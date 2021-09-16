/*     */ package net.minecraft.entity.item;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.StatCollector;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class EntityItem extends Entity {
/*  23 */   private static final Logger logger = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private int age;
/*     */   
/*     */   private int delayBeforeCanPickup;
/*     */   
/*     */   private int health;
/*     */   
/*     */   private String thrower;
/*     */   
/*     */   private String owner;
/*     */   
/*     */   public float hoverStart;
/*     */   
/*     */   private static final String __OBFID = "CL_00001669";
/*     */ 
/*     */   
/*     */   public EntityItem(World worldIn, double x, double y, double z) {
/*  42 */     super(worldIn);
/*  43 */     this.health = 5;
/*  44 */     this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
/*  45 */     setSize(0.25F, 0.25F);
/*  46 */     setPosition(x, y, z);
/*  47 */     this.rotationYaw = (float)(Math.random() * 360.0D);
/*  48 */     this.motionX = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
/*  49 */     this.motionY = 0.20000000298023224D;
/*  50 */     this.motionZ = (float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityItem(World worldIn, double x, double y, double z, ItemStack stack) {
/*  55 */     this(worldIn, x, y, z);
/*  56 */     setEntityItemStack(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityItem(World worldIn) {
/*  70 */     super(worldIn);
/*  71 */     this.health = 5;
/*  72 */     this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
/*  73 */     setSize(0.25F, 0.25F);
/*  74 */     setEntityItemStack(new ItemStack(Blocks.air, 0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  79 */     getDataWatcher().addObjectByDataType(10, 5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  87 */     if (getEntityItem() == null) {
/*     */       
/*  89 */       setDead();
/*     */     }
/*     */     else {
/*     */       
/*  93 */       super.onUpdate();
/*     */       
/*  95 */       if (this.delayBeforeCanPickup > 0 && this.delayBeforeCanPickup != 32767)
/*     */       {
/*  97 */         this.delayBeforeCanPickup--;
/*     */       }
/*     */       
/* 100 */       this.prevPosX = this.posX;
/* 101 */       this.prevPosY = this.posY;
/* 102 */       this.prevPosZ = this.posZ;
/* 103 */       this.motionY -= 0.03999999910593033D;
/* 104 */       this.noClip = pushOutOfBlocks(this.posX, ((getEntityBoundingBox()).minY + (getEntityBoundingBox()).maxY) / 2.0D, this.posZ);
/* 105 */       moveEntity(this.motionX, this.motionY, this.motionZ);
/* 106 */       boolean var1 = !((int)this.prevPosX == (int)this.posX && (int)this.prevPosY == (int)this.posY && (int)this.prevPosZ == (int)this.posZ);
/*     */       
/* 108 */       if (var1 || this.ticksExisted % 25 == 0) {
/*     */         
/* 110 */         if (this.worldObj.getBlockState(new BlockPos(this)).getBlock().getMaterial() == Material.lava) {
/*     */           
/* 112 */           this.motionY = 0.20000000298023224D;
/* 113 */           this.motionX = ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/* 114 */           this.motionZ = ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
/* 115 */           playSound("random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
/*     */         } 
/*     */         
/* 118 */         if (!this.worldObj.isRemote)
/*     */         {
/* 120 */           searchForOtherItemsNearby();
/*     */         }
/*     */       } 
/*     */       
/* 124 */       float var2 = 0.98F;
/*     */       
/* 126 */       if (this.onGround)
/*     */       {
/* 128 */         var2 = (this.worldObj.getBlockState(new BlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double((getEntityBoundingBox()).minY) - 1, MathHelper.floor_double(this.posZ))).getBlock()).slipperiness * 0.98F;
/*     */       }
/*     */       
/* 131 */       this.motionX *= var2;
/* 132 */       this.motionY *= 0.9800000190734863D;
/* 133 */       this.motionZ *= var2;
/*     */       
/* 135 */       if (this.onGround)
/*     */       {
/* 137 */         this.motionY *= -0.5D;
/*     */       }
/*     */       
/* 140 */       if (this.age != -32768)
/*     */       {
/* 142 */         this.age++;
/*     */       }
/*     */       
/* 145 */       handleWaterMovement();
/*     */       
/* 147 */       if (!this.worldObj.isRemote && this.age >= 6000)
/*     */       {
/* 149 */         setDead();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void searchForOtherItemsNearby() {
/* 159 */     Iterator<EntityItem> var1 = this.worldObj.getEntitiesWithinAABB(EntityItem.class, getEntityBoundingBox().expand(0.5D, 0.0D, 0.5D)).iterator();
/*     */     
/* 161 */     while (var1.hasNext()) {
/*     */       
/* 163 */       EntityItem var2 = var1.next();
/* 164 */       combineItems(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean combineItems(EntityItem other) {
/* 174 */     if (other == this)
/*     */     {
/* 176 */       return false;
/*     */     }
/* 178 */     if (other.isEntityAlive() && isEntityAlive()) {
/*     */       
/* 180 */       ItemStack var2 = getEntityItem();
/* 181 */       ItemStack var3 = other.getEntityItem();
/*     */       
/* 183 */       if (this.delayBeforeCanPickup != 32767 && other.delayBeforeCanPickup != 32767) {
/*     */         
/* 185 */         if (this.age != -32768 && other.age != -32768) {
/*     */           
/* 187 */           if (var3.getItem() != var2.getItem())
/*     */           {
/* 189 */             return false;
/*     */           }
/* 191 */           if ((var3.hasTagCompound() ^ var2.hasTagCompound()) != 0)
/*     */           {
/* 193 */             return false;
/*     */           }
/* 195 */           if (var3.hasTagCompound() && !var3.getTagCompound().equals(var2.getTagCompound()))
/*     */           {
/* 197 */             return false;
/*     */           }
/* 199 */           if (var3.getItem() == null)
/*     */           {
/* 201 */             return false;
/*     */           }
/* 203 */           if (var3.getItem().getHasSubtypes() && var3.getMetadata() != var2.getMetadata())
/*     */           {
/* 205 */             return false;
/*     */           }
/* 207 */           if (var3.stackSize < var2.stackSize)
/*     */           {
/* 209 */             return other.combineItems(this);
/*     */           }
/* 211 */           if (var3.stackSize + var2.stackSize > var3.getMaxStackSize())
/*     */           {
/* 213 */             return false;
/*     */           }
/*     */ 
/*     */           
/* 217 */           var3.stackSize += var2.stackSize;
/* 218 */           other.delayBeforeCanPickup = Math.max(other.delayBeforeCanPickup, this.delayBeforeCanPickup);
/* 219 */           other.age = Math.min(other.age, this.age);
/* 220 */           other.setEntityItemStack(var3);
/* 221 */           setDead();
/* 222 */           return true;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 227 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 232 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 237 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAgeToCreativeDespawnTime() {
/* 247 */     this.age = 4800;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleWaterMovement() {
/* 255 */     if (this.worldObj.handleMaterialAcceleration(getEntityBoundingBox(), Material.water, this)) {
/*     */       
/* 257 */       if (!this.inWater && !this.firstUpdate)
/*     */       {
/* 259 */         resetHeight();
/*     */       }
/*     */       
/* 262 */       this.inWater = true;
/*     */     }
/*     */     else {
/*     */       
/* 266 */       this.inWater = false;
/*     */     } 
/*     */     
/* 269 */     return this.inWater;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dealFireDamage(int amount) {
/* 278 */     attackEntityFrom(DamageSource.inFire, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 286 */     if (func_180431_b(source))
/*     */     {
/* 288 */       return false;
/*     */     }
/* 290 */     if (getEntityItem() != null && getEntityItem().getItem() == Items.nether_star && source.isExplosion())
/*     */     {
/* 292 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 296 */     setBeenAttacked();
/* 297 */     this.health = (int)(this.health - amount);
/*     */     
/* 299 */     if (this.health <= 0)
/*     */     {
/* 301 */       setDead();
/*     */     }
/*     */     
/* 304 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 313 */     tagCompound.setShort("Health", (short)(byte)this.health);
/* 314 */     tagCompound.setShort("Age", (short)this.age);
/* 315 */     tagCompound.setShort("PickupDelay", (short)this.delayBeforeCanPickup);
/*     */     
/* 317 */     if (getThrower() != null)
/*     */     {
/* 319 */       tagCompound.setString("Thrower", this.thrower);
/*     */     }
/*     */     
/* 322 */     if (getOwner() != null)
/*     */     {
/* 324 */       tagCompound.setString("Owner", this.owner);
/*     */     }
/*     */     
/* 327 */     if (getEntityItem() != null)
/*     */     {
/* 329 */       tagCompound.setTag("Item", (NBTBase)getEntityItem().writeToNBT(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 338 */     this.health = tagCompund.getShort("Health") & 0xFF;
/* 339 */     this.age = tagCompund.getShort("Age");
/*     */     
/* 341 */     if (tagCompund.hasKey("PickupDelay"))
/*     */     {
/* 343 */       this.delayBeforeCanPickup = tagCompund.getShort("PickupDelay");
/*     */     }
/*     */     
/* 346 */     if (tagCompund.hasKey("Owner"))
/*     */     {
/* 348 */       this.owner = tagCompund.getString("Owner");
/*     */     }
/*     */     
/* 351 */     if (tagCompund.hasKey("Thrower"))
/*     */     {
/* 353 */       this.thrower = tagCompund.getString("Thrower");
/*     */     }
/*     */     
/* 356 */     NBTTagCompound var2 = tagCompund.getCompoundTag("Item");
/* 357 */     setEntityItemStack(ItemStack.loadItemStackFromNBT(var2));
/*     */     
/* 359 */     if (getEntityItem() == null)
/*     */     {
/* 361 */       setDead();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onCollideWithPlayer(EntityPlayer entityIn) {
/* 370 */     if (!this.worldObj.isRemote) {
/*     */       
/* 372 */       ItemStack var2 = getEntityItem();
/* 373 */       int var3 = var2.stackSize;
/*     */       
/* 375 */       if (this.delayBeforeCanPickup == 0 && (this.owner == null || 6000 - this.age <= 200 || this.owner.equals(entityIn.getName())) && entityIn.inventory.addItemStackToInventory(var2)) {
/*     */         
/* 377 */         if (var2.getItem() == Item.getItemFromBlock(Blocks.log))
/*     */         {
/* 379 */           entityIn.triggerAchievement((StatBase)AchievementList.mineWood);
/*     */         }
/*     */         
/* 382 */         if (var2.getItem() == Item.getItemFromBlock(Blocks.log2))
/*     */         {
/* 384 */           entityIn.triggerAchievement((StatBase)AchievementList.mineWood);
/*     */         }
/*     */         
/* 387 */         if (var2.getItem() == Items.leather)
/*     */         {
/* 389 */           entityIn.triggerAchievement((StatBase)AchievementList.killCow);
/*     */         }
/*     */         
/* 392 */         if (var2.getItem() == Items.diamond)
/*     */         {
/* 394 */           entityIn.triggerAchievement((StatBase)AchievementList.diamonds);
/*     */         }
/*     */         
/* 397 */         if (var2.getItem() == Items.blaze_rod)
/*     */         {
/* 399 */           entityIn.triggerAchievement((StatBase)AchievementList.blazeRod);
/*     */         }
/*     */         
/* 402 */         if (var2.getItem() == Items.diamond && getThrower() != null) {
/*     */           
/* 404 */           EntityPlayer var4 = this.worldObj.getPlayerEntityByName(getThrower());
/*     */           
/* 406 */           if (var4 != null && var4 != entityIn)
/*     */           {
/* 408 */             var4.triggerAchievement((StatBase)AchievementList.diamondsToYou);
/*     */           }
/*     */         } 
/*     */         
/* 412 */         if (!isSlient())
/*     */         {
/* 414 */           this.worldObj.playSoundAtEntity((Entity)entityIn, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */         }
/*     */         
/* 417 */         entityIn.onItemPickup(this, var3);
/*     */         
/* 419 */         if (var2.stackSize <= 0)
/*     */         {
/* 421 */           setDead();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 432 */     return hasCustomName() ? getCustomNameTag() : StatCollector.translateToLocal("item." + getEntityItem().getUnlocalizedName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 440 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void travelToDimension(int dimensionId) {
/* 448 */     super.travelToDimension(dimensionId);
/*     */     
/* 450 */     if (!this.worldObj.isRemote)
/*     */     {
/* 452 */       searchForOtherItemsNearby();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getEntityItem() {
/* 462 */     ItemStack var1 = getDataWatcher().getWatchableObjectItemStack(10);
/*     */     
/* 464 */     if (var1 == null) {
/*     */       
/* 466 */       if (this.worldObj != null)
/*     */       {
/* 468 */         logger.error("Item entity " + getEntityId() + " has no item?!");
/*     */       }
/*     */       
/* 471 */       return new ItemStack(Blocks.stone);
/*     */     } 
/*     */ 
/*     */     
/* 475 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntityItemStack(ItemStack stack) {
/* 484 */     getDataWatcher().updateObject(10, stack);
/* 485 */     getDataWatcher().setObjectWatched(10);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getOwner() {
/* 490 */     return this.owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(String owner) {
/* 495 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getThrower() {
/* 500 */     return this.thrower;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setThrower(String thrower) {
/* 505 */     this.thrower = thrower;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_174872_o() {
/* 510 */     return this.age;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDefaultPickupDelay() {
/* 515 */     this.delayBeforeCanPickup = 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoPickupDelay() {
/* 520 */     this.delayBeforeCanPickup = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInfinitePickupDelay() {
/* 525 */     this.delayBeforeCanPickup = 32767;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPickupDelay(int ticks) {
/* 530 */     this.delayBeforeCanPickup = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_174874_s() {
/* 535 */     return (this.delayBeforeCanPickup > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174873_u() {
/* 540 */     this.age = -6000;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174870_v() {
/* 545 */     setInfinitePickupDelay();
/* 546 */     this.age = 5999;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */