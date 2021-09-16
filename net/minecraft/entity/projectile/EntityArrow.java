/*     */ package net.minecraft.entity.projectile;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IProjectile;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityArrow extends Entity implements IProjectile {
/*  30 */   private int field_145791_d = -1;
/*  31 */   private int field_145792_e = -1;
/*  32 */   private int field_145789_f = -1;
/*     */   
/*     */   private Block field_145790_g;
/*     */   
/*     */   private int inData;
/*     */   
/*     */   private boolean inGround;
/*     */   
/*     */   public int canBePickedUp;
/*     */   
/*     */   public int arrowShake;
/*     */   
/*     */   public Entity shootingEntity;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*  47 */   private double damage = 2.0D;
/*     */   
/*     */   private int knockbackStrength;
/*     */   
/*     */   private static final String __OBFID = "CL_00001715";
/*     */ 
/*     */   
/*     */   public EntityArrow(World worldIn) {
/*  55 */     super(worldIn);
/*  56 */     this.renderDistanceWeight = 10.0D;
/*  57 */     setSize(0.5F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityArrow(World worldIn, double p_i1754_2_, double p_i1754_4_, double p_i1754_6_) {
/*  62 */     super(worldIn);
/*  63 */     this.renderDistanceWeight = 10.0D;
/*  64 */     setSize(0.5F, 0.5F);
/*  65 */     setPosition(p_i1754_2_, p_i1754_4_, p_i1754_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityArrow(World worldIn, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_) {
/*  70 */     super(worldIn);
/*  71 */     this.renderDistanceWeight = 10.0D;
/*  72 */     this.shootingEntity = (Entity)p_i1755_2_;
/*     */     
/*  74 */     if (p_i1755_2_ instanceof EntityPlayer)
/*     */     {
/*  76 */       this.canBePickedUp = 1;
/*     */     }
/*     */     
/*  79 */     this.posY = p_i1755_2_.posY + p_i1755_2_.getEyeHeight() - 0.10000000149011612D;
/*  80 */     double var6 = p_i1755_3_.posX - p_i1755_2_.posX;
/*  81 */     double var8 = (p_i1755_3_.getEntityBoundingBox()).minY + (p_i1755_3_.height / 3.0F) - this.posY;
/*  82 */     double var10 = p_i1755_3_.posZ - p_i1755_2_.posZ;
/*  83 */     double var12 = MathHelper.sqrt_double(var6 * var6 + var10 * var10);
/*     */     
/*  85 */     if (var12 >= 1.0E-7D) {
/*     */       
/*  87 */       float var14 = (float)(Math.atan2(var10, var6) * 180.0D / Math.PI) - 90.0F;
/*  88 */       float var15 = (float)-(Math.atan2(var8, var12) * 180.0D / Math.PI);
/*  89 */       double var16 = var6 / var12;
/*  90 */       double var18 = var10 / var12;
/*  91 */       setLocationAndAngles(p_i1755_2_.posX + var16, this.posY, p_i1755_2_.posZ + var18, var14, var15);
/*  92 */       float var20 = (float)(var12 * 0.20000000298023224D);
/*  93 */       setThrowableHeading(var6, var8 + var20, var10, p_i1755_4_, p_i1755_5_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityArrow(World worldIn, EntityLivingBase p_i1756_2_, float p_i1756_3_) {
/*  99 */     super(worldIn);
/* 100 */     this.renderDistanceWeight = 10.0D;
/* 101 */     this.shootingEntity = (Entity)p_i1756_2_;
/*     */     
/* 103 */     if (p_i1756_2_ instanceof EntityPlayer)
/*     */     {
/* 105 */       this.canBePickedUp = 1;
/*     */     }
/*     */     
/* 108 */     setSize(0.5F, 0.5F);
/* 109 */     setLocationAndAngles(p_i1756_2_.posX, p_i1756_2_.posY + p_i1756_2_.getEyeHeight(), p_i1756_2_.posZ, p_i1756_2_.rotationYaw, p_i1756_2_.rotationPitch);
/* 110 */     this.posX -= (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/* 111 */     this.posY -= 0.10000000149011612D;
/* 112 */     this.posZ -= (MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/* 113 */     setPosition(this.posX, this.posY, this.posZ);
/* 114 */     this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
/* 115 */     this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
/* 116 */     this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F);
/* 117 */     setThrowableHeading(this.motionX, this.motionY, this.motionZ, p_i1756_3_ * 1.5F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 122 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
/* 130 */     float var9 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
/* 131 */     p_70186_1_ /= var9;
/* 132 */     p_70186_3_ /= var9;
/* 133 */     p_70186_5_ /= var9;
/* 134 */     p_70186_1_ += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : true) * 0.007499999832361937D * p_70186_8_;
/* 135 */     p_70186_3_ += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : true) * 0.007499999832361937D * p_70186_8_;
/* 136 */     p_70186_5_ += this.rand.nextGaussian() * (this.rand.nextBoolean() ? -1 : true) * 0.007499999832361937D * p_70186_8_;
/* 137 */     p_70186_1_ *= p_70186_7_;
/* 138 */     p_70186_3_ *= p_70186_7_;
/* 139 */     p_70186_5_ *= p_70186_7_;
/* 140 */     this.motionX = p_70186_1_;
/* 141 */     this.motionY = p_70186_3_;
/* 142 */     this.motionZ = p_70186_5_;
/* 143 */     float var10 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
/* 144 */     this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
/* 145 */     this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70186_3_, var10) * 180.0D / Math.PI);
/* 146 */     this.ticksInGround = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
/* 151 */     setPosition(p_180426_1_, p_180426_3_, p_180426_5_);
/* 152 */     setRotation(p_180426_7_, p_180426_8_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double x, double y, double z) {
/* 160 */     this.motionX = x;
/* 161 */     this.motionY = y;
/* 162 */     this.motionZ = z;
/*     */     
/* 164 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*     */       
/* 166 */       float var7 = MathHelper.sqrt_double(x * x + z * z);
/* 167 */       this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
/* 168 */       this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, var7) * 180.0D / Math.PI);
/* 169 */       this.prevRotationPitch = this.rotationPitch;
/* 170 */       this.prevRotationYaw = this.rotationYaw;
/* 171 */       setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
/* 172 */       this.ticksInGround = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 181 */     super.onUpdate();
/*     */     
/* 183 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*     */       
/* 185 */       float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 186 */       this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/* 187 */       this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, var1) * 180.0D / Math.PI);
/*     */     } 
/*     */     
/* 190 */     BlockPos var18 = new BlockPos(this.field_145791_d, this.field_145792_e, this.field_145789_f);
/* 191 */     IBlockState var2 = this.worldObj.getBlockState(var18);
/* 192 */     Block var3 = var2.getBlock();
/*     */     
/* 194 */     if (var3.getMaterial() != Material.air) {
/*     */       
/* 196 */       var3.setBlockBoundsBasedOnState((IBlockAccess)this.worldObj, var18);
/* 197 */       AxisAlignedBB var4 = var3.getCollisionBoundingBox(this.worldObj, var18, var2);
/*     */       
/* 199 */       if (var4 != null && var4.isVecInside(new Vec3(this.posX, this.posY, this.posZ)))
/*     */       {
/* 201 */         this.inGround = true;
/*     */       }
/*     */     } 
/*     */     
/* 205 */     if (this.arrowShake > 0)
/*     */     {
/* 207 */       this.arrowShake--;
/*     */     }
/*     */     
/* 210 */     if (this.inGround) {
/*     */       
/* 212 */       int var20 = var3.getMetaFromState(var2);
/*     */       
/* 214 */       if (var3 == this.field_145790_g && var20 == this.inData)
/*     */       {
/* 216 */         this.ticksInGround++;
/*     */         
/* 218 */         if (this.ticksInGround >= 1200)
/*     */         {
/* 220 */           setDead();
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 225 */         this.inGround = false;
/* 226 */         this.motionX *= (this.rand.nextFloat() * 0.2F);
/* 227 */         this.motionY *= (this.rand.nextFloat() * 0.2F);
/* 228 */         this.motionZ *= (this.rand.nextFloat() * 0.2F);
/* 229 */         this.ticksInGround = 0;
/* 230 */         this.ticksInAir = 0;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 235 */       this.ticksInAir++;
/* 236 */       Vec3 var19 = new Vec3(this.posX, this.posY, this.posZ);
/* 237 */       Vec3 var5 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/* 238 */       MovingObjectPosition var6 = this.worldObj.rayTraceBlocks(var19, var5, false, true, false);
/* 239 */       var19 = new Vec3(this.posX, this.posY, this.posZ);
/* 240 */       var5 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*     */       
/* 242 */       if (var6 != null)
/*     */       {
/* 244 */         var5 = new Vec3(var6.hitVec.xCoord, var6.hitVec.yCoord, var6.hitVec.zCoord);
/*     */       }
/*     */       
/* 247 */       Entity var7 = null;
/* 248 */       List<Entity> var8 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
/* 249 */       double var9 = 0.0D;
/*     */       
/*     */       int var11;
/*     */       
/* 253 */       for (var11 = 0; var11 < var8.size(); var11++) {
/*     */         
/* 255 */         Entity var12 = var8.get(var11);
/*     */         
/* 257 */         if (var12.canBeCollidedWith() && (var12 != this.shootingEntity || this.ticksInAir >= 5)) {
/*     */           
/* 259 */           float f = 0.3F;
/* 260 */           AxisAlignedBB var14 = var12.getEntityBoundingBox().expand(f, f, f);
/* 261 */           MovingObjectPosition var15 = var14.calculateIntercept(var19, var5);
/*     */           
/* 263 */           if (var15 != null) {
/*     */             
/* 265 */             double var16 = var19.distanceTo(var15.hitVec);
/*     */             
/* 267 */             if (var16 < var9 || var9 == 0.0D) {
/*     */               
/* 269 */               var7 = var12;
/* 270 */               var9 = var16;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 276 */       if (var7 != null)
/*     */       {
/* 278 */         var6 = new MovingObjectPosition(var7);
/*     */       }
/*     */       
/* 281 */       if (var6 != null && var6.entityHit != null && var6.entityHit instanceof EntityPlayer) {
/*     */         
/* 283 */         EntityPlayer var21 = (EntityPlayer)var6.entityHit;
/*     */         
/* 285 */         if (var21.capabilities.disableDamage || (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(var21)))
/*     */         {
/* 287 */           var6 = null;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 295 */       if (var6 != null)
/*     */       {
/* 297 */         if (var6.entityHit != null) {
/*     */           DamageSource var26;
/* 299 */           float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/* 300 */           int var24 = MathHelper.ceiling_double_int(f * this.damage);
/*     */           
/* 302 */           if (getIsCritical())
/*     */           {
/* 304 */             var24 += this.rand.nextInt(var24 / 2 + 2);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 309 */           if (this.shootingEntity == null) {
/*     */             
/* 311 */             var26 = DamageSource.causeArrowDamage(this, this);
/*     */           }
/*     */           else {
/*     */             
/* 315 */             var26 = DamageSource.causeArrowDamage(this, this.shootingEntity);
/*     */           } 
/*     */           
/* 318 */           if (isBurning() && !(var6.entityHit instanceof net.minecraft.entity.monster.EntityEnderman))
/*     */           {
/* 320 */             var6.entityHit.setFire(5);
/*     */           }
/*     */           
/* 323 */           if (var6.entityHit.attackEntityFrom(var26, var24))
/*     */           {
/* 325 */             if (var6.entityHit instanceof EntityLivingBase) {
/*     */               
/* 327 */               EntityLivingBase var27 = (EntityLivingBase)var6.entityHit;
/*     */               
/* 329 */               if (!this.worldObj.isRemote)
/*     */               {
/* 331 */                 var27.setArrowCountInEntity(var27.getArrowCountInEntity() + 1);
/*     */               }
/*     */               
/* 334 */               if (this.knockbackStrength > 0) {
/*     */                 
/* 336 */                 float var29 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*     */                 
/* 338 */                 if (var29 > 0.0F)
/*     */                 {
/* 340 */                   var6.entityHit.addVelocity(this.motionX * this.knockbackStrength * 0.6000000238418579D / var29, 0.1D, this.motionZ * this.knockbackStrength * 0.6000000238418579D / var29);
/*     */                 }
/*     */               } 
/*     */               
/* 344 */               if (this.shootingEntity instanceof EntityLivingBase) {
/*     */                 
/* 346 */                 EnchantmentHelper.func_151384_a(var27, this.shootingEntity);
/* 347 */                 EnchantmentHelper.func_151385_b((EntityLivingBase)this.shootingEntity, (Entity)var27);
/*     */               } 
/*     */               
/* 350 */               if (this.shootingEntity != null && var6.entityHit != this.shootingEntity && var6.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
/*     */               {
/* 352 */                 ((EntityPlayerMP)this.shootingEntity).playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(6, 0.0F));
/*     */               }
/*     */             } 
/*     */             
/* 356 */             playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
/*     */             
/* 358 */             if (!(var6.entityHit instanceof net.minecraft.entity.monster.EntityEnderman))
/*     */             {
/* 360 */               setDead();
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 365 */             this.motionX *= -0.10000000149011612D;
/* 366 */             this.motionY *= -0.10000000149011612D;
/* 367 */             this.motionZ *= -0.10000000149011612D;
/* 368 */             this.rotationYaw += 180.0F;
/* 369 */             this.prevRotationYaw += 180.0F;
/* 370 */             this.ticksInAir = 0;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 375 */           BlockPos var23 = var6.func_178782_a();
/* 376 */           this.field_145791_d = var23.getX();
/* 377 */           this.field_145792_e = var23.getY();
/* 378 */           this.field_145789_f = var23.getZ();
/* 379 */           var2 = this.worldObj.getBlockState(var23);
/* 380 */           this.field_145790_g = var2.getBlock();
/* 381 */           this.inData = this.field_145790_g.getMetaFromState(var2);
/* 382 */           this.motionX = (float)(var6.hitVec.xCoord - this.posX);
/* 383 */           this.motionY = (float)(var6.hitVec.yCoord - this.posY);
/* 384 */           this.motionZ = (float)(var6.hitVec.zCoord - this.posZ);
/* 385 */           float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
/* 386 */           this.posX -= this.motionX / f * 0.05000000074505806D;
/* 387 */           this.posY -= this.motionY / f * 0.05000000074505806D;
/* 388 */           this.posZ -= this.motionZ / f * 0.05000000074505806D;
/* 389 */           playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
/* 390 */           this.inGround = true;
/* 391 */           this.arrowShake = 7;
/* 392 */           setIsCritical(false);
/*     */           
/* 394 */           if (this.field_145790_g.getMaterial() != Material.air)
/*     */           {
/* 396 */             this.field_145790_g.onEntityCollidedWithBlock(this.worldObj, var23, var2, this);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 401 */       if (getIsCritical())
/*     */       {
/* 403 */         for (var11 = 0; var11 < 4; var11++)
/*     */         {
/* 405 */           this.worldObj.spawnParticle(EnumParticleTypes.CRIT, this.posX + this.motionX * var11 / 4.0D, this.posY + this.motionY * var11 / 4.0D, this.posZ + this.motionZ * var11 / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ, new int[0]);
/*     */         }
/*     */       }
/*     */       
/* 409 */       this.posX += this.motionX;
/* 410 */       this.posY += this.motionY;
/* 411 */       this.posZ += this.motionZ;
/* 412 */       float var22 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 413 */       this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*     */       
/* 415 */       for (this.rotationPitch = (float)(Math.atan2(this.motionY, var22) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 420 */       while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */       {
/* 422 */         this.prevRotationPitch += 360.0F;
/*     */       }
/*     */       
/* 425 */       while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */       {
/* 427 */         this.prevRotationYaw -= 360.0F;
/*     */       }
/*     */       
/* 430 */       while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */       {
/* 432 */         this.prevRotationYaw += 360.0F;
/*     */       }
/*     */       
/* 435 */       this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 436 */       this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/* 437 */       float var25 = 0.99F;
/* 438 */       float var13 = 0.05F;
/*     */       
/* 440 */       if (isInWater()) {
/*     */         
/* 442 */         for (int var28 = 0; var28 < 4; var28++) {
/*     */           
/* 444 */           float var29 = 0.25F;
/* 445 */           this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * var29, this.posY - this.motionY * var29, this.posZ - this.motionZ * var29, this.motionX, this.motionY, this.motionZ, new int[0]);
/*     */         } 
/*     */         
/* 448 */         var25 = 0.6F;
/*     */       } 
/*     */       
/* 451 */       if (isWet())
/*     */       {
/* 453 */         extinguish();
/*     */       }
/*     */       
/* 456 */       this.motionX *= var25;
/* 457 */       this.motionY *= var25;
/* 458 */       this.motionZ *= var25;
/* 459 */       this.motionY -= var13;
/* 460 */       setPosition(this.posX, this.posY, this.posZ);
/* 461 */       doBlockCollisions();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 470 */     tagCompound.setShort("xTile", (short)this.field_145791_d);
/* 471 */     tagCompound.setShort("yTile", (short)this.field_145792_e);
/* 472 */     tagCompound.setShort("zTile", (short)this.field_145789_f);
/* 473 */     tagCompound.setShort("life", (short)this.ticksInGround);
/* 474 */     ResourceLocation var2 = (ResourceLocation)Block.blockRegistry.getNameForObject(this.field_145790_g);
/* 475 */     tagCompound.setString("inTile", (var2 == null) ? "" : var2.toString());
/* 476 */     tagCompound.setByte("inData", (byte)this.inData);
/* 477 */     tagCompound.setByte("shake", (byte)this.arrowShake);
/* 478 */     tagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/* 479 */     tagCompound.setByte("pickup", (byte)this.canBePickedUp);
/* 480 */     tagCompound.setDouble("damage", this.damage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 488 */     this.field_145791_d = tagCompund.getShort("xTile");
/* 489 */     this.field_145792_e = tagCompund.getShort("yTile");
/* 490 */     this.field_145789_f = tagCompund.getShort("zTile");
/* 491 */     this.ticksInGround = tagCompund.getShort("life");
/*     */     
/* 493 */     if (tagCompund.hasKey("inTile", 8)) {
/*     */       
/* 495 */       this.field_145790_g = Block.getBlockFromName(tagCompund.getString("inTile"));
/*     */     }
/*     */     else {
/*     */       
/* 499 */       this.field_145790_g = Block.getBlockById(tagCompund.getByte("inTile") & 0xFF);
/*     */     } 
/*     */     
/* 502 */     this.inData = tagCompund.getByte("inData") & 0xFF;
/* 503 */     this.arrowShake = tagCompund.getByte("shake") & 0xFF;
/* 504 */     this.inGround = (tagCompund.getByte("inGround") == 1);
/*     */     
/* 506 */     if (tagCompund.hasKey("damage", 99))
/*     */     {
/* 508 */       this.damage = tagCompund.getDouble("damage");
/*     */     }
/*     */     
/* 511 */     if (tagCompund.hasKey("pickup", 99)) {
/*     */       
/* 513 */       this.canBePickedUp = tagCompund.getByte("pickup");
/*     */     }
/* 515 */     else if (tagCompund.hasKey("player", 99)) {
/*     */       
/* 517 */       this.canBePickedUp = tagCompund.getBoolean("player") ? 1 : 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCollideWithPlayer(EntityPlayer entityIn) {
/* 526 */     if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0) {
/*     */       
/* 528 */       boolean var2 = !(this.canBePickedUp != 1 && (this.canBePickedUp != 2 || !entityIn.capabilities.isCreativeMode));
/*     */       
/* 530 */       if (this.canBePickedUp == 1 && !entityIn.inventory.addItemStackToInventory(new ItemStack(Items.arrow, 1)))
/*     */       {
/* 532 */         var2 = false;
/*     */       }
/*     */       
/* 535 */       if (var2) {
/*     */         
/* 537 */         playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 538 */         entityIn.onItemPickup(this, 1);
/* 539 */         setDead();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 550 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamage(double p_70239_1_) {
/* 555 */     this.damage = p_70239_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDamage() {
/* 560 */     return this.damage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKnockbackStrength(int p_70240_1_) {
/* 568 */     this.knockbackStrength = p_70240_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 576 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsCritical(boolean p_70243_1_) {
/* 584 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 586 */     if (p_70243_1_) {
/*     */       
/* 588 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else {
/*     */       
/* 592 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getIsCritical() {
/* 601 */     byte var1 = this.dataWatcher.getWatchableObjectByte(16);
/* 602 */     return ((var1 & 0x1) != 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntityArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */