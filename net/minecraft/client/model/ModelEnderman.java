/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelEnderman
/*     */   extends ModelBiped
/*     */ {
/*     */   public boolean isCarrying;
/*     */   public boolean isAttacking;
/*     */   private static final String __OBFID = "CL_00000838";
/*     */   
/*     */   public ModelEnderman(float p_i46305_1_) {
/*  16 */     super(0.0F, -14.0F, 64, 32);
/*  17 */     float var2 = -14.0F;
/*  18 */     this.bipedHeadwear = new ModelRenderer(this, 0, 16);
/*  19 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i46305_1_ - 0.5F);
/*  20 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
/*  21 */     this.bipedBody = new ModelRenderer(this, 32, 16);
/*  22 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i46305_1_);
/*  23 */     this.bipedBody.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
/*  24 */     this.bipedRightArm = new ModelRenderer(this, 56, 0);
/*  25 */     this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, p_i46305_1_);
/*  26 */     this.bipedRightArm.setRotationPoint(-3.0F, 2.0F + var2, 0.0F);
/*  27 */     this.bipedLeftArm = new ModelRenderer(this, 56, 0);
/*  28 */     this.bipedLeftArm.mirror = true;
/*  29 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 30, 2, p_i46305_1_);
/*  30 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + var2, 0.0F);
/*  31 */     this.bipedRightLeg = new ModelRenderer(this, 56, 0);
/*  32 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, p_i46305_1_);
/*  33 */     this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F + var2, 0.0F);
/*  34 */     this.bipedLeftLeg = new ModelRenderer(this, 56, 0);
/*  35 */     this.bipedLeftLeg.mirror = true;
/*  36 */     this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 30, 2, p_i46305_1_);
/*  37 */     this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F + var2, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/*  47 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/*  48 */     this.bipedHead.showModel = true;
/*  49 */     float var8 = -14.0F;
/*  50 */     this.bipedBody.rotateAngleX = 0.0F;
/*  51 */     this.bipedBody.rotationPointY = var8;
/*  52 */     this.bipedBody.rotationPointZ = -0.0F;
/*  53 */     this.bipedRightLeg.rotateAngleX -= 0.0F;
/*  54 */     this.bipedLeftLeg.rotateAngleX -= 0.0F;
/*  55 */     this.bipedRightArm.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX * 0.5D);
/*  56 */     this.bipedLeftArm.rotateAngleX = (float)(this.bipedLeftArm.rotateAngleX * 0.5D);
/*  57 */     this.bipedRightLeg.rotateAngleX = (float)(this.bipedRightLeg.rotateAngleX * 0.5D);
/*  58 */     this.bipedLeftLeg.rotateAngleX = (float)(this.bipedLeftLeg.rotateAngleX * 0.5D);
/*  59 */     float var9 = 0.4F;
/*     */     
/*  61 */     if (this.bipedRightArm.rotateAngleX > var9)
/*     */     {
/*  63 */       this.bipedRightArm.rotateAngleX = var9;
/*     */     }
/*     */     
/*  66 */     if (this.bipedLeftArm.rotateAngleX > var9)
/*     */     {
/*  68 */       this.bipedLeftArm.rotateAngleX = var9;
/*     */     }
/*     */     
/*  71 */     if (this.bipedRightArm.rotateAngleX < -var9)
/*     */     {
/*  73 */       this.bipedRightArm.rotateAngleX = -var9;
/*     */     }
/*     */     
/*  76 */     if (this.bipedLeftArm.rotateAngleX < -var9)
/*     */     {
/*  78 */       this.bipedLeftArm.rotateAngleX = -var9;
/*     */     }
/*     */     
/*  81 */     if (this.bipedRightLeg.rotateAngleX > var9)
/*     */     {
/*  83 */       this.bipedRightLeg.rotateAngleX = var9;
/*     */     }
/*     */     
/*  86 */     if (this.bipedLeftLeg.rotateAngleX > var9)
/*     */     {
/*  88 */       this.bipedLeftLeg.rotateAngleX = var9;
/*     */     }
/*     */     
/*  91 */     if (this.bipedRightLeg.rotateAngleX < -var9)
/*     */     {
/*  93 */       this.bipedRightLeg.rotateAngleX = -var9;
/*     */     }
/*     */     
/*  96 */     if (this.bipedLeftLeg.rotateAngleX < -var9)
/*     */     {
/*  98 */       this.bipedLeftLeg.rotateAngleX = -var9;
/*     */     }
/*     */     
/* 101 */     if (this.isCarrying) {
/*     */       
/* 103 */       this.bipedRightArm.rotateAngleX = -0.5F;
/* 104 */       this.bipedLeftArm.rotateAngleX = -0.5F;
/* 105 */       this.bipedRightArm.rotateAngleZ = 0.05F;
/* 106 */       this.bipedLeftArm.rotateAngleZ = -0.05F;
/*     */     } 
/*     */     
/* 109 */     this.bipedRightArm.rotationPointZ = 0.0F;
/* 110 */     this.bipedLeftArm.rotationPointZ = 0.0F;
/* 111 */     this.bipedRightLeg.rotationPointZ = 0.0F;
/* 112 */     this.bipedLeftLeg.rotationPointZ = 0.0F;
/* 113 */     this.bipedRightLeg.rotationPointY = 9.0F + var8;
/* 114 */     this.bipedLeftLeg.rotationPointY = 9.0F + var8;
/* 115 */     this.bipedHead.rotationPointZ = -0.0F;
/* 116 */     this.bipedHead.rotationPointY = var8 + 1.0F;
/* 117 */     this.bipedHeadwear.rotationPointX = this.bipedHead.rotationPointX;
/* 118 */     this.bipedHeadwear.rotationPointY = this.bipedHead.rotationPointY;
/* 119 */     this.bipedHeadwear.rotationPointZ = this.bipedHead.rotationPointZ;
/* 120 */     this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
/* 121 */     this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
/* 122 */     this.bipedHeadwear.rotateAngleZ = this.bipedHead.rotateAngleZ;
/*     */     
/* 124 */     if (this.isAttacking) {
/*     */       
/* 126 */       float var10 = 1.0F;
/* 127 */       this.bipedHead.rotationPointY -= var10 * 5.0F;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelEnderman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */