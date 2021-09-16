/*    */ package net.minecraft.client.renderer.culling;
/*    */ 
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ 
/*    */ public class Frustrum
/*    */   implements ICamera
/*    */ {
/*    */   private ClippingHelper clippingHelper;
/*    */   private double xPosition;
/*    */   private double yPosition;
/*    */   private double zPosition;
/*    */   private static final String __OBFID = "CL_00000976";
/*    */   
/*    */   public Frustrum() {
/* 15 */     this(ClippingHelperImpl.getInstance());
/*    */   }
/*    */ 
/*    */   
/*    */   public Frustrum(ClippingHelper p_i46196_1_) {
/* 20 */     this.clippingHelper = p_i46196_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPosition(double p_78547_1_, double p_78547_3_, double p_78547_5_) {
/* 25 */     this.xPosition = p_78547_1_;
/* 26 */     this.yPosition = p_78547_3_;
/* 27 */     this.zPosition = p_78547_5_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBoxInFrustum(double p_78548_1_, double p_78548_3_, double p_78548_5_, double p_78548_7_, double p_78548_9_, double p_78548_11_) {
/* 35 */     return this.clippingHelper.isBoxInFrustum(p_78548_1_ - this.xPosition, p_78548_3_ - this.yPosition, p_78548_5_ - this.zPosition, p_78548_7_ - this.xPosition, p_78548_9_ - this.yPosition, p_78548_11_ - this.zPosition);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isBoundingBoxInFrustum(AxisAlignedBB p_78546_1_) {
/* 43 */     return isBoxInFrustum(p_78546_1_.minX, p_78546_1_.minY, p_78546_1_.minZ, p_78546_1_.maxX, p_78546_1_.maxY, p_78546_1_.maxZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\culling\Frustrum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */