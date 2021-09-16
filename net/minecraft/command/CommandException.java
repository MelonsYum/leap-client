/*    */ package net.minecraft.command;
/*    */ 
/*    */ public class CommandException
/*    */   extends Exception
/*    */ {
/*    */   private final Object[] errorObjects;
/*    */   private static final String __OBFID = "CL_00001187";
/*    */   
/*    */   public CommandException(String p_i1359_1_, Object... p_i1359_2_) {
/* 10 */     super(p_i1359_1_);
/* 11 */     this.errorObjects = p_i1359_2_;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object[] getErrorOjbects() {
/* 16 */     return this.errorObjects;
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */