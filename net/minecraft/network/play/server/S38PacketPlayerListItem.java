/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ 
/*     */ public class S38PacketPlayerListItem
/*     */   implements Packet {
/*     */   private Action field_179770_a;
/*  20 */   private final List field_179769_b = Lists.newArrayList();
/*     */   
/*     */   private static final String __OBFID = "CL_00001318";
/*     */   
/*     */   public S38PacketPlayerListItem() {}
/*     */   
/*     */   public S38PacketPlayerListItem(Action p_i45967_1_, EntityPlayerMP... p_i45967_2_) {
/*  27 */     this.field_179770_a = p_i45967_1_;
/*  28 */     EntityPlayerMP[] var3 = p_i45967_2_;
/*  29 */     int var4 = p_i45967_2_.length;
/*     */     
/*  31 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/*  33 */       EntityPlayerMP var6 = var3[var5];
/*  34 */       this.field_179769_b.add(new AddPlayerData(var6.getGameProfile(), var6.ping, var6.theItemInWorldManager.getGameType(), var6.func_175396_E()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public S38PacketPlayerListItem(Action p_i45968_1_, Iterable p_i45968_2_) {
/*  40 */     this.field_179770_a = p_i45968_1_;
/*  41 */     Iterator<EntityPlayerMP> var3 = p_i45968_2_.iterator();
/*     */     
/*  43 */     while (var3.hasNext()) {
/*     */       
/*  45 */       EntityPlayerMP var4 = var3.next();
/*  46 */       this.field_179769_b.add(new AddPlayerData(var4.getGameProfile(), var4.ping, var4.theItemInWorldManager.getGameType(), var4.func_175396_E()));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  55 */     this.field_179770_a = (Action)data.readEnumValue(Action.class);
/*  56 */     int var2 = data.readVarIntFromBuffer();
/*     */     
/*  58 */     for (int var3 = 0; var3 < var2; var3++) {
/*     */       int var8, var9;
/*  60 */       GameProfile var4 = null;
/*  61 */       int var5 = 0;
/*  62 */       WorldSettings.GameType var6 = null;
/*  63 */       IChatComponent var7 = null;
/*     */       
/*  65 */       switch (SwitchAction.field_179938_a[this.field_179770_a.ordinal()]) {
/*     */         
/*     */         case 1:
/*  68 */           var4 = new GameProfile(data.readUuid(), data.readStringFromBuffer(16));
/*  69 */           var8 = data.readVarIntFromBuffer();
/*     */           
/*  71 */           for (var9 = 0; var9 < var8; var9++) {
/*     */             
/*  73 */             String var10 = data.readStringFromBuffer(32767);
/*  74 */             String var11 = data.readStringFromBuffer(32767);
/*     */             
/*  76 */             if (data.readBoolean()) {
/*     */               
/*  78 */               var4.getProperties().put(var10, new Property(var10, var11, data.readStringFromBuffer(32767)));
/*     */             }
/*     */             else {
/*     */               
/*  82 */               var4.getProperties().put(var10, new Property(var10, var11));
/*     */             } 
/*     */           } 
/*     */           
/*  86 */           var6 = WorldSettings.GameType.getByID(data.readVarIntFromBuffer());
/*  87 */           var5 = data.readVarIntFromBuffer();
/*     */           
/*  89 */           if (data.readBoolean())
/*     */           {
/*  91 */             var7 = data.readChatComponent();
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/*  97 */           var4 = new GameProfile(data.readUuid(), null);
/*  98 */           var6 = WorldSettings.GameType.getByID(data.readVarIntFromBuffer());
/*     */           break;
/*     */         
/*     */         case 3:
/* 102 */           var4 = new GameProfile(data.readUuid(), null);
/* 103 */           var5 = data.readVarIntFromBuffer();
/*     */           break;
/*     */         
/*     */         case 4:
/* 107 */           var4 = new GameProfile(data.readUuid(), null);
/*     */           
/* 109 */           if (data.readBoolean())
/*     */           {
/* 111 */             var7 = data.readChatComponent();
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         case 5:
/* 117 */           var4 = new GameProfile(data.readUuid(), null);
/*     */           break;
/*     */       } 
/* 120 */       this.field_179769_b.add(new AddPlayerData(var4, var5, var6, var7));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/* 129 */     data.writeEnumValue(this.field_179770_a);
/* 130 */     data.writeVarIntToBuffer(this.field_179769_b.size());
/* 131 */     Iterator<AddPlayerData> var2 = this.field_179769_b.iterator();
/*     */     
/* 133 */     while (var2.hasNext()) {
/*     */       Iterator<Property> var4;
/* 135 */       AddPlayerData var3 = var2.next();
/*     */       
/* 137 */       switch (SwitchAction.field_179938_a[this.field_179770_a.ordinal()]) {
/*     */         
/*     */         case 1:
/* 140 */           data.writeUuid(var3.func_179962_a().getId());
/* 141 */           data.writeString(var3.func_179962_a().getName());
/* 142 */           data.writeVarIntToBuffer(var3.func_179962_a().getProperties().size());
/* 143 */           var4 = var3.func_179962_a().getProperties().values().iterator();
/*     */           
/* 145 */           while (var4.hasNext()) {
/*     */             
/* 147 */             Property var5 = var4.next();
/* 148 */             data.writeString(var5.getName());
/* 149 */             data.writeString(var5.getValue());
/*     */             
/* 151 */             if (var5.hasSignature()) {
/*     */               
/* 153 */               data.writeBoolean(true);
/* 154 */               data.writeString(var5.getSignature());
/*     */               
/*     */               continue;
/*     */             } 
/* 158 */             data.writeBoolean(false);
/*     */           } 
/*     */ 
/*     */           
/* 162 */           data.writeVarIntToBuffer(var3.func_179960_c().getID());
/* 163 */           data.writeVarIntToBuffer(var3.func_179963_b());
/*     */           
/* 165 */           if (var3.func_179961_d() == null) {
/*     */             
/* 167 */             data.writeBoolean(false);
/*     */             
/*     */             continue;
/*     */           } 
/* 171 */           data.writeBoolean(true);
/* 172 */           data.writeChatComponent(var3.func_179961_d());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 178 */           data.writeUuid(var3.func_179962_a().getId());
/* 179 */           data.writeVarIntToBuffer(var3.func_179960_c().getID());
/*     */ 
/*     */         
/*     */         case 3:
/* 183 */           data.writeUuid(var3.func_179962_a().getId());
/* 184 */           data.writeVarIntToBuffer(var3.func_179963_b());
/*     */ 
/*     */         
/*     */         case 4:
/* 188 */           data.writeUuid(var3.func_179962_a().getId());
/*     */           
/* 190 */           if (var3.func_179961_d() == null) {
/*     */             
/* 192 */             data.writeBoolean(false);
/*     */             
/*     */             continue;
/*     */           } 
/* 196 */           data.writeBoolean(true);
/* 197 */           data.writeChatComponent(var3.func_179961_d());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 5:
/* 203 */           data.writeUuid(var3.func_179962_a().getId());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180743_a(INetHandlerPlayClient p_180743_1_) {
/* 210 */     p_180743_1_.handlePlayerListItem(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public List func_179767_a() {
/* 215 */     return this.field_179769_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public Action func_179768_b() {
/* 220 */     return this.field_179770_a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 228 */     func_180743_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */   
/*     */   public enum Action
/*     */   {
/* 233 */     ADD_PLAYER("ADD_PLAYER", 0),
/* 234 */     UPDATE_GAME_MODE("UPDATE_GAME_MODE", 1),
/* 235 */     UPDATE_LATENCY("UPDATE_LATENCY", 2),
/* 236 */     UPDATE_DISPLAY_NAME("UPDATE_DISPLAY_NAME", 3),
/* 237 */     REMOVE_PLAYER("REMOVE_PLAYER", 4);
/*     */     
/* 239 */     private static final Action[] $VALUES = new Action[] { ADD_PLAYER, UPDATE_GAME_MODE, UPDATE_LATENCY, UPDATE_DISPLAY_NAME, REMOVE_PLAYER };
/*     */     private static final String __OBFID = "CL_00002295";
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */   }
/*     */   
/*     */   public class AddPlayerData {
/*     */     private final int field_179966_b;
/*     */     private final WorldSettings.GameType field_179967_c;
/*     */     private final GameProfile field_179964_d;
/*     */     private final IChatComponent field_179965_e;
/*     */     private static final String __OBFID = "CL_00002294";
/*     */     
/*     */     public AddPlayerData(GameProfile p_i45965_2_, int p_i45965_3_, WorldSettings.GameType p_i45965_4_, IChatComponent p_i45965_5_) {
/* 255 */       this.field_179964_d = p_i45965_2_;
/* 256 */       this.field_179966_b = p_i45965_3_;
/* 257 */       this.field_179967_c = p_i45965_4_;
/* 258 */       this.field_179965_e = p_i45965_5_;
/*     */     }
/*     */ 
/*     */     
/*     */     public GameProfile func_179962_a() {
/* 263 */       return this.field_179964_d;
/*     */     }
/*     */ 
/*     */     
/*     */     public int func_179963_b() {
/* 268 */       return this.field_179966_b;
/*     */     }
/*     */ 
/*     */     
/*     */     public WorldSettings.GameType func_179960_c() {
/* 273 */       return this.field_179967_c;
/*     */     }
/*     */ 
/*     */     
/*     */     public IChatComponent func_179961_d() {
/* 278 */       return this.field_179965_e;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class SwitchAction
/*     */   {
/* 284 */     static final int[] field_179938_a = new int[(S38PacketPlayerListItem.Action.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002296";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 291 */         field_179938_a[S38PacketPlayerListItem.Action.ADD_PLAYER.ordinal()] = 1;
/*     */       }
/* 293 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 300 */         field_179938_a[S38PacketPlayerListItem.Action.UPDATE_GAME_MODE.ordinal()] = 2;
/*     */       }
/* 302 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 309 */         field_179938_a[S38PacketPlayerListItem.Action.UPDATE_LATENCY.ordinal()] = 3;
/*     */       }
/* 311 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 318 */         field_179938_a[S38PacketPlayerListItem.Action.UPDATE_DISPLAY_NAME.ordinal()] = 4;
/*     */       }
/* 320 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 327 */         field_179938_a[S38PacketPlayerListItem.Action.REMOVE_PLAYER.ordinal()] = 5;
/*     */       }
/* 329 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S38PacketPlayerListItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */