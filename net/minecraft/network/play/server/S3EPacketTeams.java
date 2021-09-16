/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.scoreboard.ScorePlayerTeam;
/*     */ import net.minecraft.scoreboard.Team;
/*     */ 
/*     */ public class S3EPacketTeams
/*     */   implements Packet {
/*  16 */   private String field_149320_a = "";
/*  17 */   private String field_149318_b = "";
/*  18 */   private String field_149319_c = "";
/*  19 */   private String field_149316_d = "";
/*     */   
/*     */   private String field_179816_e;
/*     */   private int field_179815_f;
/*     */   private Collection field_149317_e;
/*     */   private int field_149314_f;
/*     */   private int field_149315_g;
/*     */   private static final String __OBFID = "CL_00001334";
/*     */   
/*     */   public S3EPacketTeams() {
/*  29 */     this.field_179816_e = Team.EnumVisible.ALWAYS.field_178830_e;
/*  30 */     this.field_179815_f = -1;
/*  31 */     this.field_149317_e = Lists.newArrayList();
/*     */   }
/*     */ 
/*     */   
/*     */   public S3EPacketTeams(ScorePlayerTeam p_i45225_1_, int p_i45225_2_) {
/*  36 */     this.field_179816_e = Team.EnumVisible.ALWAYS.field_178830_e;
/*  37 */     this.field_179815_f = -1;
/*  38 */     this.field_149317_e = Lists.newArrayList();
/*  39 */     this.field_149320_a = p_i45225_1_.getRegisteredName();
/*  40 */     this.field_149314_f = p_i45225_2_;
/*     */     
/*  42 */     if (p_i45225_2_ == 0 || p_i45225_2_ == 2) {
/*     */       
/*  44 */       this.field_149318_b = p_i45225_1_.func_96669_c();
/*  45 */       this.field_149319_c = p_i45225_1_.getColorPrefix();
/*  46 */       this.field_149316_d = p_i45225_1_.getColorSuffix();
/*  47 */       this.field_149315_g = p_i45225_1_.func_98299_i();
/*  48 */       this.field_179816_e = (p_i45225_1_.func_178770_i()).field_178830_e;
/*  49 */       this.field_179815_f = p_i45225_1_.func_178775_l().func_175746_b();
/*     */     } 
/*     */     
/*  52 */     if (p_i45225_2_ == 0)
/*     */     {
/*  54 */       this.field_149317_e.addAll(p_i45225_1_.getMembershipCollection());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public S3EPacketTeams(ScorePlayerTeam p_i45226_1_, Collection p_i45226_2_, int p_i45226_3_) {
/*  60 */     this.field_179816_e = Team.EnumVisible.ALWAYS.field_178830_e;
/*  61 */     this.field_179815_f = -1;
/*  62 */     this.field_149317_e = Lists.newArrayList();
/*     */     
/*  64 */     if (p_i45226_3_ != 3 && p_i45226_3_ != 4)
/*     */     {
/*  66 */       throw new IllegalArgumentException("Method must be join or leave for player constructor");
/*     */     }
/*  68 */     if (p_i45226_2_ != null && !p_i45226_2_.isEmpty()) {
/*     */       
/*  70 */       this.field_149314_f = p_i45226_3_;
/*  71 */       this.field_149320_a = p_i45226_1_.getRegisteredName();
/*  72 */       this.field_149317_e.addAll(p_i45226_2_);
/*     */     }
/*     */     else {
/*     */       
/*  76 */       throw new IllegalArgumentException("Players cannot be null/empty");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  85 */     this.field_149320_a = data.readStringFromBuffer(16);
/*  86 */     this.field_149314_f = data.readByte();
/*     */     
/*  88 */     if (this.field_149314_f == 0 || this.field_149314_f == 2) {
/*     */       
/*  90 */       this.field_149318_b = data.readStringFromBuffer(32);
/*  91 */       this.field_149319_c = data.readStringFromBuffer(16);
/*  92 */       this.field_149316_d = data.readStringFromBuffer(16);
/*  93 */       this.field_149315_g = data.readByte();
/*  94 */       this.field_179816_e = data.readStringFromBuffer(32);
/*  95 */       this.field_179815_f = data.readByte();
/*     */     } 
/*     */     
/*  98 */     if (this.field_149314_f == 0 || this.field_149314_f == 3 || this.field_149314_f == 4) {
/*     */       
/* 100 */       int var2 = data.readVarIntFromBuffer();
/*     */       
/* 102 */       for (int var3 = 0; var3 < var2; var3++)
/*     */       {
/* 104 */         this.field_149317_e.add(data.readStringFromBuffer(40));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/* 114 */     data.writeString(this.field_149320_a);
/* 115 */     data.writeByte(this.field_149314_f);
/*     */     
/* 117 */     if (this.field_149314_f == 0 || this.field_149314_f == 2) {
/*     */       
/* 119 */       data.writeString(this.field_149318_b);
/* 120 */       data.writeString(this.field_149319_c);
/* 121 */       data.writeString(this.field_149316_d);
/* 122 */       data.writeByte(this.field_149315_g);
/* 123 */       data.writeString(this.field_179816_e);
/* 124 */       data.writeByte(this.field_179815_f);
/*     */     } 
/*     */     
/* 127 */     if (this.field_149314_f == 0 || this.field_149314_f == 3 || this.field_149314_f == 4) {
/*     */       
/* 129 */       data.writeVarIntToBuffer(this.field_149317_e.size());
/* 130 */       Iterator<String> var2 = this.field_149317_e.iterator();
/*     */       
/* 132 */       while (var2.hasNext()) {
/*     */         
/* 134 */         String var3 = var2.next();
/* 135 */         data.writeString(var3);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandlerPlayClient handler) {
/* 145 */     handler.handleTeams(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_149312_c() {
/* 150 */     return this.field_149320_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_149306_d() {
/* 155 */     return this.field_149318_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_149311_e() {
/* 160 */     return this.field_149319_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_149309_f() {
/* 165 */     return this.field_149316_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection func_149310_g() {
/* 170 */     return this.field_149317_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149307_h() {
/* 175 */     return this.field_149314_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149308_i() {
/* 180 */     return this.field_149315_g;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_179813_h() {
/* 185 */     return this.field_179815_f;
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_179814_i() {
/* 190 */     return this.field_179816_e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 198 */     processPacket((INetHandlerPlayClient)handler);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S3EPacketTeams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */