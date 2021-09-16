/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.world.World;
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
/*     */ public class EntityAIAttackOnCollide
/*     */   extends EntityAIBase
/*     */ {
/*     */   World worldObj;
/*     */   protected EntityCreature attacker;
/*     */   int attackTick;
/*     */   double speedTowardsTarget;
/*     */   boolean longMemory;
/*     */   PathEntity entityPathEntity;
/*     */   Class classTarget;
/*     */   private int field_75445_i;
/*     */   private double field_151497_i;
/*     */   private double field_151495_j;
/*     */   private double field_151496_k;
/*     */   private static final String __OBFID = "CL_00001595";
/*     */   
/*     */   public EntityAIAttackOnCollide(EntityCreature p_i1635_1_, Class p_i1635_2_, double p_i1635_3_, boolean p_i1635_5_) {
/*  38 */     this(p_i1635_1_, p_i1635_3_, p_i1635_5_);
/*  39 */     this.classTarget = p_i1635_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAIAttackOnCollide(EntityCreature p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
/*  44 */     this.attacker = p_i1636_1_;
/*  45 */     this.worldObj = p_i1636_1_.worldObj;
/*  46 */     this.speedTowardsTarget = p_i1636_2_;
/*  47 */     this.longMemory = p_i1636_4_;
/*  48 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  56 */     EntityLivingBase var1 = this.attacker.getAttackTarget();
/*     */     
/*  58 */     if (var1 == null)
/*     */     {
/*  60 */       return false;
/*     */     }
/*  62 */     if (!var1.isEntityAlive())
/*     */     {
/*  64 */       return false;
/*     */     }
/*  66 */     if (this.classTarget != null && !this.classTarget.isAssignableFrom(var1.getClass()))
/*     */     {
/*  68 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  72 */     this.entityPathEntity = this.attacker.getNavigator().getPathToEntityLiving((Entity)var1);
/*  73 */     return (this.entityPathEntity != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  82 */     EntityLivingBase var1 = this.attacker.getAttackTarget();
/*  83 */     return (var1 == null) ? false : (!var1.isEntityAlive() ? false : (!this.longMemory ? (!this.attacker.getNavigator().noPath()) : this.attacker.func_180485_d(new BlockPos((Entity)var1))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  91 */     this.attacker.getNavigator().setPath(this.entityPathEntity, this.speedTowardsTarget);
/*  92 */     this.field_75445_i = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 100 */     this.attacker.getNavigator().clearPathEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 108 */     EntityLivingBase var1 = this.attacker.getAttackTarget();
/* 109 */     this.attacker.getLookHelper().setLookPositionWithEntity((Entity)var1, 30.0F, 30.0F);
/* 110 */     double var2 = this.attacker.getDistanceSq(var1.posX, (var1.getEntityBoundingBox()).minY, var1.posZ);
/* 111 */     double var4 = func_179512_a(var1);
/* 112 */     this.field_75445_i--;
/*     */     
/* 114 */     if ((this.longMemory || this.attacker.getEntitySenses().canSee((Entity)var1)) && this.field_75445_i <= 0 && ((this.field_151497_i == 0.0D && this.field_151495_j == 0.0D && this.field_151496_k == 0.0D) || var1.getDistanceSq(this.field_151497_i, this.field_151495_j, this.field_151496_k) >= 1.0D || this.attacker.getRNG().nextFloat() < 0.05F)) {
/*     */       
/* 116 */       this.field_151497_i = var1.posX;
/* 117 */       this.field_151495_j = (var1.getEntityBoundingBox()).minY;
/* 118 */       this.field_151496_k = var1.posZ;
/* 119 */       this.field_75445_i = 4 + this.attacker.getRNG().nextInt(7);
/*     */       
/* 121 */       if (var2 > 1024.0D) {
/*     */         
/* 123 */         this.field_75445_i += 10;
/*     */       }
/* 125 */       else if (var2 > 256.0D) {
/*     */         
/* 127 */         this.field_75445_i += 5;
/*     */       } 
/*     */       
/* 130 */       if (!this.attacker.getNavigator().tryMoveToEntityLiving((Entity)var1, this.speedTowardsTarget))
/*     */       {
/* 132 */         this.field_75445_i += 15;
/*     */       }
/*     */     } 
/*     */     
/* 136 */     this.attackTick = Math.max(this.attackTick - 1, 0);
/*     */     
/* 138 */     if (var2 <= var4 && this.attackTick <= 0) {
/*     */       
/* 140 */       this.attackTick = 20;
/*     */       
/* 142 */       if (this.attacker.getHeldItem() != null)
/*     */       {
/* 144 */         this.attacker.swingItem();
/*     */       }
/*     */       
/* 147 */       this.attacker.attackEntityAsMob((Entity)var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected double func_179512_a(EntityLivingBase p_179512_1_) {
/* 153 */     return (this.attacker.width * 2.0F * this.attacker.width * 2.0F + p_179512_1_.width);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIAttackOnCollide.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */