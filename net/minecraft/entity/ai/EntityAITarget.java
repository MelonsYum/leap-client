/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityOwnable;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityAITarget
/*     */   extends EntityAIBase
/*     */ {
/*     */   protected final EntityCreature taskOwner;
/*     */   protected boolean shouldCheckSight;
/*     */   private boolean nearbyOnly;
/*     */   private int targetSearchStatus;
/*     */   private int targetSearchDelay;
/*     */   private int targetUnseenTicks;
/*     */   private static final String __OBFID = "CL_00001626";
/*     */   
/*     */   public EntityAITarget(EntityCreature p_i1669_1_, boolean p_i1669_2_) {
/*  51 */     this(p_i1669_1_, p_i1669_2_, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAITarget(EntityCreature p_i1670_1_, boolean p_i1670_2_, boolean p_i1670_3_) {
/*  56 */     this.taskOwner = p_i1670_1_;
/*  57 */     this.shouldCheckSight = p_i1670_2_;
/*  58 */     this.nearbyOnly = p_i1670_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  66 */     EntityLivingBase var1 = this.taskOwner.getAttackTarget();
/*     */     
/*  68 */     if (var1 == null)
/*     */     {
/*  70 */       return false;
/*     */     }
/*  72 */     if (!var1.isEntityAlive())
/*     */     {
/*  74 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  78 */     Team var2 = this.taskOwner.getTeam();
/*  79 */     Team var3 = var1.getTeam();
/*     */     
/*  81 */     if (var2 != null && var3 == var2)
/*     */     {
/*  83 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  87 */     double var4 = getTargetDistance();
/*     */     
/*  89 */     if (this.taskOwner.getDistanceSqToEntity((Entity)var1) > var4 * var4)
/*     */     {
/*  91 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  95 */     if (this.shouldCheckSight)
/*     */     {
/*  97 */       if (this.taskOwner.getEntitySenses().canSee((Entity)var1)) {
/*     */         
/*  99 */         this.targetUnseenTicks = 0;
/*     */       }
/* 101 */       else if (++this.targetUnseenTicks > 60) {
/*     */         
/* 103 */         return false;
/*     */       } 
/*     */     }
/*     */     
/* 107 */     return !(var1 instanceof EntityPlayer && ((EntityPlayer)var1).capabilities.disableDamage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected double getTargetDistance() {
/* 115 */     IAttributeInstance var1 = this.taskOwner.getEntityAttribute(SharedMonsterAttributes.followRange);
/* 116 */     return (var1 == null) ? 16.0D : var1.getAttributeValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 124 */     this.targetSearchStatus = 0;
/* 125 */     this.targetSearchDelay = 0;
/* 126 */     this.targetUnseenTicks = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 134 */     this.taskOwner.setAttackTarget(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_179445_a(EntityLiving p_179445_0_, EntityLivingBase p_179445_1_, boolean p_179445_2_, boolean p_179445_3_) {
/* 139 */     if (p_179445_1_ == null)
/*     */     {
/* 141 */       return false;
/*     */     }
/* 143 */     if (p_179445_1_ == p_179445_0_)
/*     */     {
/* 145 */       return false;
/*     */     }
/* 147 */     if (!p_179445_1_.isEntityAlive())
/*     */     {
/* 149 */       return false;
/*     */     }
/* 151 */     if (!p_179445_0_.canAttackClass(p_179445_1_.getClass()))
/*     */     {
/* 153 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 157 */     Team var4 = p_179445_0_.getTeam();
/* 158 */     Team var5 = p_179445_1_.getTeam();
/*     */     
/* 160 */     if (var4 != null && var5 == var4)
/*     */     {
/* 162 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 166 */     if (p_179445_0_ instanceof IEntityOwnable && StringUtils.isNotEmpty(((IEntityOwnable)p_179445_0_).func_152113_b())) {
/*     */       
/* 168 */       if (p_179445_1_ instanceof IEntityOwnable && ((IEntityOwnable)p_179445_0_).func_152113_b().equals(((IEntityOwnable)p_179445_1_).func_152113_b()))
/*     */       {
/* 170 */         return false;
/*     */       }
/*     */       
/* 173 */       if (p_179445_1_ == ((IEntityOwnable)p_179445_0_).getOwner())
/*     */       {
/* 175 */         return false;
/*     */       }
/*     */     }
/* 178 */     else if (p_179445_1_ instanceof EntityPlayer && !p_179445_2_ && ((EntityPlayer)p_179445_1_).capabilities.disableDamage) {
/*     */       
/* 180 */       return false;
/*     */     } 
/*     */     
/* 183 */     return !(p_179445_3_ && !p_179445_0_.getEntitySenses().canSee((Entity)p_179445_1_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isSuitableTarget(EntityLivingBase p_75296_1_, boolean p_75296_2_) {
/* 194 */     if (!func_179445_a((EntityLiving)this.taskOwner, p_75296_1_, p_75296_2_, this.shouldCheckSight))
/*     */     {
/* 196 */       return false;
/*     */     }
/* 198 */     if (!this.taskOwner.func_180485_d(new BlockPos((Entity)p_75296_1_)))
/*     */     {
/* 200 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 204 */     if (this.nearbyOnly) {
/*     */       
/* 206 */       if (--this.targetSearchDelay <= 0)
/*     */       {
/* 208 */         this.targetSearchStatus = 0;
/*     */       }
/*     */       
/* 211 */       if (this.targetSearchStatus == 0)
/*     */       {
/* 213 */         this.targetSearchStatus = canEasilyReach(p_75296_1_) ? 1 : 2;
/*     */       }
/*     */       
/* 216 */       if (this.targetSearchStatus == 2)
/*     */       {
/* 218 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 222 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canEasilyReach(EntityLivingBase p_75295_1_) {
/* 231 */     this.targetSearchDelay = 10 + this.taskOwner.getRNG().nextInt(5);
/* 232 */     PathEntity var2 = this.taskOwner.getNavigator().getPathToEntityLiving((Entity)p_75295_1_);
/*     */     
/* 234 */     if (var2 == null)
/*     */     {
/* 236 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 240 */     PathPoint var3 = var2.getFinalPathPoint();
/*     */     
/* 242 */     if (var3 == null)
/*     */     {
/* 244 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 248 */     int var4 = var3.xCoord - MathHelper.floor_double(p_75295_1_.posX);
/* 249 */     int var5 = var3.zCoord - MathHelper.floor_double(p_75295_1_.posZ);
/* 250 */     return ((var4 * var4 + var5 * var5) <= 2.25D);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAITarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */