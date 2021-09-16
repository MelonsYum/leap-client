/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ 
/*    */ public class GenLayerAddMushroomIsland
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000552";
/*    */   
/*    */   public GenLayerAddMushroomIsland(long p_i2120_1_, GenLayer p_i2120_3_) {
/* 11 */     super(p_i2120_1_);
/* 12 */     this.parent = p_i2120_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/* 21 */     int var5 = areaX - 1;
/* 22 */     int var6 = areaY - 1;
/* 23 */     int var7 = areaWidth + 2;
/* 24 */     int var8 = areaHeight + 2;
/* 25 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8);
/* 26 */     int[] var10 = IntCache.getIntCache(areaWidth * areaHeight);
/*    */     
/* 28 */     for (int var11 = 0; var11 < areaHeight; var11++) {
/*    */       
/* 30 */       for (int var12 = 0; var12 < areaWidth; var12++) {
/*    */         
/* 32 */         int var13 = var9[var12 + 0 + (var11 + 0) * var7];
/* 33 */         int var14 = var9[var12 + 2 + (var11 + 0) * var7];
/* 34 */         int var15 = var9[var12 + 0 + (var11 + 2) * var7];
/* 35 */         int var16 = var9[var12 + 2 + (var11 + 2) * var7];
/* 36 */         int var17 = var9[var12 + 1 + (var11 + 1) * var7];
/* 37 */         initChunkSeed((var12 + areaX), (var11 + areaY));
/*    */         
/* 39 */         if (var17 == 0 && var13 == 0 && var14 == 0 && var15 == 0 && var16 == 0 && nextInt(100) == 0) {
/*    */           
/* 41 */           var10[var12 + var11 * areaWidth] = BiomeGenBase.mushroomIsland.biomeID;
/*    */         }
/*    */         else {
/*    */           
/* 45 */           var10[var12 + var11 * areaWidth] = var17;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 50 */     return var10;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerAddMushroomIsland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */