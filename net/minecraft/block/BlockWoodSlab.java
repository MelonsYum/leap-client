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
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class BlockWoodSlab
/*     */   extends BlockSlab {
/*  19 */   public static final PropertyEnum field_176557_b = PropertyEnum.create("variant", BlockPlanks.EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000337";
/*     */   
/*     */   public BlockWoodSlab() {
/*  24 */     super(Material.wood);
/*  25 */     IBlockState var1 = this.blockState.getBaseState();
/*     */     
/*  27 */     if (!isDouble())
/*     */     {
/*  29 */       var1 = var1.withProperty((IProperty)HALF_PROP, BlockSlab.EnumBlockHalf.BOTTOM);
/*     */     }
/*     */     
/*  32 */     setDefaultState(var1.withProperty((IProperty)field_176557_b, BlockPlanks.EnumType.OAK));
/*  33 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  43 */     return Item.getItemFromBlock(Blocks.wooden_slab);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/*  48 */     return Item.getItemFromBlock(Blocks.wooden_slab);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullSlabName(int p_150002_1_) {
/*  56 */     return String.valueOf(getUnlocalizedName()) + "." + BlockPlanks.EnumType.func_176837_a(p_150002_1_).func_176840_c();
/*     */   }
/*     */ 
/*     */   
/*     */   public IProperty func_176551_l() {
/*  61 */     return (IProperty)field_176557_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object func_176553_a(ItemStack p_176553_1_) {
/*  66 */     return BlockPlanks.EnumType.func_176837_a(p_176553_1_.getMetadata() & 0x7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  74 */     if (itemIn != Item.getItemFromBlock(Blocks.double_wooden_slab)) {
/*     */       
/*  76 */       BlockPlanks.EnumType[] var4 = BlockPlanks.EnumType.values();
/*  77 */       int var5 = var4.length;
/*     */       
/*  79 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/*  81 */         BlockPlanks.EnumType var7 = var4[var6];
/*  82 */         list.add(new ItemStack(itemIn, 1, var7.func_176839_a()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  92 */     IBlockState var2 = getDefaultState().withProperty((IProperty)field_176557_b, BlockPlanks.EnumType.func_176837_a(meta & 0x7));
/*     */     
/*  94 */     if (!isDouble())
/*     */     {
/*  96 */       var2 = var2.withProperty((IProperty)HALF_PROP, ((meta & 0x8) == 0) ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
/*     */     }
/*     */     
/*  99 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 107 */     byte var2 = 0;
/* 108 */     int var3 = var2 | ((BlockPlanks.EnumType)state.getValue((IProperty)field_176557_b)).func_176839_a();
/*     */     
/* 110 */     if (!isDouble() && state.getValue((IProperty)HALF_PROP) == BlockSlab.EnumBlockHalf.TOP)
/*     */     {
/* 112 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 115 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 120 */     return isDouble() ? new BlockState(this, new IProperty[] { (IProperty)field_176557_b }) : new BlockState(this, new IProperty[] { (IProperty)HALF_PROP, (IProperty)field_176557_b });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 128 */     return ((BlockPlanks.EnumType)state.getValue((IProperty)field_176557_b)).func_176839_a();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockWoodSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */