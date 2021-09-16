/*    */ package net.minecraft.util;
/*    */ 
/*    */ public enum EnumWorldBlockLayer
/*    */ {
/*  5 */   SOLID("SOLID", 0, "Solid"),
/*  6 */   CUTOUT_MIPPED("CUTOUT_MIPPED", 1, "Mipped Cutout"),
/*  7 */   CUTOUT("CUTOUT", 2, "Cutout"),
/*  8 */   TRANSLUCENT("TRANSLUCENT", 3, "Translucent"); private final String field_180338_e;
/*    */   
/*    */   static {
/* 11 */     $VALUES = new EnumWorldBlockLayer[] { SOLID, CUTOUT_MIPPED, CUTOUT, TRANSLUCENT };
/*    */   }
/*    */   private static final EnumWorldBlockLayer[] $VALUES; private static final String __OBFID = "CL_00002152";
/*    */   
/*    */   EnumWorldBlockLayer(String p_i45755_1_, int p_i45755_2_, String p_i45755_3_) {
/* 16 */     this.field_180338_e = p_i45755_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return this.field_180338_e;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\EnumWorldBlockLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */