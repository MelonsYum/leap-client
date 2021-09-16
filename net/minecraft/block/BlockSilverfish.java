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
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.monster.EntitySilverfish;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockSilverfish extends Block {
/*  21 */   public static final PropertyEnum VARIANT_PROP = PropertyEnum.create("variant", EnumType.class);
/*     */   
/*     */   private static final String __OBFID = "CL_00000271";
/*     */   
/*     */   public BlockSilverfish() {
/*  26 */     super(Material.clay);
/*  27 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)VARIANT_PROP, EnumType.STONE));
/*  28 */     setHardness(0.0F);
/*  29 */     setCreativeTab(CreativeTabs.tabDecorations);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/*  37 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_176377_d(IBlockState p_176377_0_) {
/*  42 */     Block var1 = p_176377_0_.getBlock();
/*  43 */     return !(p_176377_0_ != Blocks.stone.getDefaultState().withProperty((IProperty)BlockStone.VARIANT_PROP, BlockStone.EnumType.STONE) && var1 != Blocks.cobblestone && var1 != Blocks.stonebrick);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack createStackedBlock(IBlockState state) {
/*  48 */     switch (SwitchEnumType.field_180178_a[((EnumType)state.getValue((IProperty)VARIANT_PROP)).ordinal()]) {
/*     */       
/*     */       case 1:
/*  51 */         return new ItemStack(Blocks.cobblestone);
/*     */       
/*     */       case 2:
/*  54 */         return new ItemStack(Blocks.stonebrick);
/*     */       
/*     */       case 3:
/*  57 */         return new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.EnumType.MOSSY.getMetaFromState());
/*     */       
/*     */       case 4:
/*  60 */         return new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.EnumType.CRACKED.getMetaFromState());
/*     */       
/*     */       case 5:
/*  63 */         return new ItemStack(Blocks.stonebrick, 1, BlockStoneBrick.EnumType.CHISELED.getMetaFromState());
/*     */     } 
/*     */     
/*  66 */     return new ItemStack(Blocks.stone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
/*  78 */     if (!worldIn.isRemote && worldIn.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
/*     */       
/*  80 */       EntitySilverfish var6 = new EntitySilverfish(worldIn);
/*  81 */       var6.setLocationAndAngles(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 0.0F, 0.0F);
/*  82 */       worldIn.spawnEntityInWorld((Entity)var6);
/*  83 */       var6.spawnExplosionParticle();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDamageValue(World worldIn, BlockPos pos) {
/*  89 */     IBlockState var3 = worldIn.getBlockState(pos);
/*  90 */     return var3.getBlock().getMetaFromState(var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  98 */     EnumType[] var4 = EnumType.values();
/*  99 */     int var5 = var4.length;
/*     */     
/* 101 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/* 103 */       EnumType var7 = var4[var6];
/* 104 */       list.add(new ItemStack(itemIn, 1, var7.func_176881_a()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/* 113 */     return getDefaultState().withProperty((IProperty)VARIANT_PROP, EnumType.func_176879_a(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/* 121 */     return ((EnumType)state.getValue((IProperty)VARIANT_PROP)).func_176881_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/* 126 */     return new BlockState(this, new IProperty[] { (IProperty)VARIANT_PROP });
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/* 131 */     STONE("STONE", 0, 0, "stone", null)
/*     */     {
/*     */       private static final String __OBFID = "CL_00002097";
/*     */       
/*     */       public IBlockState func_176883_d() {
/* 136 */         return Blocks.stone.getDefaultState().withProperty((IProperty)BlockStone.VARIANT_PROP, BlockStone.EnumType.STONE);
/*     */       }
/*     */     },
/* 139 */     COBBLESTONE("COBBLESTONE", 1, 1, "cobblestone", "cobble", null)
/*     */     {
/*     */       private static final String __OBFID = "CL_00002096";
/*     */       
/*     */       public IBlockState func_176883_d() {
/* 144 */         return Blocks.cobblestone.getDefaultState();
/*     */       }
/*     */     },
/* 147 */     STONEBRICK("STONEBRICK", 2, 2, "stone_brick", "brick", null)
/*     */     {
/*     */       private static final String __OBFID = "CL_00002095";
/*     */       
/*     */       public IBlockState func_176883_d() {
/* 152 */         return Blocks.stonebrick.getDefaultState().withProperty((IProperty)BlockStoneBrick.VARIANT_PROP, BlockStoneBrick.EnumType.DEFAULT);
/*     */       }
/*     */     },
/* 155 */     MOSSY_STONEBRICK("MOSSY_STONEBRICK", 3, 3, "mossy_brick", "mossybrick", null)
/*     */     {
/*     */       private static final String __OBFID = "CL_00002094";
/*     */       
/*     */       public IBlockState func_176883_d() {
/* 160 */         return Blocks.stonebrick.getDefaultState().withProperty((IProperty)BlockStoneBrick.VARIANT_PROP, BlockStoneBrick.EnumType.MOSSY);
/*     */       }
/*     */     },
/* 163 */     CRACKED_STONEBRICK("CRACKED_STONEBRICK", 4, 4, "cracked_brick", "crackedbrick", null)
/*     */     {
/*     */       private static final String __OBFID = "CL_00002093";
/*     */       
/*     */       public IBlockState func_176883_d() {
/* 168 */         return Blocks.stonebrick.getDefaultState().withProperty((IProperty)BlockStoneBrick.VARIANT_PROP, BlockStoneBrick.EnumType.CRACKED);
/*     */       }
/*     */     },
/* 171 */     CHISELED_STONEBRICK("CHISELED_STONEBRICK", 5, 5, "chiseled_brick", "chiseledbrick", null)
/*     */     {
/*     */       private static final String __OBFID = "CL_00002092";
/*     */       
/*     */       public IBlockState func_176883_d() {
/* 176 */         return Blocks.stonebrick.getDefaultState().withProperty((IProperty)BlockStoneBrick.VARIANT_PROP, BlockStoneBrick.EnumType.CHISELED);
/*     */       }
/*     */     };
/* 179 */     private static final EnumType[] field_176885_g = new EnumType[(values()).length];
/*     */     
/*     */     private final int field_176893_h;
/*     */     private final String field_176894_i;
/*     */     private final String field_176891_j;
/* 184 */     private static final EnumType[] $VALUES = new EnumType[] { STONE, COBBLESTONE, STONEBRICK, MOSSY_STONEBRICK, CRACKED_STONEBRICK, CHISELED_STONEBRICK };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002098";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     EnumType(String p_i45705_1_, int p_i45705_2_, int p_i45705_3_, String p_i45705_4_, String p_i45705_5_) {
/*     */       this.field_176893_h = p_i45705_3_;
/*     */       this.field_176894_i = p_i45705_4_;
/*     */       this.field_176891_j = p_i45705_5_;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int func_176881_a() {
/*     */       return this.field_176893_h;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176894_i;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static EnumType func_176879_a(int p_176879_0_) {
/*     */       if (p_176879_0_ < 0 || p_176879_0_ >= field_176885_g.length) {
/*     */         p_176879_0_ = 0;
/*     */       }
/*     */       return field_176885_g[p_176879_0_];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176894_i;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String func_176882_c() {
/*     */       return this.field_176891_j;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static EnumType func_176878_a(IBlockState p_176878_0_) {
/*     */       EnumType[] var1 = values();
/*     */       int var2 = var1.length;
/*     */       for (int var3 = 0; var3 < var2; var3++) {
/*     */         EnumType var4 = var1[var3];
/*     */         if (p_176878_0_ == var4.func_176883_d()) {
/*     */           return var4;
/*     */         }
/*     */       } 
/*     */       return STONE;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 260 */       EnumType[] var0 = values();
/* 261 */       int var1 = var0.length;
/*     */       
/* 263 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 265 */         EnumType var3 = var0[var2];
/* 266 */         field_176885_g[var3.func_176881_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     public abstract IBlockState func_176883_d(); }
/*     */   
/*     */   static final class SwitchEnumType {
/* 273 */     static final int[] field_180178_a = new int[(BlockSilverfish.EnumType.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002099";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 280 */         field_180178_a[BlockSilverfish.EnumType.COBBLESTONE.ordinal()] = 1;
/*     */       }
/* 282 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 289 */         field_180178_a[BlockSilverfish.EnumType.STONEBRICK.ordinal()] = 2;
/*     */       }
/* 291 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 298 */         field_180178_a[BlockSilverfish.EnumType.MOSSY_STONEBRICK.ordinal()] = 3;
/*     */       }
/* 300 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 307 */         field_180178_a[BlockSilverfish.EnumType.CRACKED_STONEBRICK.ordinal()] = 4;
/*     */       }
/* 309 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 316 */         field_180178_a[BlockSilverfish.EnumType.CHISELED_STONEBRICK.ordinal()] = 5;
/*     */       }
/* 318 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockSilverfish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */