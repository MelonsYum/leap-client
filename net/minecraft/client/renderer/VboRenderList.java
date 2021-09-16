/*    */ package net.minecraft.client.renderer;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import net.minecraft.client.renderer.chunk.RenderChunk;
/*    */ import net.minecraft.client.renderer.vertex.VertexBuffer;
/*    */ import net.minecraft.util.EnumWorldBlockLayer;
/*    */ import optifine.Config;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import shadersmod.client.ShadersRender;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VboRenderList
/*    */   extends ChunkRenderContainer
/*    */ {
/*    */   public void func_178001_a(EnumWorldBlockLayer p_178001_1_) {
/* 17 */     if (this.field_178007_b) {
/*    */       
/* 19 */       Iterator<RenderChunk> var2 = this.field_178009_a.iterator();
/*    */       
/* 21 */       while (var2.hasNext()) {
/*    */         
/* 23 */         RenderChunk var3 = var2.next();
/* 24 */         VertexBuffer var4 = var3.func_178565_b(p_178001_1_.ordinal());
/* 25 */         GlStateManager.pushMatrix();
/* 26 */         func_178003_a(var3);
/* 27 */         var3.func_178572_f();
/* 28 */         var4.func_177359_a();
/* 29 */         func_178010_a();
/* 30 */         var4.func_177358_a(7);
/* 31 */         GlStateManager.popMatrix();
/*    */       } 
/*    */       
/* 34 */       OpenGlHelper.func_176072_g(OpenGlHelper.field_176089_P, 0);
/* 35 */       GlStateManager.func_179117_G();
/* 36 */       this.field_178009_a.clear();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void func_178010_a() {
/* 42 */     if (Config.isShaders()) {
/*    */       
/* 44 */       ShadersRender.setupArrayPointersVbo();
/*    */     }
/*    */     else {
/*    */       
/* 48 */       GL11.glVertexPointer(3, 5126, 28, 0L);
/* 49 */       GL11.glColorPointer(4, 5121, 28, 12L);
/* 50 */       GL11.glTexCoordPointer(2, 5126, 28, 16L);
/* 51 */       OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 52 */       GL11.glTexCoordPointer(2, 5122, 28, 24L);
/* 53 */       OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\VboRenderList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */