/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenIceSpike
/*     */   extends WorldGenerator
/*     */ {
/*     */   private static final String __OBFID = "CL_00000417";
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  17 */     while (worldIn.isAirBlock(p_180709_3_) && p_180709_3_.getY() > 2)
/*     */     {
/*  19 */       p_180709_3_ = p_180709_3_.offsetDown();
/*     */     }
/*     */     
/*  22 */     if (worldIn.getBlockState(p_180709_3_).getBlock() != Blocks.snow)
/*     */     {
/*  24 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  28 */     p_180709_3_ = p_180709_3_.offsetUp(p_180709_2_.nextInt(4));
/*  29 */     int var4 = p_180709_2_.nextInt(4) + 7;
/*  30 */     int var5 = var4 / 4 + p_180709_2_.nextInt(2);
/*     */     
/*  32 */     if (var5 > 1 && p_180709_2_.nextInt(60) == 0)
/*     */     {
/*  34 */       p_180709_3_ = p_180709_3_.offsetUp(10 + p_180709_2_.nextInt(30));
/*     */     }
/*     */ 
/*     */     
/*     */     int var6;
/*     */     
/*  40 */     for (var6 = 0; var6 < var4; var6++) {
/*     */       
/*  42 */       float var7 = (1.0F - var6 / var4) * var5;
/*  43 */       int var8 = MathHelper.ceiling_float_int(var7);
/*     */       
/*  45 */       for (int var9 = -var8; var9 <= var8; var9++) {
/*     */         
/*  47 */         float var10 = MathHelper.abs_int(var9) - 0.25F;
/*     */         
/*  49 */         for (int var11 = -var8; var11 <= var8; var11++) {
/*     */           
/*  51 */           float var12 = MathHelper.abs_int(var11) - 0.25F;
/*     */           
/*  53 */           if (((var9 == 0 && var11 == 0) || var10 * var10 + var12 * var12 <= var7 * var7) && ((var9 != -var8 && var9 != var8 && var11 != -var8 && var11 != var8) || p_180709_2_.nextFloat() <= 0.75F)) {
/*     */             
/*  55 */             Block var13 = worldIn.getBlockState(p_180709_3_.add(var9, var6, var11)).getBlock();
/*     */             
/*  57 */             if (var13.getMaterial() == Material.air || var13 == Blocks.dirt || var13 == Blocks.snow || var13 == Blocks.ice)
/*     */             {
/*  59 */               func_175906_a(worldIn, p_180709_3_.add(var9, var6, var11), Blocks.packed_ice);
/*     */             }
/*     */             
/*  62 */             if (var6 != 0 && var8 > 1) {
/*     */               
/*  64 */               var13 = worldIn.getBlockState(p_180709_3_.add(var9, -var6, var11)).getBlock();
/*     */               
/*  66 */               if (var13.getMaterial() == Material.air || var13 == Blocks.dirt || var13 == Blocks.snow || var13 == Blocks.ice)
/*     */               {
/*  68 */                 func_175906_a(worldIn, p_180709_3_.add(var9, -var6, var11), Blocks.packed_ice);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     var6 = var5 - 1;
/*     */     
/*  78 */     if (var6 < 0) {
/*     */       
/*  80 */       var6 = 0;
/*     */     }
/*  82 */     else if (var6 > 1) {
/*     */       
/*  84 */       var6 = 1;
/*     */     } 
/*     */     
/*  87 */     for (int var14 = -var6; var14 <= var6; var14++) {
/*     */       
/*  89 */       int var8 = -var6;
/*     */       
/*  91 */       while (var8 <= var6) {
/*     */         
/*  93 */         BlockPos var15 = p_180709_3_.add(var14, -1, var8);
/*  94 */         int var16 = 50;
/*     */         
/*  96 */         if (Math.abs(var14) == 1 && Math.abs(var8) == 1)
/*     */         {
/*  98 */           var16 = p_180709_2_.nextInt(5);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 103 */         while (var15.getY() > 50) {
/*     */           
/* 105 */           Block var17 = worldIn.getBlockState(var15).getBlock();
/*     */           
/* 107 */           if (var17.getMaterial() == Material.air || var17 == Blocks.dirt || var17 == Blocks.snow || var17 == Blocks.ice || var17 == Blocks.packed_ice) {
/*     */             
/* 109 */             func_175906_a(worldIn, var15, Blocks.packed_ice);
/* 110 */             var15 = var15.offsetDown();
/* 111 */             var16--;
/*     */             
/* 113 */             if (var16 <= 0) {
/*     */               
/* 115 */               var15 = var15.offsetDown(p_180709_2_.nextInt(5) + 1);
/* 116 */               var16 = p_180709_2_.nextInt(5);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 123 */         var8++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 129 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenIceSpike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */