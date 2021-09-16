/*      */ package net.minecraft.client.renderer;
/*      */ 
/*      */ import java.nio.FloatBuffer;
/*      */ import java.nio.IntBuffer;
/*      */ import optifine.Config;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class GlStateManager
/*      */ {
/*   13 */   private static AlphaState alphaState = new AlphaState(null);
/*   14 */   private static BooleanState lightingState = new BooleanState(2896);
/*   15 */   private static BooleanState[] field_179159_c = new BooleanState[8];
/*   16 */   private static ColorMaterialState colorMaterialState = new ColorMaterialState(null);
/*   17 */   private static BlendState blendState = new BlendState(null);
/*   18 */   private static DepthState depthState = new DepthState(null);
/*   19 */   private static FogState fogState = new FogState(null);
/*   20 */   private static CullState cullState = new CullState(null);
/*   21 */   private static PolygonOffsetState polygonOffsetState = new PolygonOffsetState(null);
/*   22 */   private static ColorLogicState colorLogicState = new ColorLogicState(null);
/*   23 */   private static TexGenState texGenState = new TexGenState(null);
/*   24 */   private static ClearState clearState = new ClearState(null);
/*   25 */   private static StencilState stencilState = new StencilState(null);
/*   26 */   private static BooleanState normalizeState = new BooleanState(2977);
/*   27 */   private static int field_179162_o = 0;
/*   28 */   private static TextureState[] field_179174_p = new TextureState[32];
/*   29 */   private static int field_179173_q = 7425;
/*   30 */   private static BooleanState rescaleNormalState = new BooleanState(32826);
/*   31 */   private static ColorMask colorMaskState = new ColorMask(null);
/*   32 */   private static Color colorState = new Color();
/*   33 */   private static Viewport field_179169_u = new Viewport(null);
/*      */   
/*      */   private static final String __OBFID = "CL_00002558";
/*      */   public static boolean clearEnabled = true;
/*      */   
/*      */   public static void pushAttrib() {
/*   39 */     GL11.glPushAttrib(8256);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void popAttrib() {
/*   44 */     GL11.glPopAttrib();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableAlpha() {
/*   49 */     alphaState.field_179208_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableAlpha() {
/*   54 */     alphaState.field_179208_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void alphaFunc(int p_179092_0_, float p_179092_1_) {
/*   59 */     if (p_179092_0_ != alphaState.field_179206_b || p_179092_1_ != alphaState.field_179207_c) {
/*      */       
/*   61 */       alphaState.field_179206_b = p_179092_0_;
/*   62 */       alphaState.field_179207_c = p_179092_1_;
/*   63 */       GL11.glAlphaFunc(p_179092_0_, p_179092_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableLighting() {
/*   69 */     lightingState.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableLighting() {
/*   74 */     lightingState.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableBooleanStateAt(int p_179085_0_) {
/*   79 */     field_179159_c[p_179085_0_].setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableBooleanStateAt(int p_179122_0_) {
/*   84 */     field_179159_c[p_179122_0_].setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableColorMaterial() {
/*   89 */     colorMaterialState.field_179191_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableColorMaterial() {
/*   94 */     colorMaterialState.field_179191_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void colorMaterial(int p_179104_0_, int p_179104_1_) {
/*   99 */     if (p_179104_0_ != colorMaterialState.field_179189_b || p_179104_1_ != colorMaterialState.field_179190_c) {
/*      */       
/*  101 */       colorMaterialState.field_179189_b = p_179104_0_;
/*  102 */       colorMaterialState.field_179190_c = p_179104_1_;
/*  103 */       GL11.glColorMaterial(p_179104_0_, p_179104_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableDepth() {
/*  109 */     depthState.field_179052_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableDepth() {
/*  114 */     depthState.field_179052_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void depthFunc(int p_179143_0_) {
/*  119 */     if (p_179143_0_ != depthState.field_179051_c) {
/*      */       
/*  121 */       depthState.field_179051_c = p_179143_0_;
/*  122 */       GL11.glDepthFunc(p_179143_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void depthMask(boolean p_179132_0_) {
/*  128 */     if (p_179132_0_ != depthState.field_179050_b) {
/*      */       
/*  130 */       depthState.field_179050_b = p_179132_0_;
/*  131 */       GL11.glDepthMask(p_179132_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableBlend() {
/*  137 */     blendState.field_179213_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableBlend() {
/*  142 */     blendState.field_179213_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void blendFunc(int p_179112_0_, int p_179112_1_) {
/*  147 */     if (p_179112_0_ != blendState.field_179211_b || p_179112_1_ != blendState.field_179212_c) {
/*      */       
/*  149 */       blendState.field_179211_b = p_179112_0_;
/*  150 */       blendState.field_179212_c = p_179112_1_;
/*  151 */       GL11.glBlendFunc(p_179112_0_, p_179112_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void tryBlendFuncSeparate(int p_179120_0_, int p_179120_1_, int p_179120_2_, int p_179120_3_) {
/*  157 */     if (p_179120_0_ != blendState.field_179211_b || p_179120_1_ != blendState.field_179212_c || p_179120_2_ != blendState.field_179209_d || p_179120_3_ != blendState.field_179210_e) {
/*      */       
/*  159 */       blendState.field_179211_b = p_179120_0_;
/*  160 */       blendState.field_179212_c = p_179120_1_;
/*  161 */       blendState.field_179209_d = p_179120_2_;
/*  162 */       blendState.field_179210_e = p_179120_3_;
/*  163 */       OpenGlHelper.glBlendFunc(p_179120_0_, p_179120_1_, p_179120_2_, p_179120_3_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableFog() {
/*  169 */     fogState.field_179049_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableFog() {
/*  174 */     fogState.field_179049_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setFog(int p_179093_0_) {
/*  179 */     if (p_179093_0_ != fogState.field_179047_b) {
/*      */       
/*  181 */       fogState.field_179047_b = p_179093_0_;
/*  182 */       GL11.glFogi(2917, p_179093_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setFogDensity(float p_179095_0_) {
/*  188 */     if (p_179095_0_ != fogState.field_179048_c) {
/*      */       
/*  190 */       fogState.field_179048_c = p_179095_0_;
/*  191 */       GL11.glFogf(2914, p_179095_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setFogStart(float p_179102_0_) {
/*  197 */     if (p_179102_0_ != fogState.field_179045_d) {
/*      */       
/*  199 */       fogState.field_179045_d = p_179102_0_;
/*  200 */       GL11.glFogf(2915, p_179102_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setFogEnd(float p_179153_0_) {
/*  206 */     if (p_179153_0_ != fogState.field_179046_e) {
/*      */       
/*  208 */       fogState.field_179046_e = p_179153_0_;
/*  209 */       GL11.glFogf(2916, p_179153_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableCull() {
/*  215 */     cullState.field_179054_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableCull() {
/*  220 */     cullState.field_179054_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void cullFace(int p_179107_0_) {
/*  225 */     if (p_179107_0_ != cullState.field_179053_b) {
/*      */       
/*  227 */       cullState.field_179053_b = p_179107_0_;
/*  228 */       GL11.glCullFace(p_179107_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enablePolygonOffset() {
/*  234 */     polygonOffsetState.field_179044_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disablePolygonOffset() {
/*  239 */     polygonOffsetState.field_179044_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void doPolygonOffset(float p_179136_0_, float p_179136_1_) {
/*  244 */     if (p_179136_0_ != polygonOffsetState.field_179043_c || p_179136_1_ != polygonOffsetState.field_179041_d) {
/*      */       
/*  246 */       polygonOffsetState.field_179043_c = p_179136_0_;
/*  247 */       polygonOffsetState.field_179041_d = p_179136_1_;
/*  248 */       GL11.glPolygonOffset(p_179136_0_, p_179136_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableColorLogic() {
/*  254 */     colorLogicState.field_179197_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableColorLogic() {
/*  259 */     colorLogicState.field_179197_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void colorLogicOp(int p_179116_0_) {
/*  264 */     if (p_179116_0_ != colorLogicState.field_179196_b) {
/*      */       
/*  266 */       colorLogicState.field_179196_b = p_179116_0_;
/*  267 */       GL11.glLogicOp(p_179116_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableTexGenCoord(TexGen p_179087_0_) {
/*  273 */     (texGenCoord(p_179087_0_)).field_179067_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableTexGenCoord(TexGen p_179100_0_) {
/*  278 */     (texGenCoord(p_179100_0_)).field_179067_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void texGen(TexGen p_179149_0_, int p_179149_1_) {
/*  283 */     TexGenCoord var2 = texGenCoord(p_179149_0_);
/*      */     
/*  285 */     if (p_179149_1_ != var2.field_179066_c) {
/*      */       
/*  287 */       var2.field_179066_c = p_179149_1_;
/*  288 */       GL11.glTexGeni(var2.field_179065_b, 9472, p_179149_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void func_179105_a(TexGen p_179105_0_, int p_179105_1_, FloatBuffer p_179105_2_) {
/*  294 */     GL11.glTexGen((texGenCoord(p_179105_0_)).field_179065_b, p_179105_1_, p_179105_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   private static TexGenCoord texGenCoord(TexGen p_179125_0_) {
/*  299 */     switch (SwitchTexGen.field_179175_a[p_179125_0_.ordinal()]) {
/*      */       
/*      */       case 1:
/*  302 */         return texGenState.field_179064_a;
/*      */       
/*      */       case 2:
/*  305 */         return texGenState.field_179062_b;
/*      */       
/*      */       case 3:
/*  308 */         return texGenState.field_179063_c;
/*      */       
/*      */       case 4:
/*  311 */         return texGenState.field_179061_d;
/*      */     } 
/*      */     
/*  314 */     return texGenState.field_179064_a;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setActiveTexture(int p_179138_0_) {
/*  320 */     if (field_179162_o != p_179138_0_ - OpenGlHelper.defaultTexUnit) {
/*      */       
/*  322 */       field_179162_o = p_179138_0_ - OpenGlHelper.defaultTexUnit;
/*  323 */       OpenGlHelper.setActiveTexture(p_179138_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void func_179098_w() {
/*  329 */     (field_179174_p[field_179162_o]).field_179060_a.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void func_179090_x() {
/*  334 */     (field_179174_p[field_179162_o]).field_179060_a.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static int func_179146_y() {
/*  339 */     return GL11.glGenTextures();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void func_179150_h(int p_179150_0_) {
/*  344 */     if (p_179150_0_ != 0) {
/*      */       
/*  346 */       GL11.glDeleteTextures(p_179150_0_);
/*  347 */       TextureState[] var1 = field_179174_p;
/*  348 */       int var2 = var1.length;
/*      */       
/*  350 */       for (int var3 = 0; var3 < var2; var3++) {
/*      */         
/*  352 */         TextureState var4 = var1[var3];
/*      */         
/*  354 */         if (var4.field_179059_b == p_179150_0_)
/*      */         {
/*  356 */           var4.field_179059_b = 0;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void func_179144_i(int p_179144_0_) {
/*  364 */     if (p_179144_0_ != (field_179174_p[field_179162_o]).field_179059_b) {
/*      */       
/*  366 */       (field_179174_p[field_179162_o]).field_179059_b = p_179144_0_;
/*  367 */       GL11.glBindTexture(3553, p_179144_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void bindCurrentTexture() {
/*  373 */     GL11.glBindTexture(3553, (field_179174_p[field_179162_o]).field_179059_b);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableNormalize() {
/*  378 */     normalizeState.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableNormalize() {
/*  383 */     normalizeState.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void shadeModel(int p_179103_0_) {
/*  388 */     if (p_179103_0_ != field_179173_q) {
/*      */       
/*  390 */       field_179173_q = p_179103_0_;
/*  391 */       GL11.glShadeModel(p_179103_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableRescaleNormal() {
/*  397 */     rescaleNormalState.setEnabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableRescaleNormal() {
/*  402 */     rescaleNormalState.setDisabled();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void viewport(int p_179083_0_, int p_179083_1_, int p_179083_2_, int p_179083_3_) {
/*  407 */     if (p_179083_0_ != field_179169_u.field_179058_a || p_179083_1_ != field_179169_u.field_179056_b || p_179083_2_ != field_179169_u.field_179057_c || p_179083_3_ != field_179169_u.field_179055_d) {
/*      */       
/*  409 */       field_179169_u.field_179058_a = p_179083_0_;
/*  410 */       field_179169_u.field_179056_b = p_179083_1_;
/*  411 */       field_179169_u.field_179057_c = p_179083_2_;
/*  412 */       field_179169_u.field_179055_d = p_179083_3_;
/*  413 */       GL11.glViewport(p_179083_0_, p_179083_1_, p_179083_2_, p_179083_3_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void colorMask(boolean p_179135_0_, boolean p_179135_1_, boolean p_179135_2_, boolean p_179135_3_) {
/*  419 */     if (p_179135_0_ != colorMaskState.field_179188_a || p_179135_1_ != colorMaskState.field_179186_b || p_179135_2_ != colorMaskState.field_179187_c || p_179135_3_ != colorMaskState.field_179185_d) {
/*      */       
/*  421 */       colorMaskState.field_179188_a = p_179135_0_;
/*  422 */       colorMaskState.field_179186_b = p_179135_1_;
/*  423 */       colorMaskState.field_179187_c = p_179135_2_;
/*  424 */       colorMaskState.field_179185_d = p_179135_3_;
/*  425 */       GL11.glColorMask(p_179135_0_, p_179135_1_, p_179135_2_, p_179135_3_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void clearDepth(double p_179151_0_) {
/*  431 */     if (p_179151_0_ != clearState.field_179205_a) {
/*      */       
/*  433 */       clearState.field_179205_a = p_179151_0_;
/*  434 */       GL11.glClearDepth(p_179151_0_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void clearColor(float p_179082_0_, float p_179082_1_, float p_179082_2_, float p_179082_3_) {
/*  440 */     if (p_179082_0_ != clearState.field_179203_b.field_179195_a || p_179082_1_ != clearState.field_179203_b.green || p_179082_2_ != clearState.field_179203_b.blue || p_179082_3_ != clearState.field_179203_b.alpha) {
/*      */       
/*  442 */       clearState.field_179203_b.field_179195_a = p_179082_0_;
/*  443 */       clearState.field_179203_b.green = p_179082_1_;
/*  444 */       clearState.field_179203_b.blue = p_179082_2_;
/*  445 */       clearState.field_179203_b.alpha = p_179082_3_;
/*  446 */       GL11.glClearColor(p_179082_0_, p_179082_1_, p_179082_2_, p_179082_3_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void clear(int p_179086_0_) {
/*  452 */     if (clearEnabled)
/*      */     {
/*  454 */       GL11.glClear(p_179086_0_);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void matrixMode(int p_179128_0_) {
/*  460 */     GL11.glMatrixMode(p_179128_0_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void loadIdentity() {
/*  465 */     GL11.glLoadIdentity();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void pushMatrix() {
/*  470 */     GL11.glPushMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void popMatrix() {
/*  475 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void getFloat(int p_179111_0_, FloatBuffer p_179111_1_) {
/*  480 */     GL11.glGetFloat(p_179111_0_, p_179111_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void ortho(double p_179130_0_, double p_179130_2_, double p_179130_4_, double p_179130_6_, double p_179130_8_, double p_179130_10_) {
/*  485 */     GL11.glOrtho(p_179130_0_, p_179130_2_, p_179130_4_, p_179130_6_, p_179130_8_, p_179130_10_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void rotate(float p_179114_0_, float p_179114_1_, float p_179114_2_, float p_179114_3_) {
/*  490 */     GL11.glRotatef(p_179114_0_, p_179114_1_, p_179114_2_, p_179114_3_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void scale(float p_179152_0_, float p_179152_1_, float p_179152_2_) {
/*  495 */     GL11.glScalef(p_179152_0_, p_179152_1_, p_179152_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void scale(double p_179139_0_, double p_179139_2_, double p_179139_4_) {
/*  500 */     GL11.glScaled(p_179139_0_, p_179139_2_, p_179139_4_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void translate(float p_179109_0_, float p_179109_1_, float p_179109_2_) {
/*  505 */     GL11.glTranslatef(p_179109_0_, p_179109_1_, p_179109_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void translate(double p_179137_0_, double p_179137_2_, double p_179137_4_) {
/*  510 */     GL11.glTranslated(p_179137_0_, p_179137_2_, p_179137_4_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void multMatrix(FloatBuffer p_179110_0_) {
/*  515 */     GL11.glMultMatrix(p_179110_0_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void color(float p_179131_0_, float p_179131_1_, float p_179131_2_, float p_179131_3_) {
/*  520 */     if (p_179131_0_ != colorState.field_179195_a || p_179131_1_ != colorState.green || p_179131_2_ != colorState.blue || p_179131_3_ != colorState.alpha) {
/*      */       
/*  522 */       colorState.field_179195_a = p_179131_0_;
/*  523 */       colorState.green = p_179131_1_;
/*  524 */       colorState.blue = p_179131_2_;
/*  525 */       colorState.alpha = p_179131_3_;
/*  526 */       GL11.glColor4f(p_179131_0_, p_179131_1_, p_179131_2_, p_179131_3_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void color(float p_179124_0_, float p_179124_1_, float p_179124_2_) {
/*  532 */     color(p_179124_0_, p_179124_1_, p_179124_2_, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void func_179117_G() {
/*  537 */     colorState.field_179195_a = colorState.green = colorState.blue = colorState.alpha = -1.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void callList(int p_179148_0_) {
/*  542 */     GL11.glCallList(p_179148_0_);
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getActiveTextureUnit() {
/*  547 */     return OpenGlHelper.defaultTexUnit + field_179162_o;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getBoundTexture() {
/*  552 */     return (field_179174_p[field_179162_o]).field_179059_b;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void checkBoundTexture() {
/*  557 */     if (Config.isMinecraftThread()) {
/*      */       
/*  559 */       int glAct = GL11.glGetInteger(34016);
/*  560 */       int glTex = GL11.glGetInteger(32873);
/*  561 */       int act = getActiveTextureUnit();
/*  562 */       int tex = getBoundTexture();
/*      */       
/*  564 */       if (tex > 0)
/*      */       {
/*  566 */         if (glAct != act || glTex != tex)
/*      */         {
/*  568 */           Config.dbg("checkTexture: act: " + act + ", glAct: " + glAct + ", tex: " + tex + ", glTex: " + glTex);
/*      */         }
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void deleteTextures(IntBuffer buf) {
/*  576 */     buf.rewind();
/*      */     
/*  578 */     while (buf.position() < buf.limit()) {
/*      */       
/*  580 */       int texId = buf.get();
/*  581 */       func_179150_h(texId);
/*      */     } 
/*      */     
/*  584 */     buf.rewind();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*      */     int var0;
/*  591 */     for (var0 = 0; var0 < 8; var0++)
/*      */     {
/*  593 */       field_179159_c[var0] = new BooleanState(16384 + var0);
/*      */     }
/*      */     
/*  596 */     for (var0 = 0; var0 < field_179174_p.length; var0++)
/*      */     {
/*  598 */       field_179174_p[var0] = new TextureState(null);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class AlphaState
/*      */   {
/*  611 */     public GlStateManager.BooleanState field_179208_a = new GlStateManager.BooleanState(3008);
/*  612 */     public int field_179206_b = 519;
/*  613 */     public float field_179207_c = -1.0F;
/*      */     
/*      */     private static final String __OBFID = "CL_00002556";
/*      */     
/*      */     AlphaState(GlStateManager.SwitchTexGen p_i46269_1_) {
/*  618 */       this();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private AlphaState() {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class BlendState
/*      */   {
/*  632 */     public GlStateManager.BooleanState field_179213_a = new GlStateManager.BooleanState(3042);
/*  633 */     public int field_179211_b = 1;
/*  634 */     public int field_179212_c = 0;
/*  635 */     public int field_179209_d = 1;
/*  636 */     public int field_179210_e = 0;
/*      */     
/*      */     private BlendState() {}
/*      */     
/*      */     BlendState(GlStateManager.SwitchTexGen p_i46268_1_) {
/*  641 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   static class BooleanState
/*      */   {
/*      */     private final int capability;
/*      */     private boolean currentState = false;
/*      */     
/*      */     public BooleanState(int p_i46267_1_) {
/*  651 */       this.capability = p_i46267_1_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void setDisabled() {
/*  656 */       setState(false);
/*      */     }
/*      */ 
/*      */     
/*      */     public void setEnabled() {
/*  661 */       setState(true);
/*      */     }
/*      */ 
/*      */     
/*      */     public void setState(boolean p_179199_1_) {
/*  666 */       if (p_179199_1_ != this.currentState) {
/*      */         
/*  668 */         this.currentState = p_179199_1_;
/*      */         
/*  670 */         if (p_179199_1_) {
/*      */           
/*  672 */           GL11.glEnable(this.capability);
/*      */         }
/*      */         else {
/*      */           
/*  676 */           GL11.glDisable(this.capability);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   static class ClearState
/*      */   {
/*      */     public double field_179205_a;
/*      */     public GlStateManager.Color field_179203_b;
/*      */     public int field_179204_c;
/*      */     
/*      */     private ClearState() {
/*  689 */       this.field_179205_a = 1.0D;
/*  690 */       this.field_179203_b = new GlStateManager.Color(0.0F, 0.0F, 0.0F, 0.0F);
/*  691 */       this.field_179204_c = 0;
/*      */     }
/*      */ 
/*      */     
/*      */     ClearState(GlStateManager.SwitchTexGen p_i46266_1_) {
/*  696 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   static class Color
/*      */   {
/*  702 */     public float field_179195_a = 1.0F;
/*  703 */     public float green = 1.0F;
/*  704 */     public float blue = 1.0F;
/*  705 */     public float alpha = 1.0F;
/*      */ 
/*      */     
/*      */     public Color() {}
/*      */     
/*      */     public Color(float p_i46265_1_, float p_i46265_2_, float p_i46265_3_, float p_i46265_4_) {
/*  711 */       this.field_179195_a = p_i46265_1_;
/*  712 */       this.green = p_i46265_2_;
/*  713 */       this.blue = p_i46265_3_;
/*  714 */       this.alpha = p_i46265_4_;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class ColorLogicState
/*      */   {
/*      */     public GlStateManager.BooleanState field_179197_a;
/*      */     public int field_179196_b;
/*      */     
/*      */     private ColorLogicState() {
/*  725 */       this.field_179197_a = new GlStateManager.BooleanState(3058);
/*  726 */       this.field_179196_b = 5379;
/*      */     }
/*      */ 
/*      */     
/*      */     ColorLogicState(GlStateManager.SwitchTexGen p_i46264_1_) {
/*  731 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class ColorMask
/*      */   {
/*      */     public boolean field_179188_a;
/*      */     public boolean field_179186_b;
/*      */     public boolean field_179187_c;
/*      */     public boolean field_179185_d;
/*      */     
/*      */     private ColorMask() {
/*  744 */       this.field_179188_a = true;
/*  745 */       this.field_179186_b = true;
/*  746 */       this.field_179187_c = true;
/*  747 */       this.field_179185_d = true;
/*      */     }
/*      */ 
/*      */     
/*      */     ColorMask(GlStateManager.SwitchTexGen p_i46263_1_) {
/*  752 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class ColorMaterialState
/*      */   {
/*      */     public GlStateManager.BooleanState field_179191_a;
/*      */     public int field_179189_b;
/*      */     public int field_179190_c;
/*      */     
/*      */     private ColorMaterialState() {
/*  764 */       this.field_179191_a = new GlStateManager.BooleanState(2903);
/*  765 */       this.field_179189_b = 1032;
/*  766 */       this.field_179190_c = 5634;
/*      */     }
/*      */ 
/*      */     
/*      */     ColorMaterialState(GlStateManager.SwitchTexGen p_i46262_1_) {
/*  771 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class CullState
/*      */   {
/*      */     public GlStateManager.BooleanState field_179054_a;
/*      */     public int field_179053_b;
/*      */     
/*      */     private CullState() {
/*  782 */       this.field_179054_a = new GlStateManager.BooleanState(2884);
/*  783 */       this.field_179053_b = 1029;
/*      */     }
/*      */ 
/*      */     
/*      */     CullState(GlStateManager.SwitchTexGen p_i46261_1_) {
/*  788 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class DepthState
/*      */   {
/*      */     public GlStateManager.BooleanState field_179052_a;
/*      */     public boolean field_179050_b;
/*      */     public int field_179051_c;
/*      */     
/*      */     private DepthState() {
/*  800 */       this.field_179052_a = new GlStateManager.BooleanState(2929);
/*  801 */       this.field_179050_b = true;
/*  802 */       this.field_179051_c = 513;
/*      */     }
/*      */ 
/*      */     
/*      */     DepthState(GlStateManager.SwitchTexGen p_i46260_1_) {
/*  807 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class FogState
/*      */   {
/*      */     public GlStateManager.BooleanState field_179049_a;
/*      */     public int field_179047_b;
/*      */     public float field_179048_c;
/*      */     public float field_179045_d;
/*      */     public float field_179046_e;
/*      */     
/*      */     private FogState() {
/*  821 */       this.field_179049_a = new GlStateManager.BooleanState(2912);
/*  822 */       this.field_179047_b = 2048;
/*  823 */       this.field_179048_c = 1.0F;
/*  824 */       this.field_179045_d = 0.0F;
/*  825 */       this.field_179046_e = 1.0F;
/*      */     }
/*      */ 
/*      */     
/*      */     FogState(GlStateManager.SwitchTexGen p_i46259_1_) {
/*  830 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class PolygonOffsetState
/*      */   {
/*      */     public GlStateManager.BooleanState field_179044_a;
/*      */     public GlStateManager.BooleanState field_179042_b;
/*      */     public float field_179043_c;
/*      */     public float field_179041_d;
/*      */     
/*      */     private PolygonOffsetState() {
/*  843 */       this.field_179044_a = new GlStateManager.BooleanState(32823);
/*  844 */       this.field_179042_b = new GlStateManager.BooleanState(10754);
/*  845 */       this.field_179043_c = 0.0F;
/*  846 */       this.field_179041_d = 0.0F;
/*      */     }
/*      */ 
/*      */     
/*      */     PolygonOffsetState(GlStateManager.SwitchTexGen p_i46258_1_) {
/*  851 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class StencilFunc
/*      */   {
/*      */     public int field_179081_a;
/*      */     public int field_179079_b;
/*      */     public int field_179080_c;
/*      */     
/*      */     private StencilFunc() {
/*  863 */       this.field_179081_a = 519;
/*  864 */       this.field_179079_b = 0;
/*  865 */       this.field_179080_c = -1;
/*      */     }
/*      */ 
/*      */     
/*      */     StencilFunc(GlStateManager.SwitchTexGen p_i46257_1_) {
/*  870 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class StencilState
/*      */   {
/*      */     public GlStateManager.StencilFunc field_179078_a;
/*      */     public int field_179076_b;
/*      */     public int field_179077_c;
/*      */     public int field_179074_d;
/*      */     public int field_179075_e;
/*      */     
/*      */     private StencilState() {
/*  884 */       this.field_179078_a = new GlStateManager.StencilFunc(null);
/*  885 */       this.field_179076_b = -1;
/*  886 */       this.field_179077_c = 7680;
/*  887 */       this.field_179074_d = 7680;
/*  888 */       this.field_179075_e = 7680;
/*      */     }
/*      */ 
/*      */     
/*      */     StencilState(GlStateManager.SwitchTexGen p_i46256_1_) {
/*  893 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   static final class SwitchTexGen
/*      */   {
/*  899 */     static final int[] field_179175_a = new int[(GlStateManager.TexGen.values()).length];
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/*  905 */         field_179175_a[GlStateManager.TexGen.S.ordinal()] = 1;
/*      */       }
/*  907 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  914 */         field_179175_a[GlStateManager.TexGen.T.ordinal()] = 2;
/*      */       }
/*  916 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  923 */         field_179175_a[GlStateManager.TexGen.R.ordinal()] = 3;
/*      */       }
/*  925 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  932 */         field_179175_a[GlStateManager.TexGen.Q.ordinal()] = 4;
/*      */       }
/*  934 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public enum TexGen
/*      */   {
/*  943 */     S("S", 0, "S", 0),
/*  944 */     T("T", 1, "T", 1),
/*  945 */     R("R", 2, "R", 2),
/*  946 */     Q("Q", 3, "Q", 3);
/*  947 */     private static final TexGen[] $VALUES = new TexGen[] { S, T, R, Q };
/*      */     private static final String __OBFID = "CL_00002542";
/*      */     
/*      */     static {
/*      */     
/*      */     } }
/*      */   
/*      */   static class TexGenCoord {
/*      */     public GlStateManager.BooleanState field_179067_a;
/*      */     public int field_179065_b;
/*  957 */     public int field_179066_c = -1;
/*      */ 
/*      */     
/*      */     public TexGenCoord(int p_i46254_1_, int p_i46254_2_) {
/*  961 */       this.field_179065_b = p_i46254_1_;
/*  962 */       this.field_179067_a = new GlStateManager.BooleanState(p_i46254_2_);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class TexGenState
/*      */   {
/*      */     public GlStateManager.TexGenCoord field_179064_a;
/*      */     public GlStateManager.TexGenCoord field_179062_b;
/*      */     public GlStateManager.TexGenCoord field_179063_c;
/*      */     public GlStateManager.TexGenCoord field_179061_d;
/*      */     
/*      */     private TexGenState() {
/*  975 */       this.field_179064_a = new GlStateManager.TexGenCoord(8192, 3168);
/*  976 */       this.field_179062_b = new GlStateManager.TexGenCoord(8193, 3169);
/*  977 */       this.field_179063_c = new GlStateManager.TexGenCoord(8194, 3170);
/*  978 */       this.field_179061_d = new GlStateManager.TexGenCoord(8195, 3171);
/*      */     }
/*      */ 
/*      */     
/*      */     TexGenState(GlStateManager.SwitchTexGen p_i46253_1_) {
/*  983 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class TextureState
/*      */   {
/*      */     public GlStateManager.BooleanState field_179060_a;
/*      */     public int field_179059_b;
/*      */     
/*      */     private TextureState() {
/*  994 */       this.field_179060_a = new GlStateManager.BooleanState(3553);
/*  995 */       this.field_179059_b = 0;
/*      */     }
/*      */ 
/*      */     
/*      */     TextureState(GlStateManager.SwitchTexGen p_i46252_1_) {
/* 1000 */       this();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class Viewport
/*      */   {
/*      */     public int field_179058_a;
/*      */     public int field_179056_b;
/*      */     public int field_179057_c;
/*      */     public int field_179055_d;
/*      */     
/*      */     private Viewport() {
/* 1013 */       this.field_179058_a = 0;
/* 1014 */       this.field_179056_b = 0;
/* 1015 */       this.field_179057_c = 0;
/* 1016 */       this.field_179055_d = 0;
/*      */     }
/*      */ 
/*      */     
/*      */     Viewport(GlStateManager.SwitchTexGen p_i46251_1_) {
/* 1021 */       this();
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\GlStateManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */