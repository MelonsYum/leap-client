/*    */ package net.minecraft.client.audio;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.util.JsonUtils;
/*    */ import org.apache.commons.lang3.Validate;
/*    */ 
/*    */ public class SoundListSerializer
/*    */   implements JsonDeserializer
/*    */ {
/*    */   private static final String __OBFID = "CL_00001124";
/*    */   
/*    */   public SoundList deserialize1(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 18 */     JsonObject var4 = JsonUtils.getElementAsJsonObject(p_deserialize_1_, "entry");
/* 19 */     SoundList var5 = new SoundList();
/* 20 */     var5.setReplaceExisting(JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "replace", false));
/* 21 */     SoundCategory var6 = SoundCategory.func_147154_a(JsonUtils.getJsonObjectStringFieldValueOrDefault(var4, "category", SoundCategory.MASTER.getCategoryName()));
/* 22 */     var5.setSoundCategory(var6);
/* 23 */     Validate.notNull(var6, "Invalid category", new Object[0]);
/*    */     
/* 25 */     if (var4.has("sounds")) {
/*    */       
/* 27 */       JsonArray var7 = JsonUtils.getJsonObjectJsonArrayField(var4, "sounds");
/*    */       
/* 29 */       for (int var8 = 0; var8 < var7.size(); var8++) {
/*    */         
/* 31 */         JsonElement var9 = var7.get(var8);
/* 32 */         SoundList.SoundEntry var10 = new SoundList.SoundEntry();
/*    */         
/* 34 */         if (JsonUtils.jsonElementTypeIsString(var9)) {
/*    */           
/* 36 */           var10.setSoundEntryName(JsonUtils.getJsonElementStringValue(var9, "sound"));
/*    */         }
/*    */         else {
/*    */           
/* 40 */           JsonObject var11 = JsonUtils.getElementAsJsonObject(var9, "sound");
/* 41 */           var10.setSoundEntryName(JsonUtils.getJsonObjectStringFieldValue(var11, "name"));
/*    */           
/* 43 */           if (var11.has("type")) {
/*    */             
/* 45 */             SoundList.SoundEntry.Type var12 = SoundList.SoundEntry.Type.getType(JsonUtils.getJsonObjectStringFieldValue(var11, "type"));
/* 46 */             Validate.notNull(var12, "Invalid type", new Object[0]);
/* 47 */             var10.setSoundEntryType(var12);
/*    */           } 
/*    */ 
/*    */ 
/*    */           
/* 52 */           if (var11.has("volume")) {
/*    */             
/* 54 */             float var13 = JsonUtils.getJsonObjectFloatFieldValue(var11, "volume");
/* 55 */             Validate.isTrue((var13 > 0.0F), "Invalid volume", new Object[0]);
/* 56 */             var10.setSoundEntryVolume(var13);
/*    */           } 
/*    */           
/* 59 */           if (var11.has("pitch")) {
/*    */             
/* 61 */             float var13 = JsonUtils.getJsonObjectFloatFieldValue(var11, "pitch");
/* 62 */             Validate.isTrue((var13 > 0.0F), "Invalid pitch", new Object[0]);
/* 63 */             var10.setSoundEntryPitch(var13);
/*    */           } 
/*    */           
/* 66 */           if (var11.has("weight")) {
/*    */             
/* 68 */             int var14 = JsonUtils.getJsonObjectIntegerFieldValue(var11, "weight");
/* 69 */             Validate.isTrue((var14 > 0), "Invalid weight", new Object[0]);
/* 70 */             var10.setSoundEntryWeight(var14);
/*    */           } 
/*    */           
/* 73 */           if (var11.has("stream"))
/*    */           {
/* 75 */             var10.setStreaming(JsonUtils.getJsonObjectBooleanFieldValue(var11, "stream"));
/*    */           }
/*    */         } 
/*    */         
/* 79 */         var5.getSoundList().add(var10);
/*    */       } 
/*    */     } 
/*    */     
/* 83 */     return var5;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 88 */     return deserialize1(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\audio\SoundListSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */