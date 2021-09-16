/*     */ package optifine;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Iterables;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.resources.IResourcePack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.io.Charsets;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ public class Lang
/*     */ {
/*  19 */   private static final Splitter splitter = Splitter.on('=').limit(2);
/*  20 */   private static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
/*     */ 
/*     */   
/*     */   public static void resourcesReloaded() {
/*  24 */     Map localeProperties = I18n.getLocaleProperties();
/*  25 */     ArrayList<String> listFiles = new ArrayList();
/*  26 */     String PREFIX = "optifine/lang/";
/*  27 */     String EN_US = "en_US";
/*  28 */     String SUFFIX = ".lang";
/*  29 */     listFiles.add(String.valueOf(PREFIX) + EN_US + SUFFIX);
/*     */     
/*  31 */     if (!(Config.getGameSettings()).language.equals(EN_US))
/*     */     {
/*  33 */       listFiles.add(String.valueOf(PREFIX) + (Config.getGameSettings()).language + SUFFIX);
/*     */     }
/*     */     
/*  36 */     String[] files = listFiles.<String>toArray(new String[listFiles.size()]);
/*  37 */     loadResources((IResourcePack)Config.getDefaultResourcePack(), files, localeProperties);
/*  38 */     IResourcePack[] resourcePacks = Config.getResourcePacks();
/*     */     
/*  40 */     for (int i = 0; i < resourcePacks.length; i++) {
/*     */       
/*  42 */       IResourcePack rp = resourcePacks[i];
/*  43 */       loadResources(rp, files, localeProperties);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void loadResources(IResourcePack rp, String[] files, Map localeProperties) {
/*     */     try {
/*  51 */       for (int e = 0; e < files.length; e++) {
/*     */         
/*  53 */         String file = files[e];
/*  54 */         ResourceLocation loc = new ResourceLocation(file);
/*     */         
/*  56 */         if (rp.resourceExists(loc))
/*     */         {
/*  58 */           InputStream in = rp.getInputStream(loc);
/*     */           
/*  60 */           if (in != null)
/*     */           {
/*  62 */             loadLocaleData(in, localeProperties);
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*  67 */     } catch (IOException var7) {
/*     */       
/*  69 */       var7.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void loadLocaleData(InputStream is, Map<String, String> localeProperties) throws IOException {
/*  75 */     Iterator<String> it = IOUtils.readLines(is, Charsets.UTF_8).iterator();
/*     */     
/*  77 */     while (it.hasNext()) {
/*     */       
/*  79 */       String line = it.next();
/*     */       
/*  81 */       if (!line.isEmpty() && line.charAt(0) != '#') {
/*     */         
/*  83 */         String[] parts = (String[])Iterables.toArray(splitter.split(line), String.class);
/*     */         
/*  85 */         if (parts != null && parts.length == 2) {
/*     */           
/*  87 */           String key = parts[0];
/*  88 */           String value = pattern.matcher(parts[1]).replaceAll("%$1s");
/*  89 */           localeProperties.put(key, value);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String get(String key) {
/*  97 */     return I18n.format(key, new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String get(String key, String def) {
/* 102 */     String str = I18n.format(key, new Object[0]);
/* 103 */     return (str != null && !str.equals(key)) ? str : def;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getOn() {
/* 108 */     return I18n.format("options.on", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getOff() {
/* 113 */     return I18n.format("options.off", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFast() {
/* 118 */     return I18n.format("options.graphics.fast", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFancy() {
/* 123 */     return I18n.format("options.graphics.fancy", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getDefault() {
/* 128 */     return I18n.format("generator.default", new Object[0]);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\Lang.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */