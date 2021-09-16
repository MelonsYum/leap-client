/*     */ package net.minecraft.client.gui;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.model.ModelBook;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ContainerEnchantment;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnchantmentNameParts;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.IWorldNameable;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.util.glu.Project;
/*     */ 
/*     */ public class GuiEnchantment extends GuiContainer {
/*  27 */   private static final ResourceLocation field_147078_C = new ResourceLocation("textures/gui/container/enchanting_table.png");
/*  28 */   private static final ResourceLocation field_147070_D = new ResourceLocation("textures/entity/enchanting_table_book.png");
/*  29 */   private static final ModelBook field_147072_E = new ModelBook();
/*     */   private final InventoryPlayer field_175379_F;
/*  31 */   private Random field_147074_F = new Random();
/*     */   
/*     */   private ContainerEnchantment field_147075_G;
/*     */   public int field_147073_u;
/*     */   public float field_147071_v;
/*     */   public float field_147069_w;
/*     */   public float field_147082_x;
/*     */   public float field_147081_y;
/*     */   public float field_147080_z;
/*     */   public float field_147076_A;
/*     */   ItemStack field_147077_B;
/*     */   private final IWorldNameable field_175380_I;
/*     */   private static final String __OBFID = "CL_00000757";
/*     */   
/*     */   public GuiEnchantment(InventoryPlayer p_i45502_1_, World worldIn, IWorldNameable p_i45502_3_) {
/*  46 */     super((Container)new ContainerEnchantment(p_i45502_1_, worldIn));
/*  47 */     this.field_175379_F = p_i45502_1_;
/*  48 */     this.field_147075_G = (ContainerEnchantment)this.inventorySlots;
/*  49 */     this.field_175380_I = p_i45502_3_;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
/*  57 */     fontRendererObj.drawString(this.field_175380_I.getDisplayName().getUnformattedText(), 12.0D, 5.0D, 4210752);
/*  58 */     fontRendererObj.drawString(this.field_175379_F.getDisplayName().getUnformattedText(), 8.0D, (this.ySize - 96 + 2), 4210752);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateScreen() {
/*  66 */     super.updateScreen();
/*  67 */     func_147068_g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/*  75 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*  76 */     int var4 = (width - this.xSize) / 2;
/*  77 */     int var5 = (height - this.ySize) / 2;
/*     */     
/*  79 */     for (int var6 = 0; var6 < 3; var6++) {
/*     */       
/*  81 */       int var7 = mouseX - var4 + 60;
/*  82 */       int var8 = mouseY - var5 + 14 + 19 * var6;
/*     */       
/*  84 */       if (var7 >= 0 && var8 >= 0 && var7 < 108 && var8 < 19 && this.field_147075_G.enchantItem((EntityPlayer)this.mc.thePlayer, var6))
/*     */       {
/*  86 */         this.mc.playerController.sendEnchantPacket(this.field_147075_G.windowId, var6);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
/*  96 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  97 */     this.mc.getTextureManager().bindTexture(field_147078_C);
/*  98 */     int var4 = (width - this.xSize) / 2;
/*  99 */     int var5 = (height - this.ySize) / 2;
/* 100 */     drawTexturedModalRect(var4, var5, 0, 0, this.xSize, this.ySize);
/* 101 */     GlStateManager.pushMatrix();
/* 102 */     GlStateManager.matrixMode(5889);
/* 103 */     GlStateManager.pushMatrix();
/* 104 */     GlStateManager.loadIdentity();
/* 105 */     ScaledResolution var6 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/* 106 */     GlStateManager.viewport((var6.getScaledWidth() - 320) / 2 * var6.getScaleFactor(), (var6.getScaledHeight() - 240) / 2 * var6.getScaleFactor(), 320 * var6.getScaleFactor(), 240 * var6.getScaleFactor());
/* 107 */     GlStateManager.translate(-0.34F, 0.23F, 0.0F);
/* 108 */     Project.gluPerspective(90.0F, 1.3333334F, 9.0F, 80.0F);
/* 109 */     float var7 = 1.0F;
/* 110 */     GlStateManager.matrixMode(5888);
/* 111 */     GlStateManager.loadIdentity();
/* 112 */     RenderHelper.enableStandardItemLighting();
/* 113 */     GlStateManager.translate(0.0F, 3.3F, -16.0F);
/* 114 */     GlStateManager.scale(var7, var7, var7);
/* 115 */     float var8 = 5.0F;
/* 116 */     GlStateManager.scale(var8, var8, var8);
/* 117 */     GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
/* 118 */     this.mc.getTextureManager().bindTexture(field_147070_D);
/* 119 */     GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
/* 120 */     float var9 = this.field_147076_A + (this.field_147080_z - this.field_147076_A) * partialTicks;
/* 121 */     GlStateManager.translate((1.0F - var9) * 0.2F, (1.0F - var9) * 0.1F, (1.0F - var9) * 0.25F);
/* 122 */     GlStateManager.rotate(-(1.0F - var9) * 90.0F - 90.0F, 0.0F, 1.0F, 0.0F);
/* 123 */     GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
/* 124 */     float var10 = this.field_147069_w + (this.field_147071_v - this.field_147069_w) * partialTicks + 0.25F;
/* 125 */     float var11 = this.field_147069_w + (this.field_147071_v - this.field_147069_w) * partialTicks + 0.75F;
/* 126 */     var10 = (var10 - MathHelper.truncateDoubleToInt(var10)) * 1.6F - 0.3F;
/* 127 */     var11 = (var11 - MathHelper.truncateDoubleToInt(var11)) * 1.6F - 0.3F;
/*     */     
/* 129 */     if (var10 < 0.0F)
/*     */     {
/* 131 */       var10 = 0.0F;
/*     */     }
/*     */     
/* 134 */     if (var11 < 0.0F)
/*     */     {
/* 136 */       var11 = 0.0F;
/*     */     }
/*     */     
/* 139 */     if (var10 > 1.0F)
/*     */     {
/* 141 */       var10 = 1.0F;
/*     */     }
/*     */     
/* 144 */     if (var11 > 1.0F)
/*     */     {
/* 146 */       var11 = 1.0F;
/*     */     }
/*     */     
/* 149 */     GlStateManager.enableRescaleNormal();
/* 150 */     field_147072_E.render(null, 0.0F, var10, var11, var9, 0.0F, 0.0625F);
/* 151 */     GlStateManager.disableRescaleNormal();
/* 152 */     RenderHelper.disableStandardItemLighting();
/* 153 */     GlStateManager.matrixMode(5889);
/* 154 */     GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
/* 155 */     GlStateManager.popMatrix();
/* 156 */     GlStateManager.matrixMode(5888);
/* 157 */     GlStateManager.popMatrix();
/* 158 */     RenderHelper.disableStandardItemLighting();
/* 159 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 160 */     EnchantmentNameParts.func_178176_a().reseedRandomGenerator(this.field_147075_G.field_178149_f);
/* 161 */     int var12 = this.field_147075_G.func_178147_e();
/*     */     
/* 163 */     for (int var13 = 0; var13 < 3; var13++) {
/*     */       
/* 165 */       int var14 = var4 + 60;
/* 166 */       int var15 = var14 + 20;
/* 167 */       byte var16 = 86;
/* 168 */       String var17 = EnchantmentNameParts.func_178176_a().generateNewRandomName();
/* 169 */       zLevel = 0.0F;
/* 170 */       this.mc.getTextureManager().bindTexture(field_147078_C);
/* 171 */       int var18 = this.field_147075_G.enchantLevels[var13];
/* 172 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 174 */       if (var18 == 0) {
/*     */         
/* 176 */         drawTexturedModalRect(var14, var5 + 14 + 19 * var13, 0, 185, 108, 19);
/*     */       }
/*     */       else {
/*     */         
/* 180 */         int i = var18;
/* 181 */         FontRenderer var20 = this.mc.standardGalacticFontRenderer;
/* 182 */         int var21 = 6839882;
/*     */         
/* 184 */         if ((var12 < var13 + 1 || this.mc.thePlayer.experienceLevel < var18) && !this.mc.thePlayer.capabilities.isCreativeMode) {
/*     */           
/* 186 */           drawTexturedModalRect(var14, var5 + 14 + 19 * var13, 0, 185, 108, 19);
/* 187 */           drawTexturedModalRect(var14 + 1, var5 + 15 + 19 * var13, 16 * var13, 239, 16, 16);
/* 188 */           var20.drawSplitString(var17, var15, var5 + 16 + 19 * var13, var16, (var21 & 0xFEFEFE) >> 1);
/* 189 */           var21 = 4226832;
/*     */         }
/*     */         else {
/*     */           
/* 193 */           int var22 = mouseX - var4 + 60;
/* 194 */           int var23 = mouseY - var5 + 14 + 19 * var13;
/*     */           
/* 196 */           if (var22 >= 0 && var23 >= 0 && var22 < 108 && var23 < 19) {
/*     */             
/* 198 */             drawTexturedModalRect(var14, var5 + 14 + 19 * var13, 0, 204, 108, 19);
/* 199 */             var21 = 16777088;
/*     */           }
/*     */           else {
/*     */             
/* 203 */             drawTexturedModalRect(var14, var5 + 14 + 19 * var13, 0, 166, 108, 19);
/*     */           } 
/*     */           
/* 206 */           drawTexturedModalRect(var14 + 1, var5 + 15 + 19 * var13, 16 * var13, 223, 16, 16);
/* 207 */           var20.drawSplitString(var17, var15, var5 + 16 + 19 * var13, var16, var21);
/* 208 */           var21 = 8453920;
/*     */         } 
/*     */         
/* 211 */         var20 = this.mc.fontRendererObj;
/* 212 */         var20.drawStringWithShadow(i, (var15 + 86 - var20.getStringWidth(i)), (var5 + 16 + 19 * var13 + 7), var21);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 222 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 223 */     boolean var4 = this.mc.thePlayer.capabilities.isCreativeMode;
/* 224 */     int var5 = this.field_147075_G.func_178147_e();
/*     */     
/* 226 */     for (int var6 = 0; var6 < 3; var6++) {
/*     */       
/* 228 */       int var7 = this.field_147075_G.enchantLevels[var6];
/* 229 */       int var8 = this.field_147075_G.field_178151_h[var6];
/* 230 */       int var9 = var6 + 1;
/*     */       
/* 232 */       if (isPointInRegion(60, 14 + 19 * var6, 108, 17, mouseX, mouseY) && var7 > 0 && var8 >= 0) {
/*     */         
/* 234 */         ArrayList<String> var10 = Lists.newArrayList();
/*     */ 
/*     */         
/* 237 */         if (var8 >= 0 && Enchantment.func_180306_c(var8 & 0xFF) != null) {
/*     */           
/* 239 */           String var11 = Enchantment.func_180306_c(var8 & 0xFF).getTranslatedName((var8 & 0xFF00) >> 8);
/* 240 */           var10.add(String.valueOf(EnumChatFormatting.WHITE.toString()) + EnumChatFormatting.ITALIC.toString() + I18n.format("container.enchant.clue", new Object[] { var11 }));
/*     */         } 
/*     */         
/* 243 */         if (!var4) {
/*     */           
/* 245 */           if (var8 >= 0)
/*     */           {
/* 247 */             var10.add("");
/*     */           }
/*     */           
/* 250 */           if (this.mc.thePlayer.experienceLevel < var7) {
/*     */             
/* 252 */             var10.add(String.valueOf(EnumChatFormatting.RED.toString()) + "Level Requirement: " + this.field_147075_G.enchantLevels[var6]);
/*     */           }
/*     */           else {
/*     */             
/* 256 */             String var11 = "";
/*     */             
/* 258 */             if (var9 == 1) {
/*     */               
/* 260 */               var11 = I18n.format("container.enchant.lapis.one", new Object[0]);
/*     */             }
/*     */             else {
/*     */               
/* 264 */               var11 = I18n.format("container.enchant.lapis.many", new Object[] { Integer.valueOf(var9) });
/*     */             } 
/*     */             
/* 267 */             if (var5 >= var9) {
/*     */               
/* 269 */               var10.add(String.valueOf(EnumChatFormatting.GRAY.toString()) + var11);
/*     */             }
/*     */             else {
/*     */               
/* 273 */               var10.add(String.valueOf(EnumChatFormatting.RED.toString()) + var11);
/*     */             } 
/*     */             
/* 276 */             if (var9 == 1) {
/*     */               
/* 278 */               var11 = I18n.format("container.enchant.level.one", new Object[0]);
/*     */             }
/*     */             else {
/*     */               
/* 282 */               var11 = I18n.format("container.enchant.level.many", new Object[] { Integer.valueOf(var9) });
/*     */             } 
/*     */             
/* 285 */             var10.add(String.valueOf(EnumChatFormatting.GRAY.toString()) + var11);
/*     */           } 
/*     */         } 
/*     */         
/* 289 */         drawHoveringText(var10, mouseX, mouseY);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_147068_g() {
/* 297 */     ItemStack var1 = this.inventorySlots.getSlot(0).getStack();
/*     */     
/* 299 */     if (!ItemStack.areItemStacksEqual(var1, this.field_147077_B)) {
/*     */       
/* 301 */       this.field_147077_B = var1;
/*     */ 
/*     */       
/*     */       do {
/* 305 */         this.field_147082_x += (this.field_147074_F.nextInt(4) - this.field_147074_F.nextInt(4));
/*     */       }
/* 307 */       while (this.field_147071_v <= this.field_147082_x + 1.0F && this.field_147071_v >= this.field_147082_x - 1.0F);
/*     */     } 
/*     */     
/* 310 */     this.field_147073_u++;
/* 311 */     this.field_147069_w = this.field_147071_v;
/* 312 */     this.field_147076_A = this.field_147080_z;
/* 313 */     boolean var2 = false;
/*     */     
/* 315 */     for (int var3 = 0; var3 < 3; var3++) {
/*     */       
/* 317 */       if (this.field_147075_G.enchantLevels[var3] != 0)
/*     */       {
/* 319 */         var2 = true;
/*     */       }
/*     */     } 
/*     */     
/* 323 */     if (var2) {
/*     */       
/* 325 */       this.field_147080_z += 0.2F;
/*     */     }
/*     */     else {
/*     */       
/* 329 */       this.field_147080_z -= 0.2F;
/*     */     } 
/*     */     
/* 332 */     this.field_147080_z = MathHelper.clamp_float(this.field_147080_z, 0.0F, 1.0F);
/* 333 */     float var5 = (this.field_147082_x - this.field_147071_v) * 0.4F;
/* 334 */     float var4 = 0.2F;
/* 335 */     var5 = MathHelper.clamp_float(var5, -var4, var4);
/* 336 */     this.field_147081_y += (var5 - this.field_147081_y) * 0.9F;
/* 337 */     this.field_147071_v += this.field_147081_y;
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */