/*    */ package leap.clickgui;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import leap.modules.Module;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ import net.minecraft.client.gui.ScaledResolution;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClickGUIScreen2
/*    */   extends GuiScreen
/*    */ {
/*    */   static int x;
/*    */   static int y;
/*    */   int width;
/*    */   int height;
/* 24 */   public static ClickGUIScreen2 INSTANCE = new ClickGUIScreen2(x, y);
/*    */   
/*    */   ArrayList<GuiFrame> frames;
/*    */ 
/*    */   
/*    */   public ClickGUIScreen2(int x, int y) {
/* 30 */     ClickGUIScreen2.x = x;
/* 31 */     ClickGUIScreen2.y = y;
/* 32 */     this.width = 100;
/* 33 */     this.height = 260;
/*    */     
/* 35 */     this.frames = new ArrayList<>();
/* 36 */     int offset = 0; byte b; int i; Module.Category[] arrayOfCategory;
/* 37 */     for (i = (arrayOfCategory = Module.Category.values()).length, b = 0; b < i; ) { Module.Category category = arrayOfCategory[b];
/* 38 */       this.frames.add(new GuiFrame(category, 10 + offset, 20));
/* 39 */       offset += 110;
/*    */       b++; }
/*    */   
/*    */   }
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 44 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*    */     
/* 46 */     int count = 0;
/*    */     
/* 48 */     ScaledResolution sr = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
/*    */     
/* 50 */     this.mc.getTextureManager().bindTexture(new ResourceLocation("clickgui/Combat.png"));
/* 51 */     Gui.drawModalRectWithCustomSizedTexture(100.0F, (this.mc.displayHeight / 50), 4.0F, 10.0F, 108.0F, 200.0F, 128.0F, 570.0F);
/*    */     
/* 53 */     this.mc.getTextureManager().bindTexture(new ResourceLocation("clickgui/Movement.png"));
/* 54 */     Gui.drawModalRectWithCustomSizedTexture(215.0F, (this.mc.displayHeight / 50), 4.0F, 10.0F, 108.0F, 240.0F, 128.0F, 570.0F);
/*    */     
/* 56 */     this.mc.getTextureManager().bindTexture(new ResourceLocation("clickgui/Player.png"));
/* 57 */     Gui.drawModalRectWithCustomSizedTexture(325.0F, (this.mc.displayHeight / 50), 4.0F, 10.0F, 108.0F, 200.0F, 128.0F, 570.0F);
/*    */     
/* 59 */     this.mc.getTextureManager().bindTexture(new ResourceLocation("clickgui/Render.png"));
/* 60 */     Gui.drawModalRectWithCustomSizedTexture(435.0F, (this.mc.displayHeight / 50), 4.0F, 10.0F, 108.0F, 200.0F, 128.0F, 570.0F);
/*    */     
/* 62 */     this.mc.getTextureManager().bindTexture(new ResourceLocation("clickgui/Memes.png"));
/* 63 */     Gui.drawModalRectWithCustomSizedTexture(545.0F, (this.mc.displayHeight / 50), 4.0F, 10.0F, 108.0F, 200.0F, 128.0F, 570.0F);
/*    */     
/* 65 */     this.mc.getTextureManager().bindTexture(new ResourceLocation("clickgui/Ghost.png"));
/* 66 */     Gui.drawModalRectWithCustomSizedTexture(655.0F, (this.mc.displayHeight / 50), 4.0F, 10.0F, 108.0F, 200.0F, 128.0F, 570.0F);
/*    */ 
/*    */     
/* 69 */     for (GuiFrame frame : this.frames) {
/* 70 */       frame.render(mouseX, mouseY);
/*    */     }
/*    */   }
/*    */   
/*    */   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
/* 75 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 76 */     for (GuiFrame frame : this.frames)
/* 77 */       frame.onClick(mouseX, mouseY, mouseButton); 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\clickgui\ClickGUIScreen2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */