/*    */ package net.minecraft.entity.boss;
/*    */ 
/*    */ 
/*    */ public final class BossStatus
/*    */ {
/*    */   public static float healthScale;
/*    */   public static int statusBarTime;
/*    */   public static String bossName;
/*    */   public static boolean hasColorModifier;
/*    */   private static final String __OBFID = "CL_00000941";
/*    */   
/*    */   public static void setBossStatus(IBossDisplayData p_82824_0_, boolean p_82824_1_) {
/* 13 */     healthScale = p_82824_0_.getHealth() / p_82824_0_.getMaxHealth();
/* 14 */     statusBarTime = 100;
/* 15 */     bossName = p_82824_0_.getDisplayName().getFormattedText();
/* 16 */     hasColorModifier = p_82824_1_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\boss\BossStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */