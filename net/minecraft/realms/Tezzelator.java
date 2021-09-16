/*     */ package net.minecraft.realms;
/*     */ 
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ 
/*     */ public class Tezzelator
/*     */ {
/*   8 */   public static Tessellator t = Tessellator.getInstance();
/*   9 */   public static final Tezzelator instance = new Tezzelator();
/*     */   
/*     */   private static final String __OBFID = "CL_00001855";
/*     */   
/*     */   public int end() {
/*  14 */     return t.draw();
/*     */   }
/*     */ 
/*     */   
/*     */   public void vertex(double p_vertex_1_, double p_vertex_3_, double p_vertex_5_) {
/*  19 */     t.getWorldRenderer().addVertex(p_vertex_1_, p_vertex_3_, p_vertex_5_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void color(float p_color_1_, float p_color_2_, float p_color_3_, float p_color_4_) {
/*  24 */     t.getWorldRenderer().func_178960_a(p_color_1_, p_color_2_, p_color_3_, p_color_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void color(int p_color_1_, int p_color_2_, int p_color_3_) {
/*  29 */     t.getWorldRenderer().setPosition(p_color_1_, p_color_2_, p_color_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tex2(int p_tex2_1_) {
/*  34 */     t.getWorldRenderer().func_178963_b(p_tex2_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void normal(float p_normal_1_, float p_normal_2_, float p_normal_3_) {
/*  39 */     t.getWorldRenderer().func_178980_d(p_normal_1_, p_normal_2_, p_normal_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void noColor() {
/*  44 */     t.getWorldRenderer().markDirty();
/*     */   }
/*     */ 
/*     */   
/*     */   public void color(int p_color_1_) {
/*  49 */     t.getWorldRenderer().func_178991_c(p_color_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void color(float p_color_1_, float p_color_2_, float p_color_3_) {
/*  54 */     t.getWorldRenderer().func_178986_b(p_color_1_, p_color_2_, p_color_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldRenderer.State sortQuads(float p_sortQuads_1_, float p_sortQuads_2_, float p_sortQuads_3_) {
/*  59 */     return t.getWorldRenderer().getVertexState(p_sortQuads_1_, p_sortQuads_2_, p_sortQuads_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void restoreState(WorldRenderer.State p_restoreState_1_) {
/*  64 */     t.getWorldRenderer().setVertexState(p_restoreState_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void begin(int p_begin_1_) {
/*  69 */     t.getWorldRenderer().startDrawing(p_begin_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void begin() {
/*  74 */     t.getWorldRenderer().startDrawingQuads();
/*     */   }
/*     */ 
/*     */   
/*     */   public void vertexUV(double p_vertexUV_1_, double p_vertexUV_3_, double p_vertexUV_5_, double p_vertexUV_7_, double p_vertexUV_9_) {
/*  79 */     t.getWorldRenderer().addVertexWithUV(p_vertexUV_1_, p_vertexUV_3_, p_vertexUV_5_, p_vertexUV_7_, p_vertexUV_9_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void color(int p_color_1_, int p_color_2_) {
/*  84 */     t.getWorldRenderer().func_178974_a(p_color_1_, p_color_2_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void offset(double p_offset_1_, double p_offset_3_, double p_offset_5_) {
/*  89 */     t.getWorldRenderer().setTranslation(p_offset_1_, p_offset_3_, p_offset_5_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void color(int p_color_1_, int p_color_2_, int p_color_3_, int p_color_4_) {
/*  94 */     t.getWorldRenderer().func_178961_b(p_color_1_, p_color_2_, p_color_3_, p_color_4_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tex(double p_tex_1_, double p_tex_3_) {
/*  99 */     t.getWorldRenderer().setTextureUV(p_tex_1_, p_tex_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void color(byte p_color_1_, byte p_color_2_, byte p_color_3_) {
/* 104 */     t.getWorldRenderer().func_178982_a(p_color_1_, p_color_2_, p_color_3_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\Tezzelator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */