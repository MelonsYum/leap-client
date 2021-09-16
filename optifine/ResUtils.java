/*     */ package optifine;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import net.minecraft.client.resources.AbstractResourcePack;
/*     */ import net.minecraft.client.resources.IResourcePack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResUtils
/*     */ {
/*     */   public static String[] collectFiles(String prefix, String suffix) {
/*  20 */     return collectFiles(new String[] { prefix }, new String[] { suffix });
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] collectFiles(String[] prefixes, String[] suffixes) {
/*  25 */     LinkedHashSet setPaths = new LinkedHashSet();
/*  26 */     IResourcePack[] rps = Config.getResourcePacks();
/*     */     
/*  28 */     for (int paths = 0; paths < rps.length; paths++) {
/*     */       
/*  30 */       IResourcePack rp = rps[paths];
/*  31 */       String[] ps = collectFiles(rp, prefixes, suffixes, (String[])null);
/*  32 */       setPaths.addAll(Arrays.asList(ps));
/*     */     } 
/*     */     
/*  35 */     String[] var7 = (String[])setPaths.toArray((Object[])new String[setPaths.size()]);
/*  36 */     return var7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] collectFiles(IResourcePack rp, String prefix, String suffix, String[] defaultPaths) {
/*  41 */     return collectFiles(rp, new String[] { prefix }, new String[] { suffix }, defaultPaths);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] collectFiles(IResourcePack rp, String[] prefixes, String[] suffixes) {
/*  46 */     return collectFiles(rp, prefixes, suffixes, (String[])null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String[] collectFiles(IResourcePack rp, String[] prefixes, String[] suffixes, String[] defaultPaths) {
/*  51 */     if (rp instanceof net.minecraft.client.resources.DefaultResourcePack)
/*     */     {
/*  53 */       return collectFilesFixed(rp, defaultPaths);
/*     */     }
/*  55 */     if (!(rp instanceof AbstractResourcePack))
/*     */     {
/*  57 */       return new String[0];
/*     */     }
/*     */ 
/*     */     
/*  61 */     AbstractResourcePack arp = (AbstractResourcePack)rp;
/*  62 */     File tpFile = arp.resourcePackFile;
/*  63 */     return (tpFile == null) ? new String[0] : (tpFile.isDirectory() ? collectFilesFolder(tpFile, "", prefixes, suffixes) : (tpFile.isFile() ? collectFilesZIP(tpFile, prefixes, suffixes) : new String[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String[] collectFilesFixed(IResourcePack rp, String[] paths) {
/*  69 */     if (paths == null)
/*     */     {
/*  71 */       return new String[0];
/*     */     }
/*     */ 
/*     */     
/*  75 */     ArrayList<String> list = new ArrayList();
/*     */     
/*  77 */     for (int pathArr = 0; pathArr < paths.length; pathArr++) {
/*     */       
/*  79 */       String path = paths[pathArr];
/*  80 */       ResourceLocation loc = new ResourceLocation(path);
/*     */       
/*  82 */       if (rp.resourceExists(loc))
/*     */       {
/*  84 */         list.add(path);
/*     */       }
/*     */     } 
/*     */     
/*  88 */     String[] var6 = list.<String>toArray(new String[list.size()]);
/*  89 */     return var6;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String[] collectFilesFolder(File tpFile, String basePath, String[] prefixes, String[] suffixes) {
/*  95 */     ArrayList<String> list = new ArrayList();
/*  96 */     String prefixAssets = "assets/minecraft/";
/*  97 */     File[] files = tpFile.listFiles();
/*     */     
/*  99 */     if (files == null)
/*     */     {
/* 101 */       return new String[0];
/*     */     }
/*     */ 
/*     */     
/* 105 */     for (int names = 0; names < files.length; names++) {
/*     */       
/* 107 */       File file = files[names];
/*     */ 
/*     */       
/* 110 */       if (file.isFile()) {
/*     */         
/* 112 */         String dirPath = String.valueOf(basePath) + file.getName();
/*     */         
/* 114 */         if (dirPath.startsWith(prefixAssets))
/*     */         {
/* 116 */           dirPath = dirPath.substring(prefixAssets.length());
/*     */           
/* 118 */           if (StrUtils.startsWith(dirPath, prefixes) && StrUtils.endsWith(dirPath, suffixes))
/*     */           {
/* 120 */             list.add(dirPath);
/*     */           }
/*     */         }
/*     */       
/* 124 */       } else if (file.isDirectory()) {
/*     */         
/* 126 */         String dirPath = String.valueOf(basePath) + file.getName() + "/";
/* 127 */         String[] names1 = collectFilesFolder(file, dirPath, prefixes, suffixes);
/*     */         
/* 129 */         for (int n = 0; n < names1.length; n++) {
/*     */           
/* 131 */           String name = names1[n];
/* 132 */           list.add(name);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     String[] var13 = list.<String>toArray(new String[list.size()]);
/* 138 */     return var13;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String[] collectFilesZIP(File tpFile, String[] prefixes, String[] suffixes) {
/* 144 */     ArrayList<String> list = new ArrayList();
/* 145 */     String prefixAssets = "assets/minecraft/";
/*     */ 
/*     */     
/*     */     try {
/* 149 */       ZipFile e = new ZipFile(tpFile);
/* 150 */       Enumeration<? extends ZipEntry> en = e.entries();
/*     */       
/* 152 */       while (en.hasMoreElements()) {
/*     */         
/* 154 */         ZipEntry names = en.nextElement();
/* 155 */         String name = names.getName();
/*     */         
/* 157 */         if (name.startsWith(prefixAssets)) {
/*     */           
/* 159 */           name = name.substring(prefixAssets.length());
/*     */           
/* 161 */           if (StrUtils.startsWith(name, prefixes) && StrUtils.endsWith(name, suffixes))
/*     */           {
/* 163 */             list.add(name);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 168 */       e.close();
/* 169 */       String[] names1 = list.<String>toArray(new String[list.size()]);
/* 170 */       return names1;
/*     */     }
/* 172 */     catch (IOException var9) {
/*     */       
/* 174 */       var9.printStackTrace();
/* 175 */       return new String[0];
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ResUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */