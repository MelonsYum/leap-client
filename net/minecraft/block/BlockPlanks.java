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
/*     */ public class BlockPlanks
/*     */   extends Block {
/*  16 */   public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00002082";
/*     */   
/*     */   public BlockPlanks() {
/*  21 */     super(Material.wood);
/*  22 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT_PROP, EnumType.OAK));
/*  23 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  31 */     return ((EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176839_a();
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
/*  45 */       list.add(new ItemStack(itemIn, 1, var7.func_176839_a()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  54 */     return getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.func_176837_a(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  62 */     return ((EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176839_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  67 */     return new BlockState(this, new IProperty[] { (IProperty)VARIANT_PROP });
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/*  72 */     OAK("OAK", 0, 0, "oak"),
/*  73 */     SPRUCE("SPRUCE", 1, 1, "spruce"),
/*  74 */     BIRCH("BIRCH", 2, 2, "birch"),
/*  75 */     JUNGLE("JUNGLE", 3, 3, "jungle"),
/*  76 */     ACACIA("ACACIA", 4, 4, "acacia"),
/*  77 */     DARK_OAK("DARK_OAK", 5, 5, "dark_oak", "big_oak");
/*  78 */     private static final EnumType[] field_176842_g = new EnumType[(values()).length];
/*     */     
/*     */     private final int field_176850_h;
/*     */     private final String field_176851_i;
/*     */     private final String field_176848_j;
/*  83 */     private static final EnumType[] $VALUES = new EnumType[] { OAK, SPRUCE, BIRCH, JUNGLE, ACACIA, DARK_OAK };
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
/*     */     private static final String __OBFID = "CL_00002081";
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
/* 129 */       EnumType[] var0 = values();
/* 130 */       int var1 = var0.length;
/*     */       
/* 132 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 134 */         EnumType var3 = var0[var2];
/* 135 */         field_176842_g[var3.func_176839_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45696_1_, int p_i45696_2_, int p_i45696_3_, String p_i45696_4_, String p_i45696_5_) {
/*     */       this.field_176850_h = p_i45696_3_;
/*     */       this.field_176851_i = p_i45696_4_;
/*     */       this.field_176848_j = p_i45696_5_;
/*     */     }
/*     */     
/*     */     public int func_176839_a() {
/*     */       return this.field_176850_h;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176851_i;
/*     */     }
/*     */     
/*     */     public static EnumType func_176837_a(int p_176837_0_) {
/*     */       if (p_176837_0_ < 0 || p_176837_0_ >= field_176842_g.length)
/*     */         p_176837_0_ = 0; 
/*     */       return field_176842_g[p_176837_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176851_i;
/*     */     }
/*     */     
/*     */     public String func_176840_c() {
/*     */       return this.field_176848_j;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPlanks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */