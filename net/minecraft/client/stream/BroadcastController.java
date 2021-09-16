/*      */ package net.minecraft.client.stream;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import net.minecraft.crash.CrashReport;
/*      */ import net.minecraft.crash.CrashReportCategory;
/*      */ import net.minecraft.util.ReportedException;
/*      */ import net.minecraft.util.ThreadSafeBoundList;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import tv.twitch.AuthToken;
/*      */ import tv.twitch.Core;
/*      */ import tv.twitch.CoreAPI;
/*      */ import tv.twitch.ErrorCode;
/*      */ import tv.twitch.MessageLevel;
/*      */ import tv.twitch.StandardCoreAPI;
/*      */ import tv.twitch.broadcast.ArchivingState;
/*      */ import tv.twitch.broadcast.AudioDeviceType;
/*      */ import tv.twitch.broadcast.AudioParams;
/*      */ import tv.twitch.broadcast.ChannelInfo;
/*      */ import tv.twitch.broadcast.DesktopStreamAPI;
/*      */ import tv.twitch.broadcast.EncodingCpuUsage;
/*      */ import tv.twitch.broadcast.FrameBuffer;
/*      */ import tv.twitch.broadcast.GameInfo;
/*      */ import tv.twitch.broadcast.GameInfoList;
/*      */ import tv.twitch.broadcast.IStatCallbacks;
/*      */ import tv.twitch.broadcast.IStreamCallbacks;
/*      */ import tv.twitch.broadcast.IngestList;
/*      */ import tv.twitch.broadcast.IngestServer;
/*      */ import tv.twitch.broadcast.PixelFormat;
/*      */ import tv.twitch.broadcast.StartFlags;
/*      */ import tv.twitch.broadcast.StatType;
/*      */ import tv.twitch.broadcast.Stream;
/*      */ import tv.twitch.broadcast.StreamAPI;
/*      */ import tv.twitch.broadcast.StreamInfo;
/*      */ import tv.twitch.broadcast.StreamInfoForSetting;
/*      */ import tv.twitch.broadcast.UserInfo;
/*      */ import tv.twitch.broadcast.VideoParams;
/*      */ 
/*      */ public class BroadcastController {
/*   41 */   private static final Logger logger = LogManager.getLogger();
/*   42 */   protected final int field_152865_a = 30;
/*   43 */   protected final int field_152866_b = 3;
/*   44 */   private static final ThreadSafeBoundList field_152862_C = new ThreadSafeBoundList(String.class, 50);
/*   45 */   private String field_152863_D = null;
/*   46 */   protected BroadcastListener field_152867_c = null;
/*   47 */   protected String field_152868_d = "";
/*   48 */   protected String field_152869_e = "";
/*   49 */   protected String field_152870_f = "";
/*      */   protected boolean field_152871_g = true;
/*   51 */   protected Core field_152872_h = null;
/*   52 */   protected Stream field_152873_i = null;
/*   53 */   protected List field_152874_j = Lists.newArrayList();
/*   54 */   protected List field_152875_k = Lists.newArrayList();
/*      */   
/*      */   protected boolean field_152876_l = false;
/*      */   protected boolean field_152877_m = false;
/*      */   protected boolean field_152878_n = false;
/*      */   protected BroadcastState broadcastState;
/*      */   protected String field_152880_p;
/*      */   protected VideoParams field_152881_q;
/*      */   protected AudioParams field_152882_r;
/*      */   protected IngestList field_152883_s;
/*      */   protected IngestServer field_152884_t;
/*      */   protected AuthToken field_152885_u;
/*      */   protected ChannelInfo channelInfo;
/*      */   protected UserInfo field_152887_w;
/*      */   protected StreamInfo field_152888_x;
/*      */   protected ArchivingState field_152889_y;
/*      */   protected long field_152890_z;
/*      */   protected IngestServerTester field_152860_A;
/*      */   private ErrorCode field_152864_E;
/*      */   protected IStreamCallbacks field_177948_B;
/*      */   protected IStatCallbacks field_177949_C;
/*      */   private static final String __OBFID = "CL_00001822";
/*      */   
/*      */   public void func_152841_a(BroadcastListener p_152841_1_) {
/*   78 */     this.field_152867_c = p_152841_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_152858_b() {
/*   83 */     return this.field_152876_l;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152842_a(String p_152842_1_) {
/*   88 */     this.field_152868_d = p_152842_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public StreamInfo func_152816_j() {
/*   93 */     return this.field_152888_x;
/*      */   }
/*      */ 
/*      */   
/*      */   public ChannelInfo func_152843_l() {
/*   98 */     return this.channelInfo;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBroadcasting() {
/*  103 */     return !(this.broadcastState != BroadcastState.Broadcasting && this.broadcastState != BroadcastState.Paused);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_152857_n() {
/*  108 */     return (this.broadcastState == BroadcastState.ReadyToBroadcast);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isIngestTesting() {
/*  113 */     return (this.broadcastState == BroadcastState.IngestTesting);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBroadcastPaused() {
/*  118 */     return (this.broadcastState == BroadcastState.Paused);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_152849_q() {
/*  123 */     return this.field_152877_m;
/*      */   }
/*      */ 
/*      */   
/*      */   public IngestServer func_152833_s() {
/*  128 */     return this.field_152884_t;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152824_a(IngestServer p_152824_1_) {
/*  133 */     this.field_152884_t = p_152824_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public IngestList func_152855_t() {
/*  138 */     return this.field_152883_s;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152829_a(float p_152829_1_) {
/*  143 */     this.field_152873_i.setVolume(AudioDeviceType.TTV_RECORDER_DEVICE, p_152829_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152837_b(float p_152837_1_) {
/*  148 */     this.field_152873_i.setVolume(AudioDeviceType.TTV_PLAYBACK_DEVICE, p_152837_1_);
/*      */   }
/*      */ 
/*      */   
/*      */   public IngestServerTester isReady() {
/*  153 */     return this.field_152860_A;
/*      */   }
/*      */ 
/*      */   
/*      */   public long func_152844_x() {
/*  158 */     return this.field_152873_i.getStreamTime();
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean func_152848_y() {
/*  163 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public ErrorCode func_152852_P() {
/*  168 */     return this.field_152864_E;
/*      */   }
/*      */ 
/*      */   
/*      */   public BroadcastController() {
/*  173 */     this.broadcastState = BroadcastState.Uninitialized;
/*  174 */     this.field_152880_p = null;
/*  175 */     this.field_152881_q = null;
/*  176 */     this.field_152882_r = null;
/*  177 */     this.field_152883_s = new IngestList(new IngestServer[0]);
/*  178 */     this.field_152884_t = null;
/*  179 */     this.field_152885_u = new AuthToken();
/*  180 */     this.channelInfo = new ChannelInfo();
/*  181 */     this.field_152887_w = new UserInfo();
/*  182 */     this.field_152888_x = new StreamInfo();
/*  183 */     this.field_152889_y = new ArchivingState();
/*  184 */     this.field_152890_z = 0L;
/*  185 */     this.field_152860_A = null;
/*  186 */     this.field_177948_B = new IStreamCallbacks()
/*      */       {
/*      */         private static final String __OBFID = "CL_00002375";
/*      */         
/*      */         public void requestAuthTokenCallback(ErrorCode p_requestAuthTokenCallback_1_, AuthToken p_requestAuthTokenCallback_2_) {
/*  191 */           if (ErrorCode.succeeded(p_requestAuthTokenCallback_1_)) {
/*      */             
/*  193 */             BroadcastController.this.field_152885_u = p_requestAuthTokenCallback_2_;
/*  194 */             BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Authenticated);
/*      */           }
/*      */           else {
/*      */             
/*  198 */             BroadcastController.this.field_152885_u.data = "";
/*  199 */             BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Initialized);
/*  200 */             String var3 = ErrorCode.getString(p_requestAuthTokenCallback_1_);
/*  201 */             BroadcastController.this.func_152820_d(String.format("RequestAuthTokenDoneCallback got failure: %s", new Object[] { var3 }));
/*      */           } 
/*      */ 
/*      */           
/*      */           try {
/*  206 */             if (BroadcastController.this.field_152867_c != null)
/*      */             {
/*  208 */               BroadcastController.this.field_152867_c.func_152900_a(p_requestAuthTokenCallback_1_, p_requestAuthTokenCallback_2_);
/*      */             }
/*      */           }
/*  211 */           catch (Exception var4) {
/*      */             
/*  213 */             BroadcastController.this.func_152820_d(var4.toString());
/*      */           } 
/*      */         }
/*      */         
/*      */         public void loginCallback(ErrorCode p_loginCallback_1_, ChannelInfo p_loginCallback_2_) {
/*  218 */           if (ErrorCode.succeeded(p_loginCallback_1_)) {
/*      */             
/*  220 */             BroadcastController.this.channelInfo = p_loginCallback_2_;
/*  221 */             BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.LoggedIn);
/*  222 */             BroadcastController.this.field_152877_m = true;
/*      */           }
/*      */           else {
/*      */             
/*  226 */             BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Initialized);
/*  227 */             BroadcastController.this.field_152877_m = false;
/*  228 */             String var3 = ErrorCode.getString(p_loginCallback_1_);
/*  229 */             BroadcastController.this.func_152820_d(String.format("LoginCallback got failure: %s", new Object[] { var3 }));
/*      */           } 
/*      */ 
/*      */           
/*      */           try {
/*  234 */             if (BroadcastController.this.field_152867_c != null)
/*      */             {
/*  236 */               BroadcastController.this.field_152867_c.func_152897_a(p_loginCallback_1_);
/*      */             }
/*      */           }
/*  239 */           catch (Exception var4) {
/*      */             
/*  241 */             BroadcastController.this.func_152820_d(var4.toString());
/*      */           } 
/*      */         }
/*      */         
/*      */         public void getIngestServersCallback(ErrorCode p_getIngestServersCallback_1_, IngestList p_getIngestServersCallback_2_) {
/*  246 */           if (ErrorCode.succeeded(p_getIngestServersCallback_1_)) {
/*      */             
/*  248 */             BroadcastController.this.field_152883_s = p_getIngestServersCallback_2_;
/*  249 */             BroadcastController.this.field_152884_t = BroadcastController.this.field_152883_s.getDefaultServer();
/*  250 */             BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.ReceivedIngestServers);
/*      */ 
/*      */             
/*      */             try {
/*  254 */               if (BroadcastController.this.field_152867_c != null)
/*      */               {
/*  256 */                 BroadcastController.this.field_152867_c.func_152896_a(p_getIngestServersCallback_2_);
/*      */               }
/*      */             }
/*  259 */             catch (Exception var4) {
/*      */               
/*  261 */               BroadcastController.this.func_152820_d(var4.toString());
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/*  266 */             String var3 = ErrorCode.getString(p_getIngestServersCallback_1_);
/*  267 */             BroadcastController.this.func_152820_d(String.format("IngestListCallback got failure: %s", new Object[] { var3 }));
/*  268 */             BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.LoggingIn);
/*      */           } 
/*      */         }
/*      */         
/*      */         public void getUserInfoCallback(ErrorCode p_getUserInfoCallback_1_, UserInfo p_getUserInfoCallback_2_) {
/*  273 */           BroadcastController.this.field_152887_w = p_getUserInfoCallback_2_;
/*      */           
/*  275 */           if (ErrorCode.failed(p_getUserInfoCallback_1_)) {
/*      */             
/*  277 */             String var3 = ErrorCode.getString(p_getUserInfoCallback_1_);
/*  278 */             BroadcastController.this.func_152820_d(String.format("UserInfoDoneCallback got failure: %s", new Object[] { var3 }));
/*      */           } 
/*      */         }
/*      */         
/*      */         public void getStreamInfoCallback(ErrorCode p_getStreamInfoCallback_1_, StreamInfo p_getStreamInfoCallback_2_) {
/*  283 */           if (ErrorCode.succeeded(p_getStreamInfoCallback_1_)) {
/*      */             
/*  285 */             BroadcastController.this.field_152888_x = p_getStreamInfoCallback_2_;
/*      */ 
/*      */             
/*      */             try {
/*  289 */               if (BroadcastController.this.field_152867_c != null)
/*      */               {
/*  291 */                 BroadcastController.this.field_152867_c.func_152894_a(p_getStreamInfoCallback_2_);
/*      */               }
/*      */             }
/*  294 */             catch (Exception var4) {
/*      */               
/*  296 */               BroadcastController.this.func_152820_d(var4.toString());
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/*  301 */             String var3 = ErrorCode.getString(p_getStreamInfoCallback_1_);
/*  302 */             BroadcastController.this.func_152832_e(String.format("StreamInfoDoneCallback got failure: %s", new Object[] { var3 }));
/*      */           } 
/*      */         }
/*      */         
/*      */         public void getArchivingStateCallback(ErrorCode p_getArchivingStateCallback_1_, ArchivingState p_getArchivingStateCallback_2_) {
/*  307 */           BroadcastController.this.field_152889_y = p_getArchivingStateCallback_2_;
/*      */           
/*  309 */           if (ErrorCode.failed(p_getArchivingStateCallback_1_));
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         public void runCommercialCallback(ErrorCode p_runCommercialCallback_1_) {
/*  316 */           if (ErrorCode.failed(p_runCommercialCallback_1_)) {
/*      */             
/*  318 */             String var2 = ErrorCode.getString(p_runCommercialCallback_1_);
/*  319 */             BroadcastController.this.func_152832_e(String.format("RunCommercialCallback got failure: %s", new Object[] { var2 }));
/*      */           } 
/*      */         }
/*      */         
/*      */         public void setStreamInfoCallback(ErrorCode p_setStreamInfoCallback_1_) {
/*  324 */           if (ErrorCode.failed(p_setStreamInfoCallback_1_)) {
/*      */             
/*  326 */             String var2 = ErrorCode.getString(p_setStreamInfoCallback_1_);
/*  327 */             BroadcastController.this.func_152832_e(String.format("SetStreamInfoCallback got failure: %s", new Object[] { var2 }));
/*      */           } 
/*      */         }
/*      */         
/*      */         public void getGameNameListCallback(ErrorCode p_getGameNameListCallback_1_, GameInfoList p_getGameNameListCallback_2_) {
/*  332 */           if (ErrorCode.failed(p_getGameNameListCallback_1_)) {
/*      */             
/*  334 */             String var3 = ErrorCode.getString(p_getGameNameListCallback_1_);
/*  335 */             BroadcastController.this.func_152820_d(String.format("GameNameListCallback got failure: %s", new Object[] { var3 }));
/*      */           } 
/*      */ 
/*      */           
/*      */           try {
/*  340 */             if (BroadcastController.this.field_152867_c != null)
/*      */             {
/*  342 */               BroadcastController.this.field_152867_c.func_152898_a(p_getGameNameListCallback_1_, (p_getGameNameListCallback_2_ == null) ? new GameInfo[0] : p_getGameNameListCallback_2_.list);
/*      */             }
/*      */           }
/*  345 */           catch (Exception var4) {
/*      */             
/*  347 */             BroadcastController.this.func_152820_d(var4.toString());
/*      */           } 
/*      */         }
/*      */         
/*      */         public void bufferUnlockCallback(long p_bufferUnlockCallback_1_) {
/*  352 */           FrameBuffer var3 = FrameBuffer.lookupBuffer(p_bufferUnlockCallback_1_);
/*  353 */           BroadcastController.this.field_152875_k.add(var3);
/*      */         }
/*      */         
/*      */         public void startCallback(ErrorCode p_startCallback_1_) {
/*  357 */           if (ErrorCode.succeeded(p_startCallback_1_)) {
/*      */ 
/*      */             
/*      */             try {
/*  361 */               if (BroadcastController.this.field_152867_c != null)
/*      */               {
/*  363 */                 BroadcastController.this.field_152867_c.func_152899_b();
/*      */               }
/*      */             }
/*  366 */             catch (Exception var4) {
/*      */               
/*  368 */               BroadcastController.this.func_152820_d(var4.toString());
/*      */             } 
/*      */             
/*  371 */             BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Broadcasting);
/*      */           }
/*      */           else {
/*      */             
/*  375 */             BroadcastController.this.field_152881_q = null;
/*  376 */             BroadcastController.this.field_152882_r = null;
/*  377 */             BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.ReadyToBroadcast);
/*      */ 
/*      */             
/*      */             try {
/*  381 */               if (BroadcastController.this.field_152867_c != null)
/*      */               {
/*  383 */                 BroadcastController.this.field_152867_c.func_152892_c(p_startCallback_1_);
/*      */               }
/*      */             }
/*  386 */             catch (Exception var3) {
/*      */               
/*  388 */               BroadcastController.this.func_152820_d(var3.toString());
/*      */             } 
/*      */             
/*  391 */             String var2 = ErrorCode.getString(p_startCallback_1_);
/*  392 */             BroadcastController.this.func_152820_d(String.format("startCallback got failure: %s", new Object[] { var2 }));
/*      */           } 
/*      */         }
/*      */         
/*      */         public void stopCallback(ErrorCode p_stopCallback_1_) {
/*  397 */           if (ErrorCode.succeeded(p_stopCallback_1_)) {
/*      */             
/*  399 */             BroadcastController.this.field_152881_q = null;
/*  400 */             BroadcastController.this.field_152882_r = null;
/*  401 */             BroadcastController.this.func_152831_M();
/*      */ 
/*      */             
/*      */             try {
/*  405 */               if (BroadcastController.this.field_152867_c != null)
/*      */               {
/*  407 */                 BroadcastController.this.field_152867_c.func_152901_c();
/*      */               }
/*      */             }
/*  410 */             catch (Exception var3) {
/*      */               
/*  412 */               BroadcastController.this.func_152820_d(var3.toString());
/*      */             } 
/*      */             
/*  415 */             if (BroadcastController.this.field_152877_m)
/*      */             {
/*  417 */               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.ReadyToBroadcast);
/*      */             }
/*      */             else
/*      */             {
/*  421 */               BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.Initialized);
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/*  426 */             BroadcastController.this.func_152827_a(BroadcastController.BroadcastState.ReadyToBroadcast);
/*  427 */             String var2 = ErrorCode.getString(p_stopCallback_1_);
/*  428 */             BroadcastController.this.func_152820_d(String.format("stopCallback got failure: %s", new Object[] { var2 }));
/*      */           } 
/*      */         }
/*      */         
/*      */         public void sendActionMetaDataCallback(ErrorCode p_sendActionMetaDataCallback_1_) {
/*  433 */           if (ErrorCode.failed(p_sendActionMetaDataCallback_1_)) {
/*      */             
/*  435 */             String var2 = ErrorCode.getString(p_sendActionMetaDataCallback_1_);
/*  436 */             BroadcastController.this.func_152820_d(String.format("sendActionMetaDataCallback got failure: %s", new Object[] { var2 }));
/*      */           } 
/*      */         }
/*      */         
/*      */         public void sendStartSpanMetaDataCallback(ErrorCode p_sendStartSpanMetaDataCallback_1_) {
/*  441 */           if (ErrorCode.failed(p_sendStartSpanMetaDataCallback_1_)) {
/*      */             
/*  443 */             String var2 = ErrorCode.getString(p_sendStartSpanMetaDataCallback_1_);
/*  444 */             BroadcastController.this.func_152820_d(String.format("sendStartSpanMetaDataCallback got failure: %s", new Object[] { var2 }));
/*      */           } 
/*      */         }
/*      */         
/*      */         public void sendEndSpanMetaDataCallback(ErrorCode p_sendEndSpanMetaDataCallback_1_) {
/*  449 */           if (ErrorCode.failed(p_sendEndSpanMetaDataCallback_1_)) {
/*      */             
/*  451 */             String var2 = ErrorCode.getString(p_sendEndSpanMetaDataCallback_1_);
/*  452 */             BroadcastController.this.func_152820_d(String.format("sendEndSpanMetaDataCallback got failure: %s", new Object[] { var2 }));
/*      */           } 
/*      */         }
/*      */       };
/*  456 */     this.field_177949_C = new IStatCallbacks() {
/*      */         private static final String __OBFID = "CL_00002374";
/*      */         
/*      */         public void statCallback(StatType p_statCallback_1_, long p_statCallback_2_) {}
/*      */       };
/*  461 */     this.field_152872_h = Core.getInstance();
/*      */     
/*  463 */     if (Core.getInstance() == null)
/*      */     {
/*  465 */       this.field_152872_h = new Core((CoreAPI)new StandardCoreAPI());
/*      */     }
/*      */     
/*  468 */     this.field_152873_i = new Stream((StreamAPI)new DesktopStreamAPI());
/*      */   }
/*      */ 
/*      */   
/*      */   protected PixelFormat func_152826_z() {
/*  473 */     return PixelFormat.TTV_PF_RGBA;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_152817_A() {
/*  478 */     if (this.field_152876_l)
/*      */     {
/*  480 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  484 */     this.field_152873_i.setStreamCallbacks(this.field_177948_B);
/*  485 */     ErrorCode var1 = this.field_152872_h.initialize(this.field_152868_d, System.getProperty("java.library.path"));
/*      */     
/*  487 */     if (!func_152853_a(var1)) {
/*      */       
/*  489 */       this.field_152873_i.setStreamCallbacks(null);
/*  490 */       this.field_152864_E = var1;
/*  491 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  495 */     var1 = this.field_152872_h.setTraceLevel(MessageLevel.TTV_ML_ERROR);
/*      */     
/*  497 */     if (!func_152853_a(var1)) {
/*      */       
/*  499 */       this.field_152873_i.setStreamCallbacks(null);
/*  500 */       this.field_152872_h.shutdown();
/*  501 */       this.field_152864_E = var1;
/*  502 */       return false;
/*      */     } 
/*  504 */     if (ErrorCode.succeeded(var1)) {
/*      */       
/*  506 */       this.field_152876_l = true;
/*  507 */       func_152827_a(BroadcastState.Initialized);
/*  508 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  512 */     this.field_152864_E = var1;
/*  513 */     this.field_152872_h.shutdown();
/*  514 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152851_B() {
/*  522 */     if (!this.field_152876_l)
/*      */     {
/*  524 */       return true;
/*      */     }
/*  526 */     if (isIngestTesting())
/*      */     {
/*  528 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  532 */     this.field_152878_n = true;
/*  533 */     func_152845_C();
/*  534 */     this.field_152873_i.setStreamCallbacks(null);
/*  535 */     this.field_152873_i.setStatCallbacks(null);
/*  536 */     ErrorCode var1 = this.field_152872_h.shutdown();
/*  537 */     func_152853_a(var1);
/*  538 */     this.field_152876_l = false;
/*  539 */     this.field_152878_n = false;
/*  540 */     func_152827_a(BroadcastState.Uninitialized);
/*  541 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void statCallback() {
/*  547 */     if (this.broadcastState != BroadcastState.Uninitialized) {
/*      */       
/*  549 */       if (this.field_152860_A != null)
/*      */       {
/*  551 */         this.field_152860_A.func_153039_l();
/*      */       }
/*      */       
/*  554 */       for (; this.field_152860_A != null; func_152821_H()) {
/*      */ 
/*      */         
/*      */         try {
/*  558 */           Thread.sleep(200L);
/*      */         }
/*  560 */         catch (Exception var2) {
/*      */           
/*  562 */           func_152820_d(var2.toString());
/*      */         } 
/*      */       } 
/*      */       
/*  566 */       func_152851_B();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_152818_a(String p_152818_1_, AuthToken p_152818_2_) {
/*  572 */     if (isIngestTesting())
/*      */     {
/*  574 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  578 */     func_152845_C();
/*      */     
/*  580 */     if (p_152818_1_ != null && !p_152818_1_.isEmpty()) {
/*      */       
/*  582 */       if (p_152818_2_ != null && p_152818_2_.data != null && !p_152818_2_.data.isEmpty()) {
/*      */         
/*  584 */         this.field_152880_p = p_152818_1_;
/*  585 */         this.field_152885_u = p_152818_2_;
/*      */         
/*  587 */         if (func_152858_b())
/*      */         {
/*  589 */           func_152827_a(BroadcastState.Authenticated);
/*      */         }
/*      */         
/*  592 */         return true;
/*      */       } 
/*      */ 
/*      */       
/*  596 */       func_152820_d("Auth token must be valid");
/*  597 */       return false;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  602 */     func_152820_d("Username must be valid");
/*  603 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152845_C() {
/*  610 */     if (isIngestTesting())
/*      */     {
/*  612 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  616 */     if (isBroadcasting())
/*      */     {
/*  618 */       this.field_152873_i.stop(false);
/*      */     }
/*      */     
/*  621 */     this.field_152880_p = "";
/*  622 */     this.field_152885_u = new AuthToken();
/*      */     
/*  624 */     if (!this.field_152877_m)
/*      */     {
/*  626 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  630 */     this.field_152877_m = false;
/*      */     
/*  632 */     if (!this.field_152878_n) {
/*      */       
/*      */       try {
/*      */         
/*  636 */         if (this.field_152867_c != null)
/*      */         {
/*  638 */           this.field_152867_c.func_152895_a();
/*      */         }
/*      */       }
/*  641 */       catch (Exception var2) {
/*      */         
/*  643 */         func_152820_d(var2.toString());
/*      */       } 
/*      */     }
/*      */     
/*  647 */     func_152827_a(BroadcastState.Initialized);
/*  648 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152828_a(String p_152828_1_, String p_152828_2_, String p_152828_3_) {
/*  655 */     if (!this.field_152877_m)
/*      */     {
/*  657 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  661 */     if (p_152828_1_ == null || p_152828_1_.equals(""))
/*      */     {
/*  663 */       p_152828_1_ = this.field_152880_p;
/*      */     }
/*      */     
/*  666 */     if (p_152828_2_ == null)
/*      */     {
/*  668 */       p_152828_2_ = "";
/*      */     }
/*      */     
/*  671 */     if (p_152828_3_ == null)
/*      */     {
/*  673 */       p_152828_3_ = "";
/*      */     }
/*      */     
/*  676 */     StreamInfoForSetting var4 = new StreamInfoForSetting();
/*  677 */     var4.streamTitle = p_152828_3_;
/*  678 */     var4.gameName = p_152828_2_;
/*  679 */     ErrorCode var5 = this.field_152873_i.setStreamInfo(this.field_152885_u, p_152828_1_, var4);
/*  680 */     func_152853_a(var5);
/*  681 */     return ErrorCode.succeeded(var5);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152830_D() {
/*  687 */     if (!isBroadcasting())
/*      */     {
/*  689 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  693 */     ErrorCode var1 = this.field_152873_i.runCommercial(this.field_152885_u);
/*  694 */     func_152853_a(var1);
/*  695 */     return ErrorCode.succeeded(var1);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public VideoParams func_152834_a(int p_152834_1_, int p_152834_2_, float p_152834_3_, float p_152834_4_) {
/*  701 */     int[] var5 = this.field_152873_i.getMaxResolution(p_152834_1_, p_152834_2_, p_152834_3_, p_152834_4_);
/*  702 */     VideoParams var6 = new VideoParams();
/*  703 */     var6.maxKbps = p_152834_1_;
/*  704 */     var6.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
/*  705 */     var6.pixelFormat = func_152826_z();
/*  706 */     var6.targetFps = p_152834_2_;
/*  707 */     var6.outputWidth = var5[0];
/*  708 */     var6.outputHeight = var5[1];
/*  709 */     var6.disableAdaptiveBitrate = false;
/*  710 */     var6.verticalFlip = false;
/*  711 */     return var6;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_152836_a(VideoParams p_152836_1_) {
/*  716 */     if (p_152836_1_ != null && func_152857_n()) {
/*      */       
/*  718 */       this.field_152881_q = p_152836_1_.clone();
/*  719 */       this.field_152882_r = new AudioParams();
/*  720 */       this.field_152882_r.audioEnabled = (this.field_152871_g && func_152848_y());
/*  721 */       this.field_152882_r.enableMicCapture = this.field_152882_r.audioEnabled;
/*  722 */       this.field_152882_r.enablePlaybackCapture = this.field_152882_r.audioEnabled;
/*  723 */       this.field_152882_r.enablePassthroughAudio = false;
/*      */       
/*  725 */       if (!func_152823_L()) {
/*      */         
/*  727 */         this.field_152881_q = null;
/*  728 */         this.field_152882_r = null;
/*  729 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  733 */       ErrorCode var2 = this.field_152873_i.start(p_152836_1_, this.field_152882_r, this.field_152884_t, StartFlags.None, true);
/*      */       
/*  735 */       if (ErrorCode.failed(var2)) {
/*      */         
/*  737 */         func_152831_M();
/*  738 */         String var3 = ErrorCode.getString(var2);
/*  739 */         func_152820_d(String.format("Error while starting to broadcast: %s", new Object[] { var3 }));
/*  740 */         this.field_152881_q = null;
/*  741 */         this.field_152882_r = null;
/*  742 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  746 */       func_152827_a(BroadcastState.Starting);
/*  747 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  753 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152819_E() {
/*  759 */     if (!isBroadcasting())
/*      */     {
/*  761 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  765 */     ErrorCode var1 = this.field_152873_i.stop(true);
/*      */     
/*  767 */     if (ErrorCode.failed(var1)) {
/*      */       
/*  769 */       String var2 = ErrorCode.getString(var1);
/*  770 */       func_152820_d(String.format("Error while stopping the broadcast: %s", new Object[] { var2 }));
/*  771 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  775 */     func_152827_a(BroadcastState.Stopping);
/*  776 */     return ErrorCode.succeeded(var1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152847_F() {
/*  783 */     if (!isBroadcasting())
/*      */     {
/*  785 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  789 */     ErrorCode var1 = this.field_152873_i.pauseVideo();
/*      */     
/*  791 */     if (ErrorCode.failed(var1)) {
/*      */       
/*  793 */       func_152819_E();
/*  794 */       String var2 = ErrorCode.getString(var1);
/*  795 */       func_152820_d(String.format("Error pausing stream: %s\n", new Object[] { var2 }));
/*      */     }
/*      */     else {
/*      */       
/*  799 */       func_152827_a(BroadcastState.Paused);
/*      */     } 
/*      */     
/*  802 */     return ErrorCode.succeeded(var1);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152854_G() {
/*  808 */     if (!isBroadcastPaused())
/*      */     {
/*  810 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  814 */     func_152827_a(BroadcastState.Broadcasting);
/*  815 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152840_a(String p_152840_1_, long p_152840_2_, String p_152840_4_, String p_152840_5_) {
/*  821 */     ErrorCode var6 = this.field_152873_i.sendActionMetaData(this.field_152885_u, p_152840_1_, p_152840_2_, p_152840_4_, p_152840_5_);
/*      */     
/*  823 */     if (ErrorCode.failed(var6)) {
/*      */       
/*  825 */       String var7 = ErrorCode.getString(var6);
/*  826 */       func_152820_d(String.format("Error while sending meta data: %s\n", new Object[] { var7 }));
/*  827 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  831 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public long func_177946_b(String p_177946_1_, long p_177946_2_, String p_177946_4_, String p_177946_5_) {
/*  837 */     long var6 = this.field_152873_i.sendStartSpanMetaData(this.field_152885_u, p_177946_1_, p_177946_2_, p_177946_4_, p_177946_5_);
/*      */     
/*  839 */     if (var6 == -1L)
/*      */     {
/*  841 */       func_152820_d(String.format("Error in SendStartSpanMetaData\n", new Object[0]));
/*      */     }
/*      */     
/*  844 */     return var6;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_177947_a(String p_177947_1_, long p_177947_2_, long p_177947_4_, String p_177947_6_, String p_177947_7_) {
/*  849 */     if (p_177947_4_ == -1L) {
/*      */       
/*  851 */       func_152820_d(String.format("Invalid sequence id: %d\n", new Object[] { Long.valueOf(p_177947_4_) }));
/*  852 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  856 */     ErrorCode var8 = this.field_152873_i.sendEndSpanMetaData(this.field_152885_u, p_177947_1_, p_177947_2_, p_177947_4_, p_177947_6_, p_177947_7_);
/*      */     
/*  858 */     if (ErrorCode.failed(var8)) {
/*      */       
/*  860 */       String var9 = ErrorCode.getString(var8);
/*  861 */       func_152820_d(String.format("Error in SendStopSpanMetaData: %s\n", new Object[] { var9 }));
/*  862 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  866 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_152827_a(BroadcastState p_152827_1_) {
/*  873 */     if (p_152827_1_ != this.broadcastState) {
/*      */       
/*  875 */       this.broadcastState = p_152827_1_;
/*      */ 
/*      */       
/*      */       try {
/*  879 */         if (this.field_152867_c != null)
/*      */         {
/*  881 */           this.field_152867_c.func_152891_a(p_152827_1_);
/*      */         }
/*      */       }
/*  884 */       catch (Exception var3) {
/*      */         
/*  886 */         func_152820_d(var3.toString());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152821_H() {
/*  893 */     if (this.field_152873_i != null && this.field_152876_l) {
/*      */       
/*  895 */       ErrorCode var1 = this.field_152873_i.pollTasks();
/*  896 */       func_152853_a(var1);
/*      */       
/*  898 */       if (isIngestTesting()) {
/*      */         
/*  900 */         this.field_152860_A.func_153041_j();
/*      */         
/*  902 */         if (this.field_152860_A.func_153032_e()) {
/*      */           
/*  904 */           this.field_152860_A = null;
/*  905 */           func_152827_a(BroadcastState.ReadyToBroadcast);
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  911 */       switch (SwitchBroadcastState.field_177773_a[this.broadcastState.ordinal()]) {
/*      */         
/*      */         case 1:
/*  914 */           func_152827_a(BroadcastState.LoggingIn);
/*  915 */           var1 = this.field_152873_i.login(this.field_152885_u);
/*      */           
/*  917 */           if (ErrorCode.failed(var1)) {
/*      */             
/*  919 */             String var2 = ErrorCode.getString(var1);
/*  920 */             func_152820_d(String.format("Error in TTV_Login: %s\n", new Object[] { var2 }));
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         case 2:
/*  926 */           func_152827_a(BroadcastState.FindingIngestServer);
/*  927 */           var1 = this.field_152873_i.getIngestServers(this.field_152885_u);
/*      */           
/*  929 */           if (ErrorCode.failed(var1)) {
/*      */             
/*  931 */             func_152827_a(BroadcastState.LoggedIn);
/*  932 */             String var2 = ErrorCode.getString(var1);
/*  933 */             func_152820_d(String.format("Error in TTV_GetIngestServers: %s\n", new Object[] { var2 }));
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         case 3:
/*  939 */           func_152827_a(BroadcastState.ReadyToBroadcast);
/*  940 */           var1 = this.field_152873_i.getUserInfo(this.field_152885_u);
/*      */           
/*  942 */           if (ErrorCode.failed(var1)) {
/*      */             
/*  944 */             String var2 = ErrorCode.getString(var1);
/*  945 */             func_152820_d(String.format("Error in TTV_GetUserInfo: %s\n", new Object[] { var2 }));
/*      */           } 
/*      */           
/*  948 */           func_152835_I();
/*  949 */           var1 = this.field_152873_i.getArchivingState(this.field_152885_u);
/*      */           
/*  951 */           if (ErrorCode.failed(var1)) {
/*      */             
/*  953 */             String var2 = ErrorCode.getString(var1);
/*  954 */             func_152820_d(String.format("Error in TTV_GetArchivingState: %s\n", new Object[] { var2 }));
/*      */           } 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/*      */           return;
/*      */ 
/*      */         
/*      */         case 11:
/*      */         case 12:
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/*  969 */       func_152835_I();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_152835_I() {
/*  976 */     long var1 = System.nanoTime();
/*  977 */     long var3 = (var1 - this.field_152890_z) / 1000000000L;
/*      */     
/*  979 */     if (var3 >= 30L) {
/*      */       
/*  981 */       this.field_152890_z = var1;
/*  982 */       ErrorCode var5 = this.field_152873_i.getStreamInfo(this.field_152885_u, this.field_152880_p);
/*      */       
/*  984 */       if (ErrorCode.failed(var5)) {
/*      */         
/*  986 */         String var6 = ErrorCode.getString(var5);
/*  987 */         func_152820_d(String.format("Error in TTV_GetStreamInfo: %s", new Object[] { var6 }));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public IngestServerTester func_152838_J() {
/*  994 */     if (func_152857_n() && this.field_152883_s != null) {
/*      */       
/*  996 */       if (isIngestTesting())
/*      */       {
/*  998 */         return null;
/*      */       }
/*      */ 
/*      */       
/* 1002 */       this.field_152860_A = new IngestServerTester(this.field_152873_i, this.field_152883_s);
/* 1003 */       this.field_152860_A.func_176004_j();
/* 1004 */       func_152827_a(BroadcastState.IngestTesting);
/* 1005 */       return this.field_152860_A;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1010 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean func_152823_L() {
/* 1016 */     for (int var1 = 0; var1 < 3; var1++) {
/*      */       
/* 1018 */       FrameBuffer var2 = this.field_152873_i.allocateFrameBuffer(this.field_152881_q.outputWidth * this.field_152881_q.outputHeight * 4);
/*      */       
/* 1020 */       if (!var2.getIsValid()) {
/*      */         
/* 1022 */         func_152820_d(String.format("Error while allocating frame buffer", new Object[0]));
/* 1023 */         return false;
/*      */       } 
/*      */       
/* 1026 */       this.field_152874_j.add(var2);
/* 1027 */       this.field_152875_k.add(var2);
/*      */     } 
/*      */     
/* 1030 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_152831_M() {
/* 1035 */     for (int var1 = 0; var1 < this.field_152874_j.size(); var1++) {
/*      */       
/* 1037 */       FrameBuffer var2 = this.field_152874_j.get(var1);
/* 1038 */       var2.free();
/*      */     } 
/*      */     
/* 1041 */     this.field_152875_k.clear();
/* 1042 */     this.field_152874_j.clear();
/*      */   }
/*      */ 
/*      */   
/*      */   public FrameBuffer func_152822_N() {
/* 1047 */     if (this.field_152875_k.size() == 0) {
/*      */       
/* 1049 */       func_152820_d(String.format("Out of free buffers, this should never happen", new Object[0]));
/* 1050 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 1054 */     FrameBuffer var1 = this.field_152875_k.get(this.field_152875_k.size() - 1);
/* 1055 */     this.field_152875_k.remove(this.field_152875_k.size() - 1);
/* 1056 */     return var1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_152846_a(FrameBuffer p_152846_1_) {
/*      */     try {
/* 1064 */       this.field_152873_i.captureFrameBuffer_ReadPixels(p_152846_1_);
/*      */     }
/* 1066 */     catch (Throwable var5) {
/*      */       
/* 1068 */       CrashReport var3 = CrashReport.makeCrashReport(var5, "Trying to submit a frame to Twitch");
/* 1069 */       CrashReportCategory var4 = var3.makeCategory("Broadcast State");
/* 1070 */       var4.addCrashSection("Last reported errors", Arrays.toString(field_152862_C.func_152756_c()));
/* 1071 */       var4.addCrashSection("Buffer", p_152846_1_);
/* 1072 */       var4.addCrashSection("Free buffer count", Integer.valueOf(this.field_152875_k.size()));
/* 1073 */       var4.addCrashSection("Capture buffer count", Integer.valueOf(this.field_152874_j.size()));
/* 1074 */       throw new ReportedException(var3);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public ErrorCode func_152859_b(FrameBuffer p_152859_1_) {
/* 1080 */     if (isBroadcastPaused()) {
/*      */       
/* 1082 */       func_152854_G();
/*      */     }
/* 1084 */     else if (!isBroadcasting()) {
/*      */       
/* 1086 */       return ErrorCode.TTV_EC_STREAM_NOT_STARTED;
/*      */     } 
/*      */     
/* 1089 */     ErrorCode var2 = this.field_152873_i.submitVideoFrame(p_152859_1_);
/*      */     
/* 1091 */     if (var2 != ErrorCode.TTV_EC_SUCCESS) {
/*      */       
/* 1093 */       String var3 = ErrorCode.getString(var2);
/*      */       
/* 1095 */       if (ErrorCode.succeeded(var2)) {
/*      */         
/* 1097 */         func_152832_e(String.format("Warning in SubmitTexturePointer: %s\n", new Object[] { var3 }));
/*      */       }
/*      */       else {
/*      */         
/* 1101 */         func_152820_d(String.format("Error in SubmitTexturePointer: %s\n", new Object[] { var3 }));
/* 1102 */         func_152819_E();
/*      */       } 
/*      */       
/* 1105 */       if (this.field_152867_c != null)
/*      */       {
/* 1107 */         this.field_152867_c.func_152893_b(var2);
/*      */       }
/*      */     } 
/*      */     
/* 1111 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean func_152853_a(ErrorCode p_152853_1_) {
/* 1116 */     if (ErrorCode.failed(p_152853_1_)) {
/*      */       
/* 1118 */       func_152820_d(ErrorCode.getString(p_152853_1_));
/* 1119 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1123 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_152820_d(String p_152820_1_) {
/* 1129 */     this.field_152863_D = p_152820_1_;
/* 1130 */     field_152862_C.func_152757_a("<Error> " + p_152820_1_);
/* 1131 */     logger.error(TwitchStream.field_152949_a, "[Broadcast controller] {}", new Object[] { p_152820_1_ });
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_152832_e(String p_152832_1_) {
/* 1136 */     field_152862_C.func_152757_a("<Warning> " + p_152832_1_);
/* 1137 */     logger.warn(TwitchStream.field_152949_a, "[Broadcast controller] {}", new Object[] { p_152832_1_ });
/*      */   }
/*      */   
/*      */   public static interface BroadcastListener
/*      */   {
/*      */     void func_152900_a(ErrorCode param1ErrorCode, AuthToken param1AuthToken);
/*      */     
/*      */     void func_152897_a(ErrorCode param1ErrorCode);
/*      */     
/*      */     void func_152898_a(ErrorCode param1ErrorCode, GameInfo[] param1ArrayOfGameInfo);
/*      */     
/*      */     void func_152891_a(BroadcastController.BroadcastState param1BroadcastState);
/*      */     
/*      */     void func_152895_a();
/*      */     
/*      */     void func_152894_a(StreamInfo param1StreamInfo);
/*      */     
/*      */     void func_152896_a(IngestList param1IngestList);
/*      */     
/*      */     void func_152893_b(ErrorCode param1ErrorCode);
/*      */     
/*      */     void func_152899_b();
/*      */     
/*      */     void func_152901_c();
/*      */     
/*      */     void func_152892_c(ErrorCode param1ErrorCode);
/*      */   }
/*      */   
/*      */   public enum BroadcastState
/*      */   {
/* 1167 */     Uninitialized("Uninitialized", 0),
/* 1168 */     Initialized("Initialized", 1),
/* 1169 */     Authenticating("Authenticating", 2),
/* 1170 */     Authenticated("Authenticated", 3),
/* 1171 */     LoggingIn("LoggingIn", 4),
/* 1172 */     LoggedIn("LoggedIn", 5),
/* 1173 */     FindingIngestServer("FindingIngestServer", 6),
/* 1174 */     ReceivedIngestServers("ReceivedIngestServers", 7),
/* 1175 */     ReadyToBroadcast("ReadyToBroadcast", 8),
/* 1176 */     Starting("Starting", 9),
/* 1177 */     Broadcasting("Broadcasting", 10),
/* 1178 */     Stopping("Stopping", 11),
/* 1179 */     Paused("Paused", 12),
/* 1180 */     IngestTesting("IngestTesting", 13);
/*      */     
/* 1182 */     private static final BroadcastState[] $VALUES = new BroadcastState[] { Uninitialized, Initialized, Authenticating, Authenticated, LoggingIn, LoggedIn, FindingIngestServer, ReceivedIngestServers, ReadyToBroadcast, Starting, Broadcasting, Stopping, Paused, IngestTesting };
/*      */     private static final String __OBFID = "CL_00001820";
/*      */     
/*      */     static {
/*      */     
/*      */     } }
/*      */   
/*      */   static final class SwitchBroadcastState {
/* 1190 */     static final int[] field_177773_a = new int[(BroadcastController.BroadcastState.values()).length];
/*      */     
/*      */     private static final String __OBFID = "CL_00001821";
/*      */ 
/*      */     
/*      */     static {
/*      */       try {
/* 1197 */         field_177773_a[BroadcastController.BroadcastState.Authenticated.ordinal()] = 1;
/*      */       }
/* 1199 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1206 */         field_177773_a[BroadcastController.BroadcastState.LoggedIn.ordinal()] = 2;
/*      */       }
/* 1208 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1215 */         field_177773_a[BroadcastController.BroadcastState.ReceivedIngestServers.ordinal()] = 3;
/*      */       }
/* 1217 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1224 */         field_177773_a[BroadcastController.BroadcastState.Starting.ordinal()] = 4;
/*      */       }
/* 1226 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1233 */         field_177773_a[BroadcastController.BroadcastState.Stopping.ordinal()] = 5;
/*      */       }
/* 1235 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1242 */         field_177773_a[BroadcastController.BroadcastState.FindingIngestServer.ordinal()] = 6;
/*      */       }
/* 1244 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1251 */         field_177773_a[BroadcastController.BroadcastState.Authenticating.ordinal()] = 7;
/*      */       }
/* 1253 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1260 */         field_177773_a[BroadcastController.BroadcastState.Initialized.ordinal()] = 8;
/*      */       }
/* 1262 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1269 */         field_177773_a[BroadcastController.BroadcastState.Uninitialized.ordinal()] = 9;
/*      */       }
/* 1271 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1278 */         field_177773_a[BroadcastController.BroadcastState.IngestTesting.ordinal()] = 10;
/*      */       }
/* 1280 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1287 */         field_177773_a[BroadcastController.BroadcastState.Paused.ordinal()] = 11;
/*      */       }
/* 1289 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1296 */         field_177773_a[BroadcastController.BroadcastState.Broadcasting.ordinal()] = 12;
/*      */       }
/* 1298 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\BroadcastController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */