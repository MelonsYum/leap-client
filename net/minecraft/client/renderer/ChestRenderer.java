/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ChestRenderer
/*    */ {
/*    */   private static final String __OBFID = "CL_00002530";
/*    */   
/*    */   public void func_178175_a(Block p_178175_1_, float p_178175_2_) {
/* 13 */     GlStateManager.color(p_178175_2_, p_178175_2_, p_178175_2_, 1.0F);
/* 14 */     GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
/* 15 */     TileEntityRendererChestHelper.instance.renderByItem(new ItemStack(p_178175_1_));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\ChestRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */