/*    */ package net.minecraft.enchantment;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class EnchantmentArrowInfinite
/*    */   extends Enchantment
/*    */ {
/*    */   private static final String __OBFID = "CL_00000100";
/*    */   
/*    */   public EnchantmentArrowInfinite(int p_i45776_1_, ResourceLocation p_i45776_2_, int p_i45776_3_) {
/* 11 */     super(p_i45776_1_, p_i45776_2_, p_i45776_3_, EnumEnchantmentType.BOW);
/* 12 */     setName("arrowInfinite");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMinEnchantability(int p_77321_1_) {
/* 20 */     return 20;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxEnchantability(int p_77317_1_) {
/* 28 */     return 50;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 36 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentArrowInfinite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */