/*    */ package net.minecraft.client.resources.data;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.util.JsonUtils;
/*    */ 
/*    */ public class TextureMetadataSectionSerializer
/*    */   extends BaseMetadataSectionSerializer
/*    */ {
/*    */   private static final String __OBFID = "CL_00001115";
/*    */   
/*    */   public TextureMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 19 */     JsonObject var4 = p_deserialize_1_.getAsJsonObject();
/* 20 */     boolean var5 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "blur", false);
/* 21 */     boolean var6 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "clamp", false);
/* 22 */     ArrayList<Integer> var7 = Lists.newArrayList();
/*    */     
/* 24 */     if (var4.has("mipmaps")) {
/*    */       
/*    */       try {
/*    */         
/* 28 */         JsonArray var8 = var4.getAsJsonArray("mipmaps");
/*    */         
/* 30 */         for (int var9 = 0; var9 < var8.size(); var9++) {
/*    */           
/* 32 */           JsonElement var10 = var8.get(var9);
/*    */           
/* 34 */           if (var10.isJsonPrimitive()) {
/*    */             
/*    */             try
/*    */             {
/* 38 */               var7.add(Integer.valueOf(var10.getAsInt()));
/*    */             }
/* 40 */             catch (NumberFormatException var12)
/*    */             {
/* 42 */               throw new JsonParseException("Invalid texture->mipmap->" + var9 + ": expected number, was " + var10, var12);
/*    */             }
/*    */           
/* 45 */           } else if (var10.isJsonObject()) {
/*    */             
/* 47 */             throw new JsonParseException("Invalid texture->mipmap->" + var9 + ": expected number, was " + var10);
/*    */           }
/*    */         
/*    */         } 
/* 51 */       } catch (ClassCastException var13) {
/*    */         
/* 53 */         throw new JsonParseException("Invalid texture->mipmaps: expected array, was " + var4.get("mipmaps"), var13);
/*    */       } 
/*    */     }
/*    */     
/* 57 */     return new TextureMetadataSection(var5, var6, var7);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 65 */     return "texture";
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\TextureMetadataSectionSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */