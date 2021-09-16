/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.EnumSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ 
/*     */ public class S08PacketPlayerPosLook
/*     */   implements Packet
/*     */ {
/*     */   public static double field_148940_a;
/*     */   public static double field_148938_b;
/*     */   public static double field_148939_c;
/*     */   public static float field_148936_d;
/*     */   public static float field_148937_e;
/*     */   public static Set field_179835_f;
/*     */   private static final String __OBFID = "CL_00001273";
/*     */   
/*     */   public S08PacketPlayerPosLook() {}
/*     */   
/*     */   public S08PacketPlayerPosLook(double p_i45993_1_, double p_i45993_3_, double p_i45993_5_, float p_i45993_7_, float p_i45993_8_, Set p_i45993_9_) {
/*  26 */     field_148940_a = p_i45993_1_;
/*  27 */     field_148938_b = p_i45993_3_;
/*  28 */     field_148939_c = p_i45993_5_;
/*  29 */     field_148936_d = p_i45993_7_;
/*  30 */     field_148937_e = p_i45993_8_;
/*  31 */     field_179835_f = p_i45993_9_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  39 */     field_148940_a = data.readDouble();
/*  40 */     field_148938_b = data.readDouble();
/*  41 */     field_148939_c = data.readDouble();
/*  42 */     field_148936_d = data.readFloat();
/*  43 */     field_148937_e = data.readFloat();
/*  44 */     field_179835_f = EnumFlags.func_180053_a(data.readUnsignedByte());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  52 */     data.writeDouble(field_148940_a);
/*  53 */     data.writeDouble(field_148938_b);
/*  54 */     data.writeDouble(field_148939_c);
/*  55 */     data.writeFloat(field_148936_d);
/*  56 */     data.writeFloat(field_148937_e);
/*  57 */     data.writeByte(EnumFlags.func_180056_a(field_179835_f));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180718_a(INetHandlerPlayClient p_180718_1_) {
/*  62 */     p_180718_1_.handlePlayerPosLook(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_148932_c() {
/*  67 */     return field_148940_a;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_148928_d() {
/*  72 */     return field_148938_b;
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_148933_e() {
/*  77 */     return field_148939_c;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_148931_f() {
/*  82 */     return field_148936_d;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_148930_g() {
/*  87 */     return field_148937_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set func_179834_f() {
/*  92 */     return field_179835_f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 100 */     func_180718_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */   
/*     */   public enum EnumFlags
/*     */   {
/* 105 */     X("X", 0, 0),
/* 106 */     Y("Y", 1, 1),
/* 107 */     Z("Z", 2, 2),
/* 108 */     Y_ROT("Y_ROT", 3, 3),
/* 109 */     X_ROT("X_ROT", 4, 4);
/*     */     
/*     */     private int field_180058_f;
/* 112 */     private static final EnumFlags[] $VALUES = new EnumFlags[] { X, Y, Z, Y_ROT, X_ROT };
/*     */     
/*     */     private static final String __OBFID = "CL_00002304";
/*     */     
/*     */     EnumFlags(String p_i45992_1_, int p_i45992_2_, int p_i45992_3_) {
/* 117 */       this.field_180058_f = p_i45992_3_;
/*     */     } static {
/*     */     
/*     */     }
/*     */     private int func_180055_a() {
/* 122 */       return 1 << this.field_180058_f;
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean func_180054_b(int p_180054_1_) {
/* 127 */       return ((p_180054_1_ & func_180055_a()) == func_180055_a());
/*     */     }
/*     */ 
/*     */     
/*     */     public static Set func_180053_a(int p_180053_0_) {
/* 132 */       EnumSet<EnumFlags> var1 = EnumSet.noneOf(EnumFlags.class);
/* 133 */       EnumFlags[] var2 = values();
/* 134 */       int var3 = var2.length;
/*     */       
/* 136 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/* 138 */         EnumFlags var5 = var2[var4];
/*     */         
/* 140 */         if (var5.func_180054_b(p_180053_0_))
/*     */         {
/* 142 */           var1.add(var5);
/*     */         }
/*     */       } 
/*     */       
/* 146 */       return var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public static int func_180056_a(Set p_180056_0_) {
/* 151 */       int var1 = 0;
/*     */ 
/*     */       
/* 154 */       for (Iterator<EnumFlags> var2 = p_180056_0_.iterator(); var2.hasNext(); var1 |= var3.func_180055_a())
/*     */       {
/* 156 */         EnumFlags var3 = var2.next();
/*     */       }
/*     */       
/* 159 */       return var1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S08PacketPlayerPosLook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */