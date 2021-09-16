/*    */ package net.minecraft.world.gen.layer;
/*    */ 
/*    */ import net.minecraft.world.biome.BiomeGenBase;
/*    */ 
/*    */ public class GenLayerRiverMix
/*    */   extends GenLayer
/*    */ {
/*    */   private GenLayer biomePatternGeneratorChain;
/*    */   private GenLayer riverPatternGeneratorChain;
/*    */   private static final String __OBFID = "CL_00000567";
/*    */   
/*    */   public GenLayerRiverMix(long p_i2129_1_, GenLayer p_i2129_3_, GenLayer p_i2129_4_) {
/* 13 */     super(p_i2129_1_);
/* 14 */     this.biomePatternGeneratorChain = p_i2129_3_;
/* 15 */     this.riverPatternGeneratorChain = p_i2129_4_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initWorldGenSeed(long p_75905_1_) {
/* 24 */     this.biomePatternGeneratorChain.initWorldGenSeed(p_75905_1_);
/* 25 */     this.riverPatternGeneratorChain.initWorldGenSeed(p_75905_1_);
/* 26 */     super.initWorldGenSeed(p_75905_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
/* 35 */     int[] var5 = this.biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
/* 36 */     int[] var6 = this.riverPatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
/* 37 */     int[] var7 = IntCache.getIntCache(areaWidth * areaHeight);
/*    */     
/* 39 */     for (int var8 = 0; var8 < areaWidth * areaHeight; var8++) {
/*    */       
/* 41 */       if (var5[var8] != BiomeGenBase.ocean.biomeID && var5[var8] != BiomeGenBase.deepOcean.biomeID) {
/*    */         
/* 43 */         if (var6[var8] == BiomeGenBase.river.biomeID) {
/*    */           
/* 45 */           if (var5[var8] == BiomeGenBase.icePlains.biomeID)
/*    */           {
/* 47 */             var7[var8] = BiomeGenBase.frozenRiver.biomeID;
/*    */           }
/* 49 */           else if (var5[var8] != BiomeGenBase.mushroomIsland.biomeID && var5[var8] != BiomeGenBase.mushroomIslandShore.biomeID)
/*    */           {
/* 51 */             var7[var8] = var6[var8] & 0xFF;
/*    */           }
/*    */           else
/*    */           {
/* 55 */             var7[var8] = BiomeGenBase.mushroomIslandShore.biomeID;
/*    */           }
/*    */         
/*    */         } else {
/*    */           
/* 60 */           var7[var8] = var5[var8];
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 65 */         var7[var8] = var5[var8];
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     return var7;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\layer\GenLayerRiverMix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */