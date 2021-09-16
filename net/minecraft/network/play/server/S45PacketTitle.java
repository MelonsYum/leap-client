/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ public class S45PacketTitle
/*     */   implements Packet
/*     */ {
/*     */   private Type field_179812_a;
/*     */   private IChatComponent field_179810_b;
/*     */   private int field_179811_c;
/*     */   private int field_179808_d;
/*     */   private int field_179809_e;
/*     */   private static final String __OBFID = "CL_00002287";
/*     */   
/*     */   public S45PacketTitle() {}
/*     */   
/*     */   public S45PacketTitle(Type p_i45953_1_, IChatComponent p_i45953_2_) {
/*  23 */     this(p_i45953_1_, p_i45953_2_, -1, -1, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public S45PacketTitle(int p_i45954_1_, int p_i45954_2_, int p_i45954_3_) {
/*  28 */     this(Type.TIMES, null, p_i45954_1_, p_i45954_2_, p_i45954_3_);
/*     */   }
/*     */ 
/*     */   
/*     */   public S45PacketTitle(Type p_i45955_1_, IChatComponent p_i45955_2_, int p_i45955_3_, int p_i45955_4_, int p_i45955_5_) {
/*  33 */     this.field_179812_a = p_i45955_1_;
/*  34 */     this.field_179810_b = p_i45955_2_;
/*  35 */     this.field_179811_c = p_i45955_3_;
/*  36 */     this.field_179808_d = p_i45955_4_;
/*  37 */     this.field_179809_e = p_i45955_5_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  45 */     this.field_179812_a = (Type)data.readEnumValue(Type.class);
/*     */     
/*  47 */     if (this.field_179812_a == Type.TITLE || this.field_179812_a == Type.SUBTITLE)
/*     */     {
/*  49 */       this.field_179810_b = data.readChatComponent();
/*     */     }
/*     */     
/*  52 */     if (this.field_179812_a == Type.TIMES) {
/*     */       
/*  54 */       this.field_179811_c = data.readInt();
/*  55 */       this.field_179808_d = data.readInt();
/*  56 */       this.field_179809_e = data.readInt();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  65 */     data.writeEnumValue(this.field_179812_a);
/*     */     
/*  67 */     if (this.field_179812_a == Type.TITLE || this.field_179812_a == Type.SUBTITLE)
/*     */     {
/*  69 */       data.writeChatComponent(this.field_179810_b);
/*     */     }
/*     */     
/*  72 */     if (this.field_179812_a == Type.TIMES) {
/*     */       
/*  74 */       data.writeInt(this.field_179811_c);
/*  75 */       data.writeInt(this.field_179808_d);
/*  76 */       data.writeInt(this.field_179809_e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179802_a(INetHandlerPlayClient p_179802_1_) {
/*  82 */     p_179802_1_.func_175099_a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Type func_179807_a() {
/*  87 */     return this.field_179812_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatComponent func_179805_b() {
/*  92 */     return this.field_179810_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_179806_c() {
/*  97 */     return this.field_179811_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_179804_d() {
/* 102 */     return this.field_179808_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_179803_e() {
/* 107 */     return this.field_179809_e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 115 */     func_179802_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */   
/*     */   public enum Type
/*     */   {
/* 120 */     TITLE("TITLE", 0),
/* 121 */     SUBTITLE("SUBTITLE", 1),
/* 122 */     TIMES("TIMES", 2),
/* 123 */     CLEAR("CLEAR", 3),
/* 124 */     RESET("RESET", 4);
/*     */     
/* 126 */     private static final Type[] $VALUES = new Type[] { TITLE, SUBTITLE, TIMES, CLEAR, RESET };
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00002286";
/*     */ 
/*     */     
/*     */     public static Type func_179969_a(String p_179969_0_) {
/* 133 */       Type[] var1 = values();
/* 134 */       int var2 = var1.length;
/*     */       
/* 136 */       for (int var3 = 0; var3 < var2; var3++) {
/*     */         
/* 138 */         Type var4 = var1[var3];
/*     */         
/* 140 */         if (var4.name().equalsIgnoreCase(p_179969_0_))
/*     */         {
/* 142 */           return var4;
/*     */         }
/*     */       } 
/*     */       
/* 146 */       return TITLE;
/*     */     } static {
/*     */     
/*     */     }
/*     */     public static String[] func_179971_a() {
/* 151 */       String[] var0 = new String[(values()).length];
/* 152 */       int var1 = 0;
/* 153 */       Type[] var2 = values();
/* 154 */       int var3 = var2.length;
/*     */       
/* 156 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/* 158 */         Type var5 = var2[var4];
/* 159 */         var0[var1++] = var5.name().toLowerCase();
/*     */       } 
/*     */       
/* 162 */       return var0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S45PacketTitle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */