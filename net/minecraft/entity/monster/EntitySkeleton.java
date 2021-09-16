/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Calendar;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIFleeSun;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictSun;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySkeleton extends EntityMob implements IRangedAttackMob {
/*  45 */   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
/*  46 */   private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
/*     */   
/*     */   private static final String __OBFID = "CL_00001697";
/*     */   
/*     */   public EntitySkeleton(World worldIn) {
/*  51 */     super(worldIn);
/*  52 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  53 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIRestrictSun(this));
/*  54 */     this.tasks.addTask(2, this.field_175455_a);
/*  55 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIFleeSun(this, 1.0D));
/*  56 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIAvoidEntity(this, new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002203";
/*     */             
/*     */             public boolean func_179945_a(Entity p_179945_1_) {
/*  61 */               return p_179945_1_ instanceof net.minecraft.entity.passive.EntityWolf;
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/*  65 */               return func_179945_a((Entity)p_apply_1_);
/*     */             }
/*  67 */           },  6.0F, 1.0D, 1.2D));
/*  68 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIWander(this, 1.0D));
/*  69 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  70 */     this.tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  71 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget(this, false, new Class[0]));
/*  72 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  73 */     this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
/*     */     
/*  75 */     if (worldIn != null && !worldIn.isRemote)
/*     */     {
/*  77 */       setCombatTask();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  83 */     super.applyEntityAttributes();
/*  84 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  89 */     super.entityInit();
/*  90 */     this.dataWatcher.addObject(13, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  98 */     return "mob.skeleton.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 106 */     return "mob.skeleton.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 114 */     return "mob.skeleton.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/* 119 */     playSound("mob.skeleton.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 124 */     if (super.attackEntityAsMob(p_70652_1_)) {
/*     */       
/* 126 */       if (getSkeletonType() == 1 && p_70652_1_ instanceof EntityLivingBase)
/*     */       {
/* 128 */         ((EntityLivingBase)p_70652_1_).addPotionEffect(new PotionEffect(Potion.wither.id, 200));
/*     */       }
/*     */       
/* 131 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 144 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 153 */     if (this.worldObj.isDaytime() && !this.worldObj.isRemote) {
/*     */       
/* 155 */       float var1 = getBrightness(1.0F);
/* 156 */       BlockPos var2 = new BlockPos(this.posX, Math.round(this.posY), this.posZ);
/*     */       
/* 158 */       if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.worldObj.isAgainstSky(var2)) {
/*     */         
/* 160 */         boolean var3 = true;
/* 161 */         ItemStack var4 = getEquipmentInSlot(4);
/*     */         
/* 163 */         if (var4 != null) {
/*     */           
/* 165 */           if (var4.isItemStackDamageable()) {
/*     */             
/* 167 */             var4.setItemDamage(var4.getItemDamage() + this.rand.nextInt(2));
/*     */             
/* 169 */             if (var4.getItemDamage() >= var4.getMaxDamage()) {
/*     */               
/* 171 */               renderBrokenItemStack(var4);
/* 172 */               setCurrentItemOrArmor(4, (ItemStack)null);
/*     */             } 
/*     */           } 
/*     */           
/* 176 */           var3 = false;
/*     */         } 
/*     */         
/* 179 */         if (var3)
/*     */         {
/* 181 */           setFire(8);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     if (this.worldObj.isRemote && getSkeletonType() == 1)
/*     */     {
/* 188 */       setSize(0.72F, 2.535F);
/*     */     }
/*     */     
/* 191 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateRidden() {
/* 199 */     super.updateRidden();
/*     */     
/* 201 */     if (this.ridingEntity instanceof EntityCreature) {
/*     */       
/* 203 */       EntityCreature var1 = (EntityCreature)this.ridingEntity;
/* 204 */       this.renderYawOffset = var1.renderYawOffset;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource cause) {
/* 213 */     super.onDeath(cause);
/*     */     
/* 215 */     if (cause.getSourceOfDamage() instanceof EntityArrow && cause.getEntity() instanceof EntityPlayer) {
/*     */       
/* 217 */       EntityPlayer var2 = (EntityPlayer)cause.getEntity();
/* 218 */       double var3 = var2.posX - this.posX;
/* 219 */       double var5 = var2.posZ - this.posZ;
/*     */       
/* 221 */       if (var3 * var3 + var5 * var5 >= 2500.0D)
/*     */       {
/* 223 */         var2.triggerAchievement((StatBase)AchievementList.snipeSkeleton);
/*     */       }
/*     */     }
/* 226 */     else if (cause.getEntity() instanceof EntityCreeper && ((EntityCreeper)cause.getEntity()).getPowered() && ((EntityCreeper)cause.getEntity()).isAIEnabled()) {
/*     */       
/* 228 */       ((EntityCreeper)cause.getEntity()).func_175493_co();
/* 229 */       entityDropItem(new ItemStack(Items.skull, 1, (getSkeletonType() == 1) ? 1 : 0), 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 235 */     return Items.arrow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 246 */     if (getSkeletonType() == 1) {
/*     */       
/* 248 */       int var3 = this.rand.nextInt(3 + p_70628_2_) - 1;
/*     */       
/* 250 */       for (int var4 = 0; var4 < var3; var4++)
/*     */       {
/* 252 */         dropItem(Items.coal, 1);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 257 */       int var3 = this.rand.nextInt(3 + p_70628_2_);
/*     */       
/* 259 */       for (int var4 = 0; var4 < var3; var4++)
/*     */       {
/* 261 */         dropItem(Items.arrow, 1);
/*     */       }
/*     */     } 
/*     */     
/* 265 */     int i = this.rand.nextInt(3 + p_70628_2_);
/*     */     
/* 267 */     for (byte b = 0; b < i; b++)
/*     */     {
/* 269 */       dropItem(Items.bone, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addRandomArmor() {
/* 278 */     if (getSkeletonType() == 1)
/*     */     {
/* 280 */       entityDropItem(new ItemStack(Items.skull, 1, 1), 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180481_a(DifficultyInstance p_180481_1_) {
/* 286 */     super.func_180481_a(p_180481_1_);
/* 287 */     setCurrentItemOrArmor(0, new ItemStack((Item)Items.bow));
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 292 */     p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
/*     */     
/* 294 */     if (this.worldObj.provider instanceof net.minecraft.world.WorldProviderHell && getRNG().nextInt(5) > 0) {
/*     */       
/* 296 */       this.tasks.addTask(4, (EntityAIBase)this.aiAttackOnCollide);
/* 297 */       setSkeletonType(1);
/* 298 */       setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
/* 299 */       getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
/*     */     }
/*     */     else {
/*     */       
/* 303 */       this.tasks.addTask(4, (EntityAIBase)this.aiArrowAttack);
/* 304 */       func_180481_a(p_180482_1_);
/* 305 */       func_180483_b(p_180482_1_);
/*     */     } 
/*     */     
/* 308 */     setCanPickUpLoot((this.rand.nextFloat() < 0.55F * p_180482_1_.func_180170_c()));
/*     */     
/* 310 */     if (getEquipmentInSlot(4) == null) {
/*     */       
/* 312 */       Calendar var3 = this.worldObj.getCurrentDate();
/*     */       
/* 314 */       if (var3.get(2) + 1 == 10 && var3.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
/*     */         
/* 316 */         setCurrentItemOrArmor(4, new ItemStack((this.rand.nextFloat() < 0.1F) ? Blocks.lit_pumpkin : Blocks.pumpkin));
/* 317 */         this.equipmentDropChances[4] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 321 */     return p_180482_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCombatTask() {
/* 329 */     this.tasks.removeTask((EntityAIBase)this.aiAttackOnCollide);
/* 330 */     this.tasks.removeTask((EntityAIBase)this.aiArrowAttack);
/* 331 */     ItemStack var1 = getHeldItem();
/*     */     
/* 333 */     if (var1 != null && var1.getItem() == Items.bow) {
/*     */       
/* 335 */       this.tasks.addTask(4, (EntityAIBase)this.aiArrowAttack);
/*     */     }
/*     */     else {
/*     */       
/* 339 */       this.tasks.addTask(4, (EntityAIBase)this.aiAttackOnCollide);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
/* 348 */     EntityArrow var3 = new EntityArrow(this.worldObj, (EntityLivingBase)this, p_82196_1_, 1.6F, (14 - this.worldObj.getDifficulty().getDifficultyId() * 4));
/* 349 */     int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItem());
/* 350 */     int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItem());
/* 351 */     var3.setDamage((p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (this.worldObj.getDifficulty().getDifficultyId() * 0.11F));
/*     */     
/* 353 */     if (var4 > 0)
/*     */     {
/* 355 */       var3.setDamage(var3.getDamage() + var4 * 0.5D + 0.5D);
/*     */     }
/*     */     
/* 358 */     if (var5 > 0)
/*     */     {
/* 360 */       var3.setKnockbackStrength(var5);
/*     */     }
/*     */     
/* 363 */     if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, getHeldItem()) > 0 || getSkeletonType() == 1)
/*     */     {
/* 365 */       var3.setFire(100);
/*     */     }
/*     */     
/* 368 */     playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
/* 369 */     this.worldObj.spawnEntityInWorld((Entity)var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSkeletonType() {
/* 377 */     return this.dataWatcher.getWatchableObjectByte(13);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSkeletonType(int p_82201_1_) {
/* 385 */     this.dataWatcher.updateObject(13, Byte.valueOf((byte)p_82201_1_));
/* 386 */     this.isImmuneToFire = (p_82201_1_ == 1);
/*     */     
/* 388 */     if (p_82201_1_ == 1) {
/*     */       
/* 390 */       setSize(0.72F, 2.535F);
/*     */     }
/*     */     else {
/*     */       
/* 394 */       setSize(0.6F, 1.95F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 403 */     super.readEntityFromNBT(tagCompund);
/*     */     
/* 405 */     if (tagCompund.hasKey("SkeletonType", 99)) {
/*     */       
/* 407 */       byte var2 = tagCompund.getByte("SkeletonType");
/* 408 */       setSkeletonType(var2);
/*     */     } 
/*     */     
/* 411 */     setCombatTask();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 419 */     super.writeEntityToNBT(tagCompound);
/* 420 */     tagCompound.setByte("SkeletonType", (byte)getSkeletonType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentItemOrArmor(int slotIn, ItemStack itemStackIn) {
/* 428 */     super.setCurrentItemOrArmor(slotIn, itemStackIn);
/*     */     
/* 430 */     if (!this.worldObj.isRemote && slotIn == 0)
/*     */     {
/* 432 */       setCombatTask();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 438 */     return (getSkeletonType() == 1) ? super.getEyeHeight() : 1.74F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYOffset() {
/* 446 */     return super.getYOffset() - 0.5D;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntitySkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */