/*     */ package optifine;
/*     */ 
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ public class NaturalProperties
/*     */ {
/*  12 */   public int rotation = 1;
/*     */   public boolean flip = false;
/*  14 */   private Map[] quadMaps = new Map[8];
/*     */ 
/*     */   
/*     */   public NaturalProperties(String type) {
/*  18 */     if (type.equals("4")) {
/*     */       
/*  20 */       this.rotation = 4;
/*     */     }
/*  22 */     else if (type.equals("2")) {
/*     */       
/*  24 */       this.rotation = 2;
/*     */     }
/*  26 */     else if (type.equals("F")) {
/*     */       
/*  28 */       this.flip = true;
/*     */     }
/*  30 */     else if (type.equals("4F")) {
/*     */       
/*  32 */       this.rotation = 4;
/*  33 */       this.flip = true;
/*     */     }
/*  35 */     else if (type.equals("2F")) {
/*     */       
/*  37 */       this.rotation = 2;
/*  38 */       this.flip = true;
/*     */     }
/*     */     else {
/*     */       
/*  42 */       Config.warn("NaturalTextures: Unknown type: " + type);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/*  48 */     return (this.rotation != 2 && this.rotation != 4) ? this.flip : true;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized BakedQuad getQuad(BakedQuad quadIn, int rotate, boolean flipU) {
/*  53 */     int index = rotate;
/*     */     
/*  55 */     if (flipU)
/*     */     {
/*  57 */       index = rotate | 0x4;
/*     */     }
/*     */     
/*  60 */     if (index > 0 && index < this.quadMaps.length) {
/*     */       
/*  62 */       Object<Object, Object> map = (Object<Object, Object>)this.quadMaps[index];
/*     */       
/*  64 */       if (map == null) {
/*     */         
/*  66 */         map = (Object<Object, Object>)new IdentityHashMap<>(1);
/*  67 */         this.quadMaps[index] = (Map)map;
/*     */       } 
/*     */       
/*  70 */       BakedQuad quad = (BakedQuad)((Map)map).get(quadIn);
/*     */       
/*  72 */       if (quad == null) {
/*     */         
/*  74 */         quad = makeQuad(quadIn, rotate, flipU);
/*  75 */         ((Map)map).put(quadIn, quad);
/*     */       } 
/*     */       
/*  78 */       return quad;
/*     */     } 
/*     */ 
/*     */     
/*  82 */     return quadIn;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BakedQuad makeQuad(BakedQuad quad, int rotate, boolean flipU) {
/*  88 */     int[] vertexData = quad.func_178209_a();
/*  89 */     int tintIndex = quad.func_178211_c();
/*  90 */     EnumFacing face = quad.getFace();
/*  91 */     TextureAtlasSprite sprite = quad.getSprite();
/*     */     
/*  93 */     if (!isFullSprite(quad))
/*     */     {
/*  95 */       return quad;
/*     */     }
/*     */ 
/*     */     
/*  99 */     vertexData = transformVertexData(vertexData, rotate, flipU);
/* 100 */     BakedQuad bq = new BakedQuad(vertexData, tintIndex, face, sprite);
/* 101 */     return bq;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] transformVertexData(int[] vertexData, int rotate, boolean flipU) {
/* 107 */     int[] vertexData2 = (int[])vertexData.clone();
/* 108 */     int v2 = 4 - rotate;
/*     */     
/* 110 */     if (flipU)
/*     */     {
/* 112 */       v2 += 3;
/*     */     }
/*     */     
/* 115 */     v2 %= 4;
/* 116 */     int step = vertexData2.length / 4;
/*     */     
/* 118 */     for (int v = 0; v < 4; v++) {
/*     */       
/* 120 */       int pos = v * step;
/* 121 */       int pos2 = v2 * step;
/* 122 */       vertexData2[pos2 + 4] = vertexData[pos + 4];
/* 123 */       vertexData2[pos2 + 4 + 1] = vertexData[pos + 4 + 1];
/*     */       
/* 125 */       if (flipU) {
/*     */         
/* 127 */         v2--;
/*     */         
/* 129 */         if (v2 < 0)
/*     */         {
/* 131 */           v2 = 3;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 136 */         v2++;
/*     */         
/* 138 */         if (v2 > 3)
/*     */         {
/* 140 */           v2 = 0;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     return vertexData2;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isFullSprite(BakedQuad quad) {
/* 150 */     TextureAtlasSprite sprite = quad.getSprite();
/* 151 */     float uMin = sprite.getMinU();
/* 152 */     float uMax = sprite.getMaxU();
/* 153 */     float uSize = uMax - uMin;
/* 154 */     float uDelta = uSize / 256.0F;
/* 155 */     float vMin = sprite.getMinV();
/* 156 */     float vMax = sprite.getMaxV();
/* 157 */     float vSize = vMax - vMin;
/* 158 */     float vDelta = vSize / 256.0F;
/* 159 */     int[] vertexData = quad.func_178209_a();
/* 160 */     int step = vertexData.length / 4;
/*     */     
/* 162 */     for (int i = 0; i < 4; i++) {
/*     */       
/* 164 */       int pos = i * step;
/* 165 */       float u = Float.intBitsToFloat(vertexData[pos + 4]);
/* 166 */       float v = Float.intBitsToFloat(vertexData[pos + 4 + 1]);
/*     */       
/* 168 */       if (!equalsDelta(u, uMin, uDelta) && !equalsDelta(u, uMax, uDelta))
/*     */       {
/* 170 */         return false;
/*     */       }
/*     */       
/* 173 */       if (!equalsDelta(v, vMin, vDelta) && !equalsDelta(v, vMax, vDelta))
/*     */       {
/* 175 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 179 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean equalsDelta(float x1, float x2, float deltaMax) {
/* 184 */     float deltaAbs = MathHelper.abs(x1 - x2);
/* 185 */     return (deltaAbs < deltaMax);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\NaturalProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */