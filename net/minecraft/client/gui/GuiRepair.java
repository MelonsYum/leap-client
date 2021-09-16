/*     */ package net.minecraft.client.gui;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerRepair;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.client.C17PacketCustomPayload;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ 
/*     */ public class GuiRepair extends GuiContainer implements ICrafting {
/*  25 */   private static final ResourceLocation anvilResource = new ResourceLocation("textures/gui/container/anvil.png");
/*     */   
/*     */   private ContainerRepair anvil;
/*     */   private GuiTextField nameField;
/*     */   private InventoryPlayer playerInventory;
/*     */   private static final String __OBFID = "CL_00000738";
/*     */   
/*     */   public GuiRepair(InventoryPlayer p_i45508_1_, World worldIn) {
/*  33 */     super((Container)new ContainerRepair(p_i45508_1_, worldIn, (EntityPlayer)(Minecraft.getMinecraft()).thePlayer));
/*  34 */     this.playerInventory = p_i45508_1_;
/*  35 */     this.anvil = (ContainerRepair)this.inventorySlots;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  43 */     super.initGui();
/*  44 */     Keyboard.enableRepeatEvents(true);
/*  45 */     int var1 = (width - this.xSize) / 2;
/*  46 */     int var2 = (height - this.ySize) / 2;
/*  47 */     this.nameField = new GuiTextField(0, fontRendererObj, var1 + 62, var2 + 24, 103, 12);
/*  48 */     this.nameField.setTextColor(-1);
/*  49 */     this.nameField.setDisabledTextColour(-1);
/*  50 */     this.nameField.setEnableBackgroundDrawing(false);
/*  51 */     this.nameField.setMaxStringLength(40);
/*  52 */     this.inventorySlots.removeCraftingFromCrafters(this);
/*  53 */     this.inventorySlots.onCraftGuiOpened(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onGuiClosed() {
/*  61 */     super.onGuiClosed();
/*  62 */     Keyboard.enableRepeatEvents(false);
/*  63 */     this.inventorySlots.removeCraftingFromCrafters(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/*  71 */     GlStateManager.disableLighting();
/*  72 */     GlStateManager.disableBlend();
/*  73 */     fontRendererObj.drawString(I18n.format("container.repair", new Object[0]), 60.0D, 6.0D, 4210752);
/*     */     
/*  75 */     if (this.anvil.maximumCost > 0) {
/*     */       
/*  77 */       int var3 = 8453920;
/*  78 */       boolean var4 = true;
/*  79 */       String var5 = I18n.format("container.repair.cost", new Object[] { Integer.valueOf(this.anvil.maximumCost) });
/*     */       
/*  81 */       if (this.anvil.maximumCost >= 40 && !this.mc.thePlayer.capabilities.isCreativeMode) {
/*     */         
/*  83 */         var5 = I18n.format("container.repair.expensive", new Object[0]);
/*  84 */         var3 = 16736352;
/*     */       }
/*  86 */       else if (!this.anvil.getSlot(2).getHasStack()) {
/*     */         
/*  88 */         var4 = false;
/*     */       }
/*  90 */       else if (!this.anvil.getSlot(2).canTakeStack(this.playerInventory.player)) {
/*     */         
/*  92 */         var3 = 16736352;
/*     */       } 
/*     */       
/*  95 */       if (var4) {
/*     */         
/*  97 */         int var6 = 0xFF000000 | (var3 & 0xFCFCFC) >> 2 | var3 & 0xFF000000;
/*  98 */         int var7 = this.xSize - 8 - fontRendererObj.getStringWidth(var5);
/*  99 */         byte var8 = 67;
/*     */         
/* 101 */         if (fontRendererObj.getUnicodeFlag()) {
/*     */           
/* 103 */           drawRect((var7 - 3), (var8 - 2), (this.xSize - 7), (var8 + 10), -16777216);
/* 104 */           drawRect((var7 - 2), (var8 - 1), (this.xSize - 8), (var8 + 9), -12895429);
/*     */         }
/*     */         else {
/*     */           
/* 108 */           fontRendererObj.drawString(var5, var7, (var8 + 1), var6);
/* 109 */           fontRendererObj.drawString(var5, (var7 + 1), var8, var6);
/* 110 */           fontRendererObj.drawString(var5, (var7 + 1), (var8 + 1), var6);
/*     */         } 
/*     */         
/* 113 */         fontRendererObj.drawString(var5, var7, var8, var3);
/*     */       } 
/*     */     } 
/*     */     
/* 117 */     GlStateManager.enableLighting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void keyTyped(char typedChar, int keyCode) throws IOException {
/* 126 */     if (this.nameField.textboxKeyTyped(typedChar, keyCode)) {
/*     */       
/* 128 */       renameItem();
/*     */     }
/*     */     else {
/*     */       
/* 132 */       super.keyTyped(typedChar, keyCode);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void renameItem() {
/* 138 */     String var1 = this.nameField.getText();
/* 139 */     Slot var2 = this.anvil.getSlot(0);
/*     */     
/* 141 */     if (var2 != null && var2.getHasStack() && !var2.getStack().hasDisplayName() && var1.equals(var2.getStack().getDisplayName()))
/*     */     {
/* 143 */       var1 = "";
/*     */     }
/*     */     
/* 146 */     this.anvil.updateItemName(var1);
/* 147 */     this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C17PacketCustomPayload("MC|ItemName", (new PacketBuffer(Unpooled.buffer())).writeString(var1)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 155 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 156 */     this.nameField.mouseClicked(mouseX, mouseY, mouseButton);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 164 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 165 */     GlStateManager.disableLighting();
/* 166 */     GlStateManager.disableBlend();
/* 167 */     this.nameField.drawTextBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 175 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 176 */     this.mc.getTextureManager().bindTexture(anvilResource);
/* 177 */     int var4 = (width - this.xSize) / 2;
/* 178 */     int var5 = (height - this.ySize) / 2;
/* 179 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/* 180 */     drawTexturedModalRect(var4 + 59, var5 + 20, 0, this.ySize + (this.anvil.getSlot(0).getHasStack() ? 0 : 16), 110, 16);
/*     */     
/* 182 */     if ((this.anvil.getSlot(0).getHasStack() || this.anvil.getSlot(1).getHasStack()) && !this.anvil.getSlot(2).getHasStack())
/*     */     {
/* 184 */       drawTexturedModalRect(var4 + 99, var5 + 45, this.xSize, 0, 28, 21);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateCraftingInventory(Container p_71110_1_, List p_71110_2_) {
/* 193 */     sendSlotContents(p_71110_1_, 0, p_71110_1_.getSlot(0).getStack());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendSlotContents(Container p_71111_1_, int p_71111_2_, ItemStack p_71111_3_) {
/* 202 */     if (p_71111_2_ == 0) {
/*     */       
/* 204 */       this.nameField.setText((p_71111_3_ == null) ? "" : p_71111_3_.getDisplayName());
/* 205 */       this.nameField.setEnabled((p_71111_3_ != null));
/*     */       
/* 207 */       if (p_71111_3_ != null)
/*     */       {
/* 209 */         renameItem();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendProgressBarUpdate(Container p_71112_1_, int p_71112_2_, int p_71112_3_) {}
/*     */   
/*     */   public void func_175173_a(Container p_175173_1_, IInventory p_175173_2_) {}
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiRepair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */