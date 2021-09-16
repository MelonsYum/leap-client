/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.passive.EntitySheep;
/*    */ 
/*    */ public class ModelSheep2
/*    */   extends ModelQuadruped
/*    */ {
/*    */   private float field_78153_i;
/*    */   private static final String __OBFID = "CL_00000853";
/*    */   
/*    */   public ModelSheep2() {
/* 14 */     super(12, 0.0F);
/* 15 */     this.head = new ModelRenderer(this, 0, 0);
/* 16 */     this.head.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
/* 17 */     this.head.setRotationPoint(0.0F, 6.0F, -8.0F);
/* 18 */     this.body = new ModelRenderer(this, 28, 8);
/* 19 */     this.body.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
/* 20 */     this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 29 */     super.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
/* 30 */     this.head.rotationPointY = 6.0F + ((EntitySheep)p_78086_1_).getHeadRotationPointY(p_78086_4_) * 9.0F;
/* 31 */     this.field_78153_i = ((EntitySheep)p_78086_1_).getHeadRotationAngleX(p_78086_4_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 41 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 42 */     this.head.rotateAngleX = this.field_78153_i;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSheep2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */