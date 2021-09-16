/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ 
/*     */ public class MapGenVillage
/*     */   extends MapGenStructure
/*     */ {
/*  17 */   public static final List villageSpawnBiomes = Arrays.asList(new BiomeGenBase[] { BiomeGenBase.plains, BiomeGenBase.desert, BiomeGenBase.savanna });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int terrainType;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  27 */   private int field_82665_g = 32;
/*  28 */   private int field_82666_h = 8;
/*     */   
/*     */   private static final String __OBFID = "CL_00000514";
/*     */   
/*     */   public MapGenVillage(Map p_i2093_1_) {
/*  33 */     this();
/*  34 */     Iterator<Map.Entry> var2 = p_i2093_1_.entrySet().iterator();
/*     */     
/*  36 */     while (var2.hasNext()) {
/*     */       
/*  38 */       Map.Entry var3 = var2.next();
/*     */       
/*  40 */       if (((String)var3.getKey()).equals("size")) {
/*     */         
/*  42 */         this.terrainType = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.terrainType, 0); continue;
/*     */       } 
/*  44 */       if (((String)var3.getKey()).equals("distance"))
/*     */       {
/*  46 */         this.field_82665_g = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_82665_g, this.field_82666_h + 1); } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public MapGenVillage() {}
/*     */   
/*     */   public String getStructureName() {
/*  53 */     return "Village";
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_) {
/*  58 */     int var3 = p_75047_1_;
/*  59 */     int var4 = p_75047_2_;
/*     */     
/*  61 */     if (p_75047_1_ < 0)
/*     */     {
/*  63 */       p_75047_1_ -= this.field_82665_g - 1;
/*     */     }
/*     */     
/*  66 */     if (p_75047_2_ < 0)
/*     */     {
/*  68 */       p_75047_2_ -= this.field_82665_g - 1;
/*     */     }
/*     */     
/*  71 */     int var5 = p_75047_1_ / this.field_82665_g;
/*  72 */     int var6 = p_75047_2_ / this.field_82665_g;
/*  73 */     Random var7 = this.worldObj.setRandomSeed(var5, var6, 10387312);
/*  74 */     var5 *= this.field_82665_g;
/*  75 */     var6 *= this.field_82665_g;
/*  76 */     var5 += var7.nextInt(this.field_82665_g - this.field_82666_h);
/*  77 */     var6 += var7.nextInt(this.field_82665_g - this.field_82666_h);
/*     */     
/*  79 */     if (var3 == var5 && var4 == var6) {
/*     */       
/*  81 */       boolean var8 = this.worldObj.getWorldChunkManager().areBiomesViable(var3 * 16 + 8, var4 * 16 + 8, 0, villageSpawnBiomes);
/*     */       
/*  83 */       if (var8)
/*     */       {
/*  85 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  89 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_) {
/*  94 */     return new Start(this.worldObj, this.rand, p_75049_1_, p_75049_2_, this.terrainType);
/*     */   }
/*     */   
/*     */   public static class Start
/*     */     extends StructureStart
/*     */   {
/*     */     private boolean hasMoreThanTwoComponents;
/*     */     private static final String __OBFID = "CL_00000515";
/*     */     
/*     */     public Start() {}
/*     */     
/*     */     public Start(World worldIn, Random p_i2092_2_, int p_i2092_3_, int p_i2092_4_, int p_i2092_5_) {
/* 106 */       super(p_i2092_3_, p_i2092_4_);
/* 107 */       List var6 = StructureVillagePieces.getStructureVillageWeightedPieceList(p_i2092_2_, p_i2092_5_);
/* 108 */       StructureVillagePieces.Start var7 = new StructureVillagePieces.Start(worldIn.getWorldChunkManager(), 0, p_i2092_2_, (p_i2092_3_ << 4) + 2, (p_i2092_4_ << 4) + 2, var6, p_i2092_5_);
/* 109 */       this.components.add(var7);
/* 110 */       var7.buildComponent(var7, this.components, p_i2092_2_);
/* 111 */       List<StructureComponent> var8 = var7.field_74930_j;
/* 112 */       List<StructureComponent> var9 = var7.field_74932_i;
/*     */ 
/*     */       
/* 115 */       while (!var8.isEmpty() || !var9.isEmpty()) {
/*     */ 
/*     */ 
/*     */         
/* 119 */         if (var8.isEmpty()) {
/*     */           
/* 121 */           int j = p_i2092_2_.nextInt(var9.size());
/* 122 */           StructureComponent structureComponent = var9.remove(j);
/* 123 */           structureComponent.buildComponent(var7, this.components, p_i2092_2_);
/*     */           
/*     */           continue;
/*     */         } 
/* 127 */         int i = p_i2092_2_.nextInt(var8.size());
/* 128 */         StructureComponent var11 = var8.remove(i);
/* 129 */         var11.buildComponent(var7, this.components, p_i2092_2_);
/*     */       } 
/*     */ 
/*     */       
/* 133 */       updateBoundingBox();
/* 134 */       int var10 = 0;
/* 135 */       Iterator<StructureComponent> var13 = this.components.iterator();
/*     */       
/* 137 */       while (var13.hasNext()) {
/*     */         
/* 139 */         StructureComponent var12 = var13.next();
/*     */         
/* 141 */         if (!(var12 instanceof StructureVillagePieces.Road))
/*     */         {
/* 143 */           var10++;
/*     */         }
/*     */       } 
/*     */       
/* 147 */       this.hasMoreThanTwoComponents = (var10 > 2);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isSizeableStructure() {
/* 152 */       return this.hasMoreThanTwoComponents;
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_143022_a(NBTTagCompound p_143022_1_) {
/* 157 */       super.func_143022_a(p_143022_1_);
/* 158 */       p_143022_1_.setBoolean("Valid", this.hasMoreThanTwoComponents);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_143017_b(NBTTagCompound p_143017_1_) {
/* 163 */       super.func_143017_b(p_143017_1_);
/* 164 */       this.hasMoreThanTwoComponents = p_143017_1_.getBoolean("Valid");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\MapGenVillage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */