/*     */ package net.minecraft.client.gui.inventory;
/*     */ 
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerBeacon;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.client.C17PacketCustomPayload;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.tileentity.TileEntityBeacon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class GuiBeacon extends GuiContainer {
/*  27 */   private static final Logger logger = LogManager.getLogger();
/*  28 */   private static final ResourceLocation beaconGuiTextures = new ResourceLocation("textures/gui/container/beacon.png");
/*     */   
/*     */   private IInventory tileBeacon;
/*     */   private ConfirmButton beaconConfirmButton;
/*     */   private boolean buttonsNotDrawn;
/*     */   private static final String __OBFID = "CL_00000739";
/*     */   
/*     */   public GuiBeacon(InventoryPlayer p_i45507_1_, IInventory p_i45507_2_) {
/*  36 */     super((Container)new ContainerBeacon((IInventory)p_i45507_1_, p_i45507_2_));
/*  37 */     this.tileBeacon = p_i45507_2_;
/*  38 */     this.xSize = 230;
/*  39 */     this.ySize = 219;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  47 */     super.initGui();
/*  48 */     this.buttonList.add(this.beaconConfirmButton = new ConfirmButton(-1, guiLeft + 164, guiTop + 107));
/*  49 */     this.buttonList.add(new CancelButton(-2, guiLeft + 190, guiTop + 107));
/*  50 */     this.buttonsNotDrawn = true;
/*  51 */     this.beaconConfirmButton.enabled = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  59 */     super.updateScreen();
/*  60 */     int var1 = this.tileBeacon.getField(0);
/*  61 */     int var2 = this.tileBeacon.getField(1);
/*  62 */     int var3 = this.tileBeacon.getField(2);
/*     */     
/*  64 */     if (this.buttonsNotDrawn && var1 >= 0) {
/*     */       
/*  66 */       this.buttonsNotDrawn = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  73 */       for (int var4 = 0; var4 <= 2; var4++) {
/*     */         
/*  75 */         int i = (TileEntityBeacon.effectsList[var4]).length;
/*  76 */         int j = i * 22 + (i - 1) * 2;
/*     */         
/*  78 */         for (int k = 0; k < i; k++) {
/*     */           
/*  80 */           int var8 = (TileEntityBeacon.effectsList[var4][k]).id;
/*  81 */           PowerButton var9 = new PowerButton(var4 << 8 | var8, guiLeft + 76 + k * 24 - j / 2, guiTop + 22 + var4 * 25, var8, var4);
/*  82 */           this.buttonList.add(var9);
/*     */           
/*  84 */           if (var4 >= var1) {
/*     */             
/*  86 */             var9.enabled = false;
/*     */           }
/*  88 */           else if (var8 == var2) {
/*     */             
/*  90 */             var9.func_146140_b(true);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  95 */       byte var10 = 3;
/*  96 */       int var5 = (TileEntityBeacon.effectsList[var10]).length + 1;
/*  97 */       int var6 = var5 * 22 + (var5 - 1) * 2;
/*     */       
/*  99 */       for (int var7 = 0; var7 < var5 - 1; var7++) {
/*     */         
/* 101 */         int var8 = (TileEntityBeacon.effectsList[var10][var7]).id;
/* 102 */         PowerButton var9 = new PowerButton(var10 << 8 | var8, guiLeft + 167 + var7 * 24 - var6 / 2, guiTop + 47, var8, var10);
/* 103 */         this.buttonList.add(var9);
/*     */         
/* 105 */         if (var10 >= var1) {
/*     */           
/* 107 */           var9.enabled = false;
/*     */         }
/* 109 */         else if (var8 == var3) {
/*     */           
/* 111 */           var9.func_146140_b(true);
/*     */         } 
/*     */       } 
/*     */       
/* 115 */       if (var2 > 0) {
/*     */         
/* 117 */         PowerButton var11 = new PowerButton(var10 << 8 | var2, guiLeft + 167 + (var5 - 1) * 24 - var6 / 2, guiTop + 47, var2, var10);
/* 118 */         this.buttonList.add(var11);
/*     */         
/* 120 */         if (var10 >= var1) {
/*     */           
/* 122 */           var11.enabled = false;
/*     */         }
/* 124 */         else if (var2 == var3) {
/*     */           
/* 126 */           var11.func_146140_b(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     this.beaconConfirmButton.enabled = (this.tileBeacon.getStackInSlot(0) != null && var2 > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/* 136 */     if (button.id == -2) {
/*     */       
/* 138 */       this.mc.displayGuiScreen(null);
/*     */     }
/* 140 */     else if (button.id == -1) {
/*     */       
/* 142 */       String var2 = "MC|Beacon";
/* 143 */       PacketBuffer var3 = new PacketBuffer(Unpooled.buffer());
/* 144 */       var3.writeInt(this.tileBeacon.getField(1));
/* 145 */       var3.writeInt(this.tileBeacon.getField(2));
/* 146 */       this.mc.getNetHandler().addToSendQueue((Packet)new C17PacketCustomPayload(var2, var3));
/* 147 */       this.mc.displayGuiScreen(null);
/*     */     }
/* 149 */     else if (button instanceof PowerButton) {
/*     */       
/* 151 */       if (((PowerButton)button).func_146141_c()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 156 */       int var5 = button.id;
/* 157 */       int var6 = var5 & 0xFF;
/* 158 */       int var4 = var5 >> 8;
/*     */       
/* 160 */       if (var4 < 3) {
/*     */         
/* 162 */         this.tileBeacon.setField(1, var6);
/*     */       }
/*     */       else {
/*     */         
/* 166 */         this.tileBeacon.setField(2, var6);
/*     */       } 
/*     */       
/* 169 */       this.buttonList.clear();
/* 170 */       initGui();
/* 171 */       updateScreen();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/* 180 */     RenderHelper.disableStandardItemLighting();
/* 181 */     drawCenteredString(fontRendererObj, I18n.format("tile.beacon.primary", new Object[0]), 62.0F, 10.0F, 14737632);
/* 182 */     drawCenteredString(fontRendererObj, I18n.format("tile.beacon.secondary", new Object[0]), 169.0F, 10.0F, 14737632);
/* 183 */     Iterator<GuiButton> var3 = this.buttonList.iterator();
/*     */     
/* 185 */     while (var3.hasNext()) {
/*     */       
/* 187 */       GuiButton var4 = var3.next();
/*     */       
/* 189 */       if (var4.isMouseOver()) {
/*     */         
/* 191 */         var4.drawButtonForegroundLayer(mouseX - guiLeft, mouseY - guiTop);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 196 */     RenderHelper.enableGUIStandardItemLighting();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 204 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 205 */     this.mc.getTextureManager().bindTexture(beaconGuiTextures);
/* 206 */     int var4 = (width - this.xSize) / 2;
/* 207 */     int var5 = (height - this.ySize) / 2;
/* 208 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/* 209 */     this.itemRender.zLevel = 100.0F;
/* 210 */     this.itemRender.func_180450_b(new ItemStack(Items.emerald), var4 + 42, var5 + 109);
/* 211 */     this.itemRender.func_180450_b(new ItemStack(Items.diamond), var4 + 42 + 22, var5 + 109);
/* 212 */     this.itemRender.func_180450_b(new ItemStack(Items.gold_ingot), var4 + 42 + 44, var5 + 109);
/* 213 */     this.itemRender.func_180450_b(new ItemStack(Items.iron_ingot), var4 + 42 + 66, var5 + 109);
/* 214 */     this.itemRender.zLevel = 0.0F;
/*     */   }
/*     */   
/*     */   static class Button
/*     */     extends GuiButton
/*     */   {
/*     */     private final ResourceLocation field_146145_o;
/*     */     private final int field_146144_p;
/*     */     private final int field_146143_q;
/*     */     private boolean field_146142_r;
/*     */     private static final String __OBFID = "CL_00000743";
/*     */     
/*     */     protected Button(int p_i1077_1_, int p_i1077_2_, int p_i1077_3_, ResourceLocation p_i1077_4_, int p_i1077_5_, int p_i1077_6_) {
/* 227 */       super(p_i1077_1_, p_i1077_2_, p_i1077_3_, 22, 22, "");
/* 228 */       this.field_146145_o = p_i1077_4_;
/* 229 */       this.field_146144_p = p_i1077_5_;
/* 230 */       this.field_146143_q = p_i1077_6_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawButton(Minecraft mc, int mouseX, int mouseY) {
/* 235 */       if (this.visible) {
/*     */         
/* 237 */         mc.getTextureManager().bindTexture(GuiBeacon.beaconGuiTextures);
/* 238 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 239 */         this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
/* 240 */         short var4 = 219;
/* 241 */         int var5 = 0;
/*     */         
/* 243 */         if (!this.enabled) {
/*     */           
/* 245 */           var5 += this.width * 2;
/*     */         }
/* 247 */         else if (this.field_146142_r) {
/*     */           
/* 249 */           var5 += this.width * 1;
/*     */         }
/* 251 */         else if (this.hovered) {
/*     */           
/* 253 */           var5 += this.width * 3;
/*     */         } 
/*     */         
/* 256 */         drawTexturedModalRect(this.xPosition, this.yPosition, var5, var4, this.width, this.height);
/*     */         
/* 258 */         if (!GuiBeacon.beaconGuiTextures.equals(this.field_146145_o))
/*     */         {
/* 260 */           mc.getTextureManager().bindTexture(this.field_146145_o);
/*     */         }
/*     */         
/* 263 */         drawTexturedModalRect(this.xPosition + 2, this.yPosition + 2, this.field_146144_p, this.field_146143_q, 18, 18);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean func_146141_c() {
/* 269 */       return this.field_146142_r;
/*     */     }
/*     */ 
/*     */     
/*     */     public void func_146140_b(boolean p_146140_1_) {
/* 274 */       this.field_146142_r = p_146140_1_;
/*     */     }
/*     */   }
/*     */   
/*     */   class CancelButton
/*     */     extends Button
/*     */   {
/*     */     private static final String __OBFID = "CL_00000740";
/*     */     
/*     */     public CancelButton(int p_i1074_2_, int p_i1074_3_, int p_i1074_4_) {
/* 284 */       super(p_i1074_2_, p_i1074_3_, p_i1074_4_, GuiBeacon.beaconGuiTextures, 112, 220);
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawButtonForegroundLayer(int mouseX, int mouseY) {
/* 289 */       GuiBeacon.this.drawCreativeTabHoveringText(I18n.format("gui.cancel", new Object[0]), mouseX, mouseY);
/*     */     }
/*     */   }
/*     */   
/*     */   class ConfirmButton
/*     */     extends Button
/*     */   {
/*     */     private static final String __OBFID = "CL_00000741";
/*     */     
/*     */     public ConfirmButton(int p_i1075_2_, int p_i1075_3_, int p_i1075_4_) {
/* 299 */       super(p_i1075_2_, p_i1075_3_, p_i1075_4_, GuiBeacon.beaconGuiTextures, 90, 220);
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawButtonForegroundLayer(int mouseX, int mouseY) {
/* 304 */       GuiBeacon.this.drawCreativeTabHoveringText(I18n.format("gui.done", new Object[0]), mouseX, mouseY);
/*     */     }
/*     */   }
/*     */   
/*     */   class PowerButton
/*     */     extends Button
/*     */   {
/*     */     private final int field_146149_p;
/*     */     private final int field_146148_q;
/*     */     private static final String __OBFID = "CL_00000742";
/*     */     
/*     */     public PowerButton(int p_i1076_2_, int p_i1076_3_, int p_i1076_4_, int p_i1076_5_, int p_i1076_6_) {
/* 316 */       super(p_i1076_2_, p_i1076_3_, p_i1076_4_, GuiContainer.inventoryBackground, 0 + Potion.potionTypes[p_i1076_5_].getStatusIconIndex() % 8 * 18, 198 + Potion.potionTypes[p_i1076_5_].getStatusIconIndex() / 8 * 18);
/* 317 */       this.field_146149_p = p_i1076_5_;
/* 318 */       this.field_146148_q = p_i1076_6_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawButtonForegroundLayer(int mouseX, int mouseY) {
/* 323 */       String var3 = I18n.format(Potion.potionTypes[this.field_146149_p].getName(), new Object[0]);
/*     */       
/* 325 */       if (this.field_146148_q >= 3 && this.field_146149_p != Potion.regeneration.id)
/*     */       {
/* 327 */         var3 = String.valueOf(var3) + " II";
/*     */       }
/*     */       
/* 330 */       GuiBeacon.this.drawCreativeTabHoveringText(var3, mouseX, mouseY);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\inventory\GuiBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */