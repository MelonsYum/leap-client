/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityEnderEye
/*     */   extends Entity
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   private int despawnTimer;
/*     */   private boolean shatterOrDrop;
/*     */   private static final String __OBFID = "CL_00001716";
/*     */   
/*     */   public EntityEnderEye(World worldIn) {
/*  28 */     super(worldIn);
/*  29 */     setSize(0.25F, 0.25F);
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
/*  40 */     double var3 = getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
/*  41 */     var3 *= 64.0D;
/*  42 */     return (distance < var3 * var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityEnderEye(World worldIn, double p_i1758_2_, double p_i1758_4_, double p_i1758_6_) {
/*  47 */     super(worldIn);
/*  48 */     this.despawnTimer = 0;
/*  49 */     setSize(0.25F, 0.25F);
/*  50 */     setPosition(p_i1758_2_, p_i1758_4_, p_i1758_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180465_a(BlockPos p_180465_1_) {
/*  55 */     double var2 = p_180465_1_.getX();
/*  56 */     int var4 = p_180465_1_.getY();
/*  57 */     double var5 = p_180465_1_.getZ();
/*  58 */     double var7 = var2 - this.posX;
/*  59 */     double var9 = var5 - this.posZ;
/*  60 */     float var11 = MathHelper.sqrt_double(var7 * var7 + var9 * var9);
/*     */     
/*  62 */     if (var11 > 12.0F) {
/*     */       
/*  64 */       this.targetX = this.posX + var7 / var11 * 12.0D;
/*  65 */       this.targetZ = this.posZ + var9 / var11 * 12.0D;
/*  66 */       this.targetY = this.posY + 8.0D;
/*     */     }
/*     */     else {
/*     */       
/*  70 */       this.targetX = var2;
/*  71 */       this.targetY = var4;
/*  72 */       this.targetZ = var5;
/*     */     } 
/*     */     
/*  75 */     this.despawnTimer = 0;
/*  76 */     this.shatterOrDrop = (this.rand.nextInt(5) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double x, double y, double z) {
/*  84 */     this.motionX = x;
/*  85 */     this.motionY = y;
/*  86 */     this.motionZ = z;
/*     */     
/*  88 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*     */       
/*  90 */       float var7 = MathHelper.sqrt_double(x * x + z * z);
/*  91 */       this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(x, z) * 180.0D / Math.PI);
/*  92 */       this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(y, var7) * 180.0D / Math.PI);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 101 */     this.lastTickPosX = this.posX;
/* 102 */     this.lastTickPosY = this.posY;
/* 103 */     this.lastTickPosZ = this.posZ;
/* 104 */     super.onUpdate();
/* 105 */     this.posX += this.motionX;
/* 106 */     this.posY += this.motionY;
/* 107 */     this.posZ += this.motionZ;
/* 108 */     float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 109 */     this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*     */     
/* 111 */     for (this.rotationPitch = (float)(Math.atan2(this.motionY, var1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */     {
/* 118 */       this.prevRotationPitch += 360.0F;
/*     */     }
/*     */     
/* 121 */     while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */     {
/* 123 */       this.prevRotationYaw -= 360.0F;
/*     */     }
/*     */     
/* 126 */     while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */     {
/* 128 */       this.prevRotationYaw += 360.0F;
/*     */     }
/*     */     
/* 131 */     this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 132 */     this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/*     */     
/* 134 */     if (!this.worldObj.isRemote) {
/*     */       
/* 136 */       double var2 = this.targetX - this.posX;
/* 137 */       double var4 = this.targetZ - this.posZ;
/* 138 */       float var6 = (float)Math.sqrt(var2 * var2 + var4 * var4);
/* 139 */       float var7 = (float)Math.atan2(var4, var2);
/* 140 */       double var8 = var1 + (var6 - var1) * 0.0025D;
/*     */       
/* 142 */       if (var6 < 1.0F) {
/*     */         
/* 144 */         var8 *= 0.8D;
/* 145 */         this.motionY *= 0.8D;
/*     */       } 
/*     */       
/* 148 */       this.motionX = Math.cos(var7) * var8;
/* 149 */       this.motionZ = Math.sin(var7) * var8;
/*     */       
/* 151 */       if (this.posY < this.targetY) {
/*     */         
/* 153 */         this.motionY += (1.0D - this.motionY) * 0.014999999664723873D;
/*     */       }
/*     */       else {
/*     */         
/* 157 */         this.motionY += (-1.0D - this.motionY) * 0.014999999664723873D;
/*     */       } 
/*     */     } 
/*     */     
/* 161 */     float var10 = 0.25F;
/*     */     
/* 163 */     if (isInWater()) {
/*     */       
/* 165 */       for (int var3 = 0; var3 < 4; var3++)
/*     */       {
/* 167 */         this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * var10, this.posY - this.motionY * var10, this.posZ - this.motionZ * var10, this.motionX, this.motionY, this.motionZ, new int[0]);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 172 */       this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, this.posX - this.motionX * var10 + this.rand.nextDouble() * 0.6D - 0.3D, this.posY - this.motionY * var10 - 0.5D, this.posZ - this.motionZ * var10 + this.rand.nextDouble() * 0.6D - 0.3D, this.motionX, this.motionY, this.motionZ, new int[0]);
/*     */     } 
/*     */     
/* 175 */     if (!this.worldObj.isRemote) {
/*     */       
/* 177 */       setPosition(this.posX, this.posY, this.posZ);
/* 178 */       this.despawnTimer++;
/*     */       
/* 180 */       if (this.despawnTimer > 80 && !this.worldObj.isRemote) {
/*     */         
/* 182 */         setDead();
/*     */         
/* 184 */         if (this.shatterOrDrop) {
/*     */           
/* 186 */           this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Items.ender_eye)));
/*     */         }
/*     */         else {
/*     */           
/* 190 */           this.worldObj.playAuxSFX(2003, new BlockPos(this), 0);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float p_70013_1_) {
/* 211 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/* 216 */     return 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 224 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityEnderEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */