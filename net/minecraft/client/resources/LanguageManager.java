/*     */ package net.minecraft.client.resources;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.SortedSet;
/*     */ import net.minecraft.client.resources.data.IMetadataSerializer;
/*     */ import net.minecraft.client.resources.data.LanguageMetadataSection;
/*     */ import net.minecraft.util.StringTranslate;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class LanguageManager
/*     */   implements IResourceManagerReloadListener {
/*  20 */   private static final Logger logger = LogManager.getLogger();
/*     */   private final IMetadataSerializer theMetadataSerializer;
/*     */   private String currentLanguage;
/*  23 */   protected static final Locale currentLocale = new Locale();
/*  24 */   private Map languageMap = Maps.newHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00001096";
/*     */   
/*     */   public LanguageManager(IMetadataSerializer p_i1304_1_, String p_i1304_2_) {
/*  29 */     this.theMetadataSerializer = p_i1304_1_;
/*  30 */     this.currentLanguage = p_i1304_2_;
/*  31 */     I18n.setLocale(currentLocale);
/*     */   }
/*     */ 
/*     */   
/*     */   public void parseLanguageMetadata(List p_135043_1_) {
/*  36 */     this.languageMap.clear();
/*  37 */     Iterator<IResourcePack> var2 = p_135043_1_.iterator();
/*     */     
/*  39 */     while (var2.hasNext()) {
/*     */       
/*  41 */       IResourcePack var3 = var2.next();
/*     */ 
/*     */       
/*     */       try {
/*  45 */         LanguageMetadataSection var4 = (LanguageMetadataSection)var3.getPackMetadata(this.theMetadataSerializer, "language");
/*     */         
/*  47 */         if (var4 != null) {
/*     */           
/*  49 */           Iterator<Language> var5 = var4.getLanguages().iterator();
/*     */           
/*  51 */           while (var5.hasNext())
/*     */           {
/*  53 */             Language var6 = var5.next();
/*     */             
/*  55 */             if (!this.languageMap.containsKey(var6.getLanguageCode()))
/*     */             {
/*  57 */               this.languageMap.put(var6.getLanguageCode(), var6);
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*  62 */       } catch (RuntimeException var7) {
/*     */         
/*  64 */         logger.warn("Unable to parse metadata section of resourcepack: " + var3.getPackName(), var7);
/*     */       }
/*  66 */       catch (IOException var8) {
/*     */         
/*  68 */         logger.warn("Unable to parse metadata section of resourcepack: " + var3.getPackName(), var8);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onResourceManagerReload(IResourceManager p_110549_1_) {
/*  75 */     ArrayList<String> var2 = Lists.newArrayList((Object[])new String[] { "en_US" });
/*     */     
/*  77 */     if (!"en_US".equals(this.currentLanguage))
/*     */     {
/*  79 */       var2.add(this.currentLanguage);
/*     */     }
/*     */     
/*  82 */     currentLocale.loadLocaleDataFiles(p_110549_1_, var2);
/*  83 */     StringTranslate.replaceWith(currentLocale.field_135032_a);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCurrentLocaleUnicode() {
/*  88 */     return currentLocale.isUnicode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCurrentLanguageBidirectional() {
/*  93 */     return (getCurrentLanguage() != null && getCurrentLanguage().isBidirectional());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCurrentLanguage(Language p_135045_1_) {
/*  98 */     this.currentLanguage = p_135045_1_.getLanguageCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public Language getCurrentLanguage() {
/* 103 */     return this.languageMap.containsKey(this.currentLanguage) ? (Language)this.languageMap.get(this.currentLanguage) : (Language)this.languageMap.get("en_US");
/*     */   }
/*     */ 
/*     */   
/*     */   public SortedSet getLanguages() {
/* 108 */     return Sets.newTreeSet(this.languageMap.values());
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\LanguageManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */