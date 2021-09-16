/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelZombie
/*    */   extends ModelBiped
/*    */ {
/*    */   private static final String __OBFID = "CL_00000869";
/*    */   
/*    */   public ModelZombie() {
/* 12 */     this(0.0F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ModelZombie(float p_i1167_1_, float p_i1167_2_, int p_i1167_3_, int p_i1167_4_) {
/* 17 */     super(p_i1167_1_, p_i1167_2_, p_i1167_3_, p_i1167_4_);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelZombie(float p_i1168_1_, boolean p_i1168_2_) {
/* 22 */     super(p_i1168_1_, 0.0F, 64, p_i1168_2_ ? 32 : 64);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 32 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 33 */     float var8 = MathHelper.sin(this.swingProgress * 3.1415927F);
/* 34 */     float var9 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * 3.1415927F);
/* 35 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 36 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 37 */     this.bipedRightArm.rotateAngleY = -(0.1F - var8 * 0.6F);
/* 38 */     this.bipedLeftArm.rotateAngleY = 0.1F - var8 * 0.6F;
/* 39 */     this.bipedRightArm.rotateAngleX = -1.5707964F;
/* 40 */     this.bipedLeftArm.rotateAngleX = -1.5707964F;
/* 41 */     this.bipedRightArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
/* 42 */     this.bipedLeftArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
/* 43 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 44 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 45 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
/* 46 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */