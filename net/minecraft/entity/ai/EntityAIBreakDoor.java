/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ public class EntityAIBreakDoor extends EntityAIDoorInteract {
/*     */   private int breakingTime;
/*  11 */   private int field_75358_j = -1;
/*     */   
/*     */   private static final String __OBFID = "CL_00001577";
/*     */   
/*     */   public EntityAIBreakDoor(EntityLiving p_i1618_1_) {
/*  16 */     super(p_i1618_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  24 */     if (!super.shouldExecute())
/*     */     {
/*  26 */       return false;
/*     */     }
/*  28 */     if (!this.theEntity.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
/*     */     {
/*  30 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  34 */     BlockDoor var10000 = this.doorBlock;
/*  35 */     return !BlockDoor.func_176514_f((IBlockAccess)this.theEntity.worldObj, this.field_179507_b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  44 */     super.startExecuting();
/*  45 */     this.breakingTime = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  53 */     double var1 = this.theEntity.getDistanceSq(this.field_179507_b);
/*     */ 
/*     */     
/*  56 */     if (this.breakingTime <= 240) {
/*     */       
/*  58 */       BlockDoor var10000 = this.doorBlock;
/*     */       
/*  60 */       if (!BlockDoor.func_176514_f((IBlockAccess)this.theEntity.worldObj, this.field_179507_b) && var1 < 4.0D) {
/*     */         
/*  62 */         boolean bool = true;
/*  63 */         return bool;
/*     */       } 
/*     */     } 
/*     */     
/*  67 */     boolean var3 = false;
/*  68 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/*  76 */     super.resetTask();
/*  77 */     this.theEntity.worldObj.sendBlockBreakProgress(this.theEntity.getEntityId(), this.field_179507_b, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/*  85 */     super.updateTask();
/*     */     
/*  87 */     if (this.theEntity.getRNG().nextInt(20) == 0)
/*     */     {
/*  89 */       this.theEntity.worldObj.playAuxSFX(1010, this.field_179507_b, 0);
/*     */     }
/*     */     
/*  92 */     this.breakingTime++;
/*  93 */     int var1 = (int)(this.breakingTime / 240.0F * 10.0F);
/*     */     
/*  95 */     if (var1 != this.field_75358_j) {
/*     */       
/*  97 */       this.theEntity.worldObj.sendBlockBreakProgress(this.theEntity.getEntityId(), this.field_179507_b, var1);
/*  98 */       this.field_75358_j = var1;
/*     */     } 
/*     */     
/* 101 */     if (this.breakingTime == 240 && this.theEntity.worldObj.getDifficulty() == EnumDifficulty.HARD) {
/*     */       
/* 103 */       this.theEntity.worldObj.setBlockToAir(this.field_179507_b);
/* 104 */       this.theEntity.worldObj.playAuxSFX(1012, this.field_179507_b, 0);
/* 105 */       this.theEntity.worldObj.playAuxSFX(2001, this.field_179507_b, Block.getIdFromBlock((Block)this.doorBlock));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIBreakDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */