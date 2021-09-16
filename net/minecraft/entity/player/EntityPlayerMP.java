/*      */ package net.minecraft.entity.player;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Sets;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import io.netty.buffer.Unpooled;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.IMerchant;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.projectile.EntityArrow;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.inventory.ContainerChest;
/*      */ import net.minecraft.inventory.ContainerHorseInventory;
/*      */ import net.minecraft.inventory.ContainerMerchant;
/*      */ import net.minecraft.inventory.ICrafting;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.inventory.InventoryMerchant;
/*      */ import net.minecraft.item.EnumAction;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemMapBase;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.network.NetHandlerPlayServer;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.PacketBuffer;
/*      */ import net.minecraft.network.play.client.C15PacketClientSettings;
/*      */ import net.minecraft.network.play.server.S02PacketChat;
/*      */ import net.minecraft.network.play.server.S06PacketUpdateHealth;
/*      */ import net.minecraft.network.play.server.S0APacketUseBed;
/*      */ import net.minecraft.network.play.server.S0BPacketAnimation;
/*      */ import net.minecraft.network.play.server.S13PacketDestroyEntities;
/*      */ import net.minecraft.network.play.server.S19PacketEntityStatus;
/*      */ import net.minecraft.network.play.server.S1BPacketEntityAttach;
/*      */ import net.minecraft.network.play.server.S1DPacketEntityEffect;
/*      */ import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
/*      */ import net.minecraft.network.play.server.S1FPacketSetExperience;
/*      */ import net.minecraft.network.play.server.S21PacketChunkData;
/*      */ import net.minecraft.network.play.server.S26PacketMapChunkBulk;
/*      */ import net.minecraft.network.play.server.S29PacketSoundEffect;
/*      */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*      */ import net.minecraft.network.play.server.S2DPacketOpenWindow;
/*      */ import net.minecraft.network.play.server.S2EPacketCloseWindow;
/*      */ import net.minecraft.network.play.server.S2FPacketSetSlot;
/*      */ import net.minecraft.network.play.server.S30PacketWindowItems;
/*      */ import net.minecraft.network.play.server.S31PacketWindowProperty;
/*      */ import net.minecraft.network.play.server.S36PacketSignEditorOpen;
/*      */ import net.minecraft.network.play.server.S39PacketPlayerAbilities;
/*      */ import net.minecraft.network.play.server.S3FPacketCustomPayload;
/*      */ import net.minecraft.network.play.server.S42PacketCombatEvent;
/*      */ import net.minecraft.network.play.server.S43PacketCamera;
/*      */ import net.minecraft.network.play.server.S48PacketResourcePackSend;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.scoreboard.IScoreObjectiveCriteria;
/*      */ import net.minecraft.scoreboard.Score;
/*      */ import net.minecraft.scoreboard.ScoreObjective;
/*      */ import net.minecraft.scoreboard.Team;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.server.management.ItemInWorldManager;
/*      */ import net.minecraft.server.management.UserListOpsEntry;
/*      */ import net.minecraft.stats.AchievementList;
/*      */ import net.minecraft.stats.StatBase;
/*      */ import net.minecraft.stats.StatList;
/*      */ import net.minecraft.stats.StatisticsFile;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.tileentity.TileEntitySign;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.IJsonSerializable;
/*      */ import net.minecraft.util.JsonSerializableSet;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.village.MerchantRecipeList;
/*      */ import net.minecraft.world.ChunkCoordIntPair;
/*      */ import net.minecraft.world.IInteractionObject;
/*      */ import net.minecraft.world.ILockableContainer;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.WorldSettings;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraft.world.chunk.Chunk;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ 
/*      */ public class EntityPlayerMP
/*      */   extends EntityPlayer
/*      */   implements ICrafting
/*      */ {
/*  104 */   private static final Logger logger = LogManager.getLogger();
/*  105 */   private String translator = "en_US";
/*      */ 
/*      */ 
/*      */   
/*      */   public NetHandlerPlayServer playerNetServerHandler;
/*      */ 
/*      */ 
/*      */   
/*      */   public final MinecraftServer mcServer;
/*      */ 
/*      */   
/*      */   public final ItemInWorldManager theItemInWorldManager;
/*      */ 
/*      */   
/*      */   public double managedPosX;
/*      */ 
/*      */   
/*      */   public double managedPosZ;
/*      */ 
/*      */   
/*  125 */   public final List loadedChunks = Lists.newLinkedList();
/*      */ 
/*      */   
/*  128 */   private final List destroyedItemsNetCache = Lists.newLinkedList();
/*      */   private final StatisticsFile statsFile;
/*  130 */   private float field_130068_bO = Float.MIN_VALUE;
/*      */ 
/*      */   
/*  133 */   private float lastHealth = -1.0E8F;
/*      */ 
/*      */   
/*  136 */   private int lastFoodLevel = -99999999;
/*      */ 
/*      */   
/*      */   private boolean wasHungry = true;
/*      */ 
/*      */   
/*  142 */   private int lastExperience = -99999999;
/*  143 */   private int respawnInvulnerabilityTicks = 60;
/*      */   private EntityPlayer.EnumChatVisibility chatVisibility;
/*      */   private boolean chatColours = true;
/*  146 */   private long playerLastActiveTime = System.currentTimeMillis();
/*  147 */   private Entity field_175401_bS = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private int currentWindowId;
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChangingQuantityOnly;
/*      */ 
/*      */ 
/*      */   
/*      */   public int ping;
/*      */ 
/*      */   
/*      */   public boolean playerConqueredTheEnd;
/*      */ 
/*      */   
/*      */   private static final String __OBFID = "CL_00001440";
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityPlayerMP(MinecraftServer server, WorldServer worldIn, GameProfile profile, ItemInWorldManager interactionManager) {
/*  170 */     super((World)worldIn, profile);
/*  171 */     interactionManager.thisPlayerMP = this;
/*  172 */     this.theItemInWorldManager = interactionManager;
/*  173 */     BlockPos var5 = worldIn.getSpawnPoint();
/*      */     
/*  175 */     if (!worldIn.provider.getHasNoSky() && worldIn.getWorldInfo().getGameType() != WorldSettings.GameType.ADVENTURE) {
/*      */       
/*  177 */       int var6 = Math.max(5, server.getSpawnProtectionSize() - 6);
/*  178 */       int var7 = MathHelper.floor_double(worldIn.getWorldBorder().getClosestDistance(var5.getX(), var5.getZ()));
/*      */       
/*  180 */       if (var7 < var6)
/*      */       {
/*  182 */         var6 = var7;
/*      */       }
/*      */       
/*  185 */       if (var7 <= 1)
/*      */       {
/*  187 */         var6 = 1;
/*      */       }
/*      */       
/*  190 */       var5 = worldIn.func_175672_r(var5.add(this.rand.nextInt(var6 * 2) - var6, 0, this.rand.nextInt(var6 * 2) - var6));
/*      */     } 
/*      */     
/*  193 */     this.mcServer = server;
/*  194 */     this.statsFile = server.getConfigurationManager().getPlayerStatsFile(this);
/*  195 */     this.stepHeight = 0.0F;
/*  196 */     func_174828_a(var5, 0.0F, 0.0F);
/*      */     
/*  198 */     while (!worldIn.getCollidingBoundingBoxes((Entity)this, getEntityBoundingBox()).isEmpty() && this.posY < 255.0D)
/*      */     {
/*  200 */       setPosition(this.posX, this.posY + 1.0D, this.posZ);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  209 */     super.readEntityFromNBT(tagCompund);
/*      */     
/*  211 */     if (tagCompund.hasKey("playerGameType", 99))
/*      */     {
/*  213 */       if (MinecraftServer.getServer().getForceGamemode()) {
/*      */         
/*  215 */         this.theItemInWorldManager.setGameType(MinecraftServer.getServer().getGameType());
/*      */       }
/*      */       else {
/*      */         
/*  219 */         this.theItemInWorldManager.setGameType(WorldSettings.GameType.getByID(tagCompund.getInteger("playerGameType")));
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  229 */     super.writeEntityToNBT(tagCompound);
/*  230 */     tagCompound.setInteger("playerGameType", this.theItemInWorldManager.getGameType().getID());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addExperienceLevel(int p_82242_1_) {
/*  238 */     super.addExperienceLevel(p_82242_1_);
/*  239 */     this.lastExperience = -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_71013_b(int p_71013_1_) {
/*  244 */     super.func_71013_b(p_71013_1_);
/*  245 */     this.lastExperience = -1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addSelfToInternalCraftingInventory() {
/*  250 */     this.openContainer.onCraftGuiOpened(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152111_bt() {
/*  255 */     super.func_152111_bt();
/*  256 */     this.playerNetServerHandler.sendPacket((Packet)new S42PacketCombatEvent(getCombatTracker(), S42PacketCombatEvent.Event.ENTER_COMBAT));
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152112_bu() {
/*  261 */     super.func_152112_bu();
/*  262 */     this.playerNetServerHandler.sendPacket((Packet)new S42PacketCombatEvent(getCombatTracker(), S42PacketCombatEvent.Event.END_COMBAT));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*  270 */     this.theItemInWorldManager.updateBlockRemoving();
/*  271 */     this.respawnInvulnerabilityTicks--;
/*      */     
/*  273 */     if (this.hurtResistantTime > 0)
/*      */     {
/*  275 */       this.hurtResistantTime--;
/*      */     }
/*      */     
/*  278 */     this.openContainer.detectAndSendChanges();
/*      */     
/*  280 */     if (!this.worldObj.isRemote && !this.openContainer.canInteractWith(this)) {
/*      */       
/*  282 */       closeScreen();
/*  283 */       this.openContainer = this.inventoryContainer;
/*      */     } 
/*      */     
/*  286 */     while (!this.destroyedItemsNetCache.isEmpty()) {
/*      */       
/*  288 */       int var1 = Math.min(this.destroyedItemsNetCache.size(), 2147483647);
/*  289 */       int[] var2 = new int[var1];
/*  290 */       Iterator var3 = this.destroyedItemsNetCache.iterator();
/*  291 */       int var4 = 0;
/*      */       
/*  293 */       while (var3.hasNext() && var4 < var1) {
/*      */         
/*  295 */         var2[var4++] = ((Integer)var3.next()).intValue();
/*  296 */         var3.remove();
/*      */       } 
/*      */       
/*  299 */       this.playerNetServerHandler.sendPacket((Packet)new S13PacketDestroyEntities(var2));
/*      */     } 
/*      */     
/*  302 */     if (!this.loadedChunks.isEmpty()) {
/*      */       
/*  304 */       ArrayList<Chunk> var6 = Lists.newArrayList();
/*  305 */       Iterator<ChunkCoordIntPair> var8 = this.loadedChunks.iterator();
/*  306 */       ArrayList var9 = Lists.newArrayList();
/*      */ 
/*      */       
/*  309 */       while (var8.hasNext() && var6.size() < 10) {
/*      */         
/*  311 */         ChunkCoordIntPair var10 = var8.next();
/*      */         
/*  313 */         if (var10 != null) {
/*      */           
/*  315 */           if (this.worldObj.isBlockLoaded(new BlockPos(var10.chunkXPos << 4, 0, var10.chunkZPos << 4))) {
/*      */             
/*  317 */             Chunk var5 = this.worldObj.getChunkFromChunkCoords(var10.chunkXPos, var10.chunkZPos);
/*      */             
/*  319 */             if (var5.isPopulated()) {
/*      */               
/*  321 */               var6.add(var5);
/*  322 */               var9.addAll(((WorldServer)this.worldObj).func_147486_a(var10.chunkXPos * 16, 0, var10.chunkZPos * 16, var10.chunkXPos * 16 + 16, 256, var10.chunkZPos * 16 + 16));
/*  323 */               var8.remove();
/*      */             } 
/*      */           } 
/*      */           
/*      */           continue;
/*      */         } 
/*  329 */         var8.remove();
/*      */       } 
/*      */ 
/*      */       
/*  333 */       if (!var6.isEmpty()) {
/*      */         
/*  335 */         if (var6.size() == 1) {
/*      */           
/*  337 */           this.playerNetServerHandler.sendPacket((Packet)new S21PacketChunkData(var6.get(0), true, 65535));
/*      */         }
/*      */         else {
/*      */           
/*  341 */           this.playerNetServerHandler.sendPacket((Packet)new S26PacketMapChunkBulk(var6));
/*      */         } 
/*      */         
/*  344 */         Iterator<TileEntity> var11 = var9.iterator();
/*      */         
/*  346 */         while (var11.hasNext()) {
/*      */           
/*  348 */           TileEntity var12 = var11.next();
/*  349 */           sendTileEntityUpdate(var12);
/*      */         } 
/*      */         
/*  352 */         var11 = (Iterator)var6.iterator();
/*      */         
/*  354 */         while (var11.hasNext()) {
/*      */           
/*  356 */           Chunk var5 = (Chunk)var11.next();
/*  357 */           getServerForPlayer().getEntityTracker().func_85172_a(this, var5);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  362 */     Entity var7 = func_175398_C();
/*      */     
/*  364 */     if (var7 != this)
/*      */     {
/*  366 */       if (!var7.isEntityAlive()) {
/*      */         
/*  368 */         func_175399_e((Entity)this);
/*      */       }
/*      */       else {
/*      */         
/*  372 */         setPositionAndRotation(var7.posX, var7.posY, var7.posZ, var7.rotationYaw, var7.rotationPitch);
/*  373 */         this.mcServer.getConfigurationManager().serverUpdateMountedMovingPlayer(this);
/*      */         
/*  375 */         if (isSneaking())
/*      */         {
/*  377 */           func_175399_e((Entity)this);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdateEntity() {
/*      */     try {
/*  387 */       super.onUpdate();
/*      */       
/*  389 */       for (int var1 = 0; var1 < this.inventory.getSizeInventory(); var1++) {
/*      */         
/*  391 */         ItemStack var6 = this.inventory.getStackInSlot(var1);
/*      */         
/*  393 */         if (var6 != null && var6.getItem().isMap()) {
/*      */           
/*  395 */           Packet var8 = ((ItemMapBase)var6.getItem()).createMapDataPacket(var6, this.worldObj, this);
/*      */           
/*  397 */           if (var8 != null)
/*      */           {
/*  399 */             this.playerNetServerHandler.sendPacket(var8);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  404 */       if (getHealth() != this.lastHealth || this.lastFoodLevel != this.foodStats.getFoodLevel() || ((this.foodStats.getSaturationLevel() == 0.0F)) != this.wasHungry) {
/*      */         
/*  406 */         this.playerNetServerHandler.sendPacket((Packet)new S06PacketUpdateHealth(getHealth(), this.foodStats.getFoodLevel(), this.foodStats.getSaturationLevel()));
/*  407 */         this.lastHealth = getHealth();
/*  408 */         this.lastFoodLevel = this.foodStats.getFoodLevel();
/*  409 */         this.wasHungry = (this.foodStats.getSaturationLevel() == 0.0F);
/*      */       } 
/*      */       
/*  412 */       if (getHealth() + getAbsorptionAmount() != this.field_130068_bO) {
/*      */         
/*  414 */         this.field_130068_bO = getHealth() + getAbsorptionAmount();
/*  415 */         Collection var5 = getWorldScoreboard().func_96520_a(IScoreObjectiveCriteria.health);
/*  416 */         Iterator<ScoreObjective> var7 = var5.iterator();
/*      */         
/*  418 */         while (var7.hasNext()) {
/*      */           
/*  420 */           ScoreObjective var9 = var7.next();
/*  421 */           getWorldScoreboard().getValueFromObjective(getName(), var9).func_96651_a(Arrays.asList(new EntityPlayer[] { this }));
/*      */         } 
/*      */       } 
/*      */       
/*  425 */       if (this.experienceTotal != this.lastExperience) {
/*      */         
/*  427 */         this.lastExperience = this.experienceTotal;
/*  428 */         this.playerNetServerHandler.sendPacket((Packet)new S1FPacketSetExperience(this.experience, this.experienceTotal, this.experienceLevel));
/*      */       } 
/*      */       
/*  431 */       if (this.ticksExisted % 20 * 5 == 0 && !getStatFile().hasAchievementUnlocked(AchievementList.exploreAllBiomes))
/*      */       {
/*  433 */         func_147098_j();
/*      */       }
/*      */     }
/*  436 */     catch (Throwable var4) {
/*      */       
/*  438 */       CrashReport var2 = CrashReport.makeCrashReport(var4, "Ticking player");
/*  439 */       CrashReportCategory var3 = var2.makeCategory("Player being ticked");
/*  440 */       addEntityCrashInfo(var3);
/*  441 */       throw new ReportedException(var2);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_147098_j() {
/*  447 */     BiomeGenBase var1 = this.worldObj.getBiomeGenForCoords(new BlockPos(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ)));
/*  448 */     String var2 = var1.biomeName;
/*  449 */     JsonSerializableSet var3 = (JsonSerializableSet)getStatFile().func_150870_b((StatBase)AchievementList.exploreAllBiomes);
/*      */     
/*  451 */     if (var3 == null)
/*      */     {
/*  453 */       var3 = (JsonSerializableSet)getStatFile().func_150872_a((StatBase)AchievementList.exploreAllBiomes, (IJsonSerializable)new JsonSerializableSet());
/*      */     }
/*      */     
/*  456 */     var3.add(var2);
/*      */     
/*  458 */     if (getStatFile().canUnlockAchievement(AchievementList.exploreAllBiomes) && var3.size() >= BiomeGenBase.explorationBiomesList.size()) {
/*      */       
/*  460 */       HashSet var4 = Sets.newHashSet(BiomeGenBase.explorationBiomesList);
/*  461 */       Iterator<String> var5 = var3.iterator();
/*      */       
/*  463 */       while (var5.hasNext()) {
/*      */         
/*  465 */         String var6 = var5.next();
/*  466 */         Iterator<BiomeGenBase> var7 = var4.iterator();
/*      */         
/*  468 */         while (var7.hasNext()) {
/*      */           
/*  470 */           BiomeGenBase var8 = var7.next();
/*      */           
/*  472 */           if (var8.biomeName.equals(var6))
/*      */           {
/*  474 */             var7.remove();
/*      */           }
/*      */         } 
/*      */         
/*  478 */         if (var4.isEmpty()) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  484 */       if (var4.isEmpty())
/*      */       {
/*  486 */         triggerAchievement((StatBase)AchievementList.exploreAllBiomes);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource cause) {
/*  496 */     if (this.worldObj.getGameRules().getGameRuleBooleanValue("showDeathMessages")) {
/*      */       
/*  498 */       Team var2 = getTeam();
/*      */       
/*  500 */       if (var2 != null && var2.func_178771_j() != Team.EnumVisible.ALWAYS) {
/*      */         
/*  502 */         if (var2.func_178771_j() == Team.EnumVisible.HIDE_FOR_OTHER_TEAMS)
/*      */         {
/*  504 */           this.mcServer.getConfigurationManager().func_177453_a(this, getCombatTracker().func_151521_b());
/*      */         }
/*  506 */         else if (var2.func_178771_j() == Team.EnumVisible.HIDE_FOR_OWN_TEAM)
/*      */         {
/*  508 */           this.mcServer.getConfigurationManager().func_177452_b(this, getCombatTracker().func_151521_b());
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  513 */         this.mcServer.getConfigurationManager().sendChatMsg(getCombatTracker().func_151521_b());
/*      */       } 
/*      */     } 
/*      */     
/*  517 */     if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
/*      */     {
/*  519 */       this.inventory.dropAllItems();
/*      */     }
/*      */     
/*  522 */     Collection var6 = this.worldObj.getScoreboard().func_96520_a(IScoreObjectiveCriteria.deathCount);
/*  523 */     Iterator<ScoreObjective> var3 = var6.iterator();
/*      */     
/*  525 */     while (var3.hasNext()) {
/*      */       
/*  527 */       ScoreObjective var4 = var3.next();
/*  528 */       Score var5 = getWorldScoreboard().getValueFromObjective(getName(), var4);
/*  529 */       var5.func_96648_a();
/*      */     } 
/*      */     
/*  532 */     EntityLivingBase var7 = func_94060_bK();
/*      */     
/*  534 */     if (var7 != null) {
/*      */       
/*  536 */       EntityList.EntityEggInfo var8 = (EntityList.EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(EntityList.getEntityID((Entity)var7)));
/*      */       
/*  538 */       if (var8 != null)
/*      */       {
/*  540 */         triggerAchievement(var8.field_151513_e);
/*      */       }
/*      */       
/*  543 */       var7.addToPlayerScore((Entity)this, this.scoreValue);
/*      */     } 
/*      */     
/*  546 */     triggerAchievement(StatList.deathsStat);
/*  547 */     func_175145_a(StatList.timeSinceDeathStat);
/*  548 */     getCombatTracker().func_94549_h();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  556 */     if (func_180431_b(source))
/*      */     {
/*  558 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  562 */     boolean var3 = (this.mcServer.isDedicatedServer() && func_175400_cq() && "fall".equals(source.damageType));
/*      */     
/*  564 */     if (!var3 && this.respawnInvulnerabilityTicks > 0 && source != DamageSource.outOfWorld)
/*      */     {
/*  566 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  570 */     if (source instanceof net.minecraft.util.EntityDamageSource) {
/*      */       
/*  572 */       Entity var4 = source.getEntity();
/*      */       
/*  574 */       if (var4 instanceof EntityPlayer && !canAttackPlayer((EntityPlayer)var4))
/*      */       {
/*  576 */         return false;
/*      */       }
/*      */       
/*  579 */       if (var4 instanceof EntityArrow) {
/*      */         
/*  581 */         EntityArrow var5 = (EntityArrow)var4;
/*      */         
/*  583 */         if (var5.shootingEntity instanceof EntityPlayer && !canAttackPlayer((EntityPlayer)var5.shootingEntity))
/*      */         {
/*  585 */           return false;
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  590 */     return super.attackEntityFrom(source, amount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canAttackPlayer(EntityPlayer other) {
/*  597 */     return !func_175400_cq() ? false : super.canAttackPlayer(other);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_175400_cq() {
/*  602 */     return this.mcServer.isPVPEnabled();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void travelToDimension(int dimensionId) {
/*  610 */     if (this.dimension == 1 && dimensionId == 1) {
/*      */       
/*  612 */       triggerAchievement((StatBase)AchievementList.theEnd2);
/*  613 */       this.worldObj.removeEntity((Entity)this);
/*  614 */       this.playerConqueredTheEnd = true;
/*  615 */       this.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(4, 0.0F));
/*      */     }
/*      */     else {
/*      */       
/*  619 */       if (this.dimension == 0 && dimensionId == 1) {
/*      */         
/*  621 */         triggerAchievement((StatBase)AchievementList.theEnd);
/*  622 */         BlockPos var2 = this.mcServer.worldServerForDimension(dimensionId).func_180504_m();
/*      */         
/*  624 */         if (var2 != null)
/*      */         {
/*  626 */           this.playerNetServerHandler.setPlayerLocation(var2.getX(), var2.getY(), var2.getZ(), 0.0F, 0.0F);
/*      */         }
/*      */         
/*  629 */         dimensionId = 1;
/*      */       }
/*      */       else {
/*      */         
/*  633 */         triggerAchievement((StatBase)AchievementList.portal);
/*      */       } 
/*      */       
/*  636 */       this.mcServer.getConfigurationManager().transferPlayerToDimension(this, dimensionId);
/*  637 */       this.lastExperience = -1;
/*  638 */       this.lastHealth = -1.0F;
/*  639 */       this.lastFoodLevel = -1;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_174827_a(EntityPlayerMP p_174827_1_) {
/*  645 */     return p_174827_1_.func_175149_v() ? ((func_175398_C() == this)) : (func_175149_v() ? false : super.func_174827_a(p_174827_1_));
/*      */   }
/*      */ 
/*      */   
/*      */   private void sendTileEntityUpdate(TileEntity p_147097_1_) {
/*  650 */     if (p_147097_1_ != null) {
/*      */       
/*  652 */       Packet var2 = p_147097_1_.getDescriptionPacket();
/*      */       
/*  654 */       if (var2 != null)
/*      */       {
/*  656 */         this.playerNetServerHandler.sendPacket(var2);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onItemPickup(Entity p_71001_1_, int p_71001_2_) {
/*  666 */     super.onItemPickup(p_71001_1_, p_71001_2_);
/*  667 */     this.openContainer.detectAndSendChanges();
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer.EnumStatus func_180469_a(BlockPos p_180469_1_) {
/*  672 */     EntityPlayer.EnumStatus var2 = super.func_180469_a(p_180469_1_);
/*      */     
/*  674 */     if (var2 == EntityPlayer.EnumStatus.OK) {
/*      */       
/*  676 */       S0APacketUseBed var3 = new S0APacketUseBed(this, p_180469_1_);
/*  677 */       getServerForPlayer().getEntityTracker().sendToAllTrackingEntity((Entity)this, (Packet)var3);
/*  678 */       this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/*  679 */       this.playerNetServerHandler.sendPacket((Packet)var3);
/*      */     } 
/*      */     
/*  682 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void wakeUpPlayer(boolean p_70999_1_, boolean updateWorldFlag, boolean setSpawn) {
/*  690 */     if (isPlayerSleeping())
/*      */     {
/*  692 */       getServerForPlayer().getEntityTracker().func_151248_b((Entity)this, (Packet)new S0BPacketAnimation((Entity)this, 2));
/*      */     }
/*      */     
/*  695 */     super.wakeUpPlayer(p_70999_1_, updateWorldFlag, setSpawn);
/*      */     
/*  697 */     if (this.playerNetServerHandler != null)
/*      */     {
/*  699 */       this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mountEntity(Entity entityIn) {
/*  708 */     Entity var2 = this.ridingEntity;
/*  709 */     super.mountEntity(entityIn);
/*      */     
/*  711 */     if (entityIn != var2) {
/*      */       
/*  713 */       this.playerNetServerHandler.sendPacket((Packet)new S1BPacketEntityAttach(0, (Entity)this, this.ridingEntity));
/*  714 */       this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleFalling(double p_71122_1_, boolean p_71122_3_) {
/*  725 */     int var4 = MathHelper.floor_double(this.posX);
/*  726 */     int var5 = MathHelper.floor_double(this.posY - 0.20000000298023224D);
/*  727 */     int var6 = MathHelper.floor_double(this.posZ);
/*  728 */     BlockPos var7 = new BlockPos(var4, var5, var6);
/*  729 */     Block var8 = this.worldObj.getBlockState(var7).getBlock();
/*      */     
/*  731 */     if (var8.getMaterial() == Material.air) {
/*      */       
/*  733 */       Block var9 = this.worldObj.getBlockState(var7.offsetDown()).getBlock();
/*      */       
/*  735 */       if (var9 instanceof net.minecraft.block.BlockFence || var9 instanceof net.minecraft.block.BlockWall || var9 instanceof net.minecraft.block.BlockFenceGate) {
/*      */         
/*  737 */         var7 = var7.offsetDown();
/*  738 */         var8 = this.worldObj.getBlockState(var7).getBlock();
/*      */       } 
/*      */     } 
/*      */     
/*  742 */     super.func_180433_a(p_71122_1_, p_71122_3_, var8, var7);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175141_a(TileEntitySign p_175141_1_) {
/*  747 */     p_175141_1_.func_145912_a(this);
/*  748 */     this.playerNetServerHandler.sendPacket((Packet)new S36PacketSignEditorOpen(p_175141_1_.getPos()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getNextWindowId() {
/*  756 */     this.currentWindowId = this.currentWindowId % 100 + 1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void displayGui(IInteractionObject guiOwner) {
/*  761 */     getNextWindowId();
/*  762 */     this.playerNetServerHandler.sendPacket((Packet)new S2DPacketOpenWindow(this.currentWindowId, guiOwner.getGuiID(), guiOwner.getDisplayName()));
/*  763 */     this.openContainer = guiOwner.createContainer(this.inventory, this);
/*  764 */     this.openContainer.windowId = this.currentWindowId;
/*  765 */     this.openContainer.onCraftGuiOpened(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIChest(IInventory chestInventory) {
/*  773 */     if (this.openContainer != this.inventoryContainer)
/*      */     {
/*  775 */       closeScreen();
/*      */     }
/*      */     
/*  778 */     if (chestInventory instanceof ILockableContainer) {
/*      */       
/*  780 */       ILockableContainer var2 = (ILockableContainer)chestInventory;
/*      */       
/*  782 */       if (var2.isLocked() && !func_175146_a(var2.getLockCode()) && !func_175149_v()) {
/*      */         
/*  784 */         this.playerNetServerHandler.sendPacket((Packet)new S02PacketChat((IChatComponent)new ChatComponentTranslation("container.isLocked", new Object[] { chestInventory.getDisplayName() }), (byte)2));
/*  785 */         this.playerNetServerHandler.sendPacket((Packet)new S29PacketSoundEffect("random.door_close", this.posX, this.posY, this.posZ, 1.0F, 1.0F));
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/*  790 */     getNextWindowId();
/*      */     
/*  792 */     if (chestInventory instanceof IInteractionObject) {
/*      */       
/*  794 */       this.playerNetServerHandler.sendPacket((Packet)new S2DPacketOpenWindow(this.currentWindowId, ((IInteractionObject)chestInventory).getGuiID(), chestInventory.getDisplayName(), chestInventory.getSizeInventory()));
/*  795 */       this.openContainer = ((IInteractionObject)chestInventory).createContainer(this.inventory, this);
/*      */     }
/*      */     else {
/*      */       
/*  799 */       this.playerNetServerHandler.sendPacket((Packet)new S2DPacketOpenWindow(this.currentWindowId, "minecraft:container", chestInventory.getDisplayName(), chestInventory.getSizeInventory()));
/*  800 */       this.openContainer = (Container)new ContainerChest(this.inventory, chestInventory, this);
/*      */     } 
/*      */     
/*  803 */     this.openContainer.windowId = this.currentWindowId;
/*  804 */     this.openContainer.onCraftGuiOpened(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public void displayVillagerTradeGui(IMerchant villager) {
/*  809 */     getNextWindowId();
/*  810 */     this.openContainer = (Container)new ContainerMerchant(this.inventory, villager, this.worldObj);
/*  811 */     this.openContainer.windowId = this.currentWindowId;
/*  812 */     this.openContainer.onCraftGuiOpened(this);
/*  813 */     InventoryMerchant var2 = ((ContainerMerchant)this.openContainer).getMerchantInventory();
/*  814 */     IChatComponent var3 = villager.getDisplayName();
/*  815 */     this.playerNetServerHandler.sendPacket((Packet)new S2DPacketOpenWindow(this.currentWindowId, "minecraft:villager", var3, var2.getSizeInventory()));
/*  816 */     MerchantRecipeList var4 = villager.getRecipes(this);
/*      */     
/*  818 */     if (var4 != null) {
/*      */       
/*  820 */       PacketBuffer var5 = new PacketBuffer(Unpooled.buffer());
/*  821 */       var5.writeInt(this.currentWindowId);
/*  822 */       var4.func_151391_a(var5);
/*  823 */       this.playerNetServerHandler.sendPacket((Packet)new S3FPacketCustomPayload("MC|TrList", var5));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void displayGUIHorse(EntityHorse p_110298_1_, IInventory p_110298_2_) {
/*  829 */     if (this.openContainer != this.inventoryContainer)
/*      */     {
/*  831 */       closeScreen();
/*      */     }
/*      */     
/*  834 */     getNextWindowId();
/*  835 */     this.playerNetServerHandler.sendPacket((Packet)new S2DPacketOpenWindow(this.currentWindowId, "EntityHorse", p_110298_2_.getDisplayName(), p_110298_2_.getSizeInventory(), p_110298_1_.getEntityId()));
/*  836 */     this.openContainer = (Container)new ContainerHorseInventory(this.inventory, p_110298_2_, p_110298_1_, this);
/*  837 */     this.openContainer.windowId = this.currentWindowId;
/*  838 */     this.openContainer.onCraftGuiOpened(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void displayGUIBook(ItemStack bookStack) {
/*  846 */     Item var2 = bookStack.getItem();
/*      */     
/*  848 */     if (var2 == Items.written_book)
/*      */     {
/*  850 */       this.playerNetServerHandler.sendPacket((Packet)new S3FPacketCustomPayload("MC|BOpen", new PacketBuffer(Unpooled.buffer())));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendSlotContents(Container p_71111_1_, int p_71111_2_, ItemStack p_71111_3_) {
/*  860 */     if (!(p_71111_1_.getSlot(p_71111_2_) instanceof net.minecraft.inventory.SlotCrafting))
/*      */     {
/*  862 */       if (!this.isChangingQuantityOnly)
/*      */       {
/*  864 */         this.playerNetServerHandler.sendPacket((Packet)new S2FPacketSetSlot(p_71111_1_.windowId, p_71111_2_, p_71111_3_));
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void sendContainerToPlayer(Container p_71120_1_) {
/*  871 */     updateCraftingInventory(p_71120_1_, p_71120_1_.getInventory());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateCraftingInventory(Container p_71110_1_, List p_71110_2_) {
/*  879 */     this.playerNetServerHandler.sendPacket((Packet)new S30PacketWindowItems(p_71110_1_.windowId, p_71110_2_));
/*  880 */     this.playerNetServerHandler.sendPacket((Packet)new S2FPacketSetSlot(-1, -1, this.inventory.getItemStack()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendProgressBarUpdate(Container p_71112_1_, int p_71112_2_, int p_71112_3_) {
/*  890 */     this.playerNetServerHandler.sendPacket((Packet)new S31PacketWindowProperty(p_71112_1_.windowId, p_71112_2_, p_71112_3_));
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175173_a(Container p_175173_1_, IInventory p_175173_2_) {
/*  895 */     for (int var3 = 0; var3 < p_175173_2_.getFieldCount(); var3++)
/*      */     {
/*  897 */       this.playerNetServerHandler.sendPacket((Packet)new S31PacketWindowProperty(p_175173_1_.windowId, var3, p_175173_2_.getField(var3)));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeScreen() {
/*  906 */     this.playerNetServerHandler.sendPacket((Packet)new S2EPacketCloseWindow(this.openContainer.windowId));
/*  907 */     closeContainer();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateHeldItem() {
/*  915 */     if (!this.isChangingQuantityOnly)
/*      */     {
/*  917 */       this.playerNetServerHandler.sendPacket((Packet)new S2FPacketSetSlot(-1, -1, this.inventory.getItemStack()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeContainer() {
/*  926 */     this.openContainer.onContainerClosed(this);
/*  927 */     this.openContainer = this.inventoryContainer;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEntityActionState(float p_110430_1_, float p_110430_2_, boolean p_110430_3_, boolean p_110430_4_) {
/*  932 */     if (this.ridingEntity != null) {
/*      */       
/*  934 */       if (p_110430_1_ >= -1.0F && p_110430_1_ <= 1.0F)
/*      */       {
/*  936 */         this.moveStrafing = p_110430_1_;
/*      */       }
/*      */       
/*  939 */       if (p_110430_2_ >= -1.0F && p_110430_2_ <= 1.0F)
/*      */       {
/*  941 */         this.moveForward = p_110430_2_;
/*      */       }
/*      */       
/*  944 */       this.isJumping = p_110430_3_;
/*  945 */       setSneaking(p_110430_4_);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addStat(StatBase p_71064_1_, int p_71064_2_) {
/*  954 */     if (p_71064_1_ != null) {
/*      */       
/*  956 */       this.statsFile.func_150871_b(this, p_71064_1_, p_71064_2_);
/*  957 */       Iterator<ScoreObjective> var3 = getWorldScoreboard().func_96520_a(p_71064_1_.func_150952_k()).iterator();
/*      */       
/*  959 */       while (var3.hasNext()) {
/*      */         
/*  961 */         ScoreObjective var4 = var3.next();
/*  962 */         getWorldScoreboard().getValueFromObjective(getName(), var4).increseScore(p_71064_2_);
/*      */       } 
/*      */       
/*  965 */       if (this.statsFile.func_150879_e())
/*      */       {
/*  967 */         this.statsFile.func_150876_a(this);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175145_a(StatBase p_175145_1_) {
/*  974 */     if (p_175145_1_ != null) {
/*      */       
/*  976 */       this.statsFile.func_150873_a(this, p_175145_1_, 0);
/*  977 */       Iterator<ScoreObjective> var2 = getWorldScoreboard().func_96520_a(p_175145_1_.func_150952_k()).iterator();
/*      */       
/*  979 */       while (var2.hasNext()) {
/*      */         
/*  981 */         ScoreObjective var3 = var2.next();
/*  982 */         getWorldScoreboard().getValueFromObjective(getName(), var3).setScorePoints(0);
/*      */       } 
/*      */       
/*  985 */       if (this.statsFile.func_150879_e())
/*      */       {
/*  987 */         this.statsFile.func_150876_a(this);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void mountEntityAndWakeUp() {
/*  994 */     if (this.riddenByEntity != null)
/*      */     {
/*  996 */       this.riddenByEntity.mountEntity((Entity)this);
/*      */     }
/*      */     
/*  999 */     if (this.sleeping)
/*      */     {
/* 1001 */       wakeUpPlayer(true, false, false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPlayerHealthUpdated() {
/* 1011 */     this.lastHealth = -1.0E8F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addChatComponentMessage(IChatComponent p_146105_1_) {
/* 1016 */     this.playerNetServerHandler.sendPacket((Packet)new S02PacketChat(p_146105_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onItemUseFinish() {
/* 1024 */     this.playerNetServerHandler.sendPacket((Packet)new S19PacketEntityStatus((Entity)this, (byte)9));
/* 1025 */     super.onItemUseFinish();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItemInUse(ItemStack p_71008_1_, int p_71008_2_) {
/* 1033 */     super.setItemInUse(p_71008_1_, p_71008_2_);
/*      */     
/* 1035 */     if (p_71008_1_ != null && p_71008_1_.getItem() != null && p_71008_1_.getItem().getItemUseAction(p_71008_1_) == EnumAction.EAT)
/*      */     {
/* 1037 */       getServerForPlayer().getEntityTracker().func_151248_b((Entity)this, (Packet)new S0BPacketAnimation((Entity)this, 3));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clonePlayer(EntityPlayer p_71049_1_, boolean p_71049_2_) {
/* 1047 */     super.clonePlayer(p_71049_1_, p_71049_2_);
/* 1048 */     this.lastExperience = -1;
/* 1049 */     this.lastHealth = -1.0F;
/* 1050 */     this.lastFoodLevel = -1;
/* 1051 */     this.destroyedItemsNetCache.addAll(((EntityPlayerMP)p_71049_1_).destroyedItemsNetCache);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onNewPotionEffect(PotionEffect p_70670_1_) {
/* 1056 */     super.onNewPotionEffect(p_70670_1_);
/* 1057 */     this.playerNetServerHandler.sendPacket((Packet)new S1DPacketEntityEffect(getEntityId(), p_70670_1_));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onChangedPotionEffect(PotionEffect p_70695_1_, boolean p_70695_2_) {
/* 1062 */     super.onChangedPotionEffect(p_70695_1_, p_70695_2_);
/* 1063 */     this.playerNetServerHandler.sendPacket((Packet)new S1DPacketEntityEffect(getEntityId(), p_70695_1_));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onFinishedPotionEffect(PotionEffect p_70688_1_) {
/* 1068 */     super.onFinishedPotionEffect(p_70688_1_);
/* 1069 */     this.playerNetServerHandler.sendPacket((Packet)new S1EPacketRemoveEntityEffect(getEntityId(), p_70688_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndUpdate(double p_70634_1_, double p_70634_3_, double p_70634_5_) {
/* 1077 */     this.playerNetServerHandler.setPlayerLocation(p_70634_1_, p_70634_3_, p_70634_5_, this.rotationYaw, this.rotationPitch);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onCriticalHit(Entity p_71009_1_) {
/* 1085 */     getServerForPlayer().getEntityTracker().func_151248_b((Entity)this, (Packet)new S0BPacketAnimation(p_71009_1_, 4));
/*      */   }
/*      */ 
/*      */   
/*      */   public void onEnchantmentCritical(Entity p_71047_1_) {
/* 1090 */     getServerForPlayer().getEntityTracker().func_151248_b((Entity)this, (Packet)new S0BPacketAnimation(p_71047_1_, 5));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPlayerAbilities() {
/* 1098 */     if (this.playerNetServerHandler != null) {
/*      */       
/* 1100 */       this.playerNetServerHandler.sendPacket((Packet)new S39PacketPlayerAbilities(this.capabilities));
/* 1101 */       func_175135_B();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public WorldServer getServerForPlayer() {
/* 1107 */     return (WorldServer)this.worldObj;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGameType(WorldSettings.GameType gameType) {
/* 1115 */     this.theItemInWorldManager.setGameType(gameType);
/* 1116 */     this.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(3, gameType.getID()));
/*      */     
/* 1118 */     if (gameType == WorldSettings.GameType.SPECTATOR) {
/*      */       
/* 1120 */       mountEntity((Entity)null);
/*      */     }
/*      */     else {
/*      */       
/* 1124 */       func_175399_e((Entity)this);
/*      */     } 
/*      */     
/* 1127 */     sendPlayerAbilities();
/* 1128 */     func_175136_bO();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175149_v() {
/* 1133 */     return (this.theItemInWorldManager.getGameType() == WorldSettings.GameType.SPECTATOR);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addChatMessage(IChatComponent message) {
/* 1144 */     this.playerNetServerHandler.sendPacket((Packet)new S02PacketChat(message));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCommandSenderUseCommand(int permissionLevel, String command) {
/* 1152 */     if ("seed".equals(command) && !this.mcServer.isDedicatedServer())
/*      */     {
/* 1154 */       return true;
/*      */     }
/* 1156 */     if (!"tell".equals(command) && !"help".equals(command) && !"me".equals(command) && !"trigger".equals(command)) {
/*      */       
/* 1158 */       if (this.mcServer.getConfigurationManager().canSendCommands(getGameProfile())) {
/*      */         
/* 1160 */         UserListOpsEntry var3 = (UserListOpsEntry)this.mcServer.getConfigurationManager().getOppedPlayers().getEntry(getGameProfile());
/* 1161 */         return (var3 != null) ? ((var3.func_152644_a() >= permissionLevel)) : ((this.mcServer.getOpPermissionLevel() >= permissionLevel));
/*      */       } 
/*      */ 
/*      */       
/* 1165 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1170 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPlayerIP() {
/* 1179 */     String var1 = this.playerNetServerHandler.netManager.getRemoteAddress().toString();
/* 1180 */     var1 = var1.substring(var1.indexOf("/") + 1);
/* 1181 */     var1 = var1.substring(0, var1.indexOf(":"));
/* 1182 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleClientSettings(C15PacketClientSettings p_147100_1_) {
/* 1187 */     this.translator = p_147100_1_.getLang();
/* 1188 */     this.chatVisibility = p_147100_1_.getChatVisibility();
/* 1189 */     this.chatColours = p_147100_1_.isColorsEnabled();
/* 1190 */     getDataWatcher().updateObject(10, Byte.valueOf((byte)p_147100_1_.getView()));
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityPlayer.EnumChatVisibility getChatVisibility() {
/* 1195 */     return this.chatVisibility;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175397_a(String p_175397_1_, String p_175397_2_) {
/* 1200 */     this.playerNetServerHandler.sendPacket((Packet)new S48PacketResourcePackSend(p_175397_1_, p_175397_2_));
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPos getPosition() {
/* 1205 */     return new BlockPos(this.posX, this.posY + 0.5D, this.posZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public void markPlayerActive() {
/* 1210 */     this.playerLastActiveTime = MinecraftServer.getCurrentTimeMillis();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StatisticsFile getStatFile() {
/* 1218 */     return this.statsFile;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152339_d(Entity p_152339_1_) {
/* 1223 */     if (p_152339_1_ instanceof EntityPlayer) {
/*      */       
/* 1225 */       this.playerNetServerHandler.sendPacket((Packet)new S13PacketDestroyEntities(new int[] { p_152339_1_.getEntityId() }));
/*      */     }
/*      */     else {
/*      */       
/* 1229 */       this.destroyedItemsNetCache.add(Integer.valueOf(p_152339_1_.getEntityId()));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175135_B() {
/* 1235 */     if (func_175149_v()) {
/*      */       
/* 1237 */       func_175133_bi();
/* 1238 */       setInvisible(true);
/*      */     }
/*      */     else {
/*      */       
/* 1242 */       super.func_175135_B();
/*      */     } 
/*      */     
/* 1245 */     getServerForPlayer().getEntityTracker().func_180245_a(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity func_175398_C() {
/* 1250 */     return (this.field_175401_bS == null) ? (Entity)this : this.field_175401_bS;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175399_e(Entity p_175399_1_) {
/* 1255 */     Entity var2 = func_175398_C();
/* 1256 */     this.field_175401_bS = (p_175399_1_ == null) ? (Entity)this : p_175399_1_;
/*      */     
/* 1258 */     if (var2 != this.field_175401_bS) {
/*      */       
/* 1260 */       this.playerNetServerHandler.sendPacket((Packet)new S43PacketCamera(this.field_175401_bS));
/* 1261 */       setPositionAndUpdate(this.field_175401_bS.posX, this.field_175401_bS.posY, this.field_175401_bS.posZ);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void attackTargetEntityWithCurrentItem(Entity targetEntity) {
/* 1271 */     if (this.theItemInWorldManager.getGameType() == WorldSettings.GameType.SPECTATOR) {
/*      */       
/* 1273 */       func_175399_e(targetEntity);
/*      */     }
/*      */     else {
/*      */       
/* 1277 */       super.attackTargetEntityWithCurrentItem(targetEntity);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLastActiveTime() {
/* 1283 */     return this.playerLastActiveTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public IChatComponent func_175396_E() {
/* 1288 */     return null;
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\player\EntityPlayerMP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */