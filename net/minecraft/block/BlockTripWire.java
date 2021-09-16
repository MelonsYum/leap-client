/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumWorldBlockLayer;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockTripWire
/*     */   extends Block {
/*  25 */   public static final PropertyBool field_176293_a = PropertyBool.create("powered");
/*  26 */   public static final PropertyBool field_176290_b = PropertyBool.create("suspended");
/*  27 */   public static final PropertyBool field_176294_M = PropertyBool.create("attached");
/*  28 */   public static final PropertyBool field_176295_N = PropertyBool.create("disarmed");
/*  29 */   public static final PropertyBool field_176296_O = PropertyBool.create("north");
/*  30 */   public static final PropertyBool field_176291_P = PropertyBool.create("east");
/*  31 */   public static final PropertyBool field_176289_Q = PropertyBool.create("south");
/*  32 */   public static final PropertyBool field_176292_R = PropertyBool.create("west");
/*     */   
/*     */   private static final String __OBFID = "CL_00000328";
/*     */   
/*     */   public BlockTripWire() {
/*  37 */     super(Material.circuits);
/*  38 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176293_a, Boolean.valueOf(false)).withProperty((IProperty)field_176290_b, Boolean.valueOf(false)).withProperty((IProperty)field_176294_M, Boolean.valueOf(false)).withProperty((IProperty)field_176295_N, Boolean.valueOf(false)).withProperty((IProperty)field_176296_O, Boolean.valueOf(false)).withProperty((IProperty)field_176291_P, Boolean.valueOf(false)).withProperty((IProperty)field_176289_Q, Boolean.valueOf(false)).withProperty((IProperty)field_176292_R, Boolean.valueOf(false)));
/*  39 */     setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.15625F, 1.0F);
/*  40 */     setTickRandomly(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  49 */     return state.withProperty((IProperty)field_176296_O, Boolean.valueOf(func_176287_c(worldIn, pos, state, EnumFacing.NORTH))).withProperty((IProperty)field_176291_P, Boolean.valueOf(func_176287_c(worldIn, pos, state, EnumFacing.EAST))).withProperty((IProperty)field_176289_Q, Boolean.valueOf(func_176287_c(worldIn, pos, state, EnumFacing.SOUTH))).withProperty((IProperty)field_176292_R, Boolean.valueOf(func_176287_c(worldIn, pos, state, EnumFacing.WEST)));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBoundingBox(World worldIn, BlockPos pos, IBlockState state) {
/*  54 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpaqueCube() {
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFullCube() {
/*  64 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumWorldBlockLayer getBlockLayer() {
/*  69 */     return EnumWorldBlockLayer.TRANSLUCENT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  79 */     return Items.string;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/*  84 */     return Items.string;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
/*  89 */     boolean var5 = ((Boolean)state.getValue((IProperty)field_176290_b)).booleanValue();
/*  90 */     boolean var6 = !World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown());
/*     */     
/*  92 */     if (var5 != var6) {
/*     */       
/*  94 */       dropBlockAsItem(worldIn, pos, state, 0);
/*  95 */       worldIn.setBlockToAir(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockBoundsBasedOnState(IBlockAccess access, BlockPos pos) {
/* 101 */     IBlockState var3 = access.getBlockState(pos);
/* 102 */     boolean var4 = ((Boolean)var3.getValue((IProperty)field_176294_M)).booleanValue();
/* 103 */     boolean var5 = ((Boolean)var3.getValue((IProperty)field_176290_b)).booleanValue();
/*     */     
/* 105 */     if (!var5) {
/*     */       
/* 107 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.09375F, 1.0F);
/*     */     }
/* 109 */     else if (!var4) {
/*     */       
/* 111 */       setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
/*     */     }
/*     */     else {
/*     */       
/* 115 */       setBlockBounds(0.0F, 0.0625F, 0.0F, 1.0F, 0.15625F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 121 */     state = state.withProperty((IProperty)field_176290_b, Boolean.valueOf(!World.doesBlockHaveSolidTopSurface((IBlockAccess)worldIn, pos.offsetDown())));
/* 122 */     worldIn.setBlockState(pos, state, 3);
/* 123 */     func_176286_e(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 128 */     func_176286_e(worldIn, pos, state.withProperty((IProperty)field_176293_a, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn) {
/* 133 */     if (!worldIn.isRemote)
/*     */     {
/* 135 */       if (playerIn.getCurrentEquippedItem() != null && playerIn.getCurrentEquippedItem().getItem() == Items.shears)
/*     */       {
/* 137 */         worldIn.setBlockState(pos, state.withProperty((IProperty)field_176295_N, Boolean.valueOf(true)), 4);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176286_e(World worldIn, BlockPos p_176286_2_, IBlockState p_176286_3_) {
/* 144 */     EnumFacing[] var4 = { EnumFacing.SOUTH, EnumFacing.WEST };
/* 145 */     int var5 = var4.length;
/* 146 */     int var6 = 0;
/*     */     
/* 148 */     while (var6 < var5) {
/*     */       
/* 150 */       EnumFacing var7 = var4[var6];
/* 151 */       int var8 = 1;
/*     */ 
/*     */ 
/*     */       
/* 155 */       while (var8 < 42) {
/*     */         
/* 157 */         BlockPos var9 = p_176286_2_.offset(var7, var8);
/* 158 */         IBlockState var10 = worldIn.getBlockState(var9);
/*     */         
/* 160 */         if (var10.getBlock() == Blocks.tripwire_hook) {
/*     */           
/* 162 */           if (var10.getValue((IProperty)BlockTripWireHook.field_176264_a) == var7.getOpposite())
/*     */           {
/* 164 */             Blocks.tripwire_hook.func_176260_a(worldIn, var9, var10, false, true, var8, p_176286_3_); } 
/*     */           break;
/*     */         } 
/* 167 */         if (var10.getBlock() == Blocks.tripwire)
/*     */         {
/* 169 */           var8++;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 174 */       var6++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
/* 185 */     if (!worldIn.isRemote)
/*     */     {
/* 187 */       if (!((Boolean)state.getValue((IProperty)field_176293_a)).booleanValue())
/*     */       {
/* 189 */         func_176288_d(worldIn, pos);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 201 */     if (!worldIn.isRemote)
/*     */     {
/* 203 */       if (((Boolean)worldIn.getBlockState(pos).getValue((IProperty)field_176293_a)).booleanValue())
/*     */       {
/* 205 */         func_176288_d(worldIn, pos);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176288_d(World worldIn, BlockPos p_176288_2_) {
/* 212 */     IBlockState var3 = worldIn.getBlockState(p_176288_2_);
/* 213 */     boolean var4 = ((Boolean)var3.getValue((IProperty)field_176293_a)).booleanValue();
/* 214 */     boolean var5 = false;
/* 215 */     List var6 = worldIn.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(p_176288_2_.getX() + this.minX, p_176288_2_.getY() + this.minY, p_176288_2_.getZ() + this.minZ, p_176288_2_.getX() + this.maxX, p_176288_2_.getY() + this.maxY, p_176288_2_.getZ() + this.maxZ));
/*     */     
/* 217 */     if (!var6.isEmpty()) {
/*     */       
/* 219 */       Iterator<Entity> var7 = var6.iterator();
/*     */       
/* 221 */       while (var7.hasNext()) {
/*     */         
/* 223 */         Entity var8 = var7.next();
/*     */         
/* 225 */         if (!var8.doesEntityNotTriggerPressurePlate()) {
/*     */           
/* 227 */           var5 = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 233 */     if (var5 != var4) {
/*     */       
/* 235 */       var3 = var3.withProperty((IProperty)field_176293_a, Boolean.valueOf(var5));
/* 236 */       worldIn.setBlockState(p_176288_2_, var3, 3);
/* 237 */       func_176286_e(worldIn, p_176288_2_, var3);
/*     */     } 
/*     */     
/* 240 */     if (var5)
/*     */     {
/* 242 */       worldIn.scheduleUpdate(p_176288_2_, this, tickRate(worldIn));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_176287_c(IBlockAccess p_176287_0_, BlockPos p_176287_1_, IBlockState p_176287_2_, EnumFacing p_176287_3_) {
/* 248 */     BlockPos var4 = p_176287_1_.offset(p_176287_3_);
/* 249 */     IBlockState var5 = p_176287_0_.getBlockState(var4);
/* 250 */     Block var6 = var5.getBlock();
/*     */     
/* 252 */     if (var6 == Blocks.tripwire_hook) {
/*     */       
/* 254 */       EnumFacing var9 = p_176287_3_.getOpposite();
/* 255 */       return (var5.getValue((IProperty)BlockTripWireHook.field_176264_a) == var9);
/*     */     } 
/* 257 */     if (var6 == Blocks.tripwire) {
/*     */       
/* 259 */       boolean var7 = ((Boolean)p_176287_2_.getValue((IProperty)field_176290_b)).booleanValue();
/* 260 */       boolean var8 = ((Boolean)var5.getValue((IProperty)field_176290_b)).booleanValue();
/* 261 */       return (var7 == var8);
/*     */     } 
/*     */ 
/*     */     
/* 265 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 274 */     return getDefaultState().withProperty((IProperty)field_176293_a, Boolean.valueOf(((meta & 0x1) > 0))).withProperty((IProperty)field_176290_b, Boolean.valueOf(((meta & 0x2) > 0))).withProperty((IProperty)field_176294_M, Boolean.valueOf(((meta & 0x4) > 0))).withProperty((IProperty)field_176295_N, Boolean.valueOf(((meta & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 282 */     int var2 = 0;
/*     */     
/* 284 */     if (((Boolean)state.getValue((IProperty)field_176293_a)).booleanValue())
/*     */     {
/* 286 */       var2 |= 0x1;
/*     */     }
/*     */     
/* 289 */     if (((Boolean)state.getValue((IProperty)field_176290_b)).booleanValue())
/*     */     {
/* 291 */       var2 |= 0x2;
/*     */     }
/*     */     
/* 294 */     if (((Boolean)state.getValue((IProperty)field_176294_M)).booleanValue())
/*     */     {
/* 296 */       var2 |= 0x4;
/*     */     }
/*     */     
/* 299 */     if (((Boolean)state.getValue((IProperty)field_176295_N)).booleanValue())
/*     */     {
/* 301 */       var2 |= 0x8;
/*     */     }
/*     */     
/* 304 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 309 */     return new BlockState(this, new IProperty[] { (IProperty)field_176293_a, (IProperty)field_176290_b, (IProperty)field_176294_M, (IProperty)field_176295_N, (IProperty)field_176296_O, (IProperty)field_176291_P, (IProperty)field_176292_R, (IProperty)field_176289_Q });
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockTripWire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */