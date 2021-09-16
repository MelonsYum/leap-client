/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityMinecartCommandBlock;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockRailDetector
/*     */   extends BlockRailBase {
/*  25 */   public static final PropertyEnum field_176573_b = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002126";
/*     */         
/*     */         public boolean func_180344_a(BlockRailBase.EnumRailDirection p_180344_1_) {
/*  30 */           return (p_180344_1_ != BlockRailBase.EnumRailDirection.NORTH_EAST && p_180344_1_ != BlockRailBase.EnumRailDirection.NORTH_WEST && p_180344_1_ != BlockRailBase.EnumRailDirection.SOUTH_EAST && p_180344_1_ != BlockRailBase.EnumRailDirection.SOUTH_WEST);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  34 */           return func_180344_a((BlockRailBase.EnumRailDirection)p_apply_1_);
/*     */         }
/*     */       });
/*  37 */   public static final PropertyBool field_176574_M = PropertyBool.create("powered");
/*     */   
/*     */   private static final String __OBFID = "CL_00000225";
/*     */   
/*     */   public BlockRailDetector() {
/*  42 */     super(true);
/*  43 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176574_M, Boolean.valueOf(false)).withProperty((IProperty)field_176573_b, BlockRailBase.EnumRailDirection.NORTH_SOUTH));
/*  44 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/*  52 */     return 20;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
/*  68 */     if (!worldIn.isRemote)
/*     */     {
/*  70 */       if (!((Boolean)state.getValue((IProperty)field_176574_M)).booleanValue())
/*     */       {
/*  72 */         func_176570_e(worldIn, pos, state);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  84 */     if (!worldIn.isRemote && ((Boolean)state.getValue((IProperty)field_176574_M)).booleanValue())
/*     */     {
/*  86 */       func_176570_e(worldIn, pos, state);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/*  92 */     return ((Boolean)state.getValue((IProperty)field_176574_M)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/*  97 */     return !((Boolean)state.getValue((IProperty)field_176574_M)).booleanValue() ? 0 : ((side == EnumFacing.UP) ? 15 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176570_e(World worldIn, BlockPos p_176570_2_, IBlockState p_176570_3_) {
/* 102 */     boolean var4 = ((Boolean)p_176570_3_.getValue((IProperty)field_176574_M)).booleanValue();
/* 103 */     boolean var5 = false;
/* 104 */     List var6 = func_176571_a(worldIn, p_176570_2_, EntityMinecart.class, new Predicate[0]);
/*     */     
/* 106 */     if (!var6.isEmpty())
/*     */     {
/* 108 */       var5 = true;
/*     */     }
/*     */     
/* 111 */     if (var5 && !var4) {
/*     */       
/* 113 */       worldIn.setBlockState(p_176570_2_, p_176570_3_.withProperty((IProperty)field_176574_M, Boolean.valueOf(true)), 3);
/* 114 */       worldIn.notifyNeighborsOfStateChange(p_176570_2_, this);
/* 115 */       worldIn.notifyNeighborsOfStateChange(p_176570_2_.offsetDown(), this);
/* 116 */       worldIn.markBlockRangeForRenderUpdate(p_176570_2_, p_176570_2_);
/*     */     } 
/*     */     
/* 119 */     if (!var5 && var4) {
/*     */       
/* 121 */       worldIn.setBlockState(p_176570_2_, p_176570_3_.withProperty((IProperty)field_176574_M, Boolean.valueOf(false)), 3);
/* 122 */       worldIn.notifyNeighborsOfStateChange(p_176570_2_, this);
/* 123 */       worldIn.notifyNeighborsOfStateChange(p_176570_2_.offsetDown(), this);
/* 124 */       worldIn.markBlockRangeForRenderUpdate(p_176570_2_, p_176570_2_);
/*     */     } 
/*     */     
/* 127 */     if (var5)
/*     */     {
/* 129 */       worldIn.scheduleUpdate(p_176570_2_, this, tickRate(worldIn));
/*     */     }
/*     */     
/* 132 */     worldIn.updateComparatorOutputLevel(p_176570_2_, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 137 */     super.onBlockAdded(worldIn, pos, state);
/* 138 */     func_176570_e(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public IProperty func_176560_l() {
/* 143 */     return (IProperty)field_176573_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 153 */     if (((Boolean)worldIn.getBlockState(pos).getValue((IProperty)field_176574_M)).booleanValue()) {
/*     */       
/* 155 */       List<EntityMinecartCommandBlock> var3 = func_176571_a(worldIn, pos, EntityMinecartCommandBlock.class, new Predicate[0]);
/*     */       
/* 157 */       if (!var3.isEmpty())
/*     */       {
/* 159 */         return ((EntityMinecartCommandBlock)var3.get(0)).func_145822_e().getSuccessCount();
/*     */       }
/*     */       
/* 162 */       List<IInventory> var4 = func_176571_a(worldIn, pos, EntityMinecart.class, new Predicate[] { IEntitySelector.selectInventories });
/*     */       
/* 164 */       if (!var4.isEmpty())
/*     */       {
/* 166 */         return Container.calcRedstoneFromInventory(var4.get(0));
/*     */       }
/*     */     } 
/*     */     
/* 170 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected List func_176571_a(World worldIn, BlockPos p_176571_2_, Class p_176571_3_, Predicate... p_176571_4_) {
/* 175 */     AxisAlignedBB var5 = func_176572_a(p_176571_2_);
/* 176 */     return (p_176571_4_.length != 1) ? worldIn.getEntitiesWithinAABB(p_176571_3_, var5) : worldIn.func_175647_a(p_176571_3_, var5, p_176571_4_[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   private AxisAlignedBB func_176572_a(BlockPos p_176572_1_) {
/* 181 */     float var2 = 0.2F;
/* 182 */     return new AxisAlignedBB((p_176572_1_.getX() + 0.2F), p_176572_1_.getY(), (p_176572_1_.getZ() + 0.2F), ((p_176572_1_.getX() + 1) - 0.2F), ((p_176572_1_.getY() + 1) - 0.2F), ((p_176572_1_.getZ() + 1) - 0.2F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 190 */     return getDefaultState().withProperty((IProperty)field_176573_b, BlockRailBase.EnumRailDirection.func_177016_a(meta & 0x7)).withProperty((IProperty)field_176574_M, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 198 */     byte var2 = 0;
/* 199 */     int var3 = var2 | ((BlockRailBase.EnumRailDirection)state.getValue((IProperty)field_176573_b)).func_177015_a();
/*     */     
/* 201 */     if (((Boolean)state.getValue((IProperty)field_176574_M)).booleanValue())
/*     */     {
/* 203 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 206 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 211 */     return new BlockState(this, new IProperty[] { (IProperty)field_176573_b, (IProperty)field_176574_M });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRailDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */