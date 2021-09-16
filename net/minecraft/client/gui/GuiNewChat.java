/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiNewChat
/*     */   extends Gui
/*     */ {
/*  22 */   private static final Logger logger = LogManager.getLogger();
/*     */   
/*     */   private final Minecraft mc;
/*     */   
/*  26 */   private final List sentMessages = Lists.newArrayList();
/*     */ 
/*     */   
/*  29 */   private final List chatLines = Lists.newArrayList();
/*  30 */   private final List field_146253_i = Lists.newArrayList();
/*     */   
/*     */   private int scrollPos;
/*     */   private boolean isScrolled;
/*     */   private static final String __OBFID = "CL_00000669";
/*     */   
/*     */   public GuiNewChat(Minecraft mcIn) {
/*  37 */     this.mc = mcIn;
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
/*     */   public void drawChat(int p_146230_1_) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield mc : Lnet/minecraft/client/Minecraft;
/*     */     //   4: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   7: getfield chatVisibility : Lnet/minecraft/entity/player/EntityPlayer$EnumChatVisibility;
/*     */     //   10: getstatic net/minecraft/entity/player/EntityPlayer$EnumChatVisibility.HIDDEN : Lnet/minecraft/entity/player/EntityPlayer$EnumChatVisibility;
/*     */     //   13: if_acmpeq -> 615
/*     */     //   16: aload_0
/*     */     //   17: invokevirtual getLineCount : ()I
/*     */     //   20: istore_2
/*     */     //   21: iconst_0
/*     */     //   22: istore_3
/*     */     //   23: iconst_0
/*     */     //   24: istore #4
/*     */     //   26: aload_0
/*     */     //   27: getfield field_146253_i : Ljava/util/List;
/*     */     //   30: invokeinterface size : ()I
/*     */     //   35: istore #5
/*     */     //   37: aload_0
/*     */     //   38: getfield mc : Lnet/minecraft/client/Minecraft;
/*     */     //   41: getfield gameSettings : Lnet/minecraft/client/settings/GameSettings;
/*     */     //   44: getfield chatOpacity : F
/*     */     //   47: ldc 0.9
/*     */     //   49: fmul
/*     */     //   50: ldc 0.1
/*     */     //   52: fadd
/*     */     //   53: fstore #6
/*     */     //   55: iload #5
/*     */     //   57: ifle -> 615
/*     */     //   60: aload_0
/*     */     //   61: invokevirtual getChatOpen : ()Z
/*     */     //   64: ifeq -> 69
/*     */     //   67: iconst_1
/*     */     //   68: istore_3
/*     */     //   69: aload_0
/*     */     //   70: invokevirtual getChatScale : ()F
/*     */     //   73: fstore #7
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual getChatWidth : ()I
/*     */     //   79: i2f
/*     */     //   80: fload #7
/*     */     //   82: fdiv
/*     */     //   83: invokestatic ceiling_float_int : (F)I
/*     */     //   86: istore #8
/*     */     //   88: invokestatic pushMatrix : ()V
/*     */     //   91: fconst_2
/*     */     //   92: ldc 20.0
/*     */     //   94: fconst_0
/*     */     //   95: invokestatic translate : (FFF)V
/*     */     //   98: fload #7
/*     */     //   100: fload #7
/*     */     //   102: fconst_1
/*     */     //   103: invokestatic scale : (FFF)V
/*     */     //   106: iconst_0
/*     */     //   107: istore #9
/*     */     //   109: goto -> 432
/*     */     //   112: aload_0
/*     */     //   113: getfield field_146253_i : Ljava/util/List;
/*     */     //   116: iload #9
/*     */     //   118: aload_0
/*     */     //   119: getfield scrollPos : I
/*     */     //   122: iadd
/*     */     //   123: invokeinterface get : (I)Ljava/lang/Object;
/*     */     //   128: checkcast net/minecraft/client/gui/ChatLine
/*     */     //   131: astore #12
/*     */     //   133: aload #12
/*     */     //   135: ifnull -> 429
/*     */     //   138: iload_1
/*     */     //   139: aload #12
/*     */     //   141: invokevirtual getUpdatedCounter : ()I
/*     */     //   144: isub
/*     */     //   145: istore #10
/*     */     //   147: iload #10
/*     */     //   149: sipush #200
/*     */     //   152: if_icmplt -> 159
/*     */     //   155: iload_3
/*     */     //   156: ifeq -> 429
/*     */     //   159: iload #10
/*     */     //   161: i2d
/*     */     //   162: ldc2_w 200.0
/*     */     //   165: ddiv
/*     */     //   166: dstore #13
/*     */     //   168: dconst_1
/*     */     //   169: dload #13
/*     */     //   171: dsub
/*     */     //   172: dstore #13
/*     */     //   174: dload #13
/*     */     //   176: ldc2_w 10.0
/*     */     //   179: dmul
/*     */     //   180: dstore #13
/*     */     //   182: dload #13
/*     */     //   184: dconst_0
/*     */     //   185: dconst_1
/*     */     //   186: invokestatic clamp_double : (DDD)D
/*     */     //   189: dstore #13
/*     */     //   191: dload #13
/*     */     //   193: dload #13
/*     */     //   195: dmul
/*     */     //   196: dstore #13
/*     */     //   198: ldc2_w 255.0
/*     */     //   201: dload #13
/*     */     //   203: dmul
/*     */     //   204: d2i
/*     */     //   205: istore #11
/*     */     //   207: iload_3
/*     */     //   208: ifeq -> 216
/*     */     //   211: sipush #255
/*     */     //   214: istore #11
/*     */     //   216: iload #11
/*     */     //   218: i2f
/*     */     //   219: fload #6
/*     */     //   221: fmul
/*     */     //   222: f2i
/*     */     //   223: istore #11
/*     */     //   225: iinc #4, 1
/*     */     //   228: iload #11
/*     */     //   230: iconst_3
/*     */     //   231: if_icmple -> 429
/*     */     //   234: iconst_0
/*     */     //   235: istore #15
/*     */     //   237: iload #9
/*     */     //   239: ineg
/*     */     //   240: bipush #9
/*     */     //   242: imul
/*     */     //   243: istore #16
/*     */     //   245: getstatic leap/modules/render/BetterChat.background : Lleap/settings/BooleanSetting;
/*     */     //   248: getfield enabled : Z
/*     */     //   251: ifeq -> 269
/*     */     //   254: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   257: pop
/*     */     //   258: ldc 'BetterChat'
/*     */     //   260: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   263: invokevirtual isEnabled : ()Z
/*     */     //   266: ifne -> 284
/*     */     //   269: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   272: pop
/*     */     //   273: ldc 'BetterChat'
/*     */     //   275: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   278: invokevirtual isEnabled : ()Z
/*     */     //   281: ifne -> 314
/*     */     //   284: iload #15
/*     */     //   286: i2d
/*     */     //   287: iload #16
/*     */     //   289: bipush #9
/*     */     //   291: isub
/*     */     //   292: i2d
/*     */     //   293: iload #15
/*     */     //   295: iload #8
/*     */     //   297: iadd
/*     */     //   298: iconst_4
/*     */     //   299: iadd
/*     */     //   300: i2d
/*     */     //   301: iload #16
/*     */     //   303: i2d
/*     */     //   304: iload #11
/*     */     //   306: iconst_2
/*     */     //   307: idiv
/*     */     //   308: bipush #24
/*     */     //   310: ishl
/*     */     //   311: invokestatic drawRect : (DDDDI)V
/*     */     //   314: aload #12
/*     */     //   316: invokevirtual getChatComponent : ()Lnet/minecraft/util/IChatComponent;
/*     */     //   319: invokeinterface getFormattedText : ()Ljava/lang/String;
/*     */     //   324: astore #17
/*     */     //   326: invokestatic enableBlend : ()V
/*     */     //   329: getstatic leap/modules/render/BetterChat.cfont : Lleap/settings/BooleanSetting;
/*     */     //   332: invokevirtual isEnabled : ()Z
/*     */     //   335: ifeq -> 391
/*     */     //   338: getstatic leap/Client.INSTANCE : Lleap/Client;
/*     */     //   341: pop
/*     */     //   342: ldc 'BetterChat'
/*     */     //   344: invokestatic getModule : (Ljava/lang/String;)Lleap/modules/Module;
/*     */     //   347: invokevirtual isEnabled : ()Z
/*     */     //   350: ifeq -> 391
/*     */     //   353: getstatic leap/Client.customFontRenderer : Lleap/util/font/CustomFontRenderer;
/*     */     //   356: aload #17
/*     */     //   358: iload #15
/*     */     //   360: i2f
/*     */     //   361: f2d
/*     */     //   362: iload #16
/*     */     //   364: bipush #8
/*     */     //   366: isub
/*     */     //   367: i2f
/*     */     //   368: f2d
/*     */     //   369: new leap/util/JColor
/*     */     //   372: dup
/*     */     //   373: ldc 16777215
/*     */     //   375: iload #11
/*     */     //   377: bipush #24
/*     */     //   379: ishl
/*     */     //   380: iadd
/*     */     //   381: invokespecial <init> : (I)V
/*     */     //   384: invokevirtual drawStringWithShadow : (Ljava/lang/String;DDLleap/util/JColor;)F
/*     */     //   387: pop
/*     */     //   388: goto -> 423
/*     */     //   391: aload_0
/*     */     //   392: getfield mc : Lnet/minecraft/client/Minecraft;
/*     */     //   395: getfield fontRendererObj : Lnet/minecraft/client/gui/FontRenderer;
/*     */     //   398: aload #17
/*     */     //   400: iload #15
/*     */     //   402: i2f
/*     */     //   403: f2d
/*     */     //   404: iload #16
/*     */     //   406: bipush #8
/*     */     //   408: isub
/*     */     //   409: i2f
/*     */     //   410: f2d
/*     */     //   411: ldc 16777215
/*     */     //   413: iload #11
/*     */     //   415: bipush #24
/*     */     //   417: ishl
/*     */     //   418: iadd
/*     */     //   419: invokevirtual drawStringWithShadow : (Ljava/lang/String;DDI)I
/*     */     //   422: pop
/*     */     //   423: invokestatic disableAlpha : ()V
/*     */     //   426: invokestatic disableBlend : ()V
/*     */     //   429: iinc #9, 1
/*     */     //   432: iload #9
/*     */     //   434: aload_0
/*     */     //   435: getfield scrollPos : I
/*     */     //   438: iadd
/*     */     //   439: aload_0
/*     */     //   440: getfield field_146253_i : Ljava/util/List;
/*     */     //   443: invokeinterface size : ()I
/*     */     //   448: if_icmpge -> 457
/*     */     //   451: iload #9
/*     */     //   453: iload_2
/*     */     //   454: if_icmplt -> 112
/*     */     //   457: iload_3
/*     */     //   458: ifeq -> 612
/*     */     //   461: aload_0
/*     */     //   462: getfield mc : Lnet/minecraft/client/Minecraft;
/*     */     //   465: getfield fontRendererObj : Lnet/minecraft/client/gui/FontRenderer;
/*     */     //   468: getfield FONT_HEIGHT : I
/*     */     //   471: istore #9
/*     */     //   473: ldc -3.0
/*     */     //   475: fconst_0
/*     */     //   476: fconst_0
/*     */     //   477: invokestatic translate : (FFF)V
/*     */     //   480: iload #5
/*     */     //   482: iload #9
/*     */     //   484: imul
/*     */     //   485: iload #5
/*     */     //   487: iadd
/*     */     //   488: istore #12
/*     */     //   490: iload #4
/*     */     //   492: iload #9
/*     */     //   494: imul
/*     */     //   495: iload #4
/*     */     //   497: iadd
/*     */     //   498: istore #10
/*     */     //   500: aload_0
/*     */     //   501: getfield scrollPos : I
/*     */     //   504: iload #10
/*     */     //   506: imul
/*     */     //   507: iload #5
/*     */     //   509: idiv
/*     */     //   510: istore #13
/*     */     //   512: iload #10
/*     */     //   514: iload #10
/*     */     //   516: imul
/*     */     //   517: iload #12
/*     */     //   519: idiv
/*     */     //   520: istore #14
/*     */     //   522: iload #12
/*     */     //   524: iload #10
/*     */     //   526: if_icmpeq -> 612
/*     */     //   529: iload #13
/*     */     //   531: ifle -> 540
/*     */     //   534: sipush #170
/*     */     //   537: goto -> 542
/*     */     //   540: bipush #96
/*     */     //   542: istore #11
/*     */     //   544: aload_0
/*     */     //   545: getfield isScrolled : Z
/*     */     //   548: ifeq -> 556
/*     */     //   551: ldc 13382451
/*     */     //   553: goto -> 558
/*     */     //   556: ldc 3355562
/*     */     //   558: istore #15
/*     */     //   560: dconst_0
/*     */     //   561: iload #13
/*     */     //   563: ineg
/*     */     //   564: i2d
/*     */     //   565: ldc2_w 2.0
/*     */     //   568: iload #13
/*     */     //   570: ineg
/*     */     //   571: iload #14
/*     */     //   573: isub
/*     */     //   574: i2d
/*     */     //   575: iload #15
/*     */     //   577: iload #11
/*     */     //   579: bipush #24
/*     */     //   581: ishl
/*     */     //   582: iadd
/*     */     //   583: invokestatic drawRect : (DDDDI)V
/*     */     //   586: ldc2_w 2.0
/*     */     //   589: iload #13
/*     */     //   591: ineg
/*     */     //   592: i2d
/*     */     //   593: dconst_1
/*     */     //   594: iload #13
/*     */     //   596: ineg
/*     */     //   597: iload #14
/*     */     //   599: isub
/*     */     //   600: i2d
/*     */     //   601: ldc 13421772
/*     */     //   603: iload #11
/*     */     //   605: bipush #24
/*     */     //   607: ishl
/*     */     //   608: iadd
/*     */     //   609: invokestatic drawRect : (DDDDI)V
/*     */     //   612: invokestatic popMatrix : ()V
/*     */     //   615: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #42	-> 0
/*     */     //   #44	-> 16
/*     */     //   #45	-> 21
/*     */     //   #46	-> 23
/*     */     //   #47	-> 26
/*     */     //   #48	-> 37
/*     */     //   #50	-> 55
/*     */     //   #52	-> 60
/*     */     //   #54	-> 67
/*     */     //   #57	-> 69
/*     */     //   #58	-> 75
/*     */     //   #59	-> 88
/*     */     //   #60	-> 91
/*     */     //   #61	-> 98
/*     */     //   #66	-> 106
/*     */     //   #68	-> 112
/*     */     //   #70	-> 133
/*     */     //   #72	-> 138
/*     */     //   #74	-> 147
/*     */     //   #76	-> 159
/*     */     //   #77	-> 168
/*     */     //   #78	-> 174
/*     */     //   #79	-> 182
/*     */     //   #80	-> 191
/*     */     //   #81	-> 198
/*     */     //   #83	-> 207
/*     */     //   #85	-> 211
/*     */     //   #88	-> 216
/*     */     //   #89	-> 225
/*     */     //   #91	-> 228
/*     */     //   #93	-> 234
/*     */     //   #94	-> 237
/*     */     //   #95	-> 245
/*     */     //   #96	-> 284
/*     */     //   #98	-> 314
/*     */     //   #99	-> 326
/*     */     //   #100	-> 329
/*     */     //   #101	-> 353
/*     */     //   #102	-> 388
/*     */     //   #103	-> 391
/*     */     //   #105	-> 423
/*     */     //   #106	-> 426
/*     */     //   #66	-> 429
/*     */     //   #112	-> 457
/*     */     //   #114	-> 461
/*     */     //   #115	-> 473
/*     */     //   #116	-> 480
/*     */     //   #117	-> 490
/*     */     //   #118	-> 500
/*     */     //   #119	-> 512
/*     */     //   #121	-> 522
/*     */     //   #123	-> 529
/*     */     //   #124	-> 544
/*     */     //   #125	-> 560
/*     */     //   #126	-> 586
/*     */     //   #130	-> 612
/*     */     //   #133	-> 615
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	616	0	this	Lnet/minecraft/client/gui/GuiNewChat;
/*     */     //   0	616	1	p_146230_1_	I
/*     */     //   21	594	2	var2	I
/*     */     //   23	592	3	var3	Z
/*     */     //   26	589	4	var4	I
/*     */     //   37	578	5	var5	I
/*     */     //   55	560	6	var6	F
/*     */     //   75	540	7	var7	F
/*     */     //   88	527	8	var8	I
/*     */     //   109	506	9	var9	I
/*     */     //   147	282	10	var11	I
/*     */     //   500	112	10	var11	I
/*     */     //   207	222	11	var14	I
/*     */     //   544	68	11	var14	I
/*     */     //   133	296	12	var10	Lnet/minecraft/client/gui/ChatLine;
/*     */     //   168	261	13	var12	D
/*     */     //   237	192	15	var15	B
/*     */     //   245	184	16	var16	I
/*     */     //   326	103	17	var17	Ljava/lang/String;
/*     */     //   490	122	12	var18	I
/*     */     //   512	100	13	var19	I
/*     */     //   522	90	14	var13	I
/*     */     //   560	52	15	var20	I
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
/*     */   public void clearChatMessages() {
/* 140 */     this.field_146253_i.clear();
/* 141 */     this.chatLines.clear();
/* 142 */     this.sentMessages.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void printChatMessage(IChatComponent p_146227_1_) {
/* 147 */     printChatMessageWithOptionalDeletion(p_146227_1_, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printChatMessageWithOptionalDeletion(IChatComponent p_146234_1_, int p_146234_2_) {
/* 155 */     setChatLine(p_146234_1_, p_146234_2_, this.mc.ingameGUI.getUpdateCounter(), false);
/* 156 */     logger.info("[CHAT] " + p_146234_1_.getUnformattedText());
/*     */   }
/*     */ 
/*     */   
/*     */   private void setChatLine(IChatComponent p_146237_1_, int p_146237_2_, int p_146237_3_, boolean p_146237_4_) {
/* 161 */     if (p_146237_2_ != 0)
/*     */     {
/* 163 */       deleteChatLine(p_146237_2_);
/*     */     }
/*     */     
/* 166 */     int var5 = MathHelper.floor_float(getChatWidth() / getChatScale());
/* 167 */     List var6 = GuiUtilRenderComponents.func_178908_a(p_146237_1_, var5, this.mc.fontRendererObj, false, false);
/* 168 */     boolean var7 = getChatOpen();
/*     */ 
/*     */     
/* 171 */     for (Iterator<IChatComponent> var8 = var6.iterator(); var8.hasNext(); this.field_146253_i.add(0, new ChatLine(p_146237_3_, var9, p_146237_2_))) {
/*     */       
/* 173 */       IChatComponent var9 = var8.next();
/*     */       
/* 175 */       if (var7 && this.scrollPos > 0) {
/*     */         
/* 177 */         this.isScrolled = true;
/* 178 */         scroll(1);
/*     */       } 
/*     */     } 
/*     */     
/* 182 */     while (this.field_146253_i.size() > 100)
/*     */     {
/* 184 */       this.field_146253_i.remove(this.field_146253_i.size() - 1);
/*     */     }
/*     */     
/* 187 */     if (!p_146237_4_) {
/*     */       
/* 189 */       this.chatLines.add(0, new ChatLine(p_146237_3_, p_146237_1_, p_146237_2_));
/*     */       
/* 191 */       while (this.chatLines.size() > 100)
/*     */       {
/* 193 */         this.chatLines.remove(this.chatLines.size() - 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshChat() {
/* 200 */     this.field_146253_i.clear();
/* 201 */     resetScroll();
/*     */     
/* 203 */     for (int var1 = this.chatLines.size() - 1; var1 >= 0; var1--) {
/*     */       
/* 205 */       ChatLine var2 = this.chatLines.get(var1);
/* 206 */       setChatLine(var2.getChatComponent(), var2.getChatLineID(), var2.getUpdatedCounter(), true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getSentMessages() {
/* 215 */     return this.sentMessages;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToSentMessages(String p_146239_1_) {
/* 223 */     if (this.sentMessages.isEmpty() || !((String)this.sentMessages.get(this.sentMessages.size() - 1)).equals(p_146239_1_))
/*     */     {
/* 225 */       this.sentMessages.add(p_146239_1_);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetScroll() {
/* 234 */     this.scrollPos = 0;
/* 235 */     this.isScrolled = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scroll(int p_146229_1_) {
/* 243 */     this.scrollPos += p_146229_1_;
/* 244 */     int var2 = this.field_146253_i.size();
/*     */     
/* 246 */     if (this.scrollPos > var2 - getLineCount())
/*     */     {
/* 248 */       this.scrollPos = var2 - getLineCount();
/*     */     }
/*     */     
/* 251 */     if (this.scrollPos <= 0) {
/*     */       
/* 253 */       this.scrollPos = 0;
/* 254 */       this.isScrolled = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatComponent getChatComponent(int p_146236_1_, int p_146236_2_) {
/* 263 */     if (!getChatOpen())
/*     */     {
/* 265 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 269 */     ScaledResolution var3 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 270 */     int var4 = var3.getScaleFactor();
/* 271 */     float var5 = getChatScale();
/* 272 */     int var6 = p_146236_1_ / var4 - 3;
/* 273 */     int var7 = p_146236_2_ / var4 - 27;
/* 274 */     var6 = MathHelper.floor_float(var6 / var5);
/* 275 */     var7 = MathHelper.floor_float(var7 / var5);
/*     */     
/* 277 */     if (var6 >= 0 && var7 >= 0) {
/*     */       
/* 279 */       int var8 = Math.min(getLineCount(), this.field_146253_i.size());
/*     */       
/* 281 */       if (var6 <= MathHelper.floor_float(getChatWidth() / getChatScale()) && var7 < this.mc.fontRendererObj.FONT_HEIGHT * var8 + var8) {
/*     */         
/* 283 */         int var9 = var7 / this.mc.fontRendererObj.FONT_HEIGHT + this.scrollPos;
/*     */         
/* 285 */         if (var9 >= 0 && var9 < this.field_146253_i.size()) {
/*     */           
/* 287 */           ChatLine var10 = this.field_146253_i.get(var9);
/* 288 */           int var11 = 0;
/* 289 */           Iterator<IChatComponent> var12 = var10.getChatComponent().iterator();
/*     */           
/* 291 */           while (var12.hasNext()) {
/*     */             
/* 293 */             IChatComponent var13 = var12.next();
/*     */             
/* 295 */             if (var13 instanceof ChatComponentText) {
/*     */               
/* 297 */               var11 += this.mc.fontRendererObj.getStringWidth(GuiUtilRenderComponents.func_178909_a(((ChatComponentText)var13).getChatComponentText_TextValue(), false));
/*     */               
/* 299 */               if (var11 > var6)
/*     */               {
/* 301 */                 return var13;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 307 */         return null;
/*     */       } 
/*     */ 
/*     */       
/* 311 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 316 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getChatOpen() {
/* 326 */     return this.mc.currentScreen instanceof GuiChat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteChatLine(int p_146242_1_) {
/* 334 */     Iterator<ChatLine> var2 = this.field_146253_i.iterator();
/*     */ 
/*     */     
/* 337 */     while (var2.hasNext()) {
/*     */       
/* 339 */       ChatLine var3 = var2.next();
/*     */       
/* 341 */       if (var3.getChatLineID() == p_146242_1_)
/*     */       {
/* 343 */         var2.remove();
/*     */       }
/*     */     } 
/*     */     
/* 347 */     var2 = this.chatLines.iterator();
/*     */     
/* 349 */     while (var2.hasNext()) {
/*     */       
/* 351 */       ChatLine var3 = var2.next();
/*     */       
/* 353 */       if (var3.getChatLineID() == p_146242_1_) {
/*     */         
/* 355 */         var2.remove();
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChatWidth() {
/* 363 */     return calculateChatboxWidth(this.mc.gameSettings.chatWidth);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChatHeight() {
/* 368 */     return calculateChatboxHeight(getChatOpen() ? this.mc.gameSettings.chatHeightFocused : this.mc.gameSettings.chatHeightUnfocused);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getChatScale() {
/* 376 */     return this.mc.gameSettings.chatScale;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int calculateChatboxWidth(float p_146233_0_) {
/* 381 */     short var1 = 320;
/* 382 */     byte var2 = 40;
/* 383 */     return MathHelper.floor_float(p_146233_0_ * (var1 - var2) + var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int calculateChatboxHeight(float p_146243_0_) {
/* 388 */     short var1 = 180;
/* 389 */     byte var2 = 20;
/* 390 */     return MathHelper.floor_float(p_146243_0_ * (var1 - var2) + var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLineCount() {
/* 395 */     return getChatHeight() / 9;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiNewChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */