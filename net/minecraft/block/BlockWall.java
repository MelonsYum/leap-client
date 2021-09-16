/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockWall
/*     */   extends Block {
/*  23 */   public static final PropertyBool field_176256_a = PropertyBool.create("up");
/*  24 */   public static final PropertyBool field_176254_b = PropertyBool.create("north");
/*  25 */   public static final PropertyBool field_176257_M = PropertyBool.create("east");
/*  26 */   public static final PropertyBool field_176258_N = PropertyBool.create("south");
/*  27 */   public static final PropertyBool field_176259_O = PropertyBool.create("west");
/*  28 */   public static final PropertyEnum field_176255_P = PropertyEnum.create("variant", EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000331";
/*     */   
/*     */   public BlockWall(Block p_i45435_1_) {
/*  33 */     super(p_i45435_1_.blockMaterial);
/*  34 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176256_a, Boolean.valueOf(false)).withProperty((IProperty)field_176254_b, Boolean.valueOf(false)).withProperty((IProperty)field_176257_M, Boolean.valueOf(false)).withProperty((IProperty)field_176258_N, Boolean.valueOf(false)).withProperty((IProperty)field_176259_O, Boolean.valueOf(false)).withProperty((IProperty)field_176255_P, EnumType.NORMAL));
/*  35 */     setHardness(p_i45435_1_.blockHardness);
/*  36 */     setResistance(p_i45435_1_.blockResistance / 3.0F);
/*  37 */     setStepSound(p_i45435_1_.stepSound);
/*  38 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  43 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPassable(IBlockAccess blockAccess, BlockPos pos) {
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  58 */     boolean var3 = func_176253_e(access, pos.offsetNorth());
/*  59 */     boolean var4 = func_176253_e(access, pos.offsetSouth());
/*  60 */     boolean var5 = func_176253_e(access, pos.offsetWest());
/*  61 */     boolean var6 = func_176253_e(access, pos.offsetEast());
/*  62 */     float var7 = 0.25F;
/*  63 */     float var8 = 0.75F;
/*  64 */     float var9 = 0.25F;
/*  65 */     float var10 = 0.75F;
/*  66 */     float var11 = 1.0F;
/*     */     
/*  68 */     if (var3)
/*     */     {
/*  70 */       var9 = 0.0F;
/*     */     }
/*     */     
/*  73 */     if (var4)
/*     */     {
/*  75 */       var10 = 1.0F;
/*     */     }
/*     */     
/*  78 */     if (var5)
/*     */     {
/*  80 */       var7 = 0.0F;
/*     */     }
/*     */     
/*  83 */     if (var6)
/*     */     {
/*  85 */       var8 = 1.0F;
/*     */     }
/*     */     
/*  88 */     if (var3 && var4 && !var5 && !var6) {
/*     */       
/*  90 */       var11 = 0.8125F;
/*  91 */       var7 = 0.3125F;
/*  92 */       var8 = 0.6875F;
/*     */     }
/*  94 */     else if (!var3 && !var4 && var5 && var6) {
/*     */       
/*  96 */       var11 = 0.8125F;
/*  97 */       var9 = 0.3125F;
/*  98 */       var10 = 0.6875F;
/*     */     } 
/*     */     
/* 101 */     setBlockBounds(var7, 0.0F, var9, var8, var11, var10);
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 106 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/* 107 */     this.maxY = 1.5D;
/* 108 */     return super.getCollisionBoundingBox(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176253_e(IBlockAccess p_176253_1_, BlockPos p_176253_2_) {
/* 113 */     Block var3 = p_176253_1_.getBlockState(p_176253_2_).getBlock();
/* 114 */     return (var3 == Blocks.barrier) ? false : ((var3 != this && !(var3 instanceof BlockFenceGate)) ? ((var3.blockMaterial.isOpaque() && var3.isFullCube()) ? ((var3.blockMaterial != Material.gourd)) : false) : true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/* 122 */     EnumType[] var4 = EnumType.values();
/* 123 */     int var5 = var4.length;
/*     */     
/* 125 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 127 */       EnumType var7 = var4[var6];
/* 128 */       list.add(new ItemStack(itemIn, 1, var7.func_176657_a()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 137 */     return ((EnumType)state.getValue((IProperty)field_176255_P)).func_176657_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
/* 142 */     return (side == EnumFacing.DOWN) ? super.shouldSideBeRendered(worldIn, pos, side) : true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 150 */     return getDefaultState().withProperty((IProperty)field_176255_P, EnumType.func_176660_a(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 158 */     return ((EnumType)state.getValue((IProperty)field_176255_P)).func_176657_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 167 */     return state.withProperty((IProperty)field_176256_a, Boolean.valueOf(!worldIn.isAirBlock(pos.offsetUp()))).withProperty((IProperty)field_176254_b, Boolean.valueOf(func_176253_e(worldIn, pos.offsetNorth()))).withProperty((IProperty)field_176257_M, Boolean.valueOf(func_176253_e(worldIn, pos.offsetEast()))).withProperty((IProperty)field_176258_N, Boolean.valueOf(func_176253_e(worldIn, pos.offsetSouth()))).withProperty((IProperty)field_176259_O, Boolean.valueOf(func_176253_e(worldIn, pos.offsetWest())));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 172 */     return new BlockState(this, new IProperty[] { (IProperty)field_176256_a, (IProperty)field_176254_b, (IProperty)field_176257_M, (IProperty)field_176259_O, (IProperty)field_176258_N, (IProperty)field_176255_P });
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/* 177 */     NORMAL("NORMAL", 0, 0, "cobblestone", "normal"),
/* 178 */     MOSSY("MOSSY", 1, 1, "mossy_cobblestone", "mossy");
/* 179 */     private static final EnumType[] field_176666_c = new EnumType[(values()).length];
/*     */     
/*     */     private final int field_176663_d;
/*     */     private final String field_176664_e;
/*     */     private String field_176661_f;
/* 184 */     private static final EnumType[] $VALUES = new EnumType[] { NORMAL, MOSSY };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002048";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 225 */       EnumType[] var0 = values();
/* 226 */       int var1 = var0.length;
/*     */       
/* 228 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 230 */         EnumType var3 = var0[var2];
/* 231 */         field_176666_c[var3.func_176657_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45673_1_, int p_i45673_2_, int p_i45673_3_, String p_i45673_4_, String p_i45673_5_) {
/*     */       this.field_176663_d = p_i45673_3_;
/*     */       this.field_176664_e = p_i45673_4_;
/*     */       this.field_176661_f = p_i45673_5_;
/*     */     }
/*     */     
/*     */     public int func_176657_a() {
/*     */       return this.field_176663_d;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176664_e;
/*     */     }
/*     */     
/*     */     public static EnumType func_176660_a(int p_176660_0_) {
/*     */       if (p_176660_0_ < 0 || p_176660_0_ >= field_176666_c.length)
/*     */         p_176660_0_ = 0; 
/*     */       return field_176666_c[p_176660_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176664_e;
/*     */     }
/*     */     
/*     */     public String func_176659_c() {
/*     */       return this.field_176661_f;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */