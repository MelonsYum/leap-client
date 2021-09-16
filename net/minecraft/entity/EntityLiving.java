/*      */ package net.minecraft.entity;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.UUID;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.ai.EntityAITasks;
/*      */ import net.minecraft.entity.ai.EntityJumpHelper;
/*      */ import net.minecraft.entity.ai.EntityLookHelper;
/*      */ import net.minecraft.entity.ai.EntityMoveHelper;
/*      */ import net.minecraft.entity.ai.EntitySenses;
/*      */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.monster.EntityGhast;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemArmor;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.item.ItemSword;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagFloat;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.play.server.S1BPacketEntityAttach;
/*      */ import net.minecraft.pathfinding.PathNavigate;
/*      */ import net.minecraft.pathfinding.PathNavigateGround;
/*      */ import net.minecraft.stats.AchievementList;
/*      */ import net.minecraft.stats.StatBase;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.world.DifficultyInstance;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ import net.minecraft.world.biome.BiomeGenBase;
/*      */ import optifine.BlockPosM;
/*      */ import optifine.Config;
/*      */ import optifine.Reflector;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class EntityLiving
/*      */   extends EntityLivingBase
/*      */ {
/*      */   public int livingSoundTime;
/*      */   protected int experienceValue;
/*      */   private EntityLookHelper lookHelper;
/*      */   protected EntityMoveHelper moveHelper;
/*      */   protected EntityJumpHelper jumpHelper;
/*      */   private EntityBodyHelper bodyHelper;
/*      */   protected PathNavigate navigator;
/*      */   protected final EntityAITasks tasks;
/*      */   protected final EntityAITasks targetTasks;
/*      */   private EntityLivingBase attackTarget;
/*      */   private EntitySenses senses;
/*   72 */   private ItemStack[] equipment = new ItemStack[5];
/*      */ 
/*      */   
/*   75 */   protected float[] equipmentDropChances = new float[5];
/*      */   
/*      */   private boolean canPickUpLoot;
/*      */   
/*      */   private boolean persistenceRequired;
/*      */   
/*      */   private boolean isLeashed;
/*      */   
/*      */   private Entity leashedToEntity;
/*      */   private NBTTagCompound leashNBTTag;
/*      */   private static final String __OBFID = "CL_00001550";
/*   86 */   public int randomMobsId = 0;
/*   87 */   public BiomeGenBase spawnBiome = null;
/*   88 */   public BlockPos spawnPosition = null;
/*      */ 
/*      */   
/*      */   public EntityLiving(World worldIn) {
/*   92 */     super(worldIn);
/*   93 */     this.tasks = new EntityAITasks((worldIn != null && worldIn.theProfiler != null) ? worldIn.theProfiler : null);
/*   94 */     this.targetTasks = new EntityAITasks((worldIn != null && worldIn.theProfiler != null) ? worldIn.theProfiler : null);
/*   95 */     this.lookHelper = new EntityLookHelper(this);
/*   96 */     this.moveHelper = new EntityMoveHelper(this);
/*   97 */     this.jumpHelper = new EntityJumpHelper(this);
/*   98 */     this.bodyHelper = new EntityBodyHelper(this);
/*   99 */     this.navigator = func_175447_b(worldIn);
/*  100 */     this.senses = new EntitySenses(this);
/*      */     
/*  102 */     for (int uuid = 0; uuid < this.equipmentDropChances.length; uuid++)
/*      */     {
/*  104 */       this.equipmentDropChances[uuid] = 0.085F;
/*      */     }
/*      */     
/*  107 */     UUID var5 = getUniqueID();
/*  108 */     long uuidLow = var5.getLeastSignificantBits();
/*  109 */     this.randomMobsId = (int)(uuidLow & 0x7FFFFFFFL);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*  114 */     super.applyEntityAttributes();
/*  115 */     getAttributeMap().registerAttribute(SharedMonsterAttributes.followRange).setBaseValue(16.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   protected PathNavigate func_175447_b(World worldIn) {
/*  120 */     return (PathNavigate)new PathNavigateGround(this, worldIn);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLookHelper getLookHelper() {
/*  125 */     return this.lookHelper;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityMoveHelper getMoveHelper() {
/*  130 */     return this.moveHelper;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityJumpHelper getJumpHelper() {
/*  135 */     return this.jumpHelper;
/*      */   }
/*      */ 
/*      */   
/*      */   public PathNavigate getNavigator() {
/*  140 */     return this.navigator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntitySenses getEntitySenses() {
/*  148 */     return this.senses;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityLivingBase getAttackTarget() {
/*  156 */     return this.attackTarget;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttackTarget(EntityLivingBase p_70624_1_) {
/*  164 */     this.attackTarget = p_70624_1_;
/*  165 */     Reflector.callVoid(Reflector.ForgeHooks_onLivingSetAttackTarget, new Object[] { this, p_70624_1_ });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canAttackClass(Class<EntityGhast> p_70686_1_) {
/*  173 */     return (p_70686_1_ != EntityGhast.class);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void eatGrassBonus() {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  184 */     super.entityInit();
/*  185 */     this.dataWatcher.addObject(15, Byte.valueOf((byte)0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTalkInterval() {
/*  193 */     return 80;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void playLivingSound() {
/*  201 */     String var1 = getLivingSound();
/*      */     
/*  203 */     if (var1 != null)
/*      */     {
/*  205 */       playSound(var1, getSoundVolume(), getSoundPitch());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityUpdate() {
/*  214 */     super.onEntityUpdate();
/*  215 */     this.worldObj.theProfiler.startSection("mobBaseTick");
/*      */     
/*  217 */     if (isEntityAlive() && this.rand.nextInt(1000) < this.livingSoundTime++) {
/*      */       
/*  219 */       this.livingSoundTime = -getTalkInterval();
/*  220 */       playLivingSound();
/*      */     } 
/*      */     
/*  223 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getExperiencePoints(EntityPlayer p_70693_1_) {
/*  231 */     if (this.experienceValue > 0) {
/*      */       
/*  233 */       int var2 = this.experienceValue;
/*  234 */       ItemStack[] var3 = getInventory();
/*      */       
/*  236 */       for (int var4 = 0; var4 < var3.length; var4++) {
/*      */         
/*  238 */         if (var3[var4] != null && this.equipmentDropChances[var4] <= 1.0F)
/*      */         {
/*  240 */           var2 += 1 + this.rand.nextInt(3);
/*      */         }
/*      */       } 
/*      */       
/*  244 */       return var2;
/*      */     } 
/*      */ 
/*      */     
/*  248 */     return this.experienceValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void spawnExplosionParticle() {
/*  257 */     if (this.worldObj.isRemote) {
/*      */       
/*  259 */       for (int var1 = 0; var1 < 20; var1++)
/*      */       {
/*  261 */         double var2 = this.rand.nextGaussian() * 0.02D;
/*  262 */         double var4 = this.rand.nextGaussian() * 0.02D;
/*  263 */         double var6 = this.rand.nextGaussian() * 0.02D;
/*  264 */         double var8 = 10.0D;
/*  265 */         this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width - var2 * var8, this.posY + (this.rand.nextFloat() * this.height) - var4 * var8, this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width - var6 * var8, var2, var4, var6, new int[0]);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  270 */       this.worldObj.setEntityState(this, (byte)20);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(byte p_70103_1_) {
/*  276 */     if (p_70103_1_ == 20) {
/*      */       
/*  278 */       spawnExplosionParticle();
/*      */     }
/*      */     else {
/*      */       
/*  282 */       super.handleHealthUpdate(p_70103_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*  291 */     if (Config.isSmoothWorld() && canSkipUpdate()) {
/*      */       
/*  293 */       onUpdateMinimal();
/*      */     }
/*      */     else {
/*      */       
/*  297 */       super.onUpdate();
/*      */       
/*  299 */       if (!this.worldObj.isRemote)
/*      */       {
/*  301 */         updateLeashedState();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected float func_110146_f(float p_110146_1_, float p_110146_2_) {
/*  308 */     this.bodyHelper.updateRenderAngles();
/*  309 */     return p_110146_2_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getLivingSound() {
/*  317 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected Item getDropItem() {
/*  322 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/*  330 */     Item var3 = getDropItem();
/*      */     
/*  332 */     if (var3 != null) {
/*      */       
/*  334 */       int var4 = this.rand.nextInt(3);
/*      */       
/*  336 */       if (p_70628_2_ > 0)
/*      */       {
/*  338 */         var4 += this.rand.nextInt(p_70628_2_ + 1);
/*      */       }
/*      */       
/*  341 */       for (int var5 = 0; var5 < var4; var5++)
/*      */       {
/*  343 */         dropItem(var3, 1);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  353 */     super.writeEntityToNBT(tagCompound);
/*  354 */     tagCompound.setBoolean("CanPickUpLoot", canPickUpLoot());
/*  355 */     tagCompound.setBoolean("PersistenceRequired", this.persistenceRequired);
/*  356 */     NBTTagList var2 = new NBTTagList();
/*      */ 
/*      */     
/*  359 */     for (int var6 = 0; var6 < this.equipment.length; var6++) {
/*      */       
/*  361 */       NBTTagCompound var4 = new NBTTagCompound();
/*      */       
/*  363 */       if (this.equipment[var6] != null)
/*      */       {
/*  365 */         this.equipment[var6].writeToNBT(var4);
/*      */       }
/*      */       
/*  368 */       var2.appendTag((NBTBase)var4);
/*      */     } 
/*      */     
/*  371 */     tagCompound.setTag("Equipment", (NBTBase)var2);
/*  372 */     NBTTagList var61 = new NBTTagList();
/*      */     
/*  374 */     for (int var5 = 0; var5 < this.equipmentDropChances.length; var5++)
/*      */     {
/*  376 */       var61.appendTag((NBTBase)new NBTTagFloat(this.equipmentDropChances[var5]));
/*      */     }
/*      */     
/*  379 */     tagCompound.setTag("DropChances", (NBTBase)var61);
/*  380 */     tagCompound.setBoolean("Leashed", this.isLeashed);
/*      */     
/*  382 */     if (this.leashedToEntity != null) {
/*      */       
/*  384 */       NBTTagCompound var4 = new NBTTagCompound();
/*      */       
/*  386 */       if (this.leashedToEntity instanceof EntityLivingBase) {
/*      */         
/*  388 */         var4.setLong("UUIDMost", this.leashedToEntity.getUniqueID().getMostSignificantBits());
/*  389 */         var4.setLong("UUIDLeast", this.leashedToEntity.getUniqueID().getLeastSignificantBits());
/*      */       }
/*  391 */       else if (this.leashedToEntity instanceof EntityHanging) {
/*      */         
/*  393 */         BlockPos var7 = ((EntityHanging)this.leashedToEntity).func_174857_n();
/*  394 */         var4.setInteger("X", var7.getX());
/*  395 */         var4.setInteger("Y", var7.getY());
/*  396 */         var4.setInteger("Z", var7.getZ());
/*      */       } 
/*      */       
/*  399 */       tagCompound.setTag("Leash", (NBTBase)var4);
/*      */     } 
/*      */     
/*  402 */     if (isAIDisabled())
/*      */     {
/*  404 */       tagCompound.setBoolean("NoAI", isAIDisabled());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  413 */     super.readEntityFromNBT(tagCompund);
/*      */     
/*  415 */     if (tagCompund.hasKey("CanPickUpLoot", 1))
/*      */     {
/*  417 */       setCanPickUpLoot(tagCompund.getBoolean("CanPickUpLoot"));
/*      */     }
/*      */     
/*  420 */     this.persistenceRequired = tagCompund.getBoolean("PersistenceRequired");
/*      */ 
/*      */ 
/*      */     
/*  424 */     if (tagCompund.hasKey("Equipment", 9)) {
/*      */       
/*  426 */       NBTTagList var2 = tagCompund.getTagList("Equipment", 10);
/*      */       
/*  428 */       for (int var3 = 0; var3 < this.equipment.length; var3++)
/*      */       {
/*  430 */         this.equipment[var3] = ItemStack.loadItemStackFromNBT(var2.getCompoundTagAt(var3));
/*      */       }
/*      */     } 
/*      */     
/*  434 */     if (tagCompund.hasKey("DropChances", 9)) {
/*      */       
/*  436 */       NBTTagList var2 = tagCompund.getTagList("DropChances", 5);
/*      */       
/*  438 */       for (int var3 = 0; var3 < var2.tagCount(); var3++)
/*      */       {
/*  440 */         this.equipmentDropChances[var3] = var2.getFloat(var3);
/*      */       }
/*      */     } 
/*      */     
/*  444 */     this.isLeashed = tagCompund.getBoolean("Leashed");
/*      */     
/*  446 */     if (this.isLeashed && tagCompund.hasKey("Leash", 10))
/*      */     {
/*  448 */       this.leashNBTTag = tagCompund.getCompoundTag("Leash");
/*      */     }
/*      */     
/*  451 */     setNoAI(tagCompund.getBoolean("NoAI"));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMoveForward(float p_70657_1_) {
/*  456 */     this.moveForward = p_70657_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAIMoveSpeed(float p_70659_1_) {
/*  464 */     super.setAIMoveSpeed(p_70659_1_);
/*  465 */     setMoveForward(p_70659_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/*  474 */     super.onLivingUpdate();
/*  475 */     this.worldObj.theProfiler.startSection("looting");
/*      */     
/*  477 */     if (!this.worldObj.isRemote && canPickUpLoot() && !this.dead && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*      */       
/*  479 */       List var1 = this.worldObj.getEntitiesWithinAABB(EntityItem.class, getEntityBoundingBox().expand(1.0D, 0.0D, 1.0D));
/*  480 */       Iterator<EntityItem> var2 = var1.iterator();
/*      */       
/*  482 */       while (var2.hasNext()) {
/*      */         
/*  484 */         EntityItem var3 = var2.next();
/*      */         
/*  486 */         if (!var3.isDead && var3.getEntityItem() != null && !var3.func_174874_s())
/*      */         {
/*  488 */           func_175445_a(var3);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  493 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_175445_a(EntityItem p_175445_1_) {
/*  498 */     ItemStack var2 = p_175445_1_.getEntityItem();
/*  499 */     int var3 = getArmorPosition(var2);
/*      */     
/*  501 */     if (var3 > -1) {
/*      */       
/*  503 */       boolean var4 = true;
/*  504 */       ItemStack var5 = getEquipmentInSlot(var3);
/*      */       
/*  506 */       if (var5 != null)
/*      */       {
/*  508 */         if (var3 == 0) {
/*      */           
/*  510 */           if (var2.getItem() instanceof ItemSword && !(var5.getItem() instanceof ItemSword)) {
/*      */             
/*  512 */             var4 = true;
/*      */           }
/*  514 */           else if (var2.getItem() instanceof ItemSword && var5.getItem() instanceof ItemSword) {
/*      */             
/*  516 */             ItemSword var9 = (ItemSword)var2.getItem();
/*  517 */             ItemSword var10 = (ItemSword)var5.getItem();
/*      */             
/*  519 */             if (var9.func_150931_i() == var10.func_150931_i())
/*      */             {
/*  521 */               var4 = !(var2.getMetadata() <= var5.getMetadata() && (!var2.hasTagCompound() || var5.hasTagCompound()));
/*      */             }
/*      */             else
/*      */             {
/*  525 */               var4 = (var9.func_150931_i() > var10.func_150931_i());
/*      */             }
/*      */           
/*  528 */           } else if (var2.getItem() instanceof net.minecraft.item.ItemBow && var5.getItem() instanceof net.minecraft.item.ItemBow) {
/*      */             
/*  530 */             var4 = (var2.hasTagCompound() && !var5.hasTagCompound());
/*      */           }
/*      */           else {
/*      */             
/*  534 */             var4 = false;
/*      */           }
/*      */         
/*  537 */         } else if (var2.getItem() instanceof ItemArmor && !(var5.getItem() instanceof ItemArmor)) {
/*      */           
/*  539 */           var4 = true;
/*      */         }
/*  541 */         else if (var2.getItem() instanceof ItemArmor && var5.getItem() instanceof ItemArmor) {
/*      */           
/*  543 */           ItemArmor var91 = (ItemArmor)var2.getItem();
/*  544 */           ItemArmor var101 = (ItemArmor)var5.getItem();
/*      */           
/*  546 */           if (var91.damageReduceAmount == var101.damageReduceAmount)
/*      */           {
/*  548 */             var4 = !(var2.getMetadata() <= var5.getMetadata() && (!var2.hasTagCompound() || var5.hasTagCompound()));
/*      */           }
/*      */           else
/*      */           {
/*  552 */             var4 = (var91.damageReduceAmount > var101.damageReduceAmount);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  557 */           var4 = false;
/*      */         } 
/*      */       }
/*      */       
/*  561 */       if (var4 && func_175448_a(var2)) {
/*      */         
/*  563 */         if (var5 != null && this.rand.nextFloat() - 0.1F < this.equipmentDropChances[var3])
/*      */         {
/*  565 */           entityDropItem(var5, 0.0F);
/*      */         }
/*      */         
/*  568 */         if (var2.getItem() == Items.diamond && p_175445_1_.getThrower() != null) {
/*      */           
/*  570 */           EntityPlayer var92 = this.worldObj.getPlayerEntityByName(p_175445_1_.getThrower());
/*      */           
/*  572 */           if (var92 != null)
/*      */           {
/*  574 */             var92.triggerAchievement((StatBase)AchievementList.diamondsToYou);
/*      */           }
/*      */         } 
/*      */         
/*  578 */         setCurrentItemOrArmor(var3, var2);
/*  579 */         this.equipmentDropChances[var3] = 2.0F;
/*  580 */         this.persistenceRequired = true;
/*  581 */         onItemPickup((Entity)p_175445_1_, 1);
/*  582 */         p_175445_1_.setDead();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean func_175448_a(ItemStack p_175448_1_) {
/*  589 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canDespawn() {
/*  597 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void despawnEntity() {
/*  605 */     Object result = null;
/*  606 */     Object Result_DEFAULT = Reflector.getFieldValue(Reflector.Event_Result_DEFAULT);
/*  607 */     Object Result_DENY = Reflector.getFieldValue(Reflector.Event_Result_DENY);
/*      */     
/*  609 */     if (this.persistenceRequired) {
/*      */       
/*  611 */       this.entityAge = 0;
/*      */     }
/*  613 */     else if ((this.entityAge & 0x1F) == 31 && (result = Reflector.call(Reflector.ForgeEventFactory_canEntityDespawn, new Object[] { this })) != Result_DEFAULT) {
/*      */       
/*  615 */       if (result == Result_DENY)
/*      */       {
/*  617 */         this.entityAge = 0;
/*      */       }
/*      */       else
/*      */       {
/*  621 */         setDead();
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  626 */       EntityPlayer var1 = this.worldObj.getClosestPlayerToEntity(this, -1.0D);
/*      */       
/*  628 */       if (var1 != null) {
/*      */         
/*  630 */         double var2 = var1.posX - this.posX;
/*  631 */         double var4 = var1.posY - this.posY;
/*  632 */         double var6 = var1.posZ - this.posZ;
/*  633 */         double var8 = var2 * var2 + var4 * var4 + var6 * var6;
/*      */         
/*  635 */         if (canDespawn() && var8 > 16384.0D)
/*      */         {
/*  637 */           setDead();
/*      */         }
/*      */         
/*  640 */         if (this.entityAge > 600 && this.rand.nextInt(800) == 0 && var8 > 1024.0D && canDespawn()) {
/*      */           
/*  642 */           setDead();
/*      */         }
/*  644 */         else if (var8 < 1024.0D) {
/*      */           
/*  646 */           this.entityAge = 0;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected final void updateEntityActionState() {
/*  654 */     this.entityAge++;
/*  655 */     this.worldObj.theProfiler.startSection("checkDespawn");
/*  656 */     despawnEntity();
/*  657 */     this.worldObj.theProfiler.endSection();
/*  658 */     this.worldObj.theProfiler.startSection("sensing");
/*  659 */     this.senses.clearSensingCache();
/*  660 */     this.worldObj.theProfiler.endSection();
/*  661 */     this.worldObj.theProfiler.startSection("targetSelector");
/*  662 */     this.targetTasks.onUpdateTasks();
/*  663 */     this.worldObj.theProfiler.endSection();
/*  664 */     this.worldObj.theProfiler.startSection("goalSelector");
/*  665 */     this.tasks.onUpdateTasks();
/*  666 */     this.worldObj.theProfiler.endSection();
/*  667 */     this.worldObj.theProfiler.startSection("navigation");
/*  668 */     this.navigator.onUpdateNavigation();
/*  669 */     this.worldObj.theProfiler.endSection();
/*  670 */     this.worldObj.theProfiler.startSection("mob tick");
/*  671 */     updateAITasks();
/*  672 */     this.worldObj.theProfiler.endSection();
/*  673 */     this.worldObj.theProfiler.startSection("controls");
/*  674 */     this.worldObj.theProfiler.startSection("move");
/*  675 */     this.moveHelper.onUpdateMoveHelper();
/*  676 */     this.worldObj.theProfiler.endStartSection("look");
/*  677 */     this.lookHelper.onUpdateLook();
/*  678 */     this.worldObj.theProfiler.endStartSection("jump");
/*  679 */     this.jumpHelper.doJump();
/*  680 */     this.worldObj.theProfiler.endSection();
/*  681 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateAITasks() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVerticalFaceSpeed() {
/*  692 */     return 40;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void faceEntity(Entity p_70625_1_, float p_70625_2_, float p_70625_3_) {
/*  700 */     double var6, var4 = p_70625_1_.posX - this.posX;
/*  701 */     double var8 = p_70625_1_.posZ - this.posZ;
/*      */ 
/*      */     
/*  704 */     if (p_70625_1_ instanceof EntityLivingBase) {
/*      */       
/*  706 */       EntityLivingBase var14 = (EntityLivingBase)p_70625_1_;
/*  707 */       var6 = var14.posY + var14.getEyeHeight() - this.posY + getEyeHeight();
/*      */     }
/*      */     else {
/*      */       
/*  711 */       var6 = ((p_70625_1_.getEntityBoundingBox()).minY + (p_70625_1_.getEntityBoundingBox()).maxY) / 2.0D - this.posY + getEyeHeight();
/*      */     } 
/*      */     
/*  714 */     double var141 = MathHelper.sqrt_double(var4 * var4 + var8 * var8);
/*  715 */     float var12 = (float)(Math.atan2(var8, var4) * 180.0D / Math.PI) - 90.0F;
/*  716 */     float var13 = (float)-(Math.atan2(var6, var141) * 180.0D / Math.PI);
/*  717 */     this.rotationPitch = updateRotation(this.rotationPitch, var13, p_70625_3_);
/*  718 */     this.rotationYaw = updateRotation(this.rotationYaw, var12, p_70625_2_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private float updateRotation(float p_70663_1_, float p_70663_2_, float p_70663_3_) {
/*  726 */     float var4 = MathHelper.wrapAngleTo180_float(p_70663_2_ - p_70663_1_);
/*      */     
/*  728 */     if (var4 > p_70663_3_)
/*      */     {
/*  730 */       var4 = p_70663_3_;
/*      */     }
/*      */     
/*  733 */     if (var4 < -p_70663_3_)
/*      */     {
/*  735 */       var4 = -p_70663_3_;
/*      */     }
/*      */     
/*  738 */     return p_70663_1_ + var4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCanSpawnHere() {
/*  746 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean handleLavaMovement() {
/*  754 */     return (this.worldObj.checkNoEntityCollision(getEntityBoundingBox(), this) && this.worldObj.getCollidingBoundingBoxes(this, getEntityBoundingBox()).isEmpty() && !this.worldObj.isAnyLiquid(getEntityBoundingBox()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getRenderSizeModifier() {
/*  762 */     return 1.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxSpawnedInChunk() {
/*  770 */     return 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxFallHeight() {
/*  778 */     if (getAttackTarget() == null)
/*      */     {
/*  780 */       return 3;
/*      */     }
/*      */ 
/*      */     
/*  784 */     int var1 = (int)(getHealth() - getMaxHealth() * 0.33F);
/*  785 */     var1 -= (3 - this.worldObj.getDifficulty().getDifficultyId()) * 4;
/*      */     
/*  787 */     if (var1 < 0)
/*      */     {
/*  789 */       var1 = 0;
/*      */     }
/*      */     
/*  792 */     return var1 + 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getHeldItem() {
/*  801 */     return this.equipment[0];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack getEquipmentInSlot(int slotIn) {
/*  809 */     return this.equipment[slotIn];
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack getCurrentArmor(int slotIn) {
/*  814 */     return this.equipment[slotIn + 1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurrentItemOrArmor(int slotIn, ItemStack stack) {
/*  822 */     this.equipment[slotIn] = stack;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack[] getInventory() {
/*  830 */     return this.equipment;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dropEquipment(boolean p_82160_1_, int p_82160_2_) {
/*  838 */     for (int var3 = 0; var3 < (getInventory()).length; var3++) {
/*      */       
/*  840 */       ItemStack var4 = getEquipmentInSlot(var3);
/*  841 */       boolean var5 = (this.equipmentDropChances[var3] > 1.0F);
/*      */       
/*  843 */       if (var4 != null && (p_82160_1_ || var5) && this.rand.nextFloat() - p_82160_2_ * 0.01F < this.equipmentDropChances[var3]) {
/*      */         
/*  845 */         if (!var5 && var4.isItemStackDamageable()) {
/*      */           
/*  847 */           int var6 = Math.max(var4.getMaxDamage() - 25, 1);
/*  848 */           int var7 = var4.getMaxDamage() - this.rand.nextInt(this.rand.nextInt(var6) + 1);
/*      */           
/*  850 */           if (var7 > var6)
/*      */           {
/*  852 */             var7 = var6;
/*      */           }
/*      */           
/*  855 */           if (var7 < 1)
/*      */           {
/*  857 */             var7 = 1;
/*      */           }
/*      */           
/*  860 */           var4.setItemDamage(var7);
/*      */         } 
/*      */         
/*  863 */         entityDropItem(var4, 0.0F);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_180481_a(DifficultyInstance p_180481_1_) {
/*  870 */     if (this.rand.nextFloat() < 0.15F * p_180481_1_.func_180170_c()) {
/*      */       
/*  872 */       int var2 = this.rand.nextInt(2);
/*  873 */       float var3 = (this.worldObj.getDifficulty() == EnumDifficulty.HARD) ? 0.1F : 0.25F;
/*      */       
/*  875 */       if (this.rand.nextFloat() < 0.095F)
/*      */       {
/*  877 */         var2++;
/*      */       }
/*      */       
/*  880 */       if (this.rand.nextFloat() < 0.095F)
/*      */       {
/*  882 */         var2++;
/*      */       }
/*      */       
/*  885 */       if (this.rand.nextFloat() < 0.095F)
/*      */       {
/*  887 */         var2++;
/*      */       }
/*      */       
/*  890 */       for (int var4 = 3; var4 >= 0; var4--) {
/*      */         
/*  892 */         ItemStack var5 = getCurrentArmor(var4);
/*      */         
/*  894 */         if (var4 < 3 && this.rand.nextFloat() < var3) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/*  899 */         if (var5 == null) {
/*      */           
/*  901 */           Item var6 = getArmorItemForSlot(var4 + 1, var2);
/*      */           
/*  903 */           if (var6 != null)
/*      */           {
/*  905 */             setCurrentItemOrArmor(var4 + 1, new ItemStack(var6));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static int getArmorPosition(ItemStack p_82159_0_) {
/*  914 */     if (p_82159_0_.getItem() != Item.getItemFromBlock(Blocks.pumpkin) && p_82159_0_.getItem() != Items.skull) {
/*      */       
/*  916 */       if (p_82159_0_.getItem() instanceof ItemArmor)
/*      */       {
/*  918 */         switch (((ItemArmor)p_82159_0_.getItem()).armorType) {
/*      */           
/*      */           case 0:
/*  921 */             return 4;
/*      */           
/*      */           case 1:
/*  924 */             return 3;
/*      */           
/*      */           case 2:
/*  927 */             return 2;
/*      */           
/*      */           case 3:
/*  930 */             return 1;
/*      */         } 
/*      */       
/*      */       }
/*  934 */       return 0;
/*      */     } 
/*      */ 
/*      */     
/*  938 */     return 4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Item getArmorItemForSlot(int armorSlot, int itemTier) {
/*  947 */     switch (armorSlot) {
/*      */       
/*      */       case 4:
/*  950 */         if (itemTier == 0)
/*      */         {
/*  952 */           return (Item)Items.leather_helmet;
/*      */         }
/*  954 */         if (itemTier == 1)
/*      */         {
/*  956 */           return (Item)Items.golden_helmet;
/*      */         }
/*  958 */         if (itemTier == 2)
/*      */         {
/*  960 */           return (Item)Items.chainmail_helmet;
/*      */         }
/*  962 */         if (itemTier == 3)
/*      */         {
/*  964 */           return (Item)Items.iron_helmet;
/*      */         }
/*  966 */         if (itemTier == 4)
/*      */         {
/*  968 */           return (Item)Items.diamond_helmet;
/*      */         }
/*      */       
/*      */       case 3:
/*  972 */         if (itemTier == 0)
/*      */         {
/*  974 */           return (Item)Items.leather_chestplate;
/*      */         }
/*  976 */         if (itemTier == 1)
/*      */         {
/*  978 */           return (Item)Items.golden_chestplate;
/*      */         }
/*  980 */         if (itemTier == 2)
/*      */         {
/*  982 */           return (Item)Items.chainmail_chestplate;
/*      */         }
/*  984 */         if (itemTier == 3)
/*      */         {
/*  986 */           return (Item)Items.iron_chestplate;
/*      */         }
/*  988 */         if (itemTier == 4)
/*      */         {
/*  990 */           return (Item)Items.diamond_chestplate;
/*      */         }
/*      */       
/*      */       case 2:
/*  994 */         if (itemTier == 0)
/*      */         {
/*  996 */           return (Item)Items.leather_leggings;
/*      */         }
/*  998 */         if (itemTier == 1)
/*      */         {
/* 1000 */           return (Item)Items.golden_leggings;
/*      */         }
/* 1002 */         if (itemTier == 2)
/*      */         {
/* 1004 */           return (Item)Items.chainmail_leggings;
/*      */         }
/* 1006 */         if (itemTier == 3)
/*      */         {
/* 1008 */           return (Item)Items.iron_leggings;
/*      */         }
/* 1010 */         if (itemTier == 4)
/*      */         {
/* 1012 */           return (Item)Items.diamond_leggings;
/*      */         }
/*      */       
/*      */       case 1:
/* 1016 */         if (itemTier == 0)
/*      */         {
/* 1018 */           return (Item)Items.leather_boots;
/*      */         }
/* 1020 */         if (itemTier == 1)
/*      */         {
/* 1022 */           return (Item)Items.golden_boots;
/*      */         }
/* 1024 */         if (itemTier == 2)
/*      */         {
/* 1026 */           return (Item)Items.chainmail_boots;
/*      */         }
/* 1028 */         if (itemTier == 3)
/*      */         {
/* 1030 */           return (Item)Items.iron_boots;
/*      */         }
/* 1032 */         if (itemTier == 4)
/*      */         {
/* 1034 */           return (Item)Items.diamond_boots;
/*      */         }
/*      */         break;
/*      */     } 
/* 1038 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_180483_b(DifficultyInstance p_180483_1_) {
/* 1044 */     float var2 = p_180483_1_.func_180170_c();
/*      */     
/* 1046 */     if (getHeldItem() != null && this.rand.nextFloat() < 0.25F * var2)
/*      */     {
/* 1048 */       EnchantmentHelper.addRandomEnchantment(this.rand, getHeldItem(), (int)(5.0F + var2 * this.rand.nextInt(18)));
/*      */     }
/*      */     
/* 1051 */     for (int var3 = 0; var3 < 4; var3++) {
/*      */       
/* 1053 */       ItemStack var4 = getCurrentArmor(var3);
/*      */       
/* 1055 */       if (var4 != null && this.rand.nextFloat() < 0.5F * var2)
/*      */       {
/* 1057 */         EnchantmentHelper.addRandomEnchantment(this.rand, var4, (int)(5.0F + var2 * this.rand.nextInt(18)));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 1064 */     getEntityAttribute(SharedMonsterAttributes.followRange).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextGaussian() * 0.05D, 1));
/* 1065 */     return p_180482_2_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeSteered() {
/* 1074 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void enablePersistence() {
/* 1082 */     this.persistenceRequired = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEquipmentDropChance(int p_96120_1_, float p_96120_2_) {
/* 1087 */     this.equipmentDropChances[p_96120_1_] = p_96120_2_;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canPickUpLoot() {
/* 1092 */     return this.canPickUpLoot;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCanPickUpLoot(boolean p_98053_1_) {
/* 1097 */     this.canPickUpLoot = p_98053_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isNoDespawnRequired() {
/* 1102 */     return this.persistenceRequired;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean interactFirst(EntityPlayer playerIn) {
/* 1110 */     if (getLeashed() && getLeashedToEntity() == playerIn) {
/*      */       
/* 1112 */       clearLeashed(true, !playerIn.capabilities.isCreativeMode);
/* 1113 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1117 */     ItemStack var2 = playerIn.inventory.getCurrentItem();
/*      */     
/* 1119 */     if (var2 != null && var2.getItem() == Items.lead && allowLeashing()) {
/*      */       
/* 1121 */       if (!(this instanceof EntityTameable) || !((EntityTameable)this).isTamed()) {
/*      */         
/* 1123 */         setLeashedToEntity((Entity)playerIn, true);
/* 1124 */         var2.stackSize--;
/* 1125 */         return true;
/*      */       } 
/*      */       
/* 1128 */       if (((EntityTameable)this).func_152114_e((EntityLivingBase)playerIn)) {
/*      */         
/* 1130 */         setLeashedToEntity((Entity)playerIn, true);
/* 1131 */         var2.stackSize--;
/* 1132 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 1136 */     return interact(playerIn) ? true : super.interactFirst(playerIn);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean interact(EntityPlayer player) {
/* 1145 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateLeashedState() {
/* 1153 */     if (this.leashNBTTag != null)
/*      */     {
/* 1155 */       recreateLeash();
/*      */     }
/*      */     
/* 1158 */     if (this.isLeashed) {
/*      */       
/* 1160 */       if (!isEntityAlive())
/*      */       {
/* 1162 */         clearLeashed(true, true);
/*      */       }
/*      */       
/* 1165 */       if (this.leashedToEntity == null || this.leashedToEntity.isDead)
/*      */       {
/* 1167 */         clearLeashed(true, true);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clearLeashed(boolean p_110160_1_, boolean p_110160_2_) {
/* 1177 */     if (this.isLeashed) {
/*      */       
/* 1179 */       this.isLeashed = false;
/* 1180 */       this.leashedToEntity = null;
/*      */       
/* 1182 */       if (!this.worldObj.isRemote && p_110160_2_)
/*      */       {
/* 1184 */         dropItem(Items.lead, 1);
/*      */       }
/*      */       
/* 1187 */       if (!this.worldObj.isRemote && p_110160_1_ && this.worldObj instanceof WorldServer)
/*      */       {
/* 1189 */         ((WorldServer)this.worldObj).getEntityTracker().sendToAllTrackingEntity(this, (Packet)new S1BPacketEntityAttach(1, this, null));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean allowLeashing() {
/* 1196 */     return (!getLeashed() && !(this instanceof net.minecraft.entity.monster.IMob));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getLeashed() {
/* 1201 */     return this.isLeashed;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getLeashedToEntity() {
/* 1206 */     return this.leashedToEntity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLeashedToEntity(Entity entityIn, boolean sendAttachNotification) {
/* 1214 */     this.isLeashed = true;
/* 1215 */     this.leashedToEntity = entityIn;
/*      */     
/* 1217 */     if (!this.worldObj.isRemote && sendAttachNotification && this.worldObj instanceof WorldServer)
/*      */     {
/* 1219 */       ((WorldServer)this.worldObj).getEntityTracker().sendToAllTrackingEntity(this, (Packet)new S1BPacketEntityAttach(1, this, this.leashedToEntity));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void recreateLeash() {
/* 1225 */     if (this.isLeashed && this.leashNBTTag != null)
/*      */     {
/* 1227 */       if (this.leashNBTTag.hasKey("UUIDMost", 4) && this.leashNBTTag.hasKey("UUIDLeast", 4)) {
/*      */         
/* 1229 */         UUID var11 = new UUID(this.leashNBTTag.getLong("UUIDMost"), this.leashNBTTag.getLong("UUIDLeast"));
/* 1230 */         List var21 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().expand(10.0D, 10.0D, 10.0D));
/* 1231 */         Iterator<EntityLivingBase> var3 = var21.iterator();
/*      */         
/* 1233 */         while (var3.hasNext()) {
/*      */           
/* 1235 */           EntityLivingBase var4 = var3.next();
/*      */           
/* 1237 */           if (var4.getUniqueID().equals(var11)) {
/*      */             
/* 1239 */             this.leashedToEntity = var4;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 1244 */       } else if (this.leashNBTTag.hasKey("X", 99) && this.leashNBTTag.hasKey("Y", 99) && this.leashNBTTag.hasKey("Z", 99)) {
/*      */         
/* 1246 */         BlockPos var1 = new BlockPos(this.leashNBTTag.getInteger("X"), this.leashNBTTag.getInteger("Y"), this.leashNBTTag.getInteger("Z"));
/* 1247 */         EntityLeashKnot var2 = EntityLeashKnot.func_174863_b(this.worldObj, var1);
/*      */         
/* 1249 */         if (var2 == null)
/*      */         {
/* 1251 */           var2 = EntityLeashKnot.func_174862_a(this.worldObj, var1);
/*      */         }
/*      */         
/* 1254 */         this.leashedToEntity = var2;
/*      */       }
/*      */       else {
/*      */         
/* 1258 */         clearLeashed(false, true);
/*      */       } 
/*      */     }
/*      */     
/* 1262 */     this.leashNBTTag = null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
/*      */     int var3;
/* 1269 */     if (p_174820_1_ == 99) {
/*      */       
/* 1271 */       var3 = 0;
/*      */     }
/*      */     else {
/*      */       
/* 1275 */       var3 = p_174820_1_ - 100 + 1;
/*      */       
/* 1277 */       if (var3 < 0 || var3 >= this.equipment.length)
/*      */       {
/* 1279 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 1283 */     if (p_174820_2_ != null && getArmorPosition(p_174820_2_) != var3 && (var3 != 4 || !(p_174820_2_.getItem() instanceof net.minecraft.item.ItemBlock)))
/*      */     {
/* 1285 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1289 */     setCurrentItemOrArmor(var3, p_174820_2_);
/* 1290 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isServerWorld() {
/* 1299 */     return (super.isServerWorld() && !isAIDisabled());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setNoAI(boolean p_94061_1_) {
/* 1307 */     this.dataWatcher.updateObject(15, Byte.valueOf((byte)(p_94061_1_ ? 1 : 0)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isAIDisabled() {
/* 1315 */     return (this.dataWatcher.getWatchableObjectByte(15) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityInsideOpaqueBlock() {
/* 1323 */     if (this.noClip)
/*      */     {
/* 1325 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1329 */     BlockPosM posM = new BlockPosM(0, 0, 0);
/*      */     
/* 1331 */     for (int var1 = 0; var1 < 8; var1++) {
/*      */       
/* 1333 */       double var2 = this.posX + ((((var1 >> 0) % 2) - 0.5F) * this.width * 0.8F);
/* 1334 */       double var4 = this.posY + ((((var1 >> 1) % 2) - 0.5F) * 0.1F);
/* 1335 */       double var6 = this.posZ + ((((var1 >> 2) % 2) - 0.5F) * this.width * 0.8F);
/* 1336 */       posM.setXyz(var2, var4 + getEyeHeight(), var6);
/*      */       
/* 1338 */       if (this.worldObj.getBlockState((BlockPos)posM).getBlock().isVisuallyOpaque())
/*      */       {
/* 1340 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1344 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean canSkipUpdate() {
/* 1350 */     if (isChild())
/*      */     {
/* 1352 */       return false;
/*      */     }
/* 1354 */     if (this.hurtTime > 0)
/*      */     {
/* 1356 */       return false;
/*      */     }
/* 1358 */     if (this.ticksExisted < 20)
/*      */     {
/* 1360 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1364 */     World world = getEntityWorld();
/*      */     
/* 1366 */     if (world == null)
/*      */     {
/* 1368 */       return false;
/*      */     }
/* 1370 */     if (world.playerEntities.size() != 1)
/*      */     {
/* 1372 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1376 */     Entity player = world.playerEntities.get(0);
/* 1377 */     double dx = Math.max(Math.abs(this.posX - player.posX) - 16.0D, 0.0D);
/* 1378 */     double dz = Math.max(Math.abs(this.posZ - player.posZ) - 16.0D, 0.0D);
/* 1379 */     double distSq = dx * dx + dz * dz;
/* 1380 */     return !isInRangeToRenderDist(distSq);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void onUpdateMinimal() {
/* 1387 */     this.entityAge++;
/*      */     
/* 1389 */     if (this instanceof net.minecraft.entity.monster.EntityMob) {
/*      */       
/* 1391 */       float brightness = getBrightness(1.0F);
/*      */       
/* 1393 */       if (brightness > 0.5F)
/*      */       {
/* 1395 */         this.entityAge += 2;
/*      */       }
/*      */     } 
/*      */     
/* 1399 */     despawnEntity();
/*      */   }
/*      */   
/*      */   public enum SpawnPlacementType
/*      */   {
/* 1404 */     ON_GROUND("ON_GROUND", 0, "ON_GROUND", 0),
/* 1405 */     IN_AIR("IN_AIR", 1, "IN_AIR", 1),
/* 1406 */     IN_WATER("IN_WATER", 2, "IN_WATER", 2);
/* 1407 */     private static final SpawnPlacementType[] $VALUES = new SpawnPlacementType[] { ON_GROUND, IN_AIR, IN_WATER };
/*      */     private static final String __OBFID = "CL_00002255";
/*      */     
/*      */     static {
/*      */     
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\EntityLiving.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */