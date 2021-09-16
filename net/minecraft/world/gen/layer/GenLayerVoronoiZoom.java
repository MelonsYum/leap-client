/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ public class GenLayerVoronoiZoom
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000571";
/*    */   
/*    */   public GenLayerVoronoiZoom(long p_i2133_1_, GenLayer p_i2133_3_) {
/*  9 */     super(p_i2133_1_);
/* 10 */     this.parent = p_i2133_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/* 19 */     areaX -= 2;
/* 20 */     areaY -= 2;
/* 21 */     int var5 = areaX >> 2;
/* 22 */     int var6 = areaY >> 2;
/* 23 */     int var7 = (areaWidth >> 2) + 2;
/* 24 */     int var8 = (areaHeight >> 2) + 2;
/* 25 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8);
/* 26 */     int var10 = var7 - 1 << 2;
/* 27 */     int var11 = var8 - 1 << 2;
/* 28 */     int[] var12 = IntCache.getIntCache(var10 * var11);
/*    */ 
/*    */     
/* 31 */     for (int var13 = 0; var13 < var8 - 1; var13++) {
/*    */       
/* 33 */       int i = 0;
/* 34 */       int var15 = var9[i + 0 + (var13 + 0) * var7];
/*    */       
/* 36 */       for (int var16 = var9[i + 0 + (var13 + 1) * var7]; i < var7 - 1; i++) {
/*    */         
/* 38 */         double var17 = 3.6D;
/* 39 */         initChunkSeed((i + var5 << 2), (var13 + var6 << 2));
/* 40 */         double var19 = (nextInt(1024) / 1024.0D - 0.5D) * 3.6D;
/* 41 */         double var21 = (nextInt(1024) / 1024.0D - 0.5D) * 3.6D;
/* 42 */         initChunkSeed((i + var5 + 1 << 2), (var13 + var6 << 2));
/* 43 */         double var23 = (nextInt(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
/* 44 */         double var25 = (nextInt(1024) / 1024.0D - 0.5D) * 3.6D;
/* 45 */         initChunkSeed((i + var5 << 2), (var13 + var6 + 1 << 2));
/* 46 */         double var27 = (nextInt(1024) / 1024.0D - 0.5D) * 3.6D;
/* 47 */         double var29 = (nextInt(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
/* 48 */         initChunkSeed((i + var5 + 1 << 2), (var13 + var6 + 1 << 2));
/* 49 */         double var31 = (nextInt(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
/* 50 */         double var33 = (nextInt(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
/* 51 */         int var35 = var9[i + 1 + (var13 + 0) * var7] & 0xFF;
/* 52 */         int var36 = var9[i + 1 + (var13 + 1) * var7] & 0xFF;
/*    */         
/* 54 */         for (int var37 = 0; var37 < 4; var37++) {
/*    */           
/* 56 */           int var38 = ((var13 << 2) + var37) * var10 + (i << 2);
/*    */           
/* 58 */           for (int var39 = 0; var39 < 4; var39++) {
/*    */             
/* 60 */             double var40 = (var37 - var21) * (var37 - var21) + (var39 - var19) * (var39 - var19);
/* 61 */             double var42 = (var37 - var25) * (var37 - var25) + (var39 - var23) * (var39 - var23);
/* 62 */             double var44 = (var37 - var29) * (var37 - var29) + (var39 - var27) * (var39 - var27);
/* 63 */             double var46 = (var37 - var33) * (var37 - var33) + (var39 - var31) * (var39 - var31);
/*    */             
/* 65 */             if (var40 < var42 && var40 < var44 && var40 < var46) {
/*    */               
/* 67 */               var12[var38++] = var15;
/*    */             }
/* 69 */             else if (var42 < var40 && var42 < var44 && var42 < var46) {
/*    */               
/* 71 */               var12[var38++] = var35;
/*    */             }
/* 73 */             else if (var44 < var40 && var44 < var42 && var44 < var46) {
/*    */               
/* 75 */               var12[var38++] = var16;
/*    */             }
/*    */             else {
/*    */               
/* 79 */               var12[var38++] = var36;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */         
/* 84 */         var15 = var35;
/* 85 */         var16 = var36;
/*    */       } 
/*    */     } 
/*    */     
/* 89 */     int[] var48 = IntCache.getIntCache(areaWidth * areaHeight);
/*    */     
/* 91 */     for (int var14 = 0; var14 < areaHeight; var14++)
/*    */     {
/* 93 */       System.arraycopy(var12, (var14 + (areaY & 0x3)) * var10 + (areaX & 0x3), var48, var14 * areaWidth, areaWidth);
/*    */     }
/*    */     
/* 96 */     return var48;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerVoronoiZoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */