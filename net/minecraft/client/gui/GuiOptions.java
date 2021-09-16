/*     */ package net.minecraft.client.gui;
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.audio.SoundCategory;
/*     */ import net.minecraft.client.audio.SoundEventAccessorComposite;
/*     */ import net.minecraft.client.audio.SoundHandler;
/*     */ import net.minecraft.client.gui.stream.GuiStreamOptions;
/*     */ import net.minecraft.client.gui.stream.GuiStreamUnavailable;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.client.stream.IStream;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ 
/*     */ public class GuiOptions extends GuiScreen implements GuiYesNoCallback {
/*  19 */   private static final GameSettings.Options[] field_146440_f = new GameSettings.Options[] { GameSettings.Options.FOV };
/*     */   
/*     */   private final GuiScreen field_146441_g;
/*     */   
/*     */   private final GameSettings game_settings_1;
/*     */   private GuiButton field_175357_i;
/*     */   private GuiLockIconButton field_175356_r;
/*  26 */   protected String field_146442_a = "Options";
/*     */   
/*     */   private static final String __OBFID = "CL_00000700";
/*     */   
/*     */   public GuiOptions(GuiScreen p_i1046_1_, GameSettings p_i1046_2_) {
/*  31 */     this.field_146441_g = p_i1046_1_;
/*  32 */     this.game_settings_1 = p_i1046_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  40 */     int var1 = 0;
/*  41 */     this.field_146442_a = I18n.format("options.title", new Object[0]);
/*  42 */     GameSettings.Options[] var2 = field_146440_f;
/*  43 */     int var3 = var2.length;
/*     */     
/*  45 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/*  47 */       GameSettings.Options var5 = var2[var4];
/*     */       
/*  49 */       if (var5.getEnumFloat()) {
/*     */         
/*  51 */         this.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 - 12 + 24 * (var1 >> 1), var5));
/*     */       }
/*     */       else {
/*     */         
/*  55 */         GuiOptionButton var6 = new GuiOptionButton(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 - 12 + 24 * (var1 >> 1), var5, this.game_settings_1.getKeyBinding(var5));
/*  56 */         this.buttonList.add(var6);
/*     */       } 
/*     */       
/*  59 */       var1++;
/*     */     } 
/*     */     
/*  62 */     if (this.mc.theWorld != null) {
/*     */       
/*  64 */       EnumDifficulty var7 = this.mc.theWorld.getDifficulty();
/*  65 */       this.field_175357_i = new GuiButton(108, width / 2 - 155 + var1 % 2 * 160, height / 6 - 12 + 24 * (var1 >> 1), 150, 20, func_175355_a(var7));
/*  66 */       this.buttonList.add(this.field_175357_i);
/*     */       
/*  68 */       if (this.mc.isSingleplayer() && !this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
/*     */         
/*  70 */         this.field_175357_i.func_175211_a(this.field_175357_i.getButtonWidth() - 20);
/*  71 */         this.field_175356_r = new GuiLockIconButton(109, this.field_175357_i.xPosition + this.field_175357_i.getButtonWidth(), this.field_175357_i.yPosition);
/*  72 */         this.buttonList.add(this.field_175356_r);
/*  73 */         this.field_175356_r.func_175229_b(this.mc.theWorld.getWorldInfo().isDifficultyLocked());
/*  74 */         this.field_175356_r.enabled = !this.field_175356_r.func_175230_c();
/*  75 */         this.field_175357_i.enabled = !this.field_175356_r.func_175230_c();
/*     */       }
/*     */       else {
/*     */         
/*  79 */         this.field_175357_i.enabled = false;
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     this.buttonList.add(new GuiButton(110, width / 2 - 155, height / 6 + 48 - 6, 150, 20, I18n.format("options.skinCustomisation", new Object[0])));
/*  84 */     this.buttonList.add(new GuiButton(8675309, width / 2 + 5, height / 6 + 48 - 6, 150, 20, "Super Secret Settings...")
/*     */         {
/*     */           private static final String __OBFID = "CL_00000701";
/*     */           
/*     */           public void playPressSound(SoundHandler soundHandlerIn) {
/*  89 */             SoundEventAccessorComposite var2 = soundHandlerIn.getRandomSoundFromCategories(new SoundCategory[] { SoundCategory.ANIMALS, SoundCategory.BLOCKS, SoundCategory.MOBS, SoundCategory.PLAYERS, SoundCategory.WEATHER });
/*     */             
/*  91 */             if (var2 != null)
/*     */             {
/*  93 */               soundHandlerIn.playSound((ISound)PositionedSoundRecord.createPositionedSoundRecord(var2.getSoundEventLocation(), 0.5F));
/*     */             }
/*     */           }
/*     */         });
/*  97 */     this.buttonList.add(new GuiButton(106, width / 2 - 155, height / 6 + 72 - 6, 150, 20, I18n.format("options.sounds", new Object[0])));
/*  98 */     this.buttonList.add(new GuiButton(107, width / 2 + 5, height / 6 + 72 - 6, 150, 20, I18n.format("options.stream", new Object[0])));
/*  99 */     this.buttonList.add(new GuiButton(101, width / 2 - 155, height / 6 + 96 - 6, 150, 20, I18n.format("options.video", new Object[0])));
/* 100 */     this.buttonList.add(new GuiButton(100, width / 2 + 5, height / 6 + 96 - 6, 150, 20, I18n.format("options.controls", new Object[0])));
/* 101 */     this.buttonList.add(new GuiButton(102, width / 2 - 155, height / 6 + 120 - 6, 150, 20, I18n.format("options.language", new Object[0])));
/* 102 */     this.buttonList.add(new GuiButton(103, width / 2 + 5, height / 6 + 120 - 6, 150, 20, I18n.format("options.multiplayer.title", new Object[0])));
/* 103 */     this.buttonList.add(new GuiButton(105, width / 2 - 155, height / 6 + 144 - 6, 150, 20, I18n.format("options.resourcepack", new Object[0])));
/* 104 */     this.buttonList.add(new GuiButton(104, width / 2 + 5, height / 6 + 144 - 6, 150, 20, I18n.format("options.snooper.view", new Object[0])));
/* 105 */     this.buttonList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, I18n.format("gui.done", new Object[0])));
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_175355_a(EnumDifficulty p_175355_1_) {
/* 110 */     ChatComponentText var2 = new ChatComponentText("");
/* 111 */     var2.appendSibling((IChatComponent)new ChatComponentTranslation("options.difficulty", new Object[0]));
/* 112 */     var2.appendText(": ");
/* 113 */     var2.appendSibling((IChatComponent)new ChatComponentTranslation(p_175355_1_.getDifficultyResourceKey(), new Object[0]));
/* 114 */     return var2.getFormattedText();
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean result, int id) {
/* 119 */     this.mc.displayGuiScreen(this);
/*     */     
/* 121 */     if (id == 109 && result && this.mc.theWorld != null) {
/*     */       
/* 123 */       this.mc.theWorld.getWorldInfo().setDifficultyLocked(true);
/* 124 */       this.field_175356_r.func_175229_b(true);
/* 125 */       this.field_175356_r.enabled = false;
/* 126 */       this.field_175357_i.enabled = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 132 */     if (button.enabled) {
/*     */       
/* 134 */       if (button.id < 100 && button instanceof GuiOptionButton) {
/*     */         
/* 136 */         GameSettings.Options var2 = ((GuiOptionButton)button).returnEnumOptions();
/* 137 */         this.game_settings_1.setOptionValue(var2, 1);
/* 138 */         button.displayString = this.game_settings_1.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
/*     */       } 
/*     */       
/* 141 */       if (button.id == 108) {
/*     */         
/* 143 */         this.mc.theWorld.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum(this.mc.theWorld.getDifficulty().getDifficultyId() + 1));
/* 144 */         this.field_175357_i.displayString = func_175355_a(this.mc.theWorld.getDifficulty());
/*     */       } 
/*     */       
/* 147 */       if (button.id == 109)
/*     */       {
/* 149 */         this.mc.displayGuiScreen(new GuiYesNo(this, (new ChatComponentTranslation("difficulty.lock.title", new Object[0])).getFormattedText(), (new ChatComponentTranslation("difficulty.lock.question", new Object[] { new ChatComponentTranslation(this.mc.theWorld.getWorldInfo().getDifficulty().getDifficultyResourceKey(), new Object[0]) })).getFormattedText(), 109));
/*     */       }
/*     */       
/* 152 */       if (button.id == 110) {
/*     */         
/* 154 */         this.mc.gameSettings.saveOptions();
/* 155 */         this.mc.displayGuiScreen(new GuiCustomizeSkin(this));
/*     */       } 
/*     */       
/* 158 */       if (button.id == 8675309)
/*     */       {
/* 160 */         this.mc.entityRenderer.activateNextShader();
/*     */       }
/*     */       
/* 163 */       if (button.id == 101) {
/*     */         
/* 165 */         this.mc.gameSettings.saveOptions();
/* 166 */         this.mc.displayGuiScreen(new GuiVideoSettings(this, this.game_settings_1));
/*     */       } 
/*     */       
/* 169 */       if (button.id == 100) {
/*     */         
/* 171 */         this.mc.gameSettings.saveOptions();
/* 172 */         this.mc.displayGuiScreen(new GuiControls(this, this.game_settings_1));
/*     */       } 
/*     */       
/* 175 */       if (button.id == 102) {
/*     */         
/* 177 */         this.mc.gameSettings.saveOptions();
/* 178 */         this.mc.displayGuiScreen(new GuiLanguage(this, this.game_settings_1, this.mc.getLanguageManager()));
/*     */       } 
/*     */       
/* 181 */       if (button.id == 103) {
/*     */         
/* 183 */         this.mc.gameSettings.saveOptions();
/* 184 */         this.mc.displayGuiScreen(new ScreenChatOptions(this, this.game_settings_1));
/*     */       } 
/*     */       
/* 187 */       if (button.id == 104) {
/*     */         
/* 189 */         this.mc.gameSettings.saveOptions();
/* 190 */         this.mc.displayGuiScreen(new GuiSnooper(this, this.game_settings_1));
/*     */       } 
/*     */       
/* 193 */       if (button.id == 200) {
/*     */         
/* 195 */         this.mc.gameSettings.saveOptions();
/* 196 */         this.mc.displayGuiScreen(this.field_146441_g);
/*     */       } 
/*     */       
/* 199 */       if (button.id == 105) {
/*     */         
/* 201 */         this.mc.gameSettings.saveOptions();
/* 202 */         this.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
/*     */       } 
/*     */       
/* 205 */       if (button.id == 106) {
/*     */         
/* 207 */         this.mc.gameSettings.saveOptions();
/* 208 */         this.mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.game_settings_1));
/*     */       } 
/*     */       
/* 211 */       if (button.id == 107) {
/*     */         
/* 213 */         this.mc.gameSettings.saveOptions();
/* 214 */         IStream var3 = this.mc.getTwitchStream();
/*     */         
/* 216 */         if (var3.func_152936_l() && var3.func_152928_D()) {
/*     */           
/* 218 */           this.mc.displayGuiScreen((GuiScreen)new GuiStreamOptions(this, this.game_settings_1));
/*     */         }
/*     */         else {
/*     */           
/* 222 */           GuiStreamUnavailable.func_152321_a(this);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 233 */     drawDefaultBackground();
/* 234 */     drawCenteredString(fontRendererObj, this.field_146442_a, (width / 2), 15.0F, 16777215);
/* 235 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */