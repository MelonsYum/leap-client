/*    */ package leap.events.listeners;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import net.minecraft.network.Packet;
/*    */ 
/*    */ public class EventReceivePacket extends Event<EventReceivePacket> {
/*    */   private Packet packet;
/*    */   
/*    */   public EventReceivePacket(Packet packet) {
/* 10 */     this.packet = packet;
/*    */   }
/*    */   
/*    */   public Packet getPacket() {
/* 14 */     return this.packet;
/*    */   }
/*    */   public void setPacket(Packet packet) {
/* 17 */     this.packet = packet;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\events\listeners\EventReceivePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */