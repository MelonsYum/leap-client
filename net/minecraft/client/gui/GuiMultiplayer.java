/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import leap.alts.GuiAltManager;
/*     */ import net.minecraft.client.multiplayer.GuiConnecting;
/*     */ import net.minecraft.client.multiplayer.ServerData;
/*     */ import net.minecraft.client.multiplayer.ServerList;
/*     */ import net.minecraft.client.network.LanServerDetector;
/*     */ import net.minecraft.client.network.OldServerPinger;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiMultiplayer
/*     */   extends GuiScreen
/*     */   implements GuiYesNoCallback
/*     */ {
/*  22 */   private static final Logger logger = LogManager.getLogger();
/*  23 */   private final OldServerPinger oldServerPinger = new OldServerPinger();
/*     */   
/*     */   private GuiScreen parentScreen;
/*     */   private ServerSelectionList serverListSelector;
/*     */   private ServerList savedServerList;
/*     */   private GuiButton btnEditServer;
/*     */   private GuiButton btnSelectServer;
/*     */   private GuiButton btnDeleteServer;
/*     */   private boolean deletingServer;
/*     */   private boolean addingServer;
/*     */   private boolean editingServer;
/*     */   private boolean directConnect;
/*     */   private String field_146812_y;
/*     */   private ServerData selectedServer;
/*     */   private LanServerDetector.LanServerList lanServerList;
/*     */   private LanServerDetector.ThreadLanServerFind lanServerDetector;
/*     */   private boolean initialized;
/*     */   private static final String __OBFID = "CL_00000814";
/*     */   
/*     */   public GuiMultiplayer(GuiScreen parentScreen) {
/*  43 */     this.parentScreen = parentScreen;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  52 */     Keyboard.enableRepeatEvents(true);
/*  53 */     this.buttonList.clear();
/*     */     
/*  55 */     if (!this.initialized) {
/*     */       
/*  57 */       this.initialized = true;
/*  58 */       this.savedServerList = new ServerList(this.mc);
/*  59 */       this.savedServerList.loadServerList();
/*  60 */       this.lanServerList = new LanServerDetector.LanServerList();
/*     */ 
/*     */       
/*     */       try {
/*  64 */         this.lanServerDetector = new LanServerDetector.ThreadLanServerFind(this.lanServerList);
/*  65 */         this.lanServerDetector.start();
/*     */       }
/*  67 */       catch (Exception var2) {
/*     */         
/*  69 */         logger.warn("Unable to start LAN server detection: " + var2.getMessage());
/*     */       } 
/*     */       
/*  72 */       this.serverListSelector = new ServerSelectionList(this, this.mc, width, height, 32, height - 64, 36);
/*  73 */       this.serverListSelector.func_148195_a(this.savedServerList);
/*     */     }
/*     */     else {
/*     */       
/*  77 */       this.serverListSelector.setDimensions(width, height, 32, height - 64);
/*     */     } 
/*     */     
/*  80 */     createButtons();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/*  88 */     super.handleMouseInput();
/*  89 */     this.serverListSelector.func_178039_p();
/*     */   }
/*     */ 
/*     */   
/*     */   public void createButtons() {
/*  94 */     this.buttonList.add(this.btnEditServer = new GuiButton(7, width / 2 - 154, height - 28, 70, 20, I18n.format("selectServer.edit", new Object[0])));
/*  95 */     this.buttonList.add(this.btnDeleteServer = new GuiButton(2, width / 2 - 74, height - 28, 70, 20, I18n.format("selectServer.delete", new Object[0])));
/*  96 */     this.buttonList.add(this.btnSelectServer = new GuiButton(1, width / 2 - 154, height - 52, 100, 20, I18n.format("selectServer.select", new Object[0])));
/*  97 */     this.buttonList.add(new GuiButton(4, width / 2 - 50, height - 52, 100, 20, I18n.format("selectServer.direct", new Object[0])));
/*  98 */     this.buttonList.add(new GuiButton(3, width / 2 + 4 + 50, height - 52, 100, 20, I18n.format("selectServer.add", new Object[0])));
/*  99 */     this.buttonList.add(new GuiButton(8, width / 2 + 4, height - 28, 70, 20, I18n.format("selectServer.refresh", new Object[0])));
/* 100 */     this.buttonList.add(new GuiButton(0, width / 2 + 4 + 76, height - 28, 75, 20, I18n.format("gui.cancel", new Object[0])));
/* 101 */     this.buttonList.add(new GuiButton(10, width / 2 + 4 + 155, height - 40, 70, 20, I18n.format("Alt Manager", new Object[0])));
/* 102 */     selectServer(this.serverListSelector.func_148193_k());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 110 */     super.updateScreen();
/*     */     
/* 112 */     if (this.lanServerList.getWasUpdated()) {
/*     */       
/* 114 */       List var1 = this.lanServerList.getLanServers();
/* 115 */       this.lanServerList.setWasNotUpdated();
/* 116 */       this.serverListSelector.func_148194_a(var1);
/*     */     } 
/*     */     
/* 119 */     this.oldServerPinger.pingPendingNetworks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 127 */     Keyboard.enableRepeatEvents(false);
/*     */     
/* 129 */     if (this.lanServerDetector != null) {
/*     */       
/* 131 */       this.lanServerDetector.interrupt();
/* 132 */       this.lanServerDetector = null;
/*     */     } 
/*     */     
/* 135 */     this.oldServerPinger.clearPendingNetworks();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 140 */     if (button.enabled) {
/*     */       
/* 142 */       GuiListExtended.IGuiListEntry var2 = (this.serverListSelector.func_148193_k() < 0) ? null : this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k());
/*     */       
/* 144 */       if (button.id == 2 && var2 instanceof ServerListEntryNormal) {
/*     */         
/* 146 */         String var9 = (((ServerListEntryNormal)var2).getServerData()).serverName;
/*     */         
/* 148 */         if (var9 != null)
/*     */         {
/* 150 */           this.deletingServer = true;
/* 151 */           String var4 = I18n.format("selectServer.deleteQuestion", new Object[0]);
/* 152 */           String var5 = "'" + var9 + "' " + I18n.format("selectServer.deleteWarning", new Object[0]);
/* 153 */           String var6 = I18n.format("selectServer.deleteButton", new Object[0]);
/* 154 */           String var7 = I18n.format("gui.cancel", new Object[0]);
/* 155 */           GuiYesNo var8 = new GuiYesNo(this, var4, var5, var6, var7, this.serverListSelector.func_148193_k());
/* 156 */           this.mc.displayGuiScreen(var8);
/*     */         }
/*     */       
/* 159 */       } else if (button.id == 1) {
/*     */         
/* 161 */         connectToSelected();
/*     */       }
/* 163 */       else if (button.id == 4) {
/*     */         
/* 165 */         this.directConnect = true;
/* 166 */         this.mc.displayGuiScreen(new GuiScreenServerList(this, this.selectedServer = new ServerData(I18n.format("selectServer.defaultName", new Object[0]), "")));
/*     */       }
/* 168 */       else if (button.id == 3) {
/*     */         
/* 170 */         this.addingServer = true;
/* 171 */         this.mc.displayGuiScreen(new GuiScreenAddServer(this, this.selectedServer = new ServerData(I18n.format("selectServer.defaultName", new Object[0]), "")));
/*     */       }
/* 173 */       else if (button.id == 7 && var2 instanceof ServerListEntryNormal) {
/*     */         
/* 175 */         this.editingServer = true;
/* 176 */         ServerData var3 = ((ServerListEntryNormal)var2).getServerData();
/* 177 */         this.selectedServer = new ServerData(var3.serverName, var3.serverIP);
/* 178 */         this.selectedServer.copyFrom(var3);
/* 179 */         this.mc.displayGuiScreen(new GuiScreenAddServer(this, this.selectedServer));
/*     */       }
/* 181 */       else if (button.id == 0) {
/*     */         
/* 183 */         this.mc.displayGuiScreen(this.parentScreen);
/*     */       }
/* 185 */       else if (button.id == 8) {
/*     */         
/* 187 */         refreshServerList();
/*     */       }
/* 189 */       else if (button.id == 10) {
/*     */         
/* 191 */         this.mc.displayGuiScreen((GuiScreen)new GuiAltManager());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void refreshServerList() {
/* 198 */     this.mc.displayGuiScreen(new GuiMultiplayer(this.parentScreen));
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean result, int id) {
/* 203 */     GuiListExtended.IGuiListEntry var3 = (this.serverListSelector.func_148193_k() < 0) ? null : this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k());
/*     */     
/* 205 */     if (this.deletingServer) {
/*     */       
/* 207 */       this.deletingServer = false;
/*     */       
/* 209 */       if (result && var3 instanceof ServerListEntryNormal) {
/*     */         
/* 211 */         this.savedServerList.removeServerData(this.serverListSelector.func_148193_k());
/* 212 */         this.savedServerList.saveServerList();
/* 213 */         this.serverListSelector.func_148192_c(-1);
/* 214 */         this.serverListSelector.func_148195_a(this.savedServerList);
/*     */       } 
/*     */       
/* 217 */       this.mc.displayGuiScreen(this);
/*     */     }
/* 219 */     else if (this.directConnect) {
/*     */       
/* 221 */       this.directConnect = false;
/*     */       
/* 223 */       if (result)
/*     */       {
/* 225 */         connectToServer(this.selectedServer);
/*     */       }
/*     */       else
/*     */       {
/* 229 */         this.mc.displayGuiScreen(this);
/*     */       }
/*     */     
/* 232 */     } else if (this.addingServer) {
/*     */       
/* 234 */       this.addingServer = false;
/*     */       
/* 236 */       if (result) {
/*     */         
/* 238 */         this.savedServerList.addServerData(this.selectedServer);
/* 239 */         this.savedServerList.saveServerList();
/* 240 */         this.serverListSelector.func_148192_c(-1);
/* 241 */         this.serverListSelector.func_148195_a(this.savedServerList);
/*     */       } 
/*     */       
/* 244 */       this.mc.displayGuiScreen(this);
/*     */     }
/* 246 */     else if (this.editingServer) {
/*     */       
/* 248 */       this.editingServer = false;
/*     */       
/* 250 */       if (result && var3 instanceof ServerListEntryNormal) {
/*     */         
/* 252 */         ServerData var4 = ((ServerListEntryNormal)var3).getServerData();
/* 253 */         var4.serverName = this.selectedServer.serverName;
/* 254 */         var4.serverIP = this.selectedServer.serverIP;
/* 255 */         var4.copyFrom(this.selectedServer);
/* 256 */         this.savedServerList.saveServerList();
/* 257 */         this.serverListSelector.func_148195_a(this.savedServerList);
/*     */       } 
/*     */       
/* 260 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/* 270 */     int var3 = this.serverListSelector.func_148193_k();
/* 271 */     GuiListExtended.IGuiListEntry var4 = (var3 < 0) ? null : this.serverListSelector.getListEntry(var3);
/*     */     
/* 273 */     if (keyCode == 63) {
/*     */       
/* 275 */       refreshServerList();
/*     */ 
/*     */     
/*     */     }
/* 279 */     else if (var3 >= 0) {
/*     */       
/* 281 */       if (keyCode == 200) {
/*     */         
/* 283 */         if (isShiftKeyDown()) {
/*     */           
/* 285 */           if (var3 > 0 && var4 instanceof ServerListEntryNormal)
/*     */           {
/* 287 */             this.savedServerList.swapServers(var3, var3 - 1);
/* 288 */             selectServer(this.serverListSelector.func_148193_k() - 1);
/* 289 */             this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
/* 290 */             this.serverListSelector.func_148195_a(this.savedServerList);
/*     */           }
/*     */         
/* 293 */         } else if (var3 > 0) {
/*     */           
/* 295 */           selectServer(this.serverListSelector.func_148193_k() - 1);
/* 296 */           this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
/*     */           
/* 298 */           if (this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k()) instanceof ServerListEntryLanScan)
/*     */           {
/* 300 */             if (this.serverListSelector.func_148193_k() > 0)
/*     */             {
/* 302 */               selectServer(this.serverListSelector.getSize() - 1);
/* 303 */               this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
/*     */             }
/*     */             else
/*     */             {
/* 307 */               selectServer(-1);
/*     */             }
/*     */           
/*     */           }
/*     */         } else {
/*     */           
/* 313 */           selectServer(-1);
/*     */         }
/*     */       
/* 316 */       } else if (keyCode == 208) {
/*     */         
/* 318 */         if (isShiftKeyDown()) {
/*     */           
/* 320 */           if (var3 < this.savedServerList.countServers() - 1)
/*     */           {
/* 322 */             this.savedServerList.swapServers(var3, var3 + 1);
/* 323 */             selectServer(var3 + 1);
/* 324 */             this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
/* 325 */             this.serverListSelector.func_148195_a(this.savedServerList);
/*     */           }
/*     */         
/* 328 */         } else if (var3 < this.serverListSelector.getSize()) {
/*     */           
/* 330 */           selectServer(this.serverListSelector.func_148193_k() + 1);
/* 331 */           this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
/*     */           
/* 333 */           if (this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k()) instanceof ServerListEntryLanScan)
/*     */           {
/* 335 */             if (this.serverListSelector.func_148193_k() < this.serverListSelector.getSize() - 1)
/*     */             {
/* 337 */               selectServer(this.serverListSelector.getSize() + 1);
/* 338 */               this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
/*     */             }
/*     */             else
/*     */             {
/* 342 */               selectServer(-1);
/*     */             }
/*     */           
/*     */           }
/*     */         } else {
/*     */           
/* 348 */           selectServer(-1);
/*     */         }
/*     */       
/* 351 */       } else if (keyCode != 28 && keyCode != 156) {
/*     */         
/* 353 */         super.keyTyped(typedChar, keyCode);
/*     */       }
/*     */       else {
/*     */         
/* 357 */         actionPerformed(this.buttonList.get(2));
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 362 */       super.keyTyped(typedChar, keyCode);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 372 */     this.field_146812_y = null;
/* 373 */     drawDefaultBackground();
/* 374 */     this.serverListSelector.drawScreen(mouseX, mouseY, partialTicks);
/* 375 */     drawCenteredString(fontRendererObj, I18n.format("multiplayer.title", new Object[0]), (width / 2), 20.0F, 16777215);
/* 376 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */     
/* 378 */     if (this.field_146812_y != null)
/*     */     {
/* 380 */       drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.field_146812_y)), mouseX, mouseY);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void connectToSelected() {
/* 386 */     GuiListExtended.IGuiListEntry var1 = (this.serverListSelector.func_148193_k() < 0) ? null : this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k());
/*     */     
/* 388 */     if (var1 instanceof ServerListEntryNormal) {
/*     */       
/* 390 */       connectToServer(((ServerListEntryNormal)var1).getServerData());
/*     */     }
/* 392 */     else if (var1 instanceof ServerListEntryLanDetected) {
/*     */       
/* 394 */       LanServerDetector.LanServer var2 = ((ServerListEntryLanDetected)var1).getLanServer();
/* 395 */       connectToServer(new ServerData(var2.getServerMotd(), var2.getServerIpPort()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void connectToServer(ServerData server) {
/* 401 */     this.mc.displayGuiScreen((GuiScreen)new GuiConnecting(this, this.mc, server));
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectServer(int index) {
/* 406 */     this.serverListSelector.func_148192_c(index);
/* 407 */     GuiListExtended.IGuiListEntry var2 = (index < 0) ? null : this.serverListSelector.getListEntry(index);
/* 408 */     this.btnSelectServer.enabled = false;
/* 409 */     this.btnEditServer.enabled = false;
/* 410 */     this.btnDeleteServer.enabled = false;
/*     */     
/* 412 */     if (var2 != null && !(var2 instanceof ServerListEntryLanScan)) {
/*     */       
/* 414 */       this.btnSelectServer.enabled = true;
/*     */       
/* 416 */       if (var2 instanceof ServerListEntryNormal) {
/*     */         
/* 418 */         this.btnEditServer.enabled = true;
/* 419 */         this.btnDeleteServer.enabled = true;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public OldServerPinger getOldServerPinger() {
/* 426 */     return this.oldServerPinger;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_146793_a(String p_146793_1_) {
/* 431 */     this.field_146812_y = p_146793_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 439 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 440 */     this.serverListSelector.func_148179_a(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseReleased(int mouseX, int mouseY, int state) {
/* 448 */     super.mouseReleased(mouseX, mouseY, state);
/* 449 */     this.serverListSelector.func_148181_b(mouseX, mouseY, state);
/*     */   }
/*     */ 
/*     */   
/*     */   public ServerList getServerList() {
/* 454 */     return this.savedServerList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175392_a(ServerListEntryNormal p_175392_1_, int p_175392_2_) {
/* 459 */     return (p_175392_2_ > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_175394_b(ServerListEntryNormal p_175394_1_, int p_175394_2_) {
/* 464 */     return (p_175394_2_ < this.savedServerList.countServers() - 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175391_a(ServerListEntryNormal p_175391_1_, int p_175391_2_, boolean p_175391_3_) {
/* 469 */     int var4 = p_175391_3_ ? 0 : (p_175391_2_ - 1);
/* 470 */     this.savedServerList.swapServers(p_175391_2_, var4);
/*     */     
/* 472 */     if (this.serverListSelector.func_148193_k() == p_175391_2_)
/*     */     {
/* 474 */       selectServer(var4);
/*     */     }
/*     */     
/* 477 */     this.serverListSelector.func_148195_a(this.savedServerList);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175393_b(ServerListEntryNormal p_175393_1_, int p_175393_2_, boolean p_175393_3_) {
/* 482 */     int var4 = p_175393_3_ ? (this.savedServerList.countServers() - 1) : (p_175393_2_ + 1);
/* 483 */     this.savedServerList.swapServers(p_175393_2_, var4);
/*     */     
/* 485 */     if (this.serverListSelector.func_148193_k() == p_175393_2_)
/*     */     {
/* 487 */       selectServer(var4);
/*     */     }
/*     */     
/* 490 */     this.serverListSelector.func_148195_a(this.savedServerList);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiMultiplayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */