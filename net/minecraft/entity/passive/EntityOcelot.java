/*     */ package net.minecraft.entity.passive;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIFollowOwner;
/*     */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*     */ import net.minecraft.entity.ai.EntityAIMate;
/*     */ import net.minecraft.entity.ai.EntityAIOcelotAttack;
/*     */ import net.minecraft.entity.ai.EntityAIOcelotSit;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITargetNonTamed;
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityOcelot
/*     */   extends EntityTameable
/*     */ {
/*     */   private EntityAIAvoidEntity field_175545_bm;
/*     */   private EntityAITempt aiTempt;
/*     */   private static final String __OBFID = "CL_00001646";
/*     */   
/*     */   public EntityOcelot(World worldIn) {
/*  46 */     super(worldIn);
/*  47 */     setSize(0.6F, 0.7F);
/*  48 */     ((PathNavigateGround)getNavigator()).func_179690_a(true);
/*  49 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  50 */     this.tasks.addTask(2, (EntityAIBase)this.aiSit);
/*  51 */     this.tasks.addTask(3, (EntityAIBase)(this.aiTempt = new EntityAITempt((EntityCreature)this, 0.6D, Items.fish, true)));
/*  52 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
/*  53 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIOcelotSit(this, 0.8D));
/*  54 */     this.tasks.addTask(7, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.3F));
/*  55 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIOcelotAttack((EntityLiving)this));
/*  56 */     this.tasks.addTask(9, (EntityAIBase)new EntityAIMate(this, 0.8D));
/*  57 */     this.tasks.addTask(10, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8D));
/*  58 */     this.tasks.addTask(11, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 10.0F));
/*  59 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAITargetNonTamed(this, EntityChicken.class, false, null));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  64 */     super.entityInit();
/*  65 */     this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAITasks() {
/*  70 */     if (getMoveHelper().isUpdating()) {
/*     */       
/*  72 */       double var1 = getMoveHelper().getSpeed();
/*     */       
/*  74 */       if (var1 == 0.6D)
/*     */       {
/*  76 */         setSneaking(true);
/*  77 */         setSprinting(false);
/*     */       }
/*  79 */       else if (var1 == 1.33D)
/*     */       {
/*  81 */         setSneaking(false);
/*  82 */         setSprinting(true);
/*     */       }
/*     */       else
/*     */       {
/*  86 */         setSneaking(false);
/*  87 */         setSprinting(false);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  92 */       setSneaking(false);
/*  93 */       setSprinting(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 102 */     return (!isTamed() && this.ticksExisted > 2400);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 107 */     super.applyEntityAttributes();
/* 108 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
/* 109 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float distance, float damageMultiplier) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 119 */     super.writeEntityToNBT(tagCompound);
/* 120 */     tagCompound.setInteger("CatType", getTameSkin());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 128 */     super.readEntityFromNBT(tagCompund);
/* 129 */     setTameSkin(tagCompund.getInteger("CatType"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 137 */     return isTamed() ? (isInLove() ? "mob.cat.purr" : ((this.rand.nextInt(4) == 0) ? "mob.cat.purreow" : "mob.cat.meow")) : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 145 */     return "mob.cat.hitt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 153 */     return "mob.cat.hitt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 161 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 166 */     return Items.leather;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 171 */     return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 3.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 179 */     if (func_180431_b(source))
/*     */     {
/* 181 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 185 */     this.aiSit.setSitting(false);
/* 186 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(EntityPlayer p_70085_1_) {
/* 200 */     ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
/*     */     
/* 202 */     if (isTamed()) {
/*     */       
/* 204 */       if (func_152114_e((EntityLivingBase)p_70085_1_) && !this.worldObj.isRemote && !isBreedingItem(var2))
/*     */       {
/* 206 */         this.aiSit.setSitting(!isSitting());
/*     */       }
/*     */     }
/* 209 */     else if (this.aiTempt.isRunning() && var2 != null && var2.getItem() == Items.fish && p_70085_1_.getDistanceSqToEntity((Entity)this) < 9.0D) {
/*     */       
/* 211 */       if (!p_70085_1_.capabilities.isCreativeMode)
/*     */       {
/* 213 */         var2.stackSize--;
/*     */       }
/*     */       
/* 216 */       if (var2.stackSize <= 0)
/*     */       {
/* 218 */         p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, null);
/*     */       }
/*     */       
/* 221 */       if (!this.worldObj.isRemote)
/*     */       {
/* 223 */         if (this.rand.nextInt(3) == 0) {
/*     */           
/* 225 */           setTamed(true);
/* 226 */           setTameSkin(1 + this.worldObj.rand.nextInt(3));
/* 227 */           func_152115_b(p_70085_1_.getUniqueID().toString());
/* 228 */           playTameEffect(true);
/* 229 */           this.aiSit.setSitting(true);
/* 230 */           this.worldObj.setEntityState((Entity)this, (byte)7);
/*     */         }
/*     */         else {
/*     */           
/* 234 */           playTameEffect(false);
/* 235 */           this.worldObj.setEntityState((Entity)this, (byte)6);
/*     */         } 
/*     */       }
/*     */       
/* 239 */       return true;
/*     */     } 
/*     */     
/* 242 */     return super.interact(p_70085_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityOcelot func_180493_b(EntityAgeable p_180493_1_) {
/* 247 */     EntityOcelot var2 = new EntityOcelot(this.worldObj);
/*     */     
/* 249 */     if (isTamed()) {
/*     */       
/* 251 */       var2.func_152115_b(func_152113_b());
/* 252 */       var2.setTamed(true);
/* 253 */       var2.setTameSkin(getTameSkin());
/*     */     } 
/*     */     
/* 256 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBreedingItem(ItemStack p_70877_1_) {
/* 265 */     return (p_70877_1_ != null && p_70877_1_.getItem() == Items.fish);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(EntityAnimal p_70878_1_) {
/* 273 */     if (p_70878_1_ == this)
/*     */     {
/* 275 */       return false;
/*     */     }
/* 277 */     if (!isTamed())
/*     */     {
/* 279 */       return false;
/*     */     }
/* 281 */     if (!(p_70878_1_ instanceof EntityOcelot))
/*     */     {
/* 283 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 287 */     EntityOcelot var2 = (EntityOcelot)p_70878_1_;
/* 288 */     return !var2.isTamed() ? false : ((isInLove() && var2.isInLove()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTameSkin() {
/* 294 */     return this.dataWatcher.getWatchableObjectByte(18);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTameSkin(int p_70912_1_) {
/* 299 */     this.dataWatcher.updateObject(18, Byte.valueOf((byte)p_70912_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 307 */     return (this.worldObj.rand.nextInt(3) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleLavaMovement() {
/* 315 */     if (this.worldObj.checkNoEntityCollision(getEntityBoundingBox(), (Entity)this) && this.worldObj.getCollidingBoundingBoxes((Entity)this, getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(getEntityBoundingBox())) {
/*     */       
/* 317 */       BlockPos var1 = new BlockPos(this.posX, (getEntityBoundingBox()).minY, this.posZ);
/*     */       
/* 319 */       if (var1.getY() < 63)
/*     */       {
/* 321 */         return false;
/*     */       }
/*     */       
/* 324 */       Block var2 = this.worldObj.getBlockState(var1.offsetDown()).getBlock();
/*     */       
/* 326 */       if (var2 == Blocks.grass || var2.getMaterial() == Material.leaves)
/*     */       {
/* 328 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 332 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 340 */     return hasCustomName() ? getCustomNameTag() : (isTamed() ? StatCollector.translateToLocal("entity.Cat.name") : super.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTamed(boolean p_70903_1_) {
/* 345 */     super.setTamed(p_70903_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175544_ck() {
/* 350 */     if (this.field_175545_bm == null)
/*     */     {
/* 352 */       this.field_175545_bm = new EntityAIAvoidEntity((EntityCreature)this, new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002243";
/*     */             
/*     */             public boolean func_179874_a(Entity p_179874_1_) {
/* 357 */               return p_179874_1_ instanceof EntityPlayer;
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/* 361 */               return func_179874_a((Entity)p_apply_1_);
/*     */             }
/* 363 */           },  16.0F, 0.8D, 1.33D);
/*     */     }
/*     */     
/* 366 */     this.tasks.removeTask((EntityAIBase)this.field_175545_bm);
/*     */     
/* 368 */     if (!isTamed())
/*     */     {
/* 370 */       this.tasks.addTask(4, (EntityAIBase)this.field_175545_bm);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 376 */     p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
/*     */     
/* 378 */     if (this.worldObj.rand.nextInt(7) == 0)
/*     */     {
/* 380 */       for (int var3 = 0; var3 < 2; var3++) {
/*     */         
/* 382 */         EntityOcelot var4 = new EntityOcelot(this.worldObj);
/* 383 */         var4.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
/* 384 */         var4.setGrowingAge(-24000);
/* 385 */         this.worldObj.spawnEntityInWorld((Entity)var4);
/*     */       } 
/*     */     }
/*     */     
/* 389 */     return p_180482_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable p_90011_1_) {
/* 394 */     return func_180493_b(p_90011_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityOcelot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */