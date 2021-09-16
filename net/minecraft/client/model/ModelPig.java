/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ public class ModelPig
/*    */   extends ModelQuadruped
/*    */ {
/*    */   private static final String __OBFID = "CL_00000849";
/*    */   
/*    */   public ModelPig() {
/*  9 */     this(0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelPig(float p_i1151_1_) {
/* 14 */     super(6, p_i1151_1_);
/* 15 */     this.head.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, p_i1151_1_);
/* 16 */     this.childYOffset = 4.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelPig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */