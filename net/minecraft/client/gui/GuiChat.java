/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.client.C14PacketTabComplete;
/*     */ import net.minecraft.util.BlockPos;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiChat
/*     */   extends GuiScreen
/*     */ {
/*  26 */   private static final Logger logger = LogManager.getLogger();
/*  27 */   private String historyBuffer = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   private int sentHistoryCursor = -1;
/*     */   private boolean playerNamesFound;
/*     */   private boolean waitingOnAutocomplete;
/*     */   private int autocompleteIndex;
/*  38 */   private List foundPlayerNames = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   protected GuiTextField inputField;
/*     */ 
/*     */ 
/*     */   
/*  46 */   private String defaultInputFieldText = "";
/*     */   
/*     */   private static final String __OBFID = "CL_00000682";
/*     */   
/*     */   public GuiChat() {}
/*     */   
/*     */   public GuiChat(String p_i1024_1_) {
/*  53 */     this.defaultInputFieldText = p_i1024_1_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  62 */     Keyboard.enableRepeatEvents(true);
/*  63 */     this.sentHistoryCursor = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
/*  64 */     this.inputField = new GuiTextField(0, fontRendererObj, 4, height - 12, width - 4, 12);
/*  65 */     this.inputField.setMaxStringLength(100);
/*  66 */     this.inputField.setEnableBackgroundDrawing(false);
/*  67 */     this.inputField.setFocused(true);
/*  68 */     this.inputField.setText(this.defaultInputFieldText);
/*  69 */     this.inputField.setCanLoseFocus(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  77 */     Keyboard.enableRepeatEvents(false);
/*  78 */     this.mc.ingameGUI.getChatGUI().resetScroll();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  86 */     this.inputField.updateCursorCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/*  95 */     this.waitingOnAutocomplete = false;
/*     */     
/*  97 */     if (keyCode == 15) {
/*     */       
/*  99 */       autocompletePlayerNames();
/*     */     }
/*     */     else {
/*     */       
/* 103 */       this.playerNamesFound = false;
/*     */     } 
/*     */     
/* 106 */     if (keyCode == 1) {
/*     */       
/* 108 */       this.mc.displayGuiScreen(null);
/*     */     }
/* 110 */     else if (keyCode != 28 && keyCode != 156) {
/*     */       
/* 112 */       if (keyCode == 200)
/*     */       {
/* 114 */         getSentHistory(-1);
/*     */       }
/* 116 */       else if (keyCode == 208)
/*     */       {
/* 118 */         getSentHistory(1);
/*     */       }
/* 120 */       else if (keyCode == 201)
/*     */       {
/* 122 */         this.mc.ingameGUI.getChatGUI().scroll(this.mc.ingameGUI.getChatGUI().getLineCount() - 1);
/*     */       }
/* 124 */       else if (keyCode == 209)
/*     */       {
/* 126 */         this.mc.ingameGUI.getChatGUI().scroll(-this.mc.ingameGUI.getChatGUI().getLineCount() + 1);
/*     */       }
/*     */       else
/*     */       {
/* 130 */         this.inputField.textboxKeyTyped(typedChar, keyCode);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 135 */       String var3 = this.inputField.getText().trim();
/*     */       
/* 137 */       if (var3.length() > 0)
/*     */       {
/* 139 */         func_175275_f(var3);
/*     */       }
/*     */       
/* 142 */       this.mc.displayGuiScreen(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/* 151 */     super.handleMouseInput();
/* 152 */     int var1 = Mouse.getEventDWheel();
/*     */     
/* 154 */     if (var1 != 0) {
/*     */       
/* 156 */       if (var1 > 1)
/*     */       {
/* 158 */         var1 = 1;
/*     */       }
/*     */       
/* 161 */       if (var1 < -1)
/*     */       {
/* 163 */         var1 = -1;
/*     */       }
/*     */       
/* 166 */       if (!isShiftKeyDown())
/*     */       {
/* 168 */         var1 *= 7;
/*     */       }
/*     */       
/* 171 */       this.mc.ingameGUI.getChatGUI().scroll(var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 180 */     if (mouseButton == 0) {
/*     */       
/* 182 */       IChatComponent var4 = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
/*     */       
/* 184 */       if (func_175276_a(var4)) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 189 */     this.inputField.mouseClicked(mouseX, mouseY, mouseButton);
/* 190 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175274_a(String p_175274_1_, boolean p_175274_2_) {
/* 195 */     if (p_175274_2_) {
/*     */       
/* 197 */       this.inputField.setText(p_175274_1_);
/*     */     }
/*     */     else {
/*     */       
/* 201 */       this.inputField.writeText(p_175274_1_);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void autocompletePlayerNames() {
/* 209 */     if (this.playerNamesFound) {
/*     */       
/* 211 */       this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
/*     */       
/* 213 */       if (this.autocompleteIndex >= this.foundPlayerNames.size())
/*     */       {
/* 215 */         this.autocompleteIndex = 0;
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 220 */       int var1 = this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false);
/* 221 */       this.foundPlayerNames.clear();
/* 222 */       this.autocompleteIndex = 0;
/* 223 */       String var2 = this.inputField.getText().substring(var1).toLowerCase();
/* 224 */       String var3 = this.inputField.getText().substring(0, this.inputField.getCursorPosition());
/* 225 */       sendAutocompleteRequest(var3, var2);
/*     */       
/* 227 */       if (this.foundPlayerNames.isEmpty()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 232 */       this.playerNamesFound = true;
/* 233 */       this.inputField.deleteFromCursor(var1 - this.inputField.getCursorPosition());
/*     */     } 
/*     */     
/* 236 */     if (this.foundPlayerNames.size() > 1) {
/*     */       
/* 238 */       StringBuilder var4 = new StringBuilder();
/*     */       
/* 240 */       for (Iterator<String> var5 = this.foundPlayerNames.iterator(); var5.hasNext(); var4.append(var3)) {
/*     */         
/* 242 */         String var3 = var5.next();
/*     */         
/* 244 */         if (var4.length() > 0)
/*     */         {
/* 246 */           var4.append(", ");
/*     */         }
/*     */       } 
/*     */       
/* 250 */       this.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((IChatComponent)new ChatComponentText(var4.toString()), 1);
/*     */     } 
/*     */     
/* 253 */     this.inputField.writeText(this.foundPlayerNames.get(this.autocompleteIndex++));
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendAutocompleteRequest(String p_146405_1_, String p_146405_2_) {
/* 258 */     if (p_146405_1_.length() >= 1) {
/*     */       
/* 260 */       BlockPos var3 = null;
/*     */       
/* 262 */       if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
/*     */       {
/* 264 */         var3 = this.mc.objectMouseOver.func_178782_a();
/*     */       }
/*     */       
/* 267 */       this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C14PacketTabComplete(p_146405_1_, var3));
/* 268 */       this.waitingOnAutocomplete = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getSentHistory(int p_146402_1_) {
/* 278 */     int var2 = this.sentHistoryCursor + p_146402_1_;
/* 279 */     int var3 = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
/* 280 */     var2 = MathHelper.clamp_int(var2, 0, var3);
/*     */     
/* 282 */     if (var2 != this.sentHistoryCursor)
/*     */     {
/* 284 */       if (var2 == var3) {
/*     */         
/* 286 */         this.sentHistoryCursor = var3;
/* 287 */         this.inputField.setText(this.historyBuffer);
/*     */       }
/*     */       else {
/*     */         
/* 291 */         if (this.sentHistoryCursor == var3)
/*     */         {
/* 293 */           this.historyBuffer = this.inputField.getText();
/*     */         }
/*     */         
/* 296 */         this.inputField.setText(this.mc.ingameGUI.getChatGUI().getSentMessages().get(var2));
/* 297 */         this.sentHistoryCursor = var2;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 307 */     drawRect(2.0D, (height - 14), (width - 2), (height - 2), -2147483648);
/* 308 */     this.inputField.drawTextBox();
/* 309 */     IChatComponent var4 = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
/*     */     
/* 311 */     if (var4 != null && var4.getChatStyle().getChatHoverEvent() != null)
/*     */     {
/* 313 */       func_175272_a(var4, mouseX, mouseY);
/*     */     }
/*     */     
/* 316 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onAutocompleteResponse(String[] p_146406_1_) {
/* 321 */     if (this.waitingOnAutocomplete) {
/*     */       
/* 323 */       this.playerNamesFound = false;
/* 324 */       this.foundPlayerNames.clear();
/* 325 */       String[] var2 = p_146406_1_;
/* 326 */       int var3 = p_146406_1_.length;
/*     */       
/* 328 */       for (int var4 = 0; var4 < var3; var4++) {
/*     */         
/* 330 */         String var5 = var2[var4];
/*     */         
/* 332 */         if (var5.length() > 0)
/*     */         {
/* 334 */           this.foundPlayerNames.add(var5);
/*     */         }
/*     */       } 
/*     */       
/* 338 */       String var6 = this.inputField.getText().substring(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false));
/* 339 */       String var7 = StringUtils.getCommonPrefix(p_146406_1_);
/*     */       
/* 341 */       if (var7.length() > 0 && !var6.equalsIgnoreCase(var7)) {
/*     */         
/* 343 */         this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
/* 344 */         this.inputField.writeText(var7);
/*     */       }
/* 346 */       else if (this.foundPlayerNames.size() > 0) {
/*     */         
/* 348 */         this.playerNamesFound = true;
/* 349 */         autocompletePlayerNames();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 359 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   enum Handle
/*     */   {
/* 367 */     DRAW,
/* 368 */     CLICK,
/* 369 */     RELEASE;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */