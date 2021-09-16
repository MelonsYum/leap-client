/*     */ package net.minecraft.enchantment;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class EnchantmentDamage
/*     */   extends Enchantment
/*     */ {
/*  15 */   private static final String[] protectionName = new String[] { "all", "undead", "arthropods" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  20 */   private static final int[] baseEnchantability = new int[] { 1, 5, 5 };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   private static final int[] levelEnchantability = new int[] { 11, 8, 8 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   private static final int[] thresholdEnchantability = new int[] { 20, 20, 20 };
/*     */ 
/*     */   
/*     */   public final int damageType;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000102";
/*     */ 
/*     */   
/*     */   public EnchantmentDamage(int p_i45774_1_, ResourceLocation p_i45774_2_, int p_i45774_3_, int p_i45774_4_) {
/*  41 */     super(p_i45774_1_, p_i45774_2_, p_i45774_3_, EnumEnchantmentType.WEAPON);
/*  42 */     this.damageType = p_i45774_4_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinEnchantability(int p_77321_1_) {
/*  50 */     return baseEnchantability[this.damageType] + (p_77321_1_ - 1) * levelEnchantability[this.damageType];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxEnchantability(int p_77317_1_) {
/*  58 */     return getMinEnchantability(p_77317_1_) + thresholdEnchantability[this.damageType];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxLevel() {
/*  66 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_152376_a(int p_152376_1_, EnumCreatureAttribute p_152376_2_) {
/*  71 */     return (this.damageType == 0) ? (p_152376_1_ * 1.25F) : ((this.damageType == 1 && p_152376_2_ == EnumCreatureAttribute.UNDEAD) ? (p_152376_1_ * 2.5F) : ((this.damageType == 2 && p_152376_2_ == EnumCreatureAttribute.ARTHROPOD) ? (p_152376_1_ * 2.5F) : 0.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  79 */     return "enchantment.damage." + protectionName[this.damageType];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canApplyTogether(Enchantment p_77326_1_) {
/*  87 */     return !(p_77326_1_ instanceof EnchantmentDamage);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canApply(ItemStack p_92089_1_) {
/*  92 */     return (p_92089_1_.getItem() instanceof net.minecraft.item.ItemAxe) ? true : super.canApply(p_92089_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_151368_a(EntityLivingBase p_151368_1_, Entity p_151368_2_, int p_151368_3_) {
/*  97 */     if (p_151368_2_ instanceof EntityLivingBase) {
/*     */       
/*  99 */       EntityLivingBase var4 = (EntityLivingBase)p_151368_2_;
/*     */       
/* 101 */       if (this.damageType == 2 && var4.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
/*     */         
/* 103 */         int var5 = 20 + p_151368_1_.getRNG().nextInt(10 * p_151368_3_);
/* 104 */         var4.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, var5, 3));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */