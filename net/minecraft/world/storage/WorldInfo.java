/*     */ package net.minecraft.world.storage;
/*     */ 
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.GameRules;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ import net.minecraft.world.WorldType;
/*     */ 
/*     */ public class WorldInfo {
/*  15 */   public static final EnumDifficulty DEFAULT_DIFFICULTY = EnumDifficulty.NORMAL;
/*     */   
/*     */   private long randomSeed;
/*     */   
/*     */   private WorldType terrainType;
/*     */   
/*     */   private String generatorOptions;
/*     */   
/*     */   private int spawnX;
/*     */   
/*     */   private int spawnY;
/*     */   
/*     */   private int spawnZ;
/*     */   
/*     */   private long totalTime;
/*     */   
/*     */   private long worldTime;
/*     */   
/*     */   private long lastTimePlayed;
/*     */   
/*     */   private long sizeOnDisk;
/*     */   
/*     */   private NBTTagCompound playerTag;
/*     */   
/*     */   private int dimension;
/*     */   
/*     */   private String levelName;
/*     */   
/*     */   private int saveVersion;
/*     */   
/*     */   private int cleanWeatherTime;
/*     */   
/*     */   private boolean raining;
/*     */   
/*     */   private int rainTime;
/*     */   
/*     */   private boolean thundering;
/*     */   
/*     */   private int thunderTime;
/*     */   
/*     */   private WorldSettings.GameType theGameType;
/*     */   
/*     */   private boolean mapFeaturesEnabled;
/*     */   
/*     */   private boolean hardcore;
/*     */   
/*     */   private boolean allowCommands;
/*     */   
/*     */   private boolean initialized;
/*     */   
/*     */   private EnumDifficulty difficulty;
/*     */   
/*     */   private boolean difficultyLocked;
/*     */   
/*     */   private double borderCenterX;
/*     */   
/*     */   private double borderCenterZ;
/*     */   
/*     */   private double borderSize;
/*     */   
/*     */   private long borderSizeLerpTime;
/*     */   
/*     */   private double borderSizeLerpTarget;
/*     */   
/*     */   private double borderSafeZone;
/*     */   
/*     */   private double borderDamagePerBlock;
/*     */   
/*     */   private int borderWarningDistance;
/*     */   
/*     */   private int borderWarningTime;
/*     */   
/*     */   private GameRules theGameRules;
/*     */   
/*     */   private static final String __OBFID = "CL_00000587";
/*     */   
/*     */   protected WorldInfo() {
/*  92 */     this.terrainType = WorldType.DEFAULT;
/*  93 */     this.generatorOptions = "";
/*  94 */     this.borderCenterX = 0.0D;
/*  95 */     this.borderCenterZ = 0.0D;
/*  96 */     this.borderSize = 6.0E7D;
/*  97 */     this.borderSizeLerpTime = 0L;
/*  98 */     this.borderSizeLerpTarget = 0.0D;
/*  99 */     this.borderSafeZone = 5.0D;
/* 100 */     this.borderDamagePerBlock = 0.2D;
/* 101 */     this.borderWarningDistance = 5;
/* 102 */     this.borderWarningTime = 15;
/* 103 */     this.theGameRules = new GameRules();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldInfo(NBTTagCompound nbt) {
/* 108 */     this.terrainType = WorldType.DEFAULT;
/* 109 */     this.generatorOptions = "";
/* 110 */     this.borderCenterX = 0.0D;
/* 111 */     this.borderCenterZ = 0.0D;
/* 112 */     this.borderSize = 6.0E7D;
/* 113 */     this.borderSizeLerpTime = 0L;
/* 114 */     this.borderSizeLerpTarget = 0.0D;
/* 115 */     this.borderSafeZone = 5.0D;
/* 116 */     this.borderDamagePerBlock = 0.2D;
/* 117 */     this.borderWarningDistance = 5;
/* 118 */     this.borderWarningTime = 15;
/* 119 */     this.theGameRules = new GameRules();
/* 120 */     this.randomSeed = nbt.getLong("RandomSeed");
/*     */     
/* 122 */     if (nbt.hasKey("generatorName", 8)) {
/*     */       
/* 124 */       String var2 = nbt.getString("generatorName");
/* 125 */       this.terrainType = WorldType.parseWorldType(var2);
/*     */       
/* 127 */       if (this.terrainType == null) {
/*     */         
/* 129 */         this.terrainType = WorldType.DEFAULT;
/*     */       }
/* 131 */       else if (this.terrainType.isVersioned()) {
/*     */         
/* 133 */         int var3 = 0;
/*     */         
/* 135 */         if (nbt.hasKey("generatorVersion", 99))
/*     */         {
/* 137 */           var3 = nbt.getInteger("generatorVersion");
/*     */         }
/*     */         
/* 140 */         this.terrainType = this.terrainType.getWorldTypeForGeneratorVersion(var3);
/*     */       } 
/*     */       
/* 143 */       if (nbt.hasKey("generatorOptions", 8))
/*     */       {
/* 145 */         this.generatorOptions = nbt.getString("generatorOptions");
/*     */       }
/*     */     } 
/*     */     
/* 149 */     this.theGameType = WorldSettings.GameType.getByID(nbt.getInteger("GameType"));
/*     */     
/* 151 */     if (nbt.hasKey("MapFeatures", 99)) {
/*     */       
/* 153 */       this.mapFeaturesEnabled = nbt.getBoolean("MapFeatures");
/*     */     }
/*     */     else {
/*     */       
/* 157 */       this.mapFeaturesEnabled = true;
/*     */     } 
/*     */     
/* 160 */     this.spawnX = nbt.getInteger("SpawnX");
/* 161 */     this.spawnY = nbt.getInteger("SpawnY");
/* 162 */     this.spawnZ = nbt.getInteger("SpawnZ");
/* 163 */     this.totalTime = nbt.getLong("Time");
/*     */     
/* 165 */     if (nbt.hasKey("DayTime", 99)) {
/*     */       
/* 167 */       this.worldTime = nbt.getLong("DayTime");
/*     */     }
/*     */     else {
/*     */       
/* 171 */       this.worldTime = this.totalTime;
/*     */     } 
/*     */     
/* 174 */     this.lastTimePlayed = nbt.getLong("LastPlayed");
/* 175 */     this.sizeOnDisk = nbt.getLong("SizeOnDisk");
/* 176 */     this.levelName = nbt.getString("LevelName");
/* 177 */     this.saveVersion = nbt.getInteger("version");
/* 178 */     this.cleanWeatherTime = nbt.getInteger("clearWeatherTime");
/* 179 */     this.rainTime = nbt.getInteger("rainTime");
/* 180 */     this.raining = nbt.getBoolean("raining");
/* 181 */     this.thunderTime = nbt.getInteger("thunderTime");
/* 182 */     this.thundering = nbt.getBoolean("thundering");
/* 183 */     this.hardcore = nbt.getBoolean("hardcore");
/*     */     
/* 185 */     if (nbt.hasKey("initialized", 99)) {
/*     */       
/* 187 */       this.initialized = nbt.getBoolean("initialized");
/*     */     }
/*     */     else {
/*     */       
/* 191 */       this.initialized = true;
/*     */     } 
/*     */     
/* 194 */     if (nbt.hasKey("allowCommands", 99)) {
/*     */       
/* 196 */       this.allowCommands = nbt.getBoolean("allowCommands");
/*     */     }
/*     */     else {
/*     */       
/* 200 */       this.allowCommands = (this.theGameType == WorldSettings.GameType.CREATIVE);
/*     */     } 
/*     */     
/* 203 */     if (nbt.hasKey("Player", 10)) {
/*     */       
/* 205 */       this.playerTag = nbt.getCompoundTag("Player");
/* 206 */       this.dimension = this.playerTag.getInteger("Dimension");
/*     */     } 
/*     */     
/* 209 */     if (nbt.hasKey("GameRules", 10))
/*     */     {
/* 211 */       this.theGameRules.readGameRulesFromNBT(nbt.getCompoundTag("GameRules"));
/*     */     }
/*     */     
/* 214 */     if (nbt.hasKey("Difficulty", 99))
/*     */     {
/* 216 */       this.difficulty = EnumDifficulty.getDifficultyEnum(nbt.getByte("Difficulty"));
/*     */     }
/*     */     
/* 219 */     if (nbt.hasKey("DifficultyLocked", 1))
/*     */     {
/* 221 */       this.difficultyLocked = nbt.getBoolean("DifficultyLocked");
/*     */     }
/*     */     
/* 224 */     if (nbt.hasKey("BorderCenterX", 99))
/*     */     {
/* 226 */       this.borderCenterX = nbt.getDouble("BorderCenterX");
/*     */     }
/*     */     
/* 229 */     if (nbt.hasKey("BorderCenterZ", 99))
/*     */     {
/* 231 */       this.borderCenterZ = nbt.getDouble("BorderCenterZ");
/*     */     }
/*     */     
/* 234 */     if (nbt.hasKey("BorderSize", 99))
/*     */     {
/* 236 */       this.borderSize = nbt.getDouble("BorderSize");
/*     */     }
/*     */     
/* 239 */     if (nbt.hasKey("BorderSizeLerpTime", 99))
/*     */     {
/* 241 */       this.borderSizeLerpTime = nbt.getLong("BorderSizeLerpTime");
/*     */     }
/*     */     
/* 244 */     if (nbt.hasKey("BorderSizeLerpTarget", 99))
/*     */     {
/* 246 */       this.borderSizeLerpTarget = nbt.getDouble("BorderSizeLerpTarget");
/*     */     }
/*     */     
/* 249 */     if (nbt.hasKey("BorderSafeZone", 99))
/*     */     {
/* 251 */       this.borderSafeZone = nbt.getDouble("BorderSafeZone");
/*     */     }
/*     */     
/* 254 */     if (nbt.hasKey("BorderDamagePerBlock", 99))
/*     */     {
/* 256 */       this.borderDamagePerBlock = nbt.getDouble("BorderDamagePerBlock");
/*     */     }
/*     */     
/* 259 */     if (nbt.hasKey("BorderWarningBlocks", 99))
/*     */     {
/* 261 */       this.borderWarningDistance = nbt.getInteger("BorderWarningBlocks");
/*     */     }
/*     */     
/* 264 */     if (nbt.hasKey("BorderWarningTime", 99))
/*     */     {
/* 266 */       this.borderWarningTime = nbt.getInteger("BorderWarningTime");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldInfo(WorldSettings settings, String name) {
/* 272 */     this.terrainType = WorldType.DEFAULT;
/* 273 */     this.generatorOptions = "";
/* 274 */     this.borderCenterX = 0.0D;
/* 275 */     this.borderCenterZ = 0.0D;
/* 276 */     this.borderSize = 6.0E7D;
/* 277 */     this.borderSizeLerpTime = 0L;
/* 278 */     this.borderSizeLerpTarget = 0.0D;
/* 279 */     this.borderSafeZone = 5.0D;
/* 280 */     this.borderDamagePerBlock = 0.2D;
/* 281 */     this.borderWarningDistance = 5;
/* 282 */     this.borderWarningTime = 15;
/* 283 */     this.theGameRules = new GameRules();
/* 284 */     populateFromWorldSettings(settings);
/* 285 */     this.levelName = name;
/* 286 */     this.difficulty = DEFAULT_DIFFICULTY;
/* 287 */     this.initialized = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void populateFromWorldSettings(WorldSettings settings) {
/* 292 */     this.randomSeed = settings.getSeed();
/* 293 */     this.theGameType = settings.getGameType();
/* 294 */     this.mapFeaturesEnabled = settings.isMapFeaturesEnabled();
/* 295 */     this.hardcore = settings.getHardcoreEnabled();
/* 296 */     this.terrainType = settings.getTerrainType();
/* 297 */     this.generatorOptions = settings.getWorldName();
/* 298 */     this.allowCommands = settings.areCommandsAllowed();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldInfo(WorldInfo p_i2159_1_) {
/* 303 */     this.terrainType = WorldType.DEFAULT;
/* 304 */     this.generatorOptions = "";
/* 305 */     this.borderCenterX = 0.0D;
/* 306 */     this.borderCenterZ = 0.0D;
/* 307 */     this.borderSize = 6.0E7D;
/* 308 */     this.borderSizeLerpTime = 0L;
/* 309 */     this.borderSizeLerpTarget = 0.0D;
/* 310 */     this.borderSafeZone = 5.0D;
/* 311 */     this.borderDamagePerBlock = 0.2D;
/* 312 */     this.borderWarningDistance = 5;
/* 313 */     this.borderWarningTime = 15;
/* 314 */     this.theGameRules = new GameRules();
/* 315 */     this.randomSeed = p_i2159_1_.randomSeed;
/* 316 */     this.terrainType = p_i2159_1_.terrainType;
/* 317 */     this.generatorOptions = p_i2159_1_.generatorOptions;
/* 318 */     this.theGameType = p_i2159_1_.theGameType;
/* 319 */     this.mapFeaturesEnabled = p_i2159_1_.mapFeaturesEnabled;
/* 320 */     this.spawnX = p_i2159_1_.spawnX;
/* 321 */     this.spawnY = p_i2159_1_.spawnY;
/* 322 */     this.spawnZ = p_i2159_1_.spawnZ;
/* 323 */     this.totalTime = p_i2159_1_.totalTime;
/* 324 */     this.worldTime = p_i2159_1_.worldTime;
/* 325 */     this.lastTimePlayed = p_i2159_1_.lastTimePlayed;
/* 326 */     this.sizeOnDisk = p_i2159_1_.sizeOnDisk;
/* 327 */     this.playerTag = p_i2159_1_.playerTag;
/* 328 */     this.dimension = p_i2159_1_.dimension;
/* 329 */     this.levelName = p_i2159_1_.levelName;
/* 330 */     this.saveVersion = p_i2159_1_.saveVersion;
/* 331 */     this.rainTime = p_i2159_1_.rainTime;
/* 332 */     this.raining = p_i2159_1_.raining;
/* 333 */     this.thunderTime = p_i2159_1_.thunderTime;
/* 334 */     this.thundering = p_i2159_1_.thundering;
/* 335 */     this.hardcore = p_i2159_1_.hardcore;
/* 336 */     this.allowCommands = p_i2159_1_.allowCommands;
/* 337 */     this.initialized = p_i2159_1_.initialized;
/* 338 */     this.theGameRules = p_i2159_1_.theGameRules;
/* 339 */     this.difficulty = p_i2159_1_.difficulty;
/* 340 */     this.difficultyLocked = p_i2159_1_.difficultyLocked;
/* 341 */     this.borderCenterX = p_i2159_1_.borderCenterX;
/* 342 */     this.borderCenterZ = p_i2159_1_.borderCenterZ;
/* 343 */     this.borderSize = p_i2159_1_.borderSize;
/* 344 */     this.borderSizeLerpTime = p_i2159_1_.borderSizeLerpTime;
/* 345 */     this.borderSizeLerpTarget = p_i2159_1_.borderSizeLerpTarget;
/* 346 */     this.borderSafeZone = p_i2159_1_.borderSafeZone;
/* 347 */     this.borderDamagePerBlock = p_i2159_1_.borderDamagePerBlock;
/* 348 */     this.borderWarningTime = p_i2159_1_.borderWarningTime;
/* 349 */     this.borderWarningDistance = p_i2159_1_.borderWarningDistance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getNBTTagCompound() {
/* 357 */     NBTTagCompound var1 = new NBTTagCompound();
/* 358 */     updateTagCompound(var1, this.playerTag);
/* 359 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound cloneNBTCompound(NBTTagCompound nbt) {
/* 367 */     NBTTagCompound var2 = new NBTTagCompound();
/* 368 */     updateTagCompound(var2, nbt);
/* 369 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateTagCompound(NBTTagCompound nbt, NBTTagCompound playerNbt) {
/* 374 */     nbt.setLong("RandomSeed", this.randomSeed);
/* 375 */     nbt.setString("generatorName", this.terrainType.getWorldTypeName());
/* 376 */     nbt.setInteger("generatorVersion", this.terrainType.getGeneratorVersion());
/* 377 */     nbt.setString("generatorOptions", this.generatorOptions);
/* 378 */     nbt.setInteger("GameType", this.theGameType.getID());
/* 379 */     nbt.setBoolean("MapFeatures", this.mapFeaturesEnabled);
/* 380 */     nbt.setInteger("SpawnX", this.spawnX);
/* 381 */     nbt.setInteger("SpawnY", this.spawnY);
/* 382 */     nbt.setInteger("SpawnZ", this.spawnZ);
/* 383 */     nbt.setLong("Time", this.totalTime);
/* 384 */     nbt.setLong("DayTime", this.worldTime);
/* 385 */     nbt.setLong("SizeOnDisk", this.sizeOnDisk);
/* 386 */     nbt.setLong("LastPlayed", MinecraftServer.getCurrentTimeMillis());
/* 387 */     nbt.setString("LevelName", this.levelName);
/* 388 */     nbt.setInteger("version", this.saveVersion);
/* 389 */     nbt.setInteger("clearWeatherTime", this.cleanWeatherTime);
/* 390 */     nbt.setInteger("rainTime", this.rainTime);
/* 391 */     nbt.setBoolean("raining", this.raining);
/* 392 */     nbt.setInteger("thunderTime", this.thunderTime);
/* 393 */     nbt.setBoolean("thundering", this.thundering);
/* 394 */     nbt.setBoolean("hardcore", this.hardcore);
/* 395 */     nbt.setBoolean("allowCommands", this.allowCommands);
/* 396 */     nbt.setBoolean("initialized", this.initialized);
/* 397 */     nbt.setDouble("BorderCenterX", this.borderCenterX);
/* 398 */     nbt.setDouble("BorderCenterZ", this.borderCenterZ);
/* 399 */     nbt.setDouble("BorderSize", this.borderSize);
/* 400 */     nbt.setLong("BorderSizeLerpTime", this.borderSizeLerpTime);
/* 401 */     nbt.setDouble("BorderSafeZone", this.borderSafeZone);
/* 402 */     nbt.setDouble("BorderDamagePerBlock", this.borderDamagePerBlock);
/* 403 */     nbt.setDouble("BorderSizeLerpTarget", this.borderSizeLerpTarget);
/* 404 */     nbt.setDouble("BorderWarningBlocks", this.borderWarningDistance);
/* 405 */     nbt.setDouble("BorderWarningTime", this.borderWarningTime);
/*     */     
/* 407 */     if (this.difficulty != null)
/*     */     {
/* 409 */       nbt.setByte("Difficulty", (byte)this.difficulty.getDifficultyId());
/*     */     }
/*     */     
/* 412 */     nbt.setBoolean("DifficultyLocked", this.difficultyLocked);
/* 413 */     nbt.setTag("GameRules", (NBTBase)this.theGameRules.writeGameRulesToNBT());
/*     */     
/* 415 */     if (playerNbt != null)
/*     */     {
/* 417 */       nbt.setTag("Player", (NBTBase)playerNbt);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSeed() {
/* 426 */     return this.randomSeed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpawnX() {
/* 434 */     return this.spawnX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpawnY() {
/* 442 */     return this.spawnY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpawnZ() {
/* 450 */     return this.spawnZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getWorldTotalTime() {
/* 455 */     return this.totalTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getWorldTime() {
/* 463 */     return this.worldTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSizeOnDisk() {
/* 468 */     return this.sizeOnDisk;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getPlayerNBTTagCompound() {
/* 476 */     return this.playerTag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnX(int p_76058_1_) {
/* 484 */     this.spawnX = p_76058_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnY(int p_76056_1_) {
/* 492 */     this.spawnY = p_76056_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawnZ(int p_76087_1_) {
/* 500 */     this.spawnZ = p_76087_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void incrementTotalWorldTime(long p_82572_1_) {
/* 505 */     this.totalTime = p_82572_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldTime(long p_76068_1_) {
/* 513 */     this.worldTime = p_76068_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSpawn(BlockPos spawnPoint) {
/* 518 */     this.spawnX = spawnPoint.getX();
/* 519 */     this.spawnY = spawnPoint.getY();
/* 520 */     this.spawnZ = spawnPoint.getZ();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWorldName() {
/* 528 */     return this.levelName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorldName(String p_76062_1_) {
/* 533 */     this.levelName = p_76062_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSaveVersion() {
/* 541 */     return this.saveVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSaveVersion(int p_76078_1_) {
/* 549 */     this.saveVersion = p_76078_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLastTimePlayed() {
/* 557 */     return this.lastTimePlayed;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_176133_A() {
/* 562 */     return this.cleanWeatherTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176142_i(int p_176142_1_) {
/* 567 */     this.cleanWeatherTime = p_176142_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isThundering() {
/* 575 */     return this.thundering;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThundering(boolean p_76069_1_) {
/* 583 */     this.thundering = p_76069_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getThunderTime() {
/* 591 */     return this.thunderTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThunderTime(int p_76090_1_) {
/* 599 */     this.thunderTime = p_76090_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRaining() {
/* 607 */     return this.raining;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRaining(boolean p_76084_1_) {
/* 615 */     this.raining = p_76084_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRainTime() {
/* 623 */     return this.rainTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRainTime(int p_76080_1_) {
/* 631 */     this.rainTime = p_76080_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldSettings.GameType getGameType() {
/* 639 */     return this.theGameType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMapFeaturesEnabled() {
/* 647 */     return this.mapFeaturesEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMapFeaturesEnabled(boolean enabled) {
/* 652 */     this.mapFeaturesEnabled = enabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameType(WorldSettings.GameType type) {
/* 660 */     this.theGameType = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHardcoreModeEnabled() {
/* 668 */     return this.hardcore;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHardcore(boolean hardcoreIn) {
/* 673 */     this.hardcore = hardcoreIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldType getTerrainType() {
/* 678 */     return this.terrainType;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTerrainType(WorldType p_76085_1_) {
/* 683 */     this.terrainType = p_76085_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getGeneratorOptions() {
/* 688 */     return this.generatorOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areCommandsAllowed() {
/* 696 */     return this.allowCommands;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllowCommands(boolean allow) {
/* 701 */     this.allowCommands = allow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInitialized() {
/* 709 */     return this.initialized;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setServerInitialized(boolean initializedIn) {
/* 717 */     this.initialized = initializedIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameRules getGameRulesInstance() {
/* 725 */     return this.theGameRules;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_176120_C() {
/* 730 */     return this.borderCenterX;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_176126_D() {
/* 735 */     return this.borderCenterZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_176137_E() {
/* 740 */     return this.borderSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176145_a(double p_176145_1_) {
/* 745 */     this.borderSize = p_176145_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public long func_176134_F() {
/* 750 */     return this.borderSizeLerpTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176135_e(long p_176135_1_) {
/* 755 */     this.borderSizeLerpTime = p_176135_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_176132_G() {
/* 760 */     return this.borderSizeLerpTarget;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176118_b(double p_176118_1_) {
/* 765 */     this.borderSizeLerpTarget = p_176118_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176141_c(double p_176141_1_) {
/* 770 */     this.borderCenterZ = p_176141_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176124_d(double p_176124_1_) {
/* 775 */     this.borderCenterX = p_176124_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_176138_H() {
/* 780 */     return this.borderSafeZone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176129_e(double p_176129_1_) {
/* 785 */     this.borderSafeZone = p_176129_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_176140_I() {
/* 790 */     return this.borderDamagePerBlock;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176125_f(double p_176125_1_) {
/* 795 */     this.borderDamagePerBlock = p_176125_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_176131_J() {
/* 800 */     return this.borderWarningDistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_176139_K() {
/* 805 */     return this.borderWarningTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176122_j(int p_176122_1_) {
/* 810 */     this.borderWarningDistance = p_176122_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176136_k(int p_176136_1_) {
/* 815 */     this.borderWarningTime = p_176136_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDifficulty getDifficulty() {
/* 820 */     return this.difficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDifficulty(EnumDifficulty newDifficulty) {
/* 825 */     this.difficulty = newDifficulty;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDifficultyLocked() {
/* 830 */     return this.difficultyLocked;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDifficultyLocked(boolean locked) {
/* 835 */     this.difficultyLocked = locked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToCrashReport(CrashReportCategory category) {
/* 843 */     category.addCrashSectionCallable("Level seed", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000588";
/*     */           
/*     */           public String call() {
/* 848 */             return String.valueOf(WorldInfo.this.getSeed());
/*     */           }
/*     */         });
/* 851 */     category.addCrashSectionCallable("Level generator", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000589";
/*     */           
/*     */           public String call() {
/* 856 */             return String.format("ID %02d - %s, ver %d. Features enabled: %b", new Object[] { Integer.valueOf(WorldInfo.access$0(this.this$0).getWorldTypeID()), WorldInfo.access$0(this.this$0).getWorldTypeName(), Integer.valueOf(WorldInfo.access$0(this.this$0).getGeneratorVersion()), Boolean.valueOf(WorldInfo.access$1(this.this$0)) });
/*     */           }
/*     */         });
/* 859 */     category.addCrashSectionCallable("Level generator options", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000590";
/*     */           
/*     */           public String call() {
/* 864 */             return WorldInfo.this.generatorOptions;
/*     */           }
/*     */         });
/* 867 */     category.addCrashSectionCallable("Level spawn location", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000591";
/*     */           
/*     */           public String call() {
/* 872 */             return CrashReportCategory.getCoordinateInfo(WorldInfo.this.spawnX, WorldInfo.this.spawnY, WorldInfo.this.spawnZ);
/*     */           }
/*     */         });
/* 875 */     category.addCrashSectionCallable("Level time", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000592";
/*     */           
/*     */           public String call() {
/* 880 */             return String.format("%d game time, %d day time", new Object[] { Long.valueOf(WorldInfo.access$6(this.this$0)), Long.valueOf(WorldInfo.access$7(this.this$0)) });
/*     */           }
/*     */         });
/* 883 */     category.addCrashSectionCallable("Level dimension", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000593";
/*     */           
/*     */           public String call() {
/* 888 */             return String.valueOf(WorldInfo.this.dimension);
/*     */           }
/*     */         });
/* 891 */     category.addCrashSectionCallable("Level storage version", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000594";
/*     */           
/*     */           public String call() {
/* 896 */             String var1 = "Unknown?";
/*     */ 
/*     */             
/*     */             try {
/* 900 */               switch (WorldInfo.this.saveVersion) {
/*     */                 
/*     */                 case 19132:
/* 903 */                   var1 = "McRegion";
/*     */                   break;
/*     */                 
/*     */                 case 19133:
/* 907 */                   var1 = "Anvil";
/*     */                   break;
/*     */               } 
/* 910 */             } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 915 */             return String.format("0x%05X - %s", new Object[] { Integer.valueOf(WorldInfo.access$9(this.this$0)), var1 });
/*     */           }
/*     */         });
/* 918 */     category.addCrashSectionCallable("Level weather", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000595";
/*     */           
/*     */           public String call() {
/* 923 */             return String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", new Object[] { Integer.valueOf(WorldInfo.access$10(this.this$0)), Boolean.valueOf(WorldInfo.access$11(this.this$0)), Integer.valueOf(WorldInfo.access$12(this.this$0)), Boolean.valueOf(WorldInfo.access$13(this.this$0)) });
/*     */           }
/*     */         });
/* 926 */     category.addCrashSectionCallable("Level game mode", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00000597";
/*     */           
/*     */           public String call() {
/* 931 */             return String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", new Object[] { WorldInfo.access$14(this.this$0).getName(), Integer.valueOf(WorldInfo.access$14(this.this$0).getID()), Boolean.valueOf(WorldInfo.access$15(this.this$0)), Boolean.valueOf(WorldInfo.access$16(this.this$0)) });
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\storage\WorldInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */