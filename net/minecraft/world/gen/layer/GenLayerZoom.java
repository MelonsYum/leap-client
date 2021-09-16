/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ public class GenLayerZoom
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000572";
/*    */   
/*    */   public GenLayerZoom(long p_i2134_1_, GenLayer p_i2134_3_) {
/*  9 */     super(p_i2134_1_);
/* 10 */     this.parent = p_i2134_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/* 19 */     int var5 = areaX >> 1;
/* 20 */     int var6 = areaY >> 1;
/* 21 */     int var7 = (areaWidth >> 1) + 2;
/* 22 */     int var8 = (areaHeight >> 1) + 2;
/* 23 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8);
/* 24 */     int var10 = var7 - 1 << 1;
/* 25 */     int var11 = var8 - 1 << 1;
/* 26 */     int[] var12 = IntCache.getIntCache(var10 * var11);
/*    */ 
/*    */     
/* 29 */     for (int var13 = 0; var13 < var8 - 1; var13++) {
/*    */       
/* 31 */       int i = (var13 << 1) * var10;
/* 32 */       int var15 = 0;
/* 33 */       int var16 = var9[var15 + 0 + (var13 + 0) * var7];
/*    */       
/* 35 */       for (int var17 = var9[var15 + 0 + (var13 + 1) * var7]; var15 < var7 - 1; var15++) {
/*    */         
/* 37 */         initChunkSeed((var15 + var5 << 1), (var13 + var6 << 1));
/* 38 */         int var18 = var9[var15 + 1 + (var13 + 0) * var7];
/* 39 */         int var19 = var9[var15 + 1 + (var13 + 1) * var7];
/* 40 */         var12[i] = var16;
/* 41 */         var12[i++ + var10] = selectRandom(new int[] { var16, var17 });
/* 42 */         var12[i] = selectRandom(new int[] { var16, var18 });
/* 43 */         var12[i++ + var10] = selectModeOrRandom(var16, var18, var17, var19);
/* 44 */         var16 = var18;
/* 45 */         var17 = var19;
/*    */       } 
/*    */     } 
/*    */     
/* 49 */     int[] var20 = IntCache.getIntCache(areaWidth * areaHeight);
/*    */     
/* 51 */     for (int var14 = 0; var14 < areaHeight; var14++)
/*    */     {
/* 53 */       System.arraycopy(var12, (var14 + (areaY & 0x1)) * var10 + (areaX & 0x1), var20, var14 * areaWidth, areaWidth);
/*    */     }
/*    */     
/* 56 */     return var20;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static GenLayer magnify(long p_75915_0_, GenLayer p_75915_2_, int p_75915_3_) {
/* 64 */     Object var4 = p_75915_2_;
/*    */     
/* 66 */     for (int var5 = 0; var5 < p_75915_3_; var5++)
/*    */     {
/* 68 */       var4 = new GenLayerZoom(p_75915_0_ + var5, (GenLayer)var4);
/*    */     }
/*    */     
/* 71 */     return (GenLayer)var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerZoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */