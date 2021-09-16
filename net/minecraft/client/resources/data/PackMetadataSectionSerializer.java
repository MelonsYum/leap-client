/*    */ package net.minecraft.client.resources.data;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.util.JsonUtils;
/*    */ 
/*    */ public class PackMetadataSectionSerializer
/*    */   extends BaseMetadataSectionSerializer
/*    */   implements JsonSerializer {
/*    */   private static final String __OBFID = "CL_00001113";
/*    */   
/*    */   public PackMetadataSection deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 19 */     JsonObject var4 = p_deserialize_1_.getAsJsonObject();
/* 20 */     IChatComponent var5 = (IChatComponent)p_deserialize_3_.deserialize(var4.get("description"), IChatComponent.class);
/*    */     
/* 22 */     if (var5 == null)
/*    */     {
/* 24 */       throw new JsonParseException("Invalid/missing description!");
/*    */     }
/*    */ 
/*    */     
/* 28 */     int var6 = JsonUtils.getJsonObjectIntegerFieldValue(var4, "pack_format");
/* 29 */     return new PackMetadataSection(var5, var6);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(PackMetadataSection p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 35 */     JsonObject var4 = new JsonObject();
/* 36 */     var4.addProperty("pack_format", Integer.valueOf(p_serialize_1_.getPackFormat()));
/* 37 */     var4.add("description", p_serialize_3_.serialize(p_serialize_1_.func_152805_a()));
/* 38 */     return (JsonElement)var4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getSectionName() {
/* 46 */     return "pack";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 52 */     return serialize((PackMetadataSection)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\data\PackMetadataSectionSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */