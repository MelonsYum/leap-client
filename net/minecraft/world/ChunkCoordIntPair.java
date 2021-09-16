/*     */ package net.minecraft.world;
/*     */ 
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkCoordIntPair
/*     */ {
/*     */   public final int chunkXPos;
/*     */   public final int chunkZPos;
/*     */   private static final String __OBFID = "CL_00000133";
/*  13 */   private int cachedHashCode = 0;
/*     */ 
/*     */   
/*     */   public ChunkCoordIntPair(int x, int z) {
/*  17 */     this.chunkXPos = x;
/*  18 */     this.chunkZPos = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static long chunkXZ2Int(int x, int z) {
/*  26 */     return x & 0xFFFFFFFFL | (z & 0xFFFFFFFFL) << 32L;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  31 */     if (this.cachedHashCode == 0) {
/*     */       
/*  33 */       int var1 = 1664525 * this.chunkXPos + 1013904223;
/*  34 */       int var2 = 1664525 * (this.chunkZPos ^ 0xDEADBEEF) + 1013904223;
/*  35 */       this.cachedHashCode = var1 ^ var2;
/*     */     } 
/*     */     
/*  38 */     return this.cachedHashCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  43 */     if (this == p_equals_1_)
/*     */     {
/*  45 */       return true;
/*     */     }
/*  47 */     if (!(p_equals_1_ instanceof ChunkCoordIntPair))
/*     */     {
/*  49 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  53 */     ChunkCoordIntPair var2 = (ChunkCoordIntPair)p_equals_1_;
/*  54 */     return (this.chunkXPos == var2.chunkXPos && this.chunkZPos == var2.chunkZPos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCenterXPos() {
/*  60 */     return (this.chunkXPos << 4) + 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCenterZPosition() {
/*  65 */     return (this.chunkZPos << 4) + 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXStart() {
/*  73 */     return this.chunkXPos << 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getZStart() {
/*  81 */     return this.chunkZPos << 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXEnd() {
/*  89 */     return (this.chunkXPos << 4) + 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getZEnd() {
/*  97 */     return (this.chunkZPos << 4) + 15;
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
/*     */   public BlockPos getBlock(int x, int y, int z) {
/* 109 */     return new BlockPos((this.chunkXPos << 4) + x, y, (this.chunkZPos << 4) + z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPos getCenterBlock(int y) {
/* 119 */     return new BlockPos(getCenterXPos(), y, getCenterZPosition());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 124 */     return "[" + this.chunkXPos + ", " + this.chunkZPos + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\ChunkCoordIntPair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */