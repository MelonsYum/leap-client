/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import net.minecraft.util.Vec3;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class RenderHelper
/*    */ {
/* 10 */   private static FloatBuffer colorBuffer = GLAllocation.createDirectFloatBuffer(16);
/* 11 */   private static final Vec3 field_82884_b = (new Vec3(0.20000000298023224D, 1.0D, -0.699999988079071D)).normalize();
/* 12 */   private static final Vec3 field_82885_c = (new Vec3(-0.20000000298023224D, 1.0D, 0.699999988079071D)).normalize();
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00000629";
/*    */ 
/*    */ 
/*    */   
/*    */   public static void disableStandardItemLighting() {
/* 20 */     GlStateManager.disableLighting();
/* 21 */     GlStateManager.disableBooleanStateAt(0);
/* 22 */     GlStateManager.disableBooleanStateAt(1);
/* 23 */     GlStateManager.disableColorMaterial();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void enableStandardItemLighting() {
/* 31 */     GlStateManager.enableLighting();
/* 32 */     GlStateManager.enableBooleanStateAt(0);
/* 33 */     GlStateManager.enableBooleanStateAt(1);
/* 34 */     GlStateManager.enableColorMaterial();
/* 35 */     GlStateManager.colorMaterial(1032, 5634);
/* 36 */     float var0 = 0.4F;
/* 37 */     float var1 = 0.6F;
/* 38 */     float var2 = 0.0F;
/* 39 */     GL11.glLight(16384, 4611, setColorBuffer(field_82884_b.xCoord, field_82884_b.yCoord, field_82884_b.zCoord, 0.0D));
/* 40 */     GL11.glLight(16384, 4609, setColorBuffer(var1, var1, var1, 1.0F));
/* 41 */     GL11.glLight(16384, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 42 */     GL11.glLight(16384, 4610, setColorBuffer(var2, var2, var2, 1.0F));
/* 43 */     GL11.glLight(16385, 4611, setColorBuffer(field_82885_c.xCoord, field_82885_c.yCoord, field_82885_c.zCoord, 0.0D));
/* 44 */     GL11.glLight(16385, 4609, setColorBuffer(var1, var1, var1, 1.0F));
/* 45 */     GL11.glLight(16385, 4608, setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
/* 46 */     GL11.glLight(16385, 4610, setColorBuffer(var2, var2, var2, 1.0F));
/* 47 */     GlStateManager.shadeModel(7424);
/* 48 */     GL11.glLightModel(2899, setColorBuffer(var0, var0, var0, 1.0F));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static FloatBuffer setColorBuffer(double p_74517_0_, double p_74517_2_, double p_74517_4_, double p_74517_6_) {
/* 56 */     return setColorBuffer((float)p_74517_0_, (float)p_74517_2_, (float)p_74517_4_, (float)p_74517_6_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static FloatBuffer setColorBuffer(float p_74521_0_, float p_74521_1_, float p_74521_2_, float p_74521_3_) {
/* 64 */     colorBuffer.clear();
/* 65 */     colorBuffer.put(p_74521_0_).put(p_74521_1_).put(p_74521_2_).put(p_74521_3_);
/* 66 */     colorBuffer.flip();
/* 67 */     return colorBuffer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void enableGUIStandardItemLighting() {
/* 75 */     GlStateManager.pushMatrix();
/* 76 */     GlStateManager.rotate(-30.0F, 0.0F, 1.0F, 0.0F);
/* 77 */     GlStateManager.rotate(165.0F, 1.0F, 0.0F, 0.0F);
/* 78 */     enableStandardItemLighting();
/* 79 */     GlStateManager.popMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\RenderHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */