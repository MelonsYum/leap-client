/*    */ package net.minecraft.util;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.TypeAdapterFactory;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import com.google.gson.stream.JsonReader;
/*    */ import com.google.gson.stream.JsonToken;
/*    */ import com.google.gson.stream.JsonWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.HashMap;
/*    */ import java.util.Locale;
/*    */ 
/*    */ public class EnumTypeAdapterFactory
/*    */   implements TypeAdapterFactory
/*    */ {
/*    */   private static final String __OBFID = "CL_00001494";
/*    */   
/*    */   public TypeAdapter create(Gson p_create_1_, TypeToken p_create_2_) {
/* 21 */     Class<Object> var3 = p_create_2_.getRawType();
/*    */     
/* 23 */     if (!var3.isEnum())
/*    */     {
/* 25 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 29 */     final HashMap<String, Object> var4 = Maps.newHashMap();
/* 30 */     Object[] var5 = var3.getEnumConstants();
/* 31 */     int var6 = var5.length;
/*    */     
/* 33 */     for (int var7 = 0; var7 < var6; var7++) {
/*    */       
/* 35 */       Object var8 = var5[var7];
/* 36 */       var4.put(func_151232_a(var8), var8);
/*    */     } 
/*    */     
/* 39 */     return new TypeAdapter()
/*    */       {
/*    */         private static final String __OBFID = "CL_00001495";
/*    */         
/*    */         public void write(JsonWriter p_write_1_, Object p_write_2_) throws IOException {
/* 44 */           if (p_write_2_ == null) {
/*    */             
/* 46 */             p_write_1_.nullValue();
/*    */           }
/*    */           else {
/*    */             
/* 50 */             p_write_1_.value(EnumTypeAdapterFactory.this.func_151232_a(p_write_2_));
/*    */           } 
/*    */         }
/*    */         
/*    */         public Object read(JsonReader p_read_1_) throws IOException {
/* 55 */           if (p_read_1_.peek() == JsonToken.NULL) {
/*    */             
/* 57 */             p_read_1_.nextNull();
/* 58 */             return null;
/*    */           } 
/*    */ 
/*    */           
/* 62 */           return var4.get(p_read_1_.nextString());
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private String func_151232_a(Object p_151232_1_) {
/* 71 */     return (p_151232_1_ instanceof Enum) ? ((Enum)p_151232_1_).name().toLowerCase(Locale.US) : p_151232_1_.toString().toLowerCase(Locale.US);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\EnumTypeAdapterFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */