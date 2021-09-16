/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenLiquids
/*    */   extends WorldGenerator
/*    */ {
/*    */   private Block field_150521_a;
/*    */   private static final String __OBFID = "CL_00000434";
/*    */   
/*    */   public WorldGenLiquids(Block p_i45465_1_) {
/* 17 */     this.field_150521_a = p_i45465_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 22 */     if (worldIn.getBlockState(p_180709_3_.offsetUp()).getBlock() != Blocks.stone)
/*    */     {
/* 24 */       return false;
/*    */     }
/* 26 */     if (worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock() != Blocks.stone)
/*    */     {
/* 28 */       return false;
/*    */     }
/* 30 */     if (worldIn.getBlockState(p_180709_3_).getBlock().getMaterial() != Material.air && worldIn.getBlockState(p_180709_3_).getBlock() != Blocks.stone)
/*    */     {
/* 32 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 36 */     int var4 = 0;
/*    */     
/* 38 */     if (worldIn.getBlockState(p_180709_3_.offsetWest()).getBlock() == Blocks.stone)
/*    */     {
/* 40 */       var4++;
/*    */     }
/*    */     
/* 43 */     if (worldIn.getBlockState(p_180709_3_.offsetEast()).getBlock() == Blocks.stone)
/*    */     {
/* 45 */       var4++;
/*    */     }
/*    */     
/* 48 */     if (worldIn.getBlockState(p_180709_3_.offsetNorth()).getBlock() == Blocks.stone)
/*    */     {
/* 50 */       var4++;
/*    */     }
/*    */     
/* 53 */     if (worldIn.getBlockState(p_180709_3_.offsetSouth()).getBlock() == Blocks.stone)
/*    */     {
/* 55 */       var4++;
/*    */     }
/*    */     
/* 58 */     int var5 = 0;
/*    */     
/* 60 */     if (worldIn.isAirBlock(p_180709_3_.offsetWest()))
/*    */     {
/* 62 */       var5++;
/*    */     }
/*    */     
/* 65 */     if (worldIn.isAirBlock(p_180709_3_.offsetEast()))
/*    */     {
/* 67 */       var5++;
/*    */     }
/*    */     
/* 70 */     if (worldIn.isAirBlock(p_180709_3_.offsetNorth()))
/*    */     {
/* 72 */       var5++;
/*    */     }
/*    */     
/* 75 */     if (worldIn.isAirBlock(p_180709_3_.offsetSouth()))
/*    */     {
/* 77 */       var5++;
/*    */     }
/*    */     
/* 80 */     if (var4 == 3 && var5 == 1) {
/*    */       
/* 82 */       worldIn.setBlockState(p_180709_3_, this.field_150521_a.getDefaultState(), 2);
/* 83 */       worldIn.func_175637_a(this.field_150521_a, p_180709_3_, p_180709_2_);
/*    */     } 
/*    */     
/* 86 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenLiquids.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */