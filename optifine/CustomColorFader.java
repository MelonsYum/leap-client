/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class CustomColorFader
/*    */ {
/*  7 */   private Vec3 color = null;
/*  8 */   private long timeUpdate = System.currentTimeMillis();
/*    */ 
/*    */   
/*    */   public Vec3 getColor(double x, double y, double z) {
/* 12 */     if (this.color == null) {
/*    */       
/* 14 */       this.color = new Vec3(x, y, z);
/* 15 */       return this.color;
/*    */     } 
/*    */ 
/*    */     
/* 19 */     long timeNow = System.currentTimeMillis();
/* 20 */     long timeDiff = timeNow - this.timeUpdate;
/*    */     
/* 22 */     if (timeDiff == 0L)
/*    */     {
/* 24 */       return this.color;
/*    */     }
/*    */ 
/*    */     
/* 28 */     this.timeUpdate = timeNow;
/*    */     
/* 30 */     if (Math.abs(x - this.color.xCoord) < 0.004D && Math.abs(y - this.color.yCoord) < 0.004D && Math.abs(z - this.color.zCoord) < 0.004D)
/*    */     {
/* 32 */       return this.color;
/*    */     }
/*    */ 
/*    */     
/* 36 */     double k = timeDiff * 0.001D;
/* 37 */     k = Config.limit(k, 0.0D, 1.0D);
/* 38 */     double dx = x - this.color.xCoord;
/* 39 */     double dy = y - this.color.yCoord;
/* 40 */     double dz = z - this.color.zCoord;
/* 41 */     double xn = this.color.xCoord + dx * k;
/* 42 */     double yn = this.color.yCoord + dy * k;
/* 43 */     double zn = this.color.zCoord + dz * k;
/* 44 */     this.color = new Vec3(xn, yn, zn);
/* 45 */     return this.color;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CustomColorFader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */