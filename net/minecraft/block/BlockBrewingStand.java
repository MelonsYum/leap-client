/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryHelper;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityBrewingStand;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockBrewingStand extends BlockContainer {
/*  29 */   public static final PropertyBool[] BOTTLE_PROPS = new PropertyBool[] { PropertyBool.create("has_bottle_0"), PropertyBool.create("has_bottle_1"), PropertyBool.create("has_bottle_2") };
/*  30 */   private final Random rand = new Random();
/*     */   
/*     */   private static final String __OBFID = "CL_00000207";
/*     */   
/*     */   public BlockBrewingStand() {
/*  35 */     super(Material.iron);
/*  36 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)BOTTLE_PROPS[0], Boolean.valueOf(false)).withProperty((IProperty)BOTTLE_PROPS[1], Boolean.valueOf(false)).withProperty((IProperty)BOTTLE_PROPS[2], Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/*  49 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/*  57 */     return (TileEntity)new TileEntityBrewingStand();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/*  72 */     setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
/*  73 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*  74 */     setBlockBoundsForItemRender();
/*  75 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  83 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  88 */     if (worldIn.isRemote)
/*     */     {
/*  90 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  94 */     TileEntity var9 = worldIn.getTileEntity(pos);
/*     */     
/*  96 */     if (var9 instanceof TileEntityBrewingStand)
/*     */     {
/*  98 */       playerIn.displayGUIChest((IInventory)var9);
/*     */     }
/*     */     
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 107 */     if (stack.hasDisplayName()) {
/*     */       
/* 109 */       TileEntity var6 = worldIn.getTileEntity(pos);
/*     */       
/* 111 */       if (var6 instanceof TileEntityBrewingStand)
/*     */       {
/* 113 */         ((TileEntityBrewingStand)var6).func_145937_a(stack.getDisplayName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 120 */     double var5 = (pos.getX() + 0.4F + rand.nextFloat() * 0.2F);
/* 121 */     double var7 = (pos.getY() + 0.7F + rand.nextFloat() * 0.3F);
/* 122 */     double var9 = (pos.getZ() + 0.4F + rand.nextFloat() * 0.2F);
/* 123 */     worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var5, var7, var9, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 128 */     TileEntity var4 = worldIn.getTileEntity(pos);
/*     */     
/* 130 */     if (var4 instanceof TileEntityBrewingStand)
/*     */     {
/* 132 */       InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)var4);
/*     */     }
/*     */     
/* 135 */     super.breakBlock(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 145 */     return Items.brewing_stand;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 150 */     return Items.brewing_stand;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasComparatorInputOverride() {
/* 155 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getComparatorInputOverride(World worldIn, BlockPos pos) {
/* 160 */     return Container.calcRedstoneFromInventory(worldIn.getTileEntity(pos));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 165 */     return EnumWorldBlockLayer.CUTOUT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 173 */     IBlockState var2 = getDefaultState();
/*     */     
/* 175 */     for (int var3 = 0; var3 < 3; var3++)
/*     */     {
/* 177 */       var2 = var2.withProperty((IProperty)BOTTLE_PROPS[var3], Boolean.valueOf(((meta & 1 << var3) > 0)));
/*     */     }
/*     */     
/* 180 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 188 */     int var2 = 0;
/*     */     
/* 190 */     for (int var3 = 0; var3 < 3; var3++) {
/*     */       
/* 192 */       if (((Boolean)state.getValue((IProperty)BOTTLE_PROPS[var3])).booleanValue())
/*     */       {
/* 194 */         var2 |= 1 << var3;
/*     */       }
/*     */     } 
/*     */     
/* 198 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 203 */     return new BlockState(this, new IProperty[] { (IProperty)BOTTLE_PROPS[0], (IProperty)BOTTLE_PROPS[1], (IProperty)BOTTLE_PROPS[2] });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */