/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ public class BlockOldLog
/*     */   extends BlockLog {
/*  15 */   public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002084";
/*     */         
/*     */         public boolean func_180200_a(BlockPlanks.EnumType p_180200_1_) {
/*  20 */           return (p_180200_1_.func_176839_a() < 4);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  24 */           return func_180200_a((BlockPlanks.EnumType)p_apply_1_);
/*     */         }
/*     */       });
/*     */   
/*     */   private static final String __OBFID = "CL_00000281";
/*     */   
/*     */   public BlockOldLog() {
/*  31 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT_PROP, BlockPlanks.EnumType.OAK).withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.Y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  39 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.OAK.func_176839_a()));
/*  40 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.SPRUCE.func_176839_a()));
/*  41 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.BIRCH.func_176839_a()));
/*  42 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.JUNGLE.func_176839_a()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  50 */     IBlockState var2 = getDefaultState().withProperty((IProperty)VARIANT_PROP, BlockPlanks.EnumType.func_176837_a((meta & 0x3) % 4));
/*     */     
/*  52 */     switch (meta & 0xC)
/*     */     
/*     */     { case 0:
/*  55 */         var2 = var2.withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.Y);
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
/*  70 */         return var2;case 4: var2 = var2.withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.X); return var2;case 8: var2 = var2.withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.Z); return var2; }  var2 = var2.withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.NONE); return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  78 */     byte var2 = 0;
/*  79 */     int var3 = var2 | ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176839_a();
/*     */     
/*  81 */     switch (SwitchEnumAxis.field_180203_a[((BlockLog.EnumAxis)state.getValue((IProperty)AXIS_PROP)).ordinal()]) {
/*     */       
/*     */       case 1:
/*  84 */         var3 |= 0x4;
/*     */         break;
/*     */       
/*     */       case 2:
/*  88 */         var3 |= 0x8;
/*     */         break;
/*     */       
/*     */       case 3:
/*  92 */         var3 |= 0xC;
/*     */         break;
/*     */     } 
/*  95 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 100 */     return new BlockState(this, new IProperty[] { (IProperty)VARIANT_PROP, (IProperty)AXIS_PROP });
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack createStackedBlock(IBlockState state) {
/* 105 */     return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176839_a());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 113 */     return ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176839_a();
/*     */   }
/*     */   
/*     */   static final class SwitchEnumAxis
/*     */   {
/* 118 */     static final int[] field_180203_a = new int[(BlockLog.EnumAxis.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002083";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 125 */         field_180203_a[BlockLog.EnumAxis.X.ordinal()] = 1;
/*     */       }
/* 127 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 134 */         field_180203_a[BlockLog.EnumAxis.Z.ordinal()] = 2;
/*     */       }
/* 136 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 143 */         field_180203_a[BlockLog.EnumAxis.NONE.ordinal()] = 3;
/*     */       }
/* 145 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockOldLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */