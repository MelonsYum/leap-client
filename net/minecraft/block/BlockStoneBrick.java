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
/*     */ public class BlockStoneBrick
/*     */   extends Block {
/*  16 */   public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", EnumType.class);
/*  17 */   public static final int DEFAULT_META = EnumType.DEFAULT.getMetaFromState();
/*  18 */   public static final int MOSSY_META = EnumType.MOSSY.getMetaFromState();
/*  19 */   public static final int CRACKED_META = EnumType.CRACKED.getMetaFromState();
/*  20 */   public static final int CHISELED_META = EnumType.CHISELED.getMetaFromState();
/*     */   
/*     */   private static final String __OBFID = "CL_00000318";
/*     */   
/*     */   public BlockStoneBrick() {
/*  25 */     super(Material.rock);
/*  26 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT_PROP, EnumType.DEFAULT));
/*  27 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  35 */     return ((EnumType)state.getValue((IProperty)VARIANT_PROP)).getMetaFromState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  43 */     EnumType[] var4 = EnumType.values();
/*  44 */     int var5 = var4.length;
/*     */     
/*  46 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/*  48 */       EnumType var7 = var4[var6];
/*  49 */       list.add(new ItemStack(itemIn, 1, var7.getMetaFromState()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  58 */     return getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.getStateFromMeta(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  66 */     return ((EnumType)state.getValue((IProperty)VARIANT_PROP)).getMetaFromState();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  71 */     return new BlockState(this, new IProperty[] { (IProperty)VARIANT_PROP });
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/*  76 */     DEFAULT("DEFAULT", 0, 0, "stonebrick", "default"),
/*  77 */     MOSSY("MOSSY", 1, 1, "mossy_stonebrick", "mossy"),
/*  78 */     CRACKED("CRACKED", 2, 2, "cracked_stonebrick", "cracked"),
/*  79 */     CHISELED("CHISELED", 3, 3, "chiseled_stonebrick", "chiseled");
/*  80 */     private static final EnumType[] TYPES_ARRAY = new EnumType[(values()).length];
/*     */     
/*     */     private final int field_176615_f;
/*     */     private final String field_176616_g;
/*     */     private final String field_176622_h;
/*  85 */     private static final EnumType[] $VALUES = new EnumType[] { DEFAULT, MOSSY, CRACKED, CHISELED };
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
/*     */     private static final String __OBFID = "CL_00002057";
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
/* 126 */       EnumType[] var0 = values();
/* 127 */       int var1 = var0.length;
/*     */       
/* 129 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 131 */         EnumType var3 = var0[var2];
/* 132 */         TYPES_ARRAY[var3.getMetaFromState()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45679_1_, int p_i45679_2_, int p_i45679_3_, String p_i45679_4_, String p_i45679_5_) {
/*     */       this.field_176615_f = p_i45679_3_;
/*     */       this.field_176616_g = p_i45679_4_;
/*     */       this.field_176622_h = p_i45679_5_;
/*     */     }
/*     */     
/*     */     public int getMetaFromState() {
/*     */       return this.field_176615_f;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176616_g;
/*     */     }
/*     */     
/*     */     public static EnumType getStateFromMeta(int p_176613_0_) {
/*     */       if (p_176613_0_ < 0 || p_176613_0_ >= TYPES_ARRAY.length)
/*     */         p_176613_0_ = 0; 
/*     */       return TYPES_ARRAY[p_176613_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176616_g;
/*     */     }
/*     */     
/*     */     public String getVariantName() {
/*     */       return this.field_176622_h;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStoneBrick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */