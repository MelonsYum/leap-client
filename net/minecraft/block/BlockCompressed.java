/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ 
/*    */ public class BlockCompressed
/*    */   extends Block
/*    */ {
/*    */   private final MapColor mapColor;
/*    */   private static final String __OBFID = "CL_00000268";
/*    */   
/*    */   public BlockCompressed(MapColor p_i45414_1_) {
/* 15 */     super(Material.iron);
/* 16 */     this.mapColor = p_i45414_1_;
/* 17 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MapColor getMapColor(IBlockState state) {
/* 25 */     return this.mapColor;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockCompressed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */