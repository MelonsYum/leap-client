/*     */ package net.minecraft.crash;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.world.gen.layer.IntCache;
/*     */ import optifine.CrashReporter;
/*     */ import optifine.Reflector;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrashReport
/*     */ {
/*  28 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private final String description;
/*     */ 
/*     */   
/*     */   private final Throwable cause;
/*     */ 
/*     */   
/*  37 */   private final CrashReportCategory theReportCategory = new CrashReportCategory(this, "System Details");
/*     */ 
/*     */   
/*  40 */   private final List crashReportSections = Lists.newArrayList();
/*     */   
/*     */   private File crashReportFile;
/*     */   
/*     */   private boolean field_85059_f = true;
/*  45 */   private StackTraceElement[] stacktrace = new StackTraceElement[0];
/*     */   
/*     */   private static final String __OBFID = "CL_00000990";
/*     */   private boolean reported = false;
/*     */   
/*     */   public CrashReport(String descriptionIn, Throwable causeThrowable) {
/*  51 */     this.description = descriptionIn;
/*  52 */     this.cause = causeThrowable;
/*  53 */     populateEnvironment();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void populateEnvironment() {
/*  62 */     this.theReportCategory.addCrashSectionCallable("Minecraft Version", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001197";
/*     */           
/*     */           public String call() {
/*  67 */             return "1.8";
/*     */           }
/*     */         });
/*  70 */     this.theReportCategory.addCrashSectionCallable("Operating System", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001222";
/*     */           
/*     */           public String call() {
/*  75 */             return String.valueOf(System.getProperty("os.name")) + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version");
/*     */           }
/*     */         });
/*  78 */     this.theReportCategory.addCrashSectionCallable("Java Version", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001248";
/*     */           
/*     */           public String call() {
/*  83 */             return String.valueOf(System.getProperty("java.version")) + ", " + System.getProperty("java.vendor");
/*     */           }
/*     */         });
/*  86 */     this.theReportCategory.addCrashSectionCallable("Java VM Version", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001275";
/*     */           
/*     */           public String call() {
/*  91 */             return String.valueOf(System.getProperty("java.vm.name")) + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor");
/*     */           }
/*     */         });
/*  94 */     this.theReportCategory.addCrashSectionCallable("Memory", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001302";
/*     */           
/*     */           public String call() {
/*  99 */             Runtime var1 = Runtime.getRuntime();
/* 100 */             long var2 = var1.maxMemory();
/* 101 */             long var4 = var1.totalMemory();
/* 102 */             long var6 = var1.freeMemory();
/* 103 */             long var8 = var2 / 1024L / 1024L;
/* 104 */             long var10 = var4 / 1024L / 1024L;
/* 105 */             long var12 = var6 / 1024L / 1024L;
/* 106 */             return String.valueOf(var6) + " bytes (" + var12 + " MB) / " + var4 + " bytes (" + var10 + " MB) up to " + var2 + " bytes (" + var8 + " MB)";
/*     */           }
/*     */         });
/* 109 */     this.theReportCategory.addCrashSectionCallable("JVM Flags", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001329";
/*     */           
/*     */           public String call() {
/* 114 */             RuntimeMXBean var1 = ManagementFactory.getRuntimeMXBean();
/* 115 */             List<String> var2 = var1.getInputArguments();
/* 116 */             int var3 = 0;
/* 117 */             StringBuilder var4 = new StringBuilder();
/* 118 */             Iterator<String> var5 = var2.iterator();
/*     */             
/* 120 */             while (var5.hasNext()) {
/*     */               
/* 122 */               String var6 = var5.next();
/*     */               
/* 124 */               if (var6.startsWith("-X")) {
/*     */                 
/* 126 */                 if (var3++ > 0)
/*     */                 {
/* 128 */                   var4.append(" ");
/*     */                 }
/*     */                 
/* 131 */                 var4.append(var6);
/*     */               } 
/*     */             } 
/*     */             
/* 135 */             return String.format("%d total; %s", new Object[] { Integer.valueOf(var3), var4.toString() });
/*     */           }
/*     */         });
/* 138 */     this.theReportCategory.addCrashSectionCallable("IntCache", new Callable()
/*     */         {
/*     */           private static final String __OBFID = "CL_00001355";
/*     */           
/*     */           public String call() {
/* 143 */             return IntCache.getCacheSizes();
/*     */           }
/*     */         });
/*     */     
/* 147 */     if (Reflector.FMLCommonHandler_enhanceCrashReport.exists()) {
/*     */       
/* 149 */       Object instance = Reflector.call(Reflector.FMLCommonHandler_instance, new Object[0]);
/* 150 */       Reflector.callString(instance, Reflector.FMLCommonHandler_enhanceCrashReport, new Object[] { this, this.theReportCategory });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 159 */     return this.description;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Throwable getCrashCause() {
/* 167 */     return this.cause;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSectionsInStringBuilder(StringBuilder builder) {
/* 175 */     if ((this.stacktrace == null || this.stacktrace.length <= 0) && this.crashReportSections.size() > 0)
/*     */     {
/* 177 */       this.stacktrace = (StackTraceElement[])ArrayUtils.subarray((Object[])((CrashReportCategory)this.crashReportSections.get(0)).getStackTrace(), 0, 1);
/*     */     }
/*     */     
/* 180 */     if (this.stacktrace != null && this.stacktrace.length > 0) {
/*     */       
/* 182 */       builder.append("-- Head --\n");
/* 183 */       builder.append("Stacktrace:\n");
/* 184 */       StackTraceElement[] var6 = this.stacktrace;
/* 185 */       int var7 = var6.length;
/*     */       
/* 187 */       for (int var4 = 0; var4 < var7; var4++) {
/*     */         
/* 189 */         StackTraceElement var5 = var6[var4];
/* 190 */         builder.append("\t").append("at ").append(var5.toString());
/* 191 */         builder.append("\n");
/*     */       } 
/*     */       
/* 194 */       builder.append("\n");
/*     */     } 
/*     */     
/* 197 */     Iterator<CrashReportCategory> var61 = this.crashReportSections.iterator();
/*     */     
/* 199 */     while (var61.hasNext()) {
/*     */       
/* 201 */       CrashReportCategory var71 = var61.next();
/* 202 */       var71.appendToStringBuilder(builder);
/* 203 */       builder.append("\n\n");
/*     */     } 
/*     */     
/* 206 */     this.theReportCategory.appendToStringBuilder(builder);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCauseStackTraceOrString() {
/* 214 */     StringWriter var1 = null;
/* 215 */     PrintWriter var2 = null;
/* 216 */     Object var3 = this.cause;
/*     */     
/* 218 */     if (((Throwable)var3).getMessage() == null) {
/*     */       
/* 220 */       if (var3 instanceof NullPointerException) {
/*     */         
/* 222 */         var3 = new NullPointerException(this.description);
/*     */       }
/* 224 */       else if (var3 instanceof StackOverflowError) {
/*     */         
/* 226 */         var3 = new StackOverflowError(this.description);
/*     */       }
/* 228 */       else if (var3 instanceof OutOfMemoryError) {
/*     */         
/* 230 */         var3 = new OutOfMemoryError(this.description);
/*     */       } 
/*     */       
/* 233 */       ((Throwable)var3).setStackTrace(this.cause.getStackTrace());
/*     */     } 
/*     */     
/* 236 */     String var4 = ((Throwable)var3).toString();
/*     */ 
/*     */     
/*     */     try {
/* 240 */       var1 = new StringWriter();
/* 241 */       var2 = new PrintWriter(var1);
/* 242 */       ((Throwable)var3).printStackTrace(var2);
/* 243 */       var4 = var1.toString();
/*     */     }
/*     */     finally {
/*     */       
/* 247 */       IOUtils.closeQuietly(var1);
/* 248 */       IOUtils.closeQuietly(var2);
/*     */     } 
/*     */     
/* 251 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCompleteReport() {
/* 259 */     if (!this.reported) {
/*     */       
/* 261 */       this.reported = true;
/* 262 */       CrashReporter.onCrashReport(this, this.theReportCategory);
/*     */     } 
/*     */     
/* 265 */     StringBuilder var1 = new StringBuilder();
/* 266 */     var1.append("---- Minecraft Crash Report ----\n");
/* 267 */     Reflector.call(Reflector.BlamingTransformer_onCrash, new Object[] { var1 });
/* 268 */     Reflector.call(Reflector.CoreModManager_onCrash, new Object[] { var1 });
/* 269 */     var1.append("// ");
/* 270 */     var1.append(getWittyComment());
/* 271 */     var1.append("\n\n");
/* 272 */     var1.append("Time: ");
/* 273 */     var1.append((new SimpleDateFormat()).format(new Date()));
/* 274 */     var1.append("\n");
/* 275 */     var1.append("Description: ");
/* 276 */     var1.append(this.description);
/* 277 */     var1.append("\n\n");
/* 278 */     var1.append(getCauseStackTraceOrString());
/* 279 */     var1.append("\n\nA detailed walkthrough of the error, its code path and all known details is as follows:\n");
/*     */     
/* 281 */     for (int var2 = 0; var2 < 87; var2++)
/*     */     {
/* 283 */       var1.append("-");
/*     */     }
/*     */     
/* 286 */     var1.append("\n\n");
/* 287 */     getSectionsInStringBuilder(var1);
/* 288 */     return var1.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getFile() {
/* 296 */     return this.crashReportFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveToFile(File toFile) {
/* 304 */     if (this.crashReportFile != null)
/*     */     {
/* 306 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 310 */     if (toFile.getParentFile() != null)
/*     */     {
/* 312 */       toFile.getParentFile().mkdirs();
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 317 */       FileWriter var3 = new FileWriter(toFile);
/* 318 */       var3.write(getCompleteReport());
/* 319 */       var3.close();
/* 320 */       this.crashReportFile = toFile;
/* 321 */       return true;
/*     */     }
/* 323 */     catch (Throwable var31) {
/*     */       
/* 325 */       logger.error("Could not save crash report to " + toFile, var31);
/* 326 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReportCategory getCategory() {
/* 333 */     return this.theReportCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReportCategory makeCategory(String name) {
/* 341 */     return makeCategoryDepth(name, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CrashReportCategory makeCategoryDepth(String categoryName, int stacktraceLength) {
/* 349 */     CrashReportCategory var3 = new CrashReportCategory(this, categoryName);
/*     */     
/* 351 */     if (this.field_85059_f) {
/*     */       
/* 353 */       int var4 = var3.getPrunedStackTrace(stacktraceLength);
/* 354 */       StackTraceElement[] var5 = this.cause.getStackTrace();
/* 355 */       StackTraceElement var6 = null;
/* 356 */       StackTraceElement var7 = null;
/* 357 */       int var8 = var5.length - var4;
/*     */       
/* 359 */       if (var8 < 0)
/*     */       {
/* 361 */         System.out.println("Negative index in crash report handler (" + var5.length + "/" + var4 + ")");
/*     */       }
/*     */       
/* 364 */       if (var5 != null && var8 >= 0 && var8 < var5.length) {
/*     */         
/* 366 */         var6 = var5[var8];
/*     */         
/* 368 */         if (var5.length + 1 - var4 < var5.length)
/*     */         {
/* 370 */           var7 = var5[var5.length + 1 - var4];
/*     */         }
/*     */       } 
/*     */       
/* 374 */       this.field_85059_f = var3.firstTwoElementsOfStackTraceMatch(var6, var7);
/*     */       
/* 376 */       if (var4 > 0 && !this.crashReportSections.isEmpty()) {
/*     */         
/* 378 */         CrashReportCategory var9 = this.crashReportSections.get(this.crashReportSections.size() - 1);
/* 379 */         var9.trimStackTraceEntriesFromBottom(var4);
/*     */       }
/* 381 */       else if (var5 != null && var5.length >= var4 && var8 >= 0 && var8 < var5.length) {
/*     */         
/* 383 */         this.stacktrace = new StackTraceElement[var8];
/* 384 */         System.arraycopy(var5, 0, this.stacktrace, 0, this.stacktrace.length);
/*     */       }
/*     */       else {
/*     */         
/* 388 */         this.field_85059_f = false;
/*     */       } 
/*     */     } 
/*     */     
/* 392 */     this.crashReportSections.add(var3);
/* 393 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getWittyComment() {
/* 401 */     String[] var0 = { "Who set us up the TNT?", "Everything's going to plan. No, really, that was supposed to happen.", "Uh... Did I do that?", "Oops.", "Why did you do that?", "I feel sad now :(", "My bad.", "I'm sorry, Dave.", "I let you down. Sorry :(", "On the bright side, I bought you a teddy bear!", "Daisy, daisy...", "Oh - I know what I did wrong!", "Hey, that tickles! Hehehe!", "I blame Dinnerbone.", "You should try our sister game, Minceraft!", "Don't be sad. I'll do better next time, I promise!", "Don't be sad, have a hug! <3", "I just don't know what went wrong :(", "Shall we play a game?", "Quite honestly, I wouldn't worry myself about that.", "I bet Cylons wouldn't have this problem.", "Sorry :(", "Surprise! Haha. Well, this is awkward.", "Would you like a cupcake?", "Hi. I'm Minecraft, and I'm a crashaholic.", "Ooh. Shiny.", "This doesn't make any sense!", "Why is it breaking :(", "Don't do that.", "Ouch. That hurt :(", "You're mean.", "This is a token for 1 free hug. Redeem at your nearest Mojangsta: [~~HUG~~]", "There are four lights!", "But it works on my machine." };
/*     */ 
/*     */     
/*     */     try {
/* 405 */       return var0[(int)(System.nanoTime() % var0.length)];
/*     */     }
/* 407 */     catch (Throwable var2) {
/*     */       
/* 409 */       return "Witty comment unavailable :(";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CrashReport makeCrashReport(Throwable causeIn, String descriptionIn) {
/*     */     CrashReport var2;
/* 420 */     if (causeIn instanceof ReportedException) {
/*     */       
/* 422 */       var2 = ((ReportedException)causeIn).getCrashReport();
/*     */     }
/*     */     else {
/*     */       
/* 426 */       var2 = new CrashReport(descriptionIn, causeIn);
/*     */     } 
/*     */     
/* 429 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\crash\CrashReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */