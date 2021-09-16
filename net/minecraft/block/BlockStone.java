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
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ 
/*     */ public class BlockStone
/*     */   extends Block {
/*  18 */   public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000317";
/*     */   
/*     */   public BlockStone() {
/*  23 */     super(Material.rock);
/*  24 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT_PROP, EnumType.STONE));
/*  25 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  35 */     return (state.getValue((IProperty)VARIANT_PROP) == EnumType.STONE) ? Item.getItemFromBlock(Blocks.cobblestone) : Item.getItemFromBlock(Blocks.stone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  43 */     return ((EnumType)state.getValue((IProperty)VARIANT_PROP)).getMetaFromState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  51 */     EnumType[] var4 = EnumType.values();
/*  52 */     int var5 = var4.length;
/*     */     
/*  54 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/*  56 */       EnumType var7 = var4[var6];
/*  57 */       list.add(new ItemStack(itemIn, 1, var7.getMetaFromState()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  66 */     return getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.getStateFromMeta(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  74 */     return ((EnumType)state.getValue((IProperty)VARIANT_PROP)).getMetaFromState();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  79 */     return new BlockState(this, new IProperty[] { (IProperty)VARIANT_PROP });
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/*  84 */     STONE("STONE", 0, 0, "stone"),
/*  85 */     GRANITE("GRANITE", 1, 1, "granite"),
/*  86 */     GRANITE_SMOOTH("GRANITE_SMOOTH", 2, 2, "smooth_granite", "graniteSmooth"),
/*  87 */     DIORITE("DIORITE", 3, 3, "diorite"),
/*  88 */     DIORITE_SMOOTH("DIORITE_SMOOTH", 4, 4, "smooth_diorite", "dioriteSmooth"),
/*  89 */     ANDESITE("ANDESITE", 5, 5, "andesite"),
/*  90 */     ANDESITE_SMOOTH("ANDESITE_SMOOTH", 6, 6, "smooth_andesite", "andesiteSmooth");
/*  91 */     private static final EnumType[] BLOCKSTATES = new EnumType[(values()).length];
/*     */     
/*     */     private final int meta;
/*     */     private final String name;
/*     */     private final String field_176654_k;
/*  96 */     private static final EnumType[] $VALUES = new EnumType[] { STONE, GRANITE, GRANITE_SMOOTH, DIORITE, DIORITE_SMOOTH, ANDESITE, ANDESITE_SMOOTH };
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
/*     */     private static final String __OBFID = "CL_00002058";
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
/* 142 */       EnumType[] var0 = values();
/* 143 */       int var1 = var0.length;
/*     */       
/* 145 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 147 */         EnumType var3 = var0[var2];
/* 148 */         BLOCKSTATES[var3.getMetaFromState()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45681_1_, int p_i45681_2_, int p_i45681_3_, String p_i45681_4_, String p_i45681_5_) {
/*     */       this.meta = p_i45681_3_;
/*     */       this.name = p_i45681_4_;
/*     */       this.field_176654_k = p_i45681_5_;
/*     */     }
/*     */     
/*     */     public int getMetaFromState() {
/*     */       return this.meta;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.name;
/*     */     }
/*     */     
/*     */     public static EnumType getStateFromMeta(int p_176643_0_) {
/*     */       if (p_176643_0_ < 0 || p_176643_0_ >= BLOCKSTATES.length)
/*     */         p_176643_0_ = 0; 
/*     */       return BLOCKSTATES[p_176643_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.name;
/*     */     }
/*     */     
/*     */     public String func_176644_c() {
/*     */       return this.field_176654_k;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */