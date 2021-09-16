/*     */ package net.minecraft.server.management;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S21PacketChunkData;
/*     */ import net.minecraft.network.play.server.S22PacketMultiBlockChange;
/*     */ import net.minecraft.network.play.server.S23PacketBlockChange;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class PlayerManager {
/*  25 */   private static final Logger field_152627_a = LogManager.getLogger();
/*     */   
/*     */   private final WorldServer theWorldServer;
/*     */   
/*  29 */   private final List players = Lists.newArrayList();
/*     */ 
/*     */   
/*  32 */   private final LongHashMap playerInstances = new LongHashMap();
/*     */ 
/*     */   
/*  35 */   private final List playerInstancesToUpdate = Lists.newArrayList();
/*     */ 
/*     */   
/*  38 */   private final List playerInstanceList = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   private int playerViewRadius;
/*     */ 
/*     */ 
/*     */   
/*     */   private long previousTotalWorldTime;
/*     */ 
/*     */   
/*  49 */   private final int[][] xzDirectionsConst = new int[][] { { 1 }, { 0, 1 }, { -1 }, { 0, -1 } };
/*     */   
/*     */   private static final String __OBFID = "CL_00001434";
/*     */   
/*     */   public PlayerManager(WorldServer p_i1176_1_) {
/*  54 */     this.theWorldServer = p_i1176_1_;
/*  55 */     func_152622_a(p_i1176_1_.func_73046_m().getConfigurationManager().getViewDistance());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldServer getMinecraftServer() {
/*  63 */     return this.theWorldServer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updatePlayerInstances() {
/*  71 */     long var1 = this.theWorldServer.getTotalWorldTime();
/*     */ 
/*     */ 
/*     */     
/*  75 */     if (var1 - this.previousTotalWorldTime > 8000L) {
/*     */       
/*  77 */       this.previousTotalWorldTime = var1;
/*     */       
/*  79 */       for (int var3 = 0; var3 < this.playerInstanceList.size(); var3++)
/*     */       {
/*  81 */         PlayerInstance var4 = this.playerInstanceList.get(var3);
/*  82 */         var4.onUpdate();
/*  83 */         var4.processChunk();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  88 */       for (int var3 = 0; var3 < this.playerInstancesToUpdate.size(); var3++) {
/*     */         
/*  90 */         PlayerInstance var4 = this.playerInstancesToUpdate.get(var3);
/*  91 */         var4.onUpdate();
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     this.playerInstancesToUpdate.clear();
/*     */     
/*  97 */     if (this.players.isEmpty()) {
/*     */       
/*  99 */       WorldProvider var5 = this.theWorldServer.provider;
/*     */       
/* 101 */       if (!var5.canRespawnHere())
/*     */       {
/* 103 */         this.theWorldServer.theChunkProviderServer.unloadAllChunks();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152621_a(int p_152621_1_, int p_152621_2_) {
/* 110 */     long var3 = p_152621_1_ + 2147483647L | p_152621_2_ + 2147483647L << 32L;
/* 111 */     return (this.playerInstances.getValueByKey(var3) != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PlayerInstance getPlayerInstance(int p_72690_1_, int p_72690_2_, boolean p_72690_3_) {
/* 119 */     long var4 = p_72690_1_ + 2147483647L | p_72690_2_ + 2147483647L << 32L;
/* 120 */     PlayerInstance var6 = (PlayerInstance)this.playerInstances.getValueByKey(var4);
/*     */     
/* 122 */     if (var6 == null && p_72690_3_) {
/*     */       
/* 124 */       var6 = new PlayerInstance(p_72690_1_, p_72690_2_);
/* 125 */       this.playerInstances.add(var4, var6);
/* 126 */       this.playerInstanceList.add(var6);
/*     */     } 
/*     */     
/* 129 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180244_a(BlockPos p_180244_1_) {
/* 134 */     int var2 = p_180244_1_.getX() >> 4;
/* 135 */     int var3 = p_180244_1_.getZ() >> 4;
/* 136 */     PlayerInstance var4 = getPlayerInstance(var2, var3, false);
/*     */     
/* 138 */     if (var4 != null)
/*     */     {
/* 140 */       var4.flagChunkForUpdate(p_180244_1_.getX() & 0xF, p_180244_1_.getY(), p_180244_1_.getZ() & 0xF);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addPlayer(EntityPlayerMP p_72683_1_) {
/* 149 */     int var2 = (int)p_72683_1_.posX >> 4;
/* 150 */     int var3 = (int)p_72683_1_.posZ >> 4;
/* 151 */     p_72683_1_.managedPosX = p_72683_1_.posX;
/* 152 */     p_72683_1_.managedPosZ = p_72683_1_.posZ;
/*     */     
/* 154 */     for (int var4 = var2 - this.playerViewRadius; var4 <= var2 + this.playerViewRadius; var4++) {
/*     */       
/* 156 */       for (int var5 = var3 - this.playerViewRadius; var5 <= var3 + this.playerViewRadius; var5++)
/*     */       {
/* 158 */         getPlayerInstance(var4, var5, true).addPlayer(p_72683_1_);
/*     */       }
/*     */     } 
/*     */     
/* 162 */     this.players.add(p_72683_1_);
/* 163 */     filterChunkLoadQueue(p_72683_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void filterChunkLoadQueue(EntityPlayerMP p_72691_1_) {
/* 171 */     ArrayList var2 = Lists.newArrayList(p_72691_1_.loadedChunks);
/* 172 */     int var3 = 0;
/* 173 */     int var4 = this.playerViewRadius;
/* 174 */     int var5 = (int)p_72691_1_.posX >> 4;
/* 175 */     int var6 = (int)p_72691_1_.posZ >> 4;
/* 176 */     int var7 = 0;
/* 177 */     int var8 = 0;
/* 178 */     ChunkCoordIntPair var9 = (getPlayerInstance(var5, var6, true)).currentChunk;
/* 179 */     p_72691_1_.loadedChunks.clear();
/*     */     
/* 181 */     if (var2.contains(var9))
/*     */     {
/* 183 */       p_72691_1_.loadedChunks.add(var9);
/*     */     }
/*     */     
/*     */     int var10;
/*     */     
/* 188 */     for (var10 = 1; var10 <= var4 * 2; var10++) {
/*     */       
/* 190 */       for (int var11 = 0; var11 < 2; var11++) {
/*     */         
/* 192 */         int[] var12 = this.xzDirectionsConst[var3++ % 4];
/*     */         
/* 194 */         for (int var13 = 0; var13 < var10; var13++) {
/*     */           
/* 196 */           var7 += var12[0];
/* 197 */           var8 += var12[1];
/* 198 */           var9 = (getPlayerInstance(var5 + var7, var6 + var8, true)).currentChunk;
/*     */           
/* 200 */           if (var2.contains(var9))
/*     */           {
/* 202 */             p_72691_1_.loadedChunks.add(var9);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 208 */     var3 %= 4;
/*     */     
/* 210 */     for (var10 = 0; var10 < var4 * 2; var10++) {
/*     */       
/* 212 */       var7 += this.xzDirectionsConst[var3][0];
/* 213 */       var8 += this.xzDirectionsConst[var3][1];
/* 214 */       var9 = (getPlayerInstance(var5 + var7, var6 + var8, true)).currentChunk;
/*     */       
/* 216 */       if (var2.contains(var9))
/*     */       {
/* 218 */         p_72691_1_.loadedChunks.add(var9);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removePlayer(EntityPlayerMP p_72695_1_) {
/* 228 */     int var2 = (int)p_72695_1_.managedPosX >> 4;
/* 229 */     int var3 = (int)p_72695_1_.managedPosZ >> 4;
/*     */     
/* 231 */     for (int var4 = var2 - this.playerViewRadius; var4 <= var2 + this.playerViewRadius; var4++) {
/*     */       
/* 233 */       for (int var5 = var3 - this.playerViewRadius; var5 <= var3 + this.playerViewRadius; var5++) {
/*     */         
/* 235 */         PlayerInstance var6 = getPlayerInstance(var4, var5, false);
/*     */         
/* 237 */         if (var6 != null)
/*     */         {
/* 239 */           var6.removePlayer(p_72695_1_);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 244 */     this.players.remove(p_72695_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean overlaps(int p_72684_1_, int p_72684_2_, int p_72684_3_, int p_72684_4_, int p_72684_5_) {
/* 253 */     int var6 = p_72684_1_ - p_72684_3_;
/* 254 */     int var7 = p_72684_2_ - p_72684_4_;
/* 255 */     return (var6 >= -p_72684_5_ && var6 <= p_72684_5_) ? ((var7 >= -p_72684_5_ && var7 <= p_72684_5_)) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateMountedMovingPlayer(EntityPlayerMP p_72685_1_) {
/* 263 */     int var2 = (int)p_72685_1_.posX >> 4;
/* 264 */     int var3 = (int)p_72685_1_.posZ >> 4;
/* 265 */     double var4 = p_72685_1_.managedPosX - p_72685_1_.posX;
/* 266 */     double var6 = p_72685_1_.managedPosZ - p_72685_1_.posZ;
/* 267 */     double var8 = var4 * var4 + var6 * var6;
/*     */     
/* 269 */     if (var8 >= 64.0D) {
/*     */       
/* 271 */       int var10 = (int)p_72685_1_.managedPosX >> 4;
/* 272 */       int var11 = (int)p_72685_1_.managedPosZ >> 4;
/* 273 */       int var12 = this.playerViewRadius;
/* 274 */       int var13 = var2 - var10;
/* 275 */       int var14 = var3 - var11;
/*     */       
/* 277 */       if (var13 != 0 || var14 != 0) {
/*     */         
/* 279 */         for (int var15 = var2 - var12; var15 <= var2 + var12; var15++) {
/*     */           
/* 281 */           for (int var16 = var3 - var12; var16 <= var3 + var12; var16++) {
/*     */             
/* 283 */             if (!overlaps(var15, var16, var10, var11, var12))
/*     */             {
/* 285 */               getPlayerInstance(var15, var16, true).addPlayer(p_72685_1_);
/*     */             }
/*     */             
/* 288 */             if (!overlaps(var15 - var13, var16 - var14, var2, var3, var12)) {
/*     */               
/* 290 */               PlayerInstance var17 = getPlayerInstance(var15 - var13, var16 - var14, false);
/*     */               
/* 292 */               if (var17 != null)
/*     */               {
/* 294 */                 var17.removePlayer(p_72685_1_);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 300 */         filterChunkLoadQueue(p_72685_1_);
/* 301 */         p_72685_1_.managedPosX = p_72685_1_.posX;
/* 302 */         p_72685_1_.managedPosZ = p_72685_1_.posZ;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlayerWatchingChunk(EntityPlayerMP p_72694_1_, int p_72694_2_, int p_72694_3_) {
/* 309 */     PlayerInstance var4 = getPlayerInstance(p_72694_2_, p_72694_3_, false);
/* 310 */     return (var4 != null && var4.playersWatchingChunk.contains(p_72694_1_) && !p_72694_1_.loadedChunks.contains(var4.currentChunk));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152622_a(int p_152622_1_) {
/* 315 */     p_152622_1_ = MathHelper.clamp_int(p_152622_1_, 3, 32);
/*     */     
/* 317 */     if (p_152622_1_ != this.playerViewRadius) {
/*     */       
/* 319 */       int var2 = p_152622_1_ - this.playerViewRadius;
/* 320 */       ArrayList var3 = Lists.newArrayList(this.players);
/* 321 */       Iterator<EntityPlayerMP> var4 = var3.iterator();
/*     */       
/* 323 */       while (var4.hasNext()) {
/*     */         
/* 325 */         EntityPlayerMP var5 = var4.next();
/* 326 */         int var6 = (int)var5.posX >> 4;
/* 327 */         int var7 = (int)var5.posZ >> 4;
/*     */ 
/*     */ 
/*     */         
/* 331 */         if (var2 > 0) {
/*     */           
/* 333 */           for (int i = var6 - p_152622_1_; i <= var6 + p_152622_1_; i++) {
/*     */             
/* 335 */             for (int var9 = var7 - p_152622_1_; var9 <= var7 + p_152622_1_; var9++) {
/*     */               
/* 337 */               PlayerInstance var10 = getPlayerInstance(i, var9, true);
/*     */               
/* 339 */               if (!var10.playersWatchingChunk.contains(var5))
/*     */               {
/* 341 */                 var10.addPlayer(var5);
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/*     */           continue;
/*     */         } 
/* 348 */         for (int var8 = var6 - this.playerViewRadius; var8 <= var6 + this.playerViewRadius; var8++) {
/*     */           
/* 350 */           for (int var9 = var7 - this.playerViewRadius; var9 <= var7 + this.playerViewRadius; var9++) {
/*     */             
/* 352 */             if (!overlaps(var8, var9, var6, var7, p_152622_1_))
/*     */             {
/* 354 */               getPlayerInstance(var8, var9, true).removePlayer(var5);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 361 */       this.playerViewRadius = p_152622_1_;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFurthestViewableBlock(int p_72686_0_) {
/* 370 */     return p_72686_0_ * 16 - 16;
/*     */   }
/*     */   
/*     */   class PlayerInstance
/*     */   {
/* 375 */     private final List playersWatchingChunk = Lists.newArrayList();
/*     */     private final ChunkCoordIntPair currentChunk;
/* 377 */     private short[] locationOfBlockChange = new short[64];
/*     */     
/*     */     private int numBlocksToUpdate;
/*     */     private int flagsYAreasToUpdate;
/*     */     private long previousWorldTime;
/*     */     private static final String __OBFID = "CL_00001435";
/*     */     
/*     */     public PlayerInstance(int p_i1518_2_, int p_i1518_3_) {
/* 385 */       this.currentChunk = new ChunkCoordIntPair(p_i1518_2_, p_i1518_3_);
/* 386 */       (PlayerManager.this.getMinecraftServer()).theChunkProviderServer.loadChunk(p_i1518_2_, p_i1518_3_);
/*     */     }
/*     */ 
/*     */     
/*     */     public void addPlayer(EntityPlayerMP p_73255_1_) {
/* 391 */       if (this.playersWatchingChunk.contains(p_73255_1_)) {
/*     */         
/* 393 */         PlayerManager.field_152627_a.debug("Failed to add player. {} already is in chunk {}, {}", new Object[] { p_73255_1_, Integer.valueOf(this.currentChunk.chunkXPos), Integer.valueOf(this.currentChunk.chunkZPos) });
/*     */       }
/*     */       else {
/*     */         
/* 397 */         if (this.playersWatchingChunk.isEmpty())
/*     */         {
/* 399 */           this.previousWorldTime = PlayerManager.this.theWorldServer.getTotalWorldTime();
/*     */         }
/*     */         
/* 402 */         this.playersWatchingChunk.add(p_73255_1_);
/* 403 */         p_73255_1_.loadedChunks.add(this.currentChunk);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void removePlayer(EntityPlayerMP p_73252_1_) {
/* 409 */       if (this.playersWatchingChunk.contains(p_73252_1_)) {
/*     */         
/* 411 */         Chunk var2 = PlayerManager.this.theWorldServer.getChunkFromChunkCoords(this.currentChunk.chunkXPos, this.currentChunk.chunkZPos);
/*     */         
/* 413 */         if (var2.isPopulated())
/*     */         {
/* 415 */           p_73252_1_.playerNetServerHandler.sendPacket((Packet)new S21PacketChunkData(var2, true, 0));
/*     */         }
/*     */         
/* 418 */         this.playersWatchingChunk.remove(p_73252_1_);
/* 419 */         p_73252_1_.loadedChunks.remove(this.currentChunk);
/*     */         
/* 421 */         if (this.playersWatchingChunk.isEmpty()) {
/*     */           
/* 423 */           long var3 = this.currentChunk.chunkXPos + 2147483647L | this.currentChunk.chunkZPos + 2147483647L << 32L;
/* 424 */           increaseInhabitedTime(var2);
/* 425 */           PlayerManager.this.playerInstances.remove(var3);
/* 426 */           PlayerManager.this.playerInstanceList.remove(this);
/*     */           
/* 428 */           if (this.numBlocksToUpdate > 0)
/*     */           {
/* 430 */             PlayerManager.this.playerInstancesToUpdate.remove(this);
/*     */           }
/*     */           
/* 433 */           (PlayerManager.this.getMinecraftServer()).theChunkProviderServer.dropChunk(this.currentChunk.chunkXPos, this.currentChunk.chunkZPos);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void processChunk() {
/* 440 */       increaseInhabitedTime(PlayerManager.this.theWorldServer.getChunkFromChunkCoords(this.currentChunk.chunkXPos, this.currentChunk.chunkZPos));
/*     */     }
/*     */ 
/*     */     
/*     */     private void increaseInhabitedTime(Chunk p_111196_1_) {
/* 445 */       p_111196_1_.setInhabitedTime(p_111196_1_.getInhabitedTime() + PlayerManager.this.theWorldServer.getTotalWorldTime() - this.previousWorldTime);
/* 446 */       this.previousWorldTime = PlayerManager.this.theWorldServer.getTotalWorldTime();
/*     */     }
/*     */ 
/*     */     
/*     */     public void flagChunkForUpdate(int p_151253_1_, int p_151253_2_, int p_151253_3_) {
/* 451 */       if (this.numBlocksToUpdate == 0)
/*     */       {
/* 453 */         PlayerManager.this.playerInstancesToUpdate.add(this);
/*     */       }
/*     */       
/* 456 */       this.flagsYAreasToUpdate |= 1 << p_151253_2_ >> 4;
/*     */       
/* 458 */       if (this.numBlocksToUpdate < 64) {
/*     */         
/* 460 */         short var4 = (short)(p_151253_1_ << 12 | p_151253_3_ << 8 | p_151253_2_);
/*     */         
/* 462 */         for (int var5 = 0; var5 < this.numBlocksToUpdate; var5++) {
/*     */           
/* 464 */           if (this.locationOfBlockChange[var5] == var4) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 470 */         this.locationOfBlockChange[this.numBlocksToUpdate++] = var4;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void sendToAllPlayersWatchingChunk(Packet p_151251_1_) {
/* 476 */       for (int var2 = 0; var2 < this.playersWatchingChunk.size(); var2++) {
/*     */         
/* 478 */         EntityPlayerMP var3 = this.playersWatchingChunk.get(var2);
/*     */         
/* 480 */         if (!var3.loadedChunks.contains(this.currentChunk))
/*     */         {
/* 482 */           var3.playerNetServerHandler.sendPacket(p_151251_1_);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void onUpdate() {
/* 489 */       if (this.numBlocksToUpdate != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 495 */         if (this.numBlocksToUpdate == 1) {
/*     */           
/* 497 */           int var1 = (this.locationOfBlockChange[0] >> 12 & 0xF) + this.currentChunk.chunkXPos * 16;
/* 498 */           int var2 = this.locationOfBlockChange[0] & 0xFF;
/* 499 */           int var3 = (this.locationOfBlockChange[0] >> 8 & 0xF) + this.currentChunk.chunkZPos * 16;
/* 500 */           BlockPos var4 = new BlockPos(var1, var2, var3);
/* 501 */           sendToAllPlayersWatchingChunk((Packet)new S23PacketBlockChange((World)PlayerManager.this.theWorldServer, var4));
/*     */           
/* 503 */           if (PlayerManager.this.theWorldServer.getBlockState(var4).getBlock().hasTileEntity())
/*     */           {
/* 505 */             sendTileToAllPlayersWatchingChunk(PlayerManager.this.theWorldServer.getTileEntity(var4));
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/* 512 */         else if (this.numBlocksToUpdate == 64) {
/*     */           
/* 514 */           int var1 = this.currentChunk.chunkXPos * 16;
/* 515 */           int var2 = this.currentChunk.chunkZPos * 16;
/* 516 */           sendToAllPlayersWatchingChunk((Packet)new S21PacketChunkData(PlayerManager.this.theWorldServer.getChunkFromChunkCoords(this.currentChunk.chunkXPos, this.currentChunk.chunkZPos), false, this.flagsYAreasToUpdate));
/*     */           
/* 518 */           for (int var3 = 0; var3 < 16; var3++) {
/*     */             
/* 520 */             if ((this.flagsYAreasToUpdate & 1 << var3) != 0)
/*     */             {
/* 522 */               int var7 = var3 << 4;
/* 523 */               List<TileEntity> var5 = PlayerManager.this.theWorldServer.func_147486_a(var1, var7, var2, var1 + 16, var7 + 16, var2 + 16);
/*     */               
/* 525 */               for (int var6 = 0; var6 < var5.size(); var6++)
/*     */               {
/* 527 */                 sendTileToAllPlayersWatchingChunk(var5.get(var6));
/*     */               }
/*     */             }
/*     */           
/*     */           } 
/*     */         } else {
/*     */           
/* 534 */           sendToAllPlayersWatchingChunk((Packet)new S22PacketMultiBlockChange(this.numBlocksToUpdate, this.locationOfBlockChange, PlayerManager.this.theWorldServer.getChunkFromChunkCoords(this.currentChunk.chunkXPos, this.currentChunk.chunkZPos)));
/*     */           
/* 536 */           for (int var1 = 0; var1 < this.numBlocksToUpdate; var1++) {
/*     */             
/* 538 */             int var2 = (this.locationOfBlockChange[var1] >> 12 & 0xF) + this.currentChunk.chunkXPos * 16;
/* 539 */             int var3 = this.locationOfBlockChange[var1] & 0xFF;
/* 540 */             int var7 = (this.locationOfBlockChange[var1] >> 8 & 0xF) + this.currentChunk.chunkZPos * 16;
/* 541 */             BlockPos var8 = new BlockPos(var2, var3, var7);
/*     */             
/* 543 */             if (PlayerManager.this.theWorldServer.getBlockState(var8).getBlock().hasTileEntity())
/*     */             {
/* 545 */               sendTileToAllPlayersWatchingChunk(PlayerManager.this.theWorldServer.getTileEntity(var8));
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 551 */         this.numBlocksToUpdate = 0;
/* 552 */         this.flagsYAreasToUpdate = 0;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private void sendTileToAllPlayersWatchingChunk(TileEntity p_151252_1_) {
/* 558 */       if (p_151252_1_ != null) {
/*     */         
/* 560 */         Packet var2 = p_151252_1_.getDescriptionPacket();
/*     */         
/* 562 */         if (var2 != null)
/*     */         {
/* 564 */           sendToAllPlayersWatchingChunk(var2);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\PlayerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */