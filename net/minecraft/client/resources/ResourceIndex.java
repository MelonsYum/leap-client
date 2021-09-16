/*    */ package net.minecraft.client.resources;
/*    */ 
/*    */ import com.google.common.base.Charsets;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.io.Files;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonParser;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.util.JsonUtils;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class ResourceIndex
/*    */ {
/* 22 */   private static final Logger field_152783_a = LogManager.getLogger();
/* 23 */   private final Map field_152784_b = Maps.newHashMap();
/*    */   
/*    */   private static final String __OBFID = "CL_00001831";
/*    */   
/*    */   public ResourceIndex(File p_i1047_1_, String p_i1047_2_) {
/* 28 */     if (p_i1047_2_ != null) {
/*    */       
/* 30 */       File var3 = new File(p_i1047_1_, "objects");
/* 31 */       File var4 = new File(p_i1047_1_, "indexes/" + p_i1047_2_ + ".json");
/* 32 */       BufferedReader var5 = null;
/*    */ 
/*    */       
/*    */       try {
/* 36 */         var5 = Files.newReader(var4, Charsets.UTF_8);
/* 37 */         JsonObject var6 = (new JsonParser()).parse(var5).getAsJsonObject();
/* 38 */         JsonObject var7 = JsonUtils.getJsonObjectFieldOrDefault(var6, "objects", null);
/*    */         
/* 40 */         if (var7 != null) {
/*    */           
/* 42 */           Iterator<Map.Entry> var8 = var7.entrySet().iterator();
/*    */           
/* 44 */           while (var8.hasNext())
/*    */           {
/* 46 */             Map.Entry var9 = var8.next();
/* 47 */             JsonObject var10 = (JsonObject)var9.getValue();
/* 48 */             String var11 = (String)var9.getKey();
/* 49 */             String[] var12 = var11.split("/", 2);
/* 50 */             String var13 = (var12.length == 1) ? var12[0] : (String.valueOf(var12[0]) + ":" + var12[1]);
/* 51 */             String var14 = JsonUtils.getJsonObjectStringFieldValue(var10, "hash");
/* 52 */             File var15 = new File(var3, String.valueOf(var14.substring(0, 2)) + "/" + var14);
/* 53 */             this.field_152784_b.put(var13, var15);
/*    */           }
/*    */         
/*    */         } 
/* 57 */       } catch (JsonParseException var20) {
/*    */         
/* 59 */         field_152783_a.error("Unable to parse resource index file: " + var4);
/*    */       }
/* 61 */       catch (FileNotFoundException var21) {
/*    */         
/* 63 */         field_152783_a.error("Can't find the resource index file: " + var4);
/*    */       }
/*    */       finally {
/*    */         
/* 67 */         IOUtils.closeQuietly(var5);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Map func_152782_a() {
/* 74 */     return this.field_152784_b;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\resources\ResourceIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */