/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.stream.GuiTwitchUserMode;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.client.renderer.entity.RenderItem;
/*     */ import net.minecraft.entity.EntityList;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import net.minecraft.event.HoverEvent;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.JsonToNBT;
/*     */ import net.minecraft.nbt.NBTException;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.Achievement;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import tv.twitch.chat.ChatUserInfo;
/*     */ 
/*     */ public abstract class GuiScreen
/*     */   extends Gui implements GuiYesNoCallback {
/*  49 */   private static final Logger field_175287_a = LogManager.getLogger();
/*  50 */   private static final Set field_175284_f = Sets.newHashSet((Object[])new String[] { "http", "https" });
/*  51 */   private static final Splitter field_175285_g = Splitter.on('\n');
/*     */ 
/*     */ 
/*     */   
/*     */   protected Minecraft mc;
/*     */ 
/*     */ 
/*     */   
/*     */   protected RenderItem itemRender;
/*     */ 
/*     */   
/*     */   public static int width;
/*     */ 
/*     */   
/*     */   public static int height;
/*     */ 
/*     */   
/*  68 */   protected List buttonList = Lists.newArrayList();
/*     */ 
/*     */   
/*  71 */   protected List labelList = Lists.newArrayList();
/*     */ 
/*     */   
/*     */   public boolean allowUserInput;
/*     */ 
/*     */   
/*     */   protected static FontRenderer fontRendererObj;
/*     */ 
/*     */   
/*     */   private GuiButton selectedButton;
/*     */ 
/*     */   
/*     */   protected int eventButton;
/*     */ 
/*     */   
/*     */   private long lastMouseEvent;
/*     */   
/*     */   private int touchValue;
/*     */   
/*     */   private URI field_175286_t;
/*     */   
/*     */   private static final String __OBFID = "CL_00000710";
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*     */     int var4;
/*  97 */     for (var4 = 0; var4 < this.buttonList.size(); var4++)
/*     */     {
/*  99 */       ((GuiButton)this.buttonList.get(var4)).drawButton(this.mc, mouseX, mouseY);
/*     */     }
/*     */     
/* 102 */     for (var4 = 0; var4 < this.labelList.size(); var4++)
/*     */     {
/* 104 */       ((GuiLabel)this.labelList.get(var4)).drawLabel(this.mc, mouseX, mouseY);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/* 114 */     if (keyCode == 1) {
/*     */       
/* 116 */       this.mc.displayGuiScreen(null);
/*     */       
/* 118 */       if (this.mc.currentScreen == null)
/*     */       {
/* 120 */         this.mc.setIngameFocus();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getClipboardString() {
/*     */     try {
/* 132 */       Transferable var0 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
/*     */       
/* 134 */       if (var0 != null && var0.isDataFlavorSupported(DataFlavor.stringFlavor))
/*     */       {
/* 136 */         return (String)var0.getTransferData(DataFlavor.stringFlavor);
/*     */       }
/*     */     }
/* 139 */     catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setClipboardString(String copyText) {
/* 152 */     if (!StringUtils.isEmpty(copyText)) {
/*     */       
/*     */       try {
/*     */         
/* 156 */         StringSelection var1 = new StringSelection(copyText);
/* 157 */         Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var1, null);
/*     */       }
/* 159 */       catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderToolTip(ItemStack itemIn, int x, int y) {
/* 168 */     List<String> var4 = itemIn.getTooltip((EntityPlayer)this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);
/*     */     
/* 170 */     for (int var5 = 0; var5 < var4.size(); var5++) {
/*     */       
/* 172 */       if (var5 == 0) {
/*     */         
/* 174 */         var4.set(var5, (itemIn.getRarity()).rarityColor + (String)var4.get(var5));
/*     */       }
/*     */       else {
/*     */         
/* 178 */         var4.set(var5, EnumChatFormatting.GRAY + (String)var4.get(var5));
/*     */       } 
/*     */     } 
/*     */     
/* 182 */     drawHoveringText(var4, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawCreativeTabHoveringText(String tabName, int mouseX, int mouseY) {
/* 191 */     drawHoveringText(Arrays.asList(new String[] { tabName }, ), mouseX, mouseY);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void drawHoveringText(List<String> textLines, int x, int y) {
/* 196 */     if (!textLines.isEmpty()) {
/*     */       
/* 198 */       GlStateManager.disableRescaleNormal();
/* 199 */       RenderHelper.disableStandardItemLighting();
/* 200 */       GlStateManager.disableLighting();
/* 201 */       GlStateManager.disableDepth();
/* 202 */       int var4 = 0;
/* 203 */       Iterator<String> var5 = textLines.iterator();
/*     */       
/* 205 */       while (var5.hasNext()) {
/*     */         
/* 207 */         String var6 = var5.next();
/* 208 */         int var7 = fontRendererObj.getStringWidth(var6);
/*     */         
/* 210 */         if (var7 > var4)
/*     */         {
/* 212 */           var4 = var7;
/*     */         }
/*     */       } 
/*     */       
/* 216 */       int var14 = x + 12;
/* 217 */       int var15 = y - 12;
/* 218 */       int var8 = 8;
/*     */       
/* 220 */       if (textLines.size() > 1)
/*     */       {
/* 222 */         var8 += 2 + (textLines.size() - 1) * 10;
/*     */       }
/*     */       
/* 225 */       if (var14 + var4 > width)
/*     */       {
/* 227 */         var14 -= 28 + var4;
/*     */       }
/*     */       
/* 230 */       if (var15 + var8 + 6 > height)
/*     */       {
/* 232 */         var15 = height - var8 - 6;
/*     */       }
/*     */       
/* 235 */       zLevel = 300.0F;
/* 236 */       this.itemRender.zLevel = 300.0F;
/* 237 */       int var9 = -267386864;
/* 238 */       drawGradientRect((var14 - 3), (var15 - 4), (var14 + var4 + 3), (var15 - 3), var9, var9);
/* 239 */       drawGradientRect((var14 - 3), (var15 + var8 + 3), (var14 + var4 + 3), (var15 + var8 + 4), var9, var9);
/* 240 */       drawGradientRect((var14 - 3), (var15 - 3), (var14 + var4 + 3), (var15 + var8 + 3), var9, var9);
/* 241 */       drawGradientRect((var14 - 4), (var15 - 3), (var14 - 3), (var15 + var8 + 3), var9, var9);
/* 242 */       drawGradientRect((var14 + var4 + 3), (var15 - 3), (var14 + var4 + 4), (var15 + var8 + 3), var9, var9);
/* 243 */       int var10 = 1347420415;
/* 244 */       int var11 = (var10 & 0xFEFEFE) >> 1 | var10 & 0xFF000000;
/* 245 */       drawGradientRect((var14 - 3), (var15 - 3 + 1), (var14 - 3 + 1), (var15 + var8 + 3 - 1), var10, var11);
/* 246 */       drawGradientRect((var14 + var4 + 2), (var15 - 3 + 1), (var14 + var4 + 3), (var15 + var8 + 3 - 1), var10, var11);
/* 247 */       drawGradientRect((var14 - 3), (var15 - 3), (var14 + var4 + 3), (var15 - 3 + 1), var10, var10);
/* 248 */       drawGradientRect((var14 - 3), (var15 + var8 + 2), (var14 + var4 + 3), (var15 + var8 + 3), var11, var11);
/*     */       
/* 250 */       for (int var12 = 0; var12 < textLines.size(); var12++) {
/*     */         
/* 252 */         String var13 = textLines.get(var12);
/* 253 */         fontRendererObj.drawStringWithShadow(var13, var14, var15, -1);
/*     */         
/* 255 */         if (var12 == 0)
/*     */         {
/* 257 */           var15 += 2;
/*     */         }
/*     */         
/* 260 */         var15 += 10;
/*     */       } 
/*     */       
/* 263 */       zLevel = 0.0F;
/* 264 */       this.itemRender.zLevel = 0.0F;
/* 265 */       GlStateManager.enableLighting();
/* 266 */       GlStateManager.enableDepth();
/* 267 */       RenderHelper.enableStandardItemLighting();
/* 268 */       GlStateManager.enableRescaleNormal();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175272_a(IChatComponent p_175272_1_, int p_175272_2_, int p_175272_3_) {
/* 274 */     if (p_175272_1_ != null && p_175272_1_.getChatStyle().getChatHoverEvent() != null) {
/*     */       
/* 276 */       HoverEvent var4 = p_175272_1_.getChatStyle().getChatHoverEvent();
/*     */       
/* 278 */       if (var4.getAction() == HoverEvent.Action.SHOW_ITEM) {
/*     */         
/* 280 */         ItemStack var5 = null;
/*     */ 
/*     */         
/*     */         try {
/* 284 */           NBTTagCompound var6 = JsonToNBT.func_180713_a(var4.getValue().getUnformattedText());
/*     */           
/* 286 */           if (var6 instanceof NBTTagCompound)
/*     */           {
/* 288 */             var5 = ItemStack.loadItemStackFromNBT(var6);
/*     */           }
/*     */         }
/* 291 */         catch (NBTException nBTException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 296 */         if (var5 != null)
/*     */         {
/* 298 */           renderToolTip(var5, p_175272_2_, p_175272_3_);
/*     */         }
/*     */         else
/*     */         {
/* 302 */           drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid Item!", p_175272_2_, p_175272_3_);
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 309 */       else if (var4.getAction() == HoverEvent.Action.SHOW_ENTITY) {
/*     */         
/* 311 */         if (this.mc.gameSettings.advancedItemTooltips) {
/*     */           
/*     */           try {
/*     */             
/* 315 */             NBTTagCompound var12 = JsonToNBT.func_180713_a(var4.getValue().getUnformattedText());
/*     */             
/* 317 */             if (var12 instanceof NBTTagCompound)
/*     */             {
/* 319 */               ArrayList<String> var14 = Lists.newArrayList();
/* 320 */               NBTTagCompound var7 = var12;
/* 321 */               var14.add(var7.getString("name"));
/*     */               
/* 323 */               if (var7.hasKey("type", 8)) {
/*     */                 
/* 325 */                 String var8 = var7.getString("type");
/* 326 */                 var14.add("Type: " + var8 + " (" + EntityList.func_180122_a(var8) + ")");
/*     */               } 
/*     */               
/* 329 */               var14.add(var7.getString("id"));
/* 330 */               drawHoveringText(var14, p_175272_2_, p_175272_3_);
/*     */             }
/*     */             else
/*     */             {
/* 334 */               drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid Entity!", p_175272_2_, p_175272_3_);
/*     */             }
/*     */           
/* 337 */           } catch (NBTException var10) {
/*     */             
/* 339 */             drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid Entity!", p_175272_2_, p_175272_3_);
/*     */           }
/*     */         
/*     */         }
/* 343 */       } else if (var4.getAction() == HoverEvent.Action.SHOW_TEXT) {
/*     */         
/* 345 */         drawHoveringText(field_175285_g.splitToList(var4.getValue().getFormattedText()), p_175272_2_, p_175272_3_);
/*     */       }
/* 347 */       else if (var4.getAction() == HoverEvent.Action.SHOW_ACHIEVEMENT) {
/*     */         
/* 349 */         StatBase var13 = StatList.getOneShotStat(var4.getValue().getUnformattedText());
/*     */         
/* 351 */         if (var13 != null) {
/*     */           
/* 353 */           IChatComponent var15 = var13.getStatName();
/* 354 */           ChatComponentTranslation var16 = new ChatComponentTranslation("stats.tooltip.type." + (var13.isAchievement() ? "achievement" : "statistic"), new Object[0]);
/* 355 */           var16.getChatStyle().setItalic(Boolean.valueOf(true));
/* 356 */           String var8 = (var13 instanceof Achievement) ? ((Achievement)var13).getDescription() : null;
/* 357 */           ArrayList var9 = Lists.newArrayList((Object[])new String[] { var15.getFormattedText(), var16.getFormattedText() });
/*     */           
/* 359 */           if (var8 != null)
/*     */           {
/* 361 */             var9.addAll(fontRendererObj.listFormattedStringToWidth(var8, 150));
/*     */           }
/*     */           
/* 364 */           drawHoveringText(var9, p_175272_2_, p_175272_3_);
/*     */         }
/*     */         else {
/*     */           
/* 368 */           drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid statistic/achievement!", p_175272_2_, p_175272_3_);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 373 */       GlStateManager.disableLighting();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175274_a(String p_175274_1_, boolean p_175274_2_) {}
/*     */   
/*     */   protected boolean func_175276_a(IChatComponent p_175276_1_) {
/* 381 */     if (p_175276_1_ == null)
/*     */     {
/* 383 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 387 */     ClickEvent var2 = p_175276_1_.getChatStyle().getChatClickEvent();
/*     */     
/* 389 */     if (isShiftKeyDown()) {
/*     */       
/* 391 */       if (p_175276_1_.getChatStyle().getInsertion() != null)
/*     */       {
/* 393 */         func_175274_a(p_175276_1_.getChatStyle().getInsertion(), false);
/*     */       }
/*     */     }
/* 396 */     else if (var2 != null) {
/*     */ 
/*     */ 
/*     */       
/* 400 */       if (var2.getAction() == ClickEvent.Action.OPEN_URL) {
/*     */         
/* 402 */         if (!this.mc.gameSettings.chatLinks)
/*     */         {
/* 404 */           return false;
/*     */         }
/*     */ 
/*     */         
/*     */         try {
/* 409 */           URI var3 = new URI(var2.getValue());
/*     */           
/* 411 */           if (!field_175284_f.contains(var3.getScheme().toLowerCase()))
/*     */           {
/* 413 */             throw new URISyntaxException(var2.getValue(), "Unsupported protocol: " + var3.getScheme().toLowerCase());
/*     */           }
/*     */           
/* 416 */           if (this.mc.gameSettings.chatLinksPrompt)
/*     */           {
/* 418 */             this.field_175286_t = var3;
/* 419 */             this.mc.displayGuiScreen(new GuiConfirmOpenLink(this, var2.getValue(), 31102009, false));
/*     */           }
/*     */           else
/*     */           {
/* 423 */             func_175282_a(var3);
/*     */           }
/*     */         
/* 426 */         } catch (URISyntaxException var4) {
/*     */           
/* 428 */           field_175287_a.error("Can't open url for " + var2, var4);
/*     */         }
/*     */       
/* 431 */       } else if (var2.getAction() == ClickEvent.Action.OPEN_FILE) {
/*     */         
/* 433 */         URI var3 = (new File(var2.getValue())).toURI();
/* 434 */         func_175282_a(var3);
/*     */       }
/* 436 */       else if (var2.getAction() == ClickEvent.Action.SUGGEST_COMMAND) {
/*     */         
/* 438 */         func_175274_a(var2.getValue(), true);
/*     */       }
/* 440 */       else if (var2.getAction() == ClickEvent.Action.RUN_COMMAND) {
/*     */         
/* 442 */         func_175281_b(var2.getValue(), false);
/*     */       }
/* 444 */       else if (var2.getAction() == ClickEvent.Action.TWITCH_USER_INFO) {
/*     */         
/* 446 */         ChatUserInfo var5 = this.mc.getTwitchStream().func_152926_a(var2.getValue());
/*     */         
/* 448 */         if (var5 != null)
/*     */         {
/* 450 */           this.mc.displayGuiScreen((GuiScreen)new GuiTwitchUserMode(this.mc.getTwitchStream(), var5));
/*     */         }
/*     */         else
/*     */         {
/* 454 */           field_175287_a.error("Tried to handle twitch user but couldn't find them!");
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 459 */         field_175287_a.error("Don't know how to handle " + var2);
/*     */       } 
/*     */       
/* 462 */       return true;
/*     */     } 
/*     */     
/* 465 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_175275_f(String p_175275_1_) {
/* 471 */     func_175281_b(p_175275_1_, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175281_b(String p_175281_1_, boolean p_175281_2_) {
/* 476 */     if (p_175281_2_)
/*     */     {
/* 478 */       this.mc.ingameGUI.getChatGUI().addToSentMessages(p_175281_1_);
/*     */     }
/*     */     
/* 481 */     this.mc.thePlayer.sendChatMessage(p_175281_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 489 */     if (mouseButton == 0)
/*     */     {
/* 491 */       for (int var4 = 0; var4 < this.buttonList.size(); var4++) {
/*     */         
/* 493 */         GuiButton var5 = this.buttonList.get(var4);
/*     */         
/* 495 */         if (var5.mousePressed(this.mc, mouseX, mouseY)) {
/*     */           
/* 497 */           this.selectedButton = var5;
/* 498 */           var5.playPressSound(this.mc.getSoundHandler());
/* 499 */           actionPerformed(var5);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseReleased(int mouseX, int mouseY, int state) {
/* 510 */     if (this.selectedButton != null && state == 0) {
/*     */       
/* 512 */       this.selectedButton.mouseReleased(mouseX, mouseY);
/* 513 */       this.selectedButton = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWorldAndResolution(Minecraft mc, int width, int height) {
/* 531 */     this.mc = mc;
/* 532 */     this.itemRender = mc.getRenderItem();
/* 533 */     fontRendererObj = mc.fontRendererObj;
/* 534 */     GuiScreen.width = width;
/* 535 */     GuiScreen.height = height;
/* 536 */     this.buttonList.clear();
/* 537 */     initGui();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleInput() throws IOException {
/* 550 */     if (Mouse.isCreated())
/*     */     {
/* 552 */       while (Mouse.next())
/*     */       {
/* 554 */         handleMouseInput();
/*     */       }
/*     */     }
/*     */     
/* 558 */     if (Keyboard.isCreated())
/*     */     {
/* 560 */       while (Keyboard.next())
/*     */       {
/* 562 */         handleKeyboardInput();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleMouseInput() throws IOException {
/* 572 */     int var1 = Mouse.getEventX() * width / this.mc.displayWidth;
/* 573 */     int var2 = height - Mouse.getEventY() * height / this.mc.displayHeight - 1;
/* 574 */     int var3 = Mouse.getEventButton();
/*     */     
/* 576 */     if (Mouse.getEventButtonState()) {
/*     */       
/* 578 */       if (this.mc.gameSettings.touchscreen && this.touchValue++ > 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 583 */       this.eventButton = var3;
/* 584 */       this.lastMouseEvent = Minecraft.getSystemTime();
/* 585 */       mouseClicked(var1, var2, this.eventButton);
/*     */     }
/* 587 */     else if (var3 != -1) {
/*     */       
/* 589 */       if (this.mc.gameSettings.touchscreen && --this.touchValue > 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 594 */       this.eventButton = -1;
/* 595 */       mouseReleased(var1, var2, var3);
/*     */     }
/* 597 */     else if (this.eventButton != -1 && this.lastMouseEvent > 0L) {
/*     */       
/* 599 */       long var4 = Minecraft.getSystemTime() - this.lastMouseEvent;
/* 600 */       mouseClickMove(var1, var2, this.eventButton, var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleKeyboardInput() throws IOException {
/* 609 */     if (Keyboard.getEventKeyState())
/*     */     {
/* 611 */       keyTyped(Keyboard.getEventCharacter(), Keyboard.getEventKey());
/*     */     }
/*     */     
/* 614 */     this.mc.dispatchKeypresses();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawDefaultBackground() {
/* 632 */     drawWorldBackground(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawWorldBackground(int tint) {
/* 637 */     if (this.mc.theWorld != null) {
/*     */       
/* 639 */       drawGradientRect(0.0F, 0.0F, width, height, -1072689136, -804253680);
/*     */     }
/*     */     else {
/*     */       
/* 643 */       drawBackground(tint);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawBackground(int tint) {
/* 652 */     GlStateManager.disableLighting();
/* 653 */     GlStateManager.disableFog();
/* 654 */     Tessellator var2 = Tessellator.getInstance();
/* 655 */     WorldRenderer var3 = var2.getWorldRenderer();
/* 656 */     this.mc.getTextureManager().bindTexture(optionsBackground);
/* 657 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 658 */     float var4 = 32.0F;
/* 659 */     var3.startDrawingQuads();
/* 660 */     var3.func_178991_c(4210752);
/* 661 */     var3.addVertexWithUV(0.0D, height, 0.0D, 0.0D, (height / var4 + tint));
/* 662 */     var3.addVertexWithUV(width, height, 0.0D, (width / var4), (height / var4 + tint));
/* 663 */     var3.addVertexWithUV(width, 0.0D, 0.0D, (width / var4), tint);
/* 664 */     var3.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, tint);
/* 665 */     var2.draw();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 673 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void confirmClicked(boolean result, int id) {
/* 678 */     if (id == 31102009) {
/*     */       
/* 680 */       if (result)
/*     */       {
/* 682 */         func_175282_a(this.field_175286_t);
/*     */       }
/*     */       
/* 685 */       this.field_175286_t = null;
/* 686 */       this.mc.displayGuiScreen(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void func_175282_a(URI p_175282_1_) {
/*     */     try {
/* 694 */       Class<?> var2 = Class.forName("java.awt.Desktop");
/* 695 */       Object var3 = var2.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 696 */       var2.getMethod("browse", new Class[] { URI.class }).invoke(var3, new Object[] { p_175282_1_ });
/*     */     }
/* 698 */     catch (Throwable var4) {
/*     */       
/* 700 */       field_175287_a.error("Couldn't open link", var4);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCtrlKeyDown() {
/* 709 */     return Minecraft.isRunningOnMac ? (!(!Keyboard.isKeyDown(219) && !Keyboard.isKeyDown(220))) : (!(!Keyboard.isKeyDown(29) && !Keyboard.isKeyDown(157)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isShiftKeyDown() {
/* 717 */     return !(!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_175283_s() {
/* 722 */     return !(!Keyboard.isKeyDown(56) && !Keyboard.isKeyDown(184));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_175277_d(int p_175277_0_) {
/* 727 */     return (p_175277_0_ == 45 && isCtrlKeyDown());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_175279_e(int p_175279_0_) {
/* 732 */     return (p_175279_0_ == 47 && isCtrlKeyDown());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_175280_f(int p_175280_0_) {
/* 737 */     return (p_175280_0_ == 46 && isCtrlKeyDown());
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean func_175278_g(int p_175278_0_) {
/* 742 */     return (p_175278_0_ == 30 && isCtrlKeyDown());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_175273_b(Minecraft mcIn, int p_175273_2_, int p_175273_3_) {
/* 747 */     setWorldAndResolution(mcIn, p_175273_2_, p_175273_3_);
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */