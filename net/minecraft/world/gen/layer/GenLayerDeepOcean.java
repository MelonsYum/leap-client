/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ 
/*    */ public class GenLayerDeepOcean
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000546";
/*    */   
/*    */   public GenLayerDeepOcean(long p_i45472_1_, GenLayer p_i45472_3_) {
/* 11 */     super(p_i45472_1_);
/* 12 */     this.parent = p_i45472_3_;
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
/* 32 */         int var13 = var9[var12 + 1 + (var11 + 1 - 1) * (areaWidth + 2)];
/* 33 */         int var14 = var9[var12 + 1 + 1 + (var11 + 1) * (areaWidth + 2)];
/* 34 */         int var15 = var9[var12 + 1 - 1 + (var11 + 1) * (areaWidth + 2)];
/* 35 */         int var16 = var9[var12 + 1 + (var11 + 1 + 1) * (areaWidth + 2)];
/* 36 */         int var17 = var9[var12 + 1 + (var11 + 1) * var7];
/* 37 */         int var18 = 0;
/*    */         
/* 39 */         if (var13 == 0)
/*    */         {
/* 41 */           var18++;
/*    */         }
/*    */         
/* 44 */         if (var14 == 0)
/*    */         {
/* 46 */           var18++;
/*    */         }
/*    */         
/* 49 */         if (var15 == 0)
/*    */         {
/* 51 */           var18++;
/*    */         }
/*    */         
/* 54 */         if (var16 == 0)
/*    */         {
/* 56 */           var18++;
/*    */         }
/*    */         
/* 59 */         if (var17 == 0 && var18 > 3) {
/*    */           
/* 61 */           var10[var12 + var11 * areaWidth] = BiomeGenBase.deepOcean.biomeID;
/*    */         }
/*    */         else {
/*    */           
/* 65 */           var10[var12 + var11 * areaWidth] = var17;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 70 */     return var10;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerDeepOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */