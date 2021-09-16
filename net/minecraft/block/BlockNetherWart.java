/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockNetherWart
/*     */   extends BlockBush
/*     */ {
/*  18 */   public static final PropertyInteger AGE_PROP = PropertyInteger.create("age", 0, 3);
/*     */   
/*     */   private static final String __OBFID = "CL_00000274";
/*     */   
/*     */   protected BlockNetherWart() {
/*  23 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)AGE_PROP, Integer.valueOf(0)));
/*  24 */     setTickRandomly(true);
/*  25 */     float var1 = 0.5F;
/*  26 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
/*  27 */     setCreativeTab(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canPlaceBlockOn(Block ground) {
/*  35 */     return (ground == Blocks.soul_sand);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlockStay(World worldIn, BlockPos p_180671_2_, IBlockState p_180671_3_) {
/*  40 */     return canPlaceBlockOn(worldIn.getBlockState(p_180671_2_.offsetDown()).getBlock());
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  45 */     int var5 = ((Integer)state.getValue((IProperty)AGE_PROP)).intValue();
/*     */     
/*  47 */     if (var5 < 3 && rand.nextInt(10) == 0) {
/*     */       
/*  49 */       state = state.withProperty((IProperty)AGE_PROP, Integer.valueOf(var5 + 1));
/*  50 */       worldIn.setBlockState(pos, state, 2);
/*     */     } 
/*     */     
/*  53 */     super.updateTick(worldIn, pos, state, rand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/*  64 */     if (!worldIn.isRemote) {
/*     */       
/*  66 */       int var6 = 1;
/*     */       
/*  68 */       if (((Integer)state.getValue((IProperty)AGE_PROP)).intValue() >= 3) {
/*     */         
/*  70 */         var6 = 2 + worldIn.rand.nextInt(3);
/*     */         
/*  72 */         if (fortune > 0)
/*     */         {
/*  74 */           var6 += worldIn.rand.nextInt(fortune + 1);
/*     */         }
/*     */       } 
/*     */       
/*  78 */       for (int var7 = 0; var7 < var6; var7++)
/*     */       {
/*  80 */         spawnAsEntity(worldIn, pos, new ItemStack(Items.nether_wart));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 100 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 105 */     return Items.nether_wart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 113 */     return getDefaultState().withProperty((IProperty)AGE_PROP, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 121 */     return ((Integer)state.getValue((IProperty)AGE_PROP)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 126 */     return new BlockState(this, new IProperty[] { (IProperty)AGE_PROP });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockNetherWart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */