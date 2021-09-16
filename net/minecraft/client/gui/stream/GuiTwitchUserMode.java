/*     */ package net.minecraft.client.gui.stream;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.stream.IStream;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import tv.twitch.chat.ChatUserInfo;
/*     */ import tv.twitch.chat.ChatUserMode;
/*     */ import tv.twitch.chat.ChatUserSubscription;
/*     */ 
/*     */ public class GuiTwitchUserMode
/*     */   extends GuiScreen {
/*  23 */   private static final EnumChatFormatting field_152331_a = EnumChatFormatting.DARK_GREEN;
/*  24 */   private static final EnumChatFormatting field_152335_f = EnumChatFormatting.RED;
/*  25 */   private static final EnumChatFormatting field_152336_g = EnumChatFormatting.DARK_PURPLE;
/*     */   private final ChatUserInfo field_152337_h;
/*     */   private final IChatComponent field_152338_i;
/*  28 */   private final List field_152332_r = Lists.newArrayList();
/*     */   
/*     */   private final IStream field_152333_s;
/*     */   private int field_152334_t;
/*     */   private static final String __OBFID = "CL_00001837";
/*     */   
/*     */   public GuiTwitchUserMode(IStream p_i1064_1_, ChatUserInfo p_i1064_2_) {
/*  35 */     this.field_152333_s = p_i1064_1_;
/*  36 */     this.field_152337_h = p_i1064_2_;
/*  37 */     this.field_152338_i = (IChatComponent)new ChatComponentText(p_i1064_2_.displayName);
/*  38 */     this.field_152332_r.addAll(func_152328_a(p_i1064_2_.modes, p_i1064_2_.subscriptions, p_i1064_1_));
/*     */   }
/*     */ 
/*     */   
/*     */   public static List func_152328_a(Set p_152328_0_, Set<ChatUserMode> p_152328_1_, IStream p_152328_2_) {
/*  43 */     String var3 = (p_152328_2_ == null) ? null : p_152328_2_.func_152921_C();
/*  44 */     boolean var4 = (p_152328_2_ != null && p_152328_2_.func_152927_B());
/*  45 */     ArrayList<ChatComponentText> var5 = Lists.newArrayList();
/*  46 */     Iterator<ChatUserMode> var6 = p_152328_0_.iterator();
/*     */ 
/*     */ 
/*     */     
/*  50 */     while (var6.hasNext()) {
/*     */       
/*  52 */       ChatUserMode var7 = var6.next();
/*  53 */       IChatComponent var8 = func_152329_a(var7, var3, var4);
/*     */       
/*  55 */       if (var8 != null) {
/*     */         
/*  57 */         ChatComponentText var9 = new ChatComponentText("- ");
/*  58 */         var9.appendSibling(var8);
/*  59 */         var5.add(var9);
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     var6 = p_152328_1_.iterator();
/*     */     
/*  65 */     while (var6.hasNext()) {
/*     */       
/*  67 */       ChatUserSubscription var10 = (ChatUserSubscription)var6.next();
/*  68 */       IChatComponent var8 = func_152330_a(var10, var3, var4);
/*     */       
/*  70 */       if (var8 != null) {
/*     */         
/*  72 */         ChatComponentText var9 = new ChatComponentText("- ");
/*  73 */         var9.appendSibling(var8);
/*  74 */         var5.add(var9);
/*     */       } 
/*     */     } 
/*     */     
/*  78 */     return var5;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IChatComponent func_152330_a(ChatUserSubscription p_152330_0_, String p_152330_1_, boolean p_152330_2_) {
/*  83 */     ChatComponentTranslation var3 = null;
/*     */     
/*  85 */     if (p_152330_0_ == ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER) {
/*     */       
/*  87 */       if (p_152330_1_ == null) {
/*     */         
/*  89 */         var3 = new ChatComponentTranslation("stream.user.subscription.subscriber", new Object[0]);
/*     */       }
/*  91 */       else if (p_152330_2_) {
/*     */         
/*  93 */         var3 = new ChatComponentTranslation("stream.user.subscription.subscriber.self", new Object[0]);
/*     */       }
/*     */       else {
/*     */         
/*  97 */         var3 = new ChatComponentTranslation("stream.user.subscription.subscriber.other", new Object[] { p_152330_1_ });
/*     */       } 
/*     */       
/* 100 */       var3.getChatStyle().setColor(field_152331_a);
/*     */     }
/* 102 */     else if (p_152330_0_ == ChatUserSubscription.TTV_CHAT_USERSUB_TURBO) {
/*     */       
/* 104 */       var3 = new ChatComponentTranslation("stream.user.subscription.turbo", new Object[0]);
/* 105 */       var3.getChatStyle().setColor(field_152336_g);
/*     */     } 
/*     */     
/* 108 */     return (IChatComponent)var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IChatComponent func_152329_a(ChatUserMode p_152329_0_, String p_152329_1_, boolean p_152329_2_) {
/* 113 */     ChatComponentTranslation var3 = null;
/*     */     
/* 115 */     if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR) {
/*     */       
/* 117 */       var3 = new ChatComponentTranslation("stream.user.mode.administrator", new Object[0]);
/* 118 */       var3.getChatStyle().setColor(field_152336_g);
/*     */     }
/* 120 */     else if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_BANNED) {
/*     */       
/* 122 */       if (p_152329_1_ == null) {
/*     */         
/* 124 */         var3 = new ChatComponentTranslation("stream.user.mode.banned", new Object[0]);
/*     */       }
/* 126 */       else if (p_152329_2_) {
/*     */         
/* 128 */         var3 = new ChatComponentTranslation("stream.user.mode.banned.self", new Object[0]);
/*     */       }
/*     */       else {
/*     */         
/* 132 */         var3 = new ChatComponentTranslation("stream.user.mode.banned.other", new Object[] { p_152329_1_ });
/*     */       } 
/*     */       
/* 135 */       var3.getChatStyle().setColor(field_152335_f);
/*     */     }
/* 137 */     else if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_BROADCASTER) {
/*     */       
/* 139 */       if (p_152329_1_ == null) {
/*     */         
/* 141 */         var3 = new ChatComponentTranslation("stream.user.mode.broadcaster", new Object[0]);
/*     */       }
/* 143 */       else if (p_152329_2_) {
/*     */         
/* 145 */         var3 = new ChatComponentTranslation("stream.user.mode.broadcaster.self", new Object[0]);
/*     */       }
/*     */       else {
/*     */         
/* 149 */         var3 = new ChatComponentTranslation("stream.user.mode.broadcaster.other", new Object[0]);
/*     */       } 
/*     */       
/* 152 */       var3.getChatStyle().setColor(field_152331_a);
/*     */     }
/* 154 */     else if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_MODERATOR) {
/*     */       
/* 156 */       if (p_152329_1_ == null) {
/*     */         
/* 158 */         var3 = new ChatComponentTranslation("stream.user.mode.moderator", new Object[0]);
/*     */       }
/* 160 */       else if (p_152329_2_) {
/*     */         
/* 162 */         var3 = new ChatComponentTranslation("stream.user.mode.moderator.self", new Object[0]);
/*     */       }
/*     */       else {
/*     */         
/* 166 */         var3 = new ChatComponentTranslation("stream.user.mode.moderator.other", new Object[] { p_152329_1_ });
/*     */       } 
/*     */       
/* 169 */       var3.getChatStyle().setColor(field_152331_a);
/*     */     }
/* 171 */     else if (p_152329_0_ == ChatUserMode.TTV_CHAT_USERMODE_STAFF) {
/*     */       
/* 173 */       var3 = new ChatComponentTranslation("stream.user.mode.staff", new Object[0]);
/* 174 */       var3.getChatStyle().setColor(field_152336_g);
/*     */     } 
/*     */     
/* 177 */     return (IChatComponent)var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/* 185 */     int var1 = width / 3;
/* 186 */     int var2 = var1 - 130;
/* 187 */     this.buttonList.add(new GuiButton(1, var1 * 0 + var2 / 2, height - 70, 130, 20, I18n.format("stream.userinfo.timeout", new Object[0])));
/* 188 */     this.buttonList.add(new GuiButton(0, var1 * 1 + var2 / 2, height - 70, 130, 20, I18n.format("stream.userinfo.ban", new Object[0])));
/* 189 */     this.buttonList.add(new GuiButton(2, var1 * 2 + var2 / 2, height - 70, 130, 20, I18n.format("stream.userinfo.mod", new Object[0])));
/* 190 */     this.buttonList.add(new GuiButton(5, var1 * 0 + var2 / 2, height - 45, 130, 20, I18n.format("gui.cancel", new Object[0])));
/* 191 */     this.buttonList.add(new GuiButton(3, var1 * 1 + var2 / 2, height - 45, 130, 20, I18n.format("stream.userinfo.unban", new Object[0])));
/* 192 */     this.buttonList.add(new GuiButton(4, var1 * 2 + var2 / 2, height - 45, 130, 20, I18n.format("stream.userinfo.unmod", new Object[0])));
/* 193 */     int var3 = 0;
/*     */ 
/*     */     
/* 196 */     for (Iterator<IChatComponent> var4 = this.field_152332_r.iterator(); var4.hasNext(); var3 = Math.max(var3, fontRendererObj.getStringWidth(var5.getFormattedText())))
/*     */     {
/* 198 */       IChatComponent var5 = var4.next();
/*     */     }
/*     */     
/* 201 */     this.field_152334_t = width / 2 - var3 / 2;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 206 */     if (button.enabled) {
/*     */       
/* 208 */       if (button.id == 0) {
/*     */         
/* 210 */         this.field_152333_s.func_152917_b("/ban " + this.field_152337_h.displayName);
/*     */       }
/* 212 */       else if (button.id == 3) {
/*     */         
/* 214 */         this.field_152333_s.func_152917_b("/unban " + this.field_152337_h.displayName);
/*     */       }
/* 216 */       else if (button.id == 2) {
/*     */         
/* 218 */         this.field_152333_s.func_152917_b("/mod " + this.field_152337_h.displayName);
/*     */       }
/* 220 */       else if (button.id == 4) {
/*     */         
/* 222 */         this.field_152333_s.func_152917_b("/unmod " + this.field_152337_h.displayName);
/*     */       }
/* 224 */       else if (button.id == 1) {
/*     */         
/* 226 */         this.field_152333_s.func_152917_b("/timeout " + this.field_152337_h.displayName);
/*     */       } 
/*     */       
/* 229 */       this.mc.displayGuiScreen(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 238 */     drawDefaultBackground();
/* 239 */     drawCenteredString(fontRendererObj, this.field_152338_i.getUnformattedText(), (width / 2), 70.0F, 16777215);
/* 240 */     int var4 = 80;
/*     */     
/* 242 */     for (Iterator<IChatComponent> var5 = this.field_152332_r.iterator(); var5.hasNext(); var4 += fontRendererObj.FONT_HEIGHT) {
/*     */       
/* 244 */       IChatComponent var6 = var5.next();
/* 245 */       drawString(fontRendererObj, var6.getFormattedText(), this.field_152334_t, var4, 16777215);
/*     */     } 
/*     */     
/* 248 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\stream\GuiTwitchUserMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */