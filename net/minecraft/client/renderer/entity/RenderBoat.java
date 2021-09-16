/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelBoat;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityBoat;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderBoat
/*    */   extends Render {
/* 13 */   private static final ResourceLocation boatTextures = new ResourceLocation("textures/entity/boat.png");
/*    */ 
/*    */   
/* 16 */   protected ModelBase modelBoat = (ModelBase)new ModelBoat();
/*    */   
/*    */   private static final String __OBFID = "CL_00000981";
/*    */   
/*    */   public RenderBoat(RenderManager p_i46190_1_) {
/* 21 */     super(p_i46190_1_);
/* 22 */     this.shadowSize = 0.5F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180552_a(EntityBoat p_180552_1_, double p_180552_2_, double p_180552_4_, double p_180552_6_, float p_180552_8_, float p_180552_9_) {
/* 27 */     GlStateManager.pushMatrix();
/* 28 */     GlStateManager.translate((float)p_180552_2_, (float)p_180552_4_ + 0.25F, (float)p_180552_6_);
/* 29 */     GlStateManager.rotate(180.0F - p_180552_8_, 0.0F, 1.0F, 0.0F);
/* 30 */     float var10 = p_180552_1_.getTimeSinceHit() - p_180552_9_;
/* 31 */     float var11 = p_180552_1_.getDamageTaken() - p_180552_9_;
/*    */     
/* 33 */     if (var11 < 0.0F)
/*    */     {
/* 35 */       var11 = 0.0F;
/*    */     }
/*    */     
/* 38 */     if (var10 > 0.0F)
/*    */     {
/* 40 */       GlStateManager.rotate(MathHelper.sin(var10) * var10 * var11 / 10.0F * p_180552_1_.getForwardDirection(), 1.0F, 0.0F, 0.0F);
/*    */     }
/*    */     
/* 43 */     float var12 = 0.75F;
/* 44 */     GlStateManager.scale(var12, var12, var12);
/* 45 */     GlStateManager.scale(1.0F / var12, 1.0F / var12, 1.0F / var12);
/* 46 */     bindEntityTexture((Entity)p_180552_1_);
/* 47 */     GlStateManager.scale(-1.0F, -1.0F, 1.0F);
/* 48 */     this.modelBoat.render((Entity)p_180552_1_, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 49 */     GlStateManager.popMatrix();
/* 50 */     super.doRender((Entity)p_180552_1_, p_180552_2_, p_180552_4_, p_180552_6_, p_180552_8_, p_180552_9_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180553_a(EntityBoat p_180553_1_) {
/* 55 */     return boatTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 63 */     return func_180553_a((EntityBoat)p_110775_1_);
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
/* 74 */     func_180552_a((EntityBoat)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */