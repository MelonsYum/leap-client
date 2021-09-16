/*     */ package net.minecraft.server.management;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class UserList
/*     */ {
/*  31 */   protected static final Logger logger = LogManager.getLogger();
/*     */   protected final Gson gson;
/*     */   private final File saveFile;
/*  34 */   private final Map values = Maps.newHashMap();
/*     */   private boolean lanServer = true;
/*  36 */   private static final ParameterizedType saveFileFormat = new ParameterizedType()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001875";
/*     */       
/*     */       public Type[] getActualTypeArguments() {
/*  41 */         return new Type[] { UserListEntry.class };
/*     */       }
/*     */       
/*     */       public Type getRawType() {
/*  45 */         return List.class;
/*     */       }
/*     */       
/*     */       public Type getOwnerType() {
/*  49 */         return null;
/*     */       }
/*     */     };
/*     */   
/*     */   private static final String __OBFID = "CL_00001876";
/*     */   
/*     */   public UserList(File saveFile) {
/*  56 */     this.saveFile = saveFile;
/*  57 */     GsonBuilder var2 = (new GsonBuilder()).setPrettyPrinting();
/*  58 */     var2.registerTypeHierarchyAdapter(UserListEntry.class, new Serializer(null));
/*  59 */     this.gson = var2.create();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLanServer() {
/*  64 */     return this.lanServer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLanServer(boolean state) {
/*  69 */     this.lanServer = state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEntry(UserListEntry entry) {
/*  77 */     this.values.put(getObjectKey(entry.getValue()), entry);
/*     */ 
/*     */     
/*     */     try {
/*  81 */       writeChanges();
/*     */     }
/*  83 */     catch (IOException var3) {
/*     */       
/*  85 */       logger.warn("Could not save the list after adding a user.", var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public UserListEntry getEntry(Object obj) {
/*  91 */     removeExpired();
/*  92 */     return (UserListEntry)this.values.get(getObjectKey(obj));
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeEntry(Object p_152684_1_) {
/*  97 */     this.values.remove(getObjectKey(p_152684_1_));
/*     */ 
/*     */     
/*     */     try {
/* 101 */       writeChanges();
/*     */     }
/* 103 */     catch (IOException var3) {
/*     */       
/* 105 */       logger.warn("Could not save the list after removing a user.", var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getKeys() {
/* 111 */     return (String[])this.values.keySet().toArray((Object[])new String[this.values.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getObjectKey(Object obj) {
/* 119 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean hasEntry(Object entry) {
/* 124 */     return this.values.containsKey(getObjectKey(entry));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removeExpired() {
/* 133 */     ArrayList<Object> var1 = Lists.newArrayList();
/* 134 */     Iterator<UserListEntry> var2 = this.values.values().iterator();
/*     */     
/* 136 */     while (var2.hasNext()) {
/*     */       
/* 138 */       UserListEntry var3 = var2.next();
/*     */       
/* 140 */       if (var3.hasBanExpired())
/*     */       {
/* 142 */         var1.add(var3.getValue());
/*     */       }
/*     */     } 
/*     */     
/* 146 */     var2 = var1.iterator();
/*     */     
/* 148 */     while (var2.hasNext()) {
/*     */       
/* 150 */       Object var4 = var2.next();
/* 151 */       this.values.remove(var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected UserListEntry createEntry(JsonObject entryData) {
/* 157 */     return new UserListEntry(null, entryData);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Map getValues() {
/* 162 */     return this.values;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeChanges() throws IOException {
/* 167 */     Collection var1 = this.values.values();
/* 168 */     String var2 = this.gson.toJson(var1);
/* 169 */     BufferedWriter var3 = null;
/*     */ 
/*     */     
/*     */     try {
/* 173 */       var3 = Files.newWriter(this.saveFile, Charsets.UTF_8);
/* 174 */       var3.write(var2);
/*     */     }
/*     */     finally {
/*     */       
/* 178 */       IOUtils.closeQuietly(var3);
/*     */     } 
/*     */   }
/*     */   
/*     */   class Serializer
/*     */     implements JsonDeserializer, JsonSerializer
/*     */   {
/*     */     private static final String __OBFID = "CL_00001874";
/*     */     
/*     */     private Serializer() {}
/*     */     
/*     */     public JsonElement serializeEntry(UserListEntry p_152751_1_, Type p_152751_2_, JsonSerializationContext p_152751_3_) {
/* 190 */       JsonObject var4 = new JsonObject();
/* 191 */       p_152751_1_.onSerialization(var4);
/* 192 */       return (JsonElement)var4;
/*     */     }
/*     */ 
/*     */     
/*     */     public UserListEntry deserializeEntry(JsonElement p_152750_1_, Type p_152750_2_, JsonDeserializationContext p_152750_3_) {
/* 197 */       if (p_152750_1_.isJsonObject()) {
/*     */         
/* 199 */         JsonObject var4 = p_152750_1_.getAsJsonObject();
/* 200 */         UserListEntry var5 = UserList.this.createEntry(var4);
/* 201 */         return var5;
/*     */       } 
/*     */ 
/*     */       
/* 205 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 211 */       return serializeEntry((UserListEntry)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 216 */       return deserializeEntry(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */     }
/*     */ 
/*     */     
/*     */     Serializer(Object p_i1141_2_) {
/* 221 */       this();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\UserList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */