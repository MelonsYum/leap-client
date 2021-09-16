/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockLadder extends Block {
/*  20 */   public static final PropertyDirection field_176382_a = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*     */   
/*     */   private static final String __OBFID = "CL_00000262";
/*     */   
/*     */   protected BlockLadder() {
/*  25 */     super(Material.circuits);
/*  26 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176382_a, (Comparable)EnumFacing.NORTH));
/*  27 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  32 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  33 */     return super.getCollisionBoundingBox(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/*  38 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  39 */     return super.getSelectedBoundingBox(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  44 */     IBlockState var3 = access.getBlockState(pos);
/*     */     
/*  46 */     if (var3.getBlock() == this) {
/*     */       
/*  48 */       float var4 = 0.125F;
/*     */       
/*  50 */       switch (SwitchEnumFacing.field_180190_a[((EnumFacing)var3.getValue((IProperty)field_176382_a)).ordinal()]) {
/*     */         
/*     */         case 1:
/*  53 */           setBlockBounds(0.0F, 0.0F, 1.0F - var4, 1.0F, 1.0F, 1.0F);
/*     */           return;
/*     */         
/*     */         case 2:
/*  57 */           setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var4);
/*     */           return;
/*     */         
/*     */         case 3:
/*  61 */           setBlockBounds(1.0F - var4, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */           return;
/*     */       } 
/*     */ 
/*     */       
/*  66 */       setBlockBounds(0.0F, 0.0F, 0.0F, var4, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  83 */     return worldIn.getBlockState(pos.offsetWest()).getBlock().isNormalCube() ? true : (worldIn.getBlockState(pos.offsetEast()).getBlock().isNormalCube() ? true : (worldIn.getBlockState(pos.offsetNorth()).getBlock().isNormalCube() ? true : worldIn.getBlockState(pos.offsetSouth()).getBlock().isNormalCube()));
/*     */   }
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*     */     EnumFacing var10;
/*  88 */     if (facing.getAxis().isHorizontal() && func_176381_b(worldIn, pos, facing))
/*     */     {
/*  90 */       return getDefaultState().withProperty((IProperty)field_176382_a, (Comparable)facing);
/*     */     }
/*     */ 
/*     */     
/*  94 */     Iterator<EnumFacing> var9 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  99 */       if (!var9.hasNext())
/*     */       {
/* 101 */         return getDefaultState();
/*     */       }
/*     */       
/* 104 */       var10 = var9.next();
/*     */     }
/* 106 */     while (!func_176381_b(worldIn, pos, var10));
/*     */     
/* 108 */     return getDefaultState().withProperty((IProperty)field_176382_a, (Comparable)var10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 114 */     EnumFacing var5 = (EnumFacing)state.getValue((IProperty)field_176382_a);
/*     */     
/* 116 */     if (!func_176381_b(worldIn, pos, var5)) {
/*     */       
/* 118 */       dropBlockAsItem(worldIn, pos, state, 0);
/* 119 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */     
/* 122 */     super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_176381_b(World worldIn, BlockPos p_176381_2_, EnumFacing p_176381_3_) {
/* 127 */     return worldIn.getBlockState(p_176381_2_.offset(p_176381_3_.getOpposite())).getBlock().isNormalCube();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 132 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 140 */     EnumFacing var2 = EnumFacing.getFront(meta);
/*     */     
/* 142 */     if (var2.getAxis() == EnumFacing.Axis.Y)
/*     */     {
/* 144 */       var2 = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 147 */     return getDefaultState().withProperty((IProperty)field_176382_a, (Comparable)var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 155 */     return ((EnumFacing)state.getValue((IProperty)field_176382_a)).getIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 160 */     return new BlockState(this, new IProperty[] { (IProperty)field_176382_a });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 165 */     static final int[] field_180190_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002104";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 172 */         field_180190_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 174 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 181 */         field_180190_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 183 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 190 */         field_180190_a[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 192 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 199 */         field_180190_a[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 201 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockLadder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */