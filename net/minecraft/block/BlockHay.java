/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockHay
/*    */   extends BlockRotatedPillar
/*    */ {
/*    */   private static final String __OBFID = "CL_00000256";
/*    */   
/*    */   public BlockHay() {
/* 21 */     super(Material.grass);
/* 22 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176298_M, (Comparable)EnumFacing.Axis.Y));
/* 23 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState getStateFromMeta(int meta) {
/* 31 */     EnumFacing.Axis var2 = EnumFacing.Axis.Y;
/* 32 */     int var3 = meta & 0xC;
/*    */     
/* 34 */     if (var3 == 4) {
/*    */       
/* 36 */       var2 = EnumFacing.Axis.X;
/*    */     }
/* 38 */     else if (var3 == 8) {
/*    */       
/* 40 */       var2 = EnumFacing.Axis.Z;
/*    */     } 
/*    */     
/* 43 */     return getDefaultState().withProperty((IProperty)field_176298_M, (Comparable)var2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetaFromState(IBlockState state) {
/* 51 */     int var2 = 0;
/* 52 */     EnumFacing.Axis var3 = (EnumFacing.Axis)state.getValue((IProperty)field_176298_M);
/*    */     
/* 54 */     if (var3 == EnumFacing.Axis.X) {
/*    */       
/* 56 */       var2 |= 0x4;
/*    */     }
/* 58 */     else if (var3 == EnumFacing.Axis.Z) {
/*    */       
/* 60 */       var2 |= 0x8;
/*    */     } 
/*    */     
/* 63 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockState createBlockState() {
/* 68 */     return new BlockState(this, new IProperty[] { (IProperty)field_176298_M });
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack createStackedBlock(IBlockState state) {
/* 73 */     return new ItemStack(Item.getItemFromBlock(this), 1, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 78 */     return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty((IProperty)field_176298_M, (Comparable)facing.getAxis());
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockHay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */