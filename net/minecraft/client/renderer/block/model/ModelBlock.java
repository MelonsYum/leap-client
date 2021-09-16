/*     */ package net.minecraft.client.renderer.block.model;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class ModelBlock
/*     */ {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger();
/*  31 */   static final Gson SERIALIZER = (new GsonBuilder()).registerTypeAdapter(ModelBlock.class, new Deserializer()).registerTypeAdapter(BlockPart.class, new BlockPart.Deserializer()).registerTypeAdapter(BlockPartFace.class, new BlockPartFace.Deserializer()).registerTypeAdapter(BlockFaceUV.class, new BlockFaceUV.Deserializer()).registerTypeAdapter(ItemTransformVec3f.class, new ItemTransformVec3f.Deserializer()).registerTypeAdapter(ItemCameraTransforms.class, new ItemCameraTransforms.Deserializer()).create();
/*     */   
/*     */   private final List elements;
/*     */   private final boolean ambientOcclusion;
/*     */   private final boolean field_178322_i;
/*     */   private ItemCameraTransforms itemCameraTransforms;
/*     */   public String field_178317_b;
/*     */   protected final Map textures;
/*     */   protected ModelBlock parent;
/*     */   protected ResourceLocation parentLocation;
/*     */   private static final String __OBFID = "CL_00002503";
/*     */   
/*     */   public static ModelBlock deserialize(Reader p_178307_0_) {
/*  44 */     return (ModelBlock)SERIALIZER.fromJson(p_178307_0_, ModelBlock.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ModelBlock deserialize(String p_178294_0_) {
/*  49 */     return deserialize(new StringReader(p_178294_0_));
/*     */   }
/*     */ 
/*     */   
/*     */   protected ModelBlock(List p_i46225_1_, Map p_i46225_2_, boolean p_i46225_3_, boolean p_i46225_4_, ItemCameraTransforms p_i46225_5_) {
/*  54 */     this(null, p_i46225_1_, p_i46225_2_, p_i46225_3_, p_i46225_4_, p_i46225_5_);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ModelBlock(ResourceLocation p_i46226_1_, Map p_i46226_2_, boolean p_i46226_3_, boolean p_i46226_4_, ItemCameraTransforms p_i46226_5_) {
/*  59 */     this(p_i46226_1_, Collections.emptyList(), p_i46226_2_, p_i46226_3_, p_i46226_4_, p_i46226_5_);
/*     */   }
/*     */ 
/*     */   
/*     */   private ModelBlock(ResourceLocation p_i46227_1_, List p_i46227_2_, Map p_i46227_3_, boolean p_i46227_4_, boolean p_i46227_5_, ItemCameraTransforms p_i46227_6_) {
/*  64 */     this.field_178317_b = "";
/*  65 */     this.elements = p_i46227_2_;
/*  66 */     this.field_178322_i = p_i46227_4_;
/*  67 */     this.ambientOcclusion = p_i46227_5_;
/*  68 */     this.textures = p_i46227_3_;
/*  69 */     this.parentLocation = p_i46227_1_;
/*  70 */     this.itemCameraTransforms = p_i46227_6_;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getElements() {
/*  75 */     return hasParent() ? this.parent.getElements() : this.elements;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean hasParent() {
/*  80 */     return (this.parent != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178309_b() {
/*  85 */     return hasParent() ? this.parent.func_178309_b() : this.field_178322_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAmbientOcclusionEnabled() {
/*  90 */     return this.ambientOcclusion;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isResolved() {
/*  95 */     return !(this.parentLocation != null && (this.parent == null || !this.parent.isResolved()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void getParentFromMap(Map p_178299_1_) {
/* 100 */     if (this.parentLocation != null)
/*     */     {
/* 102 */       this.parent = (ModelBlock)p_178299_1_.get(this.parentLocation);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTexturePresent(String p_178300_1_) {
/* 108 */     return !"missingno".equals(resolveTextureName(p_178300_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public String resolveTextureName(String p_178308_1_) {
/* 113 */     if (!isTextureName(p_178308_1_))
/*     */     {
/* 115 */       p_178308_1_ = String.valueOf('#') + p_178308_1_;
/*     */     }
/*     */     
/* 118 */     return resolveTextureName(p_178308_1_, new Bookkeep(null));
/*     */   }
/*     */ 
/*     */   
/*     */   private String resolveTextureName(String p_178302_1_, Bookkeep p_178302_2_) {
/* 123 */     if (isTextureName(p_178302_1_)) {
/*     */       
/* 125 */       if (this == p_178302_2_.field_178323_b) {
/*     */         
/* 127 */         LOGGER.warn("Unable to resolve texture due to upward reference: " + p_178302_1_ + " in " + this.field_178317_b);
/* 128 */         return "missingno";
/*     */       } 
/*     */ 
/*     */       
/* 132 */       String var3 = (String)this.textures.get(p_178302_1_.substring(1));
/*     */       
/* 134 */       if (var3 == null && hasParent())
/*     */       {
/* 136 */         var3 = this.parent.resolveTextureName(p_178302_1_, p_178302_2_);
/*     */       }
/*     */       
/* 139 */       p_178302_2_.field_178323_b = this;
/*     */       
/* 141 */       if (var3 != null && isTextureName(var3))
/*     */       {
/* 143 */         var3 = p_178302_2_.model.resolveTextureName(var3, p_178302_2_);
/*     */       }
/*     */       
/* 146 */       return (var3 != null && !isTextureName(var3)) ? var3 : "missingno";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 151 */     return p_178302_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isTextureName(String p_178304_1_) {
/* 157 */     return (p_178304_1_.charAt(0) == '#');
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getParentLocation() {
/* 162 */     return this.parentLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBlock getRootModel() {
/* 167 */     return hasParent() ? this.parent.getRootModel() : this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemTransformVec3f getThirdPersonTransform() {
/* 172 */     return (this.parent != null && this.itemCameraTransforms.field_178355_b == ItemTransformVec3f.field_178366_a) ? this.parent.getThirdPersonTransform() : this.itemCameraTransforms.field_178355_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemTransformVec3f getFirstPersonTransform() {
/* 177 */     return (this.parent != null && this.itemCameraTransforms.field_178356_c == ItemTransformVec3f.field_178366_a) ? this.parent.getFirstPersonTransform() : this.itemCameraTransforms.field_178356_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemTransformVec3f getHeadTransform() {
/* 182 */     return (this.parent != null && this.itemCameraTransforms.field_178353_d == ItemTransformVec3f.field_178366_a) ? this.parent.getHeadTransform() : this.itemCameraTransforms.field_178353_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemTransformVec3f getInGuiTransform() {
/* 187 */     return (this.parent != null && this.itemCameraTransforms.field_178354_e == ItemTransformVec3f.field_178366_a) ? this.parent.getInGuiTransform() : this.itemCameraTransforms.field_178354_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_178312_b(Map p_178312_0_) {
/* 192 */     Iterator<ModelBlock> var1 = p_178312_0_.values().iterator();
/*     */     
/* 194 */     while (var1.hasNext()) {
/*     */       
/* 196 */       ModelBlock var2 = var1.next();
/*     */ 
/*     */       
/*     */       try {
/* 200 */         ModelBlock var3 = var2.parent;
/*     */         
/* 202 */         for (ModelBlock var4 = var3.parent; var3 != var4; var4 = var4.parent.parent)
/*     */         {
/* 204 */           var3 = var3.parent;
/*     */         }
/*     */         
/* 207 */         throw new LoopException();
/*     */       }
/* 209 */       catch (NullPointerException nullPointerException) {}
/*     */     } 
/*     */   }
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
/*     */   final class Bookkeep
/*     */   {
/* 224 */     public final ModelBlock model = ModelBlock.this;
/*     */     
/*     */     public ModelBlock field_178323_b;
/*     */     
/*     */     Bookkeep(Object p_i46224_2_) {
/* 229 */       this();
/*     */     }
/*     */     
/*     */     private static final String __OBFID = "CL_00002501";
/*     */     
/*     */     private Bookkeep() {} }
/*     */   
/*     */   public static class Deserializer implements JsonDeserializer { private static final String __OBFID = "CL_00002500";
/*     */     
/*     */     public ModelBlock func_178327_a(JsonElement p_178327_1_, Type p_178327_2_, JsonDeserializationContext p_178327_3_) {
/* 239 */       JsonObject var4 = p_178327_1_.getAsJsonObject();
/* 240 */       List var5 = getModelElements(p_178327_3_, var4);
/* 241 */       String var6 = getParent(var4);
/* 242 */       boolean var7 = StringUtils.isEmpty(var6);
/* 243 */       boolean var8 = var5.isEmpty();
/*     */       
/* 245 */       if (var8 && var7)
/*     */       {
/* 247 */         throw new JsonParseException("BlockModel requires either elements or parent, found neither");
/*     */       }
/* 249 */       if (!var7 && !var8)
/*     */       {
/* 251 */         throw new JsonParseException("BlockModel requires either elements or parent, found both");
/*     */       }
/*     */ 
/*     */       
/* 255 */       Map var9 = getTextures(var4);
/* 256 */       boolean var10 = getAmbientOcclusionEnabled(var4);
/* 257 */       ItemCameraTransforms var11 = ItemCameraTransforms.field_178357_a;
/*     */       
/* 259 */       if (var4.has("display")) {
/*     */         
/* 261 */         JsonObject var12 = JsonUtils.getJsonObject(var4, "display");
/* 262 */         var11 = (ItemCameraTransforms)p_178327_3_.deserialize((JsonElement)var12, ItemCameraTransforms.class);
/*     */       } 
/*     */       
/* 265 */       return var8 ? new ModelBlock(new ResourceLocation(var6), var9, var10, true, var11) : new ModelBlock(var5, var9, var10, true, var11);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Map getTextures(JsonObject p_178329_1_) {
/* 271 */       HashMap var2 = Maps.newHashMap();
/*     */       
/* 273 */       if (p_178329_1_.has("textures")) {
/*     */         
/* 275 */         JsonObject var3 = p_178329_1_.getAsJsonObject("textures");
/* 276 */         Iterator<Map.Entry> var4 = var3.entrySet().iterator();
/*     */         
/* 278 */         while (var4.hasNext()) {
/*     */           
/* 280 */           Map.Entry var5 = var4.next();
/* 281 */           var2.put(var5.getKey(), ((JsonElement)var5.getValue()).getAsString());
/*     */         } 
/*     */       } 
/*     */       
/* 285 */       return var2;
/*     */     }
/*     */ 
/*     */     
/*     */     private String getParent(JsonObject p_178326_1_) {
/* 290 */       return JsonUtils.getJsonObjectStringFieldValueOrDefault(p_178326_1_, "parent", "");
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean getAmbientOcclusionEnabled(JsonObject p_178328_1_) {
/* 295 */       return JsonUtils.getJsonObjectBooleanFieldValueOrDefault(p_178328_1_, "ambientocclusion", true);
/*     */     }
/*     */ 
/*     */     
/*     */     protected List getModelElements(JsonDeserializationContext p_178325_1_, JsonObject p_178325_2_) {
/* 300 */       ArrayList<BlockPart> var3 = Lists.newArrayList();
/*     */       
/* 302 */       if (p_178325_2_.has("elements")) {
/*     */         
/* 304 */         Iterator<JsonElement> var4 = JsonUtils.getJsonObjectJsonArrayField(p_178325_2_, "elements").iterator();
/*     */         
/* 306 */         while (var4.hasNext()) {
/*     */           
/* 308 */           JsonElement var5 = var4.next();
/* 309 */           var3.add((BlockPart)p_178325_1_.deserialize(var5, BlockPart.class));
/*     */         } 
/*     */       } 
/*     */       
/* 313 */       return var3;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 318 */       return func_178327_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class LoopException extends RuntimeException {
/*     */     private static final String __OBFID = "CL_00002499";
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\ModelBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */