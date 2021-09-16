/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelMagmaCube;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityMagmaCube;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderMagmaCube extends RenderLiving {
/* 12 */   private static final ResourceLocation magmaCubeTextures = new ResourceLocation("textures/entity/slime/magmacube.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001009";
/*    */   
/*    */   public RenderMagmaCube(RenderManager p_i46159_1_) {
/* 17 */     super(p_i46159_1_, (ModelBase)new ModelMagmaCube(), 0.25F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityMagmaCube p_110775_1_) {
/* 25 */     return magmaCubeTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityMagmaCube p_77041_1_, float p_77041_2_) {
/* 34 */     int var3 = p_77041_1_.getSlimeSize();
/* 35 */     float var4 = (p_77041_1_.prevSquishFactor + (p_77041_1_.squishFactor - p_77041_1_.prevSquishFactor) * p_77041_2_) / (var3 * 0.5F + 1.0F);
/* 36 */     float var5 = 1.0F / (var4 + 1.0F);
/* 37 */     float var6 = var3;
/* 38 */     GlStateManager.scale(var5 * var6, 1.0F / var5 * var6, var5 * var6);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 47 */     preRenderCallback((EntityMagmaCube)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 55 */     return getEntityTexture((EntityMagmaCube)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderMagmaCube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */