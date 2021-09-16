/*    */ package optifine;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ 
/*    */ public class PlayerConfigurations
/*    */ {
/* 10 */   private static Map mapConfigurations = null;
/*    */ 
/*    */   
/*    */   public static void renderPlayerItems(ModelBiped modelBiped, AbstractClientPlayer player, float scale, float partialTicks) {
/* 14 */     PlayerConfiguration cfg = getPlayerConfiguration(player);
/*    */     
/* 16 */     if (cfg != null)
/*    */     {
/* 18 */       cfg.renderPlayerItems(modelBiped, player, scale, partialTicks);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public static synchronized PlayerConfiguration getPlayerConfiguration(AbstractClientPlayer player) {
/* 24 */     String name = player.getNameClear();
/*    */     
/* 26 */     if (name == null)
/*    */     {
/* 28 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 32 */     PlayerConfiguration pc = (PlayerConfiguration)getMapConfigurations().get(name);
/*    */     
/* 34 */     if (pc == null) {
/*    */       
/* 36 */       pc = new PlayerConfiguration();
/* 37 */       getMapConfigurations().put(name, pc);
/* 38 */       PlayerConfigurationReceiver pcl = new PlayerConfigurationReceiver(name);
/* 39 */       String url = "http://s.optifine.net/users/" + name + ".cfg";
/* 40 */       FileDownloadThread fdt = new FileDownloadThread(url, pcl);
/* 41 */       fdt.start();
/*    */     } 
/*    */     
/* 44 */     return pc;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void setPlayerConfiguration(String player, PlayerConfiguration pc) {
/* 50 */     getMapConfigurations().put(player, pc);
/*    */   }
/*    */ 
/*    */   
/*    */   private static Map getMapConfigurations() {
/* 55 */     if (mapConfigurations == null)
/*    */     {
/* 57 */       mapConfigurations = new HashMap<>();
/*    */     }
/*    */     
/* 60 */     return mapConfigurations;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PlayerConfigurations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */