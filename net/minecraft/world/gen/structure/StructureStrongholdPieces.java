/*      */ package net.minecraft.world.gen.structure;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.BlockEndPortalFrame;
/*      */ import net.minecraft.block.BlockStoneBrick;
/*      */ import net.minecraft.block.BlockStoneSlab;
/*      */ import net.minecraft.block.properties.IProperty;
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
/*      */ public class StructureStrongholdPieces {
/*   23 */   private static final PieceWeight[] pieceWeightArray = new PieceWeight[] { new PieceWeight(Straight.class, 40, 0), new PieceWeight(Prison.class, 5, 5), new PieceWeight(LeftTurn.class, 20, 0), new PieceWeight(RightTurn.class, 20, 0), new PieceWeight(RoomCrossing.class, 10, 6), new PieceWeight(StairsStraight.class, 5, 5), new PieceWeight(Stairs.class, 5, 5), new PieceWeight(Crossing.class, 5, 4), new PieceWeight(ChestCorridor.class, 5, 4), new PieceWeight(Library.class, 10, 2)
/*      */       {
/*      */         private static final String __OBFID = "CL_00000484";
/*      */         
/*      */         public boolean canSpawnMoreStructuresOfType(int p_75189_1_) {
/*   28 */           return (super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 4); }
/*      */       }, 
/*   30 */       new PieceWeight(PortalRoom.class, 20, 1)
/*      */       {
/*      */         private static final String __OBFID = "CL_00000485";
/*      */         
/*      */         public boolean canSpawnMoreStructuresOfType(int p_75189_1_) {
/*   35 */           return (super.canSpawnMoreStructuresOfType(p_75189_1_) && p_75189_1_ > 5);
/*      */         }
/*      */       } };
/*      */   
/*      */   private static List structurePieceList;
/*      */   private static Class strongComponentType;
/*      */   static int totalWeight;
/*   42 */   private static final Stones strongholdStones = new Stones(null);
/*      */   
/*      */   private static final String __OBFID = "CL_00000483";
/*      */   
/*      */   public static void registerStrongholdPieces() {
/*   47 */     MapGenStructureIO.registerStructureComponent(ChestCorridor.class, "SHCC");
/*   48 */     MapGenStructureIO.registerStructureComponent(Corridor.class, "SHFC");
/*   49 */     MapGenStructureIO.registerStructureComponent(Crossing.class, "SH5C");
/*   50 */     MapGenStructureIO.registerStructureComponent(LeftTurn.class, "SHLT");
/*   51 */     MapGenStructureIO.registerStructureComponent(Library.class, "SHLi");
/*   52 */     MapGenStructureIO.registerStructureComponent(PortalRoom.class, "SHPR");
/*   53 */     MapGenStructureIO.registerStructureComponent(Prison.class, "SHPH");
/*   54 */     MapGenStructureIO.registerStructureComponent(RightTurn.class, "SHRT");
/*   55 */     MapGenStructureIO.registerStructureComponent(RoomCrossing.class, "SHRC");
/*   56 */     MapGenStructureIO.registerStructureComponent(Stairs.class, "SHSD");
/*   57 */     MapGenStructureIO.registerStructureComponent(Stairs2.class, "SHStart");
/*   58 */     MapGenStructureIO.registerStructureComponent(Straight.class, "SHS");
/*   59 */     MapGenStructureIO.registerStructureComponent(StairsStraight.class, "SHSSD");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void prepareStructurePieces() {
/*   67 */     structurePieceList = Lists.newArrayList();
/*   68 */     PieceWeight[] var0 = pieceWeightArray;
/*   69 */     int var1 = var0.length;
/*      */     
/*   71 */     for (int var2 = 0; var2 < var1; var2++) {
/*      */       
/*   73 */       PieceWeight var3 = var0[var2];
/*   74 */       var3.instancesSpawned = 0;
/*   75 */       structurePieceList.add(var3);
/*      */     } 
/*      */     
/*   78 */     strongComponentType = null;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean canAddStructurePieces() {
/*   83 */     boolean var0 = false;
/*   84 */     totalWeight = 0;
/*      */ 
/*      */     
/*   87 */     for (Iterator<PieceWeight> var1 = structurePieceList.iterator(); var1.hasNext(); totalWeight += var2.pieceWeight) {
/*      */       
/*   89 */       PieceWeight var2 = var1.next();
/*      */       
/*   91 */       if (var2.instancesLimit > 0 && var2.instancesSpawned < var2.instancesLimit)
/*      */       {
/*   93 */         var0 = true;
/*      */       }
/*      */     } 
/*      */     
/*   97 */     return var0;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Stronghold func_175954_a(Class<Straight> p_175954_0_, List p_175954_1_, Random p_175954_2_, int p_175954_3_, int p_175954_4_, int p_175954_5_, EnumFacing p_175954_6_, int p_175954_7_) {
/*  102 */     Object var8 = null;
/*      */     
/*  104 */     if (p_175954_0_ == Straight.class) {
/*      */       
/*  106 */       var8 = Straight.func_175862_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  108 */     else if (p_175954_0_ == Prison.class) {
/*      */       
/*  110 */       var8 = Prison.func_175860_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  112 */     else if (p_175954_0_ == LeftTurn.class) {
/*      */       
/*  114 */       var8 = LeftTurn.func_175867_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  116 */     else if (p_175954_0_ == RightTurn.class) {
/*      */       
/*  118 */       var8 = RightTurn.func_175867_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  120 */     else if (p_175954_0_ == RoomCrossing.class) {
/*      */       
/*  122 */       var8 = RoomCrossing.func_175859_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  124 */     else if (p_175954_0_ == StairsStraight.class) {
/*      */       
/*  126 */       var8 = StairsStraight.func_175861_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  128 */     else if (p_175954_0_ == Stairs.class) {
/*      */       
/*  130 */       var8 = Stairs.func_175863_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  132 */     else if (p_175954_0_ == Crossing.class) {
/*      */       
/*  134 */       var8 = Crossing.func_175866_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  136 */     else if (p_175954_0_ == ChestCorridor.class) {
/*      */       
/*  138 */       var8 = ChestCorridor.func_175868_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  140 */     else if (p_175954_0_ == Library.class) {
/*      */       
/*  142 */       var8 = Library.func_175864_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     }
/*  144 */     else if (p_175954_0_ == PortalRoom.class) {
/*      */       
/*  146 */       var8 = PortalRoom.func_175865_a(p_175954_1_, p_175954_2_, p_175954_3_, p_175954_4_, p_175954_5_, p_175954_6_, p_175954_7_);
/*      */     } 
/*      */     
/*  149 */     return (Stronghold)var8;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Stronghold func_175955_b(Stairs2 p_175955_0_, List p_175955_1_, Random p_175955_2_, int p_175955_3_, int p_175955_4_, int p_175955_5_, EnumFacing p_175955_6_, int p_175955_7_) {
/*  154 */     if (!canAddStructurePieces())
/*      */     {
/*  156 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  160 */     if (strongComponentType != null) {
/*      */       
/*  162 */       Stronghold var8 = func_175954_a(strongComponentType, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
/*  163 */       strongComponentType = null;
/*      */       
/*  165 */       if (var8 != null)
/*      */       {
/*  167 */         return var8;
/*      */       }
/*      */     } 
/*      */     
/*  171 */     int var13 = 0;
/*      */     
/*  173 */     while (var13 < 5) {
/*      */       
/*  175 */       var13++;
/*  176 */       int var9 = p_175955_2_.nextInt(totalWeight);
/*  177 */       Iterator<PieceWeight> var10 = structurePieceList.iterator();
/*      */       
/*  179 */       while (var10.hasNext()) {
/*      */         
/*  181 */         PieceWeight var11 = var10.next();
/*  182 */         var9 -= var11.pieceWeight;
/*      */         
/*  184 */         if (var9 < 0) {
/*      */           
/*  186 */           if (!var11.canSpawnMoreStructuresOfType(p_175955_7_) || var11 == p_175955_0_.strongholdPieceWeight) {
/*      */             break;
/*      */           }
/*      */ 
/*      */           
/*  191 */           Stronghold var12 = func_175954_a(var11.pieceClass, p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_, p_175955_7_);
/*      */           
/*  193 */           if (var12 != null) {
/*      */             
/*  195 */             var11.instancesSpawned++;
/*  196 */             p_175955_0_.strongholdPieceWeight = var11;
/*      */             
/*  198 */             if (!var11.canSpawnMoreStructures())
/*      */             {
/*  200 */               structurePieceList.remove(var11);
/*      */             }
/*      */             
/*  203 */             return var12;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  209 */     StructureBoundingBox var14 = Corridor.func_175869_a(p_175955_1_, p_175955_2_, p_175955_3_, p_175955_4_, p_175955_5_, p_175955_6_);
/*      */     
/*  211 */     if (var14 != null && var14.minY > 1)
/*      */     {
/*  213 */       return new Corridor(p_175955_7_, p_175955_2_, var14, p_175955_6_);
/*      */     }
/*      */ 
/*      */     
/*  217 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static StructureComponent func_175953_c(Stairs2 p_175953_0_, List<Stronghold> p_175953_1_, Random p_175953_2_, int p_175953_3_, int p_175953_4_, int p_175953_5_, EnumFacing p_175953_6_, int p_175953_7_) {
/*  224 */     if (p_175953_7_ > 50)
/*      */     {
/*  226 */       return null;
/*      */     }
/*  228 */     if (Math.abs(p_175953_3_ - (p_175953_0_.getBoundingBox()).minX) <= 112 && Math.abs(p_175953_5_ - (p_175953_0_.getBoundingBox()).minZ) <= 112) {
/*      */       
/*  230 */       Stronghold var8 = func_175955_b(p_175953_0_, p_175953_1_, p_175953_2_, p_175953_3_, p_175953_4_, p_175953_5_, p_175953_6_, p_175953_7_ + 1);
/*      */       
/*  232 */       if (var8 != null) {
/*      */         
/*  234 */         p_175953_1_.add(var8);
/*  235 */         p_175953_0_.field_75026_c.add(var8);
/*      */       } 
/*      */       
/*  238 */       return var8;
/*      */     } 
/*      */ 
/*      */     
/*  242 */     return null;
/*      */   }
/*      */   
/*      */   public static class ChestCorridor
/*      */     extends Stronghold
/*      */   {
/*  248 */     private static final List strongholdChestContents = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.ender_pearl, 0, 1, 1, 10), new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.iron_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.iron_helmet, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.iron_leggings, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.iron_boots, 0, 1, 1, 5), new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 1), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1) });
/*      */     
/*      */     private boolean hasMadeChest;
/*      */     private static final String __OBFID = "CL_00000487";
/*      */     
/*      */     public ChestCorridor() {}
/*      */     
/*      */     public ChestCorridor(int p_i45582_1_, Random p_i45582_2_, StructureBoundingBox p_i45582_3_, EnumFacing p_i45582_4_) {
/*  256 */       super(p_i45582_1_);
/*  257 */       this.coordBaseMode = p_i45582_4_;
/*  258 */       this.field_143013_d = getRandomDoor(p_i45582_2_);
/*  259 */       this.boundingBox = p_i45582_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  264 */       super.writeStructureToNBT(p_143012_1_);
/*  265 */       p_143012_1_.setBoolean("Chest", this.hasMadeChest);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  270 */       super.readStructureFromNBT(p_143011_1_);
/*  271 */       this.hasMadeChest = p_143011_1_.getBoolean("Chest");
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  276 */       getNextComponentNormal((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public static ChestCorridor func_175868_a(List p_175868_0_, Random p_175868_1_, int p_175868_2_, int p_175868_3_, int p_175868_4_, EnumFacing p_175868_5_, int p_175868_6_) {
/*  281 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175868_2_, p_175868_3_, p_175868_4_, -1, -1, 0, 5, 5, 7, p_175868_5_);
/*  282 */       return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175868_0_, var7) == null) ? new ChestCorridor(p_175868_6_, p_175868_1_, var7, p_175868_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  287 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/*  289 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  293 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 4, 6, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  294 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
/*  295 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
/*  296 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 2, 3, 1, 4, Blocks.stonebrick.getDefaultState(), Blocks.stonebrick.getDefaultState(), false);
/*  297 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 3, 1, 1, p_74875_3_);
/*  298 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 3, 1, 5, p_74875_3_);
/*  299 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 3, 2, 2, p_74875_3_);
/*  300 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 3, 2, 4, p_74875_3_);
/*      */       
/*  302 */       for (int var4 = 2; var4 <= 4; var4++)
/*      */       {
/*  304 */         func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.SMOOTHBRICK.func_176624_a()), 2, 1, var4, p_74875_3_);
/*      */       }
/*      */       
/*  307 */       if (!this.hasMadeChest && p_74875_3_.func_175898_b((Vec3i)new BlockPos(getXWithOffset(3, 3), getYWithOffset(2), getZWithOffset(3, 3)))) {
/*      */         
/*  309 */         this.hasMadeChest = true;
/*  310 */         func_180778_a(worldIn, p_74875_3_, p_74875_2_, 3, 2, 3, WeightedRandomChestContent.func_177629_a(strongholdChestContents, new WeightedRandomChestContent[] { Items.enchanted_book.getRandomEnchantedBook(p_74875_2_) }), 2 + p_74875_2_.nextInt(2));
/*      */       } 
/*      */       
/*  313 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class Corridor
/*      */     extends Stronghold
/*      */   {
/*      */     private int field_74993_a;
/*      */     private static final String __OBFID = "CL_00000488";
/*      */     
/*      */     public Corridor() {}
/*      */     
/*      */     public Corridor(int p_i45581_1_, Random p_i45581_2_, StructureBoundingBox p_i45581_3_, EnumFacing p_i45581_4_) {
/*  327 */       super(p_i45581_1_);
/*  328 */       this.coordBaseMode = p_i45581_4_;
/*  329 */       this.boundingBox = p_i45581_3_;
/*  330 */       this.field_74993_a = (p_i45581_4_ != EnumFacing.NORTH && p_i45581_4_ != EnumFacing.SOUTH) ? p_i45581_3_.getXSize() : p_i45581_3_.getZSize();
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  335 */       super.writeStructureToNBT(p_143012_1_);
/*  336 */       p_143012_1_.setInteger("Steps", this.field_74993_a);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  341 */       super.readStructureFromNBT(p_143011_1_);
/*  342 */       this.field_74993_a = p_143011_1_.getInteger("Steps");
/*      */     }
/*      */ 
/*      */     
/*      */     public static StructureBoundingBox func_175869_a(List p_175869_0_, Random p_175869_1_, int p_175869_2_, int p_175869_3_, int p_175869_4_, EnumFacing p_175869_5_) {
/*  347 */       boolean var6 = true;
/*  348 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, 4, p_175869_5_);
/*  349 */       StructureComponent var8 = StructureComponent.findIntersecting(p_175869_0_, var7);
/*      */       
/*  351 */       if (var8 == null)
/*      */       {
/*  353 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  357 */       if ((var8.getBoundingBox()).minY == var7.minY)
/*      */       {
/*  359 */         for (int var9 = 3; var9 >= 1; var9--) {
/*      */           
/*  361 */           var7 = StructureBoundingBox.func_175897_a(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, var9 - 1, p_175869_5_);
/*      */           
/*  363 */           if (!var8.getBoundingBox().intersectsWith(var7))
/*      */           {
/*  365 */             return StructureBoundingBox.func_175897_a(p_175869_2_, p_175869_3_, p_175869_4_, -1, -1, 0, 5, 5, var9, p_175869_5_);
/*      */           }
/*      */         } 
/*      */       }
/*      */       
/*  370 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  376 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/*  378 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  382 */       for (int var4 = 0; var4 < this.field_74993_a; var4++) {
/*      */         
/*  384 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 0, 0, var4, p_74875_3_);
/*  385 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 0, var4, p_74875_3_);
/*  386 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 0, var4, p_74875_3_);
/*  387 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 0, var4, p_74875_3_);
/*  388 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 4, 0, var4, p_74875_3_);
/*      */         
/*  390 */         for (int var5 = 1; var5 <= 3; var5++) {
/*      */           
/*  392 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 0, var5, var4, p_74875_3_);
/*  393 */           func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, var5, var4, p_74875_3_);
/*  394 */           func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, var5, var4, p_74875_3_);
/*  395 */           func_175811_a(worldIn, Blocks.air.getDefaultState(), 3, var5, var4, p_74875_3_);
/*  396 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 4, var5, var4, p_74875_3_);
/*      */         } 
/*      */         
/*  399 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 0, 4, var4, p_74875_3_);
/*  400 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 4, var4, p_74875_3_);
/*  401 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 4, var4, p_74875_3_);
/*  402 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 4, var4, p_74875_3_);
/*  403 */         func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 4, 4, var4, p_74875_3_);
/*      */       } 
/*      */       
/*  406 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class Crossing
/*      */     extends Stronghold
/*      */   {
/*      */     private boolean field_74996_b;
/*      */     private boolean field_74997_c;
/*      */     private boolean field_74995_d;
/*      */     private boolean field_74999_h;
/*      */     private static final String __OBFID = "CL_00000489";
/*      */     
/*      */     public Crossing() {}
/*      */     
/*      */     public Crossing(int p_i45580_1_, Random p_i45580_2_, StructureBoundingBox p_i45580_3_, EnumFacing p_i45580_4_) {
/*  423 */       super(p_i45580_1_);
/*  424 */       this.coordBaseMode = p_i45580_4_;
/*  425 */       this.field_143013_d = getRandomDoor(p_i45580_2_);
/*  426 */       this.boundingBox = p_i45580_3_;
/*  427 */       this.field_74996_b = p_i45580_2_.nextBoolean();
/*  428 */       this.field_74997_c = p_i45580_2_.nextBoolean();
/*  429 */       this.field_74995_d = p_i45580_2_.nextBoolean();
/*  430 */       this.field_74999_h = (p_i45580_2_.nextInt(3) > 0);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  435 */       super.writeStructureToNBT(p_143012_1_);
/*  436 */       p_143012_1_.setBoolean("leftLow", this.field_74996_b);
/*  437 */       p_143012_1_.setBoolean("leftHigh", this.field_74997_c);
/*  438 */       p_143012_1_.setBoolean("rightLow", this.field_74995_d);
/*  439 */       p_143012_1_.setBoolean("rightHigh", this.field_74999_h);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  444 */       super.readStructureFromNBT(p_143011_1_);
/*  445 */       this.field_74996_b = p_143011_1_.getBoolean("leftLow");
/*  446 */       this.field_74997_c = p_143011_1_.getBoolean("leftHigh");
/*  447 */       this.field_74995_d = p_143011_1_.getBoolean("rightLow");
/*  448 */       this.field_74999_h = p_143011_1_.getBoolean("rightHigh");
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  453 */       int var4 = 3;
/*  454 */       int var5 = 5;
/*      */       
/*  456 */       if (this.coordBaseMode == EnumFacing.WEST || this.coordBaseMode == EnumFacing.NORTH) {
/*      */         
/*  458 */         var4 = 8 - var4;
/*  459 */         var5 = 8 - var5;
/*      */       } 
/*      */       
/*  462 */       getNextComponentNormal((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 5, 1);
/*      */       
/*  464 */       if (this.field_74996_b)
/*      */       {
/*  466 */         getNextComponentX((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, var4, 1);
/*      */       }
/*      */       
/*  469 */       if (this.field_74997_c)
/*      */       {
/*  471 */         getNextComponentX((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, var5, 7);
/*      */       }
/*      */       
/*  474 */       if (this.field_74995_d)
/*      */       {
/*  476 */         getNextComponentZ((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, var4, 1);
/*      */       }
/*      */       
/*  479 */       if (this.field_74999_h)
/*      */       {
/*  481 */         getNextComponentZ((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, var5, 7);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public static Crossing func_175866_a(List p_175866_0_, Random p_175866_1_, int p_175866_2_, int p_175866_3_, int p_175866_4_, EnumFacing p_175866_5_, int p_175866_6_) {
/*  487 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175866_2_, p_175866_3_, p_175866_4_, -4, -3, 0, 10, 9, 11, p_175866_5_);
/*  488 */       return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175866_0_, var7) == null) ? new Crossing(p_175866_6_, p_175866_1_, var7, p_175866_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  493 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/*  495 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  499 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 9, 8, 10, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  500 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 4, 3, 0);
/*      */       
/*  502 */       if (this.field_74996_b)
/*      */       {
/*  504 */         func_175804_a(worldIn, p_74875_3_, 0, 3, 1, 0, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       }
/*      */       
/*  507 */       if (this.field_74995_d)
/*      */       {
/*  509 */         func_175804_a(worldIn, p_74875_3_, 9, 3, 1, 9, 5, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       }
/*      */       
/*  512 */       if (this.field_74997_c)
/*      */       {
/*  514 */         func_175804_a(worldIn, p_74875_3_, 0, 5, 7, 0, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       }
/*      */       
/*  517 */       if (this.field_74999_h)
/*      */       {
/*  519 */         func_175804_a(worldIn, p_74875_3_, 9, 5, 7, 9, 7, 9, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       }
/*      */       
/*  522 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 10, 7, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  523 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 2, 1, 8, 2, 6, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  524 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 5, 4, 4, 9, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  525 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 8, 1, 5, 8, 4, 9, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  526 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 4, 7, 3, 4, 9, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  527 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 3, 5, 3, 3, 6, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  528 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 4, 3, 3, 4, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
/*  529 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 6, 3, 4, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
/*  530 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 5, 1, 7, 7, 1, 8, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  531 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 9, 7, 1, 9, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
/*  532 */       func_175804_a(worldIn, p_74875_3_, 5, 2, 7, 7, 2, 7, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
/*  533 */       func_175804_a(worldIn, p_74875_3_, 4, 5, 7, 4, 5, 9, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
/*  534 */       func_175804_a(worldIn, p_74875_3_, 8, 5, 7, 8, 5, 9, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
/*  535 */       func_175804_a(worldIn, p_74875_3_, 5, 5, 7, 7, 5, 9, Blocks.double_stone_slab.getDefaultState(), Blocks.double_stone_slab.getDefaultState(), false);
/*  536 */       func_175811_a(worldIn, Blocks.torch.getDefaultState(), 6, 5, 6, p_74875_3_);
/*  537 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class LeftTurn
/*      */     extends Stronghold
/*      */   {
/*      */     private static final String __OBFID = "CL_00000490";
/*      */     
/*      */     public LeftTurn() {}
/*      */     
/*      */     public LeftTurn(int p_i45579_1_, Random p_i45579_2_, StructureBoundingBox p_i45579_3_, EnumFacing p_i45579_4_) {
/*  550 */       super(p_i45579_1_);
/*  551 */       this.coordBaseMode = p_i45579_4_;
/*  552 */       this.field_143013_d = getRandomDoor(p_i45579_2_);
/*  553 */       this.boundingBox = p_i45579_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  558 */       if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
/*      */         
/*  560 */         getNextComponentZ((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
/*      */       }
/*      */       else {
/*      */         
/*  564 */         getNextComponentX((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public static LeftTurn func_175867_a(List p_175867_0_, Random p_175867_1_, int p_175867_2_, int p_175867_3_, int p_175867_4_, EnumFacing p_175867_5_, int p_175867_6_) {
/*  570 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175867_2_, p_175867_3_, p_175867_4_, -1, -1, 0, 5, 5, 5, p_175867_5_);
/*  571 */       return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175867_0_, var7) == null) ? new LeftTurn(p_175867_6_, p_175867_1_, var7, p_175867_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  576 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/*  578 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  582 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 4, 4, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  583 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
/*      */       
/*  585 */       if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
/*      */         
/*  587 */         func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       }
/*      */       else {
/*      */         
/*  591 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       } 
/*      */       
/*  594 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Library
/*      */     extends Stronghold
/*      */   {
/*  601 */     private static final List strongholdLibraryChestContents = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.book, 0, 1, 3, 20), new WeightedRandomChestContent(Items.paper, 0, 2, 7, 20), new WeightedRandomChestContent((Item)Items.map, 0, 1, 1, 1), new WeightedRandomChestContent(Items.compass, 0, 1, 1, 1) });
/*      */     
/*      */     private boolean isLargeRoom;
/*      */     private static final String __OBFID = "CL_00000491";
/*      */     
/*      */     public Library() {}
/*      */     
/*      */     public Library(int p_i45578_1_, Random p_i45578_2_, StructureBoundingBox p_i45578_3_, EnumFacing p_i45578_4_) {
/*  609 */       super(p_i45578_1_);
/*  610 */       this.coordBaseMode = p_i45578_4_;
/*  611 */       this.field_143013_d = getRandomDoor(p_i45578_2_);
/*  612 */       this.boundingBox = p_i45578_3_;
/*  613 */       this.isLargeRoom = (p_i45578_3_.getYSize() > 6);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  618 */       super.writeStructureToNBT(p_143012_1_);
/*  619 */       p_143012_1_.setBoolean("Tall", this.isLargeRoom);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  624 */       super.readStructureFromNBT(p_143011_1_);
/*  625 */       this.isLargeRoom = p_143011_1_.getBoolean("Tall");
/*      */     }
/*      */ 
/*      */     
/*      */     public static Library func_175864_a(List p_175864_0_, Random p_175864_1_, int p_175864_2_, int p_175864_3_, int p_175864_4_, EnumFacing p_175864_5_, int p_175864_6_) {
/*  630 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 11, 15, p_175864_5_);
/*      */       
/*  632 */       if (!canStrongholdGoDeeper(var7) || StructureComponent.findIntersecting(p_175864_0_, var7) != null) {
/*      */         
/*  634 */         var7 = StructureBoundingBox.func_175897_a(p_175864_2_, p_175864_3_, p_175864_4_, -4, -1, 0, 14, 6, 15, p_175864_5_);
/*      */         
/*  636 */         if (!canStrongholdGoDeeper(var7) || StructureComponent.findIntersecting(p_175864_0_, var7) != null)
/*      */         {
/*  638 */           return null;
/*      */         }
/*      */       } 
/*      */       
/*  642 */       return new Library(p_175864_6_, p_175864_1_, var7, p_175864_5_);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  647 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/*  649 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  653 */       byte var4 = 11;
/*      */       
/*  655 */       if (!this.isLargeRoom)
/*      */       {
/*  657 */         var4 = 6;
/*      */       }
/*      */       
/*  660 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 13, var4 - 1, 14, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  661 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 4, 1, 0);
/*  662 */       func_175805_a(worldIn, p_74875_3_, p_74875_2_, 0.07F, 2, 1, 1, 11, 4, 13, Blocks.web.getDefaultState(), Blocks.web.getDefaultState(), false);
/*  663 */       boolean var5 = true;
/*  664 */       boolean var6 = true;
/*      */       
/*      */       int var7;
/*  667 */       for (var7 = 1; var7 <= 13; var7++) {
/*      */         
/*  669 */         if ((var7 - 1) % 4 == 0) {
/*      */           
/*  671 */           func_175804_a(worldIn, p_74875_3_, 1, 1, var7, 1, 4, var7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  672 */           func_175804_a(worldIn, p_74875_3_, 12, 1, var7, 12, 4, var7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  673 */           func_175811_a(worldIn, Blocks.torch.getDefaultState(), 2, 3, var7, p_74875_3_);
/*  674 */           func_175811_a(worldIn, Blocks.torch.getDefaultState(), 11, 3, var7, p_74875_3_);
/*      */           
/*  676 */           if (this.isLargeRoom)
/*      */           {
/*  678 */             func_175804_a(worldIn, p_74875_3_, 1, 6, var7, 1, 9, var7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  679 */             func_175804_a(worldIn, p_74875_3_, 12, 6, var7, 12, 9, var7, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  684 */           func_175804_a(worldIn, p_74875_3_, 1, 1, var7, 1, 4, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
/*  685 */           func_175804_a(worldIn, p_74875_3_, 12, 1, var7, 12, 4, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
/*      */           
/*  687 */           if (this.isLargeRoom) {
/*      */             
/*  689 */             func_175804_a(worldIn, p_74875_3_, 1, 6, var7, 1, 9, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
/*  690 */             func_175804_a(worldIn, p_74875_3_, 12, 6, var7, 12, 9, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  695 */       for (var7 = 3; var7 < 12; var7 += 2) {
/*      */         
/*  697 */         func_175804_a(worldIn, p_74875_3_, 3, 1, var7, 4, 3, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
/*  698 */         func_175804_a(worldIn, p_74875_3_, 6, 1, var7, 7, 3, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
/*  699 */         func_175804_a(worldIn, p_74875_3_, 9, 1, var7, 10, 3, var7, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
/*      */       } 
/*      */       
/*  702 */       if (this.isLargeRoom) {
/*      */         
/*  704 */         func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 3, 5, 13, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  705 */         func_175804_a(worldIn, p_74875_3_, 10, 5, 1, 12, 5, 13, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  706 */         func_175804_a(worldIn, p_74875_3_, 4, 5, 1, 9, 5, 2, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  707 */         func_175804_a(worldIn, p_74875_3_, 4, 5, 12, 9, 5, 13, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  708 */         func_175811_a(worldIn, Blocks.planks.getDefaultState(), 9, 5, 11, p_74875_3_);
/*  709 */         func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 5, 11, p_74875_3_);
/*  710 */         func_175811_a(worldIn, Blocks.planks.getDefaultState(), 9, 5, 10, p_74875_3_);
/*  711 */         func_175804_a(worldIn, p_74875_3_, 3, 6, 2, 3, 6, 12, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
/*  712 */         func_175804_a(worldIn, p_74875_3_, 10, 6, 2, 10, 6, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
/*  713 */         func_175804_a(worldIn, p_74875_3_, 4, 6, 2, 9, 6, 2, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
/*  714 */         func_175804_a(worldIn, p_74875_3_, 4, 6, 12, 8, 6, 12, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
/*  715 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 9, 6, 11, p_74875_3_);
/*  716 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 8, 6, 11, p_74875_3_);
/*  717 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 9, 6, 10, p_74875_3_);
/*  718 */         var7 = getMetadataWithOffset(Blocks.ladder, 3);
/*  719 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 1, 13, p_74875_3_);
/*  720 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 2, 13, p_74875_3_);
/*  721 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 3, 13, p_74875_3_);
/*  722 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 4, 13, p_74875_3_);
/*  723 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 5, 13, p_74875_3_);
/*  724 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 6, 13, p_74875_3_);
/*  725 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var7), 10, 7, 13, p_74875_3_);
/*  726 */         byte var8 = 7;
/*  727 */         byte var9 = 7;
/*  728 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 9, var9, p_74875_3_);
/*  729 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 9, var9, p_74875_3_);
/*  730 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 8, var9, p_74875_3_);
/*  731 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 8, var9, p_74875_3_);
/*  732 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 7, var9, p_74875_3_);
/*  733 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 7, var9, p_74875_3_);
/*  734 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 2, 7, var9, p_74875_3_);
/*  735 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 + 1, 7, var9, p_74875_3_);
/*  736 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 7, var9 - 1, p_74875_3_);
/*  737 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8 - 1, 7, var9 + 1, p_74875_3_);
/*  738 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 7, var9 - 1, p_74875_3_);
/*  739 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), var8, 7, var9 + 1, p_74875_3_);
/*  740 */         func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8 - 2, 8, var9, p_74875_3_);
/*  741 */         func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8 + 1, 8, var9, p_74875_3_);
/*  742 */         func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8 - 1, 8, var9 - 1, p_74875_3_);
/*  743 */         func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8 - 1, 8, var9 + 1, p_74875_3_);
/*  744 */         func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8, 8, var9 - 1, p_74875_3_);
/*  745 */         func_175811_a(worldIn, Blocks.torch.getDefaultState(), var8, 8, var9 + 1, p_74875_3_);
/*      */       } 
/*      */       
/*  748 */       func_180778_a(worldIn, p_74875_3_, p_74875_2_, 3, 3, 5, WeightedRandomChestContent.func_177629_a(strongholdLibraryChestContents, new WeightedRandomChestContent[] { Items.enchanted_book.func_92112_a(p_74875_2_, 1, 5, 2) }), 1 + p_74875_2_.nextInt(4));
/*      */       
/*  750 */       if (this.isLargeRoom) {
/*      */         
/*  752 */         func_175811_a(worldIn, Blocks.air.getDefaultState(), 12, 9, 1, p_74875_3_);
/*  753 */         func_180778_a(worldIn, p_74875_3_, p_74875_2_, 12, 8, 1, WeightedRandomChestContent.func_177629_a(strongholdLibraryChestContents, new WeightedRandomChestContent[] { Items.enchanted_book.func_92112_a(p_74875_2_, 1, 5, 2) }), 1 + p_74875_2_.nextInt(4));
/*      */       } 
/*      */       
/*  756 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class PieceWeight
/*      */   {
/*      */     public Class pieceClass;
/*      */     
/*      */     public final int pieceWeight;
/*      */     public int instancesSpawned;
/*      */     public int instancesLimit;
/*      */     private static final String __OBFID = "CL_00000492";
/*      */     
/*      */     public PieceWeight(Class p_i2076_1_, int p_i2076_2_, int p_i2076_3_) {
/*  771 */       this.pieceClass = p_i2076_1_;
/*  772 */       this.pieceWeight = p_i2076_2_;
/*  773 */       this.instancesLimit = p_i2076_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canSpawnMoreStructuresOfType(int p_75189_1_) {
/*  778 */       return !(this.instancesLimit != 0 && this.instancesSpawned >= this.instancesLimit);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canSpawnMoreStructures() {
/*  783 */       return !(this.instancesLimit != 0 && this.instancesSpawned >= this.instancesLimit);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class PortalRoom
/*      */     extends Stronghold
/*      */   {
/*      */     private boolean hasSpawner;
/*      */     private static final String __OBFID = "CL_00000493";
/*      */     
/*      */     public PortalRoom() {}
/*      */     
/*      */     public PortalRoom(int p_i45577_1_, Random p_i45577_2_, StructureBoundingBox p_i45577_3_, EnumFacing p_i45577_4_) {
/*  796 */       super(p_i45577_1_);
/*  797 */       this.coordBaseMode = p_i45577_4_;
/*  798 */       this.boundingBox = p_i45577_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  803 */       super.writeStructureToNBT(p_143012_1_);
/*  804 */       p_143012_1_.setBoolean("Mob", this.hasSpawner);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  809 */       super.readStructureFromNBT(p_143011_1_);
/*  810 */       this.hasSpawner = p_143011_1_.getBoolean("Mob");
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  815 */       if (p_74861_1_ != null)
/*      */       {
/*  817 */         ((StructureStrongholdPieces.Stairs2)p_74861_1_).strongholdPortalRoom = this;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public static PortalRoom func_175865_a(List p_175865_0_, Random p_175865_1_, int p_175865_2_, int p_175865_3_, int p_175865_4_, EnumFacing p_175865_5_, int p_175865_6_) {
/*  823 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175865_2_, p_175865_3_, p_175865_4_, -4, -1, 0, 11, 8, 16, p_175865_5_);
/*  824 */       return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175865_0_, var7) == null) ? new PortalRoom(p_175865_6_, p_175865_1_, var7, p_175865_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  829 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 10, 7, 15, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  830 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.GRATES, 4, 1, 0);
/*  831 */       byte var4 = 6;
/*  832 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, var4, 1, 1, var4, 14, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  833 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 9, var4, 1, 9, var4, 14, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  834 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, var4, 1, 8, var4, 2, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  835 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 2, var4, 14, 8, var4, 14, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  836 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 1, 1, 1, 2, 1, 4, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  837 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 8, 1, 1, 9, 1, 4, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  838 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 1, 1, 3, Blocks.flowing_lava.getDefaultState(), Blocks.flowing_lava.getDefaultState(), false);
/*  839 */       func_175804_a(worldIn, p_74875_3_, 9, 1, 1, 9, 1, 3, Blocks.flowing_lava.getDefaultState(), Blocks.flowing_lava.getDefaultState(), false);
/*  840 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 3, 1, 8, 7, 1, 12, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  841 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 9, 6, 1, 11, Blocks.flowing_lava.getDefaultState(), Blocks.flowing_lava.getDefaultState(), false);
/*      */       
/*      */       int var5;
/*  844 */       for (var5 = 3; var5 < 14; var5 += 2) {
/*      */         
/*  846 */         func_175804_a(worldIn, p_74875_3_, 0, 3, var5, 0, 4, var5, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
/*  847 */         func_175804_a(worldIn, p_74875_3_, 10, 3, var5, 10, 4, var5, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
/*      */       } 
/*      */       
/*  850 */       for (var5 = 2; var5 < 9; var5 += 2)
/*      */       {
/*  852 */         func_175804_a(worldIn, p_74875_3_, var5, 3, 15, var5, 4, 15, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
/*      */       }
/*      */       
/*  855 */       var5 = getMetadataWithOffset(Blocks.stone_brick_stairs, 3);
/*  856 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 5, 6, 1, 7, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  857 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 2, 6, 6, 2, 7, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  858 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 3, 7, 6, 3, 7, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*      */       
/*      */       int var6;
/*  861 */       for (var6 = 4; var6 <= 6; var6++) {
/*      */         
/*  863 */         func_175811_a(worldIn, Blocks.stone_brick_stairs.getStateFromMeta(var5), var6, 1, 4, p_74875_3_);
/*  864 */         func_175811_a(worldIn, Blocks.stone_brick_stairs.getStateFromMeta(var5), var6, 2, 5, p_74875_3_);
/*  865 */         func_175811_a(worldIn, Blocks.stone_brick_stairs.getStateFromMeta(var5), var6, 3, 6, p_74875_3_);
/*      */       } 
/*      */       
/*  868 */       var6 = EnumFacing.NORTH.getHorizontalIndex();
/*  869 */       int var7 = EnumFacing.SOUTH.getHorizontalIndex();
/*  870 */       int var8 = EnumFacing.EAST.getHorizontalIndex();
/*  871 */       int var9 = EnumFacing.WEST.getHorizontalIndex();
/*      */       
/*  873 */       if (this.coordBaseMode != null)
/*      */       {
/*  875 */         switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 2:
/*  878 */             var6 = EnumFacing.SOUTH.getHorizontalIndex();
/*  879 */             var7 = EnumFacing.NORTH.getHorizontalIndex();
/*      */             break;
/*      */           
/*      */           case 3:
/*  883 */             var6 = EnumFacing.WEST.getHorizontalIndex();
/*  884 */             var7 = EnumFacing.EAST.getHorizontalIndex();
/*  885 */             var8 = EnumFacing.SOUTH.getHorizontalIndex();
/*  886 */             var9 = EnumFacing.NORTH.getHorizontalIndex();
/*      */             break;
/*      */           
/*      */           case 4:
/*  890 */             var6 = EnumFacing.EAST.getHorizontalIndex();
/*  891 */             var7 = EnumFacing.WEST.getHorizontalIndex();
/*  892 */             var8 = EnumFacing.SOUTH.getHorizontalIndex();
/*  893 */             var9 = EnumFacing.NORTH.getHorizontalIndex();
/*      */             break;
/*      */         } 
/*      */       }
/*  897 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var6).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 4, 3, 8, p_74875_3_);
/*  898 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var6).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 5, 3, 8, p_74875_3_);
/*  899 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var6).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 6, 3, 8, p_74875_3_);
/*  900 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var7).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 4, 3, 12, p_74875_3_);
/*  901 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var7).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 5, 3, 12, p_74875_3_);
/*  902 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var7).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 6, 3, 12, p_74875_3_);
/*  903 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var8).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 3, 3, 9, p_74875_3_);
/*  904 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var8).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 3, 3, 10, p_74875_3_);
/*  905 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var8).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 3, 3, 11, p_74875_3_);
/*  906 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var9).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 7, 3, 9, p_74875_3_);
/*  907 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var9).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 7, 3, 10, p_74875_3_);
/*  908 */       func_175811_a(worldIn, Blocks.end_portal_frame.getStateFromMeta(var9).withProperty((IProperty)BlockEndPortalFrame.field_176507_b, Boolean.valueOf((p_74875_2_.nextFloat() > 0.9F))), 7, 3, 11, p_74875_3_);
/*      */       
/*  910 */       if (!this.hasSpawner) {
/*      */         
/*  912 */         int var12 = getYWithOffset(3);
/*  913 */         BlockPos var10 = new BlockPos(getXWithOffset(5, 6), var12, getZWithOffset(5, 6));
/*      */         
/*  915 */         if (p_74875_3_.func_175898_b((Vec3i)var10)) {
/*      */           
/*  917 */           this.hasSpawner = true;
/*  918 */           worldIn.setBlockState(var10, Blocks.mob_spawner.getDefaultState(), 2);
/*  919 */           TileEntity var11 = worldIn.getTileEntity(var10);
/*      */           
/*  921 */           if (var11 instanceof TileEntityMobSpawner)
/*      */           {
/*  923 */             ((TileEntityMobSpawner)var11).getSpawnerBaseLogic().setEntityName("Silverfish");
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  928 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Prison
/*      */     extends Stronghold
/*      */   {
/*      */     private static final String __OBFID = "CL_00000494";
/*      */     
/*      */     public Prison() {}
/*      */     
/*      */     public Prison(int p_i45576_1_, Random p_i45576_2_, StructureBoundingBox p_i45576_3_, EnumFacing p_i45576_4_) {
/*  940 */       super(p_i45576_1_);
/*  941 */       this.coordBaseMode = p_i45576_4_;
/*  942 */       this.field_143013_d = getRandomDoor(p_i45576_2_);
/*  943 */       this.boundingBox = p_i45576_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  948 */       getNextComponentNormal((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Prison func_175860_a(List p_175860_0_, Random p_175860_1_, int p_175860_2_, int p_175860_3_, int p_175860_4_, EnumFacing p_175860_5_, int p_175860_6_) {
/*  953 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175860_2_, p_175860_3_, p_175860_4_, -1, -1, 0, 9, 5, 11, p_175860_5_);
/*  954 */       return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175860_0_, var7) == null) ? new Prison(p_175860_6_, p_175860_1_, var7, p_175860_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  959 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/*  961 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  965 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 8, 4, 10, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  966 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
/*  967 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 10, 3, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  968 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 1, 4, 3, 1, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  969 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 3, 4, 3, 3, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  970 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 7, 4, 3, 7, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  971 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 4, 1, 9, 4, 3, 9, false, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/*  972 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 4, 4, 3, 6, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
/*  973 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 5, 7, 3, 5, Blocks.iron_bars.getDefaultState(), Blocks.iron_bars.getDefaultState(), false);
/*  974 */       func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 4, 3, 2, p_74875_3_);
/*  975 */       func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 4, 3, 8, p_74875_3_);
/*  976 */       func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(getMetadataWithOffset(Blocks.iron_door, 3)), 4, 1, 2, p_74875_3_);
/*  977 */       func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(getMetadataWithOffset(Blocks.iron_door, 3) + 8), 4, 2, 2, p_74875_3_);
/*  978 */       func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(getMetadataWithOffset(Blocks.iron_door, 3)), 4, 1, 8, p_74875_3_);
/*  979 */       func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(getMetadataWithOffset(Blocks.iron_door, 3) + 8), 4, 2, 8, p_74875_3_);
/*  980 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class RightTurn
/*      */     extends LeftTurn
/*      */   {
/*      */     private static final String __OBFID = "CL_00000495";
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/*  991 */       if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
/*      */         
/*  993 */         getNextComponentX((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
/*      */       }
/*      */       else {
/*      */         
/*  997 */         getNextComponentZ((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1003 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/* 1005 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1009 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 4, 4, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/* 1010 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
/*      */       
/* 1012 */       if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.EAST) {
/*      */         
/* 1014 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       }
/*      */       else {
/*      */         
/* 1018 */         func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       } 
/*      */       
/* 1021 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class RoomCrossing
/*      */     extends Stronghold
/*      */   {
/* 1028 */     private static final List strongholdRoomCrossingChestContents = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5), new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 1) });
/*      */     
/*      */     protected int roomType;
/*      */     private static final String __OBFID = "CL_00000496";
/*      */     
/*      */     public RoomCrossing() {}
/*      */     
/*      */     public RoomCrossing(int p_i45575_1_, Random p_i45575_2_, StructureBoundingBox p_i45575_3_, EnumFacing p_i45575_4_) {
/* 1036 */       super(p_i45575_1_);
/* 1037 */       this.coordBaseMode = p_i45575_4_;
/* 1038 */       this.field_143013_d = getRandomDoor(p_i45575_2_);
/* 1039 */       this.boundingBox = p_i45575_3_;
/* 1040 */       this.roomType = p_i45575_2_.nextInt(5);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1045 */       super.writeStructureToNBT(p_143012_1_);
/* 1046 */       p_143012_1_.setInteger("Type", this.roomType);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1051 */       super.readStructureFromNBT(p_143011_1_);
/* 1052 */       this.roomType = p_143011_1_.getInteger("Type");
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 1057 */       getNextComponentNormal((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 4, 1);
/* 1058 */       getNextComponentX((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 4);
/* 1059 */       getNextComponentZ((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 4);
/*      */     }
/*      */ 
/*      */     
/*      */     public static RoomCrossing func_175859_a(List p_175859_0_, Random p_175859_1_, int p_175859_2_, int p_175859_3_, int p_175859_4_, EnumFacing p_175859_5_, int p_175859_6_) {
/* 1064 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175859_2_, p_175859_3_, p_175859_4_, -4, -1, 0, 11, 7, 11, p_175859_5_);
/* 1065 */       return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175859_0_, var7) == null) ? new RoomCrossing(p_175859_6_, p_175859_1_, var7, p_175859_5_) : null;
/*      */     }
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*      */       int var4;
/* 1070 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/* 1072 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1076 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 10, 6, 10, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/* 1077 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 4, 1, 0);
/* 1078 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 10, 6, 3, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 1079 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 4, 0, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 1080 */       func_175804_a(worldIn, p_74875_3_, 10, 1, 4, 10, 3, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */ 
/*      */       
/* 1083 */       switch (this.roomType) {
/*      */         
/*      */         case 0:
/* 1086 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 1, 5, p_74875_3_);
/* 1087 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 2, 5, p_74875_3_);
/* 1088 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 3, 5, p_74875_3_);
/* 1089 */           func_175811_a(worldIn, Blocks.torch.getDefaultState(), 4, 3, 5, p_74875_3_);
/* 1090 */           func_175811_a(worldIn, Blocks.torch.getDefaultState(), 6, 3, 5, p_74875_3_);
/* 1091 */           func_175811_a(worldIn, Blocks.torch.getDefaultState(), 5, 3, 4, p_74875_3_);
/* 1092 */           func_175811_a(worldIn, Blocks.torch.getDefaultState(), 5, 3, 6, p_74875_3_);
/* 1093 */           func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 4, 1, 4, p_74875_3_);
/* 1094 */           func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 4, 1, 5, p_74875_3_);
/* 1095 */           func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 4, 1, 6, p_74875_3_);
/* 1096 */           func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 6, 1, 4, p_74875_3_);
/* 1097 */           func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 6, 1, 5, p_74875_3_);
/* 1098 */           func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 6, 1, 6, p_74875_3_);
/* 1099 */           func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 5, 1, 4, p_74875_3_);
/* 1100 */           func_175811_a(worldIn, Blocks.stone_slab.getDefaultState(), 5, 1, 6, p_74875_3_);
/*      */           break;
/*      */         
/*      */         case 1:
/* 1104 */           for (var4 = 0; var4 < 5; var4++) {
/*      */             
/* 1106 */             func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 1, 3 + var4, p_74875_3_);
/* 1107 */             func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 7, 1, 3 + var4, p_74875_3_);
/* 1108 */             func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3 + var4, 1, 3, p_74875_3_);
/* 1109 */             func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3 + var4, 1, 7, p_74875_3_);
/*      */           } 
/*      */           
/* 1112 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 1, 5, p_74875_3_);
/* 1113 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 2, 5, p_74875_3_);
/* 1114 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 5, 3, 5, p_74875_3_);
/* 1115 */           func_175811_a(worldIn, Blocks.flowing_water.getDefaultState(), 5, 4, 5, p_74875_3_);
/*      */           break;
/*      */         
/*      */         case 2:
/* 1119 */           for (var4 = 1; var4 <= 9; var4++) {
/*      */             
/* 1121 */             func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 1, 3, var4, p_74875_3_);
/* 1122 */             func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 9, 3, var4, p_74875_3_);
/*      */           } 
/*      */           
/* 1125 */           for (var4 = 1; var4 <= 9; var4++) {
/*      */             
/* 1127 */             func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), var4, 3, 1, p_74875_3_);
/* 1128 */             func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), var4, 3, 9, p_74875_3_);
/*      */           } 
/*      */           
/* 1131 */           func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 5, 1, 4, p_74875_3_);
/* 1132 */           func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 5, 1, 6, p_74875_3_);
/* 1133 */           func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 5, 3, 4, p_74875_3_);
/* 1134 */           func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 5, 3, 6, p_74875_3_);
/* 1135 */           func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 1, 5, p_74875_3_);
/* 1136 */           func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, 1, 5, p_74875_3_);
/* 1137 */           func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 3, 5, p_74875_3_);
/* 1138 */           func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, 3, 5, p_74875_3_);
/*      */           
/* 1140 */           for (var4 = 1; var4 <= 3; var4++) {
/*      */             
/* 1142 */             func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, var4, 4, p_74875_3_);
/* 1143 */             func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, var4, 4, p_74875_3_);
/* 1144 */             func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, var4, 6, p_74875_3_);
/* 1145 */             func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, var4, 6, p_74875_3_);
/*      */           } 
/*      */           
/* 1148 */           func_175811_a(worldIn, Blocks.torch.getDefaultState(), 5, 3, 5, p_74875_3_);
/*      */           
/* 1150 */           for (var4 = 2; var4 <= 8; var4++) {
/*      */             
/* 1152 */             func_175811_a(worldIn, Blocks.planks.getDefaultState(), 2, 3, var4, p_74875_3_);
/* 1153 */             func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 3, var4, p_74875_3_);
/*      */             
/* 1155 */             if (var4 <= 3 || var4 >= 7) {
/*      */               
/* 1157 */               func_175811_a(worldIn, Blocks.planks.getDefaultState(), 4, 3, var4, p_74875_3_);
/* 1158 */               func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 3, var4, p_74875_3_);
/* 1159 */               func_175811_a(worldIn, Blocks.planks.getDefaultState(), 6, 3, var4, p_74875_3_);
/*      */             } 
/*      */             
/* 1162 */             func_175811_a(worldIn, Blocks.planks.getDefaultState(), 7, 3, var4, p_74875_3_);
/* 1163 */             func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 3, var4, p_74875_3_);
/*      */           } 
/*      */           
/* 1166 */           func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 1, 3, p_74875_3_);
/* 1167 */           func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 2, 3, p_74875_3_);
/* 1168 */           func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(getMetadataWithOffset(Blocks.ladder, EnumFacing.WEST.getIndex())), 9, 3, 3, p_74875_3_);
/* 1169 */           func_180778_a(worldIn, p_74875_3_, p_74875_2_, 3, 4, 8, WeightedRandomChestContent.func_177629_a(strongholdRoomCrossingChestContents, new WeightedRandomChestContent[] { Items.enchanted_book.getRandomEnchantedBook(p_74875_2_) }), 1 + p_74875_2_.nextInt(4));
/*      */           break;
/*      */       } 
/* 1172 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class Stairs
/*      */     extends Stronghold
/*      */   {
/*      */     private boolean field_75024_a;
/*      */     private static final String __OBFID = "CL_00000498";
/*      */     
/*      */     public Stairs() {}
/*      */     
/*      */     public Stairs(int p_i2081_1_, Random p_i2081_2_, int p_i2081_3_, int p_i2081_4_) {
/* 1186 */       super(p_i2081_1_);
/* 1187 */       this.field_75024_a = true;
/* 1188 */       this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2081_2_);
/* 1189 */       this.field_143013_d = StructureStrongholdPieces.Stronghold.Door.OPENING;
/*      */       
/* 1191 */       switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
/*      */         
/*      */         case 1:
/*      */         case 2:
/* 1195 */           this.boundingBox = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
/*      */           return;
/*      */       } 
/*      */       
/* 1199 */       this.boundingBox = new StructureBoundingBox(p_i2081_3_, 64, p_i2081_4_, p_i2081_3_ + 5 - 1, 74, p_i2081_4_ + 5 - 1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Stairs(int p_i45574_1_, Random p_i45574_2_, StructureBoundingBox p_i45574_3_, EnumFacing p_i45574_4_) {
/* 1205 */       super(p_i45574_1_);
/* 1206 */       this.field_75024_a = false;
/* 1207 */       this.coordBaseMode = p_i45574_4_;
/* 1208 */       this.field_143013_d = getRandomDoor(p_i45574_2_);
/* 1209 */       this.boundingBox = p_i45574_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1214 */       super.writeStructureToNBT(p_143012_1_);
/* 1215 */       p_143012_1_.setBoolean("Source", this.field_75024_a);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1220 */       super.readStructureFromNBT(p_143011_1_);
/* 1221 */       this.field_75024_a = p_143011_1_.getBoolean("Source");
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 1226 */       if (this.field_75024_a)
/*      */       {
/* 1228 */         StructureStrongholdPieces.strongComponentType = StructureStrongholdPieces.Crossing.class;
/*      */       }
/*      */       
/* 1231 */       getNextComponentNormal((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public static Stairs func_175863_a(List p_175863_0_, Random p_175863_1_, int p_175863_2_, int p_175863_3_, int p_175863_4_, EnumFacing p_175863_5_, int p_175863_6_) {
/* 1236 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175863_2_, p_175863_3_, p_175863_4_, -1, -7, 0, 5, 11, 5, p_175863_5_);
/* 1237 */       return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175863_0_, var7) == null) ? new Stairs(p_175863_6_, p_175863_1_, var7, p_175863_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1242 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/* 1244 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1248 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 10, 4, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/* 1249 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 7, 0);
/* 1250 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 4);
/* 1251 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 6, 1, p_74875_3_);
/* 1252 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 5, 1, p_74875_3_);
/* 1253 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 1, 6, 1, p_74875_3_);
/* 1254 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 5, 2, p_74875_3_);
/* 1255 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 4, 3, p_74875_3_);
/* 1256 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 1, 5, 3, p_74875_3_);
/* 1257 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 4, 3, p_74875_3_);
/* 1258 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 3, 3, p_74875_3_);
/* 1259 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 3, 4, 3, p_74875_3_);
/* 1260 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 3, 2, p_74875_3_);
/* 1261 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 2, 1, p_74875_3_);
/* 1262 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 3, 3, 1, p_74875_3_);
/* 1263 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 2, 1, p_74875_3_);
/* 1264 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 1, 1, p_74875_3_);
/* 1265 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 1, 2, 1, p_74875_3_);
/* 1266 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 1, 2, p_74875_3_);
/* 1267 */       func_175811_a(worldIn, Blocks.stone_slab.getStateFromMeta(BlockStoneSlab.EnumType.STONE.func_176624_a()), 1, 1, 3, p_74875_3_);
/* 1268 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Stairs2
/*      */     extends Stairs
/*      */   {
/*      */     public StructureStrongholdPieces.PieceWeight strongholdPieceWeight;
/*      */     public StructureStrongholdPieces.PortalRoom strongholdPortalRoom;
/* 1277 */     public List field_75026_c = Lists.newArrayList();
/*      */     
/*      */     private static final String __OBFID = "CL_00000499";
/*      */     
/*      */     public Stairs2() {}
/*      */     
/*      */     public Stairs2(int p_i2083_1_, Random p_i2083_2_, int p_i2083_3_, int p_i2083_4_) {
/* 1284 */       super(0, p_i2083_2_, p_i2083_3_, p_i2083_4_);
/*      */     }
/*      */ 
/*      */     
/*      */     public BlockPos func_180776_a() {
/* 1289 */       return (this.strongholdPortalRoom != null) ? this.strongholdPortalRoom.func_180776_a() : super.func_180776_a();
/*      */     }
/*      */   }
/*      */   
/*      */   public static class StairsStraight
/*      */     extends Stronghold
/*      */   {
/*      */     private static final String __OBFID = "CL_00000501";
/*      */     
/*      */     public StairsStraight() {}
/*      */     
/*      */     public StairsStraight(int p_i45572_1_, Random p_i45572_2_, StructureBoundingBox p_i45572_3_, EnumFacing p_i45572_4_) {
/* 1301 */       super(p_i45572_1_);
/* 1302 */       this.coordBaseMode = p_i45572_4_;
/* 1303 */       this.field_143013_d = getRandomDoor(p_i45572_2_);
/* 1304 */       this.boundingBox = p_i45572_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 1309 */       getNextComponentNormal((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public static StairsStraight func_175861_a(List p_175861_0_, Random p_175861_1_, int p_175861_2_, int p_175861_3_, int p_175861_4_, EnumFacing p_175861_5_, int p_175861_6_) {
/* 1314 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175861_2_, p_175861_3_, p_175861_4_, -1, -7, 0, 5, 11, 8, p_175861_5_);
/* 1315 */       return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175861_0_, var7) == null) ? new StairsStraight(p_175861_6_, p_175861_1_, var7, p_175861_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1320 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/* 1322 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1326 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 10, 7, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/* 1327 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 7, 0);
/* 1328 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 7);
/* 1329 */       int var4 = getMetadataWithOffset(Blocks.stone_stairs, 2);
/*      */       
/* 1331 */       for (int var5 = 0; var5 < 6; var5++) {
/*      */         
/* 1333 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 1, 6 - var5, 1 + var5, p_74875_3_);
/* 1334 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 2, 6 - var5, 1 + var5, p_74875_3_);
/* 1335 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(var4), 3, 6 - var5, 1 + var5, p_74875_3_);
/*      */         
/* 1337 */         if (var5 < 5) {
/*      */           
/* 1339 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 1, 5 - var5, 1 + var5, p_74875_3_);
/* 1340 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 2, 5 - var5, 1 + var5, p_74875_3_);
/* 1341 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), 3, 5 - var5, 1 + var5, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/* 1345 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class Stones
/*      */     extends StructureComponent.BlockSelector
/*      */   {
/*      */     private static final String __OBFID = "CL_00000497";
/*      */     
/*      */     private Stones() {}
/*      */     
/*      */     public void selectBlocks(Random p_75062_1_, int p_75062_2_, int p_75062_3_, int p_75062_4_, boolean p_75062_5_) {
/* 1358 */       if (p_75062_5_) {
/*      */         
/* 1360 */         float var6 = p_75062_1_.nextFloat();
/*      */         
/* 1362 */         if (var6 < 0.2F)
/*      */         {
/* 1364 */           this.field_151562_a = Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.CRACKED_META);
/*      */         }
/* 1366 */         else if (var6 < 0.5F)
/*      */         {
/* 1368 */           this.field_151562_a = Blocks.stonebrick.getStateFromMeta(BlockStoneBrick.MOSSY_META);
/*      */         }
/* 1370 */         else if (var6 < 0.55F)
/*      */         {
/* 1372 */           this.field_151562_a = Blocks.monster_egg.getStateFromMeta(BlockSilverfish.EnumType.STONEBRICK.func_176881_a());
/*      */         }
/*      */         else
/*      */         {
/* 1376 */           this.field_151562_a = Blocks.stonebrick.getDefaultState();
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1381 */         this.field_151562_a = Blocks.air.getDefaultState();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     Stones(Object p_i2080_1_) {
/* 1387 */       this();
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Straight
/*      */     extends Stronghold
/*      */   {
/*      */     private boolean expandsX;
/*      */     private boolean expandsZ;
/*      */     private static final String __OBFID = "CL_00000500";
/*      */     
/*      */     public Straight() {}
/*      */     
/*      */     public Straight(int p_i45573_1_, Random p_i45573_2_, StructureBoundingBox p_i45573_3_, EnumFacing p_i45573_4_) {
/* 1401 */       super(p_i45573_1_);
/* 1402 */       this.coordBaseMode = p_i45573_4_;
/* 1403 */       this.field_143013_d = getRandomDoor(p_i45573_2_);
/* 1404 */       this.boundingBox = p_i45573_3_;
/* 1405 */       this.expandsX = (p_i45573_2_.nextInt(2) == 0);
/* 1406 */       this.expandsZ = (p_i45573_2_.nextInt(2) == 0);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1411 */       super.writeStructureToNBT(p_143012_1_);
/* 1412 */       p_143012_1_.setBoolean("Left", this.expandsX);
/* 1413 */       p_143012_1_.setBoolean("Right", this.expandsZ);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1418 */       super.readStructureFromNBT(p_143011_1_);
/* 1419 */       this.expandsX = p_143011_1_.getBoolean("Left");
/* 1420 */       this.expandsZ = p_143011_1_.getBoolean("Right");
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 1425 */       getNextComponentNormal((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 1);
/*      */       
/* 1427 */       if (this.expandsX)
/*      */       {
/* 1429 */         getNextComponentX((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 2);
/*      */       }
/*      */       
/* 1432 */       if (this.expandsZ)
/*      */       {
/* 1434 */         getNextComponentZ((StructureStrongholdPieces.Stairs2)p_74861_1_, p_74861_2_, p_74861_3_, 1, 2);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public static Straight func_175862_a(List p_175862_0_, Random p_175862_1_, int p_175862_2_, int p_175862_3_, int p_175862_4_, EnumFacing p_175862_5_, int p_175862_6_) {
/* 1440 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175862_2_, p_175862_3_, p_175862_4_, -1, -1, 0, 5, 5, 7, p_175862_5_);
/* 1441 */       return (canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(p_175862_0_, var7) == null) ? new Straight(p_175862_6_, p_175862_1_, var7, p_175862_5_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1446 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*      */       {
/* 1448 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1452 */       fillWithRandomizedBlocks(worldIn, p_74875_3_, 0, 0, 0, 4, 4, 6, true, p_74875_2_, StructureStrongholdPieces.strongholdStones);
/* 1453 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, this.field_143013_d, 1, 1, 0);
/* 1454 */       placeDoor(worldIn, p_74875_2_, p_74875_3_, StructureStrongholdPieces.Stronghold.Door.OPENING, 1, 1, 6);
/* 1455 */       func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1F, 1, 2, 1, Blocks.torch.getDefaultState());
/* 1456 */       func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1F, 3, 2, 1, Blocks.torch.getDefaultState());
/* 1457 */       func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1F, 1, 2, 5, Blocks.torch.getDefaultState());
/* 1458 */       func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1F, 3, 2, 5, Blocks.torch.getDefaultState());
/*      */       
/* 1460 */       if (this.expandsX)
/*      */       {
/* 1462 */         func_175804_a(worldIn, p_74875_3_, 0, 1, 2, 0, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       }
/*      */       
/* 1465 */       if (this.expandsZ)
/*      */       {
/* 1467 */         func_175804_a(worldIn, p_74875_3_, 4, 1, 2, 4, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       }
/*      */       
/* 1470 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static abstract class Stronghold
/*      */     extends StructureComponent
/*      */   {
/*      */     protected Door field_143013_d;
/*      */     private static final String __OBFID = "CL_00000503";
/*      */     
/*      */     public Stronghold() {
/* 1482 */       this.field_143013_d = Door.OPENING;
/*      */     }
/*      */ 
/*      */     
/*      */     protected Stronghold(int p_i2087_1_) {
/* 1487 */       super(p_i2087_1_);
/* 1488 */       this.field_143013_d = Door.OPENING;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1493 */       p_143012_1_.setString("EntryDoor", this.field_143013_d.name());
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1498 */       this.field_143013_d = Door.valueOf(p_143011_1_.getString("EntryDoor"));
/*      */     }
/*      */ 
/*      */     
/*      */     protected void placeDoor(World worldIn, Random p_74990_2_, StructureBoundingBox p_74990_3_, Door p_74990_4_, int p_74990_5_, int p_74990_6_, int p_74990_7_) {
/* 1503 */       switch (StructureStrongholdPieces.SwitchEnumFacing.doorEnum[p_74990_4_.ordinal()]) {
/*      */ 
/*      */         
/*      */         default:
/* 1507 */           func_175804_a(worldIn, p_74990_3_, p_74990_5_, p_74990_6_, p_74990_7_, p_74990_5_ + 3 - 1, p_74990_6_ + 3 - 1, p_74990_7_, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */           return;
/*      */         
/*      */         case 2:
/* 1511 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
/* 1512 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
/* 1513 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
/* 1514 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
/* 1515 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
/* 1516 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
/* 1517 */           func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
/* 1518 */           func_175811_a(worldIn, Blocks.oak_door.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
/* 1519 */           func_175811_a(worldIn, Blocks.oak_door.getStateFromMeta(8), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
/*      */           return;
/*      */         
/*      */         case 3:
/* 1523 */           func_175811_a(worldIn, Blocks.air.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
/* 1524 */           func_175811_a(worldIn, Blocks.air.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
/* 1525 */           func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
/* 1526 */           func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
/* 1527 */           func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
/* 1528 */           func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
/* 1529 */           func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
/* 1530 */           func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
/* 1531 */           func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_); return;
/*      */         case 4:
/*      */           break;
/*      */       } 
/* 1535 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_, p_74990_7_, p_74990_3_);
/* 1536 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
/* 1537 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
/* 1538 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 1, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
/* 1539 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 2, p_74990_7_, p_74990_3_);
/* 1540 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
/* 1541 */       func_175811_a(worldIn, Blocks.stonebrick.getDefaultState(), p_74990_5_ + 2, p_74990_6_, p_74990_7_, p_74990_3_);
/* 1542 */       func_175811_a(worldIn, Blocks.iron_door.getDefaultState(), p_74990_5_ + 1, p_74990_6_, p_74990_7_, p_74990_3_);
/* 1543 */       func_175811_a(worldIn, Blocks.iron_door.getStateFromMeta(8), p_74990_5_ + 1, p_74990_6_ + 1, p_74990_7_, p_74990_3_);
/* 1544 */       func_175811_a(worldIn, Blocks.stone_button.getStateFromMeta(getMetadataWithOffset(Blocks.stone_button, 4)), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ + 1, p_74990_3_);
/* 1545 */       func_175811_a(worldIn, Blocks.stone_button.getStateFromMeta(getMetadataWithOffset(Blocks.stone_button, 3)), p_74990_5_ + 2, p_74990_6_ + 1, p_74990_7_ - 1, p_74990_3_);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected Door getRandomDoor(Random p_74988_1_) {
/* 1551 */       int var2 = p_74988_1_.nextInt(5);
/*      */       
/* 1553 */       switch (var2) {
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 1558 */           return Door.OPENING;
/*      */         
/*      */         case 2:
/* 1561 */           return Door.WOOD_DOOR;
/*      */         
/*      */         case 3:
/* 1564 */           return Door.GRATES;
/*      */         case 4:
/*      */           break;
/* 1567 */       }  return Door.IRON_DOOR;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected StructureComponent getNextComponentNormal(StructureStrongholdPieces.Stairs2 p_74986_1_, List p_74986_2_, Random p_74986_3_, int p_74986_4_, int p_74986_5_) {
/* 1573 */       if (this.coordBaseMode != null)
/*      */       {
/* 1575 */         switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1578 */             return StructureStrongholdPieces.func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ - 1, this.coordBaseMode, getComponentType());
/*      */           
/*      */           case 2:
/* 1581 */             return StructureStrongholdPieces.func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX + p_74986_4_, this.boundingBox.minY + p_74986_5_, this.boundingBox.maxZ + 1, this.coordBaseMode, getComponentType());
/*      */           
/*      */           case 3:
/* 1584 */             return StructureStrongholdPieces.func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, this.coordBaseMode, getComponentType());
/*      */           
/*      */           case 4:
/* 1587 */             return StructureStrongholdPieces.func_175953_c(p_74986_1_, p_74986_2_, p_74986_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74986_5_, this.boundingBox.minZ + p_74986_4_, this.coordBaseMode, getComponentType());
/*      */         } 
/*      */       
/*      */       }
/* 1591 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected StructureComponent getNextComponentX(StructureStrongholdPieces.Stairs2 p_74989_1_, List p_74989_2_, Random p_74989_3_, int p_74989_4_, int p_74989_5_) {
/* 1596 */       if (this.coordBaseMode != null)
/*      */       {
/* 1598 */         switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1601 */             return StructureStrongholdPieces.func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, getComponentType());
/*      */           
/*      */           case 2:
/* 1604 */             return StructureStrongholdPieces.func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ + p_74989_5_, EnumFacing.WEST, getComponentType());
/*      */           
/*      */           case 3:
/* 1607 */             return StructureStrongholdPieces.func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, getComponentType());
/*      */           
/*      */           case 4:
/* 1610 */             return StructureStrongholdPieces.func_175953_c(p_74989_1_, p_74989_2_, p_74989_3_, this.boundingBox.minX + p_74989_5_, this.boundingBox.minY + p_74989_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, getComponentType());
/*      */         } 
/*      */       
/*      */       }
/* 1614 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected StructureComponent getNextComponentZ(StructureStrongholdPieces.Stairs2 p_74987_1_, List p_74987_2_, Random p_74987_3_, int p_74987_4_, int p_74987_5_) {
/* 1619 */       if (this.coordBaseMode != null)
/*      */       {
/* 1621 */         switch (StructureStrongholdPieces.SwitchEnumFacing.field_175951_b[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1624 */             return StructureStrongholdPieces.func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, getComponentType());
/*      */           
/*      */           case 2:
/* 1627 */             return StructureStrongholdPieces.func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74987_4_, this.boundingBox.minZ + p_74987_5_, EnumFacing.EAST, getComponentType());
/*      */           
/*      */           case 3:
/* 1630 */             return StructureStrongholdPieces.func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, getComponentType());
/*      */           
/*      */           case 4:
/* 1633 */             return StructureStrongholdPieces.func_175953_c(p_74987_1_, p_74987_2_, p_74987_3_, this.boundingBox.minX + p_74987_5_, this.boundingBox.minY + p_74987_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, getComponentType());
/*      */         } 
/*      */       
/*      */       }
/* 1637 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected static boolean canStrongholdGoDeeper(StructureBoundingBox p_74991_0_) {
/* 1642 */       return (p_74991_0_ != null && p_74991_0_.minY > 10);
/*      */     }
/*      */     
/*      */     public enum Door
/*      */     {
/* 1647 */       OPENING("OPENING", 0),
/* 1648 */       WOOD_DOOR("WOOD_DOOR", 1),
/* 1649 */       GRATES("GRATES", 2),
/* 1650 */       IRON_DOOR("IRON_DOOR", 3);
/*      */       
/* 1652 */       private static final Door[] $VALUES = new Door[] { OPENING, WOOD_DOOR, GRATES, IRON_DOOR };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       private static final String __OBFID = "CL_00000504";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       static {
/*      */       
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final class SwitchEnumFacing
/*      */   {
/* 1704 */     static final int[] doorEnum = new int[(StructureStrongholdPieces.Stronghold.Door.values()).length]; static final int[] field_175951_b = new int[(EnumFacing.values()).length]; private static final String __OBFID = "CL_00001970";
/*      */     
/*      */     static {
/*      */       try {
/* 1708 */         doorEnum[StructureStrongholdPieces.Stronghold.Door.OPENING.ordinal()] = 1;
/*      */       }
/* 1710 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1717 */         doorEnum[StructureStrongholdPieces.Stronghold.Door.WOOD_DOOR.ordinal()] = 2;
/*      */       }
/* 1719 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1726 */         doorEnum[StructureStrongholdPieces.Stronghold.Door.GRATES.ordinal()] = 3;
/*      */       }
/* 1728 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1735 */         doorEnum[StructureStrongholdPieces.Stronghold.Door.IRON_DOOR.ordinal()] = 4;
/*      */       }
/* 1737 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureStrongholdPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */