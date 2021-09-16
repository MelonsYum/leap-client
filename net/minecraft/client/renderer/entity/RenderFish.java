/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.projectile.EntityFishHook;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class RenderFish
/*     */   extends Render {
/*  15 */   private static final ResourceLocation field_110792_a = new ResourceLocation("textures/particle/particles.png");
/*     */   
/*     */   private static final String __OBFID = "CL_00000996";
/*     */   
/*     */   public RenderFish(RenderManager p_i46175_1_) {
/*  20 */     super(p_i46175_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180558_a(EntityFishHook p_180558_1_, double p_180558_2_, double p_180558_4_, double p_180558_6_, float p_180558_8_, float p_180558_9_) {
/*  25 */     GlStateManager.pushMatrix();
/*  26 */     GlStateManager.translate((float)p_180558_2_, (float)p_180558_4_, (float)p_180558_6_);
/*  27 */     GlStateManager.enableRescaleNormal();
/*  28 */     GlStateManager.scale(0.5F, 0.5F, 0.5F);
/*  29 */     bindEntityTexture((Entity)p_180558_1_);
/*  30 */     Tessellator var10 = Tessellator.getInstance();
/*  31 */     WorldRenderer var11 = var10.getWorldRenderer();
/*  32 */     byte var12 = 1;
/*  33 */     byte var13 = 2;
/*  34 */     float var14 = (var12 * 8 + 0) / 128.0F;
/*  35 */     float var15 = (var12 * 8 + 8) / 128.0F;
/*  36 */     float var16 = (var13 * 8 + 0) / 128.0F;
/*  37 */     float var17 = (var13 * 8 + 8) / 128.0F;
/*  38 */     float var18 = 1.0F;
/*  39 */     float var19 = 0.5F;
/*  40 */     float var20 = 0.5F;
/*  41 */     GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/*  42 */     GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/*  43 */     var11.startDrawingQuads();
/*  44 */     var11.func_178980_d(0.0F, 1.0F, 0.0F);
/*  45 */     var11.addVertexWithUV((0.0F - var19), (0.0F - var20), 0.0D, var14, var17);
/*  46 */     var11.addVertexWithUV((var18 - var19), (0.0F - var20), 0.0D, var15, var17);
/*  47 */     var11.addVertexWithUV((var18 - var19), (1.0F - var20), 0.0D, var15, var16);
/*  48 */     var11.addVertexWithUV((0.0F - var19), (1.0F - var20), 0.0D, var14, var16);
/*  49 */     var10.draw();
/*  50 */     GlStateManager.disableRescaleNormal();
/*  51 */     GlStateManager.popMatrix();
/*     */     
/*  53 */     if (p_180558_1_.angler != null) {
/*     */       
/*  55 */       float var21 = p_180558_1_.angler.getSwingProgress(p_180558_9_);
/*  56 */       float var22 = MathHelper.sin(MathHelper.sqrt_float(var21) * 3.1415927F);
/*  57 */       Vec3 var23 = new Vec3(-0.36D, 0.03D, 0.35D);
/*  58 */       var23 = var23.rotatePitch(-(p_180558_1_.angler.prevRotationPitch + (p_180558_1_.angler.rotationPitch - p_180558_1_.angler.prevRotationPitch) * p_180558_9_) * 3.1415927F / 180.0F);
/*  59 */       var23 = var23.rotateYaw(-(p_180558_1_.angler.prevRotationYaw + (p_180558_1_.angler.rotationYaw - p_180558_1_.angler.prevRotationYaw) * p_180558_9_) * 3.1415927F / 180.0F);
/*  60 */       var23 = var23.rotateYaw(var22 * 0.5F);
/*  61 */       var23 = var23.rotatePitch(-var22 * 0.7F);
/*  62 */       double var24 = p_180558_1_.angler.prevPosX + (p_180558_1_.angler.posX - p_180558_1_.angler.prevPosX) * p_180558_9_ + var23.xCoord;
/*  63 */       double var26 = p_180558_1_.angler.prevPosY + (p_180558_1_.angler.posY - p_180558_1_.angler.prevPosY) * p_180558_9_ + var23.yCoord;
/*  64 */       double var28 = p_180558_1_.angler.prevPosZ + (p_180558_1_.angler.posZ - p_180558_1_.angler.prevPosZ) * p_180558_9_ + var23.zCoord;
/*  65 */       double var30 = p_180558_1_.angler.getEyeHeight();
/*     */       
/*  67 */       if ((this.renderManager.options != null && this.renderManager.options.thirdPersonView > 0) || p_180558_1_.angler != (Minecraft.getMinecraft()).thePlayer) {
/*     */         
/*  69 */         float var32 = (p_180558_1_.angler.prevRenderYawOffset + (p_180558_1_.angler.renderYawOffset - p_180558_1_.angler.prevRenderYawOffset) * p_180558_9_) * 3.1415927F / 180.0F;
/*  70 */         double var33 = MathHelper.sin(var32);
/*  71 */         double var35 = MathHelper.cos(var32);
/*  72 */         double var37 = 0.35D;
/*  73 */         double var39 = 0.8D;
/*  74 */         var24 = p_180558_1_.angler.prevPosX + (p_180558_1_.angler.posX - p_180558_1_.angler.prevPosX) * p_180558_9_ - var35 * 0.35D - var33 * 0.8D;
/*  75 */         var26 = p_180558_1_.angler.prevPosY + var30 + (p_180558_1_.angler.posY - p_180558_1_.angler.prevPosY) * p_180558_9_ - 0.45D;
/*  76 */         var28 = p_180558_1_.angler.prevPosZ + (p_180558_1_.angler.posZ - p_180558_1_.angler.prevPosZ) * p_180558_9_ - var33 * 0.35D + var35 * 0.8D;
/*  77 */         var30 = p_180558_1_.angler.isSneaking() ? -0.1875D : 0.0D;
/*     */       } 
/*     */       
/*  80 */       double var47 = p_180558_1_.prevPosX + (p_180558_1_.posX - p_180558_1_.prevPosX) * p_180558_9_;
/*  81 */       double var34 = p_180558_1_.prevPosY + (p_180558_1_.posY - p_180558_1_.prevPosY) * p_180558_9_ + 0.25D;
/*  82 */       double var36 = p_180558_1_.prevPosZ + (p_180558_1_.posZ - p_180558_1_.prevPosZ) * p_180558_9_;
/*  83 */       double var38 = (float)(var24 - var47);
/*  84 */       double var40 = (float)(var26 - var34) + var30;
/*  85 */       double var42 = (float)(var28 - var36);
/*  86 */       GlStateManager.func_179090_x();
/*  87 */       GlStateManager.disableLighting();
/*  88 */       var11.startDrawing(3);
/*  89 */       var11.func_178991_c(0);
/*  90 */       byte var44 = 16;
/*     */       
/*  92 */       for (int var45 = 0; var45 <= var44; var45++) {
/*     */         
/*  94 */         float var46 = var45 / var44;
/*  95 */         var11.addVertex(p_180558_2_ + var38 * var46, p_180558_4_ + var40 * (var46 * var46 + var46) * 0.5D + 0.25D, p_180558_6_ + var42 * var46);
/*     */       } 
/*     */       
/*  98 */       var10.draw();
/*  99 */       GlStateManager.enableLighting();
/* 100 */       GlStateManager.func_179098_w();
/* 101 */       super.doRender((Entity)p_180558_1_, p_180558_2_, p_180558_4_, p_180558_6_, p_180558_8_, p_180558_9_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityFishHook p_110775_1_) {
/* 110 */     return field_110792_a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 118 */     return getEntityTexture((EntityFishHook)p_110775_1_);
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
/* 129 */     func_180558_a((EntityFishHook)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */