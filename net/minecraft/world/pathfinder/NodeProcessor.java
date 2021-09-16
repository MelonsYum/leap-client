/*    */ package net.minecraft.world.pathfinder;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.pathfinding.PathPoint;
/*    */ import net.minecraft.util.IntHashMap;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ public abstract class NodeProcessor
/*    */ {
/*    */   protected IBlockAccess field_176169_a;
/* 12 */   protected IntHashMap field_176167_b = new IntHashMap();
/*    */   
/*    */   protected int field_176168_c;
/*    */   protected int field_176165_d;
/*    */   protected int field_176166_e;
/*    */   private static final String __OBFID = "CL_00001967";
/*    */   
/*    */   public void func_176162_a(IBlockAccess p_176162_1_, Entity p_176162_2_) {
/* 20 */     this.field_176169_a = p_176162_1_;
/* 21 */     this.field_176167_b.clearMap();
/* 22 */     this.field_176168_c = MathHelper.floor_float(p_176162_2_.width + 1.0F);
/* 23 */     this.field_176165_d = MathHelper.floor_float(p_176162_2_.height + 1.0F);
/* 24 */     this.field_176166_e = MathHelper.floor_float(p_176162_2_.width + 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_176163_a() {}
/*    */   
/*    */   protected PathPoint func_176159_a(int p_176159_1_, int p_176159_2_, int p_176159_3_) {
/* 31 */     int var4 = PathPoint.makeHash(p_176159_1_, p_176159_2_, p_176159_3_);
/* 32 */     PathPoint var5 = (PathPoint)this.field_176167_b.lookup(var4);
/*    */     
/* 34 */     if (var5 == null) {
/*    */       
/* 36 */       var5 = new PathPoint(p_176159_1_, p_176159_2_, p_176159_3_);
/* 37 */       this.field_176167_b.addKey(var4, var5);
/*    */     } 
/*    */     
/* 40 */     return var5;
/*    */   }
/*    */   
/*    */   public abstract PathPoint func_176161_a(Entity paramEntity);
/*    */   
/*    */   public abstract PathPoint func_176160_a(Entity paramEntity, double paramDouble1, double paramDouble2, double paramDouble3);
/*    */   
/*    */   public abstract int func_176164_a(PathPoint[] paramArrayOfPathPoint, Entity paramEntity, PathPoint paramPathPoint1, PathPoint paramPathPoint2, float paramFloat);
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\world\pathfinder\NodeProcessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */