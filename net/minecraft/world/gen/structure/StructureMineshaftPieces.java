/*     */ package net.minecraft.world.gen.structure;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.item.EntityMinecartChest;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class StructureMineshaftPieces {
/*  25 */   private static final List field_175893_a = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10), new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5), new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5), new WeightedRandomChestContent(Items.dye, EnumDyeColor.BLUE.getDyeColorDamage(), 4, 9, 5), new WeightedRandomChestContent(Items.diamond, 0, 1, 2, 3), new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10), new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15), new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 1), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.rail), 0, 4, 8, 1), new WeightedRandomChestContent(Items.melon_seeds, 0, 2, 4, 10), new WeightedRandomChestContent(Items.pumpkin_seeds, 0, 2, 4, 10), new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1) });
/*     */   
/*     */   private static final String __OBFID = "CL_00000444";
/*     */   
/*     */   public static void registerStructurePieces() {
/*  30 */     MapGenStructureIO.registerStructureComponent(Corridor.class, "MSCorridor");
/*  31 */     MapGenStructureIO.registerStructureComponent(Cross.class, "MSCrossing");
/*  32 */     MapGenStructureIO.registerStructureComponent(Room.class, "MSRoom");
/*  33 */     MapGenStructureIO.registerStructureComponent(Stairs.class, "MSStairs");
/*     */   }
/*     */ 
/*     */   
/*     */   private static StructureComponent func_175892_a(List p_175892_0_, Random p_175892_1_, int p_175892_2_, int p_175892_3_, int p_175892_4_, EnumFacing p_175892_5_, int p_175892_6_) {
/*  38 */     int var7 = p_175892_1_.nextInt(100);
/*     */ 
/*     */     
/*  41 */     if (var7 >= 80) {
/*     */       
/*  43 */       StructureBoundingBox var8 = Cross.func_175813_a(p_175892_0_, p_175892_1_, p_175892_2_, p_175892_3_, p_175892_4_, p_175892_5_);
/*     */       
/*  45 */       if (var8 != null)
/*     */       {
/*  47 */         return new Cross(p_175892_6_, p_175892_1_, var8, p_175892_5_);
/*     */       }
/*     */     }
/*  50 */     else if (var7 >= 70) {
/*     */       
/*  52 */       StructureBoundingBox var8 = Stairs.func_175812_a(p_175892_0_, p_175892_1_, p_175892_2_, p_175892_3_, p_175892_4_, p_175892_5_);
/*     */       
/*  54 */       if (var8 != null)
/*     */       {
/*  56 */         return new Stairs(p_175892_6_, p_175892_1_, var8, p_175892_5_);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  61 */       StructureBoundingBox var8 = Corridor.func_175814_a(p_175892_0_, p_175892_1_, p_175892_2_, p_175892_3_, p_175892_4_, p_175892_5_);
/*     */       
/*  63 */       if (var8 != null)
/*     */       {
/*  65 */         return new Corridor(p_175892_6_, p_175892_1_, var8, p_175892_5_);
/*     */       }
/*     */     } 
/*     */     
/*  69 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static StructureComponent func_175890_b(StructureComponent p_175890_0_, List<StructureComponent> p_175890_1_, Random p_175890_2_, int p_175890_3_, int p_175890_4_, int p_175890_5_, EnumFacing p_175890_6_, int p_175890_7_) {
/*  74 */     if (p_175890_7_ > 8)
/*     */     {
/*  76 */       return null;
/*     */     }
/*  78 */     if (Math.abs(p_175890_3_ - (p_175890_0_.getBoundingBox()).minX) <= 80 && Math.abs(p_175890_5_ - (p_175890_0_.getBoundingBox()).minZ) <= 80) {
/*     */       
/*  80 */       StructureComponent var8 = func_175892_a(p_175890_1_, p_175890_2_, p_175890_3_, p_175890_4_, p_175890_5_, p_175890_6_, p_175890_7_ + 1);
/*     */       
/*  82 */       if (var8 != null) {
/*     */         
/*  84 */         p_175890_1_.add(var8);
/*  85 */         var8.buildComponent(p_175890_0_, p_175890_1_, p_175890_2_);
/*     */       } 
/*     */       
/*  88 */       return var8;
/*     */     } 
/*     */ 
/*     */     
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Corridor
/*     */     extends StructureComponent
/*     */   {
/*     */     private boolean hasRails;
/*     */     private boolean hasSpiders;
/*     */     private boolean spawnerPlaced;
/*     */     private int sectionCount;
/*     */     private static final String __OBFID = "CL_00000445";
/*     */     
/*     */     public Corridor() {}
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 108 */       p_143012_1_.setBoolean("hr", this.hasRails);
/* 109 */       p_143012_1_.setBoolean("sc", this.hasSpiders);
/* 110 */       p_143012_1_.setBoolean("hps", this.spawnerPlaced);
/* 111 */       p_143012_1_.setInteger("Num", this.sectionCount);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 116 */       this.hasRails = p_143011_1_.getBoolean("hr");
/* 117 */       this.hasSpiders = p_143011_1_.getBoolean("sc");
/* 118 */       this.spawnerPlaced = p_143011_1_.getBoolean("hps");
/* 119 */       this.sectionCount = p_143011_1_.getInteger("Num");
/*     */     }
/*     */ 
/*     */     
/*     */     public Corridor(int p_i45625_1_, Random p_i45625_2_, StructureBoundingBox p_i45625_3_, EnumFacing p_i45625_4_) {
/* 124 */       super(p_i45625_1_);
/* 125 */       this.coordBaseMode = p_i45625_4_;
/* 126 */       this.boundingBox = p_i45625_3_;
/* 127 */       this.hasRails = (p_i45625_2_.nextInt(3) == 0);
/* 128 */       this.hasSpiders = (!this.hasRails && p_i45625_2_.nextInt(23) == 0);
/*     */       
/* 130 */       if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.SOUTH) {
/*     */         
/* 132 */         this.sectionCount = p_i45625_3_.getXSize() / 5;
/*     */       }
/*     */       else {
/*     */         
/* 136 */         this.sectionCount = p_i45625_3_.getZSize() / 5;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public static StructureBoundingBox func_175814_a(List p_175814_0_, Random p_175814_1_, int p_175814_2_, int p_175814_3_, int p_175814_4_, EnumFacing p_175814_5_) {
/* 142 */       StructureBoundingBox var6 = new StructureBoundingBox(p_175814_2_, p_175814_3_, p_175814_4_, p_175814_2_, p_175814_3_ + 2, p_175814_4_);
/*     */       
/*     */       int var7;
/* 145 */       for (var7 = p_175814_1_.nextInt(3) + 2; var7 > 0; var7--) {
/*     */         
/* 147 */         int var8 = var7 * 5;
/*     */         
/* 149 */         switch (StructureMineshaftPieces.SwitchEnumFacing.field_175894_a[p_175814_5_.ordinal()]) {
/*     */           
/*     */           case 1:
/* 152 */             var6.maxX = p_175814_2_ + 2;
/* 153 */             var6.minZ = p_175814_4_ - var8 - 1;
/*     */             break;
/*     */           
/*     */           case 2:
/* 157 */             var6.maxX = p_175814_2_ + 2;
/* 158 */             var6.maxZ = p_175814_4_ + var8 - 1;
/*     */             break;
/*     */           
/*     */           case 3:
/* 162 */             var6.minX = p_175814_2_ - var8 - 1;
/* 163 */             var6.maxZ = p_175814_4_ + 2;
/*     */             break;
/*     */           
/*     */           case 4:
/* 167 */             var6.maxX = p_175814_2_ + var8 - 1;
/* 168 */             var6.maxZ = p_175814_4_ + 2;
/*     */             break;
/*     */         } 
/* 171 */         if (StructureComponent.findIntersecting(p_175814_0_, var6) == null) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 177 */       return (var7 > 0) ? var6 : null;
/*     */     }
/*     */ 
/*     */     
/*     */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 182 */       int var4 = getComponentType();
/* 183 */       int var5 = p_74861_3_.nextInt(4);
/*     */       
/* 185 */       if (this.coordBaseMode != null)
/*     */       {
/* 187 */         switch (StructureMineshaftPieces.SwitchEnumFacing.field_175894_a[this.coordBaseMode.ordinal()]) {
/*     */           
/*     */           case 1:
/* 190 */             if (var5 <= 1) {
/*     */               
/* 192 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.minZ - 1, this.coordBaseMode, var4); break;
/*     */             } 
/* 194 */             if (var5 == 2) {
/*     */               
/* 196 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.minZ, EnumFacing.WEST, var4);
/*     */               
/*     */               break;
/*     */             } 
/* 200 */             StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.minZ, EnumFacing.EAST, var4);
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 2:
/* 206 */             if (var5 <= 1) {
/*     */               
/* 208 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.maxZ + 1, this.coordBaseMode, var4); break;
/*     */             } 
/* 210 */             if (var5 == 2) {
/*     */               
/* 212 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.WEST, var4);
/*     */               
/*     */               break;
/*     */             } 
/* 216 */             StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.EAST, var4);
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 3:
/* 222 */             if (var5 <= 1) {
/*     */               
/* 224 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, var4); break;
/*     */             } 
/* 226 */             if (var5 == 2) {
/*     */               
/* 228 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.minZ - 1, EnumFacing.NORTH, var4);
/*     */               
/*     */               break;
/*     */             } 
/* 232 */             StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.maxZ + 1, EnumFacing.SOUTH, var4);
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 4:
/* 238 */             if (var5 <= 1) {
/*     */               
/* 240 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.minZ, this.coordBaseMode, var4); break;
/*     */             } 
/* 242 */             if (var5 == 2) {
/*     */               
/* 244 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.minZ - 1, EnumFacing.NORTH, var4);
/*     */               
/*     */               break;
/*     */             } 
/* 248 */             StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + p_74861_3_.nextInt(3), this.boundingBox.maxZ + 1, EnumFacing.SOUTH, var4);
/*     */             break;
/*     */         } 
/*     */       
/*     */       }
/* 253 */       if (var4 < 8)
/*     */       {
/*     */ 
/*     */ 
/*     */         
/* 258 */         if (this.coordBaseMode != EnumFacing.NORTH && this.coordBaseMode != EnumFacing.SOUTH) {
/*     */           
/* 260 */           for (int var6 = this.boundingBox.minX + 3; var6 + 3 <= this.boundingBox.maxX; var6 += 5) {
/*     */             
/* 262 */             int var7 = p_74861_3_.nextInt(5);
/*     */             
/* 264 */             if (var7 == 0)
/*     */             {
/* 266 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, var6, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, var4 + 1);
/*     */             }
/* 268 */             else if (var7 == 1)
/*     */             {
/* 270 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, var6, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, var4 + 1);
/*     */             }
/*     */           
/*     */           } 
/*     */         } else {
/*     */           
/* 276 */           for (int var6 = this.boundingBox.minZ + 3; var6 + 3 <= this.boundingBox.maxZ; var6 += 5) {
/*     */             
/* 278 */             int var7 = p_74861_3_.nextInt(5);
/*     */             
/* 280 */             if (var7 == 0) {
/*     */               
/* 282 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, var6, EnumFacing.WEST, var4 + 1);
/*     */             }
/* 284 */             else if (var7 == 1) {
/*     */               
/* 286 */               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, var6, EnumFacing.EAST, var4 + 1);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean func_180778_a(World worldIn, StructureBoundingBox p_180778_2_, Random p_180778_3_, int p_180778_4_, int p_180778_5_, int p_180778_6_, List p_180778_7_, int p_180778_8_) {
/* 295 */       BlockPos var9 = new BlockPos(getXWithOffset(p_180778_4_, p_180778_6_), getYWithOffset(p_180778_5_), getZWithOffset(p_180778_4_, p_180778_6_));
/*     */       
/* 297 */       if (p_180778_2_.func_175898_b((Vec3i)var9) && worldIn.getBlockState(var9).getBlock().getMaterial() == Material.air) {
/*     */         
/* 299 */         int var10 = p_180778_3_.nextBoolean() ? 1 : 0;
/* 300 */         worldIn.setBlockState(var9, Blocks.rail.getStateFromMeta(getMetadataWithOffset(Blocks.rail, var10)), 2);
/* 301 */         EntityMinecartChest var11 = new EntityMinecartChest(worldIn, (var9.getX() + 0.5F), (var9.getY() + 0.5F), (var9.getZ() + 0.5F));
/* 302 */         WeightedRandomChestContent.generateChestContents(p_180778_3_, p_180778_7_, (IInventory)var11, p_180778_8_);
/* 303 */         worldIn.spawnEntityInWorld((Entity)var11);
/* 304 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 308 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 314 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*     */       {
/* 316 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 320 */       boolean var4 = false;
/* 321 */       boolean var5 = true;
/* 322 */       boolean var6 = false;
/* 323 */       boolean var7 = true;
/* 324 */       int var8 = this.sectionCount * 5 - 1;
/* 325 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 0, 2, 1, var8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 326 */       func_175805_a(worldIn, p_74875_3_, p_74875_2_, 0.8F, 0, 2, 0, 2, 2, var8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       
/* 328 */       if (this.hasSpiders)
/*     */       {
/* 330 */         func_175805_a(worldIn, p_74875_3_, p_74875_2_, 0.6F, 0, 0, 0, 2, 1, var8, Blocks.web.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       }
/*     */ 
/*     */       
/*     */       int var9;
/*     */       
/* 336 */       for (var9 = 0; var9 < this.sectionCount; var9++) {
/*     */         
/* 338 */         int var10 = 2 + var9 * 5;
/* 339 */         func_175804_a(worldIn, p_74875_3_, 0, 0, var10, 0, 1, var10, Blocks.oak_fence.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 340 */         func_175804_a(worldIn, p_74875_3_, 2, 0, var10, 2, 1, var10, Blocks.oak_fence.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */         
/* 342 */         if (p_74875_2_.nextInt(4) == 0) {
/*     */           
/* 344 */           func_175804_a(worldIn, p_74875_3_, 0, 2, var10, 0, 2, var10, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 345 */           func_175804_a(worldIn, p_74875_3_, 2, 2, var10, 2, 2, var10, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */         }
/*     */         else {
/*     */           
/* 349 */           func_175804_a(worldIn, p_74875_3_, 0, 2, var10, 2, 2, var10, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */         } 
/*     */         
/* 352 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1F, 0, 2, var10 - 1, Blocks.web.getDefaultState());
/* 353 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1F, 2, 2, var10 - 1, Blocks.web.getDefaultState());
/* 354 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1F, 0, 2, var10 + 1, Blocks.web.getDefaultState());
/* 355 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.1F, 2, 2, var10 + 1, Blocks.web.getDefaultState());
/* 356 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.05F, 0, 2, var10 - 2, Blocks.web.getDefaultState());
/* 357 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.05F, 2, 2, var10 - 2, Blocks.web.getDefaultState());
/* 358 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.05F, 0, 2, var10 + 2, Blocks.web.getDefaultState());
/* 359 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.05F, 2, 2, var10 + 2, Blocks.web.getDefaultState());
/* 360 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.05F, 1, 2, var10 - 1, Blocks.torch.getStateFromMeta(EnumFacing.UP.getIndex()));
/* 361 */         func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.05F, 1, 2, var10 + 1, Blocks.torch.getStateFromMeta(EnumFacing.UP.getIndex()));
/*     */         
/* 363 */         if (p_74875_2_.nextInt(100) == 0)
/*     */         {
/* 365 */           func_180778_a(worldIn, p_74875_3_, p_74875_2_, 2, 0, var10 - 1, WeightedRandomChestContent.func_177629_a(StructureMineshaftPieces.field_175893_a, new WeightedRandomChestContent[] { Items.enchanted_book.getRandomEnchantedBook(p_74875_2_) }), 3 + p_74875_2_.nextInt(4));
/*     */         }
/*     */         
/* 368 */         if (p_74875_2_.nextInt(100) == 0)
/*     */         {
/* 370 */           func_180778_a(worldIn, p_74875_3_, p_74875_2_, 0, 0, var10 + 1, WeightedRandomChestContent.func_177629_a(StructureMineshaftPieces.field_175893_a, new WeightedRandomChestContent[] { Items.enchanted_book.getRandomEnchantedBook(p_74875_2_) }), 3 + p_74875_2_.nextInt(4));
/*     */         }
/*     */         
/* 373 */         if (this.hasSpiders && !this.spawnerPlaced) {
/*     */           
/* 375 */           int var11 = getYWithOffset(0);
/* 376 */           int var12 = var10 - 1 + p_74875_2_.nextInt(3);
/* 377 */           int var13 = getXWithOffset(1, var12);
/* 378 */           var12 = getZWithOffset(1, var12);
/* 379 */           BlockPos var14 = new BlockPos(var13, var11, var12);
/*     */           
/* 381 */           if (p_74875_3_.func_175898_b((Vec3i)var14)) {
/*     */             
/* 383 */             this.spawnerPlaced = true;
/* 384 */             worldIn.setBlockState(var14, Blocks.mob_spawner.getDefaultState(), 2);
/* 385 */             TileEntity var15 = worldIn.getTileEntity(var14);
/*     */             
/* 387 */             if (var15 instanceof TileEntityMobSpawner)
/*     */             {
/* 389 */               ((TileEntityMobSpawner)var15).getSpawnerBaseLogic().setEntityName("CaveSpider");
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 395 */       for (var9 = 0; var9 <= 2; var9++) {
/*     */         
/* 397 */         for (int var10 = 0; var10 <= var8; var10++) {
/*     */           
/* 399 */           byte var17 = -1;
/* 400 */           IBlockState var18 = func_175807_a(worldIn, var9, var17, var10, p_74875_3_);
/*     */           
/* 402 */           if (var18.getBlock().getMaterial() == Material.air) {
/*     */             
/* 404 */             byte var19 = -1;
/* 405 */             func_175811_a(worldIn, Blocks.planks.getDefaultState(), var9, var19, var10, p_74875_3_);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 410 */       if (this.hasRails)
/*     */       {
/* 412 */         for (var9 = 0; var9 <= var8; var9++) {
/*     */           
/* 414 */           IBlockState var16 = func_175807_a(worldIn, 1, -1, var9, p_74875_3_);
/*     */           
/* 416 */           if (var16.getBlock().getMaterial() != Material.air && var16.getBlock().isFullBlock())
/*     */           {
/* 418 */             func_175809_a(worldIn, p_74875_3_, p_74875_2_, 0.7F, 1, 0, var9, Blocks.rail.getStateFromMeta(getMetadataWithOffset(Blocks.rail, 0)));
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 423 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Cross
/*     */     extends StructureComponent
/*     */   {
/*     */     private EnumFacing corridorDirection;
/*     */     private boolean isMultipleFloors;
/*     */     private static final String __OBFID = "CL_00000446";
/*     */     
/*     */     public Cross() {}
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 438 */       p_143012_1_.setBoolean("tf", this.isMultipleFloors);
/* 439 */       p_143012_1_.setInteger("D", this.corridorDirection.getHorizontalIndex());
/*     */     }
/*     */ 
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 444 */       this.isMultipleFloors = p_143011_1_.getBoolean("tf");
/* 445 */       this.corridorDirection = EnumFacing.getHorizontal(p_143011_1_.getInteger("D"));
/*     */     }
/*     */ 
/*     */     
/*     */     public Cross(int p_i45624_1_, Random p_i45624_2_, StructureBoundingBox p_i45624_3_, EnumFacing p_i45624_4_) {
/* 450 */       super(p_i45624_1_);
/* 451 */       this.corridorDirection = p_i45624_4_;
/* 452 */       this.boundingBox = p_i45624_3_;
/* 453 */       this.isMultipleFloors = (p_i45624_3_.getYSize() > 3);
/*     */     }
/*     */ 
/*     */     
/*     */     public static StructureBoundingBox func_175813_a(List p_175813_0_, Random p_175813_1_, int p_175813_2_, int p_175813_3_, int p_175813_4_, EnumFacing p_175813_5_) {
/* 458 */       StructureBoundingBox var6 = new StructureBoundingBox(p_175813_2_, p_175813_3_, p_175813_4_, p_175813_2_, p_175813_3_ + 2, p_175813_4_);
/*     */       
/* 460 */       if (p_175813_1_.nextInt(4) == 0)
/*     */       {
/* 462 */         var6.maxY += 4;
/*     */       }
/*     */       
/* 465 */       switch (StructureMineshaftPieces.SwitchEnumFacing.field_175894_a[p_175813_5_.ordinal()]) {
/*     */         
/*     */         case 1:
/* 468 */           var6.minX = p_175813_2_ - 1;
/* 469 */           var6.maxX = p_175813_2_ + 3;
/* 470 */           var6.minZ = p_175813_4_ - 4;
/*     */           break;
/*     */         
/*     */         case 2:
/* 474 */           var6.minX = p_175813_2_ - 1;
/* 475 */           var6.maxX = p_175813_2_ + 3;
/* 476 */           var6.maxZ = p_175813_4_ + 4;
/*     */           break;
/*     */         
/*     */         case 3:
/* 480 */           var6.minX = p_175813_2_ - 4;
/* 481 */           var6.minZ = p_175813_4_ - 1;
/* 482 */           var6.maxZ = p_175813_4_ + 3;
/*     */           break;
/*     */         
/*     */         case 4:
/* 486 */           var6.maxX = p_175813_2_ + 4;
/* 487 */           var6.minZ = p_175813_4_ - 1;
/* 488 */           var6.maxZ = p_175813_4_ + 3;
/*     */           break;
/*     */       } 
/* 491 */       return (StructureComponent.findIntersecting(p_175813_0_, var6) != null) ? null : var6;
/*     */     }
/*     */ 
/*     */     
/*     */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 496 */       int var4 = getComponentType();
/*     */       
/* 498 */       switch (StructureMineshaftPieces.SwitchEnumFacing.field_175894_a[this.corridorDirection.ordinal()]) {
/*     */         
/*     */         case 1:
/* 501 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, var4);
/* 502 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, var4);
/* 503 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, var4);
/*     */           break;
/*     */         
/*     */         case 2:
/* 507 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, var4);
/* 508 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, var4);
/* 509 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, var4);
/*     */           break;
/*     */         
/*     */         case 3:
/* 513 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, var4);
/* 514 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, var4);
/* 515 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.WEST, var4);
/*     */           break;
/*     */         
/*     */         case 4:
/* 519 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, var4);
/* 520 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, var4);
/* 521 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, EnumFacing.EAST, var4);
/*     */           break;
/*     */       } 
/* 524 */       if (this.isMultipleFloors) {
/*     */         
/* 526 */         if (p_74861_3_.nextBoolean())
/*     */         {
/* 528 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, var4);
/*     */         }
/*     */         
/* 531 */         if (p_74861_3_.nextBoolean())
/*     */         {
/* 533 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.WEST, var4);
/*     */         }
/*     */         
/* 536 */         if (p_74861_3_.nextBoolean())
/*     */         {
/* 538 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.minZ + 1, EnumFacing.EAST, var4);
/*     */         }
/*     */         
/* 541 */         if (p_74861_3_.nextBoolean())
/*     */         {
/* 543 */           StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + 1, this.boundingBox.minY + 3 + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, var4);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 550 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*     */       {
/* 552 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 556 */       if (this.isMultipleFloors) {
/*     */         
/* 558 */         func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 559 */         func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.minY + 3 - 1, this.boundingBox.maxZ - 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 560 */         func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX + 1, this.boundingBox.maxY - 2, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 561 */         func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX, this.boundingBox.maxY - 2, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 562 */         func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX + 1, this.boundingBox.minY + 3, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.minY + 3, this.boundingBox.maxZ - 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       }
/*     */       else {
/*     */         
/* 566 */         func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 567 */         func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       } 
/*     */       
/* 570 */       func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.minX + 1, this.boundingBox.maxY, this.boundingBox.minZ + 1, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 571 */       func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX + 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.minX + 1, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 572 */       func_175804_a(worldIn, p_74875_3_, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.minZ + 1, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.minZ + 1, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 573 */       func_175804_a(worldIn, p_74875_3_, this.boundingBox.maxX - 1, this.boundingBox.minY, this.boundingBox.maxZ - 1, this.boundingBox.maxX - 1, this.boundingBox.maxY, this.boundingBox.maxZ - 1, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       
/* 575 */       for (int var4 = this.boundingBox.minX; var4 <= this.boundingBox.maxX; var4++) {
/*     */         
/* 577 */         for (int var5 = this.boundingBox.minZ; var5 <= this.boundingBox.maxZ; var5++) {
/*     */           
/* 579 */           if (func_175807_a(worldIn, var4, this.boundingBox.minY - 1, var5, p_74875_3_).getBlock().getMaterial() == Material.air)
/*     */           {
/* 581 */             func_175811_a(worldIn, Blocks.planks.getDefaultState(), var4, this.boundingBox.minY - 1, var5, p_74875_3_);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 586 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Room
/*     */     extends StructureComponent
/*     */   {
/* 593 */     private List roomsLinkedToTheRoom = Lists.newLinkedList();
/*     */     
/*     */     private static final String __OBFID = "CL_00000447";
/*     */     
/*     */     public Room() {}
/*     */     
/*     */     public Room(int p_i2037_1_, Random p_i2037_2_, int p_i2037_3_, int p_i2037_4_) {
/* 600 */       super(p_i2037_1_);
/* 601 */       this.boundingBox = new StructureBoundingBox(p_i2037_3_, 50, p_i2037_4_, p_i2037_3_ + 7 + p_i2037_2_.nextInt(6), 54 + p_i2037_2_.nextInt(6), p_i2037_4_ + 7 + p_i2037_2_.nextInt(6));
/*     */     }
/*     */ 
/*     */     
/*     */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 606 */       int var4 = getComponentType();
/* 607 */       int var6 = this.boundingBox.getYSize() - 3 - 1;
/*     */       
/* 609 */       if (var6 <= 0)
/*     */       {
/* 611 */         var6 = 1;
/*     */       }
/*     */ 
/*     */       
/*     */       int var5;
/*     */ 
/*     */       
/* 618 */       for (var5 = 0; var5 < this.boundingBox.getXSize(); var5 += 4) {
/*     */         
/* 620 */         var5 += p_74861_3_.nextInt(this.boundingBox.getXSize());
/*     */         
/* 622 */         if (var5 + 3 > this.boundingBox.getXSize()) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 627 */         StructureComponent var7 = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + var5, this.boundingBox.minY + p_74861_3_.nextInt(var6) + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, var4);
/*     */         
/* 629 */         if (var7 != null) {
/*     */           
/* 631 */           StructureBoundingBox var8 = var7.getBoundingBox();
/* 632 */           this.roomsLinkedToTheRoom.add(new StructureBoundingBox(var8.minX, var8.minY, this.boundingBox.minZ, var8.maxX, var8.maxY, this.boundingBox.minZ + 1));
/*     */         } 
/*     */       } 
/*     */       
/* 636 */       for (var5 = 0; var5 < this.boundingBox.getXSize(); var5 += 4) {
/*     */         
/* 638 */         var5 += p_74861_3_.nextInt(this.boundingBox.getXSize());
/*     */         
/* 640 */         if (var5 + 3 > this.boundingBox.getXSize()) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 645 */         StructureComponent var7 = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX + var5, this.boundingBox.minY + p_74861_3_.nextInt(var6) + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, var4);
/*     */         
/* 647 */         if (var7 != null) {
/*     */           
/* 649 */           StructureBoundingBox var8 = var7.getBoundingBox();
/* 650 */           this.roomsLinkedToTheRoom.add(new StructureBoundingBox(var8.minX, var8.minY, this.boundingBox.maxZ - 1, var8.maxX, var8.maxY, this.boundingBox.maxZ));
/*     */         } 
/*     */       } 
/*     */       
/* 654 */       for (var5 = 0; var5 < this.boundingBox.getZSize(); var5 += 4) {
/*     */         
/* 656 */         var5 += p_74861_3_.nextInt(this.boundingBox.getZSize());
/*     */         
/* 658 */         if (var5 + 3 > this.boundingBox.getZSize()) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 663 */         StructureComponent var7 = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY + p_74861_3_.nextInt(var6) + 1, this.boundingBox.minZ + var5, EnumFacing.WEST, var4);
/*     */         
/* 665 */         if (var7 != null) {
/*     */           
/* 667 */           StructureBoundingBox var8 = var7.getBoundingBox();
/* 668 */           this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.boundingBox.minX, var8.minY, var8.minZ, this.boundingBox.minX + 1, var8.maxY, var8.maxZ));
/*     */         } 
/*     */       } 
/*     */       
/* 672 */       for (var5 = 0; var5 < this.boundingBox.getZSize(); var5 += 4) {
/*     */         
/* 674 */         var5 += p_74861_3_.nextInt(this.boundingBox.getZSize());
/*     */         
/* 676 */         if (var5 + 3 > this.boundingBox.getZSize()) {
/*     */           break;
/*     */         }
/*     */ 
/*     */         
/* 681 */         StructureComponent var7 = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74861_3_.nextInt(var6) + 1, this.boundingBox.minZ + var5, EnumFacing.EAST, var4);
/*     */         
/* 683 */         if (var7 != null) {
/*     */           
/* 685 */           StructureBoundingBox var8 = var7.getBoundingBox();
/* 686 */           this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.boundingBox.maxX - 1, var8.minY, var8.minZ, this.boundingBox.maxX, var8.maxY, var8.maxZ));
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 693 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*     */       {
/* 695 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 699 */       func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY, this.boundingBox.maxZ, Blocks.dirt.getDefaultState(), Blocks.air.getDefaultState(), true);
/* 700 */       func_175804_a(worldIn, p_74875_3_, this.boundingBox.minX, this.boundingBox.minY + 1, this.boundingBox.minZ, this.boundingBox.maxX, Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY), this.boundingBox.maxZ, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 701 */       Iterator<StructureBoundingBox> var4 = this.roomsLinkedToTheRoom.iterator();
/*     */       
/* 703 */       while (var4.hasNext()) {
/*     */         
/* 705 */         StructureBoundingBox var5 = var4.next();
/* 706 */         func_175804_a(worldIn, p_74875_3_, var5.minX, var5.maxY - 2, var5.minZ, var5.maxX, var5.maxY, var5.maxZ, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       } 
/*     */       
/* 709 */       func_180777_a(worldIn, p_74875_3_, this.boundingBox.minX, this.boundingBox.minY + 4, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ, Blocks.air.getDefaultState(), false);
/* 710 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {
/* 716 */       NBTTagList var2 = new NBTTagList();
/* 717 */       Iterator<StructureBoundingBox> var3 = this.roomsLinkedToTheRoom.iterator();
/*     */       
/* 719 */       while (var3.hasNext()) {
/*     */         
/* 721 */         StructureBoundingBox var4 = var3.next();
/* 722 */         var2.appendTag((NBTBase)var4.func_151535_h());
/*     */       } 
/*     */       
/* 725 */       p_143012_1_.setTag("Entrances", (NBTBase)var2);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {
/* 730 */       NBTTagList var2 = p_143011_1_.getTagList("Entrances", 11);
/*     */       
/* 732 */       for (int var3 = 0; var3 < var2.tagCount(); var3++)
/*     */       {
/* 734 */         this.roomsLinkedToTheRoom.add(new StructureBoundingBox(var2.getIntArray(var3)));
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Stairs
/*     */     extends StructureComponent
/*     */   {
/*     */     private static final String __OBFID = "CL_00000449";
/*     */     
/*     */     public Stairs() {}
/*     */     
/*     */     public Stairs(int p_i45623_1_, Random p_i45623_2_, StructureBoundingBox p_i45623_3_, EnumFacing p_i45623_4_) {
/* 747 */       super(p_i45623_1_);
/* 748 */       this.coordBaseMode = p_i45623_4_;
/* 749 */       this.boundingBox = p_i45623_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void writeStructureToNBT(NBTTagCompound p_143012_1_) {}
/*     */     
/*     */     protected void readStructureFromNBT(NBTTagCompound p_143011_1_) {}
/*     */     
/*     */     public static StructureBoundingBox func_175812_a(List p_175812_0_, Random p_175812_1_, int p_175812_2_, int p_175812_3_, int p_175812_4_, EnumFacing p_175812_5_) {
/* 758 */       StructureBoundingBox var6 = new StructureBoundingBox(p_175812_2_, p_175812_3_ - 5, p_175812_4_, p_175812_2_, p_175812_3_ + 2, p_175812_4_);
/*     */       
/* 760 */       switch (StructureMineshaftPieces.SwitchEnumFacing.field_175894_a[p_175812_5_.ordinal()]) {
/*     */         
/*     */         case 1:
/* 763 */           var6.maxX = p_175812_2_ + 2;
/* 764 */           var6.minZ = p_175812_4_ - 8;
/*     */           break;
/*     */         
/*     */         case 2:
/* 768 */           var6.maxX = p_175812_2_ + 2;
/* 769 */           var6.maxZ = p_175812_4_ + 8;
/*     */           break;
/*     */         
/*     */         case 3:
/* 773 */           var6.minX = p_175812_2_ - 8;
/* 774 */           var6.maxZ = p_175812_4_ + 2;
/*     */           break;
/*     */         
/*     */         case 4:
/* 778 */           var6.maxX = p_175812_2_ + 8;
/* 779 */           var6.maxZ = p_175812_4_ + 2;
/*     */           break;
/*     */       } 
/* 782 */       return (StructureComponent.findIntersecting(p_175812_0_, var6) != null) ? null : var6;
/*     */     }
/*     */ 
/*     */     
/*     */     public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_, Random p_74861_3_) {
/* 787 */       int var4 = getComponentType();
/*     */       
/* 789 */       if (this.coordBaseMode != null)
/*     */       {
/* 791 */         switch (StructureMineshaftPieces.SwitchEnumFacing.field_175894_a[this.coordBaseMode.ordinal()]) {
/*     */           
/*     */           case 1:
/* 794 */             StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, var4);
/*     */             break;
/*     */           
/*     */           case 2:
/* 798 */             StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, var4);
/*     */             break;
/*     */           
/*     */           case 3:
/* 802 */             StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.minX - 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.WEST, var4);
/*     */             break;
/*     */           
/*     */           case 4:
/* 806 */             StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.boundingBox.maxX + 1, this.boundingBox.minY, this.boundingBox.minZ, EnumFacing.EAST, var4);
/*     */             break;
/*     */         } 
/*     */       }
/*     */     }
/*     */     
/*     */     public boolean addComponentParts(World worldIn, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
/* 813 */       if (isLiquidInStructureBoundingBox(worldIn, p_74875_3_))
/*     */       {
/* 815 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 819 */       func_175804_a(worldIn, p_74875_3_, 0, 5, 0, 2, 7, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/* 820 */       func_175804_a(worldIn, p_74875_3_, 0, 0, 7, 2, 2, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       
/* 822 */       for (int var4 = 0; var4 < 5; var4++)
/*     */       {
/* 824 */         func_175804_a(worldIn, p_74875_3_, 0, 5 - var4 - ((var4 < 4) ? 1 : 0), 2 + var4, 2, 7 - var4, 2 + var4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
/*     */       }
/*     */       
/* 827 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 834 */     static final int[] field_175894_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00001998";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 841 */         field_175894_a[EnumFacing.NORTH.ordinal()] = 1;
/*     */       }
/* 843 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 850 */         field_175894_a[EnumFacing.SOUTH.ordinal()] = 2;
/*     */       }
/* 852 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 859 */         field_175894_a[EnumFacing.WEST.ordinal()] = 3;
/*     */       }
/* 861 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 868 */         field_175894_a[EnumFacing.EAST.ordinal()] = 4;
/*     */       }
/* 870 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureMineshaftPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */