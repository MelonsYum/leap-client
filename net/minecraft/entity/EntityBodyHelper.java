/*    */ package net.minecraft.entity;
/*    */ 
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityBodyHelper
/*    */ {
/*    */   private EntityLivingBase theLiving;
/*    */   private int rotationTickCounter;
/*    */   private float prevRenderYawHead;
/*    */   private static final String __OBFID = "CL_00001570";
/*    */   
/*    */   public EntityBodyHelper(EntityLivingBase p_i1611_1_) {
/* 19 */     this.theLiving = p_i1611_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateRenderAngles() {
/* 27 */     double var1 = this.theLiving.posX - this.theLiving.prevPosX;
/* 28 */     double var3 = this.theLiving.posZ - this.theLiving.prevPosZ;
/*    */     
/* 30 */     if (var1 * var1 + var3 * var3 > 2.500000277905201E-7D) {
/*    */       
/* 32 */       this.theLiving.renderYawOffset = this.theLiving.rotationYaw;
/* 33 */       this.theLiving.rotationYawHead = computeAngleWithBound(this.theLiving.renderYawOffset, this.theLiving.rotationYawHead, 75.0F);
/* 34 */       this.prevRenderYawHead = this.theLiving.rotationYawHead;
/* 35 */       this.rotationTickCounter = 0;
/*    */     }
/*    */     else {
/*    */       
/* 39 */       float var5 = 75.0F;
/*    */       
/* 41 */       if (Math.abs(this.theLiving.rotationYawHead - this.prevRenderYawHead) > 15.0F) {
/*    */         
/* 43 */         this.rotationTickCounter = 0;
/* 44 */         this.prevRenderYawHead = this.theLiving.rotationYawHead;
/*    */       }
/*    */       else {
/*    */         
/* 48 */         this.rotationTickCounter++;
/* 49 */         boolean var6 = true;
/*    */         
/* 51 */         if (this.rotationTickCounter > 10)
/*    */         {
/* 53 */           var5 = Math.max(1.0F - (this.rotationTickCounter - 10) / 10.0F, 0.0F) * 75.0F;
/*    */         }
/*    */       } 
/*    */       
/* 57 */       this.theLiving.renderYawOffset = computeAngleWithBound(this.theLiving.rotationYawHead, this.theLiving.renderYawOffset, var5);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private float computeAngleWithBound(float p_75665_1_, float p_75665_2_, float p_75665_3_) {
/* 67 */     float var4 = MathHelper.wrapAngleTo180_float(p_75665_1_ - p_75665_2_);
/*    */     
/* 69 */     if (var4 < -p_75665_3_)
/*    */     {
/* 71 */       var4 = -p_75665_3_;
/*    */     }
/*    */     
/* 74 */     if (var4 >= p_75665_3_)
/*    */     {
/* 76 */       var4 = p_75665_3_;
/*    */     }
/*    */     
/* 79 */     return p_75665_1_ - var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityBodyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */