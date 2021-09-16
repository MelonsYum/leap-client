/*     */ package net.minecraft.block;
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryEnderChest;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityEnderChest;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockEnderChest extends BlockContainer {
/*  25 */   public static final PropertyDirection field_176437_a = PropertyDirection.create("facing", (Predicate)EnumFacing.Plane.HORIZONTAL);
/*     */   
/*     */   private static final String __OBFID = "CL_00000238";
/*     */   
/*     */   protected BlockEnderChest() {
/*  30 */     super(Material.rock);
/*  31 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176437_a, (Comparable)EnumFacing.NORTH));
/*  32 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  33 */     setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  38 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  51 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  61 */     return Item.getItemFromBlock(Blocks.obsidian);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/*  69 */     return 8;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSilkHarvest() {
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  79 */     return getDefaultState().withProperty((IProperty)field_176437_a, (Comparable)placer.func_174811_aO().getOpposite());
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/*  84 */     worldIn.setBlockState(pos, state.withProperty((IProperty)field_176437_a, (Comparable)placer.func_174811_aO().getOpposite()), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  89 */     InventoryEnderChest var9 = playerIn.getInventoryEnderChest();
/*  90 */     TileEntity var10 = worldIn.getTileEntity(pos);
/*     */     
/*  92 */     if (var9 != null && var10 instanceof TileEntityEnderChest) {
/*     */       
/*  94 */       if (worldIn.getBlockState(pos.offsetUp()).getBlock().isNormalCube())
/*     */       {
/*  96 */         return true;
/*     */       }
/*  98 */       if (worldIn.isRemote)
/*     */       {
/* 100 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 104 */       var9.setChestTileEntity((TileEntityEnderChest)var10);
/* 105 */       playerIn.displayGUIChest((IInventory)var9);
/* 106 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 120 */     return (TileEntity)new TileEntityEnderChest();
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 125 */     for (int var5 = 0; var5 < 3; var5++) {
/*     */       
/* 127 */       int var6 = rand.nextInt(2) * 2 - 1;
/* 128 */       int var7 = rand.nextInt(2) * 2 - 1;
/* 129 */       double var8 = pos.getX() + 0.5D + 0.25D * var6;
/* 130 */       double var10 = (pos.getY() + rand.nextFloat());
/* 131 */       double var12 = pos.getZ() + 0.5D + 0.25D * var7;
/* 132 */       double var14 = (rand.nextFloat() * var6);
/* 133 */       double var16 = (rand.nextFloat() - 0.5D) * 0.125D;
/* 134 */       double var18 = (rand.nextFloat() * var7);
/* 135 */       worldIn.spawnParticle(EnumParticleTypes.PORTAL, var8, var10, var12, var14, var16, var18, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 144 */     EnumFacing var2 = EnumFacing.getFront(meta);
/*     */     
/* 146 */     if (var2.getAxis() == EnumFacing.Axis.Y)
/*     */     {
/* 148 */       var2 = EnumFacing.NORTH;
/*     */     }
/*     */     
/* 151 */     return getDefaultState().withProperty((IProperty)field_176437_a, (Comparable)var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 159 */     return ((EnumFacing)state.getValue((IProperty)field_176437_a)).getIndex();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 164 */     return new BlockState(this, new IProperty[] { (IProperty)field_176437_a });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */