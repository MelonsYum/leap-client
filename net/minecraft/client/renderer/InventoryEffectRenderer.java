/*     */ package net.minecraft.client.renderer;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class InventoryEffectRenderer
/*     */   extends GuiContainer
/*     */ {
/*     */   private boolean hasActivePotionEffects;
/*     */   private static final String __OBFID = "CL_00000755";
/*     */   
/*     */   public InventoryEffectRenderer(Container p_i1089_1_) {
/*  22 */     super(p_i1089_1_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGui() {
/*  30 */     super.initGui();
/*  31 */     func_175378_g();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_175378_g() {
/*  36 */     if (!this.mc.thePlayer.getActivePotionEffects().isEmpty()) {
/*     */       
/*  38 */       guiLeft = 160 + (width - this.xSize - 200) / 2;
/*  39 */       this.hasActivePotionEffects = true;
/*     */     }
/*     */     else {
/*     */       
/*  43 */       guiLeft = (width - this.xSize) / 2;
/*  44 */       this.hasActivePotionEffects = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  53 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*     */     
/*  55 */     if (this.hasActivePotionEffects)
/*     */     {
/*  57 */       drawActivePotionEffects();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawActivePotionEffects() {
/*  66 */     Minecraft mc = Minecraft.getMinecraft();
/*     */     
/*  68 */     int var1 = guiLeft - 124;
/*  69 */     int var2 = guiTop;
/*  70 */     boolean var3 = true;
/*  71 */     Collection var4 = mc.thePlayer.getActivePotionEffects();
/*     */     
/*  73 */     if (!var4.isEmpty()) {
/*     */       
/*  75 */       GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  76 */       GlStateManager.disableLighting();
/*  77 */       int var5 = 33;
/*     */       
/*  79 */       if (var4.size() > 5)
/*     */       {
/*  81 */         var5 = 132 / (var4.size() - 1);
/*     */       }
/*     */       
/*  84 */       for (Iterator<PotionEffect> var6 = mc.thePlayer.getActivePotionEffects().iterator(); var6.hasNext(); var2 += var5) {
/*     */         
/*  86 */         PotionEffect var7 = var6.next();
/*  87 */         Potion var8 = Potion.potionTypes[var7.getPotionID()];
/*  88 */         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/*  89 */         mc.getTextureManager().bindTexture(inventoryBackground);
/*  90 */         drawTexturedModalRectd(var1, var2, 0, 166, 140, 32);
/*     */         
/*  92 */         if (var8.hasStatusIcon()) {
/*     */           
/*  94 */           int var9 = var8.getStatusIconIndex();
/*  95 */           drawTexturedModalRectd(var1 + 6, var2 + 7, 0 + var9 % 8 * 18, 198 + var9 / 8 * 18, 18, 18);
/*     */         } 
/*     */         
/*  98 */         String var11 = I18n.format(var8.getName(), new Object[0]);
/*     */         
/* 100 */         if (var7.getAmplifier() == 1) {
/*     */           
/* 102 */           var11 = String.valueOf(var11) + " " + I18n.format("enchantment.level.2", new Object[0]);
/*     */         }
/* 104 */         else if (var7.getAmplifier() == 2) {
/*     */           
/* 106 */           var11 = String.valueOf(var11) + " " + I18n.format("enchantment.level.3", new Object[0]);
/*     */         }
/* 108 */         else if (var7.getAmplifier() == 3) {
/*     */           
/* 110 */           var11 = String.valueOf(var11) + " " + I18n.format("enchantment.level.4", new Object[0]);
/*     */         } 
/*     */         
/* 113 */         fontRendererObj.drawStringWithShadow(var11, (var1 + 10 + 18), (var2 + 6), 16777215);
/* 114 */         String var10 = Potion.getDurationString(var7);
/* 115 */         fontRendererObj.drawStringWithShadow(var10, (var1 + 10 + 18), (var2 + 6 + 10), 8355711);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawTexturedModalRectd(int x, int y, int textureX, int textureY, int width, int height) {
/* 122 */     float var7 = 0.00390625F;
/* 123 */     float var8 = 0.00390625F;
/* 124 */     Tessellator var9 = Tessellator.getInstance();
/* 125 */     WorldRenderer var10 = var9.getWorldRenderer();
/* 126 */     var10.startDrawingQuads();
/* 127 */     var10.addVertexWithUV((x + 0), (y + height), zLevel, ((textureX + 0) * var7), ((textureY + height) * var8));
/* 128 */     var10.addVertexWithUV((x + width), (y + height), zLevel, ((textureX + width) * var7), ((textureY + height) * var8));
/* 129 */     var10.addVertexWithUV((x + width), (y + 0), zLevel, ((textureX + width) * var7), ((textureY + 0) * var8));
/* 130 */     var10.addVertexWithUV((x + 0), (y + 0), zLevel, ((textureX + 0) * var7), ((textureY + 0) * var8));
/* 131 */     var9.draw();
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\renderer\InventoryEffectRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */