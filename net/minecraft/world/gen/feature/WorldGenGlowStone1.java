/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenGlowStone1
/*    */   extends WorldGenerator
/*    */ {
/*    */   private static final String __OBFID = "CL_00000419";
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 16 */     if (!worldIn.isAirBlock(p_180709_3_))
/*    */     {
/* 18 */       return false;
/*    */     }
/* 20 */     if (worldIn.getBlockState(p_180709_3_.offsetUp()).getBlock() != Blocks.netherrack)
/*    */     {
/* 22 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 26 */     worldIn.setBlockState(p_180709_3_, Blocks.glowstone.getDefaultState(), 2);
/*    */     
/* 28 */     for (int var4 = 0; var4 < 1500; var4++) {
/*    */       
/* 30 */       BlockPos var5 = p_180709_3_.add(p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8), -p_180709_2_.nextInt(12), p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8));
/*    */       
/* 32 */       if (worldIn.getBlockState(var5).getBlock().getMaterial() == Material.air) {
/*    */         
/* 34 */         int var6 = 0;
/* 35 */         EnumFacing[] var7 = EnumFacing.values();
/* 36 */         int var8 = var7.length;
/*    */         
/* 38 */         for (int var9 = 0; var9 < var8; var9++) {
/*    */           
/* 40 */           EnumFacing var10 = var7[var9];
/*    */           
/* 42 */           if (worldIn.getBlockState(var5.offset(var10)).getBlock() == Blocks.glowstone)
/*    */           {
/* 44 */             var6++;
/*    */           }
/*    */           
/* 47 */           if (var6 > 1) {
/*    */             break;
/*    */           }
/*    */         } 
/*    */ 
/*    */         
/* 53 */         if (var6 == 1)
/*    */         {
/* 55 */           worldIn.setBlockState(var5, Blocks.glowstone.getDefaultState(), 2);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenGlowStone1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */