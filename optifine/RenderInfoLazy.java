/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.renderer.RenderGlobal;
/*    */ import net.minecraft.client.renderer.chunk.RenderChunk;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderInfoLazy
/*    */ {
/*    */   private RenderChunk renderChunk;
/*    */   private RenderGlobal.ContainerLocalRenderInformation renderInfo;
/*    */   
/*    */   public RenderChunk getRenderChunk() {
/* 14 */     return this.renderChunk;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRenderChunk(RenderChunk renderChunk) {
/* 19 */     this.renderChunk = renderChunk;
/* 20 */     this.renderInfo = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public RenderGlobal.ContainerLocalRenderInformation getRenderInfo() {
/* 25 */     if (this.renderInfo == null)
/*    */     {
/* 27 */       this.renderInfo = new RenderGlobal.ContainerLocalRenderInformation(this.renderChunk, null, 0);
/*    */     }
/*    */     
/* 30 */     return this.renderInfo;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\RenderInfoLazy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */