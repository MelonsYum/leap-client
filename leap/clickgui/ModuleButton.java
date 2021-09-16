/*    */ package leap.clickgui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import leap.Client;
/*    */ import leap.modules.Module;
/*    */ import leap.settings.Setting;
/*    */ import leap.util.JColor;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.audio.ISound;
/*    */ import net.minecraft.client.audio.PositionedSoundRecord;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.lwjgl.input.Mouse;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModuleButton
/*    */ {
/*    */   int x;
/*    */   int y;
/*    */   int width;
/*    */   int height;
/*    */   Module module;
/*    */   GuiFrame parent;
/*    */   public static boolean displaysettings = true;
/* 29 */   public static Minecraft mc = Minecraft.getMinecraft();
/*    */   public static String currentmodule;
/*    */   ArrayList<ModuleButton> moduleButtons;
/*    */   
/*    */   public ModuleButton(Module modulesbruh, int x, int y, GuiFrame parent) {
/* 34 */     this.module = modulesbruh;
/* 35 */     this.x = x;
/* 36 */     this.y = y;
/* 37 */     this.parent = parent;
/* 38 */     this.width = parent.width;
/* 39 */     this.height = 14;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void draw(int MouseX, int MouseY) {
/* 48 */     GlStateManager.pushMatrix();
/* 49 */     if (this.module.toggled) {
/* 50 */       Client.customFontRenderer.drawString(this.module.name, (this.x + 2 + 100), (this.y + 2), new JColor((new Color(0, 204, 204)).getRGB()));
/*    */     } else {
/* 52 */       Client.customFontRenderer.drawString(this.module.name, (this.x + 2 + 100), (this.y + 2), new JColor((new Color(255, 255, 255)).getRGB()));
/*    */     } 
/*    */     
/* 55 */     GlStateManager.popMatrix();
/*    */   }
/*    */   public void onClick(int x, int y, int button) {
/* 58 */     if (x >= this.x + 100 && x <= this.x + 100 + this.width && y >= this.y && y <= this.y + this.height) {
/* 59 */       if (Mouse.isButtonDown(0)) {
/* 60 */         this.module.toggle();
/* 61 */         Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("gui.button.press"), 1.0F));
/*    */       } 
/* 63 */       if (Mouse.isButtonDown(1))
/* 64 */         for (Setting setting : this.module.settings)
/* 65 */           currentmodule = this.module.name;  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\clickgui\ModuleButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */