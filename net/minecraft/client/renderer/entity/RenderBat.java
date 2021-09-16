/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelBat;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityBat;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderBat extends RenderLiving {
/* 13 */   private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00000979";
/*    */   
/*    */   public RenderBat(RenderManager p_i46192_1_) {
/* 18 */     super(p_i46192_1_, (ModelBase)new ModelBat(), 0.25F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180566_a(EntityBat p_180566_1_) {
/* 23 */     return batTextures;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_180567_a(EntityBat p_180567_1_, float p_180567_2_) {
/* 28 */     GlStateManager.scale(0.35F, 0.35F, 0.35F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void rotateCorpse(EntityBat p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
/* 33 */     if (!p_77043_1_.getIsBatHanging()) {
/*    */       
/* 35 */       GlStateManager.translate(0.0F, MathHelper.cos(p_77043_2_ * 0.3F) * 0.1F, 0.0F);
/*    */     }
/*    */     else {
/*    */       
/* 39 */       GlStateManager.translate(0.0F, -0.1F, 0.0F);
/*    */     } 
/*    */     
/* 42 */     super.rotateCorpse((EntityLivingBase)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 51 */     func_180567_a((EntityBat)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
/* 56 */     rotateCorpse((EntityBat)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 64 */     return func_180566_a((EntityBat)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */