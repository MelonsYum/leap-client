/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockEndPortalFrame extends Block {
/*  21 */   public static final PropertyDirection field_176508_a = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*  22 */   public static final PropertyBool field_176507_b = PropertyBool.create("eye");
/*     */   
/*     */   private static final String __OBFID = "CL_00000237";
/*     */   
/*     */   public BlockEndPortalFrame() {
/*  27 */     super(Material.rock);
/*  28 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176508_a, (Comparable)EnumFacing.NORTH).withProperty((IProperty)field_176507_b, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  33 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  41 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/*  51 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
/*  52 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */     
/*  54 */     if (((Boolean)worldIn.getBlockState(pos).getValue((IProperty)field_176507_b)).booleanValue()) {
/*     */       
/*  56 */       setBlockBounds(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
/*  57 */       super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */     } 
/*     */     
/*  60 */     setBlockBoundsForItemRender();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  70 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  75 */     return getDefaultState().withProperty((IProperty)field_176508_a, (Comparable)placer.func_174811_aO().getOpposite()).withProperty((IProperty)field_176507_b, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/*  85 */     return ((Boolean)worldIn.getBlockState(pos).getValue((IProperty)field_176507_b)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  93 */     return getDefaultState().withProperty((IProperty)field_176507_b, Boolean.valueOf(((meta & 0x4) != 0))).withProperty((IProperty)field_176508_a, (Comparable)EnumFacing.getHorizontal(meta & 0x3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 101 */     byte var2 = 0;
/* 102 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)field_176508_a)).getHorizontalIndex();
/*     */     
/* 104 */     if (((Boolean)state.getValue((IProperty)field_176507_b)).booleanValue())
/*     */     {
/* 106 */       var3 |= 0x4;
/*     */     }
/*     */     
/* 109 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 114 */     return new BlockState(this, new IProperty[] { (IProperty)field_176508_a, (IProperty)field_176507_b });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockEndPortalFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */