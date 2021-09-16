/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class BlockRedstoneDiode
/*     */   extends BlockDirectional
/*     */ {
/*     */   protected final boolean isRepeaterPowered;
/*     */   private static final String __OBFID = "CL_00000226";
/*     */   
/*     */   protected BlockRedstoneDiode(boolean p_i45400_1_) {
/*  23 */     super(Material.circuits);
/*  24 */     this.isRepeaterPowered = p_i45400_1_;
/*  25 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  30 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  35 */     return World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) ? super.canPlaceBlockAt(worldIn, pos) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176409_d(World worldIn, BlockPos p_176409_2_) {
/*  40 */     return World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, p_176409_2_.offsetDown());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  50 */     if (!func_176405_b((IBlockAccess)worldIn, pos, state)) {
/*     */       
/*  52 */       boolean var5 = func_176404_e(worldIn, pos, state);
/*     */       
/*  54 */       if (this.isRepeaterPowered && !var5) {
/*     */         
/*  56 */         worldIn.setBlockState(pos, func_180675_k(state), 2);
/*     */       }
/*  58 */       else if (!this.isRepeaterPowered) {
/*     */         
/*  60 */         worldIn.setBlockState(pos, func_180674_e(state), 2);
/*     */         
/*  62 */         if (!var5)
/*     */         {
/*  64 */           worldIn.func_175654_a(pos, func_180674_e(state).getBlock(), func_176399_m(state), -1);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/*  72 */     return (side.getAxis() != EnumFacing.Axis.Y);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_176406_l(IBlockState p_176406_1_) {
/*  77 */     return this.isRepeaterPowered;
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/*  82 */     return isProvidingWeakPower(worldIn, pos, state, side);
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/*  87 */     return !func_176406_l(state) ? 0 : ((state.getValue((IProperty)AGE) == side) ? func_176408_a(worldIn, pos, state) : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  92 */     if (func_176409_d(worldIn, pos)) {
/*     */       
/*  94 */       func_176398_g(worldIn, pos, state);
/*     */     }
/*     */     else {
/*     */       
/*  98 */       dropBlockAsItem(worldIn, pos, state, 0);
/*  99 */       worldIn.setBlockToAir(pos);
/* 100 */       EnumFacing[] var5 = EnumFacing.values();
/* 101 */       int var6 = var5.length;
/*     */       
/* 103 */       for (int var7 = 0; var7 < var6; var7++) {
/*     */         
/* 105 */         EnumFacing var8 = var5[var7];
/* 106 */         worldIn.notifyNeighborsOfStateChange(pos.offset(var8), this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_176398_g(World worldIn, BlockPos p_176398_2_, IBlockState p_176398_3_) {
/* 113 */     if (!func_176405_b((IBlockAccess)worldIn, p_176398_2_, p_176398_3_)) {
/*     */       
/* 115 */       boolean var4 = func_176404_e(worldIn, p_176398_2_, p_176398_3_);
/*     */       
/* 117 */       if (((this.isRepeaterPowered && !var4) || (!this.isRepeaterPowered && var4)) && !worldIn.isBlockTickPending(p_176398_2_, this)) {
/*     */         
/* 119 */         byte var5 = -1;
/*     */         
/* 121 */         if (func_176402_i(worldIn, p_176398_2_, p_176398_3_)) {
/*     */           
/* 123 */           var5 = -3;
/*     */         }
/* 125 */         else if (this.isRepeaterPowered) {
/*     */           
/* 127 */           var5 = -2;
/*     */         } 
/*     */         
/* 130 */         worldIn.func_175654_a(p_176398_2_, this, func_176403_d(p_176398_3_), var5);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176405_b(IBlockAccess p_176405_1_, BlockPos p_176405_2_, IBlockState p_176405_3_) {
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_176404_e(World worldIn, BlockPos p_176404_2_, IBlockState p_176404_3_) {
/* 142 */     return (func_176397_f(worldIn, p_176404_2_, p_176404_3_) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176397_f(World worldIn, BlockPos p_176397_2_, IBlockState p_176397_3_) {
/* 147 */     EnumFacing var4 = (EnumFacing)p_176397_3_.getValue((IProperty)AGE);
/* 148 */     BlockPos var5 = p_176397_2_.offset(var4);
/* 149 */     int var6 = worldIn.getRedstonePower(var5, var4);
/*     */     
/* 151 */     if (var6 >= 15)
/*     */     {
/* 153 */       return var6;
/*     */     }
/*     */ 
/*     */     
/* 157 */     IBlockState var7 = worldIn.getBlockState(var5);
/* 158 */     return Math.max(var6, (var7.getBlock() == Blocks.redstone_wire) ? ((Integer)var7.getValue((IProperty)BlockRedstoneWire.POWER)).intValue() : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_176407_c(IBlockAccess p_176407_1_, BlockPos p_176407_2_, IBlockState p_176407_3_) {
/* 164 */     EnumFacing var4 = (EnumFacing)p_176407_3_.getValue((IProperty)AGE);
/* 165 */     EnumFacing var5 = var4.rotateY();
/* 166 */     EnumFacing var6 = var4.rotateYCCW();
/* 167 */     return Math.max(func_176401_c(p_176407_1_, p_176407_2_.offset(var5), var5), func_176401_c(p_176407_1_, p_176407_2_.offset(var6), var6));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176401_c(IBlockAccess p_176401_1_, BlockPos p_176401_2_, EnumFacing p_176401_3_) {
/* 172 */     IBlockState var4 = p_176401_1_.getBlockState(p_176401_2_);
/* 173 */     Block var5 = var4.getBlock();
/* 174 */     return func_149908_a(var5) ? ((var5 == Blocks.redstone_wire) ? ((Integer)var4.getValue((IProperty)BlockRedstoneWire.POWER)).intValue() : p_176401_1_.getStrongPower(p_176401_2_, p_176401_3_)) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 187 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)placer.func_174811_aO().getOpposite());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 192 */     if (func_176404_e(worldIn, pos, state))
/*     */     {
/* 194 */       worldIn.scheduleUpdate(pos, this, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 200 */     func_176400_h(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_176400_h(World worldIn, BlockPos p_176400_2_, IBlockState p_176400_3_) {
/* 205 */     EnumFacing var4 = (EnumFacing)p_176400_3_.getValue((IProperty)AGE);
/* 206 */     BlockPos var5 = p_176400_2_.offset(var4.getOpposite());
/* 207 */     worldIn.notifyBlockOfStateChange(var5, this);
/* 208 */     worldIn.notifyNeighborsOfStateExcept(var5, this, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
/* 216 */     if (this.isRepeaterPowered) {
/*     */       
/* 218 */       EnumFacing[] var4 = EnumFacing.values();
/* 219 */       int var5 = var4.length;
/*     */       
/* 221 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/* 223 */         EnumFacing var7 = var4[var6];
/* 224 */         worldIn.notifyNeighborsOfStateChange(pos.offset(var7), this);
/*     */       } 
/*     */     } 
/*     */     
/* 228 */     super.onBlockDestroyedByPlayer(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 233 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_149908_a(Block p_149908_1_) {
/* 238 */     return p_149908_1_.canProvidePower();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176408_a(IBlockAccess p_176408_1_, BlockPos p_176408_2_, IBlockState p_176408_3_) {
/* 243 */     return 15;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isRedstoneRepeaterBlockID(Block p_149909_0_) {
/* 248 */     return !(!Blocks.unpowered_repeater.func_149907_e(p_149909_0_) && !Blocks.unpowered_comparator.func_149907_e(p_149909_0_));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149907_e(Block p_149907_1_) {
/* 253 */     return !(p_149907_1_ != func_180674_e(getDefaultState()).getBlock() && p_149907_1_ != func_180675_k(getDefaultState()).getBlock());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176402_i(World worldIn, BlockPos p_176402_2_, IBlockState p_176402_3_) {
/* 258 */     EnumFacing var4 = ((EnumFacing)p_176402_3_.getValue((IProperty)AGE)).getOpposite();
/* 259 */     BlockPos var5 = p_176402_2_.offset(var4);
/* 260 */     return isRedstoneRepeaterBlockID(worldIn.getBlockState(var5).getBlock()) ? ((worldIn.getBlockState(var5).getValue((IProperty)AGE) != var4)) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176399_m(IBlockState p_176399_1_) {
/* 265 */     return func_176403_d(p_176399_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract int func_176403_d(IBlockState paramIBlockState);
/*     */   
/*     */   protected abstract IBlockState func_180674_e(IBlockState paramIBlockState);
/*     */   
/*     */   protected abstract IBlockState func_180675_k(IBlockState paramIBlockState);
/*     */   
/*     */   public boolean isAssociatedBlock(Block other) {
/* 276 */     return func_149907_e(other);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 281 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRedstoneDiode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */