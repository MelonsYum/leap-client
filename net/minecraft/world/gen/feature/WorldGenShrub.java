/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenShrub
/*    */   extends WorldGenTrees
/*    */ {
/*    */   private int field_150528_a;
/*    */   private int field_150527_b;
/*    */   private static final String __OBFID = "CL_00000411";
/*    */   
/*    */   public WorldGenShrub(int p_i2015_1_, int p_i2015_2_) {
/* 18 */     super(false);
/* 19 */     this.field_150527_b = p_i2015_1_;
/* 20 */     this.field_150528_a = p_i2015_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*    */     Block var4;
/* 27 */     while (((var4 = worldIn.getBlockState(p_180709_3_).getBlock()).getMaterial() == Material.air || var4.getMaterial() == Material.leaves) && p_180709_3_.getY() > 0)
/*    */     {
/* 29 */       p_180709_3_ = p_180709_3_.offsetDown();
/*    */     }
/*    */     
/* 32 */     Block var5 = worldIn.getBlockState(p_180709_3_).getBlock();
/*    */     
/* 34 */     if (var5 == Blocks.dirt || var5 == Blocks.grass) {
/*    */       
/* 36 */       p_180709_3_ = p_180709_3_.offsetUp();
/* 37 */       func_175905_a(worldIn, p_180709_3_, Blocks.log, this.field_150527_b);
/*    */       
/* 39 */       for (int var6 = p_180709_3_.getY(); var6 <= p_180709_3_.getY() + 2; var6++) {
/*    */         
/* 41 */         int var7 = var6 - p_180709_3_.getY();
/* 42 */         int var8 = 2 - var7;
/*    */         
/* 44 */         for (int var9 = p_180709_3_.getX() - var8; var9 <= p_180709_3_.getX() + var8; var9++) {
/*    */           
/* 46 */           int var10 = var9 - p_180709_3_.getX();
/*    */           
/* 48 */           for (int var11 = p_180709_3_.getZ() - var8; var11 <= p_180709_3_.getZ() + var8; var11++) {
/*    */             
/* 50 */             int var12 = var11 - p_180709_3_.getZ();
/*    */             
/* 52 */             if (Math.abs(var10) != var8 || Math.abs(var12) != var8 || p_180709_2_.nextInt(2) != 0) {
/*    */               
/* 54 */               BlockPos var13 = new BlockPos(var9, var6, var11);
/*    */               
/* 56 */               if (!worldIn.getBlockState(var13).getBlock().isFullBlock())
/*    */               {
/* 58 */                 func_175905_a(worldIn, var13, (Block)Blocks.leaves, this.field_150528_a);
/*    */               }
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 66 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenShrub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */