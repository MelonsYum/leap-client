/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.Vec3i;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenBlockBlob
/*    */   extends WorldGenerator {
/*    */   private final Block field_150545_a;
/*    */   private final int field_150544_b;
/*    */   private static final String __OBFID = "CL_00000402";
/*    */   
/*    */   public WorldGenBlockBlob(Block p_i45450_1_, int p_i45450_2_) {
/* 18 */     super(false);
/* 19 */     this.field_150545_a = p_i45450_1_;
/* 20 */     this.field_150544_b = p_i45450_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 27 */     while (p_180709_3_.getY() > 3) {
/*    */ 
/*    */ 
/*    */       
/* 31 */       if (!worldIn.isAirBlock(p_180709_3_.offsetDown())) {
/*    */         
/* 33 */         Block var4 = worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock();
/*    */         
/* 35 */         if (var4 == Blocks.grass || var4 == Blocks.dirt || var4 == Blocks.stone) {
/*    */           break;
/*    */         }
/*    */       } 
/*    */ 
/*    */       
/* 41 */       p_180709_3_ = p_180709_3_.offsetDown();
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 46 */     if (p_180709_3_.getY() <= 3)
/*    */     {
/* 48 */       return false;
/*    */     }
/*    */     
/* 51 */     int var12 = this.field_150544_b;
/*    */     
/* 53 */     for (int var5 = 0; var12 >= 0 && var5 < 3; var5++) {
/*    */       
/* 55 */       int var6 = var12 + p_180709_2_.nextInt(2);
/* 56 */       int var7 = var12 + p_180709_2_.nextInt(2);
/* 57 */       int var8 = var12 + p_180709_2_.nextInt(2);
/* 58 */       float var9 = (var6 + var7 + var8) * 0.333F + 0.5F;
/* 59 */       Iterator<BlockPos> var10 = BlockPos.getAllInBox(p_180709_3_.add(-var6, -var7, -var8), p_180709_3_.add(var6, var7, var8)).iterator();
/*    */       
/* 61 */       while (var10.hasNext()) {
/*    */         
/* 63 */         BlockPos var11 = var10.next();
/*    */         
/* 65 */         if (var11.distanceSq((Vec3i)p_180709_3_) <= (var9 * var9))
/*    */         {
/* 67 */           worldIn.setBlockState(var11, this.field_150545_a.getDefaultState(), 4);
/*    */         }
/*    */       } 
/*    */       
/* 71 */       p_180709_3_ = p_180709_3_.add(-(var12 + 1) + p_180709_2_.nextInt(2 + var12 * 2), 0 - p_180709_2_.nextInt(2), -(var12 + 1) + p_180709_2_.nextInt(2 + var12 * 2));
/*    */     } 
/*    */     
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenBlockBlob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */