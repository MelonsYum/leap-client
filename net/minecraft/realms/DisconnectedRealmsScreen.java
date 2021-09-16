/*    */ package net.minecraft.realms;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import net.minecraft.util.IChatComponent;
/*    */ 
/*    */ public class DisconnectedRealmsScreen
/*    */   extends RealmsScreen
/*    */ {
/*    */   private String title;
/*    */   private IChatComponent reason;
/*    */   private List lines;
/*    */   private final RealmsScreen parent;
/*    */   private static final String __OBFID = "CL_00002145";
/*    */   
/*    */   public DisconnectedRealmsScreen(RealmsScreen p_i45742_1_, String p_i45742_2_, IChatComponent p_i45742_3_) {
/* 17 */     this.parent = p_i45742_1_;
/* 18 */     this.title = getLocalizedString(p_i45742_2_);
/* 19 */     this.reason = p_i45742_3_;
/*    */   }
/*    */ 
/*    */   
/*    */   public void init() {
/* 24 */     buttonsClear();
/* 25 */     buttonsAdd(newButton(0, width() / 2 - 100, height() / 4 + 120 + 12, getLocalizedString("gui.back")));
/* 26 */     this.lines = fontSplit(this.reason.getFormattedText(), width() - 50);
/*    */   }
/*    */ 
/*    */   
/*    */   public void keyPressed(char p_keyPressed_1_, int p_keyPressed_2_) {
/* 31 */     if (p_keyPressed_2_ == 1)
/*    */     {
/* 33 */       Realms.setScreen(this.parent);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void buttonClicked(RealmsButton p_buttonClicked_1_) {
/* 39 */     if (p_buttonClicked_1_.id() == 0)
/*    */     {
/* 41 */       Realms.setScreen(this.parent);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
/* 47 */     renderBackground();
/* 48 */     drawCenteredString(this.title, width() / 2, height() / 2 - 50, 11184810);
/* 49 */     int var4 = height() / 2 - 30;
/*    */     
/* 51 */     if (this.lines != null)
/*    */     {
/* 53 */       for (Iterator<String> var5 = this.lines.iterator(); var5.hasNext(); var4 += fontLineHeight()) {
/*    */         
/* 55 */         String var6 = var5.next();
/* 56 */         drawCenteredString(var6, width() / 2, var4, 16777215);
/*    */       } 
/*    */     }
/*    */     
/* 60 */     super.render(p_render_1_, p_render_2_, p_render_3_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\realms\DisconnectedRealmsScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */