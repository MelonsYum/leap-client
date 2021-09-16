/*    */ package net.minecraft.realms;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiTextField;
/*    */ 
/*    */ 
/*    */ public class RealmsEditBox
/*    */ {
/*    */   private final GuiTextField editBox;
/*    */   private static final String __OBFID = "CL_00001858";
/*    */   
/*    */   public RealmsEditBox(int p_i45743_1_, int p_i45743_2_, int p_i45743_3_, int p_i45743_4_, int p_i45743_5_) {
/* 13 */     this.editBox = new GuiTextField(p_i45743_1_, (Minecraft.getMinecraft()).fontRendererObj, p_i45743_2_, p_i45743_3_, p_i45743_4_, p_i45743_5_);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getValue() {
/* 18 */     return this.editBox.getText();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 23 */     this.editBox.updateCursorCounter();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFocus(boolean p_setFocus_1_) {
/* 28 */     this.editBox.setFocused(p_setFocus_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setValue(String p_setValue_1_) {
/* 33 */     this.editBox.setText(p_setValue_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void keyPressed(char p_keyPressed_1_, int p_keyPressed_2_) {
/* 38 */     this.editBox.textboxKeyTyped(p_keyPressed_1_, p_keyPressed_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFocused() {
/* 43 */     return this.editBox.isFocused();
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int p_mouseClicked_1_, int p_mouseClicked_2_, int p_mouseClicked_3_) {
/* 48 */     this.editBox.mouseClicked(p_mouseClicked_1_, p_mouseClicked_2_, p_mouseClicked_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render() {
/* 53 */     this.editBox.drawTextBox();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMaxLength(int p_setMaxLength_1_) {
/* 58 */     this.editBox.setMaxStringLength(p_setMaxLength_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIsEditable(boolean p_setIsEditable_1_) {
/* 63 */     this.editBox.setEnabled(p_setIsEditable_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\RealmsEditBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */