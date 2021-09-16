/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ public class SoundEventAccessor
/*    */   implements ISoundEventAccessor
/*    */ {
/*    */   private final SoundPoolEntry entry;
/*    */   private final int weight;
/*    */   private static final String __OBFID = "CL_00001153";
/*    */   
/*    */   SoundEventAccessor(SoundPoolEntry entry, int weight) {
/* 11 */     this.entry = entry;
/* 12 */     this.weight = weight;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getWeight() {
/* 17 */     return this.weight;
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundPoolEntry cloneEntry() {
/* 22 */     return new SoundPoolEntry(this.entry);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object cloneEntry1() {
/* 27 */     return cloneEntry();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\SoundEventAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */