/*     */ package shadersmod.client;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL20;
/*     */ 
/*     */ public class SVertexBuilder
/*     */ {
/*     */   int vertexSize;
/*     */   int offsetNormal;
/*     */   int offsetUV;
/*     */   int offsetUVCenter;
/*     */   boolean hasNormal;
/*     */   boolean hasTangent;
/*     */   boolean hasUV;
/*     */   boolean hasUVCenter;
/*  25 */   long[] entityData = new long[10];
/*  26 */   int entityDataIndex = 0;
/*     */ 
/*     */   
/*     */   public SVertexBuilder() {
/*  30 */     this.entityData[this.entityDataIndex] = 0L;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void initVertexBuilder(WorldRenderer wrr) {
/*  35 */     wrr.sVertexBuilder = new SVertexBuilder();
/*     */   }
/*     */ 
/*     */   
/*     */   public void pushEntity(long data) {
/*  40 */     this.entityDataIndex++;
/*  41 */     this.entityData[this.entityDataIndex] = data;
/*     */   }
/*     */ 
/*     */   
/*     */   public void popEntity() {
/*  46 */     this.entityData[this.entityDataIndex] = 0L;
/*  47 */     this.entityDataIndex--;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void pushEntity(IBlockState blockState, BlockPos blockPos, IBlockAccess blockAccess, WorldRenderer wrr) {
/*  52 */     Block block = blockState.getBlock();
/*  53 */     int blockID = Block.getIdFromBlock(block);
/*  54 */     int renderType = block.getRenderType();
/*  55 */     int meta = block.getMetaFromState(blockState);
/*  56 */     int dataLo = ((renderType & 0xFFFF) << 16) + (blockID & 0xFFFF);
/*  57 */     int dataHi = meta & 0xFFFF;
/*  58 */     wrr.sVertexBuilder.pushEntity((dataHi << 32L) + dataLo);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void popEntity(WorldRenderer wrr) {
/*  63 */     wrr.sVertexBuilder.popEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean popEntity(boolean value, WorldRenderer wrr) {
/*  68 */     wrr.sVertexBuilder.popEntity();
/*  69 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void endSetVertexFormat(WorldRenderer wrr) {
/*  74 */     SVertexBuilder svb = wrr.sVertexBuilder;
/*  75 */     VertexFormat vf = wrr.func_178973_g();
/*  76 */     svb.vertexSize = vf.func_177338_f() / 4;
/*  77 */     svb.hasNormal = vf.func_177350_b();
/*  78 */     svb.hasTangent = svb.hasNormal;
/*  79 */     svb.hasUV = vf.func_177347_a(0);
/*  80 */     svb.offsetNormal = svb.hasNormal ? (vf.func_177342_c() / 4) : 0;
/*  81 */     svb.offsetUV = svb.hasUV ? (vf.func_177344_b(0) / 4) : 0;
/*  82 */     svb.offsetUVCenter = 8;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void beginAddVertex(WorldRenderer wrr) {
/*  87 */     if (wrr.vertexCount == 0)
/*     */     {
/*  89 */       endSetVertexFormat(wrr);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void endAddVertex(WorldRenderer wrr) {
/*  95 */     SVertexBuilder svb = wrr.sVertexBuilder;
/*     */     
/*  97 */     if (svb.vertexSize == 14) {
/*     */       
/*  99 */       if (wrr.drawMode == 7 && wrr.vertexCount % 4 == 0)
/*     */       {
/* 101 */         svb.calcNormal(wrr, wrr.rawBufferIndex - 4 * svb.vertexSize);
/*     */       }
/*     */       
/* 104 */       long eData = svb.entityData[svb.entityDataIndex];
/* 105 */       int pos = wrr.rawBufferIndex - 14 + 12;
/* 106 */       wrr.rawIntBuffer.put(pos, (int)eData);
/* 107 */       wrr.rawIntBuffer.put(pos + 1, (int)(eData >> 32L));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void beginAddVertexData(WorldRenderer wrr, int[] data) {
/* 113 */     if (wrr.vertexCount == 0)
/*     */     {
/* 115 */       endSetVertexFormat(wrr);
/*     */     }
/*     */     
/* 118 */     SVertexBuilder svb = wrr.sVertexBuilder;
/*     */     
/* 120 */     if (svb.vertexSize == 14) {
/*     */       
/* 122 */       long eData = svb.entityData[svb.entityDataIndex];
/*     */       
/* 124 */       for (int pos = 12; pos + 1 < data.length; pos += 14) {
/*     */         
/* 126 */         data[pos] = (int)eData;
/* 127 */         data[pos + 1] = (int)(eData >> 32L);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void endAddVertexData(WorldRenderer wrr) {
/* 134 */     SVertexBuilder svb = wrr.sVertexBuilder;
/*     */     
/* 136 */     if (svb.vertexSize == 14 && wrr.drawMode == 7 && wrr.vertexCount % 4 == 0)
/*     */     {
/* 138 */       svb.calcNormal(wrr, wrr.rawBufferIndex - 4 * svb.vertexSize);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void calcNormal(WorldRenderer wrr, int baseIndex) {
/* 144 */     FloatBuffer floatBuffer = wrr.rawFloatBuffer;
/* 145 */     IntBuffer intBuffer = wrr.rawIntBuffer;
/* 146 */     int rbi = wrr.rawBufferIndex;
/* 147 */     float v0x = floatBuffer.get(baseIndex + 0 * this.vertexSize);
/* 148 */     float v0y = floatBuffer.get(baseIndex + 0 * this.vertexSize + 1);
/* 149 */     float v0z = floatBuffer.get(baseIndex + 0 * this.vertexSize + 2);
/* 150 */     float v0u = floatBuffer.get(baseIndex + 0 * this.vertexSize + this.offsetUV);
/* 151 */     float v0v = floatBuffer.get(baseIndex + 0 * this.vertexSize + this.offsetUV + 1);
/* 152 */     float v1x = floatBuffer.get(baseIndex + 1 * this.vertexSize);
/* 153 */     float v1y = floatBuffer.get(baseIndex + 1 * this.vertexSize + 1);
/* 154 */     float v1z = floatBuffer.get(baseIndex + 1 * this.vertexSize + 2);
/* 155 */     float v1u = floatBuffer.get(baseIndex + 1 * this.vertexSize + this.offsetUV);
/* 156 */     float v1v = floatBuffer.get(baseIndex + 1 * this.vertexSize + this.offsetUV + 1);
/* 157 */     float v2x = floatBuffer.get(baseIndex + 2 * this.vertexSize);
/* 158 */     float v2y = floatBuffer.get(baseIndex + 2 * this.vertexSize + 1);
/* 159 */     float v2z = floatBuffer.get(baseIndex + 2 * this.vertexSize + 2);
/* 160 */     float v2u = floatBuffer.get(baseIndex + 2 * this.vertexSize + this.offsetUV);
/* 161 */     float v2v = floatBuffer.get(baseIndex + 2 * this.vertexSize + this.offsetUV + 1);
/* 162 */     float v3x = floatBuffer.get(baseIndex + 3 * this.vertexSize);
/* 163 */     float v3y = floatBuffer.get(baseIndex + 3 * this.vertexSize + 1);
/* 164 */     float v3z = floatBuffer.get(baseIndex + 3 * this.vertexSize + 2);
/* 165 */     float v3u = floatBuffer.get(baseIndex + 3 * this.vertexSize + this.offsetUV);
/* 166 */     float v3v = floatBuffer.get(baseIndex + 3 * this.vertexSize + this.offsetUV + 1);
/* 167 */     float x1 = v2x - v0x;
/* 168 */     float y1 = v2y - v0y;
/* 169 */     float z1 = v2z - v0z;
/* 170 */     float x2 = v3x - v1x;
/* 171 */     float y2 = v3y - v1y;
/* 172 */     float z2 = v3z - v1z;
/* 173 */     float vnx = y1 * z2 - y2 * z1;
/* 174 */     float vny = z1 * x2 - z2 * x1;
/* 175 */     float vnz = x1 * y2 - x2 * y1;
/* 176 */     float lensq = vnx * vnx + vny * vny + vnz * vnz;
/* 177 */     float mult = (lensq != 0.0D) ? (float)(1.0D / Math.sqrt(lensq)) : 1.0F;
/* 178 */     vnx *= mult;
/* 179 */     vny *= mult;
/* 180 */     vnz *= mult;
/* 181 */     x1 = v1x - v0x;
/* 182 */     y1 = v1y - v0y;
/* 183 */     z1 = v1z - v0z;
/* 184 */     float u1 = v1u - v0u;
/* 185 */     float v1 = v1v - v0v;
/* 186 */     x2 = v2x - v0x;
/* 187 */     y2 = v2y - v0y;
/* 188 */     z2 = v2z - v0z;
/* 189 */     float u2 = v2u - v0u;
/* 190 */     float v2 = v2v - v0v;
/* 191 */     float d = u1 * v2 - u2 * v1;
/* 192 */     float r = (d != 0.0F) ? (1.0F / d) : 1.0F;
/* 193 */     float tan1x = (v2 * x1 - v1 * x2) * r;
/* 194 */     float tan1y = (v2 * y1 - v1 * y2) * r;
/* 195 */     float tan1z = (v2 * z1 - v1 * z2) * r;
/* 196 */     float tan2x = (u1 * x2 - u2 * x1) * r;
/* 197 */     float tan2y = (u1 * y2 - u2 * y1) * r;
/* 198 */     float tan2z = (u1 * z2 - u2 * z1) * r;
/* 199 */     lensq = tan1x * tan1x + tan1y * tan1y + tan1z * tan1z;
/* 200 */     mult = (lensq != 0.0D) ? (float)(1.0D / Math.sqrt(lensq)) : 1.0F;
/* 201 */     tan1x *= mult;
/* 202 */     tan1y *= mult;
/* 203 */     tan1z *= mult;
/* 204 */     lensq = tan2x * tan2x + tan2y * tan2y + tan2z * tan2z;
/* 205 */     mult = (lensq != 0.0D) ? (float)(1.0D / Math.sqrt(lensq)) : 1.0F;
/* 206 */     tan2x *= mult;
/* 207 */     tan2y *= mult;
/* 208 */     tan2z *= mult;
/* 209 */     float tan3x = vnz * tan1y - vny * tan1z;
/* 210 */     float tan3y = vnx * tan1z - vnz * tan1x;
/* 211 */     float tan3z = vny * tan1x - vnx * tan1y;
/* 212 */     float tan1w = (tan2x * tan3x + tan2y * tan3y + tan2z * tan3z < 0.0F) ? -1.0F : 1.0F;
/* 213 */     int bnx = (int)(vnx * 127.0F) & 0xFF;
/* 214 */     int bny = (int)(vny * 127.0F) & 0xFF;
/* 215 */     int bnz = (int)(vnz * 127.0F) & 0xFF;
/* 216 */     int packedNormal = (bnz << 16) + (bny << 8) + bnx;
/* 217 */     intBuffer.put(baseIndex + 0 * this.vertexSize + this.offsetNormal, packedNormal);
/* 218 */     intBuffer.put(baseIndex + 1 * this.vertexSize + this.offsetNormal, packedNormal);
/* 219 */     intBuffer.put(baseIndex + 2 * this.vertexSize + this.offsetNormal, packedNormal);
/* 220 */     intBuffer.put(baseIndex + 3 * this.vertexSize + this.offsetNormal, packedNormal);
/* 221 */     int packedTan1xy = ((int)(tan1x * 32767.0F) & 0xFFFF) + (((int)(tan1y * 32767.0F) & 0xFFFF) << 16);
/* 222 */     int packedTan1zw = ((int)(tan1z * 32767.0F) & 0xFFFF) + (((int)(tan1w * 32767.0F) & 0xFFFF) << 16);
/* 223 */     intBuffer.put(baseIndex + 0 * this.vertexSize + 10, packedTan1xy);
/* 224 */     intBuffer.put(baseIndex + 0 * this.vertexSize + 10 + 1, packedTan1zw);
/* 225 */     intBuffer.put(baseIndex + 1 * this.vertexSize + 10, packedTan1xy);
/* 226 */     intBuffer.put(baseIndex + 1 * this.vertexSize + 10 + 1, packedTan1zw);
/* 227 */     intBuffer.put(baseIndex + 2 * this.vertexSize + 10, packedTan1xy);
/* 228 */     intBuffer.put(baseIndex + 2 * this.vertexSize + 10 + 1, packedTan1zw);
/* 229 */     intBuffer.put(baseIndex + 3 * this.vertexSize + 10, packedTan1xy);
/* 230 */     intBuffer.put(baseIndex + 3 * this.vertexSize + 10 + 1, packedTan1zw);
/* 231 */     float midU = (v0u + v1u + v2u + v3u) / 4.0F;
/* 232 */     float midV = (v0v + v1v + v2v + v3v) / 4.0F;
/* 233 */     floatBuffer.put(baseIndex + 0 * this.vertexSize + 8, midU);
/* 234 */     floatBuffer.put(baseIndex + 0 * this.vertexSize + 8 + 1, midV);
/* 235 */     floatBuffer.put(baseIndex + 1 * this.vertexSize + 8, midU);
/* 236 */     floatBuffer.put(baseIndex + 1 * this.vertexSize + 8 + 1, midV);
/* 237 */     floatBuffer.put(baseIndex + 2 * this.vertexSize + 8, midU);
/* 238 */     floatBuffer.put(baseIndex + 2 * this.vertexSize + 8 + 1, midV);
/* 239 */     floatBuffer.put(baseIndex + 3 * this.vertexSize + 8, midU);
/* 240 */     floatBuffer.put(baseIndex + 3 * this.vertexSize + 8 + 1, midV);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void calcNormalChunkLayer(WorldRenderer wrr) {
/* 245 */     if (wrr.func_178973_g().func_177350_b() && wrr.drawMode == 7 && wrr.vertexCount % 4 == 0) {
/*     */       
/* 247 */       SVertexBuilder svb = wrr.sVertexBuilder;
/* 248 */       endSetVertexFormat(wrr);
/* 249 */       int indexEnd = wrr.vertexCount * svb.vertexSize;
/*     */       
/* 251 */       for (int index = 0; index < indexEnd; index += svb.vertexSize * 4)
/*     */       {
/* 253 */         svb.calcNormal(wrr, index);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawArrays(int drawMode, int first, int count, WorldRenderer wrr) {
/* 260 */     if (count != 0) {
/*     */       
/* 262 */       VertexFormat vf = wrr.func_178973_g();
/* 263 */       int vertexSizeByte = vf.func_177338_f();
/*     */       
/* 265 */       if (vertexSizeByte == 56) {
/*     */         
/* 267 */         ByteBuffer bb = wrr.func_178966_f();
/* 268 */         bb.position(32);
/* 269 */         GL20.glVertexAttribPointer(Shaders.midTexCoordAttrib, 2, 5126, false, vertexSizeByte, bb);
/* 270 */         bb.position(40);
/* 271 */         GL20.glVertexAttribPointer(Shaders.tangentAttrib, 4, 5122, false, vertexSizeByte, bb);
/* 272 */         bb.position(48);
/* 273 */         GL20.glVertexAttribPointer(Shaders.entityAttrib, 3, 5122, false, vertexSizeByte, bb);
/* 274 */         bb.position(0);
/* 275 */         GL20.glEnableVertexAttribArray(Shaders.midTexCoordAttrib);
/* 276 */         GL20.glEnableVertexAttribArray(Shaders.tangentAttrib);
/* 277 */         GL20.glEnableVertexAttribArray(Shaders.entityAttrib);
/* 278 */         GL11.glDrawArrays(drawMode, first, count);
/* 279 */         GL20.glDisableVertexAttribArray(Shaders.midTexCoordAttrib);
/* 280 */         GL20.glDisableVertexAttribArray(Shaders.tangentAttrib);
/* 281 */         GL20.glDisableVertexAttribArray(Shaders.entityAttrib);
/*     */       }
/*     */       else {
/*     */         
/* 285 */         GL11.glDrawArrays(drawMode, first, count);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void startTexturedQuad(WorldRenderer wrr) {
/* 292 */     wrr.setVertexFormat(SVertexFormat.defVertexFormatTextured);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\SVertexBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */