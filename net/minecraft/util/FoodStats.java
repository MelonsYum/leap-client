/*     */ package net.minecraft.util;
/*     */ 
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ 
/*     */ 
/*     */ public class FoodStats
/*     */ {
/*  12 */   private int foodLevel = 20;
/*     */ 
/*     */   
/*  15 */   private float foodSaturationLevel = 5.0F;
/*     */ 
/*     */   
/*     */   private float foodExhaustionLevel;
/*     */   
/*     */   private int foodTimer;
/*     */   
/*  22 */   private int prevFoodLevel = 20;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001729";
/*     */ 
/*     */ 
/*     */   
/*     */   public void addStats(int p_75122_1_, float p_75122_2_) {
/*  30 */     this.foodLevel = Math.min(p_75122_1_ + this.foodLevel, 20);
/*  31 */     this.foodSaturationLevel = Math.min(this.foodSaturationLevel + p_75122_1_ * p_75122_2_ * 2.0F, this.foodLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addStats(ItemFood p_151686_1_, ItemStack p_151686_2_) {
/*  36 */     addStats(p_151686_1_.getHealAmount(p_151686_2_), p_151686_1_.getSaturationModifier(p_151686_2_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(EntityPlayer p_75118_1_) {
/*  44 */     EnumDifficulty var2 = p_75118_1_.worldObj.getDifficulty();
/*  45 */     this.prevFoodLevel = this.foodLevel;
/*     */     
/*  47 */     if (this.foodExhaustionLevel > 4.0F) {
/*     */       
/*  49 */       this.foodExhaustionLevel -= 4.0F;
/*     */       
/*  51 */       if (this.foodSaturationLevel > 0.0F) {
/*     */         
/*  53 */         this.foodSaturationLevel = Math.max(this.foodSaturationLevel - 1.0F, 0.0F);
/*     */       }
/*  55 */       else if (var2 != EnumDifficulty.PEACEFUL) {
/*     */         
/*  57 */         this.foodLevel = Math.max(this.foodLevel - 1, 0);
/*     */       } 
/*     */     } 
/*     */     
/*  61 */     if (p_75118_1_.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration") && this.foodLevel >= 18 && p_75118_1_.shouldHeal()) {
/*     */       
/*  63 */       this.foodTimer++;
/*     */       
/*  65 */       if (this.foodTimer >= 80)
/*     */       {
/*  67 */         p_75118_1_.heal(1.0F);
/*  68 */         addExhaustion(3.0F);
/*  69 */         this.foodTimer = 0;
/*     */       }
/*     */     
/*  72 */     } else if (this.foodLevel <= 0) {
/*     */       
/*  74 */       this.foodTimer++;
/*     */       
/*  76 */       if (this.foodTimer >= 80)
/*     */       {
/*  78 */         if (p_75118_1_.getHealth() > 10.0F || var2 == EnumDifficulty.HARD || (p_75118_1_.getHealth() > 1.0F && var2 == EnumDifficulty.NORMAL))
/*     */         {
/*  80 */           p_75118_1_.attackEntityFrom(DamageSource.starve, 1.0F);
/*     */         }
/*     */         
/*  83 */         this.foodTimer = 0;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  88 */       this.foodTimer = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readNBT(NBTTagCompound p_75112_1_) {
/*  97 */     if (p_75112_1_.hasKey("foodLevel", 99)) {
/*     */       
/*  99 */       this.foodLevel = p_75112_1_.getInteger("foodLevel");
/* 100 */       this.foodTimer = p_75112_1_.getInteger("foodTickTimer");
/* 101 */       this.foodSaturationLevel = p_75112_1_.getFloat("foodSaturationLevel");
/* 102 */       this.foodExhaustionLevel = p_75112_1_.getFloat("foodExhaustionLevel");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeNBT(NBTTagCompound p_75117_1_) {
/* 111 */     p_75117_1_.setInteger("foodLevel", this.foodLevel);
/* 112 */     p_75117_1_.setInteger("foodTickTimer", this.foodTimer);
/* 113 */     p_75117_1_.setFloat("foodSaturationLevel", this.foodSaturationLevel);
/* 114 */     p_75117_1_.setFloat("foodExhaustionLevel", this.foodExhaustionLevel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFoodLevel() {
/* 122 */     return this.foodLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPrevFoodLevel() {
/* 127 */     return this.prevFoodLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean needFood() {
/* 135 */     return (this.foodLevel < 20);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addExhaustion(float p_75113_1_) {
/* 143 */     this.foodExhaustionLevel = Math.min(this.foodExhaustionLevel + p_75113_1_, 40.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSaturationLevel() {
/* 151 */     return this.foodSaturationLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFoodLevel(int p_75114_1_) {
/* 156 */     this.foodLevel = p_75114_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFoodSaturationLevel(float p_75119_1_) {
/* 161 */     this.foodSaturationLevel = p_75119_1_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\FoodStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */