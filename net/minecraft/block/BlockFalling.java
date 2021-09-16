/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.item.EntityFallingBlock;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockFalling
/*    */   extends Block {
/*    */   public static boolean fallInstantly;
/*    */   private static final String __OBFID = "CL_00000240";
/*    */   
/*    */   public BlockFalling() {
/* 19 */     super(Material.sand);
/* 20 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockFalling(Material materialIn) {
/* 25 */     super(materialIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 30 */     worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 35 */     worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 40 */     if (!worldIn.isRemote)
/*    */     {
/* 42 */       checkFallable(worldIn, pos);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private void checkFallable(World worldIn, BlockPos pos) {
/* 48 */     if (canFallInto(worldIn, pos.offsetDown()) && pos.getY() >= 0) {
/*    */       
/* 50 */       byte var3 = 32;
/*    */       
/* 52 */       if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-var3, -var3, -var3), pos.add(var3, var3, var3))) {
/*    */         
/* 54 */         if (!worldIn.isRemote)
/*    */         {
/* 56 */           EntityFallingBlock var5 = new EntityFallingBlock(worldIn, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, worldIn.getBlockState(pos));
/* 57 */           onStartFalling(var5);
/* 58 */           worldIn.spawnEntityInWorld((Entity)var5);
/*    */         }
/*    */       
/*    */       } else {
/*    */         
/* 63 */         worldIn.setBlockToAir(pos);
/*    */         
/*    */         BlockPos var4;
/* 66 */         for (var4 = pos.offsetDown(); canFallInto(worldIn, var4) && var4.getY() > 0; var4 = var4.offsetDown());
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 71 */         if (var4.getY() > 0)
/*    */         {
/* 73 */           worldIn.setBlockState(var4.offsetUp(), getDefaultState());
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void onStartFalling(EntityFallingBlock fallingEntity) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public int tickRate(World worldIn) {
/* 86 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean canFallInto(World worldIn, BlockPos pos) {
/* 91 */     Block var2 = worldIn.getBlockState(pos).getBlock();
/* 92 */     Material var3 = var2.blockMaterial;
/* 93 */     return !(var2 != Blocks.fire && var3 != Material.air && var3 != Material.water && var3 != Material.lava);
/*    */   }
/*    */   
/*    */   public void onEndFalling(World worldIn, BlockPos pos) {}
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockFalling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */