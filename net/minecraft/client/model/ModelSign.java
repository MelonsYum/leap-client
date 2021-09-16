/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ public class ModelSign
/*    */   extends ModelBase
/*    */ {
/*  6 */   public ModelRenderer signBoard = new ModelRenderer(this, 0, 0);
/*    */   
/*    */   public ModelRenderer signStick;
/*    */   
/*    */   private static final String __OBFID = "CL_00000854";
/*    */ 
/*    */   
/*    */   public ModelSign() {
/* 14 */     this.signBoard.addBox(-12.0F, -14.0F, -1.0F, 24, 12, 2, 0.0F);
/* 15 */     this.signStick = new ModelRenderer(this, 0, 14);
/* 16 */     this.signStick.addBox(-1.0F, -2.0F, -1.0F, 2, 14, 2, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderSign() {
/* 24 */     this.signBoard.render(0.0625F);
/* 25 */     this.signStick.render(0.0625F);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */