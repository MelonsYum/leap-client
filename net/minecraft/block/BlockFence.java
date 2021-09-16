/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemLead;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockFence
/*     */   extends Block
/*     */ {
/*  23 */   public static final PropertyBool NORTH = PropertyBool.create("north");
/*     */ 
/*     */   
/*  26 */   public static final PropertyBool EAST = PropertyBool.create("east");
/*     */ 
/*     */   
/*  29 */   public static final PropertyBool SOUTH = PropertyBool.create("south");
/*     */ 
/*     */   
/*  32 */   public static final PropertyBool WEST = PropertyBool.create("west");
/*     */   
/*     */   private static final String __OBFID = "CL_00000242";
/*     */   
/*     */   public BlockFence(Material p_i45721_1_) {
/*  37 */     super(p_i45721_1_);
/*  38 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)NORTH, Boolean.valueOf(false)).withProperty((IProperty)EAST, Boolean.valueOf(false)).withProperty((IProperty)SOUTH, Boolean.valueOf(false)).withProperty((IProperty)WEST, Boolean.valueOf(false)));
/*  39 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/*  49 */     boolean var7 = func_176524_e((IBlockAccess)worldIn, pos.offsetNorth());
/*  50 */     boolean var8 = func_176524_e((IBlockAccess)worldIn, pos.offsetSouth());
/*  51 */     boolean var9 = func_176524_e((IBlockAccess)worldIn, pos.offsetWest());
/*  52 */     boolean var10 = func_176524_e((IBlockAccess)worldIn, pos.offsetEast());
/*  53 */     float var11 = 0.375F;
/*  54 */     float var12 = 0.625F;
/*  55 */     float var13 = 0.375F;
/*  56 */     float var14 = 0.625F;
/*     */     
/*  58 */     if (var7)
/*     */     {
/*  60 */       var13 = 0.0F;
/*     */     }
/*     */     
/*  63 */     if (var8)
/*     */     {
/*  65 */       var14 = 1.0F;
/*     */     }
/*     */     
/*  68 */     if (var7 || var8) {
/*     */       
/*  70 */       setBlockBounds(var11, 0.0F, var13, var12, 1.5F, var14);
/*  71 */       super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */     } 
/*     */     
/*  74 */     var13 = 0.375F;
/*  75 */     var14 = 0.625F;
/*     */     
/*  77 */     if (var9)
/*     */     {
/*  79 */       var11 = 0.0F;
/*     */     }
/*     */     
/*  82 */     if (var10)
/*     */     {
/*  84 */       var12 = 1.0F;
/*     */     }
/*     */     
/*  87 */     if (var9 || var10 || (!var7 && !var8)) {
/*     */       
/*  89 */       setBlockBounds(var11, 0.0F, var13, var12, 1.5F, var14);
/*  90 */       super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */     } 
/*     */     
/*  93 */     if (var7)
/*     */     {
/*  95 */       var13 = 0.0F;
/*     */     }
/*     */     
/*  98 */     if (var8)
/*     */     {
/* 100 */       var14 = 1.0F;
/*     */     }
/*     */     
/* 103 */     setBlockBounds(var11, 0.0F, var13, var12, 1.0F, var14);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 108 */     boolean var3 = func_176524_e(access, pos.offsetNorth());
/* 109 */     boolean var4 = func_176524_e(access, pos.offsetSouth());
/* 110 */     boolean var5 = func_176524_e(access, pos.offsetWest());
/* 111 */     boolean var6 = func_176524_e(access, pos.offsetEast());
/* 112 */     float var7 = 0.375F;
/* 113 */     float var8 = 0.625F;
/* 114 */     float var9 = 0.375F;
/* 115 */     float var10 = 0.625F;
/*     */     
/* 117 */     if (var3)
/*     */     {
/* 119 */       var9 = 0.0F;
/*     */     }
/*     */     
/* 122 */     if (var4)
/*     */     {
/* 124 */       var10 = 1.0F;
/*     */     }
/*     */     
/* 127 */     if (var5)
/*     */     {
/* 129 */       var7 = 0.0F;
/*     */     }
/*     */     
/* 132 */     if (var6)
/*     */     {
/* 134 */       var8 = 1.0F;
/*     */     }
/*     */     
/* 137 */     setBlockBounds(var7, 0.0F, var9, var8, 1.0F, var10);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 142 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176524_e(IBlockAccess p_176524_1_, BlockPos p_176524_2_) {
/* 157 */     Block var3 = p_176524_1_.getBlockState(p_176524_2_).getBlock();
/* 158 */     return (var3 == Blocks.barrier) ? false : (((!(var3 instanceof BlockFence) || var3.blockMaterial != this.blockMaterial) && !(var3 instanceof BlockFenceGate)) ? ((var3.blockMaterial.isOpaque() && var3.isFullCube()) ? ((var3.blockMaterial != Material.gourd)) : false) : true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 168 */     return worldIn.isRemote ? true : ItemLead.func_180618_a(playerIn, worldIn, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 176 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 185 */     return state.withProperty((IProperty)NORTH, Boolean.valueOf(func_176524_e(worldIn, pos.offsetNorth()))).withProperty((IProperty)EAST, Boolean.valueOf(func_176524_e(worldIn, pos.offsetEast()))).withProperty((IProperty)SOUTH, Boolean.valueOf(func_176524_e(worldIn, pos.offsetSouth()))).withProperty((IProperty)WEST, Boolean.valueOf(func_176524_e(worldIn, pos.offsetWest())));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 190 */     return new BlockState(this, new IProperty[] { (IProperty)NORTH, (IProperty)EAST, (IProperty)WEST, (IProperty)SOUTH });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockFence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */