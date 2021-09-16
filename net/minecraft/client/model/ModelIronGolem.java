/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityIronGolem;
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
/*     */ public class ModelIronGolem
/*     */   extends ModelBase
/*     */ {
/*     */   public ModelRenderer ironGolemHead;
/*     */   public ModelRenderer ironGolemBody;
/*     */   public ModelRenderer ironGolemRightArm;
/*     */   public ModelRenderer ironGolemLeftArm;
/*     */   public ModelRenderer ironGolemLeftLeg;
/*     */   public ModelRenderer ironGolemRightLeg;
/*     */   private static final String __OBFID = "CL_00000863";
/*     */   
/*     */   public ModelIronGolem() {
/*  30 */     this(0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelIronGolem(float p_i1161_1_) {
/*  35 */     this(p_i1161_1_, -7.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelIronGolem(float p_i46362_1_, float p_i46362_2_) {
/*  40 */     short var3 = 128;
/*  41 */     short var4 = 128;
/*  42 */     this.ironGolemHead = (new ModelRenderer(this)).setTextureSize(var3, var4);
/*  43 */     this.ironGolemHead.setRotationPoint(0.0F, 0.0F + p_i46362_2_, -2.0F);
/*  44 */     this.ironGolemHead.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, p_i46362_1_);
/*  45 */     this.ironGolemHead.setTextureOffset(24, 0).addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2, p_i46362_1_);
/*  46 */     this.ironGolemBody = (new ModelRenderer(this)).setTextureSize(var3, var4);
/*  47 */     this.ironGolemBody.setRotationPoint(0.0F, 0.0F + p_i46362_2_, 0.0F);
/*  48 */     this.ironGolemBody.setTextureOffset(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, p_i46362_1_);
/*  49 */     this.ironGolemBody.setTextureOffset(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9, 5, 6, p_i46362_1_ + 0.5F);
/*  50 */     this.ironGolemRightArm = (new ModelRenderer(this)).setTextureSize(var3, var4);
/*  51 */     this.ironGolemRightArm.setRotationPoint(0.0F, -7.0F, 0.0F);
/*  52 */     this.ironGolemRightArm.setTextureOffset(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4, 30, 6, p_i46362_1_);
/*  53 */     this.ironGolemLeftArm = (new ModelRenderer(this)).setTextureSize(var3, var4);
/*  54 */     this.ironGolemLeftArm.setRotationPoint(0.0F, -7.0F, 0.0F);
/*  55 */     this.ironGolemLeftArm.setTextureOffset(60, 58).addBox(9.0F, -2.5F, -3.0F, 4, 30, 6, p_i46362_1_);
/*  56 */     this.ironGolemLeftLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(var3, var4);
/*  57 */     this.ironGolemLeftLeg.setRotationPoint(-4.0F, 18.0F + p_i46362_2_, 0.0F);
/*  58 */     this.ironGolemLeftLeg.setTextureOffset(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, p_i46362_1_);
/*  59 */     this.ironGolemRightLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(var3, var4);
/*  60 */     this.ironGolemRightLeg.mirror = true;
/*  61 */     this.ironGolemRightLeg.setTextureOffset(60, 0).setRotationPoint(5.0F, 18.0F + p_i46362_2_, 0.0F);
/*  62 */     this.ironGolemRightLeg.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, p_i46362_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  70 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*  71 */     this.ironGolemHead.render(p_78088_7_);
/*  72 */     this.ironGolemBody.render(p_78088_7_);
/*  73 */     this.ironGolemLeftLeg.render(p_78088_7_);
/*  74 */     this.ironGolemRightLeg.render(p_78088_7_);
/*  75 */     this.ironGolemRightArm.render(p_78088_7_);
/*  76 */     this.ironGolemLeftArm.render(p_78088_7_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/*  86 */     this.ironGolemHead.rotateAngleY = p_78087_4_ / 57.295776F;
/*  87 */     this.ironGolemHead.rotateAngleX = p_78087_5_ / 57.295776F;
/*  88 */     this.ironGolemLeftLeg.rotateAngleX = -1.5F * func_78172_a(p_78087_1_, 13.0F) * p_78087_2_;
/*  89 */     this.ironGolemRightLeg.rotateAngleX = 1.5F * func_78172_a(p_78087_1_, 13.0F) * p_78087_2_;
/*  90 */     this.ironGolemLeftLeg.rotateAngleY = 0.0F;
/*  91 */     this.ironGolemRightLeg.rotateAngleY = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
/* 100 */     EntityIronGolem var5 = (EntityIronGolem)p_78086_1_;
/* 101 */     int var6 = var5.getAttackTimer();
/*     */     
/* 103 */     if (var6 > 0) {
/*     */       
/* 105 */       this.ironGolemRightArm.rotateAngleX = -2.0F + 1.5F * func_78172_a(var6 - p_78086_4_, 10.0F);
/* 106 */       this.ironGolemLeftArm.rotateAngleX = -2.0F + 1.5F * func_78172_a(var6 - p_78086_4_, 10.0F);
/*     */     }
/*     */     else {
/*     */       
/* 110 */       int var7 = var5.getHoldRoseTick();
/*     */       
/* 112 */       if (var7 > 0) {
/*     */         
/* 114 */         this.ironGolemRightArm.rotateAngleX = -0.8F + 0.025F * func_78172_a(var7, 70.0F);
/* 115 */         this.ironGolemLeftArm.rotateAngleX = 0.0F;
/*     */       }
/*     */       else {
/*     */         
/* 119 */         this.ironGolemRightArm.rotateAngleX = (-0.2F + 1.5F * func_78172_a(p_78086_2_, 13.0F)) * p_78086_3_;
/* 120 */         this.ironGolemLeftArm.rotateAngleX = (-0.2F - 1.5F * func_78172_a(p_78086_2_, 13.0F)) * p_78086_3_;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private float func_78172_a(float p_78172_1_, float p_78172_2_) {
/* 127 */     return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / p_78172_2_ * 0.25F;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelIronGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */