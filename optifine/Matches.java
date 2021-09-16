/*     */ package optifine;
/*     */ 
/*     */ import net.minecraft.block.state.BlockStateBase;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ 
/*     */ public class Matches
/*     */ {
/*     */   public static boolean block(BlockStateBase blockStateBase, MatchBlock[] matchBlocks) {
/*  11 */     if (matchBlocks == null)
/*     */     {
/*  13 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  17 */     for (int i = 0; i < matchBlocks.length; i++) {
/*     */       
/*  19 */       MatchBlock mb = matchBlocks[i];
/*     */       
/*  21 */       if (mb.matches(blockStateBase))
/*     */       {
/*  23 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  27 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean block(int blockId, int metadata, MatchBlock[] matchBlocks) {
/*  33 */     if (matchBlocks == null)
/*     */     {
/*  35 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  39 */     for (int i = 0; i < matchBlocks.length; i++) {
/*     */       
/*  41 */       MatchBlock mb = matchBlocks[i];
/*     */       
/*  43 */       if (mb.matches(blockId, metadata))
/*     */       {
/*  45 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean blockId(int blockId, MatchBlock[] matchBlocks) {
/*  55 */     if (matchBlocks == null)
/*     */     {
/*  57 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  61 */     for (int i = 0; i < matchBlocks.length; i++) {
/*     */       
/*  63 */       MatchBlock mb = matchBlocks[i];
/*     */       
/*  65 */       if (mb.getBlockId() == blockId)
/*     */       {
/*  67 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean metadata(int metadata, int[] metadatas) {
/*  77 */     if (metadatas == null)
/*     */     {
/*  79 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  83 */     for (int i = 0; i < metadatas.length; i++) {
/*     */       
/*  85 */       if (metadatas[i] == metadata)
/*     */       {
/*  87 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean sprite(TextureAtlasSprite sprite, TextureAtlasSprite[] sprites) {
/*  97 */     if (sprites == null)
/*     */     {
/*  99 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 103 */     for (int i = 0; i < sprites.length; i++) {
/*     */       
/* 105 */       if (sprites[i] == sprite)
/*     */       {
/* 107 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean biome(BiomeGenBase biome, BiomeGenBase[] biomes) {
/* 117 */     if (biomes == null)
/*     */     {
/* 119 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 123 */     for (int i = 0; i < biomes.length; i++) {
/*     */       
/* 125 */       if (biomes[i] == biome)
/*     */       {
/* 127 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 131 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\Matches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */