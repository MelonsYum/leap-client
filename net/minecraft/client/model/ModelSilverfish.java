/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelSilverfish
/*    */   extends ModelBase
/*    */ {
/*  9 */   private ModelRenderer[] silverfishBodyParts = new ModelRenderer[7];
/*    */   
/*    */   private ModelRenderer[] silverfishWings;
/*    */   
/* 13 */   private float[] field_78170_c = new float[7];
/*    */ 
/*    */   
/* 16 */   private static final int[][] silverfishBoxLength = new int[][] { { 3, 2, 2 }, { 4, 3, 2 }, { 6, 4, 3 }, { 3, 3, 3 }, { 2, 2, 3 }, { 2, 1, 2 }, { 1, 1, 2 } };
/*    */ 
/*    */   
/* 19 */   private static final int[][] silverfishTexturePositions = new int[][] { new int[2], { 0, 4 }, { 0, 9 }, { 0, 16 }, { 0, 22 }, { 11 }, { 13, 4 } };
/*    */   
/*    */   private static final String __OBFID = "CL_00000855";
/*    */   
/*    */   public ModelSilverfish() {
/* 24 */     float var1 = -3.5F;
/*    */     
/* 26 */     for (int var2 = 0; var2 < this.silverfishBodyParts.length; var2++) {
/*    */       
/* 28 */       this.silverfishBodyParts[var2] = new ModelRenderer(this, silverfishTexturePositions[var2][0], silverfishTexturePositions[var2][1]);
/* 29 */       this.silverfishBodyParts[var2].addBox(silverfishBoxLength[var2][0] * -0.5F, 0.0F, silverfishBoxLength[var2][2] * -0.5F, silverfishBoxLength[var2][0], silverfishBoxLength[var2][1], silverfishBoxLength[var2][2]);
/* 30 */       this.silverfishBodyParts[var2].setRotationPoint(0.0F, (24 - silverfishBoxLength[var2][1]), var1);
/* 31 */       this.field_78170_c[var2] = var1;
/*    */       
/* 33 */       if (var2 < this.silverfishBodyParts.length - 1)
/*    */       {
/* 35 */         var1 += (silverfishBoxLength[var2][2] + silverfishBoxLength[var2 + 1][2]) * 0.5F;
/*    */       }
/*    */     } 
/*    */     
/* 39 */     this.silverfishWings = new ModelRenderer[3];
/* 40 */     this.silverfishWings[0] = new ModelRenderer(this, 20, 0);
/* 41 */     this.silverfishWings[0].addBox(-5.0F, 0.0F, silverfishBoxLength[2][2] * -0.5F, 10, 8, silverfishBoxLength[2][2]);
/* 42 */     this.silverfishWings[0].setRotationPoint(0.0F, 16.0F, this.field_78170_c[2]);
/* 43 */     this.silverfishWings[1] = new ModelRenderer(this, 20, 11);
/* 44 */     this.silverfishWings[1].addBox(-3.0F, 0.0F, silverfishBoxLength[4][2] * -0.5F, 6, 4, silverfishBoxLength[4][2]);
/* 45 */     this.silverfishWings[1].setRotationPoint(0.0F, 20.0F, this.field_78170_c[4]);
/* 46 */     this.silverfishWings[2] = new ModelRenderer(this, 20, 18);
/* 47 */     this.silverfishWings[2].addBox(-3.0F, 0.0F, silverfishBoxLength[4][2] * -0.5F, 6, 5, silverfishBoxLength[1][2]);
/* 48 */     this.silverfishWings[2].setRotationPoint(0.0F, 19.0F, this.field_78170_c[1]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 56 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*    */     
/*    */     int var8;
/* 59 */     for (var8 = 0; var8 < this.silverfishBodyParts.length; var8++)
/*    */     {
/* 61 */       this.silverfishBodyParts[var8].render(p_78088_7_);
/*    */     }
/*    */     
/* 64 */     for (var8 = 0; var8 < this.silverfishWings.length; var8++)
/*    */     {
/* 66 */       this.silverfishWings[var8].render(p_78088_7_);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 77 */     for (int var8 = 0; var8 < this.silverfishBodyParts.length; var8++) {
/*    */       
/* 79 */       (this.silverfishBodyParts[var8]).rotateAngleY = MathHelper.cos(p_78087_3_ * 0.9F + var8 * 0.15F * 3.1415927F) * 3.1415927F * 0.05F * (1 + Math.abs(var8 - 2));
/* 80 */       (this.silverfishBodyParts[var8]).rotationPointX = MathHelper.sin(p_78087_3_ * 0.9F + var8 * 0.15F * 3.1415927F) * 3.1415927F * 0.2F * Math.abs(var8 - 2);
/*    */     } 
/*    */     
/* 83 */     (this.silverfishWings[0]).rotateAngleY = (this.silverfishBodyParts[2]).rotateAngleY;
/* 84 */     (this.silverfishWings[1]).rotateAngleY = (this.silverfishBodyParts[4]).rotateAngleY;
/* 85 */     (this.silverfishWings[1]).rotationPointX = (this.silverfishBodyParts[4]).rotationPointX;
/* 86 */     (this.silverfishWings[2]).rotateAngleY = (this.silverfishBodyParts[1]).rotateAngleY;
/* 87 */     (this.silverfishWings[2]).rotationPointX = (this.silverfishBodyParts[1]).rotationPointX;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSilverfish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */