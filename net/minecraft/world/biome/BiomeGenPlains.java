/*     */ package net.minecraft.world.biome;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.BlockDoublePlant;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.entity.passive.EntityHorse;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BiomeGenPlains
/*     */   extends BiomeGenBase
/*     */ {
/*     */   protected boolean field_150628_aC;
/*     */   private static final String __OBFID = "CL_00000180";
/*     */   
/*     */   protected BiomeGenPlains(int p_i1986_1_) {
/*  17 */     super(p_i1986_1_);
/*  18 */     setTemperatureRainfall(0.8F, 0.4F);
/*  19 */     setHeight(height_LowPlains);
/*  20 */     this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));
/*  21 */     this.theBiomeDecorator.treesPerChunk = -999;
/*  22 */     this.theBiomeDecorator.flowersPerChunk = 4;
/*  23 */     this.theBiomeDecorator.grassPerChunk = 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFlower.EnumFlowerType pickRandomFlower(Random p_180623_1_, BlockPos p_180623_2_) {
/*  28 */     double var3 = field_180281_af.func_151601_a(p_180623_2_.getX() / 200.0D, p_180623_2_.getZ() / 200.0D);
/*     */ 
/*     */     
/*  31 */     if (var3 < -0.8D) {
/*     */       
/*  33 */       int var5 = p_180623_1_.nextInt(4);
/*     */       
/*  35 */       switch (var5) {
/*     */         
/*     */         case 0:
/*  38 */           return BlockFlower.EnumFlowerType.ORANGE_TULIP;
/*     */         
/*     */         case 1:
/*  41 */           return BlockFlower.EnumFlowerType.RED_TULIP;
/*     */         
/*     */         case 2:
/*  44 */           return BlockFlower.EnumFlowerType.PINK_TULIP;
/*     */       } 
/*     */ 
/*     */       
/*  48 */       return BlockFlower.EnumFlowerType.WHITE_TULIP;
/*     */     } 
/*     */     
/*  51 */     if (p_180623_1_.nextInt(3) > 0) {
/*     */       
/*  53 */       int var5 = p_180623_1_.nextInt(3);
/*  54 */       return (var5 == 0) ? BlockFlower.EnumFlowerType.POPPY : ((var5 == 1) ? BlockFlower.EnumFlowerType.HOUSTONIA : BlockFlower.EnumFlowerType.OXEYE_DAISY);
/*     */     } 
/*     */ 
/*     */     
/*  58 */     return BlockFlower.EnumFlowerType.DANDELION;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/*  64 */     double var4 = field_180281_af.func_151601_a((p_180624_3_.getX() + 8) / 200.0D, (p_180624_3_.getZ() + 8) / 200.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     if (var4 < -0.8D) {
/*     */       
/*  72 */       this.theBiomeDecorator.flowersPerChunk = 15;
/*  73 */       this.theBiomeDecorator.grassPerChunk = 5;
/*     */     }
/*     */     else {
/*     */       
/*  77 */       this.theBiomeDecorator.flowersPerChunk = 4;
/*  78 */       this.theBiomeDecorator.grassPerChunk = 10;
/*  79 */       field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.GRASS);
/*     */       
/*  81 */       for (int var6 = 0; var6 < 7; var6++) {
/*     */         
/*  83 */         int var7 = p_180624_2_.nextInt(16) + 8;
/*  84 */         int var8 = p_180624_2_.nextInt(16) + 8;
/*  85 */         int var9 = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(var7, 0, var8)).getY() + 32);
/*  86 */         field_180280_ag.generate(worldIn, p_180624_2_, p_180624_3_.add(var7, var9, var8));
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     if (this.field_150628_aC) {
/*     */       
/*  92 */       field_180280_ag.func_180710_a(BlockDoublePlant.EnumPlantType.SUNFLOWER);
/*     */       
/*  94 */       for (int var6 = 0; var6 < 10; var6++) {
/*     */         
/*  96 */         int var7 = p_180624_2_.nextInt(16) + 8;
/*  97 */         int var8 = p_180624_2_.nextInt(16) + 8;
/*  98 */         int var9 = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(var7, 0, var8)).getY() + 32);
/*  99 */         field_180280_ag.generate(worldIn, p_180624_2_, p_180624_3_.add(var7, var9, var8));
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     super.func_180624_a(worldIn, p_180624_2_, p_180624_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase createMutatedBiome(int p_180277_1_) {
/* 108 */     BiomeGenPlains var2 = new BiomeGenPlains(p_180277_1_);
/* 109 */     var2.setBiomeName("Sunflower Plains");
/* 110 */     var2.field_150628_aC = true;
/* 111 */     var2.setColor(9286496);
/* 112 */     var2.field_150609_ah = 14273354;
/* 113 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenPlains.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */