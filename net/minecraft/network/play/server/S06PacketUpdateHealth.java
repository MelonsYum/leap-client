/*    */ package net.minecraft.network.play.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayClient;
/*    */ 
/*    */ public class S06PacketUpdateHealth
/*    */   implements Packet
/*    */ {
/*    */   private float health;
/*    */   private int foodLevel;
/*    */   private float saturationLevel;
/*    */   private static final String __OBFID = "CL_00001332";
/*    */   
/*    */   public S06PacketUpdateHealth() {}
/*    */   
/*    */   public S06PacketUpdateHealth(float healthIn, int foodLevelIn, float saturationIn) {
/* 20 */     this.health = healthIn;
/* 21 */     this.foodLevel = foodLevelIn;
/* 22 */     this.saturationLevel = saturationIn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 30 */     this.health = data.readFloat();
/* 31 */     this.foodLevel = data.readVarIntFromBuffer();
/* 32 */     this.saturationLevel = data.readFloat();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 40 */     data.writeFloat(this.health);
/* 41 */     data.writeVarIntToBuffer(this.foodLevel);
/* 42 */     data.writeFloat(this.saturationLevel);
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180750_a(INetHandlerPlayClient p_180750_1_) {
/* 47 */     p_180750_1_.handleUpdateHealth(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getHealth() {
/* 52 */     return this.health;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFoodLevel() {
/* 57 */     return this.foodLevel;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getSaturationLevel() {
/* 62 */     return this.saturationLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 70 */     func_180750_a((INetHandlerPlayClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\server\S06PacketUpdateHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */