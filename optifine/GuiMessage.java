/*    */ package optifine;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.io.IOException;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.gui.GuiButton;
/*    */ import net.minecraft.client.gui.GuiOptionButton;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ 
/*    */ public class GuiMessage
/*    */   extends GuiScreen {
/*    */   private GuiScreen parentScreen;
/*    */   private String messageLine1;
/*    */   private String messageLine2;
/* 17 */   private final List listLines2 = Lists.newArrayList();
/*    */   
/*    */   protected String confirmButtonText;
/*    */   private int ticksUntilEnable;
/*    */   
/*    */   public GuiMessage(GuiScreen parentScreen, String line1, String line2) {
/* 23 */     this.parentScreen = parentScreen;
/* 24 */     this.messageLine1 = line1;
/* 25 */     this.messageLine2 = line2;
/* 26 */     this.confirmButtonText = I18n.format("gui.done", new Object[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initGui() {
/* 34 */     this.buttonList.add(new GuiOptionButton(0, width / 2 - 74, height / 6 + 96, this.confirmButtonText));
/* 35 */     this.listLines2.clear();
/* 36 */     this.listLines2.addAll(fontRendererObj.listFormattedStringToWidth(this.messageLine2, width - 50));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void actionPerformed(GuiButton button) throws IOException {
/* 41 */     Config.getMinecraft().displayGuiScreen(this.parentScreen);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 49 */     drawDefaultBackground();
/* 50 */     drawCenteredString(fontRendererObj, this.messageLine1, (width / 2), 70.0F, 16777215);
/* 51 */     int var4 = 90;
/*    */     
/* 53 */     for (Iterator<String> var5 = this.listLines2.iterator(); var5.hasNext(); var4 += fontRendererObj.FONT_HEIGHT) {
/*    */       
/* 55 */       String var6 = var5.next();
/* 56 */       drawCenteredString(fontRendererObj, var6, (width / 2), var4, 16777215);
/*    */     } 
/*    */     
/* 59 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setButtonDelay(int ticksUntilEnable) {
/* 64 */     this.ticksUntilEnable = ticksUntilEnable;
/*    */ 
/*    */     
/* 67 */     for (Iterator<GuiButton> var2 = this.buttonList.iterator(); var2.hasNext(); var3.enabled = false)
/*    */     {
/* 69 */       GuiButton var3 = var2.next();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateScreen() {
/* 78 */     super.updateScreen();
/*    */ 
/*    */     
/* 81 */     if (--this.ticksUntilEnable == 0)
/*    */     {
/* 83 */       for (Iterator<GuiButton> var1 = this.buttonList.iterator(); var1.hasNext(); var2.enabled = true)
/*    */       {
/* 85 */         GuiButton var2 = var1.next();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\GuiMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */