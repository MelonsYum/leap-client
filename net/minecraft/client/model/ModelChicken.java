/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class ModelChicken
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer head;
/*     */   public ModelRenderer body;
/*     */   public ModelRenderer rightLeg;
/*     */   public ModelRenderer leftLeg;
/*     */   public ModelRenderer rightWing;
/*     */   public ModelRenderer leftWing;
/*     */   public ModelRenderer bill;
/*     */   public ModelRenderer chin;
/*     */   private static final String __OBFID = "CL_00000835";
/*     */   
/*     */   public ModelChicken() {
/*  21 */     byte var1 = 16;
/*  22 */     this.head = new ModelRenderer(this, 0, 0);
/*  23 */     this.head.addBox(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
/*  24 */     this.head.setRotationPoint(0.0F, (-1 + var1), -4.0F);
/*  25 */     this.bill = new ModelRenderer(this, 14, 0);
/*  26 */     this.bill.addBox(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
/*  27 */     this.bill.setRotationPoint(0.0F, (-1 + var1), -4.0F);
/*  28 */     this.chin = new ModelRenderer(this, 14, 4);
/*  29 */     this.chin.addBox(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
/*  30 */     this.chin.setRotationPoint(0.0F, (-1 + var1), -4.0F);
/*  31 */     this.body = new ModelRenderer(this, 0, 9);
/*  32 */     this.body.addBox(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
/*  33 */     this.body.setRotationPoint(0.0F, var1, 0.0F);
/*  34 */     this.rightLeg = new ModelRenderer(this, 26, 0);
/*  35 */     this.rightLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
/*  36 */     this.rightLeg.setRotationPoint(-2.0F, (3 + var1), 1.0F);
/*  37 */     this.leftLeg = new ModelRenderer(this, 26, 0);
/*  38 */     this.leftLeg.addBox(-1.0F, 0.0F, -3.0F, 3, 5, 3);
/*  39 */     this.leftLeg.setRotationPoint(1.0F, (3 + var1), 1.0F);
/*  40 */     this.rightWing = new ModelRenderer(this, 24, 13);
/*  41 */     this.rightWing.addBox(0.0F, 0.0F, -3.0F, 1, 4, 6);
/*  42 */     this.rightWing.setRotationPoint(-4.0F, (-3 + var1), 0.0F);
/*  43 */     this.leftWing = new ModelRenderer(this, 24, 13);
/*  44 */     this.leftWing.addBox(-1.0F, 0.0F, -3.0F, 1, 4, 6);
/*  45 */     this.leftWing.setRotationPoint(4.0F, (-3 + var1), 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  53 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*     */     
/*  55 */     if (this.isChild) {
/*     */       
/*  57 */       float var8 = 2.0F;
/*  58 */       GlStateManager.pushMatrix();
/*  59 */       GlStateManager.translate(0.0F, 5.0F * p_78088_7_, 2.0F * p_78088_7_);
/*  60 */       this.head.render(p_78088_7_);
/*  61 */       this.bill.render(p_78088_7_);
/*  62 */       this.chin.render(p_78088_7_);
/*  63 */       GlStateManager.popMatrix();
/*  64 */       GlStateManager.pushMatrix();
/*  65 */       GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
/*  66 */       GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
/*  67 */       this.body.render(p_78088_7_);
/*  68 */       this.rightLeg.render(p_78088_7_);
/*  69 */       this.leftLeg.render(p_78088_7_);
/*  70 */       this.rightWing.render(p_78088_7_);
/*  71 */       this.leftWing.render(p_78088_7_);
/*  72 */       GlStateManager.popMatrix();
/*     */     }
/*     */     else {
/*     */       
/*  76 */       this.head.render(p_78088_7_);
/*  77 */       this.bill.render(p_78088_7_);
/*  78 */       this.chin.render(p_78088_7_);
/*  79 */       this.body.render(p_78088_7_);
/*  80 */       this.rightLeg.render(p_78088_7_);
/*  81 */       this.leftLeg.render(p_78088_7_);
/*  82 */       this.rightWing.render(p_78088_7_);
/*  83 */       this.leftWing.render(p_78088_7_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/*  94 */     this.head.rotateAngleX = p_78087_5_ / 57.295776F;
/*  95 */     this.head.rotateAngleY = p_78087_4_ / 57.295776F;
/*  96 */     this.bill.rotateAngleX = this.head.rotateAngleX;
/*  97 */     this.bill.rotateAngleY = this.head.rotateAngleY;
/*  98 */     this.chin.rotateAngleX = this.head.rotateAngleX;
/*  99 */     this.chin.rotateAngleY = this.head.rotateAngleY;
/* 100 */     this.body.rotateAngleX = 1.5707964F;
/* 101 */     this.rightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
/* 102 */     this.leftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_;
/* 103 */     this.rightWing.rotateAngleZ = p_78087_3_;
/* 104 */     this.leftWing.rotateAngleZ = -p_78087_3_;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelChicken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */