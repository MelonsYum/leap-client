/*    */ package net.minecraft.util;
/*    */ 
/*    */ 
/*    */ public class Util
/*    */ {
/*    */   private static final String __OBFID = "CL_00001633";
/*    */   
/*    */   public static EnumOS getOSType() {
/*  9 */     String var0 = System.getProperty("os.name").toLowerCase();
/* 10 */     return var0.contains("win") ? EnumOS.WINDOWS : (var0.contains("mac") ? EnumOS.OSX : (var0.contains("solaris") ? EnumOS.SOLARIS : (var0.contains("sunos") ? EnumOS.SOLARIS : (var0.contains("linux") ? EnumOS.LINUX : (var0.contains("unix") ? EnumOS.LINUX : EnumOS.UNKNOWN)))));
/*    */   }
/*    */   
/*    */   public enum EnumOS
/*    */   {
/* 15 */     LINUX("LINUX", 0),
/* 16 */     SOLARIS("SOLARIS", 1),
/* 17 */     WINDOWS("WINDOWS", 2),
/* 18 */     OSX("OSX", 3),
/* 19 */     UNKNOWN("UNKNOWN", 4);
/*    */     
/* 21 */     private static final EnumOS[] $VALUES = new EnumOS[] { LINUX, SOLARIS, WINDOWS, OSX, UNKNOWN };
/*    */     private static final String __OBFID = "CL_00001660";
/*    */     
/*    */     static {
/*    */     
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\Util.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */