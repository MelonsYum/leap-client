/*     */ package shadersmod.client;
/*     */ 
/*     */ import net.minecraft.client.renderer.culling.ClippingHelper;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class ClippingHelperShadow
/*     */   extends ClippingHelper {
/*   8 */   private static ClippingHelperShadow instance = new ClippingHelperShadow();
/*   9 */   float[] frustumTest = new float[6];
/*  10 */   float[][] shadowClipPlanes = new float[10][4];
/*     */   int shadowClipPlaneCount;
/*  12 */   float[] matInvMP = new float[16];
/*  13 */   float[] vecIntersection = new float[4];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBoxInFrustum(double x1, double y1, double z1, double x2, double y2, double z2) {
/*  20 */     for (int index = 0; index < this.shadowClipPlaneCount; index++) {
/*     */       
/*  22 */       float[] plane = this.shadowClipPlanes[index];
/*     */       
/*  24 */       if (dot4(plane, x1, y1, z1) <= 0.0D && dot4(plane, x2, y1, z1) <= 0.0D && dot4(plane, x1, y2, z1) <= 0.0D && dot4(plane, x2, y2, z1) <= 0.0D && dot4(plane, x1, y1, z2) <= 0.0D && dot4(plane, x2, y1, z2) <= 0.0D && dot4(plane, x1, y2, z2) <= 0.0D && dot4(plane, x2, y2, z2) <= 0.0D)
/*     */       {
/*  26 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  30 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private double dot4(float[] plane, double x, double y, double z) {
/*  35 */     return plane[0] * x + plane[1] * y + plane[2] * z + plane[3];
/*     */   }
/*     */ 
/*     */   
/*     */   private double dot3(float[] vecA, float[] vecB) {
/*  40 */     return vecA[0] * vecB[0] + vecA[1] * vecB[1] + vecA[2] * vecB[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public static ClippingHelper getInstance() {
/*  45 */     instance.init();
/*  46 */     return instance;
/*     */   }
/*     */ 
/*     */   
/*     */   private void normalizePlane(float[] plane) {
/*  51 */     float length = MathHelper.sqrt_float(plane[0] * plane[0] + plane[1] * plane[1] + plane[2] * plane[2]);
/*  52 */     plane[0] = plane[0] / length;
/*  53 */     plane[1] = plane[1] / length;
/*  54 */     plane[2] = plane[2] / length;
/*  55 */     plane[3] = plane[3] / length;
/*     */   }
/*     */ 
/*     */   
/*     */   private void normalize3(float[] plane) {
/*  60 */     float length = MathHelper.sqrt_float(plane[0] * plane[0] + plane[1] * plane[1] + plane[2] * plane[2]);
/*     */     
/*  62 */     if (length == 0.0F)
/*     */     {
/*  64 */       length = 1.0F;
/*     */     }
/*     */     
/*  67 */     plane[0] = plane[0] / length;
/*  68 */     plane[1] = plane[1] / length;
/*  69 */     plane[2] = plane[2] / length;
/*     */   }
/*     */ 
/*     */   
/*     */   private void assignPlane(float[] plane, float a, float b, float c, float d) {
/*  74 */     float length = (float)Math.sqrt((a * a + b * b + c * c));
/*  75 */     plane[0] = a / length;
/*  76 */     plane[1] = b / length;
/*  77 */     plane[2] = c / length;
/*  78 */     plane[3] = d / length;
/*     */   }
/*     */ 
/*     */   
/*     */   private void copyPlane(float[] dst, float[] src) {
/*  83 */     dst[0] = src[0];
/*  84 */     dst[1] = src[1];
/*  85 */     dst[2] = src[2];
/*  86 */     dst[3] = src[3];
/*     */   }
/*     */ 
/*     */   
/*     */   private void cross3(float[] out, float[] a, float[] b) {
/*  91 */     out[0] = a[1] * b[2] - a[2] * b[1];
/*  92 */     out[1] = a[2] * b[0] - a[0] * b[2];
/*  93 */     out[2] = a[0] * b[1] - a[1] * b[0];
/*     */   }
/*     */ 
/*     */   
/*     */   private void addShadowClipPlane(float[] plane) {
/*  98 */     copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], plane);
/*     */   }
/*     */ 
/*     */   
/*     */   private float length(float x, float y, float z) {
/* 103 */     return (float)Math.sqrt((x * x + y * y + z * z));
/*     */   }
/*     */ 
/*     */   
/*     */   private float distance(float x1, float y1, float z1, float x2, float y2, float z2) {
/* 108 */     return length(x1 - x2, y1 - y2, z1 - z2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void makeShadowPlane(float[] shadowPlane, float[] positivePlane, float[] negativePlane, float[] vecSun) {
/* 113 */     cross3(this.vecIntersection, positivePlane, negativePlane);
/* 114 */     cross3(shadowPlane, this.vecIntersection, vecSun);
/* 115 */     normalize3(shadowPlane);
/* 116 */     float dotPN = (float)dot3(positivePlane, negativePlane);
/* 117 */     float dotSN = (float)dot3(shadowPlane, negativePlane);
/* 118 */     float disSN = distance(shadowPlane[0], shadowPlane[1], shadowPlane[2], negativePlane[0] * dotSN, negativePlane[1] * dotSN, negativePlane[2] * dotSN);
/* 119 */     float disPN = distance(positivePlane[0], positivePlane[1], positivePlane[2], negativePlane[0] * dotPN, negativePlane[1] * dotPN, negativePlane[2] * dotPN);
/* 120 */     float k1 = disSN / disPN;
/* 121 */     float dotSP = (float)dot3(shadowPlane, positivePlane);
/* 122 */     float disSP = distance(shadowPlane[0], shadowPlane[1], shadowPlane[2], positivePlane[0] * dotSP, positivePlane[1] * dotSP, positivePlane[2] * dotSP);
/* 123 */     float disNP = distance(negativePlane[0], negativePlane[1], negativePlane[2], positivePlane[0] * dotPN, positivePlane[1] * dotPN, positivePlane[2] * dotPN);
/* 124 */     float k2 = disSP / disNP;
/* 125 */     shadowPlane[3] = positivePlane[3] * k1 + negativePlane[3] * k2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void init() {
/* 130 */     float[] matPrj = this.field_178625_b;
/* 131 */     float[] matMdv = this.field_178626_c;
/* 132 */     float[] matMP = this.clippingMatrix;
/* 133 */     System.arraycopy(Shaders.faProjection, 0, matPrj, 0, 16);
/* 134 */     System.arraycopy(Shaders.faModelView, 0, matMdv, 0, 16);
/* 135 */     SMath.multiplyMat4xMat4(matMP, matMdv, matPrj);
/* 136 */     assignPlane(this.frustum[0], matMP[3] - matMP[0], matMP[7] - matMP[4], matMP[11] - matMP[8], matMP[15] - matMP[12]);
/* 137 */     assignPlane(this.frustum[1], matMP[3] + matMP[0], matMP[7] + matMP[4], matMP[11] + matMP[8], matMP[15] + matMP[12]);
/* 138 */     assignPlane(this.frustum[2], matMP[3] + matMP[1], matMP[7] + matMP[5], matMP[11] + matMP[9], matMP[15] + matMP[13]);
/* 139 */     assignPlane(this.frustum[3], matMP[3] - matMP[1], matMP[7] - matMP[5], matMP[11] - matMP[9], matMP[15] - matMP[13]);
/* 140 */     assignPlane(this.frustum[4], matMP[3] - matMP[2], matMP[7] - matMP[6], matMP[11] - matMP[10], matMP[15] - matMP[14]);
/* 141 */     assignPlane(this.frustum[5], matMP[3] + matMP[2], matMP[7] + matMP[6], matMP[11] + matMP[10], matMP[15] + matMP[14]);
/* 142 */     float[] vecSun = Shaders.shadowLightPositionVector;
/* 143 */     float test0 = (float)dot3(this.frustum[0], vecSun);
/* 144 */     float test1 = (float)dot3(this.frustum[1], vecSun);
/* 145 */     float test2 = (float)dot3(this.frustum[2], vecSun);
/* 146 */     float test3 = (float)dot3(this.frustum[3], vecSun);
/* 147 */     float test4 = (float)dot3(this.frustum[4], vecSun);
/* 148 */     float test5 = (float)dot3(this.frustum[5], vecSun);
/* 149 */     this.shadowClipPlaneCount = 0;
/*     */     
/* 151 */     if (test0 >= 0.0F) {
/*     */       
/* 153 */       copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0]);
/*     */       
/* 155 */       if (test0 > 0.0F) {
/*     */         
/* 157 */         if (test2 < 0.0F)
/*     */         {
/* 159 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0], this.frustum[2], vecSun);
/*     */         }
/*     */         
/* 162 */         if (test3 < 0.0F)
/*     */         {
/* 164 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0], this.frustum[3], vecSun);
/*     */         }
/*     */         
/* 167 */         if (test4 < 0.0F)
/*     */         {
/* 169 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0], this.frustum[4], vecSun);
/*     */         }
/*     */         
/* 172 */         if (test5 < 0.0F)
/*     */         {
/* 174 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[0], this.frustum[5], vecSun);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 179 */     if (test1 >= 0.0F) {
/*     */       
/* 181 */       copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1]);
/*     */       
/* 183 */       if (test1 > 0.0F) {
/*     */         
/* 185 */         if (test2 < 0.0F)
/*     */         {
/* 187 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1], this.frustum[2], vecSun);
/*     */         }
/*     */         
/* 190 */         if (test3 < 0.0F)
/*     */         {
/* 192 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1], this.frustum[3], vecSun);
/*     */         }
/*     */         
/* 195 */         if (test4 < 0.0F)
/*     */         {
/* 197 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1], this.frustum[4], vecSun);
/*     */         }
/*     */         
/* 200 */         if (test5 < 0.0F)
/*     */         {
/* 202 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[1], this.frustum[5], vecSun);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 207 */     if (test2 >= 0.0F) {
/*     */       
/* 209 */       copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2]);
/*     */       
/* 211 */       if (test2 > 0.0F) {
/*     */         
/* 213 */         if (test0 < 0.0F)
/*     */         {
/* 215 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2], this.frustum[0], vecSun);
/*     */         }
/*     */         
/* 218 */         if (test1 < 0.0F)
/*     */         {
/* 220 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2], this.frustum[1], vecSun);
/*     */         }
/*     */         
/* 223 */         if (test4 < 0.0F)
/*     */         {
/* 225 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2], this.frustum[4], vecSun);
/*     */         }
/*     */         
/* 228 */         if (test5 < 0.0F)
/*     */         {
/* 230 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[2], this.frustum[5], vecSun);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 235 */     if (test3 >= 0.0F) {
/*     */       
/* 237 */       copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3]);
/*     */       
/* 239 */       if (test3 > 0.0F) {
/*     */         
/* 241 */         if (test0 < 0.0F)
/*     */         {
/* 243 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3], this.frustum[0], vecSun);
/*     */         }
/*     */         
/* 246 */         if (test1 < 0.0F)
/*     */         {
/* 248 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3], this.frustum[1], vecSun);
/*     */         }
/*     */         
/* 251 */         if (test4 < 0.0F)
/*     */         {
/* 253 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3], this.frustum[4], vecSun);
/*     */         }
/*     */         
/* 256 */         if (test5 < 0.0F)
/*     */         {
/* 258 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[3], this.frustum[5], vecSun);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 263 */     if (test4 >= 0.0F) {
/*     */       
/* 265 */       copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4]);
/*     */       
/* 267 */       if (test4 > 0.0F) {
/*     */         
/* 269 */         if (test0 < 0.0F)
/*     */         {
/* 271 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4], this.frustum[0], vecSun);
/*     */         }
/*     */         
/* 274 */         if (test1 < 0.0F)
/*     */         {
/* 276 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4], this.frustum[1], vecSun);
/*     */         }
/*     */         
/* 279 */         if (test2 < 0.0F)
/*     */         {
/* 281 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4], this.frustum[2], vecSun);
/*     */         }
/*     */         
/* 284 */         if (test3 < 0.0F)
/*     */         {
/* 286 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[4], this.frustum[3], vecSun);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 291 */     if (test5 >= 0.0F) {
/*     */       
/* 293 */       copyPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5]);
/*     */       
/* 295 */       if (test5 > 0.0F) {
/*     */         
/* 297 */         if (test0 < 0.0F)
/*     */         {
/* 299 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5], this.frustum[0], vecSun);
/*     */         }
/*     */         
/* 302 */         if (test1 < 0.0F)
/*     */         {
/* 304 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5], this.frustum[1], vecSun);
/*     */         }
/*     */         
/* 307 */         if (test2 < 0.0F)
/*     */         {
/* 309 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5], this.frustum[2], vecSun);
/*     */         }
/*     */         
/* 312 */         if (test3 < 0.0F)
/*     */         {
/* 314 */           makeShadowPlane(this.shadowClipPlanes[this.shadowClipPlaneCount++], this.frustum[5], this.frustum[3], vecSun);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ClippingHelperShadow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */