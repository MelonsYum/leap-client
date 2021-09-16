/*     */ package net.minecraft.client.gui.stream;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.stream.IStream;
/*     */ import net.minecraft.client.stream.NullStream;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.Session;
/*     */ import net.minecraft.util.Util;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.opengl.GLContext;
/*     */ import tv.twitch.ErrorCode;
/*     */ 
/*     */ public class GuiStreamUnavailable
/*     */   extends GuiScreen {
/*  29 */   private static final Logger field_152322_a = LogManager.getLogger();
/*     */   
/*     */   private final IChatComponent field_152324_f;
/*     */   private final GuiScreen field_152325_g;
/*     */   private final Reason field_152326_h;
/*     */   private final List field_152327_i;
/*     */   private final List field_152323_r;
/*     */   private static final String __OBFID = "CL_00001840";
/*     */   
/*     */   public GuiStreamUnavailable(GuiScreen p_i1070_1_, Reason p_i1070_2_) {
/*  39 */     this(p_i1070_1_, p_i1070_2_, (List)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public GuiStreamUnavailable(GuiScreen p_i46311_1_, Reason p_i46311_2_, List p_i46311_3_) {
/*  44 */     this.field_152324_f = (IChatComponent)new ChatComponentTranslation("stream.unavailable.title", new Object[0]);
/*  45 */     this.field_152323_r = Lists.newArrayList();
/*  46 */     this.field_152325_g = p_i46311_1_;
/*  47 */     this.field_152326_h = p_i46311_2_;
/*  48 */     this.field_152327_i = p_i46311_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  56 */     if (this.field_152323_r.isEmpty()) {
/*     */       
/*  58 */       this.field_152323_r.addAll(fontRendererObj.listFormattedStringToWidth(this.field_152326_h.func_152561_a().getFormattedText(), (int)(width * 0.75F)));
/*     */       
/*  60 */       if (this.field_152327_i != null) {
/*     */         
/*  62 */         this.field_152323_r.add("");
/*  63 */         Iterator<ChatComponentTranslation> var1 = this.field_152327_i.iterator();
/*     */         
/*  65 */         while (var1.hasNext()) {
/*     */           
/*  67 */           ChatComponentTranslation var2 = var1.next();
/*  68 */           this.field_152323_r.add(var2.getUnformattedTextForChat());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  73 */     if (this.field_152326_h.func_152559_b() != null) {
/*     */       
/*  75 */       this.buttonList.add(new GuiButton(0, width / 2 - 155, height - 50, 150, 20, I18n.format("gui.cancel", new Object[0])));
/*  76 */       this.buttonList.add(new GuiButton(1, width / 2 - 155 + 160, height - 50, 150, 20, I18n.format(this.field_152326_h.func_152559_b().getFormattedText(), new Object[0])));
/*     */     }
/*     */     else {
/*     */       
/*  80 */       this.buttonList.add(new GuiButton(0, width / 2 - 75, height - 50, 150, 20, I18n.format("gui.cancel", new Object[0])));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  94 */     drawDefaultBackground();
/*  95 */     int var4 = Math.max((int)(height * 0.85D / 2.0D - ((this.field_152323_r.size() * fontRendererObj.FONT_HEIGHT) / 2.0F)), 50);
/*  96 */     drawCenteredString(fontRendererObj, this.field_152324_f.getFormattedText(), (width / 2), (var4 - fontRendererObj.FONT_HEIGHT * 2), 16777215);
/*     */     
/*  98 */     for (Iterator<String> var5 = this.field_152323_r.iterator(); var5.hasNext(); var4 += fontRendererObj.FONT_HEIGHT) {
/*     */       
/* 100 */       String var6 = var5.next();
/* 101 */       drawCenteredString(fontRendererObj, var6, (width / 2), var4, 10526880);
/*     */     } 
/*     */     
/* 104 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 109 */     if (button.enabled) {
/*     */       
/* 111 */       if (button.id == 1)
/*     */       {
/* 113 */         switch (SwitchReason.field_152577_a[this.field_152326_h.ordinal()]) {
/*     */           
/*     */           case 1:
/*     */           case 2:
/* 117 */             func_152320_a("https://account.mojang.com/me/settings");
/*     */             break;
/*     */           
/*     */           case 3:
/* 121 */             func_152320_a("https://account.mojang.com/migrate");
/*     */             break;
/*     */           
/*     */           case 4:
/* 125 */             func_152320_a("http://www.apple.com/osx/");
/*     */             break;
/*     */           
/*     */           case 5:
/*     */           case 6:
/*     */           case 7:
/* 131 */             func_152320_a("http://bugs.mojang.com/browse/MC");
/*     */             break;
/*     */         } 
/*     */       }
/* 135 */       this.mc.displayGuiScreen(this.field_152325_g);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_152320_a(String p_152320_1_) {
/*     */     try {
/* 143 */       Class<?> var2 = Class.forName("java.awt.Desktop");
/* 144 */       Object var3 = var2.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 145 */       var2.getMethod("browse", new Class[] { URI.class }).invoke(var3, new Object[] { new URI(p_152320_1_) });
/*     */     }
/* 147 */     catch (Throwable var4) {
/*     */       
/* 149 */       field_152322_a.error("Couldn't open link", var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void func_152321_a(GuiScreen p_152321_0_) {
/* 155 */     Minecraft var1 = Minecraft.getMinecraft();
/* 156 */     IStream var2 = var1.getTwitchStream();
/*     */     
/* 158 */     if (!OpenGlHelper.framebufferSupported) {
/*     */       
/* 160 */       ArrayList<ChatComponentTranslation> var3 = Lists.newArrayList();
/* 161 */       var3.add(new ChatComponentTranslation("stream.unavailable.no_fbo.version", new Object[] { GL11.glGetString(7938) }));
/* 162 */       var3.add(new ChatComponentTranslation("stream.unavailable.no_fbo.blend", new Object[] { Boolean.valueOf((GLContext.getCapabilities()).GL_EXT_blend_func_separate) }));
/* 163 */       var3.add(new ChatComponentTranslation("stream.unavailable.no_fbo.arb", new Object[] { Boolean.valueOf((GLContext.getCapabilities()).GL_ARB_framebuffer_object) }));
/* 164 */       var3.add(new ChatComponentTranslation("stream.unavailable.no_fbo.ext", new Object[] { Boolean.valueOf((GLContext.getCapabilities()).GL_EXT_framebuffer_object) }));
/* 165 */       var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.NO_FBO, var3));
/*     */     }
/* 167 */     else if (var2 instanceof NullStream) {
/*     */       
/* 169 */       if (((NullStream)var2).func_152937_a().getMessage().contains("Can't load AMD 64-bit .dll on a IA 32-bit platform"))
/*     */       {
/* 171 */         var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.LIBRARY_ARCH_MISMATCH));
/*     */       }
/*     */       else
/*     */       {
/* 175 */         var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.LIBRARY_FAILURE));
/*     */       }
/*     */     
/* 178 */     } else if (!var2.func_152928_D() && var2.func_152912_E() == ErrorCode.TTV_EC_OS_TOO_OLD) {
/*     */       
/* 180 */       switch (SwitchReason.field_152578_b[Util.getOSType().ordinal()]) {
/*     */         
/*     */         case 1:
/* 183 */           var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.UNSUPPORTED_OS_WINDOWS));
/*     */           return;
/*     */         
/*     */         case 2:
/* 187 */           var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.UNSUPPORTED_OS_MAC));
/*     */           return;
/*     */       } 
/*     */       
/* 191 */       var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.UNSUPPORTED_OS_OTHER));
/*     */     
/*     */     }
/* 194 */     else if (!var1.func_180509_L().containsKey("twitch_access_token")) {
/*     */       
/* 196 */       if (var1.getSession().getSessionType() == Session.Type.LEGACY)
/*     */       {
/* 198 */         var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.ACCOUNT_NOT_MIGRATED));
/*     */       }
/*     */       else
/*     */       {
/* 202 */         var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.ACCOUNT_NOT_BOUND));
/*     */       }
/*     */     
/* 205 */     } else if (!var2.func_152913_F()) {
/*     */       
/* 207 */       switch (SwitchReason.field_152579_c[var2.func_152918_H().ordinal()]) {
/*     */         
/*     */         case 1:
/* 210 */           var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.FAILED_TWITCH_AUTH));
/*     */           return;
/*     */       } 
/*     */ 
/*     */       
/* 215 */       var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.FAILED_TWITCH_AUTH_ERROR));
/*     */     
/*     */     }
/* 218 */     else if (var2.func_152912_E() != null) {
/*     */       
/* 220 */       List<ChatComponentTranslation> var4 = Arrays.asList(new ChatComponentTranslation[] { new ChatComponentTranslation("stream.unavailable.initialization_failure.extra", new Object[] { ErrorCode.getString(var2.func_152912_E()) }) });
/* 221 */       var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.INITIALIZATION_FAILURE, var4));
/*     */     }
/*     */     else {
/*     */       
/* 225 */       var1.displayGuiScreen(new GuiStreamUnavailable(p_152321_0_, Reason.UNKNOWN));
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum Reason
/*     */   {
/* 231 */     NO_FBO("NO_FBO", 0, (String)new ChatComponentTranslation("stream.unavailable.no_fbo", new Object[0])),
/* 232 */     LIBRARY_ARCH_MISMATCH("LIBRARY_ARCH_MISMATCH", 1, (String)new ChatComponentTranslation("stream.unavailable.library_arch_mismatch", new Object[0])),
/* 233 */     LIBRARY_FAILURE("LIBRARY_FAILURE", 2, (String)new ChatComponentTranslation("stream.unavailable.library_failure", new Object[0]), new ChatComponentTranslation("stream.unavailable.report_to_mojang", new Object[0])),
/* 234 */     UNSUPPORTED_OS_WINDOWS("UNSUPPORTED_OS_WINDOWS", 3, (String)new ChatComponentTranslation("stream.unavailable.not_supported.windows", new Object[0])),
/* 235 */     UNSUPPORTED_OS_MAC("UNSUPPORTED_OS_MAC", 4, (String)new ChatComponentTranslation("stream.unavailable.not_supported.mac", new Object[0]), new ChatComponentTranslation("stream.unavailable.not_supported.mac.okay", new Object[0])),
/* 236 */     UNSUPPORTED_OS_OTHER("UNSUPPORTED_OS_OTHER", 5, (String)new ChatComponentTranslation("stream.unavailable.not_supported.other", new Object[0])),
/* 237 */     ACCOUNT_NOT_MIGRATED("ACCOUNT_NOT_MIGRATED", 6, (String)new ChatComponentTranslation("stream.unavailable.account_not_migrated", new Object[0]), new ChatComponentTranslation("stream.unavailable.account_not_migrated.okay", new Object[0])),
/* 238 */     ACCOUNT_NOT_BOUND("ACCOUNT_NOT_BOUND", 7, (String)new ChatComponentTranslation("stream.unavailable.account_not_bound", new Object[0]), new ChatComponentTranslation("stream.unavailable.account_not_bound.okay", new Object[0])),
/* 239 */     FAILED_TWITCH_AUTH("FAILED_TWITCH_AUTH", 8, (String)new ChatComponentTranslation("stream.unavailable.failed_auth", new Object[0]), new ChatComponentTranslation("stream.unavailable.failed_auth.okay", new Object[0])),
/* 240 */     FAILED_TWITCH_AUTH_ERROR("FAILED_TWITCH_AUTH_ERROR", 9, (String)new ChatComponentTranslation("stream.unavailable.failed_auth_error", new Object[0])),
/* 241 */     INITIALIZATION_FAILURE("INITIALIZATION_FAILURE", 10, (String)new ChatComponentTranslation("stream.unavailable.initialization_failure", new Object[0]), new ChatComponentTranslation("stream.unavailable.report_to_mojang", new Object[0])),
/* 242 */     UNKNOWN("UNKNOWN", 11, (String)new ChatComponentTranslation("stream.unavailable.unknown", new Object[0]), new ChatComponentTranslation("stream.unavailable.report_to_mojang", new Object[0]));
/*     */     
/*     */     private final IChatComponent field_152574_m;
/*     */     private final IChatComponent field_152575_n;
/* 246 */     private static final Reason[] $VALUES = new Reason[] { NO_FBO, LIBRARY_ARCH_MISMATCH, LIBRARY_FAILURE, UNSUPPORTED_OS_WINDOWS, UNSUPPORTED_OS_MAC, UNSUPPORTED_OS_OTHER, ACCOUNT_NOT_MIGRATED, ACCOUNT_NOT_BOUND, FAILED_TWITCH_AUTH, FAILED_TWITCH_AUTH_ERROR, INITIALIZATION_FAILURE, UNKNOWN };
/*     */     
/*     */     private static final String __OBFID = "CL_00001838";
/*     */ 
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     Reason(String p_i1067_1_, int p_i1067_2_, IChatComponent p_i1067_3_, IChatComponent p_i1067_4_) {
/* 256 */       this.field_152574_m = p_i1067_3_;
/* 257 */       this.field_152575_n = p_i1067_4_;
/*     */     }
/*     */ 
/*     */     
/*     */     public IChatComponent func_152561_a() {
/* 262 */       return this.field_152574_m;
/*     */     }
/*     */ 
/*     */     
/*     */     public IChatComponent func_152559_b() {
/* 267 */       return this.field_152575_n;
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
/*     */   static final class SwitchReason
/*     */   {
/*     */     static final int[] field_152577_a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 300 */     static final int[] field_152578_b = new int[(Util.EnumOS.values()).length]; static final int[] field_152579_c = new int[(IStream.AuthFailureReason.values()).length];
/*     */     
/*     */     static {
/*     */       try {
/* 304 */         field_152578_b[Util.EnumOS.WINDOWS.ordinal()] = 1;
/*     */       }
/* 306 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 313 */         field_152578_b[Util.EnumOS.OSX.ordinal()] = 2;
/*     */       }
/* 315 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 320 */       field_152577_a = new int[(GuiStreamUnavailable.Reason.values()).length];
/*     */ 
/*     */       
/*     */       try {
/* 324 */         field_152577_a[GuiStreamUnavailable.Reason.ACCOUNT_NOT_BOUND.ordinal()] = 1;
/*     */       }
/* 326 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 333 */         field_152577_a[GuiStreamUnavailable.Reason.FAILED_TWITCH_AUTH.ordinal()] = 2;
/*     */       }
/* 335 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 342 */         field_152577_a[GuiStreamUnavailable.Reason.ACCOUNT_NOT_MIGRATED.ordinal()] = 3;
/*     */       }
/* 344 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 351 */         field_152577_a[GuiStreamUnavailable.Reason.UNSUPPORTED_OS_MAC.ordinal()] = 4;
/*     */       }
/* 353 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 360 */         field_152577_a[GuiStreamUnavailable.Reason.UNKNOWN.ordinal()] = 5;
/*     */       }
/* 362 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 369 */         field_152577_a[GuiStreamUnavailable.Reason.LIBRARY_FAILURE.ordinal()] = 6;
/*     */       }
/* 371 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 378 */         field_152577_a[GuiStreamUnavailable.Reason.INITIALIZATION_FAILURE.ordinal()] = 7;
/*     */       }
/* 380 */       catch (NoSuchFieldError noSuchFieldError) {}
/*     */     }
/*     */     
/*     */     private static final String __OBFID = "CL_00001839";
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\stream\GuiStreamUnavailable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */