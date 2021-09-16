/*     */ package net.minecraft.entity.item;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.Rotations;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ 
/*     */ public class EntityArmorStand
/*     */   extends EntityLivingBase
/*     */ {
/*  28 */   private static final Rotations DEFAULT_HEAD_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
/*  29 */   private static final Rotations DEFAULT_BODY_ROTATION = new Rotations(0.0F, 0.0F, 0.0F);
/*  30 */   private static final Rotations DEFAULT_LEFTARM_ROTATION = new Rotations(-10.0F, 0.0F, -10.0F);
/*  31 */   private static final Rotations DEFAULT_RIGHTARM_ROTATION = new Rotations(-15.0F, 0.0F, 10.0F);
/*  32 */   private static final Rotations DEFAULT_LEFTLEG_ROTATION = new Rotations(-1.0F, 0.0F, -1.0F);
/*  33 */   private static final Rotations DEFAULT_RIGHTLEG_ROTATION = new Rotations(1.0F, 0.0F, 1.0F);
/*     */   
/*     */   private final ItemStack[] contents;
/*     */   private boolean canInteract;
/*     */   private long field_175437_i;
/*     */   private int disabledSlots;
/*     */   private Rotations headRotation;
/*     */   private Rotations bodyRotation;
/*     */   private Rotations leftArmRotation;
/*     */   private Rotations rightArmRotation;
/*     */   private Rotations leftLegRotation;
/*     */   private Rotations rightLegRotation;
/*     */   private static final String __OBFID = "CL_00002228";
/*     */   
/*     */   public EntityArmorStand(World worldIn) {
/*  48 */     super(worldIn);
/*  49 */     this.contents = new ItemStack[5];
/*  50 */     this.headRotation = DEFAULT_HEAD_ROTATION;
/*  51 */     this.bodyRotation = DEFAULT_BODY_ROTATION;
/*  52 */     this.leftArmRotation = DEFAULT_LEFTARM_ROTATION;
/*  53 */     this.rightArmRotation = DEFAULT_RIGHTARM_ROTATION;
/*  54 */     this.leftLegRotation = DEFAULT_LEFTLEG_ROTATION;
/*  55 */     this.rightLegRotation = DEFAULT_RIGHTLEG_ROTATION;
/*  56 */     func_174810_b(true);
/*  57 */     this.noClip = hasNoGravity();
/*  58 */     setSize(0.5F, 1.975F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityArmorStand(World worldIn, double p_i45855_2_, double p_i45855_4_, double p_i45855_6_) {
/*  63 */     this(worldIn);
/*  64 */     setPosition(p_i45855_2_, p_i45855_4_, p_i45855_6_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isServerWorld() {
/*  72 */     return (super.isServerWorld() && !hasNoGravity());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  77 */     super.entityInit();
/*  78 */     this.dataWatcher.addObject(10, Byte.valueOf((byte)0));
/*  79 */     this.dataWatcher.addObject(11, DEFAULT_HEAD_ROTATION);
/*  80 */     this.dataWatcher.addObject(12, DEFAULT_BODY_ROTATION);
/*  81 */     this.dataWatcher.addObject(13, DEFAULT_LEFTARM_ROTATION);
/*  82 */     this.dataWatcher.addObject(14, DEFAULT_RIGHTARM_ROTATION);
/*  83 */     this.dataWatcher.addObject(15, DEFAULT_LEFTLEG_ROTATION);
/*  84 */     this.dataWatcher.addObject(16, DEFAULT_RIGHTLEG_ROTATION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getHeldItem() {
/*  92 */     return this.contents[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getEquipmentInSlot(int p_71124_1_) {
/* 100 */     return this.contents[p_71124_1_];
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getCurrentArmor(int p_82169_1_) {
/* 105 */     return this.contents[p_82169_1_ + 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentItemOrArmor(int slotIn, ItemStack itemStackIn) {
/* 113 */     this.contents[slotIn] = itemStackIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack[] getInventory() {
/* 121 */     return this.contents;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
/*     */     int var3;
/* 128 */     if (p_174820_1_ == 99) {
/*     */       
/* 130 */       var3 = 0;
/*     */     }
/*     */     else {
/*     */       
/* 134 */       var3 = p_174820_1_ - 100 + 1;
/*     */       
/* 136 */       if (var3 < 0 || var3 >= this.contents.length)
/*     */       {
/* 138 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 142 */     if (p_174820_2_ != null && EntityLiving.getArmorPosition(p_174820_2_) != var3 && (var3 != 4 || !(p_174820_2_.getItem() instanceof net.minecraft.item.ItemBlock)))
/*     */     {
/* 144 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 148 */     setCurrentItemOrArmor(var3, p_174820_2_);
/* 149 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 158 */     super.writeEntityToNBT(tagCompound);
/* 159 */     NBTTagList var2 = new NBTTagList();
/*     */     
/* 161 */     for (int var3 = 0; var3 < this.contents.length; var3++) {
/*     */       
/* 163 */       NBTTagCompound var4 = new NBTTagCompound();
/*     */       
/* 165 */       if (this.contents[var3] != null)
/*     */       {
/* 167 */         this.contents[var3].writeToNBT(var4);
/*     */       }
/*     */       
/* 170 */       var2.appendTag((NBTBase)var4);
/*     */     } 
/*     */     
/* 173 */     tagCompound.setTag("Equipment", (NBTBase)var2);
/*     */     
/* 175 */     if (getAlwaysRenderNameTag() && (getCustomNameTag() == null || getCustomNameTag().length() == 0))
/*     */     {
/* 177 */       tagCompound.setBoolean("CustomNameVisible", getAlwaysRenderNameTag());
/*     */     }
/*     */     
/* 180 */     tagCompound.setBoolean("Invisible", isInvisible());
/* 181 */     tagCompound.setBoolean("Small", isSmall());
/* 182 */     tagCompound.setBoolean("ShowArms", getShowArms());
/* 183 */     tagCompound.setInteger("DisabledSlots", this.disabledSlots);
/* 184 */     tagCompound.setBoolean("NoGravity", hasNoGravity());
/* 185 */     tagCompound.setBoolean("NoBasePlate", hasNoBasePlate());
/* 186 */     tagCompound.setTag("Pose", (NBTBase)readPoseFromNBT());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 194 */     super.readEntityFromNBT(tagCompund);
/*     */     
/* 196 */     if (tagCompund.hasKey("Equipment", 9)) {
/*     */       
/* 198 */       NBTTagList var2 = tagCompund.getTagList("Equipment", 10);
/*     */       
/* 200 */       for (int var3 = 0; var3 < this.contents.length; var3++)
/*     */       {
/* 202 */         this.contents[var3] = ItemStack.loadItemStackFromNBT(var2.getCompoundTagAt(var3));
/*     */       }
/*     */     } 
/*     */     
/* 206 */     setInvisible(tagCompund.getBoolean("Invisible"));
/* 207 */     setSmall(tagCompund.getBoolean("Small"));
/* 208 */     setShowArms(tagCompund.getBoolean("ShowArms"));
/* 209 */     this.disabledSlots = tagCompund.getInteger("DisabledSlots");
/* 210 */     setNoGravity(tagCompund.getBoolean("NoGravity"));
/* 211 */     setNoBasePlate(tagCompund.getBoolean("NoBasePlate"));
/* 212 */     this.noClip = hasNoGravity();
/* 213 */     NBTTagCompound var4 = tagCompund.getCompoundTag("Pose");
/* 214 */     writePoseToNBT(var4);
/*     */   }
/*     */ 
/*     */   
/*     */   private void writePoseToNBT(NBTTagCompound p_175416_1_) {
/* 219 */     NBTTagList var2 = p_175416_1_.getTagList("Head", 5);
/*     */     
/* 221 */     if (var2.tagCount() > 0) {
/*     */       
/* 223 */       setHeadRotation(new Rotations(var2));
/*     */     }
/*     */     else {
/*     */       
/* 227 */       setHeadRotation(DEFAULT_HEAD_ROTATION);
/*     */     } 
/*     */     
/* 230 */     NBTTagList var3 = p_175416_1_.getTagList("Body", 5);
/*     */     
/* 232 */     if (var3.tagCount() > 0) {
/*     */       
/* 234 */       setBodyRotation(new Rotations(var3));
/*     */     }
/*     */     else {
/*     */       
/* 238 */       setBodyRotation(DEFAULT_BODY_ROTATION);
/*     */     } 
/*     */     
/* 241 */     NBTTagList var4 = p_175416_1_.getTagList("LeftArm", 5);
/*     */     
/* 243 */     if (var4.tagCount() > 0) {
/*     */       
/* 245 */       setLeftArmRotation(new Rotations(var4));
/*     */     }
/*     */     else {
/*     */       
/* 249 */       setLeftArmRotation(DEFAULT_LEFTARM_ROTATION);
/*     */     } 
/*     */     
/* 252 */     NBTTagList var5 = p_175416_1_.getTagList("RightArm", 5);
/*     */     
/* 254 */     if (var5.tagCount() > 0) {
/*     */       
/* 256 */       setRightArmRotation(new Rotations(var5));
/*     */     }
/*     */     else {
/*     */       
/* 260 */       setRightArmRotation(DEFAULT_RIGHTARM_ROTATION);
/*     */     } 
/*     */     
/* 263 */     NBTTagList var6 = p_175416_1_.getTagList("LeftLeg", 5);
/*     */     
/* 265 */     if (var6.tagCount() > 0) {
/*     */       
/* 267 */       setLeftLegRotation(new Rotations(var6));
/*     */     }
/*     */     else {
/*     */       
/* 271 */       setLeftLegRotation(DEFAULT_LEFTLEG_ROTATION);
/*     */     } 
/*     */     
/* 274 */     NBTTagList var7 = p_175416_1_.getTagList("RightLeg", 5);
/*     */     
/* 276 */     if (var7.tagCount() > 0) {
/*     */       
/* 278 */       setRightLegRotation(new Rotations(var7));
/*     */     }
/*     */     else {
/*     */       
/* 282 */       setRightLegRotation(DEFAULT_RIGHTLEG_ROTATION);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private NBTTagCompound readPoseFromNBT() {
/* 288 */     NBTTagCompound var1 = new NBTTagCompound();
/*     */     
/* 290 */     if (!DEFAULT_HEAD_ROTATION.equals(this.headRotation))
/*     */     {
/* 292 */       var1.setTag("Head", (NBTBase)this.headRotation.func_179414_a());
/*     */     }
/*     */     
/* 295 */     if (!DEFAULT_BODY_ROTATION.equals(this.bodyRotation))
/*     */     {
/* 297 */       var1.setTag("Body", (NBTBase)this.bodyRotation.func_179414_a());
/*     */     }
/*     */     
/* 300 */     if (!DEFAULT_LEFTARM_ROTATION.equals(this.leftArmRotation))
/*     */     {
/* 302 */       var1.setTag("LeftArm", (NBTBase)this.leftArmRotation.func_179414_a());
/*     */     }
/*     */     
/* 305 */     if (!DEFAULT_RIGHTARM_ROTATION.equals(this.rightArmRotation))
/*     */     {
/* 307 */       var1.setTag("RightArm", (NBTBase)this.rightArmRotation.func_179414_a());
/*     */     }
/*     */     
/* 310 */     if (!DEFAULT_LEFTLEG_ROTATION.equals(this.leftLegRotation))
/*     */     {
/* 312 */       var1.setTag("LeftLeg", (NBTBase)this.leftLegRotation.func_179414_a());
/*     */     }
/*     */     
/* 315 */     if (!DEFAULT_RIGHTLEG_ROTATION.equals(this.rightLegRotation))
/*     */     {
/* 317 */       var1.setTag("RightLeg", (NBTBase)this.rightLegRotation.func_179414_a());
/*     */     }
/*     */     
/* 320 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/* 328 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void collideWithEntity(Entity p_82167_1_) {}
/*     */   
/*     */   protected void collideWithNearbyEntities() {
/* 335 */     List<Entity> var1 = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, getEntityBoundingBox());
/*     */     
/* 337 */     if (var1 != null && !var1.isEmpty())
/*     */     {
/* 339 */       for (int var2 = 0; var2 < var1.size(); var2++) {
/*     */         
/* 341 */         Entity var3 = var1.get(var2);
/*     */         
/* 343 */         if (var3 instanceof EntityMinecart && ((EntityMinecart)var3).func_180456_s() == EntityMinecart.EnumMinecartType.RIDEABLE && getDistanceSqToEntity(var3) <= 0.2D)
/*     */         {
/* 345 */           var3.applyEntityCollision((Entity)this);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_174825_a(EntityPlayer p_174825_1_, Vec3 p_174825_2_) {
/* 353 */     if (!this.worldObj.isRemote && !p_174825_1_.func_175149_v()) {
/*     */       
/* 355 */       byte var3 = 0;
/* 356 */       ItemStack var4 = p_174825_1_.getCurrentEquippedItem();
/* 357 */       boolean var5 = (var4 != null);
/*     */       
/* 359 */       if (var5 && var4.getItem() instanceof ItemArmor) {
/*     */         
/* 361 */         ItemArmor var6 = (ItemArmor)var4.getItem();
/*     */         
/* 363 */         if (var6.armorType == 3) {
/*     */           
/* 365 */           var3 = 1;
/*     */         }
/* 367 */         else if (var6.armorType == 2) {
/*     */           
/* 369 */           var3 = 2;
/*     */         }
/* 371 */         else if (var6.armorType == 1) {
/*     */           
/* 373 */           var3 = 3;
/*     */         }
/* 375 */         else if (var6.armorType == 0) {
/*     */           
/* 377 */           var3 = 4;
/*     */         } 
/*     */       } 
/*     */       
/* 381 */       if (var5 && (var4.getItem() == Items.skull || var4.getItem() == Item.getItemFromBlock(Blocks.pumpkin)))
/*     */       {
/* 383 */         var3 = 4;
/*     */       }
/*     */       
/* 386 */       double var19 = 0.1D;
/* 387 */       double var8 = 0.9D;
/* 388 */       double var10 = 0.4D;
/* 389 */       double var12 = 1.6D;
/* 390 */       byte var14 = 0;
/* 391 */       boolean var15 = isSmall();
/* 392 */       double var16 = var15 ? (p_174825_2_.yCoord * 2.0D) : p_174825_2_.yCoord;
/*     */       
/* 394 */       if (var16 >= 0.1D && var16 < 0.1D + (var15 ? 0.8D : 0.45D) && this.contents[1] != null) {
/*     */         
/* 396 */         var14 = 1;
/*     */       }
/* 398 */       else if (var16 >= 0.9D + (var15 ? 0.3D : 0.0D) && var16 < 0.9D + (var15 ? 1.0D : 0.7D) && this.contents[3] != null) {
/*     */         
/* 400 */         var14 = 3;
/*     */       }
/* 402 */       else if (var16 >= 0.4D && var16 < 0.4D + (var15 ? 1.0D : 0.8D) && this.contents[2] != null) {
/*     */         
/* 404 */         var14 = 2;
/*     */       }
/* 406 */       else if (var16 >= 1.6D && this.contents[4] != null) {
/*     */         
/* 408 */         var14 = 4;
/*     */       } 
/*     */       
/* 411 */       boolean var18 = (this.contents[var14] != null);
/*     */       
/* 413 */       if ((this.disabledSlots & 1 << var14) != 0 || (this.disabledSlots & 1 << var3) != 0) {
/*     */         
/* 415 */         var14 = var3;
/*     */         
/* 417 */         if ((this.disabledSlots & 1 << var3) != 0) {
/*     */           
/* 419 */           if ((this.disabledSlots & 0x1) != 0)
/*     */           {
/* 421 */             return true;
/*     */           }
/*     */           
/* 424 */           var14 = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 428 */       if (var5 && var3 == 0 && !getShowArms())
/*     */       {
/* 430 */         return true;
/*     */       }
/*     */ 
/*     */       
/* 434 */       if (var5) {
/*     */         
/* 436 */         func_175422_a(p_174825_1_, var3);
/*     */       }
/* 438 */       else if (var18) {
/*     */         
/* 440 */         func_175422_a(p_174825_1_, var14);
/*     */       } 
/*     */       
/* 443 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 448 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_175422_a(EntityPlayer p_175422_1_, int p_175422_2_) {
/* 454 */     ItemStack var3 = this.contents[p_175422_2_];
/*     */     
/* 456 */     if (var3 == null || (this.disabledSlots & 1 << p_175422_2_ + 8) == 0)
/*     */     {
/* 458 */       if (var3 != null || (this.disabledSlots & 1 << p_175422_2_ + 16) == 0) {
/*     */         
/* 460 */         int var4 = p_175422_1_.inventory.currentItem;
/* 461 */         ItemStack var5 = p_175422_1_.inventory.getStackInSlot(var4);
/*     */ 
/*     */         
/* 464 */         if (p_175422_1_.capabilities.isCreativeMode && (var3 == null || var3.getItem() == Item.getItemFromBlock(Blocks.air)) && var5 != null) {
/*     */           
/* 466 */           ItemStack var6 = var5.copy();
/* 467 */           var6.stackSize = 1;
/* 468 */           setCurrentItemOrArmor(p_175422_2_, var6);
/*     */         }
/* 470 */         else if (var5 != null && var5.stackSize > 1) {
/*     */           
/* 472 */           if (var3 == null)
/*     */           {
/* 474 */             ItemStack var6 = var5.copy();
/* 475 */             var6.stackSize = 1;
/* 476 */             setCurrentItemOrArmor(p_175422_2_, var6);
/* 477 */             var5.stackSize--;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 482 */           setCurrentItemOrArmor(p_175422_2_, var5);
/* 483 */           p_175422_1_.inventory.setInventorySlotContents(var4, var3);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 494 */     if (!this.worldObj.isRemote && !this.canInteract) {
/*     */       
/* 496 */       if (DamageSource.outOfWorld.equals(source)) {
/*     */         
/* 498 */         setDead();
/* 499 */         return false;
/*     */       } 
/* 501 */       if (func_180431_b(source))
/*     */       {
/* 503 */         return false;
/*     */       }
/* 505 */       if (source.isExplosion()) {
/*     */         
/* 507 */         dropContents();
/* 508 */         setDead();
/* 509 */         return false;
/*     */       } 
/* 511 */       if (DamageSource.inFire.equals(source)) {
/*     */         
/* 513 */         if (!isBurning()) {
/*     */           
/* 515 */           setFire(5);
/*     */         }
/*     */         else {
/*     */           
/* 519 */           func_175406_a(0.15F);
/*     */         } 
/*     */         
/* 522 */         return false;
/*     */       } 
/* 524 */       if (DamageSource.onFire.equals(source) && getHealth() > 0.5F) {
/*     */         
/* 526 */         func_175406_a(4.0F);
/* 527 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 531 */       boolean var3 = "arrow".equals(source.getDamageType());
/* 532 */       boolean var4 = "player".equals(source.getDamageType());
/*     */       
/* 534 */       if (!var4 && !var3)
/*     */       {
/* 536 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 540 */       if (source.getSourceOfDamage() instanceof net.minecraft.entity.projectile.EntityArrow)
/*     */       {
/* 542 */         source.getSourceOfDamage().setDead();
/*     */       }
/*     */       
/* 545 */       if (source.getEntity() instanceof EntityPlayer && !((EntityPlayer)source.getEntity()).capabilities.allowEdit)
/*     */       {
/* 547 */         return false;
/*     */       }
/* 549 */       if (source.func_180136_u()) {
/*     */         
/* 551 */         playParticles();
/* 552 */         setDead();
/* 553 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 557 */       long var5 = this.worldObj.getTotalWorldTime();
/*     */       
/* 559 */       if (var5 - this.field_175437_i > 5L && !var3) {
/*     */         
/* 561 */         this.field_175437_i = var5;
/*     */       }
/*     */       else {
/*     */         
/* 565 */         dropBlock();
/* 566 */         playParticles();
/* 567 */         setDead();
/*     */       } 
/*     */       
/* 570 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 577 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void playParticles() {
/* 583 */     if (this.worldObj instanceof WorldServer)
/*     */     {
/* 585 */       ((WorldServer)this.worldObj).func_175739_a(EnumParticleTypes.BLOCK_DUST, this.posX, this.posY + this.height / 1.5D, this.posZ, 10, (this.width / 4.0F), (this.height / 4.0F), (this.width / 4.0F), 0.05D, new int[] { Block.getStateId(Blocks.planks.getDefaultState()) });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175406_a(float p_175406_1_) {
/* 591 */     float var2 = getHealth();
/* 592 */     var2 -= p_175406_1_;
/*     */     
/* 594 */     if (var2 <= 0.5F) {
/*     */       
/* 596 */       dropContents();
/* 597 */       setDead();
/*     */     }
/*     */     else {
/*     */       
/* 601 */       setHealth(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void dropBlock() {
/* 607 */     Block.spawnAsEntity(this.worldObj, new BlockPos((Entity)this), new ItemStack((Item)Items.armor_stand));
/* 608 */     dropContents();
/*     */   }
/*     */ 
/*     */   
/*     */   private void dropContents() {
/* 613 */     for (int var1 = 0; var1 < this.contents.length; var1++) {
/*     */       
/* 615 */       if (this.contents[var1] != null && (this.contents[var1]).stackSize > 0) {
/*     */         
/* 617 */         if (this.contents[var1] != null)
/*     */         {
/* 619 */           Block.spawnAsEntity(this.worldObj, (new BlockPos((Entity)this)).offsetUp(), this.contents[var1]);
/*     */         }
/*     */         
/* 622 */         this.contents[var1] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_110146_f(float p_110146_1_, float p_110146_2_) {
/* 629 */     this.prevRenderYawOffset = this.prevRotationYaw;
/* 630 */     this.renderYawOffset = this.rotationYaw;
/* 631 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 636 */     return isChild() ? (this.height * 0.5F) : (this.height * 0.9F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
/* 644 */     if (!hasNoGravity())
/*     */     {
/* 646 */       super.moveEntityWithHeading(p_70612_1_, p_70612_2_);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 655 */     super.onUpdate();
/* 656 */     Rotations var1 = this.dataWatcher.getWatchableObjectRotations(11);
/*     */     
/* 658 */     if (!this.headRotation.equals(var1))
/*     */     {
/* 660 */       setHeadRotation(var1);
/*     */     }
/*     */     
/* 663 */     Rotations var2 = this.dataWatcher.getWatchableObjectRotations(12);
/*     */     
/* 665 */     if (!this.bodyRotation.equals(var2))
/*     */     {
/* 667 */       setBodyRotation(var2);
/*     */     }
/*     */     
/* 670 */     Rotations var3 = this.dataWatcher.getWatchableObjectRotations(13);
/*     */     
/* 672 */     if (!this.leftArmRotation.equals(var3))
/*     */     {
/* 674 */       setLeftArmRotation(var3);
/*     */     }
/*     */     
/* 677 */     Rotations var4 = this.dataWatcher.getWatchableObjectRotations(14);
/*     */     
/* 679 */     if (!this.rightArmRotation.equals(var4))
/*     */     {
/* 681 */       setRightArmRotation(var4);
/*     */     }
/*     */     
/* 684 */     Rotations var5 = this.dataWatcher.getWatchableObjectRotations(15);
/*     */     
/* 686 */     if (!this.leftLegRotation.equals(var5))
/*     */     {
/* 688 */       setLeftLegRotation(var5);
/*     */     }
/*     */     
/* 691 */     Rotations var6 = this.dataWatcher.getWatchableObjectRotations(16);
/*     */     
/* 693 */     if (!this.rightLegRotation.equals(var6))
/*     */     {
/* 695 */       setRightLegRotation(var6);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175135_B() {
/* 701 */     setInvisible(this.canInteract);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvisible(boolean invisible) {
/* 706 */     this.canInteract = invisible;
/* 707 */     super.setInvisible(invisible);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChild() {
/* 715 */     return isSmall();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174812_G() {
/* 720 */     setDead();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_180427_aV() {
/* 725 */     return isInvisible();
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSmall(boolean p_175420_1_) {
/* 730 */     byte var2 = this.dataWatcher.getWatchableObjectByte(10);
/*     */     
/* 732 */     if (p_175420_1_) {
/*     */       
/* 734 */       var2 = (byte)(var2 | 0x1);
/*     */     }
/*     */     else {
/*     */       
/* 738 */       var2 = (byte)(var2 & 0xFFFFFFFE);
/*     */     } 
/*     */     
/* 741 */     this.dataWatcher.updateObject(10, Byte.valueOf(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSmall() {
/* 746 */     return ((this.dataWatcher.getWatchableObjectByte(10) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setNoGravity(boolean p_175425_1_) {
/* 751 */     byte var2 = this.dataWatcher.getWatchableObjectByte(10);
/*     */     
/* 753 */     if (p_175425_1_) {
/*     */       
/* 755 */       var2 = (byte)(var2 | 0x2);
/*     */     }
/*     */     else {
/*     */       
/* 759 */       var2 = (byte)(var2 & 0xFFFFFFFD);
/*     */     } 
/*     */     
/* 762 */     this.dataWatcher.updateObject(10, Byte.valueOf(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNoGravity() {
/* 767 */     return ((this.dataWatcher.getWatchableObjectByte(10) & 0x2) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setShowArms(boolean p_175413_1_) {
/* 772 */     byte var2 = this.dataWatcher.getWatchableObjectByte(10);
/*     */     
/* 774 */     if (p_175413_1_) {
/*     */       
/* 776 */       var2 = (byte)(var2 | 0x4);
/*     */     }
/*     */     else {
/*     */       
/* 780 */       var2 = (byte)(var2 & 0xFFFFFFFB);
/*     */     } 
/*     */     
/* 783 */     this.dataWatcher.updateObject(10, Byte.valueOf(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getShowArms() {
/* 788 */     return ((this.dataWatcher.getWatchableObjectByte(10) & 0x4) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setNoBasePlate(boolean p_175426_1_) {
/* 793 */     byte var2 = this.dataWatcher.getWatchableObjectByte(10);
/*     */     
/* 795 */     if (p_175426_1_) {
/*     */       
/* 797 */       var2 = (byte)(var2 | 0x8);
/*     */     }
/*     */     else {
/*     */       
/* 801 */       var2 = (byte)(var2 & 0xFFFFFFF7);
/*     */     } 
/*     */     
/* 804 */     this.dataWatcher.updateObject(10, Byte.valueOf(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNoBasePlate() {
/* 809 */     return ((this.dataWatcher.getWatchableObjectByte(10) & 0x8) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadRotation(Rotations p_175415_1_) {
/* 814 */     this.headRotation = p_175415_1_;
/* 815 */     this.dataWatcher.updateObject(11, p_175415_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBodyRotation(Rotations p_175424_1_) {
/* 820 */     this.bodyRotation = p_175424_1_;
/* 821 */     this.dataWatcher.updateObject(12, p_175424_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeftArmRotation(Rotations p_175405_1_) {
/* 826 */     this.leftArmRotation = p_175405_1_;
/* 827 */     this.dataWatcher.updateObject(13, p_175405_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRightArmRotation(Rotations p_175428_1_) {
/* 832 */     this.rightArmRotation = p_175428_1_;
/* 833 */     this.dataWatcher.updateObject(14, p_175428_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLeftLegRotation(Rotations p_175417_1_) {
/* 838 */     this.leftLegRotation = p_175417_1_;
/* 839 */     this.dataWatcher.updateObject(15, p_175417_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRightLegRotation(Rotations p_175427_1_) {
/* 844 */     this.rightLegRotation = p_175427_1_;
/* 845 */     this.dataWatcher.updateObject(16, p_175427_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public Rotations getHeadRotation() {
/* 850 */     return this.headRotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rotations getBodyRotation() {
/* 855 */     return this.bodyRotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rotations getLeftArmRotation() {
/* 860 */     return this.leftArmRotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rotations getRightArmRotation() {
/* 865 */     return this.rightArmRotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rotations getLeftLegRotation() {
/* 870 */     return this.leftLegRotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rotations getRightLegRotation() {
/* 875 */     return this.rightLegRotation;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */