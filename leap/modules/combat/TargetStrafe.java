/*     */ package leap.modules.combat;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.events.listeners.EventMotion;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.ColorUtil;
/*     */ import leap.util.MoveUtil;
/*     */ import leap.util.RandomUtil;
/*     */ import leap.util.RotationUtils;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class TargetStrafe
/*     */   extends Module
/*     */ {
/*  30 */   public static ModeSetting mode = new ModeSetting("Mode", "Simple", new String[] { "Adapt", "Simple" });
/*  31 */   public static NumberSetting radius = new NumberSetting("Radius", 1.0D, 1.0D, 5.0D, 1.0D);
/*  32 */   public static NumberSetting boost = new NumberSetting("Boost", 0.0D, 0.0D, 1.0D, 0.1D);
/*  33 */   public BooleanSetting autof5 = new BooleanSetting("AutoF5", false);
/*  34 */   public static BooleanSetting timer = new BooleanSetting("Timer Boost", true);
/*  35 */   public static BooleanSetting space = new BooleanSetting("Space", false);
/*  36 */   public static NumberSetting red = new NumberSetting("Red", 0.0D, 0.0D, 255.0D, 1.0D);
/*  37 */   public static NumberSetting green = new NumberSetting("Green", 173.0D, 0.0D, 255.0D, 1.0D);
/*  38 */   public static NumberSetting blue = new NumberSetting("Blue", 0.0D, 0.0D, 255.0D, 1.0D); public static Vec3 indexPos; public static int index; public static int arraySize;
/*  39 */   public static BooleanSetting rainbow = new BooleanSetting("Rainbow", true); private boolean set; private boolean changeDir; private double strafeDirection;
/*     */   
/*     */   public TargetStrafe() {
/*  42 */     super("TargetStrafe", 0, Module.Category.COMBAT);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     this.strafeDirection = 1.0D;
/*     */     addSettings(new Setting[] { (Setting)mode, (Setting)radius, (Setting)boost, (Setting)timer, (Setting)this.autof5, (Setting)space, (Setting)red, (Setting)green, (Setting)blue, (Setting)rainbow });
/*     */   }
/*     */   public static boolean strafing = true; public static boolean isSpace = false;
/*     */   public void onEnable() {
/*  54 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/*  58 */     mc.timer.timerSpeed = 1.0F;
/*  59 */     mc.gameSettings.thirdPersonView = 0;
/*  60 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onEvent(Event e) {
/*  64 */     if (e instanceof EventMotion) {
/*     */ 
/*     */       
/*  67 */       if (canStrafe()) {
/*  68 */         if (KillAura.targets != null && 
/*  69 */           !KillAura.targets.isEmpty())
/*     */         {
/*     */ 
/*     */           
/*  73 */           double baseSpeed = MoveUtil.getBaseMoveSpeed();
/*     */           
/*  75 */           strafe((EventMotion)e, baseSpeed + boost.getValue());
/*  76 */           strafing = true;
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*  82 */       else if (Client.getModule("KillAura").isEnabled() && 
/*  83 */         KillAura.targets != null && 
/*  84 */         !KillAura.targets.isEmpty() && 
/*  85 */         this.autof5.isEnabled()) {
/*  86 */         mc.gameSettings.thirdPersonView = 0;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  91 */       if (this.autof5.isEnabled() && strafing) {
/*  92 */         if (Client.getModule("KillAura").isEnabled()) {
/*  93 */           if (KillAura.targets != null && 
/*  94 */             KillAura.targets.isEmpty()) {
/*  95 */             mc.gameSettings.thirdPersonView = 0;
/*  96 */             strafing = false;
/*     */           }
/*     */         
/*  99 */         } else if (!Client.getModule("KillAura").isEnabled()) {
/* 100 */           mc.gameSettings.thirdPersonView = 0;
/* 101 */           strafing = false;
/*     */         } 
/*     */       }
/* 104 */       if (Client.getModule("KillAura").isEnabled() && 
/* 105 */         KillAura.targets != null && 
/* 106 */         !KillAura.targets.isEmpty()) {
/* 107 */         EntityLivingBase target = KillAura.targets.get(0);
/* 108 */         if (target != null) {
/*     */           
/* 110 */           ArrayList<Vec3> posArrayList = new ArrayList<>();
/* 111 */           for (float rotation = 0.0F; rotation < 6.283184051513672D; rotation += 0.24166092F) {
/* 112 */             Vec3 pos = new Vec3(radius.getValue() * Math.cos(rotation) + target.posX, target.posY, radius.getValue() * Math.sin(rotation) + target.posZ);
/* 113 */             posArrayList.add(pos);
/*     */           } 
/* 115 */           arraySize = posArrayList.size();
/* 116 */           if (!this.set) {
/* 117 */             ArrayList<Vec3> posBuffer = new ArrayList<>(posArrayList);
/* 118 */             posBuffer.sort(Comparator.comparingDouble(vec3 -> mc.thePlayer.getDistance(vec3.xCoord, vec3.yCoord, vec3.zCoord)));
/* 119 */             index = posArrayList.indexOf(posBuffer.get(0));
/* 120 */             this.set = true;
/*     */           } else {
/* 122 */             BlockPos blockPos = new BlockPos(((Vec3)posArrayList.get(index)).xCoord, ((Vec3)posArrayList.get(index)).yCoord, ((Vec3)posArrayList.get(index)).zCoord);
/* 123 */             indexPos = new Vec3((blockPos.getX() + 0.5F), ((Vec3)posArrayList.get(index)).yCoord, blockPos.getZ());
/* 124 */             if (mc.theWorld.getBlockState(new BlockPos(indexPos.xCoord, mc.thePlayer.posY, indexPos.zCoord)).getBlock().getCollisionBoundingBox((World)mc.theWorld, new BlockPos(indexPos.xCoord, mc.thePlayer.posY, indexPos.zCoord), mc.theWorld.getBlockState(new BlockPos(indexPos.xCoord, mc.thePlayer.posY, indexPos.zCoord))) != null || mc.theWorld.getBlockState(new BlockPos(indexPos.xCoord, mc.thePlayer.posY + 1.0D, indexPos.zCoord)).getBlock().getCollisionBoundingBox((World)mc.theWorld, new BlockPos(indexPos.xCoord, mc.thePlayer.posY + 1.0D, indexPos.zCoord), mc.theWorld.getBlockState(new BlockPos(indexPos.xCoord, mc.thePlayer.posY + 1.0D, indexPos.zCoord))) != null || mc.theWorld.getBlockState(new BlockPos(indexPos.xCoord, mc.thePlayer.posY + 2.0D, indexPos.zCoord)).getBlock().getCollisionBoundingBox((World)mc.theWorld, new BlockPos(indexPos.xCoord, mc.thePlayer.posY + 2.0D, indexPos.zCoord), mc.theWorld.getBlockState(new BlockPos(indexPos.xCoord, mc.thePlayer.posY + 2.0D, indexPos.zCoord))) != null) {
/* 125 */               this.strafeDirection *= -1.0D;
/* 126 */               if (this.strafeDirection == -1.0D)
/* 127 */               { if (index + 1 > posArrayList.size() - 1) { index = 0; }
/* 128 */                 else { index++; }
/*     */                  }
/* 130 */               else if (index - 1 < 0) { index = posArrayList.size() - 1; }
/* 131 */               else { index--; }
/*     */             
/*     */             } else {
/* 134 */               if (mc.thePlayer.isCollidedHorizontally)
/* 135 */               { if (!this.changeDir) {
/* 136 */                   this.strafeDirection *= -1.0D;
/* 137 */                   changeIndex(posArrayList);
/* 138 */                   this.changeDir = true;
/*     */                 }  }
/* 140 */               else { this.changeDir = false; }
/* 141 */                if (mc.gameSettings.keyBindRight.isPressed()) {
/* 142 */                 this.strafeDirection = 1.0D;
/* 143 */               } else if (mc.gameSettings.keyBindLeft.isPressed()) {
/* 144 */                 this.strafeDirection = -1.0D;
/*     */               } 
/* 146 */               if (mc.thePlayer.getDistance(indexPos.xCoord, mc.thePlayer.posY, indexPos.zCoord) <= mc.thePlayer.getDistance(mc.thePlayer.prevPosX, mc.thePlayer.prevPosY, mc.thePlayer.prevPosZ) * 2.0D) {
/* 147 */                 changeIndex(posArrayList);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } else {
/* 152 */           this.set = false;
/* 153 */           index = 0;
/* 154 */           indexPos = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 160 */     if (e instanceof leap.events.listeners.EventRenderWorld && 
/* 161 */       canStrafe() && 
/* 162 */       KillAura.targets != null && 
/* 163 */       !KillAura.targets.isEmpty() && 
/* 164 */       KillAura.targets.get(0) != mc.thePlayer) {
/* 165 */       drawCircle((Entity)KillAura.targets.get(0), 0.2F, radius.getValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void changeIndex(ArrayList<Vec3> posArrayList) {
/* 174 */     if (this.strafeDirection == -1.0D)
/* 175 */     { if (index + 1 > posArrayList.size() - 1) { index = 0; }
/* 176 */       else { index++; }
/*     */        }
/* 178 */     else if (index - 1 < 0) { index = posArrayList.size() - 1; }
/* 179 */     else { index--; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawCircle(Entity entity, float partialTicks, double rad) {
/*     */     float r, g, b;
/* 190 */     GL11.glPushMatrix();
/* 191 */     GL11.glDisable(3553);
/* 192 */     GL11.glDisable(2929);
/* 193 */     GL11.glDepthMask(false);
/* 194 */     GL11.glLineWidth(20.0F);
/* 195 */     GL11.glBegin(3);
/*     */     
/* 197 */     double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks - (mc.getRenderManager()).viewerPosX;
/* 198 */     double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks - (mc.getRenderManager()).viewerPosY + 1.0D;
/* 199 */     double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks - (mc.getRenderManager()).viewerPosZ;
/*     */ 
/*     */     
/* 202 */     if (rainbow.isEnabled()) {
/* 203 */       r = 0.015686275F * (new Color(ColorUtil.getRainbow(5.0F, 0.9F, 1.0F, 20L))).getRed();
/* 204 */       g = 0.015686275F * (new Color(ColorUtil.getRainbow(5.0F, 0.9F, 1.0F, 20L))).getGreen();
/* 205 */       b = 0.015686275F * (new Color(ColorUtil.getRainbow(5.0F, 0.9F, 1.0F, 20L))).getBlue();
/*     */     } else {
/* 207 */       r = (float)(0.003921568859368563D * red.getValue());
/* 208 */       g = (float)(0.003921568859368563D * green.getValue());
/* 209 */       b = (float)(0.003921568859368563D * blue.getValue());
/*     */     } 
/*     */     
/* 212 */     double pix2 = 6.283185307179586D;
/*     */     
/* 214 */     for (int i = 0; i <= 40; i++) {
/* 215 */       GlStateManager.color(r, g, b, 255.0F);
/* 216 */       GL11.glVertex3d(x + rad * Math.cos(i * 6.283185307179586D / 40.0D), y, z + rad * Math.sin(i * 6.283185307179586D / 40.0D));
/*     */     } 
/*     */     
/* 219 */     GL11.glEnd();
/* 220 */     GL11.glDepthMask(true);
/* 221 */     GL11.glEnable(2929);
/* 222 */     GL11.glEnable(3553);
/* 223 */     GL11.glPopMatrix();
/*     */   }
/*     */   public static Color rainbow(int delay) {
/* 226 */     double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0D);
/* 227 */     rainbowState %= 360.0D;
/* 228 */     return Color.getHSBColor((float)(rainbowState / 360.0D), 0.8F, 0.7F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void strafe(EventMotion e, double moveSpeed) {
/* 233 */     if (!Client.getModule("TargetStrafe").isEnabled()) {
/*     */       return;
/*     */     }
/*     */     
/* 237 */     if (this.autof5.isEnabled()) {
/* 238 */       mc.gameSettings.thirdPersonView = 1;
/*     */     }
/*     */ 
/*     */     
/* 242 */     double dist = mc.thePlayer.getDistanceToEntity((Entity)KillAura.targets.get(0));
/* 243 */     if (mode.getMode() == "Simple") {
/* 244 */       if (dist > radius.getValue()) {
/*     */         
/* 246 */         if (timer.isEnabled()) {
/* 247 */           mc.timer.timerSpeed = 1.2F;
/*     */         } else {
/* 249 */           mc.timer.timerSpeed = 1.0F;
/*     */         } 
/*     */         
/* 252 */         if (RandomUtil.randomNumber(20.0D, 0.0D) > 15) {
/* 253 */           this.strafeDirection = -this.strafeDirection;
/*     */         }
/*     */         
/* 256 */         MoveUtil.setMotionWithValues(e, MoveUtil.getBaseMoveSpeed() - 0.0D, RotationUtils.getPredictedRotations((EntityLivingBase)KillAura.targets.get(0))[0] + RandomUtil.randomNumber(1.0D, 6.0D), 1.0D, this.strafeDirection);
/*     */       } else {
/* 258 */         MoveUtil.setMotionWithValues(e, MoveUtil.getBaseMoveSpeed(), RotationUtils.getPredictedRotations((EntityLivingBase)KillAura.targets.get(0))[0] + RandomUtil.randomNumber(1.0D, 6.0D), 0.0D, this.strafeDirection);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 263 */     if (mode.getMode() == "Adapt")
/* 264 */       if (dist > radius.getValue()) {
/*     */ 
/*     */         
/* 267 */         if (timer.isEnabled()) {
/* 268 */           mc.timer.timerSpeed = 1.2F;
/*     */         } else {
/* 270 */           mc.timer.timerSpeed = 1.0F;
/*     */         } 
/*     */         
/* 273 */         if (mc.thePlayer.hurtTime == 0) {
/* 274 */           this.strafeDirection = -this.strafeDirection;
/*     */         }
/* 276 */         MoveUtil.setMotionWithValues(e, moveSpeed - 0.0D, RotationUtils.getPredictedRotations((EntityLivingBase)KillAura.targets.get(0))[0] + RandomUtil.randomNumber(1.0D, 6.0D), 1.0D, this.strafeDirection);
/*     */       } else {
/*     */         
/* 279 */         MoveUtil.setMotionWithValues(e, moveSpeed, RotationUtils.getPredictedRotations((EntityLivingBase)KillAura.targets.get(0))[0] + RandomUtil.randomNumber(1.0D, 6.0D), 0.0D, this.strafeDirection);
/*     */       }  
/*     */   }
/*     */   
/*     */   public static boolean canStrafe() {
/*     */     // Byte code:
/*     */     //   0: getstatic leap/modules/combat/TargetStrafe.space : Lleap/settings/BooleanSetting;
/*     */     //   3: invokevirtual isEnabled : ()Z
/*     */     //   6: ifeq -> 91
/*     */     //   9: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   12: pop
/*     */     //   13: ldc_w 'Speed'
/*     */     //   16: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   19: invokevirtual isEnabled : ()Z
/*     */     //   22: ifeq -> 48
/*     */     //   25: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   28: pop
/*     */     //   29: ldc 'KillAura'
/*     */     //   31: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   34: invokevirtual isEnabled : ()Z
/*     */     //   37: ifeq -> 48
/*     */     //   40: bipush #57
/*     */     //   42: invokestatic isKeyDown : (I)Z
/*     */     //   45: ifne -> 89
/*     */     //   48: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   51: pop
/*     */     //   52: ldc_w 'Fly'
/*     */     //   55: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   58: invokevirtual isEnabled : ()Z
/*     */     //   61: ifeq -> 87
/*     */     //   64: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   67: pop
/*     */     //   68: ldc 'KillAura'
/*     */     //   70: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   73: invokevirtual isEnabled : ()Z
/*     */     //   76: ifeq -> 87
/*     */     //   79: bipush #57
/*     */     //   81: invokestatic isKeyDown : (I)Z
/*     */     //   84: ifne -> 89
/*     */     //   87: iconst_0
/*     */     //   88: ireturn
/*     */     //   89: iconst_1
/*     */     //   90: ireturn
/*     */     //   91: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   94: pop
/*     */     //   95: ldc_w 'Speed'
/*     */     //   98: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   101: invokevirtual isEnabled : ()Z
/*     */     //   104: ifeq -> 122
/*     */     //   107: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   110: pop
/*     */     //   111: ldc 'KillAura'
/*     */     //   113: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   116: invokevirtual isEnabled : ()Z
/*     */     //   119: ifne -> 155
/*     */     //   122: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   125: pop
/*     */     //   126: ldc_w 'Fly'
/*     */     //   129: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   132: invokevirtual isEnabled : ()Z
/*     */     //   135: ifeq -> 153
/*     */     //   138: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   141: pop
/*     */     //   142: ldc 'KillAura'
/*     */     //   144: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   147: invokevirtual isEnabled : ()Z
/*     */     //   150: ifne -> 155
/*     */     //   153: iconst_0
/*     */     //   154: ireturn
/*     */     //   155: iconst_1
/*     */     //   156: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #285	-> 0
/*     */     //   #286	-> 9
/*     */     //   #288	-> 91
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\TargetStrafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */