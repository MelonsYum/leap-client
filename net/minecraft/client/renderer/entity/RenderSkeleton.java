/*    */ package net.minecraft.client.renderer.entity;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelSkeleton;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntitySkeleton;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderSkeleton extends RenderBiped {
/* 15 */   private static final ResourceLocation skeletonTextures = new ResourceLocation("textures/entity/skeleton/skeleton.png");
/* 16 */   private static final ResourceLocation witherSkeletonTextures = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001023";
/*    */   
/*    */   public RenderSkeleton(RenderManager p_i46143_1_) {
/* 21 */     super(p_i46143_1_, (ModelBiped)new ModelSkeleton(), 0.5F);
/* 22 */     addLayer((LayerRenderer)new LayerHeldItem(this));
/* 23 */     addLayer((LayerRenderer)new LayerBipedArmor(this)
/*    */         {
/*    */           private static final String __OBFID = "CL_00002431";
/*    */           
/*    */           protected void func_177177_a() {
/* 28 */             this.field_177189_c = (ModelBase)new ModelSkeleton(0.5F, true);
/* 29 */             this.field_177186_d = (ModelBase)new ModelSkeleton(1.0F, true);
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntitySkeleton p_77041_1_, float p_77041_2_) {
/* 40 */     if (p_77041_1_.getSkeletonType() == 1)
/*    */     {
/* 42 */       GlStateManager.scale(1.2F, 1.2F, 1.2F);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_82422_c() {
/* 48 */     GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ResourceLocation func_180577_a(EntitySkeleton p_180577_1_) {
/* 53 */     return (p_180577_1_.getSkeletonType() == 1) ? witherSkeletonTextures : skeletonTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityLiving p_110775_1_) {
/* 61 */     return func_180577_a((EntitySkeleton)p_110775_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 70 */     preRenderCallback((EntitySkeleton)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 78 */     return func_180577_a((EntitySkeleton)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */