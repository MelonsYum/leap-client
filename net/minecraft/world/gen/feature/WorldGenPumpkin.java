/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockPumpkin;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenPumpkin
/*    */   extends WorldGenerator {
/*    */   private static final String __OBFID = "CL_00000428";
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 16 */     for (int var4 = 0; var4 < 64; var4++) {
/*    */       
/* 18 */       BlockPos var5 = p_180709_3_.add(p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8), p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8));
/*    */       
/* 20 */       if (worldIn.isAirBlock(var5) && worldIn.getBlockState(var5.offsetDown()).getBlock() == Blocks.grass && Blocks.pumpkin.canPlaceBlockAt(worldIn, var5))
/*    */       {
/* 22 */         worldIn.setBlockState(var5, Blocks.pumpkin.getDefaultState().withProperty((IProperty)BlockPumpkin.AGE, (Comparable)EnumFacing.Plane.HORIZONTAL.random(p_180709_2_)), 2);
/*    */       }
/*    */     } 
/*    */     
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenPumpkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */