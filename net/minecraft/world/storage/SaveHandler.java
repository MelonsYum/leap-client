/*     */ package net.minecraft.world.storage;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.CompressedStreamTools;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.world.MinecraftException;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.chunk.storage.IChunkLoader;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class SaveHandler implements ISaveHandler, IPlayerFileData {
/*  21 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private final File worldDirectory;
/*     */ 
/*     */   
/*     */   private final File playersDirectory;
/*     */ 
/*     */   
/*     */   private final File mapDataDir;
/*     */ 
/*     */   
/*  33 */   private final long initializationTime = MinecraftServer.getCurrentTimeMillis();
/*     */   
/*     */   private final String saveDirectoryName;
/*     */   
/*     */   private static final String __OBFID = "CL_00000585";
/*     */ 
/*     */   
/*     */   public SaveHandler(File p_i2146_1_, String p_i2146_2_, boolean p_i2146_3_) {
/*  41 */     this.worldDirectory = new File(p_i2146_1_, p_i2146_2_);
/*  42 */     this.worldDirectory.mkdirs();
/*  43 */     this.playersDirectory = new File(this.worldDirectory, "playerdata");
/*  44 */     this.mapDataDir = new File(this.worldDirectory, "data");
/*  45 */     this.mapDataDir.mkdirs();
/*  46 */     this.saveDirectoryName = p_i2146_2_;
/*     */     
/*  48 */     if (p_i2146_3_)
/*     */     {
/*  50 */       this.playersDirectory.mkdirs();
/*     */     }
/*     */     
/*  53 */     setSessionLock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSessionLock() {
/*     */     try {
/*  63 */       File var1 = new File(this.worldDirectory, "session.lock");
/*  64 */       DataOutputStream var2 = new DataOutputStream(new FileOutputStream(var1));
/*     */ 
/*     */       
/*     */       try {
/*  68 */         var2.writeLong(this.initializationTime);
/*     */       }
/*     */       finally {
/*     */         
/*  72 */         var2.close();
/*     */       }
/*     */     
/*  75 */     } catch (IOException var7) {
/*     */       
/*  77 */       var7.printStackTrace();
/*  78 */       throw new RuntimeException("Failed to check session lock, aborting");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getWorldDirectory() {
/*  87 */     return this.worldDirectory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkSessionLock() throws MinecraftException {
/*     */     try {
/*  97 */       File var1 = new File(this.worldDirectory, "session.lock");
/*  98 */       DataInputStream var2 = new DataInputStream(new FileInputStream(var1));
/*     */ 
/*     */       
/*     */       try {
/* 102 */         if (var2.readLong() != this.initializationTime)
/*     */         {
/* 104 */           throw new MinecraftException("The save is being accessed from another location, aborting");
/*     */         }
/*     */       }
/*     */       finally {
/*     */         
/* 109 */         var2.close();
/*     */       }
/*     */     
/* 112 */     } catch (IOException var7) {
/*     */       
/* 114 */       throw new MinecraftException("Failed to check session lock, aborting");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkLoader getChunkLoader(WorldProvider p_75763_1_) {
/* 123 */     throw new RuntimeException("Old Chunk Storage is no longer supported.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldInfo loadWorldInfo() {
/* 131 */     File var1 = new File(this.worldDirectory, "level.dat");
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (var1.exists()) {
/*     */       
/*     */       try {
/*     */         
/* 139 */         NBTTagCompound var2 = CompressedStreamTools.readCompressed(new FileInputStream(var1));
/* 140 */         NBTTagCompound var3 = var2.getCompoundTag("Data");
/* 141 */         return new WorldInfo(var3);
/*     */       }
/* 143 */       catch (Exception var5) {
/*     */         
/* 145 */         var5.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 149 */     var1 = new File(this.worldDirectory, "level.dat_old");
/*     */     
/* 151 */     if (var1.exists()) {
/*     */       
/*     */       try {
/*     */         
/* 155 */         NBTTagCompound var2 = CompressedStreamTools.readCompressed(new FileInputStream(var1));
/* 156 */         NBTTagCompound var3 = var2.getCompoundTag("Data");
/* 157 */         return new WorldInfo(var3);
/*     */       }
/* 159 */       catch (Exception var4) {
/*     */         
/* 161 */         var4.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 165 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveWorldInfoWithPlayer(WorldInfo p_75755_1_, NBTTagCompound p_75755_2_) {
/* 173 */     NBTTagCompound var3 = p_75755_1_.cloneNBTCompound(p_75755_2_);
/* 174 */     NBTTagCompound var4 = new NBTTagCompound();
/* 175 */     var4.setTag("Data", (NBTBase)var3);
/*     */ 
/*     */     
/*     */     try {
/* 179 */       File var5 = new File(this.worldDirectory, "level.dat_new");
/* 180 */       File var6 = new File(this.worldDirectory, "level.dat_old");
/* 181 */       File var7 = new File(this.worldDirectory, "level.dat");
/* 182 */       CompressedStreamTools.writeCompressed(var4, new FileOutputStream(var5));
/*     */       
/* 184 */       if (var6.exists())
/*     */       {
/* 186 */         var6.delete();
/*     */       }
/*     */       
/* 189 */       var7.renameTo(var6);
/*     */       
/* 191 */       if (var7.exists())
/*     */       {
/* 193 */         var7.delete();
/*     */       }
/*     */       
/* 196 */       var5.renameTo(var7);
/*     */       
/* 198 */       if (var5.exists())
/*     */       {
/* 200 */         var5.delete();
/*     */       }
/*     */     }
/* 203 */     catch (Exception var8) {
/*     */       
/* 205 */       var8.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveWorldInfo(WorldInfo p_75761_1_) {
/* 214 */     NBTTagCompound var2 = p_75761_1_.getNBTTagCompound();
/* 215 */     NBTTagCompound var3 = new NBTTagCompound();
/* 216 */     var3.setTag("Data", (NBTBase)var2);
/*     */ 
/*     */     
/*     */     try {
/* 220 */       File var4 = new File(this.worldDirectory, "level.dat_new");
/* 221 */       File var5 = new File(this.worldDirectory, "level.dat_old");
/* 222 */       File var6 = new File(this.worldDirectory, "level.dat");
/* 223 */       CompressedStreamTools.writeCompressed(var3, new FileOutputStream(var4));
/*     */       
/* 225 */       if (var5.exists())
/*     */       {
/* 227 */         var5.delete();
/*     */       }
/*     */       
/* 230 */       var6.renameTo(var5);
/*     */       
/* 232 */       if (var6.exists())
/*     */       {
/* 234 */         var6.delete();
/*     */       }
/*     */       
/* 237 */       var4.renameTo(var6);
/*     */       
/* 239 */       if (var4.exists())
/*     */       {
/* 241 */         var4.delete();
/*     */       }
/*     */     }
/* 244 */     catch (Exception var7) {
/*     */       
/* 246 */       var7.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePlayerData(EntityPlayer p_75753_1_) {
/*     */     try {
/* 257 */       NBTTagCompound var2 = new NBTTagCompound();
/* 258 */       p_75753_1_.writeToNBT(var2);
/* 259 */       File var3 = new File(this.playersDirectory, String.valueOf(p_75753_1_.getUniqueID().toString()) + ".dat.tmp");
/* 260 */       File var4 = new File(this.playersDirectory, String.valueOf(p_75753_1_.getUniqueID().toString()) + ".dat");
/* 261 */       CompressedStreamTools.writeCompressed(var2, new FileOutputStream(var3));
/*     */       
/* 263 */       if (var4.exists())
/*     */       {
/* 265 */         var4.delete();
/*     */       }
/*     */       
/* 268 */       var3.renameTo(var4);
/*     */     }
/* 270 */     catch (Exception var5) {
/*     */       
/* 272 */       logger.warn("Failed to save player data for " + p_75753_1_.getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound readPlayerData(EntityPlayer p_75752_1_) {
/* 281 */     NBTTagCompound var2 = null;
/*     */ 
/*     */     
/*     */     try {
/* 285 */       File var3 = new File(this.playersDirectory, String.valueOf(p_75752_1_.getUniqueID().toString()) + ".dat");
/*     */       
/* 287 */       if (var3.exists() && var3.isFile())
/*     */       {
/* 289 */         var2 = CompressedStreamTools.readCompressed(new FileInputStream(var3));
/*     */       }
/*     */     }
/* 292 */     catch (Exception var4) {
/*     */       
/* 294 */       logger.warn("Failed to load player data for " + p_75752_1_.getName());
/*     */     } 
/*     */     
/* 297 */     if (var2 != null)
/*     */     {
/* 299 */       p_75752_1_.readFromNBT(var2);
/*     */     }
/*     */     
/* 302 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPlayerFileData getPlayerNBTManager() {
/* 307 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getAvailablePlayerDat() {
/* 315 */     String[] var1 = this.playersDirectory.list();
/*     */     
/* 317 */     if (var1 == null)
/*     */     {
/* 319 */       var1 = new String[0];
/*     */     }
/*     */     
/* 322 */     for (int var2 = 0; var2 < var1.length; var2++) {
/*     */       
/* 324 */       if (var1[var2].endsWith(".dat"))
/*     */       {
/* 326 */         var1[var2] = var1[var2].substring(0, var1[var2].length() - 4);
/*     */       }
/*     */     } 
/*     */     
/* 330 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getMapFileFromName(String p_75758_1_) {
/* 343 */     return new File(this.mapDataDir, String.valueOf(p_75758_1_) + ".dat");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWorldDirectoryName() {
/* 351 */     return this.saveDirectoryName;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\storage\SaveHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */