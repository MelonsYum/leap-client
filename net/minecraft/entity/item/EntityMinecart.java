/*      */ package net.minecraft.entity.item;
/*      */ 
/*      */ import com.google.common.collect.Maps;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockRailBase;
/*      */ import net.minecraft.block.BlockRailPowered;
/*      */ import net.minecraft.block.properties.IProperty;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.EntityMinecartCommandBlock;
/*      */ import net.minecraft.entity.ai.EntityMinecartMobSpawner;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.IWorldNameable;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ 
/*      */ public abstract class EntityMinecart
/*      */   extends Entity
/*      */   implements IWorldNameable
/*      */ {
/*      */   private boolean isInReverse;
/*      */   private String entityName;
/*   40 */   private static final int[][][] matrix = new int[][][] { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1 }, { 1 } }, { { -1, -1 }, { 1 } }, { { -1 }, { 1, -1 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1 } }, { { 0, 0, 1 }, { -1 } }, { { 0, 0, -1 }, { -1 } }, { { 0, 0, -1 }, { 1 } } };
/*      */   
/*      */   private int turnProgress;
/*      */   
/*      */   private double minecartX;
/*      */   
/*      */   private double minecartY;
/*      */   private double minecartZ;
/*      */   private double minecartYaw;
/*      */   private double minecartPitch;
/*      */   private double velocityX;
/*      */   private double velocityY;
/*      */   private double velocityZ;
/*      */   private static final String __OBFID = "CL_00001670";
/*      */   
/*      */   public EntityMinecart(World worldIn) {
/*   56 */     super(worldIn);
/*   57 */     this.preventEntitySpawning = true;
/*   58 */     setSize(0.98F, 0.7F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static EntityMinecart func_180458_a(World worldIn, double p_180458_1_, double p_180458_3_, double p_180458_5_, EnumMinecartType p_180458_7_) {
/*   63 */     switch (SwitchEnumMinecartType.field_180037_a[p_180458_7_.ordinal()]) {
/*      */       
/*      */       case 1:
/*   66 */         return new EntityMinecartChest(worldIn, p_180458_1_, p_180458_3_, p_180458_5_);
/*      */       
/*      */       case 2:
/*   69 */         return new EntityMinecartFurnace(worldIn, p_180458_1_, p_180458_3_, p_180458_5_);
/*      */       
/*      */       case 3:
/*   72 */         return new EntityMinecartTNT(worldIn, p_180458_1_, p_180458_3_, p_180458_5_);
/*      */       
/*      */       case 4:
/*   75 */         return (EntityMinecart)new EntityMinecartMobSpawner(worldIn, p_180458_1_, p_180458_3_, p_180458_5_);
/*      */       
/*      */       case 5:
/*   78 */         return new EntityMinecartHopper(worldIn, p_180458_1_, p_180458_3_, p_180458_5_);
/*      */       
/*      */       case 6:
/*   81 */         return (EntityMinecart)new EntityMinecartCommandBlock(worldIn, p_180458_1_, p_180458_3_, p_180458_5_);
/*      */     } 
/*      */     
/*   84 */     return new EntityMinecartEmpty(worldIn, p_180458_1_, p_180458_3_, p_180458_5_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canTriggerWalking() {
/*   94 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*   99 */     this.dataWatcher.addObject(17, new Integer(0));
/*  100 */     this.dataWatcher.addObject(18, new Integer(1));
/*  101 */     this.dataWatcher.addObject(19, new Float(0.0F));
/*  102 */     this.dataWatcher.addObject(20, new Integer(0));
/*  103 */     this.dataWatcher.addObject(21, new Integer(6));
/*  104 */     this.dataWatcher.addObject(22, Byte.valueOf((byte)0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getCollisionBox(Entity entityIn) {
/*  113 */     return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getBoundingBox() {
/*  121 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePushed() {
/*  129 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityMinecart(World worldIn, double p_i1713_2_, double p_i1713_4_, double p_i1713_6_) {
/*  134 */     this(worldIn);
/*  135 */     setPosition(p_i1713_2_, p_i1713_4_, p_i1713_6_);
/*  136 */     this.motionX = 0.0D;
/*  137 */     this.motionY = 0.0D;
/*  138 */     this.motionZ = 0.0D;
/*  139 */     this.prevPosX = p_i1713_2_;
/*  140 */     this.prevPosY = p_i1713_4_;
/*  141 */     this.prevPosZ = p_i1713_6_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMountedYOffset() {
/*  149 */     return this.height * 0.5D - 0.20000000298023224D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean attackEntityFrom(DamageSource source, float amount) {
/*  157 */     if (!this.worldObj.isRemote && !this.isDead) {
/*      */       
/*  159 */       if (func_180431_b(source))
/*      */       {
/*  161 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  165 */       setRollingDirection(-getRollingDirection());
/*  166 */       setRollingAmplitude(10);
/*  167 */       setBeenAttacked();
/*  168 */       setDamage(getDamage() + amount * 10.0F);
/*  169 */       boolean var3 = (source.getEntity() instanceof EntityPlayer && ((EntityPlayer)source.getEntity()).capabilities.isCreativeMode);
/*      */       
/*  171 */       if (var3 || getDamage() > 40.0F) {
/*      */         
/*  173 */         if (this.riddenByEntity != null)
/*      */         {
/*  175 */           this.riddenByEntity.mountEntity(null);
/*      */         }
/*      */         
/*  178 */         if (var3 && !hasCustomName()) {
/*      */           
/*  180 */           setDead();
/*      */         }
/*      */         else {
/*      */           
/*  184 */           killMinecart(source);
/*      */         } 
/*      */       } 
/*      */       
/*  188 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  193 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void killMinecart(DamageSource p_94095_1_) {
/*  199 */     setDead();
/*  200 */     ItemStack var2 = new ItemStack(Items.minecart, 1);
/*      */     
/*  202 */     if (this.entityName != null)
/*      */     {
/*  204 */       var2.setStackDisplayName(this.entityName);
/*      */     }
/*      */     
/*  207 */     entityDropItem(var2, 0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void performHurtAnimation() {
/*  215 */     setRollingDirection(-getRollingDirection());
/*  216 */     setRollingAmplitude(10);
/*  217 */     setDamage(getDamage() + getDamage() * 10.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeCollidedWith() {
/*  225 */     return !this.isDead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDead() {
/*  233 */     super.setDead();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*  241 */     if (getRollingAmplitude() > 0)
/*      */     {
/*  243 */       setRollingAmplitude(getRollingAmplitude() - 1);
/*      */     }
/*      */     
/*  246 */     if (getDamage() > 0.0F)
/*      */     {
/*  248 */       setDamage(getDamage() - 1.0F);
/*      */     }
/*      */     
/*  251 */     if (this.posY < -64.0D)
/*      */     {
/*  253 */       kill();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  258 */     if (!this.worldObj.isRemote && this.worldObj instanceof WorldServer) {
/*      */       
/*  260 */       this.worldObj.theProfiler.startSection("portal");
/*  261 */       MinecraftServer var1 = ((WorldServer)this.worldObj).func_73046_m();
/*  262 */       int var2 = getMaxInPortalTime();
/*      */       
/*  264 */       if (this.inPortal) {
/*      */         
/*  266 */         if (var1.getAllowNether())
/*      */         {
/*  268 */           if (this.ridingEntity == null && this.portalCounter++ >= var2) {
/*      */             byte var3;
/*  270 */             this.portalCounter = var2;
/*  271 */             this.timeUntilPortal = getPortalCooldown();
/*      */ 
/*      */             
/*  274 */             if (this.worldObj.provider.getDimensionId() == -1) {
/*      */               
/*  276 */               var3 = 0;
/*      */             }
/*      */             else {
/*      */               
/*  280 */               var3 = -1;
/*      */             } 
/*      */             
/*  283 */             travelToDimension(var3);
/*      */           } 
/*      */           
/*  286 */           this.inPortal = false;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  291 */         if (this.portalCounter > 0)
/*      */         {
/*  293 */           this.portalCounter -= 4;
/*      */         }
/*      */         
/*  296 */         if (this.portalCounter < 0)
/*      */         {
/*  298 */           this.portalCounter = 0;
/*      */         }
/*      */       } 
/*      */       
/*  302 */       if (this.timeUntilPortal > 0)
/*      */       {
/*  304 */         this.timeUntilPortal--;
/*      */       }
/*      */       
/*  307 */       this.worldObj.theProfiler.endSection();
/*      */     } 
/*      */     
/*  310 */     if (this.worldObj.isRemote) {
/*      */       
/*  312 */       if (this.turnProgress > 0)
/*      */       {
/*  314 */         double var15 = this.posX + (this.minecartX - this.posX) / this.turnProgress;
/*  315 */         double var17 = this.posY + (this.minecartY - this.posY) / this.turnProgress;
/*  316 */         double var18 = this.posZ + (this.minecartZ - this.posZ) / this.turnProgress;
/*  317 */         double var7 = MathHelper.wrapAngleTo180_double(this.minecartYaw - this.rotationYaw);
/*  318 */         this.rotationYaw = (float)(this.rotationYaw + var7 / this.turnProgress);
/*  319 */         this.rotationPitch = (float)(this.rotationPitch + (this.minecartPitch - this.rotationPitch) / this.turnProgress);
/*  320 */         this.turnProgress--;
/*  321 */         setPosition(var15, var17, var18);
/*  322 */         setRotation(this.rotationYaw, this.rotationPitch);
/*      */       }
/*      */       else
/*      */       {
/*  326 */         setPosition(this.posX, this.posY, this.posZ);
/*  327 */         setRotation(this.rotationYaw, this.rotationPitch);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  332 */       this.prevPosX = this.posX;
/*  333 */       this.prevPosY = this.posY;
/*  334 */       this.prevPosZ = this.posZ;
/*  335 */       this.motionY -= 0.03999999910593033D;
/*  336 */       int var14 = MathHelper.floor_double(this.posX);
/*  337 */       int var2 = MathHelper.floor_double(this.posY);
/*  338 */       int var16 = MathHelper.floor_double(this.posZ);
/*      */       
/*  340 */       if (BlockRailBase.func_176562_d(this.worldObj, new BlockPos(var14, var2 - 1, var16)))
/*      */       {
/*  342 */         var2--;
/*      */       }
/*      */       
/*  345 */       BlockPos var4 = new BlockPos(var14, var2, var16);
/*  346 */       IBlockState var5 = this.worldObj.getBlockState(var4);
/*      */       
/*  348 */       if (BlockRailBase.func_176563_d(var5)) {
/*      */         
/*  350 */         func_180460_a(var4, var5);
/*      */         
/*  352 */         if (var5.getBlock() == Blocks.activator_rail)
/*      */         {
/*  354 */           onActivatorRailPass(var14, var2, var16, ((Boolean)var5.getValue((IProperty)BlockRailPowered.field_176569_M)).booleanValue());
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  359 */         func_180459_n();
/*      */       } 
/*      */       
/*  362 */       doBlockCollisions();
/*  363 */       this.rotationPitch = 0.0F;
/*  364 */       double var6 = this.prevPosX - this.posX;
/*  365 */       double var8 = this.prevPosZ - this.posZ;
/*      */       
/*  367 */       if (var6 * var6 + var8 * var8 > 0.001D) {
/*      */         
/*  369 */         this.rotationYaw = (float)(Math.atan2(var8, var6) * 180.0D / Math.PI);
/*      */         
/*  371 */         if (this.isInReverse)
/*      */         {
/*  373 */           this.rotationYaw += 180.0F;
/*      */         }
/*      */       } 
/*      */       
/*  377 */       double var10 = MathHelper.wrapAngleTo180_float(this.rotationYaw - this.prevRotationYaw);
/*      */       
/*  379 */       if (var10 < -170.0D || var10 >= 170.0D) {
/*      */         
/*  381 */         this.rotationYaw += 180.0F;
/*  382 */         this.isInReverse = !this.isInReverse;
/*      */       } 
/*      */       
/*  385 */       setRotation(this.rotationYaw, this.rotationPitch);
/*  386 */       Iterator<Entity> var12 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(0.20000000298023224D, 0.0D, 0.20000000298023224D)).iterator();
/*      */       
/*  388 */       while (var12.hasNext()) {
/*      */         
/*  390 */         Entity var13 = var12.next();
/*      */         
/*  392 */         if (var13 != this.riddenByEntity && var13.canBePushed() && var13 instanceof EntityMinecart)
/*      */         {
/*  394 */           var13.applyEntityCollision(this);
/*      */         }
/*      */       } 
/*      */       
/*  398 */       if (this.riddenByEntity != null && this.riddenByEntity.isDead) {
/*      */         
/*  400 */         if (this.riddenByEntity.ridingEntity == this)
/*      */         {
/*  402 */           this.riddenByEntity.ridingEntity = null;
/*      */         }
/*      */         
/*  405 */         this.riddenByEntity = null;
/*      */       } 
/*      */       
/*  408 */       handleWaterMovement();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected double func_174898_m() {
/*  414 */     return 0.4D;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onActivatorRailPass(int p_96095_1_, int p_96095_2_, int p_96095_3_, boolean p_96095_4_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_180459_n() {
/*  424 */     double var1 = func_174898_m();
/*  425 */     this.motionX = MathHelper.clamp_double(this.motionX, -var1, var1);
/*  426 */     this.motionZ = MathHelper.clamp_double(this.motionZ, -var1, var1);
/*      */     
/*  428 */     if (this.onGround) {
/*      */       
/*  430 */       this.motionX *= 0.5D;
/*  431 */       this.motionY *= 0.5D;
/*  432 */       this.motionZ *= 0.5D;
/*      */     } 
/*      */     
/*  435 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*      */     
/*  437 */     if (!this.onGround) {
/*      */       
/*  439 */       this.motionX *= 0.949999988079071D;
/*  440 */       this.motionY *= 0.949999988079071D;
/*  441 */       this.motionZ *= 0.949999988079071D;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_180460_a(BlockPos p_180460_1_, IBlockState p_180460_2_) {
/*  447 */     this.fallDistance = 0.0F;
/*  448 */     Vec3 var3 = func_70489_a(this.posX, this.posY, this.posZ);
/*  449 */     this.posY = p_180460_1_.getY();
/*  450 */     boolean var4 = false;
/*  451 */     boolean var5 = false;
/*  452 */     BlockRailBase var6 = (BlockRailBase)p_180460_2_.getBlock();
/*      */     
/*  454 */     if (var6 == Blocks.golden_rail) {
/*      */       
/*  456 */       var4 = ((Boolean)p_180460_2_.getValue((IProperty)BlockRailPowered.field_176569_M)).booleanValue();
/*  457 */       var5 = !var4;
/*      */     } 
/*      */     
/*  460 */     double var7 = 0.0078125D;
/*  461 */     BlockRailBase.EnumRailDirection var9 = (BlockRailBase.EnumRailDirection)p_180460_2_.getValue(var6.func_176560_l());
/*      */     
/*  463 */     switch (SwitchEnumMinecartType.field_180036_b[var9.ordinal()]) {
/*      */       
/*      */       case 1:
/*  466 */         this.motionX -= 0.0078125D;
/*  467 */         this.posY++;
/*      */         break;
/*      */       
/*      */       case 2:
/*  471 */         this.motionX += 0.0078125D;
/*  472 */         this.posY++;
/*      */         break;
/*      */       
/*      */       case 3:
/*  476 */         this.motionZ += 0.0078125D;
/*  477 */         this.posY++;
/*      */         break;
/*      */       
/*      */       case 4:
/*  481 */         this.motionZ -= 0.0078125D;
/*  482 */         this.posY++;
/*      */         break;
/*      */     } 
/*  485 */     int[][] var10 = matrix[var9.func_177015_a()];
/*  486 */     double var11 = (var10[1][0] - var10[0][0]);
/*  487 */     double var13 = (var10[1][2] - var10[0][2]);
/*  488 */     double var15 = Math.sqrt(var11 * var11 + var13 * var13);
/*  489 */     double var17 = this.motionX * var11 + this.motionZ * var13;
/*      */     
/*  491 */     if (var17 < 0.0D) {
/*      */       
/*  493 */       var11 = -var11;
/*  494 */       var13 = -var13;
/*      */     } 
/*      */     
/*  497 */     double var19 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*      */     
/*  499 */     if (var19 > 2.0D)
/*      */     {
/*  501 */       var19 = 2.0D;
/*      */     }
/*      */     
/*  504 */     this.motionX = var19 * var11 / var15;
/*  505 */     this.motionZ = var19 * var13 / var15;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  511 */     if (this.riddenByEntity instanceof EntityLivingBase) {
/*      */       
/*  513 */       double d = ((EntityLivingBase)this.riddenByEntity).moveForward;
/*      */       
/*  515 */       if (d > 0.0D) {
/*      */         
/*  517 */         double d1 = -Math.sin((this.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
/*  518 */         double d2 = Math.cos((this.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
/*  519 */         double d3 = this.motionX * this.motionX + this.motionZ * this.motionZ;
/*      */         
/*  521 */         if (d3 < 0.01D) {
/*      */           
/*  523 */           this.motionX += d1 * 0.1D;
/*  524 */           this.motionZ += d2 * 0.1D;
/*  525 */           var5 = false;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  530 */     if (var5) {
/*      */       
/*  532 */       double d = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*      */       
/*  534 */       if (d < 0.03D) {
/*      */         
/*  536 */         this.motionX *= 0.0D;
/*  537 */         this.motionY *= 0.0D;
/*  538 */         this.motionZ *= 0.0D;
/*      */       }
/*      */       else {
/*      */         
/*  542 */         this.motionX *= 0.5D;
/*  543 */         this.motionY *= 0.0D;
/*  544 */         this.motionZ *= 0.5D;
/*      */       } 
/*      */     } 
/*      */     
/*  548 */     double var21 = 0.0D;
/*  549 */     double var23 = p_180460_1_.getX() + 0.5D + var10[0][0] * 0.5D;
/*  550 */     double var25 = p_180460_1_.getZ() + 0.5D + var10[0][2] * 0.5D;
/*  551 */     double var27 = p_180460_1_.getX() + 0.5D + var10[1][0] * 0.5D;
/*  552 */     double var29 = p_180460_1_.getZ() + 0.5D + var10[1][2] * 0.5D;
/*  553 */     var11 = var27 - var23;
/*  554 */     var13 = var29 - var25;
/*      */ 
/*      */ 
/*      */     
/*  558 */     if (var11 == 0.0D) {
/*      */       
/*  560 */       this.posX = p_180460_1_.getX() + 0.5D;
/*  561 */       var21 = this.posZ - p_180460_1_.getZ();
/*      */     }
/*  563 */     else if (var13 == 0.0D) {
/*      */       
/*  565 */       this.posZ = p_180460_1_.getZ() + 0.5D;
/*  566 */       var21 = this.posX - p_180460_1_.getX();
/*      */     }
/*      */     else {
/*      */       
/*  570 */       double d1 = this.posX - var23;
/*  571 */       double d2 = this.posZ - var25;
/*  572 */       var21 = (d1 * var11 + d2 * var13) * 2.0D;
/*      */     } 
/*      */     
/*  575 */     this.posX = var23 + var11 * var21;
/*  576 */     this.posZ = var25 + var13 * var21;
/*  577 */     setPosition(this.posX, this.posY, this.posZ);
/*  578 */     double var31 = this.motionX;
/*  579 */     double var33 = this.motionZ;
/*      */     
/*  581 */     if (this.riddenByEntity != null) {
/*      */       
/*  583 */       var31 *= 0.75D;
/*  584 */       var33 *= 0.75D;
/*      */     } 
/*      */     
/*  587 */     double var35 = func_174898_m();
/*  588 */     var31 = MathHelper.clamp_double(var31, -var35, var35);
/*  589 */     var33 = MathHelper.clamp_double(var33, -var35, var35);
/*  590 */     moveEntity(var31, 0.0D, var33);
/*      */     
/*  592 */     if (var10[0][1] != 0 && MathHelper.floor_double(this.posX) - p_180460_1_.getX() == var10[0][0] && MathHelper.floor_double(this.posZ) - p_180460_1_.getZ() == var10[0][2]) {
/*      */       
/*  594 */       setPosition(this.posX, this.posY + var10[0][1], this.posZ);
/*      */     }
/*  596 */     else if (var10[1][1] != 0 && MathHelper.floor_double(this.posX) - p_180460_1_.getX() == var10[1][0] && MathHelper.floor_double(this.posZ) - p_180460_1_.getZ() == var10[1][2]) {
/*      */       
/*  598 */       setPosition(this.posX, this.posY + var10[1][1], this.posZ);
/*      */     } 
/*      */     
/*  601 */     applyDrag();
/*  602 */     Vec3 var37 = func_70489_a(this.posX, this.posY, this.posZ);
/*      */     
/*  604 */     if (var37 != null && var3 != null) {
/*      */       
/*  606 */       double var38 = (var3.yCoord - var37.yCoord) * 0.05D;
/*  607 */       var19 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*      */       
/*  609 */       if (var19 > 0.0D) {
/*      */         
/*  611 */         this.motionX = this.motionX / var19 * (var19 + var38);
/*  612 */         this.motionZ = this.motionZ / var19 * (var19 + var38);
/*      */       } 
/*      */       
/*  615 */       setPosition(this.posX, var37.yCoord, this.posZ);
/*      */     } 
/*      */     
/*  618 */     int var44 = MathHelper.floor_double(this.posX);
/*  619 */     int var39 = MathHelper.floor_double(this.posZ);
/*      */     
/*  621 */     if (var44 != p_180460_1_.getX() || var39 != p_180460_1_.getZ()) {
/*      */       
/*  623 */       var19 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*  624 */       this.motionX = var19 * (var44 - p_180460_1_.getX());
/*  625 */       this.motionZ = var19 * (var39 - p_180460_1_.getZ());
/*      */     } 
/*      */     
/*  628 */     if (var4) {
/*      */       
/*  630 */       double var40 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*      */       
/*  632 */       if (var40 > 0.01D) {
/*      */         
/*  634 */         double var42 = 0.06D;
/*  635 */         this.motionX += this.motionX / var40 * var42;
/*  636 */         this.motionZ += this.motionZ / var40 * var42;
/*      */       }
/*  638 */       else if (var9 == BlockRailBase.EnumRailDirection.EAST_WEST) {
/*      */         
/*  640 */         if (this.worldObj.getBlockState(p_180460_1_.offsetWest()).getBlock().isNormalCube())
/*      */         {
/*  642 */           this.motionX = 0.02D;
/*      */         }
/*  644 */         else if (this.worldObj.getBlockState(p_180460_1_.offsetEast()).getBlock().isNormalCube())
/*      */         {
/*  646 */           this.motionX = -0.02D;
/*      */         }
/*      */       
/*  649 */       } else if (var9 == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
/*      */         
/*  651 */         if (this.worldObj.getBlockState(p_180460_1_.offsetNorth()).getBlock().isNormalCube()) {
/*      */           
/*  653 */           this.motionZ = 0.02D;
/*      */         }
/*  655 */         else if (this.worldObj.getBlockState(p_180460_1_.offsetSouth()).getBlock().isNormalCube()) {
/*      */           
/*  657 */           this.motionZ = -0.02D;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void applyDrag() {
/*  665 */     if (this.riddenByEntity != null) {
/*      */       
/*  667 */       this.motionX *= 0.996999979019165D;
/*  668 */       this.motionY *= 0.0D;
/*  669 */       this.motionZ *= 0.996999979019165D;
/*      */     }
/*      */     else {
/*      */       
/*  673 */       this.motionX *= 0.9599999785423279D;
/*  674 */       this.motionY *= 0.0D;
/*  675 */       this.motionZ *= 0.9599999785423279D;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPosition(double x, double y, double z) {
/*  684 */     this.posX = x;
/*  685 */     this.posY = y;
/*  686 */     this.posZ = z;
/*  687 */     float var7 = this.width / 2.0F;
/*  688 */     float var8 = this.height;
/*  689 */     func_174826_a(new AxisAlignedBB(x - var7, y, z - var7, x + var7, y + var8, z + var7));
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 func_70495_a(double p_70495_1_, double p_70495_3_, double p_70495_5_, double p_70495_7_) {
/*  694 */     int var9 = MathHelper.floor_double(p_70495_1_);
/*  695 */     int var10 = MathHelper.floor_double(p_70495_3_);
/*  696 */     int var11 = MathHelper.floor_double(p_70495_5_);
/*      */     
/*  698 */     if (BlockRailBase.func_176562_d(this.worldObj, new BlockPos(var9, var10 - 1, var11)))
/*      */     {
/*  700 */       var10--;
/*      */     }
/*      */     
/*  703 */     IBlockState var12 = this.worldObj.getBlockState(new BlockPos(var9, var10, var11));
/*      */     
/*  705 */     if (BlockRailBase.func_176563_d(var12)) {
/*      */       
/*  707 */       BlockRailBase.EnumRailDirection var13 = (BlockRailBase.EnumRailDirection)var12.getValue(((BlockRailBase)var12.getBlock()).func_176560_l());
/*  708 */       p_70495_3_ = var10;
/*      */       
/*  710 */       if (var13.func_177018_c())
/*      */       {
/*  712 */         p_70495_3_ = (var10 + 1);
/*      */       }
/*      */       
/*  715 */       int[][] var14 = matrix[var13.func_177015_a()];
/*  716 */       double var15 = (var14[1][0] - var14[0][0]);
/*  717 */       double var17 = (var14[1][2] - var14[0][2]);
/*  718 */       double var19 = Math.sqrt(var15 * var15 + var17 * var17);
/*  719 */       var15 /= var19;
/*  720 */       var17 /= var19;
/*  721 */       p_70495_1_ += var15 * p_70495_7_;
/*  722 */       p_70495_5_ += var17 * p_70495_7_;
/*      */       
/*  724 */       if (var14[0][1] != 0 && MathHelper.floor_double(p_70495_1_) - var9 == var14[0][0] && MathHelper.floor_double(p_70495_5_) - var11 == var14[0][2]) {
/*      */         
/*  726 */         p_70495_3_ += var14[0][1];
/*      */       }
/*  728 */       else if (var14[1][1] != 0 && MathHelper.floor_double(p_70495_1_) - var9 == var14[1][0] && MathHelper.floor_double(p_70495_5_) - var11 == var14[1][2]) {
/*      */         
/*  730 */         p_70495_3_ += var14[1][1];
/*      */       } 
/*      */       
/*  733 */       return func_70489_a(p_70495_1_, p_70495_3_, p_70495_5_);
/*      */     } 
/*      */ 
/*      */     
/*  737 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 func_70489_a(double p_70489_1_, double p_70489_3_, double p_70489_5_) {
/*  743 */     int var7 = MathHelper.floor_double(p_70489_1_);
/*  744 */     int var8 = MathHelper.floor_double(p_70489_3_);
/*  745 */     int var9 = MathHelper.floor_double(p_70489_5_);
/*      */     
/*  747 */     if (BlockRailBase.func_176562_d(this.worldObj, new BlockPos(var7, var8 - 1, var9)))
/*      */     {
/*  749 */       var8--;
/*      */     }
/*      */     
/*  752 */     IBlockState var10 = this.worldObj.getBlockState(new BlockPos(var7, var8, var9));
/*      */     
/*  754 */     if (BlockRailBase.func_176563_d(var10)) {
/*      */       
/*  756 */       BlockRailBase.EnumRailDirection var11 = (BlockRailBase.EnumRailDirection)var10.getValue(((BlockRailBase)var10.getBlock()).func_176560_l());
/*  757 */       int[][] var12 = matrix[var11.func_177015_a()];
/*  758 */       double var13 = 0.0D;
/*  759 */       double var15 = var7 + 0.5D + var12[0][0] * 0.5D;
/*  760 */       double var17 = var8 + 0.0625D + var12[0][1] * 0.5D;
/*  761 */       double var19 = var9 + 0.5D + var12[0][2] * 0.5D;
/*  762 */       double var21 = var7 + 0.5D + var12[1][0] * 0.5D;
/*  763 */       double var23 = var8 + 0.0625D + var12[1][1] * 0.5D;
/*  764 */       double var25 = var9 + 0.5D + var12[1][2] * 0.5D;
/*  765 */       double var27 = var21 - var15;
/*  766 */       double var29 = (var23 - var17) * 2.0D;
/*  767 */       double var31 = var25 - var19;
/*      */       
/*  769 */       if (var27 == 0.0D) {
/*      */         
/*  771 */         p_70489_1_ = var7 + 0.5D;
/*  772 */         var13 = p_70489_5_ - var9;
/*      */       }
/*  774 */       else if (var31 == 0.0D) {
/*      */         
/*  776 */         p_70489_5_ = var9 + 0.5D;
/*  777 */         var13 = p_70489_1_ - var7;
/*      */       }
/*      */       else {
/*      */         
/*  781 */         double var33 = p_70489_1_ - var15;
/*  782 */         double var35 = p_70489_5_ - var19;
/*  783 */         var13 = (var33 * var27 + var35 * var31) * 2.0D;
/*      */       } 
/*      */       
/*  786 */       p_70489_1_ = var15 + var27 * var13;
/*  787 */       p_70489_3_ = var17 + var29 * var13;
/*  788 */       p_70489_5_ = var19 + var31 * var13;
/*      */       
/*  790 */       if (var29 < 0.0D)
/*      */       {
/*  792 */         p_70489_3_++;
/*      */       }
/*      */       
/*  795 */       if (var29 > 0.0D)
/*      */       {
/*  797 */         p_70489_3_ += 0.5D;
/*      */       }
/*      */       
/*  800 */       return new Vec3(p_70489_1_, p_70489_3_, p_70489_5_);
/*      */     } 
/*      */ 
/*      */     
/*  804 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void readEntityFromNBT(NBTTagCompound tagCompund) {
/*  813 */     if (tagCompund.getBoolean("CustomDisplayTile")) {
/*      */       
/*  815 */       int var2 = tagCompund.getInteger("DisplayData");
/*      */ 
/*      */       
/*  818 */       if (tagCompund.hasKey("DisplayTile", 8)) {
/*      */         
/*  820 */         Block var3 = Block.getBlockFromName(tagCompund.getString("DisplayTile"));
/*      */         
/*  822 */         if (var3 == null)
/*      */         {
/*  824 */           func_174899_a(Blocks.air.getDefaultState());
/*      */         }
/*      */         else
/*      */         {
/*  828 */           func_174899_a(var3.getStateFromMeta(var2));
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  833 */         Block var3 = Block.getBlockById(tagCompund.getInteger("DisplayTile"));
/*      */         
/*  835 */         if (var3 == null) {
/*      */           
/*  837 */           func_174899_a(Blocks.air.getDefaultState());
/*      */         }
/*      */         else {
/*      */           
/*  841 */           func_174899_a(var3.getStateFromMeta(var2));
/*      */         } 
/*      */       } 
/*      */       
/*  845 */       setDisplayTileOffset(tagCompund.getInteger("DisplayOffset"));
/*      */     } 
/*      */     
/*  848 */     if (tagCompund.hasKey("CustomName", 8) && tagCompund.getString("CustomName").length() > 0)
/*      */     {
/*  850 */       this.entityName = tagCompund.getString("CustomName");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
/*  859 */     if (hasDisplayTile()) {
/*      */       
/*  861 */       tagCompound.setBoolean("CustomDisplayTile", true);
/*  862 */       IBlockState var2 = func_174897_t();
/*  863 */       ResourceLocation var3 = (ResourceLocation)Block.blockRegistry.getNameForObject(var2.getBlock());
/*  864 */       tagCompound.setString("DisplayTile", (var3 == null) ? "" : var3.toString());
/*  865 */       tagCompound.setInteger("DisplayData", var2.getBlock().getMetaFromState(var2));
/*  866 */       tagCompound.setInteger("DisplayOffset", getDisplayTileOffset());
/*      */     } 
/*      */     
/*  869 */     if (this.entityName != null && this.entityName.length() > 0)
/*      */     {
/*  871 */       tagCompound.setString("CustomName", this.entityName);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void applyEntityCollision(Entity entityIn) {
/*  880 */     if (!this.worldObj.isRemote)
/*      */     {
/*  882 */       if (!entityIn.noClip && !this.noClip)
/*      */       {
/*  884 */         if (entityIn != this.riddenByEntity) {
/*      */           
/*  886 */           if (entityIn instanceof EntityLivingBase && !(entityIn instanceof EntityPlayer) && !(entityIn instanceof net.minecraft.entity.monster.EntityIronGolem) && func_180456_s() == EnumMinecartType.RIDEABLE && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.01D && this.riddenByEntity == null && entityIn.ridingEntity == null)
/*      */           {
/*  888 */             entityIn.mountEntity(this);
/*      */           }
/*      */           
/*  891 */           double var2 = entityIn.posX - this.posX;
/*  892 */           double var4 = entityIn.posZ - this.posZ;
/*  893 */           double var6 = var2 * var2 + var4 * var4;
/*      */           
/*  895 */           if (var6 >= 9.999999747378752E-5D) {
/*      */             
/*  897 */             var6 = MathHelper.sqrt_double(var6);
/*  898 */             var2 /= var6;
/*  899 */             var4 /= var6;
/*  900 */             double var8 = 1.0D / var6;
/*      */             
/*  902 */             if (var8 > 1.0D)
/*      */             {
/*  904 */               var8 = 1.0D;
/*      */             }
/*      */             
/*  907 */             var2 *= var8;
/*  908 */             var4 *= var8;
/*  909 */             var2 *= 0.10000000149011612D;
/*  910 */             var4 *= 0.10000000149011612D;
/*  911 */             var2 *= (1.0F - this.entityCollisionReduction);
/*  912 */             var4 *= (1.0F - this.entityCollisionReduction);
/*  913 */             var2 *= 0.5D;
/*  914 */             var4 *= 0.5D;
/*      */             
/*  916 */             if (entityIn instanceof EntityMinecart) {
/*      */               
/*  918 */               double var10 = entityIn.posX - this.posX;
/*  919 */               double var12 = entityIn.posZ - this.posZ;
/*  920 */               Vec3 var14 = (new Vec3(var10, 0.0D, var12)).normalize();
/*  921 */               Vec3 var15 = (new Vec3(MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F), 0.0D, MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F))).normalize();
/*  922 */               double var16 = Math.abs(var14.dotProduct(var15));
/*      */               
/*  924 */               if (var16 < 0.800000011920929D) {
/*      */                 return;
/*      */               }
/*      */ 
/*      */               
/*  929 */               double var18 = entityIn.motionX + this.motionX;
/*  930 */               double var20 = entityIn.motionZ + this.motionZ;
/*      */               
/*  932 */               if (((EntityMinecart)entityIn).func_180456_s() == EnumMinecartType.FURNACE && func_180456_s() != EnumMinecartType.FURNACE)
/*      */               {
/*  934 */                 this.motionX *= 0.20000000298023224D;
/*  935 */                 this.motionZ *= 0.20000000298023224D;
/*  936 */                 addVelocity(entityIn.motionX - var2, 0.0D, entityIn.motionZ - var4);
/*  937 */                 entityIn.motionX *= 0.949999988079071D;
/*  938 */                 entityIn.motionZ *= 0.949999988079071D;
/*      */               }
/*  940 */               else if (((EntityMinecart)entityIn).func_180456_s() != EnumMinecartType.FURNACE && func_180456_s() == EnumMinecartType.FURNACE)
/*      */               {
/*  942 */                 entityIn.motionX *= 0.20000000298023224D;
/*  943 */                 entityIn.motionZ *= 0.20000000298023224D;
/*  944 */                 entityIn.addVelocity(this.motionX + var2, 0.0D, this.motionZ + var4);
/*  945 */                 this.motionX *= 0.949999988079071D;
/*  946 */                 this.motionZ *= 0.949999988079071D;
/*      */               }
/*      */               else
/*      */               {
/*  950 */                 var18 /= 2.0D;
/*  951 */                 var20 /= 2.0D;
/*  952 */                 this.motionX *= 0.20000000298023224D;
/*  953 */                 this.motionZ *= 0.20000000298023224D;
/*  954 */                 addVelocity(var18 - var2, 0.0D, var20 - var4);
/*  955 */                 entityIn.motionX *= 0.20000000298023224D;
/*  956 */                 entityIn.motionZ *= 0.20000000298023224D;
/*  957 */                 entityIn.addVelocity(var18 + var2, 0.0D, var20 + var4);
/*      */               }
/*      */             
/*      */             } else {
/*      */               
/*  962 */               addVelocity(-var2, 0.0D, -var4);
/*  963 */               entityIn.addVelocity(var2 / 4.0D, 0.0D, var4 / 4.0D);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
/*  973 */     this.minecartX = p_180426_1_;
/*  974 */     this.minecartY = p_180426_3_;
/*  975 */     this.minecartZ = p_180426_5_;
/*  976 */     this.minecartYaw = p_180426_7_;
/*  977 */     this.minecartPitch = p_180426_8_;
/*  978 */     this.turnProgress = p_180426_9_ + 2;
/*  979 */     this.motionX = this.velocityX;
/*  980 */     this.motionY = this.velocityY;
/*  981 */     this.motionZ = this.velocityZ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVelocity(double x, double y, double z) {
/*  989 */     this.velocityX = this.motionX = x;
/*  990 */     this.velocityY = this.motionY = y;
/*  991 */     this.velocityZ = this.motionZ = z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDamage(float p_70492_1_) {
/* 1000 */     this.dataWatcher.updateObject(19, Float.valueOf(p_70492_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getDamage() {
/* 1009 */     return this.dataWatcher.getWatchableObjectFloat(19);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRollingAmplitude(int p_70497_1_) {
/* 1017 */     this.dataWatcher.updateObject(17, Integer.valueOf(p_70497_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRollingAmplitude() {
/* 1025 */     return this.dataWatcher.getWatchableObjectInt(17);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRollingDirection(int p_70494_1_) {
/* 1033 */     this.dataWatcher.updateObject(18, Integer.valueOf(p_70494_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getRollingDirection() {
/* 1041 */     return this.dataWatcher.getWatchableObjectInt(18);
/*      */   }
/*      */ 
/*      */   
/*      */   public abstract EnumMinecartType func_180456_s();
/*      */   
/*      */   public IBlockState func_174897_t() {
/* 1048 */     return !hasDisplayTile() ? func_180457_u() : Block.getStateById(getDataWatcher().getWatchableObjectInt(20));
/*      */   }
/*      */ 
/*      */   
/*      */   public IBlockState func_180457_u() {
/* 1053 */     return Blocks.air.getDefaultState();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDisplayTileOffset() {
/* 1058 */     return !hasDisplayTile() ? getDefaultDisplayTileOffset() : getDataWatcher().getWatchableObjectInt(21);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDefaultDisplayTileOffset() {
/* 1063 */     return 6;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174899_a(IBlockState p_174899_1_) {
/* 1068 */     getDataWatcher().updateObject(20, Integer.valueOf(Block.getStateId(p_174899_1_)));
/* 1069 */     setHasDisplayTile(true);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDisplayTileOffset(int p_94086_1_) {
/* 1074 */     getDataWatcher().updateObject(21, Integer.valueOf(p_94086_1_));
/* 1075 */     setHasDisplayTile(true);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasDisplayTile() {
/* 1080 */     return (getDataWatcher().getWatchableObjectByte(22) == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHasDisplayTile(boolean p_94096_1_) {
/* 1085 */     getDataWatcher().updateObject(22, Byte.valueOf((byte)(p_94096_1_ ? 1 : 0)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCustomNameTag(String p_96094_1_) {
/* 1093 */     this.entityName = p_96094_1_;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/* 1101 */     return (this.entityName != null) ? this.entityName : super.getName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasCustomName() {
/* 1109 */     return (this.entityName != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getCustomNameTag() {
/* 1114 */     return this.entityName;
/*      */   }
/*      */ 
/*      */   
/*      */   public IChatComponent getDisplayName() {
/* 1119 */     if (hasCustomName()) {
/*      */       
/* 1121 */       ChatComponentText var2 = new ChatComponentText(this.entityName);
/* 1122 */       var2.getChatStyle().setChatHoverEvent(func_174823_aP());
/* 1123 */       var2.getChatStyle().setInsertion(getUniqueID().toString());
/* 1124 */       return (IChatComponent)var2;
/*      */     } 
/*      */ 
/*      */     
/* 1128 */     ChatComponentTranslation var1 = new ChatComponentTranslation(getName(), new Object[0]);
/* 1129 */     var1.getChatStyle().setChatHoverEvent(func_174823_aP());
/* 1130 */     var1.getChatStyle().setInsertion(getUniqueID().toString());
/* 1131 */     return (IChatComponent)var1;
/*      */   }
/*      */ 
/*      */   
/*      */   public enum EnumMinecartType
/*      */   {
/* 1137 */     RIDEABLE("RIDEABLE", 0, 0, "MinecartRideable"),
/* 1138 */     CHEST("CHEST", 1, 1, "MinecartChest"),
/* 1139 */     FURNACE("FURNACE", 2, 2, "MinecartFurnace"),
/* 1140 */     TNT("TNT", 3, 3, "MinecartTNT"),
/* 1141 */     SPAWNER("SPAWNER", 4, 4, "MinecartSpawner"),
/* 1142 */     HOPPER("HOPPER", 5, 5, "MinecartHopper"),
/* 1143 */     COMMAND_BLOCK("COMMAND_BLOCK", 6, 6, "MinecartCommandBlock");
/* 1144 */     private static final Map field_180051_h = Maps.newHashMap();
/*      */     
/*      */     private final int field_180052_i;
/*      */     private final String field_180049_j;
/* 1148 */     private static final EnumMinecartType[] $VALUES = new EnumMinecartType[] { RIDEABLE, CHEST, FURNACE, TNT, SPAWNER, HOPPER, COMMAND_BLOCK };
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
/*      */     private static final String __OBFID = "CL_00002226";
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
/*      */     static {
/* 1174 */       EnumMinecartType[] var0 = values();
/* 1175 */       int var1 = var0.length;
/*      */       
/* 1177 */       for (int var2 = 0; var2 < var1; var2++) {
/*      */         
/* 1179 */         EnumMinecartType var3 = var0[var2];
/* 1180 */         field_180051_h.put(Integer.valueOf(var3.func_180039_a()), var3);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     EnumMinecartType(String p_i45847_1_, int p_i45847_2_, int p_i45847_3_, String p_i45847_4_) {
/*      */       this.field_180052_i = p_i45847_3_;
/*      */       this.field_180049_j = p_i45847_4_;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int func_180039_a() {
/*      */       return this.field_180052_i;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String func_180040_b() {
/*      */       return this.field_180049_j;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static EnumMinecartType func_180038_a(int p_180038_0_) {
/*      */       EnumMinecartType var1 = (EnumMinecartType)field_180051_h.get(Integer.valueOf(p_180038_0_));
/*      */       return (var1 == null) ? RIDEABLE : var1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final class SwitchEnumMinecartType
/*      */   {
/* 1230 */     static final int[] field_180037_a = new int[(EntityMinecart.EnumMinecartType.values()).length];
/*      */     
/*      */     static {
/*      */       try {
/* 1234 */         field_180037_a[EntityMinecart.EnumMinecartType.CHEST.ordinal()] = 1;
/*      */       }
/* 1236 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1243 */         field_180037_a[EntityMinecart.EnumMinecartType.FURNACE.ordinal()] = 2;
/*      */       }
/* 1245 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1252 */         field_180037_a[EntityMinecart.EnumMinecartType.TNT.ordinal()] = 3;
/*      */       }
/* 1254 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1261 */         field_180037_a[EntityMinecart.EnumMinecartType.SPAWNER.ordinal()] = 4;
/*      */       }
/* 1263 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1270 */         field_180037_a[EntityMinecart.EnumMinecartType.HOPPER.ordinal()] = 5;
/*      */       }
/* 1272 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1279 */         field_180037_a[EntityMinecart.EnumMinecartType.COMMAND_BLOCK.ordinal()] = 6;
/*      */       }
/* 1281 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */     
/*      */     static final int[] field_180036_b = new int[(BlockRailBase.EnumRailDirection.values()).length];
/*      */     private static final String __OBFID = "CL_00002227";
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\item\EntityMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */