/*      */ package net.minecraft.entity.passive;
/*      */ import com.google.common.base.Predicate;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAIFollowParent;
/*      */ import net.minecraft.entity.ai.EntityAILookIdle;
/*      */ import net.minecraft.entity.ai.EntityAIMate;
/*      */ import net.minecraft.entity.ai.EntityAIPanic;
/*      */ import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
/*      */ import net.minecraft.entity.ai.EntityAIWander;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.ai.attributes.IAttribute;
/*      */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*      */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.AnimalChest;
/*      */ import net.minecraft.inventory.IInvBasic;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.inventory.InventoryBasic;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.potion.Potion;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraft.world.DifficultyInstance;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ public class EntityHorse extends EntityAnimal implements IInvBasic {
/*   47 */   private static final Predicate horseBreedingSelector = new Predicate()
/*      */     {
/*      */       private static final String __OBFID = "CL_00001642";
/*      */       
/*      */       public boolean func_179873_a(Entity p_179873_1_) {
/*   52 */         return (p_179873_1_ instanceof EntityHorse && ((EntityHorse)p_179873_1_).func_110205_ce());
/*      */       }
/*      */       
/*      */       public boolean apply(Object p_apply_1_) {
/*   56 */         return func_179873_a((Entity)p_apply_1_);
/*      */       }
/*      */     };
/*   59 */   private static final IAttribute horseJumpStrength = (IAttribute)(new RangedAttribute(null, "horse.jumpStrength", 0.7D, 0.0D, 2.0D)).setDescription("Jump Strength").setShouldWatch(true);
/*   60 */   private static final String[] horseArmorTextures = new String[] { null, "textures/entity/horse/armor/horse_armor_iron.png", "textures/entity/horse/armor/horse_armor_gold.png", "textures/entity/horse/armor/horse_armor_diamond.png" };
/*   61 */   private static final String[] field_110273_bx = new String[] { "", "meo", "goo", "dio" };
/*   62 */   private static final int[] armorValues = new int[] { 0, 5, 7, 11 };
/*   63 */   private static final String[] horseTextures = new String[] { "textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png" };
/*   64 */   private static final String[] field_110269_bA = new String[] { "hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb" };
/*   65 */   private static final String[] horseMarkingTextures = new String[] { null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png" };
/*   66 */   private static final String[] field_110292_bC = new String[] { "", "wo_", "wmo", "wdo", "bdo" };
/*      */   
/*      */   private int eatingHaystackCounter;
/*      */   
/*      */   private int openMouthCounter;
/*      */   
/*      */   private int jumpRearingCounter;
/*      */   
/*      */   public int field_110278_bp;
/*      */   
/*      */   public int field_110279_bq;
/*      */   
/*      */   protected boolean horseJumping;
/*      */   private AnimalChest horseChest;
/*      */   private boolean hasReproduced;
/*      */   protected int temper;
/*      */   protected float jumpPower;
/*      */   private boolean field_110294_bI;
/*      */   private float headLean;
/*      */   private float prevHeadLean;
/*      */   private float rearingAmount;
/*      */   private float prevRearingAmount;
/*      */   private float mouthOpenness;
/*      */   private float prevMouthOpenness;
/*      */   private int gallopTime;
/*      */   private String field_110286_bQ;
/*   92 */   private String[] field_110280_bR = new String[3];
/*      */   
/*      */   private boolean field_175508_bO = false;
/*      */   private static final String __OBFID = "CL_00001641";
/*      */   
/*      */   public EntityHorse(World worldIn) {
/*   98 */     super(worldIn);
/*   99 */     setSize(1.4F, 1.6F);
/*  100 */     this.isImmuneToFire = false;
/*  101 */     setChested(false);
/*  102 */     ((PathNavigateGround)getNavigator()).func_179690_a(true);
/*  103 */     this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  104 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.2D));
/*  105 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIRunAroundLikeCrazy(this, 1.2D));
/*  106 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIMate(this, 1.0D));
/*  107 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIFollowParent(this, 1.0D));
/*  108 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.7D));
/*  109 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*  110 */     this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  111 */     func_110226_cD();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  116 */     super.entityInit();
/*  117 */     this.dataWatcher.addObject(16, Integer.valueOf(0));
/*  118 */     this.dataWatcher.addObject(19, Byte.valueOf((byte)0));
/*  119 */     this.dataWatcher.addObject(20, Integer.valueOf(0));
/*  120 */     this.dataWatcher.addObject(21, String.valueOf(""));
/*  121 */     this.dataWatcher.addObject(22, Integer.valueOf(0));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseType(int p_110214_1_) {
/*  126 */     this.dataWatcher.updateObject(19, Byte.valueOf((byte)p_110214_1_));
/*  127 */     func_110230_cF();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHorseType() {
/*  135 */     return this.dataWatcher.getWatchableObjectByte(19);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseVariant(int p_110235_1_) {
/*  140 */     this.dataWatcher.updateObject(20, Integer.valueOf(p_110235_1_));
/*  141 */     func_110230_cF();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getHorseVariant() {
/*  146 */     return this.dataWatcher.getWatchableObjectInt(20);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/*  154 */     if (hasCustomName())
/*      */     {
/*  156 */       return getCustomNameTag();
/*      */     }
/*      */ 
/*      */     
/*  160 */     int var1 = getHorseType();
/*      */     
/*  162 */     switch (var1) {
/*      */ 
/*      */       
/*      */       default:
/*  166 */         return StatCollector.translateToLocal("entity.horse.name");
/*      */       
/*      */       case 1:
/*  169 */         return StatCollector.translateToLocal("entity.donkey.name");
/*      */       
/*      */       case 2:
/*  172 */         return StatCollector.translateToLocal("entity.mule.name");
/*      */       
/*      */       case 3:
/*  175 */         return StatCollector.translateToLocal("entity.zombiehorse.name");
/*      */       case 4:
/*      */         break;
/*  178 */     }  return StatCollector.translateToLocal("entity.skeletonhorse.name");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean getHorseWatchableBoolean(int p_110233_1_) {
/*  185 */     return ((this.dataWatcher.getWatchableObjectInt(16) & p_110233_1_) != 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setHorseWatchableBoolean(int p_110208_1_, boolean p_110208_2_) {
/*  190 */     int var3 = this.dataWatcher.getWatchableObjectInt(16);
/*      */     
/*  192 */     if (p_110208_2_) {
/*      */       
/*  194 */       this.dataWatcher.updateObject(16, Integer.valueOf(var3 | p_110208_1_));
/*      */     }
/*      */     else {
/*      */       
/*  198 */       this.dataWatcher.updateObject(16, Integer.valueOf(var3 & (p_110208_1_ ^ 0xFFFFFFFF)));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAdultHorse() {
/*  204 */     return !isChild();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isTame() {
/*  209 */     return getHorseWatchableBoolean(2);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_110253_bW() {
/*  214 */     return isAdultHorse();
/*      */   }
/*      */ 
/*      */   
/*      */   public String func_152119_ch() {
/*  219 */     return this.dataWatcher.getWatchableObjectString(21);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152120_b(String p_152120_1_) {
/*  224 */     this.dataWatcher.updateObject(21, p_152120_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getHorseSize() {
/*  229 */     int var1 = getGrowingAge();
/*  230 */     return (var1 >= 0) ? 1.0F : (0.5F + (-24000 - var1) / -24000.0F * 0.5F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setScaleForAge(boolean p_98054_1_) {
/*  238 */     if (p_98054_1_) {
/*      */       
/*  240 */       setScale(getHorseSize());
/*      */     }
/*      */     else {
/*      */       
/*  244 */       setScale(1.0F);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHorseJumping() {
/*  250 */     return this.horseJumping;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseTamed(boolean p_110234_1_) {
/*  255 */     setHorseWatchableBoolean(2, p_110234_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseJumping(boolean p_110255_1_) {
/*  260 */     this.horseJumping = p_110255_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean allowLeashing() {
/*  265 */     return (!isUndead() && super.allowLeashing());
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_142017_o(float p_142017_1_) {
/*  270 */     if (p_142017_1_ > 6.0F && isEatingHaystack())
/*      */     {
/*  272 */       setEatingHaystack(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isChested() {
/*  278 */     return getHorseWatchableBoolean(8);
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_110241_cb() {
/*  283 */     return this.dataWatcher.getWatchableObjectInt(22);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getHorseArmorIndex(ItemStack p_110260_1_) {
/*  291 */     if (p_110260_1_ == null)
/*      */     {
/*  293 */       return 0;
/*      */     }
/*      */ 
/*      */     
/*  297 */     Item var2 = p_110260_1_.getItem();
/*  298 */     return (var2 == Items.iron_horse_armor) ? 1 : ((var2 == Items.golden_horse_armor) ? 2 : ((var2 == Items.diamond_horse_armor) ? 3 : 0));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEatingHaystack() {
/*  304 */     return getHorseWatchableBoolean(32);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isRearing() {
/*  309 */     return getHorseWatchableBoolean(64);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_110205_ce() {
/*  314 */     return getHorseWatchableBoolean(16);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getHasReproduced() {
/*  319 */     return this.hasReproduced;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHorseArmorStack(ItemStack p_146086_1_) {
/*  327 */     this.dataWatcher.updateObject(22, Integer.valueOf(getHorseArmorIndex(p_146086_1_)));
/*  328 */     func_110230_cF();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_110242_l(boolean p_110242_1_) {
/*  333 */     setHorseWatchableBoolean(16, p_110242_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setChested(boolean p_110207_1_) {
/*  338 */     setHorseWatchableBoolean(8, p_110207_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHasReproduced(boolean p_110221_1_) {
/*  343 */     this.hasReproduced = p_110221_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHorseSaddled(boolean p_110251_1_) {
/*  348 */     setHorseWatchableBoolean(4, p_110251_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTemper() {
/*  353 */     return this.temper;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTemper(int p_110238_1_) {
/*  358 */     this.temper = p_110238_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public int increaseTemper(int p_110198_1_) {
/*  363 */     int var2 = MathHelper.clamp_int(getTemper() + p_110198_1_, 0, getMaxTemper());
/*  364 */     setTemper(var2);
/*  365 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  373 */     Entity var3 = source.getEntity();
/*  374 */     return (this.riddenByEntity != null && this.riddenByEntity.equals(var3)) ? false : super.attackEntityFrom(source, amount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTotalArmorValue() {
/*  382 */     return armorValues[func_110241_cb()];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePushed() {
/*  390 */     return (this.riddenByEntity == null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean prepareChunkForSpawn() {
/*  395 */     int var1 = MathHelper.floor_double(this.posX);
/*  396 */     int var2 = MathHelper.floor_double(this.posZ);
/*  397 */     this.worldObj.getBiomeGenForCoords(new BlockPos(var1, 0, var2));
/*  398 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropChests() {
/*  403 */     if (!this.worldObj.isRemote && isChested()) {
/*      */       
/*  405 */       dropItem(Item.getItemFromBlock((Block)Blocks.chest), 1);
/*  406 */       setChested(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_110266_cB() {
/*  412 */     openHorseMouth();
/*      */     
/*  414 */     if (!isSlient())
/*      */     {
/*  416 */       this.worldObj.playSoundAtEntity((Entity)this, "eating", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void fall(float distance, float damageMultiplier) {
/*  422 */     if (distance > 1.0F)
/*      */     {
/*  424 */       playSound("mob.horse.land", 0.4F, 1.0F);
/*      */     }
/*      */     
/*  427 */     int var3 = MathHelper.ceiling_float_int((distance * 0.5F - 3.0F) * damageMultiplier);
/*      */     
/*  429 */     if (var3 > 0) {
/*      */       
/*  431 */       attackEntityFrom(DamageSource.fall, var3);
/*      */       
/*  433 */       if (this.riddenByEntity != null)
/*      */       {
/*  435 */         this.riddenByEntity.attackEntityFrom(DamageSource.fall, var3);
/*      */       }
/*      */       
/*  438 */       Block var4 = this.worldObj.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - this.prevRotationYaw, this.posZ)).getBlock();
/*      */       
/*  440 */       if (var4.getMaterial() != Material.air && !isSlient()) {
/*      */         
/*  442 */         Block.SoundType var5 = var4.stepSound;
/*  443 */         this.worldObj.playSoundAtEntity((Entity)this, var5.getStepSound(), var5.getVolume() * 0.5F, var5.getFrequency() * 0.75F);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private int func_110225_cC() {
/*  450 */     int var1 = getHorseType();
/*  451 */     return (isChested() && (var1 == 1 || var1 == 2)) ? 17 : 2;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_110226_cD() {
/*  456 */     AnimalChest var1 = this.horseChest;
/*  457 */     this.horseChest = new AnimalChest("HorseChest", func_110225_cC());
/*  458 */     this.horseChest.func_110133_a(getName());
/*      */     
/*  460 */     if (var1 != null) {
/*      */       
/*  462 */       var1.func_110132_b(this);
/*  463 */       int var2 = Math.min(var1.getSizeInventory(), this.horseChest.getSizeInventory());
/*      */       
/*  465 */       for (int var3 = 0; var3 < var2; var3++) {
/*      */         
/*  467 */         ItemStack var4 = var1.getStackInSlot(var3);
/*      */         
/*  469 */         if (var4 != null)
/*      */         {
/*  471 */           this.horseChest.setInventorySlotContents(var3, var4.copy());
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  476 */     this.horseChest.func_110134_a(this);
/*  477 */     func_110232_cE();
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_110232_cE() {
/*  482 */     if (!this.worldObj.isRemote) {
/*      */       
/*  484 */       setHorseSaddled((this.horseChest.getStackInSlot(0) != null));
/*      */       
/*  486 */       if (canWearArmor())
/*      */       {
/*  488 */         setHorseArmorStack(this.horseChest.getStackInSlot(1));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onInventoryChanged(InventoryBasic p_76316_1_) {
/*  498 */     int var2 = func_110241_cb();
/*  499 */     boolean var3 = isHorseSaddled();
/*  500 */     func_110232_cE();
/*      */     
/*  502 */     if (this.ticksExisted > 20) {
/*      */       
/*  504 */       if (var2 == 0 && var2 != func_110241_cb()) {
/*      */         
/*  506 */         playSound("mob.horse.armor", 0.5F, 1.0F);
/*      */       }
/*  508 */       else if (var2 != func_110241_cb()) {
/*      */         
/*  510 */         playSound("mob.horse.armor", 0.5F, 1.0F);
/*      */       } 
/*      */       
/*  513 */       if (!var3 && isHorseSaddled())
/*      */       {
/*  515 */         playSound("mob.horse.leather", 0.5F, 1.0F);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCanSpawnHere() {
/*  525 */     prepareChunkForSpawn();
/*  526 */     return super.getCanSpawnHere();
/*      */   }
/*      */ 
/*      */   
/*      */   protected EntityHorse getClosestHorse(Entity p_110250_1_, double p_110250_2_) {
/*  531 */     double var4 = Double.MAX_VALUE;
/*  532 */     Entity var6 = null;
/*  533 */     List var7 = this.worldObj.func_175674_a(p_110250_1_, p_110250_1_.getEntityBoundingBox().addCoord(p_110250_2_, p_110250_2_, p_110250_2_), horseBreedingSelector);
/*  534 */     Iterator<Entity> var8 = var7.iterator();
/*      */     
/*  536 */     while (var8.hasNext()) {
/*      */       
/*  538 */       Entity var9 = var8.next();
/*  539 */       double var10 = var9.getDistanceSq(p_110250_1_.posX, p_110250_1_.posY, p_110250_1_.posZ);
/*      */       
/*  541 */       if (var10 < var4) {
/*      */         
/*  543 */         var6 = var9;
/*  544 */         var4 = var10;
/*      */       } 
/*      */     } 
/*      */     
/*  548 */     return (EntityHorse)var6;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getHorseJumpStrength() {
/*  553 */     return getEntityAttribute(horseJumpStrength).getAttributeValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getDeathSound() {
/*  561 */     openHorseMouth();
/*  562 */     int var1 = getHorseType();
/*  563 */     return (var1 == 3) ? "mob.horse.zombie.death" : ((var1 == 4) ? "mob.horse.skeleton.death" : ((var1 != 1 && var1 != 2) ? "mob.horse.death" : "mob.horse.donkey.death"));
/*      */   }
/*      */ 
/*      */   
/*      */   protected Item getDropItem() {
/*  568 */     boolean var1 = (this.rand.nextInt(4) == 0);
/*  569 */     int var2 = getHorseType();
/*  570 */     return (var2 == 4) ? Items.bone : ((var2 == 3) ? (var1 ? null : Items.rotten_flesh) : Items.leather);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getHurtSound() {
/*  578 */     openHorseMouth();
/*      */     
/*  580 */     if (this.rand.nextInt(3) == 0)
/*      */     {
/*  582 */       makeHorseRear();
/*      */     }
/*      */     
/*  585 */     int var1 = getHorseType();
/*  586 */     return (var1 == 3) ? "mob.horse.zombie.hit" : ((var1 == 4) ? "mob.horse.skeleton.hit" : ((var1 != 1 && var1 != 2) ? "mob.horse.hit" : "mob.horse.donkey.hit"));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isHorseSaddled() {
/*  591 */     return getHorseWatchableBoolean(4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getLivingSound() {
/*  599 */     openHorseMouth();
/*      */     
/*  601 */     if (this.rand.nextInt(10) == 0 && !isMovementBlocked())
/*      */     {
/*  603 */       makeHorseRear();
/*      */     }
/*      */     
/*  606 */     int var1 = getHorseType();
/*  607 */     return (var1 == 3) ? "mob.horse.zombie.idle" : ((var1 == 4) ? "mob.horse.skeleton.idle" : ((var1 != 1 && var1 != 2) ? "mob.horse.idle" : "mob.horse.donkey.idle"));
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getAngrySoundName() {
/*  612 */     openHorseMouth();
/*  613 */     makeHorseRear();
/*  614 */     int var1 = getHorseType();
/*  615 */     return (var1 != 3 && var1 != 4) ? ((var1 != 1 && var1 != 2) ? "mob.horse.angry" : "mob.horse.donkey.angry") : null;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/*  620 */     Block.SoundType var3 = p_180429_2_.stepSound;
/*      */     
/*  622 */     if (this.worldObj.getBlockState(p_180429_1_.offsetUp()).getBlock() == Blocks.snow_layer)
/*      */     {
/*  624 */       var3 = Blocks.snow_layer.stepSound;
/*      */     }
/*      */     
/*  627 */     if (!p_180429_2_.getMaterial().isLiquid()) {
/*      */       
/*  629 */       int var4 = getHorseType();
/*      */       
/*  631 */       if (this.riddenByEntity != null && var4 != 1 && var4 != 2) {
/*      */         
/*  633 */         this.gallopTime++;
/*      */         
/*  635 */         if (this.gallopTime > 5 && this.gallopTime % 3 == 0)
/*      */         {
/*  637 */           playSound("mob.horse.gallop", var3.getVolume() * 0.15F, var3.getFrequency());
/*      */           
/*  639 */           if (var4 == 0 && this.rand.nextInt(10) == 0)
/*      */           {
/*  641 */             playSound("mob.horse.breathe", var3.getVolume() * 0.6F, var3.getFrequency());
/*      */           }
/*      */         }
/*  644 */         else if (this.gallopTime <= 5)
/*      */         {
/*  646 */           playSound("mob.horse.wood", var3.getVolume() * 0.15F, var3.getFrequency());
/*      */         }
/*      */       
/*  649 */       } else if (var3 == Block.soundTypeWood) {
/*      */         
/*  651 */         playSound("mob.horse.wood", var3.getVolume() * 0.15F, var3.getFrequency());
/*      */       }
/*      */       else {
/*      */         
/*  655 */         playSound("mob.horse.soft", var3.getVolume() * 0.15F, var3.getFrequency());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*  662 */     super.applyEntityAttributes();
/*  663 */     getAttributeMap().registerAttribute(horseJumpStrength);
/*  664 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(53.0D);
/*  665 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22499999403953552D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxSpawnedInChunk() {
/*  673 */     return 6;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxTemper() {
/*  678 */     return 100;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getSoundVolume() {
/*  686 */     return 0.8F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getTalkInterval() {
/*  694 */     return 400;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_110239_cn() {
/*  699 */     return !(getHorseType() != 0 && func_110241_cb() <= 0);
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_110230_cF() {
/*  704 */     this.field_110286_bQ = null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175507_cI() {
/*  709 */     return this.field_175508_bO;
/*      */   }
/*      */ 
/*      */   
/*      */   private void setHorseTexturePaths() {
/*  714 */     this.field_110286_bQ = "horse/";
/*  715 */     this.field_110280_bR[0] = null;
/*  716 */     this.field_110280_bR[1] = null;
/*  717 */     this.field_110280_bR[2] = null;
/*  718 */     int var1 = getHorseType();
/*  719 */     int var2 = getHorseVariant();
/*      */ 
/*      */     
/*  722 */     if (var1 == 0) {
/*      */       
/*  724 */       int i = var2 & 0xFF;
/*  725 */       int var4 = (var2 & 0xFF00) >> 8;
/*      */       
/*  727 */       if (i >= horseTextures.length) {
/*      */         
/*  729 */         this.field_175508_bO = false;
/*      */         
/*      */         return;
/*      */       } 
/*  733 */       this.field_110280_bR[0] = horseTextures[i];
/*  734 */       this.field_110286_bQ = String.valueOf(this.field_110286_bQ) + field_110269_bA[i];
/*      */       
/*  736 */       if (var4 >= horseMarkingTextures.length) {
/*      */         
/*  738 */         this.field_175508_bO = false;
/*      */         
/*      */         return;
/*      */       } 
/*  742 */       this.field_110280_bR[1] = horseMarkingTextures[var4];
/*  743 */       this.field_110286_bQ = String.valueOf(this.field_110286_bQ) + field_110292_bC[var4];
/*      */     }
/*      */     else {
/*      */       
/*  747 */       this.field_110280_bR[0] = "";
/*  748 */       this.field_110286_bQ = String.valueOf(this.field_110286_bQ) + "_" + var1 + "_";
/*      */     } 
/*      */     
/*  751 */     int var3 = func_110241_cb();
/*      */     
/*  753 */     if (var3 >= horseArmorTextures.length) {
/*      */       
/*  755 */       this.field_175508_bO = false;
/*      */     }
/*      */     else {
/*      */       
/*  759 */       this.field_110280_bR[2] = horseArmorTextures[var3];
/*  760 */       this.field_110286_bQ = String.valueOf(this.field_110286_bQ) + field_110273_bx[var3];
/*  761 */       this.field_175508_bO = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public String getHorseTexture() {
/*  767 */     if (this.field_110286_bQ == null)
/*      */     {
/*  769 */       setHorseTexturePaths();
/*      */     }
/*      */     
/*  772 */     return this.field_110286_bQ;
/*      */   }
/*      */ 
/*      */   
/*      */   public String[] getVariantTexturePaths() {
/*  777 */     if (this.field_110286_bQ == null)
/*      */     {
/*  779 */       setHorseTexturePaths();
/*      */     }
/*      */     
/*  782 */     return this.field_110280_bR;
/*      */   }
/*      */ 
/*      */   
/*      */   public void openGUI(EntityPlayer p_110199_1_) {
/*  787 */     if (!this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == p_110199_1_) && isTame()) {
/*      */       
/*  789 */       this.horseChest.func_110133_a(getName());
/*  790 */       p_110199_1_.displayGUIHorse(this, (IInventory)this.horseChest);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean interact(EntityPlayer p_70085_1_) {
/*  799 */     ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
/*      */     
/*  801 */     if (var2 != null && var2.getItem() == Items.spawn_egg)
/*      */     {
/*  803 */       return super.interact(p_70085_1_);
/*      */     }
/*  805 */     if (!isTame() && isUndead())
/*      */     {
/*  807 */       return false;
/*      */     }
/*  809 */     if (isTame() && isAdultHorse() && p_70085_1_.isSneaking()) {
/*      */       
/*  811 */       openGUI(p_70085_1_);
/*  812 */       return true;
/*      */     } 
/*  814 */     if (func_110253_bW() && this.riddenByEntity != null)
/*      */     {
/*  816 */       return super.interact(p_70085_1_);
/*      */     }
/*      */ 
/*      */     
/*  820 */     if (var2 != null) {
/*      */       
/*  822 */       boolean var3 = false;
/*      */       
/*  824 */       if (canWearArmor()) {
/*      */         
/*  826 */         byte var4 = -1;
/*      */         
/*  828 */         if (var2.getItem() == Items.iron_horse_armor) {
/*      */           
/*  830 */           var4 = 1;
/*      */         }
/*  832 */         else if (var2.getItem() == Items.golden_horse_armor) {
/*      */           
/*  834 */           var4 = 2;
/*      */         }
/*  836 */         else if (var2.getItem() == Items.diamond_horse_armor) {
/*      */           
/*  838 */           var4 = 3;
/*      */         } 
/*      */         
/*  841 */         if (var4 >= 0) {
/*      */           
/*  843 */           if (!isTame()) {
/*      */             
/*  845 */             makeHorseRearWithSound();
/*  846 */             return true;
/*      */           } 
/*      */           
/*  849 */           openGUI(p_70085_1_);
/*  850 */           return true;
/*      */         } 
/*      */       } 
/*      */       
/*  854 */       if (!var3 && !isUndead()) {
/*      */         
/*  856 */         float var7 = 0.0F;
/*  857 */         short var5 = 0;
/*  858 */         byte var6 = 0;
/*      */         
/*  860 */         if (var2.getItem() == Items.wheat) {
/*      */           
/*  862 */           var7 = 2.0F;
/*  863 */           var5 = 20;
/*  864 */           var6 = 3;
/*      */         }
/*  866 */         else if (var2.getItem() == Items.sugar) {
/*      */           
/*  868 */           var7 = 1.0F;
/*  869 */           var5 = 30;
/*  870 */           var6 = 3;
/*      */         }
/*  872 */         else if (Block.getBlockFromItem(var2.getItem()) == Blocks.hay_block) {
/*      */           
/*  874 */           var7 = 20.0F;
/*  875 */           var5 = 180;
/*      */         }
/*  877 */         else if (var2.getItem() == Items.apple) {
/*      */           
/*  879 */           var7 = 3.0F;
/*  880 */           var5 = 60;
/*  881 */           var6 = 3;
/*      */         }
/*  883 */         else if (var2.getItem() == Items.golden_carrot) {
/*      */           
/*  885 */           var7 = 4.0F;
/*  886 */           var5 = 60;
/*  887 */           var6 = 5;
/*      */           
/*  889 */           if (isTame() && getGrowingAge() == 0)
/*      */           {
/*  891 */             var3 = true;
/*  892 */             setInLove(p_70085_1_);
/*      */           }
/*      */         
/*  895 */         } else if (var2.getItem() == Items.golden_apple) {
/*      */           
/*  897 */           var7 = 10.0F;
/*  898 */           var5 = 240;
/*  899 */           var6 = 10;
/*      */           
/*  901 */           if (isTame() && getGrowingAge() == 0) {
/*      */             
/*  903 */             var3 = true;
/*  904 */             setInLove(p_70085_1_);
/*      */           } 
/*      */         } 
/*      */         
/*  908 */         if (getHealth() < getMaxHealth() && var7 > 0.0F) {
/*      */           
/*  910 */           heal(var7);
/*  911 */           var3 = true;
/*      */         } 
/*      */         
/*  914 */         if (!isAdultHorse() && var5 > 0) {
/*      */           
/*  916 */           addGrowth(var5);
/*  917 */           var3 = true;
/*      */         } 
/*      */         
/*  920 */         if (var6 > 0 && (var3 || !isTame()) && var6 < getMaxTemper()) {
/*      */           
/*  922 */           var3 = true;
/*  923 */           increaseTemper(var6);
/*      */         } 
/*      */         
/*  926 */         if (var3)
/*      */         {
/*  928 */           func_110266_cB();
/*      */         }
/*      */       } 
/*      */       
/*  932 */       if (!isTame() && !var3) {
/*      */         
/*  934 */         if (var2 != null && var2.interactWithEntity(p_70085_1_, (EntityLivingBase)this))
/*      */         {
/*  936 */           return true;
/*      */         }
/*      */         
/*  939 */         makeHorseRearWithSound();
/*  940 */         return true;
/*      */       } 
/*      */       
/*  943 */       if (!var3 && canCarryChest() && !isChested() && var2.getItem() == Item.getItemFromBlock((Block)Blocks.chest)) {
/*      */         
/*  945 */         setChested(true);
/*  946 */         playSound("mob.chickenplop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*  947 */         var3 = true;
/*  948 */         func_110226_cD();
/*      */       } 
/*      */       
/*  951 */       if (!var3 && func_110253_bW() && !isHorseSaddled() && var2.getItem() == Items.saddle) {
/*      */         
/*  953 */         openGUI(p_70085_1_);
/*  954 */         return true;
/*      */       } 
/*      */       
/*  957 */       if (var3) {
/*      */         
/*  959 */         if (!p_70085_1_.capabilities.isCreativeMode && --var2.stackSize == 0)
/*      */         {
/*  961 */           p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, null);
/*      */         }
/*      */         
/*  964 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/*  968 */     if (func_110253_bW() && this.riddenByEntity == null) {
/*      */       
/*  970 */       if (var2 != null && var2.interactWithEntity(p_70085_1_, (EntityLivingBase)this))
/*      */       {
/*  972 */         return true;
/*      */       }
/*      */ 
/*      */       
/*  976 */       func_110237_h(p_70085_1_);
/*  977 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  982 */     return super.interact(p_70085_1_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void func_110237_h(EntityPlayer p_110237_1_) {
/*  989 */     p_110237_1_.rotationYaw = this.rotationYaw;
/*  990 */     p_110237_1_.rotationPitch = this.rotationPitch;
/*  991 */     setEatingHaystack(false);
/*  992 */     setRearing(false);
/*      */     
/*  994 */     if (!this.worldObj.isRemote)
/*      */     {
/*  996 */       p_110237_1_.mountEntity((Entity)this);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canWearArmor() {
/* 1005 */     return (getHorseType() == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCarryChest() {
/* 1013 */     int var1 = getHorseType();
/* 1014 */     return !(var1 != 2 && var1 != 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean isMovementBlocked() {
/* 1022 */     return (this.riddenByEntity != null && isHorseSaddled()) ? true : (!(!isEatingHaystack() && !isRearing()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUndead() {
/* 1030 */     int var1 = getHorseType();
/* 1031 */     return !(var1 != 3 && var1 != 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSterile() {
/* 1039 */     return !(!isUndead() && getHorseType() != 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBreedingItem(ItemStack p_70877_1_) {
/* 1048 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_110210_cH() {
/* 1053 */     this.field_110278_bp = 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onDeath(DamageSource cause) {
/* 1061 */     super.onDeath(cause);
/*      */     
/* 1063 */     if (!this.worldObj.isRemote)
/*      */     {
/* 1065 */       dropChestItems();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/* 1075 */     if (this.rand.nextInt(200) == 0)
/*      */     {
/* 1077 */       func_110210_cH();
/*      */     }
/*      */     
/* 1080 */     super.onLivingUpdate();
/*      */     
/* 1082 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1084 */       if (this.rand.nextInt(900) == 0 && this.deathTime == 0)
/*      */       {
/* 1086 */         heal(1.0F);
/*      */       }
/*      */       
/* 1089 */       if (!isEatingHaystack() && this.riddenByEntity == null && this.rand.nextInt(300) == 0 && this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY) - 1, MathHelper.floor_double(this.posZ))).getBlock() == Blocks.grass)
/*      */       {
/* 1091 */         setEatingHaystack(true);
/*      */       }
/*      */       
/* 1094 */       if (isEatingHaystack() && ++this.eatingHaystackCounter > 50) {
/*      */         
/* 1096 */         this.eatingHaystackCounter = 0;
/* 1097 */         setEatingHaystack(false);
/*      */       } 
/*      */       
/* 1100 */       if (func_110205_ce() && !isAdultHorse() && !isEatingHaystack()) {
/*      */         
/* 1102 */         EntityHorse var1 = getClosestHorse((Entity)this, 16.0D);
/*      */         
/* 1104 */         if (var1 != null && getDistanceSqToEntity((Entity)var1) > 4.0D)
/*      */         {
/* 1106 */           this.navigator.getPathToEntityLiving((Entity)var1);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/* 1117 */     super.onUpdate();
/*      */     
/* 1119 */     if (this.worldObj.isRemote && this.dataWatcher.hasObjectChanged()) {
/*      */       
/* 1121 */       this.dataWatcher.func_111144_e();
/* 1122 */       func_110230_cF();
/*      */     } 
/*      */     
/* 1125 */     if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30) {
/*      */       
/* 1127 */       this.openMouthCounter = 0;
/* 1128 */       setHorseWatchableBoolean(128, false);
/*      */     } 
/*      */     
/* 1131 */     if (!this.worldObj.isRemote && this.jumpRearingCounter > 0 && ++this.jumpRearingCounter > 20) {
/*      */       
/* 1133 */       this.jumpRearingCounter = 0;
/* 1134 */       setRearing(false);
/*      */     } 
/*      */     
/* 1137 */     if (this.field_110278_bp > 0 && ++this.field_110278_bp > 8)
/*      */     {
/* 1139 */       this.field_110278_bp = 0;
/*      */     }
/*      */     
/* 1142 */     if (this.field_110279_bq > 0) {
/*      */       
/* 1144 */       this.field_110279_bq++;
/*      */       
/* 1146 */       if (this.field_110279_bq > 300)
/*      */       {
/* 1148 */         this.field_110279_bq = 0;
/*      */       }
/*      */     } 
/*      */     
/* 1152 */     this.prevHeadLean = this.headLean;
/*      */     
/* 1154 */     if (isEatingHaystack()) {
/*      */       
/* 1156 */       this.headLean += (1.0F - this.headLean) * 0.4F + 0.05F;
/*      */       
/* 1158 */       if (this.headLean > 1.0F)
/*      */       {
/* 1160 */         this.headLean = 1.0F;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1165 */       this.headLean += (0.0F - this.headLean) * 0.4F - 0.05F;
/*      */       
/* 1167 */       if (this.headLean < 0.0F)
/*      */       {
/* 1169 */         this.headLean = 0.0F;
/*      */       }
/*      */     } 
/*      */     
/* 1173 */     this.prevRearingAmount = this.rearingAmount;
/*      */     
/* 1175 */     if (isRearing()) {
/*      */       
/* 1177 */       this.prevHeadLean = this.headLean = 0.0F;
/* 1178 */       this.rearingAmount += (1.0F - this.rearingAmount) * 0.4F + 0.05F;
/*      */       
/* 1180 */       if (this.rearingAmount > 1.0F)
/*      */       {
/* 1182 */         this.rearingAmount = 1.0F;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1187 */       this.field_110294_bI = false;
/* 1188 */       this.rearingAmount += (0.8F * this.rearingAmount * this.rearingAmount * this.rearingAmount - this.rearingAmount) * 0.6F - 0.05F;
/*      */       
/* 1190 */       if (this.rearingAmount < 0.0F)
/*      */       {
/* 1192 */         this.rearingAmount = 0.0F;
/*      */       }
/*      */     } 
/*      */     
/* 1196 */     this.prevMouthOpenness = this.mouthOpenness;
/*      */     
/* 1198 */     if (getHorseWatchableBoolean(128)) {
/*      */       
/* 1200 */       this.mouthOpenness += (1.0F - this.mouthOpenness) * 0.7F + 0.05F;
/*      */       
/* 1202 */       if (this.mouthOpenness > 1.0F)
/*      */       {
/* 1204 */         this.mouthOpenness = 1.0F;
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1209 */       this.mouthOpenness += (0.0F - this.mouthOpenness) * 0.7F - 0.05F;
/*      */       
/* 1211 */       if (this.mouthOpenness < 0.0F)
/*      */       {
/* 1213 */         this.mouthOpenness = 0.0F;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void openHorseMouth() {
/* 1220 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1222 */       this.openMouthCounter = 1;
/* 1223 */       setHorseWatchableBoolean(128, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean canMate() {
/* 1232 */     return (this.riddenByEntity == null && this.ridingEntity == null && isTame() && isAdultHorse() && !isSterile() && getHealth() >= getMaxHealth() && isInLove());
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEating(boolean eating) {
/* 1237 */     setHorseWatchableBoolean(32, eating);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEatingHaystack(boolean p_110227_1_) {
/* 1242 */     setEating(p_110227_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRearing(boolean p_110219_1_) {
/* 1247 */     if (p_110219_1_)
/*      */     {
/* 1249 */       setEatingHaystack(false);
/*      */     }
/*      */     
/* 1252 */     setHorseWatchableBoolean(64, p_110219_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   private void makeHorseRear() {
/* 1257 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1259 */       this.jumpRearingCounter = 1;
/* 1260 */       setRearing(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void makeHorseRearWithSound() {
/* 1266 */     makeHorseRear();
/* 1267 */     String var1 = getAngrySoundName();
/*      */     
/* 1269 */     if (var1 != null)
/*      */     {
/* 1271 */       playSound(var1, getSoundVolume(), getSoundPitch());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void dropChestItems() {
/* 1277 */     dropItemsInChest((Entity)this, this.horseChest);
/* 1278 */     dropChests();
/*      */   }
/*      */ 
/*      */   
/*      */   private void dropItemsInChest(Entity p_110240_1_, AnimalChest p_110240_2_) {
/* 1283 */     if (p_110240_2_ != null && !this.worldObj.isRemote)
/*      */     {
/* 1285 */       for (int var3 = 0; var3 < p_110240_2_.getSizeInventory(); var3++) {
/*      */         
/* 1287 */         ItemStack var4 = p_110240_2_.getStackInSlot(var3);
/*      */         
/* 1289 */         if (var4 != null)
/*      */         {
/* 1291 */           entityDropItem(var4, 0.0F);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean setTamedBy(EntityPlayer p_110263_1_) {
/* 1299 */     func_152120_b(p_110263_1_.getUniqueID().toString());
/* 1300 */     setHorseTamed(true);
/* 1301 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
/* 1309 */     if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase && isHorseSaddled()) {
/*      */       
/* 1311 */       this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
/* 1312 */       this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
/* 1313 */       setRotation(this.rotationYaw, this.rotationPitch);
/* 1314 */       this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
/* 1315 */       p_70612_1_ = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
/* 1316 */       p_70612_2_ = ((EntityLivingBase)this.riddenByEntity).moveForward;
/*      */       
/* 1318 */       if (p_70612_2_ <= 0.0F) {
/*      */         
/* 1320 */         p_70612_2_ *= 0.25F;
/* 1321 */         this.gallopTime = 0;
/*      */       } 
/*      */       
/* 1324 */       if (this.onGround && this.jumpPower == 0.0F && isRearing() && !this.field_110294_bI) {
/*      */         
/* 1326 */         p_70612_1_ = 0.0F;
/* 1327 */         p_70612_2_ = 0.0F;
/*      */       } 
/*      */       
/* 1330 */       if (this.jumpPower > 0.0F && !isHorseJumping() && this.onGround) {
/*      */         
/* 1332 */         this.motionY = getHorseJumpStrength() * this.jumpPower;
/*      */         
/* 1334 */         if (isPotionActive(Potion.jump))
/*      */         {
/* 1336 */           this.motionY += ((getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);
/*      */         }
/*      */         
/* 1339 */         setHorseJumping(true);
/* 1340 */         this.isAirBorne = true;
/*      */         
/* 1342 */         if (p_70612_2_ > 0.0F) {
/*      */           
/* 1344 */           float var3 = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
/* 1345 */           float var4 = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);
/* 1346 */           this.motionX += (-0.4F * var3 * this.jumpPower);
/* 1347 */           this.motionZ += (0.4F * var4 * this.jumpPower);
/* 1348 */           playSound("mob.horse.jump", 0.4F, 1.0F);
/*      */         } 
/*      */         
/* 1351 */         this.jumpPower = 0.0F;
/*      */       } 
/*      */       
/* 1354 */       this.stepHeight = 1.0F;
/* 1355 */       this.jumpMovementFactor = getAIMoveSpeed() * 0.1F;
/*      */       
/* 1357 */       if (!this.worldObj.isRemote) {
/*      */         
/* 1359 */         setAIMoveSpeed((float)getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
/* 1360 */         super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
/*      */       } 
/*      */       
/* 1363 */       if (this.onGround) {
/*      */         
/* 1365 */         this.jumpPower = 0.0F;
/* 1366 */         setHorseJumping(false);
/*      */       } 
/*      */       
/* 1369 */       this.prevLimbSwingAmount = this.limbSwingAmount;
/* 1370 */       double var8 = this.posX - this.prevPosX;
/* 1371 */       double var5 = this.posZ - this.prevPosZ;
/* 1372 */       float var7 = MathHelper.sqrt_double(var8 * var8 + var5 * var5) * 4.0F;
/*      */       
/* 1374 */       if (var7 > 1.0F)
/*      */       {
/* 1376 */         var7 = 1.0F;
/*      */       }
/*      */       
/* 1379 */       this.limbSwingAmount += (var7 - this.limbSwingAmount) * 0.4F;
/* 1380 */       this.limbSwing += this.limbSwingAmount;
/*      */     }
/*      */     else {
/*      */       
/* 1384 */       this.stepHeight = 0.5F;
/* 1385 */       this.jumpMovementFactor = 0.02F;
/* 1386 */       super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 1395 */     super.writeEntityToNBT(tagCompound);
/* 1396 */     tagCompound.setBoolean("EatingHaystack", isEatingHaystack());
/* 1397 */     tagCompound.setBoolean("ChestedHorse", isChested());
/* 1398 */     tagCompound.setBoolean("HasReproduced", getHasReproduced());
/* 1399 */     tagCompound.setBoolean("Bred", func_110205_ce());
/* 1400 */     tagCompound.setInteger("Type", getHorseType());
/* 1401 */     tagCompound.setInteger("Variant", getHorseVariant());
/* 1402 */     tagCompound.setInteger("Temper", getTemper());
/* 1403 */     tagCompound.setBoolean("Tame", isTame());
/* 1404 */     tagCompound.setString("OwnerUUID", func_152119_ch());
/*      */     
/* 1406 */     if (isChested()) {
/*      */       
/* 1408 */       NBTTagList var2 = new NBTTagList();
/*      */       
/* 1410 */       for (int var3 = 2; var3 < this.horseChest.getSizeInventory(); var3++) {
/*      */         
/* 1412 */         ItemStack var4 = this.horseChest.getStackInSlot(var3);
/*      */         
/* 1414 */         if (var4 != null) {
/*      */           
/* 1416 */           NBTTagCompound var5 = new NBTTagCompound();
/* 1417 */           var5.setByte("Slot", (byte)var3);
/* 1418 */           var4.writeToNBT(var5);
/* 1419 */           var2.appendTag((NBTBase)var5);
/*      */         } 
/*      */       } 
/*      */       
/* 1423 */       tagCompound.setTag("Items", (NBTBase)var2);
/*      */     } 
/*      */     
/* 1426 */     if (this.horseChest.getStackInSlot(1) != null)
/*      */     {
/* 1428 */       tagCompound.setTag("ArmorItem", (NBTBase)this.horseChest.getStackInSlot(1).writeToNBT(new NBTTagCompound()));
/*      */     }
/*      */     
/* 1431 */     if (this.horseChest.getStackInSlot(0) != null)
/*      */     {
/* 1433 */       tagCompound.setTag("SaddleItem", (NBTBase)this.horseChest.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 1442 */     super.readEntityFromNBT(tagCompund);
/* 1443 */     setEatingHaystack(tagCompund.getBoolean("EatingHaystack"));
/* 1444 */     func_110242_l(tagCompund.getBoolean("Bred"));
/* 1445 */     setChested(tagCompund.getBoolean("ChestedHorse"));
/* 1446 */     setHasReproduced(tagCompund.getBoolean("HasReproduced"));
/* 1447 */     setHorseType(tagCompund.getInteger("Type"));
/* 1448 */     setHorseVariant(tagCompund.getInteger("Variant"));
/* 1449 */     setTemper(tagCompund.getInteger("Temper"));
/* 1450 */     setHorseTamed(tagCompund.getBoolean("Tame"));
/* 1451 */     String var2 = "";
/*      */     
/* 1453 */     if (tagCompund.hasKey("OwnerUUID", 8)) {
/*      */       
/* 1455 */       var2 = tagCompund.getString("OwnerUUID");
/*      */     }
/*      */     else {
/*      */       
/* 1459 */       String var3 = tagCompund.getString("Owner");
/* 1460 */       var2 = PreYggdrasilConverter.func_152719_a(var3);
/*      */     } 
/*      */     
/* 1463 */     if (var2.length() > 0)
/*      */     {
/* 1465 */       func_152120_b(var2);
/*      */     }
/*      */     
/* 1468 */     IAttributeInstance var8 = getAttributeMap().getAttributeInstanceByName("Speed");
/*      */     
/* 1470 */     if (var8 != null)
/*      */     {
/* 1472 */       getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(var8.getBaseValue() * 0.25D);
/*      */     }
/*      */     
/* 1475 */     if (isChested()) {
/*      */       
/* 1477 */       NBTTagList var4 = tagCompund.getTagList("Items", 10);
/* 1478 */       func_110226_cD();
/*      */       
/* 1480 */       for (int var5 = 0; var5 < var4.tagCount(); var5++) {
/*      */         
/* 1482 */         NBTTagCompound var6 = var4.getCompoundTagAt(var5);
/* 1483 */         int var7 = var6.getByte("Slot") & 0xFF;
/*      */         
/* 1485 */         if (var7 >= 2 && var7 < this.horseChest.getSizeInventory())
/*      */         {
/* 1487 */           this.horseChest.setInventorySlotContents(var7, ItemStack.loadItemStackFromNBT(var6));
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1494 */     if (tagCompund.hasKey("ArmorItem", 10)) {
/*      */       
/* 1496 */       ItemStack var9 = ItemStack.loadItemStackFromNBT(tagCompund.getCompoundTag("ArmorItem"));
/*      */       
/* 1498 */       if (var9 != null && func_146085_a(var9.getItem()))
/*      */       {
/* 1500 */         this.horseChest.setInventorySlotContents(1, var9);
/*      */       }
/*      */     } 
/*      */     
/* 1504 */     if (tagCompund.hasKey("SaddleItem", 10)) {
/*      */       
/* 1506 */       ItemStack var9 = ItemStack.loadItemStackFromNBT(tagCompund.getCompoundTag("SaddleItem"));
/*      */       
/* 1508 */       if (var9 != null && var9.getItem() == Items.saddle)
/*      */       {
/* 1510 */         this.horseChest.setInventorySlotContents(0, var9);
/*      */       }
/*      */     }
/* 1513 */     else if (tagCompund.getBoolean("Saddle")) {
/*      */       
/* 1515 */       this.horseChest.setInventorySlotContents(0, new ItemStack(Items.saddle));
/*      */     } 
/*      */     
/* 1518 */     func_110232_cE();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canMateWith(EntityAnimal p_70878_1_) {
/* 1526 */     if (p_70878_1_ == this)
/*      */     {
/* 1528 */       return false;
/*      */     }
/* 1530 */     if (p_70878_1_.getClass() != getClass())
/*      */     {
/* 1532 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1536 */     EntityHorse var2 = (EntityHorse)p_70878_1_;
/*      */     
/* 1538 */     if (canMate() && var2.canMate()) {
/*      */       
/* 1540 */       int var3 = getHorseType();
/* 1541 */       int var4 = var2.getHorseType();
/* 1542 */       return !(var3 != var4 && (var3 != 0 || var4 != 1) && (var3 != 1 || var4 != 0));
/*      */     } 
/*      */ 
/*      */     
/* 1546 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable createChild(EntityAgeable p_90011_1_) {
/* 1553 */     EntityHorse var2 = (EntityHorse)p_90011_1_;
/* 1554 */     EntityHorse var3 = new EntityHorse(this.worldObj);
/* 1555 */     int var4 = getHorseType();
/* 1556 */     int var5 = var2.getHorseType();
/* 1557 */     int var6 = 0;
/*      */     
/* 1559 */     if (var4 == var5) {
/*      */       
/* 1561 */       var6 = var4;
/*      */     }
/* 1563 */     else if ((var4 == 0 && var5 == 1) || (var4 == 1 && var5 == 0)) {
/*      */       
/* 1565 */       var6 = 2;
/*      */     } 
/*      */     
/* 1568 */     if (var6 == 0) {
/*      */       
/* 1570 */       int var7, var8 = this.rand.nextInt(9);
/*      */ 
/*      */       
/* 1573 */       if (var8 < 4) {
/*      */         
/* 1575 */         var7 = getHorseVariant() & 0xFF;
/*      */       }
/* 1577 */       else if (var8 < 8) {
/*      */         
/* 1579 */         var7 = var2.getHorseVariant() & 0xFF;
/*      */       }
/*      */       else {
/*      */         
/* 1583 */         var7 = this.rand.nextInt(7);
/*      */       } 
/*      */       
/* 1586 */       int var9 = this.rand.nextInt(5);
/*      */       
/* 1588 */       if (var9 < 2) {
/*      */         
/* 1590 */         var7 |= getHorseVariant() & 0xFF00;
/*      */       }
/* 1592 */       else if (var9 < 4) {
/*      */         
/* 1594 */         var7 |= var2.getHorseVariant() & 0xFF00;
/*      */       }
/*      */       else {
/*      */         
/* 1598 */         var7 |= this.rand.nextInt(5) << 8 & 0xFF00;
/*      */       } 
/*      */       
/* 1601 */       var3.setHorseVariant(var7);
/*      */     } 
/*      */     
/* 1604 */     var3.setHorseType(var6);
/* 1605 */     double var13 = getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() + p_90011_1_.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() + func_110267_cL();
/* 1606 */     var3.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(var13 / 3.0D);
/* 1607 */     double var14 = getEntityAttribute(horseJumpStrength).getBaseValue() + p_90011_1_.getEntityAttribute(horseJumpStrength).getBaseValue() + func_110245_cM();
/* 1608 */     var3.getEntityAttribute(horseJumpStrength).setBaseValue(var14 / 3.0D);
/* 1609 */     double var11 = getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() + p_90011_1_.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue() + func_110203_cN();
/* 1610 */     var3.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(var11 / 3.0D);
/* 1611 */     return var3;
/*      */   }
/*      */   
/*      */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/*      */     int var8;
/* 1616 */     Object p_180482_2_1 = super.func_180482_a(p_180482_1_, p_180482_2_);
/* 1617 */     boolean var3 = false;
/* 1618 */     int var4 = 0;
/*      */ 
/*      */     
/* 1621 */     if (p_180482_2_1 instanceof GroupData) {
/*      */       
/* 1623 */       var8 = ((GroupData)p_180482_2_1).field_111107_a;
/* 1624 */       var4 = ((GroupData)p_180482_2_1).field_111106_b & 0xFF | this.rand.nextInt(5) << 8;
/*      */     }
/*      */     else {
/*      */       
/* 1628 */       if (this.rand.nextInt(10) == 0) {
/*      */         
/* 1630 */         var8 = 1;
/*      */       }
/*      */       else {
/*      */         
/* 1634 */         int var5 = this.rand.nextInt(7);
/* 1635 */         int var6 = this.rand.nextInt(5);
/* 1636 */         var8 = 0;
/* 1637 */         var4 = var5 | var6 << 8;
/*      */       } 
/*      */       
/* 1640 */       p_180482_2_1 = new GroupData(var8, var4);
/*      */     } 
/*      */     
/* 1643 */     setHorseType(var8);
/* 1644 */     setHorseVariant(var4);
/*      */     
/* 1646 */     if (this.rand.nextInt(5) == 0)
/*      */     {
/* 1648 */       setGrowingAge(-24000);
/*      */     }
/*      */     
/* 1651 */     if (var8 != 4 && var8 != 3) {
/*      */       
/* 1653 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(func_110267_cL());
/*      */       
/* 1655 */       if (var8 == 0)
/*      */       {
/* 1657 */         getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(func_110203_cN());
/*      */       }
/*      */       else
/*      */       {
/* 1661 */         getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.17499999701976776D);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1666 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15.0D);
/* 1667 */       getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.20000000298023224D);
/*      */     } 
/*      */     
/* 1670 */     if (var8 != 2 && var8 != 1) {
/*      */       
/* 1672 */       getEntityAttribute(horseJumpStrength).setBaseValue(func_110245_cM());
/*      */     }
/*      */     else {
/*      */       
/* 1676 */       getEntityAttribute(horseJumpStrength).setBaseValue(0.5D);
/*      */     } 
/*      */     
/* 1679 */     setHealth(getMaxHealth());
/* 1680 */     return (IEntityLivingData)p_180482_2_1;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getGrassEatingAmount(float p_110258_1_) {
/* 1685 */     return this.prevHeadLean + (this.headLean - this.prevHeadLean) * p_110258_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getRearingAmount(float p_110223_1_) {
/* 1690 */     return this.prevRearingAmount + (this.rearingAmount - this.prevRearingAmount) * p_110223_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public float func_110201_q(float p_110201_1_) {
/* 1695 */     return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * p_110201_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setJumpPower(int p_110206_1_) {
/* 1700 */     if (isHorseSaddled()) {
/*      */       
/* 1702 */       if (p_110206_1_ < 0) {
/*      */         
/* 1704 */         p_110206_1_ = 0;
/*      */       }
/*      */       else {
/*      */         
/* 1708 */         this.field_110294_bI = true;
/* 1709 */         makeHorseRear();
/*      */       } 
/*      */       
/* 1712 */       if (p_110206_1_ >= 90) {
/*      */         
/* 1714 */         this.jumpPower = 1.0F;
/*      */       }
/*      */       else {
/*      */         
/* 1718 */         this.jumpPower = 0.4F + 0.4F * p_110206_1_ / 90.0F;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void spawnHorseParticles(boolean p_110216_1_) {
/* 1728 */     EnumParticleTypes var2 = p_110216_1_ ? EnumParticleTypes.HEART : EnumParticleTypes.SMOKE_NORMAL;
/*      */     
/* 1730 */     for (int var3 = 0; var3 < 7; var3++) {
/*      */       
/* 1732 */       double var4 = this.rand.nextGaussian() * 0.02D;
/* 1733 */       double var6 = this.rand.nextGaussian() * 0.02D;
/* 1734 */       double var8 = this.rand.nextGaussian() * 0.02D;
/* 1735 */       this.worldObj.spawnParticle(var2, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, var4, var6, var8, new int[0]);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(byte p_70103_1_) {
/* 1741 */     if (p_70103_1_ == 7) {
/*      */       
/* 1743 */       spawnHorseParticles(true);
/*      */     }
/* 1745 */     else if (p_70103_1_ == 6) {
/*      */       
/* 1747 */       spawnHorseParticles(false);
/*      */     }
/*      */     else {
/*      */       
/* 1751 */       super.handleHealthUpdate(p_70103_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateRiderPosition() {
/* 1757 */     super.updateRiderPosition();
/*      */     
/* 1759 */     if (this.prevRearingAmount > 0.0F) {
/*      */       
/* 1761 */       float var1 = MathHelper.sin(this.renderYawOffset * 3.1415927F / 180.0F);
/* 1762 */       float var2 = MathHelper.cos(this.renderYawOffset * 3.1415927F / 180.0F);
/* 1763 */       float var3 = 0.7F * this.prevRearingAmount;
/* 1764 */       float var4 = 0.15F * this.prevRearingAmount;
/* 1765 */       this.riddenByEntity.setPosition(this.posX + (var3 * var1), this.posY + getMountedYOffset() + this.riddenByEntity.getYOffset() + var4, this.posZ - (var3 * var2));
/*      */       
/* 1767 */       if (this.riddenByEntity instanceof EntityLivingBase)
/*      */       {
/* 1769 */         ((EntityLivingBase)this.riddenByEntity).renderYawOffset = this.renderYawOffset;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private float func_110267_cL() {
/* 1776 */     return 15.0F + this.rand.nextInt(8) + this.rand.nextInt(9);
/*      */   }
/*      */ 
/*      */   
/*      */   private double func_110245_cM() {
/* 1781 */     return 0.4000000059604645D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D;
/*      */   }
/*      */ 
/*      */   
/*      */   private double func_110203_cN() {
/* 1786 */     return (0.44999998807907104D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D) * 0.25D;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean func_146085_a(Item p_146085_0_) {
/* 1791 */     return !(p_146085_0_ != Items.iron_horse_armor && p_146085_0_ != Items.golden_horse_armor && p_146085_0_ != Items.diamond_horse_armor);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOnLadder() {
/* 1799 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getEyeHeight() {
/* 1804 */     return this.height;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
/* 1809 */     if (p_174820_1_ == 499 && canCarryChest()) {
/*      */       
/* 1811 */       if (p_174820_2_ == null && isChested()) {
/*      */         
/* 1813 */         setChested(false);
/* 1814 */         func_110226_cD();
/* 1815 */         return true;
/*      */       } 
/*      */       
/* 1818 */       if (p_174820_2_ != null && p_174820_2_.getItem() == Item.getItemFromBlock((Block)Blocks.chest) && !isChested()) {
/*      */         
/* 1820 */         setChested(true);
/* 1821 */         func_110226_cD();
/* 1822 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/* 1826 */     int var3 = p_174820_1_ - 400;
/*      */     
/* 1828 */     if (var3 >= 0 && var3 < 2 && var3 < this.horseChest.getSizeInventory()) {
/*      */       
/* 1830 */       if (var3 == 0 && p_174820_2_ != null && p_174820_2_.getItem() != Items.saddle)
/*      */       {
/* 1832 */         return false;
/*      */       }
/* 1834 */       if (var3 == 1 && ((p_174820_2_ != null && !func_146085_a(p_174820_2_.getItem())) || !canWearArmor()))
/*      */       {
/* 1836 */         return false;
/*      */       }
/*      */ 
/*      */       
/* 1840 */       this.horseChest.setInventorySlotContents(var3, p_174820_2_);
/* 1841 */       func_110232_cE();
/* 1842 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1847 */     int var4 = p_174820_1_ - 500 + 2;
/*      */     
/* 1849 */     if (var4 >= 2 && var4 < this.horseChest.getSizeInventory()) {
/*      */       
/* 1851 */       this.horseChest.setInventorySlotContents(var4, p_174820_2_);
/* 1852 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1856 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public static class GroupData
/*      */     implements IEntityLivingData
/*      */   {
/*      */     public int field_111107_a;
/*      */     
/*      */     public int field_111106_b;
/*      */     private static final String __OBFID = "CL_00001643";
/*      */     
/*      */     public GroupData(int p_i1684_1_, int p_i1684_2_) {
/* 1869 */       this.field_111107_a = p_i1684_1_;
/* 1870 */       this.field_111106_b = p_i1684_2_;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */