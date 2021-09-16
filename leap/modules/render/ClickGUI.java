/*    */ package leap.modules.render;
/*    */ 
/*    */ import leap.events.Event;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.BooleanSetting;
/*    */ import leap.settings.ModeSetting;
/*    */ import leap.settings.NumberSetting;
/*    */ import leap.settings.Setting;
/*    */ import leap.ui.ClickGUIScreen;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ 
/*    */ public class ClickGUI
/*    */   extends Module
/*    */ {
/*    */   public int currentTab;
/*    */   int mouseX;
/*    */   int mouseY;
/*    */   public static final int HEADER_SIZE = 20;
/*    */   public static final int HEADER_OFFSET = 2;
/* 21 */   private final Minecraft mc = Minecraft.getMinecraft();
/* 22 */   public ModeSetting theme = new ModeSetting("Mode", "Leap", new String[] { "Custom Color", "Leap" });
/* 23 */   public BooleanSetting blur = new BooleanSetting("Blur", false);
/* 24 */   public BooleanSetting custom = new BooleanSetting("Custom Tab", true);
/* 25 */   public NumberSetting offset = new NumberSetting("Offset", 2.0D, 1.0D, 15.0D, 0.5D);
/* 26 */   public NumberSetting corners = new NumberSetting("Corners", 3.0D, 1.0D, 6.0D, 1.0D);
/* 27 */   public NumberSetting red = new NumberSetting("Red", 1.0D, 1.0D, 255.0D, 1.0D);
/* 28 */   public NumberSetting green = new NumberSetting("Green", 1.0D, 1.0D, 255.0D, 1.0D);
/* 29 */   public NumberSetting blue = new NumberSetting("Blue", 1.0D, 1.0D, 255.0D, 1.0D);
/* 30 */   public NumberSetting alpha = new NumberSetting("Alpha", 1.0D, 0.1D, 1.0D, 0.1D);
/* 31 */   public NumberSetting rainbowSpeed = new NumberSetting("Rainbow Speed", 30.0D, 1.0D, 100.0D, 1.0D);
/*    */   
/*    */   public ClickGUI() {
/* 34 */     super("ClickGUI", 54, Module.Category.RENDER);
/* 35 */     addSettings(new Setting[] { (Setting)this.theme, (Setting)this.blur });
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 39 */     this.mc.displayGuiScreen((GuiScreen)new ClickGUIScreen());
/* 40 */     toggle();
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 44 */     super.onDisable();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEvent(Event e) {
/* 49 */     if (e instanceof leap.events.listeners.EventUpdate)
/* 50 */       if (!this.settings.contains(this.custom) && this.theme.getMode() == "Custom Color") {
/* 51 */         addSettings(new Setting[] { (Setting)this.custom, (Setting)this.offset, (Setting)this.corners, (Setting)this.red, (Setting)this.green, (Setting)this.blue, (Setting)this.alpha, (Setting)this.rainbowSpeed });
/* 52 */       } else if (this.theme.getMode() != "Custom Color" && this.settings.contains(this.custom)) {
/* 53 */         removeSettings(new Setting[] { (Setting)this.custom, (Setting)this.offset, (Setting)this.corners, (Setting)this.red, (Setting)this.green, (Setting)this.blue, (Setting)this.alpha, (Setting)this.rainbowSpeed });
/*    */       }  
/*    */   }
/*    */   
/*    */   public void onStandby() {
/* 58 */     if (!this.settings.contains(this.corners) && this.theme.getMode() == "Custom Color") {
/* 59 */       addSettings(new Setting[] { (Setting)this.offset, (Setting)this.corners, (Setting)this.red, (Setting)this.green, (Setting)this.blue, (Setting)this.alpha, (Setting)this.rainbowSpeed });
/* 60 */     } else if (this.theme.getMode() != "Custom Color" && this.settings.contains(this.corners)) {
/* 61 */       removeSettings(new Setting[] { (Setting)this.offset, (Setting)this.corners, (Setting)this.red, (Setting)this.green, (Setting)this.blue, (Setting)this.alpha, (Setting)this.rainbowSpeed });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\modules\render\ClickGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */