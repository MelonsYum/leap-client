/*    */ package net.minecraft.world.gen.feature;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.WeightedRandomChestContent;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGeneratorBonusChest
/*    */   extends WorldGenerator
/*    */ {
/*    */   private final List field_175909_a;
/*    */   private final int itemsToGenerateInBonusChest;
/*    */   private static final String __OBFID = "CL_00000403";
/*    */   
/*    */   public WorldGeneratorBonusChest(List p_i45634_1_, int p_i45634_2_) {
/* 26 */     this.field_175909_a = p_i45634_1_;
/* 27 */     this.itemsToGenerateInBonusChest = p_i45634_2_;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*    */     Block var4;
/* 34 */     while (((var4 = worldIn.getBlockState(p_180709_3_).getBlock()).getMaterial() == Material.air || var4.getMaterial() == Material.leaves) && p_180709_3_.getY() > 1)
/*    */     {
/* 36 */       p_180709_3_ = p_180709_3_.offsetDown();
/*    */     }
/*    */     
/* 39 */     if (p_180709_3_.getY() < 1)
/*    */     {
/* 41 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 45 */     p_180709_3_ = p_180709_3_.offsetUp();
/*    */     
/* 47 */     for (int var5 = 0; var5 < 4; var5++) {
/*    */       
/* 49 */       BlockPos var6 = p_180709_3_.add(p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4), p_180709_2_.nextInt(3) - p_180709_2_.nextInt(3), p_180709_2_.nextInt(4) - p_180709_2_.nextInt(4));
/*    */       
/* 51 */       if (worldIn.isAirBlock(var6) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, var6.offsetDown())) {
/*    */         
/* 53 */         worldIn.setBlockState(var6, Blocks.chest.getDefaultState(), 2);
/* 54 */         TileEntity var7 = worldIn.getTileEntity(var6);
/*    */         
/* 56 */         if (var7 instanceof net.minecraft.tileentity.TileEntityChest)
/*    */         {
/* 58 */           WeightedRandomChestContent.generateChestContents(p_180709_2_, this.field_175909_a, (IInventory)var7, this.itemsToGenerateInBonusChest);
/*    */         }
/*    */         
/* 61 */         BlockPos var8 = var6.offsetEast();
/* 62 */         BlockPos var9 = var6.offsetWest();
/* 63 */         BlockPos var10 = var6.offsetNorth();
/* 64 */         BlockPos var11 = var6.offsetSouth();
/*    */         
/* 66 */         if (worldIn.isAirBlock(var9) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, var9.offsetDown()))
/*    */         {
/* 68 */           worldIn.setBlockState(var9, Blocks.torch.getDefaultState(), 2);
/*    */         }
/*    */         
/* 71 */         if (worldIn.isAirBlock(var8) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, var8.offsetDown()))
/*    */         {
/* 73 */           worldIn.setBlockState(var8, Blocks.torch.getDefaultState(), 2);
/*    */         }
/*    */         
/* 76 */         if (worldIn.isAirBlock(var10) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, var10.offsetDown()))
/*    */         {
/* 78 */           worldIn.setBlockState(var10, Blocks.torch.getDefaultState(), 2);
/*    */         }
/*    */         
/* 81 */         if (worldIn.isAirBlock(var11) && World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, var11.offsetDown()))
/*    */         {
/* 83 */           worldIn.setBlockState(var11, Blocks.torch.getDefaultState(), 2);
/*    */         }
/*    */         
/* 86 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 90 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGeneratorBonusChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */