/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.URI;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class GuiScreenDemo
/*    */   extends GuiScreen {
/* 14 */   private static final Logger logger = LogManager.getLogger();
/* 15 */   private static final ResourceLocation field_146348_f = new ResourceLocation("textures/gui/demo_background.png");
/*    */ 
/*    */   
/*    */   private static final String __OBFID = "CL_00000691";
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 23 */     this.buttonList.clear();
/* 24 */     byte var1 = -16;
/* 25 */     this.buttonList.add(new GuiButton(1, width / 2 - 116, height / 2 + 62 + var1, 114, 20, I18n.format("demo.help.buy", new Object[0])));
/* 26 */     this.buttonList.add(new GuiButton(2, width / 2 + 2, height / 2 + 62 + var1, 114, 20, I18n.format("demo.help.later", new Object[0])));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton button) throws IOException {
/* 31 */     switch (button.id) {
/*    */       
/*    */       case 1:
/* 34 */         button.enabled = false;
/*    */ 
/*    */         
/*    */         try {
/* 38 */           Class<?> var2 = Class.forName("java.awt.Desktop");
/* 39 */           Object var3 = var2.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
/* 40 */           var2.getMethod("browse", new Class[] { URI.class }).invoke(var3, new Object[] { new URI("http://www.minecraft.net/store?source=demo") });
/*    */         }
/* 42 */         catch (Throwable var4) {
/*    */           
/* 44 */           logger.error("Couldn't open link", var4);
/*    */         } 
/*    */         break;
/*    */ 
/*    */       
/*    */       case 2:
/* 50 */         this.mc.displayGuiScreen(null);
/* 51 */         this.mc.setIngameFocus();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateScreen() {
/* 60 */     super.updateScreen();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawDefaultBackground() {
/* 68 */     super.drawDefaultBackground();
/* 69 */     GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
/* 70 */     this.mc.getTextureManager().bindTexture(field_146348_f);
/* 71 */     int var1 = (width - 248) / 2;
/* 72 */     int var2 = (height - 166) / 2;
/* 73 */     drawTexturedModalRect(var1, var2, 0, 0, 248, 166);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 81 */     drawDefaultBackground();
/* 82 */     int var4 = (width - 248) / 2 + 10;
/* 83 */     int var5 = (height - 166) / 2 + 8;
/* 84 */     fontRendererObj.drawString(I18n.format("demo.help.title", new Object[0]), var4, var5, 2039583);
/* 85 */     var5 += 12;
/* 86 */     GameSettings var6 = this.mc.gameSettings;
/* 87 */     fontRendererObj.drawString(I18n.format("demo.help.movementShort", new Object[] { GameSettings.getKeyDisplayString(var6.keyBindForward.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindLeft.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindBack.getKeyCode()), GameSettings.getKeyDisplayString(var6.keyBindRight.getKeyCode()) }), var4, var5, 5197647);
/* 88 */     fontRendererObj.drawString(I18n.format("demo.help.movementMouse", new Object[0]), var4, (var5 + 12), 5197647);
/* 89 */     fontRendererObj.drawString(I18n.format("demo.help.jump", new Object[] { GameSettings.getKeyDisplayString(var6.keyBindJump.getKeyCode()) }), var4, (var5 + 24), 5197647);
/* 90 */     fontRendererObj.drawString(I18n.format("demo.help.inventory", new Object[] { GameSettings.getKeyDisplayString(var6.keyBindInventory.getKeyCode()) }), var4, (var5 + 36), 5197647);
/* 91 */     fontRendererObj.drawSplitString(I18n.format("demo.help.fullWrapped", new Object[0]), var4, var5 + 68, 218, 2039583);
/* 92 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiScreenDemo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */