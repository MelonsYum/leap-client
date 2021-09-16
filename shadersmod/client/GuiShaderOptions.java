/*     */ package shadersmod.client;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiVideoSettings;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import optifine.Config;
/*     */ import optifine.Lang;
/*     */ import optifine.StrUtils;
/*     */ 
/*     */ public class GuiShaderOptions
/*     */   extends GuiScreen
/*     */ {
/*     */   private GuiScreen prevScreen;
/*     */   protected String title;
/*     */   private GameSettings settings;
/*     */   private int lastMouseX;
/*     */   private int lastMouseY;
/*     */   private long mouseStillTime;
/*     */   private String screenName;
/*     */   private String screenText;
/*     */   private boolean changed;
/*     */   public static final String OPTION_PROFILE = "<profile>";
/*     */   public static final String OPTION_EMPTY = "<empty>";
/*     */   public static final String OPTION_REST = "*";
/*     */   
/*     */   public GuiShaderOptions(GuiScreen guiscreen, GameSettings gamesettings) {
/*  35 */     this.lastMouseX = 0;
/*  36 */     this.lastMouseY = 0;
/*  37 */     this.mouseStillTime = 0L;
/*  38 */     this.screenName = null;
/*  39 */     this.screenText = null;
/*  40 */     this.changed = false;
/*  41 */     this.title = "Shader Options";
/*  42 */     this.prevScreen = guiscreen;
/*  43 */     this.settings = gamesettings;
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiShaderOptions(GuiScreen guiscreen, GameSettings gamesettings, String screenName) {
/*  48 */     this(guiscreen, gamesettings);
/*  49 */     this.screenName = screenName;
/*     */     
/*  51 */     if (screenName != null)
/*     */     {
/*  53 */       this.screenText = Shaders.translate("screen." + screenName, screenName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  62 */     this.title = I18n.format("of.options.shaderOptionsTitle", new Object[0]);
/*  63 */     byte baseId = 100;
/*  64 */     boolean baseX = false;
/*  65 */     byte baseY = 30;
/*  66 */     byte stepY = 20;
/*  67 */     int btnX = width - 130;
/*  68 */     byte btnWidth = 120;
/*  69 */     byte btnHeight = 20;
/*  70 */     int columns = 2;
/*  71 */     ShaderOption[] ops = Shaders.getShaderPackOptions(this.screenName);
/*     */     
/*  73 */     if (ops != null) {
/*     */       
/*  75 */       if (ops.length > 18)
/*     */       {
/*  77 */         columns = ops.length / 9 + 1;
/*     */       }
/*     */       
/*  80 */       for (int i = 0; i < ops.length; i++) {
/*     */         
/*  82 */         ShaderOption so = ops[i];
/*     */         
/*  84 */         if (so != null && so.isVisible()) {
/*     */           
/*  86 */           int col = i % columns;
/*  87 */           int row = i / columns;
/*  88 */           int colWidth = Math.min(width / columns, 200);
/*  89 */           int var21 = (width - colWidth * columns) / 2;
/*  90 */           int x = col * colWidth + 5 + var21;
/*  91 */           int y = baseY + row * stepY;
/*  92 */           int w = colWidth - 10;
/*  93 */           String text = getButtonText(so, w);
/*  94 */           GuiButtonShaderOption btn = new GuiButtonShaderOption(baseId + i, x, y, w, btnHeight, so, text);
/*  95 */           btn.enabled = so.isEnabled();
/*  96 */           this.buttonList.add(btn);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 101 */     this.buttonList.add(new GuiButton(201, width / 2 - btnWidth - 20, height / 6 + 168 + 11, btnWidth, btnHeight, I18n.format("controls.reset", new Object[0])));
/* 102 */     this.buttonList.add(new GuiButton(200, width / 2 + 20, height / 6 + 168 + 11, btnWidth, btnHeight, I18n.format("gui.done", new Object[0])));
/*     */   }
/*     */ 
/*     */   
/*     */   private String getButtonText(ShaderOption so, int btnWidth) {
/* 107 */     String labelName = so.getNameText();
/*     */     
/* 109 */     if (so instanceof ShaderOptionScreen) {
/*     */       
/* 111 */       ShaderOptionScreen fr1 = (ShaderOptionScreen)so;
/* 112 */       return String.valueOf(labelName) + "...";
/*     */     } 
/*     */ 
/*     */     
/* 116 */     FontRenderer fr = (Config.getMinecraft()).fontRendererObj;
/*     */     
/* 118 */     for (int lenSuffix = fr.getStringWidth(": " + Lang.getOff()) + 5; fr.getStringWidth(labelName) + lenSuffix >= btnWidth && labelName.length() > 0; labelName = labelName.substring(0, labelName.length() - 1));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     String col = so.isChanged() ? so.getValueColor(so.getValue()) : "";
/* 124 */     String labelValue = so.getValueText(so.getValue());
/* 125 */     return String.valueOf(labelName) + ": " + col + labelValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton guibutton) {
/* 131 */     if (guibutton.enabled) {
/*     */       
/* 133 */       if (guibutton.id < 200 && guibutton instanceof GuiButtonShaderOption) {
/*     */         
/* 135 */         GuiButtonShaderOption opts = (GuiButtonShaderOption)guibutton;
/* 136 */         ShaderOption i = opts.getShaderOption();
/*     */         
/* 138 */         if (i instanceof ShaderOptionScreen) {
/*     */           
/* 140 */           String var8 = i.getName();
/* 141 */           GuiShaderOptions scr = new GuiShaderOptions(this, this.settings, var8);
/* 142 */           this.mc.displayGuiScreen(scr);
/*     */           
/*     */           return;
/*     */         } 
/* 146 */         i.nextValue();
/* 147 */         updateAllButtons();
/* 148 */         this.changed = true;
/*     */       } 
/*     */       
/* 151 */       if (guibutton.id == 201) {
/*     */         
/* 153 */         ShaderOption[] var6 = Shaders.getChangedOptions(Shaders.getShaderPackOptions());
/*     */         
/* 155 */         for (int var7 = 0; var7 < var6.length; var7++) {
/*     */           
/* 157 */           ShaderOption opt = var6[var7];
/* 158 */           opt.resetValue();
/* 159 */           this.changed = true;
/*     */         } 
/*     */         
/* 162 */         updateAllButtons();
/*     */       } 
/*     */       
/* 165 */       if (guibutton.id == 200) {
/*     */         
/* 167 */         if (this.changed) {
/*     */           
/* 169 */           Shaders.saveShaderPackOptions();
/* 170 */           Shaders.uninit();
/*     */         } 
/*     */         
/* 173 */         this.mc.displayGuiScreen(this.prevScreen);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 183 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*     */     
/* 185 */     if (mouseButton == 1) {
/*     */       
/* 187 */       GuiButton btn = getSelectedButton(mouseX, mouseY);
/*     */       
/* 189 */       if (btn instanceof GuiButtonShaderOption) {
/*     */         
/* 191 */         GuiButtonShaderOption btnSo = (GuiButtonShaderOption)btn;
/* 192 */         ShaderOption so = btnSo.getShaderOption();
/*     */         
/* 194 */         if (so.isChanged()) {
/*     */           
/* 196 */           btnSo.playPressSound(this.mc.getSoundHandler());
/* 197 */           so.resetValue();
/* 198 */           this.changed = true;
/* 199 */           updateAllButtons();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateAllButtons() {
/* 207 */     Iterator<GuiButton> it = this.buttonList.iterator();
/*     */     
/* 209 */     while (it.hasNext()) {
/*     */       
/* 211 */       GuiButton btn = it.next();
/*     */       
/* 213 */       if (btn instanceof GuiButtonShaderOption) {
/*     */         
/* 215 */         GuiButtonShaderOption gbso = (GuiButtonShaderOption)btn;
/* 216 */         ShaderOption opt = gbso.getShaderOption();
/*     */         
/* 218 */         if (opt instanceof ShaderOptionProfile) {
/*     */           
/* 220 */           ShaderOptionProfile optProf = (ShaderOptionProfile)opt;
/* 221 */           optProf.updateProfile();
/*     */         } 
/*     */         
/* 224 */         gbso.displayString = getButtonText(opt, gbso.getButtonWidth());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int x, int y, float f) {
/* 234 */     drawDefaultBackground();
/*     */     
/* 236 */     if (this.screenText != null) {
/*     */       
/* 238 */       drawCenteredString(fontRendererObj, this.screenText, (width / 2), 15.0F, 16777215);
/*     */     }
/*     */     else {
/*     */       
/* 242 */       drawCenteredString(fontRendererObj, this.title, (width / 2), 15.0F, 16777215);
/*     */     } 
/*     */     
/* 245 */     super.drawScreen(x, y, f);
/*     */     
/* 247 */     if (Math.abs(x - this.lastMouseX) <= 5 && Math.abs(y - this.lastMouseY) <= 5) {
/*     */       
/* 249 */       drawTooltips(x, y, this.buttonList);
/*     */     }
/*     */     else {
/*     */       
/* 253 */       this.lastMouseX = x;
/* 254 */       this.lastMouseY = y;
/* 255 */       this.mouseStillTime = System.currentTimeMillis();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawTooltips(int x, int y, List buttonList) {
/* 261 */     short activateDelay = 700;
/*     */     
/* 263 */     if (System.currentTimeMillis() >= this.mouseStillTime + activateDelay) {
/*     */       
/* 265 */       int x1 = width / 2 - 150;
/* 266 */       int y1 = height / 6 - 7;
/*     */       
/* 268 */       if (y <= y1 + 98)
/*     */       {
/* 270 */         y1 += 105;
/*     */       }
/*     */       
/* 273 */       int x2 = x1 + 150 + 150;
/* 274 */       int y2 = y1 + 84 + 10;
/* 275 */       GuiButton btn = getSelectedButton(x, y);
/*     */       
/* 277 */       if (btn instanceof GuiButtonShaderOption) {
/*     */         
/* 279 */         GuiButtonShaderOption btnSo = (GuiButtonShaderOption)btn;
/* 280 */         ShaderOption so = btnSo.getShaderOption();
/* 281 */         String[] lines = makeTooltipLines(so, x2 - x1);
/*     */         
/* 283 */         if (lines == null) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 288 */         drawGradientRect(x1, y1, x2, y2, -536870912, -536870912);
/*     */         
/* 290 */         for (int i = 0; i < lines.length; i++) {
/*     */           
/* 292 */           String line = lines[i];
/* 293 */           int col = 14540253;
/*     */           
/* 295 */           if (line.endsWith("!"))
/*     */           {
/* 297 */             col = 16719904;
/*     */           }
/*     */           
/* 300 */           fontRendererObj.drawStringWithShadow(line, (x1 + 5), (y1 + 5 + i * 11), col);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private String[] makeTooltipLines(ShaderOption so, int width) {
/* 308 */     if (so instanceof ShaderOptionProfile)
/*     */     {
/* 310 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 314 */     String name = so.getNameText();
/* 315 */     String desc = Config.normalize(so.getDescriptionText()).trim();
/* 316 */     String[] descs = splitDescription(desc);
/* 317 */     String id = null;
/*     */     
/* 319 */     if (!name.equals(so.getName()))
/*     */     {
/* 321 */       id = String.valueOf(Lang.get("of.general.id")) + ": " + so.getName();
/*     */     }
/*     */     
/* 324 */     String source = null;
/*     */     
/* 326 */     if (so.getPaths() != null)
/*     */     {
/* 328 */       source = String.valueOf(Lang.get("of.general.from")) + ": " + Config.arrayToString((Object[])so.getPaths());
/*     */     }
/*     */     
/* 331 */     String def = null;
/*     */     
/* 333 */     if (so.getValueDefault() != null) {
/*     */       
/* 335 */       String list = so.isEnabled() ? so.getValueText(so.getValueDefault()) : Lang.get("of.general.ambiguous");
/* 336 */       def = String.valueOf(Lang.getDefault()) + ": " + list;
/*     */     } 
/*     */     
/* 339 */     ArrayList<String> list1 = new ArrayList();
/* 340 */     list1.add(name);
/* 341 */     list1.addAll(Arrays.asList(descs));
/*     */     
/* 343 */     if (id != null)
/*     */     {
/* 345 */       list1.add(id);
/*     */     }
/*     */     
/* 348 */     if (source != null)
/*     */     {
/* 350 */       list1.add(source);
/*     */     }
/*     */     
/* 353 */     if (def != null)
/*     */     {
/* 355 */       list1.add(def);
/*     */     }
/*     */     
/* 358 */     String[] lines = makeTooltipLines(width, list1);
/* 359 */     return lines;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] splitDescription(String desc) {
/* 365 */     if (desc.length() <= 0)
/*     */     {
/* 367 */       return new String[0];
/*     */     }
/*     */ 
/*     */     
/* 371 */     desc = StrUtils.removePrefix(desc, "//");
/* 372 */     String[] descs = desc.split("\\. ");
/*     */     
/* 374 */     for (int i = 0; i < descs.length; i++) {
/*     */       
/* 376 */       descs[i] = "- " + descs[i].trim();
/* 377 */       descs[i] = StrUtils.removeSuffix(descs[i], ".");
/*     */     } 
/*     */     
/* 380 */     return descs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String[] makeTooltipLines(int width, List<String> args) {
/* 386 */     FontRenderer fr = (Config.getMinecraft()).fontRendererObj;
/* 387 */     ArrayList<String> list = new ArrayList();
/*     */     
/* 389 */     for (int lines = 0; lines < args.size(); lines++) {
/*     */       
/* 391 */       String arg = args.get(lines);
/*     */       
/* 393 */       if (arg != null && arg.length() > 0) {
/*     */         
/* 395 */         List parts = fr.listFormattedStringToWidth(arg, width);
/* 396 */         Iterator<String> it = parts.iterator();
/*     */         
/* 398 */         while (it.hasNext()) {
/*     */           
/* 400 */           String part = it.next();
/* 401 */           list.add(part);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 406 */     String[] var10 = list.<String>toArray(new String[list.size()]);
/* 407 */     return var10;
/*     */   }
/*     */ 
/*     */   
/*     */   private GuiButton getSelectedButton(int x, int y) {
/* 412 */     for (int i = 0; i < this.buttonList.size(); i++) {
/*     */       
/* 414 */       GuiButton btn = this.buttonList.get(i);
/* 415 */       int btnWidth = GuiVideoSettings.getButtonWidth(btn);
/* 416 */       int btnHeight = GuiVideoSettings.getButtonHeight(btn);
/*     */       
/* 418 */       if (x >= btn.xPosition && y >= btn.yPosition && x < btn.xPosition + btnWidth && y < btn.yPosition + btnHeight)
/*     */       {
/* 420 */         return btn;
/*     */       }
/*     */     } 
/*     */     
/* 424 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\GuiShaderOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */