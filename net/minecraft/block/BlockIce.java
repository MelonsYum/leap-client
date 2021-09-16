/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.enchantment.EnchantmentHelper;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.stats.StatList;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumWorldBlockLayer;
/*    */ import net.minecraft.world.EnumSkyBlock;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockIce
/*    */   extends BlockBreakable {
/*    */   private static final String __OBFID = "CL_00000259";
/*    */   
/*    */   public BlockIce() {
/* 24 */     super(Material.ice, false);
/* 25 */     this.slipperiness = 0.98F;
/* 26 */     setTickRandomly(true);
/* 27 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumWorldBlockLayer getBlockLayer() {
/* 32 */     return EnumWorldBlockLayer.TRANSLUCENT;
/*    */   }
/*    */ 
/*    */   
/*    */   public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te) {
/* 37 */     playerIn.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
/* 38 */     playerIn.addExhaustion(0.025F);
/*    */     
/* 40 */     if (canSilkHarvest() && EnchantmentHelper.getSilkTouchModifier((EntityLivingBase)playerIn)) {
/*    */       
/* 42 */       ItemStack var8 = createStackedBlock(state);
/*    */       
/* 44 */       if (var8 != null)
/*    */       {
/* 46 */         spawnAsEntity(worldIn, pos, var8);
/*    */       }
/*    */     }
/*    */     else {
/*    */       
/* 51 */       if (worldIn.provider.func_177500_n()) {
/*    */         
/* 53 */         worldIn.setBlockToAir(pos);
/*    */         
/*    */         return;
/*    */       } 
/* 57 */       int var6 = EnchantmentHelper.getFortuneModifier((EntityLivingBase)playerIn);
/* 58 */       dropBlockAsItem(worldIn, pos, state, var6);
/* 59 */       Material var7 = worldIn.getBlockState(pos.offsetDown()).getBlock().getMaterial();
/*    */       
/* 61 */       if (var7.blocksMovement() || var7.isLiquid())
/*    */       {
/* 63 */         worldIn.setBlockState(pos, Blocks.flowing_water.getDefaultState());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int quantityDropped(Random random) {
/* 73 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 78 */     if (worldIn.getLightFor(EnumSkyBlock.BLOCK, pos) > 11 - getLightOpacity())
/*    */     {
/* 80 */       if (worldIn.provider.func_177500_n()) {
/*    */         
/* 82 */         worldIn.setBlockToAir(pos);
/*    */       }
/*    */       else {
/*    */         
/* 86 */         dropBlockAsItem(worldIn, pos, worldIn.getBlockState(pos), 0);
/* 87 */         worldIn.setBlockState(pos, Blocks.water.getDefaultState());
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMobilityFlag() {
/* 94 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockIce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */