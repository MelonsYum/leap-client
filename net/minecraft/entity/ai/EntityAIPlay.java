/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.util.Vec3;
/*     */ 
/*     */ public class EntityAIPlay extends EntityAIBase {
/*     */   private EntityVillager villagerObj;
/*     */   private EntityLivingBase targetVillager;
/*     */   private double field_75261_c;
/*     */   private int playTime;
/*     */   private static final String __OBFID = "CL_00001605";
/*     */   
/*     */   public EntityAIPlay(EntityVillager p_i1646_1_, double p_i1646_2_) {
/*  19 */     this.villagerObj = p_i1646_1_;
/*  20 */     this.field_75261_c = p_i1646_2_;
/*  21 */     setMutexBits(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  29 */     if (this.villagerObj.getGrowingAge() >= 0)
/*     */     {
/*  31 */       return false;
/*     */     }
/*  33 */     if (this.villagerObj.getRNG().nextInt(400) != 0)
/*     */     {
/*  35 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  39 */     List var1 = this.villagerObj.worldObj.getEntitiesWithinAABB(EntityVillager.class, this.villagerObj.getEntityBoundingBox().expand(6.0D, 3.0D, 6.0D));
/*  40 */     double var2 = Double.MAX_VALUE;
/*  41 */     Iterator<EntityVillager> var4 = var1.iterator();
/*     */     
/*  43 */     while (var4.hasNext()) {
/*     */       
/*  45 */       EntityVillager var5 = var4.next();
/*     */       
/*  47 */       if (var5 != this.villagerObj && !var5.isPlaying() && var5.getGrowingAge() < 0) {
/*     */         
/*  49 */         double var6 = var5.getDistanceSqToEntity((Entity)this.villagerObj);
/*     */         
/*  51 */         if (var6 <= var2) {
/*     */           
/*  53 */           var2 = var6;
/*  54 */           this.targetVillager = (EntityLivingBase)var5;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  59 */     if (this.targetVillager == null) {
/*     */       
/*  61 */       Vec3 var8 = RandomPositionGenerator.findRandomTarget((EntityCreature)this.villagerObj, 16, 3);
/*     */       
/*  63 */       if (var8 == null)
/*     */       {
/*  65 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  69 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  78 */     return (this.playTime > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  86 */     if (this.targetVillager != null)
/*     */     {
/*  88 */       this.villagerObj.setPlaying(true);
/*     */     }
/*     */     
/*  91 */     this.playTime = 1000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  99 */     this.villagerObj.setPlaying(false);
/* 100 */     this.targetVillager = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 108 */     this.playTime--;
/*     */     
/* 110 */     if (this.targetVillager != null) {
/*     */       
/* 112 */       if (this.villagerObj.getDistanceSqToEntity((Entity)this.targetVillager) > 4.0D)
/*     */       {
/* 114 */         this.villagerObj.getNavigator().tryMoveToEntityLiving((Entity)this.targetVillager, this.field_75261_c);
/*     */       }
/*     */     }
/* 117 */     else if (this.villagerObj.getNavigator().noPath()) {
/*     */       
/* 119 */       Vec3 var1 = RandomPositionGenerator.findRandomTarget((EntityCreature)this.villagerObj, 16, 3);
/*     */       
/* 121 */       if (var1 == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 126 */       this.villagerObj.getNavigator().tryMoveToXYZ(var1.xCoord, var1.yCoord, var1.zCoord, this.field_75261_c);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIPlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */