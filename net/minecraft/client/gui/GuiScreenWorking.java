/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import net.minecraft.util.IProgressUpdate;
/*    */ 
/*    */ public class GuiScreenWorking
/*    */   extends GuiScreen implements IProgressUpdate {
/*  7 */   private String field_146591_a = "";
/*  8 */   private String field_146589_f = "";
/*    */   
/*    */   private int field_146590_g;
/*    */   
/*    */   private boolean field_146592_h;
/*    */   
/*    */   private static final String __OBFID = "CL_00000707";
/*    */ 
/*    */   
/*    */   public void displaySavingString(String message) {
/* 18 */     resetProgressAndMessage(message);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetProgressAndMessage(String p_73721_1_) {
/* 27 */     this.field_146591_a = p_73721_1_;
/* 28 */     displayLoadingString("Working...");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void displayLoadingString(String message) {
/* 36 */     this.field_146589_f = message;
/* 37 */     setLoadingProgress(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLoadingProgress(int progress) {
/* 45 */     this.field_146590_g = progress;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDoneWorking() {
/* 50 */     this.field_146592_h = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 58 */     if (this.field_146592_h) {
/*    */       
/* 60 */       this.mc.displayGuiScreen(null);
/*    */     }
/*    */     else {
/*    */       
/* 64 */       drawDefaultBackground();
/* 65 */       drawCenteredString(fontRendererObj, this.field_146591_a, (width / 2), 70.0F, 16777215);
/* 66 */       drawCenteredString(fontRendererObj, String.valueOf(this.field_146589_f) + " " + this.field_146590_g + "%", (width / 2), 90.0F, 16777215);
/* 67 */       super.drawScreen(mouseX, mouseY, partialTicks);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiScreenWorking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */