/*     */ package shadersmod.client;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import optifine.Config;
/*     */ import optifine.Lang;
/*     */ import org.lwjgl.Sys;
/*     */ 
/*     */ public class GuiShaders
/*     */   extends GuiScreen
/*     */ {
/*     */   protected GuiScreen parentGui;
/*  20 */   protected String screenTitle = "Shaders";
/*  21 */   private int updateTimer = -1;
/*     */   private GuiSlotShaders shaderList;
/*  23 */   private static float[] QUALITY_MULTIPLIERS = new float[] { 0.5F, 0.70710677F, 1.0F, 1.4142135F, 2.0F };
/*  24 */   private static String[] QUALITY_MULTIPLIER_NAMES = new String[] { "0.5x", "0.7x", "1x", "1.5x", "2x" };
/*  25 */   private static float[] HAND_DEPTH_VALUES = new float[] { 0.0625F, 0.125F, 0.25F };
/*  26 */   private static String[] HAND_DEPTH_NAMES = new String[] { "0.5x", "1x", "2x" };
/*     */   
/*     */   public static final int EnumOS_UNKNOWN = 0;
/*     */   public static final int EnumOS_WINDOWS = 1;
/*     */   public static final int EnumOS_OSX = 2;
/*     */   public static final int EnumOS_SOLARIS = 3;
/*     */   public static final int EnumOS_LINUX = 4;
/*     */   
/*     */   public GuiShaders(GuiScreen par1GuiScreen, GameSettings par2GameSettings) {
/*  35 */     this.parentGui = par1GuiScreen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  43 */     this.screenTitle = I18n.format("of.options.shadersTitle", new Object[0]);
/*     */     
/*  45 */     if (Shaders.shadersConfig == null)
/*     */     {
/*  47 */       Shaders.loadConfig();
/*     */     }
/*     */     
/*  50 */     byte btnWidth = 120;
/*  51 */     byte btnHeight = 20;
/*  52 */     int btnX = width - btnWidth - 10;
/*  53 */     byte baseY = 30;
/*  54 */     byte stepY = 20;
/*  55 */     int shaderListWidth = width - btnWidth - 20;
/*  56 */     this.shaderList = new GuiSlotShaders(this, shaderListWidth, height, baseY, height - 50, 16);
/*  57 */     this.shaderList.registerScrollButtons(7, 8);
/*  58 */     this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.ANTIALIASING, btnX, 0 * stepY + baseY, btnWidth, btnHeight));
/*  59 */     this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.NORMAL_MAP, btnX, 1 * stepY + baseY, btnWidth, btnHeight));
/*  60 */     this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.SPECULAR_MAP, btnX, 2 * stepY + baseY, btnWidth, btnHeight));
/*  61 */     this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.RENDER_RES_MUL, btnX, 3 * stepY + baseY, btnWidth, btnHeight));
/*  62 */     this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.SHADOW_RES_MUL, btnX, 4 * stepY + baseY, btnWidth, btnHeight));
/*  63 */     this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.HAND_DEPTH_MUL, btnX, 5 * stepY + baseY, btnWidth, btnHeight));
/*  64 */     this.buttonList.add(new GuiButtonEnumShaderOption(EnumShaderOption.OLD_LIGHTING, btnX, 6 * stepY + baseY, btnWidth, btnHeight));
/*  65 */     int btnFolderWidth = Math.min(150, shaderListWidth / 2 - 10);
/*  66 */     this.buttonList.add(new GuiButton(201, shaderListWidth / 4 - btnFolderWidth / 2, height - 25, btnFolderWidth, btnHeight, Lang.get("of.options.shaders.shadersFolder")));
/*  67 */     this.buttonList.add(new GuiButton(202, shaderListWidth / 4 * 3 - btnFolderWidth / 2, height - 25, btnFolderWidth, btnHeight, I18n.format("gui.done", new Object[0])));
/*  68 */     this.buttonList.add(new GuiButton(203, btnX, height - 25, btnWidth, btnHeight, Lang.get("of.options.shaders.shaderOptions")));
/*  69 */     updateButtons();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateButtons() {
/*  74 */     boolean shaderActive = Config.isShaders();
/*  75 */     Iterator<GuiButton> it = this.buttonList.iterator();
/*     */     
/*  77 */     while (it.hasNext()) {
/*     */       
/*  79 */       GuiButton button = it.next();
/*     */       
/*  81 */       if (button.id != 201 && button.id != 202 && button.id != EnumShaderOption.ANTIALIASING.ordinal())
/*     */       {
/*  83 */         button.enabled = shaderActive;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  93 */     super.handleMouseInput();
/*  94 */     this.shaderList.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) {
/*  99 */     if (button.enabled)
/*     */     {
/* 101 */       if (button instanceof GuiButtonEnumShaderOption) {
/*     */         String[] names; int index; float var13, var15[];
/* 103 */         GuiButtonEnumShaderOption var12 = (GuiButtonEnumShaderOption)button;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 109 */         switch (var12.getEnumShaderOption()) {
/*     */           
/*     */           case ANTIALIASING:
/* 112 */             Shaders.nextAntialiasingLevel();
/* 113 */             Shaders.uninit();
/*     */             break;
/*     */           
/*     */           case NORMAL_MAP:
/* 117 */             Shaders.configNormalMap = !Shaders.configNormalMap;
/* 118 */             this.mc.func_175603_A();
/*     */             break;
/*     */           
/*     */           case SPECULAR_MAP:
/* 122 */             Shaders.configSpecularMap = !Shaders.configSpecularMap;
/* 123 */             this.mc.func_175603_A();
/*     */             break;
/*     */           
/*     */           case RENDER_RES_MUL:
/* 127 */             var13 = Shaders.configRenderResMul;
/* 128 */             var15 = QUALITY_MULTIPLIERS;
/* 129 */             names = QUALITY_MULTIPLIER_NAMES;
/* 130 */             index = getValueIndex(var13, var15);
/*     */             
/* 132 */             if (isShiftKeyDown()) {
/*     */               
/* 134 */               index--;
/*     */               
/* 136 */               if (index < 0)
/*     */               {
/* 138 */                 index = var15.length - 1;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 143 */               index++;
/*     */               
/* 145 */               if (index >= var15.length)
/*     */               {
/* 147 */                 index = 0;
/*     */               }
/*     */             } 
/*     */             
/* 151 */             Shaders.configRenderResMul = var15[index];
/* 152 */             Shaders.scheduleResize();
/*     */             break;
/*     */           
/*     */           case SHADOW_RES_MUL:
/* 156 */             var13 = Shaders.configShadowResMul;
/* 157 */             var15 = QUALITY_MULTIPLIERS;
/* 158 */             names = QUALITY_MULTIPLIER_NAMES;
/* 159 */             index = getValueIndex(var13, var15);
/*     */             
/* 161 */             if (isShiftKeyDown()) {
/*     */               
/* 163 */               index--;
/*     */               
/* 165 */               if (index < 0)
/*     */               {
/* 167 */                 index = var15.length - 1;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 172 */               index++;
/*     */               
/* 174 */               if (index >= var15.length)
/*     */               {
/* 176 */                 index = 0;
/*     */               }
/*     */             } 
/*     */             
/* 180 */             Shaders.configShadowResMul = var15[index];
/* 181 */             Shaders.scheduleResizeShadow();
/*     */             break;
/*     */           
/*     */           case HAND_DEPTH_MUL:
/* 185 */             var13 = Shaders.configHandDepthMul;
/* 186 */             var15 = HAND_DEPTH_VALUES;
/* 187 */             names = HAND_DEPTH_NAMES;
/* 188 */             index = getValueIndex(var13, var15);
/*     */             
/* 190 */             if (isShiftKeyDown()) {
/*     */               
/* 192 */               index--;
/*     */               
/* 194 */               if (index < 0)
/*     */               {
/* 196 */                 index = var15.length - 1;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 201 */               index++;
/*     */               
/* 203 */               if (index >= var15.length)
/*     */               {
/* 205 */                 index = 0;
/*     */               }
/*     */             } 
/*     */             
/* 209 */             Shaders.configHandDepthMul = var15[index];
/*     */             break;
/*     */           
/*     */           case CLOUD_SHADOW:
/* 213 */             Shaders.configCloudShadow = !Shaders.configCloudShadow;
/*     */             break;
/*     */           
/*     */           case OLD_LIGHTING:
/* 217 */             Shaders.configOldLighting.nextValue();
/* 218 */             Shaders.updateBlockLightLevel();
/* 219 */             this.mc.func_175603_A();
/*     */             break;
/*     */           
/*     */           case TWEAK_BLOCK_DAMAGE:
/* 223 */             Shaders.configTweakBlockDamage = !Shaders.configTweakBlockDamage;
/*     */             break;
/*     */           
/*     */           case TEX_MIN_FIL_B:
/* 227 */             Shaders.configTexMinFilB = (Shaders.configTexMinFilB + 1) % 3;
/* 228 */             Shaders.configTexMinFilN = Shaders.configTexMinFilS = Shaders.configTexMinFilB;
/* 229 */             button.displayString = "Tex Min: " + Shaders.texMinFilDesc[Shaders.configTexMinFilB];
/* 230 */             ShadersTex.updateTextureMinMagFilter();
/*     */             break;
/*     */           
/*     */           case TEX_MAG_FIL_N:
/* 234 */             Shaders.configTexMagFilN = (Shaders.configTexMagFilN + 1) % 2;
/* 235 */             button.displayString = "Tex_n Mag: " + Shaders.texMagFilDesc[Shaders.configTexMagFilN];
/* 236 */             ShadersTex.updateTextureMinMagFilter();
/*     */             break;
/*     */           
/*     */           case TEX_MAG_FIL_S:
/* 240 */             Shaders.configTexMagFilS = (Shaders.configTexMagFilS + 1) % 2;
/* 241 */             button.displayString = "Tex_s Mag: " + Shaders.texMagFilDesc[Shaders.configTexMagFilS];
/* 242 */             ShadersTex.updateTextureMinMagFilter();
/*     */             break;
/*     */           
/*     */           case SHADOW_CLIP_FRUSTRUM:
/* 246 */             Shaders.configShadowClipFrustrum = !Shaders.configShadowClipFrustrum;
/* 247 */             button.displayString = "ShadowClipFrustrum: " + toStringOnOff(Shaders.configShadowClipFrustrum);
/* 248 */             ShadersTex.updateTextureMinMagFilter();
/*     */             break;
/*     */         } 
/* 251 */         var12.updateButtonText();
/*     */       } else {
/*     */         String gbeso; boolean var11;
/*     */         GuiShaderOptions values;
/* 255 */         switch (button.id) {
/*     */           
/*     */           case 201:
/* 258 */             switch (getOSType()) {
/*     */               
/*     */               case 1:
/* 261 */                 gbeso = String.format("cmd.exe /C start \"Open file\" \"%s\"", new Object[] { Shaders.shaderpacksdir.getAbsolutePath() });
/*     */ 
/*     */                 
/*     */                 try {
/* 265 */                   Runtime.getRuntime().exec(gbeso);
/*     */                   
/*     */                   return;
/* 268 */                 } catch (IOException var9) {
/*     */                   
/* 270 */                   var9.printStackTrace();
/*     */                   break;
/*     */                 } 
/*     */ 
/*     */               
/*     */               case 2:
/*     */                 try {
/* 277 */                   Runtime.getRuntime().exec(new String[] { "/usr/bin/open", Shaders.shaderpacksdir.getAbsolutePath() });
/*     */                   
/*     */                   return;
/* 280 */                 } catch (IOException var10) {
/*     */                   
/* 282 */                   var10.printStackTrace();
/*     */                   break;
/*     */                 } 
/*     */             } 
/* 286 */             var11 = false;
/*     */ 
/*     */             
/*     */             try {
/* 290 */               Class<?> val = Class.forName("java.awt.Desktop");
/* 291 */               Object var14 = val.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 292 */               val.getMethod("browse", new Class[] { URI.class }).invoke(var14, new Object[] { (new File(this.mc.mcDataDir, Shaders.shaderpacksdirname)).toURI() });
/*     */             }
/* 294 */             catch (Throwable var8) {
/*     */               
/* 296 */               var8.printStackTrace();
/* 297 */               var11 = true;
/*     */             } 
/*     */             
/* 300 */             if (var11) {
/*     */               
/* 302 */               Config.dbg("Opening via system class!");
/* 303 */               Sys.openURL("file://" + Shaders.shaderpacksdir.getAbsolutePath());
/*     */             } 
/*     */             return;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 202:
/*     */             try {
/* 313 */               Shaders.storeConfig();
/*     */             }
/* 315 */             catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 320 */             this.mc.displayGuiScreen(this.parentGui);
/*     */             return;
/*     */           
/*     */           case 203:
/* 324 */             values = new GuiShaderOptions(this, Config.getGameSettings());
/* 325 */             Config.getMinecraft().displayGuiScreen(values);
/*     */             return;
/*     */         } 
/*     */         
/* 329 */         this.shaderList.actionPerformed(button);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 340 */     drawDefaultBackground();
/* 341 */     this.shaderList.drawScreen(mouseX, mouseY, partialTicks);
/*     */     
/* 343 */     if (this.updateTimer <= 0) {
/*     */       
/* 345 */       this.shaderList.updateList();
/* 346 */       this.updateTimer += 20;
/*     */     } 
/*     */     
/* 349 */     drawCenteredString(fontRendererObj, String.valueOf(this.screenTitle) + " ", (width / 2), 15.0F, 16777215);
/* 350 */     String info = "OpenGL: " + Shaders.glVersionString + ", " + Shaders.glVendorString + ", " + Shaders.glRendererString;
/* 351 */     int infoWidth = fontRendererObj.getStringWidth(info);
/*     */     
/* 353 */     if (infoWidth < width - 5) {
/*     */       
/* 355 */       drawCenteredString(fontRendererObj, info, (width / 2), (height - 40), 8421504);
/*     */     }
/*     */     else {
/*     */       
/* 359 */       drawString(fontRendererObj, info, 5, height - 40, 8421504);
/*     */     } 
/*     */     
/* 362 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 370 */     super.updateScreen();
/* 371 */     this.updateTimer--;
/*     */   }
/*     */ 
/*     */   
/*     */   public Minecraft getMc() {
/* 376 */     return this.mc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawCenteredString(String text, int x, int y, int color) {
/* 381 */     drawCenteredString(fontRendererObj, text, x, y, color);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toStringOnOff(boolean value) {
/* 386 */     String on = Lang.getOn();
/* 387 */     String off = Lang.getOff();
/* 388 */     return value ? on : off;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toStringAa(int value) {
/* 393 */     return (value == 2) ? "FXAA 2x" : ((value == 4) ? "FXAA 4x" : Lang.getOff());
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toStringValue(float val, float[] values, String[] names) {
/* 398 */     int index = getValueIndex(val, values);
/* 399 */     return names[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getValueIndex(float val, float[] values) {
/* 404 */     for (int i = 0; i < values.length; i++) {
/*     */       
/* 406 */       float value = values[i];
/*     */       
/* 408 */       if (value >= val)
/*     */       {
/* 410 */         return i;
/*     */       }
/*     */     } 
/*     */     
/* 414 */     return values.length - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toStringQuality(float val) {
/* 419 */     return toStringValue(val, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_NAMES);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toStringHandDepth(float val) {
/* 424 */     return toStringValue(val, HAND_DEPTH_VALUES, HAND_DEPTH_NAMES);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getOSType() {
/* 429 */     String osName = System.getProperty("os.name").toLowerCase();
/* 430 */     return osName.contains("win") ? 1 : (osName.contains("mac") ? 2 : (osName.contains("solaris") ? 3 : (osName.contains("sunos") ? 3 : (osName.contains("linux") ? 4 : (osName.contains("unix") ? 4 : 0)))));
/*     */   }
/*     */   
/*     */   static class NamelessClass1647571870
/*     */   {
/* 435 */     static final int[] $SwitchMap$shadersmod$client$EnumShaderOption = new int[(EnumShaderOption.values()).length];
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 441 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.ANTIALIASING.ordinal()] = 1;
/*     */       }
/* 443 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 450 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.NORMAL_MAP.ordinal()] = 2;
/*     */       }
/* 452 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 459 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SPECULAR_MAP.ordinal()] = 3;
/*     */       }
/* 461 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 468 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.RENDER_RES_MUL.ordinal()] = 4;
/*     */       }
/* 470 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 477 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SHADOW_RES_MUL.ordinal()] = 5;
/*     */       }
/* 479 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 486 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.HAND_DEPTH_MUL.ordinal()] = 6;
/*     */       }
/* 488 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 495 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.CLOUD_SHADOW.ordinal()] = 7;
/*     */       }
/* 497 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 504 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.OLD_LIGHTING.ordinal()] = 8;
/*     */       }
/* 506 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 513 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TWEAK_BLOCK_DAMAGE.ordinal()] = 9;
/*     */       }
/* 515 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 522 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TEX_MIN_FIL_B.ordinal()] = 10;
/*     */       }
/* 524 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 531 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TEX_MAG_FIL_N.ordinal()] = 11;
/*     */       }
/* 533 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 540 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.TEX_MAG_FIL_S.ordinal()] = 12;
/*     */       }
/* 542 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 549 */         $SwitchMap$shadersmod$client$EnumShaderOption[EnumShaderOption.SHADOW_CLIP_FRUSTRUM.ordinal()] = 13;
/*     */       }
/* 551 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\GuiShaders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */