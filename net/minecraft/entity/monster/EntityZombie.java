/*     */ package net.minecraft.entity.monster;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIBreakDoor;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttribute;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityZombie extends EntityMob {
/*  48 */   protected static final IAttribute field_110186_bp = (IAttribute)(new RangedAttribute(null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
/*  49 */   private static final UUID babySpeedBoostUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
/*  50 */   private static final AttributeModifier babySpeedBoostModifier = new AttributeModifier(babySpeedBoostUUID, "Baby speed boost", 0.5D, 1);
/*  51 */   private final EntityAIBreakDoor field_146075_bs = new EntityAIBreakDoor((EntityLiving)this);
/*     */ 
/*     */   
/*     */   private int conversionTime;
/*     */   
/*     */   private boolean field_146076_bu = false;
/*     */   
/*  58 */   private float field_146074_bv = -1.0F;
/*     */   
/*     */   private float field_146073_bw;
/*     */   private static final String __OBFID = "CL_00001702";
/*     */   
/*     */   public EntityZombie(World worldIn) {
/*  64 */     super(worldIn);
/*  65 */     ((PathNavigateGround)getNavigator()).func_179688_b(true);
/*  66 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  67 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  68 */     this.tasks.addTask(2, this.field_175455_a);
/*  69 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  70 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWander(this, 1.0D));
/*  71 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  72 */     this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  73 */     func_175456_n();
/*  74 */     setSize(0.6F, 1.95F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175456_n() {
/*  79 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  80 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackOnCollide(this, EntityIronGolem.class, 1.0D, true));
/*  81 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIMoveThroughVillage(this, 1.0D, false));
/*  82 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget(this, true, new Class[] { EntityPigZombie.class }));
/*  83 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  84 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
/*  85 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  90 */     super.applyEntityAttributes();
/*  91 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(35.0D);
/*  92 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
/*  93 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
/*  94 */     getAttributeMap().registerAttribute(field_110186_bp).setBaseValue(this.rand.nextDouble() * 0.10000000149011612D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  99 */     super.entityInit();
/* 100 */     getDataWatcher().addObject(12, Byte.valueOf((byte)0));
/* 101 */     getDataWatcher().addObject(13, Byte.valueOf((byte)0));
/* 102 */     getDataWatcher().addObject(14, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalArmorValue() {
/* 110 */     int var1 = super.getTotalArmorValue() + 2;
/*     */     
/* 112 */     if (var1 > 20)
/*     */     {
/* 114 */       var1 = 20;
/*     */     }
/*     */     
/* 117 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_146072_bX() {
/* 122 */     return this.field_146076_bu;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146070_a(boolean p_146070_1_) {
/* 127 */     if (this.field_146076_bu != p_146070_1_) {
/*     */       
/* 129 */       this.field_146076_bu = p_146070_1_;
/*     */       
/* 131 */       if (p_146070_1_) {
/*     */         
/* 133 */         this.tasks.addTask(1, (EntityAIBase)this.field_146075_bs);
/*     */       }
/*     */       else {
/*     */         
/* 137 */         this.tasks.removeTask((EntityAIBase)this.field_146075_bs);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChild() {
/* 147 */     return (getDataWatcher().getWatchableObjectByte(12) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getExperiencePoints(EntityPlayer p_70693_1_) {
/* 155 */     if (isChild())
/*     */     {
/* 157 */       this.experienceValue = (int)(this.experienceValue * 2.5F);
/*     */     }
/*     */     
/* 160 */     return super.getExperiencePoints(p_70693_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChild(boolean p_82227_1_) {
/* 168 */     getDataWatcher().updateObject(12, Byte.valueOf((byte)(p_82227_1_ ? 1 : 0)));
/*     */     
/* 170 */     if (this.worldObj != null && !this.worldObj.isRemote) {
/*     */       
/* 172 */       IAttributeInstance var2 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/* 173 */       var2.removeModifier(babySpeedBoostModifier);
/*     */       
/* 175 */       if (p_82227_1_)
/*     */       {
/* 177 */         var2.applyModifier(babySpeedBoostModifier);
/*     */       }
/*     */     } 
/*     */     
/* 181 */     func_146071_k(p_82227_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVillager() {
/* 189 */     return (getDataWatcher().getWatchableObjectByte(13) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVillager(boolean p_82229_1_) {
/* 197 */     getDataWatcher().updateObject(13, Byte.valueOf((byte)(p_82229_1_ ? 1 : 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 206 */     if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !isChild()) {
/*     */       
/* 208 */       float var1 = getBrightness(1.0F);
/* 209 */       BlockPos var2 = new BlockPos(this.posX, Math.round(this.posY), this.posZ);
/*     */       
/* 211 */       if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.worldObj.isAgainstSky(var2)) {
/*     */         
/* 213 */         boolean var3 = true;
/* 214 */         ItemStack var4 = getEquipmentInSlot(4);
/*     */         
/* 216 */         if (var4 != null) {
/*     */           
/* 218 */           if (var4.isItemStackDamageable()) {
/*     */             
/* 220 */             var4.setItemDamage(var4.getItemDamage() + this.rand.nextInt(2));
/*     */             
/* 222 */             if (var4.getItemDamage() >= var4.getMaxDamage()) {
/*     */               
/* 224 */               renderBrokenItemStack(var4);
/* 225 */               setCurrentItemOrArmor(4, null);
/*     */             } 
/*     */           } 
/*     */           
/* 229 */           var3 = false;
/*     */         } 
/*     */         
/* 232 */         if (var3)
/*     */         {
/* 234 */           setFire(8);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 239 */     if (isRiding() && getAttackTarget() != null && this.ridingEntity instanceof EntityChicken)
/*     */     {
/* 241 */       ((EntityLiving)this.ridingEntity).getNavigator().setPath(getNavigator().getPath(), 1.5D);
/*     */     }
/*     */     
/* 244 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 252 */     if (super.attackEntityFrom(source, amount)) {
/*     */       
/* 254 */       EntityLivingBase var3 = getAttackTarget();
/*     */       
/* 256 */       if (var3 == null && source.getEntity() instanceof EntityLivingBase)
/*     */       {
/* 258 */         var3 = (EntityLivingBase)source.getEntity();
/*     */       }
/*     */       
/* 261 */       if (var3 != null && this.worldObj.getDifficulty() == EnumDifficulty.HARD && this.rand.nextFloat() < getEntityAttribute(field_110186_bp).getAttributeValue()) {
/*     */         
/* 263 */         int var4 = MathHelper.floor_double(this.posX);
/* 264 */         int var5 = MathHelper.floor_double(this.posY);
/* 265 */         int var6 = MathHelper.floor_double(this.posZ);
/* 266 */         EntityZombie var7 = new EntityZombie(this.worldObj);
/*     */         
/* 268 */         for (int var8 = 0; var8 < 50; var8++) {
/*     */           
/* 270 */           int var9 = var4 + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
/* 271 */           int var10 = var5 + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
/* 272 */           int var11 = var6 + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
/*     */           
/* 274 */           if (World.doesBlockHaveSolidTopSurface((IBlockAccess)this.worldObj, new BlockPos(var9, var10 - 1, var11)) && this.worldObj.getLightFromNeighbors(new BlockPos(var9, var10, var11)) < 10) {
/*     */             
/* 276 */             var7.setPosition(var9, var10, var11);
/*     */             
/* 278 */             if (!this.worldObj.func_175636_b(var9, var10, var11, 7.0D) && this.worldObj.checkNoEntityCollision(var7.getEntityBoundingBox(), (Entity)var7) && this.worldObj.getCollidingBoundingBoxes((Entity)var7, var7.getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(var7.getEntityBoundingBox())) {
/*     */               
/* 280 */               this.worldObj.spawnEntityInWorld((Entity)var7);
/* 281 */               var7.setAttackTarget(var3);
/* 282 */               var7.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos((Entity)var7)), (IEntityLivingData)null);
/* 283 */               getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
/* 284 */               var7.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 291 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 295 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 304 */     if (!this.worldObj.isRemote && isConverting()) {
/*     */       
/* 306 */       int var1 = getConversionTimeBoost();
/* 307 */       this.conversionTime -= var1;
/*     */       
/* 309 */       if (this.conversionTime <= 0)
/*     */       {
/* 311 */         convertToVillager();
/*     */       }
/*     */     } 
/*     */     
/* 315 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 320 */     boolean var2 = super.attackEntityAsMob(p_70652_1_);
/*     */     
/* 322 */     if (var2) {
/*     */       
/* 324 */       int var3 = this.worldObj.getDifficulty().getDifficultyId();
/*     */       
/* 326 */       if (getHeldItem() == null && isBurning() && this.rand.nextFloat() < var3 * 0.3F)
/*     */       {
/* 328 */         p_70652_1_.setFire(2 * var3);
/*     */       }
/*     */     } 
/*     */     
/* 332 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 340 */     return "mob.zombie.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 348 */     return "mob.zombie.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 356 */     return "mob.zombie.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/* 361 */     playSound("mob.zombie.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 366 */     return Items.rotten_flesh;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 374 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addRandomArmor() {
/* 382 */     switch (this.rand.nextInt(3)) {
/*     */       
/*     */       case 0:
/* 385 */         dropItem(Items.iron_ingot, 1);
/*     */         break;
/*     */       
/*     */       case 1:
/* 389 */         dropItem(Items.carrot, 1);
/*     */         break;
/*     */       
/*     */       case 2:
/* 393 */         dropItem(Items.potato, 1);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void func_180481_a(DifficultyInstance p_180481_1_) {
/* 399 */     super.func_180481_a(p_180481_1_);
/*     */     
/* 401 */     if (this.rand.nextFloat() < ((this.worldObj.getDifficulty() == EnumDifficulty.HARD) ? 0.05F : 0.01F)) {
/*     */       
/* 403 */       int var2 = this.rand.nextInt(3);
/*     */       
/* 405 */       if (var2 == 0) {
/*     */         
/* 407 */         setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
/*     */       }
/*     */       else {
/*     */         
/* 411 */         setCurrentItemOrArmor(0, new ItemStack(Items.iron_shovel));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 421 */     super.writeEntityToNBT(tagCompound);
/*     */     
/* 423 */     if (isChild())
/*     */     {
/* 425 */       tagCompound.setBoolean("IsBaby", true);
/*     */     }
/*     */     
/* 428 */     if (isVillager())
/*     */     {
/* 430 */       tagCompound.setBoolean("IsVillager", true);
/*     */     }
/*     */     
/* 433 */     tagCompound.setInteger("ConversionTime", isConverting() ? this.conversionTime : -1);
/* 434 */     tagCompound.setBoolean("CanBreakDoors", func_146072_bX());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 442 */     super.readEntityFromNBT(tagCompund);
/*     */     
/* 444 */     if (tagCompund.getBoolean("IsBaby"))
/*     */     {
/* 446 */       setChild(true);
/*     */     }
/*     */     
/* 449 */     if (tagCompund.getBoolean("IsVillager"))
/*     */     {
/* 451 */       setVillager(true);
/*     */     }
/*     */     
/* 454 */     if (tagCompund.hasKey("ConversionTime", 99) && tagCompund.getInteger("ConversionTime") > -1)
/*     */     {
/* 456 */       startConversion(tagCompund.getInteger("ConversionTime"));
/*     */     }
/*     */     
/* 459 */     func_146070_a(tagCompund.getBoolean("CanBreakDoors"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onKillEntity(EntityLivingBase entityLivingIn) {
/* 467 */     super.onKillEntity(entityLivingIn);
/*     */     
/* 469 */     if ((this.worldObj.getDifficulty() == EnumDifficulty.NORMAL || this.worldObj.getDifficulty() == EnumDifficulty.HARD) && entityLivingIn instanceof EntityVillager) {
/*     */       
/* 471 */       if (this.worldObj.getDifficulty() != EnumDifficulty.HARD && this.rand.nextBoolean()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 476 */       EntityZombie var2 = new EntityZombie(this.worldObj);
/* 477 */       var2.copyLocationAndAnglesFrom((Entity)entityLivingIn);
/* 478 */       this.worldObj.removeEntity((Entity)entityLivingIn);
/* 479 */       var2.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos((Entity)var2)), (IEntityLivingData)null);
/* 480 */       var2.setVillager(true);
/*     */       
/* 482 */       if (entityLivingIn.isChild())
/*     */       {
/* 484 */         var2.setChild(true);
/*     */       }
/*     */       
/* 487 */       this.worldObj.spawnEntityInWorld((Entity)var2);
/* 488 */       this.worldObj.playAuxSFXAtEntity(null, 1016, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 494 */     float var1 = 1.74F;
/*     */     
/* 496 */     if (isChild())
/*     */     {
/* 498 */       var1 = (float)(var1 - 0.81D);
/*     */     }
/*     */     
/* 501 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_175448_a(ItemStack p_175448_1_) {
/* 506 */     return (p_175448_1_.getItem() == Items.egg && isChild() && isRiding()) ? false : super.func_175448_a(p_175448_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 511 */     Object p_180482_2_1 = super.func_180482_a(p_180482_1_, p_180482_2_);
/* 512 */     float var3 = p_180482_1_.func_180170_c();
/* 513 */     setCanPickUpLoot((this.rand.nextFloat() < 0.55F * var3));
/*     */     
/* 515 */     if (p_180482_2_1 == null)
/*     */     {
/* 517 */       p_180482_2_1 = new GroupData((this.worldObj.rand.nextFloat() < 0.05F), (this.worldObj.rand.nextFloat() < 0.05F), null);
/*     */     }
/*     */     
/* 520 */     if (p_180482_2_1 instanceof GroupData) {
/*     */       
/* 522 */       GroupData var4 = (GroupData)p_180482_2_1;
/*     */       
/* 524 */       if (var4.field_142046_b)
/*     */       {
/* 526 */         setVillager(true);
/*     */       }
/*     */       
/* 529 */       if (var4.field_142048_a) {
/*     */         
/* 531 */         setChild(true);
/*     */         
/* 533 */         if (this.worldObj.rand.nextFloat() < 0.05D) {
/*     */           
/* 535 */           List<EntityChicken> var5 = this.worldObj.func_175647_a(EntityChicken.class, getEntityBoundingBox().expand(5.0D, 3.0D, 5.0D), IEntitySelector.field_152785_b);
/*     */           
/* 537 */           if (!var5.isEmpty())
/*     */           {
/* 539 */             EntityChicken var6 = var5.get(0);
/* 540 */             var6.func_152117_i(true);
/* 541 */             mountEntity((Entity)var6);
/*     */           }
/*     */         
/* 544 */         } else if (this.worldObj.rand.nextFloat() < 0.05D) {
/*     */           
/* 546 */           EntityChicken var10 = new EntityChicken(this.worldObj);
/* 547 */           var10.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
/* 548 */           var10.func_180482_a(p_180482_1_, null);
/* 549 */           var10.func_152117_i(true);
/* 550 */           this.worldObj.spawnEntityInWorld((Entity)var10);
/* 551 */           mountEntity((Entity)var10);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 556 */     func_146070_a((this.rand.nextFloat() < var3 * 0.1F));
/* 557 */     func_180481_a(p_180482_1_);
/* 558 */     func_180483_b(p_180482_1_);
/*     */     
/* 560 */     if (getEquipmentInSlot(4) == null) {
/*     */       
/* 562 */       Calendar var8 = this.worldObj.getCurrentDate();
/*     */       
/* 564 */       if (var8.get(2) + 1 == 10 && var8.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
/*     */         
/* 566 */         setCurrentItemOrArmor(4, new ItemStack((this.rand.nextFloat() < 0.1F) ? Blocks.lit_pumpkin : Blocks.pumpkin));
/* 567 */         this.equipmentDropChances[4] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 571 */     getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
/* 572 */     double var9 = this.rand.nextDouble() * 1.5D * var3;
/*     */     
/* 574 */     if (var9 > 1.0D)
/*     */     {
/* 576 */       getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random zombie-spawn bonus", var9, 2));
/*     */     }
/*     */     
/* 579 */     if (this.rand.nextFloat() < var3 * 0.05F) {
/*     */       
/* 581 */       getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
/* 582 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
/* 583 */       func_146070_a(true);
/*     */     } 
/*     */     
/* 586 */     return (IEntityLivingData)p_180482_2_1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(EntityPlayer p_70085_1_) {
/* 594 */     ItemStack var2 = p_70085_1_.getCurrentEquippedItem();
/*     */     
/* 596 */     if (var2 != null && var2.getItem() == Items.golden_apple && var2.getMetadata() == 0 && isVillager() && isPotionActive(Potion.weakness)) {
/*     */       
/* 598 */       if (!p_70085_1_.capabilities.isCreativeMode)
/*     */       {
/* 600 */         var2.stackSize--;
/*     */       }
/*     */       
/* 603 */       if (var2.stackSize <= 0)
/*     */       {
/* 605 */         p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, null);
/*     */       }
/*     */       
/* 608 */       if (!this.worldObj.isRemote)
/*     */       {
/* 610 */         startConversion(this.rand.nextInt(2401) + 3600);
/*     */       }
/*     */       
/* 613 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 617 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void startConversion(int p_82228_1_) {
/* 627 */     this.conversionTime = p_82228_1_;
/* 628 */     getDataWatcher().updateObject(14, Byte.valueOf((byte)1));
/* 629 */     removePotionEffect(Potion.weakness.id);
/* 630 */     addPotionEffect(new PotionEffect(Potion.damageBoost.id, p_82228_1_, Math.min(this.worldObj.getDifficulty().getDifficultyId() - 1, 0)));
/* 631 */     this.worldObj.setEntityState((Entity)this, (byte)16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 636 */     if (p_70103_1_ == 16) {
/*     */       
/* 638 */       if (!isSlient())
/*     */       {
/* 640 */         this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "mob.zombie.remedy", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 645 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 654 */     return !isConverting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 662 */     return (getDataWatcher().getWatchableObjectByte(14) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void convertToVillager() {
/* 670 */     EntityVillager var1 = new EntityVillager(this.worldObj);
/* 671 */     var1.copyLocationAndAnglesFrom((Entity)this);
/* 672 */     var1.func_180482_a(this.worldObj.getDifficultyForLocation(new BlockPos((Entity)var1)), null);
/* 673 */     var1.setLookingForHome();
/*     */     
/* 675 */     if (isChild())
/*     */     {
/* 677 */       var1.setGrowingAge(-24000);
/*     */     }
/*     */     
/* 680 */     this.worldObj.removeEntity((Entity)this);
/* 681 */     this.worldObj.spawnEntityInWorld((Entity)var1);
/* 682 */     var1.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
/* 683 */     this.worldObj.playAuxSFXAtEntity(null, 1017, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getConversionTimeBoost() {
/* 691 */     int var1 = 1;
/*     */     
/* 693 */     if (this.rand.nextFloat() < 0.01F) {
/*     */       
/* 695 */       int var2 = 0;
/*     */       
/* 697 */       for (int var3 = (int)this.posX - 4; var3 < (int)this.posX + 4 && var2 < 14; var3++) {
/*     */         
/* 699 */         for (int var4 = (int)this.posY - 4; var4 < (int)this.posY + 4 && var2 < 14; var4++) {
/*     */           
/* 701 */           for (int var5 = (int)this.posZ - 4; var5 < (int)this.posZ + 4 && var2 < 14; var5++) {
/*     */             
/* 703 */             Block var6 = this.worldObj.getBlockState(new BlockPos(var3, var4, var5)).getBlock();
/*     */             
/* 705 */             if (var6 == Blocks.iron_bars || var6 == Blocks.bed) {
/*     */               
/* 707 */               if (this.rand.nextFloat() < 0.3F)
/*     */               {
/* 709 */                 var1++;
/*     */               }
/*     */               
/* 712 */               var2++;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 719 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146071_k(boolean p_146071_1_) {
/* 724 */     func_146069_a(p_146071_1_ ? 0.5F : 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void setSize(float width, float height) {
/* 732 */     boolean var3 = (this.field_146074_bv > 0.0F && this.field_146073_bw > 0.0F);
/* 733 */     this.field_146074_bv = width;
/* 734 */     this.field_146073_bw = height;
/*     */     
/* 736 */     if (!var3)
/*     */     {
/* 738 */       func_146069_a(1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void func_146069_a(float p_146069_1_) {
/* 744 */     super.setSize(this.field_146074_bv * p_146069_1_, this.field_146073_bw * p_146069_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYOffset() {
/* 752 */     return super.getYOffset() - 0.5D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource cause) {
/* 760 */     super.onDeath(cause);
/*     */     
/* 762 */     if (cause.getEntity() instanceof EntityCreeper && !(this instanceof EntityPigZombie) && ((EntityCreeper)cause.getEntity()).getPowered() && ((EntityCreeper)cause.getEntity()).isAIEnabled()) {
/*     */       
/* 764 */       ((EntityCreeper)cause.getEntity()).func_175493_co();
/* 765 */       entityDropItem(new ItemStack(Items.skull, 1, 2), 0.0F);
/*     */     } 
/*     */   }
/*     */   
/*     */   class GroupData
/*     */     implements IEntityLivingData
/*     */   {
/*     */     public boolean field_142048_a;
/*     */     public boolean field_142046_b;
/*     */     private static final String __OBFID = "CL_00001704";
/*     */     
/*     */     private GroupData(boolean p_i2348_2_, boolean p_i2348_3_) {
/* 777 */       this.field_142048_a = false;
/* 778 */       this.field_142046_b = false;
/* 779 */       this.field_142048_a = p_i2348_2_;
/* 780 */       this.field_142046_b = p_i2348_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     GroupData(boolean p_i2349_2_, boolean p_i2349_3_, Object p_i2349_4_) {
/* 785 */       this(p_i2349_2_, p_i2349_3_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */