/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityEnderCrystal;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class WorldGenSpikes
/*    */   extends WorldGenerator {
/*    */   private Block field_150520_a;
/*    */   private static final String __OBFID = "CL_00000433";
/*    */   
/*    */   public WorldGenSpikes(Block p_i45464_1_) {
/* 17 */     this.field_150520_a = p_i45464_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 22 */     if (worldIn.isAirBlock(p_180709_3_) && worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock() == this.field_150520_a) {
/*    */       
/* 24 */       int var4 = p_180709_2_.nextInt(32) + 6;
/* 25 */       int var5 = p_180709_2_.nextInt(4) + 1;
/*    */ 
/*    */       
/*    */       int var6;
/*    */ 
/*    */       
/* 31 */       for (var6 = p_180709_3_.getX() - var5; var6 <= p_180709_3_.getX() + var5; var6++) {
/*    */         
/* 33 */         for (int var7 = p_180709_3_.getZ() - var5; var7 <= p_180709_3_.getZ() + var5; var7++) {
/*    */           
/* 35 */           int var8 = var6 - p_180709_3_.getX();
/* 36 */           int var9 = var7 - p_180709_3_.getZ();
/*    */           
/* 38 */           if (var8 * var8 + var9 * var9 <= var5 * var5 + 1 && worldIn.getBlockState(new BlockPos(var6, p_180709_3_.getY() - 1, var7)).getBlock() != this.field_150520_a)
/*    */           {
/* 40 */             return false;
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 45 */       for (var6 = p_180709_3_.getY(); var6 < p_180709_3_.getY() + var4 && var6 < 256; var6++) {
/*    */         
/* 47 */         for (int var7 = p_180709_3_.getX() - var5; var7 <= p_180709_3_.getX() + var5; var7++) {
/*    */           
/* 49 */           for (int var8 = p_180709_3_.getZ() - var5; var8 <= p_180709_3_.getZ() + var5; var8++) {
/*    */             
/* 51 */             int var9 = var7 - p_180709_3_.getX();
/* 52 */             int var10 = var8 - p_180709_3_.getZ();
/*    */             
/* 54 */             if (var9 * var9 + var10 * var10 <= var5 * var5 + 1)
/*    */             {
/* 56 */               worldIn.setBlockState(new BlockPos(var7, var6, var8), Blocks.obsidian.getDefaultState(), 2);
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/* 62 */       EntityEnderCrystal var11 = new EntityEnderCrystal(worldIn);
/* 63 */       var11.setLocationAndAngles((p_180709_3_.getX() + 0.5F), (p_180709_3_.getY() + var4), (p_180709_3_.getZ() + 0.5F), p_180709_2_.nextFloat() * 360.0F, 0.0F);
/* 64 */       worldIn.spawnEntityInWorld((Entity)var11);
/* 65 */       worldIn.setBlockState(p_180709_3_.offsetUp(var4), Blocks.bedrock.getDefaultState(), 2);
/* 66 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 70 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenSpikes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */