/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockRedstoneTorch
/*     */   extends BlockTorch {
/*  20 */   private static Map field_150112_b = Maps.newHashMap();
/*     */   
/*     */   private final boolean field_150113_a;
/*     */   private static final String __OBFID = "CL_00000298";
/*     */   
/*     */   private boolean func_176598_a(World worldIn, BlockPos p_176598_2_, boolean p_176598_3_) {
/*  26 */     if (!field_150112_b.containsKey(worldIn))
/*     */     {
/*  28 */       field_150112_b.put(worldIn, Lists.newArrayList());
/*     */     }
/*     */     
/*  31 */     List<Toggle> var4 = (List)field_150112_b.get(worldIn);
/*     */     
/*  33 */     if (p_176598_3_)
/*     */     {
/*  35 */       var4.add(new Toggle(p_176598_2_, worldIn.getTotalWorldTime()));
/*     */     }
/*     */     
/*  38 */     int var5 = 0;
/*     */     
/*  40 */     for (int var6 = 0; var6 < var4.size(); var6++) {
/*     */       
/*  42 */       Toggle var7 = var4.get(var6);
/*     */       
/*  44 */       if (var7.field_180111_a.equals(p_176598_2_)) {
/*     */         
/*  46 */         var5++;
/*     */         
/*  48 */         if (var5 >= 8)
/*     */         {
/*  50 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  55 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockRedstoneTorch(boolean p_i45423_1_) {
/*  60 */     this.field_150113_a = p_i45423_1_;
/*  61 */     setTickRandomly(true);
/*  62 */     setCreativeTab(null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/*  70 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  75 */     if (this.field_150113_a) {
/*     */       
/*  77 */       EnumFacing[] var4 = EnumFacing.values();
/*  78 */       int var5 = var4.length;
/*     */       
/*  80 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/*  82 */         EnumFacing var7 = var4[var6];
/*  83 */         worldIn.notifyNeighborsOfStateChange(pos.offset(var7), this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/*  90 */     if (this.field_150113_a) {
/*     */       
/*  92 */       EnumFacing[] var4 = EnumFacing.values();
/*  93 */       int var5 = var4.length;
/*     */       
/*  95 */       for (int var6 = 0; var6 < var5; var6++) {
/*     */         
/*  97 */         EnumFacing var7 = var4[var6];
/*  98 */         worldIn.notifyNeighborsOfStateChange(pos.offset(var7), this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingWeakPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 105 */     return (this.field_150113_a && state.getValue((IProperty)FACING_PROP) != side) ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176597_g(World worldIn, BlockPos p_176597_2_, IBlockState p_176597_3_) {
/* 110 */     EnumFacing var4 = ((EnumFacing)p_176597_3_.getValue((IProperty)FACING_PROP)).getOpposite();
/* 111 */     return worldIn.func_175709_b(p_176597_2_.offset(var4), var4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 121 */     boolean var5 = func_176597_g(worldIn, pos, state);
/* 122 */     List var6 = (List)field_150112_b.get(worldIn);
/*     */     
/* 124 */     while (var6 != null && !var6.isEmpty() && worldIn.getTotalWorldTime() - ((Toggle)var6.get(0)).field_150844_d > 60L)
/*     */     {
/* 126 */       var6.remove(0);
/*     */     }
/*     */     
/* 129 */     if (this.field_150113_a) {
/*     */       
/* 131 */       if (var5) {
/*     */         
/* 133 */         worldIn.setBlockState(pos, Blocks.unlit_redstone_torch.getDefaultState().withProperty((IProperty)FACING_PROP, state.getValue((IProperty)FACING_PROP)), 3);
/*     */         
/* 135 */         if (func_176598_a(worldIn, pos, true))
/*     */         {
/* 137 */           worldIn.playSoundEffect((pos.getX() + 0.5F), (pos.getY() + 0.5F), (pos.getZ() + 0.5F), "random.fizz", 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);
/*     */           
/* 139 */           for (int var7 = 0; var7 < 5; var7++) {
/*     */             
/* 141 */             double var8 = pos.getX() + rand.nextDouble() * 0.6D + 0.2D;
/* 142 */             double var10 = pos.getY() + rand.nextDouble() * 0.6D + 0.2D;
/* 143 */             double var12 = pos.getZ() + rand.nextDouble() * 0.6D + 0.2D;
/* 144 */             worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var8, var10, var12, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           } 
/*     */           
/* 147 */           worldIn.scheduleUpdate(pos, worldIn.getBlockState(pos).getBlock(), 160);
/*     */         }
/*     */       
/*     */       } 
/* 151 */     } else if (!var5 && !func_176598_a(worldIn, pos, false)) {
/*     */       
/* 153 */       worldIn.setBlockState(pos, Blocks.redstone_torch.getDefaultState().withProperty((IProperty)FACING_PROP, state.getValue((IProperty)FACING_PROP)), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 159 */     if (!func_176592_e(worldIn, pos, state))
/*     */     {
/* 161 */       if (this.field_150113_a == func_176597_g(worldIn, pos, state))
/*     */       {
/* 163 */         worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int isProvidingStrongPower(IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side) {
/* 170 */     return (side == EnumFacing.DOWN) ? isProvidingWeakPower(worldIn, pos, state, side) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 180 */     return Item.getItemFromBlock(Blocks.redstone_torch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canProvidePower() {
/* 188 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 193 */     if (this.field_150113_a) {
/*     */       
/* 195 */       double var5 = (pos.getX() + 0.5F) + (rand.nextFloat() - 0.5F) * 0.2D;
/* 196 */       double var7 = (pos.getY() + 0.7F) + (rand.nextFloat() - 0.5F) * 0.2D;
/* 197 */       double var9 = (pos.getZ() + 0.5F) + (rand.nextFloat() - 0.5F) * 0.2D;
/* 198 */       EnumFacing var11 = (EnumFacing)state.getValue((IProperty)FACING_PROP);
/*     */       
/* 200 */       if (var11.getAxis().isHorizontal()) {
/*     */         
/* 202 */         EnumFacing var12 = var11.getOpposite();
/* 203 */         double var13 = 0.27000001072883606D;
/* 204 */         var5 += 0.27000001072883606D * var12.getFrontOffsetX();
/* 205 */         var7 += 0.2199999988079071D;
/* 206 */         var9 += 0.27000001072883606D * var12.getFrontOffsetZ();
/*     */       } 
/*     */       
/* 209 */       worldIn.spawnParticle(EnumParticleTypes.REDSTONE, var5, var7, var9, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 215 */     return Item.getItemFromBlock(Blocks.redstone_torch);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAssociatedBlock(Block other) {
/* 220 */     return !(other != Blocks.unlit_redstone_torch && other != Blocks.redstone_torch);
/*     */   }
/*     */ 
/*     */   
/*     */   static class Toggle
/*     */   {
/*     */     BlockPos field_180111_a;
/*     */     long field_150844_d;
/*     */     private static final String __OBFID = "CL_00000299";
/*     */     
/*     */     public Toggle(BlockPos p_i45688_1_, long p_i45688_2_) {
/* 231 */       this.field_180111_a = p_i45688_1_;
/* 232 */       this.field_150844_d = p_i45688_2_;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRedstoneTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */