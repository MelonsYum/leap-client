/*    */ package optifine;
/*    */ 
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ 
/*    */ 
/*    */ public class Json
/*    */ {
/*    */   public static float getFloat(JsonObject obj, String field, float def) {
/* 12 */     JsonElement elem = obj.get(field);
/* 13 */     return (elem == null) ? def : elem.getAsFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean getBoolean(JsonObject obj, String field, boolean def) {
/* 18 */     JsonElement elem = obj.get(field);
/* 19 */     return (elem == null) ? def : elem.getAsBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getString(JsonObject jsonObj, String field) {
/* 24 */     return getString(jsonObj, field, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getString(JsonObject jsonObj, String field, String def) {
/* 29 */     JsonElement jsonElement = jsonObj.get(field);
/* 30 */     return (jsonElement == null) ? def : jsonElement.getAsString();
/*    */   }
/*    */ 
/*    */   
/*    */   public static float[] parseFloatArray(JsonElement jsonElement, int len) {
/* 35 */     return parseFloatArray(jsonElement, len, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public static float[] parseFloatArray(JsonElement jsonElement, int len, float[] def) {
/* 40 */     if (jsonElement == null)
/*    */     {
/* 42 */       return def;
/*    */     }
/*    */ 
/*    */     
/* 46 */     JsonArray arr = jsonElement.getAsJsonArray();
/*    */     
/* 48 */     if (arr.size() != len)
/*    */     {
/* 50 */       throw new JsonParseException("Wrong array length: " + arr.size() + ", should be: " + len + ", array: " + arr);
/*    */     }
/*    */ 
/*    */     
/* 54 */     float[] floatArr = new float[arr.size()];
/*    */     
/* 56 */     for (int i = 0; i < floatArr.length; i++)
/*    */     {
/* 58 */       floatArr[i] = arr.get(i).getAsFloat();
/*    */     }
/*    */     
/* 61 */     return floatArr;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int[] parseIntArray(JsonElement jsonElement, int len) {
/* 68 */     return parseIntArray(jsonElement, len, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int[] parseIntArray(JsonElement jsonElement, int len, int[] def) {
/* 73 */     if (jsonElement == null)
/*    */     {
/* 75 */       return def;
/*    */     }
/*    */ 
/*    */     
/* 79 */     JsonArray arr = jsonElement.getAsJsonArray();
/*    */     
/* 81 */     if (arr.size() != len)
/*    */     {
/* 83 */       throw new JsonParseException("Wrong array length: " + arr.size() + ", should be: " + len + ", array: " + arr);
/*    */     }
/*    */ 
/*    */     
/* 87 */     int[] intArr = new int[arr.size()];
/*    */     
/* 89 */     for (int i = 0; i < intArr.length; i++)
/*    */     {
/* 91 */       intArr[i] = arr.get(i).getAsInt();
/*    */     }
/*    */     
/* 94 */     return intArr;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\Json.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */