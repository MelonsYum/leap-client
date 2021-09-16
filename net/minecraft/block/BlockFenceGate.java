/*     */ package net.minecraft.block;
/*     */ 
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockFenceGate
/*     */   extends BlockDirectional {
/*  20 */   public static final PropertyBool field_176466_a = PropertyBool.create("open");
/*  21 */   public static final PropertyBool field_176465_b = PropertyBool.create("powered");
/*  22 */   public static final PropertyBool field_176467_M = PropertyBool.create("in_wall");
/*     */   
/*     */   private static final String __OBFID = "CL_00000243";
/*     */   
/*     */   public BlockFenceGate() {
/*  27 */     super(Material.wood);
/*  28 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176466_a, Boolean.valueOf(false)).withProperty((IProperty)field_176465_b, Boolean.valueOf(false)).withProperty((IProperty)field_176467_M, Boolean.valueOf(false)));
/*  29 */     setCreativeTab(CreativeTabs.tabRedstone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  38 */     EnumFacing.Axis var4 = ((EnumFacing)state.getValue((IProperty)AGE)).getAxis();
/*     */     
/*  40 */     if ((var4 == EnumFacing.Axis.Z && (worldIn.getBlockState(pos.offsetWest()).getBlock() == Blocks.cobblestone_wall || worldIn.getBlockState(pos.offsetEast()).getBlock() == Blocks.cobblestone_wall)) || (var4 == EnumFacing.Axis.X && (worldIn.getBlockState(pos.offsetNorth()).getBlock() == Blocks.cobblestone_wall || worldIn.getBlockState(pos.offsetSouth()).getBlock() == Blocks.cobblestone_wall)))
/*     */     {
/*  42 */       state = state.withProperty((IProperty)field_176467_M, Boolean.valueOf(true));
/*     */     }
/*     */     
/*  45 */     return state;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  50 */     return worldIn.getBlockState(pos.offsetDown()).getBlock().getMaterial().isSolid() ? super.canPlaceBlockAt(worldIn, pos) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  55 */     if (((Boolean)state.getValue((IProperty)field_176466_a)).booleanValue())
/*     */     {
/*  57 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  61 */     EnumFacing.Axis var4 = ((EnumFacing)state.getValue((IProperty)AGE)).getAxis();
/*  62 */     return (var4 == EnumFacing.Axis.Z) ? new AxisAlignedBB(pos.getX(), pos.getY(), (pos.getZ() + 0.375F), (pos.getX() + 1), (pos.getY() + 1.5F), (pos.getZ() + 0.625F)) : new AxisAlignedBB((pos.getX() + 0.375F), pos.getY(), pos.getZ(), (pos.getX() + 0.625F), (pos.getY() + 1.5F), (pos.getZ() + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  68 */     EnumFacing.Axis var3 = ((EnumFacing)access.getBlockState(pos).getValue((IProperty)AGE)).getAxis();
/*     */     
/*  70 */     if (var3 == EnumFacing.Axis.Z) {
/*     */       
/*  72 */       setBlockBounds(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
/*     */     }
/*     */     else {
/*     */       
/*  76 */       setBlockBounds(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  87 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/*  92 */     return ((Boolean)blockAccess.getBlockState(pos).getValue((IProperty)field_176466_a)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  97 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)placer.func_174811_aO()).withProperty((IProperty)field_176466_a, Boolean.valueOf(false)).withProperty((IProperty)field_176465_b, Boolean.valueOf(false)).withProperty((IProperty)field_176467_M, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 102 */     if (((Boolean)state.getValue((IProperty)field_176466_a)).booleanValue()) {
/*     */       
/* 104 */       state = state.withProperty((IProperty)field_176466_a, Boolean.valueOf(false));
/* 105 */       worldIn.setBlockState(pos, state, 2);
/*     */     }
/*     */     else {
/*     */       
/* 109 */       EnumFacing var9 = EnumFacing.fromAngle(playerIn.rotationYaw);
/*     */       
/* 111 */       if (state.getValue((IProperty)AGE) == var9.getOpposite())
/*     */       {
/* 113 */         state = state.withProperty((IProperty)AGE, (Comparable)var9);
/*     */       }
/*     */       
/* 116 */       state = state.withProperty((IProperty)field_176466_a, Boolean.valueOf(true));
/* 117 */       worldIn.setBlockState(pos, state, 2);
/*     */     } 
/*     */     
/* 120 */     worldIn.playAuxSFXAtEntity(playerIn, ((Boolean)state.getValue((IProperty)field_176466_a)).booleanValue() ? 1003 : 1006, pos, 0);
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 126 */     if (!worldIn.isRemote) {
/*     */       
/* 128 */       boolean var5 = worldIn.isBlockPowered(pos);
/*     */       
/* 130 */       if (var5 || neighborBlock.canProvidePower())
/*     */       {
/* 132 */         if (var5 && !((Boolean)state.getValue((IProperty)field_176466_a)).booleanValue() && !((Boolean)state.getValue((IProperty)field_176465_b)).booleanValue()) {
/*     */           
/* 134 */           worldIn.setBlockState(pos, state.withProperty((IProperty)field_176466_a, Boolean.valueOf(true)).withProperty((IProperty)field_176465_b, Boolean.valueOf(true)), 2);
/* 135 */           worldIn.playAuxSFXAtEntity(null, 1003, pos, 0);
/*     */         }
/* 137 */         else if (!var5 && ((Boolean)state.getValue((IProperty)field_176466_a)).booleanValue() && ((Boolean)state.getValue((IProperty)field_176465_b)).booleanValue()) {
/*     */           
/* 139 */           worldIn.setBlockState(pos, state.withProperty((IProperty)field_176466_a, Boolean.valueOf(false)).withProperty((IProperty)field_176465_b, Boolean.valueOf(false)), 2);
/* 140 */           worldIn.playAuxSFXAtEntity(null, 1006, pos, 0);
/*     */         }
/* 142 */         else if (var5 != ((Boolean)state.getValue((IProperty)field_176465_b)).booleanValue()) {
/*     */           
/* 144 */           worldIn.setBlockState(pos, state.withProperty((IProperty)field_176465_b, Boolean.valueOf(var5)), 2);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 160 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)EnumFacing.getHorizontal(meta)).withProperty((IProperty)field_176466_a, Boolean.valueOf(((meta & 0x4) != 0))).withProperty((IProperty)field_176465_b, Boolean.valueOf(((meta & 0x8) != 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 168 */     byte var2 = 0;
/* 169 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)AGE)).getHorizontalIndex();
/*     */     
/* 171 */     if (((Boolean)state.getValue((IProperty)field_176465_b)).booleanValue())
/*     */     {
/* 173 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 176 */     if (((Boolean)state.getValue((IProperty)field_176466_a)).booleanValue())
/*     */     {
/* 178 */       var3 |= 0x4;
/*     */     }
/*     */     
/* 181 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 186 */     return new BlockState(this, new IProperty[] { (IProperty)AGE, (IProperty)field_176466_a, (IProperty)field_176465_b, (IProperty)field_176467_M });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */