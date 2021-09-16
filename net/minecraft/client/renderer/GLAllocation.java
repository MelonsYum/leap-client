/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ByteOrder;
/*    */ import java.nio.FloatBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import org.lwjgl.util.glu.GLU;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GLAllocation
/*    */ {
/*    */   private static final String __OBFID = "CL_00000630";
/*    */   
/*    */   public static synchronized int generateDisplayLists(int p_74526_0_) {
/* 19 */     int var1 = GL11.glGenLists(p_74526_0_);
/*    */     
/* 21 */     if (var1 == 0) {
/*    */       
/* 23 */       int var2 = GL11.glGetError();
/* 24 */       String var3 = "No error code reported";
/*    */       
/* 26 */       if (var2 != 0)
/*    */       {
/* 28 */         var3 = GLU.gluErrorString(var2);
/*    */       }
/*    */       
/* 31 */       throw new IllegalStateException("glGenLists returned an ID of 0 for a count of " + p_74526_0_ + ", GL error (" + var2 + "): " + var3);
/*    */     } 
/*    */ 
/*    */     
/* 35 */     return var1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized void func_178874_a(int p_178874_0_, int p_178874_1_) {
/* 41 */     GL11.glDeleteLists(p_178874_0_, p_178874_1_);
/*    */   }
/*    */ 
/*    */   
/*    */   public static synchronized void deleteDisplayLists(int p_74523_0_) {
/* 46 */     GL11.glDeleteLists(p_74523_0_, 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static synchronized ByteBuffer createDirectByteBuffer(int p_74524_0_) {
/* 54 */     return ByteBuffer.allocateDirect(p_74524_0_).order(ByteOrder.nativeOrder());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static IntBuffer createDirectIntBuffer(int p_74527_0_) {
/* 62 */     return createDirectByteBuffer(p_74527_0_ << 2).asIntBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static FloatBuffer createDirectFloatBuffer(int p_74529_0_) {
/* 71 */     return createDirectByteBuffer(p_74529_0_ << 2).asFloatBuffer();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\GLAllocation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */