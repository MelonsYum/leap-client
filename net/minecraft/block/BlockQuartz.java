/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.MapColor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockQuartz
/*     */   extends Block {
/*  21 */   public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000292";
/*     */   
/*     */   public BlockQuartz() {
/*  26 */     super(Material.rock);
/*  27 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT_PROP, EnumType.DEFAULT));
/*  28 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  33 */     if (meta == EnumType.LINES_Y.getMetaFromState()) {
/*     */       
/*  35 */       switch (SwitchAxis.field_180101_a[facing.getAxis().ordinal()]) {
/*     */         
/*     */         case 1:
/*  38 */           return getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.LINES_Z);
/*     */         
/*     */         case 2:
/*  41 */           return getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.LINES_X);
/*     */       } 
/*     */ 
/*     */       
/*  45 */       return getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.LINES_Y);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  50 */     return (meta == EnumType.CHISELED.getMetaFromState()) ? getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.CHISELED) : getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  59 */     EnumType var2 = (EnumType)state.getValue((IProperty)VARIANT_PROP);
/*  60 */     return (var2 != EnumType.LINES_X && var2 != EnumType.LINES_Z) ? var2.getMetaFromState() : EnumType.LINES_Y.getMetaFromState();
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack createStackedBlock(IBlockState state) {
/*  65 */     EnumType var2 = (EnumType)state.getValue((IProperty)VARIANT_PROP);
/*  66 */     return (var2 != EnumType.LINES_X && var2 != EnumType.LINES_Z) ? super.createStackedBlock(state) : new ItemStack(Item.getItemFromBlock(this), 1, EnumType.LINES_Y.getMetaFromState());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  74 */     list.add(new ItemStack(itemIn, 1, EnumType.DEFAULT.getMetaFromState()));
/*  75 */     list.add(new ItemStack(itemIn, 1, EnumType.CHISELED.getMetaFromState()));
/*  76 */     list.add(new ItemStack(itemIn, 1, EnumType.LINES_Y.getMetaFromState()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapColor getMapColor(IBlockState state) {
/*  84 */     return MapColor.quartzColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  92 */     return getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.func_176794_a(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 100 */     return ((EnumType)state.getValue((IProperty)VARIANT_PROP)).getMetaFromState();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 105 */     return new BlockState(this, new IProperty[] { (IProperty)VARIANT_PROP });
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/* 110 */     DEFAULT("DEFAULT", 0, 0, "default", "default"),
/* 111 */     CHISELED("CHISELED", 1, 1, "chiseled", "chiseled"),
/* 112 */     LINES_Y("LINES_Y", 2, 2, "lines_y", "lines"),
/* 113 */     LINES_X("LINES_X", 3, 3, "lines_x", "lines"),
/* 114 */     LINES_Z("LINES_Z", 4, 4, "lines_z", "lines");
/* 115 */     private static final EnumType[] TYPES_ARRAY = new EnumType[(values()).length];
/*     */     
/*     */     private final int field_176798_g;
/*     */     private final String field_176805_h;
/*     */     private final String field_176806_i;
/* 120 */     private static final EnumType[] $VALUES = new EnumType[] { DEFAULT, CHISELED, LINES_Y, LINES_X, LINES_Z };
/*     */     
/*     */     private static final String __OBFID = "CL_00002074";
/*     */ 
/*     */     
/*     */     EnumType(String p_i45691_1_, int p_i45691_2_, int p_i45691_3_, String p_i45691_4_, String p_i45691_5_) {
/*     */       this.field_176798_g = p_i45691_3_;
/*     */       this.field_176805_h = p_i45691_4_;
/*     */       this.field_176806_i = p_i45691_5_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMetaFromState() {
/*     */       return this.field_176798_g;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176806_i;
/*     */     }
/*     */ 
/*     */     
/*     */     public static EnumType func_176794_a(int p_176794_0_) {
/*     */       if (p_176794_0_ < 0 || p_176794_0_ >= TYPES_ARRAY.length) {
/*     */         p_176794_0_ = 0;
/*     */       }
/*     */       return TYPES_ARRAY[p_176794_0_];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176805_h;
/*     */     }
/*     */ 
/*     */     
/*     */     static {
/* 156 */       EnumType[] var0 = values();
/* 157 */       int var1 = var0.length;
/*     */       
/* 159 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 161 */         EnumType var3 = var0[var2];
/* 162 */         TYPES_ARRAY[var3.getMetaFromState()] = var3;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchAxis
/*     */   {
/* 169 */     static final int[] field_180101_a = new int[(EnumFacing.Axis.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002075";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 176 */         field_180101_a[EnumFacing.Axis.Z.ordinal()] = 1;
/*     */       }
/* 178 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 185 */         field_180101_a[EnumFacing.Axis.X.ordinal()] = 2;
/*     */       }
/* 187 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 194 */         field_180101_a[EnumFacing.Axis.Y.ordinal()] = 3;
/*     */       }
/* 196 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockQuartz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */