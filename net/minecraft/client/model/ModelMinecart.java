/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class ModelMinecart
/*    */   extends ModelBase {
/*  7 */   public ModelRenderer[] sideModels = new ModelRenderer[7];
/*    */   
/*    */   private static final String __OBFID = "CL_00000844";
/*    */   
/*    */   public ModelMinecart() {
/* 12 */     this.sideModels[0] = new ModelRenderer(this, 0, 10);
/* 13 */     this.sideModels[1] = new ModelRenderer(this, 0, 0);
/* 14 */     this.sideModels[2] = new ModelRenderer(this, 0, 0);
/* 15 */     this.sideModels[3] = new ModelRenderer(this, 0, 0);
/* 16 */     this.sideModels[4] = new ModelRenderer(this, 0, 0);
/* 17 */     this.sideModels[5] = new ModelRenderer(this, 44, 10);
/* 18 */     byte var1 = 20;
/* 19 */     byte var2 = 8;
/* 20 */     byte var3 = 16;
/* 21 */     byte var4 = 4;
/* 22 */     this.sideModels[0].addBox((-var1 / 2), (-var3 / 2), -1.0F, var1, var3, 2, 0.0F);
/* 23 */     this.sideModels[0].setRotationPoint(0.0F, var4, 0.0F);
/* 24 */     this.sideModels[5].addBox((-var1 / 2 + 1), (-var3 / 2 + 1), -1.0F, var1 - 2, var3 - 2, 1, 0.0F);
/* 25 */     this.sideModels[5].setRotationPoint(0.0F, var4, 0.0F);
/* 26 */     this.sideModels[1].addBox((-var1 / 2 + 2), (-var2 - 1), -1.0F, var1 - 4, var2, 2, 0.0F);
/* 27 */     this.sideModels[1].setRotationPoint((-var1 / 2 + 1), var4, 0.0F);
/* 28 */     this.sideModels[2].addBox((-var1 / 2 + 2), (-var2 - 1), -1.0F, var1 - 4, var2, 2, 0.0F);
/* 29 */     this.sideModels[2].setRotationPoint((var1 / 2 - 1), var4, 0.0F);
/* 30 */     this.sideModels[3].addBox((-var1 / 2 + 2), (-var2 - 1), -1.0F, var1 - 4, var2, 2, 0.0F);
/* 31 */     this.sideModels[3].setRotationPoint(0.0F, var4, (-var3 / 2 + 1));
/* 32 */     this.sideModels[4].addBox((-var1 / 2 + 2), (-var2 - 1), -1.0F, var1 - 4, var2, 2, 0.0F);
/* 33 */     this.sideModels[4].setRotationPoint(0.0F, var4, (var3 / 2 - 1));
/* 34 */     (this.sideModels[0]).rotateAngleX = 1.5707964F;
/* 35 */     (this.sideModels[1]).rotateAngleY = 4.712389F;
/* 36 */     (this.sideModels[2]).rotateAngleY = 1.5707964F;
/* 37 */     (this.sideModels[3]).rotateAngleY = 3.1415927F;
/* 38 */     (this.sideModels[5]).rotateAngleX = -1.5707964F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 46 */     (this.sideModels[5]).rotationPointY = 4.0F - p_78088_4_;
/*    */     
/* 48 */     for (int var8 = 0; var8 < 6; var8++)
/*    */     {
/* 50 */       this.sideModels[var8].render(p_78088_7_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */