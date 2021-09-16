/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ public class GenLayerRemoveTooMuchOcean
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000564";
/*    */   
/*    */   public GenLayerRemoveTooMuchOcean(long p_i45480_1_, GenLayer p_i45480_3_) {
/*  9 */     super(p_i45480_1_);
/* 10 */     this.parent = p_i45480_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/* 19 */     int var5 = areaX - 1;
/* 20 */     int var6 = areaY - 1;
/* 21 */     int var7 = areaWidth + 2;
/* 22 */     int var8 = areaHeight + 2;
/* 23 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8);
/* 24 */     int[] var10 = IntCache.getIntCache(areaWidth * areaHeight);
/*    */     
/* 26 */     for (int var11 = 0; var11 < areaHeight; var11++) {
/*    */       
/* 28 */       for (int var12 = 0; var12 < areaWidth; var12++) {
/*    */         
/* 30 */         int var13 = var9[var12 + 1 + (var11 + 1 - 1) * (areaWidth + 2)];
/* 31 */         int var14 = var9[var12 + 1 + 1 + (var11 + 1) * (areaWidth + 2)];
/* 32 */         int var15 = var9[var12 + 1 - 1 + (var11 + 1) * (areaWidth + 2)];
/* 33 */         int var16 = var9[var12 + 1 + (var11 + 1 + 1) * (areaWidth + 2)];
/* 34 */         int var17 = var9[var12 + 1 + (var11 + 1) * var7];
/* 35 */         var10[var12 + var11 * areaWidth] = var17;
/* 36 */         initChunkSeed((var12 + areaX), (var11 + areaY));
/*    */         
/* 38 */         if (var17 == 0 && var13 == 0 && var14 == 0 && var15 == 0 && var16 == 0 && nextInt(2) == 0)
/*    */         {
/* 40 */           var10[var12 + var11 * areaWidth] = 1;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     return var10;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerRemoveTooMuchOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */