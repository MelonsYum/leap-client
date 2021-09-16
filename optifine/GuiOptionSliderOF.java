/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiOptionSlider;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ 
/*    */ public class GuiOptionSliderOF
/*    */   extends GuiOptionSlider implements IOptionControl {
/*  8 */   private GameSettings.Options option = null;
/*    */ 
/*    */   
/*    */   public GuiOptionSliderOF(int id, int x, int y, GameSettings.Options option) {
/* 12 */     super(id, x, y, option);
/* 13 */     this.option = option;
/*    */   }
/*    */ 
/*    */   
/*    */   public GameSettings.Options getOption() {
/* 18 */     return this.option;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\GuiOptionSliderOF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */