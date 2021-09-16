/*     */ package optifine;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.block.model.BakedQuad;
/*     */ import net.minecraft.client.resources.model.IBakedModel;
/*     */ import net.minecraft.client.resources.model.ModelManager;
/*     */ import net.minecraft.client.resources.model.ModelResourceLocation;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class SmartLeaves
/*     */ {
/*  15 */   private static IBakedModel modelLeavesCullAcacia = null;
/*  16 */   private static IBakedModel modelLeavesCullBirch = null;
/*  17 */   private static IBakedModel modelLeavesCullDarkOak = null;
/*  18 */   private static IBakedModel modelLeavesCullJungle = null;
/*  19 */   private static IBakedModel modelLeavesCullOak = null;
/*  20 */   private static IBakedModel modelLeavesCullSpruce = null;
/*  21 */   private static List generalQuadsCullAcacia = null;
/*  22 */   private static List generalQuadsCullBirch = null;
/*  23 */   private static List generalQuadsCullDarkOak = null;
/*  24 */   private static List generalQuadsCullJungle = null;
/*  25 */   private static List generalQuadsCullOak = null;
/*  26 */   private static List generalQuadsCullSpruce = null;
/*  27 */   private static IBakedModel modelLeavesDoubleAcacia = null;
/*  28 */   private static IBakedModel modelLeavesDoubleBirch = null;
/*  29 */   private static IBakedModel modelLeavesDoubleDarkOak = null;
/*  30 */   private static IBakedModel modelLeavesDoubleJungle = null;
/*  31 */   private static IBakedModel modelLeavesDoubleOak = null;
/*  32 */   private static IBakedModel modelLeavesDoubleSpruce = null;
/*     */ 
/*     */   
/*     */   public static IBakedModel getLeavesModel(IBakedModel model) {
/*  36 */     if (!Config.isTreesSmart())
/*     */     {
/*  38 */       return model;
/*     */     }
/*     */ 
/*     */     
/*  42 */     List generalQuads = model.func_177550_a();
/*  43 */     return (generalQuads == generalQuadsCullAcacia) ? modelLeavesDoubleAcacia : ((generalQuads == generalQuadsCullBirch) ? modelLeavesDoubleBirch : ((generalQuads == generalQuadsCullDarkOak) ? modelLeavesDoubleDarkOak : ((generalQuads == generalQuadsCullJungle) ? modelLeavesDoubleJungle : ((generalQuads == generalQuadsCullOak) ? modelLeavesDoubleOak : ((generalQuads == generalQuadsCullSpruce) ? modelLeavesDoubleSpruce : model)))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateLeavesModels() {
/*  49 */     ArrayList updatedTypes = new ArrayList();
/*  50 */     modelLeavesCullAcacia = getModelCull("acacia", updatedTypes);
/*  51 */     modelLeavesCullBirch = getModelCull("birch", updatedTypes);
/*  52 */     modelLeavesCullDarkOak = getModelCull("dark_oak", updatedTypes);
/*  53 */     modelLeavesCullJungle = getModelCull("jungle", updatedTypes);
/*  54 */     modelLeavesCullOak = getModelCull("oak", updatedTypes);
/*  55 */     modelLeavesCullSpruce = getModelCull("spruce", updatedTypes);
/*  56 */     generalQuadsCullAcacia = getGeneralQuadsSafe(modelLeavesCullAcacia);
/*  57 */     generalQuadsCullBirch = getGeneralQuadsSafe(modelLeavesCullBirch);
/*  58 */     generalQuadsCullDarkOak = getGeneralQuadsSafe(modelLeavesCullDarkOak);
/*  59 */     generalQuadsCullJungle = getGeneralQuadsSafe(modelLeavesCullJungle);
/*  60 */     generalQuadsCullOak = getGeneralQuadsSafe(modelLeavesCullOak);
/*  61 */     generalQuadsCullSpruce = getGeneralQuadsSafe(modelLeavesCullSpruce);
/*  62 */     modelLeavesDoubleAcacia = getModelDoubleFace(modelLeavesCullAcacia);
/*  63 */     modelLeavesDoubleBirch = getModelDoubleFace(modelLeavesCullBirch);
/*  64 */     modelLeavesDoubleDarkOak = getModelDoubleFace(modelLeavesCullDarkOak);
/*  65 */     modelLeavesDoubleJungle = getModelDoubleFace(modelLeavesCullJungle);
/*  66 */     modelLeavesDoubleOak = getModelDoubleFace(modelLeavesCullOak);
/*  67 */     modelLeavesDoubleSpruce = getModelDoubleFace(modelLeavesCullSpruce);
/*     */     
/*  69 */     if (updatedTypes.size() > 0)
/*     */     {
/*  71 */       Config.dbg("Enable face culling: " + Config.arrayToString(updatedTypes.toArray()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static List getGeneralQuadsSafe(IBakedModel model) {
/*  77 */     return (model == null) ? null : model.func_177550_a();
/*     */   }
/*     */ 
/*     */   
/*     */   static IBakedModel getModelCull(String type, List<String> updatedTypes) {
/*  82 */     ModelManager modelManager = Config.getModelManager();
/*     */     
/*  84 */     if (modelManager == null)
/*     */     {
/*  86 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  90 */     ResourceLocation locState = new ResourceLocation("blockstates/" + type + "_leaves.json");
/*     */     
/*  92 */     if (Config.getDefiningResourcePack(locState) != Config.getDefaultResourcePack())
/*     */     {
/*  94 */       return null;
/*     */     }
/*     */ 
/*     */     
/*  98 */     ResourceLocation locModel = new ResourceLocation("models/block/" + type + "_leaves.json");
/*     */     
/* 100 */     if (Config.getDefiningResourcePack(locModel) != Config.getDefaultResourcePack())
/*     */     {
/* 102 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 106 */     ModelResourceLocation mrl = new ModelResourceLocation(String.valueOf(type) + "_leaves", "normal");
/* 107 */     IBakedModel model = modelManager.getModel(mrl);
/*     */     
/* 109 */     if (model != null && model != modelManager.getMissingModel()) {
/*     */       
/* 111 */       List listGeneral = model.func_177550_a();
/*     */       
/* 113 */       if (listGeneral.size() == 0)
/*     */       {
/* 115 */         return model;
/*     */       }
/* 117 */       if (listGeneral.size() != 6)
/*     */       {
/* 119 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 123 */       Iterator<BakedQuad> it = listGeneral.iterator();
/*     */       
/* 125 */       while (it.hasNext()) {
/*     */         
/* 127 */         BakedQuad quad = it.next();
/* 128 */         List<BakedQuad> listFace = model.func_177551_a(quad.getFace());
/*     */         
/* 130 */         if (listFace.size() > 0)
/*     */         {
/* 132 */           return null;
/*     */         }
/*     */         
/* 135 */         listFace.add(quad);
/*     */       } 
/*     */       
/* 138 */       listGeneral.clear();
/* 139 */       updatedTypes.add(String.valueOf(type) + "_leaves");
/* 140 */       return model;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 145 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static IBakedModel getModelDoubleFace(IBakedModel model) {
/* 154 */     if (model == null)
/*     */     {
/* 156 */       return null;
/*     */     }
/* 158 */     if (model.func_177550_a().size() > 0) {
/*     */       
/* 160 */       Config.warn("SmartLeaves: Model is not cube, general quads: " + model.func_177550_a().size() + ", model: " + model);
/* 161 */       return model;
/*     */     } 
/*     */ 
/*     */     
/* 165 */     EnumFacing[] faces = EnumFacing.VALUES;
/*     */     
/* 167 */     for (int model2 = 0; model2 < faces.length; model2++) {
/*     */       
/* 169 */       EnumFacing faceQuads = faces[model2];
/* 170 */       List i = model.func_177551_a(faceQuads);
/*     */       
/* 172 */       if (i.size() != 1) {
/*     */         
/* 174 */         Config.warn("SmartLeaves: Model is not cube, side: " + faceQuads + ", quads: " + i.size() + ", model: " + model);
/* 175 */         return model;
/*     */       } 
/*     */     } 
/*     */     
/* 179 */     IBakedModel var12 = ModelUtils.duplicateModel(model);
/* 180 */     List[] var13 = new List[faces.length];
/*     */     
/* 182 */     for (int var14 = 0; var14 < faces.length; var14++) {
/*     */       
/* 184 */       EnumFacing face = faces[var14];
/* 185 */       List<BakedQuad> quads = var12.func_177551_a(face);
/* 186 */       BakedQuad quad = quads.get(0);
/* 187 */       BakedQuad quad2 = new BakedQuad((int[])quad.func_178209_a().clone(), quad.func_178211_c(), quad.getFace(), quad.getSprite());
/* 188 */       int[] vd = quad2.func_178209_a();
/* 189 */       int[] vd2 = (int[])vd.clone();
/* 190 */       int step = vd.length / 4;
/* 191 */       System.arraycopy(vd, 0 * step, vd2, 3 * step, step);
/* 192 */       System.arraycopy(vd, 1 * step, vd2, 2 * step, step);
/* 193 */       System.arraycopy(vd, 2 * step, vd2, 1 * step, step);
/* 194 */       System.arraycopy(vd, 3 * step, vd2, 0 * step, step);
/* 195 */       System.arraycopy(vd2, 0, vd, 0, vd2.length);
/* 196 */       quads.add(quad2);
/*     */     } 
/*     */     
/* 199 */     return var12;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\SmartLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */