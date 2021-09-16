/*    */ package net.minecraft.enchantment;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class EnchantmentFireAspect
/*    */   extends Enchantment
/*    */ {
/*    */   private static final String __OBFID = "CL_00000116";
/*    */   
/*    */   protected EnchantmentFireAspect(int p_i45770_1_, ResourceLocation p_i45770_2_, int p_i45770_3_) {
/* 11 */     super(p_i45770_1_, p_i45770_2_, p_i45770_3_, EnumEnchantmentType.WEAPON);
/* 12 */     setName("fire");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMinEnchantability(int p_77321_1_) {
/* 20 */     return 10 + 20 * (p_77321_1_ - 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxEnchantability(int p_77317_1_) {
/* 28 */     return super.getMinEnchantability(p_77317_1_) + 50;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 36 */     return 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentFireAspect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */