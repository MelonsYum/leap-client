/*      */ package net.minecraft.client.gui;
/*      */ 
/*      */ import com.google.common.base.Predicate;
/*      */ import com.google.common.collect.Iterables;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.awt.Color;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Random;
/*      */ import leap.Client;
/*      */ import leap.events.Event;
/*      */ import leap.events.listeners.EventRender2D;
/*      */ import leap.events.listeners.EventRenderGUI;
/*      */ import leap.modules.Module;
/*      */ import leap.modules.render.Hotbar;
/*      */ import leap.modules.render.Scoreboard;
/*      */ import leap.util.ColorUtil;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.renderer.Tessellator;
/*      */ import net.minecraft.client.renderer.WorldRenderer;
/*      */ import net.minecraft.client.renderer.entity.RenderItem;
/*      */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.resources.I18n;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.boss.BossStatus;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.scoreboard.Score;
/*      */ import net.minecraft.scoreboard.ScoreObjective;
/*      */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*      */ import net.minecraft.scoreboard.Scoreboard;
/*      */ import net.minecraft.scoreboard.Team;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.FoodStats;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.StringUtils;
/*      */ import net.minecraft.world.border.WorldBorder;
/*      */ import optifine.Config;
/*      */ import optifine.CustomColors;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class GuiIngame
/*      */   extends Gui
/*      */ {
/*   63 */   private static final ResourceLocation vignetteTexPath = new ResourceLocation("textures/misc/vignette.png");
/*   64 */   private static final ResourceLocation widgetsTexPath = new ResourceLocation("textures/gui/widgets.png");
/*   65 */   private static final ResourceLocation customHotbarPath = new ResourceLocation("pics/widgets.png");
/*   66 */   private static final ResourceLocation customHotbarLongPath = new ResourceLocation("pics/widgetsLong.png");
/*   67 */   private static final ResourceLocation pumpkinBlurTexPath = new ResourceLocation("textures/misc/pumpkinblur.png");
/*   68 */   private final Random rand = new Random();
/*      */   
/*      */   private final Minecraft mc;
/*      */   
/*      */   private final RenderItem itemRenderer;
/*      */   
/*      */   private final GuiNewChat persistantChatGUI;
/*      */   
/*      */   private final GuiStreamIndicator streamIndicator;
/*      */   private int updateCounter;
/*   78 */   private String recordPlaying = "";
/*      */ 
/*      */   
/*      */   private int recordPlayingUpFor;
/*      */   
/*      */   private boolean recordIsPlaying;
/*      */   
/*   85 */   public float prevVignetteBrightness = 1.0F;
/*      */   
/*      */   private int remainingHighlightTicks;
/*      */   
/*      */   private ItemStack highlightingItemStack;
/*      */   
/*      */   private final GuiOverlayDebug overlayDebug;
/*      */   
/*      */   private final GuiSpectator field_175197_u;
/*      */   private final GuiPlayerTabOverlay overlayPlayerList;
/*      */   private int field_175195_w;
/*   96 */   private String field_175201_x = "";
/*   97 */   private String field_175200_y = "";
/*      */   private int field_175199_z;
/*      */   private int field_175192_A;
/*      */   private int field_175193_B;
/*  101 */   private int field_175194_C = 0;
/*  102 */   private int field_175189_D = 0;
/*  103 */   private long field_175190_E = 0L;
/*  104 */   private long field_175191_F = 0L;
/*      */   
/*      */   private static final String __OBFID = "CL_00000661";
/*      */   
/*      */   public GuiIngame(Minecraft mcIn) {
/*  109 */     this.mc = mcIn;
/*  110 */     this.itemRenderer = mcIn.getRenderItem();
/*  111 */     this.overlayDebug = new GuiOverlayDebug(mcIn);
/*  112 */     this.field_175197_u = new GuiSpectator(mcIn);
/*  113 */     this.persistantChatGUI = new GuiNewChat(mcIn);
/*  114 */     this.streamIndicator = new GuiStreamIndicator(mcIn);
/*  115 */     this.overlayPlayerList = new GuiPlayerTabOverlay(mcIn, this);
/*  116 */     func_175177_a();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175177_a() {
/*  121 */     this.field_175199_z = 10;
/*  122 */     this.field_175192_A = 70;
/*  123 */     this.field_175193_B = 20;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_175180_a(float p_175180_1_) {
/*  130 */     ScaledResolution var2 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/*  131 */     int var3 = var2.getScaledWidth();
/*  132 */     int var4 = var2.getScaledHeight();
/*  133 */     this.mc.entityRenderer.setupOverlayRendering();
/*      */ 
/*      */ 
/*      */     
/*  137 */     GlStateManager.enableBlend();
/*      */     
/*  139 */     if (Config.isVignetteEnabled()) {
/*      */       
/*  141 */       func_180480_a(this.mc.thePlayer.getBrightness(p_175180_1_), var2);
/*      */     }
/*      */     else {
/*      */       
/*  145 */       GlStateManager.enableDepth();
/*  146 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*      */     } 
/*      */     
/*  149 */     ItemStack var5 = this.mc.thePlayer.inventory.armorItemInSlot(3);
/*      */     
/*  151 */     if (this.mc.gameSettings.thirdPersonView == 0 && var5 != null && var5.getItem() == Item.getItemFromBlock(Blocks.pumpkin))
/*      */     {
/*  153 */       func_180476_e(var2);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  158 */     if (!this.mc.thePlayer.isPotionActive(Potion.confusion)) {
/*      */       
/*  160 */       float var7 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * p_175180_1_;
/*      */       
/*  162 */       if (var7 > 0.0F)
/*      */       {
/*  164 */         func_180474_b(var7, var2);
/*      */       }
/*      */     } 
/*      */     
/*  168 */     if (this.mc.playerController.enableEverythingIsScrewedUpMode()) {
/*      */       
/*  170 */       this.field_175197_u.func_175264_a(var2, p_175180_1_);
/*      */     }
/*      */     else {
/*      */       
/*  174 */       func_180479_a(var2, p_175180_1_);
/*      */     } 
/*      */     
/*  177 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  178 */     this.mc.getTextureManager().bindTexture(icons);
/*  179 */     GlStateManager.enableBlend();
/*      */     
/*  181 */     if (func_175183_b() && this.mc.gameSettings.thirdPersonView < 1) {
/*      */       
/*  183 */       GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
/*  184 */       GlStateManager.enableAlpha();
/*  185 */       drawTexturedModalRect(var3 / 2 - 7, var4 / 2 - 7, 0, 0, 16, 16);
/*      */     } 
/*      */     
/*  188 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  189 */     this.mc.mcProfiler.startSection("bossHealth");
/*  190 */     renderBossHealth();
/*  191 */     this.mc.mcProfiler.endSection();
/*      */     
/*  193 */     if (this.mc.playerController.shouldDrawHUD())
/*      */     {
/*  195 */       func_180477_d(var2);
/*      */     }
/*      */     
/*  198 */     GlStateManager.disableBlend();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  203 */     if (this.mc.thePlayer.getSleepTimer() > 0) {
/*      */       
/*  205 */       this.mc.mcProfiler.startSection("sleep");
/*  206 */       GlStateManager.disableDepth();
/*  207 */       GlStateManager.disableAlpha();
/*  208 */       int i = this.mc.thePlayer.getSleepTimer();
/*  209 */       float var7 = i / 100.0F;
/*      */       
/*  211 */       if (var7 > 1.0F)
/*      */       {
/*  213 */         var7 = 1.0F - (i - 100) / 10.0F;
/*      */       }
/*      */       
/*  216 */       int var8 = (int)(220.0F * var7) << 24 | 0x101020;
/*  217 */       drawRect(0.0D, 0.0D, var3, var4, var8);
/*  218 */       GlStateManager.enableAlpha();
/*  219 */       GlStateManager.enableDepth();
/*  220 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */     
/*  223 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  224 */     int var11 = var3 / 2 - 91;
/*      */     
/*  226 */     if (this.mc.thePlayer.isRidingHorse()) {
/*      */       
/*  228 */       func_175186_a(var2, var11);
/*      */     }
/*  230 */     else if (this.mc.playerController.gameIsSurvivalOrAdventure()) {
/*      */       
/*  232 */       func_175176_b(var2, var11);
/*      */     } 
/*      */     
/*  235 */     if (this.mc.gameSettings.heldItemTooltips && !this.mc.playerController.enableEverythingIsScrewedUpMode()) {
/*      */       
/*  237 */       func_175182_a(var2);
/*      */     }
/*  239 */     else if (this.mc.thePlayer.func_175149_v()) {
/*      */       
/*  241 */       this.field_175197_u.func_175263_a(var2);
/*      */     } 
/*      */     
/*  244 */     if (this.mc.isDemo())
/*      */     {
/*  246 */       func_175185_b(var2);
/*      */     }
/*      */     
/*  249 */     if (this.mc.gameSettings.showDebugInfo)
/*      */     {
/*  251 */       this.overlayDebug.func_175237_a(var2);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  256 */     if (this.recordPlayingUpFor > 0) {
/*      */       
/*  258 */       this.mc.mcProfiler.startSection("overlayMessage");
/*  259 */       float var7 = this.recordPlayingUpFor - p_175180_1_;
/*  260 */       int var8 = (int)(var7 * 255.0F / 20.0F);
/*      */       
/*  262 */       if (var8 > 255)
/*      */       {
/*  264 */         var8 = 255;
/*      */       }
/*      */       
/*  267 */       if (var8 > 8) {
/*      */         
/*  269 */         GlStateManager.pushMatrix();
/*  270 */         GlStateManager.translate((var3 / 2), (var4 - 68), 0.0F);
/*  271 */         GlStateManager.enableBlend();
/*  272 */         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  273 */         int var9 = 16777215;
/*      */         
/*  275 */         if (this.recordIsPlaying)
/*      */         {
/*  277 */           var9 = Color.HSBtoRGB(var7 / 50.0F, 0.7F, 0.6F) & 0xFFFFFF;
/*      */         }
/*      */         
/*  280 */         func_175179_f().drawString(this.recordPlaying, (-func_175179_f().getStringWidth(this.recordPlaying) / 2), -4.0D, var9 + (var8 << 24 & 0xFF000000));
/*  281 */         GlStateManager.disableBlend();
/*  282 */         GlStateManager.popMatrix();
/*      */       } 
/*      */       
/*  285 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */     
/*  288 */     if (this.field_175195_w > 0) {
/*      */ 
/*      */       
/*  291 */       this.mc.mcProfiler.startSection("titleAndSubtitle");
/*  292 */       float var7 = this.field_175195_w - p_175180_1_;
/*  293 */       int var8 = 255;
/*      */       
/*  295 */       if (this.field_175195_w > this.field_175193_B + this.field_175192_A) {
/*      */         
/*  297 */         float var12 = (this.field_175199_z + this.field_175192_A + this.field_175193_B) - var7;
/*  298 */         var8 = (int)(var12 * 255.0F / this.field_175199_z);
/*      */       } 
/*      */       
/*  301 */       if (this.field_175195_w <= this.field_175193_B)
/*      */       {
/*  303 */         var8 = (int)(var7 * 255.0F / this.field_175193_B);
/*      */       }
/*      */       
/*  306 */       var8 = MathHelper.clamp_int(var8, 0, 255);
/*      */       
/*  308 */       if (var8 > 8) {
/*      */         
/*  310 */         GlStateManager.pushMatrix();
/*  311 */         GlStateManager.translate((var3 / 2), (var4 / 2), 0.0F);
/*  312 */         GlStateManager.enableBlend();
/*  313 */         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  314 */         GlStateManager.pushMatrix();
/*  315 */         GlStateManager.scale(4.0F, 4.0F, 4.0F);
/*  316 */         int var9 = var8 << 24 & 0xFF000000;
/*  317 */         func_175179_f().func_175065_a(this.field_175201_x, (-func_175179_f().getStringWidth(this.field_175201_x) / 2), -10.0F, 0xFFFFFF | var9, true);
/*  318 */         GlStateManager.popMatrix();
/*  319 */         GlStateManager.pushMatrix();
/*  320 */         GlStateManager.scale(2.0F, 2.0F, 2.0F);
/*  321 */         func_175179_f().func_175065_a(this.field_175200_y, (-func_175179_f().getStringWidth(this.field_175200_y) / 2), 5.0F, 0xFFFFFF | var9, true);
/*  322 */         GlStateManager.popMatrix();
/*  323 */         GlStateManager.disableBlend();
/*  324 */         GlStateManager.popMatrix();
/*      */       } 
/*      */       
/*  327 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */     
/*  330 */     Scoreboard var121 = this.mc.theWorld.getScoreboard();
/*  331 */     ScoreObjective var13 = null;
/*  332 */     ScorePlayerTeam var15 = var121.getPlayersTeam(this.mc.thePlayer.getName());
/*      */     
/*  334 */     if (var15 != null) {
/*      */       
/*  336 */       int var16 = var15.func_178775_l().func_175746_b();
/*      */       
/*  338 */       if (var16 >= 0)
/*      */       {
/*  340 */         var13 = var121.getObjectiveInDisplaySlot(3 + var16);
/*      */       }
/*      */     } 
/*      */     
/*  344 */     ScoreObjective var161 = (var13 != null) ? var13 : var121.getObjectiveInDisplaySlot(1);
/*      */     
/*  346 */     if (var161 != null)
/*      */     {
/*      */       
/*  349 */       if (Scoreboard.show.isEnabled()) {
/*  350 */         GlStateManager.pushMatrix();
/*  351 */         if (Scoreboard.clip.isEnabled()) {
/*  352 */           int offset = var2.getScaledHeight() / 3 - 0;
/*      */           
/*  354 */           offset = (int)(Client.modules.stream().filter(m -> m.isEnabled()).count() * 5L);
/*  355 */           double X = Scoreboard.scoreboardX.getValue();
/*      */           
/*  357 */           GlStateManager.translate(X, offset, 0.0D);
/*  358 */           func_180475_a(var161, var2);
/*  359 */           GlStateManager.translate(-X, -offset, 0.0D);
/*      */         } else {
/*      */           
/*  362 */           double X = Scoreboard.scoreboardX.getValue();
/*  363 */           double Y = Scoreboard.scoreboardY.getValue();
/*  364 */           GlStateManager.translate(X, Y, 0.0D);
/*  365 */           func_180475_a(var161, var2);
/*  366 */           GlStateManager.translate(-X, -Y, 0.0D);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  371 */         GlStateManager.popMatrix();
/*      */       } 
/*      */     }
/*      */     
/*  375 */     GlStateManager.enableBlend();
/*  376 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  377 */     GlStateManager.disableAlpha();
/*  378 */     GlStateManager.pushMatrix();
/*  379 */     GlStateManager.translate(0.0F, (var4 - 48), 0.0F);
/*  380 */     this.mc.mcProfiler.startSection("chat");
/*  381 */     this.persistantChatGUI.drawChat(this.updateCounter);
/*  382 */     this.mc.mcProfiler.endSection();
/*  383 */     GlStateManager.popMatrix();
/*  384 */     var161 = var121.getObjectiveInDisplaySlot(0);
/*      */     
/*  386 */     if (this.mc.gameSettings.keyBindPlayerList.getIsKeyPressed() && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.func_175106_d().size() > 1 || var161 != null)) {
/*      */       
/*  388 */       this.overlayPlayerList.func_175246_a(true);
/*  389 */       this.overlayPlayerList.func_175249_a(var3, var121, var161);
/*      */     }
/*      */     else {
/*      */       
/*  393 */       this.overlayPlayerList.func_175246_a(false);
/*      */     } 
/*      */ 
/*      */     
/*  397 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  398 */     GlStateManager.disableLighting();
/*  399 */     GlStateManager.enableAlpha();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_180479_a(ScaledResolution p_180479_1_, float p_180479_2_) {
/*  404 */     if (this.mc.func_175606_aa() instanceof EntityPlayer) {
/*      */       
/*  406 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/*  408 */       EntityPlayer var3 = (EntityPlayer)this.mc.func_175606_aa();
/*  409 */       int var4 = p_180479_1_.getScaledWidth() / 2;
/*  410 */       float var5 = zLevel;
/*  411 */       zLevel = -90.0F;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  416 */       if (Hotbar.look.getMode() == "Normal Custom") {
/*  417 */         this.mc.getTextureManager().bindTexture(customHotbarPath);
/*  418 */         drawTexturedModalRect(var4 - 91, p_180479_1_.getScaledHeight() - 22, 0, 0, 182, 22);
/*  419 */         drawTexturedModalRect(var4 - 91 - 1 + var3.inventory.currentItem * 20, p_180479_1_.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
/*      */       } 
/*  421 */       if (Hotbar.look.getMode() == "Long") {
/*  422 */         this.mc.getTextureManager().bindTexture(customHotbarLongPath);
/*  423 */         drawRect(p_180479_1_.getScaledWidth(), (p_180479_1_.getScaledHeight() - 22), -p_180479_1_.getScaledWidth(), p_180479_1_.getScaledHeight(), (new Color(1, 1, 1, 200)).getRGB());
/*  424 */         drawRect(p_180479_1_.getScaledWidth(), (p_180479_1_.getScaledHeight() - 22), -p_180479_1_.getScaledWidth(), (p_180479_1_.getScaledHeight() - 20), (new Color(ColorUtil.fade(Color.cyan, 1, 1).getRGB())).getRGB());
/*      */ 
/*      */         
/*  427 */         drawRect((var4 - 91 - 1 + var3.inventory.currentItem * 20 + 2), (p_180479_1_.getScaledHeight() - 22), (var4 - 91 - 1 + var3.inventory.currentItem * 20 + 23), (p_180479_1_.getScaledHeight() - 22 - 1 + 24), (new Color(ColorUtil.fade(Color.cyan, 1, 1).getRGB())).getRGB());
/*      */       } 
/*      */       
/*  430 */       if (Hotbar.look.getMode() == "Normal") {
/*  431 */         this.mc.getTextureManager().bindTexture(widgetsTexPath);
/*  432 */         drawTexturedModalRect(var4 - 91, p_180479_1_.getScaledHeight() - 22, 0, 0, 182, 22);
/*  433 */         drawTexturedModalRect(var4 - 91 - 1 + var3.inventory.currentItem * 20, p_180479_1_.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
/*      */       } 
/*  435 */       zLevel = var5;
/*  436 */       GlStateManager.enableRescaleNormal();
/*  437 */       GlStateManager.enableBlend();
/*  438 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  439 */       RenderHelper.enableGUIStandardItemLighting();
/*      */       
/*  441 */       for (int var6 = 0; var6 < 9; var6++) {
/*      */         
/*  443 */         int var7 = p_180479_1_.getScaledWidth() / 2 - 90 + var6 * 20 + 2;
/*  444 */         int var8 = p_180479_1_.getScaledHeight() - 16 - 3;
/*  445 */         func_175184_a(var6, var7, var8, p_180479_2_, var3);
/*      */       } 
/*      */ 
/*      */       
/*  449 */       Client.onEvent((Event)new EventRenderGUI());
/*      */       
/*  451 */       Client.onEvent((Event)new EventRender2D());
/*      */ 
/*      */ 
/*      */       
/*  455 */       Client.notificationManager.drawScreen();
/*  456 */       RenderHelper.disableStandardItemLighting();
/*  457 */       GlStateManager.disableRescaleNormal();
/*  458 */       GlStateManager.disableBlend();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_175186_a(ScaledResolution p_175186_1_, int p_175186_2_) {
/*  465 */     this.mc.mcProfiler.startSection("jumpBar");
/*  466 */     this.mc.getTextureManager().bindTexture(Gui.icons);
/*  467 */     float var3 = this.mc.thePlayer.getHorseJumpPower();
/*  468 */     short var4 = 182;
/*  469 */     int var5 = (int)(var3 * (var4 + 1));
/*  470 */     int var6 = p_175186_1_.getScaledHeight() - 32 + 3;
/*  471 */     drawTexturedModalRect(p_175186_2_, var6, 0, 84, var4, 5);
/*      */     
/*  473 */     if (var5 > 0)
/*      */     {
/*  475 */       drawTexturedModalRect(p_175186_2_, var6, 0, 89, var5, 5);
/*      */     }
/*      */     
/*  478 */     this.mc.mcProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175176_b(ScaledResolution p_175176_1_, int p_175176_2_) {
/*  483 */     this.mc.mcProfiler.startSection("expBar");
/*  484 */     this.mc.getTextureManager().bindTexture(Gui.icons);
/*  485 */     int var3 = this.mc.thePlayer.xpBarCap();
/*      */ 
/*      */     
/*  488 */     if (var3 > 0) {
/*      */       
/*  490 */       short var9 = 182;
/*  491 */       int var10 = (int)(this.mc.thePlayer.experience * (var9 + 1));
/*  492 */       int var6 = p_175176_1_.getScaledHeight() - 32 + 3;
/*  493 */       drawTexturedModalRect(p_175176_2_, var6, 0, 64, var9, 5);
/*      */       
/*  495 */       if (var10 > 0)
/*      */       {
/*  497 */         drawTexturedModalRect(p_175176_2_, var6, 0, 69, var10, 5);
/*      */       }
/*      */     } 
/*      */     
/*  501 */     this.mc.mcProfiler.endSection();
/*      */     
/*  503 */     if (this.mc.thePlayer.experienceLevel > 0) {
/*      */       
/*  505 */       this.mc.mcProfiler.startSection("expLevel");
/*  506 */       int var91 = 8453920;
/*      */       
/*  508 */       if (Config.isCustomColors())
/*      */       {
/*  510 */         var91 = CustomColors.getExpBarTextColor(var91);
/*      */       }
/*      */       
/*  513 */       int i = this.mc.thePlayer.experienceLevel;
/*  514 */       int var6 = (p_175176_1_.getScaledWidth() - func_175179_f().getStringWidth(i)) / 2;
/*  515 */       int var7 = p_175176_1_.getScaledHeight() - 31 - 4;
/*  516 */       boolean var8 = false;
/*      */       
/*  518 */       FontRenderer fr = this.mc.fontRendererObj;
/*  519 */       this.mc.fontRendererObj.drawString(i, (var6 + 1), var7, 0);
/*  520 */       this.mc.fontRendererObj.drawString(i, (var6 - 1), var7, 0);
/*  521 */       func_175179_f().drawString(i, var6, (var7 + 1), 0);
/*  522 */       func_175179_f().drawString(i, var6, (var7 - 1), 0);
/*  523 */       func_175179_f().drawString(i, var6, var7, var91);
/*  524 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175182_a(ScaledResolution p_175182_1_) {
/*  530 */     this.mc.mcProfiler.startSection("toolHighlight");
/*      */     
/*  532 */     if (this.remainingHighlightTicks > 0 && this.highlightingItemStack != null) {
/*      */       
/*  534 */       String var2 = this.highlightingItemStack.getDisplayName();
/*      */       
/*  536 */       if (this.highlightingItemStack.hasDisplayName())
/*      */       {
/*  538 */         var2 = EnumChatFormatting.ITALIC + var2;
/*      */       }
/*      */       
/*  541 */       int var3 = (p_175182_1_.getScaledWidth() - func_175179_f().getStringWidth(var2)) / 2;
/*  542 */       int var4 = p_175182_1_.getScaledHeight() - 59;
/*      */       
/*  544 */       if (!this.mc.playerController.shouldDrawHUD())
/*      */       {
/*  546 */         var4 += 14;
/*      */       }
/*      */       
/*  549 */       int var5 = (int)(this.remainingHighlightTicks * 256.0F / 10.0F);
/*      */       
/*  551 */       if (var5 > 255)
/*      */       {
/*  553 */         var5 = 255;
/*      */       }
/*      */       
/*  556 */       if (var5 > 0) {
/*      */         
/*  558 */         GlStateManager.pushMatrix();
/*  559 */         GlStateManager.enableBlend();
/*  560 */         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*  561 */         func_175179_f().drawStringWithShadow(var2, var3, var4, 16777215 + (var5 << 24));
/*  562 */         GlStateManager.disableBlend();
/*  563 */         GlStateManager.popMatrix();
/*      */       } 
/*      */     } 
/*      */     
/*  567 */     this.mc.mcProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175185_b(ScaledResolution p_175185_1_) {
/*  572 */     this.mc.mcProfiler.startSection("demo");
/*  573 */     String var2 = "";
/*      */     
/*  575 */     if (this.mc.theWorld.getTotalWorldTime() >= 120500L) {
/*      */       
/*  577 */       var2 = I18n.format("demo.demoExpired", new Object[0]);
/*      */     }
/*      */     else {
/*      */       
/*  581 */       var2 = I18n.format("demo.remainingTime", new Object[] { StringUtils.ticksToElapsedTime((int)(120500L - this.mc.theWorld.getTotalWorldTime())) });
/*      */     } 
/*      */     
/*  584 */     int var3 = func_175179_f().getStringWidth(var2);
/*  585 */     func_175179_f().drawStringWithShadow(var2, (p_175185_1_.getScaledWidth() - var3 - 10), 5.0D, 16777215);
/*  586 */     this.mc.mcProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean func_175183_b() {
/*  591 */     if (this.mc.gameSettings.showDebugInfo && !this.mc.thePlayer.func_175140_cp() && !this.mc.gameSettings.field_178879_v)
/*      */     {
/*  593 */       return false;
/*      */     }
/*  595 */     if (this.mc.playerController.enableEverythingIsScrewedUpMode()) {
/*      */       
/*  597 */       if (this.mc.pointedEntity != null)
/*      */       {
/*  599 */         return true;
/*      */       }
/*      */ 
/*      */       
/*  603 */       if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*      */         
/*  605 */         BlockPos var1 = this.mc.objectMouseOver.func_178782_a();
/*      */         
/*  607 */         if (this.mc.theWorld.getTileEntity(var1) instanceof net.minecraft.inventory.IInventory)
/*      */         {
/*  609 */           return true;
/*      */         }
/*      */       } 
/*      */       
/*  613 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  618 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_180478_c(ScaledResolution p_180478_1_) {
/*  624 */     this.streamIndicator.render(p_180478_1_.getScaledWidth() - 10, 10);
/*      */   }
/*      */   
/*      */   private void func_180475_a(ScoreObjective p_180475_1_, ScaledResolution p_180475_2_) {
/*      */     ArrayList var21;
/*  629 */     Scoreboard var3 = p_180475_1_.getScoreboard();
/*  630 */     Collection var4 = var3.getSortedScores(p_180475_1_);
/*  631 */     ArrayList var5 = Lists.newArrayList(Iterables.filter(var4, new Predicate()
/*      */           {
/*      */             private static final String __OBFID = "CL_00001958";
/*      */             
/*      */             public boolean func_178903_a(Score p_178903_1_) {
/*  636 */               return (p_178903_1_.getPlayerName() != null && !p_178903_1_.getPlayerName().startsWith("#"));
/*      */             }
/*      */             
/*      */             public boolean apply(Object p_apply_1_) {
/*  640 */               return func_178903_a((Score)p_apply_1_);
/*      */             }
/*      */           }));
/*      */ 
/*      */     
/*  645 */     if (var5.size() > 15) {
/*      */       
/*  647 */       var21 = Lists.newArrayList(Iterables.skip(var5, var4.size() - 15));
/*      */     }
/*      */     else {
/*      */       
/*  651 */       var21 = var5;
/*      */     } 
/*      */     
/*  654 */     int var6 = func_175179_f().getStringWidth(p_180475_1_.getDisplayName());
/*      */ 
/*      */     
/*  657 */     for (Iterator<Score> var22 = var21.iterator(); var22.hasNext(); var6 = Math.max(var6, func_175179_f().getStringWidth(var10))) {
/*      */       
/*  659 */       Score var23 = var22.next();
/*  660 */       ScorePlayerTeam var24 = var3.getPlayersTeam(var23.getPlayerName());
/*  661 */       String var10 = String.valueOf(ScorePlayerTeam.formatPlayerName((Team)var24, var23.getPlayerName())) + ": " + EnumChatFormatting.RED + var23.getScorePoints();
/*      */     } 
/*      */     
/*  664 */     int var221 = var21.size() * (func_175179_f()).FONT_HEIGHT;
/*  665 */     int var231 = p_180475_2_.getScaledHeight() / 2 + var221 / 3;
/*  666 */     byte var241 = 3;
/*  667 */     int var25 = p_180475_2_.getScaledWidth() - var6 - var241;
/*  668 */     int var11 = 0;
/*  669 */     Iterator<Score> var12 = var21.iterator();
/*      */     
/*  671 */     while (var12.hasNext()) {
/*      */       
/*  673 */       Score var13 = var12.next();
/*  674 */       var11++;
/*  675 */       ScorePlayerTeam var14 = var3.getPlayersTeam(var13.getPlayerName());
/*  676 */       String var15 = ScorePlayerTeam.formatPlayerName((Team)var14, var13.getPlayerName());
/*  677 */       String var16 = EnumChatFormatting.RED + var13.getScorePoints();
/*  678 */       int var18 = var231 - var11 * (func_175179_f()).FONT_HEIGHT;
/*  679 */       int var19 = p_180475_2_.getScaledWidth() - var241 + 2;
/*  680 */       drawRect((var25 - 2), var18, var19, (var18 + (func_175179_f()).FONT_HEIGHT), 1342177280);
/*  681 */       func_175179_f().drawString(var15, var25, var18, 553648127);
/*  682 */       func_175179_f().drawString(var16, (var19 - func_175179_f().getStringWidth(var16)), var18, 553648127);
/*      */       
/*  684 */       if (var11 == var21.size()) {
/*      */         
/*  686 */         String var20 = p_180475_1_.getDisplayName();
/*  687 */         drawRect((var25 - 2), (var18 - (func_175179_f()).FONT_HEIGHT - 1), var19, (var18 - 1), 1610612736);
/*  688 */         drawRect((var25 - 2), (var18 - 1), var19, var18, 1342177280);
/*      */         
/*  690 */         func_175179_f().drawString(var20, (var25 + var6 / 2 - func_175179_f().getStringWidth(var20) / 2), (var18 - (func_175179_f()).FONT_HEIGHT), 553648127);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180477_d(ScaledResolution p_180477_1_) {
/*  697 */     if (this.mc.func_175606_aa() instanceof EntityPlayer) {
/*      */       
/*  699 */       EntityPlayer var2 = (EntityPlayer)this.mc.func_175606_aa();
/*  700 */       int var3 = MathHelper.ceiling_float_int(var2.getHealth());
/*  701 */       boolean var4 = (this.field_175191_F > this.updateCounter && (this.field_175191_F - this.updateCounter) / 3L % 2L == 1L);
/*      */       
/*  703 */       if (var3 < this.field_175194_C && var2.hurtResistantTime > 0) {
/*      */         
/*  705 */         this.field_175190_E = Minecraft.getSystemTime();
/*  706 */         this.field_175191_F = (this.updateCounter + 20);
/*      */       }
/*  708 */       else if (var3 > this.field_175194_C && var2.hurtResistantTime > 0) {
/*      */         
/*  710 */         this.field_175190_E = Minecraft.getSystemTime();
/*  711 */         this.field_175191_F = (this.updateCounter + 10);
/*      */       } 
/*      */       
/*  714 */       if (Minecraft.getSystemTime() - this.field_175190_E > 1000L) {
/*      */         
/*  716 */         this.field_175194_C = var3;
/*  717 */         this.field_175189_D = var3;
/*  718 */         this.field_175190_E = Minecraft.getSystemTime();
/*      */       } 
/*      */       
/*  721 */       this.field_175194_C = var3;
/*  722 */       int var5 = this.field_175189_D;
/*  723 */       this.rand.setSeed((this.updateCounter * 312871));
/*  724 */       boolean var6 = false;
/*  725 */       FoodStats var7 = var2.getFoodStats();
/*  726 */       int var8 = var7.getFoodLevel();
/*  727 */       int var9 = var7.getPrevFoodLevel();
/*  728 */       IAttributeInstance var10 = var2.getEntityAttribute(SharedMonsterAttributes.maxHealth);
/*  729 */       int var11 = p_180477_1_.getScaledWidth() / 2 - 91;
/*  730 */       int var12 = p_180477_1_.getScaledWidth() / 2 + 91;
/*  731 */       int var13 = p_180477_1_.getScaledHeight() - 39;
/*  732 */       float var14 = (float)var10.getAttributeValue();
/*  733 */       float var15 = var2.getAbsorptionAmount();
/*  734 */       int var16 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F / 10.0F);
/*  735 */       int var17 = Math.max(10 - var16 - 2, 3);
/*  736 */       int var18 = var13 - (var16 - 1) * var17 - 10;
/*  737 */       float var19 = var15;
/*  738 */       int var20 = var2.getTotalArmorValue();
/*  739 */       int var21 = -1;
/*      */       
/*  741 */       if (var2.isPotionActive(Potion.regeneration))
/*      */       {
/*  743 */         var21 = this.updateCounter % MathHelper.ceiling_float_int(var14 + 5.0F);
/*      */       }
/*      */       
/*  746 */       this.mc.mcProfiler.startSection("armor");
/*      */       
/*      */       int var22;
/*      */       
/*  750 */       for (var22 = 0; var22 < 10; var22++) {
/*      */         
/*  752 */         if (var20 > 0) {
/*      */           
/*  754 */           int var23 = var11 + var22 * 8;
/*      */           
/*  756 */           if (var22 * 2 + 1 < var20)
/*      */           {
/*  758 */             drawTexturedModalRect(var23, var18, 34, 9, 9, 9);
/*      */           }
/*      */           
/*  761 */           if (var22 * 2 + 1 == var20)
/*      */           {
/*  763 */             drawTexturedModalRect(var23, var18, 25, 9, 9, 9);
/*      */           }
/*      */           
/*  766 */           if (var22 * 2 + 1 > var20)
/*      */           {
/*  768 */             drawTexturedModalRect(var23, var18, 16, 9, 9, 9);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  773 */       this.mc.mcProfiler.endStartSection("health");
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  778 */       for (var22 = MathHelper.ceiling_float_int((var14 + var15) / 2.0F) - 1; var22 >= 0; var22--) {
/*      */         
/*  780 */         int var23 = 16;
/*      */         
/*  782 */         if (var2.isPotionActive(Potion.poison)) {
/*      */           
/*  784 */           var23 += 36;
/*      */         }
/*  786 */         else if (var2.isPotionActive(Potion.wither)) {
/*      */           
/*  788 */           var23 += 72;
/*      */         } 
/*      */         
/*  791 */         byte var34 = 0;
/*      */         
/*  793 */         if (var4)
/*      */         {
/*  795 */           var34 = 1;
/*      */         }
/*      */         
/*  798 */         int var25 = MathHelper.ceiling_float_int((var22 + 1) / 10.0F) - 1;
/*  799 */         int var26 = var11 + var22 % 10 * 8;
/*  800 */         int var27 = var13 - var25 * var17;
/*      */         
/*  802 */         if (var3 <= 4)
/*      */         {
/*  804 */           var27 += this.rand.nextInt(2);
/*      */         }
/*      */         
/*  807 */         if (var22 == var21)
/*      */         {
/*  809 */           var27 -= 2;
/*      */         }
/*      */         
/*  812 */         byte var36 = 0;
/*      */         
/*  814 */         if (var2.worldObj.getWorldInfo().isHardcoreModeEnabled())
/*      */         {
/*  816 */           var36 = 5;
/*      */         }
/*      */         
/*  819 */         drawTexturedModalRect(var26, var27, 16 + var34 * 9, 9 * var36, 9, 9);
/*      */         
/*  821 */         if (var4) {
/*      */           
/*  823 */           if (var22 * 2 + 1 < var5)
/*      */           {
/*  825 */             drawTexturedModalRect(var26, var27, var23 + 54, 9 * var36, 9, 9);
/*      */           }
/*      */           
/*  828 */           if (var22 * 2 + 1 == var5)
/*      */           {
/*  830 */             drawTexturedModalRect(var26, var27, var23 + 63, 9 * var36, 9, 9);
/*      */           }
/*      */         } 
/*      */         
/*  834 */         if (var19 > 0.0F) {
/*      */           
/*  836 */           if (var19 == var15 && var15 % 2.0F == 1.0F) {
/*      */             
/*  838 */             drawTexturedModalRect(var26, var27, var23 + 153, 9 * var36, 9, 9);
/*      */           }
/*      */           else {
/*      */             
/*  842 */             drawTexturedModalRect(var26, var27, var23 + 144, 9 * var36, 9, 9);
/*      */           } 
/*      */           
/*  845 */           var19 -= 2.0F;
/*      */         }
/*      */         else {
/*      */           
/*  849 */           if (var22 * 2 + 1 < var3)
/*      */           {
/*  851 */             drawTexturedModalRect(var26, var27, var23 + 36, 9 * var36, 9, 9);
/*      */           }
/*      */           
/*  854 */           if (var22 * 2 + 1 == var3)
/*      */           {
/*  856 */             drawTexturedModalRect(var26, var27, var23 + 45, 9 * var36, 9, 9);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  861 */       Entity var371 = var2.ridingEntity;
/*      */ 
/*      */       
/*  864 */       if (var371 == null) {
/*      */         
/*  866 */         this.mc.mcProfiler.endStartSection("food");
/*      */         
/*  868 */         for (int var23 = 0; var23 < 10; var23++)
/*      */         {
/*  870 */           int var38 = var13;
/*  871 */           int var25 = 16;
/*  872 */           byte var35 = 0;
/*      */           
/*  874 */           if (var2.isPotionActive(Potion.hunger)) {
/*      */             
/*  876 */             var25 += 36;
/*  877 */             var35 = 13;
/*      */           } 
/*      */           
/*  880 */           if (var2.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (var8 * 3 + 1) == 0)
/*      */           {
/*  882 */             var38 = var13 + this.rand.nextInt(3) - 1;
/*      */           }
/*      */           
/*  885 */           if (var6)
/*      */           {
/*  887 */             var35 = 1;
/*      */           }
/*      */           
/*  890 */           int var27 = var12 - var23 * 8 - 9;
/*  891 */           drawTexturedModalRect(var27, var38, 16 + var35 * 9, 27, 9, 9);
/*      */           
/*  893 */           if (var6) {
/*      */             
/*  895 */             if (var23 * 2 + 1 < var9)
/*      */             {
/*  897 */               drawTexturedModalRect(var27, var38, var25 + 54, 27, 9, 9);
/*      */             }
/*      */             
/*  900 */             if (var23 * 2 + 1 == var9)
/*      */             {
/*  902 */               drawTexturedModalRect(var27, var38, var25 + 63, 27, 9, 9);
/*      */             }
/*      */           } 
/*      */           
/*  906 */           if (var23 * 2 + 1 < var8)
/*      */           {
/*  908 */             drawTexturedModalRect(var27, var38, var25 + 36, 27, 9, 9);
/*      */           }
/*      */           
/*  911 */           if (var23 * 2 + 1 == var8)
/*      */           {
/*  913 */             drawTexturedModalRect(var27, var38, var25 + 45, 27, 9, 9);
/*      */           }
/*      */         }
/*      */       
/*  917 */       } else if (var371 instanceof EntityLivingBase) {
/*      */         
/*  919 */         this.mc.mcProfiler.endStartSection("mountHealth");
/*  920 */         EntityLivingBase var391 = (EntityLivingBase)var371;
/*  921 */         int var38 = (int)Math.ceil(var391.getHealth());
/*  922 */         float var37 = var391.getMaxHealth();
/*  923 */         int var26 = (int)(var37 + 0.5F) / 2;
/*      */         
/*  925 */         if (var26 > 30)
/*      */         {
/*  927 */           var26 = 30;
/*      */         }
/*      */         
/*  930 */         int var27 = var13;
/*      */         
/*  932 */         for (int var39 = 0; var26 > 0; var39 += 20) {
/*      */           
/*  934 */           int var29 = Math.min(var26, 10);
/*  935 */           var26 -= var29;
/*      */           
/*  937 */           for (int var30 = 0; var30 < var29; var30++) {
/*      */             
/*  939 */             byte var31 = 52;
/*  940 */             byte var32 = 0;
/*      */             
/*  942 */             if (var6)
/*      */             {
/*  944 */               var32 = 1;
/*      */             }
/*      */             
/*  947 */             int var33 = var12 - var30 * 8 - 9;
/*  948 */             drawTexturedModalRect(var33, var27, var31 + var32 * 9, 9, 9, 9);
/*      */             
/*  950 */             if (var30 * 2 + 1 + var39 < var38)
/*      */             {
/*  952 */               drawTexturedModalRect(var33, var27, var31 + 36, 9, 9, 9);
/*      */             }
/*      */             
/*  955 */             if (var30 * 2 + 1 + var39 == var38)
/*      */             {
/*  957 */               drawTexturedModalRect(var33, var27, var31 + 45, 9, 9, 9);
/*      */             }
/*      */           } 
/*      */           
/*  961 */           var27 -= 10;
/*      */         } 
/*      */       } 
/*      */       
/*  965 */       this.mc.mcProfiler.endStartSection("air");
/*      */       
/*  967 */       if (var2.isInsideOfMaterial(Material.water)) {
/*      */         
/*  969 */         int var23 = this.mc.thePlayer.getAir();
/*  970 */         int var38 = MathHelper.ceiling_double_int((var23 - 2) * 10.0D / 300.0D);
/*  971 */         int var25 = MathHelper.ceiling_double_int(var23 * 10.0D / 300.0D) - var38;
/*      */         
/*  973 */         for (int var26 = 0; var26 < var38 + var25; var26++) {
/*      */           
/*  975 */           if (var26 < var38) {
/*      */             
/*  977 */             drawTexturedModalRect(var12 - var26 * 8 - 9, var18, 16, 18, 9, 9);
/*      */           }
/*      */           else {
/*      */             
/*  981 */             drawTexturedModalRect(var12 - var26 * 8 - 9, var18, 25, 18, 9, 9);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  986 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void renderBossHealth() {
/*  995 */     if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
/*      */       
/*  997 */       BossStatus.statusBarTime--;
/*  998 */       FontRenderer var1 = this.mc.fontRendererObj;
/*  999 */       ScaledResolution var2 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 1000 */       int var3 = var2.getScaledWidth();
/* 1001 */       short var4 = 182;
/* 1002 */       int var5 = var3 / 2 - var4 / 2;
/* 1003 */       int var6 = (int)(BossStatus.healthScale * (var4 + 1));
/* 1004 */       byte var7 = 12;
/* 1005 */       drawTexturedModalRect(var5, var7, 0, 74, var4, 5);
/* 1006 */       drawTexturedModalRect(var5, var7, 0, 74, var4, 5);
/*      */       
/* 1008 */       if (var6 > 0)
/*      */       {
/* 1010 */         drawTexturedModalRect(var5, var7, 0, 79, var6, 5);
/*      */       }
/*      */       
/* 1013 */       String var8 = BossStatus.bossName;
/* 1014 */       int bossTextColor = 16777215;
/*      */       
/* 1016 */       if (Config.isCustomColors())
/*      */       {
/* 1018 */         bossTextColor = CustomColors.getBossTextColor(bossTextColor);
/*      */       }
/*      */       
/* 1021 */       func_175179_f().drawStringWithShadow(var8, (var3 / 2 - func_175179_f().getStringWidth(var8) / 2), (var7 - 10), bossTextColor);
/* 1022 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 1023 */       this.mc.getTextureManager().bindTexture(icons);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180476_e(ScaledResolution p_180476_1_) {
/* 1029 */     GlStateManager.disableDepth();
/* 1030 */     GlStateManager.depthMask(false);
/* 1031 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1032 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 1033 */     GlStateManager.disableAlpha();
/* 1034 */     this.mc.getTextureManager().bindTexture(pumpkinBlurTexPath);
/* 1035 */     Tessellator var2 = Tessellator.getInstance();
/* 1036 */     WorldRenderer var3 = var2.getWorldRenderer();
/* 1037 */     var3.startDrawingQuads();
/* 1038 */     var3.addVertexWithUV(0.0D, p_180476_1_.getScaledHeight(), -90.0D, 0.0D, 1.0D);
/* 1039 */     var3.addVertexWithUV(p_180476_1_.getScaledWidth(), p_180476_1_.getScaledHeight(), -90.0D, 1.0D, 1.0D);
/* 1040 */     var3.addVertexWithUV(p_180476_1_.getScaledWidth(), 0.0D, -90.0D, 1.0D, 0.0D);
/* 1041 */     var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
/* 1042 */     var2.draw();
/* 1043 */     GlStateManager.depthMask(true);
/* 1044 */     GlStateManager.enableDepth();
/* 1045 */     GlStateManager.enableAlpha();
/* 1046 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180480_a(float p_180480_1_, ScaledResolution p_180480_2_) {
/* 1051 */     if (Config.isVignetteEnabled()) {
/*      */       
/* 1053 */       p_180480_1_ = 1.0F - p_180480_1_;
/* 1054 */       p_180480_1_ = MathHelper.clamp_float(p_180480_1_, 0.0F, 1.0F);
/* 1055 */       WorldBorder var3 = this.mc.theWorld.getWorldBorder();
/* 1056 */       float var4 = (float)var3.getClosestDistance((Entity)this.mc.thePlayer);
/* 1057 */       double var5 = Math.min(var3.func_177749_o() * var3.getWarningTime() * 1000.0D, Math.abs(var3.getTargetSize() - var3.getDiameter()));
/* 1058 */       double var7 = Math.max(var3.getWarningDistance(), var5);
/*      */       
/* 1060 */       if (var4 < var7) {
/*      */         
/* 1062 */         var4 = 1.0F - (float)(var4 / var7);
/*      */       }
/*      */       else {
/*      */         
/* 1066 */         var4 = 0.0F;
/*      */       } 
/*      */       
/* 1069 */       this.prevVignetteBrightness = (float)(this.prevVignetteBrightness + (p_180480_1_ - this.prevVignetteBrightness) * 0.01D);
/* 1070 */       GlStateManager.disableDepth();
/* 1071 */       GlStateManager.depthMask(false);
/* 1072 */       GlStateManager.tryBlendFuncSeparate(0, 769, 1, 0);
/*      */       
/* 1074 */       if (var4 > 0.0F) {
/*      */         
/* 1076 */         GlStateManager.color(0.0F, var4, var4, 1.0F);
/*      */       }
/*      */       else {
/*      */         
/* 1080 */         GlStateManager.color(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
/*      */       } 
/*      */       
/* 1083 */       this.mc.getTextureManager().bindTexture(vignetteTexPath);
/* 1084 */       Tessellator var9 = Tessellator.getInstance();
/* 1085 */       WorldRenderer var10 = var9.getWorldRenderer();
/* 1086 */       var10.startDrawingQuads();
/* 1087 */       var10.addVertexWithUV(0.0D, p_180480_2_.getScaledHeight(), -90.0D, 0.0D, 1.0D);
/* 1088 */       var10.addVertexWithUV(p_180480_2_.getScaledWidth(), p_180480_2_.getScaledHeight(), -90.0D, 1.0D, 1.0D);
/* 1089 */       var10.addVertexWithUV(p_180480_2_.getScaledWidth(), 0.0D, -90.0D, 1.0D, 0.0D);
/* 1090 */       var10.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
/* 1091 */       var9.draw();
/* 1092 */       GlStateManager.depthMask(true);
/* 1093 */       GlStateManager.enableDepth();
/* 1094 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 1095 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180474_b(float p_180474_1_, ScaledResolution p_180474_2_) {
/* 1101 */     if (p_180474_1_ < 1.0F) {
/*      */       
/* 1103 */       p_180474_1_ *= p_180474_1_;
/* 1104 */       p_180474_1_ *= p_180474_1_;
/* 1105 */       p_180474_1_ = p_180474_1_ * 0.8F + 0.2F;
/*      */     } 
/*      */     
/* 1108 */     GlStateManager.disableAlpha();
/* 1109 */     GlStateManager.disableDepth();
/* 1110 */     GlStateManager.depthMask(false);
/* 1111 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1112 */     GlStateManager.color(1.0F, 1.0F, 1.0F, p_180474_1_);
/* 1113 */     this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 1114 */     TextureAtlasSprite var3 = this.mc.getBlockRendererDispatcher().func_175023_a().func_178122_a(Blocks.portal.getDefaultState());
/* 1115 */     float var4 = var3.getMinU();
/* 1116 */     float var5 = var3.getMinV();
/* 1117 */     float var6 = var3.getMaxU();
/* 1118 */     float var7 = var3.getMaxV();
/* 1119 */     Tessellator var8 = Tessellator.getInstance();
/* 1120 */     WorldRenderer var9 = var8.getWorldRenderer();
/* 1121 */     var9.startDrawingQuads();
/* 1122 */     var9.addVertexWithUV(0.0D, p_180474_2_.getScaledHeight(), -90.0D, var4, var7);
/* 1123 */     var9.addVertexWithUV(p_180474_2_.getScaledWidth(), p_180474_2_.getScaledHeight(), -90.0D, var6, var7);
/* 1124 */     var9.addVertexWithUV(p_180474_2_.getScaledWidth(), 0.0D, -90.0D, var6, var5);
/* 1125 */     var9.addVertexWithUV(0.0D, 0.0D, -90.0D, var4, var5);
/* 1126 */     var8.draw();
/* 1127 */     GlStateManager.depthMask(true);
/* 1128 */     GlStateManager.enableDepth();
/* 1129 */     GlStateManager.enableAlpha();
/* 1130 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175184_a(int p_175184_1_, int p_175184_2_, int p_175184_3_, float p_175184_4_, EntityPlayer p_175184_5_) {
/* 1135 */     ItemStack var6 = p_175184_5_.inventory.mainInventory[p_175184_1_];
/*      */     
/* 1137 */     if (var6 != null) {
/*      */       
/* 1139 */       float var7 = var6.animationsToGo - p_175184_4_;
/*      */       
/* 1141 */       if (var7 > 0.0F) {
/*      */         
/* 1143 */         GlStateManager.pushMatrix();
/* 1144 */         float var8 = 1.0F + var7 / 5.0F;
/* 1145 */         GlStateManager.translate((p_175184_2_ + 8), (p_175184_3_ + 12), 0.0F);
/* 1146 */         GlStateManager.scale(1.0F / var8, (var8 + 1.0F) / 2.0F, 1.0F);
/* 1147 */         GlStateManager.translate(-(p_175184_2_ + 8), -(p_175184_3_ + 12), 0.0F);
/*      */       } 
/*      */       
/* 1150 */       this.itemRenderer.func_180450_b(var6, p_175184_2_, p_175184_3_);
/*      */       
/* 1152 */       if (var7 > 0.0F)
/*      */       {
/* 1154 */         GlStateManager.popMatrix();
/*      */       }
/*      */       
/* 1157 */       this.itemRenderer.func_175030_a(this.mc.fontRendererObj, var6, p_175184_2_, p_175184_3_);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateTick() {
/* 1166 */     if (this.recordPlayingUpFor > 0)
/*      */     {
/* 1168 */       this.recordPlayingUpFor--;
/*      */     }
/*      */     
/* 1171 */     if (this.field_175195_w > 0) {
/*      */       
/* 1173 */       this.field_175195_w--;
/*      */       
/* 1175 */       if (this.field_175195_w <= 0) {
/*      */         
/* 1177 */         this.field_175201_x = "";
/* 1178 */         this.field_175200_y = "";
/*      */       } 
/*      */     } 
/*      */     
/* 1182 */     this.updateCounter++;
/* 1183 */     this.streamIndicator.func_152439_a();
/*      */     
/* 1185 */     if (this.mc.thePlayer != null) {
/*      */       
/* 1187 */       ItemStack var1 = this.mc.thePlayer.inventory.getCurrentItem();
/*      */       
/* 1189 */       if (var1 == null) {
/*      */         
/* 1191 */         this.remainingHighlightTicks = 0;
/*      */       }
/* 1193 */       else if (this.highlightingItemStack != null && var1.getItem() == this.highlightingItemStack.getItem() && ItemStack.areItemStackTagsEqual(var1, this.highlightingItemStack) && (var1.isItemStackDamageable() || var1.getMetadata() == this.highlightingItemStack.getMetadata())) {
/*      */         
/* 1195 */         if (this.remainingHighlightTicks > 0)
/*      */         {
/* 1197 */           this.remainingHighlightTicks--;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1202 */         this.remainingHighlightTicks = 40;
/*      */       } 
/*      */       
/* 1205 */       this.highlightingItemStack = var1;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRecordPlayingMessage(String p_73833_1_) {
/* 1211 */     setRecordPlaying(I18n.format("record.nowPlaying", new Object[] { p_73833_1_ }), true);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRecordPlaying(String p_110326_1_, boolean p_110326_2_) {
/* 1216 */     this.recordPlaying = p_110326_1_;
/* 1217 */     this.recordPlayingUpFor = 60;
/* 1218 */     this.recordIsPlaying = p_110326_2_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175178_a(String p_175178_1_, String p_175178_2_, int p_175178_3_, int p_175178_4_, int p_175178_5_) {
/* 1223 */     if (p_175178_1_ == null && p_175178_2_ == null && p_175178_3_ < 0 && p_175178_4_ < 0 && p_175178_5_ < 0) {
/*      */       
/* 1225 */       this.field_175201_x = "";
/* 1226 */       this.field_175200_y = "";
/* 1227 */       this.field_175195_w = 0;
/*      */     }
/* 1229 */     else if (p_175178_1_ != null) {
/*      */       
/* 1231 */       this.field_175201_x = p_175178_1_;
/* 1232 */       this.field_175195_w = this.field_175199_z + this.field_175192_A + this.field_175193_B;
/*      */     }
/* 1234 */     else if (p_175178_2_ != null) {
/*      */       
/* 1236 */       this.field_175200_y = p_175178_2_;
/*      */     }
/*      */     else {
/*      */       
/* 1240 */       if (p_175178_3_ >= 0)
/*      */       {
/* 1242 */         this.field_175199_z = p_175178_3_;
/*      */       }
/*      */       
/* 1245 */       if (p_175178_4_ >= 0)
/*      */       {
/* 1247 */         this.field_175192_A = p_175178_4_;
/*      */       }
/*      */       
/* 1250 */       if (p_175178_5_ >= 0)
/*      */       {
/* 1252 */         this.field_175193_B = p_175178_5_;
/*      */       }
/*      */       
/* 1255 */       if (this.field_175195_w > 0)
/*      */       {
/* 1257 */         this.field_175195_w = this.field_175199_z + this.field_175192_A + this.field_175193_B;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175188_a(IChatComponent p_175188_1_, boolean p_175188_2_) {
/* 1264 */     setRecordPlaying(p_175188_1_.getUnformattedText(), p_175188_2_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public GuiNewChat getChatGUI() {
/* 1272 */     return this.persistantChatGUI;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getUpdateCounter() {
/* 1277 */     return this.updateCounter;
/*      */   }
/*      */ 
/*      */   
/*      */   public FontRenderer func_175179_f() {
/* 1282 */     return this.mc.fontRendererObj;
/*      */   }
/*      */ 
/*      */   
/*      */   public GuiSpectator func_175187_g() {
/* 1287 */     return this.field_175197_u;
/*      */   }
/*      */ 
/*      */   
/*      */   public GuiPlayerTabOverlay getTabList() {
/* 1292 */     return this.overlayPlayerList;
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiIngame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */