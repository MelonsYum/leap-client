/*    */ package net.minecraft.dispenser;
/*    */ 
/*    */ public class PositionImpl
/*    */   implements IPosition
/*    */ {
/*    */   protected final double x;
/*    */   protected final double y;
/*    */   protected final double z;
/*    */   private static final String __OBFID = "CL_00001208";
/*    */   
/*    */   public PositionImpl(double xCoord, double yCoord, double zCoord) {
/* 12 */     this.x = xCoord;
/* 13 */     this.y = yCoord;
/* 14 */     this.z = zCoord;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getX() {
/* 19 */     return this.x;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getY() {
/* 24 */     return this.y;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getZ() {
/* 29 */     return this.z;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\dispenser\PositionImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */