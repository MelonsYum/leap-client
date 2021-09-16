/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.BlockWorldState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.block.state.pattern.BlockPattern;
/*     */ import net.minecraft.block.state.pattern.BlockStateHelper;
/*     */ import net.minecraft.block.state.pattern.FactoryBlockPattern;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityIronGolem;
/*     */ import net.minecraft.entity.monster.EntitySnowman;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPumpkin
/*     */   extends BlockDirectional
/*     */ {
/*     */   private BlockPattern field_176394_a;
/*     */   private BlockPattern field_176393_b;
/*     */   
/*     */   protected BlockPumpkin() {
/*  31 */     super(Material.gourd);
/*  32 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)AGE, (Comparable)EnumFacing.NORTH));
/*  33 */     setTickRandomly(true);
/*  34 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */   private BlockPattern field_176395_M; private BlockPattern field_176396_O; private static final String __OBFID = "CL_00000291";
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  39 */     super.onBlockAdded(worldIn, pos, state);
/*  40 */     createGolem(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176390_d(World worldIn, BlockPos p_176390_2_) {
/*  45 */     return !(func_176392_j().func_177681_a(worldIn, p_176390_2_) == null && func_176389_S().func_177681_a(worldIn, p_176390_2_) == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createGolem(World worldIn, BlockPos p_180673_2_) {
/*     */     BlockPattern.PatternHelper var3;
/*  54 */     if ((var3 = func_176391_l().func_177681_a(worldIn, p_180673_2_)) != null) {
/*     */       
/*  56 */       for (int var4 = 0; var4 < func_176391_l().func_177685_b(); var4++) {
/*     */         
/*  58 */         BlockWorldState var5 = var3.func_177670_a(0, var4, 0);
/*  59 */         worldIn.setBlockState(var5.getPos(), Blocks.air.getDefaultState(), 2);
/*     */       } 
/*     */       
/*  62 */       EntitySnowman var9 = new EntitySnowman(worldIn);
/*  63 */       BlockPos var11 = var3.func_177670_a(0, 2, 0).getPos();
/*  64 */       var9.setLocationAndAngles(var11.getX() + 0.5D, var11.getY() + 0.05D, var11.getZ() + 0.5D, 0.0F, 0.0F);
/*  65 */       worldIn.spawnEntityInWorld((Entity)var9);
/*     */       int var6;
/*  67 */       for (var6 = 0; var6 < 120; var6++)
/*     */       {
/*  69 */         worldIn.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, var11.getX() + worldIn.rand.nextDouble(), var11.getY() + worldIn.rand.nextDouble() * 2.5D, var11.getZ() + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */       
/*  72 */       for (var6 = 0; var6 < func_176391_l().func_177685_b(); var6++)
/*     */       {
/*  74 */         BlockWorldState var7 = var3.func_177670_a(0, var6, 0);
/*  75 */         worldIn.func_175722_b(var7.getPos(), Blocks.air);
/*     */       }
/*     */     
/*  78 */     } else if ((var3 = func_176388_T().func_177681_a(worldIn, p_180673_2_)) != null) {
/*     */       
/*  80 */       for (int var4 = 0; var4 < func_176388_T().func_177684_c(); var4++) {
/*     */         
/*  82 */         for (int var12 = 0; var12 < func_176388_T().func_177685_b(); var12++)
/*     */         {
/*  84 */           worldIn.setBlockState(var3.func_177670_a(var4, var12, 0).getPos(), Blocks.air.getDefaultState(), 2);
/*     */         }
/*     */       } 
/*     */       
/*  88 */       BlockPos var10 = var3.func_177670_a(1, 2, 0).getPos();
/*  89 */       EntityIronGolem var13 = new EntityIronGolem(worldIn);
/*  90 */       var13.setPlayerCreated(true);
/*  91 */       var13.setLocationAndAngles(var10.getX() + 0.5D, var10.getY() + 0.05D, var10.getZ() + 0.5D, 0.0F, 0.0F);
/*  92 */       worldIn.spawnEntityInWorld((Entity)var13);
/*     */       int var6;
/*  94 */       for (var6 = 0; var6 < 120; var6++)
/*     */       {
/*  96 */         worldIn.spawnParticle(EnumParticleTypes.SNOWBALL, var10.getX() + worldIn.rand.nextDouble(), var10.getY() + worldIn.rand.nextDouble() * 3.9D, var10.getZ() + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */       }
/*     */       
/*  99 */       for (var6 = 0; var6 < func_176388_T().func_177684_c(); var6++) {
/*     */         
/* 101 */         for (int var14 = 0; var14 < func_176388_T().func_177685_b(); var14++) {
/*     */           
/* 103 */           BlockWorldState var8 = var3.func_177670_a(var6, var14, 0);
/* 104 */           worldIn.func_175722_b(var8.getPos(), Blocks.air);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/* 112 */     return ((worldIn.getBlockState(pos).getBlock()).blockMaterial.isReplaceable() && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 117 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)placer.func_174811_aO().getOpposite());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 125 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)EnumFacing.getHorizontal(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 133 */     return ((EnumFacing)state.getValue((IProperty)AGE)).getHorizontalIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 138 */     return new BlockState(this, new IProperty[] { (IProperty)AGE });
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockPattern func_176392_j() {
/* 143 */     if (this.field_176394_a == null)
/*     */     {
/* 145 */       this.field_176394_a = FactoryBlockPattern.start().aisle(new String[] { " ", "#", "#" }).where('#', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.snow))).build();
/*     */     }
/*     */     
/* 148 */     return this.field_176394_a;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockPattern func_176391_l() {
/* 153 */     if (this.field_176393_b == null)
/*     */     {
/* 155 */       this.field_176393_b = FactoryBlockPattern.start().aisle(new String[] { "^", "#", "#" }).where('^', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.pumpkin))).where('#', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.snow))).build();
/*     */     }
/*     */     
/* 158 */     return this.field_176393_b;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockPattern func_176389_S() {
/* 163 */     if (this.field_176395_M == null)
/*     */     {
/* 165 */       this.field_176395_M = FactoryBlockPattern.start().aisle(new String[] { "~ ~", "###", "~#~" }).where('#', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.iron_block))).where('~', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.air))).build();
/*     */     }
/*     */     
/* 168 */     return this.field_176395_M;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockPattern func_176388_T() {
/* 173 */     if (this.field_176396_O == null)
/*     */     {
/* 175 */       this.field_176396_O = FactoryBlockPattern.start().aisle(new String[] { "~^~", "###", "~#~" }).where('^', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.pumpkin))).where('#', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.iron_block))).where('~', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.air))).build();
/*     */     }
/*     */     
/* 178 */     return this.field_176396_O;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPumpkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */