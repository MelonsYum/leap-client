/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ 
/*    */ public class GuiErrorScreen
/*    */   extends GuiScreen
/*    */ {
/*    */   private String field_146313_a;
/*    */   private String field_146312_f;
/*    */   private static final String __OBFID = "CL_00000696";
/*    */   
/*    */   public GuiErrorScreen(String p_i46319_1_, String p_i46319_2_) {
/* 14 */     this.field_146313_a = p_i46319_1_;
/* 15 */     this.field_146312_f = p_i46319_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 23 */     super.initGui();
/* 24 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, 140, I18n.format("gui.cancel", new Object[0])));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 32 */     drawGradientRect(0.0F, 0.0F, width, height, -12574688, -11530224);
/* 33 */     drawCenteredString(fontRendererObj, this.field_146313_a, (width / 2), 90.0F, 16777215);
/* 34 */     drawCenteredString(fontRendererObj, this.field_146312_f, (width / 2), 110.0F, 16777215);
/* 35 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char typedChar, int keyCode) throws IOException {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton button) throws IOException {
/* 46 */     this.mc.displayGuiScreen(null);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiErrorScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */