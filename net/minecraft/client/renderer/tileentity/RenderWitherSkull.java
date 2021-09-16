/*    */ package net.minecraft.client.renderer.tileentity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelSkeletonHead;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.Render;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.projectile.EntityWitherSkull;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderWitherSkull
/*    */   extends Render {
/* 13 */   private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
/* 14 */   private static final ResourceLocation witherTextures = new ResourceLocation("textures/entity/wither/wither.png");
/*    */ 
/*    */   
/* 17 */   private final ModelSkeletonHead skeletonHeadModel = new ModelSkeletonHead();
/*    */   
/*    */   private static final String __OBFID = "CL_00001035";
/*    */   
/*    */   public RenderWitherSkull(RenderManager p_i46129_1_) {
/* 22 */     super(p_i46129_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private float func_82400_a(float p_82400_1_, float p_82400_2_, float p_82400_3_) {
/*    */     float var4;
/* 29 */     for (var4 = p_82400_2_ - p_82400_1_; var4 < -180.0F; var4 += 360.0F);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 34 */     while (var4 >= 180.0F)
/*    */     {
/* 36 */       var4 -= 360.0F;
/*    */     }
/*    */     
/* 39 */     return p_82400_1_ + p_82400_3_ * var4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(EntityWitherSkull p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 50 */     GlStateManager.pushMatrix();
/* 51 */     GlStateManager.disableCull();
/* 52 */     float var10 = func_82400_a(p_76986_1_.prevRotationYaw, p_76986_1_.rotationYaw, p_76986_9_);
/* 53 */     float var11 = p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_;
/* 54 */     GlStateManager.translate((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
/* 55 */     float var12 = 0.0625F;
/* 56 */     GlStateManager.enableRescaleNormal();
/* 57 */     GlStateManager.scale(-1.0F, -1.0F, 1.0F);
/* 58 */     GlStateManager.enableAlpha();
/* 59 */     bindEntityTexture((Entity)p_76986_1_);
/* 60 */     this.skeletonHeadModel.render((Entity)p_76986_1_, 0.0F, 0.0F, 0.0F, var10, var11, var12);
/* 61 */     GlStateManager.popMatrix();
/* 62 */     super.doRender((Entity)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180564_a(EntityWitherSkull p_180564_1_) {
/* 67 */     return p_180564_1_.isInvulnerable() ? invulnerableWitherTextures : witherTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 75 */     return func_180564_a((EntityWitherSkull)p_110775_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 86 */     doRender((EntityWitherSkull)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\tileentity\RenderWitherSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */