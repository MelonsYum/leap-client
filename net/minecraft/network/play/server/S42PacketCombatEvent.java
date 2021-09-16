/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.util.CombatTracker;
/*     */ 
/*     */ public class S42PacketCombatEvent
/*     */   implements Packet
/*     */ {
/*     */   public Event field_179776_a;
/*     */   public int field_179774_b;
/*     */   public int field_179775_c;
/*     */   public int field_179772_d;
/*     */   public String field_179773_e;
/*     */   private static final String __OBFID = "CL_00002299";
/*     */   
/*     */   public S42PacketCombatEvent() {}
/*     */   
/*     */   public S42PacketCombatEvent(CombatTracker p_i45970_1_, Event p_i45970_2_) {
/*  24 */     this.field_179776_a = p_i45970_2_;
/*  25 */     EntityLivingBase var3 = p_i45970_1_.func_94550_c();
/*     */     
/*  27 */     switch (SwitchEvent.field_179944_a[p_i45970_2_.ordinal()]) {
/*     */       
/*     */       case 1:
/*  30 */         this.field_179772_d = p_i45970_1_.func_180134_f();
/*  31 */         this.field_179775_c = (var3 == null) ? -1 : var3.getEntityId();
/*     */         break;
/*     */       
/*     */       case 2:
/*  35 */         this.field_179774_b = p_i45970_1_.func_180135_h().getEntityId();
/*  36 */         this.field_179775_c = (var3 == null) ? -1 : var3.getEntityId();
/*  37 */         this.field_179773_e = p_i45970_1_.func_151521_b().getUnformattedText();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  46 */     this.field_179776_a = (Event)data.readEnumValue(Event.class);
/*     */     
/*  48 */     if (this.field_179776_a == Event.END_COMBAT) {
/*     */       
/*  50 */       this.field_179772_d = data.readVarIntFromBuffer();
/*  51 */       this.field_179775_c = data.readInt();
/*     */     }
/*  53 */     else if (this.field_179776_a == Event.ENTITY_DIED) {
/*     */       
/*  55 */       this.field_179774_b = data.readVarIntFromBuffer();
/*  56 */       this.field_179775_c = data.readInt();
/*  57 */       this.field_179773_e = data.readStringFromBuffer(32767);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  66 */     data.writeEnumValue(this.field_179776_a);
/*     */     
/*  68 */     if (this.field_179776_a == Event.END_COMBAT) {
/*     */       
/*  70 */       data.writeVarIntToBuffer(this.field_179772_d);
/*  71 */       data.writeInt(this.field_179775_c);
/*     */     }
/*  73 */     else if (this.field_179776_a == Event.ENTITY_DIED) {
/*     */       
/*  75 */       data.writeVarIntToBuffer(this.field_179774_b);
/*  76 */       data.writeInt(this.field_179775_c);
/*  77 */       data.writeString(this.field_179773_e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179771_a(INetHandlerPlayClient p_179771_1_) {
/*  83 */     p_179771_1_.func_175098_a(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/*  91 */     func_179771_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */   
/*     */   public enum Event
/*     */   {
/*  96 */     ENTER_COMBAT("ENTER_COMBAT", 0),
/*  97 */     END_COMBAT("END_COMBAT", 1),
/*  98 */     ENTITY_DIED("ENTITY_DIED", 2);
/*     */     
/* 100 */     private static final Event[] $VALUES = new Event[] { ENTER_COMBAT, END_COMBAT, ENTITY_DIED };
/*     */     private static final String __OBFID = "CL_00002297";
/*     */     
/*     */     static {
/*     */     
/*     */     } }
/*     */   
/*     */   static final class SwitchEvent {
/* 108 */     static final int[] field_179944_a = new int[(S42PacketCombatEvent.Event.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002298";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 115 */         field_179944_a[S42PacketCombatEvent.Event.END_COMBAT.ordinal()] = 1;
/*     */       }
/* 117 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 124 */         field_179944_a[S42PacketCombatEvent.Event.ENTITY_DIED.ordinal()] = 2;
/*     */       }
/* 126 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S42PacketCombatEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */