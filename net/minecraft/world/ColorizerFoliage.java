/*    */ package net.minecraft.world;
/*    */ 
/*    */ 
/*    */ public class ColorizerFoliage
/*    */ {
/*  6 */   private static int[] foliageBuffer = new int[65536];
/*    */   
/*    */   private static final String __OBFID = "CL_00000135";
/*    */   
/*    */   public static void setFoliageBiomeColorizer(int[] p_77467_0_) {
/* 11 */     foliageBuffer = p_77467_0_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getFoliageColor(double p_77470_0_, double p_77470_2_) {
/* 19 */     p_77470_2_ *= p_77470_0_;
/* 20 */     int var4 = (int)((1.0D - p_77470_0_) * 255.0D);
/* 21 */     int var5 = (int)((1.0D - p_77470_2_) * 255.0D);
/* 22 */     return foliageBuffer[var5 << 8 | var4];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getFoliageColorPine() {
/* 30 */     return 6396257;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getFoliageColorBirch() {
/* 38 */     return 8431445;
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getFoliageColorBasic() {
/* 43 */     return 4764952;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\ColorizerFoliage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */