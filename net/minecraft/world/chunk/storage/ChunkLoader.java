/*     */ package net.minecraft.world.chunk.storage;
/*     */ 
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.WorldChunkManager;
/*     */ import net.minecraft.world.chunk.NibbleArray;
/*     */ 
/*     */ public class ChunkLoader
/*     */ {
/*     */   private static final String __OBFID = "CL_00000379";
/*     */   
/*     */   public static AnvilConverterData load(NBTTagCompound nbt) {
/*  16 */     int var1 = nbt.getInteger("xPos");
/*  17 */     int var2 = nbt.getInteger("zPos");
/*  18 */     AnvilConverterData var3 = new AnvilConverterData(var1, var2);
/*  19 */     var3.blocks = nbt.getByteArray("Blocks");
/*  20 */     var3.data = new NibbleArrayReader(nbt.getByteArray("Data"), 7);
/*  21 */     var3.skyLight = new NibbleArrayReader(nbt.getByteArray("SkyLight"), 7);
/*  22 */     var3.blockLight = new NibbleArrayReader(nbt.getByteArray("BlockLight"), 7);
/*  23 */     var3.heightmap = nbt.getByteArray("HeightMap");
/*  24 */     var3.terrainPopulated = nbt.getBoolean("TerrainPopulated");
/*  25 */     var3.entities = nbt.getTagList("Entities", 10);
/*  26 */     var3.tileEntities = nbt.getTagList("TileEntities", 10);
/*  27 */     var3.tileTicks = nbt.getTagList("TileTicks", 10);
/*     */ 
/*     */     
/*     */     try {
/*  31 */       var3.lastUpdated = nbt.getLong("LastUpdate");
/*     */     }
/*  33 */     catch (ClassCastException var5) {
/*     */       
/*  35 */       var3.lastUpdated = nbt.getInteger("LastUpdate");
/*     */     } 
/*     */     
/*  38 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void convertToAnvilFormat(AnvilConverterData p_76690_0_, NBTTagCompound p_76690_1_, WorldChunkManager p_76690_2_) {
/*  43 */     p_76690_1_.setInteger("xPos", p_76690_0_.x);
/*  44 */     p_76690_1_.setInteger("zPos", p_76690_0_.z);
/*  45 */     p_76690_1_.setLong("LastUpdate", p_76690_0_.lastUpdated);
/*  46 */     int[] var3 = new int[p_76690_0_.heightmap.length];
/*     */     
/*  48 */     for (int var4 = 0; var4 < p_76690_0_.heightmap.length; var4++)
/*     */     {
/*  50 */       var3[var4] = p_76690_0_.heightmap[var4];
/*     */     }
/*     */     
/*  53 */     p_76690_1_.setIntArray("HeightMap", var3);
/*  54 */     p_76690_1_.setBoolean("TerrainPopulated", p_76690_0_.terrainPopulated);
/*  55 */     NBTTagList var16 = new NBTTagList();
/*     */ 
/*     */     
/*  58 */     for (int var5 = 0; var5 < 8; var5++) {
/*     */       
/*  60 */       boolean var6 = true;
/*     */       
/*  62 */       for (int var7 = 0; var7 < 16 && var6; var7++) {
/*     */         
/*  64 */         int var8 = 0;
/*     */         
/*  66 */         while (var8 < 16 && var6) {
/*     */           
/*  68 */           int var9 = 0;
/*     */ 
/*     */ 
/*     */           
/*  72 */           while (var9 < 16) {
/*     */             
/*  74 */             int var10 = var7 << 11 | var9 << 7 | var8 + (var5 << 4);
/*  75 */             byte var11 = p_76690_0_.blocks[var10];
/*     */             
/*  77 */             if (var11 == 0) {
/*     */               
/*  79 */               var9++;
/*     */               
/*     */               continue;
/*     */             } 
/*  83 */             var6 = false;
/*     */           } 
/*     */           
/*  86 */           var8++;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  92 */       if (!var6) {
/*     */         
/*  94 */         byte[] var19 = new byte[4096];
/*  95 */         NibbleArray var20 = new NibbleArray();
/*  96 */         NibbleArray var21 = new NibbleArray();
/*  97 */         NibbleArray var22 = new NibbleArray();
/*     */         
/*  99 */         for (int var23 = 0; var23 < 16; var23++) {
/*     */           
/* 101 */           for (int var12 = 0; var12 < 16; var12++) {
/*     */             
/* 103 */             for (int var13 = 0; var13 < 16; var13++) {
/*     */               
/* 105 */               int var14 = var23 << 11 | var13 << 7 | var12 + (var5 << 4);
/* 106 */               byte var15 = p_76690_0_.blocks[var14];
/* 107 */               var19[var12 << 8 | var13 << 4 | var23] = (byte)(var15 & 0xFF);
/* 108 */               var20.set(var23, var12, var13, p_76690_0_.data.get(var23, var12 + (var5 << 4), var13));
/* 109 */               var21.set(var23, var12, var13, p_76690_0_.skyLight.get(var23, var12 + (var5 << 4), var13));
/* 110 */               var22.set(var23, var12, var13, p_76690_0_.blockLight.get(var23, var12 + (var5 << 4), var13));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 115 */         NBTTagCompound var24 = new NBTTagCompound();
/* 116 */         var24.setByte("Y", (byte)(var5 & 0xFF));
/* 117 */         var24.setByteArray("Blocks", var19);
/* 118 */         var24.setByteArray("Data", var20.getData());
/* 119 */         var24.setByteArray("SkyLight", var21.getData());
/* 120 */         var24.setByteArray("BlockLight", var22.getData());
/* 121 */         var16.appendTag((NBTBase)var24);
/*     */       } 
/*     */     } 
/*     */     
/* 125 */     p_76690_1_.setTag("Sections", (NBTBase)var16);
/* 126 */     byte[] var17 = new byte[256];
/*     */     
/* 128 */     for (int var18 = 0; var18 < 16; var18++) {
/*     */       
/* 130 */       for (int var7 = 0; var7 < 16; var7++)
/*     */       {
/* 132 */         var17[var7 << 4 | var18] = (byte)((p_76690_2_.func_180300_a(new BlockPos(p_76690_0_.x << 4 | var18, 0, p_76690_0_.z << 4 | var7), BiomeGenBase.field_180279_ad)).biomeID & 0xFF);
/*     */       }
/*     */     } 
/*     */     
/* 136 */     p_76690_1_.setByteArray("Biomes", var17);
/* 137 */     p_76690_1_.setTag("Entities", (NBTBase)p_76690_0_.entities);
/* 138 */     p_76690_1_.setTag("TileEntities", (NBTBase)p_76690_0_.tileEntities);
/*     */     
/* 140 */     if (p_76690_0_.tileTicks != null)
/*     */     {
/* 142 */       p_76690_1_.setTag("TileTicks", (NBTBase)p_76690_0_.tileTicks);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class AnvilConverterData
/*     */   {
/*     */     public long lastUpdated;
/*     */     public boolean terrainPopulated;
/*     */     public byte[] heightmap;
/*     */     public NibbleArrayReader blockLight;
/*     */     public NibbleArrayReader skyLight;
/*     */     public NibbleArrayReader data;
/*     */     public byte[] blocks;
/*     */     public NBTTagList entities;
/*     */     public NBTTagList tileEntities;
/*     */     public NBTTagList tileTicks;
/*     */     public final int x;
/*     */     public final int z;
/*     */     private static final String __OBFID = "CL_00000380";
/*     */     
/*     */     public AnvilConverterData(int p_i1999_1_, int p_i1999_2_) {
/* 164 */       this.x = p_i1999_1_;
/* 165 */       this.z = p_i1999_2_;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\chunk\storage\ChunkLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */