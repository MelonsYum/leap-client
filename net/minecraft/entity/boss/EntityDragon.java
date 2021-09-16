/*     */ package net.minecraft.entity.boss;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockTorch;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityMultiPart;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.item.EntityEnderCrystal;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDragon
/*     */   extends EntityLiving
/*     */   implements IBossDisplayData, IEntityMultiPart, IMob
/*     */ {
/*     */   public double targetX;
/*     */   public double targetY;
/*     */   public double targetZ;
/*  40 */   public double[][] ringBuffer = new double[64][3];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public int ringBufferIndex = -1;
/*     */ 
/*     */   
/*     */   public EntityDragonPart[] dragonPartArray;
/*     */ 
/*     */   
/*     */   public EntityDragonPart dragonPartHead;
/*     */ 
/*     */   
/*     */   public EntityDragonPart dragonPartBody;
/*     */ 
/*     */   
/*     */   public EntityDragonPart dragonPartTail1;
/*     */   
/*     */   public EntityDragonPart dragonPartTail2;
/*     */   
/*     */   public EntityDragonPart dragonPartTail3;
/*     */   
/*     */   public EntityDragonPart dragonPartWing1;
/*     */   
/*     */   public EntityDragonPart dragonPartWing2;
/*     */   
/*     */   public float prevAnimTime;
/*     */   
/*     */   public float animTime;
/*     */   
/*     */   public boolean forceNewTarget;
/*     */   
/*     */   public boolean slowed;
/*     */   
/*     */   private Entity target;
/*     */   
/*     */   public int deathTicks;
/*     */   
/*     */   public EntityEnderCrystal healingEnderCrystal;
/*     */   
/*     */   private static final String __OBFID = "CL_00001659";
/*     */ 
/*     */   
/*     */   public EntityDragon(World worldIn) {
/*  85 */     super(worldIn);
/*  86 */     this.dragonPartArray = new EntityDragonPart[] { this.dragonPartHead = new EntityDragonPart(this, "head", 6.0F, 6.0F), this.dragonPartBody = new EntityDragonPart(this, "body", 8.0F, 8.0F), this.dragonPartTail1 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail2 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail3 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartWing1 = new EntityDragonPart(this, "wing", 4.0F, 4.0F), this.dragonPartWing2 = new EntityDragonPart(this, "wing", 4.0F, 4.0F) };
/*  87 */     setHealth(getMaxHealth());
/*  88 */     setSize(16.0F, 8.0F);
/*  89 */     this.noClip = true;
/*  90 */     this.isImmuneToFire = true;
/*  91 */     this.targetY = 100.0D;
/*  92 */     this.ignoreFrustumCheck = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  97 */     super.applyEntityAttributes();
/*  98 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 103 */     super.entityInit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getMovementOffsets(int p_70974_1_, float p_70974_2_) {
/* 112 */     if (getHealth() <= 0.0F)
/*     */     {
/* 114 */       p_70974_2_ = 0.0F;
/*     */     }
/*     */     
/* 117 */     p_70974_2_ = 1.0F - p_70974_2_;
/* 118 */     int var3 = this.ringBufferIndex - p_70974_1_ * 1 & 0x3F;
/* 119 */     int var4 = this.ringBufferIndex - p_70974_1_ * 1 - 1 & 0x3F;
/* 120 */     double[] var5 = new double[3];
/* 121 */     double var6 = this.ringBuffer[var3][0];
/* 122 */     double var8 = MathHelper.wrapAngleTo180_double(this.ringBuffer[var4][0] - var6);
/* 123 */     var5[0] = var6 + var8 * p_70974_2_;
/* 124 */     var6 = this.ringBuffer[var3][1];
/* 125 */     var8 = this.ringBuffer[var4][1] - var6;
/* 126 */     var5[1] = var6 + var8 * p_70974_2_;
/* 127 */     var5[2] = this.ringBuffer[var3][2] + (this.ringBuffer[var4][2] - this.ringBuffer[var3][2]) * p_70974_2_;
/* 128 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 140 */     if (this.worldObj.isRemote) {
/*     */       
/* 142 */       float var1 = MathHelper.cos(this.animTime * 3.1415927F * 2.0F);
/* 143 */       float var2 = MathHelper.cos(this.prevAnimTime * 3.1415927F * 2.0F);
/*     */       
/* 145 */       if (var2 <= -0.3F && var1 >= -0.3F && !isSlient())
/*     */       {
/* 147 */         this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
/*     */       }
/*     */     } 
/*     */     
/* 151 */     this.prevAnimTime = this.animTime;
/*     */ 
/*     */     
/* 154 */     if (getHealth() <= 0.0F) {
/*     */       
/* 156 */       float var1 = (this.rand.nextFloat() - 0.5F) * 8.0F;
/* 157 */       float var2 = (this.rand.nextFloat() - 0.5F) * 4.0F;
/* 158 */       float var3 = (this.rand.nextFloat() - 0.5F) * 8.0F;
/* 159 */       this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX + var1, this.posY + 2.0D + var2, this.posZ + var3, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     }
/*     */     else {
/*     */       
/* 163 */       updateDragonEnderCrystal();
/* 164 */       float var1 = 0.2F / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F);
/* 165 */       var1 *= (float)Math.pow(2.0D, this.motionY);
/*     */       
/* 167 */       if (this.slowed) {
/*     */         
/* 169 */         this.animTime += var1 * 0.5F;
/*     */       }
/*     */       else {
/*     */         
/* 173 */         this.animTime += var1;
/*     */       } 
/*     */       
/* 176 */       this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
/*     */       
/* 178 */       if (this.ringBufferIndex < 0)
/*     */       {
/* 180 */         for (int var27 = 0; var27 < this.ringBuffer.length; var27++) {
/*     */           
/* 182 */           this.ringBuffer[var27][0] = this.rotationYaw;
/* 183 */           this.ringBuffer[var27][1] = this.posY;
/*     */         } 
/*     */       }
/*     */       
/* 187 */       if (++this.ringBufferIndex == this.ringBuffer.length)
/*     */       {
/* 189 */         this.ringBufferIndex = 0;
/*     */       }
/*     */       
/* 192 */       this.ringBuffer[this.ringBufferIndex][0] = this.rotationYaw;
/* 193 */       this.ringBuffer[this.ringBufferIndex][1] = this.posY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 200 */       if (this.worldObj.isRemote) {
/*     */         
/* 202 */         if (this.newPosRotationIncrements > 0)
/*     */         {
/* 204 */           double var28 = this.posX + (this.newPosX - this.posX) / this.newPosRotationIncrements;
/* 205 */           double var4 = this.posY + (this.newPosY - this.posY) / this.newPosRotationIncrements;
/* 206 */           double var6 = this.posZ + (this.newPosZ - this.posZ) / this.newPosRotationIncrements;
/* 207 */           double var8 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - this.rotationYaw);
/* 208 */           this.rotationYaw = (float)(this.rotationYaw + var8 / this.newPosRotationIncrements);
/* 209 */           this.rotationPitch = (float)(this.rotationPitch + (this.newRotationPitch - this.rotationPitch) / this.newPosRotationIncrements);
/* 210 */           this.newPosRotationIncrements--;
/* 211 */           setPosition(var28, var4, var6);
/* 212 */           setRotation(this.rotationYaw, this.rotationPitch);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 217 */         double var28 = this.targetX - this.posX;
/* 218 */         double var4 = this.targetY - this.posY;
/* 219 */         double var6 = this.targetZ - this.posZ;
/* 220 */         double var8 = var28 * var28 + var4 * var4 + var6 * var6;
/*     */ 
/*     */         
/* 223 */         if (this.target != null) {
/*     */           
/* 225 */           this.targetX = this.target.posX;
/* 226 */           this.targetZ = this.target.posZ;
/* 227 */           double var10 = this.targetX - this.posX;
/* 228 */           double var12 = this.targetZ - this.posZ;
/* 229 */           double var14 = Math.sqrt(var10 * var10 + var12 * var12);
/* 230 */           double d1 = 0.4000000059604645D + var14 / 80.0D - 1.0D;
/*     */           
/* 232 */           if (d1 > 10.0D)
/*     */           {
/* 234 */             d1 = 10.0D;
/*     */           }
/*     */           
/* 237 */           this.targetY = (this.target.getEntityBoundingBox()).minY + d1;
/*     */         }
/*     */         else {
/*     */           
/* 241 */           this.targetX += this.rand.nextGaussian() * 2.0D;
/* 242 */           this.targetZ += this.rand.nextGaussian() * 2.0D;
/*     */         } 
/*     */         
/* 245 */         if (this.forceNewTarget || var8 < 100.0D || var8 > 22500.0D || this.isCollidedHorizontally || this.isCollidedVertically)
/*     */         {
/* 247 */           setNewTarget();
/*     */         }
/*     */         
/* 250 */         var4 /= MathHelper.sqrt_double(var28 * var28 + var6 * var6);
/* 251 */         float f1 = 0.6F;
/* 252 */         var4 = MathHelper.clamp_double(var4, -f1, f1);
/* 253 */         this.motionY += var4 * 0.10000000149011612D;
/* 254 */         this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
/* 255 */         double var11 = 180.0D - Math.atan2(var28, var6) * 180.0D / Math.PI;
/* 256 */         double var13 = MathHelper.wrapAngleTo180_double(var11 - this.rotationYaw);
/*     */         
/* 258 */         if (var13 > 50.0D)
/*     */         {
/* 260 */           var13 = 50.0D;
/*     */         }
/*     */         
/* 263 */         if (var13 < -50.0D)
/*     */         {
/* 265 */           var13 = -50.0D;
/*     */         }
/*     */         
/* 268 */         Vec3 var15 = (new Vec3(this.targetX - this.posX, this.targetY - this.posY, this.targetZ - this.posZ)).normalize();
/* 269 */         double var16 = -MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);
/* 270 */         Vec3 var18 = (new Vec3(MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F), this.motionY, var16)).normalize();
/* 271 */         float var19 = ((float)var18.dotProduct(var15) + 0.5F) / 1.5F;
/*     */         
/* 273 */         if (var19 < 0.0F)
/*     */         {
/* 275 */           var19 = 0.0F;
/*     */         }
/*     */         
/* 278 */         this.randomYawVelocity *= 0.8F;
/* 279 */         float var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F;
/* 280 */         double var21 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0D + 1.0D;
/*     */         
/* 282 */         if (var21 > 40.0D)
/*     */         {
/* 284 */           var21 = 40.0D;
/*     */         }
/*     */         
/* 287 */         this.randomYawVelocity = (float)(this.randomYawVelocity + var13 * 0.699999988079071D / var21 / var20);
/* 288 */         this.rotationYaw += this.randomYawVelocity * 0.1F;
/* 289 */         float var23 = (float)(2.0D / (var21 + 1.0D));
/* 290 */         float var24 = 0.06F;
/* 291 */         moveFlying(0.0F, -1.0F, var24 * (var19 * var23 + 1.0F - var23));
/*     */         
/* 293 */         if (this.slowed) {
/*     */           
/* 295 */           moveEntity(this.motionX * 0.800000011920929D, this.motionY * 0.800000011920929D, this.motionZ * 0.800000011920929D);
/*     */         }
/*     */         else {
/*     */           
/* 299 */           moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */         } 
/*     */         
/* 302 */         Vec3 var25 = (new Vec3(this.motionX, this.motionY, this.motionZ)).normalize();
/* 303 */         float var26 = ((float)var25.dotProduct(var18) + 1.0F) / 2.0F;
/* 304 */         var26 = 0.8F + 0.15F * var26;
/* 305 */         this.motionX *= var26;
/* 306 */         this.motionZ *= var26;
/* 307 */         this.motionY *= 0.9100000262260437D;
/*     */       } 
/*     */       
/* 310 */       this.renderYawOffset = this.rotationYaw;
/* 311 */       this.dragonPartHead.width = this.dragonPartHead.height = 3.0F;
/* 312 */       this.dragonPartTail1.width = this.dragonPartTail1.height = 2.0F;
/* 313 */       this.dragonPartTail2.width = this.dragonPartTail2.height = 2.0F;
/* 314 */       this.dragonPartTail3.width = this.dragonPartTail3.height = 2.0F;
/* 315 */       this.dragonPartBody.height = 3.0F;
/* 316 */       this.dragonPartBody.width = 5.0F;
/* 317 */       this.dragonPartWing1.height = 2.0F;
/* 318 */       this.dragonPartWing1.width = 4.0F;
/* 319 */       this.dragonPartWing2.height = 3.0F;
/* 320 */       this.dragonPartWing2.width = 4.0F;
/* 321 */       float var2 = (float)(getMovementOffsets(5, 1.0F)[1] - getMovementOffsets(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
/* 322 */       float var3 = MathHelper.cos(var2);
/* 323 */       float var29 = -MathHelper.sin(var2);
/* 324 */       float var5 = this.rotationYaw * 3.1415927F / 180.0F;
/* 325 */       float var30 = MathHelper.sin(var5);
/* 326 */       float var7 = MathHelper.cos(var5);
/* 327 */       this.dragonPartBody.onUpdate();
/* 328 */       this.dragonPartBody.setLocationAndAngles(this.posX + (var30 * 0.5F), this.posY, this.posZ - (var7 * 0.5F), 0.0F, 0.0F);
/* 329 */       this.dragonPartWing1.onUpdate();
/* 330 */       this.dragonPartWing1.setLocationAndAngles(this.posX + (var7 * 4.5F), this.posY + 2.0D, this.posZ + (var30 * 4.5F), 0.0F, 0.0F);
/* 331 */       this.dragonPartWing2.onUpdate();
/* 332 */       this.dragonPartWing2.setLocationAndAngles(this.posX - (var7 * 4.5F), this.posY + 2.0D, this.posZ - (var30 * 4.5F), 0.0F, 0.0F);
/*     */       
/* 334 */       if (!this.worldObj.isRemote && this.hurtTime == 0) {
/*     */         
/* 336 */         collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.dragonPartWing1.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
/* 337 */         collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.dragonPartWing2.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
/* 338 */         attackEntitiesInList(this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.dragonPartHead.getEntityBoundingBox().expand(1.0D, 1.0D, 1.0D)));
/*     */       } 
/*     */       
/* 341 */       double[] var31 = getMovementOffsets(5, 1.0F);
/* 342 */       double[] var9 = getMovementOffsets(0, 1.0F);
/* 343 */       float var33 = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F - this.randomYawVelocity * 0.01F);
/* 344 */       float var35 = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F - this.randomYawVelocity * 0.01F);
/* 345 */       this.dragonPartHead.onUpdate();
/* 346 */       this.dragonPartHead.setLocationAndAngles(this.posX + (var33 * 5.5F * var3), this.posY + (var9[1] - var31[1]) * 1.0D + (var29 * 5.5F), this.posZ - (var35 * 5.5F * var3), 0.0F, 0.0F);
/*     */       
/* 348 */       for (int var32 = 0; var32 < 3; var32++) {
/*     */         
/* 350 */         EntityDragonPart var34 = null;
/*     */         
/* 352 */         if (var32 == 0)
/*     */         {
/* 354 */           var34 = this.dragonPartTail1;
/*     */         }
/*     */         
/* 357 */         if (var32 == 1)
/*     */         {
/* 359 */           var34 = this.dragonPartTail2;
/*     */         }
/*     */         
/* 362 */         if (var32 == 2)
/*     */         {
/* 364 */           var34 = this.dragonPartTail3;
/*     */         }
/*     */         
/* 367 */         double[] var36 = getMovementOffsets(12 + var32 * 2, 1.0F);
/* 368 */         float var37 = this.rotationYaw * 3.1415927F / 180.0F + simplifyAngle(var36[0] - var31[0]) * 3.1415927F / 180.0F * 1.0F;
/* 369 */         float var38 = MathHelper.sin(var37);
/* 370 */         float var39 = MathHelper.cos(var37);
/* 371 */         float var40 = 1.5F;
/* 372 */         float var41 = (var32 + 1) * 2.0F;
/* 373 */         var34.onUpdate();
/* 374 */         var34.setLocationAndAngles(this.posX - ((var30 * var40 + var38 * var41) * var3), this.posY + (var36[1] - var31[1]) * 1.0D - ((var41 + var40) * var29) + 1.5D, this.posZ + ((var7 * var40 + var39 * var41) * var3), 0.0F, 0.0F);
/*     */       } 
/*     */       
/* 377 */       if (!this.worldObj.isRemote)
/*     */       {
/* 379 */         this.slowed = destroyBlocksInAABB(this.dragonPartHead.getEntityBoundingBox()) | destroyBlocksInAABB(this.dragonPartBody.getEntityBoundingBox());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateDragonEnderCrystal() {
/* 389 */     if (this.healingEnderCrystal != null)
/*     */     {
/* 391 */       if (this.healingEnderCrystal.isDead) {
/*     */         
/* 393 */         if (!this.worldObj.isRemote)
/*     */         {
/* 395 */           attackEntityFromPart(this.dragonPartHead, DamageSource.setExplosionSource(null), 10.0F);
/*     */         }
/*     */         
/* 398 */         this.healingEnderCrystal = null;
/*     */       }
/* 400 */       else if (this.ticksExisted % 10 == 0 && getHealth() < getMaxHealth()) {
/*     */         
/* 402 */         setHealth(getHealth() + 1.0F);
/*     */       } 
/*     */     }
/*     */     
/* 406 */     if (this.rand.nextInt(10) == 0) {
/*     */       
/* 408 */       float var1 = 32.0F;
/* 409 */       List var2 = this.worldObj.getEntitiesWithinAABB(EntityEnderCrystal.class, getEntityBoundingBox().expand(var1, var1, var1));
/* 410 */       EntityEnderCrystal var3 = null;
/* 411 */       double var4 = Double.MAX_VALUE;
/* 412 */       Iterator<EntityEnderCrystal> var6 = var2.iterator();
/*     */       
/* 414 */       while (var6.hasNext()) {
/*     */         
/* 416 */         EntityEnderCrystal var7 = var6.next();
/* 417 */         double var8 = var7.getDistanceSqToEntity((Entity)this);
/*     */         
/* 419 */         if (var8 < var4) {
/*     */           
/* 421 */           var4 = var8;
/* 422 */           var3 = var7;
/*     */         } 
/*     */       } 
/*     */       
/* 426 */       this.healingEnderCrystal = var3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void collideWithEntities(List p_70970_1_) {
/* 435 */     double var2 = ((this.dragonPartBody.getEntityBoundingBox()).minX + (this.dragonPartBody.getEntityBoundingBox()).maxX) / 2.0D;
/* 436 */     double var4 = ((this.dragonPartBody.getEntityBoundingBox()).minZ + (this.dragonPartBody.getEntityBoundingBox()).maxZ) / 2.0D;
/* 437 */     Iterator<Entity> var6 = p_70970_1_.iterator();
/*     */     
/* 439 */     while (var6.hasNext()) {
/*     */       
/* 441 */       Entity var7 = var6.next();
/*     */       
/* 443 */       if (var7 instanceof EntityLivingBase) {
/*     */         
/* 445 */         double var8 = var7.posX - var2;
/* 446 */         double var10 = var7.posZ - var4;
/* 447 */         double var12 = var8 * var8 + var10 * var10;
/* 448 */         var7.addVelocity(var8 / var12 * 4.0D, 0.20000000298023224D, var10 / var12 * 4.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void attackEntitiesInList(List<Entity> p_70971_1_) {
/* 458 */     for (int var2 = 0; var2 < p_70971_1_.size(); var2++) {
/*     */       
/* 460 */       Entity var3 = p_70971_1_.get(var2);
/*     */       
/* 462 */       if (var3 instanceof EntityLivingBase) {
/*     */         
/* 464 */         var3.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 10.0F);
/* 465 */         func_174815_a((EntityLivingBase)this, var3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setNewTarget() {
/* 475 */     this.forceNewTarget = false;
/* 476 */     ArrayList<Entity> var1 = Lists.newArrayList(this.worldObj.playerEntities);
/* 477 */     Iterator<EntityPlayer> var2 = var1.iterator();
/*     */     
/* 479 */     while (var2.hasNext()) {
/*     */       
/* 481 */       if (((EntityPlayer)var2.next()).func_175149_v())
/*     */       {
/* 483 */         var2.remove();
/*     */       }
/*     */     } 
/*     */     
/* 487 */     if (this.rand.nextInt(2) == 0 && !var1.isEmpty()) {
/*     */       
/* 489 */       this.target = var1.get(this.rand.nextInt(var1.size()));
/*     */     } else {
/*     */       boolean var3;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 497 */         this.targetX = 0.0D;
/* 498 */         this.targetY = (70.0F + this.rand.nextFloat() * 50.0F);
/* 499 */         this.targetZ = 0.0D;
/* 500 */         this.targetX += (this.rand.nextFloat() * 120.0F - 60.0F);
/* 501 */         this.targetZ += (this.rand.nextFloat() * 120.0F - 60.0F);
/* 502 */         double var4 = this.posX - this.targetX;
/* 503 */         double var6 = this.posY - this.targetY;
/* 504 */         double var8 = this.posZ - this.targetZ;
/* 505 */         var3 = (var4 * var4 + var6 * var6 + var8 * var8 > 100.0D);
/*     */       }
/* 507 */       while (!var3);
/*     */       
/* 509 */       this.target = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float simplifyAngle(double p_70973_1_) {
/* 518 */     return (float)MathHelper.wrapAngleTo180_double(p_70973_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean destroyBlocksInAABB(AxisAlignedBB p_70972_1_) {
/* 526 */     int var2 = MathHelper.floor_double(p_70972_1_.minX);
/* 527 */     int var3 = MathHelper.floor_double(p_70972_1_.minY);
/* 528 */     int var4 = MathHelper.floor_double(p_70972_1_.minZ);
/* 529 */     int var5 = MathHelper.floor_double(p_70972_1_.maxX);
/* 530 */     int var6 = MathHelper.floor_double(p_70972_1_.maxY);
/* 531 */     int var7 = MathHelper.floor_double(p_70972_1_.maxZ);
/* 532 */     boolean var8 = false;
/* 533 */     boolean var9 = false;
/*     */     
/* 535 */     for (int var10 = var2; var10 <= var5; var10++) {
/*     */       
/* 537 */       for (int var11 = var3; var11 <= var6; var11++) {
/*     */         
/* 539 */         for (int var12 = var4; var12 <= var7; var12++) {
/*     */           
/* 541 */           Block var13 = this.worldObj.getBlockState(new BlockPos(var10, var11, var12)).getBlock();
/*     */           
/* 543 */           if (var13.getMaterial() != Material.air)
/*     */           {
/* 545 */             if (var13 != Blocks.barrier && var13 != Blocks.obsidian && var13 != Blocks.end_stone && var13 != Blocks.bedrock && var13 != Blocks.command_block && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*     */               
/* 547 */               var9 = !(!this.worldObj.setBlockToAir(new BlockPos(var10, var11, var12)) && !var9);
/*     */             }
/*     */             else {
/*     */               
/* 551 */               var8 = true;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 558 */     if (var9) {
/*     */       
/* 560 */       double var16 = p_70972_1_.minX + (p_70972_1_.maxX - p_70972_1_.minX) * this.rand.nextFloat();
/* 561 */       double var17 = p_70972_1_.minY + (p_70972_1_.maxY - p_70972_1_.minY) * this.rand.nextFloat();
/* 562 */       double var14 = p_70972_1_.minZ + (p_70972_1_.maxZ - p_70972_1_.minZ) * this.rand.nextFloat();
/* 563 */       this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, var16, var17, var14, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */     
/* 566 */     return var8;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityFromPart(EntityDragonPart p_70965_1_, DamageSource p_70965_2_, float p_70965_3_) {
/* 571 */     if (p_70965_1_ != this.dragonPartHead)
/*     */     {
/* 573 */       p_70965_3_ = p_70965_3_ / 4.0F + 1.0F;
/*     */     }
/*     */     
/* 576 */     float var4 = this.rotationYaw * 3.1415927F / 180.0F;
/* 577 */     float var5 = MathHelper.sin(var4);
/* 578 */     float var6 = MathHelper.cos(var4);
/* 579 */     this.targetX = this.posX + (var5 * 5.0F) + ((this.rand.nextFloat() - 0.5F) * 2.0F);
/* 580 */     this.targetY = this.posY + (this.rand.nextFloat() * 3.0F) + 1.0D;
/* 581 */     this.targetZ = this.posZ - (var6 * 5.0F) + ((this.rand.nextFloat() - 0.5F) * 2.0F);
/* 582 */     this.target = null;
/*     */     
/* 584 */     if (p_70965_2_.getEntity() instanceof EntityPlayer || p_70965_2_.isExplosion())
/*     */     {
/* 586 */       func_82195_e(p_70965_2_, p_70965_3_);
/*     */     }
/*     */     
/* 589 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 597 */     if (source instanceof EntityDamageSource && ((EntityDamageSource)source).func_180139_w())
/*     */     {
/* 599 */       func_82195_e(source, amount);
/*     */     }
/*     */     
/* 602 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_82195_e(DamageSource p_82195_1_, float p_82195_2_) {
/* 607 */     return super.attackEntityFrom(p_82195_1_, p_82195_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174812_G() {
/* 612 */     setDead();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDeathUpdate() {
/* 620 */     this.deathTicks++;
/*     */     
/* 622 */     if (this.deathTicks >= 180 && this.deathTicks <= 200) {
/*     */       
/* 624 */       float var1 = (this.rand.nextFloat() - 0.5F) * 8.0F;
/* 625 */       float var2 = (this.rand.nextFloat() - 0.5F) * 4.0F;
/* 626 */       float var3 = (this.rand.nextFloat() - 0.5F) * 8.0F;
/* 627 */       this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + var1, this.posY + 2.0D + var2, this.posZ + var3, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 633 */     if (!this.worldObj.isRemote) {
/*     */       
/* 635 */       if (this.deathTicks > 150 && this.deathTicks % 5 == 0 && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot")) {
/*     */         
/* 637 */         int var4 = 1000;
/*     */         
/* 639 */         while (var4 > 0) {
/*     */           
/* 641 */           int var5 = EntityXPOrb.getXPSplit(var4);
/* 642 */           var4 -= var5;
/* 643 */           this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
/*     */         } 
/*     */       } 
/*     */       
/* 647 */       if (this.deathTicks == 1)
/*     */       {
/* 649 */         this.worldObj.func_175669_a(1018, new BlockPos((Entity)this), 0);
/*     */       }
/*     */     } 
/*     */     
/* 653 */     moveEntity(0.0D, 0.10000000149011612D, 0.0D);
/* 654 */     this.renderYawOffset = this.rotationYaw += 20.0F;
/*     */     
/* 656 */     if (this.deathTicks == 200 && !this.worldObj.isRemote) {
/*     */       
/* 658 */       int var4 = 2000;
/*     */       
/* 660 */       while (var4 > 0) {
/*     */         
/* 662 */         int var5 = EntityXPOrb.getXPSplit(var4);
/* 663 */         var4 -= var5;
/* 664 */         this.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
/*     */       } 
/*     */       
/* 667 */       func_175499_a(new BlockPos(this.posX, 64.0D, this.posZ));
/* 668 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175499_a(BlockPos p_175499_1_) {
/* 674 */     boolean var2 = true;
/* 675 */     double var3 = 12.25D;
/* 676 */     double var5 = 6.25D;
/*     */     
/* 678 */     for (int var7 = -1; var7 <= 32; var7++) {
/*     */       
/* 680 */       for (int var8 = -4; var8 <= 4; var8++) {
/*     */         
/* 682 */         for (int var9 = -4; var9 <= 4; var9++) {
/*     */           
/* 684 */           double var10 = (var8 * var8 + var9 * var9);
/*     */           
/* 686 */           if (var10 <= 12.25D) {
/*     */             
/* 688 */             BlockPos var12 = p_175499_1_.add(var8, var7, var9);
/*     */             
/* 690 */             if (var7 < 0) {
/*     */               
/* 692 */               if (var10 <= 6.25D)
/*     */               {
/* 694 */                 this.worldObj.setBlockState(var12, Blocks.bedrock.getDefaultState());
/*     */               }
/*     */             }
/* 697 */             else if (var7 > 0) {
/*     */               
/* 699 */               this.worldObj.setBlockState(var12, Blocks.air.getDefaultState());
/*     */             }
/* 701 */             else if (var10 > 6.25D) {
/*     */               
/* 703 */               this.worldObj.setBlockState(var12, Blocks.bedrock.getDefaultState());
/*     */             }
/*     */             else {
/*     */               
/* 707 */               this.worldObj.setBlockState(var12, Blocks.end_portal.getDefaultState());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 714 */     this.worldObj.setBlockState(p_175499_1_, Blocks.bedrock.getDefaultState());
/* 715 */     this.worldObj.setBlockState(p_175499_1_.offsetUp(), Blocks.bedrock.getDefaultState());
/* 716 */     BlockPos var13 = p_175499_1_.offsetUp(2);
/* 717 */     this.worldObj.setBlockState(var13, Blocks.bedrock.getDefaultState());
/* 718 */     this.worldObj.setBlockState(var13.offsetWest(), Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)EnumFacing.EAST));
/* 719 */     this.worldObj.setBlockState(var13.offsetEast(), Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)EnumFacing.WEST));
/* 720 */     this.worldObj.setBlockState(var13.offsetNorth(), Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)EnumFacing.SOUTH));
/* 721 */     this.worldObj.setBlockState(var13.offsetSouth(), Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)EnumFacing.NORTH));
/* 722 */     this.worldObj.setBlockState(p_175499_1_.offsetUp(3), Blocks.bedrock.getDefaultState());
/* 723 */     this.worldObj.setBlockState(p_175499_1_.offsetUp(4), Blocks.dragon_egg.getDefaultState());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void despawnEntity() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity[] getParts() {
/* 736 */     return (Entity[])this.dragonPartArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 744 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public World func_82194_d() {
/* 749 */     return this.worldObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 757 */     return "mob.enderdragon.growl";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 765 */     return "mob.enderdragon.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/* 773 */     return 5.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\boss\EntityDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */