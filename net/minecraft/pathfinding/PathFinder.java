/*     */ package net.minecraft.pathfinding;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.pathfinder.NodeProcessor;
/*     */ 
/*     */ 
/*     */ public class PathFinder
/*     */ {
/*  11 */   private Path path = new Path();
/*     */ 
/*     */   
/*  14 */   private PathPoint[] pathOptions = new PathPoint[32];
/*     */   
/*     */   private NodeProcessor field_176190_c;
/*     */   private static final String __OBFID = "CL_00000576";
/*     */   
/*     */   public PathFinder(NodeProcessor p_i45557_1_) {
/*  20 */     this.field_176190_c = p_i45557_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity func_176188_a(IBlockAccess p_176188_1_, Entity p_176188_2_, Entity p_176188_3_, float p_176188_4_) {
/*  25 */     return func_176189_a(p_176188_1_, p_176188_2_, p_176188_3_.posX, (p_176188_3_.getEntityBoundingBox()).minY, p_176188_3_.posZ, p_176188_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity func_180782_a(IBlockAccess p_180782_1_, Entity p_180782_2_, BlockPos p_180782_3_, float p_180782_4_) {
/*  30 */     return func_176189_a(p_180782_1_, p_180782_2_, (p_180782_3_.getX() + 0.5F), (p_180782_3_.getY() + 0.5F), (p_180782_3_.getZ() + 0.5F), p_180782_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   private PathEntity func_176189_a(IBlockAccess p_176189_1_, Entity p_176189_2_, double p_176189_3_, double p_176189_5_, double p_176189_7_, float p_176189_9_) {
/*  35 */     this.path.clearPath();
/*  36 */     this.field_176190_c.func_176162_a(p_176189_1_, p_176189_2_);
/*  37 */     PathPoint var10 = this.field_176190_c.func_176161_a(p_176189_2_);
/*  38 */     PathPoint var11 = this.field_176190_c.func_176160_a(p_176189_2_, p_176189_3_, p_176189_5_, p_176189_7_);
/*  39 */     PathEntity var12 = func_176187_a(p_176189_2_, var10, var11, p_176189_9_);
/*  40 */     this.field_176190_c.func_176163_a();
/*  41 */     return var12;
/*     */   }
/*     */ 
/*     */   
/*     */   private PathEntity func_176187_a(Entity p_176187_1_, PathPoint p_176187_2_, PathPoint p_176187_3_, float p_176187_4_) {
/*  46 */     p_176187_2_.totalPathDistance = 0.0F;
/*  47 */     p_176187_2_.distanceToNext = p_176187_2_.distanceToSquared(p_176187_3_);
/*  48 */     p_176187_2_.distanceToTarget = p_176187_2_.distanceToNext;
/*  49 */     this.path.clearPath();
/*  50 */     this.path.addPoint(p_176187_2_);
/*  51 */     PathPoint var5 = p_176187_2_;
/*     */     
/*  53 */     while (!this.path.isPathEmpty()) {
/*     */       
/*  55 */       PathPoint var6 = this.path.dequeue();
/*     */       
/*  57 */       if (var6.equals(p_176187_3_))
/*     */       {
/*  59 */         return createEntityPath(p_176187_2_, p_176187_3_);
/*     */       }
/*     */       
/*  62 */       if (var6.distanceToSquared(p_176187_3_) < var5.distanceToSquared(p_176187_3_))
/*     */       {
/*  64 */         var5 = var6;
/*     */       }
/*     */       
/*  67 */       var6.visited = true;
/*  68 */       int var7 = this.field_176190_c.func_176164_a(this.pathOptions, p_176187_1_, var6, p_176187_3_, p_176187_4_);
/*     */       
/*  70 */       for (int var8 = 0; var8 < var7; var8++) {
/*     */         
/*  72 */         PathPoint var9 = this.pathOptions[var8];
/*  73 */         float var10 = var6.totalPathDistance + var6.distanceToSquared(var9);
/*     */         
/*  75 */         if (var10 < p_176187_4_ * 2.0F && (!var9.isAssigned() || var10 < var9.totalPathDistance)) {
/*     */           
/*  77 */           var9.previous = var6;
/*  78 */           var9.totalPathDistance = var10;
/*  79 */           var9.distanceToNext = var9.distanceToSquared(p_176187_3_);
/*     */           
/*  81 */           if (var9.isAssigned()) {
/*     */             
/*  83 */             this.path.changeDistance(var9, var9.totalPathDistance + var9.distanceToNext);
/*     */           }
/*     */           else {
/*     */             
/*  87 */             var9.distanceToTarget = var9.totalPathDistance + var9.distanceToNext;
/*  88 */             this.path.addPoint(var9);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     if (var5 == p_176187_2_)
/*     */     {
/*  96 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 100 */     return createEntityPath(p_176187_2_, var5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PathEntity createEntityPath(PathPoint p_75853_1_, PathPoint p_75853_2_) {
/* 109 */     int var3 = 1;
/*     */     
/*     */     PathPoint var4;
/* 112 */     for (var4 = p_75853_2_; var4.previous != null; var4 = var4.previous)
/*     */     {
/* 114 */       var3++;
/*     */     }
/*     */     
/* 117 */     PathPoint[] var5 = new PathPoint[var3];
/* 118 */     var4 = p_75853_2_;
/* 119 */     var3--;
/*     */     
/* 121 */     for (var5[var3] = p_75853_2_; var4.previous != null; var5[var3] = var4) {
/*     */       
/* 123 */       var4 = var4.previous;
/* 124 */       var3--;
/*     */     } 
/*     */     
/* 127 */     return new PathEntity(var5);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\pathfinding\PathFinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */