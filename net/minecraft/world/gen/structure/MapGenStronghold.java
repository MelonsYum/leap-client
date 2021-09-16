/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapGenStronghold
/*     */   extends MapGenStructure
/*     */ {
/*  31 */   private ChunkCoordIntPair[] structureCoords = new ChunkCoordIntPair[3];
/*  32 */   private double field_82671_h = 32.0D;
/*  33 */   private int field_82672_i = 3;
/*  34 */   private List field_151546_e = Lists.newArrayList(); public MapGenStronghold() {
/*  35 */     BiomeGenBase[] var1 = BiomeGenBase.getBiomeGenArray();
/*  36 */     int var2 = var1.length;
/*     */     
/*  38 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       
/*  40 */       BiomeGenBase var4 = var1[var3];
/*     */       
/*  42 */       if (var4 != null && var4.minHeight > 0.0F)
/*     */       {
/*  44 */         this.field_151546_e.add(var4); } 
/*     */     } 
/*     */   }
/*     */   private boolean ranBiomeCheck;
/*     */   private static final String __OBFID = "CL_00000481";
/*     */   
/*     */   public MapGenStronghold(Map p_i2068_1_) {
/*  51 */     this();
/*  52 */     Iterator<Map.Entry> var2 = p_i2068_1_.entrySet().iterator();
/*     */     
/*  54 */     while (var2.hasNext()) {
/*     */       
/*  56 */       Map.Entry var3 = var2.next();
/*     */       
/*  58 */       if (((String)var3.getKey()).equals("distance")) {
/*     */         
/*  60 */         this.field_82671_h = MathHelper.parseDoubleWithDefaultAndMax((String)var3.getValue(), this.field_82671_h, 1.0D); continue;
/*     */       } 
/*  62 */       if (((String)var3.getKey()).equals("count")) {
/*     */         
/*  64 */         this.structureCoords = new ChunkCoordIntPair[MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.structureCoords.length, 1)]; continue;
/*     */       } 
/*  66 */       if (((String)var3.getKey()).equals("spread"))
/*     */       {
/*  68 */         this.field_82672_i = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_82672_i, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStructureName() {
/*  75 */     return "Stronghold";
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_) {
/*  80 */     if (!this.ranBiomeCheck) {
/*     */       
/*  82 */       Random var3 = new Random();
/*  83 */       var3.setSeed(this.worldObj.getSeed());
/*  84 */       double var4 = var3.nextDouble() * Math.PI * 2.0D;
/*  85 */       int var6 = 1;
/*     */       
/*  87 */       for (int var7 = 0; var7 < this.structureCoords.length; var7++) {
/*     */         
/*  89 */         double var8 = (1.25D * var6 + var3.nextDouble()) * this.field_82671_h * var6;
/*  90 */         int var10 = (int)Math.round(Math.cos(var4) * var8);
/*  91 */         int var11 = (int)Math.round(Math.sin(var4) * var8);
/*  92 */         BlockPos var12 = this.worldObj.getWorldChunkManager().findBiomePosition((var10 << 4) + 8, (var11 << 4) + 8, 112, this.field_151546_e, var3);
/*     */         
/*  94 */         if (var12 != null) {
/*     */           
/*  96 */           var10 = var12.getX() >> 4;
/*  97 */           var11 = var12.getZ() >> 4;
/*     */         } 
/*     */         
/* 100 */         this.structureCoords[var7] = new ChunkCoordIntPair(var10, var11);
/* 101 */         var4 += 6.283185307179586D * var6 / this.field_82672_i;
/*     */         
/* 103 */         if (var7 == this.field_82672_i) {
/*     */           
/* 105 */           var6 += 2 + var3.nextInt(5);
/* 106 */           this.field_82672_i += 1 + var3.nextInt(2);
/*     */         } 
/*     */       } 
/*     */       
/* 110 */       this.ranBiomeCheck = true;
/*     */     } 
/*     */     
/* 113 */     ChunkCoordIntPair[] var13 = this.structureCoords;
/* 114 */     int var14 = var13.length;
/*     */     
/* 116 */     for (int var5 = 0; var5 < var14; var5++) {
/*     */       
/* 118 */       ChunkCoordIntPair var15 = var13[var5];
/*     */       
/* 120 */       if (p_75047_1_ == var15.chunkXPos && p_75047_2_ == var15.chunkZPos)
/*     */       {
/* 122 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 126 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getCoordList() {
/* 135 */     ArrayList<BlockPos> var1 = Lists.newArrayList();
/* 136 */     ChunkCoordIntPair[] var2 = this.structureCoords;
/* 137 */     int var3 = var2.length;
/*     */     
/* 139 */     for (int var4 = 0; var4 < var3; var4++) {
/*     */       
/* 141 */       ChunkCoordIntPair var5 = var2[var4];
/*     */       
/* 143 */       if (var5 != null)
/*     */       {
/* 145 */         var1.add(var5.getCenterBlock(64));
/*     */       }
/*     */     } 
/*     */     
/* 149 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_) {
/* 156 */     for (Start var3 = new Start(this.worldObj, this.rand, p_75049_1_, p_75049_2_); var3.getComponents().isEmpty() || ((StructureStrongholdPieces.Stairs2)var3.getComponents().get(0)).strongholdPortalRoom == null; var3 = new Start(this.worldObj, this.rand, p_75049_1_, p_75049_2_));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     return var3;
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart
/*     */   {
/*     */     private static final String __OBFID = "CL_00000482";
/*     */     
/*     */     public Start() {}
/*     */     
/*     */     public Start(World worldIn, Random p_i2067_2_, int p_i2067_3_, int p_i2067_4_) {
/* 172 */       super(p_i2067_3_, p_i2067_4_);
/* 173 */       StructureStrongholdPieces.prepareStructurePieces();
/* 174 */       StructureStrongholdPieces.Stairs2 var5 = new StructureStrongholdPieces.Stairs2(0, p_i2067_2_, (p_i2067_3_ << 4) + 2, (p_i2067_4_ << 4) + 2);
/* 175 */       this.components.add(var5);
/* 176 */       var5.buildComponent(var5, this.components, p_i2067_2_);
/* 177 */       List<StructureComponent> var6 = var5.field_75026_c;
/*     */       
/* 179 */       while (!var6.isEmpty()) {
/*     */         
/* 181 */         int var7 = p_i2067_2_.nextInt(var6.size());
/* 182 */         StructureComponent var8 = var6.remove(var7);
/* 183 */         var8.buildComponent(var5, this.components, p_i2067_2_);
/*     */       } 
/*     */       
/* 186 */       updateBoundingBox();
/* 187 */       markAvailableHeight(worldIn, p_i2067_2_, 10);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\MapGenStronghold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */