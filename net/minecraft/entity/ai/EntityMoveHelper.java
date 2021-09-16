/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityMoveHelper
/*     */ {
/*     */   protected EntityLiving entity;
/*     */   protected double posX;
/*     */   protected double posY;
/*     */   protected double posZ;
/*     */   protected double speed;
/*     */   protected boolean update;
/*     */   private static final String __OBFID = "CL_00001573";
/*     */   
/*     */   public EntityMoveHelper(EntityLiving p_i1614_1_) {
/*  22 */     this.entity = p_i1614_1_;
/*  23 */     this.posX = p_i1614_1_.posX;
/*  24 */     this.posY = p_i1614_1_.posY;
/*  25 */     this.posZ = p_i1614_1_.posZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUpdating() {
/*  30 */     return this.update;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSpeed() {
/*  35 */     return this.speed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMoveTo(double p_75642_1_, double p_75642_3_, double p_75642_5_, double p_75642_7_) {
/*  43 */     this.posX = p_75642_1_;
/*  44 */     this.posY = p_75642_3_;
/*  45 */     this.posZ = p_75642_5_;
/*  46 */     this.speed = p_75642_7_;
/*  47 */     this.update = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUpdateMoveHelper() {
/*  52 */     this.entity.setMoveForward(0.0F);
/*     */     
/*  54 */     if (this.update) {
/*     */       
/*  56 */       this.update = false;
/*  57 */       int var1 = MathHelper.floor_double((this.entity.getEntityBoundingBox()).minY + 0.5D);
/*  58 */       double var2 = this.posX - this.entity.posX;
/*  59 */       double var4 = this.posZ - this.entity.posZ;
/*  60 */       double var6 = this.posY - var1;
/*  61 */       double var8 = var2 * var2 + var6 * var6 + var4 * var4;
/*     */       
/*  63 */       if (var8 >= 2.500000277905201E-7D) {
/*     */         
/*  65 */         float var10 = (float)(Math.atan2(var4, var2) * 180.0D / Math.PI) - 90.0F;
/*  66 */         this.entity.rotationYaw = limitAngle(this.entity.rotationYaw, var10, 30.0F);
/*  67 */         this.entity.setAIMoveSpeed((float)(this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
/*     */         
/*  69 */         if (var6 > 0.0D && var2 * var2 + var4 * var4 < 1.0D)
/*     */         {
/*  71 */           this.entity.getJumpHelper().setJumping();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float limitAngle(float p_75639_1_, float p_75639_2_, float p_75639_3_) {
/*  82 */     float var4 = MathHelper.wrapAngleTo180_float(p_75639_2_ - p_75639_1_);
/*     */     
/*  84 */     if (var4 > p_75639_3_)
/*     */     {
/*  86 */       var4 = p_75639_3_;
/*     */     }
/*     */     
/*  89 */     if (var4 < -p_75639_3_)
/*     */     {
/*  91 */       var4 = -p_75639_3_;
/*     */     }
/*     */     
/*  94 */     float var5 = p_75639_1_ + var4;
/*     */     
/*  96 */     if (var5 < 0.0F) {
/*     */       
/*  98 */       var5 += 360.0F;
/*     */     }
/* 100 */     else if (var5 > 360.0F) {
/*     */       
/* 102 */       var5 -= 360.0F;
/*     */     } 
/*     */     
/* 105 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_179917_d() {
/* 110 */     return this.posX;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_179919_e() {
/* 115 */     return this.posY;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_179918_f() {
/* 120 */     return this.posZ;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityMoveHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */