/*     */ package net.minecraft.world.chunk.storage;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.AnvilConverterException;
/*     */ import net.minecraft.nbt.CompressedStreamTools;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.WorldChunkManager;
/*     */ import net.minecraft.world.biome.WorldChunkManagerHell;
/*     */ import net.minecraft.world.storage.ISaveHandler;
/*     */ import net.minecraft.world.storage.SaveFormatComparator;
/*     */ import net.minecraft.world.storage.SaveFormatOld;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class AnvilSaveConverter extends SaveFormatOld {
/*  32 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private static final String __OBFID = "CL_00000582";
/*     */   
/*     */   public AnvilSaveConverter(File p_i2144_1_) {
/*  37 */     super(p_i2144_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_154333_a() {
/*  42 */     return "Anvil";
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSaveList() throws AnvilConverterException {
/*  47 */     if (this.savesDirectory != null && this.savesDirectory.exists() && this.savesDirectory.isDirectory()) {
/*     */       
/*  49 */       ArrayList<SaveFormatComparator> var1 = Lists.newArrayList();
/*  50 */       File[] var2 = this.savesDirectory.listFiles();
/*  51 */       File[] var3 = var2;
/*  52 */       int var4 = var2.length;
/*     */       
/*  54 */       for (int var5 = 0; var5 < var4; var5++) {
/*     */         
/*  56 */         File var6 = var3[var5];
/*     */         
/*  58 */         if (var6.isDirectory()) {
/*     */           
/*  60 */           String var7 = var6.getName();
/*  61 */           WorldInfo var8 = getWorldInfo(var7);
/*     */           
/*  63 */           if (var8 != null && (var8.getSaveVersion() == 19132 || var8.getSaveVersion() == 19133)) {
/*     */             
/*  65 */             boolean var9 = (var8.getSaveVersion() != getSaveVersion());
/*  66 */             String var10 = var8.getWorldName();
/*     */             
/*  68 */             if (StringUtils.isEmpty(var10))
/*     */             {
/*  70 */               var10 = var7;
/*     */             }
/*     */             
/*  73 */             long var11 = 0L;
/*  74 */             var1.add(new SaveFormatComparator(var7, var10, var8.getLastTimePlayed(), var11, var8.getGameType(), var9, var8.isHardcoreModeEnabled(), var8.areCommandsAllowed()));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  79 */       return var1;
/*     */     } 
/*     */ 
/*     */     
/*  83 */     throw new AnvilConverterException("Unable to read or access folder where game worlds are saved!");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getSaveVersion() {
/*  89 */     return 19133;
/*     */   }
/*     */ 
/*     */   
/*     */   public void flushCache() {
/*  94 */     RegionFileCache.clearRegionFileReferences();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ISaveHandler getSaveLoader(String p_75804_1_, boolean p_75804_2_) {
/* 102 */     return (ISaveHandler)new AnvilSaveHandler(this.savesDirectory, p_75804_1_, p_75804_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_154334_a(String p_154334_1_) {
/* 107 */     WorldInfo var2 = getWorldInfo(p_154334_1_);
/* 108 */     return (var2 != null && var2.getSaveVersion() == 19132);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOldMapFormat(String p_75801_1_) {
/* 116 */     WorldInfo var2 = getWorldInfo(p_75801_1_);
/* 117 */     return (var2 != null && var2.getSaveVersion() != getSaveVersion());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean convertMapFormat(String p_75805_1_, IProgressUpdate p_75805_2_) {
/* 125 */     p_75805_2_.setLoadingProgress(0);
/* 126 */     ArrayList var3 = Lists.newArrayList();
/* 127 */     ArrayList var4 = Lists.newArrayList();
/* 128 */     ArrayList var5 = Lists.newArrayList();
/* 129 */     File var6 = new File(this.savesDirectory, p_75805_1_);
/* 130 */     File var7 = new File(var6, "DIM-1");
/* 131 */     File var8 = new File(var6, "DIM1");
/* 132 */     logger.info("Scanning folders...");
/* 133 */     addRegionFilesToCollection(var6, var3);
/*     */     
/* 135 */     if (var7.exists())
/*     */     {
/* 137 */       addRegionFilesToCollection(var7, var4);
/*     */     }
/*     */     
/* 140 */     if (var8.exists())
/*     */     {
/* 142 */       addRegionFilesToCollection(var8, var5);
/*     */     }
/*     */     
/* 145 */     int var9 = var3.size() + var4.size() + var5.size();
/* 146 */     logger.info("Total conversion count is " + var9);
/* 147 */     WorldInfo var10 = getWorldInfo(p_75805_1_);
/* 148 */     Object var11 = null;
/*     */     
/* 150 */     if (var10.getTerrainType() == WorldType.FLAT) {
/*     */       
/* 152 */       var11 = new WorldChunkManagerHell(BiomeGenBase.plains, 0.5F);
/*     */     }
/*     */     else {
/*     */       
/* 156 */       var11 = new WorldChunkManager(var10.getSeed(), var10.getTerrainType(), var10.getGeneratorOptions());
/*     */     } 
/*     */     
/* 159 */     convertFile(new File(var6, "region"), var3, (WorldChunkManager)var11, 0, var9, p_75805_2_);
/* 160 */     convertFile(new File(var7, "region"), var4, (WorldChunkManager)new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F), var3.size(), var9, p_75805_2_);
/* 161 */     convertFile(new File(var8, "region"), var5, (WorldChunkManager)new WorldChunkManagerHell(BiomeGenBase.sky, 0.0F), var3.size() + var4.size(), var9, p_75805_2_);
/* 162 */     var10.setSaveVersion(19133);
/*     */     
/* 164 */     if (var10.getTerrainType() == WorldType.DEFAULT_1_1)
/*     */     {
/* 166 */       var10.setTerrainType(WorldType.DEFAULT);
/*     */     }
/*     */     
/* 169 */     createFile(p_75805_1_);
/* 170 */     ISaveHandler var12 = getSaveLoader(p_75805_1_, false);
/* 171 */     var12.saveWorldInfo(var10);
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createFile(String p_75809_1_) {
/* 180 */     File var2 = new File(this.savesDirectory, p_75809_1_);
/*     */     
/* 182 */     if (!var2.exists()) {
/*     */       
/* 184 */       logger.warn("Unable to create level.dat_mcr backup");
/*     */     }
/*     */     else {
/*     */       
/* 188 */       File var3 = new File(var2, "level.dat");
/*     */       
/* 190 */       if (!var3.exists()) {
/*     */         
/* 192 */         logger.warn("Unable to create level.dat_mcr backup");
/*     */       }
/*     */       else {
/*     */         
/* 196 */         File var4 = new File(var2, "level.dat_mcr");
/*     */         
/* 198 */         if (!var3.renameTo(var4))
/*     */         {
/* 200 */           logger.warn("Unable to create level.dat_mcr backup");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void convertFile(File p_75813_1_, Iterable p_75813_2_, WorldChunkManager p_75813_3_, int p_75813_4_, int p_75813_5_, IProgressUpdate p_75813_6_) {
/* 208 */     Iterator<File> var7 = p_75813_2_.iterator();
/*     */     
/* 210 */     while (var7.hasNext()) {
/*     */       
/* 212 */       File var8 = var7.next();
/* 213 */       convertChunks(p_75813_1_, var8, p_75813_3_, p_75813_4_, p_75813_5_, p_75813_6_);
/* 214 */       p_75813_4_++;
/* 215 */       int var9 = (int)Math.round(100.0D * p_75813_4_ / p_75813_5_);
/* 216 */       p_75813_6_.setLoadingProgress(var9);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void convertChunks(File p_75811_1_, File p_75811_2_, WorldChunkManager p_75811_3_, int p_75811_4_, int p_75811_5_, IProgressUpdate p_75811_6_) {
/*     */     try {
/* 227 */       String var7 = p_75811_2_.getName();
/* 228 */       RegionFile var8 = new RegionFile(p_75811_2_);
/* 229 */       RegionFile var9 = new RegionFile(new File(p_75811_1_, String.valueOf(var7.substring(0, var7.length() - ".mcr".length())) + ".mca"));
/*     */       
/* 231 */       for (int var10 = 0; var10 < 32; var10++) {
/*     */         int var11;
/*     */ 
/*     */         
/* 235 */         for (var11 = 0; var11 < 32; var11++) {
/*     */           
/* 237 */           if (var8.isChunkSaved(var10, var11) && !var9.isChunkSaved(var10, var11)) {
/*     */             
/* 239 */             DataInputStream var12 = var8.getChunkDataInputStream(var10, var11);
/*     */             
/* 241 */             if (var12 == null) {
/*     */               
/* 243 */               logger.warn("Failed to fetch input stream");
/*     */             }
/*     */             else {
/*     */               
/* 247 */               NBTTagCompound var13 = CompressedStreamTools.read(var12);
/* 248 */               var12.close();
/* 249 */               NBTTagCompound var14 = var13.getCompoundTag("Level");
/* 250 */               ChunkLoader.AnvilConverterData var15 = ChunkLoader.load(var14);
/* 251 */               NBTTagCompound var16 = new NBTTagCompound();
/* 252 */               NBTTagCompound var17 = new NBTTagCompound();
/* 253 */               var16.setTag("Level", (NBTBase)var17);
/* 254 */               ChunkLoader.convertToAnvilFormat(var15, var17, p_75811_3_);
/* 255 */               DataOutputStream var18 = var9.getChunkDataOutputStream(var10, var11);
/* 256 */               CompressedStreamTools.write(var16, var18);
/* 257 */               var18.close();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 262 */         var11 = (int)Math.round(100.0D * (p_75811_4_ * 1024) / (p_75811_5_ * 1024));
/* 263 */         int var20 = (int)Math.round(100.0D * ((var10 + 1) * 32 + p_75811_4_ * 1024) / (p_75811_5_ * 1024));
/*     */         
/* 265 */         if (var20 > var11)
/*     */         {
/* 267 */           p_75811_6_.setLoadingProgress(var20);
/*     */         }
/*     */       } 
/*     */       
/* 271 */       var8.close();
/* 272 */       var9.close();
/*     */     }
/* 274 */     catch (IOException var19) {
/*     */       
/* 276 */       var19.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addRegionFilesToCollection(File p_75810_1_, Collection<? super File> p_75810_2_) {
/* 285 */     File var3 = new File(p_75810_1_, "region");
/* 286 */     File[] var4 = var3.listFiles(new FilenameFilter()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000583";
/*     */           
/*     */           public boolean accept(File p_accept_1_, String p_accept_2_) {
/* 291 */             return p_accept_2_.endsWith(".mcr");
/*     */           }
/*     */         });
/*     */     
/* 295 */     if (var4 != null)
/*     */     {
/* 297 */       Collections.addAll(p_75810_2_, var4);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\storage\AnvilSaveConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */