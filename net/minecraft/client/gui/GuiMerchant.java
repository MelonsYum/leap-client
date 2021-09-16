/*     */ package net.minecraft.client.gui;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.entity.IMerchant;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerMerchant;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.play.client.C17PacketCustomPayload;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.village.MerchantRecipe;
/*     */ import net.minecraft.village.MerchantRecipeList;
/*     */ import net.minecraft.world.World;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class GuiMerchant extends GuiContainer {
/*  26 */   private static final Logger logger = LogManager.getLogger();
/*  27 */   private static final ResourceLocation field_147038_v = new ResourceLocation("textures/gui/container/villager.png");
/*     */   
/*     */   private IMerchant field_147037_w;
/*     */   private MerchantButton field_147043_x;
/*     */   private MerchantButton field_147042_y;
/*     */   private int field_147041_z;
/*     */   private IChatComponent field_147040_A;
/*     */   private static final String __OBFID = "CL_00000762";
/*     */   
/*     */   public GuiMerchant(InventoryPlayer p_i45500_1_, IMerchant p_i45500_2_, World worldIn) {
/*  37 */     super((Container)new ContainerMerchant(p_i45500_1_, p_i45500_2_, worldIn));
/*  38 */     this.field_147037_w = p_i45500_2_;
/*  39 */     this.field_147040_A = p_i45500_2_.getDisplayName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  47 */     super.initGui();
/*  48 */     int var1 = (width - this.xSize) / 2;
/*  49 */     int var2 = (height - this.ySize) / 2;
/*  50 */     this.buttonList.add(this.field_147043_x = new MerchantButton(1, var1 + 120 + 27, var2 + 24 - 1, true));
/*  51 */     this.buttonList.add(this.field_147042_y = new MerchantButton(2, var1 + 36 - 19, var2 + 24 - 1, false));
/*  52 */     this.field_147043_x.enabled = false;
/*  53 */     this.field_147042_y.enabled = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/*  61 */     String var3 = this.field_147040_A.getUnformattedText();
/*  62 */     fontRendererObj.drawString(var3, (this.xSize / 2 - fontRendererObj.getStringWidth(var3) / 2), 6.0D, 4210752);
/*  63 */     fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8.0D, (this.ySize - 96 + 2), 4210752);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  71 */     super.updateScreen();
/*  72 */     MerchantRecipeList var1 = this.field_147037_w.getRecipes((EntityPlayer)this.mc.thePlayer);
/*     */     
/*  74 */     if (var1 != null) {
/*     */       
/*  76 */       this.field_147043_x.enabled = (this.field_147041_z < var1.size() - 1);
/*  77 */       this.field_147042_y.enabled = (this.field_147041_z > 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void actionPerformed(GuiButton button) throws IOException {
/*  83 */     boolean var2 = false;
/*     */     
/*  85 */     if (button == this.field_147043_x) {
/*     */       
/*  87 */       this.field_147041_z++;
/*  88 */       MerchantRecipeList var3 = this.field_147037_w.getRecipes((EntityPlayer)this.mc.thePlayer);
/*     */       
/*  90 */       if (var3 != null && this.field_147041_z >= var3.size())
/*     */       {
/*  92 */         this.field_147041_z = var3.size() - 1;
/*     */       }
/*     */       
/*  95 */       var2 = true;
/*     */     }
/*  97 */     else if (button == this.field_147042_y) {
/*     */       
/*  99 */       this.field_147041_z--;
/*     */       
/* 101 */       if (this.field_147041_z < 0)
/*     */       {
/* 103 */         this.field_147041_z = 0;
/*     */       }
/*     */       
/* 106 */       var2 = true;
/*     */     } 
/*     */     
/* 109 */     if (var2) {
/*     */       
/* 111 */       ((ContainerMerchant)this.inventorySlots).setCurrentRecipeIndex(this.field_147041_z);
/* 112 */       PacketBuffer var4 = new PacketBuffer(Unpooled.buffer());
/* 113 */       var4.writeInt(this.field_147041_z);
/* 114 */       this.mc.getNetHandler().addToSendQueue((Packet)new C17PacketCustomPayload("MC|TrSel", var4));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/* 123 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 124 */     this.mc.getTextureManager().bindTexture(field_147038_v);
/* 125 */     int var4 = (width - this.xSize) / 2;
/* 126 */     int var5 = (height - this.ySize) / 2;
/* 127 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/* 128 */     MerchantRecipeList var6 = this.field_147037_w.getRecipes((EntityPlayer)this.mc.thePlayer);
/*     */     
/* 130 */     if (var6 != null && !var6.isEmpty()) {
/*     */       
/* 132 */       int var7 = this.field_147041_z;
/*     */       
/* 134 */       if (var7 < 0 || var7 >= var6.size()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 139 */       MerchantRecipe var8 = (MerchantRecipe)var6.get(var7);
/*     */       
/* 141 */       if (var8.isRecipeDisabled()) {
/*     */         
/* 143 */         this.mc.getTextureManager().bindTexture(field_147038_v);
/* 144 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 145 */         GlStateManager.disableLighting();
/* 146 */         drawTexturedModalRect(guiLeft + 83, guiTop + 21, 212, 0, 28, 21);
/* 147 */         drawTexturedModalRect(guiLeft + 83, guiTop + 51, 212, 0, 28, 21);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 157 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 158 */     MerchantRecipeList var4 = this.field_147037_w.getRecipes((EntityPlayer)this.mc.thePlayer);
/*     */     
/* 160 */     if (var4 != null && !var4.isEmpty()) {
/*     */       
/* 162 */       int var5 = (width - this.xSize) / 2;
/* 163 */       int var6 = (height - this.ySize) / 2;
/* 164 */       int var7 = this.field_147041_z;
/* 165 */       MerchantRecipe var8 = (MerchantRecipe)var4.get(var7);
/* 166 */       ItemStack var9 = var8.getItemToBuy();
/* 167 */       ItemStack var10 = var8.getSecondItemToBuy();
/* 168 */       ItemStack var11 = var8.getItemToSell();
/* 169 */       GlStateManager.pushMatrix();
/* 170 */       RenderHelper.enableGUIStandardItemLighting();
/* 171 */       GlStateManager.disableLighting();
/* 172 */       GlStateManager.enableRescaleNormal();
/* 173 */       GlStateManager.enableColorMaterial();
/* 174 */       GlStateManager.enableLighting();
/* 175 */       this.itemRender.zLevel = 100.0F;
/* 176 */       this.itemRender.func_180450_b(var9, var5 + 36, var6 + 24);
/* 177 */       this.itemRender.func_175030_a(fontRendererObj, var9, var5 + 36, var6 + 24);
/*     */       
/* 179 */       if (var10 != null) {
/*     */         
/* 181 */         this.itemRender.func_180450_b(var10, var5 + 62, var6 + 24);
/* 182 */         this.itemRender.func_175030_a(fontRendererObj, var10, var5 + 62, var6 + 24);
/*     */       } 
/*     */       
/* 185 */       this.itemRender.func_180450_b(var11, var5 + 120, var6 + 24);
/* 186 */       this.itemRender.func_175030_a(fontRendererObj, var11, var5 + 120, var6 + 24);
/* 187 */       this.itemRender.zLevel = 0.0F;
/* 188 */       GlStateManager.disableLighting();
/*     */       
/* 190 */       if (isPointInRegion(36, 24, 16, 16, mouseX, mouseY) && var9 != null) {
/*     */         
/* 192 */         renderToolTip(var9, mouseX, mouseY);
/*     */       }
/* 194 */       else if (var10 != null && isPointInRegion(62, 24, 16, 16, mouseX, mouseY) && var10 != null) {
/*     */         
/* 196 */         renderToolTip(var10, mouseX, mouseY);
/*     */       }
/* 198 */       else if (var11 != null && isPointInRegion(120, 24, 16, 16, mouseX, mouseY) && var11 != null) {
/*     */         
/* 200 */         renderToolTip(var11, mouseX, mouseY);
/*     */       }
/* 202 */       else if (var8.isRecipeDisabled() && (isPointInRegion(83, 21, 28, 21, mouseX, mouseY) || isPointInRegion(83, 51, 28, 21, mouseX, mouseY))) {
/*     */         
/* 204 */         drawCreativeTabHoveringText(I18n.format("merchant.deprecated", new Object[0]), mouseX, mouseY);
/*     */       } 
/*     */       
/* 207 */       GlStateManager.popMatrix();
/* 208 */       GlStateManager.enableLighting();
/* 209 */       GlStateManager.enableDepth();
/* 210 */       RenderHelper.enableStandardItemLighting();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IMerchant getMerchant() {
/* 216 */     return this.field_147037_w;
/*     */   }
/*     */   
/*     */   static class MerchantButton
/*     */     extends GuiButton
/*     */   {
/*     */     private final boolean field_146157_o;
/*     */     private static final String __OBFID = "CL_00000763";
/*     */     
/*     */     public MerchantButton(int p_i1095_1_, int p_i1095_2_, int p_i1095_3_, boolean p_i1095_4_) {
/* 226 */       super(p_i1095_1_, p_i1095_2_, p_i1095_3_, 12, 19, "");
/* 227 */       this.field_146157_o = p_i1095_4_;
/*     */     }
/*     */ 
/*     */     
/*     */     public void drawButton(Minecraft mc, int mouseX, int mouseY) {
/* 232 */       if (this.visible) {
/*     */         
/* 234 */         mc.getTextureManager().bindTexture(GuiMerchant.field_147038_v);
/* 235 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 236 */         boolean var4 = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
/* 237 */         int var5 = 0;
/* 238 */         int var6 = 176;
/*     */         
/* 240 */         if (!this.enabled) {
/*     */           
/* 242 */           var6 += this.width * 2;
/*     */         }
/* 244 */         else if (var4) {
/*     */           
/* 246 */           var6 += this.width;
/*     */         } 
/*     */         
/* 249 */         if (!this.field_146157_o)
/*     */         {
/* 251 */           var5 += this.height;
/*     */         }
/*     */         
/* 254 */         drawTexturedModalRect(this.xPosition, this.yPosition, var6, var5, this.width, this.height);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */