/*     */ package leap.modules.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.List;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventKey;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.KeyBindSetting;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.ColorUtil;
/*     */ import leap.util.JColor;
/*     */ import leap.util.Shape;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TabGUI
/*     */   extends Module
/*     */ {
/*     */   public int currentTab;
/*     */   public boolean expanded;
/*     */   int tabcolor;
/*  28 */   public BooleanSetting rainbow = new BooleanSetting("Rainbow", true);
/*  29 */   public BooleanSetting noback = new BooleanSetting("No Background", false);
/*     */   
/*     */   public TabGUI() {
/*  32 */     super("TabGUI", 0, Module.Category.RENDER);
/*  33 */     addSettings(new Setting[] { (Setting)this.rainbow, (Setting)this.noback });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  37 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  41 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  45 */     if (e instanceof leap.events.listeners.EventRenderGUI) {
/*     */       int primaryColor, secondaryColor;
/*     */ 
/*     */       
/*  49 */       if (this.rainbow.enabled) {
/*  50 */         int j = 0;
/*     */         
/*  52 */         primaryColor = ColorUtil.getRainbow(4.0F, 0.7F, 0.9F, (j * 100));
/*  53 */         secondaryColor = ColorUtil.getRainbow(2.0F, 0.2F, 0.8F);
/*     */       } else {
/*  55 */         primaryColor = (new Color(255, 200, 0)).getRGB();
/*  56 */         secondaryColor = -1717986919;
/*     */       } 
/*     */       
/*  59 */       int backcolor = Color.black.getRGB();
/*  60 */       if (!this.noback.isEnabled()) {
/*     */ 
/*     */         
/*  63 */         Shape.color((new Color(20, 20, 20, 180)).getRGB());
/*  64 */         Shape.drawRoundedRect(5.0D, 30.5D, 90.0D, (5 + (Module.Category.values()).length * 16), 4.0F);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  69 */       Shape.color(primaryColor);
/*  70 */       Shape.drawRoundedRect(5.0D, 30.5D + (this.currentTab * 16), 90.0D, 20.0D, 4.0F);
/*     */       
/*  72 */       int count = 0; byte b; int i; Module.Category[] arrayOfCategory;
/*  73 */       for (i = (arrayOfCategory = Module.Category.values()).length, b = 0; b < i; ) { Module.Category c = arrayOfCategory[b];
/*  74 */         Client.customFontRendererBig.drawString(c.name, 10.0D, (36 + count * 16), new JColor(Color.white));
/*     */         
/*  76 */         count++;
/*     */         b++; }
/*     */       
/*  79 */       if (this.expanded) {
/*  80 */         Module.Category category = Module.Category.values()[this.currentTab];
/*  81 */         List<Module> modules = Client.getModulesByCategory(category);
/*     */         
/*  83 */         if (modules.size() == 0) {
/*     */           return;
/*     */         }
/*  86 */         Gui.drawRect(90.0D, 30.5D, 160.0D, (30 + modules.size() * 16) + 1.5D, backcolor);
/*  87 */         Gui.drawRect(90.1D, 30.7D + (category.moduleIndex * 16), 160.0D, (37 + category.moduleIndex * 16 + 13), primaryColor);
/*     */         
/*  89 */         count = 0;
/*  90 */         for (Module m : modules) {
/*  91 */           Client.customFontRenderer.drawString(m.name, 98.0D, (35 + count * 16), new JColor(Color.white.getRGB()));
/*     */           
/*  93 */           if (count == category.moduleIndex && m.expanded) {
/*     */             
/*  95 */             int index = 0, maxLength = 0;
/*  96 */             for (Setting setting : m.settings) {
/*     */               
/*  98 */               BooleanSetting bool = (BooleanSetting)setting;
/*  99 */               if (setting instanceof BooleanSetting && maxLength < Client.customFontRenderer.getStringWidth(String.valueOf(setting.name) + ": " + (bool.enabled ? "Enabled" : "Disabled"))) {
/* 100 */                 maxLength = Client.customFontRenderer.getStringWidth(String.valueOf(setting.name) + ": " + (bool.enabled ? "Enabled" : "Disabled"));
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 105 */               NumberSetting number = (NumberSetting)setting;
/* 106 */               if (setting instanceof NumberSetting && maxLength < Client.customFontRenderer.getStringWidth(String.valueOf(setting.name) + ": " + setting.name + ": " + number.getValue())) {
/* 107 */                 maxLength = Client.customFontRenderer.getStringWidth(String.valueOf(setting.name) + ": " + setting.name + ": " + number.getValue());
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 112 */               ModeSetting mode = (ModeSetting)setting;
/* 113 */               if (setting instanceof ModeSetting && maxLength < Client.customFontRenderer.getStringWidth(String.valueOf(setting.name) + ": " + setting.name + ": " + mode.getMode())) {
/* 114 */                 maxLength = Client.customFontRenderer.getStringWidth(String.valueOf(setting.name) + ": " + setting.name + ": " + mode.getMode());
/*     */               }
/*     */               
/* 117 */               if (setting instanceof KeyBindSetting) {
/* 118 */                 KeyBindSetting keyBind = (KeyBindSetting)setting;
/* 119 */                 if (maxLength < Client.customFontRenderer.getStringWidth(String.valueOf(setting.name) + ": " + setting.name + ": " + Keyboard.getKeyName(keyBind.code))) {
/* 120 */                   maxLength = Client.customFontRenderer.getStringWidth(String.valueOf(setting.name) + ": " + setting.name + ": " + Keyboard.getKeyName(keyBind.code));
/*     */                 }
/*     */                 
/* 123 */                 index++;
/*     */               } 
/*     */             } 
/*     */             
/* 127 */             Gui.drawRect(160.0D, (33 + m.index * 16), (17 + maxLength + 6 + 61 + 68), ((33 + m.index * 16 + 12) + 2.5F), ((Setting)m.settings.get(m.index)).focused ? secondaryColor : primaryColor);
/* 128 */             index = 0;
/* 129 */             for (Setting setting : m.settings) {
/* 130 */               if (setting instanceof BooleanSetting) {
/* 131 */                 BooleanSetting bool = (BooleanSetting)setting;
/* 132 */                 Client.customFontRenderer.drawStringWithShadow(String.valueOf(setting.name) + ": " + (bool.enabled ? "Enabled" : "Disabled"), 165.0D, (35 + index * 16), new JColor(Color.white.getRGB()));
/*     */               } 
/*     */               
/* 135 */               if (setting instanceof NumberSetting) {
/* 136 */                 NumberSetting number = (NumberSetting)setting;
/* 137 */                 Client.customFontRenderer.drawStringWithShadow(String.valueOf(setting.name) + ": " + number.getValue(), 165.0D, (35 + index * 16), new JColor(Color.white.getRGB()));
/*     */               } 
/*     */               
/* 140 */               if (setting instanceof ModeSetting) {
/* 141 */                 ModeSetting mode = (ModeSetting)setting;
/* 142 */                 Client.customFontRenderer.drawStringWithShadow(String.valueOf(setting.name) + ": " + mode.getMode(), 165.0D, (35 + index * 16), new JColor(Color.white.getRGB()));
/*     */               } 
/*     */               
/* 145 */               if (setting instanceof KeyBindSetting) {
/* 146 */                 KeyBindSetting keyBind = (KeyBindSetting)setting;
/* 147 */                 Client.customFontRenderer.drawStringWithShadow(String.valueOf(setting.name) + ": " + Keyboard.getKeyName(keyBind.code), 165.0D, (35 + index * 16), new JColor(Color.white.getRGB()));
/*     */               } 
/*     */               
/* 150 */               index++;
/*     */             } 
/*     */           } 
/*     */           
/* 154 */           count++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 159 */     if (e instanceof EventKey) {
/* 160 */       int code = ((EventKey)e).code;
/*     */       
/* 162 */       Module.Category category = Module.Category.values()[this.currentTab];
/* 163 */       List<Module> modules = Client.getModulesByCategory(category);
/*     */       
/* 165 */       if (this.expanded && !modules.isEmpty() && ((Module)modules.get(category.moduleIndex)).expanded) {
/* 166 */         Module module = modules.get(category.moduleIndex);
/*     */         
/* 168 */         if (module.settings.size() != 0 && ((Setting)module.settings.get(module.index)).focused && module.settings.get(module.index) instanceof KeyBindSetting && 
/* 169 */           code != 28 && code != 200 && code != 208 && code != 203 && code != 205 && code != 1) {
/* 170 */           KeyBindSetting keyBind = module.settings.get(module.index);
/*     */           
/* 172 */           keyBind.code = code;
/* 173 */           keyBind.focused = false;
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */       
/* 180 */       if (code == 200) {
/* 181 */         if (this.expanded) {
/* 182 */           if (this.expanded && !modules.isEmpty() && ((Module)modules.get(category.moduleIndex)).expanded) {
/* 183 */             Module module = modules.get(category.moduleIndex);
/*     */             
/* 185 */             if (((Setting)module.settings.get(module.index)).focused) {
/* 186 */               Setting setting = module.settings.get(module.index);
/*     */               
/* 188 */               if (setting instanceof NumberSetting) {
/* 189 */                 ((NumberSetting)setting).increment(true);
/*     */               }
/*     */             }
/* 192 */             else if (module.index <= 0) {
/* 193 */               module.index = module.settings.size() - 1;
/*     */             } else {
/* 195 */               module.index--;
/*     */             }
/*     */           
/* 198 */           } else if (category.moduleIndex <= 0) {
/* 199 */             category.moduleIndex = modules.size() - 1;
/*     */           } else {
/* 201 */             category.moduleIndex--;
/*     */           }
/*     */         
/* 204 */         } else if (this.currentTab <= 0) {
/* 205 */           this.currentTab = (Module.Category.values()).length - 1;
/*     */         } else {
/* 207 */           this.currentTab--;
/*     */         } 
/*     */       }
/*     */       
/* 211 */       if (code == 208) {
/* 212 */         if (this.expanded) {
/* 213 */           if (this.expanded && !modules.isEmpty() && ((Module)modules.get(category.moduleIndex)).expanded) {
/* 214 */             Module module = modules.get(category.moduleIndex);
/*     */             
/* 216 */             if (((Setting)module.settings.get(module.index)).focused) {
/* 217 */               Setting setting = module.settings.get(module.index);
/*     */               
/* 219 */               if (setting instanceof NumberSetting) {
/* 220 */                 ((NumberSetting)setting).increment(false);
/*     */               }
/*     */             }
/* 223 */             else if (module.index >= module.settings.size() - 1) {
/* 224 */               module.index = 0;
/*     */             } else {
/* 226 */               module.index++;
/*     */             }
/*     */           
/* 229 */           } else if (category.moduleIndex >= modules.size() - 1) {
/* 230 */             category.moduleIndex = 0;
/*     */           } else {
/* 232 */             category.moduleIndex++;
/*     */           }
/*     */         
/* 235 */         } else if (this.currentTab >= (Module.Category.values()).length - 1) {
/* 236 */           this.currentTab = 0;
/*     */         } else {
/* 238 */           this.currentTab++;
/*     */         } 
/*     */       }
/*     */       
/* 242 */       if (code == 28 && 
/* 243 */         this.expanded && modules.size() != 0) {
/* 244 */         Module module = modules.get(category.moduleIndex);
/*     */         
/* 246 */         if (!module.expanded && module.settings.size() != 0) {
/* 247 */           module.expanded = true;
/* 248 */         } else if (module.expanded) {
/* 249 */           ((Setting)module.settings.get(module.index)).focused = !((Setting)module.settings.get(module.index)).focused;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 254 */       if (code == 205) {
/* 255 */         if (this.expanded && modules.size() != 0) {
/* 256 */           Module module = modules.get(category.moduleIndex);
/*     */           
/* 258 */           if (this.expanded && !modules.isEmpty() && module.expanded) {
/* 259 */             Setting setting = module.settings.get(module.index);
/*     */             
/* 261 */             if (setting instanceof BooleanSetting) {
/* 262 */               ((BooleanSetting)setting).toggle();
/*     */             }
/* 264 */             if (setting instanceof ModeSetting) {
/* 265 */               ((ModeSetting)setting).cycle();
/*     */             }
/*     */           }
/* 268 */           else if (!module.name.equals("TabGUI")) {
/* 269 */             module.toggle();
/*     */           }
/*     */         
/* 272 */         } else if (modules.size() != 0) {
/* 273 */           this.expanded = true;
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 278 */       if (code == 203)
/* 279 */         if (this.expanded && !modules.isEmpty() && ((Module)modules.get(category.moduleIndex)).expanded) {
/* 280 */           Module module = modules.get(category.moduleIndex);
/* 281 */           if (!((Setting)module.settings.get(module.index)).focused)
/*     */           {
/*     */             
/* 284 */             ((Module)modules.get(category.moduleIndex)).expanded = false;
/*     */           }
/*     */         } else {
/* 287 */           this.expanded = false;
/*     */         }  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\TabGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */