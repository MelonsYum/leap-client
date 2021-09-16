/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ 
/*     */ public class BlockPrismarine
/*     */   extends Block {
/*  16 */   public static final PropertyEnum VARIANTS = PropertyEnum.create("variant", EnumType.class);
/*  17 */   public static final int ROUGHMETA = EnumType.ROUGH.getMetadata();
/*  18 */   public static final int BRICKSMETA = EnumType.BRICKS.getMetadata();
/*  19 */   public static final int DARKMETA = EnumType.DARK.getMetadata();
/*     */   
/*     */   private static final String __OBFID = "CL_00002077";
/*     */   
/*     */   public BlockPrismarine() {
/*  24 */     super(Material.rock);
/*  25 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANTS, EnumType.ROUGH));
/*  26 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  34 */     return ((EnumType)state.getValue((IProperty)VARIANTS)).getMetadata();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  42 */     return ((EnumType)state.getValue((IProperty)VARIANTS)).getMetadata();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  47 */     return new BlockState(this, new IProperty[] { (IProperty)VARIANTS });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  55 */     return getDefaultState().withProperty((IProperty)VARIANTS, EnumType.func_176810_a(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  63 */     list.add(new ItemStack(itemIn, 1, ROUGHMETA));
/*  64 */     list.add(new ItemStack(itemIn, 1, BRICKSMETA));
/*  65 */     list.add(new ItemStack(itemIn, 1, DARKMETA));
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/*  70 */     ROUGH("ROUGH", 0, 0, "prismarine", "rough"),
/*  71 */     BRICKS("BRICKS", 1, 1, "prismarine_bricks", "bricks"),
/*  72 */     DARK("DARK", 2, 2, "dark_prismarine", "dark");
/*  73 */     private static final EnumType[] field_176813_d = new EnumType[(values()).length];
/*     */     
/*     */     private final int meta;
/*     */     private final String field_176811_f;
/*     */     private final String field_176812_g;
/*  78 */     private static final EnumType[] $VALUES = new EnumType[] { ROUGH, BRICKS, DARK };
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
/*     */     private static final String __OBFID = "CL_00002076";
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
/* 119 */       EnumType[] var0 = values();
/* 120 */       int var1 = var0.length;
/*     */       
/* 122 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 124 */         EnumType var3 = var0[var2];
/* 125 */         field_176813_d[var3.getMetadata()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45692_1_, int p_i45692_2_, int p_i45692_3_, String p_i45692_4_, String p_i45692_5_) {
/*     */       this.meta = p_i45692_3_;
/*     */       this.field_176811_f = p_i45692_4_;
/*     */       this.field_176812_g = p_i45692_5_;
/*     */     }
/*     */     
/*     */     public int getMetadata() {
/*     */       return this.meta;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176811_f;
/*     */     }
/*     */     
/*     */     public static EnumType func_176810_a(int p_176810_0_) {
/*     */       if (p_176810_0_ < 0 || p_176810_0_ >= field_176813_d.length)
/*     */         p_176810_0_ = 0; 
/*     */       return field_176813_d[p_176810_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176811_f;
/*     */     }
/*     */     
/*     */     public String func_176809_c() {
/*     */       return this.field_176812_g;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPrismarine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */