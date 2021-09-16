/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import leap.Client;
/*     */ import leap.modules.render.Animations;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.AbstractClientPlayer;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RenderPlayer;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.storage.MapData;
/*     */ import optifine.Config;
/*     */ import optifine.DynamicLights;
/*     */ import optifine.Reflector;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import shadersmod.client.Shaders;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemRenderer
/*     */ {
/*  43 */   private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
/*  44 */   private static final ResourceLocation RES_UNDERWATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
/*  45 */   private double pos = 0.5D;
/*     */   
/*     */   private boolean up = true;
/*     */   
/*     */   private final Minecraft mc;
/*     */   
/*     */   private ItemStack itemToRender;
/*     */   
/*     */   private float equippedProgress;
/*     */   
/*     */   private float prevEquippedProgress;
/*     */   
/*     */   private final RenderManager field_178111_g;
/*     */   
/*     */   private final RenderItem itemRenderer;
/*     */   
/*  61 */   private int equippedItemSlot = -1;
/*     */ 
/*     */   
/*     */   public ItemRenderer(Minecraft mcIn) {
/*  65 */     this.mc = mcIn;
/*  66 */     this.field_178111_g = mcIn.getRenderManager();
/*  67 */     this.itemRenderer = mcIn.getRenderItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderItem(EntityLivingBase p_178099_1_, ItemStack p_178099_2_, ItemCameraTransforms.TransformType p_178099_3_) {
/*  72 */     if (p_178099_2_ != null) {
/*     */       
/*  74 */       Item var4 = p_178099_2_.getItem();
/*  75 */       Block var5 = Block.getBlockFromItem(var4);
/*  76 */       GlStateManager.pushMatrix();
/*     */       
/*  78 */       if (this.itemRenderer.func_175050_a(p_178099_2_)) {
/*     */         
/*  80 */         GlStateManager.scale(2.0F, 2.0F, 2.0F);
/*     */         
/*  82 */         if (func_178107_a(var5))
/*     */         {
/*  84 */           GlStateManager.depthMask(false);
/*     */         }
/*     */       } 
/*     */       
/*  88 */       this.itemRenderer.func_175049_a(p_178099_2_, p_178099_1_, p_178099_3_);
/*     */       
/*  90 */       if (func_178107_a(var5))
/*     */       {
/*  92 */         GlStateManager.depthMask(true);
/*     */       }
/*     */       
/*  95 */       GlStateManager.popMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_178107_a(Block p_178107_1_) {
/* 101 */     return (p_178107_1_ != null && p_178107_1_.getBlockLayer() == EnumWorldBlockLayer.TRANSLUCENT);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178101_a(float p_178101_1_, float p_178101_2_) {
/* 106 */     GlStateManager.pushMatrix();
/* 107 */     GlStateManager.rotate(p_178101_1_, 0.0F, 0.0F, 0.0F);
/* 108 */     GlStateManager.rotate(p_178101_2_, 0.0F, 1.0F, 0.0F);
/* 109 */     RenderHelper.enableStandardItemLighting();
/* 110 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178109_a(AbstractClientPlayer p_178109_1_) {
/* 115 */     int var2 = this.mc.theWorld.getCombinedLight(new BlockPos(p_178109_1_.posX, p_178109_1_.posY + p_178109_1_.getEyeHeight(), p_178109_1_.posZ), 0);
/*     */     
/* 117 */     if (Config.isDynamicLights())
/*     */     {
/* 119 */       var2 = DynamicLights.getCombinedLight(this.mc.func_175606_aa(), var2);
/*     */     }
/*     */     
/* 122 */     float var3 = (var2 & 0xFFFF);
/* 123 */     float var4 = (var2 >> 16);
/* 124 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178110_a(EntityPlayerSP p_178110_1_, float p_178110_2_) {
/* 129 */     float var3 = p_178110_1_.prevRenderArmPitch + (p_178110_1_.renderArmPitch - p_178110_1_.prevRenderArmPitch) * p_178110_2_;
/* 130 */     float var4 = p_178110_1_.prevRenderArmYaw + (p_178110_1_.renderArmYaw - p_178110_1_.prevRenderArmYaw) * p_178110_2_;
/* 131 */     GlStateManager.rotate((p_178110_1_.rotationPitch - var3) * 0.1F, 1.0F, 0.0F, 0.0F);
/* 132 */     GlStateManager.rotate((p_178110_1_.rotationYaw - var4) * 0.1F, 0.0F, 1.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private float func_178100_c(float p_178100_1_) {
/* 137 */     float var2 = 1.0F - p_178100_1_ / 45.0F + 0.1F;
/* 138 */     var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
/* 139 */     var2 = -MathHelper.cos(var2 * 3.1415927F) * 0.5F + 0.5F;
/* 140 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_180534_a(RenderPlayer p_180534_1_) {
/* 145 */     GlStateManager.pushMatrix();
/* 146 */     GlStateManager.rotate(54.0F, 0.0F, 1.0F, 0.0F);
/* 147 */     GlStateManager.rotate(64.0F, 1.0F, 0.0F, 0.0F);
/* 148 */     GlStateManager.rotate(-62.0F, 0.0F, 0.0F, 1.0F);
/* 149 */     GlStateManager.translate(0.25F, -0.85F, 0.75F);
/* 150 */     p_180534_1_.func_177138_b((AbstractClientPlayer)this.mc.thePlayer);
/* 151 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178106_b(RenderPlayer p_178106_1_) {
/* 156 */     GlStateManager.pushMatrix();
/* 157 */     GlStateManager.rotate(92.0F, 0.0F, 1.0F, 0.0F);
/* 158 */     GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
/* 159 */     GlStateManager.rotate(41.0F, 0.0F, 0.0F, 1.0F);
/* 160 */     GlStateManager.translate(-0.3F, -1.1F, 0.45F);
/* 161 */     p_178106_1_.func_177139_c((AbstractClientPlayer)this.mc.thePlayer);
/* 162 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178102_b(AbstractClientPlayer p_178102_1_) {
/* 167 */     this.mc.getTextureManager().bindTexture(p_178102_1_.getLocationSkin());
/* 168 */     Render var2 = this.field_178111_g.getEntityRenderObject((Entity)this.mc.thePlayer);
/* 169 */     RenderPlayer var3 = (RenderPlayer)var2;
/*     */     
/* 171 */     if (!p_178102_1_.isInvisible()) {
/*     */       
/* 173 */       func_180534_a(var3);
/* 174 */       func_178106_b(var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178097_a(AbstractClientPlayer p_178097_1_, float p_178097_2_, float p_178097_3_, float p_178097_4_) {
/* 180 */     float var5 = -0.4F * MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * 3.1415927F);
/* 181 */     float var6 = 0.2F * MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * 3.1415927F * 2.0F);
/* 182 */     float var7 = -0.2F * MathHelper.sin(p_178097_4_ * 3.1415927F);
/* 183 */     GlStateManager.translate(var5, var6, var7);
/* 184 */     float var8 = func_178100_c(p_178097_2_);
/* 185 */     GlStateManager.translate(0.0F, 0.04F, -0.72F);
/* 186 */     GlStateManager.translate(0.0F, p_178097_3_ * -1.2F, 0.0F);
/* 187 */     GlStateManager.translate(0.0F, var8 * -0.5F, 0.0F);
/* 188 */     GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
/* 189 */     GlStateManager.rotate(var8 * -85.0F, 0.0F, 0.0F, 1.0F);
/* 190 */     GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
/* 191 */     func_178102_b(p_178097_1_);
/* 192 */     float var9 = MathHelper.sin(p_178097_4_ * p_178097_4_ * 3.1415927F);
/* 193 */     float var10 = MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * 3.1415927F);
/* 194 */     GlStateManager.rotate(var9 * -20.0F, 0.0F, 1.0F, 0.0F);
/* 195 */     GlStateManager.rotate(var10 * -20.0F, 0.0F, 0.0F, 1.0F);
/* 196 */     GlStateManager.rotate(var10 * -80.0F, 1.0F, 0.0F, 0.0F);
/* 197 */     GlStateManager.scale(0.38F, 0.38F, 0.38F);
/* 198 */     GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
/* 199 */     GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
/* 200 */     GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
/* 201 */     GlStateManager.translate(-1.0F, -1.0F, 0.0F);
/* 202 */     GlStateManager.scale(0.015625F, 0.015625F, 0.015625F);
/* 203 */     this.mc.getTextureManager().bindTexture(RES_MAP_BACKGROUND);
/* 204 */     Tessellator var11 = Tessellator.getInstance();
/* 205 */     WorldRenderer var12 = var11.getWorldRenderer();
/* 206 */     GL11.glNormal3f(0.0F, 0.0F, -1.0F);
/* 207 */     var12.startDrawingQuads();
/* 208 */     var12.addVertexWithUV(-7.0D, 135.0D, 0.0D, 0.0D, 1.0D);
/* 209 */     var12.addVertexWithUV(135.0D, 135.0D, 0.0D, 1.0D, 1.0D);
/* 210 */     var12.addVertexWithUV(135.0D, -7.0D, 0.0D, 1.0D, 0.0D);
/* 211 */     var12.addVertexWithUV(-7.0D, -7.0D, 0.0D, 0.0D, 0.0D);
/* 212 */     var11.draw();
/* 213 */     MapData var13 = Items.filled_map.getMapData(this.itemToRender, (World)this.mc.theWorld);
/*     */     
/* 215 */     if (var13 != null)
/*     */     {
/* 217 */       this.mc.entityRenderer.getMapItemRenderer().func_148250_a(var13, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178095_a(AbstractClientPlayer p_178095_1_, float p_178095_2_, float p_178095_3_) {
/* 223 */     float var4 = -0.3F * MathHelper.sin(MathHelper.sqrt_float(p_178095_3_) * 3.1415927F);
/* 224 */     float var5 = 0.4F * MathHelper.sin(MathHelper.sqrt_float(p_178095_3_) * 3.1415927F * 2.0F);
/* 225 */     float var6 = -0.4F * MathHelper.sin(p_178095_3_ * 3.1415927F);
/* 226 */     GlStateManager.translate(var4, var5, var6);
/* 227 */     GlStateManager.translate(0.64000005F, -0.6F, -0.71999997F);
/* 228 */     GlStateManager.translate(0.0F, p_178095_2_ * -0.6F, 0.0F);
/* 229 */     GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
/* 230 */     float var7 = MathHelper.sin(p_178095_3_ * p_178095_3_ * 3.1415927F);
/* 231 */     float var8 = MathHelper.sin(MathHelper.sqrt_float(p_178095_3_) * 3.1415927F);
/* 232 */     GlStateManager.rotate(var8 * 70.0F, 0.0F, 1.0F, 0.0F);
/* 233 */     GlStateManager.rotate(var7 * -20.0F, 0.0F, 0.0F, 1.0F);
/* 234 */     this.mc.getTextureManager().bindTexture(p_178095_1_.getLocationSkin());
/* 235 */     GlStateManager.translate(-1.0F, 3.6F, 3.5F);
/* 236 */     GlStateManager.rotate(120.0F, 0.0F, 0.0F, 1.0F);
/* 237 */     GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
/* 238 */     GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
/* 239 */     GlStateManager.scale(1.0F, 1.0F, 1.0F);
/* 240 */     GlStateManager.translate(5.6F, 0.0F, 0.0F);
/* 241 */     Render var9 = this.field_178111_g.getEntityRenderObject((Entity)this.mc.thePlayer);
/* 242 */     RenderPlayer var10 = (RenderPlayer)var9;
/* 243 */     var10.func_177138_b((AbstractClientPlayer)this.mc.thePlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178105_d(float p_178105_1_) {
/* 248 */     float var2 = -0.4F * MathHelper.sin(MathHelper.sqrt_float(p_178105_1_) * 3.1415927F);
/* 249 */     float var3 = 0.2F * MathHelper.sin(MathHelper.sqrt_float(p_178105_1_) * 3.1415927F * 2.0F);
/* 250 */     float var4 = -0.2F * MathHelper.sin(p_178105_1_ * 3.1415927F);
/* 251 */     GlStateManager.translate(var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178104_a(AbstractClientPlayer p_178104_1_, float p_178104_2_) {
/* 256 */     float var3 = p_178104_1_.getItemInUseCount() - p_178104_2_ + 1.0F;
/* 257 */     float var4 = var3 / this.itemToRender.getMaxItemUseDuration();
/* 258 */     float var5 = MathHelper.abs(MathHelper.cos(var3 / 4.0F * 3.1415927F) * 0.1F);
/*     */     
/* 260 */     if (var4 >= 0.8F)
/*     */     {
/* 262 */       var5 = 0.0F;
/*     */     }
/*     */     
/* 265 */     GlStateManager.translate(0.0F, var5, 0.0F);
/* 266 */     float var6 = 1.0F - (float)Math.pow(var4, 27.0D);
/* 267 */     GlStateManager.translate(var6 * 0.6F, var6 * -0.5F, var6 * 0.0F);
/* 268 */     GlStateManager.rotate(var6 * 90.0F, 0.0F, 1.0F, 0.0F);
/* 269 */     GlStateManager.rotate(var6 * 10.0F, 1.0F, 0.0F, 0.0F);
/* 270 */     GlStateManager.rotate(var6 * 30.0F, 0.0F, 0.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178096_b(float p_178096_1_, float p_178096_2_) {
/* 275 */     GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
/* 276 */     GlStateManager.translate(0.0F, p_178096_1_ * -0.6F, 0.0F);
/* 277 */     GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
/* 278 */     float var3 = MathHelper.sin(p_178096_2_ * p_178096_2_ * 3.1415927F);
/* 279 */     float var4 = MathHelper.sin(MathHelper.sqrt_float(p_178096_2_) * 3.1415927F);
/* 280 */     GlStateManager.rotate(var3 * -20.0F, 0.0F, 1.0F, 0.0F);
/* 281 */     GlStateManager.rotate(var4 * -20.0F, 0.0F, 0.0F, 1.0F);
/* 282 */     GlStateManager.rotate(var4 * -80.0F, 1.0F, 0.0F, 0.0F);
/* 283 */     GlStateManager.scale(0.4F, 0.4F, 0.4F);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178098_a(float p_178098_1_, AbstractClientPlayer p_178098_2_) {
/* 288 */     GlStateManager.rotate(-18.0F, 0.0F, 0.0F, 1.0F);
/* 289 */     GlStateManager.rotate(-12.0F, 0.0F, 1.0F, 0.0F);
/* 290 */     GlStateManager.rotate(-8.0F, 1.0F, 0.0F, 0.0F);
/* 291 */     GlStateManager.translate(-0.9F, 0.2F, 0.0F);
/* 292 */     float var3 = this.itemToRender.getMaxItemUseDuration() - p_178098_2_.getItemInUseCount() - p_178098_1_ + 1.0F;
/* 293 */     float var4 = var3 / 20.0F;
/* 294 */     var4 = (var4 * var4 + var4 * 2.0F) / 3.0F;
/*     */     
/* 296 */     if (var4 > 1.0F)
/*     */     {
/* 298 */       var4 = 1.0F;
/*     */     }
/*     */     
/* 301 */     if (var4 > 0.1F) {
/*     */       
/* 303 */       float var5 = MathHelper.sin((var3 - 0.1F) * 1.3F);
/* 304 */       float var6 = var4 - 0.1F;
/* 305 */       float var7 = var5 * var6;
/* 306 */       GlStateManager.translate(var7 * 0.0F, var7 * 0.01F, var7 * 0.0F);
/*     */     } 
/*     */     
/* 309 */     GlStateManager.translate(var4 * 0.0F, var4 * 0.0F, var4 * 0.1F);
/* 310 */     GlStateManager.scale(1.0F, 1.0F, 1.0F + var4 * 0.2F);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178103_d() {
/* 315 */     GlStateManager.translate(-0.5F, 0.2F, 0.0F);
/* 316 */     GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
/* 317 */     GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
/* 318 */     GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private void transformFirstPersonItem(float equipProgress, float swingProgress) {
/* 323 */     GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
/* 324 */     GlStateManager.translate(0.0F, equipProgress * -0.6F, 0.0F);
/* 325 */     GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
/* 326 */     float f = MathHelper.sin(swingProgress * swingProgress * 3.1415927F);
/* 327 */     float f1 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927F);
/* 328 */     GlStateManager.rotate(f * -20.0F, 0.0F, 1.0F, 0.0F);
/* 329 */     GlStateManager.rotate(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
/* 330 */     GlStateManager.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
/* 331 */     GlStateManager.scale(0.4F, 0.4F, 0.4F);
/*     */   }
/*     */ 
/*     */   
/*     */   private void transformSideFirstPerson(float p_187459_2_) {
/* 336 */     GlStateManager.translate(0.56F, -0.52F + p_187459_2_ * -0.6F, -0.72F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void ItemMove(double pos, boolean up) {
/* 341 */     if (pos < 9.0D && up) {
/* 342 */       pos += 0.01D;
/*     */     }
/* 344 */     if (pos > 8.8D) {
/* 345 */       up = false;
/* 346 */       pos -= 0.01D;
/*     */     } 
/*     */     
/* 349 */     if (!up) {
/* 350 */       pos -= 0.01D;
/*     */     }
/*     */     
/* 353 */     if (pos < -2.4D && !up) {
/* 354 */       up = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private void avatar(float equipProgress, float swingProgress) {
/* 359 */     GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
/* 360 */     GlStateManager.translate(0.0F, 0.0F, 0.0F);
/* 361 */     GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
/* 362 */     float f = MathHelper.sin(swingProgress * swingProgress * 3.1415927F);
/* 363 */     float f1 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927F);
/* 364 */     GlStateManager.rotate(f * -20.0F, 0.0F, 1.0F, 0.0F);
/* 365 */     GlStateManager.rotate(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
/* 366 */     GlStateManager.rotate(f1 * -40.0F, 1.0F, 0.0F, 0.0F);
/* 367 */     GlStateManager.scale(0.4F, 0.4F, 0.4F);
/*     */   }
/*     */   
/*     */   private void nudge(float var2, float swingProgress) {
/* 371 */     float smooth = swingProgress * 0.8F - swingProgress * swingProgress * 0.8F;
/* 372 */     GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
/* 373 */     GlStateManager.translate(0.0F, var2 * -0.6F, 0.0F);
/* 374 */     GlStateManager.rotate(45.0F, 0.0F, 2.0F + smooth * 0.5F, smooth * 3.0F);
/* 375 */     float var3 = MathHelper.sin(swingProgress * swingProgress * 3.1415927F);
/* 376 */     float var4 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927F);
/* 377 */     GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
/* 378 */     GlStateManager.scale(0.37F, 0.37F, 0.37F);
/*     */   }
/*     */   
/*     */   private void func_178096_A(float p_178096_1_, float p_178096_2_) {
/* 382 */     GlStateManager.translate(0.62F, -0.66F, -0.71999997F);
/* 383 */     GlStateManager.translate(0.0F, p_178096_1_ * -0.6F, 0.0F);
/* 384 */     GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
/* 385 */     float var3 = MathHelper.sin(p_178096_2_ * p_178096_2_ * 3.1415927F);
/* 386 */     float var4 = MathHelper.sin(MathHelper.sqrt_float(p_178096_2_) * 3.1415927F);
/* 387 */     GlStateManager.rotate(var3 * -20.0F, 0.0F, 1.0F, 0.0F);
/* 388 */     GlStateManager.rotate(var4 * -20.0F, 0.0F, 0.0F, 1.0F);
/* 389 */     GlStateManager.rotate(var4 * -80.0F, 1.0F, 0.0F, 0.0F);
/* 390 */     GlStateManager.scale(0.4F, 0.4F, 0.4F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderItemInFirstPerson(float p_78440_1_) {
/* 398 */     float var2 = 1.0F - this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * p_78440_1_;
/* 399 */     EntityPlayerSP var3 = this.mc.thePlayer;
/* 400 */     float var4 = var3.getSwingProgress(p_78440_1_);
/* 401 */     float var5 = var3.prevRotationPitch + (var3.rotationPitch - var3.prevRotationPitch) * p_78440_1_;
/* 402 */     float var6 = var3.prevRotationYaw + (var3.rotationYaw - var3.prevRotationYaw) * p_78440_1_;
/* 403 */     func_178101_a(var5, var6);
/* 404 */     func_178109_a((AbstractClientPlayer)var3);
/* 405 */     func_178110_a(var3, p_78440_1_);
/* 406 */     GlStateManager.enableRescaleNormal();
/* 407 */     GlStateManager.pushMatrix();
/*     */     
/* 409 */     if (this.itemToRender != null) {
/*     */       
/* 411 */       if (this.itemToRender.getItem() instanceof net.minecraft.item.ItemMap) {
/*     */         
/* 413 */         func_178097_a((AbstractClientPlayer)var3, var5, var2, var4);
/*     */       }
/* 415 */       else if (var3.getItemInUseCount() > 0) {
/*     */ 
/*     */ 
/*     */         
/* 419 */         EnumAction var7 = this.itemToRender.getItemUseAction();
/*     */         
/* 421 */         switch (SwitchEnumAction.field_178094_a[var7.ordinal()]) {
/*     */           
/*     */           case 1:
/* 424 */             func_178096_b(var2, 0.0F);
/*     */             break;
/*     */           
/*     */           case 2:
/*     */           case 3:
/* 429 */             func_178104_a((AbstractClientPlayer)var3, p_78440_1_);
/* 430 */             func_178096_b(var2, 0.0F);
/*     */             break;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 4:
/* 437 */             if (Client.getModule("Animations").isEnabled()) {
/*     */               
/* 439 */               GlStateManager.translate(1.0D * Animations.blockx.getValue() * -Animations.blockscale.getValue(), 1.0D * Animations.blocky.getValue() * -Animations.blockscale.getValue(), 0.0D + Animations.blockscale.getValue());
/*     */               
/* 441 */               if (Animations.getMode() == "Astolfo") {
/* 442 */                 func_178096_b(var2, var4 / 32.0F);
/*     */               }
/* 444 */               if (Animations.getMode() == "Basic") {
/* 445 */                 func_178096_b(0.2F, var4 / 32.0F);
/*     */               }
/*     */               
/* 448 */               float var15 = MathHelper.sin(var4 * var4 * 3.1415927F);
/*     */               
/* 450 */               if (Animations.getMode() == "Leap") {
/* 451 */                 GlStateManager.translate(0.1D, 0.4D, -1.0D);
/* 452 */                 func_178096_b(var2, var4);
/*     */               } 
/*     */               
/* 455 */               if (Animations.getMode() == "Swing") {
/* 456 */                 transformFirstPersonItem(var2 / 2.0F, var4);
/*     */               }
/*     */               
/* 459 */               if (Animations.getMode() == "Swaing") {
/* 460 */                 func_178096_A(var2 / 2.0F, -0.2F);
/* 461 */                 var15 = MathHelper.sin(var4 * var4 * 3.1415927F);
/* 462 */                 GlStateManager.rotate(-var15 * 1.0F / 19.0F, var15 / 20.0F, -0.0F, 9.0F);
/* 463 */                 GlStateManager.rotate(-var15 * 30.0F, 10.0F, var15 / 50.0F, 0.0F);
/*     */               } 
/*     */               
/* 466 */               if (Animations.getMode() == "Swang") {
/* 467 */                 transformFirstPersonItem(var2 / 2.0F, var4);
/* 468 */                 var15 = MathHelper.sin(MathHelper.sqrt_float(var4) * 3.1415927F);
/* 469 */                 GlStateManager.rotate(var15 * 30.0F / 2.0F, -var15, -0.0F, 9.0F);
/* 470 */                 GlStateManager.rotate(var15 * 40.0F, 1.0F, -var15 / 2.0F, -0.0F);
/*     */               } 
/*     */ 
/*     */               
/* 474 */               if (Animations.getMode() == "Swank") {
/* 475 */                 transformFirstPersonItem(var2 / 2.0F, var4);
/* 476 */                 var15 = MathHelper.sin(MathHelper.sqrt_float(var4) * 3.1415927F);
/* 477 */                 GlStateManager.rotate(var15 * 30.0F, -var15, -0.0F, 9.0F);
/* 478 */                 GlStateManager.rotate(var15 * 40.0F, 1.0F, -var15, -0.0F);
/*     */               } 
/*     */               
/* 481 */               if (Animations.getMode() == "Swong") {
/* 482 */                 transformFirstPersonItem(var2 / 2.0F, 0.0F);
/* 483 */                 var15 = MathHelper.sin(var4 * var4 * 3.1415927F);
/* 484 */                 GlStateManager.rotate(-var15 * 40.0F / 2.0F, var15 / 2.0F, -0.0F, 9.0F);
/* 485 */                 GlStateManager.rotate(-var15 * 30.0F, 1.0F, var15 / 2.0F, -0.0F);
/*     */               } 
/*     */               
/* 488 */               if (Animations.getMode() == "Sigma") {
/* 489 */                 func_178096_b(var2 * 0.5F, 0.0F);
/* 490 */                 GlStateManager.rotate(-var15 * 55.0F / 2.0F, -8.0F, -0.0F, 9.0F);
/* 491 */                 GlStateManager.rotate(-var15 * 45.0F, 1.0F, var15 / 2.0F, -0.0F);
/*     */               } 
/* 493 */               if (Animations.getMode() == "Nudge") {
/* 494 */                 nudge(var2 * 0.3F, var4);
/*     */               }
/* 496 */               if (Animations.getMode() == "Avatar") {
/* 497 */                 avatar(var2 * 0.3F, var4);
/*     */               }
/*     */               
/* 500 */               if (Animations.getMode() == "Spin") {
/* 501 */                 GlStateManager.translate(0.1D, 0.4D, -1.0D);
/* 502 */                 func_178096_b(var2, 1.0F);
/*     */               } 
/*     */             } else {
/* 505 */               func_178096_b(var2, 0.0F);
/*     */             } 
/* 507 */             func_178103_d();
/*     */             
/* 509 */             if (this.pos < 1.0D && this.up) {
/* 510 */               this.pos += 0.04D;
/*     */             }
/* 512 */             if (this.pos > 0.8D) {
/* 513 */               this.up = false;
/* 514 */               this.pos -= 0.04D;
/*     */             } 
/* 516 */             if (!this.up) {
/* 517 */               this.pos -= 0.04D;
/*     */             }
/* 519 */             if (this.pos < -1.0D && !this.up) {
/* 520 */               this.up = true;
/*     */             }
/*     */             
/* 523 */             if (Client.getModule("Animations").isEnabled()) {
/*     */ 
/*     */ 
/*     */               
/* 527 */               if (Animations.getMode() == "Sigma") {
/* 528 */                 GL11.glTranslated(1.2D, 0.3D, 0.5D);
/* 529 */                 GL11.glTranslatef(-1.0F, this.mc.thePlayer.isSneaking() ? -0.1F : -0.2F, 0.2F);
/*     */               } 
/*     */               
/* 532 */               if (Animations.getMode() == "Basic") {
/* 533 */                 GlStateManager.translate(-this.pos, 0.2D, 0.0D);
/*     */               }
/* 535 */               if (Animations.getMode() == "Leap") {
/* 536 */                 GlStateManager.translate(-this.pos, 0.2D, 0.0D);
/*     */               }
/* 538 */               if (Animations.getMode() == "Spin") {
/* 539 */                 GlStateManager.rotate(var4 * 150.0F, -2.0F, 0.0F, 0.0F);
/* 540 */                 GlStateManager.translate(-this.pos, 0.2D, 0.0D);
/*     */               } 
/*     */             } 
/*     */             break;
/*     */           
/*     */           case 5:
/* 546 */             func_178096_b(var2, 0.0F);
/* 547 */             func_178098_a(p_78440_1_, (AbstractClientPlayer)var3);
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } else {
/* 553 */         func_178105_d(var4);
/* 554 */         func_178096_b(var2, var4);
/*     */         
/* 556 */         if (Client.getModule("Animations").isEnabled()) {
/* 557 */           GlStateManager.translate(1.0D * -Animations.scale.getValue(), Animations.y.getValue() * (-Animations.scale.getValue() + 1.0D), Animations.x.getValue() * (-Animations.scale.getValue() + 1.0D));
/*     */         }
/*     */       } 
/*     */       
/* 561 */       renderItem((EntityLivingBase)var3, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
/*     */     }
/* 563 */     else if (!var3.isInvisible()) {
/*     */       
/* 565 */       func_178095_a((AbstractClientPlayer)var3, var2, var4);
/*     */     } 
/*     */     
/* 568 */     GlStateManager.popMatrix();
/* 569 */     GlStateManager.disableRescaleNormal();
/* 570 */     RenderHelper.disableStandardItemLighting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderOverlays(float p_78447_1_) {
/* 578 */     GlStateManager.disableAlpha();
/*     */     
/* 580 */     if (this.mc.thePlayer.isEntityInsideOpaqueBlock()) {
/*     */       
/* 582 */       BlockPos blockPos = new BlockPos((Entity)this.mc.thePlayer);
/* 583 */       IBlockState var2 = this.mc.theWorld.getBlockState(blockPos);
/* 584 */       EntityPlayerSP var3 = this.mc.thePlayer;
/*     */       
/* 586 */       for (int overlayType = 0; overlayType < 8; overlayType++) {
/*     */         
/* 588 */         double var5 = var3.posX + ((((overlayType >> 0) % 2) - 0.5F) * var3.width * 0.8F);
/* 589 */         double var7 = var3.posY + ((((overlayType >> 1) % 2) - 0.5F) * 0.1F);
/* 590 */         double var9 = var3.posZ + ((((overlayType >> 2) % 2) - 0.5F) * var3.width * 0.8F);
/* 591 */         blockPos = new BlockPos(var5, var7 + var3.getEyeHeight(), var9);
/* 592 */         IBlockState var12 = this.mc.theWorld.getBlockState(blockPos);
/*     */         
/* 594 */         if (var12.getBlock().isVisuallyOpaque())
/*     */         {
/* 596 */           var2 = var12;
/*     */         }
/*     */       } 
/*     */       
/* 600 */       if (var2.getBlock().getRenderType() != -1) {
/*     */         
/* 602 */         Object var13 = Reflector.getFieldValue(Reflector.RenderBlockOverlayEvent_OverlayType_BLOCK);
/*     */         
/* 604 */         if (!Reflector.callBoolean(Reflector.ForgeEventFactory_renderBlockOverlay, new Object[] { this.mc.thePlayer, Float.valueOf(p_78447_1_), var13, var2, blockPos }))
/*     */         {
/* 606 */           func_178108_a(p_78447_1_, this.mc.getBlockRendererDispatcher().func_175023_a().func_178122_a(var2));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 611 */     if (!this.mc.thePlayer.func_175149_v()) {
/*     */       
/* 613 */       if (this.mc.thePlayer.isInsideOfMaterial(Material.water) && !Reflector.callBoolean(Reflector.ForgeEventFactory_renderWaterOverlay, new Object[] { this.mc.thePlayer, Float.valueOf(p_78447_1_) }))
/*     */       {
/* 615 */         renderWaterOverlayTexture(p_78447_1_);
/*     */       }
/*     */       
/* 618 */       if (this.mc.thePlayer.isBurning() && !Reflector.callBoolean(Reflector.ForgeEventFactory_renderFireOverlay, new Object[] { this.mc.thePlayer, Float.valueOf(p_78447_1_) }))
/*     */       {
/* 620 */         renderFireInFirstPerson(p_78447_1_);
/*     */       }
/*     */     } 
/*     */     
/* 624 */     GlStateManager.enableAlpha();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178108_a(float p_178108_1_, TextureAtlasSprite p_178108_2_) {
/* 629 */     this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 630 */     Tessellator var3 = Tessellator.getInstance();
/* 631 */     WorldRenderer var4 = var3.getWorldRenderer();
/* 632 */     float var5 = 0.1F;
/* 633 */     GlStateManager.color(var5, var5, var5, 20.0F);
/* 634 */     GlStateManager.pushMatrix();
/* 635 */     float var6 = -1.0F;
/* 636 */     float var7 = 1.0F;
/* 637 */     float var8 = -1.0F;
/* 638 */     float var9 = 1.0F;
/* 639 */     float var10 = -0.5F;
/* 640 */     float var11 = p_178108_2_.getMinU();
/* 641 */     float var12 = p_178108_2_.getMaxU();
/* 642 */     float var13 = p_178108_2_.getMinV();
/* 643 */     float var14 = p_178108_2_.getMaxV();
/* 644 */     var4.startDrawingQuads();
/* 645 */     var4.addVertexWithUV(var6, var8, var10, var12, var14);
/* 646 */     var4.addVertexWithUV(var7, var8, var10, var11, var14);
/* 647 */     var4.addVertexWithUV(var7, var9, var10, var11, var13);
/* 648 */     var4.addVertexWithUV(var6, var9, var10, var12, var13);
/* 649 */     var3.draw();
/* 650 */     GlStateManager.popMatrix();
/* 651 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderWaterOverlayTexture(float p_78448_1_) {
/* 660 */     this.mc.getTextureManager().bindTexture(RES_UNDERWATER_OVERLAY);
/* 661 */     Tessellator var2 = Tessellator.getInstance();
/* 662 */     WorldRenderer var3 = var2.getWorldRenderer();
/* 663 */     float var4 = this.mc.thePlayer.getBrightness(p_78448_1_);
/* 664 */     GlStateManager.color(var4, var4, var4, 0.5F);
/* 665 */     GlStateManager.enableBlend();
/* 666 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 667 */     GlStateManager.pushMatrix();
/* 668 */     float var5 = 4.0F;
/* 669 */     float var6 = -1.0F;
/* 670 */     float var7 = 1.0F;
/* 671 */     float var8 = -1.0F;
/* 672 */     float var9 = 1.0F;
/* 673 */     float var10 = -0.5F;
/* 674 */     float var11 = -this.mc.thePlayer.rotationYaw / 64.0F;
/* 675 */     float var12 = this.mc.thePlayer.rotationPitch / 64.0F;
/* 676 */     var3.startDrawingQuads();
/* 677 */     var3.addVertexWithUV(var6, var8, var10, (var5 + var11), (var5 + var12));
/* 678 */     var3.addVertexWithUV(var7, var8, var10, (0.0F + var11), (var5 + var12));
/* 679 */     var3.addVertexWithUV(var7, var9, var10, (0.0F + var11), (0.0F + var12));
/* 680 */     var3.addVertexWithUV(var6, var9, var10, (var5 + var11), (0.0F + var12));
/* 681 */     var2.draw();
/* 682 */     GlStateManager.popMatrix();
/* 683 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 684 */     GlStateManager.disableBlend();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void renderFireInFirstPerson(float p_78442_1_) {
/* 692 */     Tessellator var2 = Tessellator.getInstance();
/* 693 */     WorldRenderer var3 = var2.getWorldRenderer();
/* 694 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
/* 695 */     GlStateManager.depthFunc(519);
/* 696 */     GlStateManager.depthMask(false);
/* 697 */     GlStateManager.enableBlend();
/* 698 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 699 */     float var4 = 1.0F;
/*     */     
/* 701 */     for (int var5 = 0; var5 < 2; var5++) {
/*     */       
/* 703 */       GlStateManager.pushMatrix();
/* 704 */       TextureAtlasSprite var6 = this.mc.getTextureMapBlocks().getAtlasSprite("minecraft:blocks/fire_layer_1");
/* 705 */       this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 706 */       float var7 = var6.getMinU();
/* 707 */       float var8 = var6.getMaxU();
/* 708 */       float var9 = var6.getMinV();
/* 709 */       float var10 = var6.getMaxV();
/* 710 */       float var11 = (0.0F - var4) / 2.0F;
/* 711 */       float var12 = var11 + var4;
/* 712 */       float var13 = 0.0F - var4 / 2.0F;
/* 713 */       float var14 = var13 + var4;
/* 714 */       float var15 = -0.5F;
/* 715 */       GlStateManager.translate(-(var5 * 2 - 1) * 0.24F, -0.3F, 0.0F);
/* 716 */       GlStateManager.rotate((var5 * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
/* 717 */       var3.startDrawingQuads();
/* 718 */       var3.addVertexWithUV(var11, var13, var15, var8, var10);
/* 719 */       var3.addVertexWithUV(var12, var13, var15, var7, var10);
/* 720 */       var3.addVertexWithUV(var12, var14, var15, var7, var9);
/* 721 */       var3.addVertexWithUV(var11, var14, var15, var8, var9);
/* 722 */       var2.draw();
/* 723 */       GlStateManager.popMatrix();
/*     */     } 
/*     */     
/* 726 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 727 */     GlStateManager.disableBlend();
/* 728 */     GlStateManager.depthMask(true);
/* 729 */     GlStateManager.depthFunc(515);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateEquippedItem() {
/* 734 */     this.prevEquippedProgress = this.equippedProgress;
/* 735 */     EntityPlayerSP var1 = this.mc.thePlayer;
/* 736 */     ItemStack var2 = var1.inventory.getCurrentItem();
/* 737 */     boolean var3 = false;
/*     */     
/* 739 */     if (this.itemToRender != null && var2 != null) {
/*     */       
/* 741 */       if (!this.itemToRender.getIsItemStackEqual(var2))
/*     */       {
/* 743 */         if (Reflector.ForgeItem_shouldCauseReequipAnimation.exists()) {
/*     */           
/* 745 */           boolean var4 = Reflector.callBoolean(this.itemToRender.getItem(), Reflector.ForgeItem_shouldCauseReequipAnimation, new Object[] { this.itemToRender, var2, Boolean.valueOf((this.equippedItemSlot != var1.inventory.currentItem)) });
/*     */           
/* 747 */           if (!var4) {
/*     */             
/* 749 */             this.itemToRender = var2;
/* 750 */             this.equippedItemSlot = var1.inventory.currentItem;
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/* 755 */         var3 = true;
/*     */       }
/*     */     
/* 758 */     } else if (this.itemToRender == null && var2 == null) {
/*     */       
/* 760 */       var3 = false;
/*     */     }
/*     */     else {
/*     */       
/* 764 */       var3 = true;
/*     */     } 
/*     */     
/* 767 */     float var41 = 0.4F;
/* 768 */     float var5 = var3 ? 0.0F : 1.0F;
/* 769 */     float var6 = MathHelper.clamp_float(var5 - this.equippedProgress, -var41, var41);
/* 770 */     this.equippedProgress += var6;
/*     */     
/* 772 */     if (this.equippedProgress < 0.1F) {
/*     */       
/* 774 */       if (Config.isShaders())
/*     */       {
/* 776 */         Shaders.itemToRender = var2;
/*     */       }
/*     */       
/* 779 */       this.itemToRender = var2;
/* 780 */       this.equippedItemSlot = var1.inventory.currentItem;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetEquippedProgress() {
/* 789 */     this.equippedProgress = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetEquippedProgress2() {
/* 797 */     this.equippedProgress = 0.0F;
/*     */   }
/*     */   
/*     */   static final class SwitchEnumAction
/*     */   {
/* 802 */     static final int[] field_178094_a = new int[(EnumAction.values()).length];
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 808 */         field_178094_a[EnumAction.NONE.ordinal()] = 1;
/*     */       }
/* 810 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 817 */         field_178094_a[EnumAction.EAT.ordinal()] = 2;
/*     */       }
/* 819 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 826 */         field_178094_a[EnumAction.DRINK.ordinal()] = 3;
/*     */       }
/* 828 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 835 */         field_178094_a[EnumAction.BLOCK.ordinal()] = 4;
/*     */       }
/* 837 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 844 */         field_178094_a[EnumAction.BOW.ordinal()] = 5;
/*     */       }
/* 846 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\ItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */