/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelBook
/*    */   extends ModelBase
/*    */ {
/*  9 */   public ModelRenderer coverRight = (new ModelRenderer(this)).setTextureOffset(0, 0).addBox(-6.0F, -5.0F, 0.0F, 6, 10, 0);
/*    */ 
/*    */   
/* 12 */   public ModelRenderer coverLeft = (new ModelRenderer(this)).setTextureOffset(16, 0).addBox(0.0F, -5.0F, 0.0F, 6, 10, 0);
/*    */ 
/*    */   
/* 15 */   public ModelRenderer pagesRight = (new ModelRenderer(this)).setTextureOffset(0, 10).addBox(0.0F, -4.0F, -0.99F, 5, 8, 1);
/*    */ 
/*    */   
/* 18 */   public ModelRenderer pagesLeft = (new ModelRenderer(this)).setTextureOffset(12, 10).addBox(0.0F, -4.0F, -0.01F, 5, 8, 1);
/*    */ 
/*    */   
/* 21 */   public ModelRenderer flippingPageRight = (new ModelRenderer(this)).setTextureOffset(24, 10).addBox(0.0F, -4.0F, 0.0F, 5, 8, 0);
/*    */ 
/*    */   
/* 24 */   public ModelRenderer flippingPageLeft = (new ModelRenderer(this)).setTextureOffset(24, 10).addBox(0.0F, -4.0F, 0.0F, 5, 8, 0);
/*    */ 
/*    */   
/* 27 */   public ModelRenderer bookSpine = (new ModelRenderer(this)).setTextureOffset(12, 0).addBox(-1.0F, -5.0F, 0.0F, 2, 10, 0);
/*    */   
/*    */   private static final String __OBFID = "CL_00000833";
/*    */   
/*    */   public ModelBook() {
/* 32 */     this.coverRight.setRotationPoint(0.0F, 0.0F, -1.0F);
/* 33 */     this.coverLeft.setRotationPoint(0.0F, 0.0F, 1.0F);
/* 34 */     this.bookSpine.rotateAngleY = 1.5707964F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 42 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 43 */     this.coverRight.render(p_78088_7_);
/* 44 */     this.coverLeft.render(p_78088_7_);
/* 45 */     this.bookSpine.render(p_78088_7_);
/* 46 */     this.pagesRight.render(p_78088_7_);
/* 47 */     this.pagesLeft.render(p_78088_7_);
/* 48 */     this.flippingPageRight.render(p_78088_7_);
/* 49 */     this.flippingPageLeft.render(p_78088_7_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 59 */     float var8 = (MathHelper.sin(p_78087_1_ * 0.02F) * 0.1F + 1.25F) * p_78087_4_;
/* 60 */     this.coverRight.rotateAngleY = 3.1415927F + var8;
/* 61 */     this.coverLeft.rotateAngleY = -var8;
/* 62 */     this.pagesRight.rotateAngleY = var8;
/* 63 */     this.pagesLeft.rotateAngleY = -var8;
/* 64 */     this.flippingPageRight.rotateAngleY = var8 - var8 * 2.0F * p_78087_2_;
/* 65 */     this.flippingPageLeft.rotateAngleY = var8 - var8 * 2.0F * p_78087_3_;
/* 66 */     this.pagesRight.rotationPointX = MathHelper.sin(var8);
/* 67 */     this.pagesLeft.rotationPointX = MathHelper.sin(var8);
/* 68 */     this.flippingPageRight.rotationPointX = MathHelper.sin(var8);
/* 69 */     this.flippingPageLeft.rotationPointX = MathHelper.sin(var8);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */