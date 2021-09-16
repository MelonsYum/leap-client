/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelSpider
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer spiderHead;
/*     */   public ModelRenderer spiderNeck;
/*     */   public ModelRenderer spiderBody;
/*     */   public ModelRenderer spiderLeg1;
/*     */   public ModelRenderer spiderLeg2;
/*     */   public ModelRenderer spiderLeg3;
/*     */   public ModelRenderer spiderLeg4;
/*     */   public ModelRenderer spiderLeg5;
/*     */   public ModelRenderer spiderLeg6;
/*     */   public ModelRenderer spiderLeg7;
/*     */   public ModelRenderer spiderLeg8;
/*     */   private static final String __OBFID = "CL_00000860";
/*     */   
/*     */   public ModelSpider() {
/*  44 */     float var1 = 0.0F;
/*  45 */     byte var2 = 15;
/*  46 */     this.spiderHead = new ModelRenderer(this, 32, 4);
/*  47 */     this.spiderHead.addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, var1);
/*  48 */     this.spiderHead.setRotationPoint(0.0F, var2, -3.0F);
/*  49 */     this.spiderNeck = new ModelRenderer(this, 0, 0);
/*  50 */     this.spiderNeck.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, var1);
/*  51 */     this.spiderNeck.setRotationPoint(0.0F, var2, 0.0F);
/*  52 */     this.spiderBody = new ModelRenderer(this, 0, 12);
/*  53 */     this.spiderBody.addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, var1);
/*  54 */     this.spiderBody.setRotationPoint(0.0F, var2, 9.0F);
/*  55 */     this.spiderLeg1 = new ModelRenderer(this, 18, 0);
/*  56 */     this.spiderLeg1.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  57 */     this.spiderLeg1.setRotationPoint(-4.0F, var2, 2.0F);
/*  58 */     this.spiderLeg2 = new ModelRenderer(this, 18, 0);
/*  59 */     this.spiderLeg2.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  60 */     this.spiderLeg2.setRotationPoint(4.0F, var2, 2.0F);
/*  61 */     this.spiderLeg3 = new ModelRenderer(this, 18, 0);
/*  62 */     this.spiderLeg3.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  63 */     this.spiderLeg3.setRotationPoint(-4.0F, var2, 1.0F);
/*  64 */     this.spiderLeg4 = new ModelRenderer(this, 18, 0);
/*  65 */     this.spiderLeg4.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  66 */     this.spiderLeg4.setRotationPoint(4.0F, var2, 1.0F);
/*  67 */     this.spiderLeg5 = new ModelRenderer(this, 18, 0);
/*  68 */     this.spiderLeg5.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  69 */     this.spiderLeg5.setRotationPoint(-4.0F, var2, 0.0F);
/*  70 */     this.spiderLeg6 = new ModelRenderer(this, 18, 0);
/*  71 */     this.spiderLeg6.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  72 */     this.spiderLeg6.setRotationPoint(4.0F, var2, 0.0F);
/*  73 */     this.spiderLeg7 = new ModelRenderer(this, 18, 0);
/*  74 */     this.spiderLeg7.addBox(-15.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  75 */     this.spiderLeg7.setRotationPoint(-4.0F, var2, -1.0F);
/*  76 */     this.spiderLeg8 = new ModelRenderer(this, 18, 0);
/*  77 */     this.spiderLeg8.addBox(-1.0F, -1.0F, -1.0F, 16, 2, 2, var1);
/*  78 */     this.spiderLeg8.setRotationPoint(4.0F, var2, -1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  86 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*  87 */     this.spiderHead.render(p_78088_7_);
/*  88 */     this.spiderNeck.render(p_78088_7_);
/*  89 */     this.spiderBody.render(p_78088_7_);
/*  90 */     this.spiderLeg1.render(p_78088_7_);
/*  91 */     this.spiderLeg2.render(p_78088_7_);
/*  92 */     this.spiderLeg3.render(p_78088_7_);
/*  93 */     this.spiderLeg4.render(p_78088_7_);
/*  94 */     this.spiderLeg5.render(p_78088_7_);
/*  95 */     this.spiderLeg6.render(p_78088_7_);
/*  96 */     this.spiderLeg7.render(p_78088_7_);
/*  97 */     this.spiderLeg8.render(p_78088_7_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 107 */     this.spiderHead.rotateAngleY = p_78087_4_ / 57.295776F;
/* 108 */     this.spiderHead.rotateAngleX = p_78087_5_ / 57.295776F;
/* 109 */     float var8 = 0.7853982F;
/* 110 */     this.spiderLeg1.rotateAngleZ = -var8;
/* 111 */     this.spiderLeg2.rotateAngleZ = var8;
/* 112 */     this.spiderLeg3.rotateAngleZ = -var8 * 0.74F;
/* 113 */     this.spiderLeg4.rotateAngleZ = var8 * 0.74F;
/* 114 */     this.spiderLeg5.rotateAngleZ = -var8 * 0.74F;
/* 115 */     this.spiderLeg6.rotateAngleZ = var8 * 0.74F;
/* 116 */     this.spiderLeg7.rotateAngleZ = -var8;
/* 117 */     this.spiderLeg8.rotateAngleZ = var8;
/* 118 */     float var9 = -0.0F;
/* 119 */     float var10 = 0.3926991F;
/* 120 */     this.spiderLeg1.rotateAngleY = var10 * 2.0F + var9;
/* 121 */     this.spiderLeg2.rotateAngleY = -var10 * 2.0F - var9;
/* 122 */     this.spiderLeg3.rotateAngleY = var10 * 1.0F + var9;
/* 123 */     this.spiderLeg4.rotateAngleY = -var10 * 1.0F - var9;
/* 124 */     this.spiderLeg5.rotateAngleY = -var10 * 1.0F + var9;
/* 125 */     this.spiderLeg6.rotateAngleY = var10 * 1.0F - var9;
/* 126 */     this.spiderLeg7.rotateAngleY = -var10 * 2.0F + var9;
/* 127 */     this.spiderLeg8.rotateAngleY = var10 * 2.0F - var9;
/* 128 */     float var11 = -(MathHelper.cos(p_78087_1_ * 0.6662F * 2.0F + 0.0F) * 0.4F) * p_78087_2_;
/* 129 */     float var12 = -(MathHelper.cos(p_78087_1_ * 0.6662F * 2.0F + 3.1415927F) * 0.4F) * p_78087_2_;
/* 130 */     float var13 = -(MathHelper.cos(p_78087_1_ * 0.6662F * 2.0F + 1.5707964F) * 0.4F) * p_78087_2_;
/* 131 */     float var14 = -(MathHelper.cos(p_78087_1_ * 0.6662F * 2.0F + 4.712389F) * 0.4F) * p_78087_2_;
/* 132 */     float var15 = Math.abs(MathHelper.sin(p_78087_1_ * 0.6662F + 0.0F) * 0.4F) * p_78087_2_;
/* 133 */     float var16 = Math.abs(MathHelper.sin(p_78087_1_ * 0.6662F + 3.1415927F) * 0.4F) * p_78087_2_;
/* 134 */     float var17 = Math.abs(MathHelper.sin(p_78087_1_ * 0.6662F + 1.5707964F) * 0.4F) * p_78087_2_;
/* 135 */     float var18 = Math.abs(MathHelper.sin(p_78087_1_ * 0.6662F + 4.712389F) * 0.4F) * p_78087_2_;
/* 136 */     this.spiderLeg1.rotateAngleY += var11;
/* 137 */     this.spiderLeg2.rotateAngleY += -var11;
/* 138 */     this.spiderLeg3.rotateAngleY += var12;
/* 139 */     this.spiderLeg4.rotateAngleY += -var12;
/* 140 */     this.spiderLeg5.rotateAngleY += var13;
/* 141 */     this.spiderLeg6.rotateAngleY += -var13;
/* 142 */     this.spiderLeg7.rotateAngleY += var14;
/* 143 */     this.spiderLeg8.rotateAngleY += -var14;
/* 144 */     this.spiderLeg1.rotateAngleZ += var15;
/* 145 */     this.spiderLeg2.rotateAngleZ += -var15;
/* 146 */     this.spiderLeg3.rotateAngleZ += var16;
/* 147 */     this.spiderLeg4.rotateAngleZ += -var16;
/* 148 */     this.spiderLeg5.rotateAngleZ += var17;
/* 149 */     this.spiderLeg6.rotateAngleZ += -var17;
/* 150 */     this.spiderLeg7.rotateAngleZ += var18;
/* 151 */     this.spiderLeg8.rotateAngleZ += -var18;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */