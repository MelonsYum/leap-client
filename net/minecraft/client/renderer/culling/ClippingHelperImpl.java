/*    */ package net.minecraft.client.renderer.culling;
/*    */ 
/*    */ import java.nio.FloatBuffer;
/*    */ import net.minecraft.client.renderer.GLAllocation;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ClippingHelperImpl
/*    */   extends ClippingHelper {
/* 10 */   private static ClippingHelperImpl instance = new ClippingHelperImpl();
/* 11 */   private FloatBuffer projectionMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);
/* 12 */   private FloatBuffer modelviewMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);
/* 13 */   private FloatBuffer field_78564_h = GLAllocation.createDirectFloatBuffer(16);
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00000975";
/*    */ 
/*    */ 
/*    */   
/*    */   public static ClippingHelper getInstance() {
/* 21 */     instance.init();
/* 22 */     return instance;
/*    */   }
/*    */ 
/*    */   
/*    */   private void func_180547_a(float[] p_180547_1_) {
/* 27 */     float var2 = MathHelper.sqrt_float(p_180547_1_[0] * p_180547_1_[0] + p_180547_1_[1] * p_180547_1_[1] + p_180547_1_[2] * p_180547_1_[2]);
/* 28 */     p_180547_1_[0] = p_180547_1_[0] / var2;
/* 29 */     p_180547_1_[1] = p_180547_1_[1] / var2;
/* 30 */     p_180547_1_[2] = p_180547_1_[2] / var2;
/* 31 */     p_180547_1_[3] = p_180547_1_[3] / var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void init() {
/* 36 */     this.projectionMatrixBuffer.clear();
/* 37 */     this.modelviewMatrixBuffer.clear();
/* 38 */     this.field_78564_h.clear();
/* 39 */     GlStateManager.getFloat(2983, this.projectionMatrixBuffer);
/* 40 */     GlStateManager.getFloat(2982, this.modelviewMatrixBuffer);
/* 41 */     float[] var1 = this.field_178625_b;
/* 42 */     float[] var2 = this.field_178626_c;
/* 43 */     this.projectionMatrixBuffer.flip().limit(16);
/* 44 */     this.projectionMatrixBuffer.get(var1);
/* 45 */     this.modelviewMatrixBuffer.flip().limit(16);
/* 46 */     this.modelviewMatrixBuffer.get(var2);
/* 47 */     this.clippingMatrix[0] = var2[0] * var1[0] + var2[1] * var1[4] + var2[2] * var1[8] + var2[3] * var1[12];
/* 48 */     this.clippingMatrix[1] = var2[0] * var1[1] + var2[1] * var1[5] + var2[2] * var1[9] + var2[3] * var1[13];
/* 49 */     this.clippingMatrix[2] = var2[0] * var1[2] + var2[1] * var1[6] + var2[2] * var1[10] + var2[3] * var1[14];
/* 50 */     this.clippingMatrix[3] = var2[0] * var1[3] + var2[1] * var1[7] + var2[2] * var1[11] + var2[3] * var1[15];
/* 51 */     this.clippingMatrix[4] = var2[4] * var1[0] + var2[5] * var1[4] + var2[6] * var1[8] + var2[7] * var1[12];
/* 52 */     this.clippingMatrix[5] = var2[4] * var1[1] + var2[5] * var1[5] + var2[6] * var1[9] + var2[7] * var1[13];
/* 53 */     this.clippingMatrix[6] = var2[4] * var1[2] + var2[5] * var1[6] + var2[6] * var1[10] + var2[7] * var1[14];
/* 54 */     this.clippingMatrix[7] = var2[4] * var1[3] + var2[5] * var1[7] + var2[6] * var1[11] + var2[7] * var1[15];
/* 55 */     this.clippingMatrix[8] = var2[8] * var1[0] + var2[9] * var1[4] + var2[10] * var1[8] + var2[11] * var1[12];
/* 56 */     this.clippingMatrix[9] = var2[8] * var1[1] + var2[9] * var1[5] + var2[10] * var1[9] + var2[11] * var1[13];
/* 57 */     this.clippingMatrix[10] = var2[8] * var1[2] + var2[9] * var1[6] + var2[10] * var1[10] + var2[11] * var1[14];
/* 58 */     this.clippingMatrix[11] = var2[8] * var1[3] + var2[9] * var1[7] + var2[10] * var1[11] + var2[11] * var1[15];
/* 59 */     this.clippingMatrix[12] = var2[12] * var1[0] + var2[13] * var1[4] + var2[14] * var1[8] + var2[15] * var1[12];
/* 60 */     this.clippingMatrix[13] = var2[12] * var1[1] + var2[13] * var1[5] + var2[14] * var1[9] + var2[15] * var1[13];
/* 61 */     this.clippingMatrix[14] = var2[12] * var1[2] + var2[13] * var1[6] + var2[14] * var1[10] + var2[15] * var1[14];
/* 62 */     this.clippingMatrix[15] = var2[12] * var1[3] + var2[13] * var1[7] + var2[14] * var1[11] + var2[15] * var1[15];
/* 63 */     float[] var3 = this.frustum[0];
/* 64 */     var3[0] = this.clippingMatrix[3] - this.clippingMatrix[0];
/* 65 */     var3[1] = this.clippingMatrix[7] - this.clippingMatrix[4];
/* 66 */     var3[2] = this.clippingMatrix[11] - this.clippingMatrix[8];
/* 67 */     var3[3] = this.clippingMatrix[15] - this.clippingMatrix[12];
/* 68 */     func_180547_a(var3);
/* 69 */     float[] var4 = this.frustum[1];
/* 70 */     var4[0] = this.clippingMatrix[3] + this.clippingMatrix[0];
/* 71 */     var4[1] = this.clippingMatrix[7] + this.clippingMatrix[4];
/* 72 */     var4[2] = this.clippingMatrix[11] + this.clippingMatrix[8];
/* 73 */     var4[3] = this.clippingMatrix[15] + this.clippingMatrix[12];
/* 74 */     func_180547_a(var4);
/* 75 */     float[] var5 = this.frustum[2];
/* 76 */     var5[0] = this.clippingMatrix[3] + this.clippingMatrix[1];
/* 77 */     var5[1] = this.clippingMatrix[7] + this.clippingMatrix[5];
/* 78 */     var5[2] = this.clippingMatrix[11] + this.clippingMatrix[9];
/* 79 */     var5[3] = this.clippingMatrix[15] + this.clippingMatrix[13];
/* 80 */     func_180547_a(var5);
/* 81 */     float[] var6 = this.frustum[3];
/* 82 */     var6[0] = this.clippingMatrix[3] - this.clippingMatrix[1];
/* 83 */     var6[1] = this.clippingMatrix[7] - this.clippingMatrix[5];
/* 84 */     var6[2] = this.clippingMatrix[11] - this.clippingMatrix[9];
/* 85 */     var6[3] = this.clippingMatrix[15] - this.clippingMatrix[13];
/* 86 */     func_180547_a(var6);
/* 87 */     float[] var7 = this.frustum[4];
/* 88 */     var7[0] = this.clippingMatrix[3] - this.clippingMatrix[2];
/* 89 */     var7[1] = this.clippingMatrix[7] - this.clippingMatrix[6];
/* 90 */     var7[2] = this.clippingMatrix[11] - this.clippingMatrix[10];
/* 91 */     var7[3] = this.clippingMatrix[15] - this.clippingMatrix[14];
/* 92 */     func_180547_a(var7);
/* 93 */     float[] var8 = this.frustum[5];
/* 94 */     var8[0] = this.clippingMatrix[3] + this.clippingMatrix[2];
/* 95 */     var8[1] = this.clippingMatrix[7] + this.clippingMatrix[6];
/* 96 */     var8[2] = this.clippingMatrix[11] + this.clippingMatrix[10];
/* 97 */     var8[3] = this.clippingMatrix[15] + this.clippingMatrix[14];
/* 98 */     func_180547_a(var8);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\culling\ClippingHelperImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */