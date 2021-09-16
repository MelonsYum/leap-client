/*     */ package net.minecraft.block;
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityBanner;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockBanner extends BlockContainer {
/*  25 */   public static final PropertyDirection FACING_PROP = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*  26 */   public static final PropertyInteger ROTATION_PROP = PropertyInteger.create("rotation", 0, 15);
/*     */   
/*     */   private static final String __OBFID = "CL_00002143";
/*     */   
/*     */   protected BlockBanner() {
/*  31 */     super(Material.wood);
/*  32 */     float var1 = 0.25F;
/*  33 */     float var2 = 1.0F;
/*  34 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var2, 0.5F + var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  39 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getSelectedBoundingBox(World worldIn, BlockPos pos) {
/*  44 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  45 */     return super.getSelectedBoundingBox(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  60 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/*  68 */     return (TileEntity)new TileEntityBanner();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  78 */     return Items.banner;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/*  83 */     return Items.banner;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/*  94 */     TileEntity var6 = worldIn.getTileEntity(pos);
/*     */     
/*  96 */     if (var6 instanceof TileEntityBanner) {
/*     */       
/*  98 */       ItemStack var7 = new ItemStack(Items.banner, 1, ((TileEntityBanner)var6).getBaseColor());
/*  99 */       NBTTagCompound var8 = new NBTTagCompound();
/* 100 */       var6.writeToNBT(var8);
/* 101 */       var8.removeTag("x");
/* 102 */       var8.removeTag("y");
/* 103 */       var8.removeTag("z");
/* 104 */       var8.removeTag("id");
/* 105 */       var7.setTagInfo("BlockEntityTag", (NBTBase)var8);
/* 106 */       spawnAsEntity(worldIn, pos, var7);
/*     */     }
/*     */     else {
/*     */       
/* 110 */       super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te) {
/* 116 */     if (te instanceof TileEntityBanner) {
/*     */       
/* 118 */       ItemStack var6 = new ItemStack(Items.banner, 1, ((TileEntityBanner)te).getBaseColor());
/* 119 */       NBTTagCompound var7 = new NBTTagCompound();
/* 120 */       te.writeToNBT(var7);
/* 121 */       var7.removeTag("x");
/* 122 */       var7.removeTag("y");
/* 123 */       var7.removeTag("z");
/* 124 */       var7.removeTag("id");
/* 125 */       var6.setTagInfo("BlockEntityTag", (NBTBase)var7);
/* 126 */       spawnAsEntity(worldIn, pos, var6);
/*     */     }
/*     */     else {
/*     */       
/* 130 */       super.harvestBlock(worldIn, playerIn, pos, state, null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class BlockBannerHanging
/*     */     extends BlockBanner
/*     */   {
/*     */     private static final String __OBFID = "CL_00002140";
/*     */     
/*     */     public BlockBannerHanging() {
/* 140 */       setDefaultState(this.blockState.getBaseState().withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.NORTH));
/*     */     }
/*     */ 
/*     */     
/*     */     public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 145 */       EnumFacing var3 = (EnumFacing)access.getBlockState(pos).getValue((IProperty)FACING_PROP);
/* 146 */       float var4 = 0.0F;
/* 147 */       float var5 = 0.78125F;
/* 148 */       float var6 = 0.0F;
/* 149 */       float var7 = 1.0F;
/* 150 */       float var8 = 0.125F;
/* 151 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 153 */       switch (BlockBanner.SwitchEnumFacing.SWITCH_MAP[var3.ordinal()]) {
/*     */ 
/*     */         
/*     */         default:
/* 157 */           setBlockBounds(var6, var4, 1.0F - var8, var7, var5, 1.0F);
/*     */           return;
/*     */         
/*     */         case 2:
/* 161 */           setBlockBounds(var6, var4, 0.0F, var7, var5, var8);
/*     */           return;
/*     */         
/*     */         case 3:
/* 165 */           setBlockBounds(1.0F - var8, var4, var6, 1.0F, var5, var7); return;
/*     */         case 4:
/*     */           break;
/*     */       } 
/* 169 */       setBlockBounds(0.0F, var4, var6, var8, var5, var7);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 175 */       EnumFacing var5 = (EnumFacing)state.getValue((IProperty)FACING_PROP);
/*     */       
/* 177 */       if (!worldIn.getBlockState(pos.offset(var5.getOpposite())).getBlock().getMaterial().isSolid()) {
/*     */         
/* 179 */         dropBlockAsItem(worldIn, pos, state, 0);
/* 180 */         worldIn.setBlockToAir(pos);
/*     */       } 
/*     */       
/* 183 */       super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockState getStateFromMeta(int meta) {
/* 188 */       EnumFacing var2 = EnumFacing.getFront(meta);
/*     */       
/* 190 */       if (var2.getAxis() == EnumFacing.Axis.Y)
/*     */       {
/* 192 */         var2 = EnumFacing.NORTH;
/*     */       }
/*     */       
/* 195 */       return getDefaultState().withProperty((IProperty)FACING_PROP, (Comparable)var2);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMetaFromState(IBlockState state) {
/* 200 */       return ((EnumFacing)state.getValue((IProperty)FACING_PROP)).getIndex();
/*     */     }
/*     */ 
/*     */     
/*     */     protected BlockState createBlockState() {
/* 205 */       return new BlockState(this, new IProperty[] { (IProperty)FACING_PROP });
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BlockBannerStanding
/*     */     extends BlockBanner
/*     */   {
/*     */     private static final String __OBFID = "CL_00002141";
/*     */     
/*     */     public BlockBannerStanding() {
/* 215 */       setDefaultState(this.blockState.getBaseState().withProperty((IProperty)ROTATION_PROP, Integer.valueOf(0)));
/*     */     }
/*     */ 
/*     */     
/*     */     public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 220 */       if (!worldIn.getBlockState(pos.offsetDown()).getBlock().getMaterial().isSolid()) {
/*     */         
/* 222 */         dropBlockAsItem(worldIn, pos, state, 0);
/* 223 */         worldIn.setBlockToAir(pos);
/*     */       } 
/*     */       
/* 226 */       super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockState getStateFromMeta(int meta) {
/* 231 */       return getDefaultState().withProperty((IProperty)ROTATION_PROP, Integer.valueOf(meta));
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMetaFromState(IBlockState state) {
/* 236 */       return ((Integer)state.getValue((IProperty)ROTATION_PROP)).intValue();
/*     */     }
/*     */ 
/*     */     
/*     */     protected BlockState createBlockState() {
/* 241 */       return new BlockState(this, new IProperty[] { (IProperty)ROTATION_PROP });
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 247 */     static final int[] SWITCH_MAP = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002142";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 254 */         SWITCH_MAP[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 256 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 263 */         SWITCH_MAP[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 265 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 272 */         SWITCH_MAP[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 274 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 281 */         SWITCH_MAP[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 283 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */