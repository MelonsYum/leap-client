/*    */ package net.minecraft.enchantment;
/*    */ 
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class EnchantmentDigging
/*    */   extends Enchantment
/*    */ {
/*    */   private static final String __OBFID = "CL_00000104";
/*    */   
/*    */   protected EnchantmentDigging(int p_i45772_1_, ResourceLocation p_i45772_2_, int p_i45772_3_) {
/* 13 */     super(p_i45772_1_, p_i45772_2_, p_i45772_3_, EnumEnchantmentType.DIGGER);
/* 14 */     setName("digging");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMinEnchantability(int p_77321_1_) {
/* 22 */     return 1 + 10 * (p_77321_1_ - 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxEnchantability(int p_77317_1_) {
/* 30 */     return super.getMinEnchantability(p_77317_1_) + 50;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 38 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canApply(ItemStack p_92089_1_) {
/* 43 */     return (p_92089_1_.getItem() == Items.shears) ? true : super.canApply(p_92089_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentDigging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */