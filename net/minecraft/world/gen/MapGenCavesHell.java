/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ 
/*     */ public class MapGenCavesHell
/*     */   extends MapGenBase
/*     */ {
/*     */   private static final String __OBFID = "CL_00000395";
/*     */   
/*     */   protected void func_180705_a(long p_180705_1_, int p_180705_3_, int p_180705_4_, ChunkPrimer p_180705_5_, double p_180705_6_, double p_180705_8_, double p_180705_10_) {
/*  16 */     func_180704_a(p_180705_1_, p_180705_3_, p_180705_4_, p_180705_5_, p_180705_6_, p_180705_8_, p_180705_10_, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180704_a(long p_180704_1_, int p_180704_3_, int p_180704_4_, ChunkPrimer p_180704_5_, double p_180704_6_, double p_180704_8_, double p_180704_10_, float p_180704_12_, float p_180704_13_, float p_180704_14_, int p_180704_15_, int p_180704_16_, double p_180704_17_) {
/*  21 */     double var19 = (p_180704_3_ * 16 + 8);
/*  22 */     double var21 = (p_180704_4_ * 16 + 8);
/*  23 */     float var23 = 0.0F;
/*  24 */     float var24 = 0.0F;
/*  25 */     Random var25 = new Random(p_180704_1_);
/*     */     
/*  27 */     if (p_180704_16_ <= 0) {
/*     */       
/*  29 */       int var26 = this.range * 16 - 16;
/*  30 */       p_180704_16_ = var26 - var25.nextInt(var26 / 4);
/*     */     } 
/*     */     
/*  33 */     boolean var52 = false;
/*     */     
/*  35 */     if (p_180704_15_ == -1) {
/*     */       
/*  37 */       p_180704_15_ = p_180704_16_ / 2;
/*  38 */       var52 = true;
/*     */     } 
/*     */     
/*  41 */     int var27 = var25.nextInt(p_180704_16_ / 2) + p_180704_16_ / 4;
/*     */     
/*  43 */     for (boolean var28 = (var25.nextInt(6) == 0); p_180704_15_ < p_180704_16_; p_180704_15_++) {
/*     */       
/*  45 */       double var29 = 1.5D + (MathHelper.sin(p_180704_15_ * 3.1415927F / p_180704_16_) * p_180704_12_ * 1.0F);
/*  46 */       double var31 = var29 * p_180704_17_;
/*  47 */       float var33 = MathHelper.cos(p_180704_14_);
/*  48 */       float var34 = MathHelper.sin(p_180704_14_);
/*  49 */       p_180704_6_ += (MathHelper.cos(p_180704_13_) * var33);
/*  50 */       p_180704_8_ += var34;
/*  51 */       p_180704_10_ += (MathHelper.sin(p_180704_13_) * var33);
/*     */       
/*  53 */       if (var28) {
/*     */         
/*  55 */         p_180704_14_ *= 0.92F;
/*     */       }
/*     */       else {
/*     */         
/*  59 */         p_180704_14_ *= 0.7F;
/*     */       } 
/*     */       
/*  62 */       p_180704_14_ += var24 * 0.1F;
/*  63 */       p_180704_13_ += var23 * 0.1F;
/*  64 */       var24 *= 0.9F;
/*  65 */       var23 *= 0.75F;
/*  66 */       var24 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 2.0F;
/*  67 */       var23 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 4.0F;
/*     */       
/*  69 */       if (!var52 && p_180704_15_ == var27 && p_180704_12_ > 1.0F) {
/*     */         
/*  71 */         func_180704_a(var25.nextLong(), p_180704_3_, p_180704_4_, p_180704_5_, p_180704_6_, p_180704_8_, p_180704_10_, var25.nextFloat() * 0.5F + 0.5F, p_180704_13_ - 1.5707964F, p_180704_14_ / 3.0F, p_180704_15_, p_180704_16_, 1.0D);
/*  72 */         func_180704_a(var25.nextLong(), p_180704_3_, p_180704_4_, p_180704_5_, p_180704_6_, p_180704_8_, p_180704_10_, var25.nextFloat() * 0.5F + 0.5F, p_180704_13_ + 1.5707964F, p_180704_14_ / 3.0F, p_180704_15_, p_180704_16_, 1.0D);
/*     */         
/*     */         return;
/*     */       } 
/*  76 */       if (var52 || var25.nextInt(4) != 0) {
/*     */         
/*  78 */         double var35 = p_180704_6_ - var19;
/*  79 */         double var37 = p_180704_10_ - var21;
/*  80 */         double var39 = (p_180704_16_ - p_180704_15_);
/*  81 */         double var41 = (p_180704_12_ + 2.0F + 16.0F);
/*     */         
/*  83 */         if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  88 */         if (p_180704_6_ >= var19 - 16.0D - var29 * 2.0D && p_180704_10_ >= var21 - 16.0D - var29 * 2.0D && p_180704_6_ <= var19 + 16.0D + var29 * 2.0D && p_180704_10_ <= var21 + 16.0D + var29 * 2.0D) {
/*     */           
/*  90 */           int var53 = MathHelper.floor_double(p_180704_6_ - var29) - p_180704_3_ * 16 - 1;
/*  91 */           int var36 = MathHelper.floor_double(p_180704_6_ + var29) - p_180704_3_ * 16 + 1;
/*  92 */           int var54 = MathHelper.floor_double(p_180704_8_ - var31) - 1;
/*  93 */           int var38 = MathHelper.floor_double(p_180704_8_ + var31) + 1;
/*  94 */           int var55 = MathHelper.floor_double(p_180704_10_ - var29) - p_180704_4_ * 16 - 1;
/*  95 */           int var40 = MathHelper.floor_double(p_180704_10_ + var29) - p_180704_4_ * 16 + 1;
/*     */           
/*  97 */           if (var53 < 0)
/*     */           {
/*  99 */             var53 = 0;
/*     */           }
/*     */           
/* 102 */           if (var36 > 16)
/*     */           {
/* 104 */             var36 = 16;
/*     */           }
/*     */           
/* 107 */           if (var54 < 1)
/*     */           {
/* 109 */             var54 = 1;
/*     */           }
/*     */           
/* 112 */           if (var38 > 120)
/*     */           {
/* 114 */             var38 = 120;
/*     */           }
/*     */           
/* 117 */           if (var55 < 0)
/*     */           {
/* 119 */             var55 = 0;
/*     */           }
/*     */           
/* 122 */           if (var40 > 16)
/*     */           {
/* 124 */             var40 = 16;
/*     */           }
/*     */           
/* 127 */           boolean var56 = false;
/*     */           
/*     */           int var42;
/* 130 */           for (var42 = var53; !var56 && var42 < var36; var42++) {
/*     */             
/* 132 */             for (int var43 = var55; !var56 && var43 < var40; var43++) {
/*     */               
/* 134 */               for (int var44 = var38 + 1; !var56 && var44 >= var54 - 1; var44--) {
/*     */                 
/* 136 */                 if (var44 >= 0 && var44 < 128) {
/*     */                   
/* 138 */                   IBlockState var45 = p_180704_5_.getBlockState(var42, var44, var43);
/*     */                   
/* 140 */                   if (var45.getBlock() == Blocks.flowing_lava || var45.getBlock() == Blocks.lava)
/*     */                   {
/* 142 */                     var56 = true;
/*     */                   }
/*     */                   
/* 145 */                   if (var44 != var54 - 1 && var42 != var53 && var42 != var36 - 1 && var43 != var55 && var43 != var40 - 1)
/*     */                   {
/* 147 */                     var44 = var54;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 154 */           if (!var56) {
/*     */             
/* 156 */             for (var42 = var53; var42 < var36; var42++) {
/*     */               
/* 158 */               double var57 = ((var42 + p_180704_3_ * 16) + 0.5D - p_180704_6_) / var29;
/*     */               
/* 160 */               for (int var58 = var55; var58 < var40; var58++) {
/*     */                 
/* 162 */                 double var46 = ((var58 + p_180704_4_ * 16) + 0.5D - p_180704_10_) / var29;
/*     */                 
/* 164 */                 for (int var48 = var38; var48 > var54; var48--) {
/*     */                   
/* 166 */                   double var49 = ((var48 - 1) + 0.5D - p_180704_8_) / var31;
/*     */                   
/* 168 */                   if (var49 > -0.7D && var57 * var57 + var49 * var49 + var46 * var46 < 1.0D) {
/*     */                     
/* 170 */                     IBlockState var51 = p_180704_5_.getBlockState(var42, var48, var58);
/*     */                     
/* 172 */                     if (var51.getBlock() == Blocks.netherrack || var51.getBlock() == Blocks.dirt || var51.getBlock() == Blocks.grass)
/*     */                     {
/* 174 */                       p_180704_5_.setBlockState(var42, var48, var58, Blocks.air.getDefaultState());
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */             
/* 181 */             if (var52) {
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
/* 193 */     int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(10) + 1) + 1);
/*     */     
/* 195 */     if (this.rand.nextInt(5) != 0)
/*     */     {
/* 197 */       var7 = 0;
/*     */     }
/*     */     
/* 200 */     for (int var8 = 0; var8 < var7; var8++) {
/*     */       
/* 202 */       double var9 = (p_180701_2_ * 16 + this.rand.nextInt(16));
/* 203 */       double var11 = this.rand.nextInt(128);
/* 204 */       double var13 = (p_180701_3_ * 16 + this.rand.nextInt(16));
/* 205 */       int var15 = 1;
/*     */       
/* 207 */       if (this.rand.nextInt(4) == 0) {
/*     */         
/* 209 */         func_180705_a(this.rand.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, var9, var11, var13);
/* 210 */         var15 += this.rand.nextInt(4);
/*     */       } 
/*     */       
/* 213 */       for (int var16 = 0; var16 < var15; var16++) {
/*     */         
/* 215 */         float var17 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 216 */         float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 217 */         float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
/* 218 */         func_180704_a(this.rand.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, var9, var11, var13, var19 * 2.0F, var17, var18, 0, 0, 0.5D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\MapGenCavesHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */