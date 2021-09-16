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
/*     */ public abstract class BlockStoneSlabNew
/*     */   extends BlockSlab {
/*  21 */   public static final PropertyBool field_176558_b = PropertyBool.create("seamless");
/*  22 */   public static final PropertyEnum field_176559_M = PropertyEnum.create("variant", EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00002087";
/*     */   
/*     */   public BlockStoneSlabNew() {
/*  27 */     super(Material.rock);
/*  28 */     IBlockState var1 = this.blockState.getBaseState();
/*     */     
/*  30 */     if (isDouble()) {
/*     */       
/*  32 */       var1 = var1.withProperty((IProperty)field_176558_b, Boolean.valueOf(false));
/*     */     }
/*     */     else {
/*     */       
/*  36 */       var1 = var1.withProperty((IProperty)HALF_PROP, BlockSlab.EnumBlockHalf.BOTTOM);
/*     */     } 
/*     */     
/*  39 */     setDefaultState(var1.withProperty((IProperty)field_176559_M, EnumType.RED_SANDSTONE));
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
/*  50 */     return Item.getItemFromBlock(Blocks.stone_slab2);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/*  55 */     return Item.getItemFromBlock(Blocks.stone_slab2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFullSlabName(int p_150002_1_) {
/*  63 */     return String.valueOf(getUnlocalizedName()) + "." + EnumType.func_176916_a(p_150002_1_).func_176918_c();
/*     */   }
/*     */ 
/*     */   
/*     */   public IProperty func_176551_l() {
/*  68 */     return (IProperty)field_176559_M;
/*     */   }
/*     */ 
/*     */   
/*     */   public Object func_176553_a(ItemStack p_176553_1_) {
/*  73 */     return EnumType.func_176916_a(p_176553_1_.getMetadata() & 0x7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  81 */     if (itemIn != Item.getItemFromBlock(Blocks.double_stone_slab2)) {
/*     */       
/*  83 */       EnumType[] var4 = EnumType.values();
/*  84 */       int var5 = var4.length;
/*     */       
/*  86 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/*  88 */         EnumType var7 = var4[var6];
/*  89 */         list.add(new ItemStack(itemIn, 1, var7.func_176915_a()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  99 */     IBlockState var2 = getDefaultState().withProperty((IProperty)field_176559_M, EnumType.func_176916_a(meta & 0x7));
/*     */     
/* 101 */     if (isDouble()) {
/*     */       
/* 103 */       var2 = var2.withProperty((IProperty)field_176558_b, Boolean.valueOf(((meta & 0x8) != 0)));
/*     */     }
/*     */     else {
/*     */       
/* 107 */       var2 = var2.withProperty((IProperty)HALF_PROP, ((meta & 0x8) == 0) ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
/*     */     } 
/*     */     
/* 110 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 118 */     byte var2 = 0;
/* 119 */     int var3 = var2 | ((EnumType)state.getValue((IProperty)field_176559_M)).func_176915_a();
/*     */     
/* 121 */     if (isDouble()) {
/*     */       
/* 123 */       if (((Boolean)state.getValue((IProperty)field_176558_b)).booleanValue())
/*     */       {
/* 125 */         var3 |= 0x8;
/*     */       }
/*     */     }
/* 128 */     else if (state.getValue((IProperty)HALF_PROP) == BlockSlab.EnumBlockHalf.TOP) {
/*     */       
/* 130 */       var3 |= 0x8;
/*     */     } 
/*     */     
/* 133 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 138 */     return isDouble() ? new BlockState(this, new IProperty[] { (IProperty)field_176558_b, (IProperty)field_176559_M }) : new BlockState(this, new IProperty[] { (IProperty)HALF_PROP, (IProperty)field_176559_M });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 146 */     return ((EnumType)state.getValue((IProperty)field_176559_M)).func_176915_a();
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/* 151 */     RED_SANDSTONE("RED_SANDSTONE", 0, 0, "red_sandstone");
/* 152 */     private static final EnumType[] field_176921_b = new EnumType[(values()).length];
/*     */     
/*     */     private final int field_176922_c;
/*     */     private final String field_176919_d;
/* 156 */     private static final EnumType[] $VALUES = new EnumType[] { RED_SANDSTONE };
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
/*     */     private static final String __OBFID = "CL_00002086";
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
/* 196 */       EnumType[] var0 = values();
/* 197 */       int var1 = var0.length;
/*     */       
/* 199 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 201 */         EnumType var3 = var0[var2];
/* 202 */         field_176921_b[var3.func_176915_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45697_1_, int p_i45697_2_, int p_i45697_3_, String p_i45697_4_) {
/*     */       this.field_176922_c = p_i45697_3_;
/*     */       this.field_176919_d = p_i45697_4_;
/*     */     }
/*     */     
/*     */     public int func_176915_a() {
/*     */       return this.field_176922_c;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176919_d;
/*     */     }
/*     */     
/*     */     public static EnumType func_176916_a(int p_176916_0_) {
/*     */       if (p_176916_0_ < 0 || p_176916_0_ >= field_176921_b.length)
/*     */         p_176916_0_ = 0; 
/*     */       return field_176921_b[p_176916_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176919_d;
/*     */     }
/*     */     
/*     */     public String func_176918_c() {
/*     */       return this.field_176919_d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStoneSlabNew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */