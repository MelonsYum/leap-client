/*     */ package leap.modules.combat;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.ColorUtil;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class KillAura
/*     */   extends Module
/*     */ {
/*     */   private int deltaY;
/*     */   private int deltaZ;
/*     */   private int distance;
/*  62 */   private static int direction = -1;
/*     */   public int activeTarget;
/*     */   private boolean targetdirection = true;
/*  65 */   static double pos = 0.5D;
/*     */   static boolean up = true;
/*     */   public static int prevhealth;
/*  68 */   private double strafeDirection = 1.0D;
/*     */   
/*  70 */   public static ArrayList<Entity> bots = new ArrayList<>();
/*     */   
/*  72 */   public Timer timer = new Timer();
/*  73 */   Timer timerd = new Timer();
/*  74 */   Timer timerbruh = new Timer();
/*  75 */   public static NumberSetting range = new NumberSetting("Range", 4.0D, 1.0D, 5.0D, 0.1D);
/*  76 */   public static ModeSetting mode = new ModeSetting("Mode", "Single", new String[] { "Single", "Multi", "Switch" });
/*  77 */   public static ModeSetting switchmode = new ModeSetting("Switch Mode", "Health", new String[] { "Health", "HurtTime", "Distance" });
/*  78 */   public NumberSetting aps = new NumberSetting("APS", 14.0D, 2.0D, 20.0D, 1.0D);
/*  79 */   public BooleanSetting autoblock = new BooleanSetting("AutoBlock", true);
/*  80 */   public ModeSetting amode = new ModeSetting("AutoBlock Mode", "Packet", new String[] { "Legit", "Fake", "Packet" });
/*  81 */   public static ModeSetting strafe = new ModeSetting("Strafe Mode", "Normal", new String[] { "Normal", "Silent", "None" });
/*  82 */   public BooleanSetting noSwing = new BooleanSetting("No Swing", false);
/*  83 */   public ModeSetting test = new ModeSetting("Test", "One", new String[] { "One", "Two", "Three" });
/*  84 */   public BooleanSetting team = new BooleanSetting("Ignore Team", true);
/*  85 */   public BooleanSetting players = new BooleanSetting("Players Only", true);
/*  86 */   public static BooleanSetting tp = new BooleanSetting("Teleport", false);
/*  87 */   public static BooleanSetting delay = new BooleanSetting("1.9+ Delay", false);
/*  88 */   public static BooleanSetting drawrad = new BooleanSetting("Draw Radius", false);
/*  89 */   public static BooleanSetting superkb = new BooleanSetting("Super KB", false);
/*     */   
/*     */   public static List<EntityLivingBase> targets;
/*     */   
/*     */   public KillAura() {
/*  94 */     super("KillAura", 0, Module.Category.COMBAT);
/*  95 */     addSettings(new Setting[] { (Setting)range, (Setting)mode, (Setting)this.aps, (Setting)this.autoblock, (Setting)strafe, (Setting)this.noSwing, (Setting)this.team, (Setting)this.players, (Setting)delay, (Setting)drawrad, (Setting)superkb });
/*     */   }
/*     */   
/*     */   public void onEnable() {
/*  99 */     direction = 1;
/* 100 */     pos = 0.5D;
/* 101 */     this.timerd.reset();
/* 102 */     this.timerbruh.reset();
/* 103 */     super.onEnable();
/*     */   }
/*     */   
/*     */   public void onDisable() {
/* 107 */     mc.gameSettings.keyBindLeft.pressed = false;
/* 108 */     mc.gameSettings.keyBindRight.pressed = false;
/* 109 */     mc.gameSettings.keyBindUseItem.pressed = false;
/* 110 */     mc.timer.timerSpeed = 1.0F;
/* 111 */     super.onDisable();
/*     */   }
/*     */   
/*     */   public void onStandby() {
/* 115 */     if (!this.settings.contains(this.amode) && this.autoblock.enabled) {
/* 116 */       addSettings(new Setting[] { (Setting)this.amode });
/* 117 */     } else if (!this.autoblock.enabled && this.settings.contains(this.amode)) {
/* 118 */       removeSettings(new Setting[] { (Setting)this.amode });
/*     */     } 
/*     */     
/* 121 */     if (!this.settings.contains(switchmode) && mode.getMode() == "Switch") {
/* 122 */       addSettings(new Setting[] { (Setting)switchmode });
/* 123 */     } else if (mode.getMode() != "Switch" && this.settings.contains(switchmode)) {
/* 124 */       removeSettings(new Setting[] { (Setting)switchmode });
/*     */     } 
/*     */   }
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
/*     */ 
/*     */   
/*     */   public void onEvent(Event e) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof leap/events/listeners/EventMotion
/*     */     //   4: ifeq -> 1837
/*     */     //   7: aload_0
/*     */     //   8: getfield settings : Ljava/util/List;
/*     */     //   11: getstatic leap/modules/combat/KillAura.switchmode : Lleap/settings/ModeSetting;
/*     */     //   14: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   19: ifne -> 50
/*     */     //   22: getstatic leap/modules/combat/KillAura.mode : Lleap/settings/ModeSetting;
/*     */     //   25: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   28: ldc 'Switch'
/*     */     //   30: if_acmpne -> 50
/*     */     //   33: aload_0
/*     */     //   34: iconst_1
/*     */     //   35: anewarray leap/settings/Setting
/*     */     //   38: dup
/*     */     //   39: iconst_0
/*     */     //   40: getstatic leap/modules/combat/KillAura.switchmode : Lleap/settings/ModeSetting;
/*     */     //   43: aastore
/*     */     //   44: invokevirtual addSettings : ([Lleap/settings/Setting;)V
/*     */     //   47: goto -> 90
/*     */     //   50: getstatic leap/modules/combat/KillAura.mode : Lleap/settings/ModeSetting;
/*     */     //   53: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   56: ldc 'Switch'
/*     */     //   58: if_acmpeq -> 90
/*     */     //   61: aload_0
/*     */     //   62: getfield settings : Ljava/util/List;
/*     */     //   65: getstatic leap/modules/combat/KillAura.switchmode : Lleap/settings/ModeSetting;
/*     */     //   68: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   73: ifeq -> 90
/*     */     //   76: aload_0
/*     */     //   77: iconst_1
/*     */     //   78: anewarray leap/settings/Setting
/*     */     //   81: dup
/*     */     //   82: iconst_0
/*     */     //   83: getstatic leap/modules/combat/KillAura.switchmode : Lleap/settings/ModeSetting;
/*     */     //   86: aastore
/*     */     //   87: invokevirtual removeSettings : ([Lleap/settings/Setting;)V
/*     */     //   90: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   93: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   96: ifnull -> 132
/*     */     //   99: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   102: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
/*     */     //   105: ifnull -> 132
/*     */     //   108: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   111: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   114: getfield isDead : Z
/*     */     //   117: ifne -> 132
/*     */     //   120: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   123: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   126: invokevirtual isEntityAlive : ()Z
/*     */     //   129: ifne -> 138
/*     */     //   132: getstatic leap/modules/combat/KillAura.bots : Ljava/util/ArrayList;
/*     */     //   135: invokevirtual clear : ()V
/*     */     //   138: aload_1
/*     */     //   139: invokevirtual isPre : ()Z
/*     */     //   142: ifeq -> 1781
/*     */     //   145: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   148: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   151: ifnull -> 187
/*     */     //   154: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   157: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
/*     */     //   160: ifnull -> 187
/*     */     //   163: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   166: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   169: getfield isDead : Z
/*     */     //   172: ifne -> 187
/*     */     //   175: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   178: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   181: invokevirtual isEntityAlive : ()Z
/*     */     //   184: ifne -> 191
/*     */     //   187: aload_0
/*     */     //   188: invokevirtual toggle : ()V
/*     */     //   191: aload_1
/*     */     //   192: checkcast leap/events/listeners/EventMotion
/*     */     //   195: astore_2
/*     */     //   196: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   199: ifnull -> 282
/*     */     //   202: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   205: invokeinterface isEmpty : ()Z
/*     */     //   210: ifne -> 282
/*     */     //   213: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   216: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   219: ifnull -> 282
/*     */     //   222: aload_2
/*     */     //   223: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   226: iconst_0
/*     */     //   227: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   232: checkcast net/minecraft/entity/EntityLivingBase
/*     */     //   235: invokestatic getPredictedRotations : (Lnet/minecraft/entity/EntityLivingBase;)[F
/*     */     //   238: iconst_1
/*     */     //   239: faload
/*     */     //   240: dconst_1
/*     */     //   241: ldc2_w 6.0
/*     */     //   244: invokestatic randomNumber : (DD)I
/*     */     //   247: i2f
/*     */     //   248: fadd
/*     */     //   249: invokevirtual setPitch : (F)V
/*     */     //   252: aload_2
/*     */     //   253: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   256: iconst_0
/*     */     //   257: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   262: checkcast net/minecraft/entity/EntityLivingBase
/*     */     //   265: invokestatic getPredictedRotations : (Lnet/minecraft/entity/EntityLivingBase;)[F
/*     */     //   268: iconst_0
/*     */     //   269: faload
/*     */     //   270: dconst_1
/*     */     //   271: ldc2_w 6.0
/*     */     //   274: invokestatic randomNumber : (DD)I
/*     */     //   277: i2f
/*     */     //   278: fadd
/*     */     //   279: invokevirtual setYaw : (F)V
/*     */     //   282: aload_0
/*     */     //   283: getfield timer : Lleap/util/Timer;
/*     */     //   286: ldc2_w 1000.0
/*     */     //   289: aload_0
/*     */     //   290: getfield aps : Lleap/settings/NumberSetting;
/*     */     //   293: invokevirtual getValue : ()D
/*     */     //   296: ddiv
/*     */     //   297: d2l
/*     */     //   298: iconst_1
/*     */     //   299: invokevirtual hasTimeElapsed : (JZ)Z
/*     */     //   302: ifeq -> 1781
/*     */     //   305: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   308: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
/*     */     //   311: getfield loadedEntityList : Ljava/util/List;
/*     */     //   314: invokeinterface stream : ()Ljava/util/stream/Stream;
/*     */     //   319: ldc_w net/minecraft/entity/EntityLivingBase
/*     */     //   322: dup
/*     */     //   323: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   326: pop
/*     */     //   327: <illegal opcode> test : (Ljava/lang/Class;)Ljava/util/function/Predicate;
/*     */     //   332: invokeinterface filter : (Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
/*     */     //   337: invokestatic toList : ()Ljava/util/stream/Collector;
/*     */     //   340: invokeinterface collect : (Ljava/util/stream/Collector;)Ljava/lang/Object;
/*     */     //   345: checkcast java/util/List
/*     */     //   348: putstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   351: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   354: invokeinterface stream : ()Ljava/util/stream/Stream;
/*     */     //   359: <illegal opcode> test : ()Ljava/util/function/Predicate;
/*     */     //   364: invokeinterface filter : (Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
/*     */     //   369: invokestatic toList : ()Ljava/util/stream/Collector;
/*     */     //   372: invokeinterface collect : (Ljava/util/stream/Collector;)Ljava/lang/Object;
/*     */     //   377: checkcast java/util/List
/*     */     //   380: putstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   383: aload_0
/*     */     //   384: getfield team : Lleap/settings/BooleanSetting;
/*     */     //   387: invokevirtual isEnabled : ()Z
/*     */     //   390: ifeq -> 425
/*     */     //   393: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   396: invokeinterface stream : ()Ljava/util/stream/Stream;
/*     */     //   401: <illegal opcode> test : ()Ljava/util/function/Predicate;
/*     */     //   406: invokeinterface filter : (Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
/*     */     //   411: invokestatic toList : ()Ljava/util/stream/Collector;
/*     */     //   414: invokeinterface collect : (Ljava/util/stream/Collector;)Ljava/lang/Object;
/*     */     //   419: checkcast java/util/List
/*     */     //   422: putstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   425: getstatic leap/modules/combat/KillAura.mode : Lleap/settings/ModeSetting;
/*     */     //   428: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   431: ldc 'Switch'
/*     */     //   433: if_acmpne -> 517
/*     */     //   436: getstatic leap/modules/combat/KillAura.switchmode : Lleap/settings/ModeSetting;
/*     */     //   439: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   442: ldc 'Distance'
/*     */     //   444: if_acmpne -> 463
/*     */     //   447: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   450: <illegal opcode> applyAsDouble : ()Ljava/util/function/ToDoubleFunction;
/*     */     //   455: invokestatic comparingDouble : (Ljava/util/function/ToDoubleFunction;)Ljava/util/Comparator;
/*     */     //   458: invokeinterface sort : (Ljava/util/Comparator;)V
/*     */     //   463: getstatic leap/modules/combat/KillAura.switchmode : Lleap/settings/ModeSetting;
/*     */     //   466: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   469: ldc 'Health'
/*     */     //   471: if_acmpne -> 490
/*     */     //   474: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   477: <illegal opcode> applyAsDouble : ()Ljava/util/function/ToDoubleFunction;
/*     */     //   482: invokestatic comparingDouble : (Ljava/util/function/ToDoubleFunction;)Ljava/util/Comparator;
/*     */     //   485: invokeinterface sort : (Ljava/util/Comparator;)V
/*     */     //   490: getstatic leap/modules/combat/KillAura.switchmode : Lleap/settings/ModeSetting;
/*     */     //   493: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   496: ldc 'HurtTime'
/*     */     //   498: if_acmpne -> 517
/*     */     //   501: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   504: <illegal opcode> applyAsDouble : ()Ljava/util/function/ToDoubleFunction;
/*     */     //   509: invokestatic comparingDouble : (Ljava/util/function/ToDoubleFunction;)Ljava/util/Comparator;
/*     */     //   512: invokeinterface sort : (Ljava/util/Comparator;)V
/*     */     //   517: aload_0
/*     */     //   518: getfield players : Lleap/settings/BooleanSetting;
/*     */     //   521: invokevirtual isEnabled : ()Z
/*     */     //   524: ifeq -> 567
/*     */     //   527: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   530: invokeinterface stream : ()Ljava/util/stream/Stream;
/*     */     //   535: ldc_w net/minecraft/entity/player/EntityPlayer
/*     */     //   538: dup
/*     */     //   539: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   542: pop
/*     */     //   543: <illegal opcode> test : (Ljava/lang/Class;)Ljava/util/function/Predicate;
/*     */     //   548: invokeinterface filter : (Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
/*     */     //   553: invokestatic toList : ()Ljava/util/stream/Collector;
/*     */     //   556: invokeinterface collect : (Ljava/util/stream/Collector;)Ljava/lang/Object;
/*     */     //   561: checkcast java/util/List
/*     */     //   564: putstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   567: aload_0
/*     */     //   568: getfield activeTarget : I
/*     */     //   571: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   574: invokeinterface size : ()I
/*     */     //   579: if_icmplt -> 587
/*     */     //   582: aload_0
/*     */     //   583: iconst_0
/*     */     //   584: putfield activeTarget : I
/*     */     //   587: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   590: invokeinterface isEmpty : ()Z
/*     */     //   595: ifne -> 1736
/*     */     //   598: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   601: pop
/*     */     //   602: ldc_w 'Scaffold'
/*     */     //   605: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   608: invokevirtual isEnabled : ()Z
/*     */     //   611: ifne -> 1736
/*     */     //   614: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   617: iconst_0
/*     */     //   618: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   623: checkcast net/minecraft/entity/EntityLivingBase
/*     */     //   626: astore #5
/*     */     //   628: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   631: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   634: aload #5
/*     */     //   636: invokevirtual getDistanceToEntity : (Lnet/minecraft/entity/Entity;)F
/*     */     //   639: f2d
/*     */     //   640: getstatic leap/modules/combat/KillAura.range : Lleap/settings/NumberSetting;
/*     */     //   643: invokevirtual getValue : ()D
/*     */     //   646: dcmpl
/*     */     //   647: ifle -> 651
/*     */     //   650: return
/*     */     //   651: getstatic leap/modules/combat/KillAura.tp : Lleap/settings/BooleanSetting;
/*     */     //   654: invokevirtual isEnabled : ()Z
/*     */     //   657: ifeq -> 726
/*     */     //   660: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   663: invokevirtual getNetHandler : ()Lnet/minecraft/client/network/NetHandlerPlayClient;
/*     */     //   666: new net/minecraft/network/play/client/C03PacketPlayer
/*     */     //   669: dup
/*     */     //   670: iconst_1
/*     */     //   671: invokespecial <init> : (Z)V
/*     */     //   674: invokevirtual addToSendQueue : (Lnet/minecraft/network/Packet;)V
/*     */     //   677: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   680: invokevirtual getNetHandler : ()Lnet/minecraft/client/network/NetHandlerPlayClient;
/*     */     //   683: new net/minecraft/network/play/client/C03PacketPlayer$C04PacketPlayerPosition
/*     */     //   686: dup
/*     */     //   687: aload #5
/*     */     //   689: getfield posX : D
/*     */     //   692: aload #5
/*     */     //   694: getfield posY : D
/*     */     //   697: aload #5
/*     */     //   699: getfield posZ : D
/*     */     //   702: iconst_1
/*     */     //   703: invokespecial <init> : (DDDZ)V
/*     */     //   706: invokevirtual addToSendQueue : (Lnet/minecraft/network/Packet;)V
/*     */     //   709: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   712: invokevirtual getNetHandler : ()Lnet/minecraft/client/network/NetHandlerPlayClient;
/*     */     //   715: new net/minecraft/network/play/client/C03PacketPlayer
/*     */     //   718: dup
/*     */     //   719: iconst_1
/*     */     //   720: invokespecial <init> : (Z)V
/*     */     //   723: invokevirtual addToSendQueue : (Lnet/minecraft/network/Packet;)V
/*     */     //   726: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   729: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   732: getfield isCollidedHorizontally : Z
/*     */     //   735: ifeq -> 747
/*     */     //   738: aload_0
/*     */     //   739: aload_0
/*     */     //   740: getfield strafeDirection : D
/*     */     //   743: dneg
/*     */     //   744: putfield strafeDirection : D
/*     */     //   747: getstatic leap/modules/combat/KillAura.strafe : Lleap/settings/ModeSetting;
/*     */     //   750: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   753: ldc 'Normal'
/*     */     //   755: if_acmpne -> 876
/*     */     //   758: invokestatic canStrafe : ()Z
/*     */     //   761: ifne -> 876
/*     */     //   764: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   767: getfield timer : Lnet/minecraft/util/Timer;
/*     */     //   770: ldc_w 1.2
/*     */     //   773: putfield timerSpeed : F
/*     */     //   776: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   779: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   782: aload #5
/*     */     //   784: invokevirtual getDistanceToEntity : (Lnet/minecraft/entity/Entity;)F
/*     */     //   787: f2d
/*     */     //   788: ldc2_w 0.5
/*     */     //   791: dcmpl
/*     */     //   792: ifle -> 838
/*     */     //   795: aload_2
/*     */     //   796: invokestatic defaultMoveSpeed : ()D
/*     */     //   799: dconst_0
/*     */     //   800: dsub
/*     */     //   801: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   804: iconst_0
/*     */     //   805: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   810: checkcast net/minecraft/entity/EntityLivingBase
/*     */     //   813: invokestatic getPredictedRotations : (Lnet/minecraft/entity/EntityLivingBase;)[F
/*     */     //   816: iconst_0
/*     */     //   817: faload
/*     */     //   818: dconst_1
/*     */     //   819: ldc2_w 6.0
/*     */     //   822: invokestatic randomNumber : (DD)I
/*     */     //   825: i2f
/*     */     //   826: fadd
/*     */     //   827: dconst_1
/*     */     //   828: aload_0
/*     */     //   829: getfield strafeDirection : D
/*     */     //   832: invokestatic setMotionWithValues : (Lleap/events/listeners/EventMotion;DFDD)V
/*     */     //   835: goto -> 876
/*     */     //   838: aload_2
/*     */     //   839: invokestatic defaultMoveSpeed : ()D
/*     */     //   842: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   845: iconst_0
/*     */     //   846: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   851: checkcast net/minecraft/entity/EntityLivingBase
/*     */     //   854: invokestatic getPredictedRotations : (Lnet/minecraft/entity/EntityLivingBase;)[F
/*     */     //   857: iconst_0
/*     */     //   858: faload
/*     */     //   859: dconst_1
/*     */     //   860: ldc2_w 6.0
/*     */     //   863: invokestatic randomNumber : (DD)I
/*     */     //   866: i2f
/*     */     //   867: fadd
/*     */     //   868: dconst_0
/*     */     //   869: aload_0
/*     */     //   870: getfield strafeDirection : D
/*     */     //   873: invokestatic setMotionWithValues : (Lleap/events/listeners/EventMotion;DFDD)V
/*     */     //   876: getstatic leap/modules/combat/KillAura.strafe : Lleap/settings/ModeSetting;
/*     */     //   879: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   882: ldc 'Silent'
/*     */     //   884: if_acmpne -> 993
/*     */     //   887: invokestatic canStrafe : ()Z
/*     */     //   890: ifne -> 993
/*     */     //   893: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   896: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   899: aload #5
/*     */     //   901: invokevirtual getDistanceToEntity : (Lnet/minecraft/entity/Entity;)F
/*     */     //   904: f2d
/*     */     //   905: ldc2_w 0.5
/*     */     //   908: dcmpl
/*     */     //   909: ifle -> 955
/*     */     //   912: aload_2
/*     */     //   913: invokestatic getBaseMoveSpeed : ()D
/*     */     //   916: dconst_0
/*     */     //   917: dsub
/*     */     //   918: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   921: iconst_0
/*     */     //   922: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   927: checkcast net/minecraft/entity/EntityLivingBase
/*     */     //   930: invokestatic getPredictedRotations : (Lnet/minecraft/entity/EntityLivingBase;)[F
/*     */     //   933: iconst_0
/*     */     //   934: faload
/*     */     //   935: dconst_1
/*     */     //   936: ldc2_w 6.0
/*     */     //   939: invokestatic randomNumber : (DD)I
/*     */     //   942: i2f
/*     */     //   943: fadd
/*     */     //   944: dconst_1
/*     */     //   945: aload_0
/*     */     //   946: getfield strafeDirection : D
/*     */     //   949: invokestatic setSilentMotionWithValues : (Lleap/events/listeners/EventMotion;DFDD)V
/*     */     //   952: goto -> 993
/*     */     //   955: aload_2
/*     */     //   956: invokestatic getBaseMoveSpeed : ()D
/*     */     //   959: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   962: iconst_0
/*     */     //   963: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   968: checkcast net/minecraft/entity/EntityLivingBase
/*     */     //   971: invokestatic getPredictedRotations : (Lnet/minecraft/entity/EntityLivingBase;)[F
/*     */     //   974: iconst_0
/*     */     //   975: faload
/*     */     //   976: dconst_1
/*     */     //   977: ldc2_w 6.0
/*     */     //   980: invokestatic randomNumber : (DD)I
/*     */     //   983: i2f
/*     */     //   984: fadd
/*     */     //   985: dconst_0
/*     */     //   986: aload_0
/*     */     //   987: getfield strafeDirection : D
/*     */     //   990: invokestatic setSilentMotionWithValues : (Lleap/events/listeners/EventMotion;DFDD)V
/*     */     //   993: aload #5
/*     */     //   995: getfield hurtTime : I
/*     */     //   998: iconst_3
/*     */     //   999: if_icmpgt -> 1018
/*     */     //   1002: aload #5
/*     */     //   1004: invokevirtual getHealth : ()F
/*     */     //   1007: f2i
/*     */     //   1008: putstatic leap/modules/combat/KillAura.prevhealth : I
/*     */     //   1011: aload_0
/*     */     //   1012: getfield timerbruh : Lleap/util/Timer;
/*     */     //   1015: invokevirtual reset : ()V
/*     */     //   1018: getstatic leap/modules/combat/KillAura.mode : Lleap/settings/ModeSetting;
/*     */     //   1021: ldc 'Multi'
/*     */     //   1023: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   1026: ifeq -> 1068
/*     */     //   1029: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   1032: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   1037: astore #7
/*     */     //   1039: goto -> 1058
/*     */     //   1042: aload #7
/*     */     //   1044: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   1049: checkcast net/minecraft/entity/EntityLivingBase
/*     */     //   1052: astore #6
/*     */     //   1054: aload #6
/*     */     //   1056: astore #5
/*     */     //   1058: aload #7
/*     */     //   1060: invokeinterface hasNext : ()Z
/*     */     //   1065: ifne -> 1042
/*     */     //   1068: dconst_1
/*     */     //   1069: ldc2_w 20.0
/*     */     //   1072: ldc2_w 20.0
/*     */     //   1075: ldc2_w 20.0
/*     */     //   1078: bipush #20
/*     */     //   1080: invokestatic drawRect : (DDDDI)V
/*     */     //   1083: getstatic leap/modules/combat/KillAura.superkb : Lleap/settings/BooleanSetting;
/*     */     //   1086: invokevirtual isEnabled : ()Z
/*     */     //   1089: ifeq -> 1154
/*     */     //   1092: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1095: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1098: invokevirtual isSprinting : ()Z
/*     */     //   1101: istore #6
/*     */     //   1103: aload_0
/*     */     //   1104: new net/minecraft/network/play/client/C0BPacketEntityAction
/*     */     //   1107: dup
/*     */     //   1108: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1111: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1114: getstatic net/minecraft/network/play/client/C0BPacketEntityAction$Action.STOP_SPRINTING : Lnet/minecraft/network/play/client/C0BPacketEntityAction$Action;
/*     */     //   1117: invokespecial <init> : (Lnet/minecraft/entity/Entity;Lnet/minecraft/network/play/client/C0BPacketEntityAction$Action;)V
/*     */     //   1120: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   1123: aload_0
/*     */     //   1124: new net/minecraft/network/play/client/C0BPacketEntityAction
/*     */     //   1127: dup
/*     */     //   1128: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1131: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1134: getstatic net/minecraft/network/play/client/C0BPacketEntityAction$Action.START_SPRINTING : Lnet/minecraft/network/play/client/C0BPacketEntityAction$Action;
/*     */     //   1137: invokespecial <init> : (Lnet/minecraft/entity/Entity;Lnet/minecraft/network/play/client/C0BPacketEntityAction$Action;)V
/*     */     //   1140: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   1143: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1146: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1149: iload #6
/*     */     //   1151: invokevirtual setSprinting : (Z)V
/*     */     //   1154: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1157: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1160: invokevirtual getHeldItem : ()Lnet/minecraft/item/ItemStack;
/*     */     //   1163: getstatic net/minecraft/entity/EnumCreatureAttribute.UNDEFINED : Lnet/minecraft/entity/EnumCreatureAttribute;
/*     */     //   1166: invokestatic func_152377_a : (Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EnumCreatureAttribute;)F
/*     */     //   1169: fstore #6
/*     */     //   1171: getstatic leap/modules/render/DamageEffects.type : Lleap/settings/ModeSetting;
/*     */     //   1174: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   1177: ldc_w 'Crit'
/*     */     //   1180: if_acmpne -> 1194
/*     */     //   1183: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1186: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1189: aload #5
/*     */     //   1191: invokevirtual onCriticalHit : (Lnet/minecraft/entity/Entity;)V
/*     */     //   1194: getstatic leap/modules/render/DamageEffects.type : Lleap/settings/ModeSetting;
/*     */     //   1197: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   1200: ldc_w 'Enchant'
/*     */     //   1203: if_acmpne -> 1217
/*     */     //   1206: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1209: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1212: aload #5
/*     */     //   1214: invokevirtual onEnchantmentCritical : (Lnet/minecraft/entity/Entity;)V
/*     */     //   1217: getstatic leap/modules/combat/KillAura.mode : Lleap/settings/ModeSetting;
/*     */     //   1220: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   1223: ldc 'Multi'
/*     */     //   1225: if_acmpne -> 1438
/*     */     //   1228: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   1231: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   1236: astore #8
/*     */     //   1238: goto -> 1425
/*     */     //   1241: aload #8
/*     */     //   1243: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   1248: checkcast net/minecraft/entity/EntityLivingBase
/*     */     //   1251: astore #7
/*     */     //   1253: aload_2
/*     */     //   1254: aload #7
/*     */     //   1256: invokestatic getPredictedRotations : (Lnet/minecraft/entity/EntityLivingBase;)[F
/*     */     //   1259: iconst_1
/*     */     //   1260: faload
/*     */     //   1261: dconst_1
/*     */     //   1262: ldc2_w 6.0
/*     */     //   1265: invokestatic randomNumber : (DD)I
/*     */     //   1268: i2f
/*     */     //   1269: fadd
/*     */     //   1270: invokevirtual setPitch : (F)V
/*     */     //   1273: aload_2
/*     */     //   1274: aload #7
/*     */     //   1276: invokestatic getPredictedRotations : (Lnet/minecraft/entity/EntityLivingBase;)[F
/*     */     //   1279: iconst_0
/*     */     //   1280: faload
/*     */     //   1281: dconst_1
/*     */     //   1282: ldc2_w 6.0
/*     */     //   1285: invokestatic randomNumber : (DD)I
/*     */     //   1288: i2f
/*     */     //   1289: fadd
/*     */     //   1290: invokevirtual setYaw : (F)V
/*     */     //   1293: aload_0
/*     */     //   1294: getfield noSwing : Lleap/settings/BooleanSetting;
/*     */     //   1297: invokevirtual isEnabled : ()Z
/*     */     //   1300: ifeq -> 1317
/*     */     //   1303: aload_0
/*     */     //   1304: new net/minecraft/network/play/client/C0APacketAnimation
/*     */     //   1307: dup
/*     */     //   1308: invokespecial <init> : ()V
/*     */     //   1311: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   1314: goto -> 1326
/*     */     //   1317: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1320: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1323: invokevirtual swingItem : ()V
/*     */     //   1326: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1329: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1332: getfield posX : D
/*     */     //   1335: dstore #9
/*     */     //   1337: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1340: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1343: getfield posY : D
/*     */     //   1346: dstore #11
/*     */     //   1348: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1351: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1354: getfield posZ : D
/*     */     //   1357: dstore #13
/*     */     //   1359: getstatic leap/modules/combat/KillAura.delay : Lleap/settings/BooleanSetting;
/*     */     //   1362: invokevirtual isEnabled : ()Z
/*     */     //   1365: ifeq -> 1382
/*     */     //   1368: aload_0
/*     */     //   1369: getfield timerd : Lleap/util/Timer;
/*     */     //   1372: ldc2_w 5000
/*     */     //   1375: iconst_1
/*     */     //   1376: invokevirtual hasTimeElapsed : (JZ)Z
/*     */     //   1379: ifne -> 1391
/*     */     //   1382: getstatic leap/modules/combat/KillAura.delay : Lleap/settings/BooleanSetting;
/*     */     //   1385: invokevirtual isEnabled : ()Z
/*     */     //   1388: ifne -> 1425
/*     */     //   1391: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1394: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1397: aload #5
/*     */     //   1399: invokevirtual attackTargetEntityWithCurrentItem : (Lnet/minecraft/entity/Entity;)V
/*     */     //   1402: aload_0
/*     */     //   1403: new net/minecraft/network/play/client/C02PacketUseEntity
/*     */     //   1406: dup
/*     */     //   1407: aload #7
/*     */     //   1409: getstatic net/minecraft/network/play/client/C02PacketUseEntity$Action.ATTACK : Lnet/minecraft/network/play/client/C02PacketUseEntity$Action;
/*     */     //   1412: invokespecial <init> : (Lnet/minecraft/entity/Entity;Lnet/minecraft/network/play/client/C02PacketUseEntity$Action;)V
/*     */     //   1415: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   1418: aload_0
/*     */     //   1419: getfield timerd : Lleap/util/Timer;
/*     */     //   1422: invokevirtual reset : ()V
/*     */     //   1425: aload #8
/*     */     //   1427: invokeinterface hasNext : ()Z
/*     */     //   1432: ifne -> 1241
/*     */     //   1435: goto -> 1546
/*     */     //   1438: aload_0
/*     */     //   1439: getfield noSwing : Lleap/settings/BooleanSetting;
/*     */     //   1442: invokevirtual isEnabled : ()Z
/*     */     //   1445: ifeq -> 1462
/*     */     //   1448: aload_0
/*     */     //   1449: new net/minecraft/network/play/client/C0APacketAnimation
/*     */     //   1452: dup
/*     */     //   1453: invokespecial <init> : ()V
/*     */     //   1456: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   1459: goto -> 1471
/*     */     //   1462: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1465: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1468: invokevirtual swingItem : ()V
/*     */     //   1471: getstatic leap/modules/combat/KillAura.delay : Lleap/settings/BooleanSetting;
/*     */     //   1474: invokevirtual isEnabled : ()Z
/*     */     //   1477: ifeq -> 1494
/*     */     //   1480: aload_0
/*     */     //   1481: getfield timerd : Lleap/util/Timer;
/*     */     //   1484: ldc2_w 5000
/*     */     //   1487: iconst_1
/*     */     //   1488: invokevirtual hasTimeElapsed : (JZ)Z
/*     */     //   1491: ifne -> 1503
/*     */     //   1494: getstatic leap/modules/combat/KillAura.delay : Lleap/settings/BooleanSetting;
/*     */     //   1497: invokevirtual isEnabled : ()Z
/*     */     //   1500: ifne -> 1546
/*     */     //   1503: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1506: getfield playerController : Lnet/minecraft/client/multiplayer/PlayerControllerMP;
/*     */     //   1509: invokevirtual syncCurrentPlayItem : ()V
/*     */     //   1512: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1515: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1518: aload #5
/*     */     //   1520: invokevirtual attackTargetEntityWithCurrentItem : (Lnet/minecraft/entity/Entity;)V
/*     */     //   1523: aload_0
/*     */     //   1524: new net/minecraft/network/play/client/C02PacketUseEntity
/*     */     //   1527: dup
/*     */     //   1528: aload #5
/*     */     //   1530: getstatic net/minecraft/network/play/client/C02PacketUseEntity$Action.ATTACK : Lnet/minecraft/network/play/client/C02PacketUseEntity$Action;
/*     */     //   1533: invokespecial <init> : (Lnet/minecraft/entity/Entity;Lnet/minecraft/network/play/client/C02PacketUseEntity$Action;)V
/*     */     //   1536: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   1539: aload_0
/*     */     //   1540: getfield timerd : Lleap/util/Timer;
/*     */     //   1543: invokevirtual reset : ()V
/*     */     //   1546: aload_1
/*     */     //   1547: invokevirtual isPre : ()Z
/*     */     //   1550: ifeq -> 1746
/*     */     //   1553: aload_0
/*     */     //   1554: getfield autoblock : Lleap/settings/BooleanSetting;
/*     */     //   1557: invokevirtual isEnabled : ()Z
/*     */     //   1560: ifeq -> 1746
/*     */     //   1563: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1566: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   1569: getfield keyBindUseItem : Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   1572: invokevirtual isPressed : ()Z
/*     */     //   1575: ifne -> 1746
/*     */     //   1578: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1581: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1584: ifnull -> 1746
/*     */     //   1587: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1590: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
/*     */     //   1593: ifnull -> 1746
/*     */     //   1596: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1599: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1602: invokevirtual getHeldItem : ()Lnet/minecraft/item/ItemStack;
/*     */     //   1605: ifnull -> 1746
/*     */     //   1608: aload_0
/*     */     //   1609: getfield amode : Lleap/settings/ModeSetting;
/*     */     //   1612: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   1615: ldc 'Packet'
/*     */     //   1617: if_acmpne -> 1664
/*     */     //   1620: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1623: getfield playerController : Lnet/minecraft/client/multiplayer/PlayerControllerMP;
/*     */     //   1626: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1629: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1632: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1635: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
/*     */     //   1638: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1641: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1644: invokevirtual getHeldItem : ()Lnet/minecraft/item/ItemStack;
/*     */     //   1647: invokevirtual sendUseItem : (Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/item/ItemStack;)Z
/*     */     //   1650: pop
/*     */     //   1651: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1654: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   1657: getfield keyBindUseItem : Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   1660: iconst_1
/*     */     //   1661: putfield pressed : Z
/*     */     //   1664: aload_0
/*     */     //   1665: getfield amode : Lleap/settings/ModeSetting;
/*     */     //   1668: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   1671: ldc 'Legit'
/*     */     //   1673: if_acmpne -> 1698
/*     */     //   1676: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1679: getfield playerController : Lnet/minecraft/client/multiplayer/PlayerControllerMP;
/*     */     //   1682: invokevirtual syncCurrentPlayItem : ()V
/*     */     //   1685: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1688: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   1691: getfield keyBindUseItem : Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   1694: iconst_1
/*     */     //   1695: putfield pressed : Z
/*     */     //   1698: aload_0
/*     */     //   1699: getfield amode : Lleap/settings/ModeSetting;
/*     */     //   1702: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   1705: ldc 'Fake'
/*     */     //   1707: if_acmpne -> 1746
/*     */     //   1710: aload_0
/*     */     //   1711: new net/minecraft/network/play/client/C08PacketPlayerBlockPlacement
/*     */     //   1714: dup
/*     */     //   1715: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1718: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1721: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   1724: invokevirtual getCurrentItem : ()Lnet/minecraft/item/ItemStack;
/*     */     //   1727: invokespecial <init> : (Lnet/minecraft/item/ItemStack;)V
/*     */     //   1730: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   1733: goto -> 1746
/*     */     //   1736: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1739: getfield timer : Lnet/minecraft/util/Timer;
/*     */     //   1742: fconst_1
/*     */     //   1743: putfield timerSpeed : F
/*     */     //   1746: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   1749: invokeinterface isEmpty : ()Z
/*     */     //   1754: ifeq -> 1781
/*     */     //   1757: iconst_1
/*     */     //   1758: invokestatic isButtonDown : (I)Z
/*     */     //   1761: ifne -> 1777
/*     */     //   1764: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1767: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   1770: getfield keyBindUseItem : Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   1773: iconst_0
/*     */     //   1774: putfield pressed : Z
/*     */     //   1777: iconst_0
/*     */     //   1778: putstatic leap/modules/combat/KillAura.prevhealth : I
/*     */     //   1781: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1784: getfield currentScreen : Lnet/minecraft/client/gui/GuiScreen;
/*     */     //   1787: instanceof net/minecraft/client/gui/GuiMerchant
/*     */     //   1790: ifeq -> 1802
/*     */     //   1793: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1796: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1799: invokevirtual closeScreen : ()V
/*     */     //   1802: new net/minecraft/util/BlockPos
/*     */     //   1805: dup
/*     */     //   1806: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1809: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1812: getfield posX : D
/*     */     //   1815: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1818: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1821: getfield posY : D
/*     */     //   1824: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1827: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1830: getfield posZ : D
/*     */     //   1833: invokespecial <init> : (DDD)V
/*     */     //   1836: astore_2
/*     */     //   1837: aload_1
/*     */     //   1838: instanceof leap/events/listeners/EventRenderWorld
/*     */     //   1841: ifeq -> 1963
/*     */     //   1844: getstatic leap/modules/combat/KillAura.drawrad : Lleap/settings/BooleanSetting;
/*     */     //   1847: invokevirtual isEnabled : ()Z
/*     */     //   1850: ifeq -> 1871
/*     */     //   1853: getstatic leap/modules/combat/KillAura.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1856: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1859: ldc_w 0.5
/*     */     //   1862: getstatic leap/modules/combat/KillAura.range : Lleap/settings/NumberSetting;
/*     */     //   1865: invokevirtual getValue : ()D
/*     */     //   1868: invokestatic drawCircle : (Lnet/minecraft/entity/Entity;FD)V
/*     */     //   1871: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   1874: ifnull -> 1963
/*     */     //   1877: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   1880: invokeinterface isEmpty : ()Z
/*     */     //   1885: ifne -> 1963
/*     */     //   1888: getstatic leap/modules/combat/KillAura.strafe : Lleap/settings/ModeSetting;
/*     */     //   1891: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   1894: ldc 'Normal'
/*     */     //   1896: if_acmpne -> 1942
/*     */     //   1899: invokestatic canStrafe : ()Z
/*     */     //   1902: ifne -> 1942
/*     */     //   1905: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   1908: iconst_0
/*     */     //   1909: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   1914: checkcast net/minecraft/entity/Entity
/*     */     //   1917: ldc_w 0.2
/*     */     //   1920: ldc2_w 0.5
/*     */     //   1923: new java/awt/Color
/*     */     //   1926: dup
/*     */     //   1927: sipush #255
/*     */     //   1930: sipush #255
/*     */     //   1933: sipush #255
/*     */     //   1936: invokespecial <init> : (III)V
/*     */     //   1939: invokestatic drawCircle : (Lnet/minecraft/entity/Entity;FDLjava/awt/Color;)V
/*     */     //   1942: getstatic leap/modules/combat/KillAura.targets : Ljava/util/List;
/*     */     //   1945: iconst_0
/*     */     //   1946: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   1951: checkcast net/minecraft/entity/Entity
/*     */     //   1954: ldc_w 0.2
/*     */     //   1957: ldc2_w 0.5
/*     */     //   1960: invokestatic drawCircleTarget : (Lnet/minecraft/entity/Entity;FD)V
/*     */     //   1963: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #132	-> 0
/*     */     //   #134	-> 7
/*     */     //   #135	-> 33
/*     */     //   #136	-> 47
/*     */     //   #137	-> 76
/*     */     //   #140	-> 90
/*     */     //   #141	-> 132
/*     */     //   #144	-> 138
/*     */     //   #150	-> 145
/*     */     //   #151	-> 187
/*     */     //   #155	-> 191
/*     */     //   #156	-> 196
/*     */     //   #157	-> 202
/*     */     //   #158	-> 222
/*     */     //   #159	-> 252
/*     */     //   #166	-> 282
/*     */     //   #167	-> 305
/*     */     //   #168	-> 351
/*     */     //   #170	-> 383
/*     */     //   #171	-> 393
/*     */     //   #175	-> 425
/*     */     //   #176	-> 436
/*     */     //   #177	-> 447
/*     */     //   #179	-> 463
/*     */     //   #180	-> 474
/*     */     //   #182	-> 490
/*     */     //   #183	-> 501
/*     */     //   #187	-> 517
/*     */     //   #188	-> 527
/*     */     //   #191	-> 567
/*     */     //   #192	-> 582
/*     */     //   #194	-> 587
/*     */     //   #196	-> 614
/*     */     //   #204	-> 628
/*     */     //   #205	-> 650
/*     */     //   #208	-> 651
/*     */     //   #209	-> 660
/*     */     //   #210	-> 677
/*     */     //   #211	-> 709
/*     */     //   #214	-> 726
/*     */     //   #215	-> 738
/*     */     //   #218	-> 747
/*     */     //   #220	-> 764
/*     */     //   #221	-> 776
/*     */     //   #222	-> 795
/*     */     //   #223	-> 835
/*     */     //   #224	-> 838
/*     */     //   #227	-> 876
/*     */     //   #228	-> 893
/*     */     //   #229	-> 912
/*     */     //   #230	-> 952
/*     */     //   #231	-> 955
/*     */     //   #235	-> 993
/*     */     //   #236	-> 1002
/*     */     //   #237	-> 1011
/*     */     //   #240	-> 1018
/*     */     //   #241	-> 1029
/*     */     //   #242	-> 1054
/*     */     //   #241	-> 1058
/*     */     //   #248	-> 1068
/*     */     //   #250	-> 1083
/*     */     //   #252	-> 1092
/*     */     //   #254	-> 1103
/*     */     //   #255	-> 1123
/*     */     //   #256	-> 1143
/*     */     //   #262	-> 1154
/*     */     //   #263	-> 1163
/*     */     //   #261	-> 1166
/*     */     //   #265	-> 1171
/*     */     //   #266	-> 1183
/*     */     //   #269	-> 1194
/*     */     //   #270	-> 1206
/*     */     //   #273	-> 1217
/*     */     //   #274	-> 1228
/*     */     //   #276	-> 1253
/*     */     //   #277	-> 1273
/*     */     //   #279	-> 1293
/*     */     //   #280	-> 1303
/*     */     //   #281	-> 1314
/*     */     //   #282	-> 1317
/*     */     //   #285	-> 1326
/*     */     //   #286	-> 1337
/*     */     //   #287	-> 1348
/*     */     //   #289	-> 1359
/*     */     //   #290	-> 1391
/*     */     //   #291	-> 1402
/*     */     //   #292	-> 1418
/*     */     //   #274	-> 1425
/*     */     //   #298	-> 1435
/*     */     //   #302	-> 1438
/*     */     //   #303	-> 1448
/*     */     //   #304	-> 1459
/*     */     //   #305	-> 1462
/*     */     //   #307	-> 1471
/*     */     //   #308	-> 1503
/*     */     //   #309	-> 1512
/*     */     //   #310	-> 1523
/*     */     //   #313	-> 1539
/*     */     //   #318	-> 1546
/*     */     //   #319	-> 1553
/*     */     //   #320	-> 1563
/*     */     //   #322	-> 1578
/*     */     //   #323	-> 1608
/*     */     //   #324	-> 1620
/*     */     //   #325	-> 1651
/*     */     //   #327	-> 1664
/*     */     //   #328	-> 1676
/*     */     //   #329	-> 1685
/*     */     //   #332	-> 1698
/*     */     //   #333	-> 1710
/*     */     //   #338	-> 1733
/*     */     //   #339	-> 1736
/*     */     //   #344	-> 1746
/*     */     //   #345	-> 1757
/*     */     //   #346	-> 1764
/*     */     //   #348	-> 1777
/*     */     //   #353	-> 1781
/*     */     //   #354	-> 1793
/*     */     //   #357	-> 1802
/*     */     //   #360	-> 1837
/*     */     //   #362	-> 1844
/*     */     //   #363	-> 1853
/*     */     //   #365	-> 1871
/*     */     //   #366	-> 1877
/*     */     //   #367	-> 1888
/*     */     //   #368	-> 1905
/*     */     //   #370	-> 1942
/*     */     //   #376	-> 1963
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	1964	0	this	Lleap/modules/combat/KillAura;
/*     */     //   0	1964	1	e	Lleap/events/Event;
/*     */     //   196	1585	2	event	Lleap/events/listeners/EventMotion;
/*     */     //   628	1105	5	target	Lnet/minecraft/entity/EntityLivingBase;
/*     */     //   1054	4	6	multitarget	Lnet/minecraft/entity/EntityLivingBase;
/*     */     //   1103	51	6	keepsprint	Z
/*     */     //   1171	562	6	sharpLevel	F
/*     */     //   1253	172	7	targetsd	Lnet/minecraft/entity/EntityLivingBase;
/*     */     //   1337	88	9	posX	D
/*     */     //   1348	77	11	posY	D
/*     */     //   1359	66	13	posZ	D
/*     */   }
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
/*     */   
/*     */   public static void drawCircle(Entity entity, float partialTicks, double rad) {
/* 386 */     GL11.glPushMatrix();
/* 387 */     GL11.glDisable(3553);
/* 388 */     GL11.glDisable(2929);
/* 389 */     GL11.glDepthMask(false);
/* 390 */     GL11.glLineWidth(5.0F);
/* 391 */     GL11.glBegin(3);
/*     */     
/* 393 */     mc.getRenderManager(); double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/* 394 */     mc.getRenderManager(); double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/* 395 */     mc.getRenderManager(); double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */     
/* 397 */     float r = 0.007843138F * (new Color(ColorUtil.flash(Color.blue, 1.0F, 1).getRGB())).getRed();
/* 398 */     float g = 0.007843138F * (new Color(ColorUtil.flash(Color.blue, 1.0F, 1).getRGB())).getGreen();
/* 399 */     float b = 0.007843138F * (new Color(ColorUtil.flash(Color.blue, 1.0F, 1).getRGB())).getBlue();
/*     */     
/* 401 */     double pix2 = 6.283185307179586D;
/*     */     
/* 403 */     for (int i = 0; i <= 60; i++) {
/* 404 */       GlStateManager.color(r, g, b, 255.0F);
/* 405 */       GL11.glVertex3d(x + rad * Math.cos(i * 6.283185307179586D / 60.0D), y, z + rad * Math.sin(i * 6.283185307179586D / 60.0D));
/*     */     } 
/*     */     
/* 408 */     GL11.glEnd();
/* 409 */     GL11.glDepthMask(true);
/* 410 */     GL11.glEnable(2929);
/* 411 */     GL11.glEnable(3553);
/* 412 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawCircleTarget(Entity entity, float partialTicks, double rad) {
/* 421 */     GL11.glPushMatrix();
/* 422 */     GL11.glDisable(3553);
/* 423 */     GL11.glDisable(2929);
/* 424 */     GL11.glDepthMask(false);
/* 425 */     GL11.glLineWidth(50.0F);
/* 426 */     GL11.glBegin(3);
/*     */     
/* 428 */     if (pos < 2.0D && up) {
/* 429 */       pos += 0.03D;
/*     */     }
/* 431 */     if (pos > 1.8D) {
/* 432 */       up = false;
/* 433 */       pos -= 0.03D;
/*     */     } 
/*     */     
/* 436 */     if (!up) {
/* 437 */       pos -= 0.03D;
/*     */     }
/*     */     
/* 440 */     if (pos < 0.5D && !up) {
/* 441 */       up = true;
/*     */     }
/*     */     
/* 444 */     mc.getRenderManager(); double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/* 445 */     mc.getRenderManager(); double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY + pos;
/* 446 */     mc.getRenderManager(); double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */     
/* 448 */     float r = 0.007843138F * (new Color(ColorUtil.flash(Color.cyan, 1.0F, 1).getRGB())).getRed();
/* 449 */     float g = 0.007843138F * (new Color(ColorUtil.flash(Color.cyan, 1.0F, 1).getRGB())).getGreen();
/* 450 */     float b = 0.007843138F * (new Color(ColorUtil.flash(Color.cyan, 1.0F, 1).getRGB())).getBlue();
/*     */     
/* 452 */     double pix2 = 6.283185307179586D;
/*     */     
/* 454 */     for (int i = 0; i <= 60; i++) {
/* 455 */       GlStateManager.color(r, g, b, 255.0F);
/* 456 */       GL11.glVertex3d(x + rad * Math.cos(i * 6.283185307179586D / 60.0D), y, z + rad * Math.sin(i * 6.283185307179586D / 60.0D));
/*     */     } 
/*     */     
/* 459 */     GL11.glEnd();
/* 460 */     GL11.glDepthMask(true);
/* 461 */     GL11.glEnable(2929);
/* 462 */     GL11.glEnable(3553);
/* 463 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static boolean isOnSameTeam(Entity entity) {
/* 467 */     boolean team = false;
/*     */     
/* 469 */     if (entity instanceof net.minecraft.entity.player.EntityPlayer) {
/* 470 */       String n = entity.getDisplayName().getFormattedText();
/* 471 */       if (n.startsWith("§f") && !n.equalsIgnoreCase(entity.getName()))
/* 472 */       { team = n.substring(0, 6).equalsIgnoreCase(mc.thePlayer.getDisplayName().getFormattedText().substring(0, 6)); }
/* 473 */       else { team = n.substring(0, 2).equalsIgnoreCase(mc.thePlayer.getDisplayName().getFormattedText().substring(0, 2)); }
/*     */     
/*     */     } 
/* 476 */     return team;
/*     */   }
/*     */   
/*     */   public static void RenderTarget() {
/* 480 */     GlStateManager.pushMatrix();
/* 481 */     GlStateManager.enableColorMaterial();
/* 482 */     GlStateManager.translate(0.5F, 0.5F, 50.0F);
/* 483 */     GlStateManager.scale(0.5F, 0.5F, 0.5F);
/* 484 */     GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
/* 485 */     GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
/* 486 */     RenderHelper.enableStandardItemLighting();
/* 487 */     GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
/* 488 */     mc.thePlayer.prevRotationYawHead = mc.thePlayer.rotationYaw;
/* 489 */     GlStateManager.translate(0.0F, 0.0F, 0.0F);
/* 490 */     RenderManager var11 = Minecraft.getMinecraft().getRenderManager();
/* 491 */     var11.func_178631_a(180.0F);
/* 492 */     var11.func_178633_a(false);
/* 493 */     var11.renderEntityWithPosYaw((Entity)mc.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/* 494 */     var11.func_178633_a(true);
/* 495 */     GlStateManager.popMatrix();
/* 496 */     RenderHelper.disableStandardItemLighting();
/* 497 */     GlStateManager.disableRescaleNormal();
/* 498 */     GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
/* 499 */     GlStateManager.func_179090_x();
/* 500 */     GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
/* 501 */     GlStateManager.popMatrix();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\combat\KillAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */