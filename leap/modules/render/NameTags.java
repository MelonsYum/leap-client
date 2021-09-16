/*     */ package leap.modules.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import leap.util.JColor;
/*     */ import leap.util.Shape;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
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
/*     */ public class NameTags
/*     */   extends Module
/*     */ {
/*     */   public NameTags() {
/*  32 */     super("NameTags", 0, Module.Category.RENDER);
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  36 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  40 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  44 */     if (e instanceof leap.events.listeners.EventRenderNameTag) {
/*  45 */       FontRenderer fr = mc.fontRendererObj;
/*  46 */       ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/*  47 */       for (Object entity : mc.theWorld.playerEntities) {
/*     */         
/*  49 */         EntityPlayer targetrender = (EntityPlayer)entity;
/*     */         
/*  51 */         if (((Entity)entity).isInvisible() || entity == mc.thePlayer) {
/*     */           continue;
/*     */         }
/*  54 */         GL11.glPushMatrix();
/*  55 */         GlStateManager.pushMatrix();
/*     */ 
/*     */         
/*  58 */         mc.getRenderManager(); double x = targetrender.lastTickPosX + (targetrender.posX - targetrender.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/*  59 */         mc.getRenderManager(); double y = targetrender.lastTickPosY + (targetrender.posY - targetrender.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/*  60 */         mc.getRenderManager(); double z = targetrender.lastTickPosZ + (targetrender.posZ - targetrender.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */ 
/*     */         
/*  63 */         GL11.glTranslated(x, y + targetrender.getEyeHeight() + 1.7D, z);
/*  64 */         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/*  65 */         if (mc.gameSettings.thirdPersonView == 2) {
/*  66 */           GlStateManager.rotate(-(mc.getRenderManager()).playerViewY, 0.0F, 1.0F, 0.0F);
/*  67 */           GlStateManager.rotate(-(mc.getRenderManager()).playerViewX, 1.0F, 0.0F, 0.0F);
/*     */         } else {
/*  69 */           GlStateManager.rotate(-mc.thePlayer.rotationYaw, 0.0F, 1.0F, 0.0F);
/*  70 */           GlStateManager.rotate(mc.thePlayer.rotationPitch, 1.0F, 0.0F, 0.0F);
/*     */         } 
/*  72 */         float distance = mc.thePlayer.getDistanceToEntity((Entity)targetrender);
/*  73 */         float scaleConst_1 = 0.02672F, scaleConst_2 = 0.1F;
/*  74 */         double maxDist = 7.0D;
/*     */ 
/*     */         
/*  77 */         float scaleFactor = (float)((distance <= maxDist) ? (maxDist * scaleConst_2) : (distance * scaleConst_2));
/*  78 */         scaleConst_1 *= scaleFactor;
/*     */         
/*  80 */         float scaleBet = 0.2F;
/*  81 */         scaleConst_1 = Math.min(scaleBet, scaleConst_1);
/*     */ 
/*     */         
/*  84 */         GL11.glScalef(-scaleConst_1, -scaleConst_1, 0.2F);
/*     */         
/*  86 */         GlStateManager.disableLighting();
/*  87 */         GlStateManager.depthMask(false);
/*  88 */         GL11.glDisable(2929);
/*     */ 
/*     */         
/*  91 */         String colorCode = (targetrender.getHealth() > 15.0F) ? "§b" : ((targetrender.getHealth() > 10.0F) ? "§e" : ((targetrender.getHealth() > 7.0F) ? "§6" : "§c"));
/*  92 */         int colorrectCode = (targetrender.getHealth() > 15.0F) ? -11667621 : ((targetrender.getHealth() > 10.0F) ? -919731 : ((targetrender.getHealth() > 7.0F) ? -555699 : -568755));
/*  93 */         String thing = String.valueOf(targetrender.getName()) + " " + colorCode + (int)targetrender.getHealth();
/*  94 */         float namewidth = fr.getStringWidth(thing);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 103 */         Shape.color((new Color(60, 60, 60, 255)).getRGB());
/* 104 */         Shape.drawRoundedRect((-namewidth / 2.0F - 15.0F), 20.0D, (namewidth * 2.0F), 15.0D, 1.0F);
/*     */         
/* 106 */         Shape.color((new Color(255, 155, 0)).getRGB());
/* 107 */         Shape.drawRoundedRect((-namewidth / 2.0F - 15.0F), 33.0D, (namewidth * 2.0F), 2.0D, 0.0F);
/*     */ 
/*     */         
/* 110 */         Client.customFontRenderer.drawString("[" + (int)mc.thePlayer.getDistanceToEntity((Entity)targetrender) + "M] " + targetrender.getName(), -30.0D, 23.0D, new JColor(Color.white.getRGB()));
/* 111 */         Client.customFontRenderer.drawString(String.valueOf(colorCode) + (int)targetrender.getHealth(), (namewidth / 2.0F), 23.0D, new JColor(Color.white.getRGB()));
/*     */         
/* 113 */         GlStateManager.disableBlend();
/* 114 */         GlStateManager.depthMask(true);
/* 115 */         GL11.glEnable(2929);
/*     */ 
/*     */ 
/*     */         
/* 119 */         GL11.glPopMatrix();
/* 120 */         GlStateManager.popMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\NameTags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */