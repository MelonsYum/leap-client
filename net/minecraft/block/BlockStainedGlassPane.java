/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.properties.PropertyEnum;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumWorldBlockLayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockStainedGlassPane
/*    */   extends BlockPane {
/* 19 */   public static final PropertyEnum field_176245_a = PropertyEnum.create("color", EnumDyeColor.class);
/*    */   
/*    */   private static final String __OBFID = "CL_00000313";
/*    */   
/*    */   public BlockStainedGlassPane() {
/* 24 */     super(Material.glass, false);
/* 25 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)NORTH, Boolean.valueOf(false)).withProperty((IProperty)EAST, Boolean.valueOf(false)).withProperty((IProperty)SOUTH, Boolean.valueOf(false)).withProperty((IProperty)WEST, Boolean.valueOf(false)).withProperty((IProperty)field_176245_a, (Comparable)EnumDyeColor.WHITE));
/* 26 */     setCreativeTab(CreativeTabs.tabDecorations);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int damageDropped(IBlockState state) {
/* 34 */     return ((EnumDyeColor)state.getValue((IProperty)field_176245_a)).func_176765_a();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/* 42 */     for (int var4 = 0; var4 < (EnumDyeColor.values()).length; var4++)
/*    */     {
/* 44 */       list.add(new ItemStack(itemIn, 1, var4));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumWorldBlockLayer getBlockLayer() {
/* 50 */     return EnumWorldBlockLayer.TRANSLUCENT;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState getStateFromMeta(int meta) {
/* 58 */     return getDefaultState().withProperty((IProperty)field_176245_a, (Comparable)EnumDyeColor.func_176764_b(meta));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetaFromState(IBlockState state) {
/* 66 */     return ((EnumDyeColor)state.getValue((IProperty)field_176245_a)).func_176765_a();
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockState createBlockState() {
/* 71 */     return new BlockState(this, new IProperty[] { (IProperty)NORTH, (IProperty)EAST, (IProperty)WEST, (IProperty)SOUTH, (IProperty)field_176245_a });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 76 */     if (!worldIn.isRemote)
/*    */     {
/* 78 */       BlockBeacon.func_176450_d(worldIn, pos);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 84 */     if (!worldIn.isRemote)
/*    */     {
/* 86 */       BlockBeacon.func_176450_d(worldIn, pos);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStainedGlassPane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */