/*    */ package optifine;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParser;
/*    */ 
/*    */ public class PlayerConfigurationReceiver
/*    */   implements IFileDownloadListener {
/*  8 */   private String player = null;
/*    */ 
/*    */   
/*    */   public PlayerConfigurationReceiver(String player) {
/* 12 */     this.player = player;
/*    */   }
/*    */ 
/*    */   
/*    */   public void fileDownloadFinished(String url, byte[] bytes, Throwable exception) {
/* 17 */     if (bytes != null)
/*    */       
/*    */       try {
/*    */         
/* 21 */         String e = new String(bytes, "ASCII");
/* 22 */         JsonParser jp = new JsonParser();
/* 23 */         JsonElement je = jp.parse(e);
/* 24 */         PlayerConfigurationParser pcp = new PlayerConfigurationParser(this.player);
/* 25 */         PlayerConfiguration pc = pcp.parsePlayerConfiguration(je);
/*    */         
/* 27 */         if (pc != null)
/*    */         {
/* 29 */           pc.setInitialized(true);
/* 30 */           PlayerConfigurations.setPlayerConfiguration(this.player, pc);
/*    */         }
/*    */       
/* 33 */       } catch (Exception var9) {
/*    */         
/* 35 */         Config.dbg("Error parsing configuration: " + url + ", " + var9.getClass().getName() + ": " + var9.getMessage());
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PlayerConfigurationReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */