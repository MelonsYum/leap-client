/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.monster.EntityIronGolem;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ 
/*     */ public class EntityAIFollowGolem
/*     */   extends EntityAIBase {
/*     */   private EntityVillager theVillager;
/*     */   private EntityIronGolem theGolem;
/*     */   private int takeGolemRoseTick;
/*     */   private boolean tookGolemRose;
/*     */   private static final String __OBFID = "CL_00001615";
/*     */   
/*     */   public EntityAIFollowGolem(EntityVillager p_i1656_1_) {
/*  18 */     this.theVillager = p_i1656_1_;
/*  19 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  27 */     if (this.theVillager.getGrowingAge() >= 0)
/*     */     {
/*  29 */       return false;
/*     */     }
/*  31 */     if (!this.theVillager.worldObj.isDaytime())
/*     */     {
/*  33 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  37 */     List var1 = this.theVillager.worldObj.getEntitiesWithinAABB(EntityIronGolem.class, this.theVillager.getEntityBoundingBox().expand(6.0D, 2.0D, 6.0D));
/*     */     
/*  39 */     if (var1.isEmpty())
/*     */     {
/*  41 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  45 */     Iterator<EntityIronGolem> var2 = var1.iterator();
/*     */     
/*  47 */     while (var2.hasNext()) {
/*     */       
/*  49 */       EntityIronGolem var3 = var2.next();
/*     */       
/*  51 */       if (var3.getHoldRoseTick() > 0) {
/*     */         
/*  53 */         this.theGolem = var3;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*  58 */     return (this.theGolem != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  68 */     return (this.theGolem.getHoldRoseTick() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  76 */     this.takeGolemRoseTick = this.theVillager.getRNG().nextInt(320);
/*  77 */     this.tookGolemRose = false;
/*  78 */     this.theGolem.getNavigator().clearPathEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  86 */     this.theGolem = null;
/*  87 */     this.theVillager.getNavigator().clearPathEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  95 */     this.theVillager.getLookHelper().setLookPositionWithEntity((Entity)this.theGolem, 30.0F, 30.0F);
/*     */     
/*  97 */     if (this.theGolem.getHoldRoseTick() == this.takeGolemRoseTick) {
/*     */       
/*  99 */       this.theVillager.getNavigator().tryMoveToEntityLiving((Entity)this.theGolem, 0.5D);
/* 100 */       this.tookGolemRose = true;
/*     */     } 
/*     */     
/* 103 */     if (this.tookGolemRose && this.theVillager.getDistanceSqToEntity((Entity)this.theGolem) < 4.0D) {
/*     */       
/* 105 */       this.theGolem.setHoldingRose(false);
/* 106 */       this.theVillager.getNavigator().clearPathEntity();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIFollowGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */