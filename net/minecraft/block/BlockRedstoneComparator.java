/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyBool;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItemFrame;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityComparator;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockRedstoneComparator
/*     */   extends BlockRedstoneDiode implements ITileEntityProvider {
/*  30 */   public static final PropertyBool field_176464_a = PropertyBool.create("powered");
/*  31 */   public static final PropertyEnum field_176463_b = PropertyEnum.create("mode", Mode.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000220";
/*     */   
/*     */   public BlockRedstoneComparator(boolean p_i45399_1_) {
/*  36 */     super(p_i45399_1_);
/*  37 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)AGE, (Comparable)EnumFacing.NORTH).withProperty((IProperty)field_176464_a, Boolean.valueOf(false)).withProperty((IProperty)field_176463_b, Mode.COMPARE));
/*  38 */     this.isBlockContainer = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  48 */     return Items.comparator;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/*  53 */     return Items.comparator;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176403_d(IBlockState p_176403_1_) {
/*  58 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockState func_180674_e(IBlockState p_180674_1_) {
/*  63 */     Boolean var2 = (Boolean)p_180674_1_.getValue((IProperty)field_176464_a);
/*  64 */     Mode var3 = (Mode)p_180674_1_.getValue((IProperty)field_176463_b);
/*  65 */     EnumFacing var4 = (EnumFacing)p_180674_1_.getValue((IProperty)AGE);
/*  66 */     return Blocks.powered_comparator.getDefaultState().withProperty((IProperty)AGE, (Comparable)var4).withProperty((IProperty)field_176464_a, var2).withProperty((IProperty)field_176463_b, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockState func_180675_k(IBlockState p_180675_1_) {
/*  71 */     Boolean var2 = (Boolean)p_180675_1_.getValue((IProperty)field_176464_a);
/*  72 */     Mode var3 = (Mode)p_180675_1_.getValue((IProperty)field_176463_b);
/*  73 */     EnumFacing var4 = (EnumFacing)p_180675_1_.getValue((IProperty)AGE);
/*  74 */     return Blocks.unpowered_comparator.getDefaultState().withProperty((IProperty)AGE, (Comparable)var4).withProperty((IProperty)field_176464_a, var2).withProperty((IProperty)field_176463_b, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_176406_l(IBlockState p_176406_1_) {
/*  79 */     return !(!this.isRepeaterPowered && !((Boolean)p_176406_1_.getValue((IProperty)field_176464_a)).booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_176408_a(IBlockAccess p_176408_1_, BlockPos p_176408_2_, IBlockState p_176408_3_) {
/*  84 */     TileEntity var4 = p_176408_1_.getTileEntity(p_176408_2_);
/*  85 */     return (var4 instanceof TileEntityComparator) ? ((TileEntityComparator)var4).getOutputSignal() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_176460_j(World worldIn, BlockPos p_176460_2_, IBlockState p_176460_3_) {
/*  90 */     return (p_176460_3_.getValue((IProperty)field_176463_b) == Mode.SUBTRACT) ? Math.max(func_176397_f(worldIn, p_176460_2_, p_176460_3_) - func_176407_c((IBlockAccess)worldIn, p_176460_2_, p_176460_3_), 0) : func_176397_f(worldIn, p_176460_2_, p_176460_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_176404_e(World worldIn, BlockPos p_176404_2_, IBlockState p_176404_3_) {
/*  95 */     int var4 = func_176397_f(worldIn, p_176404_2_, p_176404_3_);
/*     */     
/*  97 */     if (var4 >= 15)
/*     */     {
/*  99 */       return true;
/*     */     }
/* 101 */     if (var4 == 0)
/*     */     {
/* 103 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 107 */     int var5 = func_176407_c((IBlockAccess)worldIn, p_176404_2_, p_176404_3_);
/* 108 */     return (var5 == 0) ? true : ((var4 >= var5));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_176397_f(World worldIn, BlockPos p_176397_2_, IBlockState p_176397_3_) {
/* 114 */     int var4 = super.func_176397_f(worldIn, p_176397_2_, p_176397_3_);
/* 115 */     EnumFacing var5 = (EnumFacing)p_176397_3_.getValue((IProperty)AGE);
/* 116 */     BlockPos var6 = p_176397_2_.offset(var5);
/* 117 */     Block var7 = worldIn.getBlockState(var6).getBlock();
/*     */     
/* 119 */     if (var7.hasComparatorInputOverride()) {
/*     */       
/* 121 */       var4 = var7.getComparatorInputOverride(worldIn, var6);
/*     */     }
/* 123 */     else if (var4 < 15 && var7.isNormalCube()) {
/*     */       
/* 125 */       var6 = var6.offset(var5);
/* 126 */       var7 = worldIn.getBlockState(var6).getBlock();
/*     */       
/* 128 */       if (var7.hasComparatorInputOverride()) {
/*     */         
/* 130 */         var4 = var7.getComparatorInputOverride(worldIn, var6);
/*     */       }
/* 132 */       else if (var7.getMaterial() == Material.air) {
/*     */         
/* 134 */         EntityItemFrame var8 = func_176461_a(worldIn, var5, var6);
/*     */         
/* 136 */         if (var8 != null)
/*     */         {
/* 138 */           var4 = var8.func_174866_q();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 143 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private EntityItemFrame func_176461_a(World worldIn, final EnumFacing p_176461_2_, BlockPos p_176461_3_) {
/* 148 */     List<EntityItemFrame> var4 = worldIn.func_175647_a(EntityItemFrame.class, new AxisAlignedBB(p_176461_3_.getX(), p_176461_3_.getY(), p_176461_3_.getZ(), (p_176461_3_.getX() + 1), (p_176461_3_.getY() + 1), (p_176461_3_.getZ() + 1)), new Predicate()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002129";
/*     */           
/*     */           public boolean func_180416_a(Entity p_180416_1_) {
/* 153 */             return (p_180416_1_ != null && p_180416_1_.func_174811_aO() == p_176461_2_);
/*     */           }
/*     */           
/*     */           public boolean apply(Object p_apply_1_) {
/* 157 */             return func_180416_a((Entity)p_apply_1_);
/*     */           }
/*     */         });
/* 160 */     return (var4.size() == 1) ? var4.get(0) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
/* 165 */     if (!playerIn.capabilities.allowEdit)
/*     */     {
/* 167 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 171 */     state = state.cycleProperty((IProperty)field_176463_b);
/* 172 */     worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.click", 0.3F, (state.getValue((IProperty)field_176463_b) == Mode.SUBTRACT) ? 0.55F : 0.5F);
/* 173 */     worldIn.setBlockState(pos, state, 2);
/* 174 */     func_176462_k(worldIn, pos, state);
/* 175 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_176398_g(World worldIn, BlockPos p_176398_2_, IBlockState p_176398_3_) {
/* 181 */     if (!worldIn.isBlockTickPending(p_176398_2_, this)) {
/*     */       
/* 183 */       int var4 = func_176460_j(worldIn, p_176398_2_, p_176398_3_);
/* 184 */       TileEntity var5 = worldIn.getTileEntity(p_176398_2_);
/* 185 */       int var6 = (var5 instanceof TileEntityComparator) ? ((TileEntityComparator)var5).getOutputSignal() : 0;
/*     */       
/* 187 */       if (var4 != var6 || func_176406_l(p_176398_3_) != func_176404_e(worldIn, p_176398_2_, p_176398_3_))
/*     */       {
/* 189 */         if (func_176402_i(worldIn, p_176398_2_, p_176398_3_)) {
/*     */           
/* 191 */           worldIn.func_175654_a(p_176398_2_, this, 2, -1);
/*     */         }
/*     */         else {
/*     */           
/* 195 */           worldIn.func_175654_a(p_176398_2_, this, 2, 0);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176462_k(World worldIn, BlockPos p_176462_2_, IBlockState p_176462_3_) {
/* 203 */     int var4 = func_176460_j(worldIn, p_176462_2_, p_176462_3_);
/* 204 */     TileEntity var5 = worldIn.getTileEntity(p_176462_2_);
/* 205 */     int var6 = 0;
/*     */     
/* 207 */     if (var5 instanceof TileEntityComparator) {
/*     */       
/* 209 */       TileEntityComparator var7 = (TileEntityComparator)var5;
/* 210 */       var6 = var7.getOutputSignal();
/* 211 */       var7.setOutputSignal(var4);
/*     */     } 
/*     */     
/* 214 */     if (var6 != var4 || p_176462_3_.getValue((IProperty)field_176463_b) == Mode.COMPARE) {
/*     */       
/* 216 */       boolean var9 = func_176404_e(worldIn, p_176462_2_, p_176462_3_);
/* 217 */       boolean var8 = func_176406_l(p_176462_3_);
/*     */       
/* 219 */       if (var8 && !var9) {
/*     */         
/* 221 */         worldIn.setBlockState(p_176462_2_, p_176462_3_.withProperty((IProperty)field_176464_a, Boolean.valueOf(false)), 2);
/*     */       }
/* 223 */       else if (!var8 && var9) {
/*     */         
/* 225 */         worldIn.setBlockState(p_176462_2_, p_176462_3_.withProperty((IProperty)field_176464_a, Boolean.valueOf(true)), 2);
/*     */       } 
/*     */       
/* 228 */       func_176400_h(worldIn, p_176462_2_, p_176462_3_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
/* 234 */     if (this.isRepeaterPowered)
/*     */     {
/* 236 */       worldIn.setBlockState(pos, func_180675_k(state).withProperty((IProperty)field_176464_a, Boolean.valueOf(true)), 4);
/*     */     }
/*     */     
/* 239 */     func_176462_k(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
/* 244 */     super.onBlockAdded(worldIn, pos, state);
/* 245 */     worldIn.setTileEntity(pos, createNewTileEntity(worldIn, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
/* 250 */     super.breakBlock(worldIn, pos, state);
/* 251 */     worldIn.removeTileEntity(pos);
/* 252 */     func_176400_h(worldIn, pos, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
/* 260 */     super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
/* 261 */     TileEntity var6 = worldIn.getTileEntity(pos);
/* 262 */     return (var6 == null) ? false : var6.receiveClientEvent(eventID, eventParam);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createNewTileEntity(World worldIn, int meta) {
/* 270 */     return (TileEntity)new TileEntityComparator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 278 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)EnumFacing.getHorizontal(meta)).withProperty((IProperty)field_176464_a, Boolean.valueOf(((meta & 0x8) > 0))).withProperty((IProperty)field_176463_b, ((meta & 0x4) > 0) ? Mode.SUBTRACT : Mode.COMPARE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 286 */     byte var2 = 0;
/* 287 */     int var3 = var2 | ((EnumFacing)state.getValue((IProperty)AGE)).getHorizontalIndex();
/*     */     
/* 289 */     if (((Boolean)state.getValue((IProperty)field_176464_a)).booleanValue())
/*     */     {
/* 291 */       var3 |= 0x8;
/*     */     }
/*     */     
/* 294 */     if (state.getValue((IProperty)field_176463_b) == Mode.SUBTRACT)
/*     */     {
/* 296 */       var3 |= 0x4;
/*     */     }
/*     */     
/* 299 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 304 */     return new BlockState(this, new IProperty[] { (IProperty)AGE, (IProperty)field_176463_b, (IProperty)field_176464_a });
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/* 309 */     return getDefaultState().withProperty((IProperty)AGE, (Comparable)placer.func_174811_aO().getOpposite()).withProperty((IProperty)field_176464_a, Boolean.valueOf(false)).withProperty((IProperty)field_176463_b, Mode.COMPARE);
/*     */   }
/*     */   
/*     */   public enum Mode
/*     */     implements IStringSerializable {
/* 314 */     COMPARE("COMPARE", 0, "compare"),
/* 315 */     SUBTRACT("SUBTRACT", 1, "subtract");
/*     */     
/*     */     private final String field_177041_c;
/* 318 */     private static final Mode[] $VALUES = new Mode[] { COMPARE, SUBTRACT };
/*     */     
/*     */     private static final String __OBFID = "CL_00002128";
/*     */     
/*     */     Mode(String p_i45731_1_, int p_i45731_2_, String p_i45731_3_) {
/* 323 */       this.field_177041_c = p_i45731_3_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public String toString() {
/* 328 */       return this.field_177041_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 333 */       return this.field_177041_c;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockRedstoneComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */