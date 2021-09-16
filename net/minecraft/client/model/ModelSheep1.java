/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntitySheep;
/*    */ 
/*    */ public class ModelSheep1
/*    */   extends ModelQuadruped
/*    */ {
/*    */   private float headRotationAngleX;
/*    */   private static final String __OBFID = "CL_00000852";
/*    */   
/*    */   public ModelSheep1() {
/* 14 */     super(12, 0.0F);
/* 15 */     this.head = new ModelRenderer(this, 0, 0);
/* 16 */     this.head.addBox(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
/* 17 */     this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
/* 18 */     this.body = new ModelRenderer(this, 28, 8);
/* 19 */     this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
/* 20 */     this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
/* 21 */     float var1 = 0.5F;
/* 22 */     this.leg1 = new ModelRenderer(this, 0, 16);
/* 23 */     this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
/* 24 */     this.leg1.setRotationPoint(-3.0F, 12.0F, 7.0F);
/* 25 */     this.leg2 = new ModelRenderer(this, 0, 16);
/* 26 */     this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
/* 27 */     this.leg2.setRotationPoint(3.0F, 12.0F, 7.0F);
/* 28 */     this.leg3 = new ModelRenderer(this, 0, 16);
/* 29 */     this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
/* 30 */     this.leg3.setRotationPoint(-3.0F, 12.0F, -5.0F);
/* 31 */     this.leg4 = new ModelRenderer(this, 0, 16);
/* 32 */     this.leg4.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
/* 33 */     this.leg4.setRotationPoint(3.0F, 12.0F, -5.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 42 */     super.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
/* 43 */     this.head.rotationPointY = 6.0F + ((EntitySheep)p_78086_1_).getHeadRotationPointY(p_78086_4_) * 9.0F;
/* 44 */     this.headRotationAngleX = ((EntitySheep)p_78086_1_).getHeadRotationAngleX(p_78086_4_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 54 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 55 */     this.head.rotateAngleX = this.headRotationAngleX;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSheep1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */