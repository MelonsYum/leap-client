/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockLeaves;
/*    */ 
/*    */ public class ItemLeaves
/*    */   extends ItemBlock {
/*    */   private final BlockLeaves field_150940_b;
/*    */   private static final String __OBFID = "CL_00000046";
/*    */   
/*    */   public ItemLeaves(BlockLeaves p_i45344_1_) {
/* 12 */     super((Block)p_i45344_1_);
/* 13 */     this.field_150940_b = p_i45344_1_;
/* 14 */     setMaxDamage(0);
/* 15 */     setHasSubtypes(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetadata(int damage) {
/* 24 */     return damage | 0x4;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/* 29 */     return this.field_150940_b.getRenderColor(this.field_150940_b.getStateFromMeta(stack.getMetadata()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack stack) {
/* 38 */     return String.valueOf(getUnlocalizedName()) + "." + this.field_150940_b.func_176233_b(stack.getMetadata()).func_176840_c();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */