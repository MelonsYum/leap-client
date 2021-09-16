/*     */ package net.minecraft.stats;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S37PacketStatistics;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.IJsonSerializable;
/*     */ import net.minecraft.util.TupleIntJsonSerializable;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class StatisticsFile extends StatFileWriter {
/*  31 */   private static final Logger logger = LogManager.getLogger();
/*     */   private final MinecraftServer field_150890_c;
/*     */   private final File field_150887_d;
/*  34 */   private final Set field_150888_e = Sets.newHashSet();
/*  35 */   private int field_150885_f = -300;
/*     */   
/*     */   private boolean field_150886_g = false;
/*     */   private static final String __OBFID = "CL_00001471";
/*     */   
/*     */   public StatisticsFile(MinecraftServer p_i45306_1_, File p_i45306_2_) {
/*  41 */     this.field_150890_c = p_i45306_1_;
/*  42 */     this.field_150887_d = p_i45306_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_150882_a() {
/*  47 */     if (this.field_150887_d.isFile()) {
/*     */       
/*     */       try {
/*     */         
/*  51 */         this.field_150875_a.clear();
/*  52 */         this.field_150875_a.putAll(func_150881_a(FileUtils.readFileToString(this.field_150887_d)));
/*     */       }
/*  54 */       catch (IOException var2) {
/*     */         
/*  56 */         logger.error("Couldn't read statistics file " + this.field_150887_d, var2);
/*     */       }
/*  58 */       catch (JsonParseException var3) {
/*     */         
/*  60 */         logger.error("Couldn't parse statistics file " + this.field_150887_d, (Throwable)var3);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150883_b() {
/*     */     try {
/*  69 */       FileUtils.writeStringToFile(this.field_150887_d, func_150880_a(this.field_150875_a));
/*     */     }
/*  71 */     catch (IOException var2) {
/*     */       
/*  73 */       logger.error("Couldn't save stats", var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_150873_a(EntityPlayer p_150873_1_, StatBase p_150873_2_, int p_150873_3_) {
/*  79 */     int var4 = p_150873_2_.isAchievement() ? writeStat(p_150873_2_) : 0;
/*  80 */     super.func_150873_a(p_150873_1_, p_150873_2_, p_150873_3_);
/*  81 */     this.field_150888_e.add(p_150873_2_);
/*     */     
/*  83 */     if (p_150873_2_.isAchievement() && var4 == 0 && p_150873_3_ > 0) {
/*     */       
/*  85 */       this.field_150886_g = true;
/*     */       
/*  87 */       if (this.field_150890_c.isAnnouncingPlayerAchievements())
/*     */       {
/*  89 */         this.field_150890_c.getConfigurationManager().sendChatMsg((IChatComponent)new ChatComponentTranslation("chat.type.achievement", new Object[] { p_150873_1_.getDisplayName(), p_150873_2_.func_150955_j() }));
/*     */       }
/*     */     } 
/*     */     
/*  93 */     if (p_150873_2_.isAchievement() && var4 > 0 && p_150873_3_ == 0) {
/*     */       
/*  95 */       this.field_150886_g = true;
/*     */       
/*  97 */       if (this.field_150890_c.isAnnouncingPlayerAchievements())
/*     */       {
/*  99 */         this.field_150890_c.getConfigurationManager().sendChatMsg((IChatComponent)new ChatComponentTranslation("chat.type.achievement.taken", new Object[] { p_150873_1_.getDisplayName(), p_150873_2_.func_150955_j() }));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set func_150878_c() {
/* 106 */     HashSet var1 = Sets.newHashSet(this.field_150888_e);
/* 107 */     this.field_150888_e.clear();
/* 108 */     this.field_150886_g = false;
/* 109 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map func_150881_a(String p_150881_1_) {
/* 114 */     JsonElement var2 = (new JsonParser()).parse(p_150881_1_);
/*     */     
/* 116 */     if (!var2.isJsonObject())
/*     */     {
/* 118 */       return Maps.newHashMap();
/*     */     }
/*     */ 
/*     */     
/* 122 */     JsonObject var3 = var2.getAsJsonObject();
/* 123 */     HashMap<StatBase, TupleIntJsonSerializable> var4 = Maps.newHashMap();
/* 124 */     Iterator<Map.Entry> var5 = var3.entrySet().iterator();
/*     */     
/* 126 */     while (var5.hasNext()) {
/*     */       
/* 128 */       Map.Entry var6 = var5.next();
/* 129 */       StatBase var7 = StatList.getOneShotStat((String)var6.getKey());
/*     */       
/* 131 */       if (var7 != null) {
/*     */         
/* 133 */         TupleIntJsonSerializable var8 = new TupleIntJsonSerializable();
/*     */         
/* 135 */         if (((JsonElement)var6.getValue()).isJsonPrimitive() && ((JsonElement)var6.getValue()).getAsJsonPrimitive().isNumber()) {
/*     */           
/* 137 */           var8.setIntegerValue(((JsonElement)var6.getValue()).getAsInt());
/*     */         }
/* 139 */         else if (((JsonElement)var6.getValue()).isJsonObject()) {
/*     */           
/* 141 */           JsonObject var9 = ((JsonElement)var6.getValue()).getAsJsonObject();
/*     */           
/* 143 */           if (var9.has("value") && var9.get("value").isJsonPrimitive() && var9.get("value").getAsJsonPrimitive().isNumber())
/*     */           {
/* 145 */             var8.setIntegerValue(var9.getAsJsonPrimitive("value").getAsInt());
/*     */           }
/*     */           
/* 148 */           if (var9.has("progress") && var7.func_150954_l() != null) {
/*     */             
/*     */             try {
/*     */               
/* 152 */               Constructor<IJsonSerializable> var10 = var7.func_150954_l().getConstructor(new Class[0]);
/* 153 */               IJsonSerializable var11 = var10.newInstance(new Object[0]);
/* 154 */               var11.func_152753_a(var9.get("progress"));
/* 155 */               var8.setJsonSerializableValue(var11);
/*     */             }
/* 157 */             catch (Throwable var12) {
/*     */               
/* 159 */               logger.warn("Invalid statistic progress in " + this.field_150887_d, var12);
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 164 */         var4.put(var7, var8);
/*     */         
/*     */         continue;
/*     */       } 
/* 168 */       logger.warn("Invalid statistic in " + this.field_150887_d + ": Don't know what " + (String)var6.getKey() + " is");
/*     */     } 
/*     */ 
/*     */     
/* 172 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String func_150880_a(Map p_150880_0_) {
/* 178 */     JsonObject var1 = new JsonObject();
/* 179 */     Iterator<Map.Entry> var2 = p_150880_0_.entrySet().iterator();
/*     */     
/* 181 */     while (var2.hasNext()) {
/*     */       
/* 183 */       Map.Entry var3 = var2.next();
/*     */       
/* 185 */       if (((TupleIntJsonSerializable)var3.getValue()).getJsonSerializableValue() != null) {
/*     */         
/* 187 */         JsonObject var4 = new JsonObject();
/* 188 */         var4.addProperty("value", Integer.valueOf(((TupleIntJsonSerializable)var3.getValue()).getIntegerValue()));
/*     */ 
/*     */         
/*     */         try {
/* 192 */           var4.add("progress", ((TupleIntJsonSerializable)var3.getValue()).getJsonSerializableValue().getSerializableElement());
/*     */         }
/* 194 */         catch (Throwable var6) {
/*     */           
/* 196 */           logger.warn("Couldn't save statistic " + ((StatBase)var3.getKey()).getStatName() + ": error serializing progress", var6);
/*     */         } 
/*     */         
/* 199 */         var1.add(((StatBase)var3.getKey()).statId, (JsonElement)var4);
/*     */         
/*     */         continue;
/*     */       } 
/* 203 */       var1.addProperty(((StatBase)var3.getKey()).statId, Integer.valueOf(((TupleIntJsonSerializable)var3.getValue()).getIntegerValue()));
/*     */     } 
/*     */ 
/*     */     
/* 207 */     return var1.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_150877_d() {
/* 212 */     Iterator<StatBase> var1 = this.field_150875_a.keySet().iterator();
/*     */     
/* 214 */     while (var1.hasNext()) {
/*     */       
/* 216 */       StatBase var2 = var1.next();
/* 217 */       this.field_150888_e.add(var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_150876_a(EntityPlayerMP p_150876_1_) {
/* 223 */     int var2 = this.field_150890_c.getTickCounter();
/* 224 */     HashMap<StatBase, Integer> var3 = Maps.newHashMap();
/*     */     
/* 226 */     if (this.field_150886_g || var2 - this.field_150885_f > 300) {
/*     */       
/* 228 */       this.field_150885_f = var2;
/* 229 */       Iterator<StatBase> var4 = func_150878_c().iterator();
/*     */       
/* 231 */       while (var4.hasNext()) {
/*     */         
/* 233 */         StatBase var5 = var4.next();
/* 234 */         var3.put(var5, Integer.valueOf(writeStat(var5)));
/*     */       } 
/*     */     } 
/*     */     
/* 238 */     p_150876_1_.playerNetServerHandler.sendPacket((Packet)new S37PacketStatistics(var3));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_150884_b(EntityPlayerMP p_150884_1_) {
/* 243 */     HashMap<Achievement, Integer> var2 = Maps.newHashMap();
/* 244 */     Iterator<Achievement> var3 = AchievementList.achievementList.iterator();
/*     */     
/* 246 */     while (var3.hasNext()) {
/*     */       
/* 248 */       Achievement var4 = var3.next();
/*     */       
/* 250 */       if (hasAchievementUnlocked(var4)) {
/*     */         
/* 252 */         var2.put(var4, Integer.valueOf(writeStat(var4)));
/* 253 */         this.field_150888_e.remove(var4);
/*     */       } 
/*     */     } 
/*     */     
/* 257 */     p_150884_1_.playerNetServerHandler.sendPacket((Packet)new S37PacketStatistics(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_150879_e() {
/* 262 */     return this.field_150886_g;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\stats\StatisticsFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */