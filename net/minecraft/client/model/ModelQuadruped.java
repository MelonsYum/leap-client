/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelQuadruped
/*    */   extends ModelBase {
/*  9 */   public ModelRenderer head = new ModelRenderer(this, 0, 0);
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer leg1;
/*    */   public ModelRenderer leg2;
/*    */   public ModelRenderer leg3;
/*    */   public ModelRenderer leg4;
/* 15 */   protected float childYOffset = 8.0F;
/* 16 */   protected float childZOffset = 4.0F;
/*    */   
/*    */   private static final String __OBFID = "CL_00000851";
/*    */   
/*    */   public ModelQuadruped(int p_i1154_1_, float p_i1154_2_) {
/* 21 */     this.head.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, p_i1154_2_);
/* 22 */     this.head.setRotationPoint(0.0F, (18 - p_i1154_1_), -6.0F);
/* 23 */     this.body = new ModelRenderer(this, 28, 8);
/* 24 */     this.body.addBox(-5.0F, -10.0F, -7.0F, 10, 16, 8, p_i1154_2_);
/* 25 */     this.body.setRotationPoint(0.0F, (17 - p_i1154_1_), 2.0F);
/* 26 */     this.leg1 = new ModelRenderer(this, 0, 16);
/* 27 */     this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
/* 28 */     this.leg1.setRotationPoint(-3.0F, (24 - p_i1154_1_), 7.0F);
/* 29 */     this.leg2 = new ModelRenderer(this, 0, 16);
/* 30 */     this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
/* 31 */     this.leg2.setRotationPoint(3.0F, (24 - p_i1154_1_), 7.0F);
/* 32 */     this.leg3 = new ModelRenderer(this, 0, 16);
/* 33 */     this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
/* 34 */     this.leg3.setRotationPoint(-3.0F, (24 - p_i1154_1_), -5.0F);
/* 35 */     this.leg4 = new ModelRenderer(this, 0, 16);
/* 36 */     this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, p_i1154_1_, 4, p_i1154_2_);
/* 37 */     this.leg4.setRotationPoint(3.0F, (24 - p_i1154_1_), -5.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 45 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*    */     
/* 47 */     if (this.isChild) {
/*    */       
/* 49 */       float var8 = 2.0F;
/* 50 */       GlStateManager.pushMatrix();
/* 51 */       GlStateManager.translate(0.0F, this.childYOffset * p_78088_7_, this.childZOffset * p_78088_7_);
/* 52 */       this.head.render(p_78088_7_);
/* 53 */       GlStateManager.popMatrix();
/* 54 */       GlStateManager.pushMatrix();
/* 55 */       GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
/* 56 */       GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
/* 57 */       this.body.render(p_78088_7_);
/* 58 */       this.leg1.render(p_78088_7_);
/* 59 */       this.leg2.render(p_78088_7_);
/* 60 */       this.leg3.render(p_78088_7_);
/* 61 */       this.leg4.render(p_78088_7_);
/* 62 */       GlStateManager.popMatrix();
/*    */     }
/*    */     else {
/*    */       
/* 66 */       this.head.render(p_78088_7_);
/* 67 */       this.body.render(p_78088_7_);
/* 68 */       this.leg1.render(p_78088_7_);
/* 69 */       this.leg2.render(p_78088_7_);
/* 70 */       this.leg3.render(p_78088_7_);
/* 71 */       this.leg4.render(p_78088_7_);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 82 */     float var8 = 57.295776F;
/* 83 */     this.head.rotateAngleX = p_78087_5_ / 57.295776F;
/* 84 */     this.head.rotateAngleY = p_78087_4_ / 57.295776F;
/* 85 */     this.body.rotateAngleX = 1.5707964F;
/* 86 */     this.leg1.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
/* 87 */     this.leg2.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_;
/* 88 */     this.leg3.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_;
/* 89 */     this.leg4.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelQuadruped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */