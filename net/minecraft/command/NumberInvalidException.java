/*    */ package net.minecraft.command;
/*    */ 
/*    */ public class NumberInvalidException
/*    */   extends CommandException
/*    */ {
/*    */   private static final String __OBFID = "CL_00001188";
/*    */   
/*    */   public NumberInvalidException() {
/*  9 */     this("commands.generic.num.invalid", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public NumberInvalidException(String p_i1360_1_, Object... p_i1360_2_) {
/* 14 */     super(p_i1360_1_, p_i1360_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\NumberInvalidException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */