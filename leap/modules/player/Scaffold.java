/*     */ package leap.modules.player;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import leap.Client;
/*     */ import leap.events.Event;
/*     */ import leap.modules.Module;
/*     */ import leap.settings.BooleanSetting;
/*     */ import leap.settings.ModeSetting;
/*     */ import leap.settings.NumberSetting;
/*     */ import leap.settings.Setting;
/*     */ import leap.util.RandomUtil;
/*     */ import leap.util.Timer;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.Vec3;
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
/*     */ public class Scaffold
/*     */   extends Module
/*     */ {
/*  60 */   private transient int delay = 0;
/*     */   
/*     */   private boolean safewalkWasActive;
/*  63 */   public NumberSetting range = new NumberSetting("Range", 4.0D, 1.0D, 6.0D, 0.1D);
/*  64 */   public BooleanSetting towers = new BooleanSetting("Scaffold Tower", true);
/*  65 */   public BooleanSetting boosts = new BooleanSetting("Scaffold Boost", false);
/*  66 */   public NumberSetting timer = new NumberSetting("Timer", 2.0D, 0.1D, 5.0D, 0.1D);
/*  67 */   public BooleanSetting nosprint = new BooleanSetting("No Sprint", true);
/*  68 */   public static ModeSetting eagle = new ModeSetting("Eagle", "Silent", new String[] { "Silent", "None", "Strict" });
/*  69 */   public BooleanSetting swing = new BooleanSetting("Swing", true);
/*  70 */   public BooleanSetting renderrot = new BooleanSetting("Render Rotations", true);
/*  71 */   public BooleanSetting jump = new BooleanSetting("Jump", false);
/*  72 */   public BooleanSetting atof5 = new BooleanSetting("AutoF5", false);
/*  73 */   public static int offset = 0;
/*     */   
/*     */   public Scaffold() {
/*  76 */     super("Scaffold", 0, Module.Category.PLAYER);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     this.validBlocks = Arrays.asList(new Block[] { Blocks.air, (Block)Blocks.water, (Block)Blocks.flowing_water, (Block)Blocks.lava, (Block)Blocks.flowing_lava });
/*     */     
/*  88 */     this.towerStopwatch = new Timer();
/*     */     
/*  90 */     this.rng = new Random();
/*     */     
/*  92 */     this.angles = new float[2];
/*     */     
/*  94 */     this.data = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 100 */     this.ClickTimer = new Timer(); addSettings(new Setting[] { (Setting)this.towers, (Setting)this.boosts, (Setting)this.nosprint, (Setting)eagle, (Setting)this.swing, (Setting)this.renderrot, (Setting)this.jump, (Setting)this.atof5 }); this.disableonlag = true; } private static final List<Block> invalidBlocks = Arrays.asList(new Block[] { Blocks.enchanting_table, Blocks.furnace, Blocks.carpet, Blocks.crafting_table, Blocks.trapped_chest, (Block)Blocks.chest, Blocks.dispenser, Blocks.air, (Block)Blocks.water, (Block)Blocks.lava, 
/*     */         (Block)Blocks.flowing_water, (Block)Blocks.flowing_lava, (Block)Blocks.sand, Blocks.snow_layer, Blocks.torch, Blocks.anvil, Blocks.jukebox, Blocks.stone_button, Blocks.wooden_button, Blocks.lever, 
/*     */         Blocks.noteblock, Blocks.stone_pressure_plate, Blocks.light_weighted_pressure_plate, Blocks.wooden_pressure_plate, Blocks.heavy_weighted_pressure_plate, (Block)Blocks.stone_slab, (Block)Blocks.wooden_slab, (Block)Blocks.stone_slab2, (Block)Blocks.red_mushroom, (Block)Blocks.brown_mushroom, 
/* 103 */         (Block)Blocks.yellow_flower, (Block)Blocks.red_flower, Blocks.anvil, Blocks.glass_pane, (Block)Blocks.stained_glass_pane, Blocks.iron_bars, (Block)Blocks.cactus, Blocks.ladder, Blocks.web }); private final List<Block> validBlocks; private final Timer towerStopwatch; private final Random rng; private float[] angles; public void onEnable() { this.angles[0] = 999.0F;
/* 104 */     this.angles[1] = 999.0F;
/* 105 */     this.towerStopwatch.reset();
/* 106 */     this.slot = mc.thePlayer.inventory.currentItem;
/* 107 */     mc.gameSettings.keyBindSneak.pressed = false;
/* 108 */     super.onEnable(); }
/*     */   
/*     */   BlockData data; public static BlockData lastdata = null; private boolean rotating; private BlockData lastblock; private int slot; Timer ClickTimer;
/*     */   public void onDisable() {
/* 112 */     mc.timer.timerSpeed = 1.0F;
/* 113 */     if (this.atof5.isEnabled()) {
/* 114 */       mc.gameSettings.thirdPersonView = 0;
/* 115 */       mc.gameSettings.keyBindSneak.pressed = false;
/*     */     } 
/* 117 */     super.onDisable();
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
/*     */   public void onEvent(Event e) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof leap/events/listeners/EventMotion
/*     */     //   4: ifeq -> 1115
/*     */     //   7: aload_0
/*     */     //   8: getfield settings : Ljava/util/List;
/*     */     //   11: aload_0
/*     */     //   12: getfield timer : Lleap/settings/NumberSetting;
/*     */     //   15: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   20: ifne -> 51
/*     */     //   23: aload_0
/*     */     //   24: getfield boosts : Lleap/settings/BooleanSetting;
/*     */     //   27: getfield enabled : Z
/*     */     //   30: ifeq -> 51
/*     */     //   33: aload_0
/*     */     //   34: iconst_1
/*     */     //   35: anewarray leap/settings/Setting
/*     */     //   38: dup
/*     */     //   39: iconst_0
/*     */     //   40: aload_0
/*     */     //   41: getfield timer : Lleap/settings/NumberSetting;
/*     */     //   44: aastore
/*     */     //   45: invokevirtual addSettings : ([Lleap/settings/Setting;)V
/*     */     //   48: goto -> 92
/*     */     //   51: aload_0
/*     */     //   52: getfield boosts : Lleap/settings/BooleanSetting;
/*     */     //   55: getfield enabled : Z
/*     */     //   58: ifne -> 92
/*     */     //   61: aload_0
/*     */     //   62: getfield settings : Ljava/util/List;
/*     */     //   65: aload_0
/*     */     //   66: getfield timer : Lleap/settings/NumberSetting;
/*     */     //   69: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   74: ifeq -> 92
/*     */     //   77: aload_0
/*     */     //   78: iconst_1
/*     */     //   79: anewarray leap/settings/Setting
/*     */     //   82: dup
/*     */     //   83: iconst_0
/*     */     //   84: aload_0
/*     */     //   85: getfield timer : Lleap/settings/NumberSetting;
/*     */     //   88: aastore
/*     */     //   89: invokevirtual removeSettings : ([Lleap/settings/Setting;)V
/*     */     //   92: aload_1
/*     */     //   93: checkcast leap/events/listeners/EventMotion
/*     */     //   96: astore_2
/*     */     //   97: aload_0
/*     */     //   98: getfield nosprint : Lleap/settings/BooleanSetting;
/*     */     //   101: invokevirtual isEnabled : ()Z
/*     */     //   104: ifeq -> 117
/*     */     //   107: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   110: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   113: iconst_0
/*     */     //   114: invokevirtual setSprinting : (Z)V
/*     */     //   117: aload_0
/*     */     //   118: getfield towers : Lleap/settings/BooleanSetting;
/*     */     //   121: invokevirtual isEnabled : ()Z
/*     */     //   124: istore_3
/*     */     //   125: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   128: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   131: ldc_w 0.105
/*     */     //   134: putfield cameraYaw : F
/*     */     //   137: dconst_1
/*     */     //   138: dstore #4
/*     */     //   140: aload_0
/*     */     //   141: aconst_null
/*     */     //   142: putfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   145: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   148: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   151: getfield posY : D
/*     */     //   154: dconst_1
/*     */     //   155: dsub
/*     */     //   156: dstore #6
/*     */     //   158: goto -> 252
/*     */     //   161: aload_0
/*     */     //   162: new net/minecraft/util/BlockPos
/*     */     //   165: dup
/*     */     //   166: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   169: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   172: getfield posX : D
/*     */     //   175: dload #6
/*     */     //   177: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   180: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   183: getfield posZ : D
/*     */     //   186: invokespecial <init> : (DDD)V
/*     */     //   189: invokespecial getBlockData : (Lnet/minecraft/util/BlockPos;)Lleap/modules/player/Scaffold$BlockData;
/*     */     //   192: astore #8
/*     */     //   194: aload #8
/*     */     //   196: ifnull -> 246
/*     */     //   199: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   202: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   205: getfield posY : D
/*     */     //   208: dload #6
/*     */     //   210: dsub
/*     */     //   211: dstore #4
/*     */     //   213: dload #4
/*     */     //   215: ldc2_w 3.0
/*     */     //   218: dcmpg
/*     */     //   219: ifgt -> 246
/*     */     //   222: aload_0
/*     */     //   223: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   226: ifnull -> 237
/*     */     //   229: aload_0
/*     */     //   230: aload_0
/*     */     //   231: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   234: putfield lastblock : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   237: aload_0
/*     */     //   238: aload #8
/*     */     //   240: putfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   243: goto -> 259
/*     */     //   246: dload #6
/*     */     //   248: dconst_1
/*     */     //   249: dsub
/*     */     //   250: dstore #6
/*     */     //   252: dload #6
/*     */     //   254: dconst_0
/*     */     //   255: dcmpl
/*     */     //   256: ifgt -> 161
/*     */     //   259: aload_0
/*     */     //   260: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   263: ifnull -> 378
/*     */     //   266: invokestatic getBlockCount : ()I
/*     */     //   269: ifle -> 378
/*     */     //   272: aload_0
/*     */     //   273: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   276: putstatic leap/modules/player/Scaffold.lastdata : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   279: aload_0
/*     */     //   280: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   283: getfield face : Lnet/minecraft/util/EnumFacing;
/*     */     //   286: getstatic net/minecraft/util/EnumFacing.NORTH : Lnet/minecraft/util/EnumFacing;
/*     */     //   289: if_acmpne -> 302
/*     */     //   292: aload_2
/*     */     //   293: ldc_w 360.0
/*     */     //   296: putfield yaw : F
/*     */     //   299: goto -> 378
/*     */     //   302: aload_0
/*     */     //   303: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   306: getfield face : Lnet/minecraft/util/EnumFacing;
/*     */     //   309: getstatic net/minecraft/util/EnumFacing.SOUTH : Lnet/minecraft/util/EnumFacing;
/*     */     //   312: if_acmpne -> 325
/*     */     //   315: aload_2
/*     */     //   316: ldc_w 180.0
/*     */     //   319: putfield yaw : F
/*     */     //   322: goto -> 378
/*     */     //   325: aload_0
/*     */     //   326: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   329: getfield face : Lnet/minecraft/util/EnumFacing;
/*     */     //   332: getstatic net/minecraft/util/EnumFacing.WEST : Lnet/minecraft/util/EnumFacing;
/*     */     //   335: if_acmpne -> 348
/*     */     //   338: aload_2
/*     */     //   339: ldc_w 270.0
/*     */     //   342: putfield yaw : F
/*     */     //   345: goto -> 378
/*     */     //   348: aload_0
/*     */     //   349: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   352: getfield face : Lnet/minecraft/util/EnumFacing;
/*     */     //   355: getstatic net/minecraft/util/EnumFacing.EAST : Lnet/minecraft/util/EnumFacing;
/*     */     //   358: if_acmpne -> 371
/*     */     //   361: aload_2
/*     */     //   362: ldc_w 90.0
/*     */     //   365: putfield yaw : F
/*     */     //   368: goto -> 378
/*     */     //   371: aload_2
/*     */     //   372: ldc_w 90.0
/*     */     //   375: putfield yaw : F
/*     */     //   378: iconst_m1
/*     */     //   379: istore #8
/*     */     //   381: iconst_0
/*     */     //   382: istore #9
/*     */     //   384: iconst_0
/*     */     //   385: istore #10
/*     */     //   387: goto -> 448
/*     */     //   390: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   393: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   396: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   399: iload #10
/*     */     //   401: invokevirtual getStackInSlot : (I)Lnet/minecraft/item/ItemStack;
/*     */     //   404: astore #11
/*     */     //   406: aload #11
/*     */     //   408: ifnull -> 445
/*     */     //   411: aload #11
/*     */     //   413: getfield stackSize : I
/*     */     //   416: istore #12
/*     */     //   418: aload_0
/*     */     //   419: aload #11
/*     */     //   421: invokevirtual getItem : ()Lnet/minecraft/item/Item;
/*     */     //   424: invokespecial isValidItem : (Lnet/minecraft/item/Item;)Z
/*     */     //   427: ifeq -> 445
/*     */     //   430: iload #12
/*     */     //   432: iload #9
/*     */     //   434: if_icmple -> 445
/*     */     //   437: iload #12
/*     */     //   439: istore #9
/*     */     //   441: iload #10
/*     */     //   443: istore #8
/*     */     //   445: iinc #10, 1
/*     */     //   448: iload #10
/*     */     //   450: bipush #9
/*     */     //   452: if_icmplt -> 390
/*     */     //   455: iload #8
/*     */     //   457: iconst_m1
/*     */     //   458: if_icmpne -> 461
/*     */     //   461: aload_0
/*     */     //   462: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   465: ifnull -> 1043
/*     */     //   468: iload #8
/*     */     //   470: iconst_m1
/*     */     //   471: if_icmpeq -> 1043
/*     */     //   474: aload_0
/*     */     //   475: getfield jump : Lleap/settings/BooleanSetting;
/*     */     //   478: invokevirtual isEnabled : ()Z
/*     */     //   481: ifeq -> 524
/*     */     //   484: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   487: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   490: getfield onGround : Z
/*     */     //   493: ifeq -> 524
/*     */     //   496: aload_2
/*     */     //   497: invokevirtual isOnGround : ()Z
/*     */     //   500: ifeq -> 524
/*     */     //   503: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   506: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   509: invokevirtual jump : ()V
/*     */     //   512: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   515: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   518: ldc2_w 3.0
/*     */     //   521: putfield motionY : D
/*     */     //   524: aload_0
/*     */     //   525: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   528: getfield pos : Lnet/minecraft/util/BlockPos;
/*     */     //   531: astore #10
/*     */     //   533: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   536: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
/*     */     //   539: aload #10
/*     */     //   541: aload_0
/*     */     //   542: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   545: getfield face : Lnet/minecraft/util/EnumFacing;
/*     */     //   548: invokevirtual offset : (Lnet/minecraft/util/EnumFacing;)Lnet/minecraft/util/BlockPos;
/*     */     //   551: invokevirtual getBlockState : (Lnet/minecraft/util/BlockPos;)Lnet/minecraft/block/state/IBlockState;
/*     */     //   554: invokeinterface getBlock : ()Lnet/minecraft/block/Block;
/*     */     //   559: astore #11
/*     */     //   561: aload_0
/*     */     //   562: aload_0
/*     */     //   563: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   566: invokespecial getVec3 : (Lleap/modules/player/Scaffold$BlockData;)Lnet/minecraft/util/Vec3;
/*     */     //   569: astore #12
/*     */     //   571: aload_0
/*     */     //   572: getfield atof5 : Lleap/settings/BooleanSetting;
/*     */     //   575: invokevirtual isEnabled : ()Z
/*     */     //   578: ifeq -> 591
/*     */     //   581: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   584: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   587: iconst_1
/*     */     //   588: putfield thirdPersonView : I
/*     */     //   591: aload_0
/*     */     //   592: getfield validBlocks : Ljava/util/List;
/*     */     //   595: aload #11
/*     */     //   597: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   602: ifeq -> 614
/*     */     //   605: aload_0
/*     */     //   606: dload #4
/*     */     //   608: invokespecial isBlockUnder : (D)Z
/*     */     //   611: ifeq -> 615
/*     */     //   614: return
/*     */     //   615: aload_1
/*     */     //   616: invokevirtual isPre : ()Z
/*     */     //   619: ifeq -> 736
/*     */     //   622: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   625: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   628: getfield keyBindSneak : Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   631: iconst_0
/*     */     //   632: putfield pressed : Z
/*     */     //   635: aload_2
/*     */     //   636: ldc_w 79.0
/*     */     //   639: invokevirtual setPitch : (F)V
/*     */     //   642: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   645: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   648: getfield keyBindJump : Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   651: invokevirtual getKeyCode : ()I
/*     */     //   654: invokestatic isKeyDown : (I)Z
/*     */     //   657: ifeq -> 729
/*     */     //   660: invokestatic isMovingWithKeys : ()Z
/*     */     //   663: ifne -> 729
/*     */     //   666: iload_3
/*     */     //   667: ifeq -> 729
/*     */     //   670: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   673: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   676: getfield onGround : Z
/*     */     //   679: ifeq -> 701
/*     */     //   682: aload_0
/*     */     //   683: invokevirtual fakeJump : ()V
/*     */     //   686: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   689: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   692: ldc2_w 0.42
/*     */     //   695: putfield motionY : D
/*     */     //   698: goto -> 729
/*     */     //   701: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   704: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   707: getfield motionY : D
/*     */     //   710: ldc2_w 0.1
/*     */     //   713: dcmpg
/*     */     //   714: ifge -> 729
/*     */     //   717: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   720: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   723: ldc2_w -0.3
/*     */     //   726: putfield motionY : D
/*     */     //   729: aload_0
/*     */     //   730: getfield towerStopwatch : Lleap/util/Timer;
/*     */     //   733: invokevirtual reset : ()V
/*     */     //   736: aload_1
/*     */     //   737: invokevirtual isPost : ()Z
/*     */     //   740: ifeq -> 1063
/*     */     //   743: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   746: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   749: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   752: getfield currentItem : I
/*     */     //   755: istore #13
/*     */     //   757: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   760: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   763: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   766: iload #8
/*     */     //   768: putfield currentItem : I
/*     */     //   771: getstatic leap/modules/player/Scaffold.eagle : Lleap/settings/ModeSetting;
/*     */     //   774: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   777: ldc 'Silent'
/*     */     //   779: if_acmpne -> 830
/*     */     //   782: aload_0
/*     */     //   783: dconst_1
/*     */     //   784: invokespecial isBlockUnder : (D)Z
/*     */     //   787: ifne -> 830
/*     */     //   790: aload_0
/*     */     //   791: new net/minecraft/network/play/client/C0BPacketEntityAction
/*     */     //   794: dup
/*     */     //   795: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   798: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   801: getstatic net/minecraft/network/play/client/C0BPacketEntityAction$Action.START_SNEAKING : Lnet/minecraft/network/play/client/C0BPacketEntityAction$Action;
/*     */     //   804: invokespecial <init> : (Lnet/minecraft/entity/Entity;Lnet/minecraft/network/play/client/C0BPacketEntityAction$Action;)V
/*     */     //   807: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   810: aload_0
/*     */     //   811: new net/minecraft/network/play/client/C0BPacketEntityAction
/*     */     //   814: dup
/*     */     //   815: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   818: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   821: getstatic net/minecraft/network/play/client/C0BPacketEntityAction$Action.STOP_SNEAKING : Lnet/minecraft/network/play/client/C0BPacketEntityAction$Action;
/*     */     //   824: invokespecial <init> : (Lnet/minecraft/entity/Entity;Lnet/minecraft/network/play/client/C0BPacketEntityAction$Action;)V
/*     */     //   827: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   830: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   833: getfield playerController : Lnet/minecraft/client/multiplayer/PlayerControllerMP;
/*     */     //   836: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   839: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   842: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   845: getfield theWorld : Lnet/minecraft/client/multiplayer/WorldClient;
/*     */     //   848: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   851: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   854: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   857: iload #8
/*     */     //   859: invokevirtual getStackInSlot : (I)Lnet/minecraft/item/ItemStack;
/*     */     //   862: aload_0
/*     */     //   863: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   866: getfield pos : Lnet/minecraft/util/BlockPos;
/*     */     //   869: aload_0
/*     */     //   870: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   873: getfield face : Lnet/minecraft/util/EnumFacing;
/*     */     //   876: aload #12
/*     */     //   878: invokevirtual func_178890_a : (Lnet/minecraft/client/entity/EntityPlayerSP;Lnet/minecraft/client/multiplayer/WorldClient;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/BlockPos;Lnet/minecraft/util/EnumFacing;Lnet/minecraft/util/Vec3;)Z
/*     */     //   881: ifeq -> 974
/*     */     //   884: getstatic leap/modules/player/Scaffold.eagle : Lleap/settings/ModeSetting;
/*     */     //   887: invokevirtual getMode : ()Ljava/lang/String;
/*     */     //   890: ldc 'Strict'
/*     */     //   892: if_acmpne -> 916
/*     */     //   895: aload_0
/*     */     //   896: dconst_1
/*     */     //   897: invokespecial isBlockUnder : (D)Z
/*     */     //   900: ifne -> 916
/*     */     //   903: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   906: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   909: getfield keyBindSneak : Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   912: iconst_1
/*     */     //   913: putfield pressed : Z
/*     */     //   916: aload_0
/*     */     //   917: new net/minecraft/network/play/client/C08PacketPlayerBlockPlacement
/*     */     //   920: dup
/*     */     //   921: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   924: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   927: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   930: iload #8
/*     */     //   932: invokevirtual getStackInSlot : (I)Lnet/minecraft/item/ItemStack;
/*     */     //   935: invokespecial <init> : (Lnet/minecraft/item/ItemStack;)V
/*     */     //   938: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   941: aload_0
/*     */     //   942: getfield swing : Lleap/settings/BooleanSetting;
/*     */     //   945: invokevirtual isEnabled : ()Z
/*     */     //   948: ifeq -> 963
/*     */     //   951: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   954: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   957: invokevirtual swingItem : ()V
/*     */     //   960: goto -> 974
/*     */     //   963: aload_0
/*     */     //   964: new net/minecraft/network/play/client/C0APacketAnimation
/*     */     //   967: dup
/*     */     //   968: invokespecial <init> : ()V
/*     */     //   971: invokevirtual sendNetPacket : (Lnet/minecraft/network/Packet;)V
/*     */     //   974: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   977: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   980: getfield inventory : Lnet/minecraft/entity/player/InventoryPlayer;
/*     */     //   983: iload #13
/*     */     //   985: putfield currentItem : I
/*     */     //   988: aload_0
/*     */     //   989: getfield boosts : Lleap/settings/BooleanSetting;
/*     */     //   992: invokevirtual isEnabled : ()Z
/*     */     //   995: istore #14
/*     */     //   997: iload #14
/*     */     //   999: ifeq -> 1031
/*     */     //   1002: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1005: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1008: getfield isCollidedHorizontally : Z
/*     */     //   1011: ifne -> 1031
/*     */     //   1014: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1017: getfield timer : Lnet/minecraft/util/Timer;
/*     */     //   1020: aload_0
/*     */     //   1021: getfield timer : Lleap/settings/NumberSetting;
/*     */     //   1024: invokevirtual getValue : ()D
/*     */     //   1027: d2f
/*     */     //   1028: putfield timerSpeed : F
/*     */     //   1031: aload_0
/*     */     //   1032: getfield data : Lleap/modules/player/Scaffold$BlockData;
/*     */     //   1035: getfield face : Lnet/minecraft/util/EnumFacing;
/*     */     //   1038: astore #15
/*     */     //   1040: goto -> 1063
/*     */     //   1043: aload_0
/*     */     //   1044: getfield atof5 : Lleap/settings/BooleanSetting;
/*     */     //   1047: invokevirtual isEnabled : ()Z
/*     */     //   1050: ifeq -> 1063
/*     */     //   1053: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1056: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   1059: iconst_0
/*     */     //   1060: putfield thirdPersonView : I
/*     */     //   1063: invokestatic getBlockCount : ()I
/*     */     //   1066: ifgt -> 1115
/*     */     //   1069: aload_0
/*     */     //   1070: ldc2_w 3.0
/*     */     //   1073: invokespecial isBlockUnder : (D)Z
/*     */     //   1076: ifne -> 1092
/*     */     //   1079: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1082: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   1085: getfield keyBindSneak : Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   1088: iconst_1
/*     */     //   1089: putfield pressed : Z
/*     */     //   1092: aload_0
/*     */     //   1093: ldc2_w 3.0
/*     */     //   1096: invokespecial isBlockUnder : (D)Z
/*     */     //   1099: ifeq -> 1115
/*     */     //   1102: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1105: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   1108: getfield keyBindSneak : Lnet/minecraft/client/settings/KeyBinding;
/*     */     //   1111: iconst_0
/*     */     //   1112: putfield pressed : Z
/*     */     //   1115: aload_1
/*     */     //   1116: instanceof leap/events/listeners/EventRenderWorld
/*     */     //   1119: ifeq -> 1148
/*     */     //   1122: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1125: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1128: fconst_1
/*     */     //   1129: ldc2_w 0.6
/*     */     //   1132: invokestatic drawCircle : (Lnet/minecraft/entity/Entity;FD)V
/*     */     //   1135: getstatic leap/modules/player/Scaffold.mc : Lnet/minecraft/client/Minecraft;
/*     */     //   1138: getfield thePlayer : Lnet/minecraft/client/entity/EntityPlayerSP;
/*     */     //   1141: fconst_1
/*     */     //   1142: ldc2_w 0.8
/*     */     //   1145: invokestatic drawCircle : (Lnet/minecraft/entity/Entity;FD)V
/*     */     //   1148: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #121	-> 0
/*     */     //   #125	-> 7
/*     */     //   #126	-> 33
/*     */     //   #127	-> 48
/*     */     //   #128	-> 77
/*     */     //   #131	-> 92
/*     */     //   #134	-> 97
/*     */     //   #135	-> 107
/*     */     //   #138	-> 117
/*     */     //   #139	-> 125
/*     */     //   #140	-> 137
/*     */     //   #141	-> 140
/*     */     //   #144	-> 145
/*     */     //   #145	-> 161
/*     */     //   #146	-> 194
/*     */     //   #147	-> 199
/*     */     //   #148	-> 213
/*     */     //   #149	-> 222
/*     */     //   #150	-> 229
/*     */     //   #152	-> 237
/*     */     //   #153	-> 243
/*     */     //   #144	-> 246
/*     */     //   #158	-> 259
/*     */     //   #161	-> 272
/*     */     //   #164	-> 279
/*     */     //   #165	-> 292
/*     */     //   #166	-> 299
/*     */     //   #167	-> 302
/*     */     //   #168	-> 315
/*     */     //   #169	-> 322
/*     */     //   #170	-> 325
/*     */     //   #171	-> 338
/*     */     //   #172	-> 345
/*     */     //   #173	-> 348
/*     */     //   #174	-> 361
/*     */     //   #175	-> 368
/*     */     //   #176	-> 371
/*     */     //   #183	-> 378
/*     */     //   #184	-> 381
/*     */     //   #185	-> 384
/*     */     //   #186	-> 390
/*     */     //   #187	-> 406
/*     */     //   #188	-> 411
/*     */     //   #189	-> 418
/*     */     //   #190	-> 437
/*     */     //   #191	-> 441
/*     */     //   #185	-> 445
/*     */     //   #195	-> 455
/*     */     //   #196	-> 461
/*     */     //   #200	-> 474
/*     */     //   #201	-> 484
/*     */     //   #202	-> 503
/*     */     //   #203	-> 512
/*     */     //   #208	-> 524
/*     */     //   #209	-> 533
/*     */     //   #210	-> 561
/*     */     //   #211	-> 571
/*     */     //   #212	-> 581
/*     */     //   #215	-> 591
/*     */     //   #216	-> 614
/*     */     //   #217	-> 615
/*     */     //   #218	-> 622
/*     */     //   #219	-> 635
/*     */     //   #221	-> 642
/*     */     //   #222	-> 660
/*     */     //   #223	-> 670
/*     */     //   #224	-> 682
/*     */     //   #225	-> 686
/*     */     //   #226	-> 698
/*     */     //   #230	-> 729
/*     */     //   #232	-> 736
/*     */     //   #234	-> 743
/*     */     //   #235	-> 757
/*     */     //   #237	-> 771
/*     */     //   #238	-> 790
/*     */     //   #239	-> 810
/*     */     //   #243	-> 830
/*     */     //   #244	-> 884
/*     */     //   #245	-> 903
/*     */     //   #248	-> 916
/*     */     //   #251	-> 941
/*     */     //   #252	-> 951
/*     */     //   #253	-> 960
/*     */     //   #254	-> 963
/*     */     //   #268	-> 974
/*     */     //   #269	-> 988
/*     */     //   #270	-> 997
/*     */     //   #271	-> 1002
/*     */     //   #272	-> 1014
/*     */     //   #276	-> 1031
/*     */     //   #279	-> 1040
/*     */     //   #280	-> 1043
/*     */     //   #281	-> 1053
/*     */     //   #285	-> 1063
/*     */     //   #286	-> 1069
/*     */     //   #287	-> 1079
/*     */     //   #289	-> 1092
/*     */     //   #290	-> 1102
/*     */     //   #296	-> 1115
/*     */     //   #297	-> 1122
/*     */     //   #298	-> 1135
/*     */     //   #302	-> 1148
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	1149	0	this	Lleap/modules/player/Scaffold;
/*     */     //   0	1149	1	e	Lleap/events/Event;
/*     */     //   97	1018	2	event	Lleap/events/listeners/EventMotion;
/*     */     //   125	990	3	tower	Z
/*     */     //   140	975	4	yDif	D
/*     */     //   158	957	6	posY	D
/*     */     //   194	52	8	newData	Lleap/modules/player/Scaffold$BlockData;
/*     */     //   381	734	8	slot	I
/*     */     //   384	731	9	blockCount	I
/*     */     //   387	68	10	i	I
/*     */     //   406	39	11	itemStack	Lnet/minecraft/item/ItemStack;
/*     */     //   418	27	12	stackSize	I
/*     */     //   533	507	10	pos	Lnet/minecraft/util/BlockPos;
/*     */     //   561	479	11	block	Lnet/minecraft/block/Block;
/*     */     //   571	469	12	hitVec	Lnet/minecraft/util/Vec3;
/*     */     //   757	283	13	last	I
/*     */     //   997	43	14	boost	Z
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
/*     */   public void onStandby() {
/* 305 */     if (!this.settings.contains(this.timer) && this.boosts.enabled) {
/* 306 */       addSettings(new Setting[] { (Setting)this.timer });
/* 307 */     } else if (!this.boosts.enabled && this.settings.contains(this.timer)) {
/* 308 */       removeSettings(new Setting[] { (Setting)this.timer });
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isBlockUnder(double yOffset) {
/* 313 */     EntityPlayerSP player = mc.thePlayer; return 
/* 314 */       !this.validBlocks.contains(mc.theWorld.getBlockState(new BlockPos(player.posX, player.posY - yOffset, player.posZ)).getBlock());
/*     */   }
/*     */   
/*     */   private Vec3 getVec3(BlockData data) {
/* 318 */     BlockPos pos = data.pos;
/* 319 */     EnumFacing face = data.face;
/* 320 */     double x = pos.getX() + 0.5D;
/* 321 */     double y = pos.getY() + 0.5D;
/* 322 */     double z = pos.getZ() + 0.5D;
/* 323 */     x += face.getFrontOffsetX() / 2.0D;
/* 324 */     z += face.getFrontOffsetZ() / 2.0D;
/* 325 */     y += face.getFrontOffsetY() / 2.0D;
/* 326 */     if (face == EnumFacing.UP || face == EnumFacing.DOWN) {
/* 327 */       x += RandomUtil.randomNumber(0.3D, -0.3D);
/* 328 */       z += RandomUtil.randomNumber(0.3D, -0.3D);
/*     */     } else {
/* 330 */       y += RandomUtil.randomNumber(0.49D, 0.5D);
/*     */     } 
/* 332 */     if (face == EnumFacing.WEST || face == EnumFacing.EAST)
/* 333 */       z += RandomUtil.randomNumber(0.3D, -0.3D); 
/* 334 */     if (face == EnumFacing.SOUTH || face == EnumFacing.NORTH)
/* 335 */       x += RandomUtil.randomNumber(0.3D, -0.3D); 
/* 336 */     return new Vec3(x, y, z);
/*     */   }
/*     */   
/*     */   private boolean isPosSolid(BlockPos pos) {
/* 340 */     Block block = mc.theWorld.getBlockState(pos).getBlock();
/* 341 */     if ((block.getMaterial().isSolid() || !block.isTranslucent() || block.isSolidFullCube() || block instanceof net.minecraft.block.BlockLadder || block instanceof net.minecraft.block.BlockCarpet || 
/* 342 */       block instanceof net.minecraft.block.BlockSnow || block instanceof net.minecraft.block.BlockSkull) && 
/* 343 */       !block.getMaterial().isLiquid() && !(block instanceof net.minecraft.block.BlockContainer)) {
/* 344 */       return true;
/*     */     }
/* 346 */     return false;
/*     */   }
/*     */   
/*     */   private BlockData getBlockData(BlockPos pos) {
/* 350 */     if (isPosSolid(pos.add(0, -1, 0))) {
/* 351 */       return new BlockData(pos.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 353 */     if (isPosSolid(pos.add(-1, 0, 0))) {
/* 354 */       return new BlockData(pos.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 356 */     if (isPosSolid(pos.add(1, 0, 0))) {
/* 357 */       return new BlockData(pos.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 359 */     if (isPosSolid(pos.add(0, 0, 1))) {
/* 360 */       return new BlockData(pos.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 362 */     if (isPosSolid(pos.add(0, 0, -1))) {
/* 363 */       return new BlockData(pos.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 365 */     BlockPos pos2 = pos.add(-1, 0, 0);
/* 366 */     if (isPosSolid(pos2.add(0, -1, 0))) {
/* 367 */       return new BlockData(pos2.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 369 */     if (isPosSolid(pos2.add(-1, 0, 0))) {
/* 370 */       return new BlockData(pos2.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 372 */     if (isPosSolid(pos2.add(1, 0, 0))) {
/* 373 */       return new BlockData(pos2.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 375 */     if (isPosSolid(pos2.add(0, 0, 1))) {
/* 376 */       return new BlockData(pos2.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 378 */     if (isPosSolid(pos2.add(0, 0, -1))) {
/* 379 */       return new BlockData(pos2.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 381 */     BlockPos pos3 = pos.add(1, 0, 0);
/* 382 */     if (isPosSolid(pos3.add(0, -1, 0))) {
/* 383 */       return new BlockData(pos3.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 385 */     if (isPosSolid(pos3.add(-1, 0, 0))) {
/* 386 */       return new BlockData(pos3.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 388 */     if (isPosSolid(pos3.add(1, 0, 0))) {
/* 389 */       return new BlockData(pos3.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 391 */     if (isPosSolid(pos3.add(0, 0, 1))) {
/* 392 */       return new BlockData(pos3.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 394 */     if (isPosSolid(pos3.add(0, 0, -1))) {
/* 395 */       return new BlockData(pos3.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 397 */     BlockPos pos4 = pos.add(0, 0, 1);
/* 398 */     if (isPosSolid(pos4.add(0, -1, 0))) {
/* 399 */       return new BlockData(pos4.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 401 */     if (isPosSolid(pos4.add(-1, 0, 0))) {
/* 402 */       return new BlockData(pos4.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 404 */     if (isPosSolid(pos4.add(1, 0, 0))) {
/* 405 */       return new BlockData(pos4.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 407 */     if (isPosSolid(pos4.add(0, 0, 1))) {
/* 408 */       return new BlockData(pos4.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 410 */     if (isPosSolid(pos4.add(0, 0, -1))) {
/* 411 */       return new BlockData(pos4.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 413 */     BlockPos pos5 = pos.add(0, 0, -1);
/* 414 */     if (isPosSolid(pos5.add(0, -1, 0))) {
/* 415 */       return new BlockData(pos5.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 417 */     if (isPosSolid(pos5.add(-1, 0, 0))) {
/* 418 */       return new BlockData(pos5.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 420 */     if (isPosSolid(pos5.add(1, 0, 0))) {
/* 421 */       return new BlockData(pos5.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 423 */     if (isPosSolid(pos5.add(0, 0, 1))) {
/* 424 */       return new BlockData(pos5.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 426 */     if (isPosSolid(pos5.add(0, 0, -1))) {
/* 427 */       return new BlockData(pos5.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 429 */     BlockPos pos6 = pos.add(-2, 0, 0);
/* 430 */     if (isPosSolid(pos2.add(0, -1, 0))) {
/* 431 */       return new BlockData(pos2.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 433 */     if (isPosSolid(pos2.add(-1, 0, 0))) {
/* 434 */       return new BlockData(pos2.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 436 */     if (isPosSolid(pos2.add(1, 0, 0))) {
/* 437 */       return new BlockData(pos2.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 439 */     if (isPosSolid(pos2.add(0, 0, 1))) {
/* 440 */       return new BlockData(pos2.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 442 */     if (isPosSolid(pos2.add(0, 0, -1))) {
/* 443 */       return new BlockData(pos2.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 445 */     BlockPos pos7 = pos.add(2, 0, 0);
/* 446 */     if (isPosSolid(pos3.add(0, -1, 0))) {
/* 447 */       return new BlockData(pos3.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 449 */     if (isPosSolid(pos3.add(-1, 0, 0))) {
/* 450 */       return new BlockData(pos3.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 452 */     if (isPosSolid(pos3.add(1, 0, 0))) {
/* 453 */       return new BlockData(pos3.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 455 */     if (isPosSolid(pos3.add(0, 0, 1))) {
/* 456 */       return new BlockData(pos3.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 458 */     if (isPosSolid(pos3.add(0, 0, -1))) {
/* 459 */       return new BlockData(pos3.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 461 */     BlockPos pos8 = pos.add(0, 0, 2);
/* 462 */     if (isPosSolid(pos4.add(0, -1, 0))) {
/* 463 */       return new BlockData(pos4.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 465 */     if (isPosSolid(pos4.add(-1, 0, 0))) {
/* 466 */       return new BlockData(pos4.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 468 */     if (isPosSolid(pos4.add(1, 0, 0))) {
/* 469 */       return new BlockData(pos4.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 471 */     if (isPosSolid(pos4.add(0, 0, 1))) {
/* 472 */       return new BlockData(pos4.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 474 */     if (isPosSolid(pos4.add(0, 0, -1))) {
/* 475 */       return new BlockData(pos4.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 477 */     BlockPos pos9 = pos.add(0, 0, -2);
/* 478 */     if (isPosSolid(pos5.add(0, -1, 0))) {
/* 479 */       return new BlockData(pos5.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 481 */     if (isPosSolid(pos5.add(-1, 0, 0))) {
/* 482 */       return new BlockData(pos5.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 484 */     if (isPosSolid(pos5.add(1, 0, 0))) {
/* 485 */       return new BlockData(pos5.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 487 */     if (isPosSolid(pos5.add(0, 0, 1))) {
/* 488 */       return new BlockData(pos5.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 490 */     if (isPosSolid(pos5.add(0, 0, -1))) {
/* 491 */       return new BlockData(pos5.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 493 */     BlockPos pos10 = pos.add(0, -1, 0);
/* 494 */     if (isPosSolid(pos10.add(0, -1, 0))) {
/* 495 */       return new BlockData(pos10.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 497 */     if (isPosSolid(pos10.add(-1, 0, 0))) {
/* 498 */       return new BlockData(pos10.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 500 */     if (isPosSolid(pos10.add(1, 0, 0))) {
/* 501 */       return new BlockData(pos10.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 503 */     if (isPosSolid(pos10.add(0, 0, 1))) {
/* 504 */       return new BlockData(pos10.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 506 */     if (isPosSolid(pos10.add(0, 0, -1))) {
/* 507 */       return new BlockData(pos10.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 509 */     BlockPos pos11 = pos10.add(1, 0, 0);
/* 510 */     if (isPosSolid(pos11.add(0, -1, 0))) {
/* 511 */       return new BlockData(pos11.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 513 */     if (isPosSolid(pos11.add(-1, 0, 0))) {
/* 514 */       return new BlockData(pos11.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 516 */     if (isPosSolid(pos11.add(1, 0, 0))) {
/* 517 */       return new BlockData(pos11.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 519 */     if (isPosSolid(pos11.add(0, 0, 1))) {
/* 520 */       return new BlockData(pos11.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 522 */     if (isPosSolid(pos11.add(0, 0, -1))) {
/* 523 */       return new BlockData(pos11.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 525 */     BlockPos pos12 = pos10.add(-1, 0, 0);
/* 526 */     if (isPosSolid(pos12.add(0, -1, 0))) {
/* 527 */       return new BlockData(pos12.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 529 */     if (isPosSolid(pos12.add(-1, 0, 0))) {
/* 530 */       return new BlockData(pos12.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 532 */     if (isPosSolid(pos12.add(1, 0, 0))) {
/* 533 */       return new BlockData(pos12.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 535 */     if (isPosSolid(pos12.add(0, 0, 1))) {
/* 536 */       return new BlockData(pos12.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 538 */     if (isPosSolid(pos12.add(0, 0, -1))) {
/* 539 */       return new BlockData(pos12.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 541 */     BlockPos pos13 = pos10.add(0, 0, 1);
/* 542 */     if (isPosSolid(pos13.add(0, -1, 0))) {
/* 543 */       return new BlockData(pos13.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 545 */     if (isPosSolid(pos13.add(-1, 0, 0))) {
/* 546 */       return new BlockData(pos13.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 548 */     if (isPosSolid(pos13.add(1, 0, 0))) {
/* 549 */       return new BlockData(pos13.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 551 */     if (isPosSolid(pos13.add(0, 0, 1))) {
/* 552 */       return new BlockData(pos13.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 554 */     if (isPosSolid(pos13.add(0, 0, -1))) {
/* 555 */       return new BlockData(pos13.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 557 */     BlockPos pos14 = pos10.add(0, 0, -1);
/* 558 */     if (isPosSolid(pos14.add(0, -1, 0))) {
/* 559 */       return new BlockData(pos14.add(0, -1, 0), EnumFacing.UP, null);
/*     */     }
/* 561 */     if (isPosSolid(pos14.add(-1, 0, 0))) {
/* 562 */       return new BlockData(pos14.add(-1, 0, 0), EnumFacing.EAST, null);
/*     */     }
/* 564 */     if (isPosSolid(pos14.add(1, 0, 0))) {
/* 565 */       return new BlockData(pos14.add(1, 0, 0), EnumFacing.WEST, null);
/*     */     }
/* 567 */     if (isPosSolid(pos14.add(0, 0, 1))) {
/* 568 */       return new BlockData(pos14.add(0, 0, 1), EnumFacing.NORTH, null);
/*     */     }
/* 570 */     if (isPosSolid(pos14.add(0, 0, -1))) {
/* 571 */       return new BlockData(pos14.add(0, 0, -1), EnumFacing.SOUTH, null);
/*     */     }
/* 573 */     return null;
/*     */   }
/*     */   
/*     */   private boolean isValidItem(Item item) {
/* 577 */     if (item instanceof ItemBlock) {
/* 578 */       ItemBlock iBlock = (ItemBlock)item;
/* 579 */       Block block = iBlock.getBlock();
/* 580 */       return !invalidBlocks.contains(block);
/*     */     } 
/* 582 */     return false;
/*     */   }
/*     */   
/*     */   public static int getBlockCount() {
/* 586 */     int blockCount = 0;
/* 587 */     for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/* 588 */       if (mc.thePlayer.inventory.getStackInSlot(i) != null) {
/* 589 */         ItemStack is = mc.thePlayer.inventory.getStackInSlot(i);
/* 590 */         Item item = is.getItem();
/* 591 */         if (is.getItem() instanceof ItemBlock && !invalidBlocks.contains(((ItemBlock)item).getBlock()))
/* 592 */           blockCount += is.stackSize; 
/*     */       } 
/* 594 */     }  return blockCount;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void RenderBlocks() {
/* 600 */     ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
/* 601 */     int offset = 0;
/* 602 */     for (int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
/* 603 */       if (mc.thePlayer.inventory.getStackInSlot(i) != null) {
/* 604 */         ItemStack is = mc.thePlayer.inventory.getStackInSlot(i);
/* 605 */         Item item = is.getItem();
/* 606 */         if (is.getItem() instanceof ItemBlock && !invalidBlocks.contains(((ItemBlock)item).getBlock())) {
/* 607 */           renderItem(mc.thePlayer.inventory.getStackInSlot(i), (sr.getScaledWidth() >> 1) + Client.customFontRenderer.getStringWidth(Integer.toString(getBlockCount())) / 2 + offset * 20 + 8, (sr.getScaledHeight() >> 1) - 23);
/* 608 */           offset++;
/* 609 */           if (offset >= 1)
/*     */             return; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public static void renderItem(ItemStack stack, int x, int y) {
/* 616 */     GlStateManager.pushMatrix();
/* 617 */     RenderHelper.enableGUIStandardItemLighting();
/* 618 */     (Minecraft.getMinecraft().getRenderItem()).zLevel = -80.0F;
/* 619 */     GlStateManager.scale(1.0F, 1.0F, 0.01F);
/* 620 */     Minecraft.getMinecraft().getRenderItem().func_175042_a(stack, x, y + 8);
/*     */     
/* 622 */     (Minecraft.getMinecraft().getRenderItem()).zLevel = 0.0F;
/* 623 */     GlStateManager.popMatrix();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class BlockData
/*     */   {
/*     */     public final BlockPos pos;
/*     */     public final EnumFacing face;
/*     */     
/*     */     private BlockData(BlockPos pos, EnumFacing face) {
/* 633 */       this.pos = pos;
/* 634 */       this.face = face;
/*     */     }
/*     */   }
/*     */   
/*     */   public void fakeJump() {
/* 639 */     mc.thePlayer.isAirBorne = true;
/* 640 */     mc.thePlayer.triggerAchievement(StatList.jumpStat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawCircle(Entity entity, float partialTicks, double rad) {
/* 649 */     GL11.glPushMatrix();
/* 650 */     GL11.glDisable(3553);
/* 651 */     GL11.glDisable(2929);
/* 652 */     GL11.glDepthMask(false);
/* 653 */     GL11.glLineWidth(1.0F);
/* 654 */     GL11.glBegin(3);
/*     */     
/* 656 */     mc.getRenderManager(); double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - RenderManager.renderPosX;
/* 657 */     mc.getRenderManager(); double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - RenderManager.renderPosY;
/* 658 */     mc.getRenderManager(); double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - RenderManager.renderPosZ;
/*     */     
/* 660 */     float r = 0.007843138F * Color.white.getRed();
/* 661 */     float g = 0.007843138F * Color.white.getGreen();
/* 662 */     float b = 0.007843138F * Color.white.getBlue();
/*     */     
/* 664 */     double pix2 = 37.69911184307752D;
/*     */     
/* 666 */     for (int i = 0; i <= 60; i++) {
/* 667 */       GlStateManager.color(r, g, b, 255.0F);
/* 668 */       GL11.glVertex3d(x + rad * Math.cos(i * 37.69911184307752D / 60.0D), y, z + rad * Math.sin(i * 37.69911184307752D / 60.0D));
/*     */     } 
/*     */     
/* 671 */     GL11.glEnd();
/* 672 */     GL11.glDepthMask(true);
/* 673 */     GL11.glEnable(2929);
/* 674 */     GL11.glEnable(3553);
/* 675 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\player\Scaffold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */