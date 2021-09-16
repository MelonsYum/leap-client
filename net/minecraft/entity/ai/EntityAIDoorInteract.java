/*     */ package net.minecraft.entity.ai;
/*     */ 
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDoor;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.pathfinding.PathEntity;
/*     */ import net.minecraft.pathfinding.PathNavigateGround;
/*     */ import net.minecraft.pathfinding.PathPoint;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityAIDoorInteract
/*     */   extends EntityAIBase
/*     */ {
/*     */   protected EntityLiving theEntity;
/*     */   protected BlockPos field_179507_b;
/*     */   protected BlockDoor doorBlock;
/*     */   boolean hasStoppedDoorInteraction;
/*     */   float entityPositionX;
/*     */   float entityPositionZ;
/*     */   private static final String __OBFID = "CL_00001581";
/*     */   
/*     */   public EntityAIDoorInteract(EntityLiving p_i1621_1_) {
/*  30 */     this.field_179507_b = BlockPos.ORIGIN;
/*  31 */     this.theEntity = p_i1621_1_;
/*     */     
/*  33 */     if (!(p_i1621_1_.getNavigator() instanceof PathNavigateGround))
/*     */     {
/*  35 */       throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldExecute() {
/*  44 */     if (!this.theEntity.isCollidedHorizontally)
/*     */     {
/*  46 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  50 */     PathNavigateGround var1 = (PathNavigateGround)this.theEntity.getNavigator();
/*  51 */     PathEntity var2 = var1.getPath();
/*     */     
/*  53 */     if (var2 != null && !var2.isFinished() && var1.func_179686_g()) {
/*     */       
/*  55 */       for (int var3 = 0; var3 < Math.min(var2.getCurrentPathIndex() + 2, var2.getCurrentPathLength()); var3++) {
/*     */         
/*  57 */         PathPoint var4 = var2.getPathPointFromIndex(var3);
/*  58 */         this.field_179507_b = new BlockPos(var4.xCoord, var4.yCoord + 1, var4.zCoord);
/*     */         
/*  60 */         if (this.theEntity.getDistanceSq(this.field_179507_b.getX(), this.theEntity.posY, this.field_179507_b.getZ()) <= 2.25D) {
/*     */           
/*  62 */           this.doorBlock = func_179506_a(this.field_179507_b);
/*     */           
/*  64 */           if (this.doorBlock != null)
/*     */           {
/*  66 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*  71 */       this.field_179507_b = (new BlockPos((Entity)this.theEntity)).offsetUp();
/*  72 */       this.doorBlock = func_179506_a(this.field_179507_b);
/*  73 */       return (this.doorBlock != null);
/*     */     } 
/*     */ 
/*     */     
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean continueExecuting() {
/*  87 */     return !this.hasStoppedDoorInteraction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startExecuting() {
/*  95 */     this.hasStoppedDoorInteraction = false;
/*  96 */     this.entityPositionX = (float)((this.field_179507_b.getX() + 0.5F) - this.theEntity.posX);
/*  97 */     this.entityPositionZ = (float)((this.field_179507_b.getZ() + 0.5F) - this.theEntity.posZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateTask() {
/* 105 */     float var1 = (float)((this.field_179507_b.getX() + 0.5F) - this.theEntity.posX);
/* 106 */     float var2 = (float)((this.field_179507_b.getZ() + 0.5F) - this.theEntity.posZ);
/* 107 */     float var3 = this.entityPositionX * var1 + this.entityPositionZ * var2;
/*     */     
/* 109 */     if (var3 < 0.0F)
/*     */     {
/* 111 */       this.hasStoppedDoorInteraction = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private BlockDoor func_179506_a(BlockPos p_179506_1_) {
/* 117 */     Block var2 = this.theEntity.worldObj.getBlockState(p_179506_1_).getBlock();
/* 118 */     return (var2 instanceof BlockDoor && var2.getMaterial() == Material.wood) ? (BlockDoor)var2 : null;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIDoorInteract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */