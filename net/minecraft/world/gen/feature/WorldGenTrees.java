/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockVine;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenTrees
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private final int minTreeHeight;
/*     */   private final boolean vinesGrow;
/*     */   private final int metaWood;
/*     */   private final int metaLeaves;
/*     */   private static final String __OBFID = "CL_00000438";
/*     */   
/*     */   public WorldGenTrees(boolean p_i2027_1_) {
/*  29 */     this(p_i2027_1_, 4, 0, 0, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenTrees(boolean p_i2028_1_, int p_i2028_2_, int p_i2028_3_, int p_i2028_4_, boolean p_i2028_5_) {
/*  34 */     super(p_i2028_1_);
/*  35 */     this.minTreeHeight = p_i2028_2_;
/*  36 */     this.metaWood = p_i2028_3_;
/*  37 */     this.metaLeaves = p_i2028_4_;
/*  38 */     this.vinesGrow = p_i2028_5_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  43 */     int var4 = p_180709_2_.nextInt(3) + this.minTreeHeight;
/*  44 */     boolean var5 = true;
/*     */     
/*  46 */     if (p_180709_3_.getY() >= 1 && p_180709_3_.getY() + var4 + 1 <= 256) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  51 */       for (int var6 = p_180709_3_.getY(); var6 <= p_180709_3_.getY() + 1 + var4; var6++) {
/*     */         
/*  53 */         byte var7 = 1;
/*     */         
/*  55 */         if (var6 == p_180709_3_.getY())
/*     */         {
/*  57 */           var7 = 0;
/*     */         }
/*     */         
/*  60 */         if (var6 >= p_180709_3_.getY() + 1 + var4 - 2)
/*     */         {
/*  62 */           var7 = 2;
/*     */         }
/*     */         
/*  65 */         for (int var8 = p_180709_3_.getX() - var7; var8 <= p_180709_3_.getX() + var7 && var5; var8++) {
/*     */           
/*  67 */           for (int var9 = p_180709_3_.getZ() - var7; var9 <= p_180709_3_.getZ() + var7 && var5; var9++) {
/*     */             
/*  69 */             if (var6 >= 0 && var6 < 256) {
/*     */               
/*  71 */               if (!func_150523_a(worldIn.getBlockState(new BlockPos(var8, var6, var9)).getBlock()))
/*     */               {
/*  73 */                 var5 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  78 */               var5 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  84 */       if (!var5)
/*     */       {
/*  86 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  90 */       Block var19 = worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock();
/*     */       
/*  92 */       if ((var19 == Blocks.grass || var19 == Blocks.dirt || var19 == Blocks.farmland) && p_180709_3_.getY() < 256 - var4 - 1) {
/*     */         
/*  94 */         func_175921_a(worldIn, p_180709_3_.offsetDown());
/*  95 */         byte var7 = 3;
/*  96 */         byte var20 = 0;
/*     */ 
/*     */ 
/*     */         
/*     */         int var9;
/*     */ 
/*     */         
/* 103 */         for (var9 = p_180709_3_.getY() - var7 + var4; var9 <= p_180709_3_.getY() + var4; var9++) {
/*     */           
/* 105 */           int var10 = var9 - p_180709_3_.getY() + var4;
/* 106 */           int var11 = var20 + 1 - var10 / 2;
/*     */           
/* 108 */           for (int var12 = p_180709_3_.getX() - var11; var12 <= p_180709_3_.getX() + var11; var12++) {
/*     */             
/* 110 */             int var13 = var12 - p_180709_3_.getX();
/*     */             
/* 112 */             for (int var14 = p_180709_3_.getZ() - var11; var14 <= p_180709_3_.getZ() + var11; var14++) {
/*     */               
/* 114 */               int var15 = var14 - p_180709_3_.getZ();
/*     */               
/* 116 */               if (Math.abs(var13) != var11 || Math.abs(var15) != var11 || (p_180709_2_.nextInt(2) != 0 && var10 != 0)) {
/*     */                 
/* 118 */                 BlockPos var16 = new BlockPos(var12, var9, var14);
/* 119 */                 Block var17 = worldIn.getBlockState(var16).getBlock();
/*     */                 
/* 121 */                 if (var17.getMaterial() == Material.air || var17.getMaterial() == Material.leaves || var17.getMaterial() == Material.vine)
/*     */                 {
/* 123 */                   func_175905_a(worldIn, var16, (Block)Blocks.leaves, this.metaLeaves);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 130 */         for (var9 = 0; var9 < var4; var9++) {
/*     */           
/* 132 */           Block var21 = worldIn.getBlockState(p_180709_3_.offsetUp(var9)).getBlock();
/*     */           
/* 134 */           if (var21.getMaterial() == Material.air || var21.getMaterial() == Material.leaves || var21.getMaterial() == Material.vine) {
/*     */             
/* 136 */             func_175905_a(worldIn, p_180709_3_.offsetUp(var9), Blocks.log, this.metaWood);
/*     */             
/* 138 */             if (this.vinesGrow && var9 > 0) {
/*     */               
/* 140 */               if (p_180709_2_.nextInt(3) > 0 && worldIn.isAirBlock(p_180709_3_.add(-1, var9, 0)))
/*     */               {
/* 142 */                 func_175905_a(worldIn, p_180709_3_.add(-1, var9, 0), Blocks.vine, BlockVine.field_176275_S);
/*     */               }
/*     */               
/* 145 */               if (p_180709_2_.nextInt(3) > 0 && worldIn.isAirBlock(p_180709_3_.add(1, var9, 0)))
/*     */               {
/* 147 */                 func_175905_a(worldIn, p_180709_3_.add(1, var9, 0), Blocks.vine, BlockVine.field_176271_T);
/*     */               }
/*     */               
/* 150 */               if (p_180709_2_.nextInt(3) > 0 && worldIn.isAirBlock(p_180709_3_.add(0, var9, -1)))
/*     */               {
/* 152 */                 func_175905_a(worldIn, p_180709_3_.add(0, var9, -1), Blocks.vine, BlockVine.field_176272_Q);
/*     */               }
/*     */               
/* 155 */               if (p_180709_2_.nextInt(3) > 0 && worldIn.isAirBlock(p_180709_3_.add(0, var9, 1)))
/*     */               {
/* 157 */                 func_175905_a(worldIn, p_180709_3_.add(0, var9, 1), Blocks.vine, BlockVine.field_176276_R);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 163 */         if (this.vinesGrow) {
/*     */           
/* 165 */           for (var9 = p_180709_3_.getY() - 3 + var4; var9 <= p_180709_3_.getY() + var4; var9++) {
/*     */             
/* 167 */             int var10 = var9 - p_180709_3_.getY() + var4;
/* 168 */             int var11 = 2 - var10 / 2;
/*     */             
/* 170 */             for (int var12 = p_180709_3_.getX() - var11; var12 <= p_180709_3_.getX() + var11; var12++) {
/*     */               
/* 172 */               for (int var13 = p_180709_3_.getZ() - var11; var13 <= p_180709_3_.getZ() + var11; var13++) {
/*     */                 
/* 174 */                 BlockPos var23 = new BlockPos(var12, var9, var13);
/*     */                 
/* 176 */                 if (worldIn.getBlockState(var23).getBlock().getMaterial() == Material.leaves) {
/*     */                   
/* 178 */                   BlockPos var24 = var23.offsetWest();
/* 179 */                   BlockPos var16 = var23.offsetEast();
/* 180 */                   BlockPos var25 = var23.offsetNorth();
/* 181 */                   BlockPos var18 = var23.offsetSouth();
/*     */                   
/* 183 */                   if (p_180709_2_.nextInt(4) == 0 && worldIn.getBlockState(var24).getBlock().getMaterial() == Material.air)
/*     */                   {
/* 185 */                     func_175923_a(worldIn, var24, BlockVine.field_176275_S);
/*     */                   }
/*     */                   
/* 188 */                   if (p_180709_2_.nextInt(4) == 0 && worldIn.getBlockState(var16).getBlock().getMaterial() == Material.air)
/*     */                   {
/* 190 */                     func_175923_a(worldIn, var16, BlockVine.field_176271_T);
/*     */                   }
/*     */                   
/* 193 */                   if (p_180709_2_.nextInt(4) == 0 && worldIn.getBlockState(var25).getBlock().getMaterial() == Material.air)
/*     */                   {
/* 195 */                     func_175923_a(worldIn, var25, BlockVine.field_176272_Q);
/*     */                   }
/*     */                   
/* 198 */                   if (p_180709_2_.nextInt(4) == 0 && worldIn.getBlockState(var18).getBlock().getMaterial() == Material.air)
/*     */                   {
/* 200 */                     func_175923_a(worldIn, var18, BlockVine.field_176276_R);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 207 */           if (p_180709_2_.nextInt(5) == 0 && var4 > 5)
/*     */           {
/* 209 */             for (var9 = 0; var9 < 2; var9++) {
/*     */               
/* 211 */               for (int var10 = 0; var10 < 4; var10++) {
/*     */                 
/* 213 */                 if (p_180709_2_.nextInt(4 - var9) == 0) {
/*     */                   
/* 215 */                   int var11 = p_180709_2_.nextInt(3);
/* 216 */                   EnumFacing var22 = EnumFacing.getHorizontal(var10).getOpposite();
/* 217 */                   func_175905_a(worldIn, p_180709_3_.add(var22.getFrontOffsetX(), var4 - 5 + var9, var22.getFrontOffsetZ()), Blocks.cocoa, var11 << 2 | EnumFacing.getHorizontal(var10).getHorizontalIndex());
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 224 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 228 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_175923_a(World worldIn, BlockPos p_175923_2_, int p_175923_3_) {
/* 240 */     func_175905_a(worldIn, p_175923_2_, Blocks.vine, p_175923_3_);
/* 241 */     int var4 = 4;
/*     */     
/* 243 */     for (p_175923_2_ = p_175923_2_.offsetDown(); worldIn.getBlockState(p_175923_2_).getBlock().getMaterial() == Material.air && var4 > 0; var4--) {
/*     */       
/* 245 */       func_175905_a(worldIn, p_175923_2_, Blocks.vine, p_175923_3_);
/* 246 */       p_175923_2_ = p_175923_2_.offsetDown();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */