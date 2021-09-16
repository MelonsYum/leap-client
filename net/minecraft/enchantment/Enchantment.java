/*     */ package net.minecraft.enchantment;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.StatCollector;
/*     */ 
/*     */ public abstract class Enchantment
/*     */ {
/*  18 */   private static final Enchantment[] field_180311_a = new Enchantment[256];
/*     */   public static final Enchantment[] enchantmentsList;
/*  20 */   private static final Map field_180307_E = Maps.newHashMap();
/*  21 */   public static final Enchantment field_180310_c = new EnchantmentProtection(0, new ResourceLocation("protection"), 10, 0);
/*     */ 
/*     */   
/*  24 */   public static final Enchantment fireProtection = new EnchantmentProtection(1, new ResourceLocation("fire_protection"), 5, 1);
/*  25 */   public static final Enchantment field_180309_e = new EnchantmentProtection(2, new ResourceLocation("feather_falling"), 5, 2);
/*     */ 
/*     */   
/*  28 */   public static final Enchantment blastProtection = new EnchantmentProtection(3, new ResourceLocation("blast_protection"), 2, 3);
/*  29 */   public static final Enchantment field_180308_g = new EnchantmentProtection(4, new ResourceLocation("projectile_protection"), 5, 4);
/*  30 */   public static final Enchantment field_180317_h = new EnchantmentOxygen(5, new ResourceLocation("respiration"), 2);
/*     */ 
/*     */   
/*  33 */   public static final Enchantment aquaAffinity = new EnchantmentWaterWorker(6, new ResourceLocation("aqua_affinity"), 2);
/*  34 */   public static final Enchantment thorns = new EnchantmentThorns(7, new ResourceLocation("thorns"), 1);
/*  35 */   public static final Enchantment field_180316_k = new EnchantmentWaterWalker(8, new ResourceLocation("depth_strider"), 2);
/*  36 */   public static final Enchantment field_180314_l = new EnchantmentDamage(16, new ResourceLocation("sharpness"), 10, 0);
/*  37 */   public static final Enchantment field_180315_m = new EnchantmentDamage(17, new ResourceLocation("smite"), 5, 1);
/*  38 */   public static final Enchantment field_180312_n = new EnchantmentDamage(18, new ResourceLocation("bane_of_arthropods"), 5, 2);
/*  39 */   public static final Enchantment field_180313_o = new EnchantmentKnockback(19, new ResourceLocation("knockback"), 5);
/*     */ 
/*     */   
/*  42 */   public static final Enchantment fireAspect = new EnchantmentFireAspect(20, new ResourceLocation("fire_aspect"), 2);
/*     */ 
/*     */   
/*  45 */   public static final Enchantment looting = new EnchantmentLootBonus(21, new ResourceLocation("looting"), 2, EnumEnchantmentType.WEAPON);
/*     */ 
/*     */   
/*  48 */   public static final Enchantment efficiency = new EnchantmentDigging(32, new ResourceLocation("efficiency"), 10);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public static final Enchantment silkTouch = new EnchantmentUntouching(33, new ResourceLocation("silk_touch"), 1);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static final Enchantment unbreaking = new EnchantmentDurability(34, new ResourceLocation("unbreaking"), 5);
/*     */ 
/*     */   
/*  62 */   public static final Enchantment fortune = new EnchantmentLootBonus(35, new ResourceLocation("fortune"), 2, EnumEnchantmentType.DIGGER);
/*     */ 
/*     */   
/*  65 */   public static final Enchantment power = new EnchantmentArrowDamage(48, new ResourceLocation("power"), 10);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public static final Enchantment punch = new EnchantmentArrowKnockback(49, new ResourceLocation("punch"), 2);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public static final Enchantment flame = new EnchantmentArrowFire(50, new ResourceLocation("flame"), 2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static final Enchantment infinity = new EnchantmentArrowInfinite(51, new ResourceLocation("infinity"), 1);
/*  82 */   public static final Enchantment luckOfTheSea = new EnchantmentLootBonus(61, new ResourceLocation("luck_of_the_sea"), 2, EnumEnchantmentType.FISHING_ROD);
/*  83 */   public static final Enchantment lure = new EnchantmentFishingSpeed(62, new ResourceLocation("lure"), 2, EnumEnchantmentType.FISHING_ROD);
/*     */   
/*     */   public final int effectId;
/*     */   
/*     */   private final int weight;
/*     */   
/*     */   public EnumEnchantmentType type;
/*     */   
/*     */   protected String name;
/*     */   
/*     */   private static final String __OBFID = "CL_00000105";
/*     */   
/*     */   public static Enchantment func_180306_c(int p_180306_0_) {
/*  96 */     return (p_180306_0_ >= 0 && p_180306_0_ < field_180311_a.length) ? field_180311_a[p_180306_0_] : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Enchantment(int p_i45771_1_, ResourceLocation p_i45771_2_, int p_i45771_3_, EnumEnchantmentType p_i45771_4_) {
/* 101 */     this.effectId = p_i45771_1_;
/* 102 */     this.weight = p_i45771_3_;
/* 103 */     this.type = p_i45771_4_;
/*     */     
/* 105 */     if (field_180311_a[p_i45771_1_] != null)
/*     */     {
/* 107 */       throw new IllegalArgumentException("Duplicate enchantment id!");
/*     */     }
/*     */ 
/*     */     
/* 111 */     field_180311_a[p_i45771_1_] = this;
/* 112 */     field_180307_E.put(p_i45771_2_, this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Enchantment func_180305_b(String p_180305_0_) {
/* 118 */     return (Enchantment)field_180307_E.get(new ResourceLocation(p_180305_0_));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] func_180304_c() {
/* 123 */     String[] var0 = new String[field_180307_E.size()];
/* 124 */     int var1 = 0;
/*     */ 
/*     */     
/* 127 */     for (Iterator<ResourceLocation> var2 = field_180307_E.keySet().iterator(); var2.hasNext(); var0[var1++] = var3.toString())
/*     */     {
/* 129 */       ResourceLocation var3 = var2.next();
/*     */     }
/*     */     
/* 132 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWeight() {
/* 137 */     return this.weight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinLevel() {
/* 145 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxLevel() {
/* 153 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinEnchantability(int p_77321_1_) {
/* 161 */     return 1 + p_77321_1_ * 10;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxEnchantability(int p_77317_1_) {
/* 169 */     return getMinEnchantability(p_77317_1_) + 5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int calcModifierDamage(int p_77318_1_, DamageSource p_77318_2_) {
/* 177 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_152376_a(int p_152376_1_, EnumCreatureAttribute p_152376_2_) {
/* 182 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canApplyTogether(Enchantment p_77326_1_) {
/* 190 */     return (this != p_77326_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Enchantment setName(String p_77322_1_) {
/* 198 */     this.name = p_77322_1_;
/* 199 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 207 */     return "enchantment." + this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTranslatedName(int p_77316_1_) {
/* 215 */     String var2 = StatCollector.translateToLocal(getName());
/* 216 */     return String.valueOf(var2) + " " + StatCollector.translateToLocal("enchantment.level." + p_77316_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApply(ItemStack p_92089_1_) {
/* 221 */     return this.type.canEnchantItem(p_92089_1_.getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_151368_a(EntityLivingBase p_151368_1_, Entity p_151368_2_, int p_151368_3_) {}
/*     */   
/*     */   public void func_151367_b(EntityLivingBase p_151367_1_, Entity p_151367_2_, int p_151367_3_) {}
/*     */   
/*     */   static {
/* 230 */     ArrayList<Enchantment> var0 = Lists.newArrayList();
/* 231 */     Enchantment[] var1 = field_180311_a;
/* 232 */     int var2 = var1.length;
/*     */     
/* 234 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/* 236 */       Enchantment var4 = var1[var3];
/*     */       
/* 238 */       if (var4 != null)
/*     */       {
/* 240 */         var0.add(var4);
/*     */       }
/*     */     } 
/*     */     
/* 244 */     enchantmentsList = var0.<Enchantment>toArray(new Enchantment[var0.size()]);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\Enchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */