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
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class EntityAIFindEntityNearestPlayer
/*     */   extends EntityAIBase
/*     */ {
/*  20 */   private static final Logger field_179436_a = LogManager.getLogger();
/*     */   
/*     */   private EntityLiving field_179434_b;
/*     */   private final Predicate field_179435_c;
/*     */   private final EntityAINearestAttackableTarget.Sorter field_179432_d;
/*     */   private EntityLivingBase field_179433_e;
/*     */   private static final String __OBFID = "CL_00002248";
/*     */   
/*     */   public EntityAIFindEntityNearestPlayer(EntityLiving p_i45882_1_) {
/*  29 */     this.field_179434_b = p_i45882_1_;
/*     */     
/*  31 */     if (p_i45882_1_ instanceof net.minecraft.entity.EntityCreature)
/*     */     {
/*  33 */       field_179436_a.warn("Use NearestAttackableTargetGoal.class for PathfinerMob mobs!");
/*     */     }
/*     */     
/*  36 */     this.field_179435_c = new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002247";
/*     */         
/*     */         public boolean func_179880_a(Entity p_179880_1_) {
/*  41 */           if (!(p_179880_1_ instanceof EntityPlayer))
/*     */           {
/*  43 */             return false;
/*     */           }
/*     */ 
/*     */           
/*  47 */           double var2 = EntityAIFindEntityNearestPlayer.this.func_179431_f();
/*     */           
/*  49 */           if (p_179880_1_.isSneaking())
/*     */           {
/*  51 */             var2 *= 0.800000011920929D;
/*     */           }
/*     */           
/*  54 */           if (p_179880_1_.isInvisible()) {
/*     */             
/*  56 */             float var4 = ((EntityPlayer)p_179880_1_).getArmorVisibility();
/*     */             
/*  58 */             if (var4 < 0.1F)
/*     */             {
/*  60 */               var4 = 0.1F;
/*     */             }
/*     */             
/*  63 */             var2 *= (0.7F * var4);
/*     */           } 
/*     */           
/*  66 */           return (p_179880_1_.getDistanceToEntity((Entity)EntityAIFindEntityNearestPlayer.this.field_179434_b) > var2) ? false : EntityAITarget.func_179445_a(EntityAIFindEntityNearestPlayer.this.field_179434_b, (EntityLivingBase)p_179880_1_, false, true);
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  71 */           return func_179880_a((Entity)p_apply_1_);
/*     */         }
/*     */       };
/*  74 */     this.field_179432_d = new EntityAINearestAttackableTarget.Sorter((Entity)p_i45882_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  82 */     double var1 = func_179431_f();
/*  83 */     List<?> var3 = this.field_179434_b.worldObj.func_175647_a(EntityPlayer.class, this.field_179434_b.getEntityBoundingBox().expand(var1, 4.0D, var1), this.field_179435_c);
/*  84 */     Collections.sort(var3, this.field_179432_d);
/*     */     
/*  86 */     if (var3.isEmpty())
/*     */     {
/*  88 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  92 */     this.field_179433_e = (EntityLivingBase)var3.get(0);
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/* 102 */     EntityLivingBase var1 = this.field_179434_b.getAttackTarget();
/*     */     
/* 104 */     if (var1 == null)
/*     */     {
/* 106 */       return false;
/*     */     }
/* 108 */     if (!var1.isEntityAlive())
/*     */     {
/* 110 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 114 */     Team var2 = this.field_179434_b.getTeam();
/* 115 */     Team var3 = var1.getTeam();
/*     */     
/* 117 */     if (var2 != null && var3 == var2)
/*     */     {
/* 119 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 123 */     double var4 = func_179431_f();
/* 124 */     return (this.field_179434_b.getDistanceSqToEntity((Entity)var1) > var4 * var4) ? false : (!(var1 instanceof EntityPlayerMP && ((EntityPlayerMP)var1).theItemInWorldManager.isCreative()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 134 */     this.field_179434_b.setAttackTarget(this.field_179433_e);
/* 135 */     super.startExecuting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 143 */     this.field_179434_b.setAttackTarget(null);
/* 144 */     super.startExecuting();
/*     */   }
/*     */ 
/*     */   
/*     */   protected double func_179431_f() {
/* 149 */     IAttributeInstance var1 = this.field_179434_b.getEntityAttribute(SharedMonsterAttributes.followRange);
/* 150 */     return (var1 == null) ? 16.0D : var1.getAttributeValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIFindEntityNearestPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */