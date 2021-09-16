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
/*     */ public class WorldGenTaiga1
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private static final String __OBFID = "CL_00000427";
/*     */   
/*     */   public WorldGenTaiga1() {
/*  17 */     super(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  22 */     int var4 = p_180709_2_.nextInt(5) + 7;
/*  23 */     int var5 = var4 - p_180709_2_.nextInt(2) - 3;
/*  24 */     int var6 = var4 - var5;
/*  25 */     int var7 = 1 + p_180709_2_.nextInt(var6 + 1);
/*  26 */     boolean var8 = true;
/*     */     
/*  28 */     if (p_180709_3_.getY() >= 1 && p_180709_3_.getY() + var4 + 1 <= 256) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  34 */       for (int var9 = p_180709_3_.getY(); var9 <= p_180709_3_.getY() + 1 + var4 && var8; var9++) {
/*     */         int var18;
/*  36 */         boolean var10 = true;
/*     */         
/*  38 */         if (var9 - p_180709_3_.getY() < var5) {
/*     */           
/*  40 */           var18 = 0;
/*     */         }
/*     */         else {
/*     */           
/*  44 */           var18 = var7;
/*     */         } 
/*     */         
/*  47 */         for (int var11 = p_180709_3_.getX() - var18; var11 <= p_180709_3_.getX() + var18 && var8; var11++) {
/*     */           
/*  49 */           for (int var12 = p_180709_3_.getZ() - var18; var12 <= p_180709_3_.getZ() + var18 && var8; var12++) {
/*     */             
/*  51 */             if (var9 >= 0 && var9 < 256) {
/*     */               
/*  53 */               if (!func_150523_a(worldIn.getBlockState(new BlockPos(var11, var9, var12)).getBlock()))
/*     */               {
/*  55 */                 var8 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  60 */               var8 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  66 */       if (!var8)
/*     */       {
/*  68 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  72 */       Block var17 = worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock();
/*     */       
/*  74 */       if ((var17 == Blocks.grass || var17 == Blocks.dirt) && p_180709_3_.getY() < 256 - var4 - 1) {
/*     */         
/*  76 */         func_175921_a(worldIn, p_180709_3_.offsetDown());
/*  77 */         int var18 = 0;
/*     */         int var11;
/*  79 */         for (var11 = p_180709_3_.getY() + var4; var11 >= p_180709_3_.getY() + var5; var11--) {
/*     */           
/*  81 */           for (int var12 = p_180709_3_.getX() - var18; var12 <= p_180709_3_.getX() + var18; var12++) {
/*     */             
/*  83 */             int var13 = var12 - p_180709_3_.getX();
/*     */             
/*  85 */             for (int var14 = p_180709_3_.getZ() - var18; var14 <= p_180709_3_.getZ() + var18; var14++) {
/*     */               
/*  87 */               int var15 = var14 - p_180709_3_.getZ();
/*     */               
/*  89 */               if (Math.abs(var13) != var18 || Math.abs(var15) != var18 || var18 <= 0) {
/*     */                 
/*  91 */                 BlockPos var16 = new BlockPos(var12, var11, var14);
/*     */                 
/*  93 */                 if (!worldIn.getBlockState(var16).getBlock().isFullBlock())
/*     */                 {
/*  95 */                   func_175905_a(worldIn, var16, (Block)Blocks.leaves, BlockPlanks.EnumType.SPRUCE.func_176839_a());
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 101 */           if (var18 >= 1 && var11 == p_180709_3_.getY() + var5 + 1) {
/*     */             
/* 103 */             var18--;
/*     */           }
/* 105 */           else if (var18 < var7) {
/*     */             
/* 107 */             var18++;
/*     */           } 
/*     */         } 
/*     */         
/* 111 */         for (var11 = 0; var11 < var4 - 1; var11++) {
/*     */           
/* 113 */           Block var19 = worldIn.getBlockState(p_180709_3_.offsetUp(var11)).getBlock();
/*     */           
/* 115 */           if (var19.getMaterial() == Material.air || var19.getMaterial() == Material.leaves)
/*     */           {
/* 117 */             func_175905_a(worldIn, p_180709_3_.offsetUp(var11), Blocks.log, BlockPlanks.EnumType.SPRUCE.func_176839_a());
/*     */           }
/*     */         } 
/*     */         
/* 121 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 125 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenTaiga1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */