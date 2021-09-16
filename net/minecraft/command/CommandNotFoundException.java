/*    */ package net.minecraft.command;
/*    */ 
/*    */ public class CommandNotFoundException
/*    */   extends CommandException
/*    */ {
/*    */   private static final String __OBFID = "CL_00001191";
/*    */   
/*    */   public CommandNotFoundException() {
/*  9 */     this("commands.generic.notFound", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandNotFoundException(String p_i1363_1_, Object... p_i1363_2_) {
/* 14 */     super(p_i1363_1_, p_i1363_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\CommandNotFoundException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */