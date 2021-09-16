/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ 
/*     */ public class MapGenRavine
/*     */   extends MapGenBase {
/*  13 */   private float[] field_75046_d = new float[1024];
/*     */   
/*     */   private static final String __OBFID = "CL_00000390";
/*     */   
/*     */   protected void func_180707_a(long p_180707_1_, int p_180707_3_, int p_180707_4_, ChunkPrimer p_180707_5_, double p_180707_6_, double p_180707_8_, double p_180707_10_, float p_180707_12_, float p_180707_13_, float p_180707_14_, int p_180707_15_, int p_180707_16_, double p_180707_17_) {
/*  18 */     Random var19 = new Random(p_180707_1_);
/*  19 */     double var20 = (p_180707_3_ * 16 + 8);
/*  20 */     double var22 = (p_180707_4_ * 16 + 8);
/*  21 */     float var24 = 0.0F;
/*  22 */     float var25 = 0.0F;
/*     */     
/*  24 */     if (p_180707_16_ <= 0) {
/*     */       
/*  26 */       int var26 = this.range * 16 - 16;
/*  27 */       p_180707_16_ = var26 - var19.nextInt(var26 / 4);
/*     */     } 
/*     */     
/*  30 */     boolean var52 = false;
/*     */     
/*  32 */     if (p_180707_15_ == -1) {
/*     */       
/*  34 */       p_180707_15_ = p_180707_16_ / 2;
/*  35 */       var52 = true;
/*     */     } 
/*     */     
/*  38 */     float var27 = 1.0F;
/*     */     
/*  40 */     for (int var28 = 0; var28 < 256; var28++) {
/*     */       
/*  42 */       if (var28 == 0 || var19.nextInt(3) == 0)
/*     */       {
/*  44 */         var27 = 1.0F + var19.nextFloat() * var19.nextFloat() * 1.0F;
/*     */       }
/*     */       
/*  47 */       this.field_75046_d[var28] = var27 * var27;
/*     */     } 
/*     */     
/*  50 */     for (; p_180707_15_ < p_180707_16_; p_180707_15_++) {
/*     */       
/*  52 */       double var53 = 1.5D + (MathHelper.sin(p_180707_15_ * 3.1415927F / p_180707_16_) * p_180707_12_ * 1.0F);
/*  53 */       double var30 = var53 * p_180707_17_;
/*  54 */       var53 *= var19.nextFloat() * 0.25D + 0.75D;
/*  55 */       var30 *= var19.nextFloat() * 0.25D + 0.75D;
/*  56 */       float var32 = MathHelper.cos(p_180707_14_);
/*  57 */       float var33 = MathHelper.sin(p_180707_14_);
/*  58 */       p_180707_6_ += (MathHelper.cos(p_180707_13_) * var32);
/*  59 */       p_180707_8_ += var33;
/*  60 */       p_180707_10_ += (MathHelper.sin(p_180707_13_) * var32);
/*  61 */       p_180707_14_ *= 0.7F;
/*  62 */       p_180707_14_ += var25 * 0.05F;
/*  63 */       p_180707_13_ += var24 * 0.05F;
/*  64 */       var25 *= 0.8F;
/*  65 */       var24 *= 0.5F;
/*  66 */       var25 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 2.0F;
/*  67 */       var24 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 4.0F;
/*     */       
/*  69 */       if (var52 || var19.nextInt(4) != 0) {
/*     */         
/*  71 */         double var34 = p_180707_6_ - var20;
/*  72 */         double var36 = p_180707_10_ - var22;
/*  73 */         double var38 = (p_180707_16_ - p_180707_15_);
/*  74 */         double var40 = (p_180707_12_ + 2.0F + 16.0F);
/*     */         
/*  76 */         if (var34 * var34 + var36 * var36 - var38 * var38 > var40 * var40) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  81 */         if (p_180707_6_ >= var20 - 16.0D - var53 * 2.0D && p_180707_10_ >= var22 - 16.0D - var53 * 2.0D && p_180707_6_ <= var20 + 16.0D + var53 * 2.0D && p_180707_10_ <= var22 + 16.0D + var53 * 2.0D) {
/*     */           
/*  83 */           int var54 = MathHelper.floor_double(p_180707_6_ - var53) - p_180707_3_ * 16 - 1;
/*  84 */           int var35 = MathHelper.floor_double(p_180707_6_ + var53) - p_180707_3_ * 16 + 1;
/*  85 */           int var55 = MathHelper.floor_double(p_180707_8_ - var30) - 1;
/*  86 */           int var37 = MathHelper.floor_double(p_180707_8_ + var30) + 1;
/*  87 */           int var56 = MathHelper.floor_double(p_180707_10_ - var53) - p_180707_4_ * 16 - 1;
/*  88 */           int var39 = MathHelper.floor_double(p_180707_10_ + var53) - p_180707_4_ * 16 + 1;
/*     */           
/*  90 */           if (var54 < 0)
/*     */           {
/*  92 */             var54 = 0;
/*     */           }
/*     */           
/*  95 */           if (var35 > 16)
/*     */           {
/*  97 */             var35 = 16;
/*     */           }
/*     */           
/* 100 */           if (var55 < 1)
/*     */           {
/* 102 */             var55 = 1;
/*     */           }
/*     */           
/* 105 */           if (var37 > 248)
/*     */           {
/* 107 */             var37 = 248;
/*     */           }
/*     */           
/* 110 */           if (var56 < 0)
/*     */           {
/* 112 */             var56 = 0;
/*     */           }
/*     */           
/* 115 */           if (var39 > 16)
/*     */           {
/* 117 */             var39 = 16;
/*     */           }
/*     */           
/* 120 */           boolean var57 = false;
/*     */           
/*     */           int var41;
/* 123 */           for (var41 = var54; !var57 && var41 < var35; var41++) {
/*     */             
/* 125 */             for (int var42 = var56; !var57 && var42 < var39; var42++) {
/*     */               
/* 127 */               for (int var43 = var37 + 1; !var57 && var43 >= var55 - 1; var43--) {
/*     */                 
/* 129 */                 if (var43 >= 0 && var43 < 256) {
/*     */                   
/* 131 */                   IBlockState var44 = p_180707_5_.getBlockState(var41, var43, var42);
/*     */                   
/* 133 */                   if (var44.getBlock() == Blocks.flowing_water || var44.getBlock() == Blocks.water)
/*     */                   {
/* 135 */                     var57 = true;
/*     */                   }
/*     */                   
/* 138 */                   if (var43 != var55 - 1 && var41 != var54 && var41 != var35 - 1 && var42 != var56 && var42 != var39 - 1)
/*     */                   {
/* 140 */                     var43 = var55;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 147 */           if (!var57) {
/*     */             
/* 149 */             for (var41 = var54; var41 < var35; var41++) {
/*     */               
/* 151 */               double var58 = ((var41 + p_180707_3_ * 16) + 0.5D - p_180707_6_) / var53;
/*     */               
/* 153 */               for (int var59 = var56; var59 < var39; var59++) {
/*     */                 
/* 155 */                 double var45 = ((var59 + p_180707_4_ * 16) + 0.5D - p_180707_10_) / var53;
/* 156 */                 boolean var47 = false;
/*     */                 
/* 158 */                 if (var58 * var58 + var45 * var45 < 1.0D)
/*     */                 {
/* 160 */                   for (int var48 = var37; var48 > var55; var48--) {
/*     */                     
/* 162 */                     double var49 = ((var48 - 1) + 0.5D - p_180707_8_) / var30;
/*     */                     
/* 164 */                     if ((var58 * var58 + var45 * var45) * this.field_75046_d[var48 - 1] + var49 * var49 / 6.0D < 1.0D) {
/*     */                       
/* 166 */                       IBlockState var51 = p_180707_5_.getBlockState(var41, var48, var59);
/*     */                       
/* 168 */                       if (var51.getBlock() == Blocks.grass)
/*     */                       {
/* 170 */                         var47 = true;
/*     */                       }
/*     */                       
/* 173 */                       if (var51.getBlock() == Blocks.stone || var51.getBlock() == Blocks.dirt || var51.getBlock() == Blocks.grass)
/*     */                       {
/* 175 */                         if (var48 - 1 < 10) {
/*     */                           
/* 177 */                           p_180707_5_.setBlockState(var41, var48, var59, Blocks.flowing_lava.getDefaultState());
/*     */                         }
/*     */                         else {
/*     */                           
/* 181 */                           p_180707_5_.setBlockState(var41, var48, var59, Blocks.air.getDefaultState());
/*     */                           
/* 183 */                           if (var47 && p_180707_5_.getBlockState(var41, var48 - 1, var59).getBlock() == Blocks.dirt)
/*     */                           {
/* 185 */                             p_180707_5_.setBlockState(var41, var48 - 1, var59, (this.worldObj.getBiomeGenForCoords(new BlockPos(var41 + p_180707_3_ * 16, 0, var59 + p_180707_4_ * 16))).topBlock);
/*     */                           }
/*     */                         } 
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 195 */             if (var52) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_180701_a(World worldIn, int p_180701_2_, int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_) {
/* 207 */     if (this.rand.nextInt(50) == 0) {
/*     */       
/* 209 */       double var7 = (p_180701_2_ * 16 + this.rand.nextInt(16));
/* 210 */       double var9 = (this.rand.nextInt(this.rand.nextInt(40) + 8) + 20);
/* 211 */       double var11 = (p_180701_3_ * 16 + this.rand.nextInt(16));
/* 212 */       byte var13 = 1;
/*     */       
/* 214 */       for (int var14 = 0; var14 < var13; var14++) {
/*     */         
/* 216 */         float var15 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 217 */         float var16 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 218 */         float var17 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
/* 219 */         func_180707_a(this.rand.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, var7, var9, var11, var17, var15, var16, 0, 0, 3.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\MapGenRavine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */