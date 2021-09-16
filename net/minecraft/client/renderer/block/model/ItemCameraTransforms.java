/*    */ package net.minecraft.client.renderer.block.model;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ public class ItemCameraTransforms
/*    */ {
/* 11 */   public static final ItemCameraTransforms field_178357_a = new ItemCameraTransforms(ItemTransformVec3f.field_178366_a, ItemTransformVec3f.field_178366_a, ItemTransformVec3f.field_178366_a, ItemTransformVec3f.field_178366_a);
/*    */   
/*    */   public final ItemTransformVec3f field_178355_b;
/*    */   public final ItemTransformVec3f field_178356_c;
/*    */   public final ItemTransformVec3f field_178353_d;
/*    */   public final ItemTransformVec3f field_178354_e;
/*    */   private static final String __OBFID = "CL_00002482";
/*    */   
/*    */   public ItemCameraTransforms(ItemTransformVec3f p_i46213_1_, ItemTransformVec3f p_i46213_2_, ItemTransformVec3f p_i46213_3_, ItemTransformVec3f p_i46213_4_) {
/* 20 */     this.field_178355_b = p_i46213_1_;
/* 21 */     this.field_178356_c = p_i46213_2_;
/* 22 */     this.field_178353_d = p_i46213_3_;
/* 23 */     this.field_178354_e = p_i46213_4_;
/*    */   }
/*    */   
/*    */   static class Deserializer
/*    */     implements JsonDeserializer
/*    */   {
/*    */     private static final String __OBFID = "CL_00002481";
/*    */     
/*    */     public ItemCameraTransforms func_178352_a(JsonElement p_178352_1_, Type p_178352_2_, JsonDeserializationContext p_178352_3_) {
/* 32 */       JsonObject var4 = p_178352_1_.getAsJsonObject();
/* 33 */       ItemTransformVec3f var5 = ItemTransformVec3f.field_178366_a;
/* 34 */       ItemTransformVec3f var6 = ItemTransformVec3f.field_178366_a;
/* 35 */       ItemTransformVec3f var7 = ItemTransformVec3f.field_178366_a;
/* 36 */       ItemTransformVec3f var8 = ItemTransformVec3f.field_178366_a;
/*    */       
/* 38 */       if (var4.has("thirdperson"))
/*    */       {
/* 40 */         var5 = (ItemTransformVec3f)p_178352_3_.deserialize(var4.get("thirdperson"), ItemTransformVec3f.class);
/*    */       }
/*    */       
/* 43 */       if (var4.has("firstperson"))
/*    */       {
/* 45 */         var6 = (ItemTransformVec3f)p_178352_3_.deserialize(var4.get("firstperson"), ItemTransformVec3f.class);
/*    */       }
/*    */       
/* 48 */       if (var4.has("head"))
/*    */       {
/* 50 */         var7 = (ItemTransformVec3f)p_178352_3_.deserialize(var4.get("head"), ItemTransformVec3f.class);
/*    */       }
/*    */       
/* 53 */       if (var4.has("gui"))
/*    */       {
/* 55 */         var8 = (ItemTransformVec3f)p_178352_3_.deserialize(var4.get("gui"), ItemTransformVec3f.class);
/*    */       }
/*    */       
/* 58 */       return new ItemCameraTransforms(var5, var6, var7, var8);
/*    */     }
/*    */ 
/*    */     
/*    */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 63 */       return func_178352_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*    */     }
/*    */   }
/*    */   
/*    */   public enum TransformType
/*    */   {
/* 69 */     NONE("NONE", 0),
/* 70 */     THIRD_PERSON("THIRD_PERSON", 1),
/* 71 */     FIRST_PERSON("FIRST_PERSON", 2),
/* 72 */     HEAD("HEAD", 3),
/* 73 */     GUI("GUI", 4);
/*    */     
/* 75 */     private static final TransformType[] $VALUES = new TransformType[] { NONE, THIRD_PERSON, FIRST_PERSON, HEAD, GUI };
/*    */     private static final String __OBFID = "CL_00002480";
/*    */     
/*    */     static {
/*    */     
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\ItemCameraTransforms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */