/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityPainting;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class RenderPainting
/*     */   extends Render {
/*  16 */   private static final ResourceLocation field_110807_a = new ResourceLocation("textures/painting/paintings_kristoffer_zetterstrand.png");
/*     */   
/*     */   private static final String __OBFID = "CL_00001018";
/*     */   
/*     */   public RenderPainting(RenderManager p_i46150_1_) {
/*  21 */     super(p_i46150_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityPainting p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/*  32 */     GlStateManager.pushMatrix();
/*  33 */     GlStateManager.translate(p_76986_2_, p_76986_4_, p_76986_6_);
/*  34 */     GlStateManager.rotate(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
/*  35 */     GlStateManager.enableRescaleNormal();
/*  36 */     bindEntityTexture((Entity)p_76986_1_);
/*  37 */     EntityPainting.EnumArt var10 = p_76986_1_.art;
/*  38 */     float var11 = 0.0625F;
/*  39 */     GlStateManager.scale(var11, var11, var11);
/*  40 */     func_77010_a(p_76986_1_, var10.sizeX, var10.sizeY, var10.offsetX, var10.offsetY);
/*  41 */     GlStateManager.disableRescaleNormal();
/*  42 */     GlStateManager.popMatrix();
/*  43 */     super.doRender((Entity)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ResourceLocation func_180562_a(EntityPainting p_180562_1_) {
/*  48 */     return field_110807_a;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_77010_a(EntityPainting p_77010_1_, int p_77010_2_, int p_77010_3_, int p_77010_4_, int p_77010_5_) {
/*  53 */     float var6 = -p_77010_2_ / 2.0F;
/*  54 */     float var7 = -p_77010_3_ / 2.0F;
/*  55 */     float var8 = 0.5F;
/*  56 */     float var9 = 0.75F;
/*  57 */     float var10 = 0.8125F;
/*  58 */     float var11 = 0.0F;
/*  59 */     float var12 = 0.0625F;
/*  60 */     float var13 = 0.75F;
/*  61 */     float var14 = 0.8125F;
/*  62 */     float var15 = 0.001953125F;
/*  63 */     float var16 = 0.001953125F;
/*  64 */     float var17 = 0.7519531F;
/*  65 */     float var18 = 0.7519531F;
/*  66 */     float var19 = 0.0F;
/*  67 */     float var20 = 0.0625F;
/*     */     
/*  69 */     for (int var21 = 0; var21 < p_77010_2_ / 16; var21++) {
/*     */       
/*  71 */       for (int var22 = 0; var22 < p_77010_3_ / 16; var22++) {
/*     */         
/*  73 */         float var23 = var6 + ((var21 + 1) * 16);
/*  74 */         float var24 = var6 + (var21 * 16);
/*  75 */         float var25 = var7 + ((var22 + 1) * 16);
/*  76 */         float var26 = var7 + (var22 * 16);
/*  77 */         func_77008_a(p_77010_1_, (var23 + var24) / 2.0F, (var25 + var26) / 2.0F);
/*  78 */         float var27 = (p_77010_4_ + p_77010_2_ - var21 * 16) / 256.0F;
/*  79 */         float var28 = (p_77010_4_ + p_77010_2_ - (var21 + 1) * 16) / 256.0F;
/*  80 */         float var29 = (p_77010_5_ + p_77010_3_ - var22 * 16) / 256.0F;
/*  81 */         float var30 = (p_77010_5_ + p_77010_3_ - (var22 + 1) * 16) / 256.0F;
/*  82 */         Tessellator var31 = Tessellator.getInstance();
/*  83 */         WorldRenderer var32 = var31.getWorldRenderer();
/*  84 */         var32.startDrawingQuads();
/*  85 */         var32.func_178980_d(0.0F, 0.0F, -1.0F);
/*  86 */         var32.addVertexWithUV(var23, var26, -var8, var28, var29);
/*  87 */         var32.addVertexWithUV(var24, var26, -var8, var27, var29);
/*  88 */         var32.addVertexWithUV(var24, var25, -var8, var27, var30);
/*  89 */         var32.addVertexWithUV(var23, var25, -var8, var28, var30);
/*  90 */         var32.func_178980_d(0.0F, 0.0F, 1.0F);
/*  91 */         var32.addVertexWithUV(var23, var25, var8, var9, var11);
/*  92 */         var32.addVertexWithUV(var24, var25, var8, var10, var11);
/*  93 */         var32.addVertexWithUV(var24, var26, var8, var10, var12);
/*  94 */         var32.addVertexWithUV(var23, var26, var8, var9, var12);
/*  95 */         var32.func_178980_d(0.0F, 1.0F, 0.0F);
/*  96 */         var32.addVertexWithUV(var23, var25, -var8, var13, var15);
/*  97 */         var32.addVertexWithUV(var24, var25, -var8, var14, var15);
/*  98 */         var32.addVertexWithUV(var24, var25, var8, var14, var16);
/*  99 */         var32.addVertexWithUV(var23, var25, var8, var13, var16);
/* 100 */         var32.func_178980_d(0.0F, -1.0F, 0.0F);
/* 101 */         var32.addVertexWithUV(var23, var26, var8, var13, var15);
/* 102 */         var32.addVertexWithUV(var24, var26, var8, var14, var15);
/* 103 */         var32.addVertexWithUV(var24, var26, -var8, var14, var16);
/* 104 */         var32.addVertexWithUV(var23, var26, -var8, var13, var16);
/* 105 */         var32.func_178980_d(-1.0F, 0.0F, 0.0F);
/* 106 */         var32.addVertexWithUV(var23, var25, var8, var18, var19);
/* 107 */         var32.addVertexWithUV(var23, var26, var8, var18, var20);
/* 108 */         var32.addVertexWithUV(var23, var26, -var8, var17, var20);
/* 109 */         var32.addVertexWithUV(var23, var25, -var8, var17, var19);
/* 110 */         var32.func_178980_d(1.0F, 0.0F, 0.0F);
/* 111 */         var32.addVertexWithUV(var24, var25, -var8, var18, var19);
/* 112 */         var32.addVertexWithUV(var24, var26, -var8, var18, var20);
/* 113 */         var32.addVertexWithUV(var24, var26, var8, var17, var20);
/* 114 */         var32.addVertexWithUV(var24, var25, var8, var17, var19);
/* 115 */         var31.draw();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_77008_a(EntityPainting p_77008_1_, float p_77008_2_, float p_77008_3_) {
/* 122 */     int var4 = MathHelper.floor_double(p_77008_1_.posX);
/* 123 */     int var5 = MathHelper.floor_double(p_77008_1_.posY + (p_77008_3_ / 16.0F));
/* 124 */     int var6 = MathHelper.floor_double(p_77008_1_.posZ);
/* 125 */     EnumFacing var7 = p_77008_1_.field_174860_b;
/*     */     
/* 127 */     if (var7 == EnumFacing.NORTH)
/*     */     {
/* 129 */       var4 = MathHelper.floor_double(p_77008_1_.posX + (p_77008_2_ / 16.0F));
/*     */     }
/*     */     
/* 132 */     if (var7 == EnumFacing.WEST)
/*     */     {
/* 134 */       var6 = MathHelper.floor_double(p_77008_1_.posZ - (p_77008_2_ / 16.0F));
/*     */     }
/*     */     
/* 137 */     if (var7 == EnumFacing.SOUTH)
/*     */     {
/* 139 */       var4 = MathHelper.floor_double(p_77008_1_.posX - (p_77008_2_ / 16.0F));
/*     */     }
/*     */     
/* 142 */     if (var7 == EnumFacing.EAST)
/*     */     {
/* 144 */       var6 = MathHelper.floor_double(p_77008_1_.posZ + (p_77008_2_ / 16.0F));
/*     */     }
/*     */     
/* 147 */     int var8 = this.renderManager.worldObj.getCombinedLight(new BlockPos(var4, var5, var6), 0);
/* 148 */     int var9 = var8 % 65536;
/* 149 */     int var10 = var8 / 65536;
/* 150 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var9, var10);
/* 151 */     GlStateManager.color(1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 159 */     return func_180562_a((EntityPainting)p_110775_1_);
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
/* 170 */     doRender((EntityPainting)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */