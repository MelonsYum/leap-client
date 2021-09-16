/*     */ package leap.modules.movement;
/*     */ 
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.modules.Module;
/*     */ import leap.notifications.Notification;
/*     */ import leap.notifications.NotificationType;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.RandomUtil;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.client.settings.KeyBinding;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
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
/*     */ public class LongJump
/*     */   extends Module
/*     */ {
/*  31 */   public static ModeSetting mode = new ModeSetting("Mode", "Damage", new String[] { "Damage", "Hypixel", "Custom" });
/*     */   
/*  33 */   public static NumberSetting motiony = new NumberSetting("Custom MotionY", 0.4D, 0.1D, 2.0D, 0.1D);
/*  34 */   public static NumberSetting motion = new NumberSetting("Custom Motion", 0.3D, 0.1D, 3.0D, 0.1D);
/*  35 */   public static NumberSetting motionrandom = new NumberSetting("Motion Add Random", 0.3D, 0.0D, 3.0D, 0.1D);
/*  36 */   public static NumberSetting customtimerstart = new NumberSetting("Custom Start Timer", 2.0D, 0.1D, 10.0D, 0.1D);
/*  37 */   public static NumberSetting customtimermidair = new NumberSetting("Custom Midair Timer", 2.0D, 0.1D, 10.0D, 0.1D);
/*  38 */   public static NumberSetting customtimermidairrandom = new NumberSetting("Midair Timer Random", 2.0D, 0.0D, 10.0D, 0.1D);
/*  39 */   public BooleanSetting spoof = new BooleanSetting("Spoof Ground", false);
/*  40 */   public BooleanSetting noground = new BooleanSetting("No Ground", false);
/*     */   
/*     */   boolean usedArrow = false;
/*     */   
/*     */   boolean hasJumped = false;
/*     */   int lastItem;
/*  46 */   Timer timer = new Timer();
/*     */   public LongJump() {
/*  48 */     super("LongJump", 0, Module.Category.MOVEMENT);
/*  49 */     addSettings(new Setting[] { (Setting)mode });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  53 */     this.usedArrow = false;
/*  54 */     this.hasJumped = false;
/*  55 */     this.lastItem = mc.thePlayer.inventory.currentItem;
/*     */     
/*  57 */     if (mode.getMode() == "Hypixel") {
/*  58 */       Notification.post(NotificationType.WARNING, "WARNING", "Using this too much can result in a ban!", 5.0D);
/*     */     }
/*     */     
/*  61 */     if (!mc.thePlayer.onGround) {
/*  62 */       sendClientMessage("\t§bLongJump ", "LongJump disabled due to not being on ground", 5.0D);
/*  63 */       toggle();
/*     */     } else {
/*  65 */       sendClientMessage("INFO", "Shooting bow...", 0.7D);
/*     */     } 
/*     */     
/*  68 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onStandby() {
/*  72 */     if (!this.settings.contains(motion) && mode.getMode() == "Custom") {
/*  73 */       addSettings(new Setting[] { (Setting)motiony, (Setting)motion, (Setting)motionrandom, (Setting)customtimerstart, (Setting)customtimermidair, (Setting)customtimermidairrandom, (Setting)this.spoof, (Setting)this.noground });
/*  74 */     } else if (mode.getMode() != "Custom" && this.settings.contains(motion)) {
/*  75 */       removeSettings(new Setting[] { (Setting)motiony, (Setting)motion, (Setting)motionrandom, (Setting)customtimerstart, (Setting)customtimermidair, (Setting)customtimermidairrandom, (Setting)this.spoof, (Setting)this.noground });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  80 */     mc.timer.timerSpeed = 1.0F;
/*     */     
/*  82 */     super.onDisable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {
/*  87 */     if (e instanceof EventMotion) {
/*     */       
/*  89 */       if (!this.settings.contains(motion) && mode.getMode() == "Custom") {
/*  90 */         addSettings(new Setting[] { (Setting)motiony, (Setting)motion, (Setting)customtimerstart, (Setting)customtimermidair });
/*  91 */       } else if (mode.getMode() != "Custom" && this.settings.contains(motion)) {
/*  92 */         removeSettings(new Setting[] { (Setting)motiony, (Setting)motion, (Setting)customtimerstart, (Setting)customtimermidair });
/*     */       } 
/*     */       
/*  95 */       EventMotion event = (EventMotion)e;
/*     */       
/*  97 */       if (mode.getMode() == "Damage") {
/*     */ 
/*     */         
/* 100 */         for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*     */           
/* 102 */           ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/* 103 */           if (itemStack != null && 
/* 104 */             itemStack.getItem() instanceof net.minecraft.item.ItemBow) {
/* 105 */             mc.thePlayer.inventory.currentItem = i;
/*     */           }
/*     */         } 
/*     */         
/* 109 */         if (mc.thePlayer.inventory.getCurrentItem() != null && 
/* 110 */           !(mc.thePlayer.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemBow)) {
/* 111 */           sendClientMessage("\t§bLongJump  ", "no bow and/or arrow in hotbar!", 5.0D);
/* 112 */           toggle();
/*     */         } 
/*     */         
/* 115 */         if (mc.thePlayer.inventory.getCurrentItem() == null) {
/* 116 */           sendClientMessage("\t§bLongJump ", "no bow and/or arrow in hotbar!", 5.0D);
/* 117 */           toggle();
/*     */         } 
/*     */         
/* 120 */         if (this.usedArrow) {
/* 121 */           mc.thePlayer.inventory.currentItem = this.lastItem;
/*     */         }
/*     */         
/* 124 */         if (mc.thePlayer.onGround && !this.usedArrow) {
/*     */           
/* 126 */           event.setPitch(-90.0F);
/* 127 */           if (mc.thePlayer.inventory.getCurrentItem() != null && 
/* 128 */             mc.thePlayer.getItemInUseDuration() < 2.7D && mc.thePlayer.inventory.getStackInSlot(mc.thePlayer.inventory.currentItem).getItem() instanceof net.minecraft.item.ItemBow) {
/* 129 */             mc.gameSettings.keyBindUseItem.pressed = true;
/*     */           }
/*     */           
/* 132 */           if (mc.thePlayer.getItemInUseDuration() > 2.7D && mc.thePlayer.inventory.getStackInSlot(mc.thePlayer.inventory.currentItem).getItem() instanceof net.minecraft.item.ItemBow) {
/* 133 */             mc.gameSettings.keyBindUseItem.pressed = false;
/* 134 */             this.usedArrow = true;
/*     */           } 
/*     */           
/* 137 */           if (mc.thePlayer.hurtTime != 0) {
/* 138 */             KeyBinding.unPressAllKeys();
/* 139 */             mc.gameSettings.keyBindForward.pressed = true;
/*     */           } 
/*     */         } 
/*     */         
/* 143 */         if (!this.hasJumped) {
/* 144 */           mc.gameSettings.keyBindForward.pressed = false;
/* 145 */           mc.gameSettings.keyBindBack.pressed = false;
/* 146 */           mc.gameSettings.keyBindLeft.pressed = false;
/* 147 */           mc.gameSettings.keyBindRight.pressed = false;
/*     */         } 
/*     */         
/* 150 */         if (mc.thePlayer.hurtTime > 0 && mc.thePlayer.onGround) {
/* 151 */           mc.thePlayer.motionY = 0.41D;
/*     */         }
/*     */ 
/*     */         
/* 155 */         if (mc.thePlayer.hurtTime > 0) {
/* 156 */           mc.timer.timerSpeed = 0.7F;
/*     */         }
/*     */         
/* 159 */         if (this.usedArrow && mc.thePlayer.onGround && mc.thePlayer.hurtTime == 0 && this.hasJumped) {
/* 160 */           toggle();
/*     */         }
/*     */         
/* 163 */         if (this.usedArrow && !mc.thePlayer.onGround) {
/*     */           
/* 165 */           event.setMotion(0.8D);
/* 166 */           this.hasJumped = true;
/* 167 */           mc.gameSettings.keyBindForward.pressed = true;
/*     */         } 
/*     */ 
/*     */         
/* 171 */         if (mc.thePlayer.hurtTime == 0) {
/* 172 */           mc.timer.timerSpeed = 1.0F;
/*     */         }
/*     */       } 
/* 175 */       if (mode.getMode() == "Hypixel") {
/*     */ 
/*     */         
/* 178 */         if (mc.thePlayer.inventory.hasItem(Item.getItemById(261)) && mc.thePlayer.inventory.hasItem(Item.getItemById(262))) {
/*     */           
/* 180 */           int current = mc.thePlayer.inventory.currentItem;
/*     */           
/* 182 */           for (int i = 0; i < 9; i++) {
/* 183 */             ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/* 184 */             if (itemStack != null && 
/* 185 */               itemStack.getItem() == Item.getItemById(261)) {
/* 186 */               current = i;
/*     */             }
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 192 */           if (mc.thePlayer.onGround && !this.usedArrow)
/*     */           {
/* 194 */             event.setPitch(-90.0F);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 199 */         if (mc.thePlayer.onGround) {
/* 200 */           mc.thePlayer.motionY = 0.4D;
/* 201 */           mc.timer.timerSpeed = 3.0F;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 206 */         if (mc.thePlayer.onGround && this.hasJumped) {
/* 207 */           toggle();
/*     */         }
/*     */         
/* 210 */         if (!mc.thePlayer.onGround) {
/*     */ 
/*     */           
/* 213 */           mc.timer.timerSpeed = 1.0F;
/* 214 */           mc.thePlayer.moveStrafing = 0.0F;
/*     */           
/* 216 */           mc.gameSettings.keyBindForward.pressed = true;
/*     */           
/* 218 */           if (mc.thePlayer.moveForward != 0.0F && mc.thePlayer.moveStrafing == 0.0F) {
/* 219 */             event.setMotion(0.4D);
/*     */           }
/* 221 */           this.hasJumped = true;
/* 222 */           mc.gameSettings.keyBindForward.pressed = true;
/*     */         } 
/*     */       } 
/*     */       
/* 226 */       if (mode.getMode() == "Custom") {
/* 227 */         if (this.spoof.isEnabled() && this.noground.isEnabled()) {
/* 228 */           this.spoof.setEnabled(false);
/* 229 */           this.noground.setEnabled(false);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 234 */         if (this.spoof.isEnabled()) {
/* 235 */           event.onGround = true;
/*     */         }
/*     */         
/* 238 */         if (this.noground.isEnabled()) {
/* 239 */           event.onGround = false;
/*     */         }
/*     */         
/* 242 */         if (mc.thePlayer.inventory.hasItem(Item.getItemById(261)) && mc.thePlayer.inventory.hasItem(Item.getItemById(262))) {
/*     */           
/* 244 */           int current = mc.thePlayer.inventory.currentItem;
/*     */           
/* 246 */           for (int i = 0; i < 9; i++) {
/* 247 */             ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/* 248 */             if (itemStack != null && 
/* 249 */               itemStack.getItem() == Item.getItemById(261)) {
/* 250 */               current = i;
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 256 */         if (mc.thePlayer.onGround) {
/* 257 */           mc.thePlayer.motionY = motiony.getValue();
/* 258 */           mc.timer.timerSpeed = (float)customtimerstart.getValue();
/*     */         } 
/*     */ 
/*     */         
/* 262 */         if (mc.thePlayer.onGround && this.hasJumped) {
/*     */           
/* 264 */           mc.thePlayer.motionY = 0.0D;
/* 265 */           mc.thePlayer.motionX = 0.0D;
/* 266 */           mc.thePlayer.motionZ = 0.0D;
/* 267 */           mc.timer.timerSpeed = 1.0F;
/* 268 */           toggle();
/*     */         } 
/*     */         
/* 271 */         if (!mc.thePlayer.onGround) {
/*     */ 
/*     */           
/* 274 */           mc.timer.timerSpeed = (float)customtimermidair.getValue() + RandomUtil.randomNumber(customtimermidairrandom.getValue(), 0.0D);
/* 275 */           mc.thePlayer.moveStrafing = 0.0F;
/*     */           
/* 277 */           mc.gameSettings.keyBindForward.pressed = true;
/*     */           
/* 279 */           if (mc.thePlayer.moveForward != 0.0F && mc.thePlayer.moveStrafing == 0.0F) {
/* 280 */             event.setMotion(motion.getValue() + RandomUtil.randomNumber(motionrandom.getValue(), 0.0D));
/*     */           }
/* 282 */           this.hasJumped = true;
/* 283 */           mc.gameSettings.keyBindForward.pressed = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int randomNumber(int max, int min) {
/* 293 */     int ii = -min + (int)(Math.random() * (max - -min + 1));
/* 294 */     return ii;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\LongJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */