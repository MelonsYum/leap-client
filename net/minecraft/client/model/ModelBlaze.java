/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelBlaze
/*    */   extends ModelBase
/*    */ {
/*  9 */   private ModelRenderer[] blazeSticks = new ModelRenderer[12];
/*    */   
/*    */   private ModelRenderer blazeHead;
/*    */   private static final String __OBFID = "CL_00000831";
/*    */   
/*    */   public ModelBlaze() {
/* 15 */     for (int var1 = 0; var1 < this.blazeSticks.length; var1++) {
/*    */       
/* 17 */       this.blazeSticks[var1] = new ModelRenderer(this, 0, 16);
/* 18 */       this.blazeSticks[var1].addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
/*    */     } 
/*    */     
/* 21 */     this.blazeHead = new ModelRenderer(this, 0, 0);
/* 22 */     this.blazeHead.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 30 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 31 */     this.blazeHead.render(p_78088_7_);
/*    */     
/* 33 */     for (int var8 = 0; var8 < this.blazeSticks.length; var8++)
/*    */     {
/* 35 */       this.blazeSticks[var8].render(p_78088_7_);
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
/* 46 */     float var8 = p_78087_3_ * 3.1415927F * -0.1F;
/*    */     
/*    */     int var9;
/* 49 */     for (var9 = 0; var9 < 4; var9++) {
/*    */       
/* 51 */       (this.blazeSticks[var9]).rotationPointY = -2.0F + MathHelper.cos(((var9 * 2) + p_78087_3_) * 0.25F);
/* 52 */       (this.blazeSticks[var9]).rotationPointX = MathHelper.cos(var8) * 9.0F;
/* 53 */       (this.blazeSticks[var9]).rotationPointZ = MathHelper.sin(var8) * 9.0F;
/* 54 */       var8++;
/*    */     } 
/*    */     
/* 57 */     var8 = 0.7853982F + p_78087_3_ * 3.1415927F * 0.03F;
/*    */     
/* 59 */     for (var9 = 4; var9 < 8; var9++) {
/*    */       
/* 61 */       (this.blazeSticks[var9]).rotationPointY = 2.0F + MathHelper.cos(((var9 * 2) + p_78087_3_) * 0.25F);
/* 62 */       (this.blazeSticks[var9]).rotationPointX = MathHelper.cos(var8) * 7.0F;
/* 63 */       (this.blazeSticks[var9]).rotationPointZ = MathHelper.sin(var8) * 7.0F;
/* 64 */       var8++;
/*    */     } 
/*    */     
/* 67 */     var8 = 0.47123894F + p_78087_3_ * 3.1415927F * -0.05F;
/*    */     
/* 69 */     for (var9 = 8; var9 < 12; var9++) {
/*    */       
/* 71 */       (this.blazeSticks[var9]).rotationPointY = 11.0F + MathHelper.cos((var9 * 1.5F + p_78087_3_) * 0.5F);
/* 72 */       (this.blazeSticks[var9]).rotationPointX = MathHelper.cos(var8) * 5.0F;
/* 73 */       (this.blazeSticks[var9]).rotationPointZ = MathHelper.sin(var8) * 5.0F;
/* 74 */       var8++;
/*    */     } 
/*    */     
/* 77 */     this.blazeHead.rotateAngleY = p_78087_4_ / 57.295776F;
/* 78 */     this.blazeHead.rotateAngleX = p_78087_5_ / 57.295776F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelBlaze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */