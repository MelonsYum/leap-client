/*     */ package net.minecraft.network.play.server;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.INetHandlerPlayClient;
/*     */ import net.minecraft.world.border.WorldBorder;
/*     */ 
/*     */ public class S44PacketWorldBorder
/*     */   implements Packet
/*     */ {
/*     */   private Action field_179795_a;
/*     */   private int field_179793_b;
/*     */   private double field_179794_c;
/*     */   private double field_179791_d;
/*     */   private double field_179792_e;
/*     */   private double field_179789_f;
/*     */   private long field_179790_g;
/*     */   private int field_179796_h;
/*     */   private int field_179797_i;
/*     */   private static final String __OBFID = "CL_00002292";
/*     */   
/*     */   public S44PacketWorldBorder() {}
/*     */   
/*     */   public S44PacketWorldBorder(WorldBorder p_i45962_1_, Action p_i45962_2_) {
/*  27 */     this.field_179795_a = p_i45962_2_;
/*  28 */     this.field_179794_c = p_i45962_1_.getCenterX();
/*  29 */     this.field_179791_d = p_i45962_1_.getCenterZ();
/*  30 */     this.field_179789_f = p_i45962_1_.getDiameter();
/*  31 */     this.field_179792_e = p_i45962_1_.getTargetSize();
/*  32 */     this.field_179790_g = p_i45962_1_.getTimeUntilTarget();
/*  33 */     this.field_179793_b = p_i45962_1_.getSize();
/*  34 */     this.field_179797_i = p_i45962_1_.getWarningDistance();
/*  35 */     this.field_179796_h = p_i45962_1_.getWarningTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(PacketBuffer data) throws IOException {
/*  43 */     this.field_179795_a = (Action)data.readEnumValue(Action.class);
/*     */     
/*  45 */     switch (SwitchAction.field_179947_a[this.field_179795_a.ordinal()]) {
/*     */       
/*     */       case 1:
/*  48 */         this.field_179792_e = data.readDouble();
/*     */         break;
/*     */       
/*     */       case 2:
/*  52 */         this.field_179789_f = data.readDouble();
/*  53 */         this.field_179792_e = data.readDouble();
/*  54 */         this.field_179790_g = data.readVarLong();
/*     */         break;
/*     */       
/*     */       case 3:
/*  58 */         this.field_179794_c = data.readDouble();
/*  59 */         this.field_179791_d = data.readDouble();
/*     */         break;
/*     */       
/*     */       case 4:
/*  63 */         this.field_179797_i = data.readVarIntFromBuffer();
/*     */         break;
/*     */       
/*     */       case 5:
/*  67 */         this.field_179796_h = data.readVarIntFromBuffer();
/*     */         break;
/*     */       
/*     */       case 6:
/*  71 */         this.field_179794_c = data.readDouble();
/*  72 */         this.field_179791_d = data.readDouble();
/*  73 */         this.field_179789_f = data.readDouble();
/*  74 */         this.field_179792_e = data.readDouble();
/*  75 */         this.field_179790_g = data.readVarLong();
/*  76 */         this.field_179793_b = data.readVarIntFromBuffer();
/*  77 */         this.field_179797_i = data.readVarIntFromBuffer();
/*  78 */         this.field_179796_h = data.readVarIntFromBuffer();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(PacketBuffer data) throws IOException {
/*  87 */     data.writeEnumValue(this.field_179795_a);
/*     */     
/*  89 */     switch (SwitchAction.field_179947_a[this.field_179795_a.ordinal()]) {
/*     */       
/*     */       case 1:
/*  92 */         data.writeDouble(this.field_179792_e);
/*     */         break;
/*     */       
/*     */       case 2:
/*  96 */         data.writeDouble(this.field_179789_f);
/*  97 */         data.writeDouble(this.field_179792_e);
/*  98 */         data.writeVarLong(this.field_179790_g);
/*     */         break;
/*     */       
/*     */       case 3:
/* 102 */         data.writeDouble(this.field_179794_c);
/* 103 */         data.writeDouble(this.field_179791_d);
/*     */         break;
/*     */       
/*     */       case 4:
/* 107 */         data.writeVarIntToBuffer(this.field_179797_i);
/*     */         break;
/*     */       
/*     */       case 5:
/* 111 */         data.writeVarIntToBuffer(this.field_179796_h);
/*     */         break;
/*     */       
/*     */       case 6:
/* 115 */         data.writeDouble(this.field_179794_c);
/* 116 */         data.writeDouble(this.field_179791_d);
/* 117 */         data.writeDouble(this.field_179789_f);
/* 118 */         data.writeDouble(this.field_179792_e);
/* 119 */         data.writeVarLong(this.field_179790_g);
/* 120 */         data.writeVarIntToBuffer(this.field_179793_b);
/* 121 */         data.writeVarIntToBuffer(this.field_179797_i);
/* 122 */         data.writeVarIntToBuffer(this.field_179796_h);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_179787_a(INetHandlerPlayClient p_179787_1_) {
/* 128 */     p_179787_1_.func_175093_a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_179788_a(WorldBorder p_179788_1_) {
/* 133 */     switch (SwitchAction.field_179947_a[this.field_179795_a.ordinal()]) {
/*     */       
/*     */       case 1:
/* 136 */         p_179788_1_.setTransition(this.field_179792_e);
/*     */         break;
/*     */       
/*     */       case 2:
/* 140 */         p_179788_1_.setTransition(this.field_179789_f, this.field_179792_e, this.field_179790_g);
/*     */         break;
/*     */       
/*     */       case 3:
/* 144 */         p_179788_1_.setCenter(this.field_179794_c, this.field_179791_d);
/*     */         break;
/*     */       
/*     */       case 4:
/* 148 */         p_179788_1_.setWarningDistance(this.field_179797_i);
/*     */         break;
/*     */       
/*     */       case 5:
/* 152 */         p_179788_1_.setWarningTime(this.field_179796_h);
/*     */         break;
/*     */       
/*     */       case 6:
/* 156 */         p_179788_1_.setCenter(this.field_179794_c, this.field_179791_d);
/*     */         
/* 158 */         if (this.field_179790_g > 0L) {
/*     */           
/* 160 */           p_179788_1_.setTransition(this.field_179789_f, this.field_179792_e, this.field_179790_g);
/*     */         }
/*     */         else {
/*     */           
/* 164 */           p_179788_1_.setTransition(this.field_179792_e);
/*     */         } 
/*     */         
/* 167 */         p_179788_1_.setSize(this.field_179793_b);
/* 168 */         p_179788_1_.setWarningDistance(this.field_179797_i);
/* 169 */         p_179788_1_.setWarningTime(this.field_179796_h);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(INetHandler handler) {
/* 178 */     func_179787_a((INetHandlerPlayClient)handler);
/*     */   }
/*     */   
/*     */   public enum Action
/*     */   {
/* 183 */     SET_SIZE("SET_SIZE", 0),
/* 184 */     LERP_SIZE("LERP_SIZE", 1),
/* 185 */     SET_CENTER("SET_CENTER", 2),
/* 186 */     INITIALIZE("INITIALIZE", 3),
/* 187 */     SET_WARNING_TIME("SET_WARNING_TIME", 4),
/* 188 */     SET_WARNING_BLOCKS("SET_WARNING_BLOCKS", 5);
/*     */     
/* 190 */     private static final Action[] $VALUES = new Action[] { SET_SIZE, LERP_SIZE, SET_CENTER, INITIALIZE, SET_WARNING_TIME, SET_WARNING_BLOCKS };
/*     */     private static final String __OBFID = "CL_00002290";
/*     */     
/*     */     static {
/*     */     
/*     */     } }
/*     */   
/*     */   static final class SwitchAction {
/* 198 */     static final int[] field_179947_a = new int[(S44PacketWorldBorder.Action.values()).length];
/*     */     
/*     */     private static final String __OBFID = "CL_00002291";
/*     */ 
/*     */     
/*     */     static {
/*     */       try {
/* 205 */         field_179947_a[S44PacketWorldBorder.Action.SET_SIZE.ordinal()] = 1;
/*     */       }
/* 207 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 214 */         field_179947_a[S44PacketWorldBorder.Action.LERP_SIZE.ordinal()] = 2;
/*     */       }
/* 216 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 223 */         field_179947_a[S44PacketWorldBorder.Action.SET_CENTER.ordinal()] = 3;
/*     */       }
/* 225 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 232 */         field_179947_a[S44PacketWorldBorder.Action.SET_WARNING_BLOCKS.ordinal()] = 4;
/*     */       }
/* 234 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 241 */         field_179947_a[S44PacketWorldBorder.Action.SET_WARNING_TIME.ordinal()] = 5;
/*     */       }
/* 243 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 250 */         field_179947_a[S44PacketWorldBorder.Action.INITIALIZE.ordinal()] = 6;
/*     */       }
/* 252 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S44PacketWorldBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */