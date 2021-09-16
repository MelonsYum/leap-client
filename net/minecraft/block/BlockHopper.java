/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryHelper;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityHopper;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockHopper extends BlockContainer {
/*  29 */   public static final PropertyDirection field_176430_a = PropertyDirection.create("facing", new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002106";
/*     */         
/*     */         public boolean func_180180_a(EnumFacing p_180180_1_) {
/*  34 */           return (p_180180_1_ != EnumFacing.UP);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  38 */           return func_180180_a((EnumFacing)p_apply_1_);
/*     */         }
/*     */       });
/*  41 */   public static final PropertyBool field_176429_b = PropertyBool.create("enabled");
/*     */   
/*     */   private static final String __OBFID = "CL_00000257";
/*     */   
/*     */   public BlockHopper() {
/*  46 */     super(Material.iron);
/*  47 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176430_a, (Comparable)EnumFacing.DOWN).withProperty((IProperty)field_176429_b, Boolean.valueOf(true)));
/*  48 */     setCreativeTab(CreativeTabs.tabRedstone);
/*  49 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  54 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/*  64 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
/*  65 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  66 */     float var7 = 0.125F;
/*  67 */     setBlockBounds(0.0F, 0.0F, 0.0F, var7, 1.0F, 1.0F);
/*  68 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  69 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var7);
/*  70 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  71 */     setBlockBounds(1.0F - var7, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*  72 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  73 */     setBlockBounds(0.0F, 0.0F, 1.0F - var7, 1.0F, 1.0F, 1.0F);
/*  74 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  75 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  80 */     EnumFacing var9 = facing.getOpposite();
/*     */     
/*  82 */     if (var9 == EnumFacing.UP)
/*     */     {
/*  84 */       var9 = EnumFacing.DOWN;
/*     */     }
/*     */     
/*  87 */     return getDefaultState().withProperty((IProperty)field_176430_a, (Comparable)var9).withProperty((IProperty)field_176429_b, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/*  95 */     return (TileEntity)new TileEntityHopper();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 100 */     super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
/*     */     
/* 102 */     if (stack.hasDisplayName()) {
/*     */       
/* 104 */       TileEntity var6 = worldIn.getTileEntity(pos);
/*     */       
/* 106 */       if (var6 instanceof TileEntityHopper)
/*     */       {
/* 108 */         ((TileEntityHopper)var6).setCustomName(stack.getDisplayName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 115 */     func_176427_e(worldIn, pos, state);
/*     */   }
/*     */ 
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
/* 128 */     if (var9 instanceof TileEntityHopper)
/*     */     {
/* 130 */       playerIn.displayGUIChest((IInventory)var9);
/*     */     }
/*     */     
/* 133 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 139 */     func_176427_e(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176427_e(World worldIn, BlockPos p_176427_2_, IBlockState p_176427_3_) {
/* 144 */     boolean var4 = !worldIn.isBlockPowered(p_176427_2_);
/*     */     
/* 146 */     if (var4 != ((Boolean)p_176427_3_.getValue((IProperty)field_176429_b)).booleanValue())
/*     */     {
/* 148 */       worldIn.setBlockState(p_176427_2_, p_176427_3_.withProperty((IProperty)field_176429_b, Boolean.valueOf(var4)), 4);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 154 */     TileEntity var4 = worldIn.getTileEntity(pos);
/*     */     
/* 156 */     if (var4 instanceof TileEntityHopper) {
/*     */       
/* 158 */       InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)var4);
/* 159 */       worldIn.updateComparatorOutputLevel(pos, this);
/*     */     } 
/*     */     
/* 162 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 170 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/* 175 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumFacing func_176428_b(int p_176428_0_) {
/* 190 */     return EnumFacing.getFront(p_176428_0_ & 0x7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getActiveStateFromMetadata(int p_149917_0_) {
/* 199 */     return ((p_149917_0_ & 0x8) != 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 204 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 209 */     return Container.calcRedstoneFromInventory(worldIn.getTileEntity(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 214 */     return EnumWorldBlockLayer.CUTOUT_MIPPED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 222 */     return getDefaultState().withProperty((IProperty)field_176430_a, (Comparable)func_176428_b(meta)).withProperty((IProperty)field_176429_b, Boolean.valueOf(getActiveStateFromMetadata(meta)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 230 */     byte var2 = 0;
/* 231 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)field_176430_a)).getIndex();
/*     */     
/* 233 */     if (!((Boolean)state.getValue((IProperty)field_176429_b)).booleanValue())
/*     */     {
/* 235 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 238 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 243 */     return new BlockState(this, new IProperty[] { (IProperty)field_176430_a, (IProperty)field_176429_b });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */