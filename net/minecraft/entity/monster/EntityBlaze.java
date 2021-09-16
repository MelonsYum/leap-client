/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntitySmallFireball;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityBlaze extends EntityMob {
/*  25 */   private float heightOffset = 0.5F;
/*     */   
/*     */   private int heightOffsetUpdateTime;
/*     */   
/*     */   private static final String __OBFID = "CL_00001682";
/*     */ 
/*     */   
/*     */   public EntityBlaze(World worldIn) {
/*  33 */     super(worldIn);
/*  34 */     this.isImmuneToFire = true;
/*  35 */     this.experienceValue = 10;
/*  36 */     this.tasks.addTask(4, new AIFireballAttack());
/*  37 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  38 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWander(this, 1.0D));
/*  39 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  40 */     this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  41 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget(this, true, new Class[0]));
/*  42 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  47 */     super.applyEntityAttributes();
/*  48 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
/*  49 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
/*  50 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(48.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  55 */     super.entityInit();
/*  56 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  64 */     return "mob.blaze.breathe";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  72 */     return "mob.blaze.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  80 */     return "mob.blaze.death";
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/*  85 */     return 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float p_70013_1_) {
/*  93 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 102 */     if (!this.onGround && this.motionY < 0.0D)
/*     */     {
/* 104 */       this.motionY *= 0.6D;
/*     */     }
/*     */     
/* 107 */     if (this.worldObj.isRemote) {
/*     */       
/* 109 */       if (this.rand.nextInt(24) == 0 && !isSlient())
/*     */       {
/* 111 */         this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
/*     */       }
/*     */       
/* 114 */       for (int var1 = 0; var1 < 2; var1++)
/*     */       {
/* 116 */         this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */     } 
/*     */     
/* 120 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/* 125 */     if (isWet())
/*     */     {
/* 127 */       attackEntityFrom(DamageSource.drown, 1.0F);
/*     */     }
/*     */     
/* 130 */     this.heightOffsetUpdateTime--;
/*     */     
/* 132 */     if (this.heightOffsetUpdateTime <= 0) {
/*     */       
/* 134 */       this.heightOffsetUpdateTime = 100;
/* 135 */       this.heightOffset = 0.5F + (float)this.rand.nextGaussian() * 3.0F;
/*     */     } 
/*     */     
/* 138 */     EntityLivingBase var1 = getAttackTarget();
/*     */     
/* 140 */     if (var1 != null && var1.posY + var1.getEyeHeight() > this.posY + getEyeHeight() + this.heightOffset) {
/*     */       
/* 142 */       this.motionY += (0.30000001192092896D - this.motionY) * 0.30000001192092896D;
/* 143 */       this.isAirBorne = true;
/*     */     } 
/*     */     
/* 146 */     super.updateAITasks();
/*     */   }
/*     */ 
/*     */   
/*     */   public void fall(float distance, float damageMultiplier) {}
/*     */   
/*     */   protected Item getDropItem() {
/* 153 */     return Items.blaze_rod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 161 */     return func_70845_n();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 169 */     if (p_70628_1_) {
/*     */       
/* 171 */       int var3 = this.rand.nextInt(2 + p_70628_2_);
/*     */       
/* 173 */       for (int var4 = 0; var4 < var3; var4++)
/*     */       {
/* 175 */         dropItem(Items.blaze_rod, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70845_n() {
/* 182 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70844_e(boolean p_70844_1_) {
/* 187 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 189 */     if (p_70844_1_) {
/*     */       
/* 191 */       var2 = (byte)(var2 | 0x1);
/*     */     }
/*     */     else {
/*     */       
/* 195 */       var2 = (byte)(var2 & 0xFFFFFFFE);
/*     */     } 
/*     */     
/* 198 */     this.dataWatcher.updateObject(16, Byte.valueOf(var2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 206 */     return true;
/*     */   }
/*     */   
/*     */   class AIFireballAttack
/*     */     extends EntityAIBase {
/* 211 */     private EntityBlaze field_179469_a = EntityBlaze.this;
/*     */     
/*     */     private int field_179467_b;
/*     */     private int field_179468_c;
/*     */     private static final String __OBFID = "CL_00002225";
/*     */     
/*     */     public AIFireballAttack() {
/* 218 */       setMutexBits(3);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 223 */       EntityLivingBase var1 = this.field_179469_a.getAttackTarget();
/* 224 */       return (var1 != null && var1.isEntityAlive());
/*     */     }
/*     */ 
/*     */     
/*     */     public void startExecuting() {
/* 229 */       this.field_179467_b = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void resetTask() {
/* 234 */       this.field_179469_a.func_70844_e(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 239 */       this.field_179468_c--;
/* 240 */       EntityLivingBase var1 = this.field_179469_a.getAttackTarget();
/* 241 */       double var2 = this.field_179469_a.getDistanceSqToEntity((Entity)var1);
/*     */       
/* 243 */       if (var2 < 4.0D) {
/*     */         
/* 245 */         if (this.field_179468_c <= 0) {
/*     */           
/* 247 */           this.field_179468_c = 20;
/* 248 */           this.field_179469_a.attackEntityAsMob((Entity)var1);
/*     */         } 
/*     */         
/* 251 */         this.field_179469_a.getMoveHelper().setMoveTo(var1.posX, var1.posY, var1.posZ, 1.0D);
/*     */       }
/* 253 */       else if (var2 < 256.0D) {
/*     */         
/* 255 */         double var4 = var1.posX - this.field_179469_a.posX;
/* 256 */         double var6 = (var1.getEntityBoundingBox()).minY + (var1.height / 2.0F) - this.field_179469_a.posY + (this.field_179469_a.height / 2.0F);
/* 257 */         double var8 = var1.posZ - this.field_179469_a.posZ;
/*     */         
/* 259 */         if (this.field_179468_c <= 0) {
/*     */           
/* 261 */           this.field_179467_b++;
/*     */           
/* 263 */           if (this.field_179467_b == 1) {
/*     */             
/* 265 */             this.field_179468_c = 60;
/* 266 */             this.field_179469_a.func_70844_e(true);
/*     */           }
/* 268 */           else if (this.field_179467_b <= 4) {
/*     */             
/* 270 */             this.field_179468_c = 6;
/*     */           }
/*     */           else {
/*     */             
/* 274 */             this.field_179468_c = 100;
/* 275 */             this.field_179467_b = 0;
/* 276 */             this.field_179469_a.func_70844_e(false);
/*     */           } 
/*     */           
/* 279 */           if (this.field_179467_b > 1) {
/*     */             
/* 281 */             float var10 = MathHelper.sqrt_float(MathHelper.sqrt_double(var2)) * 0.5F;
/* 282 */             this.field_179469_a.worldObj.playAuxSFXAtEntity(null, 1009, new BlockPos((int)this.field_179469_a.posX, (int)this.field_179469_a.posY, (int)this.field_179469_a.posZ), 0);
/*     */             
/* 284 */             for (int var11 = 0; var11 < 1; var11++) {
/*     */               
/* 286 */               EntitySmallFireball var12 = new EntitySmallFireball(this.field_179469_a.worldObj, (EntityLivingBase)this.field_179469_a, var4 + this.field_179469_a.getRNG().nextGaussian() * var10, var6, var8 + this.field_179469_a.getRNG().nextGaussian() * var10);
/* 287 */               var12.posY = this.field_179469_a.posY + (this.field_179469_a.height / 2.0F) + 0.5D;
/* 288 */               this.field_179469_a.worldObj.spawnEntityInWorld((Entity)var12);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 293 */         this.field_179469_a.getLookHelper().setLookPositionWithEntity((Entity)var1, 10.0F, 10.0F);
/*     */       }
/*     */       else {
/*     */         
/* 297 */         this.field_179469_a.getNavigator().clearPathEntity();
/* 298 */         this.field_179469_a.getMoveHelper().setMoveTo(var1.posX, var1.posY, var1.posZ, 1.0D);
/*     */       } 
/*     */       
/* 301 */       super.updateTask();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityBlaze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */