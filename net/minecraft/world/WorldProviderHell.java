/*     */ package net.minecraft.world;
/*     */ 
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.WorldChunkManager;
/*     */ import net.minecraft.world.biome.WorldChunkManagerHell;
/*     */ import net.minecraft.world.border.WorldBorder;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.ChunkProviderHell;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldProviderHell
/*     */   extends WorldProvider
/*     */ {
/*     */   private static final String __OBFID = "CL_00000387";
/*     */   
/*     */   public void registerWorldChunkManager() {
/*  19 */     this.worldChunkMgr = (WorldChunkManager)new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F);
/*  20 */     this.isHellWorld = true;
/*  21 */     this.hasNoSky = true;
/*  22 */     this.dimensionId = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getFogColor(float p_76562_1_, float p_76562_2_) {
/*  30 */     return new Vec3(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateLightBrightnessTable() {
/*  38 */     float var1 = 0.1F;
/*     */     
/*  40 */     for (int var2 = 0; var2 <= 15; var2++) {
/*     */       
/*  42 */       float var3 = 1.0F - var2 / 15.0F;
/*  43 */       this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkProvider createChunkGenerator() {
/*  52 */     return (IChunkProvider)new ChunkProviderHell(this.worldObj, this.worldObj.getWorldInfo().isMapFeaturesEnabled(), this.worldObj.getSeed());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSurfaceWorld() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canCoordinateBeSpawn(int x, int z) {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
/*  76 */     return 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canRespawnHere() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_) {
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDimensionName() {
/* 100 */     return "Nether";
/*     */   }
/*     */ 
/*     */   
/*     */   public String getInternalNameSuffix() {
/* 105 */     return "_nether";
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldBorder getWorldBorder() {
/* 110 */     return new WorldBorder()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002008";
/*     */         
/*     */         public double getCenterX() {
/* 115 */           return super.getCenterX() / 8.0D;
/*     */         }
/*     */         
/*     */         public double getCenterZ() {
/* 119 */           return super.getCenterZ() / 8.0D;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\WorldProviderHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */