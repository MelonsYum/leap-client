/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.realms.RealmsScrolledSelectionList;
/*    */ 
/*    */ public class GuiSlotRealmsProxy
/*    */   extends GuiSlot
/*    */ {
/*    */   private final RealmsScrolledSelectionList selectionList;
/*    */   private static final String __OBFID = "CL_00001846";
/*    */   
/*    */   public GuiSlotRealmsProxy(RealmsScrolledSelectionList selectionListIn, int p_i1085_2_, int p_i1085_3_, int p_i1085_4_, int p_i1085_5_, int p_i1085_6_) {
/* 13 */     super(Minecraft.getMinecraft(), p_i1085_2_, p_i1085_3_, p_i1085_4_, p_i1085_5_, p_i1085_6_);
/* 14 */     this.selectionList = selectionListIn;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getSize() {
/* 19 */     return this.selectionList.getItemCount();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
/* 27 */     this.selectionList.selectItem(slotIndex, isDoubleClick, mouseX, mouseY);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isSelected(int slotIndex) {
/* 35 */     return this.selectionList.isSelectedItem(slotIndex);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void drawBackground() {
/* 40 */     this.selectionList.renderBackground();
/*    */   }
/*    */ 
/*    */   
/*    */   protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 45 */     this.selectionList.renderItem(p_180791_1_, p_180791_2_, p_180791_3_, p_180791_4_, p_180791_5_, p_180791_6_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_154338_k() {
/* 50 */     return this.width;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_154339_l() {
/* 55 */     return this.mouseY;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_154337_m() {
/* 60 */     return this.mouseX;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getContentHeight() {
/* 68 */     return this.selectionList.getMaxPosition();
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getScrollBarX() {
/* 73 */     return this.selectionList.getScrollbarPosition();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178039_p() {
/* 78 */     super.func_178039_p();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiSlotRealmsProxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */