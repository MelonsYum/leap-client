/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelSquid
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer squidBody;
/* 11 */   ModelRenderer[] squidTentacles = new ModelRenderer[8];
/*    */   
/*    */   private static final String __OBFID = "CL_00000861";
/*    */   
/*    */   public ModelSquid() {
/* 16 */     byte var1 = -16;
/* 17 */     this.squidBody = new ModelRenderer(this, 0, 0);
/* 18 */     this.squidBody.addBox(-6.0F, -8.0F, -6.0F, 12, 16, 12);
/* 19 */     this.squidBody.rotationPointY += (24 + var1);
/*    */     
/* 21 */     for (int var2 = 0; var2 < this.squidTentacles.length; var2++) {
/*    */       
/* 23 */       this.squidTentacles[var2] = new ModelRenderer(this, 48, 0);
/* 24 */       double var3 = var2 * Math.PI * 2.0D / this.squidTentacles.length;
/* 25 */       float var5 = (float)Math.cos(var3) * 5.0F;
/* 26 */       float var6 = (float)Math.sin(var3) * 5.0F;
/* 27 */       this.squidTentacles[var2].addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2);
/* 28 */       (this.squidTentacles[var2]).rotationPointX = var5;
/* 29 */       (this.squidTentacles[var2]).rotationPointZ = var6;
/* 30 */       (this.squidTentacles[var2]).rotationPointY = (31 + var1);
/* 31 */       var3 = var2 * Math.PI * -2.0D / this.squidTentacles.length + 1.5707963267948966D;
/* 32 */       (this.squidTentacles[var2]).rotateAngleY = (float)var3;
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
/* 43 */     ModelRenderer[] var8 = this.squidTentacles;
/* 44 */     int var9 = var8.length;
/*    */     
/* 46 */     for (int var10 = 0; var10 < var9; var10++) {
/*    */       
/* 48 */       ModelRenderer var11 = var8[var10];
/* 49 */       var11.rotateAngleX = p_78087_3_;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 58 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 59 */     this.squidBody.render(p_78088_7_);
/*    */     
/* 61 */     for (int var8 = 0; var8 < this.squidTentacles.length; var8++)
/*    */     {
/* 63 */       this.squidTentacles[var8].render(p_78088_7_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */