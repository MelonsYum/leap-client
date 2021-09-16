/*     */ package optifine;
/*     */ 
/*     */ import java.util.Properties;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class CustomSkyLayer
/*     */ {
/*  11 */   public String source = null;
/*  12 */   private int startFadeIn = -1;
/*  13 */   private int endFadeIn = -1;
/*  14 */   private int startFadeOut = -1;
/*  15 */   private int endFadeOut = -1;
/*  16 */   private int blend = 1;
/*     */   private boolean rotate = false;
/*  18 */   private float speed = 1.0F;
/*     */   private float[] axis;
/*     */   private RangeListInt days;
/*     */   private int daysLoop;
/*     */   public int textureId;
/*  23 */   public static final float[] DEFAULT_AXIS = new float[] { 1.0F, 0.0F, 0.0F };
/*     */ 
/*     */   
/*     */   public CustomSkyLayer(Properties props, String defSource) {
/*  27 */     this.axis = DEFAULT_AXIS;
/*  28 */     this.days = null;
/*  29 */     this.daysLoop = 8;
/*  30 */     this.textureId = -1;
/*  31 */     ConnectedParser cp = new ConnectedParser("CustomSky");
/*  32 */     this.source = props.getProperty("source", defSource);
/*  33 */     this.startFadeIn = parseTime(props.getProperty("startFadeIn"));
/*  34 */     this.endFadeIn = parseTime(props.getProperty("endFadeIn"));
/*  35 */     this.startFadeOut = parseTime(props.getProperty("startFadeOut"));
/*  36 */     this.endFadeOut = parseTime(props.getProperty("endFadeOut"));
/*  37 */     this.blend = Blender.parseBlend(props.getProperty("blend"));
/*  38 */     this.rotate = parseBoolean(props.getProperty("rotate"), true);
/*  39 */     this.speed = parseFloat(props.getProperty("speed"), 1.0F);
/*  40 */     this.axis = parseAxis(props.getProperty("axis"), DEFAULT_AXIS);
/*  41 */     this.days = cp.parseRangeListInt(props.getProperty("days"));
/*  42 */     this.daysLoop = cp.parseInt(props.getProperty("daysLoop"), 8);
/*     */   }
/*     */ 
/*     */   
/*     */   private int parseTime(String str) {
/*  47 */     if (str == null)
/*     */     {
/*  49 */       return -1;
/*     */     }
/*     */ 
/*     */     
/*  53 */     String[] strs = Config.tokenize(str, ":");
/*     */     
/*  55 */     if (strs.length != 2) {
/*     */       
/*  57 */       Config.warn("Invalid time: " + str);
/*  58 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/*  62 */     String hourStr = strs[0];
/*  63 */     String minStr = strs[1];
/*  64 */     int hour = Config.parseInt(hourStr, -1);
/*  65 */     int min = Config.parseInt(minStr, -1);
/*     */     
/*  67 */     if (hour >= 0 && hour <= 23 && min >= 0 && min <= 59) {
/*     */       
/*  69 */       hour -= 6;
/*     */       
/*  71 */       if (hour < 0)
/*     */       {
/*  73 */         hour += 24;
/*     */       }
/*     */       
/*  76 */       int time = hour * 1000 + (int)(min / 60.0D * 1000.0D);
/*  77 */       return time;
/*     */     } 
/*     */ 
/*     */     
/*  81 */     Config.warn("Invalid time: " + str);
/*  82 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean parseBoolean(String str, boolean defVal) {
/*  90 */     if (str == null)
/*     */     {
/*  92 */       return defVal;
/*     */     }
/*  94 */     if (str.toLowerCase().equals("true"))
/*     */     {
/*  96 */       return true;
/*     */     }
/*  98 */     if (str.toLowerCase().equals("false"))
/*     */     {
/* 100 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 104 */     Config.warn("Unknown boolean: " + str);
/* 105 */     return defVal;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float parseFloat(String str, float defVal) {
/* 111 */     if (str == null)
/*     */     {
/* 113 */       return defVal;
/*     */     }
/*     */ 
/*     */     
/* 117 */     float val = Config.parseFloat(str, Float.MIN_VALUE);
/*     */     
/* 119 */     if (val == Float.MIN_VALUE) {
/*     */       
/* 121 */       Config.warn("Invalid value: " + str);
/* 122 */       return defVal;
/*     */     } 
/*     */ 
/*     */     
/* 126 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] parseAxis(String str, float[] defVal) {
/* 133 */     if (str == null)
/*     */     {
/* 135 */       return defVal;
/*     */     }
/*     */ 
/*     */     
/* 139 */     String[] strs = Config.tokenize(str, " ");
/*     */     
/* 141 */     if (strs.length != 3) {
/*     */       
/* 143 */       Config.warn("Invalid axis: " + str);
/* 144 */       return defVal;
/*     */     } 
/*     */ 
/*     */     
/* 148 */     float[] fs = new float[3];
/*     */     
/* 150 */     for (int ax = 0; ax < strs.length; ax++) {
/*     */       
/* 152 */       fs[ax] = Config.parseFloat(strs[ax], Float.MIN_VALUE);
/*     */       
/* 154 */       if (fs[ax] == Float.MIN_VALUE) {
/*     */         
/* 156 */         Config.warn("Invalid axis: " + str);
/* 157 */         return defVal;
/*     */       } 
/*     */       
/* 160 */       if (fs[ax] < -1.0F || fs[ax] > 1.0F) {
/*     */         
/* 162 */         Config.warn("Invalid axis values: " + str);
/* 163 */         return defVal;
/*     */       } 
/*     */     } 
/*     */     
/* 167 */     float var9 = fs[0];
/* 168 */     float ay = fs[1];
/* 169 */     float az = fs[2];
/*     */     
/* 171 */     if (var9 * var9 + ay * ay + az * az < 1.0E-5F) {
/*     */       
/* 173 */       Config.warn("Invalid axis values: " + str);
/* 174 */       return defVal;
/*     */     } 
/*     */ 
/*     */     
/* 178 */     float[] as = { az, ay, -var9 };
/* 179 */     return as;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid(String path) {
/* 187 */     if (this.source == null) {
/*     */       
/* 189 */       Config.warn("No source texture: " + path);
/* 190 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 194 */     this.source = TextureUtils.fixResourcePath(this.source, TextureUtils.getBasePath(path));
/*     */     
/* 196 */     if (this.startFadeIn >= 0 && this.endFadeIn >= 0 && this.endFadeOut >= 0) {
/*     */       
/* 198 */       int timeFadeIn = normalizeTime(this.endFadeIn - this.startFadeIn);
/*     */       
/* 200 */       if (this.startFadeOut < 0) {
/*     */         
/* 202 */         this.startFadeOut = normalizeTime(this.endFadeOut - timeFadeIn);
/*     */         
/* 204 */         if (timeBetween(this.startFadeOut, this.startFadeIn, this.endFadeIn))
/*     */         {
/* 206 */           this.startFadeOut = this.endFadeIn;
/*     */         }
/*     */       } 
/*     */       
/* 210 */       int timeOn = normalizeTime(this.startFadeOut - this.endFadeIn);
/* 211 */       int timeFadeOut = normalizeTime(this.endFadeOut - this.startFadeOut);
/* 212 */       int timeOff = normalizeTime(this.startFadeIn - this.endFadeOut);
/* 213 */       int timeSum = timeFadeIn + timeOn + timeFadeOut + timeOff;
/*     */       
/* 215 */       if (timeSum != 24000) {
/*     */         
/* 217 */         Config.warn("Invalid fadeIn/fadeOut times, sum is not 24h: " + timeSum);
/* 218 */         return false;
/*     */       } 
/* 220 */       if (this.speed < 0.0F) {
/*     */         
/* 222 */         Config.warn("Invalid speed: " + this.speed);
/* 223 */         return false;
/*     */       } 
/* 225 */       if (this.daysLoop <= 0) {
/*     */         
/* 227 */         Config.warn("Invalid daysLoop: " + this.daysLoop);
/* 228 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 232 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 237 */     Config.warn("Invalid times, required are: startFadeIn, endFadeIn and endFadeOut.");
/* 238 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int normalizeTime(int timeMc) {
/* 245 */     while (timeMc >= 24000)
/*     */     {
/* 247 */       timeMc -= 24000;
/*     */     }
/*     */     
/* 250 */     while (timeMc < 0)
/*     */     {
/* 252 */       timeMc += 24000;
/*     */     }
/*     */     
/* 255 */     return timeMc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(int timeOfDay, float celestialAngle, float rainBrightness) {
/* 260 */     float brightness = rainBrightness * getFadeBrightness(timeOfDay);
/* 261 */     brightness = Config.limit(brightness, 0.0F, 1.0F);
/*     */     
/* 263 */     if (brightness >= 1.0E-4F) {
/*     */       
/* 265 */       GlStateManager.func_179144_i(this.textureId);
/* 266 */       Blender.setupBlend(this.blend, brightness);
/* 267 */       GlStateManager.pushMatrix();
/*     */       
/* 269 */       if (this.rotate)
/*     */       {
/* 271 */         GlStateManager.rotate(celestialAngle * 360.0F * this.speed, this.axis[0], this.axis[1], this.axis[2]);
/*     */       }
/*     */       
/* 274 */       Tessellator tess = Tessellator.getInstance();
/* 275 */       GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
/* 276 */       GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
/* 277 */       renderSide(tess, 4);
/* 278 */       GlStateManager.pushMatrix();
/* 279 */       GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
/* 280 */       renderSide(tess, 1);
/* 281 */       GlStateManager.popMatrix();
/* 282 */       GlStateManager.pushMatrix();
/* 283 */       GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
/* 284 */       renderSide(tess, 0);
/* 285 */       GlStateManager.popMatrix();
/* 286 */       GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
/* 287 */       renderSide(tess, 5);
/* 288 */       GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
/* 289 */       renderSide(tess, 2);
/* 290 */       GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
/* 291 */       renderSide(tess, 3);
/* 292 */       GlStateManager.popMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float getFadeBrightness(int timeOfDay) {
/* 301 */     if (timeBetween(timeOfDay, this.startFadeIn, this.endFadeIn)) {
/*     */       
/* 303 */       int timeFadeOut = normalizeTime(this.endFadeIn - this.startFadeIn);
/* 304 */       int timeDiff = normalizeTime(timeOfDay - this.startFadeIn);
/* 305 */       return timeDiff / timeFadeOut;
/*     */     } 
/* 307 */     if (timeBetween(timeOfDay, this.endFadeIn, this.startFadeOut))
/*     */     {
/* 309 */       return 1.0F;
/*     */     }
/* 311 */     if (timeBetween(timeOfDay, this.startFadeOut, this.endFadeOut)) {
/*     */       
/* 313 */       int timeFadeOut = normalizeTime(this.endFadeOut - this.startFadeOut);
/* 314 */       int timeDiff = normalizeTime(timeOfDay - this.startFadeOut);
/* 315 */       return 1.0F - timeDiff / timeFadeOut;
/*     */     } 
/*     */ 
/*     */     
/* 319 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderSide(Tessellator tess, int side) {
/* 325 */     WorldRenderer wr = tess.getWorldRenderer();
/* 326 */     double tx = (side % 3) / 3.0D;
/* 327 */     double ty = (side / 3) / 2.0D;
/* 328 */     wr.startDrawingQuads();
/* 329 */     wr.addVertexWithUV(-100.0D, -100.0D, -100.0D, tx, ty);
/* 330 */     wr.addVertexWithUV(-100.0D, -100.0D, 100.0D, tx, ty + 0.5D);
/* 331 */     wr.addVertexWithUV(100.0D, -100.0D, 100.0D, tx + 0.3333333333333333D, ty + 0.5D);
/* 332 */     wr.addVertexWithUV(100.0D, -100.0D, -100.0D, tx + 0.3333333333333333D, ty);
/* 333 */     tess.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isActive(World world, int timeOfDay) {
/* 338 */     if (timeBetween(timeOfDay, this.endFadeOut, this.startFadeIn))
/*     */     {
/* 340 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 344 */     if (this.days != null) {
/*     */       
/* 346 */       long time = world.getWorldTime();
/*     */       
/*     */       long timeShift;
/* 349 */       for (timeShift = time - this.startFadeIn; timeShift < 0L; timeShift += (24000 * this.daysLoop));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 354 */       int day = (int)(timeShift / 24000L);
/* 355 */       int dayOfLoop = day % this.daysLoop;
/*     */       
/* 357 */       if (!this.days.isInRange(dayOfLoop))
/*     */       {
/* 359 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 363 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean timeBetween(int timeOfDay, int timeStart, int timeEnd) {
/* 369 */     return (timeStart <= timeEnd) ? ((timeOfDay >= timeStart && timeOfDay <= timeEnd)) : (!(timeOfDay < timeStart && timeOfDay > timeEnd));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\CustomSkyLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */