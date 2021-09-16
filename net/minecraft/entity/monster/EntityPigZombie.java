/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityPigZombie extends EntityZombie {
/*  24 */   private static final UUID field_110189_bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
/*  25 */   private static final AttributeModifier field_110190_br = (new AttributeModifier(field_110189_bq, "Attacking speed boost", 0.05D, 0)).setSaved(false);
/*     */   
/*     */   private int angerLevel;
/*     */   
/*     */   private int randomSoundDelay;
/*     */   
/*     */   private UUID field_175459_bn;
/*     */   
/*     */   private static final String __OBFID = "CL_00001693";
/*     */ 
/*     */   
/*     */   public EntityPigZombie(World worldIn) {
/*  37 */     super(worldIn);
/*  38 */     this.isImmuneToFire = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRevengeTarget(EntityLivingBase p_70604_1_) {
/*  43 */     super.setRevengeTarget(p_70604_1_);
/*     */     
/*  45 */     if (p_70604_1_ != null)
/*     */     {
/*  47 */       this.field_175459_bn = p_70604_1_.getUniqueID();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175456_n() {
/*  53 */     this.targetTasks.addTask(1, (EntityAIBase)new AIHurtByAggressor());
/*  54 */     this.targetTasks.addTask(2, (EntityAIBase)new AITargetAggressor());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  59 */     super.applyEntityAttributes();
/*  60 */     getEntityAttribute(field_110186_bp).setBaseValue(0.0D);
/*  61 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
/*  62 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  70 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/*  75 */     IAttributeInstance var1 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*     */     
/*  77 */     if (func_175457_ck()) {
/*     */       
/*  79 */       if (!isChild() && !var1.func_180374_a(field_110190_br))
/*     */       {
/*  81 */         var1.applyModifier(field_110190_br);
/*     */       }
/*     */       
/*  84 */       this.angerLevel--;
/*     */     }
/*  86 */     else if (var1.func_180374_a(field_110190_br)) {
/*     */       
/*  88 */       var1.removeModifier(field_110190_br);
/*     */     } 
/*     */     
/*  91 */     if (this.randomSoundDelay > 0 && --this.randomSoundDelay == 0)
/*     */     {
/*  93 */       playSound("mob.zombiepig.zpigangry", getSoundVolume() * 2.0F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 1.8F);
/*     */     }
/*     */     
/*  96 */     if (this.angerLevel > 0 && this.field_175459_bn != null && getAITarget() == null) {
/*     */       
/*  98 */       EntityPlayer var2 = this.worldObj.getPlayerEntityByUUID(this.field_175459_bn);
/*  99 */       setRevengeTarget((EntityLivingBase)var2);
/* 100 */       this.attackingPlayer = var2;
/* 101 */       this.recentlyHit = getRevengeTimer();
/*     */     } 
/*     */     
/* 104 */     super.updateAITasks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 112 */     return (this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleLavaMovement() {
/* 120 */     return (this.worldObj.checkNoEntityCollision(getEntityBoundingBox(), (Entity)this) && this.worldObj.getCollidingBoundingBoxes((Entity)this, getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(getEntityBoundingBox()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 128 */     super.writeEntityToNBT(tagCompound);
/* 129 */     tagCompound.setShort("Anger", (short)this.angerLevel);
/*     */     
/* 131 */     if (this.field_175459_bn != null) {
/*     */       
/* 133 */       tagCompound.setString("HurtBy", this.field_175459_bn.toString());
/*     */     }
/*     */     else {
/*     */       
/* 137 */       tagCompound.setString("HurtBy", "");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 146 */     super.readEntityFromNBT(tagCompund);
/* 147 */     this.angerLevel = tagCompund.getShort("Anger");
/* 148 */     String var2 = tagCompund.getString("HurtBy");
/*     */     
/* 150 */     if (var2.length() > 0) {
/*     */       
/* 152 */       this.field_175459_bn = UUID.fromString(var2);
/* 153 */       EntityPlayer var3 = this.worldObj.getPlayerEntityByUUID(this.field_175459_bn);
/* 154 */       setRevengeTarget((EntityLivingBase)var3);
/*     */       
/* 156 */       if (var3 != null) {
/*     */         
/* 158 */         this.attackingPlayer = var3;
/* 159 */         this.recentlyHit = getRevengeTimer();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 169 */     if (func_180431_b(source))
/*     */     {
/* 171 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 175 */     Entity var3 = source.getEntity();
/*     */     
/* 177 */     if (var3 instanceof EntityPlayer)
/*     */     {
/* 179 */       becomeAngryAt(var3);
/*     */     }
/*     */     
/* 182 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void becomeAngryAt(Entity p_70835_1_) {
/* 191 */     this.angerLevel = 400 + this.rand.nextInt(400);
/* 192 */     this.randomSoundDelay = this.rand.nextInt(40);
/*     */     
/* 194 */     if (p_70835_1_ instanceof EntityLivingBase)
/*     */     {
/* 196 */       setRevengeTarget((EntityLivingBase)p_70835_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175457_ck() {
/* 202 */     return (this.angerLevel > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 210 */     return "mob.zombiepig.zpig";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 218 */     return "mob.zombiepig.zpighurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 226 */     return "mob.zombiepig.zpigdeath";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 234 */     int var3 = this.rand.nextInt(2 + p_70628_2_);
/*     */     
/*     */     int var4;
/* 237 */     for (var4 = 0; var4 < var3; var4++)
/*     */     {
/* 239 */       dropItem(Items.rotten_flesh, 1);
/*     */     }
/*     */     
/* 242 */     var3 = this.rand.nextInt(2 + p_70628_2_);
/*     */     
/* 244 */     for (var4 = 0; var4 < var3; var4++)
/*     */     {
/* 246 */       dropItem(Items.gold_nugget, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean interact(EntityPlayer p_70085_1_) {
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addRandomArmor() {
/* 263 */     dropItem(Items.gold_ingot, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180481_a(DifficultyInstance p_180481_1_) {
/* 268 */     setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 273 */     super.func_180482_a(p_180482_1_, p_180482_2_);
/* 274 */     setVillager(false);
/* 275 */     return p_180482_2_;
/*     */   }
/*     */   
/*     */   class AIHurtByAggressor
/*     */     extends EntityAIHurtByTarget
/*     */   {
/*     */     private static final String __OBFID = "CL_00002206";
/*     */     
/*     */     public AIHurtByAggressor() {
/* 284 */       super(EntityPigZombie.this, true, new Class[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void func_179446_a(EntityCreature p_179446_1_, EntityLivingBase p_179446_2_) {
/* 289 */       super.func_179446_a(p_179446_1_, p_179446_2_);
/*     */       
/* 291 */       if (p_179446_1_ instanceof EntityPigZombie)
/*     */       {
/* 293 */         ((EntityPigZombie)p_179446_1_).becomeAngryAt((Entity)p_179446_2_);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   class AITargetAggressor
/*     */     extends EntityAINearestAttackableTarget
/*     */   {
/*     */     private static final String __OBFID = "CL_00002207";
/*     */     
/*     */     public AITargetAggressor() {
/* 304 */       super(EntityPigZombie.this, EntityPlayer.class, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 309 */       return (((EntityPigZombie)this.taskOwner).func_175457_ck() && super.shouldExecute());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityPigZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */