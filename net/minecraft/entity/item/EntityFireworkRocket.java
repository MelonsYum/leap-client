/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityFireworkRocket
/*     */   extends Entity
/*     */ {
/*     */   private int fireworkAge;
/*     */   private int lifetime;
/*     */   private static final String __OBFID = "CL_00001718";
/*     */   
/*     */   public EntityFireworkRocket(World worldIn) {
/*  23 */     super(worldIn);
/*  24 */     setSize(0.25F, 0.25F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  29 */     this.dataWatcher.addObjectByDataType(8, 5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderDist(double distance) {
/*  38 */     return (distance < 4096.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFireworkRocket(World worldIn, double p_i1763_2_, double p_i1763_4_, double p_i1763_6_, ItemStack p_i1763_8_) {
/*  43 */     super(worldIn);
/*  44 */     this.fireworkAge = 0;
/*  45 */     setSize(0.25F, 0.25F);
/*  46 */     setPosition(p_i1763_2_, p_i1763_4_, p_i1763_6_);
/*  47 */     int var9 = 1;
/*     */     
/*  49 */     if (p_i1763_8_ != null && p_i1763_8_.hasTagCompound()) {
/*     */       
/*  51 */       this.dataWatcher.updateObject(8, p_i1763_8_);
/*  52 */       NBTTagCompound var10 = p_i1763_8_.getTagCompound();
/*  53 */       NBTTagCompound var11 = var10.getCompoundTag("Fireworks");
/*     */       
/*  55 */       if (var11 != null)
/*     */       {
/*  57 */         var9 += var11.getByte("Flight");
/*     */       }
/*     */     } 
/*     */     
/*  61 */     this.motionX = this.rand.nextGaussian() * 0.001D;
/*  62 */     this.motionZ = this.rand.nextGaussian() * 0.001D;
/*  63 */     this.motionY = 0.05D;
/*  64 */     this.lifetime = 10 * var9 + this.rand.nextInt(6) + this.rand.nextInt(7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double x, double y, double z) {
/*  72 */     this.motionX = x;
/*  73 */     this.motionY = y;
/*  74 */     this.motionZ = z;
/*     */     
/*  76 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*     */       
/*  78 */       float var7 = MathHelper.sqrt_double(x * x + z * z);
/*  79 */       this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
/*  80 */       this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, var7) * 180.0D / Math.PI);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  89 */     this.lastTickPosX = this.posX;
/*  90 */     this.lastTickPosY = this.posY;
/*  91 */     this.lastTickPosZ = this.posZ;
/*  92 */     super.onUpdate();
/*  93 */     this.motionX *= 1.15D;
/*  94 */     this.motionZ *= 1.15D;
/*  95 */     this.motionY += 0.04D;
/*  96 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*  97 */     float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*  98 */     this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*     */     
/* 100 */     for (this.rotationPitch = (float)(Math.atan2(this.motionY, var1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */     {
/* 107 */       this.prevRotationPitch += 360.0F;
/*     */     }
/*     */     
/* 110 */     while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */     {
/* 112 */       this.prevRotationYaw -= 360.0F;
/*     */     }
/*     */     
/* 115 */     while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */     {
/* 117 */       this.prevRotationYaw += 360.0F;
/*     */     }
/*     */     
/* 120 */     this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 121 */     this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/*     */     
/* 123 */     if (this.fireworkAge == 0 && !isSlient())
/*     */     {
/* 125 */       this.worldObj.playSoundAtEntity(this, "fireworks.launch", 3.0F, 1.0F);
/*     */     }
/*     */     
/* 128 */     this.fireworkAge++;
/*     */     
/* 130 */     if (this.worldObj.isRemote && this.fireworkAge % 2 < 2)
/*     */     {
/* 132 */       this.worldObj.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, this.posX, this.posY - 0.3D, this.posZ, this.rand.nextGaussian() * 0.05D, -this.motionY * 0.5D, this.rand.nextGaussian() * 0.05D, new int[0]);
/*     */     }
/*     */     
/* 135 */     if (!this.worldObj.isRemote && this.fireworkAge > this.lifetime) {
/*     */       
/* 137 */       this.worldObj.setEntityState(this, (byte)17);
/* 138 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 144 */     if (p_70103_1_ == 17 && this.worldObj.isRemote) {
/*     */       
/* 146 */       ItemStack var2 = this.dataWatcher.getWatchableObjectItemStack(8);
/* 147 */       NBTTagCompound var3 = null;
/*     */       
/* 149 */       if (var2 != null && var2.hasTagCompound())
/*     */       {
/* 151 */         var3 = var2.getTagCompound().getCompoundTag("Fireworks");
/*     */       }
/*     */       
/* 154 */       this.worldObj.makeFireworks(this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, var3);
/*     */     } 
/*     */     
/* 157 */     super.handleHealthUpdate(p_70103_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 165 */     tagCompound.setInteger("Life", this.fireworkAge);
/* 166 */     tagCompound.setInteger("LifeTime", this.lifetime);
/* 167 */     ItemStack var2 = this.dataWatcher.getWatchableObjectItemStack(8);
/*     */     
/* 169 */     if (var2 != null) {
/*     */       
/* 171 */       NBTTagCompound var3 = new NBTTagCompound();
/* 172 */       var2.writeToNBT(var3);
/* 173 */       tagCompound.setTag("FireworksItem", (NBTBase)var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 182 */     this.fireworkAge = tagCompund.getInteger("Life");
/* 183 */     this.lifetime = tagCompund.getInteger("LifeTime");
/* 184 */     NBTTagCompound var2 = tagCompund.getCompoundTag("FireworksItem");
/*     */     
/* 186 */     if (var2 != null) {
/*     */       
/* 188 */       ItemStack var3 = ItemStack.loadItemStackFromNBT(var2);
/*     */       
/* 190 */       if (var3 != null)
/*     */       {
/* 192 */         this.dataWatcher.updateObject(8, var3);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float p_70013_1_) {
/* 202 */     return super.getBrightness(p_70013_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/* 207 */     return super.getBrightnessForRender(p_70070_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 215 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityFireworkRocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */