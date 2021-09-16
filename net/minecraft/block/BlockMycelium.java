/*    */ package net.minecraft.block;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.properties.IProperty;
/*    */ import net.minecraft.block.properties.PropertyBool;
/*    */ import net.minecraft.block.state.BlockState;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.EnumParticleTypes;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class BlockMycelium
/*    */   extends Block {
/* 19 */   public static final PropertyBool SNOWY_PROP = PropertyBool.create("snowy");
/*    */   
/*    */   private static final String __OBFID = "CL_00000273";
/*    */   
/*    */   protected BlockMycelium() {
/* 24 */     super(Material.grass);
/* 25 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)SNOWY_PROP, Boolean.valueOf(false)));
/* 26 */     setTickRandomly(true);
/* 27 */     setCreativeTab(CreativeTabs.tabBlock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 36 */     Block var4 = worldIn.getBlockState(pos.offsetUp()).getBlock();
/* 37 */     return state.withProperty((IProperty)SNOWY_PROP, Boolean.valueOf(!(var4 != Blocks.snow && var4 != Blocks.snow_layer)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 42 */     if (!worldIn.isRemote)
/*    */     {
/* 44 */       if (worldIn.getLightFromNeighbors(pos.offsetUp()) < 4 && worldIn.getBlockState(pos.offsetUp()).getBlock().getLightOpacity() > 2) {
/*    */         
/* 46 */         worldIn.setBlockState(pos, Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
/*    */ 
/*    */       
/*    */       }
/* 50 */       else if (worldIn.getLightFromNeighbors(pos.offsetUp()) >= 9) {
/*    */         
/* 52 */         for (int var5 = 0; var5 < 4; var5++) {
/*    */           
/* 54 */           BlockPos var6 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
/* 55 */           IBlockState var7 = worldIn.getBlockState(var6);
/* 56 */           Block var8 = worldIn.getBlockState(var6.offsetUp()).getBlock();
/*    */           
/* 58 */           if (var7.getBlock() == Blocks.dirt && var7.getValue((IProperty)BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT && worldIn.getLightFromNeighbors(var6.offsetUp()) >= 4 && var8.getLightOpacity() <= 2)
/*    */           {
/* 60 */             worldIn.setBlockState(var6, getDefaultState());
/*    */           }
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 70 */     super.randomDisplayTick(worldIn, pos, state, rand);
/*    */     
/* 72 */     if (rand.nextInt(10) == 0)
/*    */     {
/* 74 */       worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, (pos.getX() + rand.nextFloat()), (pos.getY() + 1.1F), (pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D, new int[0]);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 85 */     return Blocks.dirt.getItemDropped(Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, fortune);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMetaFromState(IBlockState state) {
/* 93 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected BlockState createBlockState() {
/* 98 */     return new BlockState(this, new IProperty[] { (IProperty)SNOWY_PROP });
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockMycelium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */