/*     */ package net.minecraft.block;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Collections2;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.properties.PropertyEnum;
/*     */ import net.minecraft.block.state.BlockState;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IStringSerializable;
/*     */ 
/*     */ public abstract class BlockFlower
/*     */   extends BlockBush
/*     */ {
/*     */   protected PropertyEnum field_176496_a;
/*     */   private static final String __OBFID = "CL_00000246";
/*     */   
/*     */   protected BlockFlower() {
/*  26 */     super(Material.plants);
/*  27 */     setDefaultState(this.blockState.getBaseState().withProperty(func_176494_l(), (func_176495_j() == EnumFlowerColor.RED) ? EnumFlowerType.POPPY : EnumFlowerType.DANDELION));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int damageDropped(IBlockState state) {
/*  35 */     return ((EnumFlowerType)state.getValue(func_176494_l())).func_176968_b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
/*  43 */     EnumFlowerType[] var4 = EnumFlowerType.func_176966_a(func_176495_j());
/*  44 */     int var5 = var4.length;
/*     */     
/*  46 */     for (int var6 = 0; var6 < var5; var6++) {
/*     */       
/*  48 */       EnumFlowerType var7 = var4[var6];
/*  49 */       list.add(new ItemStack(itemIn, 1, var7.func_176968_b()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState getStateFromMeta(int meta) {
/*  58 */     return getDefaultState().withProperty(func_176494_l(), EnumFlowerType.func_176967_a(func_176495_j(), meta));
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract EnumFlowerColor func_176495_j();
/*     */   
/*     */   public IProperty func_176494_l() {
/*  65 */     if (this.field_176496_a == null)
/*     */     {
/*  67 */       this.field_176496_a = PropertyEnum.create("type", EnumFlowerType.class, new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002120";
/*     */             
/*     */             public boolean func_180354_a(BlockFlower.EnumFlowerType p_180354_1_) {
/*  72 */               return (p_180354_1_.func_176964_a() == BlockFlower.this.func_176495_j());
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/*  76 */               return func_180354_a((BlockFlower.EnumFlowerType)p_apply_1_);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/*  81 */     return (IProperty)this.field_176496_a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMetaFromState(IBlockState state) {
/*  89 */     return ((EnumFlowerType)state.getValue(func_176494_l())).func_176968_b();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockState createBlockState() {
/*  94 */     return new BlockState(this, new IProperty[] { func_176494_l() });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Block.EnumOffsetType getOffsetType() {
/* 102 */     return Block.EnumOffsetType.XZ;
/*     */   }
/*     */   
/*     */   public enum EnumFlowerColor
/*     */   {
/* 107 */     YELLOW("YELLOW", 0),
/* 108 */     RED("RED", 1);
/*     */     
/* 110 */     private static final EnumFlowerColor[] $VALUES = new EnumFlowerColor[] { YELLOW, RED };
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002117";
/*     */ 
/*     */     
/*     */     public BlockFlower func_180346_a() {
/* 117 */       return (this == YELLOW) ? Blocks.yellow_flower : Blocks.red_flower;
/*     */     }
/*     */     static {
/*     */     
/*     */     } }
/*     */   
/* 123 */   public enum EnumFlowerType implements IStringSerializable { DANDELION("DANDELION", 0, (String)BlockFlower.EnumFlowerColor.YELLOW, 0, (BlockFlower.EnumFlowerColor)"dandelion"),
/* 124 */     POPPY("POPPY", 1, (String)BlockFlower.EnumFlowerColor.RED, 0, (BlockFlower.EnumFlowerColor)"poppy"),
/* 125 */     BLUE_ORCHID("BLUE_ORCHID", 2, (String)BlockFlower.EnumFlowerColor.RED, 1, (BlockFlower.EnumFlowerColor)"blue_orchid", "blueOrchid"),
/* 126 */     ALLIUM("ALLIUM", 3, (String)BlockFlower.EnumFlowerColor.RED, 2, (BlockFlower.EnumFlowerColor)"allium"),
/* 127 */     HOUSTONIA("HOUSTONIA", 4, (String)BlockFlower.EnumFlowerColor.RED, 3, (BlockFlower.EnumFlowerColor)"houstonia"),
/* 128 */     RED_TULIP("RED_TULIP", 5, (String)BlockFlower.EnumFlowerColor.RED, 4, (BlockFlower.EnumFlowerColor)"red_tulip", "tulipRed"),
/* 129 */     ORANGE_TULIP("ORANGE_TULIP", 6, (String)BlockFlower.EnumFlowerColor.RED, 5, (BlockFlower.EnumFlowerColor)"orange_tulip", "tulipOrange"),
/* 130 */     WHITE_TULIP("WHITE_TULIP", 7, (String)BlockFlower.EnumFlowerColor.RED, 6, (BlockFlower.EnumFlowerColor)"white_tulip", "tulipWhite"),
/* 131 */     PINK_TULIP("PINK_TULIP", 8, (String)BlockFlower.EnumFlowerColor.RED, 7, (BlockFlower.EnumFlowerColor)"pink_tulip", "tulipPink"),
/* 132 */     OXEYE_DAISY("OXEYE_DAISY", 9, (String)BlockFlower.EnumFlowerColor.RED, 8, (BlockFlower.EnumFlowerColor)"oxeye_daisy", "oxeyeDaisy");
/* 133 */     private static final EnumFlowerType[][] field_176981_k = new EnumFlowerType[(BlockFlower.EnumFlowerColor.values()).length][];
/*     */     
/*     */     private final BlockFlower.EnumFlowerColor field_176978_l;
/*     */     private final int field_176979_m;
/*     */     private final String field_176976_n;
/*     */     private final String field_176977_o;
/* 139 */     private static final EnumFlowerType[] $VALUES = new EnumFlowerType[] { DANDELION, POPPY, BLUE_ORCHID, ALLIUM, HOUSTONIA, RED_TULIP, ORANGE_TULIP, WHITE_TULIP, PINK_TULIP, OXEYE_DAISY };
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002119";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 198 */       BlockFlower.EnumFlowerColor[] var0 = BlockFlower.EnumFlowerColor.values();
/* 199 */       int var1 = var0.length;
/*     */       
/* 201 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 203 */         final BlockFlower.EnumFlowerColor var3 = var0[var2];
/* 204 */         Collection var4 = Collections2.filter(Lists.newArrayList((Object[])values()), new Predicate()
/*     */             {
/*     */               private static final String __OBFID = "CL_00002118";
/*     */               
/*     */               public boolean func_180350_a(BlockFlower.EnumFlowerType p_180350_1_) {
/* 209 */                 return (p_180350_1_.func_176964_a() == var3);
/*     */               }
/*     */               
/*     */               public boolean apply(Object p_apply_1_) {
/* 213 */                 return func_180350_a((BlockFlower.EnumFlowerType)p_apply_1_);
/*     */               }
/*     */             });
/* 216 */         field_176981_k[var3.ordinal()] = (EnumFlowerType[])var4.toArray((Object[])new EnumFlowerType[var4.size()]);
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumFlowerType(String p_i45719_1_, int p_i45719_2_, BlockFlower.EnumFlowerColor p_i45719_3_, int p_i45719_4_, String p_i45719_5_, String p_i45719_6_) {
/*     */       this.field_176978_l = p_i45719_3_;
/*     */       this.field_176979_m = p_i45719_4_;
/*     */       this.field_176976_n = p_i45719_5_;
/*     */       this.field_176977_o = p_i45719_6_;
/*     */     }
/*     */     
/*     */     public BlockFlower.EnumFlowerColor func_176964_a() {
/*     */       return this.field_176978_l;
/*     */     }
/*     */     
/*     */     public int func_176968_b() {
/*     */       return this.field_176979_m;
/*     */     }
/*     */     
/*     */     public static EnumFlowerType func_176967_a(BlockFlower.EnumFlowerColor p_176967_0_, int p_176967_1_) {
/*     */       EnumFlowerType[] var2 = field_176981_k[p_176967_0_.ordinal()];
/*     */       if (p_176967_1_ < 0 || p_176967_1_ >= var2.length)
/*     */         p_176967_1_ = 0; 
/*     */       return var2[p_176967_1_];
/*     */     }
/*     */     
/*     */     public static EnumFlowerType[] func_176966_a(BlockFlower.EnumFlowerColor p_176966_0_) {
/*     */       return field_176981_k[p_176966_0_.ordinal()];
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.field_176976_n;
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.field_176976_n;
/*     */     }
/*     */     
/*     */     public String func_176963_d() {
/*     */       return this.field_176977_o;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\BlockFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */