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
/*    */ 
/*    */ public class WorldGenClay
/*    */   extends WorldGenerator
/*    */ {
/*    */   private Block field_150546_a;
/*    */   private int numberOfBlocks;
/*    */   private static final String __OBFID = "CL_00000405";
/*    */   
/*    */   public WorldGenClay(int p_i2011_1_) {
/* 20 */     this.field_150546_a = Blocks.clay;
/* 21 */     this.numberOfBlocks = p_i2011_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 26 */     if (worldIn.getBlockState(p_180709_3_).getBlock().getMaterial() != Material.water)
/*    */     {
/* 28 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 32 */     int var4 = p_180709_2_.nextInt(this.numberOfBlocks - 2) + 2;
/* 33 */     byte var5 = 1;
/*    */     
/* 35 */     for (int var6 = p_180709_3_.getX() - var4; var6 <= p_180709_3_.getX() + var4; var6++) {
/*    */       
/* 37 */       for (int var7 = p_180709_3_.getZ() - var4; var7 <= p_180709_3_.getZ() + var4; var7++) {
/*    */         
/* 39 */         int var8 = var6 - p_180709_3_.getX();
/* 40 */         int var9 = var7 - p_180709_3_.getZ();
/*    */         
/* 42 */         if (var8 * var8 + var9 * var9 <= var4 * var4)
/*    */         {
/* 44 */           for (int var10 = p_180709_3_.getY() - var5; var10 <= p_180709_3_.getY() + var5; var10++) {
/*    */             
/* 46 */             BlockPos var11 = new BlockPos(var6, var10, var7);
/* 47 */             Block var12 = worldIn.getBlockState(var11).getBlock();
/*    */             
/* 49 */             if (var12 == Blocks.dirt || var12 == Blocks.clay)
/*    */             {
/* 51 */               worldIn.setBlockState(var11, this.field_150546_a.getDefaultState(), 2);
/*    */             }
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 58 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenClay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */