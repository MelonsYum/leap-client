/*    */ package leap.modules.player;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import leap.Client;
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventMotion;
/*    */ import leap.modules.Module;
/*    */ import leap.modules.combat.KillAura;
/*    */ import leap.util.RotationUtils;
/*    */ import leap.util.Timer;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*    */ import net.minecraft.tileentity.TileEntityChest;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChestAura
/*    */   extends Module
/*    */ {
/*    */   int slot;
/*    */   double delay;
/*    */   Timer timer;
/*    */   List<String> stealed;
/*    */   
/*    */   public ChestAura() {
/* 31 */     super("ChestAura", 0, Module.Category.PLAYER);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     this.timer = new Timer();
/* 38 */     this.stealed = new ArrayList<>();
/*    */     addSettings(new leap.settings.Setting[0]);
/*    */   } public void onDisable() {
/* 41 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 45 */     this.timer.reset();
/* 46 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 50 */     if (e instanceof EventMotion) {
/*    */       
/* 52 */       EventMotion event = (EventMotion)e;
/*    */       
/* 54 */       if (Client.getModule("KillAura").isEnabled())
/*    */       {
/* 56 */         if (KillAura.targets != null && 
/* 57 */           !KillAura.targets.isEmpty()) {
/*    */           return;
/*    */         }
/*    */       }
/*    */ 
/*    */       
/* 63 */       if (mc.thePlayer == null || mc.theWorld == null || mc.thePlayer.isDead || mc.thePlayer.ticksExisted <= 60) {
/* 64 */         this.stealed.clear();
/*    */       }
/*    */       
/* 67 */       for (Object v : (Minecraft.getMinecraft()).theWorld.loadedTileEntityList) {
/* 68 */         if (v instanceof TileEntityChest) {
/* 69 */           TileEntityChest chest = (TileEntityChest)v;
/*    */           
/* 71 */           int x = chest.getPos().getX();
/* 72 */           int y = chest.getPos().getY();
/* 73 */           int z = chest.getPos().getZ();
/* 74 */           double xPos = x - RenderManager.renderPosX;
/* 75 */           double yPos = y - RenderManager.renderPosY;
/* 76 */           double zPos = z - RenderManager.renderPosZ;
/* 77 */           BlockPos chestpos = new BlockPos(x, y, z);
/*    */           
/* 79 */           int chestdis = (int)mc.thePlayer.getDistance(x, y, z);
/* 80 */           Random chestrandom = new Random();
/*    */           
/* 82 */           if (chestdis < 4 && !(mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiContainer) && this.timer.hasTimeElapsed(10L, true) && !mc.thePlayer.isDead && !this.stealed.contains("[" + x + " " + y + " " + z)) {
/* 83 */             event.setPitch(RotationUtils.getRotationss(x, y, z)[1]);
/* 84 */             event.setYaw(RotationUtils.getRotationss(x, y, z)[0]);
/* 85 */             this.stealed.add("[" + x + " " + y + " " + z);
/* 86 */             mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(chestpos, 1, mc.thePlayer.inventory.getCurrentItem(), x, y, z));
/* 87 */             this.timer.reset();
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\ChestAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */