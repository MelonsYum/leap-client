/*     */ package net.minecraft.entity.projectile;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IProjectile;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityThrowable
/*     */   extends Entity
/*     */   implements IProjectile {
/*  22 */   private int xTile = -1;
/*  23 */   private int yTile = -1;
/*  24 */   private int zTile = -1;
/*     */   
/*     */   private Block field_174853_f;
/*     */   
/*     */   protected boolean field_174854_a;
/*     */   
/*     */   public int throwableShake;
/*     */   private EntityLivingBase thrower;
/*     */   private String throwerName;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*     */   private static final String __OBFID = "CL_00001723";
/*     */   
/*     */   public EntityThrowable(World worldIn) {
/*  38 */     super(worldIn);
/*  39 */     setSize(0.25F, 0.25F);
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
/*  50 */     double var3 = getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
/*  51 */     var3 *= 64.0D;
/*  52 */     return (distance < var3 * var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityThrowable(World worldIn, EntityLivingBase p_i1777_2_) {
/*  57 */     super(worldIn);
/*  58 */     this.thrower = p_i1777_2_;
/*  59 */     setSize(0.25F, 0.25F);
/*  60 */     setLocationAndAngles(p_i1777_2_.posX, p_i1777_2_.posY + p_i1777_2_.getEyeHeight(), p_i1777_2_.posZ, p_i1777_2_.rotationYaw, p_i1777_2_.rotationPitch);
/*  61 */     this.posX -= (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/*  62 */     this.posY -= 0.10000000149011612D;
/*  63 */     this.posZ -= (MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/*  64 */     setPosition(this.posX, this.posY, this.posZ);
/*  65 */     float var3 = 0.4F;
/*  66 */     this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/*  67 */     this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/*  68 */     this.motionY = (-MathHelper.sin((this.rotationPitch + func_70183_g()) / 180.0F * 3.1415927F) * var3);
/*  69 */     setThrowableHeading(this.motionX, this.motionY, this.motionZ, func_70182_d(), 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityThrowable(World worldIn, double p_i1778_2_, double p_i1778_4_, double p_i1778_6_) {
/*  74 */     super(worldIn);
/*  75 */     this.ticksInGround = 0;
/*  76 */     setSize(0.25F, 0.25F);
/*  77 */     setPosition(p_i1778_2_, p_i1778_4_, p_i1778_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70182_d() {
/*  82 */     return 1.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70183_g() {
/*  87 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_) {
/*  95 */     float var9 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
/*  96 */     p_70186_1_ /= var9;
/*  97 */     p_70186_3_ /= var9;
/*  98 */     p_70186_5_ /= var9;
/*  99 */     p_70186_1_ += this.rand.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/* 100 */     p_70186_3_ += this.rand.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/* 101 */     p_70186_5_ += this.rand.nextGaussian() * 0.007499999832361937D * p_70186_8_;
/* 102 */     p_70186_1_ *= p_70186_7_;
/* 103 */     p_70186_3_ *= p_70186_7_;
/* 104 */     p_70186_5_ *= p_70186_7_;
/* 105 */     this.motionX = p_70186_1_;
/* 106 */     this.motionY = p_70186_3_;
/* 107 */     this.motionZ = p_70186_5_;
/* 108 */     float var10 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
/* 109 */     this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
/* 110 */     this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70186_3_, var10) * 180.0D / Math.PI);
/* 111 */     this.ticksInGround = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double x, double y, double z) {
/* 119 */     this.motionX = x;
/* 120 */     this.motionY = y;
/* 121 */     this.motionZ = z;
/*     */     
/* 123 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*     */       
/* 125 */       float var7 = MathHelper.sqrt_double(x * x + z * z);
/* 126 */       this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
/* 127 */       this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, var7) * 180.0D / Math.PI);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 136 */     this.lastTickPosX = this.posX;
/* 137 */     this.lastTickPosY = this.posY;
/* 138 */     this.lastTickPosZ = this.posZ;
/* 139 */     super.onUpdate();
/*     */     
/* 141 */     if (this.throwableShake > 0)
/*     */     {
/* 143 */       this.throwableShake--;
/*     */     }
/*     */     
/* 146 */     if (this.field_174854_a) {
/*     */       
/* 148 */       if (this.worldObj.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.field_174853_f) {
/*     */         
/* 150 */         this.ticksInGround++;
/*     */         
/* 152 */         if (this.ticksInGround == 1200)
/*     */         {
/* 154 */           setDead();
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 160 */       this.field_174854_a = false;
/* 161 */       this.motionX *= (this.rand.nextFloat() * 0.2F);
/* 162 */       this.motionY *= (this.rand.nextFloat() * 0.2F);
/* 163 */       this.motionZ *= (this.rand.nextFloat() * 0.2F);
/* 164 */       this.ticksInGround = 0;
/* 165 */       this.ticksInAir = 0;
/*     */     }
/*     */     else {
/*     */       
/* 169 */       this.ticksInAir++;
/*     */     } 
/*     */     
/* 172 */     Vec3 var1 = new Vec3(this.posX, this.posY, this.posZ);
/* 173 */     Vec3 var2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/* 174 */     MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var1, var2);
/* 175 */     var1 = new Vec3(this.posX, this.posY, this.posZ);
/* 176 */     var2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*     */     
/* 178 */     if (var3 != null)
/*     */     {
/* 180 */       var2 = new Vec3(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
/*     */     }
/*     */     
/* 183 */     if (!this.worldObj.isRemote) {
/*     */       
/* 185 */       Entity var4 = null;
/* 186 */       List<Entity> var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
/* 187 */       double var6 = 0.0D;
/* 188 */       EntityLivingBase var8 = getThrower();
/*     */       
/* 190 */       for (int var9 = 0; var9 < var5.size(); var9++) {
/*     */         
/* 192 */         Entity var10 = var5.get(var9);
/*     */         
/* 194 */         if (var10.canBeCollidedWith() && (var10 != var8 || this.ticksInAir >= 5)) {
/*     */           
/* 196 */           float var11 = 0.3F;
/* 197 */           AxisAlignedBB var12 = var10.getEntityBoundingBox().expand(var11, var11, var11);
/* 198 */           MovingObjectPosition var13 = var12.calculateIntercept(var1, var2);
/*     */           
/* 200 */           if (var13 != null) {
/*     */             
/* 202 */             double var14 = var1.distanceTo(var13.hitVec);
/*     */             
/* 204 */             if (var14 < var6 || var6 == 0.0D) {
/*     */               
/* 206 */               var4 = var10;
/* 207 */               var6 = var14;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 213 */       if (var4 != null)
/*     */       {
/* 215 */         var3 = new MovingObjectPosition(var4);
/*     */       }
/*     */     } 
/*     */     
/* 219 */     if (var3 != null)
/*     */     {
/* 221 */       if (var3.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && this.worldObj.getBlockState(var3.func_178782_a()).getBlock() == Blocks.portal) {
/*     */         
/* 223 */         setInPortal();
/*     */       }
/*     */       else {
/*     */         
/* 227 */         onImpact(var3);
/*     */       } 
/*     */     }
/*     */     
/* 231 */     this.posX += this.motionX;
/* 232 */     this.posY += this.motionY;
/* 233 */     this.posZ += this.motionZ;
/* 234 */     float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 235 */     this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*     */     
/* 237 */     for (this.rotationPitch = (float)(Math.atan2(this.motionY, var16) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 242 */     while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */     {
/* 244 */       this.prevRotationPitch += 360.0F;
/*     */     }
/*     */     
/* 247 */     while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */     {
/* 249 */       this.prevRotationYaw -= 360.0F;
/*     */     }
/*     */     
/* 252 */     while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */     {
/* 254 */       this.prevRotationYaw += 360.0F;
/*     */     }
/*     */     
/* 257 */     this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 258 */     this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/* 259 */     float var17 = 0.99F;
/* 260 */     float var18 = getGravityVelocity();
/*     */     
/* 262 */     if (isInWater()) {
/*     */       
/* 264 */       for (int var7 = 0; var7 < 4; var7++) {
/*     */         
/* 266 */         float var19 = 0.25F;
/* 267 */         this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * var19, this.posY - this.motionY * var19, this.posZ - this.motionZ * var19, this.motionX, this.motionY, this.motionZ, new int[0]);
/*     */       } 
/*     */       
/* 270 */       var17 = 0.8F;
/*     */     } 
/*     */     
/* 273 */     this.motionX *= var17;
/* 274 */     this.motionY *= var17;
/* 275 */     this.motionZ *= var17;
/* 276 */     this.motionY -= var18;
/* 277 */     setPosition(this.posX, this.posY, this.posZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getGravityVelocity() {
/* 285 */     return 0.03F;
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
/* 298 */     tagCompound.setShort("xTile", (short)this.xTile);
/* 299 */     tagCompound.setShort("yTile", (short)this.yTile);
/* 300 */     tagCompound.setShort("zTile", (short)this.zTile);
/* 301 */     ResourceLocation var2 = (ResourceLocation)Block.blockRegistry.getNameForObject(this.field_174853_f);
/* 302 */     tagCompound.setString("inTile", (var2 == null) ? "" : var2.toString());
/* 303 */     tagCompound.setByte("shake", (byte)this.throwableShake);
/* 304 */     tagCompound.setByte("inGround", (byte)(this.field_174854_a ? 1 : 0));
/*     */     
/* 306 */     if ((this.throwerName == null || this.throwerName.length() == 0) && this.thrower instanceof net.minecraft.entity.player.EntityPlayer)
/*     */     {
/* 308 */       this.throwerName = this.thrower.getName();
/*     */     }
/*     */     
/* 311 */     tagCompound.setString("ownerName", (this.throwerName == null) ? "" : this.throwerName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 319 */     this.xTile = tagCompund.getShort("xTile");
/* 320 */     this.yTile = tagCompund.getShort("yTile");
/* 321 */     this.zTile = tagCompund.getShort("zTile");
/*     */     
/* 323 */     if (tagCompund.hasKey("inTile", 8)) {
/*     */       
/* 325 */       this.field_174853_f = Block.getBlockFromName(tagCompund.getString("inTile"));
/*     */     }
/*     */     else {
/*     */       
/* 329 */       this.field_174853_f = Block.getBlockById(tagCompund.getByte("inTile") & 0xFF);
/*     */     } 
/*     */     
/* 332 */     this.throwableShake = tagCompund.getByte("shake") & 0xFF;
/* 333 */     this.field_174854_a = (tagCompund.getByte("inGround") == 1);
/* 334 */     this.throwerName = tagCompund.getString("ownerName");
/*     */     
/* 336 */     if (this.throwerName != null && this.throwerName.length() == 0)
/*     */     {
/* 338 */       this.throwerName = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingBase getThrower() {
/* 344 */     if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0)
/*     */     {
/* 346 */       this.thrower = (EntityLivingBase)this.worldObj.getPlayerEntityByName(this.throwerName);
/*     */     }
/*     */     
/* 349 */     return this.thrower;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntityThrowable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */