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
/*     */ public class BlockSandStone
/*     */   extends Block {
/*  16 */   public static final PropertyEnum field_176297_a = PropertyEnum.create("type", EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000304";
/*     */   
/*     */   public BlockSandStone() {
/*  21 */     super(Material.rock);
/*  22 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176297_a, EnumType.DEFAULT));
/*  23 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  31 */     return ((EnumType)state.getValue((IProperty)field_176297_a)).func_176675_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  39 */     EnumType[] var4 = EnumType.values();
/*  40 */     int var5 = var4.length;
/*     */     
/*  42 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/*  44 */       EnumType var7 = var4[var6];
/*  45 */       list.add(new ItemStack(itemIn, 1, var7.func_176675_a()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  54 */     return getDefaultState().withProperty((IProperty)field_176297_a, EnumType.func_176673_a(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  62 */     return ((EnumType)state.getValue((IProperty)field_176297_a)).func_176675_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  67 */     return new BlockState(this, new IProperty[] { (IProperty)field_176297_a });
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/*  72 */     DEFAULT("DEFAULT", 0, 0, "sandstone", "default"),
/*  73 */     CHISELED("CHISELED", 1, 1, "chiseled_sandstone", "chiseled"),
/*  74 */     SMOOTH("SMOOTH", 2, 2, "smooth_sandstone", "smooth");
/*  75 */     private static final EnumType[] field_176679_d = new EnumType[(values()).length];
/*     */     
/*     */     private final int field_176680_e;
/*     */     private final String field_176677_f;
/*     */     private final String field_176678_g;
/*  80 */     private static final EnumType[] $VALUES = new EnumType[] { DEFAULT, CHISELED, SMOOTH };
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
/*     */     private static final String __OBFID = "CL_00002068";
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
/* 121 */       EnumType[] var0 = values();
/* 122 */       int var1 = var0.length;
/*     */       
/* 124 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 126 */         EnumType var3 = var0[var2];
/* 127 */         field_176679_d[var3.func_176675_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45686_1_, int p_i45686_2_, int p_i45686_3_, String p_i45686_4_, String p_i45686_5_) {
/*     */       this.field_176680_e = p_i45686_3_;
/*     */       this.field_176677_f = p_i45686_4_;
/*     */       this.field_176678_g = p_i45686_5_;
/*     */     }
/*     */     
/*     */     public int func_176675_a() {
/*     */       return this.field_176680_e;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176677_f;
/*     */     }
/*     */     
/*     */     public static EnumType func_176673_a(int p_176673_0_) {
/*     */       if (p_176673_0_ < 0 || p_176673_0_ >= field_176679_d.length)
/*     */         p_176673_0_ = 0; 
/*     */       return field_176679_d[p_176673_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176677_f;
/*     */     }
/*     */     
/*     */     public String func_176676_c() {
/*     */       return this.field_176678_g;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSandStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */