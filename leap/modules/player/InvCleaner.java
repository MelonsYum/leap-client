/*     */ package leap.modules.player;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.MoveUtil;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemPotion;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C09PacketHeldItemChange;
/*     */ import net.minecraft.network.play.client.C0DPacketCloseWindow;
/*     */ import net.minecraft.network.play.client.C16PacketClientStatus;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InvCleaner
/*     */   extends Module
/*     */ {
/*  32 */   public Random rand = new Random();
/*     */   
/*  34 */   public Timer timerrf = new Timer();
/*     */   
/*     */   private int slots;
/*     */   private double enchant;
/*     */   private boolean clean;
/*  39 */   public static NumberSetting delay = new NumberSetting("Delay", 100.0D, 100.0D, 500.0D, 10.0D);
/*  40 */   public BooleanSetting inv = new BooleanSetting("Open Inventory", true);
/*     */   
/*     */   public InvCleaner() {
/*  43 */     super("InvCleaner", 0, Module.Category.PLAYER);
/*  44 */     addSettings(new Setting[] { (Setting)delay, (Setting)this.inv });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  48 */     this.timerrf.reset();
/*  49 */     this.slots = 9;
/*  50 */     this.enchant = getEnchantmentOnSword(mc.thePlayer.getHeldItem());
/*  51 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  55 */     super.onDisable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {
/*  60 */     if (e instanceof leap.events.listeners.EventUpdate && 
/*  61 */       mc.thePlayer != null) {
/*  62 */       if (this.slots >= 45 && !this.clean) {
/*  63 */         this.timerrf.reset();
/*  64 */         this.slots = 9;
/*     */         
/*     */         return;
/*     */       } 
/*  68 */       if ((!(mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory) && MoveUtil.isMoving()) || (
/*  69 */         mc.thePlayer.openContainer != null && mc.thePlayer.openContainer.windowId != 0)) {
/*     */         return;
/*     */       }
/*  72 */       if (this.inv.isEnabled() && 
/*  73 */         !(mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiInventory)) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  78 */       if (mc.thePlayer.isSwingInProgress) {
/*  79 */         this.timerrf.reset();
/*  80 */         this.slots = 9;
/*     */         
/*     */         return;
/*     */       } 
/*  84 */       if (mc.thePlayer.isUsingItem()) {
/*  85 */         this.timerrf.reset();
/*  86 */         this.slots = 9;
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  91 */       if (this.clean) {
/*  92 */         if (this.timerrf.hasTimeElapsed((long)delay.getValue(), true)) {
/*  93 */           sendNetPacket((Packet)new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
/*  94 */           mc.playerController.windowClick(0, -999, 0, 0, (EntityPlayer)mc.thePlayer);
/*  95 */           sendPacket((Packet)new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem));
/*  96 */           sendNetPacket((Packet)new C0DPacketCloseWindow());
/*  97 */           mc.playerController.syncCurrentPlayItem();
/*  98 */           this.clean = false;
/*     */           
/* 100 */           this.timerrf.reset();
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/* 105 */       if (this.timerrf.hasTimeElapsed((long)delay.getValue(), true))
/* 106 */         this.enchant = getEnchantmentOnSword(mc.thePlayer.getHeldItem()); 
/* 107 */       ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(this.slots).getStack();
/* 108 */       if (isItemBad(stack) && !isInLobby(stack) && getEnchantmentOnSword(stack) <= this.enchant && 
/* 109 */         stack != mc.thePlayer.getHeldItem()) {
/* 110 */         if (this.inv.isEnabled()) {
/* 111 */           sendNetPacket((Packet)new C16PacketClientStatus(C16PacketClientStatus.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
/*     */         }
/* 113 */         mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, this.slots, 0, 0, (EntityPlayer)mc.thePlayer);
/* 114 */         if (this.inv.isEnabled()) {
/* 115 */           sendNetPacket((Packet)new C0DPacketCloseWindow());
/*     */         }
/* 117 */         this.timerrf.reset();
/*     */       } 
/* 119 */       this.slots++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isItemBad(ItemStack item) {
/* 127 */     return (item != null && (item.getItem().getUnlocalizedName().contains("TNT") || 
/* 128 */       item.getItem().getUnlocalizedName().contains("stick") || 
/* 129 */       item.getItem().getUnlocalizedName().contains("egg") || 
/* 130 */       item.getItem().getUnlocalizedName().contains("string") || 
/* 131 */       item.getItem().getUnlocalizedName().contains("flint") || 
/* 132 */       item.getItem().getUnlocalizedName().contains("compass") || 
/* 133 */       item.getItem().getUnlocalizedName().contains("feather") || 
/* 134 */       item.getItem().getUnlocalizedName().contains("bucket") || 
/* 135 */       item.getItem().getUnlocalizedName().contains("chest") || 
/* 136 */       item.getItem().getUnlocalizedName().contains("snowball") || 
/* 137 */       item.getItem().getUnlocalizedName().contains("fish") || 
/* 138 */       item.getItem().getUnlocalizedName().contains("enchant") || 
/* 139 */       item.getItem().getUnlocalizedName().contains("exp") || item.getItem() instanceof net.minecraft.item.ItemPickaxe || 
/* 140 */       item.getItem() instanceof net.minecraft.item.ItemTool || item.getItem() instanceof net.minecraft.item.ItemArmor || 
/* 141 */       item.getItem() instanceof ItemSword || (
/* 142 */       item.getItem().getUnlocalizedName().contains("potion") && isBadPotion(item))));
/*     */   }
/*     */   
/*     */   public static boolean isInLobby(ItemStack item) {
/* 146 */     return !((item == null || (!item.getItem().getUnlocalizedName().contains("TNT") && 
/* 147 */       !item.getDisplayName().contains("Shop"))) && 
/* 148 */       !item.getDisplayName().contains("Game") && 
/* 149 */       !item.getDisplayName().contains("Collectibles") && 
/* 150 */       !item.getDisplayName().contains("Lobby") && 
/* 151 */       !item.getDisplayName().contains("Teleport") && 
/* 152 */       !item.getDisplayName().contains("Spectator") && 
/* 153 */       !item.getDisplayName().contains("Profile") && 
/* 154 */       !item.getDisplayName().contains("My") && 
/* 155 */       !item.getDisplayName().contains("Setting") && 
/* 156 */       !item.getDisplayName().contains("Player"));
/*     */   }
/*     */   
/*     */   public static boolean isBadPotion(ItemStack itemStack) {
/* 160 */     if (itemStack == null) {
/* 161 */       return false;
/*     */     }
/* 163 */     if (!(itemStack.getItem() instanceof ItemPotion)) {
/* 164 */       return false;
/*     */     }
/* 166 */     ItemPotion itemPotion = (ItemPotion)itemStack.getItem();
/* 167 */     Iterator iterator = itemPotion.getEffects(itemStack).iterator();
/*     */     
/*     */     while (true) {
/* 170 */       if (!iterator.hasNext()) {
/* 171 */         return false;
/*     */       }
/* 173 */       Object pObj = iterator.next();
/* 174 */       PotionEffect potionEffect = (PotionEffect)pObj;
/* 175 */       if (potionEffect.getPotionID() == Potion.poison.getId()) {
/* 176 */         return true;
/*     */       }
/* 178 */       if (potionEffect.getPotionID() == Potion.moveSlowdown.getId()) {
/* 179 */         return true;
/*     */       }
/* 181 */       if (potionEffect.getPotionID() == Potion.harm.getId())
/* 182 */         return true; 
/*     */     } 
/*     */   }
/*     */   private static double getEnchantmentOnSword(ItemStack itemStack) {
/* 186 */     if (itemStack == null) {
/* 187 */       return 0.0D;
/*     */     }
/* 189 */     if (!(itemStack.getItem() instanceof ItemSword)) {
/* 190 */       return 0.0D;
/*     */     }
/* 192 */     ItemSword itemSword = (ItemSword)itemStack.getItem();
/* 193 */     return (EnchantmentHelper.getEnchantmentLevel(Enchantment.field_180314_l.effectId, itemStack) + 
/* 194 */       itemSword.field_150934_a);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\InvCleaner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */