/*     */ package shadersmod.common;
/*     */ 
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Calendar;
/*     */ import java.util.logging.ConsoleHandler;
/*     */ import java.util.logging.Formatter;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.LogRecord;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.logging.StreamHandler;
/*     */ 
/*     */ public abstract class SMCLog {
/*     */   public static final String smcLogName = "SMC";
/*  16 */   public static final Logger logger = new SMCLogger("SMC");
/*  17 */   public static final Level SMCINFO = new SMCLevel("INF", 850, null);
/*  18 */   public static final Level SMCCONFIG = new SMCLevel("CFG", 840, null);
/*  19 */   public static final Level SMCFINE = new SMCLevel("FNE", 830, null);
/*  20 */   public static final Level SMCFINER = new SMCLevel("FNR", 820, null);
/*  21 */   public static final Level SMCFINEST = new SMCLevel("FNT", 810, null);
/*     */ 
/*     */   
/*     */   public static void log(Level level, String message) {
/*  25 */     if (logger.isLoggable(level))
/*     */     {
/*  27 */       logger.log(level, message);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void severe(String message) {
/*  33 */     if (logger.isLoggable(Level.SEVERE))
/*     */     {
/*  35 */       logger.log(Level.SEVERE, message);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void warning(String message) {
/*  41 */     if (logger.isLoggable(Level.WARNING))
/*     */     {
/*  43 */       logger.log(Level.WARNING, message);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void info(String message) {
/*  49 */     if (logger.isLoggable(SMCINFO))
/*     */     {
/*  51 */       logger.log(SMCINFO, message);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void config(String message) {
/*  57 */     if (logger.isLoggable(SMCCONFIG))
/*     */     {
/*  59 */       logger.log(SMCCONFIG, message);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void fine(String message) {
/*  65 */     if (logger.isLoggable(SMCFINE))
/*     */     {
/*  67 */       logger.log(SMCFINE, message);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void finer(String message) {
/*  73 */     if (logger.isLoggable(SMCFINER))
/*     */     {
/*  75 */       logger.log(SMCFINER, message);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void finest(String message) {
/*  81 */     if (logger.isLoggable(SMCFINEST))
/*     */     {
/*  83 */       logger.log(SMCFINEST, message);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void log(Level level, String format, Object... args) {
/*  89 */     if (logger.isLoggable(level))
/*     */     {
/*  91 */       logger.log(level, String.format(format, args));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void severe(String format, Object... args) {
/*  97 */     if (logger.isLoggable(Level.SEVERE))
/*     */     {
/*  99 */       logger.log(Level.SEVERE, String.format(format, args));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void warning(String format, Object... args) {
/* 105 */     if (logger.isLoggable(Level.WARNING))
/*     */     {
/* 107 */       logger.log(Level.WARNING, String.format(format, args));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void info(String format, Object... args) {
/* 113 */     if (logger.isLoggable(SMCINFO))
/*     */     {
/* 115 */       logger.log(SMCINFO, String.format(format, args));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void config(String format, Object... args) {
/* 121 */     if (logger.isLoggable(SMCCONFIG))
/*     */     {
/* 123 */       logger.log(SMCCONFIG, String.format(format, args));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void fine(String format, Object... args) {
/* 129 */     if (logger.isLoggable(SMCFINE))
/*     */     {
/* 131 */       logger.log(SMCFINE, String.format(format, args));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void finer(String format, Object... args) {
/* 137 */     if (logger.isLoggable(SMCFINER))
/*     */     {
/* 139 */       logger.log(SMCFINER, String.format(format, args));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void finest(String format, Object... args) {
/* 145 */     if (logger.isLoggable(SMCFINEST))
/*     */     {
/* 147 */       logger.log(SMCFINEST, String.format(format, args));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class NamelessClass763038833 {}
/*     */   
/*     */   private static class SMCFormatter
/*     */     extends Formatter
/*     */   {
/* 157 */     int tzOffset = Calendar.getInstance().getTimeZone().getRawOffset();
/*     */ 
/*     */     
/*     */     public String format(LogRecord record) {
/* 161 */       StringBuilder sb = new StringBuilder();
/* 162 */       sb.append("[");
/* 163 */       sb.append("Shaders").append("]");
/*     */       
/* 165 */       if (record.getLevel() != SMCLog.SMCINFO)
/*     */       {
/* 167 */         sb.append("[").append(record.getLevel()).append("]");
/*     */       }
/*     */       
/* 170 */       sb.append(" ");
/* 171 */       sb.append(record.getMessage()).append("\n");
/* 172 */       return sb.toString();
/*     */     }
/*     */     
/*     */     private SMCFormatter() {}
/*     */   }
/*     */   
/*     */   private static class SMCLevel extends Level {
/*     */     private SMCLevel(String name, int value) {
/* 180 */       super(name, value);
/*     */     }
/*     */ 
/*     */     
/*     */     SMCLevel(String x0, int x1, SMCLog.NamelessClass763038833 x2) {
/* 185 */       this(x0, x1);
/*     */     }
/*     */   }
/*     */   
/*     */   private static class SMCLogger
/*     */     extends Logger
/*     */   {
/*     */     SMCLogger(String name) {
/* 193 */       super(name, null);
/* 194 */       setUseParentHandlers(false);
/* 195 */       SMCLog.SMCFormatter formatter = new SMCLog.SMCFormatter(null);
/* 196 */       ConsoleHandler handler = new ConsoleHandler();
/* 197 */       handler.setFormatter(formatter);
/* 198 */       handler.setLevel(Level.ALL);
/* 199 */       addHandler(handler);
/*     */ 
/*     */       
/*     */       try {
/* 203 */         FileOutputStream e = new FileOutputStream("logs/shadersmod.log", false);
/* 204 */         StreamHandler handler1 = new StreamHandler(e, formatter)
/*     */           {
/*     */             
/*     */             public synchronized void publish(LogRecord record)
/*     */             {
/* 209 */               super.publish(record);
/* 210 */               flush();
/*     */             }
/*     */           };
/* 213 */         handler1.setFormatter(formatter);
/* 214 */         handler1.setLevel(Level.ALL);
/* 215 */         addHandler(handler1);
/*     */       }
/* 217 */       catch (IOException var5) {
/*     */         
/* 219 */         var5.printStackTrace();
/*     */       } 
/*     */       
/* 222 */       setLevel(Level.ALL);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\common\SMCLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */