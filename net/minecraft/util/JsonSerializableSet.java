/*    */ package net.minecraft.util;
/*    */ 
/*    */ import com.google.common.collect.ForwardingSet;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class JsonSerializableSet
/*    */   extends ForwardingSet
/*    */   implements IJsonSerializable {
/* 15 */   private final Set underlyingSet = Sets.newHashSet();
/*    */   
/*    */   private static final String __OBFID = "CL_00001482";
/*    */   
/*    */   public void func_152753_a(JsonElement p_152753_1_) {
/* 20 */     if (p_152753_1_.isJsonArray()) {
/*    */       
/* 22 */       Iterator<JsonElement> var2 = p_152753_1_.getAsJsonArray().iterator();
/*    */       
/* 24 */       while (var2.hasNext()) {
/*    */         
/* 26 */         JsonElement var3 = var2.next();
/* 27 */         add(var3.getAsString());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement getSerializableElement() {
/* 37 */     JsonArray var1 = new JsonArray();
/* 38 */     Iterator<String> var2 = iterator();
/*    */     
/* 40 */     while (var2.hasNext()) {
/*    */       
/* 42 */       String var3 = var2.next();
/* 43 */       var1.add((JsonElement)new JsonPrimitive(var3));
/*    */     } 
/*    */     
/* 46 */     return (JsonElement)var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Set delegate() {
/* 51 */     return this.underlyingSet;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\JsonSerializableSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */