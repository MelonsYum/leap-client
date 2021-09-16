/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
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
/*     */ public class EntityLookHelper
/*     */ {
/*     */   private EntityLiving entity;
/*     */   private float deltaLookYaw;
/*     */   private float deltaLookPitch;
/*     */   private boolean isLooking;
/*     */   private double posX;
/*     */   private double posY;
/*     */   private double posZ;
/*     */   private static final String __OBFID = "CL_00001572";
/*     */   
/*     */   public EntityLookHelper(EntityLiving p_i1613_1_) {
/*  31 */     this.entity = p_i1613_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLookPositionWithEntity(Entity p_75651_1_, float p_75651_2_, float p_75651_3_) {
/*  39 */     this.posX = p_75651_1_.posX;
/*     */     
/*  41 */     if (p_75651_1_ instanceof net.minecraft.entity.EntityLivingBase) {
/*     */       
/*  43 */       this.posY = p_75651_1_.posY + p_75651_1_.getEyeHeight();
/*     */     }
/*     */     else {
/*     */       
/*  47 */       this.posY = ((p_75651_1_.getEntityBoundingBox()).minY + (p_75651_1_.getEntityBoundingBox()).maxY) / 2.0D;
/*     */     } 
/*     */     
/*  50 */     this.posZ = p_75651_1_.posZ;
/*  51 */     this.deltaLookYaw = p_75651_2_;
/*  52 */     this.deltaLookPitch = p_75651_3_;
/*  53 */     this.isLooking = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLookPosition(double p_75650_1_, double p_75650_3_, double p_75650_5_, float p_75650_7_, float p_75650_8_) {
/*  61 */     this.posX = p_75650_1_;
/*  62 */     this.posY = p_75650_3_;
/*  63 */     this.posZ = p_75650_5_;
/*  64 */     this.deltaLookYaw = p_75650_7_;
/*  65 */     this.deltaLookPitch = p_75650_8_;
/*  66 */     this.isLooking = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdateLook() {
/*  74 */     this.entity.rotationPitch = 0.0F;
/*     */     
/*  76 */     if (this.isLooking) {
/*     */       
/*  78 */       this.isLooking = false;
/*  79 */       double var1 = this.posX - this.entity.posX;
/*  80 */       double var3 = this.posY - this.entity.posY + this.entity.getEyeHeight();
/*  81 */       double var5 = this.posZ - this.entity.posZ;
/*  82 */       double var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
/*  83 */       float var9 = (float)(Math.atan2(var5, var1) * 180.0D / Math.PI) - 90.0F;
/*  84 */       float var10 = (float)-(Math.atan2(var3, var7) * 180.0D / Math.PI);
/*  85 */       this.entity.rotationPitch = updateRotation(this.entity.rotationPitch, var10, this.deltaLookPitch);
/*  86 */       this.entity.rotationYawHead = updateRotation(this.entity.rotationYawHead, var9, this.deltaLookYaw);
/*     */     }
/*     */     else {
/*     */       
/*  90 */       this.entity.rotationYawHead = updateRotation(this.entity.rotationYawHead, this.entity.renderYawOffset, 10.0F);
/*     */     } 
/*     */     
/*  93 */     float var11 = MathHelper.wrapAngleTo180_float(this.entity.rotationYawHead - this.entity.renderYawOffset);
/*     */     
/*  95 */     if (!this.entity.getNavigator().noPath()) {
/*     */       
/*  97 */       if (var11 < -75.0F)
/*     */       {
/*  99 */         this.entity.rotationYawHead = this.entity.renderYawOffset - 75.0F;
/*     */       }
/*     */       
/* 102 */       if (var11 > 75.0F)
/*     */       {
/* 104 */         this.entity.rotationYawHead = this.entity.renderYawOffset + 75.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private float updateRotation(float p_75652_1_, float p_75652_2_, float p_75652_3_) {
/* 111 */     float var4 = MathHelper.wrapAngleTo180_float(p_75652_2_ - p_75652_1_);
/*     */     
/* 113 */     if (var4 > p_75652_3_)
/*     */     {
/* 115 */       var4 = p_75652_3_;
/*     */     }
/*     */     
/* 118 */     if (var4 < -p_75652_3_)
/*     */     {
/* 120 */       var4 = -p_75652_3_;
/*     */     }
/*     */     
/* 123 */     return p_75652_1_ + var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180424_b() {
/* 128 */     return this.isLooking;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_180423_e() {
/* 133 */     return this.posX;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_180422_f() {
/* 138 */     return this.posY;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_180421_g() {
/* 143 */     return this.posZ;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityLookHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */