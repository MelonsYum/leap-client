/*     */ package net.minecraft.util;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public interface IChatComponent
/*     */   extends Iterable
/*     */ {
/*     */   IChatComponent setChatStyle(ChatStyle paramChatStyle);
/*     */   
/*     */   ChatStyle getChatStyle();
/*     */   
/*     */   IChatComponent appendText(String paramString);
/*     */   
/*     */   IChatComponent appendSibling(IChatComponent paramIChatComponent);
/*     */   
/*     */   String getUnformattedTextForChat();
/*     */   
/*     */   String getUnformattedText();
/*     */   
/*     */   String getFormattedText();
/*     */   
/*     */   List getSiblings();
/*     */   
/*     */   IChatComponent createCopy();
/*     */   
/*     */   public static class Serializer
/*     */     implements JsonDeserializer, JsonSerializer
/*     */   {
/*     */     private static final Gson GSON;
/*     */     private static final String __OBFID = "CL_00001263";
/*     */     
/*     */     public IChatComponent deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/*     */       Object var5;
/*  69 */       if (p_deserialize_1_.isJsonPrimitive())
/*     */       {
/*  71 */         return new ChatComponentText(p_deserialize_1_.getAsString());
/*     */       }
/*  73 */       if (!p_deserialize_1_.isJsonObject()) {
/*     */         
/*  75 */         if (p_deserialize_1_.isJsonArray()) {
/*     */           
/*  77 */           JsonArray var11 = p_deserialize_1_.getAsJsonArray();
/*  78 */           IChatComponent var12 = null;
/*  79 */           Iterator<JsonElement> var15 = var11.iterator();
/*     */           
/*  81 */           while (var15.hasNext()) {
/*     */             
/*  83 */             JsonElement var17 = var15.next();
/*  84 */             IChatComponent var18 = deserialize(var17, var17.getClass(), p_deserialize_3_);
/*     */             
/*  86 */             if (var12 == null) {
/*     */               
/*  88 */               var12 = var18;
/*     */               
/*     */               continue;
/*     */             } 
/*  92 */             var12.appendSibling(var18);
/*     */           } 
/*     */ 
/*     */           
/*  96 */           return var12;
/*     */         } 
/*     */ 
/*     */         
/* 100 */         throw new JsonParseException("Don't know how to turn " + p_deserialize_1_.toString() + " into a Component");
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 105 */       JsonObject var4 = p_deserialize_1_.getAsJsonObject();
/*     */ 
/*     */       
/* 108 */       if (var4.has("text")) {
/*     */         
/* 110 */         var5 = new ChatComponentText(var4.get("text").getAsString());
/*     */       }
/* 112 */       else if (var4.has("translate")) {
/*     */         
/* 114 */         String var6 = var4.get("translate").getAsString();
/*     */         
/* 116 */         if (var4.has("with"))
/*     */         {
/* 118 */           JsonArray var7 = var4.getAsJsonArray("with");
/* 119 */           Object[] var8 = new Object[var7.size()];
/*     */           
/* 121 */           for (int var9 = 0; var9 < var8.length; var9++) {
/*     */             
/* 123 */             var8[var9] = deserialize(var7.get(var9), p_deserialize_2_, p_deserialize_3_);
/*     */             
/* 125 */             if (var8[var9] instanceof ChatComponentText) {
/*     */               
/* 127 */               ChatComponentText var10 = (ChatComponentText)var8[var9];
/*     */               
/* 129 */               if (var10.getChatStyle().isEmpty() && var10.getSiblings().isEmpty())
/*     */               {
/* 131 */                 var8[var9] = var10.getChatComponentText_TextValue();
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 136 */           var5 = new ChatComponentTranslation(var6, var8);
/*     */         }
/*     */         else
/*     */         {
/* 140 */           var5 = new ChatComponentTranslation(var6, new Object[0]);
/*     */         }
/*     */       
/* 143 */       } else if (var4.has("score")) {
/*     */         
/* 145 */         JsonObject var13 = var4.getAsJsonObject("score");
/*     */         
/* 147 */         if (!var13.has("name") || !var13.has("objective"))
/*     */         {
/* 149 */           throw new JsonParseException("A score component needs a least a name and an objective");
/*     */         }
/*     */         
/* 152 */         var5 = new ChatComponentScore(JsonUtils.getJsonObjectStringFieldValue(var13, "name"), JsonUtils.getJsonObjectStringFieldValue(var13, "objective"));
/*     */         
/* 154 */         if (var13.has("value"))
/*     */         {
/* 156 */           ((ChatComponentScore)var5).func_179997_b(JsonUtils.getJsonObjectStringFieldValue(var13, "value"));
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 161 */         if (!var4.has("selector"))
/*     */         {
/* 163 */           throw new JsonParseException("Don't know how to turn " + p_deserialize_1_.toString() + " into a Component");
/*     */         }
/*     */         
/* 166 */         var5 = new ChatComponentSelector(JsonUtils.getJsonObjectStringFieldValue(var4, "selector"));
/*     */       } 
/*     */       
/* 169 */       if (var4.has("extra")) {
/*     */         
/* 171 */         JsonArray var14 = var4.getAsJsonArray("extra");
/*     */         
/* 173 */         if (var14.size() <= 0)
/*     */         {
/* 175 */           throw new JsonParseException("Unexpected empty array of components");
/*     */         }
/*     */         
/* 178 */         for (int var16 = 0; var16 < var14.size(); var16++)
/*     */         {
/* 180 */           ((IChatComponent)var5).appendSibling(deserialize(var14.get(var16), p_deserialize_2_, p_deserialize_3_));
/*     */         }
/*     */       } 
/*     */       
/* 184 */       ((IChatComponent)var5).setChatStyle((ChatStyle)p_deserialize_3_.deserialize(p_deserialize_1_, ChatStyle.class));
/* 185 */       return (IChatComponent)var5;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private void serializeChatStyle(ChatStyle style, JsonObject object, JsonSerializationContext ctx) {
/* 191 */       JsonElement var4 = ctx.serialize(style);
/*     */       
/* 193 */       if (var4.isJsonObject()) {
/*     */         
/* 195 */         JsonObject var5 = (JsonObject)var4;
/* 196 */         Iterator<Map.Entry> var6 = var5.entrySet().iterator();
/*     */         
/* 198 */         while (var6.hasNext()) {
/*     */           
/* 200 */           Map.Entry var7 = var6.next();
/* 201 */           object.add((String)var7.getKey(), (JsonElement)var7.getValue());
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement serialize(IChatComponent p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 208 */       if (p_serialize_1_ instanceof ChatComponentText && p_serialize_1_.getChatStyle().isEmpty() && p_serialize_1_.getSiblings().isEmpty())
/*     */       {
/* 210 */         return (JsonElement)new JsonPrimitive(((ChatComponentText)p_serialize_1_).getChatComponentText_TextValue());
/*     */       }
/*     */ 
/*     */       
/* 214 */       JsonObject var4 = new JsonObject();
/*     */       
/* 216 */       if (!p_serialize_1_.getChatStyle().isEmpty())
/*     */       {
/* 218 */         serializeChatStyle(p_serialize_1_.getChatStyle(), var4, p_serialize_3_);
/*     */       }
/*     */       
/* 221 */       if (!p_serialize_1_.getSiblings().isEmpty()) {
/*     */         
/* 223 */         JsonArray var5 = new JsonArray();
/* 224 */         Iterator<IChatComponent> var6 = p_serialize_1_.getSiblings().iterator();
/*     */         
/* 226 */         while (var6.hasNext()) {
/*     */           
/* 228 */           IChatComponent var7 = var6.next();
/* 229 */           var5.add(serialize(var7, var7.getClass(), p_serialize_3_));
/*     */         } 
/*     */         
/* 232 */         var4.add("extra", (JsonElement)var5);
/*     */       } 
/*     */       
/* 235 */       if (p_serialize_1_ instanceof ChatComponentText) {
/*     */         
/* 237 */         var4.addProperty("text", ((ChatComponentText)p_serialize_1_).getChatComponentText_TextValue());
/*     */       }
/* 239 */       else if (p_serialize_1_ instanceof ChatComponentTranslation) {
/*     */         
/* 241 */         ChatComponentTranslation var11 = (ChatComponentTranslation)p_serialize_1_;
/* 242 */         var4.addProperty("translate", var11.getKey());
/*     */         
/* 244 */         if (var11.getFormatArgs() != null && (var11.getFormatArgs()).length > 0)
/*     */         {
/* 246 */           JsonArray var14 = new JsonArray();
/* 247 */           Object[] var16 = var11.getFormatArgs();
/* 248 */           int var8 = var16.length;
/*     */           
/* 250 */           for (int var9 = 0; var9 < var8; var9++) {
/*     */             
/* 252 */             Object var10 = var16[var9];
/*     */             
/* 254 */             if (var10 instanceof IChatComponent) {
/*     */               
/* 256 */               var14.add(serialize((IChatComponent)var10, var10.getClass(), p_serialize_3_));
/*     */             }
/*     */             else {
/*     */               
/* 260 */               var14.add((JsonElement)new JsonPrimitive(String.valueOf(var10)));
/*     */             } 
/*     */           } 
/*     */           
/* 264 */           var4.add("with", (JsonElement)var14);
/*     */         }
/*     */       
/* 267 */       } else if (p_serialize_1_ instanceof ChatComponentScore) {
/*     */         
/* 269 */         ChatComponentScore var12 = (ChatComponentScore)p_serialize_1_;
/* 270 */         JsonObject var15 = new JsonObject();
/* 271 */         var15.addProperty("name", var12.func_179995_g());
/* 272 */         var15.addProperty("objective", var12.func_179994_h());
/* 273 */         var15.addProperty("value", var12.getUnformattedTextForChat());
/* 274 */         var4.add("score", (JsonElement)var15);
/*     */       }
/*     */       else {
/*     */         
/* 278 */         if (!(p_serialize_1_ instanceof ChatComponentSelector))
/*     */         {
/* 280 */           throw new IllegalArgumentException("Don't know how to serialize " + p_serialize_1_ + " as a Component");
/*     */         }
/*     */         
/* 283 */         ChatComponentSelector var13 = (ChatComponentSelector)p_serialize_1_;
/* 284 */         var4.addProperty("selector", var13.func_179992_g());
/*     */       } 
/*     */       
/* 287 */       return (JsonElement)var4;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static String componentToJson(IChatComponent component) {
/* 293 */       return GSON.toJson(component);
/*     */     }
/*     */ 
/*     */     
/*     */     public static IChatComponent jsonToComponent(String json) {
/* 298 */       return (IChatComponent)GSON.fromJson(json, IChatComponent.class);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 303 */       return serialize((IChatComponent)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 309 */       GsonBuilder var0 = new GsonBuilder();
/* 310 */       var0.registerTypeHierarchyAdapter(IChatComponent.class, new Serializer());
/* 311 */       var0.registerTypeHierarchyAdapter(ChatStyle.class, new ChatStyle.Serializer());
/* 312 */       var0.registerTypeAdapterFactory(new EnumTypeAdapterFactory());
/* 313 */       GSON = var0.create();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\IChatComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */