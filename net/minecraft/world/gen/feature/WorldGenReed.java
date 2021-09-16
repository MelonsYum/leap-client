/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenReed
/*    */   extends WorldGenerator
/*    */ {
/*    */   private static final String __OBFID = "CL_00000429";
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 15 */     for (int var4 = 0; var4 < 20; var4++) {
/*    */       
/* 17 */       BlockPos var5 = p_180709_3_.add(p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), 0, p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4));
/*    */       
/* 19 */       if (worldIn.isAirBlock(var5)) {
/*    */         
/* 21 */         BlockPos var6 = var5.offsetDown();
/*    */         
/* 23 */         if (worldIn.getBlockState(var6.offsetWest()).getBlock().getMaterial() == Material.water || worldIn.getBlockState(var6.offsetEast()).getBlock().getMaterial() == Material.water || worldIn.getBlockState(var6.offsetNorth()).getBlock().getMaterial() == Material.water || worldIn.getBlockState(var6.offsetSouth()).getBlock().getMaterial() == Material.water) {
/*    */           
/* 25 */           int var7 = 2 + p_180709_2_.nextInt(p_180709_2_.nextInt(3) + 1);
/*    */           
/* 27 */           for (int var8 = 0; var8 < var7; var8++) {
/*    */             
/* 29 */             if (Blocks.reeds.func_176354_d(worldIn, var5))
/*    */             {
/* 31 */               worldIn.setBlockState(var5.offsetUp(var8), Blocks.reeds.getDefaultState(), 2);
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenReed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */