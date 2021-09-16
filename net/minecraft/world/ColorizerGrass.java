/*    */ package net.minecraft.world;
/*    */ 
/*    */ 
/*    */ public class ColorizerGrass
/*    */ {
/*  6 */   private static int[] grassBuffer = new int[65536];
/*    */   
/*    */   private static final String __OBFID = "CL_00000138";
/*    */   
/*    */   public static void setGrassBiomeColorizer(int[] p_77479_0_) {
/* 11 */     grassBuffer = p_77479_0_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getGrassColor(double p_77480_0_, double p_77480_2_) {
/* 19 */     p_77480_2_ *= p_77480_0_;
/* 20 */     int var4 = (int)((1.0D - p_77480_0_) * 255.0D);
/* 21 */     int var5 = (int)((1.0D - p_77480_2_) * 255.0D);
/* 22 */     int var6 = var5 << 8 | var4;
/* 23 */     return (var6 > grassBuffer.length) ? -65281 : grassBuffer[var6];
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\ColorizerGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */