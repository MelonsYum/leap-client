/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityFallingBlock;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockDragonEgg
/*     */   extends Block {
/*     */   private static final String __OBFID = "CL_00000232";
/*     */   
/*     */   public BlockDragonEgg() {
/*  21 */     super(Material.dragonEgg);
/*  22 */     setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/*  27 */     worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  32 */     worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  37 */     func_180683_d(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_180683_d(World worldIn, BlockPos p_180683_2_) {
/*  42 */     if (BlockFalling.canFallInto(worldIn, p_180683_2_.offsetDown()) && p_180683_2_.getY() >= 0) {
/*     */       
/*  44 */       byte var3 = 32;
/*     */       
/*  46 */       if (!BlockFalling.fallInstantly && worldIn.isAreaLoaded(p_180683_2_.add(-var3, -var3, -var3), p_180683_2_.add(var3, var3, var3))) {
/*     */         
/*  48 */         worldIn.spawnEntityInWorld((Entity)new EntityFallingBlock(worldIn, (p_180683_2_.getX() + 0.5F), p_180683_2_.getY(), (p_180683_2_.getZ() + 0.5F), getDefaultState()));
/*     */       }
/*     */       else {
/*     */         
/*  52 */         worldIn.setBlockToAir(p_180683_2_);
/*     */         
/*     */         BlockPos var4;
/*  55 */         for (var4 = p_180683_2_; BlockFalling.canFallInto(worldIn, var4) && var4.getY() > 0; var4 = var4.offsetDown());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  60 */         if (var4.getY() > 0)
/*     */         {
/*  62 */           worldIn.setBlockState(var4, getDefaultState(), 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/*  70 */     func_180684_e(worldIn, pos);
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
/*  76 */     func_180684_e(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_180684_e(World worldIn, BlockPos p_180684_2_) {
/*  81 */     IBlockState var3 = worldIn.getBlockState(p_180684_2_);
/*     */     
/*  83 */     if (var3.getBlock() == this)
/*     */     {
/*  85 */       for (int var4 = 0; var4 < 1000; var4++) {
/*     */         
/*  87 */         BlockPos var5 = p_180684_2_.add(worldIn.rand.nextInt(16) - worldIn.rand.nextInt(16), worldIn.rand.nextInt(8) - worldIn.rand.nextInt(8), worldIn.rand.nextInt(16) - worldIn.rand.nextInt(16));
/*     */         
/*  89 */         if ((worldIn.getBlockState(var5).getBlock()).blockMaterial == Material.air) {
/*     */           
/*  91 */           if (worldIn.isRemote) {
/*     */             
/*  93 */             for (int var6 = 0; var6 < 128; var6++)
/*     */             {
/*  95 */               double var7 = worldIn.rand.nextDouble();
/*  96 */               float var9 = (worldIn.rand.nextFloat() - 0.5F) * 0.2F;
/*  97 */               float var10 = (worldIn.rand.nextFloat() - 0.5F) * 0.2F;
/*  98 */               float var11 = (worldIn.rand.nextFloat() - 0.5F) * 0.2F;
/*  99 */               double var12 = var5.getX() + (p_180684_2_.getX() - var5.getX()) * var7 + (worldIn.rand.nextDouble() - 0.5D) * 1.0D + 0.5D;
/* 100 */               double var14 = var5.getY() + (p_180684_2_.getY() - var5.getY()) * var7 + worldIn.rand.nextDouble() * 1.0D - 0.5D;
/* 101 */               double var16 = var5.getZ() + (p_180684_2_.getZ() - var5.getZ()) * var7 + (worldIn.rand.nextDouble() - 0.5D) * 1.0D + 0.5D;
/* 102 */               worldIn.spawnParticle(EnumParticleTypes.PORTAL, var12, var14, var16, var9, var10, var11, new int[0]);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 107 */             worldIn.setBlockState(var5, var3, 2);
/* 108 */             worldIn.setBlockToAir(p_180684_2_);
/*     */           } 
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int tickRate(World worldIn) {
/* 122 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 127 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/* 132 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 137 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 142 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockDragonEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */