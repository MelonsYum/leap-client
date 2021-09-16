/*     */ package leap.modules.player;
/*     */ 
/*     */ import java.util.Random;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventReceivePacket;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.RandomUtil;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.play.server.S02PacketChat;
/*     */ import net.minecraft.network.play.server.S3EPacketTeams;
/*     */ import net.minecraft.network.play.server.S45PacketTitle;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoHypixel
/*     */   extends Module
/*     */ {
/*  28 */   public static BooleanSetting toxic = new BooleanSetting("Toxic", true);
/*  29 */   public static BooleanSetting play = new BooleanSetting("Auto Play", true);
/*  30 */   public static BooleanSetting gg = new BooleanSetting("Auto GG", true);
/*  31 */   public static BooleanSetting advertise = new BooleanSetting("Advertise", true);
/*     */   public String Mode;
/*     */   public String Type;
/*  34 */   public Timer delay = new Timer();
/*     */   
/*     */   public AutoHypixel() {
/*  37 */     super("AutoHypixel", 0, Module.Category.PLAYER);
/*  38 */     addSettings(new Setting[] { (Setting)toxic, (Setting)play, (Setting)gg, (Setting)advertise });
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {
/*  43 */     if (play.isEnabled() && 
/*  44 */       e instanceof leap.events.listeners.EventUpdate) {
/*     */       
/*  46 */       String[] arrayOfString = { " LL!!! ", " P!P!PP!P ", " IG!G ", " B!B!B!! ", " !C!C!C! ", " !O!!O!O! ", " !!YY! ", "H!!H!H!", "A!A!A", "{}!!}{", "RR!RRE" };
/*     */       
/*  48 */       Random random1 = new Random();
/*     */       
/*  50 */       int j = random1.nextInt(arrayOfString.length);
/*     */       
/*  52 */       if (RandomUtil.randomNumber(100.0D, 1.0D) == 10 && advertise.isEnabled()) {
/*  53 */         mc.thePlayer.sendChatMessage(String.valueOf(arrayOfString[j]) + " [Leap Client]" + arrayOfString[j] + "block game hacks that" + RandomUtil.randomNumber(1.0D, 1000.0D) + "make you the best!" + arrayOfString[j] + "Download Leap Client allah " + arrayOfString[j]);
/*     */       }
/*     */       
/*  56 */       for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*     */         
/*  58 */         ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/*  59 */         if (itemStack != null && 
/*  60 */           itemStack.getDisplayName().contains("Retry")) {
/*  61 */           mc.thePlayer.inventory.currentItem = i;
/*  62 */           mc.playerController.sendUseItem((EntityPlayer)mc.thePlayer, (World)mc.theWorld, mc.thePlayer.inventory.getCurrentItem());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  69 */     String[] cope = { "L", "Ur actually dogwater", "Imagine Losing", "Ur so bad LLLL", "Cope harder", "im not hacking its just my rgb keyboard", "im not hacking maybe its my samsung smart fridge", "ur IQ is less than room temperature", "ur so bad lol", "when the coping isn't strong enough", "get gud", "ur literally 2 yrs old", "Call the Ambulance, theres been a murder!", "i honestly feel sorry for you, you are so bad its sad", "that was almost too easy lol" };
/*     */     
/*  71 */     Random random = new Random();
/*     */     
/*  73 */     int select = random.nextInt(cope.length);
/*     */ 
/*     */     
/*  76 */     if (e instanceof EventReceivePacket) {
/*  77 */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       
/*  79 */       if (event.getPacket() instanceof S3EPacketTeams) {
/*     */         
/*  81 */         S3EPacketTeams packet = (S3EPacketTeams)event.getPacket();
/*     */         
/*  83 */         String message = packet.func_149311_e();
/*  84 */         if (message.toLowerCase().contains("normal")) {
/*  85 */           this.Mode = "normal";
/*     */         }
/*  87 */         if (message.toLowerCase().contains("insane")) {
/*  88 */           this.Mode = "insane";
/*     */         }
/*  90 */         if (message.toLowerCase().contains("mega")) {
/*  91 */           this.Type = "mega";
/*  92 */           this.Mode = "normal";
/*     */         } 
/*  94 */         if (message.toLowerCase().contains("teams left")) {
/*  95 */           this.Type = "teams";
/*     */         }
/*     */       } 
/*     */       
/*  99 */       if (event.getPacket() instanceof S45PacketTitle) {
/* 100 */         if (((S45PacketTitle)event.getPacket()).func_179805_b() == null) {
/*     */           return;
/*     */         }
/* 103 */         String message = ((S45PacketTitle)event.getPacket()).func_179805_b().getUnformattedText();
/* 104 */         if ((message.toLowerCase().contains("you died!") || message.toLowerCase().contains("game end") || message.toLowerCase().contains("victory!") || message.toLowerCase().contains("you are now a spectator!")) && 
/* 105 */           this.Type != null && this.Mode != null && 
/* 106 */           this.delay.hasTimeElapsed(2000L, true)) {
/* 107 */           mc.thePlayer.sendChatMessage("/play " + this.Type + "_" + this.Mode);
/* 108 */           sendClientMessage("INFO: ", "Started new game!", 5.0D);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 115 */       if (event.getPacket() instanceof S02PacketChat) {
/*     */         
/* 117 */         S02PacketChat packet = (S02PacketChat)event.getPacket();
/* 118 */         String message = packet.func_148915_c().getUnformattedText();
/*     */         
/* 120 */         if (message.toLowerCase().contains("teaming is not allowed on solo mode!")) {
/* 121 */           this.Type = "solo";
/*     */         }
/*     */ 
/*     */         
/* 125 */         if (message.toLowerCase().contains(" pas ") || 
/* 126 */           message.toLowerCase().contains(" au ") || 
/* 127 */           message.toLowerCase().contains("的") || 
/* 128 */           message.toLowerCase().contains("一") || 
/* 129 */           message.toLowerCase().contains("á") || 
/* 130 */           message.toLowerCase().contains("dé") || 
/* 131 */           message.toLowerCase().contains("Sekunde") || 
/* 132 */           message.toLowerCase().contains(" fini ")) {
/* 133 */           mc.thePlayer.sendChatMessage("/language english");
/*     */         }
/*     */         
/* 136 */         if (toxic.isEnabled() && (
/* 137 */           message.toLowerCase().contains("was killed by " + mc.thePlayer.getName().toLowerCase() + ".") || 
/* 138 */           message.toLowerCase().contains("was thrown into the void by " + mc.thePlayer.getName().toLowerCase() + ".") || message.toLowerCase().contains(" cannonballed " + mc.thePlayer.getName().toLowerCase() + ".") || message.toLowerCase().contains("davy jones" + mc.thePlayer.getName().toLowerCase() + ".") || (
/* 139 */           message.toLowerCase().contains("was thrown off a cliff by " + mc.thePlayer.getName().toLowerCase() + ".") && mc.thePlayer.isEntityAlive() && !mc.thePlayer.isDead))) {
/* 140 */           if (advertise.isEnabled()) {
/* 141 */             mc.thePlayer.sendChatMessage("[Leap Client] " + cope[select]);
/*     */           } else {
/* 143 */             mc.thePlayer.sendChatMessage(cope[select]);
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 149 */         if (gg.isEnabled() && 
/* 150 */           message.toLowerCase().contains("you won!")) {
/* 151 */           if (advertise.isEnabled()) {
/* 152 */             mc.thePlayer.sendChatMessage("gg smashed by Leap Client with da bypass");
/*     */           } else {
/* 154 */             mc.thePlayer.sendChatMessage("gg");
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 165 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/* 169 */     super.onDisable();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\AutoHypixel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */