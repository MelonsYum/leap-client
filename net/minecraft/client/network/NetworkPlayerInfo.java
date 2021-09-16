/*     */ package net.minecraft.client.network;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*     */ import net.minecraft.client.resources.SkinManager;
/*     */ import net.minecraft.network.play.server.S38PacketPlayerListItem;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NetworkPlayerInfo
/*     */ {
/*     */   private final GameProfile field_178867_a;
/*     */   private WorldSettings.GameType gameType;
/*     */   public int responseTime;
/*     */   private boolean field_178864_d = false;
/*     */   private ResourceLocation field_178865_e;
/*     */   private ResourceLocation field_178862_f;
/*     */   private String field_178863_g;
/*     */   private IChatComponent field_178872_h;
/*  28 */   private int field_178873_i = 0;
/*  29 */   private int field_178870_j = 0;
/*  30 */   private long field_178871_k = 0L;
/*  31 */   private long field_178868_l = 0L;
/*  32 */   private long field_178869_m = 0L;
/*     */   
/*     */   private static final String __OBFID = "CL_00000888";
/*     */   
/*     */   public NetworkPlayerInfo(GameProfile p_i46294_1_) {
/*  37 */     this.field_178867_a = p_i46294_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public NetworkPlayerInfo(S38PacketPlayerListItem.AddPlayerData p_i46295_1_) {
/*  42 */     this.field_178867_a = p_i46295_1_.func_179962_a();
/*  43 */     this.gameType = p_i46295_1_.func_179960_c();
/*  44 */     this.responseTime = p_i46295_1_.func_179963_b();
/*     */   }
/*     */ 
/*     */   
/*     */   public GameProfile func_178845_a() {
/*  49 */     return this.field_178867_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldSettings.GameType getGameType() {
/*  54 */     return this.gameType;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getResponseTime() {
/*  59 */     return this.responseTime;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_178839_a(WorldSettings.GameType p_178839_1_) {
/*  64 */     this.gameType = p_178839_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_178838_a(int p_178838_1_) {
/*  69 */     this.responseTime = p_178838_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_178856_e() {
/*  74 */     return (this.field_178865_e != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_178851_f() {
/*  79 */     return (this.field_178863_g == null) ? DefaultPlayerSkin.func_177332_b(this.field_178867_a.getId()) : this.field_178863_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation func_178837_g() {
/*  84 */     if (this.field_178865_e == null)
/*     */     {
/*  86 */       func_178841_j();
/*     */     }
/*     */     
/*  89 */     return (ResourceLocation)Objects.firstNonNull(this.field_178865_e, DefaultPlayerSkin.func_177334_a(this.field_178867_a.getId()));
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation func_178861_h() {
/*  94 */     if (this.field_178862_f == null)
/*     */     {
/*  96 */       func_178841_j();
/*     */     }
/*     */     
/*  99 */     return this.field_178862_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public ScorePlayerTeam func_178850_i() {
/* 104 */     return (Minecraft.getMinecraft()).theWorld.getScoreboard().getPlayersTeam(func_178845_a().getName());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_178841_j() {
/* 109 */     synchronized (this) {
/*     */       
/* 111 */       if (!this.field_178864_d) {
/*     */         
/* 113 */         this.field_178864_d = true;
/* 114 */         Minecraft.getMinecraft().getSkinManager().func_152790_a(this.field_178867_a, new SkinManager.SkinAvailableCallback()
/*     */             {
/*     */               
/*     */               public void func_180521_a(MinecraftProfileTexture.Type p_180521_1_, ResourceLocation p_180521_2_, MinecraftProfileTexture p_180521_3_)
/*     */               {
/* 119 */                 switch (NetworkPlayerInfo.SwitchType.field_178875_a[p_180521_1_.ordinal()]) {
/*     */                   
/*     */                   case 1:
/* 122 */                     NetworkPlayerInfo.this.field_178865_e = p_180521_2_;
/* 123 */                     NetworkPlayerInfo.this.field_178863_g = p_180521_3_.getMetadata("model");
/*     */                     
/* 125 */                     if (NetworkPlayerInfo.this.field_178863_g == null)
/*     */                     {
/* 127 */                       NetworkPlayerInfo.this.field_178863_g = "default";
/*     */                     }
/*     */                     break;
/*     */ 
/*     */                   
/*     */                   case 2:
/* 133 */                     NetworkPlayerInfo.this.field_178862_f = p_180521_2_;
/*     */                     break;
/*     */                 }  } private static final String __OBFID = "CL_00002619";
/* 136 */             },  true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178859_a(IChatComponent p_178859_1_) {
/* 143 */     this.field_178872_h = p_178859_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent func_178854_k() {
/* 148 */     return this.field_178872_h;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178835_l() {
/* 153 */     return this.field_178873_i;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178836_b(int p_178836_1_) {
/* 158 */     this.field_178873_i = p_178836_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_178860_m() {
/* 163 */     return this.field_178870_j;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178857_c(int p_178857_1_) {
/* 168 */     this.field_178870_j = p_178857_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public long func_178847_n() {
/* 173 */     return this.field_178871_k;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178846_a(long p_178846_1_) {
/* 178 */     this.field_178871_k = p_178846_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public long func_178858_o() {
/* 183 */     return this.field_178868_l;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178844_b(long p_178844_1_) {
/* 188 */     this.field_178868_l = p_178844_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public long func_178855_p() {
/* 193 */     return this.field_178869_m;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_178843_c(long p_178843_1_) {
/* 198 */     this.field_178869_m = p_178843_1_;
/*     */   }
/*     */   
/*     */   static final class SwitchType
/*     */   {
/* 203 */     static final int[] field_178875_a = new int[(MinecraftProfileTexture.Type.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002618";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 210 */         field_178875_a[MinecraftProfileTexture.Type.SKIN.ordinal()] = 1;
/*     */       }
/* 212 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 219 */         field_178875_a[MinecraftProfileTexture.Type.CAPE.ordinal()] = 2;
/*     */       }
/* 221 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\network\NetworkPlayerInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */