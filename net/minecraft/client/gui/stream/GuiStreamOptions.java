/*     */ package net.minecraft.client.gui.stream;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiOptionButton;
/*     */ import net.minecraft.client.gui.GuiOptionSlider;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ 
/*     */ public class GuiStreamOptions
/*     */   extends GuiScreen {
/*  14 */   private static final GameSettings.Options[] field_152312_a = new GameSettings.Options[] { GameSettings.Options.STREAM_BYTES_PER_PIXEL, GameSettings.Options.STREAM_FPS, GameSettings.Options.STREAM_KBPS, GameSettings.Options.STREAM_SEND_METADATA, GameSettings.Options.STREAM_VOLUME_MIC, GameSettings.Options.STREAM_VOLUME_SYSTEM, GameSettings.Options.STREAM_MIC_TOGGLE_BEHAVIOR, GameSettings.Options.STREAM_COMPRESSION };
/*  15 */   private static final GameSettings.Options[] field_152316_f = new GameSettings.Options[] { GameSettings.Options.STREAM_CHAT_ENABLED, GameSettings.Options.STREAM_CHAT_USER_FILTER };
/*     */   
/*     */   private final GuiScreen field_152317_g;
/*     */   private final GameSettings field_152318_h;
/*     */   private String field_152319_i;
/*     */   private String field_152313_r;
/*     */   private int field_152314_s;
/*     */   private boolean field_152315_t = false;
/*     */   private static final String __OBFID = "CL_00001841";
/*     */   
/*     */   public GuiStreamOptions(GuiScreen p_i1073_1_, GameSettings p_i1073_2_) {
/*  26 */     this.field_152317_g = p_i1073_1_;
/*  27 */     this.field_152318_h = p_i1073_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  35 */     int var1 = 0;
/*  36 */     this.field_152319_i = I18n.format("options.stream.title", new Object[0]);
/*  37 */     this.field_152313_r = I18n.format("options.stream.chat.title", new Object[0]);
/*  38 */     GameSettings.Options[] var2 = field_152312_a;
/*  39 */     int var3 = var2.length;
/*     */     
/*     */     int var4;
/*     */     
/*  43 */     for (var4 = 0; var4 < var3; var4++) {
/*     */       
/*  45 */       GameSettings.Options var5 = var2[var4];
/*     */       
/*  47 */       if (var5.getEnumFloat()) {
/*     */         
/*  49 */         this.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 + 24 * (var1 >> 1), var5));
/*     */       }
/*     */       else {
/*     */         
/*  53 */         this.buttonList.add(new GuiOptionButton(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 + 24 * (var1 >> 1), var5, this.field_152318_h.getKeyBinding(var5)));
/*     */       } 
/*     */       
/*  56 */       var1++;
/*     */     } 
/*     */     
/*  59 */     if (var1 % 2 == 1)
/*     */     {
/*  61 */       var1++;
/*     */     }
/*     */     
/*  64 */     this.field_152314_s = height / 6 + 24 * (var1 >> 1) + 6;
/*  65 */     var1 += 2;
/*  66 */     var2 = field_152316_f;
/*  67 */     var3 = var2.length;
/*     */     
/*  69 */     for (var4 = 0; var4 < var3; var4++) {
/*     */       
/*  71 */       GameSettings.Options var5 = var2[var4];
/*     */       
/*  73 */       if (var5.getEnumFloat()) {
/*     */         
/*  75 */         this.buttonList.add(new GuiOptionSlider(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 + 24 * (var1 >> 1), var5));
/*     */       }
/*     */       else {
/*     */         
/*  79 */         this.buttonList.add(new GuiOptionButton(var5.returnEnumOrdinal(), width / 2 - 155 + var1 % 2 * 160, height / 6 + 24 * (var1 >> 1), var5, this.field_152318_h.getKeyBinding(var5)));
/*     */       } 
/*     */       
/*  82 */       var1++;
/*     */     } 
/*     */     
/*  85 */     this.buttonList.add(new GuiButton(200, width / 2 - 155, height / 6 + 168, 150, 20, I18n.format("gui.done", new Object[0])));
/*  86 */     GuiButton var6 = new GuiButton(201, width / 2 + 5, height / 6 + 168, 150, 20, I18n.format("options.stream.ingestSelection", new Object[0]));
/*  87 */     var6.enabled = !((!this.mc.getTwitchStream().func_152924_m() || (this.mc.getTwitchStream().func_152925_v()).length <= 0) && !this.mc.getTwitchStream().func_152908_z());
/*  88 */     this.buttonList.add(var6);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  93 */     if (button.enabled) {
/*     */       
/*  95 */       if (button.id < 100 && button instanceof GuiOptionButton) {
/*     */         
/*  97 */         GameSettings.Options var2 = ((GuiOptionButton)button).returnEnumOptions();
/*  98 */         this.field_152318_h.setOptionValue(var2, 1);
/*  99 */         button.displayString = this.field_152318_h.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
/*     */         
/* 101 */         if (this.mc.getTwitchStream().func_152934_n() && var2 != GameSettings.Options.STREAM_CHAT_ENABLED && var2 != GameSettings.Options.STREAM_CHAT_USER_FILTER)
/*     */         {
/* 103 */           this.field_152315_t = true;
/*     */         }
/*     */       }
/* 106 */       else if (button instanceof GuiOptionSlider) {
/*     */         
/* 108 */         if (button.id == GameSettings.Options.STREAM_VOLUME_MIC.returnEnumOrdinal()) {
/*     */           
/* 110 */           this.mc.getTwitchStream().func_152915_s();
/*     */         }
/* 112 */         else if (button.id == GameSettings.Options.STREAM_VOLUME_SYSTEM.returnEnumOrdinal()) {
/*     */           
/* 114 */           this.mc.getTwitchStream().func_152915_s();
/*     */         }
/* 116 */         else if (this.mc.getTwitchStream().func_152934_n()) {
/*     */           
/* 118 */           this.field_152315_t = true;
/*     */         } 
/*     */       } 
/*     */       
/* 122 */       if (button.id == 200) {
/*     */         
/* 124 */         this.mc.gameSettings.saveOptions();
/* 125 */         this.mc.displayGuiScreen(this.field_152317_g);
/*     */       }
/* 127 */       else if (button.id == 201) {
/*     */         
/* 129 */         this.mc.gameSettings.saveOptions();
/* 130 */         this.mc.displayGuiScreen(new GuiIngestServers(this));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 140 */     drawDefaultBackground();
/* 141 */     drawCenteredString(fontRendererObj, this.field_152319_i, (width / 2), 20.0F, 16777215);
/* 142 */     drawCenteredString(fontRendererObj, this.field_152313_r, (width / 2), this.field_152314_s, 16777215);
/*     */     
/* 144 */     if (this.field_152315_t)
/*     */     {
/* 146 */       drawCenteredString(fontRendererObj, EnumChatFormatting.RED + I18n.format("options.stream.changes", new Object[0]), (width / 2), (20 + fontRendererObj.FONT_HEIGHT), 16777215);
/*     */     }
/*     */     
/* 149 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\stream\GuiStreamOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */