/*     */ package optifine;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.awt.Dimension;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ public class PlayerItemParser
/*     */ {
/*  20 */   private static JsonParser jsonParser = new JsonParser();
/*     */   
/*     */   public static final String ITEM_TYPE = "type";
/*     */   public static final String ITEM_TEXTURE_SIZE = "textureSize";
/*     */   public static final String ITEM_USE_PLAYER_TEXTURE = "usePlayerTexture";
/*     */   public static final String ITEM_MODELS = "models";
/*     */   public static final String MODEL_ID = "id";
/*     */   public static final String MODEL_BASE_ID = "baseId";
/*     */   public static final String MODEL_TYPE = "type";
/*     */   public static final String MODEL_ATTACH_TO = "attachTo";
/*     */   public static final String MODEL_INVERT_AXIS = "invertAxis";
/*     */   public static final String MODEL_MIRROR_TEXTURE = "mirrorTexture";
/*     */   public static final String MODEL_TRANSLATE = "translate";
/*     */   public static final String MODEL_ROTATE = "rotate";
/*     */   public static final String MODEL_SCALE = "scale";
/*     */   public static final String MODEL_BOXES = "boxes";
/*     */   public static final String MODEL_SPRITES = "sprites";
/*     */   public static final String MODEL_SUBMODEL = "submodel";
/*     */   public static final String MODEL_SUBMODELS = "submodels";
/*     */   public static final String BOX_TEXTURE_OFFSET = "textureOffset";
/*     */   public static final String BOX_COORDINATES = "coordinates";
/*     */   public static final String BOX_SIZE_ADD = "sizeAdd";
/*     */   public static final String ITEM_TYPE_MODEL = "PlayerItem";
/*     */   public static final String MODEL_TYPE_BOX = "ModelBox";
/*     */   
/*     */   public static PlayerItemModel parseItemModel(JsonObject obj) {
/*  46 */     String type = Json.getString(obj, "type");
/*     */     
/*  48 */     if (!Config.equals(type, "PlayerItem"))
/*     */     {
/*  50 */       throw new JsonParseException("Unknown model type: " + type);
/*     */     }
/*     */ 
/*     */     
/*  54 */     int[] textureSize = Json.parseIntArray(obj.get("textureSize"), 2);
/*  55 */     checkNull(textureSize, "Missing texture size");
/*  56 */     Dimension textureDim = new Dimension(textureSize[0], textureSize[1]);
/*  57 */     boolean usePlayerTexture = Json.getBoolean(obj, "usePlayerTexture", false);
/*  58 */     JsonArray models = (JsonArray)obj.get("models");
/*  59 */     checkNull(models, "Missing elements");
/*  60 */     HashMap<Object, Object> mapModelJsons = new HashMap<>();
/*  61 */     ArrayList listModels = new ArrayList();
/*     */ 
/*     */     
/*  64 */     for (int modelRenderers = 0; modelRenderers < models.size(); modelRenderers++) {
/*     */       
/*  66 */       JsonObject elem = (JsonObject)models.get(modelRenderers);
/*  67 */       String baseId = Json.getString(elem, "baseId");
/*     */       
/*  69 */       if (baseId != null)
/*     */       
/*  71 */       { JsonObject id = (JsonObject)mapModelJsons.get(baseId);
/*     */         
/*  73 */         if (id == null)
/*     */         
/*  75 */         { Config.warn("BaseID not found: " + baseId); }
/*     */         
/*     */         else
/*     */         
/*  79 */         { Set mr = id.entrySet();
/*  80 */           Iterator<Map.Entry> iterator = mr.iterator();
/*     */           
/*  82 */           while (iterator.hasNext()) {
/*     */             
/*  84 */             Map.Entry entry = iterator.next();
/*     */             
/*  86 */             if (!elem.has((String)entry.getKey()))
/*     */             {
/*  88 */               elem.add((String)entry.getKey(), (JsonElement)entry.getValue());
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/*  93 */           String var17 = Json.getString(elem, "id"); }  continue; }  String str1 = Json.getString(elem, "id");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     PlayerItemRenderer[] var16 = (PlayerItemRenderer[])listModels.toArray((Object[])new PlayerItemRenderer[listModels.size()]);
/* 116 */     return new PlayerItemModel(textureDim, usePlayerTexture, var16);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void checkNull(Object obj, String msg) {
/* 122 */     if (obj == null)
/*     */     {
/* 124 */       throw new JsonParseException(msg);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static ResourceLocation makeResourceLocation(String texture) {
/* 130 */     int pos = texture.indexOf(':');
/*     */     
/* 132 */     if (pos < 0)
/*     */     {
/* 134 */       return new ResourceLocation(texture);
/*     */     }
/*     */ 
/*     */     
/* 138 */     String domain = texture.substring(0, pos);
/* 139 */     String path = texture.substring(pos + 1);
/* 140 */     return new ResourceLocation(domain, path);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int parseAttachModel(String attachModelStr) {
/* 146 */     if (attachModelStr == null)
/*     */     {
/* 148 */       return 0;
/*     */     }
/* 150 */     if (attachModelStr.equals("body"))
/*     */     {
/* 152 */       return 0;
/*     */     }
/* 154 */     if (attachModelStr.equals("head"))
/*     */     {
/* 156 */       return 1;
/*     */     }
/* 158 */     if (attachModelStr.equals("leftArm"))
/*     */     {
/* 160 */       return 2;
/*     */     }
/* 162 */     if (attachModelStr.equals("rightArm"))
/*     */     {
/* 164 */       return 3;
/*     */     }
/* 166 */     if (attachModelStr.equals("leftLeg"))
/*     */     {
/* 168 */       return 4;
/*     */     }
/* 170 */     if (attachModelStr.equals("rightLeg"))
/*     */     {
/* 172 */       return 5;
/*     */     }
/* 174 */     if (attachModelStr.equals("cape"))
/*     */     {
/* 176 */       return 6;
/*     */     }
/*     */ 
/*     */     
/* 180 */     Config.warn("Unknown attachModel: " + attachModelStr);
/* 181 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static PlayerItemRenderer parseItemRenderer(JsonObject elem, Dimension textureDim) {
/* 187 */     String type = Json.getString(elem, "type");
/*     */     
/* 189 */     if (!Config.equals(type, "ModelBox")) {
/*     */       
/* 191 */       Config.warn("Unknown model type: " + type);
/* 192 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 196 */     String attachToStr = Json.getString(elem, "attachTo");
/* 197 */     int attachTo = parseAttachModel(attachToStr);
/* 198 */     float scale = Json.getFloat(elem, "scale", 1.0F);
/* 199 */     ModelPlayerItem modelBase = new ModelPlayerItem();
/* 200 */     modelBase.textureWidth = textureDim.width;
/* 201 */     modelBase.textureHeight = textureDim.height;
/* 202 */     ModelRenderer mr = parseModelRenderer(elem, modelBase);
/* 203 */     PlayerItemRenderer pir = new PlayerItemRenderer(attachTo, scale, mr);
/* 204 */     return pir;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static ModelRenderer parseModelRenderer(JsonObject elem, ModelBase modelBase) {
/* 210 */     ModelRenderer mr = new ModelRenderer(modelBase);
/* 211 */     String invertAxis = Json.getString(elem, "invertAxis", "").toLowerCase();
/* 212 */     boolean invertX = invertAxis.contains("x");
/* 213 */     boolean invertY = invertAxis.contains("y");
/* 214 */     boolean invertZ = invertAxis.contains("z");
/* 215 */     float[] translate = Json.parseFloatArray(elem.get("translate"), 3, new float[3]);
/*     */     
/* 217 */     if (invertX)
/*     */     {
/* 219 */       translate[0] = -translate[0];
/*     */     }
/*     */     
/* 222 */     if (invertY)
/*     */     {
/* 224 */       translate[1] = -translate[1];
/*     */     }
/*     */     
/* 227 */     if (invertZ)
/*     */     {
/* 229 */       translate[2] = -translate[2];
/*     */     }
/*     */     
/* 232 */     float[] rotateAngles = Json.parseFloatArray(elem.get("rotate"), 3, new float[3]);
/*     */     
/* 234 */     for (int mirrorTexture = 0; mirrorTexture < rotateAngles.length; mirrorTexture++)
/*     */     {
/* 236 */       rotateAngles[mirrorTexture] = rotateAngles[mirrorTexture] / 180.0F * 3.1415927F;
/*     */     }
/*     */     
/* 239 */     if (invertX)
/*     */     {
/* 241 */       rotateAngles[0] = -rotateAngles[0];
/*     */     }
/*     */     
/* 244 */     if (invertY)
/*     */     {
/* 246 */       rotateAngles[1] = -rotateAngles[1];
/*     */     }
/*     */     
/* 249 */     if (invertZ)
/*     */     {
/* 251 */       rotateAngles[2] = -rotateAngles[2];
/*     */     }
/*     */     
/* 254 */     mr.setRotationPoint(translate[0], translate[1], translate[2]);
/* 255 */     mr.rotateAngleX = rotateAngles[0];
/* 256 */     mr.rotateAngleY = rotateAngles[1];
/* 257 */     mr.rotateAngleZ = rotateAngles[2];
/* 258 */     String var19 = Json.getString(elem, "mirrorTexture", "").toLowerCase();
/* 259 */     boolean invertU = var19.contains("u");
/* 260 */     boolean invertV = var19.contains("v");
/*     */     
/* 262 */     if (invertU)
/*     */     {
/* 264 */       mr.mirror = true;
/*     */     }
/*     */     
/* 267 */     if (invertV)
/*     */     {
/* 269 */       mr.mirrorV = true;
/*     */     }
/*     */     
/* 272 */     JsonArray boxes = elem.getAsJsonArray("boxes");
/*     */ 
/*     */     
/* 275 */     if (boxes != null)
/*     */     {
/* 277 */       for (int sprites = 0; sprites < boxes.size(); sprites++) {
/*     */         
/* 279 */         JsonObject jsonObject = boxes.get(sprites).getAsJsonObject();
/* 280 */         int[] submodels = Json.parseIntArray(jsonObject.get("textureOffset"), 2);
/*     */         
/* 282 */         if (submodels == null)
/*     */         {
/* 284 */           throw new JsonParseException("Texture offset not specified");
/*     */         }
/*     */         
/* 287 */         float[] i = Json.parseFloatArray(jsonObject.get("coordinates"), 6);
/*     */         
/* 289 */         if (i == null)
/*     */         {
/* 291 */           throw new JsonParseException("Coordinates not specified");
/*     */         }
/*     */         
/* 294 */         if (invertX)
/*     */         {
/* 296 */           i[0] = -i[0] - i[3];
/*     */         }
/*     */         
/* 299 */         if (invertY)
/*     */         {
/* 301 */           i[1] = -i[1] - i[4];
/*     */         }
/*     */         
/* 304 */         if (invertZ)
/*     */         {
/* 306 */           i[2] = -i[2] - i[5];
/*     */         }
/*     */         
/* 309 */         float sm = Json.getFloat(jsonObject, "sizeAdd", 0.0F);
/* 310 */         mr.setTextureOffset(submodels[0], submodels[1]);
/* 311 */         mr.addBox(i[0], i[1], i[2], (int)i[3], (int)i[4], (int)i[5], sm);
/*     */       } 
/*     */     }
/*     */     
/* 315 */     JsonArray var20 = elem.getAsJsonArray("sprites");
/*     */     
/* 317 */     if (var20 != null)
/*     */     {
/* 319 */       for (int var21 = 0; var21 < var20.size(); var21++) {
/*     */         
/* 321 */         JsonObject var22 = var20.get(var21).getAsJsonObject();
/* 322 */         int[] var25 = Json.parseIntArray(var22.get("textureOffset"), 2);
/*     */         
/* 324 */         if (var25 == null)
/*     */         {
/* 326 */           throw new JsonParseException("Texture offset not specified");
/*     */         }
/*     */         
/* 329 */         float[] var27 = Json.parseFloatArray(var22.get("coordinates"), 6);
/*     */         
/* 331 */         if (var27 == null)
/*     */         {
/* 333 */           throw new JsonParseException("Coordinates not specified");
/*     */         }
/*     */         
/* 336 */         if (invertX)
/*     */         {
/* 338 */           var27[0] = -var27[0] - var27[3];
/*     */         }
/*     */         
/* 341 */         if (invertY)
/*     */         {
/* 343 */           var27[1] = -var27[1] - var27[4];
/*     */         }
/*     */         
/* 346 */         if (invertZ)
/*     */         {
/* 348 */           var27[2] = -var27[2] - var27[5];
/*     */         }
/*     */         
/* 351 */         float subMr = Json.getFloat(var22, "sizeAdd", 0.0F);
/* 352 */         mr.setTextureOffset(var25[0], var25[1]);
/* 353 */         mr.addSprite(var27[0], var27[1], var27[2], (int)var27[3], (int)var27[4], (int)var27[5], subMr);
/*     */       } 
/*     */     }
/*     */     
/* 357 */     JsonObject submodel = (JsonObject)elem.get("submodel");
/*     */     
/* 359 */     if (submodel != null) {
/*     */       
/* 361 */       ModelRenderer var23 = parseModelRenderer(submodel, modelBase);
/* 362 */       mr.addChild(var23);
/*     */     } 
/*     */     
/* 365 */     JsonArray var24 = (JsonArray)elem.get("submodels");
/*     */     
/* 367 */     if (var24 != null)
/*     */     {
/* 369 */       for (int var26 = 0; var26 < var24.size(); var26++) {
/*     */         
/* 371 */         JsonObject var28 = (JsonObject)var24.get(var26);
/* 372 */         ModelRenderer var29 = parseModelRenderer(var28, modelBase);
/* 373 */         mr.addChild(var29);
/*     */       } 
/*     */     }
/*     */     
/* 377 */     return mr;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\PlayerItemParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */