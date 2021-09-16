/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelGhast
/*    */   extends ModelBase {
/*    */   ModelRenderer body;
/* 11 */   ModelRenderer[] tentacles = new ModelRenderer[9];
/*    */   
/*    */   private static final String __OBFID = "CL_00000839";
/*    */   
/*    */   public ModelGhast() {
/* 16 */     byte var1 = -16;
/* 17 */     this.body = new ModelRenderer(this, 0, 0);
/* 18 */     this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
/* 19 */     this.body.rotationPointY += (24 + var1);
/* 20 */     Random var2 = new Random(1660L);
/*    */     
/* 22 */     for (int var3 = 0; var3 < this.tentacles.length; var3++) {
/*    */       
/* 24 */       this.tentacles[var3] = new ModelRenderer(this, 0, 0);
/* 25 */       float var4 = (((var3 % 3) - (var3 / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
/* 26 */       float var5 = ((var3 / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
/* 27 */       int var6 = var2.nextInt(7) + 8;
/* 28 */       this.tentacles[var3].addBox(-1.0F, 0.0F, -1.0F, 2, var6, 2);
/* 29 */       (this.tentacles[var3]).rotationPointX = var4;
/* 30 */       (this.tentacles[var3]).rotationPointZ = var5;
/* 31 */       (this.tentacles[var3]).rotationPointY = (31 + var1);
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
/* 42 */     for (int var8 = 0; var8 < this.tentacles.length; var8++)
/*    */     {
/* 44 */       (this.tentacles[var8]).rotateAngleX = 0.2F * MathHelper.sin(p_78087_3_ * 0.3F + var8) + 0.4F;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 53 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 54 */     GlStateManager.pushMatrix();
/* 55 */     GlStateManager.translate(0.0F, 0.6F, 0.0F);
/* 56 */     this.body.render(p_78088_7_);
/* 57 */     ModelRenderer[] var8 = this.tentacles;
/* 58 */     int var9 = var8.length;
/*    */     
/* 60 */     for (int var10 = 0; var10 < var9; var10++) {
/*    */       
/* 62 */       ModelRenderer var11 = var8[var10];
/* 63 */       var11.render(p_78088_7_);
/*    */     } 
/*    */     
/* 66 */     GlStateManager.popMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelGhast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */