/*     */ package net.minecraft.world;
/*     */ 
/*     */ import net.minecraft.entity.player.PlayerCapabilities;
/*     */ import net.minecraft.world.storage.WorldInfo;
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
/*     */ public final class WorldSettings
/*     */ {
/*     */   private final long seed;
/*     */   private final GameType theGameType;
/*     */   private final boolean mapFeaturesEnabled;
/*     */   private final boolean hardcoreEnabled;
/*     */   private final WorldType terrainType;
/*     */   private boolean commandsAllowed;
/*     */   private boolean bonusChestEnabled;
/*     */   private String worldName;
/*     */   private static final String __OBFID = "CL_00000147";
/*     */   
/*     */   public WorldSettings(long seedIn, GameType gameType, boolean enableMapFeatures, boolean hardcoreMode, WorldType worldTypeIn) {
/*  33 */     this.worldName = "";
/*  34 */     this.seed = seedIn;
/*  35 */     this.theGameType = gameType;
/*  36 */     this.mapFeaturesEnabled = enableMapFeatures;
/*  37 */     this.hardcoreEnabled = hardcoreMode;
/*  38 */     this.terrainType = worldTypeIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldSettings(WorldInfo info) {
/*  43 */     this(info.getSeed(), info.getGameType(), info.isMapFeaturesEnabled(), info.isHardcoreModeEnabled(), info.getTerrainType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldSettings enableBonusChest() {
/*  51 */     this.bonusChestEnabled = true;
/*  52 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldSettings enableCommands() {
/*  60 */     this.commandsAllowed = true;
/*  61 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldSettings setWorldName(String name) {
/*  66 */     this.worldName = name;
/*  67 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBonusChestEnabled() {
/*  75 */     return this.bonusChestEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  83 */     return this.seed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameType getGameType() {
/*  91 */     return this.theGameType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHardcoreEnabled() {
/*  99 */     return this.hardcoreEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMapFeaturesEnabled() {
/* 107 */     return this.mapFeaturesEnabled;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldType getTerrainType() {
/* 112 */     return this.terrainType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areCommandsAllowed() {
/* 120 */     return this.commandsAllowed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static GameType getGameTypeById(int id) {
/* 128 */     return GameType.getByID(id);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWorldName() {
/* 133 */     return this.worldName;
/*     */   }
/*     */   
/*     */   public enum GameType
/*     */   {
/* 138 */     NOT_SET("NOT_SET", 0, -1, ""),
/* 139 */     SURVIVAL("SURVIVAL", 1, 0, "survival"),
/* 140 */     CREATIVE("CREATIVE", 2, 1, "creative"),
/* 141 */     ADVENTURE("ADVENTURE", 3, 2, "adventure"),
/* 142 */     SPECTATOR("SPECTATOR", 4, 3, "spectator");
/*     */     
/*     */     int id;
/*     */     String name;
/* 146 */     private static final GameType[] $VALUES = new GameType[] { NOT_SET, SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR };
/*     */     
/*     */     private static final String __OBFID = "CL_00000148";
/*     */     
/*     */     GameType(String p_i1956_1_, int p_i1956_2_, int typeId, String nameIn) {
/* 151 */       this.id = typeId;
/* 152 */       this.name = nameIn;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public int getID() {
/* 157 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 162 */       return this.name;
/*     */     }
/*     */ 
/*     */     
/*     */     public void configurePlayerCapabilities(PlayerCapabilities capabilities) {
/* 167 */       if (this == CREATIVE) {
/*     */         
/* 169 */         capabilities.allowFlying = true;
/* 170 */         capabilities.isCreativeMode = true;
/* 171 */         capabilities.disableDamage = true;
/*     */       }
/* 173 */       else if (this == SPECTATOR) {
/*     */         
/* 175 */         capabilities.allowFlying = true;
/* 176 */         capabilities.isCreativeMode = false;
/* 177 */         capabilities.disableDamage = true;
/* 178 */         capabilities.isFlying = true;
/*     */       }
/*     */       else {
/*     */         
/* 182 */         capabilities.allowFlying = false;
/* 183 */         capabilities.isCreativeMode = false;
/* 184 */         capabilities.disableDamage = false;
/* 185 */         capabilities.isFlying = false;
/*     */       } 
/*     */       
/* 188 */       capabilities.allowEdit = !isAdventure();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isAdventure() {
/* 193 */       return !(this != ADVENTURE && this != SPECTATOR);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isCreative() {
/* 198 */       return (this == CREATIVE);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isSurvivalOrAdventure() {
/* 203 */       return !(this != SURVIVAL && this != ADVENTURE);
/*     */     }
/*     */ 
/*     */     
/*     */     public static GameType getByID(int idIn) {
/* 208 */       GameType[] var1 = values();
/* 209 */       int var2 = var1.length;
/*     */       
/* 211 */       for (int var3 = 0; var3 < var2; var3++) {
/*     */         
/* 213 */         GameType var4 = var1[var3];
/*     */         
/* 215 */         if (var4.id == idIn)
/*     */         {
/* 217 */           return var4;
/*     */         }
/*     */       } 
/*     */       
/* 221 */       return SURVIVAL;
/*     */     }
/*     */ 
/*     */     
/*     */     public static GameType getByName(String p_77142_0_) {
/* 226 */       GameType[] var1 = values();
/* 227 */       int var2 = var1.length;
/*     */       
/* 229 */       for (int var3 = 0; var3 < var2; var3++) {
/*     */         
/* 231 */         GameType var4 = var1[var3];
/*     */         
/* 233 */         if (var4.name.equals(p_77142_0_))
/*     */         {
/* 235 */           return var4;
/*     */         }
/*     */       } 
/*     */       
/* 239 */       return SURVIVAL;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\WorldSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */