/*    */ package leap.events.listeners;
/*    */ 
/*    */ import leap.events.Event;
/*    */ 
/*    */ public class EventKey
/*    */   extends Event<EventKey> {
/*    */   public int code;
/*    */   
/*    */   public EventKey(int code) {
/* 10 */     this.code = code;
/*    */   }
/*    */   
/*    */   public int getCode() {
/* 14 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(int code) {
/* 18 */     this.code = code;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\events\listeners\EventKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */