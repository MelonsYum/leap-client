/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventRenderNameTag;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.culling.ICamera;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import optifine.Config;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import shadersmod.client.Shaders;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Render
/*     */ {
/*  46 */   private static final ResourceLocation shadowTextures = new ResourceLocation("textures/misc/shadow.png");
/*     */ 
/*     */   
/*     */   protected final RenderManager renderManager;
/*     */   
/*     */   protected float shadowSize;
/*     */   
/*  53 */   protected float shadowOpaque = 1.0F;
/*     */ 
/*     */   
/*     */   protected Render(RenderManager p_i46179_1_) {
/*  57 */     this.renderManager = p_i46179_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_177071_a(Entity p_177071_1_, ICamera p_177071_2_, double p_177071_3_, double p_177071_5_, double p_177071_7_) {
/*  62 */     return (p_177071_1_.ignoreFrustumCheck || p_177071_2_.isBoundingBoxInFrustum(p_177071_1_.getEntityBoundingBox()));
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
/*  73 */     func_177067_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_177067_a(Entity p_177067_1_, double p_177067_2_, double p_177067_4_, double p_177067_6_) {
/*  79 */     if (func_177070_b(p_177067_1_))
/*     */     {
/*  81 */       renderLivingLabel(p_177067_1_, p_177067_1_.getDisplayName().getFormattedText(), p_177067_2_, p_177067_4_, p_177067_6_, 64);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_177070_b(Entity p_177070_1_) {
/*  87 */     return (p_177070_1_.getAlwaysRenderNameTagForRender() && p_177070_1_.hasCustomName());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_177069_a(Entity p_177069_1_, double p_177069_2_, double p_177069_4_, double p_177069_6_, String p_177069_8_, float p_177069_9_, double p_177069_10_) {
/*  92 */     renderLivingLabel(p_177069_1_, p_177069_8_, p_177069_2_, p_177069_4_, p_177069_6_, 64);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract ResourceLocation getEntityTexture(Entity paramEntity);
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean bindEntityTexture(Entity p_180548_1_) {
/* 102 */     ResourceLocation var2 = getEntityTexture(p_180548_1_);
/*     */     
/* 104 */     if (var2 == null)
/*     */     {
/* 106 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 110 */     bindTexture(var2);
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void bindTexture(ResourceLocation p_110776_1_) {
/* 117 */     this.renderManager.renderEngine.bindTexture(p_110776_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderEntityOnFire(Entity p_76977_1_, double p_76977_2_, double p_76977_4_, double p_76977_6_, float p_76977_8_) {
/* 125 */     GlStateManager.disableLighting();
/* 126 */     TextureMap var9 = Minecraft.getMinecraft().getTextureMapBlocks();
/* 127 */     TextureAtlasSprite var10 = var9.getAtlasSprite("minecraft:blocks/fire_layer_0");
/* 128 */     TextureAtlasSprite var11 = var9.getAtlasSprite("minecraft:blocks/fire_layer_1");
/* 129 */     GlStateManager.pushMatrix();
/* 130 */     GlStateManager.translate((float)p_76977_2_, (float)p_76977_4_, (float)p_76977_6_);
/* 131 */     float var12 = p_76977_1_.width * 1.4F;
/* 132 */     GlStateManager.scale(var12, var12, var12);
/* 133 */     Tessellator var13 = Tessellator.getInstance();
/* 134 */     WorldRenderer var14 = var13.getWorldRenderer();
/* 135 */     float var15 = 0.5F;
/* 136 */     float var16 = 0.0F;
/* 137 */     float var17 = p_76977_1_.height / var12;
/* 138 */     float var18 = (float)(p_76977_1_.posY - (p_76977_1_.getEntityBoundingBox()).minY);
/* 139 */     GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 140 */     GlStateManager.translate(0.0F, 0.0F, -0.3F + (int)var17 * 0.02F);
/* 141 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 142 */     float var19 = 0.0F;
/* 143 */     int var20 = 0;
/* 144 */     var14.startDrawingQuads();
/*     */     
/* 146 */     while (var17 > 0.0F) {
/*     */       
/* 148 */       TextureAtlasSprite var21 = (var20 % 2 == 0) ? var10 : var11;
/* 149 */       bindTexture(TextureMap.locationBlocksTexture);
/* 150 */       float var22 = var21.getMinU();
/* 151 */       float var23 = var21.getMinV();
/* 152 */       float var24 = var21.getMaxU();
/* 153 */       float var25 = var21.getMaxV();
/*     */       
/* 155 */       if (var20 / 2 % 2 == 0) {
/*     */         
/* 157 */         float var26 = var24;
/* 158 */         var24 = var22;
/* 159 */         var22 = var26;
/*     */       } 
/*     */       
/* 162 */       var14.addVertexWithUV((var15 - var16), (0.0F - var18), var19, var24, var25);
/* 163 */       var14.addVertexWithUV((-var15 - var16), (0.0F - var18), var19, var22, var25);
/* 164 */       var14.addVertexWithUV((-var15 - var16), (1.4F - var18), var19, var22, var23);
/* 165 */       var14.addVertexWithUV((var15 - var16), (1.4F - var18), var19, var24, var23);
/* 166 */       var17 -= 0.45F;
/* 167 */       var18 -= 0.45F;
/* 168 */       var15 *= 0.9F;
/* 169 */       var19 += 0.03F;
/* 170 */       var20++;
/*     */     } 
/*     */     
/* 173 */     var13.draw();
/* 174 */     GlStateManager.popMatrix();
/* 175 */     GlStateManager.enableLighting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderShadow(Entity p_76975_1_, double p_76975_2_, double p_76975_4_, double p_76975_6_, float p_76975_8_, float p_76975_9_) {
/* 185 */     if (!Config.isShaders() || !Shaders.shouldSkipDefaultShadow) {
/*     */       
/* 187 */       GlStateManager.enableBlend();
/* 188 */       GlStateManager.blendFunc(770, 771);
/* 189 */       this.renderManager.renderEngine.bindTexture(shadowTextures);
/* 190 */       World var10 = getWorldFromRenderManager();
/* 191 */       GlStateManager.depthMask(false);
/* 192 */       float var11 = this.shadowSize;
/*     */       
/* 194 */       if (p_76975_1_ instanceof EntityLiving) {
/*     */         
/* 196 */         EntityLiving var35 = (EntityLiving)p_76975_1_;
/* 197 */         var11 *= var35.getRenderSizeModifier();
/*     */         
/* 199 */         if (var35.isChild())
/*     */         {
/* 201 */           var11 *= 0.5F;
/*     */         }
/*     */       } 
/*     */       
/* 205 */       double var351 = p_76975_1_.lastTickPosX + (p_76975_1_.posX - p_76975_1_.lastTickPosX) * p_76975_9_;
/* 206 */       double var14 = p_76975_1_.lastTickPosY + (p_76975_1_.posY - p_76975_1_.lastTickPosY) * p_76975_9_;
/* 207 */       double var16 = p_76975_1_.lastTickPosZ + (p_76975_1_.posZ - p_76975_1_.lastTickPosZ) * p_76975_9_;
/* 208 */       int var18 = MathHelper.floor_double(var351 - var11);
/* 209 */       int var19 = MathHelper.floor_double(var351 + var11);
/* 210 */       int var20 = MathHelper.floor_double(var14 - var11);
/* 211 */       int var21 = MathHelper.floor_double(var14);
/* 212 */       int var22 = MathHelper.floor_double(var16 - var11);
/* 213 */       int var23 = MathHelper.floor_double(var16 + var11);
/* 214 */       double var24 = p_76975_2_ - var351;
/* 215 */       double var26 = p_76975_4_ - var14;
/* 216 */       double var28 = p_76975_6_ - var16;
/* 217 */       Tessellator var30 = Tessellator.getInstance();
/* 218 */       WorldRenderer var31 = var30.getWorldRenderer();
/* 219 */       var31.startDrawingQuads();
/* 220 */       Iterator<BlockPos> var32 = BlockPos.getAllInBox(new BlockPos(var18, var20, var22), new BlockPos(var19, var21, var23)).iterator();
/*     */       
/* 222 */       while (var32.hasNext()) {
/*     */         
/* 224 */         BlockPos var33 = var32.next();
/* 225 */         Block var34 = var10.getBlockState(var33.offsetDown()).getBlock();
/*     */         
/* 227 */         if (var34.getRenderType() != -1 && var10.getLightFromNeighbors(var33) > 3)
/*     */         {
/* 229 */           func_180549_a(var34, p_76975_2_, p_76975_4_, p_76975_6_, var33, p_76975_8_, var11, var24, var26, var28);
/*     */         }
/*     */       } 
/*     */       
/* 233 */       var30.draw();
/* 234 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 235 */       GlStateManager.disableBlend();
/* 236 */       GlStateManager.depthMask(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private World getWorldFromRenderManager() {
/* 245 */     return this.renderManager.worldObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_180549_a(Block p_180549_1_, double p_180549_2_, double p_180549_4_, double p_180549_6_, BlockPos p_180549_8_, float p_180549_9_, float p_180549_10_, double p_180549_11_, double p_180549_13_, double p_180549_15_) {
/* 252 */     if (p_180549_1_.isFullCube()) {
/*     */ 
/*     */       
/* 255 */       Tessellator var17 = Tessellator.getInstance();
/* 256 */       WorldRenderer var18 = var17.getWorldRenderer();
/* 257 */       double var19 = (p_180549_9_ - (p_180549_4_ - p_180549_8_.getY() + p_180549_13_) / 2.0D) * 0.5D * getWorldFromRenderManager().getLightBrightness(p_180549_8_);
/*     */       
/* 259 */       if (var19 >= 0.0D) {
/*     */         
/* 261 */         if (var19 > 1.0D)
/*     */         {
/* 263 */           var19 = 1.0D;
/*     */         }
/*     */         
/* 266 */         var18.func_178960_a(1.0F, 1.0F, 1.0F, (float)var19);
/* 267 */         double var21 = p_180549_8_.getX() + p_180549_1_.getBlockBoundsMinX() + p_180549_11_;
/* 268 */         double var23 = p_180549_8_.getX() + p_180549_1_.getBlockBoundsMaxX() + p_180549_11_;
/* 269 */         double var25 = p_180549_8_.getY() + p_180549_1_.getBlockBoundsMinY() + p_180549_13_ + 0.015625D;
/* 270 */         double var27 = p_180549_8_.getZ() + p_180549_1_.getBlockBoundsMinZ() + p_180549_15_;
/* 271 */         double var29 = p_180549_8_.getZ() + p_180549_1_.getBlockBoundsMaxZ() + p_180549_15_;
/* 272 */         float var31 = (float)((p_180549_2_ - var21) / 2.0D / p_180549_10_ + 0.5D);
/* 273 */         float var32 = (float)((p_180549_2_ - var23) / 2.0D / p_180549_10_ + 0.5D);
/* 274 */         float var33 = (float)((p_180549_6_ - var27) / 2.0D / p_180549_10_ + 0.5D);
/* 275 */         float var34 = (float)((p_180549_6_ - var29) / 2.0D / p_180549_10_ + 0.5D);
/* 276 */         var18.addVertexWithUV(var21, var25, var27, var31, var33);
/* 277 */         var18.addVertexWithUV(var21, var25, var29, var31, var34);
/* 278 */         var18.addVertexWithUV(var23, var25, var29, var32, var34);
/* 279 */         var18.addVertexWithUV(var23, var25, var27, var32, var33);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void renderOffsetAABB(AxisAlignedBB p_76978_0_, double p_76978_1_, double p_76978_3_, double p_76978_5_) {
/* 289 */     GlStateManager.func_179090_x();
/* 290 */     Tessellator var7 = Tessellator.getInstance();
/* 291 */     WorldRenderer var8 = var7.getWorldRenderer();
/* 292 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 293 */     var8.startDrawingQuads();
/* 294 */     var8.setTranslation(p_76978_1_, p_76978_3_, p_76978_5_);
/* 295 */     var8.func_178980_d(0.0F, 0.0F, -1.0F);
/* 296 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.minZ);
/* 297 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.minZ);
/* 298 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.minZ);
/* 299 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.minZ);
/* 300 */     var8.func_178980_d(0.0F, 0.0F, 1.0F);
/* 301 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.maxZ);
/* 302 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.maxZ);
/* 303 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.maxZ);
/* 304 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.maxZ);
/* 305 */     var8.func_178980_d(0.0F, -1.0F, 0.0F);
/* 306 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.minZ);
/* 307 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.minZ);
/* 308 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.maxZ);
/* 309 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.maxZ);
/* 310 */     var8.func_178980_d(0.0F, 1.0F, 0.0F);
/* 311 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.maxZ);
/* 312 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.maxZ);
/* 313 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.minZ);
/* 314 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.minZ);
/* 315 */     var8.func_178980_d(-1.0F, 0.0F, 0.0F);
/* 316 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.maxZ);
/* 317 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.maxZ);
/* 318 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.maxY, p_76978_0_.minZ);
/* 319 */     var8.addVertex(p_76978_0_.minX, p_76978_0_.minY, p_76978_0_.minZ);
/* 320 */     var8.func_178980_d(1.0F, 0.0F, 0.0F);
/* 321 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.minZ);
/* 322 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.minZ);
/* 323 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.maxY, p_76978_0_.maxZ);
/* 324 */     var8.addVertex(p_76978_0_.maxX, p_76978_0_.minY, p_76978_0_.maxZ);
/* 325 */     var8.setTranslation(0.0D, 0.0D, 0.0D);
/* 326 */     var7.draw();
/* 327 */     GlStateManager.func_179098_w();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRenderShadowAndFire(Entity p_76979_1_, double p_76979_2_, double p_76979_4_, double p_76979_6_, float p_76979_8_, float p_76979_9_) {
/* 336 */     if (this.renderManager.options != null) {
/*     */       
/* 338 */       if (this.renderManager.options.fancyGraphics && this.shadowSize > 0.0F && !p_76979_1_.isInvisible() && this.renderManager.func_178627_a()) {
/*     */         
/* 340 */         double var10 = this.renderManager.getDistanceToCamera(p_76979_1_.posX, p_76979_1_.posY, p_76979_1_.posZ);
/* 341 */         float var12 = (float)((1.0D - var10 / 256.0D) * this.shadowOpaque);
/*     */         
/* 343 */         if (var12 > 0.0F)
/*     */         {
/* 345 */           renderShadow(p_76979_1_, p_76979_2_, p_76979_4_, p_76979_6_, var12, p_76979_9_);
/*     */         }
/*     */       } 
/*     */       
/* 349 */       if (p_76979_1_.canRenderOnFire() && (!(p_76979_1_ instanceof EntityPlayer) || !((EntityPlayer)p_76979_1_).func_175149_v()))
/*     */       {
/* 351 */         renderEntityOnFire(p_76979_1_, p_76979_2_, p_76979_4_, p_76979_6_, p_76979_9_);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FontRenderer getFontRendererFromRenderManager() {
/* 361 */     return this.renderManager.getFontRenderer();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderLivingLabel(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_) {
/* 369 */     Minecraft mc = Minecraft.getMinecraft();
/*     */     
/* 371 */     Client.onEvent((Event)new EventRenderNameTag());
/*     */     
/* 373 */     if (!Client.getModule("NameTags").isEnabled()) {
/*     */ 
/*     */       
/* 376 */       double var10 = p_147906_1_.getDistanceSqToEntity(this.renderManager.livingPlayer);
/*     */       
/* 378 */       if (var10 <= (p_147906_9_ * p_147906_9_)) {
/*     */         
/* 380 */         FontRenderer var12 = getFontRendererFromRenderManager();
/* 381 */         float var13 = 1.6F;
/* 382 */         float var14 = 0.016666668F * var13;
/* 383 */         GlStateManager.pushMatrix();
/* 384 */         GlStateManager.translate((float)p_147906_3_ + 0.0F, (float)p_147906_5_ + p_147906_1_.height + 0.5F, (float)p_147906_7_);
/* 385 */         GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 386 */         GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 387 */         GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 388 */         GlStateManager.scale(-var14, -var14, var14);
/* 389 */         GlStateManager.disableLighting();
/* 390 */         GlStateManager.depthMask(false);
/* 391 */         GlStateManager.disableDepth();
/* 392 */         GlStateManager.enableBlend();
/* 393 */         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 394 */         Tessellator var15 = Tessellator.getInstance();
/* 395 */         WorldRenderer var16 = var15.getWorldRenderer();
/* 396 */         byte var17 = 0;
/*     */         
/* 398 */         if (p_147906_2_.equals("deadmau5"))
/*     */         {
/* 400 */           var17 = -10;
/*     */         }
/*     */         
/* 403 */         GlStateManager.func_179090_x();
/* 404 */         var16.startDrawingQuads();
/* 405 */         int var18 = var12.getStringWidth(p_147906_2_) / 2;
/* 406 */         var16.func_178960_a(0.0F, 0.0F, 0.0F, 0.25F);
/* 407 */         var16.addVertex((-var18 - 1), (-1 + var17), 0.0D);
/* 408 */         var16.addVertex((-var18 - 1), (8 + var17), 0.0D);
/* 409 */         var16.addVertex((var18 + 1), (8 + var17), 0.0D);
/* 410 */         var16.addVertex((var18 + 1), (-1 + var17), 0.0D);
/* 411 */         var15.draw();
/* 412 */         GlStateManager.func_179098_w();
/* 413 */         var12.drawString(p_147906_2_, (-var12.getStringWidth(p_147906_2_) / 2), var17, 553648127);
/* 414 */         GlStateManager.enableDepth();
/* 415 */         GlStateManager.depthMask(true);
/* 416 */         var12.drawString(p_147906_2_, (-var12.getStringWidth(p_147906_2_) / 2), var17, -1);
/* 417 */         GlStateManager.enableLighting();
/* 418 */         GlStateManager.disableBlend();
/* 419 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 420 */         GlStateManager.popMatrix();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderManager func_177068_d() {
/* 427 */     return this.renderManager;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\Render.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */