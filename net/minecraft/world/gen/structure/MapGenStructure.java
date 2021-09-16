/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.Callable;
/*     */ import net.minecraft.crash.CrashReport;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ import net.minecraft.util.ReportedException;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.ChunkPrimer;
/*     */ import net.minecraft.world.gen.MapGenBase;
/*     */ import net.minecraft.world.storage.MapStorage;
/*     */ import optifine.Reflector;
/*     */ 
/*     */ public abstract class MapGenStructure
/*     */   extends MapGenBase {
/*     */   private MapGenStructureData field_143029_e;
/*     */   protected Map structureMap;
/*     */   private static final String __OBFID = "CL_00000505";
/*     */   private LongHashMap structureLongMap;
/*     */   
/*     */   public MapGenStructure() {
/*  32 */     this.structureMap = Maps.newHashMap();
/*     */     
/*  34 */     this.structureLongMap = new LongHashMap();
/*     */   }
/*     */ 
/*     */   
/*     */   protected final void func_180701_a(World worldIn, final int p_180701_2_, final int p_180701_3_, int p_180701_4_, int p_180701_5_, ChunkPrimer p_180701_6_) {
/*  39 */     func_143027_a(worldIn);
/*     */     
/*  41 */     if (!this.structureLongMap.containsItem(ChunkCoordIntPair.chunkXZ2Int(p_180701_2_, p_180701_3_))) {
/*     */       
/*  43 */       this.rand.nextInt();
/*     */ 
/*     */       
/*     */       try {
/*  47 */         if (canSpawnStructureAtCoords(p_180701_2_, p_180701_3_))
/*     */         {
/*  49 */           StructureStart var10 = getStructureStart(p_180701_2_, p_180701_3_);
/*  50 */           this.structureMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(p_180701_2_, p_180701_3_)), var10);
/*  51 */           this.structureLongMap.add(ChunkCoordIntPair.chunkXZ2Int(p_180701_2_, p_180701_3_), var10);
/*  52 */           func_143026_a(p_180701_2_, p_180701_3_, var10);
/*     */         }
/*     */       
/*  55 */       } catch (Throwable var101) {
/*     */         
/*  57 */         CrashReport var8 = CrashReport.makeCrashReport(var101, "Exception preparing structure feature");
/*  58 */         CrashReportCategory var9 = var8.makeCategory("Feature being prepared");
/*  59 */         var9.addCrashSectionCallable("Is feature chunk", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00000506";
/*     */               
/*     */               public String call() {
/*  64 */                 return MapGenStructure.this.canSpawnStructureAtCoords(p_180701_2_, p_180701_3_) ? "True" : "False";
/*     */               }
/*     */             });
/*  67 */         var9.addCrashSection("Chunk location", String.format("%d,%d", new Object[] { Integer.valueOf(p_180701_2_), Integer.valueOf(p_180701_3_) }));
/*  68 */         var9.addCrashSectionCallable("Chunk pos hash", new Callable()
/*     */             {
/*     */               private static final String __OBFID = "CL_00000507";
/*     */               
/*     */               public String call() {
/*  73 */                 return String.valueOf(ChunkCoordIntPair.chunkXZ2Int(p_180701_2_, p_180701_3_));
/*     */               }
/*     */             });
/*  76 */         var9.addCrashSectionCallable("Structure type", new Callable()
/*     */             {
/*     */               public String call()
/*     */               {
/*  80 */                 return MapGenStructure.this.getClass().getCanonicalName();
/*     */               }
/*     */             });
/*  83 */         throw new ReportedException(var8);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public abstract String getStructureName();
/*     */   
/*     */   public boolean func_175794_a(World worldIn, Random p_175794_2_, ChunkCoordIntPair p_175794_3_) {
/*  90 */     func_143027_a(worldIn);
/*  91 */     int var4 = (p_175794_3_.chunkXPos << 4) + 8;
/*  92 */     int var5 = (p_175794_3_.chunkZPos << 4) + 8;
/*  93 */     boolean var6 = false;
/*  94 */     Iterator<StructureStart> var7 = this.structureMap.values().iterator();
/*     */     
/*  96 */     while (var7.hasNext()) {
/*     */       
/*  98 */       StructureStart var8 = var7.next();
/*     */       
/* 100 */       if (var8.isSizeableStructure() && var8.func_175788_a(p_175794_3_) && var8.getBoundingBox().intersectsWith(var4, var5, var4 + 15, var5 + 15)) {
/*     */         
/* 102 */         var8.generateStructure(worldIn, p_175794_2_, new StructureBoundingBox(var4, var5, var4 + 15, var5 + 15));
/* 103 */         var8.func_175787_b(p_175794_3_);
/* 104 */         var6 = true;
/* 105 */         func_143026_a(var8.func_143019_e(), var8.func_143018_f(), var8);
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     return var6;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175795_b(BlockPos p_175795_1_) {
/* 114 */     func_143027_a(this.worldObj);
/* 115 */     return (func_175797_c(p_175795_1_) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   protected StructureStart func_175797_c(BlockPos p_175797_1_) {
/* 120 */     Iterator<StructureStart> var2 = this.structureMap.values().iterator();
/*     */     
/* 122 */     while (var2.hasNext()) {
/*     */       
/* 124 */       StructureStart var3 = var2.next();
/*     */       
/* 126 */       if (var3.isSizeableStructure() && var3.getBoundingBox().func_175898_b((Vec3i)p_175797_1_)) {
/*     */         
/* 128 */         Iterator<StructureComponent> var4 = var3.getComponents().iterator();
/*     */         
/* 130 */         while (var4.hasNext()) {
/*     */           
/* 132 */           StructureComponent var5 = var4.next();
/*     */           
/* 134 */           if (var5.getBoundingBox().func_175898_b((Vec3i)p_175797_1_))
/*     */           {
/* 136 */             return var3;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 142 */     return null;
/*     */   }
/*     */   
/*     */   public boolean func_175796_a(World worldIn, BlockPos p_175796_2_) {
/*     */     StructureStart var4;
/* 147 */     func_143027_a(worldIn);
/* 148 */     Iterator<StructureStart> var3 = this.structureMap.values().iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 153 */       if (!var3.hasNext())
/*     */       {
/* 155 */         return false;
/*     */       }
/*     */       
/* 158 */       var4 = var3.next();
/*     */     }
/* 160 */     while (!var4.isSizeableStructure() || !var4.getBoundingBox().func_175898_b((Vec3i)p_175796_2_));
/*     */     
/* 162 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180706_b(World worldIn, BlockPos p_180706_2_) {
/* 167 */     this.worldObj = worldIn;
/* 168 */     func_143027_a(worldIn);
/* 169 */     this.rand.setSeed(worldIn.getSeed());
/* 170 */     long var3 = this.rand.nextLong();
/* 171 */     long var5 = this.rand.nextLong();
/* 172 */     long var7 = (p_180706_2_.getX() >> 4) * var3;
/* 173 */     long var9 = (p_180706_2_.getZ() >> 4) * var5;
/* 174 */     this.rand.setSeed(var7 ^ var9 ^ worldIn.getSeed());
/* 175 */     func_180701_a(worldIn, p_180706_2_.getX() >> 4, p_180706_2_.getZ() >> 4, 0, 0, (ChunkPrimer)null);
/* 176 */     double var11 = Double.MAX_VALUE;
/* 177 */     BlockPos var13 = null;
/* 178 */     Iterator<StructureStart> var14 = this.structureMap.values().iterator();
/*     */ 
/*     */ 
/*     */     
/* 182 */     while (var14.hasNext()) {
/*     */       
/* 184 */       StructureStart var20 = var14.next();
/*     */       
/* 186 */       if (var20.isSizeableStructure()) {
/*     */         
/* 188 */         StructureComponent var21 = var20.getComponents().get(0);
/* 189 */         BlockPos var17 = var21.func_180776_a();
/* 190 */         double var18 = var17.distanceSq((Vec3i)p_180706_2_);
/*     */         
/* 192 */         if (var18 < var11) {
/*     */           
/* 194 */           var11 = var18;
/* 195 */           var13 = var17;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 200 */     if (var13 != null)
/*     */     {
/* 202 */       return var13;
/*     */     }
/*     */ 
/*     */     
/* 206 */     List var201 = getCoordList();
/*     */     
/* 208 */     if (var201 != null) {
/*     */       
/* 210 */       BlockPos var211 = null;
/* 211 */       Iterator<BlockPos> var22 = var201.iterator();
/*     */       
/* 213 */       while (var22.hasNext()) {
/*     */         
/* 215 */         BlockPos var17 = var22.next();
/* 216 */         double var18 = var17.distanceSq((Vec3i)p_180706_2_);
/*     */         
/* 218 */         if (var18 < var11) {
/*     */           
/* 220 */           var11 = var18;
/* 221 */           var211 = var17;
/*     */         } 
/*     */       } 
/*     */       
/* 225 */       return var211;
/*     */     } 
/*     */ 
/*     */     
/* 229 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getCoordList() {
/* 240 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_143027_a(World worldIn) {
/* 245 */     if (this.field_143029_e == null) {
/*     */ 
/*     */ 
/*     */       
/* 249 */       if (Reflector.ForgeWorld_getPerWorldStorage.exists()) {
/*     */         
/* 251 */         MapStorage var2 = (MapStorage)Reflector.call(worldIn, Reflector.ForgeWorld_getPerWorldStorage, new Object[0]);
/* 252 */         this.field_143029_e = (MapGenStructureData)var2.loadData(MapGenStructureData.class, getStructureName());
/*     */       }
/*     */       else {
/*     */         
/* 256 */         this.field_143029_e = (MapGenStructureData)worldIn.loadItemData(MapGenStructureData.class, getStructureName());
/*     */       } 
/*     */       
/* 259 */       if (this.field_143029_e == null) {
/*     */         
/* 261 */         this.field_143029_e = new MapGenStructureData(getStructureName());
/*     */         
/* 263 */         if (Reflector.ForgeWorld_getPerWorldStorage.exists())
/*     */         {
/* 265 */           MapStorage var2 = (MapStorage)Reflector.call(worldIn, Reflector.ForgeWorld_getPerWorldStorage, new Object[0]);
/* 266 */           var2.setData(getStructureName(), this.field_143029_e);
/*     */         }
/*     */         else
/*     */         {
/* 270 */           worldIn.setItemData(getStructureName(), this.field_143029_e);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 275 */         NBTTagCompound var21 = this.field_143029_e.func_143041_a();
/* 276 */         Iterator<String> var3 = var21.getKeySet().iterator();
/*     */         
/* 278 */         while (var3.hasNext()) {
/*     */           
/* 280 */           String var4 = var3.next();
/* 281 */           NBTBase var5 = var21.getTag(var4);
/*     */           
/* 283 */           if (var5.getId() == 10) {
/*     */             
/* 285 */             NBTTagCompound var6 = (NBTTagCompound)var5;
/*     */             
/* 287 */             if (var6.hasKey("ChunkX") && var6.hasKey("ChunkZ")) {
/*     */               
/* 289 */               int var7 = var6.getInteger("ChunkX");
/* 290 */               int var8 = var6.getInteger("ChunkZ");
/* 291 */               StructureStart var9 = MapGenStructureIO.func_143035_a(var6, worldIn);
/*     */               
/* 293 */               if (var9 != null) {
/*     */                 
/* 295 */                 this.structureMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(var7, var8)), var9);
/* 296 */                 this.structureLongMap.add(ChunkCoordIntPair.chunkXZ2Int(var7, var8), var9);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_143026_a(int p_143026_1_, int p_143026_2_, StructureStart p_143026_3_) {
/* 307 */     this.field_143029_e.func_143043_a(p_143026_3_.func_143021_a(p_143026_1_, p_143026_2_), p_143026_1_, p_143026_2_);
/* 308 */     this.field_143029_e.markDirty();
/*     */   }
/*     */   
/*     */   protected abstract boolean canSpawnStructureAtCoords(int paramInt1, int paramInt2);
/*     */   
/*     */   protected abstract StructureStart getStructureStart(int paramInt1, int paramInt2);
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\MapGenStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */