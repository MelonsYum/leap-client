/*     */ package net.minecraft.client.resources.data;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ 
/*     */ public class AnimationMetadataSectionSerializer
/*     */   extends BaseMetadataSectionSerializer
/*     */   implements JsonSerializer {
/*     */   private static final String __OBFID = "CL_00001107";
/*     */   
/*     */   public AnimationMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/*  23 */     ArrayList<AnimationFrame> var4 = Lists.newArrayList();
/*  24 */     JsonObject var5 = JsonUtils.getElementAsJsonObject(p_deserialize_1_, "metadata section");
/*  25 */     int var6 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var5, "frametime", 1);
/*     */     
/*  27 */     if (var6 != 1)
/*     */     {
/*  29 */       Validate.inclusiveBetween(1L, 2147483647L, var6, "Invalid default frame time");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  34 */     if (var5.has("frames")) {
/*     */       
/*     */       try {
/*     */         
/*  38 */         JsonArray var7 = JsonUtils.getJsonObjectJsonArrayField(var5, "frames");
/*     */         
/*  40 */         for (int i = 0; i < var7.size(); i++)
/*     */         {
/*  42 */           JsonElement var9 = var7.get(i);
/*  43 */           AnimationFrame var10 = parseAnimationFrame(i, var9);
/*     */           
/*  45 */           if (var10 != null)
/*     */           {
/*  47 */             var4.add(var10);
/*     */           }
/*     */         }
/*     */       
/*  51 */       } catch (ClassCastException var11) {
/*     */         
/*  53 */         throw new JsonParseException("Invalid animation->frames: expected array, was " + var5.get("frames"), var11);
/*     */       } 
/*     */     }
/*     */     
/*  57 */     int var12 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var5, "width", -1);
/*  58 */     int var8 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var5, "height", -1);
/*     */     
/*  60 */     if (var12 != -1)
/*     */     {
/*  62 */       Validate.inclusiveBetween(1L, 2147483647L, var12, "Invalid width");
/*     */     }
/*     */     
/*  65 */     if (var8 != -1)
/*     */     {
/*  67 */       Validate.inclusiveBetween(1L, 2147483647L, var8, "Invalid height");
/*     */     }
/*     */     
/*  70 */     boolean var13 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var5, "interpolate", false);
/*  71 */     return new AnimationMetadataSection(var4, var12, var8, var6, var13);
/*     */   }
/*     */ 
/*     */   
/*     */   private AnimationFrame parseAnimationFrame(int p_110492_1_, JsonElement p_110492_2_) {
/*  76 */     if (p_110492_2_.isJsonPrimitive())
/*     */     {
/*  78 */       return new AnimationFrame(JsonUtils.getJsonElementIntegerValue(p_110492_2_, "frames[" + p_110492_1_ + "]"));
/*     */     }
/*  80 */     if (p_110492_2_.isJsonObject()) {
/*     */       
/*  82 */       JsonObject var3 = JsonUtils.getElementAsJsonObject(p_110492_2_, "frames[" + p_110492_1_ + "]");
/*  83 */       int var4 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(var3, "time", -1);
/*     */       
/*  85 */       if (var3.has("time"))
/*     */       {
/*  87 */         Validate.inclusiveBetween(1L, 2147483647L, var4, "Invalid frame time");
/*     */       }
/*     */       
/*  90 */       int var5 = JsonUtils.getJsonObjectIntegerFieldValue(var3, "index");
/*  91 */       Validate.inclusiveBetween(0L, 2147483647L, var5, "Invalid frame index");
/*  92 */       return new AnimationFrame(var5, var4);
/*     */     } 
/*     */ 
/*     */     
/*  96 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonElement serialize(AnimationMetadataSection p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 102 */     JsonObject var4 = new JsonObject();
/* 103 */     var4.addProperty("frametime", Integer.valueOf(p_serialize_1_.getFrameTime()));
/*     */     
/* 105 */     if (p_serialize_1_.getFrameWidth() != -1)
/*     */     {
/* 107 */       var4.addProperty("width", Integer.valueOf(p_serialize_1_.getFrameWidth()));
/*     */     }
/*     */     
/* 110 */     if (p_serialize_1_.getFrameHeight() != -1)
/*     */     {
/* 112 */       var4.addProperty("height", Integer.valueOf(p_serialize_1_.getFrameHeight()));
/*     */     }
/*     */     
/* 115 */     if (p_serialize_1_.getFrameCount() > 0) {
/*     */       
/* 117 */       JsonArray var5 = new JsonArray();
/*     */       
/* 119 */       for (int var6 = 0; var6 < p_serialize_1_.getFrameCount(); var6++) {
/*     */         
/* 121 */         if (p_serialize_1_.frameHasTime(var6)) {
/*     */           
/* 123 */           JsonObject var7 = new JsonObject();
/* 124 */           var7.addProperty("index", Integer.valueOf(p_serialize_1_.getFrameIndex(var6)));
/* 125 */           var7.addProperty("time", Integer.valueOf(p_serialize_1_.getFrameTimeSingle(var6)));
/* 126 */           var5.add((JsonElement)var7);
/*     */         }
/*     */         else {
/*     */           
/* 130 */           var5.add((JsonElement)new JsonPrimitive(Integer.valueOf(p_serialize_1_.getFrameIndex(var6))));
/*     */         } 
/*     */       } 
/*     */       
/* 134 */       var4.add("frames", (JsonElement)var5);
/*     */     } 
/*     */     
/* 137 */     return (JsonElement)var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSectionName() {
/* 145 */     return "animation";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 152 */     return serialize((AnimationMetadataSection)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\AnimationMetadataSectionSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */