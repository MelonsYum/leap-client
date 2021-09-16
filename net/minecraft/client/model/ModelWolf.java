/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityWolf;
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
/*     */ public class ModelWolf
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer wolfHeadMain;
/*     */   public ModelRenderer wolfBody;
/*     */   public ModelRenderer wolfLeg1;
/*     */   public ModelRenderer wolfLeg2;
/*     */   public ModelRenderer wolfLeg3;
/*     */   public ModelRenderer wolfLeg4;
/*     */   ModelRenderer wolfTail;
/*     */   ModelRenderer wolfMane;
/*     */   private static final String __OBFID = "CL_00000868";
/*     */   
/*     */   public ModelWolf() {
/*  38 */     float var1 = 0.0F;
/*  39 */     float var2 = 13.5F;
/*  40 */     this.wolfHeadMain = new ModelRenderer(this, 0, 0);
/*  41 */     this.wolfHeadMain.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, var1);
/*  42 */     this.wolfHeadMain.setRotationPoint(-1.0F, var2, -7.0F);
/*  43 */     this.wolfBody = new ModelRenderer(this, 18, 14);
/*  44 */     this.wolfBody.addBox(-4.0F, -2.0F, -3.0F, 6, 9, 6, var1);
/*  45 */     this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
/*  46 */     this.wolfMane = new ModelRenderer(this, 21, 0);
/*  47 */     this.wolfMane.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 7, var1);
/*  48 */     this.wolfMane.setRotationPoint(-1.0F, 14.0F, 2.0F);
/*  49 */     this.wolfLeg1 = new ModelRenderer(this, 0, 18);
/*  50 */     this.wolfLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  51 */     this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
/*  52 */     this.wolfLeg2 = new ModelRenderer(this, 0, 18);
/*  53 */     this.wolfLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  54 */     this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
/*  55 */     this.wolfLeg3 = new ModelRenderer(this, 0, 18);
/*  56 */     this.wolfLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  57 */     this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
/*  58 */     this.wolfLeg4 = new ModelRenderer(this, 0, 18);
/*  59 */     this.wolfLeg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  60 */     this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
/*  61 */     this.wolfTail = new ModelRenderer(this, 9, 18);
/*  62 */     this.wolfTail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, var1);
/*  63 */     this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
/*  64 */     this.wolfHeadMain.setTextureOffset(16, 14).addBox(-3.0F, -5.0F, 0.0F, 2, 2, 1, var1);
/*  65 */     this.wolfHeadMain.setTextureOffset(16, 14).addBox(1.0F, -5.0F, 0.0F, 2, 2, 1, var1);
/*  66 */     this.wolfHeadMain.setTextureOffset(0, 10).addBox(-1.5F, 0.0F, -5.0F, 3, 3, 4, var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  74 */     super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
/*  75 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*     */     
/*  77 */     if (this.isChild) {
/*     */       
/*  79 */       float var8 = 2.0F;
/*  80 */       GlStateManager.pushMatrix();
/*  81 */       GlStateManager.translate(0.0F, 5.0F * p_78088_7_, 2.0F * p_78088_7_);
/*  82 */       this.wolfHeadMain.renderWithRotation(p_78088_7_);
/*  83 */       GlStateManager.popMatrix();
/*  84 */       GlStateManager.pushMatrix();
/*  85 */       GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
/*  86 */       GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
/*  87 */       this.wolfBody.render(p_78088_7_);
/*  88 */       this.wolfLeg1.render(p_78088_7_);
/*  89 */       this.wolfLeg2.render(p_78088_7_);
/*  90 */       this.wolfLeg3.render(p_78088_7_);
/*  91 */       this.wolfLeg4.render(p_78088_7_);
/*  92 */       this.wolfTail.renderWithRotation(p_78088_7_);
/*  93 */       this.wolfMane.render(p_78088_7_);
/*  94 */       GlStateManager.popMatrix();
/*     */     }
/*     */     else {
/*     */       
/*  98 */       this.wolfHeadMain.renderWithRotation(p_78088_7_);
/*  99 */       this.wolfBody.render(p_78088_7_);
/* 100 */       this.wolfLeg1.render(p_78088_7_);
/* 101 */       this.wolfLeg2.render(p_78088_7_);
/* 102 */       this.wolfLeg3.render(p_78088_7_);
/* 103 */       this.wolfLeg4.render(p_78088_7_);
/* 104 */       this.wolfTail.renderWithRotation(p_78088_7_);
/* 105 */       this.wolfMane.render(p_78088_7_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 115 */     EntityWolf var5 = (EntityWolf)p_78086_1_;
/*     */     
/* 117 */     if (var5.isAngry()) {
/*     */       
/* 119 */       this.wolfTail.rotateAngleY = 0.0F;
/*     */     }
/*     */     else {
/*     */       
/* 123 */       this.wolfTail.rotateAngleY = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
/*     */     } 
/*     */     
/* 126 */     if (var5.isSitting()) {
/*     */       
/* 128 */       this.wolfMane.setRotationPoint(-1.0F, 16.0F, -3.0F);
/* 129 */       this.wolfMane.rotateAngleX = 1.2566371F;
/* 130 */       this.wolfMane.rotateAngleY = 0.0F;
/* 131 */       this.wolfBody.setRotationPoint(0.0F, 18.0F, 0.0F);
/* 132 */       this.wolfBody.rotateAngleX = 0.7853982F;
/* 133 */       this.wolfTail.setRotationPoint(-1.0F, 21.0F, 6.0F);
/* 134 */       this.wolfLeg1.setRotationPoint(-2.5F, 22.0F, 2.0F);
/* 135 */       this.wolfLeg1.rotateAngleX = 4.712389F;
/* 136 */       this.wolfLeg2.setRotationPoint(0.5F, 22.0F, 2.0F);
/* 137 */       this.wolfLeg2.rotateAngleX = 4.712389F;
/* 138 */       this.wolfLeg3.rotateAngleX = 5.811947F;
/* 139 */       this.wolfLeg3.setRotationPoint(-2.49F, 17.0F, -4.0F);
/* 140 */       this.wolfLeg4.rotateAngleX = 5.811947F;
/* 141 */       this.wolfLeg4.setRotationPoint(0.51F, 17.0F, -4.0F);
/*     */     }
/*     */     else {
/*     */       
/* 145 */       this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
/* 146 */       this.wolfBody.rotateAngleX = 1.5707964F;
/* 147 */       this.wolfMane.setRotationPoint(-1.0F, 14.0F, -3.0F);
/* 148 */       this.wolfMane.rotateAngleX = this.wolfBody.rotateAngleX;
/* 149 */       this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
/* 150 */       this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
/* 151 */       this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
/* 152 */       this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
/* 153 */       this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
/* 154 */       this.wolfLeg1.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
/* 155 */       this.wolfLeg2.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + 3.1415927F) * 1.4F * p_78086_3_;
/* 156 */       this.wolfLeg3.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F + 3.1415927F) * 1.4F * p_78086_3_;
/* 157 */       this.wolfLeg4.rotateAngleX = MathHelper.cos(p_78086_2_ * 0.6662F) * 1.4F * p_78086_3_;
/*     */     } 
/*     */     
/* 160 */     this.wolfHeadMain.rotateAngleZ = var5.getInterestedAngle(p_78086_4_) + var5.getShakeAngle(p_78086_4_, 0.0F);
/* 161 */     this.wolfMane.rotateAngleZ = var5.getShakeAngle(p_78086_4_, -0.08F);
/* 162 */     this.wolfBody.rotateAngleZ = var5.getShakeAngle(p_78086_4_, -0.16F);
/* 163 */     this.wolfTail.rotateAngleZ = var5.getShakeAngle(p_78086_4_, -0.2F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 173 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 174 */     this.wolfHeadMain.rotateAngleX = p_78087_5_ / 57.295776F;
/* 175 */     this.wolfHeadMain.rotateAngleY = p_78087_4_ / 57.295776F;
/* 176 */     this.wolfTail.rotateAngleX = p_78087_3_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */