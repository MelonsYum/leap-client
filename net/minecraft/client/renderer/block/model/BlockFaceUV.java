/*     */ package net.minecraft.client.renderer.block.model;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import java.lang.reflect.Type;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ 
/*     */ 
/*     */ public class BlockFaceUV
/*     */ {
/*     */   public float[] field_178351_a;
/*     */   public final int field_178350_b;
/*     */   private static final String __OBFID = "CL_00002505";
/*     */   
/*     */   public BlockFaceUV(float[] p_i46228_1_, int p_i46228_2_) {
/*  20 */     this.field_178351_a = p_i46228_1_;
/*  21 */     this.field_178350_b = p_i46228_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_178348_a(int p_178348_1_) {
/*  26 */     if (this.field_178351_a == null)
/*     */     {
/*  28 */       throw new NullPointerException("uvs");
/*     */     }
/*     */ 
/*     */     
/*  32 */     int var2 = func_178347_d(p_178348_1_);
/*  33 */     return (var2 != 0 && var2 != 1) ? this.field_178351_a[2] : this.field_178351_a[0];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_178346_b(int p_178346_1_) {
/*  39 */     if (this.field_178351_a == null)
/*     */     {
/*  41 */       throw new NullPointerException("uvs");
/*     */     }
/*     */ 
/*     */     
/*  45 */     int var2 = func_178347_d(p_178346_1_);
/*  46 */     return (var2 != 0 && var2 != 3) ? this.field_178351_a[3] : this.field_178351_a[1];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int func_178347_d(int p_178347_1_) {
/*  52 */     return (p_178347_1_ + this.field_178350_b / 90) % 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178345_c(int p_178345_1_) {
/*  57 */     return (p_178345_1_ + 4 - this.field_178350_b / 90) % 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178349_a(float[] p_178349_1_) {
/*  62 */     if (this.field_178351_a == null)
/*     */     {
/*  64 */       this.field_178351_a = p_178349_1_;
/*     */     }
/*     */   }
/*     */   
/*     */   static class Deserializer
/*     */     implements JsonDeserializer
/*     */   {
/*     */     private static final String __OBFID = "CL_00002504";
/*     */     
/*     */     public BlockFaceUV func_178293_a(JsonElement p_178293_1_, Type p_178293_2_, JsonDeserializationContext p_178293_3_) {
/*  74 */       JsonObject var4 = p_178293_1_.getAsJsonObject();
/*  75 */       float[] var5 = func_178292_b(var4);
/*  76 */       int var6 = func_178291_a(var4);
/*  77 */       return new BlockFaceUV(var5, var6);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int func_178291_a(JsonObject p_178291_1_) {
/*  82 */       int var2 = JsonUtils.getJsonObjectIntegerFieldValueOrDefault(p_178291_1_, "rotation", 0);
/*     */       
/*  84 */       if (var2 >= 0 && var2 % 90 == 0 && var2 / 90 <= 3)
/*     */       {
/*  86 */         return var2;
/*     */       }
/*     */ 
/*     */       
/*  90 */       throw new JsonParseException("Invalid rotation " + var2 + " found, only 0/90/180/270 allowed");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private float[] func_178292_b(JsonObject p_178292_1_) {
/*  96 */       if (!p_178292_1_.has("uv"))
/*     */       {
/*  98 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 102 */       JsonArray var2 = JsonUtils.getJsonObjectJsonArrayField(p_178292_1_, "uv");
/*     */       
/* 104 */       if (var2.size() != 4)
/*     */       {
/* 106 */         throw new JsonParseException("Expected 4 uv values, found: " + var2.size());
/*     */       }
/*     */ 
/*     */       
/* 110 */       float[] var3 = new float[4];
/*     */       
/* 112 */       for (int var4 = 0; var4 < var3.length; var4++)
/*     */       {
/* 114 */         var3[var4] = JsonUtils.getJsonElementFloatValue(var2.get(var4), "uv[" + var4 + "]");
/*     */       }
/*     */       
/* 117 */       return var3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 124 */       return func_178293_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\block\model\BlockFaceUV.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */