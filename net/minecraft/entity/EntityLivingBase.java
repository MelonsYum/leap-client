/*      */ package net.minecraft.entity;
/*      */ 
/*      */ import com.google.common.collect.Maps;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.entity.ai.attributes.BaseAttributeMap;
/*      */ import net.minecraft.entity.ai.attributes.IAttribute;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
/*      */ import net.minecraft.entity.item.EntityXPOrb;
/*      */ import net.minecraft.entity.passive.EntityWolf;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemArmor;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagFloat;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.nbt.NBTTagShort;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.play.server.S04PacketEntityEquipment;
/*      */ import net.minecraft.network.play.server.S0BPacketAnimation;
/*      */ import net.minecraft.network.play.server.S0DPacketCollectItem;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.potion.PotionEffect;
/*      */ import net.minecraft.potion.PotionHelper;
/*      */ import net.minecraft.scoreboard.Team;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.CombatTracker;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class EntityLivingBase
/*      */   extends Entity
/*      */ {
/*   57 */   private static final UUID sprintingSpeedBoostModifierUUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
/*   58 */   private static final AttributeModifier sprintingSpeedBoostModifier = (new AttributeModifier(sprintingSpeedBoostModifierUUID, "Sprinting speed boost", 0.30000001192092896D, 2)).setSaved(false);
/*      */   private BaseAttributeMap attributeMap;
/*   60 */   private final CombatTracker _combatTracker = new CombatTracker(this);
/*   61 */   private final Map activePotionsMap = Maps.newHashMap();
/*      */ 
/*      */   
/*   64 */   private final ItemStack[] previousEquipment = new ItemStack[5];
/*      */ 
/*      */   
/*      */   public boolean isSwingInProgress;
/*      */ 
/*      */   
/*      */   public int swingProgressInt;
/*      */ 
/*      */   
/*      */   public int arrowHitTimer;
/*      */ 
/*      */   
/*      */   public int hurtTime;
/*      */ 
/*      */   
/*      */   public int maxHurtTime;
/*      */ 
/*      */   
/*      */   public float attackedAtYaw;
/*      */   
/*      */   public int deathTime;
/*      */   
/*      */   public float prevSwingProgress;
/*      */   
/*      */   public float swingProgress;
/*      */   
/*      */   public float prevLimbSwingAmount;
/*      */   
/*      */   public float limbSwingAmount;
/*      */   
/*      */   public float limbSwing;
/*      */   
/*   96 */   public int maxHurtResistantTime = 20;
/*      */   
/*      */   public float prevCameraPitch;
/*      */   
/*      */   public float cameraPitch;
/*      */   
/*      */   public float field_70769_ao;
/*      */   
/*      */   public float field_70770_ap;
/*      */   
/*      */   public float renderYawOffset;
/*      */   
/*      */   public float prevRenderYawOffset;
/*      */   
/*      */   public float rotationYawHead;
/*      */   
/*      */   public float prevRotationYawHead;
/*  113 */   public float jumpMovementFactor = 0.02F;
/*      */ 
/*      */   
/*      */   protected EntityPlayer attackingPlayer;
/*      */ 
/*      */   
/*      */   protected int recentlyHit;
/*      */ 
/*      */   
/*      */   protected boolean dead;
/*      */ 
/*      */   
/*      */   protected int entityAge;
/*      */ 
/*      */   
/*      */   protected float field_70768_au;
/*      */ 
/*      */   
/*      */   protected float field_110154_aX;
/*      */ 
/*      */   
/*      */   protected float field_70764_aw;
/*      */ 
/*      */   
/*      */   protected float field_70763_ax;
/*      */ 
/*      */   
/*      */   protected float field_70741_aB;
/*      */ 
/*      */   
/*      */   protected int scoreValue;
/*      */ 
/*      */   
/*      */   protected float lastDamage;
/*      */ 
/*      */   
/*      */   protected boolean isJumping;
/*      */ 
/*      */   
/*      */   public float moveStrafing;
/*      */ 
/*      */   
/*      */   public float moveForward;
/*      */ 
/*      */   
/*      */   protected float randomYawVelocity;
/*      */ 
/*      */   
/*      */   protected int newPosRotationIncrements;
/*      */   
/*      */   protected double newPosX;
/*      */   
/*      */   protected double newPosY;
/*      */   
/*      */   protected double newPosZ;
/*      */   
/*      */   protected double newRotationYaw;
/*      */   
/*      */   protected double newRotationPitch;
/*      */   
/*      */   private boolean potionsNeedUpdate = true;
/*      */   
/*      */   private EntityLivingBase entityLivingToAttack;
/*      */   
/*      */   private int revengeTimer;
/*      */   
/*      */   private EntityLivingBase lastAttacker;
/*      */   
/*      */   private int lastAttackerTime;
/*      */   
/*      */   private float landMovementFactor;
/*      */   
/*      */   private int jumpTicks;
/*      */   
/*      */   private float field_110151_bq;
/*      */   
/*      */   private static final String __OBFID = "CL_00001549";
/*      */ 
/*      */   
/*      */   public void func_174812_G() {
/*  193 */     attackEntityFrom(DamageSource.outOfWorld, Float.MAX_VALUE);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLivingBase(World worldIn) {
/*  198 */     super(worldIn);
/*  199 */     applyEntityAttributes();
/*  200 */     setHealth(getMaxHealth());
/*  201 */     this.preventEntitySpawning = true;
/*  202 */     this.field_70770_ap = (float)((Math.random() + 1.0D) * 0.009999999776482582D);
/*  203 */     setPosition(this.posX, this.posY, this.posZ);
/*  204 */     this.field_70769_ao = (float)Math.random() * 12398.0F;
/*  205 */     this.rotationYaw = (float)(Math.random() * Math.PI * 2.0D);
/*  206 */     this.rotationYawHead = this.rotationYaw;
/*  207 */     this.stepHeight = 0.6F;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  212 */     this.dataWatcher.addObject(7, Integer.valueOf(0));
/*  213 */     this.dataWatcher.addObject(8, Byte.valueOf((byte)0));
/*  214 */     this.dataWatcher.addObject(9, Byte.valueOf((byte)0));
/*  215 */     this.dataWatcher.addObject(6, Float.valueOf(1.0F));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*  220 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.maxHealth);
/*  221 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.knockbackResistance);
/*  222 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.movementSpeed);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
/*  227 */     if (!isInWater())
/*      */     {
/*  229 */       handleWaterMovement();
/*      */     }
/*      */     
/*  232 */     if (!this.worldObj.isRemote && this.fallDistance > 3.0F && p_180433_3_) {
/*      */       
/*  234 */       IBlockState var6 = this.worldObj.getBlockState(p_180433_5_);
/*  235 */       Block var7 = var6.getBlock();
/*  236 */       float var8 = MathHelper.ceiling_float_int(this.fallDistance - 3.0F);
/*      */       
/*  238 */       if (var7.getMaterial() != Material.air) {
/*      */         
/*  240 */         double var9 = Math.min(0.2F + var8 / 15.0F, 10.0F);
/*      */         
/*  242 */         if (var9 > 2.5D)
/*      */         {
/*  244 */           var9 = 2.5D;
/*      */         }
/*      */         
/*  247 */         int var11 = (int)(150.0D * var9);
/*  248 */         ((WorldServer)this.worldObj).func_175739_a(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY, this.posZ, var11, 0.0D, 0.0D, 0.0D, 0.15000000596046448D, new int[] { Block.getStateId(var6) });
/*      */       } 
/*      */     } 
/*      */     
/*  252 */     super.func_180433_a(p_180433_1_, p_180433_3_, p_180433_4_, p_180433_5_);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBreatheUnderwater() {
/*  257 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityUpdate() {
/*  265 */     this.prevSwingProgress = this.swingProgress;
/*  266 */     super.onEntityUpdate();
/*  267 */     this.worldObj.theProfiler.startSection("livingEntityBaseTick");
/*  268 */     boolean var1 = this instanceof EntityPlayer;
/*      */     
/*  270 */     if (isEntityAlive())
/*      */     {
/*  272 */       if (isEntityInsideOpaqueBlock()) {
/*      */         
/*  274 */         attackEntityFrom(DamageSource.inWall, 1.0F);
/*      */       }
/*  276 */       else if (var1 && !this.worldObj.getWorldBorder().contains(getEntityBoundingBox())) {
/*      */         
/*  278 */         double var2 = this.worldObj.getWorldBorder().getClosestDistance(this) + this.worldObj.getWorldBorder().getDamageBuffer();
/*      */         
/*  280 */         if (var2 < 0.0D)
/*      */         {
/*  282 */           attackEntityFrom(DamageSource.inWall, Math.max(1, MathHelper.floor_double(-var2 * this.worldObj.getWorldBorder().func_177727_n())));
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  287 */     if (isImmuneToFire() || this.worldObj.isRemote)
/*      */     {
/*  289 */       extinguish();
/*      */     }
/*      */     
/*  292 */     boolean var7 = (var1 && ((EntityPlayer)this).capabilities.disableDamage);
/*      */     
/*  294 */     if (isEntityAlive() && isInsideOfMaterial(Material.water)) {
/*      */       
/*  296 */       if (!canBreatheUnderwater() && !isPotionActive(Potion.waterBreathing.id) && !var7) {
/*      */         
/*  298 */         setAir(decreaseAirSupply(getAir()));
/*      */         
/*  300 */         if (getAir() == -20) {
/*      */           
/*  302 */           setAir(0);
/*      */           
/*  304 */           for (int var3 = 0; var3 < 8; var3++) {
/*      */             
/*  306 */             float var4 = this.rand.nextFloat() - this.rand.nextFloat();
/*  307 */             float var5 = this.rand.nextFloat() - this.rand.nextFloat();
/*  308 */             float var6 = this.rand.nextFloat() - this.rand.nextFloat();
/*  309 */             this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + var4, this.posY + var5, this.posZ + var6, this.motionX, this.motionY, this.motionZ, new int[0]);
/*      */           } 
/*      */           
/*  312 */           attackEntityFrom(DamageSource.drown, 2.0F);
/*      */         } 
/*      */       } 
/*      */       
/*  316 */       if (!this.worldObj.isRemote && isRiding() && this.ridingEntity instanceof EntityLivingBase)
/*      */       {
/*  318 */         mountEntity((Entity)null);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  323 */       setAir(300);
/*      */     } 
/*      */     
/*  326 */     if (isEntityAlive() && isWet())
/*      */     {
/*  328 */       extinguish();
/*      */     }
/*      */     
/*  331 */     this.prevCameraPitch = this.cameraPitch;
/*      */     
/*  333 */     if (this.hurtTime > 0)
/*      */     {
/*  335 */       this.hurtTime--;
/*      */     }
/*      */     
/*  338 */     if (this.hurtResistantTime > 0 && !(this instanceof net.minecraft.entity.player.EntityPlayerMP))
/*      */     {
/*  340 */       this.hurtResistantTime--;
/*      */     }
/*      */     
/*  343 */     if (getHealth() <= 0.0F)
/*      */     {
/*  345 */       onDeathUpdate();
/*      */     }
/*      */     
/*  348 */     if (this.recentlyHit > 0) {
/*      */       
/*  350 */       this.recentlyHit--;
/*      */     }
/*      */     else {
/*      */       
/*  354 */       this.attackingPlayer = null;
/*      */     } 
/*      */     
/*  357 */     if (this.lastAttacker != null && !this.lastAttacker.isEntityAlive())
/*      */     {
/*  359 */       this.lastAttacker = null;
/*      */     }
/*      */     
/*  362 */     if (this.entityLivingToAttack != null)
/*      */     {
/*  364 */       if (!this.entityLivingToAttack.isEntityAlive()) {
/*      */         
/*  366 */         setRevengeTarget((EntityLivingBase)null);
/*      */       }
/*  368 */       else if (this.ticksExisted - this.revengeTimer > 100) {
/*      */         
/*  370 */         setRevengeTarget((EntityLivingBase)null);
/*      */       } 
/*      */     }
/*      */     
/*  374 */     updatePotionEffects();
/*  375 */     this.field_70763_ax = this.field_70764_aw;
/*  376 */     this.prevRenderYawOffset = this.renderYawOffset;
/*  377 */     this.prevRotationYawHead = this.rotationYawHead;
/*  378 */     this.prevRotationYaw = this.rotationYaw;
/*  379 */     this.prevRotationPitch = this.rotationPitch;
/*  380 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isChild() {
/*  388 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void onDeathUpdate() {
/*  396 */     this.deathTime++;
/*      */     
/*  398 */     if (this.deathTime == 20) {
/*      */ 
/*      */ 
/*      */       
/*  402 */       if (!this.worldObj.isRemote && (this.recentlyHit > 0 || isPlayer()) && func_146066_aG() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
/*      */         
/*  404 */         int i = getExperiencePoints(this.attackingPlayer);
/*      */         
/*  406 */         while (i > 0) {
/*      */           
/*  408 */           int var2 = EntityXPOrb.getXPSplit(i);
/*  409 */           i -= var2;
/*  410 */           this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var2));
/*      */         } 
/*      */       } 
/*      */       
/*  414 */       setDead();
/*      */       
/*  416 */       for (int var1 = 0; var1 < 20; var1++) {
/*      */         
/*  418 */         double var8 = this.rand.nextGaussian() * 0.02D;
/*  419 */         double var4 = this.rand.nextGaussian() * 0.02D;
/*  420 */         double var6 = this.rand.nextGaussian() * 0.02D;
/*  421 */         this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var8, var4, var6, new int[0]);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean func_146066_aG() {
/*  428 */     return !isChild();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int decreaseAirSupply(int p_70682_1_) {
/*  436 */     int var2 = EnchantmentHelper.func_180319_a(this);
/*  437 */     return (var2 > 0 && this.rand.nextInt(var2 + 1) > 0) ? p_70682_1_ : (p_70682_1_ - 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getExperiencePoints(EntityPlayer p_70693_1_) {
/*  445 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isPlayer() {
/*  453 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public Random getRNG() {
/*  458 */     return this.rand;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLivingBase getAITarget() {
/*  463 */     return this.entityLivingToAttack;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRevengeTimer() {
/*  468 */     return this.revengeTimer;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRevengeTarget(EntityLivingBase p_70604_1_) {
/*  473 */     this.entityLivingToAttack = p_70604_1_;
/*  474 */     this.revengeTimer = this.ticksExisted;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLivingBase getLastAttacker() {
/*  479 */     return this.lastAttacker;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLastAttackerTime() {
/*  484 */     return this.lastAttackerTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastAttacker(Entity p_130011_1_) {
/*  489 */     if (p_130011_1_ instanceof EntityLivingBase) {
/*      */       
/*  491 */       this.lastAttacker = (EntityLivingBase)p_130011_1_;
/*      */     }
/*      */     else {
/*      */       
/*  495 */       this.lastAttacker = null;
/*      */     } 
/*      */     
/*  498 */     this.lastAttackerTime = this.ticksExisted;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAge() {
/*  503 */     return this.entityAge;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  511 */     tagCompound.setFloat("HealF", getHealth());
/*  512 */     tagCompound.setShort("Health", (short)(int)Math.ceil(getHealth()));
/*  513 */     tagCompound.setShort("HurtTime", (short)this.hurtTime);
/*  514 */     tagCompound.setInteger("HurtByTimestamp", this.revengeTimer);
/*  515 */     tagCompound.setShort("DeathTime", (short)this.deathTime);
/*  516 */     tagCompound.setFloat("AbsorptionAmount", getAbsorptionAmount());
/*  517 */     ItemStack[] var2 = getInventory();
/*  518 */     int var3 = var2.length;
/*      */     
/*      */     int var4;
/*      */     
/*  522 */     for (var4 = 0; var4 < var3; var4++) {
/*      */       
/*  524 */       ItemStack var5 = var2[var4];
/*      */       
/*  526 */       if (var5 != null)
/*      */       {
/*  528 */         this.attributeMap.removeAttributeModifiers(var5.getAttributeModifiers());
/*      */       }
/*      */     } 
/*      */     
/*  532 */     tagCompound.setTag("Attributes", (NBTBase)SharedMonsterAttributes.writeBaseAttributeMapToNBT(getAttributeMap()));
/*  533 */     var2 = getInventory();
/*  534 */     var3 = var2.length;
/*      */     
/*  536 */     for (var4 = 0; var4 < var3; var4++) {
/*      */       
/*  538 */       ItemStack var5 = var2[var4];
/*      */       
/*  540 */       if (var5 != null)
/*      */       {
/*  542 */         this.attributeMap.applyAttributeModifiers(var5.getAttributeModifiers());
/*      */       }
/*      */     } 
/*      */     
/*  546 */     if (!this.activePotionsMap.isEmpty()) {
/*      */       
/*  548 */       NBTTagList var6 = new NBTTagList();
/*  549 */       Iterator<PotionEffect> var7 = this.activePotionsMap.values().iterator();
/*      */       
/*  551 */       while (var7.hasNext()) {
/*      */         
/*  553 */         PotionEffect var8 = var7.next();
/*  554 */         var6.appendTag((NBTBase)var8.writeCustomPotionEffectToNBT(new NBTTagCompound()));
/*      */       } 
/*      */       
/*  557 */       tagCompound.setTag("ActiveEffects", (NBTBase)var6);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  566 */     setAbsorptionAmount(tagCompund.getFloat("AbsorptionAmount"));
/*      */     
/*  568 */     if (tagCompund.hasKey("Attributes", 9) && this.worldObj != null && !this.worldObj.isRemote)
/*      */     {
/*  570 */       SharedMonsterAttributes.func_151475_a(getAttributeMap(), tagCompund.getTagList("Attributes", 10));
/*      */     }
/*      */     
/*  573 */     if (tagCompund.hasKey("ActiveEffects", 9)) {
/*      */       
/*  575 */       NBTTagList var2 = tagCompund.getTagList("ActiveEffects", 10);
/*      */       
/*  577 */       for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*      */         
/*  579 */         NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/*  580 */         PotionEffect var5 = PotionEffect.readCustomPotionEffectFromNBT(var4);
/*      */         
/*  582 */         if (var5 != null)
/*      */         {
/*  584 */           this.activePotionsMap.put(Integer.valueOf(var5.getPotionID()), var5);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  589 */     if (tagCompund.hasKey("HealF", 99)) {
/*      */       
/*  591 */       setHealth(tagCompund.getFloat("HealF"));
/*      */     }
/*      */     else {
/*      */       
/*  595 */       NBTBase var6 = tagCompund.getTag("Health");
/*      */       
/*  597 */       if (var6 == null) {
/*      */         
/*  599 */         setHealth(getMaxHealth());
/*      */       }
/*  601 */       else if (var6.getId() == 5) {
/*      */         
/*  603 */         setHealth(((NBTTagFloat)var6).getFloat());
/*      */       }
/*  605 */       else if (var6.getId() == 2) {
/*      */         
/*  607 */         setHealth(((NBTTagShort)var6).getShort());
/*      */       } 
/*      */     } 
/*      */     
/*  611 */     this.hurtTime = tagCompund.getShort("HurtTime");
/*  612 */     this.deathTime = tagCompund.getShort("DeathTime");
/*  613 */     this.revengeTimer = tagCompund.getInteger("HurtByTimestamp");
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updatePotionEffects() {
/*  618 */     Iterator<Integer> var1 = this.activePotionsMap.keySet().iterator();
/*      */     
/*  620 */     while (var1.hasNext()) {
/*      */       
/*  622 */       Integer var2 = var1.next();
/*  623 */       PotionEffect var3 = (PotionEffect)this.activePotionsMap.get(var2);
/*      */       
/*  625 */       if (!var3.onUpdate(this)) {
/*      */         
/*  627 */         if (!this.worldObj.isRemote) {
/*      */           
/*  629 */           var1.remove();
/*  630 */           onFinishedPotionEffect(var3);
/*      */         }  continue;
/*      */       } 
/*  633 */       if (var3.getDuration() % 600 == 0)
/*      */       {
/*  635 */         onChangedPotionEffect(var3, false);
/*      */       }
/*      */     } 
/*      */     
/*  639 */     if (this.potionsNeedUpdate) {
/*      */       
/*  641 */       if (!this.worldObj.isRemote)
/*      */       {
/*  643 */         func_175135_B();
/*      */       }
/*      */       
/*  646 */       this.potionsNeedUpdate = false;
/*      */     } 
/*      */     
/*  649 */     int var11 = this.dataWatcher.getWatchableObjectInt(7);
/*  650 */     boolean var12 = (this.dataWatcher.getWatchableObjectByte(8) > 0);
/*      */     
/*  652 */     if (var11 > 0) {
/*      */       int i;
/*  654 */       boolean var4 = false;
/*      */       
/*  656 */       if (!isInvisible()) {
/*      */         
/*  658 */         var4 = this.rand.nextBoolean();
/*      */       }
/*      */       else {
/*      */         
/*  662 */         var4 = (this.rand.nextInt(15) == 0);
/*      */       } 
/*      */       
/*  665 */       if (var12)
/*      */       {
/*  667 */         i = var4 & ((this.rand.nextInt(5) == 0) ? 1 : 0);
/*      */       }
/*      */       
/*  670 */       if (i != 0 && var11 > 0) {
/*      */         
/*  672 */         double var5 = (var11 >> 16 & 0xFF) / 255.0D;
/*  673 */         double var7 = (var11 >> 8 & 0xFF) / 255.0D;
/*  674 */         double var9 = (var11 >> 0 & 0xFF) / 255.0D;
/*  675 */         this.worldObj.spawnParticle(var12 ? EnumParticleTypes.SPELL_MOB_AMBIENT : EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, var5, var7, var9, new int[0]);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175135_B() {
/*  682 */     if (this.activePotionsMap.isEmpty()) {
/*      */       
/*  684 */       func_175133_bi();
/*  685 */       setInvisible(false);
/*      */     }
/*      */     else {
/*      */       
/*  689 */       int var1 = PotionHelper.calcPotionLiquidColor(this.activePotionsMap.values());
/*  690 */       this.dataWatcher.updateObject(8, Byte.valueOf((byte)(PotionHelper.func_82817_b(this.activePotionsMap.values()) ? 1 : 0)));
/*  691 */       this.dataWatcher.updateObject(7, Integer.valueOf(var1));
/*  692 */       setInvisible(isPotionActive(Potion.invisibility.id));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175133_bi() {
/*  698 */     this.dataWatcher.updateObject(8, Byte.valueOf((byte)0));
/*  699 */     this.dataWatcher.updateObject(7, Integer.valueOf(0));
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearActivePotions() {
/*  704 */     Iterator<Integer> var1 = this.activePotionsMap.keySet().iterator();
/*      */     
/*  706 */     while (var1.hasNext()) {
/*      */       
/*  708 */       Integer var2 = var1.next();
/*  709 */       PotionEffect var3 = (PotionEffect)this.activePotionsMap.get(var2);
/*      */       
/*  711 */       if (!this.worldObj.isRemote) {
/*      */         
/*  713 */         var1.remove();
/*  714 */         onFinishedPotionEffect(var3);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection getActivePotionEffects() {
/*  721 */     return this.activePotionsMap.values();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPotionActive(int p_82165_1_) {
/*  726 */     return this.activePotionsMap.containsKey(Integer.valueOf(p_82165_1_));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPotionActive(Potion p_70644_1_) {
/*  731 */     return this.activePotionsMap.containsKey(Integer.valueOf(p_70644_1_.id));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PotionEffect getActivePotionEffect(Potion p_70660_1_) {
/*  739 */     return (PotionEffect)this.activePotionsMap.get(Integer.valueOf(p_70660_1_.id));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPotionEffect(PotionEffect p_70690_1_) {
/*  747 */     if (isPotionApplicable(p_70690_1_))
/*      */     {
/*  749 */       if (this.activePotionsMap.containsKey(Integer.valueOf(p_70690_1_.getPotionID()))) {
/*      */         
/*  751 */         ((PotionEffect)this.activePotionsMap.get(Integer.valueOf(p_70690_1_.getPotionID()))).combine(p_70690_1_);
/*  752 */         onChangedPotionEffect((PotionEffect)this.activePotionsMap.get(Integer.valueOf(p_70690_1_.getPotionID())), true);
/*      */       }
/*      */       else {
/*      */         
/*  756 */         this.activePotionsMap.put(Integer.valueOf(p_70690_1_.getPotionID()), p_70690_1_);
/*  757 */         onNewPotionEffect(p_70690_1_);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPotionApplicable(PotionEffect p_70687_1_) {
/*  764 */     if (getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) {
/*      */       
/*  766 */       int var2 = p_70687_1_.getPotionID();
/*      */       
/*  768 */       if (var2 == Potion.regeneration.id || var2 == Potion.poison.id)
/*      */       {
/*  770 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  774 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityUndead() {
/*  782 */     return (getCreatureAttribute() == EnumCreatureAttribute.UNDEAD);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removePotionEffectClient(int p_70618_1_) {
/*  790 */     this.activePotionsMap.remove(Integer.valueOf(p_70618_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removePotionEffect(int p_82170_1_) {
/*  798 */     PotionEffect var2 = (PotionEffect)this.activePotionsMap.remove(Integer.valueOf(p_82170_1_));
/*      */     
/*  800 */     if (var2 != null)
/*      */     {
/*  802 */       onFinishedPotionEffect(var2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onNewPotionEffect(PotionEffect p_70670_1_) {
/*  808 */     this.potionsNeedUpdate = true;
/*      */     
/*  810 */     if (!this.worldObj.isRemote)
/*      */     {
/*  812 */       Potion.potionTypes[p_70670_1_.getPotionID()].applyAttributesModifiersToEntity(this, getAttributeMap(), p_70670_1_.getAmplifier());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onChangedPotionEffect(PotionEffect p_70695_1_, boolean p_70695_2_) {
/*  818 */     this.potionsNeedUpdate = true;
/*      */     
/*  820 */     if (p_70695_2_ && !this.worldObj.isRemote) {
/*      */       
/*  822 */       Potion.potionTypes[p_70695_1_.getPotionID()].removeAttributesModifiersFromEntity(this, getAttributeMap(), p_70695_1_.getAmplifier());
/*  823 */       Potion.potionTypes[p_70695_1_.getPotionID()].applyAttributesModifiersToEntity(this, getAttributeMap(), p_70695_1_.getAmplifier());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void onFinishedPotionEffect(PotionEffect p_70688_1_) {
/*  829 */     this.potionsNeedUpdate = true;
/*      */     
/*  831 */     if (!this.worldObj.isRemote)
/*      */     {
/*  833 */       Potion.potionTypes[p_70688_1_.getPotionID()].removeAttributesModifiersFromEntity(this, getAttributeMap(), p_70688_1_.getAmplifier());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void heal(float p_70691_1_) {
/*  842 */     float var2 = getHealth();
/*      */     
/*  844 */     if (var2 > 0.0F)
/*      */     {
/*  846 */       setHealth(var2 + p_70691_1_);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final float getHealth() {
/*  852 */     return this.dataWatcher.getWatchableObjectFloat(6);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHealth(float p_70606_1_) {
/*  857 */     this.dataWatcher.updateObject(6, Float.valueOf(MathHelper.clamp_float(p_70606_1_, 0.0F, getMaxHealth())));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  865 */     if (func_180431_b(source))
/*      */     {
/*  867 */       return false;
/*      */     }
/*  869 */     if (this.worldObj.isRemote)
/*      */     {
/*  871 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  875 */     this.entityAge = 0;
/*      */     
/*  877 */     if (getHealth() <= 0.0F)
/*      */     {
/*  879 */       return false;
/*      */     }
/*  881 */     if (source.isFireDamage() && isPotionActive(Potion.fireResistance))
/*      */     {
/*  883 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  887 */     if ((source == DamageSource.anvil || source == DamageSource.fallingBlock) && getEquipmentInSlot(4) != null) {
/*      */       
/*  889 */       getEquipmentInSlot(4).damageItem((int)(amount * 4.0F + this.rand.nextFloat() * amount * 2.0F), this);
/*  890 */       amount *= 0.75F;
/*      */     } 
/*      */     
/*  893 */     this.limbSwingAmount = 1.5F;
/*  894 */     boolean var3 = true;
/*      */     
/*  896 */     if (this.hurtResistantTime > this.maxHurtResistantTime / 2.0F) {
/*      */       
/*  898 */       if (amount <= this.lastDamage)
/*      */       {
/*  900 */         return false;
/*      */       }
/*      */       
/*  903 */       damageEntity(source, amount - this.lastDamage);
/*  904 */       this.lastDamage = amount;
/*  905 */       var3 = false;
/*      */     }
/*      */     else {
/*      */       
/*  909 */       this.lastDamage = amount;
/*  910 */       this.hurtResistantTime = this.maxHurtResistantTime;
/*  911 */       damageEntity(source, amount);
/*  912 */       this.hurtTime = this.maxHurtTime = 10;
/*      */     } 
/*      */     
/*  915 */     this.attackedAtYaw = 0.0F;
/*  916 */     Entity var4 = source.getEntity();
/*      */     
/*  918 */     if (var4 != null) {
/*      */       
/*  920 */       if (var4 instanceof EntityLivingBase)
/*      */       {
/*  922 */         setRevengeTarget((EntityLivingBase)var4);
/*      */       }
/*      */       
/*  925 */       if (var4 instanceof EntityPlayer) {
/*      */         
/*  927 */         this.recentlyHit = 100;
/*  928 */         this.attackingPlayer = (EntityPlayer)var4;
/*      */       }
/*  930 */       else if (var4 instanceof EntityWolf) {
/*      */         
/*  932 */         EntityWolf var5 = (EntityWolf)var4;
/*      */         
/*  934 */         if (var5.isTamed()) {
/*      */           
/*  936 */           this.recentlyHit = 100;
/*  937 */           this.attackingPlayer = null;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  942 */     if (var3) {
/*      */       
/*  944 */       this.worldObj.setEntityState(this, (byte)2);
/*      */       
/*  946 */       if (source != DamageSource.drown)
/*      */       {
/*  948 */         setBeenAttacked();
/*      */       }
/*      */       
/*  951 */       if (var4 != null) {
/*      */         
/*  953 */         double var9 = var4.posX - this.posX;
/*      */         
/*      */         double var7;
/*  956 */         for (var7 = var4.posZ - this.posZ; var9 * var9 + var7 * var7 < 1.0E-4D; var7 = (Math.random() - Math.random()) * 0.01D)
/*      */         {
/*  958 */           var9 = (Math.random() - Math.random()) * 0.01D;
/*      */         }
/*      */         
/*  961 */         this.attackedAtYaw = (float)(Math.atan2(var7, var9) * 180.0D / Math.PI - this.rotationYaw);
/*  962 */         knockBack(var4, amount, var9, var7);
/*      */       }
/*      */       else {
/*      */         
/*  966 */         this.attackedAtYaw = ((int)(Math.random() * 2.0D) * 180);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  972 */     if (getHealth() <= 0.0F) {
/*      */       
/*  974 */       String var10 = getDeathSound();
/*      */       
/*  976 */       if (var3 && var10 != null)
/*      */       {
/*  978 */         playSound(var10, getSoundVolume(), getSoundPitch());
/*      */       }
/*      */       
/*  981 */       onDeath(source);
/*      */     }
/*      */     else {
/*      */       
/*  985 */       String var10 = getHurtSound();
/*      */       
/*  987 */       if (var3 && var10 != null)
/*      */       {
/*  989 */         playSound(var10, getSoundVolume(), getSoundPitch());
/*      */       }
/*      */     } 
/*      */     
/*  993 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderBrokenItemStack(ItemStack p_70669_1_) {
/* 1003 */     playSound("random.break", 0.8F, 0.8F + this.worldObj.rand.nextFloat() * 0.4F);
/*      */     
/* 1005 */     for (int var2 = 0; var2 < 5; var2++) {
/*      */       
/* 1007 */       Vec3 var3 = new Vec3((this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
/* 1008 */       var3 = var3.rotatePitch(-this.rotationPitch * 3.1415927F / 180.0F);
/* 1009 */       var3 = var3.rotateYaw(-this.rotationYaw * 3.1415927F / 180.0F);
/* 1010 */       double var4 = -this.rand.nextFloat() * 0.6D - 0.3D;
/* 1011 */       Vec3 var6 = new Vec3((this.rand.nextFloat() - 0.5D) * 0.3D, var4, 0.6D);
/* 1012 */       var6 = var6.rotatePitch(-this.rotationPitch * 3.1415927F / 180.0F);
/* 1013 */       var6 = var6.rotateYaw(-this.rotationYaw * 3.1415927F / 180.0F);
/* 1014 */       var6 = var6.addVector(this.posX, this.posY + getEyeHeight(), this.posZ);
/* 1015 */       this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, var6.xCoord, var6.yCoord, var6.zCoord, var3.xCoord, var3.yCoord + 0.05D, var3.zCoord, new int[] { Item.getIdFromItem(p_70669_1_.getItem()) });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource cause) {
/* 1024 */     Entity var2 = cause.getEntity();
/* 1025 */     EntityLivingBase var3 = func_94060_bK();
/*      */     
/* 1027 */     if (this.scoreValue >= 0 && var3 != null)
/*      */     {
/* 1029 */       var3.addToPlayerScore(this, this.scoreValue);
/*      */     }
/*      */     
/* 1032 */     if (var2 != null)
/*      */     {
/* 1034 */       var2.onKillEntity(this);
/*      */     }
/*      */     
/* 1037 */     this.dead = true;
/* 1038 */     getCombatTracker().func_94549_h();
/*      */     
/* 1040 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1042 */       int var4 = 0;
/*      */       
/* 1044 */       if (var2 instanceof EntityPlayer)
/*      */       {
/* 1046 */         var4 = EnchantmentHelper.getLootingModifier((EntityLivingBase)var2);
/*      */       }
/*      */       
/* 1049 */       if (func_146066_aG() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
/*      */         
/* 1051 */         dropFewItems((this.recentlyHit > 0), var4);
/* 1052 */         dropEquipment((this.recentlyHit > 0), var4);
/*      */         
/* 1054 */         if (this.recentlyHit > 0 && this.rand.nextFloat() < 0.025F + var4 * 0.01F)
/*      */         {
/* 1056 */           addRandomArmor();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1061 */     this.worldObj.setEntityState(this, (byte)3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropEquipment(boolean p_82160_1_, int p_82160_2_) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
/* 1074 */     if (this.rand.nextDouble() >= getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue()) {
/*      */       
/* 1076 */       this.isAirBorne = true;
/* 1077 */       float var7 = MathHelper.sqrt_double(p_70653_3_ * p_70653_3_ + p_70653_5_ * p_70653_5_);
/* 1078 */       float var8 = 0.4F;
/* 1079 */       this.motionX /= 2.0D;
/* 1080 */       this.motionY /= 2.0D;
/* 1081 */       this.motionZ /= 2.0D;
/* 1082 */       this.motionX -= p_70653_3_ / var7 * var8;
/* 1083 */       this.motionY += var8;
/* 1084 */       this.motionZ -= p_70653_5_ / var7 * var8;
/*      */       
/* 1086 */       if (this.motionY > 0.4000000059604645D)
/*      */       {
/* 1088 */         this.motionY = 0.4000000059604645D;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getHurtSound() {
/* 1098 */     return "game.neutral.hurt";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getDeathSound() {
/* 1106 */     return "game.neutral.die";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void addRandomArmor() {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOnLadder() {
/* 1124 */     int var1 = MathHelper.floor_double(this.posX);
/* 1125 */     int var2 = MathHelper.floor_double((getEntityBoundingBox()).minY);
/* 1126 */     int var3 = MathHelper.floor_double(this.posZ);
/* 1127 */     Block var4 = this.worldObj.getBlockState(new BlockPos(var1, var2, var3)).getBlock();
/* 1128 */     return ((var4 == Blocks.ladder || var4 == Blocks.vine) && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).func_175149_v()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityAlive() {
/* 1136 */     return (!this.isDead && getHealth() > 0.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public void fall(float distance, float damageMultiplier) {
/* 1141 */     super.fall(distance, damageMultiplier);
/* 1142 */     PotionEffect var3 = getActivePotionEffect(Potion.jump);
/* 1143 */     float var4 = (var3 != null) ? (var3.getAmplifier() + 1) : 0.0F;
/* 1144 */     int var5 = MathHelper.ceiling_float_int((distance - 3.0F - var4) * damageMultiplier);
/*      */     
/* 1146 */     if (var5 > 0) {
/*      */       
/* 1148 */       playSound(func_146067_o(var5), 1.0F, 1.0F);
/* 1149 */       attackEntityFrom(DamageSource.fall, var5);
/* 1150 */       int var6 = MathHelper.floor_double(this.posX);
/* 1151 */       int var7 = MathHelper.floor_double(this.posY - 0.20000000298023224D);
/* 1152 */       int var8 = MathHelper.floor_double(this.posZ);
/* 1153 */       Block var9 = this.worldObj.getBlockState(new BlockPos(var6, var7, var8)).getBlock();
/*      */       
/* 1155 */       if (var9.getMaterial() != Material.air) {
/*      */         
/* 1157 */         Block.SoundType var10 = var9.stepSound;
/* 1158 */         playSound(var10.getStepSound(), var10.getVolume() * 0.5F, var10.getFrequency() * 0.75F);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected String func_146067_o(int p_146067_1_) {
/* 1165 */     return (p_146067_1_ > 4) ? "game.neutral.hurt.fall.big" : "game.neutral.hurt.fall.small";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void performHurtAnimation() {
/* 1173 */     this.hurtTime = this.maxHurtTime = 10;
/* 1174 */     this.attackedAtYaw = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTotalArmorValue() {
/* 1182 */     int var1 = 0;
/* 1183 */     ItemStack[] var2 = getInventory();
/* 1184 */     int var3 = var2.length;
/*      */     
/* 1186 */     for (int var4 = 0; var4 < var3; var4++) {
/*      */       
/* 1188 */       ItemStack var5 = var2[var4];
/*      */       
/* 1190 */       if (var5 != null && var5.getItem() instanceof ItemArmor) {
/*      */         
/* 1192 */         int var6 = ((ItemArmor)var5.getItem()).damageReduceAmount;
/* 1193 */         var1 += var6;
/*      */       } 
/*      */     } 
/*      */     
/* 1197 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void damageArmor(float p_70675_1_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected float applyArmorCalculations(DamageSource p_70655_1_, float p_70655_2_) {
/* 1207 */     if (!p_70655_1_.isUnblockable()) {
/*      */       
/* 1209 */       int var3 = 25 - getTotalArmorValue();
/* 1210 */       float var4 = p_70655_2_ * var3;
/* 1211 */       damageArmor(p_70655_2_);
/* 1212 */       p_70655_2_ = var4 / 25.0F;
/*      */     } 
/*      */     
/* 1215 */     return p_70655_2_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float applyPotionDamageCalculations(DamageSource p_70672_1_, float p_70672_2_) {
/* 1223 */     if (p_70672_1_.isDamageAbsolute())
/*      */     {
/* 1225 */       return p_70672_2_;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1233 */     if (isPotionActive(Potion.resistance) && p_70672_1_ != DamageSource.outOfWorld) {
/*      */       
/* 1235 */       int i = (getActivePotionEffect(Potion.resistance).getAmplifier() + 1) * 5;
/* 1236 */       int var4 = 25 - i;
/* 1237 */       float var5 = p_70672_2_ * var4;
/* 1238 */       p_70672_2_ = var5 / 25.0F;
/*      */     } 
/*      */     
/* 1241 */     if (p_70672_2_ <= 0.0F)
/*      */     {
/* 1243 */       return 0.0F;
/*      */     }
/*      */ 
/*      */     
/* 1247 */     int var3 = EnchantmentHelper.getEnchantmentModifierDamage(getInventory(), p_70672_1_);
/*      */     
/* 1249 */     if (var3 > 20)
/*      */     {
/* 1251 */       var3 = 20;
/*      */     }
/*      */     
/* 1254 */     if (var3 > 0 && var3 <= 20) {
/*      */       
/* 1256 */       int var4 = 25 - var3;
/* 1257 */       float var5 = p_70672_2_ * var4;
/* 1258 */       p_70672_2_ = var5 / 25.0F;
/*      */     } 
/*      */     
/* 1261 */     return p_70672_2_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void damageEntity(DamageSource p_70665_1_, float p_70665_2_) {
/* 1272 */     if (!func_180431_b(p_70665_1_)) {
/*      */       
/* 1274 */       p_70665_2_ = applyArmorCalculations(p_70665_1_, p_70665_2_);
/* 1275 */       p_70665_2_ = applyPotionDamageCalculations(p_70665_1_, p_70665_2_);
/* 1276 */       float var3 = p_70665_2_;
/* 1277 */       p_70665_2_ = Math.max(p_70665_2_ - getAbsorptionAmount(), 0.0F);
/* 1278 */       setAbsorptionAmount(getAbsorptionAmount() - var3 - p_70665_2_);
/*      */       
/* 1280 */       if (p_70665_2_ != 0.0F) {
/*      */         
/* 1282 */         float var4 = getHealth();
/* 1283 */         setHealth(var4 - p_70665_2_);
/* 1284 */         getCombatTracker().func_94547_a(p_70665_1_, var4, p_70665_2_);
/* 1285 */         setAbsorptionAmount(getAbsorptionAmount() - p_70665_2_);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public CombatTracker getCombatTracker() {
/* 1292 */     return this._combatTracker;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLivingBase func_94060_bK() {
/* 1297 */     return (this._combatTracker.func_94550_c() != null) ? this._combatTracker.func_94550_c() : ((this.attackingPlayer != null) ? (EntityLivingBase)this.attackingPlayer : ((this.entityLivingToAttack != null) ? this.entityLivingToAttack : null));
/*      */   }
/*      */ 
/*      */   
/*      */   public final float getMaxHealth() {
/* 1302 */     return (float)getEntityAttribute(SharedMonsterAttributes.maxHealth).getAttributeValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getArrowCountInEntity() {
/* 1310 */     return this.dataWatcher.getWatchableObjectByte(9);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setArrowCountInEntity(int p_85034_1_) {
/* 1318 */     this.dataWatcher.updateObject(9, Byte.valueOf((byte)p_85034_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getArmSwingAnimationEnd() {
/* 1327 */     return isPotionActive(Potion.digSpeed) ? (6 - (1 + getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1) : (isPotionActive(Potion.digSlowdown) ? (6 + (1 + getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2) : 6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void swingItem() {
/* 1335 */     if (!this.isSwingInProgress || this.swingProgressInt >= getArmSwingAnimationEnd() / 2 || this.swingProgressInt < 0) {
/*      */       
/* 1337 */       this.swingProgressInt = -1;
/* 1338 */       this.isSwingInProgress = true;
/*      */       
/* 1340 */       if (this.worldObj instanceof WorldServer)
/*      */       {
/* 1342 */         ((WorldServer)this.worldObj).getEntityTracker().sendToAllTrackingEntity(this, (Packet)new S0BPacketAnimation(this, 0));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(byte p_70103_1_) {
/* 1351 */     if (p_70103_1_ == 2) {
/*      */       
/* 1353 */       this.limbSwingAmount = 1.5F;
/* 1354 */       this.hurtResistantTime = this.maxHurtResistantTime;
/* 1355 */       this.hurtTime = this.maxHurtTime = 10;
/* 1356 */       this.attackedAtYaw = 0.0F;
/* 1357 */       String var2 = getHurtSound();
/*      */       
/* 1359 */       if (var2 != null)
/*      */       {
/* 1361 */         playSound(getHurtSound(), getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*      */       }
/*      */       
/* 1364 */       attackEntityFrom(DamageSource.generic, 0.0F);
/*      */     }
/* 1366 */     else if (p_70103_1_ == 3) {
/*      */       
/* 1368 */       String var2 = getDeathSound();
/*      */       
/* 1370 */       if (var2 != null)
/*      */       {
/* 1372 */         playSound(getDeathSound(), getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*      */       }
/*      */       
/* 1375 */       setHealth(0.0F);
/* 1376 */       onDeath(DamageSource.generic);
/*      */     }
/*      */     else {
/*      */       
/* 1380 */       super.handleHealthUpdate(p_70103_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void kill() {
/* 1389 */     attackEntityFrom(DamageSource.outOfWorld, 4.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateArmSwingProgress() {
/* 1397 */     int var1 = getArmSwingAnimationEnd();
/*      */     
/* 1399 */     if (this.isSwingInProgress) {
/*      */       
/* 1401 */       this.swingProgressInt++;
/*      */       
/* 1403 */       if (this.swingProgressInt >= var1)
/*      */       {
/* 1405 */         this.swingProgressInt = 0;
/* 1406 */         this.isSwingInProgress = false;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1411 */       this.swingProgressInt = 0;
/*      */     } 
/*      */     
/* 1414 */     this.swingProgress = this.swingProgressInt / var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public IAttributeInstance getEntityAttribute(IAttribute p_110148_1_) {
/* 1419 */     return getAttributeMap().getAttributeInstance(p_110148_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public BaseAttributeMap getAttributeMap() {
/* 1424 */     if (this.attributeMap == null)
/*      */     {
/* 1426 */       this.attributeMap = (BaseAttributeMap)new ServersideAttributeMap();
/*      */     }
/*      */     
/* 1429 */     return this.attributeMap;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumCreatureAttribute getCreatureAttribute() {
/* 1437 */     return EnumCreatureAttribute.UNDEFINED;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract ItemStack getHeldItem();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract ItemStack getEquipmentInSlot(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract ItemStack getCurrentArmor(int paramInt);
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract void setCurrentItemOrArmor(int paramInt, ItemStack paramItemStack);
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSprinting(boolean sprinting) {
/* 1462 */     super.setSprinting(sprinting);
/* 1463 */     IAttributeInstance var2 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*      */     
/* 1465 */     if (var2.getModifier(sprintingSpeedBoostModifierUUID) != null)
/*      */     {
/* 1467 */       var2.removeModifier(sprintingSpeedBoostModifier);
/*      */     }
/*      */     
/* 1470 */     if (sprinting)
/*      */     {
/* 1472 */       var2.applyModifier(sprintingSpeedBoostModifier);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract ItemStack[] getInventory();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getSoundVolume() {
/* 1486 */     return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getSoundPitch() {
/* 1494 */     return isChild() ? ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.5F) : ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isMovementBlocked() {
/* 1502 */     return (getHealth() <= 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void dismountEntity(Entity p_110145_1_) {
/* 1510 */     double var3 = p_110145_1_.posX;
/* 1511 */     double var5 = (p_110145_1_.getEntityBoundingBox()).minY + p_110145_1_.height;
/* 1512 */     double var7 = p_110145_1_.posZ;
/* 1513 */     byte var9 = 1;
/*      */     
/* 1515 */     for (int var10 = -var9; var10 <= var9; var10++) {
/*      */       
/* 1517 */       for (int var11 = -var9; var11 < var9; var11++) {
/*      */         
/* 1519 */         if (var10 != 0 || var11 != 0) {
/*      */           
/* 1521 */           int var12 = (int)(this.posX + var10);
/* 1522 */           int var13 = (int)(this.posZ + var11);
/* 1523 */           AxisAlignedBB var2 = getEntityBoundingBox().offset(var10, 1.0D, var11);
/*      */           
/* 1525 */           if (this.worldObj.func_147461_a(var2).isEmpty()) {
/*      */             
/* 1527 */             if (World.doesBlockHaveSolidTopSurface((IBlockAccess)this.worldObj, new BlockPos(var12, (int)this.posY, var13))) {
/*      */               
/* 1529 */               setPositionAndUpdate(this.posX + var10, this.posY + 1.0D, this.posZ + var11);
/*      */               
/*      */               return;
/*      */             } 
/* 1533 */             if (World.doesBlockHaveSolidTopSurface((IBlockAccess)this.worldObj, new BlockPos(var12, (int)this.posY - 1, var13)) || this.worldObj.getBlockState(new BlockPos(var12, (int)this.posY - 1, var13)).getBlock().getMaterial() == Material.water) {
/*      */               
/* 1535 */               var3 = this.posX + var10;
/* 1536 */               var5 = this.posY + 1.0D;
/* 1537 */               var7 = this.posZ + var11;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1544 */     setPositionAndUpdate(var3, var5, var7);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAlwaysRenderNameTagForRender() {
/* 1549 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected float func_175134_bD() {
/* 1554 */     return 0.42F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void jump() {
/* 1562 */     this.motionY = func_175134_bD();
/*      */     
/* 1564 */     if (isPotionActive(Potion.jump))
/*      */     {
/* 1566 */       this.motionY += ((getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
/*      */     }
/*      */     
/* 1569 */     if (isSprinting()) {
/*      */       
/* 1571 */       float var1 = this.rotationYaw * 0.017453292F;
/* 1572 */       this.motionX -= (MathHelper.sin(var1) * 0.2F);
/* 1573 */       this.motionZ += (MathHelper.cos(var1) * 0.2F);
/*      */     } 
/*      */     
/* 1576 */     this.isAirBorne = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateAITick() {
/* 1584 */     this.motionY += 0.03999999910593033D;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_180466_bG() {
/* 1589 */     this.motionY += 0.03999999910593033D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
/* 1600 */     if (isServerWorld())
/*      */     {
/*      */ 
/*      */ 
/*      */       
/* 1605 */       if (isInWater() && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).capabilities.isFlying)) {
/*      */         
/* 1607 */         double d = this.posY;
/* 1608 */         float var5 = 0.8F;
/* 1609 */         float var6 = 0.02F;
/* 1610 */         float f1 = EnchantmentHelper.func_180318_b(this);
/*      */         
/* 1612 */         if (f1 > 3.0F)
/*      */         {
/* 1614 */           f1 = 3.0F;
/*      */         }
/* 1616 */         if (!this.onGround)
/*      */         {
/* 1618 */           f1 *= 0.5F;
/*      */         }
/*      */         
/* 1621 */         if (f1 > 0.0F) {
/*      */           
/* 1623 */           var5 += (0.54600006F - var5) * f1 / 3.0F;
/* 1624 */           var6 += (getAIMoveSpeed() * 1.0F - var6) * f1 / 3.0F;
/*      */         } 
/*      */         
/* 1627 */         moveFlying(p_70612_1_, p_70612_2_, var6);
/* 1628 */         moveEntity(this.motionX, this.motionY, this.motionZ);
/* 1629 */         this.motionX *= var5;
/* 1630 */         this.motionY *= 0.800000011920929D;
/* 1631 */         this.motionZ *= var5;
/* 1632 */         this.motionY -= 0.02D;
/*      */         
/* 1634 */         if (this.isCollidedHorizontally && isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d, this.motionZ))
/*      */         {
/* 1636 */           this.motionY = 0.30000001192092896D;
/*      */         }
/*      */       }
/* 1639 */       else if (func_180799_ab() && (!(this instanceof EntityPlayer) || !((EntityPlayer)this).capabilities.isFlying)) {
/*      */         
/* 1641 */         double d = this.posY;
/* 1642 */         moveFlying(p_70612_1_, p_70612_2_, 0.02F);
/* 1643 */         moveEntity(this.motionX, this.motionY, this.motionZ);
/* 1644 */         this.motionX *= 0.5D;
/* 1645 */         this.motionY *= 0.5D;
/* 1646 */         this.motionZ *= 0.5D;
/* 1647 */         this.motionY -= 0.02D;
/*      */         
/* 1649 */         if (this.isCollidedHorizontally && isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d, this.motionZ))
/*      */         {
/* 1651 */           this.motionY = 0.30000001192092896D;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1656 */         float var5, var3 = 0.91F;
/*      */         
/* 1658 */         if (this.onGround)
/*      */         {
/* 1660 */           var3 = (this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double((getEntityBoundingBox()).minY) - 1, MathHelper.floor_double(this.posZ))).getBlock()).slipperiness * 0.91F;
/*      */         }
/*      */         
/* 1663 */         float var4 = 0.16277136F / var3 * var3 * var3;
/*      */         
/* 1665 */         if (this.onGround) {
/*      */           
/* 1667 */           var5 = getAIMoveSpeed() * var4;
/*      */         }
/*      */         else {
/*      */           
/* 1671 */           var5 = this.jumpMovementFactor;
/*      */         } 
/*      */         
/* 1674 */         moveFlying(p_70612_1_, p_70612_2_, var5);
/* 1675 */         var3 = 0.91F;
/* 1676 */         if (this.onGround)
/*      */         {
/* 1678 */           var3 = (this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double((getEntityBoundingBox()).minY) - 1, MathHelper.floor_double(this.posZ))).getBlock()).slipperiness * 0.91F;
/*      */         }
/*      */         
/* 1681 */         if (isOnLadder()) {
/*      */           
/* 1683 */           float var6 = 0.15F;
/* 1684 */           this.motionX = MathHelper.clamp_double(this.motionX, -var6, var6);
/* 1685 */           this.motionZ = MathHelper.clamp_double(this.motionZ, -var6, var6);
/* 1686 */           this.fallDistance = 0.0F;
/*      */           
/* 1688 */           if (this.motionY < -0.15D)
/*      */           {
/* 1690 */             this.motionY = -0.15D;
/*      */           }
/*      */           
/* 1693 */           boolean var7 = (isSneaking() && this instanceof EntityPlayer);
/*      */           
/* 1695 */           if (var7 && this.motionY < 0.0D)
/*      */           {
/* 1697 */             this.motionY = 0.0D;
/*      */           }
/*      */         } 
/*      */         
/* 1701 */         moveEntity(this.motionX, this.motionY, this.motionZ);
/*      */         
/* 1703 */         if (this.isCollidedHorizontally && isOnLadder())
/*      */         {
/* 1705 */           this.motionY = 0.2D;
/*      */         }
/*      */         
/* 1708 */         if (this.worldObj.isRemote && (!this.worldObj.isBlockLoaded(new BlockPos((int)this.posX, 0, (int)this.posZ)) || !this.worldObj.getChunkFromBlockCoords(new BlockPos((int)this.posX, 0, (int)this.posZ)).isLoaded())) {
/*      */           
/* 1710 */           if (this.posY > 0.0D)
/*      */           {
/* 1712 */             this.motionY = -0.1D;
/*      */           }
/*      */           else
/*      */           {
/* 1716 */             this.motionY = 0.0D;
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1721 */           this.motionY -= 0.08D;
/*      */         } 
/*      */         
/* 1724 */         this.motionY *= 0.9800000190734863D;
/* 1725 */         this.motionX *= var3;
/* 1726 */         this.motionZ *= var3;
/*      */       } 
/*      */     }
/*      */     
/* 1730 */     this.prevLimbSwingAmount = this.limbSwingAmount;
/* 1731 */     double var8 = this.posX - this.prevPosX;
/* 1732 */     double var9 = this.posZ - this.prevPosZ;
/* 1733 */     float var10 = MathHelper.sqrt_double(var8 * var8 + var9 * var9) * 4.0F;
/*      */     
/* 1735 */     if (var10 > 1.0F)
/*      */     {
/* 1737 */       var10 = 1.0F;
/*      */     }
/*      */     
/* 1740 */     this.limbSwingAmount += (var10 - this.limbSwingAmount) * 0.4F;
/* 1741 */     this.limbSwing += this.limbSwingAmount;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAIMoveSpeed() {
/* 1749 */     return this.landMovementFactor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAIMoveSpeed(float p_70659_1_) {
/* 1757 */     this.landMovementFactor = p_70659_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 1762 */     setLastAttacker(p_70652_1_);
/* 1763 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPlayerSleeping() {
/* 1771 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/* 1779 */     super.onUpdate();
/*      */     
/* 1781 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1783 */       int var1 = getArrowCountInEntity();
/*      */       
/* 1785 */       if (var1 > 0) {
/*      */         
/* 1787 */         if (this.arrowHitTimer <= 0)
/*      */         {
/* 1789 */           this.arrowHitTimer = 20 * (30 - var1);
/*      */         }
/*      */         
/* 1792 */         this.arrowHitTimer--;
/*      */         
/* 1794 */         if (this.arrowHitTimer <= 0)
/*      */         {
/* 1796 */           setArrowCountInEntity(var1 - 1);
/*      */         }
/*      */       } 
/*      */       
/* 1800 */       for (int var2 = 0; var2 < 5; var2++) {
/*      */         
/* 1802 */         ItemStack var3 = this.previousEquipment[var2];
/* 1803 */         ItemStack var4 = getEquipmentInSlot(var2);
/*      */         
/* 1805 */         if (!ItemStack.areItemStacksEqual(var4, var3)) {
/*      */           
/* 1807 */           ((WorldServer)this.worldObj).getEntityTracker().sendToAllTrackingEntity(this, (Packet)new S04PacketEntityEquipment(getEntityId(), var2, var4));
/*      */           
/* 1809 */           if (var3 != null)
/*      */           {
/* 1811 */             this.attributeMap.removeAttributeModifiers(var3.getAttributeModifiers());
/*      */           }
/*      */           
/* 1814 */           if (var4 != null)
/*      */           {
/* 1816 */             this.attributeMap.applyAttributeModifiers(var4.getAttributeModifiers());
/*      */           }
/*      */           
/* 1819 */           this.previousEquipment[var2] = (var4 == null) ? null : var4.copy();
/*      */         } 
/*      */       } 
/*      */       
/* 1823 */       if (this.ticksExisted % 20 == 0)
/*      */       {
/* 1825 */         getCombatTracker().func_94549_h();
/*      */       }
/*      */     } 
/*      */     
/* 1829 */     onLivingUpdate();
/* 1830 */     double var9 = this.posX - this.prevPosX;
/* 1831 */     double var10 = this.posZ - this.prevPosZ;
/* 1832 */     float var5 = (float)(var9 * var9 + var10 * var10);
/* 1833 */     float var6 = this.renderYawOffset;
/* 1834 */     float var7 = 0.0F;
/* 1835 */     this.field_70768_au = this.field_110154_aX;
/* 1836 */     float var8 = 0.0F;
/*      */     
/* 1838 */     if (var5 > 0.0025000002F) {
/*      */       
/* 1840 */       var8 = 1.0F;
/* 1841 */       var7 = (float)Math.sqrt(var5) * 3.0F;
/* 1842 */       var6 = (float)Math.atan2(var10, var9) * 180.0F / 3.1415927F - 90.0F;
/*      */     } 
/*      */     
/* 1845 */     if (this.swingProgress > 0.0F)
/*      */     {
/* 1847 */       var6 = this.rotationYaw;
/*      */     }
/* 1849 */     if (!this.onGround)
/*      */     {
/* 1851 */       var8 = 0.0F;
/*      */     }
/*      */     
/* 1854 */     this.field_110154_aX += (var8 - this.field_110154_aX) * 0.3F;
/* 1855 */     this.worldObj.theProfiler.startSection("headTurn");
/* 1856 */     var7 = func_110146_f(var6, var7);
/* 1857 */     this.worldObj.theProfiler.endSection();
/* 1858 */     this.worldObj.theProfiler.startSection("rangeChecks");
/*      */     
/* 1860 */     while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*      */     {
/* 1862 */       this.prevRotationYaw -= 360.0F;
/*      */     }
/*      */     
/* 1865 */     while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*      */     {
/* 1867 */       this.prevRotationYaw += 360.0F;
/*      */     }
/*      */     
/* 1870 */     while (this.renderYawOffset - this.prevRenderYawOffset < -180.0F)
/*      */     {
/* 1872 */       this.prevRenderYawOffset -= 360.0F;
/*      */     }
/*      */     
/* 1875 */     while (this.renderYawOffset - this.prevRenderYawOffset >= 180.0F)
/*      */     {
/* 1877 */       this.prevRenderYawOffset += 360.0F;
/*      */     }
/*      */     
/* 1880 */     while (this.rotationPitch - this.prevRotationPitch < -180.0F)
/*      */     {
/* 1882 */       this.prevRotationPitch -= 360.0F;
/*      */     }
/*      */     
/* 1885 */     while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*      */     {
/* 1887 */       this.prevRotationPitch += 360.0F;
/*      */     }
/*      */     
/* 1890 */     while (this.rotationYawHead - this.prevRotationYawHead < -180.0F)
/*      */     {
/* 1892 */       this.prevRotationYawHead -= 360.0F;
/*      */     }
/*      */     
/* 1895 */     while (this.rotationYawHead - this.prevRotationYawHead >= 180.0F)
/*      */     {
/* 1897 */       this.prevRotationYawHead += 360.0F;
/*      */     }
/*      */     
/* 1900 */     this.worldObj.theProfiler.endSection();
/* 1901 */     this.field_70764_aw += var7;
/*      */   }
/*      */ 
/*      */   
/*      */   protected float func_110146_f(float p_110146_1_, float p_110146_2_) {
/* 1906 */     float var3 = MathHelper.wrapAngleTo180_float(p_110146_1_ - this.renderYawOffset);
/* 1907 */     this.renderYawOffset += var3 * 0.3F;
/* 1908 */     float var4 = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.renderYawOffset);
/* 1909 */     boolean var5 = !(var4 >= -90.0F && var4 < 90.0F);
/*      */     
/* 1911 */     if (var4 < -75.0F)
/*      */     {
/* 1913 */       var4 = -75.0F;
/*      */     }
/*      */     
/* 1916 */     if (var4 >= 75.0F)
/*      */     {
/* 1918 */       var4 = 75.0F;
/*      */     }
/*      */     
/* 1921 */     this.renderYawOffset = this.rotationYaw - var4;
/*      */     
/* 1923 */     if (var4 * var4 > 2500.0F)
/*      */     {
/* 1925 */       this.renderYawOffset += var4 * 0.2F;
/*      */     }
/*      */     
/* 1928 */     if (var5)
/*      */     {
/* 1930 */       p_110146_2_ *= -1.0F;
/*      */     }
/*      */     
/* 1933 */     return p_110146_2_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/* 1942 */     if (this.jumpTicks > 0)
/*      */     {
/* 1944 */       this.jumpTicks--;
/*      */     }
/*      */     
/* 1947 */     if (this.newPosRotationIncrements > 0) {
/*      */       
/* 1949 */       double var1 = this.posX + (this.newPosX - this.posX) / this.newPosRotationIncrements;
/* 1950 */       double var3 = this.posY + (this.newPosY - this.posY) / this.newPosRotationIncrements;
/* 1951 */       double var5 = this.posZ + (this.newPosZ - this.posZ) / this.newPosRotationIncrements;
/* 1952 */       double var7 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - this.rotationYaw);
/* 1953 */       this.rotationYaw = (float)(this.rotationYaw + var7 / this.newPosRotationIncrements);
/* 1954 */       this.rotationPitch = (float)(this.rotationPitch + (this.newRotationPitch - this.rotationPitch) / this.newPosRotationIncrements);
/* 1955 */       this.newPosRotationIncrements--;
/* 1956 */       setPosition(var1, var3, var5);
/* 1957 */       setRotation(this.rotationYaw, this.rotationPitch);
/*      */     }
/* 1959 */     else if (!isServerWorld()) {
/*      */       
/* 1961 */       this.motionX *= 0.98D;
/* 1962 */       this.motionY *= 0.98D;
/* 1963 */       this.motionZ *= 0.98D;
/*      */     } 
/*      */     
/* 1966 */     if (Math.abs(this.motionX) < 0.005D)
/*      */     {
/* 1968 */       this.motionX = 0.0D;
/*      */     }
/*      */     
/* 1971 */     if (Math.abs(this.motionY) < 0.005D)
/*      */     {
/* 1973 */       this.motionY = 0.0D;
/*      */     }
/*      */     
/* 1976 */     if (Math.abs(this.motionZ) < 0.005D)
/*      */     {
/* 1978 */       this.motionZ = 0.0D;
/*      */     }
/*      */     
/* 1981 */     this.worldObj.theProfiler.startSection("ai");
/*      */     
/* 1983 */     if (isMovementBlocked()) {
/*      */       
/* 1985 */       this.isJumping = false;
/* 1986 */       this.moveStrafing = 0.0F;
/* 1987 */       this.moveForward = 0.0F;
/* 1988 */       this.randomYawVelocity = 0.0F;
/*      */     }
/* 1990 */     else if (isServerWorld()) {
/*      */       
/* 1992 */       this.worldObj.theProfiler.startSection("newAi");
/* 1993 */       updateEntityActionState();
/* 1994 */       this.worldObj.theProfiler.endSection();
/*      */     } 
/*      */     
/* 1997 */     this.worldObj.theProfiler.endSection();
/* 1998 */     this.worldObj.theProfiler.startSection("jump");
/*      */     
/* 2000 */     if (this.isJumping) {
/*      */       
/* 2002 */       if (isInWater())
/*      */       {
/* 2004 */         updateAITick();
/*      */       }
/* 2006 */       else if (func_180799_ab())
/*      */       {
/* 2008 */         func_180466_bG();
/*      */       }
/* 2010 */       else if (this.onGround && this.jumpTicks == 0)
/*      */       {
/* 2012 */         jump();
/* 2013 */         this.jumpTicks = 10;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 2018 */       this.jumpTicks = 0;
/*      */     } 
/*      */     
/* 2021 */     this.worldObj.theProfiler.endSection();
/* 2022 */     this.worldObj.theProfiler.startSection("travel");
/* 2023 */     this.moveStrafing *= 0.98F;
/* 2024 */     this.moveForward *= 0.98F;
/* 2025 */     this.randomYawVelocity *= 0.9F;
/* 2026 */     moveEntityWithHeading(this.moveStrafing, this.moveForward);
/* 2027 */     this.worldObj.theProfiler.endSection();
/* 2028 */     this.worldObj.theProfiler.startSection("push");
/*      */     
/* 2030 */     if (!this.worldObj.isRemote)
/*      */     {
/* 2032 */       collideWithNearbyEntities();
/*      */     }
/*      */     
/* 2035 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void updateEntityActionState() {}
/*      */   
/*      */   protected void collideWithNearbyEntities() {
/* 2042 */     List<Entity> var1 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
/*      */     
/* 2044 */     if (var1 != null && !var1.isEmpty())
/*      */     {
/* 2046 */       for (int var2 = 0; var2 < var1.size(); var2++) {
/*      */         
/* 2048 */         Entity var3 = var1.get(var2);
/*      */         
/* 2050 */         if (var3.canBePushed())
/*      */         {
/* 2052 */           collideWithEntity(var3);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void collideWithEntity(Entity p_82167_1_) {
/* 2060 */     p_82167_1_.applyEntityCollision(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mountEntity(Entity entityIn) {
/* 2068 */     if (this.ridingEntity != null && entityIn == null) {
/*      */       
/* 2070 */       if (!this.worldObj.isRemote)
/*      */       {
/* 2072 */         dismountEntity(this.ridingEntity);
/*      */       }
/*      */       
/* 2075 */       if (this.ridingEntity != null)
/*      */       {
/* 2077 */         this.ridingEntity.riddenByEntity = null;
/*      */       }
/*      */       
/* 2080 */       this.ridingEntity = null;
/*      */     }
/*      */     else {
/*      */       
/* 2084 */       super.mountEntity(entityIn);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRidden() {
/* 2093 */     super.updateRidden();
/* 2094 */     this.field_70768_au = this.field_110154_aX;
/* 2095 */     this.field_110154_aX = 0.0F;
/* 2096 */     this.fallDistance = 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
/* 2101 */     this.newPosX = p_180426_1_;
/* 2102 */     this.newPosY = p_180426_3_;
/* 2103 */     this.newPosZ = p_180426_5_;
/* 2104 */     this.newRotationYaw = p_180426_7_;
/* 2105 */     this.newRotationPitch = p_180426_8_;
/* 2106 */     this.newPosRotationIncrements = p_180426_9_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setJumping(boolean p_70637_1_) {
/* 2111 */     this.isJumping = p_70637_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onItemPickup(Entity p_71001_1_, int p_71001_2_) {
/* 2119 */     if (!p_71001_1_.isDead && !this.worldObj.isRemote) {
/*      */       
/* 2121 */       EntityTracker var3 = ((WorldServer)this.worldObj).getEntityTracker();
/*      */       
/* 2123 */       if (p_71001_1_ instanceof net.minecraft.entity.item.EntityItem)
/*      */       {
/* 2125 */         var3.sendToAllTrackingEntity(p_71001_1_, (Packet)new S0DPacketCollectItem(p_71001_1_.getEntityId(), getEntityId()));
/*      */       }
/*      */       
/* 2128 */       if (p_71001_1_ instanceof net.minecraft.entity.projectile.EntityArrow)
/*      */       {
/* 2130 */         var3.sendToAllTrackingEntity(p_71001_1_, (Packet)new S0DPacketCollectItem(p_71001_1_.getEntityId(), getEntityId()));
/*      */       }
/*      */       
/* 2133 */       if (p_71001_1_ instanceof EntityXPOrb)
/*      */       {
/* 2135 */         var3.sendToAllTrackingEntity(p_71001_1_, (Packet)new S0DPacketCollectItem(p_71001_1_.getEntityId(), getEntityId()));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canEntityBeSeen(Entity p_70685_1_) {
/* 2145 */     return (this.worldObj.rayTraceBlocks(new Vec3(this.posX, this.posY + getEyeHeight(), this.posZ), new Vec3(p_70685_1_.posX, p_70685_1_.posY + p_70685_1_.getEyeHeight(), p_70685_1_.posZ)) == null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getLookVec() {
/* 2153 */     return getLook(1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getLook(float p_70676_1_) {
/* 2161 */     if (p_70676_1_ == 1.0F)
/*      */     {
/* 2163 */       return func_174806_f(this.rotationPitch, this.rotationYawHead);
/*      */     }
/*      */ 
/*      */     
/* 2167 */     float var2 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * p_70676_1_;
/* 2168 */     float var3 = this.prevRotationYawHead + (this.rotationYawHead - this.prevRotationYawHead) * p_70676_1_;
/* 2169 */     return func_174806_f(var2, var3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSwingProgress(float p_70678_1_) {
/* 2178 */     float var2 = this.swingProgress - this.prevSwingProgress;
/*      */     
/* 2180 */     if (var2 < 0.0F)
/*      */     {
/* 2182 */       var2++;
/*      */     }
/*      */     
/* 2185 */     return this.prevSwingProgress + var2 * p_70678_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isServerWorld() {
/* 2193 */     return !this.worldObj.isRemote;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeCollidedWith() {
/* 2201 */     return !this.isDead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePushed() {
/* 2209 */     return !this.isDead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBeenAttacked() {
/* 2217 */     this.velocityChanged = (this.rand.nextDouble() >= getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue());
/*      */   }
/*      */ 
/*      */   
/*      */   public float getRotationYawHead() {
/* 2222 */     return this.rotationYawHead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotationYawHead(float rotation) {
/* 2230 */     this.rotationYawHead = rotation;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAbsorptionAmount() {
/* 2235 */     return this.field_110151_bq;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAbsorptionAmount(float p_110149_1_) {
/* 2240 */     if (p_110149_1_ < 0.0F)
/*      */     {
/* 2242 */       p_110149_1_ = 0.0F;
/*      */     }
/*      */     
/* 2245 */     this.field_110151_bq = p_110149_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public Team getTeam() {
/* 2250 */     return (Team)this.worldObj.getScoreboard().getPlayersTeam(getUniqueID().toString());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOnSameTeam(EntityLivingBase p_142014_1_) {
/* 2255 */     return isOnTeam(p_142014_1_.getTeam());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOnTeam(Team p_142012_1_) {
/* 2263 */     return (getTeam() != null) ? getTeam().isSameTeam(p_142012_1_) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152111_bt() {}
/*      */   
/*      */   public void func_152112_bu() {}
/*      */   
/*      */   protected void func_175136_bO() {
/* 2272 */     this.potionsNeedUpdate = true;
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityLivingBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */