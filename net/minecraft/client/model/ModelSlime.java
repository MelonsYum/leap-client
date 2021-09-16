/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelSlime
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer slimeBodies;
/*    */   ModelRenderer slimeRightEye;
/*    */   ModelRenderer slimeLeftEye;
/*    */   ModelRenderer slimeMouth;
/*    */   private static final String __OBFID = "CL_00000858";
/*    */   
/*    */   public ModelSlime(int p_i1157_1_) {
/* 22 */     this.slimeBodies = new ModelRenderer(this, 0, p_i1157_1_);
/* 23 */     this.slimeBodies.addBox(-4.0F, 16.0F, -4.0F, 8, 8, 8);
/*    */     
/* 25 */     if (p_i1157_1_ > 0) {
/*    */       
/* 27 */       this.slimeBodies = new ModelRenderer(this, 0, p_i1157_1_);
/* 28 */       this.slimeBodies.addBox(-3.0F, 17.0F, -3.0F, 6, 6, 6);
/* 29 */       this.slimeRightEye = new ModelRenderer(this, 32, 0);
/* 30 */       this.slimeRightEye.addBox(-3.25F, 18.0F, -3.5F, 2, 2, 2);
/* 31 */       this.slimeLeftEye = new ModelRenderer(this, 32, 4);
/* 32 */       this.slimeLeftEye.addBox(1.25F, 18.0F, -3.5F, 2, 2, 2);
/* 33 */       this.slimeMouth = new ModelRenderer(this, 32, 8);
/* 34 */       this.slimeMouth.addBox(0.0F, 21.0F, -3.5F, 1, 1, 1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 43 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 44 */     this.slimeBodies.render(p_78088_7_);
/*    */     
/* 46 */     if (this.slimeRightEye != null) {
/*    */       
/* 48 */       this.slimeRightEye.render(p_78088_7_);
/* 49 */       this.slimeLeftEye.render(p_78088_7_);
/* 50 */       this.slimeMouth.render(p_78088_7_);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSlime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */