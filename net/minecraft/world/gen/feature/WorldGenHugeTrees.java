/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class WorldGenHugeTrees
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   protected final int baseHeight;
/*     */   protected final int woodMetadata;
/*     */   protected final int leavesMetadata;
/*     */   protected int field_150538_d;
/*     */   private static final String __OBFID = "CL_00000423";
/*     */   
/*     */   public WorldGenHugeTrees(boolean p_i45458_1_, int p_i45458_2_, int p_i45458_3_, int p_i45458_4_, int p_i45458_5_) {
/*  25 */     super(p_i45458_1_);
/*  26 */     this.baseHeight = p_i45458_2_;
/*  27 */     this.field_150538_d = p_i45458_3_;
/*  28 */     this.woodMetadata = p_i45458_4_;
/*  29 */     this.leavesMetadata = p_i45458_5_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_150533_a(Random p_150533_1_) {
/*  34 */     int var2 = p_150533_1_.nextInt(3) + this.baseHeight;
/*     */     
/*  36 */     if (this.field_150538_d > 1)
/*     */     {
/*  38 */       var2 += p_150533_1_.nextInt(this.field_150538_d);
/*     */     }
/*     */     
/*  41 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_175926_c(World worldIn, BlockPos p_175926_2_, int p_175926_3_) {
/*  46 */     boolean var4 = true;
/*     */     
/*  48 */     if (p_175926_2_.getY() >= 1 && p_175926_2_.getY() + p_175926_3_ + 1 <= 256) {
/*     */       
/*  50 */       for (int var5 = 0; var5 <= 1 + p_175926_3_; var5++) {
/*     */         
/*  52 */         byte var6 = 2;
/*     */         
/*  54 */         if (var5 == 0) {
/*     */           
/*  56 */           var6 = 1;
/*     */         }
/*  58 */         else if (var5 >= 1 + p_175926_3_ - 2) {
/*     */           
/*  60 */           var6 = 2;
/*     */         } 
/*     */         
/*  63 */         for (int var7 = -var6; var7 <= var6 && var4; var7++) {
/*     */           
/*  65 */           for (int var8 = -var6; var8 <= var6 && var4; var8++) {
/*     */             
/*  67 */             if (p_175926_2_.getY() + var5 < 0 || p_175926_2_.getY() + var5 >= 256 || !func_150523_a(worldIn.getBlockState(p_175926_2_.add(var7, var5, var8)).getBlock()))
/*     */             {
/*  69 */               var4 = false;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  75 */       return var4;
/*     */     } 
/*     */ 
/*     */     
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_175927_a(BlockPos p_175927_1_, World worldIn) {
/*  85 */     BlockPos var3 = p_175927_1_.offsetDown();
/*  86 */     Block var4 = worldIn.getBlockState(var3).getBlock();
/*     */     
/*  88 */     if ((var4 == Blocks.grass || var4 == Blocks.dirt) && p_175927_1_.getY() >= 2) {
/*     */       
/*  90 */       func_175921_a(worldIn, var3);
/*  91 */       func_175921_a(worldIn, var3.offsetEast());
/*  92 */       func_175921_a(worldIn, var3.offsetSouth());
/*  93 */       func_175921_a(worldIn, var3.offsetSouth().offsetEast());
/*  94 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  98 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_175929_a(World worldIn, Random p_175929_2_, BlockPos p_175929_3_, int p_175929_4_) {
/* 104 */     return (func_175926_c(worldIn, p_175929_3_, p_175929_4_) && func_175927_a(p_175929_3_, worldIn));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175925_a(World worldIn, BlockPos p_175925_2_, int p_175925_3_) {
/* 109 */     int var4 = p_175925_3_ * p_175925_3_;
/*     */     
/* 111 */     for (int var5 = -p_175925_3_; var5 <= p_175925_3_ + 1; var5++) {
/*     */       
/* 113 */       for (int var6 = -p_175925_3_; var6 <= p_175925_3_ + 1; var6++) {
/*     */         
/* 115 */         int var7 = var5 - 1;
/* 116 */         int var8 = var6 - 1;
/*     */         
/* 118 */         if (var5 * var5 + var6 * var6 <= var4 || var7 * var7 + var8 * var8 <= var4 || var5 * var5 + var8 * var8 <= var4 || var7 * var7 + var6 * var6 <= var4) {
/*     */           
/* 120 */           BlockPos var9 = p_175925_2_.add(var5, 0, var6);
/* 121 */           Material var10 = worldIn.getBlockState(var9).getBlock().getMaterial();
/*     */           
/* 123 */           if (var10 == Material.air || var10 == Material.leaves)
/*     */           {
/* 125 */             func_175905_a(worldIn, var9, (Block)Blocks.leaves, this.leavesMetadata);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175928_b(World worldIn, BlockPos p_175928_2_, int p_175928_3_) {
/* 134 */     int var4 = p_175928_3_ * p_175928_3_;
/*     */     
/* 136 */     for (int var5 = -p_175928_3_; var5 <= p_175928_3_; var5++) {
/*     */       
/* 138 */       for (int var6 = -p_175928_3_; var6 <= p_175928_3_; var6++) {
/*     */         
/* 140 */         if (var5 * var5 + var6 * var6 <= var4) {
/*     */           
/* 142 */           BlockPos var7 = p_175928_2_.add(var5, 0, var6);
/* 143 */           Material var8 = worldIn.getBlockState(var7).getBlock().getMaterial();
/*     */           
/* 145 */           if (var8 == Material.air || var8 == Material.leaves)
/*     */           {
/* 147 */             func_175905_a(worldIn, var7, (Block)Blocks.leaves, this.leavesMetadata);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenHugeTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */