/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockBed;
/*     */ import net.minecraft.block.BlockCactus;
/*     */ import net.minecraft.block.BlockColored;
/*     */ import net.minecraft.block.BlockCommandBlock;
/*     */ import net.minecraft.block.BlockDirt;
/*     */ import net.minecraft.block.BlockDispenser;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.block.BlockDoublePlant;
/*     */ import net.minecraft.block.BlockDropper;
/*     */ import net.minecraft.block.BlockFenceGate;
/*     */ import net.minecraft.block.BlockFire;
/*     */ import net.minecraft.block.BlockFlowerPot;
/*     */ import net.minecraft.block.BlockHopper;
/*     */ import net.minecraft.block.BlockJukebox;
/*     */ import net.minecraft.block.BlockLeaves;
/*     */ import net.minecraft.block.BlockNewLeaf;
/*     */ import net.minecraft.block.BlockNewLog;
/*     */ import net.minecraft.block.BlockOldLeaf;
/*     */ import net.minecraft.block.BlockOldLog;
/*     */ import net.minecraft.block.BlockPlanks;
/*     */ import net.minecraft.block.BlockPrismarine;
/*     */ import net.minecraft.block.BlockQuartz;
/*     */ import net.minecraft.block.BlockRedSandstone;
/*     */ import net.minecraft.block.BlockRedstoneWire;
/*     */ import net.minecraft.block.BlockReed;
/*     */ import net.minecraft.block.BlockSand;
/*     */ import net.minecraft.block.BlockSandStone;
/*     */ import net.minecraft.block.BlockSapling;
/*     */ import net.minecraft.block.BlockSilverfish;
/*     */ import net.minecraft.block.BlockStem;
/*     */ import net.minecraft.block.BlockStone;
/*     */ import net.minecraft.block.BlockStoneBrick;
/*     */ import net.minecraft.block.BlockStoneSlab;
/*     */ import net.minecraft.block.BlockStoneSlabNew;
/*     */ import net.minecraft.block.BlockTNT;
/*     */ import net.minecraft.block.BlockTallGrass;
/*     */ import net.minecraft.block.BlockTripWire;
/*     */ import net.minecraft.block.BlockWall;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.renderer.block.statemap.BlockStateMapper;
/*     */ import net.minecraft.client.renderer.block.statemap.IStateMapper;
/*     */ import net.minecraft.client.renderer.block.statemap.StateMap;
/*     */ import net.minecraft.client.renderer.block.statemap.StateMapperBase;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.ModelManager;
/*     */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class BlockModelShapes
/*     */ {
/*  63 */   private final Map field_178129_a = Maps.newIdentityHashMap();
/*  64 */   private final BlockStateMapper blockStateMapper = new BlockStateMapper();
/*     */   
/*     */   private final ModelManager modelManager;
/*     */   private static final String __OBFID = "CL_00002529";
/*     */   
/*     */   public BlockModelShapes(ModelManager p_i46245_1_) {
/*  70 */     this.modelManager = p_i46245_1_;
/*  71 */     func_178119_d();
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockStateMapper getBlockStateMapper() {
/*  76 */     return this.blockStateMapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public TextureAtlasSprite func_178122_a(IBlockState p_178122_1_) {
/*  81 */     Block var2 = p_178122_1_.getBlock();
/*  82 */     IBakedModel var3 = func_178125_b(p_178122_1_);
/*     */     
/*  84 */     if (var3 == null || var3 == this.modelManager.getMissingModel()) {
/*     */       
/*  86 */       if (var2 == Blocks.wall_sign || var2 == Blocks.standing_sign || var2 == Blocks.chest || var2 == Blocks.trapped_chest || var2 == Blocks.standing_banner || var2 == Blocks.wall_banner)
/*     */       {
/*  88 */         return this.modelManager.func_174952_b().getAtlasSprite("minecraft:blocks/planks_oak");
/*     */       }
/*     */       
/*  91 */       if (var2 == Blocks.ender_chest)
/*     */       {
/*  93 */         return this.modelManager.func_174952_b().getAtlasSprite("minecraft:blocks/obsidian");
/*     */       }
/*     */       
/*  96 */       if (var2 == Blocks.flowing_lava || var2 == Blocks.lava)
/*     */       {
/*  98 */         return this.modelManager.func_174952_b().getAtlasSprite("minecraft:blocks/lava_still");
/*     */       }
/*     */       
/* 101 */       if (var2 == Blocks.flowing_water || var2 == Blocks.water)
/*     */       {
/* 103 */         return this.modelManager.func_174952_b().getAtlasSprite("minecraft:blocks/water_still");
/*     */       }
/*     */       
/* 106 */       if (var2 == Blocks.skull)
/*     */       {
/* 108 */         return this.modelManager.func_174952_b().getAtlasSprite("minecraft:blocks/soul_sand");
/*     */       }
/*     */       
/* 111 */       if (var2 == Blocks.barrier)
/*     */       {
/* 113 */         return this.modelManager.func_174952_b().getAtlasSprite("minecraft:items/barrier");
/*     */       }
/*     */     } 
/*     */     
/* 117 */     if (var3 == null)
/*     */     {
/* 119 */       var3 = this.modelManager.getMissingModel();
/*     */     }
/*     */     
/* 122 */     return var3.getTexture();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBakedModel func_178125_b(IBlockState p_178125_1_) {
/* 127 */     IBakedModel var2 = (IBakedModel)this.field_178129_a.get(p_178125_1_);
/*     */     
/* 129 */     if (var2 == null)
/*     */     {
/* 131 */       var2 = this.modelManager.getMissingModel();
/*     */     }
/*     */     
/* 134 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelManager func_178126_b() {
/* 139 */     return this.modelManager;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178124_c() {
/* 144 */     this.field_178129_a.clear();
/* 145 */     Iterator<Map.Entry> var1 = this.blockStateMapper.func_178446_a().entrySet().iterator();
/*     */     
/* 147 */     while (var1.hasNext()) {
/*     */       
/* 149 */       Map.Entry var2 = var1.next();
/* 150 */       this.field_178129_a.put(var2.getKey(), this.modelManager.getModel((ModelResourceLocation)var2.getValue()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178121_a(Block p_178121_1_, IStateMapper p_178121_2_) {
/* 156 */     this.blockStateMapper.func_178447_a(p_178121_1_, p_178121_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178123_a(Block... p_178123_1_) {
/* 161 */     this.blockStateMapper.registerBuiltInBlocks(p_178123_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178119_d() {
/* 166 */     func_178123_a(new Block[] { Blocks.air, (Block)Blocks.flowing_water, (Block)Blocks.water, (Block)Blocks.flowing_lava, (Block)Blocks.lava, (Block)Blocks.piston_extension, (Block)Blocks.chest, Blocks.ender_chest, Blocks.trapped_chest, Blocks.standing_sign, (Block)Blocks.skull, Blocks.end_portal, Blocks.barrier, Blocks.wall_sign, Blocks.wall_banner, Blocks.standing_banner });
/* 167 */     func_178121_a(Blocks.stone, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockStone.VARIANT_PROP).build());
/* 168 */     func_178121_a(Blocks.prismarine, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockPrismarine.VARIANTS).build());
/* 169 */     func_178121_a((Block)Blocks.leaves, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockOldLeaf.VARIANT_PROP).func_178439_a("_leaves").func_178442_a(new IProperty[] { (IProperty)BlockLeaves.field_176236_b, (IProperty)BlockLeaves.field_176237_a }).build());
/* 170 */     func_178121_a((Block)Blocks.leaves2, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockNewLeaf.field_176240_P).func_178439_a("_leaves").func_178442_a(new IProperty[] { (IProperty)BlockLeaves.field_176236_b, (IProperty)BlockLeaves.field_176237_a }).build());
/* 171 */     func_178121_a((Block)Blocks.cactus, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockCactus.AGE_PROP }).build());
/* 172 */     func_178121_a((Block)Blocks.reeds, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockReed.field_176355_a }).build());
/* 173 */     func_178121_a(Blocks.jukebox, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockJukebox.HAS_RECORD }).build());
/* 174 */     func_178121_a(Blocks.command_block, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockCommandBlock.TRIGGERED_PROP }).build());
/* 175 */     func_178121_a(Blocks.cobblestone_wall, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockWall.field_176255_P).func_178439_a("_wall").build());
/* 176 */     func_178121_a((Block)Blocks.double_plant, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockDoublePlant.VARIANT_PROP).build());
/* 177 */     func_178121_a(Blocks.oak_fence_gate, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockFenceGate.field_176465_b }).build());
/* 178 */     func_178121_a(Blocks.spruce_fence_gate, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockFenceGate.field_176465_b }).build());
/* 179 */     func_178121_a(Blocks.birch_fence_gate, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockFenceGate.field_176465_b }).build());
/* 180 */     func_178121_a(Blocks.jungle_fence_gate, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockFenceGate.field_176465_b }).build());
/* 181 */     func_178121_a(Blocks.dark_oak_fence_gate, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockFenceGate.field_176465_b }).build());
/* 182 */     func_178121_a(Blocks.acacia_fence_gate, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockFenceGate.field_176465_b }).build());
/* 183 */     func_178121_a(Blocks.tripwire, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockTripWire.field_176295_N, (IProperty)BlockTripWire.field_176293_a }).build());
/* 184 */     func_178121_a((Block)Blocks.double_wooden_slab, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockPlanks.VARIANT_PROP).func_178439_a("_double_slab").build());
/* 185 */     func_178121_a((Block)Blocks.wooden_slab, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockPlanks.VARIANT_PROP).func_178439_a("_slab").build());
/* 186 */     func_178121_a(Blocks.tnt, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockTNT.field_176246_a }).build());
/* 187 */     func_178121_a((Block)Blocks.fire, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockFire.field_176543_a }).build());
/* 188 */     func_178121_a((Block)Blocks.redstone_wire, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockRedstoneWire.POWER }).build());
/* 189 */     func_178121_a(Blocks.oak_door, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockDoor.POWERED_PROP }).build());
/* 190 */     func_178121_a(Blocks.spruce_door, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockDoor.POWERED_PROP }).build());
/* 191 */     func_178121_a(Blocks.birch_door, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockDoor.POWERED_PROP }).build());
/* 192 */     func_178121_a(Blocks.jungle_door, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockDoor.POWERED_PROP }).build());
/* 193 */     func_178121_a(Blocks.acacia_door, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockDoor.POWERED_PROP }).build());
/* 194 */     func_178121_a(Blocks.dark_oak_door, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockDoor.POWERED_PROP }).build());
/* 195 */     func_178121_a(Blocks.iron_door, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockDoor.POWERED_PROP }).build());
/* 196 */     func_178121_a(Blocks.wool, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockColored.COLOR).func_178439_a("_wool").build());
/* 197 */     func_178121_a(Blocks.carpet, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockColored.COLOR).func_178439_a("_carpet").build());
/* 198 */     func_178121_a(Blocks.stained_hardened_clay, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockColored.COLOR).func_178439_a("_stained_hardened_clay").build());
/* 199 */     func_178121_a((Block)Blocks.stained_glass_pane, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockColored.COLOR).func_178439_a("_stained_glass_pane").build());
/* 200 */     func_178121_a((Block)Blocks.stained_glass, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockColored.COLOR).func_178439_a("_stained_glass").build());
/* 201 */     func_178121_a(Blocks.sandstone, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockSandStone.field_176297_a).build());
/* 202 */     func_178121_a(Blocks.red_sandstone, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockRedSandstone.TYPE).build());
/* 203 */     func_178121_a((Block)Blocks.tallgrass, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockTallGrass.field_176497_a).build());
/* 204 */     func_178121_a(Blocks.bed, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockBed.OCCUPIED_PROP }).build());
/* 205 */     func_178121_a((Block)Blocks.yellow_flower, (IStateMapper)(new StateMap.Builder()).func_178440_a(Blocks.yellow_flower.func_176494_l()).build());
/* 206 */     func_178121_a((Block)Blocks.red_flower, (IStateMapper)(new StateMap.Builder()).func_178440_a(Blocks.red_flower.func_176494_l()).build());
/* 207 */     func_178121_a((Block)Blocks.stone_slab, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockStoneSlab.field_176556_M).func_178439_a("_slab").build());
/* 208 */     func_178121_a((Block)Blocks.stone_slab2, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockStoneSlabNew.field_176559_M).func_178439_a("_slab").build());
/* 209 */     func_178121_a(Blocks.monster_egg, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockSilverfish.VARIANT_PROP).func_178439_a("_monster_egg").build());
/* 210 */     func_178121_a(Blocks.stonebrick, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockStoneBrick.VARIANT_PROP).build());
/* 211 */     func_178121_a(Blocks.dispenser, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockDispenser.TRIGGERED }).build());
/* 212 */     func_178121_a(Blocks.dropper, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockDropper.TRIGGERED }).build());
/* 213 */     func_178121_a(Blocks.log, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockOldLog.VARIANT_PROP).func_178439_a("_log").build());
/* 214 */     func_178121_a(Blocks.log2, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockNewLog.field_176300_b).func_178439_a("_log").build());
/* 215 */     func_178121_a(Blocks.planks, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockPlanks.VARIANT_PROP).func_178439_a("_planks").build());
/* 216 */     func_178121_a(Blocks.sapling, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockSapling.TYPE_PROP).func_178439_a("_sapling").build());
/* 217 */     func_178121_a((Block)Blocks.sand, (IStateMapper)(new StateMap.Builder()).func_178440_a((IProperty)BlockSand.VARIANT_PROP).build());
/* 218 */     func_178121_a((Block)Blocks.hopper, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockHopper.field_176429_b }).build());
/* 219 */     func_178121_a(Blocks.flower_pot, (IStateMapper)(new StateMap.Builder()).func_178442_a(new IProperty[] { (IProperty)BlockFlowerPot.field_176444_a }).build());
/* 220 */     func_178121_a(Blocks.quartz_block, (IStateMapper)new StateMapperBase()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002528";
/*     */           
/*     */           protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/* 225 */             BlockQuartz.EnumType var2 = (BlockQuartz.EnumType)p_178132_1_.getValue((IProperty)BlockQuartz.VARIANT_PROP);
/*     */             
/* 227 */             switch (BlockModelShapes.SwitchEnumType.field_178257_a[var2.ordinal()]) {
/*     */ 
/*     */               
/*     */               default:
/* 231 */                 return new ModelResourceLocation("quartz_block", "normal");
/*     */               
/*     */               case 2:
/* 234 */                 return new ModelResourceLocation("chiseled_quartz_block", "normal");
/*     */               
/*     */               case 3:
/* 237 */                 return new ModelResourceLocation("quartz_column", "axis=y");
/*     */               
/*     */               case 4:
/* 240 */                 return new ModelResourceLocation("quartz_column", "axis=x");
/*     */               case 5:
/*     */                 break;
/* 243 */             }  return new ModelResourceLocation("quartz_column", "axis=z");
/*     */           }
/*     */         });
/*     */     
/* 247 */     func_178121_a((Block)Blocks.deadbush, (IStateMapper)new StateMapperBase()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002527";
/*     */           
/*     */           protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/* 252 */             return new ModelResourceLocation("dead_bush", "normal");
/*     */           }
/*     */         });
/* 255 */     func_178121_a(Blocks.pumpkin_stem, (IStateMapper)new StateMapperBase()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002526";
/*     */           
/*     */           protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/* 260 */             LinkedHashMap var2 = Maps.newLinkedHashMap((Map)p_178132_1_.getProperties());
/*     */             
/* 262 */             if (p_178132_1_.getValue((IProperty)BlockStem.FACING_PROP) != EnumFacing.UP)
/*     */             {
/* 264 */               var2.remove(BlockStem.AGE_PROP);
/*     */             }
/*     */             
/* 267 */             return new ModelResourceLocation((ResourceLocation)Block.blockRegistry.getNameForObject(p_178132_1_.getBlock()), func_178131_a(var2));
/*     */           }
/*     */         });
/* 270 */     func_178121_a(Blocks.melon_stem, (IStateMapper)new StateMapperBase()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002525";
/*     */           
/*     */           protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/* 275 */             LinkedHashMap var2 = Maps.newLinkedHashMap((Map)p_178132_1_.getProperties());
/*     */             
/* 277 */             if (p_178132_1_.getValue((IProperty)BlockStem.FACING_PROP) != EnumFacing.UP)
/*     */             {
/* 279 */               var2.remove(BlockStem.AGE_PROP);
/*     */             }
/*     */             
/* 282 */             return new ModelResourceLocation((ResourceLocation)Block.blockRegistry.getNameForObject(p_178132_1_.getBlock()), func_178131_a(var2));
/*     */           }
/*     */         });
/* 285 */     func_178121_a(Blocks.dirt, (IStateMapper)new StateMapperBase()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002524";
/*     */           
/*     */           protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/* 290 */             LinkedHashMap var2 = Maps.newLinkedHashMap((Map)p_178132_1_.getProperties());
/* 291 */             String var3 = BlockDirt.VARIANT.getName((Comparable)var2.remove(BlockDirt.VARIANT));
/*     */             
/* 293 */             if (BlockDirt.DirtType.PODZOL != p_178132_1_.getValue((IProperty)BlockDirt.VARIANT))
/*     */             {
/* 295 */               var2.remove(BlockDirt.SNOWY);
/*     */             }
/*     */             
/* 298 */             return new ModelResourceLocation(var3, func_178131_a(var2));
/*     */           }
/*     */         });
/* 301 */     func_178121_a((Block)Blocks.double_stone_slab, (IStateMapper)new StateMapperBase()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002523";
/*     */           
/*     */           protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/* 306 */             LinkedHashMap var2 = Maps.newLinkedHashMap((Map)p_178132_1_.getProperties());
/* 307 */             String var3 = BlockStoneSlab.field_176556_M.getName((Comparable)var2.remove(BlockStoneSlab.field_176556_M));
/* 308 */             var2.remove(BlockStoneSlab.field_176555_b);
/* 309 */             String var4 = ((Boolean)p_178132_1_.getValue((IProperty)BlockStoneSlab.field_176555_b)).booleanValue() ? "all" : "normal";
/* 310 */             return new ModelResourceLocation(String.valueOf(var3) + "_double_slab", var4);
/*     */           }
/*     */         });
/* 313 */     func_178121_a((Block)Blocks.double_stone_slab2, (IStateMapper)new StateMapperBase()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002522";
/*     */           
/*     */           protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/* 318 */             LinkedHashMap var2 = Maps.newLinkedHashMap((Map)p_178132_1_.getProperties());
/* 319 */             String var3 = BlockStoneSlabNew.field_176559_M.getName((Comparable)var2.remove(BlockStoneSlabNew.field_176559_M));
/* 320 */             var2.remove(BlockStoneSlab.field_176555_b);
/* 321 */             String var4 = ((Boolean)p_178132_1_.getValue((IProperty)BlockStoneSlabNew.field_176558_b)).booleanValue() ? "all" : "normal";
/* 322 */             return new ModelResourceLocation(String.valueOf(var3) + "_double_slab", var4);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumType
/*     */   {
/* 329 */     static final int[] field_178257_a = new int[(BlockQuartz.EnumType.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002521";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 336 */         field_178257_a[BlockQuartz.EnumType.DEFAULT.ordinal()] = 1;
/*     */       }
/* 338 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 345 */         field_178257_a[BlockQuartz.EnumType.CHISELED.ordinal()] = 2;
/*     */       }
/* 347 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 354 */         field_178257_a[BlockQuartz.EnumType.LINES_Y.ordinal()] = 3;
/*     */       }
/* 356 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 363 */         field_178257_a[BlockQuartz.EnumType.LINES_X.ordinal()] = 4;
/*     */       }
/* 365 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 372 */         field_178257_a[BlockQuartz.EnumType.LINES_Z.ordinal()] = 5;
/*     */       }
/* 374 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\BlockModelShapes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */