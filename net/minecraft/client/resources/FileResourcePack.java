/*     */ package net.minecraft.client.resources;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ 
/*     */ public class FileResourcePack
/*     */   extends AbstractResourcePack implements Closeable {
/*  20 */   public static final Splitter entryNameSplitter = Splitter.on('/').omitEmptyStrings().limit(3);
/*     */   
/*     */   private ZipFile resourcePackZipFile;
/*     */   private static final String __OBFID = "CL_00001075";
/*     */   
/*     */   public FileResourcePack(File p_i1290_1_) {
/*  26 */     super(p_i1290_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   private ZipFile getResourcePackZipFile() throws IOException {
/*  31 */     if (this.resourcePackZipFile == null)
/*     */     {
/*  33 */       this.resourcePackZipFile = new ZipFile(this.resourcePackFile);
/*     */     }
/*     */     
/*  36 */     return this.resourcePackZipFile;
/*     */   }
/*     */ 
/*     */   
/*     */   protected InputStream getInputStreamByName(String p_110591_1_) throws IOException {
/*  41 */     ZipFile var2 = getResourcePackZipFile();
/*  42 */     ZipEntry var3 = var2.getEntry(p_110591_1_);
/*     */     
/*  44 */     if (var3 == null)
/*     */     {
/*  46 */       throw new ResourcePackFileNotFoundException(this.resourcePackFile, p_110591_1_);
/*     */     }
/*     */ 
/*     */     
/*  50 */     return var2.getInputStream(var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasResourceName(String p_110593_1_) {
/*     */     try {
/*  58 */       return (getResourcePackZipFile().getEntry(p_110593_1_) != null);
/*     */     }
/*  60 */     catch (IOException var3) {
/*     */       
/*  62 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set getResourceDomains() {
/*     */     ZipFile var1;
/*     */     try {
/*  72 */       var1 = getResourcePackZipFile();
/*     */     }
/*  74 */     catch (IOException var8) {
/*     */       
/*  76 */       return Collections.emptySet();
/*     */     } 
/*     */     
/*  79 */     Enumeration<? extends ZipEntry> var2 = var1.entries();
/*  80 */     HashSet<String> var3 = Sets.newHashSet();
/*     */     
/*  82 */     while (var2.hasMoreElements()) {
/*     */       
/*  84 */       ZipEntry var4 = var2.nextElement();
/*  85 */       String var5 = var4.getName();
/*     */       
/*  87 */       if (var5.startsWith("assets/")) {
/*     */         
/*  89 */         ArrayList<String> var6 = Lists.newArrayList(entryNameSplitter.split(var5));
/*     */         
/*  91 */         if (var6.size() > 1) {
/*     */           
/*  93 */           String var7 = var6.get(1);
/*     */           
/*  95 */           if (!var7.equals(var7.toLowerCase())) {
/*     */             
/*  97 */             logNameNotLowercase(var7);
/*     */             
/*     */             continue;
/*     */           } 
/* 101 */           var3.add(var7);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 107 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 112 */     close();
/* 113 */     super.finalize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 118 */     if (this.resourcePackZipFile != null) {
/*     */       
/* 120 */       this.resourcePackZipFile.close();
/* 121 */       this.resourcePackZipFile = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\FileResourcePack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */