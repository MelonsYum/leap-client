/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ 
/*     */ public class EntityAIFollowParent
/*     */   extends EntityAIBase
/*     */ {
/*     */   EntityAnimal childAnimal;
/*     */   EntityAnimal parentAnimal;
/*     */   double field_75347_c;
/*     */   private int field_75345_d;
/*     */   private static final String __OBFID = "CL_00001586";
/*     */   
/*     */   public EntityAIFollowParent(EntityAnimal p_i1626_1_, double p_i1626_2_) {
/*  18 */     this.childAnimal = p_i1626_1_;
/*  19 */     this.field_75347_c = p_i1626_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  27 */     if (this.childAnimal.getGrowingAge() >= 0)
/*     */     {
/*  29 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  33 */     List var1 = this.childAnimal.worldObj.getEntitiesWithinAABB(this.childAnimal.getClass(), this.childAnimal.getEntityBoundingBox().expand(8.0D, 4.0D, 8.0D));
/*  34 */     EntityAnimal var2 = null;
/*  35 */     double var3 = Double.MAX_VALUE;
/*  36 */     Iterator<EntityAnimal> var5 = var1.iterator();
/*     */     
/*  38 */     while (var5.hasNext()) {
/*     */       
/*  40 */       EntityAnimal var6 = var5.next();
/*     */       
/*  42 */       if (var6.getGrowingAge() >= 0) {
/*     */         
/*  44 */         double var7 = this.childAnimal.getDistanceSqToEntity((Entity)var6);
/*     */         
/*  46 */         if (var7 <= var3) {
/*     */           
/*  48 */           var3 = var7;
/*  49 */           var2 = var6;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  54 */     if (var2 == null)
/*     */     {
/*  56 */       return false;
/*     */     }
/*  58 */     if (var3 < 9.0D)
/*     */     {
/*  60 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  64 */     this.parentAnimal = var2;
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  75 */     if (this.childAnimal.getGrowingAge() >= 0)
/*     */     {
/*  77 */       return false;
/*     */     }
/*  79 */     if (!this.parentAnimal.isEntityAlive())
/*     */     {
/*  81 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  85 */     double var1 = this.childAnimal.getDistanceSqToEntity((Entity)this.parentAnimal);
/*  86 */     return (var1 >= 9.0D && var1 <= 256.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  95 */     this.field_75345_d = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 103 */     this.parentAnimal = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 111 */     if (--this.field_75345_d <= 0) {
/*     */       
/* 113 */       this.field_75345_d = 10;
/* 114 */       this.childAnimal.getNavigator().tryMoveToEntityLiving((Entity)this.parentAnimal, this.field_75347_c);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIFollowParent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */