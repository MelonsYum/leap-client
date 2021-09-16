/*     */ package net.minecraft.entity.projectile;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityFireball extends Entity {
/*  21 */   private int field_145795_e = -1;
/*  22 */   private int field_145793_f = -1;
/*  23 */   private int field_145794_g = -1;
/*     */   
/*     */   private Block field_145796_h;
/*     */   private boolean inGround;
/*     */   public EntityLivingBase shootingEntity;
/*     */   private int ticksAlive;
/*     */   private int ticksInAir;
/*     */   public double accelerationX;
/*     */   public double accelerationY;
/*     */   public double accelerationZ;
/*     */   private static final String __OBFID = "CL_00001717";
/*     */   
/*     */   public EntityFireball(World worldIn) {
/*  36 */     super(worldIn);
/*  37 */     setSize(1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderDist(double distance) {
/*  48 */     double var3 = getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
/*  49 */     var3 *= 64.0D;
/*  50 */     return (distance < var3 * var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFireball(World worldIn, double p_i1760_2_, double p_i1760_4_, double p_i1760_6_, double p_i1760_8_, double p_i1760_10_, double p_i1760_12_) {
/*  55 */     super(worldIn);
/*  56 */     setSize(1.0F, 1.0F);
/*  57 */     setLocationAndAngles(p_i1760_2_, p_i1760_4_, p_i1760_6_, this.rotationYaw, this.rotationPitch);
/*  58 */     setPosition(p_i1760_2_, p_i1760_4_, p_i1760_6_);
/*  59 */     double var14 = MathHelper.sqrt_double(p_i1760_8_ * p_i1760_8_ + p_i1760_10_ * p_i1760_10_ + p_i1760_12_ * p_i1760_12_);
/*  60 */     this.accelerationX = p_i1760_8_ / var14 * 0.1D;
/*  61 */     this.accelerationY = p_i1760_10_ / var14 * 0.1D;
/*  62 */     this.accelerationZ = p_i1760_12_ / var14 * 0.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFireball(World worldIn, EntityLivingBase p_i1761_2_, double p_i1761_3_, double p_i1761_5_, double p_i1761_7_) {
/*  67 */     super(worldIn);
/*  68 */     this.shootingEntity = p_i1761_2_;
/*  69 */     setSize(1.0F, 1.0F);
/*  70 */     setLocationAndAngles(p_i1761_2_.posX, p_i1761_2_.posY, p_i1761_2_.posZ, p_i1761_2_.rotationYaw, p_i1761_2_.rotationPitch);
/*  71 */     setPosition(this.posX, this.posY, this.posZ);
/*  72 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/*  73 */     p_i1761_3_ += this.rand.nextGaussian() * 0.4D;
/*  74 */     p_i1761_5_ += this.rand.nextGaussian() * 0.4D;
/*  75 */     p_i1761_7_ += this.rand.nextGaussian() * 0.4D;
/*  76 */     double var9 = MathHelper.sqrt_double(p_i1761_3_ * p_i1761_3_ + p_i1761_5_ * p_i1761_5_ + p_i1761_7_ * p_i1761_7_);
/*  77 */     this.accelerationX = p_i1761_3_ / var9 * 0.1D;
/*  78 */     this.accelerationY = p_i1761_5_ / var9 * 0.1D;
/*  79 */     this.accelerationZ = p_i1761_7_ / var9 * 0.1D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  87 */     if (!this.worldObj.isRemote && ((this.shootingEntity != null && this.shootingEntity.isDead) || !this.worldObj.isBlockLoaded(new BlockPos(this)))) {
/*     */       
/*  89 */       setDead();
/*     */     }
/*     */     else {
/*     */       
/*  93 */       super.onUpdate();
/*  94 */       setFire(1);
/*     */       
/*  96 */       if (this.inGround) {
/*     */         
/*  98 */         if (this.worldObj.getBlockState(new BlockPos(this.field_145795_e, this.field_145793_f, this.field_145794_g)).getBlock() == this.field_145796_h) {
/*     */           
/* 100 */           this.ticksAlive++;
/*     */           
/* 102 */           if (this.ticksAlive == 600)
/*     */           {
/* 104 */             setDead();
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 110 */         this.inGround = false;
/* 111 */         this.motionX *= (this.rand.nextFloat() * 0.2F);
/* 112 */         this.motionY *= (this.rand.nextFloat() * 0.2F);
/* 113 */         this.motionZ *= (this.rand.nextFloat() * 0.2F);
/* 114 */         this.ticksAlive = 0;
/* 115 */         this.ticksInAir = 0;
/*     */       }
/*     */       else {
/*     */         
/* 119 */         this.ticksInAir++;
/*     */       } 
/*     */       
/* 122 */       Vec3 var1 = new Vec3(this.posX, this.posY, this.posZ);
/* 123 */       Vec3 var2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/* 124 */       MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var1, var2);
/* 125 */       var1 = new Vec3(this.posX, this.posY, this.posZ);
/* 126 */       var2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*     */       
/* 128 */       if (var3 != null)
/*     */       {
/* 130 */         var2 = new Vec3(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
/*     */       }
/*     */       
/* 133 */       Entity var4 = null;
/* 134 */       List<Entity> var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
/* 135 */       double var6 = 0.0D;
/*     */       
/* 137 */       for (int var8 = 0; var8 < var5.size(); var8++) {
/*     */         
/* 139 */         Entity var9 = var5.get(var8);
/*     */         
/* 141 */         if (var9.canBeCollidedWith() && (!var9.isEntityEqual((Entity)this.shootingEntity) || this.ticksInAir >= 25)) {
/*     */           
/* 143 */           float var10 = 0.3F;
/* 144 */           AxisAlignedBB var11 = var9.getEntityBoundingBox().expand(var10, var10, var10);
/* 145 */           MovingObjectPosition var12 = var11.calculateIntercept(var1, var2);
/*     */           
/* 147 */           if (var12 != null) {
/*     */             
/* 149 */             double var13 = var1.distanceTo(var12.hitVec);
/*     */             
/* 151 */             if (var13 < var6 || var6 == 0.0D) {
/*     */               
/* 153 */               var4 = var9;
/* 154 */               var6 = var13;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 160 */       if (var4 != null)
/*     */       {
/* 162 */         var3 = new MovingObjectPosition(var4);
/*     */       }
/*     */       
/* 165 */       if (var3 != null)
/*     */       {
/* 167 */         onImpact(var3);
/*     */       }
/*     */       
/* 170 */       this.posX += this.motionX;
/* 171 */       this.posY += this.motionY;
/* 172 */       this.posZ += this.motionZ;
/* 173 */       float var15 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 174 */       this.rotationYaw = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;
/*     */       
/* 176 */       for (this.rotationPitch = (float)(Math.atan2(var15, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 181 */       while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */       {
/* 183 */         this.prevRotationPitch += 360.0F;
/*     */       }
/*     */       
/* 186 */       while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */       {
/* 188 */         this.prevRotationYaw -= 360.0F;
/*     */       }
/*     */       
/* 191 */       while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */       {
/* 193 */         this.prevRotationYaw += 360.0F;
/*     */       }
/*     */       
/* 196 */       this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 197 */       this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/* 198 */       float var16 = getMotionFactor();
/*     */       
/* 200 */       if (isInWater()) {
/*     */         
/* 202 */         for (int var17 = 0; var17 < 4; var17++) {
/*     */           
/* 204 */           float var18 = 0.25F;
/* 205 */           this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * var18, this.posY - this.motionY * var18, this.posZ - this.motionZ * var18, this.motionX, this.motionY, this.motionZ, new int[0]);
/*     */         } 
/*     */         
/* 208 */         var16 = 0.8F;
/*     */       } 
/*     */       
/* 211 */       this.motionX += this.accelerationX;
/* 212 */       this.motionY += this.accelerationY;
/* 213 */       this.motionZ += this.accelerationZ;
/* 214 */       this.motionX *= var16;
/* 215 */       this.motionY *= var16;
/* 216 */       this.motionZ *= var16;
/* 217 */       this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
/* 218 */       setPosition(this.posX, this.posY, this.posZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getMotionFactor() {
/* 227 */     return 0.95F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void onImpact(MovingObjectPosition paramMovingObjectPosition);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 240 */     tagCompound.setShort("xTile", (short)this.field_145795_e);
/* 241 */     tagCompound.setShort("yTile", (short)this.field_145793_f);
/* 242 */     tagCompound.setShort("zTile", (short)this.field_145794_g);
/* 243 */     ResourceLocation var2 = (ResourceLocation)Block.blockRegistry.getNameForObject(this.field_145796_h);
/* 244 */     tagCompound.setString("inTile", (var2 == null) ? "" : var2.toString());
/* 245 */     tagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/* 246 */     tagCompound.setTag("direction", (NBTBase)newDoubleNBTList(new double[] { this.motionX, this.motionY, this.motionZ }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 254 */     this.field_145795_e = tagCompund.getShort("xTile");
/* 255 */     this.field_145793_f = tagCompund.getShort("yTile");
/* 256 */     this.field_145794_g = tagCompund.getShort("zTile");
/*     */     
/* 258 */     if (tagCompund.hasKey("inTile", 8)) {
/*     */       
/* 260 */       this.field_145796_h = Block.getBlockFromName(tagCompund.getString("inTile"));
/*     */     }
/*     */     else {
/*     */       
/* 264 */       this.field_145796_h = Block.getBlockById(tagCompund.getByte("inTile") & 0xFF);
/*     */     } 
/*     */     
/* 267 */     this.inGround = (tagCompund.getByte("inGround") == 1);
/*     */     
/* 269 */     if (tagCompund.hasKey("direction", 9)) {
/*     */       
/* 271 */       NBTTagList var2 = tagCompund.getTagList("direction", 6);
/* 272 */       this.motionX = var2.getDouble(0);
/* 273 */       this.motionY = var2.getDouble(1);
/* 274 */       this.motionZ = var2.getDouble(2);
/*     */     }
/*     */     else {
/*     */       
/* 278 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 287 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCollisionBorderSize() {
/* 292 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 300 */     if (func_180431_b(source))
/*     */     {
/* 302 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 306 */     setBeenAttacked();
/*     */     
/* 308 */     if (source.getEntity() != null) {
/*     */       
/* 310 */       Vec3 var3 = source.getEntity().getLookVec();
/*     */       
/* 312 */       if (var3 != null) {
/*     */         
/* 314 */         this.motionX = var3.xCoord;
/* 315 */         this.motionY = var3.yCoord;
/* 316 */         this.motionZ = var3.zCoord;
/* 317 */         this.accelerationX = this.motionX * 0.1D;
/* 318 */         this.accelerationY = this.motionY * 0.1D;
/* 319 */         this.accelerationZ = this.motionZ * 0.1D;
/*     */       } 
/*     */       
/* 322 */       if (source.getEntity() instanceof EntityLivingBase)
/*     */       {
/* 324 */         this.shootingEntity = (EntityLivingBase)source.getEntity();
/*     */       }
/*     */       
/* 327 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 331 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float p_70013_1_) {
/* 341 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/* 346 */     return 15728880;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntityFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */