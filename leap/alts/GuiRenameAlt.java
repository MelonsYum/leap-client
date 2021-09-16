/*    */ package leap.alts;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.GuiTextField;
/*    */ import net.minecraft.util.EnumChatFormatting;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuiRenameAlt
/*    */   extends GuiScreen
/*    */ {
/*    */   private final GuiAltManager manager;
/*    */   private GuiTextField nameField;
/*    */   private PasswordField pwField;
/* 19 */   private String status = EnumChatFormatting.GRAY + "Waiting...";
/*    */   
/*    */   public GuiRenameAlt(GuiAltManager manager) {
/* 22 */     this.manager = manager;
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(GuiButton button) {
/* 27 */     switch (button.id) {
/*    */       case 1:
/* 29 */         this.mc.displayGuiScreen(this.manager);
/*    */         break;
/*    */       
/*    */       case 0:
/* 33 */         this.manager.selectedAlt.setMask(this.nameField.getText());
/* 34 */         this.manager.selectedAlt.setPassword(this.pwField.getText());
/* 35 */         this.status = "Edited!";
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int par1, int par2, float par3) {
/* 42 */     drawDefaultBackground();
/* 43 */     drawCenteredString(fontRendererObj, "Edit Alt", (width / 2), 10.0F, -1);
/* 44 */     drawCenteredString(fontRendererObj, this.status, (width / 2), 20.0F, -1);
/* 45 */     this.nameField.drawTextBox();
/* 46 */     this.pwField.drawTextBox();
/* 47 */     if (this.nameField.getText().isEmpty()) {
/* 48 */       drawString(this.mc.fontRendererObj, "New name", width / 2 - 96, 66, -7829368);
/*    */     }
/* 50 */     if (this.pwField.getText().isEmpty()) {
/* 51 */       drawString(this.mc.fontRendererObj, "New password", width / 2 - 96, 106, -7829368);
/*    */     }
/* 53 */     super.drawScreen(par1, par2, par3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 58 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 92 + 12, "Edit"));
/* 59 */     this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 116 + 12, "Cancel"));
/* 60 */     this.nameField = new GuiTextField(this.eventButton, this.mc.fontRendererObj, width / 2 - 100, 60, 200, 20);
/* 61 */     this.pwField = new PasswordField(this.mc.fontRendererObj, width / 2 - 100, 100, 200, 20);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void keyTyped(char par1, int par2) {
/* 66 */     this.nameField.textboxKeyTyped(par1, par2);
/* 67 */     this.pwField.textboxKeyTyped(par1, par2);
/* 68 */     if (par1 == '\t' && (this.nameField.isFocused() || this.pwField.isFocused())) {
/* 69 */       this.nameField.setFocused(!this.nameField.isFocused());
/* 70 */       this.pwField.setFocused(!this.pwField.isFocused());
/*    */     } 
/* 72 */     if (par1 == '\r') {
/* 73 */       actionPerformed(this.buttonList.get(0));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected void mouseClicked(int par1, int par2, int par3) {
/*    */     try {
/* 80 */       super.mouseClicked(par1, par2, par3);
/*    */     }
/* 82 */     catch (IOException e) {
/* 83 */       e.printStackTrace();
/*    */     } 
/* 85 */     this.nameField.mouseClicked(par1, par2, par3);
/* 86 */     this.pwField.mouseClicked(par1, par2, par3);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\alts\GuiRenameAlt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */