/*     */ package optifine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Properties;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class RandomMobsProperties
/*     */ {
/*  11 */   public String name = null;
/*  12 */   public String basePath = null;
/*  13 */   public ResourceLocation[] resourceLocations = null;
/*  14 */   public RandomMobsRule[] rules = null;
/*     */ 
/*     */   
/*     */   public RandomMobsProperties(String path, ResourceLocation[] variants) {
/*  18 */     ConnectedParser cp = new ConnectedParser("RandomMobs");
/*  19 */     this.name = cp.parseName(path);
/*  20 */     this.basePath = cp.parseBasePath(path);
/*  21 */     this.resourceLocations = variants;
/*     */   }
/*     */ 
/*     */   
/*     */   public RandomMobsProperties(Properties props, String path, ResourceLocation baseResLoc) {
/*  26 */     ConnectedParser cp = new ConnectedParser("RandomMobs");
/*  27 */     this.name = cp.parseName(path);
/*  28 */     this.basePath = cp.parseBasePath(path);
/*  29 */     this.rules = parseRules(props, baseResLoc, cp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(ResourceLocation loc, EntityLiving el) {
/*  36 */     if (this.rules != null)
/*     */     {
/*  38 */       for (int randomId = 0; randomId < this.rules.length; randomId++) {
/*     */         
/*  40 */         RandomMobsRule index = this.rules[randomId];
/*     */         
/*  42 */         if (index.matches(el))
/*     */         {
/*  44 */           return index.getTextureLocation(loc, el.randomMobsId);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  49 */     if (this.resourceLocations != null) {
/*     */       
/*  51 */       int randomId = el.randomMobsId;
/*  52 */       int var5 = randomId % this.resourceLocations.length;
/*  53 */       return this.resourceLocations[var5];
/*     */     } 
/*     */ 
/*     */     
/*  57 */     return loc;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private RandomMobsRule[] parseRules(Properties props, ResourceLocation baseResLoc, ConnectedParser cp) {
/*  63 */     ArrayList<RandomMobsRule> list = new ArrayList();
/*  64 */     int count = props.size();
/*     */     
/*  66 */     for (int rules = 0; rules < count; rules++) {
/*     */       
/*  68 */       int index = rules + 1;
/*  69 */       String valSkins = props.getProperty("skins." + index);
/*     */       
/*  71 */       if (valSkins != null) {
/*     */         
/*  73 */         int[] skins = cp.parseIntList(valSkins);
/*  74 */         int[] weights = cp.parseIntList(props.getProperty("weights." + index));
/*  75 */         BiomeGenBase[] biomes = cp.parseBiomes(props.getProperty("biomes." + index));
/*  76 */         RangeListInt heights = cp.parseRangeListInt(props.getProperty("heights." + index));
/*     */         
/*  78 */         if (heights == null)
/*     */         {
/*  80 */           heights = parseMinMaxHeight(props, index);
/*     */         }
/*     */         
/*  83 */         RandomMobsRule rule = new RandomMobsRule(baseResLoc, skins, weights, biomes, heights);
/*  84 */         list.add(rule);
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     RandomMobsRule[] var14 = list.<RandomMobsRule>toArray(new RandomMobsRule[list.size()]);
/*  89 */     return var14;
/*     */   }
/*     */ 
/*     */   
/*     */   private RangeListInt parseMinMaxHeight(Properties props, int index) {
/*  94 */     String minHeightStr = props.getProperty("minHeight." + index);
/*  95 */     String maxHeightStr = props.getProperty("maxHeight." + index);
/*     */     
/*  97 */     if (minHeightStr == null && maxHeightStr == null)
/*     */     {
/*  99 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 103 */     int minHeight = 0;
/*     */     
/* 105 */     if (minHeightStr != null) {
/*     */       
/* 107 */       minHeight = Config.parseInt(minHeightStr, -1);
/*     */       
/* 109 */       if (minHeight < 0) {
/*     */         
/* 111 */         Config.warn("Invalid minHeight: " + minHeightStr);
/* 112 */         return null;
/*     */       } 
/*     */     } 
/*     */     
/* 116 */     int maxHeight = 256;
/*     */     
/* 118 */     if (maxHeightStr != null) {
/*     */       
/* 120 */       maxHeight = Config.parseInt(maxHeightStr, -1);
/*     */       
/* 122 */       if (maxHeight < 0) {
/*     */         
/* 124 */         Config.warn("Invalid maxHeight: " + maxHeightStr);
/* 125 */         return null;
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     if (maxHeight < 0) {
/*     */       
/* 131 */       Config.warn("Invalid minHeight, maxHeight: " + minHeightStr + ", " + maxHeightStr);
/* 132 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 136 */     RangeListInt list = new RangeListInt();
/* 137 */     list.addRange(new RangeInt(minHeight, maxHeight));
/* 138 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid(String path) {
/* 145 */     if (this.resourceLocations == null && this.rules == null) {
/*     */       
/* 147 */       Config.warn("No skins specified: " + path);
/* 148 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 154 */     if (this.rules != null)
/*     */     {
/* 156 */       for (int i = 0; i < this.rules.length; i++) {
/*     */         
/* 158 */         RandomMobsRule loc = this.rules[i];
/*     */         
/* 160 */         if (!loc.isValid(path))
/*     */         {
/* 162 */           return false;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 167 */     if (this.resourceLocations != null)
/*     */     {
/* 169 */       for (int i = 0; i < this.resourceLocations.length; i++) {
/*     */         
/* 171 */         ResourceLocation var4 = this.resourceLocations[i];
/*     */         
/* 173 */         if (!Config.hasResource(var4)) {
/*     */           
/* 175 */           Config.warn("Texture not found: " + var4.getResourcePath());
/* 176 */           return false;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 181 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\RandomMobsProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */