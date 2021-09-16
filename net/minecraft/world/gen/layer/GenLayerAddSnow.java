/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ public class GenLayerAddSnow
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000553";
/*    */   
/*    */   public GenLayerAddSnow(long p_i2121_1_, GenLayer p_i2121_3_) {
/*  9 */     super(p_i2121_1_);
/* 10 */     this.parent = p_i2121_3_;
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
/* 30 */         int var13 = var9[var12 + 1 + (var11 + 1) * var7];
/* 31 */         initChunkSeed((var12 + areaX), (var11 + areaY));
/*    */         
/* 33 */         if (var13 == 0) {
/*    */           
/* 35 */           var10[var12 + var11 * areaWidth] = 0;
/*    */         } else {
/*    */           byte var15;
/*    */           
/* 39 */           int var14 = nextInt(6);
/*    */ 
/*    */           
/* 42 */           if (var14 == 0) {
/*    */             
/* 44 */             var15 = 4;
/*    */           }
/* 46 */           else if (var14 <= 1) {
/*    */             
/* 48 */             var15 = 3;
/*    */           }
/*    */           else {
/*    */             
/* 52 */             var15 = 1;
/*    */           } 
/*    */           
/* 55 */           var10[var12 + var11 * areaWidth] = var15;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     return var10;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerAddSnow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */