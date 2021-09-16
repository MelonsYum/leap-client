/*     */ package net.minecraft.pathfinding;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathEntity
/*     */ {
/*     */   private final PathPoint[] points;
/*     */   private int currentPathIndex;
/*     */   private int pathLength;
/*     */   private static final String __OBFID = "CL_00000575";
/*     */   
/*     */   public PathEntity(PathPoint[] p_i2136_1_) {
/*  20 */     this.points = p_i2136_1_;
/*  21 */     this.pathLength = p_i2136_1_.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementPathIndex() {
/*  29 */     this.currentPathIndex++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFinished() {
/*  37 */     return (this.currentPathIndex >= this.pathLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint getFinalPathPoint() {
/*  45 */     return (this.pathLength > 0) ? this.points[this.pathLength - 1] : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint getPathPointFromIndex(int p_75877_1_) {
/*  53 */     return this.points[p_75877_1_];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentPathLength() {
/*  58 */     return this.pathLength;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentPathLength(int p_75871_1_) {
/*  63 */     this.pathLength = p_75871_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCurrentPathIndex() {
/*  68 */     return this.currentPathIndex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentPathIndex(int p_75872_1_) {
/*  73 */     this.currentPathIndex = p_75872_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getVectorFromIndex(Entity p_75881_1_, int p_75881_2_) {
/*  81 */     double var3 = (this.points[p_75881_2_]).xCoord + (int)(p_75881_1_.width + 1.0F) * 0.5D;
/*  82 */     double var5 = (this.points[p_75881_2_]).yCoord;
/*  83 */     double var7 = (this.points[p_75881_2_]).zCoord + (int)(p_75881_1_.width + 1.0F) * 0.5D;
/*  84 */     return new Vec3(var3, var5, var7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getPosition(Entity p_75878_1_) {
/*  92 */     return getVectorFromIndex(p_75878_1_, this.currentPathIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSamePath(PathEntity p_75876_1_) {
/* 100 */     if (p_75876_1_ == null)
/*     */     {
/* 102 */       return false;
/*     */     }
/* 104 */     if (p_75876_1_.points.length != this.points.length)
/*     */     {
/* 106 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 110 */     for (int var2 = 0; var2 < this.points.length; var2++) {
/*     */       
/* 112 */       if ((this.points[var2]).xCoord != (p_75876_1_.points[var2]).xCoord || (this.points[var2]).yCoord != (p_75876_1_.points[var2]).yCoord || (this.points[var2]).zCoord != (p_75876_1_.points[var2]).zCoord)
/*     */       {
/* 114 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDestinationSame(Vec3 p_75880_1_) {
/* 127 */     PathPoint var2 = getFinalPathPoint();
/* 128 */     return (var2 == null) ? false : ((var2.xCoord == (int)p_75880_1_.xCoord && var2.zCoord == (int)p_75880_1_.zCoord));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\pathfinding\PathEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */