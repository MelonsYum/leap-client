/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ public class ModelPlayer
/*     */   extends ModelBiped
/*     */ {
/*     */   public ModelRenderer field_178734_a;
/*     */   public ModelRenderer field_178732_b;
/*     */   public ModelRenderer field_178733_c;
/*     */   public ModelRenderer field_178731_d;
/*     */   public ModelRenderer field_178730_v;
/*     */   private ModelRenderer field_178729_w;
/*     */   private ModelRenderer field_178736_x;
/*     */   private boolean field_178735_y;
/*     */   private static final String __OBFID = "CL_00002626";
/*     */   
/*     */   public ModelPlayer(float p_i46304_1_, boolean p_i46304_2_) {
/*  20 */     super(p_i46304_1_, 0.0F, 64, 64);
/*  21 */     this.field_178735_y = p_i46304_2_;
/*  22 */     this.field_178736_x = new ModelRenderer(this, 24, 0);
/*  23 */     this.field_178736_x.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, p_i46304_1_);
/*  24 */     this.field_178729_w = new ModelRenderer(this, 0, 0);
/*  25 */     this.field_178729_w.setTextureSize(64, 32);
/*  26 */     this.field_178729_w.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, p_i46304_1_);
/*     */     
/*  28 */     if (p_i46304_2_) {
/*     */       
/*  30 */       this.bipedLeftArm = new ModelRenderer(this, 32, 48);
/*  31 */       this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_);
/*  32 */       this.bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
/*  33 */       this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  34 */       this.bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_);
/*  35 */       this.bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
/*  36 */       this.field_178734_a = new ModelRenderer(this, 48, 48);
/*  37 */       this.field_178734_a.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_ + 0.25F);
/*  38 */       this.field_178734_a.setRotationPoint(5.0F, 2.5F, 0.0F);
/*  39 */       this.field_178732_b = new ModelRenderer(this, 40, 32);
/*  40 */       this.field_178732_b.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, p_i46304_1_ + 0.25F);
/*  41 */       this.field_178732_b.setRotationPoint(-5.0F, 2.5F, 10.0F);
/*     */     }
/*     */     else {
/*     */       
/*  45 */       this.bipedLeftArm = new ModelRenderer(this, 32, 48);
/*  46 */       this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i46304_1_);
/*  47 */       this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/*  48 */       this.field_178734_a = new ModelRenderer(this, 48, 48);
/*  49 */       this.field_178734_a.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
/*  50 */       this.field_178734_a.setRotationPoint(5.0F, 2.0F, 0.0F);
/*  51 */       this.field_178732_b = new ModelRenderer(this, 40, 32);
/*  52 */       this.field_178732_b.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
/*  53 */       this.field_178732_b.setRotationPoint(-5.0F, 2.0F, 10.0F);
/*     */     } 
/*     */     
/*  56 */     this.bipedLeftLeg = new ModelRenderer(this, 16, 48);
/*  57 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i46304_1_);
/*  58 */     this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
/*  59 */     this.field_178733_c = new ModelRenderer(this, 0, 48);
/*  60 */     this.field_178733_c.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
/*  61 */     this.field_178733_c.setRotationPoint(1.9F, 12.0F, 0.0F);
/*  62 */     this.field_178731_d = new ModelRenderer(this, 0, 32);
/*  63 */     this.field_178731_d.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i46304_1_ + 0.25F);
/*  64 */     this.field_178731_d.setRotationPoint(-1.9F, 12.0F, 0.0F);
/*  65 */     this.field_178730_v = new ModelRenderer(this, 16, 32);
/*  66 */     this.field_178730_v.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, p_i46304_1_ + 0.25F);
/*  67 */     this.field_178730_v.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  75 */     super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
/*  76 */     GlStateManager.pushMatrix();
/*     */     
/*  78 */     if (this.isChild) {
/*     */       
/*  80 */       float var8 = 2.0F;
/*  81 */       GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
/*  82 */       GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
/*  83 */       this.field_178733_c.render(p_78088_7_);
/*  84 */       this.field_178731_d.render(p_78088_7_);
/*  85 */       this.field_178734_a.render(p_78088_7_);
/*  86 */       this.field_178732_b.render(p_78088_7_);
/*  87 */       this.field_178730_v.render(p_78088_7_);
/*     */     }
/*     */     else {
/*     */       
/*  91 */       if (p_78088_1_.isSneaking())
/*     */       {
/*  93 */         GlStateManager.translate(0.0F, 0.2F, 0.0F);
/*     */       }
/*     */       
/*  96 */       this.field_178733_c.render(p_78088_7_);
/*  97 */       this.field_178731_d.render(p_78088_7_);
/*  98 */       this.field_178734_a.render(p_78088_7_);
/*  99 */       this.field_178732_b.render(p_78088_7_);
/* 100 */       this.field_178730_v.render(p_78088_7_);
/*     */     } 
/*     */     
/* 103 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178727_b(float p_178727_1_) {
/* 108 */     func_178685_a(this.bipedHead, this.field_178736_x);
/* 109 */     this.field_178736_x.rotationPointX = 0.0F;
/* 110 */     this.field_178736_x.rotationPointY = 0.0F;
/* 111 */     this.field_178736_x.render(p_178727_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178728_c(float p_178728_1_) {
/* 116 */     this.field_178729_w.render(p_178728_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 126 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 127 */     func_178685_a(this.bipedLeftLeg, this.field_178733_c);
/* 128 */     func_178685_a(this.bipedRightLeg, this.field_178731_d);
/* 129 */     func_178685_a(this.bipedLeftArm, this.field_178734_a);
/* 130 */     func_178685_a(this.bipedRightArm, this.field_178732_b);
/* 131 */     func_178685_a(this.bipedBody, this.field_178730_v);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178725_a() {
/* 136 */     this.bipedRightArm.render(0.0625F);
/* 137 */     this.field_178732_b.render(0.0625F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178726_b() {
/* 142 */     this.bipedLeftArm.render(0.0625F);
/* 143 */     this.field_178734_a.render(0.0625F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178719_a(boolean p_178719_1_) {
/* 148 */     super.func_178719_a(p_178719_1_);
/* 149 */     this.field_178734_a.showModel = p_178719_1_;
/* 150 */     this.field_178732_b.showModel = p_178719_1_;
/* 151 */     this.field_178733_c.showModel = p_178719_1_;
/* 152 */     this.field_178731_d.showModel = p_178719_1_;
/* 153 */     this.field_178730_v.showModel = p_178719_1_;
/* 154 */     this.field_178729_w.showModel = p_178719_1_;
/* 155 */     this.field_178736_x.showModel = p_178719_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postRenderHiddenArm(float p_178718_1_) {
/* 160 */     if (this.field_178735_y) {
/*     */       
/* 162 */       this.bipedRightArm.rotationPointX++;
/* 163 */       this.bipedRightArm.postRender(p_178718_1_);
/* 164 */       this.bipedRightArm.rotationPointX--;
/*     */     }
/*     */     else {
/*     */       
/* 168 */       this.bipedRightArm.postRender(p_178718_1_);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */