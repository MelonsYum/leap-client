/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.renderer.texture.TextureUtil;
/*     */ import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import optifine.BetterGrass;
/*     */ import optifine.BetterSnow;
/*     */ import optifine.Config;
/*     */ import optifine.ConnectedTextures;
/*     */ import optifine.CustomColors;
/*     */ import optifine.NaturalTextures;
/*     */ import optifine.Reflector;
/*     */ import optifine.RenderEnv;
/*     */ import optifine.SmartLeaves;
/*     */ 
/*     */ 
/*     */ public class BlockModelRenderer
/*     */ {
/*     */   private static final String __OBFID = "CL_00002518";
/*  35 */   private static float aoLightValueOpaque = 0.2F;
/*     */ 
/*     */   
/*     */   public static void updateAoLightValue() {
/*  39 */     aoLightValueOpaque = 1.0F - Config.getAmbientOcclusionLevel() * 0.8F;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockModelRenderer() {
/*  44 */     if (Reflector.ForgeModContainer_forgeLightPipelineEnabled.exists())
/*     */     {
/*  46 */       Reflector.setFieldValue(Reflector.ForgeModContainer_forgeLightPipelineEnabled, Boolean.valueOf(false));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178259_a(IBlockAccess blockAccessIn, IBakedModel modelIn, IBlockState blockStateIn, BlockPos blockPosIn, WorldRenderer worldRendererIn) {
/*  52 */     Block var6 = blockStateIn.getBlock();
/*  53 */     var6.setBlockBoundsBasedOnState(blockAccessIn, blockPosIn);
/*  54 */     return renderBlockModel(blockAccessIn, modelIn, blockStateIn, blockPosIn, worldRendererIn, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderBlockModel(IBlockAccess blockAccessIn, IBakedModel modelIn, IBlockState blockStateIn, BlockPos blockPosIn, WorldRenderer worldRendererIn, boolean checkSides) {
/*  59 */     boolean var7 = (Minecraft.isAmbientOcclusionEnabled() && blockStateIn.getBlock().getLightValue() == 0 && modelIn.isGui3d());
/*     */ 
/*     */     
/*     */     try {
/*  63 */       Block var11 = blockStateIn.getBlock();
/*     */       
/*  65 */       if (Config.isTreesSmart() && blockStateIn.getBlock() instanceof net.minecraft.block.BlockLeavesBase)
/*     */       {
/*  67 */         modelIn = SmartLeaves.getLeavesModel(modelIn);
/*     */       }
/*     */       
/*  70 */       return var7 ? renderModelAmbientOcclusion(blockAccessIn, modelIn, var11, blockStateIn, blockPosIn, worldRendererIn, checkSides) : renderModelStandard(blockAccessIn, modelIn, var11, blockStateIn, blockPosIn, worldRendererIn, checkSides);
/*     */     }
/*  72 */     catch (Throwable var111) {
/*     */       
/*  74 */       CrashReport var9 = CrashReport.makeCrashReport(var111, "Tesselating block model");
/*  75 */       CrashReportCategory var10 = var9.makeCategory("Block model being tesselated");
/*  76 */       CrashReportCategory.addBlockInfo(var10, blockPosIn, blockStateIn);
/*  77 */       var10.addCrashSection("Using AO", Boolean.valueOf(var7));
/*  78 */       throw new ReportedException(var9);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178265_a(IBlockAccess blockAccessIn, IBakedModel modelIn, Block blockIn, BlockPos blockPosIn, WorldRenderer worldRendererIn, boolean checkSides) {
/*  84 */     return renderModelAmbientOcclusion(blockAccessIn, modelIn, blockIn, blockAccessIn.getBlockState(blockPosIn), blockPosIn, worldRendererIn, checkSides);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderModelAmbientOcclusion(IBlockAccess blockAccessIn, IBakedModel modelIn, Block blockIn, IBlockState blockStateIn, BlockPos blockPosIn, WorldRenderer worldRendererIn, boolean checkSides) {
/*  89 */     boolean var7 = false;
/*  90 */     worldRendererIn.func_178963_b(983055);
/*  91 */     RenderEnv renderEnv = null;
/*  92 */     EnumFacing[] var11 = EnumFacing.VALUES;
/*  93 */     int var12 = var11.length;
/*     */     
/*  95 */     for (int var17 = 0; var17 < var12; var17++) {
/*     */       
/*  97 */       EnumFacing modelSnow = var11[var17];
/*  98 */       List stateSnow = modelIn.func_177551_a(modelSnow);
/*     */       
/* 100 */       if (!stateSnow.isEmpty()) {
/*     */         
/* 102 */         BlockPos var16 = blockPosIn.offset(modelSnow);
/*     */         
/* 104 */         if (!checkSides || blockIn.shouldSideBeRendered(blockAccessIn, var16, modelSnow)) {
/*     */           
/* 106 */           if (renderEnv == null)
/*     */           {
/* 108 */             renderEnv = RenderEnv.getInstance(blockAccessIn, blockStateIn, blockPosIn);
/*     */           }
/*     */           
/* 111 */           if (!renderEnv.isBreakingAnimation(stateSnow) && Config.isBetterGrass())
/*     */           {
/* 113 */             stateSnow = BetterGrass.getFaceQuads(blockAccessIn, blockIn, blockPosIn, modelSnow, stateSnow);
/*     */           }
/*     */           
/* 116 */           renderModelAmbientOcclusionQuads(blockAccessIn, blockIn, blockPosIn, worldRendererIn, stateSnow, renderEnv);
/* 117 */           var7 = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     List var161 = modelIn.func_177550_a();
/*     */     
/* 124 */     if (var161.size() > 0) {
/*     */       
/* 126 */       if (renderEnv == null)
/*     */       {
/* 128 */         renderEnv = RenderEnv.getInstance(blockAccessIn, blockStateIn, blockPosIn);
/*     */       }
/*     */       
/* 131 */       renderModelAmbientOcclusionQuads(blockAccessIn, blockIn, blockPosIn, worldRendererIn, var161, renderEnv);
/* 132 */       var7 = true;
/*     */     } 
/*     */     
/* 135 */     if (renderEnv != null && Config.isBetterSnow() && !renderEnv.isBreakingAnimation() && BetterSnow.shouldRender(blockAccessIn, blockIn, blockStateIn, blockPosIn)) {
/*     */       
/* 137 */       IBakedModel var171 = BetterSnow.getModelSnowLayer();
/* 138 */       IBlockState var18 = BetterSnow.getStateSnowLayer();
/* 139 */       renderModelAmbientOcclusion(blockAccessIn, var171, var18.getBlock(), var18, blockPosIn, worldRendererIn, true);
/*     */     } 
/*     */     
/* 142 */     return var7;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178258_b(IBlockAccess blockAccessIn, IBakedModel modelIn, Block blockIn, BlockPos blockPosIn, WorldRenderer worldRendererIn, boolean checkSides) {
/* 147 */     return renderModelStandard(blockAccessIn, modelIn, blockIn, blockAccessIn.getBlockState(blockPosIn), blockPosIn, worldRendererIn, checkSides);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderModelStandard(IBlockAccess blockAccessIn, IBakedModel modelIn, Block blockIn, IBlockState blockStateIn, BlockPos blockPosIn, WorldRenderer worldRendererIn, boolean checkSides) {
/* 152 */     boolean var7 = false;
/* 153 */     RenderEnv renderEnv = null;
/* 154 */     EnumFacing[] var9 = EnumFacing.VALUES;
/* 155 */     int var10 = var9.length;
/*     */     
/* 157 */     for (int var16 = 0; var16 < var10; var16++) {
/*     */       
/* 159 */       EnumFacing modelSnow = var9[var16];
/* 160 */       List stateSnow = modelIn.func_177551_a(modelSnow);
/*     */       
/* 162 */       if (!stateSnow.isEmpty()) {
/*     */         
/* 164 */         BlockPos var14 = blockPosIn.offset(modelSnow);
/*     */         
/* 166 */         if (!checkSides || blockIn.shouldSideBeRendered(blockAccessIn, var14, modelSnow)) {
/*     */           
/* 168 */           if (renderEnv == null)
/*     */           {
/* 170 */             renderEnv = RenderEnv.getInstance(blockAccessIn, blockStateIn, blockPosIn);
/*     */           }
/*     */           
/* 173 */           if (!renderEnv.isBreakingAnimation(stateSnow) && Config.isBetterGrass())
/*     */           {
/* 175 */             stateSnow = BetterGrass.getFaceQuads(blockAccessIn, blockIn, blockPosIn, modelSnow, stateSnow);
/*     */           }
/*     */           
/* 178 */           int var15 = blockIn.getMixedBrightnessForBlock(blockAccessIn, var14);
/* 179 */           renderModelStandardQuads(blockAccessIn, blockIn, blockPosIn, modelSnow, var15, false, worldRendererIn, stateSnow, renderEnv);
/* 180 */           var7 = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 185 */     List var17 = modelIn.func_177550_a();
/*     */     
/* 187 */     if (var17.size() > 0) {
/*     */       
/* 189 */       if (renderEnv == null)
/*     */       {
/* 191 */         renderEnv = RenderEnv.getInstance(blockAccessIn, blockStateIn, blockPosIn);
/*     */       }
/*     */       
/* 194 */       renderModelStandardQuads(blockAccessIn, blockIn, blockPosIn, null, -1, true, worldRendererIn, var17, renderEnv);
/* 195 */       var7 = true;
/*     */     } 
/*     */     
/* 198 */     if (renderEnv != null && Config.isBetterSnow() && !renderEnv.isBreakingAnimation() && BetterSnow.shouldRender(blockAccessIn, blockIn, blockStateIn, blockPosIn) && BetterSnow.shouldRender(blockAccessIn, blockIn, blockStateIn, blockPosIn)) {
/*     */       
/* 200 */       IBakedModel var18 = BetterSnow.getModelSnowLayer();
/* 201 */       IBlockState var19 = BetterSnow.getStateSnowLayer();
/* 202 */       renderModelStandard(blockAccessIn, var18, var19.getBlock(), var19, blockPosIn, worldRendererIn, true);
/*     */     } 
/*     */     
/* 205 */     return var7;
/*     */   }
/*     */ 
/*     */   
/*     */   private void renderModelAmbientOcclusionQuads(IBlockAccess blockAccessIn, Block blockIn, BlockPos blockPosIn, WorldRenderer worldRendererIn, List listQuadsIn, RenderEnv renderEnv) {
/* 210 */     float[] quadBounds = renderEnv.getQuadBounds();
/* 211 */     BitSet boundsFlags = renderEnv.getBoundsFlags();
/* 212 */     AmbientOcclusionFace aoFaceIn = renderEnv.getAoFace();
/* 213 */     IBlockState blockStateIn = renderEnv.getBlockState();
/* 214 */     double var9 = blockPosIn.getX();
/* 215 */     double var11 = blockPosIn.getY();
/* 216 */     double var13 = blockPosIn.getZ();
/* 217 */     Block.EnumOffsetType var15 = blockIn.getOffsetType();
/*     */     
/* 219 */     if (var15 != Block.EnumOffsetType.NONE) {
/*     */       
/* 221 */       long var22 = MathHelper.func_180186_a((Vec3i)blockPosIn);
/* 222 */       var9 += (((float)(var22 >> 16L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
/* 223 */       var13 += (((float)(var22 >> 24L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
/*     */       
/* 225 */       if (var15 == Block.EnumOffsetType.XYZ)
/*     */       {
/* 227 */         var11 += (((float)(var22 >> 20L & 0xFL) / 15.0F) - 1.0D) * 0.2D;
/*     */       }
/*     */     } 
/*     */     
/* 231 */     for (Iterator<BakedQuad> var221 = listQuadsIn.iterator(); var221.hasNext(); worldRendererIn.func_178987_a(var9, var11, var13)) {
/*     */       
/* 233 */       BakedQuad var17 = var221.next();
/*     */       
/* 235 */       if (!renderEnv.isBreakingAnimation(var17)) {
/*     */         
/* 237 */         BakedQuad colorMultiplier = var17;
/*     */         
/* 239 */         if (Config.isConnectedTextures())
/*     */         {
/* 241 */           var17 = ConnectedTextures.getConnectedTexture(blockAccessIn, blockStateIn, blockPosIn, var17, renderEnv);
/*     */         }
/*     */         
/* 244 */         if (var17 == colorMultiplier && Config.isNaturalTextures())
/*     */         {
/* 246 */           var17 = NaturalTextures.getNaturalTexture(blockPosIn, var17);
/*     */         }
/*     */       } 
/*     */       
/* 250 */       func_178261_a(blockIn, var17.func_178209_a(), var17.getFace(), quadBounds, boundsFlags);
/* 251 */       aoFaceIn.func_178204_a(blockAccessIn, blockIn, blockPosIn, var17.getFace(), quadBounds, boundsFlags);
/*     */       
/* 253 */       if (worldRendererIn.isMultiTexture()) {
/*     */         
/* 255 */         worldRendererIn.func_178981_a(var17.getVertexDataSingle());
/* 256 */         worldRendererIn.putSprite(var17.getSprite());
/*     */       }
/*     */       else {
/*     */         
/* 260 */         worldRendererIn.func_178981_a(var17.func_178209_a());
/*     */       } 
/*     */       
/* 263 */       worldRendererIn.func_178962_a(aoFaceIn.field_178207_c[0], aoFaceIn.field_178207_c[1], aoFaceIn.field_178207_c[2], aoFaceIn.field_178207_c[3]);
/* 264 */       int colorMultiplier1 = CustomColors.getColorMultiplier(var17, blockIn, blockAccessIn, blockPosIn, renderEnv);
/*     */       
/* 266 */       if (!var17.func_178212_b() && colorMultiplier1 == -1) {
/*     */         
/* 268 */         worldRendererIn.func_178978_a(aoFaceIn.field_178206_b[0], aoFaceIn.field_178206_b[0], aoFaceIn.field_178206_b[0], 4);
/* 269 */         worldRendererIn.func_178978_a(aoFaceIn.field_178206_b[1], aoFaceIn.field_178206_b[1], aoFaceIn.field_178206_b[1], 3);
/* 270 */         worldRendererIn.func_178978_a(aoFaceIn.field_178206_b[2], aoFaceIn.field_178206_b[2], aoFaceIn.field_178206_b[2], 2);
/* 271 */         worldRendererIn.func_178978_a(aoFaceIn.field_178206_b[3], aoFaceIn.field_178206_b[3], aoFaceIn.field_178206_b[3], 1);
/*     */       } else {
/*     */         int var18;
/*     */ 
/*     */ 
/*     */         
/* 277 */         if (colorMultiplier1 != -1) {
/*     */           
/* 279 */           var18 = colorMultiplier1;
/*     */         }
/*     */         else {
/*     */           
/* 283 */           var18 = blockIn.colorMultiplier(blockAccessIn, blockPosIn, var17.func_178211_c());
/*     */         } 
/*     */         
/* 286 */         if (EntityRenderer.anaglyphEnable)
/*     */         {
/* 288 */           var18 = TextureUtil.func_177054_c(var18);
/*     */         }
/*     */         
/* 291 */         float var19 = (var18 >> 16 & 0xFF) / 255.0F;
/* 292 */         float var20 = (var18 >> 8 & 0xFF) / 255.0F;
/* 293 */         float var21 = (var18 & 0xFF) / 255.0F;
/* 294 */         worldRendererIn.func_178978_a(aoFaceIn.field_178206_b[0] * var19, aoFaceIn.field_178206_b[0] * var20, aoFaceIn.field_178206_b[0] * var21, 4);
/* 295 */         worldRendererIn.func_178978_a(aoFaceIn.field_178206_b[1] * var19, aoFaceIn.field_178206_b[1] * var20, aoFaceIn.field_178206_b[1] * var21, 3);
/* 296 */         worldRendererIn.func_178978_a(aoFaceIn.field_178206_b[2] * var19, aoFaceIn.field_178206_b[2] * var20, aoFaceIn.field_178206_b[2] * var21, 2);
/* 297 */         worldRendererIn.func_178978_a(aoFaceIn.field_178206_b[3] * var19, aoFaceIn.field_178206_b[3] * var20, aoFaceIn.field_178206_b[3] * var21, 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178261_a(Block blockIn, int[] vertexData, EnumFacing facingIn, float[] quadBounds, BitSet boundsFlags) {
/* 304 */     float var6 = 32.0F;
/* 305 */     float var7 = 32.0F;
/* 306 */     float var8 = 32.0F;
/* 307 */     float var9 = -32.0F;
/* 308 */     float var10 = -32.0F;
/* 309 */     float var11 = -32.0F;
/* 310 */     int step = vertexData.length / 4;
/*     */ 
/*     */     
/* 313 */     for (int var16 = 0; var16 < 4; var16++) {
/*     */       
/* 315 */       float f1 = Float.intBitsToFloat(vertexData[var16 * step]);
/* 316 */       float var14 = Float.intBitsToFloat(vertexData[var16 * step + 1]);
/* 317 */       float var15 = Float.intBitsToFloat(vertexData[var16 * step + 2]);
/* 318 */       var6 = Math.min(var6, f1);
/* 319 */       var7 = Math.min(var7, var14);
/* 320 */       var8 = Math.min(var8, var15);
/* 321 */       var9 = Math.max(var9, f1);
/* 322 */       var10 = Math.max(var10, var14);
/* 323 */       var11 = Math.max(var11, var15);
/*     */     } 
/*     */     
/* 326 */     if (quadBounds != null) {
/*     */       
/* 328 */       quadBounds[EnumFacing.WEST.getIndex()] = var6;
/* 329 */       quadBounds[EnumFacing.EAST.getIndex()] = var9;
/* 330 */       quadBounds[EnumFacing.DOWN.getIndex()] = var7;
/* 331 */       quadBounds[EnumFacing.UP.getIndex()] = var10;
/* 332 */       quadBounds[EnumFacing.NORTH.getIndex()] = var8;
/* 333 */       quadBounds[EnumFacing.SOUTH.getIndex()] = var11;
/* 334 */       quadBounds[EnumFacing.WEST.getIndex() + EnumFacing.VALUES.length] = 1.0F - var6;
/* 335 */       quadBounds[EnumFacing.EAST.getIndex() + EnumFacing.VALUES.length] = 1.0F - var9;
/* 336 */       quadBounds[EnumFacing.DOWN.getIndex() + EnumFacing.VALUES.length] = 1.0F - var7;
/* 337 */       quadBounds[EnumFacing.UP.getIndex() + EnumFacing.VALUES.length] = 1.0F - var10;
/* 338 */       quadBounds[EnumFacing.NORTH.getIndex() + EnumFacing.VALUES.length] = 1.0F - var8;
/* 339 */       quadBounds[EnumFacing.SOUTH.getIndex() + EnumFacing.VALUES.length] = 1.0F - var11;
/*     */     } 
/*     */     
/* 342 */     float var17 = 1.0E-4F;
/* 343 */     float var13 = 0.9999F;
/*     */     
/* 345 */     switch (SwitchEnumFacing.field_178290_a[facingIn.ordinal()]) {
/*     */       
/*     */       case 1:
/* 348 */         boundsFlags.set(1, !(var6 < 1.0E-4F && var8 < 1.0E-4F && var9 > 0.9999F && var11 > 0.9999F));
/* 349 */         boundsFlags.set(0, ((var7 < 1.0E-4F || blockIn.isFullCube()) && var7 == var10));
/*     */         break;
/*     */       
/*     */       case 2:
/* 353 */         boundsFlags.set(1, !(var6 < 1.0E-4F && var8 < 1.0E-4F && var9 > 0.9999F && var11 > 0.9999F));
/* 354 */         boundsFlags.set(0, ((var10 > 0.9999F || blockIn.isFullCube()) && var7 == var10));
/*     */         break;
/*     */       
/*     */       case 3:
/* 358 */         boundsFlags.set(1, !(var6 < 1.0E-4F && var7 < 1.0E-4F && var9 > 0.9999F && var10 > 0.9999F));
/* 359 */         boundsFlags.set(0, ((var8 < 1.0E-4F || blockIn.isFullCube()) && var8 == var11));
/*     */         break;
/*     */       
/*     */       case 4:
/* 363 */         boundsFlags.set(1, !(var6 < 1.0E-4F && var7 < 1.0E-4F && var9 > 0.9999F && var10 > 0.9999F));
/* 364 */         boundsFlags.set(0, ((var11 > 0.9999F || blockIn.isFullCube()) && var8 == var11));
/*     */         break;
/*     */       
/*     */       case 5:
/* 368 */         boundsFlags.set(1, !(var7 < 1.0E-4F && var8 < 1.0E-4F && var10 > 0.9999F && var11 > 0.9999F));
/* 369 */         boundsFlags.set(0, ((var6 < 1.0E-4F || blockIn.isFullCube()) && var6 == var9));
/*     */         break;
/*     */       
/*     */       case 6:
/* 373 */         boundsFlags.set(1, !(var7 < 1.0E-4F && var8 < 1.0E-4F && var10 > 0.9999F && var11 > 0.9999F));
/* 374 */         boundsFlags.set(0, ((var9 > 0.9999F || blockIn.isFullCube()) && var6 == var9));
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderModelStandardQuads(IBlockAccess blockAccessIn, Block blockIn, BlockPos blockPosIn, EnumFacing faceIn, int brightnessIn, boolean ownBrightness, WorldRenderer worldRendererIn, List listQuadsIn, RenderEnv renderEnv) {
/* 380 */     BitSet boundsFlags = renderEnv.getBoundsFlags();
/* 381 */     IBlockState blockStateIn = renderEnv.getBlockState();
/* 382 */     double var10 = blockPosIn.getX();
/* 383 */     double var12 = blockPosIn.getY();
/* 384 */     double var14 = blockPosIn.getZ();
/* 385 */     Block.EnumOffsetType var16 = blockIn.getOffsetType();
/*     */     
/* 387 */     if (var16 != Block.EnumOffsetType.NONE) {
/*     */       
/* 389 */       int var23 = blockPosIn.getX();
/* 390 */       int var24 = blockPosIn.getZ();
/* 391 */       long colorMultiplier = (var23 * 3129871) ^ var24 * 116129781L;
/* 392 */       colorMultiplier = colorMultiplier * colorMultiplier * 42317861L + colorMultiplier * 11L;
/* 393 */       var10 += (((float)(colorMultiplier >> 16L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
/* 394 */       var14 += (((float)(colorMultiplier >> 24L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
/*     */       
/* 396 */       if (var16 == Block.EnumOffsetType.XYZ)
/*     */       {
/* 398 */         var12 += (((float)(colorMultiplier >> 20L & 0xFL) / 15.0F) - 1.0D) * 0.2D;
/*     */       }
/*     */     } 
/*     */     
/* 402 */     for (Iterator<BakedQuad> var231 = listQuadsIn.iterator(); var231.hasNext(); worldRendererIn.func_178987_a(var10, var12, var14)) {
/*     */       
/* 404 */       BakedQuad var241 = var231.next();
/*     */       
/* 406 */       if (!renderEnv.isBreakingAnimation(var241)) {
/*     */         
/* 408 */         BakedQuad colorMultiplier1 = var241;
/*     */         
/* 410 */         if (Config.isConnectedTextures())
/*     */         {
/* 412 */           var241 = ConnectedTextures.getConnectedTexture(blockAccessIn, blockStateIn, blockPosIn, var241, renderEnv);
/*     */         }
/*     */         
/* 415 */         if (var241 == colorMultiplier1 && Config.isNaturalTextures())
/*     */         {
/* 417 */           var241 = NaturalTextures.getNaturalTexture(blockPosIn, var241);
/*     */         }
/*     */       } 
/*     */       
/* 421 */       if (ownBrightness) {
/*     */         
/* 423 */         func_178261_a(blockIn, var241.func_178209_a(), var241.getFace(), null, boundsFlags);
/* 424 */         brightnessIn = boundsFlags.get(0) ? blockIn.getMixedBrightnessForBlock(blockAccessIn, blockPosIn.offset(var241.getFace())) : blockIn.getMixedBrightnessForBlock(blockAccessIn, blockPosIn);
/*     */       } 
/*     */       
/* 427 */       if (worldRendererIn.isMultiTexture()) {
/*     */         
/* 429 */         worldRendererIn.func_178981_a(var241.getVertexDataSingle());
/* 430 */         worldRendererIn.putSprite(var241.getSprite());
/*     */       }
/*     */       else {
/*     */         
/* 434 */         worldRendererIn.func_178981_a(var241.func_178209_a());
/*     */       } 
/*     */       
/* 437 */       worldRendererIn.func_178962_a(brightnessIn, brightnessIn, brightnessIn, brightnessIn);
/* 438 */       int colorMultiplier2 = CustomColors.getColorMultiplier(var241, blockIn, blockAccessIn, blockPosIn, renderEnv);
/*     */       
/* 440 */       if (var241.func_178212_b() || colorMultiplier2 != -1) {
/*     */         int var25;
/*     */ 
/*     */         
/* 444 */         if (colorMultiplier2 != -1) {
/*     */           
/* 446 */           var25 = colorMultiplier2;
/*     */         }
/*     */         else {
/*     */           
/* 450 */           var25 = blockIn.colorMultiplier(blockAccessIn, blockPosIn, var241.func_178211_c());
/*     */         } 
/*     */         
/* 453 */         if (EntityRenderer.anaglyphEnable)
/*     */         {
/* 455 */           var25 = TextureUtil.func_177054_c(var25);
/*     */         }
/*     */         
/* 458 */         float var20 = (var25 >> 16 & 0xFF) / 255.0F;
/* 459 */         float var21 = (var25 >> 8 & 0xFF) / 255.0F;
/* 460 */         float var22 = (var25 & 0xFF) / 255.0F;
/* 461 */         worldRendererIn.func_178978_a(var20, var21, var22, 4);
/* 462 */         worldRendererIn.func_178978_a(var20, var21, var22, 3);
/* 463 */         worldRendererIn.func_178978_a(var20, var21, var22, 2);
/* 464 */         worldRendererIn.func_178978_a(var20, var21, var22, 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178262_a(IBakedModel p_178262_1_, float p_178262_2_, float p_178262_3_, float p_178262_4_, float p_178262_5_) {
/* 471 */     EnumFacing[] var6 = EnumFacing.VALUES;
/* 472 */     int var7 = var6.length;
/*     */     
/* 474 */     for (int var8 = 0; var8 < var7; var8++) {
/*     */       
/* 476 */       EnumFacing var9 = var6[var8];
/* 477 */       func_178264_a(p_178262_2_, p_178262_3_, p_178262_4_, p_178262_5_, p_178262_1_.func_177551_a(var9));
/*     */     } 
/*     */     
/* 480 */     func_178264_a(p_178262_2_, p_178262_3_, p_178262_4_, p_178262_5_, p_178262_1_.func_177550_a());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178266_a(IBakedModel p_178266_1_, IBlockState p_178266_2_, float p_178266_3_, boolean p_178266_4_) {
/* 485 */     Block var5 = p_178266_2_.getBlock();
/* 486 */     var5.setBlockBoundsForItemRender();
/* 487 */     GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
/* 488 */     int var6 = var5.getRenderColor(var5.getStateForEntityRender(p_178266_2_));
/*     */     
/* 490 */     if (EntityRenderer.anaglyphEnable)
/*     */     {
/* 492 */       var6 = TextureUtil.func_177054_c(var6);
/*     */     }
/*     */     
/* 495 */     float var7 = (var6 >> 16 & 0xFF) / 255.0F;
/* 496 */     float var8 = (var6 >> 8 & 0xFF) / 255.0F;
/* 497 */     float var9 = (var6 & 0xFF) / 255.0F;
/*     */     
/* 499 */     if (!p_178266_4_)
/*     */     {
/* 501 */       GlStateManager.color(p_178266_3_, p_178266_3_, p_178266_3_, 1.0F);
/*     */     }
/*     */     
/* 504 */     func_178262_a(p_178266_1_, p_178266_3_, var7, var8, var9);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178264_a(float p_178264_1_, float p_178264_2_, float p_178264_3_, float p_178264_4_, List p_178264_5_) {
/* 509 */     Tessellator var6 = Tessellator.getInstance();
/* 510 */     WorldRenderer var7 = var6.getWorldRenderer();
/* 511 */     Iterator<BakedQuad> var8 = p_178264_5_.iterator();
/*     */     
/* 513 */     while (var8.hasNext()) {
/*     */       
/* 515 */       BakedQuad var9 = var8.next();
/* 516 */       var7.startDrawingQuads();
/* 517 */       var7.setVertexFormat(DefaultVertexFormats.field_176599_b);
/* 518 */       var7.func_178981_a(var9.func_178209_a());
/*     */       
/* 520 */       if (var9.func_178212_b()) {
/*     */         
/* 522 */         var7.func_178990_f(p_178264_2_ * p_178264_1_, p_178264_3_ * p_178264_1_, p_178264_4_ * p_178264_1_);
/*     */       }
/*     */       else {
/*     */         
/* 526 */         var7.func_178990_f(p_178264_1_, p_178264_1_, p_178264_1_);
/*     */       } 
/*     */       
/* 529 */       Vec3i var10 = var9.getFace().getDirectionVec();
/* 530 */       var7.func_178975_e(var10.getX(), var10.getY(), var10.getZ());
/* 531 */       var6.draw();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static float fixAoLightValue(float val) {
/* 537 */     return (val == 0.2F) ? aoLightValueOpaque : val;
/*     */   }
/*     */   
/*     */   public static class AmbientOcclusionFace
/*     */   {
/* 542 */     private final float[] field_178206_b = new float[4];
/* 543 */     private final int[] field_178207_c = new int[4]; public void func_178204_a(IBlockAccess blockAccessIn, Block blockIn, BlockPos blockPosIn, EnumFacing facingIn, float[] quadBounds, BitSet boundsFlags) {
/*     */       float var25;
/*     */       int var29;
/*     */       float var26;
/*     */       int var30;
/*     */       float var27;
/*     */       int var31;
/*     */       float var28;
/*     */       int var32;
/* 552 */       BlockPos var7 = boundsFlags.get(0) ? blockPosIn.offset(facingIn) : blockPosIn;
/* 553 */       BlockModelRenderer.EnumNeighborInfo var8 = BlockModelRenderer.EnumNeighborInfo.func_178273_a(facingIn);
/* 554 */       BlockPos var9 = var7.offset(var8.field_178276_g[0]);
/* 555 */       BlockPos var10 = var7.offset(var8.field_178276_g[1]);
/* 556 */       BlockPos var11 = var7.offset(var8.field_178276_g[2]);
/* 557 */       BlockPos var12 = var7.offset(var8.field_178276_g[3]);
/* 558 */       int var13 = blockIn.getMixedBrightnessForBlock(blockAccessIn, var9);
/* 559 */       int var14 = blockIn.getMixedBrightnessForBlock(blockAccessIn, var10);
/* 560 */       int var15 = blockIn.getMixedBrightnessForBlock(blockAccessIn, var11);
/* 561 */       int var16 = blockIn.getMixedBrightnessForBlock(blockAccessIn, var12);
/* 562 */       float var17 = BlockModelRenderer.fixAoLightValue(blockAccessIn.getBlockState(var9).getBlock().getAmbientOcclusionLightValue());
/* 563 */       float var18 = BlockModelRenderer.fixAoLightValue(blockAccessIn.getBlockState(var10).getBlock().getAmbientOcclusionLightValue());
/* 564 */       float var19 = BlockModelRenderer.fixAoLightValue(blockAccessIn.getBlockState(var11).getBlock().getAmbientOcclusionLightValue());
/* 565 */       float var20 = BlockModelRenderer.fixAoLightValue(blockAccessIn.getBlockState(var12).getBlock().getAmbientOcclusionLightValue());
/* 566 */       boolean var21 = blockAccessIn.getBlockState(var9.offset(facingIn)).getBlock().isTranslucent();
/* 567 */       boolean var22 = blockAccessIn.getBlockState(var10.offset(facingIn)).getBlock().isTranslucent();
/* 568 */       boolean var23 = blockAccessIn.getBlockState(var11.offset(facingIn)).getBlock().isTranslucent();
/* 569 */       boolean var24 = blockAccessIn.getBlockState(var12.offset(facingIn)).getBlock().isTranslucent();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 574 */       if (!var23 && !var21) {
/*     */         
/* 576 */         var25 = var17;
/* 577 */         var29 = var13;
/*     */       }
/*     */       else {
/*     */         
/* 581 */         BlockPos var33 = var9.offset(var8.field_178276_g[2]);
/* 582 */         var25 = BlockModelRenderer.fixAoLightValue(blockAccessIn.getBlockState(var33).getBlock().getAmbientOcclusionLightValue());
/* 583 */         var29 = blockIn.getMixedBrightnessForBlock(blockAccessIn, var33);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 589 */       if (!var24 && !var21) {
/*     */         
/* 591 */         var26 = var17;
/* 592 */         var30 = var13;
/*     */       }
/*     */       else {
/*     */         
/* 596 */         BlockPos var33 = var9.offset(var8.field_178276_g[3]);
/* 597 */         var26 = BlockModelRenderer.fixAoLightValue(blockAccessIn.getBlockState(var33).getBlock().getAmbientOcclusionLightValue());
/* 598 */         var30 = blockIn.getMixedBrightnessForBlock(blockAccessIn, var33);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 604 */       if (!var23 && !var22) {
/*     */         
/* 606 */         var27 = var18;
/* 607 */         var31 = var14;
/*     */       }
/*     */       else {
/*     */         
/* 611 */         BlockPos var33 = var10.offset(var8.field_178276_g[2]);
/* 612 */         var27 = BlockModelRenderer.fixAoLightValue(blockAccessIn.getBlockState(var33).getBlock().getAmbientOcclusionLightValue());
/* 613 */         var31 = blockIn.getMixedBrightnessForBlock(blockAccessIn, var33);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 619 */       if (!var24 && !var22) {
/*     */         
/* 621 */         var28 = var18;
/* 622 */         var32 = var14;
/*     */       }
/*     */       else {
/*     */         
/* 626 */         BlockPos var33 = var10.offset(var8.field_178276_g[3]);
/* 627 */         var28 = BlockModelRenderer.fixAoLightValue(blockAccessIn.getBlockState(var33).getBlock().getAmbientOcclusionLightValue());
/* 628 */         var32 = blockIn.getMixedBrightnessForBlock(blockAccessIn, var33);
/*     */       } 
/*     */       
/* 631 */       int var60 = blockIn.getMixedBrightnessForBlock(blockAccessIn, blockPosIn);
/*     */       
/* 633 */       if (boundsFlags.get(0) || !blockAccessIn.getBlockState(blockPosIn.offset(facingIn)).getBlock().isOpaqueCube())
/*     */       {
/* 635 */         var60 = blockIn.getMixedBrightnessForBlock(blockAccessIn, blockPosIn.offset(facingIn));
/*     */       }
/*     */       
/* 638 */       float var34 = boundsFlags.get(0) ? blockAccessIn.getBlockState(var7).getBlock().getAmbientOcclusionLightValue() : blockAccessIn.getBlockState(blockPosIn).getBlock().getAmbientOcclusionLightValue();
/* 639 */       var34 = BlockModelRenderer.fixAoLightValue(var34);
/* 640 */       BlockModelRenderer.VertexTranslations var35 = BlockModelRenderer.VertexTranslations.func_178184_a(facingIn);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 646 */       if (boundsFlags.get(1) && var8.field_178289_i) {
/*     */         
/* 648 */         float var36 = (var20 + var17 + var26 + var34) * 0.25F;
/* 649 */         float var37 = (var19 + var17 + var25 + var34) * 0.25F;
/* 650 */         float var38 = (var19 + var18 + var27 + var34) * 0.25F;
/* 651 */         float var39 = (var20 + var18 + var28 + var34) * 0.25F;
/* 652 */         float var40 = quadBounds[(var8.field_178286_j[0]).field_178229_m] * quadBounds[(var8.field_178286_j[1]).field_178229_m];
/* 653 */         float var41 = quadBounds[(var8.field_178286_j[2]).field_178229_m] * quadBounds[(var8.field_178286_j[3]).field_178229_m];
/* 654 */         float var42 = quadBounds[(var8.field_178286_j[4]).field_178229_m] * quadBounds[(var8.field_178286_j[5]).field_178229_m];
/* 655 */         float var43 = quadBounds[(var8.field_178286_j[6]).field_178229_m] * quadBounds[(var8.field_178286_j[7]).field_178229_m];
/* 656 */         float var44 = quadBounds[(var8.field_178287_k[0]).field_178229_m] * quadBounds[(var8.field_178287_k[1]).field_178229_m];
/* 657 */         float var45 = quadBounds[(var8.field_178287_k[2]).field_178229_m] * quadBounds[(var8.field_178287_k[3]).field_178229_m];
/* 658 */         float var46 = quadBounds[(var8.field_178287_k[4]).field_178229_m] * quadBounds[(var8.field_178287_k[5]).field_178229_m];
/* 659 */         float var47 = quadBounds[(var8.field_178287_k[6]).field_178229_m] * quadBounds[(var8.field_178287_k[7]).field_178229_m];
/* 660 */         float var48 = quadBounds[(var8.field_178284_l[0]).field_178229_m] * quadBounds[(var8.field_178284_l[1]).field_178229_m];
/* 661 */         float var49 = quadBounds[(var8.field_178284_l[2]).field_178229_m] * quadBounds[(var8.field_178284_l[3]).field_178229_m];
/* 662 */         float var50 = quadBounds[(var8.field_178284_l[4]).field_178229_m] * quadBounds[(var8.field_178284_l[5]).field_178229_m];
/* 663 */         float var51 = quadBounds[(var8.field_178284_l[6]).field_178229_m] * quadBounds[(var8.field_178284_l[7]).field_178229_m];
/* 664 */         float var52 = quadBounds[(var8.field_178285_m[0]).field_178229_m] * quadBounds[(var8.field_178285_m[1]).field_178229_m];
/* 665 */         float var53 = quadBounds[(var8.field_178285_m[2]).field_178229_m] * quadBounds[(var8.field_178285_m[3]).field_178229_m];
/* 666 */         float var54 = quadBounds[(var8.field_178285_m[4]).field_178229_m] * quadBounds[(var8.field_178285_m[5]).field_178229_m];
/* 667 */         float var55 = quadBounds[(var8.field_178285_m[6]).field_178229_m] * quadBounds[(var8.field_178285_m[7]).field_178229_m];
/* 668 */         this.field_178206_b[var35.field_178191_g] = var36 * var40 + var37 * var41 + var38 * var42 + var39 * var43;
/* 669 */         this.field_178206_b[var35.field_178200_h] = var36 * var44 + var37 * var45 + var38 * var46 + var39 * var47;
/* 670 */         this.field_178206_b[var35.field_178201_i] = var36 * var48 + var37 * var49 + var38 * var50 + var39 * var51;
/* 671 */         this.field_178206_b[var35.field_178198_j] = var36 * var52 + var37 * var53 + var38 * var54 + var39 * var55;
/* 672 */         int var56 = getAoBrightness(var16, var13, var30, var60);
/* 673 */         int var57 = getAoBrightness(var15, var13, var29, var60);
/* 674 */         int var58 = getAoBrightness(var15, var14, var31, var60);
/* 675 */         int var59 = getAoBrightness(var16, var14, var32, var60);
/* 676 */         this.field_178207_c[var35.field_178191_g] = func_178203_a(var56, var57, var58, var59, var40, var41, var42, var43);
/* 677 */         this.field_178207_c[var35.field_178200_h] = func_178203_a(var56, var57, var58, var59, var44, var45, var46, var47);
/* 678 */         this.field_178207_c[var35.field_178201_i] = func_178203_a(var56, var57, var58, var59, var48, var49, var50, var51);
/* 679 */         this.field_178207_c[var35.field_178198_j] = func_178203_a(var56, var57, var58, var59, var52, var53, var54, var55);
/*     */       }
/*     */       else {
/*     */         
/* 683 */         float var36 = (var20 + var17 + var26 + var34) * 0.25F;
/* 684 */         float var37 = (var19 + var17 + var25 + var34) * 0.25F;
/* 685 */         float var38 = (var19 + var18 + var27 + var34) * 0.25F;
/* 686 */         float var39 = (var20 + var18 + var28 + var34) * 0.25F;
/* 687 */         this.field_178207_c[var35.field_178191_g] = getAoBrightness(var16, var13, var30, var60);
/* 688 */         this.field_178207_c[var35.field_178200_h] = getAoBrightness(var15, var13, var29, var60);
/* 689 */         this.field_178207_c[var35.field_178201_i] = getAoBrightness(var15, var14, var31, var60);
/* 690 */         this.field_178207_c[var35.field_178198_j] = getAoBrightness(var16, var14, var32, var60);
/* 691 */         this.field_178206_b[var35.field_178191_g] = var36;
/* 692 */         this.field_178206_b[var35.field_178200_h] = var37;
/* 693 */         this.field_178206_b[var35.field_178201_i] = var38;
/* 694 */         this.field_178206_b[var35.field_178198_j] = var39;
/*     */       } 
/*     */     }
/*     */     private static final String __OBFID = "CL_00002515";
/*     */     
/*     */     private int getAoBrightness(int p_147778_1_, int p_147778_2_, int p_147778_3_, int p_147778_4_) {
/* 700 */       if (p_147778_1_ == 0)
/*     */       {
/* 702 */         p_147778_1_ = p_147778_4_;
/*     */       }
/*     */       
/* 705 */       if (p_147778_2_ == 0)
/*     */       {
/* 707 */         p_147778_2_ = p_147778_4_;
/*     */       }
/*     */       
/* 710 */       if (p_147778_3_ == 0)
/*     */       {
/* 712 */         p_147778_3_ = p_147778_4_;
/*     */       }
/*     */       
/* 715 */       return p_147778_1_ + p_147778_2_ + p_147778_3_ + p_147778_4_ >> 2 & 0xFF00FF;
/*     */     }
/*     */ 
/*     */     
/*     */     private int func_178203_a(int p_178203_1_, int p_178203_2_, int p_178203_3_, int p_178203_4_, float p_178203_5_, float p_178203_6_, float p_178203_7_, float p_178203_8_) {
/* 720 */       int var9 = (int)((p_178203_1_ >> 16 & 0xFF) * p_178203_5_ + (p_178203_2_ >> 16 & 0xFF) * p_178203_6_ + (p_178203_3_ >> 16 & 0xFF) * p_178203_7_ + (p_178203_4_ >> 16 & 0xFF) * p_178203_8_) & 0xFF;
/* 721 */       int var10 = (int)((p_178203_1_ & 0xFF) * p_178203_5_ + (p_178203_2_ & 0xFF) * p_178203_6_ + (p_178203_3_ & 0xFF) * p_178203_7_ + (p_178203_4_ & 0xFF) * p_178203_8_) & 0xFF;
/* 722 */       return var9 << 16 | var10;
/*     */     }
/*     */     public AmbientOcclusionFace(BlockModelRenderer bmr) {}
/*     */     
/*     */     public AmbientOcclusionFace() {} }
/*     */   
/* 728 */   public enum EnumNeighborInfo { DOWN("DOWN", 0, "DOWN", 0, (String)new EnumFacing[] { EnumFacing.WEST, EnumFacing.EAST, EnumFacing.NORTH, EnumFacing.SOUTH }, 0.5F, true, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.SOUTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.NORTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.NORTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.SOUTH }),
/* 729 */     UP("UP", 1, "UP", 1, (String)new EnumFacing[] { EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH }, 1.0F, true, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.SOUTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.NORTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.NORTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.SOUTH }),
/* 730 */     NORTH("NORTH", 2, "NORTH", 2, (String)new EnumFacing[] { EnumFacing.UP, EnumFacing.DOWN, EnumFacing.EAST, EnumFacing.WEST }, 0.8F, true, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_WEST }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_EAST }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_EAST }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_WEST }),
/* 731 */     SOUTH("SOUTH", 3, "SOUTH", 3, (String)new EnumFacing[] { EnumFacing.WEST, EnumFacing.EAST, EnumFacing.DOWN, EnumFacing.UP }, 0.8F, true, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.WEST }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_WEST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.WEST, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.WEST }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.EAST }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_EAST, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.EAST, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.EAST }),
/* 732 */     WEST("WEST", 4, "WEST", 4, (String)new EnumFacing[] { EnumFacing.UP, EnumFacing.DOWN, EnumFacing.NORTH, EnumFacing.SOUTH }, 0.6F, true, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.SOUTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.NORTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.NORTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.SOUTH }),
/* 733 */     EAST("EAST", 5, "EAST", 5, (String)new EnumFacing[] { EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH }, 0.6F, true, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.SOUTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.FLIP_DOWN, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.DOWN, BlockModelRenderer.Orientation.NORTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.NORTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_NORTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.NORTH }, new BlockModelRenderer.Orientation[] { BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.SOUTH, BlockModelRenderer.Orientation.FLIP_UP, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.FLIP_SOUTH, BlockModelRenderer.Orientation.UP, BlockModelRenderer.Orientation.SOUTH });
/*     */     protected final EnumFacing[] field_178276_g;
/*     */     protected final float field_178288_h;
/*     */     protected final boolean field_178289_i;
/*     */     protected final BlockModelRenderer.Orientation[] field_178286_j;
/*     */     protected final BlockModelRenderer.Orientation[] field_178287_k;
/*     */     protected final BlockModelRenderer.Orientation[] field_178284_l;
/*     */     protected final BlockModelRenderer.Orientation[] field_178285_m;
/* 741 */     private static final EnumNeighborInfo[] field_178282_n = new EnumNeighborInfo[6];
/* 742 */     private static final EnumNeighborInfo[] $VALUES = new EnumNeighborInfo[] { DOWN, UP, NORTH, SOUTH, WEST, EAST };
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002516";
/*     */ 
/*     */ 
/*     */     
/*     */     EnumNeighborInfo(String p_i46381_1_, int p_i46381_2_, String p_i46236_1_, int p_i46236_2_, EnumFacing[] p_i46236_3_, float p_i46236_4_, boolean p_i46236_5_, BlockModelRenderer.Orientation[] p_i46236_6_, BlockModelRenderer.Orientation[] p_i46236_7_, BlockModelRenderer.Orientation[] p_i46236_8_, BlockModelRenderer.Orientation[] p_i46236_9_) {
/*     */       this.field_178276_g = p_i46236_3_;
/*     */       this.field_178288_h = p_i46236_4_;
/*     */       this.field_178289_i = p_i46236_5_;
/*     */       this.field_178286_j = p_i46236_6_;
/*     */       this.field_178287_k = p_i46236_7_;
/*     */       this.field_178284_l = p_i46236_8_;
/*     */       this.field_178285_m = p_i46236_9_;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 763 */       field_178282_n[EnumFacing.DOWN.getIndex()] = DOWN;
/* 764 */       field_178282_n[EnumFacing.UP.getIndex()] = UP;
/* 765 */       field_178282_n[EnumFacing.NORTH.getIndex()] = NORTH;
/* 766 */       field_178282_n[EnumFacing.SOUTH.getIndex()] = SOUTH;
/* 767 */       field_178282_n[EnumFacing.WEST.getIndex()] = WEST;
/* 768 */       field_178282_n[EnumFacing.EAST.getIndex()] = EAST;
/*     */     }
/*     */     public static EnumNeighborInfo func_178273_a(EnumFacing p_178273_0_) {
/*     */       return field_178282_n[p_178273_0_.getIndex()];
/*     */     } }
/*     */   
/* 774 */   public enum Orientation { DOWN("DOWN", 0, "DOWN", 0, (String)EnumFacing.DOWN, false),
/* 775 */     UP("UP", 1, "UP", 1, (String)EnumFacing.UP, false),
/* 776 */     NORTH("NORTH", 2, "NORTH", 2, (String)EnumFacing.NORTH, false),
/* 777 */     SOUTH("SOUTH", 3, "SOUTH", 3, (String)EnumFacing.SOUTH, false),
/* 778 */     WEST("WEST", 4, "WEST", 4, (String)EnumFacing.WEST, false),
/* 779 */     EAST("EAST", 5, "EAST", 5, (String)EnumFacing.EAST, false),
/* 780 */     FLIP_DOWN("FLIP_DOWN", 6, "FLIP_DOWN", 6, (String)EnumFacing.DOWN, true),
/* 781 */     FLIP_UP("FLIP_UP", 7, "FLIP_UP", 7, (String)EnumFacing.UP, true),
/* 782 */     FLIP_NORTH("FLIP_NORTH", 8, "FLIP_NORTH", 8, (String)EnumFacing.NORTH, true),
/* 783 */     FLIP_SOUTH("FLIP_SOUTH", 9, "FLIP_SOUTH", 9, (String)EnumFacing.SOUTH, true),
/* 784 */     FLIP_WEST("FLIP_WEST", 10, "FLIP_WEST", 10, (String)EnumFacing.WEST, true),
/* 785 */     FLIP_EAST("FLIP_EAST", 11, "FLIP_EAST", 11, (String)EnumFacing.EAST, true);
/*     */     protected final int field_178229_m;
/* 787 */     private static final Orientation[] $VALUES = new Orientation[] { DOWN, UP, NORTH, SOUTH, WEST, EAST, FLIP_DOWN, FLIP_UP, FLIP_NORTH, FLIP_SOUTH, FLIP_WEST, FLIP_EAST }; private static final String __OBFID = "CL_00002513";
/*     */     static {
/*     */     
/*     */     }
/*     */     Orientation(String p_i46383_1_, int p_i46383_2_, String p_i46233_1_, int p_i46233_2_, EnumFacing p_i46233_3_, boolean p_i46233_4_) {
/* 792 */       this.field_178229_m = p_i46233_3_.getIndex() + (p_i46233_4_ ? (EnumFacing.values()).length : 0);
/*     */     } }
/*     */ 
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 798 */     static final int[] field_178290_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002517";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 805 */         field_178290_a[EnumFacing.DOWN.ordinal()] = 1;
/*     */       }
/* 807 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 814 */         field_178290_a[EnumFacing.UP.ordinal()] = 2;
/*     */       }
/* 816 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 823 */         field_178290_a[EnumFacing.NORTH.ordinal()] = 3;
/*     */       }
/* 825 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 832 */         field_178290_a[EnumFacing.SOUTH.ordinal()] = 4;
/*     */       }
/* 834 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 841 */         field_178290_a[EnumFacing.WEST.ordinal()] = 5;
/*     */       }
/* 843 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 850 */         field_178290_a[EnumFacing.EAST.ordinal()] = 6;
/*     */       }
/* 852 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   enum VertexTranslations
/*     */   {
/* 861 */     DOWN("DOWN", 0, "DOWN", 0, 0, 1, 2, 3),
/* 862 */     UP("UP", 1, "UP", 1, 2, 3, 0, 1),
/* 863 */     NORTH("NORTH", 2, "NORTH", 2, 3, 0, 1, 2),
/* 864 */     SOUTH("SOUTH", 3, "SOUTH", 3, 0, 1, 2, 3),
/* 865 */     WEST("WEST", 4, "WEST", 4, 3, 0, 1, 2),
/* 866 */     EAST("EAST", 5, "EAST", 5, 1, 2, 3, 0);
/*     */     private final int field_178191_g;
/*     */     private final int field_178200_h;
/*     */     private final int field_178201_i;
/*     */     private final int field_178198_j;
/* 871 */     private static final VertexTranslations[] field_178199_k = new VertexTranslations[6];
/* 872 */     private static final VertexTranslations[] $VALUES = new VertexTranslations[] { DOWN, UP, NORTH, SOUTH, WEST, EAST };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002514";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 890 */       field_178199_k[EnumFacing.DOWN.getIndex()] = DOWN;
/* 891 */       field_178199_k[EnumFacing.UP.getIndex()] = UP;
/* 892 */       field_178199_k[EnumFacing.NORTH.getIndex()] = NORTH;
/* 893 */       field_178199_k[EnumFacing.SOUTH.getIndex()] = SOUTH;
/* 894 */       field_178199_k[EnumFacing.WEST.getIndex()] = WEST;
/* 895 */       field_178199_k[EnumFacing.EAST.getIndex()] = EAST;
/*     */     }
/*     */     
/*     */     VertexTranslations(String p_i46382_1_, int p_i46382_2_, String p_i46234_1_, int p_i46234_2_, int p_i46234_3_, int p_i46234_4_, int p_i46234_5_, int p_i46234_6_) {
/*     */       this.field_178191_g = p_i46234_3_;
/*     */       this.field_178200_h = p_i46234_4_;
/*     */       this.field_178201_i = p_i46234_5_;
/*     */       this.field_178198_j = p_i46234_6_;
/*     */     }
/*     */     
/*     */     public static VertexTranslations func_178184_a(EnumFacing p_178184_0_) {
/*     */       return field_178199_k[p_178184_0_.getIndex()];
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\BlockModelRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */