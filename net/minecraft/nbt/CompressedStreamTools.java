/*     */ package net.minecraft.nbt;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.util.ReportedException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompressedStreamTools
/*     */ {
/*     */   private static final String __OBFID = "CL_00001226";
/*     */   
/*     */   public static NBTTagCompound readCompressed(InputStream p_74796_0_) throws IOException {
/*     */     NBTTagCompound var2;
/*  30 */     DataInputStream var1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(p_74796_0_)));
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  35 */       var2 = func_152456_a(var1, NBTSizeTracker.INFINITE);
/*     */     }
/*     */     finally {
/*     */       
/*  39 */       var1.close();
/*     */     } 
/*     */     
/*  42 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeCompressed(NBTTagCompound p_74799_0_, OutputStream p_74799_1_) throws IOException {
/*  50 */     DataOutputStream var2 = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(p_74799_1_)));
/*     */ 
/*     */     
/*     */     try {
/*  54 */       write(p_74799_0_, var2);
/*     */     }
/*     */     finally {
/*     */       
/*  58 */       var2.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void safeWrite(NBTTagCompound p_74793_0_, File p_74793_1_) throws IOException {
/*  64 */     File var2 = new File(String.valueOf(p_74793_1_.getAbsolutePath()) + "_tmp");
/*     */     
/*  66 */     if (var2.exists())
/*     */     {
/*  68 */       var2.delete();
/*     */     }
/*     */     
/*  71 */     write(p_74793_0_, var2);
/*     */     
/*  73 */     if (p_74793_1_.exists())
/*     */     {
/*  75 */       p_74793_1_.delete();
/*     */     }
/*     */     
/*  78 */     if (p_74793_1_.exists())
/*     */     {
/*  80 */       throw new IOException("Failed to delete " + p_74793_1_);
/*     */     }
/*     */ 
/*     */     
/*  84 */     var2.renameTo(p_74793_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void write(NBTTagCompound p_74795_0_, File p_74795_1_) throws IOException {
/*  90 */     DataOutputStream var2 = new DataOutputStream(new FileOutputStream(p_74795_1_));
/*     */ 
/*     */     
/*     */     try {
/*  94 */       write(p_74795_0_, var2);
/*     */     }
/*     */     finally {
/*     */       
/*  98 */       var2.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static NBTTagCompound read(File p_74797_0_) throws IOException {
/*     */     NBTTagCompound var2;
/* 104 */     if (!p_74797_0_.exists())
/*     */     {
/* 106 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 110 */     DataInputStream var1 = new DataInputStream(new FileInputStream(p_74797_0_));
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 115 */       var2 = func_152456_a(var1, NBTSizeTracker.INFINITE);
/*     */     }
/*     */     finally {
/*     */       
/* 119 */       var1.close();
/*     */     } 
/*     */     
/* 122 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static NBTTagCompound read(DataInputStream p_74794_0_) throws IOException {
/* 131 */     return func_152456_a(p_74794_0_, NBTSizeTracker.INFINITE);
/*     */   }
/*     */ 
/*     */   
/*     */   public static NBTTagCompound func_152456_a(DataInput p_152456_0_, NBTSizeTracker p_152456_1_) throws IOException {
/* 136 */     NBTBase var2 = func_152455_a(p_152456_0_, 0, p_152456_1_);
/*     */     
/* 138 */     if (var2 instanceof NBTTagCompound)
/*     */     {
/* 140 */       return (NBTTagCompound)var2;
/*     */     }
/*     */ 
/*     */     
/* 144 */     throw new IOException("Root tag must be a named compound tag");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void write(NBTTagCompound p_74800_0_, DataOutput p_74800_1_) throws IOException {
/* 150 */     writeTag(p_74800_0_, p_74800_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void writeTag(NBTBase p_150663_0_, DataOutput p_150663_1_) throws IOException {
/* 155 */     p_150663_1_.writeByte(p_150663_0_.getId());
/*     */     
/* 157 */     if (p_150663_0_.getId() != 0) {
/*     */       
/* 159 */       p_150663_1_.writeUTF("");
/* 160 */       p_150663_0_.write(p_150663_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static NBTBase func_152455_a(DataInput p_152455_0_, int p_152455_1_, NBTSizeTracker p_152455_2_) throws IOException {
/* 166 */     byte var3 = p_152455_0_.readByte();
/*     */     
/* 168 */     if (var3 == 0)
/*     */     {
/* 170 */       return new NBTTagEnd();
/*     */     }
/*     */ 
/*     */     
/* 174 */     p_152455_0_.readUTF();
/* 175 */     NBTBase var4 = NBTBase.createNewByType(var3);
/*     */ 
/*     */     
/*     */     try {
/* 179 */       var4.read(p_152455_0_, p_152455_1_, p_152455_2_);
/* 180 */       return var4;
/*     */     }
/* 182 */     catch (IOException var8) {
/*     */       
/* 184 */       CrashReport var6 = CrashReport.makeCrashReport(var8, "Loading NBT data");
/* 185 */       CrashReportCategory var7 = var6.makeCategory("NBT Tag");
/* 186 */       var7.addCrashSection("Tag name", "[UNNAMED TAG]");
/* 187 */       var7.addCrashSection("Tag type", Byte.valueOf(var3));
/* 188 */       throw new ReportedException(var6);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\nbt\CompressedStreamTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */