/*    */ package net.minecraft.util;
/*    */ 
/*    */ import org.lwjgl.input.Mouse;
/*    */ import org.lwjgl.opengl.Display;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MouseHelper
/*    */ {
/*    */   public int deltaX;
/*    */   public int deltaY;
/*    */   private static final String __OBFID = "CL_00000648";
/*    */   
/*    */   public void grabMouseCursor() {
/* 20 */     Mouse.setGrabbed(true);
/* 21 */     this.deltaX = 0;
/* 22 */     this.deltaY = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void ungrabMouseCursor() {
/* 30 */     Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
/* 31 */     Mouse.setGrabbed(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseXYChange() {
/* 36 */     this.deltaX = Mouse.getDX();
/* 37 */     this.deltaY = Mouse.getDY();
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraf\\util\MouseHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */