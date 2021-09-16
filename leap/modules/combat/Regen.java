/*    */ package leap.modules.combat;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.events.listeners.EventMotion;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.Setting;
/*    */ import leap.util.Timer;
/*    */ import net.minecraft.network.Packet;
/*    */ import net.minecraft.network.play.client.C03PacketPlayer;
/*    */ import net.minecraft.potion.Potion;
/*    */ import net.minecraft.potion.PotionEffect;
/*    */ 
/*    */ 
/*    */ public class Regen
/*    */   extends Module
/*    */ {
/* 18 */   Timer timer = new Timer();
/*    */   
/* 20 */   public ModeSetting mode = new ModeSetting("Mode", "Mineplex", new String[] { "Potion", "Mineplex", "Infinite" });
/*    */   
/*    */   public Regen() {
/* 23 */     super("Regen", 0, Module.Category.COMBAT);
/* 24 */     addSettings(new Setting[] { (Setting)this.mode });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 28 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 32 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 36 */     if (e instanceof EventMotion && 
/* 37 */       e.isPre()) {
/* 38 */       EventMotion event = (EventMotion)e;
/*    */       
/* 40 */       if (this.mode.modes.equals("Potion") && mc.thePlayer.getActivePotionEffect(Potion.regeneration) != null && mc.thePlayer.onGround && mc.thePlayer.getHealth() < mc.thePlayer.getMaxHealth()) {
/* 41 */         for (int i = 0; i < mc.thePlayer.getMaxHealth() - mc.thePlayer.getHealth() && mc.thePlayer.getActivePotionEffect(Potion.regeneration) != null; i++) {
/* 42 */           mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer(event.onGround));
/*    */           
/* 44 */           boolean item = (mc.thePlayer.getCurrentEquippedItem() == null);
/* 45 */           mc.thePlayer.addPotionEffect(new PotionEffect(Potion.regeneration.getId(), 100, item ? 1 : 0));
/*    */         } 
/*    */       }
/*    */       
/* 49 */       if (this.mode.modes.equals("Mineplex") && mc.thePlayer.onGround && mc.thePlayer.getHealth() < mc.thePlayer.getMaxHealth()) {
/* 50 */         for (int i = 0; i < 10; i++) {
/* 51 */           mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C05PacketPlayerLook(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, true));
/*    */         }
/*    */       }
/*    */       
/* 55 */       if (this.mode.modes.equals("Infinite") && !mc.thePlayer.capabilities.isCreativeMode && mc.thePlayer.getFoodStats().getFoodLevel() > 17 && mc.thePlayer.getHealth() < 20.0F && mc.thePlayer.getHealth() != 0.0F && mc.thePlayer.onGround)
/* 56 */         for (int i = 0; i < 50; i++)
/* 57 */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer());  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\Regen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */