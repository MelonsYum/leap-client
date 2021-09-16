/*     */ package leap.modules.player;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventReceivePacket;
/*     */ import leap.modules.Module;
/*     */ import leap.util.RenderUtil;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C07PacketPlayerDigging;
/*     */ import net.minecraft.network.play.client.C09PacketHeldItemChange;
/*     */ import net.minecraft.network.play.server.S02PacketChat;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CakeEater
/*     */   extends Module
/*     */ {
/*     */   private int[] bestArmor;
/*  32 */   public Timer timer = new Timer();
/*     */   BlockPos pos;
/*     */   Block pblock;
/*     */   Block sblock;
/*     */   int xpos;
/*     */   int ypos;
/*     */   int zpos;
/*  39 */   List<String> teamcake = new ArrayList<>();
/*     */   
/*     */   public CakeEater() {
/*  42 */     super("CakeEater", 0, Module.Category.PLAYER);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {
/*  47 */     if (e instanceof EventReceivePacket) {
/*     */       
/*  49 */       EventReceivePacket event = (EventReceivePacket)e;
/*     */       
/*  51 */       if (event.getPacket() instanceof S02PacketChat) {
/*  52 */         S02PacketChat s02PacketChat = (S02PacketChat)event.getPacket();
/*     */         
/*  54 */         if (!s02PacketChat.func_148915_c().getUnformattedText().isEmpty()) {
/*  55 */           String message = s02PacketChat.func_148915_c().getUnformattedText();
/*     */           
/*  57 */           if (message.contains("Cake Wars") && message.contains("Game")) {
/*  58 */             sendClientMessage("Cake Wars", "Detected Mode: Cake Wars", 5.0D);
/*  59 */             this.teamcake.clear();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  66 */     if (e instanceof leap.events.listeners.EventRenderWorld) {
/*     */       
/*  68 */       int radius = 20;
/*  69 */       for (int x = -radius; x < radius; x++) {
/*  70 */         for (int y = -radius; y < radius; y++) {
/*  71 */           for (int z = -radius; z < radius; z++) {
/*  72 */             double posX = mc.thePlayer.posX;
/*  73 */             double posY = mc.thePlayer.posY;
/*  74 */             double posZ = mc.thePlayer.posZ;
/*  75 */             this.pos = new BlockPos(posX + x, posY + y, posZ + z);
/*  76 */             this.pblock = mc.theWorld.getBlockState(this.pos).getBlock();
/*     */             
/*  78 */             double xPos = this.pos.getX() - RenderManager.renderPosX;
/*  79 */             double yPos = this.pos.getY() - RenderManager.renderPosY;
/*  80 */             double zPos = this.pos.getZ() - RenderManager.renderPosZ;
/*     */             
/*  82 */             if (this.pblock.getBlockState().getBlock() instanceof net.minecraft.block.BlockCake) {
/*  83 */               if (this.teamcake.contains("[" + Integer.toString(this.pos.getX()) + " " + Integer.toString(this.pos.getY()) + " " + Integer.toString(this.pos.getZ()) + "]") && !this.teamcake.isEmpty()) {
/*  84 */                 RenderUtil.drawCakeESP(xPos, yPos, zPos, 0.0F, 255.0F, 0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.2F, 0.2F);
/*     */               } else {
/*  86 */                 RenderUtil.drawCakeESP(xPos, yPos, zPos, 255.0F, 0.0F, 0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.2F, 0.2F);
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     if (e.isPre()) {
/*     */       
/*  96 */       if (mc.theWorld == null) {
/*  97 */         this.teamcake.clear();
/*     */       }
/*     */       
/* 100 */       int radius = 15;
/* 101 */       for (int x = -radius; x < radius; x++) {
/* 102 */         for (int y = -radius; y < radius; y++) {
/* 103 */           for (int z = -radius; z < radius; z++) {
/* 104 */             double posX = mc.thePlayer.posX;
/* 105 */             double posY = mc.thePlayer.posY;
/* 106 */             double posZ = mc.thePlayer.posZ;
/* 107 */             this.pos = new BlockPos(posX + x, posY + y, posZ + z);
/* 108 */             this.pblock = mc.theWorld.getBlockState(this.pos).getBlock();
/* 109 */             if (this.pblock.getBlockState().getBlock() instanceof net.minecraft.block.BlockCake) {
/* 110 */               BlockPos surroundTop = new BlockPos(this.pos.getX(), this.pos.getY() + 1, this.pos.getZ());
/* 111 */               this.sblock = mc.theWorld.getBlockState(surroundTop).getBlock();
/* 112 */               boolean surrounded = !(this.sblock.getBlockState().getBlock() instanceof net.minecraft.block.BlockAir);
/*     */               
/* 114 */               if (this.pblock.getBlockState().getBlock() instanceof net.minecraft.block.BlockCake) {
/* 115 */                 if (this.teamcake.isEmpty()) {
/* 116 */                   this.teamcake.add("[" + Integer.toString(this.pos.getX()) + " " + Integer.toString(this.pos.getY()) + " " + Integer.toString(this.pos.getZ()) + "]");
/* 117 */                   sendClientMessage("Cake Wars", "§fSet Team Cake to §e" + this.pos.getX() + "," + this.pos.getY() + "," + this.pos.getZ() + "!", 5.0D);
/*     */                 } 
/* 119 */                 if (this.teamcake.contains("[" + Integer.toString(this.pos.getX()) + " " + Integer.toString(this.pos.getY()) + " " + Integer.toString(this.pos.getZ()) + "]") && !this.teamcake.isEmpty()) {
/*     */                   return;
/*     */                 }
/*     */               } 
/*     */               
/* 124 */               if (!surrounded) {
/* 125 */                 if (this.pblock.getBlockState().getBlock() instanceof net.minecraft.block.BlockCake && 
/* 126 */                   mc.thePlayer.getDistance(this.pos.getX(), this.pos.getY(), this.pos.getZ()) <= 4.0D) {
/* 127 */                   mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, this.pos, EnumFacing.NORTH));
/* 128 */                   mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.pos, EnumFacing.NORTH));
/*     */                 }
/*     */               
/*     */               }
/* 132 */               else if (this.sblock.getBlockState().getBlock() == Blocks.wool) {
/* 133 */                 if (mc.thePlayer.getDistance(surroundTop.getX(), surroundTop.getY(), surroundTop.getZ()) <= 4.0D) {
/* 134 */                   mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, surroundTop, EnumFacing.UP));
/*     */                   
/* 136 */                   mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, surroundTop, EnumFacing.UP));
/*     */                 } 
/* 138 */               } else if (this.sblock.getBlockState().getBlock() == Blocks.end_stone) {
/* 139 */                 if (mc.thePlayer.getDistance(surroundTop.getX(), surroundTop.getY(), surroundTop.getZ()) <= 4.0D) {
/* 140 */                   for (int i = 0; i < 9; i++) {
/* 141 */                     if (mc.thePlayer.inventory.getStackInSlot(i) != null)
/*     */                     {
/* 143 */                       if (mc.thePlayer.inventory.getStackInSlot(i).getItem() instanceof net.minecraft.item.ItemPickaxe) {
/* 144 */                         mc.thePlayer.sendQueue.addToSendQueue((Packet)new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem = i));
/*     */                       }
/*     */                     }
/*     */                   } 
/* 148 */                   mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, surroundTop, EnumFacing.UP));
/*     */ 
/*     */                   
/* 151 */                   mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, surroundTop, EnumFacing.UP));
/*     */                 } 
/* 153 */               } else if (this.sblock.getBlockState().getBlock() instanceof net.minecraft.block.BlockPlanks && 
/* 154 */                 mc.thePlayer.getDistance(surroundTop.getX(), surroundTop.getY(), surroundTop.getZ()) <= 4.0D) {
/* 155 */                 for (int i = 0; i < 9; i++) {
/* 156 */                   if (mc.thePlayer.inventory.getStackInSlot(i) != null)
/*     */                   {
/* 158 */                     if (mc.thePlayer.inventory.getStackInSlot(i).getItem() instanceof net.minecraft.item.ItemAxe)
/* 159 */                       mc.thePlayer.sendQueue.addToSendQueue((Packet)new C09PacketHeldItemChange(mc.thePlayer.inventory.currentItem = i)); 
/*     */                   }
/*     */                 } 
/* 162 */                 mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, surroundTop, EnumFacing.UP));
/*     */                 
/* 164 */                 mc.thePlayer.sendQueue.addToSendQueue((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.STOP_DESTROY_BLOCK, surroundTop, EnumFacing.UP));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 176 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/* 180 */     super.onDisable();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\CakeEater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */