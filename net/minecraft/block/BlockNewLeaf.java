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
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockNewLeaf
/*     */   extends BlockLeaves {
/*  21 */   public static final PropertyEnum field_176240_P = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002090";
/*     */         
/*     */         public boolean func_180195_a(BlockPlanks.EnumType p_180195_1_) {
/*  26 */           return (p_180195_1_.func_176839_a() >= 4);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  30 */           return func_180195_a((BlockPlanks.EnumType)p_apply_1_);
/*     */         }
/*     */       });
/*     */   
/*     */   private static final String __OBFID = "CL_00000276";
/*     */   
/*     */   public BlockNewLeaf() {
/*  37 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176240_P, BlockPlanks.EnumType.ACACIA).withProperty((IProperty)field_176236_b, Boolean.valueOf(true)).withProperty((IProperty)field_176237_a, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_176234_a(World worldIn, BlockPos p_176234_2_, IBlockState p_176234_3_, int p_176234_4_) {
/*  42 */     if (p_176234_3_.getValue((IProperty)field_176240_P) == BlockPlanks.EnumType.DARK_OAK && worldIn.rand.nextInt(p_176234_4_) == 0)
/*     */     {
/*  44 */       spawnAsEntity(worldIn, p_176234_2_, new ItemStack(Items.apple, 1, 0));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  53 */     return ((BlockPlanks.EnumType)state.getValue((IProperty)field_176240_P)).func_176839_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/*  58 */     IBlockState var3 = worldIn.getBlockState(pos);
/*  59 */     return var3.getBlock().getMetaFromState(var3) & 0x3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  67 */     list.add(new ItemStack(itemIn, 1, 0));
/*  68 */     list.add(new ItemStack(itemIn, 1, 1));
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack createStackedBlock(IBlockState state) {
/*  73 */     return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue((IProperty)field_176240_P)).func_176839_a() - 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  81 */     return getDefaultState().withProperty((IProperty)field_176240_P, func_176233_b(meta)).withProperty((IProperty)field_176237_a, Boolean.valueOf(((meta & 0x4) == 0))).withProperty((IProperty)field_176236_b, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  89 */     byte var2 = 0;
/*  90 */     int var3 = var2 | ((BlockPlanks.EnumType)state.getValue((IProperty)field_176240_P)).func_176839_a() - 4;
/*     */     
/*  92 */     if (!((Boolean)state.getValue((IProperty)field_176237_a)).booleanValue())
/*     */     {
/*  94 */       var3 |= 0x4;
/*     */     }
/*     */     
/*  97 */     if (((Boolean)state.getValue((IProperty)field_176236_b)).booleanValue())
/*     */     {
/*  99 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 102 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPlanks.EnumType func_176233_b(int p_176233_1_) {
/* 107 */     return BlockPlanks.EnumType.func_176837_a((p_176233_1_ & 0x3) + 4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 112 */     return new BlockState(this, new IProperty[] { (IProperty)field_176240_P, (IProperty)field_176236_b, (IProperty)field_176237_a });
/*     */   }
/*     */ 
/*     */   
/*     */   public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te) {
/* 117 */     if (!worldIn.isRemote && playerIn.getCurrentEquippedItem() != null && playerIn.getCurrentEquippedItem().getItem() == Items.shears) {
/*     */       
/* 119 */       playerIn.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
/* 120 */       spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue((IProperty)field_176240_P)).func_176839_a() - 4));
/*     */     }
/*     */     else {
/*     */       
/* 124 */       super.harvestBlock(worldIn, playerIn, pos, state, te);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockNewLeaf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */