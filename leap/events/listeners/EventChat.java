/*    */ package leap.events.listeners;
/*    */ 
/*    */ import leap.events.Event;
/*    */ 
/*    */ public class EventChat
/*    */   extends Event<EventChat>
/*    */ {
/*    */   public String message;
/*    */   
/*    */   public EventChat(String message) {
/* 11 */     this.message = message;
/*    */   }
/*    */   
/*    */   public String getMessage() {
/* 15 */     return this.message;
/*    */   }
/*    */   
/*    */   public void setMessage(String message) {
/* 19 */     this.message = message;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\events\listeners\EventChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */