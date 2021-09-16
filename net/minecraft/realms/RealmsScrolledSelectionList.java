/*    */ package net.minecraft.realms;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiSlotRealmsProxy;
/*    */ 
/*    */ 
/*    */ public class RealmsScrolledSelectionList
/*    */ {
/*    */   private final GuiSlotRealmsProxy proxy;
/*    */   private static final String __OBFID = "CL_00001863";
/*    */   
/*    */   public RealmsScrolledSelectionList(int p_i1119_1_, int p_i1119_2_, int p_i1119_3_, int p_i1119_4_, int p_i1119_5_) {
/* 12 */     this.proxy = new GuiSlotRealmsProxy(this, p_i1119_1_, p_i1119_2_, p_i1119_3_, p_i1119_4_, p_i1119_5_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
/* 17 */     this.proxy.drawScreen(p_render_1_, p_render_2_, p_render_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int width() {
/* 22 */     return this.proxy.func_154338_k();
/*    */   }
/*    */ 
/*    */   
/*    */   public int ym() {
/* 27 */     return this.proxy.func_154339_l();
/*    */   }
/*    */ 
/*    */   
/*    */   public int xm() {
/* 32 */     return this.proxy.func_154337_m();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void renderItem(int p_renderItem_1_, int p_renderItem_2_, int p_renderItem_3_, int p_renderItem_4_, Tezzelator p_renderItem_5_, int p_renderItem_6_, int p_renderItem_7_) {}
/*    */   
/*    */   public void renderItem(int p_renderItem_1_, int p_renderItem_2_, int p_renderItem_3_, int p_renderItem_4_, int p_renderItem_5_, int p_renderItem_6_) {
/* 39 */     renderItem(p_renderItem_1_, p_renderItem_2_, p_renderItem_3_, p_renderItem_4_, Tezzelator.instance, p_renderItem_5_, p_renderItem_6_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getItemCount() {
/* 44 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void selectItem(int p_selectItem_1_, boolean p_selectItem_2_, int p_selectItem_3_, int p_selectItem_4_) {}
/*    */   
/*    */   public boolean isSelectedItem(int p_isSelectedItem_1_) {
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderBackground() {}
/*    */   
/*    */   public int getMaxPosition() {
/* 58 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getScrollbarPosition() {
/* 63 */     return this.proxy.func_154338_k() / 2 + 124;
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseEvent() {
/* 68 */     this.proxy.func_178039_p();
/*    */   }
/*    */ 
/*    */   
/*    */   public void scroll(int p_scroll_1_) {
/* 73 */     this.proxy.scrollBy(p_scroll_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getScroll() {
/* 78 */     return this.proxy.getAmountScrolled();
/*    */   }
/*    */   
/*    */   protected void renderList(int p_renderList_1_, int p_renderList_2_, int p_renderList_3_, int p_renderList_4_) {}
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsScrolledSelectionList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */