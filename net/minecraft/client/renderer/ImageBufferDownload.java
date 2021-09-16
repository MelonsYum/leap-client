/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferInt;
/*     */ 
/*     */ 
/*     */ public class ImageBufferDownload
/*     */   implements IImageBuffer
/*     */ {
/*     */   private int[] imageData;
/*     */   private int imageWidth;
/*     */   private int imageHeight;
/*     */   
/*     */   public BufferedImage parseUserSkin(BufferedImage p_78432_1_) {
/*  16 */     if (p_78432_1_ == null)
/*     */     {
/*  18 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  22 */     this.imageWidth = 64;
/*  23 */     this.imageHeight = 64;
/*  24 */     int srcWidth = p_78432_1_.getWidth();
/*  25 */     int srcHeight = p_78432_1_.getHeight();
/*     */     
/*     */     int k;
/*  28 */     for (k = 1; this.imageWidth < srcWidth || this.imageHeight < srcHeight; k *= 2) {
/*     */       
/*  30 */       this.imageWidth *= 2;
/*  31 */       this.imageHeight *= 2;
/*     */     } 
/*     */     
/*  34 */     BufferedImage var2 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
/*  35 */     Graphics var3 = var2.getGraphics();
/*  36 */     var3.drawImage(p_78432_1_, 0, 0, null);
/*     */     
/*  38 */     if (p_78432_1_.getHeight() == 32 * k) {
/*     */       
/*  40 */       var3.drawImage(var2, 24 * k, 48 * k, 20 * k, 52 * k, 4 * k, 16 * k, 8 * k, 20 * k, null);
/*  41 */       var3.drawImage(var2, 28 * k, 48 * k, 24 * k, 52 * k, 8 * k, 16 * k, 12 * k, 20 * k, null);
/*  42 */       var3.drawImage(var2, 20 * k, 52 * k, 16 * k, 64 * k, 8 * k, 20 * k, 12 * k, 32 * k, null);
/*  43 */       var3.drawImage(var2, 24 * k, 52 * k, 20 * k, 64 * k, 4 * k, 20 * k, 8 * k, 32 * k, null);
/*  44 */       var3.drawImage(var2, 28 * k, 52 * k, 24 * k, 64 * k, 0 * k, 20 * k, 4 * k, 32 * k, null);
/*  45 */       var3.drawImage(var2, 32 * k, 52 * k, 28 * k, 64 * k, 12 * k, 20 * k, 16 * k, 32 * k, null);
/*  46 */       var3.drawImage(var2, 40 * k, 48 * k, 36 * k, 52 * k, 44 * k, 16 * k, 48 * k, 20 * k, null);
/*  47 */       var3.drawImage(var2, 44 * k, 48 * k, 40 * k, 52 * k, 48 * k, 16 * k, 52 * k, 20 * k, null);
/*  48 */       var3.drawImage(var2, 36 * k, 52 * k, 32 * k, 64 * k, 48 * k, 20 * k, 52 * k, 32 * k, null);
/*  49 */       var3.drawImage(var2, 40 * k, 52 * k, 36 * k, 64 * k, 44 * k, 20 * k, 48 * k, 32 * k, null);
/*  50 */       var3.drawImage(var2, 44 * k, 52 * k, 40 * k, 64 * k, 40 * k, 20 * k, 44 * k, 32 * k, null);
/*  51 */       var3.drawImage(var2, 48 * k, 52 * k, 44 * k, 64 * k, 52 * k, 20 * k, 56 * k, 32 * k, null);
/*     */     } 
/*     */     
/*  54 */     var3.dispose();
/*  55 */     this.imageData = ((DataBufferInt)var2.getRaster().getDataBuffer()).getData();
/*  56 */     setAreaOpaque(0, 0, 32 * k, 16 * k);
/*  57 */     setAreaTransparent(32 * k, 0, 64 * k, 32 * k);
/*  58 */     setAreaOpaque(0, 16 * k, 64 * k, 32 * k);
/*  59 */     setAreaTransparent(0, 32 * k, 16 * k, 48 * k);
/*  60 */     setAreaTransparent(16 * k, 32 * k, 40 * k, 48 * k);
/*  61 */     setAreaTransparent(40 * k, 32 * k, 56 * k, 48 * k);
/*  62 */     setAreaTransparent(0, 48 * k, 16 * k, 64 * k);
/*  63 */     setAreaOpaque(16 * k, 48 * k, 48 * k, 64 * k);
/*  64 */     setAreaTransparent(48 * k, 48 * k, 64 * k, 64 * k);
/*  65 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_152634_a() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setAreaTransparent(int p_78434_1_, int p_78434_2_, int p_78434_3_, int p_78434_4_) {
/*  78 */     if (!hasTransparency(p_78434_1_, p_78434_2_, p_78434_3_, p_78434_4_))
/*     */     {
/*  80 */       for (int var5 = p_78434_1_; var5 < p_78434_3_; var5++) {
/*     */         
/*  82 */         for (int var6 = p_78434_2_; var6 < p_78434_4_; var6++)
/*     */         {
/*  84 */           this.imageData[var5 + var6 * this.imageWidth] = this.imageData[var5 + var6 * this.imageWidth] & 0xFFFFFF;
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setAreaOpaque(int p_78433_1_, int p_78433_2_, int p_78433_3_, int p_78433_4_) {
/*  95 */     for (int var5 = p_78433_1_; var5 < p_78433_3_; var5++) {
/*     */       
/*  97 */       for (int var6 = p_78433_2_; var6 < p_78433_4_; var6++)
/*     */       {
/*  99 */         this.imageData[var5 + var6 * this.imageWidth] = this.imageData[var5 + var6 * this.imageWidth] | 0xFF000000;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasTransparency(int p_78435_1_, int p_78435_2_, int p_78435_3_, int p_78435_4_) {
/* 109 */     for (int var5 = p_78435_1_; var5 < p_78435_3_; var5++) {
/*     */       
/* 111 */       for (int var6 = p_78435_2_; var6 < p_78435_4_; var6++) {
/*     */         
/* 113 */         int var7 = this.imageData[var5 + var6 * this.imageWidth];
/*     */         
/* 115 */         if ((var7 >> 24 & 0xFF) < 128)
/*     */         {
/* 117 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\ImageBufferDownload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */