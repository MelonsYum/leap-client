/*    */ package net.minecraft.block.material;
/*    */ 
/*    */ public class MaterialTransparent
/*    */   extends Material
/*    */ {
/*    */   private static final String __OBFID = "CL_00000540";
/*    */   
/*    */   public MaterialTransparent(MapColor p_i2113_1_) {
/*  9 */     super(p_i2113_1_);
/* 10 */     setReplaceable();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSolid() {
/* 15 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean blocksLight() {
/* 23 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean blocksMovement() {
/* 31 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\block\material\MaterialTransparent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */