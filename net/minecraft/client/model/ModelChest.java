/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ public class ModelChest
/*    */   extends ModelBase
/*    */ {
/*  6 */   public ModelRenderer chestLid = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
/*    */ 
/*    */   
/*    */   public ModelRenderer chestBelow;
/*    */   
/*    */   public ModelRenderer chestKnob;
/*    */   
/*    */   private static final String __OBFID = "CL_00000834";
/*    */ 
/*    */   
/*    */   public ModelChest() {
/* 17 */     this.chestLid.addBox(0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F);
/* 18 */     this.chestLid.rotationPointX = 1.0F;
/* 19 */     this.chestLid.rotationPointY = 7.0F;
/* 20 */     this.chestLid.rotationPointZ = 15.0F;
/* 21 */     this.chestKnob = (new ModelRenderer(this, 0, 0)).setTextureSize(64, 64);
/* 22 */     this.chestKnob.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F);
/* 23 */     this.chestKnob.rotationPointX = 8.0F;
/* 24 */     this.chestKnob.rotationPointY = 7.0F;
/* 25 */     this.chestKnob.rotationPointZ = 15.0F;
/* 26 */     this.chestBelow = (new ModelRenderer(this, 0, 19)).setTextureSize(64, 64);
/* 27 */     this.chestBelow.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F);
/* 28 */     this.chestBelow.rotationPointX = 1.0F;
/* 29 */     this.chestBelow.rotationPointY = 6.0F;
/* 30 */     this.chestBelow.rotationPointZ = 1.0F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderAll() {
/* 38 */     this.chestKnob.rotateAngleX = this.chestLid.rotateAngleX;
/* 39 */     this.chestLid.render(0.0625F);
/* 40 */     this.chestKnob.render(0.0625F);
/* 41 */     this.chestBelow.render(0.0625F);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */