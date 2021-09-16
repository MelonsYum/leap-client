/*    */ package leap.modules.combat;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.network.NetworkPlayerInfo;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AntiBots
/*    */   extends Module
/*    */ {
/* 31 */   public ModeSetting mode = new ModeSetting("Mode", "Hypixel", new String[] { "Basic", "Hypixel" });
/*    */   
/*    */   public AntiBots() {
/* 34 */     super("AntiBots", 0, Module.Category.COMBAT);
/* 35 */     addSettings(new Setting[] { (Setting)this.mode });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 39 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 43 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 47 */     if (e instanceof leap.events.listeners.EventUpdate && 
/* 48 */       e.isPre()) {
/* 49 */       if (this.mode.getMode() == "Basic") {
/* 50 */         for (Object entity : mc.theWorld.loadedEntityList) {
/* 51 */           if (entity instanceof EntityPlayer && (((
/* 52 */             (Entity)entity).getCustomNameTag() == "" && entity != mc.thePlayer && !KillAura.isOnSameTeam((Entity)entity)) || (((Entity)entity).getName().contains("[NPC]") && entity != mc.thePlayer))) {
/* 53 */             KillAura.bots.add((Entity)entity);
/*    */           }
/*    */         } 
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 62 */       if (this.mode.getMode() == "HypixelTest") {
/* 63 */         for (Object entity : mc.theWorld.loadedEntityList) {
/* 64 */           if (((Entity)entity).getCustomNameTag() == "" || (entity != mc.thePlayer && mc.thePlayer != null && !mc.thePlayer.isDead) || (((Entity)entity).ticksExisted <= 3 && ((Entity)entity).ticksExisted != 0 && !mc.thePlayer.isDead && entity != mc.thePlayer)) {
/* 65 */             KillAura.bots.add((Entity)entity);
/*    */           }
/* 67 */           if (!getPlayerList().contains(entity)) mc.theWorld.removeEntity((Entity)entity);
/*    */           
/* 69 */           if (((Entity)entity).getDisplayName().getFormattedText().equalsIgnoreCase(String.valueOf(((Entity)entity).getName()) + "§r") && !mc.thePlayer.getDisplayName().getFormattedText().equalsIgnoreCase(String.valueOf(mc.thePlayer.getName()) + "§r")) {
/* 70 */             KillAura.bots.add((Entity)entity);
/*    */           }
/*    */ 
/*    */           
/* 74 */           if (!((Entity)entity).getDisplayName().getFormattedText().replace("§r", "").contains("§")) {
/* 75 */             KillAura.bots.add((Entity)entity);
/*    */           }
/*    */           
/* 78 */           if (((Entity)entity).getEntityId() >= 1000000000 || ((Entity)entity).getEntityId() <= -1) {
/* 79 */             KillAura.bots.add((Entity)entity);
/*    */           }
/* 81 */           if (((Entity)entity).isInvisible() && ((Entity)entity).ticksExisted <= 10 && ((Entity)entity).ticksExisted != 0) {
/* 82 */             KillAura.bots.add((Entity)entity);
/*    */           }
/*    */         } 
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<EntityPlayer> getPlayerList() {
/* 92 */     Minecraft.getMinecraft();
/* 93 */     Collection<NetworkPlayerInfo> playerInfoMap = mc.getNetHandler().func_175106_d();
/* 94 */     ArrayList<EntityPlayer> list = new ArrayList<>();
/* 95 */     for (NetworkPlayerInfo networkPlayerInfo : playerInfoMap) {
/* 96 */       list.add((Minecraft.getMinecraft()).theWorld.getPlayerEntityByName(networkPlayerInfo.getGameType().getName()));
/*    */     }
/* 98 */     return list;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\AntiBots.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */