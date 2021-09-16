/*    */ package net.minecraft.client.renderer.block.model;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.lang.reflect.Type;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.JsonUtils;
/*    */ 
/*    */ public class BlockPartFace
/*    */ {
/* 13 */   public static final EnumFacing field_178246_a = null;
/*    */   
/*    */   public final EnumFacing field_178244_b;
/*    */   public final int field_178245_c;
/*    */   public final String field_178242_d;
/*    */   public final BlockFaceUV field_178243_e;
/*    */   private static final String __OBFID = "CL_00002508";
/*    */   
/*    */   public BlockPartFace(EnumFacing p_i46230_1_, int p_i46230_2_, String p_i46230_3_, BlockFaceUV p_i46230_4_) {
/* 22 */     this.field_178244_b = p_i46230_1_;
/* 23 */     this.field_178245_c = p_i46230_2_;
/* 24 */     this.field_178242_d = p_i46230_3_;
/* 25 */     this.field_178243_e = p_i46230_4_;
/*    */   }
/*    */   
/*    */   static class Deserializer
/*    */     implements JsonDeserializer
/*    */   {
/*    */     private static final String __OBFID = "CL_00002507";
/*    */     
/*    */     public BlockPartFace func_178338_a(JsonElement p_178338_1_, Type p_178338_2_, JsonDeserializationContext p_178338_3_) {
/* 34 */       JsonObject var4 = p_178338_1_.getAsJsonObject();
/* 35 */       EnumFacing var5 = func_178339_c(var4);
/* 36 */       int var6 = func_178337_a(var4);
/* 37 */       String var7 = func_178340_b(var4);
/* 38 */       BlockFaceUV var8 = (BlockFaceUV)p_178338_3_.deserialize((JsonElement)var4, BlockFaceUV.class);
/* 39 */       return new BlockPartFace(var5, var6, var7, var8);
/*    */     }
/*    */ 
/*    */     
/*    */     protected int func_178337_a(JsonObject p_178337_1_) {
/* 44 */       return JsonUtils.getJsonObjectIntegerFieldValueOrDefault(p_178337_1_, "tintindex", -1);
/*    */     }
/*    */ 
/*    */     
/*    */     private String func_178340_b(JsonObject p_178340_1_) {
/* 49 */       return JsonUtils.getJsonObjectStringFieldValue(p_178340_1_, "texture");
/*    */     }
/*    */ 
/*    */     
/*    */     private EnumFacing func_178339_c(JsonObject p_178339_1_) {
/* 54 */       String var2 = JsonUtils.getJsonObjectStringFieldValueOrDefault(p_178339_1_, "cullface", "");
/* 55 */       return EnumFacing.byName(var2);
/*    */     }
/*    */ 
/*    */     
/*    */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 60 */       return func_178338_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\BlockPartFace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */