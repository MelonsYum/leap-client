/*     */ package net.minecraft.world.biome;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockDoublePlant;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigMushroom;
/*     */ import net.minecraft.world.gen.feature.WorldGenCanopyTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenForest;
/*     */ 
/*     */ public class BiomeGenForest
/*     */   extends BiomeGenBase {
/*     */   private int field_150632_aF;
/*  18 */   protected static final WorldGenForest field_150629_aC = new WorldGenForest(false, true);
/*  19 */   protected static final WorldGenForest field_150630_aD = new WorldGenForest(false, false);
/*  20 */   protected static final WorldGenCanopyTree field_150631_aE = new WorldGenCanopyTree(false);
/*     */   
/*     */   private static final String __OBFID = "CL_00000170";
/*     */   
/*     */   public BiomeGenForest(int p_i45377_1_, int p_i45377_2_) {
/*  25 */     super(p_i45377_1_);
/*  26 */     this.field_150632_aF = p_i45377_2_;
/*  27 */     this.theBiomeDecorator.treesPerChunk = 10;
/*  28 */     this.theBiomeDecorator.grassPerChunk = 2;
/*     */     
/*  30 */     if (this.field_150632_aF == 1) {
/*     */       
/*  32 */       this.theBiomeDecorator.treesPerChunk = 6;
/*  33 */       this.theBiomeDecorator.flowersPerChunk = 100;
/*  34 */       this.theBiomeDecorator.grassPerChunk = 1;
/*     */     } 
/*     */     
/*  37 */     setFillerBlockMetadata(5159473);
/*  38 */     setTemperatureRainfall(0.7F, 0.8F);
/*     */     
/*  40 */     if (this.field_150632_aF == 2) {
/*     */       
/*  42 */       this.field_150609_ah = 353825;
/*  43 */       this.color = 3175492;
/*  44 */       setTemperatureRainfall(0.6F, 0.6F);
/*     */     } 
/*     */     
/*  47 */     if (this.field_150632_aF == 0)
/*     */     {
/*  49 */       this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 5, 4, 4));
/*     */     }
/*     */     
/*  52 */     if (this.field_150632_aF == 3)
/*     */     {
/*  54 */       this.theBiomeDecorator.treesPerChunk = -999;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase func_150557_a(int p_150557_1_, boolean p_150557_2_) {
/*  60 */     if (this.field_150632_aF == 2) {
/*     */       
/*  62 */       this.field_150609_ah = 353825;
/*  63 */       this.color = p_150557_1_;
/*     */       
/*  65 */       if (p_150557_2_)
/*     */       {
/*  67 */         this.field_150609_ah = (this.field_150609_ah & 0xFEFEFE) >> 1;
/*     */       }
/*     */       
/*  70 */       return this;
/*     */     } 
/*     */ 
/*     */     
/*  74 */     return super.func_150557_a(p_150557_1_, p_150557_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/*  80 */     return (this.field_150632_aF == 3 && p_150567_1_.nextInt(3) > 0) ? (WorldGenAbstractTree)field_150631_aE : ((this.field_150632_aF != 2 && p_150567_1_.nextInt(5) != 0) ? (WorldGenAbstractTree)this.worldGeneratorTrees : (WorldGenAbstractTree)field_150630_aD);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFlower.EnumFlowerType pickRandomFlower(Random p_180623_1_, BlockPos p_180623_2_) {
/*  85 */     if (this.field_150632_aF == 1) {
/*     */       
/*  87 */       double var3 = MathHelper.clamp_double((1.0D + field_180281_af.func_151601_a(p_180623_2_.getX() / 48.0D, p_180623_2_.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
/*  88 */       BlockFlower.EnumFlowerType var5 = BlockFlower.EnumFlowerType.values()[(int)(var3 * (BlockFlower.EnumFlowerType.values()).length)];
/*  89 */       return (var5 == BlockFlower.EnumFlowerType.BLUE_ORCHID) ? BlockFlower.EnumFlowerType.POPPY : var5;
/*     */     } 
/*     */ 
/*     */     
/*  93 */     return super.pickRandomFlower(p_180623_1_, p_180623_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/* 104 */     if (this.field_150632_aF == 3)
/*     */     {
/* 106 */       for (int i = 0; i < 4; i++) {
/*     */         
/* 108 */         for (int j = 0; j < 4; j++) {
/*     */           
/* 110 */           int var6 = i * 4 + 1 + 8 + p_180624_2_.nextInt(3);
/* 111 */           int var7 = j * 4 + 1 + 8 + p_180624_2_.nextInt(3);
/* 112 */           BlockPos var8 = worldIn.getHorizon(p_180624_3_.add(var6, 0, var7));
/*     */           
/* 114 */           if (p_180624_2_.nextInt(20) == 0) {
/*     */             
/* 116 */             WorldGenBigMushroom var9 = new WorldGenBigMushroom();
/* 117 */             var9.generate(worldIn, p_180624_2_, var8);
/*     */           }
/*     */           else {
/*     */             
/* 121 */             WorldGenAbstractTree var12 = genBigTreeChance(p_180624_2_);
/* 122 */             var12.func_175904_e();
/*     */             
/* 124 */             if (var12.generate(worldIn, p_180624_2_, var8))
/*     */             {
/* 126 */               var12.func_180711_a(worldIn, p_180624_2_, var8);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 133 */     int var4 = p_180624_2_.nextInt(5) - 3;
/*     */     
/* 135 */     if (this.field_150632_aF == 1)
/*     */     {
/* 137 */       var4 += 2;
/*     */     }
/*     */     
/* 140 */     int var5 = 0;
/*     */     
/* 142 */     while (var5 < var4) {
/*     */       
/* 144 */       int var6 = p_180624_2_.nextInt(3);
/*     */       
/* 146 */       if (var6 == 0) {
/*     */         
/* 148 */         field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.SYRINGA);
/*     */       }
/* 150 */       else if (var6 == 1) {
/*     */         
/* 152 */         field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.ROSE);
/*     */       }
/* 154 */       else if (var6 == 2) {
/*     */         
/* 156 */         field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.PAEONIA);
/*     */       } 
/*     */       
/* 159 */       int var7 = 0;
/*     */ 
/*     */ 
/*     */       
/* 163 */       while (var7 < 5) {
/*     */         
/* 165 */         int var11 = p_180624_2_.nextInt(16) + 8;
/* 166 */         int var13 = p_180624_2_.nextInt(16) + 8;
/* 167 */         int var10 = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(var11, 0, var13)).getY() + 32);
/*     */         
/* 169 */         if (!field_180280_ag.generate(worldIn, p_180624_2_, new BlockPos(p_180624_3_.getX() + var11, var10, p_180624_3_.getZ() + var13)))
/*     */         {
/* 171 */           var7++;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 176 */       var5++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 181 */     super.func_180624_a(worldIn, p_180624_2_, p_180624_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_180627_b(BlockPos p_180627_1_) {
/* 186 */     int var2 = super.func_180627_b(p_180627_1_);
/* 187 */     return (this.field_150632_aF == 3) ? ((var2 & 0xFEFEFE) + 2634762 >> 1) : var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase createMutatedBiome(int p_180277_1_) {
/* 192 */     if (this.biomeID == BiomeGenBase.forest.biomeID) {
/*     */       
/* 194 */       BiomeGenForest var2 = new BiomeGenForest(p_180277_1_, 1);
/* 195 */       var2.setHeight(new BiomeGenBase.Height(this.minHeight, this.maxHeight + 0.2F));
/* 196 */       var2.setBiomeName("Flower Forest");
/* 197 */       var2.func_150557_a(6976549, true);
/* 198 */       var2.setFillerBlockMetadata(8233509);
/* 199 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 203 */     return (this.biomeID != BiomeGenBase.birchForest.biomeID && this.biomeID != BiomeGenBase.birchForestHills.biomeID) ? new BiomeGenMutated(p_180277_1_, this)
/*     */       {
/*     */         private static final String __OBFID = "CL_00000172";
/*     */         
/*     */         public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/* 208 */           this.baseBiome.func_180624_a(worldIn, p_180624_2_, p_180624_3_);
/*     */         }
/* 210 */       } : new BiomeGenMutated(p_180277_1_, this)
/*     */       {
/*     */         private static final String __OBFID = "CL_00001861";
/*     */         
/*     */         public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/* 215 */           return p_150567_1_.nextBoolean() ? (WorldGenAbstractTree)BiomeGenForest.field_150629_aC : (WorldGenAbstractTree)BiomeGenForest.field_150630_aD;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenForest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */