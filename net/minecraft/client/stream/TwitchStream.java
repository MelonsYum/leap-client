/*     */ package net.minecraft.client.stream;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParser;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.stream.GuiTwitchUserMode;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.client.shader.Framebuffer;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import net.minecraft.event.HoverEvent;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.HttpUtil;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.JsonUtils;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Util;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import tv.twitch.AuthToken;
/*     */ import tv.twitch.ErrorCode;
/*     */ import tv.twitch.broadcast.EncodingCpuUsage;
/*     */ import tv.twitch.broadcast.FrameBuffer;
/*     */ import tv.twitch.broadcast.GameInfo;
/*     */ import tv.twitch.broadcast.IngestList;
/*     */ import tv.twitch.broadcast.IngestServer;
/*     */ import tv.twitch.broadcast.StreamInfo;
/*     */ import tv.twitch.broadcast.VideoParams;
/*     */ import tv.twitch.chat.ChatRawMessage;
/*     */ import tv.twitch.chat.ChatTokenizedMessage;
/*     */ import tv.twitch.chat.ChatUserInfo;
/*     */ import tv.twitch.chat.ChatUserMode;
/*     */ import tv.twitch.chat.ChatUserSubscription;
/*     */ 
/*     */ public class TwitchStream
/*     */   implements BroadcastController.BroadcastListener, ChatController.ChatListener, IngestServerTester.IngestTestListener, IStream {
/*  54 */   private static final Logger field_152950_b = LogManager.getLogger();
/*  55 */   public static final Marker field_152949_a = MarkerManager.getMarker("STREAM");
/*     */   private final BroadcastController broadcastController;
/*     */   private final ChatController field_152952_d;
/*     */   private String field_176029_e;
/*     */   private final Minecraft field_152953_e;
/*  60 */   private final IChatComponent field_152954_f = (IChatComponent)new ChatComponentText("Twitch");
/*  61 */   private final Map field_152955_g = Maps.newHashMap();
/*     */   private Framebuffer field_152956_h;
/*     */   private boolean field_152957_i;
/*  64 */   private int field_152958_j = 30;
/*  65 */   private long field_152959_k = 0L;
/*     */   
/*     */   private boolean field_152960_l = false;
/*     */   private boolean field_152961_m;
/*     */   private boolean field_152962_n;
/*     */   private boolean field_152963_o;
/*     */   private IStream.AuthFailureReason field_152964_p;
/*     */   private static boolean field_152965_q;
/*     */   private static final String __OBFID = "CL_00001812";
/*     */   
/*     */   public TwitchStream(Minecraft mcIn, final Property p_i46057_2_) {
/*  76 */     this.field_152964_p = IStream.AuthFailureReason.ERROR;
/*  77 */     this.field_152953_e = mcIn;
/*  78 */     this.broadcastController = new BroadcastController();
/*  79 */     this.field_152952_d = new ChatController();
/*  80 */     this.broadcastController.func_152841_a(this);
/*  81 */     this.field_152952_d.func_152990_a(this);
/*  82 */     this.broadcastController.func_152842_a("nmt37qblda36pvonovdkbopzfzw3wlq");
/*  83 */     this.field_152952_d.func_152984_a("nmt37qblda36pvonovdkbopzfzw3wlq");
/*  84 */     this.field_152954_f.getChatStyle().setColor(EnumChatFormatting.DARK_PURPLE);
/*     */     
/*  86 */     if (p_i46057_2_ != null && !Strings.isNullOrEmpty(p_i46057_2_.getValue()) && OpenGlHelper.framebufferSupported) {
/*     */       
/*  88 */       Thread var3 = new Thread("Twitch authenticator")
/*     */         {
/*     */           private static final String __OBFID = "CL_00001811";
/*     */ 
/*     */           
/*     */           public void run() {
/*     */             try {
/*  95 */               URL var1 = new URL("https://api.twitch.tv/kraken?oauth_token=" + URLEncoder.encode(p_i46057_2_.getValue(), "UTF-8"));
/*  96 */               String var2 = HttpUtil.get(var1);
/*  97 */               JsonObject var3 = JsonUtils.getElementAsJsonObject((new JsonParser()).parse(var2), "Response");
/*  98 */               JsonObject var4 = JsonUtils.getJsonObject(var3, "token");
/*     */               
/* 100 */               if (JsonUtils.getJsonObjectBooleanFieldValue(var4, "valid"))
/*     */               {
/* 102 */                 String var5 = JsonUtils.getJsonObjectStringFieldValue(var4, "user_name");
/* 103 */                 TwitchStream.field_152950_b.debug(TwitchStream.field_152949_a, "Authenticated with twitch; username is {}", new Object[] { var5 });
/* 104 */                 AuthToken var6 = new AuthToken();
/* 105 */                 var6.data = p_i46057_2_.getValue();
/* 106 */                 TwitchStream.this.broadcastController.func_152818_a(var5, var6);
/* 107 */                 TwitchStream.this.field_152952_d.func_152998_c(var5);
/* 108 */                 TwitchStream.this.field_152952_d.func_152994_a(var6);
/* 109 */                 Runtime.getRuntime().addShutdownHook(new Thread("Twitch shutdown hook")
/*     */                     {
/*     */                       private static final String __OBFID = "CL_00001810";
/*     */                       
/*     */                       public void run() {
/* 114 */                         TwitchStream.null.access$0(TwitchStream.null.this).shutdownStream();
/*     */                       }
/*     */                     });
/* 117 */                 TwitchStream.this.broadcastController.func_152817_A();
/* 118 */                 TwitchStream.this.field_152952_d.func_175984_n();
/*     */               }
/*     */               else
/*     */               {
/* 122 */                 TwitchStream.this.field_152964_p = IStream.AuthFailureReason.INVALID_TOKEN;
/* 123 */                 TwitchStream.field_152950_b.error(TwitchStream.field_152949_a, "Given twitch access token is invalid");
/*     */               }
/*     */             
/* 126 */             } catch (IOException var7) {
/*     */               
/* 128 */               TwitchStream.this.field_152964_p = IStream.AuthFailureReason.ERROR;
/* 129 */               TwitchStream.field_152950_b.error(TwitchStream.field_152949_a, "Could not authenticate with twitch", var7);
/*     */             } 
/*     */           }
/*     */         };
/* 133 */       var3.setDaemon(true);
/* 134 */       var3.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdownStream() {
/* 143 */     field_152950_b.debug(field_152949_a, "Shutdown streaming");
/* 144 */     this.broadcastController.statCallback();
/* 145 */     this.field_152952_d.func_175988_p();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152935_j() {
/* 150 */     int var1 = this.field_152953_e.gameSettings.streamChatEnabled;
/* 151 */     boolean var2 = (this.field_176029_e != null && this.field_152952_d.func_175990_d(this.field_176029_e));
/* 152 */     boolean var3 = (this.field_152952_d.func_153000_j() == ChatController.ChatState.Initialized && (this.field_176029_e == null || this.field_152952_d.func_175989_e(this.field_176029_e) == ChatController.EnumChannelState.Disconnected));
/*     */     
/* 154 */     if (var1 == 2) {
/*     */       
/* 156 */       if (var2)
/*     */       {
/* 158 */         field_152950_b.debug(field_152949_a, "Disconnecting from twitch chat per user options");
/* 159 */         this.field_152952_d.func_175991_l(this.field_176029_e);
/*     */       }
/*     */     
/* 162 */     } else if (var1 == 1) {
/*     */       
/* 164 */       if (var3 && this.broadcastController.func_152849_q())
/*     */       {
/* 166 */         field_152950_b.debug(field_152949_a, "Connecting to twitch chat per user options");
/* 167 */         func_152942_I();
/*     */       }
/*     */     
/* 170 */     } else if (var1 == 0) {
/*     */       
/* 172 */       if (var2 && !func_152934_n()) {
/*     */         
/* 174 */         field_152950_b.debug(field_152949_a, "Disconnecting from twitch chat as user is no longer streaming");
/* 175 */         this.field_152952_d.func_175991_l(this.field_176029_e);
/*     */       }
/* 177 */       else if (var3 && func_152934_n()) {
/*     */         
/* 179 */         field_152950_b.debug(field_152949_a, "Connecting to twitch chat as user is streaming");
/* 180 */         func_152942_I();
/*     */       } 
/*     */     } 
/*     */     
/* 184 */     this.broadcastController.func_152821_H();
/* 185 */     this.field_152952_d.func_152997_n();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_152942_I() {
/* 190 */     ChatController.ChatState var1 = this.field_152952_d.func_153000_j();
/* 191 */     String var2 = (this.broadcastController.func_152843_l()).name;
/* 192 */     this.field_176029_e = var2;
/*     */     
/* 194 */     if (var1 != ChatController.ChatState.Initialized) {
/*     */       
/* 196 */       field_152950_b.warn("Invalid twitch chat state {}", new Object[] { var1 });
/*     */     }
/* 198 */     else if (this.field_152952_d.func_175989_e(this.field_176029_e) == ChatController.EnumChannelState.Disconnected) {
/*     */       
/* 200 */       this.field_152952_d.func_152986_d(var2);
/*     */     }
/*     */     else {
/*     */       
/* 204 */       field_152950_b.warn("Invalid twitch chat state {}", new Object[] { var1 });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152922_k() {
/* 210 */     if (this.broadcastController.isBroadcasting() && !this.broadcastController.isBroadcastPaused()) {
/*     */       
/* 212 */       long var1 = System.nanoTime();
/* 213 */       long var3 = (1000000000 / this.field_152958_j);
/* 214 */       long var5 = var1 - this.field_152959_k;
/* 215 */       boolean var7 = (var5 >= var3);
/*     */       
/* 217 */       if (var7) {
/*     */         
/* 219 */         FrameBuffer var8 = this.broadcastController.func_152822_N();
/* 220 */         Framebuffer var9 = this.field_152953_e.getFramebuffer();
/* 221 */         this.field_152956_h.bindFramebuffer(true);
/* 222 */         GlStateManager.matrixMode(5889);
/* 223 */         GlStateManager.pushMatrix();
/* 224 */         GlStateManager.loadIdentity();
/* 225 */         GlStateManager.ortho(0.0D, this.field_152956_h.framebufferWidth, this.field_152956_h.framebufferHeight, 0.0D, 1000.0D, 3000.0D);
/* 226 */         GlStateManager.matrixMode(5888);
/* 227 */         GlStateManager.pushMatrix();
/* 228 */         GlStateManager.loadIdentity();
/* 229 */         GlStateManager.translate(0.0F, 0.0F, -2000.0F);
/* 230 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 231 */         GlStateManager.viewport(0, 0, this.field_152956_h.framebufferWidth, this.field_152956_h.framebufferHeight);
/* 232 */         GlStateManager.func_179098_w();
/* 233 */         GlStateManager.disableAlpha();
/* 234 */         GlStateManager.disableBlend();
/* 235 */         float var10 = this.field_152956_h.framebufferWidth;
/* 236 */         float var11 = this.field_152956_h.framebufferHeight;
/* 237 */         float var12 = var9.framebufferWidth / var9.framebufferTextureWidth;
/* 238 */         float var13 = var9.framebufferHeight / var9.framebufferTextureHeight;
/* 239 */         var9.bindFramebufferTexture();
/* 240 */         GL11.glTexParameterf(3553, 10241, 9729.0F);
/* 241 */         GL11.glTexParameterf(3553, 10240, 9729.0F);
/* 242 */         Tessellator var14 = Tessellator.getInstance();
/* 243 */         WorldRenderer var15 = var14.getWorldRenderer();
/* 244 */         var15.startDrawingQuads();
/* 245 */         var15.addVertexWithUV(0.0D, var11, 0.0D, 0.0D, var13);
/* 246 */         var15.addVertexWithUV(var10, var11, 0.0D, var12, var13);
/* 247 */         var15.addVertexWithUV(var10, 0.0D, 0.0D, var12, 0.0D);
/* 248 */         var15.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/* 249 */         var14.draw();
/* 250 */         var9.unbindFramebufferTexture();
/* 251 */         GlStateManager.popMatrix();
/* 252 */         GlStateManager.matrixMode(5889);
/* 253 */         GlStateManager.popMatrix();
/* 254 */         GlStateManager.matrixMode(5888);
/* 255 */         this.broadcastController.func_152846_a(var8);
/* 256 */         this.field_152956_h.unbindFramebuffer();
/* 257 */         this.broadcastController.func_152859_b(var8);
/* 258 */         this.field_152959_k = var1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152936_l() {
/* 265 */     return this.broadcastController.func_152849_q();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152924_m() {
/* 270 */     return this.broadcastController.func_152857_n();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152934_n() {
/* 275 */     return this.broadcastController.isBroadcasting();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152911_a(Metadata p_152911_1_, long p_152911_2_) {
/* 280 */     if (func_152934_n() && this.field_152957_i) {
/*     */       
/* 282 */       long var4 = this.broadcastController.func_152844_x();
/*     */       
/* 284 */       if (!this.broadcastController.func_152840_a(p_152911_1_.func_152810_c(), var4 + p_152911_2_, p_152911_1_.func_152809_a(), p_152911_1_.func_152806_b())) {
/*     */         
/* 286 */         field_152950_b.warn(field_152949_a, "Couldn't send stream metadata action at {}: {}", new Object[] { Long.valueOf(var4 + p_152911_2_), p_152911_1_ });
/*     */       }
/*     */       else {
/*     */         
/* 290 */         field_152950_b.debug(field_152949_a, "Sent stream metadata action at {}: {}", new Object[] { Long.valueOf(var4 + p_152911_2_), p_152911_1_ });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176026_a(Metadata p_176026_1_, long p_176026_2_, long p_176026_4_) {
/* 297 */     if (func_152934_n() && this.field_152957_i) {
/*     */       
/* 299 */       long var6 = this.broadcastController.func_152844_x();
/* 300 */       String var8 = p_176026_1_.func_152809_a();
/* 301 */       String var9 = p_176026_1_.func_152806_b();
/* 302 */       long var10 = this.broadcastController.func_177946_b(p_176026_1_.func_152810_c(), var6 + p_176026_2_, var8, var9);
/*     */       
/* 304 */       if (var10 < 0L) {
/*     */         
/* 306 */         field_152950_b.warn(field_152949_a, "Could not send stream metadata sequence from {} to {}: {}", new Object[] { Long.valueOf(var6 + p_176026_2_), Long.valueOf(var6 + p_176026_4_), p_176026_1_ });
/*     */       }
/* 308 */       else if (this.broadcastController.func_177947_a(p_176026_1_.func_152810_c(), var6 + p_176026_4_, var10, var8, var9)) {
/*     */         
/* 310 */         field_152950_b.debug(field_152949_a, "Sent stream metadata sequence from {} to {}: {}", new Object[] { Long.valueOf(var6 + p_176026_2_), Long.valueOf(var6 + p_176026_4_), p_176026_1_ });
/*     */       }
/*     */       else {
/*     */         
/* 314 */         field_152950_b.warn(field_152949_a, "Half-sent stream metadata sequence from {} to {}: {}", new Object[] { Long.valueOf(var6 + p_176026_2_), Long.valueOf(var6 + p_176026_4_), p_176026_1_ });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPaused() {
/* 321 */     return this.broadcastController.isBroadcastPaused();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152931_p() {
/* 326 */     if (this.broadcastController.func_152830_D()) {
/*     */       
/* 328 */       field_152950_b.debug(field_152949_a, "Requested commercial from Twitch");
/*     */     }
/*     */     else {
/*     */       
/* 332 */       field_152950_b.warn(field_152949_a, "Could not request commercial from Twitch");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152916_q() {
/* 338 */     this.broadcastController.func_152847_F();
/* 339 */     this.field_152962_n = true;
/* 340 */     func_152915_s();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152933_r() {
/* 345 */     this.broadcastController.func_152854_G();
/* 346 */     this.field_152962_n = false;
/* 347 */     func_152915_s();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152915_s() {
/* 352 */     if (func_152934_n()) {
/*     */       
/* 354 */       float var1 = this.field_152953_e.gameSettings.streamGameVolume;
/* 355 */       boolean var2 = !(!this.field_152962_n && var1 > 0.0F);
/* 356 */       this.broadcastController.func_152837_b(var2 ? 0.0F : var1);
/* 357 */       this.broadcastController.func_152829_a(func_152929_G() ? 0.0F : this.field_152953_e.gameSettings.streamMicVolume);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152930_t() {
/* 363 */     GameSettings var1 = this.field_152953_e.gameSettings;
/* 364 */     VideoParams var2 = this.broadcastController.func_152834_a(func_152946_b(var1.streamKbps), func_152948_a(var1.streamFps), func_152947_c(var1.streamBytesPerPixel), this.field_152953_e.displayWidth / this.field_152953_e.displayHeight);
/*     */     
/* 366 */     switch (var1.streamCompression) {
/*     */       
/*     */       case 0:
/* 369 */         var2.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_LOW;
/*     */         break;
/*     */       
/*     */       case 1:
/* 373 */         var2.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_MEDIUM;
/*     */         break;
/*     */       
/*     */       case 2:
/* 377 */         var2.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
/*     */         break;
/*     */     } 
/* 380 */     if (this.field_152956_h == null) {
/*     */       
/* 382 */       this.field_152956_h = new Framebuffer(var2.outputWidth, var2.outputHeight, false);
/*     */     }
/*     */     else {
/*     */       
/* 386 */       this.field_152956_h.createBindFramebuffer(var2.outputWidth, var2.outputHeight);
/*     */     } 
/*     */     
/* 389 */     if (var1.streamPreferredServer != null && var1.streamPreferredServer.length() > 0) {
/*     */       
/* 391 */       IngestServer[] var3 = func_152925_v();
/* 392 */       int var4 = var3.length;
/*     */       
/* 394 */       for (int var5 = 0; var5 < var4; var5++) {
/*     */         
/* 396 */         IngestServer var6 = var3[var5];
/*     */         
/* 398 */         if (var6.serverUrl.equals(var1.streamPreferredServer)) {
/*     */           
/* 400 */           this.broadcastController.func_152824_a(var6);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 406 */     this.field_152958_j = var2.targetFps;
/* 407 */     this.field_152957_i = var1.streamSendMetadata;
/* 408 */     this.broadcastController.func_152836_a(var2);
/* 409 */     field_152950_b.info(field_152949_a, "Streaming at {}/{} at {} kbps to {}", new Object[] { Integer.valueOf(var2.outputWidth), Integer.valueOf(var2.outputHeight), Integer.valueOf(var2.maxKbps), (this.broadcastController.func_152833_s()).serverUrl });
/* 410 */     this.broadcastController.func_152828_a(null, "Minecraft", null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152914_u() {
/* 415 */     if (this.broadcastController.func_152819_E()) {
/*     */       
/* 417 */       field_152950_b.info(field_152949_a, "Stopped streaming to Twitch");
/*     */     }
/*     */     else {
/*     */       
/* 421 */       field_152950_b.warn(field_152949_a, "Could not stop streaming to Twitch");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152900_a(ErrorCode p_152900_1_, AuthToken p_152900_2_) {}
/*     */   
/*     */   public void func_152897_a(ErrorCode p_152897_1_) {
/* 429 */     if (ErrorCode.succeeded(p_152897_1_)) {
/*     */       
/* 431 */       field_152950_b.debug(field_152949_a, "Login attempt successful");
/* 432 */       this.field_152961_m = true;
/*     */     }
/*     */     else {
/*     */       
/* 436 */       field_152950_b.warn(field_152949_a, "Login attempt unsuccessful: {} (error code {})", new Object[] { ErrorCode.getString(p_152897_1_), Integer.valueOf(p_152897_1_.getValue()) });
/* 437 */       this.field_152961_m = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152898_a(ErrorCode p_152898_1_, GameInfo[] p_152898_2_) {}
/*     */   
/*     */   public void func_152891_a(BroadcastController.BroadcastState p_152891_1_) {
/* 445 */     field_152950_b.debug(field_152949_a, "Broadcast state changed to {}", new Object[] { p_152891_1_ });
/*     */     
/* 447 */     if (p_152891_1_ == BroadcastController.BroadcastState.Initialized)
/*     */     {
/* 449 */       this.broadcastController.func_152827_a(BroadcastController.BroadcastState.Authenticated);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152895_a() {
/* 455 */     field_152950_b.info(field_152949_a, "Logged out of twitch");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152894_a(StreamInfo p_152894_1_) {
/* 460 */     field_152950_b.debug(field_152949_a, "Stream info updated; {} viewers on stream ID {}", new Object[] { Integer.valueOf(p_152894_1_.viewers), Long.valueOf(p_152894_1_.streamId) });
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152896_a(IngestList p_152896_1_) {}
/*     */   
/*     */   public void func_152893_b(ErrorCode p_152893_1_) {
/* 467 */     field_152950_b.warn(field_152949_a, "Issue submitting frame: {} (Error code {})", new Object[] { ErrorCode.getString(p_152893_1_), Integer.valueOf(p_152893_1_.getValue()) });
/* 468 */     this.field_152953_e.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((IChatComponent)new ChatComponentText("Issue streaming frame: " + p_152893_1_ + " (" + ErrorCode.getString(p_152893_1_) + ")"), 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152899_b() {
/* 473 */     func_152915_s();
/* 474 */     field_152950_b.info(field_152949_a, "Broadcast to Twitch has started");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152901_c() {
/* 479 */     field_152950_b.info(field_152949_a, "Broadcast to Twitch has stopped");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_152892_c(ErrorCode p_152892_1_) {
/* 486 */     if (p_152892_1_ == ErrorCode.TTV_EC_SOUNDFLOWER_NOT_INSTALLED) {
/*     */       
/* 488 */       ChatComponentTranslation var2 = new ChatComponentTranslation("stream.unavailable.soundflower.chat.link", new Object[0]);
/* 489 */       var2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://help.mojang.com/customer/portal/articles/1374877-configuring-soundflower-for-streaming-on-apple-computers"));
/* 490 */       var2.getChatStyle().setUnderlined(Boolean.valueOf(true));
/* 491 */       ChatComponentTranslation var3 = new ChatComponentTranslation("stream.unavailable.soundflower.chat", new Object[] { var2 });
/* 492 */       var3.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
/* 493 */       this.field_152953_e.ingameGUI.getChatGUI().printChatMessage((IChatComponent)var3);
/*     */     }
/*     */     else {
/*     */       
/* 497 */       ChatComponentTranslation var2 = new ChatComponentTranslation("stream.unavailable.unknown.chat", new Object[] { ErrorCode.getString(p_152892_1_) });
/* 498 */       var2.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
/* 499 */       this.field_152953_e.ingameGUI.getChatGUI().printChatMessage((IChatComponent)var2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152907_a(IngestServerTester p_152907_1_, IngestServerTester.IngestTestState p_152907_2_) {
/* 505 */     field_152950_b.debug(field_152949_a, "Ingest test state changed to {}", new Object[] { p_152907_2_ });
/*     */     
/* 507 */     if (p_152907_2_ == IngestServerTester.IngestTestState.Finished)
/*     */     {
/* 509 */       this.field_152960_l = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_152948_a(float p_152948_0_) {
/* 515 */     return MathHelper.floor_float(10.0F + p_152948_0_ * 50.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_152946_b(float p_152946_0_) {
/* 520 */     return MathHelper.floor_float(230.0F + p_152946_0_ * 3270.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float func_152947_c(float p_152947_0_) {
/* 525 */     return 0.1F + p_152947_0_ * 0.1F;
/*     */   }
/*     */ 
/*     */   
/*     */   public IngestServer[] func_152925_v() {
/* 530 */     return this.broadcastController.func_152855_t().getServers();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152909_x() {
/* 535 */     IngestServerTester var1 = this.broadcastController.func_152838_J();
/*     */     
/* 537 */     if (var1 != null)
/*     */     {
/* 539 */       var1.func_153042_a(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IngestServerTester func_152932_y() {
/* 545 */     return this.broadcastController.isReady();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152908_z() {
/* 550 */     return this.broadcastController.isIngestTesting();
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_152920_A() {
/* 555 */     return func_152934_n() ? (this.broadcastController.func_152816_j()).viewers : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176023_d(ErrorCode p_176023_1_) {
/* 560 */     if (ErrorCode.failed(p_176023_1_))
/*     */     {
/* 562 */       field_152950_b.error(field_152949_a, "Chat failed to initialize");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176022_e(ErrorCode p_176022_1_) {
/* 568 */     if (ErrorCode.failed(p_176022_1_))
/*     */     {
/* 570 */       field_152950_b.error(field_152949_a, "Chat failed to shutdown");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176017_a(ChatController.ChatState p_176017_1_) {}
/*     */   
/*     */   public void func_180605_a(String p_180605_1_, ChatRawMessage[] p_180605_2_) {
/* 578 */     ChatRawMessage[] var3 = p_180605_2_;
/* 579 */     int var4 = p_180605_2_.length;
/*     */     
/* 581 */     for (int var5 = 0; var5 < var4; var5++) {
/*     */       
/* 583 */       ChatRawMessage var6 = var3[var5];
/* 584 */       func_176027_a(var6.userName, var6);
/*     */       
/* 586 */       if (func_176028_a(var6.modes, var6.subscriptions, this.field_152953_e.gameSettings.streamChatUserFilter)) {
/*     */         
/* 588 */         ChatComponentText var7 = new ChatComponentText(var6.userName);
/* 589 */         ChatComponentTranslation var8 = new ChatComponentTranslation("chat.stream." + (var6.action ? "emote" : "text"), new Object[] { this.field_152954_f, var7, EnumChatFormatting.getTextWithoutFormattingCodes(var6.message) });
/*     */         
/* 591 */         if (var6.action)
/*     */         {
/* 593 */           var8.getChatStyle().setItalic(Boolean.valueOf(true));
/*     */         }
/*     */         
/* 596 */         ChatComponentText var9 = new ChatComponentText("");
/* 597 */         var9.appendSibling((IChatComponent)new ChatComponentTranslation("stream.userinfo.chatTooltip", new Object[0]));
/* 598 */         Iterator<IChatComponent> var10 = GuiTwitchUserMode.func_152328_a(var6.modes, var6.subscriptions, null).iterator();
/*     */         
/* 600 */         while (var10.hasNext()) {
/*     */           
/* 602 */           IChatComponent var11 = var10.next();
/* 603 */           var9.appendText("\n");
/* 604 */           var9.appendSibling(var11);
/*     */         } 
/*     */         
/* 607 */         var7.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (IChatComponent)var9));
/* 608 */         var7.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.TWITCH_USER_INFO, var6.userName));
/* 609 */         this.field_152953_e.ingameGUI.getChatGUI().printChatMessage((IChatComponent)var8);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176025_a(String p_176025_1_, ChatTokenizedMessage[] p_176025_2_) {}
/*     */   
/*     */   private void func_176027_a(String p_176027_1_, ChatRawMessage p_176027_2_) {
/* 618 */     ChatUserInfo var3 = (ChatUserInfo)this.field_152955_g.get(p_176027_1_);
/*     */     
/* 620 */     if (var3 == null) {
/*     */       
/* 622 */       var3 = new ChatUserInfo();
/* 623 */       var3.displayName = p_176027_1_;
/* 624 */       this.field_152955_g.put(p_176027_1_, var3);
/*     */     } 
/*     */     
/* 627 */     var3.subscriptions = p_176027_2_.subscriptions;
/* 628 */     var3.modes = p_176027_2_.modes;
/* 629 */     var3.nameColorARGB = p_176027_2_.nameColorARGB;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_176028_a(Set p_176028_1_, Set p_176028_2_, int p_176028_3_) {
/* 634 */     return p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_BANNED) ? false : (p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR) ? true : (p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_MODERATOR) ? true : (p_176028_1_.contains(ChatUserMode.TTV_CHAT_USERMODE_STAFF) ? true : ((p_176028_3_ == 0) ? true : ((p_176028_3_ == 1) ? p_176028_2_.contains(ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER) : false)))));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176018_a(String p_176018_1_, ChatUserInfo[] p_176018_2_, ChatUserInfo[] p_176018_3_, ChatUserInfo[] p_176018_4_) {
/* 639 */     ChatUserInfo[] var5 = p_176018_3_;
/* 640 */     int var6 = p_176018_3_.length;
/*     */     
/*     */     int var7;
/*     */     
/* 644 */     for (var7 = 0; var7 < var6; var7++) {
/*     */       
/* 646 */       ChatUserInfo var8 = var5[var7];
/* 647 */       this.field_152955_g.remove(var8.displayName);
/*     */     } 
/*     */     
/* 650 */     var5 = p_176018_4_;
/* 651 */     var6 = p_176018_4_.length;
/*     */     
/* 653 */     for (var7 = 0; var7 < var6; var7++) {
/*     */       
/* 655 */       ChatUserInfo var8 = var5[var7];
/* 656 */       this.field_152955_g.put(var8.displayName, var8);
/*     */     } 
/*     */     
/* 659 */     var5 = p_176018_2_;
/* 660 */     var6 = p_176018_2_.length;
/*     */     
/* 662 */     for (var7 = 0; var7 < var6; var7++) {
/*     */       
/* 664 */       ChatUserInfo var8 = var5[var7];
/* 665 */       this.field_152955_g.put(var8.displayName, var8);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180606_a(String p_180606_1_) {
/* 671 */     field_152950_b.debug(field_152949_a, "Chat connected");
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_180607_b(String p_180607_1_) {
/* 676 */     field_152950_b.debug(field_152949_a, "Chat disconnected");
/* 677 */     this.field_152955_g.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_176019_a(String p_176019_1_, String p_176019_2_) {}
/*     */   
/*     */   public void func_176021_d() {}
/*     */   
/*     */   public void func_176024_e() {}
/*     */   
/*     */   public void func_176016_c(String p_176016_1_) {}
/*     */   
/*     */   public void func_176020_d(String p_176020_1_) {}
/*     */   
/*     */   public boolean func_152927_B() {
/* 692 */     return (this.field_176029_e != null && this.field_176029_e.equals((this.broadcastController.func_152843_l()).name));
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_152921_C() {
/* 697 */     return this.field_176029_e;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatUserInfo func_152926_a(String p_152926_1_) {
/* 702 */     return (ChatUserInfo)this.field_152955_g.get(p_152926_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152917_b(String p_152917_1_) {
/* 707 */     this.field_152952_d.func_175986_a(this.field_176029_e, p_152917_1_);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152928_D() {
/* 712 */     return (field_152965_q && this.broadcastController.func_152858_b());
/*     */   }
/*     */ 
/*     */   
/*     */   public ErrorCode func_152912_E() {
/* 717 */     return !field_152965_q ? ErrorCode.TTV_EC_OS_TOO_OLD : this.broadcastController.func_152852_P();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152913_F() {
/* 722 */     return this.field_152961_m;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_152910_a(boolean p_152910_1_) {
/* 727 */     this.field_152963_o = p_152910_1_;
/* 728 */     func_152915_s();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_152929_G() {
/* 733 */     boolean var1 = (this.field_152953_e.gameSettings.streamMicToggleBehavior == 1);
/* 734 */     return !(!this.field_152962_n && this.field_152953_e.gameSettings.streamMicVolume > 0.0F && var1 == this.field_152963_o);
/*     */   }
/*     */ 
/*     */   
/*     */   public IStream.AuthFailureReason func_152918_H() {
/* 739 */     return this.field_152964_p;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/* 746 */       if (Util.getOSType() == Util.EnumOS.WINDOWS) {
/*     */         
/* 748 */         System.loadLibrary("avutil-ttv-51");
/* 749 */         System.loadLibrary("swresample-ttv-0");
/* 750 */         System.loadLibrary("libmp3lame-ttv");
/*     */         
/* 752 */         if (System.getProperty("os.arch").contains("64")) {
/*     */           
/* 754 */           System.loadLibrary("libmfxsw64");
/*     */         }
/*     */         else {
/*     */           
/* 758 */           System.loadLibrary("libmfxsw32");
/*     */         } 
/*     */       } 
/*     */       
/* 762 */       field_152965_q = true;
/*     */     }
/* 764 */     catch (Throwable var1) {
/*     */       
/* 766 */       field_152965_q = false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\stream\TwitchStream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */