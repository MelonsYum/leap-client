/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class ModelSkeletonHead
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer skeletonHead;
/*    */   private static final String __OBFID = "CL_00000856";
/*    */   
/*    */   public ModelSkeletonHead() {
/* 12 */     this(0, 35, 64, 64);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelSkeletonHead(int p_i1155_1_, int p_i1155_2_, int p_i1155_3_, int p_i1155_4_) {
/* 17 */     this.textureWidth = p_i1155_3_;
/* 18 */     this.textureHeight = p_i1155_4_;
/* 19 */     this.skeletonHead = new ModelRenderer(this, p_i1155_1_, p_i1155_2_);
/* 20 */     this.skeletonHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
/* 21 */     this.skeletonHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 29 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 30 */     this.skeletonHead.render(p_78088_7_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 40 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 41 */     this.skeletonHead.rotateAngleY = p_78087_4_ / 57.295776F;
/* 42 */     this.skeletonHead.rotateAngleX = p_78087_5_ / 57.295776F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelSkeletonHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */