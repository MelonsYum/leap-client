/*    */ package net.minecraft.client.renderer.entity.layers;
/*    */ 
/*    */ import net.minecraft.client.model.ModelWither;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.renderer.entity.RenderWither;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.EntityWither;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class LayerWitherAura implements LayerRenderer {
/* 13 */   private static final ResourceLocation field_177217_a = new ResourceLocation("textures/entity/wither/wither_armor.png");
/*    */   private final RenderWither field_177215_b;
/* 15 */   private final ModelWither field_177216_c = new ModelWither(0.5F);
/*    */   
/*    */   private static final String __OBFID = "CL_00002406";
/*    */   
/*    */   public LayerWitherAura(RenderWither p_i46105_1_) {
/* 20 */     this.field_177215_b = p_i46105_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177214_a(EntityWither p_177214_1_, float p_177214_2_, float p_177214_3_, float p_177214_4_, float p_177214_5_, float p_177214_6_, float p_177214_7_, float p_177214_8_) {
/* 25 */     if (p_177214_1_.isArmored()) {
/*    */       
/* 27 */       GlStateManager.depthMask(!p_177214_1_.isInvisible());
/* 28 */       this.field_177215_b.bindTexture(field_177217_a);
/* 29 */       GlStateManager.matrixMode(5890);
/* 30 */       GlStateManager.loadIdentity();
/* 31 */       float var9 = p_177214_1_.ticksExisted + p_177214_4_;
/* 32 */       float var10 = MathHelper.cos(var9 * 0.02F) * 3.0F;
/* 33 */       float var11 = var9 * 0.01F;
/* 34 */       GlStateManager.translate(var10, var11, 0.0F);
/* 35 */       GlStateManager.matrixMode(5888);
/* 36 */       GlStateManager.enableBlend();
/* 37 */       float var12 = 0.5F;
/* 38 */       GlStateManager.color(var12, var12, var12, 1.0F);
/* 39 */       GlStateManager.disableLighting();
/* 40 */       GlStateManager.blendFunc(1, 1);
/* 41 */       this.field_177216_c.setLivingAnimations((EntityLivingBase)p_177214_1_, p_177214_2_, p_177214_3_, p_177214_4_);
/* 42 */       this.field_177216_c.setModelAttributes(this.field_177215_b.getMainModel());
/* 43 */       this.field_177216_c.render((Entity)p_177214_1_, p_177214_2_, p_177214_3_, p_177214_5_, p_177214_6_, p_177214_7_, p_177214_8_);
/* 44 */       GlStateManager.matrixMode(5890);
/* 45 */       GlStateManager.loadIdentity();
/* 46 */       GlStateManager.matrixMode(5888);
/* 47 */       GlStateManager.enableLighting();
/* 48 */       GlStateManager.disableBlend();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldCombineTextures() {
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
/* 59 */     func_177214_a((EntityWither)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\layers\LayerWitherAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */