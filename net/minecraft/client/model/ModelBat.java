/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.passive.EntityBat;
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
/*     */ public class ModelBat
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer batHead;
/*     */   private ModelRenderer batBody;
/*     */   private ModelRenderer batRightWing;
/*     */   private ModelRenderer batLeftWing;
/*     */   private ModelRenderer batOuterRightWing;
/*     */   private ModelRenderer batOuterLeftWing;
/*     */   private static final String __OBFID = "CL_00000830";
/*     */   
/*     */   public ModelBat() {
/*  29 */     this.textureWidth = 64;
/*  30 */     this.textureHeight = 64;
/*  31 */     this.batHead = new ModelRenderer(this, 0, 0);
/*  32 */     this.batHead.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
/*  33 */     ModelRenderer var1 = new ModelRenderer(this, 24, 0);
/*  34 */     var1.addBox(-4.0F, -6.0F, -2.0F, 3, 4, 1);
/*  35 */     this.batHead.addChild(var1);
/*  36 */     ModelRenderer var2 = new ModelRenderer(this, 24, 0);
/*  37 */     var2.mirror = true;
/*  38 */     var2.addBox(1.0F, -6.0F, -2.0F, 3, 4, 1);
/*  39 */     this.batHead.addChild(var2);
/*  40 */     this.batBody = new ModelRenderer(this, 0, 16);
/*  41 */     this.batBody.addBox(-3.0F, 4.0F, -3.0F, 6, 12, 6);
/*  42 */     this.batBody.setTextureOffset(0, 34).addBox(-5.0F, 16.0F, 0.0F, 10, 6, 1);
/*  43 */     this.batRightWing = new ModelRenderer(this, 42, 0);
/*  44 */     this.batRightWing.addBox(-12.0F, 1.0F, 1.5F, 10, 16, 1);
/*  45 */     this.batOuterRightWing = new ModelRenderer(this, 24, 16);
/*  46 */     this.batOuterRightWing.setRotationPoint(-12.0F, 1.0F, 1.5F);
/*  47 */     this.batOuterRightWing.addBox(-8.0F, 1.0F, 0.0F, 8, 12, 1);
/*  48 */     this.batLeftWing = new ModelRenderer(this, 42, 0);
/*  49 */     this.batLeftWing.mirror = true;
/*  50 */     this.batLeftWing.addBox(2.0F, 1.0F, 1.5F, 10, 16, 1);
/*  51 */     this.batOuterLeftWing = new ModelRenderer(this, 24, 16);
/*  52 */     this.batOuterLeftWing.mirror = true;
/*  53 */     this.batOuterLeftWing.setRotationPoint(12.0F, 1.0F, 1.5F);
/*  54 */     this.batOuterLeftWing.addBox(0.0F, 1.0F, 0.0F, 8, 12, 1);
/*  55 */     this.batBody.addChild(this.batRightWing);
/*  56 */     this.batBody.addChild(this.batLeftWing);
/*  57 */     this.batRightWing.addChild(this.batOuterRightWing);
/*  58 */     this.batLeftWing.addChild(this.batOuterLeftWing);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  66 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*  67 */     this.batHead.render(p_78088_7_);
/*  68 */     this.batBody.render(p_78088_7_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/*  80 */     if (((EntityBat)p_78087_7_).getIsBatHanging()) {
/*     */       
/*  82 */       float var8 = 57.295776F;
/*  83 */       this.batHead.rotateAngleX = p_78087_5_ / 57.295776F;
/*  84 */       this.batHead.rotateAngleY = 3.1415927F - p_78087_4_ / 57.295776F;
/*  85 */       this.batHead.rotateAngleZ = 3.1415927F;
/*  86 */       this.batHead.setRotationPoint(0.0F, -2.0F, 0.0F);
/*  87 */       this.batRightWing.setRotationPoint(-3.0F, 0.0F, 3.0F);
/*  88 */       this.batLeftWing.setRotationPoint(3.0F, 0.0F, 3.0F);
/*  89 */       this.batBody.rotateAngleX = 3.1415927F;
/*  90 */       this.batRightWing.rotateAngleX = -0.15707964F;
/*  91 */       this.batRightWing.rotateAngleY = -1.2566371F;
/*  92 */       this.batOuterRightWing.rotateAngleY = -1.7278761F;
/*  93 */       this.batLeftWing.rotateAngleX = this.batRightWing.rotateAngleX;
/*  94 */       this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
/*  95 */       this.batOuterLeftWing.rotateAngleY = -this.batOuterRightWing.rotateAngleY;
/*     */     }
/*     */     else {
/*     */       
/*  99 */       float var8 = 57.295776F;
/* 100 */       this.batHead.rotateAngleX = p_78087_5_ / 57.295776F;
/* 101 */       this.batHead.rotateAngleY = p_78087_4_ / 57.295776F;
/* 102 */       this.batHead.rotateAngleZ = 0.0F;
/* 103 */       this.batHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 104 */       this.batRightWing.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 105 */       this.batLeftWing.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 106 */       this.batBody.rotateAngleX = 0.7853982F + MathHelper.cos(p_78087_3_ * 0.1F) * 0.15F;
/* 107 */       this.batBody.rotateAngleY = 0.0F;
/* 108 */       this.batRightWing.rotateAngleY = MathHelper.cos(p_78087_3_ * 1.3F) * 3.1415927F * 0.25F;
/* 109 */       this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
/* 110 */       this.batRightWing.rotateAngleY *= 0.5F;
/* 111 */       this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5F;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelBat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */