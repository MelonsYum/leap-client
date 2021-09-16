/*     */ package net.minecraft.world.biome;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockColored;
/*     */ import net.minecraft.block.BlockDirt;
/*     */ import net.minecraft.block.BlockSand;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.gen.NoiseGeneratorPerlin;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ 
/*     */ public class BiomeGenMesa
/*     */   extends BiomeGenBase {
/*     */   private IBlockState[] field_150621_aC;
/*     */   private long field_150622_aD;
/*     */   private NoiseGeneratorPerlin field_150623_aE;
/*     */   private NoiseGeneratorPerlin field_150624_aF;
/*     */   private NoiseGeneratorPerlin field_150625_aG;
/*     */   private boolean field_150626_aH;
/*     */   private boolean field_150620_aI;
/*     */   private static final String __OBFID = "CL_00000176";
/*     */   
/*     */   public BiomeGenMesa(int p_i45380_1_, boolean p_i45380_2_, boolean p_i45380_3_) {
/*  31 */     super(p_i45380_1_);
/*  32 */     this.field_150626_aH = p_i45380_2_;
/*  33 */     this.field_150620_aI = p_i45380_3_;
/*  34 */     setDisableRain();
/*  35 */     setTemperatureRainfall(2.0F, 0.0F);
/*  36 */     this.spawnableCreatureList.clear();
/*  37 */     this.topBlock = Blocks.sand.getDefaultState().withProperty((IProperty)BlockSand.VARIANT_PROP, (Comparable)BlockSand.EnumType.RED_SAND);
/*  38 */     this.fillerBlock = Blocks.stained_hardened_clay.getDefaultState();
/*  39 */     this.theBiomeDecorator.treesPerChunk = -999;
/*  40 */     this.theBiomeDecorator.deadBushPerChunk = 20;
/*  41 */     this.theBiomeDecorator.reedsPerChunk = 3;
/*  42 */     this.theBiomeDecorator.cactiPerChunk = 5;
/*  43 */     this.theBiomeDecorator.flowersPerChunk = 0;
/*  44 */     this.spawnableCreatureList.clear();
/*     */     
/*  46 */     if (p_i45380_3_)
/*     */     {
/*  48 */       this.theBiomeDecorator.treesPerChunk = 5;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/*  54 */     return (WorldGenAbstractTree)this.worldGeneratorTrees;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_180625_c(BlockPos p_180625_1_) {
/*  59 */     return 10387789;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_180627_b(BlockPos p_180627_1_) {
/*  64 */     return 9470285;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/*  69 */     super.func_180624_a(worldIn, p_180624_2_, p_180624_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
/*  74 */     if (this.field_150621_aC == null || this.field_150622_aD != worldIn.getSeed())
/*     */     {
/*  76 */       func_150619_a(worldIn.getSeed());
/*     */     }
/*     */     
/*  79 */     if (this.field_150623_aE == null || this.field_150624_aF == null || this.field_150622_aD != worldIn.getSeed()) {
/*     */       
/*  81 */       Random var8 = new Random(this.field_150622_aD);
/*  82 */       this.field_150623_aE = new NoiseGeneratorPerlin(var8, 4);
/*  83 */       this.field_150624_aF = new NoiseGeneratorPerlin(var8, 1);
/*     */     } 
/*     */     
/*  86 */     this.field_150622_aD = worldIn.getSeed();
/*  87 */     double var22 = 0.0D;
/*     */ 
/*     */ 
/*     */     
/*  91 */     if (this.field_150626_aH) {
/*     */       
/*  93 */       int i = (p_180622_4_ & 0xFFFFFFF0) + (p_180622_5_ & 0xF);
/*  94 */       int j = (p_180622_5_ & 0xFFFFFFF0) + (p_180622_4_ & 0xF);
/*  95 */       double var12 = Math.min(Math.abs(p_180622_6_), this.field_150623_aE.func_151601_a(i * 0.25D, j * 0.25D));
/*     */       
/*  97 */       if (var12 > 0.0D) {
/*     */         
/*  99 */         double var14 = 0.001953125D;
/* 100 */         double var16 = Math.abs(this.field_150624_aF.func_151601_a(i * var14, j * var14));
/* 101 */         var22 = var12 * var12 * 2.5D;
/* 102 */         double var18 = Math.ceil(var16 * 50.0D) + 14.0D;
/*     */         
/* 104 */         if (var22 > var18)
/*     */         {
/* 106 */           var22 = var18;
/*     */         }
/*     */         
/* 109 */         var22 += 64.0D;
/*     */       } 
/*     */     } 
/*     */     
/* 113 */     int var10 = p_180622_4_ & 0xF;
/* 114 */     int var11 = p_180622_5_ & 0xF;
/* 115 */     boolean var23 = true;
/* 116 */     IBlockState var13 = Blocks.stained_hardened_clay.getDefaultState();
/* 117 */     IBlockState var24 = this.fillerBlock;
/* 118 */     int var15 = (int)(p_180622_6_ / 3.0D + 3.0D + p_180622_2_.nextDouble() * 0.25D);
/* 119 */     boolean var25 = (Math.cos(p_180622_6_ / 3.0D * Math.PI) > 0.0D);
/* 120 */     int var17 = -1;
/* 121 */     boolean var26 = false;
/*     */     
/* 123 */     for (int var19 = 255; var19 >= 0; var19--) {
/*     */       
/* 125 */       if (p_180622_3_.getBlockState(var11, var19, var10).getBlock().getMaterial() == Material.air && var19 < (int)var22)
/*     */       {
/* 127 */         p_180622_3_.setBlockState(var11, var19, var10, Blocks.stone.getDefaultState());
/*     */       }
/*     */       
/* 130 */       if (var19 <= p_180622_2_.nextInt(5)) {
/*     */         
/* 132 */         p_180622_3_.setBlockState(var11, var19, var10, Blocks.bedrock.getDefaultState());
/*     */       }
/*     */       else {
/*     */         
/* 136 */         IBlockState var20 = p_180622_3_.getBlockState(var11, var19, var10);
/*     */         
/* 138 */         if (var20.getBlock().getMaterial() == Material.air) {
/*     */           
/* 140 */           var17 = -1;
/*     */         }
/* 142 */         else if (var20.getBlock() == Blocks.stone) {
/*     */ 
/*     */ 
/*     */           
/* 146 */           if (var17 == -1) {
/*     */             
/* 148 */             var26 = false;
/*     */             
/* 150 */             if (var15 <= 0) {
/*     */               
/* 152 */               var13 = null;
/* 153 */               var24 = Blocks.stone.getDefaultState();
/*     */             }
/* 155 */             else if (var19 >= 59 && var19 <= 64) {
/*     */               
/* 157 */               var13 = Blocks.stained_hardened_clay.getDefaultState();
/* 158 */               var24 = this.fillerBlock;
/*     */             } 
/*     */             
/* 161 */             if (var19 < 63 && (var13 == null || var13.getBlock().getMaterial() == Material.air))
/*     */             {
/* 163 */               var13 = Blocks.water.getDefaultState();
/*     */             }
/*     */             
/* 166 */             var17 = var15 + Math.max(0, var19 - 63);
/*     */             
/* 168 */             if (var19 >= 62) {
/*     */               
/* 170 */               if (this.field_150620_aI && var19 > 86 + var15 * 2) {
/*     */                 
/* 172 */                 if (var25)
/*     */                 {
/* 174 */                   p_180622_3_.setBlockState(var11, var19, var10, Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, (Comparable)BlockDirt.DirtType.COARSE_DIRT));
/*     */                 }
/*     */                 else
/*     */                 {
/* 178 */                   p_180622_3_.setBlockState(var11, var19, var10, Blocks.grass.getDefaultState());
/*     */                 }
/*     */               
/* 181 */               } else if (var19 > 66 + var15) {
/*     */                 IBlockState var21;
/* 183 */                 if (var19 >= 64 && var19 <= 127) {
/*     */                   
/* 185 */                   if (var25)
/*     */                   {
/* 187 */                     var21 = Blocks.hardened_clay.getDefaultState();
/*     */                   }
/*     */                   else
/*     */                   {
/* 191 */                     var21 = func_180629_a(p_180622_4_, var19, p_180622_5_);
/*     */                   }
/*     */                 
/*     */                 } else {
/*     */                   
/* 196 */                   var21 = Blocks.stained_hardened_clay.getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.ORANGE);
/*     */                 } 
/*     */                 
/* 199 */                 p_180622_3_.setBlockState(var11, var19, var10, var21);
/*     */               }
/*     */               else {
/*     */                 
/* 203 */                 p_180622_3_.setBlockState(var11, var19, var10, this.topBlock);
/* 204 */                 var26 = true;
/*     */               }
/*     */             
/*     */             } else {
/*     */               
/* 209 */               p_180622_3_.setBlockState(var11, var19, var10, var24);
/*     */               
/* 211 */               if (var24.getBlock() == Blocks.stained_hardened_clay)
/*     */               {
/* 213 */                 p_180622_3_.setBlockState(var11, var19, var10, var24.getBlock().getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.ORANGE));
/*     */               }
/*     */             }
/*     */           
/* 217 */           } else if (var17 > 0) {
/*     */             
/* 219 */             var17--;
/*     */             
/* 221 */             if (var26) {
/*     */               
/* 223 */               p_180622_3_.setBlockState(var11, var19, var10, Blocks.stained_hardened_clay.getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.ORANGE));
/*     */             }
/*     */             else {
/*     */               
/* 227 */               IBlockState var21 = func_180629_a(p_180622_4_, var19, p_180622_5_);
/* 228 */               p_180622_3_.setBlockState(var11, var19, var10, var21);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_150619_a(long p_150619_1_) {
/* 238 */     this.field_150621_aC = new IBlockState[64];
/* 239 */     Arrays.fill((Object[])this.field_150621_aC, Blocks.hardened_clay.getDefaultState());
/* 240 */     Random var3 = new Random(p_150619_1_);
/* 241 */     this.field_150625_aG = new NoiseGeneratorPerlin(var3, 1);
/*     */     
/*     */     int var4;
/* 244 */     for (var4 = 0; var4 < 64; var4++) {
/*     */       
/* 246 */       var4 += var3.nextInt(5) + 1;
/*     */       
/* 248 */       if (var4 < 64)
/*     */       {
/* 250 */         this.field_150621_aC[var4] = Blocks.stained_hardened_clay.getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.ORANGE);
/*     */       }
/*     */     } 
/*     */     
/* 254 */     var4 = var3.nextInt(4) + 2;
/*     */ 
/*     */     
/*     */     int var5;
/*     */ 
/*     */     
/* 260 */     for (var5 = 0; var5 < var4; var5++) {
/*     */       
/* 262 */       int i = var3.nextInt(3) + 1;
/* 263 */       int j = var3.nextInt(64);
/*     */       
/* 265 */       for (int k = 0; j + k < 64 && k < i; k++)
/*     */       {
/* 267 */         this.field_150621_aC[j + k] = Blocks.stained_hardened_clay.getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.YELLOW);
/*     */       }
/*     */     } 
/*     */     
/* 271 */     var5 = var3.nextInt(4) + 2;
/*     */     
/*     */     int var6;
/* 274 */     for (var6 = 0; var6 < var5; var6++) {
/*     */       
/* 276 */       int i = var3.nextInt(3) + 2;
/* 277 */       int j = var3.nextInt(64);
/*     */       
/* 279 */       for (int k = 0; j + k < 64 && k < i; k++)
/*     */       {
/* 281 */         this.field_150621_aC[j + k] = Blocks.stained_hardened_clay.getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.BROWN);
/*     */       }
/*     */     } 
/*     */     
/* 285 */     var6 = var3.nextInt(4) + 2;
/*     */     int var7;
/* 287 */     for (var7 = 0; var7 < var6; var7++) {
/*     */       
/* 289 */       int i = var3.nextInt(3) + 1;
/* 290 */       int j = var3.nextInt(64);
/*     */       
/* 292 */       for (int var10 = 0; j + var10 < 64 && var10 < i; var10++)
/*     */       {
/* 294 */         this.field_150621_aC[j + var10] = Blocks.stained_hardened_clay.getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.RED);
/*     */       }
/*     */     } 
/*     */     
/* 298 */     var7 = var3.nextInt(3) + 3;
/* 299 */     int var8 = 0;
/*     */     
/* 301 */     for (int var9 = 0; var9 < var7; var9++) {
/*     */       
/* 303 */       byte var12 = 1;
/* 304 */       var8 += var3.nextInt(16) + 4;
/*     */       
/* 306 */       for (int var11 = 0; var8 + var11 < 64 && var11 < var12; var11++) {
/*     */         
/* 308 */         this.field_150621_aC[var8 + var11] = Blocks.stained_hardened_clay.getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.WHITE);
/*     */         
/* 310 */         if (var8 + var11 > 1 && var3.nextBoolean())
/*     */         {
/* 312 */           this.field_150621_aC[var8 + var11 - 1] = Blocks.stained_hardened_clay.getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.SILVER);
/*     */         }
/*     */         
/* 315 */         if (var8 + var11 < 63 && var3.nextBoolean())
/*     */         {
/* 317 */           this.field_150621_aC[var8 + var11 + 1] = Blocks.stained_hardened_clay.getDefaultState().withProperty((IProperty)BlockColored.COLOR, (Comparable)EnumDyeColor.SILVER);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private IBlockState func_180629_a(int p_180629_1_, int p_180629_2_, int p_180629_3_) {
/* 325 */     int var4 = (int)Math.round(this.field_150625_aG.func_151601_a(p_180629_1_ * 1.0D / 512.0D, p_180629_1_ * 1.0D / 512.0D) * 2.0D);
/* 326 */     return this.field_150621_aC[(p_180629_2_ + var4 + 64) % 64];
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase createMutatedBiome(int p_180277_1_) {
/* 331 */     boolean var2 = (this.biomeID == BiomeGenBase.mesa.biomeID);
/* 332 */     BiomeGenMesa var3 = new BiomeGenMesa(p_180277_1_, var2, this.field_150620_aI);
/*     */     
/* 334 */     if (!var2) {
/*     */       
/* 336 */       var3.setHeight(height_LowHills);
/* 337 */       var3.setBiomeName(String.valueOf(this.biomeName) + " M");
/*     */     }
/*     */     else {
/*     */       
/* 341 */       var3.setBiomeName(String.valueOf(this.biomeName) + " (Bryce)");
/*     */     } 
/*     */     
/* 344 */     var3.func_150557_a(this.color, true);
/* 345 */     return var3;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenMesa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */