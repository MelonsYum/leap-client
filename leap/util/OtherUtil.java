/*     */ package leap.util;
/*     */ 
/*     */ import leap.modules.combat.KillAura;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.FontRenderer;
/*     */ import net.minecraft.client.gui.ScaledResolution;
/*     */ import net.minecraft.client.renderer.GlStateManager;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.WorldRenderer;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import optifine.Reflector;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class OtherUtil
/*     */ {
/*  20 */   public static Minecraft mc = Minecraft.getMinecraft();
/*     */   
/*     */   public static void drawArmorStatus(ScaledResolution scaledRes) {
/*  23 */     if (mc.playerController.isNotCreative()) {
/*  24 */       int x = 15;
/*  25 */       GL11.glPushMatrix();
/*     */       
/*  27 */       for (int index = 4; index >= 0; index--) {
/*  28 */         ItemStack stack = mc.thePlayer.getEquipmentInSlot(index);
/*  29 */         if (stack != null) {
/*  30 */           mc.getRenderItem().func_180450_b(stack, scaledRes.getScaledWidth() / 2 + x - 1, scaledRes.getScaledHeight() - (mc.thePlayer.isInsideOfMaterial(Material.water) ? 65 : 55) - 2);
/*  31 */           mc.getRenderItem().func_175030_a(mc.fontRendererObj, stack, scaledRes.getScaledWidth() / 2 + x - 1, scaledRes.getScaledHeight() - (mc.thePlayer.isInsideOfMaterial(Material.water) ? 65 : 55) - 2);
/*  32 */           x += 18;
/*     */         } 
/*     */       } 
/*     */       
/*  36 */       GlStateManager.disableCull();
/*  37 */       GlStateManager.enableAlpha();
/*  38 */       GlStateManager.disableBlend();
/*  39 */       GlStateManager.disableLighting();
/*  40 */       GlStateManager.disableCull();
/*  41 */       GlStateManager.clear(256);
/*  42 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawArmorStatusTarget(ScaledResolution scaledRes) {
/*  49 */     if (mc.playerController.isNotCreative()) {
/*  50 */       int x = 15;
/*  51 */       GlStateManager.pushMatrix();
/*     */       
/*  53 */       for (int index = 4; index >= 0; index--) {
/*  54 */         ItemStack stack = ((EntityLivingBase)KillAura.targets.get(0)).getEquipmentInSlot(index);
/*  55 */         if (stack != null) {
/*  56 */           mc.getRenderItem().func_180450_b(stack, (int)(scaledRes.getScaledWidth() / 1.78D + x - 1.0D), scaledRes.getScaledHeight() / 2 - (mc.thePlayer.isInsideOfMaterial(Material.water) ? 65 : 55) + 10);
/*  57 */           mc.getRenderItem().func_175030_a(mc.fontRendererObj, stack, (int)(scaledRes.getScaledWidth() / 1.78D + x - 1.0D), scaledRes.getScaledHeight() / 2 - (mc.thePlayer.isInsideOfMaterial(Material.water) ? 65 : 55) + 10);
/*  58 */           x += 18;
/*     */         } 
/*     */       } 
/*     */       
/*  62 */       GlStateManager.disableCull();
/*  63 */       GlStateManager.enableAlpha();
/*  64 */       GlStateManager.disableBlend();
/*  65 */       GlStateManager.disableLighting();
/*  66 */       GlStateManager.disableCull();
/*  67 */       GlStateManager.clear(256);
/*  68 */       GlStateManager.popMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_180453_a(FontRenderer p_180453_1_, ItemStack p_180453_2_, int p_180453_3_, int p_180453_4_, String p_180453_5_) {
/*  76 */     if (p_180453_2_ != null) {
/*     */       
/*  78 */       if (p_180453_2_.stackSize != 1 || p_180453_5_ != null) {
/*     */         
/*  80 */         String itemDamaged = (p_180453_5_ == null) ? String.valueOf(p_180453_2_.stackSize) : p_180453_5_;
/*     */         
/*  82 */         if (p_180453_5_ == null && p_180453_2_.stackSize < 1)
/*     */         {
/*  84 */           itemDamaged = EnumChatFormatting.RED + String.valueOf(p_180453_2_.stackSize);
/*     */         }
/*     */         
/*  87 */         GlStateManager.disableLighting();
/*  88 */         GlStateManager.disableDepth();
/*  89 */         GlStateManager.disableBlend();
/*  90 */         p_180453_1_.drawStringWithShadow(itemDamaged, (p_180453_3_ + 19 - 2 - p_180453_1_.getStringWidth(itemDamaged)), (p_180453_4_ + 6 + 3), 16777215);
/*  91 */         GlStateManager.enableLighting();
/*  92 */         GlStateManager.enableDepth();
/*     */       } 
/*     */       
/*  95 */       boolean itemDamaged1 = p_180453_2_.isItemDamaged();
/*     */       
/*  97 */       if (Reflector.ForgeItem_showDurabilityBar.exists())
/*     */       {
/*  99 */         itemDamaged1 = Reflector.callBoolean(p_180453_2_.getItem(), Reflector.ForgeItem_showDurabilityBar, new Object[] { p_180453_2_ });
/*     */       }
/*     */       
/* 102 */       if (itemDamaged1) {
/*     */         
/* 104 */         int var12 = (int)Math.round(13.0D - p_180453_2_.getItemDamage() * 13.0D / p_180453_2_.getMaxDamage());
/* 105 */         int var7 = (int)Math.round(255.0D - p_180453_2_.getItemDamage() * 255.0D / p_180453_2_.getMaxDamage());
/*     */         
/* 107 */         if (Reflector.ForgeItem_getDurabilityForDisplay.exists()) {
/*     */           
/* 109 */           double var8 = Reflector.callDouble(p_180453_2_.getItem(), Reflector.ForgeItem_getDurabilityForDisplay, new Object[] { p_180453_2_ });
/* 110 */           var12 = (int)Math.round(13.0D - var8 * 13.0D);
/* 111 */           var7 = (int)Math.round(255.0D - var8 * 255.0D);
/*     */         } 
/*     */         
/* 114 */         GlStateManager.disableLighting();
/* 115 */         GlStateManager.disableDepth();
/* 116 */         GlStateManager.func_179090_x();
/* 117 */         GlStateManager.disableAlpha();
/* 118 */         GlStateManager.disableBlend();
/* 119 */         Tessellator var81 = Tessellator.getInstance();
/* 120 */         WorldRenderer var9 = var81.getWorldRenderer();
/* 121 */         int var10 = 255 - var7 << 16 | var7 << 8;
/* 122 */         int var11 = (255 - var7) / 4 << 16 | 0x3F00;
/* 123 */         mc.getRenderItem().func_175044_a(var9, p_180453_3_ + 2, p_180453_4_ + 13, 13, 2, 0);
/* 124 */         mc.getRenderItem().func_175044_a(var9, p_180453_3_ + 2, p_180453_4_ + 13, 12, 1, var11);
/* 125 */         mc.getRenderItem().func_175044_a(var9, p_180453_3_ + 2, p_180453_4_ + 13, var12, 1, var10);
/*     */         
/* 127 */         if (!Reflector.ForgeHooksClient.exists())
/*     */         {
/* 129 */           GlStateManager.enableBlend();
/*     */         }
/*     */         
/* 132 */         GlStateManager.enableAlpha();
/* 133 */         GlStateManager.func_179098_w();
/* 134 */         GlStateManager.enableLighting();
/* 135 */         GlStateManager.enableDepth();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\lea\\util\OtherUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */