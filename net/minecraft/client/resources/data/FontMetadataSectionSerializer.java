/*    */ package net.minecraft.client.resources.data;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.util.JsonUtils;
/*    */ import org.apache.commons.lang3.Validate;
/*    */ 
/*    */ public class FontMetadataSectionSerializer
/*    */   extends BaseMetadataSectionSerializer
/*    */ {
/*    */   private static final String __OBFID = "CL_00001109";
/*    */   
/*    */   public FontMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 17 */     JsonObject var4 = p_deserialize_1_.getAsJsonObject();
/* 18 */     float[] var5 = new float[256];
/* 19 */     float[] var6 = new float[256];
/* 20 */     float[] var7 = new float[256];
/* 21 */     float var8 = 1.0F;
/* 22 */     float var9 = 0.0F;
/* 23 */     float var10 = 0.0F;
/*    */     
/* 25 */     if (var4.has("characters")) {
/*    */       
/* 27 */       if (!var4.get("characters").isJsonObject())
/*    */       {
/* 29 */         throw new JsonParseException("Invalid font->characters: expected object, was " + var4.get("characters"));
/*    */       }
/*    */       
/* 32 */       JsonObject var11 = var4.getAsJsonObject("characters");
/*    */       
/* 34 */       if (var11.has("default")) {
/*    */         
/* 36 */         if (!var11.get("default").isJsonObject())
/*    */         {
/* 38 */           throw new JsonParseException("Invalid font->characters->default: expected object, was " + var11.get("default"));
/*    */         }
/*    */         
/* 41 */         JsonObject var12 = var11.getAsJsonObject("default");
/* 42 */         var8 = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var12, "width", var8);
/* 43 */         Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, var8, "Invalid default width");
/* 44 */         var9 = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var12, "spacing", var9);
/* 45 */         Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, var9, "Invalid default spacing");
/* 46 */         var10 = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var12, "left", var9);
/* 47 */         Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, var10, "Invalid default left");
/*    */       } 
/*    */       
/* 50 */       for (int var18 = 0; var18 < 256; var18++) {
/*    */         
/* 52 */         JsonElement var13 = var11.get(Integer.toString(var18));
/* 53 */         float var14 = var8;
/* 54 */         float var15 = var9;
/* 55 */         float var16 = var10;
/*    */         
/* 57 */         if (var13 != null) {
/*    */           
/* 59 */           JsonObject var17 = JsonUtils.getElementAsJsonObject(var13, "characters[" + var18 + "]");
/* 60 */           var14 = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var17, "width", var8);
/* 61 */           Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, var14, "Invalid width");
/* 62 */           var15 = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var17, "spacing", var9);
/* 63 */           Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, var15, "Invalid spacing");
/* 64 */           var16 = JsonUtils.getJsonObjectFloatFieldValueOrDefault(var17, "left", var10);
/* 65 */           Validate.inclusiveBetween(0.0D, 3.4028234663852886E38D, var16, "Invalid left");
/*    */         } 
/*    */         
/* 68 */         var5[var18] = var14;
/* 69 */         var6[var18] = var15;
/* 70 */         var7[var18] = var16;
/*    */       } 
/*    */     } 
/*    */     
/* 74 */     return new FontMetadataSection(var5, var7, var6);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 82 */     return "font";
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\FontMetadataSectionSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */