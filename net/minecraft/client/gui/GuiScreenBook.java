/*     */ package net.minecraft.client.gui;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonParseException;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.event.ClickEvent;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.ItemEditableBook;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.nbt.NBTTagString;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.client.C17PacketCustomPayload;
/*     */ import net.minecraft.util.ChatAllowedCharacters;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiScreenBook extends GuiScreen {
/*  33 */   private static final Logger logger = LogManager.getLogger();
/*  34 */   private static final ResourceLocation bookGuiTextures = new ResourceLocation("textures/gui/book.png");
/*     */ 
/*     */   
/*     */   private final EntityPlayer editingPlayer;
/*     */ 
/*     */   
/*     */   private final ItemStack bookObj;
/*     */ 
/*     */   
/*     */   private final boolean bookIsUnsigned;
/*     */ 
/*     */   
/*     */   private boolean bookIsModified;
/*     */ 
/*     */   
/*     */   private boolean bookGettingSigned;
/*     */   
/*     */   private int updateCount;
/*     */   
/*  53 */   private int bookImageWidth = 192;
/*  54 */   private int bookImageHeight = 192;
/*  55 */   private int bookTotalPages = 1;
/*     */   private int currPage;
/*     */   private NBTTagList bookPages;
/*  58 */   private String bookTitle = "";
/*     */   private List field_175386_A;
/*  60 */   private int field_175387_B = -1;
/*     */   
/*     */   private NextPageButton buttonNextPage;
/*     */   
/*     */   private NextPageButton buttonPreviousPage;
/*     */   
/*     */   private GuiButton buttonDone;
/*     */   private GuiButton buttonSign;
/*     */   private GuiButton buttonFinalize;
/*     */   private GuiButton buttonCancel;
/*     */   private static final String __OBFID = "CL_00000744";
/*     */   
/*     */   public GuiScreenBook(EntityPlayer p_i1080_1_, ItemStack p_i1080_2_, boolean p_i1080_3_) {
/*  73 */     this.editingPlayer = p_i1080_1_;
/*  74 */     this.bookObj = p_i1080_2_;
/*  75 */     this.bookIsUnsigned = p_i1080_3_;
/*     */     
/*  77 */     if (p_i1080_2_.hasTagCompound()) {
/*     */       
/*  79 */       NBTTagCompound var4 = p_i1080_2_.getTagCompound();
/*  80 */       this.bookPages = var4.getTagList("pages", 8);
/*     */       
/*  82 */       if (this.bookPages != null) {
/*     */         
/*  84 */         this.bookPages = (NBTTagList)this.bookPages.copy();
/*  85 */         this.bookTotalPages = this.bookPages.tagCount();
/*     */         
/*  87 */         if (this.bookTotalPages < 1)
/*     */         {
/*  89 */           this.bookTotalPages = 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     if (this.bookPages == null && p_i1080_3_) {
/*     */       
/*  96 */       this.bookPages = new NBTTagList();
/*  97 */       this.bookPages.appendTag((NBTBase)new NBTTagString(""));
/*  98 */       this.bookTotalPages = 1;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 107 */     super.updateScreen();
/* 108 */     this.updateCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/* 116 */     this.buttonList.clear();
/* 117 */     Keyboard.enableRepeatEvents(true);
/*     */     
/* 119 */     if (this.bookIsUnsigned) {
/*     */       
/* 121 */       this.buttonList.add(this.buttonSign = new GuiButton(3, width / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.format("book.signButton", new Object[0])));
/* 122 */       this.buttonList.add(this.buttonDone = new GuiButton(0, width / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.format("gui.done", new Object[0])));
/* 123 */       this.buttonList.add(this.buttonFinalize = new GuiButton(5, width / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.format("book.finalizeButton", new Object[0])));
/* 124 */       this.buttonList.add(this.buttonCancel = new GuiButton(4, width / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.format("gui.cancel", new Object[0])));
/*     */     }
/*     */     else {
/*     */       
/* 128 */       this.buttonList.add(this.buttonDone = new GuiButton(0, width / 2 - 100, 4 + this.bookImageHeight, 200, 20, I18n.format("gui.done", new Object[0])));
/*     */     } 
/*     */     
/* 131 */     int var1 = (width - this.bookImageWidth) / 2;
/* 132 */     byte var2 = 2;
/* 133 */     this.buttonList.add(this.buttonNextPage = new NextPageButton(1, var1 + 120, var2 + 154, true));
/* 134 */     this.buttonList.add(this.buttonPreviousPage = new NextPageButton(2, var1 + 38, var2 + 154, false));
/* 135 */     updateButtons();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 143 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateButtons() {
/* 148 */     this.buttonNextPage.visible = (!this.bookGettingSigned && (this.currPage < this.bookTotalPages - 1 || this.bookIsUnsigned));
/* 149 */     this.buttonPreviousPage.visible = (!this.bookGettingSigned && this.currPage > 0);
/* 150 */     this.buttonDone.visible = !(this.bookIsUnsigned && this.bookGettingSigned);
/*     */     
/* 152 */     if (this.bookIsUnsigned) {
/*     */       
/* 154 */       this.buttonSign.visible = !this.bookGettingSigned;
/* 155 */       this.buttonCancel.visible = this.bookGettingSigned;
/* 156 */       this.buttonFinalize.visible = this.bookGettingSigned;
/* 157 */       this.buttonFinalize.enabled = (this.bookTitle.trim().length() > 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendBookToServer(boolean p_146462_1_) throws IOException {
/* 163 */     if (this.bookIsUnsigned && this.bookIsModified)
/*     */     {
/* 165 */       if (this.bookPages != null) {
/*     */ 
/*     */ 
/*     */         
/* 169 */         while (this.bookPages.tagCount() > 1) {
/*     */           
/* 171 */           String str = this.bookPages.getStringTagAt(this.bookPages.tagCount() - 1);
/*     */           
/* 173 */           if (str.length() != 0) {
/*     */             break;
/*     */           }
/*     */ 
/*     */           
/* 178 */           this.bookPages.removeTag(this.bookPages.tagCount() - 1);
/*     */         } 
/*     */         
/* 181 */         if (this.bookObj.hasTagCompound()) {
/*     */           
/* 183 */           NBTTagCompound var6 = this.bookObj.getTagCompound();
/* 184 */           var6.setTag("pages", (NBTBase)this.bookPages);
/*     */         }
/*     */         else {
/*     */           
/* 188 */           this.bookObj.setTagInfo("pages", (NBTBase)this.bookPages);
/*     */         } 
/*     */         
/* 191 */         String var2 = "MC|BEdit";
/*     */         
/* 193 */         if (p_146462_1_) {
/*     */           
/* 195 */           var2 = "MC|BSign";
/* 196 */           this.bookObj.setTagInfo("author", (NBTBase)new NBTTagString(this.editingPlayer.getName()));
/* 197 */           this.bookObj.setTagInfo("title", (NBTBase)new NBTTagString(this.bookTitle.trim()));
/*     */           
/* 199 */           for (int var3 = 0; var3 < this.bookPages.tagCount(); var3++) {
/*     */             
/* 201 */             String var4 = this.bookPages.getStringTagAt(var3);
/* 202 */             ChatComponentText var5 = new ChatComponentText(var4);
/* 203 */             var4 = IChatComponent.Serializer.componentToJson((IChatComponent)var5);
/* 204 */             this.bookPages.set(var3, (NBTBase)new NBTTagString(var4));
/*     */           } 
/*     */           
/* 207 */           this.bookObj.setItem(Items.written_book);
/*     */         } 
/*     */         
/* 210 */         PacketBuffer var7 = new PacketBuffer(Unpooled.buffer());
/* 211 */         var7.writeItemStackToBuffer(this.bookObj);
/* 212 */         this.mc.getNetHandler().addToSendQueue((Packet)new C17PacketCustomPayload(var2, var7));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 219 */     if (button.enabled) {
/*     */       
/* 221 */       if (button.id == 0) {
/*     */         
/* 223 */         this.mc.displayGuiScreen(null);
/* 224 */         sendBookToServer(false);
/*     */       }
/* 226 */       else if (button.id == 3 && this.bookIsUnsigned) {
/*     */         
/* 228 */         this.bookGettingSigned = true;
/*     */       }
/* 230 */       else if (button.id == 1) {
/*     */         
/* 232 */         if (this.currPage < this.bookTotalPages - 1)
/*     */         {
/* 234 */           this.currPage++;
/*     */         }
/* 236 */         else if (this.bookIsUnsigned)
/*     */         {
/* 238 */           addNewPage();
/*     */           
/* 240 */           if (this.currPage < this.bookTotalPages - 1)
/*     */           {
/* 242 */             this.currPage++;
/*     */           }
/*     */         }
/*     */       
/* 246 */       } else if (button.id == 2) {
/*     */         
/* 248 */         if (this.currPage > 0)
/*     */         {
/* 250 */           this.currPage--;
/*     */         }
/*     */       }
/* 253 */       else if (button.id == 5 && this.bookGettingSigned) {
/*     */         
/* 255 */         sendBookToServer(true);
/* 256 */         this.mc.displayGuiScreen(null);
/*     */       }
/* 258 */       else if (button.id == 4 && this.bookGettingSigned) {
/*     */         
/* 260 */         this.bookGettingSigned = false;
/*     */       } 
/*     */       
/* 263 */       updateButtons();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addNewPage() {
/* 269 */     if (this.bookPages != null && this.bookPages.tagCount() < 50) {
/*     */       
/* 271 */       this.bookPages.appendTag((NBTBase)new NBTTagString(""));
/* 272 */       this.bookTotalPages++;
/* 273 */       this.bookIsModified = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/* 283 */     super.keyTyped(typedChar, keyCode);
/*     */     
/* 285 */     if (this.bookIsUnsigned)
/*     */     {
/* 287 */       if (this.bookGettingSigned) {
/*     */         
/* 289 */         keyTypedInTitle(typedChar, keyCode);
/*     */       }
/*     */       else {
/*     */         
/* 293 */         keyTypedInBook(typedChar, keyCode);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void keyTypedInBook(char p_146463_1_, int p_146463_2_) {
/* 303 */     if (GuiScreen.func_175279_e(p_146463_2_)) {
/*     */       
/* 305 */       pageInsertIntoCurrent(GuiScreen.getClipboardString());
/*     */     } else {
/*     */       String var3;
/*     */       
/* 309 */       switch (p_146463_2_) {
/*     */         
/*     */         case 14:
/* 312 */           var3 = pageGetCurrent();
/*     */           
/* 314 */           if (var3.length() > 0)
/*     */           {
/* 316 */             pageSetCurrent(var3.substring(0, var3.length() - 1));
/*     */           }
/*     */           return;
/*     */ 
/*     */         
/*     */         case 28:
/*     */         case 156:
/* 323 */           pageInsertIntoCurrent("\n");
/*     */           return;
/*     */       } 
/*     */       
/* 327 */       if (ChatAllowedCharacters.isAllowedCharacter(p_146463_1_))
/*     */       {
/* 329 */         pageInsertIntoCurrent(Character.toString(p_146463_1_));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void keyTypedInTitle(char p_146460_1_, int p_146460_2_) throws IOException {
/* 340 */     switch (p_146460_2_) {
/*     */       
/*     */       case 14:
/* 343 */         if (!this.bookTitle.isEmpty()) {
/*     */           
/* 345 */           this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
/* 346 */           updateButtons();
/*     */         } 
/*     */         return;
/*     */ 
/*     */       
/*     */       case 28:
/*     */       case 156:
/* 353 */         if (!this.bookTitle.isEmpty()) {
/*     */           
/* 355 */           sendBookToServer(true);
/* 356 */           this.mc.displayGuiScreen(null);
/*     */         } 
/*     */         return;
/*     */     } 
/*     */ 
/*     */     
/* 362 */     if (this.bookTitle.length() < 16 && ChatAllowedCharacters.isAllowedCharacter(p_146460_1_)) {
/*     */       
/* 364 */       this.bookTitle = String.valueOf(this.bookTitle) + Character.toString(p_146460_1_);
/* 365 */       updateButtons();
/* 366 */       this.bookIsModified = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String pageGetCurrent() {
/* 376 */     return (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) ? this.bookPages.getStringTagAt(this.currPage) : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void pageSetCurrent(String p_146457_1_) {
/* 384 */     if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
/*     */       
/* 386 */       this.bookPages.set(this.currPage, (NBTBase)new NBTTagString(p_146457_1_));
/* 387 */       this.bookIsModified = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void pageInsertIntoCurrent(String p_146459_1_) {
/* 396 */     String var2 = pageGetCurrent();
/* 397 */     String var3 = String.valueOf(var2) + p_146459_1_;
/* 398 */     int var4 = fontRendererObj.splitStringWidth(String.valueOf(var3) + EnumChatFormatting.BLACK + "_", 118);
/*     */     
/* 400 */     if (var4 <= 128 && var3.length() < 256)
/*     */     {
/* 402 */       pageSetCurrent(var3);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 411 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 412 */     this.mc.getTextureManager().bindTexture(bookGuiTextures);
/* 413 */     int var4 = (width - this.bookImageWidth) / 2;
/* 414 */     byte var5 = 2;
/* 415 */     drawTexturedModalRect(var4, var5, 0, 0, this.bookImageWidth, this.bookImageHeight);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 421 */     if (this.bookGettingSigned) {
/*     */       
/* 423 */       String var6 = this.bookTitle;
/*     */       
/* 425 */       if (this.bookIsUnsigned)
/*     */       {
/* 427 */         if (this.updateCount / 6 % 2 == 0) {
/*     */           
/* 429 */           var6 = String.valueOf(var6) + EnumChatFormatting.BLACK + "_";
/*     */         }
/*     */         else {
/*     */           
/* 433 */           var6 = String.valueOf(var6) + EnumChatFormatting.GRAY + "_";
/*     */         } 
/*     */       }
/*     */       
/* 437 */       String var7 = I18n.format("book.editTitle", new Object[0]);
/* 438 */       int var8 = fontRendererObj.getStringWidth(var7);
/* 439 */       fontRendererObj.drawString(var7, (var4 + 36 + (116 - var8) / 2), (var5 + 16 + 16), 0);
/* 440 */       int var9 = fontRendererObj.getStringWidth(var6);
/* 441 */       fontRendererObj.drawString(var6, (var4 + 36 + (116 - var9) / 2), (var5 + 48), 0);
/* 442 */       String var10 = I18n.format("book.byAuthor", new Object[] { this.editingPlayer.getName() });
/* 443 */       int var11 = fontRendererObj.getStringWidth(var10);
/* 444 */       fontRendererObj.drawString(EnumChatFormatting.DARK_GRAY + var10, (var4 + 36 + (116 - var11) / 2), (var5 + 48 + 10), 0);
/* 445 */       String var12 = I18n.format("book.finalizeWarning", new Object[0]);
/* 446 */       fontRendererObj.drawSplitString(var12, var4 + 36, var5 + 80, 116, 0);
/*     */     }
/*     */     else {
/*     */       
/* 450 */       String var6 = I18n.format("book.pageIndicator", new Object[] { Integer.valueOf(this.currPage + 1), Integer.valueOf(this.bookTotalPages) });
/* 451 */       String var7 = "";
/*     */       
/* 453 */       if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount())
/*     */       {
/* 455 */         var7 = this.bookPages.getStringTagAt(this.currPage);
/*     */       }
/*     */       
/* 458 */       if (this.bookIsUnsigned) {
/*     */         
/* 460 */         if (fontRendererObj.getBidiFlag())
/*     */         {
/* 462 */           var7 = String.valueOf(var7) + "_";
/*     */         }
/* 464 */         else if (this.updateCount / 6 % 2 == 0)
/*     */         {
/* 466 */           var7 = String.valueOf(var7) + EnumChatFormatting.BLACK + "_";
/*     */         }
/*     */         else
/*     */         {
/* 470 */           var7 = String.valueOf(var7) + EnumChatFormatting.GRAY + "_";
/*     */         }
/*     */       
/* 473 */       } else if (this.field_175387_B != this.currPage) {
/*     */         
/* 475 */         if (ItemEditableBook.validBookTagContents(this.bookObj.getTagCompound())) {
/*     */           
/*     */           try
/*     */           {
/* 479 */             IChatComponent var14 = IChatComponent.Serializer.jsonToComponent(var7);
/* 480 */             this.field_175386_A = (var14 != null) ? GuiUtilRenderComponents.func_178908_a(var14, 116, fontRendererObj, true, true) : null;
/*     */           }
/* 482 */           catch (JsonParseException var13)
/*     */           {
/* 484 */             this.field_175386_A = null;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 489 */           ChatComponentText var15 = new ChatComponentText(String.valueOf(EnumChatFormatting.DARK_RED.toString()) + "* Invalid book tag *");
/* 490 */           this.field_175386_A = Lists.newArrayList((Iterable)var15);
/*     */         } 
/*     */         
/* 493 */         this.field_175387_B = this.currPage;
/*     */       } 
/*     */       
/* 496 */       int var8 = fontRendererObj.getStringWidth(var6);
/* 497 */       fontRendererObj.drawString(var6, (var4 - var8 + this.bookImageWidth - 44), (var5 + 16), 0);
/*     */       
/* 499 */       if (this.field_175386_A == null) {
/*     */         
/* 501 */         fontRendererObj.drawSplitString(var7, var4 + 36, var5 + 16 + 16, 116, 0);
/*     */       }
/*     */       else {
/*     */         
/* 505 */         int var9 = Math.min(128 / fontRendererObj.FONT_HEIGHT, this.field_175386_A.size());
/*     */         
/* 507 */         for (int var16 = 0; var16 < var9; var16++) {
/*     */           
/* 509 */           IChatComponent var18 = this.field_175386_A.get(var16);
/* 510 */           fontRendererObj.drawString(var18.getUnformattedText(), (var4 + 36), (var5 + 16 + 16 + var16 * fontRendererObj.FONT_HEIGHT), 0);
/*     */         } 
/*     */         
/* 513 */         IChatComponent var17 = func_175385_b(mouseX, mouseY);
/*     */         
/* 515 */         if (var17 != null)
/*     */         {
/* 517 */           func_175272_a(var17, mouseX, mouseY);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 522 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 530 */     if (mouseButton == 0) {
/*     */       
/* 532 */       IChatComponent var4 = func_175385_b(mouseX, mouseY);
/*     */       
/* 534 */       if (func_175276_a(var4)) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 540 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_175276_a(IChatComponent p_175276_1_) {
/* 545 */     ClickEvent var2 = (p_175276_1_ == null) ? null : p_175276_1_.getChatStyle().getChatClickEvent();
/*     */     
/* 547 */     if (var2 == null)
/*     */     {
/* 549 */       return false;
/*     */     }
/* 551 */     if (var2.getAction() == ClickEvent.Action.CHANGE_PAGE) {
/*     */       
/* 553 */       String var6 = var2.getValue();
/*     */ 
/*     */       
/*     */       try {
/* 557 */         int var4 = Integer.parseInt(var6) - 1;
/*     */         
/* 559 */         if (var4 >= 0 && var4 < this.bookTotalPages && var4 != this.currPage)
/*     */         {
/* 561 */           this.currPage = var4;
/* 562 */           updateButtons();
/* 563 */           return true;
/*     */         }
/*     */       
/* 566 */       } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 571 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 575 */     boolean var3 = super.func_175276_a(p_175276_1_);
/*     */     
/* 577 */     if (var3 && var2.getAction() == ClickEvent.Action.RUN_COMMAND)
/*     */     {
/* 579 */       this.mc.displayGuiScreen(null);
/*     */     }
/*     */     
/* 582 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatComponent func_175385_b(int p_175385_1_, int p_175385_2_) {
/* 588 */     if (this.field_175386_A == null)
/*     */     {
/* 590 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 594 */     int var3 = p_175385_1_ - (width - this.bookImageWidth) / 2 - 36;
/* 595 */     int var4 = p_175385_2_ - 2 - 16 - 16;
/*     */     
/* 597 */     if (var3 >= 0 && var4 >= 0) {
/*     */       
/* 599 */       int var5 = Math.min(128 / fontRendererObj.FONT_HEIGHT, this.field_175386_A.size());
/*     */       
/* 601 */       if (var3 <= 116 && var4 < this.mc.fontRendererObj.FONT_HEIGHT * var5 + var5) {
/*     */         
/* 603 */         int var6 = var4 / this.mc.fontRendererObj.FONT_HEIGHT;
/*     */         
/* 605 */         if (var6 >= 0 && var6 < this.field_175386_A.size()) {
/*     */           
/* 607 */           IChatComponent var7 = this.field_175386_A.get(var6);
/* 608 */           int var8 = 0;
/* 609 */           Iterator<IChatComponent> var9 = var7.iterator();
/*     */           
/* 611 */           while (var9.hasNext()) {
/*     */             
/* 613 */             IChatComponent var10 = var9.next();
/*     */             
/* 615 */             if (var10 instanceof ChatComponentText) {
/*     */               
/* 617 */               var8 += this.mc.fontRendererObj.getStringWidth(((ChatComponentText)var10).getChatComponentText_TextValue());
/*     */               
/* 619 */               if (var8 > var3)
/*     */               {
/* 621 */                 return var10;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 627 */         return null;
/*     */       } 
/*     */ 
/*     */       
/* 631 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 636 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   static class NextPageButton
/*     */     extends GuiButton
/*     */   {
/*     */     private final boolean field_146151_o;
/*     */     
/*     */     private static final String __OBFID = "CL_00000745";
/*     */     
/*     */     public NextPageButton(int p_i46316_1_, int p_i46316_2_, int p_i46316_3_, boolean p_i46316_4_) {
/* 648 */       super(p_i46316_1_, p_i46316_2_, p_i46316_3_, 23, 13, "");
/* 649 */       this.field_146151_o = p_i46316_4_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawButton(Minecraft mc, int mouseX, int mouseY) {
/* 654 */       if (this.visible) {
/*     */         
/* 656 */         boolean var4 = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
/* 657 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 658 */         mc.getTextureManager().bindTexture(GuiScreenBook.bookGuiTextures);
/* 659 */         int var5 = 0;
/* 660 */         int var6 = 192;
/*     */         
/* 662 */         if (var4)
/*     */         {
/* 664 */           var5 += 23;
/*     */         }
/*     */         
/* 667 */         if (!this.field_146151_o)
/*     */         {
/* 669 */           var6 += 13;
/*     */         }
/*     */         
/* 672 */         drawTexturedModalRect(this.xPosition, this.yPosition, var5, var6, 23, 13);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiScreenBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */