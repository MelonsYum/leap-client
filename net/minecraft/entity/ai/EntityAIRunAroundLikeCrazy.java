/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.passive.EntityHorse;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class EntityAIRunAroundLikeCrazy
/*    */   extends EntityAIBase {
/*    */   private EntityHorse horseHost;
/*    */   private double field_111178_b;
/*    */   private double field_111179_c;
/*    */   private double field_111176_d;
/*    */   private double field_111177_e;
/*    */   private static final String __OBFID = "CL_00001612";
/*    */   
/*    */   public EntityAIRunAroundLikeCrazy(EntityHorse p_i1653_1_, double p_i1653_2_) {
/* 19 */     this.horseHost = p_i1653_1_;
/* 20 */     this.field_111178_b = p_i1653_2_;
/* 21 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 29 */     if (!this.horseHost.isTame() && this.horseHost.riddenByEntity != null) {
/*    */       
/* 31 */       Vec3 var1 = RandomPositionGenerator.findRandomTarget((EntityCreature)this.horseHost, 5, 4);
/*    */       
/* 33 */       if (var1 == null)
/*    */       {
/* 35 */         return false;
/*    */       }
/*    */ 
/*    */       
/* 39 */       this.field_111179_c = var1.xCoord;
/* 40 */       this.field_111176_d = var1.yCoord;
/* 41 */       this.field_111177_e = var1.zCoord;
/* 42 */       return true;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 47 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 56 */     this.horseHost.getNavigator().tryMoveToXYZ(this.field_111179_c, this.field_111176_d, this.field_111177_e, this.field_111178_b);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 64 */     return (!this.horseHost.getNavigator().noPath() && this.horseHost.riddenByEntity != null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {
/* 72 */     if (this.horseHost.getRNG().nextInt(50) == 0) {
/*    */       
/* 74 */       if (this.horseHost.riddenByEntity instanceof EntityPlayer) {
/*    */         
/* 76 */         int var1 = this.horseHost.getTemper();
/* 77 */         int var2 = this.horseHost.getMaxTemper();
/*    */         
/* 79 */         if (var2 > 0 && this.horseHost.getRNG().nextInt(var2) < var1) {
/*    */           
/* 81 */           this.horseHost.setTamedBy((EntityPlayer)this.horseHost.riddenByEntity);
/* 82 */           this.horseHost.worldObj.setEntityState((Entity)this.horseHost, (byte)7);
/*    */           
/*    */           return;
/*    */         } 
/* 86 */         this.horseHost.increaseTemper(5);
/*    */       } 
/*    */       
/* 89 */       this.horseHost.riddenByEntity.mountEntity(null);
/* 90 */       this.horseHost.riddenByEntity = null;
/* 91 */       this.horseHost.makeHorseRearWithSound();
/* 92 */       this.horseHost.worldObj.setEntityState((Entity)this.horseHost, (byte)6);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIRunAroundLikeCrazy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */