/*     */ package net.minecraft.server.management;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.collect.Iterators;
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
/*     */ import com.mojang.authlib.Agent;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.ProfileLookupCallback;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ public class PlayerProfileCache
/*     */ {
/*  43 */   public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*  44 */   private final Map field_152661_c = Maps.newHashMap();
/*  45 */   private final Map field_152662_d = Maps.newHashMap();
/*  46 */   private final LinkedList field_152663_e = Lists.newLinkedList();
/*     */   private final MinecraftServer field_152664_f;
/*     */   protected final Gson gson;
/*     */   private final File usercacheFile;
/*  50 */   private static final ParameterizedType field_152666_h = new ParameterizedType()
/*     */     {
/*     */       private static final String __OBFID = "CL_00001886";
/*     */       
/*     */       public Type[] getActualTypeArguments() {
/*  55 */         return new Type[] { PlayerProfileCache.ProfileEntry.class };
/*     */       }
/*     */       
/*     */       public Type getRawType() {
/*  59 */         return List.class;
/*     */       }
/*     */       
/*     */       public Type getOwnerType() {
/*  63 */         return null;
/*     */       }
/*     */     };
/*     */   
/*     */   private static final String __OBFID = "CL_00001888";
/*     */   
/*     */   public PlayerProfileCache(MinecraftServer p_i1171_1_, File p_i1171_2_) {
/*  70 */     this.field_152664_f = p_i1171_1_;
/*  71 */     this.usercacheFile = p_i1171_2_;
/*  72 */     GsonBuilder var3 = new GsonBuilder();
/*  73 */     var3.registerTypeHierarchyAdapter(ProfileEntry.class, new Serializer(null));
/*  74 */     this.gson = var3.create();
/*  75 */     func_152657_b();
/*     */   }
/*     */ 
/*     */   
/*     */   private static GameProfile func_152650_a(MinecraftServer p_152650_0_, String p_152650_1_) {
/*  80 */     final GameProfile[] var2 = new GameProfile[1];
/*  81 */     ProfileLookupCallback var3 = new ProfileLookupCallback()
/*     */       {
/*     */         private static final String __OBFID = "CL_00001887";
/*     */         
/*     */         public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
/*  86 */           var2[0] = p_onProfileLookupSucceeded_1_;
/*     */         }
/*     */         
/*     */         public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
/*  90 */           var2[0] = null;
/*     */         }
/*     */       };
/*  93 */     p_152650_0_.getGameProfileRepository().findProfilesByNames(new String[] { p_152650_1_ }, Agent.MINECRAFT, var3);
/*     */     
/*  95 */     if (!p_152650_0_.isServerInOnlineMode() && var2[0] == null) {
/*     */       
/*  97 */       UUID var4 = EntityPlayer.getUUID(new GameProfile(null, p_152650_1_));
/*  98 */       GameProfile var5 = new GameProfile(var4, p_152650_1_);
/*  99 */       var3.onProfileLookupSucceeded(var5);
/*     */     } 
/*     */     
/* 102 */     return var2[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152649_a(GameProfile p_152649_1_) {
/* 107 */     func_152651_a(p_152649_1_, null);
/*     */   }
/*     */ 
/*     */   
/*     */   private void func_152651_a(GameProfile p_152651_1_, Date p_152651_2_) {
/* 112 */     UUID var3 = p_152651_1_.getId();
/*     */     
/* 114 */     if (p_152651_2_ == null) {
/*     */       
/* 116 */       Calendar var4 = Calendar.getInstance();
/* 117 */       var4.setTime(new Date());
/* 118 */       var4.add(2, 1);
/* 119 */       p_152651_2_ = var4.getTime();
/*     */     } 
/*     */     
/* 122 */     String var7 = p_152651_1_.getName().toLowerCase(Locale.ROOT);
/* 123 */     ProfileEntry var5 = new ProfileEntry(p_152651_1_, p_152651_2_, null);
/*     */     
/* 125 */     if (this.field_152662_d.containsKey(var3)) {
/*     */       
/* 127 */       ProfileEntry var6 = (ProfileEntry)this.field_152662_d.get(var3);
/* 128 */       this.field_152661_c.remove(var6.func_152668_a().getName().toLowerCase(Locale.ROOT));
/* 129 */       this.field_152661_c.put(p_152651_1_.getName().toLowerCase(Locale.ROOT), var5);
/* 130 */       this.field_152663_e.remove(p_152651_1_);
/*     */     }
/*     */     else {
/*     */       
/* 134 */       this.field_152662_d.put(var3, var5);
/* 135 */       this.field_152661_c.put(var7, var5);
/*     */     } 
/*     */     
/* 138 */     this.field_152663_e.addFirst(p_152651_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public GameProfile getGameProfileForUsername(String p_152655_1_) {
/* 143 */     String var2 = p_152655_1_.toLowerCase(Locale.ROOT);
/* 144 */     ProfileEntry var3 = (ProfileEntry)this.field_152661_c.get(var2);
/*     */     
/* 146 */     if (var3 != null && (new Date()).getTime() >= var3.field_152673_c.getTime()) {
/*     */       
/* 148 */       this.field_152662_d.remove(var3.func_152668_a().getId());
/* 149 */       this.field_152661_c.remove(var3.func_152668_a().getName().toLowerCase(Locale.ROOT));
/* 150 */       this.field_152663_e.remove(var3.func_152668_a());
/* 151 */       var3 = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 156 */     if (var3 != null) {
/*     */       
/* 158 */       GameProfile var4 = var3.func_152668_a();
/* 159 */       this.field_152663_e.remove(var4);
/* 160 */       this.field_152663_e.addFirst(var4);
/*     */     }
/*     */     else {
/*     */       
/* 164 */       GameProfile var4 = func_152650_a(this.field_152664_f, var2);
/*     */       
/* 166 */       if (var4 != null) {
/*     */         
/* 168 */         func_152649_a(var4);
/* 169 */         var3 = (ProfileEntry)this.field_152661_c.get(var2);
/*     */       } 
/*     */     } 
/*     */     
/* 173 */     func_152658_c();
/* 174 */     return (var3 == null) ? null : var3.func_152668_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] func_152654_a() {
/* 179 */     ArrayList var1 = Lists.newArrayList(this.field_152661_c.keySet());
/* 180 */     return (String[])var1.toArray((Object[])new String[var1.size()]);
/*     */   }
/*     */ 
/*     */   
/*     */   public GameProfile func_152652_a(UUID p_152652_1_) {
/* 185 */     ProfileEntry var2 = (ProfileEntry)this.field_152662_d.get(p_152652_1_);
/* 186 */     return (var2 == null) ? null : var2.func_152668_a();
/*     */   }
/*     */ 
/*     */   
/*     */   private ProfileEntry func_152653_b(UUID p_152653_1_) {
/* 191 */     ProfileEntry var2 = (ProfileEntry)this.field_152662_d.get(p_152653_1_);
/*     */     
/* 193 */     if (var2 != null) {
/*     */       
/* 195 */       GameProfile var3 = var2.func_152668_a();
/* 196 */       this.field_152663_e.remove(var3);
/* 197 */       this.field_152663_e.addFirst(var3);
/*     */     } 
/*     */     
/* 200 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152657_b() {
/* 205 */     List var1 = null;
/* 206 */     BufferedReader var2 = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 211 */       var2 = Files.newReader(this.usercacheFile, Charsets.UTF_8);
/* 212 */       var1 = (List)this.gson.fromJson(var2, field_152666_h);
/*     */     
/*     */     }
/* 215 */     catch (FileNotFoundException fileNotFoundException) {
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 221 */       IOUtils.closeQuietly(var2);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 227 */     if (var1 != null) {
/*     */       
/* 229 */       this.field_152661_c.clear();
/* 230 */       this.field_152662_d.clear();
/* 231 */       this.field_152663_e.clear();
/* 232 */       var1 = Lists.reverse(var1);
/* 233 */       Iterator<ProfileEntry> var3 = var1.iterator();
/*     */       
/* 235 */       while (var3.hasNext()) {
/*     */         
/* 237 */         ProfileEntry var4 = var3.next();
/*     */         
/* 239 */         if (var4 != null)
/*     */         {
/* 241 */           func_152651_a(var4.func_152668_a(), var4.func_152670_b());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152658_c() {
/* 249 */     String var1 = this.gson.toJson(func_152656_a(1000));
/* 250 */     BufferedWriter var2 = null;
/*     */ 
/*     */     
/*     */     try {
/* 254 */       var2 = Files.newWriter(this.usercacheFile, Charsets.UTF_8);
/* 255 */       var2.write(var1);
/*     */       
/*     */       return;
/* 258 */     } catch (FileNotFoundException var8) {
/*     */       
/*     */       return;
/*     */     }
/* 262 */     catch (IOException iOException) {
/*     */ 
/*     */     
/*     */     }
/*     */     finally {
/*     */       
/* 268 */       IOUtils.closeQuietly(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private List func_152656_a(int p_152656_1_) {
/* 274 */     ArrayList<ProfileEntry> var2 = Lists.newArrayList();
/* 275 */     ArrayList var3 = Lists.newArrayList(Iterators.limit(this.field_152663_e.iterator(), p_152656_1_));
/* 276 */     Iterator<GameProfile> var4 = var3.iterator();
/*     */     
/* 278 */     while (var4.hasNext()) {
/*     */       
/* 280 */       GameProfile var5 = var4.next();
/* 281 */       ProfileEntry var6 = func_152653_b(var5.getId());
/*     */       
/* 283 */       if (var6 != null)
/*     */       {
/* 285 */         var2.add(var6);
/*     */       }
/*     */     } 
/*     */     
/* 289 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   class ProfileEntry
/*     */   {
/*     */     private final GameProfile field_152672_b;
/*     */     private final Date field_152673_c;
/*     */     private static final String __OBFID = "CL_00001885";
/*     */     
/*     */     private ProfileEntry(GameProfile p_i46333_2_, Date p_i46333_3_) {
/* 300 */       this.field_152672_b = p_i46333_2_;
/* 301 */       this.field_152673_c = p_i46333_3_;
/*     */     }
/*     */ 
/*     */     
/*     */     public GameProfile func_152668_a() {
/* 306 */       return this.field_152672_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public Date func_152670_b() {
/* 311 */       return this.field_152673_c;
/*     */     }
/*     */ 
/*     */     
/*     */     ProfileEntry(GameProfile p_i1166_2_, Date p_i1166_3_, Object p_i1166_4_) {
/* 316 */       this(p_i1166_2_, p_i1166_3_);
/*     */     }
/*     */   }
/*     */   
/*     */   class Serializer
/*     */     implements JsonDeserializer, JsonSerializer
/*     */   {
/*     */     private static final String __OBFID = "CL_00001884";
/*     */     
/*     */     private Serializer() {}
/*     */     
/*     */     public JsonElement func_152676_a(PlayerProfileCache.ProfileEntry p_152676_1_, Type p_152676_2_, JsonSerializationContext p_152676_3_) {
/* 328 */       JsonObject var4 = new JsonObject();
/* 329 */       var4.addProperty("name", p_152676_1_.func_152668_a().getName());
/* 330 */       UUID var5 = p_152676_1_.func_152668_a().getId();
/* 331 */       var4.addProperty("uuid", (var5 == null) ? "" : var5.toString());
/* 332 */       var4.addProperty("expiresOn", PlayerProfileCache.dateFormat.format(p_152676_1_.func_152670_b()));
/* 333 */       return (JsonElement)var4;
/*     */     }
/*     */ 
/*     */     
/*     */     public PlayerProfileCache.ProfileEntry func_152675_a(JsonElement p_152675_1_, Type p_152675_2_, JsonDeserializationContext p_152675_3_) {
/* 338 */       if (p_152675_1_.isJsonObject()) {
/*     */         
/* 340 */         JsonObject var4 = p_152675_1_.getAsJsonObject();
/* 341 */         JsonElement var5 = var4.get("name");
/* 342 */         JsonElement var6 = var4.get("uuid");
/* 343 */         JsonElement var7 = var4.get("expiresOn");
/*     */         
/* 345 */         if (var5 != null && var6 != null) {
/*     */           
/* 347 */           String var8 = var6.getAsString();
/* 348 */           String var9 = var5.getAsString();
/* 349 */           Date var10 = null;
/*     */           
/* 351 */           if (var7 != null) {
/*     */             
/*     */             try {
/*     */               
/* 355 */               var10 = PlayerProfileCache.dateFormat.parse(var7.getAsString());
/*     */             }
/* 357 */             catch (ParseException var14) {
/*     */               
/* 359 */               var10 = null;
/*     */             } 
/*     */           }
/*     */           
/* 363 */           if (var9 != null && var8 != null) {
/*     */             UUID var11;
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/* 369 */               var11 = UUID.fromString(var8);
/*     */             }
/* 371 */             catch (Throwable var13) {
/*     */               
/* 373 */               return null;
/*     */             } 
/*     */             
/* 376 */             PlayerProfileCache.this.getClass(); PlayerProfileCache.ProfileEntry var12 = new PlayerProfileCache.ProfileEntry(new GameProfile(var11, var9), var10, null);
/* 377 */             return var12;
/*     */           } 
/*     */ 
/*     */           
/* 381 */           return null;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 386 */         return null;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 391 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonElement serialize(Object p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
/* 397 */       return func_152676_a((PlayerProfileCache.ProfileEntry)p_serialize_1_, p_serialize_2_, p_serialize_3_);
/*     */     }
/*     */ 
/*     */     
/*     */     public Object deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) {
/* 402 */       return func_152675_a(p_deserialize_1_, p_deserialize_2_, p_deserialize_3_);
/*     */     }
/*     */ 
/*     */     
/*     */     Serializer(Object p_i46332_2_) {
/* 407 */       this();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\management\PlayerProfileCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */