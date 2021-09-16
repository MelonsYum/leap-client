/*     */ package net.minecraft.entity.monster;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityFlying;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
/*     */ import net.minecraft.entity.ai.EntityMoveHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityLargeFireball;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityGhast extends EntityFlying implements IMob {
/*  27 */   private int explosionStrength = 1;
/*     */   
/*     */   private static final String __OBFID = "CL_00001689";
/*     */   
/*     */   public EntityGhast(World worldIn) {
/*  32 */     super(worldIn);
/*  33 */     setSize(4.0F, 4.0F);
/*  34 */     this.isImmuneToFire = true;
/*  35 */     this.experienceValue = 5;
/*  36 */     this.moveHelper = new GhastMoveHelper();
/*  37 */     this.tasks.addTask(5, new AIRandomFly());
/*  38 */     this.tasks.addTask(7, new AILookAround());
/*  39 */     this.tasks.addTask(7, new AIFireballAttack());
/*  40 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIFindEntityNearestPlayer((EntityLiving)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_110182_bF() {
/*  45 */     return (this.dataWatcher.getWatchableObjectByte(16) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175454_a(boolean p_175454_1_) {
/*  50 */     this.dataWatcher.updateObject(16, Byte.valueOf((byte)(p_175454_1_ ? 1 : 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_175453_cd() {
/*  55 */     return this.explosionStrength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  63 */     super.onUpdate();
/*     */     
/*  65 */     if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL)
/*     */     {
/*  67 */       setDead();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  76 */     if (func_180431_b(source))
/*     */     {
/*  78 */       return false;
/*     */     }
/*  80 */     if ("fireball".equals(source.getDamageType()) && source.getEntity() instanceof EntityPlayer) {
/*     */       
/*  82 */       super.attackEntityFrom(source, 1000.0F);
/*  83 */       ((EntityPlayer)source.getEntity()).triggerAchievement((StatBase)AchievementList.ghast);
/*  84 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  88 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  94 */     super.entityInit();
/*  95 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 100 */     super.applyEntityAttributes();
/* 101 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
/* 102 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(100.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 110 */     return "mob.ghast.moan";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 118 */     return "mob.ghast.scream";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 126 */     return "mob.ghast.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 131 */     return Items.gunpowder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 139 */     int var3 = this.rand.nextInt(2) + this.rand.nextInt(1 + p_70628_2_);
/*     */     
/*     */     int var4;
/* 142 */     for (var4 = 0; var4 < var3; var4++)
/*     */     {
/* 144 */       dropItem(Items.ghast_tear, 1);
/*     */     }
/*     */     
/* 147 */     var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + p_70628_2_);
/*     */     
/* 149 */     for (var4 = 0; var4 < var3; var4++)
/*     */     {
/* 151 */       dropItem(Items.gunpowder, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 160 */     return 10.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 168 */     return (this.rand.nextInt(20) == 0 && super.getCanSpawnHere() && this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxSpawnedInChunk() {
/* 176 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 184 */     super.writeEntityToNBT(tagCompound);
/* 185 */     tagCompound.setInteger("ExplosionPower", this.explosionStrength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 193 */     super.readEntityFromNBT(tagCompund);
/*     */     
/* 195 */     if (tagCompund.hasKey("ExplosionPower", 99))
/*     */     {
/* 197 */       this.explosionStrength = tagCompund.getInteger("ExplosionPower");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 203 */     return 2.6F;
/*     */   }
/*     */   
/*     */   class AIFireballAttack
/*     */     extends EntityAIBase {
/* 208 */     private EntityGhast field_179470_b = EntityGhast.this;
/*     */     
/*     */     public int field_179471_a;
/*     */     private static final String __OBFID = "CL_00002215";
/*     */     
/*     */     public boolean shouldExecute() {
/* 214 */       return (this.field_179470_b.getAttackTarget() != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public void startExecuting() {
/* 219 */       this.field_179471_a = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void resetTask() {
/* 224 */       this.field_179470_b.func_175454_a(false);
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 229 */       EntityLivingBase var1 = this.field_179470_b.getAttackTarget();
/* 230 */       double var2 = 64.0D;
/*     */       
/* 232 */       if (var1.getDistanceSqToEntity((Entity)this.field_179470_b) < var2 * var2 && this.field_179470_b.canEntityBeSeen((Entity)var1)) {
/*     */         
/* 234 */         World var4 = this.field_179470_b.worldObj;
/* 235 */         this.field_179471_a++;
/*     */         
/* 237 */         if (this.field_179471_a == 10)
/*     */         {
/* 239 */           var4.playAuxSFXAtEntity(null, 1007, new BlockPos((Entity)this.field_179470_b), 0);
/*     */         }
/*     */         
/* 242 */         if (this.field_179471_a == 20)
/*     */         {
/* 244 */           double var5 = 4.0D;
/* 245 */           Vec3 var7 = this.field_179470_b.getLook(1.0F);
/* 246 */           double var8 = var1.posX - this.field_179470_b.posX + var7.xCoord * var5;
/* 247 */           double var10 = (var1.getEntityBoundingBox()).minY + (var1.height / 2.0F) - 0.5D + this.field_179470_b.posY + (this.field_179470_b.height / 2.0F);
/* 248 */           double var12 = var1.posZ - this.field_179470_b.posZ + var7.zCoord * var5;
/* 249 */           var4.playAuxSFXAtEntity(null, 1008, new BlockPos((Entity)this.field_179470_b), 0);
/* 250 */           EntityLargeFireball var14 = new EntityLargeFireball(var4, (EntityLivingBase)this.field_179470_b, var8, var10, var12);
/* 251 */           var14.field_92057_e = this.field_179470_b.func_175453_cd();
/* 252 */           var14.posX = this.field_179470_b.posX + var7.xCoord * var5;
/* 253 */           var14.posY = this.field_179470_b.posY + (this.field_179470_b.height / 2.0F) + 0.5D;
/* 254 */           var14.posZ = this.field_179470_b.posZ + var7.zCoord * var5;
/* 255 */           var4.spawnEntityInWorld((Entity)var14);
/* 256 */           this.field_179471_a = -40;
/*     */         }
/*     */       
/* 259 */       } else if (this.field_179471_a > 0) {
/*     */         
/* 261 */         this.field_179471_a--;
/*     */       } 
/*     */       
/* 264 */       this.field_179470_b.func_175454_a((this.field_179471_a > 10));
/*     */     }
/*     */   }
/*     */   
/*     */   class AILookAround
/*     */     extends EntityAIBase {
/* 270 */     private EntityGhast field_179472_a = EntityGhast.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002217";
/*     */     
/*     */     public AILookAround() {
/* 275 */       setMutexBits(2);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 280 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 285 */       if (this.field_179472_a.getAttackTarget() == null) {
/*     */         
/* 287 */         this.field_179472_a.renderYawOffset = this.field_179472_a.rotationYaw = -((float)Math.atan2(this.field_179472_a.motionX, this.field_179472_a.motionZ)) * 180.0F / 3.1415927F;
/*     */       }
/*     */       else {
/*     */         
/* 291 */         EntityLivingBase var1 = this.field_179472_a.getAttackTarget();
/* 292 */         double var2 = 64.0D;
/*     */         
/* 294 */         if (var1.getDistanceSqToEntity((Entity)this.field_179472_a) < var2 * var2) {
/*     */           
/* 296 */           double var4 = var1.posX - this.field_179472_a.posX;
/* 297 */           double var6 = var1.posZ - this.field_179472_a.posZ;
/* 298 */           this.field_179472_a.renderYawOffset = this.field_179472_a.rotationYaw = -((float)Math.atan2(var4, var6)) * 180.0F / 3.1415927F;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class AIRandomFly
/*     */     extends EntityAIBase {
/* 306 */     private EntityGhast field_179454_a = EntityGhast.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002214";
/*     */     
/*     */     public AIRandomFly() {
/* 311 */       setMutexBits(1);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 316 */       EntityMoveHelper var1 = this.field_179454_a.getMoveHelper();
/*     */       
/* 318 */       if (!var1.isUpdating())
/*     */       {
/* 320 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 324 */       double var2 = var1.func_179917_d() - this.field_179454_a.posX;
/* 325 */       double var4 = var1.func_179919_e() - this.field_179454_a.posY;
/* 326 */       double var6 = var1.func_179918_f() - this.field_179454_a.posZ;
/* 327 */       double var8 = var2 * var2 + var4 * var4 + var6 * var6;
/* 328 */       return !(var8 >= 1.0D && var8 <= 3600.0D);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean continueExecuting() {
/* 334 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void startExecuting() {
/* 339 */       Random var1 = this.field_179454_a.getRNG();
/* 340 */       double var2 = this.field_179454_a.posX + ((var1.nextFloat() * 2.0F - 1.0F) * 16.0F);
/* 341 */       double var4 = this.field_179454_a.posY + ((var1.nextFloat() * 2.0F - 1.0F) * 16.0F);
/* 342 */       double var6 = this.field_179454_a.posZ + ((var1.nextFloat() * 2.0F - 1.0F) * 16.0F);
/* 343 */       this.field_179454_a.getMoveHelper().setMoveTo(var2, var4, var6, 1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   class GhastMoveHelper
/*     */     extends EntityMoveHelper {
/* 349 */     private EntityGhast field_179927_g = EntityGhast.this;
/*     */     
/*     */     private int field_179928_h;
/*     */     private static final String __OBFID = "CL_00002216";
/*     */     
/*     */     public GhastMoveHelper() {
/* 355 */       super((EntityLiving)EntityGhast.this);
/*     */     }
/*     */ 
/*     */     
/*     */     public void onUpdateMoveHelper() {
/* 360 */       if (this.update) {
/*     */         
/* 362 */         double var1 = this.posX - this.field_179927_g.posX;
/* 363 */         double var3 = this.posY - this.field_179927_g.posY;
/* 364 */         double var5 = this.posZ - this.field_179927_g.posZ;
/* 365 */         double var7 = var1 * var1 + var3 * var3 + var5 * var5;
/*     */         
/* 367 */         if (this.field_179928_h-- <= 0) {
/*     */           
/* 369 */           this.field_179928_h += this.field_179927_g.getRNG().nextInt(5) + 2;
/* 370 */           var7 = MathHelper.sqrt_double(var7);
/*     */           
/* 372 */           if (func_179926_b(this.posX, this.posY, this.posZ, var7)) {
/*     */             
/* 374 */             this.field_179927_g.motionX += var1 / var7 * 0.1D;
/* 375 */             this.field_179927_g.motionY += var3 / var7 * 0.1D;
/* 376 */             this.field_179927_g.motionZ += var5 / var7 * 0.1D;
/*     */           }
/*     */           else {
/*     */             
/* 380 */             this.update = false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_179926_b(double p_179926_1_, double p_179926_3_, double p_179926_5_, double p_179926_7_) {
/* 388 */       double var9 = (p_179926_1_ - this.field_179927_g.posX) / p_179926_7_;
/* 389 */       double var11 = (p_179926_3_ - this.field_179927_g.posY) / p_179926_7_;
/* 390 */       double var13 = (p_179926_5_ - this.field_179927_g.posZ) / p_179926_7_;
/* 391 */       AxisAlignedBB var15 = this.field_179927_g.getEntityBoundingBox();
/*     */       
/* 393 */       for (int var16 = 1; var16 < p_179926_7_; var16++) {
/*     */         
/* 395 */         var15 = var15.offset(var9, var11, var13);
/*     */         
/* 397 */         if (!this.field_179927_g.worldObj.getCollidingBoundingBoxes((Entity)this.field_179927_g, var15).isEmpty())
/*     */         {
/* 399 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 403 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityGhast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */