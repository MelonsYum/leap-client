/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ 
/*     */ 
/*     */ public class GuiSnooper
/*     */   extends GuiScreen
/*     */ {
/*     */   private final GuiScreen field_146608_a;
/*     */   private final GameSettings game_settings_2;
/*  18 */   private final java.util.List field_146604_g = Lists.newArrayList();
/*  19 */   private final java.util.List field_146609_h = Lists.newArrayList();
/*     */   
/*     */   private String field_146610_i;
/*     */   private String[] field_146607_r;
/*     */   private List field_146606_s;
/*     */   private GuiButton field_146605_t;
/*     */   private static final String __OBFID = "CL_00000714";
/*     */   
/*     */   public GuiSnooper(GuiScreen p_i1061_1_, GameSettings p_i1061_2_) {
/*  28 */     this.field_146608_a = p_i1061_1_;
/*  29 */     this.game_settings_2 = p_i1061_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  37 */     this.field_146610_i = I18n.format("options.snooper.title", new Object[0]);
/*  38 */     String var1 = I18n.format("options.snooper.desc", new Object[0]);
/*  39 */     ArrayList<String> var2 = Lists.newArrayList();
/*  40 */     Iterator<String> var3 = fontRendererObj.listFormattedStringToWidth(var1, width - 30).iterator();
/*     */     
/*  42 */     while (var3.hasNext()) {
/*     */       
/*  44 */       String var4 = var3.next();
/*  45 */       var2.add(var4);
/*     */     } 
/*     */     
/*  48 */     this.field_146607_r = var2.<String>toArray(new String[0]);
/*  49 */     this.field_146604_g.clear();
/*  50 */     this.field_146609_h.clear();
/*  51 */     this.buttonList.add(this.field_146605_t = new GuiButton(1, width / 2 - 152, height - 30, 150, 20, this.game_settings_2.getKeyBinding(GameSettings.Options.SNOOPER_ENABLED)));
/*  52 */     this.buttonList.add(new GuiButton(2, width / 2 + 2, height - 30, 150, 20, I18n.format("gui.done", new Object[0])));
/*  53 */     boolean var6 = (this.mc.getIntegratedServer() != null && this.mc.getIntegratedServer().getPlayerUsageSnooper() != null);
/*  54 */     Iterator<Map.Entry> var7 = (new TreeMap<>(this.mc.getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();
/*     */ 
/*     */     
/*  57 */     while (var7.hasNext()) {
/*     */       
/*  59 */       Map.Entry var5 = var7.next();
/*  60 */       this.field_146604_g.add(String.valueOf(var6 ? "C " : "") + (String)var5.getKey());
/*  61 */       this.field_146609_h.add(fontRendererObj.trimStringToWidth((String)var5.getValue(), width - 220));
/*     */     } 
/*     */     
/*  64 */     if (var6) {
/*     */       
/*  66 */       var7 = (new TreeMap<>(this.mc.getIntegratedServer().getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();
/*     */       
/*  68 */       while (var7.hasNext()) {
/*     */         
/*  70 */         Map.Entry var5 = var7.next();
/*  71 */         this.field_146604_g.add("S " + (String)var5.getKey());
/*  72 */         this.field_146609_h.add(fontRendererObj.trimStringToWidth((String)var5.getValue(), width - 220));
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     this.field_146606_s = new List();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  84 */     super.handleMouseInput();
/*  85 */     this.field_146606_s.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  90 */     if (button.enabled) {
/*     */       
/*  92 */       if (button.id == 2) {
/*     */         
/*  94 */         this.game_settings_2.saveOptions();
/*  95 */         this.game_settings_2.saveOptions();
/*  96 */         this.mc.displayGuiScreen(this.field_146608_a);
/*     */       } 
/*     */       
/*  99 */       if (button.id == 1) {
/*     */         
/* 101 */         this.game_settings_2.setOptionValue(GameSettings.Options.SNOOPER_ENABLED, 1);
/* 102 */         this.field_146605_t.displayString = this.game_settings_2.getKeyBinding(GameSettings.Options.SNOOPER_ENABLED);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 112 */     drawDefaultBackground();
/* 113 */     this.field_146606_s.drawScreen(mouseX, mouseY, partialTicks);
/* 114 */     drawCenteredString(fontRendererObj, this.field_146610_i, (width / 2), 8.0F, 16777215);
/* 115 */     int var4 = 22;
/* 116 */     String[] var5 = this.field_146607_r;
/* 117 */     int var6 = var5.length;
/*     */     
/* 119 */     for (int var7 = 0; var7 < var6; var7++) {
/*     */       
/* 121 */       String var8 = var5[var7];
/* 122 */       drawCenteredString(fontRendererObj, var8, (width / 2), var4, 8421504);
/* 123 */       var4 += fontRendererObj.FONT_HEIGHT;
/*     */     } 
/*     */     
/* 126 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */   
/*     */   class List
/*     */     extends GuiSlot
/*     */   {
/*     */     private static final String __OBFID = "CL_00000715";
/*     */     
/*     */     public List() {
/* 135 */       super(GuiSnooper.this.mc, GuiSnooper.width, GuiSnooper.height, 80, GuiSnooper.height - 40, GuiSnooper.fontRendererObj.FONT_HEIGHT + 1);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getSize() {
/* 140 */       return GuiSnooper.this.field_146604_g.size();
/*     */     }
/*     */ 
/*     */     
/*     */     protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {}
/*     */     
/*     */     protected boolean isSelected(int slotIndex) {
/* 147 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawBackground() {}
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 154 */       GuiSnooper.fontRendererObj.drawString(GuiSnooper.this.field_146604_g.get(p_180791_1_), 10.0D, p_180791_3_, 16777215);
/* 155 */       GuiSnooper.fontRendererObj.drawString(GuiSnooper.this.field_146609_h.get(p_180791_1_), 230.0D, p_180791_3_, 16777215);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getScrollBarX() {
/* 160 */       return this.width - 10;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiSnooper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */