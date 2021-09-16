/*     */ package leap.modules.combat;
/*     */ 
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.modules.Module;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C03PacketPlayer;
/*     */ import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
/*     */ import net.minecraft.network.play.client.C09PacketHeldItemChange;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.util.BlockPos;
/*     */ 
/*     */ public class AutoPotion
/*     */   extends Module {
/*  21 */   private final Timer timer = new Timer();
/*     */   public boolean potting;
/*     */   private float prevPitch;
/*     */   
/*     */   public AutoPotion() {
/*  26 */     super("AutoPotion", 0, Module.Category.COMBAT);
/*  27 */     addSettings(new leap.settings.Setting[0]);
/*     */   }
/*     */   
/*     */   public static boolean ReachEnabled = false;
/*     */   
/*     */   public void onEnable() {
/*  33 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  37 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  41 */     if (e instanceof EventMotion) {
/*     */       
/*  43 */       EventMotion event = (EventMotion)e;
/*     */       
/*  45 */       int prevSlot = mc.thePlayer.inventory.currentItem;
/*     */       
/*  47 */       if (KillAura.targets != null && 
/*  48 */         KillAura.targets.isEmpty()) {
/*  49 */         if (!Client.getModule("Fly").isEnabled()) if (!Client.getModule("Scaffold").isEnabled() && mc.thePlayer.onGround) {
/*  50 */             if (e.isPre()) {
/*  51 */               if (!mc.thePlayer.isPotionActive(Potion.moveSpeed) && 
/*  52 */                 this.timer.hasTimeElapsed(50L, this.potting)) {
/*  53 */                 if (isSpeedPotsInHotBar() && !mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
/*  54 */                   int index; for (index = 36; index < 45; index++) {
/*  55 */                     ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/*  56 */                     if (stack != null)
/*     */                     {
/*  58 */                       if (isStackSplashSpeedPot(stack)) {
/*  59 */                         this.potting = true;
/*  60 */                         this.prevPitch = mc.thePlayer.rotationPitch;
/*     */                       } 
/*     */                     }
/*     */                   } 
/*  64 */                   for (index = 36; index < 45; index++) {
/*  65 */                     ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/*  66 */                     if (stack != null)
/*     */                     {
/*  68 */                       if (isStackSplashSpeedPot(stack) && this.potting) {
/*  69 */                         event.setPitch(89.0F);
/*  70 */                         mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, 89.0F, mc.thePlayer.onGround));
/*  71 */                         mc.getNetHandler().addToSendQueue((Packet)new C09PacketHeldItemChange(index - 36));
/*  72 */                         mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.thePlayer.getHeldItem(), 0.0F, 0.0F, 0.0F));
/*  73 */                         mc.getNetHandler().addToSendQueue((Packet)new C09PacketHeldItemChange(prevSlot));
/*  74 */                         mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, this.prevPitch, mc.thePlayer.onGround));
/*     */                         break;
/*     */                       } 
/*     */                     }
/*     */                   } 
/*  79 */                   this.timer.reset();
/*  80 */                   this.potting = false;
/*     */                 } else {
/*  82 */                   getSpeedPotsFromInventory();
/*     */                 } 
/*     */               }
/*     */ 
/*     */               
/*  87 */               if (!mc.thePlayer.isPotionActive(Potion.regeneration) && mc.thePlayer.getHealth() <= 12.0F && 
/*  88 */                 this.timer.hasTimeElapsed(50L, true)) {
/*  89 */                 if (isRegenPotsInHotBar()) {
/*  90 */                   int index; for (index = 36; index < 45; index++) {
/*  91 */                     ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/*  92 */                     if (stack != null)
/*     */                     {
/*  94 */                       if (isStackSplashRegenPot(stack)) {
/*  95 */                         this.potting = true;
/*  96 */                         this.prevPitch = mc.thePlayer.rotationPitch;
/*     */                       }  } 
/*     */                   } 
/*  99 */                   for (index = 36; index < 45; index++) {
/* 100 */                     ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 101 */                     if (stack != null)
/*     */                     {
/* 103 */                       if (isStackSplashRegenPot(stack)) {
/* 104 */                         mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, 89.0F, mc.thePlayer.onGround));
/* 105 */                         mc.getNetHandler().addToSendQueue((Packet)new C09PacketHeldItemChange(index - 36));
/* 106 */                         mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.thePlayer.getHeldItem(), 0.0F, 0.0F, 0.0F));
/* 107 */                         mc.getNetHandler().addToSendQueue((Packet)new C09PacketHeldItemChange(prevSlot));
/* 108 */                         mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, this.prevPitch, mc.thePlayer.onGround));
/*     */                         break;
/*     */                       }  } 
/*     */                   } 
/* 112 */                   this.timer.reset();
/* 113 */                   this.potting = false;
/*     */                 } else {
/* 115 */                   getRegenPotsFromInventory();
/*     */                 } 
/*     */               }
/*     */ 
/*     */               
/* 120 */               if (mc.thePlayer.getHealth() <= 12.0F && 
/* 121 */                 this.timer.hasTimeElapsed(50L, true)) {
/* 122 */                 if (isHealthPotsInHotBar()) {
/* 123 */                   int index; for (index = 36; index < 45; index++) {
/* 124 */                     ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 125 */                     if (stack != null)
/*     */                     {
/* 127 */                       if (isStackSplashHealthPot(stack)) {
/* 128 */                         this.potting = true;
/* 129 */                         this.prevPitch = mc.thePlayer.rotationPitch;
/*     */                       }  } 
/*     */                   } 
/* 132 */                   for (index = 36; index < 45; index++) {
/* 133 */                     ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 134 */                     if (stack != null)
/*     */                     {
/* 136 */                       if (isStackSplashHealthPot(stack)) {
/* 137 */                         mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, 89.0F, mc.thePlayer.onGround));
/* 138 */                         mc.getNetHandler().addToSendQueue((Packet)new C09PacketHeldItemChange(index - 36));
/* 139 */                         mc.getNetHandler().addToSendQueue((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.thePlayer.getHeldItem(), 0.0F, 0.0F, 0.0F));
/* 140 */                         mc.getNetHandler().addToSendQueue((Packet)new C09PacketHeldItemChange(prevSlot));
/* 141 */                         mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, this.prevPitch, mc.thePlayer.onGround));
/*     */                         break;
/*     */                       }  } 
/*     */                   } 
/* 145 */                   this.timer.reset();
/* 146 */                   this.potting = false;
/*     */                 } else {
/* 148 */                   getPotsFromInventory();
/*     */                 }
/*     */               
/*     */               }
/* 152 */             } else if (e.isPost()) {
/* 153 */               this.potting = false;
/*     */             } 
/*     */           }
/*     */       
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isSpeedPotsInHotBar() {
/* 162 */     for (int index = 36; index < 45; index++) {
/* 163 */       ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 164 */       if (stack != null)
/*     */       {
/* 166 */         if (!stack.getDisplayName().contains("Frog") && 
/* 167 */           isStackSplashSpeedPot(stack))
/* 168 */           return true;  } 
/*     */     } 
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isHealthPotsInHotBar() {
/* 174 */     for (int index = 36; index < 45; index++) {
/* 175 */       ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 176 */       if (stack != null)
/*     */       {
/* 178 */         if (isStackSplashHealthPot(stack))
/* 179 */           return true;  } 
/*     */     } 
/* 181 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isRegenPotsInHotBar() {
/* 185 */     for (int index = 36; index < 45; index++) {
/* 186 */       ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 187 */       if (stack != null)
/*     */       {
/* 189 */         if (isStackSplashRegenPot(stack))
/* 190 */           return true;  } 
/*     */     } 
/* 192 */     return false;
/*     */   }
/*     */   
/*     */   private void getSpeedPotsFromInventory() {
/* 196 */     if (mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiChest)
/*     */       return; 
/* 198 */     for (int index = 9; index < 36; index++) {
/* 199 */       ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 200 */       if (stack != null)
/*     */       {
/* 202 */         if (!stack.getDisplayName().contains("Frog") && 
/* 203 */           isStackSplashSpeedPot(stack)) {
/* 204 */           mc.playerController.windowClick(0, index, 6, 2, (EntityPlayer)mc.thePlayer);
/*     */           break;
/*     */         }  } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getPotionCount() {
/* 211 */     int count = 0;
/* 212 */     for (int index = 0; index < 45; index++) {
/* 213 */       ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 214 */       if (stack != null)
/*     */       {
/* 216 */         if (isStackSplashHealthPot(stack) || isStackSplashHealthPot(stack) || isStackSplashRegenPot(stack))
/* 217 */           count++;  } 
/*     */     } 
/* 219 */     return count;
/*     */   }
/*     */   
/*     */   private void getPotsFromInventory() {
/* 223 */     if (mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiChest)
/*     */       return; 
/* 225 */     for (int index = 9; index < 36; index++) {
/* 226 */       ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 227 */       if (stack != null)
/*     */       {
/* 229 */         if (isStackSplashHealthPot(stack)) {
/* 230 */           mc.playerController.windowClick(0, index, 6, 2, (EntityPlayer)mc.thePlayer);
/*     */           break;
/*     */         }  } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isStackSplashSpeedPot(ItemStack stack) {
/* 237 */     if (stack == null) {
/* 238 */       return false;
/*     */     }
/* 240 */     if (stack.getItem() instanceof ItemPotion) {
/* 241 */       ItemPotion potion = (ItemPotion)stack.getItem();
/* 242 */       if (ItemPotion.isSplash(stack.getItemDamage())) {
/* 243 */         for (Object o : potion.getEffects(stack)) {
/* 244 */           PotionEffect effect = (PotionEffect)o;
/* 245 */           if (stack.getDisplayName().contains("Frog")) return false; 
/* 246 */           if (effect.getPotionID() == Potion.moveSpeed.id && effect.getPotionID() != Potion.jump.id) {
/* 247 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 252 */     return false;
/*     */   }
/*     */   
/*     */   private boolean isStackSplashHealthPot(ItemStack stack) {
/* 256 */     if (stack == null) {
/* 257 */       return false;
/*     */     }
/* 259 */     if (stack.getItem() instanceof ItemPotion) {
/* 260 */       ItemPotion potion = (ItemPotion)stack.getItem();
/* 261 */       if (ItemPotion.isSplash(stack.getItemDamage())) {
/* 262 */         for (Object o : potion.getEffects(stack)) {
/* 263 */           PotionEffect effect = (PotionEffect)o;
/* 264 */           if (effect.getPotionID() == Potion.heal.id) {
/* 265 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 270 */     return false;
/*     */   }
/*     */   
/*     */   private void getRegenPotsFromInventory() {
/* 274 */     if (mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiChest)
/*     */       return; 
/* 276 */     for (int index = 9; index < 36; index++) {
/* 277 */       ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
/* 278 */       if (stack != null)
/*     */       {
/* 280 */         if (isStackSplashRegenPot(stack)) {
/* 281 */           mc.playerController.windowClick(0, index, 6, 2, (EntityPlayer)mc.thePlayer);
/*     */           break;
/*     */         }  } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isStackSplashRegenPot(ItemStack stack) {
/* 288 */     if (stack == null) {
/* 289 */       return false;
/*     */     }
/* 291 */     if (stack.getItem() instanceof ItemPotion) {
/* 292 */       ItemPotion potion = (ItemPotion)stack.getItem();
/* 293 */       if (ItemPotion.isSplash(stack.getItemDamage())) {
/* 294 */         for (Object o : potion.getEffects(stack)) {
/* 295 */           PotionEffect effect = (PotionEffect)o;
/* 296 */           if (effect.getPotionID() == Potion.regeneration.id) {
/* 297 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/* 302 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\AutoPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */