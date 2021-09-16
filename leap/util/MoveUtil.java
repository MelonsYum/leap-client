/*     */ package leap.util;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C03PacketPlayer;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class MoveUtil {
/*  14 */   protected static Minecraft mc = Minecraft.getMinecraft();
/*     */   
/*     */   public static void setSpeed(EventMotion moveEvent, double moveSpeed) {
/*  17 */     setSpeed(moveEvent, moveSpeed, mc.thePlayer.rotationYaw, mc.thePlayer.movementInput.moveStrafe, mc.thePlayer.movementInput.moveForward);
/*     */   }
/*     */   
/*     */   public static boolean isMovingOnGround() {
/*  21 */     return (isMoving() && mc.thePlayer.onGround);
/*     */   }
/*     */   
/*     */   public static float getRetarded() {
/*  25 */     return 0.2873F;
/*     */   }
/*     */   
/*     */   public static double getJumpHeight(double speed) {
/*  29 */     return mc.thePlayer.isPotionActive(Potion.jump) ? (speed + 0.1D * (mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1)) : speed;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void strafe(float speed) {
/*  34 */     if (!isMoving()) {
/*     */       return;
/*     */     }
/*  37 */     double yaw = getDirection();
/*  38 */     mc.thePlayer.motionX = -Math.sin(yaw) * speed;
/*  39 */     mc.thePlayer.motionZ = Math.cos(yaw) * speed;
/*     */   }
/*     */   
/*     */   public static double getDirection() {
/*  43 */     float rotationYaw = mc.thePlayer.rotationYaw;
/*     */     
/*  45 */     if (mc.thePlayer.moveForward < 0.0F) {
/*  46 */       rotationYaw += 180.0F;
/*     */     }
/*  48 */     float forward = 1.0F;
/*  49 */     if (mc.thePlayer.moveForward < 0.0F) {
/*  50 */       forward = -0.5F;
/*  51 */     } else if (mc.thePlayer.moveForward > 0.0F) {
/*  52 */       forward = 0.5F;
/*     */     } 
/*  54 */     if (mc.thePlayer.moveStrafing > 0.0F) {
/*  55 */       rotationYaw -= 90.0F * forward;
/*     */     }
/*  57 */     if (mc.thePlayer.moveStrafing < 0.0F) {
/*  58 */       rotationYaw += 90.0F * forward;
/*     */     }
/*  60 */     return Math.toRadians(rotationYaw);
/*     */   }
/*     */   
/*     */   public static void sendPosition(double x, double y, double z, boolean ground, boolean moving) {
/*  64 */     if (!moving) {
/*  65 */       mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + y, mc.thePlayer.posZ, ground));
/*     */     } else {
/*  67 */       mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX + x, mc.thePlayer.posY + y, mc.thePlayer.posZ + z, ground));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Block getBlockAtPos(BlockPos inBlockPos) {
/*  72 */     IBlockState s = mc.theWorld.getBlockState(inBlockPos);
/*  73 */     return s.getBlock();
/*     */   }
/*     */   
/*     */   public static void setSpeed(EventMotion moveEvent, double moveSpeed, float pseudoYaw, double pseudoStrafe, double pseudoForward) {
/*  77 */     double forward = pseudoForward;
/*  78 */     double strafe = pseudoStrafe;
/*  79 */     float yaw = pseudoYaw;
/*     */     
/*  81 */     if (forward != 0.0D) {
/*  82 */       if (strafe > 0.0D) {
/*  83 */         yaw += ((forward > 0.0D) ? -45 : 45);
/*  84 */       } else if (strafe < 0.0D) {
/*  85 */         yaw += ((forward > 0.0D) ? 45 : -45);
/*     */       } 
/*  87 */       strafe = 0.0D;
/*  88 */       if (forward > 0.0D) {
/*  89 */         forward = 1.0D;
/*  90 */       } else if (forward < 0.0D) {
/*  91 */         forward = -1.0D;
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     if (strafe > 0.0D) {
/*  96 */       strafe = 1.0D;
/*  97 */     } else if (strafe < 0.0D) {
/*  98 */       strafe = -1.0D;
/*     */     } 
/* 100 */     double mx = Math.cos(Math.toRadians((yaw + 90.0F)));
/* 101 */     double mz = Math.sin(Math.toRadians((yaw + 90.0F)));
/* 102 */     moveEvent.x = forward * moveSpeed * mx + strafe * moveSpeed * mz;
/* 103 */     moveEvent.z = forward * moveSpeed * mz - strafe * moveSpeed * mx;
/*     */   }
/*     */ 
/*     */   
/*     */   public static double getBaseMoveSpeed() {
/* 108 */     double baseSpeed = 0.2875D;
/* 109 */     if (mc.thePlayer.isPotionActive(Potion.moveSpeed))
/* 110 */       baseSpeed *= 1.0D + 0.2D * (mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1); 
/* 111 */     return baseSpeed;
/*     */   }
/*     */   
/*     */   public static boolean isMoving() {
/* 115 */     return !(mc.thePlayer.movementInput.moveForward == 0.0F && mc.thePlayer.movementInput.moveStrafe == 0.0F);
/*     */   }
/*     */   
/*     */   public static double defaultMoveSpeed() {
/* 119 */     return mc.thePlayer.isSprinting() ? 0.28700000047683716D : 0.22300000488758087D;
/*     */   }
/*     */   
/*     */   public static double getLastDistance() {
/* 123 */     return Math.hypot(mc.thePlayer.posX - mc.thePlayer.prevPosX, mc.thePlayer.posZ - mc.thePlayer.prevPosZ);
/*     */   }
/*     */   
/*     */   public static boolean isOnGround(double height) {
/* 127 */     return !mc.theWorld.getCollidingBoundingBoxes((Entity)mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0D, -height, 0.0D)).isEmpty();
/*     */   }
/*     */   
/*     */   public static double jumpHeight() {
/* 131 */     if (mc.thePlayer.isPotionActive(Potion.jump)) {
/* 132 */       return 0.419999986886978D + 0.1D * (mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1);
/*     */     }
/* 134 */     return 0.419999986886978D;
/*     */   }
/*     */   
/*     */   public static double getJumpBoostModifier(double baseJumpHeight) {
/* 138 */     if (mc.thePlayer.isPotionActive(Potion.jump)) {
/* 139 */       int amplifier = mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier();
/* 140 */       baseJumpHeight += ((amplifier + 1) * 0.1F);
/*     */     } 
/*     */     
/* 143 */     return baseJumpHeight;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSpeed(double moveSpeed, float yaw, double strafe, double forward) {
/* 148 */     double fforward = mc.thePlayer.movementInput.moveForward;
/* 149 */     double sstrafe = mc.thePlayer.movementInput.moveStrafe;
/* 150 */     float yyaw = mc.thePlayer.rotationYaw;
/* 151 */     if (forward != 0.0D) {
/* 152 */       if (strafe > 0.0D) {
/* 153 */         yaw += ((forward > 0.0D) ? -45 : 45);
/* 154 */       } else if (strafe < 0.0D) {
/* 155 */         yaw += ((forward > 0.0D) ? 45 : -45);
/*     */       } 
/* 157 */       strafe = 0.0D;
/* 158 */       if (forward > 0.0D) {
/* 159 */         forward = 1.0D;
/* 160 */       } else if (forward < 0.0D) {
/* 161 */         forward = -1.0D;
/*     */       } 
/*     */     } 
/* 164 */     if (strafe > 0.0D) {
/* 165 */       strafe = 1.0D;
/* 166 */     } else if (strafe < 0.0D) {
/* 167 */       strafe = -1.0D;
/*     */     } 
/* 169 */     double mx = Math.cos(Math.toRadians((yaw + 90.0F)));
/* 170 */     double mz = Math.sin(Math.toRadians((yaw + 90.0F)));
/* 171 */     mc.thePlayer.motionX = forward * moveSpeed * mx + strafe * moveSpeed * mz;
/* 172 */     mc.thePlayer.motionZ = forward * moveSpeed * mz - strafe * moveSpeed * mx;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setMotionWithValues(EventMotion em, double speed, float yaw, double forward, double strafe) {
/* 178 */     if (forward == 0.0D && strafe == 0.0D) {
/* 179 */       if (em != null) {
/* 180 */         em.setX(0.0D);
/* 181 */         em.setZ(0.0D);
/*     */       } else {
/* 183 */         mc.thePlayer.motionX = 0.0D;
/* 184 */         mc.thePlayer.motionZ = 0.0D;
/*     */       } 
/*     */     } else {
/* 187 */       if (forward != 0.0D) {
/* 188 */         if (strafe > 0.0D) {
/* 189 */           yaw += ((forward > 0.0D) ? -45 : 45);
/* 190 */         } else if (strafe < 0.0D) {
/* 191 */           yaw += ((forward > 0.0D) ? 45 : -45);
/*     */         } 
/* 193 */         strafe = 0.0D;
/* 194 */         if (forward > 0.0D) {
/* 195 */           forward = 1.0D;
/* 196 */         } else if (forward < 0.0D) {
/* 197 */           forward = -1.0D;
/*     */         } 
/*     */       } 
/* 200 */       mc.thePlayer.motionX = forward * speed * Math.cos(Math.toRadians((yaw + 90.0F))) + strafe * speed * Math.sin(Math.toRadians((yaw + 90.0F)));
/* 201 */       mc.thePlayer.motionZ = forward * speed * Math.sin(Math.toRadians((yaw + 90.0F))) - strafe * speed * Math.cos(Math.toRadians((yaw + 90.0F)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setSilentMotionWithValues(EventMotion em, double speed, float yaw, double forward, double strafe) {
/* 206 */     if (forward == 0.0D && strafe == 0.0D) {
/* 207 */       if (em != null) {
/* 208 */         em.setX(0.0D);
/* 209 */         em.setZ(0.0D);
/*     */       } else {
/* 211 */         mc.thePlayer.motionX = 0.0D;
/* 212 */         mc.thePlayer.motionZ = 0.0D;
/*     */       } 
/*     */     } else {
/* 215 */       if (forward != 0.0D) {
/* 216 */         if (strafe > 0.0D) {
/* 217 */           yaw += ((forward > 0.0D) ? -45 : 45);
/* 218 */         } else if (strafe < 0.0D) {
/* 219 */           yaw += ((forward > 0.0D) ? 45 : -45);
/*     */         } 
/* 221 */         strafe = 0.0D;
/* 222 */         if (forward > 0.0D) {
/* 223 */           forward = 1.0D;
/* 224 */         } else if (forward < 0.0D) {
/* 225 */           forward = -1.0D;
/*     */         } 
/*     */       } 
/* 228 */       em.x = forward * speed * Math.cos(Math.toRadians((yaw + 90.0F))) + strafe * speed * Math.sin(Math.toRadians((yaw + 90.0F)));
/* 229 */       em.z = forward * speed * Math.sin(Math.toRadians((yaw + 90.0F))) - strafe * speed * Math.cos(Math.toRadians((yaw + 90.0F)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float getSpeed() {
/* 234 */     return (float)Math.sqrt(mc.thePlayer.motionX * mc.thePlayer.motionX + mc.thePlayer.motionZ * mc.thePlayer.motionZ);
/*     */   }
/*     */   
/*     */   public static boolean isMovingWithKeys() {
/* 238 */     if (!Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode()) && !Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode()) && !Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode()) && !Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())) {
/* 239 */       return false;
/*     */     }
/* 241 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSpeed(double moveSpeed) {
/* 246 */     setSpeed(moveSpeed, mc.thePlayer.rotationYaw, mc.thePlayer.movementInput.moveStrafe, mc.thePlayer.movementInput.moveForward);
/*     */   }
/*     */   
/*     */   public double getTickDist() {
/* 250 */     double xDist = mc.thePlayer.posX - mc.thePlayer.lastTickPosX;
/* 251 */     double zDist = mc.thePlayer.posZ - mc.thePlayer.lastTickPosZ;
/* 252 */     return Math.sqrt(Math.pow(xDist, 2.0D) + Math.pow(zDist, 2.0D));
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\MoveUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */