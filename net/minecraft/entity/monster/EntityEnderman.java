/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityEnderman
/*     */   extends EntityMob {
/*  44 */   private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
/*  45 */   private static final AttributeModifier attackingSpeedBoostModifier = (new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 0.15000000596046448D, 0)).setSaved(false);
/*  46 */   private static final Set carriableBlocks = Sets.newIdentityHashSet();
/*     */   
/*     */   private boolean isAggressive;
/*     */   private static final String __OBFID = "CL_00001685";
/*     */   
/*     */   public EntityEnderman(World worldIn) {
/*  52 */     super(worldIn);
/*  53 */     setSize(0.6F, 2.9F);
/*  54 */     this.stepHeight = 1.0F;
/*  55 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  56 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIAttackOnCollide(this, 1.0D, false));
/*  57 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWander(this, 1.0D));
/*  58 */     this.tasks.addTask(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  59 */     this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  60 */     this.tasks.addTask(10, new AIPlaceBlock());
/*  61 */     this.tasks.addTask(11, new AITakeBlock());
/*  62 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget(this, false, new Class[0]));
/*  63 */     this.targetTasks.addTask(2, (EntityAIBase)new AIFindPlayer());
/*  64 */     this.targetTasks.addTask(3, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityEndermite.class, 10, true, false, new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002223";
/*     */             
/*     */             public boolean func_179948_a(EntityEndermite p_179948_1_) {
/*  69 */               return p_179948_1_.isSpawnedByPlayer();
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/*  73 */               return func_179948_a((EntityEndermite)p_apply_1_);
/*     */             }
/*     */           }));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  80 */     super.applyEntityAttributes();
/*  81 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
/*  82 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
/*  83 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(7.0D);
/*  84 */     getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  89 */     super.entityInit();
/*  90 */     this.dataWatcher.addObject(16, new Short((short)0));
/*  91 */     this.dataWatcher.addObject(17, new Byte((byte)0));
/*  92 */     this.dataWatcher.addObject(18, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 100 */     super.writeEntityToNBT(tagCompound);
/* 101 */     IBlockState var2 = func_175489_ck();
/* 102 */     tagCompound.setShort("carried", (short)Block.getIdFromBlock(var2.getBlock()));
/* 103 */     tagCompound.setShort("carriedData", (short)var2.getBlock().getMetaFromState(var2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*     */     IBlockState var2;
/* 111 */     super.readEntityFromNBT(tagCompund);
/*     */ 
/*     */     
/* 114 */     if (tagCompund.hasKey("carried", 8)) {
/*     */       
/* 116 */       var2 = Block.getBlockFromName(tagCompund.getString("carried")).getStateFromMeta(tagCompund.getShort("carriedData") & 0xFFFF);
/*     */     }
/*     */     else {
/*     */       
/* 120 */       var2 = Block.getBlockById(tagCompund.getShort("carried")).getStateFromMeta(tagCompund.getShort("carriedData") & 0xFFFF);
/*     */     } 
/*     */     
/* 123 */     func_175490_a(var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean shouldAttackPlayer(EntityPlayer p_70821_1_) {
/* 131 */     ItemStack var2 = p_70821_1_.inventory.armorInventory[3];
/*     */     
/* 133 */     if (var2 != null && var2.getItem() == Item.getItemFromBlock(Blocks.pumpkin))
/*     */     {
/* 135 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 139 */     Vec3 var3 = p_70821_1_.getLook(1.0F).normalize();
/* 140 */     Vec3 var4 = new Vec3(this.posX - p_70821_1_.posX, (getEntityBoundingBox()).minY + (this.height / 2.0F) - p_70821_1_.posY + p_70821_1_.getEyeHeight(), this.posZ - p_70821_1_.posZ);
/* 141 */     double var5 = var4.lengthVector();
/* 142 */     var4 = var4.normalize();
/* 143 */     double var7 = var3.dotProduct(var4);
/* 144 */     return (var7 > 1.0D - 0.025D / var5) ? p_70821_1_.canEntityBeSeen((Entity)this) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 150 */     return 2.55F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 159 */     if (this.worldObj.isRemote)
/*     */     {
/* 161 */       for (int var1 = 0; var1 < 2; var1++)
/*     */       {
/* 163 */         this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D, new int[0]);
/*     */       }
/*     */     }
/*     */     
/* 167 */     this.isJumping = false;
/* 168 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/* 173 */     if (isWet())
/*     */     {
/* 175 */       attackEntityFrom(DamageSource.drown, 1.0F);
/*     */     }
/*     */     
/* 178 */     if (isScreaming() && !this.isAggressive && this.rand.nextInt(100) == 0)
/*     */     {
/* 180 */       setScreaming(false);
/*     */     }
/*     */     
/* 183 */     if (this.worldObj.isDaytime()) {
/*     */       
/* 185 */       float var1 = getBrightness(1.0F);
/*     */       
/* 187 */       if (var1 > 0.5F && this.worldObj.isAgainstSky(new BlockPos((Entity)this)) && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
/*     */         
/* 189 */         setAttackTarget(null);
/* 190 */         setScreaming(false);
/* 191 */         this.isAggressive = false;
/* 192 */         teleportRandomly();
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     super.updateAITasks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean teleportRandomly() {
/* 204 */     double var1 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
/* 205 */     double var3 = this.posY + (this.rand.nextInt(64) - 32);
/* 206 */     double var5 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
/* 207 */     return teleportTo(var1, var3, var5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean teleportToEntity(Entity p_70816_1_) {
/* 215 */     Vec3 var2 = new Vec3(this.posX - p_70816_1_.posX, (getEntityBoundingBox()).minY + (this.height / 2.0F) - p_70816_1_.posY + p_70816_1_.getEyeHeight(), this.posZ - p_70816_1_.posZ);
/* 216 */     var2 = var2.normalize();
/* 217 */     double var3 = 16.0D;
/* 218 */     double var5 = this.posX + (this.rand.nextDouble() - 0.5D) * 8.0D - var2.xCoord * var3;
/* 219 */     double var7 = this.posY + (this.rand.nextInt(16) - 8) - var2.yCoord * var3;
/* 220 */     double var9 = this.posZ + (this.rand.nextDouble() - 0.5D) * 8.0D - var2.zCoord * var3;
/* 221 */     return teleportTo(var5, var7, var9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
/* 229 */     double var7 = this.posX;
/* 230 */     double var9 = this.posY;
/* 231 */     double var11 = this.posZ;
/* 232 */     this.posX = p_70825_1_;
/* 233 */     this.posY = p_70825_3_;
/* 234 */     this.posZ = p_70825_5_;
/* 235 */     boolean var13 = false;
/* 236 */     BlockPos var14 = new BlockPos(this.posX, this.posY, this.posZ);
/*     */     
/* 238 */     if (this.worldObj.isBlockLoaded(var14)) {
/*     */       
/* 240 */       boolean var15 = false;
/*     */       
/* 242 */       while (!var15 && var14.getY() > 0) {
/*     */         
/* 244 */         BlockPos var16 = var14.offsetDown();
/* 245 */         Block var17 = this.worldObj.getBlockState(var16).getBlock();
/*     */         
/* 247 */         if (var17.getMaterial().blocksMovement()) {
/*     */           
/* 249 */           var15 = true;
/*     */           
/*     */           continue;
/*     */         } 
/* 253 */         this.posY--;
/* 254 */         var14 = var16;
/*     */       } 
/*     */ 
/*     */       
/* 258 */       if (var15) {
/*     */         
/* 260 */         setPositionAndUpdate(this.posX, this.posY, this.posZ);
/*     */         
/* 262 */         if (this.worldObj.getCollidingBoundingBoxes((Entity)this, getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(getEntityBoundingBox()))
/*     */         {
/* 264 */           var13 = true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 269 */     if (!var13) {
/*     */       
/* 271 */       setPosition(var7, var9, var11);
/* 272 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 276 */     short var28 = 128;
/*     */     
/* 278 */     for (int var29 = 0; var29 < var28; var29++) {
/*     */       
/* 280 */       double var30 = var29 / (var28 - 1.0D);
/* 281 */       float var19 = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 282 */       float var20 = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 283 */       float var21 = (this.rand.nextFloat() - 0.5F) * 0.2F;
/* 284 */       double var22 = var7 + (this.posX - var7) * var30 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
/* 285 */       double var24 = var9 + (this.posY - var9) * var30 + this.rand.nextDouble() * this.height;
/* 286 */       double var26 = var11 + (this.posZ - var11) * var30 + (this.rand.nextDouble() - 0.5D) * this.width * 2.0D;
/* 287 */       this.worldObj.spawnParticle(EnumParticleTypes.PORTAL, var22, var24, var26, var19, var20, var21, new int[0]);
/*     */     } 
/*     */     
/* 290 */     this.worldObj.playSoundEffect(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
/* 291 */     playSound("mob.endermen.portal", 1.0F, 1.0F);
/* 292 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 301 */     return isScreaming() ? "mob.endermen.scream" : "mob.endermen.idle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 309 */     return "mob.endermen.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 317 */     return "mob.endermen.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 322 */     return Items.ender_pearl;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 330 */     Item var3 = getDropItem();
/*     */     
/* 332 */     if (var3 != null) {
/*     */       
/* 334 */       int var4 = this.rand.nextInt(2 + p_70628_2_);
/*     */       
/* 336 */       for (int var5 = 0; var5 < var4; var5++)
/*     */       {
/* 338 */         dropItem(var3, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175490_a(IBlockState p_175490_1_) {
/* 345 */     this.dataWatcher.updateObject(16, Short.valueOf((short)(Block.getStateId(p_175490_1_) & 0xFFFF)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockState func_175489_ck() {
/* 350 */     return Block.getStateById(this.dataWatcher.getWatchableObjectShort(16) & 0xFFFF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 358 */     if (func_180431_b(source))
/*     */     {
/* 360 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 364 */     if (source.getEntity() == null || !(source.getEntity() instanceof EntityEndermite)) {
/*     */       
/* 366 */       if (!this.worldObj.isRemote)
/*     */       {
/* 368 */         setScreaming(true);
/*     */       }
/*     */       
/* 371 */       if (source instanceof net.minecraft.util.EntityDamageSource && source.getEntity() instanceof EntityPlayer)
/*     */       {
/* 373 */         if (source.getEntity() instanceof EntityPlayerMP && ((EntityPlayerMP)source.getEntity()).theItemInWorldManager.isCreative()) {
/*     */           
/* 375 */           setScreaming(false);
/*     */         }
/*     */         else {
/*     */           
/* 379 */           this.isAggressive = true;
/*     */         } 
/*     */       }
/*     */       
/* 383 */       if (source instanceof net.minecraft.util.EntityDamageSourceIndirect) {
/*     */         
/* 385 */         this.isAggressive = false;
/*     */         
/* 387 */         for (int var4 = 0; var4 < 64; var4++) {
/*     */           
/* 389 */           if (teleportRandomly())
/*     */           {
/* 391 */             return true;
/*     */           }
/*     */         } 
/*     */         
/* 395 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 399 */     boolean var3 = super.attackEntityFrom(source, amount);
/*     */     
/* 401 */     if (source.isUnblockable() && this.rand.nextInt(10) != 0)
/*     */     {
/* 403 */       teleportRandomly();
/*     */     }
/*     */     
/* 406 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isScreaming() {
/* 412 */     return (this.dataWatcher.getWatchableObjectByte(18) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScreaming(boolean p_70819_1_) {
/* 417 */     this.dataWatcher.updateObject(18, Byte.valueOf((byte)(p_70819_1_ ? 1 : 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 422 */     carriableBlocks.add(Blocks.grass);
/* 423 */     carriableBlocks.add(Blocks.dirt);
/* 424 */     carriableBlocks.add(Blocks.sand);
/* 425 */     carriableBlocks.add(Blocks.gravel);
/* 426 */     carriableBlocks.add(Blocks.yellow_flower);
/* 427 */     carriableBlocks.add(Blocks.red_flower);
/* 428 */     carriableBlocks.add(Blocks.brown_mushroom);
/* 429 */     carriableBlocks.add(Blocks.red_mushroom);
/* 430 */     carriableBlocks.add(Blocks.tnt);
/* 431 */     carriableBlocks.add(Blocks.cactus);
/* 432 */     carriableBlocks.add(Blocks.clay);
/* 433 */     carriableBlocks.add(Blocks.pumpkin);
/* 434 */     carriableBlocks.add(Blocks.melon_block);
/* 435 */     carriableBlocks.add(Blocks.mycelium);
/*     */   }
/*     */   
/*     */   class AIFindPlayer
/*     */     extends EntityAINearestAttackableTarget {
/*     */     private EntityPlayer field_179448_g;
/*     */     private int field_179450_h;
/*     */     private int field_179451_i;
/* 443 */     private EntityEnderman field_179449_j = EntityEnderman.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002221";
/*     */     
/*     */     public AIFindPlayer() {
/* 448 */       super(EntityEnderman.this, EntityPlayer.class, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 453 */       double var1 = getTargetDistance();
/* 454 */       List<?> var3 = this.taskOwner.worldObj.func_175647_a(EntityPlayer.class, this.taskOwner.getEntityBoundingBox().expand(var1, 4.0D, var1), this.targetEntitySelector);
/* 455 */       Collections.sort(var3, (Comparator<?>)this.theNearestAttackableTargetSorter);
/*     */       
/* 457 */       if (var3.isEmpty())
/*     */       {
/* 459 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 463 */       this.field_179448_g = (EntityPlayer)var3.get(0);
/* 464 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void startExecuting() {
/* 470 */       this.field_179450_h = 5;
/* 471 */       this.field_179451_i = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void resetTask() {
/* 476 */       this.field_179448_g = null;
/* 477 */       this.field_179449_j.setScreaming(false);
/* 478 */       IAttributeInstance var1 = this.field_179449_j.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/* 479 */       var1.removeModifier(EntityEnderman.attackingSpeedBoostModifier);
/* 480 */       super.resetTask();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean continueExecuting() {
/* 485 */       if (this.field_179448_g != null) {
/*     */         
/* 487 */         if (!this.field_179449_j.shouldAttackPlayer(this.field_179448_g))
/*     */         {
/* 489 */           return false;
/*     */         }
/*     */ 
/*     */         
/* 493 */         this.field_179449_j.isAggressive = true;
/* 494 */         this.field_179449_j.faceEntity((Entity)this.field_179448_g, 10.0F, 10.0F);
/* 495 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 500 */       return super.continueExecuting();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 506 */       if (this.field_179448_g != null) {
/*     */         
/* 508 */         if (--this.field_179450_h <= 0)
/*     */         {
/* 510 */           this.targetEntity = (EntityLivingBase)this.field_179448_g;
/* 511 */           this.field_179448_g = null;
/* 512 */           super.startExecuting();
/* 513 */           this.field_179449_j.playSound("mob.endermen.stare", 1.0F, 1.0F);
/* 514 */           this.field_179449_j.setScreaming(true);
/* 515 */           IAttributeInstance var1 = this.field_179449_j.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/* 516 */           var1.applyModifier(EntityEnderman.attackingSpeedBoostModifier);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 521 */         if (this.targetEntity != null)
/*     */         {
/* 523 */           if (this.targetEntity instanceof EntityPlayer && this.field_179449_j.shouldAttackPlayer((EntityPlayer)this.targetEntity)) {
/*     */             
/* 525 */             if (this.targetEntity.getDistanceSqToEntity((Entity)this.field_179449_j) < 16.0D)
/*     */             {
/* 527 */               this.field_179449_j.teleportRandomly();
/*     */             }
/*     */             
/* 530 */             this.field_179451_i = 0;
/*     */           }
/* 532 */           else if (this.targetEntity.getDistanceSqToEntity((Entity)this.field_179449_j) > 256.0D && this.field_179451_i++ >= 30 && this.field_179449_j.teleportToEntity((Entity)this.targetEntity)) {
/*     */             
/* 534 */             this.field_179451_i = 0;
/*     */           } 
/*     */         }
/*     */         
/* 538 */         super.updateTask();
/*     */       } 
/*     */     } }
/*     */   
/*     */   class AIPlaceBlock extends EntityAIBase { private EntityEnderman field_179475_a;
/*     */     
/*     */     AIPlaceBlock() {
/* 545 */       this.field_179475_a = EntityEnderman.this;
/*     */     }
/*     */     private static final String __OBFID = "CL_00002222";
/*     */     
/*     */     public boolean shouldExecute() {
/* 550 */       return !this.field_179475_a.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") ? false : ((this.field_179475_a.func_175489_ck().getBlock().getMaterial() == Material.air) ? false : ((this.field_179475_a.getRNG().nextInt(2000) == 0)));
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 555 */       Random var1 = this.field_179475_a.getRNG();
/* 556 */       World var2 = this.field_179475_a.worldObj;
/* 557 */       int var3 = MathHelper.floor_double(this.field_179475_a.posX - 1.0D + var1.nextDouble() * 2.0D);
/* 558 */       int var4 = MathHelper.floor_double(this.field_179475_a.posY + var1.nextDouble() * 2.0D);
/* 559 */       int var5 = MathHelper.floor_double(this.field_179475_a.posZ - 1.0D + var1.nextDouble() * 2.0D);
/* 560 */       BlockPos var6 = new BlockPos(var3, var4, var5);
/* 561 */       Block var7 = var2.getBlockState(var6).getBlock();
/* 562 */       Block var8 = var2.getBlockState(var6.offsetDown()).getBlock();
/*     */       
/* 564 */       if (func_179474_a(var2, var6, this.field_179475_a.func_175489_ck().getBlock(), var7, var8)) {
/*     */         
/* 566 */         var2.setBlockState(var6, this.field_179475_a.func_175489_ck(), 3);
/* 567 */         this.field_179475_a.func_175490_a(Blocks.air.getDefaultState());
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_179474_a(World worldIn, BlockPos p_179474_2_, Block p_179474_3_, Block p_179474_4_, Block p_179474_5_) {
/* 573 */       return !p_179474_3_.canPlaceBlockAt(worldIn, p_179474_2_) ? false : ((p_179474_4_.getMaterial() != Material.air) ? false : ((p_179474_5_.getMaterial() == Material.air) ? false : p_179474_5_.isFullCube()));
/*     */     } }
/*     */   class AITakeBlock extends EntityAIBase { private EntityEnderman field_179473_a;
/*     */     private static final String __OBFID = "CL_00002220";
/*     */     
/*     */     AITakeBlock() {
/* 579 */       this.field_179473_a = EntityEnderman.this;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 584 */       return !this.field_179473_a.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing") ? false : ((this.field_179473_a.func_175489_ck().getBlock().getMaterial() != Material.air) ? false : ((this.field_179473_a.getRNG().nextInt(20) == 0)));
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 589 */       Random var1 = this.field_179473_a.getRNG();
/* 590 */       World var2 = this.field_179473_a.worldObj;
/* 591 */       int var3 = MathHelper.floor_double(this.field_179473_a.posX - 2.0D + var1.nextDouble() * 4.0D);
/* 592 */       int var4 = MathHelper.floor_double(this.field_179473_a.posY + var1.nextDouble() * 3.0D);
/* 593 */       int var5 = MathHelper.floor_double(this.field_179473_a.posZ - 2.0D + var1.nextDouble() * 4.0D);
/* 594 */       BlockPos var6 = new BlockPos(var3, var4, var5);
/* 595 */       IBlockState var7 = var2.getBlockState(var6);
/* 596 */       Block var8 = var7.getBlock();
/*     */       
/* 598 */       if (EntityEnderman.carriableBlocks.contains(var8)) {
/*     */         
/* 600 */         this.field_179473_a.func_175490_a(var7);
/* 601 */         var2.setBlockState(var6, Blocks.air.getDefaultState());
/*     */       } 
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityEnderman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */