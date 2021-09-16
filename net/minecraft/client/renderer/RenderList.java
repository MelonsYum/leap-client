/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.client.renderer.chunk.ListedRenderChunk;
/*    */ import net.minecraft.client.renderer.chunk.RenderChunk;
/*    */ import net.minecraft.util.EnumWorldBlockLayer;
/*    */ import optifine.Config;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderList
/*    */   extends ChunkRenderContainer
/*    */ {
/*    */   public void func_178001_a(EnumWorldBlockLayer p_178001_1_) {
/* 16 */     if (this.field_178007_b) {
/*    */       
/* 18 */       if (this.field_178009_a.size() == 0) {
/*    */         return;
/*    */       }
/*    */ 
/*    */       
/* 23 */       Iterator<RenderChunk> var2 = this.field_178009_a.iterator();
/*    */       
/* 25 */       while (var2.hasNext()) {
/*    */         
/* 27 */         RenderChunk var3 = var2.next();
/* 28 */         ListedRenderChunk var4 = (ListedRenderChunk)var3;
/* 29 */         GlStateManager.pushMatrix();
/* 30 */         func_178003_a(var3);
/* 31 */         GL11.glCallList(var4.func_178600_a(p_178001_1_, var4.func_178571_g()));
/* 32 */         GlStateManager.popMatrix();
/*    */       } 
/*    */       
/* 35 */       if (Config.isMultiTexture())
/*    */       {
/* 37 */         GlStateManager.bindCurrentTexture();
/*    */       }
/*    */       
/* 40 */       GlStateManager.func_179117_G();
/* 41 */       this.field_178009_a.clear();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\RenderList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */