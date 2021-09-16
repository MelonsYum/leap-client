/*     */ package net.minecraft.enchantment;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class EnchantmentProtection
/*     */   extends Enchantment
/*     */ {
/*  11 */   private static final String[] protectionName = new String[] { "all", "fire", "fall", "explosion", "projectile" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  16 */   private static final int[] baseEnchantability = new int[] { 1, 10, 5, 5, 3 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  21 */   private static final int[] levelEnchantability = new int[] { 11, 8, 6, 8, 6 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  27 */   private static final int[] thresholdEnchantability = new int[] { 20, 12, 10, 12, 15 };
/*     */ 
/*     */   
/*     */   public final int protectionType;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000121";
/*     */ 
/*     */ 
/*     */   
/*     */   public EnchantmentProtection(int p_i45765_1_, ResourceLocation p_i45765_2_, int p_i45765_3_, int p_i45765_4_) {
/*  38 */     super(p_i45765_1_, p_i45765_2_, p_i45765_3_, EnumEnchantmentType.ARMOR);
/*  39 */     this.protectionType = p_i45765_4_;
/*     */     
/*  41 */     if (p_i45765_4_ == 2)
/*     */     {
/*  43 */       this.type = EnumEnchantmentType.ARMOR_FEET;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinEnchantability(int p_77321_1_) {
/*  52 */     return baseEnchantability[this.protectionType] + (p_77321_1_ - 1) * levelEnchantability[this.protectionType];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxEnchantability(int p_77317_1_) {
/*  60 */     return getMinEnchantability(p_77317_1_) + thresholdEnchantability[this.protectionType];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxLevel() {
/*  68 */     return 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int calcModifierDamage(int p_77318_1_, DamageSource p_77318_2_) {
/*  76 */     if (p_77318_2_.canHarmInCreative())
/*     */     {
/*  78 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  82 */     float var3 = (6 + p_77318_1_ * p_77318_1_) / 3.0F;
/*  83 */     return (this.protectionType == 0) ? MathHelper.floor_float(var3 * 0.75F) : ((this.protectionType == 1 && p_77318_2_.isFireDamage()) ? MathHelper.floor_float(var3 * 1.25F) : ((this.protectionType == 2 && p_77318_2_ == DamageSource.fall) ? MathHelper.floor_float(var3 * 2.5F) : ((this.protectionType == 3 && p_77318_2_.isExplosion()) ? MathHelper.floor_float(var3 * 1.5F) : ((this.protectionType == 4 && p_77318_2_.isProjectile()) ? MathHelper.floor_float(var3 * 1.5F) : 0))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  92 */     return "enchantment.protect." + protectionName[this.protectionType];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canApplyTogether(Enchantment p_77326_1_) {
/* 100 */     if (p_77326_1_ instanceof EnchantmentProtection) {
/*     */       
/* 102 */       EnchantmentProtection var2 = (EnchantmentProtection)p_77326_1_;
/* 103 */       return (var2.protectionType == this.protectionType) ? false : (!(this.protectionType != 2 && var2.protectionType != 2));
/*     */     } 
/*     */ 
/*     */     
/* 107 */     return super.canApplyTogether(p_77326_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFireTimeForEntity(Entity p_92093_0_, int p_92093_1_) {
/* 116 */     int var2 = EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.fireProtection.effectId, p_92093_0_.getInventory());
/*     */     
/* 118 */     if (var2 > 0)
/*     */     {
/* 120 */       p_92093_1_ -= MathHelper.floor_float(p_92093_1_ * var2 * 0.15F);
/*     */     }
/*     */     
/* 123 */     return p_92093_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double func_92092_a(Entity p_92092_0_, double p_92092_1_) {
/* 128 */     int var3 = EnchantmentHelper.getMaxEnchantmentLevel(Enchantment.blastProtection.effectId, p_92092_0_.getInventory());
/*     */     
/* 130 */     if (var3 > 0)
/*     */     {
/* 132 */       p_92092_1_ -= MathHelper.floor_double(p_92092_1_ * (var3 * 0.15F));
/*     */     }
/*     */     
/* 135 */     return p_92092_1_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentProtection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */