/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockCactus
/*     */   extends Block {
/*  22 */   public static final PropertyInteger AGE_PROP = PropertyInteger.create("age", 0, 15);
/*     */   
/*     */   private static final String __OBFID = "CL_00000210";
/*     */   
/*     */   protected BlockCactus() {
/*  27 */     super(Material.cactus);
/*  28 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)AGE_PROP, Integer.valueOf(0)));
/*  29 */     setTickRandomly(true);
/*  30 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  35 */     BlockPos var5 = pos.offsetUp();
/*     */     
/*  37 */     if (worldIn.isAirBlock(var5)) {
/*     */       int var6;
/*     */ 
/*     */       
/*  41 */       for (var6 = 1; worldIn.getBlockState(pos.offsetDown(var6)).getBlock() == this; var6++);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  46 */       if (var6 < 3) {
/*     */         
/*  48 */         int var7 = ((Integer)state.getValue((IProperty)AGE_PROP)).intValue();
/*     */         
/*  50 */         if (var7 == 15) {
/*     */           
/*  52 */           worldIn.setBlockState(var5, getDefaultState());
/*  53 */           IBlockState var8 = state.withProperty((IProperty)AGE_PROP, Integer.valueOf(0));
/*  54 */           worldIn.setBlockState(pos, var8, 4);
/*  55 */           onNeighborBlockChange(worldIn, var5, var8, this);
/*     */         }
/*     */         else {
/*     */           
/*  59 */           worldIn.setBlockState(pos, state.withProperty((IProperty)AGE_PROP, Integer.valueOf(var7 + 1)), 4);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  67 */     float var4 = 0.0625F;
/*  68 */     return new AxisAlignedBB((pos.getX() + var4), pos.getY(), (pos.getZ() + var4), ((pos.getX() + 1) - var4), ((pos.getY() + 1) - var4), ((pos.getZ() + 1) - var4));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/*  73 */     float var3 = 0.0625F;
/*  74 */     return new AxisAlignedBB((pos.getX() + var3), pos.getY(), (pos.getZ() + var3), ((pos.getX() + 1) - var3), (pos.getY() + 1), ((pos.getZ() + 1) - var3));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  89 */     return super.canPlaceBlockAt(worldIn, pos) ? canBlockStay(worldIn, pos) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  94 */     if (!canBlockStay(worldIn, pos))
/*     */     {
/*  96 */       worldIn.destroyBlock(pos, true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlockStay(World worldIn, BlockPos p_176586_2_) {
/* 102 */     Iterator<EnumFacing> var3 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/* 104 */     while (var3.hasNext()) {
/*     */       
/* 106 */       EnumFacing var4 = var3.next();
/*     */       
/* 108 */       if (worldIn.getBlockState(p_176586_2_.offset(var4)).getBlock().getMaterial().isSolid())
/*     */       {
/* 110 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 114 */     Block var5 = worldIn.getBlockState(p_176586_2_.offsetDown()).getBlock();
/* 115 */     return !(var5 != Blocks.cactus && var5 != Blocks.sand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
/* 123 */     entityIn.attackEntityFrom(DamageSource.cactus, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 128 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 136 */     return getDefaultState().withProperty((IProperty)AGE_PROP, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 144 */     return ((Integer)state.getValue((IProperty)AGE_PROP)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 149 */     return new BlockState(this, new IProperty[] { (IProperty)AGE_PROP });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockCactus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */