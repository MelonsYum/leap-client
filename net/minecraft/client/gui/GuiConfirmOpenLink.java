/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiConfirmOpenLink
/*    */   extends GuiYesNo
/*    */ {
/*    */   private final String openLinkWarning;
/*    */   private final String copyLinkButtonText;
/*    */   private final String linkText;
/*    */   private boolean showSecurityWarning = true;
/*    */   private static final String __OBFID = "CL_00000683";
/*    */   
/*    */   public GuiConfirmOpenLink(GuiYesNoCallback p_i1084_1_, String p_i1084_2_, int p_i1084_3_, boolean p_i1084_4_) {
/* 19 */     super(p_i1084_1_, I18n.format(p_i1084_4_ ? "chat.link.confirmTrusted" : "chat.link.confirm", new Object[0]), p_i1084_2_, p_i1084_3_);
/* 20 */     this.confirmButtonText = I18n.format(p_i1084_4_ ? "chat.link.open" : "gui.yes", new Object[0]);
/* 21 */     this.cancelButtonText = I18n.format(p_i1084_4_ ? "gui.cancel" : "gui.no", new Object[0]);
/* 22 */     this.copyLinkButtonText = I18n.format("chat.copy", new Object[0]);
/* 23 */     this.openLinkWarning = I18n.format("chat.link.warning", new Object[0]);
/* 24 */     this.linkText = p_i1084_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 32 */     this.buttonList.add(new GuiButton(0, width / 2 - 50 - 105, height / 6 + 96, 100, 20, this.confirmButtonText));
/* 33 */     this.buttonList.add(new GuiButton(2, width / 2 - 50, height / 6 + 96, 100, 20, this.copyLinkButtonText));
/* 34 */     this.buttonList.add(new GuiButton(1, width / 2 - 50 + 105, height / 6 + 96, 100, 20, this.cancelButtonText));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton button) throws IOException {
/* 39 */     if (button.id == 2)
/*    */     {
/* 41 */       copyLinkToClipboard();
/*    */     }
/*    */     
/* 44 */     this.parentScreen.confirmClicked((button.id == 0), this.parentButtonClickedId);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void copyLinkToClipboard() {
/* 52 */     setClipboardString(this.linkText);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 60 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */     
/* 62 */     if (this.showSecurityWarning)
/*    */     {
/* 64 */       drawCenteredString(fontRendererObj, this.openLinkWarning, (width / 2), 110.0F, 16764108);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void disableSecurityWarning() {
/* 70 */     this.showSecurityWarning = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiConfirmOpenLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */