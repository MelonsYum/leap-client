/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ public class Tessellator
/*    */ {
/*    */   private WorldRenderer worldRenderer;
/*  6 */   private WorldVertexBufferUploader field_178182_b = new WorldVertexBufferUploader();
/*    */ 
/*    */   
/*  9 */   private static final Tessellator instance = new Tessellator(2097152);
/*    */   
/*    */   private static final String __OBFID = "CL_00000960";
/*    */   
/*    */   public static Tessellator getInstance() {
/* 14 */     return instance;
/*    */   }
/*    */ 
/*    */   
/*    */   public Tessellator(int p_i1250_1_) {
/* 19 */     this.worldRenderer = new WorldRenderer(p_i1250_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int draw() {
/* 27 */     return this.field_178182_b.draw(this.worldRenderer, this.worldRenderer.draw());
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldRenderer getWorldRenderer() {
/* 32 */     return this.worldRenderer;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\Tessellator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */