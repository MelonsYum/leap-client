/*    */ package leap.modules.render;
/*    */ 
/*    */ import java.util.Random;
/*    */ import leap.Client;
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventReceivePacket;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.play.client.C01PacketChatMessage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Streamer
/*    */   extends Module
/*    */ {
/*    */   public static boolean hidename = false;
/* 23 */   public static BooleanSetting name = new BooleanSetting("True Name", true);
/* 24 */   public static BooleanSetting player = new BooleanSetting("Hide Player", true);
/* 25 */   public static BooleanSetting other = new BooleanSetting("Hide Others", true);
/*    */   
/*    */   public Streamer() {
/* 28 */     super("Streamer", 0, Module.Category.RENDER);
/* 29 */     addSettings(new Setting[] { (Setting)name, (Setting)player, (Setting)other });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 35 */     super.onDisable();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 40 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 44 */     if (e instanceof EventReceivePacket) {
/* 45 */       EventReceivePacket event = (EventReceivePacket)e;
/*    */       
/* 47 */       if (event.getPacket() instanceof C01PacketChatMessage) {
/*    */         
/* 49 */         C01PacketChatMessage packet = (C01PacketChatMessage)event.getPacket();
/* 50 */         String message = packet.message;
/*    */         
/* 52 */         if (other.isEnabled()) {
/* 53 */           String[] names = { "Bob", "Ben", "Guy", "Person", "epikgamer1090", "Xx_EPIXXGAMER_XX", "THE_BEST_110", "I_WON_LOL", "PandaFork", "Dan229", "Fortnite6687", "dogwater280", "isuck67", "getmad834", "cope_23452" };
/*    */           
/* 55 */           Random random = new Random();
/*    */           
/* 57 */           int select = random.nextInt(names.length);
/*    */ 
/*    */           
/* 60 */           for (Object list : mc.theWorld.loadedEntityList) {
/* 61 */             Entity entity = (Entity)list;
/*    */             
/* 63 */             String name = names[select];
/* 64 */             if (entity != null && 
/* 65 */               entity instanceof net.minecraft.entity.player.EntityPlayer) {
/* 66 */               if (packet.message.toLowerCase().contains(entity.getName().toLowerCase()));
/* 67 */               packet.message.replace(entity.getName(), name);
/*    */             } 
/*    */           } 
/*    */         } 
/*    */ 
/*    */         
/* 73 */         if (Streamer.name.isEnabled() && 
/* 74 */           Client.realPlayerName != null && mc.thePlayer != null) {
/* 75 */           if (packet.message.toLowerCase().contains(mc.thePlayer.getName().toLowerCase()));
/* 76 */           packet.message.replace(mc.thePlayer.getName(), Client.realPlayerName);
/*    */         } 
/*    */ 
/*    */         
/* 80 */         if (Client.realPlayerName != null && mc.thePlayer != null) {
/* 81 */           if (packet.message.toLowerCase().contains(mc.thePlayer.getName()));
/* 82 */           packet.message.replace(mc.thePlayer.getName(), "Player");
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\Streamer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */