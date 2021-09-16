/*     */ package net.minecraft.client.renderer.block.model;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraftforge.client.model.pipeline.IVertexConsumer;
/*     */ import net.minecraftforge.client.model.pipeline.IVertexProducer;
/*     */ import optifine.Config;
/*     */ import optifine.Reflector;
/*     */ 
/*     */ public class BakedQuad
/*     */   implements IVertexProducer {
/*     */   protected int[] field_178215_a;
/*     */   protected final int field_178213_b;
/*     */   protected final EnumFacing face;
/*     */   private static final String __OBFID = "CL_00002512";
/*  17 */   private TextureAtlasSprite sprite = null;
/*  18 */   private int[] vertexDataSingle = null;
/*     */ 
/*     */   
/*     */   public BakedQuad(int[] p_i46232_1_, int p_i46232_2_, EnumFacing p_i46232_3_, TextureAtlasSprite sprite) {
/*  22 */     this.field_178215_a = p_i46232_1_;
/*  23 */     this.field_178213_b = p_i46232_2_;
/*  24 */     this.face = p_i46232_3_;
/*  25 */     this.sprite = sprite;
/*  26 */     fixVertexData();
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite getSprite() {
/*  31 */     if (this.sprite == null)
/*     */     {
/*  33 */       this.sprite = getSpriteByUv(func_178209_a());
/*     */     }
/*     */     
/*  36 */     return this.sprite;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  41 */     return "vertex: " + (this.field_178215_a.length / 7) + ", tint: " + this.field_178213_b + ", facing: " + this.face + ", sprite: " + this.sprite;
/*     */   }
/*     */ 
/*     */   
/*     */   public BakedQuad(int[] p_i46232_1_, int p_i46232_2_, EnumFacing p_i46232_3_) {
/*  46 */     this.field_178215_a = p_i46232_1_;
/*  47 */     this.field_178213_b = p_i46232_2_;
/*  48 */     this.face = p_i46232_3_;
/*  49 */     fixVertexData();
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] func_178209_a() {
/*  54 */     fixVertexData();
/*  55 */     return this.field_178215_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178212_b() {
/*  60 */     return (this.field_178213_b != -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178211_c() {
/*  65 */     return this.field_178213_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumFacing getFace() {
/*  70 */     return this.face;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getVertexDataSingle() {
/*  75 */     if (this.vertexDataSingle == null)
/*     */     {
/*  77 */       this.vertexDataSingle = makeVertexDataSingle(func_178209_a(), getSprite());
/*     */     }
/*     */     
/*  80 */     return this.vertexDataSingle;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int[] makeVertexDataSingle(int[] vd, TextureAtlasSprite sprite) {
/*  85 */     int[] vdSingle = (int[])vd.clone();
/*  86 */     int ku = sprite.sheetWidth / sprite.getIconWidth();
/*  87 */     int kv = sprite.sheetHeight / sprite.getIconHeight();
/*  88 */     int step = vdSingle.length / 4;
/*     */     
/*  90 */     for (int i = 0; i < 4; i++) {
/*     */       
/*  92 */       int pos = i * step;
/*  93 */       float tu = Float.intBitsToFloat(vdSingle[pos + 4]);
/*  94 */       float tv = Float.intBitsToFloat(vdSingle[pos + 4 + 1]);
/*  95 */       float u = sprite.toSingleU(tu);
/*  96 */       float v = sprite.toSingleV(tv);
/*  97 */       vdSingle[pos + 4] = Float.floatToRawIntBits(u);
/*  98 */       vdSingle[pos + 4 + 1] = Float.floatToRawIntBits(v);
/*     */     } 
/*     */     
/* 101 */     return vdSingle;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pipe(IVertexConsumer consumer) {
/* 106 */     Reflector.callVoid(Reflector.LightUtil_putBakedQuad, new Object[] { consumer, this });
/*     */   }
/*     */ 
/*     */   
/*     */   private static TextureAtlasSprite getSpriteByUv(int[] vertexData) {
/* 111 */     float uMin = 1.0F;
/* 112 */     float vMin = 1.0F;
/* 113 */     float uMax = 0.0F;
/* 114 */     float vMax = 0.0F;
/* 115 */     int step = vertexData.length / 4;
/*     */     
/* 117 */     for (int uMid = 0; uMid < 4; uMid++) {
/*     */       
/* 119 */       int vMid = uMid * step;
/* 120 */       float spriteUv = Float.intBitsToFloat(vertexData[vMid + 4]);
/* 121 */       float tv = Float.intBitsToFloat(vertexData[vMid + 4 + 1]);
/* 122 */       uMin = Math.min(uMin, spriteUv);
/* 123 */       vMin = Math.min(vMin, tv);
/* 124 */       uMax = Math.max(uMax, spriteUv);
/* 125 */       vMax = Math.max(vMax, tv);
/*     */     } 
/*     */     
/* 128 */     float var10 = (uMin + uMax) / 2.0F;
/* 129 */     float var11 = (vMin + vMax) / 2.0F;
/* 130 */     TextureAtlasSprite var12 = Minecraft.getMinecraft().getTextureMapBlocks().getIconByUV(var10, var11);
/* 131 */     return var12;
/*     */   }
/*     */ 
/*     */   
/*     */   private void fixVertexData() {
/* 136 */     if (Config.isShaders()) {
/*     */       
/* 138 */       if (this.field_178215_a.length == 28)
/*     */       {
/* 140 */         this.field_178215_a = expandVertexData(this.field_178215_a);
/*     */       }
/*     */     }
/* 143 */     else if (this.field_178215_a.length == 56) {
/*     */       
/* 145 */       this.field_178215_a = compactVertexData(this.field_178215_a);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static int[] expandVertexData(int[] vd) {
/* 151 */     int step = vd.length / 4;
/* 152 */     int stepNew = step * 2;
/* 153 */     int[] vdNew = new int[stepNew * 4];
/*     */     
/* 155 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 157 */       System.arraycopy(vd, i * step, vdNew, i * stepNew, step);
/*     */     }
/*     */     
/* 160 */     return vdNew;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int[] compactVertexData(int[] vd) {
/* 165 */     int step = vd.length / 4;
/* 166 */     int stepNew = step / 2;
/* 167 */     int[] vdNew = new int[stepNew * 4];
/*     */     
/* 169 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 171 */       System.arraycopy(vd, i * step, vdNew, i * stepNew, stepNew);
/*     */     }
/*     */     
/* 174 */     return vdNew;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\BakedQuad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */