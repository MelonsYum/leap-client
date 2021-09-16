/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ import net.minecraft.util.BlockPos;
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
/*    */ public class DestroyBlockProgress
/*    */ {
/*    */   private final int miningPlayerEntId;
/*    */   private final BlockPos field_180247_b;
/*    */   private int partialBlockProgress;
/*    */   private int createdAtCloudUpdateTick;
/*    */   private static final String __OBFID = "CL_00001427";
/*    */   
/*    */   public DestroyBlockProgress(int p_i45925_1_, BlockPos p_i45925_2_) {
/* 27 */     this.miningPlayerEntId = p_i45925_1_;
/* 28 */     this.field_180247_b = p_i45925_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPos func_180246_b() {
/* 33 */     return this.field_180247_b;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPartialBlockDamage(int damage) {
/* 42 */     if (damage > 10)
/*    */     {
/* 44 */       damage = 10;
/*    */     }
/*    */     
/* 47 */     this.partialBlockProgress = damage;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPartialBlockDamage() {
/* 52 */     return this.partialBlockProgress;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCloudUpdateTick(int p_82744_1_) {
/* 60 */     this.createdAtCloudUpdateTick = p_82744_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCreationCloudUpdateTick() {
/* 68 */     return this.createdAtCloudUpdateTick;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\DestroyBlockProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */