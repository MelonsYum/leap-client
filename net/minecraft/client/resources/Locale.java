/*     */ package net.minecraft.client.resources;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.IllegalFormatException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.Charsets;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ public class Locale
/*     */ {
/*  20 */   private static final Splitter splitter = Splitter.on('=').limit(2);
/*  21 */   private static final Pattern field_135031_c = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
/*  22 */   Map field_135032_a = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   private boolean field_135029_d;
/*     */   
/*     */   private static final String __OBFID = "CL_00001097";
/*     */ 
/*     */   
/*     */   public synchronized void loadLocaleDataFiles(IResourceManager p_135022_1_, List p_135022_2_) {
/*  31 */     this.field_135032_a.clear();
/*  32 */     Iterator<String> var3 = p_135022_2_.iterator();
/*     */     
/*  34 */     while (var3.hasNext()) {
/*     */       
/*  36 */       String var4 = var3.next();
/*  37 */       String var5 = String.format("lang/%s.lang", new Object[] { var4 });
/*  38 */       Iterator<String> var6 = p_135022_1_.getResourceDomains().iterator();
/*     */       
/*  40 */       while (var6.hasNext()) {
/*     */         
/*  42 */         String var7 = var6.next();
/*     */ 
/*     */         
/*     */         try {
/*  46 */           loadLocaleData(p_135022_1_.getAllResources(new ResourceLocation(var7, var5)));
/*     */         }
/*  48 */         catch (IOException iOException) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  55 */     checkUnicode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUnicode() {
/*  60 */     return this.field_135029_d;
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkUnicode() {
/*  65 */     this.field_135029_d = false;
/*  66 */     int var1 = 0;
/*  67 */     int var2 = 0;
/*  68 */     Iterator<String> var3 = this.field_135032_a.values().iterator();
/*     */     
/*  70 */     while (var3.hasNext()) {
/*     */       
/*  72 */       String var4 = var3.next();
/*  73 */       int var5 = var4.length();
/*  74 */       var2 += var5;
/*     */       
/*  76 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/*  78 */         if (var4.charAt(var6) >= 'Ā')
/*     */         {
/*  80 */           var1++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  85 */     float var7 = var1 / var2;
/*  86 */     this.field_135029_d = (var7 > 0.1D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadLocaleData(List p_135028_1_) throws IOException {
/*  94 */     Iterator<IResource> var2 = p_135028_1_.iterator();
/*     */     
/*  96 */     while (var2.hasNext()) {
/*     */       
/*  98 */       IResource var3 = var2.next();
/*  99 */       InputStream var4 = var3.getInputStream();
/*     */ 
/*     */       
/*     */       try {
/* 103 */         loadLocaleData(var4);
/*     */       }
/*     */       finally {
/*     */         
/* 107 */         IOUtils.closeQuietly(var4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadLocaleData(InputStream p_135021_1_) throws IOException {
/* 114 */     Iterator<String> var2 = IOUtils.readLines(p_135021_1_, Charsets.UTF_8).iterator();
/*     */     
/* 116 */     while (var2.hasNext()) {
/*     */       
/* 118 */       String var3 = var2.next();
/*     */       
/* 120 */       if (!var3.isEmpty() && var3.charAt(0) != '#') {
/*     */         
/* 122 */         String[] var4 = (String[])Iterables.toArray(splitter.split(var3), String.class);
/*     */         
/* 124 */         if (var4 != null && var4.length == 2) {
/*     */           
/* 126 */           String var5 = var4[0];
/* 127 */           String var6 = field_135031_c.matcher(var4[1]).replaceAll("%$1s");
/* 128 */           this.field_135032_a.put(var5, var6);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String translateKeyPrivate(String p_135026_1_) {
/* 139 */     String var2 = (String)this.field_135032_a.get(p_135026_1_);
/* 140 */     return (var2 == null) ? p_135026_1_ : var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String formatMessage(String p_135023_1_, Object[] p_135023_2_) {
/* 148 */     String var3 = translateKeyPrivate(p_135023_1_);
/*     */ 
/*     */     
/*     */     try {
/* 152 */       return String.format(var3, p_135023_2_);
/*     */     }
/* 154 */     catch (IllegalFormatException var5) {
/*     */       
/* 156 */       return "Format error: " + var3;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\Locale.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */