/*     */ package net.minecraft.world;
/*     */ 
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.WorldChunkManager;
/*     */ import net.minecraft.world.biome.WorldChunkManagerHell;
/*     */ import net.minecraft.world.border.WorldBorder;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.ChunkProviderDebug;
/*     */ import net.minecraft.world.gen.ChunkProviderFlat;
/*     */ import net.minecraft.world.gen.ChunkProviderGenerate;
/*     */ import net.minecraft.world.gen.FlatGeneratorInfo;
/*     */ 
/*     */ public abstract class WorldProvider
/*     */ {
/*  19 */   public static final float[] moonPhaseFactors = new float[] { 1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F };
/*     */ 
/*     */   
/*     */   protected World worldObj;
/*     */ 
/*     */   
/*     */   private WorldType terrainType;
/*     */ 
/*     */   
/*     */   private String generatorSettings;
/*     */ 
/*     */   
/*     */   protected WorldChunkManager worldChunkMgr;
/*     */ 
/*     */   
/*     */   protected boolean isHellWorld;
/*     */ 
/*     */   
/*     */   protected boolean hasNoSky;
/*     */ 
/*     */   
/*  40 */   protected final float[] lightBrightnessTable = new float[16];
/*     */ 
/*     */   
/*     */   protected int dimensionId;
/*     */ 
/*     */   
/*  46 */   private final float[] colorsSunriseSunset = new float[4];
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000386";
/*     */ 
/*     */ 
/*     */   
/*     */   public final void registerWorld(World worldIn) {
/*  54 */     this.worldObj = worldIn;
/*  55 */     this.terrainType = worldIn.getWorldInfo().getTerrainType();
/*  56 */     this.generatorSettings = worldIn.getWorldInfo().getGeneratorOptions();
/*  57 */     registerWorldChunkManager();
/*  58 */     generateLightBrightnessTable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateLightBrightnessTable() {
/*  66 */     float var1 = 0.0F;
/*     */     
/*  68 */     for (int var2 = 0; var2 <= 15; var2++) {
/*     */       
/*  70 */       float var3 = 1.0F - var2 / 15.0F;
/*  71 */       this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerWorldChunkManager() {
/*  80 */     WorldType var1 = this.worldObj.getWorldInfo().getTerrainType();
/*     */     
/*  82 */     if (var1 == WorldType.FLAT) {
/*     */       
/*  84 */       FlatGeneratorInfo var2 = FlatGeneratorInfo.createFlatGeneratorFromString(this.worldObj.getWorldInfo().getGeneratorOptions());
/*  85 */       this.worldChunkMgr = (WorldChunkManager)new WorldChunkManagerHell(BiomeGenBase.getBiomeFromBiomeList(var2.getBiome(), BiomeGenBase.field_180279_ad), 0.5F);
/*     */     }
/*  87 */     else if (var1 == WorldType.DEBUG_WORLD) {
/*     */       
/*  89 */       this.worldChunkMgr = (WorldChunkManager)new WorldChunkManagerHell(BiomeGenBase.plains, 0.0F);
/*     */     }
/*     */     else {
/*     */       
/*  93 */       this.worldChunkMgr = new WorldChunkManager(this.worldObj);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkProvider createChunkGenerator() {
/* 102 */     return (this.terrainType == WorldType.FLAT) ? (IChunkProvider)new ChunkProviderFlat(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.generatorSettings) : ((this.terrainType == WorldType.DEBUG_WORLD) ? (IChunkProvider)new ChunkProviderDebug(this.worldObj) : ((this.terrainType == WorldType.CUSTOMIZED) ? (IChunkProvider)new ChunkProviderGenerate(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.generatorSettings) : (IChunkProvider)new ChunkProviderGenerate(this.worldObj, this.worldObj.getSeed(), this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.generatorSettings)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCoordinateBeSpawn(int x, int z) {
/* 110 */     return (this.worldObj.getGroundAboveSeaLevel(new BlockPos(x, 0, z)) == Blocks.grass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
/* 118 */     int var4 = (int)(p_76563_1_ % 24000L);
/* 119 */     float var5 = (var4 + p_76563_3_) / 24000.0F - 0.25F;
/*     */     
/* 121 */     if (var5 < 0.0F)
/*     */     {
/* 123 */       var5++;
/*     */     }
/*     */     
/* 126 */     if (var5 > 1.0F)
/*     */     {
/* 128 */       var5--;
/*     */     }
/*     */     
/* 131 */     float var6 = var5;
/* 132 */     var5 = 1.0F - (float)((Math.cos(var5 * Math.PI) + 1.0D) / 2.0D);
/* 133 */     var5 = var6 + (var5 - var6) / 3.0F;
/* 134 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMoonPhase(long p_76559_1_) {
/* 139 */     return (int)(p_76559_1_ / 24000L % 8L + 8L) % 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSurfaceWorld() {
/* 147 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_) {
/* 155 */     float var3 = 0.4F;
/* 156 */     float var4 = MathHelper.cos(p_76560_1_ * 3.1415927F * 2.0F) - 0.0F;
/* 157 */     float var5 = -0.0F;
/*     */     
/* 159 */     if (var4 >= var5 - var3 && var4 <= var5 + var3) {
/*     */       
/* 161 */       float var6 = (var4 - var5) / var3 * 0.5F + 0.5F;
/* 162 */       float var7 = 1.0F - (1.0F - MathHelper.sin(var6 * 3.1415927F)) * 0.99F;
/* 163 */       var7 *= var7;
/* 164 */       this.colorsSunriseSunset[0] = var6 * 0.3F + 0.7F;
/* 165 */       this.colorsSunriseSunset[1] = var6 * var6 * 0.7F + 0.2F;
/* 166 */       this.colorsSunriseSunset[2] = var6 * var6 * 0.0F + 0.2F;
/* 167 */       this.colorsSunriseSunset[3] = var7;
/* 168 */       return this.colorsSunriseSunset;
/*     */     } 
/*     */ 
/*     */     
/* 172 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
/* 181 */     float var3 = MathHelper.cos(p_76562_1_ * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/* 182 */     var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
/* 183 */     float var4 = 0.7529412F;
/* 184 */     float var5 = 0.84705883F;
/* 185 */     float var6 = 1.0F;
/* 186 */     var4 *= var3 * 0.94F + 0.06F;
/* 187 */     var5 *= var3 * 0.94F + 0.06F;
/* 188 */     var6 *= var3 * 0.91F + 0.09F;
/* 189 */     return new Vec3(var4, var5, var6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRespawnHere() {
/* 197 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static WorldProvider getProviderForDimension(int dimension) {
/* 202 */     return (dimension == -1) ? new WorldProviderHell() : ((dimension == 0) ? new WorldProviderSurface() : ((dimension == 1) ? new WorldProviderEnd() : null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCloudHeight() {
/* 210 */     return 128.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSkyColored() {
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_177496_h() {
/* 220 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAverageGroundLevel() {
/* 225 */     return (this.terrainType == WorldType.FLAT) ? 4 : 64;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getVoidFogYFactor() {
/* 235 */     return (this.terrainType == WorldType.FLAT) ? 1.0D : 0.03125D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_) {
/* 243 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getDimensionName();
/*     */ 
/*     */   
/*     */   public abstract String getInternalNameSuffix();
/*     */ 
/*     */   
/*     */   public WorldChunkManager getWorldChunkManager() {
/* 255 */     return this.worldChunkMgr;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177500_n() {
/* 260 */     return this.isHellWorld;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getHasNoSky() {
/* 265 */     return this.hasNoSky;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] getLightBrightnessTable() {
/* 270 */     return this.lightBrightnessTable;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDimensionId() {
/* 275 */     return this.dimensionId;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldBorder getWorldBorder() {
/* 280 */     return new WorldBorder();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\WorldProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */