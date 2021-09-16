/*    */ package net.minecraft.client.gui.spectator.categories;
/*    */ 
/*    */ import com.google.common.collect.ComparisonChain;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Ordering;
/*    */ import java.util.Collection;
/*    */ import java.util.Comparator;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.gui.GuiSpectator;
/*    */ import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
/*    */ import net.minecraft.client.gui.spectator.ISpectatorMenuView;
/*    */ import net.minecraft.client.gui.spectator.PlayerMenuObject;
/*    */ import net.minecraft.client.gui.spectator.SpectatorMenu;
/*    */ import net.minecraft.client.network.NetworkPlayerInfo;
/*    */ import net.minecraft.util.ChatComponentText;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ import net.minecraft.world.WorldSettings;
/*    */ 
/*    */ public class TeleportToPlayer
/*    */   implements ISpectatorMenuView, ISpectatorMenuObject {
/* 24 */   private static final Ordering field_178674_a = Ordering.from(new Comparator()
/*    */       {
/*    */         private static final String __OBFID = "CL_00001921";
/*    */         
/*    */         public int func_178746_a(NetworkPlayerInfo p_178746_1_, NetworkPlayerInfo p_178746_2_) {
/* 29 */           return ComparisonChain.start().compare(p_178746_1_.func_178845_a().getId(), p_178746_2_.func_178845_a().getId()).result();
/*    */         }
/*    */         
/*    */         public int compare(Object p_compare_1_, Object p_compare_2_) {
/* 33 */           return func_178746_a((NetworkPlayerInfo)p_compare_1_, (NetworkPlayerInfo)p_compare_2_);
/*    */         }
/*    */       });
/*    */   
/*    */   private final List field_178673_b;
/*    */   private static final String __OBFID = "CL_00001922";
/*    */   
/*    */   public TeleportToPlayer() {
/* 41 */     this(field_178674_a.sortedCopy(Minecraft.getMinecraft().getNetHandler().func_175106_d()));
/*    */   }
/*    */ 
/*    */   
/*    */   public TeleportToPlayer(Collection p_i45493_1_) {
/* 46 */     this.field_178673_b = Lists.newArrayList();
/* 47 */     Iterator<NetworkPlayerInfo> var2 = field_178674_a.sortedCopy(p_i45493_1_).iterator();
/*    */     
/* 49 */     while (var2.hasNext()) {
/*    */       
/* 51 */       NetworkPlayerInfo var3 = var2.next();
/*    */       
/* 53 */       if (var3.getGameType() != WorldSettings.GameType.SPECTATOR)
/*    */       {
/* 55 */         this.field_178673_b.add(new PlayerMenuObject(var3.func_178845_a()));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public List func_178669_a() {
/* 62 */     return this.field_178673_b;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_178670_b() {
/* 67 */     return (IChatComponent)new ChatComponentText("Select a player to teleport to");
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178661_a(SpectatorMenu p_178661_1_) {
/* 72 */     p_178661_1_.func_178647_a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatComponent func_178664_z_() {
/* 77 */     return (IChatComponent)new ChatComponentText("Teleport to player");
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_178663_a(float p_178663_1_, int p_178663_2_) {
/* 82 */     Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
/* 83 */     Gui.drawModalRectWithCustomSizedTexture(0.0F, 0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 256.0F, 256.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_178662_A_() {
/* 88 */     return !this.field_178673_b.isEmpty();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\spectator\categories\TeleportToPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */