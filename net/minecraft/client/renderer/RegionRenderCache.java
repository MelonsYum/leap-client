/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Arrays;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.ChunkCache;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import optifine.Config;
/*     */ import optifine.DynamicLights;
/*     */ 
/*     */ public class RegionRenderCache
/*     */   extends ChunkCache {
/*  18 */   private static final IBlockState field_175632_f = Blocks.air.getDefaultState();
/*     */   private final BlockPos field_175633_g;
/*     */   private int[] field_175634_h;
/*     */   private IBlockState[] field_175635_i;
/*     */   private static final String __OBFID = "CL_00002565";
/*  23 */   private static ArrayDeque<int[]> cacheLights = (ArrayDeque)new ArrayDeque<>();
/*  24 */   private static ArrayDeque<IBlockState[]> cacheStates = (ArrayDeque)new ArrayDeque<>();
/*  25 */   private static int maxCacheSize = Config.limit(Runtime.getRuntime().availableProcessors(), 1, 32);
/*     */ 
/*     */   
/*     */   public RegionRenderCache(World worldIn, BlockPos posFromIn, BlockPos posToIn, int subIn) {
/*  29 */     super(worldIn, posFromIn, posToIn, subIn);
/*  30 */     this.field_175633_g = posFromIn.subtract(new Vec3i(subIn, subIn, subIn));
/*  31 */     boolean var5 = true;
/*  32 */     this.field_175634_h = allocateLights(8000);
/*  33 */     Arrays.fill(this.field_175634_h, -1);
/*  34 */     this.field_175635_i = allocateStates(8000);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity getTileEntity(BlockPos pos) {
/*  39 */     int var2 = (pos.getX() >> 4) - this.chunkX;
/*  40 */     int var3 = (pos.getZ() >> 4) - this.chunkZ;
/*  41 */     return this.chunkArray[var2][var3].func_177424_a(pos, Chunk.EnumCreateEntityType.QUEUED);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCombinedLight(BlockPos p_175626_1_, int p_175626_2_) {
/*  46 */     int var3 = func_175630_e(p_175626_1_);
/*  47 */     int var4 = this.field_175634_h[var3];
/*     */     
/*  49 */     if (var4 == -1) {
/*     */       
/*  51 */       var4 = super.getCombinedLight(p_175626_1_, p_175626_2_);
/*     */       
/*  53 */       if (Config.isDynamicLights() && !getBlockState(p_175626_1_).getBlock().isOpaqueCube())
/*     */       {
/*  55 */         var4 = DynamicLights.getCombinedLight(p_175626_1_, var4);
/*     */       }
/*     */       
/*  58 */       this.field_175634_h[var3] = var4;
/*     */     } 
/*     */     
/*  61 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState getBlockState(BlockPos pos) {
/*  66 */     int var2 = func_175630_e(pos);
/*  67 */     IBlockState var3 = this.field_175635_i[var2];
/*     */     
/*  69 */     if (var3 == null) {
/*     */       
/*  71 */       var3 = func_175631_c(pos);
/*  72 */       this.field_175635_i[var2] = var3;
/*     */     } 
/*     */     
/*  75 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private IBlockState func_175631_c(BlockPos pos) {
/*  80 */     if (pos.getY() >= 0 && pos.getY() < 256) {
/*     */       
/*  82 */       int var2 = (pos.getX() >> 4) - this.chunkX;
/*  83 */       int var3 = (pos.getZ() >> 4) - this.chunkZ;
/*  84 */       return this.chunkArray[var2][var3].getBlockState(pos);
/*     */     } 
/*     */ 
/*     */     
/*  88 */     return field_175632_f;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int func_175630_e(BlockPos p_175630_1_) {
/*  94 */     int var2 = p_175630_1_.getX() - this.field_175633_g.getX();
/*  95 */     int var3 = p_175630_1_.getY() - this.field_175633_g.getY();
/*  96 */     int var4 = p_175630_1_.getZ() - this.field_175633_g.getZ();
/*  97 */     return var2 * 400 + var4 * 20 + var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void freeBuffers() {
/* 102 */     freeLights(this.field_175634_h);
/* 103 */     freeStates(this.field_175635_i);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int[] allocateLights(int size) {
/* 108 */     ArrayDeque<int[]> var1 = cacheLights;
/*     */     
/* 110 */     synchronized (cacheLights) {
/*     */       
/* 112 */       int[] ints = cacheLights.pollLast();
/*     */       
/* 114 */       if (ints == null || ints.length < size)
/*     */       {
/* 116 */         ints = new int[size];
/*     */       }
/*     */       
/* 119 */       return ints;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void freeLights(int[] ints) {
/* 125 */     ArrayDeque<int[]> var1 = cacheLights;
/*     */     
/* 127 */     synchronized (cacheLights) {
/*     */       
/* 129 */       if (cacheLights.size() < maxCacheSize)
/*     */       {
/* 131 */         cacheLights.add(ints);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static IBlockState[] allocateStates(int size) {
/* 138 */     ArrayDeque<IBlockState[]> var1 = cacheStates;
/*     */     
/* 140 */     synchronized (cacheStates) {
/*     */       
/* 142 */       IBlockState[] states = cacheStates.pollLast();
/*     */       
/* 144 */       if (states != null && states.length >= size) {
/*     */         
/* 146 */         Arrays.fill((Object[])states, (Object)null);
/*     */       }
/*     */       else {
/*     */         
/* 150 */         states = new IBlockState[size];
/*     */       } 
/*     */       
/* 153 */       return states;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void freeStates(IBlockState[] states) {
/* 159 */     ArrayDeque<IBlockState[]> var1 = cacheStates;
/*     */     
/* 161 */     synchronized (cacheStates) {
/*     */       
/* 163 */       if (cacheStates.size() < maxCacheSize)
/*     */       {
/* 165 */         cacheStates.add(states);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\RegionRenderCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */