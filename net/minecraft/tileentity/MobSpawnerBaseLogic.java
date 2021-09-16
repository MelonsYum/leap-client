/*     */ package net.minecraft.tileentity;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.item.EntityMinecart;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class MobSpawnerBaseLogic
/*     */ {
/*  24 */   private int spawnDelay = 20;
/*  25 */   private String mobID = "Pig";
/*     */ 
/*     */   
/*  28 */   private final List minecartToSpawn = Lists.newArrayList();
/*     */   private WeightedRandomMinecart randomEntity;
/*     */   private double field_98287_c;
/*     */   private double field_98284_d;
/*  32 */   private int minSpawnDelay = 200;
/*  33 */   private int maxSpawnDelay = 800;
/*  34 */   private int spawnCount = 4;
/*     */   
/*     */   private Entity cachedEntity;
/*     */   
/*  38 */   private int maxNearbyEntities = 6;
/*     */ 
/*     */   
/*  41 */   private int activatingRangeFromPlayer = 16;
/*     */ 
/*     */   
/*  44 */   private int spawnRange = 4;
/*     */ 
/*     */   
/*     */   private static final String __OBFID = "CL_00000129";
/*     */ 
/*     */ 
/*     */   
/*     */   private String getEntityNameToSpawn() {
/*  52 */     if (getRandomEntity() == null) {
/*     */       
/*  54 */       if (this.mobID.equals("Minecart"))
/*     */       {
/*  56 */         this.mobID = "MinecartRideable";
/*     */       }
/*     */       
/*  59 */       return this.mobID;
/*     */     } 
/*     */ 
/*     */     
/*  63 */     return (getRandomEntity()).entityType;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntityName(String p_98272_1_) {
/*  69 */     this.mobID = p_98272_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isActivated() {
/*  77 */     BlockPos var1 = func_177221_b();
/*  78 */     return getSpawnerWorld().func_175636_b(var1.getX() + 0.5D, var1.getY() + 0.5D, var1.getZ() + 0.5D, this.activatingRangeFromPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateSpawner() {
/*  83 */     if (isActivated()) {
/*     */       
/*  85 */       BlockPos var1 = func_177221_b();
/*     */ 
/*     */       
/*  88 */       if ((getSpawnerWorld()).isRemote) {
/*     */         
/*  90 */         double var2 = (var1.getX() + (getSpawnerWorld()).rand.nextFloat());
/*  91 */         double var4 = (var1.getY() + (getSpawnerWorld()).rand.nextFloat());
/*  92 */         double var6 = (var1.getZ() + (getSpawnerWorld()).rand.nextFloat());
/*  93 */         getSpawnerWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var2, var4, var6, 0.0D, 0.0D, 0.0D, new int[0]);
/*  94 */         getSpawnerWorld().spawnParticle(EnumParticleTypes.FLAME, var2, var4, var6, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         
/*  96 */         if (this.spawnDelay > 0)
/*     */         {
/*  98 */           this.spawnDelay--;
/*     */         }
/*     */         
/* 101 */         this.field_98284_d = this.field_98287_c;
/* 102 */         this.field_98287_c = (this.field_98287_c + (1000.0F / (this.spawnDelay + 200.0F))) % 360.0D;
/*     */       }
/*     */       else {
/*     */         
/* 106 */         if (this.spawnDelay == -1)
/*     */         {
/* 108 */           resetTimer();
/*     */         }
/*     */         
/* 111 */         if (this.spawnDelay > 0) {
/*     */           
/* 113 */           this.spawnDelay--;
/*     */           
/*     */           return;
/*     */         } 
/* 117 */         boolean var13 = false;
/*     */         
/* 119 */         for (int var3 = 0; var3 < this.spawnCount; var3++) {
/*     */           
/* 121 */           Entity var14 = EntityList.createEntityByName(getEntityNameToSpawn(), getSpawnerWorld());
/*     */           
/* 123 */           if (var14 == null) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/* 128 */           int var5 = getSpawnerWorld().getEntitiesWithinAABB(var14.getClass(), (new AxisAlignedBB(var1.getX(), var1.getY(), var1.getZ(), (var1.getX() + 1), (var1.getY() + 1), (var1.getZ() + 1))).expand(this.spawnRange, this.spawnRange, this.spawnRange)).size();
/*     */           
/* 130 */           if (var5 >= this.maxNearbyEntities) {
/*     */             
/* 132 */             resetTimer();
/*     */             
/*     */             return;
/*     */           } 
/* 136 */           double var6 = var1.getX() + ((getSpawnerWorld()).rand.nextDouble() - (getSpawnerWorld()).rand.nextDouble()) * this.spawnRange + 0.5D;
/* 137 */           double var8 = (var1.getY() + (getSpawnerWorld()).rand.nextInt(3) - 1);
/* 138 */           double var10 = var1.getZ() + ((getSpawnerWorld()).rand.nextDouble() - (getSpawnerWorld()).rand.nextDouble()) * this.spawnRange + 0.5D;
/* 139 */           EntityLiving var12 = (var14 instanceof EntityLiving) ? (EntityLiving)var14 : null;
/* 140 */           var14.setLocationAndAngles(var6, var8, var10, (getSpawnerWorld()).rand.nextFloat() * 360.0F, 0.0F);
/*     */           
/* 142 */           if (var12 == null || (var12.getCanSpawnHere() && var12.handleLavaMovement())) {
/*     */             
/* 144 */             func_180613_a(var14, true);
/* 145 */             getSpawnerWorld().playAuxSFX(2004, var1, 0);
/*     */             
/* 147 */             if (var12 != null)
/*     */             {
/* 149 */               var12.spawnExplosionParticle();
/*     */             }
/*     */             
/* 152 */             var13 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 156 */         if (var13)
/*     */         {
/* 158 */           resetTimer();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Entity func_180613_a(Entity p_180613_1_, boolean p_180613_2_) {
/* 166 */     if (getRandomEntity() != null) {
/*     */       
/* 168 */       NBTTagCompound var3 = new NBTTagCompound();
/* 169 */       p_180613_1_.writeToNBTOptional(var3);
/* 170 */       Iterator<String> var4 = (getRandomEntity()).field_98222_b.getKeySet().iterator();
/*     */       
/* 172 */       while (var4.hasNext()) {
/*     */         
/* 174 */         String var5 = var4.next();
/* 175 */         NBTBase var6 = (getRandomEntity()).field_98222_b.getTag(var5);
/* 176 */         var3.setTag(var5, var6.copy());
/*     */       } 
/*     */       
/* 179 */       p_180613_1_.readFromNBT(var3);
/*     */       
/* 181 */       if (p_180613_1_.worldObj != null && p_180613_2_)
/*     */       {
/* 183 */         p_180613_1_.worldObj.spawnEntityInWorld(p_180613_1_);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 188 */       for (Entity var11 = p_180613_1_; var3.hasKey("Riding", 10); var3 = var12)
/*     */       {
/* 190 */         NBTTagCompound var12 = var3.getCompoundTag("Riding");
/* 191 */         Entity var13 = EntityList.createEntityByName(var12.getString("id"), p_180613_1_.worldObj);
/*     */         
/* 193 */         if (var13 != null) {
/*     */           
/* 195 */           NBTTagCompound var7 = new NBTTagCompound();
/* 196 */           var13.writeToNBTOptional(var7);
/* 197 */           Iterator<String> var8 = var12.getKeySet().iterator();
/*     */           
/* 199 */           while (var8.hasNext()) {
/*     */             
/* 201 */             String var9 = var8.next();
/* 202 */             NBTBase var10 = var12.getTag(var9);
/* 203 */             var7.setTag(var9, var10.copy());
/*     */           } 
/*     */           
/* 206 */           var13.readFromNBT(var7);
/* 207 */           var13.setLocationAndAngles(var11.posX, var11.posY, var11.posZ, var11.rotationYaw, var11.rotationPitch);
/*     */           
/* 209 */           if (p_180613_1_.worldObj != null && p_180613_2_)
/*     */           {
/* 211 */             p_180613_1_.worldObj.spawnEntityInWorld(var13);
/*     */           }
/*     */           
/* 214 */           var11.mountEntity(var13);
/*     */         } 
/*     */         
/* 217 */         var11 = var13;
/*     */       }
/*     */     
/* 220 */     } else if (p_180613_1_ instanceof net.minecraft.entity.EntityLivingBase && p_180613_1_.worldObj != null && p_180613_2_) {
/*     */       
/* 222 */       ((EntityLiving)p_180613_1_).func_180482_a(p_180613_1_.worldObj.getDifficultyForLocation(new BlockPos(p_180613_1_)), null);
/* 223 */       p_180613_1_.worldObj.spawnEntityInWorld(p_180613_1_);
/*     */     } 
/*     */     
/* 226 */     return p_180613_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetTimer() {
/* 231 */     if (this.maxSpawnDelay <= this.minSpawnDelay) {
/*     */       
/* 233 */       this.spawnDelay = this.minSpawnDelay;
/*     */     }
/*     */     else {
/*     */       
/* 237 */       int var10003 = this.maxSpawnDelay - this.minSpawnDelay;
/* 238 */       this.spawnDelay = this.minSpawnDelay + (getSpawnerWorld()).rand.nextInt(var10003);
/*     */     } 
/*     */     
/* 241 */     if (this.minecartToSpawn.size() > 0)
/*     */     {
/* 243 */       setRandomEntity((WeightedRandomMinecart)WeightedRandom.getRandomItem((getSpawnerWorld()).rand, this.minecartToSpawn));
/*     */     }
/*     */     
/* 246 */     func_98267_a(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readFromNBT(NBTTagCompound p_98270_1_) {
/* 251 */     this.mobID = p_98270_1_.getString("EntityId");
/* 252 */     this.spawnDelay = p_98270_1_.getShort("Delay");
/* 253 */     this.minecartToSpawn.clear();
/*     */     
/* 255 */     if (p_98270_1_.hasKey("SpawnPotentials", 9)) {
/*     */       
/* 257 */       NBTTagList var2 = p_98270_1_.getTagList("SpawnPotentials", 10);
/*     */       
/* 259 */       for (int var3 = 0; var3 < var2.tagCount(); var3++)
/*     */       {
/* 261 */         this.minecartToSpawn.add(new WeightedRandomMinecart(var2.getCompoundTagAt(var3)));
/*     */       }
/*     */     } 
/*     */     
/* 265 */     if (p_98270_1_.hasKey("SpawnData", 10)) {
/*     */       
/* 267 */       setRandomEntity(new WeightedRandomMinecart(p_98270_1_.getCompoundTag("SpawnData"), this.mobID));
/*     */     }
/*     */     else {
/*     */       
/* 271 */       setRandomEntity(null);
/*     */     } 
/*     */     
/* 274 */     if (p_98270_1_.hasKey("MinSpawnDelay", 99)) {
/*     */       
/* 276 */       this.minSpawnDelay = p_98270_1_.getShort("MinSpawnDelay");
/* 277 */       this.maxSpawnDelay = p_98270_1_.getShort("MaxSpawnDelay");
/* 278 */       this.spawnCount = p_98270_1_.getShort("SpawnCount");
/*     */     } 
/*     */     
/* 281 */     if (p_98270_1_.hasKey("MaxNearbyEntities", 99)) {
/*     */       
/* 283 */       this.maxNearbyEntities = p_98270_1_.getShort("MaxNearbyEntities");
/* 284 */       this.activatingRangeFromPlayer = p_98270_1_.getShort("RequiredPlayerRange");
/*     */     } 
/*     */     
/* 287 */     if (p_98270_1_.hasKey("SpawnRange", 99))
/*     */     {
/* 289 */       this.spawnRange = p_98270_1_.getShort("SpawnRange");
/*     */     }
/*     */     
/* 292 */     if (getSpawnerWorld() != null)
/*     */     {
/* 294 */       this.cachedEntity = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToNBT(NBTTagCompound p_98280_1_) {
/* 300 */     p_98280_1_.setString("EntityId", getEntityNameToSpawn());
/* 301 */     p_98280_1_.setShort("Delay", (short)this.spawnDelay);
/* 302 */     p_98280_1_.setShort("MinSpawnDelay", (short)this.minSpawnDelay);
/* 303 */     p_98280_1_.setShort("MaxSpawnDelay", (short)this.maxSpawnDelay);
/* 304 */     p_98280_1_.setShort("SpawnCount", (short)this.spawnCount);
/* 305 */     p_98280_1_.setShort("MaxNearbyEntities", (short)this.maxNearbyEntities);
/* 306 */     p_98280_1_.setShort("RequiredPlayerRange", (short)this.activatingRangeFromPlayer);
/* 307 */     p_98280_1_.setShort("SpawnRange", (short)this.spawnRange);
/*     */     
/* 309 */     if (getRandomEntity() != null)
/*     */     {
/* 311 */       p_98280_1_.setTag("SpawnData", (getRandomEntity()).field_98222_b.copy());
/*     */     }
/*     */     
/* 314 */     if (getRandomEntity() != null || this.minecartToSpawn.size() > 0) {
/*     */       
/* 316 */       NBTTagList var2 = new NBTTagList();
/*     */       
/* 318 */       if (this.minecartToSpawn.size() > 0) {
/*     */         
/* 320 */         Iterator<WeightedRandomMinecart> var3 = this.minecartToSpawn.iterator();
/*     */         
/* 322 */         while (var3.hasNext())
/*     */         {
/* 324 */           WeightedRandomMinecart var4 = var3.next();
/* 325 */           var2.appendTag((NBTBase)var4.func_98220_a());
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 330 */         var2.appendTag((NBTBase)getRandomEntity().func_98220_a());
/*     */       } 
/*     */       
/* 333 */       p_98280_1_.setTag("SpawnPotentials", (NBTBase)var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity func_180612_a(World worldIn) {
/* 339 */     if (this.cachedEntity == null) {
/*     */       
/* 341 */       Entity var2 = EntityList.createEntityByName(getEntityNameToSpawn(), worldIn);
/*     */       
/* 343 */       if (var2 != null) {
/*     */         
/* 345 */         var2 = func_180613_a(var2, false);
/* 346 */         this.cachedEntity = var2;
/*     */       } 
/*     */     } 
/*     */     
/* 350 */     return this.cachedEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setDelayToMin(int p_98268_1_) {
/* 358 */     if (p_98268_1_ == 1 && (getSpawnerWorld()).isRemote) {
/*     */       
/* 360 */       this.spawnDelay = this.minSpawnDelay;
/* 361 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 365 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private WeightedRandomMinecart getRandomEntity() {
/* 371 */     return this.randomEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRandomEntity(WeightedRandomMinecart p_98277_1_) {
/* 376 */     this.randomEntity = p_98277_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void func_98267_a(int paramInt);
/*     */   
/*     */   public abstract World getSpawnerWorld();
/*     */   
/*     */   public abstract BlockPos func_177221_b();
/*     */   
/*     */   public double func_177222_d() {
/* 387 */     return this.field_98287_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_177223_e() {
/* 392 */     return this.field_98284_d;
/*     */   }
/*     */   
/*     */   public class WeightedRandomMinecart
/*     */     extends WeightedRandom.Item
/*     */   {
/*     */     private final NBTTagCompound field_98222_b;
/*     */     private final String entityType;
/*     */     private static final String __OBFID = "CL_00000130";
/*     */     
/*     */     public WeightedRandomMinecart(NBTTagCompound p_i1945_2_) {
/* 403 */       this(p_i1945_2_.getCompoundTag("Properties"), p_i1945_2_.getString("Type"), p_i1945_2_.getInteger("Weight"));
/*     */     }
/*     */ 
/*     */     
/*     */     public WeightedRandomMinecart(NBTTagCompound p_i1946_2_, String p_i1946_3_) {
/* 408 */       this(p_i1946_2_, p_i1946_3_, 1);
/*     */     }
/*     */ 
/*     */     
/*     */     private WeightedRandomMinecart(NBTTagCompound p_i45757_2_, String p_i45757_3_, int p_i45757_4_) {
/* 413 */       super(p_i45757_4_);
/*     */       
/* 415 */       if (p_i45757_3_.equals("Minecart"))
/*     */       {
/* 417 */         if (p_i45757_2_ != null) {
/*     */           
/* 419 */           p_i45757_3_ = EntityMinecart.EnumMinecartType.func_180038_a(p_i45757_2_.getInteger("Type")).func_180040_b();
/*     */         }
/*     */         else {
/*     */           
/* 423 */           p_i45757_3_ = "MinecartRideable";
/*     */         } 
/*     */       }
/*     */       
/* 427 */       this.field_98222_b = p_i45757_2_;
/* 428 */       this.entityType = p_i45757_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTTagCompound func_98220_a() {
/* 433 */       NBTTagCompound var1 = new NBTTagCompound();
/* 434 */       var1.setTag("Properties", (NBTBase)this.field_98222_b);
/* 435 */       var1.setString("Type", this.entityType);
/* 436 */       var1.setInteger("Weight", this.itemWeight);
/* 437 */       return var1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\tileentity\MobSpawnerBaseLogic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */