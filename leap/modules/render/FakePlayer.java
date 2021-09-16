/*    */ package leap.modules.render;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.WorldSettings;
/*    */ 
/*    */ 
/*    */ public class FakePlayer
/*    */   extends Module
/*    */ {
/*    */   EntityOtherPlayerMP clonedPlayer;
/*    */   
/*    */   public FakePlayer() {
/* 19 */     super("FakePlayer", 0, Module.Category.RENDER);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 24 */     this.clonedPlayer = new EntityOtherPlayerMP((World)mc.theWorld, new GameProfile(UUID.fromString("fdee323e-7f0c-4c15-8d1c-0f277442342a"), "Fit"));
/*    */     
/* 26 */     this.clonedPlayer.copyLocationAndAnglesFrom((Entity)mc.thePlayer);
/* 27 */     this.clonedPlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
/* 28 */     this.clonedPlayer.rotationYaw = mc.thePlayer.rotationYaw;
/*    */     
/* 30 */     this.clonedPlayer.rotationPitch = mc.thePlayer.rotationPitch;
/* 31 */     this.clonedPlayer.setGameType(WorldSettings.GameType.SURVIVAL);
/* 32 */     this.clonedPlayer.setHealth(20.0F);
/*    */     
/* 34 */     if (mc.thePlayer == null || mc.thePlayer.isDead || mc.theWorld == null || !mc.thePlayer.isEntityAlive() || this.clonedPlayer == null || this.clonedPlayer.getName() != "Fit" || mc.thePlayer.getDistanceToEntity((Entity)this.clonedPlayer) > 100.0F) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 39 */     mc.theWorld.addEntityToWorld(-1234, (Entity)this.clonedPlayer);
/* 40 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 44 */     if (e instanceof leap.events.listeners.EventUpdate && (
/* 45 */       mc.thePlayer == null || mc.thePlayer.isDead || mc.theWorld == null || !mc.thePlayer.isEntityAlive() || this.clonedPlayer == null || this.clonedPlayer.getName() != "Fit" || mc.thePlayer.getDistanceToEntity((Entity)this.clonedPlayer) > 100.0F)) {
/* 46 */       toggle();
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 55 */     if (mc.theWorld != null) {
/* 56 */       mc.theWorld.removeEntityFromWorld(-1234);
/* 57 */       super.onDisable();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\FakePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */