/*     */ package net.minecraft.pathfinding;
/*     */ 
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
/*     */ public class PathPoint
/*     */ {
/*     */   public final int xCoord;
/*     */   public final int yCoord;
/*     */   public final int zCoord;
/*     */   private final int hash;
/*  20 */   int index = -1;
/*     */ 
/*     */   
/*     */   float totalPathDistance;
/*     */ 
/*     */   
/*     */   float distanceToNext;
/*     */ 
/*     */   
/*     */   float distanceToTarget;
/*     */ 
/*     */   
/*     */   PathPoint previous;
/*     */   
/*     */   public boolean visited;
/*     */   
/*     */   private static final String __OBFID = "CL_00000574";
/*     */ 
/*     */   
/*     */   public PathPoint(int p_i2135_1_, int p_i2135_2_, int p_i2135_3_) {
/*  40 */     this.xCoord = p_i2135_1_;
/*  41 */     this.yCoord = p_i2135_2_;
/*  42 */     this.zCoord = p_i2135_3_;
/*  43 */     this.hash = makeHash(p_i2135_1_, p_i2135_2_, p_i2135_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int makeHash(int p_75830_0_, int p_75830_1_, int p_75830_2_) {
/*  48 */     return p_75830_1_ & 0xFF | (p_75830_0_ & 0x7FFF) << 8 | (p_75830_2_ & 0x7FFF) << 24 | ((p_75830_0_ < 0) ? Integer.MIN_VALUE : 0) | ((p_75830_2_ < 0) ? 32768 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(PathPoint p_75829_1_) {
/*  56 */     float var2 = (p_75829_1_.xCoord - this.xCoord);
/*  57 */     float var3 = (p_75829_1_.yCoord - this.yCoord);
/*  58 */     float var4 = (p_75829_1_.zCoord - this.zCoord);
/*  59 */     return MathHelper.sqrt_float(var2 * var2 + var3 * var3 + var4 * var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceToSquared(PathPoint p_75832_1_) {
/*  67 */     float var2 = (p_75832_1_.xCoord - this.xCoord);
/*  68 */     float var3 = (p_75832_1_.yCoord - this.yCoord);
/*  69 */     float var4 = (p_75832_1_.zCoord - this.zCoord);
/*  70 */     return var2 * var2 + var3 * var3 + var4 * var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  75 */     if (!(p_equals_1_ instanceof PathPoint))
/*     */     {
/*  77 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  81 */     PathPoint var2 = (PathPoint)p_equals_1_;
/*  82 */     return (this.hash == var2.hash && this.xCoord == var2.xCoord && this.yCoord == var2.yCoord && this.zCoord == var2.zCoord);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  88 */     return this.hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAssigned() {
/*  96 */     return (this.index >= 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 101 */     return String.valueOf(this.xCoord) + ", " + this.yCoord + ", " + this.zCoord;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\pathfinding\PathPoint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */