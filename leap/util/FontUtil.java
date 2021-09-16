/*    */ package leap.util;
/*    */ 
/*    */ import leap.Client;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ public class FontUtil
/*    */ {
/*  8 */   private static final Minecraft mc = Minecraft.getMinecraft();
/*    */   
/*    */   public static float drawStringWithShadow(boolean customFont, String text, int x, int y, JColor color) {
/* 11 */     if (customFont) {
/* 12 */       return Client.customFontRenderer.drawStringWithShadow(text, x, y, color);
/*    */     }
/*    */     
/* 15 */     return mc.fontRendererObj.drawStringWithShadow(text, x, y, color.getRGB());
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getStringWidth(boolean customFont, String string) {
/* 20 */     if (customFont) {
/* 21 */       return Client.customFontRenderer.getStringWidth(string);
/*    */     }
/*    */     
/* 24 */     return mc.fontRendererObj.getStringWidth(string);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getFontHeight(boolean customFont) {
/* 29 */     if (customFont) {
/* 30 */       return Client.customFontRenderer.getHeight();
/*    */     }
/*    */     
/* 33 */     return mc.fontRendererObj.FONT_HEIGHT;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\FontUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */