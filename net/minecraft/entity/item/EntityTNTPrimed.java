/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class EntityTNTPrimed
/*     */   extends Entity
/*     */ {
/*     */   public int fuse;
/*     */   private EntityLivingBase tntPlacedBy;
/*     */   private static final String __OBFID = "CL_00001681";
/*     */   
/*     */   public EntityTNTPrimed(World worldIn) {
/*  18 */     super(worldIn);
/*  19 */     this.preventEntitySpawning = true;
/*  20 */     setSize(0.98F, 0.98F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityTNTPrimed(World worldIn, double p_i1730_2_, double p_i1730_4_, double p_i1730_6_, EntityLivingBase p_i1730_8_) {
/*  25 */     this(worldIn);
/*  26 */     setPosition(p_i1730_2_, p_i1730_4_, p_i1730_6_);
/*  27 */     float var9 = (float)(Math.random() * Math.PI * 2.0D);
/*  28 */     this.motionX = (-((float)Math.sin(var9)) * 0.02F);
/*  29 */     this.motionY = 0.20000000298023224D;
/*  30 */     this.motionZ = (-((float)Math.cos(var9)) * 0.02F);
/*  31 */     this.fuse = 80;
/*  32 */     this.prevPosX = p_i1730_2_;
/*  33 */     this.prevPosY = p_i1730_4_;
/*  34 */     this.prevPosZ = p_i1730_6_;
/*  35 */     this.tntPlacedBy = p_i1730_8_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/*  54 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  62 */     this.prevPosX = this.posX;
/*  63 */     this.prevPosY = this.posY;
/*  64 */     this.prevPosZ = this.posZ;
/*  65 */     this.motionY -= 0.03999999910593033D;
/*  66 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*  67 */     this.motionX *= 0.9800000190734863D;
/*  68 */     this.motionY *= 0.9800000190734863D;
/*  69 */     this.motionZ *= 0.9800000190734863D;
/*     */     
/*  71 */     if (this.onGround) {
/*     */       
/*  73 */       this.motionX *= 0.699999988079071D;
/*  74 */       this.motionZ *= 0.699999988079071D;
/*  75 */       this.motionY *= -0.5D;
/*     */     } 
/*     */     
/*  78 */     if (this.fuse-- <= 0) {
/*     */       
/*  80 */       setDead();
/*     */       
/*  82 */       if (!this.worldObj.isRemote)
/*     */       {
/*  84 */         explode();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  89 */       handleWaterMovement();
/*  90 */       this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void explode() {
/*  96 */     float var1 = 4.0F;
/*  97 */     this.worldObj.createExplosion(this, this.posX, this.posY + (this.height / 2.0F), this.posZ, var1, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 105 */     tagCompound.setByte("Fuse", (byte)this.fuse);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 113 */     this.fuse = tagCompund.getByte("Fuse");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLivingBase getTntPlacedBy() {
/* 121 */     return this.tntPlacedBy;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 126 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityTNTPrimed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */