/*    */ package net.minecraft.potion;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class PotionHealth
/*    */   extends Potion
/*    */ {
/*    */   private static final String __OBFID = "CL_00001527";
/*    */   
/*    */   public PotionHealth(int p_i45898_1_, ResourceLocation p_i45898_2_, boolean p_i45898_3_, int p_i45898_4_) {
/* 11 */     super(p_i45898_1_, p_i45898_2_, p_i45898_3_, p_i45898_4_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInstant() {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isReady(int p_76397_1_, int p_76397_2_) {
/* 27 */     return (p_76397_1_ >= 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\potion\PotionHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */