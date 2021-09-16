/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
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
/*     */ public class ModelBiped
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer bipedHead;
/*     */   public ModelRenderer bipedHeadwear;
/*     */   public ModelRenderer bipedBody;
/*     */   public ModelRenderer bipedRightArm;
/*     */   public ModelRenderer bipedLeftArm;
/*     */   public ModelRenderer bipedRightLeg;
/*     */   public ModelRenderer bipedLeftLeg;
/*     */   public int heldItemLeft;
/*     */   public int heldItemRight;
/*     */   public boolean isSneak;
/*     */   public boolean aimedBow;
/*     */   private static final String __OBFID = "CL_00000840";
/*     */   
/*     */   public ModelBiped() {
/*  44 */     this(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBiped(float p_i1148_1_) {
/*  49 */     this(p_i1148_1_, 0.0F, 64, 32);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBiped(float p_i1149_1_, float p_i1149_2_, int p_i1149_3_, int p_i1149_4_) {
/*  54 */     this.textureWidth = p_i1149_3_;
/*  55 */     this.textureHeight = p_i1149_4_;
/*  56 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  57 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1149_1_);
/*  58 */     this.bipedHead.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
/*  59 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/*  60 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, p_i1149_1_ + 0.5F);
/*  61 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
/*  62 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  63 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i1149_1_);
/*  64 */     this.bipedBody.setRotationPoint(0.0F, 0.0F + p_i1149_2_, 0.0F);
/*  65 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  66 */     this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
/*  67 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + p_i1149_2_, 0.0F);
/*  68 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/*  69 */     this.bipedLeftArm.mirror = true;
/*  70 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i1149_1_);
/*  71 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + p_i1149_2_, 0.0F);
/*  72 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  73 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_);
/*  74 */     this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + p_i1149_2_, 0.0F);
/*  75 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  76 */     this.bipedLeftLeg.mirror = true;
/*  77 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1149_1_);
/*  78 */     this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + p_i1149_2_, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  86 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*  87 */     GlStateManager.pushMatrix();
/*     */     
/*  89 */     if (this.isChild) {
/*     */       
/*  91 */       float var8 = 2.0F;
/*  92 */       GlStateManager.scale(1.5F / var8, 1.5F / var8, 1.5F / var8);
/*  93 */       GlStateManager.translate(0.0F, 16.0F * p_78088_7_, 0.0F);
/*  94 */       this.bipedHead.render(p_78088_7_);
/*  95 */       GlStateManager.popMatrix();
/*  96 */       GlStateManager.pushMatrix();
/*  97 */       GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
/*  98 */       GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
/*  99 */       this.bipedBody.render(p_78088_7_);
/* 100 */       this.bipedRightArm.render(p_78088_7_);
/* 101 */       this.bipedLeftArm.render(p_78088_7_);
/* 102 */       this.bipedRightLeg.render(p_78088_7_);
/* 103 */       this.bipedLeftLeg.render(p_78088_7_);
/* 104 */       this.bipedHeadwear.render(p_78088_7_);
/*     */     }
/*     */     else {
/*     */       
/* 108 */       if (p_78088_1_.isSneaking())
/*     */       {
/* 110 */         GlStateManager.translate(0.0F, 0.2F, 0.0F);
/*     */       }
/*     */       
/* 113 */       this.bipedHead.render(p_78088_7_);
/* 114 */       this.bipedBody.render(p_78088_7_);
/* 115 */       this.bipedRightArm.render(p_78088_7_);
/* 116 */       this.bipedLeftArm.render(p_78088_7_);
/* 117 */       this.bipedRightLeg.render(p_78088_7_);
/* 118 */       this.bipedLeftLeg.render(p_78088_7_);
/* 119 */       this.bipedHeadwear.render(p_78088_7_);
/*     */     } 
/*     */     
/* 122 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 132 */     this.bipedHead.rotateAngleY = p_78087_4_ / 57.295776F;
/* 133 */     this.bipedHead.rotateAngleX = p_78087_5_ / 57.295776F;
/* 134 */     this.bipedRightArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 2.0F * p_78087_2_ * 0.5F;
/* 135 */     this.bipedLeftArm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 2.0F * p_78087_2_ * 0.5F;
/* 136 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 137 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 138 */     this.bipedRightLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
/* 139 */     this.bipedLeftLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_;
/* 140 */     this.bipedRightLeg.rotateAngleY = 0.0F;
/* 141 */     this.bipedLeftLeg.rotateAngleY = 0.0F;
/*     */     
/* 143 */     if (this.isRiding) {
/*     */       
/* 145 */       this.bipedRightArm.rotateAngleX += -0.62831855F;
/* 146 */       this.bipedLeftArm.rotateAngleX += -0.62831855F;
/* 147 */       this.bipedRightLeg.rotateAngleX = -1.2566371F;
/* 148 */       this.bipedLeftLeg.rotateAngleX = -1.2566371F;
/* 149 */       this.bipedRightLeg.rotateAngleY = 0.31415927F;
/* 150 */       this.bipedLeftLeg.rotateAngleY = -0.31415927F;
/*     */     } 
/*     */     
/* 153 */     if (this.heldItemLeft != 0)
/*     */     {
/* 155 */       this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.31415927F * this.heldItemLeft;
/*     */     }
/*     */     
/* 158 */     this.bipedRightArm.rotateAngleY = 0.0F;
/* 159 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/*     */     
/* 161 */     switch (this.heldItemRight) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 169 */         this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.31415927F * this.heldItemRight;
/*     */         break;
/*     */       
/*     */       case 3:
/* 173 */         this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.31415927F * this.heldItemRight;
/* 174 */         this.bipedRightArm.rotateAngleY = -0.5235988F;
/*     */         break;
/*     */     } 
/* 177 */     this.bipedLeftArm.rotateAngleY = 0.0F;
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (this.swingProgress > -9990.0F) {
/*     */       
/* 183 */       float var8 = this.swingProgress;
/* 184 */       this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(var8) * 3.1415927F * 2.0F) * 0.2F;
/* 185 */       this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 186 */       this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 187 */       this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
/* 188 */       this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
/* 189 */       this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 190 */       this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 191 */       this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
/* 192 */       var8 = 1.0F - this.swingProgress;
/* 193 */       var8 *= var8;
/* 194 */       var8 *= var8;
/* 195 */       var8 = 1.0F - var8;
/* 196 */       float var9 = MathHelper.sin(var8 * 3.1415927F);
/* 197 */       float var10 = MathHelper.sin(this.swingProgress * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 198 */       this.bipedRightArm.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX - var9 * 1.2D + var10);
/* 199 */       this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 200 */       this.bipedRightArm.rotateAngleZ += MathHelper.sin(this.swingProgress * 3.1415927F) * -0.4F;
/*     */     } 
/*     */     
/* 203 */     if (this.isSneak) {
/*     */       
/* 205 */       this.bipedBody.rotateAngleX = 0.5F;
/* 206 */       this.bipedRightArm.rotateAngleX += 0.4F;
/* 207 */       this.bipedLeftArm.rotateAngleX += 0.4F;
/* 208 */       this.bipedRightLeg.rotationPointZ = 4.0F;
/* 209 */       this.bipedLeftLeg.rotationPointZ = 4.0F;
/* 210 */       this.bipedRightLeg.rotationPointY = 9.0F;
/* 211 */       this.bipedLeftLeg.rotationPointY = 9.0F;
/* 212 */       this.bipedHead.rotationPointY = 1.0F;
/*     */     }
/*     */     else {
/*     */       
/* 216 */       this.bipedBody.rotateAngleX = 0.0F;
/* 217 */       this.bipedRightLeg.rotationPointZ = 0.1F;
/* 218 */       this.bipedLeftLeg.rotationPointZ = 0.1F;
/* 219 */       this.bipedRightLeg.rotationPointY = 12.0F;
/* 220 */       this.bipedLeftLeg.rotationPointY = 12.0F;
/* 221 */       this.bipedHead.rotationPointY = 0.0F;
/*     */     } 
/*     */     
/* 224 */     this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 225 */     this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 226 */     this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
/* 227 */     this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
/*     */     
/* 229 */     if (this.aimedBow) {
/*     */       
/* 231 */       float var8 = 0.0F;
/* 232 */       float var9 = 0.0F;
/* 233 */       this.bipedRightArm.rotateAngleZ = 0.0F;
/* 234 */       this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 235 */       this.bipedRightArm.rotateAngleY = -(0.1F - var8 * 0.6F) + this.bipedHead.rotateAngleY;
/* 236 */       this.bipedLeftArm.rotateAngleY = 0.1F - var8 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
/* 237 */       this.bipedRightArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
/* 238 */       this.bipedLeftArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
/* 239 */       this.bipedRightArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
/* 240 */       this.bipedLeftArm.rotateAngleX -= var8 * 1.2F - var9 * 0.4F;
/* 241 */       this.bipedRightArm.rotateAngleZ += MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 242 */       this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(p_78087_3_ * 0.09F) * 0.05F + 0.05F;
/* 243 */       this.bipedRightArm.rotateAngleX += MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
/* 244 */       this.bipedLeftArm.rotateAngleX -= MathHelper.sin(p_78087_3_ * 0.067F) * 0.05F;
/*     */     } 
/*     */     
/* 247 */     func_178685_a(this.bipedHead, this.bipedHeadwear);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModelAttributes(ModelBase p_178686_1_) {
/* 252 */     super.setModelAttributes(p_178686_1_);
/*     */     
/* 254 */     if (p_178686_1_ instanceof ModelBiped) {
/*     */       
/* 256 */       ModelBiped var2 = (ModelBiped)p_178686_1_;
/* 257 */       this.heldItemLeft = var2.heldItemLeft;
/* 258 */       this.heldItemRight = var2.heldItemRight;
/* 259 */       this.isSneak = var2.isSneak;
/* 260 */       this.aimedBow = var2.aimedBow;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178719_a(boolean p_178719_1_) {
/* 266 */     this.bipedHead.showModel = p_178719_1_;
/* 267 */     this.bipedHeadwear.showModel = p_178719_1_;
/* 268 */     this.bipedBody.showModel = p_178719_1_;
/* 269 */     this.bipedRightArm.showModel = p_178719_1_;
/* 270 */     this.bipedLeftArm.showModel = p_178719_1_;
/* 271 */     this.bipedRightLeg.showModel = p_178719_1_;
/* 272 */     this.bipedLeftLeg.showModel = p_178719_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postRenderHiddenArm(float p_178718_1_) {
/* 277 */     this.bipedRightArm.postRender(p_178718_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelBiped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */