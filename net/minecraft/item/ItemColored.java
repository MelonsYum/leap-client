/*    */ package net.minecraft.item;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ public class ItemColored
/*    */   extends ItemBlock
/*    */ {
/*    */   private final Block field_150944_b;
/*    */   private String[] field_150945_c;
/*    */   private static final String __OBFID = "CL_00000003";
/*    */   
/*    */   public ItemColored(Block p_i45332_1_, boolean p_i45332_2_) {
/* 13 */     super(p_i45332_1_);
/* 14 */     this.field_150944_b = p_i45332_1_;
/*    */     
/* 16 */     if (p_i45332_2_) {
/*    */       
/* 18 */       setMaxDamage(0);
/* 19 */       setHasSubtypes(true);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/* 25 */     return this.field_150944_b.getRenderColor(this.field_150944_b.getStateFromMeta(stack.getMetadata()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetadata(int damage) {
/* 34 */     return damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemColored func_150943_a(String[] p_150943_1_) {
/* 39 */     this.field_150945_c = p_150943_1_;
/* 40 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUnlocalizedName(ItemStack stack) {
/* 49 */     if (this.field_150945_c == null)
/*    */     {
/* 51 */       return super.getUnlocalizedName(stack);
/*    */     }
/*    */ 
/*    */     
/* 55 */     int var2 = stack.getMetadata();
/* 56 */     return (var2 >= 0 && var2 < this.field_150945_c.length) ? (String.valueOf(super.getUnlocalizedName(stack)) + "." + this.field_150945_c[var2]) : super.getUnlocalizedName(stack);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemColored.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */