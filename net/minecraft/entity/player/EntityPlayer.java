/*      */ package net.minecraft.entity.player;
/*      */ 
/*      */ import com.google.common.base.Charsets;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.mojang.authlib.GameProfile;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockBed;
/*      */ import net.minecraft.block.BlockDirectional;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.properties.IProperty;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.command.server.CommandBlockLogic;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityList;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.EnumCreatureAttribute;
/*      */ import net.minecraft.entity.IEntityMultiPart;
/*      */ import net.minecraft.entity.IMerchant;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.boss.EntityDragonPart;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityMob;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.passive.EntityPig;
/*      */ import net.minecraft.entity.projectile.EntityArrow;
/*      */ import net.minecraft.entity.projectile.EntityFishHook;
/*      */ import net.minecraft.event.ClickEvent;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.inventory.ContainerPlayer;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.inventory.InventoryEnderChest;
/*      */ import net.minecraft.item.EnumAction;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.play.server.S12PacketEntityVelocity;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.scoreboard.IScoreObjectiveCriteria;
/*      */ import net.minecraft.scoreboard.Score;
/*      */ import net.minecraft.scoreboard.ScoreObjective;
/*      */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*      */ import net.minecraft.scoreboard.Scoreboard;
/*      */ import net.minecraft.scoreboard.Team;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.stats.AchievementList;
/*      */ import net.minecraft.stats.StatBase;
/*      */ import net.minecraft.stats.StatList;
/*      */ import net.minecraft.tileentity.TileEntitySign;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.FoodStats;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.IInteractionObject;
/*      */ import net.minecraft.world.LockCode;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldSettings;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class EntityPlayer
/*      */   extends EntityLivingBase
/*      */ {
/*   88 */   public InventoryPlayer inventory = new InventoryPlayer(this);
/*   89 */   private InventoryEnderChest theInventoryEnderChest = new InventoryEnderChest();
/*      */ 
/*      */ 
/*      */   
/*      */   public Container inventoryContainer;
/*      */ 
/*      */ 
/*      */   
/*      */   public Container openContainer;
/*      */ 
/*      */   
/*  100 */   protected FoodStats foodStats = new FoodStats();
/*      */ 
/*      */   
/*      */   protected int flyToggleTimer;
/*      */ 
/*      */   
/*      */   public float prevCameraYaw;
/*      */ 
/*      */   
/*      */   public float cameraYaw;
/*      */   
/*      */   public int xpCooldown;
/*      */   
/*      */   public double field_71091_bM;
/*      */   
/*      */   public double field_71096_bN;
/*      */   
/*      */   public double field_71097_bO;
/*      */   
/*      */   public double field_71094_bP;
/*      */   
/*      */   public double field_71095_bQ;
/*      */   
/*      */   public double field_71085_bR;
/*      */   
/*      */   protected boolean sleeping;
/*      */   
/*      */   public BlockPos playerLocation;
/*      */   
/*      */   private int sleepTimer;
/*      */   
/*      */   public float field_71079_bU;
/*      */   
/*      */   public float field_71082_cx;
/*      */   
/*      */   public float field_71089_bV;
/*      */   
/*      */   private BlockPos spawnChunk;
/*      */   
/*      */   private boolean spawnForced;
/*      */   
/*      */   private BlockPos startMinecartRidingCoordinate;
/*      */   
/*  143 */   public PlayerCapabilities capabilities = new PlayerCapabilities();
/*      */ 
/*      */ 
/*      */   
/*      */   public int experienceLevel;
/*      */ 
/*      */ 
/*      */   
/*      */   public int experienceTotal;
/*      */ 
/*      */ 
/*      */   
/*      */   public float experience;
/*      */ 
/*      */ 
/*      */   
/*      */   private int field_175152_f;
/*      */ 
/*      */ 
/*      */   
/*      */   private ItemStack itemInUse;
/*      */ 
/*      */   
/*      */   private int itemInUseCount;
/*      */ 
/*      */   
/*  169 */   protected float speedOnGround = 0.1F;
/*  170 */   protected float speedInAir = 0.02F;
/*      */ 
/*      */   
/*      */   private int field_82249_h;
/*      */   
/*      */   public final GameProfile gameProfile;
/*      */   
/*      */   private boolean field_175153_bG = false;
/*      */   
/*      */   public EntityFishHook fishEntity;
/*      */   
/*      */   private static final String __OBFID = "CL_00001711";
/*      */ 
/*      */   
/*      */   public EntityPlayer(World worldIn, GameProfile p_i45324_2_) {
/*  185 */     super(worldIn);
/*  186 */     this.entityUniqueID = getUUID(p_i45324_2_);
/*  187 */     this.gameProfile = p_i45324_2_;
/*  188 */     this.inventoryContainer = (Container)new ContainerPlayer(this.inventory, !worldIn.isRemote, this);
/*  189 */     this.openContainer = this.inventoryContainer;
/*  190 */     BlockPos var3 = worldIn.getSpawnPoint();
/*  191 */     setLocationAndAngles(var3.getX() + 0.5D, (var3.getY() + 1), var3.getZ() + 0.5D, 0.0F, 0.0F);
/*  192 */     this.field_70741_aB = 180.0F;
/*  193 */     this.fireResistance = 20;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*  198 */     super.applyEntityAttributes();
/*  199 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
/*  200 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.10000000149011612D);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  205 */     super.entityInit();
/*  206 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*  207 */     this.dataWatcher.addObject(17, Float.valueOf(0.0F));
/*  208 */     this.dataWatcher.addObject(18, Integer.valueOf(0));
/*  209 */     this.dataWatcher.addObject(10, Byte.valueOf((byte)0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getItemInUse() {
/*  217 */     return this.itemInUse;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItemInUseCount() {
/*  225 */     return this.itemInUseCount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUsingItem() {
/*  233 */     return (this.itemInUse != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItemInUseDuration() {
/*  241 */     return isUsingItem() ? (this.itemInUse.getMaxItemUseDuration() - this.itemInUseCount) : 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void stopUsingItem() {
/*  246 */     if (this.itemInUse != null)
/*      */     {
/*  248 */       this.itemInUse.onPlayerStoppedUsing(this.worldObj, this, this.itemInUseCount);
/*      */     }
/*      */     
/*  251 */     clearItemInUse();
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearItemInUse() {
/*  256 */     this.itemInUse = null;
/*  257 */     this.itemInUseCount = 0;
/*      */     
/*  259 */     if (!this.worldObj.isRemote)
/*      */     {
/*  261 */       setEating(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBlocking() {
/*  267 */     return (isUsingItem() && this.itemInUse.getItem().getItemUseAction(this.itemInUse) == EnumAction.BLOCK);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*  275 */     this.noClip = func_175149_v();
/*      */ 
/*      */     
/*  278 */     if (func_175149_v())
/*      */     {
/*  280 */       this.onGround = false;
/*      */     }
/*      */     
/*  283 */     if (this.itemInUse != null) {
/*      */       
/*  285 */       ItemStack var1 = this.inventory.getCurrentItem();
/*      */       
/*  287 */       if (var1 == this.itemInUse) {
/*      */         
/*  289 */         if (this.itemInUseCount <= 25 && this.itemInUseCount % 4 == 0)
/*      */         {
/*  291 */           updateItemUse(var1, 5);
/*      */         }
/*      */         
/*  294 */         if (--this.itemInUseCount == 0 && !this.worldObj.isRemote)
/*      */         {
/*  296 */           onItemUseFinish();
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  301 */         clearItemInUse();
/*      */       } 
/*      */     } 
/*      */     
/*  305 */     if (this.xpCooldown > 0)
/*      */     {
/*  307 */       this.xpCooldown--;
/*      */     }
/*      */     
/*  310 */     if (isPlayerSleeping()) {
/*      */       
/*  312 */       this.sleepTimer++;
/*      */       
/*  314 */       if (this.sleepTimer > 100)
/*      */       {
/*  316 */         this.sleepTimer = 100;
/*      */       }
/*      */       
/*  319 */       if (!this.worldObj.isRemote)
/*      */       {
/*  321 */         if (!func_175143_p())
/*      */         {
/*  323 */           wakeUpPlayer(true, true, false);
/*      */         }
/*  325 */         else if (this.worldObj.isDaytime())
/*      */         {
/*  327 */           wakeUpPlayer(false, true, true);
/*      */         }
/*      */       
/*      */       }
/*  331 */     } else if (this.sleepTimer > 0) {
/*      */       
/*  333 */       this.sleepTimer++;
/*      */       
/*  335 */       if (this.sleepTimer >= 110)
/*      */       {
/*  337 */         this.sleepTimer = 0;
/*      */       }
/*      */     } 
/*      */     
/*  341 */     super.onUpdate();
/*      */     
/*  343 */     if (!this.worldObj.isRemote && this.openContainer != null && !this.openContainer.canInteractWith(this)) {
/*      */       
/*  345 */       closeScreen();
/*  346 */       this.openContainer = this.inventoryContainer;
/*      */     } 
/*      */     
/*  349 */     if (isBurning() && this.capabilities.disableDamage)
/*      */     {
/*  351 */       extinguish();
/*      */     }
/*      */     
/*  354 */     this.field_71091_bM = this.field_71094_bP;
/*  355 */     this.field_71096_bN = this.field_71095_bQ;
/*  356 */     this.field_71097_bO = this.field_71085_bR;
/*  357 */     double var14 = this.posX - this.field_71094_bP;
/*  358 */     double var3 = this.posY - this.field_71095_bQ;
/*  359 */     double var5 = this.posZ - this.field_71085_bR;
/*  360 */     double var7 = 10.0D;
/*      */     
/*  362 */     if (var14 > var7)
/*      */     {
/*  364 */       this.field_71091_bM = this.field_71094_bP = this.posX;
/*      */     }
/*      */     
/*  367 */     if (var5 > var7)
/*      */     {
/*  369 */       this.field_71097_bO = this.field_71085_bR = this.posZ;
/*      */     }
/*      */     
/*  372 */     if (var3 > var7)
/*      */     {
/*  374 */       this.field_71096_bN = this.field_71095_bQ = this.posY;
/*      */     }
/*      */     
/*  377 */     if (var14 < -var7)
/*      */     {
/*  379 */       this.field_71091_bM = this.field_71094_bP = this.posX;
/*      */     }
/*      */     
/*  382 */     if (var5 < -var7)
/*      */     {
/*  384 */       this.field_71097_bO = this.field_71085_bR = this.posZ;
/*      */     }
/*      */     
/*  387 */     if (var3 < -var7)
/*      */     {
/*  389 */       this.field_71096_bN = this.field_71095_bQ = this.posY;
/*      */     }
/*      */     
/*  392 */     this.field_71094_bP += var14 * 0.25D;
/*  393 */     this.field_71085_bR += var5 * 0.25D;
/*  394 */     this.field_71095_bQ += var3 * 0.25D;
/*      */     
/*  396 */     if (this.ridingEntity == null)
/*      */     {
/*  398 */       this.startMinecartRidingCoordinate = null;
/*      */     }
/*      */     
/*  401 */     if (!this.worldObj.isRemote) {
/*      */       
/*  403 */       this.foodStats.onUpdate(this);
/*  404 */       triggerAchievement(StatList.minutesPlayedStat);
/*      */       
/*  406 */       if (isEntityAlive())
/*      */       {
/*  408 */         triggerAchievement(StatList.timeSinceDeathStat);
/*      */       }
/*      */     } 
/*      */     
/*  412 */     int var9 = 29999999;
/*  413 */     double var10 = MathHelper.clamp_double(this.posX, -2.9999999E7D, 2.9999999E7D);
/*  414 */     double var12 = MathHelper.clamp_double(this.posZ, -2.9999999E7D, 2.9999999E7D);
/*      */     
/*  416 */     if (var10 != this.posX || var12 != this.posZ)
/*      */     {
/*  418 */       setPosition(var10, this.posY, var12);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxInPortalTime() {
/*  427 */     return this.capabilities.disableDamage ? 0 : 80;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getSwimSound() {
/*  432 */     return "game.player.swim";
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getSplashSound() {
/*  437 */     return "game.player.swim.splash";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPortalCooldown() {
/*  445 */     return 10;
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(String name, float volume, float pitch) {
/*  450 */     this.worldObj.playSoundToNearExcept(this, name, volume, pitch);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateItemUse(ItemStack itemStackIn, int p_71010_2_) {
/*  458 */     if (itemStackIn.getItemUseAction() == EnumAction.DRINK)
/*      */     {
/*  460 */       playSound("random.drink", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
/*      */     }
/*      */     
/*  463 */     if (itemStackIn.getItemUseAction() == EnumAction.EAT) {
/*      */       
/*  465 */       for (int var3 = 0; var3 < p_71010_2_; var3++) {
/*      */         
/*  467 */         Vec3 var4 = new Vec3((this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/*  468 */         var4 = var4.rotatePitch(-this.rotationPitch * 3.1415927F / 180.0F);
/*  469 */         var4 = var4.rotateYaw(-this.rotationYaw * 3.1415927F / 180.0F);
/*  470 */         double var5 = -this.rand.nextFloat() * 0.6D - 0.3D;
/*  471 */         Vec3 var7 = new Vec3((this.rand.nextFloat() - 0.5D) * 0.3D, var5, 0.6D);
/*  472 */         var7 = var7.rotatePitch(-this.rotationPitch * 3.1415927F / 180.0F);
/*  473 */         var7 = var7.rotateYaw(-this.rotationYaw * 3.1415927F / 180.0F);
/*  474 */         var7 = var7.addVector(this.posX, this.posY + getEyeHeight(), this.posZ);
/*      */         
/*  476 */         if (itemStackIn.getHasSubtypes()) {
/*      */           
/*  478 */           this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, var7.xCoord, var7.yCoord, var7.zCoord, var4.xCoord, var4.yCoord + 0.05D, var4.zCoord, new int[] { Item.getIdFromItem(itemStackIn.getItem()), itemStackIn.getMetadata() });
/*      */         }
/*      */         else {
/*      */           
/*  482 */           this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, var7.xCoord, var7.yCoord, var7.zCoord, var4.xCoord, var4.yCoord + 0.05D, var4.zCoord, new int[] { Item.getIdFromItem(itemStackIn.getItem()) });
/*      */         } 
/*      */       } 
/*      */       
/*  486 */       playSound("random.eat", 0.5F + 0.5F * this.rand.nextInt(2), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onItemUseFinish() {
/*  495 */     if (this.itemInUse != null) {
/*      */       
/*  497 */       updateItemUse(this.itemInUse, 16);
/*  498 */       int var1 = this.itemInUse.stackSize;
/*  499 */       ItemStack var2 = this.itemInUse.onItemUseFinish(this.worldObj, this);
/*      */       
/*  501 */       if (var2 != this.itemInUse || (var2 != null && var2.stackSize != var1)) {
/*      */         
/*  503 */         this.inventory.mainInventory[this.inventory.currentItem] = var2;
/*      */         
/*  505 */         if (var2.stackSize == 0)
/*      */         {
/*  507 */           this.inventory.mainInventory[this.inventory.currentItem] = null;
/*      */         }
/*      */       } 
/*      */       
/*  511 */       clearItemInUse();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(byte p_70103_1_) {
/*  517 */     if (p_70103_1_ == 9) {
/*      */       
/*  519 */       onItemUseFinish();
/*      */     }
/*  521 */     else if (p_70103_1_ == 23) {
/*      */       
/*  523 */       this.field_175153_bG = false;
/*      */     }
/*  525 */     else if (p_70103_1_ == 22) {
/*      */       
/*  527 */       this.field_175153_bG = true;
/*      */     }
/*      */     else {
/*      */       
/*  531 */       super.handleHealthUpdate(p_70103_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isMovementBlocked() {
/*  540 */     return !(getHealth() > 0.0F && !isPlayerSleeping());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void closeScreen() {
/*  548 */     this.openContainer = this.inventoryContainer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRidden() {
/*  556 */     if (!this.worldObj.isRemote && isSneaking()) {
/*      */       
/*  558 */       mountEntity(null);
/*  559 */       setSneaking(false);
/*      */     }
/*      */     else {
/*      */       
/*  563 */       double var1 = this.posX;
/*  564 */       double var3 = this.posY;
/*  565 */       double var5 = this.posZ;
/*  566 */       float var7 = this.rotationYaw;
/*  567 */       float var8 = this.rotationPitch;
/*  568 */       super.updateRidden();
/*  569 */       this.prevCameraYaw = this.cameraYaw;
/*  570 */       this.cameraYaw = 0.0F;
/*  571 */       addMountedMovementStat(this.posX - var1, this.posY - var3, this.posZ - var5);
/*      */       
/*  573 */       if (this.ridingEntity instanceof EntityPig) {
/*      */         
/*  575 */         this.rotationPitch = var8;
/*  576 */         this.rotationYaw = var7;
/*  577 */         this.renderYawOffset = ((EntityPig)this.ridingEntity).renderYawOffset;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void preparePlayerToSpawn() {
/*  588 */     setSize(0.6F, 1.8F);
/*  589 */     super.preparePlayerToSpawn();
/*  590 */     setHealth(getMaxHealth());
/*  591 */     this.deathTime = 0;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateEntityActionState() {
/*  596 */     super.updateEntityActionState();
/*  597 */     updateArmSwingProgress();
/*  598 */     this.rotationYawHead = this.rotationYaw;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/*  607 */     if (this.flyToggleTimer > 0)
/*      */     {
/*  609 */       this.flyToggleTimer--;
/*      */     }
/*      */     
/*  612 */     if (this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL && this.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration")) {
/*      */       
/*  614 */       if (getHealth() < getMaxHealth() && this.ticksExisted % 20 == 0)
/*      */       {
/*  616 */         heal(1.0F);
/*      */       }
/*      */       
/*  619 */       if (this.foodStats.needFood() && this.ticksExisted % 10 == 0)
/*      */       {
/*  621 */         this.foodStats.setFoodLevel(this.foodStats.getFoodLevel() + 1);
/*      */       }
/*      */     } 
/*      */     
/*  625 */     this.inventory.decrementAnimations();
/*  626 */     this.prevCameraYaw = this.cameraYaw;
/*  627 */     super.onLivingUpdate();
/*  628 */     IAttributeInstance var1 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*      */     
/*  630 */     if (!this.worldObj.isRemote)
/*      */     {
/*  632 */       var1.setBaseValue(this.capabilities.getWalkSpeed());
/*      */     }
/*      */     
/*  635 */     this.jumpMovementFactor = this.speedInAir;
/*      */     
/*  637 */     if (isSprinting())
/*      */     {
/*  639 */       this.jumpMovementFactor = (float)(this.jumpMovementFactor + this.speedInAir * 0.3D);
/*      */     }
/*      */     
/*  642 */     setAIMoveSpeed((float)var1.getAttributeValue());
/*  643 */     float var2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*  644 */     float var3 = (float)(Math.atan(-this.motionY * 0.20000000298023224D) * 15.0D);
/*      */     
/*  646 */     if (var2 > 0.1F)
/*      */     {
/*  648 */       var2 = 0.1F;
/*      */     }
/*      */     
/*  651 */     if (!this.onGround || getHealth() <= 0.0F)
/*      */     {
/*  653 */       var2 = 0.0F;
/*      */     }
/*      */     
/*  656 */     if (this.onGround || getHealth() <= 0.0F)
/*      */     {
/*  658 */       var3 = 0.0F;
/*      */     }
/*      */     
/*  661 */     this.cameraYaw += (var2 - this.cameraYaw) * 0.4F;
/*      */     
/*  663 */     this.cameraPitch += (var3 - this.cameraPitch) * 0.8F;
/*      */     
/*  665 */     if (getHealth() > 0.0F && !func_175149_v()) {
/*      */       
/*  667 */       AxisAlignedBB var4 = null;
/*      */       
/*  669 */       if (this.ridingEntity != null && !this.ridingEntity.isDead) {
/*      */         
/*  671 */         var4 = getEntityBoundingBox().union(this.ridingEntity.getEntityBoundingBox()).expand(1.0D, 0.0D, 1.0D);
/*      */       }
/*      */       else {
/*      */         
/*  675 */         var4 = getEntityBoundingBox().expand(1.0D, 0.5D, 1.0D);
/*      */       } 
/*      */       
/*  678 */       List<Entity> var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, var4);
/*      */       
/*  680 */       for (int var6 = 0; var6 < var5.size(); var6++) {
/*      */         
/*  682 */         Entity var7 = var5.get(var6);
/*      */         
/*  684 */         if (!var7.isDead)
/*      */         {
/*  686 */           collideWithPlayer(var7);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void collideWithPlayer(Entity p_71044_1_) {
/*  694 */     p_71044_1_.onCollideWithPlayer(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getScore() {
/*  699 */     return this.dataWatcher.getWatchableObjectInt(18);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setScore(int p_85040_1_) {
/*  707 */     this.dataWatcher.updateObject(18, Integer.valueOf(p_85040_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addScore(int p_85039_1_) {
/*  715 */     int var2 = getScore();
/*  716 */     this.dataWatcher.updateObject(18, Integer.valueOf(var2 + p_85039_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource cause) {
/*  724 */     super.onDeath(cause);
/*  725 */     setSize(0.2F, 0.2F);
/*  726 */     setPosition(this.posX, this.posY, this.posZ);
/*  727 */     this.motionY = 0.10000000149011612D;
/*      */     
/*  729 */     if (getName().equals("Notch"))
/*      */     {
/*  731 */       func_146097_a(new ItemStack(Items.apple, 1), true, false);
/*      */     }
/*      */     
/*  734 */     if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
/*      */     {
/*  736 */       this.inventory.dropAllItems();
/*      */     }
/*      */     
/*  739 */     if (cause != null) {
/*      */       
/*  741 */       this.motionX = (-MathHelper.cos((this.attackedAtYaw + this.rotationYaw) * 3.1415927F / 180.0F) * 0.1F);
/*  742 */       this.motionZ = (-MathHelper.sin((this.attackedAtYaw + this.rotationYaw) * 3.1415927F / 180.0F) * 0.1F);
/*      */     }
/*      */     else {
/*      */       
/*  746 */       this.motionX = this.motionZ = 0.0D;
/*      */     } 
/*      */     
/*  749 */     triggerAchievement(StatList.deathsStat);
/*  750 */     func_175145_a(StatList.timeSinceDeathStat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getHurtSound() {
/*  758 */     return "game.player.hurt";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getDeathSound() {
/*  766 */     return "game.player.die";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToPlayerScore(Entity entityIn, int amount) {
/*  775 */     addScore(amount);
/*  776 */     Collection var3 = getWorldScoreboard().func_96520_a(IScoreObjectiveCriteria.totalKillCount);
/*      */     
/*  778 */     if (entityIn instanceof EntityPlayer) {
/*      */       
/*  780 */       triggerAchievement(StatList.playerKillsStat);
/*  781 */       var3.addAll(getWorldScoreboard().func_96520_a(IScoreObjectiveCriteria.playerKillCount));
/*  782 */       var3.addAll(func_175137_e(entityIn));
/*      */     }
/*      */     else {
/*      */       
/*  786 */       triggerAchievement(StatList.mobKillsStat);
/*      */     } 
/*      */     
/*  789 */     Iterator<ScoreObjective> var4 = var3.iterator();
/*      */     
/*  791 */     while (var4.hasNext()) {
/*      */       
/*  793 */       ScoreObjective var5 = var4.next();
/*  794 */       Score var6 = getWorldScoreboard().getValueFromObjective(getName(), var5);
/*  795 */       var6.func_96648_a();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private Collection func_175137_e(Entity p_175137_1_) {
/*  801 */     ScorePlayerTeam var2 = getWorldScoreboard().getPlayersTeam(getName());
/*      */     
/*  803 */     if (var2 != null) {
/*      */       
/*  805 */       int var3 = var2.func_178775_l().func_175746_b();
/*      */       
/*  807 */       if (var3 >= 0 && var3 < IScoreObjectiveCriteria.field_178793_i.length) {
/*      */         
/*  809 */         Iterator<ScoreObjective> var4 = getWorldScoreboard().func_96520_a(IScoreObjectiveCriteria.field_178793_i[var3]).iterator();
/*      */         
/*  811 */         while (var4.hasNext()) {
/*      */           
/*  813 */           ScoreObjective var5 = var4.next();
/*  814 */           Score var6 = getWorldScoreboard().getValueFromObjective(p_175137_1_.getName(), var5);
/*  815 */           var6.func_96648_a();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  820 */     ScorePlayerTeam var7 = getWorldScoreboard().getPlayersTeam(p_175137_1_.getName());
/*      */     
/*  822 */     if (var7 != null) {
/*      */       
/*  824 */       int var8 = var7.func_178775_l().func_175746_b();
/*      */       
/*  826 */       if (var8 >= 0 && var8 < IScoreObjectiveCriteria.field_178792_h.length)
/*      */       {
/*  828 */         return getWorldScoreboard().func_96520_a(IScoreObjectiveCriteria.field_178792_h[var8]);
/*      */       }
/*      */     } 
/*      */     
/*  832 */     return Lists.newArrayList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropOneItem(boolean p_71040_1_) {
/*  840 */     return func_146097_a(this.inventory.decrStackSize(this.inventory.currentItem, (p_71040_1_ && this.inventory.getCurrentItem() != null) ? (this.inventory.getCurrentItem()).stackSize : 1), false, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem dropPlayerItemWithRandomChoice(ItemStack itemStackIn, boolean p_71019_2_) {
/*  848 */     return func_146097_a(itemStackIn, false, false);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityItem func_146097_a(ItemStack p_146097_1_, boolean p_146097_2_, boolean p_146097_3_) {
/*  853 */     if (p_146097_1_ == null)
/*      */     {
/*  855 */       return null;
/*      */     }
/*  857 */     if (p_146097_1_.stackSize == 0)
/*      */     {
/*  859 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  863 */     double var4 = this.posY - 0.30000001192092896D + getEyeHeight();
/*  864 */     EntityItem var6 = new EntityItem(this.worldObj, this.posX, var4, this.posZ, p_146097_1_);
/*  865 */     var6.setPickupDelay(40);
/*      */     
/*  867 */     if (p_146097_3_)
/*      */     {
/*  869 */       var6.setThrower(getName());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  875 */     if (p_146097_2_) {
/*      */       
/*  877 */       float var7 = this.rand.nextFloat() * 0.5F;
/*  878 */       float var8 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/*  879 */       var6.motionX = (-MathHelper.sin(var8) * var7);
/*  880 */       var6.motionZ = (MathHelper.cos(var8) * var7);
/*  881 */       var6.motionY = 0.20000000298023224D;
/*      */     }
/*      */     else {
/*      */       
/*  885 */       float var7 = 0.3F;
/*  886 */       var6.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var7);
/*  887 */       var6.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var7);
/*  888 */       var6.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F) * var7 + 0.1F);
/*  889 */       float var8 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/*  890 */       var7 = 0.02F * this.rand.nextFloat();
/*  891 */       var6.motionX += Math.cos(var8) * var7;
/*  892 */       var6.motionY += ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
/*  893 */       var6.motionZ += Math.sin(var8) * var7;
/*      */     } 
/*      */     
/*  896 */     joinEntityItemWithWorld(var6);
/*      */     
/*  898 */     if (p_146097_3_)
/*      */     {
/*  900 */       triggerAchievement(StatList.dropStat);
/*      */     }
/*      */     
/*  903 */     return var6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void joinEntityItemWithWorld(EntityItem p_71012_1_) {
/*  912 */     this.worldObj.spawnEntityInWorld((Entity)p_71012_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public float func_180471_a(Block p_180471_1_) {
/*  917 */     float var2 = this.inventory.getStrVsBlock(p_180471_1_);
/*      */     
/*  919 */     if (var2 > 1.0F) {
/*      */       
/*  921 */       int var3 = EnchantmentHelper.getEfficiencyModifier(this);
/*  922 */       ItemStack var4 = this.inventory.getCurrentItem();
/*      */       
/*  924 */       if (var3 > 0 && var4 != null)
/*      */       {
/*  926 */         var2 += (var3 * var3 + 1);
/*      */       }
/*      */     } 
/*      */     
/*  930 */     if (isPotionActive(Potion.digSpeed))
/*      */     {
/*  932 */       var2 *= 1.0F + (getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
/*      */     }
/*      */     
/*  935 */     if (isPotionActive(Potion.digSlowdown)) {
/*      */       
/*  937 */       float var5 = 1.0F;
/*      */       
/*  939 */       switch (getActivePotionEffect(Potion.digSlowdown).getAmplifier()) {
/*      */         
/*      */         case 0:
/*  942 */           var5 = 0.3F;
/*      */           break;
/*      */         
/*      */         case 1:
/*  946 */           var5 = 0.09F;
/*      */           break;
/*      */         
/*      */         case 2:
/*  950 */           var5 = 0.0027F;
/*      */           break;
/*      */ 
/*      */         
/*      */         default:
/*  955 */           var5 = 8.1E-4F;
/*      */           break;
/*      */       } 
/*  958 */       var2 *= var5;
/*      */     } 
/*      */     
/*  961 */     if (isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(this))
/*      */     {
/*  963 */       var2 /= 5.0F;
/*      */     }
/*      */     
/*  966 */     if (!this.onGround)
/*      */     {
/*  968 */       var2 /= 5.0F;
/*      */     }
/*      */     
/*  971 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canHarvestBlock(Block p_146099_1_) {
/*  979 */     return this.inventory.func_146025_b(p_146099_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  987 */     super.readEntityFromNBT(tagCompund);
/*  988 */     this.entityUniqueID = getUUID(this.gameProfile);
/*  989 */     NBTTagList var2 = tagCompund.getTagList("Inventory", 10);
/*  990 */     this.inventory.readFromNBT(var2);
/*  991 */     this.inventory.currentItem = tagCompund.getInteger("SelectedItemSlot");
/*  992 */     this.sleeping = tagCompund.getBoolean("Sleeping");
/*  993 */     this.sleepTimer = tagCompund.getShort("SleepTimer");
/*  994 */     this.experience = tagCompund.getFloat("XpP");
/*  995 */     this.experienceLevel = tagCompund.getInteger("XpLevel");
/*  996 */     this.experienceTotal = tagCompund.getInteger("XpTotal");
/*  997 */     this.field_175152_f = tagCompund.getInteger("XpSeed");
/*      */     
/*  999 */     if (this.field_175152_f == 0)
/*      */     {
/* 1001 */       this.field_175152_f = this.rand.nextInt();
/*      */     }
/*      */     
/* 1004 */     setScore(tagCompund.getInteger("Score"));
/*      */     
/* 1006 */     if (this.sleeping) {
/*      */       
/* 1008 */       this.playerLocation = new BlockPos((Entity)this);
/* 1009 */       wakeUpPlayer(true, true, false);
/*      */     } 
/*      */     
/* 1012 */     if (tagCompund.hasKey("SpawnX", 99) && tagCompund.hasKey("SpawnY", 99) && tagCompund.hasKey("SpawnZ", 99)) {
/*      */       
/* 1014 */       this.spawnChunk = new BlockPos(tagCompund.getInteger("SpawnX"), tagCompund.getInteger("SpawnY"), tagCompund.getInteger("SpawnZ"));
/* 1015 */       this.spawnForced = tagCompund.getBoolean("SpawnForced");
/*      */     } 
/*      */     
/* 1018 */     this.foodStats.readNBT(tagCompund);
/* 1019 */     this.capabilities.readCapabilitiesFromNBT(tagCompund);
/*      */     
/* 1021 */     if (tagCompund.hasKey("EnderItems", 9)) {
/*      */       
/* 1023 */       NBTTagList var3 = tagCompund.getTagList("EnderItems", 10);
/* 1024 */       this.theInventoryEnderChest.loadInventoryFromNBT(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 1033 */     super.writeEntityToNBT(tagCompound);
/* 1034 */     tagCompound.setTag("Inventory", (NBTBase)this.inventory.writeToNBT(new NBTTagList()));
/* 1035 */     tagCompound.setInteger("SelectedItemSlot", this.inventory.currentItem);
/* 1036 */     tagCompound.setBoolean("Sleeping", this.sleeping);
/* 1037 */     tagCompound.setShort("SleepTimer", (short)this.sleepTimer);
/* 1038 */     tagCompound.setFloat("XpP", this.experience);
/* 1039 */     tagCompound.setInteger("XpLevel", this.experienceLevel);
/* 1040 */     tagCompound.setInteger("XpTotal", this.experienceTotal);
/* 1041 */     tagCompound.setInteger("XpSeed", this.field_175152_f);
/* 1042 */     tagCompound.setInteger("Score", getScore());
/*      */     
/* 1044 */     if (this.spawnChunk != null) {
/*      */       
/* 1046 */       tagCompound.setInteger("SpawnX", this.spawnChunk.getX());
/* 1047 */       tagCompound.setInteger("SpawnY", this.spawnChunk.getY());
/* 1048 */       tagCompound.setInteger("SpawnZ", this.spawnChunk.getZ());
/* 1049 */       tagCompound.setBoolean("SpawnForced", this.spawnForced);
/*      */     } 
/*      */     
/* 1052 */     this.foodStats.writeNBT(tagCompound);
/* 1053 */     this.capabilities.writeCapabilitiesToNBT(tagCompound);
/* 1054 */     tagCompound.setTag("EnderItems", (NBTBase)this.theInventoryEnderChest.saveInventoryToNBT());
/* 1055 */     ItemStack var2 = this.inventory.getCurrentItem();
/*      */     
/* 1057 */     if (var2 != null && var2.getItem() != null)
/*      */     {
/* 1059 */       tagCompound.setTag("SelectedItem", (NBTBase)var2.writeToNBT(new NBTTagCompound()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 1068 */     if (func_180431_b(source))
/*      */     {
/* 1070 */       return false;
/*      */     }
/* 1072 */     if (this.capabilities.disableDamage && !source.canHarmInCreative())
/*      */     {
/* 1074 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1078 */     this.entityAge = 0;
/*      */     
/* 1080 */     if (getHealth() <= 0.0F)
/*      */     {
/* 1082 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1086 */     if (isPlayerSleeping() && !this.worldObj.isRemote)
/*      */     {
/* 1088 */       wakeUpPlayer(true, true, false);
/*      */     }
/*      */     
/* 1091 */     if (source.isDifficultyScaled()) {
/*      */       
/* 1093 */       if (this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL)
/*      */       {
/* 1095 */         amount = 0.0F;
/*      */       }
/*      */       
/* 1098 */       if (this.worldObj.getDifficulty() == EnumDifficulty.EASY)
/*      */       {
/* 1100 */         amount = amount / 2.0F + 1.0F;
/*      */       }
/*      */       
/* 1103 */       if (this.worldObj.getDifficulty() == EnumDifficulty.HARD)
/*      */       {
/* 1105 */         amount = amount * 3.0F / 2.0F;
/*      */       }
/*      */     } 
/*      */     
/* 1109 */     if (amount == 0.0F)
/*      */     {
/* 1111 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1115 */     Entity var3 = source.getEntity();
/*      */     
/* 1117 */     if (var3 instanceof EntityArrow && ((EntityArrow)var3).shootingEntity != null)
/*      */     {
/* 1119 */       var3 = ((EntityArrow)var3).shootingEntity;
/*      */     }
/*      */     
/* 1122 */     return super.attackEntityFrom(source, amount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canAttackPlayer(EntityPlayer other) {
/* 1130 */     Team var2 = getTeam();
/* 1131 */     Team var3 = other.getTeam();
/* 1132 */     return (var2 == null) ? true : (!var2.isSameTeam(var3) ? true : var2.getAllowFriendlyFire());
/*      */   }
/*      */ 
/*      */   
/*      */   protected void damageArmor(float p_70675_1_) {
/* 1137 */     this.inventory.damageArmor(p_70675_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTotalArmorValue() {
/* 1145 */     return this.inventory.getTotalArmorValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getArmorVisibility() {
/* 1154 */     int var1 = 0;
/* 1155 */     ItemStack[] var2 = this.inventory.armorInventory;
/* 1156 */     int var3 = var2.length;
/*      */     
/* 1158 */     for (int var4 = 0; var4 < var3; var4++) {
/*      */       
/* 1160 */       ItemStack var5 = var2[var4];
/*      */       
/* 1162 */       if (var5 != null)
/*      */       {
/* 1164 */         var1++;
/*      */       }
/*      */     } 
/*      */     
/* 1168 */     return var1 / this.inventory.armorInventory.length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void damageEntity(DamageSource p_70665_1_, float p_70665_2_) {
/* 1177 */     if (!func_180431_b(p_70665_1_)) {
/*      */       
/* 1179 */       if (!p_70665_1_.isUnblockable() && isBlocking() && p_70665_2_ > 0.0F)
/*      */       {
/* 1181 */         p_70665_2_ = (1.0F + p_70665_2_) * 0.5F;
/*      */       }
/*      */       
/* 1184 */       p_70665_2_ = applyArmorCalculations(p_70665_1_, p_70665_2_);
/* 1185 */       p_70665_2_ = applyPotionDamageCalculations(p_70665_1_, p_70665_2_);
/* 1186 */       float var3 = p_70665_2_;
/* 1187 */       p_70665_2_ = Math.max(p_70665_2_ - getAbsorptionAmount(), 0.0F);
/* 1188 */       setAbsorptionAmount(getAbsorptionAmount() - var3 - p_70665_2_);
/*      */       
/* 1190 */       if (p_70665_2_ != 0.0F) {
/*      */         
/* 1192 */         addExhaustion(p_70665_1_.getHungerDamage());
/* 1193 */         float var4 = getHealth();
/* 1194 */         setHealth(getHealth() - p_70665_2_);
/* 1195 */         getCombatTracker().func_94547_a(p_70665_1_, var4, p_70665_2_);
/*      */         
/* 1197 */         if (p_70665_2_ < 3.4028235E37F)
/*      */         {
/* 1199 */           addStat(StatList.damageTakenStat, Math.round(p_70665_2_ * 10.0F));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175141_a(TileEntitySign p_175141_1_) {}
/*      */ 
/*      */   
/*      */   public void func_146095_a(CommandBlockLogic p_146095_1_) {}
/*      */ 
/*      */   
/*      */   public void displayVillagerTradeGui(IMerchant villager) {}
/*      */ 
/*      */   
/*      */   public void displayGUIChest(IInventory chestInventory) {}
/*      */ 
/*      */   
/*      */   public void displayGUIHorse(EntityHorse p_110298_1_, IInventory p_110298_2_) {}
/*      */ 
/*      */   
/*      */   public void displayGui(IInteractionObject guiOwner) {}
/*      */ 
/*      */   
/*      */   public void displayGUIBook(ItemStack bookStack) {}
/*      */   
/*      */   public boolean interactWith(Entity p_70998_1_) {
/* 1227 */     if (func_175149_v()) {
/*      */       
/* 1229 */       if (p_70998_1_ instanceof IInventory)
/*      */       {
/* 1231 */         displayGUIChest((IInventory)p_70998_1_);
/*      */       }
/*      */       
/* 1234 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1238 */     ItemStack var2 = getCurrentEquippedItem();
/* 1239 */     ItemStack var3 = (var2 != null) ? var2.copy() : null;
/*      */     
/* 1241 */     if (!p_70998_1_.interactFirst(this)) {
/*      */       
/* 1243 */       if (var2 != null && p_70998_1_ instanceof EntityLivingBase) {
/*      */         
/* 1245 */         if (this.capabilities.isCreativeMode)
/*      */         {
/* 1247 */           var2 = var3;
/*      */         }
/*      */         
/* 1250 */         if (var2.interactWithEntity(this, (EntityLivingBase)p_70998_1_)) {
/*      */           
/* 1252 */           if (var2.stackSize <= 0 && !this.capabilities.isCreativeMode)
/*      */           {
/* 1254 */             destroyCurrentEquippedItem();
/*      */           }
/*      */           
/* 1257 */           return true;
/*      */         } 
/*      */       } 
/*      */       
/* 1261 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1265 */     if (var2 != null && var2 == getCurrentEquippedItem())
/*      */     {
/* 1267 */       if (var2.stackSize <= 0 && !this.capabilities.isCreativeMode) {
/*      */         
/* 1269 */         destroyCurrentEquippedItem();
/*      */       }
/* 1271 */       else if (var2.stackSize < var3.stackSize && this.capabilities.isCreativeMode) {
/*      */         
/* 1273 */         var2.stackSize = var3.stackSize;
/*      */       } 
/*      */     }
/*      */     
/* 1277 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getCurrentEquippedItem() {
/* 1287 */     return this.inventory.getCurrentItem();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void destroyCurrentEquippedItem() {
/* 1295 */     this.inventory.setInventorySlotContents(this.inventory.currentItem, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getYOffset() {
/* 1303 */     return -0.35D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void attackTargetEntityWithCurrentItem(Entity targetEntity) {
/* 1312 */     if (targetEntity.canAttackWithItem())
/*      */     {
/* 1314 */       if (!targetEntity.hitByEntity((Entity)this)) {
/*      */         
/* 1316 */         float var2 = (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
/* 1317 */         byte var3 = 0;
/* 1318 */         float var4 = 0.0F;
/*      */         
/* 1320 */         if (targetEntity instanceof EntityLivingBase) {
/*      */           
/* 1322 */           var4 = EnchantmentHelper.func_152377_a(getHeldItem(), ((EntityLivingBase)targetEntity).getCreatureAttribute());
/*      */         }
/*      */         else {
/*      */           
/* 1326 */           var4 = EnchantmentHelper.func_152377_a(getHeldItem(), EnumCreatureAttribute.UNDEFINED);
/*      */         } 
/*      */         
/* 1329 */         int var18 = var3 + EnchantmentHelper.getRespiration(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1336 */         if (var2 > 0.0F || var4 > 0.0F) {
/*      */           
/* 1338 */           boolean var5 = (this.fallDistance > 0.0F && !this.onGround && !isOnLadder() && !isInWater() && !isPotionActive(Potion.blindness) && this.ridingEntity == null && targetEntity instanceof EntityLivingBase);
/*      */           
/* 1340 */           if (var5 && var2 > 0.0F)
/*      */           {
/* 1342 */             var2 *= 1.5F;
/*      */           }
/*      */           
/* 1345 */           var2 += var4;
/* 1346 */           boolean var6 = false;
/* 1347 */           int var7 = EnchantmentHelper.getFireAspectModifier(this);
/*      */           
/* 1349 */           if (targetEntity instanceof EntityLivingBase && var7 > 0 && !targetEntity.isBurning()) {
/*      */             
/* 1351 */             var6 = true;
/* 1352 */             targetEntity.setFire(1);
/*      */           } 
/*      */           
/* 1355 */           double var8 = targetEntity.motionX;
/* 1356 */           double var10 = targetEntity.motionY;
/* 1357 */           double var12 = targetEntity.motionZ;
/* 1358 */           boolean var14 = targetEntity.attackEntityFrom(DamageSource.causePlayerDamage(this), var2);
/*      */           
/* 1360 */           if (var14) {
/*      */             
/* 1362 */             if (var18 > 0) {
/*      */               
/* 1364 */               targetEntity.addVelocity((-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * var18 * 0.5F), 0.1D, (MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * var18 * 0.5F));
/* 1365 */               this.motionX *= 0.6D;
/* 1366 */               this.motionZ *= 0.6D;
/*      */             } 
/*      */ 
/*      */             
/* 1370 */             if (targetEntity instanceof EntityPlayerMP && targetEntity.velocityChanged) {
/*      */               
/* 1372 */               ((EntityPlayerMP)targetEntity).playerNetServerHandler.sendPacket((Packet)new S12PacketEntityVelocity(targetEntity));
/*      */               
/* 1374 */               targetEntity.motionX = var8;
/* 1375 */               targetEntity.motionY = var10;
/* 1376 */               targetEntity.motionZ = var12;
/*      */             } 
/*      */             
/* 1379 */             if (var5)
/*      */             {
/* 1381 */               onCriticalHit(targetEntity);
/*      */             }
/*      */             
/* 1384 */             if (var4 > 0.0F)
/*      */             {
/* 1386 */               onEnchantmentCritical(targetEntity);
/*      */             }
/*      */             
/* 1389 */             if (var2 >= 18.0F)
/*      */             {
/* 1391 */               triggerAchievement((StatBase)AchievementList.overkill);
/*      */             }
/*      */             
/* 1394 */             setLastAttacker(targetEntity);
/*      */             
/* 1396 */             if (targetEntity instanceof EntityLivingBase)
/*      */             {
/* 1398 */               EnchantmentHelper.func_151384_a((EntityLivingBase)targetEntity, (Entity)this);
/*      */             }
/*      */             
/* 1401 */             EnchantmentHelper.func_151385_b(this, targetEntity);
/* 1402 */             ItemStack var15 = getCurrentEquippedItem();
/* 1403 */             Object var16 = targetEntity;
/*      */             
/* 1405 */             if (targetEntity instanceof EntityDragonPart) {
/*      */               
/* 1407 */               IEntityMultiPart var17 = ((EntityDragonPart)targetEntity).entityDragonObj;
/*      */               
/* 1409 */               if (var17 instanceof EntityLivingBase)
/*      */               {
/* 1411 */                 var16 = var17;
/*      */               }
/*      */             } 
/*      */             
/* 1415 */             if (var15 != null && var16 instanceof EntityLivingBase) {
/*      */               
/* 1417 */               var15.hitEntity((EntityLivingBase)var16, this);
/*      */               
/* 1419 */               if (var15.stackSize <= 0)
/*      */               {
/* 1421 */                 destroyCurrentEquippedItem();
/*      */               }
/*      */             } 
/*      */             
/* 1425 */             if (targetEntity instanceof EntityLivingBase) {
/*      */               
/* 1427 */               addStat(StatList.damageDealtStat, Math.round(var2 * 10.0F));
/*      */               
/* 1429 */               if (var7 > 0)
/*      */               {
/* 1431 */                 targetEntity.setFire(var7 * 4);
/*      */               }
/*      */             } 
/*      */             
/* 1435 */             addExhaustion(0.3F);
/*      */           }
/* 1437 */           else if (var6) {
/*      */             
/* 1439 */             targetEntity.extinguish();
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onCriticalHit(Entity p_71009_1_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEnchantmentCritical(Entity p_71047_1_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void respawnPlayer() {}
/*      */ 
/*      */   
/*      */   public void setDead() {
/* 1460 */     super.setDead();
/* 1461 */     this.inventoryContainer.onContainerClosed(this);
/*      */     
/* 1463 */     if (this.openContainer != null)
/*      */     {
/* 1465 */       this.openContainer.onContainerClosed(this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityInsideOpaqueBlock() {
/* 1474 */     return (!this.sleeping && super.isEntityInsideOpaqueBlock());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175144_cb() {
/* 1479 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GameProfile getGameProfile() {
/* 1487 */     return this.gameProfile;
/*      */   }
/*      */ 
/*      */   
/*      */   public EnumStatus func_180469_a(BlockPos p_180469_1_) {
/* 1492 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1494 */       if (isPlayerSleeping() || !isEntityAlive())
/*      */       {
/* 1496 */         return EnumStatus.OTHER_PROBLEM;
/*      */       }
/*      */       
/* 1499 */       if (!this.worldObj.provider.isSurfaceWorld())
/*      */       {
/* 1501 */         return EnumStatus.NOT_POSSIBLE_HERE;
/*      */       }
/*      */       
/* 1504 */       if (this.worldObj.isDaytime())
/*      */       {
/* 1506 */         return EnumStatus.NOT_POSSIBLE_NOW;
/*      */       }
/*      */       
/* 1509 */       if (Math.abs(this.posX - p_180469_1_.getX()) > 3.0D || Math.abs(this.posY - p_180469_1_.getY()) > 2.0D || Math.abs(this.posZ - p_180469_1_.getZ()) > 3.0D)
/*      */       {
/* 1511 */         return EnumStatus.TOO_FAR_AWAY;
/*      */       }
/*      */       
/* 1514 */       double var2 = 8.0D;
/* 1515 */       double var4 = 5.0D;
/* 1516 */       List var6 = this.worldObj.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(p_180469_1_.getX() - var2, p_180469_1_.getY() - var4, p_180469_1_.getZ() - var2, p_180469_1_.getX() + var2, p_180469_1_.getY() + var4, p_180469_1_.getZ() + var2));
/*      */       
/* 1518 */       if (!var6.isEmpty())
/*      */       {
/* 1520 */         return EnumStatus.NOT_SAFE;
/*      */       }
/*      */     } 
/*      */     
/* 1524 */     if (isRiding())
/*      */     {
/* 1526 */       mountEntity(null);
/*      */     }
/*      */     
/* 1529 */     setSize(0.2F, 0.2F);
/*      */     
/* 1531 */     if (this.worldObj.isBlockLoaded(p_180469_1_)) {
/*      */       
/* 1533 */       EnumFacing var7 = (EnumFacing)this.worldObj.getBlockState(p_180469_1_).getValue((IProperty)BlockDirectional.AGE);
/* 1534 */       float var3 = 0.5F;
/* 1535 */       float var8 = 0.5F;
/*      */       
/* 1537 */       switch (SwitchEnumFacing.field_179420_a[var7.ordinal()]) {
/*      */         
/*      */         case 1:
/* 1540 */           var8 = 0.9F;
/*      */           break;
/*      */         
/*      */         case 2:
/* 1544 */           var8 = 0.1F;
/*      */           break;
/*      */         
/*      */         case 3:
/* 1548 */           var3 = 0.1F;
/*      */           break;
/*      */         
/*      */         case 4:
/* 1552 */           var3 = 0.9F;
/*      */           break;
/*      */       } 
/* 1555 */       func_175139_a(var7);
/* 1556 */       setPosition((p_180469_1_.getX() + var3), (p_180469_1_.getY() + 0.6875F), (p_180469_1_.getZ() + var8));
/*      */     }
/*      */     else {
/*      */       
/* 1560 */       setPosition((p_180469_1_.getX() + 0.5F), (p_180469_1_.getY() + 0.6875F), (p_180469_1_.getZ() + 0.5F));
/*      */     } 
/*      */     
/* 1563 */     this.sleeping = true;
/* 1564 */     this.sleepTimer = 0;
/* 1565 */     this.playerLocation = p_180469_1_;
/* 1566 */     this.motionX = this.motionZ = this.motionY = 0.0D;
/*      */     
/* 1568 */     if (!this.worldObj.isRemote)
/*      */     {
/* 1570 */       this.worldObj.updateAllPlayersSleepingFlag();
/*      */     }
/*      */     
/* 1573 */     return EnumStatus.OK;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175139_a(EnumFacing p_175139_1_) {
/* 1578 */     this.field_71079_bU = 0.0F;
/* 1579 */     this.field_71089_bV = 0.0F;
/*      */     
/* 1581 */     switch (SwitchEnumFacing.field_179420_a[p_175139_1_.ordinal()]) {
/*      */       
/*      */       case 1:
/* 1584 */         this.field_71089_bV = -1.8F;
/*      */         break;
/*      */       
/*      */       case 2:
/* 1588 */         this.field_71089_bV = 1.8F;
/*      */         break;
/*      */       
/*      */       case 3:
/* 1592 */         this.field_71079_bU = 1.8F;
/*      */         break;
/*      */       
/*      */       case 4:
/* 1596 */         this.field_71079_bU = -1.8F;
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void wakeUpPlayer(boolean p_70999_1_, boolean updateWorldFlag, boolean setSpawn) {
/* 1605 */     setSize(0.6F, 1.8F);
/* 1606 */     IBlockState var4 = this.worldObj.getBlockState(this.playerLocation);
/*      */     
/* 1608 */     if (this.playerLocation != null && var4.getBlock() == Blocks.bed) {
/*      */       
/* 1610 */       this.worldObj.setBlockState(this.playerLocation, var4.withProperty((IProperty)BlockBed.OCCUPIED_PROP, Boolean.valueOf(false)), 4);
/* 1611 */       BlockPos var5 = BlockBed.getSafeExitLocation(this.worldObj, this.playerLocation, 0);
/*      */       
/* 1613 */       if (var5 == null)
/*      */       {
/* 1615 */         var5 = this.playerLocation.offsetUp();
/*      */       }
/*      */       
/* 1618 */       setPosition((var5.getX() + 0.5F), (var5.getY() + 0.1F), (var5.getZ() + 0.5F));
/*      */     } 
/*      */     
/* 1621 */     this.sleeping = false;
/*      */     
/* 1623 */     if (!this.worldObj.isRemote && updateWorldFlag)
/*      */     {
/* 1625 */       this.worldObj.updateAllPlayersSleepingFlag();
/*      */     }
/*      */     
/* 1628 */     this.sleepTimer = p_70999_1_ ? 0 : 100;
/*      */     
/* 1630 */     if (setSpawn)
/*      */     {
/* 1632 */       func_180473_a(this.playerLocation, false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_175143_p() {
/* 1638 */     return (this.worldObj.getBlockState(this.playerLocation).getBlock() == Blocks.bed);
/*      */   }
/*      */ 
/*      */   
/*      */   public static BlockPos func_180467_a(World worldIn, BlockPos p_180467_1_, boolean p_180467_2_) {
/* 1643 */     if (worldIn.getBlockState(p_180467_1_).getBlock() != Blocks.bed) {
/*      */       
/* 1645 */       if (!p_180467_2_)
/*      */       {
/* 1647 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 1651 */       Material var3 = worldIn.getBlockState(p_180467_1_).getBlock().getMaterial();
/* 1652 */       Material var4 = worldIn.getBlockState(p_180467_1_.offsetUp()).getBlock().getMaterial();
/* 1653 */       boolean var5 = (!var3.isSolid() && !var3.isLiquid());
/* 1654 */       boolean var6 = (!var4.isSolid() && !var4.isLiquid());
/* 1655 */       return (var5 && var6) ? p_180467_1_ : null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1660 */     return BlockBed.getSafeExitLocation(worldIn, p_180467_1_, 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getBedOrientationInDegrees() {
/* 1669 */     if (this.playerLocation != null) {
/*      */       
/* 1671 */       EnumFacing var1 = (EnumFacing)this.worldObj.getBlockState(this.playerLocation).getValue((IProperty)BlockDirectional.AGE);
/*      */       
/* 1673 */       switch (SwitchEnumFacing.field_179420_a[var1.ordinal()]) {
/*      */         
/*      */         case 1:
/* 1676 */           return 90.0F;
/*      */         
/*      */         case 2:
/* 1679 */           return 270.0F;
/*      */         
/*      */         case 3:
/* 1682 */           return 0.0F;
/*      */         
/*      */         case 4:
/* 1685 */           return 180.0F;
/*      */       } 
/*      */     
/*      */     } 
/* 1689 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPlayerSleeping() {
/* 1697 */     return this.sleeping;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPlayerFullyAsleep() {
/* 1705 */     return (this.sleeping && this.sleepTimer >= 100);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSleepTimer() {
/* 1710 */     return this.sleepTimer;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addChatComponentMessage(IChatComponent p_146105_1_) {}
/*      */   
/*      */   public BlockPos func_180470_cg() {
/* 1717 */     return this.spawnChunk;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isSpawnForced() {
/* 1722 */     return this.spawnForced;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180473_a(BlockPos p_180473_1_, boolean p_180473_2_) {
/* 1727 */     if (p_180473_1_ != null) {
/*      */       
/* 1729 */       this.spawnChunk = p_180473_1_;
/* 1730 */       this.spawnForced = p_180473_2_;
/*      */     }
/*      */     else {
/*      */       
/* 1734 */       this.spawnChunk = null;
/* 1735 */       this.spawnForced = false;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void triggerAchievement(StatBase p_71029_1_) {
/* 1744 */     addStat(p_71029_1_, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addStat(StatBase p_71064_1_, int p_71064_2_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_175145_a(StatBase p_175145_1_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void jump() {
/* 1759 */     super.jump();
/* 1760 */     triggerAchievement(StatList.jumpStat);
/*      */     
/* 1762 */     if (isSprinting()) {
/*      */       
/* 1764 */       addExhaustion(0.8F);
/*      */     }
/*      */     else {
/*      */       
/* 1768 */       addExhaustion(0.2F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
/* 1777 */     double var3 = this.posX;
/* 1778 */     double var5 = this.posY;
/* 1779 */     double var7 = this.posZ;
/*      */     
/* 1781 */     if (this.capabilities.isFlying && this.ridingEntity == null) {
/*      */       
/* 1783 */       double var9 = this.motionY;
/* 1784 */       float var11 = this.jumpMovementFactor;
/* 1785 */       this.jumpMovementFactor = this.capabilities.getFlySpeed() * (isSprinting() ? 2 : true);
/* 1786 */       super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
/* 1787 */       this.motionY = var9 * 0.6D;
/* 1788 */       this.jumpMovementFactor = var11;
/*      */     }
/*      */     else {
/*      */       
/* 1792 */       super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
/*      */     } 
/*      */     
/* 1795 */     addMovementStat(this.posX - var3, this.posY - var5, this.posZ - var7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAIMoveSpeed() {
/* 1803 */     return (float)getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addMovementStat(double p_71000_1_, double p_71000_3_, double p_71000_5_) {
/* 1811 */     if (this.ridingEntity == null)
/*      */     {
/*      */ 
/*      */       
/* 1815 */       if (isInsideOfMaterial(Material.water)) {
/*      */         
/* 1817 */         int var7 = Math.round(MathHelper.sqrt_double(p_71000_1_ * p_71000_1_ + p_71000_3_ * p_71000_3_ + p_71000_5_ * p_71000_5_) * 100.0F);
/*      */         
/* 1819 */         if (var7 > 0)
/*      */         {
/* 1821 */           addStat(StatList.distanceDoveStat, var7);
/* 1822 */           addExhaustion(0.015F * var7 * 0.01F);
/*      */         }
/*      */       
/* 1825 */       } else if (isInWater()) {
/*      */         
/* 1827 */         int var7 = Math.round(MathHelper.sqrt_double(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);
/*      */         
/* 1829 */         if (var7 > 0)
/*      */         {
/* 1831 */           addStat(StatList.distanceSwumStat, var7);
/* 1832 */           addExhaustion(0.015F * var7 * 0.01F);
/*      */         }
/*      */       
/* 1835 */       } else if (isOnLadder()) {
/*      */         
/* 1837 */         if (p_71000_3_ > 0.0D)
/*      */         {
/* 1839 */           addStat(StatList.distanceClimbedStat, (int)Math.round(p_71000_3_ * 100.0D));
/*      */         }
/*      */       }
/* 1842 */       else if (this.onGround) {
/*      */         
/* 1844 */         int var7 = Math.round(MathHelper.sqrt_double(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);
/*      */         
/* 1846 */         if (var7 > 0) {
/*      */           
/* 1848 */           addStat(StatList.distanceWalkedStat, var7);
/*      */           
/* 1850 */           if (isSprinting())
/*      */           {
/* 1852 */             addStat(StatList.distanceSprintedStat, var7);
/* 1853 */             addExhaustion(0.099999994F * var7 * 0.01F);
/*      */           }
/*      */           else
/*      */           {
/* 1857 */             if (isSneaking())
/*      */             {
/* 1859 */               addStat(StatList.distanceCrouchedStat, var7);
/*      */             }
/*      */             
/* 1862 */             addExhaustion(0.01F * var7 * 0.01F);
/*      */           }
/*      */         
/*      */         } 
/*      */       } else {
/*      */         
/* 1868 */         int var7 = Math.round(MathHelper.sqrt_double(p_71000_1_ * p_71000_1_ + p_71000_5_ * p_71000_5_) * 100.0F);
/*      */         
/* 1870 */         if (var7 > 25)
/*      */         {
/* 1872 */           addStat(StatList.distanceFlownStat, var7);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void addMountedMovementStat(double p_71015_1_, double p_71015_3_, double p_71015_5_) {
/* 1883 */     if (this.ridingEntity != null) {
/*      */       
/* 1885 */       int var7 = Math.round(MathHelper.sqrt_double(p_71015_1_ * p_71015_1_ + p_71015_3_ * p_71015_3_ + p_71015_5_ * p_71015_5_) * 100.0F);
/*      */       
/* 1887 */       if (var7 > 0)
/*      */       {
/* 1889 */         if (this.ridingEntity instanceof net.minecraft.entity.item.EntityMinecart) {
/*      */           
/* 1891 */           addStat(StatList.distanceByMinecartStat, var7);
/*      */           
/* 1893 */           if (this.startMinecartRidingCoordinate == null)
/*      */           {
/* 1895 */             this.startMinecartRidingCoordinate = new BlockPos((Entity)this);
/*      */           }
/* 1897 */           else if (this.startMinecartRidingCoordinate.distanceSq(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) >= 1000000.0D)
/*      */           {
/* 1899 */             triggerAchievement((StatBase)AchievementList.onARail);
/*      */           }
/*      */         
/* 1902 */         } else if (this.ridingEntity instanceof net.minecraft.entity.item.EntityBoat) {
/*      */           
/* 1904 */           addStat(StatList.distanceByBoatStat, var7);
/*      */         }
/* 1906 */         else if (this.ridingEntity instanceof EntityPig) {
/*      */           
/* 1908 */           addStat(StatList.distanceByPigStat, var7);
/*      */         }
/* 1910 */         else if (this.ridingEntity instanceof EntityHorse) {
/*      */           
/* 1912 */           addStat(StatList.distanceByHorseStat, var7);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void fall(float distance, float damageMultiplier) {
/* 1920 */     if (!this.capabilities.allowFlying) {
/*      */       
/* 1922 */       if (distance >= 2.0F)
/*      */       {
/* 1924 */         addStat(StatList.distanceFallenStat, (int)Math.round(distance * 100.0D));
/*      */       }
/*      */       
/* 1927 */       super.fall(distance, damageMultiplier);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void resetHeight() {
/* 1936 */     if (!func_175149_v())
/*      */     {
/* 1938 */       super.resetHeight();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected String func_146067_o(int p_146067_1_) {
/* 1944 */     return (p_146067_1_ > 4) ? "game.player.hurt.fall.big" : "game.player.hurt.fall.small";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onKillEntity(EntityLivingBase entityLivingIn) {
/* 1952 */     if (entityLivingIn instanceof net.minecraft.entity.monster.IMob)
/*      */     {
/* 1954 */       triggerAchievement((StatBase)AchievementList.killEnemy);
/*      */     }
/*      */     
/* 1957 */     EntityList.EntityEggInfo var2 = (EntityList.EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(EntityList.getEntityID((Entity)entityLivingIn)));
/*      */     
/* 1959 */     if (var2 != null)
/*      */     {
/* 1961 */       triggerAchievement(var2.field_151512_d);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInWeb() {
/* 1970 */     if (!this.capabilities.isFlying)
/*      */     {
/* 1972 */       super.setInWeb();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack getCurrentArmor(int p_82169_1_) {
/* 1978 */     return this.inventory.armorItemInSlot(p_82169_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addExperience(int p_71023_1_) {
/* 1986 */     addScore(p_71023_1_);
/* 1987 */     int var2 = Integer.MAX_VALUE - this.experienceTotal;
/*      */     
/* 1989 */     if (p_71023_1_ > var2)
/*      */     {
/* 1991 */       p_71023_1_ = var2;
/*      */     }
/*      */     
/* 1994 */     this.experience += p_71023_1_ / xpBarCap();
/*      */     
/* 1996 */     for (this.experienceTotal += p_71023_1_; this.experience >= 1.0F; this.experience /= xpBarCap()) {
/*      */       
/* 1998 */       this.experience = (this.experience - 1.0F) * xpBarCap();
/* 1999 */       addExperienceLevel(1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_175138_ci() {
/* 2005 */     return this.field_175152_f;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_71013_b(int p_71013_1_) {
/* 2010 */     this.experienceLevel -= p_71013_1_;
/*      */     
/* 2012 */     if (this.experienceLevel < 0) {
/*      */       
/* 2014 */       this.experienceLevel = 0;
/* 2015 */       this.experience = 0.0F;
/* 2016 */       this.experienceTotal = 0;
/*      */     } 
/*      */     
/* 2019 */     this.field_175152_f = this.rand.nextInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addExperienceLevel(int p_82242_1_) {
/* 2027 */     this.experienceLevel += p_82242_1_;
/*      */     
/* 2029 */     if (this.experienceLevel < 0) {
/*      */       
/* 2031 */       this.experienceLevel = 0;
/* 2032 */       this.experience = 0.0F;
/* 2033 */       this.experienceTotal = 0;
/*      */     } 
/*      */     
/* 2036 */     if (p_82242_1_ > 0 && this.experienceLevel % 5 == 0 && this.field_82249_h < this.ticksExisted - 100.0F) {
/*      */       
/* 2038 */       float var2 = (this.experienceLevel > 30) ? 1.0F : (this.experienceLevel / 30.0F);
/* 2039 */       this.worldObj.playSoundAtEntity((Entity)this, "random.levelup", var2 * 0.75F, 1.0F);
/* 2040 */       this.field_82249_h = this.ticksExisted;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int xpBarCap() {
/* 2050 */     return (this.experienceLevel >= 30) ? (112 + (this.experienceLevel - 30) * 9) : ((this.experienceLevel >= 15) ? (37 + (this.experienceLevel - 15) * 5) : (7 + this.experienceLevel * 2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addExhaustion(float p_71020_1_) {
/* 2058 */     if (!this.capabilities.disableDamage)
/*      */     {
/* 2060 */       if (!this.worldObj.isRemote)
/*      */       {
/* 2062 */         this.foodStats.addExhaustion(p_71020_1_);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FoodStats getFoodStats() {
/* 2072 */     return this.foodStats;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canEat(boolean p_71043_1_) {
/* 2077 */     return ((p_71043_1_ || this.foodStats.needFood()) && !this.capabilities.disableDamage);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean shouldHeal() {
/* 2085 */     return (getHealth() > 0.0F && getHealth() < getMaxHealth());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItemInUse(ItemStack p_71008_1_, int p_71008_2_) {
/* 2093 */     if (p_71008_1_ != this.itemInUse) {
/*      */       
/* 2095 */       this.itemInUse = p_71008_1_;
/* 2096 */       this.itemInUseCount = p_71008_2_;
/*      */       
/* 2098 */       if (!this.worldObj.isRemote)
/*      */       {
/* 2100 */         setEating(true);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175142_cm() {
/* 2107 */     return this.capabilities.allowEdit;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175151_a(BlockPos p_175151_1_, EnumFacing p_175151_2_, ItemStack p_175151_3_) {
/* 2112 */     if (this.capabilities.allowEdit)
/*      */     {
/* 2114 */       return true;
/*      */     }
/* 2116 */     if (p_175151_3_ == null)
/*      */     {
/* 2118 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 2122 */     BlockPos var4 = p_175151_1_.offset(p_175151_2_.getOpposite());
/* 2123 */     Block var5 = this.worldObj.getBlockState(var4).getBlock();
/* 2124 */     return !(!p_175151_3_.canPlaceOn(var5) && !p_175151_3_.canEditBlocks());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getExperiencePoints(EntityPlayer p_70693_1_) {
/* 2133 */     if (this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
/*      */     {
/* 2135 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 2139 */     int var2 = this.experienceLevel * 7;
/* 2140 */     return (var2 > 100) ? 100 : var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isPlayer() {
/* 2149 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAlwaysRenderNameTagForRender() {
/* 2154 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clonePlayer(EntityPlayer p_71049_1_, boolean p_71049_2_) {
/* 2163 */     if (p_71049_2_) {
/*      */       
/* 2165 */       this.inventory.copyInventory(p_71049_1_.inventory);
/* 2166 */       setHealth(p_71049_1_.getHealth());
/* 2167 */       this.foodStats = p_71049_1_.foodStats;
/* 2168 */       this.experienceLevel = p_71049_1_.experienceLevel;
/* 2169 */       this.experienceTotal = p_71049_1_.experienceTotal;
/* 2170 */       this.experience = p_71049_1_.experience;
/* 2171 */       setScore(p_71049_1_.getScore());
/* 2172 */       this.teleportDirection = p_71049_1_.teleportDirection;
/*      */     }
/* 2174 */     else if (this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
/*      */       
/* 2176 */       this.inventory.copyInventory(p_71049_1_.inventory);
/* 2177 */       this.experienceLevel = p_71049_1_.experienceLevel;
/* 2178 */       this.experienceTotal = p_71049_1_.experienceTotal;
/* 2179 */       this.experience = p_71049_1_.experience;
/* 2180 */       setScore(p_71049_1_.getScore());
/*      */     } 
/*      */     
/* 2183 */     this.theInventoryEnderChest = p_71049_1_.theInventoryEnderChest;
/* 2184 */     getDataWatcher().updateObject(10, Byte.valueOf(p_71049_1_.getDataWatcher().getWatchableObjectByte(10)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canTriggerWalking() {
/* 2193 */     return !this.capabilities.isFlying;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendPlayerAbilities() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGameType(WorldSettings.GameType gameType) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/* 2211 */     return this.gameProfile.getName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InventoryEnderChest getInventoryEnderChest() {
/* 2220 */     return this.theInventoryEnderChest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getEquipmentInSlot(int p_71124_1_) {
/* 2228 */     return (p_71124_1_ == 0) ? this.inventory.getCurrentItem() : this.inventory.armorInventory[p_71124_1_ - 1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getHeldItem() {
/* 2236 */     return this.inventory.getCurrentItem();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurrentItemOrArmor(int slotIn, ItemStack itemStackIn) {
/* 2244 */     this.inventory.armorInventory[slotIn] = itemStackIn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInvisibleToPlayer(EntityPlayer playerIn) {
/* 2254 */     if (!isInvisible())
/*      */     {
/* 2256 */       return false;
/*      */     }
/* 2258 */     if (playerIn.func_175149_v())
/*      */     {
/* 2260 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 2264 */     Team var2 = getTeam();
/* 2265 */     return !(var2 != null && playerIn != null && playerIn.getTeam() == var2 && var2.func_98297_h());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract boolean func_175149_v();
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack[] getInventory() {
/* 2276 */     return this.inventory.armorInventory;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPushedByWater() {
/* 2281 */     return !this.capabilities.isFlying;
/*      */   }
/*      */ 
/*      */   
/*      */   public Scoreboard getWorldScoreboard() {
/* 2286 */     return this.worldObj.getScoreboard();
/*      */   }
/*      */ 
/*      */   
/*      */   public Team getTeam() {
/* 2291 */     return (Team)getWorldScoreboard().getPlayersTeam(getName());
/*      */   }
/*      */ 
/*      */   
/*      */   public IChatComponent getDisplayName() {
/* 2296 */     ChatComponentText var1 = new ChatComponentText(ScorePlayerTeam.formatPlayerName(getTeam(), getName()));
/* 2297 */     var1.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + getName() + " "));
/* 2298 */     var1.getChatStyle().setChatHoverEvent(func_174823_aP());
/* 2299 */     var1.getChatStyle().setInsertion(getName());
/* 2300 */     return (IChatComponent)var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getEyeHeight() {
/* 2305 */     float var1 = 1.62F;
/*      */     
/* 2307 */     if (isPlayerSleeping())
/*      */     {
/* 2309 */       var1 = 0.2F;
/*      */     }
/*      */     
/* 2312 */     if (isSneaking())
/*      */     {
/* 2314 */       var1 -= 0.08F;
/*      */     }
/*      */     
/* 2317 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAbsorptionAmount(float p_110149_1_) {
/* 2322 */     if (p_110149_1_ < 0.0F)
/*      */     {
/* 2324 */       p_110149_1_ = 0.0F;
/*      */     }
/*      */     
/* 2327 */     getDataWatcher().updateObject(17, Float.valueOf(p_110149_1_));
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAbsorptionAmount() {
/* 2332 */     return getDataWatcher().getWatchableObjectFloat(17);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static UUID getUUID(GameProfile p_146094_0_) {
/* 2340 */     UUID var1 = p_146094_0_.getId();
/*      */     
/* 2342 */     if (var1 == null)
/*      */     {
/* 2344 */       var1 = func_175147_b(p_146094_0_.getName());
/*      */     }
/*      */     
/* 2347 */     return var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public static UUID func_175147_b(String p_175147_0_) {
/* 2352 */     return UUID.nameUUIDFromBytes(("OfflinePlayer:" + p_175147_0_).getBytes(Charsets.UTF_8));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175146_a(LockCode p_175146_1_) {
/* 2357 */     if (p_175146_1_.isEmpty())
/*      */     {
/* 2359 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 2363 */     ItemStack var2 = getCurrentEquippedItem();
/* 2364 */     return (var2 != null && var2.hasDisplayName()) ? var2.getDisplayName().equals(p_175146_1_.getLock()) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_175148_a(EnumPlayerModelParts p_175148_1_) {
/* 2370 */     return ((getDataWatcher().getWatchableObjectByte(10) & p_175148_1_.func_179327_a()) == p_175148_1_.func_179327_a());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean sendCommandFeedback() {
/* 2375 */     return (MinecraftServer.getServer()).worldServers[0].getGameRules().getGameRuleBooleanValue("sendCommandFeedback");
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
/* 2380 */     if (p_174820_1_ >= 0 && p_174820_1_ < this.inventory.mainInventory.length) {
/*      */       
/* 2382 */       this.inventory.setInventorySlotContents(p_174820_1_, p_174820_2_);
/* 2383 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 2387 */     int var3 = p_174820_1_ - 100;
/*      */ 
/*      */     
/* 2390 */     if (var3 >= 0 && var3 < this.inventory.armorInventory.length) {
/*      */       
/* 2392 */       int i = var3 + 1;
/*      */       
/* 2394 */       if (p_174820_2_ != null && p_174820_2_.getItem() != null)
/*      */       {
/* 2396 */         if (p_174820_2_.getItem() instanceof net.minecraft.item.ItemArmor) {
/*      */           
/* 2398 */           if (EntityLiving.getArmorPosition(p_174820_2_) != i)
/*      */           {
/* 2400 */             return false;
/*      */           }
/*      */         }
/* 2403 */         else if (i != 4 || (p_174820_2_.getItem() != Items.skull && !(p_174820_2_.getItem() instanceof net.minecraft.item.ItemBlock))) {
/*      */           
/* 2405 */           return false;
/*      */         } 
/*      */       }
/*      */       
/* 2409 */       this.inventory.setInventorySlotContents(var3 + this.inventory.mainInventory.length, p_174820_2_);
/* 2410 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 2414 */     int var4 = p_174820_1_ - 200;
/*      */     
/* 2416 */     if (var4 >= 0 && var4 < this.theInventoryEnderChest.getSizeInventory()) {
/*      */       
/* 2418 */       this.theInventoryEnderChest.setInventorySlotContents(var4, p_174820_2_);
/* 2419 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 2423 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_175140_cp() {
/* 2431 */     return this.field_175153_bG;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175150_k(boolean p_175150_1_) {
/* 2436 */     this.field_175153_bG = p_175150_1_;
/*      */   }
/*      */   
/*      */   public enum EnumChatVisibility
/*      */   {
/* 2441 */     FULL("FULL", 0, 0, "options.chat.visibility.full"),
/* 2442 */     SYSTEM("SYSTEM", 1, 1, "options.chat.visibility.system"),
/* 2443 */     HIDDEN("HIDDEN", 2, 2, "options.chat.visibility.hidden");
/* 2444 */     private static final EnumChatVisibility[] field_151432_d = new EnumChatVisibility[(values()).length];
/*      */     
/*      */     private final int chatVisibility;
/*      */     private final String resourceKey;
/* 2448 */     private static final EnumChatVisibility[] $VALUES = new EnumChatVisibility[] { FULL, SYSTEM, HIDDEN };
/*      */     
/*      */     private static final String __OBFID = "CL_00001714";
/*      */ 
/*      */     
/*      */     EnumChatVisibility(String p_i45323_1_, int p_i45323_2_, int p_i45323_3_, String p_i45323_4_) {
/*      */       this.chatVisibility = p_i45323_3_;
/*      */       this.resourceKey = p_i45323_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getChatVisibility() {
/*      */       return this.chatVisibility;
/*      */     }
/*      */ 
/*      */     
/*      */     public static EnumChatVisibility getEnumChatVisibility(int p_151426_0_) {
/*      */       return field_151432_d[p_151426_0_ % field_151432_d.length];
/*      */     }
/*      */     
/*      */     public String getResourceKey() {
/*      */       return this.resourceKey;
/*      */     }
/*      */     
/*      */     static {
/* 2473 */       EnumChatVisibility[] var0 = values();
/* 2474 */       int var1 = var0.length;
/*      */       
/* 2476 */       for (int var2 = 0; var2 < var1; var2++) {
/*      */         
/* 2478 */         EnumChatVisibility var3 = var0[var2];
/* 2479 */         field_151432_d[var3.chatVisibility] = var3;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public enum EnumStatus
/*      */   {
/* 2486 */     OK("OK", 0),
/* 2487 */     NOT_POSSIBLE_HERE("NOT_POSSIBLE_HERE", 1),
/* 2488 */     NOT_POSSIBLE_NOW("NOT_POSSIBLE_NOW", 2),
/* 2489 */     TOO_FAR_AWAY("TOO_FAR_AWAY", 3),
/* 2490 */     OTHER_PROBLEM("OTHER_PROBLEM", 4),
/* 2491 */     NOT_SAFE("NOT_SAFE", 5);
/*      */     
/* 2493 */     private static final EnumStatus[] $VALUES = new EnumStatus[] { OK, NOT_POSSIBLE_HERE, NOT_POSSIBLE_NOW, TOO_FAR_AWAY, OTHER_PROBLEM, NOT_SAFE };
/*      */     private static final String __OBFID = "CL_00001712";
/*      */     
/*      */     static {
/*      */     
/*      */     } }
/*      */   
/*      */   static final class SwitchEnumFacing {
/* 2501 */     static final int[] field_179420_a = new int[(EnumFacing.values()).length];
/*      */     
/*      */     private static final String __OBFID = "CL_00002188";
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 2508 */         field_179420_a[EnumFacing.SOUTH.ordinal()] = 1;
/*      */       }
/* 2510 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2517 */         field_179420_a[EnumFacing.NORTH.ordinal()] = 2;
/*      */       }
/* 2519 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2526 */         field_179420_a[EnumFacing.WEST.ordinal()] = 3;
/*      */       }
/* 2528 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 2535 */         field_179420_a[EnumFacing.EAST.ordinal()] = 4;
/*      */       }
/* 2537 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\player\EntityPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */