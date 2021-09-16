/*     */ package net.minecraft.client.renderer.block.model;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.io.Reader;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.resources.model.ModelRotation;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class ModelBlockDefinition
/*     */ {
/*  26 */   static final Gson field_178333_a = (new GsonBuilder()).registerTypeAdapter(ModelBlockDefinition.class, new Deserializer()).registerTypeAdapter(Variant.class, new Variant.Deserializer()).create();
/*  27 */   private final Map field_178332_b = Maps.newHashMap();
/*     */   
/*     */   private static final String __OBFID = "CL_00002498";
/*     */   
/*     */   public static ModelBlockDefinition func_178331_a(Reader p_178331_0_) {
/*  32 */     return (ModelBlockDefinition)field_178333_a.fromJson(p_178331_0_, ModelBlockDefinition.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBlockDefinition(Collection p_i46221_1_) {
/*  37 */     Iterator<Variants> var2 = p_i46221_1_.iterator();
/*     */     
/*  39 */     while (var2.hasNext()) {
/*     */       
/*  41 */       Variants var3 = var2.next();
/*  42 */       this.field_178332_b.put(var3.field_178423_a, var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ModelBlockDefinition(List p_i46222_1_) {
/*  48 */     Iterator<ModelBlockDefinition> var2 = p_i46222_1_.iterator();
/*     */     
/*  50 */     while (var2.hasNext()) {
/*     */       
/*  52 */       ModelBlockDefinition var3 = var2.next();
/*  53 */       this.field_178332_b.putAll(var3.field_178332_b);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Variants func_178330_b(String p_178330_1_) {
/*  59 */     Variants var2 = (Variants)this.field_178332_b.get(p_178330_1_);
/*     */     
/*  61 */     if (var2 == null)
/*     */     {
/*  63 */       throw new MissingVariantException();
/*     */     }
/*     */ 
/*     */     
/*  67 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object p_equals_1_) {
/*  73 */     if (this == p_equals_1_)
/*     */     {
/*  75 */       return true;
/*     */     }
/*  77 */     if (p_equals_1_ instanceof ModelBlockDefinition) {
/*     */       
/*  79 */       ModelBlockDefinition var2 = (ModelBlockDefinition)p_equals_1_;
/*  80 */       return this.field_178332_b.equals(var2.field_178332_b);
/*     */     } 
/*     */ 
/*     */     
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  90 */     return this.field_178332_b.hashCode();
/*     */   }
/*     */   
/*     */   public static class Deserializer
/*     */     implements JsonDeserializer
/*     */   {
/*     */     private static final String __OBFID = "CL_00002497";
/*     */     
/*     */     public ModelBlockDefinition func_178336_a(JsonElement p_178336_1_, Type p_178336_2_, JsonDeserializationContext p_178336_3_) {
/*  99 */       JsonObject var4 = p_178336_1_.getAsJsonObject();
/* 100 */       List var5 = func_178334_a(p_178336_3_, var4);
/* 101 */       return new ModelBlockDefinition(var5);
/*     */     }
/*     */ 
/*     */     
/*     */     protected List func_178334_a(JsonDeserializationContext p_178334_1_, JsonObject p_178334_2_) {
/* 106 */       JsonObject var3 = JsonUtils.getJsonObject(p_178334_2_, "variants");
/* 107 */       ArrayList<ModelBlockDefinition.Variants> var4 = Lists.newArrayList();
/* 108 */       Iterator<Map.Entry> var5 = var3.entrySet().iterator();
/*     */       
/* 110 */       while (var5.hasNext()) {
/*     */         
/* 112 */         Map.Entry var6 = var5.next();
/* 113 */         var4.add(func_178335_a(p_178334_1_, var6));
/*     */       } 
/*     */       
/* 116 */       return var4;
/*     */     }
/*     */ 
/*     */     
/*     */     protected ModelBlockDefinition.Variants func_178335_a(JsonDeserializationContext p_178335_1_, Map.Entry p_178335_2_) {
/* 121 */       String var3 = (String)p_178335_2_.getKey();
/* 122 */       ArrayList<ModelBlockDefinition.Variant> var4 = Lists.newArrayList();
/* 123 */       JsonElement var5 = (JsonElement)p_178335_2_.getValue();
/*     */       
/* 125 */       if (var5.isJsonArray()) {
/*     */         
/* 127 */         Iterator<JsonElement> var6 = var5.getAsJsonArray().iterator();
/*     */         
/* 129 */         while (var6.hasNext())
/*     */         {
/* 131 */           JsonElement var7 = var6.next();
/* 132 */           var4.add((ModelBlockDefinition.Variant)p_178335_1_.deserialize(var7, ModelBlockDefinition.Variant.class));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 137 */         var4.add((ModelBlockDefinition.Variant)p_178335_1_.deserialize(var5, ModelBlockDefinition.Variant.class));
/*     */       } 
/*     */       
/* 140 */       return new ModelBlockDefinition.Variants(var3, var4);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 145 */       return func_178336_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */     }
/*     */   }
/*     */   
/*     */   public class MissingVariantException
/*     */     extends RuntimeException
/*     */   {
/*     */     private static final String __OBFID = "CL_00002496";
/*     */   }
/*     */   
/*     */   public static class Variant
/*     */   {
/*     */     private final ResourceLocation field_178437_a;
/*     */     private final ModelRotation field_178435_b;
/*     */     private final boolean field_178436_c;
/*     */     private final int field_178434_d;
/*     */     private static final String __OBFID = "CL_00002495";
/*     */     
/*     */     public Variant(ResourceLocation p_i46219_1_, ModelRotation p_i46219_2_, boolean p_i46219_3_, int p_i46219_4_) {
/* 164 */       this.field_178437_a = p_i46219_1_;
/* 165 */       this.field_178435_b = p_i46219_2_;
/* 166 */       this.field_178436_c = p_i46219_3_;
/* 167 */       this.field_178434_d = p_i46219_4_;
/*     */     }
/*     */ 
/*     */     
/*     */     public ResourceLocation getModelLocation() {
/* 172 */       return this.field_178437_a;
/*     */     }
/*     */ 
/*     */     
/*     */     public ModelRotation getRotation() {
/* 177 */       return this.field_178435_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isUvLocked() {
/* 182 */       return this.field_178436_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getWeight() {
/* 187 */       return this.field_178434_d;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object p_equals_1_) {
/* 192 */       if (this == p_equals_1_)
/*     */       {
/* 194 */         return true;
/*     */       }
/* 196 */       if (!(p_equals_1_ instanceof Variant))
/*     */       {
/* 198 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 202 */       Variant var2 = (Variant)p_equals_1_;
/* 203 */       return (this.field_178437_a.equals(var2.field_178437_a) && this.field_178435_b == var2.field_178435_b && this.field_178436_c == var2.field_178436_c);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 209 */       int var1 = this.field_178437_a.hashCode();
/* 210 */       var1 = 31 * var1 + ((this.field_178435_b != null) ? this.field_178435_b.hashCode() : 0);
/* 211 */       var1 = 31 * var1 + (this.field_178436_c ? 1 : 0);
/* 212 */       return var1;
/*     */     }
/*     */     
/*     */     public static class Deserializer
/*     */       implements JsonDeserializer
/*     */     {
/*     */       private static final String __OBFID = "CL_00002494";
/*     */       
/*     */       public ModelBlockDefinition.Variant func_178425_a(JsonElement p_178425_1_, Type p_178425_2_, JsonDeserializationContext p_178425_3_) {
/* 221 */         JsonObject var4 = p_178425_1_.getAsJsonObject();
/* 222 */         String var5 = func_178424_b(var4);
/* 223 */         ModelRotation var6 = func_178428_a(var4);
/* 224 */         boolean var7 = func_178429_d(var4);
/* 225 */         int var8 = func_178427_c(var4);
/* 226 */         return new ModelBlockDefinition.Variant(func_178426_a(var5), var6, var7, var8);
/*     */       }
/*     */ 
/*     */       
/*     */       private ResourceLocation func_178426_a(String p_178426_1_) {
/* 231 */         ResourceLocation var2 = new ResourceLocation(p_178426_1_);
/* 232 */         var2 = new ResourceLocation(var2.getResourceDomain(), "block/" + var2.getResourcePath());
/* 233 */         return var2;
/*     */       }
/*     */ 
/*     */       
/*     */       private boolean func_178429_d(JsonObject p_178429_1_) {
/* 238 */         return JsonUtils.getJsonObjectBooleanFieldValueOrDefault(p_178429_1_, "uvlock", false);
/*     */       }
/*     */ 
/*     */       
/*     */       protected ModelRotation func_178428_a(JsonObject p_178428_1_) {
/* 243 */         int var2 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(p_178428_1_, "x", 0);
/* 244 */         int var3 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(p_178428_1_, "y", 0);
/* 245 */         ModelRotation var4 = ModelRotation.func_177524_a(var2, var3);
/*     */         
/* 247 */         if (var4 == null)
/*     */         {
/* 249 */           throw new JsonParseException("Invalid BlockModelRotation x: " + var2 + ", y: " + var3);
/*     */         }
/*     */ 
/*     */         
/* 253 */         return var4;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       protected String func_178424_b(JsonObject p_178424_1_) {
/* 259 */         return JsonUtils.getJsonObjectStringFieldValue(p_178424_1_, "model");
/*     */       }
/*     */ 
/*     */       
/*     */       protected int func_178427_c(JsonObject p_178427_1_) {
/* 264 */         return JsonUtils.getJsonObjectIntegerFieldValueOrDefault(p_178427_1_, "weight", 1);
/*     */       }
/*     */       
/*     */       public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_)
/*     */       {
/* 269 */         return func_178425_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_); } } } public static class Deserializer implements JsonDeserializer { private static final String __OBFID = "CL_00002494"; public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) { return func_178425_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_); }
/*     */     public ModelBlockDefinition.Variant func_178425_a(JsonElement p_178425_1_, Type p_178425_2_, JsonDeserializationContext p_178425_3_) { JsonObject var4 = p_178425_1_.getAsJsonObject(); String var5 = func_178424_b(var4); ModelRotation var6 = func_178428_a(var4); boolean var7 = func_178429_d(var4); int var8 = func_178427_c(var4); return new ModelBlockDefinition.Variant(func_178426_a(var5), var6, var7, var8); }
/*     */     private ResourceLocation func_178426_a(String p_178426_1_) { ResourceLocation var2 = new ResourceLocation(p_178426_1_);
/*     */       var2 = new ResourceLocation(var2.getResourceDomain(), "block/" + var2.getResourcePath());
/*     */       return var2; }
/*     */     private boolean func_178429_d(JsonObject p_178429_1_) { return JsonUtils.getJsonObjectBooleanFieldValueOrDefault(p_178429_1_, "uvlock", false); } protected ModelRotation func_178428_a(JsonObject p_178428_1_) { int var2 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(p_178428_1_, "x", 0);
/*     */       int var3 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(p_178428_1_, "y", 0);
/*     */       ModelRotation var4 = ModelRotation.func_177524_a(var2, var3);
/*     */       if (var4 == null)
/*     */         throw new JsonParseException("Invalid BlockModelRotation x: " + var2 + ", y: " + var3); 
/*     */       return var4; } protected String func_178424_b(JsonObject p_178424_1_) { return JsonUtils.getJsonObjectStringFieldValue(p_178424_1_, "model"); } protected int func_178427_c(JsonObject p_178427_1_) { return JsonUtils.getJsonObjectIntegerFieldValueOrDefault(p_178427_1_, "weight", 1); } }
/*     */    public static class Variants
/*     */   {
/* 282 */     private final String field_178423_a; private final List field_178422_b; private static final String __OBFID = "CL_00002493"; public Variants(String p_i46218_1_, List p_i46218_2_) { this.field_178423_a = p_i46218_1_;
/* 283 */       this.field_178422_b = p_i46218_2_; }
/*     */ 
/*     */ 
/*     */     
/*     */     public List getVariants() {
/* 288 */       return this.field_178422_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object p_equals_1_) {
/* 293 */       if (this == p_equals_1_)
/*     */       {
/* 295 */         return true;
/*     */       }
/* 297 */       if (!(p_equals_1_ instanceof Variants))
/*     */       {
/* 299 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 303 */       Variants var2 = (Variants)p_equals_1_;
/* 304 */       return !this.field_178423_a.equals(var2.field_178423_a) ? false : this.field_178422_b.equals(var2.field_178422_b);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 310 */       int var1 = this.field_178423_a.hashCode();
/* 311 */       var1 = 31 * var1 + this.field_178422_b.hashCode();
/* 312 */       return var1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\ModelBlockDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */