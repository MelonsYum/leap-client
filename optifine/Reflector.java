/*     */ package optifine;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.client.renderer.tileentity.RenderItemFrame;
/*     */ import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
/*     */ import net.minecraft.client.renderer.vertex.VertexFormatElement;
/*     */ import net.minecraft.client.resources.model.ModelManager;
/*     */ import net.minecraft.client.resources.model.ModelRotation;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemRecord;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ 
/*     */ public class Reflector
/*     */ {
/*  36 */   public static ReflectorClass MinecraftForge = new ReflectorClass("net.minecraftforge.common.MinecraftForge");
/*  37 */   public static ReflectorField MinecraftForge_EVENT_BUS = new ReflectorField(MinecraftForge, "EVENT_BUS");
/*  38 */   public static ReflectorClass ForgeHooks = new ReflectorClass("net.minecraftforge.common.ForgeHooks");
/*  39 */   public static ReflectorMethod ForgeHooks_onLivingSetAttackTarget = new ReflectorMethod(ForgeHooks, "onLivingSetAttackTarget");
/*  40 */   public static ReflectorMethod ForgeHooks_onLivingUpdate = new ReflectorMethod(ForgeHooks, "onLivingUpdate");
/*  41 */   public static ReflectorMethod ForgeHooks_onLivingAttack = new ReflectorMethod(ForgeHooks, "onLivingAttack");
/*  42 */   public static ReflectorMethod ForgeHooks_onLivingHurt = new ReflectorMethod(ForgeHooks, "onLivingHurt");
/*  43 */   public static ReflectorMethod ForgeHooks_onLivingDeath = new ReflectorMethod(ForgeHooks, "onLivingDeath");
/*  44 */   public static ReflectorMethod ForgeHooks_onLivingDrops = new ReflectorMethod(ForgeHooks, "onLivingDrops");
/*  45 */   public static ReflectorMethod ForgeHooks_onLivingFall = new ReflectorMethod(ForgeHooks, "onLivingFall");
/*  46 */   public static ReflectorMethod ForgeHooks_onLivingJump = new ReflectorMethod(ForgeHooks, "onLivingJump");
/*  47 */   public static ReflectorClass MinecraftForgeClient = new ReflectorClass("net.minecraftforge.client.MinecraftForgeClient");
/*  48 */   public static ReflectorMethod MinecraftForgeClient_getRenderPass = new ReflectorMethod(MinecraftForgeClient, "getRenderPass");
/*  49 */   public static ReflectorMethod MinecraftForgeClient_onRebuildChunk = new ReflectorMethod(MinecraftForgeClient, "onRebuildChunk");
/*  50 */   public static ReflectorClass ForgeHooksClient = new ReflectorClass("net.minecraftforge.client.ForgeHooksClient");
/*  51 */   public static ReflectorMethod ForgeHooksClient_onDrawBlockHighlight = new ReflectorMethod(ForgeHooksClient, "onDrawBlockHighlight");
/*  52 */   public static ReflectorMethod ForgeHooksClient_orientBedCamera = new ReflectorMethod(ForgeHooksClient, "orientBedCamera");
/*  53 */   public static ReflectorMethod ForgeHooksClient_dispatchRenderLast = new ReflectorMethod(ForgeHooksClient, "dispatchRenderLast");
/*  54 */   public static ReflectorMethod ForgeHooksClient_setRenderPass = new ReflectorMethod(ForgeHooksClient, "setRenderPass");
/*  55 */   public static ReflectorMethod ForgeHooksClient_onTextureStitchedPre = new ReflectorMethod(ForgeHooksClient, "onTextureStitchedPre");
/*  56 */   public static ReflectorMethod ForgeHooksClient_onTextureStitchedPost = new ReflectorMethod(ForgeHooksClient, "onTextureStitchedPost");
/*  57 */   public static ReflectorMethod ForgeHooksClient_renderFirstPersonHand = new ReflectorMethod(ForgeHooksClient, "renderFirstPersonHand");
/*  58 */   public static ReflectorMethod ForgeHooksClient_getOffsetFOV = new ReflectorMethod(ForgeHooksClient, "getOffsetFOV");
/*  59 */   public static ReflectorMethod ForgeHooksClient_drawScreen = new ReflectorMethod(ForgeHooksClient, "drawScreen");
/*  60 */   public static ReflectorMethod ForgeHooksClient_onFogRender = new ReflectorMethod(ForgeHooksClient, "onFogRender");
/*  61 */   public static ReflectorMethod ForgeHooksClient_setRenderLayer = new ReflectorMethod(ForgeHooksClient, "setRenderLayer");
/*  62 */   public static ReflectorMethod ForgeHooksClient_transform = new ReflectorMethod(ForgeHooksClient, "transform");
/*  63 */   public static ReflectorMethod ForgeHooksClient_getMatrix = new ReflectorMethod(ForgeHooksClient, "getMatrix", new Class[] { ModelRotation.class });
/*  64 */   public static ReflectorMethod ForgeHooksClient_fillNormal = new ReflectorMethod(ForgeHooksClient, "fillNormal");
/*  65 */   public static ReflectorMethod ForgeHooksClient_handleCameraTransforms = new ReflectorMethod(ForgeHooksClient, "handleCameraTransforms");
/*  66 */   public static ReflectorMethod ForgeHooksClient_getArmorModel = new ReflectorMethod(ForgeHooksClient, "getArmorModel");
/*  67 */   public static ReflectorMethod ForgeHooksClient_getArmorTexture = new ReflectorMethod(ForgeHooksClient, "getArmorTexture");
/*  68 */   public static ReflectorMethod ForgeHooksClient_putQuadColor = new ReflectorMethod(ForgeHooksClient, "putQuadColor");
/*  69 */   public static ReflectorMethod ForgeHooksClient_loadEntityShader = new ReflectorMethod(ForgeHooksClient, "loadEntityShader");
/*  70 */   public static ReflectorMethod ForgeHooksClient_getFogDensity = new ReflectorMethod(ForgeHooksClient, "getFogDensity");
/*  71 */   public static ReflectorClass FMLCommonHandler = new ReflectorClass("net.minecraftforge.fml.common.FMLCommonHandler");
/*  72 */   public static ReflectorMethod FMLCommonHandler_instance = new ReflectorMethod(FMLCommonHandler, "instance");
/*  73 */   public static ReflectorMethod FMLCommonHandler_handleServerStarting = new ReflectorMethod(FMLCommonHandler, "handleServerStarting");
/*  74 */   public static ReflectorMethod FMLCommonHandler_handleServerAboutToStart = new ReflectorMethod(FMLCommonHandler, "handleServerAboutToStart");
/*  75 */   public static ReflectorMethod FMLCommonHandler_enhanceCrashReport = new ReflectorMethod(FMLCommonHandler, "enhanceCrashReport");
/*  76 */   public static ReflectorMethod FMLCommonHandler_getBrandings = new ReflectorMethod(FMLCommonHandler, "getBrandings");
/*  77 */   public static ReflectorMethod FMLCommonHandler_callFuture = new ReflectorMethod(FMLCommonHandler, "callFuture");
/*  78 */   public static ReflectorClass FMLClientHandler = new ReflectorClass("net.minecraftforge.fml.client.FMLClientHandler");
/*  79 */   public static ReflectorMethod FMLClientHandler_instance = new ReflectorMethod(FMLClientHandler, "instance");
/*  80 */   public static ReflectorMethod FMLClientHandler_isLoading = new ReflectorMethod(FMLClientHandler, "isLoading");
/*  81 */   public static ReflectorMethod FMLClientHandler_trackBrokenTexture = new ReflectorMethod(FMLClientHandler, "trackBrokenTexture");
/*  82 */   public static ReflectorMethod FMLClientHandler_trackMissingTexture = new ReflectorMethod(FMLClientHandler, "trackMissingTexture");
/*  83 */   public static ReflectorClass ForgeWorldProvider = new ReflectorClass(WorldProvider.class);
/*  84 */   public static ReflectorMethod ForgeWorldProvider_getSkyRenderer = new ReflectorMethod(ForgeWorldProvider, "getSkyRenderer");
/*  85 */   public static ReflectorMethod ForgeWorldProvider_getCloudRenderer = new ReflectorMethod(ForgeWorldProvider, "getCloudRenderer");
/*  86 */   public static ReflectorMethod ForgeWorldProvider_getWeatherRenderer = new ReflectorMethod(ForgeWorldProvider, "getWeatherRenderer");
/*  87 */   public static ReflectorClass ForgeWorld = new ReflectorClass(World.class);
/*  88 */   public static ReflectorMethod ForgeWorld_countEntities = new ReflectorMethod(ForgeWorld, "countEntities", new Class[] { EnumCreatureType.class, boolean.class });
/*  89 */   public static ReflectorMethod ForgeWorld_getPerWorldStorage = new ReflectorMethod(ForgeWorld, "getPerWorldStorage");
/*  90 */   public static ReflectorClass IRenderHandler = new ReflectorClass("net.minecraftforge.client.IRenderHandler");
/*  91 */   public static ReflectorMethod IRenderHandler_render = new ReflectorMethod(IRenderHandler, "render");
/*  92 */   public static ReflectorClass DimensionManager = new ReflectorClass("net.minecraftforge.common.DimensionManager");
/*  93 */   public static ReflectorMethod DimensionManager_getStaticDimensionIDs = new ReflectorMethod(DimensionManager, "getStaticDimensionIDs");
/*  94 */   public static ReflectorClass WorldEvent_Load = new ReflectorClass("net.minecraftforge.event.world.WorldEvent$Load");
/*  95 */   public static ReflectorConstructor WorldEvent_Load_Constructor = new ReflectorConstructor(WorldEvent_Load, new Class[] { World.class });
/*  96 */   public static ReflectorClass RenderItemInFrameEvent = new ReflectorClass("net.minecraftforge.client.event.RenderItemInFrameEvent");
/*  97 */   public static ReflectorConstructor RenderItemInFrameEvent_Constructor = new ReflectorConstructor(RenderItemInFrameEvent, new Class[] { EntityItemFrame.class, RenderItemFrame.class });
/*  98 */   public static ReflectorClass DrawScreenEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre");
/*  99 */   public static ReflectorConstructor DrawScreenEvent_Pre_Constructor = new ReflectorConstructor(DrawScreenEvent_Pre, new Class[] { GuiScreen.class, int.class, int.class, float.class });
/* 100 */   public static ReflectorClass DrawScreenEvent_Post = new ReflectorClass("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post");
/* 101 */   public static ReflectorConstructor DrawScreenEvent_Post_Constructor = new ReflectorConstructor(DrawScreenEvent_Post, new Class[] { GuiScreen.class, int.class, int.class, float.class });
/* 102 */   public static ReflectorClass EntityViewRenderEvent_FogColors = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$FogColors");
/* 103 */   public static ReflectorConstructor EntityViewRenderEvent_FogColors_Constructor = new ReflectorConstructor(EntityViewRenderEvent_FogColors, new Class[] { EntityRenderer.class, Entity.class, Block.class, double.class, float.class, float.class, float.class });
/* 104 */   public static ReflectorField EntityViewRenderEvent_FogColors_red = new ReflectorField(EntityViewRenderEvent_FogColors, "red");
/* 105 */   public static ReflectorField EntityViewRenderEvent_FogColors_green = new ReflectorField(EntityViewRenderEvent_FogColors, "green");
/* 106 */   public static ReflectorField EntityViewRenderEvent_FogColors_blue = new ReflectorField(EntityViewRenderEvent_FogColors, "blue");
/* 107 */   public static ReflectorClass EntityViewRenderEvent_CameraSetup = new ReflectorClass("net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup");
/* 108 */   public static ReflectorConstructor EntityViewRenderEvent_CameraSetup_Constructor = new ReflectorConstructor(EntityViewRenderEvent_CameraSetup, new Class[] { EntityRenderer.class, Entity.class, Block.class, double.class, float.class, float.class, float.class });
/* 109 */   public static ReflectorField EntityViewRenderEvent_CameraSetup_yaw = new ReflectorField(EntityViewRenderEvent_CameraSetup, "yaw");
/* 110 */   public static ReflectorField EntityViewRenderEvent_CameraSetup_pitch = new ReflectorField(EntityViewRenderEvent_CameraSetup, "pitch");
/* 111 */   public static ReflectorField EntityViewRenderEvent_CameraSetup_roll = new ReflectorField(EntityViewRenderEvent_CameraSetup, "roll");
/* 112 */   public static ReflectorClass RenderLivingEvent_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Pre");
/* 113 */   public static ReflectorConstructor RenderLivingEvent_Pre_Constructor = new ReflectorConstructor(RenderLivingEvent_Pre, new Class[] { EntityLivingBase.class, RendererLivingEntity.class, double.class, double.class, double.class });
/* 114 */   public static ReflectorClass RenderLivingEvent_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Post");
/* 115 */   public static ReflectorConstructor RenderLivingEvent_Post_Constructor = new ReflectorConstructor(RenderLivingEvent_Post, new Class[] { EntityLivingBase.class, RendererLivingEntity.class, double.class, double.class, double.class });
/* 116 */   public static ReflectorClass RenderLivingEvent_Specials_Pre = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre");
/* 117 */   public static ReflectorConstructor RenderLivingEvent_Specials_Pre_Constructor = new ReflectorConstructor(RenderLivingEvent_Specials_Pre, new Class[] { EntityLivingBase.class, RendererLivingEntity.class, double.class, double.class, double.class });
/* 118 */   public static ReflectorClass RenderLivingEvent_Specials_Post = new ReflectorClass("net.minecraftforge.client.event.RenderLivingEvent$Specials$Post");
/* 119 */   public static ReflectorConstructor RenderLivingEvent_Specials_Post_Constructor = new ReflectorConstructor(RenderLivingEvent_Specials_Post, new Class[] { EntityLivingBase.class, RendererLivingEntity.class, double.class, double.class, double.class });
/* 120 */   public static ReflectorClass EventBus = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.EventBus");
/* 121 */   public static ReflectorMethod EventBus_post = new ReflectorMethod(EventBus, "post");
/* 122 */   public static ReflectorClass Event_Result = new ReflectorClass("net.minecraftforge.fml.common.eventhandler.Event$Result");
/* 123 */   public static ReflectorField Event_Result_DENY = new ReflectorField(Event_Result, "DENY");
/* 124 */   public static ReflectorField Event_Result_ALLOW = new ReflectorField(Event_Result, "ALLOW");
/* 125 */   public static ReflectorField Event_Result_DEFAULT = new ReflectorField(Event_Result, "DEFAULT");
/* 126 */   public static ReflectorClass ForgeEventFactory = new ReflectorClass("net.minecraftforge.event.ForgeEventFactory");
/* 127 */   public static ReflectorMethod ForgeEventFactory_canEntitySpawn = new ReflectorMethod(ForgeEventFactory, "canEntitySpawn");
/* 128 */   public static ReflectorMethod ForgeEventFactory_canEntityDespawn = new ReflectorMethod(ForgeEventFactory, "canEntityDespawn");
/* 129 */   public static ReflectorMethod ForgeEventFactory_renderBlockOverlay = new ReflectorMethod(ForgeEventFactory, "renderBlockOverlay");
/* 130 */   public static ReflectorMethod ForgeEventFactory_renderWaterOverlay = new ReflectorMethod(ForgeEventFactory, "renderWaterOverlay");
/* 131 */   public static ReflectorMethod ForgeEventFactory_renderFireOverlay = new ReflectorMethod(ForgeEventFactory, "renderFireOverlay");
/* 132 */   public static ReflectorClass RenderBlockOverlayEvent_OverlayType = new ReflectorClass("net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType");
/* 133 */   public static ReflectorField RenderBlockOverlayEvent_OverlayType_BLOCK = new ReflectorField(RenderBlockOverlayEvent_OverlayType, "BLOCK");
/* 134 */   public static ReflectorClass ChunkWatchEvent_UnWatch = new ReflectorClass("net.minecraftforge.event.world.ChunkWatchEvent$UnWatch");
/* 135 */   public static ReflectorConstructor ChunkWatchEvent_UnWatch_Constructor = new ReflectorConstructor(ChunkWatchEvent_UnWatch, new Class[] { ChunkCoordIntPair.class, EntityPlayerMP.class });
/* 136 */   public static ReflectorClass ForgeBlock = new ReflectorClass(Block.class);
/* 137 */   public static ReflectorMethod ForgeBlock_getBedDirection = new ReflectorMethod(ForgeBlock, "getBedDirection");
/* 138 */   public static ReflectorMethod ForgeBlock_isBed = new ReflectorMethod(ForgeBlock, "isBed");
/* 139 */   public static ReflectorMethod ForgeBlock_isBedFoot = new ReflectorMethod(ForgeBlock, "isBedFoot");
/* 140 */   public static ReflectorMethod ForgeBlock_hasTileEntity = new ReflectorMethod(ForgeBlock, "hasTileEntity", new Class[] { IBlockState.class });
/* 141 */   public static ReflectorMethod ForgeBlock_canCreatureSpawn = new ReflectorMethod(ForgeBlock, "canCreatureSpawn");
/* 142 */   public static ReflectorMethod ForgeBlock_addHitEffects = new ReflectorMethod(ForgeBlock, "addHitEffects");
/* 143 */   public static ReflectorMethod ForgeBlock_addDestroyEffects = new ReflectorMethod(ForgeBlock, "addDestroyEffects");
/* 144 */   public static ReflectorMethod ForgeBlock_isAir = new ReflectorMethod(ForgeBlock, "isAir");
/* 145 */   public static ReflectorMethod ForgeBlock_canRenderInLayer = new ReflectorMethod(ForgeBlock, "canRenderInLayer");
/* 146 */   public static ReflectorMethod ForgeBlock_getExtendedState = new ReflectorMethod(ForgeBlock, "getExtendedState");
/* 147 */   public static ReflectorClass ForgeEntity = new ReflectorClass(Entity.class);
/* 148 */   public static ReflectorField ForgeEntity_captureDrops = new ReflectorField(ForgeEntity, "captureDrops");
/* 149 */   public static ReflectorField ForgeEntity_capturedDrops = new ReflectorField(ForgeEntity, "capturedDrops");
/* 150 */   public static ReflectorMethod ForgeEntity_shouldRenderInPass = new ReflectorMethod(ForgeEntity, "shouldRenderInPass");
/* 151 */   public static ReflectorMethod ForgeEntity_canRiderInteract = new ReflectorMethod(ForgeEntity, "canRiderInteract");
/* 152 */   public static ReflectorMethod ForgeEntity_shouldRiderSit = new ReflectorMethod(ForgeEntity, "shouldRiderSit");
/* 153 */   public static ReflectorClass ForgeTileEntity = new ReflectorClass(TileEntity.class);
/* 154 */   public static ReflectorMethod ForgeTileEntity_shouldRenderInPass = new ReflectorMethod(ForgeTileEntity, "shouldRenderInPass");
/* 155 */   public static ReflectorMethod ForgeTileEntity_getRenderBoundingBox = new ReflectorMethod(ForgeTileEntity, "getRenderBoundingBox");
/* 156 */   public static ReflectorMethod ForgeTileEntity_canRenderBreaking = new ReflectorMethod(ForgeTileEntity, "canRenderBreaking");
/* 157 */   public static ReflectorClass ForgeItem = new ReflectorClass(Item.class);
/* 158 */   public static ReflectorMethod ForgeItem_onEntitySwing = new ReflectorMethod(ForgeItem, "onEntitySwing");
/* 159 */   public static ReflectorMethod ForgeItem_shouldCauseReequipAnimation = new ReflectorMethod(ForgeItem, "shouldCauseReequipAnimation");
/* 160 */   public static ReflectorMethod ForgeItem_getModel = new ReflectorMethod(ForgeItem, "getModel");
/* 161 */   public static ReflectorMethod ForgeItem_showDurabilityBar = new ReflectorMethod(ForgeItem, "showDurabilityBar");
/* 162 */   public static ReflectorMethod ForgeItem_getDurabilityForDisplay = new ReflectorMethod(ForgeItem, "getDurabilityForDisplay");
/* 163 */   public static ReflectorClass ForgePotionEffect = new ReflectorClass(PotionEffect.class);
/* 164 */   public static ReflectorMethod ForgePotionEffect_isCurativeItem = new ReflectorMethod(ForgePotionEffect, "isCurativeItem");
/* 165 */   public static ReflectorClass ForgeItemRecord = new ReflectorClass(ItemRecord.class);
/* 166 */   public static ReflectorMethod ForgeItemRecord_getRecordResource = new ReflectorMethod(ForgeItemRecord, "getRecordResource", new Class[] { String.class });
/* 167 */   public static ReflectorClass ForgeVertexFormatElementEnumUseage = new ReflectorClass(VertexFormatElement.EnumUseage.class);
/* 168 */   public static ReflectorMethod ForgeVertexFormatElementEnumUseage_preDraw = new ReflectorMethod(ForgeVertexFormatElementEnumUseage, "preDraw");
/* 169 */   public static ReflectorMethod ForgeVertexFormatElementEnumUseage_postDraw = new ReflectorMethod(ForgeVertexFormatElementEnumUseage, "postDraw");
/* 170 */   public static ReflectorClass BlamingTransformer = new ReflectorClass("net.minecraftforge.fml.common.asm.transformers.BlamingTransformer");
/* 171 */   public static ReflectorMethod BlamingTransformer_onCrash = new ReflectorMethod(BlamingTransformer, "onCrash");
/* 172 */   public static ReflectorClass CoreModManager = new ReflectorClass("net.minecraftforge.fml.relauncher.CoreModManager");
/* 173 */   public static ReflectorMethod CoreModManager_onCrash = new ReflectorMethod(CoreModManager, "onCrash");
/* 174 */   public static ReflectorClass ISmartBlockModel = new ReflectorClass("net.minecraftforge.client.model.ISmartBlockModel");
/* 175 */   public static ReflectorMethod ISmartBlockModel_handleBlockState = new ReflectorMethod(ISmartBlockModel, "handleBlockState");
/* 176 */   public static ReflectorClass Launch = new ReflectorClass("net.minecraft.launchwrapper.Launch");
/* 177 */   public static ReflectorField Launch_blackboard = new ReflectorField(Launch, "blackboard");
/* 178 */   public static ReflectorClass SplashScreen = new ReflectorClass("net.minecraftforge.fml.client.SplashProgress");
/* 179 */   public static ReflectorClass LightUtil = new ReflectorClass("net.minecraftforge.client.model.pipeline.LightUtil");
/* 180 */   public static ReflectorField LightUtil_tessellator = new ReflectorField(LightUtil, "tessellator");
/* 181 */   public static ReflectorField LightUtil_itemConsumer = new ReflectorField(LightUtil, "itemConsumer");
/* 182 */   public static ReflectorMethod LightUtil_putBakedQuad = new ReflectorMethod(LightUtil, "putBakedQuad");
/* 183 */   public static ReflectorMethod LightUtil_renderQuadColor = new ReflectorMethod(LightUtil, "renderQuadColor");
/* 184 */   public static ReflectorClass IExtendedBlockState = new ReflectorClass("net.minecraftforge.common.property.IExtendedBlockState");
/* 185 */   public static ReflectorMethod IExtendedBlockState_getClean = new ReflectorMethod(IExtendedBlockState, "getClean");
/* 186 */   public static ReflectorClass ItemModelMesherForge = new ReflectorClass("net.minecraftforge.client.ItemModelMesherForge");
/* 187 */   public static ReflectorConstructor ItemModelMesherForge_Constructor = new ReflectorConstructor(ItemModelMesherForge, new Class[] { ModelManager.class });
/* 188 */   public static ReflectorClass ModelLoader = new ReflectorClass("net.minecraftforge.client.model.ModelLoader");
/* 189 */   public static ReflectorMethod ModelLoader_onRegisterItems = new ReflectorMethod(ModelLoader, "onRegisterItems");
/* 190 */   public static ReflectorClass Attributes = new ReflectorClass("net.minecraftforge.client.model.Attributes");
/* 191 */   public static ReflectorField Attributes_DEFAULT_BAKED_FORMAT = new ReflectorField(Attributes, "DEFAULT_BAKED_FORMAT");
/* 192 */   public static ReflectorClass BetterFoliageClient = new ReflectorClass("mods.betterfoliage.client.BetterFoliageClient");
/* 193 */   public static ReflectorClass IColoredBakedQuad = new ReflectorClass("net.minecraftforge.client.model.IColoredBakedQuad");
/* 194 */   public static ReflectorClass ForgeBiomeGenBase = new ReflectorClass(BiomeGenBase.class);
/* 195 */   public static ReflectorMethod ForgeBiomeGenBase_getWaterColorMultiplier = new ReflectorMethod(ForgeBiomeGenBase, "getWaterColorMultiplier");
/* 196 */   public static ReflectorClass RenderingRegistry = new ReflectorClass("net.minecraftforge.fml.client.registry.RenderingRegistry");
/* 197 */   public static ReflectorMethod RenderingRegistry_loadEntityRenderers = new ReflectorMethod(RenderingRegistry, "loadEntityRenderers", new Class[] { RenderManager.class, Map.class });
/* 198 */   public static ReflectorClass ForgeTileEntityRendererDispatcher = new ReflectorClass(TileEntityRendererDispatcher.class);
/* 199 */   public static ReflectorMethod ForgeTileEntityRendererDispatcher_preDrawBatch = new ReflectorMethod(ForgeTileEntityRendererDispatcher, "preDrawBatch");
/* 200 */   public static ReflectorMethod ForgeTileEntityRendererDispatcher_drawBatch = new ReflectorMethod(ForgeTileEntityRendererDispatcher, "drawBatch");
/* 201 */   public static ReflectorClass OptiFineClassTransformer = new ReflectorClass("optifine.OptiFineClassTransformer");
/* 202 */   public static ReflectorField OptiFineClassTransformer_instance = new ReflectorField(OptiFineClassTransformer, "instance");
/* 203 */   public static ReflectorMethod OptiFineClassTransformer_getOptiFineResource = new ReflectorMethod(OptiFineClassTransformer, "getOptiFineResource");
/* 204 */   public static ReflectorClass ForgeModContainer = new ReflectorClass("net.minecraftforge.common.ForgeModContainer");
/* 205 */   public static ReflectorField ForgeModContainer_forgeLightPipelineEnabled = new ReflectorField(ForgeModContainer, "forgeLightPipelineEnabled");
/*     */ 
/*     */ 
/*     */   
/*     */   public static void callVoid(ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 211 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 213 */       if (e == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 218 */       e.invoke(null, params);
/*     */     }
/* 220 */     catch (Throwable var3) {
/*     */       
/* 222 */       handleException(var3, null, refMethod, params);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean callBoolean(ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 230 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 232 */       if (e == null)
/*     */       {
/* 234 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 238 */       Boolean retVal = (Boolean)e.invoke(null, params);
/* 239 */       return retVal.booleanValue();
/*     */     
/*     */     }
/* 242 */     catch (Throwable var4) {
/*     */       
/* 244 */       handleException(var4, null, refMethod, params);
/* 245 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int callInt(ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 253 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 255 */       if (e == null)
/*     */       {
/* 257 */         return 0;
/*     */       }
/*     */ 
/*     */       
/* 261 */       Integer retVal = (Integer)e.invoke(null, params);
/* 262 */       return retVal.intValue();
/*     */     
/*     */     }
/* 265 */     catch (Throwable var4) {
/*     */       
/* 267 */       handleException(var4, null, refMethod, params);
/* 268 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float callFloat(ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 276 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 278 */       if (e == null)
/*     */       {
/* 280 */         return 0.0F;
/*     */       }
/*     */ 
/*     */       
/* 284 */       Float retVal = (Float)e.invoke(null, params);
/* 285 */       return retVal.floatValue();
/*     */     
/*     */     }
/* 288 */     catch (Throwable var4) {
/*     */       
/* 290 */       handleException(var4, null, refMethod, params);
/* 291 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double callDouble(ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 299 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 301 */       if (e == null)
/*     */       {
/* 303 */         return 0.0D;
/*     */       }
/*     */ 
/*     */       
/* 307 */       Double retVal = (Double)e.invoke(null, params);
/* 308 */       return retVal.doubleValue();
/*     */     
/*     */     }
/* 311 */     catch (Throwable var4) {
/*     */       
/* 313 */       handleException(var4, null, refMethod, params);
/* 314 */       return 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String callString(ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 322 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 324 */       if (e == null)
/*     */       {
/* 326 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 330 */       String retVal = (String)e.invoke(null, params);
/* 331 */       return retVal;
/*     */     
/*     */     }
/* 334 */     catch (Throwable var4) {
/*     */       
/* 336 */       handleException(var4, null, refMethod, params);
/* 337 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object call(ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 345 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 347 */       if (e == null)
/*     */       {
/* 349 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 353 */       Object retVal = e.invoke(null, params);
/* 354 */       return retVal;
/*     */     
/*     */     }
/* 357 */     catch (Throwable var4) {
/*     */       
/* 359 */       handleException(var4, null, refMethod, params);
/* 360 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void callVoid(Object obj, ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 368 */       if (obj == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 373 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 375 */       if (e == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 380 */       e.invoke(obj, params);
/*     */     }
/* 382 */     catch (Throwable var4) {
/*     */       
/* 384 */       handleException(var4, obj, refMethod, params);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean callBoolean(Object obj, ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 392 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 394 */       if (e == null)
/*     */       {
/* 396 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 400 */       Boolean retVal = (Boolean)e.invoke(obj, params);
/* 401 */       return retVal.booleanValue();
/*     */     
/*     */     }
/* 404 */     catch (Throwable var5) {
/*     */       
/* 406 */       handleException(var5, obj, refMethod, params);
/* 407 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int callInt(Object obj, ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 415 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 417 */       if (e == null)
/*     */       {
/* 419 */         return 0;
/*     */       }
/*     */ 
/*     */       
/* 423 */       Integer retVal = (Integer)e.invoke(obj, params);
/* 424 */       return retVal.intValue();
/*     */     
/*     */     }
/* 427 */     catch (Throwable var5) {
/*     */       
/* 429 */       handleException(var5, obj, refMethod, params);
/* 430 */       return 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float callFloat(Object obj, ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 438 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 440 */       if (e == null)
/*     */       {
/* 442 */         return 0.0F;
/*     */       }
/*     */ 
/*     */       
/* 446 */       Float retVal = (Float)e.invoke(obj, params);
/* 447 */       return retVal.floatValue();
/*     */     
/*     */     }
/* 450 */     catch (Throwable var5) {
/*     */       
/* 452 */       handleException(var5, obj, refMethod, params);
/* 453 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double callDouble(Object obj, ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 461 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 463 */       if (e == null)
/*     */       {
/* 465 */         return 0.0D;
/*     */       }
/*     */ 
/*     */       
/* 469 */       Double retVal = (Double)e.invoke(obj, params);
/* 470 */       return retVal.doubleValue();
/*     */     
/*     */     }
/* 473 */     catch (Throwable var5) {
/*     */       
/* 475 */       handleException(var5, obj, refMethod, params);
/* 476 */       return 0.0D;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String callString(Object obj, ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 484 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 486 */       if (e == null)
/*     */       {
/* 488 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 492 */       String retVal = (String)e.invoke(obj, params);
/* 493 */       return retVal;
/*     */     
/*     */     }
/* 496 */     catch (Throwable var5) {
/*     */       
/* 498 */       handleException(var5, obj, refMethod, params);
/* 499 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object call(Object obj, ReflectorMethod refMethod, Object... params) {
/*     */     try {
/* 507 */       Method e = refMethod.getTargetMethod();
/*     */       
/* 509 */       if (e == null)
/*     */       {
/* 511 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 515 */       Object retVal = e.invoke(obj, params);
/* 516 */       return retVal;
/*     */     
/*     */     }
/* 519 */     catch (Throwable var5) {
/*     */       
/* 521 */       handleException(var5, obj, refMethod, params);
/* 522 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object getFieldValue(ReflectorField refField) {
/* 528 */     return getFieldValue(null, refField);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getFieldValue(Object obj, ReflectorField refField) {
/*     */     try {
/* 535 */       Field e = refField.getTargetField();
/*     */       
/* 537 */       if (e == null)
/*     */       {
/* 539 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 543 */       Object value = e.get(obj);
/* 544 */       return value;
/*     */     
/*     */     }
/* 547 */     catch (Throwable var4) {
/*     */       
/* 549 */       var4.printStackTrace();
/* 550 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getFieldValueFloat(Object obj, ReflectorField refField, float def) {
/* 556 */     Object val = getFieldValue(obj, refField);
/*     */     
/* 558 */     if (!(val instanceof Float))
/*     */     {
/* 560 */       return def;
/*     */     }
/*     */ 
/*     */     
/* 564 */     Float valFloat = (Float)val;
/* 565 */     return valFloat.floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setFieldValue(ReflectorField refField, Object value) {
/* 571 */     setFieldValue(null, refField, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setFieldValue(Object obj, ReflectorField refField, Object value) {
/*     */     try {
/* 578 */       Field e = refField.getTargetField();
/*     */       
/* 580 */       if (e == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 585 */       e.set(obj, value);
/*     */     }
/* 587 */     catch (Throwable var4) {
/*     */       
/* 589 */       var4.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean postForgeBusEvent(ReflectorConstructor constr, Object... params) {
/* 595 */     Object event = newInstance(constr, params);
/* 596 */     return (event == null) ? false : postForgeBusEvent(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean postForgeBusEvent(Object event) {
/* 601 */     if (event == null)
/*     */     {
/* 603 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 607 */     Object eventBus = getFieldValue(MinecraftForge_EVENT_BUS);
/*     */     
/* 609 */     if (eventBus == null)
/*     */     {
/* 611 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 615 */     Object ret = call(eventBus, EventBus_post, new Object[] { event });
/*     */     
/* 617 */     if (!(ret instanceof Boolean))
/*     */     {
/* 619 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 623 */     Boolean retBool = (Boolean)ret;
/* 624 */     return retBool.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object newInstance(ReflectorConstructor constr, Object... params) {
/* 632 */     Constructor c = constr.getTargetConstructor();
/*     */     
/* 634 */     if (c == null)
/*     */     {
/* 636 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 642 */       Object e = c.newInstance(params);
/* 643 */       return e;
/*     */     }
/* 645 */     catch (Throwable var4) {
/*     */       
/* 647 */       handleException(var4, constr, params);
/* 648 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Method getMethod(Class cls, String methodName, Class[] paramTypes) {
/* 655 */     Method[] ms = cls.getDeclaredMethods();
/*     */     
/* 657 */     for (int i = 0; i < ms.length; i++) {
/*     */       
/* 659 */       Method m = ms[i];
/*     */       
/* 661 */       if (m.getName().equals(methodName)) {
/*     */         
/* 663 */         Class[] types = m.getParameterTypes();
/*     */         
/* 665 */         if (matchesTypes(paramTypes, types))
/*     */         {
/* 667 */           return m;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 672 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Method[] getMethods(Class cls, String methodName) {
/* 677 */     ArrayList<Method> listMethods = new ArrayList();
/* 678 */     Method[] ms = cls.getDeclaredMethods();
/*     */     
/* 680 */     for (int methods = 0; methods < ms.length; methods++) {
/*     */       
/* 682 */       Method m = ms[methods];
/*     */       
/* 684 */       if (m.getName().equals(methodName))
/*     */       {
/* 686 */         listMethods.add(m);
/*     */       }
/*     */     } 
/*     */     
/* 690 */     Method[] var6 = listMethods.<Method>toArray(new Method[listMethods.size()]);
/* 691 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean matchesTypes(Class[] pTypes, Class[] cTypes) {
/* 696 */     if (pTypes.length != cTypes.length)
/*     */     {
/* 698 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 702 */     for (int i = 0; i < cTypes.length; i++) {
/*     */       
/* 704 */       Class pType = pTypes[i];
/* 705 */       Class cType = cTypes[i];
/*     */       
/* 707 */       if (pType != cType)
/*     */       {
/* 709 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 713 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void dbgCall(boolean isStatic, String callType, ReflectorMethod refMethod, Object[] params, Object retVal) {
/* 719 */     String className = refMethod.getTargetMethod().getDeclaringClass().getName();
/* 720 */     String methodName = refMethod.getTargetMethod().getName();
/* 721 */     String staticStr = "";
/*     */     
/* 723 */     if (isStatic)
/*     */     {
/* 725 */       staticStr = " static";
/*     */     }
/*     */     
/* 728 */     Config.dbg(String.valueOf(callType) + staticStr + " " + className + "." + methodName + "(" + Config.arrayToString(params) + ") => " + retVal);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void dbgCallVoid(boolean isStatic, String callType, ReflectorMethod refMethod, Object[] params) {
/* 733 */     String className = refMethod.getTargetMethod().getDeclaringClass().getName();
/* 734 */     String methodName = refMethod.getTargetMethod().getName();
/* 735 */     String staticStr = "";
/*     */     
/* 737 */     if (isStatic)
/*     */     {
/* 739 */       staticStr = " static";
/*     */     }
/*     */     
/* 742 */     Config.dbg(String.valueOf(callType) + staticStr + " " + className + "." + methodName + "(" + Config.arrayToString(params) + ")");
/*     */   }
/*     */ 
/*     */   
/*     */   private static void dbgFieldValue(boolean isStatic, String accessType, ReflectorField refField, Object val) {
/* 747 */     String className = refField.getTargetField().getDeclaringClass().getName();
/* 748 */     String fieldName = refField.getTargetField().getName();
/* 749 */     String staticStr = "";
/*     */     
/* 751 */     if (isStatic)
/*     */     {
/* 753 */       staticStr = " static";
/*     */     }
/*     */     
/* 756 */     Config.dbg(String.valueOf(accessType) + staticStr + " " + className + "." + fieldName + " => " + val);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void handleException(Throwable e, Object obj, ReflectorMethod refMethod, Object[] params) {
/* 761 */     if (e instanceof java.lang.reflect.InvocationTargetException) {
/*     */       
/* 763 */       e.printStackTrace();
/*     */     }
/*     */     else {
/*     */       
/* 767 */       if (e instanceof IllegalArgumentException) {
/*     */         
/* 769 */         Config.warn("*** IllegalArgumentException ***");
/* 770 */         Config.warn("Method: " + refMethod.getTargetMethod());
/* 771 */         Config.warn("Object: " + obj);
/* 772 */         Config.warn("Parameter classes: " + Config.arrayToString(getClasses(params)));
/* 773 */         Config.warn("Parameters: " + Config.arrayToString(params));
/*     */       } 
/*     */       
/* 776 */       Config.warn("*** Exception outside of method ***");
/* 777 */       Config.warn("Method deactivated: " + refMethod.getTargetMethod());
/* 778 */       refMethod.deactivate();
/* 779 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void handleException(Throwable e, ReflectorConstructor refConstr, Object[] params) {
/* 785 */     if (e instanceof java.lang.reflect.InvocationTargetException) {
/*     */       
/* 787 */       e.printStackTrace();
/*     */     }
/*     */     else {
/*     */       
/* 791 */       if (e instanceof IllegalArgumentException) {
/*     */         
/* 793 */         Config.warn("*** IllegalArgumentException ***");
/* 794 */         Config.warn("Constructor: " + refConstr.getTargetConstructor());
/* 795 */         Config.warn("Parameter classes: " + Config.arrayToString(getClasses(params)));
/* 796 */         Config.warn("Parameters: " + Config.arrayToString(params));
/*     */       } 
/*     */       
/* 799 */       Config.warn("*** Exception outside of constructor ***");
/* 800 */       Config.warn("Constructor deactivated: " + refConstr.getTargetConstructor());
/* 801 */       refConstr.deactivate();
/* 802 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static Object[] getClasses(Object[] objs) {
/* 808 */     if (objs == null)
/*     */     {
/* 810 */       return (Object[])new Class[0];
/*     */     }
/*     */ 
/*     */     
/* 814 */     Class[] classes = new Class[objs.length];
/*     */     
/* 816 */     for (int i = 0; i < classes.length; i++) {
/*     */       
/* 818 */       Object obj = objs[i];
/*     */       
/* 820 */       if (obj != null)
/*     */       {
/* 822 */         classes[i] = obj.getClass();
/*     */       }
/*     */     } 
/*     */     
/* 826 */     return (Object[])classes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Field getField(Class cls, Class<?> fieldType) {
/*     */     try {
/* 834 */       Field[] e = cls.getDeclaredFields();
/*     */       
/* 836 */       for (int i = 0; i < e.length; i++) {
/*     */         
/* 838 */         Field field = e[i];
/*     */         
/* 840 */         if (field.getType() == fieldType) {
/*     */           
/* 842 */           field.setAccessible(true);
/* 843 */           return field;
/*     */         } 
/*     */       } 
/*     */       
/* 847 */       return null;
/*     */     }
/* 849 */     catch (Exception var5) {
/*     */       
/* 851 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Field[] getFields(Class cls, Class<?> fieldType) {
/* 857 */     ArrayList<Field> list = new ArrayList();
/*     */ 
/*     */     
/*     */     try {
/* 861 */       Field[] e = cls.getDeclaredFields();
/*     */       
/* 863 */       for (int fields = 0; fields < e.length; fields++) {
/*     */         
/* 865 */         Field field = e[fields];
/*     */         
/* 867 */         if (field.getType() == fieldType) {
/*     */           
/* 869 */           field.setAccessible(true);
/* 870 */           list.add(field);
/*     */         } 
/*     */       } 
/*     */       
/* 874 */       Field[] var7 = list.<Field>toArray(new Field[list.size()]);
/* 875 */       return var7;
/*     */     }
/* 877 */     catch (Exception var6) {
/*     */       
/* 879 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\Reflector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */