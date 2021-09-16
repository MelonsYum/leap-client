/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.network.NetHandlerPlayClient;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C00PacketKeepAlive;
/*    */ 
/*    */ public class GuiDownloadTerrain
/*    */   extends GuiScreen {
/*    */   private NetHandlerPlayClient netHandlerPlayClient;
/*    */   private int progress;
/*    */   private static final String __OBFID = "CL_00000708";
/*    */   
/*    */   public GuiDownloadTerrain(NetHandlerPlayClient p_i45023_1_) {
/* 16 */     this.netHandlerPlayClient = p_i45023_1_;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void keyTyped(char typedChar, int keyCode) throws IOException {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 30 */     this.buttonList.clear();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateScreen() {
/* 38 */     this.progress++;
/*    */     
/* 40 */     if (this.progress % 20 == 0)
/*    */     {
/* 42 */       this.netHandlerPlayClient.addToSendQueue((Packet)new C00PacketKeepAlive());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 51 */     drawBackground(0);
/* 52 */     drawCenteredString(fontRendererObj, I18n.format("multiplayer.downloadingTerrain", new Object[0]), (width / 2), (height / 2 - 50), 16777215);
/* 53 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean doesGuiPauseGame() {
/* 61 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiDownloadTerrain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */