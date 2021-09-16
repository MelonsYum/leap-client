/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIFindEntityNearest;
/*     */ import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
/*     */ import net.minecraft.entity.ai.EntityMoveHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ public class EntitySlime
/*     */   extends EntityLiving
/*     */   implements IMob {
/*     */   public float squishAmount;
/*     */   public float squishFactor;
/*     */   public float prevSquishFactor;
/*     */   private boolean field_175452_bi;
/*     */   private static final String __OBFID = "CL_00001698";
/*     */   
/*     */   public EntitySlime(World worldIn) {
/*  38 */     super(worldIn);
/*  39 */     this.moveHelper = new SlimeMoveHelper();
/*  40 */     this.tasks.addTask(1, new AISlimeFloat());
/*  41 */     this.tasks.addTask(2, new AISlimeAttack());
/*  42 */     this.tasks.addTask(3, new AISlimeFaceRandom());
/*  43 */     this.tasks.addTask(5, new AISlimeHop());
/*  44 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIFindEntityNearestPlayer(this));
/*  45 */     this.targetTasks.addTask(3, (EntityAIBase)new EntityAIFindEntityNearest(this, EntityIronGolem.class));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  50 */     super.entityInit();
/*  51 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)1));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setSlimeSize(int p_70799_1_) {
/*  56 */     this.dataWatcher.updateObject(16, Byte.valueOf((byte)p_70799_1_));
/*  57 */     setSize(0.51000005F * p_70799_1_, 0.51000005F * p_70799_1_);
/*  58 */     setPosition(this.posX, this.posY, this.posZ);
/*  59 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((p_70799_1_ * p_70799_1_));
/*  60 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue((0.2F + 0.1F * p_70799_1_));
/*  61 */     setHealth(getMaxHealth());
/*  62 */     this.experienceValue = p_70799_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSlimeSize() {
/*  70 */     return this.dataWatcher.getWatchableObjectByte(16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  78 */     super.writeEntityToNBT(tagCompound);
/*  79 */     tagCompound.setInteger("Size", getSlimeSize() - 1);
/*  80 */     tagCompound.setBoolean("wasOnGround", this.field_175452_bi);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  88 */     super.readEntityFromNBT(tagCompund);
/*  89 */     int var2 = tagCompund.getInteger("Size");
/*     */     
/*  91 */     if (var2 < 0)
/*     */     {
/*  93 */       var2 = 0;
/*     */     }
/*     */     
/*  96 */     setSlimeSize(var2 + 1);
/*  97 */     this.field_175452_bi = tagCompund.getBoolean("wasOnGround");
/*     */   }
/*     */ 
/*     */   
/*     */   protected EnumParticleTypes func_180487_n() {
/* 102 */     return EnumParticleTypes.SLIME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getJumpSound() {
/* 110 */     return "mob.slime." + ((getSlimeSize() > 1) ? "big" : "small");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 118 */     if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL && getSlimeSize() > 0)
/*     */     {
/* 120 */       this.isDead = true;
/*     */     }
/*     */     
/* 123 */     this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
/* 124 */     this.prevSquishFactor = this.squishFactor;
/* 125 */     super.onUpdate();
/*     */     
/* 127 */     if (this.onGround && !this.field_175452_bi) {
/*     */       
/* 129 */       int var1 = getSlimeSize();
/*     */       
/* 131 */       for (int var2 = 0; var2 < var1 * 8; var2++) {
/*     */         
/* 133 */         float var3 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 134 */         float var4 = this.rand.nextFloat() * 0.5F + 0.5F;
/* 135 */         float var5 = MathHelper.sin(var3) * var1 * 0.5F * var4;
/* 136 */         float var6 = MathHelper.cos(var3) * var1 * 0.5F * var4;
/* 137 */         World var10000 = this.worldObj;
/* 138 */         EnumParticleTypes var10001 = func_180487_n();
/* 139 */         double var10002 = this.posX + var5;
/* 140 */         double var10004 = this.posZ + var6;
/* 141 */         var10000.spawnParticle(var10001, var10002, (getEntityBoundingBox()).minY, var10004, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       } 
/*     */       
/* 144 */       if (makesSoundOnLand())
/*     */       {
/* 146 */         playSound(getJumpSound(), getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
/*     */       }
/*     */       
/* 149 */       this.squishAmount = -0.5F;
/*     */     }
/* 151 */     else if (!this.onGround && this.field_175452_bi) {
/*     */       
/* 153 */       this.squishAmount = 1.0F;
/*     */     } 
/*     */     
/* 156 */     this.field_175452_bi = this.onGround;
/* 157 */     alterSquishAmount();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void alterSquishAmount() {
/* 162 */     this.squishAmount *= 0.6F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getJumpDelay() {
/* 170 */     return this.rand.nextInt(20) + 10;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntitySlime createInstance() {
/* 175 */     return new EntitySlime(this.worldObj);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145781_i(int p_145781_1_) {
/* 180 */     if (p_145781_1_ == 16) {
/*     */       
/* 182 */       int var2 = getSlimeSize();
/* 183 */       setSize(0.51000005F * var2, 0.51000005F * var2);
/* 184 */       this.rotationYaw = this.rotationYawHead;
/* 185 */       this.renderYawOffset = this.rotationYawHead;
/*     */       
/* 187 */       if (isInWater() && this.rand.nextInt(20) == 0)
/*     */       {
/* 189 */         resetHeight();
/*     */       }
/*     */     } 
/*     */     
/* 193 */     super.func_145781_i(p_145781_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 201 */     int var1 = getSlimeSize();
/*     */     
/* 203 */     if (!this.worldObj.isRemote && var1 > 1 && getHealth() <= 0.0F) {
/*     */       
/* 205 */       int var2 = 2 + this.rand.nextInt(3);
/*     */       
/* 207 */       for (int var3 = 0; var3 < var2; var3++) {
/*     */         
/* 209 */         float var4 = ((var3 % 2) - 0.5F) * var1 / 4.0F;
/* 210 */         float var5 = ((var3 / 2) - 0.5F) * var1 / 4.0F;
/* 211 */         EntitySlime var6 = createInstance();
/*     */         
/* 213 */         if (hasCustomName())
/*     */         {
/* 215 */           var6.setCustomNameTag(getCustomNameTag());
/*     */         }
/*     */         
/* 218 */         if (isNoDespawnRequired())
/*     */         {
/* 220 */           var6.enablePersistence();
/*     */         }
/*     */         
/* 223 */         var6.setSlimeSize(var1 / 2);
/* 224 */         var6.setLocationAndAngles(this.posX + var4, this.posY + 0.5D, this.posZ + var5, this.rand.nextFloat() * 360.0F, 0.0F);
/* 225 */         this.worldObj.spawnEntityInWorld((Entity)var6);
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     super.setDead();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyEntityCollision(Entity entityIn) {
/* 237 */     super.applyEntityCollision(entityIn);
/*     */     
/* 239 */     if (entityIn instanceof EntityIronGolem && canDamagePlayer())
/*     */     {
/* 241 */       func_175451_e((EntityLivingBase)entityIn);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCollideWithPlayer(EntityPlayer entityIn) {
/* 250 */     if (canDamagePlayer())
/*     */     {
/* 252 */       func_175451_e((EntityLivingBase)entityIn);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175451_e(EntityLivingBase p_175451_1_) {
/* 258 */     int var2 = getSlimeSize();
/*     */     
/* 260 */     if (canEntityBeSeen((Entity)p_175451_1_) && getDistanceSqToEntity((Entity)p_175451_1_) < 0.6D * var2 * 0.6D * var2 && p_175451_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), getAttackStrength())) {
/*     */       
/* 262 */       playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/* 263 */       func_174815_a((EntityLivingBase)this, (Entity)p_175451_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 269 */     return 0.625F * this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDamagePlayer() {
/* 277 */     return (getSlimeSize() > 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getAttackStrength() {
/* 285 */     return getSlimeSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 293 */     return "mob.slime." + ((getSlimeSize() > 1) ? "big" : "small");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 301 */     return "mob.slime." + ((getSlimeSize() > 1) ? "big" : "small");
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 306 */     return (getSlimeSize() == 1) ? Items.slime_ball : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 314 */     Chunk var1 = this.worldObj.getChunkFromBlockCoords(new BlockPos(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ)));
/*     */     
/* 316 */     if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT && this.rand.nextInt(4) != 1)
/*     */     {
/* 318 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 322 */     if (this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL) {
/*     */       
/* 324 */       BiomeGenBase var2 = this.worldObj.getBiomeGenForCoords(new BlockPos(MathHelper.floor_double(this.posX), 0, MathHelper.floor_double(this.posZ)));
/*     */       
/* 326 */       if (var2 == BiomeGenBase.swampland && this.posY > 50.0D && this.posY < 70.0D && this.rand.nextFloat() < 0.5F && this.rand.nextFloat() < this.worldObj.getCurrentMoonPhaseFactor() && this.worldObj.getLightFromNeighbors(new BlockPos((Entity)this)) <= this.rand.nextInt(8))
/*     */       {
/* 328 */         return super.getCanSpawnHere();
/*     */       }
/*     */       
/* 331 */       if (this.rand.nextInt(10) == 0 && var1.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0D)
/*     */       {
/* 333 */         return super.getCanSpawnHere();
/*     */       }
/*     */     } 
/*     */     
/* 337 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 346 */     return 0.4F * getSlimeSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVerticalFaceSpeed() {
/* 355 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean makesSoundOnJump() {
/* 363 */     return (getSlimeSize() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean makesSoundOnLand() {
/* 371 */     return (getSlimeSize() > 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void jump() {
/* 379 */     this.motionY = 0.41999998688697815D;
/* 380 */     this.isAirBorne = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 385 */     int var3 = this.rand.nextInt(3);
/*     */     
/* 387 */     if (var3 < 2 && this.rand.nextFloat() < 0.5F * p_180482_1_.func_180170_c())
/*     */     {
/* 389 */       var3++;
/*     */     }
/*     */     
/* 392 */     int var4 = 1 << var3;
/* 393 */     setSlimeSize(var4);
/* 394 */     return super.func_180482_a(p_180482_1_, p_180482_2_);
/*     */   }
/*     */   
/*     */   class AISlimeAttack
/*     */     extends EntityAIBase {
/* 399 */     private EntitySlime field_179466_a = EntitySlime.this;
/*     */     
/*     */     private int field_179465_b;
/*     */     private static final String __OBFID = "CL_00002202";
/*     */     
/*     */     public AISlimeAttack() {
/* 405 */       setMutexBits(2);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 410 */       EntityLivingBase var1 = this.field_179466_a.getAttackTarget();
/* 411 */       return (var1 == null) ? false : var1.isEntityAlive();
/*     */     }
/*     */ 
/*     */     
/*     */     public void startExecuting() {
/* 416 */       this.field_179465_b = 300;
/* 417 */       super.startExecuting();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean continueExecuting() {
/* 422 */       EntityLivingBase var1 = this.field_179466_a.getAttackTarget();
/* 423 */       return (var1 == null) ? false : (!var1.isEntityAlive() ? false : ((--this.field_179465_b > 0)));
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 428 */       this.field_179466_a.faceEntity((Entity)this.field_179466_a.getAttackTarget(), 10.0F, 10.0F);
/* 429 */       ((EntitySlime.SlimeMoveHelper)this.field_179466_a.getMoveHelper()).func_179920_a(this.field_179466_a.rotationYaw, this.field_179466_a.canDamagePlayer());
/*     */     }
/*     */   }
/*     */   
/*     */   class AISlimeFaceRandom
/*     */     extends EntityAIBase {
/* 435 */     private EntitySlime field_179461_a = EntitySlime.this;
/*     */     
/*     */     private float field_179459_b;
/*     */     private int field_179460_c;
/*     */     private static final String __OBFID = "CL_00002198";
/*     */     
/*     */     public AISlimeFaceRandom() {
/* 442 */       setMutexBits(2);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 447 */       return (this.field_179461_a.getAttackTarget() == null && (this.field_179461_a.onGround || this.field_179461_a.isInWater() || this.field_179461_a.func_180799_ab()));
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 452 */       if (--this.field_179460_c <= 0) {
/*     */         
/* 454 */         this.field_179460_c = 40 + this.field_179461_a.getRNG().nextInt(60);
/* 455 */         this.field_179459_b = this.field_179461_a.getRNG().nextInt(360);
/*     */       } 
/*     */       
/* 458 */       ((EntitySlime.SlimeMoveHelper)this.field_179461_a.getMoveHelper()).func_179920_a(this.field_179459_b, false);
/*     */     }
/*     */   }
/*     */   
/*     */   class AISlimeFloat
/*     */     extends EntityAIBase {
/* 464 */     private EntitySlime field_179457_a = EntitySlime.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002201";
/*     */     
/*     */     public AISlimeFloat() {
/* 469 */       setMutexBits(5);
/* 470 */       ((PathNavigateGround)EntitySlime.this.getNavigator()).func_179693_d(true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 475 */       return !(!this.field_179457_a.isInWater() && !this.field_179457_a.func_180799_ab());
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 480 */       if (this.field_179457_a.getRNG().nextFloat() < 0.8F)
/*     */       {
/* 482 */         this.field_179457_a.getJumpHelper().setJumping();
/*     */       }
/*     */       
/* 485 */       ((EntitySlime.SlimeMoveHelper)this.field_179457_a.getMoveHelper()).func_179921_a(1.2D);
/*     */     }
/*     */   }
/*     */   
/*     */   class AISlimeHop
/*     */     extends EntityAIBase {
/* 491 */     private EntitySlime field_179458_a = EntitySlime.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002200";
/*     */     
/*     */     public AISlimeHop() {
/* 496 */       setMutexBits(5);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 501 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 506 */       ((EntitySlime.SlimeMoveHelper)this.field_179458_a.getMoveHelper()).func_179921_a(1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   class SlimeMoveHelper
/*     */     extends EntityMoveHelper {
/*     */     private float field_179922_g;
/*     */     private int field_179924_h;
/* 514 */     private EntitySlime field_179925_i = EntitySlime.this;
/*     */     
/*     */     private boolean field_179923_j;
/*     */     private static final String __OBFID = "CL_00002199";
/*     */     
/*     */     public SlimeMoveHelper() {
/* 520 */       super(EntitySlime.this);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_179920_a(float p_179920_1_, boolean p_179920_2_) {
/* 525 */       this.field_179922_g = p_179920_1_;
/* 526 */       this.field_179923_j = p_179920_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_179921_a(double p_179921_1_) {
/* 531 */       this.speed = p_179921_1_;
/* 532 */       this.update = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void onUpdateMoveHelper() {
/* 537 */       this.entity.rotationYaw = limitAngle(this.entity.rotationYaw, this.field_179922_g, 30.0F);
/* 538 */       this.entity.rotationYawHead = this.entity.rotationYaw;
/* 539 */       this.entity.renderYawOffset = this.entity.rotationYaw;
/*     */       
/* 541 */       if (!this.update) {
/*     */         
/* 543 */         this.entity.setMoveForward(0.0F);
/*     */       }
/*     */       else {
/*     */         
/* 547 */         this.update = false;
/*     */         
/* 549 */         if (this.entity.onGround) {
/*     */           
/* 551 */           this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
/*     */           
/* 553 */           if (this.field_179924_h-- <= 0)
/*     */           {
/* 555 */             this.field_179924_h = this.field_179925_i.getJumpDelay();
/*     */             
/* 557 */             if (this.field_179923_j)
/*     */             {
/* 559 */               this.field_179924_h /= 3;
/*     */             }
/*     */             
/* 562 */             this.field_179925_i.getJumpHelper().setJumping();
/*     */             
/* 564 */             if (this.field_179925_i.makesSoundOnJump())
/*     */             {
/* 566 */               this.field_179925_i.playSound(this.field_179925_i.getJumpSound(), this.field_179925_i.getSoundVolume(), ((this.field_179925_i.getRNG().nextFloat() - this.field_179925_i.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F);
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 571 */             this.field_179925_i.moveStrafing = this.field_179925_i.moveForward = 0.0F;
/* 572 */             this.entity.setAIMoveSpeed(0.0F);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 577 */           this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntitySlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */