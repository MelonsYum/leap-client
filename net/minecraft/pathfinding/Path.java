/*     */ package net.minecraft.pathfinding;
/*     */ 
/*     */ 
/*     */ public class Path
/*     */ {
/*   6 */   private PathPoint[] pathPoints = new PathPoint[1024];
/*     */ 
/*     */   
/*     */   private int count;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000573";
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint addPoint(PathPoint point) {
/*  17 */     if (point.index >= 0)
/*     */     {
/*  19 */       throw new IllegalStateException("OW KNOWS!");
/*     */     }
/*     */ 
/*     */     
/*  23 */     if (this.count == this.pathPoints.length) {
/*     */       
/*  25 */       PathPoint[] var2 = new PathPoint[this.count << 1];
/*  26 */       System.arraycopy(this.pathPoints, 0, var2, 0, this.count);
/*  27 */       this.pathPoints = var2;
/*     */     } 
/*     */     
/*  30 */     this.pathPoints[this.count] = point;
/*  31 */     point.index = this.count;
/*  32 */     sortBack(this.count++);
/*  33 */     return point;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPath() {
/*  42 */     this.count = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PathPoint dequeue() {
/*  50 */     PathPoint var1 = this.pathPoints[0];
/*  51 */     this.pathPoints[0] = this.pathPoints[--this.count];
/*  52 */     this.pathPoints[this.count] = null;
/*     */     
/*  54 */     if (this.count > 0)
/*     */     {
/*  56 */       sortForward(0);
/*     */     }
/*     */     
/*  59 */     var1.index = -1;
/*  60 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeDistance(PathPoint p_75850_1_, float p_75850_2_) {
/*  68 */     float var3 = p_75850_1_.distanceToTarget;
/*  69 */     p_75850_1_.distanceToTarget = p_75850_2_;
/*     */     
/*  71 */     if (p_75850_2_ < var3) {
/*     */       
/*  73 */       sortBack(p_75850_1_.index);
/*     */     }
/*     */     else {
/*     */       
/*  77 */       sortForward(p_75850_1_.index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sortBack(int p_75847_1_) {
/*  86 */     PathPoint var2 = this.pathPoints[p_75847_1_];
/*     */ 
/*     */     
/*  89 */     for (float var3 = var2.distanceToTarget; p_75847_1_ > 0; p_75847_1_ = var4) {
/*     */       
/*  91 */       int var4 = p_75847_1_ - 1 >> 1;
/*  92 */       PathPoint var5 = this.pathPoints[var4];
/*     */       
/*  94 */       if (var3 >= var5.distanceToTarget) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/*  99 */       this.pathPoints[p_75847_1_] = var5;
/* 100 */       var5.index = p_75847_1_;
/*     */     } 
/*     */     
/* 103 */     this.pathPoints[p_75847_1_] = var2;
/* 104 */     var2.index = p_75847_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sortForward(int p_75846_1_) {
/* 112 */     PathPoint var2 = this.pathPoints[p_75846_1_];
/* 113 */     float var3 = var2.distanceToTarget;
/*     */     while (true) {
/*     */       PathPoint var8;
/*     */       float var9;
/* 117 */       int var4 = 1 + (p_75846_1_ << 1);
/* 118 */       int var5 = var4 + 1;
/*     */       
/* 120 */       if (var4 >= this.count) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 125 */       PathPoint var6 = this.pathPoints[var4];
/* 126 */       float var7 = var6.distanceToTarget;
/*     */ 
/*     */ 
/*     */       
/* 130 */       if (var5 >= this.count) {
/*     */         
/* 132 */         var8 = null;
/* 133 */         var9 = Float.POSITIVE_INFINITY;
/*     */       }
/*     */       else {
/*     */         
/* 137 */         var8 = this.pathPoints[var5];
/* 138 */         var9 = var8.distanceToTarget;
/*     */       } 
/*     */       
/* 141 */       if (var7 < var9) {
/*     */         
/* 143 */         if (var7 >= var3) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 148 */         this.pathPoints[p_75846_1_] = var6;
/* 149 */         var6.index = p_75846_1_;
/* 150 */         p_75846_1_ = var4;
/*     */         
/*     */         continue;
/*     */       } 
/* 154 */       if (var9 >= var3) {
/*     */         break;
/*     */       }
/*     */ 
/*     */       
/* 159 */       this.pathPoints[p_75846_1_] = var8;
/* 160 */       var8.index = p_75846_1_;
/* 161 */       p_75846_1_ = var5;
/*     */     } 
/*     */ 
/*     */     
/* 165 */     this.pathPoints[p_75846_1_] = var2;
/* 166 */     var2.index = p_75846_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPathEmpty() {
/* 174 */     return (this.count == 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\pathfinding\Path.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */