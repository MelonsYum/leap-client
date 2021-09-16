/*    */ package net.minecraft.inventory;
/*    */ 
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntityFurnace;
/*    */ 
/*    */ public class SlotFurnaceFuel
/*    */   extends Slot
/*    */ {
/*    */   private static final String __OBFID = "CL_00002184";
/*    */   
/*    */   public SlotFurnaceFuel(IInventory p_i45795_1_, int p_i45795_2_, int p_i45795_3_, int p_i45795_4_) {
/* 13 */     super(p_i45795_1_, p_i45795_2_, p_i45795_3_, p_i45795_4_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isItemValid(ItemStack stack) {
/* 21 */     return !(!TileEntityFurnace.isItemFuel(stack) && !func_178173_c_(stack));
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_178170_b(ItemStack p_178170_1_) {
/* 26 */     return func_178173_c_(p_178170_1_) ? 1 : super.func_178170_b(p_178170_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean func_178173_c_(ItemStack p_178173_0_) {
/* 31 */     return (p_178173_0_ != null && p_178173_0_.getItem() != null && p_178173_0_.getItem() == Items.bucket);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\inventory\SlotFurnaceFuel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */