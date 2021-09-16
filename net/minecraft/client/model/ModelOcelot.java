/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityOcelot;
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
/*     */ public class ModelOcelot
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer ocelotBackLeftLeg;
/*     */   ModelRenderer ocelotBackRightLeg;
/*     */   ModelRenderer ocelotFrontLeftLeg;
/*     */   ModelRenderer ocelotFrontRightLeg;
/*     */   ModelRenderer ocelotTail;
/*     */   ModelRenderer ocelotTail2;
/*     */   ModelRenderer ocelotHead;
/*     */   ModelRenderer ocelotBody;
/*  34 */   int field_78163_i = 1;
/*     */   
/*     */   private static final String __OBFID = "CL_00000848";
/*     */   
/*     */   public ModelOcelot() {
/*  39 */     setTextureOffset("head.main", 0, 0);
/*  40 */     setTextureOffset("head.nose", 0, 24);
/*  41 */     setTextureOffset("head.ear1", 0, 10);
/*  42 */     setTextureOffset("head.ear2", 6, 10);
/*  43 */     this.ocelotHead = new ModelRenderer(this, "head");
/*  44 */     this.ocelotHead.addBox("main", -2.5F, -2.0F, -3.0F, 5, 4, 5);
/*  45 */     this.ocelotHead.addBox("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2);
/*  46 */     this.ocelotHead.addBox("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2);
/*  47 */     this.ocelotHead.addBox("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2);
/*  48 */     this.ocelotHead.setRotationPoint(0.0F, 15.0F, -9.0F);
/*  49 */     this.ocelotBody = new ModelRenderer(this, 20, 0);
/*  50 */     this.ocelotBody.addBox(-2.0F, 3.0F, -8.0F, 4, 16, 6, 0.0F);
/*  51 */     this.ocelotBody.setRotationPoint(0.0F, 12.0F, -10.0F);
/*  52 */     this.ocelotTail = new ModelRenderer(this, 0, 15);
/*  53 */     this.ocelotTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
/*  54 */     this.ocelotTail.rotateAngleX = 0.9F;
/*  55 */     this.ocelotTail.setRotationPoint(0.0F, 15.0F, 8.0F);
/*  56 */     this.ocelotTail2 = new ModelRenderer(this, 4, 15);
/*  57 */     this.ocelotTail2.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
/*  58 */     this.ocelotTail2.setRotationPoint(0.0F, 20.0F, 14.0F);
/*  59 */     this.ocelotBackLeftLeg = new ModelRenderer(this, 8, 13);
/*  60 */     this.ocelotBackLeftLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
/*  61 */     this.ocelotBackLeftLeg.setRotationPoint(1.1F, 18.0F, 5.0F);
/*  62 */     this.ocelotBackRightLeg = new ModelRenderer(this, 8, 13);
/*  63 */     this.ocelotBackRightLeg.addBox(-1.0F, 0.0F, 1.0F, 2, 6, 2);
/*  64 */     this.ocelotBackRightLeg.setRotationPoint(-1.1F, 18.0F, 5.0F);
/*  65 */     this.ocelotFrontLeftLeg = new ModelRenderer(this, 40, 0);
/*  66 */     this.ocelotFrontLeftLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
/*  67 */     this.ocelotFrontLeftLeg.setRotationPoint(1.2F, 13.8F, -5.0F);
/*  68 */     this.ocelotFrontRightLeg = new ModelRenderer(this, 40, 0);
/*  69 */     this.ocelotFrontRightLeg.addBox(-1.0F, 0.0F, 0.0F, 2, 10, 2);
/*  70 */     this.ocelotFrontRightLeg.setRotationPoint(-1.2F, 13.8F, -5.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  78 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*     */     
/*  80 */     if (this.isChild) {
/*     */       
/*  82 */       float var8 = 2.0F;
/*  83 */       GlStateManager.pushMatrix();
/*  84 */       GlStateManager.scale(1.5F / var8, 1.5F / var8, 1.5F / var8);
/*  85 */       GlStateManager.translate(0.0F, 10.0F * p_78088_7_, 4.0F * p_78088_7_);
/*  86 */       this.ocelotHead.render(p_78088_7_);
/*  87 */       GlStateManager.popMatrix();
/*  88 */       GlStateManager.pushMatrix();
/*  89 */       GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
/*  90 */       GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
/*  91 */       this.ocelotBody.render(p_78088_7_);
/*  92 */       this.ocelotBackLeftLeg.render(p_78088_7_);
/*  93 */       this.ocelotBackRightLeg.render(p_78088_7_);
/*  94 */       this.ocelotFrontLeftLeg.render(p_78088_7_);
/*  95 */       this.ocelotFrontRightLeg.render(p_78088_7_);
/*  96 */       this.ocelotTail.render(p_78088_7_);
/*  97 */       this.ocelotTail2.render(p_78088_7_);
/*  98 */       GlStateManager.popMatrix();
/*     */     }
/*     */     else {
/*     */       
/* 102 */       this.ocelotHead.render(p_78088_7_);
/* 103 */       this.ocelotBody.render(p_78088_7_);
/* 104 */       this.ocelotTail.render(p_78088_7_);
/* 105 */       this.ocelotTail2.render(p_78088_7_);
/* 106 */       this.ocelotBackLeftLeg.render(p_78088_7_);
/* 107 */       this.ocelotBackRightLeg.render(p_78088_7_);
/* 108 */       this.ocelotFrontLeftLeg.render(p_78088_7_);
/* 109 */       this.ocelotFrontRightLeg.render(p_78088_7_);
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
/* 120 */     this.ocelotHead.rotateAngleX = p_78087_5_ / 57.295776F;
/* 121 */     this.ocelotHead.rotateAngleY = p_78087_4_ / 57.295776F;
/*     */     
/* 123 */     if (this.field_78163_i != 3) {
/*     */       
/* 125 */       this.ocelotBody.rotateAngleX = 1.5707964F;
/*     */       
/* 127 */       if (this.field_78163_i == 2) {
/*     */         
/* 129 */         this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.0F * p_78087_2_;
/* 130 */         this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 0.3F) * 1.0F * p_78087_2_;
/* 131 */         this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F + 0.3F) * 1.0F * p_78087_2_;
/* 132 */         this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.0F * p_78087_2_;
/* 133 */         this.ocelotTail2.rotateAngleX = 1.7278761F + 0.31415927F * MathHelper.cos(p_78087_1_) * p_78087_2_;
/*     */       }
/*     */       else {
/*     */         
/* 137 */         this.ocelotBackLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.0F * p_78087_2_;
/* 138 */         this.ocelotBackRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.0F * p_78087_2_;
/* 139 */         this.ocelotFrontLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.0F * p_78087_2_;
/* 140 */         this.ocelotFrontRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.0F * p_78087_2_;
/*     */         
/* 142 */         if (this.field_78163_i == 1) {
/*     */           
/* 144 */           this.ocelotTail2.rotateAngleX = 1.7278761F + 0.7853982F * MathHelper.cos(p_78087_1_) * p_78087_2_;
/*     */         }
/*     */         else {
/*     */           
/* 148 */           this.ocelotTail2.rotateAngleX = 1.7278761F + 0.47123894F * MathHelper.cos(p_78087_1_) * p_78087_2_;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 160 */     EntityOcelot var5 = (EntityOcelot)p_78086_1_;
/* 161 */     this.ocelotBody.rotationPointY = 12.0F;
/* 162 */     this.ocelotBody.rotationPointZ = -10.0F;
/* 163 */     this.ocelotHead.rotationPointY = 15.0F;
/* 164 */     this.ocelotHead.rotationPointZ = -9.0F;
/* 165 */     this.ocelotTail.rotationPointY = 15.0F;
/* 166 */     this.ocelotTail.rotationPointZ = 8.0F;
/* 167 */     this.ocelotTail2.rotationPointY = 20.0F;
/* 168 */     this.ocelotTail2.rotationPointZ = 14.0F;
/* 169 */     this.ocelotFrontRightLeg.rotationPointY = 13.8F;
/* 170 */     this.ocelotFrontRightLeg.rotationPointZ = -5.0F;
/* 171 */     this.ocelotBackRightLeg.rotationPointY = 18.0F;
/* 172 */     this.ocelotBackRightLeg.rotationPointZ = 5.0F;
/* 173 */     this.ocelotTail.rotateAngleX = 0.9F;
/*     */     
/* 175 */     if (var5.isSneaking()) {
/*     */       
/* 177 */       this.ocelotBody.rotationPointY++;
/* 178 */       this.ocelotHead.rotationPointY += 2.0F;
/* 179 */       this.ocelotTail.rotationPointY++;
/* 180 */       this.ocelotTail2.rotationPointY += -4.0F;
/* 181 */       this.ocelotTail2.rotationPointZ += 2.0F;
/* 182 */       this.ocelotTail.rotateAngleX = 1.5707964F;
/* 183 */       this.ocelotTail2.rotateAngleX = 1.5707964F;
/* 184 */       this.field_78163_i = 0;
/*     */     }
/* 186 */     else if (var5.isSprinting()) {
/*     */       
/* 188 */       this.ocelotTail2.rotationPointY = this.ocelotTail.rotationPointY;
/* 189 */       this.ocelotTail2.rotationPointZ += 2.0F;
/* 190 */       this.ocelotTail.rotateAngleX = 1.5707964F;
/* 191 */       this.ocelotTail2.rotateAngleX = 1.5707964F;
/* 192 */       this.field_78163_i = 2;
/*     */     }
/* 194 */     else if (var5.isSitting()) {
/*     */       
/* 196 */       this.ocelotBody.rotateAngleX = 0.7853982F;
/* 197 */       this.ocelotBody.rotationPointY += -4.0F;
/* 198 */       this.ocelotBody.rotationPointZ += 5.0F;
/* 199 */       this.ocelotHead.rotationPointY += -3.3F;
/* 200 */       this.ocelotHead.rotationPointZ++;
/* 201 */       this.ocelotTail.rotationPointY += 8.0F;
/* 202 */       this.ocelotTail.rotationPointZ += -2.0F;
/* 203 */       this.ocelotTail2.rotationPointY += 2.0F;
/* 204 */       this.ocelotTail2.rotationPointZ += -0.8F;
/* 205 */       this.ocelotTail.rotateAngleX = 1.7278761F;
/* 206 */       this.ocelotTail2.rotateAngleX = 2.670354F;
/* 207 */       this.ocelotFrontRightLeg.rotateAngleX = -0.15707964F;
/* 208 */       this.ocelotFrontRightLeg.rotationPointY = 15.8F;
/* 209 */       this.ocelotFrontRightLeg.rotationPointZ = -7.0F;
/* 210 */       this.ocelotBackRightLeg.rotateAngleX = -1.5707964F;
/* 211 */       this.ocelotBackRightLeg.rotationPointY = 21.0F;
/* 212 */       this.ocelotBackRightLeg.rotationPointZ = 1.0F;
/* 213 */       this.field_78163_i = 3;
/*     */     }
/*     */     else {
/*     */       
/* 217 */       this.field_78163_i = 1;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelOcelot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */