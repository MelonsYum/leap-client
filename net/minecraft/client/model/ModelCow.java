/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ public class ModelCow
/*    */   extends ModelQuadruped
/*    */ {
/*    */   private static final String __OBFID = "CL_00000836";
/*    */   
/*    */   public ModelCow() {
/*  9 */     super(12, 0.0F);
/* 10 */     this.head = new ModelRenderer(this, 0, 0);
/* 11 */     this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
/* 12 */     this.head.setRotationPoint(0.0F, 4.0F, -8.0F);
/* 13 */     this.head.setTextureOffset(22, 0).addBox(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
/* 14 */     this.head.setTextureOffset(22, 0).addBox(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
/* 15 */     this.body = new ModelRenderer(this, 18, 4);
/* 16 */     this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
/* 17 */     this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
/* 18 */     this.body.setTextureOffset(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1);
/* 19 */     this.leg1.rotationPointX--;
/* 20 */     this.leg2.rotationPointX++;
/* 21 */     this.leg1.rotationPointZ += 0.0F;
/* 22 */     this.leg2.rotationPointZ += 0.0F;
/* 23 */     this.leg3.rotationPointX--;
/* 24 */     this.leg4.rotationPointX++;
/* 25 */     this.leg3.rotationPointZ--;
/* 26 */     this.leg4.rotationPointZ--;
/* 27 */     this.childZOffset += 2.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */