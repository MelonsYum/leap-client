/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelGuardian;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.culling.ICamera;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityGuardian;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderGuardian extends RenderLiving {
/*  20 */   private static final ResourceLocation field_177114_e = new ResourceLocation("textures/entity/guardian.png");
/*  21 */   private static final ResourceLocation field_177116_j = new ResourceLocation("textures/entity/guardian_elder.png");
/*  22 */   private static final ResourceLocation field_177117_k = new ResourceLocation("textures/entity/guardian_beam.png");
/*     */   
/*     */   int field_177115_a;
/*     */   private static final String __OBFID = "CL_00002443";
/*     */   
/*     */   public RenderGuardian(RenderManager p_i46171_1_) {
/*  28 */     super(p_i46171_1_, (ModelBase)new ModelGuardian(), 0.5F);
/*  29 */     this.field_177115_a = ((ModelGuardian)this.mainModel).func_178706_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177113_a(EntityGuardian p_177113_1_, ICamera p_177113_2_, double p_177113_3_, double p_177113_5_, double p_177113_7_) {
/*  34 */     if (super.func_177104_a((EntityLiving)p_177113_1_, p_177113_2_, p_177113_3_, p_177113_5_, p_177113_7_))
/*     */     {
/*  36 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  40 */     if (p_177113_1_.func_175474_cn()) {
/*     */       
/*  42 */       EntityLivingBase var9 = p_177113_1_.func_175466_co();
/*     */       
/*  44 */       if (var9 != null) {
/*     */         
/*  46 */         Vec3 var10 = func_177110_a(var9, var9.height * 0.5D, 1.0F);
/*  47 */         Vec3 var11 = func_177110_a((EntityLivingBase)p_177113_1_, p_177113_1_.getEyeHeight(), 1.0F);
/*     */         
/*  49 */         if (p_177113_2_.isBoundingBoxInFrustum(AxisAlignedBB.fromBounds(var11.xCoord, var11.yCoord, var11.zCoord, var10.xCoord, var10.yCoord, var10.zCoord)))
/*     */         {
/*  51 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  56 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Vec3 func_177110_a(EntityLivingBase p_177110_1_, double p_177110_2_, float p_177110_4_) {
/*  62 */     double var5 = p_177110_1_.lastTickPosX + (p_177110_1_.posX - p_177110_1_.lastTickPosX) * p_177110_4_;
/*  63 */     double var7 = p_177110_2_ + p_177110_1_.lastTickPosY + (p_177110_1_.posY - p_177110_1_.lastTickPosY) * p_177110_4_;
/*  64 */     double var9 = p_177110_1_.lastTickPosZ + (p_177110_1_.posZ - p_177110_1_.lastTickPosZ) * p_177110_4_;
/*  65 */     return new Vec3(var5, var7, var9);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177109_a(EntityGuardian p_177109_1_, double p_177109_2_, double p_177109_4_, double p_177109_6_, float p_177109_8_, float p_177109_9_) {
/*  70 */     if (this.field_177115_a != ((ModelGuardian)this.mainModel).func_178706_a()) {
/*     */       
/*  72 */       this.mainModel = (ModelBase)new ModelGuardian();
/*  73 */       this.field_177115_a = ((ModelGuardian)this.mainModel).func_178706_a();
/*     */     } 
/*     */     
/*  76 */     super.doRender((EntityLiving)p_177109_1_, p_177109_2_, p_177109_4_, p_177109_6_, p_177109_8_, p_177109_9_);
/*  77 */     EntityLivingBase var10 = p_177109_1_.func_175466_co();
/*     */     
/*  79 */     if (var10 != null) {
/*     */       
/*  81 */       float var11 = p_177109_1_.func_175477_p(p_177109_9_);
/*  82 */       Tessellator var12 = Tessellator.getInstance();
/*  83 */       WorldRenderer var13 = var12.getWorldRenderer();
/*  84 */       bindTexture(field_177117_k);
/*  85 */       GL11.glTexParameterf(3553, 10242, 10497.0F);
/*  86 */       GL11.glTexParameterf(3553, 10243, 10497.0F);
/*  87 */       GlStateManager.disableLighting();
/*  88 */       GlStateManager.disableCull();
/*  89 */       GlStateManager.disableBlend();
/*  90 */       GlStateManager.depthMask(true);
/*  91 */       float var14 = 240.0F;
/*  92 */       OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var14, var14);
/*  93 */       GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
/*  94 */       float var15 = (float)p_177109_1_.worldObj.getTotalWorldTime() + p_177109_9_;
/*  95 */       float var16 = var15 * 0.5F % 1.0F;
/*  96 */       float var17 = p_177109_1_.getEyeHeight();
/*  97 */       GlStateManager.pushMatrix();
/*  98 */       GlStateManager.translate((float)p_177109_2_, (float)p_177109_4_ + var17, (float)p_177109_6_);
/*  99 */       Vec3 var18 = func_177110_a(var10, var10.height * 0.5D, p_177109_9_);
/* 100 */       Vec3 var19 = func_177110_a((EntityLivingBase)p_177109_1_, var17, p_177109_9_);
/* 101 */       Vec3 var20 = var18.subtract(var19);
/* 102 */       double var21 = var20.lengthVector() + 1.0D;
/* 103 */       var20 = var20.normalize();
/* 104 */       float var23 = (float)Math.acos(var20.yCoord);
/* 105 */       float var24 = (float)Math.atan2(var20.zCoord, var20.xCoord);
/* 106 */       GlStateManager.rotate((1.5707964F + -var24) * 57.295776F, 0.0F, 1.0F, 0.0F);
/* 107 */       GlStateManager.rotate(var23 * 57.295776F, 1.0F, 0.0F, 0.0F);
/* 108 */       byte var25 = 1;
/* 109 */       double var26 = var15 * 0.05D * (1.0D - (var25 & 0x1) * 2.5D);
/* 110 */       var13.startDrawingQuads();
/* 111 */       float var28 = var11 * var11;
/* 112 */       var13.func_178961_b(64 + (int)(var28 * 240.0F), 32 + (int)(var28 * 192.0F), 128 - (int)(var28 * 64.0F), 255);
/* 113 */       double var29 = var25 * 0.2D;
/* 114 */       double var31 = var29 * 1.41D;
/* 115 */       double var33 = 0.0D + Math.cos(var26 + 2.356194490192345D) * var31;
/* 116 */       double var35 = 0.0D + Math.sin(var26 + 2.356194490192345D) * var31;
/* 117 */       double var37 = 0.0D + Math.cos(var26 + 0.7853981633974483D) * var31;
/* 118 */       double var39 = 0.0D + Math.sin(var26 + 0.7853981633974483D) * var31;
/* 119 */       double var41 = 0.0D + Math.cos(var26 + 3.9269908169872414D) * var31;
/* 120 */       double var43 = 0.0D + Math.sin(var26 + 3.9269908169872414D) * var31;
/* 121 */       double var45 = 0.0D + Math.cos(var26 + 5.497787143782138D) * var31;
/* 122 */       double var47 = 0.0D + Math.sin(var26 + 5.497787143782138D) * var31;
/* 123 */       double var49 = 0.0D + Math.cos(var26 + Math.PI) * var29;
/* 124 */       double var51 = 0.0D + Math.sin(var26 + Math.PI) * var29;
/* 125 */       double var53 = 0.0D + Math.cos(var26 + 0.0D) * var29;
/* 126 */       double var55 = 0.0D + Math.sin(var26 + 0.0D) * var29;
/* 127 */       double var57 = 0.0D + Math.cos(var26 + 1.5707963267948966D) * var29;
/* 128 */       double var59 = 0.0D + Math.sin(var26 + 1.5707963267948966D) * var29;
/* 129 */       double var61 = 0.0D + Math.cos(var26 + 4.71238898038469D) * var29;
/* 130 */       double var63 = 0.0D + Math.sin(var26 + 4.71238898038469D) * var29;
/* 131 */       double var67 = 0.0D;
/* 132 */       double var69 = 0.4999D;
/* 133 */       double var71 = (-1.0F + var16);
/* 134 */       double var73 = var21 * 0.5D / var29 + var71;
/* 135 */       var13.addVertexWithUV(var49, var21, var51, var69, var73);
/* 136 */       var13.addVertexWithUV(var49, 0.0D, var51, var69, var71);
/* 137 */       var13.addVertexWithUV(var53, 0.0D, var55, var67, var71);
/* 138 */       var13.addVertexWithUV(var53, var21, var55, var67, var73);
/* 139 */       var13.addVertexWithUV(var57, var21, var59, var69, var73);
/* 140 */       var13.addVertexWithUV(var57, 0.0D, var59, var69, var71);
/* 141 */       var13.addVertexWithUV(var61, 0.0D, var63, var67, var71);
/* 142 */       var13.addVertexWithUV(var61, var21, var63, var67, var73);
/* 143 */       double var75 = 0.0D;
/*     */       
/* 145 */       if (p_177109_1_.ticksExisted % 2 == 0)
/*     */       {
/* 147 */         var75 = 0.5D;
/*     */       }
/*     */       
/* 150 */       var13.addVertexWithUV(var33, var21, var35, 0.5D, var75 + 0.5D);
/* 151 */       var13.addVertexWithUV(var37, var21, var39, 1.0D, var75 + 0.5D);
/* 152 */       var13.addVertexWithUV(var45, var21, var47, 1.0D, var75);
/* 153 */       var13.addVertexWithUV(var41, var21, var43, 0.5D, var75);
/* 154 */       var12.draw();
/* 155 */       GlStateManager.popMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_177112_a(EntityGuardian p_177112_1_, float p_177112_2_) {
/* 161 */     if (p_177112_1_.func_175461_cl())
/*     */     {
/* 163 */       GlStateManager.scale(2.35F, 2.35F, 2.35F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_177111_a(EntityGuardian p_177111_1_) {
/* 169 */     return p_177111_1_.func_175461_cl() ? field_177116_j : field_177114_e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 180 */     func_177109_a((EntityGuardian)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177104_a(EntityLiving p_177104_1_, ICamera p_177104_2_, double p_177104_3_, double p_177104_5_, double p_177104_7_) {
/* 185 */     return func_177113_a((EntityGuardian)p_177104_1_, p_177104_2_, p_177104_3_, p_177104_5_, p_177104_7_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
/* 194 */     func_177112_a((EntityGuardian)p_77041_1_, p_77041_2_);
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
/* 205 */     func_177109_a((EntityGuardian)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 213 */     return func_177111_a((EntityGuardian)p_110775_1_);
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
/* 224 */     func_177109_a((EntityGuardian)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177071_a(Entity p_177071_1_, ICamera p_177071_2_, double p_177071_3_, double p_177071_5_, double p_177071_7_) {
/* 229 */     return func_177113_a((EntityGuardian)p_177071_1_, p_177071_2_, p_177071_3_, p_177071_5_, p_177071_7_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */