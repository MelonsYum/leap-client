/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class WorldGenDeadBush
/*    */   extends WorldGenerator
/*    */ {
/*    */   private static final String __OBFID = "CL_00000406";
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*    */     Block var4;
/* 18 */     while (((var4 = worldIn.getBlockState(p_180709_3_).getBlock()).getMaterial() == Material.air || var4.getMaterial() == Material.leaves) && p_180709_3_.getY() > 0)
/*    */     {
/* 20 */       p_180709_3_ = p_180709_3_.offsetDown();
/*    */     }
/*    */     
/* 23 */     for (int var5 = 0; var5 < 4; var5++) {
/*    */       
/* 25 */       BlockPos var6 = p_180709_3_.add(p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8), p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8));
/*    */       
/* 27 */       if (worldIn.isAirBlock(var6) && Blocks.deadbush.canBlockStay(worldIn, var6, Blocks.deadbush.getDefaultState()))
/*    */       {
/* 29 */         worldIn.setBlockState(var6, Blocks.deadbush.getDefaultState(), 2);
/*    */       }
/*    */     } 
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenDeadBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */