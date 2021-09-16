/*    */ package leap.commands.impl;
/*    */ 
/*    */ import leap.Client;
/*    */ import leap.commands.Command;
/*    */ import net.minecraft.client.Minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VClip
/*    */   extends Command
/*    */ {
/*    */   public VClip() {
/* 13 */     super("VClip", "teleports the player up or down a certain amount", "vclip <distance>", new String[] { "v" });
/*    */   }
/*    */ 
/*    */   
/*    */   public void onCommand(String[] args, String command) {
/* 18 */     if (args.length == 1) {
/*    */       
/* 20 */       if (isInteger(args[0])) {
/* 21 */         Minecraft mc = Minecraft.getMinecraft();
/*    */         
/* 23 */         mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + Integer.valueOf(args[0]).intValue(), mc.thePlayer.posZ);
/*    */       } else {
/* 25 */         Client.addChatMessage("Invalid synax! Example: .vclip <distance>");
/*    */       } 
/*    */     } else {
/* 28 */       Client.addChatMessage("Invalid synax! Example: .vclip <distance>");
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean isInteger(String s) {
/* 33 */     if (s == null || s.equals("")) {
/* 34 */       return false;
/*    */     }
/*    */     
/*    */     try {
/* 38 */       int iVal = Integer.parseInt(s);
/* 39 */       return true;
/*    */     }
/* 41 */     catch (NumberFormatException numberFormatException) {
/*    */       
/* 43 */       return false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\wyatt\Downloads\Leap-Client.jar!\leap\commands\impl\VClip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */