/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIAvoidEntity;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAICreeperSwell;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityCreeper
/*     */   extends EntityMob
/*     */ {
/*     */   private int lastActiveTime;
/*     */   private int timeSinceIgnited;
/*  37 */   private int fuseTime = 30;
/*     */ 
/*     */   
/*  40 */   private int explosionRadius = 3;
/*  41 */   private int field_175494_bm = 0;
/*     */   
/*     */   private static final String __OBFID = "CL_00001684";
/*     */   
/*     */   public EntityCreeper(World worldIn) {
/*  46 */     super(worldIn);
/*  47 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  48 */     this.tasks.addTask(2, (EntityAIBase)new EntityAICreeperSwell(this));
/*  49 */     this.tasks.addTask(2, this.field_175455_a);
/*  50 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIAvoidEntity(this, new Predicate()
/*     */           {
/*     */             private static final String __OBFID = "CL_00002224";
/*     */             
/*     */             public boolean func_179958_a(Entity p_179958_1_) {
/*  55 */               return p_179958_1_ instanceof net.minecraft.entity.passive.EntityOcelot;
/*     */             }
/*     */             
/*     */             public boolean apply(Object p_apply_1_) {
/*  59 */               return func_179958_a((Entity)p_apply_1_);
/*     */             }
/*  61 */           },  6.0F, 1.0D, 1.2D));
/*  62 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIAttackOnCollide(this, 1.0D, false));
/*  63 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWander(this, 0.8D));
/*  64 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  65 */     this.tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  66 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
/*  67 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAIHurtByTarget(this, false, new Class[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  72 */     super.applyEntityAttributes();
/*  73 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxFallHeight() {
/*  81 */     return (getAttackTarget() == null) ? 3 : (3 + (int)(getHealth() - 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void fall(float distance, float damageMultiplier) {
/*  86 */     super.fall(distance, damageMultiplier);
/*  87 */     this.timeSinceIgnited = (int)(this.timeSinceIgnited + distance * 1.5F);
/*     */     
/*  89 */     if (this.timeSinceIgnited > this.fuseTime - 5)
/*     */     {
/*  91 */       this.timeSinceIgnited = this.fuseTime - 5;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  97 */     super.entityInit();
/*  98 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)-1));
/*  99 */     this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
/* 100 */     this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 108 */     super.writeEntityToNBT(tagCompound);
/*     */     
/* 110 */     if (this.dataWatcher.getWatchableObjectByte(17) == 1)
/*     */     {
/* 112 */       tagCompound.setBoolean("powered", true);
/*     */     }
/*     */     
/* 115 */     tagCompound.setShort("Fuse", (short)this.fuseTime);
/* 116 */     tagCompound.setByte("ExplosionRadius", (byte)this.explosionRadius);
/* 117 */     tagCompound.setBoolean("ignited", func_146078_ca());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 125 */     super.readEntityFromNBT(tagCompund);
/* 126 */     this.dataWatcher.updateObject(17, Byte.valueOf((byte)(tagCompund.getBoolean("powered") ? 1 : 0)));
/*     */     
/* 128 */     if (tagCompund.hasKey("Fuse", 99))
/*     */     {
/* 130 */       this.fuseTime = tagCompund.getShort("Fuse");
/*     */     }
/*     */     
/* 133 */     if (tagCompund.hasKey("ExplosionRadius", 99))
/*     */     {
/* 135 */       this.explosionRadius = tagCompund.getByte("ExplosionRadius");
/*     */     }
/*     */     
/* 138 */     if (tagCompund.getBoolean("ignited"))
/*     */     {
/* 140 */       func_146079_cb();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 149 */     if (isEntityAlive()) {
/*     */       
/* 151 */       this.lastActiveTime = this.timeSinceIgnited;
/*     */       
/* 153 */       if (func_146078_ca())
/*     */       {
/* 155 */         setCreeperState(1);
/*     */       }
/*     */       
/* 158 */       int var1 = getCreeperState();
/*     */       
/* 160 */       if (var1 > 0 && this.timeSinceIgnited == 0)
/*     */       {
/* 162 */         playSound("creeper.primed", 1.0F, 0.5F);
/*     */       }
/*     */       
/* 165 */       this.timeSinceIgnited += var1;
/*     */       
/* 167 */       if (this.timeSinceIgnited < 0)
/*     */       {
/* 169 */         this.timeSinceIgnited = 0;
/*     */       }
/*     */       
/* 172 */       if (this.timeSinceIgnited >= this.fuseTime) {
/*     */         
/* 174 */         this.timeSinceIgnited = this.fuseTime;
/* 175 */         func_146077_cc();
/*     */       } 
/*     */     } 
/*     */     
/* 179 */     super.onUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 187 */     return "mob.creeper.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 195 */     return "mob.creeper.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource cause) {
/* 203 */     super.onDeath(cause);
/*     */     
/* 205 */     if (cause.getEntity() instanceof EntitySkeleton) {
/*     */       
/* 207 */       int var2 = Item.getIdFromItem(Items.record_13);
/* 208 */       int var3 = Item.getIdFromItem(Items.record_wait);
/* 209 */       int var4 = var2 + this.rand.nextInt(var3 - var2 + 1);
/* 210 */       dropItem(Item.getItemById(var4), 1);
/*     */     }
/* 212 */     else if (cause.getEntity() instanceof EntityCreeper && cause.getEntity() != this && ((EntityCreeper)cause.getEntity()).getPowered() && ((EntityCreeper)cause.getEntity()).isAIEnabled()) {
/*     */       
/* 214 */       ((EntityCreeper)cause.getEntity()).func_175493_co();
/* 215 */       entityDropItem(new ItemStack(Items.skull, 1, 4), 0.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 221 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPowered() {
/* 229 */     return (this.dataWatcher.getWatchableObjectByte(17) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCreeperFlashIntensity(float p_70831_1_) {
/* 237 */     return (this.lastActiveTime + (this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (this.fuseTime - 2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 242 */     return Items.gunpowder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCreeperState() {
/* 250 */     return this.dataWatcher.getWatchableObjectByte(16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreeperState(int p_70829_1_) {
/* 258 */     this.dataWatcher.updateObject(16, Byte.valueOf((byte)p_70829_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStruckByLightning(EntityLightningBolt lightningBolt) {
/* 266 */     super.onStruckByLightning(lightningBolt);
/* 267 */     this.dataWatcher.updateObject(17, Byte.valueOf((byte)1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean interact(EntityPlayer p_70085_1_) {
/* 275 */     ItemStack var2 = p_70085_1_.inventory.getCurrentItem();
/*     */     
/* 277 */     if (var2 != null && var2.getItem() == Items.flint_and_steel) {
/*     */       
/* 279 */       this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "fire.ignite", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
/* 280 */       p_70085_1_.swingItem();
/*     */       
/* 282 */       if (!this.worldObj.isRemote) {
/*     */         
/* 284 */         func_146079_cb();
/* 285 */         var2.damageItem(1, (EntityLivingBase)p_70085_1_);
/* 286 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 290 */     return super.interact(p_70085_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_146077_cc() {
/* 295 */     if (!this.worldObj.isRemote) {
/*     */       
/* 297 */       boolean var1 = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
/* 298 */       float var2 = getPowered() ? 2.0F : 1.0F;
/* 299 */       this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, this.explosionRadius * var2, var1);
/* 300 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_146078_ca() {
/* 306 */     return (this.dataWatcher.getWatchableObjectByte(18) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146079_cb() {
/* 311 */     this.dataWatcher.updateObject(18, Byte.valueOf((byte)1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAIEnabled() {
/* 319 */     return (this.field_175494_bm < 1 && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175493_co() {
/* 324 */     this.field_175494_bm++;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityCreeper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */