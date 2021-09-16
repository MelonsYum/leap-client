/*    */ package optifine;
/*    */ 
/*    */ import net.minecraft.client.gui.GuiOptionButton;
/*    */ import net.minecraft.client.settings.GameSettings;
/*    */ 
/*    */ public class GuiOptionButtonOF
/*    */   extends GuiOptionButton implements IOptionControl {
/*  8 */   private GameSettings.Options option = null;
/*    */ 
/*    */   
/*    */   public GuiOptionButtonOF(int id, int x, int y, GameSettings.Options option, String text) {
/* 12 */     super(id, x, y, option, text);
/* 13 */     this.option = option;
/*    */   }
/*    */ 
/*    */   
/*    */   public GameSettings.Options getOption() {
/* 18 */     return this.option;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\optifine\GuiOptionButtonOF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */