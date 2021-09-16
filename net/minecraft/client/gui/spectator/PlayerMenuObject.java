/*    */ package net.minecraft.client.gui.spectator;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.AbstractClientPlayer;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C18PacketSpectate;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ public class PlayerMenuObject
/*    */   implements ISpectatorMenuObject {
/*    */   private final GameProfile field_178668_a;
/*    */   private final ResourceLocation field_178667_b;
/*    */   private static final String __OBFID = "CL_00001929";
/*    */   
/*    */   public PlayerMenuObject(GameProfile p_i45498_1_) {
/* 21 */     this.field_178668_a = p_i45498_1_;
/* 22 */     this.field_178667_b = AbstractClientPlayer.getLocationSkin(p_i45498_1_.getName());
/* 23 */     AbstractClientPlayer.getDownloadImageSkin(this.field_178667_b, p_i45498_1_.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178661_a(SpectatorMenu p_178661_1_) {
/* 28 */     Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet)new C18PacketSpectate(this.field_178668_a.getId()));
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_178664_z_() {
/* 33 */     return (IChatComponent)new ChatComponentText(this.field_178668_a.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178663_a(float p_178663_1_, int p_178663_2_) {
/* 38 */     Minecraft.getMinecraft().getTextureManager().bindTexture(this.field_178667_b);
/* 39 */     GlStateManager.color(1.0F, 1.0F, 1.0F, p_178663_2_ / 255.0F);
/* 40 */     Gui.drawScaledCustomSizeModalRect(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
/* 41 */     Gui.drawScaledCustomSizeModalRect(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_178662_A_() {
/* 46 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\spectator\PlayerMenuObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */