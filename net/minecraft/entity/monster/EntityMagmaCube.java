/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityMagmaCube
/*     */   extends EntitySlime {
/*     */   private static final String __OBFID = "CL_00001691";
/*     */   
/*     */   public EntityMagmaCube(World worldIn) {
/*  16 */     super(worldIn);
/*  17 */     this.isImmuneToFire = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  22 */     super.applyEntityAttributes();
/*  23 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/*  31 */     return (this.worldObj.getDifficulty() != EnumDifficulty.PEACEFUL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleLavaMovement() {
/*  39 */     return (this.worldObj.checkNoEntityCollision(getEntityBoundingBox(), (Entity)this) && this.worldObj.getCollidingBoundingBoxes((Entity)this, getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(getEntityBoundingBox()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalArmorValue() {
/*  47 */     return getSlimeSize() * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float p_70070_1_) {
/*  52 */     return 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float p_70013_1_) {
/*  60 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EnumParticleTypes func_180487_n() {
/*  65 */     return EnumParticleTypes.FLAME;
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntitySlime createInstance() {
/*  70 */     return new EntityMagmaCube(this.worldObj);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/*  75 */     return Items.magma_cream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/*  83 */     Item var3 = getDropItem();
/*     */     
/*  85 */     if (var3 != null && getSlimeSize() > 1) {
/*     */       
/*  87 */       int var4 = this.rand.nextInt(4) - 2;
/*     */       
/*  89 */       if (p_70628_2_ > 0)
/*     */       {
/*  91 */         var4 += this.rand.nextInt(p_70628_2_ + 1);
/*     */       }
/*     */       
/*  94 */       for (int var5 = 0; var5 < var4; var5++)
/*     */       {
/*  96 */         dropItem(var3, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 106 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getJumpDelay() {
/* 114 */     return super.getJumpDelay() * 4;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void alterSquishAmount() {
/* 119 */     this.squishAmount *= 0.9F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void jump() {
/* 127 */     this.motionY = (0.42F + getSlimeSize() * 0.1F);
/* 128 */     this.isAirBorne = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180466_bG() {
/* 133 */     this.motionY = (0.22F + getSlimeSize() * 0.05F);
/* 134 */     this.isAirBorne = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fall(float distance, float damageMultiplier) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDamagePlayer() {
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getAttackStrength() {
/* 152 */     return super.getAttackStrength() + 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getJumpSound() {
/* 160 */     return (getSlimeSize() > 1) ? "mob.magmacube.big" : "mob.magmacube.small";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean makesSoundOnLand() {
/* 168 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityMagmaCube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */