/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntitySlime;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderSlime extends RenderLiving {
/* 14 */   private static final ResourceLocation slimeTextures = new ResourceLocation("textures/entity/slime/slime.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001024";
/*    */   
/*    */   public RenderSlime(RenderManager p_i46141_1_, ModelBase p_i46141_2_, float p_i46141_3_) {
/* 19 */     super(p_i46141_1_, p_i46141_2_, p_i46141_3_);
/* 20 */     addLayer((LayerRenderer)new LayerSlimeGel(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(EntitySlime p_177124_1_, double p_177124_2_, double p_177124_4_, double p_177124_6_, float p_177124_8_, float p_177124_9_) {
/* 25 */     this.shadowSize = 0.25F * p_177124_1_.getSlimeSize();
/* 26 */     super.doRender((EntityLiving)p_177124_1_, p_177124_2_, p_177124_4_, p_177124_6_, p_177124_8_, p_177124_9_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntitySlime p_77041_1_, float p_77041_2_) {
/* 35 */     float var3 = p_77041_1_.getSlimeSize();
/* 36 */     float var4 = (p_77041_1_.prevSquishFactor + (p_77041_1_.squishFactor - p_77041_1_.prevSquishFactor) * p_77041_2_) / (var3 * 0.5F + 1.0F);
/* 37 */     float var5 = 1.0F / (var4 + 1.0F);
/* 38 */     GlStateManager.scale(var5 * var3, 1.0F / var5 * var3, var5 * var3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntitySlime p_110775_1_) {
/* 46 */     return slimeTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 57 */     doRender((EntitySlime)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 66 */     preRenderCallback((EntitySlime)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 77 */     doRender((EntitySlime)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 85 */     return getEntityTexture((EntitySlime)p_110775_1_);
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
/* 96 */     doRender((EntitySlime)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */