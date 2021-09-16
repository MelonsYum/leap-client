/*     */ package net.minecraft.world;
/*     */ 
/*     */ 
/*     */ public class WorldType
/*     */ {
/*   6 */   public static final WorldType[] worldTypes = new WorldType[16];
/*     */ 
/*     */   
/*   9 */   public static final WorldType DEFAULT = (new WorldType(0, "default", 1)).setVersioned();
/*     */ 
/*     */   
/*  12 */   public static final WorldType FLAT = new WorldType(1, "flat");
/*     */ 
/*     */   
/*  15 */   public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
/*     */ 
/*     */   
/*  18 */   public static final WorldType AMPLIFIED = (new WorldType(3, "amplified")).setNotificationData();
/*  19 */   public static final WorldType CUSTOMIZED = new WorldType(4, "customized");
/*  20 */   public static final WorldType DEBUG_WORLD = new WorldType(5, "debug_all_block_states");
/*     */ 
/*     */   
/*  23 */   public static final WorldType DEFAULT_1_1 = (new WorldType(8, "default_1_1", 0)).setCanBeCreated(false);
/*     */ 
/*     */   
/*     */   private final int worldTypeId;
/*     */ 
/*     */   
/*     */   private final String worldType;
/*     */ 
/*     */   
/*     */   private final int generatorVersion;
/*     */   
/*     */   private boolean canBeCreated;
/*     */   
/*     */   private boolean isWorldTypeVersioned;
/*     */   
/*     */   private boolean hasNotificationData;
/*     */   
/*     */   private static final String __OBFID = "CL_00000150";
/*     */ 
/*     */   
/*     */   private WorldType(int id, String name) {
/*  44 */     this(id, name, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private WorldType(int id, String name, int version) {
/*  49 */     this.worldType = name;
/*  50 */     this.generatorVersion = version;
/*  51 */     this.canBeCreated = true;
/*  52 */     this.worldTypeId = id;
/*  53 */     worldTypes[id] = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWorldTypeName() {
/*  58 */     return this.worldType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTranslateName() {
/*  66 */     return "generator." + this.worldType;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_151359_c() {
/*  71 */     return String.valueOf(getTranslateName()) + ".info";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGeneratorVersion() {
/*  79 */     return this.generatorVersion;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldType getWorldTypeForGeneratorVersion(int version) {
/*  84 */     return (this == DEFAULT && version == 0) ? DEFAULT_1_1 : this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WorldType setCanBeCreated(boolean enable) {
/*  92 */     this.canBeCreated = enable;
/*  93 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanBeCreated() {
/* 101 */     return this.canBeCreated;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WorldType setVersioned() {
/* 109 */     this.isWorldTypeVersioned = true;
/* 110 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVersioned() {
/* 118 */     return this.isWorldTypeVersioned;
/*     */   }
/*     */ 
/*     */   
/*     */   public static WorldType parseWorldType(String type) {
/* 123 */     for (int var1 = 0; var1 < worldTypes.length; var1++) {
/*     */       
/* 125 */       if (worldTypes[var1] != null && (worldTypes[var1]).worldType.equalsIgnoreCase(type))
/*     */       {
/* 127 */         return worldTypes[var1];
/*     */       }
/*     */     } 
/*     */     
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWorldTypeID() {
/* 136 */     return this.worldTypeId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean showWorldInfoNotice() {
/* 145 */     return this.hasNotificationData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WorldType setNotificationData() {
/* 153 */     this.hasNotificationData = true;
/* 154 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\WorldType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */