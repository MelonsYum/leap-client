/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenHellLava
/*    */   extends WorldGenerator
/*    */ {
/*    */   private final Block field_150553_a;
/*    */   private final boolean field_94524_b;
/*    */   private static final String __OBFID = "CL_00000414";
/*    */   
/*    */   public WorldGenHellLava(Block p_i45453_1_, boolean p_i45453_2_) {
/* 18 */     this.field_150553_a = p_i45453_1_;
/* 19 */     this.field_94524_b = p_i45453_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 24 */     if (worldIn.getBlockState(p_180709_3_.offsetUp()).getBlock() != Blocks.netherrack)
/*    */     {
/* 26 */       return false;
/*    */     }
/* 28 */     if (worldIn.getBlockState(p_180709_3_).getBlock().getMaterial() != Material.air && worldIn.getBlockState(p_180709_3_).getBlock() != Blocks.netherrack)
/*    */     {
/* 30 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 34 */     int var4 = 0;
/*    */     
/* 36 */     if (worldIn.getBlockState(p_180709_3_.offsetWest()).getBlock() == Blocks.netherrack)
/*    */     {
/* 38 */       var4++;
/*    */     }
/*    */     
/* 41 */     if (worldIn.getBlockState(p_180709_3_.offsetEast()).getBlock() == Blocks.netherrack)
/*    */     {
/* 43 */       var4++;
/*    */     }
/*    */     
/* 46 */     if (worldIn.getBlockState(p_180709_3_.offsetNorth()).getBlock() == Blocks.netherrack)
/*    */     {
/* 48 */       var4++;
/*    */     }
/*    */     
/* 51 */     if (worldIn.getBlockState(p_180709_3_.offsetSouth()).getBlock() == Blocks.netherrack)
/*    */     {
/* 53 */       var4++;
/*    */     }
/*    */     
/* 56 */     if (worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock() == Blocks.netherrack)
/*    */     {
/* 58 */       var4++;
/*    */     }
/*    */     
/* 61 */     int var5 = 0;
/*    */     
/* 63 */     if (worldIn.isAirBlock(p_180709_3_.offsetWest()))
/*    */     {
/* 65 */       var5++;
/*    */     }
/*    */     
/* 68 */     if (worldIn.isAirBlock(p_180709_3_.offsetEast()))
/*    */     {
/* 70 */       var5++;
/*    */     }
/*    */     
/* 73 */     if (worldIn.isAirBlock(p_180709_3_.offsetNorth()))
/*    */     {
/* 75 */       var5++;
/*    */     }
/*    */     
/* 78 */     if (worldIn.isAirBlock(p_180709_3_.offsetSouth()))
/*    */     {
/* 80 */       var5++;
/*    */     }
/*    */     
/* 83 */     if (worldIn.isAirBlock(p_180709_3_.offsetDown()))
/*    */     {
/* 85 */       var5++;
/*    */     }
/*    */     
/* 88 */     if ((!this.field_94524_b && var4 == 4 && var5 == 1) || var4 == 5) {
/*    */       
/* 90 */       worldIn.setBlockState(p_180709_3_, this.field_150553_a.getDefaultState(), 2);
/* 91 */       worldIn.func_175637_a(this.field_150553_a, p_180709_3_, p_180709_2_);
/*    */     } 
/*    */     
/* 94 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenHellLava.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */