/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityFlowerPot;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockFlowerPot
/*     */   extends BlockContainer
/*     */ {
/*  27 */   public static final PropertyInteger field_176444_a = PropertyInteger.create("legacy_data", 0, 15);
/*  28 */   public static final PropertyEnum field_176443_b = PropertyEnum.create("contents", EnumFlowerType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000247";
/*     */   
/*     */   public BlockFlowerPot() {
/*  33 */     super(Material.circuits);
/*  34 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176443_b, EnumFlowerType.EMPTY).withProperty((IProperty)field_176444_a, Integer.valueOf(0)));
/*  35 */     setBlockBoundsForItemRender();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  43 */     float var1 = 0.375F;
/*  44 */     float var2 = var1 / 2.0F;
/*  45 */     setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, var1, 0.5F + var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  58 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/*  68 */     TileEntity var4 = worldIn.getTileEntity(pos);
/*     */     
/*  70 */     if (var4 instanceof TileEntityFlowerPot) {
/*     */       
/*  72 */       Item var5 = ((TileEntityFlowerPot)var4).getFlowerPotItem();
/*     */       
/*  74 */       if (var5 instanceof net.minecraft.item.ItemBlock)
/*     */       {
/*  76 */         return Block.getBlockFromItem(var5).colorMultiplier(worldIn, pos, renderPass);
/*     */       }
/*     */     } 
/*     */     
/*  80 */     return 16777215;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  85 */     ItemStack var9 = playerIn.inventory.getCurrentItem();
/*     */     
/*  87 */     if (var9 != null && var9.getItem() instanceof net.minecraft.item.ItemBlock) {
/*     */       
/*  89 */       TileEntityFlowerPot var10 = func_176442_d(worldIn, pos);
/*     */       
/*  91 */       if (var10 == null)
/*     */       {
/*  93 */         return false;
/*     */       }
/*  95 */       if (var10.getFlowerPotItem() != null)
/*     */       {
/*  97 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 101 */       Block var11 = Block.getBlockFromItem(var9.getItem());
/*     */       
/* 103 */       if (!func_149928_a(var11, var9.getMetadata()))
/*     */       {
/* 105 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 109 */       var10.func_145964_a(var9.getItem(), var9.getMetadata());
/* 110 */       var10.markDirty();
/* 111 */       worldIn.markBlockForUpdate(pos);
/*     */       
/* 113 */       if (!playerIn.capabilities.isCreativeMode && --var9.stackSize <= 0)
/*     */       {
/* 115 */         playerIn.inventory.setInventorySlotContents(playerIn.inventory.currentItem, null);
/*     */       }
/*     */       
/* 118 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean func_149928_a(Block p_149928_1_, int p_149928_2_) {
/* 130 */     return (p_149928_1_ != Blocks.yellow_flower && p_149928_1_ != Blocks.red_flower && p_149928_1_ != Blocks.cactus && p_149928_1_ != Blocks.brown_mushroom && p_149928_1_ != Blocks.red_mushroom && p_149928_1_ != Blocks.sapling && p_149928_1_ != Blocks.deadbush) ? ((p_149928_1_ == Blocks.tallgrass && p_149928_2_ == BlockTallGrass.EnumType.FERN.func_177044_a())) : true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 135 */     TileEntityFlowerPot var3 = func_176442_d(worldIn, pos);
/* 136 */     return (var3 != null && var3.getFlowerPotItem() != null) ? var3.getFlowerPotItem() : Items.flower_pot;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/* 141 */     TileEntityFlowerPot var3 = func_176442_d(worldIn, pos);
/* 142 */     return (var3 != null && var3.getFlowerPotItem() != null) ? var3.getFlowerPotData() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFlowerPot() {
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/* 155 */     return (super.canPlaceBlockAt(worldIn, pos) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 160 */     if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown())) {
/*     */       
/* 162 */       dropBlockAsItem(worldIn, pos, state, 0);
/* 163 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 169 */     TileEntityFlowerPot var4 = func_176442_d(worldIn, pos);
/*     */     
/* 171 */     if (var4 != null && var4.getFlowerPotItem() != null)
/*     */     {
/* 173 */       spawnAsEntity(worldIn, pos, new ItemStack(var4.getFlowerPotItem(), 1, var4.getFlowerPotData()));
/*     */     }
/*     */     
/* 176 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
/* 181 */     super.onBlockHarvested(worldIn, pos, state, playerIn);
/*     */     
/* 183 */     if (playerIn.capabilities.isCreativeMode) {
/*     */       
/* 185 */       TileEntityFlowerPot var5 = func_176442_d(worldIn, pos);
/*     */       
/* 187 */       if (var5 != null)
/*     */       {
/* 189 */         var5.func_145964_a(null, 0);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 201 */     return Items.flower_pot;
/*     */   }
/*     */ 
/*     */   
/*     */   private TileEntityFlowerPot func_176442_d(World worldIn, BlockPos p_176442_2_) {
/* 206 */     TileEntity var3 = worldIn.getTileEntity(p_176442_2_);
/* 207 */     return (var3 instanceof TileEntityFlowerPot) ? (TileEntityFlowerPot)var3 : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 215 */     Object var3 = null;
/* 216 */     int var4 = 0;
/*     */     
/* 218 */     switch (meta) {
/*     */       
/*     */       case 1:
/* 221 */         var3 = Blocks.red_flower;
/* 222 */         var4 = BlockFlower.EnumFlowerType.POPPY.func_176968_b();
/*     */         break;
/*     */       
/*     */       case 2:
/* 226 */         var3 = Blocks.yellow_flower;
/*     */         break;
/*     */       
/*     */       case 3:
/* 230 */         var3 = Blocks.sapling;
/* 231 */         var4 = BlockPlanks.EnumType.OAK.func_176839_a();
/*     */         break;
/*     */       
/*     */       case 4:
/* 235 */         var3 = Blocks.sapling;
/* 236 */         var4 = BlockPlanks.EnumType.SPRUCE.func_176839_a();
/*     */         break;
/*     */       
/*     */       case 5:
/* 240 */         var3 = Blocks.sapling;
/* 241 */         var4 = BlockPlanks.EnumType.BIRCH.func_176839_a();
/*     */         break;
/*     */       
/*     */       case 6:
/* 245 */         var3 = Blocks.sapling;
/* 246 */         var4 = BlockPlanks.EnumType.JUNGLE.func_176839_a();
/*     */         break;
/*     */       
/*     */       case 7:
/* 250 */         var3 = Blocks.red_mushroom;
/*     */         break;
/*     */       
/*     */       case 8:
/* 254 */         var3 = Blocks.brown_mushroom;
/*     */         break;
/*     */       
/*     */       case 9:
/* 258 */         var3 = Blocks.cactus;
/*     */         break;
/*     */       
/*     */       case 10:
/* 262 */         var3 = Blocks.deadbush;
/*     */         break;
/*     */       
/*     */       case 11:
/* 266 */         var3 = Blocks.tallgrass;
/* 267 */         var4 = BlockTallGrass.EnumType.FERN.func_177044_a();
/*     */         break;
/*     */       
/*     */       case 12:
/* 271 */         var3 = Blocks.sapling;
/* 272 */         var4 = BlockPlanks.EnumType.ACACIA.func_176839_a();
/*     */         break;
/*     */       
/*     */       case 13:
/* 276 */         var3 = Blocks.sapling;
/* 277 */         var4 = BlockPlanks.EnumType.DARK_OAK.func_176839_a();
/*     */         break;
/*     */     } 
/* 280 */     return (TileEntity)new TileEntityFlowerPot(Item.getItemFromBlock((Block)var3), var4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 285 */     return new BlockState(this, new IProperty[] { (IProperty)field_176443_b, (IProperty)field_176444_a });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 293 */     return ((Integer)state.getValue((IProperty)field_176444_a)).intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 302 */     EnumFlowerType var4 = EnumFlowerType.EMPTY;
/* 303 */     TileEntity var5 = worldIn.getTileEntity(pos);
/*     */     
/* 305 */     if (var5 instanceof TileEntityFlowerPot)
/*     */     
/* 307 */     { TileEntityFlowerPot var6 = (TileEntityFlowerPot)var5;
/* 308 */       Item var7 = var6.getFlowerPotItem();
/*     */       
/* 310 */       if (var7 instanceof net.minecraft.item.ItemBlock)
/*     */       
/* 312 */       { int var8 = var6.getFlowerPotData();
/* 313 */         Block var9 = Block.getBlockFromItem(var7);
/*     */         
/* 315 */         if (var9 == Blocks.sapling)
/*     */         
/* 317 */         { switch (SwitchEnumType.field_180353_a[BlockPlanks.EnumType.func_176837_a(var8).ordinal()])
/*     */           
/*     */           { case 1:
/* 320 */               var4 = EnumFlowerType.OAK_SAPLING;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 430 */               return state.withProperty((IProperty)field_176443_b, var4);case 2: var4 = EnumFlowerType.SPRUCE_SAPLING; return state.withProperty((IProperty)field_176443_b, var4);case 3: var4 = EnumFlowerType.BIRCH_SAPLING; return state.withProperty((IProperty)field_176443_b, var4);case 4: var4 = EnumFlowerType.JUNGLE_SAPLING; return state.withProperty((IProperty)field_176443_b, var4);case 5: var4 = EnumFlowerType.ACACIA_SAPLING; return state.withProperty((IProperty)field_176443_b, var4);case 6: var4 = EnumFlowerType.DARK_OAK_SAPLING; return state.withProperty((IProperty)field_176443_b, var4); }  var4 = EnumFlowerType.EMPTY; } else if (var9 == Blocks.tallgrass) { switch (var8) { case 0: var4 = EnumFlowerType.DEAD_BUSH; return state.withProperty((IProperty)field_176443_b, var4);case 2: var4 = EnumFlowerType.FERN; return state.withProperty((IProperty)field_176443_b, var4); }  var4 = EnumFlowerType.EMPTY; } else if (var9 == Blocks.yellow_flower) { var4 = EnumFlowerType.DANDELION; } else if (var9 == Blocks.red_flower) { switch (SwitchEnumType.field_180352_b[BlockFlower.EnumFlowerType.func_176967_a(BlockFlower.EnumFlowerColor.RED, var8).ordinal()]) { case 1: var4 = EnumFlowerType.POPPY; return state.withProperty((IProperty)field_176443_b, var4);case 2: var4 = EnumFlowerType.BLUE_ORCHID; return state.withProperty((IProperty)field_176443_b, var4);case 3: var4 = EnumFlowerType.ALLIUM; return state.withProperty((IProperty)field_176443_b, var4);case 4: var4 = EnumFlowerType.HOUSTONIA; return state.withProperty((IProperty)field_176443_b, var4);case 5: var4 = EnumFlowerType.RED_TULIP; return state.withProperty((IProperty)field_176443_b, var4);case 6: var4 = EnumFlowerType.ORANGE_TULIP; return state.withProperty((IProperty)field_176443_b, var4);case 7: var4 = EnumFlowerType.WHITE_TULIP; return state.withProperty((IProperty)field_176443_b, var4);case 8: var4 = EnumFlowerType.PINK_TULIP; return state.withProperty((IProperty)field_176443_b, var4);case 9: var4 = EnumFlowerType.OXEYE_DAISY; return state.withProperty((IProperty)field_176443_b, var4); }  var4 = EnumFlowerType.EMPTY; } else if (var9 == Blocks.red_mushroom) { var4 = EnumFlowerType.MUSHROOM_RED; } else if (var9 == Blocks.brown_mushroom) { var4 = EnumFlowerType.MUSHROOM_BROWN; } else if (var9 == Blocks.deadbush) { var4 = EnumFlowerType.DEAD_BUSH; } else if (var9 == Blocks.cactus) { var4 = EnumFlowerType.CACTUS; }  }  }  return state.withProperty((IProperty)field_176443_b, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 435 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */   
/*     */   public enum EnumFlowerType
/*     */     implements IStringSerializable {
/* 440 */     EMPTY("EMPTY", 0, "empty"),
/* 441 */     POPPY("POPPY", 1, "rose"),
/* 442 */     BLUE_ORCHID("BLUE_ORCHID", 2, "blue_orchid"),
/* 443 */     ALLIUM("ALLIUM", 3, "allium"),
/* 444 */     HOUSTONIA("HOUSTONIA", 4, "houstonia"),
/* 445 */     RED_TULIP("RED_TULIP", 5, "red_tulip"),
/* 446 */     ORANGE_TULIP("ORANGE_TULIP", 6, "orange_tulip"),
/* 447 */     WHITE_TULIP("WHITE_TULIP", 7, "white_tulip"),
/* 448 */     PINK_TULIP("PINK_TULIP", 8, "pink_tulip"),
/* 449 */     OXEYE_DAISY("OXEYE_DAISY", 9, "oxeye_daisy"),
/* 450 */     DANDELION("DANDELION", 10, "dandelion"),
/* 451 */     OAK_SAPLING("OAK_SAPLING", 11, "oak_sapling"),
/* 452 */     SPRUCE_SAPLING("SPRUCE_SAPLING", 12, "spruce_sapling"),
/* 453 */     BIRCH_SAPLING("BIRCH_SAPLING", 13, "birch_sapling"),
/* 454 */     JUNGLE_SAPLING("JUNGLE_SAPLING", 14, "jungle_sapling"),
/* 455 */     ACACIA_SAPLING("ACACIA_SAPLING", 15, "acacia_sapling"),
/* 456 */     DARK_OAK_SAPLING("DARK_OAK_SAPLING", 16, "dark_oak_sapling"),
/* 457 */     MUSHROOM_RED("MUSHROOM_RED", 17, "mushroom_red"),
/* 458 */     MUSHROOM_BROWN("MUSHROOM_BROWN", 18, "mushroom_brown"),
/* 459 */     DEAD_BUSH("DEAD_BUSH", 19, "dead_bush"),
/* 460 */     FERN("FERN", 20, "fern"),
/* 461 */     CACTUS("CACTUS", 21, "cactus");
/*     */     
/*     */     private final String field_177006_w;
/* 464 */     private static final EnumFlowerType[] $VALUES = new EnumFlowerType[] { EMPTY, POPPY, BLUE_ORCHID, ALLIUM, HOUSTONIA, RED_TULIP, ORANGE_TULIP, WHITE_TULIP, PINK_TULIP, OXEYE_DAISY, DANDELION, OAK_SAPLING, SPRUCE_SAPLING, BIRCH_SAPLING, JUNGLE_SAPLING, ACACIA_SAPLING, DARK_OAK_SAPLING, MUSHROOM_RED, MUSHROOM_BROWN, DEAD_BUSH, FERN, CACTUS }; private static final String __OBFID = "CL_00002115";
/*     */     static {
/*     */     
/*     */     }
/*     */     EnumFlowerType(String p_i45715_1_, int p_i45715_2_, String p_i45715_3_) {
/* 469 */       this.field_177006_w = p_i45715_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 474 */       return this.field_177006_w;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 479 */       return this.field_177006_w;
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
/*     */   static final class SwitchEnumType
/*     */   {
/* 573 */     static final int[] field_180353_a = new int[(BlockPlanks.EnumType.values()).length]; static final int[] field_180352_b = new int[(BlockFlower.EnumFlowerType.values()).length]; private static final String __OBFID = "CL_00002116";
/*     */     
/*     */     static {
/*     */       try {
/* 577 */         field_180353_a[BlockPlanks.EnumType.OAK.ordinal()] = 1;
/*     */       }
/* 579 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 586 */         field_180353_a[BlockPlanks.EnumType.SPRUCE.ordinal()] = 2;
/*     */       }
/* 588 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 595 */         field_180353_a[BlockPlanks.EnumType.BIRCH.ordinal()] = 3;
/*     */       }
/* 597 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 604 */         field_180353_a[BlockPlanks.EnumType.JUNGLE.ordinal()] = 4;
/*     */       }
/* 606 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 613 */         field_180353_a[BlockPlanks.EnumType.ACACIA.ordinal()] = 5;
/*     */       }
/* 615 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 622 */         field_180353_a[BlockPlanks.EnumType.DARK_OAK.ordinal()] = 6;
/*     */       }
/* 624 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockFlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */