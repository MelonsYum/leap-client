/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockLog;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenBigTree
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private Random field_175949_k;
/*     */   private World field_175946_l;
/*     */   private BlockPos field_175947_m;
/*     */   int heightLimit;
/*     */   int height;
/*     */   double heightAttenuation;
/*     */   double field_175944_d;
/*     */   double field_175945_e;
/*     */   double leafDensity;
/*     */   int field_175943_g;
/*     */   int field_175950_h;
/*     */   int leafDistanceLimit;
/*     */   List field_175948_j;
/*     */   private static final String __OBFID = "CL_00000400";
/*     */   
/*     */   public WorldGenBigTree(boolean p_i2008_1_) {
/*  38 */     super(p_i2008_1_);
/*  39 */     this.field_175947_m = BlockPos.ORIGIN;
/*  40 */     this.heightAttenuation = 0.618D;
/*  41 */     this.field_175944_d = 0.381D;
/*  42 */     this.field_175945_e = 1.0D;
/*  43 */     this.leafDensity = 1.0D;
/*  44 */     this.field_175943_g = 1;
/*  45 */     this.field_175950_h = 12;
/*  46 */     this.leafDistanceLimit = 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNodeList() {
/*  54 */     this.height = (int)(this.heightLimit * this.heightAttenuation);
/*     */     
/*  56 */     if (this.height >= this.heightLimit)
/*     */     {
/*  58 */       this.height = this.heightLimit - 1;
/*     */     }
/*     */     
/*  61 */     int var1 = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
/*     */     
/*  63 */     if (var1 < 1)
/*     */     {
/*  65 */       var1 = 1;
/*     */     }
/*     */     
/*  68 */     int var2 = this.field_175947_m.getY() + this.height;
/*  69 */     int var3 = this.heightLimit - this.leafDistanceLimit;
/*  70 */     this.field_175948_j = Lists.newArrayList();
/*  71 */     this.field_175948_j.add(new FoliageCoordinates(this.field_175947_m.offsetUp(var3), var2));
/*     */     
/*  73 */     for (; var3 >= 0; var3--) {
/*     */       
/*  75 */       float var4 = layerSize(var3);
/*     */       
/*  77 */       if (var4 >= 0.0F)
/*     */       {
/*  79 */         for (int var5 = 0; var5 < var1; var5++) {
/*     */           
/*  81 */           double var6 = this.field_175945_e * var4 * (this.field_175949_k.nextFloat() + 0.328D);
/*  82 */           double var8 = (this.field_175949_k.nextFloat() * 2.0F) * Math.PI;
/*  83 */           double var10 = var6 * Math.sin(var8) + 0.5D;
/*  84 */           double var12 = var6 * Math.cos(var8) + 0.5D;
/*  85 */           BlockPos var14 = this.field_175947_m.add(var10, (var3 - 1), var12);
/*  86 */           BlockPos var15 = var14.offsetUp(this.leafDistanceLimit);
/*     */           
/*  88 */           if (func_175936_a(var14, var15) == -1) {
/*     */             
/*  90 */             int var16 = this.field_175947_m.getX() - var14.getX();
/*  91 */             int var17 = this.field_175947_m.getZ() - var14.getZ();
/*  92 */             double var18 = var14.getY() - Math.sqrt((var16 * var16 + var17 * var17)) * this.field_175944_d;
/*  93 */             int var20 = (var18 > var2) ? var2 : (int)var18;
/*  94 */             BlockPos var21 = new BlockPos(this.field_175947_m.getX(), var20, this.field_175947_m.getZ());
/*     */             
/*  96 */             if (func_175936_a(var21, var14) == -1)
/*     */             {
/*  98 */               this.field_175948_j.add(new FoliageCoordinates(var14, var21.getY()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void func_180712_a(BlockPos p_180712_1_, float p_180712_2_, Block p_180712_3_) {
/* 108 */     int var4 = (int)(p_180712_2_ + 0.618D);
/*     */     
/* 110 */     for (int var5 = -var4; var5 <= var4; var5++) {
/*     */       
/* 112 */       for (int var6 = -var4; var6 <= var4; var6++) {
/*     */         
/* 114 */         if (Math.pow(Math.abs(var5) + 0.5D, 2.0D) + Math.pow(Math.abs(var6) + 0.5D, 2.0D) <= (p_180712_2_ * p_180712_2_)) {
/*     */           
/* 116 */           BlockPos var7 = p_180712_1_.add(var5, 0, var6);
/* 117 */           Material var8 = this.field_175946_l.getBlockState(var7).getBlock().getMaterial();
/*     */           
/* 119 */           if (var8 == Material.air || var8 == Material.leaves)
/*     */           {
/* 121 */             func_175905_a(this.field_175946_l, var7, p_180712_3_, 0);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float layerSize(int p_76490_1_) {
/* 133 */     if (p_76490_1_ < this.heightLimit * 0.3F)
/*     */     {
/* 135 */       return -1.0F;
/*     */     }
/*     */ 
/*     */     
/* 139 */     float var2 = this.heightLimit / 2.0F;
/* 140 */     float var3 = var2 - p_76490_1_;
/* 141 */     float var4 = MathHelper.sqrt_float(var2 * var2 - var3 * var3);
/*     */     
/* 143 */     if (var3 == 0.0F) {
/*     */       
/* 145 */       var4 = var2;
/*     */     }
/* 147 */     else if (Math.abs(var3) >= var2) {
/*     */       
/* 149 */       return 0.0F;
/*     */     } 
/*     */     
/* 152 */     return var4 * 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   float leafSize(int p_76495_1_) {
/* 158 */     return (p_76495_1_ >= 0 && p_76495_1_ < this.leafDistanceLimit) ? ((p_76495_1_ != 0 && p_76495_1_ != this.leafDistanceLimit - 1) ? 3.0F : 2.0F) : -1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   void func_175940_a(BlockPos p_175940_1_) {
/* 163 */     for (int var2 = 0; var2 < this.leafDistanceLimit; var2++)
/*     */     {
/* 165 */       func_180712_a(p_175940_1_.offsetUp(var2), leafSize(var2), (Block)Blocks.leaves);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void func_175937_a(BlockPos p_175937_1_, BlockPos p_175937_2_, Block p_175937_3_) {
/* 171 */     BlockPos var4 = p_175937_2_.add(-p_175937_1_.getX(), -p_175937_1_.getY(), -p_175937_1_.getZ());
/* 172 */     int var5 = func_175935_b(var4);
/* 173 */     float var6 = var4.getX() / var5;
/* 174 */     float var7 = var4.getY() / var5;
/* 175 */     float var8 = var4.getZ() / var5;
/*     */     
/* 177 */     for (int var9 = 0; var9 <= var5; var9++) {
/*     */       
/* 179 */       BlockPos var10 = p_175937_1_.add((0.5F + var9 * var6), (0.5F + var9 * var7), (0.5F + var9 * var8));
/* 180 */       BlockLog.EnumAxis var11 = func_175938_b(p_175937_1_, var10);
/* 181 */       func_175903_a(this.field_175946_l, var10, p_175937_3_.getDefaultState().withProperty((IProperty)BlockLog.AXIS_PROP, (Comparable)var11));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_175935_b(BlockPos p_175935_1_) {
/* 187 */     int var2 = MathHelper.abs_int(p_175935_1_.getX());
/* 188 */     int var3 = MathHelper.abs_int(p_175935_1_.getY());
/* 189 */     int var4 = MathHelper.abs_int(p_175935_1_.getZ());
/* 190 */     return (var4 > var2 && var4 > var3) ? var4 : ((var3 > var2) ? var3 : var2);
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockLog.EnumAxis func_175938_b(BlockPos p_175938_1_, BlockPos p_175938_2_) {
/* 195 */     BlockLog.EnumAxis var3 = BlockLog.EnumAxis.Y;
/* 196 */     int var4 = Math.abs(p_175938_2_.getX() - p_175938_1_.getX());
/* 197 */     int var5 = Math.abs(p_175938_2_.getZ() - p_175938_1_.getZ());
/* 198 */     int var6 = Math.max(var4, var5);
/*     */     
/* 200 */     if (var6 > 0)
/*     */     {
/* 202 */       if (var4 == var6) {
/*     */         
/* 204 */         var3 = BlockLog.EnumAxis.X;
/*     */       }
/* 206 */       else if (var5 == var6) {
/*     */         
/* 208 */         var3 = BlockLog.EnumAxis.Z;
/*     */       } 
/*     */     }
/*     */     
/* 212 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   void func_175941_b() {
/* 217 */     Iterator<FoliageCoordinates> var1 = this.field_175948_j.iterator();
/*     */     
/* 219 */     while (var1.hasNext()) {
/*     */       
/* 221 */       FoliageCoordinates var2 = var1.next();
/* 222 */       func_175940_a(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean leafNodeNeedsBase(int p_76493_1_) {
/* 231 */     return (p_76493_1_ >= this.heightLimit * 0.2D);
/*     */   }
/*     */ 
/*     */   
/*     */   void func_175942_c() {
/* 236 */     BlockPos var1 = this.field_175947_m;
/* 237 */     BlockPos var2 = this.field_175947_m.offsetUp(this.height);
/* 238 */     Block var3 = Blocks.log;
/* 239 */     func_175937_a(var1, var2, var3);
/*     */     
/* 241 */     if (this.field_175943_g == 2) {
/*     */       
/* 243 */       func_175937_a(var1.offsetEast(), var2.offsetEast(), var3);
/* 244 */       func_175937_a(var1.offsetEast().offsetSouth(), var2.offsetEast().offsetSouth(), var3);
/* 245 */       func_175937_a(var1.offsetSouth(), var2.offsetSouth(), var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void func_175939_d() {
/* 251 */     Iterator<FoliageCoordinates> var1 = this.field_175948_j.iterator();
/*     */     
/* 253 */     while (var1.hasNext()) {
/*     */       
/* 255 */       FoliageCoordinates var2 = var1.next();
/* 256 */       int var3 = var2.func_177999_q();
/* 257 */       BlockPos var4 = new BlockPos(this.field_175947_m.getX(), var3, this.field_175947_m.getZ());
/*     */       
/* 259 */       if (leafNodeNeedsBase(var3 - this.field_175947_m.getY()))
/*     */       {
/* 261 */         func_175937_a(var4, var2, Blocks.log);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   int func_175936_a(BlockPos p_175936_1_, BlockPos p_175936_2_) {
/* 268 */     BlockPos var3 = p_175936_2_.add(-p_175936_1_.getX(), -p_175936_1_.getY(), -p_175936_1_.getZ());
/* 269 */     int var4 = func_175935_b(var3);
/* 270 */     float var5 = var3.getX() / var4;
/* 271 */     float var6 = var3.getY() / var4;
/* 272 */     float var7 = var3.getZ() / var4;
/*     */     
/* 274 */     if (var4 == 0)
/*     */     {
/* 276 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 280 */     for (int var8 = 0; var8 <= var4; var8++) {
/*     */       
/* 282 */       BlockPos var9 = p_175936_1_.add((0.5F + var8 * var5), (0.5F + var8 * var6), (0.5F + var8 * var7));
/*     */       
/* 284 */       if (!func_150523_a(this.field_175946_l.getBlockState(var9).getBlock()))
/*     */       {
/* 286 */         return var8;
/*     */       }
/*     */     } 
/*     */     
/* 290 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_175904_e() {
/* 296 */     this.leafDistanceLimit = 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/* 301 */     this.field_175946_l = worldIn;
/* 302 */     this.field_175947_m = p_180709_3_;
/* 303 */     this.field_175949_k = new Random(p_180709_2_.nextLong());
/*     */     
/* 305 */     if (this.heightLimit == 0)
/*     */     {
/* 307 */       this.heightLimit = 5 + this.field_175949_k.nextInt(this.field_175950_h);
/*     */     }
/*     */     
/* 310 */     if (!validTreeLocation())
/*     */     {
/* 312 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 316 */     generateLeafNodeList();
/* 317 */     func_175941_b();
/* 318 */     func_175942_c();
/* 319 */     func_175939_d();
/* 320 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean validTreeLocation() {
/* 330 */     Block var1 = this.field_175946_l.getBlockState(this.field_175947_m.offsetDown()).getBlock();
/*     */     
/* 332 */     if (var1 != Blocks.dirt && var1 != Blocks.grass && var1 != Blocks.farmland)
/*     */     {
/* 334 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 338 */     int var2 = func_175936_a(this.field_175947_m, this.field_175947_m.offsetUp(this.heightLimit - 1));
/*     */     
/* 340 */     if (var2 == -1)
/*     */     {
/* 342 */       return true;
/*     */     }
/* 344 */     if (var2 < 6)
/*     */     {
/* 346 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 350 */     this.heightLimit = var2;
/* 351 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   static class FoliageCoordinates
/*     */     extends BlockPos
/*     */   {
/*     */     private final int field_178000_b;
/*     */     
/*     */     private static final String __OBFID = "CL_00002001";
/*     */     
/*     */     public FoliageCoordinates(BlockPos p_i45635_1_, int p_i45635_2_) {
/* 363 */       super(p_i45635_1_.getX(), p_i45635_1_.getY(), p_i45635_1_.getZ());
/* 364 */       this.field_178000_b = p_i45635_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_177999_q() {
/* 369 */       return this.field_178000_b;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenBigTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */