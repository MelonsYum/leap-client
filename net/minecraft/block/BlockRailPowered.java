/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockRailPowered
/*     */   extends BlockRailBase {
/*  14 */   public static final PropertyEnum field_176568_b = PropertyEnum.create("shape", BlockRailBase.EnumRailDirection.class, new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002080";
/*     */         
/*     */         public boolean func_180133_a(BlockRailBase.EnumRailDirection p_180133_1_) {
/*  19 */           return (p_180133_1_ != BlockRailBase.EnumRailDirection.NORTH_EAST && p_180133_1_ != BlockRailBase.EnumRailDirection.NORTH_WEST && p_180133_1_ != BlockRailBase.EnumRailDirection.SOUTH_EAST && p_180133_1_ != BlockRailBase.EnumRailDirection.SOUTH_WEST);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  23 */           return func_180133_a((BlockRailBase.EnumRailDirection)p_apply_1_);
/*     */         }
/*     */       });
/*  26 */   public static final PropertyBool field_176569_M = PropertyBool.create("powered");
/*     */   
/*     */   private static final String __OBFID = "CL_00000288";
/*     */   
/*     */   protected BlockRailPowered() {
/*  31 */     super(true);
/*  32 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176568_b, BlockRailBase.EnumRailDirection.NORTH_SOUTH).withProperty((IProperty)field_176569_M, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_176566_a(World worldIn, BlockPos p_176566_2_, IBlockState p_176566_3_, boolean p_176566_4_, int p_176566_5_) {
/*  37 */     if (p_176566_5_ >= 8)
/*     */     {
/*  39 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  43 */     int var6 = p_176566_2_.getX();
/*  44 */     int var7 = p_176566_2_.getY();
/*  45 */     int var8 = p_176566_2_.getZ();
/*  46 */     boolean var9 = true;
/*  47 */     BlockRailBase.EnumRailDirection var10 = (BlockRailBase.EnumRailDirection)p_176566_3_.getValue((IProperty)field_176568_b);
/*     */     
/*  49 */     switch (SwitchEnumRailDirection.field_180121_a[var10.ordinal()]) {
/*     */       
/*     */       case 1:
/*  52 */         if (p_176566_4_) {
/*     */           
/*  54 */           var8++;
/*     */           
/*     */           break;
/*     */         } 
/*  58 */         var8--;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/*  64 */         if (p_176566_4_) {
/*     */           
/*  66 */           var6--;
/*     */           
/*     */           break;
/*     */         } 
/*  70 */         var6++;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/*  76 */         if (p_176566_4_) {
/*     */           
/*  78 */           var6--;
/*     */         }
/*     */         else {
/*     */           
/*  82 */           var6++;
/*  83 */           var7++;
/*  84 */           var9 = false;
/*     */         } 
/*     */         
/*  87 */         var10 = BlockRailBase.EnumRailDirection.EAST_WEST;
/*     */         break;
/*     */       
/*     */       case 4:
/*  91 */         if (p_176566_4_) {
/*     */           
/*  93 */           var6--;
/*  94 */           var7++;
/*  95 */           var9 = false;
/*     */         }
/*     */         else {
/*     */           
/*  99 */           var6++;
/*     */         } 
/*     */         
/* 102 */         var10 = BlockRailBase.EnumRailDirection.EAST_WEST;
/*     */         break;
/*     */       
/*     */       case 5:
/* 106 */         if (p_176566_4_) {
/*     */           
/* 108 */           var8++;
/*     */         }
/*     */         else {
/*     */           
/* 112 */           var8--;
/* 113 */           var7++;
/* 114 */           var9 = false;
/*     */         } 
/*     */         
/* 117 */         var10 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/*     */         break;
/*     */       
/*     */       case 6:
/* 121 */         if (p_176566_4_) {
/*     */           
/* 123 */           var8++;
/* 124 */           var7++;
/* 125 */           var9 = false;
/*     */         }
/*     */         else {
/*     */           
/* 129 */           var8--;
/*     */         } 
/*     */         
/* 132 */         var10 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
/*     */         break;
/*     */     } 
/* 135 */     return func_176567_a(worldIn, new BlockPos(var6, var7, var8), p_176566_4_, p_176566_5_, var10) ? true : ((var9 && func_176567_a(worldIn, new BlockPos(var6, var7 - 1, var8), p_176566_4_, p_176566_5_, var10)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_176567_a(World worldIn, BlockPos p_176567_2_, boolean p_176567_3_, int p_176567_4_, BlockRailBase.EnumRailDirection p_176567_5_) {
/* 141 */     IBlockState var6 = worldIn.getBlockState(p_176567_2_);
/*     */     
/* 143 */     if (var6.getBlock() != this)
/*     */     {
/* 145 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 149 */     BlockRailBase.EnumRailDirection var7 = (BlockRailBase.EnumRailDirection)var6.getValue((IProperty)field_176568_b);
/* 150 */     return (p_176567_5_ == BlockRailBase.EnumRailDirection.EAST_WEST && (var7 == BlockRailBase.EnumRailDirection.NORTH_SOUTH || var7 == BlockRailBase.EnumRailDirection.ASCENDING_NORTH || var7 == BlockRailBase.EnumRailDirection.ASCENDING_SOUTH)) ? false : ((p_176567_5_ == BlockRailBase.EnumRailDirection.NORTH_SOUTH && (var7 == BlockRailBase.EnumRailDirection.EAST_WEST || var7 == BlockRailBase.EnumRailDirection.ASCENDING_EAST || var7 == BlockRailBase.EnumRailDirection.ASCENDING_WEST)) ? false : (((Boolean)var6.getValue((IProperty)field_176569_M)).booleanValue() ? (worldIn.isBlockPowered(p_176567_2_) ? true : func_176566_a(worldIn, p_176567_2_, var6, p_176567_3_, p_176567_4_ + 1)) : false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_176561_b(World worldIn, BlockPos p_176561_2_, IBlockState p_176561_3_, Block p_176561_4_) {
/* 156 */     boolean var5 = ((Boolean)p_176561_3_.getValue((IProperty)field_176569_M)).booleanValue();
/* 157 */     boolean var6 = !(!worldIn.isBlockPowered(p_176561_2_) && !func_176566_a(worldIn, p_176561_2_, p_176561_3_, true, 0) && !func_176566_a(worldIn, p_176561_2_, p_176561_3_, false, 0));
/*     */     
/* 159 */     if (var6 != var5) {
/*     */       
/* 161 */       worldIn.setBlockState(p_176561_2_, p_176561_3_.withProperty((IProperty)field_176569_M, Boolean.valueOf(var6)), 3);
/* 162 */       worldIn.notifyNeighborsOfStateChange(p_176561_2_.offsetDown(), this);
/*     */       
/* 164 */       if (((BlockRailBase.EnumRailDirection)p_176561_3_.getValue((IProperty)field_176568_b)).func_177018_c())
/*     */       {
/* 166 */         worldIn.notifyNeighborsOfStateChange(p_176561_2_.offsetUp(), this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IProperty func_176560_l() {
/* 173 */     return (IProperty)field_176568_b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 181 */     return getDefaultState().withProperty((IProperty)field_176568_b, BlockRailBase.EnumRailDirection.func_177016_a(meta & 0x7)).withProperty((IProperty)field_176569_M, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 189 */     byte var2 = 0;
/* 190 */     int var3 = var2 | ((BlockRailBase.EnumRailDirection)state.getValue((IProperty)field_176568_b)).func_177015_a();
/*     */     
/* 192 */     if (((Boolean)state.getValue((IProperty)field_176569_M)).booleanValue())
/*     */     {
/* 194 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 197 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 202 */     return new BlockState(this, new IProperty[] { (IProperty)field_176568_b, (IProperty)field_176569_M });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumRailDirection
/*     */   {
/* 207 */     static final int[] field_180121_a = new int[(BlockRailBase.EnumRailDirection.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002079";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 214 */         field_180121_a[BlockRailBase.EnumRailDirection.NORTH_SOUTH.ordinal()] = 1;
/*     */       }
/* 216 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 223 */         field_180121_a[BlockRailBase.EnumRailDirection.EAST_WEST.ordinal()] = 2;
/*     */       }
/* 225 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 232 */         field_180121_a[BlockRailBase.EnumRailDirection.ASCENDING_EAST.ordinal()] = 3;
/*     */       }
/* 234 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 241 */         field_180121_a[BlockRailBase.EnumRailDirection.ASCENDING_WEST.ordinal()] = 4;
/*     */       }
/* 243 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 250 */         field_180121_a[BlockRailBase.EnumRailDirection.ASCENDING_NORTH.ordinal()] = 5;
/*     */       }
/* 252 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 259 */         field_180121_a[BlockRailBase.EnumRailDirection.ASCENDING_SOUTH.ordinal()] = 6;
/*     */       }
/* 261 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRailPowered.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */