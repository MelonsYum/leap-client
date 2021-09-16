/*     */ package leap.modules.movement;
/*     */ 
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.modules.Module;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class PearlFly
/*     */   extends Module
/*     */ {
/*     */   boolean hasThrown = false;
/*     */   boolean isFlying = false;
/*     */   int last;
/*  19 */   Timer timerz = new Timer();
/*     */   
/*  21 */   Timer timer = new Timer();
/*     */   public PearlFly() {
/*  23 */     super("PearlFly", 0, Module.Category.MOVEMENT);
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  27 */     this.last = mc.thePlayer.inventory.currentItem;
/*  28 */     this.hasThrown = false;
/*  29 */     this.isFlying = false;
/*  30 */     this.timerz.reset();
/*  31 */     this.timer.reset();
/*     */     
/*  33 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  37 */     mc.thePlayer.capabilities.isFlying = false;
/*  38 */     mc.thePlayer.capabilities.allowFlying = false;
/*  39 */     mc.thePlayer.capabilities.setFlySpeed(0.1F);
/*  40 */     mc.timer.timerSpeed = 1.0F;
/*     */     
/*  42 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  46 */     if (e instanceof EventMotion) {
/*     */       
/*  48 */       EventMotion event = (EventMotion)e;
/*     */       
/*  50 */       if (!mc.thePlayer.capabilities.isFlying && this.hasThrown) {
/*  51 */         this.isFlying = false;
/*  52 */         toggle();
/*     */       } 
/*     */       
/*  55 */       if (mc.thePlayer.capabilities.isFlying) {
/*  56 */         this.isFlying = true;
/*     */       }
/*     */       
/*  59 */       for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*  60 */         ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/*  61 */         if (itemStack != null && 
/*  62 */           itemStack.getItem() instanceof net.minecraft.item.ItemEnderPearl) {
/*  63 */           mc.thePlayer.inventory.currentItem = i;
/*     */         }
/*     */       } 
/*     */       
/*  67 */       if (mc.thePlayer.inventory.getCurrentItem() != null && !this.hasThrown) {
/*  68 */         if (mc.thePlayer.inventory.getStackInSlot(mc.thePlayer.inventory.currentItem).getItem() instanceof net.minecraft.item.ItemEnderPearl) {
/*  69 */           event.setPitch(90.0F);
/*     */ 
/*     */           
/*  72 */           if (this.timerz.hasTimeElapsed(100L, true)) {
/*  73 */             mc.playerController.sendUseItem((EntityPlayer)mc.thePlayer, (World)mc.theWorld, mc.thePlayer.inventory.getStackInSlot(mc.thePlayer.inventory.currentItem));
/*  74 */             mc.thePlayer.inventory.currentItem = this.last;
/*     */             
/*  76 */             mc.thePlayer.jump();
/*     */             
/*  78 */             mc.thePlayer.capabilities.allowFlying = true;
/*  79 */             mc.thePlayer.capabilities.isFlying = true;
/*     */             
/*  81 */             this.isFlying = true;
/*  82 */             this.hasThrown = true;
/*     */           } 
/*     */         } else {
/*     */           
/*  86 */           sendClientMessage("PearlFly:", "no pearls in hotbar!", 5.0D);
/*  87 */           toggle();
/*     */         } 
/*     */       }
/*  90 */       if (this.timer.hasTimeElapsed(2000L, true)) {
/*  91 */         mc.thePlayer.capabilities.allowFlying = false;
/*  92 */         mc.thePlayer.capabilities.isFlying = false;
/*  93 */         toggle();
/*     */       } 
/*     */ 
/*     */       
/*  97 */       if (this.hasThrown);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int randomNumber(int max, int min) {
/* 105 */     int ii = -min + (int)(Math.random() * (max - -min + 1));
/* 106 */     return ii;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\PearlFly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */