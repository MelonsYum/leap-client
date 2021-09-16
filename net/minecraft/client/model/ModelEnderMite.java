/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelEnderMite
/*    */   extends ModelBase {
/*  8 */   private static final int[][] field_178716_a = new int[][] { { 4, 3, 2 }, { 6, 4, 5 }, { 3, 3, 1 }, { 1, 2, 1 } };
/*  9 */   private static final int[][] field_178714_b = new int[][] { new int[2], { 0, 5 }, { 0, 14 }, { 0, 18 } };
/* 10 */   private static final int field_178715_c = field_178716_a.length;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   private final ModelRenderer[] field_178713_d = new ModelRenderer[field_178715_c]; public ModelEnderMite() {
/* 17 */     float var1 = -3.5F;
/*    */     
/* 19 */     for (int var2 = 0; var2 < this.field_178713_d.length; var2++) {
/*    */       
/* 21 */       this.field_178713_d[var2] = new ModelRenderer(this, field_178714_b[var2][0], field_178714_b[var2][1]);
/* 22 */       this.field_178713_d[var2].addBox(field_178716_a[var2][0] * -0.5F, 0.0F, field_178716_a[var2][2] * -0.5F, field_178716_a[var2][0], field_178716_a[var2][1], field_178716_a[var2][2]);
/* 23 */       this.field_178713_d[var2].setRotationPoint(0.0F, (24 - field_178716_a[var2][1]), var1);
/*    */       
/* 25 */       if (var2 < this.field_178713_d.length - 1)
/*    */       {
/* 27 */         var1 += (field_178716_a[var2][2] + field_178716_a[var2 + 1][2]) * 0.5F;
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00002629";
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 37 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*    */     
/* 39 */     for (int var8 = 0; var8 < this.field_178713_d.length; var8++)
/*    */     {
/* 41 */       this.field_178713_d[var8].render(p_78088_7_);
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
/* 52 */     for (int var8 = 0; var8 < this.field_178713_d.length; var8++) {
/*    */       
/* 54 */       (this.field_178713_d[var8]).rotateAngleY = MathHelper.cos(p_78087_3_ * 0.9F + var8 * 0.15F * 3.1415927F) * 3.1415927F * 0.01F * (1 + Math.abs(var8 - 2));
/* 55 */       (this.field_178713_d[var8]).rotationPointX = MathHelper.sin(p_78087_3_ * 0.9F + var8 * 0.15F * 3.1415927F) * 3.1415927F * 0.1F * Math.abs(var8 - 2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelEnderMite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */