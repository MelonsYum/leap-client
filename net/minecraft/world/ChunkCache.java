/*     */ package net.minecraft.world;
/*     */ 
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkCache
/*     */   implements IBlockAccess
/*     */ {
/*     */   protected int chunkX;
/*     */   protected int chunkZ;
/*     */   protected Chunk[][] chunkArray;
/*     */   protected boolean hasExtendedLevels;
/*     */   protected World worldObj;
/*     */   private static final String __OBFID = "CL_00000155";
/*     */   
/*     */   public ChunkCache(World worldIn, BlockPos p_i45746_2_, BlockPos p_i45746_3_, int p_i45746_4_) {
/*  27 */     this.worldObj = worldIn;
/*  28 */     this.chunkX = p_i45746_2_.getX() - p_i45746_4_ >> 4;
/*  29 */     this.chunkZ = p_i45746_2_.getZ() - p_i45746_4_ >> 4;
/*  30 */     int var5 = p_i45746_3_.getX() + p_i45746_4_ >> 4;
/*  31 */     int var6 = p_i45746_3_.getZ() + p_i45746_4_ >> 4;
/*  32 */     this.chunkArray = new Chunk[var5 - this.chunkX + 1][var6 - this.chunkZ + 1];
/*  33 */     this.hasExtendedLevels = true;
/*     */     
/*     */     int var7;
/*     */     
/*  37 */     for (var7 = this.chunkX; var7 <= var5; var7++) {
/*     */       
/*  39 */       for (int var8 = this.chunkZ; var8 <= var6; var8++)
/*     */       {
/*  41 */         this.chunkArray[var7 - this.chunkX][var8 - this.chunkZ] = worldIn.getChunkFromChunkCoords(var7, var8);
/*     */       }
/*     */     } 
/*     */     
/*  45 */     for (var7 = p_i45746_2_.getX() >> 4; var7 <= p_i45746_3_.getX() >> 4; var7++) {
/*     */       
/*  47 */       for (int var8 = p_i45746_2_.getZ() >> 4; var8 <= p_i45746_3_.getZ() >> 4; var8++) {
/*     */         
/*  49 */         Chunk var9 = this.chunkArray[var7 - this.chunkX][var8 - this.chunkZ];
/*     */         
/*  51 */         if (var9 != null && !var9.getAreLevelsEmpty(p_i45746_2_.getY(), p_i45746_3_.getY()))
/*     */         {
/*  53 */           this.hasExtendedLevels = false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean extendedLevelsInChunkCache() {
/*  64 */     return this.hasExtendedLevels;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity getTileEntity(BlockPos pos) {
/*  69 */     int var2 = (pos.getX() >> 4) - this.chunkX;
/*  70 */     int var3 = (pos.getZ() >> 4) - this.chunkZ;
/*  71 */     return this.chunkArray[var2][var3].func_177424_a(pos, Chunk.EnumCreateEntityType.IMMEDIATE);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCombinedLight(BlockPos p_175626_1_, int p_175626_2_) {
/*  76 */     int var3 = func_175629_a(EnumSkyBlock.SKY, p_175626_1_);
/*  77 */     int var4 = func_175629_a(EnumSkyBlock.BLOCK, p_175626_1_);
/*     */     
/*  79 */     if (var4 < p_175626_2_)
/*     */     {
/*  81 */       var4 = p_175626_2_;
/*     */     }
/*     */     
/*  84 */     return var3 << 20 | var4 << 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState getBlockState(BlockPos pos) {
/*  89 */     if (pos.getY() >= 0 && pos.getY() < 256) {
/*     */       
/*  91 */       int var2 = (pos.getX() >> 4) - this.chunkX;
/*  92 */       int var3 = (pos.getZ() >> 4) - this.chunkZ;
/*     */       
/*  94 */       if (var2 >= 0 && var2 < this.chunkArray.length && var3 >= 0 && var3 < (this.chunkArray[var2]).length) {
/*     */         
/*  96 */         Chunk var4 = this.chunkArray[var2][var3];
/*     */         
/*  98 */         if (var4 != null)
/*     */         {
/* 100 */           return var4.getBlockState(pos);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 105 */     return Blocks.air.getDefaultState();
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeGenBase getBiomeGenForCoords(BlockPos pos) {
/* 110 */     return this.worldObj.getBiomeGenForCoords(pos);
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_175629_a(EnumSkyBlock p_175629_1_, BlockPos p_175629_2_) {
/* 115 */     if (p_175629_1_ == EnumSkyBlock.SKY && this.worldObj.provider.getHasNoSky())
/*     */     {
/* 117 */       return 0;
/*     */     }
/* 119 */     if (p_175629_2_.getY() >= 0 && p_175629_2_.getY() < 256) {
/*     */ 
/*     */ 
/*     */       
/* 123 */       if (getBlockState(p_175629_2_).getBlock().getUseNeighborBrightness()) {
/*     */         
/* 125 */         int i = 0;
/* 126 */         EnumFacing[] var9 = EnumFacing.values();
/* 127 */         int var5 = var9.length;
/*     */         
/* 129 */         for (int var6 = 0; var6 < var5; var6++) {
/*     */           
/* 131 */           EnumFacing var7 = var9[var6];
/* 132 */           int var8 = func_175628_b(p_175629_1_, p_175629_2_.offset(var7));
/*     */           
/* 134 */           if (var8 > i)
/*     */           {
/* 136 */             i = var8;
/*     */           }
/*     */           
/* 139 */           if (i >= 15)
/*     */           {
/* 141 */             return i;
/*     */           }
/*     */         } 
/*     */         
/* 145 */         return i;
/*     */       } 
/*     */ 
/*     */       
/* 149 */       int var3 = (p_175629_2_.getX() >> 4) - this.chunkX;
/* 150 */       int var4 = (p_175629_2_.getZ() >> 4) - this.chunkZ;
/* 151 */       return this.chunkArray[var3][var4].getLightFor(p_175629_1_, p_175629_2_);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 156 */     return p_175629_1_.defaultLightValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAirBlock(BlockPos pos) {
/* 162 */     return (getBlockState(pos).getBlock().getMaterial() == Material.air);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_175628_b(EnumSkyBlock p_175628_1_, BlockPos p_175628_2_) {
/* 167 */     if (p_175628_2_.getY() >= 0 && p_175628_2_.getY() < 256) {
/*     */       
/* 169 */       int var3 = (p_175628_2_.getX() >> 4) - this.chunkX;
/* 170 */       int var4 = (p_175628_2_.getZ() >> 4) - this.chunkZ;
/* 171 */       return this.chunkArray[var3][var4].getLightFor(p_175628_1_, p_175628_2_);
/*     */     } 
/*     */ 
/*     */     
/* 175 */     return p_175628_1_.defaultLightValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStrongPower(BlockPos pos, EnumFacing direction) {
/* 181 */     IBlockState var3 = getBlockState(pos);
/* 182 */     return var3.getBlock().isProvidingStrongPower(this, pos, var3, direction);
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldType getWorldType() {
/* 187 */     return this.worldObj.getWorldType();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\ChunkCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */