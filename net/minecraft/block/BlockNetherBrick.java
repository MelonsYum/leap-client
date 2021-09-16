/*    */ package net.minecraft.block;
/*    */ 
/*    */ import net.minecraft.block.material.MapColor;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ 
/*    */ public class BlockNetherBrick
/*    */   extends Block
/*    */ {
/*    */   private static final String __OBFID = "CL_00002091";
/*    */   
/*    */   public BlockNetherBrick() {
/* 14 */     super(Material.rock);
/* 15 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MapColor getMapColor(IBlockState state) {
/* 23 */     return MapColor.netherrackColor;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockNetherBrick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */