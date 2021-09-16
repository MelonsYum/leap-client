/*    */ package net.minecraft.world;
/*    */ 
/*    */ public enum EnumSkyBlock
/*    */ {
/*  5 */   SKY("SKY", 0, 15),
/*  6 */   BLOCK("BLOCK", 1, 0); public final int defaultLightValue;
/*    */   
/*    */   static {
/*  9 */     $VALUES = new EnumSkyBlock[] { SKY, BLOCK };
/*    */   }
/*    */   private static final EnumSkyBlock[] $VALUES; private static final String __OBFID = "CL_00000151";
/*    */   
/*    */   EnumSkyBlock(String p_i1961_1_, int p_i1961_2_, int p_i1961_3_) {
/* 14 */     this.defaultLightValue = p_i1961_3_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\EnumSkyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */