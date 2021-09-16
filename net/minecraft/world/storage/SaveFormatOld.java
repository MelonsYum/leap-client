/*     */ package net.minecraft.world.storage;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.AnvilConverterException;
/*     */ import net.minecraft.nbt.CompressedStreamTools;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class SaveFormatOld
/*     */   implements ISaveFormat {
/*  18 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   protected final File savesDirectory;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000586";
/*     */ 
/*     */   
/*     */   public SaveFormatOld(File p_i2147_1_) {
/*  28 */     if (!p_i2147_1_.exists())
/*     */     {
/*  30 */       p_i2147_1_.mkdirs();
/*     */     }
/*     */     
/*  33 */     this.savesDirectory = p_i2147_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_154333_a() {
/*  38 */     return "Old Format";
/*     */   }
/*     */ 
/*     */   
/*     */   public List getSaveList() throws AnvilConverterException {
/*  43 */     ArrayList<SaveFormatComparator> var1 = Lists.newArrayList();
/*     */     
/*  45 */     for (int var2 = 0; var2 < 5; var2++) {
/*     */       
/*  47 */       String var3 = "World" + (var2 + 1);
/*  48 */       WorldInfo var4 = getWorldInfo(var3);
/*     */       
/*  50 */       if (var4 != null)
/*     */       {
/*  52 */         var1.add(new SaveFormatComparator(var3, "", var4.getLastTimePlayed(), var4.getSizeOnDisk(), var4.getGameType(), false, var4.isHardcoreModeEnabled(), var4.areCommandsAllowed()));
/*     */       }
/*     */     } 
/*     */     
/*  56 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void flushCache() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldInfo getWorldInfo(String p_75803_1_) {
/*  66 */     File var2 = new File(this.savesDirectory, p_75803_1_);
/*     */     
/*  68 */     if (!var2.exists())
/*     */     {
/*  70 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  74 */     File var3 = new File(var2, "level.dat");
/*     */ 
/*     */ 
/*     */     
/*  78 */     if (var3.exists()) {
/*     */       
/*     */       try {
/*     */         
/*  82 */         NBTTagCompound var4 = CompressedStreamTools.readCompressed(new FileInputStream(var3));
/*  83 */         NBTTagCompound var5 = var4.getCompoundTag("Data");
/*  84 */         return new WorldInfo(var5);
/*     */       }
/*  86 */       catch (Exception var7) {
/*     */         
/*  88 */         logger.error("Exception reading " + var3, var7);
/*     */       } 
/*     */     }
/*     */     
/*  92 */     var3 = new File(var2, "level.dat_old");
/*     */     
/*  94 */     if (var3.exists()) {
/*     */       
/*     */       try {
/*     */         
/*  98 */         NBTTagCompound var4 = CompressedStreamTools.readCompressed(new FileInputStream(var3));
/*  99 */         NBTTagCompound var5 = var4.getCompoundTag("Data");
/* 100 */         return new WorldInfo(var5);
/*     */       }
/* 102 */       catch (Exception var6) {
/*     */         
/* 104 */         logger.error("Exception reading " + var3, var6);
/*     */       } 
/*     */     }
/*     */     
/* 108 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renameWorld(String p_75806_1_, String p_75806_2_) {
/* 119 */     File var3 = new File(this.savesDirectory, p_75806_1_);
/*     */     
/* 121 */     if (var3.exists()) {
/*     */       
/* 123 */       File var4 = new File(var3, "level.dat");
/*     */       
/* 125 */       if (var4.exists()) {
/*     */         
/*     */         try {
/*     */           
/* 129 */           NBTTagCompound var5 = CompressedStreamTools.readCompressed(new FileInputStream(var4));
/* 130 */           NBTTagCompound var6 = var5.getCompoundTag("Data");
/* 131 */           var6.setString("LevelName", p_75806_2_);
/* 132 */           CompressedStreamTools.writeCompressed(var5, new FileOutputStream(var4));
/*     */         }
/* 134 */         catch (Exception var7) {
/*     */           
/* 136 */           var7.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_154335_d(String p_154335_1_) {
/* 144 */     File var2 = new File(this.savesDirectory, p_154335_1_);
/*     */     
/* 146 */     if (var2.exists())
/*     */     {
/* 148 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 154 */       var2.mkdir();
/* 155 */       var2.delete();
/* 156 */       return true;
/*     */     }
/* 158 */     catch (Throwable var4) {
/*     */       
/* 160 */       logger.warn("Couldn't make new level", var4);
/* 161 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean deleteWorldDirectory(String p_75802_1_) {
/* 172 */     File var2 = new File(this.savesDirectory, p_75802_1_);
/*     */     
/* 174 */     if (!var2.exists())
/*     */     {
/* 176 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 180 */     logger.info("Deleting level " + p_75802_1_);
/*     */     
/* 182 */     for (int var3 = 1; var3 <= 5; var3++) {
/*     */       
/* 184 */       logger.info("Attempt " + var3 + "...");
/*     */       
/* 186 */       if (deleteFiles(var2.listFiles())) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 191 */       logger.warn("Unsuccessful in deleting contents.");
/*     */       
/* 193 */       if (var3 < 5) {
/*     */         
/*     */         try {
/*     */           
/* 197 */           Thread.sleep(500L);
/*     */         }
/* 199 */         catch (InterruptedException interruptedException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     return var2.delete();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean deleteFiles(File[] p_75807_0_) {
/* 216 */     for (int var1 = 0; var1 < p_75807_0_.length; var1++) {
/*     */       
/* 218 */       File var2 = p_75807_0_[var1];
/* 219 */       logger.debug("Deleting " + var2);
/*     */       
/* 221 */       if (var2.isDirectory() && !deleteFiles(var2.listFiles())) {
/*     */         
/* 223 */         logger.warn("Couldn't delete directory " + var2);
/* 224 */         return false;
/*     */       } 
/*     */       
/* 227 */       if (!var2.delete()) {
/*     */         
/* 229 */         logger.warn("Couldn't delete file " + var2);
/* 230 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 234 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ISaveHandler getSaveLoader(String p_75804_1_, boolean p_75804_2_) {
/* 242 */     return new SaveHandler(this.savesDirectory, p_75804_1_, p_75804_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_154334_a(String p_154334_1_) {
/* 247 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOldMapFormat(String p_75801_1_) {
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean convertMapFormat(String p_75805_1_, IProgressUpdate p_75805_2_) {
/* 263 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canLoadWorld(String p_90033_1_) {
/* 271 */     File var2 = new File(this.savesDirectory, p_90033_1_);
/* 272 */     return var2.isDirectory();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\storage\SaveFormatOld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */