/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityArmorStand;
/*     */ 
/*     */ public class ModelArmorStand
/*     */   extends ModelArmorStandArmor
/*     */ {
/*     */   public ModelRenderer standRightSide;
/*     */   public ModelRenderer standLeftSide;
/*     */   public ModelRenderer standWaist;
/*     */   public ModelRenderer standBase;
/*     */   private static final String __OBFID = "CL_00002631";
/*     */   
/*     */   public ModelArmorStand() {
/*  17 */     this(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelArmorStand(float p_i46306_1_) {
/*  22 */     super(p_i46306_1_, 64, 64);
/*  23 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  24 */     this.bipedHead.addBox(-1.0F, -7.0F, -1.0F, 2, 7, 2, p_i46306_1_);
/*  25 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  26 */     this.bipedBody = new ModelRenderer(this, 0, 26);
/*  27 */     this.bipedBody.addBox(-6.0F, 0.0F, -1.5F, 12, 3, 3, p_i46306_1_);
/*  28 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  29 */     this.bipedRightArm = new ModelRenderer(this, 24, 0);
/*  30 */     this.bipedRightArm.addBox(-2.0F, -2.0F, -1.0F, 2, 12, 2, p_i46306_1_);
/*  31 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/*  32 */     this.bipedLeftArm = new ModelRenderer(this, 32, 16);
/*  33 */     this.bipedLeftArm.mirror = true;
/*  34 */     this.bipedLeftArm.addBox(0.0F, -2.0F, -1.0F, 2, 12, 2, p_i46306_1_);
/*  35 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/*  36 */     this.bipedRightLeg = new ModelRenderer(this, 8, 0);
/*  37 */     this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, p_i46306_1_);
/*  38 */     this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
/*  39 */     this.bipedLeftLeg = new ModelRenderer(this, 40, 16);
/*  40 */     this.bipedLeftLeg.mirror = true;
/*  41 */     this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 11, 2, p_i46306_1_);
/*  42 */     this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
/*  43 */     this.standRightSide = new ModelRenderer(this, 16, 0);
/*  44 */     this.standRightSide.addBox(-3.0F, 3.0F, -1.0F, 2, 7, 2, p_i46306_1_);
/*  45 */     this.standRightSide.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  46 */     this.standRightSide.showModel = true;
/*  47 */     this.standLeftSide = new ModelRenderer(this, 48, 16);
/*  48 */     this.standLeftSide.addBox(1.0F, 3.0F, -1.0F, 2, 7, 2, p_i46306_1_);
/*  49 */     this.standLeftSide.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  50 */     this.standWaist = new ModelRenderer(this, 0, 48);
/*  51 */     this.standWaist.addBox(-4.0F, 10.0F, -1.0F, 8, 2, 2, p_i46306_1_);
/*  52 */     this.standWaist.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  53 */     this.standBase = new ModelRenderer(this, 0, 32);
/*  54 */     this.standBase.addBox(-6.0F, 11.0F, -6.0F, 12, 1, 12, p_i46306_1_);
/*  55 */     this.standBase.setRotationPoint(0.0F, 12.0F, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/*  65 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/*     */     
/*  67 */     if (p_78087_7_ instanceof EntityArmorStand) {
/*     */       
/*  69 */       EntityArmorStand var8 = (EntityArmorStand)p_78087_7_;
/*  70 */       this.bipedLeftArm.showModel = var8.getShowArms();
/*  71 */       this.bipedRightArm.showModel = var8.getShowArms();
/*  72 */       this.standBase.showModel = !var8.hasNoBasePlate();
/*  73 */       this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
/*  74 */       this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
/*  75 */       this.standRightSide.rotateAngleX = 0.017453292F * var8.getBodyRotation().func_179415_b();
/*  76 */       this.standRightSide.rotateAngleY = 0.017453292F * var8.getBodyRotation().func_179416_c();
/*  77 */       this.standRightSide.rotateAngleZ = 0.017453292F * var8.getBodyRotation().func_179413_d();
/*  78 */       this.standLeftSide.rotateAngleX = 0.017453292F * var8.getBodyRotation().func_179415_b();
/*  79 */       this.standLeftSide.rotateAngleY = 0.017453292F * var8.getBodyRotation().func_179416_c();
/*  80 */       this.standLeftSide.rotateAngleZ = 0.017453292F * var8.getBodyRotation().func_179413_d();
/*  81 */       this.standWaist.rotateAngleX = 0.017453292F * var8.getBodyRotation().func_179415_b();
/*  82 */       this.standWaist.rotateAngleY = 0.017453292F * var8.getBodyRotation().func_179416_c();
/*  83 */       this.standWaist.rotateAngleZ = 0.017453292F * var8.getBodyRotation().func_179413_d();
/*  84 */       float var9 = (var8.getLeftLegRotation().func_179415_b() + var8.getRightLegRotation().func_179415_b()) / 2.0F;
/*  85 */       float var10 = (var8.getLeftLegRotation().func_179416_c() + var8.getRightLegRotation().func_179416_c()) / 2.0F;
/*  86 */       float var11 = (var8.getLeftLegRotation().func_179413_d() + var8.getRightLegRotation().func_179413_d()) / 2.0F;
/*  87 */       this.standBase.rotateAngleX = 0.0F;
/*  88 */       this.standBase.rotateAngleY = 0.017453292F * -p_78087_7_.rotationYaw;
/*  89 */       this.standBase.rotateAngleZ = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  98 */     super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
/*  99 */     GlStateManager.pushMatrix();
/*     */     
/* 101 */     if (this.isChild) {
/*     */       
/* 103 */       float var8 = 2.0F;
/* 104 */       GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
/* 105 */       GlStateManager.translate(0.0F, 24.0F * p_78088_7_, 0.0F);
/* 106 */       this.standRightSide.render(p_78088_7_);
/* 107 */       this.standLeftSide.render(p_78088_7_);
/* 108 */       this.standWaist.render(p_78088_7_);
/* 109 */       this.standBase.render(p_78088_7_);
/*     */     }
/*     */     else {
/*     */       
/* 113 */       if (p_78088_1_.isSneaking())
/*     */       {
/* 115 */         GlStateManager.translate(0.0F, 0.2F, 0.0F);
/*     */       }
/*     */       
/* 118 */       this.standRightSide.render(p_78088_7_);
/* 119 */       this.standLeftSide.render(p_78088_7_);
/* 120 */       this.standWaist.render(p_78088_7_);
/* 121 */       this.standBase.render(p_78088_7_);
/*     */     } 
/*     */     
/* 124 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public void postRenderHiddenArm(float p_178718_1_) {
/* 129 */     boolean var2 = this.bipedRightArm.showModel;
/* 130 */     this.bipedRightArm.showModel = true;
/* 131 */     super.postRenderHiddenArm(p_178718_1_);
/* 132 */     this.bipedRightArm.showModel = var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */