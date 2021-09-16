/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ 
/*    */ public class GenLayerRiver
/*    */   extends GenLayer
/*    */ {
/*    */   private static final String __OBFID = "CL_00000566";
/*    */   
/*    */   public GenLayerRiver(long p_i2128_1_, GenLayer p_i2128_3_) {
/* 11 */     super(p_i2128_1_);
/* 12 */     this.parent = p_i2128_3_;
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
/* 32 */         int var13 = func_151630_c(var9[var12 + 0 + (var11 + 1) * var7]);
/* 33 */         int var14 = func_151630_c(var9[var12 + 2 + (var11 + 1) * var7]);
/* 34 */         int var15 = func_151630_c(var9[var12 + 1 + (var11 + 0) * var7]);
/* 35 */         int var16 = func_151630_c(var9[var12 + 1 + (var11 + 2) * var7]);
/* 36 */         int var17 = func_151630_c(var9[var12 + 1 + (var11 + 1) * var7]);
/*    */         
/* 38 */         if (var17 == var13 && var17 == var15 && var17 == var14 && var17 == var16) {
/*    */           
/* 40 */           var10[var12 + var11 * areaWidth] = -1;
/*    */         }
/*    */         else {
/*    */           
/* 44 */           var10[var12 + var11 * areaWidth] = BiomeGenBase.river.biomeID;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 49 */     return var10;
/*    */   }
/*    */ 
/*    */   
/*    */   private int func_151630_c(int p_151630_1_) {
/* 54 */     return (p_151630_1_ >= 2) ? (2 + (p_151630_1_ & 0x1)) : p_151630_1_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerRiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */