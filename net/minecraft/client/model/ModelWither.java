/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.boss.EntityWither;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelWither
/*    */   extends ModelBase
/*    */ {
/*    */   private ModelRenderer[] field_82905_a;
/*    */   private ModelRenderer[] field_82904_b;
/*    */   private static final String __OBFID = "CL_00000867";
/*    */   
/*    */   public ModelWither(float p_i46302_1_) {
/* 16 */     this.textureWidth = 64;
/* 17 */     this.textureHeight = 64;
/* 18 */     this.field_82905_a = new ModelRenderer[3];
/* 19 */     this.field_82905_a[0] = new ModelRenderer(this, 0, 16);
/* 20 */     this.field_82905_a[0].addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3, p_i46302_1_);
/* 21 */     this.field_82905_a[1] = (new ModelRenderer(this)).setTextureSize(this.textureWidth, this.textureHeight);
/* 22 */     this.field_82905_a[1].setRotationPoint(-2.0F, 6.9F, -0.5F);
/* 23 */     this.field_82905_a[1].setTextureOffset(0, 22).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, p_i46302_1_);
/* 24 */     this.field_82905_a[1].setTextureOffset(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11, 2, 2, p_i46302_1_);
/* 25 */     this.field_82905_a[1].setTextureOffset(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11, 2, 2, p_i46302_1_);
/* 26 */     this.field_82905_a[1].setTextureOffset(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11, 2, 2, p_i46302_1_);
/* 27 */     this.field_82905_a[2] = new ModelRenderer(this, 12, 22);
/* 28 */     this.field_82905_a[2].addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, p_i46302_1_);
/* 29 */     this.field_82904_b = new ModelRenderer[3];
/* 30 */     this.field_82904_b[0] = new ModelRenderer(this, 0, 0);
/* 31 */     this.field_82904_b[0].addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, p_i46302_1_);
/* 32 */     this.field_82904_b[1] = new ModelRenderer(this, 32, 0);
/* 33 */     this.field_82904_b[1].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
/* 34 */     (this.field_82904_b[1]).rotationPointX = -8.0F;
/* 35 */     (this.field_82904_b[1]).rotationPointY = 4.0F;
/* 36 */     this.field_82904_b[2] = new ModelRenderer(this, 32, 0);
/* 37 */     this.field_82904_b[2].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, p_i46302_1_);
/* 38 */     (this.field_82904_b[2]).rotationPointX = 10.0F;
/* 39 */     (this.field_82904_b[2]).rotationPointY = 4.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 47 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 48 */     ModelRenderer[] var8 = this.field_82904_b;
/* 49 */     int var9 = var8.length;
/*    */     
/*    */     int var10;
/*    */     
/* 53 */     for (var10 = 0; var10 < var9; var10++) {
/*    */       
/* 55 */       ModelRenderer var11 = var8[var10];
/* 56 */       var11.render(p_78088_7_);
/*    */     } 
/*    */     
/* 59 */     var8 = this.field_82905_a;
/* 60 */     var9 = var8.length;
/*    */     
/* 62 */     for (var10 = 0; var10 < var9; var10++) {
/*    */       
/* 64 */       ModelRenderer var11 = var8[var10];
/* 65 */       var11.render(p_78088_7_);
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
/* 76 */     float var8 = MathHelper.cos(p_78087_3_ * 0.1F);
/* 77 */     (this.field_82905_a[1]).rotateAngleX = (0.065F + 0.05F * var8) * 3.1415927F;
/* 78 */     this.field_82905_a[2].setRotationPoint(-2.0F, 6.9F + MathHelper.cos((this.field_82905_a[1]).rotateAngleX) * 10.0F, -0.5F + MathHelper.sin((this.field_82905_a[1]).rotateAngleX) * 10.0F);
/* 79 */     (this.field_82905_a[2]).rotateAngleX = (0.265F + 0.1F * var8) * 3.1415927F;
/* 80 */     (this.field_82904_b[0]).rotateAngleY = p_78087_4_ / 57.295776F;
/* 81 */     (this.field_82904_b[0]).rotateAngleX = p_78087_5_ / 57.295776F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 90 */     EntityWither var5 = (EntityWither)p_78086_1_;
/*    */     
/* 92 */     for (int var6 = 1; var6 < 3; var6++) {
/*    */       
/* 94 */       (this.field_82904_b[var6]).rotateAngleY = (var5.func_82207_a(var6 - 1) - p_78086_1_.renderYawOffset) / 57.295776F;
/* 95 */       (this.field_82904_b[var6]).rotateAngleX = var5.func_82210_r(var6 - 1) / 57.295776F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */