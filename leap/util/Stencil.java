/*    */ package leap.util;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.shader.Framebuffer;
/*    */ import org.lwjgl.opengl.EXTFramebufferObject;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class Stencil
/*    */ {
/* 12 */   static Minecraft mc = Minecraft.getMinecraft();
/*    */   
/*    */   public static void dispose() {
/* 15 */     GL11.glDisable(2960);
/* 16 */     GlStateManager.disableAlpha();
/* 17 */     GlStateManager.disableBlend();
/*    */   }
/*    */   
/*    */   public static void erase(boolean invert) {
/* 21 */     GL11.glStencilFunc(invert ? 514 : 517, 1, 65535);
/* 22 */     GL11.glStencilOp(7680, 7680, 7681);
/* 23 */     GlStateManager.colorMask(true, true, true, true);
/* 24 */     GlStateManager.enableAlpha();
/* 25 */     GlStateManager.enableBlend();
/* 26 */     GL11.glAlphaFunc(516, 0.0F);
/*    */   }
/*    */   
/*    */   public static void write(boolean renderClipLayer) {
/* 30 */     checkSetupFBO();
/* 31 */     GL11.glClearStencil(0);
/* 32 */     GL11.glClear(1024);
/* 33 */     GL11.glEnable(2960);
/* 34 */     GL11.glStencilFunc(519, 1, 65535);
/* 35 */     GL11.glStencilOp(7680, 7680, 7681);
/* 36 */     if (!renderClipLayer) {
/* 37 */       GlStateManager.colorMask(false, false, false, false);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void checkSetupFBO() {
/* 42 */     Framebuffer fbo = mc.getFramebuffer();
/* 43 */     if (fbo != null && fbo.depthBuffer > -1) {
/* 44 */       setupFBO(fbo);
/* 45 */       fbo.depthBuffer = -1;
/*    */     } 
/*    */   }
/*    */   
/*    */   public static void setupFBO(Framebuffer fbo) {
/* 50 */     EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.depthBuffer);
/* 51 */     int stencil_depth_buffer_ID = EXTFramebufferObject.glGenRenderbuffersEXT();
/* 52 */     EXTFramebufferObject.glBindRenderbufferEXT(36161, stencil_depth_buffer_ID);
/* 53 */     EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, (Minecraft.getMinecraft()).displayWidth, (Minecraft.getMinecraft()).displayHeight);
/* 54 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencil_depth_buffer_ID);
/* 55 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencil_depth_buffer_ID);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\Stencil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */