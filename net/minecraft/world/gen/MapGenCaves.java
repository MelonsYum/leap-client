/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockSand;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ 
/*     */ public class MapGenCaves
/*     */   extends MapGenBase {
/*     */   private static final String __OBFID = "CL_00000393";
/*     */   
/*     */   protected void func_180703_a(long p_180703_1_, int p_180703_3_, int p_180703_4_, ChunkPrimer p_180703_5_, double p_180703_6_, double p_180703_8_, double p_180703_10_) {
/*  20 */     func_180702_a(p_180703_1_, p_180703_3_, p_180703_4_, p_180703_5_, p_180703_6_, p_180703_8_, p_180703_10_, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180702_a(long p_180702_1_, int p_180702_3_, int p_180702_4_, ChunkPrimer p_180702_5_, double p_180702_6_, double p_180702_8_, double p_180702_10_, float p_180702_12_, float p_180702_13_, float p_180702_14_, int p_180702_15_, int p_180702_16_, double p_180702_17_) {
/*  25 */     double var19 = (p_180702_3_ * 16 + 8);
/*  26 */     double var21 = (p_180702_4_ * 16 + 8);
/*  27 */     float var23 = 0.0F;
/*  28 */     float var24 = 0.0F;
/*  29 */     Random var25 = new Random(p_180702_1_);
/*     */     
/*  31 */     if (p_180702_16_ <= 0) {
/*     */       
/*  33 */       int var26 = this.range * 16 - 16;
/*  34 */       p_180702_16_ = var26 - var25.nextInt(var26 / 4);
/*     */     } 
/*     */     
/*  37 */     boolean var54 = false;
/*     */     
/*  39 */     if (p_180702_15_ == -1) {
/*     */       
/*  41 */       p_180702_15_ = p_180702_16_ / 2;
/*  42 */       var54 = true;
/*     */     } 
/*     */     
/*  45 */     int var27 = var25.nextInt(p_180702_16_ / 2) + p_180702_16_ / 4;
/*     */     
/*  47 */     for (boolean var28 = (var25.nextInt(6) == 0); p_180702_15_ < p_180702_16_; p_180702_15_++) {
/*     */       
/*  49 */       double var29 = 1.5D + (MathHelper.sin(p_180702_15_ * 3.1415927F / p_180702_16_) * p_180702_12_ * 1.0F);
/*  50 */       double var31 = var29 * p_180702_17_;
/*  51 */       float var33 = MathHelper.cos(p_180702_14_);
/*  52 */       float var34 = MathHelper.sin(p_180702_14_);
/*  53 */       p_180702_6_ += (MathHelper.cos(p_180702_13_) * var33);
/*  54 */       p_180702_8_ += var34;
/*  55 */       p_180702_10_ += (MathHelper.sin(p_180702_13_) * var33);
/*     */       
/*  57 */       if (var28) {
/*     */         
/*  59 */         p_180702_14_ *= 0.92F;
/*     */       }
/*     */       else {
/*     */         
/*  63 */         p_180702_14_ *= 0.7F;
/*     */       } 
/*     */       
/*  66 */       p_180702_14_ += var24 * 0.1F;
/*  67 */       p_180702_13_ += var23 * 0.1F;
/*  68 */       var24 *= 0.9F;
/*  69 */       var23 *= 0.75F;
/*  70 */       var24 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 2.0F;
/*  71 */       var23 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 4.0F;
/*     */       
/*  73 */       if (!var54 && p_180702_15_ == var27 && p_180702_12_ > 1.0F && p_180702_16_ > 0) {
/*     */         
/*  75 */         func_180702_a(var25.nextLong(), p_180702_3_, p_180702_4_, p_180702_5_, p_180702_6_, p_180702_8_, p_180702_10_, var25.nextFloat() * 0.5F + 0.5F, p_180702_13_ - 1.5707964F, p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
/*  76 */         func_180702_a(var25.nextLong(), p_180702_3_, p_180702_4_, p_180702_5_, p_180702_6_, p_180702_8_, p_180702_10_, var25.nextFloat() * 0.5F + 0.5F, p_180702_13_ + 1.5707964F, p_180702_14_ / 3.0F, p_180702_15_, p_180702_16_, 1.0D);
/*     */         
/*     */         return;
/*     */       } 
/*  80 */       if (var54 || var25.nextInt(4) != 0) {
/*     */         
/*  82 */         double var35 = p_180702_6_ - var19;
/*  83 */         double var37 = p_180702_10_ - var21;
/*  84 */         double var39 = (p_180702_16_ - p_180702_15_);
/*  85 */         double var41 = (p_180702_12_ + 2.0F + 16.0F);
/*     */         
/*  87 */         if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  92 */         if (p_180702_6_ >= var19 - 16.0D - var29 * 2.0D && p_180702_10_ >= var21 - 16.0D - var29 * 2.0D && p_180702_6_ <= var19 + 16.0D + var29 * 2.0D && p_180702_10_ <= var21 + 16.0D + var29 * 2.0D) {
/*     */           
/*  94 */           int var55 = MathHelper.floor_double(p_180702_6_ - var29) - p_180702_3_ * 16 - 1;
/*  95 */           int var36 = MathHelper.floor_double(p_180702_6_ + var29) - p_180702_3_ * 16 + 1;
/*  96 */           int var56 = MathHelper.floor_double(p_180702_8_ - var31) - 1;
/*  97 */           int var38 = MathHelper.floor_double(p_180702_8_ + var31) + 1;
/*  98 */           int var57 = MathHelper.floor_double(p_180702_10_ - var29) - p_180702_4_ * 16 - 1;
/*  99 */           int var40 = MathHelper.floor_double(p_180702_10_ + var29) - p_180702_4_ * 16 + 1;
/*     */           
/* 101 */           if (var55 < 0)
/*     */           {
/* 103 */             var55 = 0;
/*     */           }
/*     */           
/* 106 */           if (var36 > 16)
/*     */           {
/* 108 */             var36 = 16;
/*     */           }
/*     */           
/* 111 */           if (var56 < 1)
/*     */           {
/* 113 */             var56 = 1;
/*     */           }
/*     */           
/* 116 */           if (var38 > 248)
/*     */           {
/* 118 */             var38 = 248;
/*     */           }
/*     */           
/* 121 */           if (var57 < 0)
/*     */           {
/* 123 */             var57 = 0;
/*     */           }
/*     */           
/* 126 */           if (var40 > 16)
/*     */           {
/* 128 */             var40 = 16;
/*     */           }
/*     */           
/* 131 */           boolean var58 = false;
/*     */           
/*     */           int var42;
/* 134 */           for (var42 = var55; !var58 && var42 < var36; var42++) {
/*     */             
/* 136 */             for (int var43 = var57; !var58 && var43 < var40; var43++) {
/*     */               
/* 138 */               for (int var44 = var38 + 1; !var58 && var44 >= var56 - 1; var44--) {
/*     */                 
/* 140 */                 if (var44 >= 0 && var44 < 256) {
/*     */                   
/* 142 */                   IBlockState var45 = p_180702_5_.getBlockState(var42, var44, var43);
/*     */                   
/* 144 */                   if (var45.getBlock() == Blocks.flowing_water || var45.getBlock() == Blocks.water)
/*     */                   {
/* 146 */                     var58 = true;
/*     */                   }
/*     */                   
/* 149 */                   if (var44 != var56 - 1 && var42 != var55 && var42 != var36 - 1 && var43 != var57 && var43 != var40 - 1)
/*     */                   {
/* 151 */                     var44 = var56;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 158 */           if (!var58) {
/*     */             
/* 160 */             for (var42 = var55; var42 < var36; var42++) {
/*     */               
/* 162 */               double var59 = ((var42 + p_180702_3_ * 16) + 0.5D - p_180702_6_) / var29;
/*     */               
/* 164 */               for (int var60 = var57; var60 < var40; var60++) {
/*     */                 
/* 166 */                 double var46 = ((var60 + p_180702_4_ * 16) + 0.5D - p_180702_10_) / var29;
/* 167 */                 boolean var48 = false;
/*     */                 
/* 169 */                 if (var59 * var59 + var46 * var46 < 1.0D)
/*     */                 {
/* 171 */                   for (int var49 = var38; var49 > var56; var49--) {
/*     */                     
/* 173 */                     double var50 = ((var49 - 1) + 0.5D - p_180702_8_) / var31;
/*     */                     
/* 175 */                     if (var50 > -0.7D && var59 * var59 + var50 * var50 + var46 * var46 < 1.0D) {
/*     */                       
/* 177 */                       IBlockState var52 = p_180702_5_.getBlockState(var42, var49, var60);
/* 178 */                       IBlockState var53 = (IBlockState)Objects.firstNonNull(p_180702_5_.getBlockState(var42, var49 + 1, var60), Blocks.air.getDefaultState());
/*     */                       
/* 180 */                       if (var52.getBlock() == Blocks.grass || var52.getBlock() == Blocks.mycelium)
/*     */                       {
/* 182 */                         var48 = true;
/*     */                       }
/*     */                       
/* 185 */                       if (func_175793_a(var52, var53))
/*     */                       {
/* 187 */                         if (var49 - 1 < 10) {
/*     */                           
/* 189 */                           p_180702_5_.setBlockState(var42, var49, var60, Blocks.lava.getDefaultState());
/*     */                         }
/*     */                         else {
/*     */                           
/* 193 */                           p_180702_5_.setBlockState(var42, var49, var60, Blocks.air.getDefaultState());
/*     */                           
/* 195 */                           if (var53.getBlock() == Blocks.sand)
/*     */                           {
/* 197 */                             p_180702_5_.setBlockState(var42, var49 + 1, var60, (var53.getValue((IProperty)BlockSand.VARIANT_PROP) == BlockSand.EnumType.RED_SAND) ? Blocks.red_sandstone.getDefaultState() : Blocks.sandstone.getDefaultState());
/*     */                           }
/*     */                           
/* 200 */                           if (var48 && p_180702_5_.getBlockState(var42, var49 - 1, var60).getBlock() == Blocks.dirt)
/*     */                           {
/* 202 */                             p_180702_5_.setBlockState(var42, var49 - 1, var60, (this.worldObj.getBiomeGenForCoords(new BlockPos(var42 + p_180702_3_ * 16, 0, var60 + p_180702_4_ * 16))).topBlock.getBlock().getDefaultState());
/*     */                           }
/*     */                         } 
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 212 */             if (var54) {
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
/*     */   protected boolean func_175793_a(IBlockState p_175793_1_, IBlockState p_175793_2_) {
/* 224 */     return (p_175793_1_.getBlock() == Blocks.stone) ? true : ((p_175793_1_.getBlock() == Blocks.dirt) ? true : ((p_175793_1_.getBlock() == Blocks.grass) ? true : ((p_175793_1_.getBlock() == Blocks.hardened_clay) ? true : ((p_175793_1_.getBlock() == Blocks.stained_hardened_clay) ? true : ((p_175793_1_.getBlock() == Blocks.sandstone) ? true : ((p_175793_1_.getBlock() == Blocks.red_sandstone) ? true : ((p_175793_1_.getBlock() == Blocks.mycelium) ? true : ((p_175793_1_.getBlock() == Blocks.snow_layer) ? true : (((p_175793_1_.getBlock() == Blocks.sand || p_175793_1_.getBlock() == Blocks.gravel) && p_175793_2_.getBlock().getMaterial() != Material.water))))))))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180701_a(World worldIn, int p_180701_2_, int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_) {
/* 229 */     int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(15) + 1) + 1);
/*     */     
/* 231 */     if (this.rand.nextInt(7) != 0)
/*     */     {
/* 233 */       var7 = 0;
/*     */     }
/*     */     
/* 236 */     for (int var8 = 0; var8 < var7; var8++) {
/*     */       
/* 238 */       double var9 = (p_180701_2_ * 16 + this.rand.nextInt(16));
/* 239 */       double var11 = this.rand.nextInt(this.rand.nextInt(120) + 8);
/* 240 */       double var13 = (p_180701_3_ * 16 + this.rand.nextInt(16));
/* 241 */       int var15 = 1;
/*     */       
/* 243 */       if (this.rand.nextInt(4) == 0) {
/*     */         
/* 245 */         func_180703_a(this.rand.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, var9, var11, var13);
/* 246 */         var15 += this.rand.nextInt(4);
/*     */       } 
/*     */       
/* 249 */       for (int var16 = 0; var16 < var15; var16++) {
/*     */         
/* 251 */         float var17 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 252 */         float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 253 */         float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
/*     */         
/* 255 */         if (this.rand.nextInt(10) == 0)
/*     */         {
/* 257 */           var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
/*     */         }
/*     */         
/* 260 */         func_180702_a(this.rand.nextLong(), p_180701_4_, p_180701_5_, p_180701_6_, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\MapGenCaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */