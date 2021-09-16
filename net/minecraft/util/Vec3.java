/*     */ package net.minecraft.util;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Vec3
/*     */ {
/*     */   public final double xCoord;
/*     */   public final double yCoord;
/*     */   public final double zCoord;
/*     */   private static final String __OBFID = "CL_00000612";
/*     */   
/*     */   public Vec3(double x, double y, double z) {
/*  17 */     if (x == -0.0D)
/*     */     {
/*  19 */       x = 0.0D;
/*     */     }
/*     */     
/*  22 */     if (y == -0.0D)
/*     */     {
/*  24 */       y = 0.0D;
/*     */     }
/*     */     
/*  27 */     if (z == -0.0D)
/*     */     {
/*  29 */       z = 0.0D;
/*     */     }
/*     */     
/*  32 */     this.xCoord = x;
/*  33 */     this.yCoord = y;
/*  34 */     this.zCoord = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 subtractReverse(Vec3 vec) {
/*  42 */     return new Vec3(vec.xCoord - this.xCoord, vec.yCoord - this.yCoord, vec.zCoord - this.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 normalize() {
/*  50 */     double var1 = MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
/*  51 */     return (var1 < 1.0E-4D) ? new Vec3(0.0D, 0.0D, 0.0D) : new Vec3(this.xCoord / var1, this.yCoord / var1, this.zCoord / var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public double dotProduct(Vec3 vec) {
/*  56 */     return this.xCoord * vec.xCoord + this.yCoord * vec.yCoord + this.zCoord * vec.zCoord;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 crossProduct(Vec3 vec) {
/*  64 */     return new Vec3(this.yCoord * vec.zCoord - this.zCoord * vec.yCoord, this.zCoord * vec.xCoord - this.xCoord * vec.zCoord, this.xCoord * vec.yCoord - this.yCoord * vec.xCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 subtract(Vec3 p_178788_1_) {
/*  69 */     return subtract(p_178788_1_.xCoord, p_178788_1_.yCoord, p_178788_1_.zCoord);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 subtract(double p_178786_1_, double p_178786_3_, double p_178786_5_) {
/*  74 */     return addVector(-p_178786_1_, -p_178786_3_, -p_178786_5_);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 add(Vec3 p_178787_1_) {
/*  79 */     return addVector(p_178787_1_.xCoord, p_178787_1_.yCoord, p_178787_1_.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 addVector(double x, double y, double z) {
/*  88 */     return new Vec3(this.xCoord + x, this.yCoord + y, this.zCoord + z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distanceTo(Vec3 vec) {
/*  96 */     double var2 = vec.xCoord - this.xCoord;
/*  97 */     double var4 = vec.yCoord - this.yCoord;
/*  98 */     double var6 = vec.zCoord - this.zCoord;
/*  99 */     return MathHelper.sqrt_double(var2 * var2 + var4 * var4 + var6 * var6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double squareDistanceTo(Vec3 vec) {
/* 107 */     double var2 = vec.xCoord - this.xCoord;
/* 108 */     double var4 = vec.yCoord - this.yCoord;
/* 109 */     double var6 = vec.zCoord - this.zCoord;
/* 110 */     return var2 * var2 + var4 * var4 + var6 * var6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double lengthVector() {
/* 118 */     return MathHelper.sqrt_double(this.xCoord * this.xCoord + this.yCoord * this.yCoord + this.zCoord * this.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getIntermediateWithXValue(Vec3 vec, double x) {
/* 127 */     double var4 = vec.xCoord - this.xCoord;
/* 128 */     double var6 = vec.yCoord - this.yCoord;
/* 129 */     double var8 = vec.zCoord - this.zCoord;
/*     */     
/* 131 */     if (var4 * var4 < 1.0000000116860974E-7D)
/*     */     {
/* 133 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 137 */     double var10 = (x - this.xCoord) / var4;
/* 138 */     return (var10 >= 0.0D && var10 <= 1.0D) ? new Vec3(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getIntermediateWithYValue(Vec3 vec, double y) {
/* 148 */     double var4 = vec.xCoord - this.xCoord;
/* 149 */     double var6 = vec.yCoord - this.yCoord;
/* 150 */     double var8 = vec.zCoord - this.zCoord;
/*     */     
/* 152 */     if (var6 * var6 < 1.0000000116860974E-7D)
/*     */     {
/* 154 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 158 */     double var10 = (y - this.yCoord) / var6;
/* 159 */     return (var10 >= 0.0D && var10 <= 1.0D) ? new Vec3(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getIntermediateWithZValue(Vec3 vec, double z) {
/* 169 */     double var4 = vec.xCoord - this.xCoord;
/* 170 */     double var6 = vec.yCoord - this.yCoord;
/* 171 */     double var8 = vec.zCoord - this.zCoord;
/*     */     
/* 173 */     if (var8 * var8 < 1.0000000116860974E-7D)
/*     */     {
/* 175 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 179 */     double var10 = (z - this.zCoord) / var8;
/* 180 */     return (var10 >= 0.0D && var10 <= 1.0D) ? new Vec3(this.xCoord + var4 * var10, this.yCoord + var6 * var10, this.zCoord + var8 * var10) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 186 */     return "(" + this.xCoord + ", " + this.yCoord + ", " + this.zCoord + ")";
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 rotatePitch(float p_178789_1_) {
/* 191 */     float var2 = MathHelper.cos(p_178789_1_);
/* 192 */     float var3 = MathHelper.sin(p_178789_1_);
/* 193 */     double var4 = this.xCoord;
/* 194 */     double var6 = this.yCoord * var2 + this.zCoord * var3;
/* 195 */     double var8 = this.zCoord * var2 - this.yCoord * var3;
/* 196 */     return new Vec3(var4, var6, var8);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 rotateYaw(float p_178785_1_) {
/* 201 */     float var2 = MathHelper.cos(p_178785_1_);
/* 202 */     float var3 = MathHelper.sin(p_178785_1_);
/* 203 */     double var4 = this.xCoord * var2 + this.zCoord * var3;
/* 204 */     double var6 = this.yCoord;
/* 205 */     double var8 = this.zCoord * var2 - this.xCoord * var3;
/* 206 */     return new Vec3(var4, var6, var8);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\Vec3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */