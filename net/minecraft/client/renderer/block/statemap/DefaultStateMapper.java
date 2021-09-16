/*    */ package net.minecraft.client.renderer.block.statemap;
/*    */ 
/*    */ import java.util.Map;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class DefaultStateMapper
/*    */   extends StateMapperBase {
/*    */   private static final String __OBFID = "CL_00002477";
/*    */   
/*    */   protected ModelResourceLocation func_178132_a(IBlockState p_178132_1_) {
/* 14 */     return new ModelResourceLocation((ResourceLocation)Block.blockRegistry.getNameForObject(p_178132_1_.getBlock()), func_178131_a((Map)p_178132_1_.getProperties()));
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\statemap\DefaultStateMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */