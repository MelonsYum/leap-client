/*    */ package net.minecraft.enchantment;
/*    */ 
/*    */ import net.minecraft.util.WeightedRandom;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnchantmentData
/*    */   extends WeightedRandom.Item
/*    */ {
/*    */   public final Enchantment enchantmentobj;
/*    */   public final int enchantmentLevel;
/*    */   private static final String __OBFID = "CL_00000115";
/*    */   
/*    */   public EnchantmentData(Enchantment p_i1930_1_, int p_i1930_2_) {
/* 16 */     super(p_i1930_1_.getWeight());
/* 17 */     this.enchantmentobj = p_i1930_1_;
/* 18 */     this.enchantmentLevel = p_i1930_2_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */