/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.Entity;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelEnderCrystal
/*    */   extends ModelBase
/*    */ {
/*    */   private ModelRenderer cube;
/* 12 */   private ModelRenderer glass = new ModelRenderer(this, "glass");
/*    */   
/*    */   private ModelRenderer base;
/*    */   
/*    */   private static final String __OBFID = "CL_00000871";
/*    */ 
/*    */   
/*    */   public ModelEnderCrystal(float p_i1170_1_, boolean p_i1170_2_) {
/* 20 */     this.glass.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/* 21 */     this.cube = new ModelRenderer(this, "cube");
/* 22 */     this.cube.setTextureOffset(32, 0).addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8);
/*    */     
/* 24 */     if (p_i1170_2_) {
/*    */       
/* 26 */       this.base = new ModelRenderer(this, "base");
/* 27 */       this.base.setTextureOffset(0, 16).addBox(-6.0F, 0.0F, -6.0F, 12, 4, 12);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
/* 36 */     GlStateManager.pushMatrix();
/* 37 */     GlStateManager.scale(2.0F, 2.0F, 2.0F);
/* 38 */     GlStateManager.translate(0.0F, -0.5F, 0.0F);
/*    */     
/* 40 */     if (this.base != null)
/*    */     {
/* 42 */       this.base.render(p_78088_7_);
/*    */     }
/*    */     
/* 45 */     GlStateManager.rotate(p_78088_3_, 0.0F, 1.0F, 0.0F);
/* 46 */     GlStateManager.translate(0.0F, 0.8F + p_78088_4_, 0.0F);
/* 47 */     GlStateManager.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
/* 48 */     this.glass.render(p_78088_7_);
/* 49 */     float var8 = 0.875F;
/* 50 */     GlStateManager.scale(var8, var8, var8);
/* 51 */     GlStateManager.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
/* 52 */     GlStateManager.rotate(p_78088_3_, 0.0F, 1.0F, 0.0F);
/* 53 */     this.glass.render(p_78088_7_);
/* 54 */     GlStateManager.scale(var8, var8, var8);
/* 55 */     GlStateManager.rotate(60.0F, 0.7071F, 0.0F, 0.7071F);
/* 56 */     GlStateManager.rotate(p_78088_3_, 0.0F, 1.0F, 0.0F);
/* 57 */     this.cube.render(p_78088_7_);
/* 58 */     GlStateManager.popMatrix();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelEnderCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */