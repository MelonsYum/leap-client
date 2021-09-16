/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import optifine.Config;
/*     */ import optifine.CustomColors;
/*     */ 
/*     */ public class RenderXPOrb
/*     */   extends Render {
/*  16 */   private static final ResourceLocation experienceOrbTextures = new ResourceLocation("textures/entity/experience_orb.png");
/*     */   
/*     */   private static final String __OBFID = "CL_00000993";
/*     */   
/*     */   public RenderXPOrb(RenderManager p_i46178_1_) {
/*  21 */     super(p_i46178_1_);
/*  22 */     this.shadowSize = 0.15F;
/*  23 */     this.shadowOpaque = 0.75F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityXPOrb p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/*  34 */     GlStateManager.pushMatrix();
/*  35 */     GlStateManager.translate((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
/*  36 */     bindEntityTexture((Entity)p_76986_1_);
/*  37 */     int var10 = p_76986_1_.getTextureByXP();
/*  38 */     float var11 = (var10 % 4 * 16 + 0) / 64.0F;
/*  39 */     float var12 = (var10 % 4 * 16 + 16) / 64.0F;
/*  40 */     float var13 = (var10 / 4 * 16 + 0) / 64.0F;
/*  41 */     float var14 = (var10 / 4 * 16 + 16) / 64.0F;
/*  42 */     float var15 = 1.0F;
/*  43 */     float var16 = 0.5F;
/*  44 */     float var17 = 0.25F;
/*  45 */     int var18 = p_76986_1_.getBrightnessForRender(p_76986_9_);
/*  46 */     int var19 = var18 % 65536;
/*  47 */     int var20 = var18 / 65536;
/*  48 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var19 / 1.0F, var20 / 1.0F);
/*  49 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  50 */     float var27 = 255.0F;
/*  51 */     float var28 = (p_76986_1_.xpColor + p_76986_9_) / 2.0F;
/*  52 */     var20 = (int)((MathHelper.sin(var28 + 0.0F) + 1.0F) * 0.5F * var27);
/*  53 */     int var21 = (int)var27;
/*  54 */     int var22 = (int)((MathHelper.sin(var28 + 4.1887903F) + 1.0F) * 0.1F * var27);
/*  55 */     int var23 = var20 << 16 | var21 << 8 | var22;
/*  56 */     GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  57 */     GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  58 */     float var24 = 0.3F;
/*  59 */     GlStateManager.scale(var24, var24, var24);
/*  60 */     Tessellator var25 = Tessellator.getInstance();
/*  61 */     WorldRenderer var26 = var25.getWorldRenderer();
/*  62 */     var26.startDrawingQuads();
/*     */     
/*  64 */     if (Config.isCustomColors()) {
/*     */       
/*  66 */       int col = CustomColors.getXpOrbColor(var27);
/*     */       
/*  68 */       if (col >= 0)
/*     */       {
/*  70 */         var23 = col;
/*     */       }
/*     */     } 
/*     */     
/*  74 */     var26.func_178974_a(var23, 128);
/*  75 */     var26.func_178980_d(0.0F, 1.0F, 0.0F);
/*  76 */     var26.addVertexWithUV((0.0F - var16), (0.0F - var17), 0.0D, var11, var14);
/*  77 */     var26.addVertexWithUV((var15 - var16), (0.0F - var17), 0.0D, var12, var14);
/*  78 */     var26.addVertexWithUV((var15 - var16), (1.0F - var17), 0.0D, var12, var13);
/*  79 */     var26.addVertexWithUV((0.0F - var16), (1.0F - var17), 0.0D, var11, var13);
/*  80 */     var25.draw();
/*  81 */     GlStateManager.disableBlend();
/*  82 */     GlStateManager.disableRescaleNormal();
/*  83 */     GlStateManager.popMatrix();
/*  84 */     super.doRender((Entity)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getTexture(EntityXPOrb p_180555_1_) {
/*  89 */     return experienceOrbTextures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/*  97 */     return getTexture((EntityXPOrb)p_110775_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 108 */     doRender((EntityXPOrb)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderXPOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */