/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockStainedGlass
/*     */   extends BlockBreakable {
/*  21 */   public static final PropertyEnum field_176547_a = PropertyEnum.create("color", EnumDyeColor.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000312";
/*     */   
/*     */   public BlockStainedGlass(Material p_i45427_1_) {
/*  26 */     super(p_i45427_1_, false);
/*  27 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176547_a, (Comparable)EnumDyeColor.WHITE));
/*  28 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  36 */     return ((EnumDyeColor)state.getValue((IProperty)field_176547_a)).func_176765_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  44 */     EnumDyeColor[] var4 = EnumDyeColor.values();
/*  45 */     int var5 = var4.length;
/*     */     
/*  47 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/*  49 */       EnumDyeColor var7 = var4[var6];
/*  50 */       list.add(new ItemStack(itemIn, 1, var7.func_176765_a()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapColor getMapColor(IBlockState state) {
/*  59 */     return ((EnumDyeColor)state.getValue((IProperty)field_176547_a)).func_176768_e();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/*  64 */     return EnumWorldBlockLayer.TRANSLUCENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/*  72 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSilkHarvest() {
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  82 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  90 */     return getDefaultState().withProperty((IProperty)field_176547_a, (Comparable)EnumDyeColor.func_176764_b(meta));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  95 */     if (!worldIn.isRemote)
/*     */     {
/*  97 */       BlockBeacon.func_176450_d(worldIn, pos);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 103 */     if (!worldIn.isRemote)
/*     */     {
/* 105 */       BlockBeacon.func_176450_d(worldIn, pos);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 114 */     return ((EnumDyeColor)state.getValue((IProperty)field_176547_a)).func_176765_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 119 */     return new BlockState(this, new IProperty[] { (IProperty)field_176547_a });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStainedGlass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */