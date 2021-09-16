/*    */ package net.minecraft.world.border;
/*    */ 
/*    */ public enum EnumBorderStatus
/*    */ {
/*  5 */   GROWING("GROWING", 0, 4259712),
/*  6 */   SHRINKING("SHRINKING", 1, 16724016),
/*  7 */   STATIONARY("STATIONARY", 2, 2138367); private final int id;
/*    */   
/*    */   static {
/* 10 */     $VALUES = new EnumBorderStatus[] { GROWING, SHRINKING, STATIONARY };
/*    */   }
/*    */   private static final EnumBorderStatus[] $VALUES; private static final String __OBFID = "CL_00002013";
/*    */   
/*    */   EnumBorderStatus(String p_i45647_1_, int p_i45647_2_, int id) {
/* 15 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_177766_a() {
/* 20 */     return this.id;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\border\EnumBorderStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */