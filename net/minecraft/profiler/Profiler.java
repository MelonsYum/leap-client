/*     */ package net.minecraft.profiler;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import optifine.Config;
/*     */ import optifine.Lagometer;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class Profiler
/*     */ {
/*  20 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*  23 */   private final List sectionList = Lists.newArrayList();
/*     */ 
/*     */   
/*  26 */   private final List timestampList = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   public boolean profilingEnabled;
/*     */ 
/*     */   
/*  32 */   private String profilingSection = "";
/*     */ 
/*     */   
/*  35 */   private final Map profilingMap = Maps.newHashMap();
/*     */   private static final String __OBFID = "CL_00001497";
/*     */   public boolean profilerGlobalEnabled = true;
/*     */   private boolean profilerLocalEnabled;
/*     */   private static final String SCHEDULED_EXECUTABLES = "scheduledExecutables";
/*     */   private static final String TICK = "tick";
/*     */   private static final String PRE_RENDER_ERRORS = "preRenderErrors";
/*     */   private static final String RENDER = "render";
/*     */   private static final String DISPLAY = "display";
/*  44 */   private static final int HASH_SCHEDULED_EXECUTABLES = "scheduledExecutables".hashCode();
/*  45 */   private static final int HASH_TICK = "tick".hashCode();
/*  46 */   private static final int HASH_PRE_RENDER_ERRORS = "preRenderErrors".hashCode();
/*  47 */   private static final int HASH_RENDER = "render".hashCode();
/*  48 */   private static final int HASH_DISPLAY = "display".hashCode();
/*     */ 
/*     */   
/*     */   public Profiler() {
/*  52 */     this.profilerLocalEnabled = this.profilerGlobalEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearProfiling() {
/*  60 */     this.profilingMap.clear();
/*  61 */     this.profilingSection = "";
/*  62 */     this.sectionList.clear();
/*  63 */     this.profilerLocalEnabled = this.profilerGlobalEnabled;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startSection(String name) {
/*  73 */     if (Lagometer.isActive()) {
/*     */       
/*  75 */       int hashName = name.hashCode();
/*     */       
/*  77 */       if (hashName == HASH_SCHEDULED_EXECUTABLES && name.equals("scheduledExecutables")) {
/*     */         
/*  79 */         Lagometer.timerScheduledExecutables.start();
/*     */       }
/*  81 */       else if (hashName == HASH_TICK && name.equals("tick") && Config.isMinecraftThread()) {
/*     */         
/*  83 */         Lagometer.timerScheduledExecutables.end();
/*  84 */         Lagometer.timerTick.start();
/*     */       }
/*  86 */       else if (hashName == HASH_PRE_RENDER_ERRORS && name.equals("preRenderErrors")) {
/*     */         
/*  88 */         Lagometer.timerTick.end();
/*     */       } 
/*     */     } 
/*     */     
/*  92 */     if (Config.isFastRender()) {
/*     */       
/*  94 */       int hashName = name.hashCode();
/*     */       
/*  96 */       if (hashName == HASH_RENDER && name.equals("render")) {
/*     */         
/*  98 */         GlStateManager.clearEnabled = false;
/*     */       }
/* 100 */       else if (hashName == HASH_DISPLAY && name.equals("display")) {
/*     */         
/* 102 */         GlStateManager.clearEnabled = true;
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     if (this.profilerLocalEnabled)
/*     */     {
/* 108 */       if (this.profilingEnabled) {
/*     */         
/* 110 */         if (this.profilingSection.length() > 0)
/*     */         {
/* 112 */           this.profilingSection = String.valueOf(this.profilingSection) + ".";
/*     */         }
/*     */         
/* 115 */         this.profilingSection = String.valueOf(this.profilingSection) + name;
/* 116 */         this.sectionList.add(this.profilingSection);
/* 117 */         this.timestampList.add(Long.valueOf(System.nanoTime()));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endSection() {
/* 127 */     if (this.profilerLocalEnabled)
/*     */     {
/* 129 */       if (this.profilingEnabled) {
/*     */         
/* 131 */         long var1 = System.nanoTime();
/* 132 */         long var3 = ((Long)this.timestampList.remove(this.timestampList.size() - 1)).longValue();
/* 133 */         this.sectionList.remove(this.sectionList.size() - 1);
/* 134 */         long var5 = var1 - var3;
/*     */         
/* 136 */         if (this.profilingMap.containsKey(this.profilingSection)) {
/*     */           
/* 138 */           this.profilingMap.put(this.profilingSection, Long.valueOf(((Long)this.profilingMap.get(this.profilingSection)).longValue() + var5));
/*     */         }
/*     */         else {
/*     */           
/* 142 */           this.profilingMap.put(this.profilingSection, Long.valueOf(var5));
/*     */         } 
/*     */         
/* 145 */         if (var5 > 100000000L)
/*     */         {
/* 147 */           logger.warn("Something's taking too long! '" + this.profilingSection + "' took aprox " + (var5 / 1000000.0D) + " ms");
/*     */         }
/*     */         
/* 150 */         this.profilingSection = !this.sectionList.isEmpty() ? this.sectionList.get(this.sectionList.size() - 1) : "";
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getProfilingData(String p_76321_1_) {
/* 160 */     this.profilerLocalEnabled = this.profilerGlobalEnabled;
/*     */     
/* 162 */     if (!this.profilerLocalEnabled)
/*     */     {
/* 164 */       return new ArrayList(Arrays.asList((Object[])new Result[] { new Result("root", 0.0D, 0.0D) }));
/*     */     }
/* 166 */     if (!this.profilingEnabled)
/*     */     {
/* 168 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 172 */     long var3 = this.profilingMap.containsKey("root") ? ((Long)this.profilingMap.get("root")).longValue() : 0L;
/* 173 */     long var5 = this.profilingMap.containsKey(p_76321_1_) ? ((Long)this.profilingMap.get(p_76321_1_)).longValue() : -1L;
/* 174 */     ArrayList<Result> var7 = Lists.newArrayList();
/*     */     
/* 176 */     if (p_76321_1_.length() > 0)
/*     */     {
/* 178 */       p_76321_1_ = String.valueOf(p_76321_1_) + ".";
/*     */     }
/*     */     
/* 181 */     long var8 = 0L;
/* 182 */     Iterator<String> var10 = this.profilingMap.keySet().iterator();
/*     */     
/* 184 */     while (var10.hasNext()) {
/*     */       
/* 186 */       String var20 = var10.next();
/*     */       
/* 188 */       if (var20.length() > p_76321_1_.length() && var20.startsWith(p_76321_1_) && var20.indexOf(".", p_76321_1_.length() + 1) < 0)
/*     */       {
/* 190 */         var8 += ((Long)this.profilingMap.get(var20)).longValue();
/*     */       }
/*     */     } 
/*     */     
/* 194 */     float var201 = (float)var8;
/*     */     
/* 196 */     if (var8 < var5)
/*     */     {
/* 198 */       var8 = var5;
/*     */     }
/*     */     
/* 201 */     if (var3 < var8)
/*     */     {
/* 203 */       var3 = var8;
/*     */     }
/*     */     
/* 206 */     Iterator<String> var21 = this.profilingMap.keySet().iterator();
/*     */ 
/*     */     
/* 209 */     while (var21.hasNext()) {
/*     */       
/* 211 */       String var12 = var21.next();
/*     */       
/* 213 */       if (var12.length() > p_76321_1_.length() && var12.startsWith(p_76321_1_) && var12.indexOf(".", p_76321_1_.length() + 1) < 0) {
/*     */         
/* 215 */         long var13 = ((Long)this.profilingMap.get(var12)).longValue();
/* 216 */         double var15 = var13 * 100.0D / var8;
/* 217 */         double var17 = var13 * 100.0D / var3;
/* 218 */         String var19 = var12.substring(p_76321_1_.length());
/* 219 */         var7.add(new Result(var19, var15, var17));
/*     */       } 
/*     */     } 
/*     */     
/* 223 */     var21 = this.profilingMap.keySet().iterator();
/*     */     
/* 225 */     while (var21.hasNext()) {
/*     */       
/* 227 */       String var12 = var21.next();
/* 228 */       this.profilingMap.put(var12, Long.valueOf(((Long)this.profilingMap.get(var12)).longValue() * 950L / 1000L));
/*     */     } 
/*     */     
/* 231 */     if ((float)var8 > var201)
/*     */     {
/* 233 */       var7.add(new Result("unspecified", ((float)var8 - var201) * 100.0D / var8, ((float)var8 - var201) * 100.0D / var3));
/*     */     }
/*     */     
/* 236 */     Collections.sort(var7);
/* 237 */     var7.add(0, new Result(p_76321_1_, 100.0D, var8 * 100.0D / var3));
/* 238 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endStartSection(String name) {
/* 247 */     if (this.profilerLocalEnabled) {
/*     */       
/* 249 */       endSection();
/* 250 */       startSection(name);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getNameOfLastSection() {
/* 256 */     return (this.sectionList.size() == 0) ? "[UNKNOWN]" : this.sectionList.get(this.sectionList.size() - 1);
/*     */   }
/*     */   
/*     */   public static final class Result
/*     */     implements Comparable
/*     */   {
/*     */     public double field_76332_a;
/*     */     public double field_76330_b;
/*     */     public String field_76331_c;
/*     */     
/*     */     public Result(String p_i1554_1_, double p_i1554_2_, double p_i1554_4_) {
/* 267 */       this.field_76331_c = p_i1554_1_;
/* 268 */       this.field_76332_a = p_i1554_2_;
/* 269 */       this.field_76330_b = p_i1554_4_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int compareTo(Result p_compareTo_1_) {
/* 274 */       return (p_compareTo_1_.field_76332_a < this.field_76332_a) ? -1 : ((p_compareTo_1_.field_76332_a > this.field_76332_a) ? 1 : p_compareTo_1_.field_76331_c.compareTo(this.field_76331_c));
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_76329_a() {
/* 279 */       return (this.field_76331_c.hashCode() & 0xAAAAAA) + 4473924;
/*     */     }
/*     */ 
/*     */     
/*     */     public int compareTo(Object p_compareTo_1_) {
/* 284 */       return compareTo((Result)p_compareTo_1_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\profiler\Profiler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */