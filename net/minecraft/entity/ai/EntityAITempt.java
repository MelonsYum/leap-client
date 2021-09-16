/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityAITempt
/*     */   extends EntityAIBase
/*     */ {
/*     */   private EntityCreature temptedEntity;
/*     */   private double field_75282_b;
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   private double field_75278_f;
/*     */   private double field_75279_g;
/*     */   private EntityPlayer temptingPlayer;
/*     */   private int delayTemptCounter;
/*     */   private boolean isRunning;
/*     */   private Item field_151484_k;
/*     */   private boolean scaredByPlayerMovement;
/*     */   private boolean field_75286_m;
/*     */   private static final String __OBFID = "CL_00001616";
/*     */   
/*     */   public EntityAITempt(EntityCreature p_i45316_1_, double p_i45316_2_, Item p_i45316_4_, boolean p_i45316_5_) {
/*  48 */     this.temptedEntity = p_i45316_1_;
/*  49 */     this.field_75282_b = p_i45316_2_;
/*  50 */     this.field_151484_k = p_i45316_4_;
/*  51 */     this.scaredByPlayerMovement = p_i45316_5_;
/*  52 */     setMutexBits(3);
/*     */     
/*  54 */     if (!(p_i45316_1_.getNavigator() instanceof PathNavigateGround))
/*     */     {
/*  56 */       throw new IllegalArgumentException("Unsupported mob type for TemptGoal");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  65 */     if (this.delayTemptCounter > 0) {
/*     */       
/*  67 */       this.delayTemptCounter--;
/*  68 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     this.temptingPlayer = this.temptedEntity.worldObj.getClosestPlayerToEntity((Entity)this.temptedEntity, 10.0D);
/*     */     
/*  74 */     if (this.temptingPlayer == null)
/*     */     {
/*  76 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  80 */     ItemStack var1 = this.temptingPlayer.getCurrentEquippedItem();
/*  81 */     return (var1 == null) ? false : ((var1.getItem() == this.field_151484_k));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  91 */     if (this.scaredByPlayerMovement) {
/*     */       
/*  93 */       if (this.temptedEntity.getDistanceSqToEntity((Entity)this.temptingPlayer) < 36.0D) {
/*     */         
/*  95 */         if (this.temptingPlayer.getDistanceSq(this.targetX, this.targetY, this.targetZ) > 0.010000000000000002D)
/*     */         {
/*  97 */           return false;
/*     */         }
/*     */         
/* 100 */         if (Math.abs(this.temptingPlayer.rotationPitch - this.field_75278_f) > 5.0D || Math.abs(this.temptingPlayer.rotationYaw - this.field_75279_g) > 5.0D)
/*     */         {
/* 102 */           return false;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 107 */         this.targetX = this.temptingPlayer.posX;
/* 108 */         this.targetY = this.temptingPlayer.posY;
/* 109 */         this.targetZ = this.temptingPlayer.posZ;
/*     */       } 
/*     */       
/* 112 */       this.field_75278_f = this.temptingPlayer.rotationPitch;
/* 113 */       this.field_75279_g = this.temptingPlayer.rotationYaw;
/*     */     } 
/*     */     
/* 116 */     return shouldExecute();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/* 124 */     this.targetX = this.temptingPlayer.posX;
/* 125 */     this.targetY = this.temptingPlayer.posY;
/* 126 */     this.targetZ = this.temptingPlayer.posZ;
/* 127 */     this.isRunning = true;
/* 128 */     this.field_75286_m = ((PathNavigateGround)this.temptedEntity.getNavigator()).func_179689_e();
/* 129 */     ((PathNavigateGround)this.temptedEntity.getNavigator()).func_179690_a(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 137 */     this.temptingPlayer = null;
/* 138 */     this.temptedEntity.getNavigator().clearPathEntity();
/* 139 */     this.delayTemptCounter = 100;
/* 140 */     this.isRunning = false;
/* 141 */     ((PathNavigateGround)this.temptedEntity.getNavigator()).func_179690_a(this.field_75286_m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 149 */     this.temptedEntity.getLookHelper().setLookPositionWithEntity((Entity)this.temptingPlayer, 30.0F, this.temptedEntity.getVerticalFaceSpeed());
/*     */     
/* 151 */     if (this.temptedEntity.getDistanceSqToEntity((Entity)this.temptingPlayer) < 6.25D) {
/*     */       
/* 153 */       this.temptedEntity.getNavigator().clearPathEntity();
/*     */     }
/*     */     else {
/*     */       
/* 157 */       this.temptedEntity.getNavigator().tryMoveToEntityLiving((Entity)this.temptingPlayer, this.field_75282_b);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRunning() {
/* 166 */     return this.isRunning;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAITempt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */