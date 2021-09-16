/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class BlockBasePressurePlate
/*     */   extends Block
/*     */ {
/*     */   private static final String __OBFID = "CL_00000194";
/*     */   
/*     */   protected BlockBasePressurePlate(Material materialIn) {
/*  20 */     super(materialIn);
/*  21 */     setCreativeTab(CreativeTabs.tabRedstone);
/*  22 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  27 */     func_180668_d(access.getBlockState(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180668_d(IBlockState p_180668_1_) {
/*  32 */     boolean var2 = (getRedstoneStrength(p_180668_1_) > 0);
/*  33 */     float var3 = 0.0625F;
/*     */     
/*  35 */     if (var2) {
/*     */       
/*  37 */       setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.03125F, 0.9375F);
/*     */     }
/*     */     else {
/*     */       
/*  41 */       setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.0625F, 0.9375F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/*  50 */     return 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  55 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  75 */     return canBePlacedOn(worldIn, pos.offsetDown());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  80 */     if (!canBePlacedOn(worldIn, pos.offsetDown())) {
/*     */       
/*  82 */       dropBlockAsItem(worldIn, pos, state, 0);
/*  83 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean canBePlacedOn(World worldIn, BlockPos pos) {
/*  89 */     return !(!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos) && !(worldIn.getBlockState(pos).getBlock() instanceof BlockFence));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  99 */     if (!worldIn.isRemote) {
/*     */       
/* 101 */       int var5 = getRedstoneStrength(state);
/*     */       
/* 103 */       if (var5 > 0)
/*     */       {
/* 105 */         updateState(worldIn, pos, state, var5);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
/* 115 */     if (!worldIn.isRemote) {
/*     */       
/* 117 */       int var5 = getRedstoneStrength(state);
/*     */       
/* 119 */       if (var5 == 0)
/*     */       {
/* 121 */         updateState(worldIn, pos, state, var5);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateState(World worldIn, BlockPos pos, IBlockState state, int oldRedstoneStrength) {
/* 131 */     int var5 = computeRedstoneStrength(worldIn, pos);
/* 132 */     boolean var6 = (oldRedstoneStrength > 0);
/* 133 */     boolean var7 = (var5 > 0);
/*     */     
/* 135 */     if (oldRedstoneStrength != var5) {
/*     */       
/* 137 */       state = setRedstoneStrength(state, var5);
/* 138 */       worldIn.setBlockState(pos, state, 2);
/* 139 */       updateNeighbors(worldIn, pos);
/* 140 */       worldIn.markBlockRangeForRenderUpdate(pos, pos);
/*     */     } 
/*     */     
/* 143 */     if (!var7 && var6) {
/*     */       
/* 145 */       worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, "random.click", 0.3F, 0.5F);
/*     */     }
/* 147 */     else if (var7 && !var6) {
/*     */       
/* 149 */       worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.1D, pos.getZ() + 0.5D, "random.click", 0.3F, 0.6F);
/*     */     } 
/*     */     
/* 152 */     if (var7)
/*     */     {
/* 154 */       worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AxisAlignedBB getSensitiveAABB(BlockPos pos) {
/* 163 */     float var2 = 0.125F;
/* 164 */     return new AxisAlignedBB((pos.getX() + 0.125F), pos.getY(), (pos.getZ() + 0.125F), ((pos.getX() + 1) - 0.125F), pos.getY() + 0.25D, ((pos.getZ() + 1) - 0.125F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 169 */     if (getRedstoneStrength(state) > 0)
/*     */     {
/* 171 */       updateNeighbors(worldIn, pos);
/*     */     }
/*     */     
/* 174 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateNeighbors(World worldIn, BlockPos pos) {
/* 182 */     worldIn.notifyNeighborsOfStateChange(pos, this);
/* 183 */     worldIn.notifyNeighborsOfStateChange(pos.offsetDown(), this);
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 188 */     return getRedstoneStrength(state);
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 193 */     return (side == EnumFacing.UP) ? getRedstoneStrength(state) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 201 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/* 209 */     float var1 = 0.5F;
/* 210 */     float var2 = 0.125F;
/* 211 */     float var3 = 0.5F;
/* 212 */     setBlockBounds(0.0F, 0.375F, 0.0F, 1.0F, 0.625F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMobilityFlag() {
/* 217 */     return 1;
/*     */   }
/*     */   
/*     */   protected abstract int computeRedstoneStrength(World paramWorld, BlockPos paramBlockPos);
/*     */   
/*     */   protected abstract int getRedstoneStrength(IBlockState paramIBlockState);
/*     */   
/*     */   protected abstract IBlockState setRedstoneStrength(IBlockState paramIBlockState, int paramInt);
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockBasePressurePlate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */