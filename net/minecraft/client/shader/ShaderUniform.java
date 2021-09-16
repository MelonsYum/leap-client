/*     */ package net.minecraft.client.shader;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import javax.vecmath.Matrix4f;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.BufferUtils;
/*     */ 
/*     */ public class ShaderUniform
/*     */ {
/*  13 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private int uniformLocation;
/*     */   private final int uniformCount;
/*     */   private final int uniformType;
/*     */   private final IntBuffer uniformIntBuffer;
/*     */   private final FloatBuffer uniformFloatBuffer;
/*     */   private final String shaderName;
/*     */   private boolean field_148105_h;
/*     */   private final ShaderManager shaderManager;
/*     */   private static final String __OBFID = "CL_00001046";
/*     */   
/*     */   public ShaderUniform(String name, int type, int count, ShaderManager manager) {
/*  26 */     this.shaderName = name;
/*  27 */     this.uniformCount = count;
/*  28 */     this.uniformType = type;
/*  29 */     this.shaderManager = manager;
/*     */     
/*  31 */     if (type <= 3) {
/*     */       
/*  33 */       this.uniformIntBuffer = BufferUtils.createIntBuffer(count);
/*  34 */       this.uniformFloatBuffer = null;
/*     */     }
/*     */     else {
/*     */       
/*  38 */       this.uniformIntBuffer = null;
/*  39 */       this.uniformFloatBuffer = BufferUtils.createFloatBuffer(count);
/*     */     } 
/*     */     
/*  42 */     this.uniformLocation = -1;
/*  43 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   private void markDirty() {
/*  48 */     this.field_148105_h = true;
/*     */     
/*  50 */     if (this.shaderManager != null)
/*     */     {
/*  52 */       this.shaderManager.markDirty();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static int parseType(String p_148085_0_) {
/*  58 */     byte var1 = -1;
/*     */     
/*  60 */     if (p_148085_0_.equals("int")) {
/*     */       
/*  62 */       var1 = 0;
/*     */     }
/*  64 */     else if (p_148085_0_.equals("float")) {
/*     */       
/*  66 */       var1 = 4;
/*     */     }
/*  68 */     else if (p_148085_0_.startsWith("matrix")) {
/*     */       
/*  70 */       if (p_148085_0_.endsWith("2x2")) {
/*     */         
/*  72 */         var1 = 8;
/*     */       }
/*  74 */       else if (p_148085_0_.endsWith("3x3")) {
/*     */         
/*  76 */         var1 = 9;
/*     */       }
/*  78 */       else if (p_148085_0_.endsWith("4x4")) {
/*     */         
/*  80 */         var1 = 10;
/*     */       } 
/*     */     } 
/*     */     
/*  84 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUniformLocation(int p_148084_1_) {
/*  89 */     this.uniformLocation = p_148084_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getShaderName() {
/*  94 */     return this.shaderName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(float p_148090_1_) {
/*  99 */     this.uniformFloatBuffer.position(0);
/* 100 */     this.uniformFloatBuffer.put(0, p_148090_1_);
/* 101 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(float p_148087_1_, float p_148087_2_) {
/* 106 */     this.uniformFloatBuffer.position(0);
/* 107 */     this.uniformFloatBuffer.put(0, p_148087_1_);
/* 108 */     this.uniformFloatBuffer.put(1, p_148087_2_);
/* 109 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(float p_148095_1_, float p_148095_2_, float p_148095_3_) {
/* 114 */     this.uniformFloatBuffer.position(0);
/* 115 */     this.uniformFloatBuffer.put(0, p_148095_1_);
/* 116 */     this.uniformFloatBuffer.put(1, p_148095_2_);
/* 117 */     this.uniformFloatBuffer.put(2, p_148095_3_);
/* 118 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(float p_148081_1_, float p_148081_2_, float p_148081_3_, float p_148081_4_) {
/* 123 */     this.uniformFloatBuffer.position(0);
/* 124 */     this.uniformFloatBuffer.put(p_148081_1_);
/* 125 */     this.uniformFloatBuffer.put(p_148081_2_);
/* 126 */     this.uniformFloatBuffer.put(p_148081_3_);
/* 127 */     this.uniformFloatBuffer.put(p_148081_4_);
/* 128 */     this.uniformFloatBuffer.flip();
/* 129 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_148092_b(float p_148092_1_, float p_148092_2_, float p_148092_3_, float p_148092_4_) {
/* 134 */     this.uniformFloatBuffer.position(0);
/*     */     
/* 136 */     if (this.uniformType >= 4)
/*     */     {
/* 138 */       this.uniformFloatBuffer.put(0, p_148092_1_);
/*     */     }
/*     */     
/* 141 */     if (this.uniformType >= 5)
/*     */     {
/* 143 */       this.uniformFloatBuffer.put(1, p_148092_2_);
/*     */     }
/*     */     
/* 146 */     if (this.uniformType >= 6)
/*     */     {
/* 148 */       this.uniformFloatBuffer.put(2, p_148092_3_);
/*     */     }
/*     */     
/* 151 */     if (this.uniformType >= 7)
/*     */     {
/* 153 */       this.uniformFloatBuffer.put(3, p_148092_4_);
/*     */     }
/*     */     
/* 156 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(int p_148083_1_, int p_148083_2_, int p_148083_3_, int p_148083_4_) {
/* 161 */     this.uniformIntBuffer.position(0);
/*     */     
/* 163 */     if (this.uniformType >= 0)
/*     */     {
/* 165 */       this.uniformIntBuffer.put(0, p_148083_1_);
/*     */     }
/*     */     
/* 168 */     if (this.uniformType >= 1)
/*     */     {
/* 170 */       this.uniformIntBuffer.put(1, p_148083_2_);
/*     */     }
/*     */     
/* 173 */     if (this.uniformType >= 2)
/*     */     {
/* 175 */       this.uniformIntBuffer.put(2, p_148083_3_);
/*     */     }
/*     */     
/* 178 */     if (this.uniformType >= 3)
/*     */     {
/* 180 */       this.uniformIntBuffer.put(3, p_148083_4_);
/*     */     }
/*     */     
/* 183 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(float[] p_148097_1_) {
/* 188 */     if (p_148097_1_.length < this.uniformCount) {
/*     */       
/* 190 */       logger.warn("Uniform.set called with a too-small value array (expected " + this.uniformCount + ", got " + p_148097_1_.length + "). Ignoring.");
/*     */     }
/*     */     else {
/*     */       
/* 194 */       this.uniformFloatBuffer.position(0);
/* 195 */       this.uniformFloatBuffer.put(p_148097_1_);
/* 196 */       this.uniformFloatBuffer.position(0);
/* 197 */       markDirty();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(float p_148094_1_, float p_148094_2_, float p_148094_3_, float p_148094_4_, float p_148094_5_, float p_148094_6_, float p_148094_7_, float p_148094_8_, float p_148094_9_, float p_148094_10_, float p_148094_11_, float p_148094_12_, float p_148094_13_, float p_148094_14_, float p_148094_15_, float p_148094_16_) {
/* 203 */     this.uniformFloatBuffer.position(0);
/* 204 */     this.uniformFloatBuffer.put(0, p_148094_1_);
/* 205 */     this.uniformFloatBuffer.put(1, p_148094_2_);
/* 206 */     this.uniformFloatBuffer.put(2, p_148094_3_);
/* 207 */     this.uniformFloatBuffer.put(3, p_148094_4_);
/* 208 */     this.uniformFloatBuffer.put(4, p_148094_5_);
/* 209 */     this.uniformFloatBuffer.put(5, p_148094_6_);
/* 210 */     this.uniformFloatBuffer.put(6, p_148094_7_);
/* 211 */     this.uniformFloatBuffer.put(7, p_148094_8_);
/* 212 */     this.uniformFloatBuffer.put(8, p_148094_9_);
/* 213 */     this.uniformFloatBuffer.put(9, p_148094_10_);
/* 214 */     this.uniformFloatBuffer.put(10, p_148094_11_);
/* 215 */     this.uniformFloatBuffer.put(11, p_148094_12_);
/* 216 */     this.uniformFloatBuffer.put(12, p_148094_13_);
/* 217 */     this.uniformFloatBuffer.put(13, p_148094_14_);
/* 218 */     this.uniformFloatBuffer.put(14, p_148094_15_);
/* 219 */     this.uniformFloatBuffer.put(15, p_148094_16_);
/* 220 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(Matrix4f p_148088_1_) {
/* 225 */     set(p_148088_1_.m00, p_148088_1_.m01, p_148088_1_.m02, p_148088_1_.m03, p_148088_1_.m10, p_148088_1_.m11, p_148088_1_.m12, p_148088_1_.m13, p_148088_1_.m20, p_148088_1_.m21, p_148088_1_.m22, p_148088_1_.m23, p_148088_1_.m30, p_148088_1_.m31, p_148088_1_.m32, p_148088_1_.m33);
/*     */   }
/*     */ 
/*     */   
/*     */   public void upload() {
/* 230 */     if (!this.field_148105_h);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     this.field_148105_h = false;
/*     */     
/* 237 */     if (this.uniformType <= 3) {
/*     */       
/* 239 */       uploadInt();
/*     */     }
/* 241 */     else if (this.uniformType <= 7) {
/*     */       
/* 243 */       uploadFloat();
/*     */     }
/*     */     else {
/*     */       
/* 247 */       if (this.uniformType > 10) {
/*     */         
/* 249 */         logger.warn("Uniform.upload called, but type value (" + this.uniformType + ") is not " + "a valid type. Ignoring.");
/*     */         
/*     */         return;
/*     */       } 
/* 253 */       uploadFloatMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void uploadInt() {
/* 259 */     switch (this.uniformType) {
/*     */       
/*     */       case 0:
/* 262 */         OpenGlHelper.glUniform1(this.uniformLocation, this.uniformIntBuffer);
/*     */         return;
/*     */       
/*     */       case 1:
/* 266 */         OpenGlHelper.glUniform2(this.uniformLocation, this.uniformIntBuffer);
/*     */         return;
/*     */       
/*     */       case 2:
/* 270 */         OpenGlHelper.glUniform3(this.uniformLocation, this.uniformIntBuffer);
/*     */         return;
/*     */       
/*     */       case 3:
/* 274 */         OpenGlHelper.glUniform4(this.uniformLocation, this.uniformIntBuffer);
/*     */         return;
/*     */     } 
/*     */     
/* 278 */     logger.warn("Uniform.upload called, but count value (" + this.uniformCount + ") is " + " not in the range of 1 to 4. Ignoring.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void uploadFloat() {
/* 284 */     switch (this.uniformType) {
/*     */       
/*     */       case 4:
/* 287 */         OpenGlHelper.glUniform1(this.uniformLocation, this.uniformFloatBuffer);
/*     */         return;
/*     */       
/*     */       case 5:
/* 291 */         OpenGlHelper.glUniform2(this.uniformLocation, this.uniformFloatBuffer);
/*     */         return;
/*     */       
/*     */       case 6:
/* 295 */         OpenGlHelper.glUniform3(this.uniformLocation, this.uniformFloatBuffer);
/*     */         return;
/*     */       
/*     */       case 7:
/* 299 */         OpenGlHelper.glUniform4(this.uniformLocation, this.uniformFloatBuffer);
/*     */         return;
/*     */     } 
/*     */     
/* 303 */     logger.warn("Uniform.upload called, but count value (" + this.uniformCount + ") is " + "not in the range of 1 to 4. Ignoring.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void uploadFloatMatrix() {
/* 309 */     switch (this.uniformType) {
/*     */       
/*     */       case 8:
/* 312 */         OpenGlHelper.glUniformMatrix2(this.uniformLocation, true, this.uniformFloatBuffer);
/*     */         break;
/*     */       
/*     */       case 9:
/* 316 */         OpenGlHelper.glUniformMatrix3(this.uniformLocation, true, this.uniformFloatBuffer);
/*     */         break;
/*     */       
/*     */       case 10:
/* 320 */         OpenGlHelper.glUniformMatrix4(this.uniformLocation, true, this.uniformFloatBuffer);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\shader\ShaderUniform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */