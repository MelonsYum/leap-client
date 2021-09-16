/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeColorHelper;
/*     */ 
/*     */ public class BlockDoublePlant
/*     */   extends BlockBush implements IGrowable {
/*  27 */   public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", EnumPlantType.class);
/*  28 */   public static final PropertyEnum HALF_PROP = PropertyEnum.create("half", EnumBlockHalf.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000231";
/*     */   
/*     */   public BlockDoublePlant() {
/*  33 */     super(Material.vine);
/*  34 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT_PROP, EnumPlantType.SUNFLOWER).withProperty((IProperty)HALF_PROP, EnumBlockHalf.LOWER));
/*  35 */     setHardness(0.0F);
/*  36 */     setStepSound(soundTypeGrass);
/*  37 */     setUnlocalizedName("doublePlant");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  42 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPlantType func_176490_e(IBlockAccess p_176490_1_, BlockPos p_176490_2_) {
/*  47 */     IBlockState var3 = p_176490_1_.getBlockState(p_176490_2_);
/*     */     
/*  49 */     if (var3.getBlock() == this) {
/*     */       
/*  51 */       var3 = getActualState(var3, p_176490_1_, p_176490_2_);
/*  52 */       return (EnumPlantType)var3.getValue((IProperty)VARIANT_PROP);
/*     */     } 
/*     */ 
/*     */     
/*  56 */     return EnumPlantType.FERN;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
/*  62 */     return (super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.offsetUp()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReplaceable(World worldIn, BlockPos pos) {
/*  70 */     IBlockState var3 = worldIn.getBlockState(pos);
/*     */     
/*  72 */     if (var3.getBlock() != this)
/*     */     {
/*  74 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  78 */     EnumPlantType var4 = (EnumPlantType)getActualState(var3, (IBlockAccess)worldIn, pos).getValue((IProperty)VARIANT_PROP);
/*  79 */     return !(var4 != EnumPlantType.FERN && var4 != EnumPlantType.GRASS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_176475_e(World worldIn, BlockPos p_176475_2_, IBlockState p_176475_3_) {
/*  85 */     if (!canBlockStay(worldIn, p_176475_2_, p_176475_3_)) {
/*     */       
/*  87 */       boolean var4 = (p_176475_3_.getValue((IProperty)HALF_PROP) == EnumBlockHalf.UPPER);
/*  88 */       BlockPos var5 = var4 ? p_176475_2_ : p_176475_2_.offsetUp();
/*  89 */       BlockPos var6 = var4 ? p_176475_2_.offsetDown() : p_176475_2_;
/*  90 */       Object var7 = var4 ? this : worldIn.getBlockState(var5).getBlock();
/*  91 */       Object var8 = var4 ? worldIn.getBlockState(var6).getBlock() : this;
/*     */       
/*  93 */       if (var7 == this)
/*     */       {
/*  95 */         worldIn.setBlockState(var5, Blocks.air.getDefaultState(), 3);
/*     */       }
/*     */       
/*  98 */       if (var8 == this) {
/*     */         
/* 100 */         worldIn.setBlockState(var6, Blocks.air.getDefaultState(), 3);
/*     */         
/* 102 */         if (!var4)
/*     */         {
/* 104 */           dropBlockAsItem(worldIn, var6, p_176475_3_, 0);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBlockStay(World worldIn, BlockPos p_180671_2_, IBlockState p_180671_3_) {
/* 112 */     if (p_180671_3_.getValue((IProperty)HALF_PROP) == EnumBlockHalf.UPPER)
/*     */     {
/* 114 */       return (worldIn.getBlockState(p_180671_2_.offsetDown()).getBlock() == this);
/*     */     }
/*     */ 
/*     */     
/* 118 */     IBlockState var4 = worldIn.getBlockState(p_180671_2_.offsetUp());
/* 119 */     return (var4.getBlock() == this && super.canBlockStay(worldIn, p_180671_2_, var4));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 130 */     if (state.getValue((IProperty)HALF_PROP) == EnumBlockHalf.UPPER)
/*     */     {
/* 132 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 136 */     EnumPlantType var4 = (EnumPlantType)state.getValue((IProperty)VARIANT_PROP);
/* 137 */     return (var4 == EnumPlantType.FERN) ? null : ((var4 == EnumPlantType.GRASS) ? ((rand.nextInt(8) == 0) ? Items.wheat_seeds : null) : Item.getItemFromBlock(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/* 146 */     return (state.getValue((IProperty)HALF_PROP) != EnumBlockHalf.UPPER && state.getValue((IProperty)VARIANT_PROP) != EnumPlantType.GRASS) ? ((EnumPlantType)state.getValue((IProperty)VARIANT_PROP)).func_176936_a() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
/* 151 */     EnumPlantType var4 = func_176490_e(worldIn, pos);
/* 152 */     return (var4 != EnumPlantType.GRASS && var4 != EnumPlantType.FERN) ? 16777215 : BiomeColorHelper.func_180286_a(worldIn, pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176491_a(World worldIn, BlockPos p_176491_2_, EnumPlantType p_176491_3_, int p_176491_4_) {
/* 157 */     worldIn.setBlockState(p_176491_2_, getDefaultState().withProperty((IProperty)HALF_PROP, EnumBlockHalf.LOWER).withProperty((IProperty)VARIANT_PROP, p_176491_3_), p_176491_4_);
/* 158 */     worldIn.setBlockState(p_176491_2_.offsetUp(), getDefaultState().withProperty((IProperty)HALF_PROP, EnumBlockHalf.UPPER), p_176491_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
/* 163 */     worldIn.setBlockState(pos.offsetUp(), getDefaultState().withProperty((IProperty)HALF_PROP, EnumBlockHalf.UPPER), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te) {
/* 168 */     if (worldIn.isRemote || playerIn.getCurrentEquippedItem() == null || playerIn.getCurrentEquippedItem().getItem() != Items.shears || state.getValue((IProperty)HALF_PROP) != EnumBlockHalf.LOWER || !func_176489_b(worldIn, pos, state, playerIn))
/*     */     {
/* 170 */       super.harvestBlock(worldIn, playerIn, pos, state, te);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
/* 176 */     if (state.getValue((IProperty)HALF_PROP) == EnumBlockHalf.UPPER) {
/*     */       
/* 178 */       if (worldIn.getBlockState(pos.offsetDown()).getBlock() == this)
/*     */       {
/* 180 */         if (!playerIn.capabilities.isCreativeMode) {
/*     */           
/* 182 */           IBlockState var5 = worldIn.getBlockState(pos.offsetDown());
/* 183 */           EnumPlantType var6 = (EnumPlantType)var5.getValue((IProperty)VARIANT_PROP);
/*     */           
/* 185 */           if (var6 != EnumPlantType.FERN && var6 != EnumPlantType.GRASS) {
/*     */             
/* 187 */             worldIn.destroyBlock(pos.offsetDown(), true);
/*     */           }
/* 189 */           else if (!worldIn.isRemote) {
/*     */             
/* 191 */             if (playerIn.getCurrentEquippedItem() != null && playerIn.getCurrentEquippedItem().getItem() == Items.shears)
/*     */             {
/* 193 */               func_176489_b(worldIn, pos, var5, playerIn);
/* 194 */               worldIn.setBlockToAir(pos.offsetDown());
/*     */             }
/*     */             else
/*     */             {
/* 198 */               worldIn.destroyBlock(pos.offsetDown(), true);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 203 */             worldIn.setBlockToAir(pos.offsetDown());
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 208 */           worldIn.setBlockToAir(pos.offsetDown());
/*     */         }
/*     */       
/*     */       }
/* 212 */     } else if (playerIn.capabilities.isCreativeMode && worldIn.getBlockState(pos.offsetUp()).getBlock() == this) {
/*     */       
/* 214 */       worldIn.setBlockState(pos.offsetUp(), Blocks.air.getDefaultState(), 2);
/*     */     } 
/*     */     
/* 217 */     super.onBlockHarvested(worldIn, pos, state, playerIn);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176489_b(World worldIn, BlockPos p_176489_2_, IBlockState p_176489_3_, EntityPlayer p_176489_4_) {
/* 222 */     EnumPlantType var5 = (EnumPlantType)p_176489_3_.getValue((IProperty)VARIANT_PROP);
/*     */     
/* 224 */     if (var5 != EnumPlantType.FERN && var5 != EnumPlantType.GRASS)
/*     */     {
/* 226 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 230 */     p_176489_4_.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
/* 231 */     int var6 = ((var5 == EnumPlantType.GRASS) ? BlockTallGrass.EnumType.GRASS : BlockTallGrass.EnumType.FERN).func_177044_a();
/* 232 */     spawnAsEntity(worldIn, p_176489_2_, new ItemStack(Blocks.tallgrass, 2, var6));
/* 233 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/* 242 */     EnumPlantType[] var4 = EnumPlantType.values();
/* 243 */     int var5 = var4.length;
/*     */     
/* 245 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 247 */       EnumPlantType var7 = var4[var6];
/* 248 */       list.add(new ItemStack(itemIn, 1, var7.func_176936_a()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/* 254 */     return func_176490_e((IBlockAccess)worldIn, pos).func_176936_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStillGrowing(World worldIn, BlockPos p_176473_2_, IBlockState p_176473_3_, boolean p_176473_4_) {
/* 259 */     EnumPlantType var5 = func_176490_e((IBlockAccess)worldIn, p_176473_2_);
/* 260 */     return (var5 != EnumPlantType.GRASS && var5 != EnumPlantType.FERN);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUseBonemeal(World worldIn, Random p_180670_2_, BlockPos p_180670_3_, IBlockState p_180670_4_) {
/* 265 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void grow(World worldIn, Random p_176474_2_, BlockPos p_176474_3_, IBlockState p_176474_4_) {
/* 270 */     spawnAsEntity(worldIn, p_176474_3_, new ItemStack(this, 1, func_176490_e((IBlockAccess)worldIn, p_176474_3_).func_176936_a()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 278 */     return ((meta & 0x8) > 0) ? getDefaultState().withProperty((IProperty)HALF_PROP, EnumBlockHalf.UPPER) : getDefaultState().withProperty((IProperty)HALF_PROP, EnumBlockHalf.LOWER).withProperty((IProperty)VARIANT_PROP, EnumPlantType.func_176938_a(meta & 0x7));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/* 287 */     if (state.getValue((IProperty)HALF_PROP) == EnumBlockHalf.UPPER) {
/*     */       
/* 289 */       IBlockState var4 = worldIn.getBlockState(pos.offsetDown());
/*     */       
/* 291 */       if (var4.getBlock() == this)
/*     */       {
/* 293 */         state = state.withProperty((IProperty)VARIANT_PROP, var4.getValue((IProperty)VARIANT_PROP));
/*     */       }
/*     */     } 
/*     */     
/* 297 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 305 */     return (state.getValue((IProperty)HALF_PROP) == EnumBlockHalf.UPPER) ? 8 : ((EnumPlantType)state.getValue((IProperty)VARIANT_PROP)).func_176936_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 310 */     return new BlockState(this, new IProperty[] { (IProperty)HALF_PROP, (IProperty)VARIANT_PROP });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Block.EnumOffsetType getOffsetType() {
/* 318 */     return Block.EnumOffsetType.XZ;
/*     */   }
/*     */   
/*     */   enum EnumBlockHalf
/*     */     implements IStringSerializable {
/* 323 */     UPPER("UPPER", 0),
/* 324 */     LOWER("LOWER", 1);
/*     */     
/* 326 */     private static final EnumBlockHalf[] $VALUES = new EnumBlockHalf[] { UPPER, LOWER };
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002122";
/*     */ 
/*     */     
/*     */     public String toString() {
/* 333 */       return getName();
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String getName() {
/* 338 */       return (this == UPPER) ? "upper" : "lower";
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumPlantType
/*     */     implements IStringSerializable {
/* 344 */     SUNFLOWER("SUNFLOWER", 0, 0, "sunflower"),
/* 345 */     SYRINGA("SYRINGA", 1, 1, "syringa"),
/* 346 */     GRASS("GRASS", 2, 2, "double_grass", "grass"),
/* 347 */     FERN("FERN", 3, 3, "double_fern", "fern"),
/* 348 */     ROSE("ROSE", 4, 4, "double_rose", "rose"),
/* 349 */     PAEONIA("PAEONIA", 5, 5, "paeonia");
/* 350 */     private static final EnumPlantType[] field_176941_g = new EnumPlantType[(values()).length];
/*     */     
/*     */     private final int field_176949_h;
/*     */     private final String field_176950_i;
/*     */     private final String field_176947_j;
/* 355 */     private static final EnumPlantType[] $VALUES = new EnumPlantType[] { SUNFLOWER, SYRINGA, GRASS, FERN, ROSE, PAEONIA };
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
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002121";
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
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 401 */       EnumPlantType[] var0 = values();
/* 402 */       int var1 = var0.length;
/*     */       
/* 404 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 406 */         EnumPlantType var3 = var0[var2];
/* 407 */         field_176941_g[var3.func_176936_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumPlantType(String p_i45723_1_, int p_i45723_2_, int p_i45723_3_, String p_i45723_4_, String p_i45723_5_) {
/*     */       this.field_176949_h = p_i45723_3_;
/*     */       this.field_176950_i = p_i45723_4_;
/*     */       this.field_176947_j = p_i45723_5_;
/*     */     }
/*     */     
/*     */     public int func_176936_a() {
/*     */       return this.field_176949_h;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176950_i;
/*     */     }
/*     */     
/*     */     public static EnumPlantType func_176938_a(int p_176938_0_) {
/*     */       if (p_176938_0_ < 0 || p_176938_0_ >= field_176941_g.length)
/*     */         p_176938_0_ = 0; 
/*     */       return field_176941_g[p_176938_0_];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176950_i;
/*     */     }
/*     */     
/*     */     public String func_176939_c() {
/*     */       return this.field_176947_j;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockDoublePlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */