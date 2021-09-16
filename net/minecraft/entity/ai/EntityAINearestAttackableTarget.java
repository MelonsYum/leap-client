/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAINearestAttackableTarget
/*     */   extends EntityAITarget
/*     */ {
/*     */   protected final Class targetClass;
/*     */   private final int targetChance;
/*     */   protected final Sorter theNearestAttackableTargetSorter;
/*     */   protected Predicate targetEntitySelector;
/*     */   protected EntityLivingBase targetEntity;
/*     */   private static final String __OBFID = "CL_00001620";
/*     */   
/*     */   public EntityAINearestAttackableTarget(EntityCreature p_i45878_1_, Class p_i45878_2_, boolean p_i45878_3_) {
/*  32 */     this(p_i45878_1_, p_i45878_2_, p_i45878_3_, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAINearestAttackableTarget(EntityCreature p_i45879_1_, Class p_i45879_2_, boolean p_i45879_3_, boolean p_i45879_4_) {
/*  37 */     this(p_i45879_1_, p_i45879_2_, 10, p_i45879_3_, p_i45879_4_, (Predicate)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAINearestAttackableTarget(EntityCreature p_i45880_1_, Class p_i45880_2_, int p_i45880_3_, boolean p_i45880_4_, boolean p_i45880_5_, final Predicate p_i45880_6_) {
/*  42 */     super(p_i45880_1_, p_i45880_4_, p_i45880_5_);
/*  43 */     this.targetClass = p_i45880_2_;
/*  44 */     this.targetChance = p_i45880_3_;
/*  45 */     this.theNearestAttackableTargetSorter = new Sorter((Entity)p_i45880_1_);
/*  46 */     setMutexBits(1);
/*  47 */     this.targetEntitySelector = new Predicate()
/*     */       {
/*     */         private static final String __OBFID = "CL_00001621";
/*     */         
/*     */         public boolean func_179878_a(EntityLivingBase p_179878_1_) {
/*  52 */           if (p_i45880_6_ != null && !p_i45880_6_.apply(p_179878_1_))
/*     */           {
/*  54 */             return false;
/*     */           }
/*     */ 
/*     */           
/*  58 */           if (p_179878_1_ instanceof EntityPlayer) {
/*     */             
/*  60 */             double var2 = EntityAINearestAttackableTarget.this.getTargetDistance();
/*     */             
/*  62 */             if (p_179878_1_.isSneaking())
/*     */             {
/*  64 */               var2 *= 0.800000011920929D;
/*     */             }
/*     */             
/*  67 */             if (p_179878_1_.isInvisible()) {
/*     */               
/*  69 */               float var4 = ((EntityPlayer)p_179878_1_).getArmorVisibility();
/*     */               
/*  71 */               if (var4 < 0.1F)
/*     */               {
/*  73 */                 var4 = 0.1F;
/*     */               }
/*     */               
/*  76 */               var2 *= (0.7F * var4);
/*     */             } 
/*     */             
/*  79 */             if (p_179878_1_.getDistanceToEntity((Entity)EntityAINearestAttackableTarget.this.taskOwner) > var2)
/*     */             {
/*  81 */               return false;
/*     */             }
/*     */           } 
/*     */           
/*  85 */           return EntityAINearestAttackableTarget.this.isSuitableTarget(p_179878_1_, false);
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean apply(Object p_apply_1_) {
/*  90 */           return func_179878_a((EntityLivingBase)p_apply_1_);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/* 100 */     if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0)
/*     */     {
/* 102 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 106 */     double var1 = getTargetDistance();
/* 107 */     List<?> var3 = this.taskOwner.worldObj.func_175647_a(this.targetClass, this.taskOwner.getEntityBoundingBox().expand(var1, 4.0D, var1), Predicates.and(this.targetEntitySelector, IEntitySelector.field_180132_d));
/* 108 */     Collections.sort(var3, this.theNearestAttackableTargetSorter);
/*     */     
/* 110 */     if (var3.isEmpty())
/*     */     {
/* 112 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 116 */     this.targetEntity = (EntityLivingBase)var3.get(0);
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 127 */     this.taskOwner.setAttackTarget(this.targetEntity);
/* 128 */     super.startExecuting();
/*     */   }
/*     */   
/*     */   public static class Sorter
/*     */     implements Comparator
/*     */   {
/*     */     private final Entity theEntity;
/*     */     private static final String __OBFID = "CL_00001622";
/*     */     
/*     */     public Sorter(Entity p_i1662_1_) {
/* 138 */       this.theEntity = p_i1662_1_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int compare(Entity p_compare_1_, Entity p_compare_2_) {
/* 143 */       double var3 = this.theEntity.getDistanceSqToEntity(p_compare_1_);
/* 144 */       double var5 = this.theEntity.getDistanceSqToEntity(p_compare_2_);
/* 145 */       return (var3 < var5) ? -1 : ((var3 > var5) ? 1 : 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public int compare(Object p_compare_1_, Object p_compare_2_) {
/* 150 */       return compare((Entity)p_compare_1_, (Entity)p_compare_2_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAINearestAttackableTarget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */