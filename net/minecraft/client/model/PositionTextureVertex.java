/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ public class PositionTextureVertex
/*    */ {
/*    */   public Vec3 vector3D;
/*    */   public float texturePositionX;
/*    */   public float texturePositionY;
/*    */   private static final String __OBFID = "CL_00000862";
/*    */   
/*    */   public PositionTextureVertex(float p_i1158_1_, float p_i1158_2_, float p_i1158_3_, float p_i1158_4_, float p_i1158_5_) {
/* 14 */     this(new Vec3(p_i1158_1_, p_i1158_2_, p_i1158_3_), p_i1158_4_, p_i1158_5_);
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionTextureVertex setTexturePosition(float p_78240_1_, float p_78240_2_) {
/* 19 */     return new PositionTextureVertex(this, p_78240_1_, p_78240_2_);
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionTextureVertex(PositionTextureVertex p_i46363_1_, float p_i46363_2_, float p_i46363_3_) {
/* 24 */     this.vector3D = p_i46363_1_.vector3D;
/* 25 */     this.texturePositionX = p_i46363_2_;
/* 26 */     this.texturePositionY = p_i46363_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public PositionTextureVertex(Vec3 p_i1160_1_, float p_i1160_2_, float p_i1160_3_) {
/* 31 */     this.vector3D = p_i1160_1_;
/* 32 */     this.texturePositionX = p_i1160_2_;
/* 33 */     this.texturePositionY = p_i1160_3_;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\PositionTextureVertex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */