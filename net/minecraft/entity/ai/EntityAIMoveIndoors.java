/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.village.Village;
/*     */ import net.minecraft.village.VillageDoorInfo;
/*     */ 
/*     */ public class EntityAIMoveIndoors extends EntityAIBase {
/*     */   private EntityCreature entityObj;
/*     */   private VillageDoorInfo doorInfo;
/*  13 */   private int insidePosX = -1;
/*  14 */   private int insidePosZ = -1;
/*     */   
/*     */   private static final String __OBFID = "CL_00001596";
/*     */   
/*     */   public EntityAIMoveIndoors(EntityCreature p_i1637_1_) {
/*  19 */     this.entityObj = p_i1637_1_;
/*  20 */     setMutexBits(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  28 */     BlockPos var1 = new BlockPos((Entity)this.entityObj);
/*     */     
/*  30 */     if ((!this.entityObj.worldObj.isDaytime() || (this.entityObj.worldObj.isRaining() && !this.entityObj.worldObj.getBiomeGenForCoords(var1).canSpawnLightningBolt())) && !this.entityObj.worldObj.provider.getHasNoSky()) {
/*     */       
/*  32 */       if (this.entityObj.getRNG().nextInt(50) != 0)
/*     */       {
/*  34 */         return false;
/*     */       }
/*  36 */       if (this.insidePosX != -1 && this.entityObj.getDistanceSq(this.insidePosX, this.entityObj.posY, this.insidePosZ) < 4.0D)
/*     */       {
/*  38 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  42 */       Village var2 = this.entityObj.worldObj.getVillageCollection().func_176056_a(var1, 14);
/*     */       
/*  44 */       if (var2 == null)
/*     */       {
/*  46 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  50 */       this.doorInfo = var2.func_179863_c(var1);
/*  51 */       return (this.doorInfo != null);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  66 */     return !this.entityObj.getNavigator().noPath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  74 */     this.insidePosX = -1;
/*  75 */     BlockPos var1 = this.doorInfo.func_179856_e();
/*  76 */     int var2 = var1.getX();
/*  77 */     int var3 = var1.getY();
/*  78 */     int var4 = var1.getZ();
/*     */     
/*  80 */     if (this.entityObj.getDistanceSq(var1) > 256.0D) {
/*     */       
/*  82 */       Vec3 var5 = RandomPositionGenerator.findRandomTargetBlockTowards(this.entityObj, 14, 3, new Vec3(var2 + 0.5D, var3, var4 + 0.5D));
/*     */       
/*  84 */       if (var5 != null)
/*     */       {
/*  86 */         this.entityObj.getNavigator().tryMoveToXYZ(var5.xCoord, var5.yCoord, var5.zCoord, 1.0D);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  91 */       this.entityObj.getNavigator().tryMoveToXYZ(var2 + 0.5D, var3, var4 + 0.5D, 1.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetTask() {
/* 100 */     this.insidePosX = this.doorInfo.func_179856_e().getX();
/* 101 */     this.insidePosZ = this.doorInfo.func_179856_e().getZ();
/* 102 */     this.doorInfo = null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIMoveIndoors.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */