/*     */ package net.minecraft.world.chunk.storage;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.List;
/*     */ import java.util.zip.DeflaterOutputStream;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.InflaterInputStream;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class RegionFile
/*     */ {
/*  20 */   private static final byte[] emptySector = new byte[4096];
/*     */   private final File fileName;
/*     */   private RandomAccessFile dataFile;
/*  23 */   private final int[] offsets = new int[1024];
/*  24 */   private final int[] chunkTimestamps = new int[1024];
/*     */   
/*     */   private List sectorFree;
/*     */   
/*     */   private int sizeDelta;
/*     */   
/*     */   private long lastModified;
/*     */   private static final String __OBFID = "CL_00000381";
/*     */   
/*     */   public RegionFile(File p_i2001_1_) {
/*  34 */     this.fileName = p_i2001_1_;
/*  35 */     this.sizeDelta = 0;
/*     */ 
/*     */     
/*     */     try {
/*  39 */       if (p_i2001_1_.exists())
/*     */       {
/*  41 */         this.lastModified = p_i2001_1_.lastModified();
/*     */       }
/*     */       
/*  44 */       this.dataFile = new RandomAccessFile(p_i2001_1_, "rw");
/*     */ 
/*     */       
/*  47 */       if (this.dataFile.length() < 4096L) {
/*     */         int i;
/*  49 */         for (i = 0; i < 1024; i++)
/*     */         {
/*  51 */           this.dataFile.writeInt(0);
/*     */         }
/*     */         
/*  54 */         for (i = 0; i < 1024; i++)
/*     */         {
/*  56 */           this.dataFile.writeInt(0);
/*     */         }
/*     */         
/*  59 */         this.sizeDelta += 8192;
/*     */       } 
/*     */       
/*  62 */       if ((this.dataFile.length() & 0xFFFL) != 0L)
/*     */       {
/*  64 */         for (int i = 0; i < (this.dataFile.length() & 0xFFFL); i++)
/*     */         {
/*  66 */           this.dataFile.write(0);
/*     */         }
/*     */       }
/*     */       
/*  70 */       int var2 = (int)this.dataFile.length() / 4096;
/*  71 */       this.sectorFree = Lists.newArrayListWithCapacity(var2);
/*     */       
/*     */       int var3;
/*  74 */       for (var3 = 0; var3 < var2; var3++)
/*     */       {
/*  76 */         this.sectorFree.add(Boolean.valueOf(true));
/*     */       }
/*     */       
/*  79 */       this.sectorFree.set(0, Boolean.valueOf(false));
/*  80 */       this.sectorFree.set(1, Boolean.valueOf(false));
/*  81 */       this.dataFile.seek(0L);
/*     */ 
/*     */       
/*  84 */       for (var3 = 0; var3 < 1024; var3++) {
/*     */         
/*  86 */         int var4 = this.dataFile.readInt();
/*  87 */         this.offsets[var3] = var4;
/*     */         
/*  89 */         if (var4 != 0 && (var4 >> 8) + (var4 & 0xFF) <= this.sectorFree.size())
/*     */         {
/*  91 */           for (int var5 = 0; var5 < (var4 & 0xFF); var5++)
/*     */           {
/*  93 */             this.sectorFree.set((var4 >> 8) + var5, Boolean.valueOf(false));
/*     */           }
/*     */         }
/*     */       } 
/*     */       
/*  98 */       for (var3 = 0; var3 < 1024; var3++)
/*     */       {
/* 100 */         int var4 = this.dataFile.readInt();
/* 101 */         this.chunkTimestamps[var3] = var4;
/*     */       }
/*     */     
/* 104 */     } catch (IOException var6) {
/*     */       
/* 106 */       var6.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized DataInputStream getChunkDataInputStream(int p_76704_1_, int p_76704_2_) {
/* 115 */     if (outOfBounds(p_76704_1_, p_76704_2_))
/*     */     {
/* 117 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 123 */       int var3 = getOffset(p_76704_1_, p_76704_2_);
/*     */       
/* 125 */       if (var3 == 0)
/*     */       {
/* 127 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 131 */       int var4 = var3 >> 8;
/* 132 */       int var5 = var3 & 0xFF;
/*     */       
/* 134 */       if (var4 + var5 > this.sectorFree.size())
/*     */       {
/* 136 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 140 */       this.dataFile.seek((var4 * 4096));
/* 141 */       int var6 = this.dataFile.readInt();
/*     */       
/* 143 */       if (var6 > 4096 * var5)
/*     */       {
/* 145 */         return null;
/*     */       }
/* 147 */       if (var6 <= 0)
/*     */       {
/* 149 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 153 */       byte var7 = this.dataFile.readByte();
/*     */ 
/*     */       
/* 156 */       if (var7 == 1) {
/*     */         
/* 158 */         byte[] var8 = new byte[var6 - 1];
/* 159 */         this.dataFile.read(var8);
/* 160 */         return new DataInputStream(new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(var8))));
/*     */       } 
/* 162 */       if (var7 == 2) {
/*     */         
/* 164 */         byte[] var8 = new byte[var6 - 1];
/* 165 */         this.dataFile.read(var8);
/* 166 */         return new DataInputStream(new BufferedInputStream(new InflaterInputStream(new ByteArrayInputStream(var8))));
/*     */       } 
/*     */ 
/*     */       
/* 170 */       return null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 176 */     catch (IOException var9) {
/*     */       
/* 178 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataOutputStream getChunkDataOutputStream(int p_76710_1_, int p_76710_2_) {
/* 188 */     return outOfBounds(p_76710_1_, p_76710_2_) ? null : new DataOutputStream(new DeflaterOutputStream(new ChunkBuffer(p_76710_1_, p_76710_2_)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void write(int p_76706_1_, int p_76706_2_, byte[] p_76706_3_, int p_76706_4_) {
/*     */     try {
/* 198 */       int var5 = getOffset(p_76706_1_, p_76706_2_);
/* 199 */       int var6 = var5 >> 8;
/* 200 */       int var7 = var5 & 0xFF;
/* 201 */       int var8 = (p_76706_4_ + 5) / 4096 + 1;
/*     */       
/* 203 */       if (var8 >= 256) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 208 */       if (var6 != 0 && var7 == var8) {
/*     */         
/* 210 */         write(var6, p_76706_3_, p_76706_4_);
/*     */       } else {
/*     */         int var9;
/*     */ 
/*     */ 
/*     */         
/* 216 */         for (var9 = 0; var9 < var7; var9++)
/*     */         {
/* 218 */           this.sectorFree.set(var6 + var9, Boolean.valueOf(true));
/*     */         }
/*     */         
/* 221 */         var9 = this.sectorFree.indexOf(Boolean.valueOf(true));
/* 222 */         int var10 = 0;
/*     */ 
/*     */         
/* 225 */         if (var9 != -1)
/*     */         {
/* 227 */           for (int var11 = var9; var11 < this.sectorFree.size(); var11++) {
/*     */             
/* 229 */             if (var10 != 0) {
/*     */               
/* 231 */               if (((Boolean)this.sectorFree.get(var11)).booleanValue())
/*     */               {
/* 233 */                 var10++;
/*     */               }
/*     */               else
/*     */               {
/* 237 */                 var10 = 0;
/*     */               }
/*     */             
/* 240 */             } else if (((Boolean)this.sectorFree.get(var11)).booleanValue()) {
/*     */               
/* 242 */               var9 = var11;
/* 243 */               var10 = 1;
/*     */             } 
/*     */             
/* 246 */             if (var10 >= var8) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 253 */         if (var10 >= var8) {
/*     */           
/* 255 */           var6 = var9;
/* 256 */           setOffset(p_76706_1_, p_76706_2_, var9 << 8 | var8);
/*     */           
/* 258 */           for (int var11 = 0; var11 < var8; var11++)
/*     */           {
/* 260 */             this.sectorFree.set(var6 + var11, Boolean.valueOf(false));
/*     */           }
/*     */           
/* 263 */           write(var6, p_76706_3_, p_76706_4_);
/*     */         }
/*     */         else {
/*     */           
/* 267 */           this.dataFile.seek(this.dataFile.length());
/* 268 */           var6 = this.sectorFree.size();
/*     */           
/* 270 */           for (int var11 = 0; var11 < var8; var11++) {
/*     */             
/* 272 */             this.dataFile.write(emptySector);
/* 273 */             this.sectorFree.add(Boolean.valueOf(false));
/*     */           } 
/*     */           
/* 276 */           this.sizeDelta += 4096 * var8;
/* 277 */           write(var6, p_76706_3_, p_76706_4_);
/* 278 */           setOffset(p_76706_1_, p_76706_2_, var6 << 8 | var8);
/*     */         } 
/*     */       } 
/*     */       
/* 282 */       setChunkTimestamp(p_76706_1_, p_76706_2_, (int)(MinecraftServer.getCurrentTimeMillis() / 1000L));
/*     */     }
/* 284 */     catch (IOException var12) {
/*     */       
/* 286 */       var12.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void write(int p_76712_1_, byte[] p_76712_2_, int p_76712_3_) throws IOException {
/* 295 */     this.dataFile.seek((p_76712_1_ * 4096));
/* 296 */     this.dataFile.writeInt(p_76712_3_ + 1);
/* 297 */     this.dataFile.writeByte(2);
/* 298 */     this.dataFile.write(p_76712_2_, 0, p_76712_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean outOfBounds(int p_76705_1_, int p_76705_2_) {
/* 306 */     return !(p_76705_1_ >= 0 && p_76705_1_ < 32 && p_76705_2_ >= 0 && p_76705_2_ < 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getOffset(int p_76707_1_, int p_76707_2_) {
/* 314 */     return this.offsets[p_76707_1_ + p_76707_2_ * 32];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChunkSaved(int p_76709_1_, int p_76709_2_) {
/* 322 */     return (getOffset(p_76709_1_, p_76709_2_) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setOffset(int p_76711_1_, int p_76711_2_, int p_76711_3_) throws IOException {
/* 330 */     this.offsets[p_76711_1_ + p_76711_2_ * 32] = p_76711_3_;
/* 331 */     this.dataFile.seek(((p_76711_1_ + p_76711_2_ * 32) * 4));
/* 332 */     this.dataFile.writeInt(p_76711_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setChunkTimestamp(int p_76713_1_, int p_76713_2_, int p_76713_3_) throws IOException {
/* 340 */     this.chunkTimestamps[p_76713_1_ + p_76713_2_ * 32] = p_76713_3_;
/* 341 */     this.dataFile.seek((4096 + (p_76713_1_ + p_76713_2_ * 32) * 4));
/* 342 */     this.dataFile.writeInt(p_76713_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 350 */     if (this.dataFile != null)
/*     */     {
/* 352 */       this.dataFile.close();
/*     */     }
/*     */   }
/*     */   
/*     */   class ChunkBuffer
/*     */     extends ByteArrayOutputStream
/*     */   {
/*     */     private int chunkX;
/*     */     private int chunkZ;
/*     */     private static final String __OBFID = "CL_00000382";
/*     */     
/*     */     public ChunkBuffer(int p_i2000_2_, int p_i2000_3_) {
/* 364 */       super(8096);
/* 365 */       this.chunkX = p_i2000_2_;
/* 366 */       this.chunkZ = p_i2000_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void close() throws IOException {
/* 371 */       RegionFile.this.write(this.chunkX, this.chunkZ, this.buf, this.count);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\storage\RegionFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */