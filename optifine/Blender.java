/*     */ package optifine;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ 
/*     */ 
/*     */ public class Blender
/*     */ {
/*     */   public static final int BLEND_ALPHA = 0;
/*     */   public static final int BLEND_ADD = 1;
/*     */   public static final int BLEND_SUBSTRACT = 2;
/*     */   public static final int BLEND_MULTIPLY = 3;
/*     */   public static final int BLEND_DODGE = 4;
/*     */   public static final int BLEND_BURN = 5;
/*     */   public static final int BLEND_SCREEN = 6;
/*     */   public static final int BLEND_OVERLAY = 7;
/*     */   public static final int BLEND_REPLACE = 8;
/*     */   public static final int BLEND_DEFAULT = 1;
/*     */   
/*     */   public static int parseBlend(String str) {
/*  20 */     if (str == null)
/*     */     {
/*  22 */       return 1;
/*     */     }
/*     */ 
/*     */     
/*  26 */     str = str.toLowerCase().trim();
/*     */     
/*  28 */     if (str.equals("alpha"))
/*     */     {
/*  30 */       return 0;
/*     */     }
/*  32 */     if (str.equals("add"))
/*     */     {
/*  34 */       return 1;
/*     */     }
/*  36 */     if (str.equals("subtract"))
/*     */     {
/*  38 */       return 2;
/*     */     }
/*  40 */     if (str.equals("multiply"))
/*     */     {
/*  42 */       return 3;
/*     */     }
/*  44 */     if (str.equals("dodge"))
/*     */     {
/*  46 */       return 4;
/*     */     }
/*  48 */     if (str.equals("burn"))
/*     */     {
/*  50 */       return 5;
/*     */     }
/*  52 */     if (str.equals("screen"))
/*     */     {
/*  54 */       return 6;
/*     */     }
/*  56 */     if (str.equals("overlay"))
/*     */     {
/*  58 */       return 7;
/*     */     }
/*  60 */     if (str.equals("replace"))
/*     */     {
/*  62 */       return 8;
/*     */     }
/*     */ 
/*     */     
/*  66 */     Config.warn("Unknown blend: " + str);
/*  67 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setupBlend(int blend, float brightness) {
/*  74 */     switch (blend) {
/*     */       
/*     */       case 0:
/*  77 */         GlStateManager.disableAlpha();
/*  78 */         GlStateManager.enableBlend();
/*  79 */         GlStateManager.blendFunc(770, 771);
/*  80 */         GlStateManager.color(1.0F, 1.0F, 1.0F, brightness);
/*     */         break;
/*     */       
/*     */       case 1:
/*  84 */         GlStateManager.disableAlpha();
/*  85 */         GlStateManager.enableBlend();
/*  86 */         GlStateManager.blendFunc(770, 1);
/*  87 */         GlStateManager.color(1.0F, 1.0F, 1.0F, brightness);
/*     */         break;
/*     */       
/*     */       case 2:
/*  91 */         GlStateManager.disableAlpha();
/*  92 */         GlStateManager.enableBlend();
/*  93 */         GlStateManager.blendFunc(775, 0);
/*  94 */         GlStateManager.color(brightness, brightness, brightness, 1.0F);
/*     */         break;
/*     */       
/*     */       case 3:
/*  98 */         GlStateManager.disableAlpha();
/*  99 */         GlStateManager.enableBlend();
/* 100 */         GlStateManager.blendFunc(774, 771);
/* 101 */         GlStateManager.color(brightness, brightness, brightness, brightness);
/*     */         break;
/*     */       
/*     */       case 4:
/* 105 */         GlStateManager.disableAlpha();
/* 106 */         GlStateManager.enableBlend();
/* 107 */         GlStateManager.blendFunc(1, 1);
/* 108 */         GlStateManager.color(brightness, brightness, brightness, 1.0F);
/*     */         break;
/*     */       
/*     */       case 5:
/* 112 */         GlStateManager.disableAlpha();
/* 113 */         GlStateManager.enableBlend();
/* 114 */         GlStateManager.blendFunc(0, 769);
/* 115 */         GlStateManager.color(brightness, brightness, brightness, 1.0F);
/*     */         break;
/*     */       
/*     */       case 6:
/* 119 */         GlStateManager.disableAlpha();
/* 120 */         GlStateManager.enableBlend();
/* 121 */         GlStateManager.blendFunc(1, 769);
/* 122 */         GlStateManager.color(brightness, brightness, brightness, 1.0F);
/*     */         break;
/*     */       
/*     */       case 7:
/* 126 */         GlStateManager.disableAlpha();
/* 127 */         GlStateManager.enableBlend();
/* 128 */         GlStateManager.blendFunc(774, 768);
/* 129 */         GlStateManager.color(brightness, brightness, brightness, 1.0F);
/*     */         break;
/*     */       
/*     */       case 8:
/* 133 */         GlStateManager.enableAlpha();
/* 134 */         GlStateManager.disableBlend();
/* 135 */         GlStateManager.color(1.0F, 1.0F, 1.0F, brightness);
/*     */         break;
/*     */     } 
/* 138 */     GlStateManager.func_179098_w();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void clearBlend(float rainBrightness) {
/* 143 */     GlStateManager.disableAlpha();
/* 144 */     GlStateManager.enableBlend();
/* 145 */     GlStateManager.blendFunc(770, 1);
/* 146 */     GlStateManager.color(1.0F, 1.0F, 1.0F, rainBrightness);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\Blender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */