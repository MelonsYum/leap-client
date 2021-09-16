/*     */ package optifine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.SimpleBakedModel;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ 
/*     */ public class ModelUtils
/*     */ {
/*     */   public static void dbgModel(IBakedModel model) {
/*  15 */     if (model != null) {
/*     */       
/*  17 */       Config.dbg("Model: " + model + ", ao: " + model.isGui3d() + ", gui3d: " + model.isAmbientOcclusionEnabled() + ", builtIn: " + model.isBuiltInRenderer() + ", particle: " + model.getTexture());
/*  18 */       EnumFacing[] faces = EnumFacing.VALUES;
/*     */       
/*  20 */       for (int generalQuads = 0; generalQuads < faces.length; generalQuads++) {
/*     */         
/*  22 */         EnumFacing face = faces[generalQuads];
/*  23 */         List faceQuads = model.func_177551_a(face);
/*  24 */         dbgQuads(face.getName(), faceQuads, "  ");
/*     */       } 
/*     */       
/*  27 */       List var5 = model.func_177550_a();
/*  28 */       dbgQuads("General", var5, "  ");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void dbgQuads(String name, List quads, String prefix) {
/*  34 */     Iterator<BakedQuad> it = quads.iterator();
/*     */     
/*  36 */     while (it.hasNext()) {
/*     */       
/*  38 */       BakedQuad quad = it.next();
/*  39 */       dbgQuad(name, quad, prefix);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void dbgQuad(String name, BakedQuad quad, String prefix) {
/*  45 */     Config.dbg(String.valueOf(prefix) + "Quad: " + quad.getClass().getName() + ", type: " + name + ", face: " + quad.getFace() + ", tint: " + quad.func_178211_c() + ", sprite: " + quad.getSprite());
/*  46 */     dbgVertexData(quad.func_178209_a(), "  " + prefix);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void dbgVertexData(int[] vd, String prefix) {
/*  51 */     int step = vd.length / 4;
/*  52 */     Config.dbg(String.valueOf(prefix) + "Length: " + vd.length + ", step: " + step);
/*     */     
/*  54 */     for (int i = 0; i < 4; i++) {
/*     */       
/*  56 */       int pos = i * step;
/*  57 */       float x = Float.intBitsToFloat(vd[pos + 0]);
/*  58 */       float y = Float.intBitsToFloat(vd[pos + 1]);
/*  59 */       float z = Float.intBitsToFloat(vd[pos + 2]);
/*  60 */       int col = vd[pos + 3];
/*  61 */       float u = Float.intBitsToFloat(vd[pos + 4]);
/*  62 */       float v = Float.intBitsToFloat(vd[pos + 5]);
/*  63 */       Config.dbg(String.valueOf(prefix) + i + " xyz: " + x + "," + y + "," + z + " col: " + col + " u,v: " + u + "," + v);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static IBakedModel duplicateModel(IBakedModel model) {
/*  69 */     List generalQuads2 = duplicateQuadList(model.func_177550_a());
/*  70 */     EnumFacing[] faces = EnumFacing.VALUES;
/*  71 */     ArrayList<List> faceQuads2 = new ArrayList();
/*     */     
/*  73 */     for (int model2 = 0; model2 < faces.length; model2++) {
/*     */       
/*  75 */       EnumFacing face = faces[model2];
/*  76 */       List quads = model.func_177551_a(face);
/*  77 */       List quads2 = duplicateQuadList(quads);
/*  78 */       faceQuads2.add(quads2);
/*     */     } 
/*     */     
/*  81 */     SimpleBakedModel var8 = new SimpleBakedModel(generalQuads2, faceQuads2, model.isGui3d(), model.isAmbientOcclusionEnabled(), model.getTexture(), model.getItemCameraTransforms());
/*  82 */     return (IBakedModel)var8;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List duplicateQuadList(List list) {
/*  87 */     ArrayList<BakedQuad> list2 = new ArrayList();
/*  88 */     Iterator<BakedQuad> it = list.iterator();
/*     */     
/*  90 */     while (it.hasNext()) {
/*     */       
/*  92 */       BakedQuad quad = it.next();
/*  93 */       BakedQuad quad2 = duplicateQuad(quad);
/*  94 */       list2.add(quad2);
/*     */     } 
/*     */     
/*  97 */     return list2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BakedQuad duplicateQuad(BakedQuad quad) {
/* 102 */     BakedQuad quad2 = new BakedQuad((int[])quad.func_178209_a().clone(), quad.func_178211_c(), quad.getFace(), quad.getSprite());
/* 103 */     return quad2;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\ModelUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */