/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockRedstoneRepeater
/*     */   extends BlockRedstoneDiode {
/*  21 */   public static final PropertyBool field_176411_a = PropertyBool.create("locked");
/*  22 */   public static final PropertyInteger field_176410_b = PropertyInteger.create("delay", 1, 4);
/*     */   
/*     */   private static final String __OBFID = "CL_00000301";
/*     */   
/*     */   protected BlockRedstoneRepeater(boolean p_i45424_1_) {
/*  27 */     super(p_i45424_1_);
/*  28 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)AGE, (Comparable)EnumFacing.NORTH).withProperty((IProperty)field_176410_b, Integer.valueOf(1)).withProperty((IProperty)field_176411_a, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  37 */     return state.withProperty((IProperty)field_176411_a, Boolean.valueOf(func_176405_b(worldIn, pos, state)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  42 */     if (!playerIn.capabilities.allowEdit)
/*     */     {
/*  44 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  48 */     worldIn.setBlockState(pos, state.cycleProperty((IProperty)field_176410_b), 3);
/*  49 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_176403_d(IBlockState p_176403_1_) {
/*  55 */     return ((Integer)p_176403_1_.getValue((IProperty)field_176410_b)).intValue() * 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockState func_180674_e(IBlockState p_180674_1_) {
/*  60 */     Integer var2 = (Integer)p_180674_1_.getValue((IProperty)field_176410_b);
/*  61 */     Boolean var3 = (Boolean)p_180674_1_.getValue((IProperty)field_176411_a);
/*  62 */     EnumFacing var4 = (EnumFacing)p_180674_1_.getValue((IProperty)AGE);
/*  63 */     return Blocks.powered_repeater.getDefaultState().withProperty((IProperty)AGE, (Comparable)var4).withProperty((IProperty)field_176410_b, var2).withProperty((IProperty)field_176411_a, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockState func_180675_k(IBlockState p_180675_1_) {
/*  68 */     Integer var2 = (Integer)p_180675_1_.getValue((IProperty)field_176410_b);
/*  69 */     Boolean var3 = (Boolean)p_180675_1_.getValue((IProperty)field_176411_a);
/*  70 */     EnumFacing var4 = (EnumFacing)p_180675_1_.getValue((IProperty)AGE);
/*  71 */     return Blocks.unpowered_repeater.getDefaultState().withProperty((IProperty)AGE, (Comparable)var4).withProperty((IProperty)field_176410_b, var2).withProperty((IProperty)field_176411_a, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  81 */     return Items.repeater;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/*  86 */     return Items.repeater;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176405_b(IBlockAccess p_176405_1_, BlockPos p_176405_2_, IBlockState p_176405_3_) {
/*  91 */     return (func_176407_c(p_176405_1_, p_176405_2_, p_176405_3_) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_149908_a(Block p_149908_1_) {
/*  96 */     return isRedstoneRepeaterBlockID(p_149908_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 101 */     if (this.isRepeaterPowered) {
/*     */       
/* 103 */       EnumFacing var5 = (EnumFacing)state.getValue((IProperty)AGE);
/* 104 */       double var6 = (pos.getX() + 0.5F) + (rand.nextFloat() - 0.5F) * 0.2D;
/* 105 */       double var8 = (pos.getY() + 0.4F) + (rand.nextFloat() - 0.5F) * 0.2D;
/* 106 */       double var10 = (pos.getZ() + 0.5F) + (rand.nextFloat() - 0.5F) * 0.2D;
/* 107 */       float var12 = -5.0F;
/*     */       
/* 109 */       if (rand.nextBoolean())
/*     */       {
/* 111 */         var12 = (((Integer)state.getValue((IProperty)field_176410_b)).intValue() * 2 - 1);
/*     */       }
/*     */       
/* 114 */       var12 /= 16.0F;
/* 115 */       double var13 = (var12 * var5.getFrontOffsetX());
/* 116 */       double var15 = (var12 * var5.getFrontOffsetZ());
/* 117 */       worldIn.spawnParticle(EnumParticleTypes.REDSTONE, var6 + var13, var8, var10 + var15, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 123 */     super.breakBlock(worldIn, pos, state);
/* 124 */     func_176400_h(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 132 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)EnumFacing.getHorizontal(meta)).withProperty((IProperty)field_176411_a, Boolean.valueOf(false)).withProperty((IProperty)field_176410_b, Integer.valueOf(1 + (meta >> 2)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 140 */     byte var2 = 0;
/* 141 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)AGE)).getHorizontalIndex();
/* 142 */     var3 |= ((Integer)state.getValue((IProperty)field_176410_b)).intValue() - 1 << 2;
/* 143 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 148 */     return new BlockState(this, new IProperty[] { (IProperty)AGE, (IProperty)field_176410_b, (IProperty)field_176411_a });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRedstoneRepeater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */