/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockEventData
/*    */ {
/*    */   private BlockPos field_180329_a;
/*    */   private Block field_151344_d;
/*    */   private int eventID;
/*    */   private int eventParameter;
/*    */   private static final String __OBFID = "CL_00000131";
/*    */   
/*    */   public BlockEventData(BlockPos p_i45756_1_, Block p_i45756_2_, int p_i45756_3_, int p_i45756_4_) {
/* 17 */     this.field_180329_a = p_i45756_1_;
/* 18 */     this.eventID = p_i45756_3_;
/* 19 */     this.eventParameter = p_i45756_4_;
/* 20 */     this.field_151344_d = p_i45756_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_180328_a() {
/* 25 */     return this.field_180329_a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getEventID() {
/* 33 */     return this.eventID;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEventParameter() {
/* 38 */     return this.eventParameter;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getBlock() {
/* 43 */     return this.field_151344_d;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object p_equals_1_) {
/* 48 */     if (!(p_equals_1_ instanceof BlockEventData))
/*    */     {
/* 50 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 54 */     BlockEventData var2 = (BlockEventData)p_equals_1_;
/* 55 */     return (this.field_180329_a.equals(var2.field_180329_a) && this.eventID == var2.eventID && this.eventParameter == var2.eventParameter && this.field_151344_d == var2.field_151344_d);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     return "TE(" + this.field_180329_a + ")," + this.eventID + "," + this.eventParameter + "," + this.field_151344_d;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockEventData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */