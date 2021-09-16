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
/*     */ public class WorldGenForest
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private boolean field_150531_a;
/*     */   private static final String __OBFID = "CL_00000401";
/*     */   
/*     */   public WorldGenForest(boolean p_i45449_1_, boolean p_i45449_2_) {
/*  18 */     super(p_i45449_1_);
/*  19 */     this.field_150531_a = p_i45449_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  24 */     int var4 = p_180709_2_.nextInt(3) + 5;
/*     */     
/*  26 */     if (this.field_150531_a)
/*     */     {
/*  28 */       var4 += p_180709_2_.nextInt(7);
/*     */     }
/*     */     
/*  31 */     boolean var5 = true;
/*     */     
/*  33 */     if (p_180709_3_.getY() >= 1 && p_180709_3_.getY() + var4 + 1 <= 256) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  38 */       for (int var6 = p_180709_3_.getY(); var6 <= p_180709_3_.getY() + 1 + var4; var6++) {
/*     */         
/*  40 */         byte var7 = 1;
/*     */         
/*  42 */         if (var6 == p_180709_3_.getY())
/*     */         {
/*  44 */           var7 = 0;
/*     */         }
/*     */         
/*  47 */         if (var6 >= p_180709_3_.getY() + 1 + var4 - 2)
/*     */         {
/*  49 */           var7 = 2;
/*     */         }
/*     */         
/*  52 */         for (int var8 = p_180709_3_.getX() - var7; var8 <= p_180709_3_.getX() + var7 && var5; var8++) {
/*     */           
/*  54 */           for (int var9 = p_180709_3_.getZ() - var7; var9 <= p_180709_3_.getZ() + var7 && var5; var9++) {
/*     */             
/*  56 */             if (var6 >= 0 && var6 < 256) {
/*     */               
/*  58 */               if (!func_150523_a(worldIn.getBlockState(new BlockPos(var8, var6, var9)).getBlock()))
/*     */               {
/*  60 */                 var5 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  65 */               var5 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  71 */       if (!var5)
/*     */       {
/*  73 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  77 */       Block var16 = worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock();
/*     */       
/*  79 */       if ((var16 == Blocks.grass || var16 == Blocks.dirt || var16 == Blocks.farmland) && p_180709_3_.getY() < 256 - var4 - 1) {
/*     */         
/*  81 */         func_175921_a(worldIn, p_180709_3_.offsetDown());
/*     */         
/*     */         int var17;
/*  84 */         for (var17 = p_180709_3_.getY() - 3 + var4; var17 <= p_180709_3_.getY() + var4; var17++) {
/*     */           
/*  86 */           int var8 = var17 - p_180709_3_.getY() + var4;
/*  87 */           int var9 = 1 - var8 / 2;
/*     */           
/*  89 */           for (int var10 = p_180709_3_.getX() - var9; var10 <= p_180709_3_.getX() + var9; var10++) {
/*     */             
/*  91 */             int var11 = var10 - p_180709_3_.getX();
/*     */             
/*  93 */             for (int var12 = p_180709_3_.getZ() - var9; var12 <= p_180709_3_.getZ() + var9; var12++) {
/*     */               
/*  95 */               int var13 = var12 - p_180709_3_.getZ();
/*     */               
/*  97 */               if (Math.abs(var11) != var9 || Math.abs(var13) != var9 || (p_180709_2_.nextInt(2) != 0 && var8 != 0)) {
/*     */                 
/*  99 */                 BlockPos var14 = new BlockPos(var10, var17, var12);
/* 100 */                 Block var15 = worldIn.getBlockState(var14).getBlock();
/*     */                 
/* 102 */                 if (var15.getMaterial() == Material.air || var15.getMaterial() == Material.leaves)
/*     */                 {
/* 104 */                   func_175905_a(worldIn, var14, (Block)Blocks.leaves, BlockPlanks.EnumType.BIRCH.func_176839_a());
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 111 */         for (var17 = 0; var17 < var4; var17++) {
/*     */           
/* 113 */           Block var18 = worldIn.getBlockState(p_180709_3_.offsetUp(var17)).getBlock();
/*     */           
/* 115 */           if (var18.getMaterial() == Material.air || var18.getMaterial() == Material.leaves)
/*     */           {
/* 117 */             func_175905_a(worldIn, p_180709_3_.offsetUp(var17), Blocks.log, BlockPlanks.EnumType.BIRCH.func_176839_a());
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


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenForest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */