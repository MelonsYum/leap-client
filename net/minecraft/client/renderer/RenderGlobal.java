/*      */ package net.minecraft.client.renderer;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Maps;
/*      */ import com.google.common.collect.Sets;
/*      */ import com.google.gson.JsonSyntaxException;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayDeque;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Deque;
/*      */ import java.util.EnumSet;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.Callable;
/*      */ import javax.vecmath.Matrix4f;
/*      */ import javax.vecmath.Tuple4f;
/*      */ import javax.vecmath.Vector3d;
/*      */ import javax.vecmath.Vector3f;
/*      */ import javax.vecmath.Vector4f;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.audio.ISound;
/*      */ import net.minecraft.client.audio.PositionedSoundRecord;
/*      */ import net.minecraft.client.entity.EntityPlayerSP;
/*      */ import net.minecraft.client.gui.FontRenderer;
/*      */ import net.minecraft.client.multiplayer.WorldClient;
/*      */ import net.minecraft.client.particle.EntityFX;
/*      */ import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher;
/*      */ import net.minecraft.client.renderer.chunk.CompiledChunk;
/*      */ import net.minecraft.client.renderer.chunk.IRenderChunkFactory;
/*      */ import net.minecraft.client.renderer.chunk.ListChunkFactory;
/*      */ import net.minecraft.client.renderer.chunk.RenderChunk;
/*      */ import net.minecraft.client.renderer.chunk.VboChunkFactory;
/*      */ import net.minecraft.client.renderer.chunk.VisGraph;
/*      */ import net.minecraft.client.renderer.culling.ClippingHelper;
/*      */ import net.minecraft.client.renderer.culling.ClippingHelperImpl;
/*      */ import net.minecraft.client.renderer.culling.Frustrum;
/*      */ import net.minecraft.client.renderer.culling.ICamera;
/*      */ import net.minecraft.client.renderer.entity.RenderManager;
/*      */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*      */ import net.minecraft.client.renderer.texture.TextureManager;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*      */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*      */ import net.minecraft.client.renderer.vertex.VertexBuffer;
/*      */ import net.minecraft.client.renderer.vertex.VertexFormat;
/*      */ import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*      */ import net.minecraft.client.resources.IResourceManager;
/*      */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*      */ import net.minecraft.client.shader.Framebuffer;
/*      */ import net.minecraft.client.shader.ShaderGroup;
/*      */ import net.minecraft.client.shader.ShaderLinkHelper;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.item.EntityItemFrame;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemDye;
/*      */ import net.minecraft.item.ItemRecord;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.tileentity.TileEntityChest;
/*      */ import net.minecraft.tileentity.TileEntitySign;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.EnumWorldBlockLayer;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.IWorldAccess;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldProvider;
/*      */ import net.minecraft.world.border.WorldBorder;
/*      */ import net.minecraft.world.chunk.Chunk;
/*      */ import optifine.ChunkUtils;
/*      */ import optifine.CloudRenderer;
/*      */ import optifine.Config;
/*      */ import optifine.CustomColors;
/*      */ import optifine.CustomSky;
/*      */ import optifine.DynamicLights;
/*      */ import optifine.Lagometer;
/*      */ import optifine.RandomMobs;
/*      */ import optifine.Reflector;
/*      */ import optifine.RenderInfoLazy;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ import shadersmod.client.Shaders;
/*      */ import shadersmod.client.ShadersRender;
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RenderGlobal
/*      */   implements IWorldAccess, IResourceManagerReloadListener
/*      */ {
/*  114 */   private static final Logger logger = LogManager.getLogger();
/*  115 */   private static final ResourceLocation locationMoonPhasesPng = new ResourceLocation("textures/environment/moon_phases.png");
/*  116 */   private static final ResourceLocation locationSunPng = new ResourceLocation("textures/environment/sun.png");
/*  117 */   private static final ResourceLocation locationCloudsPng = new ResourceLocation("textures/environment/clouds.png");
/*  118 */   private static final ResourceLocation locationEndSkyPng = new ResourceLocation("textures/environment/end_sky.png");
/*  119 */   private static final ResourceLocation field_175006_g = new ResourceLocation("textures/misc/forcefield.png");
/*      */   
/*      */   public final Minecraft mc;
/*      */   
/*      */   private final TextureManager renderEngine;
/*      */   
/*      */   private final RenderManager field_175010_j;
/*      */   
/*      */   private WorldClient theWorld;
/*  128 */   private Set field_175009_l = Sets.newLinkedHashSet();
/*      */ 
/*      */   
/*  131 */   private List glRenderLists = Lists.newArrayListWithCapacity(69696);
/*      */   
/*      */   private ViewFrustum field_175008_n;
/*      */   
/*  135 */   private int starGLCallList = -1;
/*      */ 
/*      */   
/*  138 */   private int glSkyList = -1;
/*      */ 
/*      */   
/*  141 */   private int glSkyList2 = -1;
/*      */ 
/*      */   
/*      */   private VertexFormat field_175014_r;
/*      */ 
/*      */   
/*      */   private VertexBuffer field_175013_s;
/*      */ 
/*      */   
/*      */   private VertexBuffer field_175012_t;
/*      */   
/*      */   private VertexBuffer field_175011_u;
/*      */   
/*      */   private int cloudTickCounter;
/*      */   
/*  156 */   public final Map damagedBlocks = Maps.newHashMap();
/*      */ 
/*      */   
/*  159 */   private final Map mapSoundPositions = Maps.newHashMap();
/*  160 */   private final TextureAtlasSprite[] destroyBlockIcons = new TextureAtlasSprite[10];
/*      */   private Framebuffer field_175015_z;
/*      */   private ShaderGroup field_174991_A;
/*  163 */   private double field_174992_B = Double.MIN_VALUE;
/*  164 */   private double field_174993_C = Double.MIN_VALUE;
/*  165 */   private double field_174987_D = Double.MIN_VALUE;
/*  166 */   private int field_174988_E = Integer.MIN_VALUE;
/*  167 */   private int field_174989_F = Integer.MIN_VALUE;
/*  168 */   private int field_174990_G = Integer.MIN_VALUE;
/*  169 */   private double field_174997_H = Double.MIN_VALUE;
/*  170 */   private double field_174998_I = Double.MIN_VALUE;
/*  171 */   private double field_174999_J = Double.MIN_VALUE;
/*  172 */   private double field_175000_K = Double.MIN_VALUE;
/*  173 */   private double field_174994_L = Double.MIN_VALUE;
/*  174 */   private final ChunkRenderDispatcher field_174995_M = new ChunkRenderDispatcher();
/*      */   private ChunkRenderContainer field_174996_N;
/*  176 */   private int renderDistanceChunks = -1;
/*      */ 
/*      */   
/*  179 */   private int renderEntitiesStartupCounter = 2;
/*      */   
/*      */   private int countEntitiesTotal;
/*      */   
/*      */   private int countEntitiesRendered;
/*      */   
/*      */   private int countEntitiesHidden;
/*      */   
/*      */   private boolean field_175002_T = false;
/*      */   
/*      */   private ClippingHelper field_175001_U;
/*      */   
/*  191 */   private final Vector4f[] field_175004_V = new Vector4f[8];
/*  192 */   private final Vector3d field_175003_W = new Vector3d();
/*      */   private boolean field_175005_X = false;
/*      */   IRenderChunkFactory field_175007_a;
/*      */   private double prevRenderSortX;
/*      */   private double prevRenderSortY;
/*      */   private double prevRenderSortZ;
/*      */   public boolean displayListEntitiesDirty = true;
/*      */   private static final String __OBFID = "CL_00000954";
/*      */   private CloudRenderer cloudRenderer;
/*      */   public Entity renderedEntity;
/*  202 */   public Set chunksToResortTransparency = new LinkedHashSet();
/*  203 */   public Set chunksToUpdateForced = new LinkedHashSet();
/*  204 */   private Deque visibilityDeque = new ArrayDeque();
/*  205 */   private List renderInfosEntities = new ArrayList(1024);
/*  206 */   private List renderInfosTileEntities = new ArrayList(1024);
/*  207 */   private List renderInfosNormal = new ArrayList(1024);
/*  208 */   private List renderInfosEntitiesNormal = new ArrayList(1024);
/*  209 */   private List renderInfosTileEntitiesNormal = new ArrayList(1024);
/*  210 */   private List renderInfosShadow = new ArrayList(1024);
/*  211 */   private List renderInfosEntitiesShadow = new ArrayList(1024);
/*  212 */   private List renderInfosTileEntitiesShadow = new ArrayList(1024);
/*  213 */   private int renderDistance = 0;
/*  214 */   private int renderDistanceSq = 0;
/*  215 */   private static final Set SET_ALL_FACINGS = Collections.unmodifiableSet(new HashSet(Arrays.asList((Object[])EnumFacing.VALUES)));
/*      */   
/*      */   private int countTileEntitiesRendered;
/*      */   
/*      */   public RenderGlobal(Minecraft mcIn) {
/*  220 */     this.cloudRenderer = new CloudRenderer(mcIn);
/*  221 */     this.mc = mcIn;
/*  222 */     this.field_175010_j = mcIn.getRenderManager();
/*  223 */     this.renderEngine = mcIn.getTextureManager();
/*  224 */     this.renderEngine.bindTexture(field_175006_g);
/*  225 */     GL11.glTexParameteri(3553, 10242, 10497);
/*  226 */     GL11.glTexParameteri(3553, 10243, 10497);
/*  227 */     GlStateManager.func_179144_i(0);
/*  228 */     func_174971_n();
/*  229 */     this.field_175005_X = OpenGlHelper.func_176075_f();
/*      */     
/*  231 */     if (this.field_175005_X) {
/*      */       
/*  233 */       this.field_174996_N = new VboRenderList();
/*  234 */       this.field_175007_a = (IRenderChunkFactory)new VboChunkFactory();
/*      */     }
/*      */     else {
/*      */       
/*  238 */       this.field_174996_N = new RenderList();
/*  239 */       this.field_175007_a = (IRenderChunkFactory)new ListChunkFactory();
/*      */     } 
/*      */     
/*  242 */     this.field_175014_r = new VertexFormat();
/*  243 */     this.field_175014_r.func_177349_a(new VertexFormatElement(0, VertexFormatElement.EnumType.FLOAT, VertexFormatElement.EnumUseage.POSITION, 3));
/*  244 */     func_174963_q();
/*  245 */     func_174980_p();
/*  246 */     func_174964_o();
/*      */   }
/*      */ 
/*      */   
/*      */   public void onResourceManagerReload(IResourceManager resourceManager) {
/*  251 */     func_174971_n();
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_174971_n() {
/*  256 */     TextureMap var1 = this.mc.getTextureMapBlocks();
/*      */     
/*  258 */     for (int var2 = 0; var2 < this.destroyBlockIcons.length; var2++)
/*      */     {
/*  260 */       this.destroyBlockIcons[var2] = var1.getAtlasSprite("minecraft:blocks/destroy_stage_" + var2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174966_b() {
/*  266 */     if (OpenGlHelper.shadersSupported) {
/*      */       
/*  268 */       if (ShaderLinkHelper.getStaticShaderLinkHelper() == null)
/*      */       {
/*  270 */         ShaderLinkHelper.setNewStaticShaderLinkHelper();
/*      */       }
/*      */       
/*  273 */       ResourceLocation var1 = new ResourceLocation("shaders/post/entity_outline.json");
/*      */ 
/*      */       
/*      */       try {
/*  277 */         this.field_174991_A = new ShaderGroup(this.mc.getTextureManager(), this.mc.getResourceManager(), this.mc.getFramebuffer(), var1);
/*  278 */         this.field_174991_A.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
/*  279 */         this.field_175015_z = this.field_174991_A.func_177066_a("final");
/*      */       }
/*  281 */       catch (IOException var3) {
/*      */         
/*  283 */         logger.warn("Failed to load shader: " + var1, var3);
/*  284 */         this.field_174991_A = null;
/*  285 */         this.field_175015_z = null;
/*      */       }
/*  287 */       catch (JsonSyntaxException var4) {
/*      */         
/*  289 */         logger.warn("Failed to load shader: " + var1, (Throwable)var4);
/*  290 */         this.field_174991_A = null;
/*  291 */         this.field_175015_z = null;
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  296 */       this.field_174991_A = null;
/*  297 */       this.field_175015_z = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174975_c() {
/*  303 */     if (func_174985_d()) {
/*      */       
/*  305 */       GlStateManager.enableBlend();
/*  306 */       GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
/*  307 */       this.field_175015_z.func_178038_a(this.mc.displayWidth, this.mc.displayHeight, false);
/*  308 */       GlStateManager.disableBlend();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean func_174985_d() {
/*  314 */     return (!Config.isFastRender() && !Config.isShaders() && !Config.isAntialiasing()) ? ((this.field_175015_z != null && this.field_174991_A != null && this.mc.thePlayer != null && this.mc.thePlayer.func_175149_v() && this.mc.gameSettings.field_178883_an.getIsKeyPressed())) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_174964_o() {
/*  319 */     Tessellator var1 = Tessellator.getInstance();
/*  320 */     WorldRenderer var2 = var1.getWorldRenderer();
/*      */     
/*  322 */     if (this.field_175011_u != null)
/*      */     {
/*  324 */       this.field_175011_u.func_177362_c();
/*      */     }
/*      */     
/*  327 */     if (this.glSkyList2 >= 0) {
/*      */       
/*  329 */       GLAllocation.deleteDisplayLists(this.glSkyList2);
/*  330 */       this.glSkyList2 = -1;
/*      */     } 
/*      */     
/*  333 */     if (this.field_175005_X) {
/*      */       
/*  335 */       this.field_175011_u = new VertexBuffer(this.field_175014_r);
/*  336 */       func_174968_a(var2, -16.0F, true);
/*  337 */       var2.draw();
/*  338 */       var2.reset();
/*  339 */       this.field_175011_u.func_177360_a(var2.func_178966_f(), var2.func_178976_e());
/*      */     }
/*      */     else {
/*      */       
/*  343 */       this.glSkyList2 = GLAllocation.generateDisplayLists(1);
/*  344 */       GL11.glNewList(this.glSkyList2, 4864);
/*  345 */       func_174968_a(var2, -16.0F, true);
/*  346 */       var1.draw();
/*  347 */       GL11.glEndList();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_174980_p() {
/*  353 */     Tessellator var1 = Tessellator.getInstance();
/*  354 */     WorldRenderer var2 = var1.getWorldRenderer();
/*      */     
/*  356 */     if (this.field_175012_t != null)
/*      */     {
/*  358 */       this.field_175012_t.func_177362_c();
/*      */     }
/*      */     
/*  361 */     if (this.glSkyList >= 0) {
/*      */       
/*  363 */       GLAllocation.deleteDisplayLists(this.glSkyList);
/*  364 */       this.glSkyList = -1;
/*      */     } 
/*      */     
/*  367 */     if (this.field_175005_X) {
/*      */       
/*  369 */       this.field_175012_t = new VertexBuffer(this.field_175014_r);
/*  370 */       func_174968_a(var2, 16.0F, false);
/*  371 */       var2.draw();
/*  372 */       var2.reset();
/*  373 */       this.field_175012_t.func_177360_a(var2.func_178966_f(), var2.func_178976_e());
/*      */     }
/*      */     else {
/*      */       
/*  377 */       this.glSkyList = GLAllocation.generateDisplayLists(1);
/*  378 */       GL11.glNewList(this.glSkyList, 4864);
/*  379 */       func_174968_a(var2, 16.0F, false);
/*  380 */       var1.draw();
/*  381 */       GL11.glEndList();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_174968_a(WorldRenderer worldRendererIn, float p_174968_2_, boolean p_174968_3_) {
/*  387 */     boolean var4 = true;
/*  388 */     boolean var5 = true;
/*  389 */     worldRendererIn.startDrawingQuads();
/*      */     
/*  391 */     for (int var6 = -384; var6 <= 384; var6 += 64) {
/*      */       
/*  393 */       for (int var7 = -384; var7 <= 384; var7 += 64) {
/*      */         
/*  395 */         float var8 = var6;
/*  396 */         float var9 = (var6 + 64);
/*      */         
/*  398 */         if (p_174968_3_) {
/*      */           
/*  400 */           var9 = var6;
/*  401 */           var8 = (var6 + 64);
/*      */         } 
/*      */         
/*  404 */         worldRendererIn.addVertex(var8, p_174968_2_, var7);
/*  405 */         worldRendererIn.addVertex(var9, p_174968_2_, var7);
/*  406 */         worldRendererIn.addVertex(var9, p_174968_2_, (var7 + 64));
/*  407 */         worldRendererIn.addVertex(var8, p_174968_2_, (var7 + 64));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_174963_q() {
/*  414 */     Tessellator var1 = Tessellator.getInstance();
/*  415 */     WorldRenderer var2 = var1.getWorldRenderer();
/*      */     
/*  417 */     if (this.field_175013_s != null)
/*      */     {
/*  419 */       this.field_175013_s.func_177362_c();
/*      */     }
/*      */     
/*  422 */     if (this.starGLCallList >= 0) {
/*      */       
/*  424 */       GLAllocation.deleteDisplayLists(this.starGLCallList);
/*  425 */       this.starGLCallList = -1;
/*      */     } 
/*      */     
/*  428 */     if (this.field_175005_X) {
/*      */       
/*  430 */       this.field_175013_s = new VertexBuffer(this.field_175014_r);
/*  431 */       func_180444_a(var2);
/*  432 */       var2.draw();
/*  433 */       var2.reset();
/*  434 */       this.field_175013_s.func_177360_a(var2.func_178966_f(), var2.func_178976_e());
/*      */     }
/*      */     else {
/*      */       
/*  438 */       this.starGLCallList = GLAllocation.generateDisplayLists(1);
/*  439 */       GlStateManager.pushMatrix();
/*  440 */       GL11.glNewList(this.starGLCallList, 4864);
/*  441 */       func_180444_a(var2);
/*  442 */       var1.draw();
/*  443 */       GL11.glEndList();
/*  444 */       GlStateManager.popMatrix();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180444_a(WorldRenderer worldRendererIn) {
/*  450 */     Random var2 = new Random(10842L);
/*  451 */     worldRendererIn.startDrawingQuads();
/*      */     
/*  453 */     for (int var3 = 0; var3 < 1500; var3++) {
/*      */       
/*  455 */       double var4 = (var2.nextFloat() * 2.0F - 1.0F);
/*  456 */       double var6 = (var2.nextFloat() * 2.0F - 1.0F);
/*  457 */       double var8 = (var2.nextFloat() * 2.0F - 1.0F);
/*  458 */       double var10 = (0.15F + var2.nextFloat() * 0.1F);
/*  459 */       double var12 = var4 * var4 + var6 * var6 + var8 * var8;
/*      */       
/*  461 */       if (var12 < 1.0D && var12 > 0.01D) {
/*      */         
/*  463 */         var12 = 1.0D / Math.sqrt(var12);
/*  464 */         var4 *= var12;
/*  465 */         var6 *= var12;
/*  466 */         var8 *= var12;
/*  467 */         double var14 = var4 * 100.0D;
/*  468 */         double var16 = var6 * 100.0D;
/*  469 */         double var18 = var8 * 100.0D;
/*  470 */         double var20 = Math.atan2(var4, var8);
/*  471 */         double var22 = Math.sin(var20);
/*  472 */         double var24 = Math.cos(var20);
/*  473 */         double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
/*  474 */         double var28 = Math.sin(var26);
/*  475 */         double var30 = Math.cos(var26);
/*  476 */         double var32 = var2.nextDouble() * Math.PI * 2.0D;
/*  477 */         double var34 = Math.sin(var32);
/*  478 */         double var36 = Math.cos(var32);
/*      */         
/*  480 */         for (int var38 = 0; var38 < 4; var38++) {
/*      */           
/*  482 */           double var39 = 0.0D;
/*  483 */           double var41 = ((var38 & 0x2) - 1) * var10;
/*  484 */           double var43 = ((var38 + 1 & 0x2) - 1) * var10;
/*  485 */           double var45 = 0.0D;
/*  486 */           double var47 = var41 * var36 - var43 * var34;
/*  487 */           double var49 = var43 * var36 + var41 * var34;
/*  488 */           double var53 = var47 * var28 + 0.0D * var30;
/*  489 */           double var55 = 0.0D * var28 - var47 * var30;
/*  490 */           double var57 = var55 * var22 - var49 * var24;
/*  491 */           double var61 = var49 * var22 + var55 * var24;
/*  492 */           worldRendererIn.addVertex(var14 + var57, var16 + var53, var18 + var61);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWorldAndLoadRenderers(WorldClient worldClientIn) {
/*  503 */     if (this.theWorld != null)
/*      */     {
/*  505 */       this.theWorld.removeWorldAccess(this);
/*      */     }
/*      */     
/*  508 */     this.field_174992_B = Double.MIN_VALUE;
/*  509 */     this.field_174993_C = Double.MIN_VALUE;
/*  510 */     this.field_174987_D = Double.MIN_VALUE;
/*  511 */     this.field_174988_E = Integer.MIN_VALUE;
/*  512 */     this.field_174989_F = Integer.MIN_VALUE;
/*  513 */     this.field_174990_G = Integer.MIN_VALUE;
/*  514 */     this.field_175010_j.set((World)worldClientIn);
/*  515 */     this.theWorld = worldClientIn;
/*      */     
/*  517 */     if (Config.isDynamicLights())
/*      */     {
/*  519 */       DynamicLights.clear();
/*      */     }
/*      */     
/*  522 */     if (worldClientIn != null) {
/*      */       
/*  524 */       worldClientIn.addWorldAccess(this);
/*  525 */       loadRenderers();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadRenderers() {
/*  534 */     if (this.theWorld != null) {
/*      */       
/*  536 */       this.displayListEntitiesDirty = true;
/*  537 */       Blocks.leaves.setGraphicsLevel(Config.isTreesFancy());
/*  538 */       Blocks.leaves2.setGraphicsLevel(Config.isTreesFancy());
/*  539 */       BlockModelRenderer.updateAoLightValue();
/*      */       
/*  541 */       if (Config.isDynamicLights())
/*      */       {
/*  543 */         DynamicLights.clear();
/*      */       }
/*      */       
/*  546 */       this.renderDistanceChunks = this.mc.gameSettings.renderDistanceChunks;
/*  547 */       this.renderDistance = this.renderDistanceChunks * 16;
/*  548 */       this.renderDistanceSq = this.renderDistance * this.renderDistance;
/*  549 */       boolean var1 = this.field_175005_X;
/*  550 */       this.field_175005_X = OpenGlHelper.func_176075_f();
/*      */       
/*  552 */       if (var1 && !this.field_175005_X) {
/*      */         
/*  554 */         this.field_174996_N = new RenderList();
/*  555 */         this.field_175007_a = (IRenderChunkFactory)new ListChunkFactory();
/*      */       }
/*  557 */       else if (!var1 && this.field_175005_X) {
/*      */         
/*  559 */         this.field_174996_N = new VboRenderList();
/*  560 */         this.field_175007_a = (IRenderChunkFactory)new VboChunkFactory();
/*      */       } 
/*      */       
/*  563 */       if (var1 != this.field_175005_X) {
/*      */         
/*  565 */         func_174963_q();
/*  566 */         func_174980_p();
/*  567 */         func_174964_o();
/*      */       } 
/*      */       
/*  570 */       if (this.field_175008_n != null)
/*      */       {
/*  572 */         this.field_175008_n.func_178160_a();
/*      */       }
/*      */       
/*  575 */       func_174986_e();
/*  576 */       this.field_175008_n = new ViewFrustum((World)this.theWorld, this.mc.gameSettings.renderDistanceChunks, this, this.field_175007_a);
/*      */       
/*  578 */       if (this.theWorld != null) {
/*      */         
/*  580 */         Entity var2 = this.mc.func_175606_aa();
/*      */         
/*  582 */         if (var2 != null)
/*      */         {
/*  584 */           this.field_175008_n.func_178163_a(var2.posX, var2.posZ);
/*      */         }
/*      */       } 
/*      */       
/*  588 */       this.renderEntitiesStartupCounter = 2;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_174986_e() {
/*  594 */     this.field_175009_l.clear();
/*  595 */     this.field_174995_M.func_178514_b();
/*      */   }
/*      */ 
/*      */   
/*      */   public void checkOcclusionQueryResult(int p_72720_1_, int p_72720_2_) {
/*  600 */     if (OpenGlHelper.shadersSupported && this.field_174991_A != null)
/*      */     {
/*  602 */       this.field_174991_A.createBindFramebuffers(p_72720_1_, p_72720_2_);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180446_a(Entity p_180446_1_, ICamera p_180446_2_, float partialTicks) {
/*  608 */     int pass = 0;
/*      */     
/*  610 */     if (Reflector.MinecraftForgeClient_getRenderPass.exists())
/*      */     {
/*  612 */       pass = Reflector.callInt(Reflector.MinecraftForgeClient_getRenderPass, new Object[0]);
/*      */     }
/*      */     
/*  615 */     if (this.renderEntitiesStartupCounter > 0) {
/*      */       
/*  617 */       if (pass > 0) {
/*      */         return;
/*      */       }
/*      */ 
/*      */       
/*  622 */       this.renderEntitiesStartupCounter--;
/*      */     }
/*      */     else {
/*      */       
/*  626 */       double var4 = p_180446_1_.prevPosX + (p_180446_1_.posX - p_180446_1_.prevPosX) * partialTicks;
/*  627 */       double var6 = p_180446_1_.prevPosY + (p_180446_1_.posY - p_180446_1_.prevPosY) * partialTicks;
/*  628 */       double var8 = p_180446_1_.prevPosZ + (p_180446_1_.posZ - p_180446_1_.prevPosZ) * partialTicks;
/*  629 */       this.theWorld.theProfiler.startSection("prepare");
/*  630 */       TileEntityRendererDispatcher.instance.func_178470_a((World)this.theWorld, this.mc.getTextureManager(), this.mc.fontRendererObj, this.mc.func_175606_aa(), partialTicks);
/*  631 */       this.field_175010_j.func_180597_a((World)this.theWorld, this.mc.fontRendererObj, this.mc.func_175606_aa(), this.mc.pointedEntity, this.mc.gameSettings, partialTicks);
/*      */       
/*  633 */       if (pass == 0) {
/*      */         
/*  635 */         this.countEntitiesTotal = 0;
/*  636 */         this.countEntitiesRendered = 0;
/*  637 */         this.countEntitiesHidden = 0;
/*  638 */         this.countTileEntitiesRendered = 0;
/*      */       } 
/*      */       
/*  641 */       Entity var10 = this.mc.func_175606_aa();
/*  642 */       double var11 = var10.lastTickPosX + (var10.posX - var10.lastTickPosX) * partialTicks;
/*  643 */       double var13 = var10.lastTickPosY + (var10.posY - var10.lastTickPosY) * partialTicks;
/*  644 */       double var15 = var10.lastTickPosZ + (var10.posZ - var10.lastTickPosZ) * partialTicks;
/*  645 */       TileEntityRendererDispatcher.staticPlayerX = var11;
/*  646 */       TileEntityRendererDispatcher.staticPlayerY = var13;
/*  647 */       TileEntityRendererDispatcher.staticPlayerZ = var15;
/*  648 */       this.field_175010_j.func_178628_a(var11, var13, var15);
/*  649 */       this.mc.entityRenderer.func_180436_i();
/*  650 */       this.theWorld.theProfiler.endStartSection("global");
/*  651 */       List<Entity> var17 = this.theWorld.getLoadedEntityList();
/*      */       
/*  653 */       if (pass == 0)
/*      */       {
/*  655 */         this.countEntitiesTotal = var17.size();
/*      */       }
/*      */       
/*  658 */       if (Config.isFogOff() && this.mc.entityRenderer.fogStandard)
/*      */       {
/*  660 */         GlStateManager.disableFog();
/*      */       }
/*      */       
/*  663 */       boolean forgeEntityPass = Reflector.ForgeEntity_shouldRenderInPass.exists();
/*  664 */       boolean forgeTileEntityPass = Reflector.ForgeTileEntity_shouldRenderInPass.exists();
/*      */       
/*      */       int var18;
/*      */       
/*  668 */       for (var18 = 0; var18 < this.theWorld.weatherEffects.size(); var18++) {
/*      */         
/*  670 */         Entity var19 = this.theWorld.weatherEffects.get(var18);
/*      */         
/*  672 */         if (!forgeEntityPass || Reflector.callBoolean(var19, Reflector.ForgeEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) {
/*      */           
/*  674 */           this.countEntitiesRendered++;
/*      */           
/*  676 */           if (var19.isInRangeToRender3d(var4, var6, var8))
/*      */           {
/*  678 */             this.field_175010_j.renderEntitySimple(var19, partialTicks);
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  685 */       if (func_174985_d()) {
/*      */         
/*  687 */         GlStateManager.depthFunc(519);
/*  688 */         GlStateManager.disableFog();
/*  689 */         this.field_175015_z.framebufferClear();
/*  690 */         this.field_175015_z.bindFramebuffer(false);
/*  691 */         this.theWorld.theProfiler.endStartSection("entityOutlines");
/*  692 */         RenderHelper.disableStandardItemLighting();
/*  693 */         this.field_175010_j.func_178632_c(true);
/*      */         
/*  695 */         for (var18 = 0; var18 < var17.size(); var18++) {
/*      */           
/*  697 */           Entity var19 = var17.get(var18);
/*      */           
/*  699 */           if (!forgeEntityPass || Reflector.callBoolean(var19, Reflector.ForgeEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) {
/*      */             
/*  701 */             boolean bool1 = (this.mc.func_175606_aa() instanceof EntityLivingBase && ((EntityLivingBase)this.mc.func_175606_aa()).isPlayerSleeping());
/*  702 */             boolean var25 = (var19.isInRangeToRender3d(var4, var6, var8) && (var19.ignoreFrustumCheck || p_180446_2_.isBoundingBoxInFrustum(var19.getEntityBoundingBox()) || var19.riddenByEntity == this.mc.thePlayer) && var19 instanceof EntityPlayer);
/*      */             
/*  704 */             if ((var19 != this.mc.func_175606_aa() || this.mc.gameSettings.thirdPersonView != 0 || bool1) && var25)
/*      */             {
/*  706 */               this.field_175010_j.renderEntitySimple(var19, partialTicks);
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  711 */         this.field_175010_j.func_178632_c(false);
/*  712 */         RenderHelper.enableStandardItemLighting();
/*  713 */         GlStateManager.depthMask(false);
/*  714 */         this.field_174991_A.loadShaderGroup(partialTicks);
/*  715 */         GlStateManager.depthMask(true);
/*  716 */         this.mc.getFramebuffer().bindFramebuffer(false);
/*  717 */         GlStateManager.enableFog();
/*  718 */         GlStateManager.depthFunc(515);
/*  719 */         GlStateManager.enableDepth();
/*  720 */         GlStateManager.enableAlpha();
/*      */       } 
/*      */       
/*  723 */       this.theWorld.theProfiler.endStartSection("entities");
/*  724 */       boolean isShaders = Config.isShaders();
/*      */       
/*  726 */       if (isShaders)
/*      */       {
/*  728 */         Shaders.beginEntities();
/*      */       }
/*      */       
/*  731 */       Iterator<ContainerLocalRenderInformation> var35 = this.renderInfosEntities.iterator();
/*  732 */       boolean oldFancyGraphics = this.mc.gameSettings.fancyGraphics;
/*  733 */       this.mc.gameSettings.fancyGraphics = Config.isDroppedItemsFancy();
/*      */ 
/*      */       
/*  736 */       while (var35.hasNext()) {
/*      */         
/*  738 */         ContainerLocalRenderInformation var26 = var35.next();
/*  739 */         Chunk fontRenderer = this.theWorld.getChunkFromBlockCoords(var26.field_178036_a.func_178568_j());
/*  740 */         Iterator<Entity> var32 = fontRenderer.getEntityLists()[var26.field_178036_a.func_178568_j().getY() / 16].iterator();
/*      */         
/*  742 */         while (var32.hasNext()) {
/*      */           
/*  744 */           Entity var27 = var32.next();
/*      */           
/*  746 */           if (!forgeEntityPass || Reflector.callBoolean(var27, Reflector.ForgeEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) {
/*      */             
/*  748 */             boolean var30 = !(!this.field_175010_j.func_178635_a(var27, p_180446_2_, var4, var6, var8) && var27.riddenByEntity != this.mc.thePlayer);
/*      */             
/*  750 */             if (var30) {
/*      */               
/*  752 */               boolean var34 = (this.mc.func_175606_aa() instanceof EntityLivingBase) ? ((EntityLivingBase)this.mc.func_175606_aa()).isPlayerSleeping() : false;
/*      */               
/*  754 */               if ((var27 == this.mc.func_175606_aa() && this.mc.gameSettings.thirdPersonView == 0 && !var34) || (var27.posY >= 0.0D && var27.posY < 256.0D && !this.theWorld.isBlockLoaded(new BlockPos(var27)))) {
/*      */                 continue;
/*      */               }
/*      */ 
/*      */               
/*  759 */               this.countEntitiesRendered++;
/*      */               
/*  761 */               if (var27.getClass() == EntityItemFrame.class)
/*      */               {
/*  763 */                 var27.renderDistanceWeight = 0.06D;
/*      */               }
/*      */               
/*  766 */               this.renderedEntity = var27;
/*      */               
/*  768 */               if (isShaders)
/*      */               {
/*  770 */                 Shaders.nextEntity(var27);
/*      */               }
/*      */               
/*  773 */               this.field_175010_j.renderEntitySimple(var27, partialTicks);
/*  774 */               this.renderedEntity = null;
/*      */             } 
/*      */             
/*  777 */             if (!var30 && var27 instanceof net.minecraft.entity.projectile.EntityWitherSkull) {
/*      */               
/*  779 */               if (isShaders)
/*      */               {
/*  781 */                 Shaders.nextEntity(var27);
/*      */               }
/*      */               
/*  784 */               this.mc.getRenderManager().func_178630_b(var27, partialTicks);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  790 */       this.mc.gameSettings.fancyGraphics = oldFancyGraphics;
/*  791 */       FontRenderer var36 = TileEntityRendererDispatcher.instance.getFontRenderer();
/*      */       
/*  793 */       if (isShaders) {
/*      */         
/*  795 */         Shaders.endEntities();
/*  796 */         Shaders.beginBlockEntities();
/*      */       } 
/*      */       
/*  799 */       this.theWorld.theProfiler.endStartSection("blockentities");
/*  800 */       RenderHelper.enableStandardItemLighting();
/*      */       
/*  802 */       if (Reflector.ForgeTileEntityRendererDispatcher_preDrawBatch.exists())
/*      */       {
/*  804 */         Reflector.call(TileEntityRendererDispatcher.instance, Reflector.ForgeTileEntityRendererDispatcher_preDrawBatch, new Object[0]);
/*      */       }
/*      */       
/*  807 */       var35 = this.renderInfosTileEntities.iterator();
/*      */ 
/*      */       
/*  810 */       while (var35.hasNext()) {
/*      */         
/*  812 */         ContainerLocalRenderInformation var26 = var35.next();
/*  813 */         Iterator<TileEntity> var38 = var26.field_178036_a.func_178571_g().func_178485_b().iterator();
/*      */         
/*  815 */         while (var38.hasNext()) {
/*      */           
/*  817 */           TileEntity var37 = var38.next();
/*      */           
/*  819 */           if (forgeTileEntityPass) {
/*      */             
/*  821 */             if (!Reflector.callBoolean(var37, Reflector.ForgeTileEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) })) {
/*      */               continue;
/*      */             }
/*      */             
/*  825 */             AxisAlignedBB var40 = (AxisAlignedBB)Reflector.call(var37, Reflector.ForgeTileEntity_getRenderBoundingBox, new Object[0]);
/*      */             
/*  827 */             if (var40 != null && !p_180446_2_.isBoundingBoxInFrustum(var40)) {
/*      */               continue;
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/*  833 */           Class<?> var42 = var37.getClass();
/*      */           
/*  835 */           if (var42 == TileEntitySign.class && !Config.zoomMode) {
/*      */             
/*  837 */             EntityPlayerSP shouldRender = this.mc.thePlayer;
/*  838 */             double tileEntity = var37.getDistanceSq(shouldRender.posX, shouldRender.posY, shouldRender.posZ);
/*      */             
/*  840 */             if (tileEntity > 256.0D)
/*      */             {
/*  842 */               var36.enabled = false;
/*      */             }
/*      */           } 
/*      */           
/*  846 */           if (isShaders)
/*      */           {
/*  848 */             Shaders.nextBlockEntity(var37);
/*      */           }
/*      */           
/*  851 */           TileEntityRendererDispatcher.instance.func_180546_a(var37, partialTicks, -1);
/*  852 */           this.countTileEntitiesRendered++;
/*  853 */           var36.enabled = true;
/*      */         } 
/*      */       } 
/*      */       
/*  857 */       if (Reflector.ForgeTileEntityRendererDispatcher_drawBatch.exists())
/*      */       {
/*  859 */         Reflector.call(TileEntityRendererDispatcher.instance, Reflector.ForgeTileEntityRendererDispatcher_drawBatch, new Object[] { Integer.valueOf(pass) });
/*      */       }
/*      */       
/*  862 */       func_180443_s();
/*  863 */       var35 = this.damagedBlocks.values().iterator();
/*      */       
/*  865 */       while (var35.hasNext()) {
/*      */         boolean var45;
/*  867 */         DestroyBlockProgress var39 = (DestroyBlockProgress)var35.next();
/*  868 */         BlockPos var41 = var39.func_180246_b();
/*  869 */         TileEntity var37 = this.theWorld.getTileEntity(var41);
/*      */         
/*  871 */         if (var37 instanceof TileEntityChest) {
/*      */           
/*  873 */           TileEntityChest var43 = (TileEntityChest)var37;
/*      */           
/*  875 */           if (var43.adjacentChestXNeg != null) {
/*      */             
/*  877 */             var41 = var41.offset(EnumFacing.WEST);
/*  878 */             var37 = this.theWorld.getTileEntity(var41);
/*      */           }
/*  880 */           else if (var43.adjacentChestZNeg != null) {
/*      */             
/*  882 */             var41 = var41.offset(EnumFacing.NORTH);
/*  883 */             var37 = this.theWorld.getTileEntity(var41);
/*      */           } 
/*      */         } 
/*      */         
/*  887 */         Block var44 = this.theWorld.getBlockState(var41).getBlock();
/*      */ 
/*      */         
/*  890 */         if (forgeTileEntityPass) {
/*      */           
/*  892 */           var45 = false;
/*      */           
/*  894 */           if (var37 != null && Reflector.callBoolean(var37, Reflector.ForgeTileEntity_shouldRenderInPass, new Object[] { Integer.valueOf(pass) }) && Reflector.callBoolean(var37, Reflector.ForgeTileEntity_canRenderBreaking, new Object[0]))
/*      */           {
/*  896 */             AxisAlignedBB aabb = (AxisAlignedBB)Reflector.call(var37, Reflector.ForgeTileEntity_getRenderBoundingBox, new Object[0]);
/*      */             
/*  898 */             if (aabb != null)
/*      */             {
/*  900 */               var45 = p_180446_2_.isBoundingBoxInFrustum(aabb);
/*      */             }
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  906 */           var45 = (var37 != null && (var44 instanceof net.minecraft.block.BlockChest || var44 instanceof net.minecraft.block.BlockEnderChest || var44 instanceof net.minecraft.block.BlockSign || var44 instanceof net.minecraft.block.BlockSkull));
/*      */         } 
/*      */         
/*  909 */         if (var45) {
/*      */           
/*  911 */           if (isShaders)
/*      */           {
/*  913 */             Shaders.nextBlockEntity(var37);
/*      */           }
/*      */           
/*  916 */           TileEntityRendererDispatcher.instance.func_180546_a(var37, partialTicks, var39.getPartialBlockDamage());
/*      */         } 
/*      */       } 
/*      */       
/*  920 */       func_174969_t();
/*  921 */       this.mc.entityRenderer.func_175072_h();
/*  922 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDebugInfoRenders() {
/*  931 */     int var1 = this.field_175008_n.field_178164_f.length;
/*  932 */     int var2 = 0;
/*  933 */     Iterator<ContainerLocalRenderInformation> var3 = this.glRenderLists.iterator();
/*      */     
/*  935 */     while (var3.hasNext()) {
/*      */       
/*  937 */       ContainerLocalRenderInformation var4 = var3.next();
/*  938 */       CompiledChunk var5 = var4.field_178036_a.field_178590_b;
/*      */       
/*  940 */       if (var5 != CompiledChunk.field_178502_a && !var5.func_178489_a())
/*      */       {
/*  942 */         var2++;
/*      */       }
/*      */     } 
/*      */     
/*  946 */     return String.format("C: %d/%d %sD: %d, %s", new Object[] { Integer.valueOf(var2), Integer.valueOf(var1), this.mc.field_175612_E ? "(s) " : "", Integer.valueOf(this.renderDistanceChunks), this.field_174995_M.func_178504_a() });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDebugInfoEntities() {
/*  954 */     return "E: " + this.countEntitiesRendered + "/" + this.countEntitiesTotal + ", B: " + this.countEntitiesHidden + ", I: " + (this.countEntitiesTotal - this.countEntitiesHidden - this.countEntitiesRendered) + ", " + Config.getVersionDebug();
/*      */   }
/*      */   
/*      */   public void func_174970_a(Entity viewEntity, double partialTicks, ICamera camera, int frameCount, boolean playerSpectator) {
/*      */     Frustrum frustrum;
/*  959 */     if (this.mc.gameSettings.renderDistanceChunks != this.renderDistanceChunks)
/*      */     {
/*  961 */       loadRenderers();
/*      */     }
/*      */     
/*  964 */     this.theWorld.theProfiler.startSection("camera");
/*  965 */     double var7 = viewEntity.posX - this.field_174992_B;
/*  966 */     double var9 = viewEntity.posY - this.field_174993_C;
/*  967 */     double var11 = viewEntity.posZ - this.field_174987_D;
/*      */     
/*  969 */     if (this.field_174988_E != viewEntity.chunkCoordX || this.field_174989_F != viewEntity.chunkCoordY || this.field_174990_G != viewEntity.chunkCoordZ || var7 * var7 + var9 * var9 + var11 * var11 > 16.0D) {
/*      */       
/*  971 */       this.field_174992_B = viewEntity.posX;
/*  972 */       this.field_174993_C = viewEntity.posY;
/*  973 */       this.field_174987_D = viewEntity.posZ;
/*  974 */       this.field_174988_E = viewEntity.chunkCoordX;
/*  975 */       this.field_174989_F = viewEntity.chunkCoordY;
/*  976 */       this.field_174990_G = viewEntity.chunkCoordZ;
/*  977 */       this.field_175008_n.func_178163_a(viewEntity.posX, viewEntity.posZ);
/*      */     } 
/*      */     
/*  980 */     if (Config.isDynamicLights())
/*      */     {
/*  982 */       DynamicLights.update(this);
/*      */     }
/*      */     
/*  985 */     this.theWorld.theProfiler.endStartSection("renderlistcamera");
/*  986 */     double var13 = viewEntity.lastTickPosX + (viewEntity.posX - viewEntity.lastTickPosX) * partialTicks;
/*  987 */     double var15 = viewEntity.lastTickPosY + (viewEntity.posY - viewEntity.lastTickPosY) * partialTicks;
/*  988 */     double var17 = viewEntity.lastTickPosZ + (viewEntity.posZ - viewEntity.lastTickPosZ) * partialTicks;
/*  989 */     this.field_174996_N.func_178004_a(var13, var15, var17);
/*  990 */     this.theWorld.theProfiler.endStartSection("cull");
/*      */     
/*  992 */     if (this.field_175001_U != null) {
/*      */       
/*  994 */       Frustrum var35 = new Frustrum(this.field_175001_U);
/*  995 */       var35.setPosition(this.field_175003_W.x, this.field_175003_W.y, this.field_175003_W.z);
/*  996 */       frustrum = var35;
/*      */     } 
/*      */     
/*  999 */     this.mc.mcProfiler.endStartSection("culling");
/* 1000 */     BlockPos var351 = new BlockPos(var13, var15 + viewEntity.getEyeHeight(), var17);
/* 1001 */     RenderChunk var20 = this.field_175008_n.func_178161_a(var351);
/* 1002 */     BlockPos var21 = new BlockPos(MathHelper.floor_double(var13) / 16 * 16, MathHelper.floor_double(var15) / 16 * 16, MathHelper.floor_double(var17) / 16 * 16);
/* 1003 */     this.displayListEntitiesDirty = !(!this.displayListEntitiesDirty && this.field_175009_l.isEmpty() && viewEntity.posX == this.field_174997_H && viewEntity.posY == this.field_174998_I && viewEntity.posZ == this.field_174999_J && viewEntity.rotationPitch == this.field_175000_K && viewEntity.rotationYaw == this.field_174994_L);
/* 1004 */     this.field_174997_H = viewEntity.posX;
/* 1005 */     this.field_174998_I = viewEntity.posY;
/* 1006 */     this.field_174999_J = viewEntity.posZ;
/* 1007 */     this.field_175000_K = viewEntity.rotationPitch;
/* 1008 */     this.field_174994_L = viewEntity.rotationYaw;
/* 1009 */     boolean var22 = (this.field_175001_U != null);
/* 1010 */     Lagometer.timerVisibility.start();
/*      */     
/* 1012 */     if (Shaders.isShadowPass) {
/*      */       
/* 1014 */       this.glRenderLists = this.renderInfosShadow;
/* 1015 */       this.renderInfosEntities = this.renderInfosEntitiesShadow;
/* 1016 */       this.renderInfosTileEntities = this.renderInfosTileEntitiesShadow;
/*      */       
/* 1018 */       if (!var22 && this.displayListEntitiesDirty) {
/*      */         
/* 1020 */         this.glRenderLists.clear();
/* 1021 */         this.renderInfosEntities.clear();
/* 1022 */         this.renderInfosTileEntities.clear();
/* 1023 */         RenderInfoLazy var39 = new RenderInfoLazy();
/*      */         
/* 1025 */         for (int var41 = 0; var41 < this.field_175008_n.field_178164_f.length; var41++)
/*      */         {
/* 1027 */           RenderChunk var36 = this.field_175008_n.field_178164_f[var41];
/* 1028 */           var39.setRenderChunk(var36);
/*      */           
/* 1030 */           if (!var36.field_178590_b.func_178489_a() || var36.func_178569_m())
/*      */           {
/* 1032 */             this.glRenderLists.add(var39.getRenderInfo());
/*      */           }
/*      */           
/* 1035 */           BlockPos var37 = var36.func_178568_j();
/*      */           
/* 1037 */           if (ChunkUtils.hasEntities(this.theWorld.getChunkFromBlockCoords(var37)))
/*      */           {
/* 1039 */             this.renderInfosEntities.add(var39.getRenderInfo());
/*      */           }
/*      */           
/* 1042 */           if (var36.func_178571_g().func_178485_b().size() > 0)
/*      */           {
/* 1044 */             this.renderInfosTileEntities.add(var39.getRenderInfo());
/*      */           }
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1051 */       this.glRenderLists = this.renderInfosNormal;
/* 1052 */       this.renderInfosEntities = this.renderInfosEntitiesNormal;
/* 1053 */       this.renderInfosTileEntities = this.renderInfosTileEntitiesNormal;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1059 */     if (!var22 && this.displayListEntitiesDirty && !Shaders.isShadowPass) {
/*      */       
/* 1061 */       this.displayListEntitiesDirty = false;
/* 1062 */       this.glRenderLists.clear();
/* 1063 */       this.renderInfosEntities.clear();
/* 1064 */       this.renderInfosTileEntities.clear();
/* 1065 */       this.visibilityDeque.clear();
/* 1066 */       Deque<ContainerLocalRenderInformation> var38 = this.visibilityDeque;
/* 1067 */       boolean var40 = this.mc.field_175612_E;
/*      */ 
/*      */       
/* 1070 */       if (var20 == null) {
/*      */         
/* 1072 */         int var46 = (var351.getY() > 0) ? 248 : 8;
/*      */         
/* 1074 */         for (int i = -this.renderDistanceChunks; i <= this.renderDistanceChunks; i++) {
/*      */           
/* 1076 */           for (int var43 = -this.renderDistanceChunks; var43 <= this.renderDistanceChunks; var43++) {
/*      */             
/* 1078 */             RenderChunk var45 = this.field_175008_n.func_178161_a(new BlockPos((i << 4) + 8, var46, (var43 << 4) + 8));
/*      */             
/* 1080 */             if (var45 != null && frustrum.isBoundingBoxInFrustum(var45.field_178591_c))
/*      */             {
/* 1082 */               var45.func_178577_a(frameCount);
/* 1083 */               var38.add(new ContainerLocalRenderInformation(var45, null, 0, null));
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */         
/* 1090 */         boolean var42 = false;
/* 1091 */         ContainerLocalRenderInformation var44 = new ContainerLocalRenderInformation(var20, null, 0, null);
/* 1092 */         Set var451 = SET_ALL_FACINGS;
/*      */         
/* 1094 */         if (!var451.isEmpty() && var451.size() == 1) {
/*      */           
/* 1096 */           Vector3f var47 = func_174962_a(viewEntity, partialTicks);
/* 1097 */           EnumFacing var31 = EnumFacing.func_176737_a(var47.x, var47.y, var47.z).getOpposite();
/* 1098 */           var451.remove(var31);
/*      */         } 
/*      */         
/* 1101 */         if (var451.isEmpty())
/*      */         {
/* 1103 */           var42 = true;
/*      */         }
/*      */         
/* 1106 */         if (var42 && !playerSpectator) {
/*      */           
/* 1108 */           this.glRenderLists.add(var44);
/*      */         }
/*      */         else {
/*      */           
/* 1112 */           if (playerSpectator && this.theWorld.getBlockState(var351).getBlock().isOpaqueCube())
/*      */           {
/* 1114 */             var40 = false;
/*      */           }
/*      */           
/* 1117 */           var20.func_178577_a(frameCount);
/* 1118 */           var38.add(var44);
/*      */         } 
/*      */       } 
/*      */       
/* 1122 */       EnumFacing[] var431 = EnumFacing.VALUES;
/* 1123 */       int var30 = var431.length;
/*      */       
/* 1125 */       while (!var38.isEmpty()) {
/*      */         
/* 1127 */         ContainerLocalRenderInformation var361 = var38.poll();
/* 1128 */         RenderChunk var371 = var361.field_178036_a;
/* 1129 */         EnumFacing var461 = var361.field_178034_b;
/* 1130 */         BlockPos var48 = var371.func_178568_j();
/*      */         
/* 1132 */         if (!var371.field_178590_b.func_178489_a() || var371.func_178569_m())
/*      */         {
/* 1134 */           this.glRenderLists.add(var361);
/*      */         }
/*      */         
/* 1137 */         if (ChunkUtils.hasEntities(this.theWorld.getChunkFromBlockCoords(var48)))
/*      */         {
/* 1139 */           this.renderInfosEntities.add(var361);
/*      */         }
/*      */         
/* 1142 */         if (var371.func_178571_g().func_178485_b().size() > 0)
/*      */         {
/* 1144 */           this.renderInfosTileEntities.add(var361);
/*      */         }
/*      */         
/* 1147 */         for (int var49 = 0; var49 < var30; var49++) {
/*      */           
/* 1149 */           EnumFacing var32 = var431[var49];
/*      */           
/* 1151 */           if ((!var40 || !var361.field_178035_c.contains(var32.getOpposite())) && (!var40 || var461 == null || var371.func_178571_g().func_178495_a(var461.getOpposite(), var32))) {
/*      */             
/* 1153 */             RenderChunk var33 = getRenderChunkOffset(var351, var371, var32);
/*      */             
/* 1155 */             if (var33 != null && var33.func_178577_a(frameCount) && frustrum.isBoundingBoxInFrustum(var33.field_178591_c)) {
/*      */               
/* 1157 */               ContainerLocalRenderInformation var34 = new ContainerLocalRenderInformation(var33, var32, var361.field_178032_d + 1, null);
/* 1158 */               var34.field_178035_c.addAll(var361.field_178035_c);
/* 1159 */               var34.field_178035_c.add(var32);
/* 1160 */               var38.add(var34);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1167 */     if (this.field_175002_T) {
/*      */       
/* 1169 */       func_174984_a(var13, var15, var17);
/* 1170 */       this.field_175002_T = false;
/*      */     } 
/*      */     
/* 1173 */     Lagometer.timerVisibility.end();
/*      */     
/* 1175 */     if (Shaders.isShadowPass) {
/*      */       
/* 1177 */       Shaders.mcProfilerEndSection();
/*      */     }
/*      */     else {
/*      */       
/* 1181 */       this.field_174995_M.func_178513_e();
/* 1182 */       Set var391 = this.field_175009_l;
/* 1183 */       this.field_175009_l = Sets.newLinkedHashSet();
/* 1184 */       Iterator<ContainerLocalRenderInformation> var411 = this.glRenderLists.iterator();
/* 1185 */       Lagometer.timerChunkUpdate.start();
/*      */       
/* 1187 */       while (var411.hasNext()) {
/*      */         
/* 1189 */         ContainerLocalRenderInformation var361 = var411.next();
/* 1190 */         RenderChunk var371 = var361.field_178036_a;
/*      */         
/* 1192 */         if (var371.func_178569_m() || var391.contains(var371)) {
/*      */           
/* 1194 */           this.displayListEntitiesDirty = true;
/*      */           
/* 1196 */           if (func_174983_a(var21, var361.field_178036_a)) {
/*      */             
/* 1198 */             if (!var371.isPlayerUpdate()) {
/*      */               
/* 1200 */               this.chunksToUpdateForced.add(var371);
/*      */               
/*      */               continue;
/*      */             } 
/* 1204 */             this.mc.mcProfiler.startSection("build near");
/* 1205 */             this.field_174995_M.func_178505_b(var371);
/* 1206 */             var371.func_178575_a(false);
/* 1207 */             this.mc.mcProfiler.endSection();
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/* 1212 */           this.field_175009_l.add(var371);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1217 */       Lagometer.timerChunkUpdate.end();
/* 1218 */       this.field_175009_l.addAll(var391);
/* 1219 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_174983_a(BlockPos p_174983_1_, RenderChunk p_174983_2_) {
/* 1225 */     BlockPos var3 = p_174983_2_.func_178568_j();
/* 1226 */     return (MathHelper.abs_int(p_174983_1_.getX() - var3.getX()) > 16) ? false : ((MathHelper.abs_int(p_174983_1_.getY() - var3.getY()) > 16) ? false : ((MathHelper.abs_int(p_174983_1_.getZ() - var3.getZ()) <= 16)));
/*      */   }
/*      */ 
/*      */   
/*      */   private Set func_174978_c(BlockPos p_174978_1_) {
/* 1231 */     VisGraph var2 = new VisGraph();
/* 1232 */     BlockPos var3 = new BlockPos(p_174978_1_.getX() >> 4 << 4, p_174978_1_.getY() >> 4 << 4, p_174978_1_.getZ() >> 4 << 4);
/* 1233 */     Chunk var4 = this.theWorld.getChunkFromBlockCoords(var3);
/* 1234 */     Iterator<BlockPos.MutableBlockPos> var5 = BlockPos.getAllInBoxMutable(var3, var3.add(15, 15, 15)).iterator();
/*      */     
/* 1236 */     while (var5.hasNext()) {
/*      */       
/* 1238 */       BlockPos.MutableBlockPos var6 = var5.next();
/*      */       
/* 1240 */       if (var4.getBlock((BlockPos)var6).isOpaqueCube())
/*      */       {
/* 1242 */         var2.func_178606_a((BlockPos)var6);
/*      */       }
/*      */     } 
/* 1245 */     return var2.func_178609_b(p_174978_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   private RenderChunk getRenderChunkOffset(BlockPos p_174973_1_, RenderChunk renderChunk, EnumFacing p_174973_3_) {
/* 1250 */     BlockPos var4 = renderChunk.getPositionOffset16(p_174973_3_);
/*      */     
/* 1252 */     if (var4.getY() >= 0 && var4.getY() < 256) {
/*      */       
/* 1254 */       int dx = MathHelper.abs_int(p_174973_1_.getX() - var4.getX());
/* 1255 */       int dz = MathHelper.abs_int(p_174973_1_.getZ() - var4.getZ());
/*      */       
/* 1257 */       if (Config.isFogOff()) {
/*      */         
/* 1259 */         if (dx > this.renderDistance || dz > this.renderDistance)
/*      */         {
/* 1261 */           return null;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/* 1266 */         int distSq = dx * dx + dz * dz;
/*      */         
/* 1268 */         if (distSq > this.renderDistanceSq)
/*      */         {
/* 1270 */           return null;
/*      */         }
/*      */       } 
/*      */       
/* 1274 */       return this.field_175008_n.func_178161_a(var4);
/*      */     } 
/*      */ 
/*      */     
/* 1278 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void func_174984_a(double p_174984_1_, double p_174984_3_, double p_174984_5_) {
/* 1284 */     this.field_175001_U = (ClippingHelper)new ClippingHelperImpl();
/* 1285 */     ((ClippingHelperImpl)this.field_175001_U).init();
/* 1286 */     Matrix4f var7 = new Matrix4f(this.field_175001_U.field_178626_c);
/* 1287 */     var7.transpose();
/* 1288 */     Matrix4f var8 = new Matrix4f(this.field_175001_U.field_178625_b);
/* 1289 */     var8.transpose();
/* 1290 */     Matrix4f var9 = new Matrix4f();
/* 1291 */     var9.mul(var8, var7);
/* 1292 */     var9.invert();
/* 1293 */     this.field_175003_W.x = p_174984_1_;
/* 1294 */     this.field_175003_W.y = p_174984_3_;
/* 1295 */     this.field_175003_W.z = p_174984_5_;
/* 1296 */     this.field_175004_V[0] = new Vector4f(-1.0F, -1.0F, -1.0F, 1.0F);
/* 1297 */     this.field_175004_V[1] = new Vector4f(1.0F, -1.0F, -1.0F, 1.0F);
/* 1298 */     this.field_175004_V[2] = new Vector4f(1.0F, 1.0F, -1.0F, 1.0F);
/* 1299 */     this.field_175004_V[3] = new Vector4f(-1.0F, 1.0F, -1.0F, 1.0F);
/* 1300 */     this.field_175004_V[4] = new Vector4f(-1.0F, -1.0F, 1.0F, 1.0F);
/* 1301 */     this.field_175004_V[5] = new Vector4f(1.0F, -1.0F, 1.0F, 1.0F);
/* 1302 */     this.field_175004_V[6] = new Vector4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 1303 */     this.field_175004_V[7] = new Vector4f(-1.0F, 1.0F, 1.0F, 1.0F);
/*      */     
/* 1305 */     for (int var10 = 0; var10 < 8; var10++) {
/*      */       
/* 1307 */       var9.transform((Tuple4f)this.field_175004_V[var10]);
/* 1308 */       (this.field_175004_V[var10]).x /= (this.field_175004_V[var10]).w;
/* 1309 */       (this.field_175004_V[var10]).y /= (this.field_175004_V[var10]).w;
/* 1310 */       (this.field_175004_V[var10]).z /= (this.field_175004_V[var10]).w;
/* 1311 */       (this.field_175004_V[var10]).w = 1.0F;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected Vector3f func_174962_a(Entity entityIn, double partialTicks) {
/* 1317 */     float var4 = (float)(entityIn.prevRotationPitch + (entityIn.rotationPitch - entityIn.prevRotationPitch) * partialTicks);
/* 1318 */     float var5 = (float)(entityIn.prevRotationYaw + (entityIn.rotationYaw - entityIn.prevRotationYaw) * partialTicks);
/*      */     
/* 1320 */     if ((Minecraft.getMinecraft()).gameSettings.thirdPersonView == 2)
/*      */     {
/* 1322 */       var4 += 180.0F;
/*      */     }
/*      */     
/* 1325 */     float var6 = MathHelper.cos(-var5 * 0.017453292F - 3.1415927F);
/* 1326 */     float var7 = MathHelper.sin(-var5 * 0.017453292F - 3.1415927F);
/* 1327 */     float var8 = -MathHelper.cos(-var4 * 0.017453292F);
/* 1328 */     float var9 = MathHelper.sin(-var4 * 0.017453292F);
/* 1329 */     return new Vector3f(var7 * var8, var9, var6 * var8);
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_174977_a(EnumWorldBlockLayer blockLayerIn, double partialTicks, int pass, Entity entityIn) {
/* 1334 */     RenderHelper.disableStandardItemLighting();
/*      */     
/* 1336 */     if (blockLayerIn == EnumWorldBlockLayer.TRANSLUCENT) {
/*      */       
/* 1338 */       this.mc.mcProfiler.startSection("translucent_sort");
/* 1339 */       double var15 = entityIn.posX - this.prevRenderSortX;
/* 1340 */       double var16 = entityIn.posY - this.prevRenderSortY;
/* 1341 */       double var17 = entityIn.posZ - this.prevRenderSortZ;
/*      */       
/* 1343 */       if (var15 * var15 + var16 * var16 + var17 * var17 > 1.0D) {
/*      */         
/* 1345 */         this.prevRenderSortX = entityIn.posX;
/* 1346 */         this.prevRenderSortY = entityIn.posY;
/* 1347 */         this.prevRenderSortZ = entityIn.posZ;
/* 1348 */         int var18 = 0;
/* 1349 */         Iterator<ContainerLocalRenderInformation> var13 = this.glRenderLists.iterator();
/* 1350 */         this.chunksToResortTransparency.clear();
/*      */         
/* 1352 */         while (var13.hasNext()) {
/*      */           
/* 1354 */           ContainerLocalRenderInformation var14 = var13.next();
/*      */           
/* 1356 */           if (var14.field_178036_a.field_178590_b.func_178492_d(blockLayerIn) && var18++ < 15)
/*      */           {
/* 1358 */             this.chunksToResortTransparency.add(var14.field_178036_a);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1363 */       this.mc.mcProfiler.endSection();
/*      */     } 
/*      */     
/* 1366 */     this.mc.mcProfiler.startSection("filterempty");
/* 1367 */     int var151 = 0;
/* 1368 */     boolean var7 = (blockLayerIn == EnumWorldBlockLayer.TRANSLUCENT);
/* 1369 */     int var161 = var7 ? (this.glRenderLists.size() - 1) : 0;
/* 1370 */     int var9 = var7 ? -1 : this.glRenderLists.size();
/* 1371 */     int var171 = var7 ? -1 : 1;
/*      */     
/* 1373 */     for (int var11 = var161; var11 != var9; var11 += var171) {
/*      */       
/* 1375 */       RenderChunk var181 = ((ContainerLocalRenderInformation)this.glRenderLists.get(var11)).field_178036_a;
/*      */       
/* 1377 */       if (!var181.func_178571_g().func_178491_b(blockLayerIn)) {
/*      */         
/* 1379 */         var151++;
/* 1380 */         this.field_174996_N.func_178002_a(var181, blockLayerIn);
/*      */       } 
/*      */     } 
/*      */     
/* 1384 */     if (var151 == 0) {
/*      */       
/* 1386 */       this.mc.mcProfiler.endSection();
/* 1387 */       return var151;
/*      */     } 
/*      */ 
/*      */     
/* 1391 */     if (Config.isFogOff() && this.mc.entityRenderer.fogStandard)
/*      */     {
/* 1393 */       GlStateManager.disableFog();
/*      */     }
/*      */     
/* 1396 */     this.mc.mcProfiler.endStartSection("render_" + blockLayerIn);
/* 1397 */     func_174982_a(blockLayerIn);
/* 1398 */     this.mc.mcProfiler.endSection();
/* 1399 */     return var151;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void func_174982_a(EnumWorldBlockLayer blockLayerIn) {
/* 1405 */     this.mc.entityRenderer.func_180436_i();
/*      */     
/* 1407 */     if (OpenGlHelper.func_176075_f()) {
/*      */       
/* 1409 */       GL11.glEnableClientState(32884);
/* 1410 */       OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/* 1411 */       GL11.glEnableClientState(32888);
/* 1412 */       OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 1413 */       GL11.glEnableClientState(32888);
/* 1414 */       OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/* 1415 */       GL11.glEnableClientState(32886);
/*      */     } 
/*      */     
/* 1418 */     if (Config.isShaders())
/*      */     {
/* 1420 */       ShadersRender.preRenderChunkLayer();
/*      */     }
/*      */     
/* 1423 */     this.field_174996_N.func_178001_a(blockLayerIn);
/*      */     
/* 1425 */     if (Config.isShaders())
/*      */     {
/* 1427 */       ShadersRender.postRenderChunkLayer();
/*      */     }
/*      */     
/* 1430 */     if (OpenGlHelper.func_176075_f()) {
/*      */       
/* 1432 */       List var2 = DefaultVertexFormats.field_176600_a.func_177343_g();
/* 1433 */       Iterator<VertexFormatElement> var3 = var2.iterator();
/*      */       
/* 1435 */       while (var3.hasNext()) {
/*      */         
/* 1437 */         VertexFormatElement var4 = var3.next();
/* 1438 */         VertexFormatElement.EnumUseage var5 = var4.func_177375_c();
/* 1439 */         int var6 = var4.func_177369_e();
/*      */         
/* 1441 */         switch (SwitchEnumUseage.field_178037_a[var5.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1444 */             GL11.glDisableClientState(32884);
/*      */ 
/*      */           
/*      */           case 2:
/* 1448 */             OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit + var6);
/* 1449 */             GL11.glDisableClientState(32888);
/* 1450 */             OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*      */ 
/*      */           
/*      */           case 3:
/* 1454 */             GL11.glDisableClientState(32886);
/* 1455 */             GlStateManager.func_179117_G();
/*      */         } 
/*      */       
/*      */       } 
/*      */     } 
/* 1460 */     this.mc.entityRenderer.func_175072_h();
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_174965_a(Iterator<DestroyBlockProgress> p_174965_1_) {
/* 1465 */     while (p_174965_1_.hasNext()) {
/*      */       
/* 1467 */       DestroyBlockProgress var2 = p_174965_1_.next();
/* 1468 */       int var3 = var2.getCreationCloudUpdateTick();
/*      */       
/* 1470 */       if (this.cloudTickCounter - var3 > 400)
/*      */       {
/* 1472 */         p_174965_1_.remove();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateClouds() {
/* 1479 */     if (Config.isShaders() && Keyboard.isKeyDown(61) && Keyboard.isKeyDown(19))
/*      */     {
/* 1481 */       Shaders.uninit();
/*      */     }
/*      */     
/* 1484 */     this.cloudTickCounter++;
/*      */     
/* 1486 */     if (this.cloudTickCounter % 20 == 0)
/*      */     {
/* 1488 */       func_174965_a(this.damagedBlocks.values().iterator());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180448_r() {
/* 1494 */     if (Config.isSkyEnabled()) {
/*      */       
/* 1496 */       GlStateManager.disableFog();
/* 1497 */       GlStateManager.disableAlpha();
/* 1498 */       GlStateManager.enableBlend();
/* 1499 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1500 */       RenderHelper.disableStandardItemLighting();
/* 1501 */       GlStateManager.depthMask(false);
/* 1502 */       this.renderEngine.bindTexture(locationEndSkyPng);
/* 1503 */       Tessellator var1 = Tessellator.getInstance();
/* 1504 */       WorldRenderer var2 = var1.getWorldRenderer();
/*      */       
/* 1506 */       for (int var3 = 0; var3 < 6; var3++) {
/*      */         
/* 1508 */         GlStateManager.pushMatrix();
/*      */         
/* 1510 */         if (var3 == 1)
/*      */         {
/* 1512 */           GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
/*      */         }
/*      */         
/* 1515 */         if (var3 == 2)
/*      */         {
/* 1517 */           GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
/*      */         }
/*      */         
/* 1520 */         if (var3 == 3)
/*      */         {
/* 1522 */           GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
/*      */         }
/*      */         
/* 1525 */         if (var3 == 4)
/*      */         {
/* 1527 */           GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
/*      */         }
/*      */         
/* 1530 */         if (var3 == 5)
/*      */         {
/* 1532 */           GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
/*      */         }
/*      */         
/* 1535 */         var2.startDrawingQuads();
/* 1536 */         var2.func_178991_c(2631720);
/* 1537 */         var2.addVertexWithUV(-100.0D, -100.0D, -100.0D, 0.0D, 0.0D);
/* 1538 */         var2.addVertexWithUV(-100.0D, -100.0D, 100.0D, 0.0D, 16.0D);
/* 1539 */         var2.addVertexWithUV(100.0D, -100.0D, 100.0D, 16.0D, 16.0D);
/* 1540 */         var2.addVertexWithUV(100.0D, -100.0D, -100.0D, 16.0D, 0.0D);
/* 1541 */         var1.draw();
/* 1542 */         GlStateManager.popMatrix();
/*      */       } 
/*      */       
/* 1545 */       GlStateManager.depthMask(true);
/* 1546 */       GlStateManager.func_179098_w();
/* 1547 */       GlStateManager.enableAlpha();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174976_a(float partialTicks, int pass) {
/* 1553 */     if (Reflector.ForgeWorldProvider_getSkyRenderer.exists()) {
/*      */       
/* 1555 */       WorldProvider isShaders = this.mc.theWorld.provider;
/* 1556 */       Object var3 = Reflector.call(isShaders, Reflector.ForgeWorldProvider_getSkyRenderer, new Object[0]);
/*      */       
/* 1558 */       if (var3 != null) {
/*      */         
/* 1560 */         Reflector.callVoid(var3, Reflector.IRenderHandler_render, new Object[] { Float.valueOf(partialTicks), this.theWorld, this.mc });
/*      */         
/*      */         return;
/*      */       } 
/*      */     } 
/* 1565 */     if (this.mc.theWorld.provider.getDimensionId() == 1) {
/*      */       
/* 1567 */       func_180448_r();
/*      */     }
/* 1569 */     else if (this.mc.theWorld.provider.isSurfaceWorld()) {
/*      */       
/* 1571 */       GlStateManager.func_179090_x();
/* 1572 */       boolean var231 = Config.isShaders();
/*      */       
/* 1574 */       if (var231)
/*      */       {
/* 1576 */         Shaders.disableTexture2D();
/*      */       }
/*      */       
/* 1579 */       Vec3 var241 = this.theWorld.getSkyColor(this.mc.func_175606_aa(), partialTicks);
/* 1580 */       var241 = CustomColors.getSkyColor(var241, (IBlockAccess)this.mc.theWorld, (this.mc.func_175606_aa()).posX, (this.mc.func_175606_aa()).posY + 1.0D, (this.mc.func_175606_aa()).posZ);
/*      */       
/* 1582 */       if (var231)
/*      */       {
/* 1584 */         Shaders.setSkyColor(var241);
/*      */       }
/*      */       
/* 1587 */       float var4 = (float)var241.xCoord;
/* 1588 */       float var5 = (float)var241.yCoord;
/* 1589 */       float var6 = (float)var241.zCoord;
/*      */       
/* 1591 */       if (pass != 2) {
/*      */         
/* 1593 */         float var23 = (var4 * 30.0F + var5 * 59.0F + var6 * 11.0F) / 100.0F;
/* 1594 */         float var24 = (var4 * 30.0F + var5 * 70.0F) / 100.0F;
/* 1595 */         float var25 = (var4 * 30.0F + var6 * 70.0F) / 100.0F;
/* 1596 */         var4 = var23;
/* 1597 */         var5 = var24;
/* 1598 */         var6 = var25;
/*      */       } 
/*      */       
/* 1601 */       GlStateManager.color(var4, var5, var6);
/* 1602 */       Tessellator var251 = Tessellator.getInstance();
/* 1603 */       WorldRenderer var261 = var251.getWorldRenderer();
/* 1604 */       GlStateManager.depthMask(false);
/* 1605 */       GlStateManager.enableFog();
/*      */       
/* 1607 */       if (var231)
/*      */       {
/* 1609 */         Shaders.enableFog();
/*      */       }
/*      */       
/* 1612 */       GlStateManager.color(var4, var5, var6);
/*      */       
/* 1614 */       if (var231)
/*      */       {
/* 1616 */         Shaders.preSkyList();
/*      */       }
/*      */       
/* 1619 */       if (Config.isSkyEnabled())
/*      */       {
/* 1621 */         if (this.field_175005_X) {
/*      */           
/* 1623 */           this.field_175012_t.func_177359_a();
/* 1624 */           GL11.glEnableClientState(32884);
/* 1625 */           GL11.glVertexPointer(3, 5126, 12, 0L);
/* 1626 */           this.field_175012_t.func_177358_a(7);
/* 1627 */           this.field_175012_t.func_177361_b();
/* 1628 */           GL11.glDisableClientState(32884);
/*      */         }
/*      */         else {
/*      */           
/* 1632 */           GlStateManager.callList(this.glSkyList);
/*      */         } 
/*      */       }
/*      */       
/* 1636 */       GlStateManager.disableFog();
/*      */       
/* 1638 */       if (var231)
/*      */       {
/* 1640 */         Shaders.disableFog();
/*      */       }
/*      */       
/* 1643 */       GlStateManager.disableAlpha();
/* 1644 */       GlStateManager.enableBlend();
/* 1645 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 1646 */       RenderHelper.disableStandardItemLighting();
/* 1647 */       float[] var27 = this.theWorld.provider.calcSunriseSunsetColors(this.theWorld.getCelestialAngle(partialTicks), partialTicks);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1658 */       if (var27 != null && Config.isSunMoonEnabled()) {
/*      */         
/* 1660 */         GlStateManager.func_179090_x();
/*      */         
/* 1662 */         if (var231)
/*      */         {
/* 1664 */           Shaders.disableTexture2D();
/*      */         }
/*      */         
/* 1667 */         GlStateManager.shadeModel(7425);
/* 1668 */         GlStateManager.pushMatrix();
/* 1669 */         GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
/* 1670 */         GlStateManager.rotate((MathHelper.sin(this.theWorld.getCelestialAngleRadians(partialTicks)) < 0.0F) ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
/* 1671 */         GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
/* 1672 */         float f1 = var27[0];
/* 1673 */         float f2 = var27[1];
/* 1674 */         float f3 = var27[2];
/*      */         
/* 1676 */         if (pass != 2) {
/*      */           
/* 1678 */           float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
/* 1679 */           float var14 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
/* 1680 */           float f5 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
/* 1681 */           f1 = f4;
/* 1682 */           f2 = var14;
/* 1683 */           f3 = f5;
/*      */         } 
/*      */         
/* 1686 */         var261.startDrawing(6);
/* 1687 */         var261.func_178960_a(f1, f2, f3, var27[3]);
/* 1688 */         var261.addVertex(0.0D, 100.0D, 0.0D);
/* 1689 */         boolean var26 = true;
/* 1690 */         var261.func_178960_a(var27[0], var27[1], var27[2], 0.0F);
/*      */         
/* 1692 */         for (int var31 = 0; var31 <= 16; var31++) {
/*      */           
/* 1694 */           float f4 = var31 * 3.1415927F * 2.0F / 16.0F;
/* 1695 */           float var18 = MathHelper.sin(f4);
/* 1696 */           float var19 = MathHelper.cos(f4);
/* 1697 */           var261.addVertex((var18 * 120.0F), (var19 * 120.0F), (-var19 * 40.0F * var27[3]));
/*      */         } 
/*      */         
/* 1700 */         var251.draw();
/* 1701 */         GlStateManager.popMatrix();
/* 1702 */         GlStateManager.shadeModel(7424);
/*      */       } 
/*      */       
/* 1705 */       GlStateManager.func_179098_w();
/*      */       
/* 1707 */       if (var231)
/*      */       {
/* 1709 */         Shaders.enableTexture2D();
/*      */       }
/*      */       
/* 1712 */       GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
/* 1713 */       GlStateManager.pushMatrix();
/* 1714 */       float var10 = 1.0F - this.theWorld.getRainStrength(partialTicks);
/* 1715 */       float var11 = 0.0F;
/* 1716 */       float var12 = 0.0F;
/* 1717 */       float var13 = 0.0F;
/* 1718 */       GlStateManager.color(1.0F, 1.0F, 1.0F, var10);
/* 1719 */       GlStateManager.translate(0.0F, 0.0F, 0.0F);
/* 1720 */       GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
/* 1721 */       CustomSky.renderSky((World)this.theWorld, this.renderEngine, this.theWorld.getCelestialAngle(partialTicks), var10);
/*      */       
/* 1723 */       if (var231)
/*      */       {
/* 1725 */         Shaders.preCelestialRotate();
/*      */       }
/*      */       
/* 1728 */       GlStateManager.rotate(this.theWorld.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
/*      */       
/* 1730 */       if (var231)
/*      */       {
/* 1732 */         Shaders.postCelestialRotate();
/*      */       }
/*      */       
/* 1735 */       if (Config.isSunMoonEnabled()) {
/*      */         
/* 1737 */         float var14 = 30.0F;
/* 1738 */         this.renderEngine.bindTexture(locationSunPng);
/* 1739 */         var261.startDrawingQuads();
/* 1740 */         var261.addVertexWithUV(-var14, 100.0D, -var14, 0.0D, 0.0D);
/* 1741 */         var261.addVertexWithUV(var14, 100.0D, -var14, 1.0D, 0.0D);
/* 1742 */         var261.addVertexWithUV(var14, 100.0D, var14, 1.0D, 1.0D);
/* 1743 */         var261.addVertexWithUV(-var14, 100.0D, var14, 0.0D, 1.0D);
/* 1744 */         var251.draw();
/* 1745 */         var14 = 20.0F;
/* 1746 */         this.renderEngine.bindTexture(locationMoonPhasesPng);
/* 1747 */         int var28 = this.theWorld.getMoonPhase();
/* 1748 */         int var29 = var28 % 4;
/* 1749 */         int var31 = var28 / 4 % 2;
/* 1750 */         float var18 = (var29 + 0) / 4.0F;
/* 1751 */         float var19 = (var31 + 0) / 2.0F;
/* 1752 */         float var20 = (var29 + 1) / 4.0F;
/* 1753 */         float var21 = (var31 + 1) / 2.0F;
/* 1754 */         var261.startDrawingQuads();
/* 1755 */         var261.addVertexWithUV(-var14, -100.0D, var14, var20, var21);
/* 1756 */         var261.addVertexWithUV(var14, -100.0D, var14, var18, var21);
/* 1757 */         var261.addVertexWithUV(var14, -100.0D, -var14, var18, var19);
/* 1758 */         var261.addVertexWithUV(-var14, -100.0D, -var14, var20, var19);
/* 1759 */         var251.draw();
/*      */       } 
/*      */       
/* 1762 */       GlStateManager.func_179090_x();
/*      */       
/* 1764 */       if (var231)
/*      */       {
/* 1766 */         Shaders.disableTexture2D();
/*      */       }
/*      */       
/* 1769 */       float var22 = this.theWorld.getStarBrightness(partialTicks) * var10;
/*      */       
/* 1771 */       if (var22 > 0.0F && Config.isStarsEnabled() && !CustomSky.hasSkyLayers((World)this.theWorld)) {
/*      */         
/* 1773 */         GlStateManager.color(var22, var22, var22, var22);
/*      */         
/* 1775 */         if (this.field_175005_X) {
/*      */           
/* 1777 */           this.field_175013_s.func_177359_a();
/* 1778 */           GL11.glEnableClientState(32884);
/* 1779 */           GL11.glVertexPointer(3, 5126, 12, 0L);
/* 1780 */           this.field_175013_s.func_177358_a(7);
/* 1781 */           this.field_175013_s.func_177361_b();
/* 1782 */           GL11.glDisableClientState(32884);
/*      */         }
/*      */         else {
/*      */           
/* 1786 */           GlStateManager.callList(this.starGLCallList);
/*      */         } 
/*      */       } 
/*      */       
/* 1790 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 1791 */       GlStateManager.disableBlend();
/* 1792 */       GlStateManager.enableAlpha();
/* 1793 */       GlStateManager.enableFog();
/*      */       
/* 1795 */       if (var231)
/*      */       {
/* 1797 */         Shaders.enableFog();
/*      */       }
/*      */       
/* 1800 */       GlStateManager.popMatrix();
/* 1801 */       GlStateManager.func_179090_x();
/*      */       
/* 1803 */       if (var231)
/*      */       {
/* 1805 */         Shaders.disableTexture2D();
/*      */       }
/*      */       
/* 1808 */       GlStateManager.color(0.0F, 0.0F, 0.0F);
/* 1809 */       double var30 = (this.mc.thePlayer.func_174824_e(partialTicks)).yCoord - this.theWorld.getHorizon();
/*      */       
/* 1811 */       if (var30 < 0.0D) {
/*      */         
/* 1813 */         GlStateManager.pushMatrix();
/* 1814 */         GlStateManager.translate(0.0F, 12.0F, 0.0F);
/*      */         
/* 1816 */         if (this.field_175005_X) {
/*      */           
/* 1818 */           this.field_175011_u.func_177359_a();
/* 1819 */           GL11.glEnableClientState(32884);
/* 1820 */           GL11.glVertexPointer(3, 5126, 12, 0L);
/* 1821 */           this.field_175011_u.func_177358_a(7);
/* 1822 */           this.field_175011_u.func_177361_b();
/* 1823 */           GL11.glDisableClientState(32884);
/*      */         }
/*      */         else {
/*      */           
/* 1827 */           GlStateManager.callList(this.glSkyList2);
/*      */         } 
/*      */         
/* 1830 */         GlStateManager.popMatrix();
/* 1831 */         var12 = 1.0F;
/* 1832 */         var13 = -((float)(var30 + 65.0D));
/* 1833 */         float var14 = -1.0F;
/* 1834 */         var261.startDrawingQuads();
/* 1835 */         var261.func_178974_a(0, 255);
/* 1836 */         var261.addVertex(-1.0D, var13, 1.0D);
/* 1837 */         var261.addVertex(1.0D, var13, 1.0D);
/* 1838 */         var261.addVertex(1.0D, -1.0D, 1.0D);
/* 1839 */         var261.addVertex(-1.0D, -1.0D, 1.0D);
/* 1840 */         var261.addVertex(-1.0D, -1.0D, -1.0D);
/* 1841 */         var261.addVertex(1.0D, -1.0D, -1.0D);
/* 1842 */         var261.addVertex(1.0D, var13, -1.0D);
/* 1843 */         var261.addVertex(-1.0D, var13, -1.0D);
/* 1844 */         var261.addVertex(1.0D, -1.0D, -1.0D);
/* 1845 */         var261.addVertex(1.0D, -1.0D, 1.0D);
/* 1846 */         var261.addVertex(1.0D, var13, 1.0D);
/* 1847 */         var261.addVertex(1.0D, var13, -1.0D);
/* 1848 */         var261.addVertex(-1.0D, var13, -1.0D);
/* 1849 */         var261.addVertex(-1.0D, var13, 1.0D);
/* 1850 */         var261.addVertex(-1.0D, -1.0D, 1.0D);
/* 1851 */         var261.addVertex(-1.0D, -1.0D, -1.0D);
/* 1852 */         var261.addVertex(-1.0D, -1.0D, -1.0D);
/* 1853 */         var261.addVertex(-1.0D, -1.0D, 1.0D);
/* 1854 */         var261.addVertex(1.0D, -1.0D, 1.0D);
/* 1855 */         var261.addVertex(1.0D, -1.0D, -1.0D);
/* 1856 */         var251.draw();
/*      */       } 
/*      */       
/* 1859 */       if (this.theWorld.provider.isSkyColored()) {
/*      */         
/* 1861 */         GlStateManager.color(var4 * 0.2F + 0.04F, var5 * 0.2F + 0.04F, var6 * 0.6F + 0.1F);
/*      */       }
/*      */       else {
/*      */         
/* 1865 */         GlStateManager.color(var4, var5, var6);
/*      */       } 
/*      */       
/* 1868 */       if (this.mc.gameSettings.renderDistanceChunks <= 4)
/*      */       {
/* 1870 */         GlStateManager.color(this.mc.entityRenderer.field_175080_Q, this.mc.entityRenderer.field_175082_R, this.mc.entityRenderer.field_175081_S);
/*      */       }
/*      */       
/* 1873 */       GlStateManager.pushMatrix();
/* 1874 */       GlStateManager.translate(0.0F, -((float)(var30 - 16.0D)), 0.0F);
/*      */       
/* 1876 */       if (Config.isSkyEnabled())
/*      */       {
/* 1878 */         GlStateManager.callList(this.glSkyList2);
/*      */       }
/*      */       
/* 1881 */       GlStateManager.popMatrix();
/* 1882 */       GlStateManager.func_179098_w();
/*      */       
/* 1884 */       if (var231)
/*      */       {
/* 1886 */         Shaders.enableTexture2D();
/*      */       }
/*      */       
/* 1889 */       GlStateManager.depthMask(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180447_b(float p_180447_1_, int p_180447_2_) {
/* 1895 */     if (!Config.isCloudsOff()) {
/*      */       
/* 1897 */       if (Reflector.ForgeWorldProvider_getCloudRenderer.exists()) {
/*      */         
/* 1899 */         WorldProvider partialTicks = this.mc.theWorld.provider;
/* 1900 */         Object var3 = Reflector.call(partialTicks, Reflector.ForgeWorldProvider_getCloudRenderer, new Object[0]);
/*      */         
/* 1902 */         if (var3 != null) {
/*      */           
/* 1904 */           Reflector.callVoid(var3, Reflector.IRenderHandler_render, new Object[] { Float.valueOf(p_180447_1_), this.theWorld, this.mc });
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/* 1909 */       if (this.mc.theWorld.provider.isSurfaceWorld()) {
/*      */         
/* 1911 */         if (Config.isShaders())
/*      */         {
/* 1913 */           Shaders.beginClouds();
/*      */         }
/*      */         
/* 1916 */         if (Config.isCloudsFancy()) {
/*      */           
/* 1918 */           func_180445_c(p_180447_1_, p_180447_2_);
/*      */         }
/*      */         else {
/*      */           
/* 1922 */           this.cloudRenderer.prepareToRender(false, this.cloudTickCounter, p_180447_1_);
/* 1923 */           p_180447_1_ = 0.0F;
/* 1924 */           GlStateManager.disableCull();
/* 1925 */           float var31 = (float)((this.mc.func_175606_aa()).lastTickPosY + ((this.mc.func_175606_aa()).posY - (this.mc.func_175606_aa()).lastTickPosY) * p_180447_1_);
/* 1926 */           boolean var4 = true;
/* 1927 */           boolean var5 = true;
/* 1928 */           Tessellator var6 = Tessellator.getInstance();
/* 1929 */           WorldRenderer var7 = var6.getWorldRenderer();
/* 1930 */           this.renderEngine.bindTexture(locationCloudsPng);
/* 1931 */           GlStateManager.enableBlend();
/* 1932 */           GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/*      */           
/* 1934 */           if (this.cloudRenderer.shouldUpdateGlList()) {
/*      */             
/* 1936 */             this.cloudRenderer.startUpdateGlList();
/* 1937 */             Vec3 var8 = this.theWorld.getCloudColour(p_180447_1_);
/* 1938 */             float var9 = (float)var8.xCoord;
/* 1939 */             float var10 = (float)var8.yCoord;
/* 1940 */             float var11 = (float)var8.zCoord;
/*      */ 
/*      */             
/* 1943 */             if (p_180447_2_ != 2) {
/*      */               
/* 1945 */               float f1 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
/* 1946 */               float var26 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
/* 1947 */               float var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
/* 1948 */               var9 = f1;
/* 1949 */               var10 = var26;
/* 1950 */               var11 = var14;
/*      */             } 
/*      */             
/* 1953 */             float var12 = 4.8828125E-4F;
/* 1954 */             double var261 = (this.cloudTickCounter + p_180447_1_);
/* 1955 */             double var15 = (this.mc.func_175606_aa()).prevPosX + ((this.mc.func_175606_aa()).posX - (this.mc.func_175606_aa()).prevPosX) * p_180447_1_ + var261 * 0.029999999329447746D;
/* 1956 */             double var17 = (this.mc.func_175606_aa()).prevPosZ + ((this.mc.func_175606_aa()).posZ - (this.mc.func_175606_aa()).prevPosZ) * p_180447_1_;
/* 1957 */             int var19 = MathHelper.floor_double(var15 / 2048.0D);
/* 1958 */             int var20 = MathHelper.floor_double(var17 / 2048.0D);
/* 1959 */             var15 -= (var19 * 2048);
/* 1960 */             var17 -= (var20 * 2048);
/* 1961 */             float var21 = this.theWorld.provider.getCloudHeight() - var31 + 0.33F;
/* 1962 */             var21 += this.mc.gameSettings.ofCloudsHeight * 128.0F;
/* 1963 */             float var22 = (float)(var15 * 4.8828125E-4D);
/* 1964 */             float var23 = (float)(var17 * 4.8828125E-4D);
/* 1965 */             var7.startDrawingQuads();
/* 1966 */             var7.func_178960_a(var9, var10, var11, 0.8F);
/*      */             
/* 1968 */             for (int var24 = -256; var24 < 256; var24 += 32) {
/*      */               
/* 1970 */               for (int var25 = -256; var25 < 256; var25 += 32) {
/*      */                 
/* 1972 */                 var7.addVertexWithUV((var24 + 0), var21, (var25 + 32), ((var24 + 0) * 4.8828125E-4F + var22), ((var25 + 32) * 4.8828125E-4F + var23));
/* 1973 */                 var7.addVertexWithUV((var24 + 32), var21, (var25 + 32), ((var24 + 32) * 4.8828125E-4F + var22), ((var25 + 32) * 4.8828125E-4F + var23));
/* 1974 */                 var7.addVertexWithUV((var24 + 32), var21, (var25 + 0), ((var24 + 32) * 4.8828125E-4F + var22), ((var25 + 0) * 4.8828125E-4F + var23));
/* 1975 */                 var7.addVertexWithUV((var24 + 0), var21, (var25 + 0), ((var24 + 0) * 4.8828125E-4F + var22), ((var25 + 0) * 4.8828125E-4F + var23));
/*      */               } 
/*      */             } 
/*      */             
/* 1979 */             var6.draw();
/* 1980 */             this.cloudRenderer.endUpdateGlList();
/*      */           } 
/*      */           
/* 1983 */           this.cloudRenderer.renderGlList();
/* 1984 */           GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 1985 */           GlStateManager.disableBlend();
/* 1986 */           GlStateManager.enableCull();
/*      */         } 
/*      */         
/* 1989 */         if (Config.isShaders())
/*      */         {
/* 1991 */           Shaders.endClouds();
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasCloudFog(double p_72721_1_, double p_72721_3_, double p_72721_5_, float p_72721_7_) {
/* 2002 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180445_c(float p_180445_1_, int p_180445_2_) {
/* 2007 */     this.cloudRenderer.prepareToRender(true, this.cloudTickCounter, p_180445_1_);
/* 2008 */     p_180445_1_ = 0.0F;
/* 2009 */     GlStateManager.disableCull();
/* 2010 */     float var3 = (float)((this.mc.func_175606_aa()).lastTickPosY + ((this.mc.func_175606_aa()).posY - (this.mc.func_175606_aa()).lastTickPosY) * p_180445_1_);
/* 2011 */     Tessellator var4 = Tessellator.getInstance();
/* 2012 */     WorldRenderer var5 = var4.getWorldRenderer();
/* 2013 */     float var6 = 12.0F;
/* 2014 */     float var7 = 4.0F;
/* 2015 */     double var8 = (this.cloudTickCounter + p_180445_1_);
/* 2016 */     double var10 = ((this.mc.func_175606_aa()).prevPosX + ((this.mc.func_175606_aa()).posX - (this.mc.func_175606_aa()).prevPosX) * p_180445_1_ + var8 * 0.029999999329447746D) / 12.0D;
/* 2017 */     double var12 = ((this.mc.func_175606_aa()).prevPosZ + ((this.mc.func_175606_aa()).posZ - (this.mc.func_175606_aa()).prevPosZ) * p_180445_1_) / 12.0D + 0.33000001311302185D;
/* 2018 */     float var14 = this.theWorld.provider.getCloudHeight() - var3 + 0.33F;
/* 2019 */     var14 += this.mc.gameSettings.ofCloudsHeight * 128.0F;
/* 2020 */     int var15 = MathHelper.floor_double(var10 / 2048.0D);
/* 2021 */     int var16 = MathHelper.floor_double(var12 / 2048.0D);
/* 2022 */     var10 -= (var15 * 2048);
/* 2023 */     var12 -= (var16 * 2048);
/* 2024 */     this.renderEngine.bindTexture(locationCloudsPng);
/* 2025 */     GlStateManager.enableBlend();
/* 2026 */     GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 2027 */     Vec3 var17 = this.theWorld.getCloudColour(p_180445_1_);
/* 2028 */     float var18 = (float)var17.xCoord;
/* 2029 */     float var19 = (float)var17.yCoord;
/* 2030 */     float var20 = (float)var17.zCoord;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2035 */     if (p_180445_2_ != 2) {
/*      */       
/* 2037 */       float f1 = (var18 * 30.0F + var19 * 59.0F + var20 * 11.0F) / 100.0F;
/* 2038 */       float f2 = (var18 * 30.0F + var19 * 70.0F) / 100.0F;
/* 2039 */       float f3 = (var18 * 30.0F + var20 * 70.0F) / 100.0F;
/* 2040 */       var18 = f1;
/* 2041 */       var19 = f2;
/* 2042 */       var20 = f3;
/*      */     } 
/*      */     
/* 2045 */     float var21 = 0.00390625F;
/* 2046 */     float var22 = MathHelper.floor_double(var10) * 0.00390625F;
/* 2047 */     float var23 = MathHelper.floor_double(var12) * 0.00390625F;
/* 2048 */     float var24 = (float)(var10 - MathHelper.floor_double(var10));
/* 2049 */     float var25 = (float)(var12 - MathHelper.floor_double(var12));
/* 2050 */     boolean var26 = true;
/* 2051 */     boolean var27 = true;
/* 2052 */     float var28 = 9.765625E-4F;
/* 2053 */     GlStateManager.scale(12.0F, 1.0F, 12.0F);
/*      */     
/*      */     int var30;
/* 2056 */     for (var30 = 0; var30 < 2; var30++) {
/*      */       
/* 2058 */       if (var30 == 0) {
/*      */         
/* 2060 */         GlStateManager.colorMask(false, false, false, false);
/*      */       }
/*      */       else {
/*      */         
/* 2064 */         switch (p_180445_2_) {
/*      */           
/*      */           case 0:
/* 2067 */             GlStateManager.colorMask(false, true, true, true);
/*      */             break;
/*      */           
/*      */           case 1:
/* 2071 */             GlStateManager.colorMask(true, false, false, true);
/*      */             break;
/*      */           
/*      */           case 2:
/* 2075 */             GlStateManager.colorMask(true, true, true, true);
/*      */             break;
/*      */         } 
/*      */       } 
/* 2079 */       this.cloudRenderer.renderGlList();
/*      */     } 
/*      */     
/* 2082 */     if (this.cloudRenderer.shouldUpdateGlList()) {
/*      */       
/* 2084 */       this.cloudRenderer.startUpdateGlList();
/*      */       
/* 2086 */       for (var30 = -3; var30 <= 4; var30++) {
/*      */         
/* 2088 */         for (int var31 = -3; var31 <= 4; var31++) {
/*      */           
/* 2090 */           var5.startDrawingQuads();
/* 2091 */           float var32 = (var30 * 8);
/* 2092 */           float var33 = (var31 * 8);
/* 2093 */           float var34 = var32 - var24;
/* 2094 */           float var35 = var33 - var25;
/*      */           
/* 2096 */           if (var14 > -5.0F) {
/*      */             
/* 2098 */             var5.func_178960_a(var18 * 0.7F, var19 * 0.7F, var20 * 0.7F, 0.8F);
/* 2099 */             var5.func_178980_d(0.0F, -1.0F, 0.0F);
/* 2100 */             var5.addVertexWithUV((var34 + 0.0F), (var14 + 0.0F), (var35 + 8.0F), ((var32 + 0.0F) * 0.00390625F + var22), ((var33 + 8.0F) * 0.00390625F + var23));
/* 2101 */             var5.addVertexWithUV((var34 + 8.0F), (var14 + 0.0F), (var35 + 8.0F), ((var32 + 8.0F) * 0.00390625F + var22), ((var33 + 8.0F) * 0.00390625F + var23));
/* 2102 */             var5.addVertexWithUV((var34 + 8.0F), (var14 + 0.0F), (var35 + 0.0F), ((var32 + 8.0F) * 0.00390625F + var22), ((var33 + 0.0F) * 0.00390625F + var23));
/* 2103 */             var5.addVertexWithUV((var34 + 0.0F), (var14 + 0.0F), (var35 + 0.0F), ((var32 + 0.0F) * 0.00390625F + var22), ((var33 + 0.0F) * 0.00390625F + var23));
/*      */           } 
/*      */           
/* 2106 */           if (var14 <= 5.0F) {
/*      */             
/* 2108 */             var5.func_178960_a(var18, var19, var20, 0.8F);
/* 2109 */             var5.func_178980_d(0.0F, 1.0F, 0.0F);
/* 2110 */             var5.addVertexWithUV((var34 + 0.0F), (var14 + 4.0F - 9.765625E-4F), (var35 + 8.0F), ((var32 + 0.0F) * 0.00390625F + var22), ((var33 + 8.0F) * 0.00390625F + var23));
/* 2111 */             var5.addVertexWithUV((var34 + 8.0F), (var14 + 4.0F - 9.765625E-4F), (var35 + 8.0F), ((var32 + 8.0F) * 0.00390625F + var22), ((var33 + 8.0F) * 0.00390625F + var23));
/* 2112 */             var5.addVertexWithUV((var34 + 8.0F), (var14 + 4.0F - 9.765625E-4F), (var35 + 0.0F), ((var32 + 8.0F) * 0.00390625F + var22), ((var33 + 0.0F) * 0.00390625F + var23));
/* 2113 */             var5.addVertexWithUV((var34 + 0.0F), (var14 + 4.0F - 9.765625E-4F), (var35 + 0.0F), ((var32 + 0.0F) * 0.00390625F + var22), ((var33 + 0.0F) * 0.00390625F + var23));
/*      */           } 
/*      */           
/* 2116 */           var5.func_178960_a(var18 * 0.9F, var19 * 0.9F, var20 * 0.9F, 0.8F);
/*      */ 
/*      */           
/* 2119 */           if (var30 > -1) {
/*      */             
/* 2121 */             var5.func_178980_d(-1.0F, 0.0F, 0.0F);
/*      */             
/* 2123 */             for (int var36 = 0; var36 < 8; var36++) {
/*      */               
/* 2125 */               var5.addVertexWithUV((var34 + var36 + 0.0F), (var14 + 0.0F), (var35 + 8.0F), ((var32 + var36 + 0.5F) * 0.00390625F + var22), ((var33 + 8.0F) * 0.00390625F + var23));
/* 2126 */               var5.addVertexWithUV((var34 + var36 + 0.0F), (var14 + 4.0F), (var35 + 8.0F), ((var32 + var36 + 0.5F) * 0.00390625F + var22), ((var33 + 8.0F) * 0.00390625F + var23));
/* 2127 */               var5.addVertexWithUV((var34 + var36 + 0.0F), (var14 + 4.0F), (var35 + 0.0F), ((var32 + var36 + 0.5F) * 0.00390625F + var22), ((var33 + 0.0F) * 0.00390625F + var23));
/* 2128 */               var5.addVertexWithUV((var34 + var36 + 0.0F), (var14 + 0.0F), (var35 + 0.0F), ((var32 + var36 + 0.5F) * 0.00390625F + var22), ((var33 + 0.0F) * 0.00390625F + var23));
/*      */             } 
/*      */           } 
/*      */           
/* 2132 */           if (var30 <= 1) {
/*      */             
/* 2134 */             var5.func_178980_d(1.0F, 0.0F, 0.0F);
/*      */             
/* 2136 */             for (int var36 = 0; var36 < 8; var36++) {
/*      */               
/* 2138 */               var5.addVertexWithUV((var34 + var36 + 1.0F - 9.765625E-4F), (var14 + 0.0F), (var35 + 8.0F), ((var32 + var36 + 0.5F) * 0.00390625F + var22), ((var33 + 8.0F) * 0.00390625F + var23));
/* 2139 */               var5.addVertexWithUV((var34 + var36 + 1.0F - 9.765625E-4F), (var14 + 4.0F), (var35 + 8.0F), ((var32 + var36 + 0.5F) * 0.00390625F + var22), ((var33 + 8.0F) * 0.00390625F + var23));
/* 2140 */               var5.addVertexWithUV((var34 + var36 + 1.0F - 9.765625E-4F), (var14 + 4.0F), (var35 + 0.0F), ((var32 + var36 + 0.5F) * 0.00390625F + var22), ((var33 + 0.0F) * 0.00390625F + var23));
/* 2141 */               var5.addVertexWithUV((var34 + var36 + 1.0F - 9.765625E-4F), (var14 + 0.0F), (var35 + 0.0F), ((var32 + var36 + 0.5F) * 0.00390625F + var22), ((var33 + 0.0F) * 0.00390625F + var23));
/*      */             } 
/*      */           } 
/*      */           
/* 2145 */           var5.func_178960_a(var18 * 0.8F, var19 * 0.8F, var20 * 0.8F, 0.8F);
/*      */           
/* 2147 */           if (var31 > -1) {
/*      */             
/* 2149 */             var5.func_178980_d(0.0F, 0.0F, -1.0F);
/*      */             
/* 2151 */             for (int var36 = 0; var36 < 8; var36++) {
/*      */               
/* 2153 */               var5.addVertexWithUV((var34 + 0.0F), (var14 + 4.0F), (var35 + var36 + 0.0F), ((var32 + 0.0F) * 0.00390625F + var22), ((var33 + var36 + 0.5F) * 0.00390625F + var23));
/* 2154 */               var5.addVertexWithUV((var34 + 8.0F), (var14 + 4.0F), (var35 + var36 + 0.0F), ((var32 + 8.0F) * 0.00390625F + var22), ((var33 + var36 + 0.5F) * 0.00390625F + var23));
/* 2155 */               var5.addVertexWithUV((var34 + 8.0F), (var14 + 0.0F), (var35 + var36 + 0.0F), ((var32 + 8.0F) * 0.00390625F + var22), ((var33 + var36 + 0.5F) * 0.00390625F + var23));
/* 2156 */               var5.addVertexWithUV((var34 + 0.0F), (var14 + 0.0F), (var35 + var36 + 0.0F), ((var32 + 0.0F) * 0.00390625F + var22), ((var33 + var36 + 0.5F) * 0.00390625F + var23));
/*      */             } 
/*      */           } 
/*      */           
/* 2160 */           if (var31 <= 1) {
/*      */             
/* 2162 */             var5.func_178980_d(0.0F, 0.0F, 1.0F);
/*      */             
/* 2164 */             for (int var36 = 0; var36 < 8; var36++) {
/*      */               
/* 2166 */               var5.addVertexWithUV((var34 + 0.0F), (var14 + 4.0F), (var35 + var36 + 1.0F - 9.765625E-4F), ((var32 + 0.0F) * 0.00390625F + var22), ((var33 + var36 + 0.5F) * 0.00390625F + var23));
/* 2167 */               var5.addVertexWithUV((var34 + 8.0F), (var14 + 4.0F), (var35 + var36 + 1.0F - 9.765625E-4F), ((var32 + 8.0F) * 0.00390625F + var22), ((var33 + var36 + 0.5F) * 0.00390625F + var23));
/* 2168 */               var5.addVertexWithUV((var34 + 8.0F), (var14 + 0.0F), (var35 + var36 + 1.0F - 9.765625E-4F), ((var32 + 8.0F) * 0.00390625F + var22), ((var33 + var36 + 0.5F) * 0.00390625F + var23));
/* 2169 */               var5.addVertexWithUV((var34 + 0.0F), (var14 + 0.0F), (var35 + var36 + 1.0F - 9.765625E-4F), ((var32 + 0.0F) * 0.00390625F + var22), ((var33 + var36 + 0.5F) * 0.00390625F + var23));
/*      */             } 
/*      */           } 
/*      */           
/* 2173 */           var4.draw();
/*      */         } 
/*      */       } 
/*      */       
/* 2177 */       this.cloudRenderer.endUpdateGlList();
/*      */     } 
/*      */     
/* 2180 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 2181 */     GlStateManager.disableBlend();
/* 2182 */     GlStateManager.enableCull();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174967_a(long p_174967_1_) {
/* 2187 */     this.displayListEntitiesDirty |= this.field_174995_M.func_178516_a(p_174967_1_);
/*      */ 
/*      */ 
/*      */     
/* 2191 */     if (this.chunksToUpdateForced.size() > 0) {
/*      */       
/* 2193 */       Iterator<RenderChunk> countUpdated = this.chunksToUpdateForced.iterator();
/*      */       
/* 2195 */       while (countUpdated.hasNext()) {
/*      */         
/* 2197 */         RenderChunk updatesPerFrame = countUpdated.next();
/*      */         
/* 2199 */         if (!this.field_174995_M.func_178507_a(updatesPerFrame)) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/* 2204 */         updatesPerFrame.func_178575_a(false);
/* 2205 */         countUpdated.remove();
/* 2206 */         this.field_175009_l.remove(updatesPerFrame);
/* 2207 */         this.chunksToResortTransparency.remove(updatesPerFrame);
/*      */       } 
/*      */     } 
/*      */     
/* 2211 */     if (this.chunksToResortTransparency.size() > 0) {
/*      */       
/* 2213 */       Iterator<RenderChunk> countUpdated = this.chunksToResortTransparency.iterator();
/*      */       
/* 2215 */       if (countUpdated.hasNext()) {
/*      */         
/* 2217 */         RenderChunk updatesPerFrame = countUpdated.next();
/*      */         
/* 2219 */         if (this.field_174995_M.func_178509_c(updatesPerFrame))
/*      */         {
/* 2221 */           countUpdated.remove();
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 2226 */     int var8 = 0;
/* 2227 */     int var9 = Config.getUpdatesPerFrame();
/* 2228 */     int maxUpdatesPerFrame = var9 * 2;
/* 2229 */     Iterator<RenderChunk> var3 = this.field_175009_l.iterator();
/*      */     
/* 2231 */     while (var3.hasNext()) {
/*      */       
/* 2233 */       RenderChunk var4 = var3.next();
/*      */       
/* 2235 */       if (!this.field_174995_M.func_178507_a(var4)) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/* 2240 */       var4.func_178575_a(false);
/* 2241 */       var3.remove();
/*      */       
/* 2243 */       if (var4.func_178571_g().func_178489_a() && var9 < maxUpdatesPerFrame)
/*      */       {
/* 2245 */         var9++;
/*      */       }
/*      */       
/* 2248 */       var8++;
/*      */       
/* 2250 */       if (var8 >= var9) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_180449_a(Entity p_180449_1_, float p_180449_2_) {
/* 2259 */     Tessellator var3 = Tessellator.getInstance();
/* 2260 */     WorldRenderer var4 = var3.getWorldRenderer();
/* 2261 */     WorldBorder var5 = this.theWorld.getWorldBorder();
/* 2262 */     double var6 = (this.mc.gameSettings.renderDistanceChunks * 16);
/*      */     
/* 2264 */     if (p_180449_1_.posX >= var5.maxX() - var6 || p_180449_1_.posX <= var5.minX() + var6 || p_180449_1_.posZ >= var5.maxZ() - var6 || p_180449_1_.posZ <= var5.minZ() + var6) {
/*      */       
/* 2266 */       double var8 = 1.0D - var5.getClosestDistance(p_180449_1_) / var6;
/* 2267 */       var8 = Math.pow(var8, 4.0D);
/* 2268 */       double var10 = p_180449_1_.lastTickPosX + (p_180449_1_.posX - p_180449_1_.lastTickPosX) * p_180449_2_;
/* 2269 */       double var12 = p_180449_1_.lastTickPosY + (p_180449_1_.posY - p_180449_1_.lastTickPosY) * p_180449_2_;
/* 2270 */       double var14 = p_180449_1_.lastTickPosZ + (p_180449_1_.posZ - p_180449_1_.lastTickPosZ) * p_180449_2_;
/* 2271 */       GlStateManager.enableBlend();
/* 2272 */       GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
/* 2273 */       this.renderEngine.bindTexture(field_175006_g);
/* 2274 */       GlStateManager.depthMask(false);
/* 2275 */       GlStateManager.pushMatrix();
/* 2276 */       int var16 = var5.getStatus().func_177766_a();
/* 2277 */       float var17 = (var16 >> 16 & 0xFF) / 255.0F;
/* 2278 */       float var18 = (var16 >> 8 & 0xFF) / 255.0F;
/* 2279 */       float var19 = (var16 & 0xFF) / 255.0F;
/* 2280 */       GlStateManager.color(var17, var18, var19, (float)var8);
/* 2281 */       GlStateManager.doPolygonOffset(-3.0F, -3.0F);
/* 2282 */       GlStateManager.enablePolygonOffset();
/* 2283 */       GlStateManager.alphaFunc(516, 0.1F);
/* 2284 */       GlStateManager.enableAlpha();
/* 2285 */       GlStateManager.disableCull();
/* 2286 */       float var20 = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F;
/* 2287 */       float var21 = 0.0F;
/* 2288 */       float var22 = 0.0F;
/* 2289 */       float var23 = 128.0F;
/* 2290 */       var4.startDrawingQuads();
/* 2291 */       var4.setTranslation(-var10, -var12, -var14);
/* 2292 */       var4.markDirty();
/* 2293 */       double var24 = Math.max(MathHelper.floor_double(var14 - var6), var5.minZ());
/* 2294 */       double var26 = Math.min(MathHelper.ceiling_double_int(var14 + var6), var5.maxZ());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2300 */       if (var10 > var5.maxX() - var6) {
/*      */         
/* 2302 */         float var28 = 0.0F;
/*      */         
/* 2304 */         for (double var29 = var24; var29 < var26; var28 += 0.5F) {
/*      */           
/* 2306 */           double var31 = Math.min(1.0D, var26 - var29);
/* 2307 */           float var33 = (float)var31 * 0.5F;
/* 2308 */           var4.addVertexWithUV(var5.maxX(), 256.0D, var29, (var20 + var28), (var20 + 0.0F));
/* 2309 */           var4.addVertexWithUV(var5.maxX(), 256.0D, var29 + var31, (var20 + var33 + var28), (var20 + 0.0F));
/* 2310 */           var4.addVertexWithUV(var5.maxX(), 0.0D, var29 + var31, (var20 + var33 + var28), (var20 + 128.0F));
/* 2311 */           var4.addVertexWithUV(var5.maxX(), 0.0D, var29, (var20 + var28), (var20 + 128.0F));
/* 2312 */           var29++;
/*      */         } 
/*      */       } 
/*      */       
/* 2316 */       if (var10 < var5.minX() + var6) {
/*      */         
/* 2318 */         float var28 = 0.0F;
/*      */         
/* 2320 */         for (double var29 = var24; var29 < var26; var28 += 0.5F) {
/*      */           
/* 2322 */           double var31 = Math.min(1.0D, var26 - var29);
/* 2323 */           float var33 = (float)var31 * 0.5F;
/* 2324 */           var4.addVertexWithUV(var5.minX(), 256.0D, var29, (var20 + var28), (var20 + 0.0F));
/* 2325 */           var4.addVertexWithUV(var5.minX(), 256.0D, var29 + var31, (var20 + var33 + var28), (var20 + 0.0F));
/* 2326 */           var4.addVertexWithUV(var5.minX(), 0.0D, var29 + var31, (var20 + var33 + var28), (var20 + 128.0F));
/* 2327 */           var4.addVertexWithUV(var5.minX(), 0.0D, var29, (var20 + var28), (var20 + 128.0F));
/* 2328 */           var29++;
/*      */         } 
/*      */       } 
/*      */       
/* 2332 */       var24 = Math.max(MathHelper.floor_double(var10 - var6), var5.minX());
/* 2333 */       var26 = Math.min(MathHelper.ceiling_double_int(var10 + var6), var5.maxX());
/*      */       
/* 2335 */       if (var14 > var5.maxZ() - var6) {
/*      */         
/* 2337 */         float var28 = 0.0F;
/*      */         
/* 2339 */         for (double var29 = var24; var29 < var26; var28 += 0.5F) {
/*      */           
/* 2341 */           double var31 = Math.min(1.0D, var26 - var29);
/* 2342 */           float var33 = (float)var31 * 0.5F;
/* 2343 */           var4.addVertexWithUV(var29, 256.0D, var5.maxZ(), (var20 + var28), (var20 + 0.0F));
/* 2344 */           var4.addVertexWithUV(var29 + var31, 256.0D, var5.maxZ(), (var20 + var33 + var28), (var20 + 0.0F));
/* 2345 */           var4.addVertexWithUV(var29 + var31, 0.0D, var5.maxZ(), (var20 + var33 + var28), (var20 + 128.0F));
/* 2346 */           var4.addVertexWithUV(var29, 0.0D, var5.maxZ(), (var20 + var28), (var20 + 128.0F));
/* 2347 */           var29++;
/*      */         } 
/*      */       } 
/*      */       
/* 2351 */       if (var14 < var5.minZ() + var6) {
/*      */         
/* 2353 */         float var28 = 0.0F;
/*      */         
/* 2355 */         for (double var29 = var24; var29 < var26; var28 += 0.5F) {
/*      */           
/* 2357 */           double var31 = Math.min(1.0D, var26 - var29);
/* 2358 */           float var33 = (float)var31 * 0.5F;
/* 2359 */           var4.addVertexWithUV(var29, 256.0D, var5.minZ(), (var20 + var28), (var20 + 0.0F));
/* 2360 */           var4.addVertexWithUV(var29 + var31, 256.0D, var5.minZ(), (var20 + var33 + var28), (var20 + 0.0F));
/* 2361 */           var4.addVertexWithUV(var29 + var31, 0.0D, var5.minZ(), (var20 + var33 + var28), (var20 + 128.0F));
/* 2362 */           var4.addVertexWithUV(var29, 0.0D, var5.minZ(), (var20 + var28), (var20 + 128.0F));
/* 2363 */           var29++;
/*      */         } 
/*      */       } 
/*      */       
/* 2367 */       var3.draw();
/* 2368 */       var4.setTranslation(0.0D, 0.0D, 0.0D);
/* 2369 */       GlStateManager.enableCull();
/* 2370 */       GlStateManager.disableAlpha();
/* 2371 */       GlStateManager.doPolygonOffset(0.0F, 0.0F);
/* 2372 */       GlStateManager.disablePolygonOffset();
/* 2373 */       GlStateManager.enableAlpha();
/* 2374 */       GlStateManager.disableBlend();
/* 2375 */       GlStateManager.popMatrix();
/* 2376 */       GlStateManager.depthMask(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_180443_s() {
/* 2382 */     GlStateManager.tryBlendFuncSeparate(774, 768, 1, 0);
/* 2383 */     GlStateManager.enableBlend();
/* 2384 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
/* 2385 */     GlStateManager.doPolygonOffset(-3.0F, -3.0F);
/* 2386 */     GlStateManager.enablePolygonOffset();
/* 2387 */     GlStateManager.alphaFunc(516, 0.1F);
/* 2388 */     GlStateManager.enableAlpha();
/* 2389 */     GlStateManager.pushMatrix();
/*      */     
/* 2391 */     if (Config.isShaders())
/*      */     {
/* 2393 */       ShadersRender.beginBlockDamage();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_174969_t() {
/* 2399 */     GlStateManager.disableAlpha();
/* 2400 */     GlStateManager.doPolygonOffset(0.0F, 0.0F);
/* 2401 */     GlStateManager.disablePolygonOffset();
/* 2402 */     GlStateManager.enableAlpha();
/* 2403 */     GlStateManager.depthMask(true);
/* 2404 */     GlStateManager.popMatrix();
/*      */     
/* 2406 */     if (Config.isShaders())
/*      */     {
/* 2408 */       ShadersRender.endBlockDamage();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174981_a(Tessellator p_174981_1_, WorldRenderer p_174981_2_, Entity p_174981_3_, float p_174981_4_) {
/* 2414 */     double var5 = p_174981_3_.lastTickPosX + (p_174981_3_.posX - p_174981_3_.lastTickPosX) * p_174981_4_;
/* 2415 */     double var7 = p_174981_3_.lastTickPosY + (p_174981_3_.posY - p_174981_3_.lastTickPosY) * p_174981_4_;
/* 2416 */     double var9 = p_174981_3_.lastTickPosZ + (p_174981_3_.posZ - p_174981_3_.lastTickPosZ) * p_174981_4_;
/*      */     
/* 2418 */     if (!this.damagedBlocks.isEmpty()) {
/*      */       
/* 2420 */       this.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
/* 2421 */       func_180443_s();
/* 2422 */       p_174981_2_.startDrawingQuads();
/* 2423 */       p_174981_2_.setVertexFormat(DefaultVertexFormats.field_176600_a);
/* 2424 */       p_174981_2_.setTranslation(-var5, -var7, -var9);
/* 2425 */       p_174981_2_.markDirty();
/* 2426 */       Iterator<DestroyBlockProgress> var11 = this.damagedBlocks.values().iterator();
/*      */       
/* 2428 */       while (var11.hasNext()) {
/*      */         boolean renderBreaking;
/* 2430 */         DestroyBlockProgress var12 = var11.next();
/* 2431 */         BlockPos var13 = var12.func_180246_b();
/* 2432 */         double var14 = var13.getX() - var5;
/* 2433 */         double var16 = var13.getY() - var7;
/* 2434 */         double var18 = var13.getZ() - var9;
/* 2435 */         Block var20 = this.theWorld.getBlockState(var13).getBlock();
/*      */ 
/*      */         
/* 2438 */         if (Reflector.ForgeTileEntity_canRenderBreaking.exists()) {
/*      */           
/* 2440 */           boolean var22 = !(!(var20 instanceof net.minecraft.block.BlockChest) && !(var20 instanceof net.minecraft.block.BlockEnderChest) && !(var20 instanceof net.minecraft.block.BlockSign) && !(var20 instanceof net.minecraft.block.BlockSkull));
/*      */           
/* 2442 */           if (!var22) {
/*      */             
/* 2444 */             TileEntity var23 = this.theWorld.getTileEntity(var13);
/*      */             
/* 2446 */             if (var23 != null)
/*      */             {
/* 2448 */               var22 = Reflector.callBoolean(var23, Reflector.ForgeTileEntity_canRenderBreaking, new Object[0]);
/*      */             }
/*      */           } 
/*      */           
/* 2452 */           renderBreaking = !var22;
/*      */         }
/*      */         else {
/*      */           
/* 2456 */           renderBreaking = (!(var20 instanceof net.minecraft.block.BlockChest) && !(var20 instanceof net.minecraft.block.BlockEnderChest) && !(var20 instanceof net.minecraft.block.BlockSign) && !(var20 instanceof net.minecraft.block.BlockSkull));
/*      */         } 
/*      */         
/* 2459 */         if (renderBreaking) {
/*      */           
/* 2461 */           if (var14 * var14 + var16 * var16 + var18 * var18 > 1024.0D) {
/*      */             
/* 2463 */             var11.remove();
/*      */             
/*      */             continue;
/*      */           } 
/* 2467 */           IBlockState var21 = this.theWorld.getBlockState(var13);
/*      */           
/* 2469 */           if (var21.getBlock().getMaterial() != Material.air) {
/*      */             
/* 2471 */             int var221 = var12.getPartialBlockDamage();
/* 2472 */             TextureAtlasSprite var231 = this.destroyBlockIcons[var221];
/* 2473 */             BlockRendererDispatcher var24 = this.mc.getBlockRendererDispatcher();
/* 2474 */             var24.func_175020_a(var21, var13, var231, (IBlockAccess)this.theWorld);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2480 */       p_174981_1_.draw();
/* 2481 */       p_174981_2_.setTranslation(0.0D, 0.0D, 0.0D);
/* 2482 */       func_174969_t();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawSelectionBox(EntityPlayer p_72731_1_, MovingObjectPosition p_72731_2_, int p_72731_3_, float p_72731_4_) {
/* 2491 */     if (p_72731_3_ == 0 && p_72731_2_.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
/*      */       
/* 2493 */       GlStateManager.enableBlend();
/* 2494 */       GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
/* 2495 */       GlStateManager.color(0.0F, 0.0F, 0.0F, 0.4F);
/* 2496 */       GL11.glLineWidth(2.0F);
/* 2497 */       GlStateManager.func_179090_x();
/*      */       
/* 2499 */       if (Config.isShaders())
/*      */       {
/* 2501 */         Shaders.disableTexture2D();
/*      */       }
/*      */       
/* 2504 */       GlStateManager.depthMask(false);
/* 2505 */       float var5 = 0.002F;
/* 2506 */       BlockPos var6 = p_72731_2_.func_178782_a();
/* 2507 */       Block var7 = this.theWorld.getBlockState(var6).getBlock();
/*      */       
/* 2509 */       if (var7.getMaterial() != Material.air && this.theWorld.getWorldBorder().contains(var6)) {
/*      */         
/* 2511 */         var7.setBlockBoundsBasedOnState((IBlockAccess)this.theWorld, var6);
/* 2512 */         double var8 = p_72731_1_.lastTickPosX + (p_72731_1_.posX - p_72731_1_.lastTickPosX) * p_72731_4_;
/* 2513 */         double var10 = p_72731_1_.lastTickPosY + (p_72731_1_.posY - p_72731_1_.lastTickPosY) * p_72731_4_;
/* 2514 */         double var12 = p_72731_1_.lastTickPosZ + (p_72731_1_.posZ - p_72731_1_.lastTickPosZ) * p_72731_4_;
/* 2515 */         drawOutlinedBoundingBox(var7.getSelectedBoundingBox((World)this.theWorld, var6).expand(0.0020000000949949026D, 0.0020000000949949026D, 0.0020000000949949026D).offset(-var8, -var10, -var12), -1);
/*      */       } 
/*      */       
/* 2518 */       GlStateManager.depthMask(true);
/* 2519 */       GlStateManager.func_179098_w();
/*      */       
/* 2521 */       if (Config.isShaders())
/*      */       {
/* 2523 */         Shaders.enableTexture2D();
/*      */       }
/*      */       
/* 2526 */       GlStateManager.disableBlend();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawOutlinedBoundingBox(AxisAlignedBB p_147590_0_, int p_147590_1_) {
/* 2535 */     Tessellator var2 = Tessellator.getInstance();
/* 2536 */     WorldRenderer var3 = var2.getWorldRenderer();
/* 2537 */     var3.startDrawing(3);
/*      */     
/* 2539 */     if (p_147590_1_ != -1)
/*      */     {
/* 2541 */       var3.func_178991_c(p_147590_1_);
/*      */     }
/*      */     
/* 2544 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.minY, p_147590_0_.minZ);
/* 2545 */     var3.addVertex(p_147590_0_.maxX, p_147590_0_.minY, p_147590_0_.minZ);
/* 2546 */     var3.addVertex(p_147590_0_.maxX, p_147590_0_.minY, p_147590_0_.maxZ);
/* 2547 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.minY, p_147590_0_.maxZ);
/* 2548 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.minY, p_147590_0_.minZ);
/* 2549 */     var2.draw();
/* 2550 */     var3.startDrawing(3);
/*      */     
/* 2552 */     if (p_147590_1_ != -1)
/*      */     {
/* 2554 */       var3.func_178991_c(p_147590_1_);
/*      */     }
/*      */     
/* 2557 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.maxY, p_147590_0_.minZ);
/* 2558 */     var3.addVertex(p_147590_0_.maxX, p_147590_0_.maxY, p_147590_0_.minZ);
/* 2559 */     var3.addVertex(p_147590_0_.maxX, p_147590_0_.maxY, p_147590_0_.maxZ);
/* 2560 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.maxY, p_147590_0_.maxZ);
/* 2561 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.maxY, p_147590_0_.minZ);
/* 2562 */     var2.draw();
/* 2563 */     var3.startDrawing(1);
/*      */     
/* 2565 */     if (p_147590_1_ != -1)
/*      */     {
/* 2567 */       var3.func_178991_c(p_147590_1_);
/*      */     }
/*      */     
/* 2570 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.minY, p_147590_0_.minZ);
/* 2571 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.maxY, p_147590_0_.minZ);
/* 2572 */     var3.addVertex(p_147590_0_.maxX, p_147590_0_.minY, p_147590_0_.minZ);
/* 2573 */     var3.addVertex(p_147590_0_.maxX, p_147590_0_.maxY, p_147590_0_.minZ);
/* 2574 */     var3.addVertex(p_147590_0_.maxX, p_147590_0_.minY, p_147590_0_.maxZ);
/* 2575 */     var3.addVertex(p_147590_0_.maxX, p_147590_0_.maxY, p_147590_0_.maxZ);
/* 2576 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.minY, p_147590_0_.maxZ);
/* 2577 */     var3.addVertex(p_147590_0_.minX, p_147590_0_.maxY, p_147590_0_.maxZ);
/* 2578 */     var2.draw();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void markBlocksForUpdate(int p_72725_1_, int p_72725_2_, int p_72725_3_, int p_72725_4_, int p_72725_5_, int p_72725_6_) {
/* 2586 */     this.field_175008_n.func_178162_a(p_72725_1_, p_72725_2_, p_72725_3_, p_72725_4_, p_72725_5_, p_72725_6_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void markBlockForUpdate(BlockPos pos) {
/* 2591 */     int var2 = pos.getX();
/* 2592 */     int var3 = pos.getY();
/* 2593 */     int var4 = pos.getZ();
/* 2594 */     markBlocksForUpdate(var2 - 1, var3 - 1, var4 - 1, var2 + 1, var3 + 1, var4 + 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void notifyLightSet(BlockPos pos) {
/* 2599 */     int var2 = pos.getX();
/* 2600 */     int var3 = pos.getY();
/* 2601 */     int var4 = pos.getZ();
/* 2602 */     markBlocksForUpdate(var2 - 1, var3 - 1, var4 - 1, var2 + 1, var3 + 1, var4 + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
/* 2611 */     markBlocksForUpdate(x1 - 1, y1 - 1, z1 - 1, x2 + 1, y2 + 1, z2 + 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174961_a(String p_174961_1_, BlockPos p_174961_2_) {
/* 2616 */     ISound var3 = (ISound)this.mapSoundPositions.get(p_174961_2_);
/*      */     
/* 2618 */     if (var3 != null) {
/*      */       
/* 2620 */       this.mc.getSoundHandler().stopSound(var3);
/* 2621 */       this.mapSoundPositions.remove(p_174961_2_);
/*      */     } 
/*      */     
/* 2624 */     if (p_174961_1_ != null) {
/*      */       
/* 2626 */       ItemRecord var4 = ItemRecord.getRecord(p_174961_1_);
/*      */       
/* 2628 */       if (var4 != null)
/*      */       {
/* 2630 */         this.mc.ingameGUI.setRecordPlayingMessage(var4.getRecordNameLocal());
/*      */       }
/*      */       
/* 2633 */       ResourceLocation resource = null;
/*      */       
/* 2635 */       if (Reflector.ForgeItemRecord_getRecordResource.exists() && var4 != null)
/*      */       {
/* 2637 */         resource = (ResourceLocation)Reflector.call(var4, Reflector.ForgeItemRecord_getRecordResource, new Object[] { p_174961_1_ });
/*      */       }
/*      */       
/* 2640 */       if (resource == null)
/*      */       {
/* 2642 */         resource = new ResourceLocation(p_174961_1_);
/*      */       }
/*      */       
/* 2645 */       PositionedSoundRecord var5 = PositionedSoundRecord.createRecordSoundAtPosition(resource, p_174961_2_.getX(), p_174961_2_.getY(), p_174961_2_.getZ());
/* 2646 */       this.mapSoundPositions.put(p_174961_2_, var5);
/* 2647 */       this.mc.getSoundHandler().playSound((ISound)var5);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSound(String soundName, double x, double y, double z, float volume, float pitch) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playSoundToNearExcept(EntityPlayer except, String soundName, double x, double y, double z, float volume, float pitch) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_180442_a(int p_180442_1_, boolean p_180442_2_, final double p_180442_3_, final double p_180442_5_, final double p_180442_7_, double p_180442_9_, double p_180442_11_, double p_180442_13_, int... p_180442_15_) {
/*      */     try {
/* 2665 */       func_174974_b(p_180442_1_, p_180442_2_, p_180442_3_, p_180442_5_, p_180442_7_, p_180442_9_, p_180442_11_, p_180442_13_, p_180442_15_);
/*      */     }
/* 2667 */     catch (Throwable var19) {
/*      */       
/* 2669 */       CrashReport var17 = CrashReport.makeCrashReport(var19, "Exception while adding particle");
/* 2670 */       CrashReportCategory var18 = var17.makeCategory("Particle being added");
/* 2671 */       var18.addCrashSection("ID", Integer.valueOf(p_180442_1_));
/*      */       
/* 2673 */       if (p_180442_15_ != null)
/*      */       {
/* 2675 */         var18.addCrashSection("Parameters", p_180442_15_);
/*      */       }
/*      */       
/* 2678 */       var18.addCrashSectionCallable("Position", new Callable()
/*      */           {
/*      */             public String call()
/*      */             {
/* 2682 */               return CrashReportCategory.getCoordinateInfo(p_180442_3_, p_180442_5_, p_180442_7_);
/*      */             }
/*      */           });
/* 2685 */       throw new ReportedException(var17);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_174972_a(EnumParticleTypes p_174972_1_, double p_174972_2_, double p_174972_4_, double p_174972_6_, double p_174972_8_, double p_174972_10_, double p_174972_12_, int... p_174972_14_) {
/* 2691 */     func_180442_a(p_174972_1_.func_179348_c(), p_174972_1_.func_179344_e(), p_174972_2_, p_174972_4_, p_174972_6_, p_174972_8_, p_174972_10_, p_174972_12_, p_174972_14_);
/*      */   }
/*      */ 
/*      */   
/*      */   private EntityFX func_174974_b(int p_174974_1_, boolean p_174974_2_, double p_174974_3_, double p_174974_5_, double p_174974_7_, double p_174974_9_, double p_174974_11_, double p_174974_13_, int... p_174974_15_) {
/* 2696 */     if (this.mc != null && this.mc.func_175606_aa() != null && this.mc.effectRenderer != null) {
/*      */       
/* 2698 */       int var16 = this.mc.gameSettings.particleSetting;
/*      */       
/* 2700 */       if (var16 == 1 && this.theWorld.rand.nextInt(3) == 0)
/*      */       {
/* 2702 */         var16 = 2;
/*      */       }
/*      */       
/* 2705 */       double var17 = (this.mc.func_175606_aa()).posX - p_174974_3_;
/* 2706 */       double var19 = (this.mc.func_175606_aa()).posY - p_174974_5_;
/* 2707 */       double var21 = (this.mc.func_175606_aa()).posZ - p_174974_7_;
/*      */       
/* 2709 */       if (p_174974_1_ == EnumParticleTypes.EXPLOSION_HUGE.func_179348_c() && !Config.isAnimatedExplosion())
/*      */       {
/* 2711 */         return null;
/*      */       }
/* 2713 */       if (p_174974_1_ == EnumParticleTypes.EXPLOSION_LARGE.func_179348_c() && !Config.isAnimatedExplosion())
/*      */       {
/* 2715 */         return null;
/*      */       }
/* 2717 */       if (p_174974_1_ == EnumParticleTypes.EXPLOSION_NORMAL.func_179348_c() && !Config.isAnimatedExplosion())
/*      */       {
/* 2719 */         return null;
/*      */       }
/* 2721 */       if (p_174974_1_ == EnumParticleTypes.SUSPENDED.func_179348_c() && !Config.isWaterParticles())
/*      */       {
/* 2723 */         return null;
/*      */       }
/* 2725 */       if (p_174974_1_ == EnumParticleTypes.SUSPENDED_DEPTH.func_179348_c() && !Config.isVoidParticles())
/*      */       {
/* 2727 */         return null;
/*      */       }
/* 2729 */       if (p_174974_1_ == EnumParticleTypes.SMOKE_NORMAL.func_179348_c() && !Config.isAnimatedSmoke())
/*      */       {
/* 2731 */         return null;
/*      */       }
/* 2733 */       if (p_174974_1_ == EnumParticleTypes.SMOKE_LARGE.func_179348_c() && !Config.isAnimatedSmoke())
/*      */       {
/* 2735 */         return null;
/*      */       }
/* 2737 */       if (p_174974_1_ == EnumParticleTypes.SPELL_MOB.func_179348_c() && !Config.isPotionParticles())
/*      */       {
/* 2739 */         return null;
/*      */       }
/* 2741 */       if (p_174974_1_ == EnumParticleTypes.SPELL_MOB_AMBIENT.func_179348_c() && !Config.isPotionParticles())
/*      */       {
/* 2743 */         return null;
/*      */       }
/* 2745 */       if (p_174974_1_ == EnumParticleTypes.SPELL.func_179348_c() && !Config.isPotionParticles())
/*      */       {
/* 2747 */         return null;
/*      */       }
/* 2749 */       if (p_174974_1_ == EnumParticleTypes.SPELL_INSTANT.func_179348_c() && !Config.isPotionParticles())
/*      */       {
/* 2751 */         return null;
/*      */       }
/* 2753 */       if (p_174974_1_ == EnumParticleTypes.SPELL_WITCH.func_179348_c() && !Config.isPotionParticles())
/*      */       {
/* 2755 */         return null;
/*      */       }
/* 2757 */       if (p_174974_1_ == EnumParticleTypes.PORTAL.func_179348_c() && !Config.isAnimatedPortal())
/*      */       {
/* 2759 */         return null;
/*      */       }
/* 2761 */       if (p_174974_1_ == EnumParticleTypes.FLAME.func_179348_c() && !Config.isAnimatedFlame())
/*      */       {
/* 2763 */         return null;
/*      */       }
/* 2765 */       if (p_174974_1_ == EnumParticleTypes.REDSTONE.func_179348_c() && !Config.isAnimatedRedstone())
/*      */       {
/* 2767 */         return null;
/*      */       }
/* 2769 */       if (p_174974_1_ == EnumParticleTypes.DRIP_WATER.func_179348_c() && !Config.isDrippingWaterLava())
/*      */       {
/* 2771 */         return null;
/*      */       }
/* 2773 */       if (p_174974_1_ == EnumParticleTypes.DRIP_LAVA.func_179348_c() && !Config.isDrippingWaterLava())
/*      */       {
/* 2775 */         return null;
/*      */       }
/* 2777 */       if (p_174974_1_ == EnumParticleTypes.FIREWORKS_SPARK.func_179348_c() && !Config.isFireworkParticles())
/*      */       {
/* 2779 */         return null;
/*      */       }
/* 2781 */       if (p_174974_2_)
/*      */       {
/* 2783 */         return this.mc.effectRenderer.func_178927_a(p_174974_1_, p_174974_3_, p_174974_5_, p_174974_7_, p_174974_9_, p_174974_11_, p_174974_13_, p_174974_15_);
/*      */       }
/*      */ 
/*      */       
/* 2787 */       double var23 = 16.0D;
/* 2788 */       double maxDistSq = 256.0D;
/*      */       
/* 2790 */       if (p_174974_1_ == EnumParticleTypes.CRIT.func_179348_c())
/*      */       {
/* 2792 */         maxDistSq = 38416.0D;
/*      */       }
/*      */       
/* 2795 */       if (var17 * var17 + var19 * var19 + var21 * var21 > maxDistSq)
/*      */       {
/* 2797 */         return null;
/*      */       }
/* 2799 */       if (var16 > 1)
/*      */       {
/* 2801 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 2805 */       EntityFX entityFx = this.mc.effectRenderer.func_178927_a(p_174974_1_, p_174974_3_, p_174974_5_, p_174974_7_, p_174974_9_, p_174974_11_, p_174974_13_, p_174974_15_);
/*      */       
/* 2807 */       if (p_174974_1_ == EnumParticleTypes.WATER_BUBBLE.func_179348_c())
/*      */       {
/* 2809 */         CustomColors.updateWaterFX(entityFx, (IBlockAccess)this.theWorld, p_174974_3_, p_174974_5_, p_174974_7_);
/*      */       }
/*      */       
/* 2812 */       if (p_174974_1_ == EnumParticleTypes.WATER_SPLASH.func_179348_c())
/*      */       {
/* 2814 */         CustomColors.updateWaterFX(entityFx, (IBlockAccess)this.theWorld, p_174974_3_, p_174974_5_, p_174974_7_);
/*      */       }
/*      */       
/* 2817 */       if (p_174974_1_ == EnumParticleTypes.WATER_DROP.func_179348_c())
/*      */       {
/* 2819 */         CustomColors.updateWaterFX(entityFx, (IBlockAccess)this.theWorld, p_174974_3_, p_174974_5_, p_174974_7_);
/*      */       }
/*      */       
/* 2822 */       if (p_174974_1_ == EnumParticleTypes.TOWN_AURA.func_179348_c())
/*      */       {
/* 2824 */         CustomColors.updateMyceliumFX(entityFx);
/*      */       }
/*      */       
/* 2827 */       if (p_174974_1_ == EnumParticleTypes.PORTAL.func_179348_c())
/*      */       {
/* 2829 */         CustomColors.updatePortalFX(entityFx);
/*      */       }
/*      */       
/* 2832 */       if (p_174974_1_ == EnumParticleTypes.REDSTONE.func_179348_c())
/*      */       {
/* 2834 */         CustomColors.updateReddustFX(entityFx, (IBlockAccess)this.theWorld, p_174974_3_, p_174974_5_, p_174974_7_);
/*      */       }
/*      */       
/* 2837 */       return entityFx;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2843 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityAdded(Entity entityIn) {
/* 2853 */     RandomMobs.entityLoaded(entityIn, (World)this.theWorld);
/*      */     
/* 2855 */     if (Config.isDynamicLights())
/*      */     {
/* 2857 */       DynamicLights.entityAdded(entityIn, this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityRemoved(Entity entityIn) {
/* 2867 */     if (Config.isDynamicLights())
/*      */     {
/* 2869 */       DynamicLights.entityRemoved(entityIn, this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void deleteAllDisplayLists() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_180440_a(int p_180440_1_, BlockPos p_180440_2_, int p_180440_3_) {
/* 2880 */     switch (p_180440_1_) {
/*      */       
/*      */       case 1013:
/*      */       case 1018:
/* 2884 */         if (this.mc.func_175606_aa() != null) {
/*      */           
/* 2886 */           double var4 = p_180440_2_.getX() - (this.mc.func_175606_aa()).posX;
/* 2887 */           double var6 = p_180440_2_.getY() - (this.mc.func_175606_aa()).posY;
/* 2888 */           double var8 = p_180440_2_.getZ() - (this.mc.func_175606_aa()).posZ;
/* 2889 */           double var10 = Math.sqrt(var4 * var4 + var6 * var6 + var8 * var8);
/* 2890 */           double var12 = (this.mc.func_175606_aa()).posX;
/* 2891 */           double var14 = (this.mc.func_175606_aa()).posY;
/* 2892 */           double var16 = (this.mc.func_175606_aa()).posZ;
/*      */           
/* 2894 */           if (var10 > 0.0D) {
/*      */             
/* 2896 */             var12 += var4 / var10 * 2.0D;
/* 2897 */             var14 += var6 / var10 * 2.0D;
/* 2898 */             var16 += var8 / var10 * 2.0D;
/*      */           } 
/*      */           
/* 2901 */           if (p_180440_1_ == 1013) {
/*      */             
/* 2903 */             this.theWorld.playSound(var12, var14, var16, "mob.wither.spawn", 1.0F, 1.0F, false);
/*      */             
/*      */             break;
/*      */           } 
/* 2907 */           this.theWorld.playSound(var12, var14, var16, "mob.enderdragon.end", 5.0F, 1.0F, false);
/*      */         } 
/*      */         break;
/*      */     }  } public void func_180439_a(EntityPlayer p_180439_1_, int p_180439_2_, BlockPos p_180439_3_, int p_180439_4_) { double var7, var9, var11;
/*      */     int var13, var18;
/*      */     double var32;
/*      */     int var31, var8, var39;
/*      */     Block var6;
/*      */     float var14, var15, var16;
/*      */     EnumParticleTypes var17;
/* 2917 */     Random var5 = this.theWorld.rand;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2930 */     switch (p_180439_2_) {
/*      */       
/*      */       case 1000:
/* 2933 */         this.theWorld.func_175731_a(p_180439_3_, "random.click", 1.0F, 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1001:
/* 2937 */         this.theWorld.func_175731_a(p_180439_3_, "random.click", 1.0F, 1.2F, false);
/*      */         break;
/*      */       
/*      */       case 1002:
/* 2941 */         this.theWorld.func_175731_a(p_180439_3_, "random.bow", 1.0F, 1.2F, false);
/*      */         break;
/*      */       
/*      */       case 1003:
/* 2945 */         this.theWorld.func_175731_a(p_180439_3_, "random.door_open", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         break;
/*      */       
/*      */       case 1004:
/* 2949 */         this.theWorld.func_175731_a(p_180439_3_, "random.fizz", 0.5F, 2.6F + (var5.nextFloat() - var5.nextFloat()) * 0.8F, false);
/*      */         break;
/*      */       
/*      */       case 1005:
/* 2953 */         if (Item.getItemById(p_180439_4_) instanceof ItemRecord) {
/*      */           
/* 2955 */           this.theWorld.func_175717_a(p_180439_3_, "records." + ((ItemRecord)Item.getItemById(p_180439_4_)).recordName);
/*      */           
/*      */           break;
/*      */         } 
/* 2959 */         this.theWorld.func_175717_a(p_180439_3_, null);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1006:
/* 2965 */         this.theWorld.func_175731_a(p_180439_3_, "random.door_close", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         break;
/*      */       
/*      */       case 1007:
/* 2969 */         this.theWorld.func_175731_a(p_180439_3_, "mob.ghast.charge", 10.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1008:
/* 2973 */         this.theWorld.func_175731_a(p_180439_3_, "mob.ghast.fireball", 10.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1009:
/* 2977 */         this.theWorld.func_175731_a(p_180439_3_, "mob.ghast.fireball", 2.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1010:
/* 2981 */         this.theWorld.func_175731_a(p_180439_3_, "mob.zombie.wood", 2.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1011:
/* 2985 */         this.theWorld.func_175731_a(p_180439_3_, "mob.zombie.metal", 2.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1012:
/* 2989 */         this.theWorld.func_175731_a(p_180439_3_, "mob.zombie.woodbreak", 2.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1014:
/* 2993 */         this.theWorld.func_175731_a(p_180439_3_, "mob.wither.shoot", 2.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1015:
/* 2997 */         this.theWorld.func_175731_a(p_180439_3_, "mob.bat.takeoff", 0.05F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1016:
/* 3001 */         this.theWorld.func_175731_a(p_180439_3_, "mob.zombie.infect", 2.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1017:
/* 3005 */         this.theWorld.func_175731_a(p_180439_3_, "mob.zombie.unfect", 2.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F, false);
/*      */         break;
/*      */       
/*      */       case 1020:
/* 3009 */         this.theWorld.func_175731_a(p_180439_3_, "random.anvil_break", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         break;
/*      */       
/*      */       case 1021:
/* 3013 */         this.theWorld.func_175731_a(p_180439_3_, "random.anvil_use", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         break;
/*      */       
/*      */       case 1022:
/* 3017 */         this.theWorld.func_175731_a(p_180439_3_, "random.anvil_land", 0.3F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         break;
/*      */       
/*      */       case 2000:
/* 3021 */         var31 = p_180439_4_ % 3 - 1;
/* 3022 */         var8 = p_180439_4_ / 3 % 3 - 1;
/* 3023 */         var9 = p_180439_3_.getX() + var31 * 0.6D + 0.5D;
/* 3024 */         var11 = p_180439_3_.getY() + 0.5D;
/* 3025 */         var32 = p_180439_3_.getZ() + var8 * 0.6D + 0.5D;
/*      */         
/* 3027 */         for (var39 = 0; var39 < 10; var39++) {
/*      */           
/* 3029 */           double var40 = var5.nextDouble() * 0.2D + 0.01D;
/* 3030 */           double var41 = var9 + var31 * 0.01D + (var5.nextDouble() - 0.5D) * var8 * 0.5D;
/* 3031 */           double var25 = var11 + (var5.nextDouble() - 0.5D) * 0.5D;
/* 3032 */           double var27 = var32 + var8 * 0.01D + (var5.nextDouble() - 0.5D) * var31 * 0.5D;
/* 3033 */           double var42 = var31 * var40 + var5.nextGaussian() * 0.01D;
/* 3034 */           double var26 = -0.03D + var5.nextGaussian() * 0.01D;
/* 3035 */           double var28 = var8 * var40 + var5.nextGaussian() * 0.01D;
/* 3036 */           func_174972_a(EnumParticleTypes.SMOKE_NORMAL, var41, var25, var27, var42, var26, var28, new int[0]);
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 2001:
/* 3042 */         var6 = Block.getBlockById(p_180439_4_ & 0xFFF);
/*      */         
/* 3044 */         if (var6.getMaterial() != Material.air)
/*      */         {
/* 3046 */           this.mc.getSoundHandler().playSound((ISound)new PositionedSoundRecord(new ResourceLocation(var6.stepSound.getBreakSound()), (var6.stepSound.getVolume() + 1.0F) / 2.0F, var6.stepSound.getFrequency() * 0.8F, p_180439_3_.getX() + 0.5F, p_180439_3_.getY() + 0.5F, p_180439_3_.getZ() + 0.5F));
/*      */         }
/*      */         
/* 3049 */         this.mc.effectRenderer.func_180533_a(p_180439_3_, var6.getStateFromMeta(p_180439_4_ >> 12 & 0xFF));
/*      */         break;
/*      */       
/*      */       case 2002:
/* 3053 */         var7 = p_180439_3_.getX();
/* 3054 */         var9 = p_180439_3_.getY();
/* 3055 */         var11 = p_180439_3_.getZ();
/*      */         
/* 3057 */         for (var13 = 0; var13 < 8; var13++) {
/*      */           
/* 3059 */           func_174972_a(EnumParticleTypes.ITEM_CRACK, var7, var9, var11, var5.nextGaussian() * 0.15D, var5.nextDouble() * 0.2D, var5.nextGaussian() * 0.15D, new int[] { Item.getIdFromItem((Item)Items.potionitem), p_180439_4_ });
/*      */         } 
/*      */         
/* 3062 */         var13 = Items.potionitem.getColorFromDamage(p_180439_4_);
/* 3063 */         var14 = (var13 >> 16 & 0xFF) / 255.0F;
/* 3064 */         var15 = (var13 >> 8 & 0xFF) / 255.0F;
/* 3065 */         var16 = (var13 >> 0 & 0xFF) / 255.0F;
/* 3066 */         var17 = EnumParticleTypes.SPELL;
/*      */         
/* 3068 */         if (Items.potionitem.isEffectInstant(p_180439_4_))
/*      */         {
/* 3070 */           var17 = EnumParticleTypes.SPELL_INSTANT;
/*      */         }
/*      */         
/* 3073 */         for (var18 = 0; var18 < 100; var18++) {
/*      */           
/* 3075 */           double var19 = var5.nextDouble() * 4.0D;
/* 3076 */           double var21 = var5.nextDouble() * Math.PI * 2.0D;
/* 3077 */           double var23 = Math.cos(var21) * var19;
/* 3078 */           double var25 = 0.01D + var5.nextDouble() * 0.5D;
/* 3079 */           double var27 = Math.sin(var21) * var19;
/* 3080 */           EntityFX var29 = func_174974_b(var17.func_179348_c(), var17.func_179344_e(), var7 + var23 * 0.1D, var9 + 0.3D, var11 + var27 * 0.1D, var23, var25, var27, new int[0]);
/*      */           
/* 3082 */           if (var29 != null) {
/*      */             
/* 3084 */             float var30 = 0.75F + var5.nextFloat() * 0.25F;
/* 3085 */             var29.setRBGColorF(var14 * var30, var15 * var30, var16 * var30);
/* 3086 */             var29.multiplyVelocity((float)var19);
/*      */           } 
/*      */         } 
/*      */         
/* 3090 */         this.theWorld.func_175731_a(p_180439_3_, "game.potion.smash", 1.0F, this.theWorld.rand.nextFloat() * 0.1F + 0.9F, false);
/*      */         break;
/*      */       
/*      */       case 2003:
/* 3094 */         var7 = p_180439_3_.getX() + 0.5D;
/* 3095 */         var9 = p_180439_3_.getY();
/* 3096 */         var11 = p_180439_3_.getZ() + 0.5D;
/*      */         
/* 3098 */         for (var13 = 0; var13 < 8; var13++) {
/*      */           
/* 3100 */           func_174972_a(EnumParticleTypes.ITEM_CRACK, var7, var9, var11, var5.nextGaussian() * 0.15D, var5.nextDouble() * 0.2D, var5.nextGaussian() * 0.15D, new int[] { Item.getIdFromItem(Items.ender_eye) });
/*      */         } 
/*      */         
/* 3103 */         for (var32 = 0.0D; var32 < 6.283185307179586D; var32 += 0.15707963267948966D) {
/*      */           
/* 3105 */           func_174972_a(EnumParticleTypes.PORTAL, var7 + Math.cos(var32) * 5.0D, var9 - 0.4D, var11 + Math.sin(var32) * 5.0D, Math.cos(var32) * -5.0D, 0.0D, Math.sin(var32) * -5.0D, new int[0]);
/* 3106 */           func_174972_a(EnumParticleTypes.PORTAL, var7 + Math.cos(var32) * 5.0D, var9 - 0.4D, var11 + Math.sin(var32) * 5.0D, Math.cos(var32) * -7.0D, 0.0D, Math.sin(var32) * -7.0D, new int[0]);
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 2004:
/* 3112 */         for (var18 = 0; var18 < 20; var18++) {
/*      */           
/* 3114 */           double var19 = p_180439_3_.getX() + 0.5D + (this.theWorld.rand.nextFloat() - 0.5D) * 2.0D;
/* 3115 */           double var21 = p_180439_3_.getY() + 0.5D + (this.theWorld.rand.nextFloat() - 0.5D) * 2.0D;
/* 3116 */           double var23 = p_180439_3_.getZ() + 0.5D + (this.theWorld.rand.nextFloat() - 0.5D) * 2.0D;
/* 3117 */           this.theWorld.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var19, var21, var23, 0.0D, 0.0D, 0.0D, new int[0]);
/* 3118 */           this.theWorld.spawnParticle(EnumParticleTypes.FLAME, var19, var21, var23, 0.0D, 0.0D, 0.0D, new int[0]);
/*      */         } 
/*      */         return;
/*      */ 
/*      */       
/*      */       case 2005:
/* 3124 */         ItemDye.func_180617_a((World)this.theWorld, p_180439_3_, p_180439_4_);
/*      */         break;
/*      */     }  }
/*      */ 
/*      */   
/*      */   public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
/* 3130 */     if (progress >= 0 && progress < 10) {
/*      */       
/* 3132 */       DestroyBlockProgress var4 = (DestroyBlockProgress)this.damagedBlocks.get(Integer.valueOf(breakerId));
/*      */       
/* 3134 */       if (var4 == null || var4.func_180246_b().getX() != pos.getX() || var4.func_180246_b().getY() != pos.getY() || var4.func_180246_b().getZ() != pos.getZ()) {
/*      */         
/* 3136 */         var4 = new DestroyBlockProgress(breakerId, pos);
/* 3137 */         this.damagedBlocks.put(Integer.valueOf(breakerId), var4);
/*      */       } 
/*      */       
/* 3140 */       var4.setPartialBlockDamage(progress);
/* 3141 */       var4.setCloudUpdateTick(this.cloudTickCounter);
/*      */     }
/*      */     else {
/*      */       
/* 3145 */       this.damagedBlocks.remove(Integer.valueOf(breakerId));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174979_m() {
/* 3151 */     this.displayListEntitiesDirty = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void resetClouds() {
/* 3156 */     this.cloudRenderer.reset();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCountRenderers() {
/* 3161 */     return this.field_175008_n.field_178164_f.length;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCountActiveRenderers() {
/* 3166 */     return this.glRenderLists.size();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCountEntitiesRendered() {
/* 3171 */     return this.countEntitiesRendered;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCountTileEntitiesRendered() {
/* 3176 */     return this.countTileEntitiesRendered;
/*      */   }
/*      */ 
/*      */   
/*      */   public RenderChunk getRenderChunk(BlockPos pos) {
/* 3181 */     return this.field_175008_n.func_178161_a(pos);
/*      */   }
/*      */ 
/*      */   
/*      */   public RenderChunk getRenderChunk(RenderChunk renderChunk, EnumFacing facing) {
/* 3186 */     if (renderChunk == null)
/*      */     {
/* 3188 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 3192 */     BlockPos pos = renderChunk.getPositionOffset16(facing);
/* 3193 */     return this.field_175008_n.func_178161_a(pos);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public WorldClient getWorld() {
/* 3199 */     return this.theWorld;
/*      */   }
/*      */ 
/*      */   
/*      */   public static class ContainerLocalRenderInformation
/*      */   {
/*      */     final RenderChunk field_178036_a;
/*      */     final EnumFacing field_178034_b;
/*      */     final Set field_178035_c;
/*      */     final int field_178032_d;
/*      */     
/*      */     public ContainerLocalRenderInformation(RenderChunk p_i46248_2_, EnumFacing p_i46248_3_, int p_i46248_4_) {
/* 3211 */       this.field_178035_c = EnumSet.noneOf(EnumFacing.class);
/* 3212 */       this.field_178036_a = p_i46248_2_;
/* 3213 */       this.field_178034_b = p_i46248_3_;
/* 3214 */       this.field_178032_d = p_i46248_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     ContainerLocalRenderInformation(RenderChunk p_i46249_2_, EnumFacing p_i46249_3_, int p_i46249_4_, Object p_i46249_5_) {
/* 3219 */       this(p_i46249_2_, p_i46249_3_, p_i46249_4_);
/*      */     }
/*      */   }
/*      */   
/*      */   static final class SwitchEnumUseage
/*      */   {
/* 3225 */     static final int[] field_178037_a = new int[(VertexFormatElement.EnumUseage.values()).length];
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 3230 */         field_178037_a[VertexFormatElement.EnumUseage.POSITION.ordinal()] = 1;
/*      */       }
/* 3232 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3239 */         field_178037_a[VertexFormatElement.EnumUseage.UV.ordinal()] = 2;
/*      */       }
/* 3241 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 3248 */         field_178037_a[VertexFormatElement.EnumUseage.COLOR.ordinal()] = 3;
/*      */       }
/* 3250 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\RenderGlobal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */