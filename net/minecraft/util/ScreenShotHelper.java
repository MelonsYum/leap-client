/*     */ package net.minecraft.util;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.nio.IntBuffer;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.texture.TextureUtil;
/*     */ import net.minecraft.client.shader.Framebuffer;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class ScreenShotHelper
/*     */ {
/*  23 */   private static final Logger logger = LogManager.getLogger();
/*  24 */   private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
/*     */ 
/*     */ 
/*     */   
/*     */   private static IntBuffer pixelBuffer;
/*     */ 
/*     */ 
/*     */   
/*     */   private static int[] pixelValues;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000656";
/*     */ 
/*     */ 
/*     */   
/*     */   public static IChatComponent saveScreenshot(File p_148260_0_, int p_148260_1_, int p_148260_2_, Framebuffer p_148260_3_) {
/*  41 */     return saveScreenshot(p_148260_0_, null, p_148260_1_, p_148260_2_, p_148260_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IChatComponent saveScreenshot(File p_148259_0_, String p_148259_1_, int p_148259_2_, int p_148259_3_, Framebuffer p_148259_4_) {
/*     */     try {
/*  52 */       File var12, var5 = new File(p_148259_0_, "screenshots");
/*  53 */       var5.mkdir();
/*     */       
/*  55 */       if (OpenGlHelper.isFramebufferEnabled()) {
/*     */         
/*  57 */         p_148259_2_ = p_148259_4_.framebufferTextureWidth;
/*  58 */         p_148259_3_ = p_148259_4_.framebufferTextureHeight;
/*     */       } 
/*     */       
/*  61 */       int var6 = p_148259_2_ * p_148259_3_;
/*     */       
/*  63 */       if (pixelBuffer == null || pixelBuffer.capacity() < var6) {
/*     */         
/*  65 */         pixelBuffer = BufferUtils.createIntBuffer(var6);
/*  66 */         pixelValues = new int[var6];
/*     */       } 
/*     */       
/*  69 */       GL11.glPixelStorei(3333, 1);
/*  70 */       GL11.glPixelStorei(3317, 1);
/*  71 */       pixelBuffer.clear();
/*     */       
/*  73 */       if (OpenGlHelper.isFramebufferEnabled()) {
/*     */         
/*  75 */         GlStateManager.func_179144_i(p_148259_4_.framebufferTexture);
/*  76 */         GL11.glGetTexImage(3553, 0, 32993, 33639, pixelBuffer);
/*     */       }
/*     */       else {
/*     */         
/*  80 */         GL11.glReadPixels(0, 0, p_148259_2_, p_148259_3_, 32993, 33639, pixelBuffer);
/*     */       } 
/*     */       
/*  83 */       pixelBuffer.get(pixelValues);
/*  84 */       TextureUtil.func_147953_a(pixelValues, p_148259_2_, p_148259_3_);
/*  85 */       BufferedImage var7 = null;
/*     */       
/*  87 */       if (OpenGlHelper.isFramebufferEnabled()) {
/*     */         
/*  89 */         var7 = new BufferedImage(p_148259_4_.framebufferWidth, p_148259_4_.framebufferHeight, 1);
/*  90 */         int var8 = p_148259_4_.framebufferTextureHeight - p_148259_4_.framebufferHeight;
/*     */         
/*  92 */         for (int var9 = var8; var9 < p_148259_4_.framebufferTextureHeight; var9++)
/*     */         {
/*  94 */           for (int var10 = 0; var10 < p_148259_4_.framebufferWidth; var10++)
/*     */           {
/*  96 */             var7.setRGB(var10, var9 - var8, pixelValues[var9 * p_148259_4_.framebufferTextureWidth + var10]);
/*     */           }
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 102 */         var7 = new BufferedImage(p_148259_2_, p_148259_3_, 1);
/* 103 */         var7.setRGB(0, 0, p_148259_2_, p_148259_3_, pixelValues, 0, p_148259_2_);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 108 */       if (p_148259_1_ == null) {
/*     */         
/* 110 */         var12 = getTimestampedPNGFileForDirectory(var5);
/*     */       }
/*     */       else {
/*     */         
/* 114 */         var12 = new File(var5, p_148259_1_);
/*     */       } 
/*     */       
/* 117 */       ImageIO.write(var7, "png", var12);
/* 118 */       ChatComponentText var13 = new ChatComponentText(var12.getName());
/* 119 */       var13.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, var12.getAbsolutePath()));
/* 120 */       var13.getChatStyle().setUnderlined(Boolean.valueOf(true));
/* 121 */       return new ChatComponentTranslation("screenshot.success", new Object[] { var13 });
/*     */     }
/* 123 */     catch (Exception var11) {
/*     */       
/* 125 */       logger.warn("Couldn't save screenshot", var11);
/* 126 */       return new ChatComponentTranslation("screenshot.failure", new Object[] { var11.getMessage() });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static File getTimestampedPNGFileForDirectory(File p_74290_0_) {
/* 138 */     String var2 = dateFormat.format(new Date()).toString();
/* 139 */     int var3 = 1;
/*     */ 
/*     */     
/*     */     while (true) {
/* 143 */       File var1 = new File(p_74290_0_, String.valueOf(var2) + ((var3 == 1) ? "" : ("_" + var3)) + ".png");
/*     */       
/* 145 */       if (!var1.exists())
/*     */       {
/* 147 */         return var1;
/*     */       }
/*     */       
/* 150 */       var3++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\ScreenShotHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */