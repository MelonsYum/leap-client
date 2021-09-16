/*     */ package leap.modules.movement;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.UUID;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventReceivePacket;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.RandomUtil;
/*     */ import net.minecraft.client.entity.EntityOtherPlayerMP;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C00PacketKeepAlive;
/*     */ import net.minecraft.network.play.client.C03PacketPlayer;
/*     */ import net.minecraft.network.play.client.C0CPacketInput;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldSettings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DisablerModule
/*     */   extends Module
/*     */ {
/*  29 */   public ModeSetting modex = new ModeSetting("Mode", "Mineplex", new String[] { "Mineplex", "VerusCombat", "OnlyMC", "C03PacketPlayer", "C00PacketKeepAlive" });
/*     */   
/*  31 */   int count = 0;
/*  32 */   int confirmtranscounter = 0;
/*     */   
/*     */   EntityOtherPlayerMP clonedPlayer;
/*     */   
/*  36 */   ArrayList<Packet> transactions = new ArrayList<>();
/*  37 */   int currentTransaction = 0;
/*     */ 
/*     */   
/*     */   public DisablerModule() {
/*  41 */     super("Disabler", 0, Module.Category.MOVEMENT);
/*  42 */     addSettings(new Setting[] { (Setting)this.modex });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  46 */     mc.thePlayer.ticksExisted = 0;
/*     */     
/*  48 */     this.clonedPlayer = new EntityOtherPlayerMP((World)mc.theWorld, new GameProfile(UUID.fromString("fde3323e-7f0c-4c15-8d1c-0f277442342a"), "Gwen"));
/*     */     
/*  50 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  54 */     this.transactions.clear();
/*  55 */     this.currentTransaction = 0;
/*     */     
/*  57 */     if (this.clonedPlayer != null) {
/*  58 */       mc.theWorld.removeEntityFromWorld(-1234);
/*     */     }
/*     */     
/*  61 */     super.onDisable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {
/*  66 */     if (e instanceof leap.events.listeners.EventUpdate) {
/*     */       
/*  68 */       if (mc.theWorld == null) {
/*  69 */         this.count = 0;
/*  70 */         this.transactions.clear();
/*  71 */         this.currentTransaction = 0;
/*     */       } 
/*     */       
/*  74 */       if (this.modex.getMode() == "Mineplex" && 
/*  75 */         this.clonedPlayer != null) {
/*     */         
/*  77 */         this.clonedPlayer.setInvisible(true);
/*  78 */         this.clonedPlayer.copyLocationAndAnglesFrom((Entity)mc.thePlayer);
/*  79 */         this.clonedPlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
/*  80 */         this.clonedPlayer.rotationYaw = mc.thePlayer.rotationYaw;
/*  81 */         this.clonedPlayer.posY = mc.thePlayer.posY;
/*  82 */         mc.thePlayer.posY = this.clonedPlayer.posY;
/*     */ 
/*     */         
/*  85 */         this.clonedPlayer.setGameType(WorldSettings.GameType.SURVIVAL);
/*  86 */         this.clonedPlayer.setHealth(20.0F);
/*     */         
/*  88 */         if (!this.clonedPlayer.worldObj.loadedEntityList.contains(this.clonedPlayer)) {
/*  89 */           mc.theWorld.addEntityToWorld(-1234, (Entity)this.clonedPlayer);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  95 */       if (this.modex.getMode() == "VerusCombat") {
/*  96 */         if (mc.thePlayer.ticksExisted % 50 == 0) {
/*  97 */           double rand = RandomUtil.randomNumber(2000.0D, 1000.0D);
/*  98 */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY - rand, mc.thePlayer.posZ, 
/*  99 */                 mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, mc.thePlayer.onGround));
/*     */         } 
/* 101 */         if (mc.thePlayer.ticksExisted % 120 == 0 && this.transactions.size() - 1 > this.currentTransaction) {
/* 102 */           sendNetPacket(this.transactions.get(++this.currentTransaction));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 108 */     e instanceof leap.events.listeners.EventSendPacket;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (this.modex.getMode() == "Mineplex" && 
/* 115 */       e instanceof EventReceivePacket) {
/*     */       
/* 117 */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       
/* 119 */       if (event.getPacket() instanceof C00PacketKeepAlive)
/*     */       {
/* 121 */         ((C00PacketKeepAlive)event.getPacket()).key -= RandomUtil.randomNumber(2.147483647E9D, 1000.0D);
/*     */       }
/*     */     } 
/*     */     
/* 125 */     if (this.modex.getMode() == "C03PacketPlayer" && 
/* 126 */       e instanceof EventReceivePacket) {
/*     */       
/* 128 */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       
/* 130 */       if (event.getPacket() instanceof C03PacketPlayer && 
/* 131 */         mc.thePlayer.ticksExisted % 3 != 0) {
/* 132 */         event.setCancelled(true);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 137 */     if (this.modex.getMode() == "OnlyMC" && 
/* 138 */       e instanceof EventReceivePacket) {
/*     */       
/* 140 */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       
/* 142 */       if (event.getPacket() instanceof C03PacketPlayer && 
/* 143 */         mc.thePlayer.ticksExisted % 3 != 0) {
/* 144 */         event.setCancelled(true);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 149 */     if (this.modex.getMode() == "VerusCombat" && 
/* 150 */       e instanceof EventReceivePacket) {
/*     */       
/* 152 */       if (!isEnabled()) {
/*     */         return;
/*     */       }
/*     */       
/* 156 */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       
/* 158 */       Packet packet = event.getPacket();
/* 159 */       if (packet instanceof net.minecraft.network.play.client.C0FPacketConfirmTransaction) {
/* 160 */         this.transactions.add(packet);
/* 161 */         event.setCancelled(true);
/*     */       } 
/*     */       
/* 164 */       if (packet instanceof C00PacketKeepAlive) {
/* 165 */         ((C00PacketKeepAlive)packet).key -= RandomUtil.randomNumber(2.147483647E9D, 1.0D);
/*     */       }
/*     */       
/* 168 */       if (packet instanceof C03PacketPlayer) {
/* 169 */         sendNetPacket((Packet)new C0CPacketInput());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 174 */     if (this.modex.getMode() == "C00PacketKeepAlive" && 
/* 175 */       e instanceof EventReceivePacket) {
/*     */       
/* 177 */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       
/* 179 */       if (event.getPacket() instanceof C00PacketKeepAlive || event.getPacket() instanceof net.minecraft.network.play.client.C0FPacketConfirmTransaction)
/* 180 */         event.setCancelled(true); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\DisablerModule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */