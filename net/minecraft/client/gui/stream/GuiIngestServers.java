/*     */ package net.minecraft.client.gui.stream;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiSlot;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.stream.IngestServerTester;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import tv.twitch.broadcast.IngestServer;
/*     */ 
/*     */ public class GuiIngestServers
/*     */   extends GuiScreen {
/*     */   private final GuiScreen field_152309_a;
/*     */   private String field_152310_f;
/*     */   private ServerList field_152311_g;
/*     */   private static final String __OBFID = "CL_00001843";
/*     */   
/*     */   public GuiIngestServers(GuiScreen p_i46312_1_) {
/*  22 */     this.field_152309_a = p_i46312_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  30 */     this.field_152310_f = I18n.format("options.stream.ingest.title", new Object[0]);
/*  31 */     this.field_152311_g = new ServerList(this.mc);
/*     */     
/*  33 */     if (!this.mc.getTwitchStream().func_152908_z())
/*     */     {
/*  35 */       this.mc.getTwitchStream().func_152909_x();
/*     */     }
/*     */     
/*  38 */     this.buttonList.add(new GuiButton(1, width / 2 - 155, height - 24 - 6, 150, 20, I18n.format("gui.done", new Object[0])));
/*  39 */     this.buttonList.add(new GuiButton(2, width / 2 + 5, height - 24 - 6, 150, 20, I18n.format("options.stream.ingest.reset", new Object[0])));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  47 */     super.handleMouseInput();
/*  48 */     this.field_152311_g.func_178039_p();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  56 */     if (this.mc.getTwitchStream().func_152908_z())
/*     */     {
/*  58 */       this.mc.getTwitchStream().func_152932_y().func_153039_l();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  64 */     if (button.enabled)
/*     */     {
/*  66 */       if (button.id == 1) {
/*     */         
/*  68 */         this.mc.displayGuiScreen(this.field_152309_a);
/*     */       }
/*     */       else {
/*     */         
/*  72 */         this.mc.gameSettings.streamPreferredServer = "";
/*  73 */         this.mc.gameSettings.saveOptions();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  83 */     drawDefaultBackground();
/*  84 */     this.field_152311_g.drawScreen(mouseX, mouseY, partialTicks);
/*  85 */     drawCenteredString(fontRendererObj, this.field_152310_f, (width / 2), 20.0F, 16777215);
/*  86 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */   
/*     */   class ServerList
/*     */     extends GuiSlot
/*     */   {
/*     */     private static final String __OBFID = "CL_00001842";
/*     */     
/*     */     public ServerList(Minecraft mcIn) {
/*  95 */       super(mcIn, GuiIngestServers.width, GuiIngestServers.height, 32, GuiIngestServers.height - 35, (int)(mcIn.fontRendererObj.FONT_HEIGHT * 3.5D));
/*  96 */       setShowSelectionBox(false);
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getSize() {
/* 101 */       return (this.mc.getTwitchStream().func_152925_v()).length;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
/* 106 */       this.mc.gameSettings.streamPreferredServer = (this.mc.getTwitchStream().func_152925_v()[slotIndex]).serverUrl;
/* 107 */       this.mc.gameSettings.saveOptions();
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean isSelected(int slotIndex) {
/* 112 */       return (this.mc.getTwitchStream().func_152925_v()[slotIndex]).serverUrl.equals(this.mc.gameSettings.streamPreferredServer);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void drawBackground() {}
/*     */     
/*     */     protected void drawSlot(int p_180791_1_, int p_180791_2_, int p_180791_3_, int p_180791_4_, int p_180791_5_, int p_180791_6_) {
/* 119 */       IngestServer var7 = this.mc.getTwitchStream().func_152925_v()[p_180791_1_];
/* 120 */       String var8 = var7.serverUrl.replaceAll("\\{stream_key\\}", "");
/* 121 */       String var9 = String.valueOf((int)var7.bitrateKbps) + " kbps";
/* 122 */       String var10 = null;
/* 123 */       IngestServerTester var11 = this.mc.getTwitchStream().func_152932_y();
/*     */       
/* 125 */       if (var11 != null) {
/*     */         
/* 127 */         if (var7 == var11.func_153040_c())
/*     */         {
/* 129 */           var8 = EnumChatFormatting.GREEN + var8;
/* 130 */           var9 = String.valueOf((int)(var11.func_153030_h() * 100.0F)) + "%";
/*     */         }
/* 132 */         else if (p_180791_1_ < var11.func_153028_p())
/*     */         {
/* 134 */           if (var7.bitrateKbps == 0.0F)
/*     */           {
/* 136 */             var9 = EnumChatFormatting.RED + "Down!";
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 141 */           var9 = EnumChatFormatting.OBFUSCATED + "1234" + EnumChatFormatting.RESET + " kbps";
/*     */         }
/*     */       
/* 144 */       } else if (var7.bitrateKbps == 0.0F) {
/*     */         
/* 146 */         var9 = EnumChatFormatting.RED + "Down!";
/*     */       } 
/*     */       
/* 149 */       p_180791_2_ -= 15;
/*     */       
/* 151 */       if (isSelected(p_180791_1_)) {
/*     */         
/* 153 */         var10 = EnumChatFormatting.BLUE + "(Preferred)";
/*     */       }
/* 155 */       else if (var7.defaultServer) {
/*     */         
/* 157 */         var10 = EnumChatFormatting.GREEN + "(Default)";
/*     */       } 
/*     */       
/* 160 */       GuiIngestServers.this.drawString(GuiIngestServers.fontRendererObj, var7.serverName, p_180791_2_ + 2, p_180791_3_ + 5, 16777215);
/* 161 */       GuiIngestServers.this.drawString(GuiIngestServers.fontRendererObj, var8, p_180791_2_ + 2, p_180791_3_ + GuiIngestServers.fontRendererObj.FONT_HEIGHT + 5 + 3, 3158064);
/* 162 */       GuiIngestServers.this.drawString(GuiIngestServers.fontRendererObj, var9, getScrollBarX() - 5 - GuiIngestServers.fontRendererObj.getStringWidth(var9), p_180791_3_ + 5, 8421504);
/*     */       
/* 164 */       if (var10 != null)
/*     */       {
/* 166 */         GuiIngestServers.this.drawString(GuiIngestServers.fontRendererObj, var10, getScrollBarX() - 5 - GuiIngestServers.fontRendererObj.getStringWidth(var10), p_180791_3_ + 5 + 3 + GuiIngestServers.fontRendererObj.FONT_HEIGHT, 8421504);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     protected int getScrollBarX() {
/* 172 */       return super.getScrollBarX() + 15;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\stream\GuiIngestServers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */