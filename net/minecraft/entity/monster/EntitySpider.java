/*     */ package net.minecraft.entity.monster;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.pathfinding.PathNavigateClimber;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySpider
/*     */   extends EntityMob {
/*     */   public EntitySpider(World worldIn) {
/*  35 */     super(worldIn);
/*  36 */     setSize(1.4F, 0.9F);
/*  37 */     this.tasks.addTask(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  38 */     this.tasks.addTask(2, this.field_175455_a);
/*  39 */     this.tasks.addTask(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4F));
/*  40 */     this.tasks.addTask(4, (EntityAIBase)new AISpiderAttack(EntityPlayer.class));
/*  41 */     this.tasks.addTask(4, (EntityAIBase)new AISpiderAttack(EntityIronGolem.class));
/*  42 */     this.tasks.addTask(5, (EntityAIBase)new EntityAIWander(this, 0.8D));
/*  43 */     this.tasks.addTask(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  44 */     this.tasks.addTask(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  45 */     this.targetTasks.addTask(1, (EntityAIBase)new EntityAIHurtByTarget(this, false, new Class[0]));
/*  46 */     this.targetTasks.addTask(2, (EntityAIBase)new AISpiderTarget(EntityPlayer.class));
/*  47 */     this.targetTasks.addTask(3, (EntityAIBase)new AISpiderTarget(EntityIronGolem.class));
/*     */   }
/*     */   private static final String __OBFID = "CL_00001699";
/*     */   
/*     */   protected PathNavigate func_175447_b(World worldIn) {
/*  52 */     return (PathNavigate)new PathNavigateClimber((EntityLiving)this, worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  57 */     super.entityInit();
/*  58 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  66 */     super.onUpdate();
/*     */     
/*  68 */     if (!this.worldObj.isRemote)
/*     */     {
/*  70 */       setBesideClimbableBlock(this.isCollidedHorizontally);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  76 */     super.applyEntityAttributes();
/*  77 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16.0D);
/*  78 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000001192092896D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  86 */     return "mob.spider.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  94 */     return "mob.spider.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 102 */     return "mob.spider.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
/* 107 */     playSound("mob.spider.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDropItem() {
/* 112 */     return Items.string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
/* 120 */     super.dropFewItems(p_70628_1_, p_70628_2_);
/*     */     
/* 122 */     if (p_70628_1_ && (this.rand.nextInt(3) == 0 || this.rand.nextInt(1 + p_70628_2_) > 0))
/*     */     {
/* 124 */       dropItem(Items.spider_eye, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnLadder() {
/* 133 */     return isBesideClimbableBlock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInWeb() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 146 */     return EnumCreatureAttribute.ARTHROPOD;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPotionApplicable(PotionEffect p_70687_1_) {
/* 151 */     return (p_70687_1_.getPotionID() == Potion.poison.id) ? false : super.isPotionApplicable(p_70687_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBesideClimbableBlock() {
/* 160 */     return ((this.dataWatcher.getWatchableObjectByte(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBesideClimbableBlock(boolean p_70839_1_) {
/* 169 */     byte var2 = this.dataWatcher.getWatchableObjectByte(16);
/*     */     
/* 171 */     if (p_70839_1_) {
/*     */       
/* 173 */       var2 = (byte)(var2 | 0x1);
/*     */     }
/*     */     else {
/*     */       
/* 177 */       var2 = (byte)(var2 & 0xFFFFFFFE);
/*     */     } 
/*     */     
/* 180 */     this.dataWatcher.updateObject(16, Byte.valueOf(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
/* 185 */     Object p_180482_2_1 = super.func_180482_a(p_180482_1_, p_180482_2_);
/*     */     
/* 187 */     if (this.worldObj.rand.nextInt(100) == 0) {
/*     */       
/* 189 */       EntitySkeleton var3 = new EntitySkeleton(this.worldObj);
/* 190 */       var3.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
/* 191 */       var3.func_180482_a(p_180482_1_, (IEntityLivingData)null);
/* 192 */       this.worldObj.spawnEntityInWorld((Entity)var3);
/* 193 */       var3.mountEntity((Entity)this);
/*     */     } 
/*     */     
/* 196 */     if (p_180482_2_1 == null) {
/*     */       
/* 198 */       p_180482_2_1 = new GroupData();
/*     */       
/* 200 */       if (this.worldObj.getDifficulty() == EnumDifficulty.HARD && this.worldObj.rand.nextFloat() < 0.1F * p_180482_1_.func_180170_c())
/*     */       {
/* 202 */         ((GroupData)p_180482_2_1).func_111104_a(this.worldObj.rand);
/*     */       }
/*     */     } 
/*     */     
/* 206 */     if (p_180482_2_1 instanceof GroupData) {
/*     */       
/* 208 */       int var5 = ((GroupData)p_180482_2_1).field_111105_a;
/*     */       
/* 210 */       if (var5 > 0 && Potion.potionTypes[var5] != null)
/*     */       {
/* 212 */         addPotionEffect(new PotionEffect(var5, 2147483647));
/*     */       }
/*     */     } 
/*     */     
/* 216 */     return (IEntityLivingData)p_180482_2_1;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEyeHeight() {
/* 221 */     return 0.65F;
/*     */   }
/*     */   
/*     */   class AISpiderAttack
/*     */     extends EntityAIAttackOnCollide
/*     */   {
/*     */     private static final String __OBFID = "CL_00002197";
/*     */     
/*     */     public AISpiderAttack(Class p_i45819_2_) {
/* 230 */       super(EntitySpider.this, p_i45819_2_, 1.0D, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean continueExecuting() {
/* 235 */       float var1 = this.attacker.getBrightness(1.0F);
/*     */       
/* 237 */       if (var1 >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
/*     */         
/* 239 */         this.attacker.setAttackTarget(null);
/* 240 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 244 */       return super.continueExecuting();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected double func_179512_a(EntityLivingBase p_179512_1_) {
/* 250 */       return (4.0F + p_179512_1_.width);
/*     */     }
/*     */   }
/*     */   
/*     */   class AISpiderTarget
/*     */     extends EntityAINearestAttackableTarget
/*     */   {
/*     */     private static final String __OBFID = "CL_00002196";
/*     */     
/*     */     public AISpiderTarget(Class p_i45818_2_) {
/* 260 */       super(EntitySpider.this, p_i45818_2_, true);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean shouldExecute() {
/* 265 */       float var1 = this.taskOwner.getBrightness(1.0F);
/* 266 */       return (var1 >= 0.5F) ? false : super.shouldExecute();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class GroupData
/*     */     implements IEntityLivingData
/*     */   {
/*     */     public int field_111105_a;
/*     */     private static final String __OBFID = "CL_00001700";
/*     */     
/*     */     public void func_111104_a(Random p_111104_1_) {
/* 277 */       int var2 = p_111104_1_.nextInt(5);
/*     */       
/* 279 */       if (var2 <= 1) {
/*     */         
/* 281 */         this.field_111105_a = Potion.moveSpeed.id;
/*     */       }
/* 283 */       else if (var2 <= 2) {
/*     */         
/* 285 */         this.field_111105_a = Potion.damageBoost.id;
/*     */       }
/* 287 */       else if (var2 <= 3) {
/*     */         
/* 289 */         this.field_111105_a = Potion.regeneration.id;
/*     */       }
/* 291 */       else if (var2 <= 4) {
/*     */         
/* 293 */         this.field_111105_a = Potion.invisibility.id;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\monster\EntitySpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */