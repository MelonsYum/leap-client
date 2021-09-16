/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.culling.ICamera;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import optifine.Config;
/*     */ import shadersmod.client.Shaders;
/*     */ 
/*     */ 
/*     */ public abstract class RenderLiving
/*     */   extends RendererLivingEntity
/*     */ {
/*     */   private static final String __OBFID = "CL_00001015";
/*     */   
/*     */   public RenderLiving(RenderManager p_i46153_1_, ModelBase p_i46153_2_, float p_i46153_3_) {
/*  22 */     super(p_i46153_1_, p_i46153_2_, p_i46153_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canRenderName(EntityLiving targetEntity) {
/*  30 */     return (super.canRenderName((EntityLivingBase)targetEntity) && (targetEntity.getAlwaysRenderNameTagForRender() || (targetEntity.hasCustomName() && targetEntity == this.renderManager.field_147941_i)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177104_a(EntityLiving p_177104_1_, ICamera p_177104_2_, double p_177104_3_, double p_177104_5_, double p_177104_7_) {
/*  35 */     if (super.func_177071_a((Entity)p_177104_1_, p_177104_2_, p_177104_3_, p_177104_5_, p_177104_7_))
/*     */     {
/*  37 */       return true;
/*     */     }
/*  39 */     if (p_177104_1_.getLeashed() && p_177104_1_.getLeashedToEntity() != null) {
/*     */       
/*  41 */       Entity var9 = p_177104_1_.getLeashedToEntity();
/*  42 */       return p_177104_2_.isBoundingBoxInFrustum(var9.getEntityBoundingBox());
/*     */     } 
/*     */ 
/*     */     
/*  46 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/*  58 */     super.doRender((EntityLivingBase)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*  59 */     func_110827_b(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177105_a(EntityLiving p_177105_1_, float p_177105_2_) {
/*  64 */     int var3 = p_177105_1_.getBrightnessForRender(p_177105_2_);
/*  65 */     int var4 = var3 % 65536;
/*  66 */     int var5 = var3 / 65536;
/*  67 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var4 / 1.0F, var5 / 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private double func_110828_a(double start, double end, double pct) {
/*  72 */     return start + (end - start) * pct;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_110827_b(EntityLiving p_110827_1_, double p_110827_2_, double p_110827_4_, double p_110827_6_, float p_110827_8_, float p_110827_9_) {
/*  77 */     if (!Config.isShaders() || !Shaders.isShadowPass) {
/*     */       
/*  79 */       Entity var10 = p_110827_1_.getLeashedToEntity();
/*     */       
/*  81 */       if (var10 != null) {
/*     */         
/*  83 */         p_110827_4_ -= (1.6D - p_110827_1_.height) * 0.5D;
/*  84 */         Tessellator var11 = Tessellator.getInstance();
/*  85 */         WorldRenderer var12 = var11.getWorldRenderer();
/*  86 */         double var13 = func_110828_a(var10.prevRotationYaw, var10.rotationYaw, (p_110827_9_ * 0.5F)) * 0.01745329238474369D;
/*  87 */         double var15 = func_110828_a(var10.prevRotationPitch, var10.rotationPitch, (p_110827_9_ * 0.5F)) * 0.01745329238474369D;
/*  88 */         double var17 = Math.cos(var13);
/*  89 */         double var19 = Math.sin(var13);
/*  90 */         double var21 = Math.sin(var15);
/*     */         
/*  92 */         if (var10 instanceof net.minecraft.entity.EntityHanging) {
/*     */           
/*  94 */           var17 = 0.0D;
/*  95 */           var19 = 0.0D;
/*  96 */           var21 = -1.0D;
/*     */         } 
/*     */         
/*  99 */         double var23 = Math.cos(var15);
/* 100 */         double var25 = func_110828_a(var10.prevPosX, var10.posX, p_110827_9_) - var17 * 0.7D - var19 * 0.5D * var23;
/* 101 */         double var27 = func_110828_a(var10.prevPosY + var10.getEyeHeight() * 0.7D, var10.posY + var10.getEyeHeight() * 0.7D, p_110827_9_) - var21 * 0.5D - 0.25D;
/* 102 */         double var29 = func_110828_a(var10.prevPosZ, var10.posZ, p_110827_9_) - var19 * 0.7D + var17 * 0.5D * var23;
/* 103 */         double var31 = func_110828_a(p_110827_1_.prevRenderYawOffset, p_110827_1_.renderYawOffset, p_110827_9_) * 0.01745329238474369D + 1.5707963267948966D;
/* 104 */         var17 = Math.cos(var31) * p_110827_1_.width * 0.4D;
/* 105 */         var19 = Math.sin(var31) * p_110827_1_.width * 0.4D;
/* 106 */         double var33 = func_110828_a(p_110827_1_.prevPosX, p_110827_1_.posX, p_110827_9_) + var17;
/* 107 */         double var35 = func_110828_a(p_110827_1_.prevPosY, p_110827_1_.posY, p_110827_9_);
/* 108 */         double var37 = func_110828_a(p_110827_1_.prevPosZ, p_110827_1_.posZ, p_110827_9_) + var19;
/* 109 */         p_110827_2_ += var17;
/* 110 */         p_110827_6_ += var19;
/* 111 */         double var39 = (float)(var25 - var33);
/* 112 */         double var41 = (float)(var27 - var35);
/* 113 */         double var43 = (float)(var29 - var37);
/* 114 */         GlStateManager.func_179090_x();
/* 115 */         GlStateManager.disableLighting();
/* 116 */         GlStateManager.disableCull();
/*     */         
/* 118 */         if (Config.isShaders())
/*     */         {
/* 120 */           Shaders.beginLeash();
/*     */         }
/*     */         
/* 123 */         boolean var45 = true;
/* 124 */         double var46 = 0.025D;
/* 125 */         var12.startDrawing(5);
/*     */         
/*     */         int var48;
/*     */         
/* 129 */         for (var48 = 0; var48 <= 24; var48++) {
/*     */           
/* 131 */           if (var48 % 2 == 0) {
/*     */             
/* 133 */             var12.func_178960_a(0.5F, 0.4F, 0.3F, 1.0F);
/*     */           }
/*     */           else {
/*     */             
/* 137 */             var12.func_178960_a(0.35F, 0.28F, 0.21000001F, 1.0F);
/*     */           } 
/*     */           
/* 140 */           float var49 = var48 / 24.0F;
/* 141 */           var12.addVertex(p_110827_2_ + var39 * var49 + 0.0D, p_110827_4_ + var41 * (var49 * var49 + var49) * 0.5D + ((24.0F - var48) / 18.0F + 0.125F), p_110827_6_ + var43 * var49);
/* 142 */           var12.addVertex(p_110827_2_ + var39 * var49 + 0.025D, p_110827_4_ + var41 * (var49 * var49 + var49) * 0.5D + ((24.0F - var48) / 18.0F + 0.125F) + 0.025D, p_110827_6_ + var43 * var49);
/*     */         } 
/*     */         
/* 145 */         var11.draw();
/* 146 */         var12.startDrawing(5);
/*     */         
/* 148 */         for (var48 = 0; var48 <= 24; var48++) {
/*     */           
/* 150 */           if (var48 % 2 == 0) {
/*     */             
/* 152 */             var12.func_178960_a(0.5F, 0.4F, 0.3F, 1.0F);
/*     */           }
/*     */           else {
/*     */             
/* 156 */             var12.func_178960_a(0.35F, 0.28F, 0.21000001F, 1.0F);
/*     */           } 
/*     */           
/* 159 */           float var49 = var48 / 24.0F;
/* 160 */           var12.addVertex(p_110827_2_ + var39 * var49 + 0.0D, p_110827_4_ + var41 * (var49 * var49 + var49) * 0.5D + ((24.0F - var48) / 18.0F + 0.125F) + 0.025D, p_110827_6_ + var43 * var49);
/* 161 */           var12.addVertex(p_110827_2_ + var39 * var49 + 0.025D, p_110827_4_ + var41 * (var49 * var49 + var49) * 0.5D + ((24.0F - var48) / 18.0F + 0.125F), p_110827_6_ + var43 * var49 + 0.025D);
/*     */         } 
/*     */         
/* 164 */         var11.draw();
/*     */         
/* 166 */         if (Config.isShaders())
/*     */         {
/* 168 */           Shaders.endLeash();
/*     */         }
/*     */         
/* 171 */         GlStateManager.enableLighting();
/* 172 */         GlStateManager.func_179098_w();
/* 173 */         GlStateManager.enableCull();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canRenderName(EntityLivingBase targetEntity) {
/* 183 */     return canRenderName((EntityLiving)targetEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 194 */     doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_177070_b(Entity p_177070_1_) {
/* 199 */     return canRenderName((EntityLiving)p_177070_1_);
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
/* 210 */     doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177071_a(Entity p_177071_1_, ICamera p_177071_2_, double p_177071_3_, double p_177071_5_, double p_177071_7_) {
/* 215 */     return func_177104_a((EntityLiving)p_177071_1_, p_177071_2_, p_177071_3_, p_177071_5_, p_177071_7_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderLiving.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */