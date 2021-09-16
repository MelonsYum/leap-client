/*    */ package net.minecraft.client.model;
/*    */ 
/*    */ public class ModelBanner
/*    */   extends ModelBase
/*    */ {
/*    */   public ModelRenderer bannerSlate;
/*    */   public ModelRenderer bannerStand;
/*    */   public ModelRenderer bannerTop;
/*    */   private static final String __OBFID = "CL_00002630";
/*    */   
/*    */   public ModelBanner() {
/* 12 */     this.textureWidth = 64;
/* 13 */     this.textureHeight = 64;
/* 14 */     this.bannerSlate = new ModelRenderer(this, 0, 0);
/* 15 */     this.bannerSlate.addBox(-10.0F, 0.0F, -2.0F, 20, 40, 1, 0.0F);
/* 16 */     this.bannerStand = new ModelRenderer(this, 44, 0);
/* 17 */     this.bannerStand.addBox(-1.0F, -30.0F, -1.0F, 2, 42, 2, 0.0F);
/* 18 */     this.bannerTop = new ModelRenderer(this, 0, 42);
/* 19 */     this.bannerTop.addBox(-10.0F, -32.0F, -1.0F, 20, 2, 2, 0.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178687_a() {
/* 24 */     this.bannerSlate.rotationPointY = -32.0F;
/* 25 */     this.bannerSlate.render(0.0625F);
/* 26 */     this.bannerStand.render(0.0625F);
/* 27 */     this.bannerTop.render(0.0625F);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\model\ModelBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */