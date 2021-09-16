/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class BlockSlab
/*     */   extends Block {
/*  22 */   public static final PropertyEnum HALF_PROP = PropertyEnum.create("half", EnumBlockHalf.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000253";
/*     */   
/*     */   public BlockSlab(Material p_i45714_1_) {
/*  27 */     super(p_i45714_1_);
/*     */     
/*  29 */     if (isDouble()) {
/*     */       
/*  31 */       this.fullBlock = true;
/*     */     }
/*     */     else {
/*     */       
/*  35 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */     } 
/*     */     
/*  38 */     setLightOpacity(255);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSilkHarvest() {
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  48 */     if (isDouble()) {
/*     */       
/*  50 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/*  54 */       IBlockState var3 = access.getBlockState(pos);
/*     */       
/*  56 */       if (var3.getBlock() == this)
/*     */       {
/*  58 */         if (var3.getValue((IProperty)HALF_PROP) == EnumBlockHalf.TOP) {
/*     */           
/*  60 */           setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */         }
/*     */         else {
/*     */           
/*  64 */           setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/*  75 */     if (isDouble()) {
/*     */       
/*  77 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/*  81 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
/*  92 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/*  93 */     super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  98 */     return isDouble();
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 103 */     IBlockState var9 = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty((IProperty)HALF_PROP, EnumBlockHalf.BOTTOM);
/* 104 */     return isDouble() ? var9 : ((facing != EnumFacing.DOWN && (facing == EnumFacing.UP || hitY <= 0.5D)) ? var9 : var9.withProperty((IProperty)HALF_PROP, EnumBlockHalf.TOP));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 112 */     return isDouble() ? 2 : 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/* 117 */     return isDouble();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 122 */     if (isDouble())
/*     */     {
/* 124 */       return super.shouldSideBeRendered(worldIn, pos, side);
/*     */     }
/* 126 */     if (side != EnumFacing.UP && side != EnumFacing.DOWN && !super.shouldSideBeRendered(worldIn, pos, side))
/*     */     {
/* 128 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 132 */     BlockPos var4 = pos.offset(side.getOpposite());
/* 133 */     IBlockState var5 = worldIn.getBlockState(pos);
/* 134 */     IBlockState var6 = worldIn.getBlockState(var4);
/* 135 */     boolean var7 = (func_150003_a(var5.getBlock()) && var5.getValue((IProperty)HALF_PROP) == EnumBlockHalf.TOP);
/* 136 */     boolean var8 = (func_150003_a(var6.getBlock()) && var6.getValue((IProperty)HALF_PROP) == EnumBlockHalf.TOP);
/* 137 */     return var8 ? ((side == EnumFacing.DOWN) ? true : ((side == EnumFacing.UP && super.shouldSideBeRendered(worldIn, pos, side)) ? true : (!(func_150003_a(var5.getBlock()) && var7)))) : ((side == EnumFacing.UP) ? true : ((side == EnumFacing.DOWN && super.shouldSideBeRendered(worldIn, pos, side)) ? true : (!(func_150003_a(var5.getBlock()) && !var7))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean func_150003_a(Block p_150003_0_) {
/* 143 */     return !(p_150003_0_ != Blocks.stone_slab && p_150003_0_ != Blocks.wooden_slab && p_150003_0_ != Blocks.stone_slab2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getFullSlabName(int paramInt);
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/* 153 */     return super.getDamageValue(worldIn, pos) & 0x7;
/*     */   }
/*     */   
/*     */   public abstract boolean isDouble();
/*     */   
/*     */   public abstract IProperty func_176551_l();
/*     */   
/*     */   public abstract Object func_176553_a(ItemStack paramItemStack);
/*     */   
/*     */   public enum EnumBlockHalf
/*     */     implements IStringSerializable {
/* 164 */     TOP("TOP", 0, "top"),
/* 165 */     BOTTOM("BOTTOM", 1, "bottom");
/*     */     
/*     */     private final String halfName;
/* 168 */     private static final EnumBlockHalf[] $VALUES = new EnumBlockHalf[] { TOP, BOTTOM };
/*     */     
/*     */     private static final String __OBFID = "CL_00002109";
/*     */     
/*     */     EnumBlockHalf(String p_i45713_1_, int p_i45713_2_, String p_i45713_3_) {
/* 173 */       this.halfName = p_i45713_3_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String toString() {
/* 178 */       return this.halfName;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 183 */       return this.halfName;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */