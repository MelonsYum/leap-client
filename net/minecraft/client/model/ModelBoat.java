/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ public class ModelBoat
/*    */   extends ModelBase {
/*  7 */   public ModelRenderer[] boatSides = new ModelRenderer[5];
/*    */   
/*    */   private static final String __OBFID = "CL_00000832";
/*    */   
/*    */   public ModelBoat() {
/* 12 */     this.boatSides[0] = new ModelRenderer(this, 0, 8);
/* 13 */     this.boatSides[1] = new ModelRenderer(this, 0, 0);
/* 14 */     this.boatSides[2] = new ModelRenderer(this, 0, 0);
/* 15 */     this.boatSides[3] = new ModelRenderer(this, 0, 0);
/* 16 */     this.boatSides[4] = new ModelRenderer(this, 0, 0);
/* 17 */     byte var1 = 24;
/* 18 */     byte var2 = 6;
/* 19 */     byte var3 = 20;
/* 20 */     byte var4 = 4;
/* 21 */     this.boatSides[0].addBox((-var1 / 2), (-var3 / 2 + 2), -3.0F, var1, var3 - 4, 4, 0.0F);
/* 22 */     this.boatSides[0].setRotationPoint(0.0F, var4, 0.0F);
/* 23 */     this.boatSides[1].addBox((-var1 / 2 + 2), (-var2 - 1), -1.0F, var1 - 4, var2, 2, 0.0F);
/* 24 */     this.boatSides[1].setRotationPoint((-var1 / 2 + 1), var4, 0.0F);
/* 25 */     this.boatSides[2].addBox((-var1 / 2 + 2), (-var2 - 1), -1.0F, var1 - 4, var2, 2, 0.0F);
/* 26 */     this.boatSides[2].setRotationPoint((var1 / 2 - 1), var4, 0.0F);
/* 27 */     this.boatSides[3].addBox((-var1 / 2 + 2), (-var2 - 1), -1.0F, var1 - 4, var2, 2, 0.0F);
/* 28 */     this.boatSides[3].setRotationPoint(0.0F, var4, (-var3 / 2 + 1));
/* 29 */     this.boatSides[4].addBox((-var1 / 2 + 2), (-var2 - 1), -1.0F, var1 - 4, var2, 2, 0.0F);
/* 30 */     this.boatSides[4].setRotationPoint(0.0F, var4, (var3 / 2 - 1));
/* 31 */     (this.boatSides[0]).rotateAngleX = 1.5707964F;
/* 32 */     (this.boatSides[1]).rotateAngleY = 4.712389F;
/* 33 */     (this.boatSides[2]).rotateAngleY = 1.5707964F;
/* 34 */     (this.boatSides[3]).rotateAngleY = 3.1415927F;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 42 */     for (int var8 = 0; var8 < 5; var8++)
/*    */     {
/* 44 */       this.boatSides[var8].render(p_78088_7_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */