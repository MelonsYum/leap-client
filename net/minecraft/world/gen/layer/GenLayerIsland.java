/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ public class GenLayerIsland
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000558";
/*    */   
/*    */   public GenLayerIsland(long p_i2124_1_) {
/*  9 */     super(p_i2124_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/* 18 */     int[] var5 = IntCache.getIntCache(areaWidth * areaHeight);
/*    */     
/* 20 */     for (int var6 = 0; var6 < areaHeight; var6++) {
/*    */       
/* 22 */       for (int var7 = 0; var7 < areaWidth; var7++) {
/*    */         
/* 24 */         initChunkSeed((areaX + var7), (areaY + var6));
/* 25 */         var5[var7 + var6 * areaWidth] = (nextInt(10) == 0) ? 1 : 0;
/*    */       } 
/*    */     } 
/*    */     
/* 29 */     if (areaX > -areaWidth && areaX <= 0 && areaY > -areaHeight && areaY <= 0)
/*    */     {
/* 31 */       var5[-areaX + -areaY * areaWidth] = 1;
/*    */     }
/*    */     
/* 34 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */