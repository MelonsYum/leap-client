/*     */ package net.minecraft.client.stream;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import tv.twitch.AuthToken;
/*     */ import tv.twitch.ErrorCode;
/*     */ import tv.twitch.broadcast.ArchivingState;
/*     */ import tv.twitch.broadcast.AudioParams;
/*     */ import tv.twitch.broadcast.ChannelInfo;
/*     */ import tv.twitch.broadcast.EncodingCpuUsage;
/*     */ import tv.twitch.broadcast.FrameBuffer;
/*     */ import tv.twitch.broadcast.GameInfoList;
/*     */ import tv.twitch.broadcast.IStatCallbacks;
/*     */ import tv.twitch.broadcast.IStreamCallbacks;
/*     */ import tv.twitch.broadcast.IngestList;
/*     */ import tv.twitch.broadcast.IngestServer;
/*     */ import tv.twitch.broadcast.PixelFormat;
/*     */ import tv.twitch.broadcast.RTMPState;
/*     */ import tv.twitch.broadcast.StartFlags;
/*     */ import tv.twitch.broadcast.StatType;
/*     */ import tv.twitch.broadcast.Stream;
/*     */ import tv.twitch.broadcast.StreamInfo;
/*     */ import tv.twitch.broadcast.UserInfo;
/*     */ import tv.twitch.broadcast.VideoParams;
/*     */ 
/*     */ public class IngestServerTester
/*     */ {
/*  28 */   protected IngestTestListener field_153044_b = null;
/*  29 */   protected Stream field_153045_c = null;
/*  30 */   protected IngestList field_153046_d = null;
/*     */   
/*     */   protected IngestTestState field_153047_e;
/*     */   protected long field_153048_f;
/*     */   protected long field_153049_g;
/*     */   protected long field_153050_h;
/*     */   protected RTMPState field_153051_i;
/*     */   protected VideoParams field_153052_j;
/*     */   protected AudioParams field_153053_k;
/*     */   protected long field_153054_l;
/*     */   protected List field_153055_m;
/*     */   protected boolean field_153056_n;
/*     */   protected IStreamCallbacks field_153057_o;
/*     */   protected IStatCallbacks field_153058_p;
/*     */   protected IngestServer field_153059_q;
/*     */   protected boolean field_153060_r;
/*     */   protected boolean field_153061_s;
/*     */   protected int field_153062_t;
/*     */   protected int field_153063_u;
/*     */   protected long field_153064_v;
/*     */   protected float field_153065_w;
/*     */   protected float field_153066_x;
/*     */   protected boolean field_176009_x;
/*     */   protected boolean field_176008_y;
/*     */   protected boolean field_176007_z;
/*     */   protected IStreamCallbacks field_176005_A;
/*     */   protected IStatCallbacks field_176006_B;
/*     */   private static final String __OBFID = "CL_00001816";
/*     */   
/*     */   public void func_153042_a(IngestTestListener p_153042_1_) {
/*  60 */     this.field_153044_b = p_153042_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public IngestServer func_153040_c() {
/*  65 */     return this.field_153059_q;
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_153028_p() {
/*  70 */     return this.field_153062_t;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_153032_e() {
/*  75 */     return !(this.field_153047_e != IngestTestState.Finished && this.field_153047_e != IngestTestState.Cancelled && this.field_153047_e != IngestTestState.Failed);
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_153030_h() {
/*  80 */     return this.field_153066_x;
/*     */   }
/*     */ 
/*     */   
/*     */   public IngestServerTester(Stream p_i1019_1_, IngestList p_i1019_2_) {
/*  85 */     this.field_153047_e = IngestTestState.Uninitalized;
/*  86 */     this.field_153048_f = 8000L;
/*  87 */     this.field_153049_g = 2000L;
/*  88 */     this.field_153050_h = 0L;
/*  89 */     this.field_153051_i = RTMPState.Invalid;
/*  90 */     this.field_153052_j = null;
/*  91 */     this.field_153053_k = null;
/*  92 */     this.field_153054_l = 0L;
/*  93 */     this.field_153055_m = null;
/*  94 */     this.field_153056_n = false;
/*  95 */     this.field_153057_o = null;
/*  96 */     this.field_153058_p = null;
/*  97 */     this.field_153059_q = null;
/*  98 */     this.field_153060_r = false;
/*  99 */     this.field_153061_s = false;
/* 100 */     this.field_153062_t = -1;
/* 101 */     this.field_153063_u = 0;
/* 102 */     this.field_153064_v = 0L;
/* 103 */     this.field_153065_w = 0.0F;
/* 104 */     this.field_153066_x = 0.0F;
/* 105 */     this.field_176009_x = false;
/* 106 */     this.field_176008_y = false;
/* 107 */     this.field_176007_z = false;
/* 108 */     this.field_176005_A = new IStreamCallbacks()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002368";
/*     */ 
/*     */         
/*     */         public void requestAuthTokenCallback(ErrorCode p_requestAuthTokenCallback_1_, AuthToken p_requestAuthTokenCallback_2_) {}
/*     */ 
/*     */         
/*     */         public void loginCallback(ErrorCode p_loginCallback_1_, ChannelInfo p_loginCallback_2_) {}
/*     */         
/*     */         public void getIngestServersCallback(ErrorCode p_getIngestServersCallback_1_, IngestList p_getIngestServersCallback_2_) {}
/*     */         
/*     */         public void getUserInfoCallback(ErrorCode p_getUserInfoCallback_1_, UserInfo p_getUserInfoCallback_2_) {}
/*     */         
/*     */         public void startCallback(ErrorCode p_startCallback_1_) {
/* 123 */           IngestServerTester.this.field_176008_y = false;
/*     */           
/* 125 */           if (ErrorCode.succeeded(p_startCallback_1_)) {
/*     */             
/* 127 */             IngestServerTester.this.field_176009_x = true;
/* 128 */             IngestServerTester.this.field_153054_l = System.currentTimeMillis();
/* 129 */             IngestServerTester.this.func_153034_a(IngestServerTester.IngestTestState.ConnectingToServer);
/*     */           }
/*     */           else {
/*     */             
/* 133 */             IngestServerTester.this.field_153056_n = false;
/* 134 */             IngestServerTester.this.func_153034_a(IngestServerTester.IngestTestState.DoneTestingServer);
/*     */           } 
/*     */         } public void getStreamInfoCallback(ErrorCode p_getStreamInfoCallback_1_, StreamInfo p_getStreamInfoCallback_2_) {} public void getArchivingStateCallback(ErrorCode p_getArchivingStateCallback_1_, ArchivingState p_getArchivingStateCallback_2_) {} public void runCommercialCallback(ErrorCode p_runCommercialCallback_1_) {} public void setStreamInfoCallback(ErrorCode p_setStreamInfoCallback_1_) {} public void getGameNameListCallback(ErrorCode p_getGameNameListCallback_1_, GameInfoList p_getGameNameListCallback_2_) {}
/*     */         public void bufferUnlockCallback(long p_bufferUnlockCallback_1_) {}
/*     */         public void stopCallback(ErrorCode p_stopCallback_1_) {
/* 139 */           if (ErrorCode.failed(p_stopCallback_1_))
/*     */           {
/* 141 */             System.out.println("IngestTester.stopCallback failed to stop - " + IngestServerTester.this.field_153059_q.serverName + ": " + p_stopCallback_1_.toString());
/*     */           }
/*     */           
/* 144 */           IngestServerTester.this.field_176007_z = false;
/* 145 */           IngestServerTester.this.field_176009_x = false;
/* 146 */           IngestServerTester.this.func_153034_a(IngestServerTester.IngestTestState.DoneTestingServer);
/* 147 */           IngestServerTester.this.field_153059_q = null;
/*     */           
/* 149 */           if (IngestServerTester.this.field_153060_r)
/*     */           {
/* 151 */             IngestServerTester.this.func_153034_a(IngestServerTester.IngestTestState.Cancelling); } 
/*     */         }
/*     */         public void sendActionMetaDataCallback(ErrorCode p_sendActionMetaDataCallback_1_) {}
/*     */         public void sendStartSpanMetaDataCallback(ErrorCode p_sendStartSpanMetaDataCallback_1_) {}
/*     */         
/*     */         public void sendEndSpanMetaDataCallback(ErrorCode p_sendEndSpanMetaDataCallback_1_) {}
/*     */       };
/* 158 */     this.field_176006_B = new IStatCallbacks()
/*     */       {
/*     */         private static final String __OBFID = "CL_00002367";
/*     */         
/*     */         public void statCallback(StatType p_statCallback_1_, long p_statCallback_2_) {
/* 163 */           switch (IngestServerTester.SwitchStatType.field_176003_a[p_statCallback_1_.ordinal()]) {
/*     */             
/*     */             case 1:
/* 166 */               IngestServerTester.this.field_153051_i = RTMPState.lookupValue((int)p_statCallback_2_);
/*     */               break;
/*     */             
/*     */             case 2:
/* 170 */               IngestServerTester.this.field_153050_h = p_statCallback_2_;
/*     */               break;
/*     */           }  }
/*     */       };
/* 174 */     this.field_153045_c = p_i1019_1_;
/* 175 */     this.field_153046_d = p_i1019_2_;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176004_j() {
/* 180 */     if (this.field_153047_e == IngestTestState.Uninitalized) {
/*     */       
/* 182 */       this.field_153062_t = 0;
/* 183 */       this.field_153060_r = false;
/* 184 */       this.field_153061_s = false;
/* 185 */       this.field_176009_x = false;
/* 186 */       this.field_176008_y = false;
/* 187 */       this.field_176007_z = false;
/* 188 */       this.field_153058_p = this.field_153045_c.getStatCallbacks();
/* 189 */       this.field_153045_c.setStatCallbacks(this.field_176006_B);
/* 190 */       this.field_153057_o = this.field_153045_c.getStreamCallbacks();
/* 191 */       this.field_153045_c.setStreamCallbacks(this.field_176005_A);
/* 192 */       this.field_153052_j = new VideoParams();
/* 193 */       this.field_153052_j.targetFps = 60;
/* 194 */       this.field_153052_j.maxKbps = 3500;
/* 195 */       this.field_153052_j.outputWidth = 1280;
/* 196 */       this.field_153052_j.outputHeight = 720;
/* 197 */       this.field_153052_j.pixelFormat = PixelFormat.TTV_PF_BGRA;
/* 198 */       this.field_153052_j.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
/* 199 */       this.field_153052_j.disableAdaptiveBitrate = true;
/* 200 */       this.field_153052_j.verticalFlip = false;
/* 201 */       this.field_153045_c.getDefaultParams(this.field_153052_j);
/* 202 */       this.field_153053_k = new AudioParams();
/* 203 */       this.field_153053_k.audioEnabled = false;
/* 204 */       this.field_153053_k.enableMicCapture = false;
/* 205 */       this.field_153053_k.enablePlaybackCapture = false;
/* 206 */       this.field_153053_k.enablePassthroughAudio = false;
/* 207 */       this.field_153055_m = Lists.newArrayList();
/* 208 */       byte var1 = 3;
/*     */       
/* 210 */       for (int var2 = 0; var2 < var1; var2++) {
/*     */         
/* 212 */         FrameBuffer var3 = this.field_153045_c.allocateFrameBuffer(this.field_153052_j.outputWidth * this.field_153052_j.outputHeight * 4);
/*     */         
/* 214 */         if (!var3.getIsValid()) {
/*     */           
/* 216 */           func_153031_o();
/* 217 */           func_153034_a(IngestTestState.Failed);
/*     */           
/*     */           return;
/*     */         } 
/* 221 */         this.field_153055_m.add(var3);
/* 222 */         this.field_153045_c.randomizeFrameBuffer(var3);
/*     */       } 
/*     */       
/* 225 */       func_153034_a(IngestTestState.Starting);
/* 226 */       this.field_153054_l = System.currentTimeMillis();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_153041_j() {
/* 232 */     if (!func_153032_e() && this.field_153047_e != IngestTestState.Uninitalized)
/*     */     {
/* 234 */       if (!this.field_176008_y && !this.field_176007_z) {
/*     */         
/* 236 */         switch (SwitchStatType.field_176002_b[this.field_153047_e.ordinal()]) {
/*     */           
/*     */           case 1:
/*     */           case 2:
/* 240 */             if (this.field_153059_q != null) {
/*     */               
/* 242 */               if (this.field_153061_s || !this.field_153056_n)
/*     */               {
/* 244 */                 this.field_153059_q.bitrateKbps = 0.0F;
/*     */               }
/*     */               
/* 247 */               func_153035_b(this.field_153059_q);
/*     */               
/*     */               break;
/*     */             } 
/* 251 */             this.field_153054_l = 0L;
/* 252 */             this.field_153061_s = false;
/* 253 */             this.field_153056_n = true;
/*     */             
/* 255 */             if (this.field_153047_e != IngestTestState.Starting)
/*     */             {
/* 257 */               this.field_153062_t++;
/*     */             }
/*     */             
/* 260 */             if (this.field_153062_t < (this.field_153046_d.getServers()).length) {
/*     */               
/* 262 */               this.field_153059_q = this.field_153046_d.getServers()[this.field_153062_t];
/* 263 */               func_153036_a(this.field_153059_q);
/*     */               
/*     */               break;
/*     */             } 
/* 267 */             func_153034_a(IngestTestState.Finished);
/*     */             break;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           case 3:
/*     */           case 4:
/* 275 */             func_153029_c(this.field_153059_q);
/*     */             break;
/*     */           
/*     */           case 5:
/* 279 */             func_153034_a(IngestTestState.Cancelled);
/*     */             break;
/*     */         } 
/* 282 */         func_153038_n();
/*     */         
/* 284 */         if (this.field_153047_e == IngestTestState.Cancelled || this.field_153047_e == IngestTestState.Finished)
/*     */         {
/* 286 */           func_153031_o();
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_153039_l() {
/* 294 */     if (!func_153032_e() && !this.field_153060_r) {
/*     */       
/* 296 */       this.field_153060_r = true;
/*     */       
/* 298 */       if (this.field_153059_q != null)
/*     */       {
/* 300 */         this.field_153059_q.bitrateKbps = 0.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_153036_a(IngestServer p_153036_1_) {
/* 307 */     this.field_153056_n = true;
/* 308 */     this.field_153050_h = 0L;
/* 309 */     this.field_153051_i = RTMPState.Idle;
/* 310 */     this.field_153059_q = p_153036_1_;
/* 311 */     this.field_176008_y = true;
/* 312 */     func_153034_a(IngestTestState.ConnectingToServer);
/* 313 */     ErrorCode var2 = this.field_153045_c.start(this.field_153052_j, this.field_153053_k, p_153036_1_, StartFlags.TTV_Start_BandwidthTest, true);
/*     */     
/* 315 */     if (ErrorCode.failed(var2)) {
/*     */       
/* 317 */       this.field_176008_y = false;
/* 318 */       this.field_153056_n = false;
/* 319 */       func_153034_a(IngestTestState.DoneTestingServer);
/* 320 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 324 */     this.field_153064_v = this.field_153050_h;
/* 325 */     p_153036_1_.bitrateKbps = 0.0F;
/* 326 */     this.field_153063_u = 0;
/* 327 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_153035_b(IngestServer p_153035_1_) {
/* 333 */     if (this.field_176008_y) {
/*     */       
/* 335 */       this.field_153061_s = true;
/*     */     }
/* 337 */     else if (this.field_176009_x) {
/*     */       
/* 339 */       this.field_176007_z = true;
/* 340 */       ErrorCode var2 = this.field_153045_c.stop(true);
/*     */       
/* 342 */       if (ErrorCode.failed(var2)) {
/*     */         
/* 344 */         this.field_176005_A.stopCallback(ErrorCode.TTV_EC_SUCCESS);
/* 345 */         System.out.println("Stop failed: " + var2.toString());
/*     */       } 
/*     */       
/* 348 */       this.field_153045_c.pollStats();
/*     */     }
/*     */     else {
/*     */       
/* 352 */       this.field_176005_A.stopCallback(ErrorCode.TTV_EC_SUCCESS);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected long func_153037_m() {
/* 358 */     return System.currentTimeMillis() - this.field_153054_l;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_153038_n() {
/* 363 */     float var1 = (float)func_153037_m();
/*     */     
/* 365 */     switch (SwitchStatType.field_176002_b[this.field_153047_e.ordinal()]) {
/*     */       
/*     */       case 1:
/*     */       case 3:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/* 373 */         this.field_153066_x = 0.0F;
/*     */         break;
/*     */       
/*     */       case 2:
/* 377 */         this.field_153066_x = 1.0F;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 383 */         this.field_153066_x = var1 / (float)this.field_153048_f;
/*     */         break;
/*     */     } 
/* 386 */     switch (SwitchStatType.field_176002_b[this.field_153047_e.ordinal()]) {
/*     */       
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/* 391 */         this.field_153065_w = 1.0F;
/*     */         return;
/*     */     } 
/*     */     
/* 395 */     this.field_153065_w = this.field_153062_t / (this.field_153046_d.getServers()).length;
/* 396 */     this.field_153065_w += this.field_153066_x / (this.field_153046_d.getServers()).length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_153029_c(IngestServer p_153029_1_) {
/* 402 */     if (!this.field_153061_s && !this.field_153060_r && func_153037_m() < this.field_153048_f) {
/*     */       
/* 404 */       if (!this.field_176008_y && !this.field_176007_z) {
/*     */         
/* 406 */         ErrorCode var2 = this.field_153045_c.submitVideoFrame(this.field_153055_m.get(this.field_153063_u));
/*     */         
/* 408 */         if (ErrorCode.failed(var2)) {
/*     */           
/* 410 */           this.field_153056_n = false;
/* 411 */           func_153034_a(IngestTestState.DoneTestingServer);
/* 412 */           return false;
/*     */         } 
/*     */ 
/*     */         
/* 416 */         this.field_153063_u = (this.field_153063_u + 1) % this.field_153055_m.size();
/* 417 */         this.field_153045_c.pollStats();
/*     */         
/* 419 */         if (this.field_153051_i == RTMPState.SendVideo) {
/*     */           
/* 421 */           func_153034_a(IngestTestState.TestingServer);
/* 422 */           long var3 = func_153037_m();
/*     */           
/* 424 */           if (var3 > 0L && this.field_153050_h > this.field_153064_v) {
/*     */             
/* 426 */             p_153029_1_.bitrateKbps = (float)(this.field_153050_h * 8L) / (float)func_153037_m();
/* 427 */             this.field_153064_v = this.field_153050_h;
/*     */           } 
/*     */         } 
/*     */         
/* 431 */         return true;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 436 */       return true;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 441 */     func_153034_a(IngestTestState.DoneTestingServer);
/* 442 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_153031_o() {
/* 448 */     this.field_153059_q = null;
/*     */     
/* 450 */     if (this.field_153055_m != null) {
/*     */       
/* 452 */       for (int var1 = 0; var1 < this.field_153055_m.size(); var1++)
/*     */       {
/* 454 */         ((FrameBuffer)this.field_153055_m.get(var1)).free();
/*     */       }
/*     */       
/* 457 */       this.field_153055_m = null;
/*     */     } 
/*     */     
/* 460 */     if (this.field_153045_c.getStatCallbacks() == this.field_176006_B) {
/*     */       
/* 462 */       this.field_153045_c.setStatCallbacks(this.field_153058_p);
/* 463 */       this.field_153058_p = null;
/*     */     } 
/*     */     
/* 466 */     if (this.field_153045_c.getStreamCallbacks() == this.field_176005_A) {
/*     */       
/* 468 */       this.field_153045_c.setStreamCallbacks(this.field_153057_o);
/* 469 */       this.field_153057_o = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_153034_a(IngestTestState p_153034_1_) {
/* 475 */     if (p_153034_1_ != this.field_153047_e) {
/*     */       
/* 477 */       this.field_153047_e = p_153034_1_;
/*     */       
/* 479 */       if (this.field_153044_b != null)
/*     */       {
/* 481 */         this.field_153044_b.func_152907_a(this, p_153034_1_);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface IngestTestListener
/*     */   {
/*     */     void func_152907_a(IngestServerTester param1IngestServerTester, IngestServerTester.IngestTestState param1IngestTestState);
/*     */   }
/*     */   
/*     */   public enum IngestTestState
/*     */   {
/* 493 */     Uninitalized("Uninitalized", 0),
/* 494 */     Starting("Starting", 1),
/* 495 */     ConnectingToServer("ConnectingToServer", 2),
/* 496 */     TestingServer("TestingServer", 3),
/* 497 */     DoneTestingServer("DoneTestingServer", 4),
/* 498 */     Finished("Finished", 5),
/* 499 */     Cancelling("Cancelling", 6),
/* 500 */     Cancelled("Cancelled", 7),
/* 501 */     Failed("Failed", 8);
/*     */     
/* 503 */     private static final IngestTestState[] $VALUES = new IngestTestState[] { Uninitalized, Starting, ConnectingToServer, TestingServer, DoneTestingServer, Finished, Cancelling, Cancelled, Failed };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private static final String __OBFID = "CL_00001814";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/*     */     
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
/*     */   static final class SwitchStatType
/*     */   {
/* 599 */     static final int[] field_176003_a = new int[(StatType.values()).length]; static final int[] field_176002_b = new int[(IngestServerTester.IngestTestState.values()).length]; private static final String __OBFID = "CL_00001815";
/*     */     
/*     */     static {
/*     */       try {
/* 603 */         field_176003_a[StatType.TTV_ST_RTMPSTATE.ordinal()] = 1;
/*     */       }
/* 605 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 612 */         field_176003_a[StatType.TTV_ST_RTMPDATASENT.ordinal()] = 2;
/*     */       }
/* 614 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\IngestServerTester.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */