/*     */ package net.minecraft.entity.passive;
/*     */ 
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntitySquid
/*     */   extends EntityWaterMob
/*     */ {
/*     */   public float squidPitch;
/*     */   public float prevSquidPitch;
/*     */   public float squidYaw;
/*     */   public float prevSquidYaw;
/*     */   public float squidRotation;
/*     */   public float prevSquidRotation;
/*     */   public float tentacleAngle;
/*     */   public float lastTentacleAngle;
/*     */   private float randomMotionSpeed;
/*     */   private float rotationVelocity;
/*     */   private float field_70871_bB;
/*     */   private float randomMotionVecX;
/*     */   private float randomMotionVecY;
/*     */   private float randomMotionVecZ;
/*     */   private static final String __OBFID = "CL_00001651";
/*     */   
/*     */   public EntitySquid(World worldIn) {
/*  45 */     super(worldIn);
/*  46 */     setSize(0.95F, 0.95F);
/*  47 */     this.rand.setSeed((1 + getEntityId()));
/*  48 */     this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
/*  49 */     this.tasks.addTask(0, new AIMoveRandom());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  54 */     super.applyEntityAttributes();
/*  55 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/*  60 */     return this.height * 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume() {
/*  92 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 114 */     int var3 = this.rand.nextInt(3 + p_70628_2_) + 1;
/*     */     
/* 116 */     for (int var4 = 0; var4 < var3; var4++)
/*     */     {
/* 118 */       entityDropItem(new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeColorDamage()), 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInWater() {
/* 128 */     return this.worldObj.handleMaterialAcceleration(getEntityBoundingBox().expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, (Entity)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 137 */     super.onLivingUpdate();
/* 138 */     this.prevSquidPitch = this.squidPitch;
/* 139 */     this.prevSquidYaw = this.squidYaw;
/* 140 */     this.prevSquidRotation = this.squidRotation;
/* 141 */     this.lastTentacleAngle = this.tentacleAngle;
/* 142 */     this.squidRotation += this.rotationVelocity;
/*     */     
/* 144 */     if (this.squidRotation > 6.283185307179586D)
/*     */     {
/* 146 */       if (this.worldObj.isRemote) {
/*     */         
/* 148 */         this.squidRotation = 6.2831855F;
/*     */       }
/*     */       else {
/*     */         
/* 152 */         this.squidRotation = (float)(this.squidRotation - 6.283185307179586D);
/*     */         
/* 154 */         if (this.rand.nextInt(10) == 0)
/*     */         {
/* 156 */           this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
/*     */         }
/*     */         
/* 159 */         this.worldObj.setEntityState((Entity)this, (byte)19);
/*     */       } 
/*     */     }
/*     */     
/* 163 */     if (this.inWater) {
/*     */ 
/*     */ 
/*     */       
/* 167 */       if (this.squidRotation < 3.1415927F) {
/*     */         
/* 169 */         float f = this.squidRotation / 3.1415927F;
/* 170 */         this.tentacleAngle = MathHelper.sin(f * f * 3.1415927F) * 3.1415927F * 0.25F;
/*     */         
/* 172 */         if (f > 0.75D)
/*     */         {
/* 174 */           this.randomMotionSpeed = 1.0F;
/* 175 */           this.field_70871_bB = 1.0F;
/*     */         }
/*     */         else
/*     */         {
/* 179 */           this.field_70871_bB *= 0.8F;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 184 */         this.tentacleAngle = 0.0F;
/* 185 */         this.randomMotionSpeed *= 0.9F;
/* 186 */         this.field_70871_bB *= 0.99F;
/*     */       } 
/*     */       
/* 189 */       if (!this.worldObj.isRemote) {
/*     */         
/* 191 */         this.motionX = (this.randomMotionVecX * this.randomMotionSpeed);
/* 192 */         this.motionY = (this.randomMotionVecY * this.randomMotionSpeed);
/* 193 */         this.motionZ = (this.randomMotionVecZ * this.randomMotionSpeed);
/*     */       } 
/*     */       
/* 196 */       float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 197 */       this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / 3.1415927F - this.renderYawOffset) * 0.1F;
/* 198 */       this.rotationYaw = this.renderYawOffset;
/* 199 */       this.squidYaw = (float)(this.squidYaw + Math.PI * this.field_70871_bB * 1.5D);
/* 200 */       this.squidPitch += (-((float)Math.atan2(var1, this.motionY)) * 180.0F / 3.1415927F - this.squidPitch) * 0.1F;
/*     */     }
/*     */     else {
/*     */       
/* 204 */       this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * 3.1415927F * 0.25F;
/*     */       
/* 206 */       if (!this.worldObj.isRemote) {
/*     */         
/* 208 */         this.motionX = 0.0D;
/* 209 */         this.motionY -= 0.08D;
/* 210 */         this.motionY *= 0.9800000190734863D;
/* 211 */         this.motionZ = 0.0D;
/*     */       } 
/*     */       
/* 214 */       this.squidPitch = (float)(this.squidPitch + (-90.0F - this.squidPitch) * 0.02D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
/* 223 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 231 */     return (this.posY > 45.0D && this.posY < 63.0D && super.getCanSpawnHere());
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 236 */     if (p_70103_1_ == 19) {
/*     */       
/* 238 */       this.squidRotation = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 242 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175568_b(float p_175568_1_, float p_175568_2_, float p_175568_3_) {
/* 248 */     this.randomMotionVecX = p_175568_1_;
/* 249 */     this.randomMotionVecY = p_175568_2_;
/* 250 */     this.randomMotionVecZ = p_175568_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175567_n() {
/* 255 */     return !(this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F);
/*     */   }
/*     */   
/*     */   class AIMoveRandom
/*     */     extends EntityAIBase {
/* 260 */     private EntitySquid field_179476_a = EntitySquid.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002232";
/*     */     
/*     */     public boolean shouldExecute() {
/* 265 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 270 */       int var1 = this.field_179476_a.getAge();
/*     */       
/* 272 */       if (var1 > 100) {
/*     */         
/* 274 */         this.field_179476_a.func_175568_b(0.0F, 0.0F, 0.0F);
/*     */       }
/* 276 */       else if (this.field_179476_a.getRNG().nextInt(50) == 0 || !this.field_179476_a.inWater || !this.field_179476_a.func_175567_n()) {
/*     */         
/* 278 */         float var2 = this.field_179476_a.getRNG().nextFloat() * 3.1415927F * 2.0F;
/* 279 */         float var3 = MathHelper.cos(var2) * 0.2F;
/* 280 */         float var4 = -0.1F + this.field_179476_a.getRNG().nextFloat() * 0.2F;
/* 281 */         float var5 = MathHelper.sin(var2) * 0.2F;
/* 282 */         this.field_179476_a.func_175568_b(var3, var4, var5);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntitySquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */