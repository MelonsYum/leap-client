/*    */ package net.minecraft.world.biome;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.BlockFlower;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.monster.EntitySlime;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.ChunkPrimer;
/*    */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*    */ 
/*    */ public class BiomeGenSwamp
/*    */   extends BiomeGenBase
/*    */ {
/*    */   private static final String __OBFID = "CL_00000185";
/*    */   
/*    */   protected BiomeGenSwamp(int p_i1988_1_) {
/* 19 */     super(p_i1988_1_);
/* 20 */     this.theBiomeDecorator.treesPerChunk = 2;
/* 21 */     this.theBiomeDecorator.flowersPerChunk = 1;
/* 22 */     this.theBiomeDecorator.deadBushPerChunk = 1;
/* 23 */     this.theBiomeDecorator.mushroomsPerChunk = 8;
/* 24 */     this.theBiomeDecorator.reedsPerChunk = 10;
/* 25 */     this.theBiomeDecorator.clayPerChunk = 1;
/* 26 */     this.theBiomeDecorator.waterlilyPerChunk = 4;
/* 27 */     this.theBiomeDecorator.sandPerChunk2 = 0;
/* 28 */     this.theBiomeDecorator.sandPerChunk = 0;
/* 29 */     this.theBiomeDecorator.grassPerChunk = 5;
/* 30 */     this.waterColorMultiplier = 14745518;
/* 31 */     this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 1, 1, 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenAbstractTree genBigTreeChance(Random p_150567_1_) {
/* 36 */     return (WorldGenAbstractTree)this.worldGeneratorSwamp;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_180627_b(BlockPos p_180627_1_) {
/* 41 */     double var2 = field_180281_af.func_151601_a(p_180627_1_.getX() * 0.0225D, p_180627_1_.getZ() * 0.0225D);
/* 42 */     return (var2 < -0.1D) ? 5011004 : 6975545;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_180625_c(BlockPos p_180625_1_) {
/* 47 */     return 6975545;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockFlower.EnumFlowerType pickRandomFlower(Random p_180623_1_, BlockPos p_180623_2_) {
/* 52 */     return BlockFlower.EnumFlowerType.BLUE_ORCHID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void genTerrainBlocks(World worldIn, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
/* 57 */     double var8 = field_180281_af.func_151601_a(p_180622_4_ * 0.25D, p_180622_5_ * 0.25D);
/*    */     
/* 59 */     if (var8 > 0.0D) {
/*    */       
/* 61 */       int var10 = p_180622_4_ & 0xF;
/* 62 */       int var11 = p_180622_5_ & 0xF;
/*    */       
/* 64 */       for (int var12 = 255; var12 >= 0; var12--) {
/*    */         
/* 66 */         if (p_180622_3_.getBlockState(var11, var12, var10).getBlock().getMaterial() != Material.air) {
/*    */           
/* 68 */           if (var12 == 62 && p_180622_3_.getBlockState(var11, var12, var10).getBlock() != Blocks.water) {
/*    */             
/* 70 */             p_180622_3_.setBlockState(var11, var12, var10, Blocks.water.getDefaultState());
/*    */             
/* 72 */             if (var8 < 0.12D)
/*    */             {
/* 74 */               p_180622_3_.setBlockState(var11, var12 + 1, var10, Blocks.waterlily.getDefaultState());
/*    */             }
/*    */           } 
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 83 */     func_180628_b(worldIn, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\biome\BiomeGenSwamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */