/*    */ package net.minecraft.command;
/*    */ 
/*    */ public class SyntaxErrorException
/*    */   extends CommandException
/*    */ {
/*    */   private static final String __OBFID = "CL_00001189";
/*    */   
/*    */   public SyntaxErrorException() {
/*  9 */     this("commands.generic.snytax", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public SyntaxErrorException(String p_i1361_1_, Object... p_i1361_2_) {
/* 14 */     super(p_i1361_1_, p_i1361_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\SyntaxErrorException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */