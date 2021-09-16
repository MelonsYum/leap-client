/*     */ package net.minecraft.client.renderer.block.model;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javax.vecmath.Vector3f;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPart
/*     */ {
/*     */   public final Vector3f field_178241_a;
/*     */   public final Vector3f field_178239_b;
/*     */   public final Map field_178240_c;
/*     */   public final BlockPartRotation field_178237_d;
/*     */   public final boolean field_178238_e;
/*     */   private static final String __OBFID = "CL_00002511";
/*     */   
/*     */   public BlockPart(Vector3f p_i46231_1_, Vector3f p_i46231_2_, Map p_i46231_3_, BlockPartRotation p_i46231_4_, boolean p_i46231_5_) {
/*  31 */     this.field_178241_a = p_i46231_1_;
/*  32 */     this.field_178239_b = p_i46231_2_;
/*  33 */     this.field_178240_c = p_i46231_3_;
/*  34 */     this.field_178237_d = p_i46231_4_;
/*  35 */     this.field_178238_e = p_i46231_5_;
/*  36 */     func_178235_a();
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_178235_a() {
/*  41 */     Iterator<Map.Entry> var1 = this.field_178240_c.entrySet().iterator();
/*     */     
/*  43 */     while (var1.hasNext()) {
/*     */       
/*  45 */       Map.Entry var2 = var1.next();
/*  46 */       float[] var3 = func_178236_a((EnumFacing)var2.getKey());
/*  47 */       ((BlockPartFace)var2.getValue()).field_178243_e.func_178349_a(var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] func_178236_a(EnumFacing p_178236_1_) {
/*     */     float[] var2;
/*  55 */     switch (SwitchEnumFacing.field_178234_a[p_178236_1_.ordinal()]) {
/*     */       
/*     */       case 1:
/*     */       case 2:
/*  59 */         var2 = new float[] { this.field_178241_a.x, this.field_178241_a.z, this.field_178239_b.x, this.field_178239_b.z };
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
/*  73 */         return var2;case 3: case 4: var2 = new float[] { this.field_178241_a.x, 16.0F - this.field_178239_b.y, this.field_178239_b.x, 16.0F - this.field_178241_a.y }; return var2;case 5: case 6: var2 = new float[] { this.field_178241_a.z, 16.0F - this.field_178239_b.y, this.field_178239_b.z, 16.0F - this.field_178241_a.y }; return var2;
/*     */     } 
/*     */     throw new NullPointerException();
/*     */   }
/*     */   
/*     */   static class Deserializer implements JsonDeserializer {
/*     */     private static final String __OBFID = "CL_00002509";
/*     */     
/*     */     public BlockPart func_178254_a(JsonElement p_178254_1_, Type p_178254_2_, JsonDeserializationContext p_178254_3_) {
/*  82 */       JsonObject var4 = p_178254_1_.getAsJsonObject();
/*  83 */       Vector3f var5 = func_178249_e(var4);
/*  84 */       Vector3f var6 = func_178247_d(var4);
/*  85 */       BlockPartRotation var7 = func_178256_a(var4);
/*  86 */       Map var8 = func_178250_a(p_178254_3_, var4);
/*     */       
/*  88 */       if (var4.has("shade") && !JsonUtils.func_180199_c(var4, "shade"))
/*     */       {
/*  90 */         throw new JsonParseException("Expected shade to be a Boolean");
/*     */       }
/*     */ 
/*     */       
/*  94 */       boolean var9 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var4, "shade", true);
/*  95 */       return new BlockPart(var5, var6, var8, var7, var9);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private BlockPartRotation func_178256_a(JsonObject p_178256_1_) {
/* 101 */       BlockPartRotation var2 = null;
/*     */       
/* 103 */       if (p_178256_1_.has("rotation")) {
/*     */         
/* 105 */         JsonObject var3 = JsonUtils.getJsonObject(p_178256_1_, "rotation");
/* 106 */         Vector3f var4 = func_178251_a(var3, "origin");
/* 107 */         var4.scale(0.0625F);
/* 108 */         EnumFacing.Axis var5 = func_178252_c(var3);
/* 109 */         float var6 = func_178255_b(var3);
/* 110 */         boolean var7 = JsonUtils.getJsonObjectBooleanFieldValueOrDefault(var3, "rescale", false);
/* 111 */         var2 = new BlockPartRotation(var4, var5, var6, var7);
/*     */       } 
/*     */       
/* 114 */       return var2;
/*     */     }
/*     */ 
/*     */     
/*     */     private float func_178255_b(JsonObject p_178255_1_) {
/* 119 */       float var2 = JsonUtils.getJsonObjectFloatFieldValue(p_178255_1_, "angle");
/*     */       
/* 121 */       if (var2 != 0.0F && MathHelper.abs(var2) != 22.5F && MathHelper.abs(var2) != 45.0F)
/*     */       {
/* 123 */         throw new JsonParseException("Invalid rotation " + var2 + " found, only -45/-22.5/0/22.5/45 allowed");
/*     */       }
/*     */ 
/*     */       
/* 127 */       return var2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private EnumFacing.Axis func_178252_c(JsonObject p_178252_1_) {
/* 133 */       String var2 = JsonUtils.getJsonObjectStringFieldValue(p_178252_1_, "axis");
/* 134 */       EnumFacing.Axis var3 = EnumFacing.Axis.byName(var2.toLowerCase());
/*     */       
/* 136 */       if (var3 == null)
/*     */       {
/* 138 */         throw new JsonParseException("Invalid rotation axis: " + var2);
/*     */       }
/*     */ 
/*     */       
/* 142 */       return var3;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Map func_178250_a(JsonDeserializationContext p_178250_1_, JsonObject p_178250_2_) {
/* 148 */       Map var3 = func_178253_b(p_178250_1_, p_178250_2_);
/*     */       
/* 150 */       if (var3.isEmpty())
/*     */       {
/* 152 */         throw new JsonParseException("Expected between 1 and 6 unique faces, got 0");
/*     */       }
/*     */ 
/*     */       
/* 156 */       return var3;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Map func_178253_b(JsonDeserializationContext p_178253_1_, JsonObject p_178253_2_) {
/* 162 */       EnumMap<EnumFacing, BlockPartFace> var3 = Maps.newEnumMap(EnumFacing.class);
/* 163 */       JsonObject var4 = JsonUtils.getJsonObject(p_178253_2_, "faces");
/* 164 */       Iterator<Map.Entry> var5 = var4.entrySet().iterator();
/*     */       
/* 166 */       while (var5.hasNext()) {
/*     */         
/* 168 */         Map.Entry var6 = var5.next();
/* 169 */         EnumFacing var7 = func_178248_a((String)var6.getKey());
/* 170 */         var3.put(var7, (BlockPartFace)p_178253_1_.deserialize((JsonElement)var6.getValue(), BlockPartFace.class));
/*     */       } 
/*     */       
/* 173 */       return var3;
/*     */     }
/*     */ 
/*     */     
/*     */     private EnumFacing func_178248_a(String p_178248_1_) {
/* 178 */       EnumFacing var2 = EnumFacing.byName(p_178248_1_);
/*     */       
/* 180 */       if (var2 == null)
/*     */       {
/* 182 */         throw new JsonParseException("Unknown facing: " + p_178248_1_);
/*     */       }
/*     */ 
/*     */       
/* 186 */       return var2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Vector3f func_178247_d(JsonObject p_178247_1_) {
/* 192 */       Vector3f var2 = func_178251_a(p_178247_1_, "to");
/*     */       
/* 194 */       if (var2.x >= -16.0F && var2.y >= -16.0F && var2.z >= -16.0F && var2.x <= 32.0F && var2.y <= 32.0F && var2.z <= 32.0F)
/*     */       {
/* 196 */         return var2;
/*     */       }
/*     */ 
/*     */       
/* 200 */       throw new JsonParseException("'to' specifier exceeds the allowed boundaries: " + var2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Vector3f func_178249_e(JsonObject p_178249_1_) {
/* 206 */       Vector3f var2 = func_178251_a(p_178249_1_, "from");
/*     */       
/* 208 */       if (var2.x >= -16.0F && var2.y >= -16.0F && var2.z >= -16.0F && var2.x <= 32.0F && var2.y <= 32.0F && var2.z <= 32.0F)
/*     */       {
/* 210 */         return var2;
/*     */       }
/*     */ 
/*     */       
/* 214 */       throw new JsonParseException("'from' specifier exceeds the allowed boundaries: " + var2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private Vector3f func_178251_a(JsonObject p_178251_1_, String p_178251_2_) {
/* 220 */       JsonArray var3 = JsonUtils.getJsonObjectJsonArrayField(p_178251_1_, p_178251_2_);
/*     */       
/* 222 */       if (var3.size() != 3)
/*     */       {
/* 224 */         throw new JsonParseException("Expected 3 " + p_178251_2_ + " values, found: " + var3.size());
/*     */       }
/*     */ 
/*     */       
/* 228 */       float[] var4 = new float[3];
/*     */       
/* 230 */       for (int var5 = 0; var5 < var4.length; var5++)
/*     */       {
/* 232 */         var4[var5] = JsonUtils.getJsonElementFloatValue(var3.get(var5), String.valueOf(p_178251_2_) + "[" + var5 + "]");
/*     */       }
/*     */       
/* 235 */       return new Vector3f(var4);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 241 */       return func_178254_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchEnumFacing
/*     */   {
/* 247 */     static final int[] field_178234_a = new int[(EnumFacing.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002510";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 254 */         field_178234_a[EnumFacing.DOWN.ordinal()] = 1;
/*     */       }
/* 256 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 263 */         field_178234_a[EnumFacing.UP.ordinal()] = 2;
/*     */       }
/* 265 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 272 */         field_178234_a[EnumFacing.NORTH.ordinal()] = 3;
/*     */       }
/* 274 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 281 */         field_178234_a[EnumFacing.SOUTH.ordinal()] = 4;
/*     */       }
/* 283 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 290 */         field_178234_a[EnumFacing.WEST.ordinal()] = 5;
/*     */       }
/* 292 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 299 */         field_178234_a[EnumFacing.EAST.ordinal()] = 6;
/*     */       }
/* 301 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\BlockPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */