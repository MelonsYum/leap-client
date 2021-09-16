/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelCreeper
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer creeperArmor;
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer leg1;
/*    */   public ModelRenderer leg2;
/*    */   public ModelRenderer leg3;
/*    */   public ModelRenderer leg4;
/*    */   private static final String __OBFID = "CL_00000837";
/*    */   
/*    */   public ModelCreeper() {
/* 19 */     this(0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelCreeper(float p_i46366_1_) {
/* 24 */     byte var2 = 6;
/* 25 */     this.head = new ModelRenderer(this, 0, 0);
/* 26 */     this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46366_1_);
/* 27 */     this.head.setRotationPoint(0.0F, var2, 0.0F);
/* 28 */     this.creeperArmor = new ModelRenderer(this, 32, 0);
/* 29 */     this.creeperArmor.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46366_1_ + 0.5F);
/* 30 */     this.creeperArmor.setRotationPoint(0.0F, var2, 0.0F);
/* 31 */     this.body = new ModelRenderer(this, 16, 16);
/* 32 */     this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i46366_1_);
/* 33 */     this.body.setRotationPoint(0.0F, var2, 0.0F);
/* 34 */     this.leg1 = new ModelRenderer(this, 0, 16);
/* 35 */     this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
/* 36 */     this.leg1.setRotationPoint(-2.0F, (12 + var2), 4.0F);
/* 37 */     this.leg2 = new ModelRenderer(this, 0, 16);
/* 38 */     this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
/* 39 */     this.leg2.setRotationPoint(2.0F, (12 + var2), 4.0F);
/* 40 */     this.leg3 = new ModelRenderer(this, 0, 16);
/* 41 */     this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
/* 42 */     this.leg3.setRotationPoint(-2.0F, (12 + var2), -4.0F);
/* 43 */     this.leg4 = new ModelRenderer(this, 0, 16);
/* 44 */     this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, p_i46366_1_);
/* 45 */     this.leg4.setRotationPoint(2.0F, (12 + var2), -4.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 53 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 54 */     this.head.render(p_78088_7_);
/* 55 */     this.body.render(p_78088_7_);
/* 56 */     this.leg1.render(p_78088_7_);
/* 57 */     this.leg2.render(p_78088_7_);
/* 58 */     this.leg3.render(p_78088_7_);
/* 59 */     this.leg4.render(p_78088_7_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 69 */     this.head.rotateAngleY = p_78087_4_ / 57.295776F;
/* 70 */     this.head.rotateAngleX = p_78087_5_ / 57.295776F;
/* 71 */     this.leg1.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
/* 72 */     this.leg2.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_;
/* 73 */     this.leg3.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_;
/* 74 */     this.leg4.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */