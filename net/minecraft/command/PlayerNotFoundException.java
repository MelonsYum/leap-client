/*    */ package net.minecraft.command;
/*    */ 
/*    */ public class PlayerNotFoundException
/*    */   extends CommandException
/*    */ {
/*    */   private static final String __OBFID = "CL_00001190";
/*    */   
/*    */   public PlayerNotFoundException() {
/*  9 */     this("commands.generic.player.notFound", new Object[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerNotFoundException(String p_i1362_1_, Object... p_i1362_2_) {
/* 14 */     super(p_i1362_1_, p_i1362_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\net\minecraft\command\PlayerNotFoundException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */