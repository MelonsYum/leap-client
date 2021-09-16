/*      */ package net.minecraft.entity;
/*      */ 
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.Callable;
/*      */ import net.minecraft.block.Block;
/*      */ import net.minecraft.block.BlockLiquid;
/*      */ import net.minecraft.block.material.Material;
/*      */ import net.minecraft.block.state.IBlockState;
/*      */ import net.minecraft.command.CommandResultStats;
/*      */ import net.minecraft.command.ICommandSender;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.enchantment.EnchantmentProtection;
/*      */ import net.minecraft.entity.effect.EntityLightningBolt;
/*      */ import net.minecraft.entity.item.EntityItem;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.event.HoverEvent;
/*      */ import net.minecraft.init.Blocks;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagDouble;
/*      */ import net.minecraft.nbt.NBTTagFloat;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.server.MinecraftServer;
/*      */ import net.minecraft.util.AxisAlignedBB;
/*      */ import net.minecraft.util.BlockPos;
/*      */ import net.minecraft.util.ChatComponentText;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.EnumFacing;
/*      */ import net.minecraft.util.EnumParticleTypes;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.MovingObjectPosition;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.StatCollector;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.Explosion;
/*      */ import net.minecraft.world.World;
/*      */ import net.minecraft.world.WorldServer;
/*      */ 
/*      */ 
/*      */ public abstract class Entity
/*      */   implements ICommandSender
/*      */ {
/*   52 */   private static final AxisAlignedBB field_174836_a = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*      */ 
/*      */   
/*      */   private static int nextEntityID;
/*      */ 
/*      */   
/*      */   private int entityId;
/*      */ 
/*      */   
/*      */   public double renderDistanceWeight;
/*      */ 
/*      */   
/*      */   public boolean preventEntitySpawning;
/*      */ 
/*      */   
/*      */   public Entity riddenByEntity;
/*      */ 
/*      */   
/*      */   public Entity ridingEntity;
/*      */ 
/*      */   
/*      */   public boolean forceSpawn;
/*      */ 
/*      */   
/*      */   public World worldObj;
/*      */ 
/*      */   
/*      */   public double prevPosX;
/*      */ 
/*      */   
/*      */   public double prevPosY;
/*      */ 
/*      */   
/*      */   public double prevPosZ;
/*      */ 
/*      */   
/*      */   public double posX;
/*      */ 
/*      */   
/*      */   public double posY;
/*      */ 
/*      */   
/*      */   public double posZ;
/*      */ 
/*      */   
/*      */   public double motionX;
/*      */ 
/*      */   
/*      */   public double motionY;
/*      */ 
/*      */   
/*      */   public double motionZ;
/*      */ 
/*      */   
/*      */   public float rotationYaw;
/*      */ 
/*      */   
/*      */   public float rotationPitch;
/*      */ 
/*      */   
/*      */   public float prevRotationYaw;
/*      */ 
/*      */   
/*      */   public float prevRotationPitch;
/*      */ 
/*      */   
/*      */   public AxisAlignedBB boundingBox;
/*      */ 
/*      */   
/*      */   public boolean onGround;
/*      */ 
/*      */   
/*      */   public boolean isCollidedHorizontally;
/*      */ 
/*      */   
/*      */   public boolean isCollidedVertically;
/*      */ 
/*      */   
/*      */   public boolean isCollided;
/*      */ 
/*      */   
/*      */   public boolean velocityChanged;
/*      */ 
/*      */   
/*      */   protected boolean isInWeb;
/*      */ 
/*      */   
/*      */   private boolean isOutsideBorder;
/*      */ 
/*      */   
/*      */   public boolean isDead;
/*      */ 
/*      */   
/*      */   public float width;
/*      */ 
/*      */   
/*      */   public float height;
/*      */ 
/*      */   
/*      */   public float prevDistanceWalkedModified;
/*      */ 
/*      */   
/*      */   public float distanceWalkedModified;
/*      */ 
/*      */   
/*      */   public float distanceWalkedOnStepModified;
/*      */   
/*      */   public float fallDistance;
/*      */   
/*      */   private int nextStepDistance;
/*      */   
/*      */   public double lastTickPosX;
/*      */   
/*      */   public double lastTickPosY;
/*      */   
/*      */   public double lastTickPosZ;
/*      */   
/*      */   public float stepHeight;
/*      */   
/*      */   public boolean noClip;
/*      */   
/*      */   public float entityCollisionReduction;
/*      */   
/*      */   protected Random rand;
/*      */   
/*      */   public int ticksExisted;
/*      */   
/*      */   public int fireResistance;
/*      */   
/*      */   private int fire;
/*      */   
/*      */   public boolean inWater;
/*      */   
/*      */   public int hurtResistantTime;
/*      */   
/*      */   protected boolean firstUpdate;
/*      */   
/*      */   protected boolean isImmuneToFire;
/*      */   
/*      */   protected DataWatcher dataWatcher;
/*      */   
/*      */   private double entityRiderPitchDelta;
/*      */   
/*      */   private double entityRiderYawDelta;
/*      */   
/*      */   public boolean addedToChunk;
/*      */   
/*      */   public int chunkCoordX;
/*      */   
/*      */   public int chunkCoordY;
/*      */   
/*      */   public int chunkCoordZ;
/*      */   
/*      */   public int serverPosX;
/*      */   
/*      */   public int serverPosY;
/*      */   
/*      */   public int serverPosZ;
/*      */   
/*      */   public boolean ignoreFrustumCheck;
/*      */   
/*      */   public boolean isAirBorne;
/*      */   
/*      */   public int timeUntilPortal;
/*      */   
/*      */   protected boolean inPortal;
/*      */   
/*      */   protected int portalCounter;
/*      */   
/*      */   public int dimension;
/*      */   
/*      */   protected int teleportDirection;
/*      */   
/*      */   private boolean invulnerable;
/*      */   
/*      */   protected UUID entityUniqueID;
/*      */   
/*      */   private final CommandResultStats field_174837_as;
/*      */   
/*      */   private static final String __OBFID = "CL_00001533";
/*      */ 
/*      */   
/*      */   public int getEntityId() {
/*  235 */     return this.entityId;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEntityId(int id) {
/*  240 */     this.entityId = id;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174812_G() {
/*  245 */     setDead();
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity(World worldIn) {
/*  250 */     this.entityId = nextEntityID++;
/*  251 */     this.renderDistanceWeight = 1.0D;
/*  252 */     this.boundingBox = field_174836_a;
/*  253 */     this.width = 0.6F;
/*  254 */     this.height = 1.8F;
/*  255 */     this.nextStepDistance = 1;
/*  256 */     this.rand = new Random();
/*  257 */     this.fireResistance = 1;
/*  258 */     this.firstUpdate = true;
/*  259 */     this.entityUniqueID = MathHelper.func_180182_a(this.rand);
/*  260 */     this.field_174837_as = new CommandResultStats();
/*  261 */     this.worldObj = worldIn;
/*  262 */     setPosition(0.0D, 0.0D, 0.0D);
/*      */     
/*  264 */     if (worldIn != null)
/*      */     {
/*  266 */       this.dimension = worldIn.provider.getDimensionId();
/*      */     }
/*      */     
/*  269 */     this.dataWatcher = new DataWatcher(this);
/*  270 */     this.dataWatcher.addObject(0, Byte.valueOf((byte)0));
/*  271 */     this.dataWatcher.addObject(1, Short.valueOf((short)300));
/*  272 */     this.dataWatcher.addObject(3, Byte.valueOf((byte)0));
/*  273 */     this.dataWatcher.addObject(2, "");
/*  274 */     this.dataWatcher.addObject(4, Byte.valueOf((byte)0));
/*  275 */     entityInit();
/*      */   }
/*      */ 
/*      */   
/*      */   protected abstract void entityInit();
/*      */   
/*      */   public DataWatcher getDataWatcher() {
/*  282 */     return this.dataWatcher;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object p_equals_1_) {
/*  287 */     return (p_equals_1_ instanceof Entity) ? ((((Entity)p_equals_1_).entityId == this.entityId)) : false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  292 */     return this.entityId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void preparePlayerToSpawn() {
/*  301 */     if (this.worldObj != null) {
/*      */       
/*  303 */       while (this.posY > 0.0D && this.posY < 256.0D) {
/*      */         
/*  305 */         setPosition(this.posX, this.posY, this.posZ);
/*      */         
/*  307 */         if (this.worldObj.getCollidingBoundingBoxes(this, getEntityBoundingBox()).isEmpty()) {
/*      */           break;
/*      */         }
/*      */ 
/*      */         
/*  312 */         this.posY++;
/*      */       } 
/*      */       
/*  315 */       this.motionX = this.motionY = this.motionZ = 0.0D;
/*  316 */       this.rotationPitch = 0.0F;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDead() {
/*  325 */     this.isDead = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setSize(float width, float height) {
/*  333 */     if (width != this.width || height != this.height) {
/*      */       
/*  335 */       float var3 = this.width;
/*  336 */       this.width = width;
/*  337 */       this.height = height;
/*  338 */       func_174826_a(new AxisAlignedBB((getEntityBoundingBox()).minX, (getEntityBoundingBox()).minY, (getEntityBoundingBox()).minZ, (getEntityBoundingBox()).minX + this.width, (getEntityBoundingBox()).minY + this.height, (getEntityBoundingBox()).minZ + this.width));
/*      */       
/*  340 */       if (this.width > var3 && !this.firstUpdate && !this.worldObj.isRemote)
/*      */       {
/*  342 */         moveEntity((var3 - this.width), 0.0D, (var3 - this.width));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setRotation(float yaw, float pitch) {
/*  352 */     this.rotationYaw = yaw % 360.0F;
/*  353 */     this.rotationPitch = pitch % 360.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPosition(double x, double y, double z) {
/*  361 */     this.posX = x;
/*  362 */     this.posY = y;
/*  363 */     this.posZ = z;
/*  364 */     float var7 = this.width / 2.0F;
/*  365 */     float var8 = this.height;
/*  366 */     func_174826_a(new AxisAlignedBB(x - var7, y, z - var7, x + var7, y + var8, z + var7));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAngles(float yaw, float pitch) {
/*  375 */     float var3 = this.rotationPitch;
/*  376 */     float var4 = this.rotationYaw;
/*  377 */     this.rotationYaw = (float)(this.rotationYaw + yaw * 0.15D);
/*  378 */     this.rotationPitch = (float)(this.rotationPitch - pitch * 0.15D);
/*  379 */     this.rotationPitch = MathHelper.clamp_float(this.rotationPitch, -90.0F, 90.0F);
/*  380 */     this.prevRotationPitch += this.rotationPitch - var3;
/*  381 */     this.prevRotationYaw += this.rotationYaw - var4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*  389 */     onEntityUpdate();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onEntityUpdate() {
/*  397 */     this.worldObj.theProfiler.startSection("entityBaseTick");
/*      */     
/*  399 */     if (this.ridingEntity != null && this.ridingEntity.isDead)
/*      */     {
/*  401 */       this.ridingEntity = null;
/*      */     }
/*      */     
/*  404 */     this.prevDistanceWalkedModified = this.distanceWalkedModified;
/*  405 */     this.prevPosX = this.posX;
/*  406 */     this.prevPosY = this.posY;
/*  407 */     this.prevPosZ = this.posZ;
/*  408 */     this.prevRotationPitch = this.rotationPitch;
/*  409 */     this.prevRotationYaw = this.rotationYaw;
/*      */     
/*  411 */     if (!this.worldObj.isRemote && this.worldObj instanceof WorldServer) {
/*      */       
/*  413 */       this.worldObj.theProfiler.startSection("portal");
/*  414 */       MinecraftServer var1 = ((WorldServer)this.worldObj).func_73046_m();
/*  415 */       int var2 = getMaxInPortalTime();
/*      */       
/*  417 */       if (this.inPortal) {
/*      */         
/*  419 */         if (var1.getAllowNether())
/*      */         {
/*  421 */           if (this.ridingEntity == null && this.portalCounter++ >= var2) {
/*      */             byte var3;
/*  423 */             this.portalCounter = var2;
/*  424 */             this.timeUntilPortal = getPortalCooldown();
/*      */ 
/*      */             
/*  427 */             if (this.worldObj.provider.getDimensionId() == -1) {
/*      */               
/*  429 */               var3 = 0;
/*      */             }
/*      */             else {
/*      */               
/*  433 */               var3 = -1;
/*      */             } 
/*      */             
/*  436 */             travelToDimension(var3);
/*      */           } 
/*      */           
/*  439 */           this.inPortal = false;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  444 */         if (this.portalCounter > 0)
/*      */         {
/*  446 */           this.portalCounter -= 4;
/*      */         }
/*      */         
/*  449 */         if (this.portalCounter < 0)
/*      */         {
/*  451 */           this.portalCounter = 0;
/*      */         }
/*      */       } 
/*      */       
/*  455 */       if (this.timeUntilPortal > 0)
/*      */       {
/*  457 */         this.timeUntilPortal--;
/*      */       }
/*      */       
/*  460 */       this.worldObj.theProfiler.endSection();
/*      */     } 
/*      */     
/*  463 */     func_174830_Y();
/*  464 */     handleWaterMovement();
/*      */     
/*  466 */     if (this.worldObj.isRemote) {
/*      */       
/*  468 */       this.fire = 0;
/*      */     }
/*  470 */     else if (this.fire > 0) {
/*      */       
/*  472 */       if (this.isImmuneToFire) {
/*      */         
/*  474 */         this.fire -= 4;
/*      */         
/*  476 */         if (this.fire < 0)
/*      */         {
/*  478 */           this.fire = 0;
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  483 */         if (this.fire % 20 == 0)
/*      */         {
/*  485 */           attackEntityFrom(DamageSource.onFire, 1.0F);
/*      */         }
/*      */         
/*  488 */         this.fire--;
/*      */       } 
/*      */     } 
/*      */     
/*  492 */     if (func_180799_ab()) {
/*      */       
/*  494 */       setOnFireFromLava();
/*  495 */       this.fallDistance *= 0.5F;
/*      */     } 
/*      */     
/*  498 */     if (this.posY < -64.0D)
/*      */     {
/*  500 */       kill();
/*      */     }
/*      */     
/*  503 */     if (!this.worldObj.isRemote)
/*      */     {
/*  505 */       setFlag(0, (this.fire > 0));
/*      */     }
/*      */     
/*  508 */     this.firstUpdate = false;
/*  509 */     this.worldObj.theProfiler.endSection();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxInPortalTime() {
/*  517 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setOnFireFromLava() {
/*  525 */     if (!this.isImmuneToFire) {
/*      */       
/*  527 */       attackEntityFrom(DamageSource.lava, 4.0F);
/*  528 */       setFire(15);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFire(int seconds) {
/*  537 */     int var2 = seconds * 20;
/*  538 */     var2 = EnchantmentProtection.getFireTimeForEntity(this, var2);
/*      */     
/*  540 */     if (this.fire < var2)
/*      */     {
/*  542 */       this.fire = var2;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void extinguish() {
/*  551 */     this.fire = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void kill() {
/*  559 */     setDead();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOffsetPositionInLiquid(double x, double y, double z) {
/*  567 */     AxisAlignedBB var7 = getEntityBoundingBox().offset(x, y, z);
/*  568 */     return func_174809_b(var7);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean func_174809_b(AxisAlignedBB p_174809_1_) {
/*  573 */     return (this.worldObj.getCollidingBoundingBoxes(this, p_174809_1_).isEmpty() && !this.worldObj.isAnyLiquid(p_174809_1_));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveEntity(double x, double y, double z) {
/*  581 */     if (this.noClip) {
/*      */       
/*  583 */       func_174826_a(getEntityBoundingBox().offset(x, y, z));
/*  584 */       func_174829_m();
/*      */     }
/*      */     else {
/*      */       
/*  588 */       this.worldObj.theProfiler.startSection("move");
/*  589 */       double var7 = this.posX;
/*  590 */       double var9 = this.posY;
/*  591 */       double var11 = this.posZ;
/*      */       
/*  593 */       if (this.isInWeb) {
/*      */         
/*  595 */         this.isInWeb = false;
/*  596 */         x *= 0.25D;
/*  597 */         y *= 0.05000000074505806D;
/*  598 */         z *= 0.25D;
/*  599 */         this.motionX = 0.0D;
/*  600 */         this.motionY = 0.0D;
/*  601 */         this.motionZ = 0.0D;
/*      */       } 
/*      */       
/*  604 */       double var13 = x;
/*  605 */       double var15 = y;
/*  606 */       double var17 = z;
/*  607 */       boolean var19 = (this.onGround && isSneaking() && this instanceof EntityPlayer);
/*      */       
/*  609 */       if (var19) {
/*      */         double var20;
/*      */ 
/*      */         
/*  613 */         for (var20 = 0.05D; x != 0.0D && this.worldObj.getCollidingBoundingBoxes(this, getEntityBoundingBox().offset(x, -1.0D, 0.0D)).isEmpty(); var13 = x) {
/*      */           
/*  615 */           if (x < var20 && x >= -var20) {
/*      */             
/*  617 */             x = 0.0D;
/*      */           }
/*  619 */           else if (x > 0.0D) {
/*      */             
/*  621 */             x -= var20;
/*      */           }
/*      */           else {
/*      */             
/*  625 */             x += var20;
/*      */           } 
/*      */         } 
/*      */         
/*  629 */         for (; z != 0.0D && this.worldObj.getCollidingBoundingBoxes(this, getEntityBoundingBox().offset(0.0D, -1.0D, z)).isEmpty(); var17 = z) {
/*      */           
/*  631 */           if (z < var20 && z >= -var20) {
/*      */             
/*  633 */             z = 0.0D;
/*      */           }
/*  635 */           else if (z > 0.0D) {
/*      */             
/*  637 */             z -= var20;
/*      */           }
/*      */           else {
/*      */             
/*  641 */             z += var20;
/*      */           } 
/*      */         } 
/*      */         
/*  645 */         for (; x != 0.0D && z != 0.0D && this.worldObj.getCollidingBoundingBoxes(this, getEntityBoundingBox().offset(x, -1.0D, z)).isEmpty(); var17 = z) {
/*      */           
/*  647 */           if (x < var20 && x >= -var20) {
/*      */             
/*  649 */             x = 0.0D;
/*      */           }
/*  651 */           else if (x > 0.0D) {
/*      */             
/*  653 */             x -= var20;
/*      */           }
/*      */           else {
/*      */             
/*  657 */             x += var20;
/*      */           } 
/*      */           
/*  660 */           var13 = x;
/*      */           
/*  662 */           if (z < var20 && z >= -var20) {
/*      */             
/*  664 */             z = 0.0D;
/*      */           }
/*  666 */           else if (z > 0.0D) {
/*      */             
/*  668 */             z -= var20;
/*      */           }
/*      */           else {
/*      */             
/*  672 */             z += var20;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  677 */       List<AxisAlignedBB> var53 = this.worldObj.getCollidingBoundingBoxes(this, getEntityBoundingBox().addCoord(x, y, z));
/*  678 */       AxisAlignedBB var21 = getEntityBoundingBox();
/*      */ 
/*      */       
/*  681 */       for (Iterator<AxisAlignedBB> var22 = var53.iterator(); var22.hasNext(); y = var23.calculateYOffset(getEntityBoundingBox(), y))
/*      */       {
/*  683 */         AxisAlignedBB var23 = var22.next();
/*      */       }
/*      */       
/*  686 */       func_174826_a(getEntityBoundingBox().offset(0.0D, y, 0.0D));
/*  687 */       boolean var54 = !(!this.onGround && (var15 == y || var15 >= 0.0D));
/*      */       
/*      */       Iterator<AxisAlignedBB> var55;
/*      */       
/*  691 */       for (var55 = var53.iterator(); var55.hasNext(); x = var24.calculateXOffset(getEntityBoundingBox(), x))
/*      */       {
/*  693 */         AxisAlignedBB var24 = var55.next();
/*      */       }
/*      */       
/*  696 */       func_174826_a(getEntityBoundingBox().offset(x, 0.0D, 0.0D));
/*      */       
/*  698 */       for (var55 = var53.iterator(); var55.hasNext(); z = var24.calculateZOffset(getEntityBoundingBox(), z))
/*      */       {
/*  700 */         AxisAlignedBB var24 = var55.next();
/*      */       }
/*      */       
/*  703 */       func_174826_a(getEntityBoundingBox().offset(0.0D, 0.0D, z));
/*      */       
/*  705 */       if (this.stepHeight > 0.0F && var54 && (var13 != x || var17 != z)) {
/*      */         
/*  707 */         double var56 = x;
/*  708 */         double var25 = y;
/*  709 */         double var27 = z;
/*  710 */         AxisAlignedBB var29 = getEntityBoundingBox();
/*  711 */         func_174826_a(var21);
/*  712 */         y = this.stepHeight;
/*  713 */         List var30 = this.worldObj.getCollidingBoundingBoxes(this, getEntityBoundingBox().addCoord(var13, y, var17));
/*  714 */         AxisAlignedBB var31 = getEntityBoundingBox();
/*  715 */         AxisAlignedBB var32 = var31.addCoord(var13, 0.0D, var17);
/*  716 */         double var33 = y;
/*      */ 
/*      */         
/*  719 */         for (Iterator<AxisAlignedBB> var35 = var30.iterator(); var35.hasNext(); var33 = var36.calculateYOffset(var32, var33))
/*      */         {
/*  721 */           AxisAlignedBB var36 = var35.next();
/*      */         }
/*      */         
/*  724 */         var31 = var31.offset(0.0D, var33, 0.0D);
/*  725 */         double var67 = var13;
/*      */ 
/*      */         
/*  728 */         for (Iterator<AxisAlignedBB> var37 = var30.iterator(); var37.hasNext(); var67 = var38.calculateXOffset(var31, var67))
/*      */         {
/*  730 */           AxisAlignedBB var38 = var37.next();
/*      */         }
/*      */         
/*  733 */         var31 = var31.offset(var67, 0.0D, 0.0D);
/*  734 */         double var68 = var17;
/*      */ 
/*      */         
/*  737 */         for (Iterator<AxisAlignedBB> var39 = var30.iterator(); var39.hasNext(); var68 = var40.calculateZOffset(var31, var68))
/*      */         {
/*  739 */           AxisAlignedBB var40 = var39.next();
/*      */         }
/*      */         
/*  742 */         var31 = var31.offset(0.0D, 0.0D, var68);
/*  743 */         AxisAlignedBB var69 = getEntityBoundingBox();
/*  744 */         double var70 = y;
/*      */ 
/*      */         
/*  747 */         for (Iterator<AxisAlignedBB> var42 = var30.iterator(); var42.hasNext(); var70 = var43.calculateYOffset(var69, var70))
/*      */         {
/*  749 */           AxisAlignedBB var43 = var42.next();
/*      */         }
/*      */         
/*  752 */         var69 = var69.offset(0.0D, var70, 0.0D);
/*  753 */         double var71 = var13;
/*      */ 
/*      */         
/*  756 */         for (Iterator<AxisAlignedBB> var44 = var30.iterator(); var44.hasNext(); var71 = var45.calculateXOffset(var69, var71))
/*      */         {
/*  758 */           AxisAlignedBB var45 = var44.next();
/*      */         }
/*      */         
/*  761 */         var69 = var69.offset(var71, 0.0D, 0.0D);
/*  762 */         double var72 = var17;
/*      */ 
/*      */         
/*  765 */         for (Iterator<AxisAlignedBB> var46 = var30.iterator(); var46.hasNext(); var72 = var47.calculateZOffset(var69, var72))
/*      */         {
/*  767 */           AxisAlignedBB var47 = var46.next();
/*      */         }
/*      */         
/*  770 */         var69 = var69.offset(0.0D, 0.0D, var72);
/*  771 */         double var73 = var67 * var67 + var68 * var68;
/*  772 */         double var48 = var71 * var71 + var72 * var72;
/*      */         
/*  774 */         if (var73 > var48) {
/*      */           
/*  776 */           x = var67;
/*  777 */           z = var68;
/*  778 */           func_174826_a(var31);
/*      */         }
/*      */         else {
/*      */           
/*  782 */           x = var71;
/*  783 */           z = var72;
/*  784 */           func_174826_a(var69);
/*      */         } 
/*      */         
/*  787 */         y = -this.stepHeight;
/*      */ 
/*      */         
/*  790 */         for (Iterator<AxisAlignedBB> var50 = var30.iterator(); var50.hasNext(); y = var51.calculateYOffset(getEntityBoundingBox(), y))
/*      */         {
/*  792 */           AxisAlignedBB var51 = var50.next();
/*      */         }
/*      */         
/*  795 */         func_174826_a(getEntityBoundingBox().offset(0.0D, y, 0.0D));
/*      */         
/*  797 */         if (var56 * var56 + var27 * var27 >= x * x + z * z) {
/*      */           
/*  799 */           x = var56;
/*  800 */           y = var25;
/*  801 */           z = var27;
/*  802 */           func_174826_a(var29);
/*      */         } 
/*      */       } 
/*      */       
/*  806 */       this.worldObj.theProfiler.endSection();
/*  807 */       this.worldObj.theProfiler.startSection("rest");
/*  808 */       func_174829_m();
/*  809 */       this.isCollidedHorizontally = !(var13 == x && var17 == z);
/*  810 */       this.isCollidedVertically = (var15 != y);
/*  811 */       this.onGround = (this.isCollidedVertically && var15 < 0.0D);
/*  812 */       this.isCollided = !(!this.isCollidedHorizontally && !this.isCollidedVertically);
/*  813 */       int var57 = MathHelper.floor_double(this.posX);
/*  814 */       int var58 = MathHelper.floor_double(this.posY - 0.20000000298023224D);
/*  815 */       int var59 = MathHelper.floor_double(this.posZ);
/*  816 */       BlockPos var26 = new BlockPos(var57, var58, var59);
/*  817 */       Block var60 = this.worldObj.getBlockState(var26).getBlock();
/*      */       
/*  819 */       if (var60.getMaterial() == Material.air) {
/*      */         
/*  821 */         Block var28 = this.worldObj.getBlockState(var26.offsetDown()).getBlock();
/*      */         
/*  823 */         if (var28 instanceof net.minecraft.block.BlockFence || var28 instanceof net.minecraft.block.BlockWall || var28 instanceof net.minecraft.block.BlockFenceGate) {
/*      */           
/*  825 */           var60 = var28;
/*  826 */           var26 = var26.offsetDown();
/*      */         } 
/*      */       } 
/*      */       
/*  830 */       func_180433_a(y, this.onGround, var60, var26);
/*      */       
/*  832 */       if (var13 != x)
/*      */       {
/*  834 */         this.motionX = 0.0D;
/*      */       }
/*      */       
/*  837 */       if (var17 != z)
/*      */       {
/*  839 */         this.motionZ = 0.0D;
/*      */       }
/*      */       
/*  842 */       if (var15 != y)
/*      */       {
/*  844 */         var60.onLanded(this.worldObj, this);
/*      */       }
/*      */       
/*  847 */       if (canTriggerWalking() && !var19 && this.ridingEntity == null) {
/*      */         
/*  849 */         double var61 = this.posX - var7;
/*  850 */         double var64 = this.posY - var9;
/*  851 */         double var66 = this.posZ - var11;
/*      */         
/*  853 */         if (var60 != Blocks.ladder)
/*      */         {
/*  855 */           var64 = 0.0D;
/*      */         }
/*      */         
/*  858 */         if (var60 != null && this.onGround)
/*      */         {
/*  860 */           var60.onEntityCollidedWithBlock(this.worldObj, var26, this);
/*      */         }
/*      */         
/*  863 */         this.distanceWalkedModified = (float)(this.distanceWalkedModified + MathHelper.sqrt_double(var61 * var61 + var66 * var66) * 0.6D);
/*  864 */         this.distanceWalkedOnStepModified = (float)(this.distanceWalkedOnStepModified + MathHelper.sqrt_double(var61 * var61 + var64 * var64 + var66 * var66) * 0.6D);
/*      */         
/*  866 */         if (this.distanceWalkedOnStepModified > this.nextStepDistance && var60.getMaterial() != Material.air) {
/*      */           
/*  868 */           this.nextStepDistance = (int)this.distanceWalkedOnStepModified + 1;
/*      */           
/*  870 */           if (isInWater()) {
/*      */             
/*  872 */             float var34 = MathHelper.sqrt_double(this.motionX * this.motionX * 0.20000000298023224D + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224D) * 0.35F;
/*      */             
/*  874 */             if (var34 > 1.0F)
/*      */             {
/*  876 */               var34 = 1.0F;
/*      */             }
/*      */             
/*  879 */             playSound(getSwimSound(), var34, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/*      */           } 
/*      */           
/*  882 */           func_180429_a(var26, var60);
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  888 */         doBlockCollisions();
/*      */       }
/*  890 */       catch (Throwable var52) {
/*      */         
/*  892 */         CrashReport var63 = CrashReport.makeCrashReport(var52, "Checking entity block collision");
/*  893 */         CrashReportCategory var65 = var63.makeCategory("Entity being checked for collision");
/*  894 */         addEntityCrashInfo(var65);
/*  895 */         throw new ReportedException(var63);
/*      */       } 
/*      */       
/*  898 */       boolean var62 = isWet();
/*      */       
/*  900 */       if (this.worldObj.func_147470_e(getEntityBoundingBox().contract(0.001D, 0.001D, 0.001D))) {
/*      */         
/*  902 */         dealFireDamage(1);
/*      */         
/*  904 */         if (!var62)
/*      */         {
/*  906 */           this.fire++;
/*      */           
/*  908 */           if (this.fire == 0)
/*      */           {
/*  910 */             setFire(8);
/*      */           }
/*      */         }
/*      */       
/*  914 */       } else if (this.fire <= 0) {
/*      */         
/*  916 */         this.fire = -this.fireResistance;
/*      */       } 
/*      */       
/*  919 */       if (var62 && this.fire > 0) {
/*      */         
/*  921 */         playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/*  922 */         this.fire = -this.fireResistance;
/*      */       } 
/*      */       
/*  925 */       this.worldObj.theProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void func_174829_m() {
/*  931 */     this.posX = ((getEntityBoundingBox()).minX + (getEntityBoundingBox()).maxX) / 2.0D;
/*  932 */     this.posY = (getEntityBoundingBox()).minY;
/*  933 */     this.posZ = ((getEntityBoundingBox()).minZ + (getEntityBoundingBox()).maxZ) / 2.0D;
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getSwimSound() {
/*  938 */     return "game.neutral.swim";
/*      */   }
/*      */ 
/*      */   
/*      */   protected void doBlockCollisions() {
/*  943 */     BlockPos var1 = new BlockPos((getEntityBoundingBox()).minX + 0.001D, (getEntityBoundingBox()).minY + 0.001D, (getEntityBoundingBox()).minZ + 0.001D);
/*  944 */     BlockPos var2 = new BlockPos((getEntityBoundingBox()).maxX - 0.001D, (getEntityBoundingBox()).maxY - 0.001D, (getEntityBoundingBox()).maxZ - 0.001D);
/*      */     
/*  946 */     if (this.worldObj.isAreaLoaded(var1, var2))
/*      */     {
/*  948 */       for (int var3 = var1.getX(); var3 <= var2.getX(); var3++) {
/*      */         
/*  950 */         for (int var4 = var1.getY(); var4 <= var2.getY(); var4++) {
/*      */           
/*  952 */           for (int var5 = var1.getZ(); var5 <= var2.getZ(); var5++) {
/*      */             
/*  954 */             BlockPos var6 = new BlockPos(var3, var4, var5);
/*  955 */             IBlockState var7 = this.worldObj.getBlockState(var6);
/*      */ 
/*      */             
/*      */             try {
/*  959 */               var7.getBlock().onEntityCollidedWithBlock(this.worldObj, var6, var7, this);
/*      */             }
/*  961 */             catch (Throwable var11) {
/*      */               
/*  963 */               CrashReport var9 = CrashReport.makeCrashReport(var11, "Colliding entity with block");
/*  964 */               CrashReportCategory var10 = var9.makeCategory("Block being collided with");
/*  965 */               CrashReportCategory.addBlockInfo(var10, var6, var7);
/*  966 */               throw new ReportedException(var9);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/*  976 */     Block.SoundType var3 = p_180429_2_.stepSound;
/*      */     
/*  978 */     if (this.worldObj.getBlockState(p_180429_1_.offsetUp()).getBlock() == Blocks.snow_layer) {
/*      */       
/*  980 */       var3 = Blocks.snow_layer.stepSound;
/*  981 */       playSound(var3.getStepSound(), var3.getVolume() * 0.15F, var3.getFrequency());
/*      */     }
/*  983 */     else if (!p_180429_2_.getMaterial().isLiquid()) {
/*      */       
/*  985 */       playSound(var3.getStepSound(), var3.getVolume() * 0.15F, var3.getFrequency());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void playSound(String name, float volume, float pitch) {
/*  991 */     if (!isSlient())
/*      */     {
/*  993 */       this.worldObj.playSoundAtEntity(this, name, volume, pitch);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSlient() {
/* 1002 */     return (this.dataWatcher.getWatchableObjectByte(4) == 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174810_b(boolean p_174810_1_) {
/* 1007 */     this.dataWatcher.updateObject(4, Byte.valueOf((byte)(p_174810_1_ ? 1 : 0)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canTriggerWalking() {
/* 1016 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_180433_a(double p_180433_1_, boolean p_180433_3_, Block p_180433_4_, BlockPos p_180433_5_) {
/* 1021 */     if (p_180433_3_) {
/*      */       
/* 1023 */       if (this.fallDistance > 0.0F)
/*      */       {
/* 1025 */         if (p_180433_4_ != null) {
/*      */           
/* 1027 */           p_180433_4_.onFallenUpon(this.worldObj, p_180433_5_, this, this.fallDistance);
/*      */         }
/*      */         else {
/*      */           
/* 1031 */           fall(this.fallDistance, 1.0F);
/*      */         } 
/*      */         
/* 1034 */         this.fallDistance = 0.0F;
/*      */       }
/*      */     
/* 1037 */     } else if (p_180433_1_ < 0.0D) {
/*      */       
/* 1039 */       this.fallDistance = (float)(this.fallDistance - p_180433_1_);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getBoundingBox() {
/* 1048 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void dealFireDamage(int amount) {
/* 1057 */     if (!this.isImmuneToFire)
/*      */     {
/* 1059 */       attackEntityFrom(DamageSource.inFire, amount);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean isImmuneToFire() {
/* 1065 */     return this.isImmuneToFire;
/*      */   }
/*      */ 
/*      */   
/*      */   public void fall(float distance, float damageMultiplier) {
/* 1070 */     if (this.riddenByEntity != null)
/*      */     {
/* 1072 */       this.riddenByEntity.fall(distance, damageMultiplier);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWet() {
/* 1081 */     return !(!this.inWater && !this.worldObj.func_175727_C(new BlockPos(this.posX, this.posY, this.posZ)) && !this.worldObj.func_175727_C(new BlockPos(this.posX, this.posY + this.height, this.posZ)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInWater() {
/* 1090 */     return this.inWater;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean handleWaterMovement() {
/* 1098 */     if (this.worldObj.handleMaterialAcceleration(getEntityBoundingBox().expand(0.0D, -0.4000000059604645D, 0.0D).contract(0.001D, 0.001D, 0.001D), Material.water, this)) {
/*      */       
/* 1100 */       if (!this.inWater && !this.firstUpdate)
/*      */       {
/* 1102 */         resetHeight();
/*      */       }
/*      */       
/* 1105 */       this.fallDistance = 0.0F;
/* 1106 */       this.inWater = true;
/* 1107 */       this.fire = 0;
/*      */     }
/*      */     else {
/*      */       
/* 1111 */       this.inWater = false;
/*      */     } 
/*      */     
/* 1114 */     return this.inWater;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void resetHeight() {
/* 1122 */     float var1 = MathHelper.sqrt_double(this.motionX * this.motionX * 0.20000000298023224D + this.motionY * this.motionY + this.motionZ * this.motionZ * 0.20000000298023224D) * 0.2F;
/*      */     
/* 1124 */     if (var1 > 1.0F)
/*      */     {
/* 1126 */       var1 = 1.0F;
/*      */     }
/*      */     
/* 1129 */     playSound(getSplashSound(), var1, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/* 1130 */     float var2 = MathHelper.floor_double((getEntityBoundingBox()).minY);
/*      */ 
/*      */     
/*      */     int var3;
/*      */     
/* 1135 */     for (var3 = 0; var3 < 1.0F + this.width * 20.0F; var3++) {
/*      */       
/* 1137 */       float var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 1138 */       float var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 1139 */       this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX + var4, (var2 + 1.0F), this.posZ + var5, this.motionX, this.motionY - (this.rand.nextFloat() * 0.2F), this.motionZ, new int[0]);
/*      */     } 
/*      */     
/* 1142 */     for (var3 = 0; var3 < 1.0F + this.width * 20.0F; var3++) {
/*      */       
/* 1144 */       float var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 1145 */       float var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 1146 */       this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX + var4, (var2 + 1.0F), this.posZ + var5, this.motionX, this.motionY, this.motionZ, new int[0]);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174830_Y() {
/* 1152 */     if (isSprinting() && !isInWater())
/*      */     {
/* 1154 */       func_174808_Z();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_174808_Z() {
/* 1160 */     int var1 = MathHelper.floor_double(this.posX);
/* 1161 */     int var2 = MathHelper.floor_double(this.posY - 0.20000000298023224D);
/* 1162 */     int var3 = MathHelper.floor_double(this.posZ);
/* 1163 */     BlockPos var4 = new BlockPos(var1, var2, var3);
/* 1164 */     IBlockState var5 = this.worldObj.getBlockState(var4);
/* 1165 */     Block var6 = var5.getBlock();
/*      */     
/* 1167 */     if (var6.getRenderType() != -1)
/*      */     {
/* 1169 */       this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextFloat() - 0.5D) * this.width, (getEntityBoundingBox()).minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, -this.motionX * 4.0D, 1.5D, -this.motionZ * 4.0D, new int[] { Block.getStateId(var5) });
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected String getSplashSound() {
/* 1175 */     return "game.neutral.swim.splash";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInsideOfMaterial(Material materialIn) {
/* 1183 */     double var2 = this.posY + getEyeHeight();
/* 1184 */     BlockPos var4 = new BlockPos(this.posX, var2, this.posZ);
/* 1185 */     IBlockState var5 = this.worldObj.getBlockState(var4);
/* 1186 */     Block var6 = var5.getBlock();
/*      */     
/* 1188 */     if (var6.getMaterial() == materialIn) {
/*      */       
/* 1190 */       float var7 = BlockLiquid.getLiquidHeightPercent(var5.getBlock().getMetaFromState(var5)) - 0.11111111F;
/* 1191 */       float var8 = (var4.getY() + 1) - var7;
/* 1192 */       boolean var9 = (var2 < var8);
/* 1193 */       return (!var9 && this instanceof EntityPlayer) ? false : var9;
/*      */     } 
/*      */ 
/*      */     
/* 1197 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_180799_ab() {
/* 1203 */     return this.worldObj.isMaterialInBB(getEntityBoundingBox().expand(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), Material.lava);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void moveFlying(float strafe, float forward, float friction) {
/* 1211 */     float var4 = strafe * strafe + forward * forward;
/*      */     
/* 1213 */     if (var4 >= 1.0E-4F) {
/*      */       
/* 1215 */       var4 = MathHelper.sqrt_float(var4);
/*      */       
/* 1217 */       if (var4 < 1.0F)
/*      */       {
/* 1219 */         var4 = 1.0F;
/*      */       }
/*      */       
/* 1222 */       var4 = friction / var4;
/* 1223 */       strafe *= var4;
/* 1224 */       forward *= var4;
/* 1225 */       float var5 = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F);
/* 1226 */       float var6 = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F);
/* 1227 */       this.motionX += (strafe * var6 - forward * var5);
/* 1228 */       this.motionZ += (forward * var6 + strafe * var5);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBrightnessForRender(float p_70070_1_) {
/* 1234 */     BlockPos var2 = new BlockPos(this.posX, 0.0D, this.posZ);
/*      */     
/* 1236 */     if (this.worldObj.isBlockLoaded(var2)) {
/*      */       
/* 1238 */       double var3 = ((getEntityBoundingBox()).maxY - (getEntityBoundingBox()).minY) * 0.66D;
/* 1239 */       int var5 = MathHelper.floor_double(this.posY + var3);
/* 1240 */       return this.worldObj.getCombinedLight(var2.offsetUp(var5), 0);
/*      */     } 
/*      */ 
/*      */     
/* 1244 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getBrightness(float p_70013_1_) {
/* 1253 */     BlockPos var2 = new BlockPos(this.posX, 0.0D, this.posZ);
/*      */     
/* 1255 */     if (this.worldObj.isBlockLoaded(var2)) {
/*      */       
/* 1257 */       double var3 = ((getEntityBoundingBox()).maxY - (getEntityBoundingBox()).minY) * 0.66D;
/* 1258 */       int var5 = MathHelper.floor_double(this.posY + var3);
/* 1259 */       return this.worldObj.getLightBrightness(var2.offsetUp(var5));
/*      */     } 
/*      */ 
/*      */     
/* 1263 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setWorld(World worldIn) {
/* 1272 */     this.worldObj = worldIn;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch) {
/* 1280 */     this.prevPosX = this.posX = x;
/* 1281 */     this.prevPosY = this.posY = y;
/* 1282 */     this.prevPosZ = this.posZ = z;
/* 1283 */     this.prevRotationYaw = this.rotationYaw = yaw;
/* 1284 */     this.prevRotationPitch = this.rotationPitch = pitch;
/* 1285 */     double var9 = (this.prevRotationYaw - yaw);
/*      */     
/* 1287 */     if (var9 < -180.0D)
/*      */     {
/* 1289 */       this.prevRotationYaw += 360.0F;
/*      */     }
/*      */     
/* 1292 */     if (var9 >= 180.0D)
/*      */     {
/* 1294 */       this.prevRotationYaw -= 360.0F;
/*      */     }
/*      */     
/* 1297 */     setPosition(this.posX, this.posY, this.posZ);
/* 1298 */     setRotation(yaw, pitch);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174828_a(BlockPos p_174828_1_, float p_174828_2_, float p_174828_3_) {
/* 1303 */     setLocationAndAngles(p_174828_1_.getX() + 0.5D, p_174828_1_.getY(), p_174828_1_.getZ() + 0.5D, p_174828_2_, p_174828_3_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch) {
/* 1311 */     this.lastTickPosX = this.prevPosX = this.posX = x;
/* 1312 */     this.lastTickPosY = this.prevPosY = this.posY = y;
/* 1313 */     this.lastTickPosZ = this.prevPosZ = this.posZ = z;
/* 1314 */     this.rotationYaw = yaw;
/* 1315 */     this.rotationPitch = pitch;
/* 1316 */     setPosition(this.posX, this.posY, this.posZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getDistanceToEntity(Entity entityIn) {
/* 1324 */     float var2 = (float)(this.posX - entityIn.posX);
/* 1325 */     float var3 = (float)(this.posY - entityIn.posY);
/* 1326 */     float var4 = (float)(this.posZ - entityIn.posZ);
/* 1327 */     return MathHelper.sqrt_float(var2 * var2 + var3 * var3 + var4 * var4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDistanceSq(double x, double y, double z) {
/* 1335 */     double var7 = this.posX - x;
/* 1336 */     double var9 = this.posY - y;
/* 1337 */     double var11 = this.posZ - z;
/* 1338 */     return var7 * var7 + var9 * var9 + var11 * var11;
/*      */   }
/*      */ 
/*      */   
/*      */   public double getDistanceSq(BlockPos p_174818_1_) {
/* 1343 */     return p_174818_1_.distanceSq(this.posX, this.posY, this.posZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public double func_174831_c(BlockPos p_174831_1_) {
/* 1348 */     return p_174831_1_.distanceSqToCenter(this.posX, this.posY, this.posZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDistance(double x, double y, double z) {
/* 1356 */     double var7 = this.posX - x;
/* 1357 */     double var9 = this.posY - y;
/* 1358 */     double var11 = this.posZ - z;
/* 1359 */     return MathHelper.sqrt_double(var7 * var7 + var9 * var9 + var11 * var11);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getDistanceSqToEntity(Entity entityIn) {
/* 1367 */     double var2 = this.posX - entityIn.posX;
/* 1368 */     double var4 = this.posY - entityIn.posY;
/* 1369 */     double var6 = this.posZ - entityIn.posZ;
/* 1370 */     return var2 * var2 + var4 * var4 + var6 * var6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onCollideWithPlayer(EntityPlayer entityIn) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void applyEntityCollision(Entity entityIn) {
/* 1383 */     if (entityIn.riddenByEntity != this && entityIn.ridingEntity != this)
/*      */     {
/* 1385 */       if (!entityIn.noClip && !this.noClip) {
/*      */         
/* 1387 */         double var2 = entityIn.posX - this.posX;
/* 1388 */         double var4 = entityIn.posZ - this.posZ;
/* 1389 */         double var6 = MathHelper.abs_max(var2, var4);
/*      */         
/* 1391 */         if (var6 >= 0.009999999776482582D) {
/*      */           
/* 1393 */           var6 = MathHelper.sqrt_double(var6);
/* 1394 */           var2 /= var6;
/* 1395 */           var4 /= var6;
/* 1396 */           double var8 = 1.0D / var6;
/*      */           
/* 1398 */           if (var8 > 1.0D)
/*      */           {
/* 1400 */             var8 = 1.0D;
/*      */           }
/*      */           
/* 1403 */           var2 *= var8;
/* 1404 */           var4 *= var8;
/* 1405 */           var2 *= 0.05000000074505806D;
/* 1406 */           var4 *= 0.05000000074505806D;
/* 1407 */           var2 *= (1.0F - this.entityCollisionReduction);
/* 1408 */           var4 *= (1.0F - this.entityCollisionReduction);
/*      */           
/* 1410 */           if (this.riddenByEntity == null)
/*      */           {
/* 1412 */             addVelocity(-var2, 0.0D, -var4);
/*      */           }
/*      */           
/* 1415 */           if (entityIn.riddenByEntity == null)
/*      */           {
/* 1417 */             entityIn.addVelocity(var2, 0.0D, var4);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addVelocity(double x, double y, double z) {
/* 1429 */     this.motionX += x;
/* 1430 */     this.motionY += y;
/* 1431 */     this.motionZ += z;
/* 1432 */     this.isAirBorne = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setBeenAttacked() {
/* 1440 */     this.velocityChanged = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 1448 */     if (func_180431_b(source))
/*      */     {
/* 1450 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1454 */     setBeenAttacked();
/* 1455 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getLook(float p_70676_1_) {
/* 1464 */     if (p_70676_1_ == 1.0F)
/*      */     {
/* 1466 */       return func_174806_f(this.rotationPitch, this.rotationYaw);
/*      */     }
/*      */ 
/*      */     
/* 1470 */     float var2 = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * p_70676_1_;
/* 1471 */     float var3 = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * p_70676_1_;
/* 1472 */     return func_174806_f(var2, var3);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected final Vec3 func_174806_f(float p_174806_1_, float p_174806_2_) {
/* 1478 */     float var3 = MathHelper.cos(-p_174806_2_ * 0.017453292F - 3.1415927F);
/* 1479 */     float var4 = MathHelper.sin(-p_174806_2_ * 0.017453292F - 3.1415927F);
/* 1480 */     float var5 = -MathHelper.cos(-p_174806_1_ * 0.017453292F);
/* 1481 */     float var6 = MathHelper.sin(-p_174806_1_ * 0.017453292F);
/* 1482 */     return new Vec3((var4 * var5), var6, (var3 * var5));
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 func_174824_e(float p_174824_1_) {
/* 1487 */     if (p_174824_1_ == 1.0F)
/*      */     {
/* 1489 */       return new Vec3(this.posX, this.posY + getEyeHeight(), this.posZ);
/*      */     }
/*      */ 
/*      */     
/* 1493 */     double var2 = this.prevPosX + (this.posX - this.prevPosX) * p_174824_1_;
/* 1494 */     double var4 = this.prevPosY + (this.posY - this.prevPosY) * p_174824_1_ + getEyeHeight();
/* 1495 */     double var6 = this.prevPosZ + (this.posZ - this.prevPosZ) * p_174824_1_;
/* 1496 */     return new Vec3(var2, var4, var6);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public MovingObjectPosition func_174822_a(double p_174822_1_, float p_174822_3_) {
/* 1502 */     Vec3 var4 = func_174824_e(p_174822_3_);
/* 1503 */     Vec3 var5 = getLook(p_174822_3_);
/* 1504 */     Vec3 var6 = var4.addVector(var5.xCoord * p_174822_1_, var5.yCoord * p_174822_1_, var5.zCoord * p_174822_1_);
/* 1505 */     return this.worldObj.rayTraceBlocks(var4, var6, false, false, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBeCollidedWith() {
/* 1513 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canBePushed() {
/* 1521 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addToPlayerScore(Entity entityIn, int amount) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInRangeToRender3d(double x, double y, double z) {
/* 1532 */     double var7 = this.posX - x;
/* 1533 */     double var9 = this.posY - y;
/* 1534 */     double var11 = this.posZ - z;
/* 1535 */     double var13 = var7 * var7 + var9 * var9 + var11 * var11;
/* 1536 */     return isInRangeToRenderDist(var13);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInRangeToRenderDist(double distance) {
/* 1545 */     double var3 = getEntityBoundingBox().getAverageEdgeLength();
/* 1546 */     var3 *= 64.0D * this.renderDistanceWeight;
/* 1547 */     return (distance < var3 * var3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean writeMountToNBT(NBTTagCompound tagCompund) {
/* 1556 */     String var2 = getEntityString();
/*      */     
/* 1558 */     if (!this.isDead && var2 != null) {
/*      */       
/* 1560 */       tagCompund.setString("id", var2);
/* 1561 */       writeToNBT(tagCompund);
/* 1562 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1566 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean writeToNBTOptional(NBTTagCompound tagCompund) {
/* 1577 */     String var2 = getEntityString();
/*      */     
/* 1579 */     if (!this.isDead && var2 != null && this.riddenByEntity == null) {
/*      */       
/* 1581 */       tagCompund.setString("id", var2);
/* 1582 */       writeToNBT(tagCompund);
/* 1583 */       return true;
/*      */     } 
/*      */ 
/*      */     
/* 1587 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeToNBT(NBTTagCompound tagCompund) {
/*      */     try {
/* 1598 */       tagCompund.setTag("Pos", (NBTBase)newDoubleNBTList(new double[] { this.posX, this.posY, this.posZ }));
/* 1599 */       tagCompund.setTag("Motion", (NBTBase)newDoubleNBTList(new double[] { this.motionX, this.motionY, this.motionZ }));
/* 1600 */       tagCompund.setTag("Rotation", (NBTBase)newFloatNBTList(new float[] { this.rotationYaw, this.rotationPitch }));
/* 1601 */       tagCompund.setFloat("FallDistance", this.fallDistance);
/* 1602 */       tagCompund.setShort("Fire", (short)this.fire);
/* 1603 */       tagCompund.setShort("Air", (short)getAir());
/* 1604 */       tagCompund.setBoolean("OnGround", this.onGround);
/* 1605 */       tagCompund.setInteger("Dimension", this.dimension);
/* 1606 */       tagCompund.setBoolean("Invulnerable", this.invulnerable);
/* 1607 */       tagCompund.setInteger("PortalCooldown", this.timeUntilPortal);
/* 1608 */       tagCompund.setLong("UUIDMost", getUniqueID().getMostSignificantBits());
/* 1609 */       tagCompund.setLong("UUIDLeast", getUniqueID().getLeastSignificantBits());
/*      */       
/* 1611 */       if (getCustomNameTag() != null && getCustomNameTag().length() > 0) {
/*      */         
/* 1613 */         tagCompund.setString("CustomName", getCustomNameTag());
/* 1614 */         tagCompund.setBoolean("CustomNameVisible", getAlwaysRenderNameTag());
/*      */       } 
/*      */       
/* 1617 */       this.field_174837_as.func_179670_b(tagCompund);
/*      */       
/* 1619 */       if (isSlient())
/*      */       {
/* 1621 */         tagCompund.setBoolean("Silent", isSlient());
/*      */       }
/*      */       
/* 1624 */       writeEntityToNBT(tagCompund);
/*      */       
/* 1626 */       if (this.ridingEntity != null)
/*      */       {
/* 1628 */         NBTTagCompound var2 = new NBTTagCompound();
/*      */         
/* 1630 */         if (this.ridingEntity.writeMountToNBT(var2))
/*      */         {
/* 1632 */           tagCompund.setTag("Riding", (NBTBase)var2);
/*      */         }
/*      */       }
/*      */     
/* 1636 */     } catch (Throwable var5) {
/*      */       
/* 1638 */       CrashReport var3 = CrashReport.makeCrashReport(var5, "Saving entity NBT");
/* 1639 */       CrashReportCategory var4 = var3.makeCategory("Entity being saved");
/* 1640 */       addEntityCrashInfo(var4);
/* 1641 */       throw new ReportedException(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readFromNBT(NBTTagCompound tagCompund) {
/*      */     try {
/* 1652 */       NBTTagList var2 = tagCompund.getTagList("Pos", 6);
/* 1653 */       NBTTagList var6 = tagCompund.getTagList("Motion", 6);
/* 1654 */       NBTTagList var7 = tagCompund.getTagList("Rotation", 5);
/* 1655 */       this.motionX = var6.getDouble(0);
/* 1656 */       this.motionY = var6.getDouble(1);
/* 1657 */       this.motionZ = var6.getDouble(2);
/*      */       
/* 1659 */       if (Math.abs(this.motionX) > 10.0D)
/*      */       {
/* 1661 */         this.motionX = 0.0D;
/*      */       }
/*      */       
/* 1664 */       if (Math.abs(this.motionY) > 10.0D)
/*      */       {
/* 1666 */         this.motionY = 0.0D;
/*      */       }
/*      */       
/* 1669 */       if (Math.abs(this.motionZ) > 10.0D)
/*      */       {
/* 1671 */         this.motionZ = 0.0D;
/*      */       }
/*      */       
/* 1674 */       this.prevPosX = this.lastTickPosX = this.posX = var2.getDouble(0);
/* 1675 */       this.prevPosY = this.lastTickPosY = this.posY = var2.getDouble(1);
/* 1676 */       this.prevPosZ = this.lastTickPosZ = this.posZ = var2.getDouble(2);
/* 1677 */       this.prevRotationYaw = this.rotationYaw = var7.getFloat(0);
/* 1678 */       this.prevRotationPitch = this.rotationPitch = var7.getFloat(1);
/* 1679 */       this.fallDistance = tagCompund.getFloat("FallDistance");
/* 1680 */       this.fire = tagCompund.getShort("Fire");
/* 1681 */       setAir(tagCompund.getShort("Air"));
/* 1682 */       this.onGround = tagCompund.getBoolean("OnGround");
/* 1683 */       this.dimension = tagCompund.getInteger("Dimension");
/* 1684 */       this.invulnerable = tagCompund.getBoolean("Invulnerable");
/* 1685 */       this.timeUntilPortal = tagCompund.getInteger("PortalCooldown");
/*      */       
/* 1687 */       if (tagCompund.hasKey("UUIDMost", 4) && tagCompund.hasKey("UUIDLeast", 4)) {
/*      */         
/* 1689 */         this.entityUniqueID = new UUID(tagCompund.getLong("UUIDMost"), tagCompund.getLong("UUIDLeast"));
/*      */       }
/* 1691 */       else if (tagCompund.hasKey("UUID", 8)) {
/*      */         
/* 1693 */         this.entityUniqueID = UUID.fromString(tagCompund.getString("UUID"));
/*      */       } 
/*      */       
/* 1696 */       setPosition(this.posX, this.posY, this.posZ);
/* 1697 */       setRotation(this.rotationYaw, this.rotationPitch);
/*      */       
/* 1699 */       if (tagCompund.hasKey("CustomName", 8) && tagCompund.getString("CustomName").length() > 0)
/*      */       {
/* 1701 */         setCustomNameTag(tagCompund.getString("CustomName"));
/*      */       }
/*      */       
/* 1704 */       setAlwaysRenderNameTag(tagCompund.getBoolean("CustomNameVisible"));
/* 1705 */       this.field_174837_as.func_179668_a(tagCompund);
/* 1706 */       func_174810_b(tagCompund.getBoolean("Silent"));
/* 1707 */       readEntityFromNBT(tagCompund);
/*      */       
/* 1709 */       if (shouldSetPosAfterLoading())
/*      */       {
/* 1711 */         setPosition(this.posX, this.posY, this.posZ);
/*      */       }
/*      */     }
/* 1714 */     catch (Throwable var5) {
/*      */       
/* 1716 */       CrashReport var3 = CrashReport.makeCrashReport(var5, "Loading entity NBT");
/* 1717 */       CrashReportCategory var4 = var3.makeCategory("Entity being loaded");
/* 1718 */       addEntityCrashInfo(var4);
/* 1719 */       throw new ReportedException(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean shouldSetPosAfterLoading() {
/* 1725 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected final String getEntityString() {
/* 1733 */     return EntityList.getEntityString(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void readEntityFromNBT(NBTTagCompound paramNBTTagCompound);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract void writeEntityToNBT(NBTTagCompound paramNBTTagCompound);
/*      */ 
/*      */ 
/*      */   
/*      */   public void onChunkLoad() {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected NBTTagList newDoubleNBTList(double... numbers) {
/* 1753 */     NBTTagList var2 = new NBTTagList();
/* 1754 */     double[] var3 = numbers;
/* 1755 */     int var4 = numbers.length;
/*      */     
/* 1757 */     for (int var5 = 0; var5 < var4; var5++) {
/*      */       
/* 1759 */       double var6 = var3[var5];
/* 1760 */       var2.appendTag((NBTBase)new NBTTagDouble(var6));
/*      */     } 
/*      */     
/* 1763 */     return var2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected NBTTagList newFloatNBTList(float... numbers) {
/* 1771 */     NBTTagList var2 = new NBTTagList();
/* 1772 */     float[] var3 = numbers;
/* 1773 */     int var4 = numbers.length;
/*      */     
/* 1775 */     for (int var5 = 0; var5 < var4; var5++) {
/*      */       
/* 1777 */       float var6 = var3[var5];
/* 1778 */       var2.appendTag((NBTBase)new NBTTagFloat(var6));
/*      */     } 
/*      */     
/* 1781 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityItem dropItem(Item itemIn, int size) {
/* 1786 */     return dropItemWithOffset(itemIn, size, 0.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityItem dropItemWithOffset(Item itemIn, int size, float p_145778_3_) {
/* 1791 */     return entityDropItem(new ItemStack(itemIn, size, 0), p_145778_3_);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityItem entityDropItem(ItemStack itemStackIn, float offsetY) {
/* 1799 */     if (itemStackIn.stackSize != 0 && itemStackIn.getItem() != null) {
/*      */       
/* 1801 */       EntityItem var3 = new EntityItem(this.worldObj, this.posX, this.posY + offsetY, this.posZ, itemStackIn);
/* 1802 */       var3.setDefaultPickupDelay();
/* 1803 */       this.worldObj.spawnEntityInWorld((Entity)var3);
/* 1804 */       return var3;
/*      */     } 
/*      */ 
/*      */     
/* 1808 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityAlive() {
/* 1817 */     return !this.isDead;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityInsideOpaqueBlock() {
/* 1825 */     if (this.noClip)
/*      */     {
/* 1827 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1831 */     for (int var1 = 0; var1 < 8; var1++) {
/*      */       
/* 1833 */       double var2 = this.posX + ((((var1 >> 0) % 2) - 0.5F) * this.width * 0.8F);
/* 1834 */       double var4 = this.posY + ((((var1 >> 1) % 2) - 0.5F) * 0.1F);
/* 1835 */       double var6 = this.posZ + ((((var1 >> 2) % 2) - 0.5F) * this.width * 0.8F);
/*      */       
/* 1837 */       if (this.worldObj.getBlockState(new BlockPos(var2, var4 + getEyeHeight(), var6)).getBlock().isVisuallyOpaque())
/*      */       {
/* 1839 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1843 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean interactFirst(EntityPlayer playerIn) {
/* 1852 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getCollisionBox(Entity entityIn) {
/* 1861 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateRidden() {
/* 1869 */     if (this.ridingEntity.isDead) {
/*      */       
/* 1871 */       this.ridingEntity = null;
/*      */     }
/*      */     else {
/*      */       
/* 1875 */       this.motionX = 0.0D;
/* 1876 */       this.motionY = 0.0D;
/* 1877 */       this.motionZ = 0.0D;
/* 1878 */       onUpdate();
/*      */       
/* 1880 */       if (this.ridingEntity != null) {
/*      */         
/* 1882 */         this.ridingEntity.updateRiderPosition();
/* 1883 */         this.entityRiderYawDelta += (this.ridingEntity.rotationYaw - this.ridingEntity.prevRotationYaw);
/*      */         
/* 1885 */         for (this.entityRiderPitchDelta += (this.ridingEntity.rotationPitch - this.ridingEntity.prevRotationPitch); this.entityRiderYawDelta >= 180.0D; this.entityRiderYawDelta -= 360.0D);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1890 */         while (this.entityRiderYawDelta < -180.0D)
/*      */         {
/* 1892 */           this.entityRiderYawDelta += 360.0D;
/*      */         }
/*      */         
/* 1895 */         while (this.entityRiderPitchDelta >= 180.0D)
/*      */         {
/* 1897 */           this.entityRiderPitchDelta -= 360.0D;
/*      */         }
/*      */         
/* 1900 */         while (this.entityRiderPitchDelta < -180.0D)
/*      */         {
/* 1902 */           this.entityRiderPitchDelta += 360.0D;
/*      */         }
/*      */         
/* 1905 */         double var1 = this.entityRiderYawDelta * 0.5D;
/* 1906 */         double var3 = this.entityRiderPitchDelta * 0.5D;
/* 1907 */         float var5 = 10.0F;
/*      */         
/* 1909 */         if (var1 > var5)
/*      */         {
/* 1911 */           var1 = var5;
/*      */         }
/*      */         
/* 1914 */         if (var1 < -var5)
/*      */         {
/* 1916 */           var1 = -var5;
/*      */         }
/*      */         
/* 1919 */         if (var3 > var5)
/*      */         {
/* 1921 */           var3 = var5;
/*      */         }
/*      */         
/* 1924 */         if (var3 < -var5)
/*      */         {
/* 1926 */           var3 = -var5;
/*      */         }
/*      */         
/* 1929 */         this.entityRiderYawDelta -= var1;
/* 1930 */         this.entityRiderPitchDelta -= var3;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateRiderPosition() {
/* 1937 */     if (this.riddenByEntity != null)
/*      */     {
/* 1939 */       this.riddenByEntity.setPosition(this.posX, this.posY + getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getYOffset() {
/* 1948 */     return 0.0D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMountedYOffset() {
/* 1956 */     return this.height * 0.75D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void mountEntity(Entity entityIn) {
/* 1964 */     this.entityRiderPitchDelta = 0.0D;
/* 1965 */     this.entityRiderYawDelta = 0.0D;
/*      */     
/* 1967 */     if (entityIn == null) {
/*      */       
/* 1969 */       if (this.ridingEntity != null) {
/*      */         
/* 1971 */         setLocationAndAngles(this.ridingEntity.posX, (this.ridingEntity.getEntityBoundingBox()).minY + this.ridingEntity.height, this.ridingEntity.posZ, this.rotationYaw, this.rotationPitch);
/* 1972 */         this.ridingEntity.riddenByEntity = null;
/*      */       } 
/*      */       
/* 1975 */       this.ridingEntity = null;
/*      */     }
/*      */     else {
/*      */       
/* 1979 */       if (this.ridingEntity != null)
/*      */       {
/* 1981 */         this.ridingEntity.riddenByEntity = null;
/*      */       }
/*      */       
/* 1984 */       if (entityIn != null)
/*      */       {
/* 1986 */         for (Entity var2 = entityIn.ridingEntity; var2 != null; var2 = var2.ridingEntity) {
/*      */           
/* 1988 */           if (var2 == this) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/* 1995 */       this.ridingEntity = entityIn;
/* 1996 */       entityIn.riddenByEntity = this;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180426_a(double p_180426_1_, double p_180426_3_, double p_180426_5_, float p_180426_7_, float p_180426_8_, int p_180426_9_, boolean p_180426_10_) {
/* 2002 */     setPosition(p_180426_1_, p_180426_3_, p_180426_5_);
/* 2003 */     setRotation(p_180426_7_, p_180426_8_);
/* 2004 */     List var11 = this.worldObj.getCollidingBoundingBoxes(this, getEntityBoundingBox().contract(0.03125D, 0.0D, 0.03125D));
/*      */     
/* 2006 */     if (!var11.isEmpty()) {
/*      */       
/* 2008 */       double var12 = 0.0D;
/* 2009 */       Iterator<AxisAlignedBB> var14 = var11.iterator();
/*      */       
/* 2011 */       while (var14.hasNext()) {
/*      */         
/* 2013 */         AxisAlignedBB var15 = var14.next();
/*      */         
/* 2015 */         if (var15.maxY > var12)
/*      */         {
/* 2017 */           var12 = var15.maxY;
/*      */         }
/*      */       } 
/*      */       
/* 2021 */       p_180426_3_ += var12 - (getEntityBoundingBox()).minY;
/* 2022 */       setPosition(p_180426_1_, p_180426_3_, p_180426_5_);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getCollisionBorderSize() {
/* 2028 */     return 0.1F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getLookVec() {
/* 2036 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInPortal() {
/* 2044 */     if (this.timeUntilPortal > 0) {
/*      */       
/* 2046 */       this.timeUntilPortal = getPortalCooldown();
/*      */     }
/*      */     else {
/*      */       
/* 2050 */       double var1 = this.prevPosX - this.posX;
/* 2051 */       double var3 = this.prevPosZ - this.posZ;
/*      */       
/* 2053 */       if (!this.worldObj.isRemote && !this.inPortal) {
/*      */         int var5;
/*      */ 
/*      */         
/* 2057 */         if (MathHelper.abs((float)var1) > MathHelper.abs((float)var3)) {
/*      */           
/* 2059 */           var5 = (var1 > 0.0D) ? EnumFacing.WEST.getHorizontalIndex() : EnumFacing.EAST.getHorizontalIndex();
/*      */         }
/*      */         else {
/*      */           
/* 2063 */           var5 = (var3 > 0.0D) ? EnumFacing.NORTH.getHorizontalIndex() : EnumFacing.SOUTH.getHorizontalIndex();
/*      */         } 
/*      */         
/* 2066 */         this.teleportDirection = var5;
/*      */       } 
/*      */       
/* 2069 */       this.inPortal = true;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPortalCooldown() {
/* 2078 */     return 300;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVelocity(double x, double y, double z) {
/* 2086 */     this.motionX = x;
/* 2087 */     this.motionY = y;
/* 2088 */     this.motionZ = z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(byte p_70103_1_) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void performHurtAnimation() {}
/*      */ 
/*      */ 
/*      */   
/*      */   public ItemStack[] getInventory() {
/* 2103 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCurrentItemOrArmor(int slotIn, ItemStack itemStackIn) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBurning() {
/* 2116 */     boolean var1 = (this.worldObj != null && this.worldObj.isRemote);
/* 2117 */     return (!this.isImmuneToFire && (this.fire > 0 || (var1 && getFlag(0))));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRiding() {
/* 2126 */     return (this.ridingEntity != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSneaking() {
/* 2134 */     return getFlag(1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSneaking(boolean sneaking) {
/* 2142 */     setFlag(1, sneaking);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSprinting() {
/* 2150 */     return getFlag(3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSprinting(boolean sprinting) {
/* 2158 */     setFlag(3, sprinting);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isInvisible() {
/* 2163 */     return getFlag(5);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInvisibleToPlayer(EntityPlayer playerIn) {
/* 2173 */     return playerIn.func_175149_v() ? false : isInvisible();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setInvisible(boolean invisible) {
/* 2178 */     setFlag(5, invisible);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isEating() {
/* 2183 */     return getFlag(4);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEating(boolean eating) {
/* 2188 */     setFlag(4, eating);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean getFlag(int flag) {
/* 2197 */     return ((this.dataWatcher.getWatchableObjectByte(0) & 1 << flag) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setFlag(int flag, boolean set) {
/* 2205 */     byte var3 = this.dataWatcher.getWatchableObjectByte(0);
/*      */     
/* 2207 */     if (set) {
/*      */       
/* 2209 */       this.dataWatcher.updateObject(0, Byte.valueOf((byte)(var3 | 1 << flag)));
/*      */     }
/*      */     else {
/*      */       
/* 2213 */       this.dataWatcher.updateObject(0, Byte.valueOf((byte)(var3 & (1 << flag ^ 0xFFFFFFFF))));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public int getAir() {
/* 2219 */     return this.dataWatcher.getWatchableObjectShort(1);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAir(int air) {
/* 2224 */     this.dataWatcher.updateObject(1, Short.valueOf((short)air));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onStruckByLightning(EntityLightningBolt lightningBolt) {
/* 2232 */     attackEntityFrom(DamageSource.field_180137_b, 5.0F);
/* 2233 */     this.fire++;
/*      */     
/* 2235 */     if (this.fire == 0)
/*      */     {
/* 2237 */       setFire(8);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onKillEntity(EntityLivingBase entityLivingIn) {}
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean pushOutOfBlocks(double x, double y, double z) {
/* 2248 */     BlockPos var7 = new BlockPos(x, y, z);
/* 2249 */     double var8 = x - var7.getX();
/* 2250 */     double var10 = y - var7.getY();
/* 2251 */     double var12 = z - var7.getZ();
/* 2252 */     List var14 = this.worldObj.func_147461_a(getEntityBoundingBox());
/*      */     
/* 2254 */     if (var14.isEmpty() && !this.worldObj.func_175665_u(var7))
/*      */     {
/* 2256 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 2260 */     byte var15 = 3;
/* 2261 */     double var16 = 9999.0D;
/*      */     
/* 2263 */     if (!this.worldObj.func_175665_u(var7.offsetWest()) && var8 < var16) {
/*      */       
/* 2265 */       var16 = var8;
/* 2266 */       var15 = 0;
/*      */     } 
/*      */     
/* 2269 */     if (!this.worldObj.func_175665_u(var7.offsetEast()) && 1.0D - var8 < var16) {
/*      */       
/* 2271 */       var16 = 1.0D - var8;
/* 2272 */       var15 = 1;
/*      */     } 
/*      */     
/* 2275 */     if (!this.worldObj.func_175665_u(var7.offsetUp()) && 1.0D - var10 < var16) {
/*      */       
/* 2277 */       var16 = 1.0D - var10;
/* 2278 */       var15 = 3;
/*      */     } 
/*      */     
/* 2281 */     if (!this.worldObj.func_175665_u(var7.offsetNorth()) && var12 < var16) {
/*      */       
/* 2283 */       var16 = var12;
/* 2284 */       var15 = 4;
/*      */     } 
/*      */     
/* 2287 */     if (!this.worldObj.func_175665_u(var7.offsetSouth()) && 1.0D - var12 < var16) {
/*      */       
/* 2289 */       var16 = 1.0D - var12;
/* 2290 */       var15 = 5;
/*      */     } 
/*      */     
/* 2293 */     float var18 = this.rand.nextFloat() * 0.2F + 0.1F;
/*      */     
/* 2295 */     if (var15 == 0)
/*      */     {
/* 2297 */       this.motionX = -var18;
/*      */     }
/*      */     
/* 2300 */     if (var15 == 1)
/*      */     {
/* 2302 */       this.motionX = var18;
/*      */     }
/*      */     
/* 2305 */     if (var15 == 3)
/*      */     {
/* 2307 */       this.motionY = var18;
/*      */     }
/*      */     
/* 2310 */     if (var15 == 4)
/*      */     {
/* 2312 */       this.motionZ = -var18;
/*      */     }
/*      */     
/* 2315 */     if (var15 == 5)
/*      */     {
/* 2317 */       this.motionZ = var18;
/*      */     }
/*      */     
/* 2320 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInWeb() {
/* 2329 */     this.isInWeb = true;
/* 2330 */     this.fallDistance = 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/* 2338 */     if (hasCustomName())
/*      */     {
/* 2340 */       return getCustomNameTag();
/*      */     }
/*      */ 
/*      */     
/* 2344 */     String var1 = EntityList.getEntityString(this);
/*      */     
/* 2346 */     if (var1 == null)
/*      */     {
/* 2348 */       var1 = "generic";
/*      */     }
/*      */     
/* 2351 */     return StatCollector.translateToLocal("entity." + var1 + ".name");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity[] getParts() {
/* 2360 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEntityEqual(Entity entityIn) {
/* 2368 */     return (this == entityIn);
/*      */   }
/*      */ 
/*      */   
/*      */   public float getRotationYawHead() {
/* 2373 */     return 0.0F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRotationYawHead(float rotation) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canAttackWithItem() {
/* 2386 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hitByEntity(Entity entityIn) {
/* 2394 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 2399 */     return String.format("%s['%s'/%d, l='%s', x=%.2f, y=%.2f, z=%.2f]", new Object[] { getClass().getSimpleName(), getName(), Integer.valueOf(this.entityId), (this.worldObj == null) ? "~NULL~" : this.worldObj.getWorldInfo().getWorldName(), Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ) });
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_180431_b(DamageSource p_180431_1_) {
/* 2404 */     return (this.invulnerable && p_180431_1_ != DamageSource.outOfWorld && !p_180431_1_.func_180136_u());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void copyLocationAndAnglesFrom(Entity entityIn) {
/* 2412 */     setLocationAndAngles(entityIn.posX, entityIn.posY, entityIn.posZ, entityIn.rotationYaw, entityIn.rotationPitch);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_180432_n(Entity p_180432_1_) {
/* 2417 */     NBTTagCompound var2 = new NBTTagCompound();
/* 2418 */     p_180432_1_.writeToNBT(var2);
/* 2419 */     readFromNBT(var2);
/* 2420 */     this.timeUntilPortal = p_180432_1_.timeUntilPortal;
/* 2421 */     this.teleportDirection = p_180432_1_.teleportDirection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void travelToDimension(int dimensionId) {
/* 2429 */     if (!this.worldObj.isRemote && !this.isDead) {
/*      */       
/* 2431 */       this.worldObj.theProfiler.startSection("changeDimension");
/* 2432 */       MinecraftServer var2 = MinecraftServer.getServer();
/* 2433 */       int var3 = this.dimension;
/* 2434 */       WorldServer var4 = var2.worldServerForDimension(var3);
/* 2435 */       WorldServer var5 = var2.worldServerForDimension(dimensionId);
/* 2436 */       this.dimension = dimensionId;
/*      */       
/* 2438 */       if (var3 == 1 && dimensionId == 1) {
/*      */         
/* 2440 */         var5 = var2.worldServerForDimension(0);
/* 2441 */         this.dimension = 0;
/*      */       } 
/*      */       
/* 2444 */       this.worldObj.removeEntity(this);
/* 2445 */       this.isDead = false;
/* 2446 */       this.worldObj.theProfiler.startSection("reposition");
/* 2447 */       var2.getConfigurationManager().transferEntityToWorld(this, var3, var4, var5);
/* 2448 */       this.worldObj.theProfiler.endStartSection("reloading");
/* 2449 */       Entity var6 = EntityList.createEntityByName(EntityList.getEntityString(this), (World)var5);
/*      */       
/* 2451 */       if (var6 != null) {
/*      */         
/* 2453 */         var6.func_180432_n(this);
/*      */         
/* 2455 */         if (var3 == 1 && dimensionId == 1) {
/*      */           
/* 2457 */           BlockPos var7 = this.worldObj.func_175672_r(var5.getSpawnPoint());
/* 2458 */           var6.func_174828_a(var7, var6.rotationYaw, var6.rotationPitch);
/*      */         } 
/*      */         
/* 2461 */         var5.spawnEntityInWorld(var6);
/*      */       } 
/*      */       
/* 2464 */       this.isDead = true;
/* 2465 */       this.worldObj.theProfiler.endSection();
/* 2466 */       var4.resetUpdateEntityTick();
/* 2467 */       var5.resetUpdateEntityTick();
/* 2468 */       this.worldObj.theProfiler.endSection();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getExplosionResistance(Explosion p_180428_1_, World worldIn, BlockPos p_180428_3_, IBlockState p_180428_4_) {
/* 2477 */     return p_180428_4_.getBlock().getExplosionResistance(this);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_174816_a(Explosion p_174816_1_, World worldIn, BlockPos p_174816_3_, IBlockState p_174816_4_, float p_174816_5_) {
/* 2482 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxFallHeight() {
/* 2490 */     return 3;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getTeleportDirection() {
/* 2495 */     return this.teleportDirection;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean doesEntityNotTriggerPressurePlate() {
/* 2503 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void addEntityCrashInfo(CrashReportCategory category) {
/* 2508 */     category.addCrashSectionCallable("Entity Type", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00001534";
/*      */           
/*      */           public String call() {
/* 2513 */             return String.valueOf(EntityList.getEntityString(Entity.this)) + " (" + Entity.this.getClass().getCanonicalName() + ")";
/*      */           }
/*      */         });
/* 2516 */     category.addCrashSection("Entity ID", Integer.valueOf(this.entityId));
/* 2517 */     category.addCrashSectionCallable("Entity Name", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00001535";
/*      */           
/*      */           public String call() {
/* 2522 */             return Entity.this.getName();
/*      */           }
/*      */         });
/* 2525 */     category.addCrashSection("Entity's Exact location", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ) }));
/* 2526 */     category.addCrashSection("Entity's Block location", CrashReportCategory.getCoordinateInfo(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)));
/* 2527 */     category.addCrashSection("Entity's Momentum", String.format("%.2f, %.2f, %.2f", new Object[] { Double.valueOf(this.motionX), Double.valueOf(this.motionY), Double.valueOf(this.motionZ) }));
/* 2528 */     category.addCrashSectionCallable("Entity's Rider", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00002259";
/*      */           
/*      */           public String func_180118_a() {
/* 2533 */             return Entity.this.riddenByEntity.toString();
/*      */           }
/*      */           
/*      */           public Object call() {
/* 2537 */             return func_180118_a();
/*      */           }
/*      */         });
/* 2540 */     category.addCrashSectionCallable("Entity's Vehicle", new Callable()
/*      */         {
/*      */           private static final String __OBFID = "CL_00002258";
/*      */           
/*      */           public String func_180116_a() {
/* 2545 */             return Entity.this.ridingEntity.toString();
/*      */           }
/*      */           
/*      */           public Object call() {
/* 2549 */             return func_180116_a();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canRenderOnFire() {
/* 2559 */     return isBurning();
/*      */   }
/*      */ 
/*      */   
/*      */   public UUID getUniqueID() {
/* 2564 */     return this.entityUniqueID;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPushedByWater() {
/* 2569 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public IChatComponent getDisplayName() {
/* 2574 */     ChatComponentText var1 = new ChatComponentText(getName());
/* 2575 */     var1.getChatStyle().setChatHoverEvent(func_174823_aP());
/* 2576 */     var1.getChatStyle().setInsertion(getUniqueID().toString());
/* 2577 */     return (IChatComponent)var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCustomNameTag(String p_96094_1_) {
/* 2585 */     this.dataWatcher.updateObject(2, p_96094_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getCustomNameTag() {
/* 2590 */     return this.dataWatcher.getWatchableObjectString(2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasCustomName() {
/* 2598 */     return (this.dataWatcher.getWatchableObjectString(2).length() > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAlwaysRenderNameTag(boolean p_174805_1_) {
/* 2603 */     this.dataWatcher.updateObject(3, Byte.valueOf((byte)(p_174805_1_ ? 1 : 0)));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAlwaysRenderNameTag() {
/* 2608 */     return (this.dataWatcher.getWatchableObjectByte(3) == 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPositionAndUpdate(double p_70634_1_, double p_70634_3_, double p_70634_5_) {
/* 2616 */     setLocationAndAngles(p_70634_1_, p_70634_3_, p_70634_5_, this.rotationYaw, this.rotationPitch);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getAlwaysRenderNameTagForRender() {
/* 2621 */     return getAlwaysRenderNameTag();
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_145781_i(int p_145781_1_) {}
/*      */   
/*      */   public EnumFacing func_174811_aO() {
/* 2628 */     return EnumFacing.getHorizontal(MathHelper.floor_double((this.rotationYaw * 4.0F / 360.0F) + 0.5D) & 0x3);
/*      */   }
/*      */ 
/*      */   
/*      */   protected HoverEvent func_174823_aP() {
/* 2633 */     NBTTagCompound var1 = new NBTTagCompound();
/* 2634 */     String var2 = EntityList.getEntityString(this);
/* 2635 */     var1.setString("id", getUniqueID().toString());
/*      */     
/* 2637 */     if (var2 != null)
/*      */     {
/* 2639 */       var1.setString("type", var2);
/*      */     }
/*      */     
/* 2642 */     var1.setString("name", getName());
/* 2643 */     return new HoverEvent(HoverEvent.Action.SHOW_ENTITY, (IChatComponent)new ChatComponentText(var1.toString()));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_174827_a(EntityPlayerMP p_174827_1_) {
/* 2648 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public AxisAlignedBB getEntityBoundingBox() {
/* 2653 */     return this.boundingBox;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174826_a(AxisAlignedBB p_174826_1_) {
/* 2658 */     this.boundingBox = p_174826_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getEyeHeight() {
/* 2663 */     return this.height * 0.85F;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOutsideBorder() {
/* 2668 */     return this.isOutsideBorder;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setOutsideBorder(boolean p_174821_1_) {
/* 2673 */     this.isOutsideBorder = p_174821_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
/* 2678 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addChatMessage(IChatComponent message) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canCommandSenderUseCommand(int permissionLevel, String command) {
/* 2694 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockPos getPosition() {
/* 2699 */     return new BlockPos(this.posX, this.posY + 0.5D, this.posZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getPositionVector() {
/* 2704 */     return new Vec3(this.posX, this.posY, this.posZ);
/*      */   }
/*      */ 
/*      */   
/*      */   public World getEntityWorld() {
/* 2709 */     return this.worldObj;
/*      */   }
/*      */ 
/*      */   
/*      */   public Entity getCommandSenderEntity() {
/* 2714 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean sendCommandFeedback() {
/* 2719 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
/* 2724 */     this.field_174837_as.func_179672_a(this, p_174794_1_, p_174794_2_);
/*      */   }
/*      */ 
/*      */   
/*      */   public CommandResultStats func_174807_aT() {
/* 2729 */     return this.field_174837_as;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174817_o(Entity p_174817_1_) {
/* 2734 */     this.field_174837_as.func_179671_a(p_174817_1_.func_174807_aT());
/*      */   }
/*      */ 
/*      */   
/*      */   public NBTTagCompound func_174819_aU() {
/* 2739 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_174834_g(NBTTagCompound p_174834_1_) {}
/*      */   
/*      */   public boolean func_174825_a(EntityPlayer p_174825_1_, Vec3 p_174825_2_) {
/* 2746 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_180427_aV() {
/* 2751 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_174815_a(EntityLivingBase p_174815_1_, Entity p_174815_2_) {
/* 2756 */     if (p_174815_2_ instanceof EntityLivingBase)
/*      */     {
/* 2758 */       EnchantmentHelper.func_151384_a((EntityLivingBase)p_174815_2_, p_174815_1_);
/*      */     }
/*      */     
/* 2761 */     EnchantmentHelper.func_151385_b(p_174815_1_, p_174815_2_);
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */