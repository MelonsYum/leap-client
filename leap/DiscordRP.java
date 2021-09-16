/*    */ package leap;
/*    */ 
/*    */ import net.arikia.dev.drpc.DiscordEventHandlers;
/*    */ import net.arikia.dev.drpc.DiscordRPC;
/*    */ import net.arikia.dev.drpc.DiscordRichPresence;
/*    */ import net.arikia.dev.drpc.DiscordUser;
/*    */ import net.arikia.dev.drpc.callbacks.ReadyCallback;
/*    */ 
/*    */ public class DiscordRP
/*    */ {
/*    */   private boolean running = true;
/* 12 */   private long created = 0L;
/*    */   
/*    */   public void start() {
/* 15 */     this.created = System.currentTimeMillis();
/*    */     
/* 17 */     DiscordEventHandlers handlers = (new DiscordEventHandlers.Builder()).setReadyEventHandler(new ReadyCallback()
/*    */         {
/*    */           public void apply(DiscordUser user) {
/* 20 */             System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
/* 21 */             DiscordRP.this.update("Booting Up....", "");
/*    */           }
/* 23 */         }).build();
/*    */     
/* 25 */     DiscordRPC.discordInitialize("849781550166179902", handlers, true);
/*    */     
/* 27 */     (new Thread("Discord RPC Callback")
/*    */       {
/*    */         public void run()
/*    */         {
/* 31 */           while (DiscordRP.this.running) {
/* 32 */             DiscordRPC.discordRunCallbacks();
/*    */           
/*    */           }
/*    */         }
/* 36 */       }).start();
/*    */   }
/*    */   public void shutdown() {
/* 39 */     this.running = false;
/* 40 */     DiscordRPC.discordShutdown();
/*    */   }
/*    */   
/*    */   public void update(String firstLine, String secondLine) {
/* 44 */     DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(secondLine);
/* 45 */     b.setBigImage("large", "");
/* 46 */     b.setDetails(firstLine);
/* 47 */     b.setStartTimestamps(this.created);
/*    */     
/* 49 */     DiscordRPC.discordUpdatePresence(b.build());
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\DiscordRP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */