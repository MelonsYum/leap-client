/*     */ package net.minecraft.client.multiplayer;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.MovingSoundMinecart;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.network.NetHandlerPlayClient;
/*     */ import net.minecraft.client.particle.EntityFX;
/*     */ import net.minecraft.client.particle.EntityFireworkStarterFX;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.profiler.Profiler;
/*     */ import net.minecraft.scoreboard.Scoreboard;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.storage.ISaveHandler;
/*     */ import net.minecraft.world.storage.MapStorage;
/*     */ import net.minecraft.world.storage.SaveDataMemoryStorage;
/*     */ import net.minecraft.world.storage.SaveHandlerMP;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import optifine.BlockPosM;
/*     */ import optifine.Config;
/*     */ import optifine.DynamicLights;
/*     */ import optifine.PlayerControllerOF;
/*     */ import optifine.Reflector;
/*     */ 
/*     */ public class WorldClient
/*     */   extends World {
/*     */   private NetHandlerPlayClient sendQueue;
/*     */   private ChunkProviderClient clientChunkProvider;
/*  53 */   private final Set entityList = Sets.newHashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   private final Set entitySpawnQueue = Sets.newHashSet();
/*  60 */   private final Minecraft mc = Minecraft.getMinecraft();
/*  61 */   private final Set previousActiveChunkSet = Sets.newHashSet();
/*     */   private static final String __OBFID = "CL_00000882";
/*  63 */   private BlockPosM randomTickPosM = new BlockPosM(0, 0, 0, 3);
/*     */   
/*     */   private boolean playerUpdate = false;
/*     */   
/*     */   public WorldClient(NetHandlerPlayClient p_i45063_1_, WorldSettings p_i45063_2_, int p_i45063_3_, EnumDifficulty p_i45063_4_, Profiler p_i45063_5_) {
/*  68 */     super((ISaveHandler)new SaveHandlerMP(), new WorldInfo(p_i45063_2_, "MpServer"), WorldProvider.getProviderForDimension(p_i45063_3_), p_i45063_5_, true);
/*  69 */     this.sendQueue = p_i45063_1_;
/*  70 */     getWorldInfo().setDifficulty(p_i45063_4_);
/*  71 */     this.provider.registerWorld(this);
/*  72 */     setSpawnLocation(new BlockPos(8, 64, 8));
/*  73 */     this.chunkProvider = createChunkProvider();
/*  74 */     this.mapStorage = (MapStorage)new SaveDataMemoryStorage();
/*  75 */     calculateInitialSkylight();
/*  76 */     calculateInitialWeather();
/*  77 */     Reflector.postForgeBusEvent(Reflector.WorldEvent_Load_Constructor, new Object[] { this });
/*     */     
/*  79 */     if (this.mc.playerController != null && this.mc.playerController.getClass() == PlayerControllerMP.class)
/*     */     {
/*  81 */       this.mc.playerController = (PlayerControllerMP)new PlayerControllerOF(this.mc, p_i45063_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  90 */     super.tick();
/*  91 */     func_82738_a(getTotalWorldTime() + 1L);
/*     */     
/*  93 */     if (getGameRules().getGameRuleBooleanValue("doDaylightCycle"))
/*     */     {
/*  95 */       setWorldTime(getWorldTime() + 1L);
/*     */     }
/*     */     
/*  98 */     this.theProfiler.startSection("reEntryProcessing");
/*     */     
/* 100 */     for (int var1 = 0; var1 < 10 && !this.entitySpawnQueue.isEmpty(); var1++) {
/*     */       
/* 102 */       Entity var2 = this.entitySpawnQueue.iterator().next();
/* 103 */       this.entitySpawnQueue.remove(var2);
/*     */       
/* 105 */       if (!this.loadedEntityList.contains(var2))
/*     */       {
/* 107 */         spawnEntityInWorld(var2);
/*     */       }
/*     */     } 
/*     */     
/* 111 */     this.theProfiler.endStartSection("chunkCache");
/* 112 */     this.clientChunkProvider.unloadQueuedChunks();
/* 113 */     this.theProfiler.endStartSection("blocks");
/* 114 */     func_147456_g();
/* 115 */     this.theProfiler.endSection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void invalidateBlockReceiveRegion(int p_73031_1_, int p_73031_2_, int p_73031_3_, int p_73031_4_, int p_73031_5_, int p_73031_6_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IChunkProvider createChunkProvider() {
/* 129 */     this.clientChunkProvider = new ChunkProviderClient(this);
/* 130 */     return this.clientChunkProvider;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_147456_g() {
/* 135 */     super.func_147456_g();
/* 136 */     this.previousActiveChunkSet.retainAll(this.activeChunkSet);
/*     */     
/* 138 */     if (this.previousActiveChunkSet.size() == this.activeChunkSet.size())
/*     */     {
/* 140 */       this.previousActiveChunkSet.clear();
/*     */     }
/*     */     
/* 143 */     int var1 = 0;
/* 144 */     Iterator<ChunkCoordIntPair> var2 = this.activeChunkSet.iterator();
/*     */     
/* 146 */     while (var2.hasNext()) {
/*     */       
/* 148 */       ChunkCoordIntPair var3 = var2.next();
/*     */       
/* 150 */       if (!this.previousActiveChunkSet.contains(var3)) {
/*     */         
/* 152 */         int var4 = var3.chunkXPos * 16;
/* 153 */         int var5 = var3.chunkZPos * 16;
/* 154 */         this.theProfiler.startSection("getChunk");
/* 155 */         Chunk var6 = getChunkFromChunkCoords(var3.chunkXPos, var3.chunkZPos);
/* 156 */         func_147467_a(var4, var5, var6);
/* 157 */         this.theProfiler.endSection();
/* 158 */         this.previousActiveChunkSet.add(var3);
/* 159 */         var1++;
/*     */         
/* 161 */         if (var1 >= 10) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doPreChunk(int p_73025_1_, int p_73025_2_, boolean p_73025_3_) {
/* 171 */     if (p_73025_3_) {
/*     */       
/* 173 */       this.clientChunkProvider.loadChunk(p_73025_1_, p_73025_2_);
/*     */     }
/*     */     else {
/*     */       
/* 177 */       this.clientChunkProvider.unloadChunk(p_73025_1_, p_73025_2_);
/*     */     } 
/*     */     
/* 180 */     if (!p_73025_3_)
/*     */     {
/* 182 */       markBlockRangeForRenderUpdate(p_73025_1_ * 16, 0, p_73025_2_ * 16, p_73025_1_ * 16 + 15, 256, p_73025_2_ * 16 + 15);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean spawnEntityInWorld(Entity p_72838_1_) {
/* 191 */     boolean var2 = super.spawnEntityInWorld(p_72838_1_);
/* 192 */     this.entityList.add(p_72838_1_);
/*     */     
/* 194 */     if (!var2) {
/*     */       
/* 196 */       this.entitySpawnQueue.add(p_72838_1_);
/*     */     }
/* 198 */     else if (p_72838_1_ instanceof EntityMinecart) {
/*     */       
/* 200 */       this.mc.getSoundHandler().playSound((ISound)new MovingSoundMinecart((EntityMinecart)p_72838_1_));
/*     */     } 
/*     */     
/* 203 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeEntity(Entity p_72900_1_) {
/* 211 */     super.removeEntity(p_72900_1_);
/* 212 */     this.entityList.remove(p_72900_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onEntityAdded(Entity p_72923_1_) {
/* 217 */     super.onEntityAdded(p_72923_1_);
/*     */     
/* 219 */     if (this.entitySpawnQueue.contains(p_72923_1_))
/*     */     {
/* 221 */       this.entitySpawnQueue.remove(p_72923_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void onEntityRemoved(Entity p_72847_1_) {
/* 227 */     super.onEntityRemoved(p_72847_1_);
/* 228 */     boolean var2 = false;
/*     */     
/* 230 */     if (this.entityList.contains(p_72847_1_))
/*     */     {
/* 232 */       if (p_72847_1_.isEntityAlive()) {
/*     */         
/* 234 */         this.entitySpawnQueue.add(p_72847_1_);
/* 235 */         var2 = true;
/*     */       }
/*     */       else {
/*     */         
/* 239 */         this.entityList.remove(p_72847_1_);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntityToWorld(int p_73027_1_, Entity p_73027_2_) {
/* 249 */     Entity var3 = getEntityByID(p_73027_1_);
/*     */     
/* 251 */     if (var3 != null)
/*     */     {
/* 253 */       removeEntity(var3);
/*     */     }
/*     */     
/* 256 */     this.entityList.add(p_73027_2_);
/* 257 */     p_73027_2_.setEntityId(p_73027_1_);
/*     */     
/* 259 */     if (!spawnEntityInWorld(p_73027_2_))
/*     */     {
/* 261 */       this.entitySpawnQueue.add(p_73027_2_);
/*     */     }
/*     */     
/* 264 */     this.entitiesById.addKey(p_73027_1_, p_73027_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getEntityByID(int p_73045_1_) {
/* 272 */     return (p_73045_1_ == this.mc.thePlayer.getEntityId()) ? (Entity)this.mc.thePlayer : super.getEntityByID(p_73045_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity removeEntityFromWorld(int p_73028_1_) {
/* 277 */     Entity var2 = (Entity)this.entitiesById.removeObject(p_73028_1_);
/*     */     
/* 279 */     if (var2 != null) {
/*     */       
/* 281 */       this.entityList.remove(var2);
/* 282 */       removeEntity(var2);
/*     */     } 
/*     */     
/* 285 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180503_b(BlockPos p_180503_1_, IBlockState p_180503_2_) {
/* 290 */     int var3 = p_180503_1_.getX();
/* 291 */     int var4 = p_180503_1_.getY();
/* 292 */     int var5 = p_180503_1_.getZ();
/* 293 */     invalidateBlockReceiveRegion(var3, var4, var5, var3, var4, var5);
/* 294 */     return super.setBlockState(p_180503_1_, p_180503_2_, 3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendQuittingDisconnectingPacket() {
/* 302 */     this.sendQueue.getNetworkManager().closeChannel((IChatComponent)new ChatComponentText("Quitting"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateWeather() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getRenderDistanceChunks() {
/* 312 */     return this.mc.gameSettings.renderDistanceChunks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doVoidFogParticles(int p_73029_1_, int p_73029_2_, int p_73029_3_) {
/* 317 */     byte var4 = 16;
/* 318 */     Random var5 = new Random();
/* 319 */     ItemStack var6 = this.mc.thePlayer.getHeldItem();
/* 320 */     boolean var7 = (this.mc.playerController.func_178889_l() == WorldSettings.GameType.CREATIVE && var6 != null && Block.getBlockFromItem(var6.getItem()) == Blocks.barrier);
/* 321 */     BlockPosM blockPosM = this.randomTickPosM;
/*     */     
/* 323 */     for (int var8 = 0; var8 < 1000; var8++) {
/*     */       
/* 325 */       int var9 = p_73029_1_ + this.rand.nextInt(var4) - this.rand.nextInt(var4);
/* 326 */       int var10 = p_73029_2_ + this.rand.nextInt(var4) - this.rand.nextInt(var4);
/* 327 */       int var11 = p_73029_3_ + this.rand.nextInt(var4) - this.rand.nextInt(var4);
/* 328 */       blockPosM.setXyz(var9, var10, var11);
/* 329 */       IBlockState var13 = getBlockState((BlockPos)blockPosM);
/* 330 */       var13.getBlock().randomDisplayTick(this, (BlockPos)blockPosM, var13, var5);
/*     */       
/* 332 */       if (var7 && var13.getBlock() == Blocks.barrier)
/*     */       {
/* 334 */         spawnParticle(EnumParticleTypes.BARRIER, (var9 + 0.5F), (var10 + 0.5F), (var11 + 0.5F), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllEntities() {
/* 344 */     this.loadedEntityList.removeAll(this.unloadedEntityList);
/*     */ 
/*     */     
/*     */     int var1;
/*     */ 
/*     */     
/* 350 */     for (var1 = 0; var1 < this.unloadedEntityList.size(); var1++) {
/*     */       
/* 352 */       Entity var2 = this.unloadedEntityList.get(var1);
/* 353 */       int var3 = var2.chunkCoordX;
/* 354 */       int var4 = var2.chunkCoordZ;
/*     */       
/* 356 */       if (var2.addedToChunk && isChunkLoaded(var3, var4, true))
/*     */       {
/* 358 */         getChunkFromChunkCoords(var3, var4).removeEntity(var2);
/*     */       }
/*     */     } 
/*     */     
/* 362 */     for (var1 = 0; var1 < this.unloadedEntityList.size(); var1++)
/*     */     {
/* 364 */       onEntityRemoved(this.unloadedEntityList.get(var1));
/*     */     }
/*     */     
/* 367 */     this.unloadedEntityList.clear();
/*     */     
/* 369 */     for (var1 = 0; var1 < this.loadedEntityList.size(); var1++) {
/*     */       
/* 371 */       Entity var2 = this.loadedEntityList.get(var1);
/*     */       
/* 373 */       if (var2.ridingEntity != null) {
/*     */         
/* 375 */         if (!var2.ridingEntity.isDead && var2.ridingEntity.riddenByEntity == var2) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 380 */         var2.ridingEntity.riddenByEntity = null;
/* 381 */         var2.ridingEntity = null;
/*     */       } 
/*     */       
/* 384 */       if (var2.isDead) {
/*     */         
/* 386 */         int var3 = var2.chunkCoordX;
/* 387 */         int var4 = var2.chunkCoordZ;
/*     */         
/* 389 */         if (var2.addedToChunk && isChunkLoaded(var3, var4, true))
/*     */         {
/* 391 */           getChunkFromChunkCoords(var3, var4).removeEntity(var2);
/*     */         }
/*     */         
/* 394 */         this.loadedEntityList.remove(var1--);
/* 395 */         onEntityRemoved(var2);
/*     */       } 
/*     */       continue;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReportCategory addWorldInfoToCrashReport(CrashReport report) {
/* 405 */     CrashReportCategory var2 = super.addWorldInfoToCrashReport(report);
/* 406 */     var2.addCrashSectionCallable("Forced entities", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000883";
/*     */           
/*     */           public String call() {
/* 411 */             return String.valueOf(WorldClient.this.entityList.size()) + " total; " + WorldClient.this.entityList.toString();
/*     */           }
/*     */         });
/* 414 */     var2.addCrashSectionCallable("Retry entities", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000884";
/*     */           
/*     */           public String call() {
/* 419 */             return String.valueOf(WorldClient.this.entitySpawnQueue.size()) + " total; " + WorldClient.this.entitySpawnQueue.toString();
/*     */           }
/*     */         });
/* 422 */     var2.addCrashSectionCallable("Server brand", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000885";
/*     */           
/*     */           public String call() {
/* 427 */             return WorldClient.this.mc.thePlayer.getClientBrand();
/*     */           }
/*     */         });
/* 430 */     var2.addCrashSectionCallable("Server type", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000886";
/*     */           
/*     */           public String call() {
/* 435 */             return (WorldClient.this.mc.getIntegratedServer() == null) ? "Non-integrated multiplayer server" : "Integrated singleplayer server";
/*     */           }
/*     */         });
/* 438 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175731_a(BlockPos p_175731_1_, String p_175731_2_, float p_175731_3_, float p_175731_4_, boolean p_175731_5_) {
/* 443 */     playSound(p_175731_1_.getX() + 0.5D, p_175731_1_.getY() + 0.5D, p_175731_1_.getZ() + 0.5D, p_175731_2_, p_175731_3_, p_175731_4_, p_175731_5_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void playSound(double x, double y, double z, String soundName, float volume, float pitch, boolean distanceDelay) {
/* 451 */     double var11 = this.mc.func_175606_aa().getDistanceSq(x, y, z);
/* 452 */     PositionedSoundRecord var13 = new PositionedSoundRecord(new ResourceLocation(soundName), volume, pitch, (float)x, (float)y, (float)z);
/*     */     
/* 454 */     if (distanceDelay && var11 > 100.0D) {
/*     */       
/* 456 */       double var14 = Math.sqrt(var11) / 40.0D;
/* 457 */       this.mc.getSoundHandler().playDelayedSound((ISound)var13, (int)(var14 * 20.0D));
/*     */     }
/*     */     else {
/*     */       
/* 461 */       this.mc.getSoundHandler().playSound((ISound)var13);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void makeFireworks(double x, double y, double z, double motionX, double motionY, double motionZ, NBTTagCompound compund) {
/* 467 */     this.mc.effectRenderer.addEffect((EntityFX)new EntityFireworkStarterFX(this, x, y, z, motionX, motionY, motionZ, this.mc.effectRenderer, compund));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorldScoreboard(Scoreboard p_96443_1_) {
/* 472 */     this.worldScoreboard = p_96443_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldTime(long time) {
/* 480 */     if (time < 0L) {
/*     */       
/* 482 */       time = -time;
/* 483 */       getGameRules().setOrCreateGameRule("doDaylightCycle", "false");
/*     */     }
/*     */     else {
/*     */       
/* 487 */       getGameRules().setOrCreateGameRule("doDaylightCycle", "true");
/*     */     } 
/*     */     
/* 490 */     super.setWorldTime(time);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCombinedLight(BlockPos pos, int lightValue) {
/* 495 */     int combinedLight = super.getCombinedLight(pos, lightValue);
/*     */     
/* 497 */     if (Config.isDynamicLights())
/*     */     {
/* 499 */       combinedLight = DynamicLights.getCombinedLight(pos, combinedLight);
/*     */     }
/*     */     
/* 502 */     return combinedLight;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setBlockState(BlockPos pos, IBlockState newState, int flags) {
/* 507 */     this.playerUpdate = isPlayerActing();
/* 508 */     boolean res = super.setBlockState(pos, newState, flags);
/* 509 */     this.playerUpdate = false;
/* 510 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isPlayerActing() {
/* 515 */     if (this.mc.playerController instanceof PlayerControllerOF) {
/*     */       
/* 517 */       PlayerControllerOF pcof = (PlayerControllerOF)this.mc.playerController;
/* 518 */       return pcof.isActing();
/*     */     } 
/*     */ 
/*     */     
/* 522 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPlayerUpdate() {
/* 528 */     return this.playerUpdate;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\multiplayer\WorldClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */