/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.WeightedRandomChestContent;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class WorldGenDungeons
/*     */   extends WorldGenerator {
/*  22 */   private static final Logger field_175918_a = LogManager.getLogger();
/*  23 */   private static final String[] SPAWNERTYPES = new String[] { "Skeleton", "Zombie", "Zombie", "Spider" };
/*  24 */   private static final List CHESTCONTENT = Lists.newArrayList((Object[])new WeightedRandomChestContent[] { new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10), new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, 10), new WeightedRandomChestContent(Items.bread, 0, 1, 1, 10), new WeightedRandomChestContent(Items.wheat, 0, 1, 4, 10), new WeightedRandomChestContent(Items.gunpowder, 0, 1, 4, 10), new WeightedRandomChestContent(Items.string, 0, 1, 4, 10), new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1), new WeightedRandomChestContent(Items.redstone, 0, 1, 4, 10), new WeightedRandomChestContent(Items.record_13, 0, 1, 1, 4), new WeightedRandomChestContent(Items.record_cat, 0, 1, 1, 4), new WeightedRandomChestContent(Items.name_tag, 0, 1, 1, 10), new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 2), new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5), new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1) });
/*     */   
/*     */   private static final String __OBFID = "CL_00000425";
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  29 */     boolean var4 = true;
/*  30 */     int var5 = p_180709_2_.nextInt(2) + 2;
/*  31 */     int var6 = -var5 - 1;
/*  32 */     int var7 = var5 + 1;
/*  33 */     boolean var8 = true;
/*  34 */     boolean var9 = true;
/*  35 */     int var10 = p_180709_2_.nextInt(2) + 2;
/*  36 */     int var11 = -var10 - 1;
/*  37 */     int var12 = var10 + 1;
/*  38 */     int var13 = 0;
/*     */ 
/*     */     
/*     */     int var14;
/*     */ 
/*     */     
/*  44 */     for (var14 = var6; var14 <= var7; var14++) {
/*     */       
/*  46 */       for (int var15 = -1; var15 <= 4; var15++) {
/*     */         
/*  48 */         for (int var16 = var11; var16 <= var12; var16++) {
/*     */           
/*  50 */           BlockPos var17 = p_180709_3_.add(var14, var15, var16);
/*  51 */           Material var18 = worldIn.getBlockState(var17).getBlock().getMaterial();
/*  52 */           boolean var19 = var18.isSolid();
/*     */           
/*  54 */           if (var15 == -1 && !var19)
/*     */           {
/*  56 */             return false;
/*     */           }
/*     */           
/*  59 */           if (var15 == 4 && !var19)
/*     */           {
/*  61 */             return false;
/*     */           }
/*     */           
/*  64 */           if ((var14 == var6 || var14 == var7 || var16 == var11 || var16 == var12) && var15 == 0 && worldIn.isAirBlock(var17) && worldIn.isAirBlock(var17.offsetUp()))
/*     */           {
/*  66 */             var13++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  72 */     if (var13 >= 1 && var13 <= 5) {
/*     */       
/*  74 */       for (var14 = var6; var14 <= var7; var14++) {
/*     */         
/*  76 */         for (int var15 = 3; var15 >= -1; var15--) {
/*     */           
/*  78 */           for (int var16 = var11; var16 <= var12; var16++) {
/*     */             
/*  80 */             BlockPos var17 = p_180709_3_.add(var14, var15, var16);
/*     */             
/*  82 */             if (var14 != var6 && var15 != -1 && var16 != var11 && var14 != var7 && var15 != 4 && var16 != var12) {
/*     */               
/*  84 */               if (worldIn.getBlockState(var17).getBlock() != Blocks.chest)
/*     */               {
/*  86 */                 worldIn.setBlockToAir(var17);
/*     */               }
/*     */             }
/*  89 */             else if (var17.getY() >= 0 && !worldIn.getBlockState(var17.offsetDown()).getBlock().getMaterial().isSolid()) {
/*     */               
/*  91 */               worldIn.setBlockToAir(var17);
/*     */             }
/*  93 */             else if (worldIn.getBlockState(var17).getBlock().getMaterial().isSolid() && worldIn.getBlockState(var17).getBlock() != Blocks.chest) {
/*     */               
/*  95 */               if (var15 == -1 && p_180709_2_.nextInt(4) != 0) {
/*     */                 
/*  97 */                 worldIn.setBlockState(var17, Blocks.mossy_cobblestone.getDefaultState(), 2);
/*     */               }
/*     */               else {
/*     */                 
/* 101 */                 worldIn.setBlockState(var17, Blocks.cobblestone.getDefaultState(), 2);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 108 */       var14 = 0;
/*     */       
/* 110 */       while (var14 < 2) {
/*     */         
/* 112 */         int var15 = 0;
/*     */ 
/*     */ 
/*     */         
/* 116 */         while (var15 < 3) {
/*     */ 
/*     */ 
/*     */           
/* 120 */           int var16 = p_180709_3_.getX() + p_180709_2_.nextInt(var5 * 2 + 1) - var5;
/* 121 */           int var24 = p_180709_3_.getY();
/* 122 */           int var25 = p_180709_3_.getZ() + p_180709_2_.nextInt(var10 * 2 + 1) - var10;
/* 123 */           BlockPos var26 = new BlockPos(var16, var24, var25);
/*     */           
/* 125 */           if (worldIn.isAirBlock(var26)) {
/*     */             
/* 127 */             int var20 = 0;
/* 128 */             Iterator<EnumFacing> var21 = EnumFacing.Plane.HORIZONTAL.iterator();
/*     */             
/* 130 */             while (var21.hasNext()) {
/*     */               
/* 132 */               EnumFacing var22 = var21.next();
/*     */               
/* 134 */               if (worldIn.getBlockState(var26.offset(var22)).getBlock().getMaterial().isSolid())
/*     */               {
/* 136 */                 var20++;
/*     */               }
/*     */             } 
/*     */             
/* 140 */             if (var20 == 1) {
/*     */               
/* 142 */               worldIn.setBlockState(var26, Blocks.chest.func_176458_f(worldIn, var26, Blocks.chest.getDefaultState()), 2);
/* 143 */               List var27 = WeightedRandomChestContent.func_177629_a(CHESTCONTENT, new WeightedRandomChestContent[] { Items.enchanted_book.getRandomEnchantedBook(p_180709_2_) });
/* 144 */               TileEntity var28 = worldIn.getTileEntity(var26);
/*     */               
/* 146 */               if (var28 instanceof net.minecraft.tileentity.TileEntityChest)
/*     */               {
/* 148 */                 WeightedRandomChestContent.generateChestContents(p_180709_2_, var27, (IInventory)var28, 8);
/*     */               }
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           
/* 155 */           var15++;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 160 */         var14++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 165 */       worldIn.setBlockState(p_180709_3_, Blocks.mob_spawner.getDefaultState(), 2);
/* 166 */       TileEntity var23 = worldIn.getTileEntity(p_180709_3_);
/*     */       
/* 168 */       if (var23 instanceof TileEntityMobSpawner) {
/*     */         
/* 170 */         ((TileEntityMobSpawner)var23).getSpawnerBaseLogic().setEntityName(pickMobSpawner(p_180709_2_));
/*     */       }
/*     */       else {
/*     */         
/* 174 */         field_175918_a.error("Failed to fetch mob spawner entity at (" + p_180709_3_.getX() + ", " + p_180709_3_.getY() + ", " + p_180709_3_.getZ() + ")");
/*     */       } 
/*     */       
/* 177 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String pickMobSpawner(Random p_76543_1_) {
/* 190 */     return SPAWNERTYPES[p_76543_1_.nextInt(SPAWNERTYPES.length)];
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenDungeons.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */