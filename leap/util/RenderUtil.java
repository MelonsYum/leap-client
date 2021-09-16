/*     */ package leap.util;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderUtil
/*     */ {
/*     */   public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
/*  17 */     Tessellator tessellator = Tessellator.getInstance();
/*  18 */     WorldRenderer worldRenderer = tessellator.getWorldRenderer();
/*  19 */     worldRenderer.startDrawing(3);
/*  20 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
/*  21 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
/*  22 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
/*  23 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
/*  24 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
/*  25 */     tessellator.draw();
/*  26 */     worldRenderer.startDrawing(3);
/*  27 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
/*  28 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
/*  29 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
/*  30 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
/*  31 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
/*  32 */     tessellator.draw();
/*  33 */     worldRenderer.startDrawing(1);
/*  34 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
/*  35 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
/*  36 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
/*  37 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
/*  38 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
/*  39 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
/*  40 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
/*  41 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
/*  42 */     tessellator.draw();
/*     */   }
/*     */   
/*     */   public static void drawBoundingBox(AxisAlignedBB aa) {
/*  46 */     Tessellator tessellator = Tessellator.getInstance();
/*  47 */     WorldRenderer worldRenderer = tessellator.getWorldRenderer();
/*  48 */     worldRenderer.startDrawingQuads();
/*  49 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
/*  50 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
/*  51 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
/*  52 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
/*  53 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
/*  54 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
/*  55 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
/*  56 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
/*  57 */     tessellator.draw();
/*  58 */     worldRenderer.startDrawingQuads();
/*  59 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
/*  60 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
/*  61 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
/*  62 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
/*  63 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
/*  64 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
/*  65 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
/*  66 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
/*  67 */     tessellator.draw();
/*  68 */     worldRenderer.startDrawingQuads();
/*  69 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
/*  70 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
/*  71 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
/*  72 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
/*  73 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
/*  74 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
/*  75 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
/*  76 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
/*  77 */     tessellator.draw();
/*  78 */     worldRenderer.startDrawingQuads();
/*  79 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
/*  80 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
/*  81 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
/*  82 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
/*  83 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
/*  84 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
/*  85 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
/*  86 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
/*  87 */     tessellator.draw();
/*  88 */     worldRenderer.startDrawingQuads();
/*  89 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
/*  90 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
/*  91 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
/*  92 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
/*  93 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
/*  94 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
/*  95 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
/*  96 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
/*  97 */     tessellator.draw();
/*  98 */     worldRenderer.startDrawingQuads();
/*  99 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.maxZ);
/* 100 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.maxZ);
/* 101 */     worldRenderer.addVertex(aa.minX, aa.maxY, aa.minZ);
/* 102 */     worldRenderer.addVertex(aa.minX, aa.minY, aa.minZ);
/* 103 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.minZ);
/* 104 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.minZ);
/* 105 */     worldRenderer.addVertex(aa.maxX, aa.maxY, aa.maxZ);
/* 106 */     worldRenderer.addVertex(aa.maxX, aa.minY, aa.maxZ);
/* 107 */     tessellator.draw();
/*     */   }
/*     */   
/*     */   public static void drawOutlinedBlockESP(double x, double y, double z, float red, float green, float blue, float alpha, float lineWidth) {
/* 111 */     GL11.glPushMatrix();
/* 112 */     GL11.glEnable(3042);
/* 113 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 115 */     GL11.glDisable(3553);
/* 116 */     GL11.glEnable(2848);
/* 117 */     GL11.glDisable(2929);
/* 118 */     GL11.glDepthMask(false);
/* 119 */     GL11.glLineWidth(lineWidth);
/* 120 */     GL11.glColor4f(red, green, blue, alpha);
/* 121 */     drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
/* 122 */     GL11.glDisable(2848);
/* 123 */     GL11.glEnable(3553);
/*     */     
/* 125 */     GL11.glEnable(2929);
/* 126 */     GL11.glDepthMask(true);
/* 127 */     GL11.glDisable(3042);
/* 128 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void scissor(double x, double y, double width, double height) {
/* 133 */     Minecraft mc = Minecraft.getMinecraft();
/*     */     
/* 135 */     ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/* 136 */     double scale = sr.getScaleFactor();
/*     */     
/* 138 */     y = sr.getScaledHeight() - y;
/*     */     
/* 140 */     x *= scale;
/* 141 */     y *= scale;
/* 142 */     width *= scale;
/* 143 */     height *= scale;
/*     */     
/* 145 */     GL11.glScissor((int)x, (int)(y - height), (int)width, (int)height);
/*     */   }
/*     */   
/*     */   public static void drawBlockESP(double x, double y, double z, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWidth) {
/* 149 */     GL11.glPushMatrix();
/* 150 */     GL11.glEnable(3042);
/* 151 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 153 */     GL11.glDisable(3553);
/* 154 */     GL11.glEnable(2848);
/* 155 */     GL11.glDisable(2929);
/* 156 */     GL11.glDepthMask(false);
/* 157 */     GL11.glColor4f(red, green, blue, alpha);
/* 158 */     drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
/* 159 */     GL11.glLineWidth(lineWidth);
/* 160 */     GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
/* 161 */     drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
/* 162 */     GL11.glDisable(2848);
/* 163 */     GL11.glEnable(3553);
/*     */     
/* 165 */     GL11.glEnable(2929);
/* 166 */     GL11.glDepthMask(true);
/* 167 */     GL11.glDisable(3042);
/* 168 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawCakeESP(double x, double y, double z, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWidth) {
/* 173 */     GL11.glPushMatrix();
/* 174 */     GL11.glEnable(3042);
/* 175 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 177 */     GL11.glDisable(3553);
/* 178 */     GL11.glEnable(2848);
/* 179 */     GL11.glDisable(2929);
/* 180 */     GL11.glDepthMask(false);
/* 181 */     GL11.glColor4f(red, green, blue, alpha);
/* 182 */     drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 0.5D, z + 1.0D));
/* 183 */     GL11.glLineWidth(lineWidth);
/* 184 */     GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
/* 185 */     drawOutlinedBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 0.5D, z + 1.0D));
/* 186 */     GL11.glDisable(2848);
/* 187 */     GL11.glEnable(3553);
/*     */     
/* 189 */     GL11.glEnable(2929);
/* 190 */     GL11.glDepthMask(true);
/* 191 */     GL11.glDisable(3042);
/* 192 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void drawSolidBlockESP(double x, double y, double z, float red, float green, float blue, float alpha) {
/* 196 */     GL11.glPushMatrix();
/* 197 */     GL11.glEnable(3042);
/* 198 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 200 */     GL11.glDisable(3553);
/* 201 */     GL11.glEnable(2848);
/* 202 */     GL11.glDisable(2929);
/* 203 */     GL11.glDepthMask(false);
/* 204 */     GL11.glColor4f(red, green, blue, alpha);
/* 205 */     drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D));
/* 206 */     GL11.glDisable(2848);
/* 207 */     GL11.glEnable(3553);
/*     */     
/* 209 */     GL11.glEnable(2929);
/* 210 */     GL11.glDepthMask(true);
/* 211 */     GL11.glDisable(3042);
/* 212 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void drawOutlinedEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha) {
/* 216 */     GL11.glPushMatrix();
/* 217 */     GL11.glEnable(3042);
/* 218 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 220 */     GL11.glDisable(3553);
/* 221 */     GL11.glEnable(2848);
/* 222 */     GL11.glDisable(2929);
/* 223 */     GL11.glDepthMask(false);
/* 224 */     GL11.glColor4f(red, green, blue, alpha);
/* 225 */     drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
/* 226 */     GL11.glDisable(2848);
/* 227 */     GL11.glEnable(3553);
/*     */     
/* 229 */     GL11.glEnable(2929);
/* 230 */     GL11.glDepthMask(true);
/* 231 */     GL11.glDisable(3042);
/* 232 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void drawSolidEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha) {
/* 236 */     GL11.glPushMatrix();
/* 237 */     GL11.glEnable(3042);
/* 238 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 240 */     GL11.glDisable(3553);
/* 241 */     GL11.glEnable(2848);
/* 242 */     GL11.glDisable(2929);
/* 243 */     GL11.glDepthMask(false);
/* 244 */     GL11.glColor4f(red, green, blue, alpha);
/* 245 */     drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
/* 246 */     GL11.glDisable(2848);
/* 247 */     GL11.glEnable(3553);
/*     */     
/* 249 */     GL11.glEnable(2929);
/* 250 */     GL11.glDepthMask(true);
/* 251 */     GL11.glDisable(3042);
/* 252 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void drawEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
/* 256 */     GL11.glPushMatrix();
/* 257 */     GL11.glEnable(3042);
/* 258 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 260 */     GL11.glDisable(3553);
/* 261 */     GL11.glEnable(2848);
/* 262 */     GL11.glDisable(2929);
/* 263 */     GL11.glDepthMask(false);
/* 264 */     GL11.glColor4f(red, green, blue, alpha);
/* 265 */     drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
/* 266 */     GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
/* 267 */     drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
/* 268 */     GL11.glDisable(2848);
/* 269 */     GL11.glEnable(3553);
/*     */     
/* 271 */     GL11.glEnable(2929);
/* 272 */     GL11.glDepthMask(true);
/* 273 */     GL11.glDisable(3042);
/* 274 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void drawEntityESPIDK(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
/* 278 */     GL11.glPushMatrix();
/* 279 */     GL11.glEnable(3042);
/* 280 */     GL11.glBlendFunc(770, 771);
/*     */     
/* 282 */     GL11.glDisable(3553);
/* 283 */     GL11.glEnable(2848);
/* 284 */     GL11.glDisable(2929);
/* 285 */     GL11.glDepthMask(false);
/* 286 */     GL11.glColor4f(red, green, blue, alpha);
/* 287 */     GL11.glLineWidth(0.1F);
/* 288 */     drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
/* 289 */     GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
/* 290 */     GL11.glDisable(2848);
/* 291 */     GL11.glEnable(3553);
/*     */     
/* 293 */     GL11.glEnable(2929);
/* 294 */     GL11.glDepthMask(true);
/* 295 */     GL11.glDisable(3042);
/* 296 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void drawTracerLine(double x, double y, double z, float red, float green, float blue, float alpha, float lineWdith) {
/* 300 */     GL11.glPushMatrix();
/* 301 */     GL11.glEnable(3042);
/* 302 */     GL11.glEnable(2848);
/* 303 */     GL11.glDisable(2929);
/*     */     
/* 305 */     GL11.glDisable(3553);
/* 306 */     GL11.glBlendFunc(770, 771);
/* 307 */     GL11.glEnable(3042);
/* 308 */     GL11.glLineWidth(lineWdith);
/* 309 */     GL11.glColor4f(red, green, blue, alpha);
/* 310 */     GL11.glBegin(2);
/* 311 */     GL11.glVertex3d(0.0D, 0.0D + (Minecraft.getMinecraft()).thePlayer.getEyeHeight(), 0.0D);
/* 312 */     GL11.glVertex3d(x, y, z);
/* 313 */     GL11.glEnd();
/* 314 */     GL11.glDisable(3042);
/* 315 */     GL11.glEnable(3553);
/* 316 */     GL11.glEnable(2929);
/* 317 */     GL11.glDisable(2848);
/* 318 */     GL11.glDisable(3042);
/*     */     
/* 320 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static void drawHat(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
/* 324 */     GL11.glPushMatrix();
/* 325 */     GL11.glEnable(3042);
/* 326 */     GL11.glBlendFunc(770, 771);
/* 327 */     GL11.glDisable(3553);
/* 328 */     GL11.glEnable(2848);
/* 329 */     GL11.glDepthMask(false);
/* 330 */     GL11.glColor4f(red, green, blue, alpha);
/* 331 */     drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
/* 332 */     GL11.glLineWidth(lineWdith);
/* 333 */     GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
/* 334 */     drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
/* 335 */     GL11.glDisable(2848);
/* 336 */     GL11.glEnable(3553);
/* 337 */     GL11.glEnable(2929);
/* 338 */     GL11.glDepthMask(true);
/* 339 */     GL11.glDisable(3042);
/* 340 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawCircle(Entity entity, float partialTicks, double rad, Color color) {
/* 346 */     Minecraft mc = Minecraft.getMinecraft();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 352 */     GL11.glPushMatrix();
/* 353 */     GL11.glDisable(3553);
/* 354 */     GL11.glDisable(2929);
/* 355 */     GL11.glDepthMask(false);
/* 356 */     GL11.glLineWidth(5.0F);
/* 357 */     GL11.glBegin(3);
/*     */     
/* 359 */     mc.getRenderManager(); double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/* 360 */     mc.getRenderManager(); double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/* 361 */     mc.getRenderManager(); double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */     
/* 363 */     float r = 0.003921569F * color.getRed();
/* 364 */     float g = 0.003921569F * color.getGreen();
/* 365 */     float b = 0.003921569F * color.getBlue();
/*     */     
/* 367 */     double pix2 = 6.283185307179586D;
/*     */     
/* 369 */     for (int i = 0; i <= 60; i++) {
/* 370 */       GlStateManager.color(r, g, b, 255.0F);
/* 371 */       GL11.glVertex3d(x + rad * Math.cos(i * 6.283185307179586D / 60.0D), y, z + rad * Math.sin(i * 6.283185307179586D / 60.0D));
/*     */     } 
/*     */     
/* 374 */     GL11.glEnd();
/* 375 */     GL11.glDepthMask(true);
/* 376 */     GL11.glEnable(2929);
/* 377 */     GL11.glEnable(3553);
/* 378 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double[] interpolate(Entity entity) {
/* 383 */     Minecraft mc = Minecraft.getMinecraft();
/*     */     
/* 385 */     double partialTicks = mc.timer.renderPartialTicks;
/* 386 */     return new double[] { entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks };
/*     */   }
/*     */   
/*     */   public static double interpolate(double current, double old, double scale) {
/* 390 */     return old + (current - old) * scale;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\RenderUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */