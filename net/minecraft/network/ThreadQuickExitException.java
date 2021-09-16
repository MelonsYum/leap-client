/*    */ package net.minecraft.network;
/*    */ 
/*    */ public final class ThreadQuickExitException
/*    */   extends RuntimeException {
/*  5 */   public static final ThreadQuickExitException field_179886_a = new ThreadQuickExitException();
/*    */   
/*    */   private static final String __OBFID = "CL_00002274";
/*    */   
/*    */   private ThreadQuickExitException() {
/* 10 */     setStackTrace(new StackTraceElement[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized Throwable fillInStackTrace() {
/* 15 */     setStackTrace(new StackTraceElement[0]);
/* 16 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\ThreadQuickExitException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */