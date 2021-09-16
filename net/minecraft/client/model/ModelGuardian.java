/*     */ package net.minecraft.client.model;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.monster.EntityGuardian;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class ModelGuardian
/*     */   extends ModelBase
/*     */ {
/*     */   private ModelRenderer guardianBody;
/*     */   private ModelRenderer guardianEye;
/*     */   private ModelRenderer[] guardianSpines;
/*     */   private ModelRenderer[] guardianTail;
/*     */   private static final String __OBFID = "CL_00002628";
/*     */   
/*     */   public ModelGuardian() {
/*  19 */     this.textureWidth = 64;
/*  20 */     this.textureHeight = 64;
/*  21 */     this.guardianSpines = new ModelRenderer[12];
/*  22 */     this.guardianBody = new ModelRenderer(this);
/*  23 */     this.guardianBody.setTextureOffset(0, 0).addBox(-6.0F, 10.0F, -8.0F, 12, 12, 16);
/*  24 */     this.guardianBody.setTextureOffset(0, 28).addBox(-8.0F, 10.0F, -6.0F, 2, 12, 12);
/*  25 */     this.guardianBody.setTextureOffset(0, 28).addBox(6.0F, 10.0F, -6.0F, 2, 12, 12, true);
/*  26 */     this.guardianBody.setTextureOffset(16, 40).addBox(-6.0F, 8.0F, -6.0F, 12, 2, 12);
/*  27 */     this.guardianBody.setTextureOffset(16, 40).addBox(-6.0F, 22.0F, -6.0F, 12, 2, 12);
/*     */     
/*  29 */     for (int var1 = 0; var1 < this.guardianSpines.length; var1++) {
/*     */       
/*  31 */       this.guardianSpines[var1] = new ModelRenderer(this, 0, 0);
/*  32 */       this.guardianSpines[var1].addBox(-1.0F, -4.5F, -1.0F, 2, 9, 2);
/*  33 */       this.guardianBody.addChild(this.guardianSpines[var1]);
/*     */     } 
/*     */     
/*  36 */     this.guardianEye = new ModelRenderer(this, 8, 0);
/*  37 */     this.guardianEye.addBox(-1.0F, 15.0F, 0.0F, 2, 2, 1);
/*  38 */     this.guardianBody.addChild(this.guardianEye);
/*  39 */     this.guardianTail = new ModelRenderer[3];
/*  40 */     this.guardianTail[0] = new ModelRenderer(this, 40, 0);
/*  41 */     this.guardianTail[0].addBox(-2.0F, 14.0F, 7.0F, 4, 4, 8);
/*  42 */     this.guardianTail[1] = new ModelRenderer(this, 0, 54);
/*  43 */     this.guardianTail[1].addBox(0.0F, 14.0F, 0.0F, 3, 3, 7);
/*  44 */     this.guardianTail[2] = new ModelRenderer(this);
/*  45 */     this.guardianTail[2].setTextureOffset(41, 32).addBox(0.0F, 14.0F, 0.0F, 2, 2, 6);
/*  46 */     this.guardianTail[2].setTextureOffset(25, 19).addBox(1.0F, 10.5F, 3.0F, 1, 9, 9);
/*  47 */     this.guardianBody.addChild(this.guardianTail[0]);
/*  48 */     this.guardianTail[0].addChild(this.guardianTail[1]);
/*  49 */     this.guardianTail[1].addChild(this.guardianTail[2]);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178706_a() {
/*  54 */     return 54;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/*  62 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/*  63 */     this.guardianBody.render(p_78088_7_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/*  73 */     EntityGuardian var8 = (EntityGuardian)p_78087_7_;
/*  74 */     float var9 = p_78087_3_ - var8.ticksExisted;
/*  75 */     this.guardianBody.rotateAngleY = p_78087_4_ / 57.295776F;
/*  76 */     this.guardianBody.rotateAngleX = p_78087_5_ / 57.295776F;
/*  77 */     float[] var10 = { 1.75F, 0.25F, 0.0F, 0.0F, 0.5F, 0.5F, 0.5F, 0.5F, 1.25F, 0.75F, 0.0F, 0.0F };
/*  78 */     float[] var11 = { 0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 1.75F, 1.25F, 0.75F, 0.0F, 0.0F, 0.0F, 0.0F };
/*  79 */     float[] var12 = { 0.0F, 0.0F, 0.25F, 1.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.75F, 1.25F };
/*  80 */     float[] var13 = { 0.0F, 0.0F, 8.0F, -8.0F, -8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F, 8.0F, -8.0F };
/*  81 */     float[] var14 = { -8.0F, -8.0F, -8.0F, -8.0F, 0.0F, 0.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, 8.0F };
/*  82 */     float[] var15 = { 8.0F, -8.0F, 0.0F, 0.0F, -8.0F, -8.0F, 8.0F, 8.0F, 8.0F, -8.0F, 0.0F, 0.0F };
/*  83 */     float var16 = (1.0F - var8.func_175469_o(var9)) * 0.55F;
/*     */     
/*  85 */     for (int var17 = 0; var17 < 12; var17++) {
/*     */       
/*  87 */       (this.guardianSpines[var17]).rotateAngleX = 3.1415927F * var10[var17];
/*  88 */       (this.guardianSpines[var17]).rotateAngleY = 3.1415927F * var11[var17];
/*  89 */       (this.guardianSpines[var17]).rotateAngleZ = 3.1415927F * var12[var17];
/*  90 */       (this.guardianSpines[var17]).rotationPointX = var13[var17] * (1.0F + MathHelper.cos(p_78087_3_ * 1.5F + var17) * 0.01F - var16);
/*  91 */       (this.guardianSpines[var17]).rotationPointY = 16.0F + var14[var17] * (1.0F + MathHelper.cos(p_78087_3_ * 1.5F + var17) * 0.01F - var16);
/*  92 */       (this.guardianSpines[var17]).rotationPointZ = var15[var17] * (1.0F + MathHelper.cos(p_78087_3_ * 1.5F + var17) * 0.01F - var16);
/*     */     } 
/*     */     
/*  95 */     this.guardianEye.rotationPointZ = -8.25F;
/*  96 */     Object var26 = Minecraft.getMinecraft().func_175606_aa();
/*     */     
/*  98 */     if (var8.func_175474_cn())
/*     */     {
/* 100 */       var26 = var8.func_175466_co();
/*     */     }
/*     */     
/* 103 */     if (var26 != null) {
/*     */       
/* 105 */       Vec3 var18 = ((Entity)var26).func_174824_e(0.0F);
/* 106 */       Vec3 var19 = p_78087_7_.func_174824_e(0.0F);
/* 107 */       double var20 = var18.yCoord - var19.yCoord;
/*     */       
/* 109 */       if (var20 > 0.0D) {
/*     */         
/* 111 */         this.guardianEye.rotationPointY = 0.0F;
/*     */       }
/*     */       else {
/*     */         
/* 115 */         this.guardianEye.rotationPointY = 1.0F;
/*     */       } 
/*     */       
/* 118 */       Vec3 var22 = p_78087_7_.getLook(0.0F);
/* 119 */       var22 = new Vec3(var22.xCoord, 0.0D, var22.zCoord);
/* 120 */       Vec3 var23 = (new Vec3(var19.xCoord - var18.xCoord, 0.0D, var19.zCoord - var18.zCoord)).normalize().rotateYaw(1.5707964F);
/* 121 */       double var24 = var22.dotProduct(var23);
/* 122 */       this.guardianEye.rotationPointX = MathHelper.sqrt_float((float)Math.abs(var24)) * 2.0F * (float)Math.signum(var24);
/*     */     } 
/*     */     
/* 125 */     this.guardianEye.showModel = true;
/* 126 */     float var27 = var8.func_175471_a(var9);
/* 127 */     (this.guardianTail[0]).rotateAngleY = MathHelper.sin(var27) * 3.1415927F * 0.05F;
/* 128 */     (this.guardianTail[1]).rotateAngleY = MathHelper.sin(var27) * 3.1415927F * 0.1F;
/* 129 */     (this.guardianTail[1]).rotationPointX = -1.5F;
/* 130 */     (this.guardianTail[1]).rotationPointY = 0.5F;
/* 131 */     (this.guardianTail[1]).rotationPointZ = 14.0F;
/* 132 */     (this.guardianTail[2]).rotateAngleY = MathHelper.sin(var27) * 3.1415927F * 0.15F;
/* 133 */     (this.guardianTail[2]).rotationPointX = 0.5F;
/* 134 */     (this.guardianTail[2]).rotationPointY = 0.5F;
/* 135 */     (this.guardianTail[2]).rotationPointZ = 6.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */