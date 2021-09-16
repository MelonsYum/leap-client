/*    */ package net.minecraft.server.integrated;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.net.SocketAddress;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ import net.minecraft.server.management.ServerConfigurationManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IntegratedPlayerList
/*    */   extends ServerConfigurationManager
/*    */ {
/*    */   private NBTTagCompound hostPlayerData;
/*    */   private static final String __OBFID = "CL_00001128";
/*    */   
/*    */   public IntegratedPlayerList(IntegratedServer p_i1314_1_) {
/* 20 */     super(p_i1314_1_);
/* 21 */     setViewDistance(10);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void writePlayerData(EntityPlayerMP playerIn) {
/* 29 */     if (playerIn.getName().equals(func_180603_b().getServerOwner())) {
/*    */       
/* 31 */       this.hostPlayerData = new NBTTagCompound();
/* 32 */       playerIn.writeToNBT(this.hostPlayerData);
/*    */     } 
/*    */     
/* 35 */     super.writePlayerData(playerIn);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String allowUserToConnect(SocketAddress address, GameProfile profile) {
/* 43 */     return (profile.getName().equalsIgnoreCase(func_180603_b().getServerOwner()) && getPlayerByUsername(profile.getName()) != null) ? "That name is already taken." : super.allowUserToConnect(address, profile);
/*    */   }
/*    */ 
/*    */   
/*    */   public IntegratedServer func_180603_b() {
/* 48 */     return (IntegratedServer)super.getServerInstance();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagCompound getHostPlayerData() {
/* 56 */     return this.hostPlayerData;
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftServer getServerInstance() {
/* 61 */     return func_180603_b();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\server\integrated\IntegratedPlayerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */