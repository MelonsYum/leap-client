/*    */ package optifine;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.resources.model.IBakedModel;
/*    */ import net.minecraft.client.resources.model.SimpleBakedModel;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BetterGrass
/*    */ {
/* 19 */   private static IBakedModel modelEmpty = (IBakedModel)new SimpleBakedModel(new ArrayList(), new ArrayList(), false, false, null, null);
/* 20 */   private static IBakedModel modelCubeMycelium = null;
/* 21 */   private static IBakedModel modelCubeGrassSnowy = null;
/* 22 */   private static IBakedModel modelCubeGrass = null;
/*    */ 
/*    */   
/*    */   public static void update() {
/* 26 */     modelCubeGrass = BlockModelUtils.makeModelCube("minecraft:blocks/grass_top", 0);
/* 27 */     modelCubeGrassSnowy = BlockModelUtils.makeModelCube("minecraft:blocks/snow", -1);
/* 28 */     modelCubeMycelium = BlockModelUtils.makeModelCube("minecraft:blocks/mycelium_top", -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static List getFaceQuads(IBlockAccess blockAccess, Block block, BlockPos blockPos, EnumFacing facing, List quads) {
/* 33 */     if (facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
/*    */       
/* 35 */       if (block instanceof net.minecraft.block.BlockMycelium)
/*    */       {
/* 37 */         return Config.isBetterGrassFancy() ? ((getBlockAt(blockPos.offsetDown(), facing, blockAccess) == Blocks.mycelium) ? modelCubeMycelium.func_177551_a(facing) : quads) : modelCubeMycelium.func_177551_a(facing);
/*    */       }
/*    */ 
/*    */       
/* 41 */       if (block instanceof net.minecraft.block.BlockGrass) {
/*    */         
/* 43 */         Block blockUp = blockAccess.getBlockState(blockPos.offsetUp()).getBlock();
/* 44 */         boolean snowy = !(blockUp != Blocks.snow && blockUp != Blocks.snow_layer);
/*    */         
/* 46 */         if (!Config.isBetterGrassFancy()) {
/*    */           
/* 48 */           if (snowy)
/*    */           {
/* 50 */             return modelCubeGrassSnowy.func_177551_a(facing);
/*    */           }
/*    */           
/* 53 */           return modelCubeGrass.func_177551_a(facing);
/*    */         } 
/*    */         
/* 56 */         if (snowy) {
/*    */           
/* 58 */           if (getBlockAt(blockPos, facing, blockAccess) == Blocks.snow_layer)
/*    */           {
/* 60 */             return modelCubeGrassSnowy.func_177551_a(facing);
/*    */           }
/*    */         }
/* 63 */         else if (getBlockAt(blockPos.offsetDown(), facing, blockAccess) == Blocks.grass) {
/*    */           
/* 65 */           return modelCubeGrass.func_177551_a(facing);
/*    */         } 
/*    */       } 
/*    */       
/* 69 */       return quads;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 74 */     return quads;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static Block getBlockAt(BlockPos blockPos, EnumFacing facing, IBlockAccess blockAccess) {
/* 80 */     BlockPos pos = blockPos.offset(facing);
/* 81 */     Block block = blockAccess.getBlockState(pos).getBlock();
/* 82 */     return block;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\BetterGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */