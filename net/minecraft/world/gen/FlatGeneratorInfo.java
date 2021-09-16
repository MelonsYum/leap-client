/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FlatGeneratorInfo
/*     */ {
/*  19 */   private final List flatLayers = Lists.newArrayList();
/*     */ 
/*     */   
/*  22 */   private final Map worldFeatures = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   private int biomeToUse;
/*     */   
/*     */   private static final String __OBFID = "CL_00000440";
/*     */ 
/*     */   
/*     */   public int getBiome() {
/*  31 */     return this.biomeToUse;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBiome(int p_82647_1_) {
/*  39 */     this.biomeToUse = p_82647_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map getWorldFeatures() {
/*  47 */     return this.worldFeatures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getFlatLayers() {
/*  55 */     return this.flatLayers;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82645_d() {
/*  60 */     int var1 = 0;
/*     */ 
/*     */     
/*  63 */     for (Iterator<FlatLayerInfo> var2 = this.flatLayers.iterator(); var2.hasNext(); var1 += var3.getLayerCount()) {
/*     */       
/*  65 */       FlatLayerInfo var3 = var2.next();
/*  66 */       var3.setMinY(var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  72 */     StringBuilder var1 = new StringBuilder();
/*  73 */     var1.append(3);
/*  74 */     var1.append(";");
/*     */     
/*     */     int var2;
/*  77 */     for (var2 = 0; var2 < this.flatLayers.size(); var2++) {
/*     */       
/*  79 */       if (var2 > 0)
/*     */       {
/*  81 */         var1.append(",");
/*     */       }
/*     */       
/*  84 */       var1.append(((FlatLayerInfo)this.flatLayers.get(var2)).toString());
/*     */     } 
/*     */     
/*  87 */     var1.append(";");
/*  88 */     var1.append(this.biomeToUse);
/*     */     
/*  90 */     if (!this.worldFeatures.isEmpty()) {
/*     */       
/*  92 */       var1.append(";");
/*  93 */       var2 = 0;
/*  94 */       Iterator<Map.Entry> var3 = this.worldFeatures.entrySet().iterator();
/*     */       
/*  96 */       while (var3.hasNext()) {
/*     */         
/*  98 */         Map.Entry var4 = var3.next();
/*     */         
/* 100 */         if (var2++ > 0)
/*     */         {
/* 102 */           var1.append(",");
/*     */         }
/*     */         
/* 105 */         var1.append(((String)var4.getKey()).toLowerCase());
/* 106 */         Map var5 = (Map)var4.getValue();
/*     */         
/* 108 */         if (!var5.isEmpty())
/*     */         {
/* 110 */           var1.append("(");
/* 111 */           int var6 = 0;
/* 112 */           Iterator<Map.Entry> var7 = var5.entrySet().iterator();
/*     */           
/* 114 */           while (var7.hasNext()) {
/*     */             
/* 116 */             Map.Entry var8 = var7.next();
/*     */             
/* 118 */             if (var6++ > 0)
/*     */             {
/* 120 */               var1.append(" ");
/*     */             }
/*     */             
/* 123 */             var1.append((String)var8.getKey());
/* 124 */             var1.append("=");
/* 125 */             var1.append((String)var8.getValue());
/*     */           } 
/*     */           
/* 128 */           var1.append(")");
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 134 */       var1.append(";");
/*     */     } 
/*     */     
/* 137 */     return var1.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static FlatLayerInfo func_180715_a(int p_180715_0_, String p_180715_1_, int p_180715_2_) {
/* 142 */     String[] var3 = (p_180715_0_ >= 3) ? p_180715_1_.split("\\*", 2) : p_180715_1_.split("x", 2);
/* 143 */     int var4 = 1;
/* 144 */     int var5 = 0;
/*     */     
/* 146 */     if (var3.length == 2) {
/*     */       
/*     */       try {
/*     */         
/* 150 */         var4 = Integer.parseInt(var3[0]);
/*     */         
/* 152 */         if (p_180715_2_ + var4 >= 256)
/*     */         {
/* 154 */           var4 = 256 - p_180715_2_;
/*     */         }
/*     */         
/* 157 */         if (var4 < 0)
/*     */         {
/* 159 */           var4 = 0;
/*     */         }
/*     */       }
/* 162 */       catch (Throwable var8) {
/*     */         
/* 164 */         return null;
/*     */       } 
/*     */     }
/*     */     
/* 168 */     Block var6 = null;
/*     */ 
/*     */     
/*     */     try {
/* 172 */       String var7 = var3[var3.length - 1];
/*     */       
/* 174 */       if (p_180715_0_ < 3) {
/*     */         
/* 176 */         var3 = var7.split(":", 2);
/*     */         
/* 178 */         if (var3.length > 1)
/*     */         {
/* 180 */           var5 = Integer.parseInt(var3[1]);
/*     */         }
/*     */         
/* 183 */         var6 = Block.getBlockById(Integer.parseInt(var3[0]));
/*     */       }
/*     */       else {
/*     */         
/* 187 */         var3 = var7.split(":", 3);
/* 188 */         var6 = (var3.length > 1) ? Block.getBlockFromName(String.valueOf(var3[0]) + ":" + var3[1]) : null;
/*     */         
/* 190 */         if (var6 != null) {
/*     */           
/* 192 */           var5 = (var3.length > 2) ? Integer.parseInt(var3[2]) : 0;
/*     */         }
/*     */         else {
/*     */           
/* 196 */           var6 = Block.getBlockFromName(var3[0]);
/*     */           
/* 198 */           if (var6 != null)
/*     */           {
/* 200 */             var5 = (var3.length > 1) ? Integer.parseInt(var3[1]) : 0;
/*     */           }
/*     */         } 
/*     */         
/* 204 */         if (var6 == null)
/*     */         {
/* 206 */           return null;
/*     */         }
/*     */       } 
/*     */       
/* 210 */       if (var6 == Blocks.air)
/*     */       {
/* 212 */         var5 = 0;
/*     */       }
/*     */       
/* 215 */       if (var5 < 0 || var5 > 15)
/*     */       {
/* 217 */         var5 = 0;
/*     */       }
/*     */     }
/* 220 */     catch (Throwable var9) {
/*     */       
/* 222 */       return null;
/*     */     } 
/*     */     
/* 225 */     FlatLayerInfo var10 = new FlatLayerInfo(p_180715_0_, var4, var6, var5);
/* 226 */     var10.setMinY(p_180715_2_);
/* 227 */     return var10;
/*     */   }
/*     */ 
/*     */   
/*     */   private static List func_180716_a(int p_180716_0_, String p_180716_1_) {
/* 232 */     if (p_180716_1_ != null && p_180716_1_.length() >= 1) {
/*     */       
/* 234 */       ArrayList<FlatLayerInfo> var2 = Lists.newArrayList();
/* 235 */       String[] var3 = p_180716_1_.split(",");
/* 236 */       int var4 = 0;
/* 237 */       String[] var5 = var3;
/* 238 */       int var6 = var3.length;
/*     */       
/* 240 */       for (int var7 = 0; var7 < var6; var7++) {
/*     */         
/* 242 */         String var8 = var5[var7];
/* 243 */         FlatLayerInfo var9 = func_180715_a(p_180716_0_, var8, var4);
/*     */         
/* 245 */         if (var9 == null)
/*     */         {
/* 247 */           return null;
/*     */         }
/*     */         
/* 250 */         var2.add(var9);
/* 251 */         var4 += var9.getLayerCount();
/*     */       } 
/*     */       
/* 254 */       return var2;
/*     */     } 
/*     */ 
/*     */     
/* 258 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static FlatGeneratorInfo createFlatGeneratorFromString(String p_82651_0_) {
/* 264 */     if (p_82651_0_ == null)
/*     */     {
/* 266 */       return getDefaultFlatGenerator();
/*     */     }
/*     */ 
/*     */     
/* 270 */     String[] var1 = p_82651_0_.split(";", -1);
/* 271 */     int var2 = (var1.length == 1) ? 0 : MathHelper.parseIntWithDefault(var1[0], 0);
/*     */     
/* 273 */     if (var2 >= 0 && var2 <= 3) {
/*     */       
/* 275 */       FlatGeneratorInfo var3 = new FlatGeneratorInfo();
/* 276 */       int var4 = (var1.length == 1) ? 0 : 1;
/* 277 */       List var5 = func_180716_a(var2, var1[var4++]);
/*     */       
/* 279 */       if (var5 != null && !var5.isEmpty()) {
/*     */         
/* 281 */         var3.getFlatLayers().addAll(var5);
/* 282 */         var3.func_82645_d();
/* 283 */         int var6 = BiomeGenBase.plains.biomeID;
/*     */         
/* 285 */         if (var2 > 0 && var1.length > var4)
/*     */         {
/* 287 */           var6 = MathHelper.parseIntWithDefault(var1[var4++], var6);
/*     */         }
/*     */         
/* 290 */         var3.setBiome(var6);
/*     */         
/* 292 */         if (var2 > 0 && var1.length > var4) {
/*     */           
/* 294 */           String[] var7 = var1[var4++].toLowerCase().split(",");
/* 295 */           String[] var8 = var7;
/* 296 */           int var9 = var7.length;
/*     */           
/* 298 */           for (int var10 = 0; var10 < var9; var10++) {
/*     */             
/* 300 */             String var11 = var8[var10];
/* 301 */             String[] var12 = var11.split("\\(", 2);
/* 302 */             HashMap<String, String> var13 = Maps.newHashMap();
/*     */             
/* 304 */             if (var12[0].length() > 0) {
/*     */               
/* 306 */               var3.getWorldFeatures().put(var12[0], var13);
/*     */               
/* 308 */               if (var12.length > 1 && var12[1].endsWith(")") && var12[1].length() > 1) {
/*     */                 
/* 310 */                 String[] var14 = var12[1].substring(0, var12[1].length() - 1).split(" ");
/*     */                 
/* 312 */                 for (int var15 = 0; var15 < var14.length; var15++)
/*     */                 {
/* 314 */                   String[] var16 = var14[var15].split("=", 2);
/*     */                   
/* 316 */                   if (var16.length == 2)
/*     */                   {
/* 318 */                     var13.put(var16[0], var16[1]);
/*     */                   }
/*     */                 }
/*     */               
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } else {
/*     */           
/* 327 */           var3.getWorldFeatures().put("village", Maps.newHashMap());
/*     */         } 
/*     */         
/* 330 */         return var3;
/*     */       } 
/*     */ 
/*     */       
/* 334 */       return getDefaultFlatGenerator();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 339 */     return getDefaultFlatGenerator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FlatGeneratorInfo getDefaultFlatGenerator() {
/* 346 */     FlatGeneratorInfo var0 = new FlatGeneratorInfo();
/* 347 */     var0.setBiome(BiomeGenBase.plains.biomeID);
/* 348 */     var0.getFlatLayers().add(new FlatLayerInfo(1, Blocks.bedrock));
/* 349 */     var0.getFlatLayers().add(new FlatLayerInfo(2, Blocks.dirt));
/* 350 */     var0.getFlatLayers().add(new FlatLayerInfo(1, (Block)Blocks.grass));
/* 351 */     var0.func_82645_d();
/* 352 */     var0.getWorldFeatures().put("village", Maps.newHashMap());
/* 353 */     return var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\FlatGeneratorInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */