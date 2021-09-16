/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityRabbit;
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
/*     */ 
/*     */ public class ModelRabbit
/*     */   extends ModelBase
/*     */ {
/*     */   ModelRenderer rabbitLeftFoot;
/*     */   ModelRenderer rabbitRightFoot;
/*     */   ModelRenderer rabbitLeftThigh;
/*     */   ModelRenderer rabbitRightThigh;
/*     */   ModelRenderer rabbitBody;
/*     */   ModelRenderer rabbitLeftArm;
/*     */   ModelRenderer rabbitRightArm;
/*     */   ModelRenderer rabbitHead;
/*     */   ModelRenderer rabbitRightEar;
/*     */   ModelRenderer rabbitLeftEar;
/*     */   ModelRenderer rabbitTail;
/*     */   ModelRenderer rabbitNose;
/*  46 */   private float field_178701_m = 0.0F;
/*  47 */   private float field_178699_n = 0.0F;
/*     */   
/*     */   private static final String __OBFID = "CL_00002625";
/*     */   
/*     */   public ModelRabbit() {
/*  52 */     setTextureOffset("head.main", 0, 0);
/*  53 */     setTextureOffset("head.nose", 0, 24);
/*  54 */     setTextureOffset("head.ear1", 0, 10);
/*  55 */     setTextureOffset("head.ear2", 6, 10);
/*  56 */     this.rabbitLeftFoot = new ModelRenderer(this, 26, 24);
/*  57 */     this.rabbitLeftFoot.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
/*  58 */     this.rabbitLeftFoot.setRotationPoint(3.0F, 17.5F, 3.7F);
/*  59 */     this.rabbitLeftFoot.mirror = true;
/*  60 */     setRotationOffset(this.rabbitLeftFoot, 0.0F, 0.0F, 0.0F);
/*  61 */     this.rabbitRightFoot = new ModelRenderer(this, 8, 24);
/*  62 */     this.rabbitRightFoot.addBox(-1.0F, 5.5F, -3.7F, 2, 1, 7);
/*  63 */     this.rabbitRightFoot.setRotationPoint(-3.0F, 17.5F, 3.7F);
/*  64 */     this.rabbitRightFoot.mirror = true;
/*  65 */     setRotationOffset(this.rabbitRightFoot, 0.0F, 0.0F, 0.0F);
/*  66 */     this.rabbitLeftThigh = new ModelRenderer(this, 30, 15);
/*  67 */     this.rabbitLeftThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
/*  68 */     this.rabbitLeftThigh.setRotationPoint(3.0F, 17.5F, 3.7F);
/*  69 */     this.rabbitLeftThigh.mirror = true;
/*  70 */     setRotationOffset(this.rabbitLeftThigh, -0.34906584F, 0.0F, 0.0F);
/*  71 */     this.rabbitRightThigh = new ModelRenderer(this, 16, 15);
/*  72 */     this.rabbitRightThigh.addBox(-1.0F, 0.0F, 0.0F, 2, 4, 5);
/*  73 */     this.rabbitRightThigh.setRotationPoint(-3.0F, 17.5F, 3.7F);
/*  74 */     this.rabbitRightThigh.mirror = true;
/*  75 */     setRotationOffset(this.rabbitRightThigh, -0.34906584F, 0.0F, 0.0F);
/*  76 */     this.rabbitBody = new ModelRenderer(this, 0, 0);
/*  77 */     this.rabbitBody.addBox(-3.0F, -2.0F, -10.0F, 6, 5, 10);
/*  78 */     this.rabbitBody.setRotationPoint(0.0F, 19.0F, 8.0F);
/*  79 */     this.rabbitBody.mirror = true;
/*  80 */     setRotationOffset(this.rabbitBody, -0.34906584F, 0.0F, 0.0F);
/*  81 */     this.rabbitLeftArm = new ModelRenderer(this, 8, 15);
/*  82 */     this.rabbitLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  83 */     this.rabbitLeftArm.setRotationPoint(3.0F, 17.0F, -1.0F);
/*  84 */     this.rabbitLeftArm.mirror = true;
/*  85 */     setRotationOffset(this.rabbitLeftArm, -0.17453292F, 0.0F, 0.0F);
/*  86 */     this.rabbitRightArm = new ModelRenderer(this, 0, 15);
/*  87 */     this.rabbitRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2);
/*  88 */     this.rabbitRightArm.setRotationPoint(-3.0F, 17.0F, -1.0F);
/*  89 */     this.rabbitRightArm.mirror = true;
/*  90 */     setRotationOffset(this.rabbitRightArm, -0.17453292F, 0.0F, 0.0F);
/*  91 */     this.rabbitHead = new ModelRenderer(this, 32, 0);
/*  92 */     this.rabbitHead.addBox(-2.5F, -4.0F, -5.0F, 5, 4, 5);
/*  93 */     this.rabbitHead.setRotationPoint(0.0F, 16.0F, -1.0F);
/*  94 */     this.rabbitHead.mirror = true;
/*  95 */     setRotationOffset(this.rabbitHead, 0.0F, 0.0F, 0.0F);
/*  96 */     this.rabbitRightEar = new ModelRenderer(this, 52, 0);
/*  97 */     this.rabbitRightEar.addBox(-2.5F, -9.0F, -1.0F, 2, 5, 1);
/*  98 */     this.rabbitRightEar.setRotationPoint(0.0F, 16.0F, -1.0F);
/*  99 */     this.rabbitRightEar.mirror = true;
/* 100 */     setRotationOffset(this.rabbitRightEar, 0.0F, -0.2617994F, 0.0F);
/* 101 */     this.rabbitLeftEar = new ModelRenderer(this, 58, 0);
/* 102 */     this.rabbitLeftEar.addBox(0.5F, -9.0F, -1.0F, 2, 5, 1);
/* 103 */     this.rabbitLeftEar.setRotationPoint(0.0F, 16.0F, -1.0F);
/* 104 */     this.rabbitLeftEar.mirror = true;
/* 105 */     setRotationOffset(this.rabbitLeftEar, 0.0F, 0.2617994F, 0.0F);
/* 106 */     this.rabbitTail = new ModelRenderer(this, 52, 6);
/* 107 */     this.rabbitTail.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
/* 108 */     this.rabbitTail.setRotationPoint(0.0F, 20.0F, 7.0F);
/* 109 */     this.rabbitTail.mirror = true;
/* 110 */     setRotationOffset(this.rabbitTail, -0.3490659F, 0.0F, 0.0F);
/* 111 */     this.rabbitNose = new ModelRenderer(this, 32, 9);
/* 112 */     this.rabbitNose.addBox(-0.5F, -2.5F, -5.5F, 1, 1, 1);
/* 113 */     this.rabbitNose.setRotationPoint(0.0F, 16.0F, -1.0F);
/* 114 */     this.rabbitNose.mirror = true;
/* 115 */     setRotationOffset(this.rabbitNose, 0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRotationOffset(ModelRenderer p_178691_1_, float p_178691_2_, float p_178691_3_, float p_178691_4_) {
/* 120 */     p_178691_1_.rotateAngleX = p_178691_2_;
/* 121 */     p_178691_1_.rotateAngleY = p_178691_3_;
/* 122 */     p_178691_1_.rotateAngleZ = p_178691_4_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 130 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*     */     
/* 132 */     if (this.isChild) {
/*     */       
/* 134 */       float var8 = 2.0F;
/* 135 */       GlStateManager.pushMatrix();
/* 136 */       GlStateManager.translate(0.0F, 5.0F * p_78088_7_, 2.0F * p_78088_7_);
/* 137 */       this.rabbitHead.render(p_78088_7_);
/* 138 */       this.rabbitLeftEar.render(p_78088_7_);
/* 139 */       this.rabbitRightEar.render(p_78088_7_);
/* 140 */       this.rabbitNose.render(p_78088_7_);
/* 141 */       GlStateManager.popMatrix();
/* 142 */       GlStateManager.pushMatrix();
/* 143 */       GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
/* 144 */       GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
/* 145 */       this.rabbitLeftFoot.render(p_78088_7_);
/* 146 */       this.rabbitRightFoot.render(p_78088_7_);
/* 147 */       this.rabbitLeftThigh.render(p_78088_7_);
/* 148 */       this.rabbitRightThigh.render(p_78088_7_);
/* 149 */       this.rabbitBody.render(p_78088_7_);
/* 150 */       this.rabbitLeftArm.render(p_78088_7_);
/* 151 */       this.rabbitRightArm.render(p_78088_7_);
/* 152 */       this.rabbitTail.render(p_78088_7_);
/* 153 */       GlStateManager.popMatrix();
/*     */     }
/*     */     else {
/*     */       
/* 157 */       this.rabbitLeftFoot.render(p_78088_7_);
/* 158 */       this.rabbitRightFoot.render(p_78088_7_);
/* 159 */       this.rabbitLeftThigh.render(p_78088_7_);
/* 160 */       this.rabbitRightThigh.render(p_78088_7_);
/* 161 */       this.rabbitBody.render(p_78088_7_);
/* 162 */       this.rabbitLeftArm.render(p_78088_7_);
/* 163 */       this.rabbitRightArm.render(p_78088_7_);
/* 164 */       this.rabbitHead.render(p_78088_7_);
/* 165 */       this.rabbitRightEar.render(p_78088_7_);
/* 166 */       this.rabbitLeftEar.render(p_78088_7_);
/* 167 */       this.rabbitTail.render(p_78088_7_);
/* 168 */       this.rabbitNose.render(p_78088_7_);
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
/* 179 */     float var8 = p_78087_3_ - p_78087_7_.ticksExisted;
/* 180 */     EntityRabbit var9 = (EntityRabbit)p_78087_7_;
/* 181 */     this.rabbitLeftEar.rotateAngleX = p_78087_5_ * 0.017453292F;
/* 182 */     this.rabbitHead.rotateAngleY = p_78087_4_ * 0.017453292F;
/* 183 */     this.rabbitNose.rotateAngleY -= 0.2617994F;
/* 184 */     this.rabbitNose.rotateAngleY += 0.2617994F;
/* 185 */     this.field_178701_m = MathHelper.sin(var9.func_175521_o(var8) * 3.1415927F);
/* 186 */     this.rabbitRightThigh.rotateAngleX = (this.field_178701_m * 50.0F - 21.0F) * 0.017453292F;
/* 187 */     this.rabbitRightFoot.rotateAngleX = this.field_178701_m * 50.0F * 0.017453292F;
/* 188 */     this.rabbitRightArm.rotateAngleX = (this.field_178701_m * -40.0F - 11.0F) * 0.017453292F;
/*     */   }
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {}
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */