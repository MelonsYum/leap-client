/*     */ package leap.modules.movement;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.Setting;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C03PacketPlayer;
/*     */ import net.minecraft.stats.StatList;
/*     */ 
/*     */ public class Step
/*     */   extends Module
/*     */ {
/*  18 */   public static ModeSetting mode = new ModeSetting("Mode", "NCP/Hypixel", new String[] { "NCP/Hypixel", "Jump", "TP" });
/*     */   
/*  20 */   private double stepX = 0.0D;
/*  21 */   private double stepY = 0.0D;
/*  22 */   private double stepZ = 0.0D;
/*     */   private boolean isStep = false;
/*     */   
/*     */   public Step() {
/*  26 */     super("Step", 0, Module.Category.MOVEMENT);
/*  27 */     addSettings(new Setting[] { (Setting)mode });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  31 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  35 */     mc.timer.timerSpeed = 1.0F;
/*  36 */     mc.thePlayer.stepHeight = 0.5F;
/*     */     
/*  38 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  42 */     if (e instanceof EventMotion) {
/*     */       
/*  44 */       EventMotion event = (EventMotion)e;
/*     */       
/*  46 */       if (canStep() && mc.thePlayer.isCollidedHorizontally) {
/*  47 */         fakeJump();
/*     */ 
/*     */ 
/*     */         
/*  51 */         if (mc.thePlayer.onGround) {
/*  52 */           mc.thePlayer.motionY = 0.4000000059604645D;
/*  53 */           mc.thePlayer.onGround = false;
/*     */         } 
/*     */       } 
/*  56 */       if (canStep() && !mc.thePlayer.isCollidedHorizontally) {
/*  57 */         mc.thePlayer.motionY = -0.1D;
/*     */       }
/*     */       
/*  60 */       if (canStep() && mc.thePlayer.motionY != 0.0D && !mc.thePlayer.onGround) {
/*  61 */         mc.thePlayer.stepHeight = 1.0F;
/*     */       } else {
/*  63 */         mc.thePlayer.stepHeight = 0.5F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canStep() {
/*  70 */     double yaw = mc.thePlayer.getTeleportDirection();
/*  71 */     double x = -Math.sin(yaw) * 0.4D;
/*  72 */     double z = Math.cos(yaw) * 0.4D;
/*  73 */     return mc.theWorld.getCollidingBoundingBoxes((Entity)mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(x, 1.001335979112147D, z)).isEmpty();
/*     */   }
/*     */   
/*     */   private void fakeJump() {
/*  77 */     mc.thePlayer.isAirBorne = true;
/*  78 */     mc.thePlayer.triggerAchievement(StatList.jumpStat);
/*     */   }
/*     */   
/*     */   private void step(double height) {
/*  82 */     List<Double> offset = Arrays.asList(new Double[] { Double.valueOf(0.42D), Double.valueOf(0.333D), Double.valueOf(0.248D), Double.valueOf(0.083D), Double.valueOf(-0.078D) });
/*  83 */     double posX = mc.thePlayer.posX;
/*  84 */     double posZ = mc.thePlayer.posZ;
/*  85 */     double y = mc.thePlayer.posY;
/*  86 */     if (height < 1.1D) {
/*  87 */       double first = 0.42D;
/*  88 */       double second = 0.75D;
/*  89 */       if (height != 1.0D) {
/*  90 */         first *= height;
/*  91 */         second *= height;
/*  92 */         if (first > 0.425D) {
/*  93 */           first = 0.425D;
/*     */         }
/*  95 */         if (second > 0.78D) {
/*  96 */           second = 0.78D;
/*     */         }
/*  98 */         if (second < 0.49D) {
/*  99 */           second = 0.49D;
/*     */         }
/*     */       } 
/* 102 */       sendNetPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(posX, y + first, posZ, false));
/* 103 */       if (y + second < y + height)
/* 104 */         sendNetPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(posX, y + second, posZ, false));  return;
/*     */     } 
/* 106 */     if (height < 1.6D) {
/* 107 */       for (int i = 0; i < offset.size(); i++) {
/* 108 */         double off = ((Double)offset.get(i)).doubleValue();
/* 109 */         y += off;
/* 110 */         sendNetPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(posX, y, posZ, false));
/*     */       } 
/* 112 */     } else if (height < 2.1D) {
/* 113 */       double[] heights = { 0.425D, 0.821D, 0.699D, 0.599D, 1.022D, 1.372D, 1.652D, 1.869D }; byte b; int i; double[] arrayOfDouble1;
/* 114 */       for (i = (arrayOfDouble1 = heights).length, b = 0; b < i; ) { double off = arrayOfDouble1[b];
/* 115 */         sendNetPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(posX, y + off, posZ, false)); b++; }
/*     */     
/*     */     } else {
/* 118 */       double[] heights = { 0.425D, 0.821D, 0.699D, 0.599D, 1.022D, 1.372D, 1.652D, 1.869D, 2.019D, 1.907D }; byte b; int i; double[] arrayOfDouble1;
/* 119 */       for (i = (arrayOfDouble1 = heights).length, b = 0; b < i; ) { double off = arrayOfDouble1[b];
/* 120 */         sendNetPacket((Packet)new C03PacketPlayer.C04PacketPlayerPosition(posX, y + off, posZ, false));
/*     */         b++; }
/*     */     
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\Step.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */