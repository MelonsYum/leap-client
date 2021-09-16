/*    */ package net.minecraft.entity.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ public class EntityAIWander
/*    */   extends EntityAIBase
/*    */ {
/*    */   private EntityCreature entity;
/*    */   private double xPosition;
/*    */   private double yPosition;
/*    */   private double zPosition;
/*    */   private double speed;
/*    */   private int field_179481_f;
/*    */   private boolean field_179482_g;
/*    */   private static final String __OBFID = "CL_00001608";
/*    */   
/*    */   public EntityAIWander(EntityCreature p_i1648_1_, double p_i1648_2_) {
/* 19 */     this(p_i1648_1_, p_i1648_2_, 120);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAIWander(EntityCreature p_i45887_1_, double p_i45887_2_, int p_i45887_4_) {
/* 24 */     this.entity = p_i45887_1_;
/* 25 */     this.speed = p_i45887_2_;
/* 26 */     this.field_179481_f = p_i45887_4_;
/* 27 */     setMutexBits(1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldExecute() {
/* 35 */     if (!this.field_179482_g) {
/*    */       
/* 37 */       if (this.entity.getAge() >= 100)
/*    */       {
/* 39 */         return false;
/*    */       }
/*    */       
/* 42 */       if (this.entity.getRNG().nextInt(this.field_179481_f) != 0)
/*    */       {
/* 44 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 48 */     Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
/*    */     
/* 50 */     if (var1 == null)
/*    */     {
/* 52 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 56 */     this.xPosition = var1.xCoord;
/* 57 */     this.yPosition = var1.yCoord;
/* 58 */     this.zPosition = var1.zCoord;
/* 59 */     this.field_179482_g = false;
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean continueExecuting() {
/* 69 */     return !this.entity.getNavigator().noPath();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {
/* 77 */     this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_179480_f() {
/* 82 */     this.field_179482_g = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_179479_b(int p_179479_1_) {
/* 87 */     this.field_179481_f = p_179479_1_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\entity\ai\EntityAIWander.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */