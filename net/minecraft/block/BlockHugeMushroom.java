/*     */ package net.minecraft.block;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class BlockHugeMushroom
/*     */   extends Block {
/*  18 */   public static final PropertyEnum field_176380_a = PropertyEnum.create("variant", EnumType.class);
/*     */   
/*     */   private final Block field_176379_b;
/*     */   private static final String __OBFID = "CL_00000258";
/*     */   
/*     */   public BlockHugeMushroom(Material p_i45711_1_, Block p_i45711_2_) {
/*  24 */     super(p_i45711_1_);
/*  25 */     setDefaultState(this.blockState.getBaseState().withProperty((IProperty)field_176380_a, EnumType.ALL_OUTSIDE));
/*  26 */     this.field_176379_b = p_i45711_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int quantityDropped(Random random) {
/*  34 */     return Math.max(0, random.nextInt(10) - 7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
/*  44 */     return Item.getItemFromBlock(this.field_176379_b);
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem(World worldIn, BlockPos pos) {
/*  49 */     return Item.getItemFromBlock(this.field_176379_b);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
/*  54 */     return getDefaultState();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  62 */     return getDefaultState().withProperty((IProperty)field_176380_a, EnumType.func_176895_a(meta));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  70 */     return ((EnumType)state.getValue((IProperty)field_176380_a)).func_176896_a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  75 */     return new BlockState(this, new IProperty[] { (IProperty)field_176380_a });
/*     */   }
/*     */   
/*     */   public enum EnumType
/*     */     implements IStringSerializable {
/*  80 */     NORTH_WEST("NORTH_WEST", 0, 1, "north_west"),
/*  81 */     NORTH("NORTH", 1, 2, "north"),
/*  82 */     NORTH_EAST("NORTH_EAST", 2, 3, "north_east"),
/*  83 */     WEST("WEST", 3, 4, "west"),
/*  84 */     CENTER("CENTER", 4, 5, "center"),
/*  85 */     EAST("EAST", 5, 6, "east"),
/*  86 */     SOUTH_WEST("SOUTH_WEST", 6, 7, "south_west"),
/*  87 */     SOUTH("SOUTH", 7, 8, "south"),
/*  88 */     SOUTH_EAST("SOUTH_EAST", 8, 9, "south_east"),
/*  89 */     STEM("STEM", 9, 10, "stem"),
/*  90 */     ALL_INSIDE("ALL_INSIDE", 10, 0, "all_inside"),
/*  91 */     ALL_OUTSIDE("ALL_OUTSIDE", 11, 14, "all_outside"),
/*  92 */     ALL_STEM("ALL_STEM", 12, 15, "all_stem");
/*  93 */     private static final EnumType[] field_176905_n = new EnumType[16];
/*     */     
/*     */     private final int field_176906_o;
/*     */     private final String field_176914_p;
/*  97 */     private static final EnumType[] $VALUES = new EnumType[] { NORTH_WEST, NORTH, NORTH_EAST, WEST, CENTER, EAST, SOUTH_WEST, SOUTH, SOUTH_EAST, STEM, ALL_INSIDE, ALL_OUTSIDE, ALL_STEM };
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
/*     */     private static final String __OBFID = "CL_00002105";
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
/* 133 */       EnumType[] var0 = values();
/* 134 */       int var1 = var0.length;
/*     */       
/* 136 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 138 */         EnumType var3 = var0[var2];
/* 139 */         field_176905_n[var3.func_176896_a()] = var3;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumType(String p_i45710_1_, int p_i45710_2_, int p_i45710_3_, String p_i45710_4_) {
/*     */       this.field_176906_o = p_i45710_3_;
/*     */       this.field_176914_p = p_i45710_4_;
/*     */     }
/*     */     
/*     */     public int func_176896_a() {
/*     */       return this.field_176906_o;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176914_p;
/*     */     }
/*     */     
/*     */     public static EnumType func_176895_a(int p_176895_0_) {
/*     */       if (p_176895_0_ < 0 || p_176895_0_ >= field_176905_n.length)
/*     */         p_176895_0_ = 0; 
/*     */       EnumType var1 = field_176905_n[p_176895_0_];
/*     */       return (var1 == null) ? field_176905_n[0] : var1;
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176914_p;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockHugeMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */