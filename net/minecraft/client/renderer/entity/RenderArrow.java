/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderArrow
/*     */   extends Render {
/*  14 */   private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");
/*     */   
/*     */   private static final String __OBFID = "CL_00000978";
/*     */   
/*     */   public RenderArrow(RenderManager p_i46193_1_) {
/*  19 */     super(p_i46193_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void doRender(EntityArrow p_180551_1_, double p_180551_2_, double p_180551_4_, double p_180551_6_, float p_180551_8_, float p_180551_9_) {
/*  24 */     bindEntityTexture((Entity)p_180551_1_);
/*  25 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  26 */     GlStateManager.pushMatrix();
/*  27 */     GlStateManager.translate((float)p_180551_2_, (float)p_180551_4_, (float)p_180551_6_);
/*  28 */     GlStateManager.rotate(p_180551_1_.prevRotationYaw + (p_180551_1_.rotationYaw - p_180551_1_.prevRotationYaw) * p_180551_9_ - 90.0F, 0.0F, 1.0F, 0.0F);
/*  29 */     GlStateManager.rotate(p_180551_1_.prevRotationPitch + (p_180551_1_.rotationPitch - p_180551_1_.prevRotationPitch) * p_180551_9_, 0.0F, 0.0F, 1.0F);
/*  30 */     Tessellator var10 = Tessellator.getInstance();
/*  31 */     WorldRenderer var11 = var10.getWorldRenderer();
/*  32 */     byte var12 = 0;
/*  33 */     float var13 = 0.0F;
/*  34 */     float var14 = 0.5F;
/*  35 */     float var15 = (0 + var12 * 10) / 32.0F;
/*  36 */     float var16 = (5 + var12 * 10) / 32.0F;
/*  37 */     float var17 = 0.0F;
/*  38 */     float var18 = 0.15625F;
/*  39 */     float var19 = (5 + var12 * 10) / 32.0F;
/*  40 */     float var20 = (10 + var12 * 10) / 32.0F;
/*  41 */     float var21 = 0.05625F;
/*  42 */     GlStateManager.enableRescaleNormal();
/*  43 */     float var22 = p_180551_1_.arrowShake - p_180551_9_;
/*     */     
/*  45 */     if (var22 > 0.0F) {
/*     */       
/*  47 */       float var23 = -MathHelper.sin(var22 * 3.0F) * var22;
/*  48 */       GlStateManager.rotate(var23, 0.0F, 0.0F, 1.0F);
/*     */     } 
/*     */     
/*  51 */     GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
/*  52 */     GlStateManager.scale(var21, var21, var21);
/*  53 */     GlStateManager.translate(-4.0F, 0.0F, 0.0F);
/*  54 */     GL11.glNormal3f(var21, 0.0F, 0.0F);
/*  55 */     var11.startDrawingQuads();
/*  56 */     var11.addVertexWithUV(-7.0D, -2.0D, -2.0D, var17, var19);
/*  57 */     var11.addVertexWithUV(-7.0D, -2.0D, 2.0D, var18, var19);
/*  58 */     var11.addVertexWithUV(-7.0D, 2.0D, 2.0D, var18, var20);
/*  59 */     var11.addVertexWithUV(-7.0D, 2.0D, -2.0D, var17, var20);
/*  60 */     var10.draw();
/*  61 */     GL11.glNormal3f(-var21, 0.0F, 0.0F);
/*  62 */     var11.startDrawingQuads();
/*  63 */     var11.addVertexWithUV(-7.0D, 2.0D, -2.0D, var17, var19);
/*  64 */     var11.addVertexWithUV(-7.0D, 2.0D, 2.0D, var18, var19);
/*  65 */     var11.addVertexWithUV(-7.0D, -2.0D, 2.0D, var18, var20);
/*  66 */     var11.addVertexWithUV(-7.0D, -2.0D, -2.0D, var17, var20);
/*  67 */     var10.draw();
/*     */     
/*  69 */     for (int var24 = 0; var24 < 4; var24++) {
/*     */       
/*  71 */       GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
/*  72 */       GL11.glNormal3f(0.0F, 0.0F, var21);
/*  73 */       var11.startDrawingQuads();
/*  74 */       var11.addVertexWithUV(-8.0D, -2.0D, 0.0D, var13, var15);
/*  75 */       var11.addVertexWithUV(8.0D, -2.0D, 0.0D, var14, var15);
/*  76 */       var11.addVertexWithUV(8.0D, 2.0D, 0.0D, var14, var16);
/*  77 */       var11.addVertexWithUV(-8.0D, 2.0D, 0.0D, var13, var16);
/*  78 */       var10.draw();
/*     */     } 
/*     */     
/*  81 */     GlStateManager.disableRescaleNormal();
/*  82 */     GlStateManager.popMatrix();
/*  83 */     super.doRender((Entity)p_180551_1_, p_180551_2_, p_180551_4_, p_180551_6_, p_180551_8_, p_180551_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityArrow p_180550_1_) {
/*  88 */     return arrowTextures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/*  96 */     return getEntityTexture((EntityArrow)p_110775_1_);
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
/* 107 */     doRender((EntityArrow)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */