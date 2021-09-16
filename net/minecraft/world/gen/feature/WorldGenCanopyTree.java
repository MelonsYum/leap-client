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
/*     */ public class WorldGenCanopyTree
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private static final String __OBFID = "CL_00000430";
/*     */   
/*     */   public WorldGenCanopyTree(boolean p_i45461_1_) {
/*  18 */     super(p_i45461_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  23 */     int var4 = p_180709_2_.nextInt(3) + p_180709_2_.nextInt(2) + 6;
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
/*  70 */       Block var18 = worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock();
/*     */       
/*  72 */       if ((var18 == Blocks.grass || var18 == Blocks.dirt) && p_180709_3_.getY() < 256 - var4 - 1) {
/*     */         
/*  74 */         func_175921_a(worldIn, p_180709_3_.offsetDown());
/*  75 */         func_175921_a(worldIn, p_180709_3_.add(1, -1, 0));
/*  76 */         func_175921_a(worldIn, p_180709_3_.add(1, -1, 1));
/*  77 */         func_175921_a(worldIn, p_180709_3_.add(0, -1, 1));
/*  78 */         EnumFacing var19 = EnumFacing.Plane.HORIZONTAL.random(p_180709_2_);
/*  79 */         int var8 = var4 - p_180709_2_.nextInt(4);
/*  80 */         int var9 = 2 - p_180709_2_.nextInt(3);
/*  81 */         int var10 = p_180709_3_.getX();
/*  82 */         int var11 = p_180709_3_.getZ();
/*  83 */         int var12 = 0;
/*     */         
/*     */         int var13;
/*     */         
/*  87 */         for (var13 = 0; var13 < var4; var13++) {
/*     */           
/*  89 */           int var14 = p_180709_3_.getY() + var13;
/*     */           
/*  91 */           if (var13 >= var8 && var9 > 0) {
/*     */             
/*  93 */             var10 += var19.getFrontOffsetX();
/*  94 */             var11 += var19.getFrontOffsetZ();
/*  95 */             var9--;
/*     */           } 
/*     */           
/*  98 */           BlockPos var15 = new BlockPos(var10, var14, var11);
/*  99 */           Material var16 = worldIn.getBlockState(var15).getBlock().getMaterial();
/*     */           
/* 101 */           if (var16 == Material.air || var16 == Material.leaves) {
/*     */             
/* 103 */             func_175905_a(worldIn, var15, Blocks.log2, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4);
/* 104 */             func_175905_a(worldIn, var15.offsetEast(), Blocks.log2, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4);
/* 105 */             func_175905_a(worldIn, var15.offsetSouth(), Blocks.log2, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4);
/* 106 */             func_175905_a(worldIn, var15.offsetEast().offsetSouth(), Blocks.log2, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4);
/* 107 */             var12 = var14;
/*     */           } 
/*     */         } 
/*     */         
/* 111 */         for (var13 = -2; var13 <= 0; var13++) {
/*     */           
/* 113 */           for (int var14 = -2; var14 <= 0; var14++) {
/*     */             
/* 115 */             byte var20 = -1;
/* 116 */             func_150526_a(worldIn, var10 + var13, var12 + var20, var11 + var14);
/* 117 */             func_150526_a(worldIn, 1 + var10 - var13, var12 + var20, var11 + var14);
/* 118 */             func_150526_a(worldIn, var10 + var13, var12 + var20, 1 + var11 - var14);
/* 119 */             func_150526_a(worldIn, 1 + var10 - var13, var12 + var20, 1 + var11 - var14);
/*     */             
/* 121 */             if ((var13 > -2 || var14 > -1) && (var13 != -1 || var14 != -2)) {
/*     */               
/* 123 */               byte var21 = 1;
/* 124 */               func_150526_a(worldIn, var10 + var13, var12 + var21, var11 + var14);
/* 125 */               func_150526_a(worldIn, 1 + var10 - var13, var12 + var21, var11 + var14);
/* 126 */               func_150526_a(worldIn, var10 + var13, var12 + var21, 1 + var11 - var14);
/* 127 */               func_150526_a(worldIn, 1 + var10 - var13, var12 + var21, 1 + var11 - var14);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 132 */         if (p_180709_2_.nextBoolean()) {
/*     */           
/* 134 */           func_150526_a(worldIn, var10, var12 + 2, var11);
/* 135 */           func_150526_a(worldIn, var10 + 1, var12 + 2, var11);
/* 136 */           func_150526_a(worldIn, var10 + 1, var12 + 2, var11 + 1);
/* 137 */           func_150526_a(worldIn, var10, var12 + 2, var11 + 1);
/*     */         } 
/*     */         
/* 140 */         for (var13 = -3; var13 <= 4; var13++) {
/*     */           
/* 142 */           for (int var14 = -3; var14 <= 4; var14++) {
/*     */             
/* 144 */             if ((var13 != -3 || var14 != -3) && (var13 != -3 || var14 != 4) && (var13 != 4 || var14 != -3) && (var13 != 4 || var14 != 4) && (Math.abs(var13) < 3 || Math.abs(var14) < 3))
/*     */             {
/* 146 */               func_150526_a(worldIn, var10 + var13, var12, var11 + var14);
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 151 */         for (var13 = -1; var13 <= 2; var13++) {
/*     */           
/* 153 */           for (int var14 = -1; var14 <= 2; var14++) {
/*     */             
/* 155 */             if ((var13 < 0 || var13 > 1 || var14 < 0 || var14 > 1) && p_180709_2_.nextInt(3) <= 0) {
/*     */               
/* 157 */               int var22 = p_180709_2_.nextInt(3) + 2;
/*     */               
/*     */               int var23;
/* 160 */               for (var23 = 0; var23 < var22; var23++)
/*     */               {
/* 162 */                 func_175905_a(worldIn, new BlockPos(p_180709_3_.getX() + var13, var12 - var23 - 1, p_180709_3_.getZ() + var14), Blocks.log2, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4);
/*     */               }
/*     */ 
/*     */ 
/*     */               
/* 167 */               for (var23 = -1; var23 <= 1; var23++) {
/*     */                 
/* 169 */                 for (int var17 = -1; var17 <= 1; var17++)
/*     */                 {
/* 171 */                   func_150526_a(worldIn, var10 + var13 + var23, var12 - 0, var11 + var14 + var17);
/*     */                 }
/*     */               } 
/*     */               
/* 175 */               for (var23 = -2; var23 <= 2; var23++) {
/*     */                 
/* 177 */                 for (int var17 = -2; var17 <= 2; var17++) {
/*     */                   
/* 179 */                   if (Math.abs(var23) != 2 || Math.abs(var17) != 2)
/*     */                   {
/* 181 */                     func_150526_a(worldIn, var10 + var13 + var23, var12 - 1, var11 + var14 + var17);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 189 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 193 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_150526_a(World worldIn, int p_150526_2_, int p_150526_3_, int p_150526_4_) {
/* 205 */     Block var5 = worldIn.getBlockState(new BlockPos(p_150526_2_, p_150526_3_, p_150526_4_)).getBlock();
/*     */     
/* 207 */     if (var5.getMaterial() == Material.air)
/*     */     {
/* 209 */       func_175905_a(worldIn, new BlockPos(p_150526_2_, p_150526_3_, p_150526_4_), (Block)Blocks.leaves2, 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenCanopyTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */