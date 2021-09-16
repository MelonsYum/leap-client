/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
/*     */ import net.minecraft.dispenser.IBehaviorDispenseItem;
/*     */ import net.minecraft.dispenser.IBlockSource;
/*     */ import net.minecraft.dispenser.IPosition;
/*     */ import net.minecraft.dispenser.PositionImpl;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryHelper;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityDispenser;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.RegistryDefaulted;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockDispenser extends BlockContainer {
/*  30 */   public static final PropertyDirection FACING = PropertyDirection.create("facing");
/*  31 */   public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");
/*     */ 
/*     */   
/*  34 */   public static final RegistryDefaulted dispenseBehaviorRegistry = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
/*  35 */   protected Random rand = new Random();
/*     */   
/*     */   private static final String __OBFID = "CL_00000229";
/*     */   
/*     */   protected BlockDispenser() {
/*  40 */     super(Material.rock);
/*  41 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING, (Comparable)EnumFacing.NORTH).withProperty((IProperty)TRIGGERED, Boolean.valueOf(false)));
/*  42 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/*  50 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  55 */     super.onBlockAdded(worldIn, pos, state);
/*  56 */     setDefaultDirection(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state) {
/*  61 */     if (!worldIn.isRemote) {
/*     */       
/*  63 */       EnumFacing var4 = (EnumFacing)state.getValue((IProperty)FACING);
/*  64 */       boolean var5 = worldIn.getBlockState(pos.offsetNorth()).getBlock().isFullBlock();
/*  65 */       boolean var6 = worldIn.getBlockState(pos.offsetSouth()).getBlock().isFullBlock();
/*     */       
/*  67 */       if (var4 == EnumFacing.NORTH && var5 && !var6) {
/*     */         
/*  69 */         var4 = EnumFacing.SOUTH;
/*     */       }
/*  71 */       else if (var4 == EnumFacing.SOUTH && var6 && !var5) {
/*     */         
/*  73 */         var4 = EnumFacing.NORTH;
/*     */       }
/*     */       else {
/*     */         
/*  77 */         boolean var7 = worldIn.getBlockState(pos.offsetWest()).getBlock().isFullBlock();
/*  78 */         boolean var8 = worldIn.getBlockState(pos.offsetEast()).getBlock().isFullBlock();
/*     */         
/*  80 */         if (var4 == EnumFacing.WEST && var7 && !var8) {
/*     */           
/*  82 */           var4 = EnumFacing.EAST;
/*     */         }
/*  84 */         else if (var4 == EnumFacing.EAST && var8 && !var7) {
/*     */           
/*  86 */           var4 = EnumFacing.WEST;
/*     */         } 
/*     */       } 
/*     */       
/*  90 */       worldIn.setBlockState(pos, state.withProperty((IProperty)FACING, (Comparable)var4).withProperty((IProperty)TRIGGERED, Boolean.valueOf(false)), 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  96 */     if (worldIn.isRemote)
/*     */     {
/*  98 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 102 */     TileEntity var9 = worldIn.getTileEntity(pos);
/*     */     
/* 104 */     if (var9 instanceof TileEntityDispenser)
/*     */     {
/* 106 */       playerIn.displayGUIChest((IInventory)var9);
/*     */     }
/*     */     
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_176439_d(World worldIn, BlockPos p_176439_2_) {
/* 115 */     BlockSourceImpl var3 = new BlockSourceImpl(worldIn, p_176439_2_);
/* 116 */     TileEntityDispenser var4 = (TileEntityDispenser)var3.getBlockTileEntity();
/*     */     
/* 118 */     if (var4 != null) {
/*     */       
/* 120 */       int var5 = var4.func_146017_i();
/*     */       
/* 122 */       if (var5 < 0) {
/*     */         
/* 124 */         worldIn.playAuxSFX(1001, p_176439_2_, 0);
/*     */       }
/*     */       else {
/*     */         
/* 128 */         ItemStack var6 = var4.getStackInSlot(var5);
/* 129 */         IBehaviorDispenseItem var7 = func_149940_a(var6);
/*     */         
/* 131 */         if (var7 != IBehaviorDispenseItem.itemDispenseBehaviorProvider) {
/*     */           
/* 133 */           ItemStack var8 = var7.dispense(var3, var6);
/* 134 */           var4.setInventorySlotContents(var5, (var8.stackSize == 0) ? null : var8);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBehaviorDispenseItem func_149940_a(ItemStack p_149940_1_) {
/* 142 */     return (IBehaviorDispenseItem)dispenseBehaviorRegistry.getObject((p_149940_1_ == null) ? null : p_149940_1_.getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 147 */     boolean var5 = !(!worldIn.isBlockPowered(pos) && !worldIn.isBlockPowered(pos.offsetUp()));
/* 148 */     boolean var6 = ((Boolean)state.getValue((IProperty)TRIGGERED)).booleanValue();
/*     */     
/* 150 */     if (var5 && !var6) {
/*     */       
/* 152 */       worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/* 153 */       worldIn.setBlockState(pos, state.withProperty((IProperty)TRIGGERED, Boolean.valueOf(true)), 4);
/*     */     }
/* 155 */     else if (!var5 && var6) {
/*     */       
/* 157 */       worldIn.setBlockState(pos, state.withProperty((IProperty)TRIGGERED, Boolean.valueOf(false)), 4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 163 */     if (!worldIn.isRemote)
/*     */     {
/* 165 */       func_176439_d(worldIn, pos);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 174 */     return (TileEntity)new TileEntityDispenser();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 179 */     return getDefaultState().withProperty((IProperty)FACING, (Comparable)BlockPistonBase.func_180695_a(worldIn, pos, placer)).withProperty((IProperty)TRIGGERED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 184 */     worldIn.setBlockState(pos, state.withProperty((IProperty)FACING, (Comparable)BlockPistonBase.func_180695_a(worldIn, pos, placer)), 2);
/*     */     
/* 186 */     if (stack.hasDisplayName()) {
/*     */       
/* 188 */       TileEntity var6 = worldIn.getTileEntity(pos);
/*     */       
/* 190 */       if (var6 instanceof TileEntityDispenser)
/*     */       {
/* 192 */         ((TileEntityDispenser)var6).func_146018_a(stack.getDisplayName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 199 */     TileEntity var4 = worldIn.getTileEntity(pos);
/*     */     
/* 201 */     if (var4 instanceof TileEntityDispenser) {
/*     */       
/* 203 */       InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)var4);
/* 204 */       worldIn.updateComparatorOutputLevel(pos, this);
/*     */     } 
/*     */     
/* 207 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static IPosition getDispensePosition(IBlockSource coords) {
/* 215 */     EnumFacing var1 = getFacing(coords.getBlockMetadata());
/* 216 */     double var2 = coords.getX() + 0.7D * var1.getFrontOffsetX();
/* 217 */     double var4 = coords.getY() + 0.7D * var1.getFrontOffsetY();
/* 218 */     double var6 = coords.getZ() + 0.7D * var1.getFrontOffsetZ();
/* 219 */     return (IPosition)new PositionImpl(var2, var4, var6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static EnumFacing getFacing(int meta) {
/* 227 */     return EnumFacing.getFront(meta & 0x7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 232 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 237 */     return Container.calcRedstoneFromInventory(worldIn.getTileEntity(pos));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 245 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateForEntityRender(IBlockState state) {
/* 253 */     return getDefaultState().withProperty((IProperty)FACING, (Comparable)EnumFacing.SOUTH);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 261 */     return getDefaultState().withProperty((IProperty)FACING, (Comparable)getFacing(meta)).withProperty((IProperty)TRIGGERED, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 269 */     byte var2 = 0;
/* 270 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)FACING)).getIndex();
/*     */     
/* 272 */     if (((Boolean)state.getValue((IProperty)TRIGGERED)).booleanValue())
/*     */     {
/* 274 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 277 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 282 */     return new BlockState(this, new IProperty[] { (IProperty)FACING, (IProperty)TRIGGERED });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */