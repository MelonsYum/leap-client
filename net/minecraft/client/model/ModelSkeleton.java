/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.monster.EntitySkeleton;
/*    */ 
/*    */ public class ModelSkeleton
/*    */   extends ModelZombie
/*    */ {
/*    */   private static final String __OBFID = "CL_00000857";
/*    */   
/*    */   public ModelSkeleton() {
/* 13 */     this(0.0F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelSkeleton(float p_i46303_1_, boolean p_i46303_2_) {
/* 18 */     super(p_i46303_1_, 0.0F, 64, 32);
/*    */     
/* 20 */     if (!p_i46303_2_) {
/*    */       
/* 22 */       this.bipedRightArm = new ModelRenderer(this, 40, 16);
/* 23 */       this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, p_i46303_1_);
/* 24 */       this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/* 25 */       this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/* 26 */       this.bipedLeftArm.mirror = true;
/* 27 */       this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, p_i46303_1_);
/* 28 */       this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/* 29 */       this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/* 30 */       this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, p_i46303_1_);
/* 31 */       this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
/* 32 */       this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/* 33 */       this.bipedLeftLeg.mirror = true;
/* 34 */       this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, p_i46303_1_);
/* 35 */       this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 45 */     this.aimedBow = (((EntitySkeleton)p_78086_1_).getSkeletonType() == 1);
/* 46 */     super.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 56 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */