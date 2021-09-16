/*     */ package net.minecraft.entity.boss;
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityWitherSkull;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityWither extends EntityMob implements IBossDisplayData, IRangedAttackMob {
/*  43 */   private float[] field_82220_d = new float[2];
/*  44 */   private float[] field_82221_e = new float[2];
/*  45 */   private float[] field_82217_f = new float[2];
/*  46 */   private float[] field_82218_g = new float[2];
/*  47 */   private int[] field_82223_h = new int[2];
/*  48 */   private int[] field_82224_i = new int[2];
/*     */   
/*     */   private int field_82222_j;
/*     */   
/*  52 */   private static final Predicate attackEntitySelector = new Predicate()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001662";
/*     */       
/*     */       public boolean func_180027_a(Entity p_180027_1_) {
/*  57 */         return (p_180027_1_ instanceof EntityLivingBase && ((EntityLivingBase)p_180027_1_).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD);
/*     */       }
/*     */       
/*     */       public boolean apply(Object p_apply_1_) {
/*  61 */         return func_180027_a((Entity)p_apply_1_);
/*     */       }
/*     */     };
/*     */   
/*     */   private static final String __OBFID = "CL_00001661";
/*     */   
/*     */   public EntityWither(World worldIn) {
/*  68 */     super(worldIn);
/*  69 */     setHealth(getMaxHealth());
/*  70 */     setSize(0.9F, 3.5F);
/*  71 */     this.isImmuneToFire = true;
/*  72 */     ((PathNavigateGround)getNavigator()).func_179693_d(true);
/*  73 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  74 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
/*  75 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
/*  76 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  77 */     this.tasks.addTask(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  78 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
/*  79 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLiving.class, 0, false, false, attackEntitySelector));
/*  80 */     this.experienceValue = 50;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  85 */     super.entityInit();
/*  86 */     this.dataWatcher.addObject(17, new Integer(0));
/*  87 */     this.dataWatcher.addObject(18, new Integer(0));
/*  88 */     this.dataWatcher.addObject(19, new Integer(0));
/*  89 */     this.dataWatcher.addObject(20, new Integer(0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  97 */     super.writeEntityToNBT(tagCompound);
/*  98 */     tagCompound.setInteger("Invul", getInvulTime());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 106 */     super.readEntityFromNBT(tagCompund);
/* 107 */     setInvulTime(tagCompund.getInteger("Invul"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 115 */     return "mob.wither.idle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 123 */     return "mob.wither.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 131 */     return "mob.wither.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 140 */     this.motionY *= 0.6000000238418579D;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     if (!this.worldObj.isRemote && getWatchedTargetId(0) > 0) {
/*     */       
/* 147 */       Entity var1 = this.worldObj.getEntityByID(getWatchedTargetId(0));
/*     */       
/* 149 */       if (var1 != null) {
/*     */         
/* 151 */         if (this.posY < var1.posY || (!isArmored() && this.posY < var1.posY + 5.0D)) {
/*     */           
/* 153 */           if (this.motionY < 0.0D)
/*     */           {
/* 155 */             this.motionY = 0.0D;
/*     */           }
/*     */           
/* 158 */           this.motionY += (0.5D - this.motionY) * 0.6000000238418579D;
/*     */         } 
/*     */         
/* 161 */         double var2 = var1.posX - this.posX;
/* 162 */         double var4 = var1.posZ - this.posZ;
/* 163 */         double var6 = var2 * var2 + var4 * var4;
/*     */         
/* 165 */         if (var6 > 9.0D) {
/*     */           
/* 167 */           double var8 = MathHelper.sqrt_double(var6);
/* 168 */           this.motionX += (var2 / var8 * 0.5D - this.motionX) * 0.6000000238418579D;
/* 169 */           this.motionZ += (var4 / var8 * 0.5D - this.motionZ) * 0.6000000238418579D;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 174 */     if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05000000074505806D)
/*     */     {
/* 176 */       this.rotationYaw = (float)Math.atan2(this.motionZ, this.motionX) * 57.295776F - 90.0F;
/*     */     }
/*     */     
/* 179 */     super.onLivingUpdate();
/*     */     
/*     */     int var20;
/* 182 */     for (var20 = 0; var20 < 2; var20++) {
/*     */       
/* 184 */       this.field_82218_g[var20] = this.field_82221_e[var20];
/* 185 */       this.field_82217_f[var20] = this.field_82220_d[var20];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 190 */     for (var20 = 0; var20 < 2; var20++) {
/*     */       
/* 192 */       int i = getWatchedTargetId(var20 + 1);
/* 193 */       Entity var3 = null;
/*     */       
/* 195 */       if (i > 0)
/*     */       {
/* 197 */         var3 = this.worldObj.getEntityByID(i);
/*     */       }
/*     */       
/* 200 */       if (var3 != null) {
/*     */         
/* 202 */         double var4 = func_82214_u(var20 + 1);
/* 203 */         double var6 = func_82208_v(var20 + 1);
/* 204 */         double var8 = func_82213_w(var20 + 1);
/* 205 */         double var10 = var3.posX - var4;
/* 206 */         double var12 = var3.posY + var3.getEyeHeight() - var6;
/* 207 */         double var14 = var3.posZ - var8;
/* 208 */         double var16 = MathHelper.sqrt_double(var10 * var10 + var14 * var14);
/* 209 */         float var18 = (float)(Math.atan2(var14, var10) * 180.0D / Math.PI) - 90.0F;
/* 210 */         float var19 = (float)-(Math.atan2(var12, var16) * 180.0D / Math.PI);
/* 211 */         this.field_82220_d[var20] = func_82204_b(this.field_82220_d[var20], var19, 40.0F);
/* 212 */         this.field_82221_e[var20] = func_82204_b(this.field_82221_e[var20], var18, 10.0F);
/*     */       }
/*     */       else {
/*     */         
/* 216 */         this.field_82221_e[var20] = func_82204_b(this.field_82221_e[var20], this.renderYawOffset, 10.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 220 */     boolean var21 = isArmored();
/*     */     int var22;
/* 222 */     for (var22 = 0; var22 < 3; var22++) {
/*     */       
/* 224 */       double var23 = func_82214_u(var22);
/* 225 */       double var5 = func_82208_v(var22);
/* 226 */       double var7 = func_82213_w(var22);
/* 227 */       this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var23 + this.rand.nextGaussian() * 0.30000001192092896D, var5 + this.rand.nextGaussian() * 0.30000001192092896D, var7 + this.rand.nextGaussian() * 0.30000001192092896D, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       
/* 229 */       if (var21 && this.worldObj.rand.nextInt(4) == 0)
/*     */       {
/* 231 */         this.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, var23 + this.rand.nextGaussian() * 0.30000001192092896D, var5 + this.rand.nextGaussian() * 0.30000001192092896D, var7 + this.rand.nextGaussian() * 0.30000001192092896D, 0.699999988079071D, 0.699999988079071D, 0.5D, new int[0]);
/*     */       }
/*     */     } 
/*     */     
/* 235 */     if (getInvulTime() > 0)
/*     */     {
/* 237 */       for (var22 = 0; var22 < 3; var22++)
/*     */       {
/* 239 */         this.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + this.rand.nextGaussian() * 1.0D, this.posY + (this.rand.nextFloat() * 3.3F), this.posZ + this.rand.nextGaussian() * 1.0D, 0.699999988079071D, 0.699999988079071D, 0.8999999761581421D, new int[0]);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/* 248 */     if (getInvulTime() > 0) {
/*     */       
/* 250 */       int var1 = getInvulTime() - 1;
/*     */       
/* 252 */       if (var1 <= 0) {
/*     */         
/* 254 */         this.worldObj.newExplosion((Entity)this, this.posX, this.posY + getEyeHeight(), this.posZ, 7.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
/* 255 */         this.worldObj.func_175669_a(1013, new BlockPos((Entity)this), 0);
/*     */       } 
/*     */       
/* 258 */       setInvulTime(var1);
/*     */       
/* 260 */       if (this.ticksExisted % 10 == 0)
/*     */       {
/* 262 */         heal(10.0F);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 267 */       super.updateAITasks();
/*     */       
/*     */       int var1;
/* 270 */       for (var1 = 1; var1 < 3; var1++) {
/*     */         
/* 272 */         if (this.ticksExisted >= this.field_82223_h[var1 - 1]) {
/*     */           
/* 274 */           this.field_82223_h[var1 - 1] = this.ticksExisted + 10 + this.rand.nextInt(10);
/*     */           
/* 276 */           if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL || this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
/*     */             
/* 278 */             int var10001 = var1 - 1;
/* 279 */             int var10003 = this.field_82224_i[var1 - 1];
/* 280 */             this.field_82224_i[var10001] = this.field_82224_i[var1 - 1] + 1;
/*     */             
/* 282 */             if (var10003 > 15) {
/*     */               
/* 284 */               float var2 = 10.0F;
/* 285 */               float var3 = 5.0F;
/* 286 */               double var4 = MathHelper.getRandomDoubleInRange(this.rand, this.posX - var2, this.posX + var2);
/* 287 */               double var6 = MathHelper.getRandomDoubleInRange(this.rand, this.posY - var3, this.posY + var3);
/* 288 */               double var8 = MathHelper.getRandomDoubleInRange(this.rand, this.posZ - var2, this.posZ + var2);
/* 289 */               launchWitherSkullToCoords(var1 + 1, var4, var6, var8, true);
/* 290 */               this.field_82224_i[var1 - 1] = 0;
/*     */             } 
/*     */           } 
/*     */           
/* 294 */           int var12 = getWatchedTargetId(var1);
/*     */           
/* 296 */           if (var12 > 0) {
/*     */             
/* 298 */             Entity var14 = this.worldObj.getEntityByID(var12);
/*     */             
/* 300 */             if (var14 != null && var14.isEntityAlive() && getDistanceSqToEntity(var14) <= 900.0D && canEntityBeSeen(var14))
/*     */             {
/* 302 */               launchWitherSkullToEntity(var1 + 1, (EntityLivingBase)var14);
/* 303 */               this.field_82223_h[var1 - 1] = this.ticksExisted + 40 + this.rand.nextInt(20);
/* 304 */               this.field_82224_i[var1 - 1] = 0;
/*     */             }
/*     */             else
/*     */             {
/* 308 */               func_82211_c(var1, 0);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 313 */             List<EntityLivingBase> var13 = this.worldObj.func_175647_a(EntityLivingBase.class, getEntityBoundingBox().expand(20.0D, 8.0D, 20.0D), Predicates.and(attackEntitySelector, IEntitySelector.field_180132_d));
/*     */             
/* 315 */             for (int var16 = 0; var16 < 10 && !var13.isEmpty(); var16++) {
/*     */               
/* 317 */               EntityLivingBase var5 = var13.get(this.rand.nextInt(var13.size()));
/*     */               
/* 319 */               if (var5 != this && var5.isEntityAlive() && canEntityBeSeen((Entity)var5)) {
/*     */                 
/* 321 */                 if (var5 instanceof EntityPlayer) {
/*     */                   
/* 323 */                   if (!((EntityPlayer)var5).capabilities.disableDamage)
/*     */                   {
/* 325 */                     func_82211_c(var1, var5.getEntityId());
/*     */                   }
/*     */                   
/*     */                   break;
/*     */                 } 
/* 330 */                 func_82211_c(var1, var5.getEntityId());
/*     */ 
/*     */                 
/*     */                 break;
/*     */               } 
/*     */               
/* 336 */               var13.remove(var5);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 342 */       if (getAttackTarget() != null) {
/*     */         
/* 344 */         func_82211_c(0, getAttackTarget().getEntityId());
/*     */       }
/*     */       else {
/*     */         
/* 348 */         func_82211_c(0, 0);
/*     */       } 
/*     */       
/* 351 */       if (this.field_82222_j > 0) {
/*     */         
/* 353 */         this.field_82222_j--;
/*     */         
/* 355 */         if (this.field_82222_j == 0 && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*     */           
/* 357 */           var1 = MathHelper.floor_double(this.posY);
/* 358 */           int var12 = MathHelper.floor_double(this.posX);
/* 359 */           int var15 = MathHelper.floor_double(this.posZ);
/* 360 */           boolean var17 = false;
/*     */           
/* 362 */           for (int var18 = -1; var18 <= 1; var18++) {
/*     */             
/* 364 */             for (int var19 = -1; var19 <= 1; var19++) {
/*     */               
/* 366 */               for (int var7 = 0; var7 <= 3; var7++) {
/*     */                 
/* 368 */                 int var20 = var12 + var18;
/* 369 */                 int var9 = var1 + var7;
/* 370 */                 int var10 = var15 + var19;
/* 371 */                 Block var11 = this.worldObj.getBlockState(new BlockPos(var20, var9, var10)).getBlock();
/*     */                 
/* 373 */                 if (var11.getMaterial() != Material.air && var11 != Blocks.bedrock && var11 != Blocks.end_portal && var11 != Blocks.end_portal_frame && var11 != Blocks.command_block && var11 != Blocks.barrier)
/*     */                 {
/* 375 */                   var17 = !(!this.worldObj.destroyBlock(new BlockPos(var20, var9, var10), true) && !var17);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 381 */           if (var17)
/*     */           {
/* 383 */             this.worldObj.playAuxSFXAtEntity(null, 1012, new BlockPos((Entity)this), 0);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 388 */       if (this.ticksExisted % 20 == 0)
/*     */       {
/* 390 */         heal(1.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82206_m() {
/* 397 */     setInvulTime(220);
/* 398 */     setHealth(getMaxHealth() / 3.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInWeb() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalArmorValue() {
/* 411 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   private double func_82214_u(int p_82214_1_) {
/* 416 */     if (p_82214_1_ <= 0)
/*     */     {
/* 418 */       return this.posX;
/*     */     }
/*     */ 
/*     */     
/* 422 */     float var2 = (this.renderYawOffset + (180 * (p_82214_1_ - 1))) / 180.0F * 3.1415927F;
/* 423 */     float var3 = MathHelper.cos(var2);
/* 424 */     return this.posX + var3 * 1.3D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private double func_82208_v(int p_82208_1_) {
/* 430 */     return (p_82208_1_ <= 0) ? (this.posY + 3.0D) : (this.posY + 2.2D);
/*     */   }
/*     */ 
/*     */   
/*     */   private double func_82213_w(int p_82213_1_) {
/* 435 */     if (p_82213_1_ <= 0)
/*     */     {
/* 437 */       return this.posZ;
/*     */     }
/*     */ 
/*     */     
/* 441 */     float var2 = (this.renderYawOffset + (180 * (p_82213_1_ - 1))) / 180.0F * 3.1415927F;
/* 442 */     float var3 = MathHelper.sin(var2);
/* 443 */     return this.posZ + var3 * 1.3D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float func_82204_b(float p_82204_1_, float p_82204_2_, float p_82204_3_) {
/* 449 */     float var4 = MathHelper.wrapAngleTo180_float(p_82204_2_ - p_82204_1_);
/*     */     
/* 451 */     if (var4 > p_82204_3_)
/*     */     {
/* 453 */       var4 = p_82204_3_;
/*     */     }
/*     */     
/* 456 */     if (var4 < -p_82204_3_)
/*     */     {
/* 458 */       var4 = -p_82204_3_;
/*     */     }
/*     */     
/* 461 */     return p_82204_1_ + var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private void launchWitherSkullToEntity(int p_82216_1_, EntityLivingBase p_82216_2_) {
/* 466 */     launchWitherSkullToCoords(p_82216_1_, p_82216_2_.posX, p_82216_2_.posY + p_82216_2_.getEyeHeight() * 0.5D, p_82216_2_.posZ, (p_82216_1_ == 0 && this.rand.nextFloat() < 0.001F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void launchWitherSkullToCoords(int p_82209_1_, double p_82209_2_, double p_82209_4_, double p_82209_6_, boolean p_82209_8_) {
/* 474 */     this.worldObj.playAuxSFXAtEntity(null, 1014, new BlockPos((Entity)this), 0);
/* 475 */     double var9 = func_82214_u(p_82209_1_);
/* 476 */     double var11 = func_82208_v(p_82209_1_);
/* 477 */     double var13 = func_82213_w(p_82209_1_);
/* 478 */     double var15 = p_82209_2_ - var9;
/* 479 */     double var17 = p_82209_4_ - var11;
/* 480 */     double var19 = p_82209_6_ - var13;
/* 481 */     EntityWitherSkull var21 = new EntityWitherSkull(this.worldObj, (EntityLivingBase)this, var15, var17, var19);
/*     */     
/* 483 */     if (p_82209_8_)
/*     */     {
/* 485 */       var21.setInvulnerable(true);
/*     */     }
/*     */     
/* 488 */     var21.posY = var11;
/* 489 */     var21.posX = var9;
/* 490 */     var21.posZ = var13;
/* 491 */     this.worldObj.spawnEntityInWorld((Entity)var21);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
/* 499 */     launchWitherSkullToEntity(0, p_82196_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 507 */     if (func_180431_b(source))
/*     */     {
/* 509 */       return false;
/*     */     }
/* 511 */     if (source != DamageSource.drown && !(source.getEntity() instanceof EntityWither)) {
/*     */       
/* 513 */       if (getInvulTime() > 0 && source != DamageSource.outOfWorld)
/*     */       {
/* 515 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 521 */       if (isArmored()) {
/*     */         
/* 523 */         Entity entity = source.getSourceOfDamage();
/*     */         
/* 525 */         if (entity instanceof net.minecraft.entity.projectile.EntityArrow)
/*     */         {
/* 527 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 531 */       Entity var3 = source.getEntity();
/*     */       
/* 533 */       if (var3 != null && !(var3 instanceof EntityPlayer) && var3 instanceof EntityLivingBase && ((EntityLivingBase)var3).getCreatureAttribute() == getCreatureAttribute())
/*     */       {
/* 535 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 539 */       if (this.field_82222_j <= 0)
/*     */       {
/* 541 */         this.field_82222_j = 20;
/*     */       }
/*     */       
/* 544 */       for (int var4 = 0; var4 < this.field_82224_i.length; var4++)
/*     */       {
/* 546 */         this.field_82224_i[var4] = this.field_82224_i[var4] + 3;
/*     */       }
/*     */       
/* 549 */       return super.attackEntityFrom(source, amount);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 555 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 564 */     EntityItem var3 = dropItem(Items.nether_star, 1);
/*     */     
/* 566 */     if (var3 != null)
/*     */     {
/* 568 */       var3.func_174873_u();
/*     */     }
/*     */     
/* 571 */     if (!this.worldObj.isRemote) {
/*     */       
/* 573 */       Iterator<EntityPlayer> var4 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().expand(50.0D, 100.0D, 50.0D)).iterator();
/*     */       
/* 575 */       while (var4.hasNext()) {
/*     */         
/* 577 */         EntityPlayer var5 = var4.next();
/* 578 */         var5.triggerAchievement((StatBase)AchievementList.killWither);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void despawnEntity() {
/* 588 */     this.entityAge = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/* 593 */     return 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float distance, float damageMultiplier) {}
/*     */ 
/*     */   
/*     */   public void addPotionEffect(PotionEffect p_70690_1_) {}
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 605 */     super.applyEntityAttributes();
/* 606 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(300.0D);
/* 607 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6000000238418579D);
/* 608 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_82207_a(int p_82207_1_) {
/* 613 */     return this.field_82221_e[p_82207_1_];
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_82210_r(int p_82210_1_) {
/* 618 */     return this.field_82220_d[p_82210_1_];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getInvulTime() {
/* 623 */     return this.dataWatcher.getWatchableObjectInt(20);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvulTime(int p_82215_1_) {
/* 628 */     this.dataWatcher.updateObject(20, Integer.valueOf(p_82215_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWatchedTargetId(int p_82203_1_) {
/* 636 */     return this.dataWatcher.getWatchableObjectInt(17 + p_82203_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82211_c(int p_82211_1_, int p_82211_2_) {
/* 641 */     this.dataWatcher.updateObject(17 + p_82211_1_, Integer.valueOf(p_82211_2_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isArmored() {
/* 650 */     return (getHealth() <= getMaxHealth() / 2.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 658 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mountEntity(Entity entityIn) {
/* 666 */     this.ridingEntity = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\boss\EntityWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */