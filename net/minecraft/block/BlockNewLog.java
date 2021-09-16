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
/*     */ public class BlockNewLog
/*     */   extends BlockLog {
/*  15 */   public static final PropertyEnum field_176300_b = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002089";
/*     */         
/*     */         public boolean func_180194_a(BlockPlanks.EnumType p_180194_1_) {
/*  20 */           return (p_180194_1_.func_176839_a() >= 4);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  24 */           return func_180194_a((BlockPlanks.EnumType)p_apply_1_);
/*     */         }
/*     */       });
/*     */   
/*     */   private static final String __OBFID = "CL_00000277";
/*     */   
/*     */   public BlockNewLog() {
/*  31 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176300_b, BlockPlanks.EnumType.ACACIA).withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.Y));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  39 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.ACACIA.func_176839_a() - 4));
/*  40 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.DARK_OAK.func_176839_a() - 4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  48 */     IBlockState var2 = getDefaultState().withProperty((IProperty)field_176300_b, BlockPlanks.EnumType.func_176837_a((meta & 0x3) + 4));
/*     */     
/*  50 */     switch (meta & 0xC)
/*     */     
/*     */     { case 0:
/*  53 */         var2 = var2.withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.Y);
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
/*  68 */         return var2;case 4: var2 = var2.withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.X); return var2;case 8: var2 = var2.withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.Z); return var2; }  var2 = var2.withProperty((IProperty)AXIS_PROP, BlockLog.EnumAxis.NONE); return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  76 */     byte var2 = 0;
/*  77 */     int var3 = var2 | ((BlockPlanks.EnumType)state.getValue((IProperty)field_176300_b)).func_176839_a() - 4;
/*     */     
/*  79 */     switch (SwitchEnumAxis.field_180191_a[((BlockLog.EnumAxis)state.getValue((IProperty)AXIS_PROP)).ordinal()]) {
/*     */       
/*     */       case 1:
/*  82 */         var3 |= 0x4;
/*     */         break;
/*     */       
/*     */       case 2:
/*  86 */         var3 |= 0x8;
/*     */         break;
/*     */       
/*     */       case 3:
/*  90 */         var3 |= 0xC;
/*     */         break;
/*     */     } 
/*  93 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  98 */     return new BlockState(this, new IProperty[] { (IProperty)field_176300_b, (IProperty)AXIS_PROP });
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack createStackedBlock(IBlockState state) {
/* 103 */     return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue((IProperty)field_176300_b)).func_176839_a() - 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 111 */     return ((BlockPlanks.EnumType)state.getValue((IProperty)field_176300_b)).func_176839_a() - 4;
/*     */   }
/*     */   
/*     */   static final class SwitchEnumAxis
/*     */   {
/* 116 */     static final int[] field_180191_a = new int[(BlockLog.EnumAxis.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002088";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 123 */         field_180191_a[BlockLog.EnumAxis.X.ordinal()] = 1;
/*     */       }
/* 125 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 132 */         field_180191_a[BlockLog.EnumAxis.Z.ordinal()] = 2;
/*     */       }
/* 134 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 141 */         field_180191_a[BlockLog.EnumAxis.NONE.ordinal()] = 3;
/*     */       }
/* 143 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockNewLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */