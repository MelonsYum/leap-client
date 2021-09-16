/*     */ package net.minecraft.client.renderer.entity;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelDragon;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerEnderDragonDeath;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerEnderDragonEyes;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.boss.BossStatus;
/*     */ import net.minecraft.entity.boss.EntityDragon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class RenderDragon extends RenderLiving {
/*  20 */   private static final ResourceLocation enderDragonCrystalBeamTextures = new ResourceLocation("textures/entity/endercrystal/endercrystal_beam.png");
/*  21 */   private static final ResourceLocation enderDragonExplodingTextures = new ResourceLocation("textures/entity/enderdragon/dragon_exploding.png");
/*  22 */   private static final ResourceLocation enderDragonTextures = new ResourceLocation("textures/entity/enderdragon/dragon.png");
/*     */   
/*     */   protected ModelDragon modelDragon;
/*     */   
/*     */   private static final String __OBFID = "CL_00000988";
/*     */ 
/*     */   
/*     */   public RenderDragon(RenderManager p_i46183_1_) {
/*  30 */     super(p_i46183_1_, (ModelBase)new ModelDragon(0.0F), 0.5F);
/*  31 */     this.modelDragon = (ModelDragon)this.mainModel;
/*  32 */     addLayer((LayerRenderer)new LayerEnderDragonEyes(this));
/*  33 */     addLayer((LayerRenderer)new LayerEnderDragonDeath());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180575_a(EntityDragon p_180575_1_, float p_180575_2_, float p_180575_3_, float p_180575_4_) {
/*  38 */     float var5 = (float)p_180575_1_.getMovementOffsets(7, p_180575_4_)[0];
/*  39 */     float var6 = (float)(p_180575_1_.getMovementOffsets(5, p_180575_4_)[1] - p_180575_1_.getMovementOffsets(10, p_180575_4_)[1]);
/*  40 */     GlStateManager.rotate(-var5, 0.0F, 1.0F, 0.0F);
/*  41 */     GlStateManager.rotate(var6 * 10.0F, 1.0F, 0.0F, 0.0F);
/*  42 */     GlStateManager.translate(0.0F, 0.0F, 1.0F);
/*     */     
/*  44 */     if (p_180575_1_.deathTime > 0) {
/*     */       
/*  46 */       float var7 = (p_180575_1_.deathTime + p_180575_4_ - 1.0F) / 20.0F * 1.6F;
/*  47 */       var7 = MathHelper.sqrt_float(var7);
/*     */       
/*  49 */       if (var7 > 1.0F)
/*     */       {
/*  51 */         var7 = 1.0F;
/*     */       }
/*     */       
/*  54 */       GlStateManager.rotate(var7 * getDeathMaxRotation((EntityLivingBase)p_180575_1_), 0.0F, 0.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderModel(EntityDragon p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
/*  63 */     if (p_77036_1_.deathTicks > 0) {
/*     */       
/*  65 */       float var8 = p_77036_1_.deathTicks / 200.0F;
/*  66 */       GlStateManager.depthFunc(515);
/*  67 */       GlStateManager.enableAlpha();
/*  68 */       GlStateManager.alphaFunc(516, var8);
/*  69 */       bindTexture(enderDragonExplodingTextures);
/*  70 */       this.mainModel.render((Entity)p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
/*  71 */       GlStateManager.alphaFunc(516, 0.1F);
/*  72 */       GlStateManager.depthFunc(514);
/*     */     } 
/*     */     
/*  75 */     bindEntityTexture((Entity)p_77036_1_);
/*  76 */     this.mainModel.render((Entity)p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
/*     */     
/*  78 */     if (p_77036_1_.hurtTime > 0) {
/*     */       
/*  80 */       GlStateManager.depthFunc(514);
/*  81 */       GlStateManager.func_179090_x();
/*  82 */       GlStateManager.enableBlend();
/*  83 */       GlStateManager.blendFunc(770, 771);
/*  84 */       GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F);
/*  85 */       this.mainModel.render((Entity)p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
/*  86 */       GlStateManager.func_179098_w();
/*  87 */       GlStateManager.disableBlend();
/*  88 */       GlStateManager.depthFunc(515);
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
/*     */   public void doRender(EntityDragon p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 100 */     BossStatus.setBossStatus((IBossDisplayData)p_76986_1_, false);
/* 101 */     super.doRender((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */     
/* 103 */     if (p_76986_1_.healingEnderCrystal != null)
/*     */     {
/* 105 */       func_180574_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_9_);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180574_a(EntityDragon p_180574_1_, double p_180574_2_, double p_180574_4_, double p_180574_6_, float p_180574_8_) {
/* 111 */     float var9 = p_180574_1_.healingEnderCrystal.innerRotation + p_180574_8_;
/* 112 */     float var10 = MathHelper.sin(var9 * 0.2F) / 2.0F + 0.5F;
/* 113 */     var10 = (var10 * var10 + var10) * 0.2F;
/* 114 */     float var11 = (float)(p_180574_1_.healingEnderCrystal.posX - p_180574_1_.posX - (p_180574_1_.prevPosX - p_180574_1_.posX) * (1.0F - p_180574_8_));
/* 115 */     float var12 = (float)(var10 + p_180574_1_.healingEnderCrystal.posY - 1.0D - p_180574_1_.posY - (p_180574_1_.prevPosY - p_180574_1_.posY) * (1.0F - p_180574_8_));
/* 116 */     float var13 = (float)(p_180574_1_.healingEnderCrystal.posZ - p_180574_1_.posZ - (p_180574_1_.prevPosZ - p_180574_1_.posZ) * (1.0F - p_180574_8_));
/* 117 */     float var14 = MathHelper.sqrt_float(var11 * var11 + var13 * var13);
/* 118 */     float var15 = MathHelper.sqrt_float(var11 * var11 + var12 * var12 + var13 * var13);
/* 119 */     GlStateManager.pushMatrix();
/* 120 */     GlStateManager.translate((float)p_180574_2_, (float)p_180574_4_ + 2.0F, (float)p_180574_6_);
/* 121 */     GlStateManager.rotate((float)-Math.atan2(var13, var11) * 180.0F / 3.1415927F - 90.0F, 0.0F, 1.0F, 0.0F);
/* 122 */     GlStateManager.rotate((float)-Math.atan2(var14, var12) * 180.0F / 3.1415927F - 90.0F, 1.0F, 0.0F, 0.0F);
/* 123 */     Tessellator var16 = Tessellator.getInstance();
/* 124 */     WorldRenderer var17 = var16.getWorldRenderer();
/* 125 */     RenderHelper.disableStandardItemLighting();
/* 126 */     GlStateManager.disableCull();
/* 127 */     bindTexture(enderDragonCrystalBeamTextures);
/* 128 */     GlStateManager.shadeModel(7425);
/* 129 */     float var18 = 0.0F - (p_180574_1_.ticksExisted + p_180574_8_) * 0.01F;
/* 130 */     float var19 = MathHelper.sqrt_float(var11 * var11 + var12 * var12 + var13 * var13) / 32.0F - (p_180574_1_.ticksExisted + p_180574_8_) * 0.01F;
/* 131 */     var17.startDrawing(5);
/* 132 */     byte var20 = 8;
/*     */     
/* 134 */     for (int var21 = 0; var21 <= var20; var21++) {
/*     */       
/* 136 */       float var22 = MathHelper.sin((var21 % var20) * 3.1415927F * 2.0F / var20) * 0.75F;
/* 137 */       float var23 = MathHelper.cos((var21 % var20) * 3.1415927F * 2.0F / var20) * 0.75F;
/* 138 */       float var24 = (var21 % var20) * 1.0F / var20;
/* 139 */       var17.func_178991_c(0);
/* 140 */       var17.addVertexWithUV((var22 * 0.2F), (var23 * 0.2F), 0.0D, var24, var19);
/* 141 */       var17.func_178991_c(16777215);
/* 142 */       var17.addVertexWithUV(var22, var23, var15, var24, var18);
/*     */     } 
/*     */     
/* 145 */     var16.draw();
/* 146 */     GlStateManager.enableCull();
/* 147 */     GlStateManager.shadeModel(7424);
/* 148 */     RenderHelper.enableStandardItemLighting();
/* 149 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityDragon p_110775_1_) {
/* 157 */     return enderDragonTextures;
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
/* 168 */     doRender((EntityDragon)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
/* 173 */     func_180575_a((EntityDragon)p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
/* 181 */     renderModel((EntityDragon)p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
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
/* 192 */     doRender((EntityDragon)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 200 */     return getEntityTexture((EntityDragon)p_110775_1_);
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
/* 211 */     doRender((EntityDragon)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */