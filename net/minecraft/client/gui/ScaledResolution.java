/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ 
/*    */ public class ScaledResolution
/*    */ {
/*    */   private final double scaledWidthD;
/*    */   private final double scaledHeightD;
/*    */   private int scaledWidth;
/*    */   private int scaledHeight;
/*    */   private int scaleFactor;
/*    */   private static final String __OBFID = "CL_00000666";
/*    */   
/*    */   public ScaledResolution(Minecraft mcIn, int p_i46324_2_, int p_i46324_3_) {
/* 17 */     this.scaledWidth = p_i46324_2_;
/* 18 */     this.scaledHeight = p_i46324_3_;
/* 19 */     this.scaleFactor = 1;
/* 20 */     boolean var4 = mcIn.isUnicode();
/* 21 */     int var5 = mcIn.gameSettings.guiScale;
/*    */     
/* 23 */     if (var5 == 0)
/*    */     {
/* 25 */       var5 = 1000;
/*    */     }
/*    */     
/* 28 */     while (this.scaleFactor < var5 && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240)
/*    */     {
/* 30 */       this.scaleFactor++;
/*    */     }
/*    */     
/* 33 */     if (var4 && this.scaleFactor % 2 != 0 && this.scaleFactor != 1)
/*    */     {
/* 35 */       this.scaleFactor--;
/*    */     }
/*    */     
/* 38 */     this.scaledWidthD = this.scaledWidth / this.scaleFactor;
/* 39 */     this.scaledHeightD = this.scaledHeight / this.scaleFactor;
/* 40 */     this.scaledWidth = MathHelper.ceiling_double_int(this.scaledWidthD);
/* 41 */     this.scaledHeight = MathHelper.ceiling_double_int(this.scaledHeightD);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getScaledWidth() {
/* 46 */     return this.scaledWidth;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getScaledHeight() {
/* 51 */     return this.scaledHeight;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getScaledWidth_double() {
/* 56 */     return this.scaledWidthD;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getScaledHeight_double() {
/* 61 */     return this.scaledHeightD;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getScaleFactor() {
/* 66 */     return this.scaleFactor;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\ScaledResolution.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */