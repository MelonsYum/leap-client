/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ModelWitch
/*    */   extends ModelVillager {
/*    */   public boolean field_82900_g;
/*  9 */   private ModelRenderer field_82901_h = (new ModelRenderer(this)).setTextureSize(64, 128);
/*    */   
/*    */   private ModelRenderer witchHat;
/*    */   private static final String __OBFID = "CL_00000866";
/*    */   
/*    */   public ModelWitch(float p_i46361_1_) {
/* 15 */     super(p_i46361_1_, 0.0F, 64, 128);
/* 16 */     this.field_82901_h.setRotationPoint(0.0F, -2.0F, 0.0F);
/* 17 */     this.field_82901_h.setTextureOffset(0, 0).addBox(0.0F, 3.0F, -6.75F, 1, 1, 1, -0.25F);
/* 18 */     this.villagerNose.addChild(this.field_82901_h);
/* 19 */     this.witchHat = (new ModelRenderer(this)).setTextureSize(64, 128);
/* 20 */     this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
/* 21 */     this.witchHat.setTextureOffset(0, 64).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
/* 22 */     this.villagerHead.addChild(this.witchHat);
/* 23 */     ModelRenderer var2 = (new ModelRenderer(this)).setTextureSize(64, 128);
/* 24 */     var2.setRotationPoint(1.75F, -4.0F, 2.0F);
/* 25 */     var2.setTextureOffset(0, 76).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
/* 26 */     var2.rotateAngleX = -0.05235988F;
/* 27 */     var2.rotateAngleZ = 0.02617994F;
/* 28 */     this.witchHat.addChild(var2);
/* 29 */     ModelRenderer var3 = (new ModelRenderer(this)).setTextureSize(64, 128);
/* 30 */     var3.setRotationPoint(1.75F, -4.0F, 2.0F);
/* 31 */     var3.setTextureOffset(0, 87).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
/* 32 */     var3.rotateAngleX = -0.10471976F;
/* 33 */     var3.rotateAngleZ = 0.05235988F;
/* 34 */     var2.addChild(var3);
/* 35 */     ModelRenderer var4 = (new ModelRenderer(this)).setTextureSize(64, 128);
/* 36 */     var4.setRotationPoint(1.75F, -2.0F, 2.0F);
/* 37 */     var4.setTextureOffset(0, 95).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
/* 38 */     var4.rotateAngleX = -0.20943952F;
/* 39 */     var4.rotateAngleZ = 0.10471976F;
/* 40 */     var3.addChild(var4);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
/* 50 */     super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
/* 51 */     this.villagerNose.offsetX = this.villagerNose.offsetY = this.villagerNose.offsetZ = 0.0F;
/* 52 */     float var8 = 0.01F * (p_78087_7_.getEntityId() % 10);
/* 53 */     this.villagerNose.rotateAngleX = MathHelper.sin(p_78087_7_.ticksExisted * var8) * 4.5F * 3.1415927F / 180.0F;
/* 54 */     this.villagerNose.rotateAngleY = 0.0F;
/* 55 */     this.villagerNose.rotateAngleZ = MathHelper.cos(p_78087_7_.ticksExisted * var8) * 2.5F * 3.1415927F / 180.0F;
/*    */     
/* 57 */     if (this.field_82900_g) {
/*    */       
/* 59 */       this.villagerNose.rotateAngleX = -0.9F;
/* 60 */       this.villagerNose.offsetZ = -0.09375F;
/* 61 */       this.villagerNose.offsetY = 0.1875F;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelWitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */