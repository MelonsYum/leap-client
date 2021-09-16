/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.ColorizerFoliage;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeColorHelper;
/*     */ 
/*     */ public abstract class BlockLeaves extends BlockLeavesBase {
/*  21 */   public static final PropertyBool field_176237_a = PropertyBool.create("decayable");
/*  22 */   public static final PropertyBool field_176236_b = PropertyBool.create("check_decay");
/*     */   
/*     */   int[] field_150128_a;
/*     */   protected int field_150127_b;
/*     */   protected boolean field_176238_O;
/*     */   private static final String __OBFID = "CL_00000263";
/*     */   
/*     */   public BlockLeaves() {
/*  30 */     super(Material.leaves, false);
/*  31 */     setTickRandomly(true);
/*  32 */     setCreativeTab(CreativeTabs.tabDecorations);
/*  33 */     setHardness(0.2F);
/*  34 */     setLightOpacity(1);
/*  35 */     setStepSound(soundTypeGrass);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBlockColor() {
/*  40 */     return ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRenderColor(IBlockState state) {
/*  45 */     return ColorizerFoliage.getFoliageColorBasic();
/*     */   }
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/*  50 */     return BiomeColorHelper.func_180287_b(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/*  55 */     byte var4 = 1;
/*  56 */     int var5 = var4 + 1;
/*  57 */     int var6 = pos.getX();
/*  58 */     int var7 = pos.getY();
/*  59 */     int var8 = pos.getZ();
/*     */     
/*  61 */     if (worldIn.isAreaLoaded(new BlockPos(var6 - var5, var7 - var5, var8 - var5), new BlockPos(var6 + var5, var7 + var5, var8 + var5)))
/*     */     {
/*  63 */       for (int var9 = -var4; var9 <= var4; var9++) {
/*     */         
/*  65 */         for (int var10 = -var4; var10 <= var4; var10++) {
/*     */           
/*  67 */           for (int var11 = -var4; var11 <= var4; var11++) {
/*     */             
/*  69 */             BlockPos var12 = pos.add(var9, var10, var11);
/*  70 */             IBlockState var13 = worldIn.getBlockState(var12);
/*     */             
/*  72 */             if (var13.getBlock().getMaterial() == Material.leaves && !((Boolean)var13.getValue((IProperty)field_176236_b)).booleanValue())
/*     */             {
/*  74 */               worldIn.setBlockState(var12, var13.withProperty((IProperty)field_176236_b, Boolean.valueOf(true)), 4);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  84 */     if (!worldIn.isRemote)
/*     */     {
/*  86 */       if (((Boolean)state.getValue((IProperty)field_176236_b)).booleanValue() && ((Boolean)state.getValue((IProperty)field_176237_a)).booleanValue()) {
/*     */         
/*  88 */         byte var5 = 4;
/*  89 */         int var6 = var5 + 1;
/*  90 */         int var7 = pos.getX();
/*  91 */         int var8 = pos.getY();
/*  92 */         int var9 = pos.getZ();
/*  93 */         byte var10 = 32;
/*  94 */         int var11 = var10 * var10;
/*  95 */         int var12 = var10 / 2;
/*     */         
/*  97 */         if (this.field_150128_a == null)
/*     */         {
/*  99 */           this.field_150128_a = new int[var10 * var10 * var10];
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 104 */         if (worldIn.isAreaLoaded(new BlockPos(var7 - var6, var8 - var6, var9 - var6), new BlockPos(var7 + var6, var8 + var6, var9 + var6))) {
/*     */           int i;
/*     */ 
/*     */ 
/*     */           
/* 109 */           for (i = -var5; i <= var5; i++) {
/*     */             
/* 111 */             for (int var14 = -var5; var14 <= var5; var14++) {
/*     */               
/* 113 */               for (int var15 = -var5; var15 <= var5; var15++) {
/*     */                 
/* 115 */                 Block var16 = worldIn.getBlockState(new BlockPos(var7 + i, var8 + var14, var9 + var15)).getBlock();
/*     */                 
/* 117 */                 if (var16 != Blocks.log && var16 != Blocks.log2) {
/*     */                   
/* 119 */                   if (var16.getMaterial() == Material.leaves)
/*     */                   {
/* 121 */                     this.field_150128_a[(i + var12) * var11 + (var14 + var12) * var10 + var15 + var12] = -2;
/*     */                   }
/*     */                   else
/*     */                   {
/* 125 */                     this.field_150128_a[(i + var12) * var11 + (var14 + var12) * var10 + var15 + var12] = -1;
/*     */                   }
/*     */                 
/*     */                 } else {
/*     */                   
/* 130 */                   this.field_150128_a[(i + var12) * var11 + (var14 + var12) * var10 + var15 + var12] = 0;
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 136 */           for (i = 1; i <= 4; i++) {
/*     */             
/* 138 */             for (int var14 = -var5; var14 <= var5; var14++) {
/*     */               
/* 140 */               for (int var15 = -var5; var15 <= var5; var15++) {
/*     */                 
/* 142 */                 for (int var17 = -var5; var17 <= var5; var17++) {
/*     */                   
/* 144 */                   if (this.field_150128_a[(var14 + var12) * var11 + (var15 + var12) * var10 + var17 + var12] == i - 1) {
/*     */                     
/* 146 */                     if (this.field_150128_a[(var14 + var12 - 1) * var11 + (var15 + var12) * var10 + var17 + var12] == -2)
/*     */                     {
/* 148 */                       this.field_150128_a[(var14 + var12 - 1) * var11 + (var15 + var12) * var10 + var17 + var12] = i;
/*     */                     }
/*     */                     
/* 151 */                     if (this.field_150128_a[(var14 + var12 + 1) * var11 + (var15 + var12) * var10 + var17 + var12] == -2)
/*     */                     {
/* 153 */                       this.field_150128_a[(var14 + var12 + 1) * var11 + (var15 + var12) * var10 + var17 + var12] = i;
/*     */                     }
/*     */                     
/* 156 */                     if (this.field_150128_a[(var14 + var12) * var11 + (var15 + var12 - 1) * var10 + var17 + var12] == -2)
/*     */                     {
/* 158 */                       this.field_150128_a[(var14 + var12) * var11 + (var15 + var12 - 1) * var10 + var17 + var12] = i;
/*     */                     }
/*     */                     
/* 161 */                     if (this.field_150128_a[(var14 + var12) * var11 + (var15 + var12 + 1) * var10 + var17 + var12] == -2)
/*     */                     {
/* 163 */                       this.field_150128_a[(var14 + var12) * var11 + (var15 + var12 + 1) * var10 + var17 + var12] = i;
/*     */                     }
/*     */                     
/* 166 */                     if (this.field_150128_a[(var14 + var12) * var11 + (var15 + var12) * var10 + var17 + var12 - 1] == -2)
/*     */                     {
/* 168 */                       this.field_150128_a[(var14 + var12) * var11 + (var15 + var12) * var10 + var17 + var12 - 1] = i;
/*     */                     }
/*     */                     
/* 171 */                     if (this.field_150128_a[(var14 + var12) * var11 + (var15 + var12) * var10 + var17 + var12 + 1] == -2)
/*     */                     {
/* 173 */                       this.field_150128_a[(var14 + var12) * var11 + (var15 + var12) * var10 + var17 + var12 + 1] = i;
/*     */                     }
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 182 */         int var13 = this.field_150128_a[var12 * var11 + var12 * var10 + var12];
/*     */         
/* 184 */         if (var13 >= 0) {
/*     */           
/* 186 */           worldIn.setBlockState(pos, state.withProperty((IProperty)field_176236_b, Boolean.valueOf(false)), 4);
/*     */         }
/*     */         else {
/*     */           
/* 190 */           func_176235_d(worldIn, pos);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 198 */     if (worldIn.func_175727_C(pos.offsetUp()) && !World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown()) && rand.nextInt(15) == 1) {
/*     */       
/* 200 */       double var5 = (pos.getX() + rand.nextFloat());
/* 201 */       double var7 = pos.getY() - 0.05D;
/* 202 */       double var9 = (pos.getZ() + rand.nextFloat());
/* 203 */       worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, var5, var7, var9, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176235_d(World worldIn, BlockPos p_176235_2_) {
/* 209 */     dropBlockAsItem(worldIn, p_176235_2_, worldIn.getBlockState(p_176235_2_), 0);
/* 210 */     worldIn.setBlockToAir(p_176235_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 218 */     return (random.nextInt(20) == 0) ? 1 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 228 */     return Item.getItemFromBlock(Blocks.sapling);
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
/* 239 */     if (!worldIn.isRemote) {
/*     */       
/* 241 */       int var6 = func_176232_d(state);
/*     */       
/* 243 */       if (fortune > 0) {
/*     */         
/* 245 */         var6 -= 2 << fortune;
/*     */         
/* 247 */         if (var6 < 10)
/*     */         {
/* 249 */           var6 = 10;
/*     */         }
/*     */       } 
/*     */       
/* 253 */       if (worldIn.rand.nextInt(var6) == 0) {
/*     */         
/* 255 */         Item var7 = getItemDropped(state, worldIn.rand, fortune);
/* 256 */         spawnAsEntity(worldIn, pos, new ItemStack(var7, 1, damageDropped(state)));
/*     */       } 
/*     */       
/* 259 */       var6 = 200;
/*     */       
/* 261 */       if (fortune > 0) {
/*     */         
/* 263 */         var6 -= 10 << fortune;
/*     */         
/* 265 */         if (var6 < 40)
/*     */         {
/* 267 */           var6 = 40;
/*     */         }
/*     */       } 
/*     */       
/* 271 */       func_176234_a(worldIn, pos, state, var6);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_176234_a(World worldIn, BlockPos p_176234_2_, IBlockState p_176234_3_, int p_176234_4_) {}
/*     */   
/*     */   protected int func_176232_d(IBlockState p_176232_1_) {
/* 279 */     return 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/* 284 */     return !this.field_150121_P;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGraphicsLevel(boolean p_150122_1_) {
/* 292 */     this.field_176238_O = p_150122_1_;
/* 293 */     this.field_150121_P = p_150122_1_;
/* 294 */     this.field_150127_b = p_150122_1_ ? 0 : 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 299 */     return this.field_176238_O ? EnumWorldBlockLayer.CUTOUT_MIPPED : EnumWorldBlockLayer.SOLID;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisuallyOpaque() {
/* 304 */     return false;
/*     */   }
/*     */   
/*     */   public abstract BlockPlanks.EnumType func_176233_b(int paramInt);
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */