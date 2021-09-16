/*     */ package optifine;
/*     */ 
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class RandomMobsRule
/*     */ {
/*   9 */   private ResourceLocation baseResLoc = null;
/*  10 */   private int[] skins = null;
/*  11 */   private ResourceLocation[] resourceLocations = null;
/*  12 */   private int[] weights = null;
/*  13 */   private BiomeGenBase[] biomes = null;
/*  14 */   private RangeListInt heights = null;
/*  15 */   public int[] sumWeights = null;
/*  16 */   public int sumAllWeights = 1;
/*     */ 
/*     */   
/*     */   public RandomMobsRule(ResourceLocation baseResLoc, int[] skins, int[] weights, BiomeGenBase[] biomes, RangeListInt heights) {
/*  20 */     this.baseResLoc = baseResLoc;
/*  21 */     this.skins = skins;
/*  22 */     this.weights = weights;
/*  23 */     this.biomes = biomes;
/*  24 */     this.heights = heights;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid(String path) {
/*  29 */     this.resourceLocations = new ResourceLocation[this.skins.length];
/*  30 */     ResourceLocation locMcp = RandomMobs.getMcpatcherLocation(this.baseResLoc);
/*     */     
/*  32 */     if (locMcp == null) {
/*     */       
/*  34 */       Config.warn("Invalid path: " + this.baseResLoc.getResourcePath());
/*  35 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*     */     int sum;
/*     */ 
/*     */     
/*  42 */     for (sum = 0; sum < this.resourceLocations.length; sum++) {
/*     */       
/*  44 */       int i = this.skins[sum];
/*     */       
/*  46 */       if (i <= 1) {
/*     */         
/*  48 */         this.resourceLocations[sum] = this.baseResLoc;
/*     */       }
/*     */       else {
/*     */         
/*  52 */         ResourceLocation i1 = RandomMobs.getLocationIndexed(locMcp, i);
/*     */         
/*  54 */         if (i1 == null) {
/*     */           
/*  56 */           Config.warn("Invalid path: " + this.baseResLoc.getResourcePath());
/*  57 */           return false;
/*     */         } 
/*     */         
/*  60 */         if (!Config.hasResource(i1)) {
/*     */           
/*  62 */           Config.warn("Texture not found: " + i1.getResourcePath());
/*  63 */           return false;
/*     */         } 
/*     */         
/*  66 */         this.resourceLocations[sum] = i1;
/*     */       } 
/*     */     } 
/*     */     
/*  70 */     if (this.weights != null) {
/*     */ 
/*     */ 
/*     */       
/*  74 */       if (this.weights.length > this.resourceLocations.length) {
/*     */         
/*  76 */         Config.warn("More weights defined than skins, trimming weights: " + path);
/*  77 */         int[] var6 = new int[this.resourceLocations.length];
/*  78 */         System.arraycopy(this.weights, 0, var6, 0, var6.length);
/*  79 */         this.weights = var6;
/*     */       } 
/*     */       
/*  82 */       if (this.weights.length < this.resourceLocations.length) {
/*     */         
/*  84 */         Config.warn("Less weights defined than skins, expanding weights: " + path);
/*  85 */         int[] var6 = new int[this.resourceLocations.length];
/*  86 */         System.arraycopy(this.weights, 0, var6, 0, this.weights.length);
/*  87 */         int j = MathUtils.getAverage(this.weights);
/*     */         
/*  89 */         for (int var7 = this.weights.length; var7 < var6.length; var7++)
/*     */         {
/*  91 */           var6[var7] = j;
/*     */         }
/*     */         
/*  94 */         this.weights = var6;
/*     */       } 
/*     */       
/*  97 */       this.sumWeights = new int[this.weights.length];
/*  98 */       sum = 0;
/*     */       
/* 100 */       for (int i = 0; i < this.weights.length; i++) {
/*     */         
/* 102 */         if (this.weights[i] < 0) {
/*     */           
/* 104 */           Config.warn("Invalid weight: " + this.weights[i]);
/* 105 */           return false;
/*     */         } 
/*     */         
/* 108 */         sum += this.weights[i];
/* 109 */         this.sumWeights[i] = sum;
/*     */       } 
/*     */       
/* 112 */       this.sumAllWeights = sum;
/*     */       
/* 114 */       if (this.sumAllWeights <= 0) {
/*     */         
/* 116 */         Config.warn("Invalid sum of all weights: " + sum);
/* 117 */         this.sumAllWeights = 1;
/*     */       } 
/*     */     } 
/*     */     
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(EntityLiving el) {
/* 127 */     return !Matches.biome(el.spawnBiome, this.biomes) ? false : ((this.heights != null && el.spawnPosition != null) ? this.heights.isInRange(el.spawnPosition.getY()) : true);
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getTextureLocation(ResourceLocation loc, int randomId) {
/* 132 */     int index = 0;
/*     */     
/* 134 */     if (this.weights == null) {
/*     */       
/* 136 */       index = randomId % this.resourceLocations.length;
/*     */     }
/*     */     else {
/*     */       
/* 140 */       int randWeight = randomId % this.sumAllWeights;
/*     */       
/* 142 */       for (int i = 0; i < this.sumWeights.length; i++) {
/*     */         
/* 144 */         if (this.sumWeights[i] > randWeight) {
/*     */           
/* 146 */           index = i;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 152 */     return this.resourceLocations[index];
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\RandomMobsRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */