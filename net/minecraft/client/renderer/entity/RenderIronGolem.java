/*    */ package net.minecraft.client.renderer.entity;
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelIronGolem;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerIronGolemFlower;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntityIronGolem;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderIronGolem extends RenderLiving {
/* 13 */   private static final ResourceLocation ironGolemTextures = new ResourceLocation("textures/entity/iron_golem.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001031";
/*    */   
/*    */   public RenderIronGolem(RenderManager p_i46133_1_) {
/* 18 */     super(p_i46133_1_, (ModelBase)new ModelIronGolem(), 0.5F);
/* 19 */     addLayer((LayerRenderer)new LayerIronGolemFlower(this));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityIronGolem p_110775_1_) {
/* 27 */     return ironGolemTextures;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void func_180588_a(EntityIronGolem p_180588_1_, float p_180588_2_, float p_180588_3_, float p_180588_4_) {
/* 32 */     super.rotateCorpse((EntityLivingBase)p_180588_1_, p_180588_2_, p_180588_3_, p_180588_4_);
/*    */     
/* 34 */     if (p_180588_1_.limbSwingAmount >= 0.01D) {
/*    */       
/* 36 */       float var5 = 13.0F;
/* 37 */       float var6 = p_180588_1_.limbSwing - p_180588_1_.limbSwingAmount * (1.0F - p_180588_4_) + 6.0F;
/* 38 */       float var7 = (Math.abs(var6 % var5 - var5 * 0.5F) - var5 * 0.25F) / var5 * 0.25F;
/* 39 */       GlStateManager.rotate(6.5F * var7, 0.0F, 0.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
/* 45 */     func_180588_a((EntityIronGolem)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 53 */     return getEntityTexture((EntityIronGolem)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderIronGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */