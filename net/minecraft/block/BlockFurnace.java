/*     */ package net.minecraft.block;
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryHelper;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityFurnace;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockFurnace extends BlockContainer {
/*  25 */   public static final PropertyDirection FACING = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*     */   
/*     */   private final boolean isBurning;
/*     */   private static boolean field_149934_M;
/*     */   private static final String __OBFID = "CL_00000248";
/*     */   
/*     */   protected BlockFurnace(boolean p_i45407_1_) {
/*  32 */     super(Material.rock);
/*  33 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING, (Comparable)EnumFacing.NORTH));
/*  34 */     this.isBurning = p_i45407_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  44 */     return Item.getItemFromBlock(Blocks.furnace);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  49 */     func_176445_e(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176445_e(World worldIn, BlockPos p_176445_2_, IBlockState p_176445_3_) {
/*  54 */     if (!worldIn.isRemote) {
/*     */       
/*  56 */       Block var4 = worldIn.getBlockState(p_176445_2_.offsetNorth()).getBlock();
/*  57 */       Block var5 = worldIn.getBlockState(p_176445_2_.offsetSouth()).getBlock();
/*  58 */       Block var6 = worldIn.getBlockState(p_176445_2_.offsetWest()).getBlock();
/*  59 */       Block var7 = worldIn.getBlockState(p_176445_2_.offsetEast()).getBlock();
/*  60 */       EnumFacing var8 = (EnumFacing)p_176445_3_.getValue((IProperty)FACING);
/*     */       
/*  62 */       if (var8 == EnumFacing.NORTH && var4.isFullBlock() && !var5.isFullBlock()) {
/*     */         
/*  64 */         var8 = EnumFacing.SOUTH;
/*     */       }
/*  66 */       else if (var8 == EnumFacing.SOUTH && var5.isFullBlock() && !var4.isFullBlock()) {
/*     */         
/*  68 */         var8 = EnumFacing.NORTH;
/*     */       }
/*  70 */       else if (var8 == EnumFacing.WEST && var6.isFullBlock() && !var7.isFullBlock()) {
/*     */         
/*  72 */         var8 = EnumFacing.EAST;
/*     */       }
/*  74 */       else if (var8 == EnumFacing.EAST && var7.isFullBlock() && !var6.isFullBlock()) {
/*     */         
/*  76 */         var8 = EnumFacing.WEST;
/*     */       } 
/*     */       
/*  79 */       worldIn.setBlockState(p_176445_2_, p_176445_3_.withProperty((IProperty)FACING, (Comparable)var8), 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  85 */     if (this.isBurning) {
/*     */       
/*  87 */       EnumFacing var5 = (EnumFacing)state.getValue((IProperty)FACING);
/*  88 */       double var6 = pos.getX() + 0.5D;
/*  89 */       double var8 = pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
/*  90 */       double var10 = pos.getZ() + 0.5D;
/*  91 */       double var12 = 0.52D;
/*  92 */       double var14 = rand.nextDouble() * 0.6D - 0.3D;
/*     */       
/*  94 */       switch (SwitchEnumFacing.field_180356_a[var5.ordinal()]) {
/*     */         
/*     */         case 1:
/*  97 */           worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var6 - var12, var8, var10 + var14, 0.0D, 0.0D, 0.0D, new int[0]);
/*  98 */           worldIn.spawnParticle(EnumParticleTypes.FLAME, var6 - var12, var8, var10 + var14, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           break;
/*     */         
/*     */         case 2:
/* 102 */           worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var6 + var12, var8, var10 + var14, 0.0D, 0.0D, 0.0D, new int[0]);
/* 103 */           worldIn.spawnParticle(EnumParticleTypes.FLAME, var6 + var12, var8, var10 + var14, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           break;
/*     */         
/*     */         case 3:
/* 107 */           worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var6 + var14, var8, var10 - var12, 0.0D, 0.0D, 0.0D, new int[0]);
/* 108 */           worldIn.spawnParticle(EnumParticleTypes.FLAME, var6 + var14, var8, var10 - var12, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           break;
/*     */         
/*     */         case 4:
/* 112 */           worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var6 + var14, var8, var10 + var12, 0.0D, 0.0D, 0.0D, new int[0]);
/* 113 */           worldIn.spawnParticle(EnumParticleTypes.FLAME, var6 + var14, var8, var10 + var12, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 120 */     if (worldIn.isRemote)
/*     */     {
/* 122 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 126 */     TileEntity var9 = worldIn.getTileEntity(pos);
/*     */     
/* 128 */     if (var9 instanceof TileEntityFurnace)
/*     */     {
/* 130 */       playerIn.displayGUIChest((IInventory)var9);
/*     */     }
/*     */     
/* 133 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void func_176446_a(boolean p_176446_0_, World worldIn, BlockPos p_176446_2_) {
/* 139 */     IBlockState var3 = worldIn.getBlockState(p_176446_2_);
/* 140 */     TileEntity var4 = worldIn.getTileEntity(p_176446_2_);
/* 141 */     field_149934_M = true;
/*     */     
/* 143 */     if (p_176446_0_) {
/*     */       
/* 145 */       worldIn.setBlockState(p_176446_2_, Blocks.lit_furnace.getDefaultState().withProperty((IProperty)FACING, var3.getValue((IProperty)FACING)), 3);
/* 146 */       worldIn.setBlockState(p_176446_2_, Blocks.lit_furnace.getDefaultState().withProperty((IProperty)FACING, var3.getValue((IProperty)FACING)), 3);
/*     */     }
/*     */     else {
/*     */       
/* 150 */       worldIn.setBlockState(p_176446_2_, Blocks.furnace.getDefaultState().withProperty((IProperty)FACING, var3.getValue((IProperty)FACING)), 3);
/* 151 */       worldIn.setBlockState(p_176446_2_, Blocks.furnace.getDefaultState().withProperty((IProperty)FACING, var3.getValue((IProperty)FACING)), 3);
/*     */     } 
/*     */     
/* 154 */     field_149934_M = false;
/*     */     
/* 156 */     if (var4 != null) {
/*     */       
/* 158 */       var4.validate();
/* 159 */       worldIn.setTileEntity(p_176446_2_, var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 168 */     return (TileEntity)new TileEntityFurnace();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 173 */     return getDefaultState().withProperty((IProperty)FACING, (Comparable)placer.func_174811_aO().getOpposite());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 178 */     worldIn.setBlockState(pos, state.withProperty((IProperty)FACING, (Comparable)placer.func_174811_aO().getOpposite()), 2);
/*     */     
/* 180 */     if (stack.hasDisplayName()) {
/*     */       
/* 182 */       TileEntity var6 = worldIn.getTileEntity(pos);
/*     */       
/* 184 */       if (var6 instanceof TileEntityFurnace)
/*     */       {
/* 186 */         ((TileEntityFurnace)var6).setCustomInventoryName(stack.getDisplayName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 193 */     if (!field_149934_M) {
/*     */       
/* 195 */       TileEntity var4 = worldIn.getTileEntity(pos);
/*     */       
/* 197 */       if (var4 instanceof TileEntityFurnace) {
/*     */         
/* 199 */         InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)var4);
/* 200 */         worldIn.updateComparatorOutputLevel(pos, this);
/*     */       } 
/*     */     } 
/*     */     
/* 204 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 209 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 214 */     return Container.calcRedstoneFromInventory(worldIn.getTileEntity(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 219 */     return Item.getItemFromBlock(Blocks.furnace);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 227 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateForEntityRender(IBlockState state) {
/* 235 */     return getDefaultState().withProperty((IProperty)FACING, (Comparable)EnumFacing.SOUTH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 243 */     EnumFacing var2 = EnumFacing.getFront(meta);
/*     */     
/* 245 */     if (var2.getAxis() == EnumFacing.Axis.Y)
/*     */     {
/* 247 */       var2 = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 250 */     return getDefaultState().withProperty((IProperty)FACING, (Comparable)var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 258 */     return ((EnumFacing)state.getValue((IProperty)FACING)).getIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 263 */     return new BlockState(this, new IProperty[] { (IProperty)FACING });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 268 */     static final int[] field_180356_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002111";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 275 */         field_180356_a[EnumFacing.WEST.ordinal()] = 1;
/*     */       }
/* 277 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 284 */         field_180356_a[EnumFacing.EAST.ordinal()] = 2;
/*     */       }
/* 286 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 293 */         field_180356_a[EnumFacing.NORTH.ordinal()] = 3;
/*     */       }
/* 295 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 302 */         field_180356_a[EnumFacing.SOUTH.ordinal()] = 4;
/*     */       }
/* 304 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */