/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.world.storage.ISaveFormat;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiRenameWorld
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiScreen field_146585_a;
/*     */   private GuiTextField field_146583_f;
/*     */   private final String field_146584_g;
/*     */   private static final String __OBFID = "CL_00000709";
/*     */   
/*     */   public GuiRenameWorld(GuiScreen p_i46317_1_, String p_i46317_2_) {
/*  18 */     this.field_146585_a = p_i46317_1_;
/*  19 */     this.field_146584_g = p_i46317_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  27 */     this.field_146583_f.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  35 */     Keyboard.enableRepeatEvents(true);
/*  36 */     this.buttonList.clear();
/*  37 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 12, I18n.format("selectWorld.renameButton", new Object[0])));
/*  38 */     this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 12, I18n.format("gui.cancel", new Object[0])));
/*  39 */     ISaveFormat var1 = this.mc.getSaveLoader();
/*  40 */     WorldInfo var2 = var1.getWorldInfo(this.field_146584_g);
/*  41 */     String var3 = var2.getWorldName();
/*  42 */     this.field_146583_f = new GuiTextField(2, fontRendererObj, width / 2 - 100, 60, 200, 20);
/*  43 */     this.field_146583_f.setFocused(true);
/*  44 */     this.field_146583_f.setText(var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  52 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  57 */     if (button.enabled)
/*     */     {
/*  59 */       if (button.id == 1) {
/*     */         
/*  61 */         this.mc.displayGuiScreen(this.field_146585_a);
/*     */       }
/*  63 */       else if (button.id == 0) {
/*     */         
/*  65 */         ISaveFormat var2 = this.mc.getSaveLoader();
/*  66 */         var2.renameWorld(this.field_146584_g, this.field_146583_f.getText().trim());
/*  67 */         this.mc.displayGuiScreen(this.field_146585_a);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/*  78 */     this.field_146583_f.textboxKeyTyped(typedChar, keyCode);
/*  79 */     ((GuiButton)this.buttonList.get(0)).enabled = (this.field_146583_f.getText().trim().length() > 0);
/*     */     
/*  81 */     if (keyCode == 28 || keyCode == 156)
/*     */     {
/*  83 */       actionPerformed(this.buttonList.get(0));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/*  92 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*  93 */     this.field_146583_f.mouseClicked(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 101 */     drawDefaultBackground();
/* 102 */     drawCenteredString(fontRendererObj, I18n.format("selectWorld.renameTitle", new Object[0]), (width / 2), 20.0F, 16777215);
/* 103 */     drawString(fontRendererObj, I18n.format("selectWorld.enterName", new Object[0]), width / 2 - 100, 47, 10526880);
/* 104 */     this.field_146583_f.drawTextBox();
/* 105 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiRenameWorld.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */