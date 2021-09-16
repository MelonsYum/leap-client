/*    */ package net.minecraft.enchantment;
/*    */ 
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class EnchantmentUntouching
/*    */   extends Enchantment
/*    */ {
/*    */   private static final String __OBFID = "CL_00000123";
/*    */   
/*    */   protected EnchantmentUntouching(int p_i45763_1_, ResourceLocation p_i45763_2_, int p_i45763_3_) {
/* 13 */     super(p_i45763_1_, p_i45763_2_, p_i45763_3_, EnumEnchantmentType.DIGGER);
/* 14 */     setName("untouching");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMinEnchantability(int p_77321_1_) {
/* 22 */     return 15;
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
/* 38 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canApplyTogether(Enchantment p_77326_1_) {
/* 46 */     return (super.canApplyTogether(p_77326_1_) && p_77326_1_.effectId != fortune.effectId);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canApply(ItemStack p_92089_1_) {
/* 51 */     return (p_92089_1_.getItem() == Items.shears) ? true : super.canApply(p_92089_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentUntouching.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */