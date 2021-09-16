/*     */ package leap.modules.movement;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.events.listeners.EventReceivePacket;
/*     */ import leap.modules.Module;
/*     */ import leap.notifications.Notification;
/*     */ import leap.notifications.NotificationType;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.RenderUtil;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C03PacketPlayer;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Fly
/*     */   extends Module
/*     */ {
/*     */   boolean done;
/*     */   boolean back;
/*     */   double mineplexSpeed;
/*     */   public int lastDist;
/*     */   int motionY;
/*     */   int motionZ;
/*  46 */   private EntityOtherPlayerMP clonedPlayer = null;
/*  47 */   private int aac5Status = 0;
/*  48 */   private double aac5LastPosX = 0.0D;
/*  49 */   private int aac5Same = 0;
/*  50 */   private C03PacketPlayer.C06PacketPlayerPosLook aac5QueuedPacket = null;
/*  51 */   private int aac5SameReach = 5;
/*     */   private boolean aac5FlyClip = false;
/*     */   private boolean aac5FlyStart = false;
/*     */   private boolean aac5nextFlag = false;
/*     */   public double firstPosY;
/*  56 */   private double aac5LastFlag = 0.0D;
/*  57 */   private final Timer flyTimer = new Timer();
/*     */   
/*  59 */   public static ModeSetting options = new ModeSetting("Options", "Vanilla", new String[] { "Vanilla", "Viper", "Mineplex", "AAC", "Motion", "Blink", "AAC5" });
/*  60 */   public static NumberSetting speed = new NumberSetting("VanillaSpeed", 6.0D, 1.0D, 20.0D, 1.0D);
/*     */   private final ArrayList<C03PacketPlayer> aac5C03List;
/*     */   
/*  63 */   public Fly() { super("Fly", 0, Module.Category.MOVEMENT);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     this.aac5C03List = new ArrayList<>();
/*     */     addSettings(new Setting[] { (Setting)options }); }
/*     */    private void sendAAC5Packets() {
/* 276 */     float yaw = mc.thePlayer.rotationYaw;
/* 277 */     float pitch = mc.thePlayer.rotationPitch;
/* 278 */     for (C03PacketPlayer packet : this.aac5C03List) {
/* 279 */       sendNetPacket((Packet)packet);
/* 280 */       if (packet.func_149466_j()) {
/* 281 */         if (packet.getRotating()) {
/* 282 */           yaw = packet.yaw;
/* 283 */           pitch = packet.pitch;
/*     */         } 
/* 285 */         sendNetPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(packet.x, 1.0E159D, packet.z, true));
/* 286 */         sendNetPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(packet.x, packet.y, packet.z, true));
/*     */       } 
/*     */     } 
/* 289 */     this.aac5C03List.clear();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*     */     if (e instanceof leap.events.listeners.EventRenderWorld) {
/*     */       EntityPlayerSP entity = mc.thePlayer;
/*     */       mc.getRenderManager();
/*     */       double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/*     */       mc.getRenderManager();
/*     */       double posY = this.firstPosY - RenderManager.renderPosY;
/*     */       mc.getRenderManager();
/*     */       double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */       if (mc.thePlayer.posY > this.firstPosY) {
/*     */         RenderUtil.drawHat(posX, posY + entity.height - 0.25D + 0.5D, posZ, (entity.width * 3.0F) - 0.6D, entity.height - 1.75D, 255.0F, 0.0F, 0.0F, 100.0F, 1.0F, 1.0F, 1.0F, 0.2F, 1.0F);
/*     */       } else {
/*     */         RenderUtil.drawHat(posX, posY + entity.height - 0.25D + 0.5D, posZ, (entity.width * 3.0F) - 0.6D, entity.height - 1.75D, 0.0F, 255.0F, 0.0F, 100.0F, 1.0F, 1.0F, 1.0F, 0.2F, 1.0F);
/*     */       } 
/*     */     } 
/*     */     if (e instanceof EventMotion) {
/*     */       String mode = options.getMode();
/*     */       EventMotion event = (EventMotion)e;
/*     */       if (mc.thePlayer == null || mc.theWorld == null) {
/*     */         this.toggled = false;
/*     */         return;
/*     */       } 
/*     */       if (!this.settings.contains(Fly.speed) && options.getMode() == "Vanilla") {
/*     */         addSettings(new Setting[] { (Setting)Fly.speed });
/*     */       } else if (options.getMode() != "Vanilla" && this.settings.contains(Fly.speed)) {
/*     */         removeSettings(new Setting[] { (Setting)Fly.speed });
/*     */       } 
/*     */       if (options.getMode() == "Motion") {
/*     */         double speed = 0.2D;
/*     */         mc.thePlayer.onGround = false;
/*     */         mc.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
/*     */         event.setOnGround(!(!event.isOnGround() && !Client.getModule("NoFall").isEnabled()));
/*     */         if (mc.thePlayer.movementInput.jump) {
/*     */           mc.thePlayer.motionY = speed * 0.6D;
/*     */         } else if (mc.thePlayer.movementInput.sneak) {
/*     */           mc.thePlayer.motionY = -speed * 0.6D;
/*     */         } else {
/*     */           mc.thePlayer.motionY = 0.0D;
/*     */         } 
/*     */       } 
/*     */       if (options.getMode() == "AAC5") {
/*     */         if (this.clonedPlayer == null)
/*     */           return; 
/*     */         this.clonedPlayer.inventory.copyInventory(mc.thePlayer.inventory);
/*     */         this.clonedPlayer.setHealth(mc.thePlayer.getHealth());
/*     */         this.clonedPlayer.rotationYaw = mc.thePlayer.rotationYaw;
/*     */         this.clonedPlayer.rotationPitch = mc.thePlayer.rotationPitch;
/*     */       } 
/*     */       if (mode.equalsIgnoreCase("Viper")) {
/*     */         if (Keyboard.isKeyDown(17)) {
/*     */           mc.thePlayer.setAIMoveSpeed(4.0F);
/*     */           if (mc.gameSettings.keyBindJump.isPressed()) {
/*     */             mc.thePlayer.motionY = 0.5D;
/*     */           } else if (mc.gameSettings.keyBindSneak.isPressed()) {
/*     */             mc.thePlayer.motionY = -0.5D;
/*     */           } 
/*     */           if (!mc.gameSettings.keyBindSneak.isPressed() && !mc.gameSettings.keyBindJump.isPressed())
/*     */             mc.thePlayer.motionY = 0.0D; 
/*     */         } else {
/*     */           mc.thePlayer.setVelocity(0.0D, 0.0D, 0.0D);
/*     */         } 
/*     */       } else if (mode.equalsIgnoreCase("Vanilla")) {
/*     */         mc.thePlayer.capabilities.isFlying = true;
/*     */         mc.thePlayer.capabilities.allowFlying = true;
/*     */         mc.thePlayer.capabilities.setFlySpeed((float)Fly.speed.getValue() / 10.0F);
/*     */       } else if (mode.equalsIgnoreCase("AAC")) {
/*     */         double MotionY = mc.thePlayer.posY;
/*     */         int counter = 1;
/*     */         counter++;
/*     */         mc.timer.timerSpeed = (float)(0.15D + (counter % 50 / 100));
/*     */       } else if (options.getMode() == "Mineplex") {
/*     */         mc.thePlayer.capabilities.isFlying = true;
/*     */         mc.thePlayer.capabilities.allowFlying = true;
/*     */         mc.thePlayer.onGround = true;
/*     */         mc.thePlayer.motionY = 0.0D;
/*     */         event.onGround = true;
/*     */         mc.thePlayer.velocityChanged = true;
/*     */         mc.thePlayer.setVelocity(mc.thePlayer.motionX, mc.thePlayer.motionY, mc.thePlayer.motionZ);
/*     */       } else if (mode.equalsIgnoreCase("Blink")) {
/*     */         if ((Minecraft.getMinecraft()).theWorld == null || Minecraft.getMinecraft().isSingleplayer()) {
/*     */           Notification.post(NotificationType.WARNING, "WARNING", "Blink can't be used outside of a Server!", 2.0D);
/*     */           this.toggled = false;
/*     */           return;
/*     */         } 
/*     */         mc.thePlayer.capabilities.isFlying = true;
/*     */         mc.thePlayer.capabilities.allowFlying = true;
/*     */         mc.thePlayer.capabilities.setFlySpeed((float)Fly.speed.getValue() / 10.0F);
/*     */       } 
/*     */     } 
/*     */     if (e instanceof EventReceivePacket) {
/*     */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       if (options.getMode() == "Mineplex") {
/*     */         if (event.getPacket() instanceof net.minecraft.network.play.client.C00PacketKeepAlive)
/*     */           event.setCancelled(true); 
/*     */         if (event.getPacket() instanceof C03PacketPlayer) {
/*     */           C03PacketPlayer packet = (C03PacketPlayer)event.getPacket();
/*     */           packet.y = 2.0D;
/*     */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer());
/*     */           event.setCancelled(true);
/*     */         } 
/*     */         if (event.getPacket() instanceof net.minecraft.network.play.client.C18PacketSpectate)
/*     */           event.setCancelled(true); 
/*     */       } 
/*     */       if (options.getMode() == "Blink" && isEnabled()) {
/*     */         Packet packet = event.getPacket();
/*     */         if ((Minecraft.getMinecraft()).theWorld == null || Minecraft.getMinecraft().isSingleplayer())
/*     */           return; 
/*     */         if (packet instanceof net.minecraft.network.play.client.C01PacketChatMessage || packet instanceof net.minecraft.network.play.client.C0FPacketConfirmTransaction || packet instanceof net.minecraft.network.play.client.C00PacketKeepAlive || packet instanceof net.minecraft.network.play.client.C14PacketTabComplete || packet instanceof net.minecraft.network.play.client.C16PacketClientStatus)
/*     */           return; 
/*     */         event.setCancelled(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int airSlot() {
/*     */     for (int j = 0; j < 8; j++) {
/*     */       if ((Minecraft.getMinecraft()).thePlayer.inventory.mainInventory[j] == null)
/*     */         return j; 
/*     */     } 
/*     */     return -10;
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*     */     this.firstPosY = mc.thePlayer.posY;
/*     */     if (options.getMode() == "AAC5") {
/*     */       this.clonedPlayer = new EntityOtherPlayerMP((World)mc.theWorld, mc.thePlayer.getGameProfile());
/*     */       this.clonedPlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
/*     */       this.clonedPlayer.copyLocationAndAnglesFrom((Entity)mc.thePlayer);
/*     */       mc.theWorld.addEntityToWorld((int)-(Math.random() * 10000.0D), (Entity)this.clonedPlayer);
/*     */       this.clonedPlayer.setInvisible(true);
/*     */       mc.pointedEntity = (Entity)this.clonedPlayer;
/*     */     } 
/*     */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onStandby() {
/*     */     if (!this.settings.contains(speed) && options.getMode() == "Vanilla") {
/*     */       addSettings(new Setting[] { (Setting)speed });
/*     */     } else if (options.getMode() != "Vanilla" && this.settings.contains(speed)) {
/*     */       removeSettings(new Setting[] { (Setting)speed });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*     */     mc.thePlayer.capabilities.isFlying = false;
/*     */     mc.thePlayer.capabilities.setFlySpeed(0.1F);
/*     */     if (mc.playerController.isNotCreative())
/*     */       mc.thePlayer.capabilities.allowFlying = false; 
/*     */     mc.timer.timerSpeed = 1.0F;
/*     */     if (mc.thePlayer == null)
/*     */       return; 
/*     */     if (options.getMode() == "AAC5") {
/*     */       mc.pointedEntity = (Entity)mc.thePlayer;
/*     */       if (this.clonedPlayer != null)
/*     */         mc.theWorld.removeEntityFromWorld(this.clonedPlayer.getEntityId()); 
/*     */       this.clonedPlayer = null;
/*     */     } 
/*     */     super.onDisable();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\Fly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */