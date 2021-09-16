/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public abstract class GuiListExtended
/*    */   extends GuiSlot
/*    */ {
/*    */   private static final String __OBFID = "CL_00000674";
/*    */   
/*    */   public GuiListExtended(Minecraft mcIn, int p_i45010_2_, int p_i45010_3_, int p_i45010_4_, int p_i45010_5_, int p_i45010_6_) {
/* 11 */     super(mcIn, p_i45010_2_, p_i45010_3_, p_i45010_4_, p_i45010_5_, p_i45010_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean isSelected(int slotIndex) {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void drawBackground() {}
/*    */   
/*    */   protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 31 */     getListEntry(p_180791_1_).drawEntry(p_180791_1_, p_180791_2_, p_180791_3_, getListWidth(), p_180791_4_, p_180791_5_, p_180791_6_, (getSlotIndexFromScreenCoords(p_180791_5_, p_180791_6_) == p_180791_1_));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_178040_a(int p_178040_1_, int p_178040_2_, int p_178040_3_) {
/* 36 */     getListEntry(p_178040_1_).setSelected(p_178040_1_, p_178040_2_, p_178040_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_148179_a(int p_148179_1_, int p_148179_2_, int p_148179_3_) {
/* 41 */     if (isMouseYWithinSlotBounds(p_148179_2_)) {
/*    */       
/* 43 */       int var4 = getSlotIndexFromScreenCoords(p_148179_1_, p_148179_2_);
/*    */       
/* 45 */       if (var4 >= 0) {
/*    */         
/* 47 */         int var5 = this.left + this.width / 2 - getListWidth() / 2 + 2;
/* 48 */         int var6 = this.top + 4 - getAmountScrolled() + var4 * this.slotHeight + this.headerPadding;
/* 49 */         int var7 = p_148179_1_ - var5;
/* 50 */         int var8 = p_148179_2_ - var6;
/*    */         
/* 52 */         if (getListEntry(var4).mousePressed(var4, p_148179_1_, p_148179_2_, p_148179_3_, var7, var8)) {
/*    */           
/* 54 */           setEnabled(false);
/* 55 */           return true;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_148181_b(int p_148181_1_, int p_148181_2_, int p_148181_3_) {
/* 65 */     for (int var4 = 0; var4 < getSize(); var4++) {
/*    */       
/* 67 */       int var5 = this.left + this.width / 2 - getListWidth() / 2 + 2;
/* 68 */       int var6 = this.top + 4 - getAmountScrolled() + var4 * this.slotHeight + this.headerPadding;
/* 69 */       int var7 = p_148181_1_ - var5;
/* 70 */       int var8 = p_148181_2_ - var6;
/* 71 */       getListEntry(var4).mouseReleased(var4, p_148181_1_, p_148181_2_, p_148181_3_, var7, var8);
/*    */     } 
/*    */     
/* 74 */     setEnabled(true);
/* 75 */     return false;
/*    */   }
/*    */   
/*    */   public abstract IGuiListEntry getListEntry(int paramInt);
/*    */   
/*    */   public static interface IGuiListEntry {
/*    */     void setSelected(int param1Int1, int param1Int2, int param1Int3);
/*    */     
/*    */     void drawEntry(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, int param1Int7, boolean param1Boolean);
/*    */     
/*    */     boolean mousePressed(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6);
/*    */     
/*    */     void mouseReleased(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiListExtended.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */