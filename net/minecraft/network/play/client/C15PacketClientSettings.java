/*    */ package net.minecraft.network.play.client;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.play.INetHandlerPlayServer;
/*    */ 
/*    */ public class C15PacketClientSettings
/*    */   implements Packet
/*    */ {
/*    */   private String lang;
/*    */   private int view;
/*    */   private EntityPlayer.EnumChatVisibility chatVisibility;
/*    */   private boolean enableColors;
/*    */   private int field_179711_e;
/*    */   private static final String __OBFID = "CL_00001350";
/*    */   
/*    */   public C15PacketClientSettings() {}
/*    */   
/*    */   public C15PacketClientSettings(String p_i45946_1_, int p_i45946_2_, EntityPlayer.EnumChatVisibility p_i45946_3_, boolean p_i45946_4_, int p_i45946_5_) {
/* 23 */     this.lang = p_i45946_1_;
/* 24 */     this.view = p_i45946_2_;
/* 25 */     this.chatVisibility = p_i45946_3_;
/* 26 */     this.enableColors = p_i45946_4_;
/* 27 */     this.field_179711_e = p_i45946_5_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 35 */     this.lang = data.readStringFromBuffer(7);
/* 36 */     this.view = data.readByte();
/* 37 */     this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility(data.readByte());
/* 38 */     this.enableColors = data.readBoolean();
/* 39 */     this.field_179711_e = data.readUnsignedByte();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 47 */     data.writeString(this.lang);
/* 48 */     data.writeByte(this.view);
/* 49 */     data.writeByte(this.chatVisibility.getChatVisibility());
/* 50 */     data.writeBoolean(this.enableColors);
/* 51 */     data.writeByte(this.field_179711_e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandlerPlayServer handler) {
/* 59 */     handler.processClientSettings(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLang() {
/* 64 */     return this.lang;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPlayer.EnumChatVisibility getChatVisibility() {
/* 69 */     return this.chatVisibility;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isColorsEnabled() {
/* 74 */     return this.enableColors;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getView() {
/* 79 */     return this.field_179711_e;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 87 */     processPacket((INetHandlerPlayServer)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\play\client\C15PacketClientSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */