/*     */ package net.minecraft.client.gui.inventory;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import leap.Client;
/*     */ import leap.util.ColorUtil;
/*     */ import leap.util.JColor;
/*     */ import leap.util.Shape;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.achievement.GuiAchievements;
/*     */ import net.minecraft.client.gui.achievement.GuiStats;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.InventoryEffectRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiInventory
/*     */   extends InventoryEffectRenderer
/*     */ {
/*     */   private float oldMouseX;
/*     */   private float oldMouseY;
/*     */   private static final String __OBFID = "CL_00000761";
/*     */   
/*     */   public GuiInventory(EntityPlayer p_i1094_1_) {
/*  34 */     super(p_i1094_1_.inventoryContainer);
/*  35 */     this.allowUserInput = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  43 */     if (this.mc.playerController.isInCreativeMode())
/*     */     {
/*  45 */       this.mc.displayGuiScreen((GuiScreen)new GuiContainerCreative((EntityPlayer)this.mc.thePlayer));
/*     */     }
/*     */     
/*  48 */     func_175378_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  56 */     this.buttonList.clear();
/*     */     
/*  58 */     this.buttonList.add(new GuiButton(54, 1, 1, "Disable KillAura"));
/*  59 */     this.buttonList.add(new GuiButton(69, 1, 24, "Disable ChestSteal"));
/*     */     
/*  61 */     if (this.mc.playerController.isInCreativeMode()) {
/*     */       
/*  63 */       this.mc.displayGuiScreen((GuiScreen)new GuiContainerCreative((EntityPlayer)this.mc.thePlayer));
/*     */     }
/*     */     else {
/*     */       
/*  67 */       super.initGui();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/*  76 */     Client.customFontRenderer.drawString(I18n.format("container.crafting", new Object[0]), 86.0D, 16.0D, new JColor(Color.white.getRGB()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  84 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*  85 */     this.oldMouseX = mouseX;
/*  86 */     this.oldMouseY = mouseY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/*  94 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  95 */     this.mc.getTextureManager().bindTexture(inventoryBackground);
/*  96 */     int var4 = guiLeft;
/*  97 */     int var5 = guiTop;
/*  98 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/*     */     
/* 100 */     drawEntityOnScreen(var4 + 51, var5 + 75, 30, (var4 + 51) - this.oldMouseX, (var5 + 75 - 50) - this.oldMouseY, (EntityLivingBase)this.mc.thePlayer);
/*     */     
/* 102 */     GlStateManager.pushMatrix();
/* 103 */     Shape.color((new Color(10, 10, 0, 100)).getRGB());
/* 104 */     Shape.drawRoundedRect(var4, var5, this.xSize, this.ySize, 4.0F);
/*     */     
/* 106 */     Shape.color(ColorUtil.fade(new Color(200, 200, 0, 10), 1, 1).getRGB());
/* 107 */     Shape.drawRoundedRect(var4, var5, this.xSize, (this.ySize / 30), 1.0F);
/* 108 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawEntityOnScreen(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_) {
/* 117 */     GlStateManager.enableColorMaterial();
/* 118 */     GlStateManager.pushMatrix();
/* 119 */     GlStateManager.translate(p_147046_0_, p_147046_1_, 50.0F);
/* 120 */     GlStateManager.scale(-p_147046_2_, p_147046_2_, p_147046_2_);
/* 121 */     GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
/* 122 */     float var6 = p_147046_5_.renderYawOffset;
/* 123 */     float var7 = p_147046_5_.rotationYaw;
/* 124 */     float var8 = p_147046_5_.rotationPitch;
/* 125 */     float var9 = p_147046_5_.prevRotationYawHead;
/* 126 */     float var10 = p_147046_5_.rotationYawHead;
/* 127 */     GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
/* 128 */     RenderHelper.enableStandardItemLighting();
/* 129 */     GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
/* 130 */     GlStateManager.rotate(-((float)Math.atan((p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
/* 131 */     p_147046_5_.renderYawOffset = (float)Math.atan((p_147046_3_ / 40.0F)) * 20.0F;
/* 132 */     p_147046_5_.rotationYaw = (float)Math.atan((p_147046_3_ / 40.0F)) * 40.0F;
/* 133 */     p_147046_5_.rotationPitch = -((float)Math.atan((p_147046_4_ / 40.0F))) * 20.0F;
/* 134 */     p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
/* 135 */     p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
/* 136 */     GlStateManager.translate(0.0F, 0.0F, 0.0F);
/* 137 */     RenderManager var11 = Minecraft.getMinecraft().getRenderManager();
/* 138 */     var11.func_178631_a(180.0F);
/* 139 */     var11.func_178633_a(false);
/* 140 */     var11.renderEntityWithPosYaw((Entity)p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/* 141 */     var11.func_178633_a(true);
/* 142 */     p_147046_5_.renderYawOffset = var6;
/* 143 */     p_147046_5_.rotationYaw = var7;
/* 144 */     p_147046_5_.rotationPitch = var8;
/* 145 */     p_147046_5_.prevRotationYawHead = var9;
/* 146 */     p_147046_5_.rotationYawHead = var10;
/* 147 */     GlStateManager.popMatrix();
/* 148 */     RenderHelper.disableStandardItemLighting();
/* 149 */     GlStateManager.disableRescaleNormal();
/* 150 */     GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 151 */     GlStateManager.func_179090_x();
/* 152 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 157 */     if (button.id == 0)
/*     */     {
/* 159 */       this.mc.displayGuiScreen((GuiScreen)new GuiAchievements((GuiScreen)this, this.mc.thePlayer.getStatFileWriter()));
/*     */     }
/*     */     
/* 162 */     if (button.id == 1)
/*     */     {
/* 164 */       this.mc.displayGuiScreen((GuiScreen)new GuiStats((GuiScreen)this, this.mc.thePlayer.getStatFileWriter()));
/*     */     }
/*     */     
/* 167 */     if (button.id == 69)
/*     */     {
/* 169 */       (Client.getModule("ChestSteal")).toggled = false;
/*     */     }
/*     */     
/* 172 */     if (button.id == 54)
/*     */     {
/* 174 */       (Client.getModule("KillAura")).toggled = false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */