/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelGhast;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityGhast;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderGhast extends RenderLiving {
/* 12 */   private static final ResourceLocation ghastTextures = new ResourceLocation("textures/entity/ghast/ghast.png");
/* 13 */   private static final ResourceLocation ghastShootingTextures = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00000997";
/*    */   
/*    */   public RenderGhast(RenderManager p_i46174_1_) {
/* 18 */     super(p_i46174_1_, (ModelBase)new ModelGhast(), 0.5F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180576_a(EntityGhast p_180576_1_) {
/* 23 */     return p_180576_1_.func_110182_bF() ? ghastShootingTextures : ghastTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityGhast p_77041_1_, float p_77041_2_) {
/* 32 */     float var3 = 1.0F;
/* 33 */     float var4 = (8.0F + var3) / 2.0F;
/* 34 */     float var5 = (8.0F + 1.0F / var3) / 2.0F;
/* 35 */     GlStateManager.scale(var5, var4, var5);
/* 36 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 45 */     preRenderCallback((EntityGhast)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 53 */     return func_180576_a((EntityGhast)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderGhast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */