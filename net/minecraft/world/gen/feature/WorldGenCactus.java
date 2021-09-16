/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenCactus
/*    */   extends WorldGenerator
/*    */ {
/*    */   private static final String __OBFID = "CL_00000404";
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 14 */     for (int var4 = 0; var4 < 10; var4++) {
/*    */       
/* 16 */       BlockPos var5 = p_180709_3_.add(p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8), p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), p_180709_2_.nextInt(8) - p_180709_2_.nextInt(8));
/*    */       
/* 18 */       if (worldIn.isAirBlock(var5)) {
/*    */         
/* 20 */         int var6 = 1 + p_180709_2_.nextInt(p_180709_2_.nextInt(3) + 1);
/*    */         
/* 22 */         for (int var7 = 0; var7 < var6; var7++) {
/*    */           
/* 24 */           if (Blocks.cactus.canBlockStay(worldIn, var5))
/*    */           {
/* 26 */             worldIn.setBlockState(var5.offsetUp(var7), Blocks.cactus.getDefaultState(), 2);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenCactus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */