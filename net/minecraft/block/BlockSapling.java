/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenCanopyTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenForest;
/*     */ import net.minecraft.world.gen.feature.WorldGenMegaJungle;
/*     */ import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenSavannaTree;
/*     */ import net.minecraft.world.gen.feature.WorldGenTaiga2;
/*     */ import net.minecraft.world.gen.feature.WorldGenTrees;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ 
/*     */ public class BlockSapling
/*     */   extends BlockBush implements IGrowable {
/*  28 */   public static final PropertyEnum TYPE_PROP = PropertyEnum.create("type", BlockPlanks.EnumType.class);
/*  29 */   public static final PropertyInteger STAGE_PROP = PropertyInteger.create("stage", 0, 1);
/*     */   
/*     */   private static final String __OBFID = "CL_00000305";
/*     */   
/*     */   protected BlockSapling() {
/*  34 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)TYPE_PROP, BlockPlanks.EnumType.OAK).withProperty((IProperty)STAGE_PROP, Integer.valueOf(0)));
/*  35 */     float var1 = 0.4F;
/*  36 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var1 * 2.0F, 0.5F + var1);
/*  37 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  42 */     if (!worldIn.isRemote) {
/*     */       
/*  44 */       super.updateTick(worldIn, pos, state, rand);
/*     */       
/*  46 */       if (worldIn.getLightFromNeighbors(pos.offsetUp()) >= 9 && rand.nextInt(7) == 0)
/*     */       {
/*  48 */         func_176478_d(worldIn, pos, state, rand);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176478_d(World worldIn, BlockPos p_176478_2_, IBlockState p_176478_3_, Random p_176478_4_) {
/*  55 */     if (((Integer)p_176478_3_.getValue((IProperty)STAGE_PROP)).intValue() == 0) {
/*     */       
/*  57 */       worldIn.setBlockState(p_176478_2_, p_176478_3_.cycleProperty((IProperty)STAGE_PROP), 4);
/*     */     }
/*     */     else {
/*     */       
/*  61 */       func_176476_e(worldIn, p_176478_2_, p_176478_3_, p_176478_4_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176476_e(World worldIn, BlockPos p_176476_2_, IBlockState p_176476_3_, Random p_176476_4_) {
/*  67 */     Object var5 = (p_176476_4_.nextInt(10) == 0) ? new WorldGenBigTree(true) : new WorldGenTrees(true);
/*  68 */     int var6 = 0;
/*  69 */     int var7 = 0;
/*  70 */     boolean var8 = false;
/*     */     
/*  72 */     switch (SwitchEnumType.field_177065_a[((BlockPlanks.EnumType)p_176476_3_.getValue((IProperty)TYPE_PROP)).ordinal()]) {
/*     */ 
/*     */       
/*     */       case 1:
/*  76 */         label77: for (var6 = 0; var6 >= -1; var6--) {
/*     */           
/*  78 */           for (var7 = 0; var7 >= -1; var7--) {
/*     */             
/*  80 */             if (func_176477_a(worldIn, p_176476_2_.add(var6, 0, var7), BlockPlanks.EnumType.SPRUCE) && func_176477_a(worldIn, p_176476_2_.add(var6 + 1, 0, var7), BlockPlanks.EnumType.SPRUCE) && func_176477_a(worldIn, p_176476_2_.add(var6, 0, var7 + 1), BlockPlanks.EnumType.SPRUCE) && func_176477_a(worldIn, p_176476_2_.add(var6 + 1, 0, var7 + 1), BlockPlanks.EnumType.SPRUCE)) {
/*     */               
/*  82 */               var5 = new WorldGenMegaPineTree(false, p_176476_4_.nextBoolean());
/*  83 */               var8 = true;
/*     */               
/*     */               break label77;
/*     */             } 
/*     */           } 
/*     */         } 
/*  89 */         if (!var8) {
/*     */           
/*  91 */           var7 = 0;
/*  92 */           var6 = 0;
/*  93 */           var5 = new WorldGenTaiga2(true);
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*  99 */         var5 = new WorldGenForest(true, false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 104 */         label78: for (var6 = 0; var6 >= -1; var6--) {
/*     */           
/* 106 */           for (var7 = 0; var7 >= -1; var7--) {
/*     */             
/* 108 */             if (func_176477_a(worldIn, p_176476_2_.add(var6, 0, var7), BlockPlanks.EnumType.JUNGLE) && func_176477_a(worldIn, p_176476_2_.add(var6 + 1, 0, var7), BlockPlanks.EnumType.JUNGLE) && func_176477_a(worldIn, p_176476_2_.add(var6, 0, var7 + 1), BlockPlanks.EnumType.JUNGLE) && func_176477_a(worldIn, p_176476_2_.add(var6 + 1, 0, var7 + 1), BlockPlanks.EnumType.JUNGLE)) {
/*     */               
/* 110 */               var5 = new WorldGenMegaJungle(true, 10, 20, BlockPlanks.EnumType.JUNGLE.func_176839_a(), BlockPlanks.EnumType.JUNGLE.func_176839_a());
/* 111 */               var8 = true;
/*     */               
/*     */               break label78;
/*     */             } 
/*     */           } 
/*     */         } 
/* 117 */         if (!var8) {
/*     */           
/* 119 */           var7 = 0;
/* 120 */           var6 = 0;
/* 121 */           var5 = new WorldGenTrees(true, 4 + p_176476_4_.nextInt(7), BlockPlanks.EnumType.JUNGLE.func_176839_a(), BlockPlanks.EnumType.JUNGLE.func_176839_a(), false);
/*     */         } 
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/* 127 */         var5 = new WorldGenSavannaTree(true);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 5:
/* 132 */         label79: for (var6 = 0; var6 >= -1; var6--) {
/*     */           
/* 134 */           for (var7 = 0; var7 >= -1; var7--) {
/*     */             
/* 136 */             if (func_176477_a(worldIn, p_176476_2_.add(var6, 0, var7), BlockPlanks.EnumType.DARK_OAK) && func_176477_a(worldIn, p_176476_2_.add(var6 + 1, 0, var7), BlockPlanks.EnumType.DARK_OAK) && func_176477_a(worldIn, p_176476_2_.add(var6, 0, var7 + 1), BlockPlanks.EnumType.DARK_OAK) && func_176477_a(worldIn, p_176476_2_.add(var6 + 1, 0, var7 + 1), BlockPlanks.EnumType.DARK_OAK)) {
/*     */               
/* 138 */               var5 = new WorldGenCanopyTree(true);
/* 139 */               var8 = true;
/*     */               
/*     */               break label79;
/*     */             } 
/*     */           } 
/*     */         } 
/* 145 */         if (!var8) {
/*     */           return;
/*     */         }
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 153 */     IBlockState var9 = Blocks.air.getDefaultState();
/*     */     
/* 155 */     if (var8) {
/*     */       
/* 157 */       worldIn.setBlockState(p_176476_2_.add(var6, 0, var7), var9, 4);
/* 158 */       worldIn.setBlockState(p_176476_2_.add(var6 + 1, 0, var7), var9, 4);
/* 159 */       worldIn.setBlockState(p_176476_2_.add(var6, 0, var7 + 1), var9, 4);
/* 160 */       worldIn.setBlockState(p_176476_2_.add(var6 + 1, 0, var7 + 1), var9, 4);
/*     */     }
/*     */     else {
/*     */       
/* 164 */       worldIn.setBlockState(p_176476_2_, var9, 4);
/*     */     } 
/*     */     
/* 167 */     if (!((WorldGenerator)var5).generate(worldIn, p_176476_4_, p_176476_2_.add(var6, 0, var7)))
/*     */     {
/* 169 */       if (var8) {
/*     */         
/* 171 */         worldIn.setBlockState(p_176476_2_.add(var6, 0, var7), p_176476_3_, 4);
/* 172 */         worldIn.setBlockState(p_176476_2_.add(var6 + 1, 0, var7), p_176476_3_, 4);
/* 173 */         worldIn.setBlockState(p_176476_2_.add(var6, 0, var7 + 1), p_176476_3_, 4);
/* 174 */         worldIn.setBlockState(p_176476_2_.add(var6 + 1, 0, var7 + 1), p_176476_3_, 4);
/*     */       }
/*     */       else {
/*     */         
/* 178 */         worldIn.setBlockState(p_176476_2_, p_176476_3_, 4);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176477_a(World worldIn, BlockPos p_176477_2_, BlockPlanks.EnumType p_176477_3_) {
/* 185 */     IBlockState var4 = worldIn.getBlockState(p_176477_2_);
/* 186 */     return (var4.getBlock() == this && var4.getValue((IProperty)TYPE_PROP) == p_176477_3_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 194 */     return ((BlockPlanks.EnumType)state.getValue((IProperty)TYPE_PROP)).func_176839_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/* 202 */     BlockPlanks.EnumType[] var4 = BlockPlanks.EnumType.values();
/* 203 */     int var5 = var4.length;
/*     */     
/* 205 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 207 */       BlockPlanks.EnumType var7 = var4[var6];
/* 208 */       list.add(new ItemStack(itemIn, 1, var7.func_176839_a()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
/* 214 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
/* 219 */     return (worldIn.rand.nextFloat() < 0.45D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
/* 224 */     func_176478_d(worldIn, p_176474_3_, p_176474_4_, p_176474_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 232 */     return getDefaultState().withProperty((IProperty)TYPE_PROP, BlockPlanks.EnumType.func_176837_a(meta & 0x7)).withProperty((IProperty)STAGE_PROP, Integer.valueOf((meta & 0x8) >> 3));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 240 */     byte var2 = 0;
/* 241 */     int var3 = var2 | ((BlockPlanks.EnumType)state.getValue((IProperty)TYPE_PROP)).func_176839_a();
/* 242 */     var3 |= ((Integer)state.getValue((IProperty)STAGE_PROP)).intValue() << 3;
/* 243 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 248 */     return new BlockState(this, new IProperty[] { (IProperty)TYPE_PROP, (IProperty)STAGE_PROP });
/*     */   }
/*     */   
/*     */   static final class SwitchEnumType
/*     */   {
/* 253 */     static final int[] field_177065_a = new int[(BlockPlanks.EnumType.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002067";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 260 */         field_177065_a[BlockPlanks.EnumType.SPRUCE.ordinal()] = 1;
/*     */       }
/* 262 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 269 */         field_177065_a[BlockPlanks.EnumType.BIRCH.ordinal()] = 2;
/*     */       }
/* 271 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 278 */         field_177065_a[BlockPlanks.EnumType.JUNGLE.ordinal()] = 3;
/*     */       }
/* 280 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 287 */         field_177065_a[BlockPlanks.EnumType.ACACIA.ordinal()] = 4;
/*     */       }
/* 289 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 296 */         field_177065_a[BlockPlanks.EnumType.DARK_OAK.ordinal()] = 5;
/*     */       }
/* 298 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 305 */         field_177065_a[BlockPlanks.EnumType.OAK.ordinal()] = 6;
/*     */       }
/* 307 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */