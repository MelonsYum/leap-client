/*      */ package optifine;
/*      */ 
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.InputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashSet;
/*      */ import java.util.IdentityHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.state.BlockStateBase;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.client.Minecraft;
/*      */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*      */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*      */ import net.minecraft.client.renderer.texture.TextureMap;
/*      */ import net.minecraft.client.resources.IResourcePack;
/*      */ import net.minecraft.client.resources.model.IBakedModel;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.world.IBlockAccess;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConnectedTextures
/*      */ {
/*   33 */   private static Map[] spriteQuadMaps = null;
/*   34 */   private static ConnectedProperties[][] blockProperties = null;
/*   35 */   private static ConnectedProperties[][] tileProperties = null;
/*      */   private static boolean multipass = false;
/*      */   private static final int Y_NEG_DOWN = 0;
/*      */   private static final int Y_POS_UP = 1;
/*      */   private static final int Z_NEG_NORTH = 2;
/*      */   private static final int Z_POS_SOUTH = 3;
/*      */   private static final int X_NEG_WEST = 4;
/*      */   private static final int X_POS_EAST = 5;
/*      */   private static final int Y_AXIS = 0;
/*      */   private static final int Z_AXIS = 1;
/*      */   private static final int X_AXIS = 2;
/*   46 */   private static final String[] propSuffixes = new String[] { "", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
/*   47 */   private static final int[] ctmIndexes = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46 };
/*   48 */   public static final IBlockState AIR_DEFAULT_STATE = Blocks.air.getDefaultState();
/*   49 */   private static TextureAtlasSprite emptySprite = null;
/*      */ 
/*      */   
/*      */   public static synchronized BakedQuad getConnectedTexture(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, BakedQuad quad, RenderEnv renderEnv) {
/*   53 */     TextureAtlasSprite spriteIn = quad.getSprite();
/*      */     
/*   55 */     if (spriteIn == null)
/*      */     {
/*   57 */       return quad;
/*      */     }
/*      */ 
/*      */     
/*   61 */     Block block = blockState.getBlock();
/*   62 */     EnumFacing side = quad.getFace();
/*      */     
/*   64 */     if (block instanceof net.minecraft.block.BlockPane && spriteIn.getIconName().startsWith("minecraft:blocks/glass_pane_top")) {
/*      */       
/*   66 */       IBlockState sprite = blockAccess.getBlockState(blockPos.offset(quad.getFace()));
/*      */       
/*   68 */       if (sprite == blockState)
/*      */       {
/*   70 */         return getQuad(emptySprite, block, blockState, quad);
/*      */       }
/*      */     } 
/*      */     
/*   74 */     TextureAtlasSprite sprite1 = getConnectedTextureMultiPass(blockAccess, blockState, blockPos, side, spriteIn, renderEnv);
/*   75 */     return (sprite1 == spriteIn) ? quad : getQuad(sprite1, block, blockState, quad);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static BakedQuad getQuad(TextureAtlasSprite sprite, Block block, IBlockState blockState, BakedQuad quadIn) {
/*   81 */     if (spriteQuadMaps == null)
/*      */     {
/*   83 */       return quadIn;
/*      */     }
/*      */ 
/*      */     
/*   87 */     int spriteIndex = sprite.getIndexInMap();
/*      */     
/*   89 */     if (spriteIndex >= 0 && spriteIndex < spriteQuadMaps.length) {
/*      */       
/*   91 */       Object<Object, Object> quadMap = (Object<Object, Object>)spriteQuadMaps[spriteIndex];
/*      */       
/*   93 */       if (quadMap == null) {
/*      */         
/*   95 */         quadMap = (Object<Object, Object>)new IdentityHashMap<>(1);
/*   96 */         spriteQuadMaps[spriteIndex] = (Map)quadMap;
/*      */       } 
/*      */       
/*   99 */       BakedQuad quad = (BakedQuad)((Map)quadMap).get(quadIn);
/*      */       
/*  101 */       if (quad == null) {
/*      */         
/*  103 */         quad = makeSpriteQuad(quadIn, sprite);
/*  104 */         ((Map)quadMap).put(quadIn, quad);
/*      */       } 
/*      */       
/*  107 */       return quad;
/*      */     } 
/*      */ 
/*      */     
/*  111 */     return quadIn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static BakedQuad makeSpriteQuad(BakedQuad quad, TextureAtlasSprite sprite) {
/*  118 */     int[] data = (int[])quad.func_178209_a().clone();
/*  119 */     TextureAtlasSprite spriteFrom = quad.getSprite();
/*      */     
/*  121 */     for (int bq = 0; bq < 4; bq++)
/*      */     {
/*  123 */       fixVertex(data, bq, spriteFrom, sprite);
/*      */     }
/*      */     
/*  126 */     BakedQuad var5 = new BakedQuad(data, quad.func_178211_c(), quad.getFace(), sprite);
/*  127 */     return var5;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void fixVertex(int[] data, int vertex, TextureAtlasSprite spriteFrom, TextureAtlasSprite spriteTo) {
/*  132 */     int mul = data.length / 4;
/*  133 */     int pos = mul * vertex;
/*  134 */     float u = Float.intBitsToFloat(data[pos + 4]);
/*  135 */     float v = Float.intBitsToFloat(data[pos + 4 + 1]);
/*  136 */     double su16 = spriteFrom.getSpriteU16(u);
/*  137 */     double sv16 = spriteFrom.getSpriteV16(v);
/*  138 */     data[pos + 4] = Float.floatToRawIntBits(spriteTo.getInterpolatedU(su16));
/*  139 */     data[pos + 4 + 1] = Float.floatToRawIntBits(spriteTo.getInterpolatedV(sv16));
/*      */   }
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureMultiPass(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing side, TextureAtlasSprite icon, RenderEnv renderEnv) {
/*  144 */     TextureAtlasSprite newIcon = getConnectedTextureSingle(blockAccess, blockState, blockPos, side, icon, true, renderEnv);
/*      */     
/*  146 */     if (!multipass)
/*      */     {
/*  148 */       return newIcon;
/*      */     }
/*  150 */     if (newIcon == icon)
/*      */     {
/*  152 */       return newIcon;
/*      */     }
/*      */ 
/*      */     
/*  156 */     TextureAtlasSprite mpIcon = newIcon;
/*      */     
/*  158 */     for (int i = 0; i < 3; i++) {
/*      */       
/*  160 */       TextureAtlasSprite newMpIcon = getConnectedTextureSingle(blockAccess, blockState, blockPos, side, mpIcon, false, renderEnv);
/*      */       
/*  162 */       if (newMpIcon == mpIcon) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  167 */       mpIcon = newMpIcon;
/*      */     } 
/*      */     
/*  170 */     return mpIcon;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static TextureAtlasSprite getConnectedTextureSingle(IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, EnumFacing facing, TextureAtlasSprite icon, boolean checkBlocks, RenderEnv renderEnv) {
/*  176 */     Block block = blockState.getBlock();
/*      */     
/*  178 */     if (!(blockState instanceof BlockStateBase))
/*      */     {
/*  180 */       return icon;
/*      */     }
/*      */ 
/*      */     
/*  184 */     BlockStateBase blockStateBase = (BlockStateBase)blockState;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  192 */     if (tileProperties != null) {
/*      */       
/*  194 */       int blockId = icon.getIndexInMap();
/*      */       
/*  196 */       if (blockId >= 0 && blockId < tileProperties.length) {
/*      */         
/*  198 */         ConnectedProperties[] cps = tileProperties[blockId];
/*      */         
/*  200 */         if (cps != null) {
/*      */           
/*  202 */           int side = getSide(facing);
/*      */           
/*  204 */           for (int i = 0; i < cps.length; i++) {
/*      */             
/*  206 */             ConnectedProperties cp = cps[i];
/*      */             
/*  208 */             if (cp != null && cp.matchesBlockId(blockStateBase.getBlockId())) {
/*      */               
/*  210 */               TextureAtlasSprite newIcon = getConnectedTexture(cp, blockAccess, blockStateBase, blockPos, side, icon, renderEnv);
/*      */               
/*  212 */               if (newIcon != null)
/*      */               {
/*  214 */                 return newIcon;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  222 */     if (blockProperties != null && checkBlocks) {
/*      */       
/*  224 */       int blockId = renderEnv.getBlockId();
/*      */       
/*  226 */       if (blockId >= 0 && blockId < blockProperties.length) {
/*      */         
/*  228 */         ConnectedProperties[] cps = blockProperties[blockId];
/*      */         
/*  230 */         if (cps != null) {
/*      */           
/*  232 */           int side = getSide(facing);
/*      */           
/*  234 */           for (int i = 0; i < cps.length; i++) {
/*      */             
/*  236 */             ConnectedProperties cp = cps[i];
/*      */             
/*  238 */             if (cp != null && cp.matchesIcon(icon)) {
/*      */               
/*  240 */               TextureAtlasSprite newIcon = getConnectedTexture(cp, blockAccess, blockStateBase, blockPos, side, icon, renderEnv);
/*      */               
/*  242 */               if (newIcon != null)
/*      */               {
/*  244 */                 return newIcon;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  252 */     return icon;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getSide(EnumFacing facing) {
/*  258 */     if (facing == null)
/*      */     {
/*  260 */       return -1;
/*      */     }
/*      */ 
/*      */     
/*  264 */     switch (facing) {
/*      */       
/*      */       case DOWN:
/*  267 */         return 0;
/*      */       
/*      */       case UP:
/*  270 */         return 1;
/*      */       
/*      */       case EAST:
/*  273 */         return 5;
/*      */       
/*      */       case WEST:
/*  276 */         return 4;
/*      */       
/*      */       case NORTH:
/*  279 */         return 2;
/*      */       
/*      */       case SOUTH:
/*  282 */         return 3;
/*      */     } 
/*      */     
/*  285 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static EnumFacing getFacing(int side) {
/*  292 */     switch (side) {
/*      */       
/*      */       case 0:
/*  295 */         return EnumFacing.DOWN;
/*      */       
/*      */       case 1:
/*  298 */         return EnumFacing.UP;
/*      */       
/*      */       case 2:
/*  301 */         return EnumFacing.NORTH;
/*      */       
/*      */       case 3:
/*  304 */         return EnumFacing.SOUTH;
/*      */       
/*      */       case 4:
/*  307 */         return EnumFacing.WEST;
/*      */       
/*      */       case 5:
/*  310 */         return EnumFacing.EAST;
/*      */     } 
/*      */     
/*  313 */     return EnumFacing.UP;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTexture(ConnectedProperties cp, IBlockAccess blockAccess, BlockStateBase blockState, BlockPos blockPos, int side, TextureAtlasSprite icon, RenderEnv renderEnv) {
/*  319 */     int vertAxis = 0;
/*  320 */     int metadata = blockState.getMetadata();
/*  321 */     int metadataCheck = metadata;
/*  322 */     Block block = blockState.getBlock();
/*      */     
/*  324 */     if (block instanceof net.minecraft.block.BlockRotatedPillar) {
/*      */       
/*  326 */       vertAxis = getWoodAxis(side, metadata);
/*      */       
/*  328 */       if (cp.getMetadataMax() <= 3)
/*      */       {
/*  330 */         metadataCheck = metadata & 0x3;
/*      */       }
/*      */     } 
/*      */     
/*  334 */     if (block instanceof net.minecraft.block.BlockQuartz) {
/*      */       
/*  336 */       vertAxis = getQuartzAxis(side, metadata);
/*      */       
/*  338 */       if (cp.getMetadataMax() <= 2 && metadataCheck > 2)
/*      */       {
/*  340 */         metadataCheck = 2;
/*      */       }
/*      */     } 
/*      */     
/*  344 */     if (!cp.matchesBlock(blockState.getBlockId(), metadataCheck))
/*      */     {
/*  346 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  352 */     if (side >= 0 && cp.faces != 63) {
/*      */       
/*  354 */       int i = side;
/*      */       
/*  356 */       if (vertAxis != 0)
/*      */       {
/*  358 */         i = fixSideByAxis(side, vertAxis);
/*      */       }
/*      */       
/*  361 */       if ((1 << i & cp.faces) == 0)
/*      */       {
/*  363 */         return null;
/*      */       }
/*      */     } 
/*      */     
/*  367 */     int y = blockPos.getY();
/*      */     
/*  369 */     if (y >= cp.minHeight && y <= cp.maxHeight) {
/*      */       
/*  371 */       if (cp.biomes != null) {
/*      */         
/*  373 */         BiomeGenBase blockBiome = blockAccess.getBiomeGenForCoords(blockPos);
/*      */         
/*  375 */         if (!cp.matchesBiome(blockBiome))
/*      */         {
/*  377 */           return null;
/*      */         }
/*      */       } 
/*      */       
/*  381 */       switch (cp.method) {
/*      */         
/*      */         case 1:
/*  384 */           return getConnectedTextureCtm(cp, blockAccess, (IBlockState)blockState, blockPos, vertAxis, side, icon, metadata, renderEnv);
/*      */         
/*      */         case 2:
/*  387 */           return getConnectedTextureHorizontal(cp, blockAccess, (IBlockState)blockState, blockPos, vertAxis, side, icon, metadata);
/*      */         
/*      */         case 3:
/*  390 */           return getConnectedTextureTop(cp, blockAccess, (IBlockState)blockState, blockPos, vertAxis, side, icon, metadata);
/*      */         
/*      */         case 4:
/*  393 */           return getConnectedTextureRandom(cp, blockPos, side);
/*      */         
/*      */         case 5:
/*  396 */           return getConnectedTextureRepeat(cp, blockPos, side);
/*      */         
/*      */         case 6:
/*  399 */           return getConnectedTextureVertical(cp, blockAccess, (IBlockState)blockState, blockPos, vertAxis, side, icon, metadata);
/*      */         
/*      */         case 7:
/*  402 */           return getConnectedTextureFixed(cp);
/*      */         
/*      */         case 8:
/*  405 */           return getConnectedTextureHorizontalVertical(cp, blockAccess, (IBlockState)blockState, blockPos, vertAxis, side, icon, metadata);
/*      */         
/*      */         case 9:
/*  408 */           return getConnectedTextureVerticalHorizontal(cp, blockAccess, (IBlockState)blockState, blockPos, vertAxis, side, icon, metadata);
/*      */       } 
/*      */       
/*  411 */       return null;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  416 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int fixSideByAxis(int side, int vertAxis) {
/*  423 */     switch (vertAxis) {
/*      */       
/*      */       case 0:
/*  426 */         return side;
/*      */       
/*      */       case 1:
/*  429 */         switch (side) {
/*      */           
/*      */           case 0:
/*  432 */             return 2;
/*      */           
/*      */           case 1:
/*  435 */             return 3;
/*      */           
/*      */           case 2:
/*  438 */             return 1;
/*      */           
/*      */           case 3:
/*  441 */             return 0;
/*      */         } 
/*      */         
/*  444 */         return side;
/*      */ 
/*      */       
/*      */       case 2:
/*  448 */         switch (side) {
/*      */           
/*      */           case 0:
/*  451 */             return 4;
/*      */           
/*      */           case 1:
/*  454 */             return 5;
/*      */ 
/*      */ 
/*      */           
/*      */           default:
/*  459 */             return side;
/*      */           
/*      */           case 4:
/*  462 */             return 1;
/*      */           case 5:
/*      */             break;
/*  465 */         }  return 0;
/*      */     } 
/*      */ 
/*      */     
/*  469 */     return side;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getWoodAxis(int side, int metadata) {
/*  475 */     int orient = (metadata & 0xC) >> 2;
/*      */     
/*  477 */     switch (orient) {
/*      */       
/*      */       case 1:
/*  480 */         return 2;
/*      */       
/*      */       case 2:
/*  483 */         return 1;
/*      */     } 
/*      */     
/*  486 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getQuartzAxis(int side, int metadata) {
/*  492 */     switch (metadata) {
/*      */       
/*      */       case 3:
/*  495 */         return 2;
/*      */       
/*      */       case 4:
/*  498 */         return 1;
/*      */     } 
/*      */     
/*  501 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureRandom(ConnectedProperties cp, BlockPos blockPos, int side) {
/*  507 */     if (cp.tileIcons.length == 1)
/*      */     {
/*  509 */       return cp.tileIcons[0];
/*      */     }
/*      */ 
/*      */     
/*  513 */     int face = side / cp.symmetry * cp.symmetry;
/*  514 */     int rand = Config.getRandom(blockPos, face) & Integer.MAX_VALUE;
/*  515 */     int index = 0;
/*      */     
/*  517 */     if (cp.weights == null) {
/*      */       
/*  519 */       index = rand % cp.tileIcons.length;
/*      */     }
/*      */     else {
/*      */       
/*  523 */       int randWeight = rand % cp.sumAllWeights;
/*  524 */       int[] sumWeights = cp.sumWeights;
/*      */       
/*  526 */       for (int i = 0; i < sumWeights.length; i++) {
/*      */         
/*  528 */         if (randWeight < sumWeights[i]) {
/*      */           
/*  530 */           index = i;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*  536 */     return cp.tileIcons[index];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureFixed(ConnectedProperties cp) {
/*  542 */     return cp.tileIcons[0];
/*      */   }
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureRepeat(ConnectedProperties cp, BlockPos blockPos, int side) {
/*  547 */     if (cp.tileIcons.length == 1)
/*      */     {
/*  549 */       return cp.tileIcons[0];
/*      */     }
/*      */ 
/*      */     
/*  553 */     int x = blockPos.getX();
/*  554 */     int y = blockPos.getY();
/*  555 */     int z = blockPos.getZ();
/*  556 */     int nx = 0;
/*  557 */     int ny = 0;
/*      */     
/*  559 */     switch (side) {
/*      */       
/*      */       case 0:
/*  562 */         nx = x;
/*  563 */         ny = z;
/*      */         break;
/*      */       
/*      */       case 1:
/*  567 */         nx = x;
/*  568 */         ny = z;
/*      */         break;
/*      */       
/*      */       case 2:
/*  572 */         nx = -x - 1;
/*  573 */         ny = -y;
/*      */         break;
/*      */       
/*      */       case 3:
/*  577 */         nx = x;
/*  578 */         ny = -y;
/*      */         break;
/*      */       
/*      */       case 4:
/*  582 */         nx = z;
/*  583 */         ny = -y;
/*      */         break;
/*      */       
/*      */       case 5:
/*  587 */         nx = -z - 1;
/*  588 */         ny = -y; break;
/*  589 */     }  nx %= 
/*      */       
/*  591 */       cp.width;
/*  592 */     ny %= cp.height;
/*      */     
/*  594 */     if (nx < 0)
/*      */     {
/*  596 */       nx += cp.width;
/*      */     }
/*      */     
/*  599 */     if (ny < 0)
/*      */     {
/*  601 */       ny += cp.height;
/*      */     }
/*      */     
/*  604 */     int index = ny * cp.width + nx;
/*  605 */     return cp.tileIcons[index];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureCtm(ConnectedProperties cp, IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, int vertAxis, int side, TextureAtlasSprite icon, int metadata, RenderEnv renderEnv) {
/*  611 */     boolean[] borders = renderEnv.getBorderFlags();
/*      */     
/*  613 */     switch (side) {
/*      */       
/*      */       case 0:
/*  616 */         borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest(), side, icon, metadata);
/*  617 */         borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*  618 */         borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetNorth(), side, icon, metadata);
/*  619 */         borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 1:
/*  623 */         borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest(), side, icon, metadata);
/*  624 */         borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*  625 */         borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/*  626 */         borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetNorth(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 2:
/*  630 */         borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*  631 */         borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest(), side, icon, metadata);
/*  632 */         borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown(), side, icon, metadata);
/*  633 */         borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 3:
/*  637 */         borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest(), side, icon, metadata);
/*  638 */         borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*  639 */         borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown(), side, icon, metadata);
/*  640 */         borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 4:
/*  644 */         borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetNorth(), side, icon, metadata);
/*  645 */         borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/*  646 */         borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown(), side, icon, metadata);
/*  647 */         borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 5:
/*  651 */         borders[0] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/*  652 */         borders[1] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetNorth(), side, icon, metadata);
/*  653 */         borders[2] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown(), side, icon, metadata);
/*  654 */         borders[3] = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/*      */         break;
/*      */     } 
/*  657 */     byte index = 0;
/*      */     
/*  659 */     if ((borders[0] & (borders[1] ? 0 : 1) & (borders[2] ? 0 : 1) & (borders[3] ? 0 : 1)) != 0) {
/*      */       
/*  661 */       index = 3;
/*      */     }
/*  663 */     else if (((borders[0] ? 0 : 1) & borders[1] & (borders[2] ? 0 : 1) & (borders[3] ? 0 : 1)) != 0) {
/*      */       
/*  665 */       index = 1;
/*      */     }
/*  667 */     else if (((borders[0] ? 0 : 1) & (borders[1] ? 0 : 1) & borders[2] & (borders[3] ? 0 : 1)) != 0) {
/*      */       
/*  669 */       index = 12;
/*      */     }
/*  671 */     else if (((borders[0] ? 0 : 1) & (borders[1] ? 0 : 1) & (borders[2] ? 0 : 1) & borders[3]) != 0) {
/*      */       
/*  673 */       index = 36;
/*      */     }
/*  675 */     else if ((borders[0] & borders[1] & (borders[2] ? 0 : 1) & (borders[3] ? 0 : 1)) != 0) {
/*      */       
/*  677 */       index = 2;
/*      */     }
/*  679 */     else if (((borders[0] ? 0 : 1) & (borders[1] ? 0 : 1) & borders[2] & borders[3]) != 0) {
/*      */       
/*  681 */       index = 24;
/*      */     }
/*  683 */     else if ((borders[0] & (borders[1] ? 0 : 1) & borders[2] & (borders[3] ? 0 : 1)) != 0) {
/*      */       
/*  685 */       index = 15;
/*      */     }
/*  687 */     else if ((borders[0] & (borders[1] ? 0 : 1) & (borders[2] ? 0 : 1) & borders[3]) != 0) {
/*      */       
/*  689 */       index = 39;
/*      */     }
/*  691 */     else if (((borders[0] ? 0 : 1) & borders[1] & borders[2] & (borders[3] ? 0 : 1)) != 0) {
/*      */       
/*  693 */       index = 13;
/*      */     }
/*  695 */     else if (((borders[0] ? 0 : 1) & borders[1] & (borders[2] ? 0 : 1) & borders[3]) != 0) {
/*      */       
/*  697 */       index = 37;
/*      */     }
/*  699 */     else if (((borders[0] ? 0 : 1) & borders[1] & borders[2] & borders[3]) != 0) {
/*      */       
/*  701 */       index = 25;
/*      */     }
/*  703 */     else if ((borders[0] & (borders[1] ? 0 : 1) & borders[2] & borders[3]) != 0) {
/*      */       
/*  705 */       index = 27;
/*      */     }
/*  707 */     else if ((borders[0] & borders[1] & (borders[2] ? 0 : 1) & borders[3]) != 0) {
/*      */       
/*  709 */       index = 38;
/*      */     }
/*  711 */     else if ((borders[0] & borders[1] & borders[2] & (borders[3] ? 0 : 1)) != 0) {
/*      */       
/*  713 */       index = 14;
/*      */     }
/*  715 */     else if ((borders[0] & borders[1] & borders[2] & borders[3]) != 0) {
/*      */       
/*  717 */       index = 26;
/*      */     } 
/*      */     
/*  720 */     if (index == 0)
/*      */     {
/*  722 */       return cp.tileIcons[index];
/*      */     }
/*  724 */     if (!Config.isConnectedTexturesFancy())
/*      */     {
/*  726 */       return cp.tileIcons[index];
/*      */     }
/*      */ 
/*      */     
/*  730 */     switch (side) {
/*      */       
/*      */       case 0:
/*  733 */         borders[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast().offsetNorth(), side, icon, metadata);
/*  734 */         borders[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest().offsetNorth(), side, icon, metadata);
/*  735 */         borders[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast().offsetSouth(), side, icon, metadata);
/*  736 */         borders[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest().offsetSouth(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 1:
/*  740 */         borders[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast().offsetSouth(), side, icon, metadata);
/*  741 */         borders[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest().offsetSouth(), side, icon, metadata);
/*  742 */         borders[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast().offsetNorth(), side, icon, metadata);
/*  743 */         borders[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest().offsetNorth(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 2:
/*  747 */         borders[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest().offsetDown(), side, icon, metadata);
/*  748 */         borders[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast().offsetDown(), side, icon, metadata);
/*  749 */         borders[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest().offsetUp(), side, icon, metadata);
/*  750 */         borders[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast().offsetUp(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 3:
/*  754 */         borders[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast().offsetDown(), side, icon, metadata);
/*  755 */         borders[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest().offsetDown(), side, icon, metadata);
/*  756 */         borders[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast().offsetUp(), side, icon, metadata);
/*  757 */         borders[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest().offsetUp(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 4:
/*  761 */         borders[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown().offsetSouth(), side, icon, metadata);
/*  762 */         borders[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown().offsetNorth(), side, icon, metadata);
/*  763 */         borders[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp().offsetSouth(), side, icon, metadata);
/*  764 */         borders[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp().offsetNorth(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 5:
/*  768 */         borders[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown().offsetNorth(), side, icon, metadata);
/*  769 */         borders[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown().offsetSouth(), side, icon, metadata);
/*  770 */         borders[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp().offsetNorth(), side, icon, metadata);
/*  771 */         borders[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp().offsetSouth(), side, icon, metadata);
/*      */         break;
/*      */     } 
/*  774 */     if (index == 13 && borders[0]) {
/*      */       
/*  776 */       index = 4;
/*      */     }
/*  778 */     else if (index == 15 && borders[1]) {
/*      */       
/*  780 */       index = 5;
/*      */     }
/*  782 */     else if (index == 37 && borders[2]) {
/*      */       
/*  784 */       index = 16;
/*      */     }
/*  786 */     else if (index == 39 && borders[3]) {
/*      */       
/*  788 */       index = 17;
/*      */     }
/*  790 */     else if (index == 14 && borders[0] && borders[1]) {
/*      */       
/*  792 */       index = 7;
/*      */     }
/*  794 */     else if (index == 25 && borders[0] && borders[2]) {
/*      */       
/*  796 */       index = 6;
/*      */     }
/*  798 */     else if (index == 27 && borders[3] && borders[1]) {
/*      */       
/*  800 */       index = 19;
/*      */     }
/*  802 */     else if (index == 38 && borders[3] && borders[2]) {
/*      */       
/*  804 */       index = 18;
/*      */     }
/*  806 */     else if (index == 14 && !borders[0] && borders[1]) {
/*      */       
/*  808 */       index = 31;
/*      */     }
/*  810 */     else if (index == 25 && borders[0] && !borders[2]) {
/*      */       
/*  812 */       index = 30;
/*      */     }
/*  814 */     else if (index == 27 && !borders[3] && borders[1]) {
/*      */       
/*  816 */       index = 41;
/*      */     }
/*  818 */     else if (index == 38 && borders[3] && !borders[2]) {
/*      */       
/*  820 */       index = 40;
/*      */     }
/*  822 */     else if (index == 14 && borders[0] && !borders[1]) {
/*      */       
/*  824 */       index = 29;
/*      */     }
/*  826 */     else if (index == 25 && !borders[0] && borders[2]) {
/*      */       
/*  828 */       index = 28;
/*      */     }
/*  830 */     else if (index == 27 && borders[3] && !borders[1]) {
/*      */       
/*  832 */       index = 43;
/*      */     }
/*  834 */     else if (index == 38 && !borders[3] && borders[2]) {
/*      */       
/*  836 */       index = 42;
/*      */     }
/*  838 */     else if (index == 26 && borders[0] && borders[1] && borders[2] && borders[3]) {
/*      */       
/*  840 */       index = 46;
/*      */     }
/*  842 */     else if (index == 26 && !borders[0] && borders[1] && borders[2] && borders[3]) {
/*      */       
/*  844 */       index = 9;
/*      */     }
/*  846 */     else if (index == 26 && borders[0] && !borders[1] && borders[2] && borders[3]) {
/*      */       
/*  848 */       index = 21;
/*      */     }
/*  850 */     else if (index == 26 && borders[0] && borders[1] && !borders[2] && borders[3]) {
/*      */       
/*  852 */       index = 8;
/*      */     }
/*  854 */     else if (index == 26 && borders[0] && borders[1] && borders[2] && !borders[3]) {
/*      */       
/*  856 */       index = 20;
/*      */     }
/*  858 */     else if (index == 26 && borders[0] && borders[1] && !borders[2] && !borders[3]) {
/*      */       
/*  860 */       index = 11;
/*      */     }
/*  862 */     else if (index == 26 && !borders[0] && !borders[1] && borders[2] && borders[3]) {
/*      */       
/*  864 */       index = 22;
/*      */     }
/*  866 */     else if (index == 26 && !borders[0] && borders[1] && !borders[2] && borders[3]) {
/*      */       
/*  868 */       index = 23;
/*      */     }
/*  870 */     else if (index == 26 && borders[0] && !borders[1] && borders[2] && !borders[3]) {
/*      */       
/*  872 */       index = 10;
/*      */     }
/*  874 */     else if (index == 26 && borders[0] && !borders[1] && !borders[2] && borders[3]) {
/*      */       
/*  876 */       index = 34;
/*      */     }
/*  878 */     else if (index == 26 && !borders[0] && borders[1] && borders[2] && !borders[3]) {
/*      */       
/*  880 */       index = 35;
/*      */     }
/*  882 */     else if (index == 26 && borders[0] && !borders[1] && !borders[2] && !borders[3]) {
/*      */       
/*  884 */       index = 32;
/*      */     }
/*  886 */     else if (index == 26 && !borders[0] && borders[1] && !borders[2] && !borders[3]) {
/*      */       
/*  888 */       index = 33;
/*      */     }
/*  890 */     else if (index == 26 && !borders[0] && !borders[1] && borders[2] && !borders[3]) {
/*      */       
/*  892 */       index = 44;
/*      */     }
/*  894 */     else if (index == 26 && !borders[0] && !borders[1] && !borders[2] && borders[3]) {
/*      */       
/*  896 */       index = 45;
/*      */     } 
/*      */     
/*  899 */     return cp.tileIcons[index];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static boolean isNeighbour(ConnectedProperties cp, IBlockAccess iblockaccess, IBlockState blockState, BlockPos blockPos, int side, TextureAtlasSprite icon, int metadata) {
/*  905 */     IBlockState neighbourState = iblockaccess.getBlockState(blockPos);
/*      */     
/*  907 */     if (blockState == neighbourState)
/*      */     {
/*  909 */       return true;
/*      */     }
/*  911 */     if (cp.connect == 2) {
/*      */       
/*  913 */       if (neighbourState == null)
/*      */       {
/*  915 */         return false;
/*      */       }
/*  917 */       if (neighbourState == AIR_DEFAULT_STATE)
/*      */       {
/*  919 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  923 */       TextureAtlasSprite neighbourIcon = getNeighbourIcon(iblockaccess, blockPos, neighbourState, side);
/*  924 */       return (neighbourIcon == icon);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  929 */     return (cp.connect == 3) ? ((neighbourState == null) ? false : ((neighbourState == AIR_DEFAULT_STATE) ? false : ((neighbourState.getBlock().getMaterial() == blockState.getBlock().getMaterial())))) : false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getNeighbourIcon(IBlockAccess iblockaccess, BlockPos blockPos, IBlockState neighbourState, int side) {
/*  935 */     neighbourState = neighbourState.getBlock().getActualState(neighbourState, iblockaccess, blockPos);
/*  936 */     IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().func_175023_a().func_178125_b(neighbourState);
/*      */     
/*  938 */     if (model == null)
/*      */     {
/*  940 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  944 */     EnumFacing facing = getFacing(side);
/*  945 */     List<BakedQuad> quads = model.func_177551_a(facing);
/*      */     
/*  947 */     if (quads.size() > 0) {
/*      */       
/*  949 */       BakedQuad var10 = quads.get(0);
/*  950 */       return var10.getSprite();
/*      */     } 
/*      */ 
/*      */     
/*  954 */     List<BakedQuad> quadsGeneral = model.func_177550_a();
/*      */     
/*  956 */     for (int i = 0; i < quadsGeneral.size(); i++) {
/*      */       
/*  958 */       BakedQuad quad = quadsGeneral.get(i);
/*      */       
/*  960 */       if (quad.getFace() == facing)
/*      */       {
/*  962 */         return quad.getSprite();
/*      */       }
/*      */     } 
/*      */     
/*  966 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureHorizontal(ConnectedProperties cp, IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, int vertAxis, int side, TextureAtlasSprite icon, int metadata) {
/*      */     byte index1;
/*  975 */     boolean left = false;
/*  976 */     boolean right = false;
/*      */ 
/*      */     
/*  979 */     switch (vertAxis) {
/*      */       
/*      */       case 0:
/*  982 */         switch (side) {
/*      */           
/*      */           case 0:
/*      */           case 1:
/*  986 */             return null;
/*      */           
/*      */           case 2:
/*  989 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*  990 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 3:
/*  994 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest(), side, icon, metadata);
/*  995 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 4:
/*  999 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetNorth(), side, icon, metadata);
/* 1000 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 5:
/* 1004 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/* 1005 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetNorth(), side, icon, metadata);
/*      */             break;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       case 1:
/* 1012 */         switch (side) {
/*      */           
/*      */           case 0:
/* 1015 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest(), side, icon, metadata);
/* 1016 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 1:
/* 1020 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest(), side, icon, metadata);
/* 1021 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 2:
/*      */           case 3:
/* 1026 */             return null;
/*      */           
/*      */           case 4:
/* 1029 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown(), side, icon, metadata);
/* 1030 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 5:
/* 1034 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/* 1035 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown(), side, icon, metadata);
/*      */             break;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       case 2:
/* 1042 */         switch (side) {
/*      */           
/*      */           case 0:
/* 1045 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetNorth(), side, icon, metadata);
/* 1046 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 1:
/* 1050 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetNorth(), side, icon, metadata);
/* 1051 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 2:
/* 1055 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown(), side, icon, metadata);
/* 1056 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 3:
/* 1060 */             left = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/* 1061 */             right = isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown(), side, icon, metadata);
/*      */             break;
/*      */           
/*      */           case 4:
/*      */           case 5:
/* 1066 */             return null;
/*      */         } 
/*      */         break;
/*      */     } 
/* 1070 */     boolean index = true;
/*      */ 
/*      */     
/* 1073 */     if (left) {
/*      */       
/* 1075 */       if (right)
/*      */       {
/* 1077 */         index1 = 1;
/*      */       }
/*      */       else
/*      */       {
/* 1081 */         index1 = 2;
/*      */       }
/*      */     
/* 1084 */     } else if (right) {
/*      */       
/* 1086 */       index1 = 0;
/*      */     }
/*      */     else {
/*      */       
/* 1090 */       index1 = 3;
/*      */     } 
/*      */     
/* 1093 */     return cp.tileIcons[index1];
/*      */   }
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureVertical(ConnectedProperties cp, IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, int vertAxis, int side, TextureAtlasSprite icon, int metadata) {
/*      */     byte index1;
/* 1098 */     boolean bottom = false;
/* 1099 */     boolean top = false;
/*      */     
/* 1101 */     switch (vertAxis) {
/*      */       
/*      */       case 0:
/* 1104 */         if (side == 1 || side == 0)
/*      */         {
/* 1106 */           return null;
/*      */         }
/*      */         
/* 1109 */         bottom = isNeighbour(cp, blockAccess, blockState, blockPos.offsetDown(), side, icon, metadata);
/* 1110 */         top = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 1:
/* 1114 */         if (side == 3 || side == 2)
/*      */         {
/* 1116 */           return null;
/*      */         }
/*      */         
/* 1119 */         bottom = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/* 1120 */         top = isNeighbour(cp, blockAccess, blockState, blockPos.offsetNorth(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 2:
/* 1124 */         if (side == 5 || side == 4)
/*      */         {
/* 1126 */           return null;
/*      */         }
/*      */         
/* 1129 */         bottom = isNeighbour(cp, blockAccess, blockState, blockPos.offsetWest(), side, icon, metadata);
/* 1130 */         top = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*      */         break;
/*      */     } 
/* 1133 */     boolean index = true;
/*      */ 
/*      */     
/* 1136 */     if (bottom) {
/*      */       
/* 1138 */       if (top)
/*      */       {
/* 1140 */         index1 = 1;
/*      */       }
/*      */       else
/*      */       {
/* 1144 */         index1 = 2;
/*      */       }
/*      */     
/* 1147 */     } else if (top) {
/*      */       
/* 1149 */       index1 = 0;
/*      */     }
/*      */     else {
/*      */       
/* 1153 */       index1 = 3;
/*      */     } 
/*      */     
/* 1156 */     return cp.tileIcons[index1];
/*      */   }
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureHorizontalVertical(ConnectedProperties cp, IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, int vertAxis, int side, TextureAtlasSprite icon, int metadata) {
/* 1161 */     TextureAtlasSprite[] tileIcons = cp.tileIcons;
/* 1162 */     TextureAtlasSprite iconH = getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
/*      */     
/* 1164 */     if (iconH != null && iconH != icon && iconH != tileIcons[3])
/*      */     {
/* 1166 */       return iconH;
/*      */     }
/*      */ 
/*      */     
/* 1170 */     TextureAtlasSprite iconV = getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
/* 1171 */     return (iconV == tileIcons[0]) ? tileIcons[4] : ((iconV == tileIcons[1]) ? tileIcons[5] : ((iconV == tileIcons[2]) ? tileIcons[6] : iconV));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureVerticalHorizontal(ConnectedProperties cp, IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, int vertAxis, int side, TextureAtlasSprite icon, int metadata) {
/* 1177 */     TextureAtlasSprite[] tileIcons = cp.tileIcons;
/* 1178 */     TextureAtlasSprite iconV = getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
/*      */     
/* 1180 */     if (iconV != null && iconV != icon && iconV != tileIcons[3])
/*      */     {
/* 1182 */       return iconV;
/*      */     }
/*      */ 
/*      */     
/* 1186 */     TextureAtlasSprite iconH = getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
/* 1187 */     return (iconH == tileIcons[0]) ? tileIcons[4] : ((iconH == tileIcons[1]) ? tileIcons[5] : ((iconH == tileIcons[2]) ? tileIcons[6] : iconH));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static TextureAtlasSprite getConnectedTextureTop(ConnectedProperties cp, IBlockAccess blockAccess, IBlockState blockState, BlockPos blockPos, int vertAxis, int side, TextureAtlasSprite icon, int metadata) {
/* 1193 */     boolean top = false;
/*      */     
/* 1195 */     switch (vertAxis) {
/*      */       
/*      */       case 0:
/* 1198 */         if (side == 1 || side == 0)
/*      */         {
/* 1200 */           return null;
/*      */         }
/*      */         
/* 1203 */         top = isNeighbour(cp, blockAccess, blockState, blockPos.offsetUp(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 1:
/* 1207 */         if (side == 3 || side == 2)
/*      */         {
/* 1209 */           return null;
/*      */         }
/*      */         
/* 1212 */         top = isNeighbour(cp, blockAccess, blockState, blockPos.offsetSouth(), side, icon, metadata);
/*      */         break;
/*      */       
/*      */       case 2:
/* 1216 */         if (side == 5 || side == 4)
/*      */         {
/* 1218 */           return null;
/*      */         }
/*      */         
/* 1221 */         top = isNeighbour(cp, blockAccess, blockState, blockPos.offsetEast(), side, icon, metadata);
/*      */         break;
/*      */     } 
/* 1224 */     return top ? cp.tileIcons[0] : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void updateIcons(TextureMap textureMap) {
/* 1229 */     blockProperties = null;
/* 1230 */     tileProperties = null;
/* 1231 */     spriteQuadMaps = null;
/*      */     
/* 1233 */     if (Config.isConnectedTextures()) {
/*      */       
/* 1235 */       IResourcePack[] rps = Config.getResourcePacks();
/*      */       
/* 1237 */       for (int locEmpty = rps.length - 1; locEmpty >= 0; locEmpty--) {
/*      */         
/* 1239 */         IResourcePack rp = rps[locEmpty];
/* 1240 */         updateIcons(textureMap, rp);
/*      */       } 
/*      */       
/* 1243 */       updateIcons(textureMap, (IResourcePack)Config.getDefaultResourcePack());
/* 1244 */       ResourceLocation var4 = new ResourceLocation("mcpatcher/ctm/default/empty");
/* 1245 */       emptySprite = textureMap.func_174942_a(var4);
/* 1246 */       spriteQuadMaps = new Map[textureMap.getCountRegisteredSprites() + 1];
/*      */       
/* 1248 */       if (blockProperties.length <= 0)
/*      */       {
/* 1250 */         blockProperties = null;
/*      */       }
/*      */       
/* 1253 */       if (tileProperties.length <= 0)
/*      */       {
/* 1255 */         tileProperties = null;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void updateIconEmpty(TextureMap textureMap) {}
/*      */   
/*      */   public static void updateIcons(TextureMap textureMap, IResourcePack rp) {
/* 1264 */     String[] names = ResUtils.collectFiles(rp, "mcpatcher/ctm/", ".properties", getDefaultCtmPaths());
/* 1265 */     Arrays.sort((Object[])names);
/* 1266 */     List tileList = makePropertyList(tileProperties);
/* 1267 */     List blockList = makePropertyList(blockProperties);
/*      */     
/* 1269 */     for (int i = 0; i < names.length; i++) {
/*      */       
/* 1271 */       String name = names[i];
/* 1272 */       Config.dbg("ConnectedTextures: " + name);
/*      */ 
/*      */       
/*      */       try {
/* 1276 */         ResourceLocation e = new ResourceLocation(name);
/* 1277 */         InputStream in = rp.getInputStream(e);
/*      */         
/* 1279 */         if (in == null) {
/*      */           
/* 1281 */           Config.warn("ConnectedTextures file not found: " + name);
/*      */         }
/*      */         else {
/*      */           
/* 1285 */           Properties props = new Properties();
/* 1286 */           props.load(in);
/* 1287 */           ConnectedProperties cp = new ConnectedProperties(props, name);
/*      */           
/* 1289 */           if (cp.isValid(name))
/*      */           {
/* 1291 */             cp.updateIcons(textureMap);
/* 1292 */             addToTileList(cp, tileList);
/* 1293 */             addToBlockList(cp, blockList);
/*      */           }
/*      */         
/*      */         } 
/* 1297 */       } catch (FileNotFoundException var11) {
/*      */         
/* 1299 */         Config.warn("ConnectedTextures file not found: " + name);
/*      */       }
/* 1301 */       catch (Exception var12) {
/*      */         
/* 1303 */         var12.printStackTrace();
/*      */       } 
/*      */     } 
/*      */     
/* 1307 */     blockProperties = propertyListToArray(blockList);
/* 1308 */     tileProperties = propertyListToArray(tileList);
/* 1309 */     multipass = detectMultipass();
/* 1310 */     Config.dbg("Multipass connected textures: " + multipass);
/*      */   }
/*      */ 
/*      */   
/*      */   private static List makePropertyList(ConnectedProperties[][] propsArr) {
/* 1315 */     ArrayList<ArrayList> list = new ArrayList();
/*      */     
/* 1317 */     if (propsArr != null)
/*      */     {
/* 1319 */       for (int i = 0; i < propsArr.length; i++) {
/*      */         
/* 1321 */         ConnectedProperties[] props = propsArr[i];
/* 1322 */         ArrayList propList = null;
/*      */         
/* 1324 */         if (props != null)
/*      */         {
/* 1326 */           propList = new ArrayList(Arrays.asList((Object[])props));
/*      */         }
/*      */         
/* 1329 */         list.add(propList);
/*      */       } 
/*      */     }
/*      */     
/* 1333 */     return list;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean detectMultipass() {
/* 1338 */     ArrayList propList = new ArrayList();
/*      */     
/*      */     int props;
/*      */     
/* 1342 */     for (props = 0; props < tileProperties.length; props++) {
/*      */       
/* 1344 */       ConnectedProperties[] matchIconSet = tileProperties[props];
/*      */       
/* 1346 */       if (matchIconSet != null)
/*      */       {
/* 1348 */         propList.addAll(Arrays.asList(matchIconSet));
/*      */       }
/*      */     } 
/*      */     
/* 1352 */     for (props = 0; props < blockProperties.length; props++) {
/*      */       
/* 1354 */       ConnectedProperties[] matchIconSet = blockProperties[props];
/*      */       
/* 1356 */       if (matchIconSet != null)
/*      */       {
/* 1358 */         propList.addAll(Arrays.asList(matchIconSet));
/*      */       }
/*      */     } 
/*      */     
/* 1362 */     ConnectedProperties[] var6 = (ConnectedProperties[])propList.toArray((Object[])new ConnectedProperties[propList.size()]);
/* 1363 */     HashSet var7 = new HashSet();
/* 1364 */     HashSet<?> tileIconSet = new HashSet();
/*      */     
/* 1366 */     for (int i = 0; i < var6.length; i++) {
/*      */       
/* 1368 */       ConnectedProperties cp = var6[i];
/*      */       
/* 1370 */       if (cp.matchTileIcons != null)
/*      */       {
/* 1372 */         var7.addAll(Arrays.asList(cp.matchTileIcons));
/*      */       }
/*      */       
/* 1375 */       if (cp.tileIcons != null)
/*      */       {
/* 1377 */         tileIconSet.addAll(Arrays.asList(cp.tileIcons));
/*      */       }
/*      */     } 
/*      */     
/* 1381 */     var7.retainAll(tileIconSet);
/* 1382 */     return !var7.isEmpty();
/*      */   }
/*      */ 
/*      */   
/*      */   private static ConnectedProperties[][] propertyListToArray(List<List> list) {
/* 1387 */     ConnectedProperties[][] propArr = new ConnectedProperties[list.size()][];
/*      */     
/* 1389 */     for (int i = 0; i < list.size(); i++) {
/*      */       
/* 1391 */       List subList = list.get(i);
/*      */       
/* 1393 */       if (subList != null) {
/*      */         
/* 1395 */         ConnectedProperties[] subArr = (ConnectedProperties[])subList.toArray((Object[])new ConnectedProperties[subList.size()]);
/* 1396 */         propArr[i] = subArr;
/*      */       } 
/*      */     } 
/*      */     
/* 1400 */     return propArr;
/*      */   }
/*      */ 
/*      */   
/*      */   private static void addToTileList(ConnectedProperties cp, List tileList) {
/* 1405 */     if (cp.matchTileIcons != null)
/*      */     {
/* 1407 */       for (int i = 0; i < cp.matchTileIcons.length; i++) {
/*      */         
/* 1409 */         TextureAtlasSprite icon = cp.matchTileIcons[i];
/*      */         
/* 1411 */         if (!(icon instanceof TextureAtlasSprite)) {
/*      */           
/* 1413 */           Config.warn("TextureAtlasSprite is not TextureAtlasSprite: " + icon + ", name: " + icon.getIconName());
/*      */         }
/*      */         else {
/*      */           
/* 1417 */           int tileId = icon.getIndexInMap();
/*      */           
/* 1419 */           if (tileId < 0) {
/*      */             
/* 1421 */             Config.warn("Invalid tile ID: " + tileId + ", icon: " + icon.getIconName());
/*      */           }
/*      */           else {
/*      */             
/* 1425 */             addToList(cp, tileList, tileId);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static void addToBlockList(ConnectedProperties cp, List blockList) {
/* 1434 */     if (cp.matchBlocks != null)
/*      */     {
/* 1436 */       for (int i = 0; i < cp.matchBlocks.length; i++) {
/*      */         
/* 1438 */         int blockId = cp.matchBlocks[i].getBlockId();
/*      */         
/* 1440 */         if (blockId < 0) {
/*      */           
/* 1442 */           Config.warn("Invalid block ID: " + blockId);
/*      */         }
/*      */         else {
/*      */           
/* 1446 */           addToList(cp, blockList, blockId);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private static void addToList(ConnectedProperties cp, List<List> list, int id) {
/* 1454 */     while (id >= list.size())
/*      */     {
/* 1456 */       list.add(null);
/*      */     }
/*      */     
/* 1459 */     Object subList = list.get(id);
/*      */     
/* 1461 */     if (subList == null) {
/*      */       
/* 1463 */       subList = new ArrayList();
/* 1464 */       list.set(id, subList);
/*      */     } 
/*      */     
/* 1467 */     ((List<ConnectedProperties>)subList).add(cp);
/*      */   }
/*      */ 
/*      */   
/*      */   private static String[] getDefaultCtmPaths() {
/* 1472 */     ArrayList<String> list = new ArrayList();
/* 1473 */     String defPath = "mcpatcher/ctm/default/";
/*      */     
/* 1475 */     if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/glass.png"))) {
/*      */       
/* 1477 */       list.add(String.valueOf(defPath) + "glass.properties");
/* 1478 */       list.add(String.valueOf(defPath) + "glasspane.properties");
/*      */     } 
/*      */     
/* 1481 */     if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/bookshelf.png")))
/*      */     {
/* 1483 */       list.add(String.valueOf(defPath) + "bookshelf.properties");
/*      */     }
/*      */     
/* 1486 */     if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/sandstone_normal.png")))
/*      */     {
/* 1488 */       list.add(String.valueOf(defPath) + "sandstone.properties");
/*      */     }
/*      */     
/* 1491 */     String[] colors = { "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black" };
/*      */     
/* 1493 */     for (int paths = 0; paths < colors.length; paths++) {
/*      */       
/* 1495 */       String color = colors[paths];
/*      */       
/* 1497 */       if (Config.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/glass_" + color + ".png"))) {
/*      */         
/* 1499 */         list.add(String.valueOf(defPath) + paths + "_glass_" + color + "/glass_" + color + ".properties");
/* 1500 */         list.add(String.valueOf(defPath) + paths + "_glass_" + color + "/glass_pane_" + color + ".properties");
/*      */       } 
/*      */     } 
/*      */     
/* 1504 */     String[] var5 = list.<String>toArray(new String[list.size()]);
/* 1505 */     return var5;
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getPaneTextureIndex(boolean linkP, boolean linkN, boolean linkYp, boolean linkYn) {
/* 1510 */     return (linkN && linkP) ? (linkYp ? (linkYn ? 34 : 50) : (linkYn ? 18 : 2)) : ((linkN && !linkP) ? (linkYp ? (linkYn ? 35 : 51) : (linkYn ? 19 : 3)) : ((!linkN && linkP) ? (linkYp ? (linkYn ? 33 : 49) : (linkYn ? 17 : 1)) : (linkYp ? (linkYn ? 32 : 48) : (linkYn ? 16 : 0))));
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getReversePaneTextureIndex(int texNum) {
/* 1515 */     int col = texNum % 16;
/* 1516 */     return (col == 1) ? (texNum + 2) : ((col == 3) ? (texNum - 2) : texNum);
/*      */   }
/*      */ 
/*      */   
/*      */   public static TextureAtlasSprite getCtmTexture(ConnectedProperties cp, int ctmIndex, TextureAtlasSprite icon) {
/* 1521 */     if (cp.method != 1)
/*      */     {
/* 1523 */       return icon;
/*      */     }
/* 1525 */     if (ctmIndex >= 0 && ctmIndex < ctmIndexes.length) {
/*      */       
/* 1527 */       int index = ctmIndexes[ctmIndex];
/* 1528 */       TextureAtlasSprite[] ctmIcons = cp.tileIcons;
/* 1529 */       return (index >= 0 && index < ctmIcons.length) ? ctmIcons[index] : icon;
/*      */     } 
/*      */ 
/*      */     
/* 1533 */     return icon;
/*      */   }
/*      */ 
/*      */   
/*      */   static class NamelessClass379831726
/*      */   {
/* 1539 */     static final int[] $SwitchMap$net$minecraft$util$EnumFacing = new int[(EnumFacing.values()).length];
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 1545 */         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.DOWN.ordinal()] = 1;
/*      */       }
/* 1547 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1554 */         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.UP.ordinal()] = 2;
/*      */       }
/* 1556 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1563 */         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.EAST.ordinal()] = 3;
/*      */       }
/* 1565 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1572 */         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.WEST.ordinal()] = 4;
/*      */       }
/* 1574 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1581 */         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.NORTH.ordinal()] = 5;
/*      */       }
/* 1583 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1590 */         $SwitchMap$net$minecraft$util$EnumFacing[EnumFacing.SOUTH.ordinal()] = 6;
/*      */       }
/* 1592 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ConnectedTextures.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */