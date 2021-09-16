/*    */ package net.minecraft.client.renderer.vertex;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import net.minecraft.client.renderer.OpenGlHelper;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class VertexBuffer
/*    */ {
/*    */   private int field_177365_a;
/*    */   private final VertexFormat field_177363_b;
/*    */   private int field_177364_c;
/*    */   private static final String __OBFID = "CL_00002402";
/*    */   
/*    */   public VertexBuffer(VertexFormat p_i46098_1_) {
/* 16 */     this.field_177363_b = p_i46098_1_;
/* 17 */     this.field_177365_a = OpenGlHelper.func_176073_e();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177359_a() {
/* 22 */     OpenGlHelper.func_176072_g(OpenGlHelper.field_176089_P, this.field_177365_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177360_a(ByteBuffer p_177360_1_, int p_177360_2_) {
/* 27 */     func_177359_a();
/* 28 */     OpenGlHelper.func_176071_a(OpenGlHelper.field_176089_P, p_177360_1_, 35044);
/* 29 */     func_177361_b();
/* 30 */     this.field_177364_c = p_177360_2_ / this.field_177363_b.func_177338_f();
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177358_a(int p_177358_1_) {
/* 35 */     GL11.glDrawArrays(p_177358_1_, 0, this.field_177364_c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177361_b() {
/* 40 */     OpenGlHelper.func_176072_g(OpenGlHelper.field_176089_P, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_177362_c() {
/* 45 */     if (this.field_177365_a >= 0) {
/*    */       
/* 47 */       OpenGlHelper.func_176074_g(this.field_177365_a);
/* 48 */       this.field_177365_a = -1;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\vertex\VertexBuffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */