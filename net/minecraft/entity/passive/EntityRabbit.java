/*     */ package net.minecraft.entity.passive;
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockCarrot;
/*     */ import net.minecraft.block.properties.IProperty;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAIMate;
/*     */ import net.minecraft.entity.ai.EntityAIMoveToBlock;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIPanic;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.ai.EntityJumpHelper;
/*     */ import net.minecraft.entity.ai.EntityMoveHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityRabbit extends EntityAnimal {
/*  44 */   private int field_175540_bm = 0; private AIAvoidEntity field_175539_bk;
/*  45 */   private int field_175535_bn = 0;
/*     */   private boolean field_175536_bo = false;
/*     */   private boolean field_175537_bp = false;
/*  48 */   private int field_175538_bq = 0;
/*     */   
/*     */   private EnumMoveType field_175542_br;
/*     */   private int field_175541_bs;
/*     */   private EntityPlayer field_175543_bt;
/*     */   private static final String __OBFID = "CL_00002242";
/*     */   
/*     */   public EntityRabbit(World worldIn) {
/*  56 */     super(worldIn);
/*  57 */     this.field_175542_br = EnumMoveType.HOP;
/*  58 */     this.field_175541_bs = 0;
/*  59 */     this.field_175543_bt = null;
/*  60 */     setSize(0.6F, 0.7F);
/*  61 */     this.jumpHelper = new RabbitJumpHelper(this);
/*  62 */     this.moveHelper = new RabbitMoveHelper();
/*  63 */     ((PathNavigateGround)getNavigator()).func_179690_a(true);
/*  64 */     this.navigator.func_179678_a(2.5F);
/*  65 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  66 */     this.tasks.addTask(1, (EntityAIBase)new AIPanic(1.33D));
/*  67 */     this.tasks.addTask(2, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.0D, Items.carrot, false));
/*  68 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIMate(this, 0.8D));
/*  69 */     this.tasks.addTask(5, (EntityAIBase)new AIRaidFarm());
/*  70 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.6D));
/*  71 */     this.tasks.addTask(11, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 10.0F));
/*  72 */     this.field_175539_bk = new AIAvoidEntity(new Predicate()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002241";
/*     */           
/*     */           public boolean func_180086_a(Entity p_180086_1_) {
/*  77 */             return p_180086_1_ instanceof EntityWolf;
/*     */           }
/*     */           
/*     */           public boolean apply(Object p_apply_1_) {
/*  81 */             return func_180086_a((Entity)p_apply_1_);
/*     */           }
/*  83 */         },  16.0F, 1.33D, 1.33D);
/*  84 */     this.tasks.addTask(4, (EntityAIBase)this.field_175539_bk);
/*  85 */     func_175515_b(0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_175134_bD() {
/*  90 */     return (this.moveHelper.isUpdating() && this.moveHelper.func_179919_e() > this.posY + 0.5D) ? 0.5F : this.field_175542_br.func_180074_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175522_a(EnumMoveType p_175522_1_) {
/*  95 */     this.field_175542_br = p_175522_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_175521_o(float p_175521_1_) {
/* 100 */     return (this.field_175535_bn == 0) ? 0.0F : ((this.field_175540_bm + p_175521_1_) / this.field_175535_bn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175515_b(double p_175515_1_) {
/* 105 */     getNavigator().setSpeed(p_175515_1_);
/* 106 */     this.moveHelper.setMoveTo(this.moveHelper.func_179917_d(), this.moveHelper.func_179919_e(), this.moveHelper.func_179918_f(), p_175515_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175519_a(boolean p_175519_1_, EnumMoveType p_175519_2_) {
/* 111 */     setJumping(p_175519_1_);
/*     */     
/* 113 */     if (!p_175519_1_) {
/*     */       
/* 115 */       if (this.field_175542_br == EnumMoveType.ATTACK)
/*     */       {
/* 117 */         this.field_175542_br = EnumMoveType.HOP;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 122 */       func_175515_b(1.5D * p_175519_2_.func_180072_a());
/* 123 */       playSound(func_175516_ck(), getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/*     */     } 
/*     */     
/* 126 */     this.field_175536_bo = p_175519_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175524_b(EnumMoveType p_175524_1_) {
/* 131 */     func_175519_a(true, p_175524_1_);
/* 132 */     this.field_175535_bn = p_175524_1_.func_180073_d();
/* 133 */     this.field_175540_bm = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175523_cj() {
/* 138 */     return this.field_175536_bo;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/* 143 */     super.entityInit();
/* 144 */     this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAITasks() {
/* 149 */     if (this.moveHelper.getSpeed() > 0.8D) {
/*     */       
/* 151 */       func_175522_a(EnumMoveType.SPRINT);
/*     */     }
/* 153 */     else if (this.field_175542_br != EnumMoveType.ATTACK) {
/*     */       
/* 155 */       func_175522_a(EnumMoveType.HOP);
/*     */     } 
/*     */     
/* 158 */     if (this.field_175538_bq > 0)
/*     */     {
/* 160 */       this.field_175538_bq--;
/*     */     }
/*     */     
/* 163 */     if (this.field_175541_bs > 0) {
/*     */       
/* 165 */       this.field_175541_bs -= this.rand.nextInt(3);
/*     */       
/* 167 */       if (this.field_175541_bs < 0)
/*     */       {
/* 169 */         this.field_175541_bs = 0;
/*     */       }
/*     */     } 
/*     */     
/* 173 */     if (this.onGround) {
/*     */       
/* 175 */       if (!this.field_175537_bp) {
/*     */         
/* 177 */         func_175519_a(false, EnumMoveType.NONE);
/* 178 */         func_175517_cu();
/*     */       } 
/*     */       
/* 181 */       if (func_175531_cl() == 99 && this.field_175538_bq == 0) {
/*     */         
/* 183 */         EntityLivingBase var1 = getAttackTarget();
/*     */         
/* 185 */         if (var1 != null && getDistanceSqToEntity((Entity)var1) < 16.0D) {
/*     */           
/* 187 */           func_175533_a(var1.posX, var1.posZ);
/* 188 */           this.moveHelper.setMoveTo(var1.posX, var1.posY, var1.posZ, this.moveHelper.getSpeed());
/* 189 */           func_175524_b(EnumMoveType.ATTACK);
/* 190 */           this.field_175537_bp = true;
/*     */         } 
/*     */       } 
/*     */       
/* 194 */       RabbitJumpHelper var4 = (RabbitJumpHelper)this.jumpHelper;
/*     */       
/* 196 */       if (!var4.func_180067_c()) {
/*     */         
/* 198 */         if (this.moveHelper.isUpdating() && this.field_175538_bq == 0)
/*     */         {
/* 200 */           PathEntity var2 = this.navigator.getPath();
/* 201 */           Vec3 var3 = new Vec3(this.moveHelper.func_179917_d(), this.moveHelper.func_179919_e(), this.moveHelper.func_179918_f());
/*     */           
/* 203 */           if (var2 != null && var2.getCurrentPathIndex() < var2.getCurrentPathLength())
/*     */           {
/* 205 */             var3 = var2.getPosition((Entity)this);
/*     */           }
/*     */           
/* 208 */           func_175533_a(var3.xCoord, var3.zCoord);
/* 209 */           func_175524_b(this.field_175542_br);
/*     */         }
/*     */       
/* 212 */       } else if (!var4.func_180065_d()) {
/*     */         
/* 214 */         func_175518_cr();
/*     */       } 
/*     */     } 
/*     */     
/* 218 */     this.field_175537_bp = this.onGround;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_174830_Y() {}
/*     */   
/*     */   private void func_175533_a(double p_175533_1_, double p_175533_3_) {
/* 225 */     this.rotationYaw = (float)(Math.atan2(p_175533_3_ - this.posZ, p_175533_1_ - this.posX) * 180.0D / Math.PI) - 90.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175518_cr() {
/* 230 */     ((RabbitJumpHelper)this.jumpHelper).func_180066_a(true);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175520_cs() {
/* 235 */     ((RabbitJumpHelper)this.jumpHelper).func_180066_a(false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175530_ct() {
/* 240 */     this.field_175538_bq = func_175532_cm();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_175517_cu() {
/* 245 */     func_175530_ct();
/* 246 */     func_175520_cs();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 255 */     super.onLivingUpdate();
/*     */     
/* 257 */     if (this.field_175540_bm != this.field_175535_bn) {
/*     */       
/* 259 */       if (this.field_175540_bm == 0 && !this.worldObj.isRemote)
/*     */       {
/* 261 */         this.worldObj.setEntityState((Entity)this, (byte)1);
/*     */       }
/*     */       
/* 264 */       this.field_175540_bm++;
/*     */     }
/* 266 */     else if (this.field_175535_bn != 0) {
/*     */       
/* 268 */       this.field_175540_bm = 0;
/* 269 */       this.field_175535_bn = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/* 275 */     super.applyEntityAttributes();
/* 276 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
/* 277 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 285 */     super.writeEntityToNBT(tagCompound);
/* 286 */     tagCompound.setInteger("RabbitType", func_175531_cl());
/* 287 */     tagCompound.setInteger("MoreCarrotTicks", this.field_175541_bs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 295 */     super.readEntityFromNBT(tagCompund);
/* 296 */     func_175529_r(tagCompund.getInteger("RabbitType"));
/* 297 */     this.field_175541_bs = tagCompund.getInteger("MoreCarrotTicks");
/*     */   }
/*     */ 
/*     */   
/*     */   protected String func_175516_ck() {
/* 302 */     return "mob.rabbit.hop";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 310 */     return "mob.rabbit.idle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 318 */     return "mob.rabbit.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 326 */     return "mob.rabbit.death";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 331 */     if (func_175531_cl() == 99) {
/*     */       
/* 333 */       playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/* 334 */       return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 8.0F);
/*     */     } 
/*     */ 
/*     */     
/* 338 */     return p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 3.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTotalArmorValue() {
/* 347 */     return (func_175531_cl() == 99) ? 8 : super.getTotalArmorValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource source, float amount) {
/* 355 */     return func_180431_b(source) ? false : super.attackEntityFrom(source, amount);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addRandomArmor() {
/* 363 */     entityDropItem(new ItemStack(Items.rabbit_foot, 1), 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 371 */     int var3 = this.rand.nextInt(2) + this.rand.nextInt(1 + p_70628_2_);
/*     */     
/*     */     int var4;
/* 374 */     for (var4 = 0; var4 < var3; var4++)
/*     */     {
/* 376 */       dropItem(Items.rabbit_hide, 1);
/*     */     }
/*     */     
/* 379 */     var3 = this.rand.nextInt(2);
/*     */     
/* 381 */     for (var4 = 0; var4 < var3; var4++) {
/*     */       
/* 383 */       if (isBurning()) {
/*     */         
/* 385 */         dropItem(Items.cooked_rabbit, 1);
/*     */       }
/*     */       else {
/*     */         
/* 389 */         dropItem(Items.rabbit, 1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_175525_a(Item p_175525_1_) {
/* 396 */     return !(p_175525_1_ != Items.carrot && p_175525_1_ != Items.golden_carrot && p_175525_1_ != Item.getItemFromBlock((Block)Blocks.yellow_flower));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityRabbit func_175526_b(EntityAgeable p_175526_1_) {
/* 401 */     EntityRabbit var2 = new EntityRabbit(this.worldObj);
/*     */     
/* 403 */     if (p_175526_1_ instanceof EntityRabbit)
/*     */     {
/* 405 */       var2.func_175529_r(this.rand.nextBoolean() ? func_175531_cl() : ((EntityRabbit)p_175526_1_).func_175531_cl());
/*     */     }
/*     */     
/* 408 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBreedingItem(ItemStack p_70877_1_) {
/* 417 */     return (p_70877_1_ != null && func_175525_a(p_70877_1_.getItem()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_175531_cl() {
/* 422 */     return this.dataWatcher.getWatchableObjectByte(18);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175529_r(int p_175529_1_) {
/* 427 */     if (p_175529_1_ == 99) {
/*     */       
/* 429 */       this.tasks.removeTask((EntityAIBase)this.field_175539_bk);
/* 430 */       this.tasks.addTask(4, (EntityAIBase)new AIEvilAttack());
/* 431 */       this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false, new Class[0]));
/* 432 */       this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, true));
/* 433 */       this.targetTasks.addTask(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityWolf.class, true));
/*     */       
/* 435 */       if (!hasCustomName())
/*     */       {
/* 437 */         setCustomNameTag(StatCollector.translateToLocal("entity.KillerBunny.name"));
/*     */       }
/*     */     } 
/*     */     
/* 441 */     this.dataWatcher.updateObject(18, Byte.valueOf((byte)p_175529_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 446 */     Object p_180482_2_1 = super.func_180482_a(p_180482_1_, p_180482_2_);
/* 447 */     int var3 = this.rand.nextInt(6);
/* 448 */     boolean var4 = false;
/*     */     
/* 450 */     if (p_180482_2_1 instanceof RabbitTypeData) {
/*     */       
/* 452 */       var3 = ((RabbitTypeData)p_180482_2_1).field_179427_a;
/* 453 */       var4 = true;
/*     */     }
/*     */     else {
/*     */       
/* 457 */       p_180482_2_1 = new RabbitTypeData(var3);
/*     */     } 
/*     */     
/* 460 */     func_175529_r(var3);
/*     */     
/* 462 */     if (var4)
/*     */     {
/* 464 */       setGrowingAge(-24000);
/*     */     }
/*     */     
/* 467 */     return (IEntityLivingData)p_180482_2_1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_175534_cv() {
/* 472 */     return (this.field_175541_bs == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int func_175532_cm() {
/* 477 */     return this.field_175542_br.func_180075_c();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175528_cn() {
/* 482 */     this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_DUST, this.posX + (this.rand.nextFloat() * this.width * 2.0F) - this.width, this.posY + 0.5D + (this.rand.nextFloat() * this.height), this.posZ + (this.rand.nextFloat() * this.width * 2.0F) - this.width, 0.0D, 0.0D, 0.0D, new int[] { Block.getStateId(Blocks.carrots.getStateFromMeta(7)) });
/* 483 */     this.field_175541_bs = 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 488 */     if (p_70103_1_ == 1) {
/*     */       
/* 490 */       func_174808_Z();
/* 491 */       this.field_175535_bn = 10;
/* 492 */       this.field_175540_bm = 0;
/*     */     }
/*     */     else {
/*     */       
/* 496 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable p_90011_1_) {
/* 502 */     return func_175526_b(p_90011_1_);
/*     */   }
/*     */   
/*     */   class AIAvoidEntity
/*     */     extends EntityAIAvoidEntity {
/* 507 */     private EntityRabbit field_179511_d = EntityRabbit.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002238";
/*     */     
/*     */     public AIAvoidEntity(Predicate p_i45865_2_, float p_i45865_3_, double p_i45865_4_, double p_i45865_6_) {
/* 512 */       super((EntityCreature)EntityRabbit.this, p_i45865_2_, p_i45865_3_, p_i45865_4_, p_i45865_6_);
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 517 */       super.updateTask();
/*     */     }
/*     */   }
/*     */   
/*     */   class AIEvilAttack
/*     */     extends EntityAIAttackOnCollide
/*     */   {
/*     */     private static final String __OBFID = "CL_00002240";
/*     */     
/*     */     public AIEvilAttack() {
/* 527 */       super((EntityCreature)EntityRabbit.this, EntityLivingBase.class, 1.4D, true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected double func_179512_a(EntityLivingBase p_179512_1_) {
/* 532 */       return (4.0F + p_179512_1_.width);
/*     */     }
/*     */   }
/*     */   
/*     */   class AIPanic
/*     */     extends EntityAIPanic {
/* 538 */     private EntityRabbit field_179486_b = EntityRabbit.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002234";
/*     */     
/*     */     public AIPanic(double p_i45861_2_) {
/* 543 */       super((EntityCreature)EntityRabbit.this, p_i45861_2_);
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 548 */       super.updateTask();
/* 549 */       this.field_179486_b.func_175515_b(this.speed);
/*     */     }
/*     */   }
/*     */   
/*     */   class AIRaidFarm
/*     */     extends EntityAIMoveToBlock
/*     */   {
/*     */     private boolean field_179498_d;
/*     */     private boolean field_179499_e = false;
/*     */     private static final String __OBFID = "CL_00002233";
/*     */     
/*     */     public AIRaidFarm() {
/* 561 */       super((EntityCreature)EntityRabbit.this, 0.699999988079071D, 16);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 566 */       if (this.field_179496_a <= 0) {
/*     */         
/* 568 */         if (!EntityRabbit.this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
/*     */         {
/* 570 */           return false;
/*     */         }
/*     */         
/* 573 */         this.field_179499_e = false;
/* 574 */         this.field_179498_d = EntityRabbit.this.func_175534_cv();
/*     */       } 
/*     */       
/* 577 */       return super.shouldExecute();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean continueExecuting() {
/* 582 */       return (this.field_179499_e && super.continueExecuting());
/*     */     }
/*     */ 
/*     */     
/*     */     public void startExecuting() {
/* 587 */       super.startExecuting();
/*     */     }
/*     */ 
/*     */     
/*     */     public void resetTask() {
/* 592 */       super.resetTask();
/*     */     }
/*     */ 
/*     */     
/*     */     public void updateTask() {
/* 597 */       super.updateTask();
/* 598 */       EntityRabbit.this.getLookHelper().setLookPosition(this.field_179494_b.getX() + 0.5D, (this.field_179494_b.getY() + 1), this.field_179494_b.getZ() + 0.5D, 10.0F, EntityRabbit.this.getVerticalFaceSpeed());
/*     */       
/* 600 */       if (func_179487_f()) {
/*     */         
/* 602 */         World var1 = EntityRabbit.this.worldObj;
/* 603 */         BlockPos var2 = this.field_179494_b.offsetUp();
/* 604 */         IBlockState var3 = var1.getBlockState(var2);
/* 605 */         Block var4 = var3.getBlock();
/*     */         
/* 607 */         if (this.field_179499_e && var4 instanceof BlockCarrot && ((Integer)var3.getValue((IProperty)BlockCarrot.AGE)).intValue() == 7) {
/*     */           
/* 609 */           var1.setBlockState(var2, Blocks.air.getDefaultState(), 2);
/* 610 */           var1.destroyBlock(var2, true);
/* 611 */           EntityRabbit.this.func_175528_cn();
/*     */         } 
/*     */         
/* 614 */         this.field_179499_e = false;
/* 615 */         this.field_179496_a = 10;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean func_179488_a(World worldIn, BlockPos p_179488_2_) {
/* 621 */       Block var3 = worldIn.getBlockState(p_179488_2_).getBlock();
/*     */       
/* 623 */       if (var3 == Blocks.farmland) {
/*     */         
/* 625 */         p_179488_2_ = p_179488_2_.offsetUp();
/* 626 */         IBlockState var4 = worldIn.getBlockState(p_179488_2_);
/* 627 */         var3 = var4.getBlock();
/*     */         
/* 629 */         if (var3 instanceof BlockCarrot && ((Integer)var4.getValue((IProperty)BlockCarrot.AGE)).intValue() == 7 && this.field_179498_d && !this.field_179499_e) {
/*     */           
/* 631 */           this.field_179499_e = true;
/* 632 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 636 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   enum EnumMoveType
/*     */   {
/* 642 */     NONE("NONE", 0, 0.0F, 0.0F, 30, 1),
/* 643 */     HOP("HOP", 1, 0.8F, 0.2F, 20, 10),
/* 644 */     STEP("STEP", 2, 1.0F, 0.45F, 14, 14),
/* 645 */     SPRINT("SPRINT", 3, 1.75F, 0.4F, 1, 8),
/* 646 */     ATTACK("ATTACK", 4, 2.0F, 0.7F, 7, 8);
/*     */     
/*     */     private final float field_180076_f;
/*     */     private final float field_180077_g;
/*     */     private final int field_180084_h;
/*     */     private final int field_180085_i;
/* 652 */     private static final EnumMoveType[] $VALUES = new EnumMoveType[] { NONE, HOP, STEP, SPRINT, ATTACK }; private static final String __OBFID = "CL_00002239";
/*     */     static {
/*     */     
/*     */     }
/*     */     EnumMoveType(String p_i45866_1_, int p_i45866_2_, float p_i45866_3_, float p_i45866_4_, int p_i45866_5_, int p_i45866_6_) {
/* 657 */       this.field_180076_f = p_i45866_3_;
/* 658 */       this.field_180077_g = p_i45866_4_;
/* 659 */       this.field_180084_h = p_i45866_5_;
/* 660 */       this.field_180085_i = p_i45866_6_;
/*     */     }
/*     */ 
/*     */     
/*     */     public float func_180072_a() {
/* 665 */       return this.field_180076_f;
/*     */     }
/*     */ 
/*     */     
/*     */     public float func_180074_b() {
/* 670 */       return this.field_180077_g;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_180075_c() {
/* 675 */       return this.field_180084_h;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_180073_d() {
/* 680 */       return this.field_180085_i;
/*     */     }
/*     */   }
/*     */   
/*     */   public class RabbitJumpHelper
/*     */     extends EntityJumpHelper
/*     */   {
/*     */     private EntityRabbit field_180070_c;
/*     */     private boolean field_180068_d = false;
/*     */     private static final String __OBFID = "CL_00002236";
/*     */     
/*     */     public RabbitJumpHelper(EntityRabbit p_i45863_2_) {
/* 692 */       super((EntityLiving)p_i45863_2_);
/* 693 */       this.field_180070_c = p_i45863_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_180067_c() {
/* 698 */       return this.isJumping;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_180065_d() {
/* 703 */       return this.field_180068_d;
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_180066_a(boolean p_180066_1_) {
/* 708 */       this.field_180068_d = p_180066_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void doJump() {
/* 713 */       if (this.isJumping) {
/*     */         
/* 715 */         this.field_180070_c.func_175524_b(EntityRabbit.EnumMoveType.STEP);
/* 716 */         this.isJumping = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   class RabbitMoveHelper
/*     */     extends EntityMoveHelper {
/* 723 */     private EntityRabbit field_179929_g = EntityRabbit.this;
/*     */     
/*     */     private static final String __OBFID = "CL_00002235";
/*     */     
/*     */     public RabbitMoveHelper() {
/* 728 */       super((EntityLiving)EntityRabbit.this);
/*     */     }
/*     */ 
/*     */     
/*     */     public void onUpdateMoveHelper() {
/* 733 */       if (this.field_179929_g.onGround && !this.field_179929_g.func_175523_cj())
/*     */       {
/* 735 */         this.field_179929_g.func_175515_b(0.0D);
/*     */       }
/*     */       
/* 738 */       super.onUpdateMoveHelper();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class RabbitTypeData
/*     */     implements IEntityLivingData
/*     */   {
/*     */     public int field_179427_a;
/*     */     private static final String __OBFID = "CL_00002237";
/*     */     
/*     */     public RabbitTypeData(int p_i45864_1_) {
/* 749 */       this.field_179427_a = p_i45864_1_;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\passive\EntityRabbit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */