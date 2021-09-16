/*     */ package net.minecraft.entity.passive;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityBat
/*     */   extends EntityAmbientCreature
/*     */ {
/*     */   private BlockPos spawnPosition;
/*     */   private static final String __OBFID = "CL_00001637";
/*     */   
/*     */   public EntityBat(World worldIn) {
/*  22 */     super(worldIn);
/*  23 */     setSize(0.5F, 0.9F);
/*  24 */     setIsBatHanging(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  29 */     super.entityInit();
/*  30 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/*  38 */     return 0.1F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundPitch() {
/*  46 */     return super.getSoundPitch() * 0.95F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  54 */     return (getIsBatHanging() && this.rand.nextInt(4) != 0) ? null : "mob.bat.idle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  62 */     return "mob.bat.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  70 */     return "mob.bat.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void collideWithEntity(Entity p_82167_1_) {}
/*     */   
/*     */   protected void collideWithNearbyEntities() {}
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  87 */     super.applyEntityAttributes();
/*  88 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getIsBatHanging() {
/*  93 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIsBatHanging(boolean p_82236_1_) {
/*  98 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 100 */     if (p_82236_1_) {
/*     */       
/* 102 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else {
/*     */       
/* 106 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 115 */     super.onUpdate();
/*     */ 
/*     */ 
/*     */     
/* 119 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/* 120 */     this.posY = MathHelper.floor_double(this.posY) + 1.0D - this.height;
/*     */ 
/*     */ 
/*     */     
/* 124 */     this.motionY *= 0.6000000238418579D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/* 130 */     super.updateAITasks();
/* 131 */     BlockPos var1 = new BlockPos((Entity)this);
/* 132 */     BlockPos var2 = var1.offsetUp();
/*     */     
/* 134 */     if (getIsBatHanging()) {
/*     */       
/* 136 */       if (!this.worldObj.getBlockState(var2).getBlock().isNormalCube()) {
/*     */         
/* 138 */         setIsBatHanging(false);
/* 139 */         this.worldObj.playAuxSFXAtEntity(null, 1015, var1, 0);
/*     */       }
/*     */       else {
/*     */         
/* 143 */         if (this.rand.nextInt(200) == 0)
/*     */         {
/* 145 */           this.rotationYawHead = this.rand.nextInt(360);
/*     */         }
/*     */         
/* 148 */         if (this.worldObj.getClosestPlayerToEntity((Entity)this, 4.0D) != null)
/*     */         {
/* 150 */           setIsBatHanging(false);
/* 151 */           this.worldObj.playAuxSFXAtEntity(null, 1015, var1, 0);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 157 */       if (this.spawnPosition != null && (!this.worldObj.isAirBlock(this.spawnPosition) || this.spawnPosition.getY() < 1))
/*     */       {
/* 159 */         this.spawnPosition = null;
/*     */       }
/*     */       
/* 162 */       if (this.spawnPosition == null || this.rand.nextInt(30) == 0 || this.spawnPosition.distanceSq((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0D)
/*     */       {
/* 164 */         this.spawnPosition = new BlockPos((int)this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int)this.posY + this.rand.nextInt(6) - 2, (int)this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));
/*     */       }
/*     */       
/* 167 */       double var3 = this.spawnPosition.getX() + 0.5D - this.posX;
/* 168 */       double var5 = this.spawnPosition.getY() + 0.1D - this.posY;
/* 169 */       double var7 = this.spawnPosition.getZ() + 0.5D - this.posZ;
/* 170 */       this.motionX += (Math.signum(var3) * 0.5D - this.motionX) * 0.10000000149011612D;
/* 171 */       this.motionY += (Math.signum(var5) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
/* 172 */       this.motionZ += (Math.signum(var7) * 0.5D - this.motionZ) * 0.10000000149011612D;
/* 173 */       float var9 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
/* 174 */       float var10 = MathHelper.wrapAngleTo180_float(var9 - this.rotationYaw);
/* 175 */       this.moveForward = 0.5F;
/* 176 */       this.rotationYaw += var10;
/*     */       
/* 178 */       if (this.rand.nextInt(100) == 0 && this.worldObj.getBlockState(var2).getBlock().isNormalCube())
/*     */       {
/* 180 */         setIsBatHanging(true);
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
/* 191 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float distance, float damageMultiplier) {}
/*     */ 
/*     */   
/*     */   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {}
/*     */ 
/*     */   
/*     */   public boolean doesEntityNotTriggerPressurePlate() {
/* 203 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 211 */     if (func_180431_b(source))
/*     */     {
/* 213 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 217 */     if (!this.worldObj.isRemote && getIsBatHanging())
/*     */     {
/* 219 */       setIsBatHanging(false);
/*     */     }
/*     */     
/* 222 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 231 */     super.readEntityFromNBT(tagCompund);
/* 232 */     this.dataWatcher.updateObject(16, Byte.valueOf(tagCompund.getByte("BatFlags")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 240 */     super.writeEntityToNBT(tagCompound);
/* 241 */     tagCompound.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 249 */     BlockPos var1 = new BlockPos(this.posX, (getEntityBoundingBox()).minY, this.posZ);
/*     */     
/* 251 */     if (var1.getY() >= 63)
/*     */     {
/* 253 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 257 */     int var2 = this.worldObj.getLightFromNeighbors(var1);
/* 258 */     byte var3 = 4;
/*     */     
/* 260 */     if (func_175569_a(this.worldObj.getCurrentDate())) {
/*     */       
/* 262 */       var3 = 7;
/*     */     }
/* 264 */     else if (this.rand.nextBoolean()) {
/*     */       
/* 266 */       return false;
/*     */     } 
/*     */     
/* 269 */     return (var2 > this.rand.nextInt(var3)) ? false : super.getCanSpawnHere();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_175569_a(Calendar p_175569_1_) {
/* 275 */     return !((p_175569_1_.get(2) + 1 != 10 || p_175569_1_.get(5) < 20) && (p_175569_1_.get(2) + 1 != 11 || p_175569_1_.get(5) > 3));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 280 */     return this.height / 2.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */