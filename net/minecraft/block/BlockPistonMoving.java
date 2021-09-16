/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityPiston;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPistonMoving
/*     */   extends BlockContainer {
/*  25 */   public static final PropertyDirection field_176426_a = BlockPistonExtension.field_176326_a;
/*  26 */   public static final PropertyEnum field_176425_b = BlockPistonExtension.field_176325_b;
/*     */   
/*     */   private static final String __OBFID = "CL_00000368";
/*     */   
/*     */   public BlockPistonMoving() {
/*  31 */     super(Material.piston);
/*  32 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176426_a, (Comparable)EnumFacing.NORTH).withProperty((IProperty)field_176425_b, BlockPistonExtension.EnumPistonType.DEFAULT));
/*  33 */     setHardness(-1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/*  41 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static TileEntity func_176423_a(IBlockState p_176423_0_, EnumFacing p_176423_1_, boolean p_176423_2_, boolean p_176423_3_) {
/*  46 */     return (TileEntity)new TileEntityPiston(p_176423_0_, p_176423_1_, p_176423_2_, p_176423_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/*  51 */     TileEntity var4 = worldIn.getTileEntity(pos);
/*     */     
/*  53 */     if (var4 instanceof TileEntityPiston) {
/*     */       
/*  55 */       ((TileEntityPiston)var4).clearPistonTileEntity();
/*     */     }
/*     */     else {
/*     */       
/*  59 */       super.breakBlock(worldIn, pos, state);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
/*  81 */     BlockPos var4 = pos.offset(((EnumFacing)state.getValue((IProperty)field_176426_a)).getOpposite());
/*  82 */     IBlockState var5 = worldIn.getBlockState(var4);
/*     */     
/*  84 */     if (var5.getBlock() instanceof BlockPistonBase && ((Boolean)var5.getValue((IProperty)BlockPistonBase.EXTENDED)).booleanValue())
/*     */     {
/*  86 */       worldIn.setBlockToAir(var4);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  92 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  97 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 102 */     if (!worldIn.isRemote && worldIn.getTileEntity(pos) == null) {
/*     */       
/* 104 */       worldIn.setBlockToAir(pos);
/* 105 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/* 131 */     if (!worldIn.isRemote) {
/*     */       
/* 133 */       TileEntityPiston var6 = func_176422_e((IBlockAccess)worldIn, pos);
/*     */       
/* 135 */       if (var6 != null) {
/*     */         
/* 137 */         IBlockState var7 = var6.func_174927_b();
/* 138 */         var7.getBlock().dropBlockAsItem(worldIn, pos, var7, 0);
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
/*     */   
/*     */   public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
/* 151 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 156 */     if (!worldIn.isRemote)
/*     */     {
/* 158 */       worldIn.getTileEntity(pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 164 */     TileEntityPiston var4 = func_176422_e((IBlockAccess)worldIn, pos);
/*     */     
/* 166 */     if (var4 == null)
/*     */     {
/* 168 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 172 */     float var5 = var4.func_145860_a(0.0F);
/*     */     
/* 174 */     if (var4.isExtending())
/*     */     {
/* 176 */       var5 = 1.0F - var5;
/*     */     }
/*     */     
/* 179 */     return func_176424_a(worldIn, pos, var4.func_174927_b(), var5, var4.func_174930_e());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 185 */     TileEntityPiston var3 = func_176422_e(access, pos);
/*     */     
/* 187 */     if (var3 != null) {
/*     */       
/* 189 */       IBlockState var4 = var3.func_174927_b();
/* 190 */       Block var5 = var4.getBlock();
/*     */       
/* 192 */       if (var5 == this || var5.getMaterial() == Material.air) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 197 */       float var6 = var3.func_145860_a(0.0F);
/*     */       
/* 199 */       if (var3.isExtending())
/*     */       {
/* 201 */         var6 = 1.0F - var6;
/*     */       }
/*     */       
/* 204 */       var5.setBlockBoundsBasedOnState(access, pos);
/*     */       
/* 206 */       if (var5 == Blocks.piston || var5 == Blocks.sticky_piston)
/*     */       {
/* 208 */         var6 = 0.0F;
/*     */       }
/*     */       
/* 211 */       EnumFacing var7 = var3.func_174930_e();
/* 212 */       this.minX = var5.getBlockBoundsMinX() - (var7.getFrontOffsetX() * var6);
/* 213 */       this.minY = var5.getBlockBoundsMinY() - (var7.getFrontOffsetY() * var6);
/* 214 */       this.minZ = var5.getBlockBoundsMinZ() - (var7.getFrontOffsetZ() * var6);
/* 215 */       this.maxX = var5.getBlockBoundsMaxX() - (var7.getFrontOffsetX() * var6);
/* 216 */       this.maxY = var5.getBlockBoundsMaxY() - (var7.getFrontOffsetY() * var6);
/* 217 */       this.maxZ = var5.getBlockBoundsMaxZ() - (var7.getFrontOffsetZ() * var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_176424_a(World worldIn, BlockPos p_176424_2_, IBlockState p_176424_3_, float p_176424_4_, EnumFacing p_176424_5_) {
/* 223 */     if (p_176424_3_.getBlock() != this && p_176424_3_.getBlock().getMaterial() != Material.air) {
/*     */       
/* 225 */       AxisAlignedBB var6 = p_176424_3_.getBlock().getCollisionBoundingBox(worldIn, p_176424_2_, p_176424_3_);
/*     */       
/* 227 */       if (var6 == null)
/*     */       {
/* 229 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 233 */       double var7 = var6.minX;
/* 234 */       double var9 = var6.minY;
/* 235 */       double var11 = var6.minZ;
/* 236 */       double var13 = var6.maxX;
/* 237 */       double var15 = var6.maxY;
/* 238 */       double var17 = var6.maxZ;
/*     */       
/* 240 */       if (p_176424_5_.getFrontOffsetX() < 0) {
/*     */         
/* 242 */         var7 -= (p_176424_5_.getFrontOffsetX() * p_176424_4_);
/*     */       }
/*     */       else {
/*     */         
/* 246 */         var13 -= (p_176424_5_.getFrontOffsetX() * p_176424_4_);
/*     */       } 
/*     */       
/* 249 */       if (p_176424_5_.getFrontOffsetY() < 0) {
/*     */         
/* 251 */         var9 -= (p_176424_5_.getFrontOffsetY() * p_176424_4_);
/*     */       }
/*     */       else {
/*     */         
/* 255 */         var15 -= (p_176424_5_.getFrontOffsetY() * p_176424_4_);
/*     */       } 
/*     */       
/* 258 */       if (p_176424_5_.getFrontOffsetZ() < 0) {
/*     */         
/* 260 */         var11 -= (p_176424_5_.getFrontOffsetZ() * p_176424_4_);
/*     */       }
/*     */       else {
/*     */         
/* 264 */         var17 -= (p_176424_5_.getFrontOffsetZ() * p_176424_4_);
/*     */       } 
/*     */       
/* 267 */       return new AxisAlignedBB(var7, var9, var11, var13, var15, var17);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 272 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private TileEntityPiston func_176422_e(IBlockAccess p_176422_1_, BlockPos p_176422_2_) {
/* 278 */     TileEntity var3 = p_176422_1_.getTileEntity(p_176422_2_);
/* 279 */     return (var3 instanceof TileEntityPiston) ? (TileEntityPiston)var3 : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 284 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 292 */     return getDefaultState().withProperty((IProperty)field_176426_a, (Comparable)BlockPistonExtension.func_176322_b(meta)).withProperty((IProperty)field_176425_b, ((meta & 0x8) > 0) ? BlockPistonExtension.EnumPistonType.STICKY : BlockPistonExtension.EnumPistonType.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 300 */     byte var2 = 0;
/* 301 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)field_176426_a)).getIndex();
/*     */     
/* 303 */     if (state.getValue((IProperty)field_176425_b) == BlockPistonExtension.EnumPistonType.STICKY)
/*     */     {
/* 305 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 308 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 313 */     return new BlockState(this, new IProperty[] { (IProperty)field_176426_a, (IProperty)field_176425_b });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPistonMoving.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */