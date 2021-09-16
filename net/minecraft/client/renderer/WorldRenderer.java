/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.PriorityQueue;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*     */ import net.minecraft.client.util.QuadComparator;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import optifine.Config;
/*     */ import optifine.TextureUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import shadersmod.client.SVertexBuilder;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldRenderer
/*     */ {
/*     */   private ByteBuffer byteBuffer;
/*     */   public IntBuffer rawIntBuffer;
/*     */   public FloatBuffer rawFloatBuffer;
/*     */   public int vertexCount;
/*     */   private double field_178998_e;
/*     */   private double field_178995_f;
/*     */   private int field_178996_g;
/*     */   private int field_179007_h;
/*     */   public int rawBufferIndex;
/*     */   private boolean needsUpdate;
/*     */   public int drawMode;
/*     */   private double xOffset;
/*     */   private double yOffset;
/*     */   private double zOffset;
/*     */   private int field_179003_o;
/*     */   private int field_179012_p;
/*     */   private VertexFormat vertexFormat;
/*     */   private boolean isDrawing;
/*     */   private int bufferSize;
/*     */   private static final String __OBFID = "CL_00000942";
/*  47 */   private EnumWorldBlockLayer blockLayer = null;
/*  48 */   private boolean[] drawnIcons = new boolean[256];
/*  49 */   private TextureAtlasSprite[] quadSprites = null;
/*  50 */   private TextureAtlasSprite[] quadSpritesPrev = null;
/*  51 */   private TextureAtlasSprite quadSprite = null;
/*     */   
/*     */   public SVertexBuilder sVertexBuilder;
/*     */   
/*     */   public WorldRenderer(int p_i46275_1_) {
/*  56 */     if (Config.isShaders())
/*     */     {
/*  58 */       p_i46275_1_ *= 2;
/*     */     }
/*     */     
/*  61 */     this.bufferSize = p_i46275_1_;
/*  62 */     this.byteBuffer = GLAllocation.createDirectByteBuffer(p_i46275_1_ * 4);
/*  63 */     this.rawIntBuffer = this.byteBuffer.asIntBuffer();
/*  64 */     this.rawFloatBuffer = this.byteBuffer.asFloatBuffer();
/*  65 */     this.vertexFormat = new VertexFormat();
/*  66 */     this.vertexFormat.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.POSITION, 3));
/*  67 */     SVertexBuilder.initVertexBuilder(this);
/*     */   }
/*     */ 
/*     */   
/*     */   private void growBuffer(int p_178983_1_) {
/*  72 */     if (Config.isShaders())
/*     */     {
/*  74 */       p_178983_1_ *= 2;
/*     */     }
/*     */     
/*  77 */     LogManager.getLogger().warn("Needed to grow BufferBuilder buffer: Old size " + (this.bufferSize * 4) + " bytes, new size " + (this.bufferSize * 4 + p_178983_1_) + " bytes.");
/*  78 */     this.bufferSize += p_178983_1_ / 4;
/*  79 */     ByteBuffer var2 = GLAllocation.createDirectByteBuffer(this.bufferSize * 4);
/*  80 */     this.rawIntBuffer.position(0);
/*  81 */     var2.asIntBuffer().put(this.rawIntBuffer);
/*  82 */     this.byteBuffer = var2;
/*  83 */     this.rawIntBuffer = this.byteBuffer.asIntBuffer();
/*  84 */     this.rawFloatBuffer = this.byteBuffer.asFloatBuffer();
/*     */     
/*  86 */     if (this.quadSprites != null) {
/*     */       
/*  88 */       TextureAtlasSprite[] sprites = this.quadSprites;
/*  89 */       int quadSize = getBufferQuadSize();
/*  90 */       this.quadSprites = new TextureAtlasSprite[quadSize];
/*  91 */       System.arraycopy(sprites, 0, this.quadSprites, 0, Math.min(sprites.length, this.quadSprites.length));
/*  92 */       this.quadSpritesPrev = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public State getVertexState(float p_178971_1_, float p_178971_2_, float p_178971_3_) {
/*  98 */     int[] var4 = new int[this.rawBufferIndex];
/*  99 */     PriorityQueue<Integer> var5 = new PriorityQueue(this.rawBufferIndex, (Comparator<?>)new QuadComparator(this.rawFloatBuffer, (float)(p_178971_1_ + this.xOffset), (float)(p_178971_2_ + this.yOffset), (float)(p_178971_3_ + this.zOffset), this.vertexFormat.func_177338_f() / 4));
/* 100 */     int var6 = this.vertexFormat.func_177338_f();
/* 101 */     TextureAtlasSprite[] quadSpritesSorted = null;
/* 102 */     int quadStep = this.vertexFormat.func_177338_f() / 4 * 4;
/*     */     
/* 104 */     if (this.quadSprites != null)
/*     */     {
/* 106 */       quadSpritesSorted = new TextureAtlasSprite[this.vertexCount / 4];
/*     */     }
/*     */     
/*     */     int var7;
/*     */     
/* 111 */     for (var7 = 0; var7 < this.rawBufferIndex; var7 += var6)
/*     */     {
/* 113 */       var5.add(Integer.valueOf(var7));
/*     */     }
/*     */     
/* 116 */     for (var7 = 0; !var5.isEmpty(); var7 += var6) {
/*     */       
/* 118 */       int var8 = ((Integer)var5.remove()).intValue();
/*     */       
/*     */       int indexQuad;
/* 121 */       for (indexQuad = 0; indexQuad < var6; indexQuad++)
/*     */       {
/* 123 */         var4[var7 + indexQuad] = this.rawIntBuffer.get(var8 + indexQuad);
/*     */       }
/*     */       
/* 126 */       if (quadSpritesSorted != null) {
/*     */         
/* 128 */         indexQuad = var8 / quadStep;
/* 129 */         int indexQuadSorted = var7 / quadStep;
/* 130 */         quadSpritesSorted[indexQuadSorted] = this.quadSprites[indexQuad];
/*     */       } 
/*     */     } 
/*     */     
/* 134 */     this.rawIntBuffer.clear();
/* 135 */     this.rawIntBuffer.put(var4);
/*     */     
/* 137 */     if (this.quadSprites != null)
/*     */     {
/* 139 */       System.arraycopy(quadSpritesSorted, 0, this.quadSprites, 0, quadSpritesSorted.length);
/*     */     }
/*     */     
/* 142 */     return new State(var4, this.rawBufferIndex, this.vertexCount, new VertexFormat(this.vertexFormat), quadSpritesSorted);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVertexState(State p_178993_1_) {
/* 147 */     if ((p_178993_1_.func_179013_a()).length > this.rawIntBuffer.capacity())
/*     */     {
/* 149 */       growBuffer(2097152);
/*     */     }
/*     */     
/* 152 */     this.rawIntBuffer.clear();
/* 153 */     this.rawIntBuffer.put(p_178993_1_.func_179013_a());
/* 154 */     this.rawBufferIndex = p_178993_1_.getRawBufferIndex();
/* 155 */     this.vertexCount = p_178993_1_.getVertexCount();
/* 156 */     this.vertexFormat = new VertexFormat(p_178993_1_.func_179016_d());
/*     */     
/* 158 */     if (p_178993_1_.stateQuadSprites != null) {
/*     */       
/* 160 */       if (this.quadSprites == null)
/*     */       {
/* 162 */         this.quadSprites = this.quadSpritesPrev;
/*     */       }
/*     */       
/* 165 */       if (this.quadSprites == null || this.quadSprites.length < getBufferQuadSize())
/*     */       {
/* 167 */         this.quadSprites = new TextureAtlasSprite[getBufferQuadSize()];
/*     */       }
/*     */       
/* 170 */       System.arraycopy(p_178993_1_.stateQuadSprites, 0, this.quadSprites, 0, p_178993_1_.stateQuadSprites.length);
/*     */     }
/*     */     else {
/*     */       
/* 174 */       if (this.quadSprites != null)
/*     */       {
/* 176 */         this.quadSpritesPrev = this.quadSprites;
/*     */       }
/*     */       
/* 179 */       this.quadSprites = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 185 */     this.vertexCount = 0;
/* 186 */     this.rawBufferIndex = 0;
/* 187 */     this.vertexFormat.clear();
/* 188 */     this.vertexFormat.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.POSITION, 3));
/*     */     
/* 190 */     if (this.blockLayer != null && Config.isMultiTexture()) {
/*     */       
/* 192 */       if (this.quadSprites == null)
/*     */       {
/* 194 */         this.quadSprites = this.quadSpritesPrev;
/*     */       }
/*     */       
/* 197 */       if (this.quadSprites == null || this.quadSprites.length < getBufferQuadSize())
/*     */       {
/* 199 */         this.quadSprites = new TextureAtlasSprite[getBufferQuadSize()];
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 204 */       if (this.quadSprites != null)
/*     */       {
/* 206 */         this.quadSpritesPrev = this.quadSprites;
/*     */       }
/*     */       
/* 209 */       this.quadSprites = null;
/*     */     } 
/*     */     
/* 212 */     this.quadSprite = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void startDrawingQuads() {
/* 217 */     startDrawing(7);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startDrawing(int p_178964_1_) {
/* 222 */     if (this.isDrawing)
/*     */     {
/* 224 */       throw new IllegalStateException("Already building!");
/*     */     }
/*     */ 
/*     */     
/* 228 */     this.isDrawing = true;
/* 229 */     reset();
/* 230 */     this.drawMode = p_178964_1_;
/* 231 */     this.needsUpdate = false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextureUV(double p_178992_1_, double p_178992_3_) {
/* 237 */     if (!this.vertexFormat.func_177347_a(0) && !this.vertexFormat.func_177347_a(1)) {
/*     */       
/* 239 */       VertexFormatElement var5 = new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.UV, 2);
/* 240 */       this.vertexFormat.func_177349_a(var5);
/*     */     } 
/*     */     
/* 243 */     this.field_178998_e = p_178992_1_;
/* 244 */     this.field_178995_f = p_178992_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178963_b(int p_178963_1_) {
/* 249 */     if (!this.vertexFormat.func_177347_a(1)) {
/*     */       
/* 251 */       if (!this.vertexFormat.func_177347_a(0))
/*     */       {
/* 253 */         this.vertexFormat.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.UV, 2));
/*     */       }
/*     */       
/* 256 */       VertexFormatElement var2 = new VertexFormatElement(1, VertexFormatElement.EnumType.SHORT, VertexFormatElement.EnumUseage.UV, 2);
/* 257 */       this.vertexFormat.func_177349_a(var2);
/*     */     } 
/*     */     
/* 260 */     this.field_178996_g = p_178963_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178986_b(float p_178986_1_, float p_178986_2_, float p_178986_3_) {
/* 265 */     setPosition((int)(p_178986_1_ * 255.0F), (int)(p_178986_2_ * 255.0F), (int)(p_178986_3_ * 255.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178960_a(float p_178960_1_, float p_178960_2_, float p_178960_3_, float p_178960_4_) {
/* 270 */     func_178961_b((int)(p_178960_1_ * 255.0F), (int)(p_178960_2_ * 255.0F), (int)(p_178960_3_ * 255.0F), (int)(p_178960_4_ * 255.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(int p_78913_1_, int p_78913_2_, int p_78913_3_) {
/* 278 */     func_178961_b(p_78913_1_, p_78913_2_, p_78913_3_, 255);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178962_a(int p_178962_1_, int p_178962_2_, int p_178962_3_, int p_178962_4_) {
/* 283 */     int var5 = (this.vertexCount - 4) * this.vertexFormat.func_177338_f() / 4 + this.vertexFormat.func_177344_b(1) / 4;
/* 284 */     int var6 = this.vertexFormat.func_177338_f() >> 2;
/* 285 */     this.rawIntBuffer.put(var5, p_178962_1_);
/* 286 */     this.rawIntBuffer.put(var5 + var6, p_178962_2_);
/* 287 */     this.rawIntBuffer.put(var5 + var6 * 2, p_178962_3_);
/* 288 */     this.rawIntBuffer.put(var5 + var6 * 3, p_178962_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178987_a(double p_178987_1_, double p_178987_3_, double p_178987_5_) {
/* 293 */     if (this.rawBufferIndex >= this.bufferSize - this.vertexFormat.func_177338_f())
/*     */     {
/* 295 */       growBuffer(2097152);
/*     */     }
/*     */     
/* 298 */     int var7 = this.vertexFormat.func_177338_f() / 4;
/* 299 */     int var8 = (this.vertexCount - 4) * var7;
/*     */     
/* 301 */     for (int var9 = 0; var9 < 4; var9++) {
/*     */       
/* 303 */       int var10 = var8 + var9 * var7;
/* 304 */       int var11 = var10 + 1;
/* 305 */       int var12 = var11 + 1;
/* 306 */       this.rawIntBuffer.put(var10, Float.floatToRawIntBits((float)(p_178987_1_ + this.xOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(var10))));
/* 307 */       this.rawIntBuffer.put(var11, Float.floatToRawIntBits((float)(p_178987_3_ + this.yOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(var11))));
/* 308 */       this.rawIntBuffer.put(var12, Float.floatToRawIntBits((float)(p_178987_5_ + this.zOffset) + Float.intBitsToFloat(this.rawIntBuffer.get(var12))));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGLCallListForPass(int p_78909_1_) {
/* 317 */     return ((this.vertexCount - p_78909_1_) * this.vertexFormat.func_177338_f() + this.vertexFormat.func_177340_e()) / 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178978_a(float p_178978_1_, float p_178978_2_, float p_178978_3_, int p_178978_4_) {
/* 322 */     int var5 = getGLCallListForPass(p_178978_4_);
/* 323 */     int var6 = this.rawIntBuffer.get(var5);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 328 */     if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
/*     */       
/* 330 */       int var7 = (int)((var6 & 0xFF) * p_178978_1_);
/* 331 */       int var8 = (int)((var6 >> 8 & 0xFF) * p_178978_2_);
/* 332 */       int var9 = (int)((var6 >> 16 & 0xFF) * p_178978_3_);
/* 333 */       var6 &= 0xFF000000;
/* 334 */       var6 |= var9 << 16 | var8 << 8 | var7;
/*     */     }
/*     */     else {
/*     */       
/* 338 */       int var7 = (int)((this.field_179007_h >> 24 & 0xFF) * p_178978_1_);
/* 339 */       int var8 = (int)((this.field_179007_h >> 16 & 0xFF) * p_178978_2_);
/* 340 */       int var9 = (int)((this.field_179007_h >> 8 & 0xFF) * p_178978_3_);
/* 341 */       var6 &= 0xFF;
/* 342 */       var6 |= var7 << 24 | var8 << 16 | var9 << 8;
/*     */     } 
/*     */     
/* 345 */     if (this.needsUpdate)
/*     */     {
/* 347 */       var6 = -1;
/*     */     }
/*     */     
/* 350 */     this.rawIntBuffer.put(var5, var6);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178988_b(int p_178988_1_, int p_178988_2_) {
/* 355 */     int var3 = getGLCallListForPass(p_178988_2_);
/* 356 */     int var4 = p_178988_1_ >> 16 & 0xFF;
/* 357 */     int var5 = p_178988_1_ >> 8 & 0xFF;
/* 358 */     int var6 = p_178988_1_ & 0xFF;
/* 359 */     int var7 = p_178988_1_ >> 24 & 0xFF;
/* 360 */     func_178972_a(var3, var4, var5, var6, var7);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178994_b(float p_178994_1_, float p_178994_2_, float p_178994_3_, int p_178994_4_) {
/* 365 */     int var5 = getGLCallListForPass(p_178994_4_);
/* 366 */     int var6 = MathHelper.clamp_int((int)(p_178994_1_ * 255.0F), 0, 255);
/* 367 */     int var7 = MathHelper.clamp_int((int)(p_178994_2_ * 255.0F), 0, 255);
/* 368 */     int var8 = MathHelper.clamp_int((int)(p_178994_3_ * 255.0F), 0, 255);
/* 369 */     func_178972_a(var5, var6, var7, var8, 255);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178972_a(int p_178972_1_, int p_178972_2_, int p_178972_3_, int p_178972_4_, int p_178972_5_) {
/* 374 */     if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
/*     */       
/* 376 */       this.rawIntBuffer.put(p_178972_1_, p_178972_5_ << 24 | p_178972_4_ << 16 | p_178972_3_ << 8 | p_178972_2_);
/*     */     }
/*     */     else {
/*     */       
/* 380 */       this.rawIntBuffer.put(p_178972_1_, p_178972_2_ << 24 | p_178972_3_ << 16 | p_178972_4_ << 8 | p_178972_5_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178961_b(int p_178961_1_, int p_178961_2_, int p_178961_3_, int p_178961_4_) {
/* 386 */     if (!this.needsUpdate) {
/*     */       
/* 388 */       if (p_178961_1_ > 255)
/*     */       {
/* 390 */         p_178961_1_ = 255;
/*     */       }
/*     */       
/* 393 */       if (p_178961_2_ > 255)
/*     */       {
/* 395 */         p_178961_2_ = 255;
/*     */       }
/*     */       
/* 398 */       if (p_178961_3_ > 255)
/*     */       {
/* 400 */         p_178961_3_ = 255;
/*     */       }
/*     */       
/* 403 */       if (p_178961_4_ > 255)
/*     */       {
/* 405 */         p_178961_4_ = 255;
/*     */       }
/*     */       
/* 408 */       if (p_178961_1_ < 0)
/*     */       {
/* 410 */         p_178961_1_ = 0;
/*     */       }
/*     */       
/* 413 */       if (p_178961_2_ < 0)
/*     */       {
/* 415 */         p_178961_2_ = 0;
/*     */       }
/*     */       
/* 418 */       if (p_178961_3_ < 0)
/*     */       {
/* 420 */         p_178961_3_ = 0;
/*     */       }
/*     */       
/* 423 */       if (p_178961_4_ < 0)
/*     */       {
/* 425 */         p_178961_4_ = 0;
/*     */       }
/*     */       
/* 428 */       if (!this.vertexFormat.func_177346_d()) {
/*     */         
/* 430 */         VertexFormatElement var5 = new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUseage.COLOR, 4);
/* 431 */         this.vertexFormat.func_177349_a(var5);
/*     */       } 
/*     */       
/* 434 */       if (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN) {
/*     */         
/* 436 */         this.field_179007_h = p_178961_4_ << 24 | p_178961_3_ << 16 | p_178961_2_ << 8 | p_178961_1_;
/*     */       }
/*     */       else {
/*     */         
/* 440 */         this.field_179007_h = p_178961_1_ << 24 | p_178961_2_ << 16 | p_178961_3_ << 8 | p_178961_4_;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178982_a(byte p_178982_1_, byte p_178982_2_, byte p_178982_3_) {
/* 447 */     setPosition(p_178982_1_ & 0xFF, p_178982_2_ & 0xFF, p_178982_3_ & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addVertexWithUV(double p_178985_1_, double p_178985_3_, double p_178985_5_, double p_178985_7_, double p_178985_9_) {
/* 452 */     if (this.quadSprite != null && this.quadSprites != null) {
/*     */       
/* 454 */       p_178985_7_ = this.quadSprite.toSingleU((float)p_178985_7_);
/* 455 */       p_178985_9_ = this.quadSprite.toSingleV((float)p_178985_9_);
/* 456 */       this.quadSprites[this.vertexCount / 4] = this.quadSprite;
/*     */     } 
/*     */     
/* 459 */     setTextureUV(p_178985_7_, p_178985_9_);
/* 460 */     addVertex(p_178985_1_, p_178985_3_, p_178985_5_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVertexFormat(VertexFormat p_178967_1_) {
/* 465 */     this.vertexFormat = new VertexFormat(p_178967_1_);
/*     */     
/* 467 */     if (Config.isShaders())
/*     */     {
/* 469 */       SVertexBuilder.endSetVertexFormat(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178981_a(int[] p_178981_1_) {
/* 475 */     if (Config.isShaders())
/*     */     {
/* 477 */       SVertexBuilder.beginAddVertexData(this, p_178981_1_);
/*     */     }
/*     */     
/* 480 */     int var2 = this.vertexFormat.func_177338_f() / 4;
/* 481 */     this.vertexCount += p_178981_1_.length / var2;
/* 482 */     this.rawIntBuffer.position(this.rawBufferIndex);
/* 483 */     this.rawIntBuffer.put(p_178981_1_);
/* 484 */     this.rawBufferIndex += p_178981_1_.length;
/*     */     
/* 486 */     if (Config.isShaders())
/*     */     {
/* 488 */       SVertexBuilder.endAddVertexData(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addVertex(double p_178984_1_, double p_178984_3_, double p_178984_5_) {
/* 494 */     if (Config.isShaders())
/*     */     {
/* 496 */       SVertexBuilder.beginAddVertex(this);
/*     */     }
/*     */     
/* 499 */     if (this.rawBufferIndex >= this.bufferSize - this.vertexFormat.func_177338_f())
/*     */     {
/* 501 */       growBuffer(2097152);
/*     */     }
/*     */     
/* 504 */     List<VertexFormatElement> var7 = this.vertexFormat.func_177343_g();
/* 505 */     int listSize = var7.size();
/*     */     
/* 507 */     for (int i = 0; i < listSize; i++) {
/*     */       
/* 509 */       VertexFormatElement var9 = var7.get(i);
/* 510 */       int var10 = var9.func_177373_a() >> 2;
/* 511 */       int var11 = this.rawBufferIndex + var10;
/*     */       
/* 513 */       switch (SwitchEnumUseage.field_178959_a[var9.func_177375_c().ordinal()]) {
/*     */         
/*     */         case 1:
/* 516 */           this.rawIntBuffer.put(var11, Float.floatToRawIntBits((float)(p_178984_1_ + this.xOffset)));
/* 517 */           this.rawIntBuffer.put(var11 + 1, Float.floatToRawIntBits((float)(p_178984_3_ + this.yOffset)));
/* 518 */           this.rawIntBuffer.put(var11 + 2, Float.floatToRawIntBits((float)(p_178984_5_ + this.zOffset)));
/*     */           break;
/*     */         
/*     */         case 2:
/* 522 */           this.rawIntBuffer.put(var11, this.field_179007_h);
/*     */           break;
/*     */         
/*     */         case 3:
/* 526 */           if (var9.func_177369_e() == 0) {
/*     */             
/* 528 */             this.rawIntBuffer.put(var11, Float.floatToRawIntBits((float)this.field_178998_e));
/* 529 */             this.rawIntBuffer.put(var11 + 1, Float.floatToRawIntBits((float)this.field_178995_f));
/*     */             
/*     */             break;
/*     */           } 
/* 533 */           this.rawIntBuffer.put(var11, this.field_178996_g);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 539 */           this.rawIntBuffer.put(var11, this.field_179003_o);
/*     */           break;
/*     */       } 
/*     */     } 
/* 543 */     this.rawBufferIndex += this.vertexFormat.func_177338_f() >> 2;
/* 544 */     this.vertexCount++;
/*     */     
/* 546 */     if (Config.isShaders())
/*     */     {
/* 548 */       SVertexBuilder.endAddVertex(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178991_c(int p_178991_1_) {
/* 554 */     int var2 = p_178991_1_ >> 16 & 0xFF;
/* 555 */     int var3 = p_178991_1_ >> 8 & 0xFF;
/* 556 */     int var4 = p_178991_1_ & 0xFF;
/* 557 */     setPosition(var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178974_a(int p_178974_1_, int p_178974_2_) {
/* 562 */     int var3 = p_178974_1_ >> 16 & 0xFF;
/* 563 */     int var4 = p_178974_1_ >> 8 & 0xFF;
/* 564 */     int var5 = p_178974_1_ & 0xFF;
/* 565 */     func_178961_b(var3, var4, var5, p_178974_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void markDirty() {
/* 573 */     this.needsUpdate = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178980_d(float p_178980_1_, float p_178980_2_, float p_178980_3_) {
/* 578 */     if (!this.vertexFormat.func_177350_b()) {
/*     */       
/* 580 */       VertexFormatElement var7 = new VertexFormatElement(0, VertexFormatElement.EnumType.BYTE, VertexFormatElement.EnumUseage.NORMAL, 3);
/* 581 */       this.vertexFormat.func_177349_a(var7);
/* 582 */       this.vertexFormat.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.UBYTE, VertexFormatElement.EnumUseage.PADDING, 1));
/*     */     } 
/*     */     
/* 585 */     byte var71 = (byte)(int)(p_178980_1_ * 127.0F);
/* 586 */     byte var5 = (byte)(int)(p_178980_2_ * 127.0F);
/* 587 */     byte var6 = (byte)(int)(p_178980_3_ * 127.0F);
/* 588 */     this.field_179003_o = var71 & 0xFF | (var5 & 0xFF) << 8 | (var6 & 0xFF) << 16;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178975_e(float p_178975_1_, float p_178975_2_, float p_178975_3_) {
/* 593 */     byte var4 = (byte)(int)(p_178975_1_ * 127.0F);
/* 594 */     byte var5 = (byte)(int)(p_178975_2_ * 127.0F);
/* 595 */     byte var6 = (byte)(int)(p_178975_3_ * 127.0F);
/* 596 */     int var7 = this.vertexFormat.func_177338_f() >> 2;
/* 597 */     int var8 = (this.vertexCount - 4) * var7 + this.vertexFormat.func_177342_c() / 4;
/* 598 */     this.field_179003_o = var4 & 0xFF | (var5 & 0xFF) << 8 | (var6 & 0xFF) << 16;
/* 599 */     this.rawIntBuffer.put(var8, this.field_179003_o);
/* 600 */     this.rawIntBuffer.put(var8 + var7, this.field_179003_o);
/* 601 */     this.rawIntBuffer.put(var8 + var7 * 2, this.field_179003_o);
/* 602 */     this.rawIntBuffer.put(var8 + var7 * 3, this.field_179003_o);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTranslation(double p_178969_1_, double p_178969_3_, double p_178969_5_) {
/* 607 */     this.xOffset = p_178969_1_;
/* 608 */     this.yOffset = p_178969_3_;
/* 609 */     this.zOffset = p_178969_5_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int draw() {
/* 614 */     if (!this.isDrawing)
/*     */     {
/* 616 */       throw new IllegalStateException("Not building!");
/*     */     }
/*     */ 
/*     */     
/* 620 */     this.isDrawing = false;
/*     */     
/* 622 */     if (this.vertexCount > 0) {
/*     */       
/* 624 */       this.byteBuffer.position(0);
/* 625 */       this.byteBuffer.limit(this.rawBufferIndex * 4);
/*     */     } 
/*     */     
/* 628 */     this.field_179012_p = this.rawBufferIndex * 4;
/* 629 */     return this.field_179012_p;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_178976_e() {
/* 635 */     return this.field_179012_p;
/*     */   }
/*     */ 
/*     */   
/*     */   public ByteBuffer func_178966_f() {
/* 640 */     return this.byteBuffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public VertexFormat func_178973_g() {
/* 645 */     return this.vertexFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178989_h() {
/* 650 */     return this.vertexCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDrawMode() {
/* 655 */     return this.drawMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178968_d(int p_178968_1_) {
/* 660 */     for (int var2 = 0; var2 < 4; var2++)
/*     */     {
/* 662 */       func_178988_b(p_178968_1_, var2 + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178990_f(float p_178990_1_, float p_178990_2_, float p_178990_3_) {
/* 668 */     for (int var4 = 0; var4 < 4; var4++)
/*     */     {
/* 670 */       func_178994_b(p_178990_1_, p_178990_2_, p_178990_3_, var4 + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void putSprite(TextureAtlasSprite sprite) {
/* 676 */     if (this.quadSprites != null) {
/*     */       
/* 678 */       int countQuads = this.vertexCount / 4;
/* 679 */       this.quadSprites[countQuads - 1] = sprite;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSprite(TextureAtlasSprite sprite) {
/* 685 */     if (this.quadSprites != null)
/*     */     {
/* 687 */       this.quadSprite = sprite;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMultiTexture() {
/* 693 */     return (this.quadSprites != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawMultiTexture() {
/* 698 */     if (this.quadSprites != null) {
/*     */       
/* 700 */       int maxTextureIndex = Config.getMinecraft().getTextureMapBlocks().getCountRegisteredSprites();
/*     */       
/* 702 */       if (this.drawnIcons.length <= maxTextureIndex)
/*     */       {
/* 704 */         this.drawnIcons = new boolean[maxTextureIndex + 1];
/*     */       }
/*     */       
/* 707 */       Arrays.fill(this.drawnIcons, false);
/* 708 */       int texSwitch = 0;
/* 709 */       int grassOverlayIndex = -1;
/* 710 */       int countQuads = this.vertexCount / 4;
/*     */       
/* 712 */       for (int i = 0; i < countQuads; i++) {
/*     */         
/* 714 */         TextureAtlasSprite icon = this.quadSprites[i];
/*     */         
/* 716 */         if (icon != null) {
/*     */           
/* 718 */           int iconIndex = icon.getIndexInMap();
/*     */           
/* 720 */           if (!this.drawnIcons[iconIndex])
/*     */           {
/* 722 */             if (icon == TextureUtils.iconGrassSideOverlay) {
/*     */               
/* 724 */               if (grassOverlayIndex < 0)
/*     */               {
/* 726 */                 grassOverlayIndex = i;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/* 731 */               i = drawForIcon(icon, i) - 1;
/* 732 */               texSwitch++;
/*     */               
/* 734 */               if (this.blockLayer != EnumWorldBlockLayer.TRANSLUCENT)
/*     */               {
/* 736 */                 this.drawnIcons[iconIndex] = true;
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 743 */       if (grassOverlayIndex >= 0) {
/*     */         
/* 745 */         drawForIcon(TextureUtils.iconGrassSideOverlay, grassOverlayIndex);
/* 746 */         texSwitch++;
/*     */       } 
/*     */       
/* 749 */       if (texSwitch > 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int drawForIcon(TextureAtlasSprite sprite, int startQuadPos) {
/* 758 */     GL11.glBindTexture(3553, sprite.glSpriteTextureId);
/* 759 */     int firstRegionEnd = -1;
/* 760 */     int lastPos = -1;
/* 761 */     int countQuads = this.vertexCount / 4;
/*     */     
/* 763 */     for (int i = startQuadPos; i < countQuads; i++) {
/*     */       
/* 765 */       TextureAtlasSprite ts = this.quadSprites[i];
/*     */       
/* 767 */       if (ts == sprite) {
/*     */         
/* 769 */         if (lastPos < 0)
/*     */         {
/* 771 */           lastPos = i;
/*     */         }
/*     */       }
/* 774 */       else if (lastPos >= 0) {
/*     */         
/* 776 */         draw(lastPos, i);
/*     */         
/* 778 */         if (this.blockLayer == EnumWorldBlockLayer.TRANSLUCENT)
/*     */         {
/* 780 */           return i;
/*     */         }
/*     */         
/* 783 */         lastPos = -1;
/*     */         
/* 785 */         if (firstRegionEnd < 0)
/*     */         {
/* 787 */           firstRegionEnd = i;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 792 */     if (lastPos >= 0)
/*     */     {
/* 794 */       draw(lastPos, countQuads);
/*     */     }
/*     */     
/* 797 */     if (firstRegionEnd < 0)
/*     */     {
/* 799 */       firstRegionEnd = countQuads;
/*     */     }
/*     */     
/* 802 */     return firstRegionEnd;
/*     */   }
/*     */ 
/*     */   
/*     */   private void draw(int startQuadVertex, int endQuadVertex) {
/* 807 */     int vxQuadCount = endQuadVertex - startQuadVertex;
/*     */     
/* 809 */     if (vxQuadCount > 0) {
/*     */       
/* 811 */       int startVertex = startQuadVertex * 4;
/* 812 */       int vxCount = vxQuadCount * 4;
/* 813 */       GL11.glDrawArrays(this.drawMode, startVertex, vxCount);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockLayer(EnumWorldBlockLayer blockLayer) {
/* 819 */     this.blockLayer = blockLayer;
/*     */     
/* 821 */     if (blockLayer == null) {
/*     */       
/* 823 */       if (this.quadSprites != null)
/*     */       {
/* 825 */         this.quadSpritesPrev = this.quadSprites;
/*     */       }
/*     */       
/* 828 */       this.quadSprites = null;
/* 829 */       this.quadSprite = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getBufferQuadSize() {
/* 835 */     int quadSize = this.rawIntBuffer.capacity() * 4 / this.vertexFormat.func_177338_f() * 4;
/* 836 */     return quadSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public void checkAndGrow() {
/* 841 */     if (this.rawBufferIndex >= this.bufferSize - this.vertexFormat.func_177338_f())
/*     */     {
/* 843 */       growBuffer(2097152);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isColorDisabled() {
/* 849 */     return this.needsUpdate;
/*     */   }
/*     */ 
/*     */   
/*     */   public class State
/*     */   {
/*     */     private final int[] field_179019_b;
/*     */     private final int field_179020_c;
/*     */     private final int field_179017_d;
/*     */     private final VertexFormat field_179018_e;
/*     */     private static final String __OBFID = "CL_00002568";
/*     */     public TextureAtlasSprite[] stateQuadSprites;
/*     */     
/*     */     public State(int[] buf, int bufIndex, int vertCount, VertexFormat vertFormat, TextureAtlasSprite[] quadSprites) {
/* 863 */       this.field_179019_b = buf;
/* 864 */       this.field_179020_c = bufIndex;
/* 865 */       this.field_179017_d = vertCount;
/* 866 */       this.field_179018_e = vertFormat;
/* 867 */       this.stateQuadSprites = quadSprites;
/*     */     }
/*     */ 
/*     */     
/*     */     public State(int[] p_i46274_2_, int p_i46274_3_, int p_i46274_4_, VertexFormat p_i46274_5_) {
/* 872 */       this.field_179019_b = p_i46274_2_;
/* 873 */       this.field_179020_c = p_i46274_3_;
/* 874 */       this.field_179017_d = p_i46274_4_;
/* 875 */       this.field_179018_e = p_i46274_5_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int[] func_179013_a() {
/* 880 */       return this.field_179019_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRawBufferIndex() {
/* 885 */       return this.field_179020_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getVertexCount() {
/* 890 */       return this.field_179017_d;
/*     */     }
/*     */ 
/*     */     
/*     */     public VertexFormat func_179016_d() {
/* 895 */       return this.field_179018_e;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchEnumUseage
/*     */   {
/* 901 */     static final int[] field_178959_a = new int[(VertexFormatElement.EnumUseage.values()).length];
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 907 */         field_178959_a[VertexFormatElement.EnumUseage.POSITION.ordinal()] = 1;
/*     */       }
/* 909 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 916 */         field_178959_a[VertexFormatElement.EnumUseage.COLOR.ordinal()] = 2;
/*     */       }
/* 918 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 925 */         field_178959_a[VertexFormatElement.EnumUseage.UV.ordinal()] = 3;
/*     */       }
/* 927 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 934 */         field_178959_a[VertexFormatElement.EnumUseage.NORMAL.ordinal()] = 4;
/*     */       }
/* 936 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\WorldRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */