/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ import net.minecraft.client.renderer.texture.Stitcher;
/*    */ 
/*    */ public class StitcherException
/*    */   extends RuntimeException
/*    */ {
/*    */   private final Stitcher.Holder field_98149_a;
/*    */   private static final String __OBFID = "CL_00001057";
/*    */   
/*    */   public StitcherException(Stitcher.Holder p_i2344_1_, String p_i2344_2_) {
/* 12 */     super(p_i2344_2_);
/* 13 */     this.field_98149_a = p_i2344_1_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\StitcherException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */