/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.monster.EntityWitch;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ public class MapGenScatteredFeature
/*     */   extends MapGenStructure
/*     */ {
/*  18 */   private static final List biomelist = Arrays.asList(new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.desertHills, BiomeGenBase.jungle, BiomeGenBase.jungleHills, BiomeGenBase.swampland });
/*     */ 
/*     */   
/*     */   private List scatteredFeatureSpawnList;
/*     */ 
/*     */   
/*     */   private int maxDistanceBetweenScatteredFeatures;
/*     */   
/*     */   private int minDistanceBetweenScatteredFeatures;
/*     */   
/*     */   private static final String __OBFID = "CL_00000471";
/*     */ 
/*     */   
/*     */   public MapGenScatteredFeature() {
/*  32 */     this.scatteredFeatureSpawnList = Lists.newArrayList();
/*  33 */     this.maxDistanceBetweenScatteredFeatures = 32;
/*  34 */     this.minDistanceBetweenScatteredFeatures = 8;
/*  35 */     this.scatteredFeatureSpawnList.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 1, 1, 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public MapGenScatteredFeature(Map p_i2061_1_) {
/*  40 */     this();
/*  41 */     Iterator<Map.Entry> var2 = p_i2061_1_.entrySet().iterator();
/*     */     
/*  43 */     while (var2.hasNext()) {
/*     */       
/*  45 */       Map.Entry var3 = var2.next();
/*     */       
/*  47 */       if (((String)var3.getKey()).equals("distance"))
/*     */       {
/*  49 */         this.maxDistanceBetweenScatteredFeatures = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.maxDistanceBetweenScatteredFeatures, this.minDistanceBetweenScatteredFeatures + 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStructureName() {
/*  56 */     return "Temple";
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_) {
/*  61 */     int var3 = p_75047_1_;
/*  62 */     int var4 = p_75047_2_;
/*     */     
/*  64 */     if (p_75047_1_ < 0)
/*     */     {
/*  66 */       p_75047_1_ -= this.maxDistanceBetweenScatteredFeatures - 1;
/*     */     }
/*     */     
/*  69 */     if (p_75047_2_ < 0)
/*     */     {
/*  71 */       p_75047_2_ -= this.maxDistanceBetweenScatteredFeatures - 1;
/*     */     }
/*     */     
/*  74 */     int var5 = p_75047_1_ / this.maxDistanceBetweenScatteredFeatures;
/*  75 */     int var6 = p_75047_2_ / this.maxDistanceBetweenScatteredFeatures;
/*  76 */     Random var7 = this.worldObj.setRandomSeed(var5, var6, 14357617);
/*  77 */     var5 *= this.maxDistanceBetweenScatteredFeatures;
/*  78 */     var6 *= this.maxDistanceBetweenScatteredFeatures;
/*  79 */     var5 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
/*  80 */     var6 += var7.nextInt(this.maxDistanceBetweenScatteredFeatures - this.minDistanceBetweenScatteredFeatures);
/*     */     
/*  82 */     if (var3 == var5 && var4 == var6) {
/*     */       
/*  84 */       BiomeGenBase var8 = this.worldObj.getWorldChunkManager().func_180631_a(new BlockPos(var3 * 16 + 8, 0, var4 * 16 + 8));
/*     */       
/*  86 */       if (var8 == null)
/*     */       {
/*  88 */         return false;
/*     */       }
/*     */       
/*  91 */       Iterator<BiomeGenBase> var9 = biomelist.iterator();
/*     */       
/*  93 */       while (var9.hasNext()) {
/*     */         
/*  95 */         BiomeGenBase var10 = var9.next();
/*     */         
/*  97 */         if (var8 == var10)
/*     */         {
/*  99 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_) {
/* 109 */     return new Start(this.worldObj, this.rand, p_75049_1_, p_75049_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175798_a(BlockPos p_175798_1_) {
/* 114 */     StructureStart var2 = func_175797_c(p_175798_1_);
/*     */     
/* 116 */     if (var2 != null && var2 instanceof Start && !var2.components.isEmpty()) {
/*     */       
/* 118 */       StructureComponent var3 = var2.components.getFirst();
/* 119 */       return var3 instanceof ComponentScatteredFeaturePieces.SwampHut;
/*     */     } 
/*     */ 
/*     */     
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getScatteredFeatureSpawnList() {
/* 132 */     return this.scatteredFeatureSpawnList;
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart
/*     */   {
/*     */     private static final String __OBFID = "CL_00000472";
/*     */     
/*     */     public Start() {}
/*     */     
/*     */     public Start(World worldIn, Random p_i2060_2_, int p_i2060_3_, int p_i2060_4_) {
/* 143 */       super(p_i2060_3_, p_i2060_4_);
/* 144 */       BiomeGenBase var5 = worldIn.getBiomeGenForCoords(new BlockPos(p_i2060_3_ * 16 + 8, 0, p_i2060_4_ * 16 + 8));
/*     */       
/* 146 */       if (var5 != BiomeGenBase.jungle && var5 != BiomeGenBase.jungleHills) {
/*     */         
/* 148 */         if (var5 == BiomeGenBase.swampland)
/*     */         {
/* 150 */           ComponentScatteredFeaturePieces.SwampHut var7 = new ComponentScatteredFeaturePieces.SwampHut(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
/* 151 */           this.components.add(var7);
/*     */         }
/* 153 */         else if (var5 == BiomeGenBase.desert || var5 == BiomeGenBase.desertHills)
/*     */         {
/* 155 */           ComponentScatteredFeaturePieces.DesertPyramid var8 = new ComponentScatteredFeaturePieces.DesertPyramid(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
/* 156 */           this.components.add(var8);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 161 */         ComponentScatteredFeaturePieces.JunglePyramid var6 = new ComponentScatteredFeaturePieces.JunglePyramid(p_i2060_2_, p_i2060_3_ * 16, p_i2060_4_ * 16);
/* 162 */         this.components.add(var6);
/*     */       } 
/*     */       
/* 165 */       updateBoundingBox();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\MapGenScatteredFeature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */