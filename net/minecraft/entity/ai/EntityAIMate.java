/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.item.EntityXPOrb;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAIMate
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityAnimal theAnimal;
/*     */   World theWorld;
/*     */   private EntityAnimal targetMate;
/*     */   int spawnBabyDelay;
/*     */   double moveSpeed;
/*     */   private static final String __OBFID = "CL_00001578";
/*     */   
/*     */   public EntityAIMate(EntityAnimal p_i1619_1_, double p_i1619_2_) {
/*  33 */     this.theAnimal = p_i1619_1_;
/*  34 */     this.theWorld = p_i1619_1_.worldObj;
/*  35 */     this.moveSpeed = p_i1619_2_;
/*  36 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  44 */     if (!this.theAnimal.isInLove())
/*     */     {
/*  46 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  50 */     this.targetMate = getNearbyMate();
/*  51 */     return (this.targetMate != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  60 */     return (this.targetMate.isEntityAlive() && this.targetMate.isInLove() && this.spawnBabyDelay < 60);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  68 */     this.targetMate = null;
/*  69 */     this.spawnBabyDelay = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  77 */     this.theAnimal.getLookHelper().setLookPositionWithEntity((Entity)this.targetMate, 10.0F, this.theAnimal.getVerticalFaceSpeed());
/*  78 */     this.theAnimal.getNavigator().tryMoveToEntityLiving((Entity)this.targetMate, this.moveSpeed);
/*  79 */     this.spawnBabyDelay++;
/*     */     
/*  81 */     if (this.spawnBabyDelay >= 60 && this.theAnimal.getDistanceSqToEntity((Entity)this.targetMate) < 9.0D)
/*     */     {
/*  83 */       spawnBaby();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private EntityAnimal getNearbyMate() {
/*  93 */     float var1 = 8.0F;
/*  94 */     List var2 = this.theWorld.getEntitiesWithinAABB(this.theAnimal.getClass(), this.theAnimal.getEntityBoundingBox().expand(var1, var1, var1));
/*  95 */     double var3 = Double.MAX_VALUE;
/*  96 */     EntityAnimal var5 = null;
/*  97 */     Iterator<EntityAnimal> var6 = var2.iterator();
/*     */     
/*  99 */     while (var6.hasNext()) {
/*     */       
/* 101 */       EntityAnimal var7 = var6.next();
/*     */       
/* 103 */       if (this.theAnimal.canMateWith(var7) && this.theAnimal.getDistanceSqToEntity((Entity)var7) < var3) {
/*     */         
/* 105 */         var5 = var7;
/* 106 */         var3 = this.theAnimal.getDistanceSqToEntity((Entity)var7);
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void spawnBaby() {
/* 118 */     EntityAgeable var1 = this.theAnimal.createChild((EntityAgeable)this.targetMate);
/*     */     
/* 120 */     if (var1 != null) {
/*     */       
/* 122 */       EntityPlayer var2 = this.theAnimal.func_146083_cb();
/*     */       
/* 124 */       if (var2 == null && this.targetMate.func_146083_cb() != null)
/*     */       {
/* 126 */         var2 = this.targetMate.func_146083_cb();
/*     */       }
/*     */       
/* 129 */       if (var2 != null) {
/*     */         
/* 131 */         var2.triggerAchievement(StatList.animalsBredStat);
/*     */         
/* 133 */         if (this.theAnimal instanceof net.minecraft.entity.passive.EntityCow)
/*     */         {
/* 135 */           var2.triggerAchievement((StatBase)AchievementList.breedCow);
/*     */         }
/*     */       } 
/*     */       
/* 139 */       this.theAnimal.setGrowingAge(6000);
/* 140 */       this.targetMate.setGrowingAge(6000);
/* 141 */       this.theAnimal.resetInLove();
/* 142 */       this.targetMate.resetInLove();
/* 143 */       var1.setGrowingAge(-24000);
/* 144 */       var1.setLocationAndAngles(this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, 0.0F, 0.0F);
/* 145 */       this.theWorld.spawnEntityInWorld((Entity)var1);
/* 146 */       Random var3 = this.theAnimal.getRNG();
/*     */       
/* 148 */       for (int var4 = 0; var4 < 7; var4++) {
/*     */         
/* 150 */         double var5 = var3.nextGaussian() * 0.02D;
/* 151 */         double var7 = var3.nextGaussian() * 0.02D;
/* 152 */         double var9 = var3.nextGaussian() * 0.02D;
/* 153 */         this.theWorld.spawnParticle(EnumParticleTypes.HEART, this.theAnimal.posX + (var3.nextFloat() * this.theAnimal.width * 2.0F) - this.theAnimal.width, this.theAnimal.posY + 0.5D + (var3.nextFloat() * this.theAnimal.height), this.theAnimal.posZ + (var3.nextFloat() * this.theAnimal.width * 2.0F) - this.theAnimal.width, var5, var7, var9, new int[0]);
/*     */       } 
/*     */       
/* 156 */       if (this.theWorld.getGameRules().getGameRuleBooleanValue("doMobLoot"))
/*     */       {
/* 158 */         this.theWorld.spawnEntityInWorld((Entity)new EntityXPOrb(this.theWorld, this.theAnimal.posX, this.theAnimal.posY, this.theAnimal.posZ, var3.nextInt(7) + 1));
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIMate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */