/*     */ package net.minecraft.world;
/*     */ 
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.WorldChunkManager;
/*     */ import net.minecraft.world.biome.WorldChunkManagerHell;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.ChunkProviderEnd;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldProviderEnd
/*     */   extends WorldProvider
/*     */ {
/*     */   private static final String __OBFID = "CL_00000389";
/*     */   
/*     */   public void registerWorldChunkManager() {
/*  20 */     this.worldChunkMgr = (WorldChunkManager)new WorldChunkManagerHell(BiomeGenBase.sky, 0.0F);
/*  21 */     this.dimensionId = 1;
/*  22 */     this.hasNoSky = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkProvider createChunkGenerator() {
/*  30 */     return (IChunkProvider)new ChunkProviderEnd(this.worldObj, this.worldObj.getSeed());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
/*  38 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] calcSunriseSunsetColors(float p_76560_1_, float p_76560_2_) {
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
/*  54 */     int var3 = 10518688;
/*  55 */     float var4 = MathHelper.cos(p_76562_1_ * 3.1415927F * 2.0F) * 2.0F + 0.5F;
/*  56 */     var4 = MathHelper.clamp_float(var4, 0.0F, 1.0F);
/*  57 */     float var5 = (var3 >> 16 & 0xFF) / 255.0F;
/*  58 */     float var6 = (var3 >> 8 & 0xFF) / 255.0F;
/*  59 */     float var7 = (var3 & 0xFF) / 255.0F;
/*  60 */     var5 *= var4 * 0.0F + 0.15F;
/*  61 */     var6 *= var4 * 0.0F + 0.15F;
/*  62 */     var7 *= var4 * 0.0F + 0.15F;
/*  63 */     return new Vec3(var5, var6, var7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSkyColored() {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRespawnHere() {
/*  76 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSurfaceWorld() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCloudHeight() {
/*  92 */     return 8.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCoordinateBeSpawn(int x, int z) {
/* 100 */     return this.worldObj.getGroundAboveSeaLevel(new BlockPos(x, 0, z)).getMaterial().blocksMovement();
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_177496_h() {
/* 105 */     return new BlockPos(100, 50, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAverageGroundLevel() {
/* 110 */     return 50;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_) {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDimensionName() {
/* 126 */     return "The End";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInternalNameSuffix() {
/* 131 */     return "_end";
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\WorldProviderEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */