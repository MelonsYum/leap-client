/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import com.google.common.base.Predicate;
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.IEntitySelector;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigate;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class EntityAIAvoidEntity
/*     */   extends EntityAIBase {
/*  15 */   public final Predicate field_179509_a = new Predicate()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001575";
/*     */       
/*     */       public boolean func_180419_a(Entity p_180419_1_) {
/*  20 */         return (p_180419_1_.isEntityAlive() && EntityAIAvoidEntity.this.theEntity.getEntitySenses().canSee(p_180419_1_));
/*     */       }
/*     */       
/*     */       public boolean apply(Object p_apply_1_) {
/*  24 */         return func_180419_a((Entity)p_apply_1_);
/*     */       }
/*     */     };
/*     */   
/*     */   protected EntityCreature theEntity;
/*     */   
/*     */   private double farSpeed;
/*     */   
/*     */   private double nearSpeed;
/*     */   
/*     */   protected Entity closestLivingEntity;
/*     */   
/*     */   private float field_179508_f;
/*     */   
/*     */   private PathEntity entityPathEntity;
/*     */   
/*     */   private PathNavigate entityPathNavigate;
/*     */   private Predicate field_179510_i;
/*     */   private static final String __OBFID = "CL_00001574";
/*     */   
/*     */   public EntityAIAvoidEntity(EntityCreature p_i45890_1_, Predicate p_i45890_2_, float p_i45890_3_, double p_i45890_4_, double p_i45890_6_) {
/*  45 */     this.theEntity = p_i45890_1_;
/*  46 */     this.field_179510_i = p_i45890_2_;
/*  47 */     this.field_179508_f = p_i45890_3_;
/*  48 */     this.farSpeed = p_i45890_4_;
/*  49 */     this.nearSpeed = p_i45890_6_;
/*  50 */     this.entityPathNavigate = p_i45890_1_.getNavigator();
/*  51 */     setMutexBits(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  59 */     List<Entity> var1 = this.theEntity.worldObj.func_175674_a((Entity)this.theEntity, this.theEntity.getEntityBoundingBox().expand(this.field_179508_f, 3.0D, this.field_179508_f), Predicates.and(new Predicate[] { IEntitySelector.field_180132_d, this.field_179509_a, this.field_179510_i }));
/*     */     
/*  61 */     if (var1.isEmpty())
/*     */     {
/*  63 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  67 */     this.closestLivingEntity = var1.get(0);
/*  68 */     Vec3 var2 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, new Vec3(this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
/*     */     
/*  70 */     if (var2 == null)
/*     */     {
/*  72 */       return false;
/*     */     }
/*  74 */     if (this.closestLivingEntity.getDistanceSq(var2.xCoord, var2.yCoord, var2.zCoord) < this.closestLivingEntity.getDistanceSqToEntity((Entity)this.theEntity))
/*     */     {
/*  76 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  80 */     this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(var2.xCoord, var2.yCoord, var2.zCoord);
/*  81 */     return (this.entityPathEntity == null) ? false : this.entityPathEntity.isDestinationSame(var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  91 */     return !this.entityPathNavigate.noPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  99 */     this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 107 */     this.closestLivingEntity = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 115 */     if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D) {
/*     */       
/* 117 */       this.theEntity.getNavigator().setSpeed(this.nearSpeed);
/*     */     }
/*     */     else {
/*     */       
/* 121 */       this.theEntity.getNavigator().setSpeed(this.farSpeed);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIAvoidEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */