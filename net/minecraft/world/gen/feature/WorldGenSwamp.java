/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockVine;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenSwamp
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private static final String __OBFID = "CL_00000436";
/*     */   
/*     */   public WorldGenSwamp() {
/*  17 */     super(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*     */     int var4;
/*  24 */     for (var4 = p_180709_2_.nextInt(4) + 5; worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock().getMaterial() == Material.water; p_180709_3_ = p_180709_3_.offsetDown());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     boolean var5 = true;
/*     */     
/*  31 */     if (p_180709_3_.getY() >= 1 && p_180709_3_.getY() + var4 + 1 <= 256) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  36 */       for (int var6 = p_180709_3_.getY(); var6 <= p_180709_3_.getY() + 1 + var4; var6++) {
/*     */         
/*  38 */         byte var7 = 1;
/*     */         
/*  40 */         if (var6 == p_180709_3_.getY())
/*     */         {
/*  42 */           var7 = 0;
/*     */         }
/*     */         
/*  45 */         if (var6 >= p_180709_3_.getY() + 1 + var4 - 2)
/*     */         {
/*  47 */           var7 = 3;
/*     */         }
/*     */         
/*  50 */         for (int var8 = p_180709_3_.getX() - var7; var8 <= p_180709_3_.getX() + var7 && var5; var8++) {
/*     */           
/*  52 */           for (int var9 = p_180709_3_.getZ() - var7; var9 <= p_180709_3_.getZ() + var7 && var5; var9++) {
/*     */             
/*  54 */             if (var6 >= 0 && var6 < 256) {
/*     */               
/*  56 */               Block var10 = worldIn.getBlockState(new BlockPos(var8, var6, var9)).getBlock();
/*     */               
/*  58 */               if (var10.getMaterial() != Material.air && var10.getMaterial() != Material.leaves)
/*     */               {
/*  60 */                 if (var10 != Blocks.water && var10 != Blocks.flowing_water)
/*     */                 {
/*  62 */                   var5 = false;
/*     */                 }
/*  64 */                 else if (var6 > p_180709_3_.getY())
/*     */                 {
/*  66 */                   var5 = false;
/*     */                 }
/*     */               
/*     */               }
/*     */             } else {
/*     */               
/*  72 */               var5 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  78 */       if (!var5)
/*     */       {
/*  80 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  84 */       Block var17 = worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock();
/*     */       
/*  86 */       if ((var17 == Blocks.grass || var17 == Blocks.dirt) && p_180709_3_.getY() < 256 - var4 - 1) {
/*     */         
/*  88 */         func_175921_a(worldIn, p_180709_3_.offsetDown());
/*     */ 
/*     */         
/*     */         int var18;
/*     */ 
/*     */         
/*  94 */         for (var18 = p_180709_3_.getY() - 3 + var4; var18 <= p_180709_3_.getY() + var4; var18++) {
/*     */           
/*  96 */           int var8 = var18 - p_180709_3_.getY() + var4;
/*  97 */           int var9 = 2 - var8 / 2;
/*     */           
/*  99 */           for (int var20 = p_180709_3_.getX() - var9; var20 <= p_180709_3_.getX() + var9; var20++) {
/*     */             
/* 101 */             int var11 = var20 - p_180709_3_.getX();
/*     */             
/* 103 */             for (int var12 = p_180709_3_.getZ() - var9; var12 <= p_180709_3_.getZ() + var9; var12++) {
/*     */               
/* 105 */               int var13 = var12 - p_180709_3_.getZ();
/*     */               
/* 107 */               if (Math.abs(var11) != var9 || Math.abs(var13) != var9 || (p_180709_2_.nextInt(2) != 0 && var8 != 0)) {
/*     */                 
/* 109 */                 BlockPos var14 = new BlockPos(var20, var18, var12);
/*     */                 
/* 111 */                 if (!worldIn.getBlockState(var14).getBlock().isFullBlock())
/*     */                 {
/* 113 */                   func_175906_a(worldIn, var14, (Block)Blocks.leaves);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 120 */         for (var18 = 0; var18 < var4; var18++) {
/*     */           
/* 122 */           Block var19 = worldIn.getBlockState(p_180709_3_.offsetUp(var18)).getBlock();
/*     */           
/* 124 */           if (var19.getMaterial() == Material.air || var19.getMaterial() == Material.leaves || var19 == Blocks.flowing_water || var19 == Blocks.water)
/*     */           {
/* 126 */             func_175906_a(worldIn, p_180709_3_.offsetUp(var18), Blocks.log);
/*     */           }
/*     */         } 
/*     */         
/* 130 */         for (var18 = p_180709_3_.getY() - 3 + var4; var18 <= p_180709_3_.getY() + var4; var18++) {
/*     */           
/* 132 */           int var8 = var18 - p_180709_3_.getY() + var4;
/* 133 */           int var9 = 2 - var8 / 2;
/*     */           
/* 135 */           for (int var20 = p_180709_3_.getX() - var9; var20 <= p_180709_3_.getX() + var9; var20++) {
/*     */             
/* 137 */             for (int var11 = p_180709_3_.getZ() - var9; var11 <= p_180709_3_.getZ() + var9; var11++) {
/*     */               
/* 139 */               BlockPos var21 = new BlockPos(var20, var18, var11);
/*     */               
/* 141 */               if (worldIn.getBlockState(var21).getBlock().getMaterial() == Material.leaves) {
/*     */                 
/* 143 */                 BlockPos var22 = var21.offsetWest();
/* 144 */                 BlockPos var14 = var21.offsetEast();
/* 145 */                 BlockPos var15 = var21.offsetNorth();
/* 146 */                 BlockPos var16 = var21.offsetSouth();
/*     */                 
/* 148 */                 if (p_180709_2_.nextInt(4) == 0 && worldIn.getBlockState(var22).getBlock().getMaterial() == Material.air)
/*     */                 {
/* 150 */                   func_175922_a(worldIn, var22, BlockVine.field_176275_S);
/*     */                 }
/*     */                 
/* 153 */                 if (p_180709_2_.nextInt(4) == 0 && worldIn.getBlockState(var14).getBlock().getMaterial() == Material.air)
/*     */                 {
/* 155 */                   func_175922_a(worldIn, var14, BlockVine.field_176271_T);
/*     */                 }
/*     */                 
/* 158 */                 if (p_180709_2_.nextInt(4) == 0 && worldIn.getBlockState(var15).getBlock().getMaterial() == Material.air)
/*     */                 {
/* 160 */                   func_175922_a(worldIn, var15, BlockVine.field_176272_Q);
/*     */                 }
/*     */                 
/* 163 */                 if (p_180709_2_.nextInt(4) == 0 && worldIn.getBlockState(var16).getBlock().getMaterial() == Material.air)
/*     */                 {
/* 165 */                   func_175922_a(worldIn, var16, BlockVine.field_176276_R);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 172 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 176 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_175922_a(World worldIn, BlockPos p_175922_2_, int p_175922_3_) {
/* 188 */     func_175905_a(worldIn, p_175922_2_, Blocks.vine, p_175922_3_);
/* 189 */     int var4 = 4;
/*     */     
/* 191 */     for (p_175922_2_ = p_175922_2_.offsetDown(); worldIn.getBlockState(p_175922_2_).getBlock().getMaterial() == Material.air && var4 > 0; var4--) {
/*     */       
/* 193 */       func_175905_a(worldIn, p_175922_2_, Blocks.vine, p_175922_3_);
/* 194 */       p_175922_2_ = p_175922_2_.offsetDown();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenSwamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */