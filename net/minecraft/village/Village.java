/*     */ package net.minecraft.village;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.monster.EntityIronGolem;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.util.Vec3i;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class Village {
/*     */   private World worldObj;
/*  27 */   private final List villageDoorInfoList = Lists.newArrayList();
/*     */   
/*     */   private BlockPos centerHelper;
/*     */   
/*     */   private BlockPos center;
/*     */   
/*     */   private int villageRadius;
/*     */   
/*     */   private int lastAddDoorTimestamp;
/*     */   
/*     */   private int tickCounter;
/*     */   
/*     */   private int numVillagers;
/*     */   
/*     */   private int noBreedTicks;
/*     */   
/*     */   private TreeMap playerReputation;
/*     */   
/*     */   private List villageAgressors;
/*     */   
/*     */   private int numIronGolems;
/*     */   
/*     */   private static final String __OBFID = "CL_00001631";
/*     */ 
/*     */   
/*     */   public Village() {
/*  53 */     this.centerHelper = BlockPos.ORIGIN;
/*  54 */     this.center = BlockPos.ORIGIN;
/*  55 */     this.playerReputation = new TreeMap<>();
/*  56 */     this.villageAgressors = Lists.newArrayList();
/*     */   }
/*     */ 
/*     */   
/*     */   public Village(World worldIn) {
/*  61 */     this.centerHelper = BlockPos.ORIGIN;
/*  62 */     this.center = BlockPos.ORIGIN;
/*  63 */     this.playerReputation = new TreeMap<>();
/*  64 */     this.villageAgressors = Lists.newArrayList();
/*  65 */     this.worldObj = worldIn;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82691_a(World worldIn) {
/*  70 */     this.worldObj = worldIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(int p_75560_1_) {
/*  78 */     this.tickCounter = p_75560_1_;
/*  79 */     removeDeadAndOutOfRangeDoors();
/*  80 */     removeDeadAndOldAgressors();
/*     */     
/*  82 */     if (p_75560_1_ % 20 == 0)
/*     */     {
/*  84 */       updateNumVillagers();
/*     */     }
/*     */     
/*  87 */     if (p_75560_1_ % 30 == 0)
/*     */     {
/*  89 */       updateNumIronGolems();
/*     */     }
/*     */     
/*  92 */     int var2 = this.numVillagers / 10;
/*     */     
/*  94 */     if (this.numIronGolems < var2 && this.villageDoorInfoList.size() > 20 && this.worldObj.rand.nextInt(7000) == 0) {
/*     */       
/*  96 */       Vec3 var3 = func_179862_a(this.center, 2, 4, 2);
/*     */       
/*  98 */       if (var3 != null) {
/*     */         
/* 100 */         EntityIronGolem var4 = new EntityIronGolem(this.worldObj);
/* 101 */         var4.setPosition(var3.xCoord, var3.yCoord, var3.zCoord);
/* 102 */         this.worldObj.spawnEntityInWorld((Entity)var4);
/* 103 */         this.numIronGolems++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Vec3 func_179862_a(BlockPos p_179862_1_, int p_179862_2_, int p_179862_3_, int p_179862_4_) {
/* 110 */     for (int var5 = 0; var5 < 10; var5++) {
/*     */       
/* 112 */       BlockPos var6 = p_179862_1_.add(this.worldObj.rand.nextInt(16) - 8, this.worldObj.rand.nextInt(6) - 3, this.worldObj.rand.nextInt(16) - 8);
/*     */       
/* 114 */       if (func_179866_a(var6) && func_179861_a(new BlockPos(p_179862_2_, p_179862_3_, p_179862_4_), var6))
/*     */       {
/* 116 */         return new Vec3(var6.getX(), var6.getY(), var6.getZ());
/*     */       }
/*     */     } 
/*     */     
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_179861_a(BlockPos p_179861_1_, BlockPos p_179861_2_) {
/* 125 */     if (!World.doesBlockHaveSolidTopSurface((IBlockAccess)this.worldObj, p_179861_2_.offsetDown()))
/*     */     {
/* 127 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 131 */     int var3 = p_179861_2_.getX() - p_179861_1_.getX() / 2;
/* 132 */     int var4 = p_179861_2_.getZ() - p_179861_1_.getZ() / 2;
/*     */     
/* 134 */     for (int var5 = var3; var5 < var3 + p_179861_1_.getX(); var5++) {
/*     */       
/* 136 */       for (int var6 = p_179861_2_.getY(); var6 < p_179861_2_.getY() + p_179861_1_.getY(); var6++) {
/*     */         
/* 138 */         for (int var7 = var4; var7 < var4 + p_179861_1_.getZ(); var7++) {
/*     */           
/* 140 */           if (this.worldObj.getBlockState(new BlockPos(var5, var6, var7)).getBlock().isNormalCube())
/*     */           {
/* 142 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateNumIronGolems() {
/* 154 */     List var1 = this.worldObj.getEntitiesWithinAABB(EntityIronGolem.class, new AxisAlignedBB((this.center.getX() - this.villageRadius), (this.center.getY() - 4), (this.center.getZ() - this.villageRadius), (this.center.getX() + this.villageRadius), (this.center.getY() + 4), (this.center.getZ() + this.villageRadius)));
/* 155 */     this.numIronGolems = var1.size();
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateNumVillagers() {
/* 160 */     List var1 = this.worldObj.getEntitiesWithinAABB(EntityVillager.class, new AxisAlignedBB((this.center.getX() - this.villageRadius), (this.center.getY() - 4), (this.center.getZ() - this.villageRadius), (this.center.getX() + this.villageRadius), (this.center.getY() + 4), (this.center.getZ() + this.villageRadius)));
/* 161 */     this.numVillagers = var1.size();
/*     */     
/* 163 */     if (this.numVillagers == 0)
/*     */     {
/* 165 */       this.playerReputation.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPos func_180608_a() {
/* 171 */     return this.center;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVillageRadius() {
/* 176 */     return this.villageRadius;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumVillageDoors() {
/* 185 */     return this.villageDoorInfoList.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTicksSinceLastDoorAdding() {
/* 190 */     return this.tickCounter - this.lastAddDoorTimestamp;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumVillagers() {
/* 195 */     return this.numVillagers;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_179866_a(BlockPos p_179866_1_) {
/* 200 */     return (this.center.distanceSq((Vec3i)p_179866_1_) < (this.villageRadius * this.villageRadius));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getVillageDoorInfoList() {
/* 208 */     return this.villageDoorInfoList;
/*     */   }
/*     */ 
/*     */   
/*     */   public VillageDoorInfo func_179865_b(BlockPos p_179865_1_) {
/* 213 */     VillageDoorInfo var2 = null;
/* 214 */     int var3 = Integer.MAX_VALUE;
/* 215 */     Iterator<VillageDoorInfo> var4 = this.villageDoorInfoList.iterator();
/*     */     
/* 217 */     while (var4.hasNext()) {
/*     */       
/* 219 */       VillageDoorInfo var5 = var4.next();
/* 220 */       int var6 = var5.func_179848_a(p_179865_1_);
/*     */       
/* 222 */       if (var6 < var3) {
/*     */         
/* 224 */         var2 = var5;
/* 225 */         var3 = var6;
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public VillageDoorInfo func_179863_c(BlockPos p_179863_1_) {
/* 234 */     VillageDoorInfo var2 = null;
/* 235 */     int var3 = Integer.MAX_VALUE;
/* 236 */     Iterator<VillageDoorInfo> var4 = this.villageDoorInfoList.iterator();
/*     */     
/* 238 */     while (var4.hasNext()) {
/*     */       
/* 240 */       VillageDoorInfo var5 = var4.next();
/* 241 */       int var6 = var5.func_179848_a(p_179863_1_);
/*     */       
/* 243 */       if (var6 > 256) {
/*     */         
/* 245 */         var6 *= 1000;
/*     */       }
/*     */       else {
/*     */         
/* 249 */         var6 = var5.getDoorOpeningRestrictionCounter();
/*     */       } 
/*     */       
/* 252 */       if (var6 < var3) {
/*     */         
/* 254 */         var2 = var5;
/* 255 */         var3 = var6;
/*     */       } 
/*     */     } 
/*     */     
/* 259 */     return var2;
/*     */   }
/*     */   
/*     */   public VillageDoorInfo func_179864_e(BlockPos p_179864_1_) {
/*     */     VillageDoorInfo var3;
/* 264 */     if (this.center.distanceSq((Vec3i)p_179864_1_) > (this.villageRadius * this.villageRadius))
/*     */     {
/* 266 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 270 */     Iterator<VillageDoorInfo> var2 = this.villageDoorInfoList.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 275 */       if (!var2.hasNext())
/*     */       {
/* 277 */         return null;
/*     */       }
/*     */       
/* 280 */       var3 = var2.next();
/*     */     }
/* 282 */     while (var3.func_179852_d().getX() != p_179864_1_.getX() || var3.func_179852_d().getZ() != p_179864_1_.getZ() || Math.abs(var3.func_179852_d().getY() - p_179864_1_.getY()) > 1);
/*     */     
/* 284 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVillageDoorInfo(VillageDoorInfo p_75576_1_) {
/* 290 */     this.villageDoorInfoList.add(p_75576_1_);
/* 291 */     this.centerHelper = this.centerHelper.add((Vec3i)p_75576_1_.func_179852_d());
/* 292 */     updateVillageRadiusAndCenter();
/* 293 */     this.lastAddDoorTimestamp = p_75576_1_.getInsidePosY();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAnnihilated() {
/* 301 */     return this.villageDoorInfoList.isEmpty();
/*     */   }
/*     */   
/*     */   public void addOrRenewAgressor(EntityLivingBase p_75575_1_) {
/*     */     VillageAgressor var3;
/* 306 */     Iterator<VillageAgressor> var2 = this.villageAgressors.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 311 */       if (!var2.hasNext()) {
/*     */         
/* 313 */         this.villageAgressors.add(new VillageAgressor(p_75575_1_, this.tickCounter));
/*     */         
/*     */         return;
/*     */       } 
/* 317 */       var3 = var2.next();
/*     */     }
/* 319 */     while (var3.agressor != p_75575_1_);
/*     */     
/* 321 */     var3.agressionTime = this.tickCounter;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingBase findNearestVillageAggressor(EntityLivingBase p_75571_1_) {
/* 326 */     double var2 = Double.MAX_VALUE;
/* 327 */     VillageAgressor var4 = null;
/*     */     
/* 329 */     for (int var5 = 0; var5 < this.villageAgressors.size(); var5++) {
/*     */       
/* 331 */       VillageAgressor var6 = this.villageAgressors.get(var5);
/* 332 */       double var7 = var6.agressor.getDistanceSqToEntity((Entity)p_75571_1_);
/*     */       
/* 334 */       if (var7 <= var2) {
/*     */         
/* 336 */         var4 = var6;
/* 337 */         var2 = var7;
/*     */       } 
/*     */     } 
/*     */     
/* 341 */     return (var4 != null) ? var4.agressor : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer func_82685_c(EntityLivingBase p_82685_1_) {
/* 346 */     double var2 = Double.MAX_VALUE;
/* 347 */     EntityPlayer var4 = null;
/* 348 */     Iterator<String> var5 = this.playerReputation.keySet().iterator();
/*     */     
/* 350 */     while (var5.hasNext()) {
/*     */       
/* 352 */       String var6 = var5.next();
/*     */       
/* 354 */       if (isPlayerReputationTooLow(var6)) {
/*     */         
/* 356 */         EntityPlayer var7 = this.worldObj.getPlayerEntityByName(var6);
/*     */         
/* 358 */         if (var7 != null) {
/*     */           
/* 360 */           double var8 = var7.getDistanceSqToEntity((Entity)p_82685_1_);
/*     */           
/* 362 */           if (var8 <= var2) {
/*     */             
/* 364 */             var4 = var7;
/* 365 */             var2 = var8;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 371 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeDeadAndOldAgressors() {
/* 376 */     Iterator<VillageAgressor> var1 = this.villageAgressors.iterator();
/*     */     
/* 378 */     while (var1.hasNext()) {
/*     */       
/* 380 */       VillageAgressor var2 = var1.next();
/*     */       
/* 382 */       if (!var2.agressor.isEntityAlive() || Math.abs(this.tickCounter - var2.agressionTime) > 300)
/*     */       {
/* 384 */         var1.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeDeadAndOutOfRangeDoors() {
/* 391 */     boolean var1 = false;
/* 392 */     boolean var2 = (this.worldObj.rand.nextInt(50) == 0);
/* 393 */     Iterator<VillageDoorInfo> var3 = this.villageDoorInfoList.iterator();
/*     */     
/* 395 */     while (var3.hasNext()) {
/*     */       
/* 397 */       VillageDoorInfo var4 = var3.next();
/*     */       
/* 399 */       if (var2)
/*     */       {
/* 401 */         var4.resetDoorOpeningRestrictionCounter();
/*     */       }
/*     */       
/* 404 */       if (!func_179860_f(var4.func_179852_d()) || Math.abs(this.tickCounter - var4.getInsidePosY()) > 1200) {
/*     */         
/* 406 */         this.centerHelper = this.centerHelper.add((Vec3i)var4.func_179852_d().multiply(-1));
/* 407 */         var1 = true;
/* 408 */         var4.func_179853_a(true);
/* 409 */         var3.remove();
/*     */       } 
/*     */     } 
/*     */     
/* 413 */     if (var1)
/*     */     {
/* 415 */       updateVillageRadiusAndCenter();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_179860_f(BlockPos p_179860_1_) {
/* 421 */     Block var2 = this.worldObj.getBlockState(p_179860_1_).getBlock();
/* 422 */     return (var2 instanceof net.minecraft.block.BlockDoor) ? ((var2.getMaterial() == Material.wood)) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateVillageRadiusAndCenter() {
/* 427 */     int var1 = this.villageDoorInfoList.size();
/*     */     
/* 429 */     if (var1 == 0) {
/*     */       
/* 431 */       this.center = new BlockPos(0, 0, 0);
/* 432 */       this.villageRadius = 0;
/*     */     }
/*     */     else {
/*     */       
/* 436 */       this.center = new BlockPos(this.centerHelper.getX() / var1, this.centerHelper.getY() / var1, this.centerHelper.getZ() / var1);
/* 437 */       int var2 = 0;
/*     */ 
/*     */       
/* 440 */       for (Iterator<VillageDoorInfo> var3 = this.villageDoorInfoList.iterator(); var3.hasNext(); var2 = Math.max(var4.func_179848_a(this.center), var2))
/*     */       {
/* 442 */         VillageDoorInfo var4 = var3.next();
/*     */       }
/*     */       
/* 445 */       this.villageRadius = Math.max(32, (int)Math.sqrt(var2) + 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getReputationForPlayer(String p_82684_1_) {
/* 454 */     Integer var2 = (Integer)this.playerReputation.get(p_82684_1_);
/* 455 */     return (var2 != null) ? var2.intValue() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int setReputationForPlayer(String p_82688_1_, int p_82688_2_) {
/* 463 */     int var3 = getReputationForPlayer(p_82688_1_);
/* 464 */     int var4 = MathHelper.clamp_int(var3 + p_82688_2_, -30, 10);
/* 465 */     this.playerReputation.put(p_82688_1_, Integer.valueOf(var4));
/* 466 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPlayerReputationTooLow(String p_82687_1_) {
/* 474 */     return (getReputationForPlayer(p_82687_1_) <= -15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readVillageDataFromNBT(NBTTagCompound p_82690_1_) {
/* 482 */     this.numVillagers = p_82690_1_.getInteger("PopSize");
/* 483 */     this.villageRadius = p_82690_1_.getInteger("Radius");
/* 484 */     this.numIronGolems = p_82690_1_.getInteger("Golems");
/* 485 */     this.lastAddDoorTimestamp = p_82690_1_.getInteger("Stable");
/* 486 */     this.tickCounter = p_82690_1_.getInteger("Tick");
/* 487 */     this.noBreedTicks = p_82690_1_.getInteger("MTick");
/* 488 */     this.center = new BlockPos(p_82690_1_.getInteger("CX"), p_82690_1_.getInteger("CY"), p_82690_1_.getInteger("CZ"));
/* 489 */     this.centerHelper = new BlockPos(p_82690_1_.getInteger("ACX"), p_82690_1_.getInteger("ACY"), p_82690_1_.getInteger("ACZ"));
/* 490 */     NBTTagList var2 = p_82690_1_.getTagList("Doors", 10);
/*     */     
/* 492 */     for (int var3 = 0; var3 < var2.tagCount(); var3++) {
/*     */       
/* 494 */       NBTTagCompound var4 = var2.getCompoundTagAt(var3);
/* 495 */       VillageDoorInfo var5 = new VillageDoorInfo(new BlockPos(var4.getInteger("X"), var4.getInteger("Y"), var4.getInteger("Z")), var4.getInteger("IDX"), var4.getInteger("IDZ"), var4.getInteger("TS"));
/* 496 */       this.villageDoorInfoList.add(var5);
/*     */     } 
/*     */     
/* 499 */     NBTTagList var6 = p_82690_1_.getTagList("Players", 10);
/*     */     
/* 501 */     for (int var7 = 0; var7 < var6.tagCount(); var7++) {
/*     */       
/* 503 */       NBTTagCompound var8 = var6.getCompoundTagAt(var7);
/* 504 */       this.playerReputation.put(var8.getString("Name"), Integer.valueOf(var8.getInteger("S")));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeVillageDataToNBT(NBTTagCompound p_82689_1_) {
/* 513 */     p_82689_1_.setInteger("PopSize", this.numVillagers);
/* 514 */     p_82689_1_.setInteger("Radius", this.villageRadius);
/* 515 */     p_82689_1_.setInteger("Golems", this.numIronGolems);
/* 516 */     p_82689_1_.setInteger("Stable", this.lastAddDoorTimestamp);
/* 517 */     p_82689_1_.setInteger("Tick", this.tickCounter);
/* 518 */     p_82689_1_.setInteger("MTick", this.noBreedTicks);
/* 519 */     p_82689_1_.setInteger("CX", this.center.getX());
/* 520 */     p_82689_1_.setInteger("CY", this.center.getY());
/* 521 */     p_82689_1_.setInteger("CZ", this.center.getZ());
/* 522 */     p_82689_1_.setInteger("ACX", this.centerHelper.getX());
/* 523 */     p_82689_1_.setInteger("ACY", this.centerHelper.getY());
/* 524 */     p_82689_1_.setInteger("ACZ", this.centerHelper.getZ());
/* 525 */     NBTTagList var2 = new NBTTagList();
/* 526 */     Iterator<VillageDoorInfo> var3 = this.villageDoorInfoList.iterator();
/*     */     
/* 528 */     while (var3.hasNext()) {
/*     */       
/* 530 */       VillageDoorInfo var4 = var3.next();
/* 531 */       NBTTagCompound var5 = new NBTTagCompound();
/* 532 */       var5.setInteger("X", var4.func_179852_d().getX());
/* 533 */       var5.setInteger("Y", var4.func_179852_d().getY());
/* 534 */       var5.setInteger("Z", var4.func_179852_d().getZ());
/* 535 */       var5.setInteger("IDX", var4.func_179847_f());
/* 536 */       var5.setInteger("IDZ", var4.func_179855_g());
/* 537 */       var5.setInteger("TS", var4.getInsidePosY());
/* 538 */       var2.appendTag((NBTBase)var5);
/*     */     } 
/*     */     
/* 541 */     p_82689_1_.setTag("Doors", (NBTBase)var2);
/* 542 */     NBTTagList var7 = new NBTTagList();
/* 543 */     Iterator<String> var8 = this.playerReputation.keySet().iterator();
/*     */     
/* 545 */     while (var8.hasNext()) {
/*     */       
/* 547 */       String var9 = var8.next();
/* 548 */       NBTTagCompound var6 = new NBTTagCompound();
/* 549 */       var6.setString("Name", var9);
/* 550 */       var6.setInteger("S", ((Integer)this.playerReputation.get(var9)).intValue());
/* 551 */       var7.appendTag((NBTBase)var6);
/*     */     } 
/*     */     
/* 554 */     p_82689_1_.setTag("Players", (NBTBase)var7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endMatingSeason() {
/* 562 */     this.noBreedTicks = this.tickCounter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMatingSeason() {
/* 570 */     return !(this.noBreedTicks != 0 && this.tickCounter - this.noBreedTicks < 3600);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultPlayerReputation(int p_82683_1_) {
/* 575 */     Iterator<String> var2 = this.playerReputation.keySet().iterator();
/*     */     
/* 577 */     while (var2.hasNext()) {
/*     */       
/* 579 */       String var3 = var2.next();
/* 580 */       setReputationForPlayer(var3, p_82683_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   class VillageAgressor
/*     */   {
/*     */     public EntityLivingBase agressor;
/*     */     public int agressionTime;
/*     */     private static final String __OBFID = "CL_00001632";
/*     */     
/*     */     VillageAgressor(EntityLivingBase p_i1674_2_, int p_i1674_3_) {
/* 592 */       this.agressor = p_i1674_2_;
/* 593 */       this.agressionTime = p_i1674_3_;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\village\Village.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */