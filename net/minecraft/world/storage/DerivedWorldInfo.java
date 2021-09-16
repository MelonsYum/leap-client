/*     */ package net.minecraft.world.storage;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ import net.minecraft.world.WorldType;
/*     */ 
/*     */ 
/*     */ public class DerivedWorldInfo
/*     */   extends WorldInfo
/*     */ {
/*     */   private final WorldInfo theWorldInfo;
/*     */   private static final String __OBFID = "CL_00000584";
/*     */   
/*     */   public DerivedWorldInfo(WorldInfo p_i2145_1_) {
/*  18 */     this.theWorldInfo = p_i2145_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getNBTTagCompound() {
/*  26 */     return this.theWorldInfo.getNBTTagCompound();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound cloneNBTCompound(NBTTagCompound nbt) {
/*  34 */     return this.theWorldInfo.cloneNBTCompound(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  42 */     return this.theWorldInfo.getSeed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpawnX() {
/*  50 */     return this.theWorldInfo.getSpawnX();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpawnY() {
/*  58 */     return this.theWorldInfo.getSpawnY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpawnZ() {
/*  66 */     return this.theWorldInfo.getSpawnZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getWorldTotalTime() {
/*  71 */     return this.theWorldInfo.getWorldTotalTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getWorldTime() {
/*  79 */     return this.theWorldInfo.getWorldTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSizeOnDisk() {
/*  84 */     return this.theWorldInfo.getSizeOnDisk();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getPlayerNBTTagCompound() {
/*  92 */     return this.theWorldInfo.getPlayerNBTTagCompound();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWorldName() {
/* 100 */     return this.theWorldInfo.getWorldName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSaveVersion() {
/* 108 */     return this.theWorldInfo.getSaveVersion();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLastTimePlayed() {
/* 116 */     return this.theWorldInfo.getLastTimePlayed();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isThundering() {
/* 124 */     return this.theWorldInfo.isThundering();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getThunderTime() {
/* 132 */     return this.theWorldInfo.getThunderTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRaining() {
/* 140 */     return this.theWorldInfo.isRaining();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRainTime() {
/* 148 */     return this.theWorldInfo.getRainTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldSettings.GameType getGameType() {
/* 156 */     return this.theWorldInfo.getGameType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnX(int p_76058_1_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnY(int p_76056_1_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnZ(int p_76087_1_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void incrementTotalWorldTime(long p_82572_1_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldTime(long p_76068_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawn(BlockPos spawnPoint) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldName(String p_76062_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSaveVersion(int p_76078_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThundering(boolean p_76069_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThunderTime(int p_76090_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRaining(boolean p_76084_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRainTime(int p_76080_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMapFeaturesEnabled() {
/* 215 */     return this.theWorldInfo.isMapFeaturesEnabled();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHardcoreModeEnabled() {
/* 223 */     return this.theWorldInfo.isHardcoreModeEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldType getTerrainType() {
/* 228 */     return this.theWorldInfo.getTerrainType();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTerrainType(WorldType p_76085_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areCommandsAllowed() {
/* 238 */     return this.theWorldInfo.areCommandsAllowed();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAllowCommands(boolean allow) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInitialized() {
/* 248 */     return this.theWorldInfo.isInitialized();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerInitialized(boolean initializedIn) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameRules getGameRulesInstance() {
/* 261 */     return this.theWorldInfo.getGameRulesInstance();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDifficulty getDifficulty() {
/* 266 */     return this.theWorldInfo.getDifficulty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDifficulty(EnumDifficulty newDifficulty) {}
/*     */   
/*     */   public boolean isDifficultyLocked() {
/* 273 */     return this.theWorldInfo.isDifficultyLocked();
/*     */   }
/*     */   
/*     */   public void setDifficultyLocked(boolean locked) {}
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\storage\DerivedWorldInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */