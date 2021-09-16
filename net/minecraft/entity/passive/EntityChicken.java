/*     */ package net.minecraft.entity.passive;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIFollowParent;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMate;
/*     */ import net.minecraft.entity.ai.EntityAIPanic;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityChicken extends EntityAnimal {
/*     */   public float field_70886_e;
/*     */   public float destPos;
/*  30 */   public float field_70889_i = 1.0F;
/*     */   
/*     */   public float field_70884_g;
/*     */   public float field_70888_h;
/*     */   public int timeUntilNextEgg;
/*     */   public boolean field_152118_bv;
/*     */   private static final String __OBFID = "CL_00001639";
/*     */   
/*     */   public EntityChicken(World worldIn) {
/*  39 */     super(worldIn);
/*  40 */     setSize(0.4F, 0.7F);
/*  41 */     this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
/*  42 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  43 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.4D));
/*  44 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIMate(this, 1.0D));
/*  45 */     this.tasks.addTask(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.0D, Items.wheat_seeds, false));
/*  46 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowParent(this, 1.1D));
/*  47 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
/*  48 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*  49 */     this.tasks.addTask(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/*  54 */     return this.height;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  59 */     super.applyEntityAttributes();
/*  60 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0D);
/*  61 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  70 */     super.onLivingUpdate();
/*  71 */     this.field_70888_h = this.field_70886_e;
/*  72 */     this.field_70884_g = this.destPos;
/*  73 */     this.destPos = (float)(this.destPos + (this.onGround ? -1 : 4) * 0.3D);
/*  74 */     this.destPos = MathHelper.clamp_float(this.destPos, 0.0F, 1.0F);
/*     */     
/*  76 */     if (!this.onGround && this.field_70889_i < 1.0F)
/*     */     {
/*  78 */       this.field_70889_i = 1.0F;
/*     */     }
/*     */     
/*  81 */     this.field_70889_i = (float)(this.field_70889_i * 0.9D);
/*     */     
/*  83 */     if (!this.onGround && this.motionY < 0.0D)
/*     */     {
/*  85 */       this.motionY *= 0.6D;
/*     */     }
/*     */     
/*  88 */     this.field_70886_e += this.field_70889_i * 2.0F;
/*     */     
/*  90 */     if (!this.worldObj.isRemote && !isChild() && !func_152116_bZ() && --this.timeUntilNextEgg <= 0) {
/*     */       
/*  92 */       playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*  93 */       dropItem(Items.egg, 1);
/*  94 */       this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float distance, float damageMultiplier) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 105 */     return "mob.chicken.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 113 */     return "mob.chicken.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 121 */     return "mob.chicken.hurt";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/* 126 */     playSound("mob.chicken.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 131 */     return Items.feather;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 139 */     int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + p_70628_2_);
/*     */     
/* 141 */     for (int var4 = 0; var4 < var3; var4++)
/*     */     {
/* 143 */       dropItem(Items.feather, 1);
/*     */     }
/*     */     
/* 146 */     if (isBurning()) {
/*     */       
/* 148 */       dropItem(Items.cooked_chicken, 1);
/*     */     }
/*     */     else {
/*     */       
/* 152 */       dropItem(Items.chicken, 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityChicken createChild1(EntityAgeable p_90011_1_) {
/* 158 */     return new EntityChicken(this.worldObj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBreedingItem(ItemStack p_70877_1_) {
/* 167 */     return (p_70877_1_ != null && p_70877_1_.getItem() == Items.wheat_seeds);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 175 */     super.readEntityFromNBT(tagCompund);
/* 176 */     this.field_152118_bv = tagCompund.getBoolean("IsChickenJockey");
/*     */     
/* 178 */     if (tagCompund.hasKey("EggLayTime"))
/*     */     {
/* 180 */       this.timeUntilNextEgg = tagCompund.getInteger("EggLayTime");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getExperiencePoints(EntityPlayer p_70693_1_) {
/* 189 */     return func_152116_bZ() ? 10 : super.getExperiencePoints(p_70693_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 197 */     super.writeEntityToNBT(tagCompound);
/* 198 */     tagCompound.setBoolean("IsChickenJockey", this.field_152118_bv);
/* 199 */     tagCompound.setInteger("EggLayTime", this.timeUntilNextEgg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 207 */     return (func_152116_bZ() && this.riddenByEntity == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateRiderPosition() {
/* 212 */     super.updateRiderPosition();
/* 213 */     float var1 = MathHelper.sin(this.renderYawOffset * 3.1415927F / 180.0F);
/* 214 */     float var2 = MathHelper.cos(this.renderYawOffset * 3.1415927F / 180.0F);
/* 215 */     float var3 = 0.1F;
/* 216 */     float var4 = 0.0F;
/* 217 */     this.riddenByEntity.setPosition(this.posX + (var3 * var1), this.posY + (this.height * 0.5F) + this.riddenByEntity.getYOffset() + var4, this.posZ - (var3 * var2));
/*     */     
/* 219 */     if (this.riddenByEntity instanceof EntityLivingBase)
/*     */     {
/* 221 */       ((EntityLivingBase)this.riddenByEntity).renderYawOffset = this.renderYawOffset;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152116_bZ() {
/* 227 */     return this.field_152118_bv;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152117_i(boolean p_152117_1_) {
/* 232 */     this.field_152118_bv = p_152117_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable p_90011_1_) {
/* 237 */     return createChild1(p_90011_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */