/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class RenderLightningBolt
/*     */   extends Render
/*     */ {
/*     */   private static final String __OBFID = "CL_00001011";
/*     */   
/*     */   public RenderLightningBolt(RenderManager p_i46157_1_) {
/*  17 */     super(p_i46157_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLightningBolt p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/*  28 */     Tessellator var10 = Tessellator.getInstance();
/*  29 */     WorldRenderer var11 = var10.getWorldRenderer();
/*  30 */     GlStateManager.func_179090_x();
/*  31 */     GlStateManager.disableLighting();
/*  32 */     GlStateManager.enableBlend();
/*  33 */     GlStateManager.blendFunc(770, 1);
/*  34 */     double[] var12 = new double[8];
/*  35 */     double[] var13 = new double[8];
/*  36 */     double var14 = 0.0D;
/*  37 */     double var16 = 0.0D;
/*  38 */     Random var18 = new Random(p_76986_1_.boltVertex);
/*     */     
/*  40 */     for (int var19 = 7; var19 >= 0; var19--) {
/*     */       
/*  42 */       var12[var19] = var14;
/*  43 */       var13[var19] = var16;
/*  44 */       var14 += (var18.nextInt(11) - 5);
/*  45 */       var16 += (var18.nextInt(11) - 5);
/*     */     } 
/*     */     
/*  48 */     for (int var46 = 0; var46 < 4; var46++) {
/*     */       
/*  50 */       Random var47 = new Random(p_76986_1_.boltVertex);
/*     */       
/*  52 */       for (int var20 = 0; var20 < 3; var20++) {
/*     */         
/*  54 */         int var21 = 7;
/*  55 */         int var22 = 0;
/*     */         
/*  57 */         if (var20 > 0)
/*     */         {
/*  59 */           var21 = 7 - var20;
/*     */         }
/*     */         
/*  62 */         if (var20 > 0)
/*     */         {
/*  64 */           var22 = var21 - 2;
/*     */         }
/*     */         
/*  67 */         double var23 = var12[var21] - var14;
/*  68 */         double var25 = var13[var21] - var16;
/*     */         
/*  70 */         for (int var27 = var21; var27 >= var22; var27--) {
/*     */           
/*  72 */           double var28 = var23;
/*  73 */           double var30 = var25;
/*     */           
/*  75 */           if (var20 == 0) {
/*     */             
/*  77 */             var23 += (var47.nextInt(11) - 5);
/*  78 */             var25 += (var47.nextInt(11) - 5);
/*     */           }
/*     */           else {
/*     */             
/*  82 */             var23 += (var47.nextInt(31) - 15);
/*  83 */             var25 += (var47.nextInt(31) - 15);
/*     */           } 
/*     */           
/*  86 */           var11.startDrawing(5);
/*  87 */           float var32 = 0.5F;
/*  88 */           var11.func_178960_a(0.9F * var32, 0.9F * var32, 1.0F * var32, 0.3F);
/*  89 */           double var33 = 0.1D + var46 * 0.2D;
/*     */           
/*  91 */           if (var20 == 0)
/*     */           {
/*  93 */             var33 *= var27 * 0.1D + 1.0D;
/*     */           }
/*     */           
/*  96 */           double var35 = 0.1D + var46 * 0.2D;
/*     */           
/*  98 */           if (var20 == 0)
/*     */           {
/* 100 */             var35 *= (var27 - 1) * 0.1D + 1.0D;
/*     */           }
/*     */           
/* 103 */           for (int var37 = 0; var37 < 5; var37++) {
/*     */             
/* 105 */             double var38 = p_76986_2_ + 0.5D - var33;
/* 106 */             double var40 = p_76986_6_ + 0.5D - var33;
/*     */             
/* 108 */             if (var37 == 1 || var37 == 2)
/*     */             {
/* 110 */               var38 += var33 * 2.0D;
/*     */             }
/*     */             
/* 113 */             if (var37 == 2 || var37 == 3)
/*     */             {
/* 115 */               var40 += var33 * 2.0D;
/*     */             }
/*     */             
/* 118 */             double var42 = p_76986_2_ + 0.5D - var35;
/* 119 */             double var44 = p_76986_6_ + 0.5D - var35;
/*     */             
/* 121 */             if (var37 == 1 || var37 == 2)
/*     */             {
/* 123 */               var42 += var35 * 2.0D;
/*     */             }
/*     */             
/* 126 */             if (var37 == 2 || var37 == 3)
/*     */             {
/* 128 */               var44 += var35 * 2.0D;
/*     */             }
/*     */             
/* 131 */             var11.addVertex(var42 + var23, p_76986_4_ + (var27 * 16), var44 + var25);
/* 132 */             var11.addVertex(var38 + var28, p_76986_4_ + ((var27 + 1) * 16), var40 + var30);
/*     */           } 
/*     */           
/* 135 */           var10.draw();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 140 */     GlStateManager.disableBlend();
/* 141 */     GlStateManager.enableLighting();
/* 142 */     GlStateManager.func_179098_w();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityLightningBolt p_110775_1_) {
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 158 */     return getEntityTexture((EntityLightningBolt)p_110775_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 169 */     doRender((EntityLightningBolt)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderLightningBolt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */