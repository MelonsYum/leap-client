/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class GuiDisconnected
/*    */   extends GuiScreen
/*    */ {
/*    */   private String reason;
/*    */   private IChatComponent message;
/*    */   private List multilineMessage;
/*    */   private final GuiScreen parentScreen;
/*    */   private int field_175353_i;
/*    */   private static final String __OBFID = "CL_00000693";
/*    */   
/*    */   public GuiDisconnected(GuiScreen p_i45020_1_, String p_i45020_2_, IChatComponent p_i45020_3_) {
/* 20 */     this.parentScreen = p_i45020_1_;
/* 21 */     this.reason = I18n.format(p_i45020_2_, new Object[0]);
/* 22 */     this.message = p_i45020_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char typedChar, int keyCode) throws IOException {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 36 */     this.buttonList.clear();
/* 37 */     this.multilineMessage = fontRendererObj.listFormattedStringToWidth(this.message.getFormattedText(), width - 50);
/* 38 */     this.field_175353_i = this.multilineMessage.size() * fontRendererObj.FONT_HEIGHT;
/* 39 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 2 + this.field_175353_i / 2 + fontRendererObj.FONT_HEIGHT, I18n.format("gui.toMenu", new Object[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton button) throws IOException {
/* 44 */     if (button.id == 0)
/*    */     {
/* 46 */       this.mc.displayGuiScreen(this.parentScreen);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 55 */     drawDefaultBackground();
/* 56 */     drawCenteredString(fontRendererObj, this.reason, (width / 2), (height / 2 - this.field_175353_i / 2 - fontRendererObj.FONT_HEIGHT * 2), 11184810);
/* 57 */     int var4 = height / 2 - this.field_175353_i / 2;
/*    */     
/* 59 */     if (this.multilineMessage != null)
/*    */     {
/* 61 */       for (Iterator<String> var5 = this.multilineMessage.iterator(); var5.hasNext(); var4 += fontRendererObj.FONT_HEIGHT) {
/*    */         
/* 63 */         String var6 = var5.next();
/* 64 */         drawCenteredString(fontRendererObj, var6, (width / 2), var4, 16777215);
/*    */       } 
/*    */     }
/*    */     
/* 68 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiDisconnected.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */