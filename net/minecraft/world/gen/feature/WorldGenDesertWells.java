/*     */ package net.minecraft.world.gen.feature;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockSand;
/*     */ import net.minecraft.block.BlockSlab;
/*     */ import net.minecraft.block.BlockStoneSlab;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.block.state.pattern.BlockStateHelper;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenDesertWells extends WorldGenerator {
/*  18 */   private static final BlockStateHelper field_175913_a = BlockStateHelper.forBlock((Block)Blocks.sand).func_177637_a((IProperty)BlockSand.VARIANT_PROP, Predicates.equalTo(BlockSand.EnumType.SAND));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   private final IBlockState field_175911_b = Blocks.stone_slab.getDefaultState().withProperty((IProperty)BlockStoneSlab.field_176556_M, (Comparable)BlockStoneSlab.EnumType.SAND).withProperty((IProperty)BlockSlab.HALF_PROP, (Comparable)BlockSlab.EnumBlockHalf.BOTTOM);
/*  27 */   private final IBlockState field_175912_c = Blocks.sandstone.getDefaultState();
/*  28 */   private final IBlockState field_175910_d = Blocks.flowing_water.getDefaultState();
/*     */   
/*     */   private static final String __OBFID = "CL_00000407";
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  33 */     while (worldIn.isAirBlock(p_180709_3_) && p_180709_3_.getY() > 2)
/*     */     {
/*  35 */       p_180709_3_ = p_180709_3_.offsetDown();
/*     */     }
/*     */     
/*  38 */     if (!field_175913_a.func_177639_a(worldIn.getBlockState(p_180709_3_)))
/*     */     {
/*  40 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     int var4;
/*     */ 
/*     */     
/*  47 */     for (var4 = -2; var4 <= 2; var4++) {
/*     */       
/*  49 */       for (int var5 = -2; var5 <= 2; var5++) {
/*     */         
/*  51 */         if (worldIn.isAirBlock(p_180709_3_.add(var4, -1, var5)) && worldIn.isAirBlock(p_180709_3_.add(var4, -2, var5)))
/*     */         {
/*  53 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  58 */     for (var4 = -1; var4 <= 0; var4++) {
/*     */       
/*  60 */       for (int var5 = -2; var5 <= 2; var5++) {
/*     */         
/*  62 */         for (int var6 = -2; var6 <= 2; var6++)
/*     */         {
/*  64 */           worldIn.setBlockState(p_180709_3_.add(var5, var4, var6), this.field_175912_c, 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  69 */     worldIn.setBlockState(p_180709_3_, this.field_175910_d, 2);
/*  70 */     Iterator<EnumFacing> var7 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */     
/*  72 */     while (var7.hasNext()) {
/*     */       
/*  74 */       EnumFacing var8 = var7.next();
/*  75 */       worldIn.setBlockState(p_180709_3_.offset(var8), this.field_175910_d, 2);
/*     */     } 
/*     */     
/*  78 */     for (var4 = -2; var4 <= 2; var4++) {
/*     */       
/*  80 */       for (int var5 = -2; var5 <= 2; var5++) {
/*     */         
/*  82 */         if (var4 == -2 || var4 == 2 || var5 == -2 || var5 == 2)
/*     */         {
/*  84 */           worldIn.setBlockState(p_180709_3_.add(var4, 1, var5), this.field_175912_c, 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  89 */     worldIn.setBlockState(p_180709_3_.add(2, 1, 0), this.field_175911_b, 2);
/*  90 */     worldIn.setBlockState(p_180709_3_.add(-2, 1, 0), this.field_175911_b, 2);
/*  91 */     worldIn.setBlockState(p_180709_3_.add(0, 1, 2), this.field_175911_b, 2);
/*  92 */     worldIn.setBlockState(p_180709_3_.add(0, 1, -2), this.field_175911_b, 2);
/*     */     
/*  94 */     for (var4 = -1; var4 <= 1; var4++) {
/*     */       
/*  96 */       for (int var5 = -1; var5 <= 1; var5++) {
/*     */         
/*  98 */         if (var4 == 0 && var5 == 0) {
/*     */           
/* 100 */           worldIn.setBlockState(p_180709_3_.add(var4, 4, var5), this.field_175912_c, 2);
/*     */         }
/*     */         else {
/*     */           
/* 104 */           worldIn.setBlockState(p_180709_3_.add(var4, 4, var5), this.field_175911_b, 2);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     for (var4 = 1; var4 <= 3; var4++) {
/*     */       
/* 111 */       worldIn.setBlockState(p_180709_3_.add(-1, var4, -1), this.field_175912_c, 2);
/* 112 */       worldIn.setBlockState(p_180709_3_.add(-1, var4, 1), this.field_175912_c, 2);
/* 113 */       worldIn.setBlockState(p_180709_3_.add(1, var4, -1), this.field_175912_c, 2);
/* 114 */       worldIn.setBlockState(p_180709_3_.add(1, var4, 1), this.field_175912_c, 2);
/*     */     } 
/*     */     
/* 117 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenDesertWells.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */