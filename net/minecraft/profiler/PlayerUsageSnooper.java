/*     */ package net.minecraft.profiler;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.util.HttpUtil;
/*     */ 
/*     */ 
/*     */ public class PlayerUsageSnooper
/*     */ {
/*  21 */   private final Map field_152773_a = Maps.newHashMap();
/*  22 */   private final Map field_152774_b = Maps.newHashMap();
/*  23 */   private final String uniqueID = UUID.randomUUID().toString();
/*     */ 
/*     */   
/*     */   private final URL serverUrl;
/*     */   
/*     */   private final IPlayerUsage playerStatsCollector;
/*     */   
/*  30 */   private final Timer threadTrigger = new Timer("Snooper Timer", true);
/*  31 */   private final Object syncLock = new Object();
/*     */   
/*     */   private final long minecraftStartTimeMilis;
/*     */   
/*     */   private boolean isRunning;
/*     */   
/*     */   private int selfCounter;
/*     */   
/*     */   private static final String __OBFID = "CL_00001515";
/*     */   
/*     */   public PlayerUsageSnooper(String p_i1563_1_, IPlayerUsage p_i1563_2_, long p_i1563_3_) {
/*     */     try {
/*  43 */       this.serverUrl = new URL("http://snoop.minecraft.net/" + p_i1563_1_ + "?version=" + '\002');
/*     */     }
/*  45 */     catch (MalformedURLException var6) {
/*     */       
/*  47 */       throw new IllegalArgumentException();
/*     */     } 
/*     */     
/*  50 */     this.playerStatsCollector = p_i1563_2_;
/*  51 */     this.minecraftStartTimeMilis = p_i1563_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startSnooper() {
/*  59 */     if (!this.isRunning) {
/*     */       
/*  61 */       this.isRunning = true;
/*  62 */       func_152766_h();
/*  63 */       this.threadTrigger.schedule(new TimerTask()
/*     */           {
/*     */             private static final String __OBFID = "CL_00001516";
/*     */             
/*     */             public void run() {
/*  68 */               if (PlayerUsageSnooper.this.playerStatsCollector.isSnooperEnabled()) {
/*     */                 HashMap<String, Integer> var1;
/*     */ 
/*     */                 
/*  72 */                 synchronized (PlayerUsageSnooper.this.syncLock) {
/*     */                   
/*  74 */                   var1 = Maps.newHashMap(PlayerUsageSnooper.this.field_152774_b);
/*     */                   
/*  76 */                   if (PlayerUsageSnooper.this.selfCounter == 0)
/*     */                   {
/*  78 */                     var1.putAll(PlayerUsageSnooper.this.field_152773_a);
/*     */                   }
/*     */                   
/*  81 */                   var1.put("snooper_count", Integer.valueOf(PlayerUsageSnooper.this.selfCounter++));
/*  82 */                   var1.put("snooper_token", PlayerUsageSnooper.this.uniqueID);
/*     */                 } 
/*     */                 
/*  85 */                 HttpUtil.postMap(PlayerUsageSnooper.this.serverUrl, var1, true);
/*     */               } 
/*     */             }
/*  88 */           }0L, 900000L);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_152766_h() {
/*  94 */     addJvmArgsToSnooper();
/*  95 */     addClientStat("snooper_token", this.uniqueID);
/*  96 */     addStatToSnooper("snooper_token", this.uniqueID);
/*  97 */     addStatToSnooper("os_name", System.getProperty("os.name"));
/*  98 */     addStatToSnooper("os_version", System.getProperty("os.version"));
/*  99 */     addStatToSnooper("os_architecture", System.getProperty("os.arch"));
/* 100 */     addStatToSnooper("java_version", System.getProperty("java.version"));
/* 101 */     addStatToSnooper("version", "1.8");
/* 102 */     this.playerStatsCollector.addServerTypeToSnooper(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void addJvmArgsToSnooper() {
/* 107 */     RuntimeMXBean var1 = ManagementFactory.getRuntimeMXBean();
/* 108 */     List<String> var2 = var1.getInputArguments();
/* 109 */     int var3 = 0;
/* 110 */     Iterator<String> var4 = var2.iterator();
/*     */     
/* 112 */     while (var4.hasNext()) {
/*     */       
/* 114 */       String var5 = var4.next();
/*     */       
/* 116 */       if (var5.startsWith("-X"))
/*     */       {
/* 118 */         addClientStat("jvm_arg[" + var3++ + "]", var5);
/*     */       }
/*     */     } 
/*     */     
/* 122 */     addClientStat("jvm_args", Integer.valueOf(var3));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addMemoryStatsToSnooper() {
/* 127 */     addStatToSnooper("memory_total", Long.valueOf(Runtime.getRuntime().totalMemory()));
/* 128 */     addStatToSnooper("memory_max", Long.valueOf(Runtime.getRuntime().maxMemory()));
/* 129 */     addStatToSnooper("memory_free", Long.valueOf(Runtime.getRuntime().freeMemory()));
/* 130 */     addStatToSnooper("cpu_cores", Integer.valueOf(Runtime.getRuntime().availableProcessors()));
/* 131 */     this.playerStatsCollector.addServerStatsToSnooper(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addClientStat(String p_152768_1_, Object p_152768_2_) {
/* 136 */     Object var3 = this.syncLock;
/*     */     
/* 138 */     synchronized (this.syncLock) {
/*     */       
/* 140 */       this.field_152774_b.put(p_152768_1_, p_152768_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addStatToSnooper(String p_152767_1_, Object p_152767_2_) {
/* 146 */     Object var3 = this.syncLock;
/*     */     
/* 148 */     synchronized (this.syncLock) {
/*     */       
/* 150 */       this.field_152773_a.put(p_152767_1_, p_152767_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Map getCurrentStats() {
/* 156 */     LinkedHashMap var1 = Maps.newLinkedHashMap();
/* 157 */     Object var2 = this.syncLock;
/*     */     
/* 159 */     synchronized (this.syncLock) {
/*     */       
/* 161 */       addMemoryStatsToSnooper();
/* 162 */       Iterator<Map.Entry> var3 = this.field_152773_a.entrySet().iterator();
/*     */ 
/*     */       
/* 165 */       while (var3.hasNext()) {
/*     */         
/* 167 */         Map.Entry var4 = var3.next();
/* 168 */         var1.put(var4.getKey(), var4.getValue().toString());
/*     */       } 
/*     */       
/* 171 */       var3 = this.field_152774_b.entrySet().iterator();
/*     */       
/* 173 */       while (var3.hasNext()) {
/*     */         
/* 175 */         Map.Entry var4 = var3.next();
/* 176 */         var1.put(var4.getKey(), var4.getValue().toString());
/*     */       } 
/*     */       
/* 179 */       return var1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSnooperRunning() {
/* 185 */     return this.isRunning;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopSnooper() {
/* 190 */     this.threadTrigger.cancel();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUniqueID() {
/* 195 */     return this.uniqueID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getMinecraftStartTimeMillis() {
/* 203 */     return this.minecraftStartTimeMilis;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\profiler\PlayerUsageSnooper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */