/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelSnowMan
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer body;
/*    */   public ModelRenderer bottomBody;
/*    */   public ModelRenderer head;
/*    */   public ModelRenderer rightHand;
/*    */   public ModelRenderer leftHand;
/*    */   private static final String __OBFID = "CL_00000859";
/*    */   
/*    */   public ModelSnowMan() {
/* 17 */     float var1 = 4.0F;
/* 18 */     float var2 = 0.0F;
/* 19 */     this.head = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
/* 20 */     this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, var2 - 0.5F);
/* 21 */     this.head.setRotationPoint(0.0F, 0.0F + var1, 0.0F);
/* 22 */     this.rightHand = (new ModelRenderer(this, 32, 0)).setTextureSize(64, 64);
/* 23 */     this.rightHand.addBox(-1.0F, 0.0F, -1.0F, 12, 2, 2, var2 - 0.5F);
/* 24 */     this.rightHand.setRotationPoint(0.0F, 0.0F + var1 + 9.0F - 7.0F, 0.0F);
/* 25 */     this.leftHand = (new ModelRenderer(this, 32, 0)).setTextureSize(64, 64);
/* 26 */     this.leftHand.addBox(-1.0F, 0.0F, -1.0F, 12, 2, 2, var2 - 0.5F);
/* 27 */     this.leftHand.setRotationPoint(0.0F, 0.0F + var1 + 9.0F - 7.0F, 0.0F);
/* 28 */     this.body = (new ModelRenderer(this, 0, 16)).setTextureSize(64, 64);
/* 29 */     this.body.addBox(-5.0F, -10.0F, -5.0F, 10, 10, 10, var2 - 0.5F);
/* 30 */     this.body.setRotationPoint(0.0F, 0.0F + var1 + 9.0F, 0.0F);
/* 31 */     this.bottomBody = (new ModelRenderer(this, 0, 36)).setTextureSize(64, 64);
/* 32 */     this.bottomBody.addBox(-6.0F, -12.0F, -6.0F, 12, 12, 12, var2 - 0.5F);
/* 33 */     this.bottomBody.setRotationPoint(0.0F, 0.0F + var1 + 20.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 43 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 44 */     this.head.rotateAngleY = p_78087_4_ / 57.295776F;
/* 45 */     this.head.rotateAngleX = p_78087_5_ / 57.295776F;
/* 46 */     this.body.rotateAngleY = p_78087_4_ / 57.295776F * 0.25F;
/* 47 */     float var8 = MathHelper.sin(this.body.rotateAngleY);
/* 48 */     float var9 = MathHelper.cos(this.body.rotateAngleY);
/* 49 */     this.rightHand.rotateAngleZ = 1.0F;
/* 50 */     this.leftHand.rotateAngleZ = -1.0F;
/* 51 */     this.rightHand.rotateAngleY = 0.0F + this.body.rotateAngleY;
/* 52 */     this.leftHand.rotateAngleY = 3.1415927F + this.body.rotateAngleY;
/* 53 */     this.rightHand.rotationPointX = var9 * 5.0F;
/* 54 */     this.rightHand.rotationPointZ = -var8 * 5.0F;
/* 55 */     this.leftHand.rotationPointX = -var9 * 5.0F;
/* 56 */     this.leftHand.rotationPointZ = var8 * 5.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 64 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 65 */     this.body.render(p_78088_7_);
/* 66 */     this.bottomBody.render(p_78088_7_);
/* 67 */     this.head.render(p_78088_7_);
/* 68 */     this.rightHand.render(p_78088_7_);
/* 69 */     this.leftHand.render(p_78088_7_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSnowMan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */