/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.passive.EntityVillager;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityAIVillagerMate
/*     */   extends EntityAIBase {
/*     */   private EntityVillager villagerObj;
/*     */   private EntityVillager mate;
/*     */   private World worldObj;
/*     */   private int matingTimeout;
/*     */   Village villageObj;
/*     */   private static final String __OBFID = "CL_00001594";
/*     */   
/*     */   public EntityAIVillagerMate(EntityVillager p_i1634_1_) {
/*  20 */     this.villagerObj = p_i1634_1_;
/*  21 */     this.worldObj = p_i1634_1_.worldObj;
/*  22 */     setMutexBits(3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  30 */     if (this.villagerObj.getGrowingAge() != 0)
/*     */     {
/*  32 */       return false;
/*     */     }
/*  34 */     if (this.villagerObj.getRNG().nextInt(500) != 0)
/*     */     {
/*  36 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  40 */     this.villageObj = this.worldObj.getVillageCollection().func_176056_a(new BlockPos((Entity)this.villagerObj), 0);
/*     */     
/*  42 */     if (this.villageObj == null)
/*     */     {
/*  44 */       return false;
/*     */     }
/*  46 */     if (checkSufficientDoorsPresentForNewVillager() && this.villagerObj.func_175550_n(true)) {
/*     */       
/*  48 */       Entity var1 = this.worldObj.findNearestEntityWithinAABB(EntityVillager.class, this.villagerObj.getEntityBoundingBox().expand(8.0D, 3.0D, 8.0D), (Entity)this.villagerObj);
/*     */       
/*  50 */       if (var1 == null)
/*     */       {
/*  52 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  56 */       this.mate = (EntityVillager)var1;
/*  57 */       return (this.mate.getGrowingAge() == 0 && this.mate.func_175550_n(true));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  62 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  72 */     this.matingTimeout = 300;
/*  73 */     this.villagerObj.setMating(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  81 */     this.villageObj = null;
/*  82 */     this.mate = null;
/*  83 */     this.villagerObj.setMating(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  91 */     return (this.matingTimeout >= 0 && checkSufficientDoorsPresentForNewVillager() && this.villagerObj.getGrowingAge() == 0 && this.villagerObj.func_175550_n(false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  99 */     this.matingTimeout--;
/* 100 */     this.villagerObj.getLookHelper().setLookPositionWithEntity((Entity)this.mate, 10.0F, 30.0F);
/*     */     
/* 102 */     if (this.villagerObj.getDistanceSqToEntity((Entity)this.mate) > 2.25D) {
/*     */       
/* 104 */       this.villagerObj.getNavigator().tryMoveToEntityLiving((Entity)this.mate, 0.25D);
/*     */     }
/* 106 */     else if (this.matingTimeout == 0 && this.mate.isMating()) {
/*     */       
/* 108 */       giveBirth();
/*     */     } 
/*     */     
/* 111 */     if (this.villagerObj.getRNG().nextInt(35) == 0)
/*     */     {
/* 113 */       this.worldObj.setEntityState((Entity)this.villagerObj, (byte)12);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkSufficientDoorsPresentForNewVillager() {
/* 119 */     if (!this.villageObj.isMatingSeason())
/*     */     {
/* 121 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 125 */     int var1 = (int)(this.villageObj.getNumVillageDoors() * 0.35D);
/* 126 */     return (this.villageObj.getNumVillagers() < var1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void giveBirth() {
/* 132 */     EntityVillager var1 = this.villagerObj.func_180488_b((EntityAgeable)this.mate);
/* 133 */     this.mate.setGrowingAge(6000);
/* 134 */     this.villagerObj.setGrowingAge(6000);
/* 135 */     this.mate.func_175549_o(false);
/* 136 */     this.villagerObj.func_175549_o(false);
/* 137 */     var1.setGrowingAge(-24000);
/* 138 */     var1.setLocationAndAngles(this.villagerObj.posX, this.villagerObj.posY, this.villagerObj.posZ, 0.0F, 0.0F);
/* 139 */     this.worldObj.spawnEntityInWorld((Entity)var1);
/* 140 */     this.worldObj.setEntityState((Entity)var1, (byte)12);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIVillagerMate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */