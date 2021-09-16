/*     */ package leap.modules.movement;
/*     */ 
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.MoveUtil;
/*     */ import leap.util.RandomUtil;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.entity.Entity;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Speed
/*     */   extends Module
/*     */ {
/*  22 */   Timer timer = new Timer();
/*     */   
/*  24 */   double lastDist = 0.0D;
/*  25 */   double speed = 0.0D;
/*  26 */   int stage = 1;
/*     */   
/*  28 */   public ModeSetting mode = new ModeSetting("Mode", "Hypixel", new String[] { "Mineplex", "Basic", "Smart", "Hypixel", "HypixelLow", "HypixelOnGround", "NCP", "AAC" });
/*     */   
/*     */   public Speed() {
/*  31 */     super("Speed", 0, Module.Category.MOVEMENT);
/*  32 */     addSettings(new Setting[] { (Setting)this.mode });
/*  33 */     this.disableonlag = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  38 */     super.onEnable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  45 */     mc.timer.timerSpeed = 1.0F;
/*  46 */     mc.gameSettings.keyBindJump.pressed = false;
/*     */     
/*  48 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  52 */     if (e instanceof EventMotion) {
/*  53 */       if (!Client.getModule("Fly").isEnabled()) { if (Client.getModule("Scaffold").isEnabled())
/*     */           return;  }
/*     */       else
/*     */       { return; }
/*  57 */        if (this.mode.getMode() == "Mineplex") {
/*     */         
/*  59 */         if ((mc.thePlayer.motionX != 0.0D || mc.thePlayer.motionY != 0.0D) && 
/*  60 */           mc.thePlayer.onGround) {
/*  61 */           mc.thePlayer.movementInput.jump = true;
/*  62 */           mc.thePlayer.motionY = 0.45D;
/*     */         } 
/*     */ 
/*     */         
/*  66 */         if (mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindRight.pressed || mc.gameSettings.keyBindLeft.pressed) {
/*     */           
/*  68 */           mc.gameSettings.keyBindSprint.pressed = true;
/*     */           
/*  70 */           EventMotion event = (EventMotion)e;
/*     */ 
/*     */ 
/*     */           
/*  74 */           if (mc.thePlayer.onGround) {
/*  75 */             if (mc.thePlayer.isSprinting()) {
/*  76 */               mc.gameSettings.keyBindJump.pressed = false;
/*  77 */               int rand = randomNumber(200.0D, 500.0D);
/*  78 */               mc.thePlayer.movementInput.jump = true;
/*  79 */               mc.thePlayer.motionY = 0.45D;
/*     */               
/*  81 */               double motionX = mc.thePlayer.motionX;
/*  82 */               double d1 = mc.thePlayer.motionZ;
/*     */             
/*     */             }
/*  85 */             else if (!mc.thePlayer.isCollidedHorizontally) {
/*  86 */               mc.thePlayer.setSprinting(true);
/*     */             }
/*     */             else {
/*     */ 
/*     */             
/*     */             }
/*     */           
/*  93 */           } else if (mc.thePlayer.moveForward > 0.0F && mc.thePlayer.moveStrafing == 0.0F) {
/*  94 */             MoveUtil.setSpeed(0.3199999928474426D);
/*  95 */             mc.thePlayer.jumpMovementFactor = 0.1335F;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 101 */       if (this.mode.getMode() == "Hypixel") {
/*     */         
/* 103 */         mc.timer.timerSpeed = 1.2F;
/*     */ 
/*     */         
/* 106 */         EventMotion event = (EventMotion)e;
/*     */ 
/*     */ 
/*     */         
/* 110 */         if ((mc.thePlayer.motionX != 0.0D || mc.thePlayer.motionY != 0.0D) && 
/* 111 */           mc.thePlayer.onGround)
/*     */         {
/* 113 */           mc.thePlayer.motionY = MoveUtil.jumpHeight();
/*     */         }
/*     */ 
/*     */         
/* 117 */         if (mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindRight.pressed || mc.gameSettings.keyBindLeft.pressed) {
/*     */           
/* 119 */           mc.gameSettings.keyBindSprint.pressed = true;
/*     */ 
/*     */           
/* 122 */           if (mc.thePlayer.onGround) {
/* 123 */             if (mc.thePlayer.isSprinting()) {
/* 124 */               mc.gameSettings.keyBindJump.pressed = false;
/*     */               
/* 126 */               int rand = randomNumber(200.0D, 500.0D);
/*     */               
/* 128 */               mc.thePlayer.jump();
/* 129 */               mc.thePlayer.motionY = 0.41D;
/*     */             }
/* 131 */             else if (!mc.thePlayer.isCollidedHorizontally) {
/* 132 */               mc.thePlayer.setSprinting(true);
/*     */             } 
/*     */           } else {
/* 135 */             MoveUtil.setSpeed(0.25D);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 141 */       if (this.mode.getMode() == "HypixelLow") {
/*     */         
/* 143 */         if ((mc.thePlayer.motionX != 0.0D || mc.thePlayer.motionY != 0.0D) && 
/* 144 */           mc.thePlayer.onGround) {
/* 145 */           mc.thePlayer.motionY = 0.25D;
/*     */         }
/*     */ 
/*     */         
/* 149 */         if (mc.thePlayer.motionX != 0.0D || mc.thePlayer.motionY != 0.0D) {
/*     */           
/* 151 */           mc.gameSettings.keyBindSprint.pressed = true;
/*     */           
/* 153 */           EventMotion event = (EventMotion)e;
/*     */ 
/*     */           
/* 156 */           if (mc.thePlayer.onGround) {
/* 157 */             if (mc.thePlayer.isSprinting()) {
/* 158 */               mc.gameSettings.keyBindJump.pressed = false;
/*     */               
/* 160 */               int rand = randomNumber(200.0D, 500.0D);
/* 161 */               mc.thePlayer.motionY = 0.25D;
/* 162 */               mc.timer.timerSpeed = 1.2F;
/* 163 */             } else if (!mc.thePlayer.isCollidedHorizontally) {
/* 164 */               mc.thePlayer.setSprinting(true);
/*     */             } 
/*     */           } else {
/* 167 */             mc.thePlayer.jumpMovementFactor = 0.0205F;
/* 168 */             mc.timer.timerSpeed = 1.2F;
/* 169 */             MoveUtil.setSpeed(0.3D);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 174 */       if (this.mode.getMode() == "Basic") {
/*     */         
/* 176 */         if ((mc.thePlayer.motionX != 0.0D || mc.thePlayer.motionY != 0.0D) && 
/* 177 */           mc.thePlayer.onGround) {
/* 178 */           mc.thePlayer.jump();
/*     */         }
/*     */ 
/*     */         
/* 182 */         if (mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindRight.pressed || mc.gameSettings.keyBindLeft.pressed) {
/*     */           
/* 184 */           mc.gameSettings.keyBindSprint.pressed = true;
/*     */           
/* 186 */           mc.thePlayer.jumpMovementFactor = 0.5735F;
/*     */           
/* 188 */           if (mc.thePlayer.onGround) {
/*     */             
/* 190 */             EventMotion event = (EventMotion)e;
/*     */             
/* 192 */             event.setMotion(0.5D);
/* 193 */             mc.gameSettings.keyBindJump.pressed = false;
/* 194 */             mc.thePlayer.jump();
/* 195 */             mc.thePlayer.jumpMovementFactor = 0.0235F;
/*     */           } else {
/* 197 */             EventMotion event = (EventMotion)e;
/*     */             
/* 199 */             event.setMotion(0.5D);
/* 200 */             mc.timer.timerSpeed = 1.0F;
/* 201 */             mc.thePlayer.isAirBorne = false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 208 */       if (this.mode.getMode() == "HypixelOnGround" && (
/* 209 */         mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindRight.pressed || mc.gameSettings.keyBindLeft.pressed)) {
/*     */         
/* 211 */         mc.gameSettings.keyBindSprint.pressed = true;
/*     */         
/* 213 */         EventMotion event = (EventMotion)e;
/* 214 */         mc.gameSettings.keyBindJump.pressed = false;
/*     */         
/* 216 */         if (mc.thePlayer.onGround) {
/* 217 */           if (mc.thePlayer.isSprinting()) {
/* 218 */             mc.gameSettings.keyBindJump.pressed = false;
/*     */             
/* 220 */             int rand = randomNumber(200.0D, 500.0D);
/* 221 */             if (!Client.getModule("NoSlow").isEnabled()) {
/* 222 */               if (mc.thePlayer.isSwingInProgress) {
/* 223 */                 mc.timer.timerSpeed = 1.2F;
/*     */               } else {
/* 225 */                 mc.timer.timerSpeed = 8.4F;
/*     */               } 
/*     */             } else {
/* 228 */               mc.timer.timerSpeed = 5.4F;
/*     */             } 
/* 230 */           } else if (!mc.thePlayer.isCollidedHorizontally) {
/* 231 */             mc.thePlayer.setSprinting(true);
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 237 */       if (this.mode.getMode() == "AAC" && (
/* 238 */         mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindRight.pressed || mc.gameSettings.keyBindLeft.pressed)) {
/*     */         
/* 240 */         mc.gameSettings.keyBindSprint.pressed = true;
/*     */         
/* 242 */         EventMotion event = (EventMotion)e;
/* 243 */         mc.gameSettings.keyBindJump.pressed = false;
/*     */         
/* 245 */         if (mc.thePlayer.onGround) {
/* 246 */           if (mc.thePlayer.isSprinting()) {
/* 247 */             mc.gameSettings.keyBindJump.pressed = false;
/*     */             
/* 249 */             int rand = randomNumber(200.0D, 500.0D);
/* 250 */             if (!Client.getModule("NoSlow").isEnabled()) {
/* 251 */               if (mc.thePlayer.isSwingInProgress) {
/* 252 */                 mc.timer.timerSpeed = 1.4F;
/*     */               } else {
/* 254 */                 mc.timer.timerSpeed = 1.4F;
/*     */               } 
/*     */             } else {
/* 257 */               mc.timer.timerSpeed = 1.4F;
/*     */             } 
/* 259 */           } else if (!mc.thePlayer.isCollidedHorizontally) {
/* 260 */             mc.thePlayer.setSprinting(true);
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 266 */       if (this.mode.getMode() == "Smart") {
/*     */         
/* 268 */         if ((mc.thePlayer.motionX != 0.0D || mc.thePlayer.motionY != 0.0D) && 
/* 269 */           mc.thePlayer.onGround) {
/* 270 */           mc.thePlayer.jump();
/*     */         }
/*     */ 
/*     */         
/* 274 */         if (mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindRight.pressed || mc.gameSettings.keyBindLeft.pressed) {
/*     */           
/* 276 */           mc.gameSettings.keyBindSprint.pressed = true;
/*     */           
/* 278 */           if (mc.thePlayer.onGround) {
/*     */             
/* 280 */             EventMotion event = (EventMotion)e;
/*     */             
/* 282 */             mc.gameSettings.keyBindJump.pressed = false;
/* 283 */             mc.thePlayer.jump();
/* 284 */             mc.thePlayer.jumpMovementFactor = 0.0265F;
/* 285 */             mc.thePlayer.moveStrafing *= 2.0F;
/*     */           } else {
/* 287 */             EventMotion event = (EventMotion)e;
/*     */             
/* 289 */             mc.timer.timerSpeed = 0.7F;
/* 290 */             mc.thePlayer.isAirBorne = false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 297 */       if (this.mode.getMode() == "NCP") {
/*     */         
/* 299 */         if ((mc.thePlayer.motionX != 0.0D || mc.thePlayer.motionY != 0.0D) && 
/* 300 */           mc.thePlayer.onGround) {
/* 301 */           mc.thePlayer.jump();
/*     */         }
/*     */ 
/*     */         
/* 305 */         if (mc.gameSettings.keyBindForward.pressed || mc.gameSettings.keyBindBack.pressed || mc.gameSettings.keyBindRight.pressed || mc.gameSettings.keyBindLeft.pressed) {
/*     */           
/* 307 */           mc.gameSettings.keyBindSprint.pressed = true;
/*     */           
/* 309 */           EventMotion event = (EventMotion)e;
/*     */ 
/*     */ 
/*     */           
/* 313 */           if (mc.thePlayer.onGround) {
/* 314 */             if (mc.thePlayer.isSprinting()) {
/* 315 */               mc.gameSettings.keyBindJump.pressed = false;
/*     */               
/* 317 */               int rand = randomNumber(200.0D, 500.0D);
/* 318 */               mc.thePlayer.jump();
/* 319 */             } else if (!mc.thePlayer.isCollidedHorizontally) {
/* 320 */               mc.thePlayer.setSprinting(true);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
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
/* 339 */     super.onEvent(e);
/*     */   }
/*     */   
/*     */   private void getHypixel() {
/* 343 */     boolean slowdown = false;
/*     */     
/* 345 */     if (this.stage == 1) {
/* 346 */       this.stage = 2;
/*     */     }
/* 348 */     if (this.stage == 2 && 
/* 349 */       mc.thePlayer.onGround && MoveUtil.isMoving()) {
/* 350 */       this.speed *= 1.7D;
/*     */     }
/* 352 */     if (this.stage == 3) {
/* 353 */       this.speed += (RandomUtil.randomNumber(10.0D, 1.0D) / 4799);
/* 354 */       double difference = 0.66D * (this.lastDist - MoveUtil.getBaseMoveSpeed());
/* 355 */       this.speed = this.lastDist - difference;
/* 356 */       this.speed -= (RandomUtil.randomNumber(10.0D, 1.0D) / 4799);
/*     */     } else {
/*     */       
/* 359 */       slowdown = (mc.thePlayer.fallDistance > 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 364 */       if (!mc.theWorld.getCollidingBoundingBoxes((Entity)mc.thePlayer, mc.thePlayer.boundingBox.offset(0.0D, mc.thePlayer.motionY, 0.0D)).isEmpty() || (mc.thePlayer.isCollidedVertically && mc.thePlayer.onGround))
/*     */       {
/* 366 */         this.stage = 1;
/*     */       }
/* 368 */       this.speed = this.lastDist - this.lastDist / 159.0D;
/*     */     } 
/* 370 */     if (slowdown) {
/* 371 */       this.speed = Math.max(this.speed - Math.sqrt(this.lastDist * this.lastDist + this.speed * this.speed) * 0.012D, MoveUtil.getBaseMoveSpeed());
/*     */     } else {
/* 373 */       this.speed = Math.max(this.speed - 0.02D * this.lastDist, MoveUtil.getBaseMoveSpeed());
/*     */     } 
/*     */     
/* 376 */     if (slowdown) {
/* 377 */       this.speed *= 1.0D - this.lastDist / 50.0D;
/*     */     }
/*     */   }
/*     */   
/*     */   public static int randomNumber(double d, double e) {
/* 382 */     int ii = (int)(-e + (int)(Math.random() * (d - -e + 1.0D)));
/* 383 */     return ii;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\movement\Speed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */