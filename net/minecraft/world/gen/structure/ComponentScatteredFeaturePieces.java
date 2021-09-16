/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlowerPot;
/*     */ import net.minecraft.block.BlockLever;
/*     */ import net.minecraft.block.BlockPlanks;
/*     */ import net.minecraft.block.BlockSandStone;
/*     */ import net.minecraft.block.BlockStoneBrick;
/*     */ import net.minecraft.block.BlockStoneSlab;
/*     */ import net.minecraft.block.BlockTripWire;
/*     */ import net.minecraft.block.BlockTripWireHook;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class ComponentScatteredFeaturePieces
/*     */ {
/*     */   public static void registerScatteredFeaturePieces() {
/*  32 */     MapGenStructureIO.registerStructureComponent(DesertPyramid.class, "TeDP");
/*  33 */     MapGenStructureIO.registerStructureComponent(JunglePyramid.class, "TeJP");
/*  34 */     MapGenStructureIO.registerStructureComponent(SwampHut.class, "TeSH");
/*     */   }
/*     */   
/*     */   private static final String __OBFID = "CL_00000473";
/*     */   
/*  39 */   public static class DesertPyramid extends Feature { private boolean[] field_74940_h = new boolean[4];
/*  40 */     private static final List itemsToGenerateInTemple = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15), new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2), new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20), new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1) });
/*     */     
/*     */     private static final String __OBFID = "CL_00000476";
/*     */     
/*     */     public DesertPyramid() {}
/*     */     
/*     */     public DesertPyramid(Random p_i2062_1_, int p_i2062_2_, int p_i2062_3_) {
/*  47 */       super(p_i2062_1_, p_i2062_2_, 64, p_i2062_3_, 21, 15, 21);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  52 */       super.writeStructureToNBT(p_143012_1_);
/*  53 */       p_143012_1_.setBoolean("hasPlacedChest0", this.field_74940_h[0]);
/*  54 */       p_143012_1_.setBoolean("hasPlacedChest1", this.field_74940_h[1]);
/*  55 */       p_143012_1_.setBoolean("hasPlacedChest2", this.field_74940_h[2]);
/*  56 */       p_143012_1_.setBoolean("hasPlacedChest3", this.field_74940_h[3]);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  61 */       super.readStructureFromNBT(p_143011_1_);
/*  62 */       this.field_74940_h[0] = p_143011_1_.getBoolean("hasPlacedChest0");
/*  63 */       this.field_74940_h[1] = p_143011_1_.getBoolean("hasPlacedChest1");
/*  64 */       this.field_74940_h[2] = p_143011_1_.getBoolean("hasPlacedChest2");
/*  65 */       this.field_74940_h[3] = p_143011_1_.getBoolean("hasPlacedChest3");
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  70 */       func_175804_a(worldIn, p_74875_3_, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/*     */       
/*     */       int var4;
/*  73 */       for (var4 = 1; var4 <= 9; var4++) {
/*     */         
/*  75 */         func_175804_a(worldIn, p_74875_3_, var4, var4, var4, this.scatteredFeatureSizeX - 1 - var4, var4, this.scatteredFeatureSizeZ - 1 - var4, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/*  76 */         func_175804_a(worldIn, p_74875_3_, var4 + 1, var4, var4 + 1, this.scatteredFeatureSizeX - 2 - var4, var4, this.scatteredFeatureSizeZ - 2 - var4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  81 */       for (var4 = 0; var4 < this.scatteredFeatureSizeX; var4++) {
/*     */         
/*  83 */         for (int i = 0; i < this.scatteredFeatureSizeZ; i++) {
/*     */           
/*  85 */           byte var6 = -5;
/*  86 */           func_175808_b(worldIn, Blocks.sandstone.getDefaultState(), var4, var6, i, p_74875_3_);
/*     */         } 
/*     */       } 
/*     */       
/*  90 */       var4 = getMetadataWithOffset(Blocks.sandstone_stairs, 3);
/*  91 */       int var5 = getMetadataWithOffset(Blocks.sandstone_stairs, 2);
/*  92 */       int var14 = getMetadataWithOffset(Blocks.sandstone_stairs, 0);
/*  93 */       int var7 = getMetadataWithOffset(Blocks.sandstone_stairs, 1);
/*  94 */       int var8 = (EnumDyeColor.ORANGE.getDyeColorDamage() ^ 0xFFFFFFFF) & 0xF;
/*  95 */       int var9 = (EnumDyeColor.BLUE.getDyeColorDamage() ^ 0xFFFFFFFF) & 0xF;
/*  96 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 4, 9, 4, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  97 */       func_175804_a(worldIn, p_74875_3_, 1, 10, 1, 3, 10, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/*  98 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var4), 2, 10, 0, p_74875_3_);
/*  99 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var5), 2, 10, 4, p_74875_3_);
/* 100 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var14), 0, 10, 2, p_74875_3_);
/* 101 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var7), 4, 10, 2, p_74875_3_);
/* 102 */       func_175804_a(worldIn, p_74875_3_, this.scatteredFeatureSizeX - 5, 0, 0, this.scatteredFeatureSizeX - 1, 9, 4, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 103 */       func_175804_a(worldIn, p_74875_3_, this.scatteredFeatureSizeX - 4, 10, 1, this.scatteredFeatureSizeX - 2, 10, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 104 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var4), this.scatteredFeatureSizeX - 3, 10, 0, p_74875_3_);
/* 105 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var5), this.scatteredFeatureSizeX - 3, 10, 4, p_74875_3_);
/* 106 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var14), this.scatteredFeatureSizeX - 5, 10, 2, p_74875_3_);
/* 107 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var7), this.scatteredFeatureSizeX - 1, 10, 2, p_74875_3_);
/* 108 */       func_175804_a(worldIn, p_74875_3_, 8, 0, 0, 12, 4, 4, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 109 */       func_175804_a(worldIn, p_74875_3_, 9, 1, 0, 11, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 110 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 9, 1, 1, p_74875_3_);
/* 111 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 9, 2, 1, p_74875_3_);
/* 112 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 9, 3, 1, p_74875_3_);
/* 113 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 10, 3, 1, p_74875_3_);
/* 114 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 11, 3, 1, p_74875_3_);
/* 115 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 11, 2, 1, p_74875_3_);
/* 116 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 11, 1, 1, p_74875_3_);
/* 117 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 8, 3, 3, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 118 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 2, 8, 2, 2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 119 */       func_175804_a(worldIn, p_74875_3_, 12, 1, 1, 16, 3, 3, Blocks.sandstone.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 120 */       func_175804_a(worldIn, p_74875_3_, 12, 1, 2, 16, 2, 2, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 121 */       func_175804_a(worldIn, p_74875_3_, 5, 4, 5, this.scatteredFeatureSizeX - 6, 4, this.scatteredFeatureSizeZ - 6, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 122 */       func_175804_a(worldIn, p_74875_3_, 9, 4, 9, 11, 4, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 123 */       func_175804_a(worldIn, p_74875_3_, 8, 1, 8, 8, 3, 8, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), false);
/* 124 */       func_175804_a(worldIn, p_74875_3_, 12, 1, 8, 12, 3, 8, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), false);
/* 125 */       func_175804_a(worldIn, p_74875_3_, 8, 1, 12, 8, 3, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), false);
/* 126 */       func_175804_a(worldIn, p_74875_3_, 12, 1, 12, 12, 3, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), false);
/* 127 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 5, 4, 4, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 128 */       func_175804_a(worldIn, p_74875_3_, this.scatteredFeatureSizeX - 5, 1, 5, this.scatteredFeatureSizeX - 2, 4, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 129 */       func_175804_a(worldIn, p_74875_3_, 6, 7, 9, 6, 7, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 130 */       func_175804_a(worldIn, p_74875_3_, this.scatteredFeatureSizeX - 7, 7, 9, this.scatteredFeatureSizeX - 7, 7, 11, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 131 */       func_175804_a(worldIn, p_74875_3_, 5, 5, 9, 5, 7, 11, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), false);
/* 132 */       func_175804_a(worldIn, p_74875_3_, this.scatteredFeatureSizeX - 6, 5, 9, this.scatteredFeatureSizeX - 6, 7, 11, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), false);
/* 133 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 5, 5, 10, p_74875_3_);
/* 134 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 5, 6, 10, p_74875_3_);
/* 135 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 6, 6, 10, p_74875_3_);
/* 136 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), this.scatteredFeatureSizeX - 6, 5, 10, p_74875_3_);
/* 137 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), this.scatteredFeatureSizeX - 6, 6, 10, p_74875_3_);
/* 138 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), this.scatteredFeatureSizeX - 7, 6, 10, p_74875_3_);
/* 139 */       func_175804_a(worldIn, p_74875_3_, 2, 4, 4, 2, 6, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 140 */       func_175804_a(worldIn, p_74875_3_, this.scatteredFeatureSizeX - 3, 4, 4, this.scatteredFeatureSizeX - 3, 6, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 141 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var4), 2, 4, 5, p_74875_3_);
/* 142 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var4), 2, 3, 4, p_74875_3_);
/* 143 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var4), this.scatteredFeatureSizeX - 3, 4, 5, p_74875_3_);
/* 144 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var4), this.scatteredFeatureSizeX - 3, 3, 4, p_74875_3_);
/* 145 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 3, 2, 2, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 146 */       func_175804_a(worldIn, p_74875_3_, this.scatteredFeatureSizeX - 3, 1, 3, this.scatteredFeatureSizeX - 2, 2, 3, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 147 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getDefaultState(), 1, 1, 2, p_74875_3_);
/* 148 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getDefaultState(), this.scatteredFeatureSizeX - 2, 1, 2, p_74875_3_);
/* 149 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SAND.func_176624_a()), 1, 2, 2, p_74875_3_);
/* 150 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SAND.func_176624_a()), this.scatteredFeatureSizeX - 2, 2, 2, p_74875_3_);
/* 151 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var7), 2, 1, 2, p_74875_3_);
/* 152 */       func_175811_a(worldIn, Blocks.sandstone_stairs.getStateFromMeta(var14), this.scatteredFeatureSizeX - 3, 1, 2, p_74875_3_);
/* 153 */       func_175804_a(worldIn, p_74875_3_, 4, 3, 5, 4, 3, 18, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 154 */       func_175804_a(worldIn, p_74875_3_, this.scatteredFeatureSizeX - 5, 3, 5, this.scatteredFeatureSizeX - 5, 3, 17, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 155 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 5, 4, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 156 */       func_175804_a(worldIn, p_74875_3_, this.scatteredFeatureSizeX - 6, 1, 5, this.scatteredFeatureSizeX - 5, 2, 16, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       
/*     */       int var10;
/* 159 */       for (var10 = 5; var10 <= 17; var10 += 2) {
/*     */         
/* 161 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 4, 1, var10, p_74875_3_);
/* 162 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), 4, 2, var10, p_74875_3_);
/* 163 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), this.scatteredFeatureSizeX - 5, 1, var10, p_74875_3_);
/* 164 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), this.scatteredFeatureSizeX - 5, 2, var10, p_74875_3_);
/*     */       } 
/*     */       
/* 167 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 10, 0, 7, p_74875_3_);
/* 168 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 10, 0, 8, p_74875_3_);
/* 169 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 9, 0, 9, p_74875_3_);
/* 170 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 11, 0, 9, p_74875_3_);
/* 171 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 8, 0, 10, p_74875_3_);
/* 172 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 12, 0, 10, p_74875_3_);
/* 173 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 7, 0, 10, p_74875_3_);
/* 174 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 13, 0, 10, p_74875_3_);
/* 175 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 9, 0, 11, p_74875_3_);
/* 176 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 11, 0, 11, p_74875_3_);
/* 177 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 10, 0, 12, p_74875_3_);
/* 178 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 10, 0, 13, p_74875_3_);
/* 179 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var9), 10, 0, 10, p_74875_3_);
/*     */       
/* 181 */       for (var10 = 0; var10 <= this.scatteredFeatureSizeX - 1; var10 += this.scatteredFeatureSizeX - 1) {
/*     */         
/* 183 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 2, 1, p_74875_3_);
/* 184 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 2, 2, p_74875_3_);
/* 185 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 2, 3, p_74875_3_);
/* 186 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 3, 1, p_74875_3_);
/* 187 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 3, 2, p_74875_3_);
/* 188 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 3, 3, p_74875_3_);
/* 189 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 4, 1, p_74875_3_);
/* 190 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), var10, 4, 2, p_74875_3_);
/* 191 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 4, 3, p_74875_3_);
/* 192 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 5, 1, p_74875_3_);
/* 193 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 5, 2, p_74875_3_);
/* 194 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 5, 3, p_74875_3_);
/* 195 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 6, 1, p_74875_3_);
/* 196 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), var10, 6, 2, p_74875_3_);
/* 197 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 6, 3, p_74875_3_);
/* 198 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 7, 1, p_74875_3_);
/* 199 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 7, 2, p_74875_3_);
/* 200 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 7, 3, p_74875_3_);
/* 201 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 8, 1, p_74875_3_);
/* 202 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 8, 2, p_74875_3_);
/* 203 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 8, 3, p_74875_3_);
/*     */       } 
/*     */       
/* 206 */       for (var10 = 2; var10 <= this.scatteredFeatureSizeX - 3; var10 += this.scatteredFeatureSizeX - 3 - 2) {
/*     */         
/* 208 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10 - 1, 2, 0, p_74875_3_);
/* 209 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 2, 0, p_74875_3_);
/* 210 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10 + 1, 2, 0, p_74875_3_);
/* 211 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10 - 1, 3, 0, p_74875_3_);
/* 212 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 3, 0, p_74875_3_);
/* 213 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10 + 1, 3, 0, p_74875_3_);
/* 214 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10 - 1, 4, 0, p_74875_3_);
/* 215 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), var10, 4, 0, p_74875_3_);
/* 216 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10 + 1, 4, 0, p_74875_3_);
/* 217 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10 - 1, 5, 0, p_74875_3_);
/* 218 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 5, 0, p_74875_3_);
/* 219 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10 + 1, 5, 0, p_74875_3_);
/* 220 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10 - 1, 6, 0, p_74875_3_);
/* 221 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), var10, 6, 0, p_74875_3_);
/* 222 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10 + 1, 6, 0, p_74875_3_);
/* 223 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10 - 1, 7, 0, p_74875_3_);
/* 224 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10, 7, 0, p_74875_3_);
/* 225 */         func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), var10 + 1, 7, 0, p_74875_3_);
/* 226 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10 - 1, 8, 0, p_74875_3_);
/* 227 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10, 8, 0, p_74875_3_);
/* 228 */         func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), var10 + 1, 8, 0, p_74875_3_);
/*     */       } 
/*     */       
/* 231 */       func_175804_a(worldIn, p_74875_3_, 8, 4, 0, 12, 6, 0, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), false);
/* 232 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 8, 6, 0, p_74875_3_);
/* 233 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 12, 6, 0, p_74875_3_);
/* 234 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 9, 5, 0, p_74875_3_);
/* 235 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), 10, 5, 0, p_74875_3_);
/* 236 */       func_175811_a(worldIn, Blocks.stained_hardened_clay.getStateFromMeta(var8), 11, 5, 0, p_74875_3_);
/* 237 */       func_175804_a(worldIn, p_74875_3_, 8, -14, 8, 12, -11, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), false);
/* 238 */       func_175804_a(worldIn, p_74875_3_, 8, -10, 8, 12, -10, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), false);
/* 239 */       func_175804_a(worldIn, p_74875_3_, 8, -9, 8, 12, -9, 12, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), false);
/* 240 */       func_175804_a(worldIn, p_74875_3_, 8, -8, 8, 12, -1, 12, Blocks.sandstone.getDefaultState(), Blocks.sandstone.getDefaultState(), false);
/* 241 */       func_175804_a(worldIn, p_74875_3_, 9, -11, 9, 11, -1, 11, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 242 */       func_175811_a(worldIn, Blocks.stone_pressure_plate.getDefaultState(), 10, -11, 10, p_74875_3_);
/* 243 */       func_175804_a(worldIn, p_74875_3_, 9, -13, 9, 11, -13, 11, Blocks.tnt.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 244 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 8, -11, 10, p_74875_3_);
/* 245 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 8, -10, 10, p_74875_3_);
/* 246 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), 7, -10, 10, p_74875_3_);
/* 247 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 7, -11, 10, p_74875_3_);
/* 248 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 12, -11, 10, p_74875_3_);
/* 249 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 12, -10, 10, p_74875_3_);
/* 250 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), 13, -10, 10, p_74875_3_);
/* 251 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 13, -11, 10, p_74875_3_);
/* 252 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 10, -11, 8, p_74875_3_);
/* 253 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 10, -10, 8, p_74875_3_);
/* 254 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), 10, -10, 7, p_74875_3_);
/* 255 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 10, -11, 7, p_74875_3_);
/* 256 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 10, -11, 12, p_74875_3_);
/* 257 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 10, -10, 12, p_74875_3_);
/* 258 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.CHISELED.func_176675_a()), 10, -10, 13, p_74875_3_);
/* 259 */       func_175811_a(worldIn, Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a()), 10, -11, 13, p_74875_3_);
/* 260 */       Iterator<EnumFacing> var15 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */       
/* 262 */       while (var15.hasNext()) {
/*     */         
/* 264 */         EnumFacing var11 = var15.next();
/*     */         
/* 266 */         if (!this.field_74940_h[var11.getHorizontalIndex()]) {
/*     */           
/* 268 */           int var12 = var11.getFrontOffsetX() * 2;
/* 269 */           int var13 = var11.getFrontOffsetZ() * 2;
/* 270 */           this.field_74940_h[var11.getHorizontalIndex()] = func_180778_a(worldIn, p_74875_3_, p_74875_2_, 10 + var12, -11, 10 + var13, WeightedRandomChestContent.func_177629_a(itemsToGenerateInTemple, new WeightedRandomChestContent[] { Items.enchanted_book.getRandomEnchantedBook(p_74875_2_) }), 2 + p_74875_2_.nextInt(5));
/*     */         } 
/*     */       } 
/*     */       
/* 274 */       return true;
/*     */     } }
/*     */ 
/*     */   
/*     */   static abstract class Feature
/*     */     extends StructureComponent {
/*     */     protected int scatteredFeatureSizeX;
/*     */     protected int scatteredFeatureSizeY;
/*     */     protected int scatteredFeatureSizeZ;
/* 283 */     protected int field_74936_d = -1;
/*     */     
/*     */     private static final String __OBFID = "CL_00000479";
/*     */     
/*     */     public Feature() {}
/*     */     
/*     */     protected Feature(Random p_i2065_1_, int p_i2065_2_, int p_i2065_3_, int p_i2065_4_, int p_i2065_5_, int p_i2065_6_, int p_i2065_7_) {
/* 290 */       super(0);
/* 291 */       this.scatteredFeatureSizeX = p_i2065_5_;
/* 292 */       this.scatteredFeatureSizeY = p_i2065_6_;
/* 293 */       this.scatteredFeatureSizeZ = p_i2065_7_;
/* 294 */       this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2065_1_);
/*     */       
/* 296 */       switch (ComponentScatteredFeaturePieces.SwitchEnumFacing.field_175956_a[this.coordBaseMode.ordinal()]) {
/*     */         
/*     */         case 1:
/*     */         case 2:
/* 300 */           this.boundingBox = new StructureBoundingBox(p_i2065_2_, p_i2065_3_, p_i2065_4_, p_i2065_2_ + p_i2065_5_ - 1, p_i2065_3_ + p_i2065_6_ - 1, p_i2065_4_ + p_i2065_7_ - 1);
/*     */           return;
/*     */       } 
/*     */       
/* 304 */       this.boundingBox = new StructureBoundingBox(p_i2065_2_, p_i2065_3_, p_i2065_4_, p_i2065_2_ + p_i2065_7_ - 1, p_i2065_3_ + p_i2065_6_ - 1, p_i2065_4_ + p_i2065_5_ - 1);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 310 */       p_143012_1_.setInteger("Width", this.scatteredFeatureSizeX);
/* 311 */       p_143012_1_.setInteger("Height", this.scatteredFeatureSizeY);
/* 312 */       p_143012_1_.setInteger("Depth", this.scatteredFeatureSizeZ);
/* 313 */       p_143012_1_.setInteger("HPos", this.field_74936_d);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 318 */       this.scatteredFeatureSizeX = p_143011_1_.getInteger("Width");
/* 319 */       this.scatteredFeatureSizeY = p_143011_1_.getInteger("Height");
/* 320 */       this.scatteredFeatureSizeZ = p_143011_1_.getInteger("Depth");
/* 321 */       this.field_74936_d = p_143011_1_.getInteger("HPos");
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean func_74935_a(World worldIn, StructureBoundingBox p_74935_2_, int p_74935_3_) {
/* 326 */       if (this.field_74936_d >= 0)
/*     */       {
/* 328 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 332 */       int var4 = 0;
/* 333 */       int var5 = 0;
/*     */       
/* 335 */       for (int var6 = this.boundingBox.minZ; var6 <= this.boundingBox.maxZ; var6++) {
/*     */         
/* 337 */         for (int var7 = this.boundingBox.minX; var7 <= this.boundingBox.maxX; var7++) {
/*     */           
/* 339 */           BlockPos var8 = new BlockPos(var7, 64, var6);
/*     */           
/* 341 */           if (p_74935_2_.func_175898_b((Vec3i)var8)) {
/*     */             
/* 343 */             var4 += Math.max(worldIn.func_175672_r(var8).getY(), worldIn.provider.getAverageGroundLevel());
/* 344 */             var5++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 349 */       if (var5 == 0)
/*     */       {
/* 351 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 355 */       this.field_74936_d = var4 / var5;
/* 356 */       this.boundingBox.offset(0, this.field_74936_d - this.boundingBox.minY + p_74935_3_, 0);
/* 357 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class JunglePyramid
/*     */     extends Feature
/*     */   {
/*     */     private boolean field_74947_h;
/*     */     private boolean field_74948_i;
/*     */     private boolean field_74945_j;
/*     */     private boolean field_74946_k;
/* 369 */     private static final List field_175816_i = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 7, 15), new WeightedRandomChestContent(Items.emerald, 0, 1, 3, 2), new WeightedRandomChestContent(Items.bone, 0, 4, 6, 20), new WeightedRandomChestContent(Items.rotten_flesh, 0, 3, 7, 16), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1) });
/* 370 */     private static final List field_175815_j = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.arrow, 0, 2, 7, 30) });
/* 371 */     private static Stones junglePyramidsRandomScatteredStones = new Stones(null);
/*     */     
/*     */     private static final String __OBFID = "CL_00000477";
/*     */     
/*     */     public JunglePyramid() {}
/*     */     
/*     */     public JunglePyramid(Random p_i2064_1_, int p_i2064_2_, int p_i2064_3_) {
/* 378 */       super(p_i2064_1_, p_i2064_2_, 64, p_i2064_3_, 12, 10, 15);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 383 */       super.writeStructureToNBT(p_143012_1_);
/* 384 */       p_143012_1_.setBoolean("placedMainChest", this.field_74947_h);
/* 385 */       p_143012_1_.setBoolean("placedHiddenChest", this.field_74948_i);
/* 386 */       p_143012_1_.setBoolean("placedTrap1", this.field_74945_j);
/* 387 */       p_143012_1_.setBoolean("placedTrap2", this.field_74946_k);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 392 */       super.readStructureFromNBT(p_143011_1_);
/* 393 */       this.field_74947_h = p_143011_1_.getBoolean("placedMainChest");
/* 394 */       this.field_74948_i = p_143011_1_.getBoolean("placedHiddenChest");
/* 395 */       this.field_74945_j = p_143011_1_.getBoolean("placedTrap1");
/* 396 */       this.field_74946_k = p_143011_1_.getBoolean("placedTrap2");
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 401 */       if (!func_74935_a(worldIn, p_74875_3_, 0))
/*     */       {
/* 403 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 407 */       int var4 = getMetadataWithOffset(Blocks.stone_stairs, 3);
/* 408 */       int var5 = getMetadataWithOffset(Blocks.stone_stairs, 2);
/* 409 */       int var6 = getMetadataWithOffset(Blocks.stone_stairs, 0);
/* 410 */       int var7 = getMetadataWithOffset(Blocks.stone_stairs, 1);
/* 411 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, -4, 0, this.scatteredFeatureSizeX - 1, 0, this.scatteredFeatureSizeZ - 1, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 412 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, 1, 2, 9, 2, 2, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 413 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, 1, 12, 9, 2, 12, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 414 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, 1, 3, 2, 2, 11, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 415 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 9, 1, 3, 9, 2, 11, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 416 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 3, 1, 10, 6, 1, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 417 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 3, 13, 10, 6, 13, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 418 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 3, 2, 1, 6, 12, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 419 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 10, 3, 2, 10, 6, 12, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 420 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, 3, 2, 9, 3, 12, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 421 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, 6, 2, 9, 6, 12, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 422 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 3, 7, 3, 8, 7, 11, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 423 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 8, 4, 7, 8, 10, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 424 */       fillWithAir(worldIn, p_74875_3_, 3, 1, 3, 8, 2, 11);
/* 425 */       fillWithAir(worldIn, p_74875_3_, 4, 3, 6, 7, 3, 9);
/* 426 */       fillWithAir(worldIn, p_74875_3_, 2, 4, 2, 9, 5, 12);
/* 427 */       fillWithAir(worldIn, p_74875_3_, 4, 6, 5, 7, 6, 9);
/* 428 */       fillWithAir(worldIn, p_74875_3_, 5, 7, 6, 6, 7, 8);
/* 429 */       fillWithAir(worldIn, p_74875_3_, 5, 1, 2, 6, 2, 2);
/* 430 */       fillWithAir(worldIn, p_74875_3_, 5, 2, 12, 6, 2, 12);
/* 431 */       fillWithAir(worldIn, p_74875_3_, 5, 5, 1, 6, 5, 1);
/* 432 */       fillWithAir(worldIn, p_74875_3_, 5, 5, 13, 6, 5, 13);
/* 433 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 5, 5, p_74875_3_);
/* 434 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 10, 5, 5, p_74875_3_);
/* 435 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 5, 9, p_74875_3_);
/* 436 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 10, 5, 9, p_74875_3_);
/*     */       
/*     */       int var8;
/* 439 */       for (var8 = 0; var8 <= 14; var8 += 14) {
/*     */         
/* 441 */         fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, 4, var8, 2, 5, var8, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 442 */         fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 4, var8, 4, 5, var8, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 443 */         fillWithRandomizedBlocks(worldIn, p_74875_3_, 7, 4, var8, 7, 5, var8, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 444 */         fillWithRandomizedBlocks(worldIn, p_74875_3_, 9, 4, var8, 9, 5, var8, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/*     */       } 
/*     */       
/* 447 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 5, 6, 0, 6, 6, 0, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/*     */       
/* 449 */       for (var8 = 0; var8 <= 11; var8 += 11) {
/*     */         
/* 451 */         for (int var9 = 2; var9 <= 12; var9 += 2)
/*     */         {
/* 453 */           fillWithRandomizedBlocks(worldIn, p_74875_3_, var8, 4, var9, var8, 5, var9, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/*     */         }
/*     */         
/* 456 */         fillWithRandomizedBlocks(worldIn, p_74875_3_, var8, 6, 5, var8, 6, 5, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 457 */         fillWithRandomizedBlocks(worldIn, p_74875_3_, var8, 6, 9, var8, 6, 9, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/*     */       } 
/*     */       
/* 460 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, 7, 2, 2, 9, 2, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 461 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 9, 7, 2, 9, 9, 2, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 462 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, 7, 12, 2, 9, 12, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 463 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 9, 7, 12, 9, 9, 12, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 464 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 9, 4, 4, 9, 4, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 465 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 7, 9, 4, 7, 9, 4, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 466 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 9, 10, 4, 9, 10, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 467 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 7, 9, 10, 7, 9, 10, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 468 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 5, 9, 7, 6, 9, 7, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 469 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 5, 9, 6, p_74875_3_);
/* 470 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 6, 9, 6, p_74875_3_);
/* 471 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var5), 5, 9, 8, p_74875_3_);
/* 472 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var5), 6, 9, 8, p_74875_3_);
/* 473 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 4, 0, 0, p_74875_3_);
/* 474 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 5, 0, 0, p_74875_3_);
/* 475 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 6, 0, 0, p_74875_3_);
/* 476 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 7, 0, 0, p_74875_3_);
/* 477 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 4, 1, 8, p_74875_3_);
/* 478 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 4, 2, 9, p_74875_3_);
/* 479 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 4, 3, 10, p_74875_3_);
/* 480 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 7, 1, 8, p_74875_3_);
/* 481 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 7, 2, 9, p_74875_3_);
/* 482 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 7, 3, 10, p_74875_3_);
/* 483 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 9, 4, 1, 9, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 484 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 7, 1, 9, 7, 1, 9, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 485 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 10, 7, 2, 10, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 486 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 5, 4, 5, 6, 4, 5, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 487 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var6), 4, 4, 5, p_74875_3_);
/* 488 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var7), 7, 4, 5, p_74875_3_);
/*     */       
/* 490 */       for (var8 = 0; var8 < 4; var8++) {
/*     */         
/* 492 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var5), 5, 0 - var8, 6 + var8, p_74875_3_);
/* 493 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var5), 6, 0 - var8, 6 + var8, p_74875_3_);
/* 494 */         fillWithAir(worldIn, p_74875_3_, 5, 0 - var8, 7 + var8, 6, 0 - var8, 9 + var8);
/*     */       } 
/*     */       
/* 497 */       fillWithAir(worldIn, p_74875_3_, 1, -3, 12, 10, -1, 13);
/* 498 */       fillWithAir(worldIn, p_74875_3_, 1, -3, 1, 3, -1, 13);
/* 499 */       fillWithAir(worldIn, p_74875_3_, 1, -3, 1, 9, -1, 5);
/*     */       
/* 501 */       for (var8 = 1; var8 <= 13; var8 += 2)
/*     */       {
/* 503 */         fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, -3, var8, 1, -2, var8, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/*     */       }
/*     */       
/* 506 */       for (var8 = 2; var8 <= 12; var8 += 2)
/*     */       {
/* 508 */         fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, -1, var8, 3, -1, var8, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/*     */       }
/*     */       
/* 511 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, -2, 1, 5, -2, 1, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 512 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 7, -2, 1, 9, -2, 1, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 513 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 6, -3, 1, 6, -3, 1, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 514 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 6, -1, 1, 6, -1, 1, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 515 */       func_175811_a(worldIn, Blocks.tripwire_hook.getStateFromMeta(getMetadataWithOffset((Block)Blocks.tripwire_hook, EnumFacing.EAST.getHorizontalIndex())).withProperty((IProperty)BlockTripWireHook.field_176265_M, Boolean.valueOf(true)), 1, -3, 8, p_74875_3_);
/* 516 */       func_175811_a(worldIn, Blocks.tripwire_hook.getStateFromMeta(getMetadataWithOffset((Block)Blocks.tripwire_hook, EnumFacing.WEST.getHorizontalIndex())).withProperty((IProperty)BlockTripWireHook.field_176265_M, Boolean.valueOf(true)), 4, -3, 8, p_74875_3_);
/* 517 */       func_175811_a(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.field_176294_M, Boolean.valueOf(true)), 2, -3, 8, p_74875_3_);
/* 518 */       func_175811_a(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.field_176294_M, Boolean.valueOf(true)), 3, -3, 8, p_74875_3_);
/* 519 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 7, p_74875_3_);
/* 520 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 6, p_74875_3_);
/* 521 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 5, p_74875_3_);
/* 522 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 4, p_74875_3_);
/* 523 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 3, p_74875_3_);
/* 524 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 2, p_74875_3_);
/* 525 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 5, -3, 1, p_74875_3_);
/* 526 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 4, -3, 1, p_74875_3_);
/* 527 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 3, -3, 1, p_74875_3_);
/*     */       
/* 529 */       if (!this.field_74945_j)
/*     */       {
/* 531 */         this.field_74945_j = func_175806_a(worldIn, p_74875_3_, p_74875_2_, 3, -2, 1, EnumFacing.NORTH.getIndex(), field_175815_j, 2);
/*     */       }
/*     */       
/* 534 */       func_175811_a(worldIn, Blocks.vine.getStateFromMeta(15), 3, -2, 2, p_74875_3_);
/* 535 */       func_175811_a(worldIn, Blocks.tripwire_hook.getStateFromMeta(getMetadataWithOffset((Block)Blocks.tripwire_hook, EnumFacing.NORTH.getHorizontalIndex())).withProperty((IProperty)BlockTripWireHook.field_176265_M, Boolean.valueOf(true)), 7, -3, 1, p_74875_3_);
/* 536 */       func_175811_a(worldIn, Blocks.tripwire_hook.getStateFromMeta(getMetadataWithOffset((Block)Blocks.tripwire_hook, EnumFacing.SOUTH.getHorizontalIndex())).withProperty((IProperty)BlockTripWireHook.field_176265_M, Boolean.valueOf(true)), 7, -3, 5, p_74875_3_);
/* 537 */       func_175811_a(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.field_176294_M, Boolean.valueOf(true)), 7, -3, 2, p_74875_3_);
/* 538 */       func_175811_a(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.field_176294_M, Boolean.valueOf(true)), 7, -3, 3, p_74875_3_);
/* 539 */       func_175811_a(worldIn, Blocks.tripwire.getDefaultState().withProperty((IProperty)BlockTripWire.field_176294_M, Boolean.valueOf(true)), 7, -3, 4, p_74875_3_);
/* 540 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 8, -3, 6, p_74875_3_);
/* 541 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 9, -3, 6, p_74875_3_);
/* 542 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 9, -3, 5, p_74875_3_);
/* 543 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 9, -3, 4, p_74875_3_);
/* 544 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 9, -2, 4, p_74875_3_);
/*     */       
/* 546 */       if (!this.field_74946_k)
/*     */       {
/* 548 */         this.field_74946_k = func_175806_a(worldIn, p_74875_3_, p_74875_2_, 9, -2, 3, EnumFacing.WEST.getIndex(), field_175815_j, 2);
/*     */       }
/*     */       
/* 551 */       func_175811_a(worldIn, Blocks.vine.getStateFromMeta(15), 8, -1, 3, p_74875_3_);
/* 552 */       func_175811_a(worldIn, Blocks.vine.getStateFromMeta(15), 8, -2, 3, p_74875_3_);
/*     */       
/* 554 */       if (!this.field_74947_h)
/*     */       {
/* 556 */         this.field_74947_h = func_180778_a(worldIn, p_74875_3_, p_74875_2_, 8, -3, 3, WeightedRandomChestContent.func_177629_a(field_175816_i, new WeightedRandomChestContent[] { Items.enchanted_book.getRandomEnchantedBook(p_74875_2_) }), 2 + p_74875_2_.nextInt(5));
/*     */       }
/*     */       
/* 559 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 9, -3, 2, p_74875_3_);
/* 560 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 8, -3, 1, p_74875_3_);
/* 561 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 4, -3, 5, p_74875_3_);
/* 562 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 5, -2, 5, p_74875_3_);
/* 563 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 5, -1, 5, p_74875_3_);
/* 564 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 6, -3, 5, p_74875_3_);
/* 565 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 7, -2, 5, p_74875_3_);
/* 566 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 7, -1, 5, p_74875_3_);
/* 567 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 8, -3, 5, p_74875_3_);
/* 568 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 9, -1, 1, 9, -1, 5, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 569 */       fillWithAir(worldIn, p_74875_3_, 8, -3, 8, 10, -1, 10);
/* 570 */       func_175811_a(worldIn, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 8, -2, 11, p_74875_3_);
/* 571 */       func_175811_a(worldIn, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 9, -2, 11, p_74875_3_);
/* 572 */       func_175811_a(worldIn, Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CHISELED_META), 10, -2, 11, p_74875_3_);
/* 573 */       func_175811_a(worldIn, Blocks.lever.getStateFromMeta(BlockLever.func_176357_a(EnumFacing.getFront(getMetadataWithOffset(Blocks.lever, EnumFacing.NORTH.getIndex())))), 8, -2, 12, p_74875_3_);
/* 574 */       func_175811_a(worldIn, Blocks.lever.getStateFromMeta(BlockLever.func_176357_a(EnumFacing.getFront(getMetadataWithOffset(Blocks.lever, EnumFacing.NORTH.getIndex())))), 9, -2, 12, p_74875_3_);
/* 575 */       func_175811_a(worldIn, Blocks.lever.getStateFromMeta(BlockLever.func_176357_a(EnumFacing.getFront(getMetadataWithOffset(Blocks.lever, EnumFacing.NORTH.getIndex())))), 10, -2, 12, p_74875_3_);
/* 576 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 8, -3, 8, 8, -3, 10, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 577 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 10, -3, 8, 10, -3, 10, false, p_74875_2_, junglePyramidsRandomScatteredStones);
/* 578 */       func_175811_a(worldIn, Blocks.mossy_cobblestone.getDefaultState(), 10, -2, 9, p_74875_3_);
/* 579 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 8, -2, 9, p_74875_3_);
/* 580 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 8, -2, 10, p_74875_3_);
/* 581 */       func_175811_a(worldIn, Blocks.redstone_wire.getDefaultState(), 10, -1, 9, p_74875_3_);
/* 582 */       func_175811_a(worldIn, Blocks.sticky_piston.getStateFromMeta(EnumFacing.UP.getIndex()), 9, -2, 8, p_74875_3_);
/* 583 */       func_175811_a(worldIn, Blocks.sticky_piston.getStateFromMeta(getMetadataWithOffset((Block)Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -2, 8, p_74875_3_);
/* 584 */       func_175811_a(worldIn, Blocks.sticky_piston.getStateFromMeta(getMetadataWithOffset((Block)Blocks.sticky_piston, EnumFacing.WEST.getIndex())), 10, -1, 8, p_74875_3_);
/* 585 */       func_175811_a(worldIn, Blocks.unpowered_repeater.getStateFromMeta(getMetadataWithOffset((Block)Blocks.unpowered_repeater, EnumFacing.NORTH.getHorizontalIndex())), 10, -2, 10, p_74875_3_);
/*     */       
/* 587 */       if (!this.field_74948_i)
/*     */       {
/* 589 */         this.field_74948_i = func_180778_a(worldIn, p_74875_3_, p_74875_2_, 9, -3, 10, WeightedRandomChestContent.func_177629_a(field_175816_i, new WeightedRandomChestContent[] { Items.enchanted_book.getRandomEnchantedBook(p_74875_2_) }), 2 + p_74875_2_.nextInt(5));
/*     */       }
/*     */       
/* 592 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     static class Stones
/*     */       extends StructureComponent.BlockSelector
/*     */     {
/*     */       private static final String __OBFID = "CL_00000478";
/*     */       
/*     */       private Stones() {}
/*     */       
/*     */       public void selectBlocks(Random p_75062_1_, int p_75062_2_, int p_75062_3_, int p_75062_4_, boolean p_75062_5_) {
/* 604 */         if (p_75062_1_.nextFloat() < 0.4F) {
/*     */           
/* 606 */           this.field_151562_a = Blocks.cobblestone.getDefaultState();
/*     */         }
/*     */         else {
/*     */           
/* 610 */           this.field_151562_a = Blocks.mossy_cobblestone.getDefaultState();
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/*     */       Stones(ComponentScatteredFeaturePieces.SwitchEnumFacing p_i45583_1_) {
/* 616 */         this();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SwampHut
/*     */     extends Feature
/*     */   {
/*     */     private boolean hasWitch;
/*     */     private static final String __OBFID = "CL_00000480";
/*     */     
/*     */     public SwampHut() {}
/*     */     
/*     */     public SwampHut(Random p_i2066_1_, int p_i2066_2_, int p_i2066_3_) {
/* 630 */       super(p_i2066_1_, p_i2066_2_, 64, p_i2066_3_, 7, 5, 9);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 635 */       super.writeStructureToNBT(p_143012_1_);
/* 636 */       p_143012_1_.setBoolean("Witch", this.hasWitch);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 641 */       super.readStructureFromNBT(p_143011_1_);
/* 642 */       this.hasWitch = p_143011_1_.getBoolean("Witch");
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 647 */       if (!func_74935_a(worldIn, p_74875_3_, 0))
/*     */       {
/* 649 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 653 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 5, 1, 7, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), false);
/* 654 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 2, 5, 4, 7, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), false);
/* 655 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 0, 4, 1, 0, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), false);
/* 656 */       func_175804_a(worldIn, p_74875_3_, 2, 2, 2, 3, 3, 2, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), false);
/* 657 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 3, 1, 3, 6, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), false);
/* 658 */       func_175804_a(worldIn, p_74875_3_, 5, 2, 3, 5, 3, 6, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), false);
/* 659 */       func_175804_a(worldIn, p_74875_3_, 2, 2, 7, 4, 3, 7, Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), Blocks.planks.getStateFromMeta(BlockPlanks.EnumType.SPRUCE.func_176839_a()), false);
/* 660 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 2, 1, 3, 2, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 661 */       func_175804_a(worldIn, p_74875_3_, 5, 0, 2, 5, 3, 2, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 662 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 7, 1, 3, 7, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 663 */       func_175804_a(worldIn, p_74875_3_, 5, 0, 7, 5, 3, 7, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 664 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 2, 3, 2, p_74875_3_);
/* 665 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 3, 3, 7, p_74875_3_);
/* 666 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 3, 4, p_74875_3_);
/* 667 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 5, 3, 4, p_74875_3_);
/* 668 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 5, 3, 5, p_74875_3_);
/* 669 */       func_175811_a(worldIn, Blocks.flower_pot.getDefaultState().withProperty((IProperty)BlockFlowerPot.field_176443_b, (Comparable)BlockFlowerPot.EnumFlowerType.MUSHROOM_RED), 1, 3, 5, p_74875_3_);
/* 670 */       func_175811_a(worldIn, Blocks.crafting_table.getDefaultState(), 3, 2, 6, p_74875_3_);
/* 671 */       func_175811_a(worldIn, Blocks.cauldron.getDefaultState(), 4, 2, 6, p_74875_3_);
/* 672 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 2, 1, p_74875_3_);
/* 673 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 5, 2, 1, p_74875_3_);
/* 674 */       int var4 = getMetadataWithOffset(Blocks.oak_stairs, 3);
/* 675 */       int var5 = getMetadataWithOffset(Blocks.oak_stairs, 1);
/* 676 */       int var6 = getMetadataWithOffset(Blocks.oak_stairs, 0);
/* 677 */       int var7 = getMetadataWithOffset(Blocks.oak_stairs, 2);
/* 678 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 1, 6, 4, 1, Blocks.spruce_stairs.getStateFromMeta(var4), Blocks.spruce_stairs.getStateFromMeta(var4), false);
/* 679 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 2, 0, 4, 7, Blocks.spruce_stairs.getStateFromMeta(var6), Blocks.spruce_stairs.getStateFromMeta(var6), false);
/* 680 */       func_175804_a(worldIn, p_74875_3_, 6, 4, 2, 6, 4, 7, Blocks.spruce_stairs.getStateFromMeta(var5), Blocks.spruce_stairs.getStateFromMeta(var5), false);
/* 681 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 8, 6, 4, 8, Blocks.spruce_stairs.getStateFromMeta(var7), Blocks.spruce_stairs.getStateFromMeta(var7), false);
/*     */       
/*     */       int var8;
/*     */       
/* 685 */       for (var8 = 2; var8 <= 7; var8 += 5) {
/*     */         
/* 687 */         for (int var9 = 1; var9 <= 5; var9 += 4)
/*     */         {
/* 689 */           func_175808_b(worldIn, Blocks.log.getDefaultState(), var9, -1, var8, p_74875_3_);
/*     */         }
/*     */       } 
/*     */       
/* 693 */       if (!this.hasWitch) {
/*     */         
/* 695 */         var8 = getXWithOffset(2, 5);
/* 696 */         int var9 = getYWithOffset(2);
/* 697 */         int var10 = getZWithOffset(2, 5);
/*     */         
/* 699 */         if (p_74875_3_.func_175898_b((Vec3i)new BlockPos(var8, var9, var10))) {
/*     */           
/* 701 */           this.hasWitch = true;
/* 702 */           EntityWitch var11 = new EntityWitch(worldIn);
/* 703 */           var11.setLocationAndAngles(var8 + 0.5D, var9, var10 + 0.5D, 0.0F, 0.0F);
/* 704 */           var11.func_180482_a(worldIn.getDifficultyForLocation(new BlockPos(var8, var9, var10)), null);
/* 705 */           worldIn.spawnEntityInWorld((Entity)var11);
/*     */         } 
/*     */       } 
/*     */       
/* 709 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 716 */     static final int[] field_175956_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00001971";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 723 */         field_175956_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 725 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 732 */         field_175956_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 734 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\ComponentScatteredFeaturePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */