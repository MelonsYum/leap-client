/*    */ package leap.clickgui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import leap.Client;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.Gui;
/*    */ 
/*    */ 
/*    */ public class GuiFrame
/*    */ {
/*    */   int x;
/*    */   int y;
/*    */   int width;
/*    */   int height;
/*    */   Module.Category category;
/* 20 */   Minecraft mc = Minecraft.getMinecraft();
/*    */   
/*    */   ArrayList<ModuleButton> moduleButtons;
/*    */   static ArrayList<SettingButton> settingButtons;
/*    */   
/*    */   public GuiFrame(Module.Category category, int x, int y) {
/* 26 */     this.x = x;
/* 27 */     this.y = y;
/* 28 */     this.width = 100;
/* 29 */     this.height = 260;
/* 30 */     this.category = category;
/*    */     
/* 32 */     this.moduleButtons = new ArrayList<>();
/* 33 */     settingButtons = new ArrayList<>();
/*    */ 
/*    */     
/* 36 */     int offsetY = 14;
/* 37 */     for (Module module1 : Client.getModulesByCategory(category)) {
/* 38 */       this.moduleButtons.add(new ModuleButton(module1, x, y + offsetY, this));
/*    */ 
/*    */       
/* 41 */       offsetY += 14;
/*    */     } 
/* 43 */     for (Setting setting : Client.getSettingsByCategory(category)) {
/* 44 */       settingButtons.add(new SettingButton(setting, x, y + offsetY, this));
/*    */ 
/*    */       
/* 47 */       offsetY += 14;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 52 */     List<Module> modules = Client.getModulesByCategory(category);
/* 53 */     Module module = modules.get(category.moduleIndex);
/*    */   }
/*    */ 
/*    */   
/*    */   public void guiback() {
/* 58 */     Gui.drawRect((this.x + 100), this.y, (this.x + this.width + 70), (this.y + this.height + 18), (new Color(72, 73, 75, 255)).getRGB());
/*    */   }
/*    */   
/*    */   public void render(int MouseX, int MouseY) {
/* 62 */     for (ModuleButton moduleButton : this.moduleButtons) {
/* 63 */       moduleButton.draw(MouseX, MouseY);
/*    */     }
/* 65 */     for (SettingButton settingButton : settingButtons) {
/* 66 */       settingButton.draw(MouseX, MouseY);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onClick(int x, int y, int button) {
/* 72 */     for (ModuleButton moduleButton : this.moduleButtons) {
/* 73 */       moduleButton.onClick(x, y, button);
/*    */     }
/* 75 */     for (SettingButton settingButton : settingButtons)
/* 76 */       settingButton.onClick(x, y, button); 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\clickgui\GuiFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */