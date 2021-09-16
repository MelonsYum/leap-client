/*    */ package net.minecraft.pathfinding;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.util.BlockPos;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class PathNavigateClimber
/*    */   extends PathNavigateGround
/*    */ {
/*    */   private BlockPos field_179696_f;
/*    */   private static final String __OBFID = "CL_00002245";
/*    */   
/*    */   public PathNavigateClimber(EntityLiving p_i45874_1_, World worldIn) {
/* 16 */     super(p_i45874_1_, worldIn);
/*    */   }
/*    */ 
/*    */   
/*    */   public PathEntity func_179680_a(BlockPos p_179680_1_) {
/* 21 */     this.field_179696_f = p_179680_1_;
/* 22 */     return super.func_179680_a(p_179680_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PathEntity getPathToEntityLiving(Entity p_75494_1_) {
/* 30 */     this.field_179696_f = new BlockPos(p_75494_1_);
/* 31 */     return super.getPathToEntityLiving(p_75494_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean tryMoveToEntityLiving(Entity p_75497_1_, double p_75497_2_) {
/* 39 */     PathEntity var4 = getPathToEntityLiving(p_75497_1_);
/*    */     
/* 41 */     if (var4 != null)
/*    */     {
/* 43 */       return setPath(var4, p_75497_2_);
/*    */     }
/*    */ 
/*    */     
/* 47 */     this.field_179696_f = new BlockPos(p_75497_1_);
/* 48 */     this.speed = p_75497_2_;
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onUpdateNavigation() {
/* 55 */     if (!noPath()) {
/*    */       
/* 57 */       super.onUpdateNavigation();
/*    */ 
/*    */     
/*    */     }
/* 61 */     else if (this.field_179696_f != null) {
/*    */       
/* 63 */       double var1 = (this.theEntity.width * this.theEntity.width);
/*    */       
/* 65 */       if (this.theEntity.func_174831_c(this.field_179696_f) >= var1 && (this.theEntity.posY <= this.field_179696_f.getY() || this.theEntity.func_174831_c(new BlockPos(this.field_179696_f.getX(), MathHelper.floor_double(this.theEntity.posY), this.field_179696_f.getZ())) >= var1)) {
/*    */         
/* 67 */         this.theEntity.getMoveHelper().setMoveTo(this.field_179696_f.getX(), this.field_179696_f.getY(), this.field_179696_f.getZ(), this.speed);
/*    */       }
/*    */       else {
/*    */         
/* 71 */         this.field_179696_f = null;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\pathfinding\PathNavigateClimber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */