/*    */ package net.minecraft.world;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NextTickListEntry
/*    */   implements Comparable
/*    */ {
/*    */   private static long nextTickEntryID;
/*    */   private final Block field_151352_g;
/*    */   public final BlockPos field_180282_a;
/*    */   public long scheduledTime;
/*    */   public int priority;
/*    */   private long tickEntryID;
/*    */   private static final String __OBFID = "CL_00000156";
/*    */   
/*    */   public NextTickListEntry(BlockPos p_i45745_1_, Block p_i45745_2_) {
/* 23 */     this.tickEntryID = nextTickEntryID++;
/* 24 */     this.field_180282_a = p_i45745_1_;
/* 25 */     this.field_151352_g = p_i45745_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 30 */     if (!(p_equals_1_ instanceof NextTickListEntry))
/*    */     {
/* 32 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 36 */     NextTickListEntry var2 = (NextTickListEntry)p_equals_1_;
/* 37 */     return (this.field_180282_a.equals(var2.field_180282_a) && Block.isEqualTo(this.field_151352_g, var2.field_151352_g));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 43 */     return this.field_180282_a.hashCode();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NextTickListEntry setScheduledTime(long p_77176_1_) {
/* 51 */     this.scheduledTime = p_77176_1_;
/* 52 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPriority(int p_82753_1_) {
/* 57 */     this.priority = p_82753_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(NextTickListEntry p_compareTo_1_) {
/* 62 */     return (this.scheduledTime < p_compareTo_1_.scheduledTime) ? -1 : ((this.scheduledTime > p_compareTo_1_.scheduledTime) ? 1 : ((this.priority != p_compareTo_1_.priority) ? (this.priority - p_compareTo_1_.priority) : ((this.tickEntryID < p_compareTo_1_.tickEntryID) ? -1 : ((this.tickEntryID > p_compareTo_1_.tickEntryID) ? 1 : 0))));
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     return String.valueOf(Block.getIdFromBlock(this.field_151352_g)) + ": " + this.field_180282_a + ", " + this.scheduledTime + ", " + this.priority + ", " + this.tickEntryID;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block func_151351_a() {
/* 72 */     return this.field_151352_g;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Object p_compareTo_1_) {
/* 77 */     return compareTo((NextTickListEntry)p_compareTo_1_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\NextTickListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */