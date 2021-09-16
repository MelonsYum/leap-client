/*     */ package leap.modules.player;
/*     */ 
/*     */ import java.util.Random;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemArmor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C0DPacketCloseWindow;
/*     */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoArmor
/*     */   extends Module
/*     */ {
/*     */   private Timer dropStopwatch;
/*     */   private Timer equipStopwatch;
/*     */   public boolean cleaning;
/*     */   public boolean equipping;
/*     */   public boolean swappingSword;
/*     */   public boolean opened;
/*     */   public int exploitTime;
/*     */   private boolean guiOpenedByMod;
/*     */   public static boolean pause = false;
/*  33 */   public static NumberSetting delay = new NumberSetting("Delay", 170.0D, 100.0D, 500.0D, 10.0D);
/*     */   
/*     */   public AutoArmor() {
/*  36 */     super("AutoArmor", 0, Module.Category.PLAYER);
/*  37 */     this.dropStopwatch = new Timer();
/*  38 */     this.equipStopwatch = new Timer();
/*  39 */     addSettings(new Setting[] { (Setting)delay });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  43 */     this.equipping = false;
/*  44 */     this.guiOpenedByMod = false;
/*  45 */     this.equipStopwatch.reset();
/*  46 */     this.dropStopwatch.reset();
/*     */     
/*  48 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  52 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public static double getProtectionValue(ItemStack stack) {
/*  56 */     return !(stack.getItem() instanceof ItemArmor) ? 0.0D : (((ItemArmor)stack.getItem()).damageReduceAmount + ((100 - ((ItemArmor)stack.getItem()).damageReduceAmount * 4) * EnchantmentHelper.getEnchantmentLevel(Enchantment.field_180310_c.effectId, stack) * 4) * 0.0075D);
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  60 */     if (e instanceof leap.events.listeners.EventUpdate && 
/*  61 */       mc.thePlayer != null && (mc.currentScreen == null || mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory)) {
/*  62 */       int slotID = -1;
/*  63 */       double maxProt = -1.0D;
/*  64 */       int switchArmor = -1;
/*     */       
/*  66 */       for (int i = 9; i < 45; i++) {
/*  67 */         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
/*  68 */         if (stack != null && (canEquip(stack) || (betterCheck(stack) && !canEquip(stack)))) {
/*  69 */           if (betterCheck(stack) && switchArmor == -1) {
/*  70 */             switchArmor = betterSwap(stack);
/*     */           }
/*     */           
/*  73 */           double protValue = getProtectionValue(stack);
/*  74 */           if (protValue >= maxProt) {
/*  75 */             slotID = i;
/*  76 */             maxProt = protValue;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  81 */       if (slotID != -1 && Client.invCooldownElapsed((long)(delay.getValue() + range(0.0D, 30.0D)))) {
/*  82 */         if (!(mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory)) {
/*  83 */           mc.getNetHandler().addToSendQueue((Packet)new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
/*     */         }
/*  85 */         if (switchArmor != -1) {
/*  86 */           mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, 4 + switchArmor, 0, 0, (EntityPlayer)mc.thePlayer);
/*  87 */           mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, -999, 0, 0, (EntityPlayer)mc.thePlayer);
/*     */         } 
/*     */         
/*  90 */         mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, slotID, 0, 1, (EntityPlayer)mc.thePlayer);
/*  91 */         if (!(mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory)) {
/*  92 */           mc.thePlayer.sendQueue.addToSendQueue((Packet)new C0DPacketCloseWindow(mc.thePlayer.openContainer.windowId));
/*     */         }
/*  94 */         Client.invCooldownElapsed(0L);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int betterSwap(ItemStack stack) {
/* 102 */     if (stack.getItem() instanceof ItemArmor) {
/* 103 */       if (mc.thePlayer.getEquipmentInSlot(4) != null && stack.getUnlocalizedName().contains("helmet") && getProtectionValue(stack) + ((ItemArmor)stack.getItem()).damageReduceAmount > getProtectionValue(mc.thePlayer.getEquipmentInSlot(4)) + ((ItemArmor)mc.thePlayer.getEquipmentInSlot(4).getItem()).damageReduceAmount) {
/* 104 */         return 1;
/*     */       }
/*     */       
/* 107 */       if (mc.thePlayer.getEquipmentInSlot(3) != null && stack.getUnlocalizedName().contains("chestplate") && getProtectionValue(stack) + ((ItemArmor)stack.getItem()).damageReduceAmount > getProtectionValue(mc.thePlayer.getEquipmentInSlot(3)) + ((ItemArmor)mc.thePlayer.getEquipmentInSlot(3).getItem()).damageReduceAmount) {
/* 108 */         return 2;
/*     */       }
/*     */       
/* 111 */       if (mc.thePlayer.getEquipmentInSlot(2) != null && stack.getUnlocalizedName().contains("leggings") && getProtectionValue(stack) + ((ItemArmor)stack.getItem()).damageReduceAmount > getProtectionValue(mc.thePlayer.getEquipmentInSlot(2)) + ((ItemArmor)mc.thePlayer.getEquipmentInSlot(2).getItem()).damageReduceAmount) {
/* 112 */         return 3;
/*     */       }
/*     */       
/* 115 */       if (mc.thePlayer.getEquipmentInSlot(1) != null && stack.getUnlocalizedName().contains("boots") && getProtectionValue(stack) + ((ItemArmor)stack.getItem()).damageReduceAmount > getProtectionValue(mc.thePlayer.getEquipmentInSlot(1)) + ((ItemArmor)mc.thePlayer.getEquipmentInSlot(1).getItem()).damageReduceAmount) {
/* 116 */         return 4;
/*     */       }
/*     */     } 
/*     */     
/* 120 */     return -1;
/*     */   }
/*     */   
/*     */   private boolean canEquip(ItemStack stack) {
/* 124 */     return !((mc.thePlayer.getEquipmentInSlot(1) != null || !stack.getUnlocalizedName().contains("boots")) && (mc.thePlayer.getEquipmentInSlot(2) != null || !stack.getUnlocalizedName().contains("leggings")) && (mc.thePlayer.getEquipmentInSlot(3) != null || !stack.getUnlocalizedName().contains("chestplate")) && (mc.thePlayer.getEquipmentInSlot(4) != null || !stack.getUnlocalizedName().contains("helmet")));
/*     */   }
/*     */   
/*     */   public boolean betterCheck(ItemStack stack) {
/* 128 */     if (stack.getItem() instanceof ItemArmor) {
/* 129 */       if (mc.thePlayer.getEquipmentInSlot(1) != null && stack.getUnlocalizedName().contains("boots") && getProtectionValue(stack) + ((ItemArmor)stack.getItem()).damageReduceAmount > getProtectionValue(mc.thePlayer.getEquipmentInSlot(1)) + ((ItemArmor)mc.thePlayer.getEquipmentInSlot(1).getItem()).damageReduceAmount) {
/* 130 */         return true;
/*     */       }
/*     */       
/* 133 */       if (mc.thePlayer.getEquipmentInSlot(2) != null && stack.getUnlocalizedName().contains("leggings") && getProtectionValue(stack) + ((ItemArmor)stack.getItem()).damageReduceAmount > getProtectionValue(mc.thePlayer.getEquipmentInSlot(2)) + ((ItemArmor)mc.thePlayer.getEquipmentInSlot(2).getItem()).damageReduceAmount) {
/* 134 */         return true;
/*     */       }
/*     */       
/* 137 */       if (mc.thePlayer.getEquipmentInSlot(3) != null && stack.getUnlocalizedName().contains("chestplate") && getProtectionValue(stack) + ((ItemArmor)stack.getItem()).damageReduceAmount > getProtectionValue(mc.thePlayer.getEquipmentInSlot(3)) + ((ItemArmor)mc.thePlayer.getEquipmentInSlot(3).getItem()).damageReduceAmount) {
/* 138 */         return true;
/*     */       }
/*     */       
/* 141 */       return (mc.thePlayer.getEquipmentInSlot(4) != null && stack.getUnlocalizedName().contains("helmet") && getProtectionValue(stack) + ((ItemArmor)stack.getItem()).damageReduceAmount > getProtectionValue(mc.thePlayer.getEquipmentInSlot(4)) + ((ItemArmor)mc.thePlayer.getEquipmentInSlot(4).getItem()).damageReduceAmount);
/*     */     } 
/*     */     
/* 144 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void doPause() {
/* 151 */     pause = true;
/*     */   }
/*     */   public static void stopPause() {
/* 154 */     pause = false;
/*     */   }
/*     */   
/*     */   public static double range(double min, double max) {
/* 158 */     return min + (new Random()).nextDouble() * (max - min);
/*     */   }
/*     */   
/*     */   public static interface Action {
/*     */     void execute();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\AutoArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */