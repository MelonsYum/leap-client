/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityXPOrb
/*     */   extends Entity
/*     */ {
/*     */   public int xpColor;
/*     */   public int xpOrbAge;
/*     */   public int field_70532_c;
/*  24 */   private int xpOrbHealth = 5;
/*     */ 
/*     */   
/*     */   private int xpValue;
/*     */ 
/*     */   
/*     */   private EntityPlayer closestPlayer;
/*     */   
/*     */   private int xpTargetColor;
/*     */   
/*     */   private static final String __OBFID = "CL_00001544";
/*     */ 
/*     */   
/*     */   public EntityXPOrb(World worldIn, double p_i1585_2_, double p_i1585_4_, double p_i1585_6_, int p_i1585_8_) {
/*  38 */     super(worldIn);
/*  39 */     setSize(0.5F, 0.5F);
/*  40 */     setPosition(p_i1585_2_, p_i1585_4_, p_i1585_6_);
/*  41 */     this.rotationYaw = (float)(Math.random() * 360.0D);
/*  42 */     this.motionX = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
/*  43 */     this.motionY = ((float)(Math.random() * 0.2D) * 2.0F);
/*  44 */     this.motionZ = ((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D) * 2.0F);
/*  45 */     this.xpValue = p_i1585_8_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityXPOrb(World worldIn) {
/*  59 */     super(worldIn);
/*  60 */     setSize(0.25F, 0.25F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/*  67 */     float var2 = 0.5F;
/*  68 */     var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
/*  69 */     int var3 = super.getBrightnessForRender(p_70070_1_);
/*  70 */     int var4 = var3 & 0xFF;
/*  71 */     int var5 = var3 >> 16 & 0xFF;
/*  72 */     var4 += (int)(var2 * 15.0F * 16.0F);
/*     */     
/*  74 */     if (var4 > 240)
/*     */     {
/*  76 */       var4 = 240;
/*     */     }
/*     */     
/*  79 */     return var4 | var5 << 16;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  87 */     super.onUpdate();
/*     */     
/*  89 */     if (this.field_70532_c > 0)
/*     */     {
/*  91 */       this.field_70532_c--;
/*     */     }
/*     */     
/*  94 */     this.prevPosX = this.posX;
/*  95 */     this.prevPosY = this.posY;
/*  96 */     this.prevPosZ = this.posZ;
/*  97 */     this.motionY -= 0.029999999329447746D;
/*     */     
/*  99 */     if (this.worldObj.getBlockState(new BlockPos(this)).getBlock().getMaterial() == Material.lava) {
/*     */       
/* 101 */       this.motionY = 0.20000000298023224D;
/* 102 */       this.motionX = ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/* 103 */       this.motionZ = ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/* 104 */       playSound("random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
/*     */     } 
/*     */     
/* 107 */     pushOutOfBlocks(this.posX, ((getEntityBoundingBox()).minY + (getEntityBoundingBox()).maxY) / 2.0D, this.posZ);
/* 108 */     double var1 = 8.0D;
/*     */     
/* 110 */     if (this.xpTargetColor < this.xpColor - 20 + getEntityId() % 100) {
/*     */       
/* 112 */       if (this.closestPlayer == null || this.closestPlayer.getDistanceSqToEntity(this) > var1 * var1)
/*     */       {
/* 114 */         this.closestPlayer = this.worldObj.getClosestPlayerToEntity(this, var1);
/*     */       }
/*     */       
/* 117 */       this.xpTargetColor = this.xpColor;
/*     */     } 
/*     */     
/* 120 */     if (this.closestPlayer != null && this.closestPlayer.func_175149_v())
/*     */     {
/* 122 */       this.closestPlayer = null;
/*     */     }
/*     */     
/* 125 */     if (this.closestPlayer != null) {
/*     */       
/* 127 */       double var3 = (this.closestPlayer.posX - this.posX) / var1;
/* 128 */       double var5 = (this.closestPlayer.posY + this.closestPlayer.getEyeHeight() - this.posY) / var1;
/* 129 */       double var7 = (this.closestPlayer.posZ - this.posZ) / var1;
/* 130 */       double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
/* 131 */       double var11 = 1.0D - var9;
/*     */       
/* 133 */       if (var11 > 0.0D) {
/*     */         
/* 135 */         var11 *= var11;
/* 136 */         this.motionX += var3 / var9 * var11 * 0.1D;
/* 137 */         this.motionY += var5 / var9 * var11 * 0.1D;
/* 138 */         this.motionZ += var7 / var9 * var11 * 0.1D;
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/* 143 */     float var13 = 0.98F;
/*     */     
/* 145 */     if (this.onGround)
/*     */     {
/* 147 */       var13 = (this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double((getEntityBoundingBox()).minY) - 1, MathHelper.floor_double(this.posZ))).getBlock()).slipperiness * 0.98F;
/*     */     }
/*     */     
/* 150 */     this.motionX *= var13;
/* 151 */     this.motionY *= 0.9800000190734863D;
/* 152 */     this.motionZ *= var13;
/*     */     
/* 154 */     if (this.onGround)
/*     */     {
/* 156 */       this.motionY *= -0.8999999761581421D;
/*     */     }
/*     */     
/* 159 */     this.xpColor++;
/* 160 */     this.xpOrbAge++;
/*     */     
/* 162 */     if (this.xpOrbAge >= 6000)
/*     */     {
/* 164 */       setDead();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleWaterMovement() {
/* 173 */     return this.worldObj.handleMaterialAcceleration(getEntityBoundingBox(), Material.water, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dealFireDamage(int amount) {
/* 182 */     attackEntityFrom(DamageSource.inFire, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 190 */     if (func_180431_b(source))
/*     */     {
/* 192 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 196 */     setBeenAttacked();
/* 197 */     this.xpOrbHealth = (int)(this.xpOrbHealth - amount);
/*     */     
/* 199 */     if (this.xpOrbHealth <= 0)
/*     */     {
/* 201 */       setDead();
/*     */     }
/*     */     
/* 204 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 213 */     tagCompound.setShort("Health", (short)(byte)this.xpOrbHealth);
/* 214 */     tagCompound.setShort("Age", (short)this.xpOrbAge);
/* 215 */     tagCompound.setShort("Value", (short)this.xpValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 223 */     this.xpOrbHealth = tagCompund.getShort("Health") & 0xFF;
/* 224 */     this.xpOrbAge = tagCompund.getShort("Age");
/* 225 */     this.xpValue = tagCompund.getShort("Value");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCollideWithPlayer(EntityPlayer entityIn) {
/* 233 */     if (!this.worldObj.isRemote)
/*     */     {
/* 235 */       if (this.field_70532_c == 0 && entityIn.xpCooldown == 0) {
/*     */         
/* 237 */         entityIn.xpCooldown = 2;
/* 238 */         this.worldObj.playSoundAtEntity((Entity)entityIn, "random.orb", 0.1F, 0.5F * ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.8F));
/* 239 */         entityIn.onItemPickup(this, 1);
/* 240 */         entityIn.addExperience(this.xpValue);
/* 241 */         setDead();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXpValue() {
/* 251 */     return this.xpValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTextureByXP() {
/* 260 */     return (this.xpValue >= 2477) ? 10 : ((this.xpValue >= 1237) ? 9 : ((this.xpValue >= 617) ? 8 : ((this.xpValue >= 307) ? 7 : ((this.xpValue >= 149) ? 6 : ((this.xpValue >= 73) ? 5 : ((this.xpValue >= 37) ? 4 : ((this.xpValue >= 17) ? 3 : ((this.xpValue >= 7) ? 2 : ((this.xpValue >= 3) ? 1 : 0)))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getXPSplit(int p_70527_0_) {
/* 268 */     return (p_70527_0_ >= 2477) ? 2477 : ((p_70527_0_ >= 1237) ? 1237 : ((p_70527_0_ >= 617) ? 617 : ((p_70527_0_ >= 307) ? 307 : ((p_70527_0_ >= 149) ? 149 : ((p_70527_0_ >= 73) ? 73 : ((p_70527_0_ >= 37) ? 37 : ((p_70527_0_ >= 17) ? 17 : ((p_70527_0_ >= 7) ? 7 : ((p_70527_0_ >= 3) ? 3 : 1)))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 276 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityXPOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */