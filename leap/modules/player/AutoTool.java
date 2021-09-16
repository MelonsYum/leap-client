/*     */ package leap.modules.player;
/*     */ 
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoTool
/*     */   extends Module
/*     */ {
/*     */   public AutoTool() {
/*  31 */     super("AutoTool", 0, Module.Category.PLAYER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {
/*  36 */     if (e instanceof leap.events.listeners.EventUpdate) {
/*     */       
/*  38 */       if (mc.objectMouseOver == null) {
/*     */         return;
/*     */       }
/*     */       
/*  42 */       if (mc.objectMouseOver.typeOfHit == null) {
/*     */         return;
/*     */       }
/*     */       
/*  46 */       if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && mc.gameSettings.keyBindAttack.pressed && !mc.gameSettings.keyBindUseItem.pressed) {
/*     */         
/*  48 */         Material blockstate = mc.theWorld.getBlockState(mc.objectMouseOver.func_178782_a()).getBlock().getMaterial();
/*     */         
/*  50 */         if (blockstate != Material.air) {
/*     */ 
/*     */           
/*  53 */           if (blockstate == Material.wood) {
/*  54 */             for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*     */               
/*  56 */               ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/*  57 */               if (itemStack != null && 
/*  58 */                 itemStack.getItem() instanceof net.minecraft.item.ItemAxe) {
/*  59 */                 mc.thePlayer.inventory.currentItem = i;
/*     */               }
/*     */             } 
/*     */           }
/*     */           
/*  64 */           if (blockstate == Material.ground || blockstate == Material.grass || blockstate == Material.sand) {
/*  65 */             for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*     */               
/*  67 */               ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/*  68 */               if (itemStack != null && 
/*  69 */                 itemStack.getItem() instanceof net.minecraft.item.ItemSpade) {
/*  70 */                 mc.thePlayer.inventory.currentItem = i;
/*     */               }
/*     */             } 
/*     */           }
/*     */           
/*  75 */           if (blockstate == Material.rock) {
/*  76 */             for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*     */               
/*  78 */               ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/*  79 */               if (itemStack != null && 
/*  80 */                 itemStack.getItem() instanceof net.minecraft.item.ItemPickaxe) {
/*  81 */                 mc.thePlayer.inventory.currentItem = i;
/*     */               }
/*     */             } 
/*     */           }
/*     */           
/*  86 */           if (blockstate == Material.leaves || blockstate == Material.cloth) {
/*  87 */             for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*     */               
/*  89 */               ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/*  90 */               if (itemStack != null && 
/*  91 */                 itemStack.getItem() instanceof net.minecraft.item.ItemShears) {
/*  92 */                 mc.thePlayer.inventory.currentItem = i;
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 100 */       if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.thePlayer.isSwingInProgress) {
/* 101 */         for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/*     */           
/* 103 */           ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
/* 104 */           if (itemStack != null && 
/* 105 */             itemStack.getItem() instanceof net.minecraft.item.ItemSword) {
/*     */             
/* 107 */             if (mc.thePlayer.inventory.getCurrentItem() != null && 
/* 108 */               mc.thePlayer.inventory.getCurrentItem().getItem() instanceof net.minecraft.item.ItemAppleGold) {
/*     */               return;
/*     */             }
/*     */ 
/*     */             
/* 113 */             mc.thePlayer.inventory.currentItem = i;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 122 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/* 126 */     super.onDisable();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\AutoTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */