/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import optifine.Config;
/*     */ import org.lwjgl.opengl.ARBFramebufferObject;
/*     */ import org.lwjgl.opengl.ARBMultitexture;
/*     */ import org.lwjgl.opengl.ARBShaderObjects;
/*     */ import org.lwjgl.opengl.ARBVertexBufferObject;
/*     */ import org.lwjgl.opengl.ARBVertexShader;
/*     */ import org.lwjgl.opengl.ContextCapabilities;
/*     */ import org.lwjgl.opengl.EXTBlendFuncSeparate;
/*     */ import org.lwjgl.opengl.EXTFramebufferObject;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL13;
/*     */ import org.lwjgl.opengl.GL14;
/*     */ import org.lwjgl.opengl.GL15;
/*     */ import org.lwjgl.opengl.GL20;
/*     */ import org.lwjgl.opengl.GL30;
/*     */ import org.lwjgl.opengl.GLContext;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OpenGlHelper
/*     */ {
/*     */   public static boolean field_153197_d;
/*     */   public static int field_153198_e;
/*     */   public static int field_153199_f;
/*     */   public static int field_153200_g;
/*     */   public static int field_153201_h;
/*     */   public static int field_153202_i;
/*     */   public static int field_153203_j;
/*     */   public static int field_153204_k;
/*     */   public static int field_153205_l;
/*     */   public static int field_153206_m;
/*     */   private static int field_153212_w;
/*     */   public static boolean framebufferSupported;
/*     */   private static boolean field_153213_x;
/*     */   private static boolean field_153214_y;
/*     */   public static int GL_LINK_STATUS;
/*     */   public static int GL_COMPILE_STATUS;
/*     */   public static int GL_VERTEX_SHADER;
/*     */   public static int GL_FRAGMENT_SHADER;
/*     */   private static boolean field_153215_z;
/*     */   public static int defaultTexUnit;
/*     */   public static int lightmapTexUnit;
/*     */   public static int field_176096_r;
/*     */   private static boolean field_176088_V;
/*     */   public static int field_176095_s;
/*     */   public static int field_176094_t;
/*     */   public static int field_176093_u;
/*     */   public static int field_176092_v;
/*     */   public static int field_176091_w;
/*     */   public static int field_176099_x;
/*     */   public static int field_176098_y;
/*     */   public static int field_176097_z;
/*     */   public static int field_176080_A;
/*     */   public static int field_176081_B;
/*     */   public static int field_176082_C;
/*     */   public static int field_176076_D;
/*     */   public static int field_176077_E;
/*     */   public static int field_176078_F;
/*     */   public static int field_176079_G;
/*     */   public static int field_176084_H;
/*     */   public static int field_176085_I;
/*     */   public static int field_176086_J;
/*     */   public static int field_176087_K;
/*     */   private static boolean openGL14;
/*     */   public static boolean field_153211_u;
/*     */   public static boolean openGL21;
/*     */   public static boolean shadersSupported;
/*  83 */   private static String field_153196_B = "";
/*     */   public static boolean field_176083_O;
/*     */   private static boolean field_176090_Y;
/*     */   public static int field_176089_P;
/*     */   public static int anisotropicFilteringMax;
/*     */   private static final String __OBFID = "CL_00001179";
/*  89 */   public static float lastBrightnessX = 0.0F;
/*  90 */   public static float lastBrightnessY = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void initializeTextures() {
/*  97 */     Config.initDisplay();
/*  98 */     ContextCapabilities var0 = GLContext.getCapabilities();
/*  99 */     field_153215_z = (var0.GL_ARB_multitexture && !var0.OpenGL13);
/* 100 */     field_176088_V = (var0.GL_ARB_texture_env_combine && !var0.OpenGL13);
/*     */     
/* 102 */     if (field_153215_z) {
/*     */       
/* 104 */       field_153196_B = String.valueOf(field_153196_B) + "Using ARB_multitexture.\n";
/* 105 */       defaultTexUnit = 33984;
/* 106 */       lightmapTexUnit = 33985;
/* 107 */       field_176096_r = 33986;
/*     */     }
/*     */     else {
/*     */       
/* 111 */       field_153196_B = String.valueOf(field_153196_B) + "Using GL 1.3 multitexturing.\n";
/* 112 */       defaultTexUnit = 33984;
/* 113 */       lightmapTexUnit = 33985;
/* 114 */       field_176096_r = 33986;
/*     */     } 
/*     */     
/* 117 */     if (field_176088_V) {
/*     */       
/* 119 */       field_153196_B = String.valueOf(field_153196_B) + "Using ARB_texture_env_combine.\n";
/* 120 */       field_176095_s = 34160;
/* 121 */       field_176094_t = 34165;
/* 122 */       field_176093_u = 34167;
/* 123 */       field_176092_v = 34166;
/* 124 */       field_176091_w = 34168;
/* 125 */       field_176099_x = 34161;
/* 126 */       field_176098_y = 34176;
/* 127 */       field_176097_z = 34177;
/* 128 */       field_176080_A = 34178;
/* 129 */       field_176081_B = 34192;
/* 130 */       field_176082_C = 34193;
/* 131 */       field_176076_D = 34194;
/* 132 */       field_176077_E = 34162;
/* 133 */       field_176078_F = 34184;
/* 134 */       field_176079_G = 34185;
/* 135 */       field_176084_H = 34186;
/* 136 */       field_176085_I = 34200;
/* 137 */       field_176086_J = 34201;
/* 138 */       field_176087_K = 34202;
/*     */     }
/*     */     else {
/*     */       
/* 142 */       field_153196_B = String.valueOf(field_153196_B) + "Using GL 1.3 texture combiners.\n";
/* 143 */       field_176095_s = 34160;
/* 144 */       field_176094_t = 34165;
/* 145 */       field_176093_u = 34167;
/* 146 */       field_176092_v = 34166;
/* 147 */       field_176091_w = 34168;
/* 148 */       field_176099_x = 34161;
/* 149 */       field_176098_y = 34176;
/* 150 */       field_176097_z = 34177;
/* 151 */       field_176080_A = 34178;
/* 152 */       field_176081_B = 34192;
/* 153 */       field_176082_C = 34193;
/* 154 */       field_176076_D = 34194;
/* 155 */       field_176077_E = 34162;
/* 156 */       field_176078_F = 34184;
/* 157 */       field_176079_G = 34185;
/* 158 */       field_176084_H = 34186;
/* 159 */       field_176085_I = 34200;
/* 160 */       field_176086_J = 34201;
/* 161 */       field_176087_K = 34202;
/*     */     } 
/*     */     
/* 164 */     field_153211_u = (var0.GL_EXT_blend_func_separate && !var0.OpenGL14);
/* 165 */     openGL14 = !(!var0.OpenGL14 && !var0.GL_EXT_blend_func_separate);
/* 166 */     framebufferSupported = (openGL14 && (var0.GL_ARB_framebuffer_object || var0.GL_EXT_framebuffer_object || var0.OpenGL30));
/*     */     
/* 168 */     if (framebufferSupported) {
/*     */       
/* 170 */       field_153196_B = String.valueOf(field_153196_B) + "Using framebuffer objects because ";
/*     */       
/* 172 */       if (var0.OpenGL30)
/*     */       {
/* 174 */         field_153196_B = String.valueOf(field_153196_B) + "OpenGL 3.0 is supported and separate blending is supported.\n";
/* 175 */         field_153212_w = 0;
/* 176 */         field_153198_e = 36160;
/* 177 */         field_153199_f = 36161;
/* 178 */         field_153200_g = 36064;
/* 179 */         field_153201_h = 36096;
/* 180 */         field_153202_i = 36053;
/* 181 */         field_153203_j = 36054;
/* 182 */         field_153204_k = 36055;
/* 183 */         field_153205_l = 36059;
/* 184 */         field_153206_m = 36060;
/*     */       }
/* 186 */       else if (var0.GL_ARB_framebuffer_object)
/*     */       {
/* 188 */         field_153196_B = String.valueOf(field_153196_B) + "ARB_framebuffer_object is supported and separate blending is supported.\n";
/* 189 */         field_153212_w = 1;
/* 190 */         field_153198_e = 36160;
/* 191 */         field_153199_f = 36161;
/* 192 */         field_153200_g = 36064;
/* 193 */         field_153201_h = 36096;
/* 194 */         field_153202_i = 36053;
/* 195 */         field_153204_k = 36055;
/* 196 */         field_153203_j = 36054;
/* 197 */         field_153205_l = 36059;
/* 198 */         field_153206_m = 36060;
/*     */       }
/* 200 */       else if (var0.GL_EXT_framebuffer_object)
/*     */       {
/* 202 */         field_153196_B = String.valueOf(field_153196_B) + "EXT_framebuffer_object is supported.\n";
/* 203 */         field_153212_w = 2;
/* 204 */         field_153198_e = 36160;
/* 205 */         field_153199_f = 36161;
/* 206 */         field_153200_g = 36064;
/* 207 */         field_153201_h = 36096;
/* 208 */         field_153202_i = 36053;
/* 209 */         field_153204_k = 36055;
/* 210 */         field_153203_j = 36054;
/* 211 */         field_153205_l = 36059;
/* 212 */         field_153206_m = 36060;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 217 */       field_153196_B = String.valueOf(field_153196_B) + "Not using framebuffer objects because ";
/* 218 */       field_153196_B = String.valueOf(field_153196_B) + "OpenGL 1.4 is " + (var0.OpenGL14 ? "" : "not ") + "supported, ";
/* 219 */       field_153196_B = String.valueOf(field_153196_B) + "EXT_blend_func_separate is " + (var0.GL_EXT_blend_func_separate ? "" : "not ") + "supported, ";
/* 220 */       field_153196_B = String.valueOf(field_153196_B) + "OpenGL 3.0 is " + (var0.OpenGL30 ? "" : "not ") + "supported, ";
/* 221 */       field_153196_B = String.valueOf(field_153196_B) + "ARB_framebuffer_object is " + (var0.GL_ARB_framebuffer_object ? "" : "not ") + "supported, and ";
/* 222 */       field_153196_B = String.valueOf(field_153196_B) + "EXT_framebuffer_object is " + (var0.GL_EXT_framebuffer_object ? "" : "not ") + "supported.\n";
/*     */     } 
/*     */     
/* 225 */     openGL21 = var0.OpenGL21;
/* 226 */     field_153213_x = !(!openGL21 && (!var0.GL_ARB_vertex_shader || !var0.GL_ARB_fragment_shader || !var0.GL_ARB_shader_objects));
/* 227 */     field_153196_B = String.valueOf(field_153196_B) + "Shaders are " + (field_153213_x ? "" : "not ") + "available because ";
/*     */     
/* 229 */     if (field_153213_x) {
/*     */       
/* 231 */       if (var0.OpenGL21)
/*     */       {
/* 233 */         field_153196_B = String.valueOf(field_153196_B) + "OpenGL 2.1 is supported.\n";
/* 234 */         field_153214_y = false;
/* 235 */         GL_LINK_STATUS = 35714;
/* 236 */         GL_COMPILE_STATUS = 35713;
/* 237 */         GL_VERTEX_SHADER = 35633;
/* 238 */         GL_FRAGMENT_SHADER = 35632;
/*     */       }
/*     */       else
/*     */       {
/* 242 */         field_153196_B = String.valueOf(field_153196_B) + "ARB_shader_objects, ARB_vertex_shader, and ARB_fragment_shader are supported.\n";
/* 243 */         field_153214_y = true;
/* 244 */         GL_LINK_STATUS = 35714;
/* 245 */         GL_COMPILE_STATUS = 35713;
/* 246 */         GL_VERTEX_SHADER = 35633;
/* 247 */         GL_FRAGMENT_SHADER = 35632;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 252 */       field_153196_B = String.valueOf(field_153196_B) + "OpenGL 2.1 is " + (var0.OpenGL21 ? "" : "not ") + "supported, ";
/* 253 */       field_153196_B = String.valueOf(field_153196_B) + "ARB_shader_objects is " + (var0.GL_ARB_shader_objects ? "" : "not ") + "supported, ";
/* 254 */       field_153196_B = String.valueOf(field_153196_B) + "ARB_vertex_shader is " + (var0.GL_ARB_vertex_shader ? "" : "not ") + "supported, and ";
/* 255 */       field_153196_B = String.valueOf(field_153196_B) + "ARB_fragment_shader is " + (var0.GL_ARB_fragment_shader ? "" : "not ") + "supported.\n";
/*     */     } 
/*     */     
/* 258 */     shadersSupported = (framebufferSupported && field_153213_x);
/* 259 */     field_153197_d = GL11.glGetString(7936).toLowerCase().contains("nvidia");
/* 260 */     field_176090_Y = (!var0.OpenGL15 && var0.GL_ARB_vertex_buffer_object);
/* 261 */     field_176083_O = !(!var0.OpenGL15 && !field_176090_Y);
/* 262 */     field_153196_B = String.valueOf(field_153196_B) + "VBOs are " + (field_176083_O ? "" : "not ") + "available because ";
/*     */     
/* 264 */     if (field_176083_O)
/*     */     {
/* 266 */       if (field_176090_Y) {
/*     */         
/* 268 */         field_153196_B = String.valueOf(field_153196_B) + "ARB_vertex_buffer_object is supported.\n";
/* 269 */         anisotropicFilteringMax = 35044;
/* 270 */         field_176089_P = 34962;
/*     */       }
/*     */       else {
/*     */         
/* 274 */         field_153196_B = String.valueOf(field_153196_B) + "OpenGL 1.5 is supported.\n";
/* 275 */         anisotropicFilteringMax = 35044;
/* 276 */         field_176089_P = 34962;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areShadersSupported() {
/* 283 */     return shadersSupported;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_153172_c() {
/* 288 */     return field_153196_B;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int glGetProgrami(int p_153175_0_, int p_153175_1_) {
/* 293 */     return field_153214_y ? ARBShaderObjects.glGetObjectParameteriARB(p_153175_0_, p_153175_1_) : GL20.glGetProgrami(p_153175_0_, p_153175_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glAttachShader(int p_153178_0_, int p_153178_1_) {
/* 298 */     if (field_153214_y) {
/*     */       
/* 300 */       ARBShaderObjects.glAttachObjectARB(p_153178_0_, p_153178_1_);
/*     */     }
/*     */     else {
/*     */       
/* 304 */       GL20.glAttachShader(p_153178_0_, p_153178_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glDeleteShader(int p_153180_0_) {
/* 310 */     if (field_153214_y) {
/*     */       
/* 312 */       ARBShaderObjects.glDeleteObjectARB(p_153180_0_);
/*     */     }
/*     */     else {
/*     */       
/* 316 */       GL20.glDeleteShader(p_153180_0_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int glCreateShader(int p_153195_0_) {
/* 325 */     return field_153214_y ? ARBShaderObjects.glCreateShaderObjectARB(p_153195_0_) : GL20.glCreateShader(p_153195_0_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glShaderSource(int p_153169_0_, ByteBuffer p_153169_1_) {
/* 330 */     if (field_153214_y) {
/*     */       
/* 332 */       ARBShaderObjects.glShaderSourceARB(p_153169_0_, p_153169_1_);
/*     */     }
/*     */     else {
/*     */       
/* 336 */       GL20.glShaderSource(p_153169_0_, p_153169_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glCompileShader(int p_153170_0_) {
/* 342 */     if (field_153214_y) {
/*     */       
/* 344 */       ARBShaderObjects.glCompileShaderARB(p_153170_0_);
/*     */     }
/*     */     else {
/*     */       
/* 348 */       GL20.glCompileShader(p_153170_0_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int glGetShaderi(int p_153157_0_, int p_153157_1_) {
/* 354 */     return field_153214_y ? ARBShaderObjects.glGetObjectParameteriARB(p_153157_0_, p_153157_1_) : GL20.glGetShaderi(p_153157_0_, p_153157_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String glGetShaderInfoLog(int p_153158_0_, int p_153158_1_) {
/* 359 */     return field_153214_y ? ARBShaderObjects.glGetInfoLogARB(p_153158_0_, p_153158_1_) : GL20.glGetShaderInfoLog(p_153158_0_, p_153158_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String glGetProgramInfoLog(int p_153166_0_, int p_153166_1_) {
/* 364 */     return field_153214_y ? ARBShaderObjects.glGetInfoLogARB(p_153166_0_, p_153166_1_) : GL20.glGetProgramInfoLog(p_153166_0_, p_153166_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUseProgram(int p_153161_0_) {
/* 369 */     if (field_153214_y) {
/*     */       
/* 371 */       ARBShaderObjects.glUseProgramObjectARB(p_153161_0_);
/*     */     }
/*     */     else {
/*     */       
/* 375 */       GL20.glUseProgram(p_153161_0_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int glCreateProgram() {
/* 381 */     return field_153214_y ? ARBShaderObjects.glCreateProgramObjectARB() : GL20.glCreateProgram();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glDeleteProgram(int p_153187_0_) {
/* 386 */     if (field_153214_y) {
/*     */       
/* 388 */       ARBShaderObjects.glDeleteObjectARB(p_153187_0_);
/*     */     }
/*     */     else {
/*     */       
/* 392 */       GL20.glDeleteProgram(p_153187_0_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glLinkProgram(int p_153179_0_) {
/* 398 */     if (field_153214_y) {
/*     */       
/* 400 */       ARBShaderObjects.glLinkProgramARB(p_153179_0_);
/*     */     }
/*     */     else {
/*     */       
/* 404 */       GL20.glLinkProgram(p_153179_0_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int glGetUniformLocation(int p_153194_0_, CharSequence p_153194_1_) {
/* 410 */     return field_153214_y ? ARBShaderObjects.glGetUniformLocationARB(p_153194_0_, p_153194_1_) : GL20.glGetUniformLocation(p_153194_0_, p_153194_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniform1(int p_153181_0_, IntBuffer p_153181_1_) {
/* 415 */     if (field_153214_y) {
/*     */       
/* 417 */       ARBShaderObjects.glUniform1ARB(p_153181_0_, p_153181_1_);
/*     */     }
/*     */     else {
/*     */       
/* 421 */       GL20.glUniform1(p_153181_0_, p_153181_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniform1i(int p_153163_0_, int p_153163_1_) {
/* 427 */     if (field_153214_y) {
/*     */       
/* 429 */       ARBShaderObjects.glUniform1iARB(p_153163_0_, p_153163_1_);
/*     */     }
/*     */     else {
/*     */       
/* 433 */       GL20.glUniform1i(p_153163_0_, p_153163_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniform1(int p_153168_0_, FloatBuffer p_153168_1_) {
/* 439 */     if (field_153214_y) {
/*     */       
/* 441 */       ARBShaderObjects.glUniform1ARB(p_153168_0_, p_153168_1_);
/*     */     }
/*     */     else {
/*     */       
/* 445 */       GL20.glUniform1(p_153168_0_, p_153168_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniform2(int p_153182_0_, IntBuffer p_153182_1_) {
/* 451 */     if (field_153214_y) {
/*     */       
/* 453 */       ARBShaderObjects.glUniform2ARB(p_153182_0_, p_153182_1_);
/*     */     }
/*     */     else {
/*     */       
/* 457 */       GL20.glUniform2(p_153182_0_, p_153182_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniform2(int p_153177_0_, FloatBuffer p_153177_1_) {
/* 463 */     if (field_153214_y) {
/*     */       
/* 465 */       ARBShaderObjects.glUniform2ARB(p_153177_0_, p_153177_1_);
/*     */     }
/*     */     else {
/*     */       
/* 469 */       GL20.glUniform2(p_153177_0_, p_153177_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniform3(int p_153192_0_, IntBuffer p_153192_1_) {
/* 475 */     if (field_153214_y) {
/*     */       
/* 477 */       ARBShaderObjects.glUniform3ARB(p_153192_0_, p_153192_1_);
/*     */     }
/*     */     else {
/*     */       
/* 481 */       GL20.glUniform3(p_153192_0_, p_153192_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniform3(int p_153191_0_, FloatBuffer p_153191_1_) {
/* 487 */     if (field_153214_y) {
/*     */       
/* 489 */       ARBShaderObjects.glUniform3ARB(p_153191_0_, p_153191_1_);
/*     */     }
/*     */     else {
/*     */       
/* 493 */       GL20.glUniform3(p_153191_0_, p_153191_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniform4(int p_153162_0_, IntBuffer p_153162_1_) {
/* 499 */     if (field_153214_y) {
/*     */       
/* 501 */       ARBShaderObjects.glUniform4ARB(p_153162_0_, p_153162_1_);
/*     */     }
/*     */     else {
/*     */       
/* 505 */       GL20.glUniform4(p_153162_0_, p_153162_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniform4(int p_153159_0_, FloatBuffer p_153159_1_) {
/* 511 */     if (field_153214_y) {
/*     */       
/* 513 */       ARBShaderObjects.glUniform4ARB(p_153159_0_, p_153159_1_);
/*     */     }
/*     */     else {
/*     */       
/* 517 */       GL20.glUniform4(p_153159_0_, p_153159_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniformMatrix2(int p_153173_0_, boolean p_153173_1_, FloatBuffer p_153173_2_) {
/* 523 */     if (field_153214_y) {
/*     */       
/* 525 */       ARBShaderObjects.glUniformMatrix2ARB(p_153173_0_, p_153173_1_, p_153173_2_);
/*     */     }
/*     */     else {
/*     */       
/* 529 */       GL20.glUniformMatrix2(p_153173_0_, p_153173_1_, p_153173_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniformMatrix3(int p_153189_0_, boolean p_153189_1_, FloatBuffer p_153189_2_) {
/* 535 */     if (field_153214_y) {
/*     */       
/* 537 */       ARBShaderObjects.glUniformMatrix3ARB(p_153189_0_, p_153189_1_, p_153189_2_);
/*     */     }
/*     */     else {
/*     */       
/* 541 */       GL20.glUniformMatrix3(p_153189_0_, p_153189_1_, p_153189_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glUniformMatrix4(int p_153160_0_, boolean p_153160_1_, FloatBuffer p_153160_2_) {
/* 547 */     if (field_153214_y) {
/*     */       
/* 549 */       ARBShaderObjects.glUniformMatrix4ARB(p_153160_0_, p_153160_1_, p_153160_2_);
/*     */     }
/*     */     else {
/*     */       
/* 553 */       GL20.glUniformMatrix4(p_153160_0_, p_153160_1_, p_153160_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int glGetAttribLocation(int p_153164_0_, CharSequence p_153164_1_) {
/* 559 */     return field_153214_y ? ARBVertexShader.glGetAttribLocationARB(p_153164_0_, p_153164_1_) : GL20.glGetAttribLocation(p_153164_0_, p_153164_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_176073_e() {
/* 564 */     return field_176090_Y ? ARBVertexBufferObject.glGenBuffersARB() : GL15.glGenBuffers();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_176072_g(int p_176072_0_, int p_176072_1_) {
/* 569 */     if (field_176090_Y) {
/*     */       
/* 571 */       ARBVertexBufferObject.glBindBufferARB(p_176072_0_, p_176072_1_);
/*     */     }
/*     */     else {
/*     */       
/* 575 */       GL15.glBindBuffer(p_176072_0_, p_176072_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_176071_a(int p_176071_0_, ByteBuffer p_176071_1_, int p_176071_2_) {
/* 581 */     if (field_176090_Y) {
/*     */       
/* 583 */       ARBVertexBufferObject.glBufferDataARB(p_176071_0_, p_176071_1_, p_176071_2_);
/*     */     }
/*     */     else {
/*     */       
/* 587 */       GL15.glBufferData(p_176071_0_, p_176071_1_, p_176071_2_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_176074_g(int p_176074_0_) {
/* 593 */     if (field_176090_Y) {
/*     */       
/* 595 */       ARBVertexBufferObject.glDeleteBuffersARB(p_176074_0_);
/*     */     }
/*     */     else {
/*     */       
/* 599 */       GL15.glDeleteBuffers(p_176074_0_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_176075_f() {
/* 605 */     return Config.isMultiTexture() ? false : ((field_176083_O && (Minecraft.getMinecraft()).gameSettings.field_178881_t));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_153171_g(int p_153171_0_, int p_153171_1_) {
/* 610 */     if (framebufferSupported)
/*     */     {
/* 612 */       switch (field_153212_w) {
/*     */         
/*     */         case 0:
/* 615 */           GL30.glBindFramebuffer(p_153171_0_, p_153171_1_);
/*     */           break;
/*     */         
/*     */         case 1:
/* 619 */           ARBFramebufferObject.glBindFramebuffer(p_153171_0_, p_153171_1_);
/*     */           break;
/*     */         
/*     */         case 2:
/* 623 */           EXTFramebufferObject.glBindFramebufferEXT(p_153171_0_, p_153171_1_);
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void func_153176_h(int p_153176_0_, int p_153176_1_) {
/* 630 */     if (framebufferSupported)
/*     */     {
/* 632 */       switch (field_153212_w) {
/*     */         
/*     */         case 0:
/* 635 */           GL30.glBindRenderbuffer(p_153176_0_, p_153176_1_);
/*     */           break;
/*     */         
/*     */         case 1:
/* 639 */           ARBFramebufferObject.glBindRenderbuffer(p_153176_0_, p_153176_1_);
/*     */           break;
/*     */         
/*     */         case 2:
/* 643 */           EXTFramebufferObject.glBindRenderbufferEXT(p_153176_0_, p_153176_1_);
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void func_153184_g(int p_153184_0_) {
/* 650 */     if (framebufferSupported)
/*     */     {
/* 652 */       switch (field_153212_w) {
/*     */         
/*     */         case 0:
/* 655 */           GL30.glDeleteRenderbuffers(p_153184_0_);
/*     */           break;
/*     */         
/*     */         case 1:
/* 659 */           ARBFramebufferObject.glDeleteRenderbuffers(p_153184_0_);
/*     */           break;
/*     */         
/*     */         case 2:
/* 663 */           EXTFramebufferObject.glDeleteRenderbuffersEXT(p_153184_0_);
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void func_153174_h(int p_153174_0_) {
/* 670 */     if (framebufferSupported)
/*     */     {
/* 672 */       switch (field_153212_w) {
/*     */         
/*     */         case 0:
/* 675 */           GL30.glDeleteFramebuffers(p_153174_0_);
/*     */           break;
/*     */         
/*     */         case 1:
/* 679 */           ARBFramebufferObject.glDeleteFramebuffers(p_153174_0_);
/*     */           break;
/*     */         
/*     */         case 2:
/* 683 */           EXTFramebufferObject.glDeleteFramebuffersEXT(p_153174_0_);
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static int func_153165_e() {
/* 690 */     if (!framebufferSupported)
/*     */     {
/* 692 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 696 */     switch (field_153212_w) {
/*     */       
/*     */       case 0:
/* 699 */         return GL30.glGenFramebuffers();
/*     */       
/*     */       case 1:
/* 702 */         return ARBFramebufferObject.glGenFramebuffers();
/*     */       
/*     */       case 2:
/* 705 */         return EXTFramebufferObject.glGenFramebuffersEXT();
/*     */     } 
/*     */     
/* 708 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int func_153185_f() {
/* 715 */     if (!framebufferSupported)
/*     */     {
/* 717 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 721 */     switch (field_153212_w) {
/*     */       
/*     */       case 0:
/* 724 */         return GL30.glGenRenderbuffers();
/*     */       
/*     */       case 1:
/* 727 */         return ARBFramebufferObject.glGenRenderbuffers();
/*     */       
/*     */       case 2:
/* 730 */         return EXTFramebufferObject.glGenRenderbuffersEXT();
/*     */     } 
/*     */     
/* 733 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void func_153186_a(int p_153186_0_, int p_153186_1_, int p_153186_2_, int p_153186_3_) {
/* 740 */     if (framebufferSupported)
/*     */     {
/* 742 */       switch (field_153212_w) {
/*     */         
/*     */         case 0:
/* 745 */           GL30.glRenderbufferStorage(p_153186_0_, p_153186_1_, p_153186_2_, p_153186_3_);
/*     */           break;
/*     */         
/*     */         case 1:
/* 749 */           ARBFramebufferObject.glRenderbufferStorage(p_153186_0_, p_153186_1_, p_153186_2_, p_153186_3_);
/*     */           break;
/*     */         
/*     */         case 2:
/* 753 */           EXTFramebufferObject.glRenderbufferStorageEXT(p_153186_0_, p_153186_1_, p_153186_2_, p_153186_3_);
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void func_153190_b(int p_153190_0_, int p_153190_1_, int p_153190_2_, int p_153190_3_) {
/* 760 */     if (framebufferSupported)
/*     */     {
/* 762 */       switch (field_153212_w) {
/*     */         
/*     */         case 0:
/* 765 */           GL30.glFramebufferRenderbuffer(p_153190_0_, p_153190_1_, p_153190_2_, p_153190_3_);
/*     */           break;
/*     */         
/*     */         case 1:
/* 769 */           ARBFramebufferObject.glFramebufferRenderbuffer(p_153190_0_, p_153190_1_, p_153190_2_, p_153190_3_);
/*     */           break;
/*     */         
/*     */         case 2:
/* 773 */           EXTFramebufferObject.glFramebufferRenderbufferEXT(p_153190_0_, p_153190_1_, p_153190_2_, p_153190_3_);
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static int func_153167_i(int p_153167_0_) {
/* 780 */     if (!framebufferSupported)
/*     */     {
/* 782 */       return -1;
/*     */     }
/*     */ 
/*     */     
/* 786 */     switch (field_153212_w) {
/*     */       
/*     */       case 0:
/* 789 */         return GL30.glCheckFramebufferStatus(p_153167_0_);
/*     */       
/*     */       case 1:
/* 792 */         return ARBFramebufferObject.glCheckFramebufferStatus(p_153167_0_);
/*     */       
/*     */       case 2:
/* 795 */         return EXTFramebufferObject.glCheckFramebufferStatusEXT(p_153167_0_);
/*     */     } 
/*     */     
/* 798 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void func_153188_a(int p_153188_0_, int p_153188_1_, int p_153188_2_, int p_153188_3_, int p_153188_4_) {
/* 805 */     if (framebufferSupported)
/*     */     {
/* 807 */       switch (field_153212_w) {
/*     */         
/*     */         case 0:
/* 810 */           GL30.glFramebufferTexture2D(p_153188_0_, p_153188_1_, p_153188_2_, p_153188_3_, p_153188_4_);
/*     */           break;
/*     */         
/*     */         case 1:
/* 814 */           ARBFramebufferObject.glFramebufferTexture2D(p_153188_0_, p_153188_1_, p_153188_2_, p_153188_3_, p_153188_4_);
/*     */           break;
/*     */         
/*     */         case 2:
/* 818 */           EXTFramebufferObject.glFramebufferTexture2DEXT(p_153188_0_, p_153188_1_, p_153188_2_, p_153188_3_, p_153188_4_);
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setActiveTexture(int p_77473_0_) {
/* 828 */     if (field_153215_z) {
/*     */       
/* 830 */       ARBMultitexture.glActiveTextureARB(p_77473_0_);
/*     */     }
/*     */     else {
/*     */       
/* 834 */       GL13.glActiveTexture(p_77473_0_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setClientActiveTexture(int p_77472_0_) {
/* 843 */     if (field_153215_z) {
/*     */       
/* 845 */       ARBMultitexture.glClientActiveTextureARB(p_77472_0_);
/*     */     }
/*     */     else {
/*     */       
/* 849 */       GL13.glClientActiveTexture(p_77472_0_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setLightmapTextureCoords(int p_77475_0_, float p_77475_1_, float p_77475_2_) {
/* 858 */     if (field_153215_z) {
/*     */       
/* 860 */       ARBMultitexture.glMultiTexCoord2fARB(p_77475_0_, p_77475_1_, p_77475_2_);
/*     */     }
/*     */     else {
/*     */       
/* 864 */       GL13.glMultiTexCoord2f(p_77475_0_, p_77475_1_, p_77475_2_);
/*     */     } 
/*     */     
/* 867 */     if (p_77475_0_ == lightmapTexUnit) {
/*     */       
/* 869 */       lastBrightnessX = p_77475_1_;
/* 870 */       lastBrightnessY = p_77475_2_;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void glBlendFunc(int p_148821_0_, int p_148821_1_, int p_148821_2_, int p_148821_3_) {
/* 876 */     if (openGL14) {
/*     */       
/* 878 */       if (field_153211_u)
/*     */       {
/* 880 */         EXTBlendFuncSeparate.glBlendFuncSeparateEXT(p_148821_0_, p_148821_1_, p_148821_2_, p_148821_3_);
/*     */       }
/*     */       else
/*     */       {
/* 884 */         GL14.glBlendFuncSeparate(p_148821_0_, p_148821_1_, p_148821_2_, p_148821_3_);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 889 */       GL11.glBlendFunc(p_148821_0_, p_148821_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFramebufferEnabled() {
/* 895 */     return Config.isFastRender() ? false : (Config.isAntialiasing() ? false : ((framebufferSupported && (Minecraft.getMinecraft()).gameSettings.fboEnable)));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\OpenGlHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */