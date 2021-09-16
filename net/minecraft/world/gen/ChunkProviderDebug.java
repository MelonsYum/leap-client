/*     */ package net.minecraft.world.gen;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.IProgressUpdate;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ public class ChunkProviderDebug implements IChunkProvider {
/*  21 */   private static final List field_177464_a = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkProviderDebug(World worldIn) {
/*  28 */     this.field_177463_c = worldIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int p_73154_1_, int p_73154_2_) {
/*  37 */     ChunkPrimer var3 = new ChunkPrimer();
/*     */ 
/*     */     
/*  40 */     for (int var4 = 0; var4 < 16; var4++) {
/*     */       
/*  42 */       for (int var5 = 0; var5 < 16; var5++) {
/*     */         
/*  44 */         int var6 = p_73154_1_ * 16 + var4;
/*  45 */         int i = p_73154_2_ * 16 + var5;
/*  46 */         var3.setBlockState(var4, 60, var5, Blocks.barrier.getDefaultState());
/*  47 */         IBlockState var8 = func_177461_b(var6, i);
/*     */         
/*  49 */         if (var8 != null)
/*     */         {
/*  51 */           var3.setBlockState(var4, 70, var5, var8);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  56 */     Chunk var9 = new Chunk(this.field_177463_c, var3, p_73154_1_, p_73154_2_);
/*  57 */     var9.generateSkylightMap();
/*  58 */     BiomeGenBase[] var10 = this.field_177463_c.getWorldChunkManager().loadBlockGeneratorData(null, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
/*  59 */     byte[] var11 = var9.getBiomeArray();
/*     */     
/*  61 */     for (int var7 = 0; var7 < var11.length; var7++)
/*     */     {
/*  63 */       var11[var7] = (byte)(var10[var7]).biomeID;
/*     */     }
/*     */     
/*  66 */     var9.generateSkylightMap();
/*  67 */     return var9;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IBlockState func_177461_b(int p_177461_0_, int p_177461_1_) {
/*  72 */     IBlockState var2 = null;
/*     */     
/*  74 */     if (p_177461_0_ > 0 && p_177461_1_ > 0 && p_177461_0_ % 2 != 0 && p_177461_1_ % 2 != 0) {
/*     */       
/*  76 */       p_177461_0_ /= 2;
/*  77 */       p_177461_1_ /= 2;
/*     */       
/*  79 */       if (p_177461_0_ <= field_177462_b && p_177461_1_ <= field_177462_b) {
/*     */         
/*  81 */         int var3 = MathHelper.abs_int(p_177461_0_ * field_177462_b + p_177461_1_);
/*     */         
/*  83 */         if (var3 < field_177464_a.size())
/*     */         {
/*  85 */           var2 = field_177464_a.get(var3);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  90 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int p_73149_1_, int p_73149_2_) {
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_) {
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveExtraData() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unloadQueuedChunks() {
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 139 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 147 */     return "DebugLevelSource";
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
/* 152 */     BiomeGenBase var3 = this.field_177463_c.getBiomeGenForCoords(p_177458_2_);
/* 153 */     return var3.getSpawnableList(p_177458_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180513_a(World worldIn, String p_180513_2_, BlockPos p_180513_3_) {
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 163 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180514_a(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {}
/*     */   
/*     */   public Chunk func_177459_a(BlockPos p_177459_1_) {
/* 170 */     return provideChunk(p_177459_1_.getX() >> 4, p_177459_1_.getZ() >> 4);
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 175 */     Iterator<Block> var0 = Block.blockRegistry.iterator();
/*     */     
/* 177 */     while (var0.hasNext()) {
/*     */       
/* 179 */       Block var1 = var0.next();
/* 180 */       field_177464_a.addAll((Collection)var1.getBlockState().getValidStates());
/*     */     } 
/*     */   }
/* 183 */   private static final int field_177462_b = MathHelper.ceiling_float_int(MathHelper.sqrt_float(field_177464_a.size()));
/*     */   private final World field_177463_c;
/*     */   private static final String __OBFID = "CL_00002002";
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\ChunkProviderDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */