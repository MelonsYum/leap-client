/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFlower;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIDefendVillage;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookAtVillager;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
/*     */ import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityIronGolem
/*     */   extends EntityGolem
/*     */ {
/*     */   private int homeCheckTimer;
/*     */   Village villageObj;
/*     */   private int attackTimer;
/*     */   private int holdRoseTick;
/*     */   private static final String __OBFID = "CL_00001652";
/*     */   
/*     */   public EntityIronGolem(World worldIn) {
/*  48 */     super(worldIn);
/*  49 */     setSize(1.4F, 2.9F);
/*  50 */     ((PathNavigateGround)getNavigator()).func_179690_a(true);
/*  51 */     this.tasks.addTask(1, (EntityAIBase)new EntityAIAttackOnCollide(this, 1.0D, true));
/*  52 */     this.tasks.addTask(2, (EntityAIBase)new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
/*  53 */     this.tasks.addTask(3, (EntityAIBase)new EntityAIMoveThroughVillage(this, 0.6D, true));
/*  54 */     this.tasks.addTask(4, (EntityAIBase)new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  55 */     this.tasks.addTask(5, (EntityAIBase)new EntityAILookAtVillager(this));
/*  56 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWander(this, 0.6D));
/*  57 */     this.tasks.addTask(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*  58 */     this.tasks.addTask(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  59 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIDefendVillage(this));
/*  60 */     this.targetTasks.addTask(2, (EntityAIBase)new EntityAIHurtByTarget(this, false, new Class[0]));
/*  61 */     this.targetTasks.addTask(3, (EntityAIBase)new AINearestAttackableTargetNonCreeper(this, EntityLiving.class, 10, false, true, IMob.field_175450_e));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  66 */     super.entityInit();
/*  67 */     this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateAITasks() {
/*  72 */     if (--this.homeCheckTimer <= 0) {
/*     */       
/*  74 */       this.homeCheckTimer = 70 + this.rand.nextInt(50);
/*  75 */       this.villageObj = this.worldObj.getVillageCollection().func_176056_a(new BlockPos((Entity)this), 32);
/*     */       
/*  77 */       if (this.villageObj == null) {
/*     */         
/*  79 */         detachHome();
/*     */       }
/*     */       else {
/*     */         
/*  83 */         BlockPos var1 = this.villageObj.func_180608_a();
/*  84 */         func_175449_a(var1, (int)(this.villageObj.getVillageRadius() * 0.6F));
/*     */       } 
/*     */     } 
/*     */     
/*  88 */     super.updateAITasks();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  93 */     super.applyEntityAttributes();
/*  94 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
/*  95 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int decreaseAirSupply(int p_70682_1_) {
/* 103 */     return p_70682_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void collideWithEntity(Entity p_82167_1_) {
/* 108 */     if (p_82167_1_ instanceof IMob && getRNG().nextInt(20) == 0)
/*     */     {
/* 110 */       setAttackTarget((EntityLivingBase)p_82167_1_);
/*     */     }
/*     */     
/* 113 */     super.collideWithEntity(p_82167_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 122 */     super.onLivingUpdate();
/*     */     
/* 124 */     if (this.attackTimer > 0)
/*     */     {
/* 126 */       this.attackTimer--;
/*     */     }
/*     */     
/* 129 */     if (this.holdRoseTick > 0)
/*     */     {
/* 131 */       this.holdRoseTick--;
/*     */     }
/*     */     
/* 134 */     if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0) {
/*     */       
/* 136 */       int var1 = MathHelper.floor_double(this.posX);
/* 137 */       int var2 = MathHelper.floor_double(this.posY - 0.20000000298023224D);
/* 138 */       int var3 = MathHelper.floor_double(this.posZ);
/* 139 */       IBlockState var4 = this.worldObj.getBlockState(new BlockPos(var1, var2, var3));
/* 140 */       Block var5 = var4.getBlock();
/*     */       
/* 142 */       if (var5.getMaterial() != Material.air)
/*     */       {
/* 144 */         this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextFloat() - 0.5D) * this.width, (getEntityBoundingBox()).minY + 0.1D, this.posZ + (this.rand.nextFloat() - 0.5D) * this.width, 4.0D * (this.rand.nextFloat() - 0.5D), 0.5D, (this.rand.nextFloat() - 0.5D) * 4.0D, new int[] { Block.getStateId(var4) });
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackClass(Class<?> p_70686_1_) {
/* 154 */     return (isPlayerCreated() && EntityPlayer.class.isAssignableFrom(p_70686_1_)) ? false : super.canAttackClass(p_70686_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound tagCompound) {
/* 162 */     super.writeEntityToNBT(tagCompound);
/* 163 */     tagCompound.setBoolean("PlayerCreated", isPlayerCreated());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound tagCompund) {
/* 171 */     super.readEntityFromNBT(tagCompund);
/* 172 */     setPlayerCreated(tagCompund.getBoolean("PlayerCreated"));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity p_70652_1_) {
/* 177 */     this.attackTimer = 10;
/* 178 */     this.worldObj.setEntityState((Entity)this, (byte)4);
/* 179 */     boolean var2 = p_70652_1_.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), (7 + this.rand.nextInt(15)));
/*     */     
/* 181 */     if (var2) {
/*     */       
/* 183 */       p_70652_1_.motionY += 0.4000000059604645D;
/* 184 */       func_174815_a((EntityLivingBase)this, p_70652_1_);
/*     */     } 
/*     */     
/* 187 */     playSound("mob.irongolem.throw", 1.0F, 1.0F);
/* 188 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(byte p_70103_1_) {
/* 193 */     if (p_70103_1_ == 4) {
/*     */       
/* 195 */       this.attackTimer = 10;
/* 196 */       playSound("mob.irongolem.throw", 1.0F, 1.0F);
/*     */     }
/* 198 */     else if (p_70103_1_ == 11) {
/*     */       
/* 200 */       this.holdRoseTick = 400;
/*     */     }
/*     */     else {
/*     */       
/* 204 */       super.handleHealthUpdate(p_70103_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Village getVillage() {
/* 210 */     return this.villageObj;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAttackTimer() {
/* 215 */     return this.attackTimer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHoldingRose(boolean p_70851_1_) {
/* 220 */     this.holdRoseTick = p_70851_1_ ? 400 : 0;
/* 221 */     this.worldObj.setEntityState((Entity)this, (byte)11);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 229 */     return "mob.irongolem.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 237 */     return "mob.irongolem.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/* 242 */     playSound("mob.irongolem.walk", 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 250 */     int var3 = this.rand.nextInt(3);
/*     */     
/*     */     int var4;
/* 253 */     for (var4 = 0; var4 < var3; var4++)
/*     */     {
/* 255 */       dropItemWithOffset(Item.getItemFromBlock((Block)Blocks.red_flower), 1, BlockFlower.EnumFlowerType.POPPY.func_176968_b());
/*     */     }
/*     */     
/* 258 */     var4 = 3 + this.rand.nextInt(3);
/*     */     
/* 260 */     for (int var5 = 0; var5 < var4; var5++)
/*     */     {
/* 262 */       dropItem(Items.iron_ingot, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHoldRoseTick() {
/* 268 */     return this.holdRoseTick;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlayerCreated() {
/* 273 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerCreated(boolean p_70849_1_) {
/* 278 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 280 */     if (p_70849_1_) {
/*     */       
/* 282 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 0x1)));
/*     */     }
/*     */     else {
/*     */       
/* 286 */       this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource cause) {
/* 295 */     if (!isPlayerCreated() && this.attackingPlayer != null && this.villageObj != null)
/*     */     {
/* 297 */       this.villageObj.setReputationForPlayer(this.attackingPlayer.getName(), -5);
/*     */     }
/*     */     
/* 300 */     super.onDeath(cause);
/*     */   }
/*     */   
/*     */   static class AINearestAttackableTargetNonCreeper
/*     */     extends EntityAINearestAttackableTarget
/*     */   {
/*     */     private static final String __OBFID = "CL_00002231";
/*     */     
/*     */     public AINearestAttackableTargetNonCreeper(final EntityCreature p_i45858_1_, Class p_i45858_2_, int p_i45858_3_, boolean p_i45858_4_, boolean p_i45858_5_, final Predicate p_i45858_6_) {
/* 309 */       super(p_i45858_1_, p_i45858_2_, p_i45858_3_, p_i45858_4_, p_i45858_5_, p_i45858_6_);
/* 310 */       this.targetEntitySelector = new Predicate()
/*     */         {
/*     */           private static final String __OBFID = "CL_00002230";
/*     */           
/*     */           public boolean func_180096_a(EntityLivingBase p_180096_1_) {
/* 315 */             if (p_i45858_6_ != null && !p_i45858_6_.apply(p_180096_1_))
/*     */             {
/* 317 */               return false;
/*     */             }
/* 319 */             if (p_180096_1_ instanceof EntityCreeper)
/*     */             {
/* 321 */               return false;
/*     */             }
/*     */ 
/*     */             
/* 325 */             if (p_180096_1_ instanceof EntityPlayer) {
/*     */               
/* 327 */               double var2 = EntityIronGolem.AINearestAttackableTargetNonCreeper.this.getTargetDistance();
/*     */               
/* 329 */               if (p_180096_1_.isSneaking())
/*     */               {
/* 331 */                 var2 *= 0.800000011920929D;
/*     */               }
/*     */               
/* 334 */               if (p_180096_1_.isInvisible()) {
/*     */                 
/* 336 */                 float var4 = ((EntityPlayer)p_180096_1_).getArmorVisibility();
/*     */                 
/* 338 */                 if (var4 < 0.1F)
/*     */                 {
/* 340 */                   var4 = 0.1F;
/*     */                 }
/*     */                 
/* 343 */                 var2 *= (0.7F * var4);
/*     */               } 
/*     */               
/* 346 */               if (p_180096_1_.getDistanceToEntity((Entity)p_i45858_1_) > var2)
/*     */               {
/* 348 */                 return false;
/*     */               }
/*     */             } 
/*     */             
/* 352 */             return EntityIronGolem.AINearestAttackableTargetNonCreeper.this.isSuitableTarget(p_180096_1_, false);
/*     */           }
/*     */ 
/*     */           
/*     */           public boolean apply(Object p_apply_1_) {
/* 357 */             return func_180096_a((EntityLivingBase)p_apply_1_);
/*     */           }
/*     */         };
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntityIronGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */