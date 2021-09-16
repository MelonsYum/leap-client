/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemMonsterPlacer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockPortal
/*     */   extends BlockBreakable {
/*  23 */   public static final PropertyEnum field_176550_a = PropertyEnum.create("axis", EnumFacing.Axis.class, (Enum[])new EnumFacing.Axis[] { EnumFacing.Axis.X, EnumFacing.Axis.Z });
/*     */   
/*     */   private static final String __OBFID = "CL_00000284";
/*     */   
/*     */   public BlockPortal() {
/*  28 */     super(Material.portal, false);
/*  29 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176550_a, (Comparable)EnumFacing.Axis.X));
/*  30 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/*  35 */     super.updateTick(worldIn, pos, state, rand);
/*     */     
/*  37 */     if (worldIn.provider.isSurfaceWorld() && worldIn.getGameRules().getGameRuleBooleanValue("doMobSpawning") && rand.nextInt(2000) < worldIn.getDifficulty().getDifficultyId()) {
/*     */       
/*  39 */       int var5 = pos.getY();
/*     */       
/*     */       BlockPos var6;
/*  42 */       for (var6 = pos; !World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, var6) && var6.getY() > 0; var6 = var6.offsetDown());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  47 */       if (var5 > 0 && !worldIn.getBlockState(var6.offsetUp()).getBlock().isNormalCube()) {
/*     */         
/*  49 */         Entity var7 = ItemMonsterPlacer.spawnCreature(worldIn, 57, var6.getX() + 0.5D, var6.getY() + 1.1D, var6.getZ() + 0.5D);
/*     */         
/*  51 */         if (var7 != null)
/*     */         {
/*  53 */           var7.timeUntilPortal = var7.getPortalCooldown();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  61 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  66 */     EnumFacing.Axis var3 = (EnumFacing.Axis)access.getBlockState(pos).getValue((IProperty)field_176550_a);
/*  67 */     float var4 = 0.125F;
/*  68 */     float var5 = 0.125F;
/*     */     
/*  70 */     if (var3 == EnumFacing.Axis.X)
/*     */     {
/*  72 */       var4 = 0.5F;
/*     */     }
/*     */     
/*  75 */     if (var3 == EnumFacing.Axis.Z)
/*     */     {
/*  77 */       var5 = 0.5F;
/*     */     }
/*     */     
/*  80 */     setBlockBounds(0.5F - var4, 0.0F, 0.5F - var5, 0.5F + var4, 1.0F, 0.5F + var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_176549_a(EnumFacing.Axis p_176549_0_) {
/*  85 */     return (p_176549_0_ == EnumFacing.Axis.X) ? 1 : ((p_176549_0_ == EnumFacing.Axis.Z) ? 2 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  90 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176548_d(World worldIn, BlockPos p_176548_2_) {
/*  95 */     Size var3 = new Size(worldIn, p_176548_2_, EnumFacing.Axis.X);
/*     */     
/*  97 */     if (var3.func_150860_b() && var3.field_150864_e == 0) {
/*     */       
/*  99 */       var3.func_150859_c();
/* 100 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 104 */     Size var4 = new Size(worldIn, p_176548_2_, EnumFacing.Axis.Z);
/*     */     
/* 106 */     if (var4.func_150860_b() && var4.field_150864_e == 0) {
/*     */       
/* 108 */       var4.func_150859_c();
/* 109 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/* 120 */     EnumFacing.Axis var5 = (EnumFacing.Axis)state.getValue((IProperty)field_176550_a);
/*     */ 
/*     */     
/* 123 */     if (var5 == EnumFacing.Axis.X) {
/*     */       
/* 125 */       Size var6 = new Size(worldIn, pos, EnumFacing.Axis.X);
/*     */       
/* 127 */       if (!var6.func_150860_b() || var6.field_150864_e < var6.field_150868_h * var6.field_150862_g)
/*     */       {
/* 129 */         worldIn.setBlockState(pos, Blocks.air.getDefaultState());
/*     */       }
/*     */     }
/* 132 */     else if (var5 == EnumFacing.Axis.Z) {
/*     */       
/* 134 */       Size var6 = new Size(worldIn, pos, EnumFacing.Axis.Z);
/*     */       
/* 136 */       if (!var6.func_150860_b() || var6.field_150864_e < var6.field_150868_h * var6.field_150862_g)
/*     */       {
/* 138 */         worldIn.setBlockState(pos, Blocks.air.getDefaultState());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 145 */     EnumFacing.Axis var4 = null;
/* 146 */     IBlockState var5 = worldIn.getBlockState(pos);
/*     */     
/* 148 */     if (worldIn.getBlockState(pos).getBlock() == this) {
/*     */       
/* 150 */       var4 = (EnumFacing.Axis)var5.getValue((IProperty)field_176550_a);
/*     */       
/* 152 */       if (var4 == null)
/*     */       {
/* 154 */         return false;
/*     */       }
/*     */       
/* 157 */       if (var4 == EnumFacing.Axis.Z && side != EnumFacing.EAST && side != EnumFacing.WEST)
/*     */       {
/* 159 */         return false;
/*     */       }
/*     */       
/* 162 */       if (var4 == EnumFacing.Axis.X && side != EnumFacing.SOUTH && side != EnumFacing.NORTH)
/*     */       {
/* 164 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 168 */     boolean var6 = (worldIn.getBlockState(pos.offsetWest()).getBlock() == this && worldIn.getBlockState(pos.offsetWest(2)).getBlock() != this);
/* 169 */     boolean var7 = (worldIn.getBlockState(pos.offsetEast()).getBlock() == this && worldIn.getBlockState(pos.offsetEast(2)).getBlock() != this);
/* 170 */     boolean var8 = (worldIn.getBlockState(pos.offsetNorth()).getBlock() == this && worldIn.getBlockState(pos.offsetNorth(2)).getBlock() != this);
/* 171 */     boolean var9 = (worldIn.getBlockState(pos.offsetSouth()).getBlock() == this && worldIn.getBlockState(pos.offsetSouth(2)).getBlock() != this);
/* 172 */     boolean var10 = !(!var6 && !var7 && var4 != EnumFacing.Axis.X);
/* 173 */     boolean var11 = !(!var8 && !var9 && var4 != EnumFacing.Axis.Z);
/* 174 */     return (var10 && side == EnumFacing.WEST) ? true : ((var10 && side == EnumFacing.EAST) ? true : ((var11 && side == EnumFacing.NORTH) ? true : ((var11 && side == EnumFacing.SOUTH))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/* 182 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/* 187 */     return EnumWorldBlockLayer.TRANSLUCENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
/* 195 */     if (entityIn.ridingEntity == null && entityIn.riddenByEntity == null)
/*     */     {
/* 197 */       entityIn.setInPortal();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 203 */     if (rand.nextInt(100) == 0)
/*     */     {
/* 205 */       worldIn.playSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
/*     */     }
/*     */     
/* 208 */     for (int var5 = 0; var5 < 4; var5++) {
/*     */       
/* 210 */       double var6 = (pos.getX() + rand.nextFloat());
/* 211 */       double var8 = (pos.getY() + rand.nextFloat());
/* 212 */       double var10 = (pos.getZ() + rand.nextFloat());
/* 213 */       double var12 = (rand.nextFloat() - 0.5D) * 0.5D;
/* 214 */       double var14 = (rand.nextFloat() - 0.5D) * 0.5D;
/* 215 */       double var16 = (rand.nextFloat() - 0.5D) * 0.5D;
/* 216 */       int var18 = rand.nextInt(2) * 2 - 1;
/*     */       
/* 218 */       if (worldIn.getBlockState(pos.offsetWest()).getBlock() != this && worldIn.getBlockState(pos.offsetEast()).getBlock() != this) {
/*     */         
/* 220 */         var6 = pos.getX() + 0.5D + 0.25D * var18;
/* 221 */         var12 = (rand.nextFloat() * 2.0F * var18);
/*     */       }
/*     */       else {
/*     */         
/* 225 */         var10 = pos.getZ() + 0.5D + 0.25D * var18;
/* 226 */         var16 = (rand.nextFloat() * 2.0F * var18);
/*     */       } 
/*     */       
/* 229 */       worldIn.spawnParticle(EnumParticleTypes.PORTAL, var6, var8, var10, var12, var14, var16, new int[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 235 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 243 */     return getDefaultState().withProperty((IProperty)field_176550_a, ((meta & 0x3) == 2) ? (Comparable)EnumFacing.Axis.Z : (Comparable)EnumFacing.Axis.X);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 251 */     return func_176549_a((EnumFacing.Axis)state.getValue((IProperty)field_176550_a));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 256 */     return new BlockState(this, new IProperty[] { (IProperty)field_176550_a });
/*     */   }
/*     */   
/*     */   public static class Size
/*     */   {
/*     */     private final World field_150867_a;
/*     */     private final EnumFacing.Axis field_150865_b;
/*     */     private final EnumFacing field_150866_c;
/*     */     private final EnumFacing field_150863_d;
/* 265 */     private int field_150864_e = 0;
/*     */     
/*     */     private BlockPos field_150861_f;
/*     */     private int field_150862_g;
/*     */     private int field_150868_h;
/*     */     private static final String __OBFID = "CL_00000285";
/*     */     
/*     */     public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_) {
/* 273 */       this.field_150867_a = worldIn;
/* 274 */       this.field_150865_b = p_i45694_3_;
/*     */       
/* 276 */       if (p_i45694_3_ == EnumFacing.Axis.X) {
/*     */         
/* 278 */         this.field_150863_d = EnumFacing.EAST;
/* 279 */         this.field_150866_c = EnumFacing.WEST;
/*     */       }
/*     */       else {
/*     */         
/* 283 */         this.field_150863_d = EnumFacing.NORTH;
/* 284 */         this.field_150866_c = EnumFacing.SOUTH;
/*     */       } 
/*     */       
/* 287 */       for (BlockPos var4 = p_i45694_2_; p_i45694_2_.getY() > var4.getY() - 21 && p_i45694_2_.getY() > 0 && func_150857_a(worldIn.getBlockState(p_i45694_2_.offsetDown()).getBlock()); p_i45694_2_ = p_i45694_2_.offsetDown());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 292 */       int var5 = func_180120_a(p_i45694_2_, this.field_150863_d) - 1;
/*     */       
/* 294 */       if (var5 >= 0) {
/*     */         
/* 296 */         this.field_150861_f = p_i45694_2_.offset(this.field_150863_d, var5);
/* 297 */         this.field_150868_h = func_180120_a(this.field_150861_f, this.field_150866_c);
/*     */         
/* 299 */         if (this.field_150868_h < 2 || this.field_150868_h > 21) {
/*     */           
/* 301 */           this.field_150861_f = null;
/* 302 */           this.field_150868_h = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 306 */       if (this.field_150861_f != null)
/*     */       {
/* 308 */         this.field_150862_g = func_150858_a();
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected int func_180120_a(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
/*     */       int var3;
/* 316 */       for (var3 = 0; var3 < 22; var3++) {
/*     */         
/* 318 */         BlockPos var4 = p_180120_1_.offset(p_180120_2_, var3);
/*     */         
/* 320 */         if (!func_150857_a(this.field_150867_a.getBlockState(var4).getBlock()) || this.field_150867_a.getBlockState(var4.offsetDown()).getBlock() != Blocks.obsidian) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 326 */       Block var5 = this.field_150867_a.getBlockState(p_180120_1_.offset(p_180120_2_, var3)).getBlock();
/* 327 */       return (var5 == Blocks.obsidian) ? var3 : 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected int func_150858_a() {
/* 335 */       label38: for (this.field_150862_g = 0; this.field_150862_g < 21; this.field_150862_g++) {
/*     */         
/* 337 */         for (int i = 0; i < this.field_150868_h; i++) {
/*     */           
/* 339 */           BlockPos var2 = this.field_150861_f.offset(this.field_150866_c, i).offsetUp(this.field_150862_g);
/* 340 */           Block var3 = this.field_150867_a.getBlockState(var2).getBlock();
/*     */           
/* 342 */           if (!func_150857_a(var3)) {
/*     */             break label38;
/*     */           }
/*     */ 
/*     */           
/* 347 */           if (var3 == Blocks.portal)
/*     */           {
/* 349 */             this.field_150864_e++;
/*     */           }
/*     */           
/* 352 */           if (i == 0) {
/*     */             
/* 354 */             var3 = this.field_150867_a.getBlockState(var2.offset(this.field_150863_d)).getBlock();
/*     */             
/* 356 */             if (var3 != Blocks.obsidian)
/*     */             {
/*     */               break label38;
/*     */             }
/*     */           }
/* 361 */           else if (i == this.field_150868_h - 1) {
/*     */             
/* 363 */             var3 = this.field_150867_a.getBlockState(var2.offset(this.field_150866_c)).getBlock();
/*     */             
/* 365 */             if (var3 != Blocks.obsidian) {
/*     */               break label38;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 373 */       for (int var1 = 0; var1 < this.field_150868_h; var1++) {
/*     */         
/* 375 */         if (this.field_150867_a.getBlockState(this.field_150861_f.offset(this.field_150866_c, var1).offsetUp(this.field_150862_g)).getBlock() != Blocks.obsidian) {
/*     */           
/* 377 */           this.field_150862_g = 0;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 382 */       if (this.field_150862_g <= 21 && this.field_150862_g >= 3)
/*     */       {
/* 384 */         return this.field_150862_g;
/*     */       }
/*     */ 
/*     */       
/* 388 */       this.field_150861_f = null;
/* 389 */       this.field_150868_h = 0;
/* 390 */       this.field_150862_g = 0;
/* 391 */       return 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean func_150857_a(Block p_150857_1_) {
/* 397 */       return !(p_150857_1_.blockMaterial != Material.air && p_150857_1_ != Blocks.fire && p_150857_1_ != Blocks.portal);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_150860_b() {
/* 402 */       return (this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_150859_c() {
/* 407 */       for (int var1 = 0; var1 < this.field_150868_h; var1++) {
/*     */         
/* 409 */         BlockPos var2 = this.field_150861_f.offset(this.field_150866_c, var1);
/*     */         
/* 411 */         for (int var3 = 0; var3 < this.field_150862_g; var3++)
/*     */         {
/* 413 */           this.field_150867_a.setBlockState(var2.offsetUp(var3), Blocks.portal.getDefaultState().withProperty((IProperty)BlockPortal.field_176550_a, (Comparable)this.field_150865_b), 2);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */