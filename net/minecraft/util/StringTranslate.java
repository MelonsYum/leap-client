/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.IllegalFormatException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.io.Charsets;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringTranslate
/*     */ {
/*  20 */   private static final Pattern numericVariablePattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   private static final Splitter equalSignSplitter = Splitter.on('=').limit(2);
/*     */ 
/*     */   
/*  28 */   private static StringTranslate instance = new StringTranslate();
/*  29 */   private final Map languageList = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   private long lastUpdateTimeInMilliseconds;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00001212";
/*     */ 
/*     */ 
/*     */   
/*     */   public StringTranslate() {
/*     */     try {
/*  41 */       InputStream var1 = StringTranslate.class.getResourceAsStream("/assets/minecraft/lang/en_US.lang");
/*  42 */       Iterator<String> var2 = IOUtils.readLines(var1, Charsets.UTF_8).iterator();
/*     */       
/*  44 */       while (var2.hasNext()) {
/*     */         
/*  46 */         String var3 = var2.next();
/*     */         
/*  48 */         if (!var3.isEmpty() && var3.charAt(0) != '#') {
/*     */           
/*  50 */           String[] var4 = (String[])Iterables.toArray(equalSignSplitter.split(var3), String.class);
/*     */           
/*  52 */           if (var4 != null && var4.length == 2) {
/*     */             
/*  54 */             String var5 = var4[0];
/*  55 */             String var6 = numericVariablePattern.matcher(var4[1]).replaceAll("%$1s");
/*  56 */             this.languageList.put(var5, var6);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  61 */       this.lastUpdateTimeInMilliseconds = System.currentTimeMillis();
/*     */     }
/*  63 */     catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static StringTranslate getInstance() {
/*  74 */     return instance;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void replaceWith(Map p_135063_0_) {
/*  82 */     instance.languageList.clear();
/*  83 */     instance.languageList.putAll(p_135063_0_);
/*  84 */     instance.lastUpdateTimeInMilliseconds = System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String translateKey(String p_74805_1_) {
/*  92 */     return tryTranslateKey(p_74805_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized String translateKeyFormat(String p_74803_1_, Object... p_74803_2_) {
/* 100 */     String var3 = tryTranslateKey(p_74803_1_);
/*     */ 
/*     */     
/*     */     try {
/* 104 */       return String.format(var3, p_74803_2_);
/*     */     }
/* 106 */     catch (IllegalFormatException var5) {
/*     */       
/* 108 */       return "Format error: " + var3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String tryTranslateKey(String p_135064_1_) {
/* 117 */     String var2 = (String)this.languageList.get(p_135064_1_);
/* 118 */     return (var2 == null) ? p_135064_1_ : var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isKeyTranslated(String p_94520_1_) {
/* 126 */     return this.languageList.containsKey(p_94520_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLastUpdateTimeInMilliseconds() {
/* 134 */     return this.lastUpdateTimeInMilliseconds;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\StringTranslate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */