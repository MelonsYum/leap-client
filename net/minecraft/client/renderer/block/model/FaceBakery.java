/*     */ package net.minecraft.client.renderer.block.model;
/*     */ import javax.vecmath.AxisAngle4d;
/*     */ import javax.vecmath.Matrix4d;
/*     */ import javax.vecmath.Tuple3d;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3d;
/*     */ import javax.vecmath.Vector3f;
/*     */ import net.minecraft.client.renderer.EnumFaceing;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.resources.model.ModelRotation;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraftforge.client.model.ITransformation;
/*     */ import optifine.Config;
/*     */ import optifine.Reflector;
/*     */ import shadersmod.client.Shaders;
/*     */ 
/*     */ public class FaceBakery {
/*  20 */   private static final double field_178418_a = 1.0D / Math.cos(0.39269908169872414D) - 1.0D;
/*  21 */   private static final double field_178417_b = 1.0D / Math.cos(0.7853981633974483D) - 1.0D;
/*     */   
/*     */   private static final String __OBFID = "CL_00002490";
/*     */   
/*     */   public BakedQuad func_178414_a(Vector3f posFrom, Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, ModelRotation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
/*  26 */     return makeBakedQuad(posFrom, posTo, face, sprite, facing, (ITransformation)modelRotationIn, partRotation, uvLocked, shade);
/*     */   }
/*     */ 
/*     */   
/*     */   public BakedQuad makeBakedQuad(Vector3f posFrom, Vector3f posTo, BlockPartFace face, TextureAtlasSprite sprite, EnumFacing facing, ITransformation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
/*  31 */     int[] var10 = makeQuadVertexData(face, sprite, facing, func_178403_a(posFrom, posTo), modelRotationIn, partRotation, uvLocked, shade);
/*  32 */     EnumFacing var11 = func_178410_a(var10);
/*     */     
/*  34 */     if (uvLocked)
/*     */     {
/*  36 */       func_178409_a(var10, var11, face.field_178243_e, sprite);
/*     */     }
/*     */     
/*  39 */     if (partRotation == null)
/*     */     {
/*  41 */       func_178408_a(var10, var11);
/*     */     }
/*     */     
/*  44 */     if (Reflector.ForgeHooksClient_fillNormal.exists())
/*     */     {
/*  46 */       Reflector.callVoid(Reflector.ForgeHooksClient_fillNormal, new Object[] { var10, var11 });
/*     */     }
/*     */     
/*  49 */     return new BakedQuad(var10, face.field_178245_c, var11, sprite);
/*     */   }
/*     */ 
/*     */   
/*     */   private int[] makeQuadVertexData(BlockPartFace p_178405_1_, TextureAtlasSprite p_178405_2_, EnumFacing p_178405_3_, float[] p_178405_4_, ITransformation p_178405_5_, BlockPartRotation p_178405_6_, boolean p_178405_7_, boolean shade) {
/*  54 */     byte vertexSize = 28;
/*     */     
/*  56 */     if (Config.isShaders())
/*     */     {
/*  58 */       vertexSize = 56;
/*     */     }
/*     */     
/*  61 */     int[] var9 = new int[vertexSize];
/*     */     
/*  63 */     for (int var10 = 0; var10 < 4; var10++)
/*     */     {
/*  65 */       fillVertexData(var9, var10, p_178405_3_, p_178405_1_, p_178405_4_, p_178405_2_, p_178405_5_, p_178405_6_, p_178405_7_, shade);
/*     */     }
/*     */     
/*  68 */     return var9;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_178413_a(EnumFacing p_178413_1_) {
/*  73 */     float var2 = func_178412_b(p_178413_1_);
/*  74 */     int var3 = MathHelper.clamp_int((int)(var2 * 255.0F), 0, 255);
/*  75 */     return 0xFF000000 | var3 << 16 | var3 << 8 | var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private float func_178412_b(EnumFacing p_178412_1_) {
/*  80 */     switch (SwitchEnumFacing.field_178400_a[p_178412_1_.ordinal()]) {
/*     */       
/*     */       case 1:
/*  83 */         if (Config.isShaders())
/*     */         {
/*  85 */           return Shaders.blockLightLevel05;
/*     */         }
/*     */         
/*  88 */         return 0.5F;
/*     */       
/*     */       case 2:
/*  91 */         return 1.0F;
/*     */       
/*     */       case 3:
/*     */       case 4:
/*  95 */         if (Config.isShaders())
/*     */         {
/*  97 */           return Shaders.blockLightLevel08;
/*     */         }
/*     */         
/* 100 */         return 0.8F;
/*     */       
/*     */       case 5:
/*     */       case 6:
/* 104 */         if (Config.isShaders())
/*     */         {
/* 106 */           return Shaders.blockLightLevel06;
/*     */         }
/*     */         
/* 109 */         return 0.6F;
/*     */     } 
/*     */     
/* 112 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] func_178403_a(Vector3f pos1, Vector3f pos2) {
/* 118 */     float[] var3 = new float[(EnumFacing.values()).length];
/* 119 */     var3[EnumFaceing.Constants.field_179176_f] = pos1.x / 16.0F;
/* 120 */     var3[EnumFaceing.Constants.field_179178_e] = pos1.y / 16.0F;
/* 121 */     var3[EnumFaceing.Constants.field_179177_d] = pos1.z / 16.0F;
/* 122 */     var3[EnumFaceing.Constants.field_179180_c] = pos2.x / 16.0F;
/* 123 */     var3[EnumFaceing.Constants.field_179179_b] = pos2.y / 16.0F;
/* 124 */     var3[EnumFaceing.Constants.field_179181_a] = pos2.z / 16.0F;
/* 125 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private void fillVertexData(int[] faceData, int vertexIndex, EnumFacing facing, BlockPartFace partFace, float[] p_178402_5_, TextureAtlasSprite sprite, ITransformation modelRotationIn, BlockPartRotation partRotation, boolean uvLocked, boolean shade) {
/* 130 */     EnumFacing var11 = modelRotationIn.rotate(facing);
/* 131 */     int var12 = shade ? func_178413_a(var11) : -1;
/* 132 */     EnumFaceing.VertexInformation var13 = EnumFaceing.func_179027_a(facing).func_179025_a(vertexIndex);
/* 133 */     Vector3d var14 = new Vector3d(p_178402_5_[var13.field_179184_a], p_178402_5_[var13.field_179182_b], p_178402_5_[var13.field_179183_c]);
/* 134 */     func_178407_a(var14, partRotation);
/* 135 */     int var15 = rotateVertex(var14, facing, vertexIndex, modelRotationIn, uvLocked);
/* 136 */     func_178404_a(faceData, var15, vertexIndex, var14, var12, sprite, partFace.field_178243_e);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178404_a(int[] faceData, int storeIndex, int vertexIndex, Vector3d position, int shadeColor, TextureAtlasSprite sprite, BlockFaceUV faceUV) {
/* 141 */     int step = faceData.length / 4;
/* 142 */     int var8 = storeIndex * step;
/* 143 */     faceData[var8] = Float.floatToRawIntBits((float)position.x);
/* 144 */     faceData[var8 + 1] = Float.floatToRawIntBits((float)position.y);
/* 145 */     faceData[var8 + 2] = Float.floatToRawIntBits((float)position.z);
/* 146 */     faceData[var8 + 3] = shadeColor;
/* 147 */     faceData[var8 + 4] = Float.floatToRawIntBits(sprite.getInterpolatedU(faceUV.func_178348_a(vertexIndex)));
/* 148 */     faceData[var8 + 4 + 1] = Float.floatToRawIntBits(sprite.getInterpolatedV(faceUV.func_178346_b(vertexIndex)));
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178407_a(Vector3d p_178407_1_, BlockPartRotation p_178407_2_) {
/* 153 */     if (p_178407_2_ != null) {
/*     */       
/* 155 */       Matrix4d var3 = func_178411_a();
/* 156 */       Vector3d var4 = new Vector3d(0.0D, 0.0D, 0.0D);
/*     */       
/* 158 */       switch (SwitchEnumFacing.field_178399_b[p_178407_2_.field_178342_b.ordinal()]) {
/*     */         
/*     */         case 1:
/* 161 */           var3.mul(func_178416_a(new AxisAngle4d(1.0D, 0.0D, 0.0D, p_178407_2_.field_178343_c * 0.017453292519943295D)));
/* 162 */           var4.set(0.0D, 1.0D, 1.0D);
/*     */           break;
/*     */         
/*     */         case 2:
/* 166 */           var3.mul(func_178416_a(new AxisAngle4d(0.0D, 1.0D, 0.0D, p_178407_2_.field_178343_c * 0.017453292519943295D)));
/* 167 */           var4.set(1.0D, 0.0D, 1.0D);
/*     */           break;
/*     */         
/*     */         case 3:
/* 171 */           var3.mul(func_178416_a(new AxisAngle4d(0.0D, 0.0D, 1.0D, p_178407_2_.field_178343_c * 0.017453292519943295D)));
/* 172 */           var4.set(1.0D, 1.0D, 0.0D); break;
/* 173 */       }  if (p_178407_2_
/*     */         
/* 175 */         .field_178341_d) {
/*     */         
/* 177 */         if (Math.abs(p_178407_2_.field_178343_c) == 22.5F) {
/*     */           
/* 179 */           var4.scale(field_178418_a);
/*     */         }
/*     */         else {
/*     */           
/* 183 */           var4.scale(field_178417_b);
/*     */         } 
/*     */         
/* 186 */         var4.add((Tuple3d)new Vector3d(1.0D, 1.0D, 1.0D));
/*     */       }
/*     */       else {
/*     */         
/* 190 */         var4.set((Tuple3d)new Vector3d(1.0D, 1.0D, 1.0D));
/*     */       } 
/*     */       
/* 193 */       func_178406_a(p_178407_1_, new Vector3d(p_178407_2_.field_178344_a), var3, var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178415_a(Vector3d position, EnumFacing facing, int vertexIndex, ModelRotation modelRotationIn, boolean uvLocked) {
/* 199 */     return rotateVertex(position, facing, vertexIndex, (ITransformation)modelRotationIn, uvLocked);
/*     */   }
/*     */ 
/*     */   
/*     */   public int rotateVertex(Vector3d position, EnumFacing facing, int vertexIndex, ITransformation modelRotationIn, boolean uvLocked) {
/* 204 */     if (modelRotationIn == ModelRotation.X0_Y0)
/*     */     {
/* 206 */       return vertexIndex;
/*     */     }
/*     */ 
/*     */     
/* 210 */     if (Reflector.ForgeHooksClient_transform.exists()) {
/*     */       
/* 212 */       Reflector.call(Reflector.ForgeHooksClient_transform, new Object[] { position, modelRotationIn.getMatrix() });
/*     */     }
/*     */     else {
/*     */       
/* 216 */       func_178406_a(position, new Vector3d(0.5D, 0.5D, 0.5D), new Matrix4d(modelRotationIn.getMatrix()), new Vector3d(1.0D, 1.0D, 1.0D));
/*     */     } 
/*     */     
/* 219 */     return modelRotationIn.rotate(facing, vertexIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_178406_a(Vector3d position, Vector3d rotationOrigin, Matrix4d rotationMatrix, Vector3d scale) {
/* 225 */     position.sub((Tuple3d)rotationOrigin);
/* 226 */     rotationMatrix.transform(position);
/* 227 */     position.x *= scale.x;
/* 228 */     position.y *= scale.y;
/* 229 */     position.z *= scale.z;
/* 230 */     position.add((Tuple3d)rotationOrigin);
/*     */   }
/*     */ 
/*     */   
/*     */   private Matrix4d func_178416_a(AxisAngle4d p_178416_1_) {
/* 235 */     Matrix4d var2 = func_178411_a();
/* 236 */     var2.setRotation(p_178416_1_);
/* 237 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private Matrix4d func_178411_a() {
/* 242 */     Matrix4d var1 = new Matrix4d();
/* 243 */     var1.setIdentity();
/* 244 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumFacing func_178410_a(int[] p_178410_0_) {
/* 249 */     int step = p_178410_0_.length / 4;
/* 250 */     int step2 = step * 2;
/* 251 */     int step3 = step * 3;
/* 252 */     Vector3f var1 = new Vector3f(Float.intBitsToFloat(p_178410_0_[0]), Float.intBitsToFloat(p_178410_0_[1]), Float.intBitsToFloat(p_178410_0_[2]));
/* 253 */     Vector3f var2 = new Vector3f(Float.intBitsToFloat(p_178410_0_[step]), Float.intBitsToFloat(p_178410_0_[step + 1]), Float.intBitsToFloat(p_178410_0_[step + 2]));
/* 254 */     Vector3f var3 = new Vector3f(Float.intBitsToFloat(p_178410_0_[step2]), Float.intBitsToFloat(p_178410_0_[step2 + 1]), Float.intBitsToFloat(p_178410_0_[step2 + 2]));
/* 255 */     Vector3f var4 = new Vector3f();
/* 256 */     Vector3f var5 = new Vector3f();
/* 257 */     Vector3f var6 = new Vector3f();
/* 258 */     var4.sub((Tuple3f)var1, (Tuple3f)var2);
/* 259 */     var5.sub((Tuple3f)var3, (Tuple3f)var2);
/* 260 */     var6.cross(var5, var4);
/* 261 */     var6.normalize();
/* 262 */     EnumFacing var7 = null;
/* 263 */     float var8 = 0.0F;
/* 264 */     EnumFacing[] var9 = EnumFacing.values();
/* 265 */     int var10 = var9.length;
/*     */     
/* 267 */     for (int var11 = 0; var11 < var10; var11++) {
/*     */       
/* 269 */       EnumFacing var12 = var9[var11];
/* 270 */       Vec3i var13 = var12.getDirectionVec();
/* 271 */       Vector3f var14 = new Vector3f(var13.getX(), var13.getY(), var13.getZ());
/* 272 */       float var15 = var6.dot(var14);
/*     */       
/* 274 */       if (var15 >= 0.0F && var15 > var8) {
/*     */         
/* 276 */         var8 = var15;
/* 277 */         var7 = var12;
/*     */       } 
/*     */     } 
/*     */     
/* 281 */     if (var8 < 0.719F)
/*     */     {
/* 283 */       if (var7 != EnumFacing.EAST && var7 != EnumFacing.WEST && var7 != EnumFacing.NORTH && var7 != EnumFacing.SOUTH) {
/*     */         
/* 285 */         var7 = EnumFacing.UP;
/*     */       }
/*     */       else {
/*     */         
/* 289 */         var7 = EnumFacing.NORTH;
/*     */       } 
/*     */     }
/*     */     
/* 293 */     return (var7 == null) ? EnumFacing.UP : var7;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178409_a(int[] p_178409_1_, EnumFacing p_178409_2_, BlockFaceUV p_178409_3_, TextureAtlasSprite p_178409_4_) {
/* 298 */     for (int var5 = 0; var5 < 4; var5++)
/*     */     {
/* 300 */       func_178401_a(var5, p_178409_1_, p_178409_2_, p_178409_3_, p_178409_4_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178408_a(int[] p_178408_1_, EnumFacing p_178408_2_) {
/* 306 */     int[] var3 = new int[p_178408_1_.length];
/* 307 */     System.arraycopy(p_178408_1_, 0, var3, 0, p_178408_1_.length);
/* 308 */     float[] var4 = new float[(EnumFacing.values()).length];
/* 309 */     var4[EnumFaceing.Constants.field_179176_f] = 999.0F;
/* 310 */     var4[EnumFaceing.Constants.field_179178_e] = 999.0F;
/* 311 */     var4[EnumFaceing.Constants.field_179177_d] = 999.0F;
/* 312 */     var4[EnumFaceing.Constants.field_179180_c] = -999.0F;
/* 313 */     var4[EnumFaceing.Constants.field_179179_b] = -999.0F;
/* 314 */     var4[EnumFaceing.Constants.field_179181_a] = -999.0F;
/* 315 */     int step = p_178408_1_.length / 4;
/*     */ 
/*     */ 
/*     */     
/* 319 */     for (int var17 = 0; var17 < 4; var17++) {
/*     */       
/* 321 */       int i = step * var17;
/* 322 */       float var18 = Float.intBitsToFloat(var3[i]);
/* 323 */       float var19 = Float.intBitsToFloat(var3[i + 1]);
/* 324 */       float var9 = Float.intBitsToFloat(var3[i + 2]);
/*     */       
/* 326 */       if (var18 < var4[EnumFaceing.Constants.field_179176_f])
/*     */       {
/* 328 */         var4[EnumFaceing.Constants.field_179176_f] = var18;
/*     */       }
/*     */       
/* 331 */       if (var19 < var4[EnumFaceing.Constants.field_179178_e])
/*     */       {
/* 333 */         var4[EnumFaceing.Constants.field_179178_e] = var19;
/*     */       }
/*     */       
/* 336 */       if (var9 < var4[EnumFaceing.Constants.field_179177_d])
/*     */       {
/* 338 */         var4[EnumFaceing.Constants.field_179177_d] = var9;
/*     */       }
/*     */       
/* 341 */       if (var18 > var4[EnumFaceing.Constants.field_179180_c])
/*     */       {
/* 343 */         var4[EnumFaceing.Constants.field_179180_c] = var18;
/*     */       }
/*     */       
/* 346 */       if (var19 > var4[EnumFaceing.Constants.field_179179_b])
/*     */       {
/* 348 */         var4[EnumFaceing.Constants.field_179179_b] = var19;
/*     */       }
/*     */       
/* 351 */       if (var9 > var4[EnumFaceing.Constants.field_179181_a])
/*     */       {
/* 353 */         var4[EnumFaceing.Constants.field_179181_a] = var9;
/*     */       }
/*     */     } 
/*     */     
/* 357 */     EnumFaceing var181 = EnumFaceing.func_179027_a(p_178408_2_);
/*     */     
/* 359 */     for (int var6 = 0; var6 < 4; var6++) {
/*     */       
/* 361 */       int var191 = step * var6;
/* 362 */       EnumFaceing.VertexInformation var20 = var181.func_179025_a(var6);
/* 363 */       float var9 = var4[var20.field_179184_a];
/* 364 */       float var10 = var4[var20.field_179182_b];
/* 365 */       float var11 = var4[var20.field_179183_c];
/* 366 */       p_178408_1_[var191] = Float.floatToRawIntBits(var9);
/* 367 */       p_178408_1_[var191 + 1] = Float.floatToRawIntBits(var10);
/* 368 */       p_178408_1_[var191 + 2] = Float.floatToRawIntBits(var11);
/*     */       
/* 370 */       for (int var12 = 0; var12 < 4; var12++) {
/*     */         
/* 372 */         int var13 = step * var12;
/* 373 */         float var14 = Float.intBitsToFloat(var3[var13]);
/* 374 */         float var15 = Float.intBitsToFloat(var3[var13 + 1]);
/* 375 */         float var16 = Float.intBitsToFloat(var3[var13 + 2]);
/*     */         
/* 377 */         if (MathHelper.func_180185_a(var9, var14) && MathHelper.func_180185_a(var10, var15) && MathHelper.func_180185_a(var11, var16)) {
/*     */           
/* 379 */           p_178408_1_[var191 + 4] = var3[var13 + 4];
/* 380 */           p_178408_1_[var191 + 4 + 1] = var3[var13 + 4 + 1];
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178401_a(int p_178401_1_, int[] p_178401_2_, EnumFacing p_178401_3_, BlockFaceUV p_178401_4_, TextureAtlasSprite p_178401_5_) {
/* 388 */     int step = p_178401_2_.length / 4;
/* 389 */     int var6 = step * p_178401_1_;
/* 390 */     float var7 = Float.intBitsToFloat(p_178401_2_[var6]);
/* 391 */     float var8 = Float.intBitsToFloat(p_178401_2_[var6 + 1]);
/* 392 */     float var9 = Float.intBitsToFloat(p_178401_2_[var6 + 2]);
/*     */     
/* 394 */     if (var7 < -0.1F || var7 >= 1.1F)
/*     */     {
/* 396 */       var7 -= MathHelper.floor_float(var7);
/*     */     }
/*     */     
/* 399 */     if (var8 < -0.1F || var8 >= 1.1F)
/*     */     {
/* 401 */       var8 -= MathHelper.floor_float(var8);
/*     */     }
/*     */     
/* 404 */     if (var9 < -0.1F || var9 >= 1.1F)
/*     */     {
/* 406 */       var9 -= MathHelper.floor_float(var9);
/*     */     }
/*     */     
/* 409 */     float var10 = 0.0F;
/* 410 */     float var11 = 0.0F;
/*     */     
/* 412 */     switch (SwitchEnumFacing.field_178400_a[p_178401_3_.ordinal()]) {
/*     */       
/*     */       case 1:
/* 415 */         var10 = var7 * 16.0F;
/* 416 */         var11 = (1.0F - var9) * 16.0F;
/*     */         break;
/*     */       
/*     */       case 2:
/* 420 */         var10 = var7 * 16.0F;
/* 421 */         var11 = var9 * 16.0F;
/*     */         break;
/*     */       
/*     */       case 3:
/* 425 */         var10 = (1.0F - var7) * 16.0F;
/* 426 */         var11 = (1.0F - var8) * 16.0F;
/*     */         break;
/*     */       
/*     */       case 4:
/* 430 */         var10 = var7 * 16.0F;
/* 431 */         var11 = (1.0F - var8) * 16.0F;
/*     */         break;
/*     */       
/*     */       case 5:
/* 435 */         var10 = var9 * 16.0F;
/* 436 */         var11 = (1.0F - var8) * 16.0F;
/*     */         break;
/*     */       
/*     */       case 6:
/* 440 */         var10 = (1.0F - var9) * 16.0F;
/* 441 */         var11 = (1.0F - var8) * 16.0F;
/*     */         break;
/*     */     } 
/* 444 */     int var12 = p_178401_4_.func_178345_c(p_178401_1_) * step;
/* 445 */     p_178401_2_[var12 + 4] = Float.floatToRawIntBits(p_178401_5_.getInterpolatedU(var10));
/* 446 */     p_178401_2_[var12 + 4 + 1] = Float.floatToRawIntBits(p_178401_5_.getInterpolatedV(var11));
/*     */   }
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
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 484 */     static final int[] field_178400_a = new int[(EnumFacing.values()).length]; static final int[] field_178399_b = new int[(EnumFacing.Axis.values()).length]; private static final String __OBFID = "CL_00002489";
/*     */     
/*     */     static {
/*     */       try {
/* 488 */         field_178400_a[EnumFacing.DOWN.ordinal()] = 1;
/*     */       }
/* 490 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 497 */         field_178400_a[EnumFacing.UP.ordinal()] = 2;
/*     */       }
/* 499 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 506 */         field_178400_a[EnumFacing.NORTH.ordinal()] = 3;
/*     */       }
/* 508 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 515 */         field_178400_a[EnumFacing.SOUTH.ordinal()] = 4;
/*     */       }
/* 517 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 524 */         field_178400_a[EnumFacing.WEST.ordinal()] = 5;
/*     */       }
/* 526 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 533 */         field_178400_a[EnumFacing.EAST.ordinal()] = 6;
/*     */       }
/* 535 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\FaceBakery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */