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
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockDirt
/*     */   extends Block {
/*  21 */   public static final PropertyEnum VARIANT = PropertyEnum.create("variant", DirtType.class);
/*  22 */   public static final PropertyBool SNOWY = PropertyBool.create("snowy");
/*     */   
/*     */   private static final String __OBFID = "CL_00000228";
/*     */   
/*     */   protected BlockDirt() {
/*  27 */     super(Material.ground);
/*  28 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT, DirtType.DIRT).withProperty((IProperty)SNOWY, Boolean.valueOf(false)));
/*  29 */     setCreativeTab(CreativeTabs.tabBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
/*  38 */     if (state.getValue((IProperty)VARIANT) == DirtType.PODZOL) {
/*     */       
/*  40 */       Block var4 = worldIn.getBlockState(pos.offsetUp()).getBlock();
/*  41 */       state = state.withProperty((IProperty)SNOWY, Boolean.valueOf(!(var4 != Blocks.snow && var4 != Blocks.snow_layer)));
/*     */     } 
/*     */     
/*  44 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  52 */     list.add(new ItemStack(this, 1, DirtType.DIRT.getMetadata()));
/*  53 */     list.add(new ItemStack(this, 1, DirtType.COARSE_DIRT.getMetadata()));
/*  54 */     list.add(new ItemStack(this, 1, DirtType.PODZOL.getMetadata()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/*  59 */     IBlockState var3 = worldIn.getBlockState(pos);
/*  60 */     return (var3.getBlock() != this) ? 0 : ((DirtType)var3.getValue((IProperty)VARIANT)).getMetadata();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  68 */     return getDefaultState().withProperty((IProperty)VARIANT, DirtType.byMetadata(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  76 */     return ((DirtType)state.getValue((IProperty)VARIANT)).getMetadata();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  81 */     return new BlockState(this, new IProperty[] { (IProperty)VARIANT, (IProperty)SNOWY });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  89 */     DirtType var2 = (DirtType)state.getValue((IProperty)VARIANT);
/*     */     
/*  91 */     if (var2 == DirtType.PODZOL)
/*     */     {
/*  93 */       var2 = DirtType.DIRT;
/*     */     }
/*     */     
/*  96 */     return var2.getMetadata();
/*     */   }
/*     */   
/*     */   public enum DirtType
/*     */     implements IStringSerializable {
/* 101 */     DIRT("DIRT", 0, 0, "dirt", "default"),
/* 102 */     COARSE_DIRT("COARSE_DIRT", 1, 1, "coarse_dirt", "coarse"),
/* 103 */     PODZOL("PODZOL", 2, 2, "podzol");
/* 104 */     private static final DirtType[] METADATA_LOOKUP = new DirtType[(values()).length];
/*     */     
/*     */     private final int metadata;
/*     */     private final String name;
/*     */     private final String unlocalizedName;
/* 109 */     private static final DirtType[] $VALUES = new DirtType[] { DIRT, COARSE_DIRT, PODZOL };
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
/*     */     private static final String __OBFID = "CL_00002125";
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
/* 155 */       DirtType[] var0 = values();
/* 156 */       int var1 = var0.length;
/*     */       
/* 158 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 160 */         DirtType var3 = var0[var2];
/* 161 */         METADATA_LOOKUP[var3.getMetadata()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     DirtType(String p_i45728_1_, int p_i45728_2_, int metadata, String name, String unlocalizedName) {
/*     */       this.metadata = metadata;
/*     */       this.name = name;
/*     */       this.unlocalizedName = unlocalizedName;
/*     */     }
/*     */     
/*     */     public int getMetadata() {
/*     */       return this.metadata;
/*     */     }
/*     */     
/*     */     public String getUnlocalizedName() {
/*     */       return this.unlocalizedName;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.name;
/*     */     }
/*     */     
/*     */     public static DirtType byMetadata(int metadata) {
/*     */       if (metadata < 0 || metadata >= METADATA_LOOKUP.length)
/*     */         metadata = 0; 
/*     */       return METADATA_LOOKUP[metadata];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.name;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockDirt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */