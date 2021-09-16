/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPistonExtension
/*     */   extends Block {
/*  25 */   public static final PropertyDirection field_176326_a = PropertyDirection.create("facing");
/*  26 */   public static final PropertyEnum field_176325_b = PropertyEnum.create("type", EnumPistonType.class);
/*  27 */   public static final PropertyBool field_176327_M = PropertyBool.create("short");
/*     */   
/*     */   private static final String __OBFID = "CL_00000367";
/*     */   
/*     */   public BlockPistonExtension() {
/*  32 */     super(Material.piston);
/*  33 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176326_a, (Comparable)EnumFacing.NORTH).withProperty((IProperty)field_176325_b, EnumPistonType.DEFAULT).withProperty((IProperty)field_176327_M, Boolean.valueOf(false)));
/*  34 */     setStepSound(soundTypePiston);
/*  35 */     setHardness(0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
/*  40 */     if (playerIn.capabilities.isCreativeMode) {
/*     */       
/*  42 */       EnumFacing var5 = (EnumFacing)state.getValue((IProperty)field_176326_a);
/*     */       
/*  44 */       if (var5 != null) {
/*     */         
/*  46 */         BlockPos var6 = pos.offset(var5.getOpposite());
/*  47 */         Block var7 = worldIn.getBlockState(var6).getBlock();
/*     */         
/*  49 */         if (var7 == Blocks.piston || var7 == Blocks.sticky_piston)
/*     */         {
/*  51 */           worldIn.setBlockToAir(var6);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  56 */     super.onBlockHarvested(worldIn, pos, state, playerIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/*  61 */     super.breakBlock(worldIn, pos, state);
/*  62 */     EnumFacing var4 = ((EnumFacing)state.getValue((IProperty)field_176326_a)).getOpposite();
/*  63 */     pos = pos.offset(var4);
/*  64 */     IBlockState var5 = worldIn.getBlockState(pos);
/*     */     
/*  66 */     if ((var5.getBlock() == Blocks.piston || var5.getBlock() == Blocks.sticky_piston) && ((Boolean)var5.getValue((IProperty)BlockPistonBase.EXTENDED)).booleanValue()) {
/*     */       
/*  68 */       var5.getBlock().dropBlockAsItem(worldIn, pos, var5, 0);
/*  69 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  75 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  80 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockOnSide(World worldIn, BlockPos pos, EnumFacing side) {
/*  93 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 101 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/* 111 */     func_176324_d(state);
/* 112 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/* 113 */     func_176323_e(state);
/* 114 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/* 115 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176323_e(IBlockState p_176323_1_) {
/* 120 */     float var2 = 0.25F;
/* 121 */     float var3 = 0.375F;
/* 122 */     float var4 = 0.625F;
/* 123 */     float var5 = 0.25F;
/* 124 */     float var6 = 0.75F;
/*     */     
/* 126 */     switch (SwitchEnumFacing.field_177247_a[((EnumFacing)p_176323_1_.getValue((IProperty)field_176326_a)).ordinal()]) {
/*     */       
/*     */       case 1:
/* 129 */         setBlockBounds(0.375F, 0.25F, 0.375F, 0.625F, 1.0F, 0.625F);
/*     */         break;
/*     */       
/*     */       case 2:
/* 133 */         setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.75F, 0.625F);
/*     */         break;
/*     */       
/*     */       case 3:
/* 137 */         setBlockBounds(0.25F, 0.375F, 0.25F, 0.75F, 0.625F, 1.0F);
/*     */         break;
/*     */       
/*     */       case 4:
/* 141 */         setBlockBounds(0.25F, 0.375F, 0.0F, 0.75F, 0.625F, 0.75F);
/*     */         break;
/*     */       
/*     */       case 5:
/* 145 */         setBlockBounds(0.375F, 0.25F, 0.25F, 0.625F, 0.75F, 1.0F);
/*     */         break;
/*     */       
/*     */       case 6:
/* 149 */         setBlockBounds(0.0F, 0.375F, 0.25F, 0.75F, 0.625F, 0.75F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 155 */     func_176324_d(access.getBlockState(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176324_d(IBlockState p_176324_1_) {
/* 160 */     float var2 = 0.25F;
/* 161 */     EnumFacing var3 = (EnumFacing)p_176324_1_.getValue((IProperty)field_176326_a);
/*     */     
/* 163 */     if (var3 != null)
/*     */     {
/* 165 */       switch (SwitchEnumFacing.field_177247_a[var3.ordinal()]) {
/*     */         
/*     */         case 1:
/* 168 */           setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
/*     */           break;
/*     */         
/*     */         case 2:
/* 172 */           setBlockBounds(0.0F, 0.75F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */           break;
/*     */         
/*     */         case 3:
/* 176 */           setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.25F);
/*     */           break;
/*     */         
/*     */         case 4:
/* 180 */           setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 1.0F, 1.0F);
/*     */           break;
/*     */         
/*     */         case 5:
/* 184 */           setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 1.0F, 1.0F);
/*     */           break;
/*     */         
/*     */         case 6:
/* 188 */           setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 195 */     EnumFacing var5 = (EnumFacing)state.getValue((IProperty)field_176326_a);
/* 196 */     BlockPos var6 = pos.offset(var5.getOpposite());
/* 197 */     IBlockState var7 = worldIn.getBlockState(var6);
/*     */     
/* 199 */     if (var7.getBlock() != Blocks.piston && var7.getBlock() != Blocks.sticky_piston) {
/*     */       
/* 201 */       worldIn.setBlockToAir(pos);
/*     */     }
/*     */     else {
/*     */       
/* 205 */       var7.getBlock().onNeighborBlockChange(worldIn, var6, var7, neighborBlock);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 211 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumFacing func_176322_b(int p_176322_0_) {
/* 216 */     int var1 = p_176322_0_ & 0x7;
/* 217 */     return (var1 > 5) ? null : EnumFacing.getFront(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 222 */     return (worldIn.getBlockState(pos).getValue((IProperty)field_176325_b) == EnumPistonType.STICKY) ? Item.getItemFromBlock(Blocks.sticky_piston) : Item.getItemFromBlock(Blocks.piston);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 230 */     return getDefaultState().withProperty((IProperty)field_176326_a, (Comparable)func_176322_b(meta)).withProperty((IProperty)field_176325_b, ((meta & 0x8) > 0) ? EnumPistonType.STICKY : EnumPistonType.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 238 */     byte var2 = 0;
/* 239 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)field_176326_a)).getIndex();
/*     */     
/* 241 */     if (state.getValue((IProperty)field_176325_b) == EnumPistonType.STICKY)
/*     */     {
/* 243 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 246 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 251 */     return new BlockState(this, new IProperty[] { (IProperty)field_176326_a, (IProperty)field_176325_b, (IProperty)field_176327_M });
/*     */   }
/*     */   
/*     */   public enum EnumPistonType
/*     */     implements IStringSerializable {
/* 256 */     DEFAULT("DEFAULT", 0, "normal"),
/* 257 */     STICKY("STICKY", 1, "sticky");
/*     */     
/*     */     private final String field_176714_c;
/* 260 */     private static final EnumPistonType[] $VALUES = new EnumPistonType[] { DEFAULT, STICKY };
/*     */     
/*     */     private static final String __OBFID = "CL_00002035";
/*     */     
/*     */     EnumPistonType(String p_i45666_1_, int p_i45666_2_, String p_i45666_3_) {
/* 265 */       this.field_176714_c = p_i45666_3_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String toString() {
/* 270 */       return this.field_176714_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 275 */       return this.field_176714_c;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 281 */     static final int[] field_177247_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002036";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 288 */         field_177247_a[EnumFacing.DOWN.ordinal()] = 1;
/*     */       }
/* 290 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 297 */         field_177247_a[EnumFacing.UP.ordinal()] = 2;
/*     */       }
/* 299 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 306 */         field_177247_a[EnumFacing.NORTH.ordinal()] = 3;
/*     */       }
/* 308 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 315 */         field_177247_a[EnumFacing.SOUTH.ordinal()] = 4;
/*     */       }
/* 317 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 324 */         field_177247_a[EnumFacing.WEST.ordinal()] = 5;
/*     */       }
/* 326 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 333 */         field_177247_a[EnumFacing.EAST.ordinal()] = 6;
/*     */       }
/* 335 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPistonExtension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */