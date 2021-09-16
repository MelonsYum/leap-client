/*    */ package leap.modules.movement;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventMotion;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import leap.util.Timer;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C02PacketUseEntity;
/*    */ import net.minecraft.network.play.client.C03PacketPlayer;
/*    */ import net.minecraft.util.BlockPos;
/*    */ 
/*    */ public class Teleport
/*    */   extends Module
/*    */ {
/* 22 */   public NumberSetting range = new NumberSetting("Range", 25.0D, 1.0D, 100.0D, 1.0D);
/* 23 */   Timer timer = new Timer();
/*    */   
/*    */   public Teleport() {
/* 26 */     super("Teleport", 0, Module.Category.MOVEMENT);
/* 27 */     addSettings(new Setting[] { (Setting)this.range });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 31 */     if (this.timer.hasTimeElapsed(1000L, true)) {
/* 32 */       toggle();
/*    */     }
/* 34 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 38 */     mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.getIsKeyPressed());
/* 39 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 43 */     if (e instanceof EventMotion && 
/* 44 */       e.isPre()) {
/*    */       
/* 46 */       EventMotion event = (EventMotion)e;
/*    */       
/* 48 */       List<EntityLivingBase> targets = (List<EntityLivingBase>)mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
/*    */       
/* 50 */       targets = (List<EntityLivingBase>)targets.stream().filter(entity -> (entity.getDistanceToEntity((Entity)mc.thePlayer) < this.range.getValue() && entity != mc.thePlayer)).collect(Collectors.toList());
/*    */       
/* 52 */       targets.sort(Comparator.comparingDouble(entity -> entity.getDistanceToEntity((Entity)mc.thePlayer)));
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 57 */       if (!targets.isEmpty()) {
/* 58 */         EntityLivingBase target = targets.get(0);
/* 59 */         if (this.timer.hasTimeElapsed(500L, true)) {
/* 60 */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C02PacketUseEntity((Entity)target, C02PacketUseEntity.Action.ATTACK));
/* 61 */           BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
/* 62 */           mc.thePlayer.isAirBorne = false;
/* 63 */           mc.thePlayer.onGround = true;
/* 64 */           mc.thePlayer.isInvisible();
/*    */         } 
/* 66 */         mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer(true));
/* 67 */         mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(target.posX, target.posY, target.posZ, true));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float[] getRotations(Entity e) {
/* 74 */     double deltaX = e.posX + e.posX - e.lastTickPosX - mc.thePlayer.posX;
/* 75 */     double deltaY = e.posY - 3.5D + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight();
/* 76 */     double deltaZ = e.posZ + e.posZ - e.lastTickPosZ - mc.thePlayer.posZ;
/* 77 */     double distance = Math.sqrt(Math.pow(deltaX, 2.0D) + Math.pow(deltaZ, 2.0D));
/*    */     
/* 79 */     float yaw = (float)Math.toDegrees(-Math.atan(deltaX - deltaZ));
/* 80 */     float pitch = (float)-Math.toDegrees(Math.atan(deltaY / distance));
/*    */     
/* 82 */     if (deltaX < 0.0D && deltaZ < 0.0D) {
/* 83 */       yaw = (float)(90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX)));
/* 84 */     } else if (deltaX > 0.0D && deltaZ < 0.0D) {
/* 85 */       yaw = (float)(-90.0D + Math.toDegrees(Math.atan(deltaZ / deltaX)));
/*    */     } 
/*    */     
/* 88 */     return new float[] { yaw, pitch };
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\Teleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */