/*    */ package net.minecraft.item;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockDoublePlant;
/*    */ import net.minecraft.world.ColorizerGrass;
/*    */ 
/*    */ public class ItemDoublePlant
/*    */   extends ItemMultiTexture
/*    */ {
/*    */   private static final String __OBFID = "CL_00000021";
/*    */   
/*    */   public ItemDoublePlant(Block p_i45787_1_, Block p_i45787_2_, Function p_i45787_3_) {
/* 14 */     super(p_i45787_1_, p_i45787_2_, p_i45787_3_);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getColorFromItemStack(ItemStack stack, int renderPass) {
/* 19 */     BlockDoublePlant.EnumPlantType var3 = BlockDoublePlant.EnumPlantType.func_176938_a(stack.getMetadata());
/* 20 */     return (var3 != BlockDoublePlant.EnumPlantType.GRASS && var3 != BlockDoublePlant.EnumPlantType.FERN) ? super.getColorFromItemStack(stack, renderPass) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\item\ItemDoublePlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */