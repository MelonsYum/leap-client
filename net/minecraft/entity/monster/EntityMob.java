/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class EntityMob
/*     */   extends EntityCreature implements IMob {
/*  20 */   protected final EntityAIBase field_175455_a = (EntityAIBase)new EntityAIAvoidEntity(this, new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002208";
/*     */         
/*     */         public boolean func_179911_a(Entity p_179911_1_) {
/*  25 */           return (p_179911_1_ instanceof EntityCreeper && ((EntityCreeper)p_179911_1_).getCreeperState() > 0);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  29 */           return func_179911_a((Entity)p_apply_1_);
/*     */         }
/*  31 */       },  4.0F, 1.0D, 2.0D);
/*     */   
/*     */   private static final String __OBFID = "CL_00001692";
/*     */   
/*     */   public EntityMob(World worldIn) {
/*  36 */     super(worldIn);
/*  37 */     this.experienceValue = 5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  46 */     updateArmSwingProgress();
/*  47 */     float var1 = getBrightness(1.0F);
/*     */     
/*  49 */     if (var1 > 0.5F)
/*     */     {
/*  51 */       this.entityAge += 2;
/*     */     }
/*     */     
/*  54 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  62 */     super.onUpdate();
/*     */     
/*  64 */     if (!this.worldObj.isRemote && this.worldObj.getDifficulty() == EnumDifficulty.PEACEFUL)
/*     */     {
/*  66 */       setDead();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getSwimSound() {
/*  72 */     return "game.hostile.swim";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getSplashSound() {
/*  77 */     return "game.hostile.swim.splash";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  85 */     if (func_180431_b(source))
/*     */     {
/*  87 */       return false;
/*     */     }
/*  89 */     if (super.attackEntityFrom(source, amount)) {
/*     */       
/*  91 */       Entity var3 = source.getEntity();
/*  92 */       return (this.riddenByEntity != var3 && this.ridingEntity != var3) ? true : true;
/*     */     } 
/*     */ 
/*     */     
/*  96 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 105 */     return "game.hostile.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 113 */     return "game.hostile.die";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String func_146067_o(int p_146067_1_) {
/* 118 */     return (p_146067_1_ > 4) ? "game.hostile.hurt.fall.big" : "game.hostile.hurt.fall.small";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 123 */     float var2 = (float)getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
/* 124 */     int var3 = 0;
/*     */     
/* 126 */     if (p_70652_1_ instanceof EntityLivingBase) {
/*     */       
/* 128 */       var2 += EnchantmentHelper.func_152377_a(getHeldItem(), ((EntityLivingBase)p_70652_1_).getCreatureAttribute());
/* 129 */       var3 += EnchantmentHelper.getRespiration((EntityLivingBase)this);
/*     */     } 
/*     */     
/* 132 */     boolean var4 = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), var2);
/*     */     
/* 134 */     if (var4) {
/*     */       
/* 136 */       if (var3 > 0) {
/*     */         
/* 138 */         p_70652_1_.addVelocity((-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * var3 * 0.5F), 0.1D, (MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * var3 * 0.5F));
/* 139 */         this.motionX *= 0.6D;
/* 140 */         this.motionZ *= 0.6D;
/*     */       } 
/*     */       
/* 143 */       int var5 = EnchantmentHelper.getFireAspectModifier((EntityLivingBase)this);
/*     */       
/* 145 */       if (var5 > 0)
/*     */       {
/* 147 */         p_70652_1_.setFire(var5 * 4);
/*     */       }
/*     */       
/* 150 */       func_174815_a((EntityLivingBase)this, p_70652_1_);
/*     */     } 
/*     */     
/* 153 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_180484_a(BlockPos p_180484_1_) {
/* 158 */     return 0.5F - this.worldObj.getLightBrightness(p_180484_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 166 */     BlockPos var1 = new BlockPos(this.posX, (getEntityBoundingBox()).minY, this.posZ);
/*     */     
/* 168 */     if (this.worldObj.getLightFor(EnumSkyBlock.SKY, var1) > this.rand.nextInt(32))
/*     */     {
/* 170 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 174 */     int var2 = this.worldObj.getLightFromNeighbors(var1);
/*     */     
/* 176 */     if (this.worldObj.isThundering()) {
/*     */       
/* 178 */       int var3 = this.worldObj.getSkylightSubtracted();
/* 179 */       this.worldObj.setSkylightSubtracted(10);
/* 180 */       var2 = this.worldObj.getLightFromNeighbors(var1);
/* 181 */       this.worldObj.setSkylightSubtracted(var3);
/*     */     } 
/*     */     
/* 184 */     return (var2 <= this.rand.nextInt(8));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 193 */     return (this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL && isValidLightLevel() && super.getCanSpawnHere());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 198 */     super.applyEntityAttributes();
/* 199 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_146066_aG() {
/* 204 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */