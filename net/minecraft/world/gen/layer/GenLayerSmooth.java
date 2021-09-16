/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ public class GenLayerSmooth
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000569";
/*    */   
/*    */   public GenLayerSmooth(long p_i2131_1_, GenLayer p_i2131_3_) {
/*  9 */     super(p_i2131_1_);
/* 10 */     this.parent = p_i2131_3_;
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
/* 30 */         int var13 = var9[var12 + 0 + (var11 + 1) * var7];
/* 31 */         int var14 = var9[var12 + 2 + (var11 + 1) * var7];
/* 32 */         int var15 = var9[var12 + 1 + (var11 + 0) * var7];
/* 33 */         int var16 = var9[var12 + 1 + (var11 + 2) * var7];
/* 34 */         int var17 = var9[var12 + 1 + (var11 + 1) * var7];
/*    */         
/* 36 */         if (var13 == var14 && var15 == var16) {
/*    */           
/* 38 */           initChunkSeed((var12 + areaX), (var11 + areaY));
/*    */           
/* 40 */           if (nextInt(2) == 0)
/*    */           {
/* 42 */             var17 = var13;
/*    */           }
/*    */           else
/*    */           {
/* 46 */             var17 = var15;
/*    */           }
/*    */         
/*    */         } else {
/*    */           
/* 51 */           if (var13 == var14)
/*    */           {
/* 53 */             var17 = var13;
/*    */           }
/*    */           
/* 56 */           if (var15 == var16)
/*    */           {
/* 58 */             var17 = var15;
/*    */           }
/*    */         } 
/*    */         
/* 62 */         var10[var12 + var11 * areaWidth] = var17;
/*    */       } 
/*    */     } 
/*    */     
/* 66 */     return var10;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerSmooth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */