/*     */ package optifine;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import shadersmod.client.Shaders;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CrashReporter
/*     */ {
/*     */   public static void onCrashReport(CrashReport crashReport, CrashReportCategory category) {
/*     */     try {
/*  15 */       GameSettings e = Config.getGameSettings();
/*     */       
/*  17 */       if (e == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  22 */       if (!e.snooperEnabled) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  27 */       Throwable cause = crashReport.getCrashCause();
/*     */       
/*  29 */       if (cause == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  34 */       if (cause.getClass() == Throwable.class) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  39 */       if (cause.getClass().getName().contains(".fml.client.SplashProgress")) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  44 */       extendCrashReport(category);
/*  45 */       String url = "http://optifine.net/crashReport";
/*  46 */       String reportStr = makeReport(crashReport);
/*  47 */       byte[] content = reportStr.getBytes("ASCII");
/*  48 */       IFileUploadListener listener = new IFileUploadListener()
/*     */         {
/*     */           public void fileUploadFinished(String url, byte[] content, Throwable exception) {}
/*     */         };
/*  52 */       HashMap<Object, Object> headers = new HashMap<>();
/*  53 */       headers.put("OF-Version", Config.getVersion());
/*  54 */       headers.put("OF-Summary", makeSummary(crashReport));
/*  55 */       FileUploadThread fut = new FileUploadThread(url, headers, content, listener);
/*  56 */       fut.setPriority(10);
/*  57 */       fut.start();
/*  58 */       Thread.sleep(1000L);
/*     */     }
/*  60 */     catch (Exception var10) {
/*     */       
/*  62 */       Config.dbg(String.valueOf(var10.getClass().getName()) + ": " + var10.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static String makeReport(CrashReport crashReport) {
/*  68 */     StringBuffer sb = new StringBuffer();
/*  69 */     sb.append("OptiFineVersion: " + Config.getVersion() + "\n");
/*  70 */     sb.append("Summary: " + makeSummary(crashReport) + "\n");
/*  71 */     sb.append("\n");
/*  72 */     sb.append(crashReport.getCompleteReport());
/*  73 */     sb.append("\n");
/*  74 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   private static String makeSummary(CrashReport crashReport) {
/*  79 */     Throwable t = crashReport.getCrashCause();
/*     */     
/*  81 */     if (t == null)
/*     */     {
/*  83 */       return "Unknown";
/*     */     }
/*     */ 
/*     */     
/*  87 */     StackTraceElement[] traces = t.getStackTrace();
/*  88 */     String firstTrace = "unknown";
/*     */     
/*  90 */     if (traces.length > 0)
/*     */     {
/*  92 */       firstTrace = traces[0].toString().trim();
/*     */     }
/*     */     
/*  95 */     String sum = String.valueOf(t.getClass().getName()) + ": " + t.getMessage() + " (" + crashReport.getDescription() + ")" + " [" + firstTrace + "]";
/*  96 */     return sum;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void extendCrashReport(CrashReportCategory cat) {
/* 102 */     cat.addCrashSection("OptiFine Version", Config.getVersion());
/*     */     
/* 104 */     if (Config.getGameSettings() != null) {
/*     */       
/* 106 */       cat.addCrashSection("Render Distance Chunks", Config.getChunkViewDistance());
/* 107 */       cat.addCrashSection("Mipmaps", Config.getMipmapLevels());
/* 108 */       cat.addCrashSection("Anisotropic Filtering", Config.getAnisotropicFilterLevel());
/* 109 */       cat.addCrashSection("Antialiasing", Config.getAntialiasingLevel());
/* 110 */       cat.addCrashSection("Multitexture", Config.isMultiTexture());
/*     */     } 
/*     */     
/* 113 */     cat.addCrashSection("Shaders", Shaders.getShaderPackName());
/* 114 */     cat.addCrashSection("OpenGlVersion", Config.openGlVersion);
/* 115 */     cat.addCrashSection("OpenGlRenderer", Config.openGlRenderer);
/* 116 */     cat.addCrashSection("OpenGlVendor", Config.openGlVendor);
/* 117 */     cat.addCrashSection("CpuCount", Config.getAvailableProcessors());
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CrashReporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */