/*     */ package net.minecraft.network;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ 
/*     */ 
/*     */ public class ServerStatusResponse
/*     */ {
/*     */   private IChatComponent serverMotd;
/*     */   private PlayerCountData playerCount;
/*     */   private MinecraftProtocolVersionIdentifier protocolVersion;
/*     */   private String favicon;
/*     */   private static final String __OBFID = "CL_00001385";
/*     */   
/*     */   public IChatComponent getServerDescription() {
/*  26 */     return this.serverMotd;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setServerDescription(IChatComponent motd) {
/*  31 */     this.serverMotd = motd;
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerCountData getPlayerCountData() {
/*  36 */     return this.playerCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerCountData(PlayerCountData countData) {
/*  41 */     this.playerCount = countData;
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecraftProtocolVersionIdentifier getProtocolVersionInfo() {
/*  46 */     return this.protocolVersion;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProtocolVersionInfo(MinecraftProtocolVersionIdentifier protocolVersionData) {
/*  51 */     this.protocolVersion = protocolVersionData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFavicon(String faviconBlob) {
/*  56 */     this.favicon = faviconBlob;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getFavicon() {
/*  61 */     return this.favicon;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class MinecraftProtocolVersionIdentifier
/*     */   {
/*     */     private final String name;
/*     */     private final int protocol;
/*     */     private static final String __OBFID = "CL_00001389";
/*     */     
/*     */     public MinecraftProtocolVersionIdentifier(String nameIn, int protocolIn) {
/*  72 */       this.name = nameIn;
/*  73 */       this.protocol = protocolIn;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/*  78 */       return this.name;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getProtocol() {
/*  83 */       return this.protocol;
/*     */     }
/*     */     
/*     */     public static class Serializer
/*     */       implements JsonDeserializer, JsonSerializer
/*     */     {
/*     */       private static final String __OBFID = "CL_00001390";
/*     */       
/*     */       public ServerStatusResponse.MinecraftProtocolVersionIdentifier deserialize1(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/*  92 */         JsonObject var4 = JsonUtils.getElementAsJsonObject(p_deserialize_1_, "version");
/*  93 */         return new ServerStatusResponse.MinecraftProtocolVersionIdentifier(JsonUtils.getJsonObjectStringFieldValue(var4, "name"), JsonUtils.getJsonObjectIntegerFieldValue(var4, "protocol"));
/*     */       }
/*     */ 
/*     */       
/*     */       public JsonElement serialize(ServerStatusResponse.MinecraftProtocolVersionIdentifier p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/*  98 */         JsonObject var4 = new JsonObject();
/*  99 */         var4.addProperty("name", p_serialize_1_.getName());
/* 100 */         var4.addProperty("protocol", Integer.valueOf(p_serialize_1_.getProtocol()));
/* 101 */         return (JsonElement)var4;
/*     */       }
/*     */ 
/*     */       
/*     */       public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 106 */         return serialize((ServerStatusResponse.MinecraftProtocolVersionIdentifier)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*     */       }
/*     */ 
/*     */       
/*     */       public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 111 */         return deserialize1(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class PlayerCountData
/*     */   {
/*     */     private final int maxPlayers;
/*     */     private final int onlinePlayerCount;
/*     */     private GameProfile[] players;
/*     */     private static final String __OBFID = "CL_00001386";
/*     */     
/*     */     public PlayerCountData(int p_i45274_1_, int p_i45274_2_) {
/* 125 */       this.maxPlayers = p_i45274_1_;
/* 126 */       this.onlinePlayerCount = p_i45274_2_;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMaxPlayers() {
/* 131 */       return this.maxPlayers;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getOnlinePlayerCount() {
/* 136 */       return this.onlinePlayerCount;
/*     */     }
/*     */ 
/*     */     
/*     */     public GameProfile[] getPlayers() {
/* 141 */       return this.players;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setPlayers(GameProfile[] playersIn) {
/* 146 */       this.players = playersIn;
/*     */     }
/*     */     
/*     */     public static class Serializer
/*     */       implements JsonDeserializer, JsonSerializer
/*     */     {
/*     */       private static final String __OBFID = "CL_00001387";
/*     */       
/*     */       public ServerStatusResponse.PlayerCountData deserialize1(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 155 */         JsonObject var4 = JsonUtils.getElementAsJsonObject(p_deserialize_1_, "players");
/* 156 */         ServerStatusResponse.PlayerCountData var5 = new ServerStatusResponse.PlayerCountData(JsonUtils.getJsonObjectIntegerFieldValue(var4, "max"), JsonUtils.getJsonObjectIntegerFieldValue(var4, "online"));
/*     */         
/* 158 */         if (JsonUtils.jsonObjectFieldTypeIsArray(var4, "sample")) {
/*     */           
/* 160 */           JsonArray var6 = JsonUtils.getJsonObjectJsonArrayField(var4, "sample");
/*     */           
/* 162 */           if (var6.size() > 0) {
/*     */             
/* 164 */             GameProfile[] var7 = new GameProfile[var6.size()];
/*     */             
/* 166 */             for (int var8 = 0; var8 < var7.length; var8++) {
/*     */               
/* 168 */               JsonObject var9 = JsonUtils.getElementAsJsonObject(var6.get(var8), "player[" + var8 + "]");
/* 169 */               String var10 = JsonUtils.getJsonObjectStringFieldValue(var9, "id");
/* 170 */               var7[var8] = new GameProfile(UUID.fromString(var10), JsonUtils.getJsonObjectStringFieldValue(var9, "name"));
/*     */             } 
/*     */             
/* 173 */             var5.setPlayers(var7);
/*     */           } 
/*     */         } 
/*     */         
/* 177 */         return var5;
/*     */       }
/*     */ 
/*     */       
/*     */       public JsonElement serialize(ServerStatusResponse.PlayerCountData p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 182 */         JsonObject var4 = new JsonObject();
/* 183 */         var4.addProperty("max", Integer.valueOf(p_serialize_1_.getMaxPlayers()));
/* 184 */         var4.addProperty("online", Integer.valueOf(p_serialize_1_.getOnlinePlayerCount()));
/*     */         
/* 186 */         if (p_serialize_1_.getPlayers() != null && (p_serialize_1_.getPlayers()).length > 0) {
/*     */           
/* 188 */           JsonArray var5 = new JsonArray();
/*     */           
/* 190 */           for (int var6 = 0; var6 < (p_serialize_1_.getPlayers()).length; var6++) {
/*     */             
/* 192 */             JsonObject var7 = new JsonObject();
/* 193 */             UUID var8 = p_serialize_1_.getPlayers()[var6].getId();
/* 194 */             var7.addProperty("id", (var8 == null) ? "" : var8.toString());
/* 195 */             var7.addProperty("name", p_serialize_1_.getPlayers()[var6].getName());
/* 196 */             var5.add((JsonElement)var7);
/*     */           } 
/*     */           
/* 199 */           var4.add("sample", (JsonElement)var5);
/*     */         } 
/*     */         
/* 202 */         return (JsonElement)var4;
/*     */       }
/*     */ 
/*     */       
/*     */       public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 207 */         return serialize((ServerStatusResponse.PlayerCountData)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*     */       }
/*     */ 
/*     */       
/*     */       public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 212 */         return deserialize1(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Serializer
/*     */     implements JsonDeserializer, JsonSerializer
/*     */   {
/*     */     private static final String __OBFID = "CL_00001388";
/*     */     
/*     */     public ServerStatusResponse deserialize1(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 223 */       JsonObject var4 = JsonUtils.getElementAsJsonObject(p_deserialize_1_, "status");
/* 224 */       ServerStatusResponse var5 = new ServerStatusResponse();
/*     */       
/* 226 */       if (var4.has("description"))
/*     */       {
/* 228 */         var5.setServerDescription((IChatComponent)p_deserialize_3_.deserialize(var4.get("description"), IChatComponent.class));
/*     */       }
/*     */       
/* 231 */       if (var4.has("players"))
/*     */       {
/* 233 */         var5.setPlayerCountData((ServerStatusResponse.PlayerCountData)p_deserialize_3_.deserialize(var4.get("players"), ServerStatusResponse.PlayerCountData.class));
/*     */       }
/*     */       
/* 236 */       if (var4.has("version"))
/*     */       {
/* 238 */         var5.setProtocolVersionInfo((ServerStatusResponse.MinecraftProtocolVersionIdentifier)p_deserialize_3_.deserialize(var4.get("version"), ServerStatusResponse.MinecraftProtocolVersionIdentifier.class));
/*     */       }
/*     */       
/* 241 */       if (var4.has("favicon"))
/*     */       {
/* 243 */         var5.setFavicon(JsonUtils.getJsonObjectStringFieldValue(var4, "favicon"));
/*     */       }
/*     */       
/* 246 */       return var5;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement serialize(ServerStatusResponse p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 251 */       JsonObject var4 = new JsonObject();
/*     */       
/* 253 */       if (p_serialize_1_.getServerDescription() != null)
/*     */       {
/* 255 */         var4.add("description", p_serialize_3_.serialize(p_serialize_1_.getServerDescription()));
/*     */       }
/*     */       
/* 258 */       if (p_serialize_1_.getPlayerCountData() != null)
/*     */       {
/* 260 */         var4.add("players", p_serialize_3_.serialize(p_serialize_1_.getPlayerCountData()));
/*     */       }
/*     */       
/* 263 */       if (p_serialize_1_.getProtocolVersionInfo() != null)
/*     */       {
/* 265 */         var4.add("version", p_serialize_3_.serialize(p_serialize_1_.getProtocolVersionInfo()));
/*     */       }
/*     */       
/* 268 */       if (p_serialize_1_.getFavicon() != null)
/*     */       {
/* 270 */         var4.addProperty("favicon", p_serialize_1_.getFavicon());
/*     */       }
/*     */       
/* 273 */       return (JsonElement)var4;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 278 */       return serialize((ServerStatusResponse)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 283 */       return deserialize1(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\ServerStatusResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */