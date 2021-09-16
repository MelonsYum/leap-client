/*    */ package net.minecraft.enchantment;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class EnchantmentOxygen
/*    */   extends Enchantment
/*    */ {
/*    */   private static final String __OBFID = "CL_00000120";
/*    */   
/*    */   public EnchantmentOxygen(int p_i45766_1_, ResourceLocation p_i45766_2_, int p_i45766_3_) {
/* 11 */     super(p_i45766_1_, p_i45766_2_, p_i45766_3_, EnumEnchantmentType.ARMOR_HEAD);
/* 12 */     setName("oxygen");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMinEnchantability(int p_77321_1_) {
/* 20 */     return 10 * p_77321_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxEnchantability(int p_77317_1_) {
/* 28 */     return getMinEnchantability(p_77317_1_) + 30;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 36 */     return 3;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentOxygen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */