/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class BlockStoneSlab
/*     */   extends BlockSlab {
/*  21 */   public static final PropertyBool field_176555_b = PropertyBool.create("seamless");
/*  22 */   public static final PropertyEnum field_176556_M = PropertyEnum.create("variant", EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000320";
/*     */   
/*     */   public BlockStoneSlab() {
/*  27 */     super(Material.rock);
/*  28 */     IBlockState var1 = this.blockState.getBaseState();
/*     */     
/*  30 */     if (isDouble()) {
/*     */       
/*  32 */       var1 = var1.withProperty((IProperty)field_176555_b, Boolean.valueOf(false));
/*     */     }
/*     */     else {
/*     */       
/*  36 */       var1 = var1.withProperty((IProperty)HALF_PROP, BlockSlab.EnumBlockHalf.BOTTOM);
/*     */     } 
/*     */     
/*  39 */     setDefaultState(var1.withProperty((IProperty)field_176556_M, EnumType.STONE));
/*  40 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  50 */     return Item.getItemFromBlock(Blocks.stone_slab);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/*  55 */     return Item.getItemFromBlock(Blocks.stone_slab);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullSlabName(int p_150002_1_) {
/*  63 */     return String.valueOf(getUnlocalizedName()) + "." + EnumType.func_176625_a(p_150002_1_).func_176627_c();
/*     */   }
/*     */ 
/*     */   
/*     */   public IProperty func_176551_l() {
/*  68 */     return (IProperty)field_176556_M;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object func_176553_a(ItemStack p_176553_1_) {
/*  73 */     return EnumType.func_176625_a(p_176553_1_.getMetadata() & 0x7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  81 */     if (itemIn != Item.getItemFromBlock(Blocks.double_stone_slab)) {
/*     */       
/*  83 */       EnumType[] var4 = EnumType.values();
/*  84 */       int var5 = var4.length;
/*     */       
/*  86 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/*  88 */         EnumType var7 = var4[var6];
/*     */         
/*  90 */         if (var7 != EnumType.WOOD)
/*     */         {
/*  92 */           list.add(new ItemStack(itemIn, 1, var7.func_176624_a()));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 103 */     IBlockState var2 = getDefaultState().withProperty((IProperty)field_176556_M, EnumType.func_176625_a(meta & 0x7));
/*     */     
/* 105 */     if (isDouble()) {
/*     */       
/* 107 */       var2 = var2.withProperty((IProperty)field_176555_b, Boolean.valueOf(((meta & 0x8) != 0)));
/*     */     }
/*     */     else {
/*     */       
/* 111 */       var2 = var2.withProperty((IProperty)HALF_PROP, ((meta & 0x8) == 0) ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
/*     */     } 
/*     */     
/* 114 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 122 */     byte var2 = 0;
/* 123 */     int var3 = var2 | ((EnumType)state.getValue((IProperty)field_176556_M)).func_176624_a();
/*     */     
/* 125 */     if (isDouble()) {
/*     */       
/* 127 */       if (((Boolean)state.getValue((IProperty)field_176555_b)).booleanValue())
/*     */       {
/* 129 */         var3 |= 0x8;
/*     */       }
/*     */     }
/* 132 */     else if (state.getValue((IProperty)HALF_PROP) == BlockSlab.EnumBlockHalf.TOP) {
/*     */       
/* 134 */       var3 |= 0x8;
/*     */     } 
/*     */     
/* 137 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 142 */     return isDouble() ? new BlockState(this, new IProperty[] { (IProperty)field_176555_b, (IProperty)field_176556_M }) : new BlockState(this, new IProperty[] { (IProperty)HALF_PROP, (IProperty)field_176556_M });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 150 */     return ((EnumType)state.getValue((IProperty)field_176556_M)).func_176624_a();
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/* 155 */     STONE("STONE", 0, 0, "stone"),
/* 156 */     SAND("SAND", 1, 1, "sandstone", "sand"),
/* 157 */     WOOD("WOOD", 2, 2, "wood_old", "wood"),
/* 158 */     COBBLESTONE("COBBLESTONE", 3, 3, "cobblestone", "cobble"),
/* 159 */     BRICK("BRICK", 4, 4, "brick"),
/* 160 */     SMOOTHBRICK("SMOOTHBRICK", 5, 5, "stone_brick", "smoothStoneBrick"),
/* 161 */     NETHERBRICK("NETHERBRICK", 6, 6, "nether_brick", "netherBrick"),
/* 162 */     QUARTZ("QUARTZ", 7, 7, "quartz");
/* 163 */     private static final EnumType[] field_176640_i = new EnumType[(values()).length];
/*     */     
/*     */     private final int field_176637_j;
/*     */     private final String field_176638_k;
/*     */     private final String field_176635_l;
/* 168 */     private static final EnumType[] $VALUES = new EnumType[] { STONE, SAND, WOOD, COBBLESTONE, BRICK, SMOOTHBRICK, NETHERBRICK, QUARTZ };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002056";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 214 */       EnumType[] var0 = values();
/* 215 */       int var1 = var0.length;
/*     */       
/* 217 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 219 */         EnumType var3 = var0[var2];
/* 220 */         field_176640_i[var3.func_176624_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45678_1_, int p_i45678_2_, int p_i45678_3_, String p_i45678_4_, String p_i45678_5_) {
/*     */       this.field_176637_j = p_i45678_3_;
/*     */       this.field_176638_k = p_i45678_4_;
/*     */       this.field_176635_l = p_i45678_5_;
/*     */     }
/*     */     
/*     */     public int func_176624_a() {
/*     */       return this.field_176637_j;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176638_k;
/*     */     }
/*     */     
/*     */     public static EnumType func_176625_a(int p_176625_0_) {
/*     */       if (p_176625_0_ < 0 || p_176625_0_ >= field_176640_i.length)
/*     */         p_176625_0_ = 0; 
/*     */       return field_176640_i[p_176625_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176638_k;
/*     */     }
/*     */     
/*     */     public String func_176627_c() {
/*     */       return this.field_176635_l;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStoneSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */