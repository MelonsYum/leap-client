/*      */ package net.minecraft.client.gui.inventory;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import java.io.IOException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import net.minecraft.client.gui.GuiButton;
/*      */ import net.minecraft.client.gui.GuiScreen;
/*      */ import net.minecraft.client.gui.GuiTextField;
/*      */ import net.minecraft.client.gui.achievement.GuiAchievements;
/*      */ import net.minecraft.client.gui.achievement.GuiStats;
/*      */ import net.minecraft.client.renderer.GlStateManager;
/*      */ import net.minecraft.client.renderer.InventoryEffectRenderer;
/*      */ import net.minecraft.client.renderer.RenderHelper;
/*      */ import net.minecraft.client.resources.I18n;
/*      */ import net.minecraft.client.settings.GameSettings;
/*      */ import net.minecraft.creativetab.CreativeTabs;
/*      */ import net.minecraft.enchantment.Enchantment;
/*      */ import net.minecraft.enchantment.EnchantmentHelper;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.InventoryPlayer;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.Container;
/*      */ import net.minecraft.inventory.IInventory;
/*      */ import net.minecraft.inventory.InventoryBasic;
/*      */ import net.minecraft.inventory.Slot;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.util.EnumChatFormatting;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.ResourceLocation;
/*      */ import org.lwjgl.input.Keyboard;
/*      */ import org.lwjgl.input.Mouse;
/*      */ 
/*      */ public class GuiContainerCreative extends InventoryEffectRenderer {
/*   38 */   private static final ResourceLocation creativeInventoryTabs = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
/*   39 */   private static InventoryBasic field_147060_v = new InventoryBasic("tmp", true, 45);
/*      */ 
/*      */   
/*   42 */   private static int selectedTabIndex = CreativeTabs.tabBlock.getTabIndex();
/*      */   
/*      */   private float currentScroll;
/*      */   
/*      */   private boolean isScrolling;
/*      */   
/*      */   private boolean wasClicking;
/*      */   
/*      */   private GuiTextField searchField;
/*      */   
/*      */   private List field_147063_B;
/*      */   
/*      */   private Slot field_147064_C;
/*      */   
/*      */   private boolean field_147057_D;
/*      */   
/*      */   private CreativeCrafting field_147059_E;
/*      */   
/*      */   private static final String __OBFID = "CL_00000752";
/*      */   
/*      */   public GuiContainerCreative(EntityPlayer p_i1088_1_) {
/*   63 */     super(new ContainerCreative(p_i1088_1_));
/*   64 */     p_i1088_1_.openContainer = this.inventorySlots;
/*   65 */     this.allowUserInput = true;
/*   66 */     this.ySize = 136;
/*   67 */     this.xSize = 195;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateScreen() {
/*   75 */     if (!this.mc.playerController.isInCreativeMode())
/*      */     {
/*   77 */       this.mc.displayGuiScreen((GuiScreen)new GuiInventory((EntityPlayer)this.mc.thePlayer));
/*      */     }
/*      */     
/*   80 */     func_175378_g();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void handleMouseClick(Slot slotIn, int slotId, int clickedButton, int clickType) {
/*   88 */     this.field_147057_D = true;
/*   89 */     boolean var5 = (clickType == 1);
/*   90 */     clickType = (slotId == -999 && clickType == 0) ? 4 : clickType;
/*      */ 
/*      */ 
/*      */     
/*   94 */     if (slotIn == null && selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && clickType != 5) {
/*      */       
/*   96 */       InventoryPlayer var11 = this.mc.thePlayer.inventory;
/*      */       
/*   98 */       if (var11.getItemStack() != null)
/*      */       {
/*  100 */         if (clickedButton == 0) {
/*      */           
/*  102 */           this.mc.thePlayer.dropPlayerItemWithRandomChoice(var11.getItemStack(), true);
/*  103 */           this.mc.playerController.sendPacketDropItem(var11.getItemStack());
/*  104 */           var11.setItemStack(null);
/*      */         } 
/*      */         
/*  107 */         if (clickedButton == 1)
/*      */         {
/*  109 */           ItemStack var7 = var11.getItemStack().splitStack(1);
/*  110 */           this.mc.thePlayer.dropPlayerItemWithRandomChoice(var7, true);
/*  111 */           this.mc.playerController.sendPacketDropItem(var7);
/*      */           
/*  113 */           if ((var11.getItemStack()).stackSize == 0)
/*      */           {
/*  115 */             var11.setItemStack(null);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*  124 */     else if (slotIn == this.field_147064_C && var5) {
/*      */       
/*  126 */       for (int var10 = 0; var10 < this.mc.thePlayer.inventoryContainer.getInventory().size(); var10++)
/*      */       {
/*  128 */         this.mc.playerController.sendSlotPacket(null, var10);
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  135 */     else if (selectedTabIndex == CreativeTabs.tabInventory.getTabIndex()) {
/*      */       
/*  137 */       if (slotIn == this.field_147064_C)
/*      */       {
/*  139 */         this.mc.thePlayer.inventory.setItemStack(null);
/*      */       }
/*  141 */       else if (clickType == 4 && slotIn != null && slotIn.getHasStack())
/*      */       {
/*  143 */         ItemStack var6 = slotIn.decrStackSize((clickedButton == 0) ? 1 : slotIn.getStack().getMaxStackSize());
/*  144 */         this.mc.thePlayer.dropPlayerItemWithRandomChoice(var6, true);
/*  145 */         this.mc.playerController.sendPacketDropItem(var6);
/*      */       }
/*  147 */       else if (clickType == 4 && this.mc.thePlayer.inventory.getItemStack() != null)
/*      */       {
/*  149 */         this.mc.thePlayer.dropPlayerItemWithRandomChoice(this.mc.thePlayer.inventory.getItemStack(), true);
/*  150 */         this.mc.playerController.sendPacketDropItem(this.mc.thePlayer.inventory.getItemStack());
/*  151 */         this.mc.thePlayer.inventory.setItemStack(null);
/*      */       }
/*      */       else
/*      */       {
/*  155 */         this.mc.thePlayer.inventoryContainer.slotClick((slotIn == null) ? slotId : ((CreativeSlot)slotIn).field_148332_b.slotNumber, clickedButton, clickType, (EntityPlayer)this.mc.thePlayer);
/*  156 */         this.mc.thePlayer.inventoryContainer.detectAndSendChanges();
/*      */       }
/*      */     
/*  159 */     } else if (clickType != 5 && slotIn.inventory == field_147060_v) {
/*      */       
/*  161 */       InventoryPlayer var11 = this.mc.thePlayer.inventory;
/*  162 */       ItemStack var7 = var11.getItemStack();
/*  163 */       ItemStack var8 = slotIn.getStack();
/*      */ 
/*      */       
/*  166 */       if (clickType == 2) {
/*      */         
/*  168 */         if (var8 != null && clickedButton >= 0 && clickedButton < 9) {
/*      */           
/*  170 */           ItemStack var9 = var8.copy();
/*  171 */           var9.stackSize = var9.getMaxStackSize();
/*  172 */           this.mc.thePlayer.inventory.setInventorySlotContents(clickedButton, var9);
/*  173 */           this.mc.thePlayer.inventoryContainer.detectAndSendChanges();
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  179 */       if (clickType == 3) {
/*      */         
/*  181 */         if (var11.getItemStack() == null && slotIn.getHasStack()) {
/*      */           
/*  183 */           ItemStack var9 = slotIn.getStack().copy();
/*  184 */           var9.stackSize = var9.getMaxStackSize();
/*  185 */           var11.setItemStack(var9);
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  191 */       if (clickType == 4) {
/*      */         
/*  193 */         if (var8 != null) {
/*      */           
/*  195 */           ItemStack var9 = var8.copy();
/*  196 */           var9.stackSize = (clickedButton == 0) ? 1 : var9.getMaxStackSize();
/*  197 */           this.mc.thePlayer.dropPlayerItemWithRandomChoice(var9, true);
/*  198 */           this.mc.playerController.sendPacketDropItem(var9);
/*      */         } 
/*      */         
/*      */         return;
/*      */       } 
/*      */       
/*  204 */       if (var7 != null && var8 != null && var7.isItemEqual(var8)) {
/*      */         
/*  206 */         if (clickedButton == 0) {
/*      */           
/*  208 */           if (var5)
/*      */           {
/*  210 */             var7.stackSize = var7.getMaxStackSize();
/*      */           }
/*  212 */           else if (var7.stackSize < var7.getMaxStackSize())
/*      */           {
/*  214 */             var7.stackSize++;
/*      */           }
/*      */         
/*  217 */         } else if (var7.stackSize <= 1) {
/*      */           
/*  219 */           var11.setItemStack(null);
/*      */         }
/*      */         else {
/*      */           
/*  223 */           var7.stackSize--;
/*      */         }
/*      */       
/*  226 */       } else if (var8 != null && var7 == null) {
/*      */         
/*  228 */         var11.setItemStack(ItemStack.copyItemStack(var8));
/*  229 */         var7 = var11.getItemStack();
/*      */         
/*  231 */         if (var5)
/*      */         {
/*  233 */           var7.stackSize = var7.getMaxStackSize();
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  238 */         var11.setItemStack(null);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  243 */       this.inventorySlots.slotClick((slotIn == null) ? slotId : slotIn.slotNumber, clickedButton, clickType, (EntityPlayer)this.mc.thePlayer);
/*      */       
/*  245 */       if (Container.getDragEvent(clickedButton) == 2) {
/*      */         
/*  247 */         for (int var10 = 0; var10 < 9; var10++)
/*      */         {
/*  249 */           this.mc.playerController.sendSlotPacket(this.inventorySlots.getSlot(45 + var10).getStack(), 36 + var10);
/*      */         }
/*      */       }
/*  252 */       else if (slotIn != null) {
/*      */         
/*  254 */         ItemStack var6 = this.inventorySlots.getSlot(slotIn.slotNumber).getStack();
/*  255 */         this.mc.playerController.sendSlotPacket(var6, slotIn.slotNumber - this.inventorySlots.inventorySlots.size() + 9 + 36);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initGui() {
/*  267 */     if (this.mc.playerController.isInCreativeMode()) {
/*      */       
/*  269 */       super.initGui();
/*  270 */       this.buttonList.clear();
/*  271 */       Keyboard.enableRepeatEvents(true);
/*  272 */       this.searchField = new GuiTextField(0, fontRendererObj, guiLeft + 82, guiTop + 6, 89, fontRendererObj.FONT_HEIGHT);
/*  273 */       this.searchField.setMaxStringLength(15);
/*  274 */       this.searchField.setEnableBackgroundDrawing(false);
/*  275 */       this.searchField.setVisible(false);
/*  276 */       this.searchField.setTextColor(16777215);
/*  277 */       int var1 = selectedTabIndex;
/*  278 */       selectedTabIndex = -1;
/*  279 */       setCurrentCreativeTab(CreativeTabs.creativeTabArray[var1]);
/*  280 */       this.field_147059_E = new CreativeCrafting(this.mc);
/*  281 */       this.mc.thePlayer.inventoryContainer.onCraftGuiOpened(this.field_147059_E);
/*      */     }
/*      */     else {
/*      */       
/*  285 */       this.mc.displayGuiScreen((GuiScreen)new GuiInventory((EntityPlayer)this.mc.thePlayer));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onGuiClosed() {
/*  294 */     super.onGuiClosed();
/*      */     
/*  296 */     if (this.mc.thePlayer != null && this.mc.thePlayer.inventory != null)
/*      */     {
/*  298 */       this.mc.thePlayer.inventoryContainer.removeCraftingFromCrafters(this.field_147059_E);
/*      */     }
/*      */     
/*  301 */     Keyboard.enableRepeatEvents(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/*  310 */     if (selectedTabIndex != CreativeTabs.tabAllSearch.getTabIndex()) {
/*      */       
/*  312 */       if (GameSettings.isKeyDown(this.mc.gameSettings.keyBindChat))
/*      */       {
/*  314 */         setCurrentCreativeTab(CreativeTabs.tabAllSearch);
/*      */       }
/*      */       else
/*      */       {
/*  318 */         super.keyTyped(typedChar, keyCode);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  323 */       if (this.field_147057_D) {
/*      */         
/*  325 */         this.field_147057_D = false;
/*  326 */         this.searchField.setText("");
/*      */       } 
/*      */       
/*  329 */       if (!checkHotbarKeys(keyCode))
/*      */       {
/*  331 */         if (this.searchField.textboxKeyTyped(typedChar, keyCode)) {
/*      */           
/*  333 */           updateCreativeSearch();
/*      */         }
/*      */         else {
/*      */           
/*  337 */           super.keyTyped(typedChar, keyCode);
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateCreativeSearch() {
/*  345 */     ContainerCreative var1 = (ContainerCreative)this.inventorySlots;
/*  346 */     var1.itemList.clear();
/*  347 */     Iterator<Item> var2 = Item.itemRegistry.iterator();
/*      */     
/*  349 */     while (var2.hasNext()) {
/*      */       
/*  351 */       Item var3 = var2.next();
/*      */       
/*  353 */       if (var3 != null && var3.getCreativeTab() != null)
/*      */       {
/*  355 */         var3.getSubItems(var3, null, var1.itemList);
/*      */       }
/*      */     } 
/*      */     
/*  359 */     Enchantment[] var8 = Enchantment.enchantmentsList;
/*  360 */     int var9 = var8.length;
/*      */     
/*  362 */     for (int var4 = 0; var4 < var9; var4++) {
/*      */       
/*  364 */       Enchantment var5 = var8[var4];
/*      */       
/*  366 */       if (var5 != null && var5.type != null)
/*      */       {
/*  368 */         Items.enchanted_book.func_92113_a(var5, var1.itemList);
/*      */       }
/*      */     } 
/*      */     
/*  372 */     var2 = var1.itemList.iterator();
/*  373 */     String var10 = this.searchField.getText().toLowerCase();
/*      */     
/*  375 */     while (var2.hasNext()) {
/*      */       
/*  377 */       ItemStack var11 = (ItemStack)var2.next();
/*  378 */       boolean var12 = false;
/*  379 */       Iterator<String> var6 = var11.getTooltip((EntityPlayer)this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips).iterator();
/*      */ 
/*      */ 
/*      */       
/*  383 */       while (var6.hasNext()) {
/*      */         
/*  385 */         String var7 = var6.next();
/*      */         
/*  387 */         if (!EnumChatFormatting.getTextWithoutFormattingCodes(var7).toLowerCase().contains(var10)) {
/*      */           continue;
/*      */         }
/*      */ 
/*      */         
/*  392 */         var12 = true;
/*      */       } 
/*      */       
/*  395 */       if (!var12)
/*      */       {
/*  397 */         var2.remove();
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  404 */     this.currentScroll = 0.0F;
/*  405 */     var1.scrollTo(0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/*  413 */     CreativeTabs var3 = CreativeTabs.creativeTabArray[selectedTabIndex];
/*      */     
/*  415 */     if (var3.drawInForegroundOfTab()) {
/*      */       
/*  417 */       GlStateManager.disableBlend();
/*  418 */       fontRendererObj.drawString(I18n.format(var3.getTranslatedTabLabel(), new Object[0]), 8.0D, 6.0D, 4210752);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/*  427 */     if (mouseButton == 0) {
/*      */       
/*  429 */       int var4 = mouseX - guiLeft;
/*  430 */       int var5 = mouseY - guiTop;
/*  431 */       CreativeTabs[] var6 = CreativeTabs.creativeTabArray;
/*  432 */       int var7 = var6.length;
/*      */       
/*  434 */       for (int var8 = 0; var8 < var7; var8++) {
/*      */         
/*  436 */         CreativeTabs var9 = var6[var8];
/*      */         
/*  438 */         if (func_147049_a(var9, var4, var5)) {
/*      */           return;
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  445 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void mouseReleased(int mouseX, int mouseY, int state) {
/*  453 */     if (state == 0) {
/*      */       
/*  455 */       int var4 = mouseX - guiLeft;
/*  456 */       int var5 = mouseY - guiTop;
/*  457 */       CreativeTabs[] var6 = CreativeTabs.creativeTabArray;
/*  458 */       int var7 = var6.length;
/*      */       
/*  460 */       for (int var8 = 0; var8 < var7; var8++) {
/*      */         
/*  462 */         CreativeTabs var9 = var6[var8];
/*      */         
/*  464 */         if (func_147049_a(var9, var4, var5)) {
/*      */           
/*  466 */           setCurrentCreativeTab(var9);
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/*      */     } 
/*  472 */     super.mouseReleased(mouseX, mouseY, state);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean needsScrollBars() {
/*  480 */     return (selectedTabIndex != CreativeTabs.tabInventory.getTabIndex() && CreativeTabs.creativeTabArray[selectedTabIndex].shouldHidePlayerInventory() && ((ContainerCreative)this.inventorySlots).func_148328_e());
/*      */   }
/*      */ 
/*      */   
/*      */   private void setCurrentCreativeTab(CreativeTabs p_147050_1_) {
/*  485 */     int var2 = selectedTabIndex;
/*  486 */     selectedTabIndex = p_147050_1_.getTabIndex();
/*  487 */     ContainerCreative var3 = (ContainerCreative)this.inventorySlots;
/*  488 */     this.dragSplittingSlots.clear();
/*  489 */     var3.itemList.clear();
/*  490 */     p_147050_1_.displayAllReleventItems(var3.itemList);
/*      */     
/*  492 */     if (p_147050_1_ == CreativeTabs.tabInventory) {
/*      */       
/*  494 */       Container var4 = this.mc.thePlayer.inventoryContainer;
/*      */       
/*  496 */       if (this.field_147063_B == null)
/*      */       {
/*  498 */         this.field_147063_B = var3.inventorySlots;
/*      */       }
/*      */       
/*  501 */       var3.inventorySlots = Lists.newArrayList();
/*      */       
/*  503 */       for (int var5 = 0; var5 < var4.inventorySlots.size(); var5++) {
/*      */         
/*  505 */         CreativeSlot var6 = new CreativeSlot(var4.inventorySlots.get(var5), var5);
/*  506 */         var3.inventorySlots.add(var6);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  511 */         if (var5 >= 5 && var5 < 9) {
/*      */           
/*  513 */           int var7 = var5 - 5;
/*  514 */           int var8 = var7 / 2;
/*  515 */           int var9 = var7 % 2;
/*  516 */           var6.xDisplayPosition = 9 + var8 * 54;
/*  517 */           var6.yDisplayPosition = 6 + var9 * 27;
/*      */         }
/*  519 */         else if (var5 >= 0 && var5 < 5) {
/*      */           
/*  521 */           var6.yDisplayPosition = -2000;
/*  522 */           var6.xDisplayPosition = -2000;
/*      */         }
/*  524 */         else if (var5 < var4.inventorySlots.size()) {
/*      */           
/*  526 */           int var7 = var5 - 9;
/*  527 */           int var8 = var7 % 9;
/*  528 */           int var9 = var7 / 9;
/*  529 */           var6.xDisplayPosition = 9 + var8 * 18;
/*      */           
/*  531 */           if (var5 >= 36) {
/*      */             
/*  533 */             var6.yDisplayPosition = 112;
/*      */           }
/*      */           else {
/*      */             
/*  537 */             var6.yDisplayPosition = 54 + var9 * 18;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  542 */       this.field_147064_C = new Slot((IInventory)field_147060_v, 0, 173, 112);
/*  543 */       var3.inventorySlots.add(this.field_147064_C);
/*      */     }
/*  545 */     else if (var2 == CreativeTabs.tabInventory.getTabIndex()) {
/*      */       
/*  547 */       var3.inventorySlots = this.field_147063_B;
/*  548 */       this.field_147063_B = null;
/*      */     } 
/*      */     
/*  551 */     if (this.searchField != null)
/*      */     {
/*  553 */       if (p_147050_1_ == CreativeTabs.tabAllSearch) {
/*      */         
/*  555 */         this.searchField.setVisible(true);
/*  556 */         this.searchField.setCanLoseFocus(false);
/*  557 */         this.searchField.setFocused(true);
/*  558 */         this.searchField.setText("");
/*  559 */         updateCreativeSearch();
/*      */       }
/*      */       else {
/*      */         
/*  563 */         this.searchField.setVisible(false);
/*  564 */         this.searchField.setCanLoseFocus(true);
/*  565 */         this.searchField.setFocused(false);
/*      */       } 
/*      */     }
/*      */     
/*  569 */     this.currentScroll = 0.0F;
/*  570 */     var3.scrollTo(0.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleMouseInput() throws IOException {
/*  578 */     super.handleMouseInput();
/*  579 */     int var1 = Mouse.getEventDWheel();
/*      */     
/*  581 */     if (var1 != 0 && needsScrollBars()) {
/*      */       
/*  583 */       int var2 = ((ContainerCreative)this.inventorySlots).itemList.size() / 9 - 5 + 1;
/*      */       
/*  585 */       if (var1 > 0)
/*      */       {
/*  587 */         var1 = 1;
/*      */       }
/*      */       
/*  590 */       if (var1 < 0)
/*      */       {
/*  592 */         var1 = -1;
/*      */       }
/*      */       
/*  595 */       this.currentScroll = (float)(this.currentScroll - var1 / var2);
/*  596 */       this.currentScroll = MathHelper.clamp_float(this.currentScroll, 0.0F, 1.0F);
/*  597 */       ((ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  606 */     boolean var4 = Mouse.isButtonDown(0);
/*  607 */     int var5 = guiLeft;
/*  608 */     int var6 = guiTop;
/*  609 */     int var7 = var5 + 175;
/*  610 */     int var8 = var6 + 18;
/*  611 */     int var9 = var7 + 14;
/*  612 */     int var10 = var8 + 112;
/*      */     
/*  614 */     if (!this.wasClicking && var4 && mouseX >= var7 && mouseY >= var8 && mouseX < var9 && mouseY < var10)
/*      */     {
/*  616 */       this.isScrolling = needsScrollBars();
/*      */     }
/*      */     
/*  619 */     if (!var4)
/*      */     {
/*  621 */       this.isScrolling = false;
/*      */     }
/*      */     
/*  624 */     this.wasClicking = var4;
/*      */     
/*  626 */     if (this.isScrolling) {
/*      */       
/*  628 */       this.currentScroll = ((mouseY - var8) - 7.5F) / ((var10 - var8) - 15.0F);
/*  629 */       this.currentScroll = MathHelper.clamp_float(this.currentScroll, 0.0F, 1.0F);
/*  630 */       ((ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
/*      */     } 
/*      */     
/*  633 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*  634 */     CreativeTabs[] var11 = CreativeTabs.creativeTabArray;
/*  635 */     int var12 = var11.length;
/*      */     
/*  637 */     for (int var13 = 0; var13 < var12; var13++) {
/*      */       
/*  639 */       CreativeTabs var14 = var11[var13];
/*      */       
/*  641 */       if (renderCreativeInventoryHoveringText(var14, mouseX, mouseY)) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  647 */     if (this.field_147064_C != null && selectedTabIndex == CreativeTabs.tabInventory.getTabIndex() && isPointInRegion(this.field_147064_C.xDisplayPosition, this.field_147064_C.yDisplayPosition, 16, 16, mouseX, mouseY))
/*      */     {
/*  649 */       drawCreativeTabHoveringText(I18n.format("inventory.binSlot", new Object[0]), mouseX, mouseY);
/*      */     }
/*      */     
/*  652 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  653 */     GlStateManager.disableLighting();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void renderToolTip(ItemStack itemIn, int x, int y) {
/*  658 */     if (selectedTabIndex == CreativeTabs.tabAllSearch.getTabIndex()) {
/*      */       
/*  660 */       List<String> var4 = itemIn.getTooltip((EntityPlayer)this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);
/*  661 */       CreativeTabs var5 = itemIn.getItem().getCreativeTab();
/*      */       
/*  663 */       if (var5 == null && itemIn.getItem() == Items.enchanted_book) {
/*      */         
/*  665 */         Map var6 = EnchantmentHelper.getEnchantments(itemIn);
/*      */         
/*  667 */         if (var6.size() == 1) {
/*      */           
/*  669 */           Enchantment var7 = Enchantment.func_180306_c(((Integer)var6.keySet().iterator().next()).intValue());
/*  670 */           CreativeTabs[] var8 = CreativeTabs.creativeTabArray;
/*  671 */           int var9 = var8.length;
/*      */           
/*  673 */           for (int var10 = 0; var10 < var9; var10++) {
/*      */             
/*  675 */             CreativeTabs var11 = var8[var10];
/*      */             
/*  677 */             if (var11.hasRelevantEnchantmentType(var7.type)) {
/*      */               
/*  679 */               var5 = var11;
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  686 */       if (var5 != null)
/*      */       {
/*  688 */         var4.add(1, EnumChatFormatting.BOLD + EnumChatFormatting.BLUE + I18n.format(var5.getTranslatedTabLabel(), new Object[0]));
/*      */       }
/*      */       
/*  691 */       for (int var12 = 0; var12 < var4.size(); var12++) {
/*      */         
/*  693 */         if (var12 == 0) {
/*      */           
/*  695 */           var4.set(var12, (itemIn.getRarity()).rarityColor + (String)var4.get(var12));
/*      */         }
/*      */         else {
/*      */           
/*  699 */           var4.set(var12, EnumChatFormatting.GRAY + (String)var4.get(var12));
/*      */         } 
/*      */       } 
/*      */       
/*  703 */       drawHoveringText(var4, x, y);
/*      */     }
/*      */     else {
/*      */       
/*  707 */       super.renderToolTip(itemIn, x, y);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/*  716 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  717 */     RenderHelper.enableGUIStandardItemLighting();
/*  718 */     CreativeTabs var4 = CreativeTabs.creativeTabArray[selectedTabIndex];
/*  719 */     CreativeTabs[] var5 = CreativeTabs.creativeTabArray;
/*  720 */     int var6 = var5.length;
/*      */     
/*      */     int var7;
/*  723 */     for (var7 = 0; var7 < var6; var7++) {
/*      */       
/*  725 */       CreativeTabs var8 = var5[var7];
/*  726 */       this.mc.getTextureManager().bindTexture(creativeInventoryTabs);
/*      */       
/*  728 */       if (var8.getTabIndex() != selectedTabIndex)
/*      */       {
/*  730 */         func_147051_a(var8);
/*      */       }
/*      */     } 
/*      */     
/*  734 */     this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + var4.getBackgroundImageName()));
/*  735 */     drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.xSize, this.ySize);
/*  736 */     this.searchField.drawTextBox();
/*  737 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  738 */     int var9 = guiLeft + 175;
/*  739 */     var6 = guiTop + 18;
/*  740 */     var7 = var6 + 112;
/*  741 */     this.mc.getTextureManager().bindTexture(creativeInventoryTabs);
/*      */     
/*  743 */     if (var4.shouldHidePlayerInventory())
/*      */     {
/*  745 */       drawTexturedModalRect(var9, var6 + (int)((var7 - var6 - 17) * this.currentScroll), 232 + (needsScrollBars() ? 0 : 12), 0, 12, 15);
/*      */     }
/*      */     
/*  748 */     func_147051_a(var4);
/*      */     
/*  750 */     if (var4 == CreativeTabs.tabInventory)
/*      */     {
/*  752 */       GuiInventory.drawEntityOnScreen(guiLeft + 43, guiTop + 45, 20, (guiLeft + 43 - mouseX), (guiTop + 45 - 30 - mouseY), (EntityLivingBase)this.mc.thePlayer);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean func_147049_a(CreativeTabs p_147049_1_, int p_147049_2_, int p_147049_3_) {
/*  758 */     int var7, var4 = p_147049_1_.getTabColumn();
/*  759 */     int var5 = 28 * var4;
/*  760 */     byte var6 = 0;
/*      */     
/*  762 */     if (var4 == 5) {
/*      */       
/*  764 */       var5 = this.xSize - 28 + 2;
/*      */     }
/*  766 */     else if (var4 > 0) {
/*      */       
/*  768 */       var5 += var4;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  773 */     if (p_147049_1_.isTabInFirstRow()) {
/*      */       
/*  775 */       var7 = var6 - 32;
/*      */     }
/*      */     else {
/*      */       
/*  779 */       var7 = var6 + this.ySize;
/*      */     } 
/*      */     
/*  782 */     return (p_147049_2_ >= var5 && p_147049_2_ <= var5 + 28 && p_147049_3_ >= var7 && p_147049_3_ <= var7 + 32);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean renderCreativeInventoryHoveringText(CreativeTabs p_147052_1_, int p_147052_2_, int p_147052_3_) {
/*  791 */     int var7, var4 = p_147052_1_.getTabColumn();
/*  792 */     int var5 = 28 * var4;
/*  793 */     byte var6 = 0;
/*      */     
/*  795 */     if (var4 == 5) {
/*      */       
/*  797 */       var5 = this.xSize - 28 + 2;
/*      */     }
/*  799 */     else if (var4 > 0) {
/*      */       
/*  801 */       var5 += var4;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  806 */     if (p_147052_1_.isTabInFirstRow()) {
/*      */       
/*  808 */       var7 = var6 - 32;
/*      */     }
/*      */     else {
/*      */       
/*  812 */       var7 = var6 + this.ySize;
/*      */     } 
/*      */     
/*  815 */     if (isPointInRegion(var5 + 3, var7 + 3, 23, 27, p_147052_2_, p_147052_3_)) {
/*      */       
/*  817 */       drawCreativeTabHoveringText(I18n.format(p_147052_1_.getTranslatedTabLabel(), new Object[0]), p_147052_2_, p_147052_3_);
/*  818 */       return true;
/*      */     } 
/*      */ 
/*      */     
/*  822 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_147051_a(CreativeTabs p_147051_1_) {
/*  828 */     boolean var2 = (p_147051_1_.getTabIndex() == selectedTabIndex);
/*  829 */     boolean var3 = p_147051_1_.isTabInFirstRow();
/*  830 */     int var4 = p_147051_1_.getTabColumn();
/*  831 */     int var5 = var4 * 28;
/*  832 */     int var6 = 0;
/*  833 */     int var7 = guiLeft + 28 * var4;
/*  834 */     int var8 = guiTop;
/*  835 */     byte var9 = 32;
/*      */     
/*  837 */     if (var2)
/*      */     {
/*  839 */       var6 += 32;
/*      */     }
/*      */     
/*  842 */     if (var4 == 5) {
/*      */       
/*  844 */       var7 = guiLeft + this.xSize - 28;
/*      */     }
/*  846 */     else if (var4 > 0) {
/*      */       
/*  848 */       var7 += var4;
/*      */     } 
/*      */     
/*  851 */     if (var3) {
/*      */       
/*  853 */       var8 -= 28;
/*      */     }
/*      */     else {
/*      */       
/*  857 */       var6 += 64;
/*  858 */       var8 += this.ySize - 4;
/*      */     } 
/*      */     
/*  861 */     GlStateManager.disableLighting();
/*  862 */     drawTexturedModalRect(var7, var8, var5, var6, 28, var9);
/*  863 */     zLevel = 100.0F;
/*  864 */     this.itemRender.zLevel = 100.0F;
/*  865 */     var7 += 6;
/*  866 */     var8 += 8 + (var3 ? 1 : -1);
/*  867 */     GlStateManager.enableLighting();
/*  868 */     GlStateManager.enableRescaleNormal();
/*  869 */     ItemStack var10 = p_147051_1_.getIconItemStack();
/*  870 */     this.itemRender.func_180450_b(var10, var7, var8);
/*  871 */     this.itemRender.func_175030_a(fontRendererObj, var10, var7, var8);
/*  872 */     GlStateManager.disableLighting();
/*  873 */     this.itemRender.zLevel = 0.0F;
/*  874 */     zLevel = 0.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void actionPerformed(GuiButton button) throws IOException {
/*  879 */     if (button.id == 0)
/*      */     {
/*  881 */       this.mc.displayGuiScreen((GuiScreen)new GuiAchievements((GuiScreen)this, this.mc.thePlayer.getStatFileWriter()));
/*      */     }
/*      */     
/*  884 */     if (button.id == 1)
/*      */     {
/*  886 */       this.mc.displayGuiScreen((GuiScreen)new GuiStats((GuiScreen)this, this.mc.thePlayer.getStatFileWriter()));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_147056_g() {
/*  892 */     return selectedTabIndex;
/*      */   }
/*      */   
/*      */   static class ContainerCreative
/*      */     extends Container {
/*  897 */     public List itemList = Lists.newArrayList();
/*      */     
/*      */     private static final String __OBFID = "CL_00000753";
/*      */     
/*      */     public ContainerCreative(EntityPlayer p_i1086_1_) {
/*  902 */       InventoryPlayer var2 = p_i1086_1_.inventory;
/*      */       
/*      */       int var3;
/*  905 */       for (var3 = 0; var3 < 5; var3++) {
/*      */         
/*  907 */         for (int var4 = 0; var4 < 9; var4++)
/*      */         {
/*  909 */           addSlotToContainer(new Slot((IInventory)GuiContainerCreative.field_147060_v, var3 * 9 + var4, 9 + var4 * 18, 18 + var3 * 18));
/*      */         }
/*      */       } 
/*      */       
/*  913 */       for (var3 = 0; var3 < 9; var3++)
/*      */       {
/*  915 */         addSlotToContainer(new Slot((IInventory)var2, var3, 9 + var3 * 18, 112));
/*      */       }
/*      */       
/*  918 */       scrollTo(0.0F);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canInteractWith(EntityPlayer playerIn) {
/*  923 */       return true;
/*      */     }
/*      */ 
/*      */     
/*      */     public void scrollTo(float p_148329_1_) {
/*  928 */       int var2 = (this.itemList.size() + 8) / 9 - 5;
/*  929 */       int var3 = (int)((p_148329_1_ * var2) + 0.5D);
/*      */       
/*  931 */       if (var3 < 0)
/*      */       {
/*  933 */         var3 = 0;
/*      */       }
/*      */       
/*  936 */       for (int var4 = 0; var4 < 5; var4++) {
/*      */         
/*  938 */         for (int var5 = 0; var5 < 9; var5++) {
/*      */           
/*  940 */           int var6 = var5 + (var4 + var3) * 9;
/*      */           
/*  942 */           if (var6 >= 0 && var6 < this.itemList.size()) {
/*      */             
/*  944 */             GuiContainerCreative.field_147060_v.setInventorySlotContents(var5 + var4 * 9, this.itemList.get(var6));
/*      */           }
/*      */           else {
/*      */             
/*  948 */             GuiContainerCreative.field_147060_v.setInventorySlotContents(var5 + var4 * 9, null);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean func_148328_e() {
/*  956 */       return (this.itemList.size() > 45);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void retrySlotClick(int p_75133_1_, int p_75133_2_, boolean p_75133_3_, EntityPlayer p_75133_4_) {}
/*      */     
/*      */     public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
/*  963 */       if (index >= this.inventorySlots.size() - 9 && index < this.inventorySlots.size()) {
/*      */         
/*  965 */         Slot var3 = this.inventorySlots.get(index);
/*      */         
/*  967 */         if (var3 != null && var3.getHasStack())
/*      */         {
/*  969 */           var3.putStack(null);
/*      */         }
/*      */       } 
/*      */       
/*  973 */       return null;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean func_94530_a(ItemStack p_94530_1_, Slot p_94530_2_) {
/*  978 */       return (p_94530_2_.yDisplayPosition > 90);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean canDragIntoSlot(Slot p_94531_1_) {
/*  983 */       return !(!(p_94531_1_.inventory instanceof InventoryPlayer) && (p_94531_1_.yDisplayPosition <= 90 || p_94531_1_.xDisplayPosition > 162));
/*      */     }
/*      */   }
/*      */   
/*      */   class CreativeSlot
/*      */     extends Slot
/*      */   {
/*      */     private final Slot field_148332_b;
/*      */     private static final String __OBFID = "CL_00000754";
/*      */     
/*      */     public CreativeSlot(Slot p_i46313_2_, int p_i46313_3_) {
/*  994 */       super(p_i46313_2_.inventory, p_i46313_3_, 0, 0);
/*  995 */       this.field_148332_b = p_i46313_2_;
/*      */     }
/*      */ 
/*      */     
/*      */     public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
/* 1000 */       this.field_148332_b.onPickupFromSlot(playerIn, stack);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isItemValid(ItemStack stack) {
/* 1005 */       return this.field_148332_b.isItemValid(stack);
/*      */     }
/*      */ 
/*      */     
/*      */     public ItemStack getStack() {
/* 1010 */       return this.field_148332_b.getStack();
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean getHasStack() {
/* 1015 */       return this.field_148332_b.getHasStack();
/*      */     }
/*      */ 
/*      */     
/*      */     public void putStack(ItemStack p_75215_1_) {
/* 1020 */       this.field_148332_b.putStack(p_75215_1_);
/*      */     }
/*      */ 
/*      */     
/*      */     public void onSlotChanged() {
/* 1025 */       this.field_148332_b.onSlotChanged();
/*      */     }
/*      */ 
/*      */     
/*      */     public int getSlotStackLimit() {
/* 1030 */       return this.field_148332_b.getSlotStackLimit();
/*      */     }
/*      */ 
/*      */     
/*      */     public int func_178170_b(ItemStack p_178170_1_) {
/* 1035 */       return this.field_148332_b.func_178170_b(p_178170_1_);
/*      */     }
/*      */ 
/*      */     
/*      */     public String func_178171_c() {
/* 1040 */       return this.field_148332_b.func_178171_c();
/*      */     }
/*      */ 
/*      */     
/*      */     public ItemStack decrStackSize(int p_75209_1_) {
/* 1045 */       return this.field_148332_b.decrStackSize(p_75209_1_);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean isHere(IInventory p_75217_1_, int p_75217_2_) {
/* 1050 */       return this.field_148332_b.isHere(p_75217_1_, p_75217_2_);
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiContainerCreative.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */