/*     */ package net.minecraft.client.gui.inventory;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import leap.Client;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.texture.TextureAtlasSprite;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class GuiContainer
/*     */   extends GuiScreen
/*     */ {
/*  34 */   protected static final ResourceLocation inventoryBackground = new ResourceLocation("textures/gui/container/inventory.png");
/*     */ 
/*     */   
/*  37 */   protected int xSize = 176;
/*     */ 
/*     */   
/*  40 */   protected int ySize = 166;
/*     */ 
/*     */   
/*     */   public Container inventorySlots;
/*     */ 
/*     */   
/*     */   public static int guiLeft;
/*     */ 
/*     */   
/*     */   public static int guiTop;
/*     */ 
/*     */   
/*     */   private Slot theSlot;
/*     */ 
/*     */   
/*     */   private Slot clickedSlot;
/*     */   
/*     */   private boolean isRightMouseClick;
/*     */   
/*     */   private ItemStack draggedStack;
/*     */   
/*     */   private int touchUpX;
/*     */   
/*     */   private int touchUpY;
/*     */   
/*     */   private Slot returningStackDestSlot;
/*     */   
/*     */   private long returningStackTime;
/*     */   
/*     */   private ItemStack returningStack;
/*     */   
/*     */   private Slot currentDragTargetSlot;
/*     */   
/*     */   private long dragItemDropDelay;
/*     */   
/*  75 */   protected final Set dragSplittingSlots = Sets.newHashSet();
/*     */   
/*     */   protected boolean dragSplitting;
/*     */   private int dragSplittingLimit;
/*     */   private int dragSplittingButton;
/*     */   private boolean ignoreMouseUp;
/*     */   private int dragSplittingRemnant;
/*     */   private long lastClickTime;
/*     */   private Slot lastClickSlot;
/*     */   private int lastClickButton;
/*     */   private boolean doubleClick;
/*     */   private ItemStack shiftClickedSlot;
/*     */   private static final String __OBFID = "CL_00000737";
/*     */   
/*     */   public GuiContainer(Container p_i1072_1_) {
/*  90 */     this.inventorySlots = p_i1072_1_;
/*  91 */     this.ignoreMouseUp = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  99 */     this.buttonList.add(new GuiButton(54, 1, 1, "Disable KillAura"));
/* 100 */     this.buttonList.add(new GuiButton(69, 1, 24, "Disable ChestSteal"));
/*     */     
/* 102 */     super.initGui();
/* 103 */     this.mc.thePlayer.openContainer = this.inventorySlots;
/* 104 */     guiLeft = (width - this.xSize) / 2;
/* 105 */     guiTop = (height - this.ySize) / 2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 111 */     if (button.id == 69)
/*     */     {
/* 113 */       (Client.getModule("ChestSteal")).toggled = false;
/*     */     }
/*     */     
/* 116 */     if (button.id == 54)
/*     */     {
/* 118 */       (Client.getModule("KillAura")).toggled = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 127 */     ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/*     */     
/* 129 */     int var4 = guiLeft;
/* 130 */     int var5 = guiTop;
/* 131 */     drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
/* 132 */     GlStateManager.disableRescaleNormal();
/* 133 */     RenderHelper.disableStandardItemLighting();
/* 134 */     GlStateManager.disableLighting();
/* 135 */     GlStateManager.disableDepth();
/* 136 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 137 */     RenderHelper.enableGUIStandardItemLighting();
/* 138 */     GlStateManager.pushMatrix();
/* 139 */     GlStateManager.translate(var4, var5, 0.0F);
/* 140 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 141 */     GlStateManager.enableRescaleNormal();
/* 142 */     this.theSlot = null;
/* 143 */     short var6 = 240;
/* 144 */     short var7 = 240;
/* 145 */     OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, var6 / 1.0F, var7 / 1.0F);
/* 146 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */ 
/*     */     
/* 149 */     for (int var8 = 0; var8 < this.inventorySlots.inventorySlots.size(); var8++) {
/*     */       
/* 151 */       Slot var9 = this.inventorySlots.inventorySlots.get(var8);
/* 152 */       drawSlot(var9);
/*     */       
/* 154 */       if (isMouseOverSlot(var9, mouseX, mouseY) && var9.canBeHovered()) {
/*     */         
/* 156 */         this.theSlot = var9;
/* 157 */         GlStateManager.disableLighting();
/* 158 */         GlStateManager.disableDepth();
/* 159 */         int var10 = var9.xDisplayPosition;
/* 160 */         int var11 = var9.yDisplayPosition;
/* 161 */         GlStateManager.colorMask(true, true, true, false);
/* 162 */         drawGradientRect(var10, var11, (var10 + 16), (var11 + 16), -2130706433, -2130706433);
/* 163 */         GlStateManager.colorMask(true, true, true, true);
/* 164 */         GlStateManager.enableLighting();
/* 165 */         GlStateManager.enableDepth();
/*     */       } 
/*     */     } 
/*     */     
/* 169 */     RenderHelper.disableStandardItemLighting();
/* 170 */     drawGuiContainerForegroundLayer(mouseX, mouseY);
/* 171 */     RenderHelper.enableGUIStandardItemLighting();
/* 172 */     InventoryPlayer var15 = this.mc.thePlayer.inventory;
/* 173 */     ItemStack var16 = (this.draggedStack == null) ? var15.getItemStack() : this.draggedStack;
/*     */     
/* 175 */     if (var16 != null) {
/*     */       
/* 177 */       byte var17 = 8;
/* 178 */       int var11 = (this.draggedStack == null) ? 8 : 16;
/* 179 */       String var12 = null;
/*     */       
/* 181 */       if (this.draggedStack != null && this.isRightMouseClick) {
/*     */         
/* 183 */         var16 = var16.copy();
/* 184 */         var16.stackSize = MathHelper.ceiling_float_int(var16.stackSize / 2.0F);
/*     */       }
/* 186 */       else if (this.dragSplitting && this.dragSplittingSlots.size() > 1) {
/*     */         
/* 188 */         var16 = var16.copy();
/* 189 */         var16.stackSize = this.dragSplittingRemnant;
/*     */         
/* 191 */         if (var16.stackSize == 0)
/*     */         {
/* 193 */           var12 = EnumChatFormatting.YELLOW + "0";
/*     */         }
/*     */       } 
/*     */       
/* 197 */       drawItemStack(var16, mouseX - var4 - var17, mouseY - var5 - var11, var12);
/*     */     } 
/*     */     
/* 200 */     if (this.returningStack != null) {
/*     */       
/* 202 */       float var18 = (float)(Minecraft.getSystemTime() - this.returningStackTime) / 100.0F;
/*     */       
/* 204 */       if (var18 >= 1.0F) {
/*     */         
/* 206 */         var18 = 1.0F;
/* 207 */         this.returningStack = null;
/*     */       } 
/*     */       
/* 210 */       int var11 = this.returningStackDestSlot.xDisplayPosition - this.touchUpX;
/* 211 */       int var20 = this.returningStackDestSlot.yDisplayPosition - this.touchUpY;
/* 212 */       int var13 = this.touchUpX + (int)(var11 * var18);
/* 213 */       int var14 = this.touchUpY + (int)(var20 * var18);
/* 214 */       drawItemStack(this.returningStack, var13, var14, (String)null);
/*     */     } 
/*     */     
/* 217 */     GlStateManager.popMatrix();
/*     */     
/* 219 */     if (var15.getItemStack() == null && this.theSlot != null && this.theSlot.getHasStack()) {
/*     */       
/* 221 */       ItemStack var19 = this.theSlot.getStack();
/* 222 */       renderToolTip(var19, mouseX, mouseY);
/*     */     } 
/*     */     
/* 225 */     GlStateManager.enableLighting();
/* 226 */     GlStateManager.enableDepth();
/* 227 */     RenderHelper.enableStandardItemLighting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawItemStack(ItemStack stack, int x, int y, String altText) {
/* 235 */     GlStateManager.translate(0.0F, 0.0F, 32.0F);
/* 236 */     zLevel = 200.0F;
/* 237 */     this.itemRender.zLevel = 200.0F;
/* 238 */     this.itemRender.func_180450_b(stack, x, y);
/* 239 */     this.itemRender.func_180453_a(fontRendererObj, stack, x, y - ((this.draggedStack == null) ? 0 : 8), altText);
/* 240 */     zLevel = 0.0F;
/* 241 */     this.itemRender.zLevel = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void drawGuiContainerBackgroundLayer(float paramFloat, int paramInt1, int paramInt2);
/*     */ 
/*     */ 
/*     */   
/*     */   private void drawSlot(Slot slotIn) {
/* 256 */     int var2 = slotIn.xDisplayPosition;
/* 257 */     int var3 = slotIn.yDisplayPosition;
/* 258 */     ItemStack var4 = slotIn.getStack();
/* 259 */     boolean var5 = false;
/* 260 */     boolean var6 = (slotIn == this.clickedSlot && this.draggedStack != null && !this.isRightMouseClick);
/* 261 */     ItemStack var7 = this.mc.thePlayer.inventory.getItemStack();
/* 262 */     String var8 = null;
/*     */     
/* 264 */     if (slotIn == this.clickedSlot && this.draggedStack != null && this.isRightMouseClick && var4 != null) {
/*     */       
/* 266 */       var4 = var4.copy();
/* 267 */       var4.stackSize /= 2;
/*     */     }
/* 269 */     else if (this.dragSplitting && this.dragSplittingSlots.contains(slotIn) && var7 != null) {
/*     */       
/* 271 */       if (this.dragSplittingSlots.size() == 1) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 276 */       if (Container.canAddItemToSlot(slotIn, var7, true) && this.inventorySlots.canDragIntoSlot(slotIn)) {
/*     */         
/* 278 */         var4 = var7.copy();
/* 279 */         var5 = true;
/* 280 */         Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, var4, (slotIn.getStack() == null) ? 0 : (slotIn.getStack()).stackSize);
/*     */         
/* 282 */         if (var4.stackSize > var4.getMaxStackSize()) {
/*     */           
/* 284 */           var8 = EnumChatFormatting.YELLOW + var4.getMaxStackSize();
/* 285 */           var4.stackSize = var4.getMaxStackSize();
/*     */         } 
/*     */         
/* 288 */         if (var4.stackSize > slotIn.func_178170_b(var4))
/*     */         {
/* 290 */           var8 = EnumChatFormatting.YELLOW + slotIn.func_178170_b(var4);
/* 291 */           var4.stackSize = slotIn.func_178170_b(var4);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 296 */         this.dragSplittingSlots.remove(slotIn);
/* 297 */         updateDragSplitting();
/*     */       } 
/*     */     } 
/*     */     
/* 301 */     zLevel = 100.0F;
/* 302 */     this.itemRender.zLevel = 100.0F;
/*     */     
/* 304 */     if (var4 == null) {
/*     */       
/* 306 */       String var9 = slotIn.func_178171_c();
/*     */       
/* 308 */       if (var9 != null) {
/*     */         
/* 310 */         TextureAtlasSprite var10 = this.mc.getTextureMapBlocks().getAtlasSprite(var9);
/* 311 */         GlStateManager.disableLighting();
/* 312 */         this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
/* 313 */         func_175175_a(var2, var3, var10, 16, 16);
/* 314 */         GlStateManager.enableLighting();
/* 315 */         var6 = true;
/*     */       } 
/*     */     } 
/*     */     
/* 319 */     if (!var6) {
/*     */       
/* 321 */       if (var5)
/*     */       {
/* 323 */         drawRect(var2, var3, (var2 + 16), (var3 + 16), -2130706433);
/*     */       }
/*     */       
/* 326 */       GlStateManager.enableDepth();
/* 327 */       this.itemRender.func_180450_b(var4, var2, var3);
/* 328 */       this.itemRender.func_180453_a(fontRendererObj, var4, var2, var3, var8);
/*     */     } 
/*     */     
/* 331 */     this.itemRender.zLevel = 0.0F;
/* 332 */     zLevel = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateDragSplitting() {
/* 337 */     ItemStack var1 = this.mc.thePlayer.inventory.getItemStack();
/*     */     
/* 339 */     if (var1 != null && this.dragSplitting) {
/*     */       
/* 341 */       this.dragSplittingRemnant = var1.stackSize;
/*     */ 
/*     */ 
/*     */       
/* 345 */       for (Iterator<Slot> var2 = this.dragSplittingSlots.iterator(); var2.hasNext(); this.dragSplittingRemnant -= var4.stackSize - var5) {
/*     */         
/* 347 */         Slot var3 = var2.next();
/* 348 */         ItemStack var4 = var1.copy();
/* 349 */         int var5 = (var3.getStack() == null) ? 0 : (var3.getStack()).stackSize;
/* 350 */         Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, var4, var5);
/*     */         
/* 352 */         if (var4.stackSize > var4.getMaxStackSize())
/*     */         {
/* 354 */           var4.stackSize = var4.getMaxStackSize();
/*     */         }
/*     */         
/* 357 */         if (var4.stackSize > var3.func_178170_b(var4))
/*     */         {
/* 359 */           var4.stackSize = var3.func_178170_b(var4);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Slot getSlotAtPosition(int x, int y) {
/* 370 */     for (int var3 = 0; var3 < this.inventorySlots.inventorySlots.size(); var3++) {
/*     */       
/* 372 */       Slot var4 = this.inventorySlots.inventorySlots.get(var3);
/*     */       
/* 374 */       if (isMouseOverSlot(var4, x, y))
/*     */       {
/* 376 */         return var4;
/*     */       }
/*     */     } 
/*     */     
/* 380 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 388 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 389 */     boolean var4 = (mouseButton == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100);
/* 390 */     Slot var5 = getSlotAtPosition(mouseX, mouseY);
/* 391 */     long var6 = Minecraft.getSystemTime();
/* 392 */     this.doubleClick = (this.lastClickSlot == var5 && var6 - this.lastClickTime < 250L && this.lastClickButton == mouseButton);
/* 393 */     this.ignoreMouseUp = false;
/*     */     
/* 395 */     if (mouseButton == 0 || mouseButton == 1 || var4) {
/*     */       
/* 397 */       int var8 = guiLeft;
/* 398 */       int var9 = guiTop;
/* 399 */       boolean var10 = !(mouseX >= var8 && mouseY >= var9 && mouseX < var8 + this.xSize && mouseY < var9 + this.ySize);
/* 400 */       int var11 = -1;
/*     */       
/* 402 */       if (var5 != null)
/*     */       {
/* 404 */         var11 = var5.slotNumber;
/*     */       }
/*     */       
/* 407 */       if (var10)
/*     */       {
/* 409 */         var11 = -999;
/*     */       }
/*     */       
/* 412 */       if (this.mc.gameSettings.touchscreen && var10 && this.mc.thePlayer.inventory.getItemStack() == null) {
/*     */         
/* 414 */         this.mc.displayGuiScreen(null);
/*     */         
/*     */         return;
/*     */       } 
/* 418 */       if (var11 != -1)
/*     */       {
/* 420 */         if (this.mc.gameSettings.touchscreen) {
/*     */           
/* 422 */           if (var5 != null && var5.getHasStack())
/*     */           {
/* 424 */             this.clickedSlot = var5;
/* 425 */             this.draggedStack = null;
/* 426 */             this.isRightMouseClick = (mouseButton == 1);
/*     */           }
/*     */           else
/*     */           {
/* 430 */             this.clickedSlot = null;
/*     */           }
/*     */         
/* 433 */         } else if (!this.dragSplitting) {
/*     */           
/* 435 */           if (this.mc.thePlayer.inventory.getItemStack() == null) {
/*     */             
/* 437 */             if (mouseButton == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
/*     */               
/* 439 */               handleMouseClick(var5, var11, mouseButton, 3);
/*     */             }
/*     */             else {
/*     */               
/* 443 */               boolean var12 = (var11 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)));
/* 444 */               byte var13 = 0;
/*     */               
/* 446 */               if (var12) {
/*     */                 
/* 448 */                 this.shiftClickedSlot = (var5 != null && var5.getHasStack()) ? var5.getStack() : null;
/* 449 */                 var13 = 1;
/*     */               }
/* 451 */               else if (var11 == -999) {
/*     */                 
/* 453 */                 var13 = 4;
/*     */               } 
/*     */               
/* 456 */               handleMouseClick(var5, var11, mouseButton, var13);
/*     */             } 
/*     */             
/* 459 */             this.ignoreMouseUp = true;
/*     */           }
/*     */           else {
/*     */             
/* 463 */             this.dragSplitting = true;
/* 464 */             this.dragSplittingButton = mouseButton;
/* 465 */             this.dragSplittingSlots.clear();
/*     */             
/* 467 */             if (mouseButton == 0) {
/*     */               
/* 469 */               this.dragSplittingLimit = 0;
/*     */             }
/* 471 */             else if (mouseButton == 1) {
/*     */               
/* 473 */               this.dragSplittingLimit = 1;
/*     */             }
/* 475 */             else if (mouseButton == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
/*     */               
/* 477 */               this.dragSplittingLimit = 2;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 484 */     this.lastClickSlot = var5;
/* 485 */     this.lastClickTime = var6;
/* 486 */     this.lastClickButton = mouseButton;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
/* 495 */     Slot var6 = getSlotAtPosition(mouseX, mouseY);
/* 496 */     ItemStack var7 = this.mc.thePlayer.inventory.getItemStack();
/*     */     
/* 498 */     if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
/*     */       
/* 500 */       if (clickedMouseButton == 0 || clickedMouseButton == 1)
/*     */       {
/* 502 */         if (this.draggedStack == null) {
/*     */           
/* 504 */           if (var6 != this.clickedSlot)
/*     */           {
/* 506 */             this.draggedStack = this.clickedSlot.getStack().copy();
/*     */           }
/*     */         }
/* 509 */         else if (this.draggedStack.stackSize > 1 && var6 != null && Container.canAddItemToSlot(var6, this.draggedStack, false)) {
/*     */           
/* 511 */           long var8 = Minecraft.getSystemTime();
/*     */           
/* 513 */           if (this.currentDragTargetSlot == var6) {
/*     */             
/* 515 */             if (var8 - this.dragItemDropDelay > 500L)
/*     */             {
/* 517 */               handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
/* 518 */               handleMouseClick(var6, var6.slotNumber, 1, 0);
/* 519 */               handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, 0);
/* 520 */               this.dragItemDropDelay = var8 + 750L;
/* 521 */               this.draggedStack.stackSize--;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 526 */             this.currentDragTargetSlot = var6;
/* 527 */             this.dragItemDropDelay = var8;
/*     */           }
/*     */         
/*     */         } 
/*     */       }
/* 532 */     } else if (this.dragSplitting && var6 != null && var7 != null && var7.stackSize > this.dragSplittingSlots.size() && Container.canAddItemToSlot(var6, var7, true) && var6.isItemValid(var7) && this.inventorySlots.canDragIntoSlot(var6)) {
/*     */       
/* 534 */       this.dragSplittingSlots.add(var6);
/* 535 */       updateDragSplitting();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseReleased(int mouseX, int mouseY, int state) {
/* 544 */     Slot var4 = getSlotAtPosition(mouseX, mouseY);
/* 545 */     int var5 = guiLeft;
/* 546 */     int var6 = guiTop;
/* 547 */     boolean var7 = !(mouseX >= var5 && mouseY >= var6 && mouseX < var5 + this.xSize && mouseY < var6 + this.ySize);
/* 548 */     int var8 = -1;
/*     */     
/* 550 */     if (var4 != null)
/*     */     {
/* 552 */       var8 = var4.slotNumber;
/*     */     }
/*     */     
/* 555 */     if (var7)
/*     */     {
/* 557 */       var8 = -999;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 563 */     if (this.doubleClick && var4 != null && state == 0 && this.inventorySlots.func_94530_a(null, var4)) {
/*     */       
/* 565 */       if (isShiftKeyDown()) {
/*     */         
/* 567 */         if (var4 != null && var4.inventory != null && this.shiftClickedSlot != null) {
/*     */           
/* 569 */           Iterator<Slot> var11 = this.inventorySlots.inventorySlots.iterator();
/*     */           
/* 571 */           while (var11.hasNext())
/*     */           {
/* 573 */             Slot var10 = var11.next();
/*     */             
/* 575 */             if (var10 != null && var10.canTakeStack((EntityPlayer)this.mc.thePlayer) && var10.getHasStack() && var10.inventory == var4.inventory && Container.canAddItemToSlot(var10, this.shiftClickedSlot, true))
/*     */             {
/* 577 */               handleMouseClick(var10, var10.slotNumber, state, 1);
/*     */             }
/*     */           }
/*     */         
/*     */         } 
/*     */       } else {
/*     */         
/* 584 */         handleMouseClick(var4, var8, state, 6);
/*     */       } 
/*     */       
/* 587 */       this.doubleClick = false;
/* 588 */       this.lastClickTime = 0L;
/*     */     }
/*     */     else {
/*     */       
/* 592 */       if (this.dragSplitting && this.dragSplittingButton != state) {
/*     */         
/* 594 */         this.dragSplitting = false;
/* 595 */         this.dragSplittingSlots.clear();
/* 596 */         this.ignoreMouseUp = true;
/*     */         
/*     */         return;
/*     */       } 
/* 600 */       if (this.ignoreMouseUp) {
/*     */         
/* 602 */         this.ignoreMouseUp = false;
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 608 */       if (this.clickedSlot != null && this.mc.gameSettings.touchscreen) {
/*     */         
/* 610 */         if (state == 0 || state == 1)
/*     */         {
/* 612 */           if (this.draggedStack == null && var4 != this.clickedSlot)
/*     */           {
/* 614 */             this.draggedStack = this.clickedSlot.getStack();
/*     */           }
/*     */           
/* 617 */           boolean var9 = Container.canAddItemToSlot(var4, this.draggedStack, false);
/*     */           
/* 619 */           if (var8 != -1 && this.draggedStack != null && var9) {
/*     */             
/* 621 */             handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, 0);
/* 622 */             handleMouseClick(var4, var8, 0, 0);
/*     */             
/* 624 */             if (this.mc.thePlayer.inventory.getItemStack() != null)
/*     */             {
/* 626 */               handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, 0);
/* 627 */               this.touchUpX = mouseX - var5;
/* 628 */               this.touchUpY = mouseY - var6;
/* 629 */               this.returningStackDestSlot = this.clickedSlot;
/* 630 */               this.returningStack = this.draggedStack;
/* 631 */               this.returningStackTime = Minecraft.getSystemTime();
/*     */             }
/*     */             else
/*     */             {
/* 635 */               this.returningStack = null;
/*     */             }
/*     */           
/* 638 */           } else if (this.draggedStack != null) {
/*     */             
/* 640 */             this.touchUpX = mouseX - var5;
/* 641 */             this.touchUpY = mouseY - var6;
/* 642 */             this.returningStackDestSlot = this.clickedSlot;
/* 643 */             this.returningStack = this.draggedStack;
/* 644 */             this.returningStackTime = Minecraft.getSystemTime();
/*     */           } 
/*     */           
/* 647 */           this.draggedStack = null;
/* 648 */           this.clickedSlot = null;
/*     */         }
/*     */       
/* 651 */       } else if (this.dragSplitting && !this.dragSplittingSlots.isEmpty()) {
/*     */         
/* 653 */         handleMouseClick((Slot)null, -999, Container.func_94534_d(0, this.dragSplittingLimit), 5);
/* 654 */         Iterator<Slot> var11 = this.dragSplittingSlots.iterator();
/*     */         
/* 656 */         while (var11.hasNext()) {
/*     */           
/* 658 */           Slot var10 = var11.next();
/* 659 */           handleMouseClick(var10, var10.slotNumber, Container.func_94534_d(1, this.dragSplittingLimit), 5);
/*     */         } 
/*     */         
/* 662 */         handleMouseClick((Slot)null, -999, Container.func_94534_d(2, this.dragSplittingLimit), 5);
/*     */       }
/* 664 */       else if (this.mc.thePlayer.inventory.getItemStack() != null) {
/*     */         
/* 666 */         if (state == this.mc.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
/*     */           
/* 668 */           handleMouseClick(var4, var8, state, 3);
/*     */         }
/*     */         else {
/*     */           
/* 672 */           boolean var9 = (var8 != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54)));
/*     */           
/* 674 */           if (var9)
/*     */           {
/* 676 */             this.shiftClickedSlot = (var4 != null && var4.getHasStack()) ? var4.getStack() : null;
/*     */           }
/*     */           
/* 679 */           handleMouseClick(var4, var8, state, var9 ? 1 : 0);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 684 */     if (this.mc.thePlayer.inventory.getItemStack() == null)
/*     */     {
/* 686 */       this.lastClickTime = 0L;
/*     */     }
/*     */     
/* 689 */     this.dragSplitting = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY) {
/* 697 */     return isPointInRegion(slotIn.xDisplayPosition, slotIn.yDisplayPosition, 16, 16, mouseX, mouseY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isPointInRegion(int left, int top, int right, int bottom, int pointX, int pointY) {
/* 706 */     int var7 = guiLeft;
/* 707 */     int var8 = guiTop;
/* 708 */     pointX -= var7;
/* 709 */     pointY -= var8;
/* 710 */     return (pointX >= left - 1 && pointX < left + right + 1 && pointY >= top - 1 && pointY < top + bottom + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleMouseClick(Slot slotIn, int slotId, int clickedButton, int clickType) {
/* 718 */     if (slotIn != null)
/*     */     {
/* 720 */       slotId = slotIn.slotNumber;
/*     */     }
/*     */     
/* 723 */     this.mc.playerController.windowClick(this.inventorySlots.windowId, slotId, clickedButton, clickType, (EntityPlayer)this.mc.thePlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/* 732 */     if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode())
/*     */     {
/* 734 */       this.mc.thePlayer.closeScreen();
/*     */     }
/*     */     
/* 737 */     checkHotbarKeys(keyCode);
/*     */     
/* 739 */     if (this.theSlot != null && this.theSlot.getHasStack())
/*     */     {
/* 741 */       if (keyCode == this.mc.gameSettings.keyBindPickBlock.getKeyCode()) {
/*     */         
/* 743 */         handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, 3);
/*     */       }
/* 745 */       else if (keyCode == this.mc.gameSettings.keyBindDrop.getKeyCode()) {
/*     */         
/* 747 */         handleMouseClick(this.theSlot, this.theSlot.slotNumber, isCtrlKeyDown() ? 1 : 0, 4);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean checkHotbarKeys(int keyCode) {
/* 758 */     if (this.mc.thePlayer.inventory.getItemStack() == null && this.theSlot != null)
/*     */     {
/* 760 */       for (int var2 = 0; var2 < 9; var2++) {
/*     */         
/* 762 */         if (keyCode == this.mc.gameSettings.keyBindsHotbar[var2].getKeyCode()) {
/*     */           
/* 764 */           handleMouseClick(this.theSlot, this.theSlot.slotNumber, var2, 2);
/* 765 */           return true;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 770 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/* 778 */     if (this.mc.thePlayer != null)
/*     */     {
/* 780 */       this.inventorySlots.onContainerClosed((EntityPlayer)this.mc.thePlayer);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean doesGuiPauseGame() {
/* 789 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/* 797 */     super.updateScreen();
/*     */     
/* 799 */     if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
/*     */     {
/* 801 */       this.mc.thePlayer.closeScreen();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */