/*     */ package net.minecraft.world.gen.structure;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public abstract class StructureStart
/*     */ {
/*  14 */   protected LinkedList components = new LinkedList();
/*     */   
/*     */   protected StructureBoundingBox boundingBox;
/*     */   private int field_143024_c;
/*     */   private int field_143023_d;
/*     */   private static final String __OBFID = "CL_00000513";
/*     */   
/*     */   public StructureStart() {}
/*     */   
/*     */   public StructureStart(int p_i43002_1_, int p_i43002_2_) {
/*  24 */     this.field_143024_c = p_i43002_1_;
/*  25 */     this.field_143023_d = p_i43002_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureBoundingBox getBoundingBox() {
/*  30 */     return this.boundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public LinkedList getComponents() {
/*  35 */     return this.components;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateStructure(World worldIn, Random p_75068_2_, StructureBoundingBox p_75068_3_) {
/*  43 */     Iterator<StructureComponent> var4 = this.components.iterator();
/*     */     
/*  45 */     while (var4.hasNext()) {
/*     */       
/*  47 */       StructureComponent var5 = var4.next();
/*     */       
/*  49 */       if (var5.getBoundingBox().intersectsWith(p_75068_3_) && !var5.addComponentParts(worldIn, p_75068_2_, p_75068_3_))
/*     */       {
/*  51 */         var4.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateBoundingBox() {
/*  61 */     this.boundingBox = StructureBoundingBox.getNewBoundingBox();
/*  62 */     Iterator<StructureComponent> var1 = this.components.iterator();
/*     */     
/*  64 */     while (var1.hasNext()) {
/*     */       
/*  66 */       StructureComponent var2 = var1.next();
/*  67 */       this.boundingBox.expandTo(var2.getBoundingBox());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound func_143021_a(int p_143021_1_, int p_143021_2_) {
/*  73 */     NBTTagCompound var3 = new NBTTagCompound();
/*  74 */     var3.setString("id", MapGenStructureIO.func_143033_a(this));
/*  75 */     var3.setInteger("ChunkX", p_143021_1_);
/*  76 */     var3.setInteger("ChunkZ", p_143021_2_);
/*  77 */     var3.setTag("BB", (NBTBase)this.boundingBox.func_151535_h());
/*  78 */     NBTTagList var4 = new NBTTagList();
/*  79 */     Iterator<StructureComponent> var5 = this.components.iterator();
/*     */     
/*  81 */     while (var5.hasNext()) {
/*     */       
/*  83 */       StructureComponent var6 = var5.next();
/*  84 */       var4.appendTag((NBTBase)var6.func_143010_b());
/*     */     } 
/*     */     
/*  87 */     var3.setTag("Children", (NBTBase)var4);
/*  88 */     func_143022_a(var3);
/*  89 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_143022_a(NBTTagCompound p_143022_1_) {}
/*     */   
/*     */   public void func_143020_a(World worldIn, NBTTagCompound p_143020_2_) {
/*  96 */     this.field_143024_c = p_143020_2_.getInteger("ChunkX");
/*  97 */     this.field_143023_d = p_143020_2_.getInteger("ChunkZ");
/*     */     
/*  99 */     if (p_143020_2_.hasKey("BB"))
/*     */     {
/* 101 */       this.boundingBox = new StructureBoundingBox(p_143020_2_.getIntArray("BB"));
/*     */     }
/*     */     
/* 104 */     NBTTagList var3 = p_143020_2_.getTagList("Children", 10);
/*     */     
/* 106 */     for (int var4 = 0; var4 < var3.tagCount(); var4++)
/*     */     {
/* 108 */       this.components.add(MapGenStructureIO.func_143032_b(var3.getCompoundTagAt(var4), worldIn));
/*     */     }
/*     */     
/* 111 */     func_143017_b(p_143020_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_143017_b(NBTTagCompound p_143017_1_) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void markAvailableHeight(World worldIn, Random p_75067_2_, int p_75067_3_) {
/* 121 */     int var4 = 63 - p_75067_3_;
/* 122 */     int var5 = this.boundingBox.getYSize() + 1;
/*     */     
/* 124 */     if (var5 < var4)
/*     */     {
/* 126 */       var5 += p_75067_2_.nextInt(var4 - var5);
/*     */     }
/*     */     
/* 129 */     int var6 = var5 - this.boundingBox.maxY;
/* 130 */     this.boundingBox.offset(0, var6, 0);
/* 131 */     Iterator<StructureComponent> var7 = this.components.iterator();
/*     */     
/* 133 */     while (var7.hasNext()) {
/*     */       
/* 135 */       StructureComponent var8 = var7.next();
/* 136 */       var8.getBoundingBox().offset(0, var6, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setRandomHeight(World worldIn, Random p_75070_2_, int p_75070_3_, int p_75070_4_) {
/* 142 */     int var10, var5 = p_75070_4_ - p_75070_3_ + 1 - this.boundingBox.getYSize();
/* 143 */     boolean var6 = true;
/*     */ 
/*     */     
/* 146 */     if (var5 > 1) {
/*     */       
/* 148 */       var10 = p_75070_3_ + p_75070_2_.nextInt(var5);
/*     */     }
/*     */     else {
/*     */       
/* 152 */       var10 = p_75070_3_;
/*     */     } 
/*     */     
/* 155 */     int var7 = var10 - this.boundingBox.minY;
/* 156 */     this.boundingBox.offset(0, var7, 0);
/* 157 */     Iterator<StructureComponent> var8 = this.components.iterator();
/*     */     
/* 159 */     while (var8.hasNext()) {
/*     */       
/* 161 */       StructureComponent var9 = var8.next();
/* 162 */       var9.getBoundingBox().offset(0, var7, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSizeableStructure() {
/* 171 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175788_a(ChunkCoordIntPair p_175788_1_) {
/* 176 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175787_b(ChunkCoordIntPair p_175787_1_) {}
/*     */   
/*     */   public int func_143019_e() {
/* 183 */     return this.field_143024_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_143018_f() {
/* 188 */     return this.field_143023_d;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\gen\structure\StructureStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */