/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.resources.Language;
/*     */ import net.minecraft.client.resources.LanguageManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiLanguage
/*     */   extends GuiScreen
/*     */ {
/*     */   protected GuiScreen field_146453_a;
/*     */   private List field_146450_f;
/*     */   private final GameSettings game_settings_3;
/*     */   private final LanguageManager field_146454_h;
/*     */   private GuiOptionButton field_146455_i;
/*     */   private GuiOptionButton field_146452_r;
/*     */   private static final String __OBFID = "CL_00000698";
/*     */   
/*     */   public GuiLanguage(GuiScreen p_i1043_1_, GameSettings p_i1043_2_, LanguageManager p_i1043_3_) {
/*  28 */     this.field_146453_a = p_i1043_1_;
/*  29 */     this.game_settings_3 = p_i1043_2_;
/*  30 */     this.field_146454_h = p_i1043_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  38 */     this.buttonList.add(this.field_146455_i = new GuiOptionButton(100, width / 2 - 155, height - 38, GameSettings.Options.FORCE_UNICODE_FONT, this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT)));
/*  39 */     this.buttonList.add(this.field_146452_r = new GuiOptionButton(6, width / 2 - 155 + 160, height - 38, I18n.format("gui.done", new Object[0])));
/*  40 */     this.field_146450_f = new List(this.mc);
/*  41 */     this.field_146450_f.registerScrollButtons(7, 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  49 */     super.handleMouseInput();
/*  50 */     this.field_146450_f.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  55 */     if (button.enabled) {
/*     */       
/*  57 */       switch (button.id) {
/*     */         case 5:
/*     */           return;
/*     */ 
/*     */         
/*     */         case 6:
/*  63 */           this.mc.displayGuiScreen(this.field_146453_a);
/*     */ 
/*     */         
/*     */         case 100:
/*  67 */           if (button instanceof GuiOptionButton) {
/*     */             
/*  69 */             this.game_settings_3.setOptionValue(((GuiOptionButton)button).returnEnumOptions(), 1);
/*  70 */             button.displayString = this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
/*  71 */             ScaledResolution var2 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/*  72 */             int var3 = var2.getScaledWidth();
/*  73 */             int var4 = var2.getScaledHeight();
/*  74 */             setWorldAndResolution(this.mc, var3, var4);
/*     */           } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  80 */       this.field_146450_f.actionPerformed(button);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  90 */     this.field_146450_f.drawScreen(mouseX, mouseY, partialTicks);
/*  91 */     drawCenteredString(fontRendererObj, I18n.format("options.language", new Object[0]), (width / 2), 16.0F, 16777215);
/*  92 */     drawCenteredString(fontRendererObj, "(" + I18n.format("options.languageWarning", new Object[0]) + ")", (width / 2), (height - 56), 8421504);
/*  93 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */   
/*     */   class List
/*     */     extends GuiSlot {
/*  98 */     private final java.util.List field_148176_l = Lists.newArrayList();
/*  99 */     private final Map field_148177_m = Maps.newHashMap();
/*     */     
/*     */     private static final String __OBFID = "CL_00000699";
/*     */     
/*     */     public List(Minecraft mcIn) {
/* 104 */       super(mcIn, GuiLanguage.width, GuiLanguage.height, 32, GuiLanguage.height - 65 + 4, 18);
/* 105 */       Iterator<Language> var3 = GuiLanguage.this.field_146454_h.getLanguages().iterator();
/*     */       
/* 107 */       while (var3.hasNext()) {
/*     */         
/* 109 */         Language var4 = var3.next();
/* 110 */         this.field_148177_m.put(var4.getLanguageCode(), var4);
/* 111 */         this.field_148176_l.add(var4.getLanguageCode());
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getSize() {
/* 117 */       return this.field_148176_l.size();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
/* 122 */       Language var5 = (Language)this.field_148177_m.get(this.field_148176_l.get(slotIndex));
/* 123 */       GuiLanguage.this.field_146454_h.setCurrentLanguage(var5);
/* 124 */       GuiLanguage.this.game_settings_3.language = var5.getLanguageCode();
/* 125 */       this.mc.refreshResources();
/* 126 */       GuiLanguage.fontRendererObj.setUnicodeFlag(!(!GuiLanguage.this.field_146454_h.isCurrentLocaleUnicode() && !GuiLanguage.this.game_settings_3.forceUnicodeFont));
/* 127 */       GuiLanguage.fontRendererObj.setBidiFlag(GuiLanguage.this.field_146454_h.isCurrentLanguageBidirectional());
/* 128 */       GuiLanguage.this.field_146452_r.displayString = I18n.format("gui.done", new Object[0]);
/* 129 */       GuiLanguage.this.field_146455_i.displayString = GuiLanguage.this.game_settings_3.getKeyBinding(GameSettings.Options.FORCE_UNICODE_FONT);
/* 130 */       GuiLanguage.this.game_settings_3.saveOptions();
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isSelected(int slotIndex) {
/* 135 */       return ((String)this.field_148176_l.get(slotIndex)).equals(GuiLanguage.this.field_146454_h.getCurrentLanguage().getLanguageCode());
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getContentHeight() {
/* 140 */       return getSize() * 18;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawBackground() {
/* 145 */       GuiLanguage.this.drawDefaultBackground();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 150 */       GuiLanguage.fontRendererObj.setBidiFlag(true);
/* 151 */       GuiLanguage.drawCenteredString(GuiLanguage.fontRendererObj, ((Language)this.field_148177_m.get(this.field_148176_l.get(p_180791_1_))).toString(), (this.width / 2), (p_180791_3_ + 1), 16777215);
/* 152 */       GuiLanguage.fontRendererObj.setBidiFlag(GuiLanguage.this.field_146454_h.getCurrentLanguage().isBidirectional());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiLanguage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */