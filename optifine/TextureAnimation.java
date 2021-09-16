/*     */ package optifine;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Properties;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class TextureAnimation
/*     */ {
/*  13 */   private String srcTex = null;
/*  14 */   private String dstTex = null;
/*  15 */   ResourceLocation dstTexLoc = null;
/*  16 */   private int dstTextId = -1;
/*  17 */   private int dstX = 0;
/*  18 */   private int dstY = 0;
/*  19 */   private int frameWidth = 0;
/*  20 */   private int frameHeight = 0;
/*  21 */   private TextureAnimationFrame[] frames = null;
/*  22 */   private int activeFrame = 0;
/*  23 */   byte[] srcData = null;
/*  24 */   private ByteBuffer imageData = null;
/*     */ 
/*     */   
/*     */   public TextureAnimation(String texFrom, byte[] srcData, String texTo, ResourceLocation locTexTo, int dstX, int dstY, int frameWidth, int frameHeight, Properties props, int durDef) {
/*  28 */     this.srcTex = texFrom;
/*  29 */     this.dstTex = texTo;
/*  30 */     this.dstTexLoc = locTexTo;
/*  31 */     this.dstX = dstX;
/*  32 */     this.dstY = dstY;
/*  33 */     this.frameWidth = frameWidth;
/*  34 */     this.frameHeight = frameHeight;
/*  35 */     int frameLen = frameWidth * frameHeight * 4;
/*     */     
/*  37 */     if (srcData.length % frameLen != 0)
/*     */     {
/*  39 */       Config.warn("Invalid animated texture length: " + srcData.length + ", frameWidth: " + frameWidth + ", frameHeight: " + frameHeight);
/*     */     }
/*     */     
/*  42 */     this.srcData = srcData;
/*  43 */     int numFrames = srcData.length / frameLen;
/*     */     
/*  45 */     if (props.get("tile.0") != null)
/*     */     {
/*  47 */       for (int durationDefStr = 0; props.get("tile." + durationDefStr) != null; durationDefStr++)
/*     */       {
/*  49 */         numFrames = durationDefStr + 1;
/*     */       }
/*     */     }
/*     */     
/*  53 */     String var21 = (String)props.get("duration");
/*  54 */     int durationDef = Config.parseInt(var21, durDef);
/*  55 */     this.frames = new TextureAnimationFrame[numFrames];
/*     */     
/*  57 */     for (int i = 0; i < this.frames.length; i++) {
/*     */       
/*  59 */       String indexStr = (String)props.get("tile." + i);
/*  60 */       int index = Config.parseInt(indexStr, i);
/*  61 */       String durationStr = (String)props.get("duration." + i);
/*  62 */       int duration = Config.parseInt(durationStr, durationDef);
/*  63 */       TextureAnimationFrame frm = new TextureAnimationFrame(index, duration);
/*  64 */       this.frames[i] = frm;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean nextFrame() {
/*  70 */     if (this.frames.length <= 0)
/*     */     {
/*  72 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  76 */     if (this.activeFrame >= this.frames.length)
/*     */     {
/*  78 */       this.activeFrame = 0;
/*     */     }
/*     */     
/*  81 */     TextureAnimationFrame frame = this.frames[this.activeFrame];
/*  82 */     frame.counter++;
/*     */     
/*  84 */     if (frame.counter < frame.duration)
/*     */     {
/*  86 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  90 */     frame.counter = 0;
/*  91 */     this.activeFrame++;
/*     */     
/*  93 */     if (this.activeFrame >= this.frames.length)
/*     */     {
/*  95 */       this.activeFrame = 0;
/*     */     }
/*     */     
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getActiveFrameIndex() {
/* 105 */     if (this.frames.length <= 0)
/*     */     {
/* 107 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 111 */     if (this.activeFrame >= this.frames.length)
/*     */     {
/* 113 */       this.activeFrame = 0;
/*     */     }
/*     */     
/* 116 */     TextureAnimationFrame frame = this.frames[this.activeFrame];
/* 117 */     return frame.index;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFrameCount() {
/* 123 */     return this.frames.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean updateTexture() {
/* 128 */     if (this.dstTextId < 0) {
/*     */       
/* 130 */       ITextureObject frameLen = TextureUtils.getTexture(this.dstTexLoc);
/*     */       
/* 132 */       if (frameLen == null)
/*     */       {
/* 134 */         return false;
/*     */       }
/*     */       
/* 137 */       this.dstTextId = frameLen.getGlTextureId();
/*     */     } 
/*     */     
/* 140 */     if (this.imageData == null) {
/*     */       
/* 142 */       this.imageData = GLAllocation.createDirectByteBuffer(this.srcData.length);
/* 143 */       this.imageData.put(this.srcData);
/* 144 */       this.srcData = null;
/*     */     } 
/*     */     
/* 147 */     if (!nextFrame())
/*     */     {
/* 149 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 153 */     int frameLen1 = this.frameWidth * this.frameHeight * 4;
/* 154 */     int imgNum = getActiveFrameIndex();
/* 155 */     int offset = frameLen1 * imgNum;
/*     */     
/* 157 */     if (offset + frameLen1 > this.imageData.capacity())
/*     */     {
/* 159 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 163 */     this.imageData.position(offset);
/* 164 */     GlStateManager.func_179144_i(this.dstTextId);
/* 165 */     GL11.glTexSubImage2D(3553, 0, this.dstX, this.dstY, this.frameWidth, this.frameHeight, 6408, 5121, this.imageData);
/* 166 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSrcTex() {
/* 173 */     return this.srcTex;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDstTex() {
/* 178 */     return this.dstTex;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getDstTexLoc() {
/* 183 */     return this.dstTexLoc;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\TextureAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */