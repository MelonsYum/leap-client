/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityDaylightDetector;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockDaylightDetector
/*     */   extends BlockContainer {
/*  25 */   public static final PropertyInteger field_176436_a = PropertyInteger.create("power", 0, 15);
/*     */   
/*     */   private final boolean field_176435_b;
/*     */   private static final String __OBFID = "CL_00000223";
/*     */   
/*     */   public BlockDaylightDetector(boolean p_i45729_1_) {
/*  31 */     super(Material.wood);
/*  32 */     this.field_176435_b = p_i45729_1_;
/*  33 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176436_a, Integer.valueOf(0)));
/*  34 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
/*  35 */     setCreativeTab(CreativeTabs.tabRedstone);
/*  36 */     setHardness(0.2F);
/*  37 */     setStepSound(soundTypeWood);
/*  38 */     setUnlocalizedName("daylightDetector");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  43 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/*  48 */     return ((Integer)state.getValue((IProperty)field_176436_a)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180677_d(World worldIn, BlockPos p_180677_2_) {
/*  53 */     if (!worldIn.provider.getHasNoSky()) {
/*     */       
/*  55 */       IBlockState var3 = worldIn.getBlockState(p_180677_2_);
/*  56 */       int var4 = worldIn.getLightFor(EnumSkyBlock.SKY, p_180677_2_) - worldIn.getSkylightSubtracted();
/*  57 */       float var5 = worldIn.getCelestialAngleRadians(1.0F);
/*  58 */       float var6 = (var5 < 3.1415927F) ? 0.0F : 6.2831855F;
/*  59 */       var5 += (var6 - var5) * 0.2F;
/*  60 */       var4 = Math.round(var4 * MathHelper.cos(var5));
/*  61 */       var4 = MathHelper.clamp_int(var4, 0, 15);
/*     */       
/*  63 */       if (this.field_176435_b)
/*     */       {
/*  65 */         var4 = 15 - var4;
/*     */       }
/*     */       
/*  68 */       if (((Integer)var3.getValue((IProperty)field_176436_a)).intValue() != var4)
/*     */       {
/*  70 */         worldIn.setBlockState(p_180677_2_, var3.withProperty((IProperty)field_176436_a, Integer.valueOf(var4)), 3);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  77 */     if (playerIn.func_175142_cm()) {
/*     */       
/*  79 */       if (worldIn.isRemote)
/*     */       {
/*  81 */         return true;
/*     */       }
/*     */ 
/*     */       
/*  85 */       if (this.field_176435_b) {
/*     */         
/*  87 */         worldIn.setBlockState(pos, Blocks.daylight_detector.getDefaultState().withProperty((IProperty)field_176436_a, state.getValue((IProperty)field_176436_a)), 4);
/*  88 */         Blocks.daylight_detector.func_180677_d(worldIn, pos);
/*     */       }
/*     */       else {
/*     */         
/*  92 */         worldIn.setBlockState(pos, Blocks.daylight_detector_inverted.getDefaultState().withProperty((IProperty)field_176436_a, state.getValue((IProperty)field_176436_a)), 4);
/*  93 */         Blocks.daylight_detector_inverted.func_180677_d(worldIn, pos);
/*     */       } 
/*     */       
/*  96 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 101 */     return super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 112 */     return Item.getItemFromBlock(Blocks.daylight_detector);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 117 */     return Item.getItemFromBlock(Blocks.daylight_detector);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/* 122 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderType() {
/* 135 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 143 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 151 */     return (TileEntity)new TileEntityDaylightDetector();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 159 */     return getDefaultState().withProperty((IProperty)field_176436_a, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 167 */     return ((Integer)state.getValue((IProperty)field_176436_a)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 172 */     return new BlockState(this, new IProperty[] { (IProperty)field_176436_a });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
/* 180 */     if (!this.field_176435_b)
/*     */     {
/* 182 */       super.getSubBlocks(itemIn, tab, list);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockDaylightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */