/*    */ package net.minecraft.client.renderer.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntityOcelot;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class RenderOcelot
/*    */   extends RenderLiving {
/* 12 */   private static final ResourceLocation blackOcelotTextures = new ResourceLocation("textures/entity/cat/black.png");
/* 13 */   private static final ResourceLocation ocelotTextures = new ResourceLocation("textures/entity/cat/ocelot.png");
/* 14 */   private static final ResourceLocation redOcelotTextures = new ResourceLocation("textures/entity/cat/red.png");
/* 15 */   private static final ResourceLocation siameseOcelotTextures = new ResourceLocation("textures/entity/cat/siamese.png");
/*    */   
/*    */   private static final String __OBFID = "CL_00001017";
/*    */   
/*    */   public RenderOcelot(RenderManager p_i46151_1_, ModelBase p_i46151_2_, float p_i46151_3_) {
/* 20 */     super(p_i46151_1_, p_i46151_2_, p_i46151_3_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(EntityOcelot p_110775_1_) {
/* 28 */     switch (p_110775_1_.getTameSkin()) {
/*    */ 
/*    */       
/*    */       default:
/* 32 */         return ocelotTextures;
/*    */       
/*    */       case 1:
/* 35 */         return blackOcelotTextures;
/*    */       
/*    */       case 2:
/* 38 */         return redOcelotTextures;
/*    */       case 3:
/*    */         break;
/* 41 */     }  return siameseOcelotTextures;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityOcelot p_77041_1_, float p_77041_2_) {
/* 51 */     super.preRenderCallback((EntityLivingBase)p_77041_1_, p_77041_2_);
/*    */     
/* 53 */     if (p_77041_1_.isTamed())
/*    */     {
/* 55 */       GlStateManager.scale(0.8F, 0.8F, 0.8F);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 65 */     preRenderCallback((EntityOcelot)p_77041_1_, p_77041_2_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 73 */     return getEntityTexture((EntityOcelot)p_110775_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderOcelot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */