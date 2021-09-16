/*    */ package leap.clickgui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import leap.Client;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.FontRenderer;
/*    */ 
/*    */ public class SettingButton
/*    */ {
/*    */   int x;
/*    */   int y;
/*    */   int width;
/*    */   int height;
/*    */   int currentTab;
/* 22 */   int maxLength = 0;
/*    */   
/*    */   Setting setting;
/*    */   
/*    */   GuiFrame parent;
/*    */   
/* 28 */   Minecraft mc = Minecraft.getMinecraft();
/*    */   
/* 30 */   int count = 0;
/*    */   
/*    */   ArrayList<SettingButton> moduleButtons;
/*    */   
/*    */   public SettingButton(Setting settingbruh, int x, int y, GuiFrame parent) {
/* 35 */     this.setting = settingbruh;
/* 36 */     this.x = x;
/* 37 */     this.y = y;
/* 38 */     this.parent = parent;
/* 39 */     this.width = parent.width;
/* 40 */     this.height = 14;
/*    */   }
/*    */ 
/*    */   
/*    */   public void draw(int MouseX, int MouseY) {
/* 45 */     FontRenderer fr = this.mc.fontRendererObj;
/* 46 */     this.currentTab = (Module.Category.values()).length - 1;
/* 47 */     Module.Category category = Module.Category.values()[this.currentTab];
/* 48 */     List<Module> modules = Client.getModulesByCategory(category);
/* 49 */     int index = 0;
/* 50 */     Module module = modules.get(category.moduleIndex);
/* 51 */     index = this.setting.name.length() - 1;
/*    */     
/* 53 */     if (this.setting instanceof BooleanSetting) {
/* 54 */       if (((BooleanSetting)this.setting).isEnabled()) {
/* 55 */         BooleanSetting bool = (BooleanSetting)this.setting;
/* 56 */         this.mc.fontRendererObj.drawStringWithShadow(String.valueOf(this.setting.name) + ": " + (bool.enabled ? "On" : "Off"), (this.x + 2 + 100), (this.y + 2 + 65), (new Color(255, 0, 0)).getRGB());
/*    */       } else {
/* 58 */         BooleanSetting bool = (BooleanSetting)this.setting;
/* 59 */         this.mc.fontRendererObj.drawStringWithShadow(String.valueOf(this.setting.name) + ": " + (bool.enabled ? "On" : "Off"), (this.x + 2 + 100), (this.y + 2 + 50), (new Color(255, 255, 255)).getRGB());
/*    */       } 
/*    */     }
/* 62 */     if (this.setting instanceof ModeSetting) {
/* 63 */       ModeSetting mode = (ModeSetting)this.setting;
/* 64 */       this.mc.fontRendererObj.drawStringWithShadow(String.valueOf(this.setting.name) + ": " + mode.getMode(), (this.x + 2 + 100), (this.y + 2 + 50), (new Color(255, 255, 255)).getRGB());
/*    */     } 
/*    */     
/* 67 */     if (this.setting instanceof NumberSetting) {
/* 68 */       NumberSetting number = (NumberSetting)this.setting;
/* 69 */       this.mc.fontRendererObj.drawStringWithShadow(String.valueOf(this.setting.name) + ": " + number.getValue(), (this.x + 2 + 100), (this.y + 2 + 50), (new Color(255, 255, 255)).getRGB());
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onClick(int x, int y, int button) {
/* 74 */     Module.Category category = Module.Category.values()[this.currentTab];
/* 75 */     List<Module> modules = Client.getModulesByCategory(category);
/* 76 */     Module module = modules.get(category.moduleIndex);
/*    */     
/* 78 */     if (x >= this.x + 100 && x <= this.x + 100 + this.width && y >= this.y + 50 && y <= this.y + 50 + this.height) {
/*    */       
/* 80 */       if (this.setting instanceof BooleanSetting) {
/* 81 */         ((BooleanSetting)this.setting).toggle();
/*    */       }
/* 83 */       if (this.setting instanceof ModeSetting)
/* 84 */         ((ModeSetting)this.setting).cycle(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\clickgui\SettingButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */