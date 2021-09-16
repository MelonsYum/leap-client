/*      */ package net.minecraft.world.gen.structure;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.tileentity.TileEntity;
/*      */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.Vec3i;
/*      */ import net.minecraft.util.WeightedRandomChestContent;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ public class StructureNetherBridgePieces {
/*   20 */   private static final PieceWeight[] primaryComponents = new PieceWeight[] { new PieceWeight(Straight.class, 30, 0, true), new PieceWeight(Crossing3.class, 10, 4), new PieceWeight(Crossing.class, 10, 4), new PieceWeight(Stairs.class, 10, 3), new PieceWeight(Throne.class, 5, 2), new PieceWeight(Entrance.class, 5, 1) };
/*   21 */   private static final PieceWeight[] secondaryComponents = new PieceWeight[] { new PieceWeight(Corridor5.class, 25, 0, true), new PieceWeight(Crossing2.class, 15, 5), new PieceWeight(Corridor2.class, 5, 10), new PieceWeight(Corridor.class, 5, 10), new PieceWeight(Corridor3.class, 10, 3, true), new PieceWeight(Corridor4.class, 7, 2), new PieceWeight(NetherStalkRoom.class, 5, 2) };
/*      */   
/*      */   private static final String __OBFID = "CL_00000453";
/*      */   
/*      */   public static void registerNetherFortressPieces() {
/*   26 */     MapGenStructureIO.registerStructureComponent(Crossing3.class, "NeBCr");
/*   27 */     MapGenStructureIO.registerStructureComponent(End.class, "NeBEF");
/*   28 */     MapGenStructureIO.registerStructureComponent(Straight.class, "NeBS");
/*   29 */     MapGenStructureIO.registerStructureComponent(Corridor3.class, "NeCCS");
/*   30 */     MapGenStructureIO.registerStructureComponent(Corridor4.class, "NeCTB");
/*   31 */     MapGenStructureIO.registerStructureComponent(Entrance.class, "NeCE");
/*   32 */     MapGenStructureIO.registerStructureComponent(Crossing2.class, "NeSCSC");
/*   33 */     MapGenStructureIO.registerStructureComponent(Corridor.class, "NeSCLT");
/*   34 */     MapGenStructureIO.registerStructureComponent(Corridor5.class, "NeSC");
/*   35 */     MapGenStructureIO.registerStructureComponent(Corridor2.class, "NeSCRT");
/*   36 */     MapGenStructureIO.registerStructureComponent(NetherStalkRoom.class, "NeCSR");
/*   37 */     MapGenStructureIO.registerStructureComponent(Throne.class, "NeMT");
/*   38 */     MapGenStructureIO.registerStructureComponent(Crossing.class, "NeRC");
/*   39 */     MapGenStructureIO.registerStructureComponent(Stairs.class, "NeSR");
/*   40 */     MapGenStructureIO.registerStructureComponent(Start.class, "NeStart");
/*      */   }
/*      */ 
/*      */   
/*      */   private static Piece func_175887_b(PieceWeight p_175887_0_, List p_175887_1_, Random p_175887_2_, int p_175887_3_, int p_175887_4_, int p_175887_5_, EnumFacing p_175887_6_, int p_175887_7_) {
/*   45 */     Class<Straight> var8 = p_175887_0_.weightClass;
/*   46 */     Object var9 = null;
/*      */     
/*   48 */     if (var8 == Straight.class) {
/*      */       
/*   50 */       var9 = Straight.func_175882_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   52 */     else if (var8 == Crossing3.class) {
/*      */       
/*   54 */       var9 = Crossing3.func_175885_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   56 */     else if (var8 == Crossing.class) {
/*      */       
/*   58 */       var9 = Crossing.func_175873_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   60 */     else if (var8 == Stairs.class) {
/*      */       
/*   62 */       var9 = Stairs.func_175872_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
/*      */     }
/*   64 */     else if (var8 == Throne.class) {
/*      */       
/*   66 */       var9 = Throne.func_175874_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_7_, p_175887_6_);
/*      */     }
/*   68 */     else if (var8 == Entrance.class) {
/*      */       
/*   70 */       var9 = Entrance.func_175881_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   72 */     else if (var8 == Corridor5.class) {
/*      */       
/*   74 */       var9 = Corridor5.func_175877_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   76 */     else if (var8 == Corridor2.class) {
/*      */       
/*   78 */       var9 = Corridor2.func_175876_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   80 */     else if (var8 == Corridor.class) {
/*      */       
/*   82 */       var9 = Corridor.func_175879_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   84 */     else if (var8 == Corridor3.class) {
/*      */       
/*   86 */       var9 = Corridor3.func_175883_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   88 */     else if (var8 == Corridor4.class) {
/*      */       
/*   90 */       var9 = Corridor4.func_175880_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   92 */     else if (var8 == Crossing2.class) {
/*      */       
/*   94 */       var9 = Crossing2.func_175878_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     }
/*   96 */     else if (var8 == NetherStalkRoom.class) {
/*      */       
/*   98 */       var9 = NetherStalkRoom.func_175875_a(p_175887_1_, p_175887_2_, p_175887_3_, p_175887_4_, p_175887_5_, p_175887_6_, p_175887_7_);
/*      */     } 
/*      */     
/*  101 */     return (Piece)var9;
/*      */   }
/*      */   
/*      */   public static class Corridor
/*      */     extends Piece
/*      */   {
/*      */     private boolean field_111021_b;
/*      */     private static final String __OBFID = "CL_00000461";
/*      */     
/*      */     public Corridor() {}
/*      */     
/*      */     public Corridor(int p_i45615_1_, Random p_i45615_2_, StructureBoundingBox p_i45615_3_, EnumFacing p_i45615_4_) {
/*  113 */       super(p_i45615_1_);
/*  114 */       this.coordBaseMode = p_i45615_4_;
/*  115 */       this.boundingBox = p_i45615_3_;
/*  116 */       this.field_111021_b = (p_i45615_2_.nextInt(3) == 0);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  121 */       super.readStructureFromNBT(p_143011_1_);
/*  122 */       this.field_111021_b = p_143011_1_.getBoolean("Chest");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  127 */       super.writeStructureToNBT(p_143012_1_);
/*  128 */       p_143012_1_.setBoolean("Chest", this.field_111021_b);
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  133 */       getNextComponentX((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Corridor func_175879_a(List p_175879_0_, Random p_175879_1_, int p_175879_2_, int p_175879_3_, int p_175879_4_, EnumFacing p_175879_5_, int p_175879_6_) {
/*  138 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175879_2_, p_175879_3_, p_175879_4_, -1, 0, 0, 5, 7, 5, p_175879_5_);
/*  139 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175879_0_, var7) == null) ? new Corridor(p_175879_6_, p_175879_1_, var7, p_175879_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  144 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  145 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  146 */       func_175804_a(worldIn, p_74875_3_, 4, 2, 0, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  147 */       func_175804_a(worldIn, p_74875_3_, 4, 3, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  148 */       func_175804_a(worldIn, p_74875_3_, 4, 3, 3, 4, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  149 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  150 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 4, 3, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  151 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 4, 1, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  152 */       func_175804_a(worldIn, p_74875_3_, 3, 3, 4, 3, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*  154 */       if (this.field_111021_b && p_74875_3_.func_175898_b((Vec3i)new BlockPos(getXWithOffset(3, 3), getYWithOffset(2), getZWithOffset(3, 3)))) {
/*      */         
/*  156 */         this.field_111021_b = false;
/*  157 */         func_180778_a(worldIn, p_74875_3_, p_74875_2_, 3, 2, 3, field_111019_a, 2 + p_74875_2_.nextInt(4));
/*      */       } 
/*      */       
/*  160 */       func_175804_a(worldIn, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*  162 */       for (int var4 = 0; var4 <= 4; var4++) {
/*      */         
/*  164 */         for (int var5 = 0; var5 <= 4; var5++)
/*      */         {
/*  166 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/*  170 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Corridor2
/*      */     extends Piece
/*      */   {
/*      */     private boolean field_111020_b;
/*      */     private static final String __OBFID = "CL_00000463";
/*      */     
/*      */     public Corridor2() {}
/*      */     
/*      */     public Corridor2(int p_i45613_1_, Random p_i45613_2_, StructureBoundingBox p_i45613_3_, EnumFacing p_i45613_4_) {
/*  183 */       super(p_i45613_1_);
/*  184 */       this.coordBaseMode = p_i45613_4_;
/*  185 */       this.boundingBox = p_i45613_3_;
/*  186 */       this.field_111020_b = (p_i45613_2_.nextInt(3) == 0);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  191 */       super.readStructureFromNBT(p_143011_1_);
/*  192 */       this.field_111020_b = p_143011_1_.getBoolean("Chest");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  197 */       super.writeStructureToNBT(p_143012_1_);
/*  198 */       p_143012_1_.setBoolean("Chest", this.field_111020_b);
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  203 */       getNextComponentZ((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Corridor2 func_175876_a(List p_175876_0_, Random p_175876_1_, int p_175876_2_, int p_175876_3_, int p_175876_4_, EnumFacing p_175876_5_, int p_175876_6_) {
/*  208 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175876_2_, p_175876_3_, p_175876_4_, -1, 0, 0, 5, 7, 5, p_175876_5_);
/*  209 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175876_0_, var7) == null) ? new Corridor2(p_175876_6_, p_175876_1_, var7, p_175876_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  214 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  215 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  216 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  217 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 1, 0, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  218 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 3, 0, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  219 */       func_175804_a(worldIn, p_74875_3_, 4, 2, 0, 4, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  220 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 4, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  221 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 4, 1, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  222 */       func_175804_a(worldIn, p_74875_3_, 3, 3, 4, 3, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*  224 */       if (this.field_111020_b && p_74875_3_.func_175898_b((Vec3i)new BlockPos(getXWithOffset(1, 3), getYWithOffset(2), getZWithOffset(1, 3)))) {
/*      */         
/*  226 */         this.field_111020_b = false;
/*  227 */         func_180778_a(worldIn, p_74875_3_, p_74875_2_, 1, 2, 3, field_111019_a, 2 + p_74875_2_.nextInt(4));
/*      */       } 
/*      */       
/*  230 */       func_175804_a(worldIn, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*  232 */       for (int var4 = 0; var4 <= 4; var4++) {
/*      */         
/*  234 */         for (int var5 = 0; var5 <= 4; var5++)
/*      */         {
/*  236 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/*  240 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Corridor3
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000457";
/*      */     
/*      */     public Corridor3() {}
/*      */     
/*      */     public Corridor3(int p_i45619_1_, Random p_i45619_2_, StructureBoundingBox p_i45619_3_, EnumFacing p_i45619_4_) {
/*  252 */       super(p_i45619_1_);
/*  253 */       this.coordBaseMode = p_i45619_4_;
/*  254 */       this.boundingBox = p_i45619_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  259 */       getNextComponentNormal((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 0, true);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Corridor3 func_175883_a(List p_175883_0_, Random p_175883_1_, int p_175883_2_, int p_175883_3_, int p_175883_4_, EnumFacing p_175883_5_, int p_175883_6_) {
/*  264 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175883_2_, p_175883_3_, p_175883_4_, -1, -7, 0, 5, 14, 10, p_175883_5_);
/*  265 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175883_0_, var7) == null) ? new Corridor3(p_175883_6_, p_175883_1_, var7, p_175883_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  270 */       int var4 = getMetadataWithOffset(Blocks.nether_brick_stairs, 2);
/*      */       
/*  272 */       for (int var5 = 0; var5 <= 9; var5++) {
/*      */         
/*  274 */         int var6 = Math.max(1, 7 - var5);
/*  275 */         int var7 = Math.min(Math.max(var6 + 5, 14 - var5), 13);
/*  276 */         int var8 = var5;
/*  277 */         func_175804_a(worldIn, p_74875_3_, 0, 0, var5, 4, var6, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  278 */         func_175804_a(worldIn, p_74875_3_, 1, var6 + 1, var5, 3, var7 - 1, var5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */         
/*  280 */         if (var5 <= 6) {
/*      */           
/*  282 */           func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var4), 1, var6 + 1, var5, p_74875_3_);
/*  283 */           func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var4), 2, var6 + 1, var5, p_74875_3_);
/*  284 */           func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var4), 3, var6 + 1, var5, p_74875_3_);
/*      */         } 
/*      */         
/*  287 */         func_175804_a(worldIn, p_74875_3_, 0, var7, var5, 4, var7, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  288 */         func_175804_a(worldIn, p_74875_3_, 0, var6 + 1, var5, 0, var7 - 1, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  289 */         func_175804_a(worldIn, p_74875_3_, 4, var6 + 1, var5, 4, var7 - 1, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */         
/*  291 */         if ((var5 & 0x1) == 0) {
/*      */           
/*  293 */           func_175804_a(worldIn, p_74875_3_, 0, var6 + 2, var5, 0, var6 + 3, var5, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  294 */           func_175804_a(worldIn, p_74875_3_, 4, var6 + 2, var5, 4, var6 + 3, var5, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*      */         } 
/*      */         
/*  297 */         for (int var9 = 0; var9 <= 4; var9++)
/*      */         {
/*  299 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var9, -1, var8, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/*  303 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Corridor4
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000458";
/*      */     
/*      */     public Corridor4() {}
/*      */     
/*      */     public Corridor4(int p_i45618_1_, Random p_i45618_2_, StructureBoundingBox p_i45618_3_, EnumFacing p_i45618_4_) {
/*  315 */       super(p_i45618_1_);
/*  316 */       this.coordBaseMode = p_i45618_4_;
/*  317 */       this.boundingBox = p_i45618_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  322 */       byte var4 = 1;
/*      */       
/*  324 */       if (this.coordBaseMode == EnumFacing.WEST || this.coordBaseMode == EnumFacing.NORTH)
/*      */       {
/*  326 */         var4 = 5;
/*      */       }
/*      */       
/*  329 */       getNextComponentX((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, var4, (p_74861_3_.nextInt(8) > 0));
/*  330 */       getNextComponentZ((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, var4, (p_74861_3_.nextInt(8) > 0));
/*      */     }
/*      */ 
/*      */     
/*      */     public static Corridor4 func_175880_a(List p_175880_0_, Random p_175880_1_, int p_175880_2_, int p_175880_3_, int p_175880_4_, EnumFacing p_175880_5_, int p_175880_6_) {
/*  335 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175880_2_, p_175880_3_, p_175880_4_, -3, 0, 0, 9, 7, 9, p_175880_5_);
/*  336 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175880_0_, var7) == null) ? new Corridor4(p_175880_6_, p_175880_1_, var7, p_175880_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  341 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 8, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  342 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 8, 5, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  343 */       func_175804_a(worldIn, p_74875_3_, 0, 6, 0, 8, 6, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  344 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 2, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  345 */       func_175804_a(worldIn, p_74875_3_, 6, 2, 0, 8, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  346 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 0, 1, 4, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  347 */       func_175804_a(worldIn, p_74875_3_, 7, 3, 0, 7, 4, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  348 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 4, 8, 2, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  349 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 4, 2, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  350 */       func_175804_a(worldIn, p_74875_3_, 6, 1, 4, 7, 2, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  351 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 8, 8, 3, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  352 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 6, 0, 3, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  353 */       func_175804_a(worldIn, p_74875_3_, 8, 3, 6, 8, 3, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  354 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 4, 0, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  355 */       func_175804_a(worldIn, p_74875_3_, 8, 3, 4, 8, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  356 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 5, 2, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  357 */       func_175804_a(worldIn, p_74875_3_, 6, 3, 5, 7, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  358 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 5, 1, 5, 5, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  359 */       func_175804_a(worldIn, p_74875_3_, 7, 4, 5, 7, 5, 5, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*      */       
/*  361 */       for (int var4 = 0; var4 <= 5; var4++) {
/*      */         
/*  363 */         for (int var5 = 0; var5 <= 8; var5++)
/*      */         {
/*  365 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var5, -1, var4, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/*  369 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Corridor5
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000462";
/*      */     
/*      */     public Corridor5() {}
/*      */     
/*      */     public Corridor5(int p_i45614_1_, Random p_i45614_2_, StructureBoundingBox p_i45614_3_, EnumFacing p_i45614_4_) {
/*  381 */       super(p_i45614_1_);
/*  382 */       this.coordBaseMode = p_i45614_4_;
/*  383 */       this.boundingBox = p_i45614_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  388 */       getNextComponentNormal((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 0, true);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Corridor5 func_175877_a(List p_175877_0_, Random p_175877_1_, int p_175877_2_, int p_175877_3_, int p_175877_4_, EnumFacing p_175877_5_, int p_175877_6_) {
/*  393 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175877_2_, p_175877_3_, p_175877_4_, -1, 0, 0, 5, 7, 5, p_175877_5_);
/*  394 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175877_0_, var7) == null) ? new Corridor5(p_175877_6_, p_175877_1_, var7, p_175877_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  399 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  400 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  401 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  402 */       func_175804_a(worldIn, p_74875_3_, 4, 2, 0, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  403 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 1, 0, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  404 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 3, 0, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  405 */       func_175804_a(worldIn, p_74875_3_, 4, 3, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  406 */       func_175804_a(worldIn, p_74875_3_, 4, 3, 3, 4, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  407 */       func_175804_a(worldIn, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*  409 */       for (int var4 = 0; var4 <= 4; var4++) {
/*      */         
/*  411 */         for (int var5 = 0; var5 <= 4; var5++)
/*      */         {
/*  413 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/*  417 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Crossing
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000468";
/*      */     
/*      */     public Crossing() {}
/*      */     
/*      */     public Crossing(int p_i45610_1_, Random p_i45610_2_, StructureBoundingBox p_i45610_3_, EnumFacing p_i45610_4_) {
/*  429 */       super(p_i45610_1_);
/*  430 */       this.coordBaseMode = p_i45610_4_;
/*  431 */       this.boundingBox = p_i45610_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  436 */       getNextComponentNormal((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 2, 0, false);
/*  437 */       getNextComponentX((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 2, false);
/*  438 */       getNextComponentZ((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 2, false);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Crossing func_175873_a(List p_175873_0_, Random p_175873_1_, int p_175873_2_, int p_175873_3_, int p_175873_4_, EnumFacing p_175873_5_, int p_175873_6_) {
/*  443 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175873_2_, p_175873_3_, p_175873_4_, -2, 0, 0, 7, 9, 7, p_175873_5_);
/*  444 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175873_0_, var7) == null) ? new Crossing(p_175873_6_, p_175873_1_, var7, p_175873_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  449 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 6, 1, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  450 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 6, 7, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  451 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 1, 6, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  452 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 6, 1, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  453 */       func_175804_a(worldIn, p_74875_3_, 5, 2, 0, 6, 6, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  454 */       func_175804_a(worldIn, p_74875_3_, 5, 2, 6, 6, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  455 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 6, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  456 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 5, 0, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  457 */       func_175804_a(worldIn, p_74875_3_, 6, 2, 0, 6, 6, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  458 */       func_175804_a(worldIn, p_74875_3_, 6, 2, 5, 6, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  459 */       func_175804_a(worldIn, p_74875_3_, 2, 6, 0, 4, 6, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  460 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 0, 4, 5, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  461 */       func_175804_a(worldIn, p_74875_3_, 2, 6, 6, 4, 6, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  462 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 6, 4, 5, 6, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  463 */       func_175804_a(worldIn, p_74875_3_, 0, 6, 2, 0, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  464 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 2, 0, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  465 */       func_175804_a(worldIn, p_74875_3_, 6, 6, 2, 6, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  466 */       func_175804_a(worldIn, p_74875_3_, 6, 5, 2, 6, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*      */       
/*  468 */       for (int var4 = 0; var4 <= 6; var4++) {
/*      */         
/*  470 */         for (int var5 = 0; var5 <= 6; var5++)
/*      */         {
/*  472 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/*  476 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Crossing2
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000460";
/*      */     
/*      */     public Crossing2() {}
/*      */     
/*      */     public Crossing2(int p_i45616_1_, Random p_i45616_2_, StructureBoundingBox p_i45616_3_, EnumFacing p_i45616_4_) {
/*  488 */       super(p_i45616_1_);
/*  489 */       this.coordBaseMode = p_i45616_4_;
/*  490 */       this.boundingBox = p_i45616_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  495 */       getNextComponentNormal((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 0, true);
/*  496 */       getNextComponentX((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
/*  497 */       getNextComponentZ((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, 1, true);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Crossing2 func_175878_a(List p_175878_0_, Random p_175878_1_, int p_175878_2_, int p_175878_3_, int p_175878_4_, EnumFacing p_175878_5_, int p_175878_6_) {
/*  502 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175878_2_, p_175878_3_, p_175878_4_, -1, 0, 0, 5, 7, 5, p_175878_5_);
/*  503 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175878_0_, var7) == null) ? new Crossing2(p_175878_6_, p_175878_1_, var7, p_175878_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  508 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  509 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  510 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  511 */       func_175804_a(worldIn, p_74875_3_, 4, 2, 0, 4, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  512 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 4, 0, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  513 */       func_175804_a(worldIn, p_74875_3_, 4, 2, 4, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  514 */       func_175804_a(worldIn, p_74875_3_, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*  516 */       for (int var4 = 0; var4 <= 4; var4++) {
/*      */         
/*  518 */         for (int var5 = 0; var5 <= 4; var5++)
/*      */         {
/*  520 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/*  524 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Crossing3
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000454";
/*      */     
/*      */     public Crossing3() {}
/*      */     
/*      */     public Crossing3(int p_i45622_1_, Random p_i45622_2_, StructureBoundingBox p_i45622_3_, EnumFacing p_i45622_4_) {
/*  536 */       super(p_i45622_1_);
/*  537 */       this.coordBaseMode = p_i45622_4_;
/*  538 */       this.boundingBox = p_i45622_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     protected Crossing3(Random p_i2042_1_, int p_i2042_2_, int p_i2042_3_) {
/*  543 */       super(0);
/*  544 */       this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2042_1_);
/*      */       
/*  546 */       switch (StructureNetherBridgePieces.SwitchEnumFacing.field_175888_a[this.coordBaseMode.ordinal()]) {
/*      */         
/*      */         case 1:
/*      */         case 2:
/*  550 */           this.boundingBox = new StructureBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
/*      */           return;
/*      */       } 
/*      */       
/*  554 */       this.boundingBox = new StructureBoundingBox(p_i2042_2_, 64, p_i2042_3_, p_i2042_2_ + 19 - 1, 73, p_i2042_3_ + 19 - 1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  560 */       getNextComponentNormal((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 8, 3, false);
/*  561 */       getNextComponentX((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 3, 8, false);
/*  562 */       getNextComponentZ((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 3, 8, false);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Crossing3 func_175885_a(List p_175885_0_, Random p_175885_1_, int p_175885_2_, int p_175885_3_, int p_175885_4_, EnumFacing p_175885_5_, int p_175885_6_) {
/*  567 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175885_2_, p_175885_3_, p_175885_4_, -8, -3, 0, 19, 10, 19, p_175885_5_);
/*  568 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175885_0_, var7) == null) ? new Crossing3(p_175885_6_, p_175885_1_, var7, p_175885_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  573 */       func_175804_a(worldIn, p_74875_3_, 7, 3, 0, 11, 4, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  574 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 7, 18, 4, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  575 */       func_175804_a(worldIn, p_74875_3_, 8, 5, 0, 10, 7, 18, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  576 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 8, 18, 7, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  577 */       func_175804_a(worldIn, p_74875_3_, 7, 5, 0, 7, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  578 */       func_175804_a(worldIn, p_74875_3_, 7, 5, 11, 7, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  579 */       func_175804_a(worldIn, p_74875_3_, 11, 5, 0, 11, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  580 */       func_175804_a(worldIn, p_74875_3_, 11, 5, 11, 11, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  581 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 7, 7, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  582 */       func_175804_a(worldIn, p_74875_3_, 11, 5, 7, 18, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  583 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 11, 7, 5, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  584 */       func_175804_a(worldIn, p_74875_3_, 11, 5, 11, 18, 5, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  585 */       func_175804_a(worldIn, p_74875_3_, 7, 2, 0, 11, 2, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  586 */       func_175804_a(worldIn, p_74875_3_, 7, 2, 13, 11, 2, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  587 */       func_175804_a(worldIn, p_74875_3_, 7, 0, 0, 11, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  588 */       func_175804_a(worldIn, p_74875_3_, 7, 0, 15, 11, 1, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*      */       int var4;
/*      */       
/*  592 */       for (var4 = 7; var4 <= 11; var4++) {
/*      */         
/*  594 */         for (int var5 = 0; var5 <= 2; var5++) {
/*      */           
/*  596 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*  597 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, 18 - var5, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  601 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 7, 5, 2, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  602 */       func_175804_a(worldIn, p_74875_3_, 13, 2, 7, 18, 2, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  603 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 7, 3, 1, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  604 */       func_175804_a(worldIn, p_74875_3_, 15, 0, 7, 18, 1, 11, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*  606 */       for (var4 = 0; var4 <= 2; var4++) {
/*      */         
/*  608 */         for (int var5 = 7; var5 <= 11; var5++) {
/*      */           
/*  610 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*  611 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), 18 - var4, -1, var5, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  615 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class End
/*      */     extends Piece
/*      */   {
/*      */     private int fillSeed;
/*      */     private static final String __OBFID = "CL_00000455";
/*      */     
/*      */     public End() {}
/*      */     
/*      */     public End(int p_i45621_1_, Random p_i45621_2_, StructureBoundingBox p_i45621_3_, EnumFacing p_i45621_4_) {
/*  628 */       super(p_i45621_1_);
/*  629 */       this.coordBaseMode = p_i45621_4_;
/*  630 */       this.boundingBox = p_i45621_3_;
/*  631 */       this.fillSeed = p_i45621_2_.nextInt();
/*      */     }
/*      */ 
/*      */     
/*      */     public static End func_175884_a(List p_175884_0_, Random p_175884_1_, int p_175884_2_, int p_175884_3_, int p_175884_4_, EnumFacing p_175884_5_, int p_175884_6_) {
/*  636 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175884_2_, p_175884_3_, p_175884_4_, -1, -3, 0, 5, 10, 8, p_175884_5_);
/*  637 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175884_0_, var7) == null) ? new End(p_175884_6_, p_175884_1_, var7, p_175884_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  642 */       super.readStructureFromNBT(p_143011_1_);
/*  643 */       this.fillSeed = p_143011_1_.getInteger("Seed");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  648 */       super.writeStructureToNBT(p_143012_1_);
/*  649 */       p_143012_1_.setInteger("Seed", this.fillSeed);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  654 */       Random var4 = new Random(this.fillSeed);
/*      */ 
/*      */       
/*      */       int var5;
/*      */       
/*  659 */       for (var5 = 0; var5 <= 4; var5++) {
/*      */         
/*  661 */         for (int var6 = 3; var6 <= 4; var6++) {
/*      */           
/*  663 */           int var7 = var4.nextInt(8);
/*  664 */           func_175804_a(worldIn, p_74875_3_, var5, var6, 0, var5, var6, var7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */         } 
/*      */       } 
/*      */       
/*  668 */       var5 = var4.nextInt(8);
/*  669 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 0, 5, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  670 */       var5 = var4.nextInt(8);
/*  671 */       func_175804_a(worldIn, p_74875_3_, 4, 5, 0, 4, 5, var5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*  673 */       for (var5 = 0; var5 <= 4; var5++) {
/*      */         
/*  675 */         int var6 = var4.nextInt(5);
/*  676 */         func_175804_a(worldIn, p_74875_3_, var5, 2, 0, var5, 2, var6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       } 
/*      */       
/*  679 */       for (var5 = 0; var5 <= 4; var5++) {
/*      */         
/*  681 */         for (int var6 = 0; var6 <= 1; var6++) {
/*      */           
/*  683 */           int var7 = var4.nextInt(3);
/*  684 */           func_175804_a(worldIn, p_74875_3_, var5, var6, 0, var5, var6, var7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */         } 
/*      */       } 
/*      */       
/*  688 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Entrance
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000459";
/*      */     
/*      */     public Entrance() {}
/*      */     
/*      */     public Entrance(int p_i45617_1_, Random p_i45617_2_, StructureBoundingBox p_i45617_3_, EnumFacing p_i45617_4_) {
/*  700 */       super(p_i45617_1_);
/*  701 */       this.coordBaseMode = p_i45617_4_;
/*  702 */       this.boundingBox = p_i45617_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  707 */       getNextComponentNormal((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 5, 3, true);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Entrance func_175881_a(List p_175881_0_, Random p_175881_1_, int p_175881_2_, int p_175881_3_, int p_175881_4_, EnumFacing p_175881_5_, int p_175881_6_) {
/*  712 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175881_2_, p_175881_3_, p_175881_4_, -5, -3, 0, 13, 14, 13, p_175881_5_);
/*  713 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175881_0_, var7) == null) ? new Entrance(p_175881_6_, p_175881_1_, var7, p_175881_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  718 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 12, 4, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  719 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 12, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  720 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 1, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  721 */       func_175804_a(worldIn, p_74875_3_, 11, 5, 0, 12, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  722 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 11, 4, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  723 */       func_175804_a(worldIn, p_74875_3_, 8, 5, 11, 10, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  724 */       func_175804_a(worldIn, p_74875_3_, 5, 9, 11, 7, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  725 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 0, 4, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  726 */       func_175804_a(worldIn, p_74875_3_, 8, 5, 0, 10, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  727 */       func_175804_a(worldIn, p_74875_3_, 5, 9, 0, 7, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  728 */       func_175804_a(worldIn, p_74875_3_, 2, 11, 2, 10, 12, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  729 */       func_175804_a(worldIn, p_74875_3_, 5, 8, 0, 7, 8, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*      */       
/*      */       int var4;
/*  732 */       for (var4 = 1; var4 <= 11; var4 += 2) {
/*      */         
/*  734 */         func_175804_a(worldIn, p_74875_3_, var4, 10, 0, var4, 11, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  735 */         func_175804_a(worldIn, p_74875_3_, var4, 10, 12, var4, 11, 12, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  736 */         func_175804_a(worldIn, p_74875_3_, 0, 10, var4, 0, 11, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  737 */         func_175804_a(worldIn, p_74875_3_, 12, 10, var4, 12, 11, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  738 */         func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), var4, 13, 0, p_74875_3_);
/*  739 */         func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), var4, 13, 12, p_74875_3_);
/*  740 */         func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), 0, 13, var4, p_74875_3_);
/*  741 */         func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), 12, 13, var4, p_74875_3_);
/*  742 */         func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), var4 + 1, 13, 0, p_74875_3_);
/*  743 */         func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), var4 + 1, 13, 12, p_74875_3_);
/*  744 */         func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 0, 13, var4 + 1, p_74875_3_);
/*  745 */         func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 12, 13, var4 + 1, p_74875_3_);
/*      */       } 
/*      */       
/*  748 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, p_74875_3_);
/*  749 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 12, p_74875_3_);
/*  750 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, p_74875_3_);
/*  751 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 12, 13, 0, p_74875_3_);
/*      */       
/*  753 */       for (var4 = 3; var4 <= 9; var4 += 2) {
/*      */         
/*  755 */         func_175804_a(worldIn, p_74875_3_, 1, 7, var4, 1, 8, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  756 */         func_175804_a(worldIn, p_74875_3_, 11, 7, var4, 11, 8, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*      */       } 
/*      */       
/*  759 */       func_175804_a(worldIn, p_74875_3_, 4, 2, 0, 8, 2, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  760 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 4, 12, 2, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  761 */       func_175804_a(worldIn, p_74875_3_, 4, 0, 0, 8, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  762 */       func_175804_a(worldIn, p_74875_3_, 4, 0, 9, 8, 1, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  763 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 4, 3, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  764 */       func_175804_a(worldIn, p_74875_3_, 9, 0, 4, 12, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */ 
/*      */       
/*  767 */       for (var4 = 4; var4 <= 8; var4++) {
/*      */         
/*  769 */         for (int var5 = 0; var5 <= 2; var5++) {
/*      */           
/*  771 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*  772 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, 12 - var5, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  776 */       for (var4 = 0; var4 <= 2; var4++) {
/*      */         
/*  778 */         for (int var5 = 4; var5 <= 8; var5++) {
/*      */           
/*  780 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*  781 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), 12 - var4, -1, var5, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  785 */       func_175804_a(worldIn, p_74875_3_, 5, 5, 5, 7, 5, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  786 */       func_175804_a(worldIn, p_74875_3_, 6, 1, 6, 6, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  787 */       func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), 6, 0, 6, p_74875_3_);
/*  788 */       func_175811_a(worldIn, Blocks.flowing_lava.getDefaultState(), 6, 5, 6, p_74875_3_);
/*  789 */       BlockPos var6 = new BlockPos(getXWithOffset(6, 6), getYWithOffset(5), getZWithOffset(6, 6));
/*      */       
/*  791 */       if (p_74875_3_.func_175898_b((Vec3i)var6))
/*      */       {
/*  793 */         worldIn.func_175637_a((Block)Blocks.flowing_lava, var6, p_74875_2_);
/*      */       }
/*      */       
/*  796 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class NetherStalkRoom
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000464";
/*      */     
/*      */     public NetherStalkRoom() {}
/*      */     
/*      */     public NetherStalkRoom(int p_i45612_1_, Random p_i45612_2_, StructureBoundingBox p_i45612_3_, EnumFacing p_i45612_4_) {
/*  808 */       super(p_i45612_1_);
/*  809 */       this.coordBaseMode = p_i45612_4_;
/*  810 */       this.boundingBox = p_i45612_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  815 */       getNextComponentNormal((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 5, 3, true);
/*  816 */       getNextComponentNormal((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 5, 11, true);
/*      */     }
/*      */ 
/*      */     
/*      */     public static NetherStalkRoom func_175875_a(List p_175875_0_, Random p_175875_1_, int p_175875_2_, int p_175875_3_, int p_175875_4_, EnumFacing p_175875_5_, int p_175875_6_) {
/*  821 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175875_2_, p_175875_3_, p_175875_4_, -5, -3, 0, 13, 14, 13, p_175875_5_);
/*  822 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175875_0_, var7) == null) ? new NetherStalkRoom(p_175875_6_, p_175875_1_, var7, p_175875_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  827 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 12, 4, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  828 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 12, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  829 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 1, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  830 */       func_175804_a(worldIn, p_74875_3_, 11, 5, 0, 12, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  831 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 11, 4, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  832 */       func_175804_a(worldIn, p_74875_3_, 8, 5, 11, 10, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  833 */       func_175804_a(worldIn, p_74875_3_, 5, 9, 11, 7, 12, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  834 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 0, 4, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  835 */       func_175804_a(worldIn, p_74875_3_, 8, 5, 0, 10, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  836 */       func_175804_a(worldIn, p_74875_3_, 5, 9, 0, 7, 12, 1, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  837 */       func_175804_a(worldIn, p_74875_3_, 2, 11, 2, 10, 12, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*      */       int var4;
/*  840 */       for (var4 = 1; var4 <= 11; var4 += 2) {
/*      */         
/*  842 */         func_175804_a(worldIn, p_74875_3_, var4, 10, 0, var4, 11, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  843 */         func_175804_a(worldIn, p_74875_3_, var4, 10, 12, var4, 11, 12, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  844 */         func_175804_a(worldIn, p_74875_3_, 0, 10, var4, 0, 11, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  845 */         func_175804_a(worldIn, p_74875_3_, 12, 10, var4, 12, 11, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  846 */         func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), var4, 13, 0, p_74875_3_);
/*  847 */         func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), var4, 13, 12, p_74875_3_);
/*  848 */         func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), 0, 13, var4, p_74875_3_);
/*  849 */         func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), 12, 13, var4, p_74875_3_);
/*  850 */         func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), var4 + 1, 13, 0, p_74875_3_);
/*  851 */         func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), var4 + 1, 13, 12, p_74875_3_);
/*  852 */         func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 0, 13, var4 + 1, p_74875_3_);
/*  853 */         func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 12, 13, var4 + 1, p_74875_3_);
/*      */       } 
/*      */       
/*  856 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, p_74875_3_);
/*  857 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 12, p_74875_3_);
/*  858 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 0, 13, 0, p_74875_3_);
/*  859 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 12, 13, 0, p_74875_3_);
/*      */       
/*  861 */       for (var4 = 3; var4 <= 9; var4 += 2) {
/*      */         
/*  863 */         func_175804_a(worldIn, p_74875_3_, 1, 7, var4, 1, 8, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  864 */         func_175804_a(worldIn, p_74875_3_, 11, 7, var4, 11, 8, var4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*      */       } 
/*      */       
/*  867 */       var4 = getMetadataWithOffset(Blocks.nether_brick_stairs, 3);
/*      */ 
/*      */       
/*      */       int var5;
/*      */       
/*  872 */       for (var5 = 0; var5 <= 6; var5++) {
/*      */         
/*  874 */         int i = var5 + 4;
/*      */         
/*  876 */         for (int j = 5; j <= 7; j++)
/*      */         {
/*  878 */           func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var4), j, 5 + var5, i, p_74875_3_);
/*      */         }
/*      */         
/*  881 */         if (i >= 5 && i <= 8) {
/*      */           
/*  883 */           func_175804_a(worldIn, p_74875_3_, 5, 5, i, 7, var5 + 4, i, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */         }
/*  885 */         else if (i >= 9 && i <= 10) {
/*      */           
/*  887 */           func_175804_a(worldIn, p_74875_3_, 5, 8, i, 7, var5 + 4, i, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */         } 
/*      */         
/*  890 */         if (var5 >= 1)
/*      */         {
/*  892 */           func_175804_a(worldIn, p_74875_3_, 5, 6 + var5, i, 7, 9 + var5, i, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */         }
/*      */       } 
/*      */       
/*  896 */       for (var5 = 5; var5 <= 7; var5++)
/*      */       {
/*  898 */         func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var4), var5, 12, 11, p_74875_3_);
/*      */       }
/*      */       
/*  901 */       func_175804_a(worldIn, p_74875_3_, 5, 6, 7, 5, 7, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  902 */       func_175804_a(worldIn, p_74875_3_, 7, 6, 7, 7, 7, 7, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*  903 */       func_175804_a(worldIn, p_74875_3_, 5, 13, 12, 7, 13, 12, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  904 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 2, 3, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  905 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 9, 3, 5, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  906 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 4, 2, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  907 */       func_175804_a(worldIn, p_74875_3_, 9, 5, 2, 10, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  908 */       func_175804_a(worldIn, p_74875_3_, 9, 5, 9, 10, 5, 10, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  909 */       func_175804_a(worldIn, p_74875_3_, 10, 5, 4, 10, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  910 */       var5 = getMetadataWithOffset(Blocks.nether_brick_stairs, 0);
/*  911 */       int var6 = getMetadataWithOffset(Blocks.nether_brick_stairs, 1);
/*  912 */       func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var6), 4, 5, 2, p_74875_3_);
/*  913 */       func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var6), 4, 5, 3, p_74875_3_);
/*  914 */       func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var6), 4, 5, 9, p_74875_3_);
/*  915 */       func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var6), 4, 5, 10, p_74875_3_);
/*  916 */       func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var5), 8, 5, 2, p_74875_3_);
/*  917 */       func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var5), 8, 5, 3, p_74875_3_);
/*  918 */       func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var5), 8, 5, 9, p_74875_3_);
/*  919 */       func_175811_a(worldIn, Blocks.nether_brick_stairs.getStateFromMeta(var5), 8, 5, 10, p_74875_3_);
/*  920 */       func_175804_a(worldIn, p_74875_3_, 3, 4, 4, 4, 4, 8, Blocks.soul_sand.getDefaultState(), Blocks.soul_sand.getDefaultState(), false);
/*  921 */       func_175804_a(worldIn, p_74875_3_, 8, 4, 4, 9, 4, 8, Blocks.soul_sand.getDefaultState(), Blocks.soul_sand.getDefaultState(), false);
/*  922 */       func_175804_a(worldIn, p_74875_3_, 3, 5, 4, 4, 5, 8, Blocks.nether_wart.getDefaultState(), Blocks.nether_wart.getDefaultState(), false);
/*  923 */       func_175804_a(worldIn, p_74875_3_, 8, 5, 4, 9, 5, 8, Blocks.nether_wart.getDefaultState(), Blocks.nether_wart.getDefaultState(), false);
/*  924 */       func_175804_a(worldIn, p_74875_3_, 4, 2, 0, 8, 2, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  925 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 4, 12, 2, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  926 */       func_175804_a(worldIn, p_74875_3_, 4, 0, 0, 8, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  927 */       func_175804_a(worldIn, p_74875_3_, 4, 0, 9, 8, 1, 12, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  928 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 4, 3, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*  929 */       func_175804_a(worldIn, p_74875_3_, 9, 0, 4, 12, 1, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/*      */       int var7;
/*  932 */       for (var7 = 4; var7 <= 8; var7++) {
/*      */         
/*  934 */         for (int var8 = 0; var8 <= 2; var8++) {
/*      */           
/*  936 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var7, -1, var8, p_74875_3_);
/*  937 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var7, -1, 12 - var8, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  941 */       for (var7 = 0; var7 <= 2; var7++) {
/*      */         
/*  943 */         for (int var8 = 4; var8 <= 8; var8++) {
/*      */           
/*  945 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var7, -1, var8, p_74875_3_);
/*  946 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), 12 - var7, -1, var8, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  950 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   static abstract class Piece
/*      */     extends StructureComponent {
/*  956 */     protected static final List field_111019_a = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 5), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 5), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 15), new WeightedRandomChestContent(Items.golden_sword, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.golden_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent(Items.flint_and_steel, 0, 1, 1, 5), new WeightedRandomChestContent(Items.nether_wart, 0, 3, 7, 5), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 8), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 3), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 2, 4, 2) });
/*      */     
/*      */     private static final String __OBFID = "CL_00000466";
/*      */     
/*      */     public Piece() {}
/*      */     
/*      */     protected Piece(int p_i2054_1_) {
/*  963 */       super(p_i2054_1_);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {}
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {}
/*      */     
/*      */     private int getTotalWeight(List p_74960_1_) {
/*  972 */       boolean var2 = false;
/*  973 */       int var3 = 0;
/*      */ 
/*      */       
/*  976 */       for (Iterator<StructureNetherBridgePieces.PieceWeight> var4 = p_74960_1_.iterator(); var4.hasNext(); var3 += var5.field_78826_b) {
/*      */         
/*  978 */         StructureNetherBridgePieces.PieceWeight var5 = var4.next();
/*      */         
/*  980 */         if (var5.field_78824_d > 0 && var5.field_78827_c < var5.field_78824_d)
/*      */         {
/*  982 */           var2 = true;
/*      */         }
/*      */       } 
/*      */       
/*  986 */       return var2 ? var3 : -1;
/*      */     }
/*      */ 
/*      */     
/*      */     private Piece func_175871_a(StructureNetherBridgePieces.Start p_175871_1_, List p_175871_2_, List p_175871_3_, Random p_175871_4_, int p_175871_5_, int p_175871_6_, int p_175871_7_, EnumFacing p_175871_8_, int p_175871_9_) {
/*  991 */       int var10 = getTotalWeight(p_175871_2_);
/*  992 */       boolean var11 = (var10 > 0 && p_175871_9_ <= 30);
/*  993 */       int var12 = 0;
/*      */       
/*  995 */       while (var12 < 5 && var11) {
/*      */         
/*  997 */         var12++;
/*  998 */         int var13 = p_175871_4_.nextInt(var10);
/*  999 */         Iterator<StructureNetherBridgePieces.PieceWeight> var14 = p_175871_2_.iterator();
/*      */         
/* 1001 */         while (var14.hasNext()) {
/*      */           
/* 1003 */           StructureNetherBridgePieces.PieceWeight var15 = var14.next();
/* 1004 */           var13 -= var15.field_78826_b;
/*      */           
/* 1006 */           if (var13 < 0) {
/*      */             
/* 1008 */             if (!var15.func_78822_a(p_175871_9_) || (var15 == p_175871_1_.theNetherBridgePieceWeight && !var15.field_78825_e)) {
/*      */               break;
/*      */             }
/*      */ 
/*      */             
/* 1013 */             Piece var16 = StructureNetherBridgePieces.func_175887_b(var15, p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);
/*      */             
/* 1015 */             if (var16 != null) {
/*      */               
/* 1017 */               var15.field_78827_c++;
/* 1018 */               p_175871_1_.theNetherBridgePieceWeight = var15;
/*      */               
/* 1020 */               if (!var15.func_78823_a())
/*      */               {
/* 1022 */                 p_175871_2_.remove(var15);
/*      */               }
/*      */               
/* 1025 */               return var16;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1031 */       return StructureNetherBridgePieces.End.func_175884_a(p_175871_3_, p_175871_4_, p_175871_5_, p_175871_6_, p_175871_7_, p_175871_8_, p_175871_9_);
/*      */     }
/*      */ 
/*      */     
/*      */     private StructureComponent func_175870_a(StructureNetherBridgePieces.Start p_175870_1_, List<Piece> p_175870_2_, Random p_175870_3_, int p_175870_4_, int p_175870_5_, int p_175870_6_, EnumFacing p_175870_7_, int p_175870_8_, boolean p_175870_9_) {
/* 1036 */       if (Math.abs(p_175870_4_ - (p_175870_1_.getBoundingBox()).minX) <= 112 && Math.abs(p_175870_6_ - (p_175870_1_.getBoundingBox()).minZ) <= 112) {
/*      */         
/* 1038 */         List var10 = p_175870_1_.primaryWeights;
/*      */         
/* 1040 */         if (p_175870_9_)
/*      */         {
/* 1042 */           var10 = p_175870_1_.secondaryWeights;
/*      */         }
/*      */         
/* 1045 */         Piece var11 = func_175871_a(p_175870_1_, var10, p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_ + 1);
/*      */         
/* 1047 */         if (var11 != null) {
/*      */           
/* 1049 */           p_175870_2_.add(var11);
/* 1050 */           p_175870_1_.field_74967_d.add(var11);
/*      */         } 
/*      */         
/* 1053 */         return var11;
/*      */       } 
/*      */ 
/*      */       
/* 1057 */       return StructureNetherBridgePieces.End.func_175884_a(p_175870_2_, p_175870_3_, p_175870_4_, p_175870_5_, p_175870_6_, p_175870_7_, p_175870_8_);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected StructureComponent getNextComponentNormal(StructureNetherBridgePieces.Start p_74963_1_, List p_74963_2_, Random p_74963_3_, int p_74963_4_, int p_74963_5_, boolean p_74963_6_) {
/* 1063 */       if (this.coordBaseMode != null)
/*      */       {
/* 1065 */         switch (StructureNetherBridgePieces.SwitchEnumFacing.field_175888_a[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1068 */             return func_175870_a(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX + p_74963_4_, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ - 1, this.coordBaseMode, getComponentType(), p_74963_6_);
/*      */           
/*      */           case 2:
/* 1071 */             return func_175870_a(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX + p_74963_4_, this.boundingBox.minY + p_74963_5_, this.boundingBox.maxZ + 1, this.coordBaseMode, getComponentType(), p_74963_6_);
/*      */           
/*      */           case 3:
/* 1074 */             return func_175870_a(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ + p_74963_4_, this.coordBaseMode, getComponentType(), p_74963_6_);
/*      */           
/*      */           case 4:
/* 1077 */             return func_175870_a(p_74963_1_, p_74963_2_, p_74963_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74963_5_, this.boundingBox.minZ + p_74963_4_, this.coordBaseMode, getComponentType(), p_74963_6_);
/*      */         } 
/*      */       
/*      */       }
/* 1081 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected StructureComponent getNextComponentX(StructureNetherBridgePieces.Start p_74961_1_, List p_74961_2_, Random p_74961_3_, int p_74961_4_, int p_74961_5_, boolean p_74961_6_) {
/* 1086 */       if (this.coordBaseMode != null)
/*      */       {
/* 1088 */         switch (StructureNetherBridgePieces.SwitchEnumFacing.field_175888_a[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1091 */             return func_175870_a(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ + p_74961_5_, EnumFacing.WEST, getComponentType(), p_74961_6_);
/*      */           
/*      */           case 2:
/* 1094 */             return func_175870_a(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ + p_74961_5_, EnumFacing.WEST, getComponentType(), p_74961_6_);
/*      */           
/*      */           case 3:
/* 1097 */             return func_175870_a(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX + p_74961_5_, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, getComponentType(), p_74961_6_);
/*      */           
/*      */           case 4:
/* 1100 */             return func_175870_a(p_74961_1_, p_74961_2_, p_74961_3_, this.boundingBox.minX + p_74961_5_, this.boundingBox.minY + p_74961_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, getComponentType(), p_74961_6_);
/*      */         } 
/*      */       
/*      */       }
/* 1104 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected StructureComponent getNextComponentZ(StructureNetherBridgePieces.Start p_74965_1_, List p_74965_2_, Random p_74965_3_, int p_74965_4_, int p_74965_5_, boolean p_74965_6_) {
/* 1109 */       if (this.coordBaseMode != null)
/*      */       {
/* 1111 */         switch (StructureNetherBridgePieces.SwitchEnumFacing.field_175888_a[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1114 */             return func_175870_a(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74965_4_, this.boundingBox.minZ + p_74965_5_, EnumFacing.EAST, getComponentType(), p_74965_6_);
/*      */           
/*      */           case 2:
/* 1117 */             return func_175870_a(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74965_4_, this.boundingBox.minZ + p_74965_5_, EnumFacing.EAST, getComponentType(), p_74965_6_);
/*      */           
/*      */           case 3:
/* 1120 */             return func_175870_a(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.minX + p_74965_5_, this.boundingBox.minY + p_74965_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, getComponentType(), p_74965_6_);
/*      */           
/*      */           case 4:
/* 1123 */             return func_175870_a(p_74965_1_, p_74965_2_, p_74965_3_, this.boundingBox.minX + p_74965_5_, this.boundingBox.minY + p_74965_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, getComponentType(), p_74965_6_);
/*      */         } 
/*      */       
/*      */       }
/* 1127 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected static boolean isAboveGround(StructureBoundingBox p_74964_0_) {
/* 1132 */       return (p_74964_0_ != null && p_74964_0_.minY > 10);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class PieceWeight
/*      */   {
/*      */     public Class weightClass;
/*      */     public final int field_78826_b;
/*      */     public int field_78827_c;
/*      */     public int field_78824_d;
/*      */     public boolean field_78825_e;
/*      */     private static final String __OBFID = "CL_00000467";
/*      */     
/*      */     public PieceWeight(Class p_i2055_1_, int p_i2055_2_, int p_i2055_3_, boolean p_i2055_4_) {
/* 1147 */       this.weightClass = p_i2055_1_;
/* 1148 */       this.field_78826_b = p_i2055_2_;
/* 1149 */       this.field_78824_d = p_i2055_3_;
/* 1150 */       this.field_78825_e = p_i2055_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     public PieceWeight(Class p_i2056_1_, int p_i2056_2_, int p_i2056_3_) {
/* 1155 */       this(p_i2056_1_, p_i2056_2_, p_i2056_3_, false);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean func_78822_a(int p_78822_1_) {
/* 1160 */       return !(this.field_78824_d != 0 && this.field_78827_c >= this.field_78824_d);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean func_78823_a() {
/* 1165 */       return !(this.field_78824_d != 0 && this.field_78827_c >= this.field_78824_d);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Stairs
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000469";
/*      */     
/*      */     public Stairs() {}
/*      */     
/*      */     public Stairs(int p_i45609_1_, Random p_i45609_2_, StructureBoundingBox p_i45609_3_, EnumFacing p_i45609_4_) {
/* 1177 */       super(p_i45609_1_);
/* 1178 */       this.coordBaseMode = p_i45609_4_;
/* 1179 */       this.boundingBox = p_i45609_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 1184 */       getNextComponentZ((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 6, 2, false);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Stairs func_175872_a(List p_175872_0_, Random p_175872_1_, int p_175872_2_, int p_175872_3_, int p_175872_4_, int p_175872_5_, EnumFacing p_175872_6_) {
/* 1189 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175872_2_, p_175872_3_, p_175872_4_, -2, 0, 0, 7, 11, 7, p_175872_6_);
/* 1190 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175872_0_, var7) == null) ? new Stairs(p_175872_5_, p_175872_1_, var7, p_175872_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1195 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 6, 1, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1196 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 6, 10, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 1197 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 1, 8, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1198 */       func_175804_a(worldIn, p_74875_3_, 5, 2, 0, 6, 8, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1199 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 1, 0, 8, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1200 */       func_175804_a(worldIn, p_74875_3_, 6, 2, 1, 6, 8, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1201 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 6, 5, 8, 6, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1202 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 2, 0, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1203 */       func_175804_a(worldIn, p_74875_3_, 6, 3, 2, 6, 5, 2, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1204 */       func_175804_a(worldIn, p_74875_3_, 6, 3, 4, 6, 5, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1205 */       func_175811_a(worldIn, Blocks.nether_brick.getDefaultState(), 5, 2, 5, p_74875_3_);
/* 1206 */       func_175804_a(worldIn, p_74875_3_, 4, 2, 5, 4, 3, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1207 */       func_175804_a(worldIn, p_74875_3_, 3, 2, 5, 3, 4, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1208 */       func_175804_a(worldIn, p_74875_3_, 2, 2, 5, 2, 5, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1209 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 5, 1, 6, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1210 */       func_175804_a(worldIn, p_74875_3_, 1, 7, 1, 5, 7, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1211 */       func_175804_a(worldIn, p_74875_3_, 6, 8, 2, 6, 8, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 1212 */       func_175804_a(worldIn, p_74875_3_, 2, 6, 0, 4, 8, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1213 */       func_175804_a(worldIn, p_74875_3_, 2, 5, 0, 4, 5, 0, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*      */       
/* 1215 */       for (int var4 = 0; var4 <= 6; var4++) {
/*      */         
/* 1217 */         for (int var5 = 0; var5 <= 6; var5++)
/*      */         {
/* 1219 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/* 1223 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Start
/*      */     extends Crossing3 {
/*      */     public StructureNetherBridgePieces.PieceWeight theNetherBridgePieceWeight;
/*      */     public List primaryWeights;
/*      */     public List secondaryWeights;
/* 1232 */     public List field_74967_d = Lists.newArrayList();
/*      */     
/*      */     private static final String __OBFID = "CL_00000470";
/*      */     
/*      */     public Start() {}
/*      */     
/*      */     public Start(Random p_i2059_1_, int p_i2059_2_, int p_i2059_3_) {
/* 1239 */       super(p_i2059_1_, p_i2059_2_, p_i2059_3_);
/* 1240 */       this.primaryWeights = Lists.newArrayList();
/* 1241 */       StructureNetherBridgePieces.PieceWeight[] var4 = StructureNetherBridgePieces.primaryComponents;
/* 1242 */       int var5 = var4.length;
/*      */       
/*      */       int var6;
/*      */       
/* 1246 */       for (var6 = 0; var6 < var5; var6++) {
/*      */         
/* 1248 */         StructureNetherBridgePieces.PieceWeight var7 = var4[var6];
/* 1249 */         var7.field_78827_c = 0;
/* 1250 */         this.primaryWeights.add(var7);
/*      */       } 
/*      */       
/* 1253 */       this.secondaryWeights = Lists.newArrayList();
/* 1254 */       var4 = StructureNetherBridgePieces.secondaryComponents;
/* 1255 */       var5 = var4.length;
/*      */       
/* 1257 */       for (var6 = 0; var6 < var5; var6++) {
/*      */         
/* 1259 */         StructureNetherBridgePieces.PieceWeight var7 = var4[var6];
/* 1260 */         var7.field_78827_c = 0;
/* 1261 */         this.secondaryWeights.add(var7);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1267 */       super.readStructureFromNBT(p_143011_1_);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1272 */       super.writeStructureToNBT(p_143012_1_);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Straight
/*      */     extends Piece
/*      */   {
/*      */     private static final String __OBFID = "CL_00000456";
/*      */     
/*      */     public Straight() {}
/*      */     
/*      */     public Straight(int p_i45620_1_, Random p_i45620_2_, StructureBoundingBox p_i45620_3_, EnumFacing p_i45620_4_) {
/* 1284 */       super(p_i45620_1_);
/* 1285 */       this.coordBaseMode = p_i45620_4_;
/* 1286 */       this.boundingBox = p_i45620_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 1291 */       getNextComponentNormal((StructureNetherBridgePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 1, 3, false);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Straight func_175882_a(List p_175882_0_, Random p_175882_1_, int p_175882_2_, int p_175882_3_, int p_175882_4_, EnumFacing p_175882_5_, int p_175882_6_) {
/* 1296 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175882_2_, p_175882_3_, p_175882_4_, -1, -3, 0, 5, 10, 19, p_175882_5_);
/* 1297 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175882_0_, var7) == null) ? new Straight(p_175882_6_, p_175882_1_, var7, p_175882_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1302 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 0, 4, 4, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1303 */       func_175804_a(worldIn, p_74875_3_, 1, 5, 0, 3, 7, 18, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 1304 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 0, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1305 */       func_175804_a(worldIn, p_74875_3_, 4, 5, 0, 4, 5, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1306 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 4, 2, 5, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1307 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 13, 4, 2, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1308 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 4, 1, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1309 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 15, 4, 1, 18, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/*      */       
/* 1311 */       for (int var4 = 0; var4 <= 4; var4++) {
/*      */         
/* 1313 */         for (int var5 = 0; var5 <= 2; var5++) {
/*      */           
/* 1315 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, var5, p_74875_3_);
/* 1316 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var4, -1, 18 - var5, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/* 1320 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1321 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 4, 0, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1322 */       func_175804_a(worldIn, p_74875_3_, 0, 3, 14, 0, 4, 14, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1323 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 17, 0, 4, 17, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1324 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1325 */       func_175804_a(worldIn, p_74875_3_, 4, 3, 4, 4, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1326 */       func_175804_a(worldIn, p_74875_3_, 4, 3, 14, 4, 4, 14, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1327 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 17, 4, 4, 17, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1328 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class SwitchEnumFacing
/*      */   {
/* 1334 */     static final int[] field_175888_a = new int[(EnumFacing.values()).length];
/*      */     
/*      */     private static final String __OBFID = "CL_00001997";
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 1341 */         field_175888_a[EnumFacing.NORTH.ordinal()] = 1;
/*      */       }
/* 1343 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1350 */         field_175888_a[EnumFacing.SOUTH.ordinal()] = 2;
/*      */       }
/* 1352 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1359 */         field_175888_a[EnumFacing.WEST.ordinal()] = 3;
/*      */       }
/* 1361 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1368 */         field_175888_a[EnumFacing.EAST.ordinal()] = 4;
/*      */       }
/* 1370 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class Throne
/*      */     extends Piece
/*      */   {
/*      */     private boolean hasSpawner;
/*      */     
/*      */     private static final String __OBFID = "CL_00000465";
/*      */ 
/*      */     
/*      */     public Throne() {}
/*      */     
/*      */     public Throne(int p_i45611_1_, Random p_i45611_2_, StructureBoundingBox p_i45611_3_, EnumFacing p_i45611_4_) {
/* 1386 */       super(p_i45611_1_);
/* 1387 */       this.coordBaseMode = p_i45611_4_;
/* 1388 */       this.boundingBox = p_i45611_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1393 */       super.readStructureFromNBT(p_143011_1_);
/* 1394 */       this.hasSpawner = p_143011_1_.getBoolean("Mob");
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1399 */       super.writeStructureToNBT(p_143012_1_);
/* 1400 */       p_143012_1_.setBoolean("Mob", this.hasSpawner);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Throne func_175874_a(List p_175874_0_, Random p_175874_1_, int p_175874_2_, int p_175874_3_, int p_175874_4_, int p_175874_5_, EnumFacing p_175874_6_) {
/* 1405 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175874_2_, p_175874_3_, p_175874_4_, -2, 0, 0, 7, 8, 9, p_175874_6_);
/* 1406 */       return (isAboveGround(var7) && StructureComponent.findIntersecting(p_175874_0_, var7) == null) ? new Throne(p_175874_5_, p_175874_1_, var7, p_175874_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1411 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 6, 7, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 1412 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 5, 1, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1413 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 1, 5, 2, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1414 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 2, 5, 3, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1415 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 3, 5, 4, 7, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1416 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 1, 4, 2, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1417 */       func_175804_a(worldIn, p_74875_3_, 5, 2, 0, 5, 4, 2, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1418 */       func_175804_a(worldIn, p_74875_3_, 1, 5, 2, 1, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1419 */       func_175804_a(worldIn, p_74875_3_, 5, 5, 2, 5, 5, 3, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1420 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 3, 0, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1421 */       func_175804_a(worldIn, p_74875_3_, 6, 5, 3, 6, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1422 */       func_175804_a(worldIn, p_74875_3_, 1, 5, 8, 5, 5, 8, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
/* 1423 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 1, 6, 3, p_74875_3_);
/* 1424 */       func_175811_a(worldIn, Blocks.nether_brick_fence.getDefaultState(), 5, 6, 3, p_74875_3_);
/* 1425 */       func_175804_a(worldIn, p_74875_3_, 0, 6, 3, 0, 6, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1426 */       func_175804_a(worldIn, p_74875_3_, 6, 6, 3, 6, 6, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1427 */       func_175804_a(worldIn, p_74875_3_, 1, 6, 8, 5, 7, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/* 1428 */       func_175804_a(worldIn, p_74875_3_, 2, 8, 8, 4, 8, 8, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
/*      */       
/* 1430 */       if (!this.hasSpawner) {
/*      */         
/* 1432 */         BlockPos var4 = new BlockPos(getXWithOffset(3, 5), getYWithOffset(5), getZWithOffset(3, 5));
/*      */         
/* 1434 */         if (p_74875_3_.func_175898_b((Vec3i)var4)) {
/*      */           
/* 1436 */           this.hasSpawner = true;
/* 1437 */           worldIn.setBlockState(var4, Blocks.mob_spawner.getDefaultState(), 2);
/* 1438 */           TileEntity var5 = worldIn.getTileEntity(var4);
/*      */           
/* 1440 */           if (var5 instanceof TileEntityMobSpawner)
/*      */           {
/* 1442 */             ((TileEntityMobSpawner)var5).getSpawnerBaseLogic().setEntityName("Blaze");
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1447 */       for (int var6 = 0; var6 <= 6; var6++) {
/*      */         
/* 1449 */         for (int var7 = 0; var7 <= 6; var7++)
/*      */         {
/* 1451 */           func_175808_b(worldIn, Blocks.nether_brick.getDefaultState(), var6, -1, var7, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/* 1455 */       return true;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureNetherBridgePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */