/*      */ package net.minecraft.client.stream;
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.ListIterator;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ import tv.twitch.AuthToken;
/*      */ import tv.twitch.Core;
/*      */ import tv.twitch.CoreAPI;
/*      */ import tv.twitch.ErrorCode;
/*      */ import tv.twitch.StandardCoreAPI;
/*      */ import tv.twitch.chat.Chat;
/*      */ import tv.twitch.chat.ChatAPI;
/*      */ import tv.twitch.chat.ChatBadgeData;
/*      */ import tv.twitch.chat.ChatChannelInfo;
/*      */ import tv.twitch.chat.ChatEmoticonData;
/*      */ import tv.twitch.chat.ChatEvent;
/*      */ import tv.twitch.chat.ChatRawMessage;
/*      */ import tv.twitch.chat.ChatTokenizationOption;
/*      */ import tv.twitch.chat.ChatTokenizedMessage;
/*      */ import tv.twitch.chat.ChatUserInfo;
/*      */ import tv.twitch.chat.IChatAPIListener;
/*      */ import tv.twitch.chat.IChatChannelListener;
/*      */ import tv.twitch.chat.StandardChatAPI;
/*      */ 
/*      */ public class ChatController {
/*   30 */   private static final Logger LOGGER = LogManager.getLogger();
/*   31 */   protected ChatListener field_153003_a = null;
/*   32 */   protected String field_153004_b = "";
/*   33 */   protected String field_153006_d = "";
/*   34 */   protected String field_153007_e = "";
/*   35 */   protected Core field_175992_e = null;
/*   36 */   protected Chat field_153008_f = null;
/*      */   
/*      */   protected ChatState field_153011_i;
/*      */   protected AuthToken field_153012_j;
/*      */   protected HashMap field_175998_i;
/*      */   protected int field_153015_m;
/*      */   protected EnumEmoticonMode field_175997_k;
/*      */   protected EnumEmoticonMode field_175995_l;
/*      */   protected ChatEmoticonData field_175996_m;
/*      */   protected int field_175993_n;
/*      */   protected int field_175994_o;
/*      */   protected IChatAPIListener field_175999_p;
/*      */   private static final String __OBFID = "CL_00001819";
/*      */   
/*      */   public void func_152990_a(ChatListener p_152990_1_) {
/*   51 */     this.field_153003_a = p_152990_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152994_a(AuthToken p_152994_1_) {
/*   56 */     this.field_153012_j = p_152994_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152984_a(String p_152984_1_) {
/*   61 */     this.field_153006_d = p_152984_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_152998_c(String p_152998_1_) {
/*   66 */     this.field_153004_b = p_152998_1_;
/*      */   }
/*      */ 
/*      */   
/*      */   public ChatState func_153000_j() {
/*   71 */     return this.field_153011_i;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175990_d(String p_175990_1_) {
/*   76 */     if (!this.field_175998_i.containsKey(p_175990_1_))
/*      */     {
/*   78 */       return false;
/*      */     }
/*      */ 
/*      */     
/*   82 */     ChatChannelListener var2 = (ChatChannelListener)this.field_175998_i.get(p_175990_1_);
/*   83 */     return (var2.func_176040_a() == EnumChannelState.Connected);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumChannelState func_175989_e(String p_175989_1_) {
/*   89 */     if (!this.field_175998_i.containsKey(p_175989_1_))
/*      */     {
/*   91 */       return EnumChannelState.Disconnected;
/*      */     }
/*      */ 
/*      */     
/*   95 */     ChatChannelListener var2 = (ChatChannelListener)this.field_175998_i.get(p_175989_1_);
/*   96 */     return var2.func_176040_a();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ChatController() {
/*  102 */     this.field_153011_i = ChatState.Uninitialized;
/*  103 */     this.field_153012_j = new AuthToken();
/*  104 */     this.field_175998_i = new HashMap<>();
/*  105 */     this.field_153015_m = 128;
/*  106 */     this.field_175997_k = EnumEmoticonMode.None;
/*  107 */     this.field_175995_l = EnumEmoticonMode.None;
/*  108 */     this.field_175996_m = null;
/*  109 */     this.field_175993_n = 500;
/*  110 */     this.field_175994_o = 2000;
/*  111 */     this.field_175999_p = new IChatAPIListener()
/*      */       {
/*      */         private static final String __OBFID = "CL_00002373";
/*      */         
/*      */         public void chatInitializationCallback(ErrorCode p_chatInitializationCallback_1_) {
/*  116 */           if (ErrorCode.succeeded(p_chatInitializationCallback_1_)) {
/*      */             
/*  118 */             ChatController.this.field_153008_f.setMessageFlushInterval(ChatController.this.field_175993_n);
/*  119 */             ChatController.this.field_153008_f.setUserChangeEventInterval(ChatController.this.field_175994_o);
/*  120 */             ChatController.this.func_153001_r();
/*  121 */             ChatController.this.func_175985_a(ChatController.ChatState.Initialized);
/*      */           }
/*      */           else {
/*      */             
/*  125 */             ChatController.this.func_175985_a(ChatController.ChatState.Uninitialized);
/*      */           } 
/*      */ 
/*      */           
/*      */           try {
/*  130 */             if (ChatController.this.field_153003_a != null)
/*      */             {
/*  132 */               ChatController.this.field_153003_a.func_176023_d(p_chatInitializationCallback_1_);
/*      */             }
/*      */           }
/*  135 */           catch (Exception var3) {
/*      */             
/*  137 */             ChatController.this.func_152995_h(var3.toString());
/*      */           } 
/*      */         }
/*      */         
/*      */         public void chatShutdownCallback(ErrorCode p_chatShutdownCallback_1_) {
/*  142 */           if (ErrorCode.succeeded(p_chatShutdownCallback_1_)) {
/*      */             
/*  144 */             ErrorCode var2 = ChatController.this.field_175992_e.shutdown();
/*      */             
/*  146 */             if (ErrorCode.failed(var2)) {
/*      */               
/*  148 */               String var3 = ErrorCode.getString(var2);
/*  149 */               ChatController.this.func_152995_h(String.format("Error shutting down the Twitch sdk: %s", new Object[] { var3 }));
/*      */             } 
/*      */             
/*  152 */             ChatController.this.func_175985_a(ChatController.ChatState.Uninitialized);
/*      */           }
/*      */           else {
/*      */             
/*  156 */             ChatController.this.func_175985_a(ChatController.ChatState.Initialized);
/*  157 */             ChatController.this.func_152995_h(String.format("Error shutting down Twith chat: %s", new Object[] { p_chatShutdownCallback_1_ }));
/*      */           } 
/*      */ 
/*      */           
/*      */           try {
/*  162 */             if (ChatController.this.field_153003_a != null)
/*      */             {
/*  164 */               ChatController.this.field_153003_a.func_176022_e(p_chatShutdownCallback_1_);
/*      */             }
/*      */           }
/*  167 */           catch (Exception var4) {
/*      */             
/*  169 */             ChatController.this.func_152995_h(var4.toString());
/*      */           } 
/*      */         }
/*      */         
/*      */         public void chatEmoticonDataDownloadCallback(ErrorCode p_chatEmoticonDataDownloadCallback_1_) {
/*  174 */           if (ErrorCode.succeeded(p_chatEmoticonDataDownloadCallback_1_))
/*      */           {
/*  176 */             ChatController.this.func_152988_s();
/*      */           }
/*      */         }
/*      */       };
/*  180 */     this.field_175992_e = Core.getInstance();
/*      */     
/*  182 */     if (this.field_175992_e == null)
/*      */     {
/*  184 */       this.field_175992_e = new Core((CoreAPI)new StandardCoreAPI());
/*      */     }
/*      */     
/*  187 */     this.field_153008_f = new Chat((ChatAPI)new StandardChatAPI());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175984_n() {
/*  192 */     if (this.field_153011_i != ChatState.Uninitialized)
/*      */     {
/*  194 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  198 */     func_175985_a(ChatState.Initializing);
/*  199 */     ErrorCode var1 = this.field_175992_e.initialize(this.field_153006_d, null);
/*      */     
/*  201 */     if (ErrorCode.failed(var1)) {
/*      */       
/*  203 */       func_175985_a(ChatState.Uninitialized);
/*  204 */       String var4 = ErrorCode.getString(var1);
/*  205 */       func_152995_h(String.format("Error initializing Twitch sdk: %s", new Object[] { var4 }));
/*  206 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  210 */     this.field_175995_l = this.field_175997_k;
/*  211 */     HashSet<ChatTokenizationOption> var2 = new HashSet();
/*      */     
/*  213 */     switch (SwitchEnumEmoticonMode.field_175975_c[this.field_175997_k.ordinal()]) {
/*      */       
/*      */       case 1:
/*  216 */         var2.add(ChatTokenizationOption.TTV_CHAT_TOKENIZATION_OPTION_NONE);
/*      */         break;
/*      */       
/*      */       case 2:
/*  220 */         var2.add(ChatTokenizationOption.TTV_CHAT_TOKENIZATION_OPTION_EMOTICON_URLS);
/*      */         break;
/*      */       
/*      */       case 3:
/*  224 */         var2.add(ChatTokenizationOption.TTV_CHAT_TOKENIZATION_OPTION_EMOTICON_TEXTURES);
/*      */         break;
/*      */     } 
/*  227 */     var1 = this.field_153008_f.initialize(var2, this.field_175999_p);
/*      */     
/*  229 */     if (ErrorCode.failed(var1)) {
/*      */       
/*  231 */       this.field_175992_e.shutdown();
/*  232 */       func_175985_a(ChatState.Uninitialized);
/*  233 */       String var3 = ErrorCode.getString(var1);
/*  234 */       func_152995_h(String.format("Error initializing Twitch chat: %s", new Object[] { var3 }));
/*  235 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  239 */     func_175985_a(ChatState.Initialized);
/*  240 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152986_d(String p_152986_1_) {
/*  248 */     return func_175987_a(p_152986_1_, false);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean func_175987_a(String p_175987_1_, boolean p_175987_2_) {
/*  253 */     if (this.field_153011_i != ChatState.Initialized)
/*      */     {
/*  255 */       return false;
/*      */     }
/*  257 */     if (this.field_175998_i.containsKey(p_175987_1_)) {
/*      */       
/*  259 */       func_152995_h("Already in channel: " + p_175987_1_);
/*  260 */       return false;
/*      */     } 
/*  262 */     if (p_175987_1_ != null && !p_175987_1_.equals("")) {
/*      */       
/*  264 */       ChatChannelListener var3 = new ChatChannelListener(p_175987_1_);
/*  265 */       this.field_175998_i.put(p_175987_1_, var3);
/*  266 */       boolean var4 = var3.func_176038_a(p_175987_2_);
/*      */       
/*  268 */       if (!var4)
/*      */       {
/*  270 */         this.field_175998_i.remove(p_175987_1_);
/*      */       }
/*      */       
/*  273 */       return var4;
/*      */     } 
/*      */ 
/*      */     
/*  277 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_175991_l(String p_175991_1_) {
/*  283 */     if (this.field_153011_i != ChatState.Initialized)
/*      */     {
/*  285 */       return false;
/*      */     }
/*  287 */     if (!this.field_175998_i.containsKey(p_175991_1_)) {
/*      */       
/*  289 */       func_152995_h("Not in channel: " + p_175991_1_);
/*  290 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  294 */     ChatChannelListener var2 = (ChatChannelListener)this.field_175998_i.get(p_175991_1_);
/*  295 */     return var2.func_176034_g();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_152993_m() {
/*  301 */     if (this.field_153011_i != ChatState.Initialized)
/*      */     {
/*  303 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  307 */     ErrorCode var1 = this.field_153008_f.shutdown();
/*      */     
/*  309 */     if (ErrorCode.failed(var1)) {
/*      */       
/*  311 */       String var2 = ErrorCode.getString(var1);
/*  312 */       func_152995_h(String.format("Error shutting down chat: %s", new Object[] { var2 }));
/*  313 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  317 */     func_152996_t();
/*  318 */     func_175985_a(ChatState.ShuttingDown);
/*  319 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_175988_p() {
/*  326 */     if (func_153000_j() != ChatState.Uninitialized) {
/*      */       
/*  328 */       func_152993_m();
/*      */       
/*  330 */       if (func_153000_j() == ChatState.ShuttingDown)
/*      */       {
/*  332 */         while (func_153000_j() != ChatState.Uninitialized) {
/*      */ 
/*      */           
/*      */           try {
/*  336 */             Thread.sleep(200L);
/*  337 */             func_152997_n();
/*      */           }
/*  339 */           catch (InterruptedException interruptedException) {}
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_152997_n() {
/*  350 */     if (this.field_153011_i != ChatState.Uninitialized) {
/*      */       
/*  352 */       ErrorCode var1 = this.field_153008_f.flushEvents();
/*      */       
/*  354 */       if (ErrorCode.failed(var1)) {
/*      */         
/*  356 */         String var2 = ErrorCode.getString(var1);
/*  357 */         func_152995_h(String.format("Error flushing chat events: %s", new Object[] { var2 }));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_175986_a(String p_175986_1_, String p_175986_2_) {
/*  364 */     if (this.field_153011_i != ChatState.Initialized)
/*      */     {
/*  366 */       return false;
/*      */     }
/*  368 */     if (!this.field_175998_i.containsKey(p_175986_1_)) {
/*      */       
/*  370 */       func_152995_h("Not in channel: " + p_175986_1_);
/*  371 */       return false;
/*      */     } 
/*      */ 
/*      */     
/*  375 */     ChatChannelListener var3 = (ChatChannelListener)this.field_175998_i.get(p_175986_1_);
/*  376 */     return var3.func_176037_b(p_175986_2_);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_175985_a(ChatState p_175985_1_) {
/*  382 */     if (p_175985_1_ != this.field_153011_i) {
/*      */       
/*  384 */       this.field_153011_i = p_175985_1_;
/*      */ 
/*      */       
/*      */       try {
/*  388 */         if (this.field_153003_a != null)
/*      */         {
/*  390 */           this.field_153003_a.func_176017_a(p_175985_1_);
/*      */         }
/*      */       }
/*  393 */       catch (Exception var3) {
/*      */         
/*  395 */         func_152995_h(var3.toString());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_153001_r() {
/*  402 */     if (this.field_175995_l != EnumEmoticonMode.None)
/*      */     {
/*  404 */       if (this.field_175996_m == null) {
/*      */         
/*  406 */         ErrorCode var1 = this.field_153008_f.downloadEmoticonData();
/*      */         
/*  408 */         if (ErrorCode.failed(var1)) {
/*      */           
/*  410 */           String var2 = ErrorCode.getString(var1);
/*  411 */           func_152995_h(String.format("Error trying to download emoticon data: %s", new Object[] { var2 }));
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_152988_s() {
/*  419 */     if (this.field_175996_m == null) {
/*      */       
/*  421 */       this.field_175996_m = new ChatEmoticonData();
/*  422 */       ErrorCode var1 = this.field_153008_f.getEmoticonData(this.field_175996_m);
/*      */       
/*  424 */       if (ErrorCode.succeeded(var1)) {
/*      */         
/*      */         try
/*      */         {
/*  428 */           if (this.field_153003_a != null)
/*      */           {
/*  430 */             this.field_153003_a.func_176021_d();
/*      */           }
/*      */         }
/*  433 */         catch (Exception var3)
/*      */         {
/*  435 */           func_152995_h(var3.toString());
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  440 */         func_152995_h("Error preparing emoticon data: " + ErrorCode.getString(var1));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_152996_t() {
/*  447 */     if (this.field_175996_m != null) {
/*      */       
/*  449 */       ErrorCode var1 = this.field_153008_f.clearEmoticonData();
/*      */       
/*  451 */       if (ErrorCode.succeeded(var1)) {
/*      */         
/*  453 */         this.field_175996_m = null;
/*      */ 
/*      */         
/*      */         try {
/*  457 */           if (this.field_153003_a != null)
/*      */           {
/*  459 */             this.field_153003_a.func_176024_e();
/*      */           }
/*      */         }
/*  462 */         catch (Exception var3) {
/*      */           
/*  464 */           func_152995_h(var3.toString());
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  469 */         func_152995_h("Error clearing emoticon data: " + ErrorCode.getString(var1));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_152995_h(String p_152995_1_) {
/*  476 */     LOGGER.error(TwitchStream.field_152949_a, "[Chat controller] {}", new Object[] { p_152995_1_ });
/*      */   }
/*      */   
/*      */   public class ChatChannelListener
/*      */     implements IChatChannelListener {
/*  481 */     protected String field_176048_a = null;
/*      */     
/*      */     protected boolean field_176046_b = false;
/*      */     protected ChatController.EnumChannelState field_176047_c;
/*      */     protected List field_176044_d;
/*      */     protected LinkedList field_176045_e;
/*      */     protected LinkedList field_176042_f;
/*      */     protected ChatBadgeData field_176043_g;
/*      */     private static final String __OBFID = "CL_00002370";
/*      */     
/*      */     public ChatChannelListener(String p_i46061_2_) {
/*  492 */       this.field_176047_c = ChatController.EnumChannelState.Created;
/*  493 */       this.field_176044_d = Lists.newArrayList();
/*  494 */       this.field_176045_e = new LinkedList();
/*  495 */       this.field_176042_f = new LinkedList();
/*  496 */       this.field_176043_g = null;
/*  497 */       this.field_176048_a = p_i46061_2_;
/*      */     }
/*      */ 
/*      */     
/*      */     public ChatController.EnumChannelState func_176040_a() {
/*  502 */       return this.field_176047_c;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean func_176038_a(boolean p_176038_1_) {
/*  507 */       this.field_176046_b = p_176038_1_;
/*  508 */       ErrorCode var2 = ErrorCode.TTV_EC_SUCCESS;
/*      */       
/*  510 */       if (p_176038_1_) {
/*      */         
/*  512 */         var2 = ChatController.this.field_153008_f.connectAnonymous(this.field_176048_a, this);
/*      */       }
/*      */       else {
/*      */         
/*  516 */         var2 = ChatController.this.field_153008_f.connect(this.field_176048_a, ChatController.this.field_153004_b, ChatController.this.field_153012_j.data, this);
/*      */       } 
/*      */       
/*  519 */       if (ErrorCode.failed(var2)) {
/*      */         
/*  521 */         String var3 = ErrorCode.getString(var2);
/*  522 */         ChatController.this.func_152995_h(String.format("Error connecting: %s", new Object[] { var3 }));
/*  523 */         func_176036_d(this.field_176048_a);
/*  524 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  528 */       func_176035_a(ChatController.EnumChannelState.Connecting);
/*  529 */       func_176041_h();
/*  530 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean func_176034_g() {
/*      */       ErrorCode var1;
/*  536 */       switch (ChatController.SwitchEnumEmoticonMode.field_175976_a[this.field_176047_c.ordinal()]) {
/*      */         
/*      */         case 1:
/*      */         case 2:
/*  540 */           var1 = ChatController.this.field_153008_f.disconnect(this.field_176048_a);
/*      */           
/*  542 */           if (ErrorCode.failed(var1)) {
/*      */             
/*  544 */             String var2 = ErrorCode.getString(var1);
/*  545 */             ChatController.this.func_152995_h(String.format("Error disconnecting: %s", new Object[] { var2 }));
/*  546 */             return false;
/*      */           } 
/*      */           
/*  549 */           func_176035_a(ChatController.EnumChannelState.Disconnecting);
/*  550 */           return true;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  556 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void func_176035_a(ChatController.EnumChannelState p_176035_1_) {
/*  562 */       if (p_176035_1_ != this.field_176047_c)
/*      */       {
/*  564 */         this.field_176047_c = p_176035_1_;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void func_176032_a(String p_176032_1_) {
/*  570 */       if (ChatController.this.field_175995_l == ChatController.EnumEmoticonMode.None) {
/*      */         
/*  572 */         this.field_176045_e.clear();
/*  573 */         this.field_176042_f.clear();
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  579 */         if (this.field_176045_e.size() > 0) {
/*      */           
/*  581 */           ListIterator<ChatRawMessage> var2 = this.field_176045_e.listIterator();
/*      */           
/*  583 */           while (var2.hasNext()) {
/*      */             
/*  585 */             ChatRawMessage var3 = var2.next();
/*      */             
/*  587 */             if (var3.userName.equals(p_176032_1_))
/*      */             {
/*  589 */               var2.remove();
/*      */             }
/*      */           } 
/*      */         } 
/*      */         
/*  594 */         if (this.field_176042_f.size() > 0) {
/*      */           
/*  596 */           ListIterator<ChatTokenizedMessage> var2 = this.field_176042_f.listIterator();
/*      */           
/*  598 */           while (var2.hasNext()) {
/*      */             
/*  600 */             ChatTokenizedMessage var5 = var2.next();
/*      */             
/*  602 */             if (var5.displayName.equals(p_176032_1_))
/*      */             {
/*  604 */               var2.remove();
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*      */       try {
/*  612 */         if (ChatController.this.field_153003_a != null)
/*      */         {
/*  614 */           ChatController.this.field_153003_a.func_176019_a(this.field_176048_a, p_176032_1_);
/*      */         }
/*      */       }
/*  617 */       catch (Exception var4) {
/*      */         
/*  619 */         ChatController.this.func_152995_h(var4.toString());
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean func_176037_b(String p_176037_1_) {
/*  625 */       if (this.field_176047_c != ChatController.EnumChannelState.Connected)
/*      */       {
/*  627 */         return false;
/*      */       }
/*      */ 
/*      */       
/*  631 */       ErrorCode var2 = ChatController.this.field_153008_f.sendMessage(this.field_176048_a, p_176037_1_);
/*      */       
/*  633 */       if (ErrorCode.failed(var2)) {
/*      */         
/*  635 */         String var3 = ErrorCode.getString(var2);
/*  636 */         ChatController.this.func_152995_h(String.format("Error sending chat message: %s", new Object[] { var3 }));
/*  637 */         return false;
/*      */       } 
/*      */ 
/*      */       
/*  641 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected void func_176041_h() {
/*  648 */       if (ChatController.this.field_175995_l != ChatController.EnumEmoticonMode.None)
/*      */       {
/*  650 */         if (this.field_176043_g == null) {
/*      */           
/*  652 */           ErrorCode var1 = ChatController.this.field_153008_f.downloadBadgeData(this.field_176048_a);
/*      */           
/*  654 */           if (ErrorCode.failed(var1)) {
/*      */             
/*  656 */             String var2 = ErrorCode.getString(var1);
/*  657 */             ChatController.this.func_152995_h(String.format("Error trying to download badge data: %s", new Object[] { var2 }));
/*      */           } 
/*      */         } 
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     protected void func_176039_i() {
/*  665 */       if (this.field_176043_g == null) {
/*      */         
/*  667 */         this.field_176043_g = new ChatBadgeData();
/*  668 */         ErrorCode var1 = ChatController.this.field_153008_f.getBadgeData(this.field_176048_a, this.field_176043_g);
/*      */         
/*  670 */         if (ErrorCode.succeeded(var1)) {
/*      */           
/*      */           try
/*      */           {
/*  674 */             if (ChatController.this.field_153003_a != null)
/*      */             {
/*  676 */               ChatController.this.field_153003_a.func_176016_c(this.field_176048_a);
/*      */             }
/*      */           }
/*  679 */           catch (Exception var3)
/*      */           {
/*  681 */             ChatController.this.func_152995_h(var3.toString());
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  686 */           ChatController.this.func_152995_h("Error preparing badge data: " + ErrorCode.getString(var1));
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     protected void func_176033_j() {
/*  693 */       if (this.field_176043_g != null) {
/*      */         
/*  695 */         ErrorCode var1 = ChatController.this.field_153008_f.clearBadgeData(this.field_176048_a);
/*      */         
/*  697 */         if (ErrorCode.succeeded(var1)) {
/*      */           
/*  699 */           this.field_176043_g = null;
/*      */ 
/*      */           
/*      */           try {
/*  703 */             if (ChatController.this.field_153003_a != null)
/*      */             {
/*  705 */               ChatController.this.field_153003_a.func_176020_d(this.field_176048_a);
/*      */             }
/*      */           }
/*  708 */           catch (Exception var3) {
/*      */             
/*  710 */             ChatController.this.func_152995_h(var3.toString());
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  715 */           ChatController.this.func_152995_h("Error releasing badge data: " + ErrorCode.getString(var1));
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void func_176031_c(String p_176031_1_) {
/*      */       try {
/*  724 */         if (ChatController.this.field_153003_a != null)
/*      */         {
/*  726 */           ChatController.this.field_153003_a.func_180606_a(p_176031_1_);
/*      */         }
/*      */       }
/*  729 */       catch (Exception var3) {
/*      */         
/*  731 */         ChatController.this.func_152995_h(var3.toString());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void func_176036_d(String p_176036_1_) {
/*      */       try {
/*  739 */         if (ChatController.this.field_153003_a != null)
/*      */         {
/*  741 */           ChatController.this.field_153003_a.func_180607_b(p_176036_1_);
/*      */         }
/*      */       }
/*  744 */       catch (Exception var3) {
/*      */         
/*  746 */         ChatController.this.func_152995_h(var3.toString());
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     private void func_176030_k() {
/*  752 */       if (this.field_176047_c != ChatController.EnumChannelState.Disconnected) {
/*      */         
/*  754 */         func_176035_a(ChatController.EnumChannelState.Disconnected);
/*  755 */         func_176036_d(this.field_176048_a);
/*  756 */         func_176033_j();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void chatStatusCallback(String p_chatStatusCallback_1_, ErrorCode p_chatStatusCallback_2_) {
/*  762 */       if (!ErrorCode.succeeded(p_chatStatusCallback_2_)) {
/*      */         
/*  764 */         ChatController.this.field_175998_i.remove(p_chatStatusCallback_1_);
/*  765 */         func_176030_k();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void chatChannelMembershipCallback(String p_chatChannelMembershipCallback_1_, ChatEvent p_chatChannelMembershipCallback_2_, ChatChannelInfo p_chatChannelMembershipCallback_3_) {
/*  771 */       switch (ChatController.SwitchEnumEmoticonMode.field_175974_b[p_chatChannelMembershipCallback_2_.ordinal()]) {
/*      */         
/*      */         case 1:
/*  774 */           func_176035_a(ChatController.EnumChannelState.Connected);
/*  775 */           func_176031_c(p_chatChannelMembershipCallback_1_);
/*      */           break;
/*      */         
/*      */         case 2:
/*  779 */           func_176030_k();
/*      */           break;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void chatChannelUserChangeCallback(String p_chatChannelUserChangeCallback_1_, ChatUserInfo[] p_chatChannelUserChangeCallback_2_, ChatUserInfo[] p_chatChannelUserChangeCallback_3_, ChatUserInfo[] p_chatChannelUserChangeCallback_4_) {
/*      */       int var5;
/*  788 */       for (var5 = 0; var5 < p_chatChannelUserChangeCallback_3_.length; var5++) {
/*      */         
/*  790 */         int var6 = this.field_176044_d.indexOf(p_chatChannelUserChangeCallback_3_[var5]);
/*      */         
/*  792 */         if (var6 >= 0)
/*      */         {
/*  794 */           this.field_176044_d.remove(var6);
/*      */         }
/*      */       } 
/*      */       
/*  798 */       for (var5 = 0; var5 < p_chatChannelUserChangeCallback_4_.length; var5++) {
/*      */         
/*  800 */         int var6 = this.field_176044_d.indexOf(p_chatChannelUserChangeCallback_4_[var5]);
/*      */         
/*  802 */         if (var6 >= 0)
/*      */         {
/*  804 */           this.field_176044_d.remove(var6);
/*      */         }
/*      */         
/*  807 */         this.field_176044_d.add(p_chatChannelUserChangeCallback_4_[var5]);
/*      */       } 
/*      */       
/*  810 */       for (var5 = 0; var5 < p_chatChannelUserChangeCallback_2_.length; var5++)
/*      */       {
/*  812 */         this.field_176044_d.add(p_chatChannelUserChangeCallback_2_[var5]);
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/*  817 */         if (ChatController.this.field_153003_a != null)
/*      */         {
/*  819 */           ChatController.this.field_153003_a.func_176018_a(this.field_176048_a, p_chatChannelUserChangeCallback_2_, p_chatChannelUserChangeCallback_3_, p_chatChannelUserChangeCallback_4_);
/*      */         }
/*      */       }
/*  822 */       catch (Exception var7) {
/*      */         
/*  824 */         ChatController.this.func_152995_h(var7.toString());
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public void chatChannelRawMessageCallback(String p_chatChannelRawMessageCallback_1_, ChatRawMessage[] p_chatChannelRawMessageCallback_2_) {
/*  830 */       for (int var3 = 0; var3 < p_chatChannelRawMessageCallback_2_.length; var3++)
/*      */       {
/*  832 */         this.field_176045_e.addLast(p_chatChannelRawMessageCallback_2_[var3]);
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/*  837 */         if (ChatController.this.field_153003_a != null)
/*      */         {
/*  839 */           ChatController.this.field_153003_a.func_180605_a(this.field_176048_a, p_chatChannelRawMessageCallback_2_);
/*      */         }
/*      */       }
/*  842 */       catch (Exception var4) {
/*      */         
/*  844 */         ChatController.this.func_152995_h(var4.toString());
/*      */       } 
/*      */       
/*  847 */       while (this.field_176045_e.size() > ChatController.this.field_153015_m)
/*      */       {
/*  849 */         this.field_176045_e.removeFirst();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void chatChannelTokenizedMessageCallback(String p_chatChannelTokenizedMessageCallback_1_, ChatTokenizedMessage[] p_chatChannelTokenizedMessageCallback_2_) {
/*  855 */       for (int var3 = 0; var3 < p_chatChannelTokenizedMessageCallback_2_.length; var3++)
/*      */       {
/*  857 */         this.field_176042_f.addLast(p_chatChannelTokenizedMessageCallback_2_[var3]);
/*      */       }
/*      */ 
/*      */       
/*      */       try {
/*  862 */         if (ChatController.this.field_153003_a != null)
/*      */         {
/*  864 */           ChatController.this.field_153003_a.func_176025_a(this.field_176048_a, p_chatChannelTokenizedMessageCallback_2_);
/*      */         }
/*      */       }
/*  867 */       catch (Exception var4) {
/*      */         
/*  869 */         ChatController.this.func_152995_h(var4.toString());
/*      */       } 
/*      */       
/*  872 */       while (this.field_176042_f.size() > ChatController.this.field_153015_m)
/*      */       {
/*  874 */         this.field_176042_f.removeFirst();
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public void chatClearCallback(String p_chatClearCallback_1_, String p_chatClearCallback_2_) {
/*  880 */       func_176032_a(p_chatClearCallback_2_);
/*      */     }
/*      */ 
/*      */     
/*      */     public void chatBadgeDataDownloadCallback(String p_chatBadgeDataDownloadCallback_1_, ErrorCode p_chatBadgeDataDownloadCallback_2_) {
/*  885 */       if (ErrorCode.succeeded(p_chatBadgeDataDownloadCallback_2_))
/*      */       {
/*  887 */         func_176039_i();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public static interface ChatListener
/*      */   {
/*      */     void func_176023_d(ErrorCode param1ErrorCode);
/*      */     
/*      */     void func_176022_e(ErrorCode param1ErrorCode);
/*      */     
/*      */     void func_176021_d();
/*      */     
/*      */     void func_176024_e();
/*      */     
/*      */     void func_176017_a(ChatController.ChatState param1ChatState);
/*      */     
/*      */     void func_176025_a(String param1String, ChatTokenizedMessage[] param1ArrayOfChatTokenizedMessage);
/*      */     
/*      */     void func_180605_a(String param1String, ChatRawMessage[] param1ArrayOfChatRawMessage);
/*      */     
/*      */     void func_176018_a(String param1String, ChatUserInfo[] param1ArrayOfChatUserInfo1, ChatUserInfo[] param1ArrayOfChatUserInfo2, ChatUserInfo[] param1ArrayOfChatUserInfo3);
/*      */     
/*      */     void func_180606_a(String param1String);
/*      */     
/*      */     void func_180607_b(String param1String);
/*      */     
/*      */     void func_176019_a(String param1String1, String param1String2);
/*      */     
/*      */     void func_176016_c(String param1String);
/*      */     
/*      */     void func_176020_d(String param1String);
/*      */   }
/*      */   
/*      */   public enum ChatState
/*      */   {
/*  923 */     Uninitialized("Uninitialized", 0),
/*  924 */     Initializing("Initializing", 1),
/*  925 */     Initialized("Initialized", 2),
/*  926 */     ShuttingDown("ShuttingDown", 3);
/*      */     
/*  928 */     private static final ChatState[] $VALUES = new ChatState[] { Uninitialized, Initializing, Initialized, ShuttingDown };
/*      */     private static final String __OBFID = "CL_00001817";
/*      */     
/*      */     static {
/*      */     
/*      */     }
/*      */   }
/*      */   
/*  936 */   public enum EnumChannelState { Created("Created", 0),
/*  937 */     Connecting("Connecting", 1),
/*  938 */     Connected("Connected", 2),
/*  939 */     Disconnecting("Disconnecting", 3),
/*  940 */     Disconnected("Disconnected", 4);
/*      */     
/*  942 */     private static final EnumChannelState[] $VALUES = new EnumChannelState[] { Created, Connecting, Connected, Disconnecting, Disconnected };
/*      */     private static final String __OBFID = "CL_00002371";
/*      */     
/*      */     static {
/*      */     
/*      */     } }
/*      */   
/*      */   public enum EnumEmoticonMode {
/*  950 */     None("None", 0),
/*  951 */     Url("Url", 1),
/*  952 */     TextureAtlas("TextureAtlas", 2);
/*      */     
/*  954 */     private static final EnumEmoticonMode[] $VALUES = new EnumEmoticonMode[] { None, Url, TextureAtlas };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private static final String __OBFID = "CL_00002369";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/*      */     
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static final class SwitchEnumEmoticonMode
/*      */   {
/*      */     static final int[] field_175976_a;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  998 */     static final int[] field_175974_b = new int[(ChatEvent.values()).length]; static final int[] field_175975_c = new int[(ChatController.EnumEmoticonMode.values()).length];
/*      */     
/*      */     static {
/*      */       try {
/* 1002 */         field_175974_b[ChatEvent.TTV_CHAT_JOINED_CHANNEL.ordinal()] = 1;
/*      */       }
/* 1004 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1011 */         field_175974_b[ChatEvent.TTV_CHAT_LEFT_CHANNEL.ordinal()] = 2;
/*      */       }
/* 1013 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1018 */       field_175976_a = new int[(ChatController.EnumChannelState.values()).length];
/*      */ 
/*      */       
/*      */       try {
/* 1022 */         field_175976_a[ChatController.EnumChannelState.Connected.ordinal()] = 1;
/*      */       }
/* 1024 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1031 */         field_175976_a[ChatController.EnumChannelState.Connecting.ordinal()] = 2;
/*      */       }
/* 1033 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1040 */         field_175976_a[ChatController.EnumChannelState.Created.ordinal()] = 3;
/*      */       }
/* 1042 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1049 */         field_175976_a[ChatController.EnumChannelState.Disconnected.ordinal()] = 4;
/*      */       }
/* 1051 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1058 */         field_175976_a[ChatController.EnumChannelState.Disconnecting.ordinal()] = 5;
/*      */       }
/* 1060 */       catch (NoSuchFieldError noSuchFieldError) {}
/*      */     }
/*      */     
/*      */     private static final String __OBFID = "CL_00002372";
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\ChatController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */