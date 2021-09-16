/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.properties.PropertyInteger;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockStem
/*     */   extends BlockBush
/*     */   implements IGrowable {
/*  25 */   public static final PropertyInteger AGE_PROP = PropertyInteger.create("age", 0, 7);
/*  26 */   public static final PropertyDirection FACING_PROP = PropertyDirection.create("facing", new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002059";
/*     */         
/*     */         public boolean apply(EnumFacing p_177218_1_) {
/*  31 */           return (p_177218_1_ != EnumFacing.DOWN);
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  35 */           return apply((EnumFacing)p_apply_1_);
/*     */         }
/*     */       });
/*     */   
/*     */   private final Block cropBlock;
/*     */   private static final String __OBFID = "CL_00000316";
/*     */   
/*     */   protected BlockStem(Block p_i45430_1_) {
/*  43 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)AGE_PROP, Integer.valueOf(0)).withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.UP));
/*  44 */     this.cropBlock = p_i45430_1_;
/*  45 */     setTickRandomly(true);
/*  46 */     float var2 = 0.125F;
/*  47 */     setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 0.25F, 0.5F + var2);
/*  48 */     setCreativeTab(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  57 */     state = state.withProperty((IProperty)FACING_PROP, (Comparable)EnumFacing.UP);
/*  58 */     Iterator<EnumFacing> var4 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/*  60 */     while (var4.hasNext()) {
/*     */       
/*  62 */       EnumFacing var5 = var4.next();
/*     */       
/*  64 */       if (worldIn.getBlockState(pos.offset(var5)).getBlock() == this.cropBlock) {
/*     */         
/*  66 */         state = state.withProperty((IProperty)FACING_PROP, (Comparable)var5);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  71 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canPlaceBlockOn(Block ground) {
/*  79 */     return (ground == Blocks.farmland);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  84 */     super.updateTick(worldIn, pos, state, rand);
/*     */     
/*  86 */     if (worldIn.getLightFromNeighbors(pos.offsetUp()) >= 9) {
/*     */       
/*  88 */       float var5 = BlockCrops.getGrowthChance(this, worldIn, pos);
/*     */       
/*  90 */       if (rand.nextInt((int)(25.0F / var5) + 1) == 0) {
/*     */         
/*  92 */         int var6 = ((Integer)state.getValue((IProperty)AGE_PROP)).intValue();
/*     */         
/*  94 */         if (var6 < 7) {
/*     */           
/*  96 */           state = state.withProperty((IProperty)AGE_PROP, Integer.valueOf(var6 + 1));
/*  97 */           worldIn.setBlockState(pos, state, 2);
/*     */         }
/*     */         else {
/*     */           
/* 101 */           Iterator<EnumFacing> var7 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */           
/* 103 */           while (var7.hasNext()) {
/*     */             
/* 105 */             EnumFacing var8 = var7.next();
/*     */             
/* 107 */             if (worldIn.getBlockState(pos.offset(var8)).getBlock() == this.cropBlock) {
/*     */               return;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 113 */           pos = pos.offset(EnumFacing.Plane.HORIZONTAL.random(rand));
/* 114 */           Block var9 = worldIn.getBlockState(pos.offsetDown()).getBlock();
/*     */           
/* 116 */           if ((worldIn.getBlockState(pos).getBlock()).blockMaterial == Material.air && (var9 == Blocks.farmland || var9 == Blocks.dirt || var9 == Blocks.grass))
/*     */           {
/* 118 */             worldIn.setBlockState(pos, this.cropBlock.getDefaultState());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void growStem(World worldIn, BlockPos p_176482_2_, IBlockState p_176482_3_) {
/* 127 */     int var4 = ((Integer)p_176482_3_.getValue((IProperty)AGE_PROP)).intValue() + MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5);
/* 128 */     worldIn.setBlockState(p_176482_2_, p_176482_3_.withProperty((IProperty)AGE_PROP, Integer.valueOf(Math.min(7, var4))), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderColor(IBlockState state) {
/* 133 */     if (state.getBlock() != this)
/*     */     {
/* 135 */       return super.getRenderColor(state);
/*     */     }
/*     */ 
/*     */     
/* 139 */     int var2 = ((Integer)state.getValue((IProperty)AGE_PROP)).intValue();
/* 140 */     int var3 = var2 * 32;
/* 141 */     int var4 = 255 - var2 * 8;
/* 142 */     int var5 = var2 * 4;
/* 143 */     return var3 << 16 | var4 << 8 | var5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/* 149 */     return getRenderColor(worldIn.getBlockState(pos));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockBoundsForItemRender() {
/* 157 */     float var1 = 0.125F;
/* 158 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 163 */     this.maxY = ((((Integer)access.getBlockState(pos).getValue((IProperty)AGE_PROP)).intValue() * 2 + 2) / 16.0F);
/* 164 */     float var3 = 0.125F;
/* 165 */     setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, (float)this.maxY, 0.5F + var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/* 176 */     super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
/*     */     
/* 178 */     if (!worldIn.isRemote) {
/*     */       
/* 180 */       Item var6 = getSeedItem();
/*     */       
/* 182 */       if (var6 != null) {
/*     */         
/* 184 */         int var7 = ((Integer)state.getValue((IProperty)AGE_PROP)).intValue();
/*     */         
/* 186 */         for (int var8 = 0; var8 < 3; var8++) {
/*     */           
/* 188 */           if (worldIn.rand.nextInt(15) <= var7)
/*     */           {
/* 190 */             spawnAsEntity(worldIn, pos, new ItemStack(var6));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getSeedItem() {
/* 199 */     return (this.cropBlock == Blocks.pumpkin) ? Items.pumpkin_seeds : ((this.cropBlock == Blocks.melon_block) ? Items.melon_seeds : null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 209 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 214 */     Item var3 = getSeedItem();
/* 215 */     return (var3 != null) ? var3 : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
/* 220 */     return (((Integer)p_176473_3_.getValue((IProperty)AGE_PROP)).intValue() != 7);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
/* 225 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
/* 230 */     growStem(worldIn, p_176474_3_, p_176474_4_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 238 */     return getDefaultState().withProperty((IProperty)AGE_PROP, Integer.valueOf(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 246 */     return ((Integer)state.getValue((IProperty)AGE_PROP)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 251 */     return new BlockState(this, new IProperty[] { (IProperty)AGE_PROP, (IProperty)FACING_PROP });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */