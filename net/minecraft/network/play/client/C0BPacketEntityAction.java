/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ public class C0BPacketEntityAction
/*    */   implements Packet
/*    */ {
/*    */   private int field_149517_a;
/*    */   private Action field_149515_b;
/*    */   private int field_149516_c;
/*    */   private static final String __OBFID = "CL_00001366";
/*    */   
/*    */   public C0BPacketEntityAction() {}
/*    */   
/*    */   public C0BPacketEntityAction(Entity p_i45937_1_, Action p_i45937_2_) {
/* 21 */     this(p_i45937_1_, p_i45937_2_, 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public C0BPacketEntityAction(Entity p_i45938_1_, Action p_i45938_2_, int p_i45938_3_) {
/* 26 */     this.field_149517_a = p_i45938_1_.getEntityId();
/* 27 */     this.field_149515_b = p_i45938_2_;
/* 28 */     this.field_149516_c = p_i45938_3_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 36 */     this.field_149517_a = data.readVarIntFromBuffer();
/* 37 */     this.field_149515_b = (Action)data.readEnumValue(Action.class);
/* 38 */     this.field_149516_c = data.readVarIntFromBuffer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 46 */     data.writeVarIntToBuffer(this.field_149517_a);
/* 47 */     data.writeEnumValue(this.field_149515_b);
/* 48 */     data.writeVarIntToBuffer(this.field_149516_c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180765_a(INetHandlerPlayServer p_180765_1_) {
/* 53 */     p_180765_1_.processEntityAction(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Action func_180764_b() {
/* 58 */     return this.field_149515_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int func_149512_e() {
/* 63 */     return this.field_149516_c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 71 */     func_180765_a((INetHandlerPlayServer)handler);
/*    */   }
/*    */   
/*    */   public enum Action
/*    */   {
/* 76 */     START_SNEAKING("START_SNEAKING", 0),
/* 77 */     STOP_SNEAKING("STOP_SNEAKING", 1),
/* 78 */     STOP_SLEEPING("STOP_SLEEPING", 2),
/* 79 */     START_SPRINTING("START_SPRINTING", 3),
/* 80 */     STOP_SPRINTING("STOP_SPRINTING", 4),
/* 81 */     RIDING_JUMP("RIDING_JUMP", 5),
/* 82 */     OPEN_INVENTORY("OPEN_INVENTORY", 6);
/*    */     
/* 84 */     private static final Action[] $VALUES = new Action[] { START_SNEAKING, STOP_SNEAKING, STOP_SLEEPING, START_SPRINTING, STOP_SPRINTING, RIDING_JUMP, OPEN_INVENTORY };
/*    */     private static final String __OBFID = "CL_00002283";
/*    */     
/*    */     static {
/*    */     
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C0BPacketEntityAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */