/*    */ package net.minecraft.util;
/*    */ 
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class LoggingPrintStream
/*    */   extends PrintStream {
/* 10 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final String domain;
/*    */   private static final String __OBFID = "CL_00002275";
/*    */   
/*    */   public LoggingPrintStream(String p_i45927_1_, OutputStream p_i45927_2_) {
/* 16 */     super(p_i45927_2_);
/* 17 */     this.domain = p_i45927_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void println(String p_println_1_) {
/* 22 */     logString(p_println_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public void println(Object p_println_1_) {
/* 27 */     logString(String.valueOf(p_println_1_));
/*    */   }
/*    */ 
/*    */   
/*    */   private void logString(String p_179882_1_) {
/* 32 */     StackTraceElement[] var2 = Thread.currentThread().getStackTrace();
/* 33 */     StackTraceElement var3 = var2[Math.min(3, var2.length)];
/* 34 */     LOGGER.info("[{}]@.({}:{}): {}", new Object[] { this.domain, var3.getFileName(), Integer.valueOf(var3.getLineNumber()), p_179882_1_ });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\LoggingPrintStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */