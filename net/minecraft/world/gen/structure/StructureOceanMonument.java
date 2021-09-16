/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.monster.EntityGuardian;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ 
/*     */ public class StructureOceanMonument
/*     */   extends MapGenStructure
/*     */ {
/*  26 */   public static final List field_175802_d = Arrays.asList(new BiomeGenBase[] { BiomeGenBase.ocean, BiomeGenBase.deepOcean, BiomeGenBase.river, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver });
/*  27 */   private static final List field_175803_h = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   private int field_175800_f = 32;
/*  33 */   private int field_175801_g = 5;
/*     */   
/*     */   private static final String __OBFID = "CL_00001996";
/*     */   
/*     */   public StructureOceanMonument(Map p_i45608_1_) {
/*  38 */     this();
/*  39 */     Iterator<Map.Entry> var2 = p_i45608_1_.entrySet().iterator();
/*     */     
/*  41 */     while (var2.hasNext()) {
/*     */       
/*  43 */       Map.Entry var3 = var2.next();
/*     */       
/*  45 */       if (((String)var3.getKey()).equals("spacing")) {
/*     */         
/*  47 */         this.field_175800_f = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_175800_f, 1); continue;
/*     */       } 
/*  49 */       if (((String)var3.getKey()).equals("separation"))
/*     */       {
/*  51 */         this.field_175801_g = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_175801_g, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStructureName() {
/*  58 */     return "Monument";
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_) {
/*  63 */     int var3 = p_75047_1_;
/*  64 */     int var4 = p_75047_2_;
/*     */     
/*  66 */     if (p_75047_1_ < 0)
/*     */     {
/*  68 */       p_75047_1_ -= this.field_175800_f - 1;
/*     */     }
/*     */     
/*  71 */     if (p_75047_2_ < 0)
/*     */     {
/*  73 */       p_75047_2_ -= this.field_175800_f - 1;
/*     */     }
/*     */     
/*  76 */     int var5 = p_75047_1_ / this.field_175800_f;
/*  77 */     int var6 = p_75047_2_ / this.field_175800_f;
/*  78 */     Random var7 = this.worldObj.setRandomSeed(var5, var6, 10387313);
/*  79 */     var5 *= this.field_175800_f;
/*  80 */     var6 *= this.field_175800_f;
/*  81 */     var5 += (var7.nextInt(this.field_175800_f - this.field_175801_g) + var7.nextInt(this.field_175800_f - this.field_175801_g)) / 2;
/*  82 */     var6 += (var7.nextInt(this.field_175800_f - this.field_175801_g) + var7.nextInt(this.field_175800_f - this.field_175801_g)) / 2;
/*     */     
/*  84 */     if (var3 == var5 && var4 == var6) {
/*     */       
/*  86 */       if (this.worldObj.getWorldChunkManager().func_180300_a(new BlockPos(var3 * 16 + 8, 64, var4 * 16 + 8), null) != BiomeGenBase.deepOcean)
/*     */       {
/*  88 */         return false;
/*     */       }
/*     */       
/*  91 */       boolean var8 = this.worldObj.getWorldChunkManager().areBiomesViable(var3 * 16 + 8, var4 * 16 + 8, 29, field_175802_d);
/*     */       
/*  93 */       if (var8)
/*     */       {
/*  95 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_) {
/* 104 */     return new StartMonument(this.worldObj, this.rand, p_75049_1_, p_75049_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_175799_b() {
/* 109 */     return field_175803_h;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 114 */     field_175803_h.add(new BiomeGenBase.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
/*     */   }
/*     */   
/*     */   public StructureOceanMonument() {}
/*     */   
/* 119 */   public static class StartMonument extends StructureStart { private Set field_175791_c = Sets.newHashSet();
/*     */     
/*     */     private boolean field_175790_d;
/*     */     private static final String __OBFID = "CL_00001995";
/*     */     
/*     */     public StartMonument() {}
/*     */     
/*     */     public StartMonument(World worldIn, Random p_i45607_2_, int p_i45607_3_, int p_i45607_4_) {
/* 127 */       super(p_i45607_3_, p_i45607_4_);
/* 128 */       func_175789_b(worldIn, p_i45607_2_, p_i45607_3_, p_i45607_4_);
/*     */     }
/*     */ 
/*     */     
/*     */     private void func_175789_b(World worldIn, Random p_175789_2_, int p_175789_3_, int p_175789_4_) {
/* 133 */       p_175789_2_.setSeed(worldIn.getSeed());
/* 134 */       long var5 = p_175789_2_.nextLong();
/* 135 */       long var7 = p_175789_2_.nextLong();
/* 136 */       long var9 = p_175789_3_ * var5;
/* 137 */       long var11 = p_175789_4_ * var7;
/* 138 */       p_175789_2_.setSeed(var9 ^ var11 ^ worldIn.getSeed());
/* 139 */       int var13 = p_175789_3_ * 16 + 8 - 29;
/* 140 */       int var14 = p_175789_4_ * 16 + 8 - 29;
/* 141 */       EnumFacing var15 = EnumFacing.Plane.HORIZONTAL.random(p_175789_2_);
/* 142 */       this.components.add(new StructureOceanMonumentPieces.MonumentBuilding(p_175789_2_, var13, var14, var15));
/* 143 */       updateBoundingBox();
/* 144 */       this.field_175790_d = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void generateStructure(World worldIn, Random p_75068_2_, StructureBoundingBox p_75068_3_) {
/* 149 */       if (!this.field_175790_d) {
/*     */         
/* 151 */         this.components.clear();
/* 152 */         func_175789_b(worldIn, p_75068_2_, func_143019_e(), func_143018_f());
/*     */       } 
/*     */       
/* 155 */       super.generateStructure(worldIn, p_75068_2_, p_75068_3_);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_175788_a(ChunkCoordIntPair p_175788_1_) {
/* 160 */       return this.field_175791_c.contains(p_175788_1_) ? false : super.func_175788_a(p_175788_1_);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_175787_b(ChunkCoordIntPair p_175787_1_) {
/* 165 */       super.func_175787_b(p_175787_1_);
/* 166 */       this.field_175791_c.add(p_175787_1_);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_143022_a(NBTTagCompound p_143022_1_) {
/* 171 */       super.func_143022_a(p_143022_1_);
/* 172 */       NBTTagList var2 = new NBTTagList();
/* 173 */       Iterator<ChunkCoordIntPair> var3 = this.field_175791_c.iterator();
/*     */       
/* 175 */       while (var3.hasNext()) {
/*     */         
/* 177 */         ChunkCoordIntPair var4 = var3.next();
/* 178 */         NBTTagCompound var5 = new NBTTagCompound();
/* 179 */         var5.setInteger("X", var4.chunkXPos);
/* 180 */         var5.setInteger("Z", var4.chunkZPos);
/* 181 */         var2.appendTag((NBTBase)var5);
/*     */       } 
/*     */       
/* 184 */       p_143022_1_.setTag("Processed", (NBTBase)var2);
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_143017_b(NBTTagCompound p_143017_1_) {
/* 189 */       super.func_143017_b(p_143017_1_);
/*     */       
/* 191 */       if (p_143017_1_.hasKey("Processed", 9)) {
/*     */         
/* 193 */         NBTTagList var2 = p_143017_1_.getTagList("Processed", 10);
/*     */         
/* 195 */         for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */           
/* 197 */           NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 198 */           this.field_175791_c.add(new ChunkCoordIntPair(var4.getInteger("X"), var4.getInteger("Z")));
/*     */         } 
/*     */       } 
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureOceanMonument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */