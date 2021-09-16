/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelVillager
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer villagerHead;
/*    */   public ModelRenderer villagerBody;
/*    */   public ModelRenderer villagerArms;
/*    */   public ModelRenderer rightVillagerLeg;
/*    */   public ModelRenderer leftVillagerLeg;
/*    */   public ModelRenderer villagerNose;
/*    */   private static final String __OBFID = "CL_00000864";
/*    */   
/*    */   public ModelVillager(float p_i1163_1_) {
/* 27 */     this(p_i1163_1_, 0.0F, 64, 64);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelVillager(float p_i1164_1_, float p_i1164_2_, int p_i1164_3_, int p_i1164_4_) {
/* 32 */     this.villagerHead = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
/* 33 */     this.villagerHead.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
/* 34 */     this.villagerHead.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, p_i1164_1_);
/* 35 */     this.villagerNose = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
/* 36 */     this.villagerNose.setRotationPoint(0.0F, p_i1164_2_ - 2.0F, 0.0F);
/* 37 */     this.villagerNose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, p_i1164_1_);
/* 38 */     this.villagerHead.addChild(this.villagerNose);
/* 39 */     this.villagerBody = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
/* 40 */     this.villagerBody.setRotationPoint(0.0F, 0.0F + p_i1164_2_, 0.0F);
/* 41 */     this.villagerBody.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, p_i1164_1_);
/* 42 */     this.villagerBody.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, p_i1164_1_ + 0.5F);
/* 43 */     this.villagerArms = (new ModelRenderer(this)).setTextureSize(p_i1164_3_, p_i1164_4_);
/* 44 */     this.villagerArms.setRotationPoint(0.0F, 0.0F + p_i1164_2_ + 2.0F, 0.0F);
/* 45 */     this.villagerArms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, p_i1164_1_);
/* 46 */     this.villagerArms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, p_i1164_1_);
/* 47 */     this.villagerArms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, p_i1164_1_);
/* 48 */     this.rightVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i1164_3_, p_i1164_4_);
/* 49 */     this.rightVillagerLeg.setRotationPoint(-2.0F, 12.0F + p_i1164_2_, 0.0F);
/* 50 */     this.rightVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1164_1_);
/* 51 */     this.leftVillagerLeg = (new ModelRenderer(this, 0, 22)).setTextureSize(p_i1164_3_, p_i1164_4_);
/* 52 */     this.leftVillagerLeg.mirror = true;
/* 53 */     this.leftVillagerLeg.setRotationPoint(2.0F, 12.0F + p_i1164_2_, 0.0F);
/* 54 */     this.leftVillagerLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, p_i1164_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 62 */     setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
/* 63 */     this.villagerHead.render(p_78088_7_);
/* 64 */     this.villagerBody.render(p_78088_7_);
/* 65 */     this.rightVillagerLeg.render(p_78088_7_);
/* 66 */     this.leftVillagerLeg.render(p_78088_7_);
/* 67 */     this.villagerArms.render(p_78088_7_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 77 */     this.villagerHead.rotateAngleY = p_78087_4_ / 57.295776F;
/* 78 */     this.villagerHead.rotateAngleX = p_78087_5_ / 57.295776F;
/* 79 */     this.villagerArms.rotationPointY = 3.0F;
/* 80 */     this.villagerArms.rotationPointZ = -1.0F;
/* 81 */     this.villagerArms.rotateAngleX = -0.75F;
/* 82 */     this.rightVillagerLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_ * 0.5F;
/* 83 */     this.leftVillagerLeg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + 3.1415927F) * 1.4F * p_78087_2_ * 0.5F;
/* 84 */     this.rightVillagerLeg.rotateAngleY = 0.0F;
/* 85 */     this.leftVillagerLeg.rotateAngleY = 0.0F;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */