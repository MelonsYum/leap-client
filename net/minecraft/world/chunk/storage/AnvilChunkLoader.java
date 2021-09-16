/*     */ package net.minecraft.world.chunk.storage;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.nbt.CompressedStreamTools;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.MinecraftException;
/*     */ import net.minecraft.world.NextTickListEntry;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.NibbleArray;
/*     */ import net.minecraft.world.storage.IThreadedFileIO;
/*     */ import net.minecraft.world.storage.ThreadedFileIOBase;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class AnvilChunkLoader implements IChunkLoader, IThreadedFileIO {
/*  34 */   private static final Logger logger = LogManager.getLogger();
/*  35 */   private List chunksToRemove = Lists.newArrayList();
/*  36 */   private Set pendingAnvilChunksCoordinates = Sets.newHashSet();
/*  37 */   private Object syncLockObject = new Object();
/*     */   
/*     */   private final File chunkSaveLocation;
/*     */   
/*     */   private static final String __OBFID = "CL_00000384";
/*     */ 
/*     */   
/*     */   public AnvilChunkLoader(File p_i2003_1_) {
/*  45 */     this.chunkSaveLocation = p_i2003_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk loadChunk(World worldIn, int x, int z) throws IOException {
/*  53 */     NBTTagCompound var4 = null;
/*  54 */     ChunkCoordIntPair var5 = new ChunkCoordIntPair(x, z);
/*  55 */     Object var6 = this.syncLockObject;
/*     */     
/*  57 */     synchronized (this.syncLockObject) {
/*     */       
/*  59 */       if (this.pendingAnvilChunksCoordinates.contains(var5))
/*     */       {
/*  61 */         for (int var7 = 0; var7 < this.chunksToRemove.size(); var7++) {
/*     */           
/*  63 */           if (((PendingChunk)this.chunksToRemove.get(var7)).chunkCoordinate.equals(var5)) {
/*     */             
/*  65 */             var4 = ((PendingChunk)this.chunksToRemove.get(var7)).nbtTags;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*  72 */     if (var4 == null) {
/*     */       
/*  74 */       DataInputStream var10 = RegionFileCache.getChunkInputStream(this.chunkSaveLocation, x, z);
/*     */       
/*  76 */       if (var10 == null)
/*     */       {
/*  78 */         return null;
/*     */       }
/*     */       
/*  81 */       var4 = CompressedStreamTools.read(var10);
/*     */     } 
/*     */     
/*  84 */     return checkedReadChunkFromNBT(worldIn, x, z, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Chunk checkedReadChunkFromNBT(World worldIn, int p_75822_2_, int p_75822_3_, NBTTagCompound p_75822_4_) {
/*  92 */     if (!p_75822_4_.hasKey("Level", 10)) {
/*     */       
/*  94 */       logger.error("Chunk file at " + p_75822_2_ + "," + p_75822_3_ + " is missing level data, skipping");
/*  95 */       return null;
/*     */     } 
/*  97 */     if (!p_75822_4_.getCompoundTag("Level").hasKey("Sections", 9)) {
/*     */       
/*  99 */       logger.error("Chunk file at " + p_75822_2_ + "," + p_75822_3_ + " is missing block data, skipping");
/* 100 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 104 */     Chunk var5 = readChunkFromNBT(worldIn, p_75822_4_.getCompoundTag("Level"));
/*     */     
/* 106 */     if (!var5.isAtLocation(p_75822_2_, p_75822_3_)) {
/*     */       
/* 108 */       logger.error("Chunk file at " + p_75822_2_ + "," + p_75822_3_ + " is in the wrong location; relocating. (Expected " + p_75822_2_ + ", " + p_75822_3_ + ", got " + var5.xPosition + ", " + var5.zPosition + ")");
/* 109 */       p_75822_4_.setInteger("xPos", p_75822_2_);
/* 110 */       p_75822_4_.setInteger("zPos", p_75822_3_);
/* 111 */       var5 = readChunkFromNBT(worldIn, p_75822_4_.getCompoundTag("Level"));
/*     */     } 
/*     */     
/* 114 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveChunk(World worldIn, Chunk chunkIn) throws MinecraftException, IOException {
/* 120 */     worldIn.checkSessionLock();
/*     */ 
/*     */     
/*     */     try {
/* 124 */       NBTTagCompound var3 = new NBTTagCompound();
/* 125 */       NBTTagCompound var4 = new NBTTagCompound();
/* 126 */       var3.setTag("Level", (NBTBase)var4);
/* 127 */       writeChunkToNBT(chunkIn, worldIn, var4);
/* 128 */       addChunkToPending(chunkIn.getChunkCoordIntPair(), var3);
/*     */     }
/* 130 */     catch (Exception var5) {
/*     */       
/* 132 */       var5.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addChunkToPending(ChunkCoordIntPair p_75824_1_, NBTTagCompound p_75824_2_) {
/* 138 */     Object var3 = this.syncLockObject;
/*     */     
/* 140 */     synchronized (this.syncLockObject) {
/*     */       
/* 142 */       if (this.pendingAnvilChunksCoordinates.contains(p_75824_1_))
/*     */       {
/* 144 */         for (int var4 = 0; var4 < this.chunksToRemove.size(); var4++) {
/*     */           
/* 146 */           if (((PendingChunk)this.chunksToRemove.get(var4)).chunkCoordinate.equals(p_75824_1_)) {
/*     */             
/* 148 */             this.chunksToRemove.set(var4, new PendingChunk(p_75824_1_, p_75824_2_));
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       }
/* 154 */       this.chunksToRemove.add(new PendingChunk(p_75824_1_, p_75824_2_));
/* 155 */       this.pendingAnvilChunksCoordinates.add(p_75824_1_);
/* 156 */       ThreadedFileIOBase.func_178779_a().queueIO(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean writeNextIO() {
/* 165 */     PendingChunk var1 = null;
/* 166 */     Object var2 = this.syncLockObject;
/*     */     
/* 168 */     synchronized (this.syncLockObject) {
/*     */       
/* 170 */       if (this.chunksToRemove.isEmpty())
/*     */       {
/* 172 */         return false;
/*     */       }
/*     */       
/* 175 */       var1 = this.chunksToRemove.remove(0);
/* 176 */       this.pendingAnvilChunksCoordinates.remove(var1.chunkCoordinate);
/*     */     } 
/*     */     
/* 179 */     if (var1 != null) {
/*     */       
/*     */       try {
/*     */         
/* 183 */         writeChunkNBTTags(var1);
/*     */       }
/* 185 */       catch (Exception var4) {
/*     */         
/* 187 */         var4.printStackTrace();
/*     */       } 
/*     */     }
/*     */     
/* 191 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeChunkNBTTags(PendingChunk p_75821_1_) throws IOException {
/* 196 */     DataOutputStream var2 = RegionFileCache.getChunkOutputStream(this.chunkSaveLocation, p_75821_1_.chunkCoordinate.chunkXPos, p_75821_1_.chunkCoordinate.chunkZPos);
/* 197 */     CompressedStreamTools.write(p_75821_1_.nbtTags, var2);
/* 198 */     var2.close();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveExtraChunkData(World worldIn, Chunk chunkIn) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void chunkTick() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveExtraData() {
/*     */     do {
/*     */     
/* 218 */     } while (writeNextIO());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeChunkToNBT(Chunk p_75820_1_, World worldIn, NBTTagCompound p_75820_3_) {
/* 230 */     p_75820_3_.setByte("V", (byte)1);
/* 231 */     p_75820_3_.setInteger("xPos", p_75820_1_.xPosition);
/* 232 */     p_75820_3_.setInteger("zPos", p_75820_1_.zPosition);
/* 233 */     p_75820_3_.setLong("LastUpdate", worldIn.getTotalWorldTime());
/* 234 */     p_75820_3_.setIntArray("HeightMap", p_75820_1_.getHeightMap());
/* 235 */     p_75820_3_.setBoolean("TerrainPopulated", p_75820_1_.isTerrainPopulated());
/* 236 */     p_75820_3_.setBoolean("LightPopulated", p_75820_1_.isLightPopulated());
/* 237 */     p_75820_3_.setLong("InhabitedTime", p_75820_1_.getInhabitedTime());
/* 238 */     ExtendedBlockStorage[] var4 = p_75820_1_.getBlockStorageArray();
/* 239 */     NBTTagList var5 = new NBTTagList();
/* 240 */     boolean var6 = !worldIn.provider.getHasNoSky();
/* 241 */     ExtendedBlockStorage[] var7 = var4;
/* 242 */     int var8 = var4.length;
/*     */ 
/*     */     
/* 245 */     for (int var9 = 0; var9 < var8; var9++) {
/*     */       
/* 247 */       ExtendedBlockStorage var10 = var7[var9];
/*     */       
/* 249 */       if (var10 != null) {
/*     */         
/* 251 */         NBTTagCompound var11 = new NBTTagCompound();
/* 252 */         var11.setByte("Y", (byte)(var10.getYLocation() >> 4 & 0xFF));
/* 253 */         byte[] var12 = new byte[(var10.getData()).length];
/* 254 */         NibbleArray var13 = new NibbleArray();
/* 255 */         NibbleArray var14 = null;
/*     */         
/* 257 */         for (int var15 = 0; var15 < (var10.getData()).length; var15++) {
/*     */           
/* 259 */           char var16 = var10.getData()[var15];
/* 260 */           int var17 = var15 & 0xF;
/* 261 */           int var18 = var15 >> 8 & 0xF;
/* 262 */           int var19 = var15 >> 4 & 0xF;
/*     */           
/* 264 */           if (var16 >> 12 != 0) {
/*     */             
/* 266 */             if (var14 == null)
/*     */             {
/* 268 */               var14 = new NibbleArray();
/*     */             }
/*     */             
/* 271 */             var14.set(var17, var18, var19, var16 >> 12);
/*     */           } 
/*     */           
/* 274 */           var12[var15] = (byte)(var16 >> 4 & 0xFF);
/* 275 */           var13.set(var17, var18, var19, var16 & 0xF);
/*     */         } 
/*     */         
/* 278 */         var11.setByteArray("Blocks", var12);
/* 279 */         var11.setByteArray("Data", var13.getData());
/*     */         
/* 281 */         if (var14 != null)
/*     */         {
/* 283 */           var11.setByteArray("Add", var14.getData());
/*     */         }
/*     */         
/* 286 */         var11.setByteArray("BlockLight", var10.getBlocklightArray().getData());
/*     */         
/* 288 */         if (var6) {
/*     */           
/* 290 */           var11.setByteArray("SkyLight", var10.getSkylightArray().getData());
/*     */         }
/*     */         else {
/*     */           
/* 294 */           var11.setByteArray("SkyLight", new byte[(var10.getBlocklightArray().getData()).length]);
/*     */         } 
/*     */         
/* 297 */         var5.appendTag((NBTBase)var11);
/*     */       } 
/*     */     } 
/*     */     
/* 301 */     p_75820_3_.setTag("Sections", (NBTBase)var5);
/* 302 */     p_75820_3_.setByteArray("Biomes", p_75820_1_.getBiomeArray());
/* 303 */     p_75820_1_.setHasEntities(false);
/* 304 */     NBTTagList var20 = new NBTTagList();
/*     */ 
/*     */     
/* 307 */     for (var8 = 0; var8 < (p_75820_1_.getEntityLists()).length; var8++) {
/*     */       
/* 309 */       Iterator<Entity> iterator = p_75820_1_.getEntityLists()[var8].iterator();
/*     */       
/* 311 */       while (iterator.hasNext()) {
/*     */         
/* 313 */         Entity var24 = iterator.next();
/* 314 */         NBTTagCompound var11 = new NBTTagCompound();
/*     */         
/* 316 */         if (var24.writeToNBTOptional(var11)) {
/*     */           
/* 318 */           p_75820_1_.setHasEntities(true);
/* 319 */           var20.appendTag((NBTBase)var11);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 324 */     p_75820_3_.setTag("Entities", (NBTBase)var20);
/* 325 */     NBTTagList var21 = new NBTTagList();
/* 326 */     Iterator<TileEntity> var22 = p_75820_1_.getTileEntityMap().values().iterator();
/*     */     
/* 328 */     while (var22.hasNext()) {
/*     */       
/* 330 */       TileEntity var25 = var22.next();
/* 331 */       NBTTagCompound var11 = new NBTTagCompound();
/* 332 */       var25.writeToNBT(var11);
/* 333 */       var21.appendTag((NBTBase)var11);
/*     */     } 
/*     */     
/* 336 */     p_75820_3_.setTag("TileEntities", (NBTBase)var21);
/* 337 */     List var23 = worldIn.getPendingBlockUpdates(p_75820_1_, false);
/*     */     
/* 339 */     if (var23 != null) {
/*     */       
/* 341 */       long var26 = worldIn.getTotalWorldTime();
/* 342 */       NBTTagList var27 = new NBTTagList();
/* 343 */       Iterator<NextTickListEntry> var28 = var23.iterator();
/*     */       
/* 345 */       while (var28.hasNext()) {
/*     */         
/* 347 */         NextTickListEntry var29 = var28.next();
/* 348 */         NBTTagCompound var30 = new NBTTagCompound();
/* 349 */         ResourceLocation var31 = (ResourceLocation)Block.blockRegistry.getNameForObject(var29.func_151351_a());
/* 350 */         var30.setString("i", (var31 == null) ? "" : var31.toString());
/* 351 */         var30.setInteger("x", var29.field_180282_a.getX());
/* 352 */         var30.setInteger("y", var29.field_180282_a.getY());
/* 353 */         var30.setInteger("z", var29.field_180282_a.getZ());
/* 354 */         var30.setInteger("t", (int)(var29.scheduledTime - var26));
/* 355 */         var30.setInteger("p", var29.priority);
/* 356 */         var27.appendTag((NBTBase)var30);
/*     */       } 
/*     */       
/* 359 */       p_75820_3_.setTag("TileTicks", (NBTBase)var27);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Chunk readChunkFromNBT(World worldIn, NBTTagCompound p_75823_2_) {
/* 369 */     int var3 = p_75823_2_.getInteger("xPos");
/* 370 */     int var4 = p_75823_2_.getInteger("zPos");
/* 371 */     Chunk var5 = new Chunk(worldIn, var3, var4);
/* 372 */     var5.setHeightMap(p_75823_2_.getIntArray("HeightMap"));
/* 373 */     var5.setTerrainPopulated(p_75823_2_.getBoolean("TerrainPopulated"));
/* 374 */     var5.setLightPopulated(p_75823_2_.getBoolean("LightPopulated"));
/* 375 */     var5.setInhabitedTime(p_75823_2_.getLong("InhabitedTime"));
/* 376 */     NBTTagList var6 = p_75823_2_.getTagList("Sections", 10);
/* 377 */     byte var7 = 16;
/* 378 */     ExtendedBlockStorage[] var8 = new ExtendedBlockStorage[var7];
/* 379 */     boolean var9 = !worldIn.provider.getHasNoSky();
/*     */     
/* 381 */     for (int var10 = 0; var10 < var6.tagCount(); var10++) {
/*     */       
/* 383 */       NBTTagCompound var11 = var6.getCompoundTagAt(var10);
/* 384 */       byte var12 = var11.getByte("Y");
/* 385 */       ExtendedBlockStorage var13 = new ExtendedBlockStorage(var12 << 4, var9);
/* 386 */       byte[] var14 = var11.getByteArray("Blocks");
/* 387 */       NibbleArray var15 = new NibbleArray(var11.getByteArray("Data"));
/* 388 */       NibbleArray var16 = var11.hasKey("Add", 7) ? new NibbleArray(var11.getByteArray("Add")) : null;
/* 389 */       char[] var17 = new char[var14.length];
/*     */       
/* 391 */       for (int var18 = 0; var18 < var17.length; var18++) {
/*     */         
/* 393 */         int var19 = var18 & 0xF;
/* 394 */         int var20 = var18 >> 8 & 0xF;
/* 395 */         int var21 = var18 >> 4 & 0xF;
/* 396 */         int var22 = (var16 != null) ? var16.get(var19, var20, var21) : 0;
/* 397 */         var17[var18] = (char)(var22 << 12 | (var14[var18] & 0xFF) << 4 | var15.get(var19, var20, var21));
/*     */       } 
/*     */       
/* 400 */       var13.setData(var17);
/* 401 */       var13.setBlocklightArray(new NibbleArray(var11.getByteArray("BlockLight")));
/*     */       
/* 403 */       if (var9)
/*     */       {
/* 405 */         var13.setSkylightArray(new NibbleArray(var11.getByteArray("SkyLight")));
/*     */       }
/*     */       
/* 408 */       var13.removeInvalidBlocks();
/* 409 */       var8[var12] = var13;
/*     */     } 
/*     */     
/* 412 */     var5.setStorageArrays(var8);
/*     */     
/* 414 */     if (p_75823_2_.hasKey("Biomes", 7))
/*     */     {
/* 416 */       var5.setBiomeArray(p_75823_2_.getByteArray("Biomes"));
/*     */     }
/*     */     
/* 419 */     NBTTagList var23 = p_75823_2_.getTagList("Entities", 10);
/*     */     
/* 421 */     if (var23 != null)
/*     */     {
/* 423 */       for (int var24 = 0; var24 < var23.tagCount(); var24++) {
/*     */         
/* 425 */         NBTTagCompound var26 = var23.getCompoundTagAt(var24);
/* 426 */         Entity var29 = EntityList.createEntityFromNBT(var26, worldIn);
/* 427 */         var5.setHasEntities(true);
/*     */         
/* 429 */         if (var29 != null) {
/*     */           
/* 431 */           var5.addEntity(var29);
/* 432 */           Entity var32 = var29;
/*     */           
/* 434 */           for (NBTTagCompound var35 = var26; var35.hasKey("Riding", 10); var35 = var35.getCompoundTag("Riding")) {
/*     */             
/* 436 */             Entity var37 = EntityList.createEntityFromNBT(var35.getCompoundTag("Riding"), worldIn);
/*     */             
/* 438 */             if (var37 != null) {
/*     */               
/* 440 */               var5.addEntity(var37);
/* 441 */               var32.mountEntity(var37);
/*     */             } 
/*     */             
/* 444 */             var32 = var37;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 450 */     NBTTagList var25 = p_75823_2_.getTagList("TileEntities", 10);
/*     */     
/* 452 */     if (var25 != null)
/*     */     {
/* 454 */       for (int var27 = 0; var27 < var25.tagCount(); var27++) {
/*     */         
/* 456 */         NBTTagCompound var30 = var25.getCompoundTagAt(var27);
/* 457 */         TileEntity var33 = TileEntity.createAndLoadEntity(var30);
/*     */         
/* 459 */         if (var33 != null)
/*     */         {
/* 461 */           var5.addTileEntity(var33);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 466 */     if (p_75823_2_.hasKey("TileTicks", 9)) {
/*     */       
/* 468 */       NBTTagList var28 = p_75823_2_.getTagList("TileTicks", 10);
/*     */       
/* 470 */       if (var28 != null)
/*     */       {
/* 472 */         for (int var31 = 0; var31 < var28.tagCount(); var31++) {
/*     */           Block var36;
/* 474 */           NBTTagCompound var34 = var28.getCompoundTagAt(var31);
/*     */ 
/*     */           
/* 477 */           if (var34.hasKey("i", 8)) {
/*     */             
/* 479 */             var36 = Block.getBlockFromName(var34.getString("i"));
/*     */           }
/*     */           else {
/*     */             
/* 483 */             var36 = Block.getBlockById(var34.getInteger("i"));
/*     */           } 
/*     */           
/* 486 */           worldIn.func_180497_b(new BlockPos(var34.getInteger("x"), var34.getInteger("y"), var34.getInteger("z")), var36, var34.getInteger("t"), var34.getInteger("p"));
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 491 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   static class PendingChunk
/*     */   {
/*     */     public final ChunkCoordIntPair chunkCoordinate;
/*     */     public final NBTTagCompound nbtTags;
/*     */     private static final String __OBFID = "CL_00000385";
/*     */     
/*     */     public PendingChunk(ChunkCoordIntPair p_i2002_1_, NBTTagCompound p_i2002_2_) {
/* 502 */       this.chunkCoordinate = p_i2002_1_;
/* 503 */       this.nbtTags = p_i2002_2_;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\storage\AnvilChunkLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */