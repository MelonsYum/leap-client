/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.ColorizerGrass;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockTallGrass
/*     */   extends BlockBush implements IGrowable {
/*  26 */   public static final PropertyEnum field_176497_a = PropertyEnum.create("type", EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000321";
/*     */   
/*     */   protected BlockTallGrass() {
/*  31 */     super(Material.vine);
/*  32 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176497_a, EnumType.DEAD_BUSH));
/*  33 */     float var1 = 0.4F;
/*  34 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.8F, 0.5F + var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockColor() {
/*  39 */     return ColorizerGrass.getGrassColor(0.5D, 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlockStay(World worldIn, BlockPos p_180671_2_, IBlockState p_180671_3_) {
/*  44 */     return canPlaceBlockOn(worldIn.getBlockState(p_180671_2_.offsetDown()).getBlock());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReplaceable(World worldIn, BlockPos pos) {
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderColor(IBlockState state) {
/*  57 */     if (state.getBlock() != this)
/*     */     {
/*  59 */       return super.getRenderColor(state);
/*     */     }
/*     */ 
/*     */     
/*  63 */     EnumType var2 = (EnumType)state.getValue((IProperty)field_176497_a);
/*  64 */     return (var2 == EnumType.DEAD_BUSH) ? 16777215 : ColorizerGrass.getGrassColor(0.5D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/*  70 */     return worldIn.getBiomeGenForCoords(pos).func_180627_b(pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  80 */     return (rand.nextInt(8) == 0) ? Items.wheat_seeds : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDroppedWithBonus(int fortune, Random random) {
/*  88 */     return 1 + random.nextInt(fortune * 2 + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te) {
/*  93 */     if (!worldIn.isRemote && playerIn.getCurrentEquippedItem() != null && playerIn.getCurrentEquippedItem().getItem() == Items.shears) {
/*     */       
/*  95 */       playerIn.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
/*  96 */       spawnAsEntity(worldIn, pos, new ItemStack(Blocks.tallgrass, 1, ((EnumType)state.getValue((IProperty)field_176497_a)).func_177044_a()));
/*     */     }
/*     */     else {
/*     */       
/* 100 */       super.harvestBlock(worldIn, playerIn, pos, state, te);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/* 106 */     IBlockState var3 = worldIn.getBlockState(pos);
/* 107 */     return var3.getBlock().getMetaFromState(var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/* 115 */     for (int var4 = 1; var4 < 3; var4++)
/*     */     {
/* 117 */       list.add(new ItemStack(itemIn, 1, var4));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
/* 123 */     return (p_176473_3_.getValue((IProperty)field_176497_a) != EnumType.DEAD_BUSH);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
/* 128 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
/* 133 */     BlockDoublePlant.EnumPlantType var5 = BlockDoublePlant.EnumPlantType.GRASS;
/*     */     
/* 135 */     if (p_176474_4_.getValue((IProperty)field_176497_a) == EnumType.FERN)
/*     */     {
/* 137 */       var5 = BlockDoublePlant.EnumPlantType.FERN;
/*     */     }
/*     */     
/* 140 */     if (Blocks.double_plant.canPlaceBlockAt(worldIn, p_176474_3_))
/*     */     {
/* 142 */       Blocks.double_plant.func_176491_a(worldIn, p_176474_3_, var5, 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 151 */     return getDefaultState().withProperty((IProperty)field_176497_a, EnumType.func_177045_a(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 159 */     return ((EnumType)state.getValue((IProperty)field_176497_a)).func_177044_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 164 */     return new BlockState(this, new IProperty[] { (IProperty)field_176497_a });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Block.EnumOffsetType getOffsetType() {
/* 172 */     return Block.EnumOffsetType.XYZ;
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/* 177 */     DEAD_BUSH("DEAD_BUSH", 0, 0, "dead_bush"),
/* 178 */     GRASS("GRASS", 1, 1, "tall_grass"),
/* 179 */     FERN("FERN", 2, 2, "fern");
/* 180 */     private static final EnumType[] field_177048_d = new EnumType[(values()).length];
/*     */     
/*     */     private final int field_177049_e;
/*     */     private final String field_177046_f;
/* 184 */     private static final EnumType[] $VALUES = new EnumType[] { DEAD_BUSH, GRASS, FERN };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002055";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 219 */       EnumType[] var0 = values();
/* 220 */       int var1 = var0.length;
/*     */       
/* 222 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 224 */         EnumType var3 = var0[var2];
/* 225 */         field_177048_d[var3.func_177044_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45676_1_, int p_i45676_2_, int p_i45676_3_, String p_i45676_4_) {
/*     */       this.field_177049_e = p_i45676_3_;
/*     */       this.field_177046_f = p_i45676_4_;
/*     */     }
/*     */     
/*     */     public int func_177044_a() {
/*     */       return this.field_177049_e;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_177046_f;
/*     */     }
/*     */     
/*     */     public static EnumType func_177045_a(int p_177045_0_) {
/*     */       if (p_177045_0_ < 0 || p_177045_0_ >= field_177048_d.length)
/*     */         p_177045_0_ = 0; 
/*     */       return field_177048_d[p_177045_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_177046_f;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockTallGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */