/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelLeashKnot;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLeashKnot;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderLeashKnot
/*    */   extends Render {
/* 11 */   private static final ResourceLocation leashKnotTextures = new ResourceLocation("textures/entity/lead_knot.png");
/* 12 */   private ModelLeashKnot leashKnotModel = new ModelLeashKnot();
/*    */   
/*    */   private static final String __OBFID = "CL_00001010";
/*    */   
/*    */   public RenderLeashKnot(RenderManager p_i46158_1_) {
/* 17 */     super(p_i46158_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180559_a(EntityLeashKnot p_180559_1_, double p_180559_2_, double p_180559_4_, double p_180559_6_, float p_180559_8_, float p_180559_9_) {
/* 22 */     GlStateManager.pushMatrix();
/* 23 */     GlStateManager.disableCull();
/* 24 */     GlStateManager.translate((float)p_180559_2_, (float)p_180559_4_, (float)p_180559_6_);
/* 25 */     float var10 = 0.0625F;
/* 26 */     GlStateManager.enableRescaleNormal();
/* 27 */     GlStateManager.scale(-1.0F, -1.0F, 1.0F);
/* 28 */     GlStateManager.enableAlpha();
/* 29 */     bindEntityTexture((Entity)p_180559_1_);
/* 30 */     this.leashKnotModel.render((Entity)p_180559_1_, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, var10);
/* 31 */     GlStateManager.popMatrix();
/* 32 */     super.doRender((Entity)p_180559_1_, p_180559_2_, p_180559_4_, p_180559_6_, p_180559_8_, p_180559_9_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityLeashKnot p_110775_1_) {
/* 40 */     return leashKnotTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 48 */     return getEntityTexture((EntityLeashKnot)p_110775_1_);
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
/* 59 */     func_180559_a((EntityLeashKnot)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderLeashKnot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */