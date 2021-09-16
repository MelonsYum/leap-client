/*    */ package optifine;
/*    */ 
/*    */ public class CacheLocal
/*    */ {
/*  5 */   private int maxX = 18;
/*  6 */   private int maxY = 128;
/*  7 */   private int maxZ = 18;
/*  8 */   private int offsetX = 0;
/*  9 */   private int offsetY = 0;
/* 10 */   private int offsetZ = 0;
/* 11 */   private int[][][] cache = null;
/* 12 */   private int[] lastZs = null;
/* 13 */   private int lastDz = 0;
/*    */ 
/*    */   
/*    */   public CacheLocal(int maxX, int maxY, int maxZ) {
/* 17 */     this.maxX = maxX;
/* 18 */     this.maxY = maxY;
/* 19 */     this.maxZ = maxZ;
/* 20 */     this.cache = new int[maxX][maxY][maxZ];
/* 21 */     resetCache();
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetCache() {
/* 26 */     for (int x = 0; x < this.maxX; x++) {
/*    */       
/* 28 */       int[][] ys = this.cache[x];
/*    */       
/* 30 */       for (int y = 0; y < this.maxY; y++) {
/*    */         
/* 32 */         int[] zs = ys[y];
/*    */         
/* 34 */         for (int z = 0; z < this.maxZ; z++)
/*    */         {
/* 36 */           zs[z] = -1;
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOffset(int x, int y, int z) {
/* 44 */     this.offsetX = x;
/* 45 */     this.offsetY = y;
/* 46 */     this.offsetZ = z;
/* 47 */     resetCache();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int get(int x, int y, int z) {
/*    */     try {
/* 54 */       this.lastZs = this.cache[x - this.offsetX][y - this.offsetY];
/* 55 */       this.lastDz = z - this.offsetZ;
/* 56 */       return this.lastZs[this.lastDz];
/*    */     }
/* 58 */     catch (ArrayIndexOutOfBoundsException var5) {
/*    */       
/* 60 */       var5.printStackTrace();
/* 61 */       return -1;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLast(int val) {
/*    */     try {
/* 69 */       this.lastZs[this.lastDz] = val;
/*    */     }
/* 71 */     catch (Exception var3) {
/*    */       
/* 73 */       var3.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CacheLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */