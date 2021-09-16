/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JsonUtils
/*     */ {
/*     */   private static final String __OBFID = "CL_00001484";
/*     */   
/*     */   public static boolean jsonObjectFieldTypeIsString(JsonObject p_151205_0_, String p_151205_1_) {
/*  18 */     return !jsonObjectFieldTypeIsPrimitive(p_151205_0_, p_151205_1_) ? false : p_151205_0_.getAsJsonPrimitive(p_151205_1_).isString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean jsonElementTypeIsString(JsonElement p_151211_0_) {
/*  26 */     return !p_151211_0_.isJsonPrimitive() ? false : p_151211_0_.getAsJsonPrimitive().isString();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_180199_c(JsonObject p_180199_0_, String p_180199_1_) {
/*  31 */     return !jsonObjectFieldTypeIsPrimitive(p_180199_0_, p_180199_1_) ? false : p_180199_0_.getAsJsonPrimitive(p_180199_1_).isBoolean();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean jsonObjectFieldTypeIsArray(JsonObject p_151202_0_, String p_151202_1_) {
/*  39 */     return !jsonObjectHasNamedField(p_151202_0_, p_151202_1_) ? false : p_151202_0_.get(p_151202_1_).isJsonArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean jsonObjectFieldTypeIsPrimitive(JsonObject p_151201_0_, String p_151201_1_) {
/*  48 */     return !jsonObjectHasNamedField(p_151201_0_, p_151201_1_) ? false : p_151201_0_.get(p_151201_1_).isJsonPrimitive();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean jsonObjectHasNamedField(JsonObject p_151204_0_, String p_151204_1_) {
/*  56 */     return (p_151204_0_ == null) ? false : ((p_151204_0_.get(p_151204_1_) != null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getJsonElementStringValue(JsonElement p_151206_0_, String p_151206_1_) {
/*  65 */     if (p_151206_0_.isJsonPrimitive())
/*     */     {
/*  67 */       return p_151206_0_.getAsString();
/*     */     }
/*     */ 
/*     */     
/*  71 */     throw new JsonSyntaxException("Expected " + p_151206_1_ + " to be a string, was " + getJsonElementTypeDescription(p_151206_0_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getJsonObjectStringFieldValue(JsonObject p_151200_0_, String p_151200_1_) {
/*  80 */     if (p_151200_0_.has(p_151200_1_))
/*     */     {
/*  82 */       return getJsonElementStringValue(p_151200_0_.get(p_151200_1_), p_151200_1_);
/*     */     }
/*     */ 
/*     */     
/*  86 */     throw new JsonSyntaxException("Missing " + p_151200_1_ + ", expected to find a string");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getJsonObjectStringFieldValueOrDefault(JsonObject p_151219_0_, String p_151219_1_, String p_151219_2_) {
/*  96 */     return p_151219_0_.has(p_151219_1_) ? getJsonElementStringValue(p_151219_0_.get(p_151219_1_), p_151219_1_) : p_151219_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getJsonElementBooleanValue(JsonElement p_151216_0_, String p_151216_1_) {
/* 105 */     if (p_151216_0_.isJsonPrimitive())
/*     */     {
/* 107 */       return p_151216_0_.getAsBoolean();
/*     */     }
/*     */ 
/*     */     
/* 111 */     throw new JsonSyntaxException("Expected " + p_151216_1_ + " to be a Boolean, was " + getJsonElementTypeDescription(p_151216_0_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getJsonObjectBooleanFieldValue(JsonObject p_151212_0_, String p_151212_1_) {
/* 120 */     if (p_151212_0_.has(p_151212_1_))
/*     */     {
/* 122 */       return getJsonElementBooleanValue(p_151212_0_.get(p_151212_1_), p_151212_1_);
/*     */     }
/*     */ 
/*     */     
/* 126 */     throw new JsonSyntaxException("Missing " + p_151212_1_ + ", expected to find a Boolean");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean getJsonObjectBooleanFieldValueOrDefault(JsonObject p_151209_0_, String p_151209_1_, boolean p_151209_2_) {
/* 136 */     return p_151209_0_.has(p_151209_1_) ? getJsonElementBooleanValue(p_151209_0_.get(p_151209_1_), p_151209_1_) : p_151209_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getJsonElementFloatValue(JsonElement p_151220_0_, String p_151220_1_) {
/* 145 */     if (p_151220_0_.isJsonPrimitive() && p_151220_0_.getAsJsonPrimitive().isNumber())
/*     */     {
/* 147 */       return p_151220_0_.getAsFloat();
/*     */     }
/*     */ 
/*     */     
/* 151 */     throw new JsonSyntaxException("Expected " + p_151220_1_ + " to be a Float, was " + getJsonElementTypeDescription(p_151220_0_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getJsonObjectFloatFieldValue(JsonObject p_151217_0_, String p_151217_1_) {
/* 160 */     if (p_151217_0_.has(p_151217_1_))
/*     */     {
/* 162 */       return getJsonElementFloatValue(p_151217_0_.get(p_151217_1_), p_151217_1_);
/*     */     }
/*     */ 
/*     */     
/* 166 */     throw new JsonSyntaxException("Missing " + p_151217_1_ + ", expected to find a Float");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getJsonObjectFloatFieldValueOrDefault(JsonObject p_151221_0_, String p_151221_1_, float p_151221_2_) {
/* 176 */     return p_151221_0_.has(p_151221_1_) ? getJsonElementFloatValue(p_151221_0_.get(p_151221_1_), p_151221_1_) : p_151221_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getJsonElementIntegerValue(JsonElement p_151215_0_, String p_151215_1_) {
/* 185 */     if (p_151215_0_.isJsonPrimitive() && p_151215_0_.getAsJsonPrimitive().isNumber())
/*     */     {
/* 187 */       return p_151215_0_.getAsInt();
/*     */     }
/*     */ 
/*     */     
/* 191 */     throw new JsonSyntaxException("Expected " + p_151215_1_ + " to be a Int, was " + getJsonElementTypeDescription(p_151215_0_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getJsonObjectIntegerFieldValue(JsonObject p_151203_0_, String p_151203_1_) {
/* 200 */     if (p_151203_0_.has(p_151203_1_))
/*     */     {
/* 202 */       return getJsonElementIntegerValue(p_151203_0_.get(p_151203_1_), p_151203_1_);
/*     */     }
/*     */ 
/*     */     
/* 206 */     throw new JsonSyntaxException("Missing " + p_151203_1_ + ", expected to find a Int");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getJsonObjectIntegerFieldValueOrDefault(JsonObject p_151208_0_, String p_151208_1_, int p_151208_2_) {
/* 216 */     return p_151208_0_.has(p_151208_1_) ? getJsonElementIntegerValue(p_151208_0_.get(p_151208_1_), p_151208_1_) : p_151208_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonObject getElementAsJsonObject(JsonElement p_151210_0_, String p_151210_1_) {
/* 225 */     if (p_151210_0_.isJsonObject())
/*     */     {
/* 227 */       return p_151210_0_.getAsJsonObject();
/*     */     }
/*     */ 
/*     */     
/* 231 */     throw new JsonSyntaxException("Expected " + p_151210_1_ + " to be a JsonObject, was " + getJsonElementTypeDescription(p_151210_0_));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonObject getJsonObject(JsonObject base, String key) {
/* 237 */     if (base.has(key))
/*     */     {
/* 239 */       return getElementAsJsonObject(base.get(key), key);
/*     */     }
/*     */ 
/*     */     
/* 243 */     throw new JsonSyntaxException("Missing " + key + ", expected to find a JsonObject");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonObject getJsonObjectFieldOrDefault(JsonObject p_151218_0_, String p_151218_1_, JsonObject p_151218_2_) {
/* 253 */     return p_151218_0_.has(p_151218_1_) ? getElementAsJsonObject(p_151218_0_.get(p_151218_1_), p_151218_1_) : p_151218_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonArray getJsonElementAsJsonArray(JsonElement p_151207_0_, String p_151207_1_) {
/* 262 */     if (p_151207_0_.isJsonArray())
/*     */     {
/* 264 */       return p_151207_0_.getAsJsonArray();
/*     */     }
/*     */ 
/*     */     
/* 268 */     throw new JsonSyntaxException("Expected " + p_151207_1_ + " to be a JsonArray, was " + getJsonElementTypeDescription(p_151207_0_));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonArray getJsonObjectJsonArrayField(JsonObject p_151214_0_, String p_151214_1_) {
/* 277 */     if (p_151214_0_.has(p_151214_1_))
/*     */     {
/* 279 */       return getJsonElementAsJsonArray(p_151214_0_.get(p_151214_1_), p_151214_1_);
/*     */     }
/*     */ 
/*     */     
/* 283 */     throw new JsonSyntaxException("Missing " + p_151214_1_ + ", expected to find a JsonArray");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonArray getJsonObjectJsonArrayFieldOrDefault(JsonObject p_151213_0_, String p_151213_1_, JsonArray p_151213_2_) {
/* 293 */     return p_151213_0_.has(p_151213_1_) ? getJsonElementAsJsonArray(p_151213_0_.get(p_151213_1_), p_151213_1_) : p_151213_2_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getJsonElementTypeDescription(JsonElement p_151222_0_) {
/* 301 */     String var1 = StringUtils.abbreviateMiddle(String.valueOf(p_151222_0_), "...", 10);
/*     */     
/* 303 */     if (p_151222_0_ == null)
/*     */     {
/* 305 */       return "null (missing)";
/*     */     }
/* 307 */     if (p_151222_0_.isJsonNull())
/*     */     {
/* 309 */       return "null (json)";
/*     */     }
/* 311 */     if (p_151222_0_.isJsonArray())
/*     */     {
/* 313 */       return "an array (" + var1 + ")";
/*     */     }
/* 315 */     if (p_151222_0_.isJsonObject())
/*     */     {
/* 317 */       return "an object (" + var1 + ")";
/*     */     }
/*     */ 
/*     */     
/* 321 */     if (p_151222_0_.isJsonPrimitive()) {
/*     */       
/* 323 */       JsonPrimitive var2 = p_151222_0_.getAsJsonPrimitive();
/*     */       
/* 325 */       if (var2.isNumber())
/*     */       {
/* 327 */         return "a number (" + var1 + ")";
/*     */       }
/*     */       
/* 330 */       if (var2.isBoolean())
/*     */       {
/* 332 */         return "a boolean (" + var1 + ")";
/*     */       }
/*     */     } 
/*     */     
/* 336 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\JsonUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */