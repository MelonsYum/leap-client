/*    */ package net.minecraft.client.gui;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.resources.I18n;
/*    */ 
/*    */ public class GuiListButton
/*    */   extends GuiButton
/*    */ {
/*    */   private boolean field_175216_o;
/*    */   private String field_175215_p;
/*    */   private final GuiPageButtonList.GuiResponder field_175214_q;
/*    */   private static final String __OBFID = "CL_00001953";
/*    */   
/*    */   public GuiListButton(GuiPageButtonList.GuiResponder p_i45539_1_, int p_i45539_2_, int p_i45539_3_, int p_i45539_4_, String p_i45539_5_, boolean p_i45539_6_) {
/* 15 */     super(p_i45539_2_, p_i45539_3_, p_i45539_4_, 150, 20, "");
/* 16 */     this.field_175215_p = p_i45539_5_;
/* 17 */     this.field_175216_o = p_i45539_6_;
/* 18 */     this.displayString = func_175213_c();
/* 19 */     this.field_175214_q = p_i45539_1_;
/*    */   }
/*    */ 
/*    */   
/*    */   private String func_175213_c() {
/* 24 */     return String.valueOf(I18n.format(this.field_175215_p, new Object[0])) + ": " + (this.field_175216_o ? I18n.format("gui.yes", new Object[0]) : I18n.format("gui.no", new Object[0]));
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_175212_b(boolean p_175212_1_) {
/* 29 */     this.field_175216_o = p_175212_1_;
/* 30 */     this.displayString = func_175213_c();
/* 31 */     this.field_175214_q.func_175321_a(this.id, p_175212_1_);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
/* 40 */     if (super.mousePressed(mc, mouseX, mouseY)) {
/*    */       
/* 42 */       this.field_175216_o = !this.field_175216_o;
/* 43 */       this.displayString = func_175213_c();
/* 44 */       this.field_175214_q.func_175321_a(this.id, this.field_175216_o);
/* 45 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 49 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\client\gui\GuiListButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */