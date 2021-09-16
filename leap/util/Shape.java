/*    */ package leap.util;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class Shape
/*    */ {
/*    */   public static void drawRoundedRect(double x, double y, double width, double height, float cornerRadius) {
/* 11 */     int slices = 10;
/*    */     
/* 13 */     drawFillRectangle(x + cornerRadius, y, width - (2.0F * cornerRadius), height);
/* 14 */     drawFillRectangle(x, y + cornerRadius, cornerRadius, height - (2.0F * cornerRadius));
/* 15 */     drawFillRectangle(x + width - cornerRadius, y + cornerRadius, cornerRadius, height - (2.0F * cornerRadius));
/*    */     
/* 17 */     drawCirclePart(x + cornerRadius, y + cornerRadius, -3.1415927F, -1.5707964F, cornerRadius, 10);
/* 18 */     drawCirclePart(x + cornerRadius, y + height - cornerRadius, -1.5707964F, 0.0F, cornerRadius, 10);
/*    */     
/* 20 */     drawCirclePart(x + width - cornerRadius, y + cornerRadius, 1.5707964F, 3.1415927F, cornerRadius, 10);
/* 21 */     drawCirclePart(x + width - cornerRadius, y + height - cornerRadius, 0.0F, 1.5707964F, cornerRadius, 10);
/*    */     
/* 23 */     GL11.glDisable(3042);
/* 24 */     GL11.glEnable(3553);
/*    */     
/* 26 */     GlStateManager.disableBlend();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void drawFillRectangle(double x, double y, double width, double height) {
/* 33 */     GlStateManager.enableBlend();
/* 34 */     GL11.glBlendFunc(770, 771);
/* 35 */     GL11.glDisable(3553);
/* 36 */     GL11.glBegin(7);
/* 37 */     GL11.glVertex2d(x, y + height);
/* 38 */     GL11.glVertex2d(x + width, y + height);
/* 39 */     GL11.glVertex2d(x + width, y);
/* 40 */     GL11.glVertex2d(x, y);
/* 41 */     GL11.glEnd();
/*    */   }
/*    */   
/*    */   public static void drawCirclePart(double x, double y, float fromAngle, float toAngle, float radius, int slices) {
/* 45 */     GlStateManager.enableBlend();
/* 46 */     GL11.glBegin(6);
/* 47 */     GL11.glVertex2d(x, y);
/* 48 */     float increment = (toAngle - fromAngle) / slices;
/*    */     
/* 50 */     for (int i = 0; i <= slices; i++) {
/* 51 */       float angle = fromAngle + i * increment;
/*    */       
/* 53 */       float dX = MathHelper.sin(angle);
/* 54 */       float dY = MathHelper.cos(angle);
/*    */       
/* 56 */       GL11.glVertex2d(x + (dX * radius), y + (dY * radius));
/*    */     } 
/* 58 */     GL11.glEnd();
/*    */   }
/*    */   
/*    */   public static void color(int color) {
/* 62 */     float red = (color & 0xFF) / 255.0F;
/* 63 */     float green = (color >> 8 & 0xFF) / 255.0F;
/* 64 */     float blue = (color >> 16 & 0xFF) / 255.0F;
/* 65 */     float alpha = (color >> 24 & 0xFF) / 255.0F;
/*    */     
/* 67 */     GlStateManager.color(red, green, blue, alpha);
/*    */   }
/*    */   
/*    */   public static void colorRGBA(int color) {
/* 71 */     float a = (color >> 24 & 0xFF) / 255.0F;
/* 72 */     float r = (color >> 16 & 0xFF) / 255.0F;
/* 73 */     float g = (color >> 8 & 0xFF) / 255.0F;
/* 74 */     float b = (color & 0xFF) / 255.0F;
/*    */     
/* 76 */     GlStateManager.color(r, g, b, a);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\Shape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */