/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ 
/*     */ public class GuiControls
/*     */   extends GuiScreen {
/*  11 */   private static final GameSettings.Options[] optionsArr = new GameSettings.Options[] { GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN };
/*     */ 
/*     */   
/*     */   private GuiScreen parentScreen;
/*     */ 
/*     */   
/*  17 */   protected String screenTitle = "Controls";
/*     */ 
/*     */   
/*     */   private GameSettings options;
/*     */ 
/*     */   
/*  23 */   public KeyBinding buttonId = null;
/*     */   
/*     */   public long time;
/*     */   private GuiKeyBindingList keyBindingList;
/*     */   private GuiButton buttonReset;
/*     */   private static final String __OBFID = "CL_00000736";
/*     */   
/*     */   public GuiControls(GuiScreen p_i1027_1_, GameSettings p_i1027_2_) {
/*  31 */     this.parentScreen = p_i1027_1_;
/*  32 */     this.options = p_i1027_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  40 */     this.keyBindingList = new GuiKeyBindingList(this, this.mc);
/*  41 */     this.buttonList.add(new GuiButton(200, width / 2 - 155, height - 29, 150, 20, I18n.format("gui.done", new Object[0])));
/*  42 */     this.buttonList.add(this.buttonReset = new GuiButton(201, width / 2 - 155 + 160, height - 29, 150, 20, I18n.format("controls.resetAll", new Object[0])));
/*  43 */     this.screenTitle = I18n.format("controls.title", new Object[0]);
/*  44 */     int var1 = 0;
/*  45 */     GameSettings.Options[] var2 = optionsArr;
/*  46 */     int var3 = var2.length;
/*     */     
/*  48 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  50 */       GameSettings.Options var5 = var2[var4];
/*     */       
/*  52 */       if (var5.getEnumFloat()) {
/*     */         
/*  54 */         this.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, 18 + 24 * (var1 >> 1), var5));
/*     */       }
/*     */       else {
/*     */         
/*  58 */         this.buttonList.add(new GuiOptionButton(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, 18 + 24 * (var1 >> 1), var5, this.options.getKeyBinding(var5)));
/*     */       } 
/*     */       
/*  61 */       var1++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  70 */     super.handleMouseInput();
/*  71 */     this.keyBindingList.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  76 */     if (button.id == 200) {
/*     */       
/*  78 */       this.mc.displayGuiScreen(this.parentScreen);
/*     */     }
/*  80 */     else if (button.id == 201) {
/*     */       
/*  82 */       KeyBinding[] var2 = this.mc.gameSettings.keyBindings;
/*  83 */       int var3 = var2.length;
/*     */       
/*  85 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/*  87 */         KeyBinding var5 = var2[var4];
/*  88 */         var5.setKeyCode(var5.getKeyCodeDefault());
/*     */       } 
/*     */       
/*  91 */       KeyBinding.resetKeyBindingArrayAndHash();
/*     */     }
/*  93 */     else if (button.id < 100 && button instanceof GuiOptionButton) {
/*     */       
/*  95 */       this.options.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
/*  96 */       button.displayString = this.options.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 105 */     if (this.buttonId != null) {
/*     */       
/* 107 */       this.options.setOptionKeyBinding(this.buttonId, -100 + mouseButton);
/* 108 */       this.buttonId = null;
/* 109 */       KeyBinding.resetKeyBindingArrayAndHash();
/*     */     }
/* 111 */     else if (mouseButton != 0 || !this.keyBindingList.func_148179_a(mouseX, mouseY, mouseButton)) {
/*     */       
/* 113 */       super.mouseClicked(mouseX, mouseY, mouseButton);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseReleased(int mouseX, int mouseY, int state) {
/* 122 */     if (state != 0 || !this.keyBindingList.func_148181_b(mouseX, mouseY, state))
/*     */     {
/* 124 */       super.mouseReleased(mouseX, mouseY, state);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/* 134 */     if (this.buttonId != null) {
/*     */       
/* 136 */       if (keyCode == 1) {
/*     */         
/* 138 */         this.options.setOptionKeyBinding(this.buttonId, 0);
/*     */       }
/* 140 */       else if (keyCode != 0) {
/*     */         
/* 142 */         this.options.setOptionKeyBinding(this.buttonId, keyCode);
/*     */       }
/* 144 */       else if (typedChar > '\000') {
/*     */         
/* 146 */         this.options.setOptionKeyBinding(this.buttonId, typedChar + 256);
/*     */       } 
/*     */       
/* 149 */       this.buttonId = null;
/* 150 */       this.time = Minecraft.getSystemTime();
/* 151 */       KeyBinding.resetKeyBindingArrayAndHash();
/*     */     }
/*     */     else {
/*     */       
/* 155 */       super.keyTyped(typedChar, keyCode);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 164 */     drawDefaultBackground();
/* 165 */     this.keyBindingList.drawScreen(mouseX, mouseY, partialTicks);
/* 166 */     drawCenteredString(fontRendererObj, this.screenTitle, (width / 2), 8.0F, 16777215);
/* 167 */     boolean var4 = true;
/* 168 */     KeyBinding[] var5 = this.options.keyBindings;
/* 169 */     int var6 = var5.length;
/*     */     
/* 171 */     for (int var7 = 0; var7 < var6; var7++) {
/*     */       
/* 173 */       KeyBinding var8 = var5[var7];
/*     */       
/* 175 */       if (var8.getKeyCode() != var8.getKeyCodeDefault()) {
/*     */         
/* 177 */         var4 = false;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 182 */     this.buttonReset.enabled = !var4;
/* 183 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiControls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */