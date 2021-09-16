/*    */ package net.minecraft.client.resources.data;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.client.resources.Language;
/*    */ import net.minecraft.util.JsonUtils;
/*    */ 
/*    */ public class LanguageMetadataSectionSerializer extends BaseMetadataSectionSerializer {
/*    */   private static final String __OBFID = "CL_00001111";
/*    */   
/*    */   public LanguageMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/*    */     String var8, var10, var11;
/*    */     boolean var12;
/* 21 */     JsonObject var4 = p_deserialize_1_.getAsJsonObject();
/* 22 */     HashSet<Language> var5 = Sets.newHashSet();
/* 23 */     Iterator<Map.Entry> var6 = var4.entrySet().iterator();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     do {
/* 31 */       if (!var6.hasNext())
/*    */       {
/* 33 */         return new LanguageMetadataSection(var5);
/*    */       }
/*    */       
/* 36 */       Map.Entry var7 = var6.next();
/* 37 */       var8 = (String)var7.getKey();
/* 38 */       JsonObject var9 = JsonUtils.getElementAsJsonObject((JsonElement)var7.getValue(), "language");
/* 39 */       var10 = JsonUtils.getJsonObjectStringFieldValue(var9, "region");
/* 40 */       var11 = JsonUtils.getJsonObjectStringFieldValue(var9, "name");
/* 41 */       var12 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var9, "bidirectional", false);
/*    */       
/* 43 */       if (var10.isEmpty())
/*    */       {
/* 45 */         throw new JsonParseException("Invalid language->'" + var8 + "'->region: empty value");
/*    */       }
/*    */       
/* 48 */       if (var11.isEmpty())
/*    */       {
/* 50 */         throw new JsonParseException("Invalid language->'" + var8 + "'->name: empty value");
/*    */       }
/*    */     }
/* 53 */     while (var5.add(new Language(var8, var10, var11, var12)));
/*    */     
/* 55 */     throw new JsonParseException("Duplicate language->'" + var8 + "' defined");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 63 */     return "language";
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\LanguageMetadataSectionSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */