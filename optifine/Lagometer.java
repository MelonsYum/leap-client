/*     */ package optifine;
/*     */ 
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiIngame;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.profiler.Profiler;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Lagometer
/*     */ {
/*     */   private static Minecraft mc;
/*     */   private static GameSettings gameSettings;
/*     */   private static Profiler profiler;
/*     */   public static boolean active = false;
/*  19 */   public static TimerNano timerTick = new TimerNano();
/*  20 */   public static TimerNano timerScheduledExecutables = new TimerNano();
/*  21 */   public static TimerNano timerChunkUpload = new TimerNano();
/*  22 */   public static TimerNano timerChunkUpdate = new TimerNano();
/*  23 */   public static TimerNano timerVisibility = new TimerNano();
/*  24 */   public static TimerNano timerTerrain = new TimerNano();
/*  25 */   public static TimerNano timerServer = new TimerNano();
/*  26 */   private static long[] timesFrame = new long[512];
/*  27 */   private static long[] timesTick = new long[512];
/*  28 */   private static long[] timesScheduledExecutables = new long[512];
/*  29 */   private static long[] timesChunkUpload = new long[512];
/*  30 */   private static long[] timesChunkUpdate = new long[512];
/*  31 */   private static long[] timesVisibility = new long[512];
/*  32 */   private static long[] timesTerrain = new long[512];
/*  33 */   private static long[] timesServer = new long[512];
/*  34 */   private static boolean[] gcs = new boolean[512];
/*  35 */   private static int numRecordedFrameTimes = 0;
/*  36 */   private static long prevFrameTimeNano = -1L;
/*  37 */   private static long renderTimeNano = 0L;
/*  38 */   private static long memTimeStartMs = System.currentTimeMillis();
/*  39 */   private static long memStart = getMemoryUsed();
/*  40 */   private static long memTimeLast = memTimeStartMs;
/*  41 */   private static long memLast = memStart;
/*  42 */   private static long memTimeDiffMs = 1L;
/*  43 */   private static long memDiff = 0L;
/*  44 */   private static int memMbSec = 0;
/*     */ 
/*     */   
/*     */   public static boolean updateMemoryAllocation() {
/*  48 */     long timeNowMs = System.currentTimeMillis();
/*  49 */     long memNow = getMemoryUsed();
/*  50 */     boolean gc = false;
/*     */     
/*  52 */     if (memNow < memLast) {
/*     */       
/*  54 */       double memDiffMb = memDiff / 1000000.0D;
/*  55 */       double timeDiffSec = memTimeDiffMs / 1000.0D;
/*  56 */       int mbSec = (int)(memDiffMb / timeDiffSec);
/*     */       
/*  58 */       if (mbSec > 0)
/*     */       {
/*  60 */         memMbSec = mbSec;
/*     */       }
/*     */       
/*  63 */       memTimeStartMs = timeNowMs;
/*  64 */       memStart = memNow;
/*  65 */       memTimeDiffMs = 0L;
/*  66 */       memDiff = 0L;
/*  67 */       gc = true;
/*     */     }
/*     */     else {
/*     */       
/*  71 */       memTimeDiffMs = timeNowMs - memTimeStartMs;
/*  72 */       memDiff = memNow - memStart;
/*     */     } 
/*     */     
/*  75 */     memTimeLast = timeNowMs;
/*  76 */     memLast = memNow;
/*  77 */     return gc;
/*     */   }
/*     */ 
/*     */   
/*     */   private static long getMemoryUsed() {
/*  82 */     Runtime r = Runtime.getRuntime();
/*  83 */     return r.totalMemory() - r.freeMemory();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateLagometer() {
/*  88 */     if (mc == null) {
/*     */       
/*  90 */       mc = Minecraft.getMinecraft();
/*  91 */       gameSettings = mc.gameSettings;
/*  92 */       profiler = mc.mcProfiler;
/*     */     } 
/*     */     
/*  95 */     if (gameSettings.showDebugInfo && gameSettings.ofLagometer) {
/*     */       
/*  97 */       active = true;
/*  98 */       long timeNowNano = System.nanoTime();
/*     */       
/* 100 */       if (prevFrameTimeNano == -1L)
/*     */       {
/* 102 */         prevFrameTimeNano = timeNowNano;
/*     */       }
/*     */       else
/*     */       {
/* 106 */         int frameIndex = numRecordedFrameTimes & timesFrame.length - 1;
/* 107 */         numRecordedFrameTimes++;
/* 108 */         boolean gc = updateMemoryAllocation();
/* 109 */         timesFrame[frameIndex] = timeNowNano - prevFrameTimeNano - renderTimeNano;
/* 110 */         timesTick[frameIndex] = timerTick.timeNano;
/* 111 */         timesScheduledExecutables[frameIndex] = timerScheduledExecutables.timeNano;
/* 112 */         timesChunkUpload[frameIndex] = timerChunkUpload.timeNano;
/* 113 */         timesChunkUpdate[frameIndex] = timerChunkUpdate.timeNano;
/* 114 */         timesVisibility[frameIndex] = timerVisibility.timeNano;
/* 115 */         timesTerrain[frameIndex] = timerTerrain.timeNano;
/* 116 */         timesServer[frameIndex] = timerServer.timeNano;
/* 117 */         gcs[frameIndex] = gc;
/* 118 */         timerTick.reset();
/* 119 */         timerScheduledExecutables.reset();
/* 120 */         timerVisibility.reset();
/* 121 */         timerChunkUpdate.reset();
/* 122 */         timerChunkUpload.reset();
/* 123 */         timerTerrain.reset();
/* 124 */         timerServer.reset();
/* 125 */         prevFrameTimeNano = System.nanoTime();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 130 */       active = false;
/* 131 */       prevFrameTimeNano = -1L;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void showLagometer(ScaledResolution scaledResolution) {
/* 137 */     if (gameSettings != null)
/*     */     {
/* 139 */       if (gameSettings.ofLagometer) {
/*     */         
/* 141 */         long timeRenderStartNano = System.nanoTime();
/* 142 */         GlStateManager.clear(256);
/* 143 */         GlStateManager.matrixMode(5889);
/* 144 */         GlStateManager.pushMatrix();
/* 145 */         GlStateManager.enableColorMaterial();
/* 146 */         GlStateManager.loadIdentity();
/* 147 */         GlStateManager.ortho(0.0D, mc.displayWidth, mc.displayHeight, 0.0D, 1000.0D, 3000.0D);
/* 148 */         GlStateManager.matrixMode(5888);
/* 149 */         GlStateManager.pushMatrix();
/* 150 */         GlStateManager.loadIdentity();
/* 151 */         GlStateManager.translate(0.0F, 0.0F, -2000.0F);
/* 152 */         GL11.glLineWidth(1.0F);
/* 153 */         GlStateManager.func_179090_x();
/* 154 */         Tessellator tess = Tessellator.getInstance();
/* 155 */         WorldRenderer tessellator = tess.getWorldRenderer();
/* 156 */         tessellator.startDrawing(1);
/*     */ 
/*     */         
/*     */         int y60;
/*     */         
/* 161 */         for (y60 = 0; y60 < timesFrame.length; y60++) {
/*     */           
/* 163 */           int i = (y60 - numRecordedFrameTimes & timesFrame.length - 1) * 100 / timesFrame.length;
/* 164 */           i += 155;
/* 165 */           float f = mc.displayHeight;
/* 166 */           long memColR = 0L;
/*     */           
/* 168 */           if (gcs[y60]) {
/*     */             
/* 170 */             renderTime(y60, timesFrame[y60], i, i / 2, 0, f, tessellator);
/*     */           }
/*     */           else {
/*     */             
/* 174 */             renderTime(y60, timesFrame[y60], i, i, i, f, tessellator);
/* 175 */             f -= (float)renderTime(y60, timesServer[y60], i / 2, i / 2, i / 2, f, tessellator);
/* 176 */             f -= (float)renderTime(y60, timesTerrain[y60], 0, i, 0, f, tessellator);
/* 177 */             f -= (float)renderTime(y60, timesVisibility[y60], i, i, 0, f, tessellator);
/* 178 */             f -= (float)renderTime(y60, timesChunkUpdate[y60], i, 0, 0, f, tessellator);
/* 179 */             f -= (float)renderTime(y60, timesChunkUpload[y60], i, 0, i, f, tessellator);
/* 180 */             f -= (float)renderTime(y60, timesScheduledExecutables[y60], 0, 0, i, f, tessellator);
/* 181 */             float f1 = f - (float)renderTime(y60, timesTick[y60], 0, i, i, f, tessellator);
/*     */           } 
/*     */         } 
/*     */         
/* 185 */         renderTimeDivider(0, timesFrame.length, 33333333L, 196, 196, 196, mc.displayHeight, tessellator);
/* 186 */         renderTimeDivider(0, timesFrame.length, 16666666L, 196, 196, 196, mc.displayHeight, tessellator);
/* 187 */         tess.draw();
/* 188 */         GlStateManager.func_179098_w();
/* 189 */         y60 = mc.displayHeight - 80;
/* 190 */         int y30 = mc.displayHeight - 160;
/* 191 */         mc.fontRendererObj.drawString("30", 2.0D, (y30 + 1), -8947849);
/* 192 */         mc.fontRendererObj.drawString("30", 1.0D, y30, -3881788);
/* 193 */         mc.fontRendererObj.drawString("60", 2.0D, (y60 + 1), -8947849);
/* 194 */         mc.fontRendererObj.drawString("60", 1.0D, y60, -3881788);
/* 195 */         GlStateManager.matrixMode(5889);
/* 196 */         GlStateManager.popMatrix();
/* 197 */         GlStateManager.matrixMode(5888);
/* 198 */         GlStateManager.popMatrix();
/* 199 */         GlStateManager.func_179098_w();
/* 200 */         float lumMem = 1.0F - (float)((System.currentTimeMillis() - memTimeStartMs) / 1000.0D);
/* 201 */         lumMem = Config.limit(lumMem, 0.0F, 1.0F);
/* 202 */         int var14 = (int)(170.0F + lumMem * 85.0F);
/* 203 */         int memColG = (int)(100.0F + lumMem * 55.0F);
/* 204 */         int memColB = (int)(10.0F + lumMem * 10.0F);
/* 205 */         int colMem = var14 << 16 | memColG << 8 | memColB;
/* 206 */         int posX = 512 / scaledResolution.getScaleFactor() + 2;
/* 207 */         int posY = mc.displayHeight / scaledResolution.getScaleFactor() - 8;
/* 208 */         GuiIngame var15 = mc.ingameGUI;
/* 209 */         GuiIngame.drawRect((posX - 1), (posY - 1), (posX + 50), (posY + 10), -1605349296);
/* 210 */         mc.fontRendererObj.drawString(" " + memMbSec + " MB/s", posX, posY, colMem);
/* 211 */         renderTimeNano = System.nanoTime() - timeRenderStartNano;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static long renderTime(int frameNum, long time, int r, int g, int b, float baseHeight, WorldRenderer tessellator) {
/* 218 */     long heightTime = time / 200000L;
/*     */     
/* 220 */     if (heightTime < 3L)
/*     */     {
/* 222 */       return 0L;
/*     */     }
/*     */ 
/*     */     
/* 226 */     tessellator.func_178961_b(r, g, b, 255);
/* 227 */     tessellator.addVertex((frameNum + 0.5F), (baseHeight - (float)heightTime + 0.5F), 0.0D);
/* 228 */     tessellator.addVertex((frameNum + 0.5F), (baseHeight + 0.5F), 0.0D);
/* 229 */     return heightTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static long renderTimeDivider(int frameStart, int frameEnd, long time, int r, int g, int b, float baseHeight, WorldRenderer tessellator) {
/* 235 */     long heightTime = time / 200000L;
/*     */     
/* 237 */     if (heightTime < 3L)
/*     */     {
/* 239 */       return 0L;
/*     */     }
/*     */ 
/*     */     
/* 243 */     tessellator.func_178961_b(r, g, b, 255);
/* 244 */     tessellator.addVertex((frameStart + 0.5F), (baseHeight - (float)heightTime + 0.5F), 0.0D);
/* 245 */     tessellator.addVertex((frameEnd + 0.5F), (baseHeight - (float)heightTime + 0.5F), 0.0D);
/* 246 */     return heightTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isActive() {
/* 252 */     return active;
/*     */   }
/*     */   
/*     */   public static class TimerNano
/*     */   {
/* 257 */     public long timeStartNano = 0L;
/* 258 */     public long timeNano = 0L;
/*     */ 
/*     */     
/*     */     public void start() {
/* 262 */       if (Lagometer.active)
/*     */       {
/* 264 */         if (this.timeStartNano == 0L)
/*     */         {
/* 266 */           this.timeStartNano = System.nanoTime();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void end() {
/* 273 */       if (Lagometer.active)
/*     */       {
/* 275 */         if (this.timeStartNano != 0L) {
/*     */           
/* 277 */           this.timeNano += System.nanoTime() - this.timeStartNano;
/* 278 */           this.timeStartNano = 0L;
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     private void reset() {
/* 285 */       this.timeNano = 0L;
/* 286 */       this.timeStartNano = 0L;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\Lagometer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */