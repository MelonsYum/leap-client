/*     */ package shadersmod.client;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.ActiveRenderInfo;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.ItemRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderGlobal;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.culling.ClippingHelper;
/*     */ import net.minecraft.client.renderer.culling.Frustrum;
/*     */ import net.minecraft.client.renderer.culling.ICamera;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import optifine.Reflector;
/*     */ import org.lwjgl.opengl.EXTFramebufferObject;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GL20;
/*     */ import org.lwjgl.opengl.GL30;
/*     */ 
/*     */ 
/*     */ public class ShadersRender
/*     */ {
/*     */   public static void setFrustrumPosition(Frustrum frustrum, double x, double y, double z) {
/*  31 */     frustrum.setPosition(x, y, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setupTerrain(RenderGlobal renderGlobal, Entity viewEntity, double partialTicks, ICamera camera, int frameCount, boolean playerSpectator) {
/*  36 */     renderGlobal.func_174970_a(viewEntity, partialTicks, camera, frameCount, playerSpectator);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateChunks(RenderGlobal renderGlobal, long finishTimeNano) {
/*  41 */     renderGlobal.func_174967_a(finishTimeNano);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void beginTerrainSolid() {
/*  46 */     if (Shaders.isRenderingWorld) {
/*     */       
/*  48 */       Shaders.fogEnabled = true;
/*  49 */       Shaders.useProgram(7);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void beginTerrainCutoutMipped() {
/*  55 */     if (Shaders.isRenderingWorld)
/*     */     {
/*  57 */       Shaders.useProgram(7);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void beginTerrainCutout() {
/*  63 */     if (Shaders.isRenderingWorld)
/*     */     {
/*  65 */       Shaders.useProgram(7);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void endTerrain() {
/*  71 */     if (Shaders.isRenderingWorld)
/*     */     {
/*  73 */       Shaders.useProgram(3);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void beginTranslucent() {
/*  79 */     if (Shaders.isRenderingWorld) {
/*     */       
/*  81 */       if (Shaders.usedDepthBuffers >= 2) {
/*     */         
/*  83 */         GlStateManager.setActiveTexture(33995);
/*  84 */         Shaders.checkGLError("pre copy depth");
/*  85 */         GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, Shaders.renderWidth, Shaders.renderHeight);
/*  86 */         Shaders.checkGLError("copy depth");
/*  87 */         GlStateManager.setActiveTexture(33984);
/*     */       } 
/*     */       
/*  90 */       Shaders.useProgram(12);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void endTranslucent() {
/*  96 */     if (Shaders.isRenderingWorld)
/*     */     {
/*  98 */       Shaders.useProgram(3);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderHand0(EntityRenderer er, float par1, int par2) {
/* 104 */     if (!Shaders.isShadowPass) {
/*     */       
/* 106 */       Item item = (Shaders.itemToRender != null) ? Shaders.itemToRender.getItem() : null;
/* 107 */       Block block = (item instanceof ItemBlock) ? ((ItemBlock)item).getBlock() : null;
/*     */       
/* 109 */       if (!(item instanceof ItemBlock) || !(block instanceof Block) || block.getBlockLayer() == EnumWorldBlockLayer.SOLID) {
/*     */         
/* 111 */         Shaders.readCenterDepth();
/* 112 */         Shaders.beginHand();
/* 113 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 114 */         er.renderHand(par1, par2);
/* 115 */         Shaders.endHand();
/* 116 */         Shaders.isHandRendered = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderHand1(EntityRenderer er, float par1, int par2) {
/* 123 */     if (!Shaders.isShadowPass && !Shaders.isHandRendered) {
/*     */       
/* 125 */       Shaders.readCenterDepth();
/* 126 */       GlStateManager.enableBlend();
/* 127 */       Shaders.beginHand();
/* 128 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 129 */       er.renderHand(par1, par2);
/* 130 */       Shaders.endHand();
/* 131 */       Shaders.isHandRendered = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderItemFP(ItemRenderer itemRenderer, float par1) {
/* 137 */     GlStateManager.depthMask(true);
/* 138 */     GlStateManager.depthFunc(515);
/* 139 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 140 */     itemRenderer.renderItemInFirstPerson(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderFPOverlay(EntityRenderer er, float par1, int par2) {
/* 145 */     if (!Shaders.isShadowPass) {
/*     */       
/* 147 */       Shaders.beginFPOverlay();
/* 148 */       er.renderHand(par1, par2);
/* 149 */       Shaders.endFPOverlay();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void beginBlockDamage() {
/* 155 */     if (Shaders.isRenderingWorld) {
/*     */       
/* 157 */       Shaders.useProgram(11);
/*     */       
/* 159 */       if (Shaders.programsID[11] == Shaders.programsID[7]) {
/*     */         
/* 161 */         Shaders.setDrawBuffers(Shaders.drawBuffersColorAtt0);
/* 162 */         GlStateManager.depthMask(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void endBlockDamage() {
/* 169 */     if (Shaders.isRenderingWorld) {
/*     */       
/* 171 */       GlStateManager.depthMask(true);
/* 172 */       Shaders.useProgram(3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderShadowMap(EntityRenderer entityRenderer, int pass, float partialTicks, long finishTimeNano) {
/* 178 */     if (Shaders.usedShadowDepthBuffers > 0 && --Shaders.shadowPassCounter <= 0) {
/*     */       
/* 180 */       Minecraft mc = Minecraft.getMinecraft();
/* 181 */       mc.mcProfiler.endStartSection("shadow pass");
/* 182 */       RenderGlobal renderGlobal = mc.renderGlobal;
/* 183 */       Shaders.isShadowPass = true;
/* 184 */       Shaders.shadowPassCounter = Shaders.shadowPassInterval;
/* 185 */       Shaders.preShadowPassThirdPersonView = mc.gameSettings.thirdPersonView;
/* 186 */       mc.gameSettings.thirdPersonView = 1;
/* 187 */       Shaders.checkGLError("pre shadow");
/* 188 */       GL11.glMatrixMode(5889);
/* 189 */       GL11.glPushMatrix();
/* 190 */       GL11.glMatrixMode(5888);
/* 191 */       GL11.glPushMatrix();
/* 192 */       mc.mcProfiler.endStartSection("shadow clear");
/* 193 */       EXTFramebufferObject.glBindFramebufferEXT(36160, Shaders.sfb);
/* 194 */       Shaders.checkGLError("shadow bind sfb");
/* 195 */       Shaders.useProgram(30);
/* 196 */       mc.mcProfiler.endStartSection("shadow camera");
/* 197 */       entityRenderer.setupCameraTransform(partialTicks, 2);
/* 198 */       Shaders.setCameraShadow(partialTicks);
/* 199 */       ActiveRenderInfo.updateRenderInfo((EntityPlayer)mc.thePlayer, (mc.gameSettings.thirdPersonView == 2));
/* 200 */       Shaders.checkGLError("shadow camera");
/* 201 */       GL20.glDrawBuffers(Shaders.sfbDrawBuffers);
/* 202 */       Shaders.checkGLError("shadow drawbuffers");
/* 203 */       GL11.glReadBuffer(0);
/* 204 */       Shaders.checkGLError("shadow readbuffer");
/* 205 */       EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, Shaders.sfbDepthTextures.get(0), 0);
/*     */       
/* 207 */       if (Shaders.usedShadowColorBuffers != 0)
/*     */       {
/* 209 */         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, Shaders.sfbColorTextures.get(0), 0);
/*     */       }
/*     */       
/* 212 */       Shaders.checkFramebufferStatus("shadow fb");
/* 213 */       GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
/* 214 */       GL11.glClear((Shaders.usedShadowColorBuffers != 0) ? 16640 : 256);
/* 215 */       Shaders.checkGLError("shadow clear");
/* 216 */       mc.mcProfiler.endStartSection("shadow frustum");
/* 217 */       ClippingHelper clippingHelper = ClippingHelperShadow.getInstance();
/* 218 */       mc.mcProfiler.endStartSection("shadow culling");
/* 219 */       Frustrum frustum = new Frustrum(clippingHelper);
/* 220 */       Entity viewEntity = mc.func_175606_aa();
/* 221 */       double viewPosX = viewEntity.lastTickPosX + (viewEntity.posX - viewEntity.lastTickPosX) * partialTicks;
/* 222 */       double viewPosY = viewEntity.lastTickPosY + (viewEntity.posY - viewEntity.lastTickPosY) * partialTicks;
/* 223 */       double viewPosZ = viewEntity.lastTickPosZ + (viewEntity.posZ - viewEntity.lastTickPosZ) * partialTicks;
/* 224 */       frustum.setPosition(viewPosX, viewPosY, viewPosZ);
/* 225 */       GlStateManager.shadeModel(7425);
/* 226 */       GlStateManager.enableDepth();
/* 227 */       GlStateManager.depthFunc(515);
/* 228 */       GlStateManager.depthMask(true);
/* 229 */       GlStateManager.colorMask(true, true, true, true);
/* 230 */       GlStateManager.disableCull();
/* 231 */       mc.mcProfiler.endStartSection("shadow prepareterrain");
/* 232 */       mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 233 */       mc.mcProfiler.endStartSection("shadow setupterrain");
/* 234 */       boolean frameCount = false;
/* 235 */       int var17 = entityRenderer.field_175084_ae;
/* 236 */       entityRenderer.field_175084_ae = var17 + 1;
/* 237 */       renderGlobal.func_174970_a(viewEntity, partialTicks, (ICamera)frustum, var17, mc.thePlayer.func_175149_v());
/* 238 */       mc.mcProfiler.endStartSection("shadow updatechunks");
/* 239 */       mc.mcProfiler.endStartSection("shadow terrain");
/* 240 */       GlStateManager.matrixMode(5888);
/* 241 */       GlStateManager.pushMatrix();
/* 242 */       GlStateManager.disableAlpha();
/* 243 */       renderGlobal.func_174977_a(EnumWorldBlockLayer.SOLID, partialTicks, 2, viewEntity);
/* 244 */       Shaders.checkGLError("shadow terrain solid");
/* 245 */       GlStateManager.enableAlpha();
/* 246 */       renderGlobal.func_174977_a(EnumWorldBlockLayer.CUTOUT_MIPPED, partialTicks, 2, viewEntity);
/* 247 */       Shaders.checkGLError("shadow terrain cutoutmipped");
/* 248 */       mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).func_174936_b(false, false);
/* 249 */       renderGlobal.func_174977_a(EnumWorldBlockLayer.CUTOUT, partialTicks, 2, viewEntity);
/* 250 */       Shaders.checkGLError("shadow terrain cutout");
/* 251 */       mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).func_174935_a();
/* 252 */       GlStateManager.shadeModel(7424);
/* 253 */       GlStateManager.alphaFunc(516, 0.1F);
/* 254 */       GlStateManager.matrixMode(5888);
/* 255 */       GlStateManager.popMatrix();
/* 256 */       GlStateManager.pushMatrix();
/* 257 */       mc.mcProfiler.endStartSection("shadow entities");
/*     */       
/* 259 */       if (Reflector.ForgeHooksClient_setRenderPass.exists())
/*     */       {
/* 261 */         Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(0) });
/*     */       }
/*     */       
/* 264 */       renderGlobal.func_180446_a(viewEntity, (ICamera)frustum, partialTicks);
/* 265 */       Shaders.checkGLError("shadow entities");
/* 266 */       GlStateManager.matrixMode(5888);
/* 267 */       GlStateManager.popMatrix();
/* 268 */       GlStateManager.depthMask(true);
/* 269 */       GlStateManager.disableBlend();
/* 270 */       GlStateManager.enableCull();
/* 271 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 272 */       GlStateManager.alphaFunc(516, 0.1F);
/*     */       
/* 274 */       if (Shaders.usedShadowDepthBuffers >= 2) {
/*     */         
/* 276 */         GlStateManager.setActiveTexture(33989);
/* 277 */         Shaders.checkGLError("pre copy shadow depth");
/* 278 */         GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, Shaders.shadowMapWidth, Shaders.shadowMapHeight);
/* 279 */         Shaders.checkGLError("copy shadow depth");
/* 280 */         GlStateManager.setActiveTexture(33984);
/*     */       } 
/*     */       
/* 283 */       GlStateManager.disableBlend();
/* 284 */       GlStateManager.depthMask(true);
/* 285 */       mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 286 */       GlStateManager.shadeModel(7425);
/* 287 */       Shaders.checkGLError("shadow pre-translucent");
/* 288 */       GL20.glDrawBuffers(Shaders.sfbDrawBuffers);
/* 289 */       Shaders.checkGLError("shadow drawbuffers pre-translucent");
/* 290 */       Shaders.checkFramebufferStatus("shadow pre-translucent");
/* 291 */       mc.mcProfiler.endStartSection("shadow translucent");
/* 292 */       renderGlobal.func_174977_a(EnumWorldBlockLayer.TRANSLUCENT, partialTicks, 2, viewEntity);
/* 293 */       Shaders.checkGLError("shadow translucent");
/*     */       
/* 295 */       if (Reflector.ForgeHooksClient_setRenderPass.exists()) {
/*     */         
/* 297 */         RenderHelper.enableStandardItemLighting();
/* 298 */         Reflector.call(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(1) });
/* 299 */         renderGlobal.func_180446_a(viewEntity, (ICamera)frustum, partialTicks);
/* 300 */         Reflector.call(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(-1) });
/* 301 */         RenderHelper.disableStandardItemLighting();
/* 302 */         Shaders.checkGLError("shadow entities 1");
/*     */       } 
/*     */       
/* 305 */       GlStateManager.shadeModel(7424);
/* 306 */       GlStateManager.depthMask(true);
/* 307 */       GlStateManager.enableCull();
/* 308 */       GlStateManager.disableBlend();
/* 309 */       GL11.glFlush();
/* 310 */       Shaders.checkGLError("shadow flush");
/* 311 */       Shaders.isShadowPass = false;
/* 312 */       mc.gameSettings.thirdPersonView = Shaders.preShadowPassThirdPersonView;
/* 313 */       mc.mcProfiler.endStartSection("shadow postprocess");
/*     */       
/* 315 */       if (Shaders.hasGlGenMipmap) {
/*     */         
/* 317 */         if (Shaders.usedShadowDepthBuffers >= 1) {
/*     */           
/* 319 */           if (Shaders.shadowMipmapEnabled[0]) {
/*     */             
/* 321 */             GlStateManager.setActiveTexture(33988);
/* 322 */             GlStateManager.func_179144_i(Shaders.sfbDepthTextures.get(0));
/* 323 */             GL30.glGenerateMipmap(3553);
/* 324 */             GL11.glTexParameteri(3553, 10241, Shaders.shadowFilterNearest[0] ? 9984 : 9987);
/*     */           } 
/*     */           
/* 327 */           if (Shaders.usedShadowDepthBuffers >= 2 && Shaders.shadowMipmapEnabled[1]) {
/*     */             
/* 329 */             GlStateManager.setActiveTexture(33989);
/* 330 */             GlStateManager.func_179144_i(Shaders.sfbDepthTextures.get(1));
/* 331 */             GL30.glGenerateMipmap(3553);
/* 332 */             GL11.glTexParameteri(3553, 10241, Shaders.shadowFilterNearest[1] ? 9984 : 9987);
/*     */           } 
/*     */           
/* 335 */           GlStateManager.setActiveTexture(33984);
/*     */         } 
/*     */         
/* 338 */         if (Shaders.usedShadowColorBuffers >= 1) {
/*     */           
/* 340 */           if (Shaders.shadowColorMipmapEnabled[0]) {
/*     */             
/* 342 */             GlStateManager.setActiveTexture(33997);
/* 343 */             GlStateManager.func_179144_i(Shaders.sfbColorTextures.get(0));
/* 344 */             GL30.glGenerateMipmap(3553);
/* 345 */             GL11.glTexParameteri(3553, 10241, Shaders.shadowColorFilterNearest[0] ? 9984 : 9987);
/*     */           } 
/*     */           
/* 348 */           if (Shaders.usedShadowColorBuffers >= 2 && Shaders.shadowColorMipmapEnabled[1]) {
/*     */             
/* 350 */             GlStateManager.setActiveTexture(33998);
/* 351 */             GlStateManager.func_179144_i(Shaders.sfbColorTextures.get(1));
/* 352 */             GL30.glGenerateMipmap(3553);
/* 353 */             GL11.glTexParameteri(3553, 10241, Shaders.shadowColorFilterNearest[1] ? 9984 : 9987);
/*     */           } 
/*     */           
/* 356 */           GlStateManager.setActiveTexture(33984);
/*     */         } 
/*     */       } 
/*     */       
/* 360 */       Shaders.checkGLError("shadow postprocess");
/* 361 */       EXTFramebufferObject.glBindFramebufferEXT(36160, Shaders.dfb);
/* 362 */       GL11.glViewport(0, 0, Shaders.renderWidth, Shaders.renderHeight);
/* 363 */       Shaders.activeDrawBuffers = null;
/* 364 */       mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 365 */       Shaders.useProgram(7);
/* 366 */       GL11.glMatrixMode(5888);
/* 367 */       GL11.glPopMatrix();
/* 368 */       GL11.glMatrixMode(5889);
/* 369 */       GL11.glPopMatrix();
/* 370 */       GL11.glMatrixMode(5888);
/* 371 */       Shaders.checkGLError("shadow end");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void preRenderChunkLayer() {
/* 377 */     if (OpenGlHelper.func_176075_f()) {
/*     */       
/* 379 */       GL11.glEnableClientState(32885);
/* 380 */       GL20.glEnableVertexAttribArray(Shaders.midTexCoordAttrib);
/* 381 */       GL20.glEnableVertexAttribArray(Shaders.tangentAttrib);
/* 382 */       GL20.glEnableVertexAttribArray(Shaders.entityAttrib);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void postRenderChunkLayer() {
/* 388 */     if (OpenGlHelper.func_176075_f()) {
/*     */       
/* 390 */       GL11.glDisableClientState(32885);
/* 391 */       GL20.glDisableVertexAttribArray(Shaders.midTexCoordAttrib);
/* 392 */       GL20.glDisableVertexAttribArray(Shaders.tangentAttrib);
/* 393 */       GL20.glDisableVertexAttribArray(Shaders.entityAttrib);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setupArrayPointersVbo() {
/* 399 */     boolean vertexSizeI = true;
/* 400 */     GL11.glVertexPointer(3, 5126, 56, 0L);
/* 401 */     GL11.glColorPointer(4, 5121, 56, 12L);
/* 402 */     GL11.glTexCoordPointer(2, 5126, 56, 16L);
/* 403 */     OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 404 */     GL11.glTexCoordPointer(2, 5122, 56, 24L);
/* 405 */     OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/* 406 */     GL11.glNormalPointer(5120, 56, 28L);
/* 407 */     GL20.glVertexAttribPointer(Shaders.midTexCoordAttrib, 2, 5126, false, 56, 32L);
/* 408 */     GL20.glVertexAttribPointer(Shaders.tangentAttrib, 4, 5122, false, 56, 40L);
/* 409 */     GL20.glVertexAttribPointer(Shaders.entityAttrib, 3, 5122, false, 56, 48L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void beaconBeamBegin() {
/* 414 */     Shaders.useProgram(14);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void beaconBeamStartQuad1() {}
/*     */   
/*     */   public static void beaconBeamStartQuad2() {}
/*     */   
/*     */   public static void beaconBeamDraw1() {}
/*     */   
/*     */   public static void beaconBeamDraw2() {
/* 425 */     GlStateManager.disableBlend();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void layerArmorBaseDrawEnchantedGlintBegin() {
/* 430 */     Shaders.useProgram(17);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void layerArmorBaseDrawEnchantedGlintEnd() {
/* 435 */     if (Shaders.isRenderingWorld) {
/*     */       
/* 437 */       Shaders.useProgram(16);
/*     */     }
/*     */     else {
/*     */       
/* 441 */       Shaders.useProgram(0);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\shadersmod\client\ShadersRender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */