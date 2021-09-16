/*    */ package net.minecraft.enchantment;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class EnchantmentLootBonus
/*    */   extends Enchantment
/*    */ {
/*    */   private static final String __OBFID = "CL_00000119";
/*    */   
/*    */   protected EnchantmentLootBonus(int p_i45767_1_, ResourceLocation p_i45767_2_, int p_i45767_3_, EnumEnchantmentType p_i45767_4_) {
/* 11 */     super(p_i45767_1_, p_i45767_2_, p_i45767_3_, p_i45767_4_);
/*    */     
/* 13 */     if (p_i45767_4_ == EnumEnchantmentType.DIGGER) {
/*    */       
/* 15 */       setName("lootBonusDigger");
/*    */     }
/* 17 */     else if (p_i45767_4_ == EnumEnchantmentType.FISHING_ROD) {
/*    */       
/* 19 */       setName("lootBonusFishing");
/*    */     }
/*    */     else {
/*    */       
/* 23 */       setName("lootBonus");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMinEnchantability(int p_77321_1_) {
/* 32 */     return 15 + (p_77321_1_ - 1) * 9;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxEnchantability(int p_77317_1_) {
/* 40 */     return super.getMinEnchantability(p_77317_1_) + 50;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 48 */     return 3;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canApplyTogether(Enchantment p_77326_1_) {
/* 56 */     return (super.canApplyTogether(p_77326_1_) && p_77326_1_.effectId != silkTouch.effectId);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\enchantment\EnchantmentLootBonus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */