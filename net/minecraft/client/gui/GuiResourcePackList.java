/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.client.resources.ResourcePackListEntry;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ 
/*    */ public abstract class GuiResourcePackList
/*    */   extends GuiListExtended
/*    */ {
/*    */   protected final Minecraft mc;
/*    */   protected final List field_148204_l;
/*    */   private static final String __OBFID = "CL_00000825";
/*    */   
/*    */   public GuiResourcePackList(Minecraft mcIn, int p_i45055_2_, int p_i45055_3_, List p_i45055_4_) {
/* 17 */     super(mcIn, p_i45055_2_, p_i45055_3_, 32, p_i45055_3_ - 55 + 4, 36);
/* 18 */     this.mc = mcIn;
/* 19 */     this.field_148204_l = p_i45055_4_;
/* 20 */     this.field_148163_i = false;
/* 21 */     setHasListHeader(true, (int)(mcIn.fontRendererObj.FONT_HEIGHT * 1.5F));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawListHeader(int p_148129_1_, int p_148129_2_, Tessellator p_148129_3_) {
/* 29 */     String var4 = EnumChatFormatting.UNDERLINE + EnumChatFormatting.BOLD + getListHeader();
/* 30 */     this.mc.fontRendererObj.drawString(var4, (p_148129_1_ + this.width / 2 - this.mc.fontRendererObj.getStringWidth(var4) / 2), Math.min(this.top + 3, p_148129_2_), 16777215);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List getList() {
/* 37 */     return this.field_148204_l;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getSize() {
/* 42 */     return getList().size();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourcePackListEntry getListEntry(int p_148180_1_) {
/* 50 */     return getList().get(p_148180_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getListWidth() {
/* 58 */     return this.width;
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getScrollBarX() {
/* 63 */     return this.right - 6;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GuiListExtended.IGuiListEntry getListEntry1(int p_148180_1_) {
/* 71 */     return (GuiListExtended.IGuiListEntry)getListEntry(p_148180_1_);
/*    */   }
/*    */   
/*    */   protected abstract String getListHeader();
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiResourcePackList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */