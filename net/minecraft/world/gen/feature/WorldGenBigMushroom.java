/*     */ package net.minecraft.world.gen.feature;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class WorldGenBigMushroom
/*     */   extends WorldGenerator
/*     */ {
/*  13 */   private int mushroomType = -1;
/*     */   
/*     */   private static final String __OBFID = "CL_00000415";
/*     */   
/*     */   public WorldGenBigMushroom(int p_i2017_1_) {
/*  18 */     super(true);
/*  19 */     this.mushroomType = p_i2017_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenBigMushroom() {
/*  24 */     super(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World worldIn, Random p_180709_2_, BlockPos p_180709_3_) {
/*  29 */     int var4 = p_180709_2_.nextInt(2);
/*     */     
/*  31 */     if (this.mushroomType >= 0)
/*     */     {
/*  33 */       var4 = this.mushroomType;
/*     */     }
/*     */     
/*  36 */     int var5 = p_180709_2_.nextInt(3) + 4;
/*  37 */     boolean var6 = true;
/*     */     
/*  39 */     if (p_180709_3_.getY() >= 1 && p_180709_3_.getY() + var5 + 1 < 256) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  44 */       for (int var7 = p_180709_3_.getY(); var7 <= p_180709_3_.getY() + 1 + var5; var7++) {
/*     */         
/*  46 */         byte var8 = 3;
/*     */         
/*  48 */         if (var7 <= p_180709_3_.getY() + 3)
/*     */         {
/*  50 */           var8 = 0;
/*     */         }
/*     */         
/*  53 */         for (int i = p_180709_3_.getX() - var8; i <= p_180709_3_.getX() + var8 && var6; i++) {
/*     */           
/*  55 */           for (int var10 = p_180709_3_.getZ() - var8; var10 <= p_180709_3_.getZ() + var8 && var6; var10++) {
/*     */             
/*  57 */             if (var7 >= 0 && var7 < 256) {
/*     */               
/*  59 */               Block var11 = worldIn.getBlockState(new BlockPos(i, var7, var10)).getBlock();
/*     */               
/*  61 */               if (var11.getMaterial() != Material.air && var11.getMaterial() != Material.leaves)
/*     */               {
/*  63 */                 var6 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  68 */               var6 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  74 */       if (!var6)
/*     */       {
/*  76 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  80 */       Block var15 = worldIn.getBlockState(p_180709_3_.offsetDown()).getBlock();
/*     */       
/*  82 */       if (var15 != Blocks.dirt && var15 != Blocks.grass && var15 != Blocks.mycelium)
/*     */       {
/*  84 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  88 */       int var16 = p_180709_3_.getY() + var5;
/*     */       
/*  90 */       if (var4 == 1)
/*     */       {
/*  92 */         var16 = p_180709_3_.getY() + var5 - 3;
/*     */       }
/*     */       int var9;
/*  95 */       for (var9 = var16; var9 <= p_180709_3_.getY() + var5; var9++) {
/*     */         
/*  97 */         int var10 = 1;
/*     */         
/*  99 */         if (var9 < p_180709_3_.getY() + var5)
/*     */         {
/* 101 */           var10++;
/*     */         }
/*     */         
/* 104 */         if (var4 == 0)
/*     */         {
/* 106 */           var10 = 3;
/*     */         }
/*     */         
/* 109 */         for (int var18 = p_180709_3_.getX() - var10; var18 <= p_180709_3_.getX() + var10; var18++) {
/*     */           
/* 111 */           for (int var12 = p_180709_3_.getZ() - var10; var12 <= p_180709_3_.getZ() + var10; var12++) {
/*     */             
/* 113 */             int var13 = 5;
/*     */             
/* 115 */             if (var18 == p_180709_3_.getX() - var10)
/*     */             {
/* 117 */               var13--;
/*     */             }
/*     */             
/* 120 */             if (var18 == p_180709_3_.getX() + var10)
/*     */             {
/* 122 */               var13++;
/*     */             }
/*     */             
/* 125 */             if (var12 == p_180709_3_.getZ() - var10)
/*     */             {
/* 127 */               var13 -= 3;
/*     */             }
/*     */             
/* 130 */             if (var12 == p_180709_3_.getZ() + var10)
/*     */             {
/* 132 */               var13 += 3;
/*     */             }
/*     */             
/* 135 */             if (var4 == 0 || var9 < p_180709_3_.getY() + var5) {
/*     */               
/* 137 */               if ((var18 == p_180709_3_.getX() - var10 || var18 == p_180709_3_.getX() + var10) && (var12 == p_180709_3_.getZ() - var10 || var12 == p_180709_3_.getZ() + var10)) {
/*     */                 continue;
/*     */               }
/*     */ 
/*     */               
/* 142 */               if (var18 == p_180709_3_.getX() - var10 - 1 && var12 == p_180709_3_.getZ() - var10)
/*     */               {
/* 144 */                 var13 = 1;
/*     */               }
/*     */               
/* 147 */               if (var18 == p_180709_3_.getX() - var10 && var12 == p_180709_3_.getZ() - var10 - 1)
/*     */               {
/* 149 */                 var13 = 1;
/*     */               }
/*     */               
/* 152 */               if (var18 == p_180709_3_.getX() + var10 - 1 && var12 == p_180709_3_.getZ() - var10)
/*     */               {
/* 154 */                 var13 = 3;
/*     */               }
/*     */               
/* 157 */               if (var18 == p_180709_3_.getX() + var10 && var12 == p_180709_3_.getZ() - var10 - 1)
/*     */               {
/* 159 */                 var13 = 3;
/*     */               }
/*     */               
/* 162 */               if (var18 == p_180709_3_.getX() - var10 - 1 && var12 == p_180709_3_.getZ() + var10)
/*     */               {
/* 164 */                 var13 = 7;
/*     */               }
/*     */               
/* 167 */               if (var18 == p_180709_3_.getX() - var10 && var12 == p_180709_3_.getZ() + var10 - 1)
/*     */               {
/* 169 */                 var13 = 7;
/*     */               }
/*     */               
/* 172 */               if (var18 == p_180709_3_.getX() + var10 - 1 && var12 == p_180709_3_.getZ() + var10)
/*     */               {
/* 174 */                 var13 = 9;
/*     */               }
/*     */               
/* 177 */               if (var18 == p_180709_3_.getX() + var10 && var12 == p_180709_3_.getZ() + var10 - 1)
/*     */               {
/* 179 */                 var13 = 9;
/*     */               }
/*     */             } 
/*     */             
/* 183 */             if (var13 == 5 && var9 < p_180709_3_.getY() + var5)
/*     */             {
/* 185 */               var13 = 0;
/*     */             }
/*     */             
/* 188 */             if (var13 != 0 || p_180709_3_.getY() >= p_180709_3_.getY() + var5 - 1) {
/*     */               
/* 190 */               BlockPos var14 = new BlockPos(var18, var9, var12);
/*     */               
/* 192 */               if (!worldIn.getBlockState(var14).getBlock().isFullBlock())
/*     */               {
/* 194 */                 func_175905_a(worldIn, var14, Block.getBlockById(Block.getIdFromBlock(Blocks.brown_mushroom_block) + var4), var13);
/*     */               }
/*     */             } 
/*     */             continue;
/*     */           } 
/*     */         } 
/*     */       } 
/* 201 */       for (var9 = 0; var9 < var5; var9++) {
/*     */         
/* 203 */         Block var17 = worldIn.getBlockState(p_180709_3_.offsetUp(var9)).getBlock();
/*     */         
/* 205 */         if (!var17.isFullBlock())
/*     */         {
/* 207 */           func_175905_a(worldIn, p_180709_3_.offsetUp(var9), Block.getBlockById(Block.getIdFromBlock(Blocks.brown_mushroom_block) + var4), 10);
/*     */         }
/*     */       } 
/*     */       
/* 211 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\feature\WorldGenBigMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */