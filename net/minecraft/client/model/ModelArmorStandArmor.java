/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityArmorStand;
/*    */ 
/*    */ public class ModelArmorStandArmor
/*    */   extends ModelBiped
/*    */ {
/*    */   private static final String __OBFID = "CL_00002632";
/*    */   
/*    */   public ModelArmorStandArmor() {
/* 12 */     this(0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelArmorStandArmor(float p_i46307_1_) {
/* 17 */     this(p_i46307_1_, 64, 32);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ModelArmorStandArmor(float p_i46308_1_, int p_i46308_2_, int p_i46308_3_) {
/* 22 */     super(p_i46308_1_, 0.0F, p_i46308_2_, p_i46308_3_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 32 */     if (p_78087_7_ instanceof EntityArmorStand) {
/*    */       
/* 34 */       EntityArmorStand var8 = (EntityArmorStand)p_78087_7_;
/* 35 */       this.bipedHead.rotateAngleX = 0.017453292F * var8.getHeadRotation().func_179415_b();
/* 36 */       this.bipedHead.rotateAngleY = 0.017453292F * var8.getHeadRotation().func_179416_c();
/* 37 */       this.bipedHead.rotateAngleZ = 0.017453292F * var8.getHeadRotation().func_179413_d();
/* 38 */       this.bipedHead.setRotationPoint(0.0F, 1.0F, 0.0F);
/* 39 */       this.bipedBody.rotateAngleX = 0.017453292F * var8.getBodyRotation().func_179415_b();
/* 40 */       this.bipedBody.rotateAngleY = 0.017453292F * var8.getBodyRotation().func_179416_c();
/* 41 */       this.bipedBody.rotateAngleZ = 0.017453292F * var8.getBodyRotation().func_179413_d();
/* 42 */       this.bipedLeftArm.rotateAngleX = 0.017453292F * var8.getLeftArmRotation().func_179415_b();
/* 43 */       this.bipedLeftArm.rotateAngleY = 0.017453292F * var8.getLeftArmRotation().func_179416_c();
/* 44 */       this.bipedLeftArm.rotateAngleZ = 0.017453292F * var8.getLeftArmRotation().func_179413_d();
/* 45 */       this.bipedRightArm.rotateAngleX = 0.017453292F * var8.getRightArmRotation().func_179415_b();
/* 46 */       this.bipedRightArm.rotateAngleY = 0.017453292F * var8.getRightArmRotation().func_179416_c();
/* 47 */       this.bipedRightArm.rotateAngleZ = 0.017453292F * var8.getRightArmRotation().func_179413_d();
/* 48 */       this.bipedLeftLeg.rotateAngleX = 0.017453292F * var8.getLeftLegRotation().func_179415_b();
/* 49 */       this.bipedLeftLeg.rotateAngleY = 0.017453292F * var8.getLeftLegRotation().func_179416_c();
/* 50 */       this.bipedLeftLeg.rotateAngleZ = 0.017453292F * var8.getLeftLegRotation().func_179413_d();
/* 51 */       this.bipedLeftLeg.setRotationPoint(1.9F, 11.0F, 0.0F);
/* 52 */       this.bipedRightLeg.rotateAngleX = 0.017453292F * var8.getRightLegRotation().func_179415_b();
/* 53 */       this.bipedRightLeg.rotateAngleY = 0.017453292F * var8.getRightLegRotation().func_179416_c();
/* 54 */       this.bipedRightLeg.rotateAngleZ = 0.017453292F * var8.getRightLegRotation().func_179413_d();
/* 55 */       this.bipedRightLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
/* 56 */       func_178685_a(this.bipedHead, this.bipedHeadwear);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelArmorStandArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */