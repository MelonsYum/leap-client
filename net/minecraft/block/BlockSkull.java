/*     */ package net.minecraft.block;
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyDirection;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.BlockWorldState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.block.state.pattern.BlockPattern;
/*     */ import net.minecraft.block.state.pattern.BlockStateHelper;
/*     */ import net.minecraft.block.state.pattern.FactoryBlockPattern;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.boss.EntityWither;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTUtil;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntitySkull;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockSkull extends BlockContainer {
/*  38 */   public static final PropertyDirection field_176418_a = PropertyDirection.create("facing");
/*  39 */   public static final PropertyBool field_176417_b = PropertyBool.create("nodrop");
/*  40 */   private static final Predicate field_176419_M = new Predicate()
/*     */     {
/*     */       private static final String __OBFID = "CL_00002065";
/*     */       
/*     */       public boolean func_177062_a(BlockWorldState p_177062_1_) {
/*  45 */         return (p_177062_1_.func_177509_a().getBlock() == Blocks.skull && p_177062_1_.func_177507_b() instanceof TileEntitySkull && ((TileEntitySkull)p_177062_1_.func_177507_b()).getSkullType() == 1);
/*     */       }
/*     */       
/*     */       public boolean apply(Object p_apply_1_) {
/*  49 */         return func_177062_a((BlockWorldState)p_apply_1_);
/*     */       }
/*     */     };
/*     */   
/*     */   private BlockPattern field_176420_N;
/*     */   private BlockPattern field_176421_O;
/*     */   private static final String __OBFID = "CL_00000307";
/*     */   
/*     */   protected BlockSkull() {
/*  58 */     super(Material.circuits);
/*  59 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176418_a, (Comparable)EnumFacing.NORTH).withProperty((IProperty)field_176417_b, Boolean.valueOf(false)));
/*  60 */     setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  70 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/*  75 */     switch (SwitchEnumFacing.field_177063_a[((EnumFacing)access.getBlockState(pos).getValue((IProperty)field_176418_a)).ordinal()]) {
/*     */ 
/*     */       
/*     */       default:
/*  79 */         setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
/*     */         return;
/*     */       
/*     */       case 2:
/*  83 */         setBlockBounds(0.25F, 0.25F, 0.5F, 0.75F, 0.75F, 1.0F);
/*     */         return;
/*     */       
/*     */       case 3:
/*  87 */         setBlockBounds(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 0.5F);
/*     */         return;
/*     */       
/*     */       case 4:
/*  91 */         setBlockBounds(0.5F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F); return;
/*     */       case 5:
/*     */         break;
/*     */     } 
/*  95 */     setBlockBounds(0.0F, 0.25F, 0.25F, 0.5F, 0.75F, 0.75F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/* 101 */     setBlockBoundsBasedOnState((IBlockAccess)worldIn, pos);
/* 102 */     return super.getCollisionBoundingBox(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 107 */     return getDefaultState().withProperty((IProperty)field_176418_a, (Comparable)placer.func_174811_aO()).withProperty((IProperty)field_176417_b, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 115 */     return (TileEntity)new TileEntitySkull();
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/* 120 */     return Items.skull;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/* 125 */     TileEntity var3 = worldIn.getTileEntity(pos);
/* 126 */     return (var3 instanceof TileEntitySkull) ? ((TileEntitySkull)var3).getSkullType() : super.getDamageValue(worldIn, pos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
/* 139 */     if (playerIn.capabilities.isCreativeMode) {
/*     */       
/* 141 */       state = state.withProperty((IProperty)field_176417_b, Boolean.valueOf(true));
/* 142 */       worldIn.setBlockState(pos, state, 4);
/*     */     } 
/*     */     
/* 145 */     super.onBlockHarvested(worldIn, pos, state, playerIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 150 */     if (!worldIn.isRemote) {
/*     */       
/* 152 */       if (!((Boolean)state.getValue((IProperty)field_176417_b)).booleanValue()) {
/*     */         
/* 154 */         TileEntity var4 = worldIn.getTileEntity(pos);
/*     */         
/* 156 */         if (var4 instanceof TileEntitySkull) {
/*     */           
/* 158 */           TileEntitySkull var5 = (TileEntitySkull)var4;
/* 159 */           ItemStack var6 = new ItemStack(Items.skull, 1, getDamageValue(worldIn, pos));
/*     */           
/* 161 */           if (var5.getSkullType() == 3 && var5.getPlayerProfile() != null) {
/*     */             
/* 163 */             var6.setTagCompound(new NBTTagCompound());
/* 164 */             NBTTagCompound var7 = new NBTTagCompound();
/* 165 */             NBTUtil.writeGameProfile(var7, var5.getPlayerProfile());
/* 166 */             var6.getTagCompound().setTag("SkullOwner", (NBTBase)var7);
/*     */           } 
/*     */           
/* 169 */           spawnAsEntity(worldIn, pos, var6);
/*     */         } 
/*     */       } 
/*     */       
/* 173 */       super.breakBlock(worldIn, pos, state);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/* 184 */     return Items.skull;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_176415_b(World worldIn, BlockPos p_176415_2_, ItemStack p_176415_3_) {
/* 189 */     return (p_176415_3_.getMetadata() == 1 && p_176415_2_.getY() >= 2 && worldIn.getDifficulty() != EnumDifficulty.PEACEFUL && !worldIn.isRemote) ? ((func_176414_j().func_177681_a(worldIn, p_176415_2_) != null)) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180679_a(World worldIn, BlockPos p_180679_2_, TileEntitySkull p_180679_3_) {
/* 194 */     if (p_180679_3_.getSkullType() == 1 && p_180679_2_.getY() >= 2 && worldIn.getDifficulty() != EnumDifficulty.PEACEFUL && !worldIn.isRemote) {
/*     */       
/* 196 */       BlockPattern var4 = func_176416_l();
/* 197 */       BlockPattern.PatternHelper var5 = var4.func_177681_a(worldIn, p_180679_2_);
/*     */       
/* 199 */       if (var5 != null) {
/*     */         int var6;
/*     */ 
/*     */         
/* 203 */         for (var6 = 0; var6 < 3; var6++) {
/*     */           
/* 205 */           BlockWorldState var7 = var5.func_177670_a(var6, 0, 0);
/* 206 */           worldIn.setBlockState(var7.getPos(), var7.func_177509_a().withProperty((IProperty)field_176417_b, Boolean.valueOf(true)), 2);
/*     */         } 
/*     */         
/* 209 */         for (var6 = 0; var6 < var4.func_177684_c(); var6++) {
/*     */           
/* 211 */           for (int var13 = 0; var13 < var4.func_177685_b(); var13++) {
/*     */             
/* 213 */             BlockWorldState var8 = var5.func_177670_a(var6, var13, 0);
/* 214 */             worldIn.setBlockState(var8.getPos(), Blocks.air.getDefaultState(), 2);
/*     */           } 
/*     */         } 
/*     */         
/* 218 */         BlockPos var12 = var5.func_177670_a(1, 0, 0).getPos();
/* 219 */         EntityWither var14 = new EntityWither(worldIn);
/* 220 */         BlockPos var15 = var5.func_177670_a(1, 2, 0).getPos();
/* 221 */         var14.setLocationAndAngles(var15.getX() + 0.5D, var15.getY() + 0.55D, var15.getZ() + 0.5D, (var5.func_177669_b().getAxis() == EnumFacing.Axis.X) ? 0.0F : 90.0F, 0.0F);
/* 222 */         var14.renderYawOffset = (var5.func_177669_b().getAxis() == EnumFacing.Axis.X) ? 0.0F : 90.0F;
/* 223 */         var14.func_82206_m();
/* 224 */         Iterator<EntityPlayer> var9 = worldIn.getEntitiesWithinAABB(EntityPlayer.class, var14.getEntityBoundingBox().expand(50.0D, 50.0D, 50.0D)).iterator();
/*     */         
/* 226 */         while (var9.hasNext()) {
/*     */           
/* 228 */           EntityPlayer var10 = var9.next();
/* 229 */           var10.triggerAchievement((StatBase)AchievementList.spawnWither);
/*     */         } 
/*     */         
/* 232 */         worldIn.spawnEntityInWorld((Entity)var14);
/*     */         
/*     */         int var16;
/* 235 */         for (var16 = 0; var16 < 120; var16++)
/*     */         {
/* 237 */           worldIn.spawnParticle(EnumParticleTypes.SNOWBALL, var12.getX() + worldIn.rand.nextDouble(), (var12.getY() - 2) + worldIn.rand.nextDouble() * 3.9D, var12.getZ() + worldIn.rand.nextDouble(), 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         }
/*     */         
/* 240 */         for (var16 = 0; var16 < var4.func_177684_c(); var16++) {
/*     */           
/* 242 */           for (int var17 = 0; var17 < var4.func_177685_b(); var17++) {
/*     */             
/* 244 */             BlockWorldState var11 = var5.func_177670_a(var16, var17, 0);
/* 245 */             worldIn.func_175722_b(var11.getPos(), Blocks.air);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 257 */     return getDefaultState().withProperty((IProperty)field_176418_a, (Comparable)EnumFacing.getFront(meta & 0x7)).withProperty((IProperty)field_176417_b, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 265 */     byte var2 = 0;
/* 266 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)field_176418_a)).getIndex();
/*     */     
/* 268 */     if (((Boolean)state.getValue((IProperty)field_176417_b)).booleanValue())
/*     */     {
/* 270 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 273 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 278 */     return new BlockState(this, new IProperty[] { (IProperty)field_176418_a, (IProperty)field_176417_b });
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockPattern func_176414_j() {
/* 283 */     if (this.field_176420_N == null)
/*     */     {
/* 285 */       this.field_176420_N = FactoryBlockPattern.start().aisle(new String[] { "   ", "###", "~#~" }).where('#', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.soul_sand))).where('~', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.air))).build();
/*     */     }
/*     */     
/* 288 */     return this.field_176420_N;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockPattern func_176416_l() {
/* 293 */     if (this.field_176421_O == null)
/*     */     {
/* 295 */       this.field_176421_O = FactoryBlockPattern.start().aisle(new String[] { "^^^", "###", "~#~" }).where('#', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.soul_sand))).where('^', field_176419_M).where('~', BlockWorldState.hasState((Predicate)BlockStateHelper.forBlock(Blocks.air))).build();
/*     */     }
/*     */     
/* 298 */     return this.field_176421_O;
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 303 */     static final int[] field_177063_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002064";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 310 */         field_177063_a[EnumFacing.UP.ordinal()] = 1;
/*     */       }
/* 312 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 319 */         field_177063_a[EnumFacing.NORTH.ordinal()] = 2;
/*     */       }
/* 321 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 328 */         field_177063_a[EnumFacing.SOUTH.ordinal()] = 3;
/*     */       }
/* 330 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 337 */         field_177063_a[EnumFacing.WEST.ordinal()] = 4;
/*     */       }
/* 339 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 346 */         field_177063_a[EnumFacing.EAST.ordinal()] = 5;
/*     */       }
/* 348 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */