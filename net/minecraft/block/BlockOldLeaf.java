/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.ColorizerFoliage;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockOldLeaf
/*     */   extends BlockLeaves {
/*  23 */   public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002085";
/*     */         
/*     */         public boolean func_180202_a(BlockPlanks.EnumType p_180202_1_) {
/*  28 */           return (p_180202_1_.func_176839_a() < 4);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  32 */           return func_180202_a((BlockPlanks.EnumType)p_apply_1_);
/*     */         }
/*     */       });
/*     */   
/*     */   private static final String __OBFID = "CL_00000280";
/*     */   
/*     */   public BlockOldLeaf() {
/*  39 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT_PROP, BlockPlanks.EnumType.OAK).withProperty((IProperty)field_176236_b, Boolean.valueOf(true)).withProperty((IProperty)field_176237_a, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderColor(IBlockState state) {
/*  44 */     if (state.getBlock() != this)
/*     */     {
/*  46 */       return super.getRenderColor(state);
/*     */     }
/*     */ 
/*     */     
/*  50 */     BlockPlanks.EnumType var2 = (BlockPlanks.EnumType)state.getValue((IProperty)VARIANT_PROP);
/*  51 */     return (var2 == BlockPlanks.EnumType.SPRUCE) ? ColorizerFoliage.getFoliageColorPine() : ((var2 == BlockPlanks.EnumType.BIRCH) ? ColorizerFoliage.getFoliageColorBirch() : super.getRenderColor(state));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/*  57 */     IBlockState var4 = worldIn.getBlockState(pos);
/*     */     
/*  59 */     if (var4.getBlock() == this) {
/*     */       
/*  61 */       BlockPlanks.EnumType var5 = (BlockPlanks.EnumType)var4.getValue((IProperty)VARIANT_PROP);
/*     */       
/*  63 */       if (var5 == BlockPlanks.EnumType.SPRUCE)
/*     */       {
/*  65 */         return ColorizerFoliage.getFoliageColorPine();
/*     */       }
/*     */       
/*  68 */       if (var5 == BlockPlanks.EnumType.BIRCH)
/*     */       {
/*  70 */         return ColorizerFoliage.getFoliageColorBirch();
/*     */       }
/*     */     } 
/*     */     
/*  74 */     return super.colorMultiplier(worldIn, pos, renderPass);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_176234_a(World worldIn, BlockPos p_176234_2_, IBlockState p_176234_3_, int p_176234_4_) {
/*  79 */     if (p_176234_3_.getValue((IProperty)VARIANT_PROP) == BlockPlanks.EnumType.OAK && worldIn.rand.nextInt(p_176234_4_) == 0)
/*     */     {
/*  81 */       spawnAsEntity(worldIn, p_176234_2_, new ItemStack(Items.apple, 1, 0));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176232_d(IBlockState p_176232_1_) {
/*  87 */     return (p_176232_1_.getValue((IProperty)VARIANT_PROP) == BlockPlanks.EnumType.JUNGLE) ? 40 : super.func_176232_d(p_176232_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  95 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.OAK.func_176839_a()));
/*  96 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.SPRUCE.func_176839_a()));
/*  97 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.BIRCH.func_176839_a()));
/*  98 */     list.add(new ItemStack(itemIn, 1, BlockPlanks.EnumType.JUNGLE.func_176839_a()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack createStackedBlock(IBlockState state) {
/* 103 */     return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176839_a());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 111 */     return getDefaultState().withProperty((IProperty)VARIANT_PROP, func_176233_b(meta)).withProperty((IProperty)field_176237_a, Boolean.valueOf(((meta & 0x4) == 0))).withProperty((IProperty)field_176236_b, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 119 */     byte var2 = 0;
/* 120 */     int var3 = var2 | ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176839_a();
/*     */     
/* 122 */     if (!((Boolean)state.getValue((IProperty)field_176237_a)).booleanValue())
/*     */     {
/* 124 */       var3 |= 0x4;
/*     */     }
/*     */     
/* 127 */     if (((Boolean)state.getValue((IProperty)field_176236_b)).booleanValue())
/*     */     {
/* 129 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 132 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPlanks.EnumType func_176233_b(int p_176233_1_) {
/* 137 */     return BlockPlanks.EnumType.func_176837_a((p_176233_1_ & 0x3) % 4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 142 */     return new BlockState(this, new IProperty[] { (IProperty)VARIANT_PROP, (IProperty)field_176236_b, (IProperty)field_176237_a });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 150 */     return ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176839_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te) {
/* 155 */     if (!worldIn.isRemote && playerIn.getCurrentEquippedItem() != null && playerIn.getCurrentEquippedItem().getItem() == Items.shears) {
/*     */       
/* 157 */       playerIn.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
/* 158 */       spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176839_a()));
/*     */     }
/*     */     else {
/*     */       
/* 162 */       super.harvestBlock(worldIn, playerIn, pos, state, te);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockOldLeaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */