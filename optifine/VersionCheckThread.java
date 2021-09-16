/*    */ package optifine;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import net.minecraft.client.ClientBrandRetriever;
/*    */ 
/*    */ public class VersionCheckThread
/*    */   extends Thread
/*    */ {
/*    */   public void run() {
/* 12 */     HttpURLConnection conn = null;
/*    */ 
/*    */     
/*    */     try {
/* 16 */       Config.dbg("Checking for new version");
/* 17 */       URL e = new URL("http://optifine.net/version/1.8/HD_U.txt");
/* 18 */       conn = (HttpURLConnection)e.openConnection();
/*    */       
/* 20 */       if ((Config.getGameSettings()).snooperEnabled) {
/*    */         
/* 22 */         conn.setRequestProperty("OF-MC-Version", "1.8");
/* 23 */         conn.setRequestProperty("OF-MC-Brand", ClientBrandRetriever.getClientModName());
/* 24 */         conn.setRequestProperty("OF-Edition", "HD_U");
/* 25 */         conn.setRequestProperty("OF-Release", "H6");
/* 26 */         conn.setRequestProperty("OF-Java-Version", System.getProperty("java.version"));
/* 27 */         conn.setRequestProperty("OF-CpuCount", Config.getAvailableProcessors());
/* 28 */         conn.setRequestProperty("OF-OpenGL-Version", Config.openGlVersion);
/* 29 */         conn.setRequestProperty("OF-OpenGL-Vendor", Config.openGlVendor);
/*    */       } 
/*    */       
/* 32 */       conn.setDoInput(true);
/* 33 */       conn.setDoOutput(false);
/* 34 */       conn.connect();
/*    */ 
/*    */ 
/*    */       
/* 38 */       try { InputStream in = conn.getInputStream();
/* 39 */         String verStr = Config.readInputStream(in);
/* 40 */         in.close();
/* 41 */         String[] verLines = Config.tokenize(verStr, "\n\r");
/*    */         
/* 43 */         if (verLines.length < 1) {
/*    */           return;
/*    */         }
/*    */ 
/*    */         
/* 48 */         String newVer = verLines[0].trim();
/* 49 */         Config.dbg("Version found: " + newVer);
/*    */ 
/*    */         
/*    */          }
/*    */       
/*    */       finally
/*    */       
/*    */       { 
/*    */ 
/*    */         
/* 59 */         if (conn != null)
/*    */         {
/* 61 */           conn.disconnect(); }  }  if (conn != null) conn.disconnect();
/*    */       
/*    */     
/*    */     }
/* 65 */     catch (Exception var11) {
/*    */       
/* 67 */       Config.dbg(String.valueOf(var11.getClass().getName()) + ": " + var11.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\VersionCheckThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */