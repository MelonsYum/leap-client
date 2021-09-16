/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class WorldGenLakes
/*     */   extends WorldGenerator
/*     */ {
/*     */   private Block field_150556_a;
/*     */   private static final String __OBFID = "CL_00000418";
/*     */   
/*     */   public WorldGenLakes(Block p_i45455_1_) {
/*  19 */     this.field_150556_a = p_i45455_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  24 */     for (p_180709_3_ = p_180709_3_.add(-8, 0, -8); p_180709_3_.getY() > 5 && worldIn.isAirBlock(p_180709_3_); p_180709_3_ = p_180709_3_.offsetDown());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     if (p_180709_3_.getY() <= 4)
/*     */     {
/*  31 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  35 */     p_180709_3_ = p_180709_3_.offsetDown(4);
/*  36 */     boolean[] var4 = new boolean[2048];
/*  37 */     int var5 = p_180709_2_.nextInt(4) + 4;
/*     */     
/*     */     int var6;
/*  40 */     for (var6 = 0; var6 < var5; var6++) {
/*     */       
/*  42 */       double var7 = p_180709_2_.nextDouble() * 6.0D + 3.0D;
/*  43 */       double var9 = p_180709_2_.nextDouble() * 4.0D + 2.0D;
/*  44 */       double var11 = p_180709_2_.nextDouble() * 6.0D + 3.0D;
/*  45 */       double var13 = p_180709_2_.nextDouble() * (16.0D - var7 - 2.0D) + 1.0D + var7 / 2.0D;
/*  46 */       double var15 = p_180709_2_.nextDouble() * (8.0D - var9 - 4.0D) + 2.0D + var9 / 2.0D;
/*  47 */       double var17 = p_180709_2_.nextDouble() * (16.0D - var11 - 2.0D) + 1.0D + var11 / 2.0D;
/*     */       
/*  49 */       for (int var19 = 1; var19 < 15; var19++) {
/*     */         
/*  51 */         for (int var20 = 1; var20 < 15; var20++) {
/*     */           
/*  53 */           for (int var21 = 1; var21 < 7; var21++) {
/*     */             
/*  55 */             double var22 = (var19 - var13) / var7 / 2.0D;
/*  56 */             double var24 = (var21 - var15) / var9 / 2.0D;
/*  57 */             double var26 = (var20 - var17) / var11 / 2.0D;
/*  58 */             double var28 = var22 * var22 + var24 * var24 + var26 * var26;
/*     */             
/*  60 */             if (var28 < 1.0D)
/*     */             {
/*  62 */               var4[(var19 * 16 + var20) * 8 + var21] = true;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     for (var6 = 0; var6 < 16; var6++) {
/*     */       
/*  75 */       for (int var30 = 0; var30 < 16; var30++) {
/*     */         
/*  77 */         for (int var8 = 0; var8 < 8; var8++) {
/*     */           
/*  79 */           boolean var32 = (!var4[(var6 * 16 + var30) * 8 + var8] && ((var6 < 15 && var4[((var6 + 1) * 16 + var30) * 8 + var8]) || (var6 > 0 && var4[((var6 - 1) * 16 + var30) * 8 + var8]) || (var30 < 15 && var4[(var6 * 16 + var30 + 1) * 8 + var8]) || (var30 > 0 && var4[(var6 * 16 + var30 - 1) * 8 + var8]) || (var8 < 7 && var4[(var6 * 16 + var30) * 8 + var8 + 1]) || (var8 > 0 && var4[(var6 * 16 + var30) * 8 + var8 - 1])));
/*     */           
/*  81 */           if (var32) {
/*     */             
/*  83 */             Material var10 = worldIn.getBlockState(p_180709_3_.add(var6, var8, var30)).getBlock().getMaterial();
/*     */             
/*  85 */             if (var8 >= 4 && var10.isLiquid())
/*     */             {
/*  87 */               return false;
/*     */             }
/*     */             
/*  90 */             if (var8 < 4 && !var10.isSolid() && worldIn.getBlockState(p_180709_3_.add(var6, var8, var30)).getBlock() != this.field_150556_a)
/*     */             {
/*  92 */               return false;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     for (var6 = 0; var6 < 16; var6++) {
/*     */       
/* 101 */       for (int var30 = 0; var30 < 16; var30++) {
/*     */         
/* 103 */         for (int var8 = 0; var8 < 8; var8++) {
/*     */           
/* 105 */           if (var4[(var6 * 16 + var30) * 8 + var8])
/*     */           {
/* 107 */             worldIn.setBlockState(p_180709_3_.add(var6, var8, var30), (var8 >= 4) ? Blocks.air.getDefaultState() : this.field_150556_a.getDefaultState(), 2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     for (var6 = 0; var6 < 16; var6++) {
/*     */       
/* 115 */       for (int var30 = 0; var30 < 16; var30++) {
/*     */         
/* 117 */         for (int var8 = 4; var8 < 8; var8++) {
/*     */           
/* 119 */           if (var4[(var6 * 16 + var30) * 8 + var8]) {
/*     */             
/* 121 */             BlockPos var33 = p_180709_3_.add(var6, var8 - 1, var30);
/*     */             
/* 123 */             if (worldIn.getBlockState(var33).getBlock() == Blocks.dirt && worldIn.getLightFor(EnumSkyBlock.SKY, p_180709_3_.add(var6, var8, var30)) > 0) {
/*     */               
/* 125 */               BiomeGenBase var34 = worldIn.getBiomeGenForCoords(var33);
/*     */               
/* 127 */               if (var34.topBlock.getBlock() == Blocks.mycelium) {
/*     */                 
/* 129 */                 worldIn.setBlockState(var33, Blocks.mycelium.getDefaultState(), 2);
/*     */               }
/*     */               else {
/*     */                 
/* 133 */                 worldIn.setBlockState(var33, Blocks.grass.getDefaultState(), 2);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 141 */     if (this.field_150556_a.getMaterial() == Material.lava)
/*     */     {
/* 143 */       for (var6 = 0; var6 < 16; var6++) {
/*     */         
/* 145 */         for (int var30 = 0; var30 < 16; var30++) {
/*     */           
/* 147 */           for (int var8 = 0; var8 < 8; var8++) {
/*     */             
/* 149 */             boolean var32 = (!var4[(var6 * 16 + var30) * 8 + var8] && ((var6 < 15 && var4[((var6 + 1) * 16 + var30) * 8 + var8]) || (var6 > 0 && var4[((var6 - 1) * 16 + var30) * 8 + var8]) || (var30 < 15 && var4[(var6 * 16 + var30 + 1) * 8 + var8]) || (var30 > 0 && var4[(var6 * 16 + var30 - 1) * 8 + var8]) || (var8 < 7 && var4[(var6 * 16 + var30) * 8 + var8 + 1]) || (var8 > 0 && var4[(var6 * 16 + var30) * 8 + var8 - 1])));
/*     */             
/* 151 */             if (var32 && (var8 < 4 || p_180709_2_.nextInt(2) != 0) && worldIn.getBlockState(p_180709_3_.add(var6, var8, var30)).getBlock().getMaterial().isSolid())
/*     */             {
/* 153 */               worldIn.setBlockState(p_180709_3_.add(var6, var8, var30), Blocks.stone.getDefaultState(), 2);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 160 */     if (this.field_150556_a.getMaterial() == Material.water)
/*     */     {
/* 162 */       for (var6 = 0; var6 < 16; var6++) {
/*     */         
/* 164 */         for (int var30 = 0; var30 < 16; var30++) {
/*     */           
/* 166 */           byte var31 = 4;
/*     */           
/* 168 */           if (worldIn.func_175675_v(p_180709_3_.add(var6, var31, var30)))
/*     */           {
/* 170 */             worldIn.setBlockState(p_180709_3_.add(var6, var31, var30), Blocks.ice.getDefaultState(), 2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 176 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenLakes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */