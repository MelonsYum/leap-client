/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ 
/*     */ public class Vec3i
/*     */   implements Comparable
/*     */ {
/*   8 */   public static final Vec3i NULL_VECTOR = new Vec3i(0, 0, 0);
/*     */ 
/*     */   
/*     */   private final int x;
/*     */ 
/*     */   
/*     */   private final int y;
/*     */   
/*     */   private final int z;
/*     */   
/*     */   private static final String __OBFID = "CL_00002315";
/*     */ 
/*     */   
/*     */   public Vec3i(int p_i46007_1_, int p_i46007_2_, int p_i46007_3_) {
/*  22 */     this.x = p_i46007_1_;
/*  23 */     this.y = p_i46007_2_;
/*  24 */     this.z = p_i46007_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3i(double p_i46008_1_, double p_i46008_3_, double p_i46008_5_) {
/*  29 */     this(MathHelper.floor_double(p_i46008_1_), MathHelper.floor_double(p_i46008_3_), MathHelper.floor_double(p_i46008_5_));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  34 */     if (this == p_equals_1_)
/*     */     {
/*  36 */       return true;
/*     */     }
/*  38 */     if (!(p_equals_1_ instanceof Vec3i))
/*     */     {
/*  40 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  44 */     Vec3i var2 = (Vec3i)p_equals_1_;
/*  45 */     return (getX() != var2.getX()) ? false : ((getY() != var2.getY()) ? false : ((getZ() == var2.getZ())));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  51 */     return (getY() + getZ() * 31) * 31 + getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Vec3i p_177953_1_) {
/*  56 */     return (getY() == p_177953_1_.getY()) ? ((getZ() == p_177953_1_.getZ()) ? (getX() - p_177953_1_.getX()) : (getZ() - p_177953_1_.getZ())) : (getY() - p_177953_1_.getY());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getX() {
/*  64 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getY() {
/*  72 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getZ() {
/*  80 */     return this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3i crossProduct(Vec3i vec) {
/*  88 */     return new Vec3i(getY() * vec.getZ() - getZ() * vec.getY(), getZ() * vec.getX() - getX() * vec.getZ(), getX() * vec.getY() - getY() * vec.getX());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distanceSq(double toX, double toY, double toZ) {
/* 100 */     double var7 = getX() - toX;
/* 101 */     double var9 = getY() - toY;
/* 102 */     double var11 = getZ() - toZ;
/* 103 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distanceSqToCenter(double p_177957_1_, double p_177957_3_, double p_177957_5_) {
/* 111 */     double var7 = getX() + 0.5D - p_177957_1_;
/* 112 */     double var9 = getY() + 0.5D - p_177957_3_;
/* 113 */     double var11 = getZ() + 0.5D - p_177957_5_;
/* 114 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distanceSq(Vec3i to) {
/* 122 */     return distanceSq(to.getX(), to.getY(), to.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 127 */     return Objects.toStringHelper(this).add("x", getX()).add("y", getY()).add("z", getZ()).toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object p_compareTo_1_) {
/* 132 */     return compareTo((Vec3i)p_compareTo_1_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\Vec3i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */