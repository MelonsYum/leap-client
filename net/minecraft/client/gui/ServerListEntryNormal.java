/*     */ package net.minecraft.client.gui;
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.ByteBufInputStream;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.handler.codec.base64.Base64;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.InputStream;
/*     */ import java.net.UnknownHostException;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ScheduledThreadPoolExecutor;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.multiplayer.ServerData;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.texture.DynamicTexture;
/*     */ import net.minecraft.client.renderer.texture.ITextureObject;
/*     */ import net.minecraft.client.renderer.texture.TextureUtil;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.commons.lang3.Validate;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ServerListEntryNormal implements GuiListExtended.IGuiListEntry {
/*  27 */   private static final Logger logger = LogManager.getLogger();
/*  28 */   private static final ThreadPoolExecutor field_148302_b = new ScheduledThreadPoolExecutor(5, (new ThreadFactoryBuilder()).setNameFormat("Server Pinger #%d").setDaemon(true).build());
/*  29 */   private static final ResourceLocation field_178015_c = new ResourceLocation("textures/misc/unknown_server.png");
/*  30 */   private static final ResourceLocation field_178014_d = new ResourceLocation("textures/gui/server_selection.png");
/*     */   
/*     */   private final GuiMultiplayer field_148303_c;
/*     */   private final Minecraft field_148300_d;
/*     */   private final ServerData field_148301_e;
/*     */   private final ResourceLocation field_148306_i;
/*     */   private String field_148299_g;
/*     */   private DynamicTexture field_148305_h;
/*     */   private long field_148298_f;
/*     */   private static final String __OBFID = "CL_00000817";
/*     */   
/*     */   protected ServerListEntryNormal(GuiMultiplayer p_i45048_1_, ServerData p_i45048_2_) {
/*  42 */     this.field_148303_c = p_i45048_1_;
/*  43 */     this.field_148301_e = p_i45048_2_;
/*  44 */     this.field_148300_d = Minecraft.getMinecraft();
/*  45 */     this.field_148306_i = new ResourceLocation("servers/" + p_i45048_2_.serverIP + "/icon");
/*  46 */     this.field_148305_h = (DynamicTexture)this.field_148300_d.getTextureManager().getTexture(this.field_148306_i);
/*     */   }
/*     */   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
/*     */     int var16;
/*     */     String var18;
/*  51 */     if (!this.field_148301_e.field_78841_f) {
/*     */       
/*  53 */       this.field_148301_e.field_78841_f = true;
/*  54 */       this.field_148301_e.pingToServer = -2L;
/*  55 */       this.field_148301_e.serverMOTD = "";
/*  56 */       this.field_148301_e.populationInfo = "";
/*  57 */       field_148302_b.submit(new Runnable()
/*     */           {
/*     */             private static final String __OBFID = "CL_00000818";
/*     */ 
/*     */             
/*     */             public void run() {
/*     */               try {
/*  64 */                 ServerListEntryNormal.this.field_148303_c.getOldServerPinger().ping(ServerListEntryNormal.this.field_148301_e);
/*     */               }
/*  66 */               catch (UnknownHostException var2) {
/*     */                 
/*  68 */                 ServerListEntryNormal.this.field_148301_e.pingToServer = -1L;
/*  69 */                 ServerListEntryNormal.this.field_148301_e.serverMOTD = EnumChatFormatting.DARK_RED + "Can't resolve hostname";
/*     */               }
/*  71 */               catch (Exception var3) {
/*     */                 
/*  73 */                 ServerListEntryNormal.this.field_148301_e.pingToServer = -1L;
/*  74 */                 ServerListEntryNormal.this.field_148301_e.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
/*     */               } 
/*     */             }
/*     */           });
/*     */     } 
/*     */     
/*  80 */     boolean var9 = (this.field_148301_e.version > 47);
/*  81 */     boolean var10 = (this.field_148301_e.version < 47);
/*  82 */     boolean var11 = !(!var9 && !var10);
/*  83 */     this.field_148300_d.fontRendererObj.drawString(this.field_148301_e.serverName, (x + 32 + 3), (y + 1), 16777215);
/*  84 */     List<String> var12 = this.field_148300_d.fontRendererObj.listFormattedStringToWidth(this.field_148301_e.serverMOTD, listWidth - 32 - 2);
/*     */     
/*  86 */     for (int var13 = 0; var13 < Math.min(var12.size(), 2); var13++)
/*     */     {
/*  88 */       this.field_148300_d.fontRendererObj.drawString(var12.get(var13), (x + 32 + 3), (y + 12 + this.field_148300_d.fontRendererObj.FONT_HEIGHT * var13), 8421504);
/*     */     }
/*     */     
/*  91 */     String var23 = var11 ? (EnumChatFormatting.DARK_RED + this.field_148301_e.gameVersion) : this.field_148301_e.populationInfo;
/*  92 */     int var14 = this.field_148300_d.fontRendererObj.getStringWidth(var23);
/*  93 */     this.field_148300_d.fontRendererObj.drawString(var23, (x + listWidth - var14 - 15 - 2), (y + 1), 8421504);
/*  94 */     byte var15 = 0;
/*  95 */     String var17 = null;
/*     */ 
/*     */ 
/*     */     
/*  99 */     if (var11) {
/*     */       
/* 101 */       var16 = 5;
/* 102 */       var18 = var9 ? "Client out of date!" : "Server out of date!";
/* 103 */       var17 = this.field_148301_e.playerList;
/*     */     }
/* 105 */     else if (this.field_148301_e.field_78841_f && this.field_148301_e.pingToServer != -2L) {
/*     */       
/* 107 */       if (this.field_148301_e.pingToServer < 0L) {
/*     */         
/* 109 */         var16 = 5;
/*     */       }
/* 111 */       else if (this.field_148301_e.pingToServer < 150L) {
/*     */         
/* 113 */         var16 = 0;
/*     */       }
/* 115 */       else if (this.field_148301_e.pingToServer < 300L) {
/*     */         
/* 117 */         var16 = 1;
/*     */       }
/* 119 */       else if (this.field_148301_e.pingToServer < 600L) {
/*     */         
/* 121 */         var16 = 2;
/*     */       }
/* 123 */       else if (this.field_148301_e.pingToServer < 1000L) {
/*     */         
/* 125 */         var16 = 3;
/*     */       }
/*     */       else {
/*     */         
/* 129 */         var16 = 4;
/*     */       } 
/*     */       
/* 132 */       if (this.field_148301_e.pingToServer < 0L)
/*     */       {
/* 134 */         var18 = "(no connection)";
/*     */       }
/*     */       else
/*     */       {
/* 138 */         var18 = String.valueOf(this.field_148301_e.pingToServer) + "ms";
/* 139 */         var17 = this.field_148301_e.playerList;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 144 */       var15 = 1;
/* 145 */       var16 = (int)(Minecraft.getSystemTime() / 100L + (slotIndex * 2) & 0x7L);
/*     */       
/* 147 */       if (var16 > 4)
/*     */       {
/* 149 */         var16 = 8 - var16;
/*     */       }
/*     */       
/* 152 */       var18 = "Pinging...";
/*     */     } 
/*     */     
/* 155 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 156 */     this.field_148300_d.getTextureManager().bindTexture(Gui.icons);
/* 157 */     Gui.drawModalRectWithCustomSizedTexture((x + listWidth - 15), y, (var15 * 10), (176 + var16 * 8), 10.0F, 8.0F, 256.0F, 256.0F);
/*     */     
/* 159 */     if (this.field_148301_e.getBase64EncodedIconData() != null && !this.field_148301_e.getBase64EncodedIconData().equals(this.field_148299_g)) {
/*     */       
/* 161 */       this.field_148299_g = this.field_148301_e.getBase64EncodedIconData();
/* 162 */       prepareServerIcon();
/* 163 */       this.field_148303_c.getServerList().saveServerList();
/*     */     } 
/*     */     
/* 166 */     if (this.field_148305_h != null) {
/*     */       
/* 168 */       func_178012_a(x, y, this.field_148306_i);
/*     */     }
/*     */     else {
/*     */       
/* 172 */       func_178012_a(x, y, field_178015_c);
/*     */     } 
/*     */     
/* 175 */     int var19 = mouseX - x;
/* 176 */     int var20 = mouseY - y;
/*     */     
/* 178 */     if (var19 >= listWidth - 15 && var19 <= listWidth - 5 && var20 >= 0 && var20 <= 8) {
/*     */       
/* 180 */       this.field_148303_c.func_146793_a(var18);
/*     */     }
/* 182 */     else if (var19 >= listWidth - var14 - 15 - 2 && var19 <= listWidth - 15 - 2 && var20 >= 0 && var20 <= 8) {
/*     */       
/* 184 */       this.field_148303_c.func_146793_a(var17);
/*     */     } 
/*     */     
/* 187 */     if (this.field_148300_d.gameSettings.touchscreen || isSelected) {
/*     */       
/* 189 */       this.field_148300_d.getTextureManager().bindTexture(field_178014_d);
/* 190 */       Gui.drawRect(x, y, (x + 32), (y + 32), -1601138544);
/* 191 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 192 */       int var21 = mouseX - x;
/* 193 */       int var22 = mouseY - y;
/*     */       
/* 195 */       if (func_178013_b())
/*     */       {
/* 197 */         if (var21 < 32 && var21 > 16) {
/*     */           
/* 199 */           Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */         }
/*     */         else {
/*     */           
/* 203 */           Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */         } 
/*     */       }
/*     */       
/* 207 */       if (this.field_148303_c.func_175392_a(this, slotIndex))
/*     */       {
/* 209 */         if (var21 < 16 && var22 < 16) {
/*     */           
/* 211 */           Gui.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */         }
/*     */         else {
/*     */           
/* 215 */           Gui.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */         } 
/*     */       }
/*     */       
/* 219 */       if (this.field_148303_c.func_175394_b(this, slotIndex))
/*     */       {
/* 221 */         if (var21 < 16 && var22 > 16) {
/*     */           
/* 223 */           Gui.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */         }
/*     */         else {
/*     */           
/* 227 */           Gui.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_178012_a(int p_178012_1_, int p_178012_2_, ResourceLocation p_178012_3_) {
/* 235 */     this.field_148300_d.getTextureManager().bindTexture(p_178012_3_);
/* 236 */     GlStateManager.enableBlend();
/* 237 */     Gui.drawModalRectWithCustomSizedTexture(p_178012_1_, p_178012_2_, 0.0F, 0.0F, 32.0F, 32.0F, 32.0F, 32.0F);
/* 238 */     GlStateManager.disableBlend();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean func_178013_b() {
/* 243 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void prepareServerIcon() {
/* 248 */     if (this.field_148301_e.getBase64EncodedIconData() == null) {
/*     */       
/* 250 */       this.field_148300_d.getTextureManager().deleteTexture(this.field_148306_i);
/* 251 */       this.field_148305_h = null;
/*     */     } else {
/*     */       BufferedImage var1;
/*     */       
/* 255 */       ByteBuf var2 = Unpooled.copiedBuffer(this.field_148301_e.getBase64EncodedIconData(), Charsets.UTF_8);
/* 256 */       ByteBuf var3 = Base64.decode(var2);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 262 */         var1 = TextureUtil.func_177053_a((InputStream)new ByteBufInputStream(var3));
/* 263 */         Validate.validState((var1.getWidth() == 64), "Must be 64 pixels wide", new Object[0]);
/* 264 */         Validate.validState((var1.getHeight() == 64), "Must be 64 pixels high", new Object[0]);
/*     */       
/*     */       }
/* 267 */       catch (Exception var8) {
/*     */         
/* 269 */         logger.error("Invalid icon for server " + this.field_148301_e.serverName + " (" + this.field_148301_e.serverIP + ")", var8);
/* 270 */         this.field_148301_e.setBase64EncodedIconData(null);
/*     */       }
/*     */       finally {
/*     */         
/* 274 */         var2.release();
/* 275 */         var3.release();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 281 */       if (this.field_148305_h == null) {
/*     */         
/* 283 */         this.field_148305_h = new DynamicTexture(var1.getWidth(), var1.getHeight());
/* 284 */         this.field_148300_d.getTextureManager().loadTexture(this.field_148306_i, (ITextureObject)this.field_148305_h);
/*     */       } 
/*     */       
/* 287 */       var1.getRGB(0, 0, var1.getWidth(), var1.getHeight(), this.field_148305_h.getTextureData(), 0, var1.getWidth());
/* 288 */       this.field_148305_h.updateDynamicTexture();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean mousePressed(int p_148278_1_, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_) {
/* 297 */     if (p_148278_5_ <= 32) {
/*     */       
/* 299 */       if (p_148278_5_ < 32 && p_148278_5_ > 16 && func_178013_b()) {
/*     */         
/* 301 */         this.field_148303_c.selectServer(p_148278_1_);
/* 302 */         this.field_148303_c.connectToSelected();
/* 303 */         return true;
/*     */       } 
/*     */       
/* 306 */       if (p_148278_5_ < 16 && p_148278_6_ < 16 && this.field_148303_c.func_175392_a(this, p_148278_1_)) {
/*     */         
/* 308 */         this.field_148303_c.func_175391_a(this, p_148278_1_, GuiScreen.isShiftKeyDown());
/* 309 */         return true;
/*     */       } 
/*     */       
/* 312 */       if (p_148278_5_ < 16 && p_148278_6_ > 16 && this.field_148303_c.func_175394_b(this, p_148278_1_)) {
/*     */         
/* 314 */         this.field_148303_c.func_175393_b(this, p_148278_1_, GuiScreen.isShiftKeyDown());
/* 315 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 319 */     this.field_148303_c.selectServer(p_148278_1_);
/*     */     
/* 321 */     if (Minecraft.getSystemTime() - this.field_148298_f < 250L)
/*     */     {
/* 323 */       this.field_148303_c.connectToSelected();
/*     */     }
/*     */     
/* 326 */     this.field_148298_f = Minecraft.getSystemTime();
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
/*     */ 
/*     */   
/*     */   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}
/*     */ 
/*     */   
/*     */   public ServerData getServerData() {
/* 339 */     return this.field_148301_e;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\ServerListEntryNormal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */