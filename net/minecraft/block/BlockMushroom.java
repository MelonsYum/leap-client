/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenBigMushroom;
/*     */ 
/*     */ public class BlockMushroom
/*     */   extends BlockBush implements IGrowable {
/*     */   private static final String __OBFID = "CL_00000272";
/*     */   
/*     */   protected BlockMushroom() {
/*  17 */     float var1 = 0.2F;
/*  18 */     setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var1 * 2.0F, 0.5F + var1);
/*  19 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  24 */     if (rand.nextInt(25) == 0) {
/*     */       
/*  26 */       int var5 = 5;
/*  27 */       boolean var6 = true;
/*  28 */       Iterator<BlockPos> var7 = BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4)).iterator();
/*     */       
/*  30 */       while (var7.hasNext()) {
/*     */         
/*  32 */         BlockPos var8 = var7.next();
/*     */         
/*  34 */         if (worldIn.getBlockState(var8).getBlock() == this) {
/*     */           
/*  36 */           var5--;
/*     */           
/*  38 */           if (var5 <= 0) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  45 */       BlockPos var9 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
/*     */       
/*  47 */       for (int var10 = 0; var10 < 4; var10++) {
/*     */         
/*  49 */         if (worldIn.isAirBlock(var9) && canBlockStay(worldIn, var9, getDefaultState()))
/*     */         {
/*  51 */           pos = var9;
/*     */         }
/*     */         
/*  54 */         var9 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
/*     */       } 
/*     */       
/*  57 */       if (worldIn.isAirBlock(var9) && canBlockStay(worldIn, var9, getDefaultState()))
/*     */       {
/*  59 */         worldIn.setBlockState(var9, getDefaultState(), 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  66 */     return (super.canPlaceBlockAt(worldIn, pos) && canBlockStay(worldIn, pos, getDefaultState()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canPlaceBlockOn(Block ground) {
/*  74 */     return ground.isFullBlock();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlockStay(World worldIn, BlockPos p_180671_2_, IBlockState p_180671_3_) {
/*  79 */     if (p_180671_2_.getY() >= 0 && p_180671_2_.getY() < 256) {
/*     */       
/*  81 */       IBlockState var4 = worldIn.getBlockState(p_180671_2_.offsetDown());
/*  82 */       return (var4.getBlock() == Blocks.mycelium) ? true : ((var4.getBlock() == Blocks.dirt && var4.getValue((IProperty)BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL) ? true : ((worldIn.getLight(p_180671_2_) < 13 && canPlaceBlockOn(var4.getBlock()))));
/*     */     } 
/*     */ 
/*     */     
/*  86 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_176485_d(World worldIn, BlockPos p_176485_2_, IBlockState p_176485_3_, Random p_176485_4_) {
/*  92 */     worldIn.setBlockToAir(p_176485_2_);
/*  93 */     WorldGenBigMushroom var5 = null;
/*     */     
/*  95 */     if (this == Blocks.brown_mushroom) {
/*     */       
/*  97 */       var5 = new WorldGenBigMushroom(0);
/*     */     }
/*  99 */     else if (this == Blocks.red_mushroom) {
/*     */       
/* 101 */       var5 = new WorldGenBigMushroom(1);
/*     */     } 
/*     */     
/* 104 */     if (var5 != null && var5.generate(worldIn, p_176485_4_, p_176485_2_))
/*     */     {
/* 106 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 110 */     worldIn.setBlockState(p_176485_2_, p_176485_3_, 3);
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
/* 122 */     return (p_180670_2_.nextFloat() < 0.4D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
/* 127 */     func_176485_d(worldIn, p_176474_3_, p_176474_4_, p_176474_2_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */