/*     */ package net.minecraft.client.renderer.entity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EnumPlayerModelParts;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import optifine.Config;
/*     */ import optifine.Reflector;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import shadersmod.client.Shaders;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RendererLivingEntity
/*     */   extends Render
/*     */ {
/*  40 */   private static final Logger logger = LogManager.getLogger();
/*  41 */   private static final DynamicTexture field_177096_e = new DynamicTexture(16, 16);
/*     */   protected ModelBase mainModel;
/*  43 */   protected FloatBuffer field_177095_g = GLAllocation.createDirectFloatBuffer(4);
/*  44 */   protected List field_177097_h = Lists.newArrayList();
/*     */   protected boolean field_177098_i = false;
/*     */   private static final String __OBFID = "CL_00001012";
/*  47 */   public static float NAME_TAG_RANGE = 64.0F;
/*  48 */   public static float NAME_TAG_RANGE_SNEAK = 32.0F;
/*     */ 
/*     */   
/*     */   public RendererLivingEntity(RenderManager p_i46156_1_, ModelBase p_i46156_2_, float p_i46156_3_) {
/*  52 */     super(p_i46156_1_);
/*  53 */     this.mainModel = p_i46156_2_;
/*  54 */     this.shadowSize = p_i46156_3_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addLayer(LayerRenderer p_177094_1_) {
/*  59 */     return this.field_177097_h.add(p_177094_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_177089_b(LayerRenderer p_177089_1_) {
/*  64 */     return this.field_177097_h.remove(p_177089_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBase getMainModel() {
/*  69 */     return this.mainModel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float interpolateRotation(float p_77034_1_, float p_77034_2_, float p_77034_3_) {
/*     */     float var4;
/*  81 */     for (var4 = p_77034_2_ - p_77034_1_; var4 < -180.0F; var4 += 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     while (var4 >= 180.0F)
/*     */     {
/*  88 */       var4 -= 360.0F;
/*     */     }
/*     */     
/*  91 */     return p_77034_1_ + p_77034_3_ * var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_82422_c() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
/* 104 */     if (!Reflector.RenderLivingEvent_Pre_Constructor.exists() || !Reflector.postForgeBusEvent(Reflector.RenderLivingEvent_Pre_Constructor, new Object[] { p_76986_1_, this, Double.valueOf(p_76986_2_), Double.valueOf(p_76986_4_), Double.valueOf(p_76986_6_) })) {
/*     */ 
/*     */       
/* 107 */       GlStateManager.pushMatrix();
/* 108 */       GlStateManager.disableCull();
/* 109 */       this.mainModel.swingProgress = getSwingProgress(p_76986_1_, p_76986_9_);
/* 110 */       this.mainModel.isRiding = p_76986_1_.isRiding();
/*     */       
/* 112 */       if (Reflector.ForgeEntity_shouldRiderSit.exists())
/*     */       {
/* 114 */         this.mainModel.isRiding = (p_76986_1_.isRiding() && p_76986_1_.ridingEntity != null && Reflector.callBoolean(p_76986_1_.ridingEntity, Reflector.ForgeEntity_shouldRiderSit, new Object[0]));
/*     */       }
/*     */       
/* 117 */       this.mainModel.isChild = p_76986_1_.isChild();
/*     */ 
/*     */       
/*     */       try {
/* 121 */         float var19 = interpolateRotation(p_76986_1_.prevRenderYawOffset, p_76986_1_.renderYawOffset, p_76986_9_);
/* 122 */         float var11 = interpolateRotation(p_76986_1_.prevRotationYawHead, p_76986_1_.rotationYawHead, p_76986_9_);
/* 123 */         float var12 = var11 - var19;
/*     */ 
/*     */         
/* 126 */         if (this.mainModel.isRiding && p_76986_1_.ridingEntity instanceof EntityLivingBase) {
/*     */           
/* 128 */           EntityLivingBase var20 = (EntityLivingBase)p_76986_1_.ridingEntity;
/* 129 */           var19 = interpolateRotation(var20.prevRenderYawOffset, var20.renderYawOffset, p_76986_9_);
/* 130 */           var12 = var11 - var19;
/* 131 */           float f = MathHelper.wrapAngleTo180_float(var12);
/*     */           
/* 133 */           if (f < -85.0F)
/*     */           {
/* 135 */             f = -85.0F;
/*     */           }
/*     */           
/* 138 */           if (f >= 85.0F)
/*     */           {
/* 140 */             f = 85.0F;
/*     */           }
/*     */           
/* 143 */           var19 = var11 - f;
/*     */           
/* 145 */           if (f * f > 2500.0F)
/*     */           {
/* 147 */             var19 += f * 0.2F;
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 153 */         float var201 = p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_;
/* 154 */         renderLivingAt(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
/* 155 */         float var14 = handleRotationFloat(p_76986_1_, p_76986_9_);
/* 156 */         rotateCorpse(p_76986_1_, var14, var19, p_76986_9_);
/* 157 */         GlStateManager.enableRescaleNormal();
/* 158 */         GlStateManager.scale(-1.0F, -1.0F, 1.0F);
/* 159 */         preRenderCallback(p_76986_1_, p_76986_9_);
/* 160 */         float var15 = 0.0625F;
/* 161 */         GlStateManager.translate(0.0F, -1.5078125F, 0.0F);
/* 162 */         float var16 = p_76986_1_.prevLimbSwingAmount + (p_76986_1_.limbSwingAmount - p_76986_1_.prevLimbSwingAmount) * p_76986_9_;
/* 163 */         float var17 = p_76986_1_.limbSwing - p_76986_1_.limbSwingAmount * (1.0F - p_76986_9_);
/*     */         
/* 165 */         if (p_76986_1_.isChild())
/*     */         {
/* 167 */           var17 *= 3.0F;
/*     */         }
/*     */         
/* 170 */         if (var16 > 1.0F)
/*     */         {
/* 172 */           var16 = 1.0F;
/*     */         }
/*     */         
/* 175 */         GlStateManager.enableAlpha();
/* 176 */         this.mainModel.setLivingAnimations(p_76986_1_, var17, var16, p_76986_9_);
/* 177 */         this.mainModel.setRotationAngles(var17, var16, var14, var12, var201, 0.0625F, (Entity)p_76986_1_);
/*     */ 
/*     */         
/* 180 */         if (this.field_177098_i) {
/*     */           
/* 182 */           boolean var18 = func_177088_c(p_76986_1_);
/* 183 */           renderModel(p_76986_1_, var17, var16, var14, var12, var201, 0.0625F);
/*     */           
/* 185 */           if (var18)
/*     */           {
/* 187 */             func_180565_e();
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 192 */           boolean var18 = func_177090_c(p_76986_1_, p_76986_9_);
/* 193 */           renderModel(p_76986_1_, var17, var16, var14, var12, var201, 0.0625F);
/*     */           
/* 195 */           if (var18)
/*     */           {
/* 197 */             func_177091_f();
/*     */           }
/*     */           
/* 200 */           GlStateManager.depthMask(true);
/*     */           
/* 202 */           if (!(p_76986_1_ instanceof EntityPlayer) || !((EntityPlayer)p_76986_1_).func_175149_v())
/*     */           {
/* 204 */             func_177093_a(p_76986_1_, var17, var16, p_76986_9_, var14, var12, var201, 0.0625F);
/*     */           }
/*     */         } 
/*     */         
/* 208 */         GlStateManager.disableRescaleNormal();
/*     */       }
/* 210 */       catch (Exception var191) {
/*     */         
/* 212 */         logger.error("Couldn't render entity", var191);
/*     */       } 
/*     */       
/* 215 */       GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 216 */       GlStateManager.func_179098_w();
/* 217 */       GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 218 */       GlStateManager.enableCull();
/* 219 */       GlStateManager.popMatrix();
/*     */       
/* 221 */       if (!this.field_177098_i)
/*     */       {
/* 223 */         super.doRender((Entity)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */       }
/*     */       
/* 226 */       if (!Reflector.RenderLivingEvent_Post_Constructor.exists() || !Reflector.postForgeBusEvent(Reflector.RenderLivingEvent_Post_Constructor, new Object[] { p_76986_1_, this, Double.valueOf(p_76986_2_), Double.valueOf(p_76986_4_), Double.valueOf(p_76986_6_) }));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_177088_c(EntityLivingBase p_177088_1_) {
/* 236 */     int var2 = 16777215;
/*     */     
/* 238 */     if (p_177088_1_ instanceof EntityPlayer) {
/*     */       
/* 240 */       ScorePlayerTeam var6 = (ScorePlayerTeam)p_177088_1_.getTeam();
/*     */       
/* 242 */       if (var6 != null) {
/*     */         
/* 244 */         String var7 = FontRenderer.getFormatFromString(var6.getColorPrefix());
/*     */         
/* 246 */         if (var7.length() >= 2)
/*     */         {
/* 248 */           var2 = getFontRendererFromRenderManager().func_175064_b(var7.charAt(1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 253 */     float var61 = (var2 >> 16 & 0xFF) / 255.0F;
/* 254 */     float var71 = (var2 >> 8 & 0xFF) / 255.0F;
/* 255 */     float var5 = (var2 & 0xFF) / 255.0F;
/* 256 */     GlStateManager.disableLighting();
/* 257 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 258 */     GlStateManager.color(var61, var71, var5, 1.0F);
/* 259 */     GlStateManager.func_179090_x();
/* 260 */     GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 261 */     GlStateManager.func_179090_x();
/* 262 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 263 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180565_e() {
/* 268 */     GlStateManager.enableLighting();
/* 269 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 270 */     GlStateManager.func_179098_w();
/* 271 */     GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 272 */     GlStateManager.func_179098_w();
/* 273 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderModel(EntityLivingBase p_77036_1_, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
/* 281 */     boolean var8 = !p_77036_1_.isInvisible();
/* 282 */     boolean var9 = (!var8 && !p_77036_1_.isInvisibleToPlayer((EntityPlayer)(Minecraft.getMinecraft()).thePlayer));
/*     */     
/* 284 */     if (var8 || var9) {
/*     */       
/* 286 */       if (!bindEntityTexture((Entity)p_77036_1_)) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 291 */       if (var9) {
/*     */         
/* 293 */         GlStateManager.pushMatrix();
/* 294 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 0.15F);
/* 295 */         GlStateManager.depthMask(false);
/* 296 */         GlStateManager.enableBlend();
/* 297 */         GlStateManager.blendFunc(770, 771);
/* 298 */         GlStateManager.alphaFunc(516, 0.003921569F);
/*     */       } 
/*     */       
/* 301 */       this.mainModel.render((Entity)p_77036_1_, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
/*     */       
/* 303 */       if (var9) {
/*     */         
/* 305 */         GlStateManager.disableBlend();
/* 306 */         GlStateManager.alphaFunc(516, 0.1F);
/* 307 */         GlStateManager.popMatrix();
/* 308 */         GlStateManager.depthMask(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_177090_c(EntityLivingBase p_177090_1_, float p_177090_2_) {
/* 315 */     return func_177092_a(p_177090_1_, p_177090_2_, true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_177092_a(EntityLivingBase p_177092_1_, float p_177092_2_, boolean p_177092_3_) {
/* 320 */     float var4 = p_177092_1_.getBrightness(p_177092_2_);
/* 321 */     int var5 = getColorMultiplier(p_177092_1_, var4, p_177092_2_);
/* 322 */     boolean var6 = ((var5 >> 24 & 0xFF) > 0);
/* 323 */     boolean var7 = !(p_177092_1_.hurtTime <= 0 && p_177092_1_.deathTime <= 0);
/*     */     
/* 325 */     if (!var6 && !var7)
/*     */     {
/* 327 */       return false;
/*     */     }
/* 329 */     if (!var6 && !p_177092_3_)
/*     */     {
/* 331 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 335 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 336 */     GlStateManager.func_179098_w();
/* 337 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/* 338 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/* 339 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.defaultTexUnit);
/* 340 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176093_u);
/* 341 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/* 342 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/* 343 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 7681);
/* 344 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.defaultTexUnit);
/* 345 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/* 346 */     GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 347 */     GlStateManager.func_179098_w();
/* 348 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/* 349 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, OpenGlHelper.field_176094_t);
/* 350 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_176092_v);
/* 351 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
/* 352 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176080_A, OpenGlHelper.field_176092_v);
/* 353 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/* 354 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/* 355 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176076_D, 770);
/* 356 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 7681);
/* 357 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_176091_w);
/* 358 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/* 359 */     this.field_177095_g.position(0);
/*     */     
/* 361 */     if (var7) {
/*     */       
/* 363 */       this.field_177095_g.put(1.0F);
/* 364 */       this.field_177095_g.put(0.0F);
/* 365 */       this.field_177095_g.put(0.0F);
/* 366 */       this.field_177095_g.put(0.3F);
/*     */       
/* 368 */       if (Config.isShaders())
/*     */       {
/* 370 */         Shaders.setEntityColor(1.0F, 0.0F, 0.0F, 0.3F);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 375 */       float var8 = (var5 >> 24 & 0xFF) / 255.0F;
/* 376 */       float var9 = (var5 >> 16 & 0xFF) / 255.0F;
/* 377 */       float var10 = (var5 >> 8 & 0xFF) / 255.0F;
/* 378 */       float var11 = (var5 & 0xFF) / 255.0F;
/* 379 */       this.field_177095_g.put(var9);
/* 380 */       this.field_177095_g.put(var10);
/* 381 */       this.field_177095_g.put(var11);
/* 382 */       this.field_177095_g.put(1.0F - var8);
/*     */       
/* 384 */       if (Config.isShaders())
/*     */       {
/* 386 */         Shaders.setEntityColor(var9, var10, var11, 1.0F - var8);
/*     */       }
/*     */     } 
/*     */     
/* 390 */     this.field_177095_g.flip();
/* 391 */     GL11.glTexEnv(8960, 8705, this.field_177095_g);
/* 392 */     GlStateManager.setActiveTexture(OpenGlHelper.field_176096_r);
/* 393 */     GlStateManager.func_179098_w();
/* 394 */     GlStateManager.func_179144_i(field_177096_e.getGlTextureId());
/* 395 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/* 396 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/* 397 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.field_176091_w);
/* 398 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.lightmapTexUnit);
/* 399 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/* 400 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/* 401 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 7681);
/* 402 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.field_176091_w);
/* 403 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/* 404 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 405 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_177091_f() {
/* 411 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 412 */     GlStateManager.func_179098_w();
/* 413 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/* 414 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/* 415 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, OpenGlHelper.defaultTexUnit);
/* 416 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176093_u);
/* 417 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/* 418 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/* 419 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
/* 420 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, OpenGlHelper.defaultTexUnit);
/* 421 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176079_G, OpenGlHelper.field_176093_u);
/* 422 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/* 423 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176086_J, 770);
/* 424 */     GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 425 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/* 426 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/* 427 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/* 428 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/* 429 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, 5890);
/* 430 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
/* 431 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
/* 432 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/* 433 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, 5890);
/* 434 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 435 */     GlStateManager.setActiveTexture(OpenGlHelper.field_176096_r);
/* 436 */     GlStateManager.func_179090_x();
/* 437 */     GlStateManager.func_179144_i(0);
/* 438 */     GL11.glTexEnvi(8960, 8704, OpenGlHelper.field_176095_s);
/* 439 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176099_x, 8448);
/* 440 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176081_B, 768);
/* 441 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176082_C, 768);
/* 442 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176098_y, 5890);
/* 443 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176097_z, OpenGlHelper.field_176091_w);
/* 444 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176077_E, 8448);
/* 445 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176085_I, 770);
/* 446 */     GL11.glTexEnvi(8960, OpenGlHelper.field_176078_F, 5890);
/* 447 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*     */     
/* 449 */     if (Config.isShaders())
/*     */     {
/* 451 */       Shaders.setEntityColor(0.0F, 0.0F, 0.0F, 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderLivingAt(EntityLivingBase p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
/* 460 */     GlStateManager.translate((float)p_77039_2_, (float)p_77039_4_, (float)p_77039_6_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void rotateCorpse(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
/* 465 */     GlStateManager.rotate(180.0F - p_77043_3_, 0.0F, 1.0F, 0.0F);
/*     */     
/* 467 */     if (p_77043_1_.deathTime > 0) {
/*     */       
/* 469 */       float var6 = (p_77043_1_.deathTime + p_77043_4_ - 1.0F) / 20.0F * 1.6F;
/* 470 */       var6 = MathHelper.sqrt_float(var6);
/*     */       
/* 472 */       if (var6 > 1.0F)
/*     */       {
/* 474 */         var6 = 1.0F;
/*     */       }
/*     */       
/* 477 */       GlStateManager.rotate(var6 * getDeathMaxRotation(p_77043_1_), 0.0F, 0.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 481 */       String var61 = EnumChatFormatting.getTextWithoutFormattingCodes(p_77043_1_.getName());
/*     */       
/* 483 */       if (var61 != null && (var61.equals("Dinnerbone") || var61.equals("Grumm")) && (!(p_77043_1_ instanceof EntityPlayer) || ((EntityPlayer)p_77043_1_).func_175148_a(EnumPlayerModelParts.CAPE))) {
/*     */         
/* 485 */         GlStateManager.translate(0.0F, p_77043_1_.height + 0.1F, 0.0F);
/* 486 */         GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSwingProgress(EntityLivingBase p_77040_1_, float p_77040_2_) {
/* 496 */     return p_77040_1_.getSwingProgress(p_77040_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float handleRotationFloat(EntityLivingBase p_77044_1_, float p_77044_2_) {
/* 504 */     return p_77044_1_.ticksExisted + p_77044_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_177093_a(EntityLivingBase p_177093_1_, float p_177093_2_, float p_177093_3_, float p_177093_4_, float p_177093_5_, float p_177093_6_, float p_177093_7_, float p_177093_8_) {
/* 509 */     Iterator<LayerRenderer> var9 = this.field_177097_h.iterator();
/*     */     
/* 511 */     while (var9.hasNext()) {
/*     */       
/* 513 */       LayerRenderer var10 = var9.next();
/* 514 */       boolean var11 = func_177092_a(p_177093_1_, p_177093_4_, var10.shouldCombineTextures());
/* 515 */       var10.doRenderLayer(p_177093_1_, p_177093_2_, p_177093_3_, p_177093_4_, p_177093_5_, p_177093_6_, p_177093_7_, p_177093_8_);
/*     */       
/* 517 */       if (var11)
/*     */       {
/* 519 */         func_177091_f();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getDeathMaxRotation(EntityLivingBase p_77037_1_) {
/* 526 */     return 90.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
/* 534 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void passSpecialRender(EntityLivingBase p_77033_1_, double p_77033_2_, double p_77033_4_, double p_77033_6_) {
/* 548 */     if (!Reflector.RenderLivingEvent_Specials_Pre_Constructor.exists() || !Reflector.postForgeBusEvent(Reflector.RenderLivingEvent_Specials_Pre_Constructor, new Object[] { p_77033_1_, this, Double.valueOf(p_77033_2_), Double.valueOf(p_77033_4_), Double.valueOf(p_77033_6_) })) {
/*     */       
/* 550 */       if (canRenderName(p_77033_1_)) {
/*     */         
/* 552 */         double var8 = p_77033_1_.getDistanceSqToEntity(this.renderManager.livingPlayer);
/* 553 */         float var10 = p_77033_1_.isSneaking() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;
/*     */         
/* 555 */         if (var8 < (var10 * var10)) {
/*     */           
/* 557 */           String var11 = p_77033_1_.getDisplayName().getFormattedText();
/* 558 */           float var12 = 0.02666667F;
/* 559 */           GlStateManager.alphaFunc(516, 0.1F);
/*     */           
/* 561 */           if (p_77033_1_.isSneaking()) {
/*     */             
/* 563 */             FontRenderer var13 = getFontRendererFromRenderManager();
/* 564 */             GlStateManager.pushMatrix();
/* 565 */             GlStateManager.translate((float)p_77033_2_, (float)p_77033_4_ + p_77033_1_.height + 0.5F - (p_77033_1_.isChild() ? (p_77033_1_.height / 2.0F) : 0.0F), (float)p_77033_6_);
/* 566 */             GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 567 */             GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
/* 568 */             GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
/* 569 */             GlStateManager.scale(-0.02666667F, -0.02666667F, 0.02666667F);
/* 570 */             GlStateManager.translate(0.0F, 9.374999F, 0.0F);
/* 571 */             GlStateManager.disableLighting();
/* 572 */             GlStateManager.depthMask(false);
/* 573 */             GlStateManager.enableBlend();
/* 574 */             GlStateManager.func_179090_x();
/* 575 */             GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 576 */             Tessellator var14 = Tessellator.getInstance();
/* 577 */             WorldRenderer var15 = var14.getWorldRenderer();
/* 578 */             var15.startDrawingQuads();
/* 579 */             int var16 = var13.getStringWidth(var11) / 2;
/* 580 */             var15.func_178960_a(0.0F, 0.0F, 0.0F, 0.25F);
/* 581 */             var15.addVertex((-var16 - 1), -1.0D, 0.0D);
/* 582 */             var15.addVertex((-var16 - 1), 8.0D, 0.0D);
/* 583 */             var15.addVertex((var16 + 1), 8.0D, 0.0D);
/* 584 */             var15.addVertex((var16 + 1), -1.0D, 0.0D);
/* 585 */             var14.draw();
/* 586 */             GlStateManager.func_179098_w();
/* 587 */             GlStateManager.depthMask(true);
/* 588 */             var13.drawString(var11, (-var13.getStringWidth(var11) / 2), 0.0D, 553648127);
/* 589 */             GlStateManager.enableLighting();
/* 590 */             GlStateManager.disableBlend();
/* 591 */             GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 592 */             GlStateManager.popMatrix();
/*     */           }
/*     */           else {
/*     */             
/* 596 */             func_177069_a((Entity)p_77033_1_, p_77033_2_, p_77033_4_ - (p_77033_1_.isChild() ? (p_77033_1_.height / 2.0F) : 0.0D), p_77033_6_, var11, 0.02666667F, var8);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 601 */       if (!Reflector.RenderLivingEvent_Specials_Post_Constructor.exists() || !Reflector.postForgeBusEvent(Reflector.RenderLivingEvent_Specials_Post_Constructor, new Object[] { p_77033_1_, this, Double.valueOf(p_77033_2_), Double.valueOf(p_77033_4_), Double.valueOf(p_77033_6_) }));
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
/*     */   protected boolean canRenderName(EntityLivingBase targetEntity) {
/* 613 */     EntityPlayerSP var2 = (Minecraft.getMinecraft()).thePlayer;
/*     */     
/* 615 */     if (targetEntity instanceof EntityPlayer && targetEntity != var2) {
/*     */       
/* 617 */       Team var3 = targetEntity.getTeam();
/* 618 */       Team var4 = var2.getTeam();
/*     */       
/* 620 */       if (var3 != null) {
/*     */         
/* 622 */         Team.EnumVisible var5 = var3.func_178770_i();
/*     */         
/* 624 */         switch (SwitchEnumVisible.field_178679_a[var5.ordinal()]) {
/*     */           
/*     */           case 1:
/* 627 */             return true;
/*     */           
/*     */           case 2:
/* 630 */             return false;
/*     */           
/*     */           case 3:
/* 633 */             return !(var4 != null && !var3.isSameTeam(var4));
/*     */           
/*     */           case 4:
/* 636 */             return !(var4 != null && var3.isSameTeam(var4));
/*     */         } 
/*     */         
/* 639 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 644 */     return (Minecraft.isGuiEnabled() && targetEntity != this.renderManager.livingPlayer && !targetEntity.isInvisibleToPlayer((EntityPlayer)var2) && targetEntity.riddenByEntity == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177086_a(boolean p_177086_1_) {
/* 649 */     this.field_177098_i = p_177086_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_177070_b(Entity p_177070_1_) {
/* 654 */     return canRenderName((EntityLivingBase)p_177070_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_177067_a(Entity p_177067_1_, double p_177067_2_, double p_177067_4_, double p_177067_6_) {
/* 659 */     passSpecialRender((EntityLivingBase)p_177067_1_, p_177067_2_, p_177067_4_, p_177067_6_);
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
/* 670 */     doRender((EntityLivingBase)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 675 */     int[] var0 = field_177096_e.getTextureData();
/*     */     
/* 677 */     for (int var1 = 0; var1 < 256; var1++)
/*     */     {
/* 679 */       var0[var1] = -1;
/*     */     }
/*     */     
/* 682 */     field_177096_e.updateDynamicTexture();
/*     */   }
/*     */   
/*     */   static final class SwitchEnumVisible
/*     */   {
/* 687 */     static final int[] field_178679_a = new int[(Team.EnumVisible.values()).length];
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 693 */         field_178679_a[Team.EnumVisible.ALWAYS.ordinal()] = 1;
/*     */       }
/* 695 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 702 */         field_178679_a[Team.EnumVisible.NEVER.ordinal()] = 2;
/*     */       }
/* 704 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 711 */         field_178679_a[Team.EnumVisible.HIDE_FOR_OTHER_TEAMS.ordinal()] = 3;
/*     */       }
/* 713 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 720 */         field_178679_a[Team.EnumVisible.HIDE_FOR_OWN_TEAM.ordinal()] = 4;
/*     */       }
/* 722 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\entity\RendererLivingEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */