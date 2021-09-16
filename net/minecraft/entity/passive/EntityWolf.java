/*     */ package net.minecraft.entity.passive;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIBeg;
/*     */ import net.minecraft.entity.ai.EntityAIFollowOwner;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMate;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITargetNonTamed;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.monster.EntitySkeleton;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityWolf
/*     */   extends EntityTameable
/*     */ {
/*     */   private float headRotationCourse;
/*     */   private float headRotationCourseOld;
/*     */   private boolean isWet;
/*     */   private boolean isShaking;
/*     */   private float timeWolfIsShaking;
/*     */   private float prevTimeWolfIsShaking;
/*     */   private static final String __OBFID = "CL_00001654";
/*     */   
/*     */   public EntityWolf(World worldIn) {
/*  62 */     super(worldIn);
/*  63 */     setSize(0.6F, 0.8F);
/*  64 */     ((PathNavigateGround)getNavigator()).func_179690_a(true);
/*  65 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  66 */     this.tasks.addTask(2, (EntityAIBase)this.aiSit);
/*  67 */     this.tasks.addTask(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4F));
/*  68 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0D, true));
/*  69 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
/*  70 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIMate(this, 1.0D));
/*  71 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
/*  72 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIBeg(this, 8.0F));
/*  73 */     this.tasks.addTask(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  74 */     this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  75 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIOwnerHurtByTarget(this));
/*  76 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAIOwnerHurtTarget(this));
/*  77 */     this.targetTasks.addTask(3, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true, new Class[0]));
/*  78 */     this.targetTasks.addTask(4, (EntityAIBase)new EntityAITargetNonTamed(this, EntityAnimal.class, false, new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002229";
/*     */             
/*     */             public boolean func_180094_a(Entity p_180094_1_) {
/*  83 */               return !(!(p_180094_1_ instanceof EntitySheep) && !(p_180094_1_ instanceof EntityRabbit));
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/*  87 */               return func_180094_a((Entity)p_apply_1_);
/*     */             }
/*     */           }));
/*  90 */     this.targetTasks.addTask(5, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntitySkeleton.class, false));
/*  91 */     setTamed(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  96 */     super.applyEntityAttributes();
/*  97 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
/*     */     
/*  99 */     if (isTamed()) {
/*     */       
/* 101 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
/*     */     }
/*     */     else {
/*     */       
/* 105 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
/*     */     } 
/*     */     
/* 108 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
/* 109 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttackTarget(EntityLivingBase p_70624_1_) {
/* 117 */     super.setAttackTarget(p_70624_1_);
/*     */     
/* 119 */     if (p_70624_1_ == null) {
/*     */       
/* 121 */       setAngry(false);
/*     */     }
/* 123 */     else if (!isTamed()) {
/*     */       
/* 125 */       setAngry(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/* 131 */     this.dataWatcher.updateObject(18, Float.valueOf(getHealth()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 136 */     super.entityInit();
/* 137 */     this.dataWatcher.addObject(18, new Float(getHealth()));
/* 138 */     this.dataWatcher.addObject(19, new Byte((byte)0));
/* 139 */     this.dataWatcher.addObject(20, new Byte((byte)EnumDyeColor.RED.func_176765_a()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/* 144 */     playSound("mob.wolf.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 152 */     super.writeEntityToNBT(tagCompound);
/* 153 */     tagCompound.setBoolean("Angry", isAngry());
/* 154 */     tagCompound.setByte("CollarColor", (byte)func_175546_cu().getDyeColorDamage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 162 */     super.readEntityFromNBT(tagCompund);
/* 163 */     setAngry(tagCompund.getBoolean("Angry"));
/*     */     
/* 165 */     if (tagCompund.hasKey("CollarColor", 99))
/*     */     {
/* 167 */       func_175547_a(EnumDyeColor.func_176766_a(tagCompund.getByte("CollarColor")));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 176 */     return isAngry() ? "mob.wolf.growl" : ((this.rand.nextInt(3) == 0) ? ((isTamed() && this.dataWatcher.getWatchableObjectFloat(18) < 10.0F) ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 184 */     return "mob.wolf.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 192 */     return "mob.wolf.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 200 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 205 */     return Item.getItemById(-1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 214 */     super.onLivingUpdate();
/*     */     
/* 216 */     if (!this.worldObj.isRemote && this.isWet && !this.isShaking && !hasPath() && this.onGround) {
/*     */       
/* 218 */       this.isShaking = true;
/* 219 */       this.timeWolfIsShaking = 0.0F;
/* 220 */       this.prevTimeWolfIsShaking = 0.0F;
/* 221 */       this.worldObj.setEntityState((Entity)this, (byte)8);
/*     */     } 
/*     */     
/* 224 */     if (!this.worldObj.isRemote && getAttackTarget() == null && isAngry())
/*     */     {
/* 226 */       setAngry(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 235 */     super.onUpdate();
/* 236 */     this.headRotationCourseOld = this.headRotationCourse;
/*     */     
/* 238 */     if (func_70922_bv()) {
/*     */       
/* 240 */       this.headRotationCourse += (1.0F - this.headRotationCourse) * 0.4F;
/*     */     }
/*     */     else {
/*     */       
/* 244 */       this.headRotationCourse += (0.0F - this.headRotationCourse) * 0.4F;
/*     */     } 
/*     */     
/* 247 */     if (isWet()) {
/*     */       
/* 249 */       this.isWet = true;
/* 250 */       this.isShaking = false;
/* 251 */       this.timeWolfIsShaking = 0.0F;
/* 252 */       this.prevTimeWolfIsShaking = 0.0F;
/*     */     }
/* 254 */     else if ((this.isWet || this.isShaking) && this.isShaking) {
/*     */       
/* 256 */       if (this.timeWolfIsShaking == 0.0F)
/*     */       {
/* 258 */         playSound("mob.wolf.shake", getSoundVolume(), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*     */       }
/*     */       
/* 261 */       this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
/* 262 */       this.timeWolfIsShaking += 0.05F;
/*     */       
/* 264 */       if (this.prevTimeWolfIsShaking >= 2.0F) {
/*     */         
/* 266 */         this.isWet = false;
/* 267 */         this.isShaking = false;
/* 268 */         this.prevTimeWolfIsShaking = 0.0F;
/* 269 */         this.timeWolfIsShaking = 0.0F;
/*     */       } 
/*     */       
/* 272 */       if (this.timeWolfIsShaking > 0.4F) {
/*     */         
/* 274 */         float var1 = (float)(getEntityBoundingBox()).minY;
/* 275 */         int var2 = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * 3.1415927F) * 7.0F);
/*     */         
/* 277 */         for (int var3 = 0; var3 < var2; var3++) {
/*     */           
/* 279 */           float var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
/* 280 */           float var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
/* 281 */           this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + var4, (var1 + 0.8F), this.posZ + var5, this.motionX, this.motionY, this.motionZ, new int[0]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWolfWet() {
/* 292 */     return this.isWet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadingWhileWet(float p_70915_1_) {
/* 300 */     return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70915_1_) / 2.0F * 0.25F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShakeAngle(float p_70923_1_, float p_70923_2_) {
/* 305 */     float var3 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * p_70923_1_ + p_70923_2_) / 1.8F;
/*     */     
/* 307 */     if (var3 < 0.0F) {
/*     */       
/* 309 */       var3 = 0.0F;
/*     */     }
/* 311 */     else if (var3 > 1.0F) {
/*     */       
/* 313 */       var3 = 1.0F;
/*     */     } 
/*     */     
/* 316 */     return MathHelper.sin(var3 * 3.1415927F) * MathHelper.sin(var3 * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getInterestedAngle(float p_70917_1_) {
/* 321 */     return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * 3.1415927F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 326 */     return this.height * 0.8F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVerticalFaceSpeed() {
/* 335 */     return isSitting() ? 20 : super.getVerticalFaceSpeed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 343 */     if (func_180431_b(source))
/*     */     {
/* 345 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 349 */     Entity var3 = source.getEntity();
/* 350 */     this.aiSit.setSitting(false);
/*     */     
/* 352 */     if (var3 != null && !(var3 instanceof EntityPlayer) && !(var3 instanceof net.minecraft.entity.projectile.EntityArrow))
/*     */     {
/* 354 */       amount = (amount + 1.0F) / 2.0F;
/*     */     }
/*     */     
/* 357 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 363 */     boolean var2 = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (int)getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
/*     */     
/* 365 */     if (var2)
/*     */     {
/* 367 */       func_174815_a((EntityLivingBase)this, p_70652_1_);
/*     */     }
/*     */     
/* 370 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTamed(boolean p_70903_1_) {
/* 375 */     super.setTamed(p_70903_1_);
/*     */     
/* 377 */     if (p_70903_1_) {
/*     */       
/* 379 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
/*     */     }
/*     */     else {
/*     */       
/* 383 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
/*     */     } 
/*     */     
/* 386 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(EntityPlayer p_70085_1_) {
/* 394 */     ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
/*     */     
/* 396 */     if (isTamed()) {
/*     */       
/* 398 */       if (var2 != null)
/*     */       {
/* 400 */         if (var2.getItem() instanceof ItemFood) {
/*     */           
/* 402 */           ItemFood var3 = (ItemFood)var2.getItem();
/*     */           
/* 404 */           if (var3.isWolfsFavoriteMeat() && this.dataWatcher.getWatchableObjectFloat(18) < 20.0F)
/*     */           {
/* 406 */             if (!p_70085_1_.capabilities.isCreativeMode)
/*     */             {
/* 408 */               var2.stackSize--;
/*     */             }
/*     */             
/* 411 */             heal(var3.getHealAmount(var2));
/*     */             
/* 413 */             if (var2.stackSize <= 0)
/*     */             {
/* 415 */               p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, null);
/*     */             }
/*     */             
/* 418 */             return true;
/*     */           }
/*     */         
/* 421 */         } else if (var2.getItem() == Items.dye) {
/*     */           
/* 423 */           EnumDyeColor var4 = EnumDyeColor.func_176766_a(var2.getMetadata());
/*     */           
/* 425 */           if (var4 != func_175546_cu()) {
/*     */             
/* 427 */             func_175547_a(var4);
/*     */             
/* 429 */             if (!p_70085_1_.capabilities.isCreativeMode && --var2.stackSize <= 0)
/*     */             {
/* 431 */               p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, null);
/*     */             }
/*     */             
/* 434 */             return true;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 439 */       if (func_152114_e((EntityLivingBase)p_70085_1_) && !this.worldObj.isRemote && !isBreedingItem(var2))
/*     */       {
/* 441 */         this.aiSit.setSitting(!isSitting());
/* 442 */         this.isJumping = false;
/* 443 */         this.navigator.clearPathEntity();
/* 444 */         setAttackTarget((EntityLivingBase)null);
/*     */       }
/*     */     
/* 447 */     } else if (var2 != null && var2.getItem() == Items.bone && !isAngry()) {
/*     */       
/* 449 */       if (!p_70085_1_.capabilities.isCreativeMode)
/*     */       {
/* 451 */         var2.stackSize--;
/*     */       }
/*     */       
/* 454 */       if (var2.stackSize <= 0)
/*     */       {
/* 456 */         p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, null);
/*     */       }
/*     */       
/* 459 */       if (!this.worldObj.isRemote)
/*     */       {
/* 461 */         if (this.rand.nextInt(3) == 0) {
/*     */           
/* 463 */           setTamed(true);
/* 464 */           this.navigator.clearPathEntity();
/* 465 */           setAttackTarget((EntityLivingBase)null);
/* 466 */           this.aiSit.setSitting(true);
/* 467 */           setHealth(20.0F);
/* 468 */           func_152115_b(p_70085_1_.getUniqueID().toString());
/* 469 */           playTameEffect(true);
/* 470 */           this.worldObj.setEntityState((Entity)this, (byte)7);
/*     */         }
/*     */         else {
/*     */           
/* 474 */           playTameEffect(false);
/* 475 */           this.worldObj.setEntityState((Entity)this, (byte)6);
/*     */         } 
/*     */       }
/*     */       
/* 479 */       return true;
/*     */     } 
/*     */     
/* 482 */     return super.interact(p_70085_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 487 */     if (p_70103_1_ == 8) {
/*     */       
/* 489 */       this.isShaking = true;
/* 490 */       this.timeWolfIsShaking = 0.0F;
/* 491 */       this.prevTimeWolfIsShaking = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 495 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getTailRotation() {
/* 501 */     return isAngry() ? 1.5393804F : (isTamed() ? ((0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * 3.1415927F) : 0.62831855F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBreedingItem(ItemStack p_70877_1_) {
/* 510 */     return (p_70877_1_ == null) ? false : (!(p_70877_1_.getItem() instanceof ItemFood) ? false : ((ItemFood)p_70877_1_.getItem()).isWolfsFavoriteMeat());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 518 */     return 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAngry() {
/* 526 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x2) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAngry(boolean p_70916_1_) {
/* 534 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 536 */     if (p_70916_1_) {
/*     */       
/* 538 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x2)));
/*     */     }
/*     */     else {
/*     */       
/* 542 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFD)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDyeColor func_175546_cu() {
/* 548 */     return EnumDyeColor.func_176766_a(this.dataWatcher.getWatchableObjectByte(20) & 0xF);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175547_a(EnumDyeColor p_175547_1_) {
/* 553 */     this.dataWatcher.updateObject(20, Byte.valueOf((byte)(p_175547_1_.getDyeColorDamage() & 0xF)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityWolf createChild(EntityAgeable p_90011_1_) {
/* 558 */     EntityWolf var2 = new EntityWolf(this.worldObj);
/* 559 */     String var3 = func_152113_b();
/*     */     
/* 561 */     if (var3 != null && var3.trim().length() > 0) {
/*     */       
/* 563 */       var2.func_152115_b(var3);
/* 564 */       var2.setTamed(true);
/*     */     } 
/*     */     
/* 567 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70918_i(boolean p_70918_1_) {
/* 572 */     if (p_70918_1_) {
/*     */       
/* 574 */       this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
/*     */     }
/*     */     else {
/*     */       
/* 578 */       this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(EntityAnimal p_70878_1_) {
/* 587 */     if (p_70878_1_ == this)
/*     */     {
/* 589 */       return false;
/*     */     }
/* 591 */     if (!isTamed())
/*     */     {
/* 593 */       return false;
/*     */     }
/* 595 */     if (!(p_70878_1_ instanceof EntityWolf))
/*     */     {
/* 597 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 601 */     EntityWolf var2 = (EntityWolf)p_70878_1_;
/* 602 */     return !var2.isTamed() ? false : (var2.isSitting() ? false : ((isInLove() && var2.isInLove())));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70922_bv() {
/* 608 */     return (this.dataWatcher.getWatchableObjectByte(19) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 616 */     return (!isTamed() && this.ticksExisted > 2400);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_142018_a(EntityLivingBase p_142018_1_, EntityLivingBase p_142018_2_) {
/* 621 */     if (!(p_142018_1_ instanceof net.minecraft.entity.monster.EntityCreeper) && !(p_142018_1_ instanceof net.minecraft.entity.monster.EntityGhast)) {
/*     */       
/* 623 */       if (p_142018_1_ instanceof EntityWolf) {
/*     */         
/* 625 */         EntityWolf var3 = (EntityWolf)p_142018_1_;
/*     */         
/* 627 */         if (var3.isTamed() && var3.func_180492_cm() == p_142018_2_)
/*     */         {
/* 629 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 633 */       return (p_142018_1_ instanceof EntityPlayer && p_142018_2_ instanceof EntityPlayer && !((EntityPlayer)p_142018_2_).canAttackPlayer((EntityPlayer)p_142018_1_)) ? false : (!(p_142018_1_ instanceof EntityHorse && ((EntityHorse)p_142018_1_).isTame()));
/*     */     } 
/*     */ 
/*     */     
/* 637 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean allowLeashing() {
/* 643 */     return (!isAngry() && super.allowLeashing());
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */