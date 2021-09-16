/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityBoat
/*     */   extends Entity
/*     */ {
/*     */   private boolean isBoatEmpty;
/*     */   private double speedMultiplier;
/*     */   private int boatPosRotationIncrements;
/*     */   private double boatX;
/*     */   private double boatY;
/*     */   private double boatZ;
/*     */   private double boatYaw;
/*     */   private double boatPitch;
/*     */   private double velocityX;
/*     */   private double velocityY;
/*     */   private double velocityZ;
/*     */   private static final String __OBFID = "CL_00001667";
/*     */   
/*     */   public EntityBoat(World worldIn) {
/*  39 */     super(worldIn);
/*  40 */     this.isBoatEmpty = true;
/*  41 */     this.speedMultiplier = 0.07D;
/*  42 */     this.preventEntitySpawning = true;
/*  43 */     setSize(1.5F, 0.6F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  57 */     this.dataWatcher.addObject(17, new Integer(0));
/*  58 */     this.dataWatcher.addObject(18, new Integer(1));
/*  59 */     this.dataWatcher.addObject(19, new Float(0.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBox(Entity entityIn) {
/*  68 */     return entityIn.getEntityBoundingBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getBoundingBox() {
/*  76 */     return getEntityBoundingBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityBoat(World worldIn, double p_i1705_2_, double p_i1705_4_, double p_i1705_6_) {
/*  89 */     this(worldIn);
/*  90 */     setPosition(p_i1705_2_, p_i1705_4_, p_i1705_6_);
/*  91 */     this.motionX = 0.0D;
/*  92 */     this.motionY = 0.0D;
/*  93 */     this.motionZ = 0.0D;
/*  94 */     this.prevPosX = p_i1705_2_;
/*  95 */     this.prevPosY = p_i1705_4_;
/*  96 */     this.prevPosZ = p_i1705_6_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 104 */     return this.height * 0.0D - 0.30000001192092896D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 112 */     if (func_180431_b(source))
/*     */     {
/* 114 */       return false;
/*     */     }
/* 116 */     if (!this.worldObj.isRemote && !this.isDead) {
/*     */       
/* 118 */       if (this.riddenByEntity != null && this.riddenByEntity == source.getEntity() && source instanceof net.minecraft.util.EntityDamageSourceIndirect)
/*     */       {
/* 120 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 124 */       setForwardDirection(-getForwardDirection());
/* 125 */       setTimeSinceHit(10);
/* 126 */       setDamageTaken(getDamageTaken() + amount * 10.0F);
/* 127 */       setBeenAttacked();
/* 128 */       boolean var3 = (source.getEntity() instanceof EntityPlayer && ((EntityPlayer)source.getEntity()).capabilities.isCreativeMode);
/*     */       
/* 130 */       if (var3 || getDamageTaken() > 40.0F) {
/*     */         
/* 132 */         if (this.riddenByEntity != null)
/*     */         {
/* 134 */           this.riddenByEntity.mountEntity(this);
/*     */         }
/*     */         
/* 137 */         if (!var3)
/*     */         {
/* 139 */           dropItemWithOffset(Items.boat, 1, 0.0F);
/*     */         }
/*     */         
/* 142 */         setDead();
/*     */       } 
/*     */       
/* 145 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void performHurtAnimation() {
/* 159 */     setForwardDirection(-getForwardDirection());
/* 160 */     setTimeSinceHit(10);
/* 161 */     setDamageTaken(getDamageTaken() * 11.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 169 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
/* 176 */     this.prevPosX = this.posX = p_180426_1_;
/* 177 */     this.prevPosY = this.posY = p_180426_3_;
/* 178 */     this.prevPosZ = this.posZ = p_180426_5_;
/* 179 */     this.rotationYaw = p_180426_7_;
/* 180 */     this.rotationPitch = p_180426_8_;
/* 181 */     this.boatPosRotationIncrements = 0;
/* 182 */     setPosition(p_180426_1_, p_180426_3_, p_180426_5_);
/* 183 */     this.motionX = this.velocityX = 0.0D;
/* 184 */     this.motionY = this.velocityY = 0.0D;
/* 185 */     this.motionZ = this.velocityZ = 0.0D;
/*     */ 
/*     */ 
/*     */     
/* 189 */     if (this.isBoatEmpty) {
/*     */       
/* 191 */       this.boatPosRotationIncrements = p_180426_9_ + 5;
/*     */     }
/*     */     else {
/*     */       
/* 195 */       double var11 = p_180426_1_ - this.posX;
/* 196 */       double var13 = p_180426_3_ - this.posY;
/* 197 */       double var15 = p_180426_5_ - this.posZ;
/* 198 */       double var17 = var11 * var11 + var13 * var13 + var15 * var15;
/*     */       
/* 200 */       if (var17 <= 1.0D) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 205 */       this.boatPosRotationIncrements = 3;
/*     */     } 
/*     */     
/* 208 */     this.boatX = p_180426_1_;
/* 209 */     this.boatY = p_180426_3_;
/* 210 */     this.boatZ = p_180426_5_;
/* 211 */     this.boatYaw = p_180426_7_;
/* 212 */     this.boatPitch = p_180426_8_;
/* 213 */     this.motionX = this.velocityX;
/* 214 */     this.motionY = this.velocityY;
/* 215 */     this.motionZ = this.velocityZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double x, double y, double z) {
/* 224 */     this.velocityX = this.motionX = x;
/* 225 */     this.velocityY = this.motionY = y;
/* 226 */     this.velocityZ = this.motionZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 234 */     super.onUpdate();
/*     */     
/* 236 */     if (getTimeSinceHit() > 0)
/*     */     {
/* 238 */       setTimeSinceHit(getTimeSinceHit() - 1);
/*     */     }
/*     */     
/* 241 */     if (getDamageTaken() > 0.0F)
/*     */     {
/* 243 */       setDamageTaken(getDamageTaken() - 1.0F);
/*     */     }
/*     */     
/* 246 */     this.prevPosX = this.posX;
/* 247 */     this.prevPosY = this.posY;
/* 248 */     this.prevPosZ = this.posZ;
/* 249 */     byte var1 = 5;
/* 250 */     double var2 = 0.0D;
/*     */     
/* 252 */     for (int var4 = 0; var4 < var1; var4++) {
/*     */       
/* 254 */       double var5 = (getEntityBoundingBox()).minY + ((getEntityBoundingBox()).maxY - (getEntityBoundingBox()).minY) * (var4 + 0) / var1 - 0.125D;
/* 255 */       double var7 = (getEntityBoundingBox()).minY + ((getEntityBoundingBox()).maxY - (getEntityBoundingBox()).minY) * (var4 + 1) / var1 - 0.125D;
/* 256 */       AxisAlignedBB var9 = new AxisAlignedBB((getEntityBoundingBox()).minX, var5, (getEntityBoundingBox()).minZ, (getEntityBoundingBox()).maxX, var7, (getEntityBoundingBox()).maxZ);
/*     */       
/* 258 */       if (this.worldObj.isAABBInMaterial(var9, Material.water))
/*     */       {
/* 260 */         var2 += 1.0D / var1;
/*     */       }
/*     */     } 
/*     */     
/* 264 */     double var19 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 269 */     if (var19 > 0.2975D) {
/*     */       
/* 271 */       double var6 = Math.cos(this.rotationYaw * Math.PI / 180.0D);
/* 272 */       double var8 = Math.sin(this.rotationYaw * Math.PI / 180.0D);
/*     */       
/* 274 */       for (int var10 = 0; var10 < 1.0D + var19 * 60.0D; var10++) {
/*     */         
/* 276 */         double var11 = (this.rand.nextFloat() * 2.0F - 1.0F);
/* 277 */         double var13 = (this.rand.nextInt(2) * 2 - 1) * 0.7D;
/*     */ 
/*     */ 
/*     */         
/* 281 */         if (this.rand.nextBoolean()) {
/*     */           
/* 283 */           double var15 = this.posX - var6 * var11 * 0.8D + var8 * var13;
/* 284 */           double var17 = this.posZ - var8 * var11 * 0.8D - var6 * var13;
/* 285 */           this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, var15, this.posY - 0.125D, var17, this.motionX, this.motionY, this.motionZ, new int[0]);
/*     */         }
/*     */         else {
/*     */           
/* 289 */           double var15 = this.posX + var6 + var8 * var11 * 0.7D;
/* 290 */           double var17 = this.posZ + var8 - var6 * var11 * 0.7D;
/* 291 */           this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, var15, this.posY - 0.125D, var17, this.motionX, this.motionY, this.motionZ, new int[0]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     if (this.worldObj.isRemote && this.isBoatEmpty) {
/*     */       
/* 301 */       if (this.boatPosRotationIncrements > 0)
/*     */       {
/* 303 */         double var6 = this.posX + (this.boatX - this.posX) / this.boatPosRotationIncrements;
/* 304 */         double var8 = this.posY + (this.boatY - this.posY) / this.boatPosRotationIncrements;
/* 305 */         double var24 = this.posZ + (this.boatZ - this.posZ) / this.boatPosRotationIncrements;
/* 306 */         double var26 = MathHelper.wrapAngleTo180_double(this.boatYaw - this.rotationYaw);
/* 307 */         this.rotationYaw = (float)(this.rotationYaw + var26 / this.boatPosRotationIncrements);
/* 308 */         this.rotationPitch = (float)(this.rotationPitch + (this.boatPitch - this.rotationPitch) / this.boatPosRotationIncrements);
/* 309 */         this.boatPosRotationIncrements--;
/* 310 */         setPosition(var6, var8, var24);
/* 311 */         setRotation(this.rotationYaw, this.rotationPitch);
/*     */       }
/*     */       else
/*     */       {
/* 315 */         double var6 = this.posX + this.motionX;
/* 316 */         double var8 = this.posY + this.motionY;
/* 317 */         double var24 = this.posZ + this.motionZ;
/* 318 */         setPosition(var6, var8, var24);
/*     */         
/* 320 */         if (this.onGround) {
/*     */           
/* 322 */           this.motionX *= 0.5D;
/* 323 */           this.motionY *= 0.5D;
/* 324 */           this.motionZ *= 0.5D;
/*     */         } 
/*     */         
/* 327 */         this.motionX *= 0.9900000095367432D;
/* 328 */         this.motionY *= 0.949999988079071D;
/* 329 */         this.motionZ *= 0.9900000095367432D;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 334 */       if (var2 < 1.0D) {
/*     */         
/* 336 */         double d = var2 * 2.0D - 1.0D;
/* 337 */         this.motionY += 0.03999999910593033D * d;
/*     */       }
/*     */       else {
/*     */         
/* 341 */         if (this.motionY < 0.0D)
/*     */         {
/* 343 */           this.motionY /= 2.0D;
/*     */         }
/*     */         
/* 346 */         this.motionY += 0.007000000216066837D;
/*     */       } 
/*     */       
/* 349 */       if (this.riddenByEntity instanceof EntityLivingBase) {
/*     */         
/* 351 */         EntityLivingBase var20 = (EntityLivingBase)this.riddenByEntity;
/* 352 */         float var21 = this.riddenByEntity.rotationYaw + -var20.moveStrafing * 90.0F;
/* 353 */         this.motionX += -Math.sin((var21 * 3.1415927F / 180.0F)) * this.speedMultiplier * var20.moveForward * 0.05000000074505806D;
/* 354 */         this.motionZ += Math.cos((var21 * 3.1415927F / 180.0F)) * this.speedMultiplier * var20.moveForward * 0.05000000074505806D;
/*     */       } 
/*     */       
/* 357 */       double var6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*     */       
/* 359 */       if (var6 > 0.35D) {
/*     */         
/* 361 */         double d = 0.35D / var6;
/* 362 */         this.motionX *= d;
/* 363 */         this.motionZ *= d;
/* 364 */         var6 = 0.35D;
/*     */       } 
/*     */       
/* 367 */       if (var6 > var19 && this.speedMultiplier < 0.35D) {
/*     */         
/* 369 */         this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;
/*     */         
/* 371 */         if (this.speedMultiplier > 0.35D)
/*     */         {
/* 373 */           this.speedMultiplier = 0.35D;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 378 */         this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;
/*     */         
/* 380 */         if (this.speedMultiplier < 0.07D)
/*     */         {
/* 382 */           this.speedMultiplier = 0.07D;
/*     */         }
/*     */       } 
/*     */       
/*     */       int var22;
/*     */       
/* 388 */       for (var22 = 0; var22 < 4; var22++) {
/*     */         
/* 390 */         int var23 = MathHelper.floor_double(this.posX + ((var22 % 2) - 0.5D) * 0.8D);
/* 391 */         int var10 = MathHelper.floor_double(this.posZ + ((var22 / 2) - 0.5D) * 0.8D);
/*     */         
/* 393 */         for (int var25 = 0; var25 < 2; var25++) {
/*     */           
/* 395 */           int var12 = MathHelper.floor_double(this.posY) + var25;
/* 396 */           BlockPos var27 = new BlockPos(var23, var12, var10);
/* 397 */           Block var14 = this.worldObj.getBlockState(var27).getBlock();
/*     */           
/* 399 */           if (var14 == Blocks.snow_layer) {
/*     */             
/* 401 */             this.worldObj.setBlockToAir(var27);
/* 402 */             this.isCollidedHorizontally = false;
/*     */           }
/* 404 */           else if (var14 == Blocks.waterlily) {
/*     */             
/* 406 */             this.worldObj.destroyBlock(var27, true);
/* 407 */             this.isCollidedHorizontally = false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 412 */       if (this.onGround) {
/*     */         
/* 414 */         this.motionX *= 0.5D;
/* 415 */         this.motionY *= 0.5D;
/* 416 */         this.motionZ *= 0.5D;
/*     */       } 
/*     */       
/* 419 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */       
/* 421 */       if (this.isCollidedHorizontally && var19 > 0.2D) {
/*     */         
/* 423 */         if (!this.worldObj.isRemote && !this.isDead)
/*     */         {
/* 425 */           setDead();
/*     */           
/* 427 */           for (var22 = 0; var22 < 3; var22++)
/*     */           {
/* 429 */             dropItemWithOffset(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
/*     */           }
/*     */           
/* 432 */           for (var22 = 0; var22 < 2; var22++)
/*     */           {
/* 434 */             dropItemWithOffset(Items.stick, 1, 0.0F);
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 440 */         this.motionX *= 0.9900000095367432D;
/* 441 */         this.motionY *= 0.949999988079071D;
/* 442 */         this.motionZ *= 0.9900000095367432D;
/*     */       } 
/*     */       
/* 445 */       this.rotationPitch = 0.0F;
/* 446 */       double var8 = this.rotationYaw;
/* 447 */       double var24 = this.prevPosX - this.posX;
/* 448 */       double var26 = this.prevPosZ - this.posZ;
/*     */       
/* 450 */       if (var24 * var24 + var26 * var26 > 0.001D)
/*     */       {
/* 452 */         var8 = (float)(Math.atan2(var26, var24) * 180.0D / Math.PI);
/*     */       }
/*     */       
/* 455 */       double var28 = MathHelper.wrapAngleTo180_double(var8 - this.rotationYaw);
/*     */       
/* 457 */       if (var28 > 20.0D)
/*     */       {
/* 459 */         var28 = 20.0D;
/*     */       }
/*     */       
/* 462 */       if (var28 < -20.0D)
/*     */       {
/* 464 */         var28 = -20.0D;
/*     */       }
/*     */       
/* 467 */       this.rotationYaw = (float)(this.rotationYaw + var28);
/* 468 */       setRotation(this.rotationYaw, this.rotationPitch);
/*     */       
/* 470 */       if (!this.worldObj.isRemote) {
/*     */         
/* 472 */         List<Entity> var16 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
/*     */         
/* 474 */         if (var16 != null && !var16.isEmpty())
/*     */         {
/* 476 */           for (int var29 = 0; var29 < var16.size(); var29++) {
/*     */             
/* 478 */             Entity var18 = var16.get(var29);
/*     */             
/* 480 */             if (var18 != this.riddenByEntity && var18.canBePushed() && var18 instanceof EntityBoat)
/*     */             {
/* 482 */               var18.applyEntityCollision(this);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 487 */         if (this.riddenByEntity != null && this.riddenByEntity.isDead)
/*     */         {
/* 489 */           this.riddenByEntity = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateRiderPosition() {
/* 497 */     if (this.riddenByEntity != null) {
/*     */       
/* 499 */       double var1 = Math.cos(this.rotationYaw * Math.PI / 180.0D) * 0.4D;
/* 500 */       double var3 = Math.sin(this.rotationYaw * Math.PI / 180.0D) * 0.4D;
/* 501 */       this.riddenByEntity.setPosition(this.posX + var1, this.posY + getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interactFirst(EntityPlayer playerIn) {
/* 520 */     if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != playerIn)
/*     */     {
/* 522 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 526 */     if (!this.worldObj.isRemote)
/*     */     {
/* 528 */       playerIn.mountEntity(this);
/*     */     }
/*     */     
/* 531 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
/* 537 */     if (p_180433_3_) {
/*     */       
/* 539 */       if (this.fallDistance > 3.0F)
/*     */       {
/* 541 */         fall(this.fallDistance, 1.0F);
/*     */         
/* 543 */         if (!this.worldObj.isRemote && !this.isDead) {
/*     */           
/* 545 */           setDead();
/*     */           
/*     */           int var6;
/* 548 */           for (var6 = 0; var6 < 3; var6++)
/*     */           {
/* 550 */             dropItemWithOffset(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
/*     */           }
/*     */           
/* 553 */           for (var6 = 0; var6 < 2; var6++)
/*     */           {
/* 555 */             dropItemWithOffset(Items.stick, 1, 0.0F);
/*     */           }
/*     */         } 
/*     */         
/* 559 */         this.fallDistance = 0.0F;
/*     */       }
/*     */     
/* 562 */     } else if (this.worldObj.getBlockState((new BlockPos(this)).offsetDown()).getBlock().getMaterial() != Material.water && p_180433_1_ < 0.0D) {
/*     */       
/* 564 */       this.fallDistance = (float)(this.fallDistance - p_180433_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamageTaken(float p_70266_1_) {
/* 573 */     this.dataWatcher.updateObject(19, Float.valueOf(p_70266_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDamageTaken() {
/* 581 */     return this.dataWatcher.getWatchableObjectFloat(19);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeSinceHit(int p_70265_1_) {
/* 589 */     this.dataWatcher.updateObject(17, Integer.valueOf(p_70265_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTimeSinceHit() {
/* 597 */     return this.dataWatcher.getWatchableObjectInt(17);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForwardDirection(int p_70269_1_) {
/* 605 */     this.dataWatcher.updateObject(18, Integer.valueOf(p_70269_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getForwardDirection() {
/* 613 */     return this.dataWatcher.getWatchableObjectInt(18);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsBoatEmpty(boolean p_70270_1_) {
/* 621 */     this.isBoatEmpty = p_70270_1_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */