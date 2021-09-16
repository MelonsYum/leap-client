/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.ColorizerGrass;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeColorHelper;
/*     */ 
/*     */ public class BlockGrass
/*     */   extends Block implements IGrowable {
/*  21 */   public static final PropertyBool SNOWY = PropertyBool.create("snowy");
/*     */   
/*     */   private static final String __OBFID = "CL_00000251";
/*     */   
/*     */   protected BlockGrass() {
/*  26 */     super(Material.grass);
/*  27 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)SNOWY, Boolean.valueOf(false)));
/*  28 */     setTickRandomly(true);
/*  29 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  38 */     Block var4 = worldIn.getBlockState(pos.offsetUp()).getBlock();
/*  39 */     return state.withProperty((IProperty)SNOWY, Boolean.valueOf(!(var4 != Blocks.snow && var4 != Blocks.snow_layer)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockColor() {
/*  44 */     return ColorizerGrass.getGrassColor(0.5D, 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderColor(IBlockState state) {
/*  49 */     return getBlockColor();
/*     */   }
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/*  54 */     return BiomeColorHelper.func_180286_a(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  59 */     if (!worldIn.isRemote)
/*     */     {
/*  61 */       if (worldIn.getLightFromNeighbors(pos.offsetUp()) < 4 && worldIn.getBlockState(pos.offsetUp()).getBlock().getLightOpacity() > 2) {
/*     */         
/*  63 */         worldIn.setBlockState(pos, Blocks.dirt.getDefaultState());
/*     */ 
/*     */       
/*     */       }
/*  67 */       else if (worldIn.getLightFromNeighbors(pos.offsetUp()) >= 9) {
/*     */         
/*  69 */         for (int var5 = 0; var5 < 4; var5++) {
/*     */           
/*  71 */           BlockPos var6 = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
/*  72 */           Block var7 = worldIn.getBlockState(var6.offsetUp()).getBlock();
/*  73 */           IBlockState var8 = worldIn.getBlockState(var6);
/*     */           
/*  75 */           if (var8.getBlock() == Blocks.dirt && var8.getValue((IProperty)BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT && worldIn.getLightFromNeighbors(var6.offsetUp()) >= 4 && var7.getLightOpacity() <= 2)
/*     */           {
/*  77 */             worldIn.setBlockState(var6, Blocks.grass.getDefaultState());
/*     */           }
/*     */         } 
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
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  92 */     return Blocks.dirt.getItemDropped(Blocks.dirt.getDefaultState().withProperty((IProperty)BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, fortune);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
/* 107 */     BlockPos var5 = p_176474_3_.offsetUp();
/* 108 */     int var6 = 0;
/*     */     
/* 110 */     while (var6 < 128) {
/*     */       
/* 112 */       BlockPos var7 = var5;
/* 113 */       int var8 = 0;
/*     */ 
/*     */ 
/*     */       
/* 117 */       while (var8 < var6 / 16) {
/*     */         
/* 119 */         var7 = var7.add(p_176474_2_.nextInt(3) - 1, (p_176474_2_.nextInt(3) - 1) * p_176474_2_.nextInt(3) / 2, p_176474_2_.nextInt(3) - 1);
/*     */         
/* 121 */         if (worldIn.getBlockState(var7.offsetDown()).getBlock() == Blocks.grass) { if (!worldIn.getBlockState(var7).getBlock().isNormalCube()) {
/*     */             
/* 123 */             var8++; continue;
/*     */           }  // Byte code: goto -> 249 }
/*     */          // Byte code: goto -> 249
/*     */       } 
/* 127 */       if ((worldIn.getBlockState(var7).getBlock()).blockMaterial == Material.air)
/*     */       {
/* 129 */         if (p_176474_2_.nextInt(8) == 0) {
/*     */           
/* 131 */           BlockFlower.EnumFlowerType var11 = worldIn.getBiomeGenForCoords(var7).pickRandomFlower(p_176474_2_, var7);
/* 132 */           BlockFlower var9 = var11.func_176964_a().func_180346_a();
/* 133 */           IBlockState var10 = var9.getDefaultState().withProperty(var9.func_176494_l(), var11);
/*     */           
/* 135 */           if (var9.canBlockStay(worldIn, var7, var10))
/*     */           {
/* 137 */             worldIn.setBlockState(var7, var10, 3);
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 142 */           IBlockState var12 = Blocks.tallgrass.getDefaultState().withProperty((IProperty)BlockTallGrass.field_176497_a, BlockTallGrass.EnumType.GRASS);
/*     */           
/* 144 */           if (Blocks.tallgrass.canBlockStay(worldIn, var7, var12))
/*     */           {
/* 146 */             worldIn.setBlockState(var7, var12, 3);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 151 */       var6++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 159 */     return EnumWorldBlockLayer.CUTOUT_MIPPED;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 167 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 172 */     return new BlockState(this, new IProperty[] { (IProperty)SNOWY });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */