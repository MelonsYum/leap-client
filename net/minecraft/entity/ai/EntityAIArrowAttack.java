/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.util.MathHelper;
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
/*     */ public class EntityAIArrowAttack
/*     */   extends EntityAIBase
/*     */ {
/*     */   private final EntityLiving entityHost;
/*     */   private final IRangedAttackMob rangedAttackEntityHost;
/*     */   private EntityLivingBase attackTarget;
/*     */   
/*     */   public EntityAIArrowAttack(IRangedAttackMob p_i1649_1_, double p_i1649_2_, int p_i1649_4_, float p_i1649_5_) {
/*  38 */     this(p_i1649_1_, p_i1649_2_, p_i1649_4_, p_i1649_4_, p_i1649_5_);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  43 */   private int rangedAttackTime = -1; private double entityMoveSpeed;
/*     */   public EntityAIArrowAttack(IRangedAttackMob p_i1650_1_, double p_i1650_2_, int p_i1650_4_, int p_i1650_5_, float p_i1650_6_) {
/*  45 */     if (!(p_i1650_1_ instanceof EntityLivingBase))
/*     */     {
/*  47 */       throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
/*     */     }
/*     */ 
/*     */     
/*  51 */     this.rangedAttackEntityHost = p_i1650_1_;
/*  52 */     this.entityHost = (EntityLiving)p_i1650_1_;
/*  53 */     this.entityMoveSpeed = p_i1650_2_;
/*  54 */     this.field_96561_g = p_i1650_4_;
/*  55 */     this.maxRangedAttackTime = p_i1650_5_;
/*  56 */     this.field_96562_i = p_i1650_6_;
/*  57 */     this.maxAttackDistance = p_i1650_6_ * p_i1650_6_;
/*  58 */     setMutexBits(3);
/*     */   }
/*     */   private int field_75318_f; private int field_96561_g;
/*     */   private int maxRangedAttackTime;
/*     */   private float field_96562_i;
/*     */   private float maxAttackDistance;
/*     */   private static final String __OBFID = "CL_00001609";
/*     */   
/*     */   public boolean shouldExecute() {
/*  67 */     EntityLivingBase var1 = this.entityHost.getAttackTarget();
/*     */     
/*  69 */     if (var1 == null)
/*     */     {
/*  71 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  75 */     this.attackTarget = var1;
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  85 */     return !(!shouldExecute() && this.entityHost.getNavigator().noPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  93 */     this.attackTarget = null;
/*  94 */     this.field_75318_f = 0;
/*  95 */     this.rangedAttackTime = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 103 */     double var1 = this.entityHost.getDistanceSq(this.attackTarget.posX, (this.attackTarget.getEntityBoundingBox()).minY, this.attackTarget.posZ);
/* 104 */     boolean var3 = this.entityHost.getEntitySenses().canSee((Entity)this.attackTarget);
/*     */     
/* 106 */     if (var3) {
/*     */       
/* 108 */       this.field_75318_f++;
/*     */     }
/*     */     else {
/*     */       
/* 112 */       this.field_75318_f = 0;
/*     */     } 
/*     */     
/* 115 */     if (var1 <= this.maxAttackDistance && this.field_75318_f >= 20) {
/*     */       
/* 117 */       this.entityHost.getNavigator().clearPathEntity();
/*     */     }
/*     */     else {
/*     */       
/* 121 */       this.entityHost.getNavigator().tryMoveToEntityLiving((Entity)this.attackTarget, this.entityMoveSpeed);
/*     */     } 
/*     */     
/* 124 */     this.entityHost.getLookHelper().setLookPositionWithEntity((Entity)this.attackTarget, 30.0F, 30.0F);
/*     */ 
/*     */     
/* 127 */     if (--this.rangedAttackTime == 0) {
/*     */       
/* 129 */       if (var1 > this.maxAttackDistance || !var3) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 134 */       float var4 = MathHelper.sqrt_double(var1) / this.field_96562_i;
/* 135 */       float var5 = MathHelper.clamp_float(var4, 0.1F, 1.0F);
/* 136 */       this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, var5);
/* 137 */       this.rangedAttackTime = MathHelper.floor_float(var4 * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
/*     */     }
/* 139 */     else if (this.rangedAttackTime < 0) {
/*     */       
/* 141 */       float var4 = MathHelper.sqrt_double(var1) / this.field_96562_i;
/* 142 */       this.rangedAttackTime = MathHelper.floor_float(var4 * (this.maxRangedAttackTime - this.field_96561_g) + this.field_96561_g);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIArrowAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */