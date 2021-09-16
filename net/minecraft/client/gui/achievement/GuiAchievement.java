/*     */ package net.minecraft.client.gui.achievement;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.Gui;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.stats.Achievement;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class GuiAchievement
/*     */   extends Gui {
/*  15 */   private static final ResourceLocation achievementBg = new ResourceLocation("textures/gui/achievement/achievement_background.png");
/*     */   
/*     */   private Minecraft mc;
/*     */   private int width;
/*     */   private int height;
/*     */   private String achievementTitle;
/*     */   private String achievementDescription;
/*     */   private Achievement theAchievement;
/*     */   private long notificationTime;
/*     */   private RenderItem renderItem;
/*     */   private boolean permanentNotification;
/*     */   private static final String __OBFID = "CL_00000721";
/*     */   
/*     */   public GuiAchievement(Minecraft mc) {
/*  29 */     this.mc = mc;
/*  30 */     this.renderItem = mc.getRenderItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public void displayAchievement(Achievement p_146256_1_) {
/*  35 */     this.achievementTitle = I18n.format("achievement.get", new Object[0]);
/*  36 */     this.achievementDescription = p_146256_1_.getStatName().getUnformattedText();
/*  37 */     this.notificationTime = Minecraft.getSystemTime();
/*  38 */     this.theAchievement = p_146256_1_;
/*  39 */     this.permanentNotification = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void displayUnformattedAchievement(Achievement p_146255_1_) {
/*  44 */     this.achievementTitle = p_146255_1_.getStatName().getUnformattedText();
/*  45 */     this.achievementDescription = p_146255_1_.getDescription();
/*  46 */     this.notificationTime = Minecraft.getSystemTime() + 2500L;
/*  47 */     this.theAchievement = p_146255_1_;
/*  48 */     this.permanentNotification = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateAchievementWindowScale() {
/*  53 */     GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/*  54 */     GlStateManager.matrixMode(5889);
/*  55 */     GlStateManager.loadIdentity();
/*  56 */     GlStateManager.matrixMode(5888);
/*  57 */     GlStateManager.loadIdentity();
/*  58 */     this.width = this.mc.displayWidth;
/*  59 */     this.height = this.mc.displayHeight;
/*  60 */     ScaledResolution var1 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/*  61 */     this.width = var1.getScaledWidth();
/*  62 */     this.height = var1.getScaledHeight();
/*  63 */     GlStateManager.clear(256);
/*  64 */     GlStateManager.matrixMode(5889);
/*  65 */     GlStateManager.loadIdentity();
/*  66 */     GlStateManager.ortho(0.0D, this.width, this.height, 0.0D, 1000.0D, 3000.0D);
/*  67 */     GlStateManager.matrixMode(5888);
/*  68 */     GlStateManager.loadIdentity();
/*  69 */     GlStateManager.translate(0.0F, 0.0F, -2000.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAchievementWindow() {
/*  74 */     if (this.theAchievement != null && this.notificationTime != 0L && (Minecraft.getMinecraft()).thePlayer != null) {
/*     */       
/*  76 */       double var1 = (Minecraft.getSystemTime() - this.notificationTime) / 3000.0D;
/*     */       
/*  78 */       if (!this.permanentNotification) {
/*     */         
/*  80 */         if (var1 < 0.0D || var1 > 1.0D) {
/*     */           
/*  82 */           this.notificationTime = 0L;
/*     */           
/*     */           return;
/*     */         } 
/*  86 */       } else if (var1 > 0.5D) {
/*     */         
/*  88 */         var1 = 0.5D;
/*     */       } 
/*     */       
/*  91 */       updateAchievementWindowScale();
/*  92 */       GlStateManager.disableDepth();
/*  93 */       GlStateManager.depthMask(false);
/*  94 */       double var3 = var1 * 2.0D;
/*     */       
/*  96 */       if (var3 > 1.0D)
/*     */       {
/*  98 */         var3 = 2.0D - var3;
/*     */       }
/*     */       
/* 101 */       var3 *= 4.0D;
/* 102 */       var3 = 1.0D - var3;
/*     */       
/* 104 */       if (var3 < 0.0D)
/*     */       {
/* 106 */         var3 = 0.0D;
/*     */       }
/*     */       
/* 109 */       var3 *= var3;
/* 110 */       var3 *= var3;
/* 111 */       int var5 = this.width - 160;
/* 112 */       int var6 = 0 - (int)(var3 * 36.0D);
/* 113 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 114 */       GlStateManager.func_179098_w();
/* 115 */       this.mc.getTextureManager().bindTexture(achievementBg);
/* 116 */       GlStateManager.disableLighting();
/* 117 */       drawTexturedModalRect(var5, var6, 96, 202, 160, 32);
/*     */       
/* 119 */       if (this.permanentNotification) {
/*     */         
/* 121 */         this.mc.fontRendererObj.drawSplitString(this.achievementDescription, var5 + 30, var6 + 7, 120, -1);
/*     */       }
/*     */       else {
/*     */         
/* 125 */         this.mc.fontRendererObj.drawString(this.achievementTitle, (var5 + 30), (var6 + 7), -256);
/* 126 */         this.mc.fontRendererObj.drawString(this.achievementDescription, (var5 + 30), (var6 + 18), -1);
/*     */       } 
/*     */       
/* 129 */       RenderHelper.enableGUIStandardItemLighting();
/* 130 */       GlStateManager.disableLighting();
/* 131 */       GlStateManager.enableRescaleNormal();
/* 132 */       GlStateManager.enableColorMaterial();
/* 133 */       GlStateManager.enableLighting();
/* 134 */       this.renderItem.func_180450_b(this.theAchievement.theItemStack, var5 + 8, var6 + 8);
/* 135 */       GlStateManager.disableLighting();
/* 136 */       GlStateManager.depthMask(true);
/* 137 */       GlStateManager.enableDepth();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearAchievements() {
/* 143 */     this.theAchievement = null;
/* 144 */     this.notificationTime = 0L;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\achievement\GuiAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */