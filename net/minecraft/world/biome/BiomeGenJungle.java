/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockPlanks;
/*    */ import net.minecraft.block.BlockTallGrass;
/*    */ import net.minecraft.entity.passive.EntityChicken;
/*    */ import net.minecraft.entity.passive.EntityOcelot;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*    */ import net.minecraft.world.gen.feature.WorldGenMegaJungle;
/*    */ import net.minecraft.world.gen.feature.WorldGenMelon;
/*    */ import net.minecraft.world.gen.feature.WorldGenShrub;
/*    */ import net.minecraft.world.gen.feature.WorldGenTallGrass;
/*    */ import net.minecraft.world.gen.feature.WorldGenTrees;
/*    */ import net.minecraft.world.gen.feature.WorldGenVines;
/*    */ import net.minecraft.world.gen.feature.WorldGenerator;
/*    */ 
/*    */ public class BiomeGenJungle
/*    */   extends BiomeGenBase
/*    */ {
/*    */   private boolean field_150614_aC;
/*    */   private static final String __OBFID = "CL_00000175";
/*    */   
/*    */   public BiomeGenJungle(int p_i45379_1_, boolean p_i45379_2_) {
/* 26 */     super(p_i45379_1_);
/* 27 */     this.field_150614_aC = p_i45379_2_;
/*    */     
/* 29 */     if (p_i45379_2_) {
/*    */       
/* 31 */       this.theBiomeDecorator.treesPerChunk = 2;
/*    */     }
/*    */     else {
/*    */       
/* 35 */       this.theBiomeDecorator.treesPerChunk = 50;
/*    */     } 
/*    */     
/* 38 */     this.theBiomeDecorator.grassPerChunk = 25;
/* 39 */     this.theBiomeDecorator.flowersPerChunk = 4;
/*    */     
/* 41 */     if (!p_i45379_2_)
/*    */     {
/* 43 */       this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
/*    */     }
/*    */     
/* 46 */     this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/* 51 */     return (p_150567_1_.nextInt(10) == 0) ? (WorldGenAbstractTree)this.worldGeneratorBigTree : ((p_150567_1_.nextInt(2) == 0) ? (WorldGenAbstractTree)new WorldGenShrub(BlockPlanks.EnumType.JUNGLE.func_176839_a(), BlockPlanks.EnumType.OAK.func_176839_a()) : ((!this.field_150614_aC && p_150567_1_.nextInt(3) == 0) ? (WorldGenAbstractTree)new WorldGenMegaJungle(false, 10, 20, BlockPlanks.EnumType.JUNGLE.func_176839_a(), BlockPlanks.EnumType.JUNGLE.func_176839_a()) : (WorldGenAbstractTree)new WorldGenTrees(false, 4 + p_150567_1_.nextInt(7), BlockPlanks.EnumType.JUNGLE.func_176839_a(), BlockPlanks.EnumType.JUNGLE.func_176839_a(), true)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_) {
/* 59 */     return (p_76730_1_.nextInt(4) == 0) ? (WorldGenerator)new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : (WorldGenerator)new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180624_a(World worldIn, Random p_180624_2_, BlockPos p_180624_3_) {
/* 64 */     super.func_180624_a(worldIn, p_180624_2_, p_180624_3_);
/* 65 */     int var4 = p_180624_2_.nextInt(16) + 8;
/* 66 */     int var5 = p_180624_2_.nextInt(16) + 8;
/* 67 */     int var6 = p_180624_2_.nextInt(worldIn.getHorizon(p_180624_3_.add(var4, 0, var5)).getY() * 2);
/* 68 */     (new WorldGenMelon()).generate(worldIn, p_180624_2_, p_180624_3_.add(var4, var6, var5));
/* 69 */     WorldGenVines var9 = new WorldGenVines();
/*    */     
/* 71 */     for (var5 = 0; var5 < 50; var5++) {
/*    */       
/* 73 */       var6 = p_180624_2_.nextInt(16) + 8;
/* 74 */       boolean var7 = true;
/* 75 */       int var8 = p_180624_2_.nextInt(16) + 8;
/* 76 */       var9.generate(worldIn, p_180624_2_, p_180624_3_.add(var6, 128, var8));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenJungle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */