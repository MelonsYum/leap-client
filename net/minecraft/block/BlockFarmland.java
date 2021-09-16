/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class BlockFarmland
/*     */   extends Block
/*     */ {
/*  21 */   public static final PropertyInteger field_176531_a = PropertyInteger.create("moisture", 0, 7);
/*     */   
/*     */   private static final String __OBFID = "CL_00000241";
/*     */   
/*     */   protected BlockFarmland() {
/*  26 */     super(Material.ground);
/*  27 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176531_a, Integer.valueOf(0)));
/*  28 */     setTickRandomly(true);
/*  29 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
/*  30 */     setLightOpacity(255);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  35 */     return new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), (pos.getX() + 1), (pos.getY() + 1), (pos.getZ() + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  40 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  50 */     int var5 = ((Integer)state.getValue((IProperty)field_176531_a)).intValue();
/*     */     
/*  52 */     if (!func_176530_e(worldIn, pos) && !worldIn.func_175727_C(pos.offsetUp())) {
/*     */       
/*  54 */       if (var5 > 0)
/*     */       {
/*  56 */         worldIn.setBlockState(pos, state.withProperty((IProperty)field_176531_a, Integer.valueOf(var5 - 1)), 2);
/*     */       }
/*  58 */       else if (!func_176529_d(worldIn, pos))
/*     */       {
/*  60 */         worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
/*     */       }
/*     */     
/*  63 */     } else if (var5 < 7) {
/*     */       
/*  65 */       worldIn.setBlockState(pos, state.withProperty((IProperty)field_176531_a, Integer.valueOf(7)), 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onFallenUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
/*  76 */     if (entityIn instanceof net.minecraft.entity.EntityLivingBase) {
/*     */       
/*  78 */       if (!worldIn.isRemote && worldIn.rand.nextFloat() < fallDistance - 0.5F) {
/*     */         
/*  80 */         if (!(entityIn instanceof net.minecraft.entity.player.EntityPlayer) && !worldIn.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  85 */         worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
/*     */       } 
/*     */       
/*  88 */       super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176529_d(World worldIn, BlockPos p_176529_2_) {
/*  94 */     Block var3 = worldIn.getBlockState(p_176529_2_.offsetUp()).getBlock();
/*  95 */     return !(!(var3 instanceof BlockCrops) && !(var3 instanceof BlockStem));
/*     */   }
/*     */   
/*     */   private boolean func_176530_e(World worldIn, BlockPos p_176530_2_) {
/*     */     BlockPos.MutableBlockPos var4;
/* 100 */     Iterator<BlockPos.MutableBlockPos> var3 = BlockPos.getAllInBoxMutable(p_176530_2_.add(-4, 0, -4), p_176530_2_.add(4, 1, 4)).iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 105 */       if (!var3.hasNext())
/*     */       {
/* 107 */         return false;
/*     */       }
/*     */       
/* 110 */       var4 = var3.next();
/*     */     }
/* 112 */     while (worldIn.getBlockState((BlockPos)var4).getBlock().getMaterial() != Material.water);
/*     */     
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 119 */     super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
/*     */     
/* 121 */     if (worldIn.getBlockState(pos.offsetUp()).getBlock().getMaterial().isSolid())
/*     */     {
/* 123 */       worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 134 */     return Blocks.dirt.getItemDropped(Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, fortune);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 139 */     return Item.getItemFromBlock(Blocks.dirt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 147 */     return getDefaultState().withProperty((IProperty)field_176531_a, Integer.valueOf(meta & 0x7));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 155 */     return ((Integer)state.getValue((IProperty)field_176531_a)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 160 */     return new BlockState(this, new IProperty[] { (IProperty)field_176531_a });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockFarmland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */