/*    */ package leap.modules.player;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.ContainerChest;
/*    */ 
/*    */ public class ChestSteal
/*    */   extends Module
/*    */ {
/* 16 */   public static BooleanSetting silent = new BooleanSetting("Silent", true);
/*    */   
/* 18 */   public static ModeSetting pickup = new ModeSetting("PickUp", "All", new String[] { "Single", "All" });
/*    */   
/* 20 */   public NumberSetting delays = new NumberSetting("Delay", 1.0D, 0.0D, 10.0D, 1.0D);
/*    */   
/*    */   public ChestSteal() {
/* 23 */     super("ChestSteal", 0, Module.Category.PLAYER);
/* 24 */     addSettings(new Setting[] { (Setting)pickup, (Setting)this.delays, (Setting)silent });
/*    */   }
/*    */   
/*    */   int slot;
/*    */   double delay;
/*    */   
/*    */   public void onDisable() {
/* 31 */     super.onDisable();
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 35 */     super.onEnable();
/*    */   }
/*    */   
/*    */   public void onEvent(Event e) {
/* 39 */     if (e instanceof leap.events.listeners.EventUpdate) {
/*    */ 
/*    */       
/* 42 */       if (pickup.getMode() == "Single" && 
/* 43 */         mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiChest) {
/*    */         
/* 45 */         ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth / 2, mc.displayHeight / 2);
/*    */ 
/*    */ 
/*    */         
/* 49 */         ContainerChest kiste = (ContainerChest)mc.thePlayer.openContainer;
/*    */         
/* 51 */         this.delay++;
/* 52 */         this.slot++;
/*    */ 
/*    */         
/* 55 */         if (this.slot > kiste.getLowerChestInventory().getSizeInventory()) {
/* 56 */           this.slot = 0;
/*    */         }
/*    */         
/* 59 */         if (this.slot == kiste.getLowerChestInventory().getSizeInventory()) {
/* 60 */           mc.thePlayer.closeScreen();
/*    */         }
/*    */         
/* 63 */         if (this.delay > this.delays.getValue() && kiste.getLowerChestInventory().getStackInSlot(this.slot) != null) {
/* 64 */           mc.playerController.windowClick(kiste.windowId, this.slot, 0, 1, (EntityPlayer)mc.thePlayer);
/* 65 */           this.delay = 0.0D;
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 70 */       if (pickup.getMode() == "All" && 
/* 71 */         mc.currentScreen instanceof net.minecraft.client.gui.inventory.GuiChest) {
/*    */         
/* 73 */         ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth / 2, mc.displayHeight / 2);
/*    */ 
/*    */ 
/*    */         
/* 77 */         ContainerChest kiste = (ContainerChest)mc.thePlayer.openContainer;
/*    */         
/* 79 */         for (int i = 0; i < kiste.getLowerChestInventory().getSizeInventory(); i++) {
/*    */           
/* 81 */           if (kiste.getLowerChestInventory().getStackInSlot(i) != null)
/* 82 */             mc.playerController.windowClick(kiste.windowId, i, 0, 1, (EntityPlayer)mc.thePlayer); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\ChestSteal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */