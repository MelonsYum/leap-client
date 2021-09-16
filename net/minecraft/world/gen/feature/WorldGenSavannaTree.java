/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockPlanks;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenSavannaTree
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private static final String __OBFID = "CL_00000432";
/*     */   
/*     */   public WorldGenSavannaTree(boolean p_i45463_1_) {
/*  18 */     super(p_i45463_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  23 */     int var4 = p_180709_2_.nextInt(3) + p_180709_2_.nextInt(3) + 5;
/*  24 */     boolean var5 = true;
/*     */     
/*  26 */     if (p_180709_3_.getY() >= 1 && p_180709_3_.getY() + var4 + 1 <= 256) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  31 */       for (int var6 = p_180709_3_.getY(); var6 <= p_180709_3_.getY() + 1 + var4; var6++) {
/*     */         
/*  33 */         byte var7 = 1;
/*     */         
/*  35 */         if (var6 == p_180709_3_.getY())
/*     */         {
/*  37 */           var7 = 0;
/*     */         }
/*     */         
/*  40 */         if (var6 >= p_180709_3_.getY() + 1 + var4 - 2)
/*     */         {
/*  42 */           var7 = 2;
/*     */         }
/*     */         
/*  45 */         for (int var8 = p_180709_3_.getX() - var7; var8 <= p_180709_3_.getX() + var7 && var5; var8++) {
/*     */           
/*  47 */           for (int var9 = p_180709_3_.getZ() - var7; var9 <= p_180709_3_.getZ() + var7 && var5; var9++) {
/*     */             
/*  49 */             if (var6 >= 0 && var6 < 256) {
/*     */               
/*  51 */               if (!func_150523_a(worldIn.getBlockState(new BlockPos(var8, var6, var9)).getBlock()))
/*     */               {
/*  53 */                 var5 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  58 */               var5 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  64 */       if (!var5)
/*     */       {
/*  66 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  70 */       Block var20 = worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock();
/*     */       
/*  72 */       if ((var20 == Blocks.grass || var20 == Blocks.dirt) && p_180709_3_.getY() < 256 - var4 - 1) {
/*     */         
/*  74 */         func_175921_a(worldIn, p_180709_3_.offsetDown());
/*  75 */         EnumFacing var21 = EnumFacing.Plane.HORIZONTAL.random(p_180709_2_);
/*  76 */         int var8 = var4 - p_180709_2_.nextInt(4) - 1;
/*  77 */         int var9 = 3 - p_180709_2_.nextInt(3);
/*  78 */         int var10 = p_180709_3_.getX();
/*  79 */         int var11 = p_180709_3_.getZ();
/*  80 */         int var12 = 0;
/*     */ 
/*     */         
/*  83 */         for (int var13 = 0; var13 < var4; var13++) {
/*     */           
/*  85 */           int i = p_180709_3_.getY() + var13;
/*     */           
/*  87 */           if (var13 >= var8 && var9 > 0) {
/*     */             
/*  89 */             var10 += var21.getFrontOffsetX();
/*  90 */             var11 += var21.getFrontOffsetZ();
/*  91 */             var9--;
/*     */           } 
/*     */           
/*  94 */           BlockPos var15 = new BlockPos(var10, i, var11);
/*  95 */           Material var16 = worldIn.getBlockState(var15).getBlock().getMaterial();
/*     */           
/*  97 */           if (var16 == Material.air || var16 == Material.leaves) {
/*     */             
/*  99 */             func_175905_a(worldIn, var15, Blocks.log2, BlockPlanks.EnumType.ACACIA.func_176839_a() - 4);
/* 100 */             var12 = i;
/*     */           } 
/*     */         } 
/*     */         
/* 104 */         BlockPos var22 = new BlockPos(var10, var12, var11);
/*     */         
/*     */         int var14;
/* 107 */         for (var14 = -3; var14 <= 3; var14++) {
/*     */           
/* 109 */           for (int var24 = -3; var24 <= 3; var24++) {
/*     */             
/* 111 */             if (Math.abs(var14) != 3 || Math.abs(var24) != 3)
/*     */             {
/* 113 */               func_175924_b(worldIn, var22.add(var14, 0, var24));
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 118 */         var22 = var22.offsetUp();
/*     */         
/* 120 */         for (var14 = -1; var14 <= 1; var14++) {
/*     */           
/* 122 */           for (int var24 = -1; var24 <= 1; var24++)
/*     */           {
/* 124 */             func_175924_b(worldIn, var22.add(var14, 0, var24));
/*     */           }
/*     */         } 
/*     */         
/* 128 */         func_175924_b(worldIn, var22.offsetEast(2));
/* 129 */         func_175924_b(worldIn, var22.offsetWest(2));
/* 130 */         func_175924_b(worldIn, var22.offsetSouth(2));
/* 131 */         func_175924_b(worldIn, var22.offsetNorth(2));
/* 132 */         var10 = p_180709_3_.getX();
/* 133 */         var11 = p_180709_3_.getZ();
/* 134 */         EnumFacing var23 = EnumFacing.Plane.HORIZONTAL.random(p_180709_2_);
/*     */         
/* 136 */         if (var23 != var21) {
/*     */           
/* 138 */           var14 = var8 - p_180709_2_.nextInt(2) - 1;
/* 139 */           int var24 = 1 + p_180709_2_.nextInt(3);
/* 140 */           var12 = 0;
/*     */ 
/*     */           
/* 143 */           for (int var25 = var14; var25 < var4 && var24 > 0; var24--) {
/*     */             
/* 145 */             if (var25 >= 1) {
/*     */               
/* 147 */               int var17 = p_180709_3_.getY() + var25;
/* 148 */               var10 += var23.getFrontOffsetX();
/* 149 */               var11 += var23.getFrontOffsetZ();
/* 150 */               BlockPos var18 = new BlockPos(var10, var17, var11);
/* 151 */               Material var19 = worldIn.getBlockState(var18).getBlock().getMaterial();
/*     */               
/* 153 */               if (var19 == Material.air || var19 == Material.leaves) {
/*     */                 
/* 155 */                 func_175905_a(worldIn, var18, Blocks.log2, BlockPlanks.EnumType.ACACIA.func_176839_a() - 4);
/* 156 */                 var12 = var17;
/*     */               } 
/*     */             } 
/*     */             
/* 160 */             var25++;
/*     */           } 
/*     */           
/* 163 */           if (var12 > 0) {
/*     */             
/* 165 */             BlockPos var26 = new BlockPos(var10, var12, var11);
/*     */             
/*     */             int var17;
/* 168 */             for (var17 = -2; var17 <= 2; var17++) {
/*     */               
/* 170 */               for (int var27 = -2; var27 <= 2; var27++) {
/*     */                 
/* 172 */                 if (Math.abs(var17) != 2 || Math.abs(var27) != 2)
/*     */                 {
/* 174 */                   func_175924_b(worldIn, var26.add(var17, 0, var27));
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 179 */             var26 = var26.offsetUp();
/*     */             
/* 181 */             for (var17 = -1; var17 <= 1; var17++) {
/*     */               
/* 183 */               for (int var27 = -1; var27 <= 1; var27++)
/*     */               {
/* 185 */                 func_175924_b(worldIn, var26.add(var17, 0, var27));
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 191 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 195 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_175924_b(World worldIn, BlockPos p_175924_2_) {
/* 207 */     Material var3 = worldIn.getBlockState(p_175924_2_).getBlock().getMaterial();
/*     */     
/* 209 */     if (var3 == Material.air || var3 == Material.leaves)
/*     */     {
/* 211 */       func_175905_a(worldIn, p_175924_2_, (Block)Blocks.leaves2, 0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenSavannaTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */