/*     */ package net.minecraft.entity.projectile;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemFishFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.util.WeightedRandomFishable;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class EntityFishHook extends Entity {
/*  34 */   private static final List JUNK = Arrays.asList(new WeightedRandomFishable[] { (new WeightedRandomFishable(new ItemStack((Item)Items.leather_boots), 10)).setMaxDamagePercent(0.9F), new WeightedRandomFishable(new ItemStack(Items.leather), 10), new WeightedRandomFishable(new ItemStack(Items.bone), 10), new WeightedRandomFishable(new ItemStack((Item)Items.potionitem), 10), new WeightedRandomFishable(new ItemStack(Items.string), 5), (new WeightedRandomFishable(new ItemStack((Item)Items.fishing_rod), 2)).setMaxDamagePercent(0.9F), new WeightedRandomFishable(new ItemStack(Items.bowl), 10), new WeightedRandomFishable(new ItemStack(Items.stick), 5), new WeightedRandomFishable(new ItemStack(Items.dye, 10, EnumDyeColor.BLACK.getDyeColorDamage()), 1), new WeightedRandomFishable(new ItemStack((Block)Blocks.tripwire_hook), 10), new WeightedRandomFishable(new ItemStack(Items.rotten_flesh), 10) });
/*  35 */   private static final List VALUABLES = Arrays.asList(new WeightedRandomFishable[] { new WeightedRandomFishable(new ItemStack(Blocks.waterlily), 1), new WeightedRandomFishable(new ItemStack(Items.name_tag), 1), new WeightedRandomFishable(new ItemStack(Items.saddle), 1), (new WeightedRandomFishable(new ItemStack((Item)Items.bow), 1)).setMaxDamagePercent(0.25F).setEnchantable(), (new WeightedRandomFishable(new ItemStack((Item)Items.fishing_rod), 1)).setMaxDamagePercent(0.25F).setEnchantable(), (new WeightedRandomFishable(new ItemStack(Items.book), 1)).setEnchantable() });
/*  36 */   private static final List FISH = Arrays.asList(new WeightedRandomFishable[] { new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.getItemDamage()), 60), new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.SALMON.getItemDamage()), 25), new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.CLOWNFISH.getItemDamage()), 2), new WeightedRandomFishable(new ItemStack(Items.fish, 1, ItemFishFood.FishType.PUFFERFISH.getItemDamage()), 13) });
/*     */   
/*     */   private int xTile;
/*     */   private int yTile;
/*     */   private int zTile;
/*     */   private Block inTile;
/*     */   private boolean inGround;
/*     */   public int shake;
/*     */   public EntityPlayer angler;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*     */   private int ticksCatchable;
/*     */   private int ticksCaughtDelay;
/*     */   private int ticksCatchableDelay;
/*     */   private float fishApproachAngle;
/*     */   public Entity caughtEntity;
/*     */   private int fishPosRotationIncrements;
/*     */   private double fishX;
/*     */   private double fishY;
/*     */   private double fishZ;
/*     */   private double fishYaw;
/*     */   private double fishPitch;
/*     */   private double clientMotionX;
/*     */   private double clientMotionY;
/*     */   private double clientMotionZ;
/*     */   private static final String __OBFID = "CL_00001663";
/*     */   
/*     */   public static List func_174855_j() {
/*  64 */     return FISH;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFishHook(World worldIn) {
/*  69 */     super(worldIn);
/*  70 */     this.xTile = -1;
/*  71 */     this.yTile = -1;
/*  72 */     this.zTile = -1;
/*  73 */     setSize(0.25F, 0.25F);
/*  74 */     this.ignoreFrustumCheck = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFishHook(World worldIn, double p_i1765_2_, double p_i1765_4_, double p_i1765_6_, EntityPlayer p_i1765_8_) {
/*  79 */     this(worldIn);
/*  80 */     setPosition(p_i1765_2_, p_i1765_4_, p_i1765_6_);
/*  81 */     this.ignoreFrustumCheck = true;
/*  82 */     this.angler = p_i1765_8_;
/*  83 */     p_i1765_8_.fishEntity = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFishHook(World worldIn, EntityPlayer p_i1766_2_) {
/*  88 */     super(worldIn);
/*  89 */     this.xTile = -1;
/*  90 */     this.yTile = -1;
/*  91 */     this.zTile = -1;
/*  92 */     this.ignoreFrustumCheck = true;
/*  93 */     this.angler = p_i1766_2_;
/*  94 */     this.angler.fishEntity = this;
/*  95 */     setSize(0.25F, 0.25F);
/*  96 */     setLocationAndAngles(p_i1766_2_.posX, p_i1766_2_.posY + p_i1766_2_.getEyeHeight(), p_i1766_2_.posZ, p_i1766_2_.rotationYaw, p_i1766_2_.rotationPitch);
/*  97 */     this.posX -= (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/*  98 */     this.posY -= 0.10000000149011612D;
/*  99 */     this.posZ -= (MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/* 100 */     setPosition(this.posX, this.posY, this.posZ);
/* 101 */     float var3 = 0.4F;
/* 102 */     this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/* 103 */     this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/* 104 */     this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/* 105 */     handleHookCasting(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderDist(double distance) {
/* 116 */     double var3 = getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
/* 117 */     var3 *= 64.0D;
/* 118 */     return (distance < var3 * var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHookCasting(double p_146035_1_, double p_146035_3_, double p_146035_5_, float p_146035_7_, float p_146035_8_) {
/* 123 */     float var9 = MathHelper.sqrt_double(p_146035_1_ * p_146035_1_ + p_146035_3_ * p_146035_3_ + p_146035_5_ * p_146035_5_);
/* 124 */     p_146035_1_ /= var9;
/* 125 */     p_146035_3_ /= var9;
/* 126 */     p_146035_5_ /= var9;
/* 127 */     p_146035_1_ += this.rand.nextGaussian() * 0.007499999832361937D * p_146035_8_;
/* 128 */     p_146035_3_ += this.rand.nextGaussian() * 0.007499999832361937D * p_146035_8_;
/* 129 */     p_146035_5_ += this.rand.nextGaussian() * 0.007499999832361937D * p_146035_8_;
/* 130 */     p_146035_1_ *= p_146035_7_;
/* 131 */     p_146035_3_ *= p_146035_7_;
/* 132 */     p_146035_5_ *= p_146035_7_;
/* 133 */     this.motionX = p_146035_1_;
/* 134 */     this.motionY = p_146035_3_;
/* 135 */     this.motionZ = p_146035_5_;
/* 136 */     float var10 = MathHelper.sqrt_double(p_146035_1_ * p_146035_1_ + p_146035_5_ * p_146035_5_);
/* 137 */     this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_146035_1_, p_146035_5_) * 180.0D / Math.PI);
/* 138 */     this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_146035_3_, var10) * 180.0D / Math.PI);
/* 139 */     this.ticksInGround = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
/* 144 */     this.fishX = p_180426_1_;
/* 145 */     this.fishY = p_180426_3_;
/* 146 */     this.fishZ = p_180426_5_;
/* 147 */     this.fishYaw = p_180426_7_;
/* 148 */     this.fishPitch = p_180426_8_;
/* 149 */     this.fishPosRotationIncrements = p_180426_9_;
/* 150 */     this.motionX = this.clientMotionX;
/* 151 */     this.motionY = this.clientMotionY;
/* 152 */     this.motionZ = this.clientMotionZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double x, double y, double z) {
/* 160 */     this.clientMotionX = this.motionX = x;
/* 161 */     this.clientMotionY = this.motionY = y;
/* 162 */     this.clientMotionZ = this.motionZ = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 170 */     super.onUpdate();
/*     */     
/* 172 */     if (this.fishPosRotationIncrements > 0) {
/*     */       
/* 174 */       double var28 = this.posX + (this.fishX - this.posX) / this.fishPosRotationIncrements;
/* 175 */       double var29 = this.posY + (this.fishY - this.posY) / this.fishPosRotationIncrements;
/* 176 */       double var30 = this.posZ + (this.fishZ - this.posZ) / this.fishPosRotationIncrements;
/* 177 */       double var7 = MathHelper.wrapAngleTo180_double(this.fishYaw - this.rotationYaw);
/* 178 */       this.rotationYaw = (float)(this.rotationYaw + var7 / this.fishPosRotationIncrements);
/* 179 */       this.rotationPitch = (float)(this.rotationPitch + (this.fishPitch - this.rotationPitch) / this.fishPosRotationIncrements);
/* 180 */       this.fishPosRotationIncrements--;
/* 181 */       setPosition(var28, var29, var30);
/* 182 */       setRotation(this.rotationYaw, this.rotationPitch);
/*     */     }
/*     */     else {
/*     */       
/* 186 */       if (!this.worldObj.isRemote) {
/*     */         
/* 188 */         ItemStack var1 = this.angler.getCurrentEquippedItem();
/*     */         
/* 190 */         if (this.angler.isDead || !this.angler.isEntityAlive() || var1 == null || var1.getItem() != Items.fishing_rod || getDistanceSqToEntity((Entity)this.angler) > 1024.0D) {
/*     */           
/* 192 */           setDead();
/* 193 */           this.angler.fishEntity = null;
/*     */           
/*     */           return;
/*     */         } 
/* 197 */         if (this.caughtEntity != null) {
/*     */           
/* 199 */           if (!this.caughtEntity.isDead) {
/*     */             
/* 201 */             this.posX = this.caughtEntity.posX;
/* 202 */             double var10002 = this.caughtEntity.height;
/* 203 */             this.posY = (this.caughtEntity.getEntityBoundingBox()).minY + var10002 * 0.8D;
/* 204 */             this.posZ = this.caughtEntity.posZ;
/*     */             
/*     */             return;
/*     */           } 
/* 208 */           this.caughtEntity = null;
/*     */         } 
/*     */       } 
/*     */       
/* 212 */       if (this.shake > 0)
/*     */       {
/* 214 */         this.shake--;
/*     */       }
/*     */       
/* 217 */       if (this.inGround) {
/*     */         
/* 219 */         if (this.worldObj.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile) {
/*     */           
/* 221 */           this.ticksInGround++;
/*     */           
/* 223 */           if (this.ticksInGround == 1200)
/*     */           {
/* 225 */             setDead();
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 231 */         this.inGround = false;
/* 232 */         this.motionX *= (this.rand.nextFloat() * 0.2F);
/* 233 */         this.motionY *= (this.rand.nextFloat() * 0.2F);
/* 234 */         this.motionZ *= (this.rand.nextFloat() * 0.2F);
/* 235 */         this.ticksInGround = 0;
/* 236 */         this.ticksInAir = 0;
/*     */       }
/*     */       else {
/*     */         
/* 240 */         this.ticksInAir++;
/*     */       } 
/*     */       
/* 243 */       Vec3 var27 = new Vec3(this.posX, this.posY, this.posZ);
/* 244 */       Vec3 var2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/* 245 */       MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var27, var2);
/* 246 */       var27 = new Vec3(this.posX, this.posY, this.posZ);
/* 247 */       var2 = new Vec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*     */       
/* 249 */       if (var3 != null)
/*     */       {
/* 251 */         var2 = new Vec3(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
/*     */       }
/*     */       
/* 254 */       Entity var4 = null;
/* 255 */       List<Entity> var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
/* 256 */       double var6 = 0.0D;
/*     */ 
/*     */       
/* 259 */       for (int var8 = 0; var8 < var5.size(); var8++) {
/*     */         
/* 261 */         Entity var9 = var5.get(var8);
/*     */         
/* 263 */         if (var9.canBeCollidedWith() && (var9 != this.angler || this.ticksInAir >= 5)) {
/*     */           
/* 265 */           float var10 = 0.3F;
/* 266 */           AxisAlignedBB var11 = var9.getEntityBoundingBox().expand(var10, var10, var10);
/* 267 */           MovingObjectPosition var12 = var11.calculateIntercept(var27, var2);
/*     */           
/* 269 */           if (var12 != null) {
/*     */             
/* 271 */             double var13 = var27.distanceTo(var12.hitVec);
/*     */             
/* 273 */             if (var13 < var6 || var6 == 0.0D) {
/*     */               
/* 275 */               var4 = var9;
/* 276 */               var6 = var13;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 282 */       if (var4 != null)
/*     */       {
/* 284 */         var3 = new MovingObjectPosition(var4);
/*     */       }
/*     */       
/* 287 */       if (var3 != null)
/*     */       {
/* 289 */         if (var3.entityHit != null) {
/*     */           
/* 291 */           if (var3.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, (Entity)this.angler), 0.0F))
/*     */           {
/* 293 */             this.caughtEntity = var3.entityHit;
/*     */           }
/*     */         }
/*     */         else {
/*     */           
/* 298 */           this.inGround = true;
/*     */         } 
/*     */       }
/*     */       
/* 302 */       if (!this.inGround) {
/*     */         
/* 304 */         moveEntity(this.motionX, this.motionY, this.motionZ);
/* 305 */         float var31 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 306 */         this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*     */         
/* 308 */         for (this.rotationPitch = (float)(Math.atan2(this.motionY, var31) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 313 */         while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */         {
/* 315 */           this.prevRotationPitch += 360.0F;
/*     */         }
/*     */         
/* 318 */         while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */         {
/* 320 */           this.prevRotationYaw -= 360.0F;
/*     */         }
/*     */         
/* 323 */         while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */         {
/* 325 */           this.prevRotationYaw += 360.0F;
/*     */         }
/*     */         
/* 328 */         this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 329 */         this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/* 330 */         float var32 = 0.92F;
/*     */         
/* 332 */         if (this.onGround || this.isCollidedHorizontally)
/*     */         {
/* 334 */           var32 = 0.5F;
/*     */         }
/*     */         
/* 337 */         byte var33 = 5;
/* 338 */         double var34 = 0.0D;
/*     */ 
/*     */         
/* 341 */         for (int var35 = 0; var35 < var33; var35++) {
/*     */           
/* 343 */           AxisAlignedBB var14 = getEntityBoundingBox();
/* 344 */           double var15 = var14.maxY - var14.minY;
/* 345 */           double var17 = var14.minY + var15 * var35 / var33;
/* 346 */           double var19 = var14.minY + var15 * (var35 + 1) / var33;
/* 347 */           AxisAlignedBB var21 = new AxisAlignedBB(var14.minX, var17, var14.minZ, var14.maxX, var19, var14.maxZ);
/*     */           
/* 349 */           if (this.worldObj.isAABBInMaterial(var21, Material.water))
/*     */           {
/* 351 */             var34 += 1.0D / var33;
/*     */           }
/*     */         } 
/*     */         
/* 355 */         if (!this.worldObj.isRemote && var34 > 0.0D) {
/*     */           
/* 357 */           WorldServer var36 = (WorldServer)this.worldObj;
/* 358 */           int var37 = 1;
/* 359 */           BlockPos var38 = (new BlockPos(this)).offsetUp();
/*     */           
/* 361 */           if (this.rand.nextFloat() < 0.25F && this.worldObj.func_175727_C(var38))
/*     */           {
/* 363 */             var37 = 2;
/*     */           }
/*     */           
/* 366 */           if (this.rand.nextFloat() < 0.5F && !this.worldObj.isAgainstSky(var38))
/*     */           {
/* 368 */             var37--;
/*     */           }
/*     */           
/* 371 */           if (this.ticksCatchable > 0) {
/*     */             
/* 373 */             this.ticksCatchable--;
/*     */             
/* 375 */             if (this.ticksCatchable <= 0)
/*     */             {
/* 377 */               this.ticksCaughtDelay = 0;
/* 378 */               this.ticksCatchableDelay = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 389 */           else if (this.ticksCatchableDelay > 0) {
/*     */             
/* 391 */             this.ticksCatchableDelay -= var37;
/*     */             
/* 393 */             if (this.ticksCatchableDelay <= 0)
/*     */             {
/* 395 */               this.motionY -= 0.20000000298023224D;
/* 396 */               playSound("random.splash", 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/* 397 */               float var16 = MathHelper.floor_double((getEntityBoundingBox()).minY);
/* 398 */               var36.func_175739_a(EnumParticleTypes.WATER_BUBBLE, this.posX, (var16 + 1.0F), this.posZ, (int)(1.0F + this.width * 20.0F), this.width, 0.0D, this.width, 0.20000000298023224D, new int[0]);
/* 399 */               var36.func_175739_a(EnumParticleTypes.WATER_WAKE, this.posX, (var16 + 1.0F), this.posZ, (int)(1.0F + this.width * 20.0F), this.width, 0.0D, this.width, 0.20000000298023224D, new int[0]);
/* 400 */               this.ticksCatchable = MathHelper.getRandomIntegerInRange(this.rand, 10, 30);
/*     */             }
/*     */             else
/*     */             {
/* 404 */               this.fishApproachAngle = (float)(this.fishApproachAngle + this.rand.nextGaussian() * 4.0D);
/* 405 */               float var16 = this.fishApproachAngle * 0.017453292F;
/* 406 */               float var39 = MathHelper.sin(var16);
/* 407 */               float var18 = MathHelper.cos(var16);
/* 408 */               double var19 = this.posX + (var39 * this.ticksCatchableDelay * 0.1F);
/* 409 */               double var40 = (MathHelper.floor_double((getEntityBoundingBox()).minY) + 1.0F);
/* 410 */               double var23 = this.posZ + (var18 * this.ticksCatchableDelay * 0.1F);
/*     */               
/* 412 */               if (this.rand.nextFloat() < 0.15F)
/*     */               {
/* 414 */                 var36.func_175739_a(EnumParticleTypes.WATER_BUBBLE, var19, var40 - 0.10000000149011612D, var23, 1, var39, 0.1D, var18, 0.0D, new int[0]);
/*     */               }
/*     */               
/* 417 */               float var25 = var39 * 0.04F;
/* 418 */               float var26 = var18 * 0.04F;
/* 419 */               var36.func_175739_a(EnumParticleTypes.WATER_WAKE, var19, var40, var23, 0, var26, 0.01D, -var25, 1.0D, new int[0]);
/* 420 */               var36.func_175739_a(EnumParticleTypes.WATER_WAKE, var19, var40, var23, 0, -var26, 0.01D, var25, 1.0D, new int[0]);
/*     */             }
/*     */           
/* 423 */           } else if (this.ticksCaughtDelay > 0) {
/*     */             
/* 425 */             this.ticksCaughtDelay -= var37;
/* 426 */             float var16 = 0.15F;
/*     */             
/* 428 */             if (this.ticksCaughtDelay < 20) {
/*     */               
/* 430 */               var16 = (float)(var16 + (20 - this.ticksCaughtDelay) * 0.05D);
/*     */             }
/* 432 */             else if (this.ticksCaughtDelay < 40) {
/*     */               
/* 434 */               var16 = (float)(var16 + (40 - this.ticksCaughtDelay) * 0.02D);
/*     */             }
/* 436 */             else if (this.ticksCaughtDelay < 60) {
/*     */               
/* 438 */               var16 = (float)(var16 + (60 - this.ticksCaughtDelay) * 0.01D);
/*     */             } 
/*     */             
/* 441 */             if (this.rand.nextFloat() < var16) {
/*     */               
/* 443 */               float var39 = MathHelper.randomFloatClamp(this.rand, 0.0F, 360.0F) * 0.017453292F;
/* 444 */               float var18 = MathHelper.randomFloatClamp(this.rand, 25.0F, 60.0F);
/* 445 */               double var19 = this.posX + (MathHelper.sin(var39) * var18 * 0.1F);
/* 446 */               double var40 = (MathHelper.floor_double((getEntityBoundingBox()).minY) + 1.0F);
/* 447 */               double var23 = this.posZ + (MathHelper.cos(var39) * var18 * 0.1F);
/* 448 */               var36.func_175739_a(EnumParticleTypes.WATER_SPLASH, var19, var40, var23, 2 + this.rand.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D, new int[0]);
/*     */             } 
/*     */             
/* 451 */             if (this.ticksCaughtDelay <= 0)
/*     */             {
/* 453 */               this.fishApproachAngle = MathHelper.randomFloatClamp(this.rand, 0.0F, 360.0F);
/* 454 */               this.ticksCatchableDelay = MathHelper.getRandomIntegerInRange(this.rand, 20, 80);
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 459 */             this.ticksCaughtDelay = MathHelper.getRandomIntegerInRange(this.rand, 100, 900);
/* 460 */             this.ticksCaughtDelay -= EnchantmentHelper.func_151387_h((EntityLivingBase)this.angler) * 20 * 5;
/*     */           } 
/*     */ 
/*     */           
/* 464 */           if (this.ticksCatchable > 0)
/*     */           {
/* 466 */             this.motionY -= (this.rand.nextFloat() * this.rand.nextFloat() * this.rand.nextFloat()) * 0.2D;
/*     */           }
/*     */         } 
/*     */         
/* 470 */         double var13 = var34 * 2.0D - 1.0D;
/* 471 */         this.motionY += 0.03999999910593033D * var13;
/*     */         
/* 473 */         if (var34 > 0.0D) {
/*     */           
/* 475 */           var32 = (float)(var32 * 0.9D);
/* 476 */           this.motionY *= 0.8D;
/*     */         } 
/*     */         
/* 479 */         this.motionX *= var32;
/* 480 */         this.motionY *= var32;
/* 481 */         this.motionZ *= var32;
/* 482 */         setPosition(this.posX, this.posY, this.posZ);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 492 */     tagCompound.setShort("xTile", (short)this.xTile);
/* 493 */     tagCompound.setShort("yTile", (short)this.yTile);
/* 494 */     tagCompound.setShort("zTile", (short)this.zTile);
/* 495 */     ResourceLocation var2 = (ResourceLocation)Block.blockRegistry.getNameForObject(this.inTile);
/* 496 */     tagCompound.setString("inTile", (var2 == null) ? "" : var2.toString());
/* 497 */     tagCompound.setByte("shake", (byte)this.shake);
/* 498 */     tagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 506 */     this.xTile = tagCompund.getShort("xTile");
/* 507 */     this.yTile = tagCompund.getShort("yTile");
/* 508 */     this.zTile = tagCompund.getShort("zTile");
/*     */     
/* 510 */     if (tagCompund.hasKey("inTile", 8)) {
/*     */       
/* 512 */       this.inTile = Block.getBlockFromName(tagCompund.getString("inTile"));
/*     */     }
/*     */     else {
/*     */       
/* 516 */       this.inTile = Block.getBlockById(tagCompund.getByte("inTile") & 0xFF);
/*     */     } 
/*     */     
/* 519 */     this.shake = tagCompund.getByte("shake") & 0xFF;
/* 520 */     this.inGround = (tagCompund.getByte("inGround") == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int handleHookRetraction() {
/* 525 */     if (this.worldObj.isRemote)
/*     */     {
/* 527 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 531 */     byte var1 = 0;
/*     */     
/* 533 */     if (this.caughtEntity != null) {
/*     */       
/* 535 */       double var2 = this.angler.posX - this.posX;
/* 536 */       double var4 = this.angler.posY - this.posY;
/* 537 */       double var6 = this.angler.posZ - this.posZ;
/* 538 */       double var8 = MathHelper.sqrt_double(var2 * var2 + var4 * var4 + var6 * var6);
/* 539 */       double var10 = 0.1D;
/* 540 */       this.caughtEntity.motionX += var2 * var10;
/* 541 */       this.caughtEntity.motionY += var4 * var10 + MathHelper.sqrt_double(var8) * 0.08D;
/* 542 */       this.caughtEntity.motionZ += var6 * var10;
/* 543 */       var1 = 3;
/*     */     }
/* 545 */     else if (this.ticksCatchable > 0) {
/*     */       
/* 547 */       EntityItem var13 = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, func_146033_f());
/* 548 */       double var3 = this.angler.posX - this.posX;
/* 549 */       double var5 = this.angler.posY - this.posY;
/* 550 */       double var7 = this.angler.posZ - this.posZ;
/* 551 */       double var9 = MathHelper.sqrt_double(var3 * var3 + var5 * var5 + var7 * var7);
/* 552 */       double var11 = 0.1D;
/* 553 */       var13.motionX = var3 * var11;
/* 554 */       var13.motionY = var5 * var11 + MathHelper.sqrt_double(var9) * 0.08D;
/* 555 */       var13.motionZ = var7 * var11;
/* 556 */       this.worldObj.spawnEntityInWorld((Entity)var13);
/* 557 */       this.angler.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(this.angler.worldObj, this.angler.posX, this.angler.posY + 0.5D, this.angler.posZ + 0.5D, this.rand.nextInt(6) + 1));
/* 558 */       var1 = 1;
/*     */     } 
/*     */     
/* 561 */     if (this.inGround)
/*     */     {
/* 563 */       var1 = 2;
/*     */     }
/*     */     
/* 566 */     setDead();
/* 567 */     this.angler.fishEntity = null;
/* 568 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ItemStack func_146033_f() {
/* 574 */     float var1 = this.worldObj.rand.nextFloat();
/* 575 */     int var2 = EnchantmentHelper.func_151386_g((EntityLivingBase)this.angler);
/* 576 */     int var3 = EnchantmentHelper.func_151387_h((EntityLivingBase)this.angler);
/* 577 */     float var4 = 0.1F - var2 * 0.025F - var3 * 0.01F;
/* 578 */     float var5 = 0.05F + var2 * 0.01F - var3 * 0.01F;
/* 579 */     var4 = MathHelper.clamp_float(var4, 0.0F, 1.0F);
/* 580 */     var5 = MathHelper.clamp_float(var5, 0.0F, 1.0F);
/*     */     
/* 582 */     if (var1 < var4) {
/*     */       
/* 584 */       this.angler.triggerAchievement(StatList.junkFishedStat);
/* 585 */       return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.rand, JUNK)).getItemStack(this.rand);
/*     */     } 
/*     */ 
/*     */     
/* 589 */     var1 -= var4;
/*     */     
/* 591 */     if (var1 < var5) {
/*     */       
/* 593 */       this.angler.triggerAchievement(StatList.treasureFishedStat);
/* 594 */       return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.rand, VALUABLES)).getItemStack(this.rand);
/*     */     } 
/*     */ 
/*     */     
/* 598 */     float var10000 = var1 - var5;
/* 599 */     this.angler.triggerAchievement(StatList.fishCaughtStat);
/* 600 */     return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.rand, FISH)).getItemStack(this.rand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 610 */     super.setDead();
/*     */     
/* 612 */     if (this.angler != null)
/*     */     {
/* 614 */       this.angler.fishEntity = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\projectile\EntityFishHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */