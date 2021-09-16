/*     */ package net.minecraft.util;
/*     */ 
/*     */ 
/*     */ public class AxisAlignedBB
/*     */ {
/*     */   public final double minX;
/*     */   public final double minY;
/*     */   public final double minZ;
/*     */   public final double maxX;
/*     */   public final double maxY;
/*     */   public final double maxZ;
/*     */   private static final String __OBFID = "CL_00000607";
/*     */   
/*     */   public AxisAlignedBB(double x1, double y1, double z1, double x2, double y2, double z2) {
/*  15 */     this.minX = Math.min(x1, x2);
/*  16 */     this.minY = Math.min(y1, y2);
/*  17 */     this.minZ = Math.min(z1, z2);
/*  18 */     this.maxX = Math.max(x1, x2);
/*  19 */     this.maxY = Math.max(y1, y2);
/*  20 */     this.maxZ = Math.max(z1, z2);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB(BlockPos p_i45554_1_, BlockPos p_i45554_2_) {
/*  25 */     this.minX = p_i45554_1_.getX();
/*  26 */     this.minY = p_i45554_1_.getY();
/*  27 */     this.minZ = p_i45554_1_.getZ();
/*  28 */     this.maxX = p_i45554_2_.getX();
/*  29 */     this.maxY = p_i45554_2_.getY();
/*  30 */     this.maxZ = p_i45554_2_.getZ();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB addCoord(double x, double y, double z) {
/*  38 */     double var7 = this.minX;
/*  39 */     double var9 = this.minY;
/*  40 */     double var11 = this.minZ;
/*  41 */     double var13 = this.maxX;
/*  42 */     double var15 = this.maxY;
/*  43 */     double var17 = this.maxZ;
/*     */     
/*  45 */     if (x < 0.0D) {
/*     */       
/*  47 */       var7 += x;
/*     */     }
/*  49 */     else if (x > 0.0D) {
/*     */       
/*  51 */       var13 += x;
/*     */     } 
/*     */     
/*  54 */     if (y < 0.0D) {
/*     */       
/*  56 */       var9 += y;
/*     */     }
/*  58 */     else if (y > 0.0D) {
/*     */       
/*  60 */       var15 += y;
/*     */     } 
/*     */     
/*  63 */     if (z < 0.0D) {
/*     */       
/*  65 */       var11 += z;
/*     */     }
/*  67 */     else if (z > 0.0D) {
/*     */       
/*  69 */       var17 += z;
/*     */     } 
/*     */     
/*  72 */     return new AxisAlignedBB(var7, var9, var11, var13, var15, var17);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB expand(double x, double y, double z) {
/*  81 */     double var7 = this.minX - x;
/*  82 */     double var9 = this.minY - y;
/*  83 */     double var11 = this.minZ - z;
/*  84 */     double var13 = this.maxX + x;
/*  85 */     double var15 = this.maxY + y;
/*  86 */     double var17 = this.maxZ + z;
/*  87 */     return new AxisAlignedBB(var7, var9, var11, var13, var15, var17);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB union(AxisAlignedBB other) {
/*  92 */     double var2 = Math.min(this.minX, other.minX);
/*  93 */     double var4 = Math.min(this.minY, other.minY);
/*  94 */     double var6 = Math.min(this.minZ, other.minZ);
/*  95 */     double var8 = Math.max(this.maxX, other.maxX);
/*  96 */     double var10 = Math.max(this.maxY, other.maxY);
/*  97 */     double var12 = Math.max(this.maxZ, other.maxZ);
/*  98 */     return new AxisAlignedBB(var2, var4, var6, var8, var10, var12);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AxisAlignedBB fromBounds(double p_178781_0_, double p_178781_2_, double p_178781_4_, double p_178781_6_, double p_178781_8_, double p_178781_10_) {
/* 106 */     double var12 = Math.min(p_178781_0_, p_178781_6_);
/* 107 */     double var14 = Math.min(p_178781_2_, p_178781_8_);
/* 108 */     double var16 = Math.min(p_178781_4_, p_178781_10_);
/* 109 */     double var18 = Math.max(p_178781_0_, p_178781_6_);
/* 110 */     double var20 = Math.max(p_178781_2_, p_178781_8_);
/* 111 */     double var22 = Math.max(p_178781_4_, p_178781_10_);
/* 112 */     return new AxisAlignedBB(var12, var14, var16, var18, var20, var22);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB offset(double x, double y, double z) {
/* 120 */     return new AxisAlignedBB(this.minX + x, this.minY + y, this.minZ + z, this.maxX + x, this.maxY + y, this.maxZ + z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double calculateXOffset(AxisAlignedBB other, double p_72316_2_) {
/* 130 */     if (other.maxY > this.minY && other.minY < this.maxY && other.maxZ > this.minZ && other.minZ < this.maxZ) {
/*     */ 
/*     */ 
/*     */       
/* 134 */       if (p_72316_2_ > 0.0D && other.maxX <= this.minX) {
/*     */         
/* 136 */         double var4 = this.minX - other.maxX;
/*     */         
/* 138 */         if (var4 < p_72316_2_)
/*     */         {
/* 140 */           p_72316_2_ = var4;
/*     */         }
/*     */       }
/* 143 */       else if (p_72316_2_ < 0.0D && other.minX >= this.maxX) {
/*     */         
/* 145 */         double var4 = this.maxX - other.minX;
/*     */         
/* 147 */         if (var4 > p_72316_2_)
/*     */         {
/* 149 */           p_72316_2_ = var4;
/*     */         }
/*     */       } 
/*     */       
/* 153 */       return p_72316_2_;
/*     */     } 
/*     */ 
/*     */     
/* 157 */     return p_72316_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double calculateYOffset(AxisAlignedBB other, double p_72323_2_) {
/* 168 */     if (other.maxX > this.minX && other.minX < this.maxX && other.maxZ > this.minZ && other.minZ < this.maxZ) {
/*     */ 
/*     */ 
/*     */       
/* 172 */       if (p_72323_2_ > 0.0D && other.maxY <= this.minY) {
/*     */         
/* 174 */         double var4 = this.minY - other.maxY;
/*     */         
/* 176 */         if (var4 < p_72323_2_)
/*     */         {
/* 178 */           p_72323_2_ = var4;
/*     */         }
/*     */       }
/* 181 */       else if (p_72323_2_ < 0.0D && other.minY >= this.maxY) {
/*     */         
/* 183 */         double var4 = this.maxY - other.minY;
/*     */         
/* 185 */         if (var4 > p_72323_2_)
/*     */         {
/* 187 */           p_72323_2_ = var4;
/*     */         }
/*     */       } 
/*     */       
/* 191 */       return p_72323_2_;
/*     */     } 
/*     */ 
/*     */     
/* 195 */     return p_72323_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double calculateZOffset(AxisAlignedBB other, double p_72322_2_) {
/* 206 */     if (other.maxX > this.minX && other.minX < this.maxX && other.maxY > this.minY && other.minY < this.maxY) {
/*     */ 
/*     */ 
/*     */       
/* 210 */       if (p_72322_2_ > 0.0D && other.maxZ <= this.minZ) {
/*     */         
/* 212 */         double var4 = this.minZ - other.maxZ;
/*     */         
/* 214 */         if (var4 < p_72322_2_)
/*     */         {
/* 216 */           p_72322_2_ = var4;
/*     */         }
/*     */       }
/* 219 */       else if (p_72322_2_ < 0.0D && other.minZ >= this.maxZ) {
/*     */         
/* 221 */         double var4 = this.maxZ - other.minZ;
/*     */         
/* 223 */         if (var4 > p_72322_2_)
/*     */         {
/* 225 */           p_72322_2_ = var4;
/*     */         }
/*     */       } 
/*     */       
/* 229 */       return p_72322_2_;
/*     */     } 
/*     */ 
/*     */     
/* 233 */     return p_72322_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean intersectsWith(AxisAlignedBB other) {
/* 242 */     return (other.maxX > this.minX && other.minX < this.maxX) ? ((other.maxY > this.minY && other.minY < this.maxY) ? ((other.maxZ > this.minZ && other.minZ < this.maxZ)) : false) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVecInside(Vec3 vec) {
/* 250 */     return (vec.xCoord > this.minX && vec.xCoord < this.maxX) ? ((vec.yCoord > this.minY && vec.yCoord < this.maxY) ? ((vec.zCoord > this.minZ && vec.zCoord < this.maxZ)) : false) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAverageEdgeLength() {
/* 258 */     double var1 = this.maxX - this.minX;
/* 259 */     double var3 = this.maxY - this.minY;
/* 260 */     double var5 = this.maxZ - this.minZ;
/* 261 */     return (var1 + var3 + var5) / 3.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB contract(double x, double y, double z) {
/* 269 */     double var7 = this.minX + x;
/* 270 */     double var9 = this.minY + y;
/* 271 */     double var11 = this.minZ + z;
/* 272 */     double var13 = this.maxX - x;
/* 273 */     double var15 = this.maxY - y;
/* 274 */     double var17 = this.maxZ - z;
/* 275 */     return new AxisAlignedBB(var7, var9, var11, var13, var15, var17);
/*     */   }
/*     */ 
/*     */   
/*     */   public MovingObjectPosition calculateIntercept(Vec3 p_72327_1_, Vec3 p_72327_2_) {
/* 280 */     Vec3 var3 = p_72327_1_.getIntermediateWithXValue(p_72327_2_, this.minX);
/* 281 */     Vec3 var4 = p_72327_1_.getIntermediateWithXValue(p_72327_2_, this.maxX);
/* 282 */     Vec3 var5 = p_72327_1_.getIntermediateWithYValue(p_72327_2_, this.minY);
/* 283 */     Vec3 var6 = p_72327_1_.getIntermediateWithYValue(p_72327_2_, this.maxY);
/* 284 */     Vec3 var7 = p_72327_1_.getIntermediateWithZValue(p_72327_2_, this.minZ);
/* 285 */     Vec3 var8 = p_72327_1_.getIntermediateWithZValue(p_72327_2_, this.maxZ);
/*     */     
/* 287 */     if (!isVecInYZ(var3))
/*     */     {
/* 289 */       var3 = null;
/*     */     }
/*     */     
/* 292 */     if (!isVecInYZ(var4))
/*     */     {
/* 294 */       var4 = null;
/*     */     }
/*     */     
/* 297 */     if (!isVecInXZ(var5))
/*     */     {
/* 299 */       var5 = null;
/*     */     }
/*     */     
/* 302 */     if (!isVecInXZ(var6))
/*     */     {
/* 304 */       var6 = null;
/*     */     }
/*     */     
/* 307 */     if (!isVecInXY(var7))
/*     */     {
/* 309 */       var7 = null;
/*     */     }
/*     */     
/* 312 */     if (!isVecInXY(var8))
/*     */     {
/* 314 */       var8 = null;
/*     */     }
/*     */     
/* 317 */     Vec3 var9 = null;
/*     */     
/* 319 */     if (var3 != null)
/*     */     {
/* 321 */       var9 = var3;
/*     */     }
/*     */     
/* 324 */     if (var4 != null && (var9 == null || p_72327_1_.squareDistanceTo(var4) < p_72327_1_.squareDistanceTo(var9)))
/*     */     {
/* 326 */       var9 = var4;
/*     */     }
/*     */     
/* 329 */     if (var5 != null && (var9 == null || p_72327_1_.squareDistanceTo(var5) < p_72327_1_.squareDistanceTo(var9)))
/*     */     {
/* 331 */       var9 = var5;
/*     */     }
/*     */     
/* 334 */     if (var6 != null && (var9 == null || p_72327_1_.squareDistanceTo(var6) < p_72327_1_.squareDistanceTo(var9)))
/*     */     {
/* 336 */       var9 = var6;
/*     */     }
/*     */     
/* 339 */     if (var7 != null && (var9 == null || p_72327_1_.squareDistanceTo(var7) < p_72327_1_.squareDistanceTo(var9)))
/*     */     {
/* 341 */       var9 = var7;
/*     */     }
/*     */     
/* 344 */     if (var8 != null && (var9 == null || p_72327_1_.squareDistanceTo(var8) < p_72327_1_.squareDistanceTo(var9)))
/*     */     {
/* 346 */       var9 = var8;
/*     */     }
/*     */     
/* 349 */     if (var9 == null)
/*     */     {
/* 351 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 355 */     EnumFacing var10 = null;
/*     */     
/* 357 */     if (var9 == var3) {
/*     */       
/* 359 */       var10 = EnumFacing.WEST;
/*     */     }
/* 361 */     else if (var9 == var4) {
/*     */       
/* 363 */       var10 = EnumFacing.EAST;
/*     */     }
/* 365 */     else if (var9 == var5) {
/*     */       
/* 367 */       var10 = EnumFacing.DOWN;
/*     */     }
/* 369 */     else if (var9 == var6) {
/*     */       
/* 371 */       var10 = EnumFacing.UP;
/*     */     }
/* 373 */     else if (var9 == var7) {
/*     */       
/* 375 */       var10 = EnumFacing.NORTH;
/*     */     }
/*     */     else {
/*     */       
/* 379 */       var10 = EnumFacing.SOUTH;
/*     */     } 
/*     */     
/* 382 */     return new MovingObjectPosition(var9, var10);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isVecInYZ(Vec3 vec) {
/* 391 */     return (vec == null) ? false : ((vec.yCoord >= this.minY && vec.yCoord <= this.maxY && vec.zCoord >= this.minZ && vec.zCoord <= this.maxZ));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isVecInXZ(Vec3 vec) {
/* 399 */     return (vec == null) ? false : ((vec.xCoord >= this.minX && vec.xCoord <= this.maxX && vec.zCoord >= this.minZ && vec.zCoord <= this.maxZ));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isVecInXY(Vec3 vec) {
/* 407 */     return (vec == null) ? false : ((vec.xCoord >= this.minX && vec.xCoord <= this.maxX && vec.yCoord >= this.minY && vec.yCoord <= this.maxY));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 412 */     return "box[" + this.minX + ", " + this.minY + ", " + this.minZ + " -> " + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\AxisAlignedBB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */