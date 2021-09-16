/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelMinecart;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class RenderMinecart
/*     */   extends Render {
/*  17 */   private static final ResourceLocation minecartTextures = new ResourceLocation("textures/entity/minecart.png");
/*     */ 
/*     */   
/*  20 */   protected ModelBase modelMinecart = (ModelBase)new ModelMinecart();
/*     */   
/*     */   private static final String __OBFID = "CL_00001013";
/*     */   
/*     */   public RenderMinecart(RenderManager p_i46155_1_) {
/*  25 */     super(p_i46155_1_);
/*  26 */     this.shadowSize = 0.5F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityMinecart p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/*  37 */     GlStateManager.pushMatrix();
/*  38 */     bindEntityTexture((Entity)p_76986_1_);
/*  39 */     long var10 = p_76986_1_.getEntityId() * 493286711L;
/*  40 */     var10 = var10 * var10 * 4392167121L + var10 * 98761L;
/*  41 */     float var12 = (((float)(var10 >> 16L & 0x7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
/*  42 */     float var13 = (((float)(var10 >> 20L & 0x7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
/*  43 */     float var14 = (((float)(var10 >> 24L & 0x7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
/*  44 */     GlStateManager.translate(var12, var13, var14);
/*  45 */     double var15 = p_76986_1_.lastTickPosX + (p_76986_1_.posX - p_76986_1_.lastTickPosX) * p_76986_9_;
/*  46 */     double var17 = p_76986_1_.lastTickPosY + (p_76986_1_.posY - p_76986_1_.lastTickPosY) * p_76986_9_;
/*  47 */     double var19 = p_76986_1_.lastTickPosZ + (p_76986_1_.posZ - p_76986_1_.lastTickPosZ) * p_76986_9_;
/*  48 */     double var21 = 0.30000001192092896D;
/*  49 */     Vec3 var23 = p_76986_1_.func_70489_a(var15, var17, var19);
/*  50 */     float var24 = p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_;
/*     */     
/*  52 */     if (var23 != null) {
/*     */       
/*  54 */       Vec3 var25 = p_76986_1_.func_70495_a(var15, var17, var19, var21);
/*  55 */       Vec3 var26 = p_76986_1_.func_70495_a(var15, var17, var19, -var21);
/*     */       
/*  57 */       if (var25 == null)
/*     */       {
/*  59 */         var25 = var23;
/*     */       }
/*     */       
/*  62 */       if (var26 == null)
/*     */       {
/*  64 */         var26 = var23;
/*     */       }
/*     */       
/*  67 */       p_76986_2_ += var23.xCoord - var15;
/*  68 */       p_76986_4_ += (var25.yCoord + var26.yCoord) / 2.0D - var17;
/*  69 */       p_76986_6_ += var23.zCoord - var19;
/*  70 */       Vec3 var27 = var26.addVector(-var25.xCoord, -var25.yCoord, -var25.zCoord);
/*     */       
/*  72 */       if (var27.lengthVector() != 0.0D) {
/*     */         
/*  74 */         var27 = var27.normalize();
/*  75 */         p_76986_8_ = (float)(Math.atan2(var27.zCoord, var27.xCoord) * 180.0D / Math.PI);
/*  76 */         var24 = (float)(Math.atan(var27.yCoord) * 73.0D);
/*     */       } 
/*     */     } 
/*     */     
/*  80 */     GlStateManager.translate((float)p_76986_2_, (float)p_76986_4_ + 0.375F, (float)p_76986_6_);
/*  81 */     GlStateManager.rotate(180.0F - p_76986_8_, 0.0F, 1.0F, 0.0F);
/*  82 */     GlStateManager.rotate(-var24, 0.0F, 0.0F, 1.0F);
/*  83 */     float var30 = p_76986_1_.getRollingAmplitude() - p_76986_9_;
/*  84 */     float var31 = p_76986_1_.getDamage() - p_76986_9_;
/*     */     
/*  86 */     if (var31 < 0.0F)
/*     */     {
/*  88 */       var31 = 0.0F;
/*     */     }
/*     */     
/*  91 */     if (var30 > 0.0F)
/*     */     {
/*  93 */       GlStateManager.rotate(MathHelper.sin(var30) * var30 * var31 / 10.0F * p_76986_1_.getRollingDirection(), 1.0F, 0.0F, 0.0F);
/*     */     }
/*     */     
/*  96 */     int var32 = p_76986_1_.getDisplayTileOffset();
/*  97 */     IBlockState var28 = p_76986_1_.func_174897_t();
/*     */     
/*  99 */     if (var28.getBlock().getRenderType() != -1) {
/*     */       
/* 101 */       GlStateManager.pushMatrix();
/* 102 */       bindTexture(TextureMap.locationBlocksTexture);
/* 103 */       float var29 = 0.75F;
/* 104 */       GlStateManager.scale(var29, var29, var29);
/* 105 */       GlStateManager.translate(-0.5F, (var32 - 8) / 16.0F, 0.5F);
/* 106 */       func_180560_a(p_76986_1_, p_76986_9_, var28);
/* 107 */       GlStateManager.popMatrix();
/* 108 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 109 */       bindEntityTexture((Entity)p_76986_1_);
/*     */     } 
/*     */     
/* 112 */     GlStateManager.scale(-1.0F, -1.0F, 1.0F);
/* 113 */     this.modelMinecart.render((Entity)p_76986_1_, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
/* 114 */     GlStateManager.popMatrix();
/* 115 */     super.doRender((Entity)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(EntityMinecart p_110775_1_) {
/* 123 */     return minecartTextures;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180560_a(EntityMinecart p_180560_1_, float p_180560_2_, IBlockState p_180560_3_) {
/* 128 */     GlStateManager.pushMatrix();
/* 129 */     Minecraft.getMinecraft().getBlockRendererDispatcher().func_175016_a(p_180560_3_, p_180560_1_.getBrightness(p_180560_2_));
/* 130 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
/* 138 */     return getEntityTexture((EntityMinecart)p_110775_1_);
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
/* 149 */     doRender((EntityMinecart)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RenderMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */