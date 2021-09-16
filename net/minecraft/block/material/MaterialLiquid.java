/*    */ package net.minecraft.block.material;
/*    */ 
/*    */ public class MaterialLiquid
/*    */   extends Material
/*    */ {
/*    */   private static final String __OBFID = "CL_00000541";
/*    */   
/*    */   public MaterialLiquid(MapColor p_i2114_1_) {
/*  9 */     super(p_i2114_1_);
/* 10 */     setReplaceable();
/* 11 */     setNoPushMobility();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isLiquid() {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean blocksMovement() {
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSolid() {
/* 32 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\material\MaterialLiquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */