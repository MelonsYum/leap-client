/*     */ package leap.modules.movement;
/*     */ 
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.events.listeners.EventReceivePacket;
/*     */ import leap.modules.Module;
/*     */ import leap.modules.player.Scaffold;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C03PacketPlayer;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AntiVoid
/*     */   extends Module
/*     */ {
/*     */   double posX;
/*     */   double posY;
/*     */   double posZ;
/*  36 */   public static NumberSetting falldist = new NumberSetting("Fall Distance", 2.0D, 1.0D, 10.0D, 1.0D);
/*     */   
/*  38 */   public static ModeSetting mode = new ModeSetting("Mode", "Hypixel", new String[] { "Hypixel", "NCP", "AAC" });
/*     */   
/*     */   public AntiVoid() {
/*  41 */     super("AntiVoid", 0, Module.Category.MOVEMENT);
/*  42 */     addSettings(new Setting[] { (Setting)mode });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  46 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  50 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  54 */     if (e instanceof EventMotion) {
/*     */       
/*  56 */       BlockPos bP = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1.0D, mc.thePlayer.posZ);
/*     */       
/*  58 */       if (mc.theWorld.getBlockState(bP).getBlock() == Blocks.air && isBlockUnder() && isBlockUnder(1.0D) && mc.thePlayer.fallDistance == 0.0F) {
/*  59 */         this.posX = mc.thePlayer.posX;
/*  60 */         this.posY = mc.thePlayer.posY;
/*  61 */         this.posZ = mc.thePlayer.posZ;
/*     */       } 
/*     */       
/*  64 */       if (mode.getMode() == "NCP" && 
/*  65 */         !isBlockUnder() && mc.thePlayer.fallDistance > falldist.getValue() && mc.thePlayer.ticksExisted % 5 == 0) {
/*  66 */         sendNetPacket((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY + 0.1D, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, (mc.thePlayer.ticksExisted % 2 == 0)));
/*     */       }
/*     */ 
/*     */       
/*  70 */       if (mode.getMode() == "Hypixel" && 
/*  71 */         !isBlockUnder() && mc.thePlayer.fallDistance > falldist.getValue() && mc.thePlayer.ticksExisted % 5 == 0) {
/*  72 */         EventMotion event = (EventMotion)e;
/*     */         
/*  74 */         Timer timer = new Timer();
/*     */         
/*  76 */         if (!Client.getModule("Scaffold").isEnabled() || Scaffold.lastdata == null) {
/*  77 */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(this.posX + randomNumber(1, 3), this.posY + randomNumber(2, 6), this.posZ + randomNumber(1, 3), mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, (mc.thePlayer.ticksExisted % 2 == 0)));
/*  78 */           mc.thePlayer.onGround = true;
/*  79 */         } else if (Scaffold.lastdata != null) {
/*  80 */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(this.posX + randomNumber(1, 3), this.posY + randomNumber(2, 6), this.posZ + randomNumber(1, 3), mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, (mc.thePlayer.ticksExisted % 2 == 0)));
/*  81 */           mc.thePlayer.onGround = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  86 */     if (mode.getMode() == "Hypixel" && 
/*  87 */       e instanceof EventReceivePacket && 
/*  88 */       !isBlockUnder() && mc.thePlayer.fallDistance > falldist.getValue() + 2.0D && mc.thePlayer.ticksExisted % 5 == 0) {
/*  89 */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       
/*  91 */       Packet packet = event.getPacket();
/*     */       
/*  93 */       if ((Minecraft.getMinecraft()).theWorld == null || Minecraft.getMinecraft().isSingleplayer()) {
/*     */         return;
/*     */       }
/*     */       
/*  97 */       if (packet instanceof net.minecraft.network.play.client.C01PacketChatMessage || packet instanceof net.minecraft.network.play.client.C0FPacketConfirmTransaction || packet instanceof net.minecraft.network.play.client.C00PacketKeepAlive || packet instanceof net.minecraft.network.play.client.C14PacketTabComplete || packet instanceof net.minecraft.network.play.client.C16PacketClientStatus) {
/*     */         return;
/*     */       }
/* 100 */       event.setCancelled(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isBlockUnder() {
/* 107 */     for (int offset = 0; offset < mc.thePlayer.posY + mc.thePlayer.getEyeHeight(); offset += 2) {
/* 108 */       AxisAlignedBB boundingBox = mc.thePlayer.getEntityBoundingBox().offset(0.0D, -offset, 0.0D);
/*     */       
/* 110 */       if (!mc.theWorld.getCollidingBoundingBoxes((Entity)mc.thePlayer, boundingBox).isEmpty())
/* 111 */         return true; 
/*     */     } 
/* 113 */     return false;
/*     */   }
/*     */   
/*     */   public static int randomNumber(int max, int min) {
/* 117 */     int ii = -min + (int)(Math.random() * (max - -min + 1));
/* 118 */     return ii;
/*     */   }
/*     */   
/*     */   private boolean isBlockUnder(double yOffset) {
/* 122 */     EntityPlayerSP player = mc.thePlayer;
/* 123 */     return (mc.theWorld.getBlockState(new BlockPos(player.posX, player.posY - yOffset, player.posZ)).getBlock() != Blocks.air);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\AntiVoid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */