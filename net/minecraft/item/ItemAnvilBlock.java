/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ public class ItemAnvilBlock
/*    */   extends ItemMultiTexture
/*    */ {
/*    */   private static final String __OBFID = "CL_00001764";
/*    */   
/*    */   public ItemAnvilBlock(Block p_i1826_1_) {
/* 11 */     super(p_i1826_1_, p_i1826_1_, new String[] { "intact", "slightlyDamaged", "veryDamaged" });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetadata(int damage) {
/* 20 */     return damage << 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemAnvilBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */