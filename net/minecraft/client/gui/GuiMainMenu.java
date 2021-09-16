/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.realms.RealmsBridge;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.demo.DemoWorldServer;
/*     */ import net.minecraft.world.storage.ISaveFormat;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import org.apache.commons.io.Charsets;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GLContext;
/*     */ import org.lwjgl.util.glu.Project;
/*     */ 
/*     */ 
/*     */ public class GuiMainMenu
/*     */   extends GuiScreen
/*     */   implements GuiYesNoCallback
/*     */ {
/*  39 */   private static final AtomicInteger field_175373_f = new AtomicInteger(0);
/*  40 */   private static final Logger logger = LogManager.getLogger();
/*  41 */   private static final Random field_175374_h = new Random();
/*     */ 
/*     */   
/*     */   private float updateCounter;
/*     */ 
/*     */   
/*     */   private String splashText;
/*     */ 
/*     */   
/*     */   private GuiButton buttonResetDemo;
/*     */   
/*     */   private int panoramaTimer;
/*     */   
/*     */   private DynamicTexture viewportTexture;
/*     */   
/*     */   private boolean field_175375_v = true;
/*     */   
/*  58 */   private final Object field_104025_t = new Object();
/*     */   private String field_92025_p;
/*     */   private String field_146972_A;
/*     */   private String field_104024_v;
/*  62 */   private static final ResourceLocation splashTexts = new ResourceLocation("texts/splashes.txt");
/*  63 */   private static final ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");
/*     */ 
/*     */   
/*  66 */   private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[] { new ResourceLocation("textures/gui/title/background/panorama_0.png"), new ResourceLocation("textures/gui/title/background/panorama_1.png"), new ResourceLocation("textures/gui/title/background/panorama_2.png"), new ResourceLocation("textures/gui/title/background/panorama_3.png"), new ResourceLocation("textures/gui/title/background/panorama_4.png"), new ResourceLocation("textures/gui/title/background/panorama_5.png") };
/*  67 */   public static final String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
/*     */   
/*     */   private int field_92024_r;
/*     */   private int field_92023_s;
/*     */   private int field_92022_t;
/*     */   private int field_92021_u;
/*     */   private int field_92020_v;
/*     */   private int field_92019_w;
/*     */   private ResourceLocation field_110351_G;
/*     */   private GuiButton field_175372_K;
/*     */   private static final String __OBFID = "CL_00001154";
/*     */   
/*     */   public GuiMainMenu() {
/*  80 */     this.field_146972_A = field_96138_a;
/*  81 */     this.splashText = "missingno";
/*  82 */     BufferedReader var1 = null;
/*     */ 
/*     */     
/*     */     try {
/*  86 */       ArrayList<String> var2 = Lists.newArrayList();
/*  87 */       var1 = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(), Charsets.UTF_8));
/*     */       
/*     */       String var3;
/*  90 */       while ((var3 = var1.readLine()) != null) {
/*     */         
/*  92 */         var3 = var3.trim();
/*     */         
/*  94 */         if (!var3.isEmpty())
/*     */         {
/*  96 */           var2.add(var3);
/*     */         }
/*     */       } 
/*     */       
/* 100 */       if (!var2.isEmpty()) {
/*     */         do
/*     */         {
/*     */           
/* 104 */           this.splashText = var2.get(field_175374_h.nextInt(var2.size()));
/*     */         }
/* 106 */         while (this.splashText.hashCode() == 125780783);
/*     */       }
/*     */     }
/* 109 */     catch (IOException iOException) {
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 115 */       if (var1 != null) {
/*     */         
/*     */         try {
/*     */           
/* 119 */           var1.close();
/*     */         }
/* 121 */         catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 128 */     this.updateCounter = field_175374_h.nextFloat();
/* 129 */     this.field_92025_p = "";
/*     */     
/* 131 */     if (!(GLContext.getCapabilities()).OpenGL20 && !OpenGlHelper.areShadersSupported()) {
/*     */       
/* 133 */       this.field_92025_p = I18n.format("title.oldgl1", new Object[0]);
/* 134 */       this.field_146972_A = I18n.format("title.oldgl2", new Object[0]);
/* 135 */       this.field_104024_v = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 144 */     this.panoramaTimer++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/* 166 */     this.viewportTexture = new DynamicTexture(256, 256);
/* 167 */     this.field_110351_G = this.mc.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
/* 168 */     Calendar var1 = Calendar.getInstance();
/* 169 */     var1.setTime(new Date());
/*     */     
/* 171 */     if (var1.get(2) + 1 == 11 && var1.get(5) == 9) {
/*     */       
/* 173 */       this.splashText = "Happy birthday, ez!";
/*     */     }
/* 175 */     else if (var1.get(2) + 1 == 6 && var1.get(5) == 1) {
/*     */       
/* 177 */       this.splashText = "Happy birthday, Notch!";
/*     */     }
/* 179 */     else if (var1.get(2) + 1 == 12 && var1.get(5) == 24) {
/*     */       
/* 181 */       this.splashText = "Merry X-mas!";
/*     */     }
/* 183 */     else if (var1.get(2) + 1 == 1 && var1.get(5) == 1) {
/*     */       
/* 185 */       this.splashText = "Happy new year!";
/*     */     }
/* 187 */     else if (var1.get(2) + 1 == 10 && var1.get(5) == 31) {
/*     */       
/* 189 */       this.splashText = "OOoooOOOoooo! Spooky!";
/*     */     } 
/*     */     
/* 192 */     boolean var2 = true;
/* 193 */     int var3 = height / 4 + 48;
/*     */     
/* 195 */     if (this.mc.isDemo()) {
/*     */       
/* 197 */       addDemoButtons(var3, 24);
/*     */     }
/*     */     else {
/*     */       
/* 201 */       addSingleplayerMultiplayerButtons(var3, 24);
/*     */     } 
/*     */     
/* 204 */     this.buttonList.add(new GuiButton(0, width / 2 - 100, var3 + 72 + 12, 98, 20, I18n.format("menu.options", new Object[0])));
/* 205 */     this.buttonList.add(new GuiButton(4, width / 2 + 2, var3 + 72 + 12, 98, 20, I18n.format("menu.quit", new Object[0])));
/* 206 */     this.buttonList.add(new GuiButtonLanguage(5, width / 2 - 124, var3 + 72 + 12));
/* 207 */     Object var4 = this.field_104025_t;
/*     */     
/* 209 */     synchronized (this.field_104025_t) {
/*     */       
/* 211 */       this.field_92023_s = fontRendererObj.getStringWidth(this.field_92025_p);
/* 212 */       this.field_92024_r = fontRendererObj.getStringWidth(this.field_146972_A);
/* 213 */       int var5 = Math.max(this.field_92023_s, this.field_92024_r);
/* 214 */       this.field_92022_t = (width - var5) / 2;
/* 215 */       this.field_92021_u = ((GuiButton)this.buttonList.get(0)).yPosition - 24;
/* 216 */       this.field_92020_v = this.field_92022_t + var5;
/* 217 */       this.field_92019_w = this.field_92021_u + 24;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_) {
/* 226 */     this.buttonList.add(new GuiButton(1, width / 2 - 100, p_73969_1_, I18n.format("menu.singleplayer", new Object[0])));
/* 227 */     this.buttonList.add(new GuiButton(2, width / 2 - 100, p_73969_1_ + p_73969_2_ * 1, I18n.format("menu.multiplayer", new Object[0])));
/* 228 */     this.buttonList.add(this.field_175372_K = new GuiButton(14, width / 2 - 100, p_73969_1_ + p_73969_2_ * 2, I18n.format("menu.online", new Object[0])));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addDemoButtons(int p_73972_1_, int p_73972_2_) {
/* 236 */     this.buttonList.add(new GuiButton(11, width / 2 - 100, p_73972_1_, I18n.format("menu.playdemo", new Object[0])));
/* 237 */     this.buttonList.add(this.buttonResetDemo = new GuiButton(12, width / 2 - 100, p_73972_1_ + p_73972_2_ * 1, I18n.format("menu.resetdemo", new Object[0])));
/* 238 */     ISaveFormat var3 = this.mc.getSaveLoader();
/* 239 */     WorldInfo var4 = var3.getWorldInfo("Demo_World");
/*     */     
/* 241 */     if (var4 == null)
/*     */     {
/* 243 */       this.buttonResetDemo.enabled = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 249 */     if (button.id == 0)
/*     */     {
/* 251 */       this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
/*     */     }
/*     */     
/* 254 */     if (button.id == 5)
/*     */     {
/* 256 */       this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
/*     */     }
/*     */     
/* 259 */     if (button.id == 1)
/*     */     {
/* 261 */       this.mc.displayGuiScreen(new GuiSelectWorld(this));
/*     */     }
/*     */     
/* 264 */     if (button.id == 2)
/*     */     {
/* 266 */       this.mc.displayGuiScreen(new GuiMultiplayer(this));
/*     */     }
/*     */     
/* 269 */     if (button.id == 14 && this.field_175372_K.visible)
/*     */     {
/* 271 */       switchToRealms();
/*     */     }
/*     */     
/* 274 */     if (button.id == 4)
/*     */     {
/* 276 */       this.mc.shutdown();
/*     */     }
/*     */     
/* 279 */     if (button.id == 11)
/*     */     {
/* 281 */       this.mc.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
/*     */     }
/*     */     
/* 284 */     if (button.id == 12) {
/*     */       
/* 286 */       ISaveFormat var2 = this.mc.getSaveLoader();
/* 287 */       WorldInfo var3 = var2.getWorldInfo("Demo_World");
/*     */       
/* 289 */       if (var3 != null) {
/*     */         
/* 291 */         GuiYesNo var4 = GuiSelectWorld.func_152129_a(this, var3.getWorldName(), 12);
/* 292 */         this.mc.displayGuiScreen(var4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void switchToRealms() {
/* 299 */     RealmsBridge var1 = new RealmsBridge();
/* 300 */     var1.switchToRealms(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean result, int id) {
/* 305 */     if (result && id == 12) {
/*     */       
/* 307 */       ISaveFormat var6 = this.mc.getSaveLoader();
/* 308 */       var6.flushCache();
/* 309 */       var6.deleteWorldDirectory("Demo_World");
/* 310 */       this.mc.displayGuiScreen(this);
/*     */     }
/* 312 */     else if (id == 13) {
/*     */       
/* 314 */       if (result) {
/*     */         
/*     */         try {
/*     */           
/* 318 */           Class<?> var3 = Class.forName("java.awt.Desktop");
/* 319 */           Object var4 = var3.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 320 */           var3.getMethod("browse", new Class[] { URI.class }).invoke(var4, new Object[] { new URI(this.field_104024_v) });
/*     */         }
/* 322 */         catch (Throwable var5) {
/*     */           
/* 324 */           logger.error("Couldn't open link", var5);
/*     */         } 
/*     */       }
/*     */       
/* 328 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawPanorama(int p_73970_1_, int p_73970_2_, float p_73970_3_) {
/* 337 */     Tessellator var4 = Tessellator.getInstance();
/* 338 */     WorldRenderer var5 = var4.getWorldRenderer();
/* 339 */     GlStateManager.matrixMode(5889);
/* 340 */     GlStateManager.pushMatrix();
/* 341 */     GlStateManager.loadIdentity();
/* 342 */     Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
/* 343 */     GlStateManager.matrixMode(5888);
/* 344 */     GlStateManager.pushMatrix();
/* 345 */     GlStateManager.loadIdentity();
/* 346 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 347 */     GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
/* 348 */     GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
/* 349 */     GlStateManager.enableBlend();
/* 350 */     GlStateManager.disableAlpha();
/* 351 */     GlStateManager.disableCull();
/* 352 */     GlStateManager.depthMask(false);
/* 353 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 354 */     byte var6 = 8;
/*     */     
/* 356 */     for (int var7 = 0; var7 < var6 * var6; var7++) {
/*     */       
/* 358 */       GlStateManager.pushMatrix();
/* 359 */       float var8 = ((var7 % var6) / var6 - 0.5F) / 64.0F;
/* 360 */       float var9 = ((var7 / var6) / var6 - 0.5F) / 64.0F;
/* 361 */       float var10 = 0.0F;
/* 362 */       GlStateManager.translate(var8, var9, var10);
/* 363 */       GlStateManager.rotate(MathHelper.sin((this.panoramaTimer + p_73970_3_) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
/* 364 */       GlStateManager.rotate(-(this.panoramaTimer + p_73970_3_) * 0.1F, 0.0F, 1.0F, 0.0F);
/*     */       
/* 366 */       for (int var11 = 0; var11 < 6; var11++) {
/*     */         
/* 368 */         GlStateManager.pushMatrix();
/*     */         
/* 370 */         if (var11 == 1)
/*     */         {
/* 372 */           GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/* 375 */         if (var11 == 2)
/*     */         {
/* 377 */           GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/* 380 */         if (var11 == 3)
/*     */         {
/* 382 */           GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
/*     */         }
/*     */         
/* 385 */         if (var11 == 4)
/*     */         {
/* 387 */           GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
/*     */         }
/*     */         
/* 390 */         if (var11 == 5)
/*     */         {
/* 392 */           GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
/*     */         }
/*     */         
/* 395 */         this.mc.getTextureManager().bindTexture(titlePanoramaPaths[var11]);
/* 396 */         var5.startDrawingQuads();
/* 397 */         var5.func_178974_a(16777215, 255 / (var7 + 1));
/* 398 */         float var12 = 0.0F;
/* 399 */         var5.addVertexWithUV(-1.0D, -1.0D, 1.0D, (0.0F + var12), (0.0F + var12));
/* 400 */         var5.addVertexWithUV(1.0D, -1.0D, 1.0D, (1.0F - var12), (0.0F + var12));
/* 401 */         var5.addVertexWithUV(1.0D, 1.0D, 1.0D, (1.0F - var12), (1.0F - var12));
/* 402 */         var5.addVertexWithUV(-1.0D, 1.0D, 1.0D, (0.0F + var12), (1.0F - var12));
/* 403 */         var4.draw();
/* 404 */         GlStateManager.popMatrix();
/*     */       } 
/*     */       
/* 407 */       GlStateManager.popMatrix();
/* 408 */       GlStateManager.colorMask(true, true, true, false);
/*     */     } 
/*     */     
/* 411 */     var5.setTranslation(0.0D, 0.0D, 0.0D);
/* 412 */     GlStateManager.colorMask(true, true, true, true);
/* 413 */     GlStateManager.matrixMode(5889);
/* 414 */     GlStateManager.popMatrix();
/* 415 */     GlStateManager.matrixMode(5888);
/* 416 */     GlStateManager.popMatrix();
/* 417 */     GlStateManager.depthMask(true);
/* 418 */     GlStateManager.enableCull();
/* 419 */     GlStateManager.enableDepth();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rotateAndBlurSkybox(float p_73968_1_) {
/* 427 */     this.mc.getTextureManager().bindTexture(this.field_110351_G);
/* 428 */     GL11.glTexParameteri(3553, 10241, 9729);
/* 429 */     GL11.glTexParameteri(3553, 10240, 9729);
/* 430 */     GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
/* 431 */     GlStateManager.enableBlend();
/* 432 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 433 */     GlStateManager.colorMask(true, true, true, false);
/* 434 */     Tessellator var2 = Tessellator.getInstance();
/* 435 */     WorldRenderer var3 = var2.getWorldRenderer();
/* 436 */     var3.startDrawingQuads();
/* 437 */     GlStateManager.disableAlpha();
/* 438 */     byte var4 = 3;
/*     */     
/* 440 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 442 */       var3.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F / (var5 + 1));
/* 443 */       int var6 = width;
/* 444 */       int var7 = height;
/* 445 */       float var8 = (var5 - var4 / 2) / 256.0F;
/* 446 */       var3.addVertexWithUV(var6, var7, zLevel, (0.0F + var8), 1.0D);
/* 447 */       var3.addVertexWithUV(var6, 0.0D, zLevel, (1.0F + var8), 1.0D);
/* 448 */       var3.addVertexWithUV(0.0D, 0.0D, zLevel, (1.0F + var8), 0.0D);
/* 449 */       var3.addVertexWithUV(0.0D, var7, zLevel, (0.0F + var8), 0.0D);
/*     */     } 
/*     */     
/* 452 */     var2.draw();
/* 453 */     GlStateManager.enableAlpha();
/* 454 */     GlStateManager.colorMask(true, true, true, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_) {
/* 462 */     this.mc.getFramebuffer().unbindFramebuffer();
/* 463 */     GlStateManager.viewport(0, 0, 256, 256);
/* 464 */     drawPanorama(p_73971_1_, p_73971_2_, p_73971_3_);
/* 465 */     rotateAndBlurSkybox(p_73971_3_);
/* 466 */     rotateAndBlurSkybox(p_73971_3_);
/* 467 */     rotateAndBlurSkybox(p_73971_3_);
/* 468 */     rotateAndBlurSkybox(p_73971_3_);
/* 469 */     rotateAndBlurSkybox(p_73971_3_);
/* 470 */     rotateAndBlurSkybox(p_73971_3_);
/* 471 */     rotateAndBlurSkybox(p_73971_3_);
/* 472 */     this.mc.getFramebuffer().bindFramebuffer(true);
/* 473 */     GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/* 474 */     Tessellator var4 = Tessellator.getInstance();
/* 475 */     WorldRenderer var5 = var4.getWorldRenderer();
/* 476 */     var5.startDrawingQuads();
/* 477 */     float var6 = (width > height) ? (120.0F / width) : (120.0F / height);
/* 478 */     float var7 = height * var6 / 256.0F;
/* 479 */     float var8 = width * var6 / 256.0F;
/* 480 */     var5.func_178960_a(1.0F, 1.0F, 1.0F, 1.0F);
/* 481 */     int var9 = width;
/* 482 */     int var10 = height;
/* 483 */     var5.addVertexWithUV(0.0D, var10, zLevel, (0.5F - var7), (0.5F + var8));
/* 484 */     var5.addVertexWithUV(var9, var10, zLevel, (0.5F - var7), (0.5F - var8));
/* 485 */     var5.addVertexWithUV(var9, 0.0D, zLevel, (0.5F + var7), (0.5F - var8));
/* 486 */     var5.addVertexWithUV(0.0D, 0.0D, zLevel, (0.5F + var7), (0.5F + var8));
/* 487 */     var4.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 495 */     GlStateManager.disableAlpha();
/* 496 */     renderSkybox(mouseX, mouseY, partialTicks);
/* 497 */     GlStateManager.enableAlpha();
/* 498 */     Tessellator var4 = Tessellator.getInstance();
/* 499 */     WorldRenderer var5 = var4.getWorldRenderer();
/* 500 */     short var6 = 274;
/* 501 */     int var7 = width / 2 - var6 / 2;
/* 502 */     byte var8 = 30;
/* 503 */     drawGradientRect(0.0F, 0.0F, width, height, -2130706433, 16777215);
/* 504 */     drawGradientRect(0.0F, 0.0F, width, height, 0, -2147483648);
/* 505 */     this.mc.getTextureManager().bindTexture(minecraftTitleTextures);
/* 506 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/* 508 */     if (this.updateCounter < 1.0E-4D) {
/*     */       
/* 510 */       drawTexturedModalRect(var7 + 0, var8 + 0, 0, 0, 99, 44);
/* 511 */       drawTexturedModalRect(var7 + 99, var8 + 0, 129, 0, 27, 44);
/* 512 */       drawTexturedModalRect(var7 + 99 + 26, var8 + 0, 126, 0, 3, 44);
/* 513 */       drawTexturedModalRect(var7 + 99 + 26 + 3, var8 + 0, 99, 0, 26, 44);
/* 514 */       drawTexturedModalRect(var7 + 155, var8 + 0, 0, 45, 155, 44);
/*     */     }
/*     */     else {
/*     */       
/* 518 */       drawTexturedModalRect(var7 + 0, var8 + 0, 0, 0, 155, 44);
/* 519 */       drawTexturedModalRect(var7 + 155, var8 + 0, 0, 45, 155, 44);
/*     */     } 
/*     */     
/* 522 */     var5.func_178991_c(-1);
/* 523 */     GlStateManager.pushMatrix();
/* 524 */     GlStateManager.translate((width / 2 + 90), 70.0F, 0.0F);
/* 525 */     GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
/* 526 */     float var9 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
/* 527 */     var9 = var9 * 100.0F / (fontRendererObj.getStringWidth(this.splashText) + 32);
/* 528 */     GlStateManager.scale(var9, var9, var9);
/* 529 */     drawCenteredString(fontRendererObj, this.splashText, 0.0F, -8.0F, -256);
/* 530 */     GlStateManager.popMatrix();
/* 531 */     String var10 = "Minecraft 1.8";
/*     */     
/* 533 */     if (this.mc.isDemo())
/*     */     {
/* 535 */       var10 = String.valueOf(var10) + " Demo";
/*     */     }
/*     */     
/* 538 */     drawString(fontRendererObj, var10, 2, height - 10, -1);
/* 539 */     String var11 = "Copyright Mojang AB. Do not distribute!";
/* 540 */     drawString(fontRendererObj, var11, width - fontRendererObj.getStringWidth(var11) - 2, height - 10, -1);
/*     */     
/* 542 */     if (this.field_92025_p != null && this.field_92025_p.length() > 0) {
/*     */       
/* 544 */       drawRect((this.field_92022_t - 2), (this.field_92021_u - 2), (this.field_92020_v + 2), (this.field_92019_w - 1), 1428160512);
/* 545 */       drawString(fontRendererObj, this.field_92025_p, this.field_92022_t, this.field_92021_u, -1);
/* 546 */       drawString(fontRendererObj, this.field_146972_A, (width - this.field_92024_r) / 2, ((GuiButton)this.buttonList.get(0)).yPosition - 12, -1);
/*     */     } 
/*     */     
/* 549 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 557 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 558 */     Object var4 = this.field_104025_t;
/*     */     
/* 560 */     synchronized (this.field_104025_t) {
/*     */       
/* 562 */       if (this.field_92025_p.length() > 0 && mouseX >= this.field_92022_t && mouseX <= this.field_92020_v && mouseY >= this.field_92021_u && mouseY <= this.field_92019_w) {
/*     */         
/* 564 */         GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.field_104024_v, 13, true);
/* 565 */         var5.disableSecurityWarning();
/* 566 */         this.mc.displayGuiScreen(var5);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiMainMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */