/*     */ package leap.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import leap.modules.player.Scaffold;
/*     */ import leap.modules.render.Appearance;
/*     */ import leap.modules.render.Hotbar;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.ColorUtil;
/*     */ import leap.util.JColor;
/*     */ import leap.util.OtherUtil;
/*     */ import leap.util.Shape;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.gui.inventory.GuiInventory;
/*     */ import net.minecraft.client.network.NetworkPlayerInfo;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import optifine.MathUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HUD
/*     */   extends Module
/*     */ {
/*     */   int fontcolor;
/*  70 */   public Minecraft mc = Minecraft.getMinecraft();
/*     */   
/*     */   double bps;
/*     */   
/*     */   public static double MouseAddLogoX;
/*     */   
/*     */   public static double MouseAddLogoY;
/*     */   public boolean NotiRender = false;
/*  78 */   public ModeSetting placement = new ModeSetting("Line Placement", "Left", new String[] { "Left", "Right" });
/*  79 */   public BooleanSetting noline = new BooleanSetting("No Line", false);
/*  80 */   public static BooleanSetting logo = new BooleanSetting("Logo", true);
/*  81 */   public static BooleanSetting mcfont = new BooleanSetting("Minecraft Font", false);
/*  82 */   public BooleanSetting spacing = new BooleanSetting("Spacing", true);
/*  83 */   public static BooleanSetting text = new BooleanSetting("Low-Case Text", false);
/*     */ 
/*     */ 
/*     */   
/*     */   public HUD() {
/*  88 */     super("HUD", 0, Module.Category.RENDER);
/*  89 */     this.toggled = true;
/*  90 */     addSettings(new Setting[] { (Setting)this.placement, (Setting)this.noline, (Setting)logo, (Setting)mcfont, (Setting)this.spacing, (Setting)text });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getCurrentTimeStamp() {
/* 100 */     SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:a");
/* 101 */     Date now = new Date();
/* 102 */     return sdfDate.format(now);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {
/* 108 */     if (e instanceof leap.events.listeners.EventRenderGUI) {
/*     */ 
/*     */       
/* 111 */       ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 112 */       FontRenderer fr = this.mc.fontRendererObj;
/*     */ 
/*     */ 
/*     */       
/* 116 */       Client.modules.sort(Comparator.comparingInt(m -> paramFontRenderer.getStringWidth(((Module)m).name)).reversed());
/*     */       
/* 118 */       int count = 0;
/*     */       
/* 120 */       if (this.mc.thePlayer != null) {
/* 121 */         double bps2 = MathUtils.square(this.mc.thePlayer.motionX) + MathUtils.square(this.mc.thePlayer.motionZ);
/* 122 */         this.bps = MathUtils.round(Math.sqrt(bps2) * 20.0D * this.mc.timer.timerSpeed, 2.0D);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 131 */       GlStateManager.pushMatrix();
/* 132 */       GlStateManager.color(1.0F, 1.0F, 1.0F);
/* 133 */       int var5 = GuiInventory.guiTop;
/*     */       
/* 135 */       GuiInventory.drawEntityOnScreen(sr.getScaledWidth() - 120, 90, sr.getScaleFactor() * 20, this.mc.thePlayer.rotationYaw, -this.mc.thePlayer.rotationPitch, (EntityLivingBase)this.mc.thePlayer);
/* 136 */       GlStateManager.popMatrix();
/*     */ 
/*     */       
/* 139 */       if (logo.isEnabled()) {
/* 140 */         int ScaleFactor = 50 * sr.getScaleFactor();
/*     */         
/* 142 */         if (this.mc.currentScreen instanceof HudEdit) {
/* 143 */           Shape.color((new Color(255, 205, 0, 255)).getRGB());
/* 144 */           Shape.drawRoundedRect(0.5D + MouseAddLogoX, -2.0D + MouseAddLogoY, 70.0D, 10.0D, 0.0F);
/*     */ 
/*     */           
/* 147 */           Shape.color((new Color(1, 1, 1, 80)).getRGB());
/* 148 */           Shape.drawRoundedRect(0.5D + MouseAddLogoX, 8.0D + MouseAddLogoY, 70.0D, 15.0D, 1.0F);
/*     */           
/* 150 */           Client.customFontRenderer.drawString("Logo", 2.8D + MouseAddLogoX, 1.2D + MouseAddLogoY, new JColor(Color.white.getRGB()));
/*     */         } 
/* 152 */         GlStateManager.color(1.0F, 1.0F, 1.0F);
/* 153 */         if (mcfont.isEnabled()) {
/* 154 */           fr.drawStringWithShadow("Leap Client", 8.0D + MouseAddLogoX, 12.0D + MouseAddLogoY, (new Color(0, 200, 255)).getRGB());
/*     */         } else {
/* 156 */           Client.customFontRendererBig.drawStringWithShadow("Leap Client", 8.0D + MouseAddLogoX, 12.0D + MouseAddLogoY, new JColor((new Color(0, 200, 255)).getRGB()));
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 163 */       this.mc.getTextureManager().bindTexture(new ResourceLocation("pics/Compass.png"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 177 */       String fps = String.valueOf(Minecraft.func_175610_ah());
/* 178 */       if (Hotbar.look.getMode() != "Long") {
/* 179 */         Client.customFontRendererBig.drawStringWithShadow("FPS: " + fps, 1.0D, sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } else {
/* 181 */         Client.customFontRendererBig.drawString("FPS: " + fps, 1.0D, sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 187 */       NetworkPlayerInfo player = this.mc.getNetHandler().func_175102_a(this.mc.thePlayer.getUniqueID());
/*     */ 
/*     */       
/* 190 */       if (Hotbar.look.getMode() != "Long") {
/* 191 */         if (player != null) {
/* 192 */           Client.customFontRendererBig.drawStringWithShadow("BPS: " + this.bps, (92 + fr.getStringWidth(Integer.toString(player.responseTime))), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */         } else {
/* 194 */           Client.customFontRendererBig.drawStringWithShadow("BPS: " + this.bps, 92.0D, sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */         }
/*     */       
/* 197 */       } else if (player != null) {
/* 198 */         Client.customFontRendererBig.drawString("BPS: " + this.bps, (92 + fr.getStringWidth(Integer.toString(player.responseTime))), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } else {
/* 200 */         Client.customFontRendererBig.drawString("BPS: " + this.bps, 92.0D, sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 206 */       if (Client.getModule("Scaffold").isEnabled()) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 211 */         Client.customFontRendererBig.drawStringWithShadow(Integer.toString(Scaffold.getBlockCount()), ((sr.getScaledWidth() >> 1) - Client.customFontRenderer.getStringWidth(Integer.toString(Scaffold.getBlockCount())) / 2), ((sr.getScaledHeight() >> 1) - 12), new JColor(Color.white));
/* 212 */         Scaffold.RenderBlocks();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 217 */       if (Hotbar.look.getMode() != "Long") {
/* 218 */         Client.customFontRendererBig.drawStringWithShadow("X: " + (int)this.mc.thePlayer.posX, (sr.getScaledWidth() - 135), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } else {
/* 220 */         Client.customFontRendererBig.drawString("X: " + (int)this.mc.thePlayer.posX, (sr.getScaledWidth() - 135), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } 
/*     */ 
/*     */       
/* 224 */       if (Hotbar.look.getMode() != "Long") {
/* 225 */         Client.customFontRendererBig.drawStringWithShadow("Y: " + (int)this.mc.thePlayer.posY, (sr.getScaledWidth() - 85), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } else {
/* 227 */         Client.customFontRendererBig.drawString("Y: " + (int)this.mc.thePlayer.posY, (sr.getScaledWidth() - 85), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } 
/*     */ 
/*     */       
/* 231 */       if (Hotbar.look.getMode() != "Long") {
/* 232 */         Client.customFontRendererBig.drawStringWithShadow("Z: " + (int)this.mc.thePlayer.posZ, (sr.getScaledWidth() - 45), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } else {
/* 234 */         Client.customFontRendererBig.drawString("Z: " + (int)this.mc.thePlayer.posZ, (sr.getScaledWidth() - 45), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 239 */       String ping = "Ping: §f" + ((player == null) ? "0" : (String)Integer.valueOf(player.responseTime));
/*     */       
/* 241 */       if (Hotbar.look.getMode() != "Long") {
/* 242 */         Client.customFontRendererBig.drawStringWithShadow(ping, (40 + fr.getStringWidth(fps)), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } else {
/* 244 */         Client.customFontRendererBig.drawString(ping, (40 + fr.getStringWidth(fps)), sr.getScaledHeight() * 0.97D, new JColor(Color.white));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 250 */       fr.drawStringWithShadow(".", -29.0D, 1.0D, Color.white.getRGB());
/* 251 */       GlStateManager.pushMatrix();
/* 252 */       if (this.spacing.isEnabled()) {
/* 253 */         GlStateManager.translate(-1.0F, 8.0F, 0.0F);
/*     */       }
/* 255 */       for (Module m : Client.modules) {
/* 256 */         double offset; if (!m.toggled || m.name.equals("TabGUI") || m.name.equals("HUD")) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 261 */         if (Appearance.look.getMode() == "Sigma") {
/* 262 */           offset = count * (fr.FONT_HEIGHT + 3.5D);
/*     */         } else {
/* 264 */           offset = count * (fr.FONT_HEIGHT + 2.5D);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 270 */         if (Appearance.look.getMode().equalsIgnoreCase("Leap")) {
/*     */           
/* 272 */           Shape.color((new Color(16, 16, 16, 180)).getRGB());
/* 273 */           Shape.drawRoundedRect((sr.getScaledWidth() - fr.getStringWidth(m.name) - 8), offset - 4.0D, 90.0D, 11.5D, 4.0F);
/*     */         } 
/* 275 */         if (Appearance.look.getMode() == "Custom") {
/* 276 */           Shape.color((new Color(16, 16, 16, 180)).getRGB());
/* 277 */           Shape.drawRoundedRect((sr.getScaledWidth() - fr.getStringWidth(m.name) - 8), offset - 4.0D, 90.0D, 11.5D, 4.0F);
/*     */         } 
/* 279 */         if (Appearance.look.getMode().equalsIgnoreCase("Chill")) {
/* 280 */           Gui.drawRect((sr.getScaledWidth() - fr.getStringWidth(m.name) - 8), offset - 4.0D, (sr.getScaledWidth() - 4), fr.FONT_HEIGHT + offset - 1.5D, (new Color(16, 16, 16, 200)).getRGB());
/*     */         }
/* 282 */         if (Appearance.look.getMode().equalsIgnoreCase("Flat")) {
/* 283 */           Gui.drawRect((sr.getScaledWidth() - fr.getStringWidth(m.name) - 8), offset - 4.0D, (sr.getScaledWidth() - 4), fr.FONT_HEIGHT + offset - 1.5D, (new Color(1, 0, 0)).getRGB());
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 288 */         if (!this.noline.isEnabled()) {
/*     */ 
/*     */           
/* 291 */           if (Appearance.look.getMode().equalsIgnoreCase("Leap")) {
/* 292 */             if (this.placement.getMode() == "Right") {
/*     */ 
/*     */               
/* 295 */               Shape.color(this.fontcolor);
/* 296 */               Shape.drawRoundedRect((sr.getScaledWidth() - 1), offset - 4.0D, 90.0D, 11.5D, 2.0F);
/*     */             } else {
/*     */               
/* 299 */               Gui.drawRect((sr.getScaledWidth() - fr.getStringWidth(m.name) - 8), offset - 4.0D, (sr.getScaledWidth() - fr.getStringWidth(m.name) - 6), fr.FONT_HEIGHT + offset - 1.6D, this.fontcolor);
/*     */             } 
/*     */           }
/* 302 */           if (Appearance.look.getMode().equalsIgnoreCase("Custom")) {
/* 303 */             if (this.placement.getMode() == "Right") {
/*     */ 
/*     */               
/* 306 */               Shape.color(this.fontcolor);
/* 307 */               Shape.drawRoundedRect((sr.getScaledWidth() - 1), offset - 4.0D, 90.0D, 11.5D, 2.0F);
/*     */             } else {
/*     */               
/* 310 */               Gui.drawRect((sr.getScaledWidth() - fr.getStringWidth(m.name) - 8), offset - 4.0D, (sr.getScaledWidth() - fr.getStringWidth(m.name) - 6), fr.FONT_HEIGHT + offset - 1.6D, this.fontcolor);
/*     */             } 
/*     */           }
/*     */           
/* 314 */           if (Appearance.look.getMode().equalsIgnoreCase("Chill")) {
/* 315 */             if (this.placement.getMode() == "Right") {
/* 316 */               Gui.drawRect((sr.getScaledWidth() - 1), offset - 4.0D, (sr.getScaledWidth() - 4), fr.FONT_HEIGHT + offset + 1.1D, ColorUtil.getRainbow(0.8F, 0.7F, 1.0F, (count + 1)));
/*     */             } else {
/* 318 */               Gui.drawRect((sr.getScaledWidth() - fr.getStringWidth(m.name) - 8), offset - 4.0D, (sr.getScaledWidth() - fr.getStringWidth(m.name) - 6), fr.FONT_HEIGHT + offset - 1.6D, ColorUtil.getRainbow(2.0F, 0.2F, 1.0F, (count + 1)));
/*     */             } 
/*     */           }
/* 321 */           if (Appearance.look.getMode().equalsIgnoreCase("Flat")) {
/* 322 */             Gui.drawRect(sr.getScaledWidth(), offset - 4.0D, (sr.getScaledWidth() - 2), fr.FONT_HEIGHT + offset + 1.1D, ColorUtil.getRainbow(10.0F, 0.8F, 1.0F, (count + 1)));
/*     */           }
/*     */         } 
/*     */         
/* 326 */         if (Appearance.look.getMode().equalsIgnoreCase("Chill")) {
/* 327 */           this.fontcolor = ColorUtil.getRainbow(1.0F, 0.2F, 0.8F, (count * 100));
/* 328 */         } else if (Appearance.look.getMode().equalsIgnoreCase("Colorful")) {
/* 329 */           this.fontcolor = ColorUtil.getRainbow(0.8F, 0.7F, 0.8F, (count * 100));
/*     */         } 
/* 331 */         if (Appearance.look.getMode().equalsIgnoreCase("Leap"))
/*     */         {
/*     */           
/* 334 */           this.fontcolor = ColorUtil.flash(new Color(0, 20, 35), 1.0F, count + 1).getRGB();
/*     */         }
/* 336 */         if (Appearance.look.getMode().equalsIgnoreCase("Custom"))
/*     */         {
/*     */           
/* 339 */           this.fontcolor = ColorUtil.fade(new Color((int)Appearance.red.getValue(), (int)Appearance.green.getValue(), (int)Appearance.blue.getValue()), count + 1, count + 1).getRGB();
/*     */         }
/* 341 */         if (Appearance.look.getMode().equalsIgnoreCase("Flat")) {
/* 342 */           this.fontcolor = -1;
/*     */         }
/* 344 */         if (Appearance.look.getMode().equalsIgnoreCase("Sigma")) {
/* 345 */           this.fontcolor = -1;
/*     */         }
/*     */         
/* 348 */         if (mcfont.isEnabled()) {
/* 349 */           fr.FONT_HEIGHT = 9;
/* 350 */           fr.drawStringWithShadow(m.name, (sr.getScaledWidth() - fr.getStringWidth(m.name) - 5), (float)offset, this.fontcolor);
/*     */         } else {
/* 352 */           Client.customFontRenderer.drawStringWithShadow(m.name, (sr.getScaledWidth() - fr.getStringWidth(m.name) - 5), (float)offset, new JColor(this.fontcolor));
/*     */         } 
/* 354 */         count++;
/*     */       } 
/*     */       
/* 357 */       GlStateManager.popMatrix();
/*     */       
/* 359 */       drawActivePotionEffects();
/* 360 */       OtherUtil.drawArmorStatus(sr);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderPlayer2d(double n, double n2, float n3, float n4, int n5, int n6, int n7, int n8, float n9, float n10, AbstractClientPlayer abstractClientPlayer) {
/* 367 */     this.mc.getTextureManager().bindTexture(abstractClientPlayer.getLocationSkin());
/* 368 */     GL11.glEnable(3042);
/* 369 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 370 */     Gui.drawScaledCustomSizeModalRect((int)n, (int)n2, n3, n4, n5, n6, n7, n8, n9, n10);
/* 371 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */   public static void renderItem(ItemStack stack, int x, int y) {
/* 375 */     RenderHelper.enableGUIStandardItemLighting();
/* 376 */     (Minecraft.getMinecraft().getRenderItem()).zLevel = -100.0F;
/* 377 */     GlStateManager.scale(1.0F, 1.0F, 0.01F);
/* 378 */     Minecraft.getMinecraft().getRenderItem().func_175042_a(stack, x, y + 8);
/*     */     
/* 380 */     (Minecraft.getMinecraft().getRenderItem()).zLevel = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawActivePotionEffects() {
/* 385 */     Minecraft mc = Minecraft.getMinecraft();
/* 386 */     FontRenderer fr = mc.fontRendererObj;
/* 387 */     ResourceLocation inventoryBackground = new ResourceLocation("textures/gui/container/inventory.png");
/* 388 */     ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/*     */     
/* 390 */     int var1 = GuiContainer.guiLeft - 124;
/* 391 */     int var2 = GuiContainer.guiTop;
/* 392 */     boolean var3 = true;
/* 393 */     Collection var4 = mc.thePlayer.getActivePotionEffects();
/*     */     
/* 395 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 396 */     GlStateManager.disableLighting();
/* 397 */     int var5 = 33;
/*     */     
/* 399 */     if (var4.size() > 5)
/*     */     {
/* 401 */       var5 = 132 / (var4.size() - 1);
/*     */     }
/*     */     
/* 404 */     for (Iterator<PotionEffect> var6 = mc.thePlayer.getActivePotionEffects().iterator(); var6.hasNext(); var2 += var5) {
/*     */       
/* 406 */       PotionEffect var7 = var6.next();
/* 407 */       Potion var8 = Potion.potionTypes[var7.getPotionID()];
/* 408 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 409 */       mc.getTextureManager().bindTexture(inventoryBackground);
/*     */ 
/*     */       
/* 412 */       if (var8.hasStatusIcon())
/*     */       {
/* 414 */         int i = var8.getStatusIconIndex();
/*     */       }
/*     */       
/* 417 */       String var11 = I18n.format(var8.getName(), new Object[0]);
/*     */       
/* 419 */       if (var7.getAmplifier() == 1) {
/*     */         
/* 421 */         var11 = String.valueOf(var11) + " " + I18n.format("enchantment.level.2", new Object[0]);
/*     */       }
/* 423 */       else if (var7.getAmplifier() == 2) {
/*     */         
/* 425 */         var11 = String.valueOf(var11) + " " + I18n.format("enchantment.level.3", new Object[0]);
/*     */       }
/* 427 */       else if (var7.getAmplifier() == 3) {
/*     */         
/* 429 */         var11 = String.valueOf(var11) + " " + I18n.format("enchantment.level.4", new Object[0]);
/*     */       } 
/*     */       
/* 432 */       String var10 = Potion.getDurationString(var7);
/*     */       
/* 434 */       Shape.color((new Color(0, 0, 0, 140)).getRGB());
/* 435 */       Shape.drawRoundedRect(sr.getScaledWidth() * 0.88D - 28.0D, -var2 / 1.5D + sr.getScaledHeight(), (46 + fr.getStringWidth(var11)), 20.0D, 0.0F);
/*     */       
/* 437 */       Client.customFontRendererBig.drawStringWithShadow(var11, sr.getScaledWidth() * 0.88D + 2.0D, -var2 / 1.5D + sr.getScaledHeight() + 4.0D, new JColor(16777215));
/*     */       
/* 439 */       Client.customFontRendererBig.drawStringWithShadow(var10, sr.getScaledWidth() * 0.88D - 25.0D, -var2 / 1.5D + sr.getScaledHeight() + 4.0D, new JColor(8355711));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawTexturedModalRectd(int x, int y, int textureX, int textureY, int width, int height) {
/* 445 */     float var7 = 0.00390625F;
/* 446 */     float var8 = 0.00390625F;
/* 447 */     Tessellator var9 = Tessellator.getInstance();
/* 448 */     WorldRenderer var10 = var9.getWorldRenderer();
/* 449 */     var10.startDrawingQuads();
/* 450 */     var10.addVertexWithUV((x + 0), (y + height), Gui.zLevel, ((textureX + 0) * var7), ((textureY + height) * var8));
/* 451 */     var10.addVertexWithUV((x + width), (y + height), Gui.zLevel, ((textureX + width) * var7), ((textureY + height) * var8));
/* 452 */     var10.addVertexWithUV((x + width), (y + 0), Gui.zLevel, ((textureX + width) * var7), ((textureY + 0) * var8));
/* 453 */     var10.addVertexWithUV((x + 0), (y + 0), Gui.zLevel, ((textureX + 0) * var7), ((textureY + 0) * var8));
/* 454 */     var9.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawGradientRect(double left, double top, double right, double bottom, int startColor, int endColor) {
/* 459 */     float var7 = (startColor >> 24 & 0xFF) / 255.0F;
/* 460 */     float var8 = (startColor >> 16 & 0xFF) / 255.0F;
/* 461 */     float var9 = (startColor >> 8 & 0xFF) / 255.0F;
/* 462 */     float var10 = (startColor & 0xFF) / 255.0F;
/* 463 */     float var11 = (endColor >> 24 & 0xFF) / 255.0F;
/* 464 */     float var12 = (endColor >> 16 & 0xFF) / 255.0F;
/* 465 */     float var13 = (endColor >> 8 & 0xFF) / 255.0F;
/* 466 */     float var14 = (endColor & 0xFF) / 255.0F;
/* 467 */     GlStateManager.func_179090_x();
/* 468 */     GlStateManager.enableBlend();
/* 469 */     GlStateManager.disableAlpha();
/* 470 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 471 */     GlStateManager.shadeModel(7425);
/* 472 */     Tessellator var15 = Tessellator.getInstance();
/* 473 */     WorldRenderer var16 = var15.getWorldRenderer();
/* 474 */     var16.startDrawingQuads();
/* 475 */     var16.func_178960_a(var8, var9, var10, var7);
/* 476 */     var16.addVertex(right, top, Gui.zLevel);
/* 477 */     var16.addVertex(left, top, Gui.zLevel);
/* 478 */     var16.func_178960_a(var12, var13, var14, var11);
/* 479 */     var16.addVertex(left, bottom, Gui.zLevel);
/* 480 */     var16.addVertex(right, bottom, Gui.zLevel);
/* 481 */     var15.draw();
/* 482 */     GlStateManager.shadeModel(7424);
/* 483 */     GlStateManager.disableBlend();
/* 484 */     GlStateManager.enableAlpha();
/* 485 */     GlStateManager.func_179098_w();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\ui\HUD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */