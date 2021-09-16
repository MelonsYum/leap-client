/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityLookHelper;
/*     */ import net.minecraft.entity.ai.EntityMoveHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.projectile.EntityFishHook;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemFishFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathNavigateSwimmer;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.util.WeightedRandomFishable;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityGuardian
/*     */   extends EntityMob {
/*     */   private float field_175482_b;
/*     */   private float field_175484_c;
/*     */   private float field_175483_bk;
/*     */   private float field_175485_bl;
/*     */   private float field_175486_bm;
/*     */   private EntityLivingBase field_175478_bn;
/*     */   private int field_175479_bo;
/*     */   private boolean field_175480_bp;
/*     */   private EntityAIWander field_175481_bq;
/*     */   private static final String __OBFID = "CL_00002213";
/*     */   
/*     */   public EntityGuardian(World worldIn) {
/*  57 */     super(worldIn);
/*  58 */     this.experienceValue = 10;
/*  59 */     setSize(0.85F, 0.85F);
/*  60 */     this.tasks.addTask(4, new AIGuardianAttack());
/*     */     EntityAIMoveTowardsRestriction var2;
/*  62 */     this.tasks.addTask(5, (EntityAIBase)(var2 = new EntityAIMoveTowardsRestriction(this, 1.0D)));
/*  63 */     this.tasks.addTask(7, (EntityAIBase)(this.field_175481_bq = new EntityAIWander(this, 1.0D, 80)));
/*  64 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  65 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityGuardian.class, 12.0F, 0.01F));
/*  66 */     this.tasks.addTask(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  67 */     this.field_175481_bq.setMutexBits(3);
/*  68 */     var2.setMutexBits(3);
/*  69 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 10, true, false, new GuardianTargetSelector()));
/*  70 */     this.moveHelper = new GuardianMoveHelper();
/*  71 */     this.field_175484_c = this.field_175482_b = this.rand.nextFloat();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  76 */     super.applyEntityAttributes();
/*  77 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6.0D);
/*  78 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
/*  79 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
/*  80 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  88 */     super.readEntityFromNBT(tagCompund);
/*  89 */     func_175467_a(tagCompund.getBoolean("Elder"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  97 */     super.writeEntityToNBT(tagCompound);
/*  98 */     tagCompound.setBoolean("Elder", func_175461_cl());
/*     */   }
/*     */ 
/*     */   
/*     */   protected PathNavigate func_175447_b(World worldIn) {
/* 103 */     return (PathNavigate)new PathNavigateSwimmer((EntityLiving)this, worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 108 */     super.entityInit();
/* 109 */     this.dataWatcher.addObject(16, Integer.valueOf(0));
/* 110 */     this.dataWatcher.addObject(17, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_175468_a(int p_175468_1_) {
/* 115 */     return ((this.dataWatcher.getWatchableObjectInt(16) & p_175468_1_) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175473_a(int p_175473_1_, boolean p_175473_2_) {
/* 120 */     int var3 = this.dataWatcher.getWatchableObjectInt(16);
/*     */     
/* 122 */     if (p_175473_2_) {
/*     */       
/* 124 */       this.dataWatcher.updateObject(16, Integer.valueOf(var3 | p_175473_1_));
/*     */     }
/*     */     else {
/*     */       
/* 128 */       this.dataWatcher.updateObject(16, Integer.valueOf(var3 & (p_175473_1_ ^ 0xFFFFFFFF)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175472_n() {
/* 134 */     return func_175468_a(2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175476_l(boolean p_175476_1_) {
/* 139 */     func_175473_a(2, p_175476_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_175464_ck() {
/* 144 */     return func_175461_cl() ? 60 : 80;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175461_cl() {
/* 149 */     return func_175468_a(4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175467_a(boolean p_175467_1_) {
/* 154 */     func_175473_a(4, p_175467_1_);
/*     */     
/* 156 */     if (p_175467_1_) {
/*     */       
/* 158 */       setSize(1.9975F, 1.9975F);
/* 159 */       getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
/* 160 */       getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(8.0D);
/* 161 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80.0D);
/* 162 */       enablePersistence();
/* 163 */       this.field_175481_bq.func_179479_b(400);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175465_cm() {
/* 169 */     func_175467_a(true);
/* 170 */     this.field_175486_bm = this.field_175485_bl = 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175463_b(int p_175463_1_) {
/* 175 */     this.dataWatcher.updateObject(17, Integer.valueOf(p_175463_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175474_cn() {
/* 180 */     return (this.dataWatcher.getWatchableObjectInt(17) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingBase func_175466_co() {
/* 185 */     if (!func_175474_cn())
/*     */     {
/* 187 */       return null;
/*     */     }
/* 189 */     if (this.worldObj.isRemote) {
/*     */       
/* 191 */       if (this.field_175478_bn != null)
/*     */       {
/* 193 */         return this.field_175478_bn;
/*     */       }
/*     */ 
/*     */       
/* 197 */       Entity var1 = this.worldObj.getEntityByID(this.dataWatcher.getWatchableObjectInt(17));
/*     */       
/* 199 */       if (var1 instanceof EntityLivingBase) {
/*     */         
/* 201 */         this.field_175478_bn = (EntityLivingBase)var1;
/* 202 */         return this.field_175478_bn;
/*     */       } 
/*     */ 
/*     */       
/* 206 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 212 */     return getAttackTarget();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145781_i(int p_145781_1_) {
/* 218 */     super.func_145781_i(p_145781_1_);
/*     */     
/* 220 */     if (p_145781_1_ == 16) {
/*     */       
/* 222 */       if (func_175461_cl() && this.width < 1.0F)
/*     */       {
/* 224 */         setSize(1.9975F, 1.9975F);
/*     */       }
/*     */     }
/* 227 */     else if (p_145781_1_ == 17) {
/*     */       
/* 229 */       this.field_175479_bo = 0;
/* 230 */       this.field_175478_bn = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTalkInterval() {
/* 239 */     return 160;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 247 */     return !isInWater() ? "mob.guardian.land.idle" : (func_175461_cl() ? "mob.guardian.elder.idle" : "mob.guardian.idle");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 255 */     return !isInWater() ? "mob.guardian.land.hit" : (func_175461_cl() ? "mob.guardian.elder.hit" : "mob.guardian.hit");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 263 */     return !isInWater() ? "mob.guardian.land.death" : (func_175461_cl() ? "mob.guardian.elder.death" : "mob.guardian.death");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/* 272 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 277 */     return this.height * 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_180484_a(BlockPos p_180484_1_) {
/* 282 */     return (this.worldObj.getBlockState(p_180484_1_).getBlock().getMaterial() == Material.water) ? (10.0F + this.worldObj.getLightBrightness(p_180484_1_) - 0.5F) : super.func_180484_a(p_180484_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 291 */     if (this.worldObj.isRemote) {
/*     */       
/* 293 */       this.field_175484_c = this.field_175482_b;
/*     */       
/* 295 */       if (!isInWater()) {
/*     */         
/* 297 */         this.field_175483_bk = 2.0F;
/*     */         
/* 299 */         if (this.motionY > 0.0D && this.field_175480_bp && !isSlient())
/*     */         {
/* 301 */           this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.guardian.flop", 1.0F, 1.0F, false);
/*     */         }
/*     */         
/* 304 */         this.field_175480_bp = (this.motionY < 0.0D && this.worldObj.func_175677_d((new BlockPos((Entity)this)).offsetDown(), false));
/*     */       }
/* 306 */       else if (func_175472_n()) {
/*     */         
/* 308 */         if (this.field_175483_bk < 0.5F)
/*     */         {
/* 310 */           this.field_175483_bk = 4.0F;
/*     */         }
/*     */         else
/*     */         {
/* 314 */           this.field_175483_bk += (0.5F - this.field_175483_bk) * 0.1F;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 319 */         this.field_175483_bk += (0.125F - this.field_175483_bk) * 0.2F;
/*     */       } 
/*     */       
/* 322 */       this.field_175482_b += this.field_175483_bk;
/* 323 */       this.field_175486_bm = this.field_175485_bl;
/*     */       
/* 325 */       if (!isInWater()) {
/*     */         
/* 327 */         this.field_175485_bl = this.rand.nextFloat();
/*     */       }
/* 329 */       else if (func_175472_n()) {
/*     */         
/* 331 */         this.field_175485_bl += (0.0F - this.field_175485_bl) * 0.25F;
/*     */       }
/*     */       else {
/*     */         
/* 335 */         this.field_175485_bl += (1.0F - this.field_175485_bl) * 0.06F;
/*     */       } 
/*     */       
/* 338 */       if (func_175472_n() && isInWater()) {
/*     */         
/* 340 */         Vec3 var1 = getLook(0.0F);
/*     */         
/* 342 */         for (int var2 = 0; var2 < 2; var2++)
/*     */         {
/* 344 */           this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + (this.rand.nextDouble() - 0.5D) * this.width - var1.xCoord * 1.5D, this.posY + this.rand.nextDouble() * this.height - var1.yCoord * 1.5D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width - var1.zCoord * 1.5D, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */         }
/*     */       } 
/*     */       
/* 348 */       if (func_175474_cn()) {
/*     */         
/* 350 */         if (this.field_175479_bo < func_175464_ck())
/*     */         {
/* 352 */           this.field_175479_bo++;
/*     */         }
/*     */         
/* 355 */         EntityLivingBase var14 = func_175466_co();
/*     */         
/* 357 */         if (var14 != null) {
/*     */           
/* 359 */           getLookHelper().setLookPositionWithEntity((Entity)var14, 90.0F, 90.0F);
/* 360 */           getLookHelper().onUpdateLook();
/* 361 */           double var15 = func_175477_p(0.0F);
/* 362 */           double var4 = var14.posX - this.posX;
/* 363 */           double var6 = var14.posY + (var14.height * 0.5F) - this.posY + getEyeHeight();
/* 364 */           double var8 = var14.posZ - this.posZ;
/* 365 */           double var10 = Math.sqrt(var4 * var4 + var6 * var6 + var8 * var8);
/* 366 */           var4 /= var10;
/* 367 */           var6 /= var10;
/* 368 */           var8 /= var10;
/* 369 */           double var12 = this.rand.nextDouble();
/*     */           
/* 371 */           while (var12 < var10) {
/*     */             
/* 373 */             var12 += 1.8D - var15 + this.rand.nextDouble() * (1.7D - var15);
/* 374 */             this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + var4 * var12, this.posY + var6 * var12 + getEyeHeight(), this.posZ + var8 * var12, 0.0D, 0.0D, 0.0D, new int[0]);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 380 */     if (this.inWater) {
/*     */       
/* 382 */       setAir(300);
/*     */     }
/* 384 */     else if (this.onGround) {
/*     */       
/* 386 */       this.motionY += 0.5D;
/* 387 */       this.motionX += ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
/* 388 */       this.motionZ += ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.4F);
/* 389 */       this.rotationYaw = this.rand.nextFloat() * 360.0F;
/* 390 */       this.onGround = false;
/* 391 */       this.isAirBorne = true;
/*     */     } 
/*     */     
/* 394 */     if (func_175474_cn())
/*     */     {
/* 396 */       this.rotationYaw = this.rotationYawHead;
/*     */     }
/*     */     
/* 399 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_175471_a(float p_175471_1_) {
/* 404 */     return this.field_175484_c + (this.field_175482_b - this.field_175484_c) * p_175471_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_175469_o(float p_175469_1_) {
/* 409 */     return this.field_175486_bm + (this.field_175485_bl - this.field_175486_bm) * p_175469_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_175477_p(float p_175477_1_) {
/* 414 */     return (this.field_175479_bo + p_175477_1_) / func_175464_ck();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/* 419 */     super.updateAITasks();
/*     */     
/* 421 */     if (func_175461_cl()) {
/*     */       
/* 423 */       boolean var1 = true;
/* 424 */       boolean var2 = true;
/* 425 */       boolean var3 = true;
/* 426 */       boolean var4 = true;
/*     */       
/* 428 */       if ((this.ticksExisted + getEntityId()) % 1200 == 0) {
/*     */         
/* 430 */         Potion var5 = Potion.digSlowdown;
/* 431 */         List var6 = this.worldObj.func_175661_b(EntityPlayerMP.class, new Predicate()
/*     */             {
/*     */               private static final String __OBFID = "CL_00002212";
/*     */               
/*     */               public boolean func_179913_a(EntityPlayerMP p_179913_1_) {
/* 436 */                 return (EntityGuardian.this.getDistanceSqToEntity((Entity)p_179913_1_) < 2500.0D && p_179913_1_.theItemInWorldManager.func_180239_c());
/*     */               }
/*     */               
/*     */               public boolean apply(Object p_apply_1_) {
/* 440 */                 return func_179913_a((EntityPlayerMP)p_apply_1_);
/*     */               }
/*     */             });
/* 443 */         Iterator<EntityPlayerMP> var7 = var6.iterator();
/*     */         
/* 445 */         while (var7.hasNext()) {
/*     */           
/* 447 */           EntityPlayerMP var8 = var7.next();
/*     */           
/* 449 */           if (!var8.isPotionActive(var5) || var8.getActivePotionEffect(var5).getAmplifier() < 2 || var8.getActivePotionEffect(var5).getDuration() < 1200) {
/*     */             
/* 451 */             var8.playerNetServerHandler.sendPacket((Packet)new S2BPacketChangeGameState(10, 0.0F));
/* 452 */             var8.addPotionEffect(new PotionEffect(var5.id, 6000, 2));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 457 */       if (!hasHome())
/*     */       {
/* 459 */         func_175449_a(new BlockPos((Entity)this), 16);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 469 */     int var3 = this.rand.nextInt(3) + this.rand.nextInt(p_70628_2_ + 1);
/*     */     
/* 471 */     if (var3 > 0)
/*     */     {
/* 473 */       entityDropItem(new ItemStack(Items.prismarine_shard, var3, 0), 1.0F);
/*     */     }
/*     */     
/* 476 */     if (this.rand.nextInt(3 + p_70628_2_) > 1) {
/*     */       
/* 478 */       entityDropItem(new ItemStack(Items.fish, 1, ItemFishFood.FishType.COD.getItemDamage()), 1.0F);
/*     */     }
/* 480 */     else if (this.rand.nextInt(3 + p_70628_2_) > 1) {
/*     */       
/* 482 */       entityDropItem(new ItemStack(Items.prismarine_crystals, 1, 0), 1.0F);
/*     */     } 
/*     */     
/* 485 */     if (p_70628_1_ && func_175461_cl())
/*     */     {
/* 487 */       entityDropItem(new ItemStack(Blocks.sponge, 1, 1), 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addRandomArmor() {
/* 496 */     ItemStack var1 = ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.rand, EntityFishHook.func_174855_j())).getItemStack(this.rand);
/* 497 */     entityDropItem(var1, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 505 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleLavaMovement() {
/* 513 */     return (this.worldObj.checkNoEntityCollision(getEntityBoundingBox(), (Entity)this) && this.worldObj.getCollidingBoundingBoxes((Entity)this, getEntityBoundingBox()).isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere() {
/* 521 */     return ((this.rand.nextInt(20) == 0 || !this.worldObj.canBlockSeeSky(new BlockPos((Entity)this))) && super.getCanSpawnHere());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 529 */     if (!func_175472_n() && !source.isMagicDamage() && source.getSourceOfDamage() instanceof EntityLivingBase) {
/*     */       
/* 531 */       EntityLivingBase var3 = (EntityLivingBase)source.getSourceOfDamage();
/*     */       
/* 533 */       if (!source.isExplosion()) {
/*     */         
/* 535 */         var3.attackEntityFrom(DamageSource.causeThornsDamage((Entity)this), 2.0F);
/* 536 */         var3.playSound("damage.thorns", 0.5F, 1.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 540 */     this.field_175481_bq.func_179480_f();
/* 541 */     return super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVerticalFaceSpeed() {
/* 550 */     return 180;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
/* 558 */     if (isServerWorld()) {
/*     */       
/* 560 */       if (isInWater())
/*     */       {
/* 562 */         moveFlying(p_70612_1_, p_70612_2_, 0.1F);
/* 563 */         moveEntity(this.motionX, this.motionY, this.motionZ);
/* 564 */         this.motionX *= 0.8999999761581421D;
/* 565 */         this.motionY *= 0.8999999761581421D;
/* 566 */         this.motionZ *= 0.8999999761581421D;
/*     */         
/* 568 */         if (!func_175472_n() && getAttackTarget() == null)
/*     */         {
/* 570 */           this.motionY -= 0.005D;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 575 */         super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 580 */       super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
/*     */     } 
/*     */   }
/*     */   
/*     */   class AIGuardianAttack
/*     */     extends EntityAIBase {
/* 586 */     private EntityGuardian field_179456_a = EntityGuardian.this;
/*     */     
/*     */     private int field_179455_b;
/*     */     private static final String __OBFID = "CL_00002211";
/*     */     
/*     */     public AIGuardianAttack() {
/* 592 */       setMutexBits(3);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 597 */       EntityLivingBase var1 = this.field_179456_a.getAttackTarget();
/* 598 */       return (var1 != null && var1.isEntityAlive());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean continueExecuting() {
/* 603 */       return (super.continueExecuting() && (this.field_179456_a.func_175461_cl() || this.field_179456_a.getDistanceSqToEntity((Entity)this.field_179456_a.getAttackTarget()) > 9.0D));
/*     */     }
/*     */ 
/*     */     
/*     */     public void startExecuting() {
/* 608 */       this.field_179455_b = -10;
/* 609 */       this.field_179456_a.getNavigator().clearPathEntity();
/* 610 */       this.field_179456_a.getLookHelper().setLookPositionWithEntity((Entity)this.field_179456_a.getAttackTarget(), 90.0F, 90.0F);
/* 611 */       this.field_179456_a.isAirBorne = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void resetTask() {
/* 616 */       this.field_179456_a.func_175463_b(0);
/* 617 */       this.field_179456_a.setAttackTarget(null);
/* 618 */       this.field_179456_a.field_175481_bq.func_179480_f();
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 623 */       EntityLivingBase var1 = this.field_179456_a.getAttackTarget();
/* 624 */       this.field_179456_a.getNavigator().clearPathEntity();
/* 625 */       this.field_179456_a.getLookHelper().setLookPositionWithEntity((Entity)var1, 90.0F, 90.0F);
/*     */       
/* 627 */       if (!this.field_179456_a.canEntityBeSeen((Entity)var1)) {
/*     */         
/* 629 */         this.field_179456_a.setAttackTarget(null);
/*     */       }
/*     */       else {
/*     */         
/* 633 */         this.field_179455_b++;
/*     */         
/* 635 */         if (this.field_179455_b == 0) {
/*     */           
/* 637 */           this.field_179456_a.func_175463_b(this.field_179456_a.getAttackTarget().getEntityId());
/* 638 */           this.field_179456_a.worldObj.setEntityState((Entity)this.field_179456_a, (byte)21);
/*     */         }
/* 640 */         else if (this.field_179455_b >= this.field_179456_a.func_175464_ck()) {
/*     */           
/* 642 */           float var2 = 1.0F;
/*     */           
/* 644 */           if (this.field_179456_a.worldObj.getDifficulty() == EnumDifficulty.HARD)
/*     */           {
/* 646 */             var2 += 2.0F;
/*     */           }
/*     */           
/* 649 */           if (this.field_179456_a.func_175461_cl())
/*     */           {
/* 651 */             var2 += 2.0F;
/*     */           }
/*     */           
/* 654 */           var1.attackEntityFrom(DamageSource.causeIndirectMagicDamage((Entity)this.field_179456_a, (Entity)this.field_179456_a), var2);
/* 655 */           var1.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this.field_179456_a), (float)this.field_179456_a.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
/* 656 */           this.field_179456_a.setAttackTarget(null);
/*     */         }
/* 658 */         else if (this.field_179455_b < 60 || this.field_179455_b % 20 == 0) {
/*     */         
/*     */         } 
/*     */ 
/*     */         
/* 663 */         super.updateTask();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class GuardianMoveHelper
/*     */     extends EntityMoveHelper {
/* 670 */     private EntityGuardian field_179930_g = EntityGuardian.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002209";
/*     */     
/*     */     public GuardianMoveHelper() {
/* 675 */       super((EntityLiving)EntityGuardian.this);
/*     */     }
/*     */ 
/*     */     
/*     */     public void onUpdateMoveHelper() {
/* 680 */       if (this.update && !this.field_179930_g.getNavigator().noPath()) {
/*     */         
/* 682 */         double var1 = this.posX - this.field_179930_g.posX;
/* 683 */         double var3 = this.posY - this.field_179930_g.posY;
/* 684 */         double var5 = this.posZ - this.field_179930_g.posZ;
/* 685 */         double var7 = var1 * var1 + var3 * var3 + var5 * var5;
/* 686 */         var7 = MathHelper.sqrt_double(var7);
/* 687 */         var3 /= var7;
/* 688 */         float var9 = (float)(Math.atan2(var5, var1) * 180.0D / Math.PI) - 90.0F;
/* 689 */         this.field_179930_g.rotationYaw = limitAngle(this.field_179930_g.rotationYaw, var9, 30.0F);
/* 690 */         this.field_179930_g.renderYawOffset = this.field_179930_g.rotationYaw;
/* 691 */         float var10 = (float)(this.speed * this.field_179930_g.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
/* 692 */         this.field_179930_g.setAIMoveSpeed(this.field_179930_g.getAIMoveSpeed() + (var10 - this.field_179930_g.getAIMoveSpeed()) * 0.125F);
/* 693 */         double var11 = Math.sin((this.field_179930_g.ticksExisted + this.field_179930_g.getEntityId()) * 0.5D) * 0.05D;
/* 694 */         double var13 = Math.cos((this.field_179930_g.rotationYaw * 3.1415927F / 180.0F));
/* 695 */         double var15 = Math.sin((this.field_179930_g.rotationYaw * 3.1415927F / 180.0F));
/* 696 */         this.field_179930_g.motionX += var11 * var13;
/* 697 */         this.field_179930_g.motionZ += var11 * var15;
/* 698 */         var11 = Math.sin((this.field_179930_g.ticksExisted + this.field_179930_g.getEntityId()) * 0.75D) * 0.05D;
/* 699 */         this.field_179930_g.motionY += var11 * (var15 + var13) * 0.25D;
/* 700 */         this.field_179930_g.motionY += this.field_179930_g.getAIMoveSpeed() * var3 * 0.1D;
/* 701 */         EntityLookHelper var17 = this.field_179930_g.getLookHelper();
/* 702 */         double var18 = this.field_179930_g.posX + var1 / var7 * 2.0D;
/* 703 */         double var20 = this.field_179930_g.getEyeHeight() + this.field_179930_g.posY + var3 / var7 * 1.0D;
/* 704 */         double var22 = this.field_179930_g.posZ + var5 / var7 * 2.0D;
/* 705 */         double var24 = var17.func_180423_e();
/* 706 */         double var26 = var17.func_180422_f();
/* 707 */         double var28 = var17.func_180421_g();
/*     */         
/* 709 */         if (!var17.func_180424_b()) {
/*     */           
/* 711 */           var24 = var18;
/* 712 */           var26 = var20;
/* 713 */           var28 = var22;
/*     */         } 
/*     */         
/* 716 */         this.field_179930_g.getLookHelper().setLookPosition(var24 + (var18 - var24) * 0.125D, var26 + (var20 - var26) * 0.125D, var28 + (var22 - var28) * 0.125D, 10.0F, 40.0F);
/* 717 */         this.field_179930_g.func_175476_l(true);
/*     */       }
/*     */       else {
/*     */         
/* 721 */         this.field_179930_g.setAIMoveSpeed(0.0F);
/* 722 */         this.field_179930_g.func_175476_l(false);
/*     */       } 
/*     */     } }
/*     */   
/*     */   class GuardianTargetSelector implements Predicate { private EntityGuardian field_179916_a;
/*     */     
/*     */     GuardianTargetSelector() {
/* 729 */       this.field_179916_a = EntityGuardian.this;
/*     */     }
/*     */     private static final String __OBFID = "CL_00002210";
/*     */     
/*     */     public boolean func_179915_a(EntityLivingBase p_179915_1_) {
/* 734 */       return ((p_179915_1_ instanceof EntityPlayer || p_179915_1_ instanceof net.minecraft.entity.passive.EntitySquid) && p_179915_1_.getDistanceSqToEntity((Entity)this.field_179916_a) > 9.0D);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean apply(Object p_apply_1_) {
/* 739 */       return func_179915_a((EntityLivingBase)p_apply_1_);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */