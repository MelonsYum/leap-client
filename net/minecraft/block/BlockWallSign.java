/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockWallSign extends BlockSign {
/*  14 */   public static final PropertyDirection field_176412_a = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*     */   
/*     */   private static final String __OBFID = "CL_00002047";
/*     */   
/*     */   public BlockWallSign() {
/*  19 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176412_a, (Comparable)EnumFacing.NORTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  24 */     EnumFacing var3 = (EnumFacing)access.getBlockState(pos).getValue((IProperty)field_176412_a);
/*  25 */     float var4 = 0.28125F;
/*  26 */     float var5 = 0.78125F;
/*  27 */     float var6 = 0.0F;
/*  28 */     float var7 = 1.0F;
/*  29 */     float var8 = 0.125F;
/*  30 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     
/*  32 */     switch (SwitchEnumFacing.field_177331_a[var3.ordinal()]) {
/*     */       
/*     */       case 1:
/*  35 */         setBlockBounds(var6, var4, 1.0F - var8, var7, var5, 1.0F);
/*     */         break;
/*     */       
/*     */       case 2:
/*  39 */         setBlockBounds(var6, var4, 0.0F, var7, var5, var8);
/*     */         break;
/*     */       
/*     */       case 3:
/*  43 */         setBlockBounds(1.0F - var8, var4, var6, 1.0F, var5, var7);
/*     */         break;
/*     */       
/*     */       case 4:
/*  47 */         setBlockBounds(0.0F, var4, var6, var8, var5, var7);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  53 */     EnumFacing var5 = (EnumFacing)state.getValue((IProperty)field_176412_a);
/*     */     
/*  55 */     if (!worldIn.getBlockState(pos.offset(var5.getOpposite())).getBlock().getMaterial().isSolid()) {
/*     */       
/*  57 */       dropBlockAsItem(worldIn, pos, state, 0);
/*  58 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */     
/*  61 */     super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  69 */     EnumFacing var2 = EnumFacing.getFront(meta);
/*     */     
/*  71 */     if (var2.getAxis() == EnumFacing.Axis.Y)
/*     */     {
/*  73 */       var2 = EnumFacing.NORTH;
/*     */     }
/*     */     
/*  76 */     return getDefaultState().withProperty((IProperty)field_176412_a, (Comparable)var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  84 */     return ((EnumFacing)state.getValue((IProperty)field_176412_a)).getIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  89 */     return new BlockState(this, new IProperty[] { (IProperty)field_176412_a });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/*  94 */     static final int[] field_177331_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002046";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 101 */         field_177331_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 103 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 110 */         field_177331_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 112 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 119 */         field_177331_a[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 121 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 128 */         field_177331_a[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 130 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockWallSign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */