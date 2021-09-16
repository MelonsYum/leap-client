/*     */ package net.minecraft.village;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.WorldSavedData;
/*     */ 
/*     */ 
/*     */ public class VillageCollection
/*     */   extends WorldSavedData
/*     */ {
/*     */   private World worldObj;
/*  25 */   private final List villagerPositionsList = Lists.newArrayList();
/*  26 */   private final List newDoors = Lists.newArrayList();
/*  27 */   private final List villageList = Lists.newArrayList();
/*     */   
/*     */   private int tickCounter;
/*     */   private static final String __OBFID = "CL_00001635";
/*     */   
/*     */   public VillageCollection(String p_i1677_1_) {
/*  33 */     super(p_i1677_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public VillageCollection(World worldIn) {
/*  38 */     super(func_176062_a(worldIn.provider));
/*  39 */     this.worldObj = worldIn;
/*  40 */     markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82566_a(World worldIn) {
/*  45 */     this.worldObj = worldIn;
/*  46 */     Iterator<Village> var2 = this.villageList.iterator();
/*     */     
/*  48 */     while (var2.hasNext()) {
/*     */       
/*  50 */       Village var3 = var2.next();
/*  51 */       var3.func_82691_a(worldIn);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176060_a(BlockPos p_176060_1_) {
/*  57 */     if (this.villagerPositionsList.size() <= 64)
/*     */     {
/*  59 */       if (!func_176057_e(p_176060_1_))
/*     */       {
/*  61 */         this.villagerPositionsList.add(p_176060_1_);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  71 */     this.tickCounter++;
/*  72 */     Iterator<Village> var1 = this.villageList.iterator();
/*     */     
/*  74 */     while (var1.hasNext()) {
/*     */       
/*  76 */       Village var2 = var1.next();
/*  77 */       var2.tick(this.tickCounter);
/*     */     } 
/*     */     
/*  80 */     removeAnnihilatedVillages();
/*  81 */     dropOldestVillagerPosition();
/*  82 */     addNewDoorsToVillageOrCreateVillage();
/*     */     
/*  84 */     if (this.tickCounter % 400 == 0)
/*     */     {
/*  86 */       markDirty();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeAnnihilatedVillages() {
/*  92 */     Iterator<Village> var1 = this.villageList.iterator();
/*     */     
/*  94 */     while (var1.hasNext()) {
/*     */       
/*  96 */       Village var2 = var1.next();
/*     */       
/*  98 */       if (var2.isAnnihilated()) {
/*     */         
/* 100 */         var1.remove();
/* 101 */         markDirty();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getVillageList() {
/* 111 */     return this.villageList;
/*     */   }
/*     */ 
/*     */   
/*     */   public Village func_176056_a(BlockPos p_176056_1_, int p_176056_2_) {
/* 116 */     Village var3 = null;
/* 117 */     double var4 = 3.4028234663852886E38D;
/* 118 */     Iterator<Village> var6 = this.villageList.iterator();
/*     */     
/* 120 */     while (var6.hasNext()) {
/*     */       
/* 122 */       Village var7 = var6.next();
/* 123 */       double var8 = var7.func_180608_a().distanceSq((Vec3i)p_176056_1_);
/*     */       
/* 125 */       if (var8 < var4) {
/*     */         
/* 127 */         float var10 = (p_176056_2_ + var7.getVillageRadius());
/*     */         
/* 129 */         if (var8 <= (var10 * var10)) {
/*     */           
/* 131 */           var3 = var7;
/* 132 */           var4 = var8;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private void dropOldestVillagerPosition() {
/* 142 */     if (!this.villagerPositionsList.isEmpty())
/*     */     {
/* 144 */       func_180609_b(this.villagerPositionsList.remove(0));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void addNewDoorsToVillageOrCreateVillage() {
/* 150 */     for (int var1 = 0; var1 < this.newDoors.size(); var1++) {
/*     */       
/* 152 */       VillageDoorInfo var2 = this.newDoors.get(var1);
/* 153 */       Village var3 = func_176056_a(var2.func_179852_d(), 32);
/*     */       
/* 155 */       if (var3 == null) {
/*     */         
/* 157 */         var3 = new Village(this.worldObj);
/* 158 */         this.villageList.add(var3);
/* 159 */         markDirty();
/*     */       } 
/*     */       
/* 162 */       var3.addVillageDoorInfo(var2);
/*     */     } 
/*     */     
/* 165 */     this.newDoors.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_180609_b(BlockPos p_180609_1_) {
/* 170 */     byte var2 = 16;
/* 171 */     byte var3 = 4;
/* 172 */     byte var4 = 16;
/*     */     
/* 174 */     for (int var5 = -var2; var5 < var2; var5++) {
/*     */       
/* 176 */       for (int var6 = -var3; var6 < var3; var6++) {
/*     */         
/* 178 */         for (int var7 = -var4; var7 < var4; var7++) {
/*     */           
/* 180 */           BlockPos var8 = p_180609_1_.add(var5, var6, var7);
/*     */           
/* 182 */           if (func_176058_f(var8)) {
/*     */             
/* 184 */             VillageDoorInfo var9 = func_176055_c(var8);
/*     */             
/* 186 */             if (var9 == null) {
/*     */               
/* 188 */               func_176059_d(var8);
/*     */             }
/*     */             else {
/*     */               
/* 192 */               var9.func_179849_a(this.tickCounter);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private VillageDoorInfo func_176055_c(BlockPos p_176055_1_) {
/*     */     VillageDoorInfo var3;
/* 202 */     Iterator<Village> var2 = this.newDoors.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 207 */       if (!var2.hasNext()) {
/*     */         VillageDoorInfo var4;
/* 209 */         var2 = this.villageList.iterator();
/*     */ 
/*     */ 
/*     */         
/*     */         do {
/* 214 */           if (!var2.hasNext())
/*     */           {
/* 216 */             return null;
/*     */           }
/*     */           
/* 219 */           Village var5 = var2.next();
/* 220 */           var4 = var5.func_179864_e(p_176055_1_);
/*     */         }
/* 222 */         while (var4 == null);
/*     */         
/* 224 */         return var4;
/*     */       } 
/*     */       
/* 227 */       var3 = (VillageDoorInfo)var2.next();
/*     */     }
/* 229 */     while (var3.func_179852_d().getX() != p_176055_1_.getX() || var3.func_179852_d().getZ() != p_176055_1_.getZ() || Math.abs(var3.func_179852_d().getY() - p_176055_1_.getY()) > 1);
/*     */     
/* 231 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_176059_d(BlockPos p_176059_1_) {
/* 236 */     EnumFacing var2 = BlockDoor.func_176517_h((IBlockAccess)this.worldObj, p_176059_1_);
/* 237 */     EnumFacing var3 = var2.getOpposite();
/* 238 */     int var4 = func_176061_a(p_176059_1_, var2, 5);
/* 239 */     int var5 = func_176061_a(p_176059_1_, var3, var4 + 1);
/*     */     
/* 241 */     if (var4 != var5)
/*     */     {
/* 243 */       this.newDoors.add(new VillageDoorInfo(p_176059_1_, (var4 < var5) ? var2 : var3, this.tickCounter));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int func_176061_a(BlockPos p_176061_1_, EnumFacing p_176061_2_, int p_176061_3_) {
/* 249 */     int var4 = 0;
/*     */     
/* 251 */     for (int var5 = 1; var5 <= 5; var5++) {
/*     */       
/* 253 */       if (this.worldObj.isAgainstSky(p_176061_1_.offset(p_176061_2_, var5))) {
/*     */         
/* 255 */         var4++;
/*     */         
/* 257 */         if (var4 >= p_176061_3_)
/*     */         {
/* 259 */           return var4;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 264 */     return var4;
/*     */   }
/*     */   
/*     */   private boolean func_176057_e(BlockPos p_176057_1_) {
/*     */     BlockPos var3;
/* 269 */     Iterator<BlockPos> var2 = this.villagerPositionsList.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 274 */       if (!var2.hasNext())
/*     */       {
/* 276 */         return false;
/*     */       }
/*     */       
/* 279 */       var3 = var2.next();
/*     */     }
/* 281 */     while (!var3.equals(p_176057_1_));
/*     */     
/* 283 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176058_f(BlockPos p_176058_1_) {
/* 288 */     Block var2 = this.worldObj.getBlockState(p_176058_1_).getBlock();
/* 289 */     return (var2 instanceof BlockDoor) ? ((var2.getMaterial() == Material.wood)) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound nbt) {
/* 297 */     this.tickCounter = nbt.getInteger("Tick");
/* 298 */     NBTTagList var2 = nbt.getTagList("Villages", 10);
/*     */     
/* 300 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 302 */       NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 303 */       Village var5 = new Village();
/* 304 */       var5.readVillageDataFromNBT(var4);
/* 305 */       this.villageList.add(var5);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound nbt) {
/* 314 */     nbt.setInteger("Tick", this.tickCounter);
/* 315 */     NBTTagList var2 = new NBTTagList();
/* 316 */     Iterator<Village> var3 = this.villageList.iterator();
/*     */     
/* 318 */     while (var3.hasNext()) {
/*     */       
/* 320 */       Village var4 = var3.next();
/* 321 */       NBTTagCompound var5 = new NBTTagCompound();
/* 322 */       var4.writeVillageDataToNBT(var5);
/* 323 */       var2.appendTag((NBTBase)var5);
/*     */     } 
/*     */     
/* 326 */     nbt.setTag("Villages", (NBTBase)var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String func_176062_a(WorldProvider p_176062_0_) {
/* 331 */     return "villages" + p_176062_0_.getInternalNameSuffix();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\village\VillageCollection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */