/*     */ package net.minecraft.world.biome;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.awt.Color;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.BlockSand;
/*     */ import net.minecraft.block.BlockTallGrass;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.monster.EntityCreeper;
/*     */ import net.minecraft.entity.monster.EntityEnderman;
/*     */ import net.minecraft.entity.monster.EntitySkeleton;
/*     */ import net.minecraft.entity.monster.EntitySlime;
/*     */ import net.minecraft.entity.monster.EntitySpider;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.passive.EntityBat;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.passive.EntityRabbit;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.passive.EntitySquid;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.world.ColorizerFoliage;
/*     */ import net.minecraft.world.ColorizerGrass;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.gen.NoiseGeneratorPerlin;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenDoublePlant;
/*     */ import net.minecraft.world.gen.feature.WorldGenSwamp;
/*     */ import net.minecraft.world.gen.feature.WorldGenTallGrass;
/*     */ import net.minecraft.world.gen.feature.WorldGenTrees;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public abstract class BiomeGenBase {
/*  53 */   private static final Logger logger = LogManager.getLogger();
/*  54 */   protected static final Height height_Default = new Height(0.1F, 0.2F);
/*  55 */   protected static final Height height_ShallowWaters = new Height(-0.5F, 0.0F);
/*  56 */   protected static final Height height_Oceans = new Height(-1.0F, 0.1F);
/*  57 */   protected static final Height height_DeepOceans = new Height(-1.8F, 0.1F);
/*  58 */   protected static final Height height_LowPlains = new Height(0.125F, 0.05F);
/*  59 */   protected static final Height height_MidPlains = new Height(0.2F, 0.2F);
/*  60 */   protected static final Height height_LowHills = new Height(0.45F, 0.3F);
/*  61 */   protected static final Height height_HighPlateaus = new Height(1.5F, 0.025F);
/*  62 */   protected static final Height height_MidHills = new Height(1.0F, 0.5F);
/*  63 */   protected static final Height height_Shores = new Height(0.0F, 0.025F);
/*  64 */   protected static final Height height_RockyWaters = new Height(0.1F, 0.8F);
/*  65 */   protected static final Height height_LowIslands = new Height(0.2F, 0.3F);
/*  66 */   protected static final Height height_PartiallySubmerged = new Height(-0.2F, 0.1F);
/*     */ 
/*     */   
/*  69 */   private static final BiomeGenBase[] biomeList = new BiomeGenBase[256];
/*  70 */   public static final Set explorationBiomesList = Sets.newHashSet();
/*  71 */   public static final Map field_180278_o = Maps.newHashMap();
/*  72 */   public static final BiomeGenBase ocean = (new BiomeGenOcean(0)).setColor(112).setBiomeName("Ocean").setHeight(height_Oceans);
/*  73 */   public static final BiomeGenBase plains = (new BiomeGenPlains(1)).setColor(9286496).setBiomeName("Plains");
/*  74 */   public static final BiomeGenBase desert = (new BiomeGenDesert(2)).setColor(16421912).setBiomeName("Desert").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowPlains);
/*  75 */   public static final BiomeGenBase extremeHills = (new BiomeGenHills(3, false)).setColor(6316128).setBiomeName("Extreme Hills").setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
/*  76 */   public static final BiomeGenBase forest = (new BiomeGenForest(4, 0)).setColor(353825).setBiomeName("Forest");
/*  77 */   public static final BiomeGenBase taiga = (new BiomeGenTaiga(5, 0)).setColor(747097).setBiomeName("Taiga").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_MidPlains);
/*  78 */   public static final BiomeGenBase swampland = (new BiomeGenSwamp(6)).setColor(522674).setBiomeName("Swampland").setFillerBlockMetadata(9154376).setHeight(height_PartiallySubmerged).setTemperatureRainfall(0.8F, 0.9F);
/*  79 */   public static final BiomeGenBase river = (new BiomeGenRiver(7)).setColor(255).setBiomeName("River").setHeight(height_ShallowWaters);
/*  80 */   public static final BiomeGenBase hell = (new BiomeGenHell(8)).setColor(16711680).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
/*     */ 
/*     */   
/*  83 */   public static final BiomeGenBase sky = (new BiomeGenEnd(9)).setColor(8421631).setBiomeName("The End").setDisableRain();
/*  84 */   public static final BiomeGenBase frozenOcean = (new BiomeGenOcean(10)).setColor(9474208).setBiomeName("FrozenOcean").setEnableSnow().setHeight(height_Oceans).setTemperatureRainfall(0.0F, 0.5F);
/*  85 */   public static final BiomeGenBase frozenRiver = (new BiomeGenRiver(11)).setColor(10526975).setBiomeName("FrozenRiver").setEnableSnow().setHeight(height_ShallowWaters).setTemperatureRainfall(0.0F, 0.5F);
/*  86 */   public static final BiomeGenBase icePlains = (new BiomeGenSnow(12, false)).setColor(16777215).setBiomeName("Ice Plains").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F).setHeight(height_LowPlains);
/*  87 */   public static final BiomeGenBase iceMountains = (new BiomeGenSnow(13, false)).setColor(10526880).setBiomeName("Ice Mountains").setEnableSnow().setHeight(height_LowHills).setTemperatureRainfall(0.0F, 0.5F);
/*  88 */   public static final BiomeGenBase mushroomIsland = (new BiomeGenMushroomIsland(14)).setColor(16711935).setBiomeName("MushroomIsland").setTemperatureRainfall(0.9F, 1.0F).setHeight(height_LowIslands);
/*  89 */   public static final BiomeGenBase mushroomIslandShore = (new BiomeGenMushroomIsland(15)).setColor(10486015).setBiomeName("MushroomIslandShore").setTemperatureRainfall(0.9F, 1.0F).setHeight(height_Shores);
/*     */ 
/*     */   
/*  92 */   public static final BiomeGenBase beach = (new BiomeGenBeach(16)).setColor(16440917).setBiomeName("Beach").setTemperatureRainfall(0.8F, 0.4F).setHeight(height_Shores);
/*     */ 
/*     */   
/*  95 */   public static final BiomeGenBase desertHills = (new BiomeGenDesert(17)).setColor(13786898).setBiomeName("DesertHills").setDisableRain().setTemperatureRainfall(2.0F, 0.0F).setHeight(height_LowHills);
/*     */ 
/*     */   
/*  98 */   public static final BiomeGenBase forestHills = (new BiomeGenForest(18, 0)).setColor(2250012).setBiomeName("ForestHills").setHeight(height_LowHills);
/*     */ 
/*     */   
/* 101 */   public static final BiomeGenBase taigaHills = (new BiomeGenTaiga(19, 0)).setColor(1456435).setBiomeName("TaigaHills").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.25F, 0.8F).setHeight(height_LowHills);
/*     */ 
/*     */   
/* 104 */   public static final BiomeGenBase extremeHillsEdge = (new BiomeGenHills(20, true)).setColor(7501978).setBiomeName("Extreme Hills Edge").setHeight(height_MidHills.attenuate()).setTemperatureRainfall(0.2F, 0.3F);
/*     */ 
/*     */   
/* 107 */   public static final BiomeGenBase jungle = (new BiomeGenJungle(21, false)).setColor(5470985).setBiomeName("Jungle").setFillerBlockMetadata(5470985).setTemperatureRainfall(0.95F, 0.9F);
/* 108 */   public static final BiomeGenBase jungleHills = (new BiomeGenJungle(22, false)).setColor(2900485).setBiomeName("JungleHills").setFillerBlockMetadata(5470985).setTemperatureRainfall(0.95F, 0.9F).setHeight(height_LowHills);
/* 109 */   public static final BiomeGenBase jungleEdge = (new BiomeGenJungle(23, true)).setColor(6458135).setBiomeName("JungleEdge").setFillerBlockMetadata(5470985).setTemperatureRainfall(0.95F, 0.8F);
/* 110 */   public static final BiomeGenBase deepOcean = (new BiomeGenOcean(24)).setColor(48).setBiomeName("Deep Ocean").setHeight(height_DeepOceans);
/* 111 */   public static final BiomeGenBase stoneBeach = (new BiomeGenStoneBeach(25)).setColor(10658436).setBiomeName("Stone Beach").setTemperatureRainfall(0.2F, 0.3F).setHeight(height_RockyWaters);
/* 112 */   public static final BiomeGenBase coldBeach = (new BiomeGenBeach(26)).setColor(16445632).setBiomeName("Cold Beach").setTemperatureRainfall(0.05F, 0.3F).setHeight(height_Shores).setEnableSnow();
/* 113 */   public static final BiomeGenBase birchForest = (new BiomeGenForest(27, 2)).setBiomeName("Birch Forest").setColor(3175492);
/* 114 */   public static final BiomeGenBase birchForestHills = (new BiomeGenForest(28, 2)).setBiomeName("Birch Forest Hills").setColor(2055986).setHeight(height_LowHills);
/* 115 */   public static final BiomeGenBase roofedForest = (new BiomeGenForest(29, 3)).setColor(4215066).setBiomeName("Roofed Forest");
/* 116 */   public static final BiomeGenBase coldTaiga = (new BiomeGenTaiga(30, 0)).setColor(3233098).setBiomeName("Cold Taiga").setFillerBlockMetadata(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_MidPlains).func_150563_c(16777215);
/* 117 */   public static final BiomeGenBase coldTaigaHills = (new BiomeGenTaiga(31, 0)).setColor(2375478).setBiomeName("Cold Taiga Hills").setFillerBlockMetadata(5159473).setEnableSnow().setTemperatureRainfall(-0.5F, 0.4F).setHeight(height_LowHills).func_150563_c(16777215);
/* 118 */   public static final BiomeGenBase megaTaiga = (new BiomeGenTaiga(32, 1)).setColor(5858897).setBiomeName("Mega Taiga").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_MidPlains);
/* 119 */   public static final BiomeGenBase megaTaigaHills = (new BiomeGenTaiga(33, 1)).setColor(4542270).setBiomeName("Mega Taiga Hills").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.3F, 0.8F).setHeight(height_LowHills);
/* 120 */   public static final BiomeGenBase extremeHillsPlus = (new BiomeGenHills(34, true)).setColor(5271632).setBiomeName("Extreme Hills+").setHeight(height_MidHills).setTemperatureRainfall(0.2F, 0.3F);
/* 121 */   public static final BiomeGenBase savanna = (new BiomeGenSavanna(35)).setColor(12431967).setBiomeName("Savanna").setTemperatureRainfall(1.2F, 0.0F).setDisableRain().setHeight(height_LowPlains);
/* 122 */   public static final BiomeGenBase savannaPlateau = (new BiomeGenSavanna(36)).setColor(10984804).setBiomeName("Savanna Plateau").setTemperatureRainfall(1.0F, 0.0F).setDisableRain().setHeight(height_HighPlateaus);
/* 123 */   public static final BiomeGenBase mesa = (new BiomeGenMesa(37, false, false)).setColor(14238997).setBiomeName("Mesa");
/* 124 */   public static final BiomeGenBase mesaPlateau_F = (new BiomeGenMesa(38, false, true)).setColor(11573093).setBiomeName("Mesa Plateau F").setHeight(height_HighPlateaus);
/* 125 */   public static final BiomeGenBase mesaPlateau = (new BiomeGenMesa(39, false, false)).setColor(13274213).setBiomeName("Mesa Plateau").setHeight(height_HighPlateaus);
/* 126 */   public static final BiomeGenBase field_180279_ad = ocean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BiomeGenBase(int p_i1971_1_) {
/* 198 */     this.topBlock = Blocks.grass.getDefaultState();
/* 199 */     this.fillerBlock = Blocks.dirt.getDefaultState();
/* 200 */     this.fillerBlockMetadata = 5169201;
/* 201 */     this.minHeight = height_Default.rootHeight;
/* 202 */     this.maxHeight = height_Default.variation;
/* 203 */     this.temperature = 0.5F;
/* 204 */     this.rainfall = 0.5F;
/* 205 */     this.waterColorMultiplier = 16777215;
/* 206 */     this.spawnableMonsterList = Lists.newArrayList();
/* 207 */     this.spawnableCreatureList = Lists.newArrayList();
/* 208 */     this.spawnableWaterCreatureList = Lists.newArrayList();
/* 209 */     this.spawnableCaveCreatureList = Lists.newArrayList();
/* 210 */     this.enableRain = true;
/* 211 */     this.worldGeneratorTrees = new WorldGenTrees(false);
/* 212 */     this.worldGeneratorBigTree = new WorldGenBigTree(false);
/* 213 */     this.worldGeneratorSwamp = new WorldGenSwamp();
/* 214 */     this.biomeID = p_i1971_1_;
/* 215 */     biomeList[p_i1971_1_] = this;
/* 216 */     this.theBiomeDecorator = createBiomeDecorator();
/* 217 */     this.spawnableCreatureList.add(new SpawnListEntry(EntitySheep.class, 12, 4, 4));
/* 218 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityRabbit.class, 10, 3, 3));
/* 219 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityPig.class, 10, 4, 4));
/* 220 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
/* 221 */     this.spawnableCreatureList.add(new SpawnListEntry(EntityCow.class, 8, 4, 4));
/* 222 */     this.spawnableMonsterList.add(new SpawnListEntry(EntitySpider.class, 100, 4, 4));
/* 223 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityZombie.class, 100, 4, 4));
/* 224 */     this.spawnableMonsterList.add(new SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
/* 225 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityCreeper.class, 100, 4, 4));
/* 226 */     this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 100, 4, 4));
/* 227 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityEnderman.class, 10, 1, 4));
/* 228 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityWitch.class, 5, 1, 1));
/* 229 */     this.spawnableWaterCreatureList.add(new SpawnListEntry(EntitySquid.class, 10, 4, 4));
/* 230 */     this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityBat.class, 10, 8, 8));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BiomeDecorator createBiomeDecorator() {
/* 238 */     return new BiomeDecorator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BiomeGenBase setTemperatureRainfall(float p_76732_1_, float p_76732_2_) {
/* 246 */     if (p_76732_1_ > 0.1F && p_76732_1_ < 0.2F)
/*     */     {
/* 248 */       throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
/*     */     }
/*     */ 
/*     */     
/* 252 */     this.temperature = p_76732_1_;
/* 253 */     this.rainfall = p_76732_2_;
/* 254 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected final BiomeGenBase setHeight(Height p_150570_1_) {
/* 260 */     this.minHeight = p_150570_1_.rootHeight;
/* 261 */     this.maxHeight = p_150570_1_.variation;
/* 262 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BiomeGenBase setDisableRain() {
/* 270 */     this.enableRain = false;
/* 271 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/* 276 */     return (p_150567_1_.nextInt(10) == 0) ? (WorldGenAbstractTree)this.worldGeneratorBigTree : (WorldGenAbstractTree)this.worldGeneratorTrees;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_) {
/* 284 */     return (WorldGenerator)new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFlower.EnumFlowerType pickRandomFlower(Random p_180623_1_, BlockPos p_180623_2_) {
/* 289 */     return (p_180623_1_.nextInt(3) > 0) ? BlockFlower.EnumFlowerType.DANDELION : BlockFlower.EnumFlowerType.POPPY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected BiomeGenBase setEnableSnow() {
/* 297 */     this.enableSnow = true;
/* 298 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase setBiomeName(String p_76735_1_) {
/* 303 */     this.biomeName = p_76735_1_;
/* 304 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase setFillerBlockMetadata(int p_76733_1_) {
/* 309 */     this.fillerBlockMetadata = p_76733_1_;
/* 310 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase setColor(int p_76739_1_) {
/* 315 */     func_150557_a(p_76739_1_, false);
/* 316 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase func_150563_c(int p_150563_1_) {
/* 321 */     this.field_150609_ah = p_150563_1_;
/* 322 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase func_150557_a(int p_150557_1_, boolean p_150557_2_) {
/* 327 */     this.color = p_150557_1_;
/*     */     
/* 329 */     if (p_150557_2_) {
/*     */       
/* 331 */       this.field_150609_ah = (p_150557_1_ & 0xFEFEFE) >> 1;
/*     */     }
/*     */     else {
/*     */       
/* 335 */       this.field_150609_ah = p_150557_1_;
/*     */     } 
/*     */     
/* 338 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSkyColorByTemp(float p_76731_1_) {
/* 346 */     p_76731_1_ /= 3.0F;
/* 347 */     p_76731_1_ = MathHelper.clamp_float(p_76731_1_, -1.0F, 1.0F);
/* 348 */     return Color.getHSBColor(0.62222224F - p_76731_1_ * 0.05F, 0.5F + p_76731_1_ * 0.1F, 1.0F).getRGB();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSpawnableList(EnumCreatureType p_76747_1_) {
/* 356 */     switch (SwitchEnumCreatureType.field_180275_a[p_76747_1_.ordinal()]) {
/*     */       
/*     */       case 1:
/* 359 */         return this.spawnableMonsterList;
/*     */       
/*     */       case 2:
/* 362 */         return this.spawnableCreatureList;
/*     */       
/*     */       case 3:
/* 365 */         return this.spawnableWaterCreatureList;
/*     */       
/*     */       case 4:
/* 368 */         return this.spawnableCaveCreatureList;
/*     */     } 
/*     */     
/* 371 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getEnableSnow() {
/* 380 */     return isSnowyBiome();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSpawnLightningBolt() {
/* 388 */     return isSnowyBiome() ? false : this.enableRain;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHighHumidity() {
/* 396 */     return (this.rainfall > 0.85F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSpawningChance() {
/* 404 */     return 0.1F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getIntRainfall() {
/* 412 */     return (int)(this.rainfall * 65536.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final float getFloatRainfall() {
/* 420 */     return this.rainfall;
/*     */   }
/*     */ 
/*     */   
/*     */   public final float func_180626_a(BlockPos p_180626_1_) {
/* 425 */     if (p_180626_1_.getY() > 64) {
/*     */       
/* 427 */       float var2 = (float)(temperatureNoise.func_151601_a(p_180626_1_.getX() * 1.0D / 8.0D, p_180626_1_.getZ() * 1.0D / 8.0D) * 4.0D);
/* 428 */       return this.temperature - (var2 + p_180626_1_.getY() - 64.0F) * 0.05F / 30.0F;
/*     */     } 
/*     */ 
/*     */     
/* 432 */     return this.temperature;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/* 438 */     this.theBiomeDecorator.func_180292_a(worldIn, p_180624_2_, this, p_180624_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_180627_b(BlockPos p_180627_1_) {
/* 443 */     double var2 = MathHelper.clamp_float(func_180626_a(p_180627_1_), 0.0F, 1.0F);
/* 444 */     double var4 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
/* 445 */     return ColorizerGrass.getGrassColor(var2, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_180625_c(BlockPos p_180625_1_) {
/* 450 */     double var2 = MathHelper.clamp_float(func_180626_a(p_180625_1_), 0.0F, 1.0F);
/* 451 */     double var4 = MathHelper.clamp_float(getFloatRainfall(), 0.0F, 1.0F);
/* 452 */     return ColorizerFoliage.getFoliageColor(var2, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSnowyBiome() {
/* 457 */     return this.enableSnow;
/*     */   }
/*     */ 
/*     */   
/*     */   public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
/* 462 */     func_180628_b(worldIn, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void func_180628_b(World worldIn, Random p_180628_2_, ChunkPrimer p_180628_3_, int p_180628_4_, int p_180628_5_, double p_180628_6_) {
/* 467 */     boolean var8 = true;
/* 468 */     IBlockState var9 = this.topBlock;
/* 469 */     IBlockState var10 = this.fillerBlock;
/* 470 */     int var11 = -1;
/* 471 */     int var12 = (int)(p_180628_6_ / 3.0D + 3.0D + p_180628_2_.nextDouble() * 0.25D);
/* 472 */     int var13 = p_180628_4_ & 0xF;
/* 473 */     int var14 = p_180628_5_ & 0xF;
/*     */     
/* 475 */     for (int var15 = 255; var15 >= 0; var15--) {
/*     */       
/* 477 */       if (var15 <= p_180628_2_.nextInt(5)) {
/*     */         
/* 479 */         p_180628_3_.setBlockState(var14, var15, var13, Blocks.bedrock.getDefaultState());
/*     */       }
/*     */       else {
/*     */         
/* 483 */         IBlockState var16 = p_180628_3_.getBlockState(var14, var15, var13);
/*     */         
/* 485 */         if (var16.getBlock().getMaterial() == Material.air) {
/*     */           
/* 487 */           var11 = -1;
/*     */         }
/* 489 */         else if (var16.getBlock() == Blocks.stone) {
/*     */           
/* 491 */           if (var11 == -1) {
/*     */             
/* 493 */             if (var12 <= 0) {
/*     */               
/* 495 */               var9 = null;
/* 496 */               var10 = Blocks.stone.getDefaultState();
/*     */             }
/* 498 */             else if (var15 >= 59 && var15 <= 64) {
/*     */               
/* 500 */               var9 = this.topBlock;
/* 501 */               var10 = this.fillerBlock;
/*     */             } 
/*     */             
/* 504 */             if (var15 < 63 && (var9 == null || var9.getBlock().getMaterial() == Material.air))
/*     */             {
/* 506 */               if (func_180626_a(new BlockPos(p_180628_4_, var15, p_180628_5_)) < 0.15F) {
/*     */                 
/* 508 */                 var9 = Blocks.ice.getDefaultState();
/*     */               }
/*     */               else {
/*     */                 
/* 512 */                 var9 = Blocks.water.getDefaultState();
/*     */               } 
/*     */             }
/*     */             
/* 516 */             var11 = var12;
/*     */             
/* 518 */             if (var15 >= 62)
/*     */             {
/* 520 */               p_180628_3_.setBlockState(var14, var15, var13, var9);
/*     */             }
/* 522 */             else if (var15 < 56 - var12)
/*     */             {
/* 524 */               var9 = null;
/* 525 */               var10 = Blocks.stone.getDefaultState();
/* 526 */               p_180628_3_.setBlockState(var14, var15, var13, Blocks.gravel.getDefaultState());
/*     */             }
/*     */             else
/*     */             {
/* 530 */               p_180628_3_.setBlockState(var14, var15, var13, var10);
/*     */             }
/*     */           
/* 533 */           } else if (var11 > 0) {
/*     */             
/* 535 */             var11--;
/* 536 */             p_180628_3_.setBlockState(var14, var15, var13, var10);
/*     */             
/* 538 */             if (var11 == 0 && var10.getBlock() == Blocks.sand) {
/*     */               
/* 540 */               var11 = p_180628_2_.nextInt(4) + Math.max(0, var15 - 63);
/* 541 */               var10 = (var10.getValue((IProperty)BlockSand.VARIANT_PROP) == BlockSand.EnumType.RED_SAND) ? Blocks.red_sandstone.getDefaultState() : Blocks.sandstone.getDefaultState();
/*     */             } 
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
/*     */   
/*     */   protected BiomeGenBase createMutation() {
/* 555 */     return createMutatedBiome(this.biomeID + 128);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BiomeGenBase createMutatedBiome(int p_180277_1_) {
/* 560 */     return new BiomeGenMutated(p_180277_1_, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Class getBiomeClass() {
/* 565 */     return getClass();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEqualTo(BiomeGenBase p_150569_1_) {
/* 573 */     return (p_150569_1_ == this) ? true : ((p_150569_1_ == null) ? false : ((getBiomeClass() == p_150569_1_.getBiomeClass())));
/*     */   }
/*     */ 
/*     */   
/*     */   public TempCategory getTempCategory() {
/* 578 */     return (this.temperature < 0.2D) ? TempCategory.COLD : ((this.temperature < 1.0D) ? TempCategory.MEDIUM : TempCategory.WARM);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BiomeGenBase[] getBiomeGenArray() {
/* 583 */     return biomeList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static BiomeGenBase getBiome(int p_150568_0_) {
/* 591 */     return getBiomeFromBiomeList(p_150568_0_, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BiomeGenBase getBiomeFromBiomeList(int p_180276_0_, BiomeGenBase p_180276_1_) {
/* 596 */     if (p_180276_0_ >= 0 && p_180276_0_ <= biomeList.length) {
/*     */       
/* 598 */       BiomeGenBase var2 = biomeList[p_180276_0_];
/* 599 */       return (var2 == null) ? p_180276_1_ : var2;
/*     */     } 
/*     */ 
/*     */     
/* 603 */     logger.warn("Biome ID is out of bounds: " + p_180276_0_ + ", defaulting to 0 (Ocean)");
/* 604 */     return ocean;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 610 */     plains.createMutation();
/* 611 */     desert.createMutation();
/* 612 */     forest.createMutation();
/* 613 */     taiga.createMutation();
/* 614 */     swampland.createMutation();
/* 615 */     icePlains.createMutation();
/* 616 */     jungle.createMutation();
/* 617 */     jungleEdge.createMutation();
/* 618 */     coldTaiga.createMutation();
/* 619 */     savanna.createMutation();
/* 620 */     savannaPlateau.createMutation();
/* 621 */     mesa.createMutation();
/* 622 */     mesaPlateau_F.createMutation();
/* 623 */     mesaPlateau.createMutation();
/* 624 */     birchForest.createMutation();
/* 625 */     birchForestHills.createMutation();
/* 626 */     roofedForest.createMutation();
/* 627 */     megaTaiga.createMutation();
/* 628 */     extremeHills.createMutation();
/* 629 */     extremeHillsPlus.createMutation();
/* 630 */     megaTaiga.createMutatedBiome(megaTaigaHills.biomeID + 128).setBiomeName("Redwood Taiga Hills M");
/* 631 */     BiomeGenBase[] var0 = biomeList;
/* 632 */     int var1 = var0.length;
/*     */     
/* 634 */     for (int var2 = 0; var2 < var1; var2++) {
/*     */       
/* 636 */       BiomeGenBase var3 = var0[var2];
/*     */       
/* 638 */       if (var3 != null) {
/*     */         
/* 640 */         if (field_180278_o.containsKey(var3.biomeName))
/*     */         {
/* 642 */           throw new Error("Biome \"" + var3.biomeName + "\" is defined as both ID " + ((BiomeGenBase)field_180278_o.get(var3.biomeName)).biomeID + " and " + var3.biomeID);
/*     */         }
/*     */         
/* 645 */         field_180278_o.put(var3.biomeName, var3);
/*     */         
/* 647 */         if (var3.biomeID < 128)
/*     */         {
/* 649 */           explorationBiomesList.add(var3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 654 */     explorationBiomesList.remove(hell);
/* 655 */     explorationBiomesList.remove(sky);
/* 656 */     explorationBiomesList.remove(frozenOcean);
/* 657 */     explorationBiomesList.remove(extremeHillsEdge);
/* 658 */   } protected static final NoiseGeneratorPerlin temperatureNoise = new NoiseGeneratorPerlin(new Random(1234L), 1);
/* 659 */   protected static final NoiseGeneratorPerlin field_180281_af = new NoiseGeneratorPerlin(new Random(2345L), 1);
/* 660 */   protected static final WorldGenDoublePlant field_180280_ag = new WorldGenDoublePlant(); public String biomeName; public int color; public int field_150609_ah; public IBlockState topBlock; public IBlockState fillerBlock; public int fillerBlockMetadata; public float minHeight; public float maxHeight; public float temperature; public float rainfall; public int waterColorMultiplier; public BiomeDecorator theBiomeDecorator; protected List spawnableMonsterList; protected List spawnableCreatureList; protected List spawnableWaterCreatureList; protected List spawnableCaveCreatureList; protected boolean enableSnow;
/*     */   protected boolean enableRain;
/*     */   public final int biomeID;
/*     */   protected WorldGenTrees worldGeneratorTrees;
/*     */   protected WorldGenBigTree worldGeneratorBigTree;
/*     */   protected WorldGenSwamp worldGeneratorSwamp;
/*     */   private static final String __OBFID = "CL_00000158";
/*     */   
/*     */   public static class Height { public float rootHeight;
/*     */     
/*     */     public Height(float p_i45371_1_, float p_i45371_2_) {
/* 671 */       this.rootHeight = p_i45371_1_;
/* 672 */       this.variation = p_i45371_2_;
/*     */     }
/*     */     public float variation; private static final String __OBFID = "CL_00000159";
/*     */     
/*     */     public Height attenuate() {
/* 677 */       return new Height(this.rootHeight * 0.8F, this.variation * 0.6F);
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class SpawnListEntry
/*     */     extends WeightedRandom.Item
/*     */   {
/*     */     public Class entityClass;
/*     */     public int minGroupCount;
/*     */     public int maxGroupCount;
/*     */     private static final String __OBFID = "CL_00000161";
/*     */     
/*     */     public SpawnListEntry(Class p_i1970_1_, int p_i1970_2_, int p_i1970_3_, int p_i1970_4_) {
/* 690 */       super(p_i1970_2_);
/* 691 */       this.entityClass = p_i1970_1_;
/* 692 */       this.minGroupCount = p_i1970_3_;
/* 693 */       this.maxGroupCount = p_i1970_4_;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 698 */       return String.valueOf(this.entityClass.getSimpleName()) + "*(" + this.minGroupCount + "-" + this.maxGroupCount + "):" + this.itemWeight;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchEnumCreatureType
/*     */   {
/* 704 */     static final int[] field_180275_a = new int[(EnumCreatureType.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002150";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 711 */         field_180275_a[EnumCreatureType.MONSTER.ordinal()] = 1;
/*     */       }
/* 713 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 720 */         field_180275_a[EnumCreatureType.CREATURE.ordinal()] = 2;
/*     */       }
/* 722 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 729 */         field_180275_a[EnumCreatureType.WATER_CREATURE.ordinal()] = 3;
/*     */       }
/* 731 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 738 */         field_180275_a[EnumCreatureType.AMBIENT.ordinal()] = 4;
/*     */       }
/* 740 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum TempCategory
/*     */   {
/* 749 */     OCEAN("OCEAN", 0),
/* 750 */     COLD("COLD", 1),
/* 751 */     MEDIUM("MEDIUM", 2),
/* 752 */     WARM("WARM", 3);
/*     */     
/* 754 */     private static final TempCategory[] $VALUES = new TempCategory[] { OCEAN, COLD, MEDIUM, WARM };
/*     */     private static final String __OBFID = "CL_00000160";
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */