/*    */ package net.minecraft.client.renderer;public enum EnumFaceing { private static final EnumFaceing[] field_179029_g; private final VertexInformation[] field_179035_h; private static final EnumFaceing[] $VALUES; private static final String __OBFID = "CL_00002562";
/*    */   
/*    */   public static EnumFaceing func_179027_a(EnumFacing p_179027_0_) {
/*    */     return field_179029_g[p_179027_0_.getIndex()];
/*    */   }
/*    */   
/*  7 */   DOWN("DOWN", 0, new VertexInformation[] { new VertexInformation(Constants.field_179176_f, Constants.field_179178_e, Constants.field_179181_a, null), new VertexInformation(Constants.field_179176_f, Constants.field_179178_e, Constants.field_179177_d, null), new VertexInformation(Constants.field_179180_c, Constants.field_179178_e, Constants.field_179177_d, null), new VertexInformation(Constants.field_179180_c, Constants.field_179178_e, Constants.field_179181_a, null) }),
/*  8 */   UP("UP", 1, new VertexInformation[] { new VertexInformation(Constants.field_179176_f, Constants.field_179179_b, Constants.field_179177_d, null), new VertexInformation(Constants.field_179176_f, Constants.field_179179_b, Constants.field_179181_a, null), new VertexInformation(Constants.field_179180_c, Constants.field_179179_b, Constants.field_179181_a, null), new VertexInformation(Constants.field_179180_c, Constants.field_179179_b, Constants.field_179177_d, null) }),
/*  9 */   NORTH("NORTH", 2, new VertexInformation[] { new VertexInformation(Constants.field_179180_c, Constants.field_179179_b, Constants.field_179177_d, null), new VertexInformation(Constants.field_179180_c, Constants.field_179178_e, Constants.field_179177_d, null), new VertexInformation(Constants.field_179176_f, Constants.field_179178_e, Constants.field_179177_d, null), new VertexInformation(Constants.field_179176_f, Constants.field_179179_b, Constants.field_179177_d, null) }),
/* 10 */   SOUTH("SOUTH", 3, new VertexInformation[] { new VertexInformation(Constants.field_179176_f, Constants.field_179179_b, Constants.field_179181_a, null), new VertexInformation(Constants.field_179176_f, Constants.field_179178_e, Constants.field_179181_a, null), new VertexInformation(Constants.field_179180_c, Constants.field_179178_e, Constants.field_179181_a, null), new VertexInformation(Constants.field_179180_c, Constants.field_179179_b, Constants.field_179181_a, null) }),
/* 11 */   WEST("WEST", 4, new VertexInformation[] { new VertexInformation(Constants.field_179176_f, Constants.field_179179_b, Constants.field_179177_d, null), new VertexInformation(Constants.field_179176_f, Constants.field_179178_e, Constants.field_179177_d, null), new VertexInformation(Constants.field_179176_f, Constants.field_179178_e, Constants.field_179181_a, null), new VertexInformation(Constants.field_179176_f, Constants.field_179179_b, Constants.field_179181_a, null) }),
/* 12 */   EAST("EAST", 5, new VertexInformation[] { new VertexInformation(Constants.field_179180_c, Constants.field_179179_b, Constants.field_179181_a, null), new VertexInformation(Constants.field_179180_c, Constants.field_179178_e, Constants.field_179181_a, null), new VertexInformation(Constants.field_179180_c, Constants.field_179178_e, Constants.field_179177_d, null), new VertexInformation(Constants.field_179180_c, Constants.field_179179_b, Constants.field_179177_d, null) });
/* 13 */   EnumFaceing(String p_i46272_1_, int p_i46272_2_, VertexInformation... p_i46272_3_) { this.field_179035_h = p_i46272_3_; } static { field_179029_g = new EnumFaceing[6];
/*    */ 
/*    */     
/* 16 */     $VALUES = new EnumFaceing[] { DOWN, UP, NORTH, SOUTH, WEST, EAST };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 35 */     field_179029_g[Constants.field_179178_e] = DOWN;
/* 36 */     field_179029_g[Constants.field_179179_b] = UP;
/* 37 */     field_179029_g[Constants.field_179177_d] = NORTH;
/* 38 */     field_179029_g[Constants.field_179181_a] = SOUTH;
/* 39 */     field_179029_g[Constants.field_179176_f] = WEST;
/* 40 */     field_179029_g[Constants.field_179180_c] = EAST; }
/*    */    public VertexInformation func_179025_a(int p_179025_1_) {
/*    */     return this.field_179035_h[p_179025_1_];
/*    */   }
/* 44 */   public static final class Constants { public static final int field_179181_a = EnumFacing.SOUTH.getIndex();
/* 45 */     public static final int field_179179_b = EnumFacing.UP.getIndex();
/* 46 */     public static final int field_179180_c = EnumFacing.EAST.getIndex();
/* 47 */     public static final int field_179177_d = EnumFacing.NORTH.getIndex();
/* 48 */     public static final int field_179178_e = EnumFacing.DOWN.getIndex();
/* 49 */     public static final int field_179176_f = EnumFacing.WEST.getIndex();
/*    */     private static final String __OBFID = "CL_00002560"; }
/*    */ 
/*    */   
/*    */   public static class VertexInformation
/*    */   {
/*    */     public final int field_179184_a;
/*    */     public final int field_179182_b;
/*    */     public final int field_179183_c;
/*    */     private static final String __OBFID = "CL_00002559";
/*    */     
/*    */     private VertexInformation(int p_i46270_1_, int p_i46270_2_, int p_i46270_3_) {
/* 61 */       this.field_179184_a = p_i46270_1_;
/* 62 */       this.field_179182_b = p_i46270_2_;
/* 63 */       this.field_179183_c = p_i46270_3_;
/*    */     }
/*    */ 
/*    */     
/*    */     VertexInformation(int p_i46271_1_, int p_i46271_2_, int p_i46271_3_, Object p_i46271_4_) {
/* 68 */       this(p_i46271_1_, p_i46271_2_, p_i46271_3_);
/*    */     }
/*    */   } }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\EnumFaceing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */