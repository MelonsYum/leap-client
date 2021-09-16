/*    */ package net.minecraft.network.login.server;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.network.INetHandler;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.network.login.INetHandlerLoginClient;
/*    */ 
/*    */ public class S02PacketLoginSuccess
/*    */   implements Packet
/*    */ {
/*    */   private GameProfile profile;
/*    */   private static final String __OBFID = "CL_00001375";
/*    */   
/*    */   public S02PacketLoginSuccess() {}
/*    */   
/*    */   public S02PacketLoginSuccess(GameProfile profileIn) {
/* 20 */     this.profile = profileIn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void readPacketData(PacketBuffer data) throws IOException {
/* 28 */     String var2 = data.readStringFromBuffer(36);
/* 29 */     String var3 = data.readStringFromBuffer(16);
/* 30 */     UUID var4 = UUID.fromString(var2);
/* 31 */     this.profile = new GameProfile(var4, var3);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void writePacketData(PacketBuffer data) throws IOException {
/* 39 */     UUID var2 = this.profile.getId();
/* 40 */     data.writeString((var2 == null) ? "" : var2.toString());
/* 41 */     data.writeString(this.profile.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_180771_a(INetHandlerLoginClient p_180771_1_) {
/* 46 */     p_180771_1_.handleLoginSuccess(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public GameProfile func_179730_a() {
/* 51 */     return this.profile;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void processPacket(INetHandler handler) {
/* 59 */     func_180771_a((INetHandlerLoginClient)handler);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\network\login\server\S02PacketLoginSuccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */