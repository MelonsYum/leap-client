/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ public class GenLayerRiverInit
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000565";
/*    */   
/*    */   public GenLayerRiverInit(long p_i2127_1_, GenLayer p_i2127_3_) {
/*  9 */     super(p_i2127_1_);
/* 10 */     this.parent = p_i2127_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/* 19 */     int[] var5 = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
/* 20 */     int[] var6 = IntCache.getIntCache(areaWidth * areaHeight);
/*    */     
/* 22 */     for (int var7 = 0; var7 < areaHeight; var7++) {
/*    */       
/* 24 */       for (int var8 = 0; var8 < areaWidth; var8++) {
/*    */         
/* 26 */         initChunkSeed((var8 + areaX), (var7 + areaY));
/* 27 */         var6[var8 + var7 * areaWidth] = (var5[var8 + var7 * areaWidth] > 0) ? (nextInt(299999) + 2) : 0;
/*    */       } 
/*    */     } 
/*    */     
/* 31 */     return var6;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerRiverInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */