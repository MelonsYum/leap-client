/*     */ package net.minecraft.world;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntitySpawnPlacementRegistry;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ public final class SpawnerAnimals {
/*  23 */   private static final int field_180268_a = (int)Math.pow(17.0D, 2.0D);
/*     */ 
/*     */   
/*  26 */   private final Set eligibleChunksForSpawning = Sets.newHashSet();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000152";
/*     */ 
/*     */ 
/*     */   
/*     */   public int findChunksForSpawning(WorldServer p_77192_1_, boolean p_77192_2_, boolean p_77192_3_, boolean p_77192_4_) {
/*  35 */     if (!p_77192_2_ && !p_77192_3_)
/*     */     {
/*  37 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*  41 */     this.eligibleChunksForSpawning.clear();
/*  42 */     int var5 = 0;
/*  43 */     Iterator<EntityPlayer> var6 = p_77192_1_.playerEntities.iterator();
/*     */ 
/*     */ 
/*     */     
/*  47 */     while (var6.hasNext()) {
/*     */       
/*  49 */       EntityPlayer var7 = var6.next();
/*     */       
/*  51 */       if (!var7.func_175149_v()) {
/*     */         
/*  53 */         int var8 = MathHelper.floor_double(var7.posX / 16.0D);
/*  54 */         int i = MathHelper.floor_double(var7.posZ / 16.0D);
/*  55 */         byte var10 = 8;
/*     */         
/*  57 */         for (int var11 = -var10; var11 <= var10; var11++) {
/*     */           
/*  59 */           for (int var12 = -var10; var12 <= var10; var12++) {
/*     */             
/*  61 */             boolean var13 = !(var11 != -var10 && var11 != var10 && var12 != -var10 && var12 != var10);
/*  62 */             ChunkCoordIntPair var14 = new ChunkCoordIntPair(var11 + var8, var12 + i);
/*     */             
/*  64 */             if (!this.eligibleChunksForSpawning.contains(var14)) {
/*     */               
/*  66 */               var5++;
/*     */               
/*  68 */               if (!var13 && p_77192_1_.getWorldBorder().contains(var14))
/*     */               {
/*  70 */                 this.eligibleChunksForSpawning.add(var14);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  78 */     int var36 = 0;
/*  79 */     BlockPos var37 = p_77192_1_.getSpawnPoint();
/*  80 */     EnumCreatureType[] var38 = EnumCreatureType.values();
/*  81 */     int var9 = var38.length;
/*     */     
/*  83 */     for (int var39 = 0; var39 < var9; var39++) {
/*     */       
/*  85 */       EnumCreatureType var40 = var38[var39];
/*     */       
/*  87 */       if ((!var40.getPeacefulCreature() || p_77192_3_) && (var40.getPeacefulCreature() || p_77192_2_) && (!var40.getAnimal() || p_77192_4_)) {
/*     */         
/*  89 */         int var12 = p_77192_1_.countEntities(var40.getCreatureClass());
/*  90 */         int var41 = var40.getMaxNumberOfCreature() * var5 / field_180268_a;
/*     */         
/*  92 */         if (var12 <= var41) {
/*     */           
/*  94 */           Iterator<ChunkCoordIntPair> var42 = this.eligibleChunksForSpawning.iterator();
/*     */ 
/*     */           
/*  97 */           while (var42.hasNext()) {
/*     */             
/*  99 */             ChunkCoordIntPair var15 = var42.next();
/* 100 */             BlockPos var16 = func_180621_a(p_77192_1_, var15.chunkXPos, var15.chunkZPos);
/* 101 */             int var17 = var16.getX();
/* 102 */             int var18 = var16.getY();
/* 103 */             int var19 = var16.getZ();
/* 104 */             Block var20 = p_77192_1_.getBlockState(var16).getBlock();
/*     */             
/* 106 */             if (!var20.isNormalCube()) {
/*     */               
/* 108 */               int var21 = 0;
/* 109 */               int var22 = 0;
/*     */               
/* 111 */               label83: while (var22 < 3) {
/*     */                 
/* 113 */                 int var23 = var17;
/* 114 */                 int var24 = var18;
/* 115 */                 int var25 = var19;
/* 116 */                 byte var26 = 6;
/* 117 */                 BiomeGenBase.SpawnListEntry var27 = null;
/* 118 */                 IEntityLivingData var28 = null;
/* 119 */                 int var29 = 0;
/*     */ 
/*     */ 
/*     */                 
/* 123 */                 while (var29 < 4) {
/*     */ 
/*     */ 
/*     */                   
/* 127 */                   var23 += p_77192_1_.rand.nextInt(var26) - p_77192_1_.rand.nextInt(var26);
/* 128 */                   var24 += p_77192_1_.rand.nextInt(1) - p_77192_1_.rand.nextInt(1);
/* 129 */                   var25 += p_77192_1_.rand.nextInt(var26) - p_77192_1_.rand.nextInt(var26);
/* 130 */                   BlockPos var30 = new BlockPos(var23, var24, var25);
/* 131 */                   float var31 = var23 + 0.5F;
/* 132 */                   float var32 = var25 + 0.5F;
/*     */                   
/* 134 */                   if (!p_77192_1_.func_175636_b(var31, var24, var32, 24.0D) && var37.distanceSq(var31, var24, var32) >= 576.0D) {
/*     */                     
/* 136 */                     if (var27 == null) {
/*     */                       
/* 138 */                       var27 = p_77192_1_.func_175734_a(var40, var30);
/*     */                       
/* 140 */                       if (var27 == null) {
/*     */                         break;
/*     */                       }
/*     */                     } 
/*     */ 
/*     */                     
/* 146 */                     if (p_77192_1_.func_175732_a(var40, var27, var30) && func_180267_a(EntitySpawnPlacementRegistry.func_180109_a(var27.entityClass), p_77192_1_, var30)) {
/*     */                       EntityLiving var33;
/*     */ 
/*     */ 
/*     */                       
/*     */                       try {
/* 152 */                         var33 = var27.entityClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { p_77192_1_ });
/*     */                       }
/* 154 */                       catch (Exception var35) {
/*     */                         
/* 156 */                         var35.printStackTrace();
/* 157 */                         return var36;
/*     */                       } 
/*     */                       
/* 160 */                       var33.setLocationAndAngles(var31, var24, var32, p_77192_1_.rand.nextFloat() * 360.0F, 0.0F);
/*     */                       
/* 162 */                       if (var33.getCanSpawnHere() && var33.handleLavaMovement()) {
/*     */                         
/* 164 */                         var28 = var33.func_180482_a(p_77192_1_.getDifficultyForLocation(new BlockPos((Entity)var33)), var28);
/*     */                         
/* 166 */                         if (var33.handleLavaMovement()) {
/*     */                           
/* 168 */                           var21++;
/* 169 */                           p_77192_1_.spawnEntityInWorld((Entity)var33);
/*     */                         } 
/*     */                         
/* 172 */                         if (var21 >= var33.getMaxSpawnedInChunk()) {
/*     */                           break label83;
/*     */                         }
/*     */                       } 
/*     */ 
/*     */                       
/* 178 */                       var36 += var21;
/*     */                     } 
/*     */                   } 
/*     */                   
/* 182 */                   var29++;
/*     */                 } 
/*     */ 
/*     */ 
/*     */                 
/* 187 */                 var22++;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 197 */     return var36;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static BlockPos func_180621_a(World worldIn, int p_180621_1_, int p_180621_2_) {
/* 203 */     Chunk var3 = worldIn.getChunkFromChunkCoords(p_180621_1_, p_180621_2_);
/* 204 */     int var4 = p_180621_1_ * 16 + worldIn.rand.nextInt(16);
/* 205 */     int var5 = p_180621_2_ * 16 + worldIn.rand.nextInt(16);
/* 206 */     int var6 = MathHelper.func_154354_b(var3.getHeight(new BlockPos(var4, 0, var5)) + 1, 16);
/* 207 */     int var7 = worldIn.rand.nextInt((var6 > 0) ? var6 : (var3.getTopFilledSegment() + 16 - 1));
/* 208 */     return new BlockPos(var4, var7, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_180267_a(EntityLiving.SpawnPlacementType p_180267_0_, World worldIn, BlockPos p_180267_2_) {
/* 213 */     if (!worldIn.getWorldBorder().contains(p_180267_2_))
/*     */     {
/* 215 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 219 */     Block var3 = worldIn.getBlockState(p_180267_2_).getBlock();
/*     */     
/* 221 */     if (p_180267_0_ == EntityLiving.SpawnPlacementType.IN_WATER)
/*     */     {
/* 223 */       return (var3.getMaterial().isLiquid() && worldIn.getBlockState(p_180267_2_.offsetDown()).getBlock().getMaterial().isLiquid() && !worldIn.getBlockState(p_180267_2_.offsetUp()).getBlock().isNormalCube());
/*     */     }
/*     */ 
/*     */     
/* 227 */     BlockPos var4 = p_180267_2_.offsetDown();
/*     */     
/* 229 */     if (!World.doesBlockHaveSolidTopSurface(worldIn, var4))
/*     */     {
/* 231 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 235 */     Block var5 = worldIn.getBlockState(var4).getBlock();
/* 236 */     boolean var6 = (var5 != Blocks.bedrock && var5 != Blocks.barrier);
/* 237 */     return (var6 && !var3.isNormalCube() && !var3.getMaterial().isLiquid() && !worldIn.getBlockState(p_180267_2_.offsetUp()).getBlock().isNormalCube());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void performWorldGenSpawning(World worldIn, BiomeGenBase p_77191_1_, int p_77191_2_, int p_77191_3_, int p_77191_4_, int p_77191_5_, Random p_77191_6_) {
/* 248 */     List var7 = p_77191_1_.getSpawnableList(EnumCreatureType.CREATURE);
/*     */     
/* 250 */     if (!var7.isEmpty())
/*     */     {
/* 252 */       while (p_77191_6_.nextFloat() < p_77191_1_.getSpawningChance()) {
/*     */         
/* 254 */         BiomeGenBase.SpawnListEntry var8 = (BiomeGenBase.SpawnListEntry)WeightedRandom.getRandomItem(worldIn.rand, var7);
/* 255 */         int var9 = var8.minGroupCount + p_77191_6_.nextInt(1 + var8.maxGroupCount - var8.minGroupCount);
/* 256 */         IEntityLivingData var10 = null;
/* 257 */         int var11 = p_77191_2_ + p_77191_6_.nextInt(p_77191_4_);
/* 258 */         int var12 = p_77191_3_ + p_77191_6_.nextInt(p_77191_5_);
/* 259 */         int var13 = var11;
/* 260 */         int var14 = var12;
/*     */         
/* 262 */         for (int var15 = 0; var15 < var9; var15++) {
/*     */           
/* 264 */           boolean var16 = false;
/*     */           
/* 266 */           for (int var17 = 0; !var16 && var17 < 4; var17++) {
/*     */             
/* 268 */             BlockPos var18 = worldIn.func_175672_r(new BlockPos(var11, 0, var12));
/*     */             
/* 270 */             if (func_180267_a(EntityLiving.SpawnPlacementType.ON_GROUND, worldIn, var18)) {
/*     */               EntityLiving var19;
/*     */ 
/*     */ 
/*     */               
/*     */               try {
/* 276 */                 var19 = var8.entityClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { worldIn });
/*     */               }
/* 278 */               catch (Exception var21) {
/*     */                 
/* 280 */                 var21.printStackTrace();
/*     */               } 
/*     */ 
/*     */               
/* 284 */               var19.setLocationAndAngles((var11 + 0.5F), var18.getY(), (var12 + 0.5F), p_77191_6_.nextFloat() * 360.0F, 0.0F);
/* 285 */               worldIn.spawnEntityInWorld((Entity)var19);
/* 286 */               var10 = var19.func_180482_a(worldIn.getDifficultyForLocation(new BlockPos((Entity)var19)), var10);
/* 287 */               var16 = true;
/*     */             } 
/*     */             
/* 290 */             var11 += p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5);
/*     */             
/* 292 */             for (var12 += p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5); var11 < p_77191_2_ || var11 >= p_77191_2_ + p_77191_4_ || var12 < p_77191_3_ || var12 >= p_77191_3_ + p_77191_4_; var12 = var14 + p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5))
/*     */             {
/* 294 */               var11 = var13 + p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\SpawnerAnimals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */