/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.block.state.pattern.BlockHelper;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenMinable
/*    */   extends WorldGenerator
/*    */ {
/*    */   private final IBlockState oreBlock;
/*    */   private final int numberOfBlocks;
/*    */   private final Predicate field_175919_c;
/*    */   private static final String __OBFID = "CL_00000426";
/*    */   
/*    */   public WorldGenMinable(IBlockState p_i45630_1_, int p_i45630_2_) {
/* 23 */     this(p_i45630_1_, p_i45630_2_, (Predicate)BlockHelper.forBlock(Blocks.stone));
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenMinable(IBlockState p_i45631_1_, int p_i45631_2_, Predicate p_i45631_3_) {
/* 28 */     this.oreBlock = p_i45631_1_;
/* 29 */     this.numberOfBlocks = p_i45631_2_;
/* 30 */     this.field_175919_c = p_i45631_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 35 */     float var4 = p_180709_2_.nextFloat() * 3.1415927F;
/* 36 */     double var5 = ((p_180709_3_.getX() + 8) + MathHelper.sin(var4) * this.numberOfBlocks / 8.0F);
/* 37 */     double var7 = ((p_180709_3_.getX() + 8) - MathHelper.sin(var4) * this.numberOfBlocks / 8.0F);
/* 38 */     double var9 = ((p_180709_3_.getZ() + 8) + MathHelper.cos(var4) * this.numberOfBlocks / 8.0F);
/* 39 */     double var11 = ((p_180709_3_.getZ() + 8) - MathHelper.cos(var4) * this.numberOfBlocks / 8.0F);
/* 40 */     double var13 = (p_180709_3_.getY() + p_180709_2_.nextInt(3) - 2);
/* 41 */     double var15 = (p_180709_3_.getY() + p_180709_2_.nextInt(3) - 2);
/*    */     
/* 43 */     for (int var17 = 0; var17 < this.numberOfBlocks; var17++) {
/*    */       
/* 45 */       float var18 = var17 / this.numberOfBlocks;
/* 46 */       double var19 = var5 + (var7 - var5) * var18;
/* 47 */       double var21 = var13 + (var15 - var13) * var18;
/* 48 */       double var23 = var9 + (var11 - var9) * var18;
/* 49 */       double var25 = p_180709_2_.nextDouble() * this.numberOfBlocks / 16.0D;
/* 50 */       double var27 = (MathHelper.sin(3.1415927F * var18) + 1.0F) * var25 + 1.0D;
/* 51 */       double var29 = (MathHelper.sin(3.1415927F * var18) + 1.0F) * var25 + 1.0D;
/* 52 */       int var31 = MathHelper.floor_double(var19 - var27 / 2.0D);
/* 53 */       int var32 = MathHelper.floor_double(var21 - var29 / 2.0D);
/* 54 */       int var33 = MathHelper.floor_double(var23 - var27 / 2.0D);
/* 55 */       int var34 = MathHelper.floor_double(var19 + var27 / 2.0D);
/* 56 */       int var35 = MathHelper.floor_double(var21 + var29 / 2.0D);
/* 57 */       int var36 = MathHelper.floor_double(var23 + var27 / 2.0D);
/*    */       
/* 59 */       for (int var37 = var31; var37 <= var34; var37++) {
/*    */         
/* 61 */         double var38 = (var37 + 0.5D - var19) / var27 / 2.0D;
/*    */         
/* 63 */         if (var38 * var38 < 1.0D)
/*    */         {
/* 65 */           for (int var40 = var32; var40 <= var35; var40++) {
/*    */             
/* 67 */             double var41 = (var40 + 0.5D - var21) / var29 / 2.0D;
/*    */             
/* 69 */             if (var38 * var38 + var41 * var41 < 1.0D)
/*    */             {
/* 71 */               for (int var43 = var33; var43 <= var36; var43++) {
/*    */                 
/* 73 */                 double var44 = (var43 + 0.5D - var23) / var27 / 2.0D;
/*    */                 
/* 75 */                 if (var38 * var38 + var41 * var41 + var44 * var44 < 1.0D) {
/*    */                   
/* 77 */                   BlockPos var46 = new BlockPos(var37, var40, var43);
/*    */                   
/* 79 */                   if (this.field_175919_c.apply(worldIn.getBlockState(var46)))
/*    */                   {
/* 81 */                     worldIn.setBlockState(var46, this.oreBlock, 2);
/*    */                   }
/*    */                 } 
/*    */               } 
/*    */             }
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 91 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenMinable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */