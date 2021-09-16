/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.properties.PropertyEnum;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.item.EnumDyeColor;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class BlockColored
/*    */   extends Block {
/* 17 */   public static final PropertyEnum COLOR = PropertyEnum.create("color", EnumDyeColor.class);
/*    */   
/*    */   private static final String __OBFID = "CL_00000217";
/*    */   
/*    */   public BlockColored(Material p_i45398_1_) {
/* 22 */     super(p_i45398_1_);
/* 23 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)COLOR, (Comparable)EnumDyeColor.WHITE));
/* 24 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int damageDropped(IBlockState state) {
/* 32 */     return ((EnumDyeColor)state.getValue((IProperty)COLOR)).func_176765_a();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/* 40 */     EnumDyeColor[] var4 = EnumDyeColor.values();
/* 41 */     int var5 = var4.length;
/*    */     
/* 43 */     for (int var6 = 0; var6 < var5; var6++) {
/*    */       
/* 45 */       EnumDyeColor var7 = var4[var6];
/* 46 */       list.add(new ItemStack(itemIn, 1, var7.func_176765_a()));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MapColor getMapColor(IBlockState state) {
/* 55 */     return ((EnumDyeColor)state.getValue((IProperty)COLOR)).func_176768_e();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState getStateFromMeta(int meta) {
/* 63 */     return getDefaultState().withProperty((IProperty)COLOR, (Comparable)EnumDyeColor.func_176764_b(meta));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetaFromState(IBlockState state) {
/* 71 */     return ((EnumDyeColor)state.getValue((IProperty)COLOR)).func_176765_a();
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockState createBlockState() {
/* 76 */     return new BlockState(this, new IProperty[] { (IProperty)COLOR });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockColored.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */