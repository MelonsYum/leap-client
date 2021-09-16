/*    */ package net.minecraft.client.renderer.block.model;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.lang.reflect.Type;
/*    */ import javax.vecmath.Vector3f;
/*    */ import net.minecraft.util.JsonUtils;
/*    */ import net.minecraft.util.MathHelper;
/*    */ 
/*    */ public class ItemTransformVec3f
/*    */ {
/* 16 */   public static final ItemTransformVec3f field_178366_a = new ItemTransformVec3f(new Vector3f(), new Vector3f(), new Vector3f(1.0F, 1.0F, 1.0F));
/*    */   
/*    */   public final Vector3f field_178364_b;
/*    */   public final Vector3f field_178365_c;
/*    */   public final Vector3f field_178363_d;
/*    */   private static final String __OBFID = "CL_00002484";
/*    */   
/*    */   public ItemTransformVec3f(Vector3f p_i46214_1_, Vector3f p_i46214_2_, Vector3f p_i46214_3_) {
/* 24 */     this.field_178364_b = new Vector3f(p_i46214_1_);
/* 25 */     this.field_178365_c = new Vector3f(p_i46214_2_);
/* 26 */     this.field_178363_d = new Vector3f(p_i46214_3_);
/*    */   }
/*    */   
/*    */   static class Deserializer
/*    */     implements JsonDeserializer {
/* 31 */     private static final Vector3f field_178362_a = new Vector3f(0.0F, 0.0F, 0.0F);
/* 32 */     private static final Vector3f field_178360_b = new Vector3f(0.0F, 0.0F, 0.0F);
/* 33 */     private static final Vector3f field_178361_c = new Vector3f(1.0F, 1.0F, 1.0F);
/*    */     
/*    */     private static final String __OBFID = "CL_00002483";
/*    */     
/*    */     public ItemTransformVec3f func_178359_a(JsonElement p_178359_1_, Type p_178359_2_, JsonDeserializationContext p_178359_3_) {
/* 38 */       JsonObject var4 = p_178359_1_.getAsJsonObject();
/* 39 */       Vector3f var5 = func_178358_a(var4, "rotation", field_178362_a);
/* 40 */       Vector3f var6 = func_178358_a(var4, "translation", field_178360_b);
/* 41 */       var6.scale(0.0625F);
/* 42 */       MathHelper.clamp_double(var6.x, -1.5D, 1.5D);
/* 43 */       MathHelper.clamp_double(var6.y, -1.5D, 1.5D);
/* 44 */       MathHelper.clamp_double(var6.z, -1.5D, 1.5D);
/* 45 */       Vector3f var7 = func_178358_a(var4, "scale", field_178361_c);
/* 46 */       MathHelper.clamp_double(var7.x, -1.5D, 1.5D);
/* 47 */       MathHelper.clamp_double(var7.y, -1.5D, 1.5D);
/* 48 */       MathHelper.clamp_double(var7.z, -1.5D, 1.5D);
/* 49 */       return new ItemTransformVec3f(var5, var6, var7);
/*    */     }
/*    */ 
/*    */     
/*    */     private Vector3f func_178358_a(JsonObject p_178358_1_, String p_178358_2_, Vector3f p_178358_3_) {
/* 54 */       if (!p_178358_1_.has(p_178358_2_))
/*    */       {
/* 56 */         return p_178358_3_;
/*    */       }
/*    */ 
/*    */       
/* 60 */       JsonArray var4 = JsonUtils.getJsonObjectJsonArrayField(p_178358_1_, p_178358_2_);
/*    */       
/* 62 */       if (var4.size() != 3)
/*    */       {
/* 64 */         throw new JsonParseException("Expected 3 " + p_178358_2_ + " values, found: " + var4.size());
/*    */       }
/*    */ 
/*    */       
/* 68 */       float[] var5 = new float[3];
/*    */       
/* 70 */       for (int var6 = 0; var6 < var5.length; var6++)
/*    */       {
/* 72 */         var5[var6] = JsonUtils.getJsonElementFloatValue(var4.get(var6), String.valueOf(p_178358_2_) + "[" + var6 + "]");
/*    */       }
/*    */       
/* 75 */       return new Vector3f(var5);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 82 */       return func_178359_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\ItemTransformVec3f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */