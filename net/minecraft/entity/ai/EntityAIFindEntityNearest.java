/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class EntityAIFindEntityNearest
/*     */   extends EntityAIBase {
/*  17 */   private static final Logger field_179444_a = LogManager.getLogger();
/*     */   
/*     */   private EntityLiving field_179442_b;
/*     */   private final Predicate field_179443_c;
/*     */   private final EntityAINearestAttackableTarget.Sorter field_179440_d;
/*     */   private EntityLivingBase field_179441_e;
/*     */   private Class field_179439_f;
/*     */   private static final String __OBFID = "CL_00002250";
/*     */   
/*     */   public EntityAIFindEntityNearest(EntityLiving p_i45884_1_, Class p_i45884_2_) {
/*  27 */     this.field_179442_b = p_i45884_1_;
/*  28 */     this.field_179439_f = p_i45884_2_;
/*     */     
/*  30 */     if (p_i45884_1_ instanceof net.minecraft.entity.EntityCreature)
/*     */     {
/*  32 */       field_179444_a.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
/*     */     }
/*     */     
/*  35 */     this.field_179443_c = new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002249";
/*     */         
/*     */         public boolean func_179876_a(EntityLivingBase p_179876_1_) {
/*  40 */           double var2 = EntityAIFindEntityNearest.this.func_179438_f();
/*     */           
/*  42 */           if (p_179876_1_.isSneaking())
/*     */           {
/*  44 */             var2 *= 0.800000011920929D;
/*     */           }
/*     */           
/*  47 */           return p_179876_1_.isInvisible() ? false : ((p_179876_1_.getDistanceToEntity((Entity)EntityAIFindEntityNearest.this.field_179442_b) > var2) ? false : EntityAITarget.func_179445_a(EntityAIFindEntityNearest.this.field_179442_b, p_179876_1_, false, true));
/*     */         }
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  51 */           return func_179876_a((EntityLivingBase)p_apply_1_);
/*     */         }
/*     */       };
/*  54 */     this.field_179440_d = new EntityAINearestAttackableTarget.Sorter((Entity)p_i45884_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  62 */     double var1 = func_179438_f();
/*  63 */     List<?> var3 = this.field_179442_b.worldObj.func_175647_a(this.field_179439_f, this.field_179442_b.getEntityBoundingBox().expand(var1, 4.0D, var1), this.field_179443_c);
/*  64 */     Collections.sort(var3, this.field_179440_d);
/*     */     
/*  66 */     if (var3.isEmpty())
/*     */     {
/*  68 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  72 */     this.field_179441_e = (EntityLivingBase)var3.get(0);
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  82 */     EntityLivingBase var1 = this.field_179442_b.getAttackTarget();
/*     */     
/*  84 */     if (var1 == null)
/*     */     {
/*  86 */       return false;
/*     */     }
/*  88 */     if (!var1.isEntityAlive())
/*     */     {
/*  90 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  94 */     double var2 = func_179438_f();
/*  95 */     return (this.field_179442_b.getDistanceSqToEntity((Entity)var1) > var2 * var2) ? false : (!(var1 instanceof EntityPlayerMP && ((EntityPlayerMP)var1).theItemInWorldManager.isCreative()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 104 */     this.field_179442_b.setAttackTarget(this.field_179441_e);
/* 105 */     super.startExecuting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 113 */     this.field_179442_b.setAttackTarget(null);
/* 114 */     super.startExecuting();
/*     */   }
/*     */ 
/*     */   
/*     */   protected double func_179438_f() {
/* 119 */     IAttributeInstance var1 = this.field_179442_b.getEntityAttribute(SharedMonsterAttributes.followRange);
/* 120 */     return (var1 == null) ? 16.0D : var1.getAttributeValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIFindEntityNearest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */