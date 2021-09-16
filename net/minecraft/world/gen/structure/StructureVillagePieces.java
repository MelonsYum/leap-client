/*      */ package net.minecraft.world.gen.structure;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockSandStone;
/*      */ import net.minecraft.block.BlockStairs;
/*      */ import net.minecraft.block.BlockTorch;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.properties.IProperty;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.passive.EntityVillager;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.EnumDyeColor;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3i;
/*      */ import net.minecraft.util.WeightedRandomChestContent;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import net.minecraft.world.biome.WorldChunkManager;
/*      */ 
/*      */ public class StructureVillagePieces {
/*      */   private static final String __OBFID = "CL_00000516";
/*      */   
/*      */   public static void registerVillagePieces() {
/*   35 */     MapGenStructureIO.registerStructureComponent(House1.class, "ViBH");
/*   36 */     MapGenStructureIO.registerStructureComponent(Field1.class, "ViDF");
/*   37 */     MapGenStructureIO.registerStructureComponent(Field2.class, "ViF");
/*   38 */     MapGenStructureIO.registerStructureComponent(Torch.class, "ViL");
/*   39 */     MapGenStructureIO.registerStructureComponent(Hall.class, "ViPH");
/*   40 */     MapGenStructureIO.registerStructureComponent(House4Garden.class, "ViSH");
/*   41 */     MapGenStructureIO.registerStructureComponent(WoodHut.class, "ViSmH");
/*   42 */     MapGenStructureIO.registerStructureComponent(Church.class, "ViST");
/*   43 */     MapGenStructureIO.registerStructureComponent(House2.class, "ViS");
/*   44 */     MapGenStructureIO.registerStructureComponent(Start.class, "ViStart");
/*   45 */     MapGenStructureIO.registerStructureComponent(Path.class, "ViSR");
/*   46 */     MapGenStructureIO.registerStructureComponent(House3.class, "ViTRH");
/*   47 */     MapGenStructureIO.registerStructureComponent(Well.class, "ViW");
/*      */   }
/*      */ 
/*      */   
/*      */   public static List getStructureVillageWeightedPieceList(Random p_75084_0_, int p_75084_1_) {
/*   52 */     ArrayList<PieceWeight> var2 = Lists.newArrayList();
/*   53 */     var2.add(new PieceWeight(House4Garden.class, 4, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
/*   54 */     var2.add(new PieceWeight(Church.class, 20, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 1 + p_75084_1_)));
/*   55 */     var2.add(new PieceWeight(House1.class, 20, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 2 + p_75084_1_)));
/*   56 */     var2.add(new PieceWeight(WoodHut.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + p_75084_1_, 5 + p_75084_1_ * 3)));
/*   57 */     var2.add(new PieceWeight(Hall.class, 15, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 2 + p_75084_1_)));
/*   58 */     var2.add(new PieceWeight(Field1.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 1 + p_75084_1_, 4 + p_75084_1_)));
/*   59 */     var2.add(new PieceWeight(Field2.class, 3, MathHelper.getRandomIntegerInRange(p_75084_0_, 2 + p_75084_1_, 4 + p_75084_1_ * 2)));
/*   60 */     var2.add(new PieceWeight(House2.class, 15, MathHelper.getRandomIntegerInRange(p_75084_0_, 0, 1 + p_75084_1_)));
/*   61 */     var2.add(new PieceWeight(House3.class, 8, MathHelper.getRandomIntegerInRange(p_75084_0_, 0 + p_75084_1_, 3 + p_75084_1_ * 2)));
/*   62 */     Iterator<PieceWeight> var3 = var2.iterator();
/*      */     
/*   64 */     while (var3.hasNext()) {
/*      */       
/*   66 */       if (((PieceWeight)var3.next()).villagePiecesLimit == 0)
/*      */       {
/*   68 */         var3.remove();
/*      */       }
/*      */     } 
/*      */     
/*   72 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int func_75079_a(List p_75079_0_) {
/*   77 */     boolean var1 = false;
/*   78 */     int var2 = 0;
/*      */ 
/*      */     
/*   81 */     for (Iterator<PieceWeight> var3 = p_75079_0_.iterator(); var3.hasNext(); var2 += var4.villagePieceWeight) {
/*      */       
/*   83 */       PieceWeight var4 = var3.next();
/*      */       
/*   85 */       if (var4.villagePiecesLimit > 0 && var4.villagePiecesSpawned < var4.villagePiecesLimit)
/*      */       {
/*   87 */         var1 = true;
/*      */       }
/*      */     } 
/*      */     
/*   91 */     return var1 ? var2 : -1;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Village func_176065_a(Start p_176065_0_, PieceWeight p_176065_1_, List p_176065_2_, Random p_176065_3_, int p_176065_4_, int p_176065_5_, int p_176065_6_, EnumFacing p_176065_7_, int p_176065_8_) {
/*   96 */     Class<House4Garden> var9 = p_176065_1_.villagePieceClass;
/*   97 */     Object var10 = null;
/*      */     
/*   99 */     if (var9 == House4Garden.class) {
/*      */       
/*  101 */       var10 = House4Garden.func_175858_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
/*      */     }
/*  103 */     else if (var9 == Church.class) {
/*      */       
/*  105 */       var10 = Church.func_175854_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
/*      */     }
/*  107 */     else if (var9 == House1.class) {
/*      */       
/*  109 */       var10 = House1.func_175850_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
/*      */     }
/*  111 */     else if (var9 == WoodHut.class) {
/*      */       
/*  113 */       var10 = WoodHut.func_175853_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
/*      */     }
/*  115 */     else if (var9 == Hall.class) {
/*      */       
/*  117 */       var10 = Hall.func_175857_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
/*      */     }
/*  119 */     else if (var9 == Field1.class) {
/*      */       
/*  121 */       var10 = Field1.func_175851_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
/*      */     }
/*  123 */     else if (var9 == Field2.class) {
/*      */       
/*  125 */       var10 = Field2.func_175852_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
/*      */     }
/*  127 */     else if (var9 == House2.class) {
/*      */       
/*  129 */       var10 = House2.func_175855_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
/*      */     }
/*  131 */     else if (var9 == House3.class) {
/*      */       
/*  133 */       var10 = House3.func_175849_a(p_176065_0_, p_176065_2_, p_176065_3_, p_176065_4_, p_176065_5_, p_176065_6_, p_176065_7_, p_176065_8_);
/*      */     } 
/*      */     
/*  136 */     return (Village)var10;
/*      */   }
/*      */ 
/*      */   
/*      */   private static Village func_176067_c(Start p_176067_0_, List p_176067_1_, Random p_176067_2_, int p_176067_3_, int p_176067_4_, int p_176067_5_, EnumFacing p_176067_6_, int p_176067_7_) {
/*  141 */     int var8 = func_75079_a(p_176067_0_.structureVillageWeightedPieceList);
/*      */     
/*  143 */     if (var8 <= 0)
/*      */     {
/*  145 */       return null;
/*      */     }
/*      */ 
/*      */     
/*  149 */     int var9 = 0;
/*      */     
/*  151 */     while (var9 < 5) {
/*      */       
/*  153 */       var9++;
/*  154 */       int var10 = p_176067_2_.nextInt(var8);
/*  155 */       Iterator<PieceWeight> var11 = p_176067_0_.structureVillageWeightedPieceList.iterator();
/*      */       
/*  157 */       while (var11.hasNext()) {
/*      */         
/*  159 */         PieceWeight var12 = var11.next();
/*  160 */         var10 -= var12.villagePieceWeight;
/*      */         
/*  162 */         if (var10 < 0) {
/*      */           
/*  164 */           if (!var12.canSpawnMoreVillagePiecesOfType(p_176067_7_) || (var12 == p_176067_0_.structVillagePieceWeight && p_176067_0_.structureVillageWeightedPieceList.size() > 1)) {
/*      */             break;
/*      */           }
/*      */ 
/*      */           
/*  169 */           Village var13 = func_176065_a(p_176067_0_, var12, p_176067_1_, p_176067_2_, p_176067_3_, p_176067_4_, p_176067_5_, p_176067_6_, p_176067_7_);
/*      */           
/*  171 */           if (var13 != null) {
/*      */             
/*  173 */             var12.villagePiecesSpawned++;
/*  174 */             p_176067_0_.structVillagePieceWeight = var12;
/*      */             
/*  176 */             if (!var12.canSpawnMoreVillagePieces())
/*      */             {
/*  178 */               p_176067_0_.structureVillageWeightedPieceList.remove(var12);
/*      */             }
/*      */             
/*  181 */             return var13;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  187 */     StructureBoundingBox var14 = Torch.func_175856_a(p_176067_0_, p_176067_1_, p_176067_2_, p_176067_3_, p_176067_4_, p_176067_5_, p_176067_6_);
/*      */     
/*  189 */     if (var14 != null)
/*      */     {
/*  191 */       return new Torch(p_176067_0_, p_176067_7_, p_176067_2_, var14, p_176067_6_);
/*      */     }
/*      */ 
/*      */     
/*  195 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static StructureComponent func_176066_d(Start p_176066_0_, List<Village> p_176066_1_, Random p_176066_2_, int p_176066_3_, int p_176066_4_, int p_176066_5_, EnumFacing p_176066_6_, int p_176066_7_) {
/*  202 */     if (p_176066_7_ > 50)
/*      */     {
/*  204 */       return null;
/*      */     }
/*  206 */     if (Math.abs(p_176066_3_ - (p_176066_0_.getBoundingBox()).minX) <= 112 && Math.abs(p_176066_5_ - (p_176066_0_.getBoundingBox()).minZ) <= 112) {
/*      */       
/*  208 */       Village var8 = func_176067_c(p_176066_0_, p_176066_1_, p_176066_2_, p_176066_3_, p_176066_4_, p_176066_5_, p_176066_6_, p_176066_7_ + 1);
/*      */       
/*  210 */       if (var8 != null) {
/*      */         
/*  212 */         int var9 = (var8.boundingBox.minX + var8.boundingBox.maxX) / 2;
/*  213 */         int var10 = (var8.boundingBox.minZ + var8.boundingBox.maxZ) / 2;
/*  214 */         int var11 = var8.boundingBox.maxX - var8.boundingBox.minX;
/*  215 */         int var12 = var8.boundingBox.maxZ - var8.boundingBox.minZ;
/*  216 */         int var13 = (var11 > var12) ? var11 : var12;
/*      */         
/*  218 */         if (p_176066_0_.getWorldChunkManager().areBiomesViable(var9, var10, var13 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
/*      */           
/*  220 */           p_176066_1_.add(var8);
/*  221 */           p_176066_0_.field_74932_i.add(var8);
/*  222 */           return var8;
/*      */         } 
/*      */       } 
/*      */       
/*  226 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  230 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static StructureComponent func_176069_e(Start p_176069_0_, List<Path> p_176069_1_, Random p_176069_2_, int p_176069_3_, int p_176069_4_, int p_176069_5_, EnumFacing p_176069_6_, int p_176069_7_) {
/*  236 */     if (p_176069_7_ > 3 + p_176069_0_.terrainType)
/*      */     {
/*  238 */       return null;
/*      */     }
/*  240 */     if (Math.abs(p_176069_3_ - (p_176069_0_.getBoundingBox()).minX) <= 112 && Math.abs(p_176069_5_ - (p_176069_0_.getBoundingBox()).minZ) <= 112) {
/*      */       
/*  242 */       StructureBoundingBox var8 = Path.func_175848_a(p_176069_0_, p_176069_1_, p_176069_2_, p_176069_3_, p_176069_4_, p_176069_5_, p_176069_6_);
/*      */       
/*  244 */       if (var8 != null && var8.minY > 10) {
/*      */         
/*  246 */         Path var9 = new Path(p_176069_0_, p_176069_7_, p_176069_2_, var8, p_176069_6_);
/*  247 */         int var10 = (var9.boundingBox.minX + var9.boundingBox.maxX) / 2;
/*  248 */         int var11 = (var9.boundingBox.minZ + var9.boundingBox.maxZ) / 2;
/*  249 */         int var12 = var9.boundingBox.maxX - var9.boundingBox.minX;
/*  250 */         int var13 = var9.boundingBox.maxZ - var9.boundingBox.minZ;
/*  251 */         int var14 = (var12 > var13) ? var12 : var13;
/*      */         
/*  253 */         if (p_176069_0_.getWorldChunkManager().areBiomesViable(var10, var11, var14 / 2 + 4, MapGenVillage.villageSpawnBiomes)) {
/*      */           
/*  255 */           p_176069_1_.add(var9);
/*  256 */           p_176069_0_.field_74930_j.add(var9);
/*  257 */           return var9;
/*      */         } 
/*      */       } 
/*      */       
/*  261 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*  265 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public static class Church
/*      */     extends Village
/*      */   {
/*      */     private static final String __OBFID = "CL_00000525";
/*      */     
/*      */     public Church() {}
/*      */     
/*      */     public Church(StructureVillagePieces.Start p_i45564_1_, int p_i45564_2_, Random p_i45564_3_, StructureBoundingBox p_i45564_4_, EnumFacing p_i45564_5_) {
/*  277 */       super(p_i45564_1_, p_i45564_2_);
/*  278 */       this.coordBaseMode = p_i45564_5_;
/*  279 */       this.boundingBox = p_i45564_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     public static Church func_175854_a(StructureVillagePieces.Start p_175854_0_, List p_175854_1_, Random p_175854_2_, int p_175854_3_, int p_175854_4_, int p_175854_5_, EnumFacing p_175854_6_, int p_175854_7_) {
/*  284 */       StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175854_3_, p_175854_4_, p_175854_5_, 0, 0, 0, 5, 12, 9, p_175854_6_);
/*  285 */       return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(p_175854_1_, var8) == null) ? new Church(p_175854_0_, p_175854_7_, p_175854_2_, var8, p_175854_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  290 */       if (this.field_143015_k < 0) {
/*      */         
/*  292 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/*  294 */         if (this.field_143015_k < 0)
/*      */         {
/*  296 */           return true;
/*      */         }
/*      */         
/*  299 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 12 - 1, 0);
/*      */       } 
/*      */       
/*  302 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 3, 3, 7, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  303 */       func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 3, 9, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  304 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 3, 0, 8, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  305 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 3, 10, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  306 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 10, 3, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  307 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 10, 3, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  308 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 4, 0, 4, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  309 */       func_175804_a(worldIn, p_74875_3_, 4, 0, 4, 4, 4, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  310 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 8, 3, 4, 8, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  311 */       func_175804_a(worldIn, p_74875_3_, 1, 5, 4, 3, 10, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  312 */       func_175804_a(worldIn, p_74875_3_, 1, 5, 5, 3, 5, 7, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  313 */       func_175804_a(worldIn, p_74875_3_, 0, 9, 0, 4, 9, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  314 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 0, 4, 4, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  315 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 0, 11, 2, p_74875_3_);
/*  316 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 11, 2, p_74875_3_);
/*  317 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 2, 11, 0, p_74875_3_);
/*  318 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 2, 11, 4, p_74875_3_);
/*  319 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 1, 1, 6, p_74875_3_);
/*  320 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 1, 1, 7, p_74875_3_);
/*  321 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 2, 1, 7, p_74875_3_);
/*  322 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 3, 1, 6, p_74875_3_);
/*  323 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 3, 1, 7, p_74875_3_);
/*  324 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 1, 5, p_74875_3_);
/*  325 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 1, 6, p_74875_3_);
/*  326 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), 3, 1, 5, p_74875_3_);
/*  327 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 1)), 1, 2, 7, p_74875_3_);
/*  328 */       func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 0)), 3, 2, 7, p_74875_3_);
/*  329 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
/*  330 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 3, 2, p_74875_3_);
/*  331 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 2, 2, p_74875_3_);
/*  332 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 3, 2, p_74875_3_);
/*  333 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 6, 2, p_74875_3_);
/*  334 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 7, 2, p_74875_3_);
/*  335 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 6, 2, p_74875_3_);
/*  336 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 7, 2, p_74875_3_);
/*  337 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 6, 0, p_74875_3_);
/*  338 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 7, 0, p_74875_3_);
/*  339 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 6, 4, p_74875_3_);
/*  340 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 7, 4, p_74875_3_);
/*  341 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 3, 6, p_74875_3_);
/*  342 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 3, 6, p_74875_3_);
/*  343 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 3, 8, p_74875_3_);
/*  344 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode.getOpposite()), 2, 4, 7, p_74875_3_);
/*  345 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode.rotateY()), 1, 4, 6, p_74875_3_);
/*  346 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode.rotateYCCW()), 3, 4, 6, p_74875_3_);
/*  347 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode), 2, 4, 5, p_74875_3_);
/*  348 */       int var4 = getMetadataWithOffset(Blocks.ladder, 4);
/*      */       
/*      */       int var5;
/*  351 */       for (var5 = 1; var5 <= 9; var5++)
/*      */       {
/*  353 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(var4), 3, var5, 3, p_74875_3_);
/*      */       }
/*      */       
/*  356 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 1, 0, p_74875_3_);
/*  357 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 2, 0, p_74875_3_);
/*  358 */       func_175810_a(worldIn, p_74875_3_, p_74875_2_, 2, 1, 0, EnumFacing.getHorizontal(getMetadataWithOffset(Blocks.oak_door, 1)));
/*      */       
/*  360 */       if (func_175807_a(worldIn, 2, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && func_175807_a(worldIn, 2, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
/*      */       {
/*  362 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, p_74875_3_);
/*      */       }
/*      */       
/*  365 */       for (var5 = 0; var5 < 9; var5++) {
/*      */         
/*  367 */         for (int var6 = 0; var6 < 5; var6++) {
/*      */           
/*  369 */           clearCurrentPositionBlocksUpwards(worldIn, var6, 12, var5, p_74875_3_);
/*  370 */           func_175808_b(worldIn, Blocks.cobblestone.getDefaultState(), var6, -1, var5, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  374 */       spawnVillagers(worldIn, p_74875_3_, 2, 1, 2, 1);
/*  375 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     protected int func_180779_c(int p_180779_1_, int p_180779_2_) {
/*  380 */       return 2;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Field1
/*      */     extends Village
/*      */   {
/*      */     private Block cropTypeA;
/*      */     private Block cropTypeB;
/*      */     private Block cropTypeC;
/*      */     private Block cropTypeD;
/*      */     private static final String __OBFID = "CL_00000518";
/*      */     
/*      */     public Field1() {}
/*      */     
/*      */     public Field1(StructureVillagePieces.Start p_i45570_1_, int p_i45570_2_, Random p_i45570_3_, StructureBoundingBox p_i45570_4_, EnumFacing p_i45570_5_) {
/*  396 */       super(p_i45570_1_, p_i45570_2_);
/*  397 */       this.coordBaseMode = p_i45570_5_;
/*  398 */       this.boundingBox = p_i45570_4_;
/*  399 */       this.cropTypeA = func_151559_a(p_i45570_3_);
/*  400 */       this.cropTypeB = func_151559_a(p_i45570_3_);
/*  401 */       this.cropTypeC = func_151559_a(p_i45570_3_);
/*  402 */       this.cropTypeD = func_151559_a(p_i45570_3_);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  407 */       super.writeStructureToNBT(p_143012_1_);
/*  408 */       p_143012_1_.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
/*  409 */       p_143012_1_.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
/*  410 */       p_143012_1_.setInteger("CC", Block.blockRegistry.getIDForObject(this.cropTypeC));
/*  411 */       p_143012_1_.setInteger("CD", Block.blockRegistry.getIDForObject(this.cropTypeD));
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  416 */       super.readStructureFromNBT(p_143011_1_);
/*  417 */       this.cropTypeA = Block.getBlockById(p_143011_1_.getInteger("CA"));
/*  418 */       this.cropTypeB = Block.getBlockById(p_143011_1_.getInteger("CB"));
/*  419 */       this.cropTypeC = Block.getBlockById(p_143011_1_.getInteger("CC"));
/*  420 */       this.cropTypeD = Block.getBlockById(p_143011_1_.getInteger("CD"));
/*      */     }
/*      */ 
/*      */     
/*      */     private Block func_151559_a(Random p_151559_1_) {
/*  425 */       switch (p_151559_1_.nextInt(5)) {
/*      */         
/*      */         case 0:
/*  428 */           return Blocks.carrots;
/*      */         
/*      */         case 1:
/*  431 */           return Blocks.potatoes;
/*      */       } 
/*      */       
/*  434 */       return Blocks.wheat;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static Field1 func_175851_a(StructureVillagePieces.Start p_175851_0_, List p_175851_1_, Random p_175851_2_, int p_175851_3_, int p_175851_4_, int p_175851_5_, EnumFacing p_175851_6_, int p_175851_7_) {
/*  440 */       StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175851_3_, p_175851_4_, p_175851_5_, 0, 0, 0, 13, 4, 9, p_175851_6_);
/*  441 */       return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(p_175851_1_, var8) == null) ? new Field1(p_175851_0_, p_175851_7_, p_175851_2_, var8, p_175851_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  446 */       if (this.field_143015_k < 0) {
/*      */         
/*  448 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/*  450 */         if (this.field_143015_k < 0)
/*      */         {
/*  452 */           return true;
/*      */         }
/*      */         
/*  455 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
/*      */       } 
/*      */       
/*  458 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 12, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  459 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
/*  460 */       func_175804_a(worldIn, p_74875_3_, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
/*  461 */       func_175804_a(worldIn, p_74875_3_, 7, 0, 1, 8, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
/*  462 */       func_175804_a(worldIn, p_74875_3_, 10, 0, 1, 11, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
/*  463 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  464 */       func_175804_a(worldIn, p_74875_3_, 6, 0, 0, 6, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  465 */       func_175804_a(worldIn, p_74875_3_, 12, 0, 0, 12, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  466 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 11, 0, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  467 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 8, 11, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  468 */       func_175804_a(worldIn, p_74875_3_, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
/*  469 */       func_175804_a(worldIn, p_74875_3_, 9, 0, 1, 9, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
/*      */       
/*      */       int var4;
/*  472 */       for (var4 = 1; var4 <= 7; var4++) {
/*      */         
/*  474 */         func_175811_a(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 1, 1, var4, p_74875_3_);
/*  475 */         func_175811_a(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 2, 1, var4, p_74875_3_);
/*  476 */         func_175811_a(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 4, 1, var4, p_74875_3_);
/*  477 */         func_175811_a(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 5, 1, var4, p_74875_3_);
/*  478 */         func_175811_a(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 7, 1, var4, p_74875_3_);
/*  479 */         func_175811_a(worldIn, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 8, 1, var4, p_74875_3_);
/*  480 */         func_175811_a(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 10, 1, var4, p_74875_3_);
/*  481 */         func_175811_a(worldIn, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 11, 1, var4, p_74875_3_);
/*      */       } 
/*      */       
/*  484 */       for (var4 = 0; var4 < 9; var4++) {
/*      */         
/*  486 */         for (int var5 = 0; var5 < 13; var5++) {
/*      */           
/*  488 */           clearCurrentPositionBlocksUpwards(worldIn, var5, 4, var4, p_74875_3_);
/*  489 */           func_175808_b(worldIn, Blocks.dirt.getDefaultState(), var5, -1, var4, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  493 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Field2
/*      */     extends Village
/*      */   {
/*      */     private Block cropTypeA;
/*      */     private Block cropTypeB;
/*      */     private static final String __OBFID = "CL_00000519";
/*      */     
/*      */     public Field2() {}
/*      */     
/*      */     public Field2(StructureVillagePieces.Start p_i45569_1_, int p_i45569_2_, Random p_i45569_3_, StructureBoundingBox p_i45569_4_, EnumFacing p_i45569_5_) {
/*  507 */       super(p_i45569_1_, p_i45569_2_);
/*  508 */       this.coordBaseMode = p_i45569_5_;
/*  509 */       this.boundingBox = p_i45569_4_;
/*  510 */       this.cropTypeA = func_151560_a(p_i45569_3_);
/*  511 */       this.cropTypeB = func_151560_a(p_i45569_3_);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  516 */       super.writeStructureToNBT(p_143012_1_);
/*  517 */       p_143012_1_.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
/*  518 */       p_143012_1_.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  523 */       super.readStructureFromNBT(p_143011_1_);
/*  524 */       this.cropTypeA = Block.getBlockById(p_143011_1_.getInteger("CA"));
/*  525 */       this.cropTypeB = Block.getBlockById(p_143011_1_.getInteger("CB"));
/*      */     }
/*      */ 
/*      */     
/*      */     private Block func_151560_a(Random p_151560_1_) {
/*  530 */       switch (p_151560_1_.nextInt(5)) {
/*      */         
/*      */         case 0:
/*  533 */           return Blocks.carrots;
/*      */         
/*      */         case 1:
/*  536 */           return Blocks.potatoes;
/*      */       } 
/*      */       
/*  539 */       return Blocks.wheat;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static Field2 func_175852_a(StructureVillagePieces.Start p_175852_0_, List p_175852_1_, Random p_175852_2_, int p_175852_3_, int p_175852_4_, int p_175852_5_, EnumFacing p_175852_6_, int p_175852_7_) {
/*  545 */       StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175852_3_, p_175852_4_, p_175852_5_, 0, 0, 0, 7, 4, 9, p_175852_6_);
/*  546 */       return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(p_175852_1_, var8) == null) ? new Field2(p_175852_0_, p_175852_7_, p_175852_2_, var8, p_175852_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  551 */       if (this.field_143015_k < 0) {
/*      */         
/*  553 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/*  555 */         if (this.field_143015_k < 0)
/*      */         {
/*  557 */           return true;
/*      */         }
/*      */         
/*  560 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
/*      */       } 
/*      */       
/*  563 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 6, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  564 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
/*  565 */       func_175804_a(worldIn, p_74875_3_, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
/*  566 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  567 */       func_175804_a(worldIn, p_74875_3_, 6, 0, 0, 6, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  568 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 5, 0, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  569 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 8, 5, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  570 */       func_175804_a(worldIn, p_74875_3_, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
/*      */       
/*      */       int var4;
/*  573 */       for (var4 = 1; var4 <= 7; var4++) {
/*      */         
/*  575 */         func_175811_a(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 1, 1, var4, p_74875_3_);
/*  576 */         func_175811_a(worldIn, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 2, 1, var4, p_74875_3_);
/*  577 */         func_175811_a(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 4, 1, var4, p_74875_3_);
/*  578 */         func_175811_a(worldIn, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(p_74875_2_, 2, 7)), 5, 1, var4, p_74875_3_);
/*      */       } 
/*      */       
/*  581 */       for (var4 = 0; var4 < 9; var4++) {
/*      */         
/*  583 */         for (int var5 = 0; var5 < 7; var5++) {
/*      */           
/*  585 */           clearCurrentPositionBlocksUpwards(worldIn, var5, 4, var4, p_74875_3_);
/*  586 */           func_175808_b(worldIn, Blocks.dirt.getDefaultState(), var5, -1, var4, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  590 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Hall
/*      */     extends Village
/*      */   {
/*      */     private static final String __OBFID = "CL_00000522";
/*      */     
/*      */     public Hall() {}
/*      */     
/*      */     public Hall(StructureVillagePieces.Start p_i45567_1_, int p_i45567_2_, Random p_i45567_3_, StructureBoundingBox p_i45567_4_, EnumFacing p_i45567_5_) {
/*  602 */       super(p_i45567_1_, p_i45567_2_);
/*  603 */       this.coordBaseMode = p_i45567_5_;
/*  604 */       this.boundingBox = p_i45567_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     public static Hall func_175857_a(StructureVillagePieces.Start p_175857_0_, List p_175857_1_, Random p_175857_2_, int p_175857_3_, int p_175857_4_, int p_175857_5_, EnumFacing p_175857_6_, int p_175857_7_) {
/*  609 */       StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175857_3_, p_175857_4_, p_175857_5_, 0, 0, 0, 9, 7, 11, p_175857_6_);
/*  610 */       return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(p_175857_1_, var8) == null) ? new Hall(p_175857_0_, p_175857_7_, p_175857_2_, var8, p_175857_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  615 */       if (this.field_143015_k < 0) {
/*      */         
/*  617 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/*  619 */         if (this.field_143015_k < 0)
/*      */         {
/*  621 */           return true;
/*      */         }
/*      */         
/*  624 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 7 - 1, 0);
/*      */       } 
/*      */       
/*  627 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  628 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  629 */       func_175804_a(worldIn, p_74875_3_, 2, 0, 6, 8, 0, 10, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
/*  630 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, 0, 6, p_74875_3_);
/*  631 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 6, 2, 1, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
/*  632 */       func_175804_a(worldIn, p_74875_3_, 8, 1, 6, 8, 1, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
/*  633 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 10, 7, 1, 10, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
/*  634 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 7, 0, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  635 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  636 */       func_175804_a(worldIn, p_74875_3_, 8, 0, 0, 8, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  637 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 7, 1, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  638 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 5, 7, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  639 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 7, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  640 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 5, 7, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  641 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 1, 8, 4, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  642 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 4, 8, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  643 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 2, 8, 5, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  644 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 0, 4, 2, p_74875_3_);
/*  645 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 0, 4, 3, p_74875_3_);
/*  646 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 4, 2, p_74875_3_);
/*  647 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 4, 3, p_74875_3_);
/*  648 */       int var4 = getMetadataWithOffset(Blocks.oak_stairs, 3);
/*  649 */       int var5 = getMetadataWithOffset(Blocks.oak_stairs, 2);
/*      */       
/*      */       int var6;
/*      */       
/*  653 */       for (var6 = -1; var6 <= 2; var6++) {
/*      */         
/*  655 */         for (int var7 = 0; var7 <= 8; var7++) {
/*      */           
/*  657 */           func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var4), var7, 4 + var6, var6, p_74875_3_);
/*  658 */           func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var5), var7, 4 + var6, 5 - var6, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  662 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 0, 2, 1, p_74875_3_);
/*  663 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 0, 2, 4, p_74875_3_);
/*  664 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 8, 2, 1, p_74875_3_);
/*  665 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 8, 2, 4, p_74875_3_);
/*  666 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
/*  667 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 3, p_74875_3_);
/*  668 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 2, p_74875_3_);
/*  669 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 3, p_74875_3_);
/*  670 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 2, 5, p_74875_3_);
/*  671 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 3, 2, 5, p_74875_3_);
/*  672 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 5, 2, 0, p_74875_3_);
/*  673 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 2, 5, p_74875_3_);
/*  674 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 2, 1, 3, p_74875_3_);
/*  675 */       func_175811_a(worldIn, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 3, p_74875_3_);
/*  676 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 1, 1, 4, p_74875_3_);
/*  677 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.oak_stairs, 3)), 2, 1, 4, p_74875_3_);
/*  678 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.oak_stairs, 1)), 1, 1, 3, p_74875_3_);
/*  679 */       func_175804_a(worldIn, p_74875_3_, 5, 0, 1, 7, 0, 3, Blocks.double_stone_slab.getDefaultState(), Blocks.double_stone_slab.getDefaultState(), false);
/*  680 */       func_175811_a(worldIn, Blocks.double_stone_slab.getDefaultState(), 6, 1, 1, p_74875_3_);
/*  681 */       func_175811_a(worldIn, Blocks.double_stone_slab.getDefaultState(), 6, 1, 2, p_74875_3_);
/*  682 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 1, 0, p_74875_3_);
/*  683 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 2, 0, p_74875_3_);
/*  684 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode), 2, 3, 1, p_74875_3_);
/*  685 */       func_175810_a(worldIn, p_74875_3_, p_74875_2_, 2, 1, 0, EnumFacing.getHorizontal(getMetadataWithOffset(Blocks.oak_door, 1)));
/*      */       
/*  687 */       if (func_175807_a(worldIn, 2, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && func_175807_a(worldIn, 2, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
/*      */       {
/*  689 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, p_74875_3_);
/*      */       }
/*      */       
/*  692 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 6, 1, 5, p_74875_3_);
/*  693 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 6, 2, 5, p_74875_3_);
/*  694 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode.getOpposite()), 6, 3, 4, p_74875_3_);
/*  695 */       func_175810_a(worldIn, p_74875_3_, p_74875_2_, 6, 1, 5, EnumFacing.getHorizontal(getMetadataWithOffset(Blocks.oak_door, 1)));
/*      */       
/*  697 */       for (var6 = 0; var6 < 5; var6++) {
/*      */         
/*  699 */         for (int var7 = 0; var7 < 9; var7++) {
/*      */           
/*  701 */           clearCurrentPositionBlocksUpwards(worldIn, var7, 7, var6, p_74875_3_);
/*  702 */           func_175808_b(worldIn, Blocks.cobblestone.getDefaultState(), var7, -1, var6, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  706 */       spawnVillagers(worldIn, p_74875_3_, 4, 1, 2, 2);
/*  707 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     protected int func_180779_c(int p_180779_1_, int p_180779_2_) {
/*  712 */       return (p_180779_1_ == 0) ? 4 : super.func_180779_c(p_180779_1_, p_180779_2_);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class House1
/*      */     extends Village
/*      */   {
/*      */     private static final String __OBFID = "CL_00000517";
/*      */     
/*      */     public House1() {}
/*      */     
/*      */     public House1(StructureVillagePieces.Start p_i45571_1_, int p_i45571_2_, Random p_i45571_3_, StructureBoundingBox p_i45571_4_, EnumFacing p_i45571_5_) {
/*  724 */       super(p_i45571_1_, p_i45571_2_);
/*  725 */       this.coordBaseMode = p_i45571_5_;
/*  726 */       this.boundingBox = p_i45571_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     public static House1 func_175850_a(StructureVillagePieces.Start p_175850_0_, List p_175850_1_, Random p_175850_2_, int p_175850_3_, int p_175850_4_, int p_175850_5_, EnumFacing p_175850_6_, int p_175850_7_) {
/*  731 */       StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175850_3_, p_175850_4_, p_175850_5_, 0, 0, 0, 9, 9, 6, p_175850_6_);
/*  732 */       return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(p_175850_1_, var8) == null) ? new House1(p_175850_0_, p_175850_7_, p_175850_2_, var8, p_175850_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  737 */       if (this.field_143015_k < 0) {
/*      */         
/*  739 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/*  741 */         if (this.field_143015_k < 0)
/*      */         {
/*  743 */           return true;
/*      */         }
/*      */         
/*  746 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 9 - 1, 0);
/*      */       } 
/*      */       
/*  749 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 7, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  750 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 8, 0, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  751 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 8, 5, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  752 */       func_175804_a(worldIn, p_74875_3_, 0, 6, 1, 8, 6, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  753 */       func_175804_a(worldIn, p_74875_3_, 0, 7, 2, 8, 7, 3, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  754 */       int var4 = getMetadataWithOffset(Blocks.oak_stairs, 3);
/*  755 */       int var5 = getMetadataWithOffset(Blocks.oak_stairs, 2);
/*      */       
/*      */       int var6;
/*      */       
/*  759 */       for (var6 = -1; var6 <= 2; var6++) {
/*      */         
/*  761 */         for (int i = 0; i <= 8; i++) {
/*      */           
/*  763 */           func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var4), i, 6 + var6, var6, p_74875_3_);
/*  764 */           func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var5), i, 6 + var6, 5 - var6, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  768 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  769 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 5, 8, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  770 */       func_175804_a(worldIn, p_74875_3_, 8, 1, 0, 8, 1, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  771 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 0, 7, 1, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  772 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 0, 0, 4, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  773 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 5, 0, 4, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  774 */       func_175804_a(worldIn, p_74875_3_, 8, 2, 5, 8, 4, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  775 */       func_175804_a(worldIn, p_74875_3_, 8, 2, 0, 8, 4, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  776 */       func_175804_a(worldIn, p_74875_3_, 0, 2, 1, 0, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  777 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 5, 7, 4, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  778 */       func_175804_a(worldIn, p_74875_3_, 8, 2, 1, 8, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  779 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 7, 4, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  780 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 2, 0, p_74875_3_);
/*  781 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 5, 2, 0, p_74875_3_);
/*  782 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 2, 0, p_74875_3_);
/*  783 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 3, 0, p_74875_3_);
/*  784 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 5, 3, 0, p_74875_3_);
/*  785 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 3, 0, p_74875_3_);
/*  786 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
/*  787 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 3, p_74875_3_);
/*  788 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 3, 2, p_74875_3_);
/*  789 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 3, 3, p_74875_3_);
/*  790 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 2, p_74875_3_);
/*  791 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 3, p_74875_3_);
/*  792 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 3, 2, p_74875_3_);
/*  793 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 3, 3, p_74875_3_);
/*  794 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 2, 5, p_74875_3_);
/*  795 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 3, 2, 5, p_74875_3_);
/*  796 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 5, 2, 5, p_74875_3_);
/*  797 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 6, 2, 5, p_74875_3_);
/*  798 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 1, 7, 4, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  799 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 4, 7, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  800 */       func_175804_a(worldIn, p_74875_3_, 1, 3, 4, 7, 3, 4, Blocks.bookshelf.getDefaultState(), Blocks.bookshelf.getDefaultState(), false);
/*  801 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 7, 1, 4, p_74875_3_);
/*  802 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.oak_stairs, 0)), 7, 1, 3, p_74875_3_);
/*  803 */       var6 = getMetadataWithOffset(Blocks.oak_stairs, 3);
/*  804 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var6), 6, 1, 4, p_74875_3_);
/*  805 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var6), 5, 1, 4, p_74875_3_);
/*  806 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var6), 4, 1, 4, p_74875_3_);
/*  807 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var6), 3, 1, 4, p_74875_3_);
/*  808 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 6, 1, 3, p_74875_3_);
/*  809 */       func_175811_a(worldIn, Blocks.wooden_pressure_plate.getDefaultState(), 6, 2, 3, p_74875_3_);
/*  810 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 1, 3, p_74875_3_);
/*  811 */       func_175811_a(worldIn, Blocks.wooden_pressure_plate.getDefaultState(), 4, 2, 3, p_74875_3_);
/*  812 */       func_175811_a(worldIn, Blocks.crafting_table.getDefaultState(), 7, 1, 1, p_74875_3_);
/*  813 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 1, 0, p_74875_3_);
/*  814 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 2, 0, p_74875_3_);
/*  815 */       func_175810_a(worldIn, p_74875_3_, p_74875_2_, 1, 1, 0, EnumFacing.getHorizontal(getMetadataWithOffset(Blocks.oak_door, 1)));
/*      */       
/*  817 */       if (func_175807_a(worldIn, 1, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && func_175807_a(worldIn, 1, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
/*      */       {
/*  819 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, p_74875_3_);
/*      */       }
/*      */       
/*  822 */       for (int var7 = 0; var7 < 6; var7++) {
/*      */         
/*  824 */         for (int var8 = 0; var8 < 9; var8++) {
/*      */           
/*  826 */           clearCurrentPositionBlocksUpwards(worldIn, var8, 9, var7, p_74875_3_);
/*  827 */           func_175808_b(worldIn, Blocks.cobblestone.getDefaultState(), var8, -1, var7, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  831 */       spawnVillagers(worldIn, p_74875_3_, 2, 1, 2, 1);
/*  832 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     protected int func_180779_c(int p_180779_1_, int p_180779_2_) {
/*  837 */       return 1;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class House2
/*      */     extends Village {
/*  843 */     private static final List villageBlacksmithChestContents = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.apple, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 5), new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.iron_chestplate, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.iron_helmet, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.iron_leggings, 0, 1, 1, 5), new WeightedRandomChestContent((Item)Items.iron_boots, 0, 1, 1, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 3, 7, 5), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.sapling), 0, 3, 7, 5), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 1), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1) });
/*      */     
/*      */     private boolean hasMadeChest;
/*      */     private static final String __OBFID = "CL_00000526";
/*      */     
/*      */     public House2() {}
/*      */     
/*      */     public House2(StructureVillagePieces.Start p_i45563_1_, int p_i45563_2_, Random p_i45563_3_, StructureBoundingBox p_i45563_4_, EnumFacing p_i45563_5_) {
/*  851 */       super(p_i45563_1_, p_i45563_2_);
/*  852 */       this.coordBaseMode = p_i45563_5_;
/*  853 */       this.boundingBox = p_i45563_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     public static House2 func_175855_a(StructureVillagePieces.Start p_175855_0_, List p_175855_1_, Random p_175855_2_, int p_175855_3_, int p_175855_4_, int p_175855_5_, EnumFacing p_175855_6_, int p_175855_7_) {
/*  858 */       StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175855_3_, p_175855_4_, p_175855_5_, 0, 0, 0, 10, 6, 7, p_175855_6_);
/*  859 */       return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(p_175855_1_, var8) == null) ? new House2(p_175855_0_, p_175855_7_, p_175855_2_, var8, p_175855_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/*  864 */       super.writeStructureToNBT(p_143012_1_);
/*  865 */       p_143012_1_.setBoolean("Chest", this.hasMadeChest);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/*  870 */       super.readStructureFromNBT(p_143011_1_);
/*  871 */       this.hasMadeChest = p_143011_1_.getBoolean("Chest");
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  876 */       if (this.field_143015_k < 0) {
/*      */         
/*  878 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/*  880 */         if (this.field_143015_k < 0)
/*      */         {
/*  882 */           return true;
/*      */         }
/*      */         
/*  885 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
/*      */       } 
/*      */       
/*  888 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 9, 4, 6, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  889 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 9, 0, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  890 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 0, 9, 4, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  891 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 9, 5, 6, Blocks.stone_slab.getDefaultState(), Blocks.stone_slab.getDefaultState(), false);
/*  892 */       func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 8, 5, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  893 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 2, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  894 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 4, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  895 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 3, 4, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  896 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 6, 0, 4, 6, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*  897 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 3, 1, p_74875_3_);
/*  898 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 2, 3, 3, 2, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  899 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 3, 5, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  900 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  901 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 6, 5, 3, 6, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  902 */       func_175804_a(worldIn, p_74875_3_, 5, 1, 0, 5, 3, 0, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
/*  903 */       func_175804_a(worldIn, p_74875_3_, 9, 1, 0, 9, 3, 0, Blocks.oak_fence.getDefaultState(), Blocks.oak_fence.getDefaultState(), false);
/*  904 */       func_175804_a(worldIn, p_74875_3_, 6, 1, 4, 9, 4, 6, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  905 */       func_175811_a(worldIn, Blocks.flowing_lava.getDefaultState(), 7, 1, 5, p_74875_3_);
/*  906 */       func_175811_a(worldIn, Blocks.flowing_lava.getDefaultState(), 8, 1, 5, p_74875_3_);
/*  907 */       func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 9, 2, 5, p_74875_3_);
/*  908 */       func_175811_a(worldIn, Blocks.iron_bars.getDefaultState(), 9, 2, 4, p_74875_3_);
/*  909 */       func_175804_a(worldIn, p_74875_3_, 7, 2, 4, 8, 2, 5, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  910 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 6, 1, 3, p_74875_3_);
/*  911 */       func_175811_a(worldIn, Blocks.furnace.getDefaultState(), 6, 2, 3, p_74875_3_);
/*  912 */       func_175811_a(worldIn, Blocks.furnace.getDefaultState(), 6, 3, 3, p_74875_3_);
/*  913 */       func_175811_a(worldIn, Blocks.double_stone_slab.getDefaultState(), 8, 1, 1, p_74875_3_);
/*  914 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
/*  915 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 4, p_74875_3_);
/*  916 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 2, 6, p_74875_3_);
/*  917 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 2, 6, p_74875_3_);
/*  918 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 2, 1, 4, p_74875_3_);
/*  919 */       func_175811_a(worldIn, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 4, p_74875_3_);
/*  920 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 1, 1, 5, p_74875_3_);
/*  921 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.oak_stairs, 3)), 2, 1, 5, p_74875_3_);
/*  922 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.oak_stairs, 1)), 1, 1, 4, p_74875_3_);
/*      */       
/*  924 */       if (!this.hasMadeChest && p_74875_3_.func_175898_b((Vec3i)new BlockPos(getXWithOffset(5, 5), getYWithOffset(1), getZWithOffset(5, 5)))) {
/*      */         
/*  926 */         this.hasMadeChest = true;
/*  927 */         func_180778_a(worldIn, p_74875_3_, p_74875_2_, 5, 1, 5, villageBlacksmithChestContents, 3 + p_74875_2_.nextInt(6));
/*      */       } 
/*      */       
/*      */       int var4;
/*      */       
/*  932 */       for (var4 = 6; var4 <= 8; var4++) {
/*      */         
/*  934 */         if (func_175807_a(worldIn, var4, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && func_175807_a(worldIn, var4, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
/*      */         {
/*  936 */           func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), var4, 0, -1, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/*  940 */       for (var4 = 0; var4 < 7; var4++) {
/*      */         
/*  942 */         for (int var5 = 0; var5 < 10; var5++) {
/*      */           
/*  944 */           clearCurrentPositionBlocksUpwards(worldIn, var5, 6, var4, p_74875_3_);
/*  945 */           func_175808_b(worldIn, Blocks.cobblestone.getDefaultState(), var5, -1, var4, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/*  949 */       spawnVillagers(worldIn, p_74875_3_, 7, 1, 1, 1);
/*  950 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     protected int func_180779_c(int p_180779_1_, int p_180779_2_) {
/*  955 */       return 3;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class House3
/*      */     extends Village
/*      */   {
/*      */     private static final String __OBFID = "CL_00000530";
/*      */     
/*      */     public House3() {}
/*      */     
/*      */     public House3(StructureVillagePieces.Start p_i45561_1_, int p_i45561_2_, Random p_i45561_3_, StructureBoundingBox p_i45561_4_, EnumFacing p_i45561_5_) {
/*  967 */       super(p_i45561_1_, p_i45561_2_);
/*  968 */       this.coordBaseMode = p_i45561_5_;
/*  969 */       this.boundingBox = p_i45561_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     public static House3 func_175849_a(StructureVillagePieces.Start p_175849_0_, List p_175849_1_, Random p_175849_2_, int p_175849_3_, int p_175849_4_, int p_175849_5_, EnumFacing p_175849_6_, int p_175849_7_) {
/*  974 */       StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175849_3_, p_175849_4_, p_175849_5_, 0, 0, 0, 9, 7, 12, p_175849_6_);
/*  975 */       return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(p_175849_1_, var8) == null) ? new House3(p_175849_0_, p_175849_7_, p_175849_2_, var8, p_175849_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/*  980 */       if (this.field_143015_k < 0) {
/*      */         
/*  982 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/*  984 */         if (this.field_143015_k < 0)
/*      */         {
/*  986 */           return true;
/*      */         }
/*      */         
/*  989 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 7 - 1, 0);
/*      */       } 
/*      */       
/*  992 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 7, 4, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  993 */       func_175804_a(worldIn, p_74875_3_, 2, 1, 6, 8, 4, 10, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*  994 */       func_175804_a(worldIn, p_74875_3_, 2, 0, 5, 8, 0, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  995 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 7, 0, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/*  996 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 0, 3, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  997 */       func_175804_a(worldIn, p_74875_3_, 8, 0, 0, 8, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  998 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 0, 7, 2, 0, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*  999 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 5, 2, 1, 5, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/* 1000 */       func_175804_a(worldIn, p_74875_3_, 2, 0, 6, 2, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/* 1001 */       func_175804_a(worldIn, p_74875_3_, 3, 0, 10, 7, 3, 10, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/* 1002 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 0, 7, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1003 */       func_175804_a(worldIn, p_74875_3_, 1, 2, 5, 2, 3, 5, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1004 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 1, 8, 4, 1, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1005 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 4, 3, 4, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1006 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 2, 8, 5, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1007 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 0, 4, 2, p_74875_3_);
/* 1008 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 0, 4, 3, p_74875_3_);
/* 1009 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 4, 2, p_74875_3_);
/* 1010 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 4, 3, p_74875_3_);
/* 1011 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 4, 4, p_74875_3_);
/* 1012 */       int var4 = getMetadataWithOffset(Blocks.oak_stairs, 3);
/* 1013 */       int var5 = getMetadataWithOffset(Blocks.oak_stairs, 2);
/*      */       
/*      */       int var6;
/*      */       
/* 1017 */       for (var6 = -1; var6 <= 2; var6++) {
/*      */         
/* 1019 */         for (int i = 0; i <= 8; i++) {
/*      */           
/* 1021 */           func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var4), i, 4 + var6, var6, p_74875_3_);
/*      */           
/* 1023 */           if ((var6 > -1 || i <= 1) && (var6 > 0 || i <= 3) && (var6 > 1 || i <= 4 || i >= 6))
/*      */           {
/* 1025 */             func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var5), i, 4 + var6, 5 - var6, p_74875_3_);
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/* 1030 */       func_175804_a(worldIn, p_74875_3_, 3, 4, 5, 3, 4, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1031 */       func_175804_a(worldIn, p_74875_3_, 7, 4, 2, 7, 4, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1032 */       func_175804_a(worldIn, p_74875_3_, 4, 5, 4, 4, 5, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1033 */       func_175804_a(worldIn, p_74875_3_, 6, 5, 4, 6, 5, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1034 */       func_175804_a(worldIn, p_74875_3_, 5, 6, 3, 5, 6, 10, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1035 */       var6 = getMetadataWithOffset(Blocks.oak_stairs, 0);
/*      */       
/*      */       int var7;
/* 1038 */       for (var7 = 4; var7 >= 1; var7--) {
/*      */         
/* 1040 */         func_175811_a(worldIn, Blocks.planks.getDefaultState(), var7, 2 + var7, 7 - var7, p_74875_3_);
/*      */         
/* 1042 */         for (int i = 8 - var7; i <= 10; i++)
/*      */         {
/* 1044 */           func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var6), var7, 2 + var7, i, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/* 1048 */       var7 = getMetadataWithOffset(Blocks.oak_stairs, 1);
/* 1049 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 6, 6, 3, p_74875_3_);
/* 1050 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 7, 5, 4, p_74875_3_);
/* 1051 */       func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var7), 6, 6, 4, p_74875_3_);
/*      */       
/*      */       int var8;
/* 1054 */       for (var8 = 6; var8 <= 8; var8++) {
/*      */         
/* 1056 */         for (int var9 = 5; var9 <= 10; var9++)
/*      */         {
/* 1058 */           func_175811_a(worldIn, Blocks.oak_stairs.getStateFromMeta(var7), var8, 12 - var8, var9, p_74875_3_);
/*      */         }
/*      */       } 
/*      */       
/* 1062 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 0, 2, 1, p_74875_3_);
/* 1063 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 0, 2, 4, p_74875_3_);
/* 1064 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
/* 1065 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 3, p_74875_3_);
/* 1066 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 4, 2, 0, p_74875_3_);
/* 1067 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 5, 2, 0, p_74875_3_);
/* 1068 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 6, 2, 0, p_74875_3_);
/* 1069 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 8, 2, 1, p_74875_3_);
/* 1070 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 2, p_74875_3_);
/* 1071 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 3, p_74875_3_);
/* 1072 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 8, 2, 4, p_74875_3_);
/* 1073 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 8, 2, 5, p_74875_3_);
/* 1074 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 8, 2, 6, p_74875_3_);
/* 1075 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 7, p_74875_3_);
/* 1076 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 8, 2, 8, p_74875_3_);
/* 1077 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 8, 2, 9, p_74875_3_);
/* 1078 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 2, 6, p_74875_3_);
/* 1079 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 2, 7, p_74875_3_);
/* 1080 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 2, 8, p_74875_3_);
/* 1081 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 2, 9, p_74875_3_);
/* 1082 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 4, 4, 10, p_74875_3_);
/* 1083 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 5, 4, 10, p_74875_3_);
/* 1084 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 6, 4, 10, p_74875_3_);
/* 1085 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 5, 5, 10, p_74875_3_);
/* 1086 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 1, 0, p_74875_3_);
/* 1087 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 2, 0, p_74875_3_);
/* 1088 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode), 2, 3, 1, p_74875_3_);
/* 1089 */       func_175810_a(worldIn, p_74875_3_, p_74875_2_, 2, 1, 0, EnumFacing.getHorizontal(getMetadataWithOffset(Blocks.oak_door, 1)));
/* 1090 */       func_175804_a(worldIn, p_74875_3_, 1, 0, -1, 3, 2, -1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       
/* 1092 */       if (func_175807_a(worldIn, 2, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && func_175807_a(worldIn, 2, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
/*      */       {
/* 1094 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, p_74875_3_);
/*      */       }
/*      */       
/* 1097 */       for (var8 = 0; var8 < 5; var8++) {
/*      */         
/* 1099 */         for (int var9 = 0; var9 < 9; var9++) {
/*      */           
/* 1101 */           clearCurrentPositionBlocksUpwards(worldIn, var9, 7, var8, p_74875_3_);
/* 1102 */           func_175808_b(worldIn, Blocks.cobblestone.getDefaultState(), var9, -1, var8, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/* 1106 */       for (var8 = 5; var8 < 11; var8++) {
/*      */         
/* 1108 */         for (int var9 = 2; var9 < 9; var9++) {
/*      */           
/* 1110 */           clearCurrentPositionBlocksUpwards(worldIn, var9, 7, var8, p_74875_3_);
/* 1111 */           func_175808_b(worldIn, Blocks.cobblestone.getDefaultState(), var9, -1, var8, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/* 1115 */       spawnVillagers(worldIn, p_74875_3_, 4, 1, 2, 2);
/* 1116 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class House4Garden
/*      */     extends Village
/*      */   {
/*      */     private boolean isRoofAccessible;
/*      */     private static final String __OBFID = "CL_00000523";
/*      */     
/*      */     public House4Garden() {}
/*      */     
/*      */     public House4Garden(StructureVillagePieces.Start p_i45566_1_, int p_i45566_2_, Random p_i45566_3_, StructureBoundingBox p_i45566_4_, EnumFacing p_i45566_5_) {
/* 1129 */       super(p_i45566_1_, p_i45566_2_);
/* 1130 */       this.coordBaseMode = p_i45566_5_;
/* 1131 */       this.boundingBox = p_i45566_4_;
/* 1132 */       this.isRoofAccessible = p_i45566_3_.nextBoolean();
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1137 */       super.writeStructureToNBT(p_143012_1_);
/* 1138 */       p_143012_1_.setBoolean("Terrace", this.isRoofAccessible);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1143 */       super.readStructureFromNBT(p_143011_1_);
/* 1144 */       this.isRoofAccessible = p_143011_1_.getBoolean("Terrace");
/*      */     }
/*      */ 
/*      */     
/*      */     public static House4Garden func_175858_a(StructureVillagePieces.Start p_175858_0_, List p_175858_1_, Random p_175858_2_, int p_175858_3_, int p_175858_4_, int p_175858_5_, EnumFacing p_175858_6_, int p_175858_7_) {
/* 1149 */       StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175858_3_, p_175858_4_, p_175858_5_, 0, 0, 0, 5, 6, 5, p_175858_6_);
/* 1150 */       return (StructureComponent.findIntersecting(p_175858_1_, var8) != null) ? null : new House4Garden(p_175858_0_, p_175858_7_, p_175858_2_, var8, p_175858_6_);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1155 */       if (this.field_143015_k < 0) {
/*      */         
/* 1157 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/* 1159 */         if (this.field_143015_k < 0)
/*      */         {
/* 1161 */           return true;
/*      */         }
/*      */         
/* 1164 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
/*      */       } 
/*      */       
/* 1167 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 4, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/* 1168 */       func_175804_a(worldIn, p_74875_3_, 0, 4, 0, 4, 4, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 1169 */       func_175804_a(worldIn, p_74875_3_, 1, 4, 1, 3, 4, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1170 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 0, 1, 0, p_74875_3_);
/* 1171 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 0, 2, 0, p_74875_3_);
/* 1172 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 0, 3, 0, p_74875_3_);
/* 1173 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 1, 0, p_74875_3_);
/* 1174 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 2, 0, p_74875_3_);
/* 1175 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 3, 0, p_74875_3_);
/* 1176 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 0, 1, 4, p_74875_3_);
/* 1177 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 0, 2, 4, p_74875_3_);
/* 1178 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 0, 3, 4, p_74875_3_);
/* 1179 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 1, 4, p_74875_3_);
/* 1180 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 2, 4, p_74875_3_);
/* 1181 */       func_175811_a(worldIn, Blocks.cobblestone.getDefaultState(), 4, 3, 4, p_74875_3_);
/* 1182 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1183 */       func_175804_a(worldIn, p_74875_3_, 4, 1, 1, 4, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1184 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 4, 3, 3, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1185 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
/* 1186 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 2, 2, 4, p_74875_3_);
/* 1187 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 4, 2, 2, p_74875_3_);
/* 1188 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 1, 1, 0, p_74875_3_);
/* 1189 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 1, 2, 0, p_74875_3_);
/* 1190 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 1, 3, 0, p_74875_3_);
/* 1191 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 2, 3, 0, p_74875_3_);
/* 1192 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 3, 0, p_74875_3_);
/* 1193 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 2, 0, p_74875_3_);
/* 1194 */       func_175811_a(worldIn, Blocks.planks.getDefaultState(), 3, 1, 0, p_74875_3_);
/*      */       
/* 1196 */       if (func_175807_a(worldIn, 2, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && func_175807_a(worldIn, 2, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
/*      */       {
/* 1198 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), 2, 0, -1, p_74875_3_);
/*      */       }
/*      */       
/* 1201 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 3, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*      */       
/* 1203 */       if (this.isRoofAccessible) {
/*      */         
/* 1205 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 0, 5, 0, p_74875_3_);
/* 1206 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 5, 0, p_74875_3_);
/* 1207 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 2, 5, 0, p_74875_3_);
/* 1208 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 3, 5, 0, p_74875_3_);
/* 1209 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 5, 0, p_74875_3_);
/* 1210 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 0, 5, 4, p_74875_3_);
/* 1211 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 5, 4, p_74875_3_);
/* 1212 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 2, 5, 4, p_74875_3_);
/* 1213 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 3, 5, 4, p_74875_3_);
/* 1214 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 5, 4, p_74875_3_);
/* 1215 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 5, 1, p_74875_3_);
/* 1216 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 5, 2, p_74875_3_);
/* 1217 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 5, 3, p_74875_3_);
/* 1218 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 0, 5, 1, p_74875_3_);
/* 1219 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 0, 5, 2, p_74875_3_);
/* 1220 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 0, 5, 3, p_74875_3_);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1225 */       if (this.isRoofAccessible) {
/*      */         
/* 1227 */         int i = getMetadataWithOffset(Blocks.ladder, 3);
/* 1228 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 1, 3, p_74875_3_);
/* 1229 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 2, 3, p_74875_3_);
/* 1230 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 3, 3, p_74875_3_);
/* 1231 */         func_175811_a(worldIn, Blocks.ladder.getStateFromMeta(i), 3, 4, 3, p_74875_3_);
/*      */       } 
/*      */       
/* 1234 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode), 2, 3, 1, p_74875_3_);
/*      */       
/* 1236 */       for (int var4 = 0; var4 < 5; var4++) {
/*      */         
/* 1238 */         for (int var5 = 0; var5 < 5; var5++) {
/*      */           
/* 1240 */           clearCurrentPositionBlocksUpwards(worldIn, var5, 6, var4, p_74875_3_);
/* 1241 */           func_175808_b(worldIn, Blocks.cobblestone.getDefaultState(), var5, -1, var4, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/* 1245 */       spawnVillagers(worldIn, p_74875_3_, 1, 1, 2, 1);
/* 1246 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Path
/*      */     extends Road
/*      */   {
/*      */     private int averageGroundLevel;
/*      */     private static final String __OBFID = "CL_00000528";
/*      */     
/*      */     public Path() {}
/*      */     
/*      */     public Path(StructureVillagePieces.Start p_i45562_1_, int p_i45562_2_, Random p_i45562_3_, StructureBoundingBox p_i45562_4_, EnumFacing p_i45562_5_) {
/* 1259 */       super(p_i45562_1_, p_i45562_2_);
/* 1260 */       this.coordBaseMode = p_i45562_5_;
/* 1261 */       this.boundingBox = p_i45562_4_;
/* 1262 */       this.averageGroundLevel = Math.max(p_i45562_4_.getXSize(), p_i45562_4_.getZSize());
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1267 */       super.writeStructureToNBT(p_143012_1_);
/* 1268 */       p_143012_1_.setInteger("Length", this.averageGroundLevel);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1273 */       super.readStructureFromNBT(p_143011_1_);
/* 1274 */       this.averageGroundLevel = p_143011_1_.getInteger("Length");
/*      */     }
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 1279 */       boolean var4 = false;
/*      */       
/*      */       int var5;
/*      */       
/* 1283 */       for (var5 = p_74861_3_.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + p_74861_3_.nextInt(5)) {
/*      */         
/* 1285 */         StructureComponent var6 = getNextComponentNN((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, var5);
/*      */         
/* 1287 */         if (var6 != null) {
/*      */           
/* 1289 */           var5 += Math.max(var6.boundingBox.getXSize(), var6.boundingBox.getZSize());
/* 1290 */           var4 = true;
/*      */         } 
/*      */       } 
/*      */       
/* 1294 */       for (var5 = p_74861_3_.nextInt(5); var5 < this.averageGroundLevel - 8; var5 += 2 + p_74861_3_.nextInt(5)) {
/*      */         
/* 1296 */         StructureComponent var6 = getNextComponentPP((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, 0, var5);
/*      */         
/* 1298 */         if (var6 != null) {
/*      */           
/* 1300 */           var5 += Math.max(var6.boundingBox.getXSize(), var6.boundingBox.getZSize());
/* 1301 */           var4 = true;
/*      */         } 
/*      */       } 
/*      */       
/* 1305 */       if (var4 && p_74861_3_.nextInt(3) > 0 && this.coordBaseMode != null)
/*      */       {
/* 1307 */         switch (StructureVillagePieces.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1310 */             StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, getComponentType());
/*      */             break;
/*      */           
/*      */           case 2:
/* 1314 */             StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.WEST, getComponentType());
/*      */             break;
/*      */           
/*      */           case 3:
/* 1318 */             StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, getComponentType());
/*      */             break;
/*      */           
/*      */           case 4:
/* 1322 */             StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, getComponentType());
/*      */             break;
/*      */         } 
/*      */       }
/* 1326 */       if (var4 && p_74861_3_.nextInt(3) > 0 && this.coordBaseMode != null)
/*      */       {
/* 1328 */         switch (StructureVillagePieces.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1331 */             StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, getComponentType());
/*      */             break;
/*      */           
/*      */           case 2:
/* 1335 */             StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 2, EnumFacing.EAST, getComponentType());
/*      */             break;
/*      */           
/*      */           case 3:
/* 1339 */             StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, getComponentType());
/*      */             break;
/*      */           
/*      */           case 4:
/* 1343 */             StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 2, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, getComponentType());
/*      */             break;
/*      */         } 
/*      */       }
/*      */     }
/*      */     
/*      */     public static StructureBoundingBox func_175848_a(StructureVillagePieces.Start p_175848_0_, List p_175848_1_, Random p_175848_2_, int p_175848_3_, int p_175848_4_, int p_175848_5_, EnumFacing p_175848_6_) {
/* 1350 */       for (int var7 = 7 * MathHelper.getRandomIntegerInRange(p_175848_2_, 3, 5); var7 >= 7; var7 -= 7) {
/*      */         
/* 1352 */         StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175848_3_, p_175848_4_, p_175848_5_, 0, 0, 0, 3, 3, var7, p_175848_6_);
/*      */         
/* 1354 */         if (StructureComponent.findIntersecting(p_175848_1_, var8) == null)
/*      */         {
/* 1356 */           return var8;
/*      */         }
/*      */       } 
/*      */       
/* 1360 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1365 */       IBlockState var4 = func_175847_a(Blocks.gravel.getDefaultState());
/* 1366 */       IBlockState var5 = func_175847_a(Blocks.cobblestone.getDefaultState());
/*      */       
/* 1368 */       for (int var6 = this.boundingBox.minX; var6 <= this.boundingBox.maxX; var6++) {
/*      */         
/* 1370 */         for (int var7 = this.boundingBox.minZ; var7 <= this.boundingBox.maxZ; var7++) {
/*      */           
/* 1372 */           BlockPos var8 = new BlockPos(var6, 64, var7);
/*      */           
/* 1374 */           if (p_74875_3_.func_175898_b((Vec3i)var8)) {
/*      */             
/* 1376 */             var8 = worldIn.func_175672_r(var8).offsetDown();
/* 1377 */             worldIn.setBlockState(var8, var4, 2);
/* 1378 */             worldIn.setBlockState(var8.offsetDown(), var5, 2);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1383 */       return true;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class PieceWeight
/*      */   {
/*      */     public Class villagePieceClass;
/*      */     public final int villagePieceWeight;
/*      */     public int villagePiecesSpawned;
/*      */     public int villagePiecesLimit;
/*      */     private static final String __OBFID = "CL_00000521";
/*      */     
/*      */     public PieceWeight(Class p_i2098_1_, int p_i2098_2_, int p_i2098_3_) {
/* 1397 */       this.villagePieceClass = p_i2098_1_;
/* 1398 */       this.villagePieceWeight = p_i2098_2_;
/* 1399 */       this.villagePiecesLimit = p_i2098_3_;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canSpawnMoreVillagePiecesOfType(int p_75085_1_) {
/* 1404 */       return !(this.villagePiecesLimit != 0 && this.villagePiecesSpawned >= this.villagePiecesLimit);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canSpawnMoreVillagePieces() {
/* 1409 */       return !(this.villagePiecesLimit != 0 && this.villagePiecesSpawned >= this.villagePiecesLimit);
/*      */     }
/*      */   }
/*      */   
/*      */   public static abstract class Road
/*      */     extends Village
/*      */   {
/*      */     private static final String __OBFID = "CL_00000532";
/*      */     
/*      */     public Road() {}
/*      */     
/*      */     protected Road(StructureVillagePieces.Start p_i2108_1_, int p_i2108_2_) {
/* 1421 */       super(p_i2108_1_, p_i2108_2_);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Start
/*      */     extends Well {
/*      */     public WorldChunkManager worldChunkMngr;
/*      */     public boolean inDesert;
/*      */     public int terrainType;
/*      */     public StructureVillagePieces.PieceWeight structVillagePieceWeight;
/*      */     public List structureVillageWeightedPieceList;
/* 1432 */     public List field_74932_i = Lists.newArrayList();
/* 1433 */     public List field_74930_j = Lists.newArrayList();
/*      */     
/*      */     private static final String __OBFID = "CL_00000527";
/*      */     
/*      */     public Start() {}
/*      */     
/*      */     public Start(WorldChunkManager p_i2104_1_, int p_i2104_2_, Random p_i2104_3_, int p_i2104_4_, int p_i2104_5_, List p_i2104_6_, int p_i2104_7_) {
/* 1440 */       super((Start)null, 0, p_i2104_3_, p_i2104_4_, p_i2104_5_);
/* 1441 */       this.worldChunkMngr = p_i2104_1_;
/* 1442 */       this.structureVillageWeightedPieceList = p_i2104_6_;
/* 1443 */       this.terrainType = p_i2104_7_;
/* 1444 */       BiomeGenBase var8 = p_i2104_1_.func_180300_a(new BlockPos(p_i2104_4_, 0, p_i2104_5_), BiomeGenBase.field_180279_ad);
/* 1445 */       this.inDesert = !(var8 != BiomeGenBase.desert && var8 != BiomeGenBase.desertHills);
/* 1446 */       func_175846_a(this.inDesert);
/*      */     }
/*      */ 
/*      */     
/*      */     public WorldChunkManager getWorldChunkManager() {
/* 1451 */       return this.worldChunkMngr;
/*      */     }
/*      */   }
/*      */   
/*      */   static final class SwitchEnumFacing
/*      */   {
/* 1457 */     static final int[] field_176064_a = new int[(EnumFacing.values()).length];
/*      */     
/*      */     private static final String __OBFID = "CL_00001968";
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 1464 */         field_176064_a[EnumFacing.NORTH.ordinal()] = 1;
/*      */       }
/* 1466 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1473 */         field_176064_a[EnumFacing.SOUTH.ordinal()] = 2;
/*      */       }
/* 1475 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1482 */         field_176064_a[EnumFacing.WEST.ordinal()] = 3;
/*      */       }
/* 1484 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1491 */         field_176064_a[EnumFacing.EAST.ordinal()] = 4;
/*      */       }
/* 1493 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class Torch
/*      */     extends Village
/*      */   {
/*      */     private static final String __OBFID = "CL_00000520";
/*      */ 
/*      */     
/*      */     public Torch() {}
/*      */ 
/*      */     
/*      */     public Torch(StructureVillagePieces.Start p_i45568_1_, int p_i45568_2_, Random p_i45568_3_, StructureBoundingBox p_i45568_4_, EnumFacing p_i45568_5_) {
/* 1508 */       super(p_i45568_1_, p_i45568_2_);
/* 1509 */       this.coordBaseMode = p_i45568_5_;
/* 1510 */       this.boundingBox = p_i45568_4_;
/*      */     }
/*      */ 
/*      */     
/*      */     public static StructureBoundingBox func_175856_a(StructureVillagePieces.Start p_175856_0_, List p_175856_1_, Random p_175856_2_, int p_175856_3_, int p_175856_4_, int p_175856_5_, EnumFacing p_175856_6_) {
/* 1515 */       StructureBoundingBox var7 = StructureBoundingBox.func_175897_a(p_175856_3_, p_175856_4_, p_175856_5_, 0, 0, 0, 3, 4, 2, p_175856_6_);
/* 1516 */       return (StructureComponent.findIntersecting(p_175856_1_, var7) != null) ? null : var7;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1521 */       if (this.field_143015_k < 0) {
/*      */         
/* 1523 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/* 1525 */         if (this.field_143015_k < 0)
/*      */         {
/* 1527 */           return true;
/*      */         }
/*      */         
/* 1530 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 4 - 1, 0);
/*      */       } 
/*      */       
/* 1533 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 2, 3, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 1534 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 0, 0, p_74875_3_);
/* 1535 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 1, 0, p_74875_3_);
/* 1536 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 2, 0, p_74875_3_);
/* 1537 */       func_175811_a(worldIn, Blocks.wool.getStateFromMeta(EnumDyeColor.WHITE.getDyeColorDamage()), 1, 3, 0, p_74875_3_);
/* 1538 */       boolean var4 = !(this.coordBaseMode != EnumFacing.EAST && this.coordBaseMode != EnumFacing.NORTH);
/* 1539 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode.rotateY()), var4 ? 2 : 0, 3, 0, p_74875_3_);
/* 1540 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode), 1, 3, 1, p_74875_3_);
/* 1541 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode.rotateYCCW()), var4 ? 0 : 2, 3, 0, p_74875_3_);
/* 1542 */       func_175811_a(worldIn, Blocks.torch.getDefaultState().withProperty((IProperty)BlockTorch.FACING_PROP, (Comparable)this.coordBaseMode.getOpposite()), 1, 3, -1, p_74875_3_);
/* 1543 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   static abstract class Village
/*      */     extends StructureComponent {
/* 1549 */     protected int field_143015_k = -1;
/*      */     
/*      */     private int villagersSpawned;
/*      */     private boolean field_143014_b;
/*      */     private static final String __OBFID = "CL_00000531";
/*      */     
/*      */     public Village() {}
/*      */     
/*      */     protected Village(StructureVillagePieces.Start p_i2107_1_, int p_i2107_2_) {
/* 1558 */       super(p_i2107_2_);
/*      */       
/* 1560 */       if (p_i2107_1_ != null)
/*      */       {
/* 1562 */         this.field_143014_b = p_i2107_1_.inDesert;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1568 */       p_143012_1_.setInteger("HPos", this.field_143015_k);
/* 1569 */       p_143012_1_.setInteger("VCount", this.villagersSpawned);
/* 1570 */       p_143012_1_.setBoolean("Desert", this.field_143014_b);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1575 */       this.field_143015_k = p_143011_1_.getInteger("HPos");
/* 1576 */       this.villagersSpawned = p_143011_1_.getInteger("VCount");
/* 1577 */       this.field_143014_b = p_143011_1_.getBoolean("Desert");
/*      */     }
/*      */ 
/*      */     
/*      */     protected StructureComponent getNextComponentNN(StructureVillagePieces.Start p_74891_1_, List p_74891_2_, Random p_74891_3_, int p_74891_4_, int p_74891_5_) {
/* 1582 */       if (this.coordBaseMode != null)
/*      */       {
/* 1584 */         switch (StructureVillagePieces.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1587 */             return StructureVillagePieces.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, getComponentType());
/*      */           
/*      */           case 2:
/* 1590 */             return StructureVillagePieces.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, getComponentType());
/*      */           
/*      */           case 3:
/* 1593 */             return StructureVillagePieces.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, getComponentType());
/*      */           
/*      */           case 4:
/* 1596 */             return StructureVillagePieces.func_176066_d(p_74891_1_, p_74891_2_, p_74891_3_, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, getComponentType());
/*      */         } 
/*      */       
/*      */       }
/* 1600 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected StructureComponent getNextComponentPP(StructureVillagePieces.Start p_74894_1_, List p_74894_2_, Random p_74894_3_, int p_74894_4_, int p_74894_5_) {
/* 1605 */       if (this.coordBaseMode != null)
/*      */       {
/* 1607 */         switch (StructureVillagePieces.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()]) {
/*      */           
/*      */           case 1:
/* 1610 */             return StructureVillagePieces.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, getComponentType());
/*      */           
/*      */           case 2:
/* 1613 */             return StructureVillagePieces.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, getComponentType());
/*      */           
/*      */           case 3:
/* 1616 */             return StructureVillagePieces.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, getComponentType());
/*      */           
/*      */           case 4:
/* 1619 */             return StructureVillagePieces.func_176066_d(p_74894_1_, p_74894_2_, p_74894_3_, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, getComponentType());
/*      */         } 
/*      */       
/*      */       }
/* 1623 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     protected int getAverageGroundLevel(World worldIn, StructureBoundingBox p_74889_2_) {
/* 1628 */       int var3 = 0;
/* 1629 */       int var4 = 0;
/*      */       
/* 1631 */       for (int var5 = this.boundingBox.minZ; var5 <= this.boundingBox.maxZ; var5++) {
/*      */         
/* 1633 */         for (int var6 = this.boundingBox.minX; var6 <= this.boundingBox.maxX; var6++) {
/*      */           
/* 1635 */           BlockPos var7 = new BlockPos(var6, 64, var5);
/*      */           
/* 1637 */           if (p_74889_2_.func_175898_b((Vec3i)var7)) {
/*      */             
/* 1639 */             var3 += Math.max(worldIn.func_175672_r(var7).getY(), worldIn.provider.getAverageGroundLevel());
/* 1640 */             var4++;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1645 */       if (var4 == 0)
/*      */       {
/* 1647 */         return -1;
/*      */       }
/*      */ 
/*      */       
/* 1651 */       return var3 / var4;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected static boolean canVillageGoDeeper(StructureBoundingBox p_74895_0_) {
/* 1657 */       return (p_74895_0_ != null && p_74895_0_.minY > 10);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void spawnVillagers(World worldIn, StructureBoundingBox p_74893_2_, int p_74893_3_, int p_74893_4_, int p_74893_5_, int p_74893_6_) {
/* 1662 */       if (this.villagersSpawned < p_74893_6_)
/*      */       {
/* 1664 */         for (int var7 = this.villagersSpawned; var7 < p_74893_6_; var7++) {
/*      */           
/* 1666 */           int var8 = getXWithOffset(p_74893_3_ + var7, p_74893_5_);
/* 1667 */           int var9 = getYWithOffset(p_74893_4_);
/* 1668 */           int var10 = getZWithOffset(p_74893_3_ + var7, p_74893_5_);
/*      */           
/* 1670 */           if (!p_74893_2_.func_175898_b((Vec3i)new BlockPos(var8, var9, var10))) {
/*      */             break;
/*      */           }
/*      */ 
/*      */           
/* 1675 */           this.villagersSpawned++;
/* 1676 */           EntityVillager var11 = new EntityVillager(worldIn);
/* 1677 */           var11.setLocationAndAngles(var8 + 0.5D, var9, var10 + 0.5D, 0.0F, 0.0F);
/* 1678 */           var11.func_180482_a(worldIn.getDifficultyForLocation(new BlockPos((Entity)var11)), null);
/* 1679 */           var11.setProfession(func_180779_c(var7, var11.getProfession()));
/* 1680 */           worldIn.spawnEntityInWorld((Entity)var11);
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected int func_180779_c(int p_180779_1_, int p_180779_2_) {
/* 1687 */       return p_180779_2_;
/*      */     }
/*      */ 
/*      */     
/*      */     protected IBlockState func_175847_a(IBlockState p_175847_1_) {
/* 1692 */       if (this.field_143014_b) {
/*      */         
/* 1694 */         if (p_175847_1_.getBlock() == Blocks.log || p_175847_1_.getBlock() == Blocks.log2)
/*      */         {
/* 1696 */           return Blocks.sandstone.getDefaultState();
/*      */         }
/*      */         
/* 1699 */         if (p_175847_1_.getBlock() == Blocks.cobblestone)
/*      */         {
/* 1701 */           return Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.func_176675_a());
/*      */         }
/*      */         
/* 1704 */         if (p_175847_1_.getBlock() == Blocks.planks)
/*      */         {
/* 1706 */           return Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.func_176675_a());
/*      */         }
/*      */         
/* 1709 */         if (p_175847_1_.getBlock() == Blocks.oak_stairs)
/*      */         {
/* 1711 */           return Blocks.sandstone_stairs.getDefaultState().withProperty((IProperty)BlockStairs.FACING, p_175847_1_.getValue((IProperty)BlockStairs.FACING));
/*      */         }
/*      */         
/* 1714 */         if (p_175847_1_.getBlock() == Blocks.stone_stairs)
/*      */         {
/* 1716 */           return Blocks.sandstone_stairs.getDefaultState().withProperty((IProperty)BlockStairs.FACING, p_175847_1_.getValue((IProperty)BlockStairs.FACING));
/*      */         }
/*      */         
/* 1719 */         if (p_175847_1_.getBlock() == Blocks.gravel)
/*      */         {
/* 1721 */           return Blocks.sandstone.getDefaultState();
/*      */         }
/*      */       } 
/*      */       
/* 1725 */       return p_175847_1_;
/*      */     }
/*      */ 
/*      */     
/*      */     protected void func_175811_a(World worldIn, IBlockState p_175811_2_, int p_175811_3_, int p_175811_4_, int p_175811_5_, StructureBoundingBox p_175811_6_) {
/* 1730 */       IBlockState var7 = func_175847_a(p_175811_2_);
/* 1731 */       super.func_175811_a(worldIn, var7, p_175811_3_, p_175811_4_, p_175811_5_, p_175811_6_);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void func_175804_a(World worldIn, StructureBoundingBox p_175804_2_, int p_175804_3_, int p_175804_4_, int p_175804_5_, int p_175804_6_, int p_175804_7_, int p_175804_8_, IBlockState p_175804_9_, IBlockState p_175804_10_, boolean p_175804_11_) {
/* 1736 */       IBlockState var12 = func_175847_a(p_175804_9_);
/* 1737 */       IBlockState var13 = func_175847_a(p_175804_10_);
/* 1738 */       super.func_175804_a(worldIn, p_175804_2_, p_175804_3_, p_175804_4_, p_175804_5_, p_175804_6_, p_175804_7_, p_175804_8_, var12, var13, p_175804_11_);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void func_175808_b(World worldIn, IBlockState p_175808_2_, int p_175808_3_, int p_175808_4_, int p_175808_5_, StructureBoundingBox p_175808_6_) {
/* 1743 */       IBlockState var7 = func_175847_a(p_175808_2_);
/* 1744 */       super.func_175808_b(worldIn, var7, p_175808_3_, p_175808_4_, p_175808_5_, p_175808_6_);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void func_175846_a(boolean p_175846_1_) {
/* 1749 */       this.field_143014_b = p_175846_1_;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Well
/*      */     extends Village
/*      */   {
/*      */     private static final String __OBFID = "CL_00000533";
/*      */     
/*      */     public Well() {}
/*      */     
/*      */     public Well(StructureVillagePieces.Start p_i2109_1_, int p_i2109_2_, Random p_i2109_3_, int p_i2109_4_, int p_i2109_5_) {
/* 1761 */       super(p_i2109_1_, p_i2109_2_);
/* 1762 */       this.coordBaseMode = EnumFacing.Plane.HORIZONTAL.random(p_i2109_3_);
/*      */       
/* 1764 */       switch (StructureVillagePieces.SwitchEnumFacing.field_176064_a[this.coordBaseMode.ordinal()]) {
/*      */         
/*      */         case 1:
/*      */         case 2:
/* 1768 */           this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
/*      */           return;
/*      */       } 
/*      */       
/* 1772 */       this.boundingBox = new StructureBoundingBox(p_i2109_4_, 64, p_i2109_5_, p_i2109_4_ + 6 - 1, 78, p_i2109_5_ + 6 - 1);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 1778 */       StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.WEST, getComponentType());
/* 1779 */       StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ + 1, EnumFacing.EAST, getComponentType());
/* 1780 */       StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.minZ - 1, EnumFacing.NORTH, getComponentType());
/* 1781 */       StructureVillagePieces.func_176069_e((StructureVillagePieces.Start)p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 4, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, getComponentType());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1786 */       if (this.field_143015_k < 0) {
/*      */         
/* 1788 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/* 1790 */         if (this.field_143015_k < 0)
/*      */         {
/* 1792 */           return true;
/*      */         }
/*      */         
/* 1795 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 3, 0);
/*      */       } 
/*      */       
/* 1798 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 4, 12, 4, Blocks.cobblestone.getDefaultState(), Blocks.flowing_water.getDefaultState(), false);
/* 1799 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 12, 2, p_74875_3_);
/* 1800 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 3, 12, 2, p_74875_3_);
/* 1801 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 2, 12, 3, p_74875_3_);
/* 1802 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 3, 12, 3, p_74875_3_);
/* 1803 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 13, 1, p_74875_3_);
/* 1804 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 14, 1, p_74875_3_);
/* 1805 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 13, 1, p_74875_3_);
/* 1806 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 14, 1, p_74875_3_);
/* 1807 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 13, 4, p_74875_3_);
/* 1808 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 1, 14, 4, p_74875_3_);
/* 1809 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 13, 4, p_74875_3_);
/* 1810 */       func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), 4, 14, 4, p_74875_3_);
/* 1811 */       func_175804_a(worldIn, p_74875_3_, 1, 15, 1, 4, 15, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/*      */       
/* 1813 */       for (int var4 = 0; var4 <= 5; var4++) {
/*      */         
/* 1815 */         for (int var5 = 0; var5 <= 5; var5++) {
/*      */           
/* 1817 */           if (var5 == 0 || var5 == 5 || var4 == 0 || var4 == 5) {
/*      */             
/* 1819 */             func_175811_a(worldIn, Blocks.gravel.getDefaultState(), var5, 11, var4, p_74875_3_);
/* 1820 */             clearCurrentPositionBlocksUpwards(worldIn, var5, 12, var4, p_74875_3_);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1825 */       return true;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class WoodHut
/*      */     extends Village
/*      */   {
/*      */     private boolean isTallHouse;
/*      */     private int tablePosition;
/*      */     private static final String __OBFID = "CL_00000524";
/*      */     
/*      */     public WoodHut() {}
/*      */     
/*      */     public WoodHut(StructureVillagePieces.Start p_i45565_1_, int p_i45565_2_, Random p_i45565_3_, StructureBoundingBox p_i45565_4_, EnumFacing p_i45565_5_) {
/* 1839 */       super(p_i45565_1_, p_i45565_2_);
/* 1840 */       this.coordBaseMode = p_i45565_5_;
/* 1841 */       this.boundingBox = p_i45565_4_;
/* 1842 */       this.isTallHouse = p_i45565_3_.nextBoolean();
/* 1843 */       this.tablePosition = p_i45565_3_.nextInt(3);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 1848 */       super.writeStructureToNBT(p_143012_1_);
/* 1849 */       p_143012_1_.setInteger("T", this.tablePosition);
/* 1850 */       p_143012_1_.setBoolean("C", this.isTallHouse);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 1855 */       super.readStructureFromNBT(p_143011_1_);
/* 1856 */       this.tablePosition = p_143011_1_.getInteger("T");
/* 1857 */       this.isTallHouse = p_143011_1_.getBoolean("C");
/*      */     }
/*      */ 
/*      */     
/*      */     public static WoodHut func_175853_a(StructureVillagePieces.Start p_175853_0_, List p_175853_1_, Random p_175853_2_, int p_175853_3_, int p_175853_4_, int p_175853_5_, EnumFacing p_175853_6_, int p_175853_7_) {
/* 1862 */       StructureBoundingBox var8 = StructureBoundingBox.func_175897_a(p_175853_3_, p_175853_4_, p_175853_5_, 0, 0, 0, 4, 6, 5, p_175853_6_);
/* 1863 */       return (canVillageGoDeeper(var8) && StructureComponent.findIntersecting(p_175853_1_, var8) == null) ? new WoodHut(p_175853_0_, p_175853_7_, p_175853_2_, var8, p_175853_6_) : null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 1868 */       if (this.field_143015_k < 0) {
/*      */         
/* 1870 */         this.field_143015_k = getAverageGroundLevel(worldIn, p_74875_3_);
/*      */         
/* 1872 */         if (this.field_143015_k < 0)
/*      */         {
/* 1874 */           return true;
/*      */         }
/*      */         
/* 1877 */         this.boundingBox.offset(0, this.field_143015_k - this.boundingBox.maxY + 6 - 1, 0);
/*      */       } 
/*      */       
/* 1880 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 1, 3, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 1881 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 3, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
/* 1882 */       func_175804_a(worldIn, p_74875_3_, 1, 0, 1, 2, 0, 3, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
/*      */       
/* 1884 */       if (this.isTallHouse) {
/*      */         
/* 1886 */         func_175804_a(worldIn, p_74875_3_, 1, 4, 1, 2, 4, 3, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*      */       }
/*      */       else {
/*      */         
/* 1890 */         func_175804_a(worldIn, p_74875_3_, 1, 5, 1, 2, 5, 3, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/*      */       } 
/*      */       
/* 1893 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 1, 4, 0, p_74875_3_);
/* 1894 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 4, 0, p_74875_3_);
/* 1895 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 1, 4, 4, p_74875_3_);
/* 1896 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 2, 4, 4, p_74875_3_);
/* 1897 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 0, 4, 1, p_74875_3_);
/* 1898 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 0, 4, 2, p_74875_3_);
/* 1899 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 0, 4, 3, p_74875_3_);
/* 1900 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 3, 4, 1, p_74875_3_);
/* 1901 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 3, 4, 2, p_74875_3_);
/* 1902 */       func_175811_a(worldIn, Blocks.log.getDefaultState(), 3, 4, 3, p_74875_3_);
/* 1903 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 0, 0, 3, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 1904 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 0, 3, 3, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 1905 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 4, 0, 3, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 1906 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 4, 3, 3, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
/* 1907 */       func_175804_a(worldIn, p_74875_3_, 0, 1, 1, 0, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1908 */       func_175804_a(worldIn, p_74875_3_, 3, 1, 1, 3, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1909 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 0, 2, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1910 */       func_175804_a(worldIn, p_74875_3_, 1, 1, 4, 2, 3, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
/* 1911 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 0, 2, 2, p_74875_3_);
/* 1912 */       func_175811_a(worldIn, Blocks.glass_pane.getDefaultState(), 3, 2, 2, p_74875_3_);
/*      */       
/* 1914 */       if (this.tablePosition > 0) {
/*      */         
/* 1916 */         func_175811_a(worldIn, Blocks.oak_fence.getDefaultState(), this.tablePosition, 1, 3, p_74875_3_);
/* 1917 */         func_175811_a(worldIn, Blocks.wooden_pressure_plate.getDefaultState(), this.tablePosition, 2, 3, p_74875_3_);
/*      */       } 
/*      */       
/* 1920 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 1, 0, p_74875_3_);
/* 1921 */       func_175811_a(worldIn, Blocks.air.getDefaultState(), 1, 2, 0, p_74875_3_);
/* 1922 */       func_175810_a(worldIn, p_74875_3_, p_74875_2_, 1, 1, 0, EnumFacing.getHorizontal(getMetadataWithOffset(Blocks.oak_door, 1)));
/*      */       
/* 1924 */       if (func_175807_a(worldIn, 1, 0, -1, p_74875_3_).getBlock().getMaterial() == Material.air && func_175807_a(worldIn, 1, -1, -1, p_74875_3_).getBlock().getMaterial() != Material.air)
/*      */       {
/* 1926 */         func_175811_a(worldIn, Blocks.stone_stairs.getStateFromMeta(getMetadataWithOffset(Blocks.stone_stairs, 3)), 1, 0, -1, p_74875_3_);
/*      */       }
/*      */       
/* 1929 */       for (int var4 = 0; var4 < 5; var4++) {
/*      */         
/* 1931 */         for (int var5 = 0; var5 < 4; var5++) {
/*      */           
/* 1933 */           clearCurrentPositionBlocksUpwards(worldIn, var5, 6, var4, p_74875_3_);
/* 1934 */           func_175808_b(worldIn, Blocks.cobblestone.getDefaultState(), var5, -1, var4, p_74875_3_);
/*      */         } 
/*      */       } 
/*      */       
/* 1938 */       spawnVillagers(worldIn, p_74875_3_, 1, 1, 2, 1);
/* 1939 */       return true;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureVillagePieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */