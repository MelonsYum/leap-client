/*      */ package net.minecraft.client.renderer;
/*      */ 
/*      */ import com.google.gson.JsonSyntaxException;
/*      */ import java.io.IOException;
/*      */ import java.lang.reflect.Field;
/*      */ import java.nio.FloatBuffer;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.concurrent.Callable;
/*      */ import leap.Client;
/*      */ import leap.events.Event;
/*      */ import leap.events.listeners.EventRenderWorld;
/*      */ import leap.ui.MainMenu;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockBed;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.properties.IProperty;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.entity.AbstractClientPlayer;
/*      */ import net.minecraft.client.gui.MapItemRenderer;
/*      */ import net.minecraft.client.gui.ScaledResolution;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.particle.EffectRenderer;
/*      */ import net.minecraft.client.renderer.culling.ClippingHelperImpl;
/*      */ import net.minecraft.client.renderer.culling.Frustrum;
/*      */ import net.minecraft.client.renderer.culling.ICamera;
/*      */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.resources.I18n;
/*      */ import net.minecraft.client.resources.IResourceManager;
/*      */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.client.shader.ShaderGroup;
/*      */ import net.minecraft.client.shader.ShaderLinkHelper;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.boss.BossStatus;
/*      */ import net.minecraft.entity.passive.EntityAnimal;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.server.integrated.IntegratedServer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.EnumWorldBlockLayer;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MouseFilter;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.WorldSettings;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import optifine.Config;
/*      */ import optifine.CustomColors;
/*      */ import optifine.Lagometer;
/*      */ import optifine.RandomMobs;
/*      */ import optifine.Reflector;
/*      */ import optifine.ReflectorForge;
/*      */ import optifine.TextureUtils;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.lwjgl.input.Mouse;
/*      */ import org.lwjgl.opengl.Display;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import org.lwjgl.opengl.GLContext;
/*      */ import org.lwjgl.util.glu.GLU;
/*      */ import org.lwjgl.util.glu.Project;
/*      */ import shadersmod.client.Shaders;
/*      */ import shadersmod.client.ShadersRender;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EntityRenderer
/*      */   implements IResourceManagerReloadListener
/*      */ {
/*   94 */   private static final Logger logger = LogManager.getLogger();
/*   95 */   private static final ResourceLocation locationRainPng = new ResourceLocation("textures/environment/rain.png");
/*   96 */   private static final ResourceLocation locationSnowPng = new ResourceLocation("textures/environment/snow.png");
/*      */   
/*      */   public static boolean anaglyphEnable;
/*      */   
/*      */   public static int anaglyphField;
/*      */   
/*      */   private Minecraft mc;
/*      */   
/*      */   private final IResourceManager resourceManager;
/*  105 */   private Random random = new Random();
/*      */   
/*      */   private float farPlaneDistance;
/*      */   
/*      */   public ItemRenderer itemRenderer;
/*      */   
/*      */   private final MapItemRenderer theMapItemRenderer;
/*      */   
/*      */   private int rendererUpdateCount;
/*      */   private Entity pointedEntity;
/*  115 */   private MouseFilter mouseFilterXAxis = new MouseFilter();
/*  116 */   private MouseFilter mouseFilterYAxis = new MouseFilter();
/*  117 */   private float thirdPersonDistance = 4.0F;
/*      */ 
/*      */   
/*  120 */   private float thirdPersonDistanceTemp = 4.0F;
/*      */ 
/*      */   
/*      */   private float smoothCamYaw;
/*      */ 
/*      */   
/*      */   private float smoothCamPitch;
/*      */ 
/*      */   
/*      */   private float smoothCamFilterX;
/*      */ 
/*      */   
/*      */   private float smoothCamFilterY;
/*      */ 
/*      */   
/*      */   private float smoothCamPartialTicks;
/*      */   
/*      */   private float fovModifierHand;
/*      */   
/*      */   private float fovModifierHandPrev;
/*      */   
/*      */   private float bossColorModifier;
/*      */   
/*      */   private float bossColorModifierPrev;
/*      */   
/*      */   private boolean cloudFog;
/*      */   
/*      */   private boolean field_175074_C = true;
/*      */   
/*      */   private boolean field_175073_D = true;
/*      */   
/*  151 */   private long prevFrameTime = Minecraft.getSystemTime();
/*      */ 
/*      */   
/*      */   private long renderEndNanoTime;
/*      */ 
/*      */   
/*      */   private final DynamicTexture lightmapTexture;
/*      */ 
/*      */   
/*      */   private final int[] lightmapColors;
/*      */ 
/*      */   
/*      */   private final ResourceLocation locationLightMap;
/*      */ 
/*      */   
/*      */   private boolean lightmapUpdateNeeded;
/*      */ 
/*      */   
/*      */   private float torchFlickerX;
/*      */ 
/*      */   
/*      */   private float field_175075_L;
/*      */ 
/*      */   
/*      */   private int rainSoundCounter;
/*      */ 
/*      */   
/*  178 */   private float[] field_175076_N = new float[1024];
/*  179 */   private float[] field_175077_O = new float[1024];
/*      */ 
/*      */   
/*  182 */   private FloatBuffer fogColorBuffer = GLAllocation.createDirectFloatBuffer(16);
/*      */   
/*      */   public float field_175080_Q;
/*      */   
/*      */   public float field_175082_R;
/*      */   
/*      */   public float field_175081_S;
/*      */   
/*      */   private float fogColor2;
/*      */   private float fogColor1;
/*  192 */   private int field_175079_V = 0;
/*      */   private boolean field_175078_W = false;
/*  194 */   private double cameraZoom = 1.0D;
/*      */   private double cameraYaw;
/*      */   private double cameraPitch;
/*      */   private ShaderGroup theShaderGroup;
/*  198 */   private static final ResourceLocation[] shaderResourceLocations = new ResourceLocation[] { new ResourceLocation("shaders/post/notch.json"), new ResourceLocation("shaders/post/fxaa.json"), new ResourceLocation("shaders/post/art.json"), new ResourceLocation("shaders/post/bumpy.json"), new ResourceLocation("shaders/post/blobs2.json"), new ResourceLocation("shaders/post/pencil.json"), new ResourceLocation("shaders/post/color_convolve.json"), new ResourceLocation("shaders/post/deconverge.json"), new ResourceLocation("shaders/post/flip.json"), new ResourceLocation("shaders/post/invert.json"), new ResourceLocation("shaders/post/ntsc.json"), new ResourceLocation("shaders/post/outline.json"), new ResourceLocation("shaders/post/phosphor.json"), new ResourceLocation("shaders/post/scan_pincushion.json"), new ResourceLocation("shaders/post/sobel.json"), new ResourceLocation("shaders/post/bits.json"), new ResourceLocation("shaders/post/desaturate.json"), new ResourceLocation("shaders/post/green.json"), new ResourceLocation("shaders/post/blur.json"), new ResourceLocation("shaders/post/wobble.json"), new ResourceLocation("shaders/post/blobs.json"), new ResourceLocation("shaders/post/antialias.json"), new ResourceLocation("shaders/post/creeper.json"), new ResourceLocation("shaders/post/spider.json") };
/*  199 */   public static final int shaderCount = shaderResourceLocations.length;
/*      */   private int shaderIndex;
/*      */   private boolean field_175083_ad;
/*      */   public int field_175084_ae;
/*      */   private static final String __OBFID = "CL_00000947";
/*      */   private boolean initialized = false;
/*  205 */   private World updatedWorld = null;
/*      */   private boolean showDebugInfo = false;
/*      */   public boolean fogStandard = false;
/*  208 */   private float clipDistance = 128.0F;
/*  209 */   private long lastServerTime = 0L;
/*  210 */   private int lastServerTicks = 0;
/*  211 */   private int serverWaitTime = 0;
/*  212 */   private int serverWaitTimeCurrent = 0;
/*  213 */   private float avgServerTimeDiff = 0.0F;
/*  214 */   private float avgServerTickDiff = 0.0F;
/*  215 */   private long lastErrorCheckTimeMs = 0L;
/*  216 */   private ShaderGroup[] fxaaShaders = new ShaderGroup[10];
/*      */ 
/*      */   
/*      */   public EntityRenderer(Minecraft mcIn, IResourceManager p_i45076_2_) {
/*  220 */     this.shaderIndex = shaderCount;
/*  221 */     this.field_175083_ad = false;
/*  222 */     this.field_175084_ae = 0;
/*  223 */     this.mc = mcIn;
/*  224 */     this.resourceManager = p_i45076_2_;
/*  225 */     this.itemRenderer = mcIn.getItemRenderer();
/*  226 */     this.theMapItemRenderer = new MapItemRenderer(mcIn.getTextureManager());
/*  227 */     this.lightmapTexture = new DynamicTexture(16, 16);
/*  228 */     this.locationLightMap = mcIn.getTextureManager().getDynamicTextureLocation("lightMap", this.lightmapTexture);
/*  229 */     this.lightmapColors = this.lightmapTexture.getTextureData();
/*  230 */     this.theShaderGroup = null;
/*      */     
/*  232 */     for (int var3 = 0; var3 < 32; var3++) {
/*      */       
/*  234 */       for (int var4 = 0; var4 < 32; var4++) {
/*      */         
/*  236 */         float var5 = (var4 - 16);
/*  237 */         float var6 = (var3 - 16);
/*  238 */         float var7 = MathHelper.sqrt_float(var5 * var5 + var6 * var6);
/*  239 */         this.field_175076_N[var3 << 5 | var4] = -var6 / var7;
/*  240 */         this.field_175077_O[var3 << 5 | var4] = var5 / var7;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isShaderActive() {
/*  247 */     return (OpenGlHelper.shadersSupported && this.theShaderGroup != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public void stopUseShader() {
/*  252 */     if (this.theShaderGroup != null)
/*      */     {
/*  254 */       this.theShaderGroup.deleteShaderGroup();
/*      */     }
/*      */     
/*  257 */     this.theShaderGroup = null;
/*  258 */     this.shaderIndex = shaderCount;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175071_c() {
/*  263 */     this.field_175083_ad = !this.field_175083_ad;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175066_a(Entity p_175066_1_) {
/*  268 */     if (OpenGlHelper.shadersSupported) {
/*      */       
/*  270 */       if (this.theShaderGroup != null)
/*      */       {
/*  272 */         this.theShaderGroup.deleteShaderGroup();
/*      */       }
/*      */       
/*  275 */       this.theShaderGroup = null;
/*      */       
/*  277 */       if (p_175066_1_ instanceof net.minecraft.entity.monster.EntityCreeper) {
/*      */         
/*  279 */         func_175069_a(new ResourceLocation("shaders/post/creeper.json"));
/*      */       }
/*  281 */       else if (p_175066_1_ instanceof net.minecraft.entity.monster.EntitySpider) {
/*      */         
/*  283 */         func_175069_a(new ResourceLocation("shaders/post/spider.json"));
/*      */       }
/*  285 */       else if (p_175066_1_ instanceof net.minecraft.entity.monster.EntityEnderman) {
/*      */         
/*  287 */         func_175069_a(new ResourceLocation("shaders/post/invert.json"));
/*      */       }
/*  289 */       else if (Reflector.ForgeHooksClient_loadEntityShader.exists()) {
/*      */         
/*  291 */         Reflector.call(Reflector.ForgeHooksClient_loadEntityShader, new Object[] { p_175066_1_, this });
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void activateNextShader() {
/*  298 */     if (OpenGlHelper.shadersSupported && this.mc.func_175606_aa() instanceof EntityPlayer) {
/*      */       
/*  300 */       if (this.theShaderGroup != null)
/*      */       {
/*  302 */         this.theShaderGroup.deleteShaderGroup();
/*      */       }
/*      */       
/*  305 */       this.shaderIndex = (this.shaderIndex + 1) % (shaderResourceLocations.length + 1);
/*      */       
/*  307 */       if (this.shaderIndex != shaderCount) {
/*      */         
/*  309 */         func_175069_a(shaderResourceLocations[this.shaderIndex]);
/*      */       }
/*      */       else {
/*      */         
/*  313 */         this.theShaderGroup = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175069_a(ResourceLocation p_175069_1_) {
/*  320 */     if (OpenGlHelper.isFramebufferEnabled()) {
/*      */       
/*      */       try {
/*      */         
/*  324 */         this.theShaderGroup = new ShaderGroup(this.mc.getTextureManager(), this.resourceManager, this.mc.getFramebuffer(), p_175069_1_);
/*  325 */         this.theShaderGroup.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
/*  326 */         this.field_175083_ad = true;
/*      */       }
/*  328 */       catch (IOException var3) {
/*      */         
/*  330 */         logger.warn("Failed to load shader: " + p_175069_1_, var3);
/*  331 */         this.shaderIndex = shaderCount;
/*  332 */         this.field_175083_ad = false;
/*      */       }
/*  334 */       catch (JsonSyntaxException var4) {
/*      */         
/*  336 */         logger.warn("Failed to load shader: " + p_175069_1_, (Throwable)var4);
/*  337 */         this.shaderIndex = shaderCount;
/*  338 */         this.field_175083_ad = false;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void onResourceManagerReload(IResourceManager resourceManager) {
/*  345 */     if (this.theShaderGroup != null)
/*      */     {
/*  347 */       this.theShaderGroup.deleteShaderGroup();
/*      */     }
/*      */     
/*  350 */     this.theShaderGroup = null;
/*      */     
/*  352 */     if (this.shaderIndex != shaderCount) {
/*      */       
/*  354 */       func_175069_a(shaderResourceLocations[this.shaderIndex]);
/*      */     }
/*      */     else {
/*      */       
/*  358 */       func_175066_a(this.mc.func_175606_aa());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRenderer() {
/*  367 */     if (OpenGlHelper.shadersSupported && ShaderLinkHelper.getStaticShaderLinkHelper() == null)
/*      */     {
/*  369 */       ShaderLinkHelper.setNewStaticShaderLinkHelper();
/*      */     }
/*      */     
/*  372 */     updateFovModifierHand();
/*  373 */     updateTorchFlicker();
/*  374 */     this.fogColor2 = this.fogColor1;
/*  375 */     this.thirdPersonDistanceTemp = this.thirdPersonDistance;
/*      */ 
/*      */ 
/*      */     
/*  379 */     if (this.mc.gameSettings.smoothCamera) {
/*      */       
/*  381 */       float f1 = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
/*  382 */       float f2 = f1 * f1 * f1 * 8.0F;
/*  383 */       this.smoothCamFilterX = this.mouseFilterXAxis.smooth(this.smoothCamYaw, 0.05F * f2);
/*  384 */       this.smoothCamFilterY = this.mouseFilterYAxis.smooth(this.smoothCamPitch, 0.05F * f2);
/*  385 */       this.smoothCamPartialTicks = 0.0F;
/*  386 */       this.smoothCamYaw = 0.0F;
/*  387 */       this.smoothCamPitch = 0.0F;
/*      */     }
/*      */     else {
/*      */       
/*  391 */       this.smoothCamFilterX = 0.0F;
/*  392 */       this.smoothCamFilterY = 0.0F;
/*  393 */       this.mouseFilterXAxis.func_180179_a();
/*  394 */       this.mouseFilterYAxis.func_180179_a();
/*      */     } 
/*      */     
/*  397 */     if (this.mc.func_175606_aa() == null)
/*      */     {
/*  399 */       this.mc.func_175607_a((Entity)this.mc.thePlayer);
/*      */     }
/*      */     
/*  402 */     Entity viewEntity = this.mc.func_175606_aa();
/*  403 */     double vx = viewEntity.posX;
/*  404 */     double vy = viewEntity.posY + viewEntity.getEyeHeight();
/*  405 */     double vz = viewEntity.posZ;
/*  406 */     float var1 = this.mc.theWorld.getLightBrightness(new BlockPos(vx, vy, vz));
/*  407 */     float var2 = this.mc.gameSettings.renderDistanceChunks / 16.0F;
/*  408 */     var2 = MathHelper.clamp_float(var2, 0.0F, 1.0F);
/*  409 */     float var3 = var1 * (1.0F - var2) + var2;
/*  410 */     this.fogColor1 += (var3 - this.fogColor1) * 0.1F;
/*  411 */     this.rendererUpdateCount++;
/*  412 */     this.itemRenderer.updateEquippedItem();
/*  413 */     addRainParticles();
/*  414 */     this.bossColorModifierPrev = this.bossColorModifier;
/*      */     
/*  416 */     if (BossStatus.hasColorModifier) {
/*      */       
/*  418 */       this.bossColorModifier += 0.05F;
/*      */       
/*  420 */       if (this.bossColorModifier > 1.0F)
/*      */       {
/*  422 */         this.bossColorModifier = 1.0F;
/*      */       }
/*      */       
/*  425 */       BossStatus.hasColorModifier = false;
/*      */     }
/*  427 */     else if (this.bossColorModifier > 0.0F) {
/*      */       
/*  429 */       this.bossColorModifier -= 0.0125F;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public ShaderGroup getShaderGroup() {
/*  435 */     return this.theShaderGroup;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateShaderGroupSize(int p_147704_1_, int p_147704_2_) {
/*  440 */     if (OpenGlHelper.shadersSupported) {
/*      */       
/*  442 */       if (this.theShaderGroup != null)
/*      */       {
/*  444 */         this.theShaderGroup.createBindFramebuffers(p_147704_1_, p_147704_2_);
/*      */       }
/*      */       
/*  447 */       this.mc.renderGlobal.checkOcclusionQueryResult(p_147704_1_, p_147704_2_);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getMouseOver(float p_78473_1_) {
/*  456 */     Entity var2 = this.mc.func_175606_aa();
/*      */     
/*  458 */     if (var2 != null && this.mc.theWorld != null) {
/*      */       
/*  460 */       this.mc.mcProfiler.startSection("pick");
/*  461 */       this.mc.pointedEntity = null;
/*  462 */       double var3 = this.mc.playerController.getBlockReachDistance();
/*  463 */       this.mc.objectMouseOver = var2.func_174822_a(var3, p_78473_1_);
/*  464 */       double var5 = var3;
/*  465 */       Vec3 var7 = var2.func_174824_e(p_78473_1_);
/*      */       
/*  467 */       if (this.mc.playerController.extendedReach()) {
/*      */         
/*  469 */         var3 = 6.0D;
/*  470 */         var5 = 6.0D;
/*      */       }
/*      */       else {
/*      */         
/*  474 */         if (var3 > 3.0D)
/*      */         {
/*  476 */           var5 = 3.0D;
/*      */         }
/*      */         
/*  479 */         var3 = var5;
/*      */       } 
/*      */       
/*  482 */       if (this.mc.objectMouseOver != null)
/*      */       {
/*  484 */         var5 = this.mc.objectMouseOver.hitVec.distanceTo(var7);
/*      */       }
/*      */       
/*  487 */       Vec3 var8 = var2.getLook(p_78473_1_);
/*  488 */       Vec3 var9 = var7.addVector(var8.xCoord * var3, var8.yCoord * var3, var8.zCoord * var3);
/*  489 */       this.pointedEntity = null;
/*  490 */       Vec3 var10 = null;
/*  491 */       float var11 = 1.0F;
/*  492 */       List<Entity> var12 = this.mc.theWorld.getEntitiesWithinAABBExcludingEntity(var2, var2.getEntityBoundingBox().addCoord(var8.xCoord * var3, var8.yCoord * var3, var8.zCoord * var3).expand(var11, var11, var11));
/*  493 */       double var13 = var5;
/*      */       
/*  495 */       for (int var15 = 0; var15 < var12.size(); var15++) {
/*      */         
/*  497 */         Entity var16 = var12.get(var15);
/*      */         
/*  499 */         if (var16.canBeCollidedWith()) {
/*      */           
/*  501 */           float var17 = var16.getCollisionBorderSize();
/*  502 */           AxisAlignedBB var18 = var16.getEntityBoundingBox().expand(var17, var17, var17);
/*  503 */           MovingObjectPosition var19 = var18.calculateIntercept(var7, var9);
/*      */           
/*  505 */           if (var18.isVecInside(var7)) {
/*      */             
/*  507 */             if (0.0D < var13 || var13 == 0.0D)
/*      */             {
/*  509 */               this.pointedEntity = var16;
/*  510 */               var10 = (var19 == null) ? var7 : var19.hitVec;
/*  511 */               var13 = 0.0D;
/*      */             }
/*      */           
/*  514 */           } else if (var19 != null) {
/*      */             
/*  516 */             double var20 = var7.distanceTo(var19.hitVec);
/*      */             
/*  518 */             if (var20 < var13 || var13 == 0.0D) {
/*      */               
/*  520 */               boolean canRiderInteract = false;
/*      */               
/*  522 */               if (Reflector.ForgeEntity_canRiderInteract.exists())
/*      */               {
/*  524 */                 canRiderInteract = Reflector.callBoolean(var16, Reflector.ForgeEntity_canRiderInteract, new Object[0]);
/*      */               }
/*      */               
/*  527 */               if (var16 == var2.ridingEntity && !canRiderInteract) {
/*      */                 
/*  529 */                 if (var13 == 0.0D)
/*      */                 {
/*  531 */                   this.pointedEntity = var16;
/*  532 */                   var10 = var19.hitVec;
/*      */                 }
/*      */               
/*      */               } else {
/*      */                 
/*  537 */                 this.pointedEntity = var16;
/*  538 */                 var10 = var19.hitVec;
/*  539 */                 var13 = var20;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  546 */       if (this.pointedEntity != null && (var13 < var5 || this.mc.objectMouseOver == null)) {
/*      */         
/*  548 */         this.mc.objectMouseOver = new MovingObjectPosition(this.pointedEntity, var10);
/*      */         
/*  550 */         if (this.pointedEntity instanceof EntityLivingBase || this.pointedEntity instanceof net.minecraft.entity.item.EntityItemFrame)
/*      */         {
/*  552 */           this.mc.pointedEntity = this.pointedEntity;
/*      */         }
/*      */       } 
/*      */       
/*  556 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateFovModifierHand() {
/*  565 */     float var1 = 1.0F;
/*      */     
/*  567 */     if (this.mc.func_175606_aa() instanceof AbstractClientPlayer) {
/*      */       
/*  569 */       AbstractClientPlayer var2 = (AbstractClientPlayer)this.mc.func_175606_aa();
/*  570 */       var1 = var2.func_175156_o();
/*      */     } 
/*      */     
/*  573 */     this.fovModifierHandPrev = this.fovModifierHand;
/*  574 */     this.fovModifierHand += (var1 - this.fovModifierHand) * 0.5F;
/*      */     
/*  576 */     if (this.fovModifierHand > 1.5F)
/*      */     {
/*  578 */       this.fovModifierHand = 1.5F;
/*      */     }
/*      */     
/*  581 */     if (this.fovModifierHand < 0.1F)
/*      */     {
/*  583 */       this.fovModifierHand = 0.1F;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float getFOVModifier(float partialTicks, boolean p_78481_2_) {
/*  592 */     if (this.field_175078_W)
/*      */     {
/*  594 */       return 90.0F;
/*      */     }
/*      */ 
/*      */     
/*  598 */     Entity var3 = this.mc.func_175606_aa();
/*  599 */     float var4 = 70.0F;
/*      */     
/*  601 */     if (p_78481_2_) {
/*      */       
/*  603 */       var4 = this.mc.gameSettings.fovSetting;
/*      */       
/*  605 */       if (Config.isDynamicFov())
/*      */       {
/*  607 */         var4 *= this.fovModifierHandPrev + (this.fovModifierHand - this.fovModifierHandPrev) * partialTicks;
/*      */       }
/*      */     } 
/*      */     
/*  611 */     boolean zoomActive = false;
/*      */     
/*  613 */     if (this.mc.currentScreen == null) {
/*      */       
/*  615 */       GameSettings var10000 = this.mc.gameSettings;
/*  616 */       zoomActive = GameSettings.isKeyDown(this.mc.gameSettings.ofKeyBindZoom);
/*      */     } 
/*      */     
/*  619 */     if (zoomActive) {
/*      */       
/*  621 */       if (!Config.zoomMode) {
/*      */         
/*  623 */         Config.zoomMode = true;
/*  624 */         this.mc.gameSettings.smoothCamera = true;
/*      */       } 
/*      */       
/*  627 */       if (Config.zoomMode)
/*      */       {
/*  629 */         var4 /= 4.0F;
/*      */       }
/*      */     }
/*  632 */     else if (Config.zoomMode) {
/*      */       
/*  634 */       Config.zoomMode = false;
/*  635 */       this.mc.gameSettings.smoothCamera = false;
/*  636 */       this.mouseFilterXAxis = new MouseFilter();
/*  637 */       this.mouseFilterYAxis = new MouseFilter();
/*  638 */       this.mc.renderGlobal.displayListEntitiesDirty = true;
/*      */     } 
/*      */     
/*  641 */     if (var3 instanceof EntityLivingBase && ((EntityLivingBase)var3).getHealth() <= 0.0F) {
/*      */       
/*  643 */       float var6 = ((EntityLivingBase)var3).deathTime + partialTicks;
/*  644 */       var4 /= (1.0F - 500.0F / (var6 + 500.0F)) * 2.0F + 1.0F;
/*      */     } 
/*      */     
/*  647 */     Block var61 = ActiveRenderInfo.func_180786_a((World)this.mc.theWorld, var3, partialTicks);
/*      */     
/*  649 */     if (var61.getMaterial() == Material.water)
/*      */     {
/*  651 */       var4 = var4 * 60.0F / 70.0F;
/*      */     }
/*      */     
/*  654 */     return var4;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void hurtCameraEffect(float p_78482_1_) {
/*  660 */     if (this.mc.func_175606_aa() instanceof EntityLivingBase) {
/*      */       
/*  662 */       EntityLivingBase var2 = (EntityLivingBase)this.mc.func_175606_aa();
/*  663 */       float var3 = var2.hurtTime - p_78482_1_;
/*      */ 
/*      */       
/*  666 */       if (var2.getHealth() <= 0.0F) {
/*      */         
/*  668 */         float f = var2.deathTime + p_78482_1_;
/*  669 */         GlStateManager.rotate(40.0F - 8000.0F / (f + 200.0F), 0.0F, 0.0F, 1.0F);
/*      */       } 
/*      */       
/*  672 */       if (var3 < 0.0F) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  677 */       var3 /= var2.maxHurtTime;
/*  678 */       var3 = MathHelper.sin(var3 * var3 * var3 * var3 * 3.1415927F);
/*  679 */       float var4 = var2.attackedAtYaw;
/*  680 */       GlStateManager.rotate(-var4, 0.0F, 1.0F, 0.0F);
/*  681 */       GlStateManager.rotate(-var3 * 14.0F, 0.0F, 0.0F, 1.0F);
/*  682 */       GlStateManager.rotate(var4, 0.0F, 1.0F, 0.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setupViewBobbing(float p_78475_1_) {
/*  691 */     if (this.mc.func_175606_aa() instanceof EntityPlayer) {
/*      */       
/*  693 */       EntityPlayer var2 = (EntityPlayer)this.mc.func_175606_aa();
/*  694 */       float var3 = var2.distanceWalkedModified - var2.prevDistanceWalkedModified;
/*  695 */       float var4 = -(var2.distanceWalkedModified + var3 * p_78475_1_);
/*  696 */       float var5 = var2.prevCameraYaw + (var2.cameraYaw - var2.prevCameraYaw) * p_78475_1_;
/*  697 */       float var6 = var2.prevCameraPitch + (var2.cameraPitch - var2.prevCameraPitch) * p_78475_1_;
/*  698 */       GlStateManager.translate(MathHelper.sin(var4 * 3.1415927F) * var5 * 0.5F, -Math.abs(MathHelper.cos(var4 * 3.1415927F) * var5), 0.0F);
/*  699 */       GlStateManager.rotate(MathHelper.sin(var4 * 3.1415927F) * var5 * 3.0F, 0.0F, 0.0F, 1.0F);
/*  700 */       GlStateManager.rotate(Math.abs(MathHelper.cos(var4 * 3.1415927F - 0.2F) * var5) * 5.0F, 1.0F, 0.0F, 0.0F);
/*  701 */       GlStateManager.rotate(var6, 1.0F, 0.0F, 0.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void orientCamera(float p_78467_1_) {
/*  710 */     Entity var2 = this.mc.func_175606_aa();
/*  711 */     float var3 = var2.getEyeHeight();
/*  712 */     double var4 = var2.prevPosX + (var2.posX - var2.prevPosX) * p_78467_1_;
/*  713 */     double var6 = var2.prevPosY + (var2.posY - var2.prevPosY) * p_78467_1_ + var3;
/*  714 */     double var8 = var2.prevPosZ + (var2.posZ - var2.prevPosZ) * p_78467_1_;
/*      */ 
/*      */ 
/*      */     
/*  718 */     if (var2 instanceof EntityLivingBase && ((EntityLivingBase)var2).isPlayerSleeping()) {
/*      */       
/*  720 */       var3 = (float)(var3 + 1.0D);
/*  721 */       GlStateManager.translate(0.0F, 0.3F, 0.0F);
/*      */       
/*  723 */       if (!this.mc.gameSettings.debugCamEnable)
/*      */       {
/*  725 */         BlockPos var27 = new BlockPos(var2);
/*  726 */         IBlockState partialTicks = this.mc.theWorld.getBlockState(var27);
/*  727 */         Block var29 = partialTicks.getBlock();
/*      */         
/*  729 */         if (Reflector.ForgeHooksClient_orientBedCamera.exists()) {
/*      */           
/*  731 */           Reflector.callVoid(Reflector.ForgeHooksClient_orientBedCamera, new Object[] { this.mc.theWorld, var27, partialTicks, var2 });
/*      */         }
/*  733 */         else if (var29 == Blocks.bed) {
/*      */           
/*  735 */           int var30 = ((EnumFacing)partialTicks.getValue((IProperty)BlockBed.AGE)).getHorizontalIndex();
/*  736 */           GlStateManager.rotate((var30 * 90), 0.0F, 1.0F, 0.0F);
/*      */         } 
/*      */         
/*  739 */         GlStateManager.rotate(var2.prevRotationYaw + (var2.rotationYaw - var2.prevRotationYaw) * p_78467_1_ + 180.0F, 0.0F, -1.0F, 0.0F);
/*  740 */         GlStateManager.rotate(var2.prevRotationPitch + (var2.rotationPitch - var2.prevRotationPitch) * p_78467_1_, -1.0F, 0.0F, 0.0F);
/*      */       }
/*      */     
/*  743 */     } else if (this.mc.gameSettings.thirdPersonView > 0) {
/*      */       
/*  745 */       double var28 = (this.thirdPersonDistanceTemp + (this.thirdPersonDistance - this.thirdPersonDistanceTemp) * p_78467_1_);
/*      */       
/*  747 */       if (this.mc.gameSettings.debugCamEnable)
/*      */       {
/*  749 */         GlStateManager.translate(0.0F, 0.0F, (float)-var28);
/*      */       }
/*      */       else
/*      */       {
/*  753 */         float yaw = var2.rotationYaw;
/*  754 */         float pitch = var2.rotationPitch;
/*      */         
/*  756 */         if (this.mc.gameSettings.thirdPersonView == 2)
/*      */         {
/*  758 */           pitch += 180.0F;
/*      */         }
/*      */         
/*  761 */         double roll = (-MathHelper.sin(yaw / 180.0F * 3.1415927F) * MathHelper.cos(pitch / 180.0F * 3.1415927F)) * var28;
/*  762 */         double event = (MathHelper.cos(yaw / 180.0F * 3.1415927F) * MathHelper.cos(pitch / 180.0F * 3.1415927F)) * var28;
/*  763 */         double var18 = -MathHelper.sin(pitch / 180.0F * 3.1415927F) * var28;
/*      */         
/*  765 */         for (int var20 = 0; var20 < 8; var20++) {
/*      */           
/*  767 */           float var21 = ((var20 & 0x1) * 2 - 1);
/*  768 */           float var22 = ((var20 >> 1 & 0x1) * 2 - 1);
/*  769 */           float var23 = ((var20 >> 2 & 0x1) * 2 - 1);
/*  770 */           var21 *= 0.1F;
/*  771 */           var22 *= 0.1F;
/*  772 */           var23 *= 0.1F;
/*  773 */           MovingObjectPosition var24 = this.mc.theWorld.rayTraceBlocks(new Vec3(var4 + var21, var6 + var22, var8 + var23), new Vec3(var4 - roll + var21 + var23, var6 - var18 + var22, var8 - event + var23));
/*      */           
/*  775 */           if (var24 != null) {
/*      */             
/*  777 */             double var25 = var24.hitVec.distanceTo(new Vec3(var4, var6, var8));
/*      */             
/*  779 */             if (var25 < var28)
/*      */             {
/*  781 */               var28 = var25;
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  786 */         if (this.mc.gameSettings.thirdPersonView == 2)
/*      */         {
/*  788 */           GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
/*      */         }
/*      */         
/*  791 */         GlStateManager.rotate(var2.rotationPitch - pitch, 1.0F, 0.0F, 0.0F);
/*  792 */         GlStateManager.rotate(var2.rotationYaw - yaw, 0.0F, 1.0F, 0.0F);
/*  793 */         GlStateManager.translate(0.0F, 0.0F, (float)-var28);
/*  794 */         GlStateManager.rotate(yaw - var2.rotationYaw, 0.0F, 1.0F, 0.0F);
/*  795 */         GlStateManager.rotate(pitch - var2.rotationPitch, 1.0F, 0.0F, 0.0F);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  800 */       GlStateManager.translate(0.0F, 0.0F, -0.1F);
/*      */     } 
/*      */     
/*  803 */     if (Reflector.EntityViewRenderEvent_CameraSetup_Constructor.exists()) {
/*      */       
/*  805 */       if (!this.mc.gameSettings.debugCamEnable)
/*      */       {
/*  807 */         float yaw = var2.prevRotationYaw + (var2.rotationYaw - var2.prevRotationYaw) * p_78467_1_ + 180.0F;
/*  808 */         float pitch = var2.prevRotationPitch + (var2.rotationPitch - var2.prevRotationPitch) * p_78467_1_;
/*  809 */         float var31 = 0.0F;
/*      */         
/*  811 */         if (var2 instanceof EntityAnimal) {
/*      */           
/*  813 */           EntityAnimal block = (EntityAnimal)var2;
/*  814 */           yaw = block.prevRotationYawHead + (block.rotationYawHead - block.prevRotationYawHead) * p_78467_1_ + 180.0F;
/*      */         } 
/*      */         
/*  817 */         Block var32 = ActiveRenderInfo.func_180786_a((World)this.mc.theWorld, var2, p_78467_1_);
/*  818 */         Object var33 = Reflector.newInstance(Reflector.EntityViewRenderEvent_CameraSetup_Constructor, new Object[] { this, var2, var32, Float.valueOf(p_78467_1_), Float.valueOf(yaw), Float.valueOf(pitch), Float.valueOf(var31) });
/*  819 */         Reflector.postForgeBusEvent(var33);
/*  820 */         var31 = Reflector.getFieldValueFloat(var33, Reflector.EntityViewRenderEvent_CameraSetup_roll, var31);
/*  821 */         pitch = Reflector.getFieldValueFloat(var33, Reflector.EntityViewRenderEvent_CameraSetup_pitch, pitch);
/*  822 */         yaw = Reflector.getFieldValueFloat(var33, Reflector.EntityViewRenderEvent_CameraSetup_yaw, yaw);
/*  823 */         GlStateManager.rotate(var31, 0.0F, 0.0F, 1.0F);
/*  824 */         GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);
/*  825 */         GlStateManager.rotate(yaw, 0.0F, 1.0F, 0.0F);
/*      */       }
/*      */     
/*  828 */     } else if (!this.mc.gameSettings.debugCamEnable) {
/*      */       
/*  830 */       GlStateManager.rotate(var2.prevRotationPitch + (var2.rotationPitch - var2.prevRotationPitch) * p_78467_1_, 1.0F, 0.0F, 0.0F);
/*      */       
/*  832 */       if (var2 instanceof EntityAnimal) {
/*      */         
/*  834 */         EntityAnimal var281 = (EntityAnimal)var2;
/*  835 */         GlStateManager.rotate(var281.prevRotationYawHead + (var281.rotationYawHead - var281.prevRotationYawHead) * p_78467_1_ + 180.0F, 0.0F, 1.0F, 0.0F);
/*      */       }
/*      */       else {
/*      */         
/*  839 */         GlStateManager.rotate(var2.prevRotationYaw + (var2.rotationYaw - var2.prevRotationYaw) * p_78467_1_ + 180.0F, 0.0F, 1.0F, 0.0F);
/*      */       } 
/*      */     } 
/*      */     
/*  843 */     GlStateManager.translate(0.0F, -var3, 0.0F);
/*  844 */     var4 = var2.prevPosX + (var2.posX - var2.prevPosX) * p_78467_1_;
/*  845 */     var6 = var2.prevPosY + (var2.posY - var2.prevPosY) * p_78467_1_ + var3;
/*  846 */     var8 = var2.prevPosZ + (var2.posZ - var2.prevPosZ) * p_78467_1_;
/*  847 */     this.cloudFog = this.mc.renderGlobal.hasCloudFog(var4, var6, var8, p_78467_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setupCameraTransform(float partialTicks, int pass) {
/*  855 */     this.farPlaneDistance = (this.mc.gameSettings.renderDistanceChunks * 16);
/*      */     
/*  857 */     if (Config.isFogFancy())
/*      */     {
/*  859 */       this.farPlaneDistance *= 0.95F;
/*      */     }
/*      */     
/*  862 */     if (Config.isFogFast())
/*      */     {
/*  864 */       this.farPlaneDistance *= 0.83F;
/*      */     }
/*      */     
/*  867 */     GlStateManager.matrixMode(5889);
/*  868 */     GlStateManager.loadIdentity();
/*  869 */     float var3 = 0.07F;
/*      */     
/*  871 */     if (this.mc.gameSettings.anaglyph)
/*      */     {
/*  873 */       GlStateManager.translate(-(pass * 2 - 1) * var3, 0.0F, 0.0F);
/*      */     }
/*      */     
/*  876 */     this.clipDistance = this.farPlaneDistance * 2.0F;
/*      */     
/*  878 */     if (this.clipDistance < 173.0F)
/*      */     {
/*  880 */       this.clipDistance = 173.0F;
/*      */     }
/*      */     
/*  883 */     if (this.mc.theWorld.provider.getDimensionId() == 1)
/*      */     {
/*  885 */       this.clipDistance = 256.0F;
/*      */     }
/*      */     
/*  888 */     if (this.cameraZoom != 1.0D) {
/*      */       
/*  890 */       GlStateManager.translate((float)this.cameraYaw, (float)-this.cameraPitch, 0.0F);
/*  891 */       GlStateManager.scale(this.cameraZoom, this.cameraZoom, 1.0D);
/*      */     } 
/*      */     
/*  894 */     Project.gluPerspective(getFOVModifier(partialTicks, true), this.mc.displayWidth / this.mc.displayHeight, 0.05F, this.clipDistance);
/*  895 */     GlStateManager.matrixMode(5888);
/*  896 */     GlStateManager.loadIdentity();
/*      */     
/*  898 */     if (this.mc.gameSettings.anaglyph)
/*      */     {
/*  900 */       GlStateManager.translate((pass * 2 - 1) * 0.1F, 0.0F, 0.0F);
/*      */     }
/*      */     
/*  903 */     hurtCameraEffect(partialTicks);
/*      */     
/*  905 */     if (this.mc.gameSettings.viewBobbing)
/*      */     {
/*  907 */       setupViewBobbing(partialTicks);
/*      */     }
/*      */     
/*  910 */     float var4 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * partialTicks;
/*      */     
/*  912 */     if (var4 > 0.0F) {
/*      */       
/*  914 */       byte var5 = 20;
/*      */       
/*  916 */       if (this.mc.thePlayer.isPotionActive(Potion.confusion))
/*      */       {
/*  918 */         var5 = 7;
/*      */       }
/*      */       
/*  921 */       float var6 = 5.0F / (var4 * var4 + 5.0F) - var4 * 0.04F;
/*  922 */       var6 *= var6;
/*  923 */       GlStateManager.rotate((this.rendererUpdateCount + partialTicks) * var5, 0.0F, 1.0F, 1.0F);
/*  924 */       GlStateManager.scale(1.0F / var6, 1.0F, 1.0F);
/*  925 */       GlStateManager.rotate(-(this.rendererUpdateCount + partialTicks) * var5, 0.0F, 1.0F, 1.0F);
/*      */     } 
/*      */     
/*  928 */     orientCamera(partialTicks);
/*      */     
/*  930 */     if (this.field_175078_W)
/*      */     {
/*  932 */       switch (this.field_175079_V) {
/*      */         
/*      */         case 0:
/*  935 */           GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
/*      */           break;
/*      */         
/*      */         case 1:
/*  939 */           GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
/*      */           break;
/*      */         
/*      */         case 2:
/*  943 */           GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
/*      */           break;
/*      */         
/*      */         case 3:
/*  947 */           GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
/*      */           break;
/*      */         
/*      */         case 4:
/*  951 */           GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
/*      */           break;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void renderHand(float p_78476_1_, int p_78476_2_) {
/*  961 */     if (!this.field_175078_W) {
/*      */       
/*  963 */       GlStateManager.matrixMode(5889);
/*  964 */       GlStateManager.loadIdentity();
/*  965 */       float var3 = 0.07F;
/*      */       
/*  967 */       if (this.mc.gameSettings.anaglyph)
/*      */       {
/*  969 */         GlStateManager.translate(-(p_78476_2_ * 2 - 1) * var3, 0.0F, 0.0F);
/*      */       }
/*      */       
/*  972 */       if (Config.isShaders())
/*      */       {
/*  974 */         Shaders.applyHandDepth();
/*      */       }
/*      */       
/*  977 */       Project.gluPerspective(getFOVModifier(p_78476_1_, false), this.mc.displayWidth / this.mc.displayHeight, 0.05F, this.farPlaneDistance * 2.0F);
/*  978 */       GlStateManager.matrixMode(5888);
/*  979 */       GlStateManager.loadIdentity();
/*      */       
/*  981 */       if (this.mc.gameSettings.anaglyph)
/*      */       {
/*  983 */         GlStateManager.translate((p_78476_2_ * 2 - 1) * 0.1F, 0.0F, 0.0F);
/*      */       }
/*      */       
/*  986 */       boolean var4 = false;
/*      */       
/*  988 */       if (!Config.isShaders() || !Shaders.isHandRendered) {
/*      */         
/*  990 */         GlStateManager.pushMatrix();
/*  991 */         hurtCameraEffect(p_78476_1_);
/*      */         
/*  993 */         if (this.mc.gameSettings.viewBobbing)
/*      */         {
/*  995 */           setupViewBobbing(p_78476_1_);
/*      */         }
/*      */         
/*  998 */         var4 = (this.mc.func_175606_aa() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.func_175606_aa()).isPlayerSleeping());
/*      */         
/* 1000 */         if (this.mc.gameSettings.thirdPersonView == 0 && !var4 && !this.mc.gameSettings.hideGUI && !this.mc.playerController.enableEverythingIsScrewedUpMode()) {
/*      */           
/* 1002 */           func_180436_i();
/*      */           
/* 1004 */           if (Config.isShaders()) {
/*      */             
/* 1006 */             ShadersRender.renderItemFP(this.itemRenderer, p_78476_1_);
/*      */           }
/*      */           else {
/*      */             
/* 1010 */             this.itemRenderer.renderItemInFirstPerson(p_78476_1_);
/*      */           } 
/*      */           
/* 1013 */           func_175072_h();
/*      */         } 
/*      */         
/* 1016 */         GlStateManager.popMatrix();
/*      */       } 
/*      */       
/* 1019 */       if (Config.isShaders() && !Shaders.isCompositeRendered) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 1024 */       func_175072_h();
/*      */       
/* 1026 */       if (this.mc.gameSettings.thirdPersonView == 0 && !var4) {
/*      */         
/* 1028 */         this.itemRenderer.renderOverlays(p_78476_1_);
/* 1029 */         hurtCameraEffect(p_78476_1_);
/*      */       } 
/*      */       
/* 1032 */       if (this.mc.gameSettings.viewBobbing)
/*      */       {
/* 1034 */         setupViewBobbing(p_78476_1_);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_175072_h() {
/* 1041 */     GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 1042 */     GlStateManager.func_179090_x();
/* 1043 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*      */     
/* 1045 */     if (Config.isShaders())
/*      */     {
/* 1047 */       Shaders.disableLightmap();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180436_i() {
/* 1053 */     GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 1054 */     GlStateManager.matrixMode(5890);
/* 1055 */     GlStateManager.loadIdentity();
/* 1056 */     float var1 = 0.00390625F;
/* 1057 */     GlStateManager.scale(var1, var1, var1);
/* 1058 */     GlStateManager.translate(8.0F, 8.0F, 8.0F);
/* 1059 */     GlStateManager.matrixMode(5888);
/* 1060 */     this.mc.getTextureManager().bindTexture(this.locationLightMap);
/* 1061 */     GL11.glTexParameteri(3553, 10241, 9729);
/* 1062 */     GL11.glTexParameteri(3553, 10240, 9729);
/* 1063 */     GL11.glTexParameteri(3553, 10242, 10496);
/* 1064 */     GL11.glTexParameteri(3553, 10243, 10496);
/* 1065 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 1066 */     GlStateManager.func_179098_w();
/* 1067 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/*      */     
/* 1069 */     if (Config.isShaders())
/*      */     {
/* 1071 */       Shaders.enableLightmap();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateTorchFlicker() {
/* 1080 */     this.field_175075_L = (float)(this.field_175075_L + (Math.random() - Math.random()) * Math.random() * Math.random());
/* 1081 */     this.field_175075_L = (float)(this.field_175075_L * 0.9D);
/* 1082 */     this.torchFlickerX += (this.field_175075_L - this.torchFlickerX) * 1.0F;
/* 1083 */     this.lightmapUpdateNeeded = true;
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateLightmap(float partialTicks) {
/* 1088 */     if (this.lightmapUpdateNeeded) {
/*      */       
/* 1090 */       this.mc.mcProfiler.startSection("lightTex");
/* 1091 */       WorldClient var2 = this.mc.theWorld;
/*      */       
/* 1093 */       if (var2 != null) {
/*      */         
/* 1095 */         if (Config.isCustomColors() && CustomColors.updateLightmap((World)var2, this.torchFlickerX, this.lightmapColors, this.mc.thePlayer.isPotionActive(Potion.nightVision))) {
/*      */           
/* 1097 */           this.lightmapTexture.updateDynamicTexture();
/* 1098 */           this.lightmapUpdateNeeded = false;
/* 1099 */           this.mc.mcProfiler.endSection();
/*      */           
/*      */           return;
/*      */         } 
/* 1103 */         for (int var3 = 0; var3 < 256; var3++) {
/*      */           
/* 1105 */           float var4 = var2.getSunBrightness(1.0F) * 0.95F + 0.05F;
/* 1106 */           float var5 = var2.provider.getLightBrightnessTable()[var3 / 16] * var4;
/* 1107 */           float var6 = var2.provider.getLightBrightnessTable()[var3 % 16] * (this.torchFlickerX * 0.1F + 1.5F);
/*      */           
/* 1109 */           if (var2.func_175658_ac() > 0)
/*      */           {
/* 1111 */             var5 = var2.provider.getLightBrightnessTable()[var3 / 16];
/*      */           }
/*      */           
/* 1114 */           float var7 = var5 * (var2.getSunBrightness(1.0F) * 0.65F + 0.35F);
/* 1115 */           float var8 = var5 * (var2.getSunBrightness(1.0F) * 0.65F + 0.35F);
/* 1116 */           float var11 = var6 * ((var6 * 0.6F + 0.4F) * 0.6F + 0.4F);
/* 1117 */           float var12 = var6 * (var6 * var6 * 0.6F + 0.4F);
/* 1118 */           float var13 = var7 + var6;
/* 1119 */           float var14 = var8 + var11;
/* 1120 */           float var15 = var5 + var12;
/* 1121 */           var13 = var13 * 0.96F + 0.03F;
/* 1122 */           var14 = var14 * 0.96F + 0.03F;
/* 1123 */           var15 = var15 * 0.96F + 0.03F;
/*      */ 
/*      */           
/* 1126 */           if (this.bossColorModifier > 0.0F) {
/*      */             
/* 1128 */             float f = this.bossColorModifierPrev + (this.bossColorModifier - this.bossColorModifierPrev) * partialTicks;
/* 1129 */             var13 = var13 * (1.0F - f) + var13 * 0.7F * f;
/* 1130 */             var14 = var14 * (1.0F - f) + var14 * 0.6F * f;
/* 1131 */             var15 = var15 * (1.0F - f) + var15 * 0.6F * f;
/*      */           } 
/*      */           
/* 1134 */           if (var2.provider.getDimensionId() == 1) {
/*      */             
/* 1136 */             var13 = 0.22F + var6 * 0.75F;
/* 1137 */             var14 = 0.28F + var11 * 0.75F;
/* 1138 */             var15 = 0.25F + var12 * 0.75F;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1143 */           if (this.mc.thePlayer.isPotionActive(Potion.nightVision)) {
/*      */             
/* 1145 */             float f1 = func_180438_a((EntityLivingBase)this.mc.thePlayer, partialTicks);
/* 1146 */             float f2 = 1.0F / var13;
/*      */             
/* 1148 */             if (f2 > 1.0F / var14)
/*      */             {
/* 1150 */               f2 = 1.0F / var14;
/*      */             }
/*      */             
/* 1153 */             if (f2 > 1.0F / var15)
/*      */             {
/* 1155 */               f2 = 1.0F / var15;
/*      */             }
/*      */             
/* 1158 */             var13 = var13 * (1.0F - f1) + var13 * f2 * f1;
/* 1159 */             var14 = var14 * (1.0F - f1) + var14 * f2 * f1;
/* 1160 */             var15 = var15 * (1.0F - f1) + var15 * f2 * f1;
/*      */           } 
/*      */           
/* 1163 */           if (var13 > 1.0F)
/*      */           {
/* 1165 */             var13 = 1.0F;
/*      */           }
/*      */           
/* 1168 */           if (var14 > 1.0F)
/*      */           {
/* 1170 */             var14 = 1.0F;
/*      */           }
/*      */           
/* 1173 */           if (var15 > 1.0F)
/*      */           {
/* 1175 */             var15 = 1.0F;
/*      */           }
/*      */           
/* 1178 */           float var16 = this.mc.gameSettings.gammaSetting;
/* 1179 */           float var17 = 1.0F - var13;
/* 1180 */           float var18 = 1.0F - var14;
/* 1181 */           float var19 = 1.0F - var15;
/* 1182 */           var17 = 1.0F - var17 * var17 * var17 * var17;
/* 1183 */           var18 = 1.0F - var18 * var18 * var18 * var18;
/* 1184 */           var19 = 1.0F - var19 * var19 * var19 * var19;
/* 1185 */           var13 = var13 * (1.0F - var16) + var17 * var16;
/* 1186 */           var14 = var14 * (1.0F - var16) + var18 * var16;
/* 1187 */           var15 = var15 * (1.0F - var16) + var19 * var16;
/* 1188 */           var13 = var13 * 0.96F + 0.03F;
/* 1189 */           var14 = var14 * 0.96F + 0.03F;
/* 1190 */           var15 = var15 * 0.96F + 0.03F;
/*      */           
/* 1192 */           if (var13 > 1.0F)
/*      */           {
/* 1194 */             var13 = 1.0F;
/*      */           }
/*      */           
/* 1197 */           if (var14 > 1.0F)
/*      */           {
/* 1199 */             var14 = 1.0F;
/*      */           }
/*      */           
/* 1202 */           if (var15 > 1.0F)
/*      */           {
/* 1204 */             var15 = 1.0F;
/*      */           }
/*      */           
/* 1207 */           if (var13 < 0.0F)
/*      */           {
/* 1209 */             var13 = 0.0F;
/*      */           }
/*      */           
/* 1212 */           if (var14 < 0.0F)
/*      */           {
/* 1214 */             var14 = 0.0F;
/*      */           }
/*      */           
/* 1217 */           if (var15 < 0.0F)
/*      */           {
/* 1219 */             var15 = 0.0F;
/*      */           }
/*      */           
/* 1222 */           short var20 = 255;
/* 1223 */           int var21 = (int)(var13 * 255.0F);
/* 1224 */           int var22 = (int)(var14 * 255.0F);
/* 1225 */           int var23 = (int)(var15 * 255.0F);
/* 1226 */           this.lightmapColors[var3] = var20 << 24 | var21 << 16 | var22 << 8 | var23;
/*      */         } 
/*      */         
/* 1229 */         this.lightmapTexture.updateDynamicTexture();
/* 1230 */         this.lightmapUpdateNeeded = false;
/* 1231 */         this.mc.mcProfiler.endSection();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private float func_180438_a(EntityLivingBase p_180438_1_, float partialTicks) {
/* 1238 */     int var3 = p_180438_1_.getActivePotionEffect(Potion.nightVision).getDuration();
/* 1239 */     return (var3 > 200) ? 1.0F : (0.7F + MathHelper.sin((var3 - partialTicks) * 3.1415927F * 0.2F) * 0.3F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateCameraAndRender(float partialTicks) {
/* 1247 */     frameInit();
/* 1248 */     boolean var2 = Display.isActive();
/*      */     
/* 1250 */     if (!var2 && this.mc.gameSettings.pauseOnLostFocus && (!this.mc.gameSettings.touchscreen || !Mouse.isButtonDown(1))) {
/*      */       
/* 1252 */       if (Minecraft.getSystemTime() - this.prevFrameTime > 500L)
/*      */       {
/* 1254 */         this.mc.displayInGameMenu();
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1259 */       this.prevFrameTime = Minecraft.getSystemTime();
/*      */     } 
/*      */     
/* 1262 */     this.mc.mcProfiler.startSection("mouse");
/*      */     
/* 1264 */     if (var2 && Minecraft.isRunningOnMac && this.mc.inGameHasFocus && !Mouse.isInsideWindow()) {
/*      */       
/* 1266 */       Mouse.setGrabbed(false);
/* 1267 */       Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
/* 1268 */       Mouse.setGrabbed(true);
/*      */     } 
/*      */     
/* 1271 */     if (this.mc.inGameHasFocus && var2) {
/*      */       
/* 1273 */       this.mc.mouseHelper.mouseXYChange();
/* 1274 */       float var13 = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
/* 1275 */       float var14 = var13 * var13 * var13 * 8.0F;
/* 1276 */       float var15 = this.mc.mouseHelper.deltaX * var14;
/* 1277 */       float var16 = this.mc.mouseHelper.deltaY * var14;
/* 1278 */       byte var17 = 1;
/*      */       
/* 1280 */       if (this.mc.gameSettings.invertMouse)
/*      */       {
/* 1282 */         var17 = -1;
/*      */       }
/*      */       
/* 1285 */       if (this.mc.gameSettings.smoothCamera) {
/*      */         
/* 1287 */         this.smoothCamYaw += var15;
/* 1288 */         this.smoothCamPitch += var16;
/* 1289 */         float var18 = partialTicks - this.smoothCamPartialTicks;
/* 1290 */         this.smoothCamPartialTicks = partialTicks;
/* 1291 */         var15 = this.smoothCamFilterX * var18;
/* 1292 */         var16 = this.smoothCamFilterY * var18;
/* 1293 */         this.mc.thePlayer.setAngles(var15, var16 * var17);
/*      */       }
/*      */       else {
/*      */         
/* 1297 */         this.smoothCamYaw = 0.0F;
/* 1298 */         this.smoothCamPitch = 0.0F;
/* 1299 */         this.mc.thePlayer.setAngles(var15, var16 * var17);
/*      */       } 
/*      */     } 
/*      */     
/* 1303 */     this.mc.mcProfiler.endSection();
/*      */     
/* 1305 */     if (!this.mc.skipRenderWorld) {
/*      */       
/* 1307 */       anaglyphEnable = this.mc.gameSettings.anaglyph;
/* 1308 */       final ScaledResolution var131 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 1309 */       int var141 = var131.getScaledWidth();
/* 1310 */       int var151 = var131.getScaledHeight();
/* 1311 */       final int var161 = Mouse.getX() * var141 / this.mc.displayWidth;
/* 1312 */       final int var171 = var151 - Mouse.getY() * var151 / this.mc.displayHeight - 1;
/* 1313 */       int var181 = this.mc.gameSettings.limitFramerate;
/*      */       
/* 1315 */       if (this.mc.theWorld != null) {
/*      */         
/* 1317 */         this.mc.mcProfiler.startSection("level");
/* 1318 */         int var12 = Math.max(Minecraft.func_175610_ah(), 30);
/* 1319 */         renderWorld(partialTicks, this.renderEndNanoTime + (1000000000 / var12));
/*      */         
/* 1321 */         if (OpenGlHelper.shadersSupported) {
/*      */           
/* 1323 */           this.mc.renderGlobal.func_174975_c();
/*      */           
/* 1325 */           if (this.theShaderGroup != null && this.field_175083_ad) {
/*      */             
/* 1327 */             GlStateManager.matrixMode(5890);
/* 1328 */             GlStateManager.pushMatrix();
/* 1329 */             GlStateManager.loadIdentity();
/* 1330 */             this.theShaderGroup.loadShaderGroup(partialTicks);
/* 1331 */             GlStateManager.popMatrix();
/*      */           } 
/*      */           
/* 1334 */           this.mc.getFramebuffer().bindFramebuffer(true);
/*      */         } 
/*      */         
/* 1337 */         this.renderEndNanoTime = System.nanoTime();
/* 1338 */         this.mc.mcProfiler.endStartSection("gui");
/*      */         
/* 1340 */         if (!this.mc.gameSettings.hideGUI || this.mc.currentScreen != null) {
/*      */           
/* 1342 */           GlStateManager.alphaFunc(516, 0.1F);
/* 1343 */           this.mc.ingameGUI.func_175180_a(partialTicks);
/*      */           
/* 1345 */           if (this.mc.gameSettings.ofShowFps && !this.mc.gameSettings.showDebugInfo)
/*      */           {
/* 1347 */             Config.drawFps();
/*      */           }
/*      */           
/* 1350 */           if (this.mc.gameSettings.showDebugInfo)
/*      */           {
/* 1352 */             Lagometer.showLagometer(var131);
/*      */           }
/*      */         } 
/*      */         
/* 1356 */         this.mc.mcProfiler.endSection();
/*      */       }
/*      */       else {
/*      */         
/* 1360 */         GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/* 1361 */         GlStateManager.matrixMode(5889);
/* 1362 */         GlStateManager.loadIdentity();
/* 1363 */         GlStateManager.matrixMode(5888);
/* 1364 */         GlStateManager.loadIdentity();
/* 1365 */         setupOverlayRendering();
/* 1366 */         this.renderEndNanoTime = System.nanoTime();
/*      */       } 
/*      */       
/* 1369 */       if (this.mc.currentScreen != null) {
/*      */         
/* 1371 */         GlStateManager.clear(256);
/*      */ 
/*      */         
/*      */         try {
/* 1375 */           if (Reflector.ForgeHooksClient_drawScreen.exists())
/*      */           {
/* 1377 */             Reflector.callVoid(Reflector.ForgeHooksClient_drawScreen, new Object[] { this.mc.currentScreen, Integer.valueOf(var161), Integer.valueOf(var171), Float.valueOf(partialTicks) });
/*      */           }
/*      */           else
/*      */           {
/* 1381 */             this.mc.currentScreen.drawScreen(var161, var171, partialTicks);
/*      */           }
/*      */         
/* 1384 */         } catch (Throwable var121) {
/*      */           
/* 1386 */           CrashReport var10 = CrashReport.makeCrashReport(var121, "Rendering screen");
/* 1387 */           CrashReportCategory var11 = var10.makeCategory("Screen render details");
/* 1388 */           var11.addCrashSectionCallable("Screen name", new Callable()
/*      */               {
/*      */                 private static final String __OBFID = "CL_00000948";
/*      */                 
/*      */                 public String call() {
/* 1393 */                   return EntityRenderer.this.mc.currentScreen.getClass().getCanonicalName();
/*      */                 }
/*      */               });
/* 1396 */           var11.addCrashSectionCallable("Mouse location", new Callable()
/*      */               {
/*      */                 private static final String __OBFID = "CL_00000950";
/*      */                 
/*      */                 public String call() {
/* 1401 */                   return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", new Object[] { Integer.valueOf(this.val$var161), Integer.valueOf(this.val$var171), Integer.valueOf(Mouse.getX()), Integer.valueOf(Mouse.getY()) });
/*      */                 }
/*      */               });
/* 1404 */           var11.addCrashSectionCallable("Screen size", new Callable()
/*      */               {
/*      */                 private static final String __OBFID = "CL_00000951";
/*      */                 
/*      */                 public String call() {
/* 1409 */                   return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", new Object[] { Integer.valueOf(this.val$var131.getScaledWidth()), Integer.valueOf(this.val$var131.getScaledHeight()), Integer.valueOf((EntityRenderer.access$0(this.this$0)).displayWidth), Integer.valueOf((EntityRenderer.access$0(this.this$0)).displayHeight), Integer.valueOf(this.val$var131.getScaleFactor()) });
/*      */                 }
/*      */               });
/* 1412 */           throw new ReportedException(var10);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1417 */     frameFinish();
/* 1418 */     waitForServerThread();
/* 1419 */     Lagometer.updateLagometer();
/*      */     
/* 1421 */     if (this.mc.gameSettings.ofProfiler)
/*      */     {
/* 1423 */       this.mc.gameSettings.showDebugProfilerChart = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152430_c(float p_152430_1_) {
/* 1429 */     setupOverlayRendering();
/* 1430 */     this.mc.ingameGUI.func_180478_c(new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight));
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_175070_n() {
/* 1435 */     if (!this.field_175073_D)
/*      */     {
/* 1437 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1441 */     Entity var1 = this.mc.func_175606_aa();
/* 1442 */     boolean var2 = (var1 instanceof EntityPlayer && !this.mc.gameSettings.hideGUI);
/*      */     
/* 1444 */     if (var2 && !((EntityPlayer)var1).capabilities.allowEdit) {
/*      */       
/* 1446 */       ItemStack var3 = ((EntityPlayer)var1).getCurrentEquippedItem();
/*      */       
/* 1448 */       if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*      */         
/* 1450 */         BlockPos var4 = this.mc.objectMouseOver.func_178782_a();
/* 1451 */         IBlockState state = this.mc.theWorld.getBlockState(var4);
/* 1452 */         Block var5 = state.getBlock();
/*      */         
/* 1454 */         if (this.mc.playerController.func_178889_l() == WorldSettings.GameType.SPECTATOR) {
/*      */           
/* 1456 */           var2 = (ReflectorForge.blockHasTileEntity(state) && this.mc.theWorld.getTileEntity(var4) instanceof net.minecraft.inventory.IInventory);
/*      */         }
/*      */         else {
/*      */           
/* 1460 */           var2 = (var3 != null && (var3.canDestroy(var5) || var3.canPlaceOn(var5)));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1465 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void func_175067_i(float p_175067_1_) {
/* 1471 */     if (this.mc.gameSettings.showDebugInfo && !this.mc.gameSettings.hideGUI && !this.mc.thePlayer.func_175140_cp() && !this.mc.gameSettings.field_178879_v) {
/*      */       
/* 1473 */       Entity var2 = this.mc.func_175606_aa();
/* 1474 */       GlStateManager.enableBlend();
/* 1475 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1476 */       GL11.glLineWidth(1.0F);
/* 1477 */       GlStateManager.func_179090_x();
/* 1478 */       GlStateManager.depthMask(false);
/* 1479 */       GlStateManager.pushMatrix();
/* 1480 */       GlStateManager.matrixMode(5888);
/* 1481 */       GlStateManager.loadIdentity();
/* 1482 */       orientCamera(p_175067_1_);
/* 1483 */       GlStateManager.translate(0.0F, var2.getEyeHeight(), 0.0F);
/* 1484 */       RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.005D, 1.0E-4D, 1.0E-4D), -65536);
/* 1485 */       RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0E-4D, 1.0E-4D, 0.005D), -16776961);
/* 1486 */       RenderGlobal.drawOutlinedBoundingBox(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0E-4D, 0.0033D, 1.0E-4D), -16711936);
/* 1487 */       GlStateManager.popMatrix();
/* 1488 */       GlStateManager.depthMask(true);
/* 1489 */       GlStateManager.func_179098_w();
/* 1490 */       GlStateManager.disableBlend();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void renderWorld(float partialTicks, long finishTimeNano) {
/* 1496 */     updateLightmap(partialTicks);
/*      */     
/* 1498 */     if (this.mc.func_175606_aa() == null)
/*      */     {
/* 1500 */       this.mc.func_175607_a((Entity)this.mc.thePlayer);
/*      */     }
/*      */     
/* 1503 */     getMouseOver(partialTicks);
/*      */     
/* 1505 */     if (Config.isShaders())
/*      */     {
/* 1507 */       Shaders.beginRender(this.mc, partialTicks, finishTimeNano);
/*      */     }
/*      */     
/* 1510 */     GlStateManager.enableDepth();
/* 1511 */     GlStateManager.enableAlpha();
/* 1512 */     GlStateManager.alphaFunc(516, 0.1F);
/* 1513 */     this.mc.mcProfiler.startSection("center");
/*      */     
/* 1515 */     if (this.mc.gameSettings.anaglyph) {
/*      */       
/* 1517 */       anaglyphField = 0;
/* 1518 */       GlStateManager.colorMask(false, true, true, false);
/* 1519 */       func_175068_a(0, partialTicks, finishTimeNano);
/* 1520 */       anaglyphField = 1;
/* 1521 */       GlStateManager.colorMask(true, false, false, false);
/* 1522 */       func_175068_a(1, partialTicks, finishTimeNano);
/* 1523 */       GlStateManager.colorMask(true, true, true, false);
/*      */     }
/*      */     else {
/*      */       
/* 1527 */       func_175068_a(2, partialTicks, finishTimeNano);
/*      */     } 
/*      */     
/* 1530 */     this.mc.mcProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_175068_a(int pass, float partialTicks, long finishTimeNano) {
/* 1535 */     boolean isShaders = Config.isShaders();
/*      */     
/* 1537 */     if (isShaders)
/*      */     {
/* 1539 */       Shaders.beginRenderPass(pass, partialTicks, finishTimeNano);
/*      */     }
/*      */     
/* 1542 */     RenderGlobal var5 = this.mc.renderGlobal;
/* 1543 */     EffectRenderer var6 = this.mc.effectRenderer;
/* 1544 */     boolean var7 = func_175070_n();
/* 1545 */     GlStateManager.enableCull();
/* 1546 */     this.mc.mcProfiler.endStartSection("clear");
/*      */     
/* 1548 */     if (isShaders) {
/*      */       
/* 1550 */       Shaders.setViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/*      */     }
/*      */     else {
/*      */       
/* 1554 */       GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/*      */     } 
/*      */     
/* 1557 */     updateFogColor(partialTicks);
/* 1558 */     GlStateManager.clear(16640);
/*      */     
/* 1560 */     if (isShaders)
/*      */     {
/* 1562 */       Shaders.clearRenderBuffer();
/*      */     }
/*      */     
/* 1565 */     this.mc.mcProfiler.endStartSection("camera");
/* 1566 */     setupCameraTransform(partialTicks, pass);
/*      */     
/* 1568 */     if (isShaders)
/*      */     {
/* 1570 */       Shaders.setCamera(partialTicks);
/*      */     }
/*      */     
/* 1573 */     ActiveRenderInfo.updateRenderInfo((EntityPlayer)this.mc.thePlayer, (this.mc.gameSettings.thirdPersonView == 2));
/* 1574 */     this.mc.mcProfiler.endStartSection("frustum");
/* 1575 */     ClippingHelperImpl.getInstance();
/* 1576 */     this.mc.mcProfiler.endStartSection("culling");
/* 1577 */     Frustrum var8 = new Frustrum();
/* 1578 */     Entity var9 = this.mc.func_175606_aa();
/* 1579 */     double var10 = var9.lastTickPosX + (var9.posX - var9.lastTickPosX) * partialTicks;
/* 1580 */     double var12 = var9.lastTickPosY + (var9.posY - var9.lastTickPosY) * partialTicks;
/* 1581 */     double var14 = var9.lastTickPosZ + (var9.posZ - var9.lastTickPosZ) * partialTicks;
/*      */     
/* 1583 */     if (isShaders) {
/*      */       
/* 1585 */       ShadersRender.setFrustrumPosition(var8, var10, var12, var14);
/*      */     }
/*      */     else {
/*      */       
/* 1589 */       var8.setPosition(var10, var12, var14);
/*      */     } 
/*      */     
/* 1592 */     if ((Config.isSkyEnabled() || Config.isSunMoonEnabled() || Config.isStarsEnabled()) && !Shaders.isShadowPass) {
/*      */       
/* 1594 */       setupFog(-1, partialTicks);
/* 1595 */       this.mc.mcProfiler.endStartSection("sky");
/* 1596 */       GlStateManager.matrixMode(5889);
/* 1597 */       GlStateManager.loadIdentity();
/* 1598 */       Project.gluPerspective(getFOVModifier(partialTicks, true), this.mc.displayWidth / this.mc.displayHeight, 0.05F, this.clipDistance);
/* 1599 */       GlStateManager.matrixMode(5888);
/*      */       
/* 1601 */       if (isShaders)
/*      */       {
/* 1603 */         Shaders.beginSky();
/*      */       }
/*      */       
/* 1606 */       var5.func_174976_a(partialTicks, pass);
/*      */       
/* 1608 */       if (isShaders)
/*      */       {
/* 1610 */         Shaders.endSky();
/*      */       }
/*      */       
/* 1613 */       GlStateManager.matrixMode(5889);
/* 1614 */       GlStateManager.loadIdentity();
/* 1615 */       Project.gluPerspective(getFOVModifier(partialTicks, true), this.mc.displayWidth / this.mc.displayHeight, 0.05F, this.clipDistance);
/* 1616 */       GlStateManager.matrixMode(5888);
/*      */     }
/*      */     else {
/*      */       
/* 1620 */       GlStateManager.disableBlend();
/*      */     } 
/*      */     
/* 1623 */     setupFog(0, partialTicks);
/* 1624 */     GlStateManager.shadeModel(7425);
/*      */     
/* 1626 */     if (var9.posY + var9.getEyeHeight() < 128.0D + (this.mc.gameSettings.ofCloudsHeight * 128.0F))
/*      */     {
/* 1628 */       func_180437_a(var5, partialTicks, pass);
/*      */     }
/*      */     
/* 1631 */     this.mc.mcProfiler.endStartSection("prepareterrain");
/* 1632 */     setupFog(0, partialTicks);
/* 1633 */     this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 1634 */     RenderHelper.disableStandardItemLighting();
/* 1635 */     this.mc.mcProfiler.endStartSection("terrain_setup");
/*      */     
/* 1637 */     if (isShaders) {
/*      */       
/* 1639 */       ShadersRender.setupTerrain(var5, var9, partialTicks, (ICamera)var8, this.field_175084_ae++, this.mc.thePlayer.func_175149_v());
/*      */     }
/*      */     else {
/*      */       
/* 1643 */       var5.func_174970_a(var9, partialTicks, (ICamera)var8, this.field_175084_ae++, this.mc.thePlayer.func_175149_v());
/*      */     } 
/*      */     
/* 1646 */     if (pass == 0 || pass == 2) {
/*      */       
/* 1648 */       this.mc.mcProfiler.endStartSection("updatechunks");
/* 1649 */       Lagometer.timerChunkUpload.start();
/*      */       
/* 1651 */       if (isShaders) {
/*      */         
/* 1653 */         ShadersRender.updateChunks(var5, finishTimeNano);
/*      */       }
/*      */       else {
/*      */         
/* 1657 */         this.mc.renderGlobal.func_174967_a(finishTimeNano);
/*      */       } 
/*      */       
/* 1660 */       Lagometer.timerChunkUpload.end();
/*      */     } 
/*      */     
/* 1663 */     this.mc.mcProfiler.endStartSection("terrain");
/* 1664 */     Lagometer.timerTerrain.start();
/*      */     
/* 1666 */     if (this.mc.gameSettings.ofSmoothFps && pass > 0) {
/*      */       
/* 1668 */       this.mc.mcProfiler.endStartSection("finish");
/* 1669 */       GL11.glFinish();
/* 1670 */       this.mc.mcProfiler.endStartSection("terrain");
/*      */     } 
/*      */     
/* 1673 */     GlStateManager.matrixMode(5888);
/* 1674 */     GlStateManager.pushMatrix();
/* 1675 */     GlStateManager.disableAlpha();
/*      */     
/* 1677 */     if (isShaders)
/*      */     {
/* 1679 */       ShadersRender.beginTerrainSolid();
/*      */     }
/*      */     
/* 1682 */     var5.func_174977_a(EnumWorldBlockLayer.SOLID, partialTicks, pass, var9);
/* 1683 */     GlStateManager.enableAlpha();
/*      */     
/* 1685 */     if (isShaders)
/*      */     {
/* 1687 */       ShadersRender.beginTerrainCutoutMipped();
/*      */     }
/*      */     
/* 1690 */     var5.func_174977_a(EnumWorldBlockLayer.CUTOUT_MIPPED, partialTicks, pass, var9);
/* 1691 */     this.mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).func_174936_b(false, false);
/*      */     
/* 1693 */     if (isShaders)
/*      */     {
/* 1695 */       ShadersRender.beginTerrainCutout();
/*      */     }
/*      */     
/* 1698 */     var5.func_174977_a(EnumWorldBlockLayer.CUTOUT, partialTicks, pass, var9);
/* 1699 */     this.mc.getTextureManager().getTexture(TextureMap.locationBlocksTexture).func_174935_a();
/*      */     
/* 1701 */     if (isShaders)
/*      */     {
/* 1703 */       ShadersRender.endTerrain();
/*      */     }
/*      */     
/* 1706 */     Lagometer.timerTerrain.end();
/* 1707 */     GlStateManager.shadeModel(7424);
/* 1708 */     GlStateManager.alphaFunc(516, 0.1F);
/*      */ 
/*      */     
/* 1711 */     if (!this.field_175078_W) {
/*      */       
/* 1713 */       GlStateManager.matrixMode(5888);
/* 1714 */       GlStateManager.popMatrix();
/* 1715 */       GlStateManager.pushMatrix();
/* 1716 */       RenderHelper.enableStandardItemLighting();
/* 1717 */       this.mc.mcProfiler.endStartSection("entities");
/*      */       
/* 1719 */       if (Reflector.ForgeHooksClient_setRenderPass.exists())
/*      */       {
/* 1721 */         Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(0) });
/*      */       }
/*      */       
/* 1724 */       var5.func_180446_a(var9, (ICamera)var8, partialTicks);
/*      */       
/* 1726 */       if (Reflector.ForgeHooksClient_setRenderPass.exists())
/*      */       {
/* 1728 */         Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(-1) });
/*      */       }
/*      */       
/* 1731 */       RenderHelper.disableStandardItemLighting();
/* 1732 */       func_175072_h();
/* 1733 */       GlStateManager.matrixMode(5888);
/* 1734 */       GlStateManager.popMatrix();
/* 1735 */       GlStateManager.pushMatrix();
/*      */       
/* 1737 */       if (this.mc.objectMouseOver != null && var9.isInsideOfMaterial(Material.water) && var7) {
/*      */         
/* 1739 */         EntityPlayer var16 = (EntityPlayer)var9;
/* 1740 */         GlStateManager.disableAlpha();
/* 1741 */         this.mc.mcProfiler.endStartSection("outline");
/*      */         
/* 1743 */         if ((!Reflector.ForgeHooksClient_onDrawBlockHighlight.exists() || !Reflector.callBoolean(Reflector.ForgeHooksClient_onDrawBlockHighlight, new Object[] { var5, var16, this.mc.objectMouseOver, Integer.valueOf(0), var16.getHeldItem(), Float.valueOf(partialTicks) })) && !this.mc.gameSettings.hideGUI)
/*      */         {
/* 1745 */           var5.drawSelectionBox(var16, this.mc.objectMouseOver, 0, partialTicks);
/*      */         }
/* 1747 */         GlStateManager.enableAlpha();
/*      */       } 
/*      */     } 
/*      */     
/* 1751 */     GlStateManager.matrixMode(5888);
/* 1752 */     GlStateManager.popMatrix();
/*      */     
/* 1754 */     if (var7 && this.mc.objectMouseOver != null && !var9.isInsideOfMaterial(Material.water)) {
/*      */       
/* 1756 */       EntityPlayer var16 = (EntityPlayer)var9;
/* 1757 */       GlStateManager.disableAlpha();
/* 1758 */       this.mc.mcProfiler.endStartSection("outline");
/*      */       
/* 1760 */       if ((!Reflector.ForgeHooksClient_onDrawBlockHighlight.exists() || !Reflector.callBoolean(Reflector.ForgeHooksClient_onDrawBlockHighlight, new Object[] { var5, var16, this.mc.objectMouseOver, Integer.valueOf(0), var16.getHeldItem(), Float.valueOf(partialTicks) })) && !this.mc.gameSettings.hideGUI)
/*      */       {
/* 1762 */         var5.drawSelectionBox(var16, this.mc.objectMouseOver, 0, partialTicks);
/*      */       }
/* 1764 */       GlStateManager.enableAlpha();
/*      */     } 
/*      */     
/* 1767 */     if (!var5.damagedBlocks.isEmpty()) {
/*      */       
/* 1769 */       this.mc.mcProfiler.endStartSection("destroyProgress");
/* 1770 */       GlStateManager.enableBlend();
/* 1771 */       GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
/* 1772 */       var5.func_174981_a(Tessellator.getInstance(), Tessellator.getInstance().getWorldRenderer(), var9, partialTicks);
/* 1773 */       GlStateManager.disableBlend();
/*      */     } 
/*      */     
/* 1776 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1777 */     GlStateManager.disableBlend();
/*      */     
/* 1779 */     if (!this.field_175078_W) {
/*      */       
/* 1781 */       func_180436_i();
/* 1782 */       this.mc.mcProfiler.endStartSection("litParticles");
/*      */       
/* 1784 */       if (isShaders)
/*      */       {
/* 1786 */         Shaders.beginLitParticles();
/*      */       }
/*      */       
/* 1789 */       var6.renderLitParticles(var9, partialTicks);
/* 1790 */       RenderHelper.disableStandardItemLighting();
/* 1791 */       setupFog(0, partialTicks);
/* 1792 */       this.mc.mcProfiler.endStartSection("particles");
/*      */       
/* 1794 */       if (isShaders)
/*      */       {
/* 1796 */         Shaders.beginParticles();
/*      */       }
/*      */       
/* 1799 */       EffectRenderer.renderParticles(var9, partialTicks);
/*      */       
/* 1801 */       if (isShaders)
/*      */       {
/* 1803 */         Shaders.endParticles();
/*      */       }
/*      */       
/* 1806 */       func_175072_h();
/*      */     } 
/*      */     
/* 1809 */     GlStateManager.depthMask(false);
/* 1810 */     GlStateManager.enableCull();
/* 1811 */     this.mc.mcProfiler.endStartSection("weather");
/*      */     
/* 1813 */     if (isShaders)
/*      */     {
/* 1815 */       Shaders.beginWeather();
/*      */     }
/*      */     
/* 1818 */     renderRainSnow(partialTicks);
/*      */     
/* 1820 */     if (isShaders)
/*      */     {
/* 1822 */       Shaders.endWeather();
/*      */     }
/*      */     
/* 1825 */     GlStateManager.depthMask(true);
/* 1826 */     var5.func_180449_a(var9, partialTicks);
/*      */     
/* 1828 */     if (isShaders) {
/*      */       
/* 1830 */       ShadersRender.renderHand0(this, partialTicks, pass);
/* 1831 */       Shaders.preWater();
/*      */     } 
/*      */     
/* 1834 */     GlStateManager.disableBlend();
/* 1835 */     GlStateManager.enableCull();
/* 1836 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1837 */     GlStateManager.alphaFunc(516, 0.1F);
/* 1838 */     setupFog(0, partialTicks);
/* 1839 */     GlStateManager.enableBlend();
/* 1840 */     GlStateManager.depthMask(false);
/* 1841 */     this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 1842 */     GlStateManager.shadeModel(7425);
/*      */     
/* 1844 */     if (Config.isTranslucentBlocksFancy()) {
/*      */       
/* 1846 */       this.mc.mcProfiler.endStartSection("translucent");
/* 1847 */       GlStateManager.enableBlend();
/* 1848 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*      */       
/* 1850 */       if (isShaders)
/*      */       {
/* 1852 */         Shaders.beginWater();
/*      */       }
/*      */       
/* 1855 */       var5.func_174977_a(EnumWorldBlockLayer.TRANSLUCENT, partialTicks, pass, var9);
/*      */       
/* 1857 */       if (isShaders)
/*      */       {
/* 1859 */         Shaders.endWater();
/*      */       }
/*      */       
/* 1862 */       GlStateManager.disableBlend();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1867 */       this.mc.mcProfiler.endStartSection("translucent");
/*      */       
/* 1869 */       if (isShaders)
/*      */       {
/* 1871 */         Shaders.beginWater();
/*      */       }
/*      */       
/* 1874 */       var5.func_174977_a(EnumWorldBlockLayer.TRANSLUCENT, partialTicks, pass, var9);
/*      */       
/* 1876 */       if (isShaders)
/*      */       {
/* 1878 */         Shaders.endWater();
/*      */       }
/*      */     } 
/*      */     
/* 1882 */     if (Reflector.ForgeHooksClient_setRenderPass.exists() && !this.field_175078_W) {
/*      */       
/* 1884 */       RenderHelper.enableStandardItemLighting();
/* 1885 */       this.mc.mcProfiler.endStartSection("entities");
/* 1886 */       Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(1) });
/* 1887 */       this.mc.renderGlobal.func_180446_a(var9, (ICamera)var8, partialTicks);
/* 1888 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1889 */       Reflector.callVoid(Reflector.ForgeHooksClient_setRenderPass, new Object[] { Integer.valueOf(-1) });
/* 1890 */       RenderHelper.disableStandardItemLighting();
/*      */     } 
/*      */     
/* 1893 */     GlStateManager.shadeModel(7424);
/* 1894 */     GlStateManager.depthMask(true);
/* 1895 */     GlStateManager.enableCull();
/* 1896 */     GlStateManager.disableBlend();
/* 1897 */     GlStateManager.disableFog();
/*      */     
/* 1899 */     if (var9.posY + var9.getEyeHeight() >= 128.0D + (this.mc.gameSettings.ofCloudsHeight * 128.0F)) {
/*      */       
/* 1901 */       this.mc.mcProfiler.endStartSection("aboveClouds");
/* 1902 */       func_180437_a(var5, partialTicks, pass);
/*      */     } 
/*      */     
/* 1905 */     if (Reflector.ForgeHooksClient_dispatchRenderLast.exists()) {
/*      */       
/* 1907 */       this.mc.mcProfiler.endStartSection("forge_render_last");
/* 1908 */       Reflector.callVoid(Reflector.ForgeHooksClient_dispatchRenderLast, new Object[] { var5, Float.valueOf(partialTicks) });
/*      */     } 
/*      */     
/* 1911 */     Client.onEvent((Event)new EventRenderWorld());
/*      */     
/* 1913 */     this.mc.mcProfiler.endStartSection("hand");
/* 1914 */     boolean handRendered = Reflector.callBoolean(Reflector.ForgeHooksClient_renderFirstPersonHand, new Object[] { this.mc.renderGlobal, Float.valueOf(partialTicks), Integer.valueOf(pass) });
/*      */     
/* 1916 */     if (!handRendered && this.field_175074_C && !Shaders.isShadowPass) {
/*      */       
/* 1918 */       if (isShaders) {
/*      */         
/* 1920 */         ShadersRender.renderHand1(this, partialTicks, pass);
/* 1921 */         Shaders.renderCompositeFinal();
/*      */       } 
/*      */       
/* 1924 */       GlStateManager.clear(256);
/*      */       
/* 1926 */       if (isShaders) {
/*      */         
/* 1928 */         ShadersRender.renderFPOverlay(this, partialTicks, pass);
/*      */       }
/*      */       else {
/*      */         
/* 1932 */         renderHand(partialTicks, pass);
/*      */       } 
/*      */       
/* 1935 */       func_175067_i(partialTicks);
/*      */     } 
/*      */     
/* 1938 */     if (isShaders)
/*      */     {
/* 1940 */       Shaders.endRender();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180437_a(RenderGlobal p_180437_1_, float partialTicks, int pass) {
/* 1946 */     if (this.mc.gameSettings.renderDistanceChunks >= 4 && !Config.isCloudsOff() && Shaders.shouldRenderClouds(this.mc.gameSettings)) {
/*      */       
/* 1948 */       this.mc.mcProfiler.endStartSection("clouds");
/* 1949 */       GlStateManager.matrixMode(5889);
/* 1950 */       GlStateManager.loadIdentity();
/* 1951 */       Project.gluPerspective(getFOVModifier(partialTicks, true), this.mc.displayWidth / this.mc.displayHeight, 0.05F, this.clipDistance * 4.0F);
/* 1952 */       GlStateManager.matrixMode(5888);
/* 1953 */       GlStateManager.pushMatrix();
/* 1954 */       setupFog(0, partialTicks);
/* 1955 */       p_180437_1_.func_180447_b(partialTicks, pass);
/* 1956 */       GlStateManager.disableFog();
/* 1957 */       GlStateManager.popMatrix();
/* 1958 */       GlStateManager.matrixMode(5889);
/* 1959 */       GlStateManager.loadIdentity();
/* 1960 */       Project.gluPerspective(getFOVModifier(partialTicks, true), this.mc.displayWidth / this.mc.displayHeight, 0.05F, this.clipDistance);
/* 1961 */       GlStateManager.matrixMode(5888);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void addRainParticles() {
/* 1967 */     float var1 = this.mc.theWorld.getRainStrength(1.0F);
/*      */     
/* 1969 */     if (!Config.isRainFancy())
/*      */     {
/* 1971 */       var1 /= 2.0F;
/*      */     }
/*      */     
/* 1974 */     if (var1 != 0.0F && Config.isRainSplash()) {
/*      */       
/* 1976 */       this.random.setSeed(this.rendererUpdateCount * 312987231L);
/* 1977 */       Entity var2 = this.mc.func_175606_aa();
/* 1978 */       WorldClient var3 = this.mc.theWorld;
/* 1979 */       BlockPos var4 = new BlockPos(var2);
/* 1980 */       byte var5 = 10;
/* 1981 */       double var6 = 0.0D;
/* 1982 */       double var8 = 0.0D;
/* 1983 */       double var10 = 0.0D;
/* 1984 */       int var12 = 0;
/* 1985 */       int var13 = (int)(100.0F * var1 * var1);
/*      */       
/* 1987 */       if (this.mc.gameSettings.particleSetting == 1) {
/*      */         
/* 1989 */         var13 >>= 1;
/*      */       }
/* 1991 */       else if (this.mc.gameSettings.particleSetting == 2) {
/*      */         
/* 1993 */         var13 = 0;
/*      */       } 
/*      */       
/* 1996 */       for (int var14 = 0; var14 < var13; var14++) {
/*      */         
/* 1998 */         BlockPos var15 = var3.func_175725_q(var4.add(this.random.nextInt(var5) - this.random.nextInt(var5), 0, this.random.nextInt(var5) - this.random.nextInt(var5)));
/* 1999 */         BiomeGenBase var16 = var3.getBiomeGenForCoords(var15);
/* 2000 */         BlockPos var17 = var15.offsetDown();
/* 2001 */         Block var18 = var3.getBlockState(var17).getBlock();
/*      */         
/* 2003 */         if (var15.getY() <= var4.getY() + var5 && var15.getY() >= var4.getY() - var5 && var16.canSpawnLightningBolt() && var16.func_180626_a(var15) >= 0.15F) {
/*      */           
/* 2005 */           float var19 = this.random.nextFloat();
/* 2006 */           float var20 = this.random.nextFloat();
/*      */           
/* 2008 */           if (var18.getMaterial() == Material.lava) {
/*      */             
/* 2010 */             this.mc.theWorld.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (var15.getX() + var19), (var15.getY() + 0.1F) - var18.getBlockBoundsMinY(), (var15.getZ() + var20), 0.0D, 0.0D, 0.0D, new int[0]);
/*      */           }
/* 2012 */           else if (var18.getMaterial() != Material.air) {
/*      */             
/* 2014 */             var18.setBlockBoundsBasedOnState((IBlockAccess)var3, var17);
/* 2015 */             var12++;
/*      */             
/* 2017 */             if (this.random.nextInt(var12) == 0) {
/*      */               
/* 2019 */               var6 = (var17.getX() + var19);
/* 2020 */               var8 = (var17.getY() + 0.1F) + var18.getBlockBoundsMaxY() - 1.0D;
/* 2021 */               var10 = (var17.getZ() + var20);
/*      */             } 
/*      */             
/* 2024 */             this.mc.theWorld.spawnParticle(EnumParticleTypes.WATER_DROP, (var17.getX() + var19), (var17.getY() + 0.1F) + var18.getBlockBoundsMaxY(), (var17.getZ() + var20), 0.0D, 0.0D, 0.0D, new int[0]);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2029 */       if (var12 > 0 && this.random.nextInt(3) < this.rainSoundCounter++) {
/*      */         
/* 2031 */         this.rainSoundCounter = 0;
/*      */         
/* 2033 */         if (var8 > (var4.getY() + 1) && var3.func_175725_q(var4).getY() > MathHelper.floor_float(var4.getY())) {
/*      */           
/* 2035 */           this.mc.theWorld.playSound(var6, var8, var10, "ambient.weather.rain", 0.1F, 0.5F, false);
/*      */         }
/*      */         else {
/*      */           
/* 2039 */           this.mc.theWorld.playSound(var6, var8, var10, "ambient.weather.rain", 0.2F, 1.0F, false);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void renderRainSnow(float partialTicks) {
/* 2050 */     if (Reflector.ForgeWorldProvider_getWeatherRenderer.exists()) {
/*      */       
/* 2052 */       WorldProvider var2 = this.mc.theWorld.provider;
/* 2053 */       Object var3 = Reflector.call(var2, Reflector.ForgeWorldProvider_getWeatherRenderer, new Object[0]);
/*      */       
/* 2055 */       if (var3 != null) {
/*      */         
/* 2057 */         Reflector.callVoid(var3, Reflector.IRenderHandler_render, new Object[] { Float.valueOf(partialTicks), this.mc.theWorld, this.mc });
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/* 2062 */     float var421 = this.mc.theWorld.getRainStrength(partialTicks);
/*      */     
/* 2064 */     if (var421 > 0.0F) {
/*      */       
/* 2066 */       if (Config.isRainOff()) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 2071 */       func_180436_i();
/* 2072 */       Entity var431 = this.mc.func_175606_aa();
/* 2073 */       WorldClient var4 = this.mc.theWorld;
/* 2074 */       int var5 = MathHelper.floor_double(var431.posX);
/* 2075 */       int var6 = MathHelper.floor_double(var431.posY);
/* 2076 */       int var7 = MathHelper.floor_double(var431.posZ);
/* 2077 */       Tessellator var8 = Tessellator.getInstance();
/* 2078 */       WorldRenderer var9 = var8.getWorldRenderer();
/* 2079 */       GlStateManager.disableCull();
/* 2080 */       GL11.glNormal3f(0.0F, 1.0F, 0.0F);
/* 2081 */       GlStateManager.enableBlend();
/* 2082 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 2083 */       GlStateManager.alphaFunc(516, 0.1F);
/* 2084 */       double var10 = var431.lastTickPosX + (var431.posX - var431.lastTickPosX) * partialTicks;
/* 2085 */       double var12 = var431.lastTickPosY + (var431.posY - var431.lastTickPosY) * partialTicks;
/* 2086 */       double var14 = var431.lastTickPosZ + (var431.posZ - var431.lastTickPosZ) * partialTicks;
/* 2087 */       int var16 = MathHelper.floor_double(var12);
/* 2088 */       byte var17 = 5;
/*      */       
/* 2090 */       if (Config.isRainFancy())
/*      */       {
/* 2092 */         var17 = 10;
/*      */       }
/*      */       
/* 2095 */       byte var18 = -1;
/* 2096 */       float var19 = this.rendererUpdateCount + partialTicks;
/*      */       
/* 2098 */       if (Config.isRainFancy())
/*      */       {
/* 2100 */         var17 = 10;
/*      */       }
/*      */       
/* 2103 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*      */       
/* 2105 */       for (int var20 = var7 - var17; var20 <= var7 + var17; var20++) {
/*      */         
/* 2107 */         for (int var21 = var5 - var17; var21 <= var5 + var17; var21++) {
/*      */           
/* 2109 */           int var22 = (var20 - var7 + 16) * 32 + var21 - var5 + 16;
/* 2110 */           float var23 = this.field_175076_N[var22] * 0.5F;
/* 2111 */           float var24 = this.field_175077_O[var22] * 0.5F;
/* 2112 */           BlockPos var25 = new BlockPos(var21, 0, var20);
/* 2113 */           BiomeGenBase var26 = var4.getBiomeGenForCoords(var25);
/*      */           
/* 2115 */           if (var26.canSpawnLightningBolt() || var26.getEnableSnow()) {
/*      */             
/* 2117 */             int var27 = var4.func_175725_q(var25).getY();
/* 2118 */             int var28 = var6 - var17;
/* 2119 */             int var29 = var6 + var17;
/*      */             
/* 2121 */             if (var28 < var27)
/*      */             {
/* 2123 */               var28 = var27;
/*      */             }
/*      */             
/* 2126 */             if (var29 < var27)
/*      */             {
/* 2128 */               var29 = var27;
/*      */             }
/*      */             
/* 2131 */             float var30 = 1.0F;
/* 2132 */             int var31 = var27;
/*      */             
/* 2134 */             if (var27 < var16)
/*      */             {
/* 2136 */               var31 = var16;
/*      */             }
/*      */             
/* 2139 */             if (var28 != var29) {
/*      */               
/* 2141 */               this.random.setSeed((var21 * var21 * 3121 + var21 * 45238971 ^ var20 * var20 * 418711 + var20 * 13761));
/* 2142 */               float var32 = var26.func_180626_a(new BlockPos(var21, var28, var20));
/*      */ 
/*      */ 
/*      */               
/* 2146 */               if (var4.getWorldChunkManager().getTemperatureAtHeight(var32, var27) >= 0.15F) {
/*      */                 
/* 2148 */                 if (var18 != 0) {
/*      */                   
/* 2150 */                   if (var18 >= 0)
/*      */                   {
/* 2152 */                     var8.draw();
/*      */                   }
/*      */                   
/* 2155 */                   var18 = 0;
/* 2156 */                   this.mc.getTextureManager().bindTexture(locationRainPng);
/* 2157 */                   var9.startDrawingQuads();
/*      */                 } 
/*      */                 
/* 2160 */                 float var33 = ((this.rendererUpdateCount + var21 * var21 * 3121 + var21 * 45238971 + var20 * var20 * 418711 + var20 * 13761 & 0x1F) + partialTicks) / 32.0F * (3.0F + this.random.nextFloat());
/* 2161 */                 double var42 = (var21 + 0.5F) - var431.posX;
/* 2162 */                 double var36 = (var20 + 0.5F) - var431.posZ;
/* 2163 */                 float var43 = MathHelper.sqrt_double(var42 * var42 + var36 * var36) / var17;
/* 2164 */                 float var39 = 1.0F;
/* 2165 */                 var9.func_178963_b(var4.getCombinedLight(new BlockPos(var21, var31, var20), 0));
/* 2166 */                 var9.func_178960_a(var39, var39, var39, ((1.0F - var43 * var43) * 0.5F + 0.5F) * var421);
/* 2167 */                 var9.setTranslation(-var10 * 1.0D, -var12 * 1.0D, -var14 * 1.0D);
/* 2168 */                 var9.addVertexWithUV((var21 - var23) + 0.5D, var28, (var20 - var24) + 0.5D, (0.0F * var30), (var28 * var30 / 4.0F + var33 * var30));
/* 2169 */                 var9.addVertexWithUV((var21 + var23) + 0.5D, var28, (var20 + var24) + 0.5D, (1.0F * var30), (var28 * var30 / 4.0F + var33 * var30));
/* 2170 */                 var9.addVertexWithUV((var21 + var23) + 0.5D, var29, (var20 + var24) + 0.5D, (1.0F * var30), (var29 * var30 / 4.0F + var33 * var30));
/* 2171 */                 var9.addVertexWithUV((var21 - var23) + 0.5D, var29, (var20 - var24) + 0.5D, (0.0F * var30), (var29 * var30 / 4.0F + var33 * var30));
/* 2172 */                 var9.setTranslation(0.0D, 0.0D, 0.0D);
/*      */               }
/*      */               else {
/*      */                 
/* 2176 */                 if (var18 != 1) {
/*      */                   
/* 2178 */                   if (var18 >= 0)
/*      */                   {
/* 2180 */                     var8.draw();
/*      */                   }
/*      */                   
/* 2183 */                   var18 = 1;
/* 2184 */                   this.mc.getTextureManager().bindTexture(locationSnowPng);
/* 2185 */                   var9.startDrawingQuads();
/*      */                 } 
/*      */                 
/* 2188 */                 float var33 = ((this.rendererUpdateCount & 0x1FF) + partialTicks) / 512.0F;
/* 2189 */                 float var44 = this.random.nextFloat() + var19 * 0.01F * (float)this.random.nextGaussian();
/* 2190 */                 float var35 = this.random.nextFloat() + var19 * (float)this.random.nextGaussian() * 0.001F;
/* 2191 */                 double var36 = (var21 + 0.5F) - var431.posX;
/* 2192 */                 double var45 = (var20 + 0.5F) - var431.posZ;
/* 2193 */                 float var40 = MathHelper.sqrt_double(var36 * var36 + var45 * var45) / var17;
/* 2194 */                 float var41 = 1.0F;
/* 2195 */                 var9.func_178963_b((var4.getCombinedLight(new BlockPos(var21, var31, var20), 0) * 3 + 15728880) / 4);
/* 2196 */                 var9.func_178960_a(var41, var41, var41, ((1.0F - var40 * var40) * 0.3F + 0.5F) * var421);
/* 2197 */                 var9.setTranslation(-var10 * 1.0D, -var12 * 1.0D, -var14 * 1.0D);
/* 2198 */                 var9.addVertexWithUV((var21 - var23) + 0.5D, var28, (var20 - var24) + 0.5D, (0.0F * var30 + var44), (var28 * var30 / 4.0F + var33 * var30 + var35));
/* 2199 */                 var9.addVertexWithUV((var21 + var23) + 0.5D, var28, (var20 + var24) + 0.5D, (1.0F * var30 + var44), (var28 * var30 / 4.0F + var33 * var30 + var35));
/* 2200 */                 var9.addVertexWithUV((var21 + var23) + 0.5D, var29, (var20 + var24) + 0.5D, (1.0F * var30 + var44), (var29 * var30 / 4.0F + var33 * var30 + var35));
/* 2201 */                 var9.addVertexWithUV((var21 - var23) + 0.5D, var29, (var20 - var24) + 0.5D, (0.0F * var30 + var44), (var29 * var30 / 4.0F + var33 * var30 + var35));
/* 2202 */                 var9.setTranslation(0.0D, 0.0D, 0.0D);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 2209 */       if (var18 >= 0)
/*      */       {
/* 2211 */         var8.draw();
/*      */       }
/*      */       
/* 2214 */       GlStateManager.enableCull();
/* 2215 */       GlStateManager.disableBlend();
/* 2216 */       GlStateManager.alphaFunc(516, 0.1F);
/* 2217 */       func_175072_h();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setupOverlayRendering() {
/* 2226 */     ScaledResolution var1 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 2227 */     GlStateManager.clear(256);
/* 2228 */     GlStateManager.matrixMode(5889);
/* 2229 */     GlStateManager.loadIdentity();
/* 2230 */     GlStateManager.ortho(0.0D, var1.getScaledWidth_double(), var1.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
/* 2231 */     GlStateManager.matrixMode(5888);
/* 2232 */     GlStateManager.loadIdentity();
/* 2233 */     GlStateManager.translate(0.0F, 0.0F, -2000.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateFogColor(float partialTicks) {
/* 2241 */     WorldClient var2 = this.mc.theWorld;
/* 2242 */     Entity var3 = this.mc.func_175606_aa();
/* 2243 */     float var4 = 0.25F + 0.75F * this.mc.gameSettings.renderDistanceChunks / 32.0F;
/* 2244 */     var4 = 1.0F - (float)Math.pow(var4, 0.25D);
/* 2245 */     Vec3 var5 = var2.getSkyColor(this.mc.func_175606_aa(), partialTicks);
/* 2246 */     var5 = CustomColors.getWorldSkyColor(var5, var2, this.mc.func_175606_aa(), partialTicks);
/* 2247 */     float var6 = (float)var5.xCoord;
/* 2248 */     float var7 = (float)var5.yCoord;
/* 2249 */     float var8 = (float)var5.zCoord;
/* 2250 */     Vec3 var9 = var2.getFogColor(partialTicks);
/* 2251 */     var9 = CustomColors.getWorldFogColor(var9, var2, this.mc.func_175606_aa(), partialTicks);
/* 2252 */     this.field_175080_Q = (float)var9.xCoord;
/* 2253 */     this.field_175082_R = (float)var9.yCoord;
/* 2254 */     this.field_175081_S = (float)var9.zCoord;
/*      */ 
/*      */     
/* 2257 */     if (this.mc.gameSettings.renderDistanceChunks >= 4) {
/*      */       
/* 2259 */       double var19 = -1.0D;
/* 2260 */       Vec3 var20 = (MathHelper.sin(var2.getCelestialAngleRadians(partialTicks)) > 0.0F) ? new Vec3(var19, 0.0D, 0.0D) : new Vec3(1.0D, 0.0D, 0.0D);
/* 2261 */       float f = (float)var3.getLook(partialTicks).dotProduct(var20);
/*      */       
/* 2263 */       if (f < 0.0F)
/*      */       {
/* 2265 */         f = 0.0F;
/*      */       }
/*      */       
/* 2268 */       if (f > 0.0F) {
/*      */         
/* 2270 */         float[] var21 = var2.provider.calcSunriseSunsetColors(var2.getCelestialAngle(partialTicks), partialTicks);
/*      */         
/* 2272 */         if (var21 != null) {
/*      */           
/* 2274 */           f *= var21[3];
/* 2275 */           this.field_175080_Q = this.field_175080_Q * (1.0F - f) + var21[0] * f;
/* 2276 */           this.field_175082_R = this.field_175082_R * (1.0F - f) + var21[1] * f;
/* 2277 */           this.field_175081_S = this.field_175081_S * (1.0F - f) + var21[2] * f;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2282 */     this.field_175080_Q += (var6 - this.field_175080_Q) * var4;
/* 2283 */     this.field_175082_R += (var7 - this.field_175082_R) * var4;
/* 2284 */     this.field_175081_S += (var8 - this.field_175081_S) * var4;
/* 2285 */     float var191 = var2.getRainStrength(partialTicks);
/*      */ 
/*      */ 
/*      */     
/* 2289 */     if (var191 > 0.0F) {
/*      */       
/* 2291 */       float f1 = 1.0F - var191 * 0.5F;
/* 2292 */       float var201 = 1.0F - var191 * 0.4F;
/* 2293 */       this.field_175080_Q *= f1;
/* 2294 */       this.field_175082_R *= f1;
/* 2295 */       this.field_175081_S *= var201;
/*      */     } 
/*      */     
/* 2298 */     float var11 = var2.getWeightedThunderStrength(partialTicks);
/*      */     
/* 2300 */     if (var11 > 0.0F) {
/*      */       
/* 2302 */       float var201 = 1.0F - var11 * 0.5F;
/* 2303 */       this.field_175080_Q *= var201;
/* 2304 */       this.field_175082_R *= var201;
/* 2305 */       this.field_175081_S *= var201;
/*      */     } 
/*      */     
/* 2308 */     Block var211 = ActiveRenderInfo.func_180786_a((World)this.mc.theWorld, var3, partialTicks);
/*      */ 
/*      */     
/* 2311 */     if (this.cloudFog) {
/*      */       
/* 2313 */       Vec3 fogYFactor = var2.getCloudColour(partialTicks);
/* 2314 */       this.field_175080_Q = (float)fogYFactor.xCoord;
/* 2315 */       this.field_175082_R = (float)fogYFactor.yCoord;
/* 2316 */       this.field_175081_S = (float)fogYFactor.zCoord;
/*      */     }
/* 2318 */     else if (var211.getMaterial() == Material.water) {
/*      */       
/* 2320 */       float f = EnchantmentHelper.func_180319_a(var3) * 0.2F;
/*      */       
/* 2322 */       if (var3 instanceof EntityLivingBase && ((EntityLivingBase)var3).isPotionActive(Potion.waterBreathing))
/*      */       {
/* 2324 */         f = f * 0.3F + 0.6F;
/*      */       }
/*      */       
/* 2327 */       this.field_175080_Q = 0.02F + f;
/* 2328 */       this.field_175082_R = 0.02F + f;
/* 2329 */       this.field_175081_S = 0.2F + f;
/* 2330 */       Vec3 fogYFactor = CustomColors.getUnderwaterColor((IBlockAccess)this.mc.theWorld, (this.mc.func_175606_aa()).posX, (this.mc.func_175606_aa()).posY + 1.0D, (this.mc.func_175606_aa()).posZ);
/*      */       
/* 2332 */       if (fogYFactor != null)
/*      */       {
/* 2334 */         this.field_175080_Q = (float)fogYFactor.xCoord;
/* 2335 */         this.field_175082_R = (float)fogYFactor.yCoord;
/* 2336 */         this.field_175081_S = (float)fogYFactor.zCoord;
/*      */       }
/*      */     
/* 2339 */     } else if (var211.getMaterial() == Material.lava) {
/*      */       
/* 2341 */       this.field_175080_Q = 0.6F;
/* 2342 */       this.field_175082_R = 0.1F;
/* 2343 */       this.field_175081_S = 0.0F;
/*      */     } 
/*      */     
/* 2346 */     float var13 = this.fogColor2 + (this.fogColor1 - this.fogColor2) * partialTicks;
/* 2347 */     this.field_175080_Q *= var13;
/* 2348 */     this.field_175082_R *= var13;
/* 2349 */     this.field_175081_S *= var13;
/* 2350 */     double fogYFactor1 = var2.provider.getVoidFogYFactor();
/* 2351 */     double var23 = (var3.lastTickPosY + (var3.posY - var3.lastTickPosY) * partialTicks) * fogYFactor1;
/*      */     
/* 2353 */     if (var3 instanceof EntityLivingBase && ((EntityLivingBase)var3).isPotionActive(Potion.blindness)) {
/*      */       
/* 2355 */       int var24 = ((EntityLivingBase)var3).getActivePotionEffect(Potion.blindness).getDuration();
/*      */       
/* 2357 */       if (var24 < 20) {
/*      */         
/* 2359 */         var23 *= (1.0F - var24 / 20.0F);
/*      */       }
/*      */       else {
/*      */         
/* 2363 */         var23 = 0.0D;
/*      */       } 
/*      */     } 
/*      */     
/* 2367 */     if (var23 < 1.0D) {
/*      */       
/* 2369 */       if (var23 < 0.0D)
/*      */       {
/* 2371 */         var23 = 0.0D;
/*      */       }
/*      */       
/* 2374 */       var23 *= var23;
/* 2375 */       this.field_175080_Q = (float)(this.field_175080_Q * var23);
/* 2376 */       this.field_175082_R = (float)(this.field_175082_R * var23);
/* 2377 */       this.field_175081_S = (float)(this.field_175081_S * var23);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2382 */     if (this.bossColorModifier > 0.0F) {
/*      */       
/* 2384 */       float var241 = this.bossColorModifierPrev + (this.bossColorModifier - this.bossColorModifierPrev) * partialTicks;
/* 2385 */       this.field_175080_Q = this.field_175080_Q * (1.0F - var241) + this.field_175080_Q * 0.7F * var241;
/* 2386 */       this.field_175082_R = this.field_175082_R * (1.0F - var241) + this.field_175082_R * 0.6F * var241;
/* 2387 */       this.field_175081_S = this.field_175081_S * (1.0F - var241) + this.field_175081_S * 0.6F * var241;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2392 */     if (var3 instanceof EntityLivingBase && ((EntityLivingBase)var3).isPotionActive(Potion.nightVision)) {
/*      */       
/* 2394 */       float var241 = func_180438_a((EntityLivingBase)var3, partialTicks);
/* 2395 */       float var17 = 1.0F / this.field_175080_Q;
/*      */       
/* 2397 */       if (var17 > 1.0F / this.field_175082_R)
/*      */       {
/* 2399 */         var17 = 1.0F / this.field_175082_R;
/*      */       }
/*      */       
/* 2402 */       if (var17 > 1.0F / this.field_175081_S)
/*      */       {
/* 2404 */         var17 = 1.0F / this.field_175081_S;
/*      */       }
/*      */       
/* 2407 */       this.field_175080_Q = this.field_175080_Q * (1.0F - var241) + this.field_175080_Q * var17 * var241;
/* 2408 */       this.field_175082_R = this.field_175082_R * (1.0F - var241) + this.field_175082_R * var17 * var241;
/* 2409 */       this.field_175081_S = this.field_175081_S * (1.0F - var241) + this.field_175081_S * var17 * var241;
/*      */     } 
/*      */     
/* 2412 */     if (this.mc.gameSettings.anaglyph) {
/*      */       
/* 2414 */       float var241 = (this.field_175080_Q * 30.0F + this.field_175082_R * 59.0F + this.field_175081_S * 11.0F) / 100.0F;
/* 2415 */       float var17 = (this.field_175080_Q * 30.0F + this.field_175082_R * 70.0F) / 100.0F;
/* 2416 */       float event = (this.field_175080_Q * 30.0F + this.field_175081_S * 70.0F) / 100.0F;
/* 2417 */       this.field_175080_Q = var241;
/* 2418 */       this.field_175082_R = var17;
/* 2419 */       this.field_175081_S = event;
/*      */     } 
/*      */     
/* 2422 */     if (Reflector.EntityViewRenderEvent_FogColors_Constructor.exists()) {
/*      */       
/* 2424 */       Object event1 = Reflector.newInstance(Reflector.EntityViewRenderEvent_FogColors_Constructor, new Object[] { this, var3, var211, Float.valueOf(partialTicks), Float.valueOf(this.field_175080_Q), Float.valueOf(this.field_175082_R), Float.valueOf(this.field_175081_S) });
/* 2425 */       Reflector.postForgeBusEvent(event1);
/* 2426 */       this.field_175080_Q = Reflector.getFieldValueFloat(event1, Reflector.EntityViewRenderEvent_FogColors_red, this.field_175080_Q);
/* 2427 */       this.field_175082_R = Reflector.getFieldValueFloat(event1, Reflector.EntityViewRenderEvent_FogColors_green, this.field_175082_R);
/* 2428 */       this.field_175081_S = Reflector.getFieldValueFloat(event1, Reflector.EntityViewRenderEvent_FogColors_blue, this.field_175081_S);
/*      */     } 
/*      */     
/* 2431 */     Shaders.setClearColor(this.field_175080_Q, this.field_175082_R, this.field_175081_S, 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setupFog(int p_78468_1_, float partialTicks) {
/* 2440 */     Entity var3 = this.mc.func_175606_aa();
/* 2441 */     boolean var4 = false;
/* 2442 */     this.fogStandard = false;
/*      */     
/* 2444 */     if (var3 instanceof EntityPlayer)
/*      */     {
/* 2446 */       var4 = ((EntityPlayer)var3).capabilities.isCreativeMode;
/*      */     }
/*      */     
/* 2449 */     GL11.glFog(2918, setFogColorBuffer(this.field_175080_Q, this.field_175082_R, this.field_175081_S, 1.0F));
/* 2450 */     GL11.glNormal3f(0.0F, -1.0F, 0.0F);
/* 2451 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 2452 */     Block var5 = ActiveRenderInfo.func_180786_a((World)this.mc.theWorld, var3, partialTicks);
/* 2453 */     float forgeFogDensity = -1.0F;
/*      */     
/* 2455 */     if (Reflector.ForgeHooksClient_getFogDensity.exists())
/*      */     {
/* 2457 */       forgeFogDensity = Reflector.callFloat(Reflector.ForgeHooksClient_getFogDensity, new Object[] { this, var3, var5, Float.valueOf(partialTicks), Float.valueOf(0.1F) });
/*      */     }
/*      */     
/* 2460 */     if (forgeFogDensity >= 0.0F) {
/*      */       
/* 2462 */       GlStateManager.setFogDensity(forgeFogDensity);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 2468 */     else if (var3 instanceof EntityLivingBase && ((EntityLivingBase)var3).isPotionActive(Potion.blindness)) {
/*      */       
/* 2470 */       float var6 = 5.0F;
/* 2471 */       int var7 = ((EntityLivingBase)var3).getActivePotionEffect(Potion.blindness).getDuration();
/*      */       
/* 2473 */       if (var7 < 20)
/*      */       {
/* 2475 */         var6 = 5.0F + (this.farPlaneDistance - 5.0F) * (1.0F - var7 / 20.0F);
/*      */       }
/*      */       
/* 2478 */       if (Config.isShaders()) {
/*      */         
/* 2480 */         Shaders.setFog(9729);
/*      */       }
/*      */       else {
/*      */         
/* 2484 */         GlStateManager.setFog(9729);
/*      */       } 
/*      */       
/* 2487 */       if (p_78468_1_ == -1) {
/*      */         
/* 2489 */         GlStateManager.setFogStart(0.0F);
/* 2490 */         GlStateManager.setFogEnd(var6 * 0.8F);
/*      */       }
/*      */       else {
/*      */         
/* 2494 */         GlStateManager.setFogStart(var6 * 0.25F);
/* 2495 */         GlStateManager.setFogEnd(var6);
/*      */       } 
/*      */       
/* 2498 */       if ((GLContext.getCapabilities()).GL_NV_fog_distance && Config.isFogFancy())
/*      */       {
/* 2500 */         GL11.glFogi(34138, 34139);
/*      */       }
/*      */     }
/* 2503 */     else if (this.cloudFog) {
/*      */       
/* 2505 */       if (Config.isShaders()) {
/*      */         
/* 2507 */         Shaders.setFog(2048);
/*      */       }
/*      */       else {
/*      */         
/* 2511 */         GlStateManager.setFog(2048);
/*      */       } 
/*      */       
/* 2514 */       GlStateManager.setFogDensity(0.1F);
/*      */     }
/* 2516 */     else if (var5.getMaterial() == Material.water) {
/*      */       
/* 2518 */       if (Config.isShaders()) {
/*      */         
/* 2520 */         Shaders.setFog(2048);
/*      */       }
/*      */       else {
/*      */         
/* 2524 */         GlStateManager.setFog(2048);
/*      */       } 
/*      */       
/* 2527 */       if (var3 instanceof EntityLivingBase && ((EntityLivingBase)var3).isPotionActive(Potion.waterBreathing)) {
/*      */         
/* 2529 */         GlStateManager.setFogDensity(0.01F);
/*      */       }
/*      */       else {
/*      */         
/* 2533 */         GlStateManager.setFogDensity(0.1F - EnchantmentHelper.func_180319_a(var3) * 0.03F);
/*      */       } 
/*      */       
/* 2536 */       if (Config.isClearWater())
/*      */       {
/* 2538 */         GlStateManager.setFogDensity(0.02F);
/*      */       }
/*      */     }
/* 2541 */     else if (var5.getMaterial() == Material.lava) {
/*      */       
/* 2543 */       if (Config.isShaders()) {
/*      */         
/* 2545 */         Shaders.setFog(2048);
/*      */       }
/*      */       else {
/*      */         
/* 2549 */         GlStateManager.setFog(2048);
/*      */       } 
/*      */       
/* 2552 */       GlStateManager.setFogDensity(2.0F);
/*      */     }
/*      */     else {
/*      */       
/* 2556 */       float var6 = this.farPlaneDistance;
/* 2557 */       this.fogStandard = true;
/*      */       
/* 2559 */       if (Config.isShaders()) {
/*      */         
/* 2561 */         Shaders.setFog(9729);
/*      */       }
/*      */       else {
/*      */         
/* 2565 */         GlStateManager.setFog(9729);
/*      */       } 
/*      */       
/* 2568 */       if (p_78468_1_ == -1) {
/*      */         
/* 2570 */         GlStateManager.setFogStart(0.0F);
/* 2571 */         GlStateManager.setFogEnd(var6);
/*      */       }
/*      */       else {
/*      */         
/* 2575 */         GlStateManager.setFogStart(var6 * Config.getFogStart());
/* 2576 */         GlStateManager.setFogEnd(var6);
/*      */       } 
/*      */       
/* 2579 */       if ((GLContext.getCapabilities()).GL_NV_fog_distance) {
/*      */         
/* 2581 */         if (Config.isFogFancy())
/*      */         {
/* 2583 */           GL11.glFogi(34138, 34139);
/*      */         }
/*      */         
/* 2586 */         if (Config.isFogFast())
/*      */         {
/* 2588 */           GL11.glFogi(34138, 34140);
/*      */         }
/*      */       } 
/*      */       
/* 2592 */       if (this.mc.theWorld.provider.doesXZShowFog((int)var3.posX, (int)var3.posZ)) {
/*      */         
/* 2594 */         GlStateManager.setFogStart(var6 * 0.05F);
/* 2595 */         GlStateManager.setFogEnd(var6);
/*      */       } 
/*      */       
/* 2598 */       if (Reflector.ForgeHooksClient_onFogRender.exists())
/*      */       {
/* 2600 */         Reflector.callVoid(Reflector.ForgeHooksClient_onFogRender, new Object[] { this, var3, var5, Float.valueOf(partialTicks), Integer.valueOf(p_78468_1_), Float.valueOf(var6) });
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2605 */     GlStateManager.enableColorMaterial();
/* 2606 */     GlStateManager.enableFog();
/* 2607 */     GlStateManager.colorMaterial(1028, 4608);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private FloatBuffer setFogColorBuffer(float p_78469_1_, float p_78469_2_, float p_78469_3_, float p_78469_4_) {
/* 2615 */     if (Config.isShaders())
/*      */     {
/* 2617 */       Shaders.setFogColor(p_78469_1_, p_78469_2_, p_78469_3_);
/*      */     }
/*      */     
/* 2620 */     this.fogColorBuffer.clear();
/* 2621 */     this.fogColorBuffer.put(p_78469_1_).put(p_78469_2_).put(p_78469_3_).put(p_78469_4_);
/* 2622 */     this.fogColorBuffer.flip();
/* 2623 */     return this.fogColorBuffer;
/*      */   }
/*      */ 
/*      */   
/*      */   public MapItemRenderer getMapItemRenderer() {
/* 2628 */     return this.theMapItemRenderer;
/*      */   }
/*      */ 
/*      */   
/*      */   private void waitForServerThread() {
/* 2633 */     this.serverWaitTimeCurrent = 0;
/*      */     
/* 2635 */     if (Config.isSmoothWorld() && Config.isSingleProcessor()) {
/*      */       
/* 2637 */       if (this.mc.isIntegratedServerRunning()) {
/*      */         
/* 2639 */         IntegratedServer srv = this.mc.getIntegratedServer();
/*      */         
/* 2641 */         if (srv != null) {
/*      */           
/* 2643 */           boolean paused = this.mc.isGamePaused();
/*      */           
/* 2645 */           if (!paused && !(this.mc.currentScreen instanceof net.minecraft.client.gui.GuiDownloadTerrain)) {
/*      */             
/* 2647 */             if (this.serverWaitTime > 0) {
/*      */               
/* 2649 */               Lagometer.timerServer.start();
/* 2650 */               Config.sleep(this.serverWaitTime);
/* 2651 */               Lagometer.timerServer.end();
/* 2652 */               this.serverWaitTimeCurrent = this.serverWaitTime;
/*      */             } 
/*      */             
/* 2655 */             long timeNow = System.nanoTime() / 1000000L;
/*      */             
/* 2657 */             if (this.lastServerTime != 0L && this.lastServerTicks != 0) {
/*      */               
/* 2659 */               long timeDiff = timeNow - this.lastServerTime;
/*      */               
/* 2661 */               if (timeDiff < 0L) {
/*      */                 
/* 2663 */                 this.lastServerTime = timeNow;
/* 2664 */                 timeDiff = 0L;
/*      */               } 
/*      */               
/* 2667 */               if (timeDiff >= 50L)
/*      */               {
/* 2669 */                 this.lastServerTime = timeNow;
/* 2670 */                 int ticks = srv.getTickCounter();
/* 2671 */                 int tickDiff = ticks - this.lastServerTicks;
/*      */                 
/* 2673 */                 if (tickDiff < 0) {
/*      */                   
/* 2675 */                   this.lastServerTicks = ticks;
/* 2676 */                   tickDiff = 0;
/*      */                 } 
/*      */                 
/* 2679 */                 if (tickDiff < 1 && this.serverWaitTime < 100)
/*      */                 {
/* 2681 */                   this.serverWaitTime += 2;
/*      */                 }
/*      */                 
/* 2684 */                 if (tickDiff > 1 && this.serverWaitTime > 0)
/*      */                 {
/* 2686 */                   this.serverWaitTime--;
/*      */                 }
/*      */                 
/* 2689 */                 this.lastServerTicks = ticks;
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/* 2694 */               this.lastServerTime = timeNow;
/* 2695 */               this.lastServerTicks = srv.getTickCounter();
/* 2696 */               this.avgServerTickDiff = 1.0F;
/* 2697 */               this.avgServerTimeDiff = 50.0F;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/* 2702 */             if (this.mc.currentScreen instanceof net.minecraft.client.gui.GuiDownloadTerrain)
/*      */             {
/* 2704 */               Config.sleep(20L);
/*      */             }
/*      */             
/* 2707 */             this.lastServerTime = 0L;
/* 2708 */             this.lastServerTicks = 0;
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/*      */     } else {
/*      */       
/* 2715 */       this.lastServerTime = 0L;
/* 2716 */       this.lastServerTicks = 0;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void frameInit() {
/* 2722 */     if (!this.initialized) {
/*      */       
/* 2724 */       TextureUtils.registerResourceListener();
/*      */       
/* 2726 */       if (Config.getBitsOs() == 64 && Config.getBitsJre() == 32)
/*      */       {
/* 2728 */         Config.setNotify64BitJava(true);
/*      */       }
/*      */       
/* 2731 */       this.initialized = true;
/*      */     } 
/*      */     
/* 2734 */     Config.checkDisplayMode();
/* 2735 */     WorldClient world = this.mc.theWorld;
/*      */     
/* 2737 */     if (world != null) {
/*      */       
/* 2739 */       if (Config.getNewRelease() != null) {
/*      */         
/* 2741 */         String msg = "HD_U".replace("HD_U", "HD Ultra").replace("L", "Light");
/* 2742 */         String fullNewVer = String.valueOf(msg) + " " + Config.getNewRelease();
/* 2743 */         ChatComponentText msg1 = new ChatComponentText(I18n.format("of.message.newVersion", new Object[] { fullNewVer }));
/* 2744 */         this.mc.ingameGUI.getChatGUI().printChatMessage((IChatComponent)msg1);
/* 2745 */         Config.setNewRelease(null);
/*      */       } 
/*      */       
/* 2748 */       if (Config.isNotify64BitJava()) {
/*      */         
/* 2750 */         Config.setNotify64BitJava(false);
/* 2751 */         ChatComponentText msg2 = new ChatComponentText(I18n.format("of.message.java64Bit", new Object[0]));
/* 2752 */         this.mc.ingameGUI.getChatGUI().printChatMessage((IChatComponent)msg2);
/*      */       } 
/*      */     } 
/*      */     
/* 2756 */     if (this.mc.currentScreen instanceof MainMenu)
/*      */     {
/* 2758 */       updateMainMenu((MainMenu)this.mc.currentScreen);
/*      */     }
/*      */     
/* 2761 */     if (this.updatedWorld != world) {
/*      */       
/* 2763 */       RandomMobs.worldChanged(this.updatedWorld, (World)world);
/* 2764 */       Config.updateThreadPriorities();
/* 2765 */       this.lastServerTime = 0L;
/* 2766 */       this.lastServerTicks = 0;
/* 2767 */       this.updatedWorld = (World)world;
/*      */     } 
/*      */     
/* 2770 */     if (!setFxaaShader(Shaders.configAntialiasingLevel))
/*      */     {
/* 2772 */       Shaders.configAntialiasingLevel = 0;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void frameFinish() {
/* 2778 */     if (this.mc.theWorld != null) {
/*      */       
/* 2780 */       long now = System.currentTimeMillis();
/*      */       
/* 2782 */       if (now > this.lastErrorCheckTimeMs + 10000L) {
/*      */         
/* 2784 */         this.lastErrorCheckTimeMs = now;
/* 2785 */         int err = GL11.glGetError();
/*      */         
/* 2787 */         if (err != 0) {
/*      */           
/* 2789 */           String text = GLU.gluErrorString(err);
/* 2790 */           ChatComponentText msg = new ChatComponentText(I18n.format("of.message.openglError", new Object[] { Integer.valueOf(err), text }));
/* 2791 */           this.mc.ingameGUI.getChatGUI().printChatMessage((IChatComponent)msg);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateMainMenu(MainMenu currentScreen) {
/*      */     try {
/* 2801 */       String e = null;
/* 2802 */       Calendar calendar = Calendar.getInstance();
/* 2803 */       calendar.setTime(new Date());
/* 2804 */       int day = calendar.get(5);
/* 2805 */       int month = calendar.get(2) + 1;
/*      */       
/* 2807 */       if (day == 8 && month == 4)
/*      */       {
/* 2809 */         e = "Happy birthday, OptiFine!";
/*      */       }
/*      */       
/* 2812 */       if (day == 14 && month == 8)
/*      */       {
/* 2814 */         e = "Happy birthday, sp614x!";
/*      */       }
/*      */       
/* 2817 */       if (e == null) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/* 2822 */       Field[] fs = MainMenu.class.getDeclaredFields();
/*      */       
/* 2824 */       for (int i = 0; i < fs.length; i++) {
/*      */         
/* 2826 */         if (fs[i].getType() == String.class) {
/*      */           
/* 2828 */           fs[i].setAccessible(true);
/* 2829 */           fs[i].set(currentScreen, e);
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 2834 */     } catch (Throwable throwable) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean setFxaaShader(int fxaaLevel) {
/* 2842 */     if (!OpenGlHelper.isFramebufferEnabled())
/*      */     {
/* 2844 */       return false;
/*      */     }
/* 2846 */     if (this.theShaderGroup != null && this.theShaderGroup != this.fxaaShaders[2] && this.theShaderGroup != this.fxaaShaders[4])
/*      */     {
/* 2848 */       return true;
/*      */     }
/* 2850 */     if (fxaaLevel != 2 && fxaaLevel != 4) {
/*      */       
/* 2852 */       if (this.theShaderGroup == null)
/*      */       {
/* 2854 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 2858 */       this.theShaderGroup.deleteShaderGroup();
/* 2859 */       this.theShaderGroup = null;
/* 2860 */       return true;
/*      */     } 
/*      */     
/* 2863 */     if (this.theShaderGroup != null && this.theShaderGroup == this.fxaaShaders[fxaaLevel])
/*      */     {
/* 2865 */       return true;
/*      */     }
/* 2867 */     if (this.mc.theWorld == null)
/*      */     {
/* 2869 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 2873 */     func_175069_a(new ResourceLocation("shaders/post/fxaa_of_" + fxaaLevel + "x.json"));
/* 2874 */     this.fxaaShaders[fxaaLevel] = this.theShaderGroup;
/* 2875 */     return this.field_175083_ad;
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\EntityRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */