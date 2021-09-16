/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockStaticLiquid
/*     */   extends BlockLiquid {
/*     */   private static final String __OBFID = "CL_00000315";
/*     */   
/*     */   protected BlockStaticLiquid(Material p_i45429_1_) {
/*  17 */     super(p_i45429_1_);
/*  18 */     setTickRandomly(false);
/*     */     
/*  20 */     if (p_i45429_1_ == Material.lava)
/*     */     {
/*  22 */       setTickRandomly(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  28 */     if (!func_176365_e(worldIn, pos, state))
/*     */     {
/*  30 */       updateLiquid(worldIn, pos, state);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateLiquid(World worldIn, BlockPos p_176370_2_, IBlockState p_176370_3_) {
/*  36 */     BlockDynamicLiquid var4 = getDynamicLiquidForMaterial(this.blockMaterial);
/*  37 */     worldIn.setBlockState(p_176370_2_, var4.getDefaultState().withProperty((IProperty)LEVEL, p_176370_3_.getValue((IProperty)LEVEL)), 2);
/*  38 */     worldIn.scheduleUpdate(p_176370_2_, var4, tickRate(worldIn));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  43 */     if (this.blockMaterial == Material.lava)
/*     */     {
/*  45 */       if (worldIn.getGameRules().getGameRuleBooleanValue("doFireTick")) {
/*     */         
/*  47 */         int var5 = rand.nextInt(3);
/*     */         
/*  49 */         if (var5 > 0) {
/*     */           
/*  51 */           BlockPos var6 = pos;
/*     */           
/*  53 */           for (int var7 = 0; var7 < var5; var7++) {
/*     */             
/*  55 */             var6 = var6.add(rand.nextInt(3) - 1, 1, rand.nextInt(3) - 1);
/*  56 */             Block var8 = worldIn.getBlockState(var6).getBlock();
/*     */             
/*  58 */             if (var8.blockMaterial == Material.air) {
/*     */               
/*  60 */               if (isSurroundingBlockFlammable(worldIn, var6)) {
/*     */                 
/*  62 */                 worldIn.setBlockState(var6, Blocks.fire.getDefaultState());
/*     */                 
/*     */                 return;
/*     */               } 
/*  66 */             } else if (var8.blockMaterial.blocksMovement()) {
/*     */               
/*     */               return;
/*     */             }
/*     */           
/*     */           } 
/*     */         } else {
/*     */           
/*  74 */           for (int var9 = 0; var9 < 3; var9++) {
/*     */             
/*  76 */             BlockPos var10 = pos.add(rand.nextInt(3) - 1, 0, rand.nextInt(3) - 1);
/*     */             
/*  78 */             if (worldIn.isAirBlock(var10.offsetUp()) && getCanBlockBurn(worldIn, var10))
/*     */             {
/*  80 */               worldIn.setBlockState(var10.offsetUp(), Blocks.fire.getDefaultState());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isSurroundingBlockFlammable(World worldIn, BlockPos p_176369_2_) {
/*  90 */     EnumFacing[] var3 = EnumFacing.values();
/*  91 */     int var4 = var3.length;
/*     */     
/*  93 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/*  95 */       EnumFacing var6 = var3[var5];
/*     */       
/*  97 */       if (getCanBlockBurn(worldIn, p_176369_2_.offset(var6)))
/*     */       {
/*  99 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean getCanBlockBurn(World worldIn, BlockPos p_176368_2_) {
/* 108 */     return worldIn.getBlockState(p_176368_2_).getBlock().getMaterial().getCanBurn();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockStaticLiquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */