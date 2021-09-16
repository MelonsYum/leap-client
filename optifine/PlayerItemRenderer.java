/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ public class PlayerItemRenderer
/*    */ {
/*  8 */   private int attachTo = 0;
/*  9 */   private float scaleFactor = 0.0F;
/* 10 */   private ModelRenderer modelRenderer = null;
/*    */ 
/*    */   
/*    */   public PlayerItemRenderer(int attachTo, float scaleFactor, ModelRenderer modelRenderer) {
/* 14 */     this.attachTo = attachTo;
/* 15 */     this.scaleFactor = scaleFactor;
/* 16 */     this.modelRenderer = modelRenderer;
/*    */   }
/*    */ 
/*    */   
/*    */   public ModelRenderer getModelRenderer() {
/* 21 */     return this.modelRenderer;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(ModelBiped modelBiped, float scale) {
/* 26 */     ModelRenderer attachModel = PlayerItemModel.getAttachModel(modelBiped, this.attachTo);
/*    */     
/* 28 */     if (attachModel != null)
/*    */     {
/* 30 */       attachModel.postRender(scale);
/*    */     }
/*    */     
/* 33 */     this.modelRenderer.render(scale * this.scaleFactor);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PlayerItemRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */