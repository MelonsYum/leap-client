/*    */ package net.minecraft.client.resources.data;
/*    */ 
/*    */ 
/*    */ public class AnimationFrame
/*    */ {
/*    */   private final int frameIndex;
/*    */   private final int frameTime;
/*    */   private static final String __OBFID = "CL_00001104";
/*    */   
/*    */   public AnimationFrame(int p_i1307_1_) {
/* 11 */     this(p_i1307_1_, -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public AnimationFrame(int p_i1308_1_, int p_i1308_2_) {
/* 16 */     this.frameIndex = p_i1308_1_;
/* 17 */     this.frameTime = p_i1308_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNoTime() {
/* 22 */     return (this.frameTime == -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFrameTime() {
/* 27 */     return this.frameTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFrameIndex() {
/* 32 */     return this.frameIndex;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\AnimationFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */