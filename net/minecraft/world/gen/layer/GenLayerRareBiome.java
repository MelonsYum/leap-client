/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ 
/*    */ public class GenLayerRareBiome
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000562";
/*    */   
/*    */   public GenLayerRareBiome(long p_i45478_1_, GenLayer p_i45478_3_) {
/* 11 */     super(p_i45478_1_);
/* 12 */     this.parent = p_i45478_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/* 21 */     int[] var5 = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
/* 22 */     int[] var6 = IntCache.getIntCache(areaWidth * areaHeight);
/*    */     
/* 24 */     for (int var7 = 0; var7 < areaHeight; var7++) {
/*    */       
/* 26 */       for (int var8 = 0; var8 < areaWidth; var8++) {
/*    */         
/* 28 */         initChunkSeed((var8 + areaX), (var7 + areaY));
/* 29 */         int var9 = var5[var8 + 1 + (var7 + 1) * (areaWidth + 2)];
/*    */         
/* 31 */         if (nextInt(57) == 0) {
/*    */           
/* 33 */           if (var9 == BiomeGenBase.plains.biomeID)
/*    */           {
/* 35 */             var6[var8 + var7 * areaWidth] = BiomeGenBase.plains.biomeID + 128;
/*    */           }
/*    */           else
/*    */           {
/* 39 */             var6[var8 + var7 * areaWidth] = var9;
/*    */           }
/*    */         
/*    */         } else {
/*    */           
/* 44 */           var6[var8 + var7 * areaWidth] = var9;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 49 */     return var6;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerRareBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */