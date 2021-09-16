/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPlanks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenTaiga2
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private static final String __OBFID = "CL_00000435";
/*     */   
/*     */   public WorldGenTaiga2(boolean p_i2025_1_) {
/*  17 */     super(p_i2025_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  22 */     int var4 = p_180709_2_.nextInt(4) + 6;
/*  23 */     int var5 = 1 + p_180709_2_.nextInt(2);
/*  24 */     int var6 = var4 - var5;
/*  25 */     int var7 = 2 + p_180709_2_.nextInt(2);
/*  26 */     boolean var8 = true;
/*     */     
/*  28 */     if (p_180709_3_.getY() >= 1 && p_180709_3_.getY() + var4 + 1 <= 256) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  33 */       for (int var9 = p_180709_3_.getY(); var9 <= p_180709_3_.getY() + 1 + var4 && var8; var9++) {
/*     */         int var21;
/*  35 */         boolean var10 = true;
/*     */         
/*  37 */         if (var9 - p_180709_3_.getY() < var5) {
/*     */           
/*  39 */           var21 = 0;
/*     */         }
/*     */         else {
/*     */           
/*  43 */           var21 = var7;
/*     */         } 
/*     */         
/*  46 */         for (int var11 = p_180709_3_.getX() - var21; var11 <= p_180709_3_.getX() + var21 && var8; var11++) {
/*     */           
/*  48 */           for (int var12 = p_180709_3_.getZ() - var21; var12 <= p_180709_3_.getZ() + var21 && var8; var12++) {
/*     */             
/*  50 */             if (var9 >= 0 && var9 < 256) {
/*     */               
/*  52 */               Block var13 = worldIn.getBlockState(new BlockPos(var11, var9, var12)).getBlock();
/*     */               
/*  54 */               if (var13.getMaterial() != Material.air && var13.getMaterial() != Material.leaves)
/*     */               {
/*  56 */                 var8 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  61 */               var8 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  67 */       if (!var8)
/*     */       {
/*  69 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  73 */       Block var20 = worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock();
/*     */       
/*  75 */       if ((var20 == Blocks.grass || var20 == Blocks.dirt || var20 == Blocks.farmland) && p_180709_3_.getY() < 256 - var4 - 1) {
/*     */         
/*  77 */         func_175921_a(worldIn, p_180709_3_.offsetDown());
/*  78 */         int var21 = p_180709_2_.nextInt(2);
/*  79 */         int var11 = 1;
/*  80 */         byte var22 = 0;
/*     */         
/*     */         int var23;
/*     */         
/*  84 */         for (var23 = 0; var23 <= var6; var23++) {
/*     */           
/*  86 */           int i = p_180709_3_.getY() + var4 - var23;
/*     */           
/*  88 */           for (int var15 = p_180709_3_.getX() - var21; var15 <= p_180709_3_.getX() + var21; var15++) {
/*     */             
/*  90 */             int var16 = var15 - p_180709_3_.getX();
/*     */             
/*  92 */             for (int var17 = p_180709_3_.getZ() - var21; var17 <= p_180709_3_.getZ() + var21; var17++) {
/*     */               
/*  94 */               int var18 = var17 - p_180709_3_.getZ();
/*     */               
/*  96 */               if (Math.abs(var16) != var21 || Math.abs(var18) != var21 || var21 <= 0) {
/*     */                 
/*  98 */                 BlockPos var19 = new BlockPos(var15, i, var17);
/*     */                 
/* 100 */                 if (!worldIn.getBlockState(var19).getBlock().isFullBlock())
/*     */                 {
/* 102 */                   func_175905_a(worldIn, var19, (Block)Blocks.leaves, BlockPlanks.EnumType.SPRUCE.func_176839_a());
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 108 */           if (var21 >= var11) {
/*     */             
/* 110 */             var21 = var22;
/* 111 */             var22 = 1;
/* 112 */             var11++;
/*     */             
/* 114 */             if (var11 > var7)
/*     */             {
/* 116 */               var11 = var7;
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/* 121 */             var21++;
/*     */           } 
/*     */         } 
/*     */         
/* 125 */         var23 = p_180709_2_.nextInt(3);
/*     */         
/* 127 */         for (int var14 = 0; var14 < var4 - var23; var14++) {
/*     */           
/* 129 */           Block var24 = worldIn.getBlockState(p_180709_3_.offsetUp(var14)).getBlock();
/*     */           
/* 131 */           if (var24.getMaterial() == Material.air || var24.getMaterial() == Material.leaves)
/*     */           {
/* 133 */             func_175905_a(worldIn, p_180709_3_.offsetUp(var14), Blocks.log, BlockPlanks.EnumType.SPRUCE.func_176839_a());
/*     */           }
/*     */         } 
/*     */         
/* 137 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 141 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenTaiga2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */