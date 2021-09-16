/*     */ package leap.modules;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import leap.events.Event;
/*     */ import leap.notifications.Notification;
/*     */ import leap.notifications.NotificationType;
/*     */ import leap.settings.KeyBindSetting;
/*     */ import leap.settings.Setting;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.audio.ISound;
/*     */ import net.minecraft.client.audio.PositionedSoundRecord;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.util.ResourceLocation;
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
/*     */ public class Module
/*     */ {
/*     */   public String name;
/*     */   public boolean toggled;
/*     */   public boolean disableonlag = false;
/*  36 */   public KeyBindSetting keyCode = new KeyBindSetting(0);
/*     */   public Category category;
/*  38 */   public static Minecraft mc = Minecraft.getMinecraft();
/*     */   
/*     */   protected static int categoryCount;
/*     */   public boolean expanded;
/*     */   public int index;
/*  43 */   public List<Setting> settings = new ArrayList<>();
/*     */   
/*     */   public Module(String name, int key, Category c) {
/*  46 */     this.name = name;
/*  47 */     this.keyCode.code = key;
/*  48 */     this.category = c;
/*  49 */     addSettings(new Setting[] { (Setting)this.keyCode });
/*     */   }
/*     */   
/*     */   public void addSettings(Setting... settings) {
/*  53 */     this.settings.addAll(Arrays.asList(settings));
/*  54 */     this.settings.sort(Comparator.comparingInt(s -> (s == this.keyCode) ? 1 : 0));
/*     */   }
/*     */   
/*     */   public void removeSettings(Setting... settings) {
/*  58 */     this.settings.removeAll(Arrays.asList((Object[])settings));
/*  59 */     this.settings.sort(Comparator.comparingInt(s -> (s == this.keyCode) ? 1 : 0));
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Setting> ListSettings(Module m) {
/*  64 */     List<Setting> s = new ArrayList<>();
/*     */     
/*  66 */     if (this.settings.contains(m)) {
/*  67 */       s.add((Setting)this.settings);
/*     */     }
/*  69 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/*  74 */     return this.toggled;
/*     */   }
/*     */   
/*     */   public int getKey() {
/*  78 */     return this.keyCode.code;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {}
/*     */ 
/*     */   
/*     */   public void sendPacket(Packet packet) {
/*  87 */     mc.thePlayer.sendQueue.addToSendQueue(packet);
/*     */   }
/*     */   
/*     */   public void sendNetPacket(Packet packet) {
/*  91 */     mc.getNetHandler().addToSendQueue(packet);
/*     */   }
/*     */   
/*     */   public void toggle() {
/*  95 */     this.toggled = !this.toggled;
/*  96 */     if (this.toggled) {
/*  97 */       onEnable();
/*     */     } else {
/*  99 */       onDisable();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 105 */     sendClientMessageType("§bModule", String.valueOf(this.name) + " was §benabled!", NotificationType.SUCCESS);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onStandby() {}
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 114 */     sendClientMessageType("§bModule", String.valueOf(this.name) + " was \t§cdisabled!", NotificationType.DISABLE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void onRender() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendClientMessage(String title, String chat, double time) {
/* 126 */     ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/*     */     
/* 128 */     Notification.post(NotificationType.INFO, title, chat, time);
/*     */     
/* 130 */     Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("gui.button.press"), 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendClientMessageType(String title, String chat, NotificationType type) {
/* 135 */     ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/*     */     
/* 137 */     Notification.post(type, title, chat, 5.0D);
/*     */     
/* 139 */     Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("gui.button.press"), 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendClientErrorMessage(String chat) {
/* 144 */     ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/*     */     
/* 146 */     mc.thePlayer.sendChatMessage("ERROR: " + chat);
/* 147 */     Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("gui.button.press"), 1.0F));
/*     */   }
/*     */   
/*     */   public enum Category {
/* 151 */     COMBAT("Combat"),
/* 152 */     MOVEMENT("Movement"),
/* 153 */     PLAYER("Player"),
/* 154 */     RENDER("Render"),
/* 155 */     MEMES("Memes"),
/* 156 */     GHOST("Ghost"),
/* 157 */     CONFIGS("Configs");
/*     */     public String name;
/*     */     public int moduleIndex;
/*     */     public int posX;
/*     */     public int posY;
/*     */     public boolean expanded;
/*     */     
/*     */     Category(String name) {
/* 165 */       this.name = name;
/* 166 */       this.posX = 150 + Module.categoryCount * 95;
/* 167 */       this.posY = 85;
/* 168 */       this.expanded = true;
/* 169 */       Module.categoryCount++;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */